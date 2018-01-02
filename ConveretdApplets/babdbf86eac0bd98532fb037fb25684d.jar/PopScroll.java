import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PopScroll extends Applet implements Runnable
{
    private Thread m_backg;
    private Image buf;
    private Graphics gbuf;
    private int screen_W;
    private int screen_H;
    private Color textColor;
    private Color shadowColor;
    private Color bckgColor;
    String outStr;
    int chWidth;
    int style;
    String fontName;
    int fontSize;
    int Len;
    int wavelength;
    int amplitude;
    private boolean m_fStandAlone;
    private String m_var1;
    private final String PARAM_var1 = "var1";
    private final String PARAM_txt = "textcolor";
    private final String PARAM_shd = "shadowcolor";
    private final String PARAM_bck = "backgroundcolor";
    private final String PARAM_font = "font";
    private final String PARAM_size = "size";
    private final String PARAM_style = "style";
    
    public void stop() {
        if (this.m_backg != null) {
            this.m_backg.stop();
            this.m_backg = null;
        }
    }
    
    void GetParameters(final String[] array) {
        final String getParameter = this.GetParameter("var1", array);
        if (getParameter != null) {
            this.m_var1 = getParameter;
        }
        try {
            final String parameter = this.getParameter("textcolor");
            if (parameter != null) {
                final int int1 = Integer.parseInt(parameter, 16);
                this.textColor = new Color(int1 >> 16, (int1 >> 8) - (int1 >> 16 << 8), int1 - (int1 >> 8 << 8));
            }
            final String parameter2 = this.getParameter("shadowcolor");
            if (parameter2 != null) {
                final int int2 = Integer.parseInt(parameter2, 16);
                this.shadowColor = new Color(int2 >> 16, (int2 >> 8) - (int2 >> 16 << 8), int2 - (int2 >> 8 << 8));
            }
            final String parameter3 = this.getParameter("backgroundcolor");
            if (parameter3 != null) {
                final int int3 = Integer.parseInt(parameter3, 16);
                this.bckgColor = new Color(int3 >> 16, (int3 >> 8) - (int3 >> 16 << 8), int3 - (int3 >> 8 << 8));
            }
        }
        catch (NumberFormatException ex) {
            this.m_var1 = " bad color specification in HTML file! please use \"RRGGBB\"";
            this.textColor = Color.red;
            this.bckgColor = Color.black;
        }
        final String parameter4 = this.getParameter("size");
        if (parameter4 != null) {
            this.fontSize = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("style");
        if (parameter5 != null) {
            this.style = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("font");
        if (parameter6 != null) {
            this.fontName = parameter6;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "var1", "String", "Parameter description" } };
    }
    
    void drawString(final int n) {
        this.gbuf.setColor(this.bckgColor);
        this.gbuf.fillRect(0, 0, this.screen_W, this.screen_H);
        this.gbuf.setColor(this.shadowColor);
        this.gbuf.drawString(this.outStr, 20 - this.chWidth * n / 16, this.screen_H - this.amplitude - 10);
        this.gbuf.setColor(this.textColor);
        this.gbuf.drawString(this.outStr, 20 - this.chWidth * n / 16, this.screen_H - this.amplitude - 20);
        for (int i = 20; i < this.screen_W; ++i) {
            this.gbuf.copyArea(i, 0, 1, 200, 0, (int)(this.amplitude * Math.sin(i / this.wavelength + n * 13.0 / 32.0)));
        }
    }
    
    public void destroy() {
    }
    
    String GetParameter(final String s, final String[] array) {
        if (array == null) {
            return this.getParameter(s);
        }
        final String string = s + "=";
        String s2 = null;
        final int length = string.length();
        try {
            int i = 0;
            while (i < array.length) {
                if (string.equalsIgnoreCase(array[i].substring(0, length))) {
                    s2 = array[i].substring(length);
                    if (!s2.startsWith("\"")) {
                        break;
                    }
                    s2 = s2.substring(1);
                    if (s2.endsWith("\"")) {
                        s2 = s2.substring(0, s2.length() - 1);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public PopScroll() {
        this.outStr = "";
        this.fontName = "";
        this.fontSize = 60;
        this.wavelength = 20;
        this.amplitude = 4;
        this.m_var1 = ".   ___Creator: Xavier Potier, www.v-era.com___   .";
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.m_backg == null) {
            (this.m_backg = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: backg\r\n" + "Author: Nerd\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public void run() {
        int n = 0;
        int n2 = 0;
        final FontMetrics fontMetrics = this.gbuf.getFontMetrics();
        while (fontMetrics.stringWidth(this.m_var1.substring(0, this.Len)) < this.screen_W) {
            ++this.Len;
        }
    Label_0048_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        n += 2;
                        if (n >= 16) {
                            n = 0;
                            ++n2;
                            this.chWidth = fontMetrics.charWidth(this.m_var1.charAt(n2));
                            if (n2 > this.m_var1.length() - this.Len) {
                                n2 = 0;
                            }
                            this.outStr = this.m_var1.substring(n2, n2 + this.Len - 1);
                        }
                        this.drawString(n);
                        this.repaint();
                        Thread.sleep(50L);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue Label_0048_Outer;
                }
                continue;
            }
        }
    }
    
    public void init() {
        if (!this.m_fStandAlone) {
            this.GetParameters(null);
        }
        final int width = this.size().width;
        this.screen_W = width;
        final int height = this.size().height;
        this.screen_H = height;
        this.buf = this.createImage(width, height);
        this.gbuf = this.buf.getGraphics();
        this.getParams();
    }
    
    void getParams() {
        this.gbuf.setFont(new Font(this.fontName, this.style, this.fontSize));
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.m_var1 = this.m_var1.substring(1) + (char)n;
        return true;
    }
}
