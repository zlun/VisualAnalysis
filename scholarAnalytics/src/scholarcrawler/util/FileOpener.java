package scholarcrawler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileOpener
{
	public static BufferedReader openReadFile(String pathIn)
	{
		try
		{
			BufferedReader inFile = new BufferedReader(new FileReader(pathIn));
			return inFile;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static BufferedReader openReadFileUTF8(String pathIn)
	{
		try
		{
			BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream(pathIn), "UTF8"));
			return inFile;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String readFile(String pathIn, boolean breakLines)
	{
		BufferedReader inFile = openReadFile(pathIn);
		StringBuilder output = new StringBuilder();
		
		String line;
		try
		{
			while((line = inFile.readLine()) != null)
			{
				output.append(line);
				if(breakLines)
				{
					output.append("\n");
				}
			}
			inFile.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output.toString();
	}
	
	public static PrintWriter openWriteFile(String pathOut)
	{
		try
		{
			return new PrintWriter(new FileWriter(pathOut));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PrintWriter openWriteFileUTF8(String pathOut)
	{
		try
		{
			return new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(pathOut)),"UTF-8"));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean makeFolderIfNeeded(String path)
	{
		path = path+"aaa";
		String[] pathSplit = path.split("/");
		path = "";
		for(int i = 0; i < pathSplit.length-1; i++)
		{
			path += pathSplit[i]+"/";
		}
		
		File folder = new File(path);
		
		if(folder.exists())
		{
			return false;
		}
		else
		{
			if(!folder.mkdirs())
			{
				System.out.println("[ERROR] problem creating folders");
			}
			return true;
		}
	}
}
