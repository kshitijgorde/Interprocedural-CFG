import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FadeText extends Applet implements Runnable
{
    Thread m_SlidingM;
    Image off;
    Graphics g_off;
    FontMetrics fm;
    URL goURL;
    Dimension d;
    int center_x;
    int num_msg;
    int k;
    private boolean m_showhand;
    boolean paused;
    boolean tag;
    boolean mouse_in;
    boolean bad_descrips;
    boolean first_pass;
    boolean multi_line;
    boolean first_run;
    private int m_delay;
    private int m_mouse_delay;
    private String m_messagefont;
    private String[] m_msg;
    private Color[] m_cur_textcolor;
    private Color[] m_cur_bgcolor;
    private Color m_bgcolor;
    private Color m_textcolor;
    private int[] m_cur_delay;
    private String[] m_cur_desturl;
    private String m_loadwhere;
    private String[] m_cur_loadwhere;
    private int m_margin;
    private int m_b_thick;
    private int[] m_cur_b_thick;
    private Color m_b_color;
    private Color[] m_cur_b_color;
    private int m_fadein_frames;
    private int m_fadeout_frames;
    private int m_fadein_delay;
    private int m_fadeout_delay;
    private Font m_font;
    private Font[] m_cur_font;
    private int m_valign;
    private int[] m_cur_valign;
    private int m_halign;
    private int[] m_cur_halign;
    private boolean m_fade_bg;
    private boolean m_underline;
    private boolean[] m_cur_underline;
    private Color m_hltext_color;
    private Color m_hlborder_color;
    private boolean m_hllinksonly;
    private Frame the_frame;
    private int m_fadebgframes;
    private int m_fadebgdelay;
    private String m_onsbtext;
    private String m_offsbtext;
    private String[] ml;
    
    public void init() {
        final String s = "Fading Messages (Developer Version), Copyright (c) 2000, OpenCube Inc.";
        this.d = this.size();
        int n = 0;
        String s2 = this.getParameter("Notice");
        if (s2 == null) {
            this.m_onsbtext = "Missing 'Notice' Tag";
            return;
        }
        final String key = evalkey.getKey(s2, this.getDocumentBase());
        if (key.equals(s)) {
            this.tag = true;
            final Properties properties = new Properties();
            try {
                s2 = this.getParameter("filelocation");
                if (s2 == null) {
                    this.m_onsbtext = "Missing \"filelocation\" parameter";
                    this.tag = false;
                    return;
                }
                final InputStream openStream = new URL(this.getDocumentBase(), s2).openStream();
                properties.load(openStream);
                openStream.close();
            }
            catch (Exception ex) {
                this.m_onsbtext = "Error loading param file: \"" + s2 + "\"";
                this.tag = false;
                return;
            }
            final String property = properties.getProperty("onsbtext");
            if (property != null) {
                this.m_onsbtext = property;
            }
            final String property2 = properties.getProperty("offsbtext");
            if (property2 != null) {
                this.m_offsbtext = property2;
            }
            final String property3 = properties.getProperty("loadwhere");
            if (property3 != null) {
                this.m_loadwhere = property3;
            }
            final String property4 = properties.getProperty("font");
            if (property4 != null) {
                this.m_font = ocfontc.getFontSD(property4, ",");
            }
            final String property5 = properties.getProperty("delay");
            if (property5 != null) {
                this.m_delay = Integer.parseInt(property5);
            }
            final String property6 = properties.getProperty("mouseoffdelay");
            if (property6 != null) {
                this.m_mouse_delay = Integer.parseInt(property6);
            }
            final String property7 = properties.getProperty("fadeindelay");
            if (property7 != null) {
                this.m_fadein_delay = Integer.parseInt(property7);
            }
            final String property8 = properties.getProperty("fadeoutdelay");
            if (property8 != null) {
                this.m_fadeout_delay = Integer.parseInt(property8);
            }
            final String property9 = properties.getProperty("fadebg");
            if (property9 != null) {
                this.m_fade_bg = Boolean.valueOf(property9);
            }
            final String property10 = properties.getProperty("showhand");
            if (property10 != null) {
                this.m_showhand = Boolean.valueOf(property10);
            }
            final String property11 = properties.getProperty("underlinetext");
            if (property11 != null) {
                this.m_underline = Boolean.valueOf(property11);
            }
            final String property12 = properties.getProperty("hllinksonly");
            if (property12 != null) {
                this.m_hllinksonly = Boolean.valueOf(property12);
            }
            final String property13 = properties.getProperty("fadebgdelay");
            if (property13 != null) {
                this.m_fadebgdelay = Integer.parseInt(property13);
            }
            final String property14 = properties.getProperty("fadbgframes");
            if (property14 != null) {
                this.m_fadebgframes = Integer.parseInt(property14);
            }
            final String property15 = properties.getProperty("fadeinframes");
            if (property15 != null) {
                this.m_fadein_frames = Integer.parseInt(property15);
            }
            final String property16 = properties.getProperty("fadeoutframes");
            if (property16 != null) {
                this.m_fadeout_frames = Integer.parseInt(property16);
            }
            final String property17 = properties.getProperty("textmargin");
            if (property17 != null) {
                this.m_margin = Integer.parseInt(property17);
            }
            final String property18 = properties.getProperty("borderthickness");
            if (property18 != null) {
                this.m_b_thick = Integer.parseInt(property18);
            }
            final String property19 = properties.getProperty("font");
            if (property19 != null) {
                this.m_messagefont = property19;
            }
            final String property20 = properties.getProperty("hltextcolor");
            if (property20 != null) {
                this.m_hltext_color = occcolor.ConvertColor(property20);
            }
            final String property21 = properties.getProperty("hlbordercolor");
            if (property21 != null) {
                this.m_hlborder_color = occcolor.ConvertColor(property21);
            }
            final String property22 = properties.getProperty("textcolor");
            if (property22 != null) {
                this.m_textcolor = occcolor.ConvertColor(property22);
            }
            final String property23 = properties.getProperty("bgcolor");
            if (property23 != null) {
                this.setBackground(this.m_bgcolor = occcolor.ConvertColor(property23));
            }
            else {
                this.setBackground(this.m_bgcolor = Color.white);
            }
            final String property24 = properties.getProperty("bordercolor");
            if (property24 != null) {
                this.m_b_color = occcolor.ConvertColor(property24);
            }
            final String property25 = properties.getProperty("valign");
            if (property25 != null) {
                this.m_valign = getAlignValue(property25);
            }
            final String property26 = properties.getProperty("halign");
            if (property26 != null) {
                this.m_halign = getAlignValue(property26);
            }
            while (properties.getProperty("desc" + n) != null) {
                ++n;
                ++this.num_msg;
            }
            if (this.num_msg < 1) {
                this.num_msg = 1;
                this.bad_descrips = true;
            }
            this.m_msg = new String[this.num_msg];
            this.m_cur_underline = new boolean[this.num_msg];
            this.m_cur_font = new Font[this.num_msg];
            this.m_cur_textcolor = new Color[this.num_msg];
            this.m_cur_bgcolor = new Color[this.num_msg];
            this.m_cur_delay = new int[this.num_msg];
            this.m_cur_desturl = new String[this.num_msg];
            this.m_cur_loadwhere = new String[this.num_msg];
            this.m_cur_b_color = new Color[this.num_msg];
            this.m_cur_b_thick = new int[this.num_msg];
            this.m_cur_valign = new int[this.num_msg];
            this.m_cur_halign = new int[this.num_msg];
            for (int i = 0; i < this.num_msg; ++i) {
                final String property27 = properties.getProperty("desc" + i);
                if (property27 != null) {
                    this.m_msg[i] = new String(property27);
                }
                else {
                    this.m_msg[i] = new String(" ");
                }
                final String property28 = properties.getProperty("underlinetext" + i);
                if (property28 != null) {
                    this.m_cur_underline[i] = Boolean.valueOf(property28);
                }
                else {
                    this.m_cur_underline[i] = this.m_underline;
                }
                final String property29 = properties.getProperty("font" + i);
                if (property29 != null) {
                    this.m_cur_font[i] = ocfontc.getFontSD(property29, ",");
                }
                else {
                    this.m_cur_font[i] = this.m_font;
                }
                final String property30 = properties.getProperty("textcolor" + i);
                if (property30 != null) {
                    this.m_cur_textcolor[i] = occcolor.ConvertColor(property30);
                }
                else {
                    this.m_cur_textcolor[i] = this.m_textcolor;
                }
                final String property31 = properties.getProperty("bgcolor" + i);
                if (property31 != null) {
                    this.m_cur_bgcolor[i] = occcolor.ConvertColor(property31);
                }
                else {
                    this.m_cur_bgcolor[i] = this.m_bgcolor;
                }
                final String property32 = properties.getProperty("delay" + i);
                if (property32 != null) {
                    this.m_cur_delay[i] = Integer.parseInt(property32);
                }
                else {
                    this.m_cur_delay[i] = this.m_delay;
                }
                final String property33 = properties.getProperty("desturl" + i);
                if (property33 != null) {
                    this.m_cur_desturl[i] = property33;
                }
                else {
                    this.m_cur_desturl[i] = "-1";
                }
                final String property34 = properties.getProperty("loadwhere" + i);
                if (property34 != null) {
                    this.m_cur_loadwhere[i] = property34;
                }
                else {
                    this.m_cur_loadwhere[i] = this.m_loadwhere;
                }
                final String property35 = properties.getProperty("valign" + i);
                if (property35 != null) {
                    this.m_cur_valign[i] = getAlignValue(property35);
                }
                else {
                    this.m_cur_valign[i] = this.m_valign;
                }
                final String property36 = properties.getProperty("halign" + i);
                if (property36 != null) {
                    this.m_cur_halign[i] = getAlignValue(property36);
                }
                else {
                    this.m_cur_halign[i] = this.m_valign;
                }
                final String property37 = properties.getProperty("bordercolor" + i);
                if (property37 != null) {
                    this.m_cur_b_color[i] = occcolor.ConvertColor(property37);
                }
                else {
                    this.m_cur_b_color[i] = this.m_b_color;
                }
                final String property38 = properties.getProperty("borderthickness" + i);
                if (property38 != null) {
                    this.m_cur_b_thick[i] = Integer.parseInt(property38);
                }
                else {
                    this.m_cur_b_thick[i] = this.m_b_thick;
                }
            }
            if (this.bad_descrips) {
                this.m_msg[0] = "Must include 'desc0' tag at a minimum in order for this applet to function.";
                this.m_cur_delay[0] = 30000;
            }
            this.setFont(this.m_font);
            this.fm = this.getFontMetrics(this.m_font);
            return;
        }
        if (key.equals("-1")) {
            this.m_onsbtext = "Incorrect Base URL Registration";
            return;
        }
        this.m_onsbtext = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    public void paint(final Graphics graphics) {
        this.paintIt();
    }
    
    public synchronized void paintIt() {
        final Graphics graphics = this.getGraphics();
        if (!this.tag) {
            graphics.setColor(Color.black);
            graphics.drawString(this.m_onsbtext, 5, 30);
            return;
        }
        graphics.drawImage(this.off, 0, 0, this);
    }
    
    public void DrawFade() {
        this.k = 0;
        while (this.k < this.num_msg) {
            this.g_off.setColor(this.m_cur_bgcolor[this.k]);
            this.g_off.fillRect(0, 0, this.d.width, this.d.height);
            this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], this.m_cur_textcolor[this.k], this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_cur_b_color[this.k], this.m_cur_b_thick[this.k], false, this.m_cur_underline[this.k]);
            this.figureFade(false, this.m_cur_textcolor[this.k], this.m_cur_bgcolor[this.k]);
            if (!this.mouse_in) {
                this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], this.m_cur_textcolor[this.k], this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_cur_b_color[this.k], this.m_cur_b_thick[this.k], false, this.m_cur_underline[this.k]);
            }
            this.paintIt();
            this.pauseIt();
            this.figureFade(true, this.m_cur_textcolor[this.k], this.m_cur_bgcolor[this.k]);
            while (this.mouse_in) {
                this.paused = true;
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                if (!this.mouse_in) {
                    try {
                        Thread.sleep(this.m_mouse_delay);
                    }
                    catch (InterruptedException ex2) {}
                    this.paused = false;
                    this.figureFade(true, this.m_cur_textcolor[this.k], this.m_cur_bgcolor[this.k]);
                }
            }
            final Color color = this.m_cur_bgcolor[this.k];
            Color color2;
            if (this.k != this.num_msg - 1) {
                color2 = this.m_cur_bgcolor[this.k + 1];
            }
            else {
                color2 = this.m_cur_bgcolor[0];
            }
            if (!color2.equals(color) && this.m_fade_bg) {
                this.figureFadeBG(color, color2);
            }
            ++this.k;
        }
    }
    
    private void figureFadeBG(final Color color, final Color color2) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = color2.getRed();
        final int green2 = color2.getGreen();
        final int blue2 = color2.getBlue();
        final float n = (red - red2) / this.m_fadebgframes;
        final float n2 = (green - green2) / this.m_fadebgframes;
        final float n3 = (blue - blue2) / this.m_fadebgframes;
        for (int i = this.m_fadebgframes; i > 0; --i) {
            this.g_off.setColor(new Color(red2 + (int)(i * n), green2 + (int)(i * n2), blue2 + (int)(i * n3)));
            final int n4 = this.m_cur_b_thick[this.k];
            this.g_off.fillRect(n4, n4, this.d.width - n4 * 2, this.d.height - n4 * 2);
            this.paintIt();
            try {
                Thread.sleep(this.m_fadebgdelay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void figureFade(final boolean b, final Color color, final Color color2) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = color2.getRed();
        final int green2 = color2.getGreen();
        final int blue2 = color2.getBlue();
        int n = this.m_fadein_frames;
        if (b) {
            n = this.m_fadeout_frames;
        }
        final float n2 = (red - red2) / n;
        final float n3 = (green - green2) / n;
        final float n4 = (blue - blue2) / n;
        if (b) {
            for (int i = n; i > 0; --i) {
                if (this.mouse_in) {
                    this.HL();
                    this.paintIt();
                    return;
                }
                this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], new Color(red2 + (int)(i * n2), green2 + (int)(i * n3), blue2 + (int)(i * n4)), this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_cur_b_color[this.k], this.m_cur_b_thick[this.k], true, this.m_cur_underline[this.k]);
                this.paintIt();
                try {
                    Thread.sleep(this.m_fadeout_delay);
                }
                catch (InterruptedException ex) {}
            }
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (this.mouse_in) {
                this.HL();
                this.paintIt();
                return;
            }
            this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], new Color(red2 + (int)(j * n2), green2 + (int)(j * n3), blue2 + (int)(j * n4)), this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_cur_b_color[this.k], this.m_cur_b_thick[this.k], true, this.m_cur_underline[this.k]);
            this.paintIt();
            try {
                Thread.sleep(this.m_fadein_delay);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    private void HL() {
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        Color color = this.m_cur_textcolor[this.k];
        if (!this.m_hllinksonly) {
            color = this.m_hltext_color;
            if (this.m_showhand) {
                this.the_frame.setCursor(12);
            }
        }
        else if (!this.m_cur_desturl[this.k].equals("-1")) {
            color = this.m_hltext_color;
            if (this.m_showhand) {
                this.the_frame.setCursor(12);
            }
        }
        this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], color, this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_hlborder_color, this.m_cur_b_thick[this.k], false, this.m_cur_underline[this.k]);
    }
    
    private void UndoHL() {
        this.the_frame.setCursor(0);
        this.drawTextOverImage(this.g_off, this.d, this.m_msg[this.k], this.m_cur_font[this.k], this.m_cur_textcolor[this.k], this.m_margin, this.m_cur_valign[this.k], this.m_cur_halign[this.k], this.m_cur_b_color[this.k], this.m_cur_b_thick[this.k], false, this.m_cur_underline[this.k]);
    }
    
    public void start() {
        if (this.m_SlidingM == null) {
            (this.m_SlidingM = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_SlidingM != null) {
            this.m_SlidingM.stop();
            this.m_SlidingM = null;
        }
    }
    
    public void run() {
        while (true) {
            if (this.tag) {
                if (this.first_run) {
                    this.off = this.createImage(this.d.width, this.d.height);
                    this.g_off = this.off.getGraphics();
                    this.first_run = false;
                }
                this.DrawFade();
            }
            else {
                this.paintIt();
                this.stop();
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mouse_in && !this.m_cur_desturl[this.k].equals("-1")) {
            final String s = this.m_cur_loadwhere[this.k];
            try {
                if (s.equalsIgnoreCase("javascript")) {
                    JSObject.getWindow((Applet)this).eval(this.m_cur_desturl[this.k]);
                }
                else {
                    this.goURL = new URL(this.getDocumentBase(), this.m_cur_desturl[this.k]);
                }
            }
            catch (MalformedURLException ex) {}
            this.getAppletContext().showDocument(this.goURL, this.m_cur_loadwhere[this.k]);
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouse_in = true;
        if (this.paused) {
            this.HL();
            this.paintIt();
        }
        this.showStatus(this.m_onsbtext);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouse_in = false;
        if (this.paused) {
            this.UndoHL();
            this.paintIt();
        }
        this.showStatus(this.m_offsbtext);
        return true;
    }
    
    private void pauseIt() {
        this.paused = true;
        try {
            Thread.sleep(this.m_cur_delay[this.k]);
        }
        catch (InterruptedException ex) {}
        while (this.mouse_in) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
            if (!this.mouse_in) {
                try {
                    Thread.sleep(this.m_mouse_delay);
                }
                catch (InterruptedException ex3) {}
            }
        }
        this.paused = false;
    }
    
    private boolean drawTextOverImage(final Graphics graphics, final Dimension dimension, final String temp, final Font font, final Color color, final int n, final int n2, final int n3, final Color color2, final int n4, final boolean b, final boolean b2) {
        try {
            graphics.setFont(font);
            final FontMetrics fontMetrics = graphics.getFontMetrics(font);
            if (!b) {
                graphics.setColor(color2);
                for (int i = 0; i < n4; ++i) {
                    graphics.drawRect(i, i, dimension.width - i * 2 - 1, dimension.height - i * 2 - 1);
                }
                this.ml = ocwordw.WrapText(temp, dimension.width - n * 2 - n4 * 2, fontMetrics);
            }
            graphics.setColor(color);
            final int n5 = fontMetrics.getHeight() * this.ml.length + fontMetrics.getAscent() / 2;
            int n6 = n4;
            if (n2 == 1) {
                n6 = (dimension.height - n5) / 2;
            }
            if (n2 == 2) {
                n6 = dimension.height - n4 - n5;
            }
            for (int j = 0; j < this.ml.length; ++j) {
                if (n3 == 1) {
                    final int n7 = dimension.width / 2 - fontMetrics.stringWidth(this.ml[j]) / 2;
                    final int n8 = n6 + (j + 1) * fontMetrics.getHeight();
                    if (b2) {
                        graphics.drawLine(n7 + 1, n8 + 1, n7 + fontMetrics.stringWidth(this.ml[j]) - 1, n8 + 1);
                    }
                    graphics.drawString(this.ml[j], n7, n8);
                }
                else if (n3 == 0) {
                    final int n9 = n + n4;
                    final int n10 = n6 + (j + 1) * fontMetrics.getHeight();
                    if (b2) {
                        graphics.drawLine(n9 + 1, n10 + 1, n9 + fontMetrics.stringWidth(this.ml[j]) - 1, n10 + 1);
                    }
                    graphics.drawString(this.ml[j], n9, n10);
                }
                else if (n3 == 2) {
                    graphics.drawString(this.ml[j], dimension.width - (fontMetrics.stringWidth(this.ml[j]) + n + n4), n6 + (j + 1) * fontMetrics.getHeight());
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    static int getAlignValue(final String s) {
        if (s.equalsIgnoreCase("right") || s.equalsIgnoreCase("bottom")) {
            return 2;
        }
        if (s.equalsIgnoreCase("center")) {
            return 1;
        }
        return 0;
    }
    
    public FadeText() {
        this.m_showhand = false;
        this.paused = false;
        this.tag = false;
        this.mouse_in = false;
        this.bad_descrips = false;
        this.first_pass = true;
        this.multi_line = false;
        this.first_run = true;
        this.m_delay = 2000;
        this.m_mouse_delay = 1000;
        this.m_messagefont = "Arial";
        this.m_bgcolor = Color.white;
        this.m_textcolor = Color.black;
        this.m_loadwhere = "_self";
        this.m_margin = 5;
        this.m_b_color = Color.black;
        this.m_fadein_frames = 50;
        this.m_fadeout_frames = 50;
        this.m_fadein_delay = 10;
        this.m_fadeout_delay = 10;
        this.m_font = new Font("Hevletica", 0, 12);
        this.m_valign = 1;
        this.m_halign = 1;
        this.m_fade_bg = true;
        this.m_underline = false;
        this.m_hltext_color = Color.yellow;
        this.m_hlborder_color = Color.yellow;
        this.m_hllinksonly = true;
        this.m_fadebgframes = 50;
        this.m_fadebgdelay = 10;
        this.m_onsbtext = "OpenCube - Fading Messages";
        this.m_offsbtext = "Java by OpenCube";
    }
}
