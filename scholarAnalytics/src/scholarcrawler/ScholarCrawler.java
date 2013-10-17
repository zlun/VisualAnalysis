package scholarcrawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

import scholarcrawler.containers.ScholarAuthor;
import scholarcrawler.util.FileOpener;

public class ScholarCrawler
{	
	public static final String AUTHOR_CRAWL_TASK = "authorCrawl";
	public static final String AUTHOR_PARSE_TASK = "authorParse";
	
	public static final String baseUrl = "http://scholar.google.ca";
	
	public static void main(String[] args)
	{
		String taskName = null;
		String inputFolder = null;
		String outputFolder = null;
		String folderPrefix = null;
		
		
		if(args.length == 0)
		{
			//taskName = AUTHOR_CRAWL_TASK;
			taskName = AUTHOR_PARSE_TASK;
			
			String rawSitePath = "/Users/zwu/Downloads/scholar/raw/";
			String authorSitePath = "/Users/zwu/Downloads/scholar/authorRaw/";
			String authorParsedPath = "/Users/zwu/Downloads/scholar/author-dal.json";
			
			if(AUTHOR_CRAWL_TASK.equals(taskName))
			{
				inputFolder = rawSitePath;
				outputFolder = authorSitePath;
			}
			else if(AUTHOR_PARSE_TASK.equals(taskName))
			{
				inputFolder = authorSitePath;
				outputFolder = authorParsedPath;
			}
			
			folderPrefix = "dal";
			//folderPrefix = "stanford";
		}
		else if(args.length == 4)
		{
			taskName = args[0];
			inputFolder = args[1];
			outputFolder = args[2];
			folderPrefix = args[3];
		}
		else
		{
			System.out.println("Incorrect number of parameters. The system requires four parameters:");
			System.out.println("1 - task name: authorCrawl or authorParse");
			System.out.println("2 - input folder");
			System.out.println("3 - output folder");
			System.out.println("4 - prefix of data folders (for example stanford or dal)");
			System.exit(1);
		}
		
		
		ScholarCrawler crawler = new ScholarCrawler();
		
		if(AUTHOR_CRAWL_TASK.equals(taskName))
		{
			crawler.crawlAuthorPages(inputFolder, outputFolder, folderPrefix);
		}
		else if(AUTHOR_PARSE_TASK.equals(taskName))
		{
			crawler.parseAuthors(inputFolder, outputFolder, folderPrefix);
		}
		else
		{
			System.out.println("Incorrect task name, or task name equal to null");
		}
	}
	
	private void crawlAuthorPages(String rawSitePath, String authorSitePath, String folderPrefix)
	{	
		String[] folders = new File(rawSitePath).list();
		for(String folder : folders)
		{
			new File(authorSitePath+folder).mkdirs();
			if(folder.startsWith(folderPrefix))
			{
				String[] files = new File(rawSitePath+folder).list();
				for(String file : files)
				{
					String content = FileOpener.readFile(rawSitePath+folder+"/"+file, false);
					String[] hrefs = parseSearchSite(content);
					for(String href : hrefs)
					{
						String id = href.split("=")[1];
						crawlSearchUrl(baseUrl+href+"&sortby=pubdate&pagesize=100", authorSitePath+folder+"/"+id+".html");
					}
				}
			}
		}
	}

	private void parseAuthors(String authorSitePath,
			String authorParsedPath, String folderPrefix)
	{
		ScholarParser parser = new ScholarParser();
		Gson gson = new Gson();
		PrintWriter outFile = FileOpener.openWriteFile(authorParsedPath);
		
		String[] folders = new File(authorSitePath).list();
		for(String folder : folders)
		{
			if(folder.startsWith(folderPrefix))
			{
				String[] files = new File(authorSitePath+folder).list();
				for(String file : files)
				{
					System.out.println("processing = "+authorSitePath+folder+"/"+file);
					String content = FileOpener.readFile(authorSitePath+folder+"/"+file, false);
					ScholarAuthor author = parser.parseAuthorSite(content);
					author.setId(file.split("\\.")[0]);
					author.setCategory(folder);
					String json = gson.toJson(author);
					outFile.println(json);
				}
			}
		}
		
		outFile.close();
	}
	
	private void crawlSearchUrl(String baseUrl, String outputPath)
	{
		PrintWriter outFile = FileOpener.openWriteFile(outputPath);
		
		Document doc;
		try {
			doc = Jsoup.connect(baseUrl)
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.83 Safari/537.1")
					.cookie("auth", "token")
					  .timeout(10000)
					  .get();
			String content = doc.toString();
			outFile.println(content);
			outFile.close();
			Thread.sleep(2000+(long)(Math.random()*1000));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[] parseSearchSite(String content)
	{	
		Set<String> hrefSet = new HashSet<String>();
		
		int start = content.indexOf("/citations?user=", 0);
		while(start >= 0)
		{
			int end = content.indexOf("&", start);
			hrefSet.add(content.substring(start, end));
			
			start = content.indexOf("/citations?user=", start+1);
		}
		
		return hrefSet.toArray(new String[hrefSet.size()]);
	}
}
