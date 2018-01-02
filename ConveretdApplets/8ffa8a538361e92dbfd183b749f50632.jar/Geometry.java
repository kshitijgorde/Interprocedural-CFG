import java.awt.Panel;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Geometry extends Applet
{
    boolean debug;
    String title;
    Color background;
    static Color orange;
    Slate slate;
    StringBuffer message;
    int parCount;
    int init;
    int defaultAlign;
    boolean floating;
    ClientFrame floater;
    int stage;
    Button closeButton;
    Button resetButton;
    Button returnButton;
    Dimension baseSize;
    String font;
    int fontsize;
    static String[] colorName;
    static Color[] constColor;
    static String[] alignName;
    static int[] constAlign;
    
    public String getAppletInfo() {
        return "Geometry. Copyright 1996-98, David Joyce, Clark University. Version 2.2";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "Color", "background color" }, { "e[i]", "element", "element information" }, { "pivot", "String", "name of pivot point, if any" }, { "debug", "boolean", "output debugging info" }, { "init", "int", "initial stage" }, { "title", "String", "title" }, { "font", "String", "font name" }, { "fontsize", "int", "font size" }, { "align", "String", "label alignment" } };
    }
    
    Color randomColor() {
        return new Color(Color.HSBtoRGB((float)Math.random(), (float)Math.random(), 1.0f));
    }
    
    Color parseColor(final String s) {
        if (s == null || "none".equals(s) || "0".equals(s)) {
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
        for (int i = 0; i < Geometry.colorName.length; ++i) {
            if (Geometry.colorName[i].equals(s)) {
                return Geometry.constColor[i];
            }
        }
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
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
            catch (NumberFormatException ex2) {
                return null;
            }
        }
    }
    
    int parseAlign(final String s) {
        if (s == null) {
            return 0;
        }
        for (int i = 0; i < Geometry.alignName.length; ++i) {
            if (Geometry.alignName[i].equals(s)) {
                return Geometry.constAlign[i];
            }
        }
        return 0;
    }
    
    Element parseElement(final String s, final StringBuffer sb) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        if (!stringTokenizer.hasMoreTokens()) {
            sb.append("Parameter is empty.");
            return null;
        }
        final String nextToken = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            sb.append("Parameter " + s + " missing an element class.");
            return null;
        }
        final String nextToken2 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            sb.append("Parameter " + s + " missing a construction.");
            return null;
        }
        final String nextToken3 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            sb.append("Parameter " + s + " missing construction data.");
            return null;
        }
        final Element constructElement = this.slate.constructElement(nextToken, nextToken2, nextToken3, stringTokenizer.nextToken(), sb);
        if (constructElement == null) {
            return null;
        }
        if (stringTokenizer.hasMoreTokens()) {
            constructElement.nameColor = this.parseColor(stringTokenizer.nextToken());
        }
        else if (constructElement.inClass("PointElement")) {
            constructElement.nameColor = Color.black;
        }
        constructElement.align = this.defaultAlign;
        if (stringTokenizer.hasMoreTokens()) {
            constructElement.vertexColor = this.parseColor(stringTokenizer.nextToken());
        }
        else if (constructElement.dimension == 0) {
            constructElement.vertexColor = (constructElement.dragable ? (constructElement.inClass("PlaneSlider") ? Color.red : Geometry.orange) : Color.black);
        }
        if (stringTokenizer.hasMoreTokens()) {
            constructElement.edgeColor = this.parseColor(stringTokenizer.nextToken());
        }
        else if (constructElement.dimension > 0) {
            constructElement.edgeColor = Color.black;
        }
        if (stringTokenizer.hasMoreTokens()) {
            constructElement.faceColor = this.parseColor(stringTokenizer.nextToken());
        }
        else if (constructElement.dimension == 2) {
            constructElement.faceColor = this.background.brighter();
        }
        return constructElement;
    }
    
    public void init() {
        this.baseSize = this.size();
        this.parCount = 0;
        this.getAppletContext().showStatus("initializing Geometry applet");
        final String parameter = this.getParameter("debug");
        this.debug = (parameter != null && (parameter.equalsIgnoreCase("yes") || parameter.equalsIgnoreCase("true")));
        final String parameter2 = this.getParameter("init");
        try {
            this.init = ((parameter2 == null) ? Integer.MAX_VALUE : Integer.parseInt(parameter2));
        }
        catch (NumberFormatException ex) {
            this.init = Integer.MAX_VALUE;
        }
        this.slate = new Slate(100);
        this.title = this.getParameter("title");
        if (this.title == null) {
            this.title = "Geometry";
        }
        this.font = this.getParameter("font");
        if (this.font == null) {
            this.font = "TimesRoman";
        }
        final String parameter3 = this.getParameter("fontsize");
        try {
            this.fontsize = ((parameter3 == null) ? 18 : Integer.parseInt(parameter3));
        }
        catch (NumberFormatException ex2) {
            this.fontsize = 18;
        }
        this.slate.setFont(new Font(this.font, 2, this.fontsize));
        final String parameter4 = this.getParameter("align");
        this.defaultAlign = this.parseAlign(parameter4);
        if (this.debug) {
            System.out.println("param=" + parameter4 + " defaultAlign=" + this.defaultAlign);
        }
        final String parameter5 = this.getParameter("background");
        if (parameter5 != null) {
            this.background = this.parseColor(parameter5);
        }
        if (this.background == null) {
            this.background = this.getBackground();
        }
        else {
            this.slate.setBackground(this.background);
        }
        this.setLayout(new BorderLayout());
        this.add("Center", this.slate);
        this.stage = this.init;
        while (this.parCount != -1 && this.parCount < this.stage) {
            final String parameter6 = this.getParameter("e[" + (this.parCount + 1) + "]");
            if (this.debug) {
                System.out.println("Parsing parameter e[" + (this.parCount + 1) + "]=" + parameter6);
            }
            if (parameter6 == null) {
                this.parCount = -1;
            }
            else if (this.parseElement(parameter6, this.message) == null) {
                System.out.println("Parameter e[" + (this.parCount + 1) + "]=" + parameter6 + " not parsed. " + this.message.toString());
                this.parCount = -1;
            }
            else {
                ++this.parCount;
            }
        }
        final String parameter7 = this.getParameter("pivot");
        if (parameter7 != null) {
            this.slate.setPivot(parameter7);
        }
        this.slate.updateCoordinates(0);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 117 || n == 85 || (n == 10 && !this.floating)) {
            if (!this.floating) {
                this.floating = true;
                this.floater = new ClientFrame(this.title, this);
                this.remove(this.slate);
                if (this.resetButton == null) {
                    this.resetButton = new Button("Reset");
                }
                if (this.closeButton == null) {
                    this.closeButton = new Button("Close");
                }
                this.floater.add("Center", this.slate);
                final Panel panel = new Panel();
                panel.add(this.resetButton);
                panel.add(this.closeButton);
                this.floater.add("South", panel);
                this.floater.resize(this.baseSize.width, this.baseSize.height + 50);
                this.floater.show();
            }
            return true;
        }
        if (n == 100 || n == 68 || n == 10) {
            if (this.floating) {
                this.floating = false;
                this.floater.hide();
                this.floater.remove(this.slate);
                this.slate.resize(this.baseSize);
                this.slate.reshape(0, 0, this.baseSize.width, this.baseSize.height);
                this.add(this.slate);
                this.invalidate();
                this.layout();
                this.floater = null;
            }
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        if (!(event.target instanceof Button)) {
            return false;
        }
        if (event.target == this.closeButton || event.target == this.returnButton) {
            if (this.floating) {
                this.floating = false;
                this.floater.hide();
                this.floater.remove(this.slate);
                this.slate.reshape(0, 0, this.baseSize.width, this.baseSize.height);
                this.add(this.slate);
                this.invalidate();
                this.layout();
                this.floater = null;
            }
            return true;
        }
        if (event.target == this.resetButton) {
            this.slate.reset();
            this.slate.repaint();
            return true;
        }
        return false;
    }
    
    public Geometry() {
        this.message = new StringBuffer(100);
        this.floating = false;
    }
    
    static {
        Geometry.orange = new Color(255, 150, 0);
        Geometry.colorName = new String[] { "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        Geometry.constColor = new Color[] { Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
        Geometry.alignName = new String[] { "central", "left", "right", "above", "below" };
        Geometry.constAlign = new int[] { 0, 1, 2, 3, 4 };
    }
}
