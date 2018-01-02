import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Panel;
import java.util.Random;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class imgslide extends Applet implements WindowListener
{
    static boolean isapplet;
    static String version;
    static String cfgfile;
    Frame f;
    Random rnd;
    config cfg;
    int gridx;
    int gridy;
    int leegkleurnr;
    Panel viewpanel;
    Label name;
    Label msg;
    Label copyrgt;
    control ctrl;
    public board game;
    example vb;
    boolean oldrules;
    
    public static void main(final String[] array) {
        imgslide.isapplet = false;
        imgslide.cfgfile = ((array.length > 0) ? array[0] : null);
        final imgslide imgslide = new imgslide();
        imgslide.view();
        imgslide.buildframe();
    }
    
    public void buildframe() {
        (this.f = new Frame(imgslide.version)).addWindowListener(this);
        this.f.setSize(250, 300);
        this.f.add(this);
        this.f.show();
    }
    
    public void init() {
        imgslide.isapplet = true;
        imgslide.cfgfile = this.getParameter("cfgfile");
        this.view();
    }
    
    public void view() {
        if (imgslide.cfgfile == null) {
            imgslide.cfgfile = "imgslide.cfg";
        }
        this.cfg = new config(this, imgslide.cfgfile, false);
        this.oldrules = this.cfg.getbool("OldRules");
        this.gridx = this.cfg.getint("GridX");
        this.gridy = this.cfg.getint("GridY");
        this.leegkleurnr = this.cfg.getint("FreeColor");
        final String value = this.cfg.get("Image");
        this.setLayout(new BorderLayout());
        (this.viewpanel = new Panel()).setLayout(new GridLayout(4, 1));
        (this.name = new Label("", 1)).setForeground(Color.white);
        this.name.setBackground(new Color(0, 0, 128));
        this.viewpanel.add(this.name);
        (this.msg = new Label("Welcome", 1)).setForeground(new Color(128, 0, 32));
        this.msg.setBackground(Color.white);
        this.viewpanel.add(this.msg);
        (this.copyrgt = new Label("(C)1997-2000 JBF Software", 1)).setForeground(Color.white);
        this.copyrgt.setBackground(new Color(0, 0, 128));
        this.viewpanel.add(this.copyrgt);
        this.game = new board(this, value, this.gridy, this.gridx);
        this.name.setText(String.valueOf(imgslide.version) + " " + this.game.pic.shortname());
        this.ctrl = new control(this);
        this.viewpanel.add(this.ctrl);
        this.add("North", this.viewpanel);
        this.add("Center", this.game);
        if (!imgslide.isapplet) {
            this.start();
        }
    }
    
    public void start() {
        this.game.reset();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this.vb) {
            this.game.process(this.vb.close);
        }
        if (windowEvent.getSource() == this.f) {
            System.exit(0);
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public imgslide() {
        this.rnd = new Random();
    }
    
    static {
        imgslide.version = "Slide game Version 3.02";
    }
}
