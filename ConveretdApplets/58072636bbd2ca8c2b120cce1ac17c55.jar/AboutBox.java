import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AboutBox extends JPanel
{
    JFrame window;
    
    public AboutBox() {
        this.init();
        (this.window = new JFrame("About UBO-SOFT GraphingCalc 1.0")).setSize(370, 240);
        this.window.getContentPane().add(this);
        this.window.setResizable(false);
        this.window.setVisible(true);
    }
    
    private void init() {
        final Font font = new Font("Arial", 0, 12);
        this.setLayout(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(7, 1));
        final JLabel label = new JLabel("UBO-SOFT GraphingCalc 1.0");
        label.setFont(new Font("Arial", 1, 14));
        final JLabel label2 = new JLabel("<html>Programmer : <b>Udesh Senaratne</b></html>");
        label2.setFont(font);
        final JLabel label3 = new JLabel("Copyright (C) 2004 Udesh Senaratne, All Rights Reserved");
        label3.setFont(font);
        final JLabel label4 = new JLabel("<html>Website: <font color='#003399'><u>www.student.math.uwaterloo.ca/~nsenarat</u></font></html>");
        label4.setFont(font);
        final JLabel label5 = new JLabel("<html>Email: <font color='#003399'><u>nsenarat@uwaterloo.ca</u></font></html>");
        label5.setFont(font);
        final JButton button = new JButton("Close");
        button.setSize(new Dimension(100, 25));
        button.setPreferredSize(new Dimension(100, 25));
        button.addActionListener(new CloseListener());
        final JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setSize(115, 30);
        panel2.setPreferredSize(new Dimension(115, 30));
        panel2.add(button);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(panel2);
        this.add(new JLabel("  "), "West");
        this.add(panel, "Center");
    }
    
    private class CloseListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            AboutBox.this.window.dispose();
        }
    }
}
