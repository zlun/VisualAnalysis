package scholarcrawler.containers;

import java.util.List;

public class SearchSiteParsed
{
	List<ScholarAuthor> authors;
	String nextUrl;
	
	public String getNextUrl() {
		return nextUrl;
	}
	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}
	public List<ScholarAuthor> getAuthors() {
		return authors;
	}
	public void setAuthors(List<ScholarAuthor> authors) {
		this.authors = authors;
	}
}
