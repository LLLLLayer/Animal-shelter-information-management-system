package window;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AnimalImages {

	public JFrame frame;
	private JTextField textField;
	String filenameString;
	int typeFlag = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalImages window = new AnimalImages("/Users/layer/Downloads/Pictures/dog.jpeg");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnimalImages(String str) {
		typeFlag=1;
		filenameString=str;
		initialize();
	}
	public AnimalImages() {
		typeFlag=2;
		initialize();
	}

	private void initialize() {
		if(typeFlag==1){
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 520);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			panel.setBounds(new Rectangle(1, 1, 1, 1));
			panel.setOpaque(false);
			panel.setBounds(10, 35, 430, 450);
			frame.getContentPane().add(panel);
			
			
			JLabel lblBackground = new JLabel("");
			System.out.println("打开图片："+filenameString);
			ImageIcon icon = new ImageIcon(filenameString);
			 icon.setImage(icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
			lblBackground.setIcon(icon);
			panel.add(lblBackground);
			
			JButton btnCancle = new JButton("Back");
			btnCancle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});
			btnCancle.setBounds(10, 10, 117, 29);
			frame.getContentPane().add(btnCancle);
			
			return;
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 147);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Images Assistant");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(165, 6, 128, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFile = new JLabel("File：");
		lblFile.setBounds(24, 46, 35, 16);
		frame.getContentPane().add(lblFile);
		
		textField = new JTextField();
		textField.setBounds(71, 41, 246, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFileChooser chooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG/GIF/PNG Images", "jpg", "gif","png");
		        chooser.setFileFilter(filter);
		        int returnVal = chooser.showOpenDialog(chooser);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        System.out.println("You chose to open this file: " +
		        chooser.getSelectedFile().getAbsolutePath());
		        }
		        textField.setText(chooser.getSelectedFile().getAbsolutePath());
				
			}
		});
		btnChoose.setBounds(327, 41, 117, 29);
		frame.getContentPane().add(btnChoose);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancle.setBounds(81, 79, 117, 29);
		frame.getContentPane().add(btnCancle);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnOk.setBounds(233, 79, 117, 29);
		frame.getContentPane().add(btnOk);
	}
}
