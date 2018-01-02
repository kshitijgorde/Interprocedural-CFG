import java.applet.Applet;
import java.awt.Container;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class DJSearch extends baseApplet
{
    String[] URL;
    DJSearch.Cnv canvas;
    Image offscreenImage;
    Image bg;
    Graphics offG;
    Panel panel;
    MediaTracker mt;
    Image logo;
    boolean startup;
    int startpos;
    int fsize;
    TextField text;
    Button selectButton;
    Choice engine;
    
    public void init() {
        super.init();
        ((Container)this).setLayout(null);
        ((Component)this).setSize(super.width, super.height);
        this.fsize = super.appletFontSize * 2;
        final int bs = this.getParameterInt("bordersize", 0);
        ((Component)this.canvas).setBounds(bs, bs, super.width - bs - bs, super.height - bs - bs);
        this.logo = ((Applet)this).getImage(((Applet)this).getDocumentBase(), this.getParameterString("logo", (String)null));
        ((Component)this.canvas).setBackground(super.foregroundColor);
        this.bg = ((Applet)this).getImage(((Applet)this).getDocumentBase(), this.getParameterString("bg", (String)null));
        if (this.bg != null) {
            this.mt.addImage(((Applet)this).getImage(((Applet)this).getDocumentBase(), ((Applet)this).getParameter("bg")), 0);
        }
        if (this.logo != null) {
            this.mt.addImage(((Applet)this).getImage(((Applet)this).getDocumentBase(), ((Applet)this).getParameter("logo")), 1);
        }
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error Loading Images");
        }
        this.offscreenImage = ((Component)this).createImage(((Component)this.canvas).getSize().width, ((Component)this.canvas).getSize().height);
        this.offG = this.offscreenImage.getGraphics();
        this.canvas.paintFirst();
        ((Container)this).add((Component)this.canvas);
        ((Container)this).add(this.text);
        this.text.setFont(new Font("Dialog", 0, super.appletFontSize));
        this.text.setBounds(10, this.startpos, 250, this.fsize);
        this.selectButton.setLabel("Search");
        ((Container)this).add(this.selectButton);
        this.selectButton.setBackground(Color.lightGray);
        this.selectButton.setBounds(10, this.startpos + 30, 65, this.fsize);
        this.text.setBackground(Color.white);
        this.selectButton.setFont(new Font("Dialog", 0, super.appletFontSize));
        ((Container)this).add(this.engine);
        this.engine.setFont(new Font("Dialog", 0, super.appletFontSize));
        this.engine.setBounds(85, this.startpos + 30, 170, this.fsize);
        final DJSearch.SymMouse aSymMouse = new DJSearch.SymMouse(this);
        this.selectButton.addMouseListener((MouseListener)aSymMouse);
        this.getEngines();
        ((Container)this).add((Component)this.canvas);
        if (!this.startup) {
            this.text.setText(this.canvas.saved);
        }
    }
    
    private void gotoURL(final String urlText) {
        URL pageURL = null;
        try {
            pageURL = new URL(((Applet)this).getDocumentBase(), urlText);
        }
        catch (MalformedURLException ex) {}
        final AppletContext browser = ((Applet)this).getAppletContext();
        browser.showDocument(pageURL);
    }
    
    void getEngines() {
        for (int i = 0; i < super.engineParms.length; ++i) {
            final StringTokenizer tok = new StringTokenizer(super.engineParms[i], "|");
            try {
                this.engine.addItem(tok.nextToken().trim());
                this.URL[i] = tok.nextToken().trim();
            }
            catch (NoSuchElementException ex) {}
        }
    }
    
    public DJSearch() {
        this.URL = new String[100];
        this.canvas = new DJSearch.Cnv(this);
        this.panel = new Panel();
        this.mt = new MediaTracker((Component)this);
        this.startup = true;
        this.text = new TextField();
        this.selectButton = new Button();
        this.engine = new Choice();
    }
}
