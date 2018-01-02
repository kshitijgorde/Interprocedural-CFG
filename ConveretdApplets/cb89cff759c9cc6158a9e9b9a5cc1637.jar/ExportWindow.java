import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class ExportWindow extends Frame implements ActionListener, WindowListener
{
    WorkPanel wp;
    CMFrame cmf;
    TextArea data;
    Button ok;
    String dicon;
    String dtarget;
    Label help1;
    Label help2;
    Canvas cc;
    
    public ExportWindow(final WorkPanel wp, final CMFrame cmf) {
        super("Export Window");
        this.data = new TextArea();
        this.ok = new Button("OK");
        this.help1 = new Label("Copy this text into a HTML document");
        this.help2 = new Label("Windows users: Press CTRL-C to copy the text");
        this.cc = new Canvas();
        this.setLayout(null);
        this.wp = wp;
        this.cmf = cmf;
        this.setBackground(Color.lightGray);
        this.data.setBounds(30, 30, 540, 250);
        this.add(this.data);
        this.help1.setBounds(30, 300, 540, 30);
        this.add(this.help1);
        this.help2.setBounds(30, 330, 540, 30);
        this.add(this.help2);
        this.ok.setBounds(30, 360, 540, 30);
        this.add(this.ok);
        this.addWindowListener(this);
        this.ok.addActionListener(this);
        this.addMaindata();
        this.data.requestFocus();
        this.data.selectAll();
        this.data.setEditable(false);
    }
    
    private void addMaindata() {
        final Hashtable data = this.wp.extractData();
        this.dicon = data.get("default_icon");
        this.dtarget = data.get("default_target");
        final String s = data.get("registrationkey");
        data.remove("regsitartionkey");
        final String s2 = "<param name=\"";
        this.data.append("<applet code=\"CascadeMenu.class\" archive=\"CascadeMenu.jar\"");
        this.data.append(" width=\"" + data.get("applet_width") + "\" height=\"" + data.get("applet_height") + "\">\n");
        this.data.append(s2 + "RegistrationKey" + "\" value=\"" + s + "\">\n");
        data.remove("applet_width");
        data.remove("applet_height");
        final Enumeration keys = data.keys();
        while (keys.hasMoreElements()) {
            this.data.append(s2);
            final String s3 = (String)keys.nextElement();
            this.data.append(s3 + "\" ");
            this.data.append("value=\"" + data.get(s3) + "\">\n");
        }
        this.data.append("\n");
        final MenuSet set = this.wp.getSet();
        set.reset();
        this.crawlData(set, "Item");
        this.data.append("Java is not enabled! Please visit <a href=\"http://www.realapplets.com\" target=\"_blank\">\nRealApplets</a>for support" + "\n");
        this.data.append("</applet>");
    }
    
    private void crawlData(final MenuSet set, final String s) {
        set.reset();
        int n = 1;
        final String string = s + "_";
        System.out.println("Crawling: " + string);
        while (set.hasMoreItems()) {
            final Item nextItem = set.getNextItem();
            System.out.println("Crawling: " + string + n);
            final String text = nextItem.getText();
            System.out.println("1");
            final String urlName = nextItem.getURLName();
            System.out.println("2");
            final String target = nextItem.getTarget();
            System.out.println("3");
            final String imageName = nextItem.getImageName();
            System.out.println("4");
            this.data.append("<param name=\"" + string + n + "_Text\" value=\"" + text + "\">\n");
            if (urlName != null && urlName.length() > 1) {
                this.data.append("<param name=\"" + string + n + "_Action\" value=\"" + urlName + "\">\n");
            }
            System.out.println("5");
            if (target != null && !target.equals(this.dtarget)) {
                this.data.append("<param name=\"" + string + n + "_Target\" value=\"" + target + "\">\n");
            }
            System.out.println("6");
            if (imageName != null && !imageName.equals(this.dicon)) {
                this.data.append("<param name=\"" + string + n + "_Icon\" value=\"" + imageName + "\">\n");
            }
            System.out.println("7");
            if (nextItem.hasSub()) {
                this.crawlData(nextItem.getMenuSet(), string + n);
            }
            System.out.println("8");
            ++n;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
