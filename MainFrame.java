package weathergui2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainFrame extends JFrame {
	public MainFrame(String title) {
		super(title);
		
		GridLayout gl = new GridLayout(3,1);
		
		setLayout(gl);
		
		//top panel, search area + button
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JTextField text_field = new JTextField();
		JButton search_button = new JButton("Search");
		panel.add(text_field);
		panel.add(search_button);
		
		//temp label info
		JLabel temp_label = new JLabel("-- F");
		temp_label.setFont(temp_label.getFont().deriveFont(64.0f));
		JLabel text_label = new JLabel("Current Temparature");
		text_label.setFont(text_label.getFont().deriveFont(40.0f));
		
		Container c = getContentPane();
		
		c.add(panel);
		c.add(temp_label);
		c.add(text_label);
		
		search_button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String location = text_field.getText();
				String[] location_info = location.split(" ");
				String city = location_info[0];
				String state = location_info[1];
				try {
					Document doc = Jsoup.connect("https://www.wunderground.com/weather/us/" + state + "/" + city).userAgent("mozilla/17.0").get();
					Elements temp = doc.select("div.current-temp");
					String current_temp = temp.text();
					temp_label.setText(current_temp);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

}