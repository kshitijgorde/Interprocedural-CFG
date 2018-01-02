import java.awt.Dimension;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RegEncoder extends Applet implements ActionListener
{
    private Banner ban;
    public TextArea detext;
    public TextField eentext;
    public Button codeer;
    public Button texteer;
    public Button cleareer;
    public Button backeer;
    public String oudetekst;
    public static final int KOL = 50;
    public Color achterkleur;
    public Color voorkleur;
    public Label passel;
    public Label bovenel;
    
    public void init() {
        final String parameter = this.getParameter("BgColor");
        this.achterkleur = ((parameter == null) ? Color.black : new Color(Integer.parseInt(parameter, 16)));
        final String parameter2 = this.getParameter("FgColor");
        this.voorkleur = ((parameter2 == null) ? Color.green : new Color(Integer.parseInt(parameter2, 16)));
        this.setBackground(this.achterkleur);
        this.setForeground(this.voorkleur);
        this.oudetekst = "";
        this.detext = new TextArea();
        this.eentext = new TextField();
        this.cleareer = new Button(this.getParameter("Button1"));
        this.backeer = new Button(this.getParameter("Button2"));
        this.codeer = new Button(this.getParameter("Button3"));
        this.texteer = new Button(this.getParameter("Button4"));
        this.bovenel = new Label(this.getParameter("Label1"), 1);
        this.passel = new Label(this.getParameter("Label2"));
        this.setLayout(null);
        this.addBanner();
        this.bovenel.setBounds(10, 10, 400, 20);
        this.detext.setBounds(10, 40, 400, 400);
        this.detext.setBackground(Color.white);
        this.detext.setForeground(Color.black);
        this.eentext.setBounds(70, 450, 210, 20);
        this.eentext.setBackground(Color.white);
        this.eentext.setForeground(Color.black);
        this.passel.setBounds(10, 450, 60, 20);
        this.cleareer.setBounds(290, 450, 50, 20);
        this.cleareer.addActionListener(this);
        this.cleareer.setForeground(Color.black);
        this.backeer.setBounds(350, 450, 50, 20);
        this.backeer.addActionListener(this);
        this.backeer.setForeground(Color.black);
        this.codeer.setBounds(10, 480, 195, 20);
        this.codeer.addActionListener(this);
        this.codeer.setForeground(Color.black);
        this.texteer.setBounds(215, 480, 195, 20);
        this.texteer.addActionListener(this);
        this.texteer.setForeground(Color.black);
        this.add(this.detext);
        this.add(this.eentext);
        this.add(this.bovenel);
        this.add(this.passel);
        this.add(this.cleareer);
        this.add(this.backeer);
        this.add(this.codeer);
        this.add(this.texteer);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.codeer == actionEvent.getSource()) {
            this.oudetekst = this.detext.getText();
            this.detext.setText(this.getParameter("Wait"));
            this.detext.setText(this.vercode(this.oudetekst, this.vertaal(this.eentext.getText())));
            return;
        }
        if (this.texteer == actionEvent.getSource()) {
            this.oudetekst = this.detext.getText();
            this.detext.setText(this.getParameter("Wait"));
            this.detext.setText(this.verdecode(this.oudetekst, this.vertaal(this.eentext.getText())));
            return;
        }
        if (this.cleareer == actionEvent.getSource()) {
            this.oudetekst = this.detext.getText();
            this.detext.setText("");
            return;
        }
        final String text = this.detext.getText();
        this.detext.setText(this.oudetekst);
        this.oudetekst = text;
    }
    
    public String vercode(final String s, final int[] array) {
        final Random random = new Random();
        String s2 = "000";
        int n = 3;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final int n2 = char1 + array[i % array.length] * (i % 12 + 1);
            String s3 = String.valueOf(s2) + (n2 / 100 - n2 / 1000 * 10);
            if (n++ >= 50) {
                s3 = String.valueOf(s3) + "\n";
                n = 0;
            }
            String s4 = String.valueOf(s3) + (n2 / 10 - n2 / 100 * 10);
            if (n++ >= 50) {
                s4 = String.valueOf(s4) + "\n";
                n = 0;
            }
            s2 = String.valueOf(s4) + (n2 - n2 / 10 * 10);
            if (n++ >= 50) {
                s2 = String.valueOf(s2) + "\n";
                n = 0;
            }
            for (char c = (char)(char1 % '\u0003'), c2 = '\0'; c2 < c; ++c2) {
                final int n3 = random.nextInt() % 10;
                s2 = String.valueOf(s2) + ((n3 >= 0) ? n3 : (-n3));
                if (n++ >= 50) {
                    s2 = String.valueOf(s2) + "\n";
                    n = 0;
                }
            }
        }
        return String.valueOf(s2) + "000";
    }
    
    public String verdecode(String string, final int[] array) {
        String string2 = "";
        int n = 0;
        int i = 0;
        try {
            while (i + 2 < string.length() && (string.charAt(i) != '0' || string.charAt(i + 1) != '0' || string.charAt(i + 2) != '0')) {
                ++i;
            }
            if (i + 2 < string.length()) {
                for (i += 3; i < string.length(); ++i) {
                    final char char1 = string.charAt(i);
                    if (char1 >= '0' && char1 <= '9') {
                        string2 = String.valueOf(string2) + char1;
                    }
                }
                string = "";
                int n3;
                for (int n2 = 0; n2 + 2 < string2.length() && (string2.charAt(n2) != '0' || string2.charAt(n2 + 1) != '0' || string2.charAt(n2 + 2) != '0'); n2 += 3 + n3 % 3, string = String.valueOf(string) + (char)n3, ++n) {
                    n3 = (string2.charAt(n2) - '0') * 'd' + (string2.charAt(n2 + 1) - '0') * '\n' + (string2.charAt(n2 + 2) - '0') - array[n % array.length] * (n % 12 + 1);
                }
                return string;
            }
            return this.getParameter("Error");
        }
        catch (IndexOutOfBoundsException ex) {
            return "Bug in program and text, please try again.";
        }
    }
    
    public int[] vertaal(final String s) {
        final int[] array = new int[s.length()];
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= 'A' && char1 <= 'Z') {
                array[i] = char1 - 'A' + '\u0001';
                ++n;
            }
            if (char1 >= 'a' && char1 <= 'z') {
                array[i] = char1 - 'a' + '\u0001';
                ++n;
            }
            if (char1 >= '0' && char1 <= '9') {
                array[i] = char1 - '0' + '\u0001';
                ++n;
            }
        }
        if (n > 0) {
            final int[] array2 = new int[n];
            for (int j = 0; j < n; ++j) {
                array2[j] = array[j];
            }
            return array2;
        }
        final int[] array3 = new int[5];
        for (int k = 0; k < 5; ++k) {
            array3[k] = k;
        }
        return array3;
    }
    
    public void addBanner() {
        System.out.println("\n**********************************************************************************");
        System.out.println("* Unregistered applet, written by Luna-Tic, contact at lunalifelover@hotmail.com *");
        System.out.println("* Get the registerd version without banner from http://www.realapplets.com       *");
        System.out.println("* Removal of banner without registration is punishable in a court of law.        *");
        System.out.println("**********************************************************************************\n");
        final Dimension size = this.getSize();
        (this.ban = new Banner(size.width, this)).setBounds(0, size.height - 60, size.width, 60);
        this.add(this.ban);
    }
}
