package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rss {
	
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
