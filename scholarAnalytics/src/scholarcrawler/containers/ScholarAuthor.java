package scholarcrawler.containers;

import java.util.List;

public class ScholarAuthor
{
	String id;
	String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	String metadata;
	String name;
	String url;
	
	List<String> titles;
	public List<String> getTitles() {
		return titles;
	}
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public List<String> getVenues() {
		return venues;
	}
	public void setVenues(List<String> venues) {
		this.venues = venues;
	}
	public List<String> getTitleTerms() {
		return titleTerms;
	}
	public void setTitleTerms(List<String> titleTerms) {
		this.titleTerms = titleTerms;
	}
	public List<String> getAuthorTerms() {
		return authorTerms;
	}
	public void setAuthorTerms(List<String> authorTerms) {
		this.authorTerms = authorTerms;
	}
	public List<String> getVenueTerms() {
		return venueTerms;
	}
	public void setVenueTerms(List<String> venueTerms) {
		this.venueTerms = venueTerms;
	}
	List<String> authors;
	List<String> venues;
	
	List<String> titleTerms;
	List<String> authorTerms;
	List<String> venueTerms;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
