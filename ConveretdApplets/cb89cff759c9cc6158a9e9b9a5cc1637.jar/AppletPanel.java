import java.awt.Image;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.util.Hashtable;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletPanel extends Panel
{
    CMFrame cmf;
    CascadeMenu cm;
    Hashtable ht;
    MenuSet ms;
    
    public AppletPanel(final CMFrame cmf) {
        this.setLayout(null);
        this.cmf = cmf;
        this.setBackground(Color.lightGray);
    }
    
    public void updateYourself(final Hashtable ht, final MenuSet ms) {
        System.out.println("*** updateYourself***");
        this.ht = ht;
        this.ms = ms;
        if (this.cm != null) {
            this.remove(this.cm);
        }
        this.cm = null;
        (this.cm = new CascadeMenu(this)).setBounds(10, 0, Integer.parseInt(ht.get("applet_width")), Integer.parseInt(ht.get("applet_height")));
        this.cm.init();
        this.add(this.cm);
    }
    
    public Image getImage(final URL url, final String s) {
        return this.cmf.cme.getImage(url, s);
    }
    
    public MenuSet getTree(final String s, final boolean b) {
        System.out.println("GetTree: " + this.ms);
        return this.ms;
    }
    
    public void showDocument(final URL url, final String s) {
        this.cmf.cme.getAppletContext().showDocument(url, s);
    }
    
    public void showStatus(final String s) {
        this.cmf.cme.getAppletContext().showStatus(s);
    }
    
    public String getParameter(final String s) {
        return this.ht.get(s.toLowerCase());
    }
    
    public URL getCodeBase() {
        return this.cmf.cme.getCodeBase();
    }
}
