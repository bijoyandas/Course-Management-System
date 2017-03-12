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
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class Email extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfReceiver;
	private JTextField tfSubject;
	private JPasswordField passwordField;
	private JTextField tfSender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Email frame = new Email();
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
	public Email() {
		setType(javax.swing.JFrame.Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Email.class.getResource("/com/College/1481642427_college.png")));
		setTitle("Send Email");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 463);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmailId = new JLabel("Receiver");
		lblEmailId.setForeground(Color.BLACK);
		lblEmailId.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblEmailId.setBounds(10, 99, 112, 23);
		contentPane.add(lblEmailId);
		
		tfReceiver = new JTextField();
		tfReceiver.setFont(new Font("Prestige Elite Std", Font.PLAIN, 14));
		tfReceiver.setBounds(139, 94, 201, 31);
		contentPane.add(tfReceiver);
		tfReceiver.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setForeground(Color.BLACK);
		lblSubject.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblSubject.setBounds(10, 140, 112, 23);
		contentPane.add(lblSubject);

		
		tfSubject = new JTextField();
		tfSubject.setFont(new Font("Prestige Elite Std", Font.PLAIN, 14));
		tfSubject.setColumns(10);
		tfSubject.setBounds(139, 131, 201, 31);
		contentPane.add(tfSubject);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.BLACK);
		lblMessage.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblMessage.setBounds(9, 179, 124, 23);
		contentPane.add(lblMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 175, 364, 145);
		contentPane.add(scrollPane);
		
		final JTextArea taMessage = new JTextArea();
		taMessage.setFont(new Font("Prestige Elite Std", Font.PLAIN, 15));
		taMessage.setLineWrap(true);
		scrollPane.setViewportView(taMessage);
		
		final JButton btnSend = new JButton("Send");
		btnSend.setFocusPainted(false);
		btnSend.setBorderPainted(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String to = tfReceiver.getText();//change accordingly

			      // Sender's email ID needs to be mentioned
			      String from = tfSender.getText();//change accordingly
			      final String username = tfSender.getText();//change accordingly
			      final String password = new String(passwordField.getPassword());//change accordingly

			      // Assuming you are sending email through relay.jangosmtp.net
			      String host = "smtp.gmail.com";

			      Properties props = new Properties();
			      props.put("mail.smtp.auth", "true");
			      props.put("mail.smtp.starttls.enable", "true");
			      props.put("mail.smtp.host", host);
			      props.put("mail.smtp.port", "587");

			      // Get the Session object.
			      Session session = Session.getInstance(props,
			      new javax.mail.Authenticator() {
			         protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			         }
			      });

			      try {
			         // Create a default MimeMessage object.
			         Message message = new MimeMessage(session);

			         // Set From: header field of the header.
			         message.setFrom(new InternetAddress(from));

			         // Set To: header field of the header.
			         message.setRecipients(Message.RecipientType.TO,
			         InternetAddress.parse(to));

			         // Set Subject: header field
			         message.setSubject(tfSubject.getText());

			         // Now set the actual message
			         message.setText(taMessage.getText().toString());

			         // Send message
			         Transport.send(message);

			         JOptionPane.showMessageDialog(null, "Message sent Successfully");
			         dispose();		  
			      } catch (MessagingException e1) {
			    	  e1.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Mail Cannot be Sent");
			      }
			}
		});
		btnSend.setBackground(new Color(220, 20, 60));
		btnSend.setForeground(new Color(255, 255, 255));
		btnSend.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		btnSend.setBounds(200, 351, 106, 54);
		btnSend.addMouseListener(new MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        btnSend.setBackground(Color.WHITE);
		        btnSend.setForeground(Color.BLACK);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        btnSend.setBackground(new Color(220,20,60));
		        btnSend.setForeground(Color.WHITE);
		    }
		});
		contentPane.add(btnSend);
		
		JLabel lblSender = new JLabel("Sender");
		lblSender.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblSender.setBounds(10, 28, 73, 24);
		contentPane.add(lblSender);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		lblPassword.setBounds(10, 67, 84, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(139, 58, 201, 30);
		contentPane.add(passwordField);
		
		tfSender = new JTextField();
		tfSender.setFont(new Font("Prestige Elite Std", Font.PLAIN, 14));
		tfSender.setBounds(139, 21, 201, 32);
		contentPane.add(tfSender);
		tfSender.setColumns(10);
	}

}
