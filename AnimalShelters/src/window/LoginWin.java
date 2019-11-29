package window;



import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;

import Connection.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.FlowLayout;

public class LoginWin {

	public JFrame frame;
	JLabel lblStatus=null;
	private Conn SqlConn=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWin window = new LoginWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginWin() {
		initialize();
		//数据库链接
		SqlConn = new Conn();
		SqlConn.getconn();
		if(SqlConn.getConnStatus()) {
			lblStatus.setText("Normal");
			lblStatus.setForeground(Color.GREEN);
		}
		else {
			lblStatus.setText("Error");
			lblStatus.setForeground(Color.RED);
		}
	}
	
	public LoginWin getSelf() {
		return this;
	}
	
	public Conn getConn() {
		return SqlConn;
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		frame.setResizable(false);
		
		JButton btnAdministrator = new JButton("Administrator");//管理员登录
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministratorLoginWin AdLoginWin = new AdministratorLoginWin(getSelf());
				AdLoginWin.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAdministrator.setBounds(155, 136, 153, 29);
		frame.getContentPane().add(btnAdministrator);
		
		JButton btnUser = new JButton("User");//用户登录
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserLoginWin UserLoginWin = new UserLoginWin(getSelf());
				UserLoginWin.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnUser.setBounds(155, 179, 153, 29);
		frame.getContentPane().add(btnUser);
		
		JLabel lblTitle1 = new JLabel("Animal Shelter ");
		lblTitle1.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblTitle1.setBounds(127, 34, 199, 32);
		frame.getContentPane().add(lblTitle1);
		
		JLabel lblTitle2 = new JLabel("Information Management System");
		lblTitle2.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		lblTitle2.setBounds(32, 79, 380, 45);
		frame.getContentPane().add(lblTitle2);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblLogin.setBounds(75, 154, 54, 27);
		frame.getContentPane().add(lblLogin);
		
		JButton btnRegistered = new JButton("Registered");//注册
		btnRegistered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisteredWin RegWin = new RegisteredWin(getSelf());
				RegWin.frame.setVisible(true);
			}
		});
		btnRegistered.setBounds(327, 243, 117, 29);
		frame.getContentPane().add(btnRegistered);
		
		lblStatus = new JLabel("Detected...");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setBounds(110, 256, 105, 16);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblNetwork = new JLabel("Network Status: ");
		lblNetwork.setBounds(6, 256, 123, 16);
		frame.getContentPane().add(lblNetwork);
		
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
