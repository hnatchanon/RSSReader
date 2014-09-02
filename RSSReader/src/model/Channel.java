package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Chanel class for rss reader
 * @author Natchanon Hongladaromp 5510546034
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

	private String title;
	private String description;
	@XmlElement(name = "item")
	private List<Item> items;

	/**
	 * Gets title.
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets title.
	 * @param title title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * Gets description.
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets description.
	 * @param description description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Gets list of items.
	 * @return list of items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * Sets list of items.
	 * @param items list of items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
