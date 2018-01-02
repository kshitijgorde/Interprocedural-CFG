import netscape.javascript.JSException;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Image;
import java.awt.FontMetrics;
import java.util.Vector;
import java.net.URL;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class navprobutton extends Canvas
{
    int level;
    private String Strdesc;
    private String Strtarget;
    private Color Clrfont;
    private String Strurl;
    private URL URLurl;
    private Color[] menucolor;
    private Color[] fontcolor;
    Vector Vtrsubmenu;
    boolean Blncurrent;
    boolean Blnbutton;
    static Color buttoncolor;
    static String textalignment;
    private static final int NORMAL = 0;
    private static final int HIGHLIGHTED = 1;
    static String linebreak;
    static String buttonshape;
    static boolean Blnborder;
    static int vspacing;
    static boolean button;
    static FontMetrics fm;
    static Image buttonimage;
    boolean imgScaled;
    Image bgimage_out;
    Image bgimage_over;
    boolean MouseOver;
    navprone AppParent;
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setBackground(this.menucolor[1]);
        this.Clrfont = this.fontcolor[1];
        this.AppParent.showStatus("Visit NavSurf.com at http://navsurf.com for more applets");
        this.MouseOver = true;
        this.repaint();
        return super.mouseEnter(event, n, n2);
    }
    
    public static void setButtonImage(final Image buttonimage) {
        navprobutton.buttonshape = null;
        navprobutton.buttonimage = buttonimage;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setBackground(this.menucolor[0]);
        this.Clrfont = this.fontcolor[0];
        this.AppParent.showStatus("");
        this.MouseOver = false;
        this.repaint();
        return super.mouseExit(event, n, n2);
    }
    
    navprobutton(final String strdesc, final Vector vtrsubmenu, final String strurl, final int level, final String strtarget, final Color[] menucolor, final Color[] fontcolor, final navprone appParent) {
        this.level = 0;
        this.Strdesc = null;
        this.Strtarget = null;
        this.Clrfont = null;
        this.Strurl = null;
        this.URLurl = null;
        this.menucolor = null;
        this.fontcolor = null;
        this.Vtrsubmenu = null;
        this.Blncurrent = false;
        this.Blnbutton = false;
        this.imgScaled = false;
        this.bgimage_out = null;
        this.bgimage_over = null;
        this.MouseOver = false;
        this.AppParent = null;
        this.Strdesc = null;
        this.Strurl = null;
        this.URLurl = null;
        this.Vtrsubmenu = null;
        this.Blncurrent = false;
        this.Blnbutton = false;
        this.AppParent = null;
        if (vtrsubmenu != null) {
            this.Blnbutton = true;
        }
        this.Strdesc = strdesc;
        this.level = level;
        this.menucolor = menucolor;
        this.fontcolor = fontcolor;
        super.setBackground(this.menucolor[0]);
        if (this.level == 1) {
            super.show();
        }
        else {
            super.hide();
        }
        this.Clrfont = this.fontcolor[0];
        this.Strurl = strurl;
        this.Vtrsubmenu = vtrsubmenu;
        this.Strtarget = strtarget;
        this.AppParent = appParent;
        try {
            this.URLurl = new URL(this.AppParent.getCodeBase(), this.Strurl);
        }
        catch (MalformedURLException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.Strdesc, navprobutton.linebreak);
        final int countTokens = stringTokenizer.countTokens();
        graphics.setColor(this.getBackground());
        if (this.MouseOver && this.bgimage_over != null) {
            graphics.drawImage(this.bgimage_over, 0, 0, this);
        }
        if (!this.MouseOver && this.bgimage_out != null) {
            graphics.drawImage(this.bgimage_out, 0, 0, this);
        }
        if (this.Blnbutton && navprobutton.button) {
            if (navprobutton.buttonshape != null) {
                if (navprobutton.buttonshape.equalsIgnoreCase("square")) {
                    graphics.setColor(navprobutton.buttoncolor);
                    graphics.fill3DRect(this.size().width - 20, 5, 15, this.size().height - 10, !this.Blncurrent);
                }
                if (navprobutton.buttonshape.equalsIgnoreCase("triangle")) {
                    final Color color = this.Blncurrent ? navprobutton.buttoncolor : navprobutton.buttoncolor.darker();
                    final Color color2 = this.Blncurrent ? navprobutton.buttoncolor.darker() : navprobutton.buttoncolor;
                    graphics.setColor(color);
                    final int n = this.size().width - 16;
                    final int n2 = this.size().height / 2;
                    final int[] array = { n, n + 12, n + 6 };
                    final int[] array2 = { n2 - 4, n2 - 4, n2 + 4 };
                    graphics.fillPolygon(array, array2, 3);
                    final int[] array3 = array;
                    final int n3 = 1;
                    --array3[n3];
                    final int[] array4 = array;
                    final int n4 = 2;
                    --array4[n4];
                    final int[] array5 = array2;
                    final int n5 = 2;
                    --array5[n5];
                    graphics.setColor(color2);
                    graphics.fillPolygon(array, array2, 3);
                }
            }
            if (navprobutton.buttonimage != null && navprobutton.buttonimage.getWidth(this) > 0 && navprobutton.buttonimage.getHeight(this) > 0) {
                if (!this.imgScaled) {
                    final float n6 = navprobutton.buttonimage.getWidth(this) / navprobutton.buttonimage.getHeight(this);
                    final int n7 = this.size().height - 2;
                    navprobutton.buttonimage = navprobutton.buttonimage.getScaledInstance((int)(n7 * n6), n7, 4);
                    this.imgScaled = true;
                }
                graphics.drawImage(navprobutton.buttonimage, this.size().width - 20, 0, this);
            }
        }
        graphics.setColor(this.getBackground());
        if (navprobutton.Blnborder) {
            if (this.Blncurrent) {
                graphics.draw3DRect(0, 0, this.size().width - 1, this.size().height - 1, false);
            }
            else {
                graphics.draw3DRect(0, 0, this.size().width - 1, this.size().height - 1, true);
            }
        }
        graphics.setColor(this.Clrfont);
        int n8 = this.size().height / 2 - (navprobutton.fm.getAscent() + navprobutton.fm.getDescent()) * countTokens / 2 + navprobutton.fm.getAscent();
        while (stringTokenizer.hasMoreTokens()) {
            final String string = "  " + stringTokenizer.nextToken() + "  ";
            int n9;
            if (navprobutton.textalignment.equals("right")) {
                n9 = super.size().width - navprobutton.fm.stringWidth(string);
            }
            else if (navprobutton.textalignment.equals("center")) {
                n9 = (super.size().width - navprobutton.fm.stringWidth(string)) / 2;
            }
            else {
                n9 = 0;
            }
            graphics.drawString(string, n9, n8);
            n8 += navprobutton.fm.getHeight();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    static {
        navprobutton.buttoncolor = new Color(255, 0, 0);
        navprobutton.textalignment = "left";
        navprobutton.linebreak = ";";
        navprobutton.buttonshape = "triangle";
        navprobutton.Blnborder = true;
        navprobutton.vspacing = 0;
        navprobutton.button = true;
        navprobutton.fm = null;
        navprobutton.buttonimage = null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        int n3 = 0;
        if (this.Vtrsubmenu != null) {
            if (this.Blncurrent) {
                this.Blncurrent = false;
                for (int i = 0; i < this.AppParent.nbv.size(); ++i) {
                    final navprobutton navprobutton = this.AppParent.nbv.elementAt(i);
                    if (navprobutton.level <= this.level && navprobutton.isVisible()) {
                        navprobutton.move(0, n3);
                        if (navprobutton.level == this.level) {
                            navprobutton.Blncurrent = false;
                        }
                        navprobutton.show();
                        n3 += navprobutton.size().height + navprobutton.vspacing;
                    }
                    else {
                        navprobutton.hide();
                        navprobutton.Blncurrent = false;
                    }
                }
                this.repaint();
            }
            else {
                for (int j = 0; j < this.AppParent.nbv.size(); ++j) {
                    final navprobutton navprobutton2 = this.AppParent.nbv.elementAt(j);
                    if (navprobutton2.equals(this)) {
                        navprobutton2.move(0, n3);
                        navprobutton2.show();
                        n3 += navprobutton2.size().height + navprobutton.vspacing;
                        for (int k = 0; k < this.Vtrsubmenu.size(); ++k) {
                            final navprobutton navprobutton3 = this.Vtrsubmenu.elementAt(k);
                            navprobutton3.move(0, n3);
                            navprobutton3.hide();
                            navprobutton3.Blncurrent = false;
                            n3 += navprobutton3.size().height + navprobutton.vspacing;
                        }
                    }
                    else if (navprobutton2.level <= this.level && navprobutton2.isVisible()) {
                        navprobutton2.move(0, n3);
                        navprobutton2.show();
                        if (navprobutton2.level == this.level) {
                            navprobutton2.Blncurrent = false;
                            if (navprobutton.buttonshape != null) {
                                navprobutton2.repaint();
                            }
                        }
                        n3 += navprobutton2.size().height + navprobutton.vspacing;
                    }
                    else {
                        navprobutton2.hide();
                    }
                    for (int l = 0; l < this.Vtrsubmenu.size(); ++l) {
                        ((navprobutton)this.Vtrsubmenu.elementAt(l)).show();
                    }
                }
                this.Blncurrent = true;
                this.repaint();
            }
        }
        if (this.Strurl != null) {
            if (this.Vtrsubmenu == null) {
                n3 = 0;
                for (int n4 = 0; n4 < this.AppParent.nbv.size(); ++n4) {
                    final navprobutton navprobutton4 = this.AppParent.nbv.elementAt(n4);
                    if (navprobutton4.level <= this.level && navprobutton4.isVisible()) {
                        if (navprobutton4.level == this.level) {
                            navprobutton4.Blncurrent = false;
                        }
                        navprobutton4.show();
                        navprobutton4.move(0, n3);
                        if (navprobutton.buttonshape != null) {
                            navprobutton4.repaint();
                        }
                        n3 += navprobutton4.size().height + navprobutton.vspacing;
                    }
                    else {
                        navprobutton4.hide();
                    }
                }
            }
            if ((this.Vtrsubmenu == null || (this.Vtrsubmenu != null && this.Blncurrent)) && !this.callJavascript(this.Strurl) && this.AppParent != null) {
                this.AppParent.getAppletContext().showDocument(this.URLurl, this.Strtarget);
            }
            if (this.Vtrsubmenu == null) {
                this.Blncurrent = true;
            }
            this.repaint();
        }
        if (this.AppParent.scrollbar_on && (this.Vtrsubmenu != null || this.Strurl != null)) {
            this.getParent().resize(this.getParent().size().width, n3);
            if (n3 > this.AppParent.size().height) {
                this.AppParent.scroller.show();
                this.AppParent.scroller.setMinimum(0);
                this.AppParent.scroller.setMaximum(n3 + 10 - this.AppParent.size().height);
            }
            else {
                this.getParent().move(0, 0);
                this.AppParent.scroller.hide();
            }
        }
        return super.mouseDown(event, n, n2);
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
