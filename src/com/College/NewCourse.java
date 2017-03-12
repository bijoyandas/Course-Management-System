package com.College;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.sql.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class NewCourse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfName;
	final JButton btnRegister;
	Connection myCon = null;
	PreparedStatement mySmt = null;
	final JButton btnUpdate;
	private JTextField tfFees;
	JTextArea taContents;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					NewCourse frame = new NewCourse();
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
	public NewCourse() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewCourse.class.getResource("/com/College/1481642427_college.png")));
		try {
			myCon = MyConnection.getConnection();
		}
		catch(Exception e) {}
		setType(javax.swing.JFrame.Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 466);
		contentPane = new JPanel();
		setTitle("New Course");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseId = new JLabel("Course Id");
		lblCourseId.setForeground(Color.BLACK);
		lblCourseId.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblCourseId.setBounds(10, 26, 137, 23);
		contentPane.add(lblCourseId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Maiandra GD", Font.BOLD, 14));
		tfId.setBounds(176, 19, 161, 32);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblContents = new JLabel("Contents");
		lblContents.setForeground(Color.BLACK);
		lblContents.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblContents.setBounds(10, 217, 152, 23);
		contentPane.add(lblContents);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblName.setBounds(10, 84, 92, 23);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Maiandra GD", Font.BOLD, 14));
		tfName.setBounds(176, 76, 161, 32);
		contentPane.add(tfName);
		tfName.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 211, 310, 134);
		contentPane.add(scrollPane);
		
		taContents = new JTextArea();
		taContents.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		scrollPane.setViewportView(taContents);
		scrollPane.getVerticalScrollBar().setValue(1);
		scrollPane.getHorizontalScrollBar().setValue(0);
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer.parseInt(tfFees.getText());
				try {
				mySmt = myCon .prepareStatement("insert into Course values(?,?,?,?,?)");
				mySmt.setString(1, tfId.getText());
				mySmt.setString(2, tfName.getText());
				mySmt.setString(3, taContents.getText());
				mySmt.setString(4, tfFees.getText());
				mySmt.setString(5, "0");
				mySmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Course Added Successfully!");
				dispose();
				}
				catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Course can't be added!");
				}
				}
				catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Fees can be only numeric!");
				}
				}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
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
		btnRegister.setBackground(new Color(220, 20, 60));
		btnRegister.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnRegister.setBounds(102, 386, 137, 40);
		contentPane.add(btnRegister);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				mySmt = myCon.prepareStatement("update Course set Id=?,Name=?,Content=?,Fees=? where Id=?");
				mySmt.setString(1, tfId.getText());
				mySmt.setString(2, tfName.getText());
				mySmt.setString(3, taContents.getText());
				mySmt.setString(4, tfFees.getText());
				mySmt.setString(5, tfId.getText());
				mySmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Course Updated!");
				dispose();
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Cannot Update!");
				}
			}
		});
		btnUpdate.setBackground(new Color(220, 20, 60));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		btnUpdate.setBounds(274, 386, 137, 40);
		btnUpdate.setEnabled(false);
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
		
		JLabel lblFees = new JLabel("Fees");
		lblFees.setForeground(Color.BLACK);
		lblFees.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblFees.setBounds(10, 146, 137, 23);
		contentPane.add(lblFees);
		
		tfFees = new JTextField();
		tfFees.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		tfFees.setBounds(176, 137, 161, 33);
		contentPane.add(tfFees);
		tfFees.setColumns(10);
		
	
	}
	public void setVal(String id,String name,String content,String fees)
	{
		tfId.setText(id);
		tfId.setEditable(false);
		tfName.setText(name);
		setTitle("Update Course");
		taContents.setText(content);
		btnUpdate.setEnabled(true);
		btnRegister.setEnabled(false);
		tfFees.setText(fees);
	}
}
