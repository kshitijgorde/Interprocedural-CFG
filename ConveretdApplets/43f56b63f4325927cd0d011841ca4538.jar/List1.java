import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Button;
import java.awt.GridLayout;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class List1 extends Applet implements ActionListener, MouseListener
{
    private URL finalurl;
    private String desturl1;
    private String desturl2;
    private String desturl3;
    private String desturl4;
    private String desturl5;
    private String author;
    private int totbutton;
    private GridLayout layout;
    private String caption1;
    private String caption2;
    private String caption3;
    private String caption4;
    private String caption5;
    private Button but1;
    private Button but2;
    private Button but3;
    private Button but4;
    private Button but5;
    private Font bfont;
    int bfontSize;
    int bfontStyle;
    String bfontName;
    Color cb;
    Color cf;
    Color cfm;
    Color cbm;
    
    public List1() {
        this.totbutton = 0;
        this.but1 = new Button("order");
        this.but2 = new Button("order");
        this.but3 = new Button("order");
        this.but4 = new Button("order");
        this.but5 = new Button("order");
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String s = ((Button)actionEvent.getSource()).getLabel();
        if (s == this.caption1) {
            s = this.desturl1;
        }
        if (s == this.caption2) {
            s = this.desturl2;
        }
        if (s == this.caption3) {
            s = this.desturl3;
        }
        if (s == this.caption4) {
            s = this.desturl4;
        }
        if (s == this.caption5) {
            s = this.desturl5;
        }
        this.gotourl(s);
    }
    
    public void gotourl(final String s) {
        String parameter = this.getParameter("targetframe");
        if (parameter == null) {
            parameter = "_self";
        }
        System.out.println("Action");
        try {
            this.finalurl = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL!");
        }
        this.getAppletContext().showDocument(this.finalurl, parameter);
    }
    
    public void init() {
        if (this.getParameter("fontname") == null) {
            this.bfontName = "TimesRoman";
        }
        else {
            this.bfontName = this.getParameter("fontname");
        }
        if (this.getParameter("fontsize") == null) {
            this.bfontSize = 12;
        }
        else {
            this.bfontSize = Integer.parseInt(this.getParameter("fontsize"));
        }
        if (this.getParameter("bold") == null) {
            this.bfontStyle = 0;
        }
        else if (this.getParameter("bold").trim().toUpperCase().intern() == "YES".intern()) {
            this.bfontStyle = 1;
        }
        if (this.getParameter("bcolor") == null) {
            this.cb = this.getBackground();
        }
        else if (this.getParameter("bcolor").trim().toUpperCase().intern() == "R".intern()) {
            this.cb = Color.red;
        }
        else if (this.getParameter("bcolor").trim().toUpperCase().intern() == "B".intern()) {
            this.cb = Color.blue;
        }
        else if (this.getParameter("bcolor").trim().toUpperCase().intern() == "W".intern()) {
            this.cb = Color.white;
        }
        else if (this.getParameter("bcolor").trim().toUpperCase().intern() == "Y".intern()) {
            this.cb = Color.yellow;
        }
        else if (this.getParameter("bcolor").trim().toUpperCase().intern() == "G".intern()) {
            this.cb = Color.green;
        }
        if (this.getParameter("fcolor") == null) {
            this.cf = this.getForeground();
        }
        else if (this.getParameter("fcolor").trim().toUpperCase().intern() == "R".intern()) {
            this.cf = Color.red;
        }
        else if (this.getParameter("fcolor").trim().toUpperCase().intern() == "B".intern()) {
            this.cf = Color.blue;
        }
        else if (this.getParameter("fcolor").trim().toUpperCase().intern() == "W".intern()) {
            this.cf = Color.white;
        }
        else if (this.getParameter("fcolor").trim().toUpperCase().intern() == "Y".intern()) {
            this.cf = Color.yellow;
        }
        else if (this.getParameter("fcolor").trim().toUpperCase().intern() == "G".intern()) {
            this.cf = Color.green;
        }
        if (this.getParameter("fmcolor") == null) {
            this.cfm = this.cf;
        }
        else if (this.getParameter("fmcolor").trim().toUpperCase().intern() == "R".intern()) {
            this.cfm = Color.red;
        }
        else if (this.getParameter("fmcolor").trim().toUpperCase().intern() == "B".intern()) {
            this.cfm = Color.blue;
        }
        else if (this.getParameter("fmcolor").trim().toUpperCase().intern() == "W".intern()) {
            this.cfm = Color.white;
        }
        else if (this.getParameter("fmcolor").trim().toUpperCase().intern() == "Y".intern()) {
            this.cfm = Color.yellow;
        }
        else if (this.getParameter("fmcolor").trim().toUpperCase().intern() == "G".intern()) {
            this.cfm = Color.green;
        }
        if (this.getParameter("bmcolor") == null) {
            this.cbm = this.cb;
        }
        else if (this.getParameter("bmcolor").trim().toUpperCase().intern() == "R".intern()) {
            this.cbm = Color.red;
        }
        else if (this.getParameter("bmcolor").trim().toUpperCase().intern() == "B".intern()) {
            this.cbm = Color.blue;
        }
        else if (this.getParameter("bmcolor").trim().toUpperCase().intern() == "W".intern()) {
            this.cbm = Color.white;
        }
        else if (this.getParameter("bmcolor").trim().toUpperCase().intern() == "Y".intern()) {
            this.cbm = Color.yellow;
        }
        else if (this.getParameter("bmcolor").trim().toUpperCase().intern() == "G".intern()) {
            this.cbm = Color.green;
        }
        this.bfont = new Font(this.bfontName, this.bfontStyle, this.bfontSize);
        this.author = this.getParameter("author");
        this.setVisible(true);
        if (this.author.trim().toUpperCase().intern() == "JACKY LEUNG") {
            this.caption1 = this.getParameter("caption1");
            this.caption2 = this.getParameter("caption2");
            this.caption3 = this.getParameter("caption3");
            this.caption4 = this.getParameter("caption4");
            this.caption5 = this.getParameter("caption5");
            if (this.caption1 != null) {
                ++this.totbutton;
            }
            if (this.caption2 != null) {
                ++this.totbutton;
            }
            if (this.caption3 != null) {
                ++this.totbutton;
            }
            if (this.caption4 != null) {
                ++this.totbutton;
            }
            if (this.caption5 != null) {
                ++this.totbutton;
            }
            final String parameter = this.getParameter("pos");
            if (parameter != null) {
                if (parameter.charAt(0) == "V".charAt(0)) {
                    this.layout = new GridLayout(this.totbutton, 1);
                }
                if (parameter.charAt(0) == "H".charAt(0)) {
                    this.layout = new GridLayout(1, this.totbutton);
                }
            }
            else {
                this.layout = new GridLayout(this.totbutton, 1);
            }
            this.setLayout(this.layout);
            if (this.caption1 != null) {
                this.add(this.but1);
                this.but1.setLabel(this.caption1);
                this.but1.setBackground(this.cb);
                this.but1.setForeground(this.cf);
                this.but1.setFont(this.bfont);
                this.but1.addMouseListener(this);
                this.but1.addActionListener(this);
                this.desturl1 = this.getParameter("dest1");
            }
            if (this.caption2 != null) {
                this.add(this.but2);
                this.but2.setLabel(this.caption2);
                this.but2.setBackground(this.cb);
                this.but2.setForeground(this.cf);
                this.but2.setFont(this.bfont);
                this.but2.addActionListener(this);
                this.but2.addMouseListener(this);
                this.desturl2 = this.getParameter("dest2");
            }
            if (this.caption3 != null) {
                this.add(this.but3);
                this.but3.setLabel(this.caption3);
                this.but3.setBackground(this.cb);
                this.but3.setForeground(this.cf);
                this.but3.setFont(this.bfont);
                this.but3.addActionListener(this);
                this.but3.addMouseListener(this);
                this.desturl3 = this.getParameter("dest3");
            }
            if (this.caption4 != null) {
                this.add(this.but4);
                this.but4.setLabel(this.caption4);
                this.but4.setBackground(this.cb);
                this.but4.setForeground(this.cf);
                this.but4.setFont(this.bfont);
                this.but4.addActionListener(this);
                this.but4.addMouseListener(this);
                this.desturl4 = this.getParameter("dest4");
            }
            if (this.caption5 != null) {
                this.add(this.but5);
                this.but5.setLabel(this.caption5);
                this.but5.setBackground(this.cb);
                this.but5.setForeground(this.cf);
                this.but5.setFont(this.bfont);
                this.but5.addActionListener(this);
                this.but5.addMouseListener(this);
                this.desturl5 = this.getParameter("dest5");
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final Button button = (Button)mouseEvent.getSource();
        button.setForeground(this.cfm);
        button.setBackground(this.cbm);
        button.setLabel(button.getLabel());
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final Button button = (Button)mouseEvent.getSource();
        button.setForeground(this.cf);
        button.setBackground(this.cb);
        button.setLabel(button.getLabel());
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
