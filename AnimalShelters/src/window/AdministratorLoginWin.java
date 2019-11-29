package window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JTextField;

import Connection.Conn;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministratorLoginWin {

	public JFrame frame;
	private JTextField textFieldAccount;
	private JTextField textFieldPassword;
	private JButton btnBack;
	private JButton btnOK;

	public LoginWin fatherLoginWin=null;

	public AdministratorLoginWin(LoginWin fatherWin) {
		initialize();
		fatherLoginWin=fatherWin;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAccount = new JLabel("Account:");
		lblAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAccount.setBounds(59, 105, 100, 25);
		frame.getContentPane().add(lblAccount);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPassword.setBounds(59, 141, 105, 25);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblTitle = new JLabel("Super administrator Login");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblTitle.setBounds(81, 41, 304, 38);
		frame.getContentPane().add(lblTitle);
		
		textFieldAccount = new JTextField();
		textFieldAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textFieldAccount.setBounds(154, 107, 245, 26);
		frame.getContentPane().add(textFieldAccount);
		textFieldAccount.setColumns(10);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textFieldPassword.setBounds(154, 142, 245, 26);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnBack = new JButton("Back");//返回
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LoginWin LogWin = new LoginWin();
				//LogWin.frame.setVisible(true);
				fatherLoginWin.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(70, 205, 117, 29);
		frame.getContentPane().add(btnBack);
		
		btnOK = new JButton("OK");//登录
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//登录监测
				Conn userConn=fatherLoginWin.getConn();
				//if(userConn.AdminLogin(textFieldAccount.getText(), textFieldPassword.getText())) {
				if(userConn.AdminLoginCheck(textFieldAccount.getText(), textFieldPassword.getText())) {
					//登录成功
					FeatureSelectionWin FSWin = new FeatureSelectionWin(1,fatherLoginWin.getConn());
					FSWin.frame.setVisible(true);
					frame.setVisible(false);
					JOptionPane.showMessageDialog(null,"Wellcome!",null,JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,"Wrong account or password!",null,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setBounds(268, 205, 117, 29);
		frame.getContentPane().add(btnOK);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setOpaque(false);
		panel.setBounds(0, 0, 450, 278);
		frame.getContentPane().add(panel);
		
		JLabel lblBackground = new JLabel("");
		ImageIcon icon = new ImageIcon("./Res/animal.png");
		 icon.setImage(icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
		lblBackground.setIcon(icon);
		panel.add(lblBackground);
	}
}
