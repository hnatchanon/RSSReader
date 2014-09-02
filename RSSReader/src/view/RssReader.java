package view;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Channel;
import model.Item;
import model.Rss;
public class RssReader {

	private Channel channel;
	private Unmarshaller unmarshaller;

	public RssReader(String url) throws JAXBException, MalformedURLException {
		JAXBContext ctx = JAXBContext.newInstance( Rss.class );
		unmarshaller = ctx.createUnmarshaller( );
		Object obj = unmarshaller.unmarshal( new URL(url) );
		this.channel = ((Rss) obj).getChannel();
	}

	public Channel getChannel() {
		return channel;
	}

	public String getTitle() {
		return channel.getTitle();
	}

	public List<Item> getItems() {
		return channel.getItems();
	}

	public void setUrl(String url) throws MalformedURLException, JAXBException {
		Object obj = unmarshaller.unmarshal( new URL(url) );
		this.channel = ((Rss) obj).getChannel();
	}
}
