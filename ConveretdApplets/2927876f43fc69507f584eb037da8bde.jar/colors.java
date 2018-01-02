import java.applet.Applet;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class colors
{
    Color framecolor;
    Color framecolor2;
    Color bordercolor;
    Color bordercolor2;
    Color textcolor;
    int texttype;
    int speedsMax;
    int speedsNow;
    boolean linkwhole;
    boolean diagonal;
    
    colors(final Applet applet) {
        this.linkwhole = false;
        final String parameter = applet.getParameter("linkpicture");
        if (parameter != null && parameter.toLowerCase().equals("on")) {
            this.linkwhole = true;
        }
        this.diagonal = false;
        final String parameter2 = applet.getParameter("diagonal");
        if (parameter2 != null && parameter2.toLowerCase().equals("on")) {
            this.diagonal = true;
        }
        this.texttype = 0;
        final String parameter3 = applet.getParameter("texttype");
        if (parameter3 != null) {
            final int int1 = Integer.parseInt(parameter3);
            if (int1 >= 0 && int1 <= 3) {
                this.texttype = int1;
            }
        }
        this.speedsMax = 4;
        this.speedsNow = 0;
        final String parameter4 = applet.getParameter("maxspeed");
        if (parameter4 != null) {
            final int convert2Int = this.convert2Int(parameter4);
            if (convert2Int >= 0 && convert2Int < 10) {
                this.speedsMax = convert2Int;
            }
        }
        this.bordercolor = Color.white;
        this.bordercolor2 = Color.gray;
        final String parameter5 = applet.getParameter("bordercolor");
        if (parameter5 != null) {
            this.bordercolor = this.convert2Color(parameter5, this.bordercolor);
            this.bordercolor2 = new Color(this.bordercolor.getRed() >> 1, this.bordercolor.getGreen() >> 1, this.bordercolor.getBlue() >> 1);
        }
        this.framecolor = Color.white;
        this.framecolor2 = Color.gray;
        final String parameter6 = applet.getParameter("framecolor");
        if (parameter6 != null) {
            this.framecolor = this.convert2Color(parameter6, this.framecolor);
            this.framecolor2 = new Color(this.framecolor.getRed() >> 1, this.framecolor.getGreen() >> 1, this.framecolor.getBlue() >> 1);
        }
        this.textcolor = new Color(50, 50, 150);
        final String parameter7 = applet.getParameter("textcolor");
        if (parameter7 != null) {
            this.textcolor = this.convert2Color(parameter7, this.textcolor);
        }
    }
    
    Color convert2Color(final String s, final Color color) {
        int int1;
        try {
            int1 = Integer.parseInt(s, 16);
        }
        catch (NumberFormatException ex) {
            return color;
        }
        return new Color(int1);
    }
    
    int convert2Int(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    int nextSpeed() {
        if (++this.speedsNow > this.speedsMax) {
            this.speedsNow = 0;
        }
        return this.speedsNow;
    }
}
