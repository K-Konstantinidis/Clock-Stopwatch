/*************************************************************************
	Copyright © 2021 Konstantinidis Konstantinos 
	Code was created with the help of: https://www.youtube.com/c/BroCodez

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at 

	      http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software 
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and 
	limitations under the License.
*************************************************************************/
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Clock_Stopwatch extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();//Use frame and not "this.", so you can change the title of the frame inside an ActionListener
		
	SimpleDateFormat timeformat;
	SimpleDateFormat dayformat;
	SimpleDateFormat dateformat;
		
	JLabel timelabel;
	JLabel daylabel;
	JLabel datelabel;
		
	String time;
	String day;
	String date;
		
	//Add an image
	Image image = new ImageIcon(this.getClass().getResource("/clock.png")).getImage();
		
	JButton stopwatch;// to go to stopwatch
	JButton clock;// to go to clock
		
	CardLayout layout = new CardLayout();
	JPanel deck = new JPanel();

	JPanel firstCard = new JPanel();
	JPanel secondCard = new JPanel();
		
	JButton startButton;
	JButton resetButton;
		
	JLabel timeLabel1;
		
	int elapsedTime = 0;
	int seconds = 0 ;
	int minutes = 0;
	int hours = 0;
		
	boolean started = false;

	String sec_string = String.format("%02d", seconds);
	String min_string = String.format("%02d", minutes);
	String hour_string = String.format("%02d", hours);
		
	public Clock_Stopwatch(){
		deck.setLayout(layout); //Set the layout to card layout
			
		deck.add(firstCard, "first"); //Add the cards
		deck.add(secondCard, "second");
			
		//hh => hours | mm => minutes | ss => seconds | a=>am/pm
		timeformat = new SimpleDateFormat("hh:mm:ss a");
			
		//E => simple day format, EEEE => To show the full name of the day
		dayformat = new SimpleDateFormat("EEEE");
			
		//dd => day | M => month, MMMMM => To show the full name of the month | yyyy => to show the 4 digits of the year
		dateformat = new SimpleDateFormat("dd MMMMM, yyyy");
		
		//Create new labels
		timelabel = new JLabel();
		timelabel.setFont(new Font("Verdana", Font.PLAIN, 50));
		timelabel.setForeground(new Color(0x00FF00));
			
		daylabel = new JLabel();
		daylabel.setFont(new Font("Garamond", Font.PLAIN, 35));
		daylabel.setForeground(new Color(0x00FF00));
			
		datelabel = new JLabel();
		datelabel.setFont(new Font("Garamond", Font.PLAIN, 35));
		datelabel.setForeground(new Color(0x00FF00));
			
		//Create the stopwatch button
		stopwatch = new JButton("STOPWATCH");
		stopwatch.setFont(new Font("Verdana", Font.PLAIN, 20));
		stopwatch.setForeground(Color.black);
		stopwatch.setBackground(new Color(0x00FF00));
		stopwatch.setOpaque(true);
		
		//Add labels and button to the 1st card 
		firstCard.add(timelabel);
		firstCard.add(daylabel);
		firstCard.add(datelabel);
		firstCard.add(stopwatch);

		firstCard.setLayout(new FlowLayout());
		firstCard.setBackground(Color.black);
			
		//Show the 1st card
		layout.show(deck, "first");
			
		timeLabel1 = new JLabel();
		startButton = new JButton("START");
		resetButton = new JButton("RESET");
		clock = new JButton("CLOCK");
			
		//A label to show the time on the clockwatch
		timeLabel1.setText(hour_string + ":" + min_string + ":" + sec_string);
		timeLabel1.setBounds(50, 10, 240, 60);
		timeLabel1.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel1.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel1.setOpaque(true);
		timeLabel1.setHorizontalAlignment(JTextField.CENTER);
				
		//The 2 buttons of the clockwatch
		startButton.setBounds(50, 80, 110, 60);
		startButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
				
		resetButton.setBounds(180, 80, 110, 60);
		resetButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
			
		//A button to return to the clock
		clock.setBounds(105, 150, 130, 60);
		clock.setFont(new Font("Verdana", Font.PLAIN, 20));
		clock.setFocusable(false);
				
		//Add label and buttons to the 2nd card 
		secondCard.add(timeLabel1);
		secondCard.add(startButton);
		secondCard.add(resetButton);
		secondCard.add(clock);
		secondCard.setLayout(new BorderLayout());
		secondCard.setBackground(Color.black);
			
		stopwatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("Stopwatch");
				layout.show(deck, "second");
			}
		});
			
		clock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setTitle("Clock");
				layout.show(deck, "first");
			}
		});

		frame.add(deck);
		frame.setTitle("Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setSize(340, 250);
		frame.setIconImage(image);
		frame.setVisible(true);
			
		setTime();
	}
		
	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			elapsedTime += 1000; //1000 milliseconds => a second || So it gains +1000 milliseconds every second
			hours = (elapsedTime/3600000); //an hour is 3600000 milliseconds
			minutes = (elapsedTime/60000) % 60; //a minute is 60000 milliseconds (%60 so it turns to hours after reaching 60)
			seconds = (elapsedTime/1000) % 60; //a second is 1000 milliseconds (%60 so it turns to minutes after reaching 60)
			
			sec_string = String.format("%02d", seconds);
			min_string = String.format("%02d", minutes);
			hour_string = String.format("%02d", hours);
			
			timeLabel1.setText(hour_string + ":" + min_string + ":" + sec_string);
		}
	});
		
	public void setTime() {
		while(true) {
			time = timeformat.format(Calendar.getInstance().getTime());
			timelabel.setText(time);
			
			day = dayformat.format(Calendar.getInstance().getTime());
			daylabel.setText(day);
			
			date = dateformat.format(Calendar.getInstance().getTime());
			datelabel.setText(date);
			
			try {
				Thread.sleep(1000);//Sleep for 1000 milliseconds => 1 second
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == startButton) {
			if(!started) {
				started = true;
				startButton.setText("STOP");
				start();
			}
			else {
				started = false;
				startButton.setText("START");
				stop();
			}
		}
		
		if(arg0.getSource() == resetButton) {
			started = false;
			startButton.setText("START");
			reset();
		}
	}

	private void start(){
		timer.start();
	}
		private void stop(){
		timer.stop();
	}
	
	private void reset(){
		timer.stop();
		
		elapsedTime = 0;
		seconds = 0 ;
		minutes = 0;
		hours = 0;
		sec_string = String.format("%02d", seconds);
		min_string = String.format("%02d", minutes);
		hour_string = String.format("%02d", hours);
		timeLabel1.setText(hour_string + ":" + min_string + ":" + sec_string);
	}
}
