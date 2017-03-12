package com.College;

import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.awt.event.ActionEvent;

import org.jfree.ui.RefineryUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

public class UInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection myCon = null;
	PreparedStatement mySmt = null;
	ResultSet myRs = null;
	static UInterface f1;
	JLabel lblTime ;
	JLabel lblNewLabel ;
	private Point initialClick;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UInterface frame = new UInterface();
					frame.setVisible(true);
					f1 = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UInterface() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UInterface.class.getResource("/com/College/favicon.png")));
		Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			myCon = MyConnection.getConnection();
		}
		catch(Exception e)
		{}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 423);
		setResizable(false);
		setLocationRelativeTo(null);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        try {
					myCon.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		setResizable(false);
		//setUndecorated(true);
		setTitle("Course Management System");
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(230, 230, 230));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnAddst = new JButton("Student");
		btnAddst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewStudent ns = new NewStudent();
				ns.setVisible(true);
			}
		});
		btnAddst.setBackground(new Color(0, 206, 209));
		btnAddst.setForeground(new Color(0, 0, 0));
		btnAddst.setFocusPainted(false);
		btnAddst.setBorderPainted(false);
		btnAddst.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnAddst.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnAddst.setBackground(Color.WHITE);
		        btnAddst.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnAddst.setBackground(new Color(0,206,209));
				btnAddst.setForeground(Color.BLACK);
		    }
		});
		btnAddst.setBounds(10, 64, 123, 56);
		contentPane.add(btnAddst);
		
		final JButton btnUpdatest = new JButton("Update");
		btnUpdatest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id;
				id = JOptionPane.showInputDialog("Enter the ID of the student");
				try {
				mySmt = myCon.prepareStatement("select * from College where ID=?");
				mySmt.setString(1, id);
				myRs = mySmt.executeQuery();
				if(myRs.next()) {
				NewStudent newObj = new NewStudent();
				newObj.setValues(myRs.getString("ID"), myRs.getString("Name"), myRs.getString("Stream"), myRs.getString("Year"), myRs.getString("Mobile"), myRs.getString("Course"), myRs.getString("Fees"), myRs.getString("Address"), myRs.getString("Status"), myRs.getString("Grade"),myRs.getString("Email"));
				newObj.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Student Not Found");
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Student not found!");
				}
			}
		});
		btnUpdatest.setForeground(Color.BLACK);
		btnUpdatest.setFocusPainted(false);

		btnUpdatest.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnUpdatest.setBorderPainted(false);
		btnUpdatest.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnUpdatest.setBackground(Color.WHITE);
		        btnUpdatest.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnUpdatest.setBackground(new Color(0,206,209));
		        btnUpdatest.setForeground(Color.BLACK);
		    }
		});
		btnUpdatest.setBackground(new Color(0,206,209));
		btnUpdatest.setBounds(10, 131, 123, 56);
		contentPane.add(btnUpdatest);
		
		final JButton btnDeletest = new JButton("Delete");
		btnDeletest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Enter the id of the Student : ");
				try {
				Integer.parseInt(id);
				mySmt = myCon.prepareStatement("delete from College where Id=?");
				mySmt.setString(1, id);
				mySmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Student Deleted!");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Student not found!");
				}
			}
		});
		btnDeletest.setForeground(Color.BLACK);
		btnDeletest.setBorderPainted(false);
		
		btnDeletest.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnDeletest.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnDeletest.setBackground(Color.WHITE);
		        btnDeletest.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnDeletest.setBackground(new Color(0,206,209));
		        btnDeletest.setForeground(Color.BLACK);
		    }
		});
		btnDeletest.setFocusPainted(false);
		btnDeletest.setBackground(new Color(0,206,209));
		btnDeletest.setBounds(10, 198, 123, 56);
		contentPane.add(btnDeletest);
		
		final JButton btnAddcr = new JButton("Course");
		btnAddcr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCourse newObj = new NewCourse();
				newObj.setVisible(true);
			}
		});
		btnAddcr.setForeground(Color.BLACK);
		btnAddcr.setBorderPainted(false);
		
		btnAddcr.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnAddcr.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnAddcr.setBackground(Color.WHITE);
		        btnAddcr.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnAddcr.setBackground(new Color(0,206,209));
		        btnAddcr.setForeground(Color.BLACK);
		    }
		});
		btnAddcr.setFocusPainted(false);
		btnAddcr.setBackground(new Color(0,206,209));
		btnAddcr.setBounds(143, 64, 123, 56);
		contentPane.add(btnAddcr);
		
		final JButton btnSearchst = new JButton("Search");
		btnSearchst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String id = JOptionPane.showInputDialog("Enter the Id of the student ");
				mySmt = myCon.prepareStatement("select * from College where Id=?");
				mySmt.setString(1, id);
				myRs = mySmt.executeQuery();
				if (myRs.next())
				{
					NewStudent newObj = new NewStudent();
					newObj.setVal2(myRs.getString("ID"), myRs.getString("Name"), myRs.getString("Stream"), myRs.getString("Year"), myRs.getString("Mobile"), myRs.getString("Course"), myRs.getString("Fees"), myRs.getString("Address"), myRs.getString("Status"), myRs.getString("Grade"),myRs.getString("Email"));
					newObj.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Student does not exist!");
			}
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null, "Can't Search!");
				}
			}
		});
		btnSearchst.setForeground(Color.BLACK);
		btnSearchst.setBorderPainted(false);
		
		btnSearchst.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnSearchst.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnSearchst.setBackground(Color.WHITE);
		        btnSearchst.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnSearchst.setBackground(new Color(0,206,209));
		        btnSearchst.setForeground(Color.BLACK);
		    }
		});
		btnSearchst.setFocusPainted(false);
		btnSearchst.setBackground(new Color(0,206,209));
		btnSearchst.setBounds(10, 265, 123, 56);
		contentPane.add(btnSearchst);
		
		final JButton btnViewall = new JButton("List");
		btnViewall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List lObj = new List();
				lObj.setVisible(true);
			}
		});
		btnViewall.setForeground(Color.BLACK);
		btnViewall.setBorderPainted(false);
		btnViewall.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnViewall.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnViewall.setBackground(Color.WHITE);
		        btnViewall.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnViewall.setBackground(new Color(0,206,209));
		        btnViewall.setForeground(Color.BLACK);
		    }
		});
		btnViewall.setFocusPainted(false);
		btnViewall.setBackground(new Color(0,206,209));
		btnViewall.setBounds(143, 265, 123, 57);
		contentPane.add(btnViewall);
		
		final JButton btnSendemail = new JButton("Email");
		btnSendemail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Email em = new Email();
				em.setVisible(true);
			}
		});
		btnSendemail.setForeground(Color.BLACK);
		btnSendemail.setBorderPainted(false);
		btnSendemail.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnSendemail.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnSendemail.setBackground(Color.WHITE);
		        btnSendemail.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnSendemail.setBackground(new Color(0,206,209));
		        btnSendemail.setForeground(Color.BLACK);
		    }
		});
		btnSendemail.setFocusPainted(false);
		btnSendemail.setBackground(new Color(0,206,209));
		btnSendemail.setBounds(143, 333, 123, 53);
		contentPane.add(btnSendemail);
		
		final JButton btnUpdatecr = new JButton("Details");
		btnUpdatecr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String id = JOptionPane.showInputDialog("Enter the Course Id ");
				mySmt = myCon.prepareStatement("select * from Course where id=?");
				mySmt.setString(1, id);
				myRs = mySmt.executeQuery();
				if (myRs.next()) {
				NewCourse newObj = new NewCourse();
				newObj.setVal(myRs.getString("Id"),myRs.getString("Name"),myRs.getString("Content"),myRs.getString("Fees"));
				newObj.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Course not Found!");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Course not Found!");
				}
			}
		});
		btnUpdatecr.setForeground(Color.BLACK);
		btnUpdatecr.setBorderPainted(false);
		btnUpdatecr.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnUpdatecr.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnUpdatecr.setBackground(Color.WHITE);
		        btnUpdatecr.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnUpdatecr.setBackground(new Color(0,206,209));
		        btnUpdatecr.setForeground(Color.BLACK);
		    }
		});
		btnUpdatecr.setFocusPainted(false);
		btnUpdatecr.setBackground(new Color(0,206,209));
		btnUpdatecr.setBounds(143, 131, 123, 56);
		contentPane.add(btnUpdatecr);
		
		final JButton btnUpload = new JButton("Upload");
		  btnUpload.setBorderPainted(false);
	        btnUpload.setFocusPainted(false);
		btnUpload.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnUpload.setBackground(Color.WHITE);
	        btnUpload.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnUpload.setBackground(new Color(0,206,209));
	        btnUpload.setForeground(Color.BLACK);
	    }
	});
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Upload upObj = new Upload();
				upObj.setVisible(true);
			}
		});
		btnUpload.setForeground(Color.BLACK);
		btnUpload.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnUpload.setFocusPainted(false);
		btnUpload.setBackground(new Color(0,206,209));
		btnUpload.setBounds(143, 198, 123, 56);
		contentPane.add(btnUpload);
		
		JLabel pic = new JLabel("");
		pic.setBounds(280, 63, 458, 322);
		pic.setIcon(new ImageIcon(this.getClass().getResource("college.png")));
		contentPane.add(pic);
		
		final JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OpenMain openObj = new OpenMain();
				openObj.visible();
				dispose();
			}
		});
		btnLogOut.setForeground(Color.BLACK);
		btnLogOut.setFocusPainted(false);
		btnLogOut.setBorderPainted(false);
		btnLogOut.setBackground(new Color(0, 206, 209));
		btnLogOut.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 13));
		btnLogOut.setBounds(626, 27, 113, 32);
		btnLogOut.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnLogOut.setBackground(Color.WHITE);
	        btnLogOut.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnLogOut.setBackground(new Color(0,206,209));
	        btnLogOut.setForeground(Color.BLACK);
	    }
	});
		contentPane.add(btnLogOut);
		
		final JButton btnChart = new JButton("Chart");
		btnChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			      Chart cObj;
				try {
					cObj = new Chart("Course Map", "Course Chart");
					 cObj.pack( );        
		    	      RefineryUtilities.centerFrameOnScreen(cObj);        
		    	      cObj.setVisible( true ); 
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Cannot display chart!");
				}
			}
		});
		btnChart.setBackground(new Color(0, 206, 209));
		btnChart.setForeground(Color.BLACK);
		btnChart.setFocusPainted(false);
		btnChart.setBorderPainted(false);
		btnChart.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnChart.setBackground(Color.WHITE);
	        btnChart.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnChart.setBackground(new Color(0,206,209));
	        btnChart.setForeground(Color.BLACK);
	    }
	});
		btnChart.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnChart.setBounds(10, 333, 123, 53);
		contentPane.add(btnChart);
		
		lblNewLabel = new JLabel("Welcome, Admin");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 33, 208, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Time :");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(228, 30, 63, 23);
		contentPane.add(lblNewLabel_1);
		
		lblTime = new JLabel("Time");
		lblTime.setForeground(new Color(0, 0, 0));
		lblTime.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		lblTime.setBounds(291, 31, 223, 22);
		contentPane.add(lblTime);
		
		final JButton button = new JButton("-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setState(Frame.ICONIFIED);
			}
		});
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(0, 206, 209));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setFont(new Font("Tahoma", Font.PLAIN, 38));
		button.setBounds(568, 27, 49, 32);
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        button.setBackground(Color.WHITE);
	        button.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        button.setBackground(new Color(0,206,209));
	        button.setForeground(Color.BLACK);
	    }
	});
		contentPane.add(button);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(0, 206, 209));
		menuBar.setBounds(0, 0, 747, 23);
		contentPane.add(menuBar);
		menuBar.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	           
	        }
	    });
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            // get location of Window
	            int thisX = UInterface.this.getLocation().x;
	            int thisY = UInterface.this.getLocation().y;

	            // Determine how much the mouse moved since the initial click
	            int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
	            int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

	            // Move window to this position
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            UInterface.this.setLocation(X, Y);
	        }
	    });
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnFile.setBackground(new Color(0, 206, 209));
		mnFile.setForeground(Color.BLACK);
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmLogOut.setBackground(new Color(0, 206, 209));
		mntmLogOut.setForeground(Color.BLACK);
		mnFile.add(mntmLogOut);
		mntmLogOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OpenMain openObj = new OpenMain();
				openObj.visible();
				dispose();
			}
		});
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmExit.setBackground(new Color(0, 206, 209));
		mntmExit.setForeground(Color.BLACK);
		mnFile.add(mntmExit);
		
		JMenu mnAccounts = new JMenu("Accounts");
		mnAccounts.setForeground(Color.BLACK);
		mnAccounts.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnAccounts);
		
		JMenuItem mntmNewUser = new JMenuItem("New User");
		mntmNewUser.setForeground(Color.BLACK);
		mntmNewUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAccounts.add(mntmNewUser);
		
		mntmNewUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				NewUser nObj = new NewUser();
				nObj.setVisible(true);
				nObj.btnUpdate.setEnabled(false);
			}
		});
		
		JMenuItem mntmEditUser = new JMenuItem("Edit User");
		mntmEditUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAccounts.add(mntmEditUser);
		mntmEditUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String uname = JOptionPane.showInputDialog("Enter the Username : ");
				if (uname != null){
					try {
						mySmt = myCon.prepareStatement("select * from AdminUser where Username=?");
						mySmt.setString(1, uname);
						myRs = mySmt.executeQuery();
						while(myRs.next()){
							if (myRs.getString("Username").equals(uname)){
								NewUser nObj = new NewUser();
								nObj.setVisible(true);
								nObj.forUpdate(myRs.getString("Name"), myRs.getString("Username"), myRs.getString("Password"), myRs.getString("Designation"));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JMenuItem mntmDeleteUser = new JMenuItem("Delete User");
		mntmDeleteUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAccounts.add(mntmDeleteUser);
		mntmDeleteUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String uname = JOptionPane.showInputDialog("Enter the Username ");
				try {
					mySmt = myCon.prepareStatement("delete from AdminUser where Username=?");
					mySmt.setString(1,uname);
					int i = mySmt.executeUpdate();
					if (i>0)
					JOptionPane.showMessageDialog(null, "User Deleted!");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Cannot Delete User!");
				}
			}
		});
		
		JMenu mnView = new JMenu("View");
		mnView.setForeground(Color.BLACK);
		mnView.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnView);
		
		JMenuItem mntmAllStudents = new JMenuItem("All Students");
		mntmAllStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List lObj = new List();
				lObj.setVisible(true);
			}
		});
		mntmAllStudents.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnView.add(mntmAllStudents);
		
		JMenuItem mntmChart = new JMenuItem("Chart");
		mntmChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					      Chart cObj;
						try {
							cObj = new Chart("Course Map", "Course Chart");
							 cObj.pack( );        
				    	      RefineryUtilities.centerFrameOnScreen(cObj);        
				    	      cObj.setVisible( true ); 
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Cannot display chart!");
						}
					}
		});
		mntmChart.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnView.add(mntmChart);
		
		JMenu mnTools = new JMenu("Tools");
		mnTools.setForeground(new Color(0, 0, 0));
		mnTools.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnTools);
		
		JMenuItem mntmCalculator = new JMenuItem("Calculator");
		mntmCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmCalculator.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTools.add(mntmCalculator);
		
		JMenuItem mntmNotepad = new JMenuItem("Notepad");
		mntmNotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("notepad");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmNotepad.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnTools.add(mntmNotepad);
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	public void tickTock() {
        lblTime.setText(DateFormat.getDateTimeInstance().format(new java.util.Date()));
    }
	public void welcome(String message){
		lblNewLabel.setText(message);
	}
}
