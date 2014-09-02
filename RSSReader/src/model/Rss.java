package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * RSS class for rss reader
 * @author �Ѱ���
 *
 */
@XmlRootElement
public class Rss {

	private Channel channel;

	/**
	 * Gets channel.
	 * @return channel
	 */
	public Channel getChannel() {
		return channel;
	}
	/**
	 * Sets channel.
	 * @param channel channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
