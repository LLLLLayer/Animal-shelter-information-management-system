package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import Connection.Conn;

import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.TextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ResWin {

	public JFrame frame;
	private int userType = 1;//登录用户类型：1管理员 2用户
	private int funcType = 1;//功能选择： 1用户信息 2动物信息 3收容所信息 4健康信息 5疫苗信息
	private String titleString = "Initialization error!";
	private FeatureSelectionWin fatherWin = null;
	Conn connectionConn=null;
	public String showString="";
	private JLabel lblInformationChange;
	
	public int TableCol=0;
	public JTable tabbelInput=null;
	private JTextField textInput;
	public String AnimalImg = "";
	
	public ResWin(int User,int func,FeatureSelectionWin father) {
		this.userType = User;
		this.funcType = func;
		this.fatherWin = father;
		System.out.println("用户类型："+userType+"\n功能类型："+funcType);
		connectionConn=fatherWin.mainConn;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 650, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(this.funcType == 1)
			titleString="Welcome to use the user information query and maintenance function!";
		else if(this.funcType == 2)
			titleString="Welcome to use the animal information query and maintenance function!";
		else if (this.funcType == 3)
			titleString="Welcome to use the shelter information query and maintenance function!";
		else if (this.funcType == 4)
			titleString="Welcome to use the health information query and maintenance function!";
		else if (this.funcType == 5)
			titleString="Welcome to use the vaccine information query and maintenance function!";
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel(titleString);
		lblTitle.setBounds(6, 6, 588, 30);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		frame.getContentPane().add(lblTitle);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fatherWin.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(533, 9, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JLabel lblTheWholeTable = new JLabel("The whole table：");
		lblTheWholeTable.setBounds(6, 48, 117, 16);
		frame.getContentPane().add(lblTheWholeTable);
		
//		TextArea textArea = new TextArea();
//		textArea.setBackground(UIManager.getColor("Button.background"));
//		textArea.setBounds(6, 64, 634, 225);
//		frame.getContentPane().add(textArea);
//		textArea.setText(showString);
		
		JScrollPane j = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(20, 20));
		panel.setBounds(6, 67, 638, 212);
		j.setViewportView(panel);

		j.setBounds(6, 71, 638, 108);
		frame.getContentPane().add(j);
		
		JButton btnRefresh = new JButton("Refresh");//刷新表格界面
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Object[][] rowData;
				try {
					if(funcType==1) {
						rowData = connectionConn.getUserInfTable();
						Object[] columnNames= {"UserID","UserName", "Email", "PhoneNo","Shelter ID"};
						JTable table = new JTable(rowData,columnNames);
						//panel.add(table.getTableHeader(), BorderLayout.NORTH);
						panel.add(table, BorderLayout.CENTER);
					}
					else if(funcType==2) {
						rowData = connectionConn.getAnimalInfTable();
						Object[] columnNames= {"AnimalID","AnimalNo","AnimalName","AnimalType","AnimalSex","AnimalAge","ShelterID"};
						JTable table = new JTable(rowData,columnNames);
						//panel.add(table.getTableHeader(), BorderLayout.NORTH);
						panel.add(table, BorderLayout.CENTER);
					}
					else if(funcType==3) {
						rowData = connectionConn.getShelterInfTable();
				        Object[] columnNames = {"ShelterID", "ShelterName", "ShelterAddress", "ShelterZipCode", "ShelterAllRoomNum","ShelterRemainingRoomNum"};
						JTable table = new JTable(rowData,columnNames);
						//panel.add(table.getTableHeader(), BorderLayout.NORTH);
						panel.add(table, BorderLayout.CENTER);
					}
					else if(funcType==4) {
						rowData = connectionConn.getHealthInfTable();
						Object[] columnNames = {"HealthID", "AnimalID", "UserID", "HealthInformation", "CheckDate","Note"};
						JTable table = new JTable(rowData,columnNames);
						//panel.add(table.getTableHeader(), BorderLayout.NORTH);
						panel.add(table, BorderLayout.CENTER);
					}
					else if(funcType==5) {
						rowData = connectionConn.getVaccineInfTable();
				        Object[] columnNames = {"VaccineID","AnimalID","UserID","VaccineName","VaccinationTime","Note"};
						JTable table = new JTable(rowData,columnNames);
						//panel.add(table.getTableHeader(), BorderLayout.NORTH);
						panel.add(table, BorderLayout.CENTER);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(533, 179, 117, 29);
		frame.getContentPane().add(btnRefresh);
		
		lblInformationChange = new JLabel("Information Change:");
		lblInformationChange.setBounds(6, 212, 150, 16);
		frame.getContentPane().add(lblInformationChange);
		
		//表格初始化
        Object[][] rowData;
		try {
			if(funcType==1) {
				rowData = connectionConn.getUserInfTable();
				Object[] columnNames= {"UserID","UserName", "Email", "PhoneNo","Shelter ID"};
				JTable table = new JTable(rowData,columnNames);
				//panel.add(table.getTableHeader(), BorderLayout.NORTH);
				panel.add(table, BorderLayout.CENTER);
			}
			else if(funcType==2) {
				rowData = connectionConn.getAnimalInfTable();
				Object[] columnNames= {"AnimalID","AnimalNo","AnimalName","AnimalType","AnimalSex","AnimalAge","ShelterID"};
				JTable table = new JTable(rowData,columnNames);
				//panel.add(table.getTableHeader(), BorderLayout.NORTH);
				panel.add(table, BorderLayout.CENTER);
			}
			else if(funcType==3) {
				rowData = connectionConn.getShelterInfTable();
		        Object[] columnNames = {"ShelterID", "ShelterName", "ShelterAddress", "ShelterZipCode", "ShelterAllRoomNum","ShelterRemainingRoomNum"};
				JTable table = new JTable(rowData,columnNames);
				//panel.add(table.getTableHeader(), BorderLayout.NORTH);
				panel.add(table, BorderLayout.CENTER);
			}
			else if(funcType==4) {
				rowData = connectionConn.getHealthInfTable();
				Object[] columnNames = {"HealthID", "AnimalID", "UserID", "HealthInformation", "CheckDate","Note"};
				JTable table = new JTable(rowData,columnNames);
				//panel.add(table.getTableHeader(), BorderLayout.NORTH);
				panel.add(table, BorderLayout.CENTER);
			}
			else if(funcType==5) {
				rowData = connectionConn.getVaccineInfTable();
		        Object[] columnNames = {"VaccineID","AnimalID","UserID","VaccineName","VaccinationTime","Note"};
				JTable table = new JTable(rowData,columnNames);
				//panel.add(table.getTableHeader(), BorderLayout.NORTH);
				panel.add(table, BorderLayout.CENTER);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//其他对应功能初始化
		
		
		if(funcType==1) {
			Object[] columnNames= {"UserID","UserName","UserPassword", "Email", "PhoneNo","Shelter ID"};
			Object[][] inData= {{"UserID","UserName","UserPassword", "Email", "PhoneNo","Shelter ID"},{"","","","","",""}};
			TableCol = 6;
			tabbelInput = new JTable(inData,columnNames);
			tabbelInput.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			tabbelInput.setBackground(SystemColor.window);
			tabbelInput.setBounds(6, 240, 638, 48);
			tabbelInput.setRowHeight(25);
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			tabbelInput.setDefaultRenderer(Object.class, cr);
			frame.getContentPane().add(tabbelInput);
		}
		else if(funcType==2) {
			Object[] columnNames= {"AnimalID","AnimalNo","AnimalName","AnimalType","AnimalSex","AnimalAge","AnimalImage","ShelterID"};
			Object[][] inData= {{"AnimalID","AnimalNo","AnimalName","AnimalType","AnimalSex","AnimalAge","AnimalImage","ShelterID"},{"","","","","","","",""}};
			TableCol = 8;
			tabbelInput = new JTable(inData,columnNames);
			tabbelInput.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			tabbelInput.setBackground(SystemColor.window);
			tabbelInput.setBounds(6, 240, 638, 48);
			tabbelInput.setRowHeight(25);
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			tabbelInput.setDefaultRenderer(Object.class, cr);
			frame.getContentPane().add(tabbelInput);
		}
		else if(funcType==3) {
			Object[] columnNames = {"ShelterID", "ShelterName", "ShelterAddress", "ShelterZipCode", "ShelterAllRoomNum","ShelterRemainingRoomNum"};
			Object[][] inData= {{"ShelterID", "ShelterName", "ShelterAddress", "ShelterZipCode", "ShelterAllRoomNum","ShelterRemainingRoomNum"},{"","","","","",""}};
			TableCol = 6;
			tabbelInput = new JTable(inData,columnNames);
			tabbelInput.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			tabbelInput.setBackground(SystemColor.window);
			tabbelInput.setBounds(6, 240, 638, 48);
			tabbelInput.setRowHeight(25);
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			tabbelInput.setDefaultRenderer(Object.class, cr);
			frame.getContentPane().add(tabbelInput);
		}
		else if(funcType==4) {
			Object[] columnNames = {"HealthID", "AnimalID", "UserID", "HealthInformation", "CheckDate","Note"};
			Object[][] inData= {{"HealthID", "AnimalID", "UserID", "HealthInformation", "CheckDate","Note"},{"","","","","",""}};
			TableCol = 6;
			tabbelInput = new JTable(inData,columnNames);
			tabbelInput.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			tabbelInput.setBackground(SystemColor.window);
			tabbelInput.setBounds(6, 240, 638, 48);
			tabbelInput.setRowHeight(25);
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			tabbelInput.setDefaultRenderer(Object.class, cr);
			frame.getContentPane().add(tabbelInput);
		}
		else if(funcType==5) {
			Object[] columnNames = {"VaccineID","AnimalID","UserID","VaccineName","VaccinationTime","Note"};
			Object[][] inData= {{"VaccineID","AnimalID","UserID","VaccineName","VaccinationTime","Note"},{"","","","","",""}};
			TableCol = 6;
			tabbelInput = new JTable(inData,columnNames);
			tabbelInput.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			tabbelInput.setBackground(SystemColor.window);
			tabbelInput.setBounds(6, 240, 638, 48);
			tabbelInput.setRowHeight(25);
			DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
			cr.setHorizontalAlignment(JLabel.CENTER);
			tabbelInput.setDefaultRenderer(Object.class, cr);
			frame.getContentPane().add(tabbelInput);
		}
			
			
			JButton btnAdd = new JButton("Add");//添加信息
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(userType==2) {
						JOptionPane.showMessageDialog(null,"Insufficient permissions!",null,JOptionPane.PLAIN_MESSAGE);
						return;
					}
					
					if(funcType==1) {
						int n=connectionConn.UserRegistrationCheck(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Added!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Add failure, may be duplicate userID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Add failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if(funcType==2) {
						int n=connectionConn.AnimalInfAdd(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"", tabbelInput.getValueAt(1, 6)+"", tabbelInput.getValueAt(1, 7)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Added!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Add failure, may be duplicate AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Add failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if(funcType==3) {
						int n=connectionConn.ShelterInfAdd(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Added!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Add failure, may be duplicate AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Add failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if(funcType==4) {
						int n=connectionConn.HealthInfAdd(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Added!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Add failure, may be duplicate HealthID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else if(n==3)
							JOptionPane.showMessageDialog(null,"Add failure, may be unknown AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Add failure, maybe unknown UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if(funcType==5) {
						int n=connectionConn.VaccineInfAdd(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Added!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Add failure, may be duplicate VaccineID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else if(n==3)
							JOptionPane.showMessageDialog(null,"Add failure, may be unknown AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Add failure, maybe unknown UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnAdd.setBounds(52, 296, 117, 29);
			frame.getContentPane().add(btnAdd);
			
			JButton btnChange = new JButton("Change");//改变信息
			btnChange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(userType==2) {
						JOptionPane.showMessageDialog(null,"Insufficient permissions!",null,JOptionPane.PLAIN_MESSAGE);
						return;
					}
					
					if(funcType==1) {
						int n=connectionConn.UserInfUpdate(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Changed!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Change failure, may be there is no userID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if (funcType==2) {
						int n=connectionConn.AnimalInfUpdate(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"", tabbelInput.getValueAt(1, 6)+"", tabbelInput.getValueAt(1, 7)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Changed!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Change failure, may be there is no UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if (funcType==3) {
						int n=connectionConn.ShelterInfUpdate(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Changed!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Change failure, may be there is no UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if (funcType==4) {
						int n=connectionConn.HealthInfUpdate(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Changed!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Change failure, may be there is no HealthID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else if(n==3)
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					else if (funcType==5) {
						int n=connectionConn.VaccineInfUpdate(tabbelInput.getValueAt(1, 0)+"", tabbelInput.getValueAt(1, 1)+"", 
								tabbelInput.getValueAt(1, 2)+"", tabbelInput.getValueAt(1, 3)+"",tabbelInput.getValueAt(1, 4)+"", 
								tabbelInput.getValueAt(1, 5)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Changed!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Change failure, may be there is no VaccineID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else if(n==3)
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown AnimalID or other error!",null,JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null,"Change failure, maybe unknown UserID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnChange.setBounds(192, 296, 117, 29);
			frame.getContentPane().add(btnChange);
			
			JButton btnDelete = new JButton("Delete");//删除记录
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(userType==2) {
						JOptionPane.showMessageDialog(null,"Insufficient permissions!",null,JOptionPane.PLAIN_MESSAGE);
						return;
					}
					
					if(funcType==1) {
						int n=connectionConn.UserInfDelete(tabbelInput.getValueAt(1, 0)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Delete!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Delete failure, may be there is no userID userID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					if(funcType==2) {
						int n=connectionConn.AnimalInfDelete(tabbelInput.getValueAt(1, 0)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Delete!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Delete failure, may be there is no AnimalID userID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					if(funcType==3) {
						int n=connectionConn.ShelterInfDelete(tabbelInput.getValueAt(1, 0)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Delete!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Delete failure, may be there is no ShelterID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					if(funcType==4) {
						int n=connectionConn.HealthInfDelete(tabbelInput.getValueAt(1, 0)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Delete!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Delete failure, may be there is no HealthID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
					if(funcType==5) {
						int n=connectionConn.VaccineInfDelete(tabbelInput.getValueAt(1, 0)+"");
						if(n==1)
							JOptionPane.showMessageDialog(null,"Successfully Delete!",null,JOptionPane.PLAIN_MESSAGE);
						else if(n==2)
							JOptionPane.showMessageDialog(null,"Delete failure, may be there is no VaccineID or other error!",null,JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnDelete.setBounds(335, 296, 117, 29);
			frame.getContentPane().add(btnDelete);
			
			JButton btnClear = new JButton("Clear");//表格清空
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(int i=0;i<TableCol;i++) {
						tabbelInput.setValueAt("", 1, i);
					}
				}
			});
			btnClear.setBounds(477, 296, 117, 29);
			frame.getContentPane().add(btnClear);
			
			JLabel lblInformationQuery = new JLabel("Information query：");
			lblInformationQuery.setBounds(6, 337, 150, 16);
			frame.getContentPane().add(lblInformationQuery);
			
			JLabel lblId = new JLabel("ID:");
			lblId.setBounds(6, 365, 28, 16);
			frame.getContentPane().add(lblId);
			
			textInput = new JTextField();
			textInput.setBackground(SystemColor.window);
			textInput.setBounds(41, 360, 411, 26);
			frame.getContentPane().add(textInput);
			textInput.setColumns(10);
			
			JTextArea textArea = new JTextArea();
			textArea.setBackground(SystemColor.window);
			textArea.setBounds(0, 0, 620, 66);
			
			JScrollPane scrollBar = new JScrollPane(textArea);
			scrollBar.setBounds(6, 393, 638, 66);
			frame.getContentPane().add(scrollBar);
			
			JButton btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ID = textInput.getText();
					String showString = connectionConn.GetInf(ID, funcType);
					textArea.setText(showString);
					if(showString.equals("Error!")) {
						JOptionPane.showMessageDialog(null,"The query fails!",null,JOptionPane.ERROR_MESSAGE);
					}
					if(funcType==2) {
						int first=showString.indexOf("AnimalImage: ");
						int last=showString.indexOf("ShelterID: ");
						String substr=showString.substring(first+13,last);
						System.out.println(substr);
						AnimalImg = substr;
					}
					
				}
			});
			btnSearch.setBounds(477, 360, 117, 29);
			frame.getContentPane().add(btnSearch);
			
			if(funcType==2) {
				
				JButton btnCheckAddress = new JButton("CheckAddress");
				btnCheckAddress.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AnimalImages AI = new AnimalImages();
						AI.frame.setVisible(true);
					}
				});
				btnCheckAddress.setBounds(335, 332, 117, 29);
				frame.getContentPane().add(btnCheckAddress);
				
				JButton btnShow = new JButton("ShowImage");
				btnShow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AnimalImages AI = new AnimalImages("/Users/layer/Downloads/Pictures/dog.jpeg");
						//AnimalImages AI = new AnimalImages(AnimalImg);
						AI.frame.setVisible(true);
					}
				});
				btnShow.setBounds(477, 332, 117, 29);
				frame.getContentPane().add(btnShow);
			}
	}
}
