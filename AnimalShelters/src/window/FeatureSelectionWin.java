package window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JRadioButton;

import Connection.Conn;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FeatureSelectionWin {

	public JFrame frame;
	private int userType = 2;
	public Conn mainConn = null;

	public FeatureSelectionWin(int user,Conn connection) {
		this.userType=user;
		System.out.println("用户类型："+userType);
		this.mainConn = connection;
		initialize();
	}

	public FeatureSelectionWin getSelf() {
		return this;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblUser = new JLabel("Query and maintain User information.");
		lblUser.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblUser.setBounds(46, 117, 387, 31);
		frame.getContentPane().add(lblUser);
		
		JLabel lblAnimal = new JLabel("Query and maintain animal information.");
		lblAnimal.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblAnimal.setBounds(46, 167, 350, 22);
		frame.getContentPane().add(lblAnimal);
		
		JButton btnUser = new JButton("Go");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResWin res = new ResWin(userType,1,getSelf());
				res.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel lblShelter = new JLabel("Query and maintain shelter information.");
		lblShelter.setBounds(46, 211, 360, 31);
		frame.getContentPane().add(lblShelter);
		lblShelter.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel lblInquiryAndMaintenance_1 = new JLabel("Query and maintain health information.");
		lblInquiryAndMaintenance_1.setBounds(46, 257, 387, 31);
		frame.getContentPane().add(lblInquiryAndMaintenance_1);
		lblInquiryAndMaintenance_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel lblVaccine = new JLabel("Query and maintain vaccine information.");
		lblVaccine.setBounds(46, 297, 387, 31);
		frame.getContentPane().add(lblVaccine);
		lblVaccine.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		
		btnUser.setBounds(436, 121, 117, 29);
		frame.getContentPane().add(btnUser);
		
		JButton btnAnimal = new JButton("Go");
		btnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResWin res = new ResWin(userType,2,getSelf());
				res.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAnimal.setBounds(436, 167, 117, 29);
		frame.getContentPane().add(btnAnimal);
		
		JButton btnShelter = new JButton("Go");
		btnShelter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResWin res = new ResWin(userType,3,getSelf());
				res.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnShelter.setBounds(436, 210, 117, 29);
		frame.getContentPane().add(btnShelter);
		
		JButton btnHealth = new JButton("Go");
		btnHealth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResWin res = new ResWin(userType,4,getSelf());
				res.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnHealth.setBounds(436, 260, 117, 29);
		frame.getContentPane().add(btnHealth);
		
		
		JButton btnVaccine = new JButton("Go");
		btnVaccine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResWin res = new ResWin(userType,5,getSelf());
				res.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnVaccine.setBounds(436, 300, 117, 29);
		frame.getContentPane().add(btnVaccine);
		
		
		JLabel lblFeatureSelection = new JLabel("Feature Selection");
		lblFeatureSelection.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		lblFeatureSelection.setBounds(189, 49, 244, 33);
		frame.getContentPane().add(lblFeatureSelection);
			
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setOpaque(false);
		panel.setBounds(0, -12, 570, 350);
		frame.getContentPane().add(panel);
		
		JLabel lblBackground = new JLabel("");
		ImageIcon icon = new ImageIcon("./Res/animal.png");
		 icon.setImage(icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
		lblBackground.setIcon(icon);
		panel.add(lblBackground);
	}
}
