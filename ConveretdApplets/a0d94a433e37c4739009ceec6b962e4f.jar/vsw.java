import java.awt.MediaTracker;
import java.util.Vector;
import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Color;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vsw extends Applet implements Runnable
{
    Font font;
    FontMetrics fm;
    Font loadfont;
    Thread trun;
    Thread fr;
    vswread the_file;
    Dimension d;
    Image off;
    Graphics g_off;
    Graphics g_erase;
    Image erase;
    URL goURL;
    String connect_message;
    Color ignore_color;
    boolean mbarpaused;
    boolean first_go;
    boolean scrollafterlink;
    boolean dragged;
    boolean break_pause;
    boolean tag;
    long next_time;
    int reload_count;
    int my;
    int mx;
    int hLine;
    boolean mouseIn;
    int l_h;
    int soff;
    boolean update_si;
    boolean in_hl;
    int old_scrolly;
    int num_i;
    int p_inx;
    int num_links;
    int temp_links;
    int[] link_cords;
    String[] link_urls;
    int[] line_coords;
    int[] re_draw;
    int num_descrips;
    int scroll_y;
    int last_h;
    int cur_dline;
    int hold_move;
    int link_ref;
    int i_h;
    String later_string;
    boolean try_later;
    boolean force_draw;
    boolean done_draw;
    boolean first_run;
    boolean paused;
    boolean mdown;
    boolean fetching_file;
    boolean barpaused;
    private boolean m_showhand;
    private boolean m_paramsinfile;
    private Frame the_frame;
    private boolean m_underline_links;
    private int m_top_margin;
    private int m_icon_base_off;
    private int m_def_delay;
    private int m_def_centertext;
    private int m_scroll_delay;
    private Color m_def_textcolor;
    private Color m_bgcolor;
    private Color m_def_hlcolor;
    private String m_sb_text;
    private int m_xoffset;
    private String m_def_loadwhere;
    private String m_offsbtext;
    private String m_file_location;
    private String m_sb_update_message;
    private int m_file_update_scrolls;
    private boolean m_wait_for_file;
    private int m_jump;
    private Color m_load_color;
    private String m_load_msg;
    private int[] m_size_pause;
    private int[] m_delay;
    private Color[] m_textcolor;
    private String[] m_desc;
    private String[] m_linkurl;
    private Font[] m_font;
    private int[] m_align;
    private String[] m_loadwhere;
    private String[] m_icon_file;
    private String[] m_icon_sfile;
    private int[] m_icon_id;
    private boolean m_indent_all;
    private int m_si;
    private Color m_bc;
    private Color m_ac;
    private Color m_hc;
    private Color m_bgc;
    private Color m_sc;
    private Color m_oc;
    private int m_bar_width;
    private int m_sb_line_dist;
    boolean first_connect_message;
    boolean first_update_message;
    Image[] icon;
    Image[] sicon;
    boolean bg_stat;
    vswsb sb;
    Dimension sb_size;
    int sb_width;
    boolean m_sbright;
    
    public void init() {
        final String s = "Vertical Scrolling Window (Developer Version), Copyright (c) 2000, OpenCube Inc.";
        this.d = this.size();
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.m_sb_text = "Missing 'Notice' Tag";
            return;
        }
        final String key = evalkey.getKey(parameter, this.getDocumentBase());
        if (key.equals(s)) {
            this.tag = true;
            String s2 = this.getParameter("paramsinfile");
            if (s2 != null) {
                this.m_paramsinfile = Boolean.valueOf(s2);
            }
            final Properties properties = new Properties();
            if (this.m_paramsinfile) {
                try {
                    s2 = this.getParameter("paramlocation");
                    if (s2 == null) {
                        this.m_sb_text = "Missing \"paramlocation\" parameter";
                        this.tag = false;
                        return;
                    }
                    final InputStream openStream = new URL(this.getDocumentBase(), s2).openStream();
                    properties.load(openStream);
                    openStream.close();
                }
                catch (Exception ex) {
                    this.m_sb_text = "Error loading param file: \"" + s2 + "\"";
                    this.tag = false;
                    return;
                }
            }
            String s3;
            if (this.m_paramsinfile) {
                s3 = properties.getProperty("barbuttoncolor");
            }
            else {
                s3 = this.getParameter("barbuttoncolor");
            }
            if (s3 != null) {
                this.m_bc = occcolor.ConvertColor(s3);
            }
            String s4;
            if (this.m_paramsinfile) {
                s4 = properties.getProperty("bararrowcolor");
            }
            else {
                s4 = this.getParameter("bararrowcolor");
            }
            if (s4 != null) {
                this.m_ac = occcolor.ConvertColor(s4);
            }
            String s5;
            if (this.m_paramsinfile) {
                s5 = properties.getProperty("bararrowhcolor");
            }
            else {
                s5 = this.getParameter("bararrowhcolor");
            }
            if (s5 != null) {
                this.m_hc = occcolor.ConvertColor(s5);
            }
            String s6;
            if (this.m_paramsinfile) {
                s6 = properties.getProperty("barbgcolor");
            }
            else {
                s6 = this.getParameter("barbgcolor");
            }
            if (s6 != null) {
                this.m_bgc = occcolor.ConvertColor(s6);
            }
            String s7;
            if (this.m_paramsinfile) {
                s7 = properties.getProperty("barslidecolor");
            }
            else {
                s7 = this.getParameter("barslidecolor");
            }
            if (s7 != null) {
                this.m_sc = occcolor.ConvertColor(s7);
            }
            String s8;
            if (this.m_paramsinfile) {
                s8 = properties.getProperty("barslideocolor");
            }
            else {
                s8 = this.getParameter("barslideocolor");
            }
            if (s8 != null) {
                this.m_oc = occcolor.ConvertColor(s8);
            }
            String s9;
            if (this.m_paramsinfile) {
                s9 = properties.getProperty("barslideinset");
            }
            else {
                s9 = this.getParameter("barslideinset");
            }
            if (s9 != null) {
                this.m_si = Integer.parseInt(s9);
            }
            String s10;
            if (this.m_paramsinfile) {
                s10 = properties.getProperty("barwidth");
            }
            else {
                s10 = this.getParameter("barwidth");
            }
            if (s10 != null) {
                this.m_bar_width = Integer.parseInt(s10);
            }
            (this.sb = new vswsb(this.m_bar_width, this.d.height)).setBackground(this.m_bgc);
            this.add(this.sb);
            final BorderLayout borderLayout;
            this.setLayout(borderLayout = new BorderLayout(20, 20));
            borderLayout.removeLayoutComponent(this.sb);
            this.sb.reshape(this.d.width - this.m_bar_width, 0, this.m_bar_width, this.d.height);
            this.d = new Dimension(this.d.width - this.m_bar_width, this.d.height);
            this.sb.setBarStyle(this.m_si, this.m_bc, this.m_ac, this.m_hc, this.m_bgc, this.m_sc, this.m_oc);
            String s11;
            if (this.m_paramsinfile) {
                s11 = properties.getProperty("barlinejump");
            }
            else {
                s11 = this.getParameter("barlinejump");
            }
            if (s11 != null) {
                this.m_sb_line_dist = Integer.parseInt(s11);
            }
            String s12;
            if (this.m_paramsinfile) {
                s12 = properties.getProperty("font");
            }
            else {
                s12 = this.getParameter("font");
            }
            if (s12 != null) {
                this.font = ocfontc.getFontSD(s12, ",");
            }
            else {
                this.font = new Font("DialogInput", 0, 10);
            }
            String s13;
            if (this.m_paramsinfile) {
                s13 = properties.getProperty("loadfont");
            }
            else {
                s13 = this.getParameter("loadfont");
            }
            if (s13 != null) {
                this.loadfont = ocfontc.getFontSD(s13, ",");
            }
            else {
                this.loadfont = new Font("Dialog", 0, 12);
            }
            String s14;
            if (this.m_paramsinfile) {
                s14 = properties.getProperty("loadcolor");
            }
            else {
                s14 = this.getParameter("loadcolor");
            }
            if (s14 != null) {
                this.m_load_color = occcolor.ConvertColor(s14);
            }
            String load_msg;
            if (this.m_paramsinfile) {
                load_msg = properties.getProperty("loadmsg");
            }
            else {
                load_msg = this.getParameter("loadmsg");
            }
            if (load_msg != null) {
                this.m_load_msg = load_msg;
            }
            String s15;
            if (this.m_paramsinfile) {
                s15 = properties.getProperty("iconvoff");
            }
            else {
                s15 = this.getParameter("iconvoff");
            }
            if (s15 != null) {
                this.m_icon_base_off = Integer.parseInt(s15);
            }
            String s16;
            if (this.m_paramsinfile) {
                s16 = properties.getProperty("topmargin");
            }
            else {
                s16 = this.getParameter("topmargin");
            }
            if (s16 != null) {
                this.m_top_margin = Integer.parseInt(s16);
            }
            String s17;
            if (this.m_paramsinfile) {
                s17 = properties.getProperty("scrolljump");
            }
            else {
                s17 = this.getParameter("scrolljump");
            }
            if (s17 != null) {
                this.m_jump = Integer.parseInt(s17);
            }
            String s18;
            if (this.m_paramsinfile) {
                s18 = properties.getProperty("refreshscrollinterval");
            }
            else {
                s18 = this.getParameter("refreshscrollinterval");
            }
            if (s18 != null) {
                this.m_file_update_scrolls = Integer.parseInt(s18);
            }
            String file_location;
            if (this.m_paramsinfile) {
                file_location = properties.getProperty("filelocation");
            }
            else {
                file_location = this.getParameter("filelocation");
            }
            if (file_location != null) {
                this.m_file_location = file_location;
                this.connect_message = "Loading: " + file_location;
            }
            String sb_update_message;
            if (this.m_paramsinfile) {
                sb_update_message = properties.getProperty("sbupdatetext");
            }
            else {
                sb_update_message = this.getParameter("sbupdatetext");
            }
            if (sb_update_message != null) {
                this.m_sb_update_message = sb_update_message;
            }
            String s19;
            if (this.m_paramsinfile) {
                s19 = properties.getProperty("waitforfile");
            }
            else {
                s19 = this.getParameter("waitforfile");
            }
            if (s19 != null) {
                this.m_wait_for_file = Boolean.valueOf(s19);
            }
            String s20;
            if (this.m_paramsinfile) {
                s20 = properties.getProperty("alligniconparas");
            }
            else {
                s20 = this.getParameter("alligniconparas");
            }
            if (s20 != null) {
                this.m_indent_all = Boolean.valueOf(s20);
            }
            String s21;
            if (this.m_paramsinfile) {
                s21 = properties.getProperty("underlinelinks");
            }
            else {
                s21 = this.getParameter("underlinelinks");
            }
            if (s21 != null) {
                this.m_underline_links = Boolean.valueOf(s21);
            }
            String s22;
            if (this.m_paramsinfile) {
                s22 = properties.getProperty("scrollafterlink");
            }
            else {
                s22 = this.getParameter("scrollafterlink");
            }
            if (s22 != null) {
                this.scrollafterlink = Boolean.valueOf(s22);
            }
            String s23;
            if (this.m_paramsinfile) {
                s23 = properties.getProperty("allignment");
            }
            else {
                s23 = this.getParameter("allignment");
            }
            if (s23 != null) {
                this.m_def_centertext = this.allignment(s23);
            }
            String def_loadwhere;
            if (this.m_paramsinfile) {
                def_loadwhere = properties.getProperty("loadwhere");
            }
            else {
                def_loadwhere = this.getParameter("loadwhere");
            }
            if (def_loadwhere != null) {
                this.m_def_loadwhere = def_loadwhere;
            }
            String s24;
            if (this.m_paramsinfile) {
                s24 = properties.getProperty("textcolor");
            }
            else {
                s24 = this.getParameter("textcolor");
            }
            if (s24 != null) {
                this.m_def_textcolor = occcolor.ConvertColor(s24);
            }
            String s25;
            if (this.m_paramsinfile) {
                s25 = properties.getProperty("bgcolor");
            }
            else {
                s25 = this.getParameter("bgcolor");
            }
            if (s25 != null) {
                this.m_bgcolor = occcolor.ConvertColor(s25);
            }
            this.setBackground(this.m_bgcolor);
            String s26;
            if (this.m_paramsinfile) {
                s26 = properties.getProperty("delay");
            }
            else {
                s26 = this.getParameter("delay");
            }
            if (s26 != null) {
                this.m_def_delay = Integer.parseInt(s26);
            }
            String s27;
            if (this.m_paramsinfile) {
                s27 = properties.getProperty("scrolldelay");
            }
            else {
                s27 = this.getParameter("scrolldelay");
            }
            if (s27 != null) {
                this.m_scroll_delay = Integer.parseInt(s27);
            }
            String s28;
            if (this.m_paramsinfile) {
                s28 = properties.getProperty("lrmargins");
            }
            else {
                s28 = this.getParameter("lrmargins");
            }
            if (s28 != null) {
                this.m_xoffset = Integer.parseInt(s28);
            }
            String s29;
            if (this.m_paramsinfile) {
                s29 = properties.getProperty("hlcolor");
            }
            else {
                s29 = this.getParameter("hlcolor");
            }
            if (s29 != null) {
                this.m_def_hlcolor = occcolor.ConvertColor(s29);
            }
            String sb_text;
            if (this.m_paramsinfile) {
                sb_text = properties.getProperty("onsbtext");
            }
            else {
                sb_text = this.getParameter("onsbtext");
            }
            if (sb_text != null) {
                this.m_sb_text = sb_text;
            }
            String offsbtext;
            if (this.m_paramsinfile) {
                offsbtext = properties.getProperty("offsbtext");
            }
            else {
                offsbtext = this.getParameter("offsbtext");
            }
            if (offsbtext != null) {
                this.m_offsbtext = offsbtext;
            }
            String s30;
            if (this.m_paramsinfile) {
                s30 = properties.getProperty("showhand");
            }
            else {
                s30 = this.getParameter("showhand");
            }
            if (s30 != null) {
                this.m_showhand = Boolean.valueOf(s30);
            }
            if (this.m_paramsinfile) {
                while (properties.getProperty("icon" + this.num_i) != null) {
                    ++this.num_i;
                }
            }
            else {
                while (this.getParameter("icon" + this.num_i) != null) {
                    ++this.num_i;
                }
            }
            this.m_icon_file = new String[this.num_i];
            this.m_icon_sfile = new String[this.num_i];
            this.icon = new Image[this.num_i];
            this.sicon = new Image[this.num_i];
            for (int i = 0; i < this.num_i; ++i) {
                String s31;
                if (this.m_paramsinfile) {
                    s31 = properties.getProperty("icon" + i);
                }
                else {
                    s31 = properties.getProperty("icon" + i);
                }
                if (s31 != null) {
                    this.m_icon_file[i] = s31;
                }
                else {
                    this.m_icon_file[i] = "-1";
                }
                String s32;
                if (this.m_paramsinfile) {
                    s32 = properties.getProperty("iconhl" + i);
                }
                else {
                    s32 = this.getParameter("iconhl" + i);
                }
                if (s32 != null) {
                    this.m_icon_sfile[i] = s32;
                }
                else {
                    this.m_icon_sfile[i] = "-1";
                }
            }
            this.the_file = new vswread(this, this.m_file_location);
            return;
        }
        if (key.equals("-1")) {
            this.m_sb_text = "Incorrect Base URL Registration";
            return;
        }
        this.m_sb_text = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    public void refreshData() {
        int n = 0;
        this.temp_links = 0;
        this.num_descrips = this.the_file.fileInfo.length;
        this.m_delay = new int[this.num_descrips];
        this.m_textcolor = new Color[this.num_descrips];
        this.m_desc = new String[this.num_descrips];
        this.m_linkurl = new String[this.num_descrips];
        this.m_font = new Font[this.num_descrips];
        this.m_align = new int[this.num_descrips];
        this.m_loadwhere = new String[this.num_descrips];
        this.m_size_pause = new int[this.num_descrips];
        this.m_icon_id = new int[this.num_descrips];
        this.line_coords = new int[this.num_descrips];
        this.re_draw = new int[this.num_descrips];
        for (int i = 0; i < this.the_file.fileInfo.length; ++i) {
            final String[] readDescrips = this.ReadDescrips(this.the_file.fileInfo[n]);
            if (!readDescrips[0].equals("-1")) {
                this.m_icon_id[i] = Integer.parseInt(readDescrips[0]);
                if (this.m_icon_id[i] >= this.num_i) {
                    this.m_icon_id[i] = -1;
                }
            }
            else {
                this.m_icon_id[i] = -1;
            }
            if (!readDescrips[1].equals("-1")) {
                this.m_size_pause[i] = Integer.parseInt(readDescrips[1]);
            }
            else {
                this.m_size_pause[i] = 0;
            }
            if (!readDescrips[2].equals("-1")) {
                this.m_delay[i] = Integer.parseInt(readDescrips[2]);
            }
            else {
                this.m_delay[i] = this.m_def_delay;
            }
            if (!readDescrips[3].equals("-1")) {
                this.m_font[i] = ocfontc.getFontSD(readDescrips[3], ",");
            }
            else {
                this.m_font[i] = this.font;
            }
            if (!readDescrips[4].equals("-1")) {
                this.m_align[i] = this.allignment(readDescrips[4]);
            }
            else {
                this.m_align[i] = this.m_def_centertext;
            }
            if (!readDescrips[5].equals("-1")) {
                this.m_textcolor[i] = occcolor.ConvertColor(readDescrips[5]);
            }
            else {
                this.m_textcolor[i] = this.m_def_textcolor;
            }
            if (!readDescrips[6].equals("-1")) {
                this.m_loadwhere[i] = readDescrips[6];
            }
            else {
                this.m_loadwhere[i] = this.m_def_loadwhere;
            }
            if (!readDescrips[7].equals("-1")) {
                ++this.temp_links;
                this.m_linkurl[i] = readDescrips[7];
            }
            else {
                this.m_linkurl[i] = "-1";
            }
            if (!readDescrips[8].equals("-1")) {
                this.m_desc[i] = readDescrips[8];
            }
            else {
                this.m_desc[i] = "";
            }
            ++n;
        }
    }
    
    public void start() {
        if (!this.tag) {
            return;
        }
        if (!this.first_run) {
            this.DrawBG(this.getGraphics());
            this.the_file.refresh = true;
            this.fetching_file = true;
        }
        if (this.first_run) {
            this.WhereAmI();
        }
        if (this.first_go) {
            (this.trun = new Thread(this)).start();
            (this.fr = new Thread(this.the_file)).start();
            return;
        }
        this.trun.resume();
        this.fr.resume();
    }
    
    public void stop() {
        this.trun.suspend();
        this.fr.suspend();
    }
    
    public void destroy() {
        if (this.trun != null) {
            this.trun.resume();
            this.trun.stop();
            this.trun = null;
        }
        if (this.fr != null) {
            this.fr.resume();
            this.fr.stop();
            this.fr = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.paintIt();
    }
    
    public void paintIt() {
        final Graphics graphics = this.getGraphics();
        if (!this.tag) {
            graphics.drawString(this.m_sb_text, 5, 20);
            return;
        }
        if (this.first_go) {
            graphics.drawImage(this.off, 0, 0, this);
        }
        if (this.done_draw) {
            graphics.drawImage(this.off, 0, this.scroll_y + this.d.height, this);
        }
    }
    
    public void DrawBG(final Graphics graphics) {
        graphics.setColor(this.m_bgcolor);
        graphics.fillRect(0, 0, this.d.width, this.d.height);
    }
    
    public void firstGo() {
        this.sb_size = this.sb.size();
        this.sb_width = this.sb_size.width;
        this.off = this.createImage(this.d.width, this.d.height);
        (this.g_off = this.off.getGraphics()).setColor(this.m_bgcolor);
        this.g_off.fillRect(0, 0, this.d.width, this.d.height);
        this.g_off.setColor(this.m_load_color);
        this.g_off.setFont(this.loadfont);
        final FontMetrics fontMetrics = this.g_off.getFontMetrics(this.loadfont);
        this.g_off.drawString(this.m_load_msg, this.d.width / 2 - fontMetrics.stringWidth(this.m_load_msg) / 2, this.d.height / 2 + fontMetrics.getHeight() / 2);
        this.paintIt();
        for (int i = 0; i < this.num_i; ++i) {
            this.icon[i] = this.trackReturn(this.m_icon_file[i]);
            if ((this.sicon[i] = this.trackReturn(this.m_icon_sfile[i])) == null) {
                this.sicon[i] = this.icon[i];
            }
        }
    }
    
    public void run() {
        while (true) {
            if (this.first_go) {
                this.firstGo();
                this.first_go = false;
            }
            if (this.fetching_file) {
                this.fetchFile();
                if (!this.scrollafterlink) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                    continue;
                }
            }
            if (this.first_run && !this.mdown && !this.barpaused) {
                this.scroll_y -= this.m_jump;
                this.ScrollIt(true);
                if (!this.mdown) {
                    this.mCheck();
                }
            }
            if (this.barpaused) {
                while (this.barpaused) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex2) {}
                }
                this.next_time = System.currentTimeMillis() + 1000L;
                this.mbarpaused = true;
                while (System.currentTimeMillis() < this.next_time) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex3) {}
                    if (this.barpaused) {
                        this.next_time = System.currentTimeMillis() + 1000L;
                    }
                }
                this.mbarpaused = false;
            }
            try {
                Thread.sleep(this.m_scroll_delay);
            }
            catch (InterruptedException ex4) {}
        }
    }
    
    public void fetchFile() {
        if (this.the_file.goodRead) {
            if (!this.first_run) {
                this.first_run = true;
                this.refreshData();
                this.done_draw = false;
                this.DrawScroll();
            }
            this.fetching_file = false;
            this.the_file.goodRead = false;
            if (this.force_draw) {
                this.refreshData();
                this.done_draw = false;
                this.DrawScroll();
                this.scroll_y = 0;
                this.cur_dline = 0;
                this.hLine = -1;
                this.l_h = 0;
                this.force_draw = false;
            }
            else {
                this.update_si = true;
            }
            if (this.mouseIn) {
                this.showStatus(this.m_sb_text);
            }
            else {
                this.showStatus(this.m_offsbtext);
            }
            if (this.try_later) {
                this.try_later = false;
                this.newPage(this.later_string);
            }
        }
        else {
            if (this.the_file.error) {
                this.the_file.error = false;
                this.fetching_file = false;
                this.showStatus("Error Loading Document");
                return;
            }
            if (this.force_draw && this.first_connect_message) {
                this.showStatus(this.connect_message);
                this.first_connect_message = false;
            }
        }
    }
    
    public void ScrollIt(final boolean b) {
        if (b && this.scroll_y + this.d.height <= -this.line_coords[this.cur_dline]) {
            if (this.m_delay[this.cur_dline] > 0) {
                this.paused = true;
                this.scroll_y = -this.line_coords[this.cur_dline] - this.d.height;
                this.drawIt();
                this.next_time = System.currentTimeMillis() + this.m_delay[this.cur_dline];
                while (System.currentTimeMillis() < this.next_time) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                    if (this.break_pause) {
                        this.break_pause = false;
                        break;
                    }
                }
                this.paused = false;
            }
            if (this.cur_dline != this.num_descrips - 1) {
                ++this.cur_dline;
            }
        }
        this.drawIt();
        if (this.scroll_y + this.d.height < -this.i_h) {
            this.scroll_y = 0;
            this.cur_dline = 0;
            if (this.m_file_update_scrolls > 0 && !this.fetching_file) {
                ++this.reload_count;
                if (this.reload_count >= this.m_file_update_scrolls) {
                    this.reload_count = 0;
                    if (this.m_wait_for_file) {
                        this.showStatus(this.m_sb_update_message);
                        this.the_file.goodRead = false;
                        this.fetching_file = true;
                        this.the_file.refresh = true;
                        int n = 0;
                        while (this.fetching_file) {
                            this.fetchFile();
                            if (++n > 300) {
                                break;
                            }
                            try {
                                Thread.sleep(50L);
                            }
                            catch (InterruptedException ex2) {}
                        }
                        this.refreshData();
                        this.done_draw = false;
                        this.DrawScroll();
                        return;
                    }
                    if (this.update_si) {
                        this.update_si = false;
                        this.refreshData();
                        this.done_draw = false;
                        this.DrawScroll();
                    }
                    if (!this.fetching_file) {
                        this.showStatus(this.m_sb_update_message);
                        this.the_file.goodRead = false;
                        this.the_file.refresh = true;
                        this.fetching_file = true;
                    }
                }
            }
        }
    }
    
    public synchronized void drawIt() {
        this.getGraphics().drawImage(this.off, 0, this.scroll_y + this.d.height, this);
        this.sb.setBubblePos(this.scroll_y, this.m_sb_line_dist);
    }
    
    public void DrawScroll() {
        int n = 0;
        int n2 = 0;
        this.num_links = this.temp_links;
        this.link_cords = new int[this.num_links * 3];
        this.link_urls = new String[this.num_links];
        for (int i = 0; i < 2; ++i) {
            int n3 = 0;
            int n4 = 0;
            for (int j = 0; j < this.num_descrips; ++j) {
                if (i == 0) {
                    this.fm = this.getFontMetrics(this.m_font[j]);
                    final int n5 = n3;
                    if (this.m_size_pause[j] == 1) {
                        n4 = n5;
                        n2 = 1;
                    }
                    else if (n2 == 0) {
                        n4 = n5;
                    }
                    String[] array;
                    if (this.m_icon_id[j] != -1) {
                        int width = 0;
                        if (this.icon[this.m_icon_id[j]] != null) {
                            width = this.icon[this.m_icon_id[j]].getWidth(this);
                        }
                        array = this.NewLine(this.m_desc[j], this.m_xoffset * 2, width);
                    }
                    else {
                        array = this.NewLine(this.m_desc[j], this.m_xoffset * 2, 0);
                    }
                    for (int k = 0; k < array.length; ++k) {
                        n3 += this.fm.getHeight();
                    }
                    if (j == this.m_desc.length - 1) {
                        this.g_off.setColor(this.m_bgcolor);
                        this.g_off.fillRect(0, 0, this.d.width, this.d.height);
                        this.getGraphics().drawImage(this.off, 0, 0, this);
                        this.i_h = n3 + this.fm.getDescent() + 5;
                        this.off = this.createImage(this.d.width, this.i_h);
                        (this.g_off = this.off.getGraphics()).setColor(this.m_bgcolor);
                        this.g_off.fillRect(0, 0, this.d.width, this.i_h);
                    }
                    else if (n3 - n4 < this.d.height && this.m_size_pause[j] == 2) {
                        n3 = n3 + (this.d.height - (n3 - n4)) + 1 - this.m_top_margin;
                        n2 = 0;
                    }
                }
                else {
                    this.g_off.setFont(this.m_font[j]);
                    this.fm = this.g_off.getFontMetrics(this.m_font[j]);
                    final int n6 = n3;
                    if (this.m_size_pause[j] == 1) {
                        n4 = n6;
                        n2 = 1;
                    }
                    else if (n2 == 0) {
                        n4 = n6;
                    }
                    this.line_coords[j] = n3 + this.fm.getDescent() + 1 - this.m_top_margin;
                    this.re_draw[j] = n3;
                    int width2 = 0;
                    int height = 0;
                    String[] array2;
                    if (this.m_icon_id[j] != -1) {
                        if (this.icon[this.m_icon_id[j]] != null) {
                            width2 = this.icon[this.m_icon_id[j]].getWidth(this);
                            height = this.icon[this.m_icon_id[j]].getHeight(this);
                        }
                        array2 = this.NewLine(this.m_desc[j], this.m_xoffset * 2, width2);
                    }
                    else {
                        array2 = this.NewLine(this.m_desc[j], this.m_xoffset * 2, 0);
                    }
                    int n7 = 0;
                    for (int l = 0; l < array2.length; ++l) {
                        n3 += this.fm.getHeight();
                        this.g_off.setColor(this.m_textcolor[j]);
                        if (this.m_align[j] == 1) {
                            if (l == 0 && this.m_icon_id[j] != -1) {
                                final int n8 = (this.d.width - (this.fm.stringWidth(array2[l]) + (width2 + 1))) / 2;
                                if (this.icon[this.m_icon_id[j]] != null) {
                                    this.g_off.drawImage(this.icon[this.m_icon_id[j]], n8, n3 + this.m_icon_base_off - height, this);
                                }
                                this.g_off.drawString(array2[l], n8 + width2 + 1, n3);
                            }
                            else {
                                this.g_off.drawString(array2[l], (this.d.width - this.fm.stringWidth(array2[l])) / 2, n3);
                            }
                            if (this.m_underline_links && this.m_linkurl[j].length() > 3) {
                                final int n9 = (this.d.width - this.fm.stringWidth(array2[l])) / 2;
                                final int n10 = n3;
                                this.g_off.drawLine(n9 + 1, n10 + 1, n9 + this.fm.stringWidth(array2[l]) - 1, n10 + 1);
                            }
                        }
                        else if (this.m_align[j] == 2) {
                            if ((l == 0 || this.m_indent_all) && this.m_icon_id[j] != -1) {
                                n7 = width2 + 1;
                                if (l == 0 && this.icon[this.m_icon_id[j]] != null) {
                                    this.g_off.drawImage(this.icon[this.m_icon_id[j]], this.d.width - width2 - this.m_xoffset, n3 + this.m_icon_base_off - height, this);
                                }
                            }
                            this.g_off.drawString(array2[l], this.d.width - this.m_xoffset - this.fm.stringWidth(array2[l]) - n7, n3);
                            if (this.m_underline_links && this.m_linkurl[j].length() > 3) {
                                final int n11 = this.d.width - this.m_xoffset - this.fm.stringWidth(array2[l]) - n7;
                                final int n12 = n3;
                                this.g_off.drawLine(n11 + 1, n12 + 1, n11 + this.fm.stringWidth(array2[l]) - 1, n12 + 1);
                            }
                        }
                        else {
                            if ((l == 0 || this.m_indent_all) && this.m_icon_id[j] != -1) {
                                if (l == 0 && this.icon[this.m_icon_id[j]] != null) {
                                    this.g_off.drawImage(this.icon[this.m_icon_id[j]], this.m_xoffset, n3 + this.m_icon_base_off - height, this);
                                }
                                n7 = width2 + 1;
                            }
                            this.g_off.drawString(array2[l], this.m_xoffset + n7, n3);
                            if (this.m_underline_links && this.m_linkurl[j].length() > 3) {
                                final int n13 = this.m_xoffset + n7;
                                final int n14 = n3;
                                this.g_off.drawLine(n13 + 1, n14 + 1, n13 + this.fm.stringWidth(array2[l]) - 1, n14 + 1);
                            }
                        }
                        n7 = 0;
                    }
                    if (this.m_linkurl[j].length() > 3) {
                        if (j == this.m_desc.length - 1) {
                            n3 += this.fm.getDescent();
                        }
                        this.link_cords[n * 3] = n6;
                        this.link_cords[n * 3 + 1] = n3;
                        this.link_cords[n * 3 + 2] = j;
                        this.link_urls[n] = this.m_linkurl[j];
                        ++n;
                    }
                    if (j != this.m_desc.length - 1 && n3 - n4 < this.d.height && this.m_size_pause[j] == 2) {
                        n3 = n3 + (this.d.height - (n3 - n4)) + 1 - this.m_top_margin;
                        n2 = 0;
                    }
                }
            }
        }
        this.sb.setValues(this.d.height / this.m_sb_line_dist, (this.i_h + this.d.height) / this.m_sb_line_dist + 1);
        this.done_draw = true;
    }
    
    public void HL(final int n, final boolean in_hl) {
        int n2 = this.re_draw[n];
        int n3 = 0;
        this.in_hl = in_hl;
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        Color def_hlcolor;
        if (in_hl) {
            def_hlcolor = this.m_def_hlcolor;
            if (this.m_showhand) {
                this.the_frame.setCursor(12);
            }
        }
        else {
            def_hlcolor = this.m_textcolor[n];
            this.the_frame.setCursor(0);
        }
        this.g_off.setColor(def_hlcolor);
        this.g_off.setFont(this.m_font[n]);
        this.fm = this.g_off.getFontMetrics(this.m_font[n]);
        int width = 0;
        int height = 0;
        boolean b = false;
        String[] array;
        if (this.m_icon_id[n] != -1) {
            if (this.icon[this.m_icon_id[n]] != null) {
                b = true;
                width = this.icon[this.m_icon_id[n]].getWidth(this);
                height = this.icon[this.m_icon_id[n]].getHeight(this);
            }
            array = this.NewLine(this.m_desc[n], this.m_xoffset * 2, width);
        }
        else {
            array = this.NewLine(this.m_desc[n], this.m_xoffset * 2, 0);
        }
        for (int i = 0; i < array.length; ++i) {
            n2 += this.fm.getHeight();
            if (this.m_align[n] == 1) {
                if (i == 0 && this.m_icon_id[n] != -1) {
                    final int n4 = (this.d.width - (this.fm.stringWidth(array[i]) + (width + 1))) / 2;
                    if (b) {
                        if (in_hl) {
                            this.g_off.drawImage(this.sicon[this.m_icon_id[n]], n4, n2 + this.m_icon_base_off - height, this);
                        }
                        else {
                            this.g_off.drawImage(this.icon[this.m_icon_id[n]], n4, n2 + this.m_icon_base_off - height, this);
                        }
                    }
                    this.g_off.drawString(array[i], n4 + width + 1, n2);
                }
                else {
                    this.g_off.drawString(array[i], (this.d.width - this.fm.stringWidth(array[i])) / 2, n2);
                }
                if (this.m_underline_links && this.m_linkurl[n].length() > 3) {
                    final int n5 = (this.d.width - this.fm.stringWidth(array[i])) / 2;
                    final int n6 = n2;
                    this.g_off.drawLine(n5 + 1, n6 + 1, n5 + this.fm.stringWidth(array[i]) - 1, n6 + 1);
                }
            }
            else if (this.m_align[n] == 2) {
                if ((i == 0 || this.m_indent_all) && this.m_icon_id[n] != -1) {
                    n3 = width + 1;
                    if (i == 0 && b) {
                        if (in_hl) {
                            this.g_off.drawImage(this.sicon[this.m_icon_id[n]], this.d.width - width - this.m_xoffset, n2 + this.m_icon_base_off - height, this);
                        }
                        else {
                            this.g_off.drawImage(this.icon[this.m_icon_id[n]], this.d.width - width - this.m_xoffset, n2 + this.m_icon_base_off - height, this);
                        }
                    }
                }
                this.g_off.drawString(array[i], this.d.width - this.m_xoffset - this.fm.stringWidth(array[i]) - n3, n2);
                if (this.m_underline_links && this.m_linkurl[n].length() > 3) {
                    final int n7 = this.d.width - this.m_xoffset - this.fm.stringWidth(array[i]) - n3;
                    final int n8 = n2;
                    this.g_off.drawLine(n7 + 1, n8 + 1, n7 + this.fm.stringWidth(array[i]) - 1, n8 + 1);
                }
            }
            else {
                if ((i == 0 || this.m_indent_all) && this.m_icon_id[n] != -1) {
                    if (i == 0 && b) {
                        if (in_hl) {
                            this.g_off.drawImage(this.sicon[this.m_icon_id[n]], this.m_xoffset, n2 + this.m_icon_base_off - height, this);
                        }
                        else {
                            this.g_off.drawImage(this.icon[this.m_icon_id[n]], this.m_xoffset, n2 + this.m_icon_base_off - height, this);
                        }
                    }
                    n3 = width + 1;
                }
                this.g_off.drawString(array[i], this.m_xoffset + n3, n2);
                if (this.m_underline_links && this.m_linkurl[n].length() > 3) {
                    final int n9 = this.m_xoffset + n3;
                    final int n10 = n2;
                    this.g_off.drawLine(n9 + 1, n10 + 1, n9 + this.fm.stringWidth(array[i]) - 1, n10 + 1);
                }
            }
            n3 = 0;
        }
        this.paintIt();
    }
    
    public void WhereAmI() {
        boolean b = false;
        for (int i = this.num_descrips - 1; i > -1; --i) {
            if (this.scroll_y + this.d.height >= -this.line_coords[i]) {
                this.cur_dline = i;
                b = true;
            }
        }
        if (!b) {
            this.cur_dline = this.num_descrips - 1;
        }
    }
    
    public boolean mouseMove(final Event event, final int mx, final int my) {
        this.my = my;
        this.mx = mx;
        if (this.paused || this.mbarpaused) {
            this.mCheck();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int mx, final int my) {
        this.my = my;
        this.mx = mx;
        if (!this.done_draw) {
            return true;
        }
        if (this.hold_move != this.my) {
            if (this.paused) {
                this.break_pause = true;
            }
            if (!this.dragged && Math.abs(this.hold_move - this.my) > 1) {
                this.hLine = -1;
                this.chl(0);
                this.dragged = true;
                this.showStatusText();
            }
            this.scroll_y += this.my - this.hold_move;
            if (this.scroll_y + this.d.height > this.d.height || this.scroll_y + this.d.height < -this.i_h) {
                if (this.scroll_y + this.d.height > this.d.height) {
                    this.scroll_y = 0;
                }
                if (this.scroll_y + this.d.height < -this.i_h) {
                    this.scroll_y = -this.i_h - this.d.height;
                }
            }
            this.ScrollIt(false);
            final int abs = Math.abs(this.my - this.hold_move);
            this.erase = this.createImage(this.d.width, abs);
            (this.g_erase = this.erase.getGraphics()).setColor(this.m_bgcolor);
            this.g_erase.fillRect(0, 0, this.d.width, abs);
            if (this.scroll_y + this.d.height > 0) {
                this.getGraphics().drawImage(this.erase, 0, this.scroll_y + this.d.height - abs, this);
            }
            if (this.scroll_y + this.d.height + this.i_h < this.d.height) {
                this.getGraphics().drawImage(this.erase, 0, this.scroll_y + this.d.height + this.i_h, this);
            }
            this.hold_move = this.my;
            return true;
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mdown = true;
        this.hold_move = this.my;
        this.cur_dline = 0;
        if (this.hLine == -1 && this.paused) {
            this.break_pause = true;
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.WhereAmI();
        this.mdown = false;
        if (this.dragged) {
            this.dragged = false;
            return true;
        }
        if (this.hLine != -1 && !this.link_urls[this.link_ref].equals("-1")) {
            if (!this.m_loadwhere[this.hLine].equalsIgnoreCase("applet")) {
                try {
                    if (this.m_loadwhere[this.hLine].equalsIgnoreCase("javascript")) {
                        JSObject.getWindow((Applet)this).eval(this.link_urls[this.link_ref]);
                    }
                    else {
                        this.goURL = new URL(this.getDocumentBase(), this.link_urls[this.link_ref]);
                    }
                }
                catch (MalformedURLException ex) {}
                this.getAppletContext().showDocument(this.goURL, this.m_loadwhere[this.hLine]);
                return true;
            }
            if (!this.fetching_file) {
                this.newPage(this.link_urls[this.link_ref]);
            }
            else {
                this.try_later = true;
                this.later_string = this.link_urls[this.link_ref];
            }
            if (this.paused) {
                this.break_pause = true;
            }
        }
        return true;
    }
    
    public void newPage(final String s) {
        this.connect_message = "Loading: " + s;
        this.first_connect_message = true;
        this.showStatus(this.connect_message);
        this.fr.stop();
        this.fr = null;
        this.the_file = new vswread(this, s);
        this.force_draw = true;
        this.fetching_file = true;
        this.the_file.refresh = true;
        (this.fr = new Thread(this.the_file)).start();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseIn = true;
        this.showStatusText();
        return true;
    }
    
    public void showStatusText() {
        if (this.fetching_file) {
            this.showStatus(this.connect_message);
            return;
        }
        if (this.mouseIn) {
            this.showStatus(this.m_sb_text);
            return;
        }
        this.showStatus(this.m_offsbtext);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseIn = false;
        this.showStatusText();
        if (this.done_draw) {
            this.mCheck();
        }
        return true;
    }
    
    public String[] NewLine(final String s, final int n, int n2) {
        int n3 = 0;
        int i = 0;
        int n4 = 0;
        int n5 = 0;
        final Vector vector = new Vector<String>();
        int n6 = 0;
        int n7 = 1;
        while (i != -1) {
            i = s.indexOf(32, n4);
            if (i != -1) {
                n7 = 0;
                final int stringWidth = this.fm.stringWidth(s.substring(n5, i));
                n3 += stringWidth;
                if (n3 >= this.d.width - (n + n2)) {
                    if (!this.m_indent_all) {
                        n2 = 0;
                    }
                    vector.addElement(this.CheckSPart(s.substring(n6, n5)));
                    n6 = n5;
                    n3 = stringWidth;
                }
                n4 = i + 1;
                n5 = i;
            }
            else {
                if (n7 == 1) {
                    vector.addElement(this.CheckSPart(s));
                    break;
                }
                n3 += this.fm.stringWidth(s.substring(n5));
                if (n3 >= this.d.width - n) {
                    vector.addElement(this.CheckSPart(s.substring(n6, n5)));
                    vector.addElement(this.CheckSPart(s.substring(n5)));
                }
                else {
                    if (n6 >= n5) {
                        continue;
                    }
                    vector.addElement(this.CheckSPart(s.substring(n6)));
                }
            }
        }
        String[] array = new String[vector.size()];
        vector.copyInto(array);
        if (array.length < 1) {
            array = new String[] { " " };
        }
        return array;
    }
    
    public String CheckSPart(final String s) {
        if (s.trim().length() == 0) {
            return " ";
        }
        return s.trim();
    }
    
    private synchronized void chl(final int n) {
        if (this.l_h != this.hLine) {
            if (this.l_h != -1) {
                this.HL(this.l_h, false);
                this.showStatus(this.m_sb_text);
            }
            if (this.hLine != -1) {
                this.HL(this.hLine, true);
                this.showStatus(this.link_urls[n]);
            }
        }
        this.l_h = this.hLine;
    }
    
    public synchronized void mCheck() {
        if (this.num_links == 0) {
            return;
        }
        for (int i = 0; i < this.num_links; ++i) {
            if (!this.mouseIn) {
                this.p_inx = 0;
                this.hLine = -1;
                this.chl(i);
                return;
            }
            if (this.my > this.link_cords[i * 3] + (this.scroll_y + this.d.height) && this.my < this.link_cords[i * 3 + 1] + (this.scroll_y + this.d.height) && this.mx < this.d.width) {
                this.hLine = this.link_cords[i * 3 + 2];
                this.p_inx = this.link_cords[i * 3];
                this.chl(this.link_ref = i);
                return;
            }
            if (i == this.num_links - 1) {
                this.p_inx = 0;
                this.hLine = -1;
                this.chl(i);
                return;
            }
        }
    }
    
    public String[] ReadDescrips(final String s) {
        final String[] array = new String[9];
        int n = 0;
        int n2 = 0;
        int i = 0;
        int n3 = 0;
        for (int j = 0; j < 9; ++j) {
            array[j] = "-1";
        }
        if (s.equals("|")) {
            return array;
        }
        while (i != -1) {
            i = s.indexOf(124, n2);
            if (i == -1) {
                break;
            }
            if (i - n2 != 0) {
                array[n] = s.substring(n2, i);
            }
            n2 = i + 1;
            ++n;
            n3 = i;
            if (n == 9) {
                break;
            }
        }
        if (n == 0) {
            array[8] = s;
        }
        else if (s.length() > n3 + 1) {
            array[8] = s.substring(n3 + 1);
        }
        return array;
    }
    
    public int allignment(final String s) {
        if (s.equalsIgnoreCase("left")) {
            return 0;
        }
        if (s.equalsIgnoreCase("center")) {
            return 1;
        }
        if (s.equalsIgnoreCase("right")) {
            return 2;
        }
        return 0;
    }
    
    public boolean handleEvent(final Event event) {
        boolean b = false;
        if (event.target == this.sb) {
            if (event.id == 602 || event.id == 601 || event.id == 605) {
                this.scroll_y = -(this.sb.getValue() * this.m_sb_line_dist);
                b = true;
            }
            else if (event.id == 604 || event.id == 603) {
                this.scroll_y = -(this.sb.getValue() * this.m_sb_line_dist);
                b = true;
            }
            else {
                if (event.id == 613) {
                    this.barpaused = true;
                    this.old_scrolly = this.scroll_y;
                    if (this.paused) {
                        this.break_pause = true;
                    }
                    return super.handleEvent(event);
                }
                if (event.id == 614) {
                    this.WhereAmI();
                    this.barpaused = false;
                    return super.handleEvent(event);
                }
            }
            if (b) {
                if (this.scroll_y + this.d.height >= this.d.height) {
                    this.scroll_y = 0;
                }
                else if (this.scroll_y < -this.i_h - this.d.height - this.d.height) {
                    this.scroll_y = -this.i_h - this.d.height;
                }
                this.getGraphics().drawImage(this.off, 0, this.scroll_y + this.d.height, this);
                final int abs = Math.abs(this.scroll_y - this.old_scrolly);
                if (abs > 0) {
                    this.erase = this.createImage(this.d.width, abs);
                    (this.g_erase = this.erase.getGraphics()).setColor(this.m_bgcolor);
                    this.g_erase.fillRect(0, 0, this.d.width, abs);
                    if (this.scroll_y + this.d.height > 0) {
                        this.getGraphics().drawImage(this.erase, 0, this.scroll_y + this.d.height - abs, this);
                    }
                    if (this.scroll_y + this.d.height + this.i_h < this.d.height) {
                        this.getGraphics().drawImage(this.erase, 0, this.scroll_y + this.d.height + this.i_h, this);
                    }
                }
            }
            this.old_scrolly = this.scroll_y;
            return true;
        }
        return super.handleEvent(event);
    }
    
    public Image trackReturn(final String s) {
        final ocgifix ocgifix = new ocgifix();
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (s.equals("-1")) {
            return null;
        }
        final Image image = this.getImage(this.getDocumentBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return null;
        }
        if (mediaTracker.isErrorID(0)) {
            System.out.println("Error Loading Image File:" + s);
            return null;
        }
        return ocgifix.gifix(image, s);
    }
    
    public Image ImageWait(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0)) {
            return null;
        }
        return image;
    }
    
    public vsw() {
        this.mbarpaused = false;
        this.first_go = true;
        this.scrollafterlink = false;
        this.dragged = false;
        this.break_pause = false;
        this.tag = false;
        this.hLine = -1;
        this.mouseIn = false;
        this.l_h = -1;
        this.update_si = false;
        this.in_hl = false;
        this.last_h = -1;
        this.later_string = "";
        this.try_later = false;
        this.force_draw = false;
        this.done_draw = false;
        this.first_run = false;
        this.mdown = false;
        this.fetching_file = false;
        this.barpaused = false;
        this.m_showhand = false;
        this.m_paramsinfile = false;
        this.m_underline_links = false;
        this.m_top_margin = 5;
        this.m_scroll_delay = 20;
        this.m_def_textcolor = Color.black;
        this.m_bgcolor = Color.white;
        this.m_def_hlcolor = Color.blue;
        this.m_sb_text = "OpenCube - Vertical Scrolling Window";
        this.m_xoffset = 5;
        this.m_def_loadwhere = "_self";
        this.m_offsbtext = "Java By OpenCube";
        this.m_sb_update_message = "Updating Scroll Data...";
        this.m_file_update_scrolls = 1;
        this.m_wait_for_file = false;
        this.m_jump = 1;
        this.m_load_color = Color.black;
        this.m_load_msg = "Loading Scroll Images...";
        this.m_si = 2;
        this.m_bc = Color.lightGray;
        this.m_ac = Color.black;
        this.m_hc = Color.yellow;
        this.m_bgc = Color.white;
        this.m_sc = Color.black;
        this.m_oc = Color.lightGray;
        this.m_bar_width = 14;
        this.m_sb_line_dist = 5;
        this.first_connect_message = true;
        this.first_update_message = false;
        this.m_sbright = true;
    }
}
