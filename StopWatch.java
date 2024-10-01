package JavaProject4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch implements ActionListener{

	JFrame frame = new JFrame();
	JButton startButton = new JButton("Start");
	JButton resetButton = new JButton("Reset");
	
	JLabel timeLabel = new JLabel();
	
	int elapsedTime = 0;//Hold the amount of milliseconds after we start the time
	int seconds = 0;
	int min = 0;
	int hour = 0;
	boolean started = false; 
	
	String seconds_string = String.format("%02d", seconds);//shows 2 digits instead if 1, ex. 00, 01, 02, etc
	String min_string = String.format("%02d", min);
	String hour_string = String.format("%02d", hour);

	Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				elapsedTime = elapsedTime + 1000;//add 1 seconds to the clock
				
				hour = (elapsedTime/3600000);//3600000 milliseconds in a hour
				min = (elapsedTime/60000) % 60;//60000 milliseconds in a min (% 60 help determine if it pass 60 mins)
				seconds = (elapsedTime/1000) % 60;
				
				//update every min by declaring it here
				seconds_string = String.format("%02d", seconds);
				min_string = String.format("%02d", min);
				hour_string = String.format("%02d", hour);
				//----------------------------------------
				
				timeLabel.setText(hour_string + ":" + min_string + ":" + seconds_string);
				
			}
		
		}
	);
	
	public StopWatch() {
		// TODO Auto-generated constructor stub
		
		timeLabel.setText(hour_string + ":" + min_string + ":" + seconds_string);
		timeLabel.setBounds(100, 100, 200, 100);//x, y, width, height
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setForeground(Color.green);
		timeLabel.setBackground(Color.black);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		startButton.setBounds(100, 200, 100, 50);
		startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton.setBounds(200, 200, 100, 50);
		resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.getContentPane().setBackground(Color.cyan);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		
		frame.add(startButton);
		frame.add(resetButton);
		frame.add(timeLabel);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == startButton) {
			if(!started) {//! works differently with boolean, this basically say !started is true WHEN started = false, !started is false WHEN started is true
						  //so when started = false, !started (means that the variable is INDEED false) ( (!started) = -> (started = false;) )
						  //so when started = false, started(without !) (means that it is false) ( (started) != (started = false) )
						  //Basically (!started) and (started == false) is the same exact thing
				started = true;
				startButton.setText("Stop");
				startButton.setBackground(Color.red);
//				startButton.setBorder(BorderFactory.createRaisedBevelBorder());
//				startButton.setOpaque(true);
//				startButton.setFocusPainted(false);
				start();
			}
			else {
				started = false;
				startButton.setText("Start");
				startButton.setBackground(UIManager.getColor("Button.background"));//Set the button background back to default
				stop();
			}
		}
		
		if(e.getSource() == resetButton) {
			started = false;
			startButton.setText("Start");
			startButton.setBackground(UIManager.getColor("Button.background"));//Set the button background back to default
			reset();
		}
		
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}

	public void reset() {
		timer.stop();
		//timer.restart();//This doesn't work because it won't reset the timer because the code above is adding on 1 seconds each time so the clock won't reset
		
		//stop the timer, and below reset everything back to 0
		elapsedTime = 0;
		seconds = 0;
		min = 0;
		hour = 0;
		
		seconds_string = String.format("%02d", seconds);
		min_string = String.format("%02d", min);
		hour_string = String.format("%02d", hour);
		timeLabel.setText(hour_string + ":" + min_string + ":" + seconds_string);
		
	}
	
}
