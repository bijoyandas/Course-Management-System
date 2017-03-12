package com.College;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class Loading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	static JProgressBar progressBar;
	private JLabel label1;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Loading() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loading.class.getResource("/com/College/1481642427_college.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 320);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(this.getClass().getResource("load.jpg")));
		label.setBounds(0, 0, 598, 291);
		contentPane.add(label);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(32, 178, 170));
		progressBar.setBounds(-3, 306, 602, 20);
		contentPane.add(progressBar);
		
		label1 = new JLabel("");
		label1.setFont(new Font("Perpetua Titling MT", Font.BOLD, 12));
		label1.setBounds(0, 293, 596, 13);
		contentPane.add(label1);
		
		Thread t = new Thread() {
			 
            public void run() {
                int i = 0;
                while (i <= 100) {
                	if (i<10)
                		label1.setText("Loading Components....");
                	else if (i<30)
                		label1.setText("Initializing UI......");
                	else if (i<60)
                		label1.setText("Connecting Databases.....");
                	else if (i<80)
                		label1.setText("Almost Done..");
                	else
                		label1.setText("Welcome...");
                    progressBar.setValue(i);
                    try {
                        sleep(120);
                    } catch (InterruptedException ex) {
                        JOptionPane.showMessageDialog(null, "Cannot Load!");
                    }
                    i+=3;
                }
            }
        };
        t.start();
	}

}
