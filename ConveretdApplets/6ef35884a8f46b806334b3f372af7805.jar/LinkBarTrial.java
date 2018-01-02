import java.awt.Event;
import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LinkBarTrial extends Applet implements Runnable
{
    int AppletWidth;
    int AppletHeight;
    int MaxHeight;
    int min;
    int n;
    int CommShow;
    int mousDown;
    BarItem[] bi;
    Graphics g;
    Color bcol;
    Color BaCol;
    String Target;
    boolean ShowHint;
    
    public void init() {
        this.g = this.getGraphics();
        final String string = this.getDocumentBase().toString();
        final String substring = string.substring(0, string.lastIndexOf(47) + 1);
        System.out.println(substring);
        this.AppletWidth = this.size().width;
        this.AppletHeight = this.size().height;
        final String parameter = this.getParameter("N");
        int n = (parameter == null) ? 1 : Integer.valueOf(parameter);
        ++n;
        ++n;
        final String parameter2 = this.getParameter("HINTS");
        this.Target = this.getParameter("TARGET");
        if (this.Target == null) {
            this.Target = "_top";
        }
        this.bcol = this.toColor(this.getParameter("BCOLOR"));
        final Color color = this.toColor(this.getParameter("ButtonColor"));
        final Color color2 = this.toColor(this.getParameter("HintColor"));
        this.BaCol = this.toColor(this.getParameter("BarColor"));
        final Color color3 = this.toColor(this.getParameter("TextColor"));
        if (parameter2 != null) {
            this.ShowHint = !parameter2.equalsIgnoreCase("no");
        }
        else {
            this.ShowHint = true;
        }
        String s = new String("");
        String parameter3 = new String("");
        final URL[] array = new URL[n];
        final Image[] array2 = new Image[n];
        int n2 = n;
        for (int i = 1; i < n; ++i) {
            try {
                s = this.getParameter("URL" + (i - 1));
                if (s == null) {
                    s = "http://www.consultcom.com/";
                }
                if (!s.substring(0, 5).equalsIgnoreCase("http:") && !s.substring(0, 4).equalsIgnoreCase("ftp:") && !s.substring(0, 7).equalsIgnoreCase("mailto:") && !s.substring(0, 5).equalsIgnoreCase("file:")) {
                    s = String.valueOf(substring) + s;
                }
                array[i] = new URL(s);
                parameter3 = this.getParameter("IMAGE" + (i - 1));
                if (parameter3 == null) {
                    parameter3 = "default.gif";
                }
                array2[i] = this.getImage(new URL(String.valueOf(substring) + parameter3));
            }
            catch (Exception ex) {
                System.out.println("Error in " + i + " Button. Incorrect URL.");
                System.out.println(String.valueOf(s) + ", " + parameter3 + ":" + array[i - 1].toString() + ", " + array2[i - 1].toString());
                --n2;
            }
        }
        this.bi = new BarItem[n2];
        String parameter4 = this.getParameter("ButtonFont");
        final String parameter5 = this.getParameter("BFontSize");
        final int fontStyle = this.toFontStyle(this.getParameter("BFontStyle"));
        if (parameter4 == null) {
            parameter4 = "Dialog";
        }
        final int n3 = (parameter5 == null) ? 10 : Integer.valueOf(parameter5);
        this.MaxHeight = this.AppletHeight - ((n3 > 16) ? (n3 + 12) : 28);
        String parameter6 = this.getParameter("HintFont");
        final String parameter7 = this.getParameter("HFontSize");
        final int fontStyle2 = this.toFontStyle(this.getParameter("HFontStyle"));
        if (parameter6 == null) {
            parameter6 = "Dialog";
        }
        final int n4 = (parameter7 == null) ? 8 : Integer.valueOf(parameter7);
        final String parameter8 = this.getParameter("Clock");
        (this.bi[0] = new BarClock(this.AppletWidth, this.MaxHeight + 3, this.AppletHeight - 3, this.g, parameter8 == null || !parameter8.equalsIgnoreCase("no"), parameter4, n3, fontStyle, parameter6, n4, fontStyle2)).setColors(color, color2, color3);
        final int n5 = this.bi[0].getX() - 2;
        final int n6 = (n2 - 1 == 0) ? n5 : (n5 / (n2 - 1));
        int n7 = 1;
        for (int j = 1; j < n; ++j) {
            if (array2[j] != null && array[j] != null) {
                String parameter9;
                String s2;
                String parameter10;
                if (j == 1) {
                    parameter9 = "LinkBar";
                    s2 = "LinkBar Trial: www.consultcom.com";
                    parameter10 = "LinkBar Trial: www.consultcom.com";
                }
                else {
                    parameter9 = this.getParameter("TEXT" + (j - 1));
                    if (parameter9 == null) {
                        parameter9 = "LinkBar";
                    }
                    s2 = this.getParameter("HINT" + (j - 1));
                    if (s2 == null) {
                        s2 = array[j].toString();
                    }
                    parameter10 = this.getParameter("STATUS" + (j - 1));
                    if (parameter10 == null) {
                        parameter10 = s2;
                    }
                }
                this.bi[n7] = new BarButton((n7 - 1) * n6 + 3, this.MaxHeight + 3, n7 * n6, this.AppletHeight - 3, array2[j], this, array[j], parameter9, s2, parameter10);
                ++n7;
            }
        }
        this.setBackground(this.bcol);
        this.min = new Date().getMinutes();
    }
    
    public Color toColor(final String s) {
        if (s == null) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.length() == 6) {
            return new Color(Integer.valueOf(s, 16));
        }
        return Color.black;
    }
    
    public int toFontStyle(final String s) {
        if (s == null) {
            return 0;
        }
        if (s.equalsIgnoreCase("plain")) {
            return 0;
        }
        if (s.equalsIgnoreCase("bold")) {
            return 1;
        }
        if (s.equalsIgnoreCase("italic")) {
            return 2;
        }
        return 0;
    }
    
    public int getObjID(final int n, final int n2) {
        for (int i = 0; i < this.bi.length; ++i) {
            if (this.bi[i].isYours(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    public void update() {
        System.out.println("Update");
        this.repaint();
    }
    
    public void Status(final int n) {
        if (n != -1) {
            if (this.ShowHint) {
                this.showStatus(this.bi[n].getStat());
                return;
            }
            this.showStatus(this.bi[n].getComment());
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mousDown > 0) {
            this.bi[this.mousDown].mouseUp(this.g);
        }
        if (this.mousDown == this.getObjID(n, n2) && this.mousDown > 0) {
            final URL clicked = this.bi[this.mousDown].clicked();
            this.mousDown = -1;
            this.getAppletContext().showDocument(clicked, this.Target);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int objID = this.getObjID(n, n2);
        if (objID > 0) {
            this.mousDown = objID;
            this.bi[objID].mouseDown(this.g);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.CommShow != -1 && this.ShowHint) {
            this.clear(this.g);
            this.CommShow = -1;
        }
        if (this.mousDown > 0) {
            this.bi[this.mousDown].mouseUp(this.g);
            this.mousDown = -1;
        }
        this.showStatus("");
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        final int objID = this.getObjID(n, n2);
        if (this.mousDown > 0 && objID != this.mousDown) {
            this.bi[this.mousDown].mouseUp(this.g);
            this.mousDown = -1;
        }
        this.Status(objID);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final int objID = this.getObjID(n, n2);
        if (objID != -1 && this.ShowHint) {
            this.bi[objID].mouseOn(this.MaxHeight, this.AppletWidth, this.AppletHeight, this.g);
            this.CommShow = objID;
        }
        this.Status(objID);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int objID = this.getObjID(n, n2);
        if (this.CommShow != objID && this.ShowHint) {
            this.clear(this.g);
            this.CommShow = -1;
        }
        if (objID != -1 && this.CommShow != objID && this.ShowHint) {
            this.bi[objID].mouseOn(this.MaxHeight, this.AppletWidth, this.AppletHeight, this.g);
            this.CommShow = objID;
        }
        this.Status(objID);
        return true;
    }
    
    public void start() {
        this.repaint();
        new Thread(this).start();
    }
    
    public void run() {
        this.repaint();
        Thread.currentThread().setPriority(1);
    Label_0011_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        final int minutes = new Date().getMinutes();
                        if (minutes != this.min) {
                            this.bi[0].Show(this.getGraphics());
                            this.min = minutes;
                        }
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                }
                catch (Exception ex) {
                    continue Label_0011_Outer;
                }
                continue;
            }
        }
    }
    
    public void repaint() {
        this.g.setColor(this.bcol);
        this.g.fillRect(0, 0, this.AppletWidth - 1, this.AppletHeight - 1);
        this.paint(this.g);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.BaCol);
        graphics.fill3DRect(0, this.MaxHeight, this.AppletWidth, this.AppletHeight, true);
        this.bi[0].Show(graphics);
        for (int i = 1; i < this.bi.length; ++i) {
            this.bi[i].Show(graphics);
        }
    }
    
    public void clear(final Graphics graphics) {
        graphics.setColor(this.bcol);
        graphics.fillRect(0, 0, this.AppletWidth, this.MaxHeight);
    }
    
    public LinkBarTrial() {
        this.MaxHeight = 45;
        this.CommShow = -1;
        this.mousDown = -1;
    }
}
