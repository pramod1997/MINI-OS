
package os;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;

public class OpenFile extends JFrame 
{
	Container konten = getContentPane();
	private JButton btnBuka = new JButton("MY COMPUTER",new ImageIcon("src/Component/gambar/package.png"));
	private JPanel panel = new JPanel();
	private JFileChooser fc = new JFileChooser();
	private JLabel lbl = new JLabel();
	private ImageHandler handler = new ImageHandler();
	
	//Konstruktor
	public OpenFile()
	{
		super("MY COMPUTER");
		setSize(1000,900);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel.add(lbl);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		konten.add(btnBuka,BorderLayout.NORTH);
		konten.add(panel,BorderLayout.CENTER);
		
		btnBuka.addActionListener(handler);
		
	}//Akhir Konstruktor
	
	//Inner class
	private class ImageHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent act)
		{
			int open = fc.showOpenDialog(panel);
			if(open == JFileChooser.APPROVE_OPTION)
			{
				String sumberGambar = fc.getSelectedFile().getPath();
				lbl.setIcon(new ImageIcon(sumberGambar));
			}
		}
	}//Akhir Inner class
	
	
	public static void main(String[] ar)
	{
		//Membuat Look and Feel dengan Java Nimbus
			try{
			       UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		            } 
				catch (UnsupportedLookAndFeelException e) 
				{
							
				} 
				catch (ClassNotFoundException e)
				{
							
				} 
				catch (InstantiationException e) 
				{
							
				} 
				catch (IllegalAccessException e)
				{
							
				}
		new OpenFile();
	}
}