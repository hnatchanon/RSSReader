package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBException;

import model.Item;

import view.RssReader;

/**
 * This is gui for rss reader and main class for this program.
 * @author Natchanon Hongladaromp 5510546034
 *
 */
public class RssReaderGui extends JFrame implements Runnable {

	private static final int UPDATE_DISCRIPTION = 0;
	private static final int UPDATE_URL = 1;

	private JTextField url;
	private RssReader rssReader;
	private JTextPane desArea;
	private Item item;
	private JList titleList;
	private JTextPane link;
	private JButton moreInfo;

	/**
	 * Constructer for this class.
	 */
	public RssReaderGui() {
		init();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * initialize method.
	 */
	private void init() {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(new EmptyBorder(10, 10, 10, 10));

		JPanel head = new JPanel(new FlowLayout());
		head.setBorder(BorderFactory.createLoweredBevelBorder());

		head.add(new JLabel("RSS URL: "));

		url = new JTextField(30);
		url.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					submitUrl();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		head.add(url);

		JButton button = new JButton("Read");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitUrl();
			}
		});
		head.add(button);
		pane.add(head);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));
		body.setBorder(BorderFactory.createLoweredBevelBorder());

		titleList = new JList();
		titleList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				item = (Item)(titleList.getSelectedValue());
				update(UPDATE_DISCRIPTION);
			}
		});

		JScrollPane scroll1 = new JScrollPane(titleList);
		scroll1.setPreferredSize(new Dimension(250, 300));
		scroll1.setAutoscrolls(true);
		body.add(scroll1);

		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

		desArea = new JTextPane();
		desArea.setPreferredSize(new Dimension(400, 0));
		desArea.setContentType("text/html");
		desArea.setEditable(false);
		JScrollPane scroll2 = new JScrollPane(desArea);
		scroll2.setPreferredSize(new Dimension(500, 250));
		scroll2.setAutoscrolls(true);
		left.add(scroll2);

		moreInfo = new JButton("More Information");
		moreInfo.setAlignmentX( Component.LEFT_ALIGNMENT );
		moreInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				URL link;
				try {
					link = new URL(item.getLink());
					open(link);
				} catch (MalformedURLException | URISyntaxException e) {
					JOptionPane.showMessageDialog(null, "Link Error!", "Link Error!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		moreInfo.setEnabled(false);
		left.add(moreInfo);

		body.add(left);
		pane.add(body);
		this.add(pane);
	}

	/**
	 * To submit url.
	 */
	private void submitUrl() {
		try {
			rssReader = new RssReader(url.getText());
			update(UPDATE_URL);
		} catch (MalformedURLException | JAXBException e1) {
			JOptionPane.showMessageDialog(null, "Wrong URL!", e1.toString(), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void run() {
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Updates this gui.
	 * @param type
	 */
	public void update(int type) {
		if(type == UPDATE_DISCRIPTION) {
			desArea.setText(item.getDescription());
			moreInfo.setEnabled(true);
		}
		if(type == UPDATE_URL) {
			titleList.revalidate();
			titleList.repaint();
			List<Item> tmpItem = rssReader.getChannel().getItems();
			Object[] array = new Object[tmpItem.size()];
			for(int i=0; i<tmpItem.size(); i++) {
				array[i] = tmpItem.get(i);
			}
			titleList.setListData(array);
		}

	}

	/**
	 * Opens url in browser.
	 * @param url url to open
	 * @throws URISyntaxException
	 */
	private static void open(URL url) throws URISyntaxException {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Browser Error!", "Browser Error!", JOptionPane.ERROR_MESSAGE);
			}  
		} 
		else {
			JOptionPane.showMessageDialog(null, "Windows is not support.", "Windows is not support.", JOptionPane.ERROR_MESSAGE);
		}
	}
}
