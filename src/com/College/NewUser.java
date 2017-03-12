package com.College;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class NewUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfDesig;
	private JTextField tfUser;
	private JPasswordField pwWord;
	public JButton btnUpdate;
	public JButton btnRegister;
	Connection myCon = null;
	PreparedStatement mySmt = null;
	ResultSet myRs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					NewUser frame = new NewUser();
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
	public NewUser() {
		myCon = MyConnection.getConnection();
		setTitle("New User");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 431, 357);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblName.setBounds(42, 55, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblDesignation.setBounds(42, 101, 108, 14);
		contentPane.add(lblDesignation);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblUsername.setBounds(42, 150, 86, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblPassword.setBounds(42, 195, 95, 19);
		contentPane.add(lblPassword);
		
		tfName = new JTextField();
		tfName.setBounds(195, 44, 125, 25);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfDesig = new JTextField();
		tfDesig.setBounds(195, 93, 125, 25);
		contentPane.add(tfDesig);
		tfDesig.setColumns(10);
		
		tfUser = new JTextField();
		tfUser.setBounds(195, 142, 125, 25);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		pwWord = new JPasswordField();
		pwWord.setBounds(195, 189, 125, 25);
		contentPane.add(pwWord);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mySmt = myCon.prepareStatement("insert into AdminUser values(?,?,?,?)");
					mySmt.setString(1, tfUser.getText());
					mySmt.setString(2, tfName.getText());
					mySmt.setString(3, new String(pwWord.getPassword()));
					mySmt.setString(4, tfDesig.getText());
					mySmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "User Added!");
					dispose();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Cannot add the user!");
				}
			}
		});
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
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		btnRegister.setBounds(96, 265, 108, 39);
		contentPane.add(btnRegister);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					mySmt = myCon.prepareStatement("update AdminUser set Username=?,Name=?,Password=?,Designation=? where Username=?");
					mySmt.setString(1, tfUser.getText());
					mySmt.setString(2, tfName.getText());
					mySmt.setString(3, new String(pwWord.getPassword()));
					mySmt.setString(4, tfDesig.getText());
					mySmt.setString(5, tfUser.getText());
					int i = mySmt.executeUpdate();
					if (i>0)
						JOptionPane.showMessageDialog(null, "Details Updated!");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Cannot Update User!");
				}
			}
		});
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
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(220, 20, 60));
		btnUpdate.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		btnUpdate.setBounds(235, 265, 96, 39);
		contentPane.add(btnUpdate);
	}
	public void forUpdate(String name,String username,String password,String desig){
		tfName.setText(name);
		tfUser.setText(username);
		pwWord.setText(password);
		tfDesig.setText(desig);
		btnRegister.setEnabled(false);
		btnUpdate.setEnabled(true);
		setLocationRelativeTo(null);
	}
}
