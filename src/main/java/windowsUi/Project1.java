package windowsUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project1 extends JFrame implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JCheckBox c1,c2,c3;
    JButton button;
    public Project1(){
        frame=new JFrame("Swing Project");
        panel=new JPanel();
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        //frame.setLayout(new BorderLayout(10,10));
        //frame.setLocationRelativeTo(null);
        frame.setSize(800,500);
        label=new JLabel("Food Ordering System");
        label.setBounds(50,50,300,20);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setVerticalAlignment(JLabel.CENTER);
        c1=new JCheckBox("Pizza @800");
        c1.setBounds(100,100,150,20);
        c2=new JCheckBox("Burger @100");
        c2.setBounds(100,150,150,20);
        c3=new JCheckBox("Tea @50");
        c3.setBounds(100,175,150,20);
        button=new JButton("Calculate");
        button.setBounds(100,200,80,30);
        button.addActionListener(this);
        panel.add(label);
        panel.add(c1);
        panel.add(c2);
        panel.add(c3);
        panel.add(button);

        panel.setBackground(Color.CYAN);
        //panel.setBounds(100,100,200,30);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Project1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float amount = 0;
        String msg = "";

        if (c1.isSelected()) {
            amount += 100;
            msg += "Pizza: 100\n";
        }
        if (c2.isSelected()) {
            amount += 50;
            msg += "Burger: 50\n";
        }
        if (c3.isSelected()) {
            amount += 30;
            msg += "Tea: 30\n";
        }

        msg += "-------------------------\n";
        JOptionPane.showMessageDialog(this, msg + "Total: " + amount);
    }

}
