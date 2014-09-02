package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Item class for rss reader.
 * @author Natchanon Hongladaromp 5510546034
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	private String title;
	private String description;
	private String link;

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
	 * Gets link
	 * @return link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * Sets link
	 * @param link link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return title;
	}
}
