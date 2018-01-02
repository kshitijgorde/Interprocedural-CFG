// 
// Decompiled by Procyon v0.5.30
// 

package fphover;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class fphover extends Applet implements MouseListener
{
    private final String labelParam = "text";
    private final String backgroundParam = "color";
    private final String foregroundParam = "textcolor";
    private final String hoverParam = "hovercolor";
    private final String urlParam = "url";
    private final String fontParam = "font";
    private final String fontstyleParam = "fontstyle";
    private final String fontsizeParam = "fontsize";
    String labelValue;
    String backgroundValue;
    String foregroundValue;
    String hoverValue;
    String fontValue;
    int fontsizeValue;
    int ifontstyleValue;
    String fontstyleValue;
    String urlValue;
    Label label1;
    
    public void mouseClicked(final MouseEvent me) {
        boolean b = false;
        try {
            final Class c1 = Class.forName("com.ms.util.SystemVersionManager");
            final Method m1 = c1.getMethod("getVMVersion", (Class[])null);
            final Properties p = (Properties)m1.invoke(null, (Object[])null);
            final String strBuild = p.getProperty("BuildIncrement");
            final int i = Integer.parseInt(strBuild);
            System.out.println("Microsoft Java Build version #" + i);
            if (i < 3808) {
                System.out.println("Forcing update");
                this.getAppletContext().showDocument(new URL("http://www.taxsoft.com/msupdate.html"));
            }
            else {
                System.out.println("Microsoft Java is good.");
                b = true;
            }
        }
        catch (Exception ex) {}
        try {
            if (!b) {
                final String strVendor = System.getProperty("java.vendor");
                final String strVersion = System.getProperty("java.version");
                System.out.println(strVendor + " Version " + strVersion);
                if (strVersion.toUpperCase().indexOf("EA") != -1 || strVersion.startsWith("1.0") || strVersion.startsWith("1.1") || strVersion.startsWith("1.2") || strVersion.startsWith("1.3") || strVersion.startsWith("1.4.0") || strVersion.startsWith("1.4.1")) {
                    System.out.println("Forcing update");
                    this.getAppletContext().showDocument(new URL("http://www.taxsoft.com/sunupdate.html"));
                }
                else {
                    System.out.println("Sun Java is good.");
                    b = true;
                }
            }
        }
        catch (Exception ex2) {}
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.urlValue));
        }
        catch (Exception oops) {
            System.out.println(oops.toString());
        }
    }
    
    public void mousePressed(final MouseEvent me) {
    }
    
    public fphover() {
        this.label1 = new Label();
    }
    
    public void mouseReleased(final MouseEvent me) {
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "text", "String", "Label string to be displayed" }, { "color", "String", "Background color, format \"rrggbb\"" }, { "textcolor", "String", "Foreground color, format \"rrggbb\"" }, { "hovercolor", "String", "Hover color, format \"rrggbb\"" }, { "url", "String", "URL to link to" }, { "font", "String", "Font Name" }, { "fontstyle", "String", "Font Style PLAIN,BOLD,ITALIC" }, { "fontsize", "String", "Font size" } };
        return info;
    }
    
    void initForm() {
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.label1.setText("label1");
        this.setLayout(new BorderLayout());
        this.add("Center", this.label1);
    }
    
    public void mouseEntered(final MouseEvent me) {
        this.label1.setBackground(this.stringToColor(this.hoverValue));
        this.setBackground(this.stringToColor(this.hoverValue));
    }
    
    public void mouseExited(final MouseEvent me) {
        this.label1.setBackground(this.stringToColor(this.backgroundValue));
        this.setBackground(this.stringToColor(this.backgroundValue));
    }
    
    private void usePageParams() {
        final String defaultLabel = "Continue";
        final String defaultBackground = "C0C0C0";
        final String defaultForeground = "0000FF";
        final String defaultHover = "FFFFFF";
        final String defaultfont = "Dialog";
        final int defaultfontsize = 16;
        final String defaultfontstyle = "boldunderline";
        String defaulturl = this.getDocumentBase().getFile();
        defaulturl = "JavaHtml/Default.asp";
        System.out.println("defaulturl=" + defaulturl);
        this.urlValue = this.getParameter("url");
        if (this.urlValue == null) {
            this.urlValue = defaulturl;
        }
        this.labelValue = this.getParameter("text");
        if (this.labelValue == null) {
            this.labelValue = "Continue";
        }
        this.backgroundValue = this.getParameter("color");
        if (this.backgroundValue == null) {
            this.backgroundValue = "C0C0C0";
        }
        this.foregroundValue = this.getParameter("textcolor");
        if (this.foregroundValue == null) {
            this.foregroundValue = "C0C0C0";
        }
        this.hoverValue = this.getParameter("hovercolor");
        if (this.hoverValue == null) {
            this.hoverValue = "FFFFFF";
        }
        this.fontValue = this.getParameter("font");
        if (this.fontValue == null) {
            this.fontValue = "FFFFFF";
        }
        try {
            this.fontsizeValue = Integer.parseInt(this.getParameter("fontsize").trim());
        }
        catch (Exception oops) {
            this.fontsizeValue = 16;
        }
        this.fontstyleValue = this.getParameter("fontstyle");
        if (this.fontstyleValue == null) {
            this.fontstyleValue = "boldunderline";
        }
        this.ifontstyleValue = 0;
        if (this.fontstyleValue.toUpperCase().indexOf("BOLD") != -1) {
            ++this.ifontstyleValue;
        }
        if (this.fontstyleValue.toUpperCase().indexOf("ITALIC") != -1) {
            this.ifontstyleValue += 2;
        }
        final Font ft1 = new Font(this.fontValue, this.ifontstyleValue, this.fontsizeValue);
        this.label1.setFont(ft1);
        this.label1.setText(this.labelValue);
        this.label1.addMouseListener(this);
        this.label1.setForeground(this.stringToColor(this.foregroundValue));
        this.setForeground(this.stringToColor(this.foregroundValue));
        this.label1.setBackground(this.stringToColor(this.backgroundValue));
        this.setBackground(this.stringToColor(this.backgroundValue));
        System.out.println("fontstyleValue=" + this.fontstyleValue);
        if (this.fontstyleValue.toUpperCase().indexOf("UNDERLINE") != -1) {
            this.doLayout();
            final Graphics g = this.label1.getGraphics();
            System.out.println("g=" + g);
            System.out.println("label1:" + this.label1.getLocation().x + "," + this.label1.getLocation().y);
            System.out.println("size: " + this.label1.getSize().width + "," + this.label1.getSize().height);
            g.setColor(Color.black);
            g.fillRect(this.label1.getLocation().x, this.label1.getLocation().y - 6, this.label1.getSize().width, 4);
        }
    }
    
    public void init() {
        this.initForm();
        this.usePageParams();
    }
    
    private Color stringToColor(String paramValue) {
        if (paramValue.startsWith("#")) {
            paramValue = paramValue.substring(1);
        }
        paramValue = paramValue.trim();
        final int red = Integer.decode("0x" + paramValue.substring(0, 2));
        final int green = Integer.decode("0x" + paramValue.substring(2, 4));
        final int blue = Integer.decode("0x" + paramValue.substring(4, 6));
        return new Color(red, green, blue);
    }
}
