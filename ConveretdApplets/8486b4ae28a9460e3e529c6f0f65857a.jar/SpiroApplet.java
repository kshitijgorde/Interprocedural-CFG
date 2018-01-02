import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.net.URL;
import java.awt.Frame;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpiroApplet extends Applet implements ActionListener, Observer
{
    ControlPanel cp;
    DesignPanel dp;
    Frame mainFrame;
    int newWinX;
    int newWinY;
    URL baseURL;
    SaveManager savemgr;
    boolean listEm;
    Font btnFont;
    Font lblFont;
    int[] sizes;
    String[] sizeNames;
    public int canvasesUp;
    static Frame f;
    static SpiroApplet sa;
    
    public SpiroApplet() {
        this.baseURL = null;
        this.savemgr = null;
        this.sizes = new int[] { 512, 640, 800, 1024 };
        this.sizeNames = new String[] { "Small", "Medium", "Large", "Huge" };
        this.canvasesUp = 0;
    }
    
    public SaveManager getSaveMgr() {
        if (this.savemgr == null) {
            this.savemgr = new CGISaveManager();
            if (((CGISaveManager)this.savemgr).setURL(this.getBaseURL(), null)) {
                this.savemgr.addObserver(this);
            }
            else {
                this.savemgr = null;
            }
        }
        return this.savemgr;
    }
    
    public URL getBaseURL() {
        if (this.baseURL == null) {
            String s = this.getParameter("baseURL");
            if (s == null) {
                s = this.getParameter("CGIURL");
            }
            if (s == null) {
                s = this.getParameter("SpiroURL");
            }
            if (s != null) {
                try {
                    this.baseURL = new URL(s);
                }
                catch (Exception ex) {
                    System.out.println("Base URL failed: " + ex);
                }
            }
        }
        return this.baseURL;
    }
    
    public void init() {
        this.btnFont = new Font("SansSerif", 1, 14);
        this.lblFont = new Font("SansSerif", 2, 10);
        this.newWinX = 14;
        this.newWinY = 14;
        this.setLayout(new GridLayout(0, 1, 2, 2));
        this.setBackground(new Color(16764057));
        for (int i = 0; i < this.sizes.length; ++i) {
            final Panel panel = new Panel();
            panel.setLayout(new FlowLayout(0));
            final Button button = new Button(this.sizeNames[i]);
            button.setFont(this.btnFont);
            button.setActionCommand(this.sizeNames[i]);
            button.addActionListener(this);
            panel.add(button);
            final Label label = new Label(" (" + this.sizes[i] + "x" + this.sizes[i] + ")", 2);
            label.setFont(this.lblFont);
            panel.add(label);
            this.add(panel);
        }
        try {
            String s = this.getParameter("list");
            if (s == null) {
                s = this.getParameter("preset");
            }
            if (s == null) {
                s = this.getParameter("presetlist");
            }
            if (s == null || s.equals("0") || s.equals("false")) {
                this.listEm = false;
            }
            else {
                this.listEm = true;
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        String actionCommand;
        int n;
        for (actionCommand = actionEvent.getActionCommand(), n = 0; n < this.sizes.length && !this.sizeNames[n].equals(actionCommand); ++n) {}
        final int n2 = this.sizes[n];
        final Frame frame = new Frame("Spirograph " + actionCommand + " Design  (" + n2 + "x" + n2 + ")");
        final DesignPanel designPanel = new DesignPanel(n2, this.getSaveMgr());
        frame.add("Center", designPanel);
        final ControlPanel controlPanel = new ControlPanel(designPanel, n2, frame, this, this.listEm);
        frame.add("West", controlPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocation(this.newWinX, this.newWinY);
        this.newWinX += 12;
        this.newWinY += 10;
        ++this.canvasesUp;
        frame.show();
        controlPanel.applyPreset(0);
    }
    
    public void update(final Observable observable, final Object o) {
        if (o != null && o instanceof URL) {
            this.getAppletContext().showDocument((URL)o, "sd");
        }
    }
    
    public static void main(final String[] array) {
        SpiroApplet.f = new Frame("Spiro 0.99");
        SpiroApplet.sa = new SpiroApplet();
        SpiroApplet.sa.savemgr = new FileSaveManager();
        if (array.length > 0) {
            SpiroApplet.sa.listEm = true;
        }
        SpiroApplet.f.add("Center", SpiroApplet.sa);
        SpiroApplet.f.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                if (SpiroApplet.sa.canvasesUp > 0) {
                    try {
                        Toolkit.getDefaultToolkit().beep();
                    }
                    catch (SecurityException ex) {}
                }
                else {
                    System.exit(0);
                }
            }
        });
        SpiroApplet.sa.init();
        SpiroApplet.sa.start();
        SpiroApplet.f.pack();
        SpiroApplet.f.show();
    }
}
