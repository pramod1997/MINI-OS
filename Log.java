
package os;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Log extends JFrame {

public static void main(String[] args) {
Log frameTabel = new Log();
}

JButton blogin = new JButton("Login");
JPanel panel = new JPanel();
//JLabel label = new JLabel("Name: ");
JTextField txuser = new JTextField("User Name",15);

JPasswordField pass = new JPasswordField("Password", 15);


Log(){
super("Login Autentification");
setSize(300,200);
setLocation(500,280);
panel.setLayout (null);


txuser.setBounds(70,30,150,20);
pass.setBounds(70,65,150,20);
blogin.setBounds(110,100,80,20);

panel.add(blogin);

panel.add(txuser);
//label.setLabelFor(txuser);
panel.add(pass);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin(){
blogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
    
String puname = txuser.getText();
String ppaswd = pass.getText();
if(puname.equals("pramod") && ppaswd.equals("12345")) {
//newframe regFace =new newframe();
//regFace.setVisible(true);
other.main(null);
dispose();
} else {

JOptionPane.showMessageDialog(null,"Wrong Password / Username");
//txuser.setText("Enter User name ");
//pass.setText("Password");
txuser.requestFocus();
}

}
});
}
}
