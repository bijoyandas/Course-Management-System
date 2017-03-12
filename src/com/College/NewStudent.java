package com.College;

import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;

public class NewStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<String> tfStatus;
	private JTextField tfname;
	private JTextField tfStream;
	JComboBox<String> tfCourse;
	private JTextField tfYear;
	private JTextField tfMobile;
	private JTextField tfFees;
	private JTextField tfGrade;
	java.sql.Connection myCon=null;
	PreparedStatement mySmt = null;
	ResultSet myRs = null;
	private JTextField tfId;
	final JTextArea taAddress;
	final JButton btnRegister;
	final JButton btnUpdate;
	private JScrollPane scrollPane;
	private JLabel lblEmail;
	private JTextField tfEmail;
	ResultSet myRs2 = null;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public NewStudent() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewStudent.class.getResource("/com/College/1481642427_college.png")));
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			myCon = MyConnection.getConnection();
		}
		catch(Exception e) {e.printStackTrace();}
		setType(javax.swing.JFrame.Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 552);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setTitle("New Student");
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pic = new JLabel("");
		pic.setBounds(311, 11, 199, 199);
		pic.setIcon(new ImageIcon(this.getClass().getResource("student.png")));
		contentPane.add(pic);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblName.setBounds(10, 21, 76, 25);
		contentPane.add(lblName);
		
		tfname = new JTextField();
		tfname.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfname.setBounds(106, 18, 171, 25);
		contentPane.add(tfname);
		tfname.setColumns(10);
		
		JLabel lblStream = new JLabel("STREAM");
		lblStream.setForeground(Color.BLACK);
		lblStream.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblStream.setBounds(10, 65, 88, 20);
		contentPane.add(lblStream);
		
		tfStream = new JTextField();
		tfStream.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfStream.setColumns(10);
		tfStream.setBounds(106, 60, 171, 25);
		contentPane.add(tfStream);
		
		JLabel lblYear = new JLabel("YEAR");
		lblYear.setForeground(Color.BLACK);
		lblYear.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblYear.setBounds(10, 105, 88, 20);
		contentPane.add(lblYear);
		
		tfYear = new JTextField();
		tfYear.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfYear.setColumns(10);
		tfYear.setBounds(106, 100, 171, 25);
		contentPane.add(tfYear);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setForeground(Color.BLACK);
		lblMobile.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblMobile.setBounds(10, 147, 88, 20);
		contentPane.add(lblMobile);
		
		tfMobile = new JTextField();
		tfMobile.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfMobile.setColumns(10);
		tfMobile.setBounds(106, 142, 171, 25);
		contentPane.add(tfMobile);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setForeground(Color.BLACK);
		lblCourse.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblCourse.setBounds(10, 190, 88, 20);
		contentPane.add(lblCourse);
		
		JLabel lblFees = new JLabel("Fees");
		lblFees.setForeground(Color.BLACK);
		lblFees.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblFees.setBounds(10, 231, 88, 20);
		contentPane.add(lblFees);
		
		tfFees = new JTextField();
		tfFees.setEditable(false);
		tfFees.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfFees.setColumns(10);
		tfFees.setBounds(106, 226, 171, 25);
		contentPane.add(tfFees);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblAddress.setBounds(10, 274, 88, 20);
		contentPane.add(lblAddress);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 268, 404, 70);
		contentPane.add(scrollPane);
		
		taAddress = new JTextArea();
		scrollPane.setViewportView(taAddress);
		taAddress.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		taAddress.setLineWrap(true);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblStatus.setBounds(10, 362, 88, 20);
		contentPane.add(lblStatus);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setForeground(Color.BLACK);
		lblGrade.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblGrade.setBounds(9, 405, 88, 20);
		contentPane.add(lblGrade);
		
		tfGrade = new JTextField();
		tfGrade.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfGrade.setColumns(10);
		tfGrade.setBounds(106, 398, 171, 25);
		contentPane.add(tfGrade);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Double.parseDouble(tfMobile.getText());
				mySmt = myCon.prepareStatement("insert into College values(?,?,?,?,?,?,?,?,?,?,?)");
				mySmt.setString(1, tfname.getText());
				mySmt.setString(2, tfStream.getText());
				mySmt.setString(3, tfYear.getText());
				mySmt.setString(4, tfId.getText());
				mySmt.setString(5, tfMobile.getText());
				mySmt.setString(6, tfCourse.getSelectedItem().toString());
				mySmt.setString(7, tfFees.getText());
				mySmt.setString(8, taAddress.getText().toString());
				mySmt.setString(9, tfStatus.getSelectedItem().toString());
				mySmt.setString(10, tfGrade.getText());
				mySmt.setString(11, tfEmail.getText());
				mySmt.executeUpdate();
				mySmt = myCon.prepareStatement("select * from Course");
				myRs = mySmt.executeQuery();
				while(myRs.next())
				{
					if (tfCourse.getSelectedItem().toString().equals(myRs.getString("Name"))) {
						String id = myRs.getString("Id");
						int i = Integer.parseInt(myRs.getString("Students"));
						i++;
						mySmt = myCon.prepareStatement("update Course set Students=? where Id=?");
						mySmt.setString(1, i+"");
						mySmt.setString(2, id);
						mySmt.executeUpdate();
						break;
					}
				}
				
				JOptionPane.showMessageDialog(null, "Student Added!");
				dispose();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Cannot Register!");
				}
			}
		});
		btnRegister.setBackground(new Color(220,20,60));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnRegister.setFocusPainted(false);
		btnRegister.setBorderPainted(false);
		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnRegister.setBackground(Color.WHITE);
	        btnRegister.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnRegister.setBackground(new Color(220,20,60));
	        btnRegister.setForeground(Color.WHITE);
	    }
	});
		btnRegister.setBounds(332, 359, 138, 53);
		contentPane.add(btnRegister);
		
		JLabel lblId = new JLabel("STID");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblId.setBounds(10, 443, 76, 26);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfId.setBounds(106, 442, 171, 24);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(tfMobile.getText());
				mySmt = myCon.prepareStatement("update College set Name=?,Stream=?,Year=?,Mobile=?,Course=?,Fees=?,Address=?,Status=?,Grade=?,Email=? where ID=?");
				mySmt.setString(1, tfname.getText());
				mySmt.setString(2, tfStream.getText());
				mySmt.setString(3, tfYear.getText());
				mySmt.setString(4, tfMobile.getText());
				mySmt.setString(5, tfCourse.getSelectedItem().toString());
				mySmt.setString(6, tfFees.getText());
				mySmt.setString(7, taAddress.getText().toString());
				mySmt.setString(8, tfStatus.getSelectedItem().toString());
				mySmt.setString(9, tfGrade.getText());
				mySmt.setString(10, tfEmail.getText());
				mySmt.setString(11, tfId.getText());
				mySmt.executeUpdate();
				mySmt = myCon.prepareStatement("select * from Course where Name=?");
				mySmt.setString(1, tfCourse.getSelectedItem().toString());
				myRs = mySmt.executeQuery();
				if(myRs.next()) {
					int i = Integer.parseInt(myRs.getString("Students"));
					i++;
					mySmt = myCon.prepareStatement("update Course set Students=? where Name=?");
					mySmt.setString(1, i+"");
					mySmt.setString(2, tfCourse.getSelectedItem().toString());
					mySmt.executeUpdate();
				}
				JOptionPane.showMessageDialog(null, "Student Updated!");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Can't Update!");
				}
			}
		});
		btnUpdate.setBackground(new Color(220, 20, 60));
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnUpdate.setBounds(332, 423, 138, 53);
		btnUpdate.setVisible(false);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnUpdate.setBackground(Color.WHITE);
	        btnUpdate.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnUpdate.setBackground(new Color(220,20,60));
	        btnUpdate.setForeground(Color.WHITE);
	    }
	});
		contentPane.add(btnUpdate);
		
		tfStatus = new JComboBox<String>();
		tfStatus.setFont(new Font("Prestige Elite Std", Font.PLAIN, 14));
		tfStatus.setBounds(106, 354, 171, 28);
		tfStatus.addItem("Paid");
		tfStatus.addItem("Not Paid");
		contentPane.add(tfStatus);
		
		lblEmail = new JLabel("Email ");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblEmail.setBounds(10, 482, 76, 25);
		contentPane.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		tfEmail.setBounds(107, 482, 169, 24);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		tfCourse = new JComboBox<String>();
		tfCourse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          Object item = e.getItem();
			          String course = item.toString();
			          try {
			        	  mySmt = myCon.prepareStatement("select Fees from Course where Name=?");
			        	  mySmt.setString(1, course);
			        	  myRs2 = mySmt.executeQuery();
			        	  if(myRs2.next())
			        		  tfFees.setText(myRs2.getString("Fees"));
			          }
			          catch(Exception e1) {
			        	  JOptionPane.showMessageDialog(null, "Can't Find "+course+" Fees");
			          }
				}
			       
			}
		});
		tfCourse.setFont(new Font("Prestige Elite Std", Font.BOLD, 15));
		tfCourse.setBounds(106, 185, 170, 25);
		try {
		mySmt = myCon.prepareStatement("select * from Course");
		myRs = mySmt.executeQuery();
		while(myRs.next())
		{
			tfCourse.addItem(myRs.getString("Name"));
		}
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "Database cannot be fetched!");
		}
		contentPane.add(tfCourse);
	}
	public void setValues(String id,String name,String stream,String year,String mobile,String course,String fees,String add,String status,String grade,String email)
	{
		setTitle("Update Student");
		tfId.setText(id);
		tfname.setText(name);
		tfStream.setText(stream);
		tfYear.setText(year);
		tfMobile.setText(mobile);
		tfCourse.setSelectedItem(course);
		tfFees.setText(fees);
		taAddress.setText(add);
		tfStatus.setSelectedItem(status);
		tfGrade.setText(grade);
		tfEmail.setText(email);
		btnRegister.setVisible(false);
		btnUpdate.setVisible(true);
		tfId.setEditable(false);
	}
	public void setVal2(String id,String name,String stream,String year,String mobile,String course,String fees,String add,String status,String grade,String email)
	{
		setTitle(name+"'s details");
		tfId.setText(id);
		tfId.setEditable(false);
		tfname.setText(name);
		tfname.setEditable(false);
		tfStream.setText(stream);
		tfStream.setEditable(false);
		tfYear.setText(year);
		tfYear.setEditable(false);
		tfMobile.setText(mobile);
		tfMobile.setEditable(false);
		tfCourse.setSelectedItem(course);
		tfCourse.setEditable(false);
		tfFees.setText(fees);
		tfFees.setEditable(false);
		taAddress.setText(add);
		taAddress.setEditable(false);
		tfStatus.setSelectedItem(status);
		tfStatus.setEditable(false);
		tfGrade.setText(grade);
		tfGrade.setEditable(false);
		tfEmail.setText(email);
		tfEmail.setEditable(false);
		btnRegister.setVisible(false);
		btnUpdate.setVisible(false);
	}
}
