package window;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Connection.Conn;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisteredWin {

	public JFrame frame;
	private JTextField textFieldAccount;
	private JTextField textFieldPassword;
	private JTextField textFieldConPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField textFieldShelterId;
	private JTextField textFieldUserName;
	public LoginWin fatherLoginWin=null;
	
	public RegisteredWin(LoginWin fathinWin) {
		initialize();
		fatherLoginWin=fathinWin;
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblTitle = new JLabel("User Registration");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblTitle.setBounds(127, 32, 192, 43);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAccount.setBounds(35, 125, 142, 16);
		frame.getContentPane().add(lblAccount);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPassword.setBounds(35, 153, 150, 16);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password：");
		lblConfirmPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(23, 181, 164, 16);
		frame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEmail.setBounds(35, 210, 142, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone NO:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPhone.setBounds(35, 238, 142, 16);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblShelterId = new JLabel("Shelter ID:");
		lblShelterId.setHorizontalAlignment(SwingConstants.CENTER);
		lblShelterId.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblShelterId.setBounds(35, 266, 142, 16);
		frame.getContentPane().add(lblShelterId);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setBounds(189, 121, 219, 26);
		frame.getContentPane().add(textFieldAccount);
		textFieldAccount.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(189, 149, 219, 26);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		textFieldConPassword = new JTextField();
		textFieldConPassword.setBounds(189, 178, 219, 26);
		frame.getContentPane().add(textFieldConPassword);
		textFieldConPassword.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(189, 206, 219, 26);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(189, 234, 219, 26);
		frame.getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		textFieldShelterId = new JTextField();
		textFieldShelterId.setBounds(189, 262, 219, 26);
		frame.getContentPane().add(textFieldShelterId);
		textFieldShelterId.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LoginWin LogWin = new LoginWin();
				//LogWin.frame.setVisible(true);
				fatherLoginWin.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(67, 298, 117, 29);
		frame.getContentPane().add(btnBack);
		
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUserName.setBounds(23, 97, 154, 16);
		frame.getContentPane().add(lblUserName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(189, 93, 219, 26);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserID=textFieldAccount.getText();
				String UserName=textFieldUserName.getText();
				String UserPassword=textFieldPassword.getText();
				String UserConPassword=textFieldConPassword.getText();
				String UserEmail=textFieldEmail.getText();
				String UserPhoneNo=textFieldPhone.getText();
				String UserSellterID=textFieldShelterId.getText();
				if(UserID.equals("")||UserName.equals("")||UserPassword.equals("")||UserConPassword.equals("")
						||UserEmail.equals("")||UserPhoneNo.equals("")||UserSellterID.equals("")) {//填写完整检测
					JOptionPane.showMessageDialog(null,"Incomplete filling！",null,JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(!UserPassword.equals(UserConPassword)){//密码正确检测
					JOptionPane.showMessageDialog(null,"Two different passwords!",null,JOptionPane.ERROR_MESSAGE);
					return;
				}
				//注册
				Conn userConn=fatherLoginWin.getConn();
				int n=userConn.UserRegistrationCheck(UserID, UserName, UserPassword, UserEmail, UserPhoneNo, UserSellterID);
				if(n==1) {
//				if(userConn.UserRegistration(UserID, UserName, UserPassword, UserEmail, UserPhoneNo, UserSellterID)) {
					//注册成功
					JOptionPane.showMessageDialog(null,"Registered successfully!",null,JOptionPane.PLAIN_MESSAGE);
					//返回登录界面
					fatherLoginWin.frame.setVisible(true);
					frame.setVisible(false);
				}
				else if(n==2) {
					//注册失败:账号重复
					JOptionPane.showMessageDialog(null,"Registration failed, may be duplicate user ID or other error!",null,JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"Registration failed, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setBounds(264, 298, 117, 29);
		frame.getContentPane().add(btnOK);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setOpaque(false);
		panel.setBounds(0, 118, 450, 260);
		frame.getContentPane().add(panel);
		
		JLabel lblBackground = new JLabel("");
		ImageIcon icon = new ImageIcon("./Res/animal.png");
		 icon.setImage(icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight()-100, Image.SCALE_DEFAULT));
		lblBackground.setIcon(icon);
		panel.add(lblBackground);
	}
}
