import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.Vector;
import java.util.Properties;
import java.awt.Font;
import java.awt.Color;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class mflip extends Applet implements Runnable
{
    URL goURL;
    Dimension d;
    Thread the_applet;
    Image hd_off;
    Image msg_off;
    Image off;
    Graphics g_off;
    FontMetrics fm;
    boolean tag;
    boolean first_run;
    boolean skip_first;
    boolean mouse_in;
    boolean mouse_msg;
    boolean hlhd;
    boolean hlmsg;
    boolean rolling;
    private boolean m_showhand;
    private Frame the_frame;
    int mouse_x;
    int num_headings;
    int max_messages;
    int[] num_messages;
    int hd_height;
    int msg_height;
    int msg_width;
    int msg_start;
    int cur_hd;
    int cur_msg;
    int cur_hdy;
    int cur_msgy;
    long next_time;
    private String m_onsbtext;
    private String m_offsbtext;
    private Color m_bgcolor;
    private int m_yoffset;
    private int m_hdwidth;
    private Font m_hdfont;
    private Font m_msgfont;
    private int m_hdmargin;
    private int m_msgmargin;
    private Color m_hd_textcolor;
    private int m_scroll_delay;
    private int m_jump_size;
    private int m_delay_time;
    private int m_mouse_delay_time;
    private Color m_hdbgcolor;
    private Color m_msg_textcolor;
    private boolean m_start_showing;
    private Color m_border_color;
    private Color m_border_hlcolor;
    private int m_border_size;
    private String m_loadwhere;
    private boolean m_unhd;
    private boolean m_unmsg;
    private Color m_hdhl;
    private Color m_msghl;
    private boolean m_hlunderline;
    private int[][] m_msg_coord;
    private int[] m_hd_coord;
    private String[] m_heading;
    private Color[] m_cur_hdbgcolor;
    private Color[] m_cur_msgbgcolor;
    private Color[] m_curhd_textcolor;
    private Color[] m_curmsg_textcolor;
    private String[][] m_message;
    private String[][] m_desturl;
    private String[][] m_cur_loadwhere;
    private String[] m_hdloadwhere;
    private String[] m_hddesturl;
    
    public void init() {
        this.d = this.size();
        int num_headings = 0;
        int max_messages = 0;
        final String s = "Message Flipper (Developer Version), Copyright (c) 2000, OpenCube Inc.";
        String s2 = this.getParameter("Notice");
        if (s2 == null) {
            this.m_onsbtext = "Missing 'Notice' Tag";
            this.m_offsbtext = "Missing 'Notice' Tag";
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
            final String property = properties.getProperty("msgbgcolor");
            if (property != null) {
                this.m_bgcolor = occcolor.ConvertColor(property);
            }
            this.setBackground(this.m_bgcolor);
            final String property2 = properties.getProperty("showhand");
            if (property2 != null) {
                this.m_showhand = Boolean.valueOf(property2);
            }
            final String property3 = properties.getProperty("bordersize");
            if (property3 != null) {
                this.m_border_size = Integer.parseInt(property3);
            }
            final String property4 = properties.getProperty("msgtextcolor");
            if (property4 != null) {
                this.m_msg_textcolor = occcolor.ConvertColor(property4);
            }
            final String property5 = properties.getProperty("hdhlcolor");
            if (property5 != null) {
                this.m_hdhl = occcolor.ConvertColor(property5);
            }
            final String property6 = properties.getProperty("msghlcolor");
            if (property6 != null) {
                this.m_msghl = occcolor.ConvertColor(property6);
            }
            final String property7 = properties.getProperty("loadwhere");
            if (property7 != null) {
                this.m_loadwhere = property7;
            }
            final String property8 = properties.getProperty("hdbgcolor");
            if (property8 != null) {
                this.m_hdbgcolor = occcolor.ConvertColor(property8);
            }
            final String property9 = properties.getProperty("hdtextcolor");
            if (property9 != null) {
                this.m_hd_textcolor = occcolor.ConvertColor(property9);
            }
            final String property10 = properties.getProperty("bordercolor");
            if (property10 != null) {
                this.m_border_color = occcolor.ConvertColor(property10);
            }
            final String property11 = properties.getProperty("borderhlcolor");
            if (property11 != null) {
                this.m_border_hlcolor = occcolor.ConvertColor(property11);
            }
            final String property12 = properties.getProperty("hdwidth");
            if (property12 != null) {
                this.m_hdwidth = Integer.parseInt(property12);
            }
            final String property13 = properties.getProperty("yoffset");
            if (property13 != null) {
                this.m_yoffset = Integer.parseInt(property13);
            }
            final String property14 = properties.getProperty("scrolldelay");
            if (property14 != null) {
                this.m_scroll_delay = Integer.parseInt(property14);
            }
            final String property15 = properties.getProperty("jumpsize");
            if (property15 != null) {
                this.m_jump_size = Integer.parseInt(property15);
            }
            final String property16 = properties.getProperty("pausetime");
            if (property16 != null) {
                this.m_delay_time = Integer.parseInt(property16);
            }
            final String property17 = properties.getProperty("mousepausetime");
            if (property17 != null) {
                this.m_mouse_delay_time = Integer.parseInt(property17);
            }
            final String property18 = properties.getProperty("hdmargin");
            if (property18 != null) {
                this.m_hdmargin = Integer.parseInt(property18);
            }
            final String property19 = properties.getProperty("msgmargin");
            if (property19 != null) {
                this.m_msgmargin = Integer.parseInt(property19);
            }
            final String property20 = properties.getProperty("hdfont");
            if (property20 != null) {
                this.m_hdfont = ocfontc.getFontSD(property20, ",");
            }
            final String property21 = properties.getProperty("msgfont");
            if (property21 != null) {
                this.m_msgfont = ocfontc.getFontSD(property21, ",");
            }
            final String property22 = properties.getProperty("startshowing");
            if (property22 != null) {
                this.m_start_showing = Boolean.valueOf(property22);
            }
            final String property23 = properties.getProperty("hlunderline");
            if (property23 != null) {
                this.m_hlunderline = Boolean.valueOf(property23);
            }
            final String property24 = properties.getProperty("hdunderline");
            if (property24 != null) {
                this.m_unhd = Boolean.valueOf(property24);
            }
            final String property25 = properties.getProperty("msgunderline");
            if (property25 != null) {
                this.m_unmsg = Boolean.valueOf(property25);
            }
            final Vector<Integer> vector = new Vector<Integer>();
            while (properties.getProperty("heading" + num_headings) != null) {
                while (properties.getProperty("msg" + num_headings + "-" + max_messages) != null) {
                    ++max_messages;
                }
                if (max_messages == 0) {
                    max_messages = 1;
                }
                vector.addElement(new Integer(max_messages));
                if (max_messages > this.max_messages) {
                    this.max_messages = max_messages;
                }
                max_messages = 0;
                ++num_headings;
            }
            this.num_headings = num_headings;
            this.num_messages = new int[num_headings];
            for (int i = 0; i < this.num_messages.length; ++i) {
                this.num_messages[i] = vector.elementAt(i);
            }
            this.m_heading = new String[this.num_headings];
            this.m_cur_hdbgcolor = new Color[this.num_headings];
            this.m_curhd_textcolor = new Color[this.num_headings];
            this.m_curmsg_textcolor = new Color[this.num_headings];
            this.m_cur_msgbgcolor = new Color[this.num_headings];
            this.m_hddesturl = new String[this.num_headings];
            this.m_hdloadwhere = new String[this.num_headings];
            this.m_hd_coord = new int[this.num_headings];
            this.m_message = new String[this.num_headings][this.max_messages];
            this.m_desturl = new String[this.num_headings][this.max_messages];
            this.m_cur_loadwhere = new String[this.num_headings][this.max_messages];
            this.m_msg_coord = new int[this.num_headings][this.max_messages];
            for (int j = 0; j < this.num_headings; ++j) {
                final String property26 = properties.getProperty("heading" + j);
                if (property26 != null) {
                    this.m_heading[j] = property26;
                }
                else {
                    this.m_heading[j] = " ";
                }
                final String property27 = properties.getProperty("hddesturl" + j);
                if (property27 != null) {
                    this.m_hddesturl[j] = property27;
                }
                else {
                    this.m_hddesturl[j] = "-1";
                }
                final String property28 = properties.getProperty("hdloadwhere" + j);
                if (property28 != null) {
                    this.m_hdloadwhere[j] = property28;
                }
                else {
                    this.m_hdloadwhere[j] = this.m_loadwhere;
                }
                final String property29 = properties.getProperty("hdbgcolor" + j);
                if (property29 != null) {
                    this.m_cur_hdbgcolor[j] = occcolor.ConvertColor(property29);
                }
                else {
                    this.m_cur_hdbgcolor[j] = this.m_hdbgcolor;
                }
                final String property30 = properties.getProperty("msgbgcolor" + j);
                if (property30 != null) {
                    this.m_cur_msgbgcolor[j] = occcolor.ConvertColor(property30);
                }
                else {
                    this.m_cur_msgbgcolor[j] = this.m_bgcolor;
                }
                final String property31 = properties.getProperty("hdtextcolor" + j);
                if (property31 != null) {
                    this.m_curhd_textcolor[j] = occcolor.ConvertColor(property31);
                }
                else {
                    this.m_curhd_textcolor[j] = this.m_hd_textcolor;
                }
                final String property32 = properties.getProperty("msgtextcolor" + j);
                if (property32 != null) {
                    this.m_curmsg_textcolor[j] = occcolor.ConvertColor(property32);
                }
                else {
                    this.m_curmsg_textcolor[j] = this.m_msg_textcolor;
                }
                for (int k = 0; k < this.num_messages[j]; ++k) {
                    final String property33 = properties.getProperty("msg" + j + "-" + k);
                    if (property33 != null) {
                        this.m_message[j][k] = property33;
                    }
                    else {
                        this.m_message[j][k] = " ";
                    }
                    final String property34 = properties.getProperty("msgdesturl" + j + "-" + k);
                    if (property34 != null) {
                        this.m_desturl[j][k] = property34;
                    }
                    else {
                        this.m_desturl[j][k] = "-1";
                    }
                    final String property35 = properties.getProperty("msgloadwhere" + j + "-" + k);
                    if (property35 != null) {
                        this.m_cur_loadwhere[j][k] = property35;
                    }
                    else {
                        this.m_cur_loadwhere[j][k] = this.m_loadwhere;
                    }
                }
            }
            this.m_hdmargin += this.m_border_size;
            return;
        }
        if (key.equals("-1")) {
            this.m_onsbtext = "Licence Error: Invalid URL";
            this.m_offsbtext = "Licence Error: Invalid URL";
            return;
        }
        this.m_onsbtext = "Incorrect Copyright Notice in 'Notice' tag";
        this.m_offsbtext = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    public void start() {
        if (!this.tag) {
            return;
        }
        if (this.cur_msg > 0) {
            this.skip_first = true;
        }
        if (this.the_applet == null) {
            (this.the_applet = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.the_applet != null) {
            this.the_applet.stop();
            this.the_applet = null;
        }
    }
    
    public void run() {
        if (this.first_run) {
            this.off = this.createImage(this.d.width, this.d.height);
            this.drawHeadings();
            this.drawMessages();
            this.g_off = this.off.getGraphics();
            if (this.m_border_size > 0) {
                this.drawBorder(false);
            }
            if (this.m_start_showing) {
                this.g_off.drawImage(this.hd_off, 0, 0, this);
                this.g_off.drawImage(this.msg_off, this.msg_start, 0, this);
                this.getGraphics().drawImage(this.off, 0, 0, this);
                this.cur_hdy = 0;
                this.cur_msgy = 0;
                this.skip_first = true;
                this.pauseIt();
            }
            else {
                this.cur_hdy = this.d.height;
                this.cur_msgy = this.d.height;
            }
            this.first_run = false;
        }
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.flipThem();
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.mouse_in) {
            String s;
            String s2;
            if (!this.mouse_msg) {
                s = this.m_cur_loadwhere[this.cur_hd][this.cur_msg];
                s2 = this.m_desturl[this.cur_hd][this.cur_msg];
            }
            else {
                s2 = this.m_hddesturl[this.cur_hd];
                s = this.m_hdloadwhere[this.cur_hd];
            }
            if (!s2.equals("-1")) {
                try {
                    if (s.equalsIgnoreCase("javascript")) {
                        JSObject.getWindow((Applet)this).eval(s2);
                    }
                    else {
                        this.goURL = new URL(this.getDocumentBase(), s2);
                    }
                }
                catch (MalformedURLException ex) {}
                this.getAppletContext().showDocument(this.goURL, s);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int mouse_x, final int n) {
        this.mouse_in = true;
        this.checkM(this.mouse_x = mouse_x);
        this.drawBorder(true);
        this.paintIt();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouse_in = false;
        if (this.hlmsg) {
            this.hlmsg(false);
            this.hlmsg = false;
        }
        if (this.hlhd) {
            this.hlhd(false);
            this.hlhd = false;
        }
        this.drawBorder(false);
        this.paintIt();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mouse_x, final int n) {
        this.checkM(this.mouse_x = mouse_x);
        return true;
    }
    
    private void checkM(final int n) {
        if (this.rolling) {
            return;
        }
        if (this.m_hdwidth > n) {
            if (this.mouse_in) {
                this.mouse_msg = true;
                if (!this.hlhd) {
                    if (!this.m_hddesturl[this.cur_hd].equals("-1")) {
                        this.hlhd(true);
                    }
                    if (this.hlmsg) {
                        this.hlmsg(false);
                        this.hlmsg = false;
                    }
                    this.paintIt();
                }
            }
        }
        else if (this.mouse_in) {
            this.mouse_msg = false;
            if (!this.hlmsg) {
                if (!this.m_desturl[this.cur_hd][this.cur_msg].equals("-1")) {
                    this.hlmsg(true);
                }
                if (this.hlhd) {
                    this.hlhd(false);
                    this.hlhd = false;
                }
                this.paintIt();
            }
        }
    }
    
    private void hlhd(final boolean b) {
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        if (b) {
            if (this.m_showhand) {
                this.the_frame.setCursor(12);
            }
            this.g_off.setColor(this.m_hdhl);
            this.hlhd = true;
        }
        else {
            this.the_frame.setCursor(0);
            this.g_off.setColor(this.m_hd_textcolor);
            this.hlhd = false;
        }
        this.fm = this.getFontMetrics(this.m_hdfont);
        this.g_off.setFont(this.m_hdfont);
        this.g_off.drawString(this.m_heading[this.cur_hd], this.m_hdmargin, this.m_hd_coord[0]);
        if (this.m_unhd && !this.m_heading[this.cur_hd].equals(" ") && this.m_hlunderline) {
            this.g_off.drawLine(this.m_hdmargin, this.m_hd_coord[0] + 1, this.m_hdmargin + this.fm.stringWidth(this.m_heading[this.cur_hd]), this.m_hd_coord[0] + 1);
        }
    }
    
    private void hlmsg(final boolean b) {
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        if (b) {
            if (this.m_showhand) {
                this.the_frame.setCursor(12);
            }
            this.g_off.setColor(this.m_msghl);
            this.hlmsg = true;
        }
        else {
            this.the_frame.setCursor(0);
            this.g_off.setColor(this.m_msg_textcolor);
            this.hlmsg = false;
        }
        this.fm = this.getFontMetrics(this.m_msgfont);
        this.g_off.setFont(this.m_msgfont);
        this.g_off.drawString(this.m_message[this.cur_hd][this.cur_msg], this.msg_start + this.m_msgmargin, this.m_msg_coord[0][0]);
        if (this.m_unmsg && !this.m_message[this.cur_hd][this.cur_msg].equals(" ") && this.m_hlunderline) {
            this.g_off.drawLine(this.m_msgmargin + this.msg_start, this.m_msg_coord[0][0] + 1, this.msg_start + this.m_msgmargin + this.fm.stringWidth(this.m_message[this.cur_hd][this.cur_msg]), this.m_msg_coord[0][0] + 1);
        }
    }
    
    private void drawBorder(final boolean b) {
        this.g_off = this.off.getGraphics();
        if (b) {
            this.g_off.setColor(this.m_border_hlcolor);
        }
        else {
            this.g_off.setColor(this.m_border_color);
        }
        for (int i = 0; i < this.m_border_size; ++i) {
            this.g_off.drawRect(i, i, this.d.width - i * 2 - 1, this.d.height - i * 2 - 1);
        }
        final int border_size = this.m_border_size;
        this.g_off.clipRect(border_size, border_size, this.d.width - border_size * 2, this.d.height - border_size * 2);
    }
    
    private void flipThem() {
        final Graphics graphics = this.getGraphics();
        while (this.cur_hd < this.num_headings) {
            if (!this.skip_first) {
                this.skip_first = true;
                for (int i = 0; i > -this.d.height; i -= this.m_jump_size) {
                    this.g_off.drawImage(this.hd_off, 0, this.cur_hdy + i, this);
                    this.g_off.drawImage(this.msg_off, this.msg_start, this.cur_msgy + i, this);
                    graphics.drawImage(this.off, 0, 0, this);
                    try {
                        Thread.sleep(this.m_scroll_delay);
                    }
                    catch (InterruptedException ex) {}
                }
                this.cur_msg = 0;
                this.g_off.drawImage(this.hd_off, 0, this.cur_hdy - this.d.height, this);
                this.g_off.drawImage(this.msg_off, this.msg_start, this.cur_msgy - this.d.height, this);
                graphics.drawImage(this.off, 0, 0, this);
                this.cur_hdy -= this.d.height;
                this.cur_msgy -= this.d.height;
                this.pauseIt();
            }
            else {
                this.skip_first = false;
            }
            this.skip_first = true;
            for (int j = this.cur_msg; j < this.num_messages[this.cur_hd] - 1; ++j) {
                for (int k = 0; k > -this.d.height; k -= this.m_jump_size) {
                    this.g_off.drawImage(this.msg_off, this.msg_start, this.cur_msgy + k, this);
                    graphics.drawImage(this.off, 0, 0, this);
                    try {
                        Thread.sleep(this.m_scroll_delay);
                    }
                    catch (InterruptedException ex2) {}
                }
                this.cur_msg = j + 1;
                this.g_off.drawImage(this.msg_off, this.msg_start, this.cur_msgy - this.d.height, this);
                graphics.drawImage(this.off, 0, 0, this);
                this.cur_msgy -= this.d.height;
                this.pauseIt();
            }
            this.skip_first = false;
            ++this.cur_hd;
        }
        this.cur_hd = 0;
        this.cur_hdy = this.d.height;
        this.cur_msgy = this.d.height;
    }
    
    private void pauseIt() {
        this.rolling = false;
        if (this.mouse_in) {
            this.checkM(this.mouse_x);
        }
        try {
            Thread.sleep(this.m_delay_time);
        }
        catch (InterruptedException ex) {}
        while (this.mouse_in) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
            if (!this.mouse_in) {
                try {
                    Thread.sleep(this.m_mouse_delay_time);
                }
                catch (InterruptedException ex3) {}
            }
        }
        this.rolling = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.tag) {
            graphics.drawString(this.m_onsbtext, 5, 20);
            return;
        }
        this.paintIt();
    }
    
    public synchronized void paintIt() {
        this.getGraphics().drawImage(this.off, 0, 0, this);
    }
    
    private void drawHeadings() {
        this.hd_height = this.d.height * this.num_headings;
        this.fm = this.getFontMetrics(this.m_hdfont);
        if (this.m_hdwidth < 0) {
            int stringWidth = 0;
            for (int i = 0; i < this.num_headings; ++i) {
                if (this.fm.stringWidth(this.m_heading[i]) > stringWidth) {
                    stringWidth = this.fm.stringWidth(this.m_heading[i]);
                }
            }
            this.m_hdwidth = stringWidth + this.m_hdmargin * 2;
        }
        this.hd_off = this.createImage(this.m_hdwidth, this.hd_height);
        (this.g_off = this.hd_off.getGraphics()).setFont(this.m_hdfont);
        int n = 0;
        for (int j = 0; j < this.num_headings; ++j) {
            this.g_off.setColor(this.m_cur_hdbgcolor[j]);
            this.g_off.fillRect(0, n, this.m_hdwidth, n + this.d.height);
            this.g_off.setColor(this.m_curhd_textcolor[j]);
            final int n2 = n + this.d.height / 2 + this.fm.getDescent() - this.m_yoffset;
            this.m_hd_coord[j] = n2;
            this.g_off.drawString(this.m_heading[j], this.m_hdmargin, n2);
            if (this.m_unhd && !this.m_heading[j].equals(" ")) {
                this.g_off.drawLine(this.m_hdmargin, n2 + 1, this.m_hdmargin + this.fm.stringWidth(this.m_heading[j]), n2 + 1);
            }
            n += this.d.height;
        }
    }
    
    private void drawMessages() {
        for (int i = 0; i < this.num_messages.length; ++i) {
            this.msg_height += this.d.height * this.num_messages[i];
        }
        if (this.msg_height < 1) {
            this.msg_height = this.d.height;
        }
        this.msg_width = this.d.width - this.m_hdwidth;
        this.msg_start = this.d.width - this.msg_width;
        this.fm = this.getFontMetrics(this.m_msgfont);
        this.msg_off = this.createImage(this.msg_width, this.msg_height);
        (this.g_off = this.msg_off.getGraphics()).setFont(this.m_msgfont);
        int n = 0;
        for (int j = 0; j < this.num_messages.length; ++j) {
            for (int k = 0; k < this.num_messages[j]; ++k) {
                this.g_off.setColor(this.m_cur_msgbgcolor[j]);
                this.g_off.fillRect(0, n, this.msg_width, n + this.d.height * this.num_messages[j]);
                this.g_off.setColor(this.m_curmsg_textcolor[j]);
                final int n2 = n + this.d.height / 2 + this.fm.getDescent() - this.m_yoffset;
                this.m_msg_coord[j][k] = n2;
                this.g_off.drawString(this.m_message[j][k], this.m_msgmargin, n2);
                if (this.m_unmsg && !this.m_message[j][k].equals(" ")) {
                    this.g_off.drawLine(this.m_msgmargin, n2 + 1, this.m_msgmargin + this.fm.stringWidth(this.m_message[j][k]), n2 + 1);
                }
                n += this.d.height;
            }
        }
    }
    
    public mflip() {
        this.first_run = true;
        this.skip_first = false;
        this.mouse_in = false;
        this.mouse_msg = false;
        this.hlhd = false;
        this.hlmsg = false;
        this.rolling = true;
        this.m_showhand = false;
        this.m_onsbtext = "Java by OpenCube";
        this.m_offsbtext = "Message Flipper";
        this.m_bgcolor = Color.white;
        this.m_hdwidth = -1;
        this.m_hdfont = new Font("Helvetica", 0, 11);
        this.m_msgfont = new Font("Helvetica", 0, 11);
        this.m_hdmargin = 5;
        this.m_msgmargin = 10;
        this.m_hd_textcolor = Color.white;
        this.m_scroll_delay = 10;
        this.m_jump_size = 2;
        this.m_delay_time = 4000;
        this.m_mouse_delay_time = 1000;
        this.m_hdbgcolor = new Color(153, 0, 0);
        this.m_msg_textcolor = Color.black;
        this.m_start_showing = true;
        this.m_border_color = Color.black;
        this.m_border_hlcolor = Color.yellow;
        this.m_border_size = 2;
        this.m_loadwhere = "_self";
        this.m_unhd = false;
        this.m_unmsg = false;
        this.m_hdhl = Color.blue;
        this.m_msghl = Color.blue;
        this.m_hlunderline = true;
    }
}
