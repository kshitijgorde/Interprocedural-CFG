import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProApplet extends JApplet implements ActionListener
{
    int binary;
    int decimal;
    int dig;
    int total;
    JTextField tf1;
    JTextField tf2;
    JButton b1;
    String[] frommenulist;
    String[] tomenulist;
    JComboBox frommenu;
    JComboBox tomenu;
    Container c;
    
    public void init() {
        this.frommenu.setSelectedItem(Integer.toString(0));
        this.tomenu.setSelectedItem(Integer.toString(0));
        this.tomenu.setBackground(new Color(230, 230, 230));
        this.frommenu.setBackground(new Color(230, 230, 230));
        this.frommenu.addActionListener(this);
        this.tomenu.addActionListener(this);
        this.c.setLayout(new FlowLayout());
        this.setSize(300, 200);
        final JLabel label = new JLabel("To");
        final JLabel label2 = new JLabel("To");
        label.setBackground(new Color(230, 230, 230));
        this.c.add(this.tf1);
        this.c.add(label2);
        this.c.add(this.tf2);
        this.c.add(this.frommenu);
        this.c.add(label);
        this.c.add(this.tomenu);
        this.c.add(this.b1);
        this.tf2.setEditable(false);
        this.b1.addActionListener(this);
        this.b1.setBackground(Color.yellow);
        this.c.setBackground(Color.black);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b1 && this.Checker()) {
            if (this.frommenu.getSelectedItem() == "Binary") {
                if (this.tomenu.getSelectedItem() == "Binary") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Octal") {
                    final String text = this.tf1.getText();
                    this.Binarytodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltooct();
                    this.tf1.setText(text);
                }
                if (this.tomenu.getSelectedItem() == "Decimal") {
                    this.Binarytodecimal();
                }
                if (this.tomenu.getSelectedItem() == "Hexadecimal") {
                    final String text2 = this.tf1.getText();
                    this.Binarytodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltohex();
                    this.tf1.setText(text2);
                }
                if (this.tomenu.getSelectedItem() == "Select One") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
            }
            if (this.frommenu.getSelectedItem() == "Select One") {
                if (this.tomenu.getSelectedItem() == "Binary") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Octal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Decimal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Hexadecimal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Select One") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
            }
            if (this.frommenu.getSelectedItem() == "Octal") {
                if (this.tomenu.getSelectedItem() == "Select One") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Binary") {
                    final String text3 = this.tf1.getText();
                    this.Octtodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltobinary();
                    this.tf1.setText(text3);
                }
                if (this.tomenu.getSelectedItem() == "Octal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Decimal") {
                    this.Octtodecimal();
                }
                if (this.tomenu.getSelectedItem() == "Hexadecimal") {
                    final String text4 = this.tf1.getText();
                    this.Octtodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltohex();
                    this.tf1.setText(text4);
                }
            }
            if (this.frommenu.getSelectedItem() == "Decimal") {
                if (this.tomenu.getSelectedItem() == "Select One") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Binary") {
                    this.Decimaltobinary();
                }
                if (this.tomenu.getSelectedItem() == "Octal") {
                    this.Decimaltooct();
                }
                if (this.tomenu.getSelectedItem() == "Decimal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Hexadecimal") {
                    this.Decimaltohex();
                }
            }
            if (this.frommenu.getSelectedItem() == "Hexadecimal") {
                if (this.tomenu.getSelectedItem() == "Select One") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
                if (this.tomenu.getSelectedItem() == "Binary") {
                    final String text5 = this.tf1.getText();
                    this.Hextodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltobinary();
                    this.tf1.setText(text5);
                }
                if (this.tomenu.getSelectedItem() == "Octal") {
                    final String text6 = this.tf1.getText();
                    this.Hextodecimal();
                    this.tf1.setText(Integer.parseInt(this.tf2.getText()) + "");
                    this.Decimaltooct();
                    this.tf1.setText(text6);
                }
                if (this.tomenu.getSelectedItem() == "Decimal") {
                    this.Hextodecimal();
                }
                if (this.tomenu.getSelectedItem() == "Hexadecimal") {
                    JOptionPane.showMessageDialog((Component)actionEvent.getSource(), "ARE YOU DUMB ?? LOOK AT YOUR CHOICES !", "HUH???", 2);
                }
            }
        }
    }
    
    public int Hex2decCheck(final String s) {
        int n = 0;
        if (s.equalsIgnoreCase("0")) {
            n = 0;
        }
        if (s.equalsIgnoreCase("1")) {
            n = 1;
        }
        if (s.equalsIgnoreCase("2")) {
            n = 2;
        }
        if (s.equalsIgnoreCase("3")) {
            n = 3;
        }
        if (s.equalsIgnoreCase("4")) {
            n = 4;
        }
        if (s.equalsIgnoreCase("5")) {
            n = 5;
        }
        if (s.equalsIgnoreCase("6")) {
            n = 6;
        }
        if (s.equalsIgnoreCase("7")) {
            n = 7;
        }
        if (s.equalsIgnoreCase("8")) {
            n = 8;
        }
        if (s.equalsIgnoreCase("9")) {
            n = 9;
        }
        if (s.equalsIgnoreCase("A")) {
            n = 10;
        }
        if (s.equalsIgnoreCase("B")) {
            n = 11;
        }
        if (s.equalsIgnoreCase("C")) {
            n = 12;
        }
        if (s.equalsIgnoreCase("D")) {
            n = 13;
        }
        if (s.equalsIgnoreCase("E")) {
            n = 14;
        }
        if (s.equalsIgnoreCase("F")) {
            n = 15;
        }
        return n;
    }
    
    public String Dec2hexCheck(final int n) {
        String s = "";
        if (n == 0) {
            s = "0";
        }
        if (n == 1) {
            s = "1";
        }
        if (n == 2) {
            s = "2";
        }
        if (n == 3) {
            s = "3";
        }
        if (n == 4) {
            s = "4";
        }
        if (n == 5) {
            s = "5";
        }
        if (n == 6) {
            s = "6";
        }
        if (n == 7) {
            s = "7";
        }
        if (n == 8) {
            s = "8";
        }
        if (n == 9) {
            s = "9";
        }
        if (n == 10) {
            s = "A";
        }
        if (n == 11) {
            s = "B";
        }
        if (n == 12) {
            s = "C";
        }
        if (n == 13) {
            s = "D";
        }
        if (n == 14) {
            s = "E";
        }
        if (n == 15) {
            s = "F";
        }
        return s;
    }
    
    public void Decimaltobinary() {
        String string = "";
        try {
            this.decimal = Integer.parseInt(this.tf1.getText());
            int n = 0;
            while (this.decimal > 0) {
                string = this.decimal % 2 + "" + string;
                this.decimal /= 2;
                ++n;
            }
        }
        catch (Exception ex) {
            this.tf1.setText("BAD NUMBER");
        }
        this.tf2.setText(string);
    }
    
    public void Binarytodecimal() {
        this.total = 0;
        try {
            int int1 = Integer.parseInt(this.tf1.getText());
            final String text = this.tf1.getText();
            for (int i = 0; i < text.length(); ++i) {
                this.dig = int1 % 10;
                int1 /= 10;
                this.total += (int)(this.dig * Math.pow(2.0, i));
            }
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
        }
        this.tf2.setText("" + this.total);
    }
    
    public void Decimaltohex() {
        String string = "";
        try {
            double n = Double.parseDouble(this.tf1.getText());
            for (int n2 = 0; n > 1.0; n = Math.floor(n / 16.0), ++n2) {
                string = this.Dec2hexCheck((int)(n % 16.0)) + "" + string;
            }
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
        }
        this.tf2.setText(string);
    }
    
    public void Hextodecimal() {
        try {
            final String text = this.tf1.getText();
            this.decimal = 0;
            for (int i = text.length(), n = 0; i > 0; --i, ++n) {
                this.decimal += (int)(this.Hex2decCheck(text.substring(i - 1, i)) * Math.pow(16.0, n));
            }
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
        }
        this.tf2.setText("" + this.decimal);
    }
    
    public void Decimaltooct() {
        String string = "";
        try {
            for (int i = Integer.parseInt(this.tf1.getText()), n = 0; i > 0; i /= 8, ++n) {
                string = i % 8 + string;
            }
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
        }
        this.tf2.setText(string);
    }
    
    public void Octtodecimal() {
        try {
            this.total = 0;
            int int1 = Integer.parseInt(this.tf1.getText());
            final int length = Integer.toString(int1).length();
            this.dig = 0;
            for (int i = 0; i < length; ++i) {
                this.dig = int1 % 10;
                int1 /= 10;
                this.total += (int)(this.dig * Math.pow(8.0, i));
            }
            this.tf2.setText(this.total + "");
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
        }
    }
    
    public boolean Checker() {
        try {
            if (this.frommenu.getSelectedItem() == "Binary") {
                int int1 = Integer.parseInt(this.tf1.getText());
                while (Integer.toString(int1).length() > 1) {
                    int n;
                    if (int1 % 10 == 0 || int1 % 10 == 1) {
                        n = 0;
                    }
                    else {
                        this.tf1.setText("Bad Number");
                        n = 1;
                    }
                    int1 /= 10;
                    if (n == 1) {
                        return false;
                    }
                }
            }
            if (this.frommenu.getSelectedItem() == "Octal") {
                int int2 = Integer.parseInt(this.tf1.getText());
                while (Integer.toString(int2).length() > 1) {
                    int n2;
                    if (int2 % 10 == 0 || int2 % 10 == 1 || int2 % 10 == 2 || int2 % 10 == 3 || int2 % 10 == 4 || int2 % 10 == 5 || int2 % 10 == 6 || int2 % 10 == 7) {
                        n2 = 0;
                    }
                    else {
                        this.tf1.setText("Bad Number");
                        n2 = 1;
                    }
                    int2 /= 10;
                    if (n2 == 1) {
                        return false;
                    }
                }
            }
            if (this.frommenu.getSelectedItem() == "Decimal") {}
            if (this.frommenu.getSelectedItem() == "Hexadecimal") {}
        }
        catch (Exception ex) {
            this.tf1.setText("Bad Number");
            return false;
        }
        return true;
    }
    
    public ProApplet() {
        this.dig = 0;
        this.total = 0;
        this.tf1 = new JTextField(10);
        this.tf2 = new JTextField(10);
        this.b1 = new JButton("CALCULATE");
        this.frommenulist = new String[] { "Select One", "Binary", "Octal", "Decimal", "Hexadecimal" };
        this.tomenulist = new String[] { "Select One", "Binary", "Octal", "Decimal", "Hexadecimal" };
        this.frommenu = new JComboBox((E[])this.frommenulist);
        this.tomenu = new JComboBox((E[])this.tomenulist);
        this.c = this.getContentPane();
    }
}
