package com.College;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Upload extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPath;
	File file;
	java.sql.Connection myCon = null;
	ResultSet myRs = null;
	PreparedStatement mySmt = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Upload frame = new Upload();
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
	public Upload() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Upload.class.getResource("/com/College/1481642427_college.png")));
		try {
			myCon = MyConnection.getConnection();
		}
		catch(Exception e) {}
		setType(javax.swing.JFrame.Type.UTILITY);
		setTitle("Upload");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 440);
		
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setForeground(Color.BLACK);
		lblId.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblId.setBounds(10, 11, 117, 23);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		tfId.setBounds(180, 10, 163, 24);
		contentPane.add(tfId);
		tfId.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		tfId.setColumns(10);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setForeground(Color.BLACK);
		lblCourse.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblCourse.setBounds(10, 81, 117, 23);
		contentPane.add(lblCourse);
		
		final JComboBox<String> comboCourse = new JComboBox<String>();
		comboCourse.setBounds(180, 77, 163, 27);
		try {
			mySmt = myCon.prepareStatement("select * from Course");
			myRs = mySmt.executeQuery();
		while(myRs.next()) {
			comboCourse.addItem(myRs.getString("Name"));
		}
		}
		catch(Exception e) {e.printStackTrace();}
		contentPane.add(comboCourse);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblName.setBounds(10, 158, 117, 23);
		contentPane.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		tfName.setBounds(180, 158, 163, 23);
		tfName.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		final JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(Upload.this);
				if (result == JFileChooser.APPROVE_OPTION) {
				    file = fileChooser.getSelectedFile();
				    tfPath.setText(file.getAbsolutePath().toString());
				}
			}
		});
		btnChooseFile.setBackground(new Color(220, 20, 60));
		btnChooseFile.setForeground(new Color(255, 255, 255));
		btnChooseFile.setFocusPainted(false);
		btnChooseFile.setBorderPainted(false);
		btnChooseFile.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnChooseFile.setBackground(Color.WHITE);
		        btnChooseFile.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnChooseFile.setBackground(new Color(220,20,60));
		        btnChooseFile.setForeground(Color.WHITE);
		    }
			});
		btnChooseFile.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 15));
		btnChooseFile.setBounds(10, 236, 161, 33);
		contentPane.add(btnChooseFile);
		
		tfPath = new JTextField();
		tfPath.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		tfPath.setBounds(171, 240, 258, 26);
		tfPath.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		contentPane.add(tfPath);
		tfPath.setColumns(10);
		
		final JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText()=="") {
					JOptionPane.showConfirmDialog(null, "Id cannot be blank");
				}
				else if (tfName.getText()=="") {
					JOptionPane.showMessageDialog(null, "Name cannot be blank");
				}
				else if (tfPath.getText()=="") 
					JOptionPane.showMessageDialog(null, "Please choose a source file");
				else {
					try {
					mySmt = myCon.prepareStatement("insert into Material values(?,?,?,?)");
					mySmt.setString(1, tfId.getText());
					mySmt.setString(2, tfName.getText());
					mySmt.setString(3, comboCourse.getSelectedItem().toString());
					FileInputStream in = new FileInputStream(file);
					mySmt.setBinaryStream(4,in,(int)file.length());
					mySmt.executeUpdate();
					mySmt.close();
					JOptionPane.showMessageDialog(null, "Material Uploaded!");
					dispose();
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpload.setBackground(new Color(220, 20, 60));
		btnUpload.setForeground(new Color(255, 255, 255));
		btnUpload.setFocusPainted(false);
		btnUpload.setBorderPainted(false);
		btnUpload.addMouseListener(new MouseAdapter() {
		public void mouseEntered(java.awt.event.MouseEvent evt) {
	        btnUpload.setBackground(Color.WHITE);
	        btnUpload.setForeground(Color.BLACK);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        btnUpload.setBackground(new Color(220,20,60));
	        btnUpload.setForeground(Color.WHITE);
	    }
		});
		btnUpload.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 15));
		btnUpload.setBounds(158, 323, 124, 42);
		contentPane.add(btnUpload);
	}
}
