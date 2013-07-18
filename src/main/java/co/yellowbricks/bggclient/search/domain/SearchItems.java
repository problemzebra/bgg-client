package co.yellowbricks.bggclient.search.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "items")
public class SearchItems {
	
	@XmlAttribute(name = "total")
	private int total;

	@XmlAttribute(name = "termsofuse")
	private String termsOfUseUrl;
	
	@XmlElement(name = "item")
	private List<SearchItem> items;
	
	public String getTermsOfUseUrl() {
		return termsOfUseUrl;
	}
	
	public int getTotal() {
		return total;
	}
	
	public List<SearchItem> getItems() {
		return items;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(total, termsOfUseUrl, items);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SearchItems) {
			SearchItems that = (SearchItems) obj;
			return Objects.equal(that.total, this.total) && Objects.equal(that.termsOfUseUrl, this.termsOfUseUrl) && Objects.equal(that.items, this.items);
		}
		return false;
	}
	
	public static class NullSearchedItems {
		
	}
}