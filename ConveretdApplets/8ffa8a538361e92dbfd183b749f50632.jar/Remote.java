import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Remote extends Applet
{
    Color background;
    String mainName;
    Applet main;
    int stage;
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "Color", "background color" }, { "stage", "int", "stage" } };
    }
    
    Color randomColor() {
        final float n = (float)Math.random();
        int n2;
        if (Math.random() < 0.2) {
            n2 = Color.HSBtoRGB(n, (float)(1.0 - Math.random() * Math.random()), (float)(1.0 - Math.random() * Math.random()));
        }
        else if (Math.random() < 0.4) {
            n2 = Color.HSBtoRGB(n, 1.0f, (float)(0.3 + 0.7 * Math.random()));
        }
        else {
            n2 = Color.HSBtoRGB(n, (float)Math.random(), 1.0f);
        }
        return new Color(n2);
    }
    
    Color parseColor(final String s) {
        if ("none".equals(s)) {
            return null;
        }
        if ("random".equals(s)) {
            return this.randomColor();
        }
        if ("background".equals(s)) {
            return this.background;
        }
        if ("brighter".equals(s)) {
            return this.background.brighter();
        }
        if ("darker".equals(s)) {
            return this.background.darker();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (!stringTokenizer.hasMoreTokens()) {
            return null;
        }
        try {
            final float n = (float)(Integer.parseInt(stringTokenizer.nextToken()) / 360.0);
            if (!stringTokenizer.hasMoreTokens()) {
                return null;
            }
            final float n2 = (float)(Integer.parseInt(stringTokenizer.nextToken()) / 100.0);
            if (!stringTokenizer.hasMoreTokens()) {
                return null;
            }
            return new Color(Color.HSBtoRGB(n, n2, (float)(Integer.parseInt(stringTokenizer.nextToken()) / 100.0)));
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("main");
        this.mainName = ((parameter != null) ? parameter : "main");
        final String parameter2 = this.getParameter("background");
        if (parameter2 != null) {
            this.setBackground(this.background = this.parseColor(parameter2));
        }
        final String parameter3 = this.getParameter("stage");
        try {
            this.stage = ((parameter3 == null) ? Integer.MAX_VALUE : Integer.parseInt(parameter3));
        }
        catch (NumberFormatException ex) {
            this.stage = Integer.MAX_VALUE;
        }
        this.main = this.getAppletContext().getApplet(this.mainName);
    }
    
    public boolean handleEvent(final Event event) {
        if (this.main == null) {
            this.main = this.getAppletContext().getApplet(this.mainName);
        }
        return this.main != null && this.main.handleEvent(event);
    }
}
