import netscape.javascript.JSException;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Event;
import java.net.URL;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class navdeluxeheader extends Canvas
{
    boolean isheader;
    boolean raised;
    boolean current;
    Color f;
    String text;
    String t;
    String u;
    URL URLurl;
    navdeluxepanel m;
    navdeluxene AppParent;
    static int NORMAL;
    static int HIGHLIGHTED;
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.current = true;
        this.raised = true;
        if (this.AppParent != null) {
            if (this.URLurl != null) {
                this.AppParent.showStatus(this.URLurl.toString());
            }
            else {
                this.AppParent.showStatus("");
            }
        }
        if (this.isheader) {
            this.f = this.AppParent.barfontcolor[navdeluxeheader.HIGHLIGHTED];
            this.setBackground(this.AppParent.barcolor[navdeluxeheader.HIGHLIGHTED]);
        }
        else {
            this.setBackground(this.AppParent.menucolor[navdeluxeheader.HIGHLIGHTED]);
            this.f = this.AppParent.fontcolor[navdeluxeheader.HIGHLIGHTED];
        }
        if (this.isheader) {
            for (int i = 0; i < this.AppParent.mp.size(); ++i) {
                ((navdeluxepanel)this.AppParent.mp.elementAt(i)).hide();
            }
        }
        else {
            final navdeluxepanel navdeluxepanel = (navdeluxepanel)this.getParent();
            for (int j = 0; j < this.AppParent.mp.size(); ++j) {
                final navdeluxepanel navdeluxepanel2 = this.AppParent.mp.elementAt(j);
                if (navdeluxepanel.menulevel < navdeluxepanel2.menulevel) {
                    navdeluxepanel2.hide();
                }
            }
        }
        if (this.m != null) {
            if (this.AppParent.ac_menuopen != null && !this.m.isVisible()) {
                this.AppParent.ac_menuopen.play();
            }
            this.raised = false;
            this.m.show();
        }
        else if (this.u != null && this.AppParent.ac_itemselect != null) {
            this.AppParent.ac_itemselect.play();
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.current = false;
        if (this.isheader) {
            this.setBackground(this.AppParent.barcolor[navdeluxeheader.NORMAL]);
            this.f = this.AppParent.barfontcolor[navdeluxeheader.NORMAL];
        }
        else {
            this.setBackground(this.AppParent.menucolor[navdeluxeheader.NORMAL]);
            this.f = this.AppParent.fontcolor[navdeluxeheader.NORMAL];
        }
        if (this.AppParent != null) {
            this.AppParent.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    navdeluxeheader(final String s, final navdeluxepanel m, final String u, final String t, final boolean isheader, final navdeluxene appParent) {
        this.isheader = false;
        this.raised = false;
        this.current = false;
        this.AppParent = null;
        this.isheader = isheader;
        this.AppParent = appParent;
        if (this.isheader) {
            super.setBackground(this.AppParent.barcolor[navdeluxeheader.NORMAL]);
            this.f = this.AppParent.barfontcolor[navdeluxeheader.NORMAL];
            this.text = "  " + s + "  ";
        }
        else {
            super.setBackground(this.AppParent.menucolor[navdeluxeheader.NORMAL]);
            this.f = this.AppParent.fontcolor[navdeluxeheader.NORMAL];
            this.text = "  " + s + "     ";
        }
        this.u = u;
        try {
            this.URLurl = new URL(this.AppParent.getCodeBase(), u);
        }
        catch (MalformedURLException ex) {}
        this.t = t;
        this.m = m;
    }
    
    public void paint(final Graphics graphics) {
        if (this.current && this.isheader && this.AppParent.barbutton != null) {
            graphics.setColor(this.AppParent.barbutton);
            graphics.draw3DRect(0, 0, super.size().width - 1, super.size().height - 1, this.raised);
        }
        graphics.setColor(this.f);
        if (!this.isheader && this.m != null) {
            final int n = this.size().width - 5;
            graphics.fillPolygon(new int[] { n, n, n + 4 }, new int[] { 4, this.size().height - 4, this.size().height / 2 }, 3);
        }
        if (this.isheader) {
            graphics.drawString(this.text, 0, this.AppParent.headerfm.getAscent());
        }
        else {
            graphics.drawString(this.text, 0, this.AppParent.menufm.getAscent());
        }
    }
    
    static {
        navdeluxeheader.NORMAL = 0;
        navdeluxeheader.HIGHLIGHTED = 1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.current = true;
        if (this.u != null) {
            this.raised = false;
            if (this.isheader) {
                this.setBackground(this.AppParent.barcolor[navdeluxeheader.HIGHLIGHTED]);
                this.f = this.AppParent.barfontcolor[navdeluxeheader.HIGHLIGHTED];
            }
            else {
                this.setBackground(this.AppParent.menucolor[navdeluxeheader.HIGHLIGHTED]);
                this.f = this.AppParent.fontcolor[navdeluxeheader.HIGHLIGHTED];
            }
            if (!this.callJavascript(this.u)) {
                this.AppParent.getAppletContext().showDocument(this.URLurl, this.t);
            }
            if (this.isheader) {
                for (int i = 0; i < this.AppParent.mp.size(); ++i) {
                    ((navdeluxepanel)this.AppParent.mp.elementAt(i)).hide();
                }
            }
        }
        if (this.m != null && this.u == null && !this.isheader) {
            if (this.m.isVisible()) {
                this.m.hide();
            }
            else {
                this.m.show();
            }
        }
        if (this.m != null && this.isheader) {
            this.current = true;
            if (this.m.isVisible()) {
                this.setBackground(this.AppParent.barcolor[navdeluxeheader.NORMAL]);
                this.m.hide();
                this.raised = true;
                this.f = this.AppParent.barfontcolor[navdeluxeheader.NORMAL];
            }
            else {
                this.setBackground(this.AppParent.barcolor[navdeluxeheader.HIGHLIGHTED]);
                this.m.show();
                this.raised = false;
                this.f = this.AppParent.barfontcolor[navdeluxeheader.HIGHLIGHTED];
            }
        }
        this.repaint();
        return true;
    }
    
    final boolean callJavascript(String s) {
        if (s.startsWith("javascript:")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(11), "(),' ");
            if (stringTokenizer.hasMoreTokens()) {
                s = stringTokenizer.nextToken().trim();
                if (s.startsWith("this.")) {
                    s = s.substring(5);
                }
                if (s.startsWith("window.")) {
                    s = s.substring(7);
                }
                try {
                    final JSObject window = JSObject.getWindow((Applet)this.AppParent);
                    final String[] array = new String[10];
                    int n = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        array[n++] = stringTokenizer.nextToken().trim();
                    }
                    window.call(s, (Object[])array);
                }
                catch (JSException ex) {
                    System.out.println("JSException encountered. Verify that MAYSCRIPT is added to the applet tag");
                }
            }
            return true;
        }
        return false;
    }
}
