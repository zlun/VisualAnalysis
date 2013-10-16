package scholarcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import scholarcrawler.containers.ScholarAuthor;
import scholarcrawler.util.FileOpener;

public class ScholarParser
{
	public static void main(String[] args)
	{
		ScholarParser parser = new ScholarParser();
		String authorParsedPath = "/Users/lipczak/data/scholar/author-stanford.json";
		parser.featureStats(authorParsedPath);
	}
	
	public ScholarAuthor parseAuthorSite(String content)
	{
		System.out.println("Processing author size = "+content.length());
		
		String titlePrefix = "class=\"cit-dark-large-link\">";
		String spanPrefix = "<span class=\"cit-gray\">";
		
		ScholarAuthor author = new ScholarAuthor();
		int start = content.indexOf("<title>", 0);
		int end = content.indexOf(" - ", start);
		author.setName(content.substring(start+"<title>".length(), end));
		
		List<String> titles = new ArrayList<String>();
		List<String> authors = new ArrayList<String>();
		List<String> venues = new ArrayList<String>();
		
		//<a class="cit-dark-large-link" href="/citations?view_op=search_authors&amp;hl=en&amp;mauthors=label:data_mining">Data Mining</a>
		//class="cit-dark-large-link">Automated authorship attribution with character level language models</a>
		
		start = content.indexOf(titlePrefix, 0);
		while(start >= 0)
		{
			int recordEnd = content.indexOf("</td>", start);
			end = content.indexOf("</a>", start);
			if(end < recordEnd)
			{
				titles.add(content.substring(start+titlePrefix.length(), end));
			}
			start = content.indexOf(spanPrefix, end);
			if(start < 0)
			{
				break;
			}
			end = content.indexOf("</span>", start);
			if(end < recordEnd)
			{
				authors.add(content.substring(start+spanPrefix.length(), end));
			}
			else
			{
				start = content.indexOf(titlePrefix, recordEnd);
				continue;
			}
			start = content.indexOf(spanPrefix, end);
			if(start < 0)
			{
				break;
			}
			end = content.indexOf("</span>", start);
			if(end < recordEnd)
			{
				venues.add(content.substring(start+spanPrefix.length(), end));
			}
			else
			{
				start = content.indexOf(titlePrefix, recordEnd);
				continue;
			}
			
			start = content.indexOf(titlePrefix, end);
		}
		
		author.setAuthors(authors);
		author.setTitles(titles);
		author.setVenues(venues);
		extractTerms(author);
		return author;
	}

	public void extractTerms(ScholarAuthor author)
	{
		author.setTitleTerms(extractTerms(author.getTitles(), "title"));
		author.setAuthorTerms(extractTerms(author.getAuthors(), "author"));
		author.setVenueTerms(extractTerms(author.getVenues(), "venue"));
	}

	private List<String> extractTerms(List<String> snippets, String type)
	{
		Map<String, Double> termMap = new HashMap<String, Double>();
		
		for(String snippet : snippets)
		{
			snippet = snippet.toLowerCase().replaceAll("[^a-z\\s]", "");
			String[] termSplit = snippet.split("\\s+");
			for(String term : termSplit)
			{
				Double score = termMap.get(term);
				termMap.put(term, score == null ? new Double(1.0) : score+1);
			}
		}
		
		List<Entry<String, Double>> termEntries = new ArrayList<Entry<String, Double>>();
		for(Entry<String, Double> termEntry : termMap.entrySet())
		{
			termEntries.add(termEntry);
		}
		
		Collections.sort(termEntries, new EntryComparator());
		
		List<String> terms = new ArrayList<String>();
		for(Entry<String, Double> termEntry : termEntries)
		{
			terms.add(termEntry.getKey()+":"+termEntry.getValue());
		}
		
		return terms;
	}
	
	private void featureStats(String pathIn)
	{
		BufferedReader inFile = FileOpener.openReadFile(pathIn);
		Gson gson = new Gson();
		
		Map<String, Double> titleTerms = new HashMap<String, Double>();
		Map<String, Double> authorTerms = new HashMap<String, Double>();
		Map<String, Double> venueTerms = new HashMap<String, Double>();
		
		String line;
		
		try
		{
			while((line = inFile.readLine()) != null)
			{
				ScholarAuthor author = gson.fromJson(line, ScholarAuthor.class);
				
				for(String titleTerm : author.getTitleTerms())
				{
					String key = titleTerm.split(":")[0];
					Double value = titleTerms.get(key);
					titleTerms.put(key, value == null ? new Double(1.0) : value+1);
				}
				
				for(String coauthor : author.getAuthorTerms())
				{
					String key = coauthor.split(":")[0];
					Double value = authorTerms.get(key);
					authorTerms.put(key, value == null ? new Double(1.0) : value+1);
				}
				
				for(String venueTerm : author.getVenueTerms())
				{
					String key = venueTerm.split(":")[0];
					Double value = venueTerms.get(key);
					venueTerms.put(key, value == null ? new Double(1.0) : value+1);
				}
			}
			
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printDistribution(pathIn+"author.tsv", authorTerms);
		printDistribution(pathIn+"title.tsv", titleTerms);
		printDistribution(pathIn+"venue.tsv", venueTerms);
	}

	private void printDistribution(String pathOut,
			Map<String, Double> termMap)
	{
		PrintWriter outFile = FileOpener.openWriteFile(pathOut);
		
		List<Entry<String, Double>> termEntries = new ArrayList<Entry<String, Double>>();
		for(Entry<String, Double> termEntry : termMap.entrySet())
		{
			termEntries.add(termEntry);
		}
		
		Collections.sort(termEntries, new EntryComparator());
		
		for(Entry<String, Double> termEntry : termEntries)
		{
			outFile.println(termEntry.getKey()+"\t"+termEntry.getValue());
		}
		
		outFile.close();
	}
}

class EntryComparator implements Comparator<Entry<String, Double>>
{
	@Override
	public int compare(Entry<String, Double> o1, Entry<String, Double> o2)
	{
		if(o2.getValue() > o1.getValue())
		{
			return 1;
		}
		if(o2.getValue() < o1.getValue())
		{
			return -1;
		}
		return 0;
	}
}
