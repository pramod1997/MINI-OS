package os;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class pagereplacement {

	private JFrame frame;
	private JTextField text;
	private JTextField text2;
	private JTextField text3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pagereplacement window = new pagereplacement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pagereplacement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	

	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground( Color.white );
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Page Replacement");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(118, 11, 178, 50);
		frame.getContentPane().add(lblNewLabel);
		
		text = new JTextField();
		text.setBounds(118, 145, 201, 28);
		frame.getContentPane().add(text);
		text.setColumns(10);
		
		JLabel lblInput = new JLabel("INPUT ");
		lblInput.setForeground(Color.cyan);
		lblInput.setBackground(Color.cyan);
		lblInput.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInput.setBounds(30, 150, 61, 23);
		frame.getContentPane().add(lblInput);
		
		text2 = new JTextField();
		text2.setBounds(115, 184, 204, 28);
		frame.getContentPane().add(text2);
		text2.setColumns(10);
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setForeground(Color.red);
		lblOutput.setBackground(Color.red);
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOutput.setBounds(40, 191, 68, 14);
		frame.getContentPane().add(lblOutput);
		
		text3 = new JTextField();
		text3.setBounds(305, 114, 86, 20);
		frame.getContentPane().add(text3);
		text3.setColumns(10);
		
		JLabel lblFrame = new JLabel("frame");
		lblFrame.setForeground(Color.blue);
		lblFrame.setBounds(247, 117, 46, 14);
		frame.getContentPane().add(lblFrame);
		
		JButton button = new JButton("FIFO");
		button.setFont(new Font("Tahoma", Font.BOLD, 10));
		button.setForeground(Color.orange);
		button.setBackground(Color.red);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 String Val,v="";
										Val = text.getText();
										convert z = new convert(Val);
										int[] L= z.method1();
										int h = Integer.parseInt(text3.getText());
										 FIFO a1= new FIFO(h,L);
										 int r = a1.method();
										 v=v+""+r;
										//a1.Get(L,L.length);
										 
										text2.setText(v);
					
			}
		});
		button.setBounds(2, 72, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("LRU");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_1.setForeground(Color.orange);
		button_1.setBackground(Color.red);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				  String Val,v="";
										Val = text.getText();
										convert z = new convert(Val);
										int[] L= z.method1();
										int h = Integer.parseInt(text3.getText());
										 LRU a1= new LRU(h,L);
										 int r = a1.method();
										 v=v+""+r;
										//a1.Get(L,L.length);
										 
										text2.setText(v);
										 
			}
		});
		button_1.setBounds(125, 72, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Optimal");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_2.setForeground(Color.orange);
		button_2.setBackground(Color.red);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String Val,v="";
					Val = text.getText();
					convert z = new convert(Val);
					int[] L= z.method1();
					int h = Integer.parseInt(text3.getText());
					 OPT a1= new OPT(h,L);
					 int r = a1.method();
					 v=v+""+r;
					//a1.Get(L,L.length);
					 
					text2.setText(v);

			}
		});
		button_2.setBounds(247, 72, 89, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("2nd chance");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		button_3.setForeground(Color.orange);
		button_3.setBackground(Color.red);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String Val,v="";
					Val = text.getText();
					convert z = new convert(Val);
					int[] L= z.method1();
					//int h = Integer.parseInt(text3.getText());
					 scp a1= new scp(3,L);
					 int r = a1.method();
					 v=v+""+r;
					//a1.Get(L,L.length);
					 
					text2.setText(v);
				
			}
		});
		button_3.setBounds(346, 72, 89, 23);
		frame.getContentPane().add(button_3);
		 
	}
}
