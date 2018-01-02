import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollingUpdateApplet extends Applet
{
    ScrollingUpdate scroller;
    
    public void stop() {
        this.scroller.stop();
    }
    
    public void start() {
        this.scroller.start();
    }
    
    public void init() {
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("text");
        String parameter3 = this.getParameter("font");
        if (parameter3 == null) {
            parameter3 = "TimesRoman";
        }
        int n = 0;
        final String parameter4 = this.getParameter("fontStyle");
        if (parameter4 != null) {
            final String lowerCase = parameter4.toLowerCase();
            if (lowerCase.equals("bold")) {
                n = 1;
            }
            else if (lowerCase.equals("italic")) {
                n = 2;
            }
            else if (lowerCase.equals("bold+italic") || lowerCase.equals("italic+bold")) {
                n = 3;
            }
        }
        int n2 = 1;
        final String parameter5 = this.getParameter("justify");
        if (parameter5 != null) {
            final String lowerCase2 = parameter5.toLowerCase();
            if (lowerCase2.equals("center")) {
                n2 = 2;
            }
            else if (lowerCase2.equals("right")) {
                n2 = 3;
            }
        }
        boolean equals = false;
        final String parameter6 = this.getParameter("notifyOnRead");
        if (parameter6 != null) {
            equals = parameter6.equals("true");
        }
        int int1 = 8;
        int int2 = 600;
        int int3 = 14;
        Color black = Color.black;
        int int4 = 60;
        int int5 = 0;
        int int6 = 0;
        int int7 = 50;
        int int8 = 50;
        try {
            final String parameter7 = this.getParameter("maxSizeKB");
            if (parameter7 != null) {
                int1 = Integer.parseInt(parameter7);
            }
            final String parameter8 = this.getParameter("refresh");
            if (parameter8 != null) {
                int2 = Integer.parseInt(parameter8);
            }
            final String parameter9 = this.getParameter("fontSize");
            if (parameter9 != null) {
                int3 = Integer.parseInt(parameter9);
            }
            final String parameter10 = this.getParameter("fontColor");
            if (parameter10 != null) {
                black = new Color(Integer.parseInt(parameter10, 16));
            }
            final String parameter11 = this.getParameter("frameDelay");
            if (parameter11 != null) {
                int4 = Integer.parseInt(parameter11);
            }
            final String parameter12 = this.getParameter("scrollRectX");
            if (parameter12 != null) {
                int5 = Integer.parseInt(parameter12);
            }
            final String parameter13 = this.getParameter("scrollRectY");
            if (parameter13 != null) {
                int6 = Integer.parseInt(parameter13);
            }
            final String parameter14 = this.getParameter("scrollRectW");
            if (parameter14 != null) {
                int7 = Integer.parseInt(parameter14);
            }
            final String parameter15 = this.getParameter("scrollRectH");
            if (parameter15 != null) {
                int8 = Integer.parseInt(parameter15);
            }
        }
        catch (Exception ex2) {
            System.out.println("[UpdateApplet] Numeric parameter error in HTML");
        }
        final Rectangle rectangle = new Rectangle(int5, int6, int7, int8);
        try {
            this.scroller = new ScrollingUpdate(this.getCodeBase(), parameter2, int1, int2, equals, parameter3, int3, n, black, n2, int4, parameter, rectangle);
            this.setLayout(null);
            this.add(this.scroller);
        }
        catch (MalformedURLException ex) {
            System.out.println("[UpdateApplet] ScrollingUpdate reported MalformedURLException ");
            System.out.println(ex);
        }
    }
}
