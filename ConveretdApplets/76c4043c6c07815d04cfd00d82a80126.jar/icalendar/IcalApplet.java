// 
// Decompiled by Procyon v0.5.30
// 

package icalendar;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.awt.event.MouseListener;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;

public class IcalApplet extends Applet
{
    static final String p_def_background = "background.gif";
    static final String p_def_openwin = "openwin.au";
    static final String p_def_noopenwin = "noopenwin.au";
    static final String p_def_current = "0";
    static final String p_def_disable = "false";
    static final String p_def_frame = "true";
    static final String p_def_framecolor = "255, 255, 255";
    static final String p_def_fontname = "Helvetica";
    static final String p_def_fontsize = "15";
    static final String p_def_fontcolor = "0, 0, 0";
    static final String p_def_bgcolor = "255, 255, 255";
    static final String p_def_target = "_blank";
    static final String p_def_steps = "20";
    static final String p_def_delay = "40";
    String p_background;
    String p_openwin;
    String p_noopenwin;
    String p_disable;
    String p_frame;
    String p_framecolor;
    String p_fontname;
    String p_fontsize;
    String p_fontcolor;
    String p_bgcolor;
    String p_target;
    String p_steps;
    String p_delay;
    String p_inaugurate;
    int xoffset;
    int yoffset;
    Integer steps;
    Image background;
    AudioClip openwin;
    AudioClip noopenwin;
    int inaugurate;
    Font font;
    Color framecolor;
    Color fontcolor;
    Color bgcolor;
    Vector backWin;
    Vector frontWin;
    Vector contentWin;
    Vector woVect;
    boolean frame;
    boolean disable;
    
    public void init() {
        this.setLayout(null);
        this.initDef();
        this.initObj();
        this.initWin();
    }
    
    public void destroy() {
    }
    
    private void initDef() {
        this.p_background = this.getParameter("background");
        this.p_openwin = this.getParameter("openwin");
        this.p_noopenwin = this.getParameter("noopenwin");
        this.p_disable = this.getParameter("disable");
        this.p_frame = this.getParameter("frame");
        this.p_framecolor = this.getParameter("framecolor");
        this.p_fontname = this.getParameter("fontname");
        this.p_fontsize = this.getParameter("fontsize");
        this.p_fontcolor = this.getParameter("fontcolor");
        this.p_bgcolor = this.getParameter("bgcolor");
        this.p_target = this.getParameter("target");
        this.p_steps = this.getParameter("steps");
        this.p_delay = this.getParameter("delay");
        this.p_inaugurate = this.getParameter("inaugurate");
        if (this.p_background == null) {
            this.p_background = "background.gif";
        }
        if (this.p_openwin == null) {
            this.p_openwin = "openwin.au";
        }
        if (this.p_noopenwin == null) {
            this.p_noopenwin = "noopenwin.au";
        }
        if (this.p_disable == null) {
            this.p_disable = "false";
        }
        if (this.p_frame == null) {
            this.p_frame = "true";
        }
        if (this.p_framecolor == null) {
            this.p_framecolor = "255, 255, 255";
        }
        if (this.p_fontname == null) {
            this.p_fontname = "Helvetica";
        }
        if (this.p_fontsize == null) {
            this.p_fontsize = "15";
        }
        if (this.p_fontcolor == null) {
            this.p_fontcolor = "0, 0, 0";
        }
        if (this.p_bgcolor == null) {
            this.p_bgcolor = "255, 255, 255";
        }
        if (this.p_steps == null) {
            this.p_steps = "20";
        }
        if (this.p_delay == null) {
            this.p_delay = "40";
        }
    }
    
    private int parseInaug(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        return DayCalc.current(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
    }
    
    private Color parseCol(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
    }
    
    private boolean parseBool(final String s) {
        return s.equalsIgnoreCase("true");
    }
    
    private void initObj() {
        WindowOpener.setShow(this.getAppletContext(), this.p_target);
        this.background = this.getImage(this.getDocumentBase(), this.p_background);
        this.openwin = this.getAudioClip(this.getDocumentBase(), this.p_openwin);
        this.noopenwin = this.getAudioClip(this.getDocumentBase(), this.p_noopenwin);
        WindowOpener.setOpenWin(this.openwin);
        WindowOpener.setNoOpenWin(this.noopenwin);
        int width;
        while ((width = this.background.getWidth(this)) < 0) {}
        int height;
        while ((height = this.background.getHeight(this)) < 0) {}
        this.xoffset = (this.getSize().width - width) / 2;
        this.yoffset = (this.getSize().height - height) / 2;
        this.framecolor = this.parseCol(this.p_framecolor);
        this.fontcolor = this.parseCol(this.p_fontcolor);
        this.setBackground(this.bgcolor = this.parseCol(this.p_bgcolor));
        this.font = new Font(this.p_fontname, 1, Integer.parseInt(this.p_fontsize));
        try {
            WindowOpener.setTotStep(Integer.parseInt(this.p_steps));
        }
        catch (NumberFormatException ex) {
            System.err.println("Illegal number of steps: " + this.p_steps);
        }
        try {
            WindowOpener.setDelay(Integer.parseInt(this.p_delay));
        }
        catch (NumberFormatException ex2) {
            System.err.println("Illegal delay: " + this.p_delay);
        }
        if (this.p_inaugurate == null) {
            this.inaugurate = -1;
        }
        else {
            this.inaugurate = this.parseInaug(this.p_inaugurate);
        }
        this.disable = this.parseBool(this.p_disable);
        this.frame = this.parseBool(this.p_frame);
        this.backWin = new Vector();
        this.frontWin = new Vector();
        this.contentWin = new Vector();
    }
    
    private void initWin() {
        String parameter;
        for (int n = 1; (parameter = this.getParameter("win" + n)) != null; ++n) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                final String trim = stringTokenizer.nextToken().trim();
                final String trim2 = stringTokenizer.nextToken().trim();
                final String trim3 = stringTokenizer.nextToken().trim();
                final String trim4 = stringTokenizer.nextToken().trim();
                final String trim5 = stringTokenizer.nextToken().trim();
                final String trim6 = stringTokenizer.nextToken().trim();
                final int int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                final int int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                final int int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
                final int int4 = Integer.parseInt(stringTokenizer.nextToken().trim());
                final URL url = new URL(trim6);
                final AudioClip audioClip = this.getAudioClip(this.getDocumentBase(), trim3);
                final Window initPlainWin = this.initPlainWin(trim);
                final Window initPlainWin2 = this.initPlainWin(trim2);
                final Window initFrontWin = this.initFrontWin(int1, int2, int3, int4, initPlainWin.getImage(), trim4);
                this.contentWin.addElement(initPlainWin);
                this.backWin.addElement(initPlainWin2);
                this.frontWin.addElement(initFrontWin);
                int n2;
                if (trim5.equalsIgnoreCase("down")) {
                    n2 = 4;
                }
                else if (trim5.equalsIgnoreCase("up")) {
                    n2 = 3;
                }
                else if (trim5.equalsIgnoreCase("right")) {
                    n2 = 2;
                }
                else {
                    n2 = 1;
                }
                final WindowOpener windowOpener = new WindowOpener(initFrontWin, initPlainWin2, initPlainWin, int1 + this.xoffset, int2 + this.yoffset, int3, int4, n2, this.inaugurate == -1 || this.inaugurate == n || (n < this.inaugurate && !this.disable), url, audioClip);
                windowOpener.setBounds(initPlainWin);
                windowOpener.setBounds(initPlainWin2);
                windowOpener.setBounds(initFrontWin);
                initPlainWin.addMouseListener(windowOpener);
                initPlainWin2.addMouseListener(windowOpener);
                initFrontWin.addMouseListener(windowOpener);
            }
            catch (NoSuchElementException ex) {
                System.err.println("Parameters were malformed for window no. " + n);
            }
            catch (MalformedURLException ex2) {
                System.err.println("Malformed URL for window no. " + n);
            }
        }
        this.addWins(this.backWin);
        this.addWins(this.frontWin);
        this.addWins(this.contentWin);
    }
    
    private void addWins(final Vector vector) {
        for (int i = 0; i != vector.size(); ++i) {
            this.add(vector.elementAt(i));
        }
    }
    
    private Window initPlainWin(final String s) {
        return new Window(this.getImage(this.getDocumentBase(), s));
    }
    
    private Window initFrontWin(final int n, final int n2, final int n3, final int n4, final Image image, final String s) {
        final Image image2 = this.createImage(new FilteredImageSource(this.background.getSource(), new CropImageFilter(n, n2, n3, n4)));
        final Image image3 = this.createImage(n3, n4);
        final int stringWidth = this.getFontMetrics(this.font).stringWidth(s);
        final int size = this.font.getSize();
        final Graphics graphics = image3.getGraphics();
        while (!graphics.drawImage(image2, 0, 0, this)) {}
        graphics.setFont(this.font);
        graphics.setColor(this.fontcolor);
        graphics.drawString(s, (n3 - stringWidth) / 2, (n4 + size) / 2);
        return new Window(image3);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.background, this.xoffset, this.yoffset, this);
        if (this.frame) {
            graphics.setColor(this.framecolor);
            for (int i = 0; i != this.contentWin.size(); ++i) {
                final Rectangle bounds = this.contentWin.elementAt(i).getBounds();
                graphics.draw3DRect(bounds.x - 1, bounds.y - 1, bounds.width + 1, bounds.height + 1, false);
            }
        }
    }
    
    public String getAppletInfo() {
        return "Icalendar 1.0 by Robert Gustavsson. All rights reserved. Free for non-commercial purposes only";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "string", "Background image file (relative to document base)" }, { "openwin", "string", "Audio file to play when opening a window" }, { "nooopenwin", "string", "Audio file to play when failing to open a window" }, { "win[n]", "string", "content image, backside image, label, direction (left, right, up or down), URL, X, Y, width, height" }, { "inaugurate", "string", "Day when window 1 can be opened (yyyy,mm,dd). If this parameter is omitted, all windows can be opened at any time" }, { "disable", "bool", "Whether earlier windows are disabled" }, { "frame", "bool", "Whether to draw a frame around the Window" }, { "fontname", "String", "Font for window labels" }, { "fontsize", "integer", "Size for window labels" }, { "fontcolor", "integer, integer, integer", "RGB colors for window labels" }, { "bgcolor", "integer, integer, integer", "RGB colors for applet background" }, { "framecolor", "RGB colors for frames" }, { "steps", "integer", "Steps in animation" }, { "delay", "integer", "Delay between animation steps" }, { "target", "string", "Where to show URL (_self, _parent, _top, _blank, or name)" } };
    }
}
