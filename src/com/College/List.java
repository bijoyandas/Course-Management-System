package com.College;

import java.awt.EventQueue;

import java.sql.*;

import net.proteanit.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class List extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultComboBoxModel<String> model;
	private JTable table;
	Connection myCon = null;
	PreparedStatement mySmt = null;
	ResultSet myRs = null;
	private JLabel lblFilter;
	private JLabel lblCourse;
	private JLabel label;
	private JComboBox<String> comboCourse;
	private JLabel lblGrade;
	private JComboBox<String> comboGrade;
	private JLabel lblStream;
	private JComboBox<String> comboStream;
	private JLabel lblLabel;
	private JComboBox<String> comboYear;
	private JLabel lblStatus;
	private JComboBox<String> comboStatus;
	private JButton btnRefresh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					List frame = new List();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public List() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(List.class.getResource("/com/College/1481642427_college.png")));
		try {
			myCon = MyConnection.getConnection();
		}
		catch(Exception e) {}
		setType(javax.swing.JFrame.Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 479);
		setLocationRelativeTo(null);
		setTitle("Student List");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1174, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
		mySmt = myCon.prepareStatement("select * from College ORDER BY Id");
		myRs = mySmt.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(myRs));
		
		lblFilter = new JLabel("FILTER");
		lblFilter.setForeground(Color.BLACK);
		lblFilter.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblFilter.setBounds(546, 311, 108, 29);
		contentPane.add(lblFilter);
		
		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblCourse.setForeground(Color.BLACK);
		lblCourse.setBackground(new Color(240, 240, 240));
		lblCourse.setBounds(10, 356, 94, 23);
		contentPane.add(lblCourse);
		
		label = new JLabel("__________________________________________________________________________________________________________");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		label.setBounds(10, 319, 1174, 29);
		contentPane.add(label);
		
		model = new DefaultComboBoxModel<String>(new String[] {"Choose","Java", "JEE", "Android"});
		try {
		mySmt = myCon.prepareStatement("select Course from College");
		myRs = mySmt.executeQuery();
		}
		catch(Exception e) {}
		while(myRs.next()) {
		if (model.getIndexOf(myRs.getString("Course"))==-1)
		model.addElement(myRs.getString("Course"));
		}
		comboCourse = new JComboBox<String>(model);
		comboCourse.setForeground(new Color(220, 20, 60));
		comboCourse.setBackground(new Color(255, 255, 255));
		comboCourse.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 17));
		comboCourse.setBounds(120, 356, 130, 25);
		comboCourse.setSelectedItem("Choose");
		comboCourse.addItemListener(new CourseListener());
		contentPane.add(comboCourse);
		
		lblGrade = new JLabel("Grade");
		lblGrade.setForeground(Color.BLACK);
		lblGrade.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblGrade.setBounds(268, 357, 94, 23);
		contentPane.add(lblGrade);
		
		comboGrade = new JComboBox<String>();
		comboGrade.setForeground(new Color(220, 20, 60));
		comboGrade.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		comboGrade.setBounds(359, 357, 126, 24);
		comboGrade.addItem("Choose");
		comboGrade.addItem("O");
		comboGrade.addItem("E");
		comboGrade.addItem("A");
		comboGrade.addItem("B");
		comboGrade.addItem("C");
		comboGrade.setSelectedItem("Choose");
		comboGrade.addItemListener(new GradeListener());
		contentPane.add(comboGrade);
		
		lblStream = new JLabel("Stream");
		lblStream.setForeground(Color.BLACK);
		lblStream.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblStream.setBounds(495, 360, 94, 14);
		contentPane.add(lblStream);
		
		comboStream = new JComboBox<String>();
		comboStream.setForeground(new Color(220, 20, 60));
		comboStream.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		comboStream.setBounds(599, 356, 141, 24);
		comboStream.addItem("Choose");
		comboStream.addItem("CSE");
		comboStream.addItem("ECE");
		comboStream.addItem("EE");
		comboStream.addItem("IT");
		comboStream.setSelectedItem("Choose");
		comboStream.addItemListener(new StreamListener());
		contentPane.add(comboStream);
		
		lblLabel = new JLabel("Year");
		lblLabel.setForeground(Color.BLACK);
		lblLabel.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblLabel.setBounds(750, 360, 62, 14);
		contentPane.add(lblLabel);
		
		comboYear = new JComboBox<String>();
		comboYear.setForeground(new Color(220, 20, 60));
		comboYear.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		comboYear.setBounds(822, 356, 130, 25);
		comboYear.addItem("Choose");
		comboYear.addItem("1st");
		comboYear.addItem("2nd");
		comboYear.addItem("3rd");
		comboYear.addItem("4th");
		comboYear.setSelectedItem("Choose");
		comboYear.addItemListener(new YearListener());
		contentPane.add(comboYear);
		
		lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		lblStatus.setBounds(962, 360, 90, 14);
		contentPane.add(lblStatus);
		
		comboStatus = new JComboBox<String>();
		comboStatus.setForeground(new Color(220, 20, 60));
		comboStatus.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		comboStatus.setBounds(1062, 356, 122, 26);
		comboStatus.addItem("Choose");
		comboStatus.addItem("Paid");
		comboStatus.addItem("Not Paid");
		comboStatus.setSelectedItem("Choose");
		comboStatus.addItemListener(new StatusListener());
		contentPane.add(comboStatus);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				comboCourse.setSelectedItem("Choose");
				comboStream.setSelectedItem("Choose");
				comboYear.setSelectedItem("Choose");
				comboGrade.setSelectedItem("Choose");
				comboStatus.setSelectedItem("Choose");
				mySmt = myCon.prepareStatement("select * from College ORDER BY Id");
				myRs = mySmt.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(myRs));
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Cannot Refresh Now!");
				}
			}
		});
		btnRefresh.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(220, 20, 60));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBorderPainted(false);
		btnRefresh.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnRefresh.setBackground(Color.WHITE);
		        btnRefresh.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnRefresh.setBackground(new Color(220,20,60));
		        btnRefresh.setForeground(Color.WHITE);
		    }
		});
		btnRefresh.setBounds(523, 390, 136, 50);
		contentPane.add(btnRefresh);
		}
		catch(Exception e1) {}
		
	}
	class StatusListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
		          Object item = e.getItem();
		          String course = item.toString();
		          if (!course.equals("Choose")) {
		          try {
		          mySmt = myCon.prepareStatement("select * from College where Status=?");
		          mySmt.setString(1, course);
		          myRs = mySmt.executeQuery();
		          table.setModel(DbUtils.resultSetToTableModel(myRs));
		          }
		          catch(Exception e1) {
		        	  JOptionPane.showMessageDialog(null, "Cannot Filter with "+course);
		          }
			}
		       }
		}
	}
	class YearListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
		          Object item = e.getItem();
		          String course = item.toString();
		          if (!course.equals("Choose")) {
		          try {
		          mySmt = myCon.prepareStatement("select * from College where Year=?");
		          mySmt.setString(1, course);
		          myRs = mySmt.executeQuery();
		          table.setModel(DbUtils.resultSetToTableModel(myRs));
		          }
		          catch(Exception e1) {
		        	  JOptionPane.showMessageDialog(null, "Cannot Filter with "+course);
		          }
			}
		       }
		}
	}
	class StreamListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
		          Object item = e.getItem();
		          String course = item.toString();
		          if (!course.equals("Choose")) {
		          try {
		          mySmt = myCon.prepareStatement("select * from College where Stream=?");
		          mySmt.setString(1, course);
		          myRs = mySmt.executeQuery();
		          table.setModel(DbUtils.resultSetToTableModel(myRs));
		          }
		          catch(Exception e1) {
		        	  JOptionPane.showMessageDialog(null, "Cannot Filter with "+course);
		          }
			}
		       }
		}
	}
	class GradeListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
		          Object item = e.getItem();
		          String course = item.toString();
		          if (!course.equals("Choose")) {
		          try {
		          mySmt = myCon.prepareStatement("select * from College where Grade=?");
		          mySmt.setString(1, course);
		          myRs = mySmt.executeQuery();
		          table.setModel(DbUtils.resultSetToTableModel(myRs));
		          }
		          catch(Exception e1) {
		        	  JOptionPane.showMessageDialog(null, "Cannot Filter with "+course);
		          }
			}
		       }
		}
		
	}
	class CourseListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getItem();
	          String course = item.toString();
	          if (!course.equals("Choose")) {
	          try {
	          mySmt = myCon.prepareStatement("select * from College where Course=?");
	          mySmt.setString(1, course);
	          myRs = mySmt.executeQuery();
	          table.setModel(DbUtils.resultSetToTableModel(myRs));
	          }
	          catch(Exception e1) {
	        	  JOptionPane.showMessageDialog(null, "Cannot Filter with "+course);
	          }
	          }
	       }
	    }       
	}
}
