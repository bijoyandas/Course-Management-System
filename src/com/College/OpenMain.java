package com.College;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class OpenMain {

	private JFrame frame;
	private Point initialClick;
	private JTextField username;
	private JPasswordField password;
	Connection myCon=null;
	PreparedStatement mySmt = null;
	ResultSet myRs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					Loading l = new Loading();
					l.setVisible(true);
					Thread.sleep(4000);
					l.dispose();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						OpenMain window = new OpenMain();
						window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpenMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(OpenMain.class.getResource("/com/College/favicon.png")));
		frame.getContentPane().setBackground(new Color(32, 178, 170));
		frame.setBounds(100, 100, 599, 383);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	        }
	    });
		frame.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            // get location of Window
	            int thisX = frame.getLocation().x;
	            int thisY = frame.getLocation().y;
	            
	            // Determine how much the mouse moved since the initial click
	            int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
	            int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

	            // Move window to this position
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            frame.setLocation(X, Y);
	        }
	    });
		
		JLabel pic = new JLabel("");
		pic.setBounds(10, 107, 134, 165);
		pic.setIcon(new ImageIcon(this.getClass().getResource("compu.png")));
		frame.getContentPane().add(pic);
		
		JLabel lblCourseManagementSystem = new JLabel("COURSE MANAGEMENT SYSTEM");
		lblCourseManagementSystem.setForeground(new Color(255, 255, 255));
		lblCourseManagementSystem.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
		lblCourseManagementSystem.setBounds(176, 36, 354, 30);
		frame.getContentPane().add(lblCourseManagementSystem);
		
		JLabel lblUsername = new JLabel("Username ");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblUsername.setBounds(176, 160, 115, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Perpetua Titling MT", Font.BOLD, 15));
		lblPassword.setBounds(176, 204, 115, 19);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		username.setBounds(302, 151, 162, 25);
		username.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		
		password = new JPasswordField();
		password.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		password.setBounds(302, 198, 162, 25);
		password.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		frame.getContentPane().add(password);
		
		final JButton loginbtn = new JButton("Login");
		loginbtn.setFocusPainted(false);
		loginbtn.setBorderPainted(false);
		loginbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        loginbtn.setBackground(Color.WHITE);
		        loginbtn.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        loginbtn.setBackground(new Color(220,20,60));
		        loginbtn.setForeground(Color.WHITE);
		    }
		});
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = username.getText();
				String pword = new String(password.getPassword());
				try {
					myCon = MyConnection.getConnection();
					mySmt = myCon.prepareStatement("select * from AdminUser where Username=? and Password=?");
					mySmt.setString(1, uname);
					mySmt.setString(2, pword);
					myRs = mySmt.executeQuery();
					if(myRs.next()) {
						UInterface ui = new UInterface();
						ui.setVisible(true);
						ui.welcome("Welcome, "+myRs.getString("Name"));
						frame.dispose();
					}
					else {
						password.setText("");
						JOptionPane.showMessageDialog(null, "Wrong Username or Password!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e){}
				
			}
		});
		loginbtn.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		loginbtn.setForeground(new Color(255, 255, 255));
		loginbtn.setBackground(new Color(220,20,60));
		loginbtn.setBounds(246, 268, 114, 46);
		frame.getContentPane().add(loginbtn);
		
		final JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
		});
		btnX.setBackground(new Color(220, 20, 60));
		btnX.setFocusPainted(false);
		btnX.setBorderPainted(false);
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 8));
		btnX.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnX.setBackground(Color.WHITE);
		        btnX.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnX.setBackground(new Color(220,20,60));
		        btnX.setForeground(Color.WHITE);
		    }
		});
		btnX.setBounds(559, 0, 40, 30);
		frame.getContentPane().add(btnX);
		
		final JButton btnMin = new JButton("-");
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		btnMin.setForeground(new Color(255, 255, 255));
		btnMin.setBackground(new Color(220, 20, 60));
		btnMin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnMin.setBounds(516, 0, 42, 30);
		btnMin.setFocusPainted(false);
		btnMin.setBorderPainted(false);
		frame.getContentPane().add(btnMin);
		btnMin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnMin.setBackground(Color.WHITE);
		        btnMin.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnMin.setBackground(new Color(220,20,60));
		        btnMin.setForeground(Color.WHITE);
		    }
		});
		username.requestFocusInWindow();
	
	}
	public void visible()
	{
		frame.setVisible(true);
	}
}
