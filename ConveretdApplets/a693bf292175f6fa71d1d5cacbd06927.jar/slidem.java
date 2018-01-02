import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.Stack;
import java.util.Vector;
import java.util.Properties;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class slidem extends Applet implements Runnable
{
    Thread trun;
    boolean clickloaded;
    boolean moveloaded;
    int usub_h;
    boolean mu_h;
    boolean mouse_in;
    String prev_url;
    Font sub_font;
    FontMetrics fm_sub_font;
    Font main_font;
    FontMetrics fm_main_font;
    private Frame the_frame;
    private boolean m_showhand;
    int max_subs;
    int break_picload;
    int break_pictype;
    int break_up;
    int last_bitstart;
    int addB;
    int mStart;
    int last_bs;
    int cur_expand;
    int break_bgload;
    URL goURL;
    Dimension d;
    Image[] suboff;
    Graphics[] g_suboff;
    Image bitoff;
    Graphics g_bitoff;
    Image mainoff;
    Graphics g_mainoff;
    Image ts;
    Graphics g_ts;
    int menux;
    int menuy;
    int menuwidth;
    int menuheight;
    int extra_length;
    int level_max;
    int max_open;
    int bitheight;
    int on_sub_num;
    int last_sub;
    boolean sub_highlighted;
    boolean tag;
    boolean first_run;
    private boolean m_check_previous_link;
    private String m_loadwhere;
    private String[] m_main_desturl;
    private Color m_bgcolor;
    private int m_jump_size;
    private int m_delay;
    private boolean m_tile_menus_only;
    private int m_top_offset;
    private boolean m_hl_text_only;
    private boolean m_retract_from_offscreen;
    private int m_sub_indent;
    private int m_sub_height;
    private Color m_sub_color;
    private Color m_sub_textcolor;
    private Color m_sub_hlcolor;
    private Color m_sub_hltextcolor;
    private Color m_sub_hlocolor;
    boolean[] gif_tile;
    private String m_sub_bgfile;
    private Image m_subbg;
    private boolean m_bgfile_stat;
    private int m_sub_icon_indent;
    private String m_load_msg;
    private Color m_load_msgcolor;
    private Color[] mc_sub_color;
    private Color[] mc_sub_textcolor;
    private Color[] mc_sub_hlcolor;
    private Color[] mc_sub_hltextcolor;
    private Color[] mc_sub_hlocolor;
    private String[] mc_sub_bgfile;
    private boolean[] mc_bgfile_stat;
    private Image[] mc_subbg;
    private Color[] mcu_sub_hlcolor;
    private Color[] mcu_sub_hltextcolor;
    private Color[] mcu_sub_hlocolor;
    private Color mu_sub_hlcolor;
    private Color mu_sub_hltextcolor;
    private Color mu_sub_hlocolor;
    private String url;
    private String m_onsbtext;
    private String m_offsbtext;
    private String[] m_icon_file;
    private String[] m_switchfile;
    private String[] m_switchufile;
    private int[] m_pic_ycord;
    private String[] m_maindesc;
    private int[] m_mainicon;
    private int m_main_height;
    private int m_main_indent;
    private int m_main_icon_indent;
    private String m_main_bgfile;
    private boolean main_gif_tile;
    private Image m_main_bgimage;
    private boolean m_tile_bg;
    private Color m_main_textcolor;
    private Color m_main_ocolor;
    private Color m_main_hltextcolor;
    private Color m_main_hlocolor;
    private Color m_main_hlbgcolor;
    private Color mu_main_hltextcolor;
    private Color mu_main_hlocolor;
    private Color mu_main_hlbgcolor;
    int last_drawn;
    int last_level;
    int num_main;
    int num_icons;
    int add_m;
    boolean[] no_subs;
    String[] subs;
    String[] p_subs;
    int[] ItoM;
    Image[] i_pic;
    Image[] s_pic;
    Image[] u_pic;
    int cur_level;
    int real_level;
    boolean[] i_pic_stat;
    boolean[] s_pic_stat;
    boolean[] u_pic_stat;
    int[] menu_coords;
    String cur_link;
    
    public void init() {
        final String s = "Sliding Tree Menu (Developer Version), Copyright (c) 2000, OpenCube Inc.";
        this.d = this.size();
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
            final String property = properties.getProperty("subfont");
            if (property != null) {
                this.sub_font = ocfontc.getFontSD(property, ",");
            }
            else {
                this.sub_font = new Font("Helvetica", 0, 12);
            }
            final String property2 = properties.getProperty("mfont");
            if (property2 != null) {
                this.main_font = ocfontc.getFontSD(property2, ",");
            }
            else {
                this.main_font = new Font("Helvetica", 0, 12);
            }
            this.fm_sub_font = this.getFontMetrics(this.sub_font);
            this.fm_main_font = this.getFontMetrics(this.main_font);
            final String property3 = properties.getProperty("mindent");
            if (property3 != null) {
                this.m_main_indent = Integer.parseInt(property3);
            }
            final String property4 = properties.getProperty("subindent");
            if (property4 != null) {
                this.m_sub_indent = Integer.parseInt(property4);
            }
            final String property5 = properties.getProperty("showhand");
            if (property5 != null) {
                this.m_showhand = Boolean.valueOf(property5);
            }
            final String property6 = properties.getProperty("mheight");
            if (property6 != null) {
                this.m_main_height = Integer.parseInt(property6);
            }
            if (this.m_main_height < 1) {
                this.m_main_height = this.fm_main_font.getHeight();
            }
            final String property7 = properties.getProperty("mtextcolor");
            if (property7 != null) {
                this.m_main_textcolor = occcolor.ConvertColor(property7);
            }
            final String property8 = properties.getProperty("mhltextcolor");
            if (property8 != null) {
                this.m_main_hltextcolor = occcolor.ConvertColor(property8);
            }
            final String property9 = properties.getProperty("muhltextcolor");
            if (property9 != null) {
                this.mu_main_hltextcolor = occcolor.ConvertColor(property9);
            }
            final String property10 = properties.getProperty("loadmsgcolor");
            if (property10 != null) {
                this.m_load_msgcolor = occcolor.ConvertColor(property10);
            }
            final String property11 = properties.getProperty("loadmsg");
            if (property11 != null) {
                this.m_load_msg = property11;
            }
            final String property12 = properties.getProperty("muhltextcolor");
            if (property12 != null) {
                this.mu_main_hltextcolor = occcolor.ConvertColor(property12);
            }
            final String property13 = properties.getProperty("mhloutcolor");
            if (property13 != null) {
                this.m_main_hlocolor = occcolor.ConvertColor(property13);
            }
            final String property14 = properties.getProperty("muhloutcolor");
            if (property14 != null) {
                this.mu_main_hlocolor = occcolor.ConvertColor(property14);
            }
            final String property15 = properties.getProperty("moutcolor");
            if (property15 != null) {
                this.m_main_ocolor = occcolor.ConvertColor(property15);
            }
            final String property16 = properties.getProperty("mbgfile");
            if (property16 != null) {
                this.m_main_bgfile = property16;
            }
            if (this.m_main_bgfile.toLowerCase().lastIndexOf("gif") != -1) {
                this.main_gif_tile = true;
            }
            final String property17 = properties.getProperty("mtilebg");
            if (property17 != null) {
                this.m_tile_bg = Boolean.valueOf(property17);
            }
            final String property18 = properties.getProperty("hlsubtextonly");
            if (property18 != null) {
                this.m_hl_text_only = Boolean.valueOf(property18);
            }
            final String property19 = properties.getProperty("checkprevlink");
            if (property19 != null) {
                this.m_check_previous_link = Boolean.valueOf(property19);
            }
            final String property20 = properties.getProperty("mbgonmenusonly");
            if (property20 != null) {
                this.m_tile_menus_only = Boolean.valueOf(property20);
            }
            final String property21 = properties.getProperty("rfromoff");
            if (property21 != null) {
                this.m_retract_from_offscreen = Boolean.valueOf(property21);
            }
            final String property22 = properties.getProperty("mhlbgcolor");
            if (property22 != null) {
                this.m_main_hlbgcolor = occcolor.ConvertColor(property22);
            }
            final String property23 = properties.getProperty("muhlbgcolor");
            if (property23 != null) {
                this.mu_main_hlbgcolor = occcolor.ConvertColor(property23);
            }
            final String property24 = properties.getProperty("subheight");
            if (property24 != null) {
                this.m_sub_height = Integer.parseInt(property24);
            }
            final String property25 = properties.getProperty("subiconindent");
            if (property25 != null) {
                this.m_sub_icon_indent = Integer.parseInt(property25);
            }
            final String property26 = properties.getProperty("s_color");
            if (property26 != null) {
                this.m_sub_color = occcolor.ConvertColor(property26);
            }
            final String property27 = properties.getProperty("s_hlcolor");
            if (property27 != null) {
                this.m_sub_hlcolor = occcolor.ConvertColor(property27);
            }
            final String property28 = properties.getProperty("s_textcolor");
            if (property28 != null) {
                this.m_sub_textcolor = occcolor.ConvertColor(property28);
            }
            final String property29 = properties.getProperty("s_hltextcolor");
            if (property29 != null) {
                this.m_sub_hltextcolor = occcolor.ConvertColor(property29);
            }
            final String property30 = properties.getProperty("s_hloutcolor");
            if (property30 != null) {
                this.m_sub_hlocolor = occcolor.ConvertColor(property30);
            }
            final String property31 = properties.getProperty("s_uhltextcolor");
            if (property31 != null) {
                this.mu_sub_hltextcolor = occcolor.ConvertColor(property31);
            }
            final String property32 = properties.getProperty("s_uhloutcolor");
            if (property32 != null) {
                this.mu_sub_hlocolor = occcolor.ConvertColor(property32);
            }
            final String property33 = properties.getProperty("s_uhlcolor");
            if (property33 != null) {
                this.mu_sub_hlcolor = occcolor.ConvertColor(property33);
            }
            final String property34 = properties.getProperty("s_bgfile");
            if (property34 != null) {
                this.m_sub_bgfile = property34;
            }
            else {
                this.m_sub_bgfile = "-1";
            }
            final String property35 = properties.getProperty("topoffset");
            if (property35 != null) {
                this.m_top_offset = Integer.parseInt(property35);
            }
            final String property36 = properties.getProperty("jumpsize");
            if (property36 != null) {
                this.m_jump_size = Integer.parseInt(property36);
            }
            final String property37 = properties.getProperty("delay");
            if (property37 != null) {
                this.m_delay = Integer.parseInt(property37);
            }
            final String property38 = properties.getProperty("miconindent");
            if (property38 != null) {
                this.m_main_icon_indent = Integer.parseInt(property38);
            }
            final String property39 = properties.getProperty("onsbtext");
            if (property39 != null) {
                this.m_onsbtext = property39;
            }
            final String property40 = properties.getProperty("offsbtext");
            if (property40 != null) {
                this.m_offsbtext = property40;
            }
            final String property41 = properties.getProperty("loadwhere");
            if (property41 != null) {
                this.m_loadwhere = property41;
            }
            final String property42 = properties.getProperty("bgcolor");
            if (property42 != null) {
                this.m_bgcolor = occcolor.ConvertColor(property42);
            }
            this.setBackground(this.m_bgcolor);
            while (properties.getProperty("maindesc" + this.num_main) != null) {
                ++this.num_main;
            }
            this.m_maindesc = new String[this.num_main];
            this.m_mainicon = new int[this.num_main];
            this.m_pic_ycord = new int[this.num_main];
            this.m_main_desturl = new String[this.num_main];
            this.no_subs = new boolean[this.num_main];
            this.ItoM = new int[this.num_main];
            final Vector<String> vector = new Vector<String>();
            final Vector vector2 = new Vector<String>();
            final Vector vector3 = new Vector<String>();
            int n = 0;
            int n2 = 0;
            Stack<String> stack = new Stack<String>();
            Stack<String> stack2 = new Stack<String>();
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            if (this.m_sub_height < 1) {
                this.bitheight = this.fm_sub_font.getHeight();
            }
            else {
                this.bitheight = this.m_sub_height;
            }
            for (int i = 0; i < this.num_main; ++i) {
                if (properties.getProperty("desc" + i + "-0") == null) {
                    this.no_subs[i] = true;
                }
                String s3 = new Integer(i).toString();
                int n7 = 1;
                while (true) {
                    final String string = String.valueOf(s3) + "-" + n2;
                    final String property43 = properties.getProperty("desc" + string);
                    int j = 0;
                    int level_max = 0;
                    boolean b = false;
                    while (j != -1) {
                        j = string.indexOf("-", j + 1);
                        if (j == -1) {
                            break;
                        }
                        if (++level_max <= this.level_max) {
                            continue;
                        }
                        this.level_max = level_max;
                        b = true;
                        vector.addElement(new Integer(n2).toString());
                    }
                    if (!b) {
                        vector.addElement("0");
                    }
                    if (property43 != null) {
                        if (properties.getProperty("desc" + string + "-0") != null) {
                            vector2.addElement(string);
                            ++n3;
                            stack.push(string);
                            stack2.push(new Integer(n * 4).toString());
                        }
                        else {
                            vector2.addElement("-1");
                        }
                        String substring = "-1";
                        final int index = property43.indexOf("|");
                        String substring2;
                        if (index > 0) {
                            if (index < property43.length() - 1) {
                                substring = property43.substring(0, index);
                                substring2 = property43.substring(index + 1);
                            }
                            else {
                                substring2 = property43;
                            }
                        }
                        else {
                            substring2 = property43;
                        }
                        vector2.addElement(substring2);
                        vector2.addElement(substring);
                        final String property44 = properties.getProperty("desturl" + string);
                        if (property44 != null) {
                            vector2.addElement(property44);
                        }
                        else {
                            vector2.addElement("-1");
                        }
                        if (n2 == 0) {
                            vector3.addElement("15");
                            vector3.addElement("15");
                            vector3.addElement(new Integer(n).toString());
                        }
                        ++n;
                        ++n2;
                    }
                    else {
                        if (this.no_subs[i]) {
                            for (int k = 0; k < 5; ++k) {
                                vector3.addElement("0");
                            }
                        }
                        if (Integer.parseInt(vector.elementAt(level_max - 1).toString()) < n2) {
                            vector.setElementAt(new Integer(n2).toString(), level_max - 1);
                        }
                        if (n2 > 0) {
                            vector3.addElement(new Integer(n - 1).toString());
                            vector3.addElement(new Integer(0).toString());
                        }
                        ++n4;
                        ++n5;
                        if (n7 != 0) {
                            this.ItoM[i] = (n4 - 1) * 5;
                            n7 = 0;
                        }
                        if (n3 < 1) {
                            break;
                        }
                        s3 = stack.pop().toString();
                        --n3;
                        vector2.setElementAt(new Integer(n4).toString(), new Integer(stack2.pop().toString()));
                        n2 = 0;
                    }
                }
                stack = new Stack<String>();
                stack2 = new Stack<String>();
                n2 = 0;
                if (n5 > n6) {
                    n6 = n5;
                    n5 = 0;
                }
                final String property45 = properties.getProperty("mdesturl" + i);
                if (property45 != null) {
                    this.m_main_desturl[i] = property45;
                }
                else {
                    this.m_main_desturl[i] = "-1";
                }
                final String property46 = properties.getProperty("maindesc" + i);
                if (property46 != null) {
                    this.m_maindesc[i] = property46;
                }
                else {
                    this.m_maindesc[i] = "";
                }
            }
            for (int l = 0; l < this.level_max; ++l) {
                this.max_open += Integer.parseInt(vector.elementAt(l).toString());
            }
            if (this.m_tile_menus_only) {
                this.extra_length = this.max_open * this.bitheight + this.m_main_height * this.m_maindesc.length - this.d.height;
            }
            else {
                this.extra_length = this.max_open * this.bitheight + this.m_main_height * this.m_maindesc.length;
            }
            if (!this.m_retract_from_offscreen || this.extra_length < 0) {
                this.extra_length = 0;
            }
            this.ts = this.createImage(this.d.width, this.d.height + this.extra_length);
            (this.g_ts = this.ts.getGraphics()).setFont(this.main_font);
            this.g_ts.setColor(this.m_bgcolor);
            this.g_ts.fillRect(0, 0, this.d.width, this.d.height + this.extra_length);
            this.menu_coords = new int[n6 * 7];
            this.suboff = new Image[n6];
            this.g_suboff = new Graphics[n6];
            vector2.copyInto(this.subs = new String[vector2.size()]);
            vector3.copyInto(this.p_subs = new String[vector3.size()]);
            this.mc_sub_hlocolor = new Color[this.level_max];
            this.mc_sub_color = new Color[this.level_max];
            this.mc_sub_textcolor = new Color[this.level_max];
            this.mc_sub_hlcolor = new Color[this.level_max];
            this.mc_sub_hltextcolor = new Color[this.level_max];
            this.mc_sub_bgfile = new String[this.level_max];
            this.mcu_sub_hlcolor = new Color[this.level_max];
            this.mcu_sub_hltextcolor = new Color[this.level_max];
            this.mcu_sub_hlocolor = new Color[this.level_max];
            this.gif_tile = new boolean[this.level_max];
            this.mc_bgfile_stat = new boolean[this.level_max];
            this.mc_subbg = new Image[this.level_max];
            for (int n8 = 0; n8 < this.level_max; ++n8) {
                final String property47 = properties.getProperty("s_color" + n8);
                if (property47 != null) {
                    this.mc_sub_color[n8] = occcolor.ConvertColor(property47);
                }
                else {
                    this.mc_sub_color[n8] = this.m_sub_color;
                }
                final String property48 = properties.getProperty("s_hlcolor" + n8);
                if (property48 != null) {
                    this.mc_sub_hlcolor[n8] = occcolor.ConvertColor(property48);
                }
                else {
                    this.mc_sub_hlcolor[n8] = this.m_sub_hlcolor;
                }
                final String property49 = properties.getProperty("s_hloutcolor" + n8);
                if (property49 != null) {
                    this.mc_sub_hlocolor[n8] = occcolor.ConvertColor(property49);
                }
                else {
                    this.mc_sub_hlocolor[n8] = this.m_sub_hlocolor;
                }
                final String property50 = properties.getProperty("s_textcolor" + n8);
                if (property50 != null) {
                    this.mc_sub_textcolor[n8] = occcolor.ConvertColor(property50);
                }
                else {
                    this.mc_sub_textcolor[n8] = this.m_sub_textcolor;
                }
                final String property51 = properties.getProperty("s_hltextcolor" + n8);
                if (property51 != null) {
                    this.mc_sub_hltextcolor[n8] = occcolor.ConvertColor(property51);
                }
                else {
                    this.mc_sub_hltextcolor[n8] = this.m_sub_hltextcolor;
                }
                final String property52 = properties.getProperty("s_bgfile" + n8);
                if (property52 != null) {
                    this.mc_sub_bgfile[n8] = property52;
                }
                else {
                    this.mc_sub_bgfile[n8] = this.m_sub_bgfile;
                }
                if (this.mc_sub_bgfile[n8].toLowerCase().lastIndexOf("gif") != -1) {
                    this.gif_tile[n8] = true;
                }
                final String property53 = properties.getProperty("s_uhlcolor" + n8);
                if (property53 != null) {
                    this.mcu_sub_hlcolor[n8] = occcolor.ConvertColor(property53);
                }
                else {
                    this.mcu_sub_hlcolor[n8] = this.mu_sub_hlcolor;
                }
                final String property54 = properties.getProperty("s_uhloutcolor" + n8);
                if (property54 != null) {
                    this.mcu_sub_hlocolor[n8] = occcolor.ConvertColor(property54);
                }
                else {
                    this.mcu_sub_hlocolor[n8] = this.mu_sub_hlocolor;
                }
                final String property55 = properties.getProperty("s_uhltextcolor" + n8);
                if (property55 != null) {
                    this.mcu_sub_hltextcolor[n8] = occcolor.ConvertColor(property55);
                }
                else {
                    this.mcu_sub_hltextcolor[n8] = this.mu_sub_hltextcolor;
                }
            }
            this.bitoff = this.createImage(this.d.width, this.bitheight + 1);
            (this.g_bitoff = this.bitoff.getGraphics()).setFont(this.sub_font);
            while (properties.getProperty("iconfile" + this.num_icons) != null) {
                ++this.num_icons;
            }
            this.m_icon_file = new String[this.num_icons];
            this.m_switchfile = new String[this.num_icons];
            this.m_switchufile = new String[this.num_icons];
            this.u_pic_stat = new boolean[this.num_icons];
            this.s_pic_stat = new boolean[this.num_icons];
            this.i_pic_stat = new boolean[this.num_icons];
            this.i_pic = new Image[this.num_icons];
            this.s_pic = new Image[this.num_icons];
            this.u_pic = new Image[this.num_icons];
            for (int n9 = 0; n9 < this.num_icons; ++n9) {
                final String property56 = properties.getProperty("iconfile" + n9);
                if (property56 != null) {
                    this.m_icon_file[n9] = new String(property56);
                }
                else {
                    this.m_icon_file[n9] = new String(" ");
                }
                final String property57 = properties.getProperty("iconswitchfile" + n9);
                if (property57 != null) {
                    this.m_switchfile[n9] = new String(property57);
                }
                else {
                    this.m_switchfile[n9] = this.m_icon_file[n9];
                }
                final String property58 = properties.getProperty("iconclickfile" + n9);
                if (property58 != null) {
                    this.m_switchufile[n9] = new String(property58);
                }
                else {
                    this.m_switchufile[n9] = this.m_icon_file[n9];
                }
            }
            return;
        }
        if (key.equals("-1")) {
            this.m_onsbtext = "License Error: Invalid URL";
            return;
        }
        this.m_onsbtext = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    public void paint(final Graphics graphics) {
        this.paintIt(graphics);
    }
    
    public void paintIt(final Graphics graphics) {
        if (!this.tag) {
            graphics.setColor(Color.black);
            graphics.drawString(this.m_onsbtext, 5, 20);
            return;
        }
        graphics.drawImage(this.ts, 0, 0, this);
    }
    
    public void HighlightPic(final int n, final int n2) {
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        this.g_mainoff.drawImage(this.ts, 0, -n2, this);
        if (this.m_main_hlbgcolor != null) {
            this.g_mainoff.setColor(this.m_main_hlbgcolor);
            this.g_mainoff.fillRect(0, 0, this.d.width, this.m_main_height);
        }
        if (this.i_pic != null && this.m_mainicon[n] > -1 && this.m_mainicon[n] < this.i_pic.length && this.i_pic[this.m_mainicon[n]] != null) {
            this.g_mainoff.drawImage(this.s_pic[this.m_mainicon[n]], this.m_main_icon_indent, this.m_main_height / 2 - this.s_pic[this.m_mainicon[n]].getHeight(this) / 2, this);
        }
        if (this.m_main_hlocolor != null) {
            this.g_mainoff.setColor(this.m_main_hlocolor);
            this.g_mainoff.drawRect(0, 0, this.d.width - 1, this.m_main_height);
        }
        if (this.m_showhand && this.m_main_desturl[n] != "-1") {
            this.the_frame.setCursor(12);
        }
        else {
            this.the_frame.setCursor(0);
        }
        this.g_mainoff.setColor(this.m_main_hltextcolor);
        this.g_mainoff.drawString(this.m_maindesc[n], this.m_main_indent, this.m_main_height / 2 - this.fm_main_font.getHeight() / 2 + this.fm_main_font.getAscent());
        this.getGraphics().drawImage(this.mainoff, 0, n2, this);
    }
    
    public void UHighlightPic(final int n, final int n2) {
        this.g_mainoff.drawImage(this.ts, 0, -n2, this);
        if (this.mu_main_hlbgcolor != null) {
            this.g_mainoff.setColor(this.mu_main_hlbgcolor);
            this.g_mainoff.fillRect(0, 0, this.d.width, this.m_main_height);
        }
        if (this.i_pic != null && this.m_mainicon[n] > -1 && this.m_mainicon[n] < this.i_pic.length && this.i_pic[this.m_mainicon[n]] != null) {
            this.g_mainoff.drawImage(this.u_pic[this.m_mainicon[n]], this.m_main_icon_indent, this.m_main_height / 2 - this.u_pic[this.m_mainicon[n]].getHeight(this) / 2, this);
        }
        if (this.mu_main_hlocolor != null) {
            this.g_mainoff.setColor(this.mu_main_hlocolor);
            this.g_mainoff.drawRect(0, 0, this.d.width - 1, this.m_main_height);
        }
        this.g_mainoff.setColor(this.mu_main_hltextcolor);
        this.g_mainoff.drawString(this.m_maindesc[n], this.m_main_indent, this.m_main_height / 2 - this.fm_main_font.getHeight() / 2 + this.fm_main_font.getAscent());
        this.getGraphics().drawImage(this.mainoff, 0, n2, this);
        this.mu_h = true;
    }
    
    public boolean drawMainMenu(final int n) {
        if (this.no_subs[n]) {
            return false;
        }
        final int n2 = new Integer(this.p_subs[this.ItoM[n] + 3]) - new Integer(this.p_subs[this.ItoM[n] + 2]) + 1;
        final int n3 = new Integer(this.p_subs[this.ItoM[n] + 2]) * 4;
        this.addB = 0;
        this.add_m = 0;
        this.menux = this.m_main_indent + this.m_sub_indent;
        this.menuy = this.m_pic_ycord[n] + this.m_main_height;
        this.menuwidth = this.d.width - (this.m_main_indent + this.m_sub_indent) - 1;
        this.menuheight = n2 * this.bitheight;
        this.menu_coords[0] = this.menux;
        this.menu_coords[1] = this.menuy;
        this.menu_coords[2] = this.menuwidth;
        this.menu_coords[3] = this.menuheight;
        this.menu_coords[4] = n2;
        this.menu_coords[5] = n;
        this.menu_coords[6] = n3;
        this.suboff[0] = this.createImage(this.d.width, this.menuheight + 1);
        (this.g_suboff[0] = this.suboff[0].getGraphics()).setFont(this.sub_font);
        final Color color = this.mc_sub_color[0];
        final Color color2 = this.mc_sub_textcolor[0];
        final Image image = this.mc_subbg[0];
        final boolean b = this.mc_bgfile_stat[0];
        this.g_suboff[0].setColor(color);
        this.g_suboff[0].fillRect(0, 0, this.d.width, this.menuheight);
        if (b) {
            int n4 = 0;
            int n5 = 0;
            final int width = this.m_subbg.getWidth(this);
            final int height = this.m_subbg.getHeight(this);
            if (this.gif_tile[0]) {
                n5 = -1;
            }
            while (true) {
                this.g_suboff[0].drawImage(image, n4, n5, this);
                n4 += width;
                if (n4 > this.d.width) {
                    n4 = 0;
                    n5 += height;
                    if (this.gif_tile[0]) {
                        --n5;
                    }
                    if (n5 > this.menuheight + 1) {
                        break;
                    }
                    continue;
                }
            }
        }
        for (int i = 0; i < n2; ++i) {
            final int int1 = Integer.parseInt(this.subs[n3 + i * 4 + 2]);
            if (this.i_pic != null && int1 > -1 && int1 < this.i_pic.length && this.i_pic[int1] != null) {
                this.g_suboff[0].drawImage(this.i_pic[int1], this.menux - this.m_sub_indent + this.m_sub_icon_indent, this.bitheight * i + this.bitheight / 2 - this.i_pic[int1].getHeight(this) / 2, this);
            }
            this.g_suboff[0].setColor(color2);
            this.g_suboff[0].drawString(this.subs[n3 + i * 4 + 1], this.menux, this.bitheight * i + this.bitheight / 2 - this.fm_sub_font.getHeight() / 2 + this.fm_sub_font.getAscent());
        }
        this.cur_level = 0;
        return true;
    }
    
    public void CreateSub(final int n, final int n2) {
        final int n3 = new Integer(this.p_subs[n + 3]) - new Integer(this.p_subs[n + 2]) + 1;
        final int n4 = new Integer(this.p_subs[n + 2]) * 4;
        this.menux = this.m_sub_indent;
        this.menuy = (n2 + 1) * this.bitheight;
        this.menuheight = n3 * this.bitheight;
        this.menuwidth = this.menu_coords[(this.cur_level - 1) * 7 + 2] - this.m_sub_indent;
        this.menu_coords[this.cur_level * 7] = this.menu_coords[(this.cur_level - 1) * 7] + this.menux;
        this.menu_coords[this.cur_level * 7 + 1] = this.menu_coords[(this.cur_level - 1) * 7 + 1] + this.menuy;
        this.menu_coords[this.cur_level * 7 + 2] = this.menuwidth;
        this.menu_coords[this.cur_level * 7 + 3] = this.menuheight;
        this.menu_coords[this.cur_level * 7 + 4] = n3;
        this.menu_coords[this.cur_level * 7 + 5] = this.on_sub_num;
        this.menu_coords[this.cur_level * 7 + 6] = n4;
        this.suboff[this.cur_level] = this.createImage(this.d.width, this.menuheight + 1);
        (this.g_suboff[this.cur_level] = this.suboff[this.cur_level].getGraphics()).setFont(this.sub_font);
        final Color color = this.mc_sub_color[this.cur_level];
        final Color color2 = this.mc_sub_textcolor[this.cur_level];
        final Image image = this.mc_subbg[this.cur_level];
        final boolean b = this.mc_bgfile_stat[this.cur_level];
        this.g_suboff[this.cur_level].setColor(color);
        this.g_suboff[this.cur_level].fillRect(0, 0, this.d.width, this.menuheight);
        if (b) {
            int n5 = 0;
            int n6 = 0;
            final int width = image.getWidth(this);
            final int height = image.getHeight(this);
            if (this.gif_tile[this.cur_level]) {
                n6 = -1;
            }
            while (true) {
                this.g_suboff[this.cur_level].drawImage(image, n5, n6, this);
                n5 += width;
                if (n5 > this.d.width) {
                    n5 = 0;
                    n6 += height;
                    if (this.gif_tile[this.cur_level]) {
                        --n6;
                    }
                    if (n6 > this.menuheight + 1) {
                        break;
                    }
                    continue;
                }
            }
        }
        for (int i = 0; i < n3; ++i) {
            final String s = this.subs[n4 + i * 4 + 1];
            final int int1 = Integer.parseInt(this.subs[n4 + i * 4 + 2]);
            if (this.i_pic != null && int1 > -1 && int1 < this.i_pic.length && this.i_pic[int1] != null) {
                this.g_suboff[this.cur_level].drawImage(this.i_pic[int1], this.menu_coords[this.cur_level * 7] - this.m_sub_indent + this.m_sub_icon_indent, this.bitheight * i + this.bitheight / 2 - this.i_pic[int1].getHeight(this) / 2, this);
            }
            this.g_suboff[this.cur_level].setColor(color2);
            this.g_suboff[this.cur_level].drawString(s, this.menu_coords[this.cur_level * 7], this.bitheight * i + this.bitheight / 2 - this.fm_sub_font.getHeight() / 2 + this.fm_sub_font.getAscent());
        }
        this.on_sub_num = -1;
        this.sub_highlighted = false;
        this.usub_h = -1;
    }
    
    public synchronized void growSub(final int n, final int n2) {
        final int n3 = this.menu_coords[n * 7 + 3];
        final int n4 = this.menu_coords[n * 7 + 1] + 1;
        this.add_m += n3;
        this.addB += n2;
        this.g_ts.clipRect(0, n4, this.d.width, this.d.height + this.extra_length - n4);
        final Image image = this.createImage(this.d.width, this.d.height + this.extra_length - n4);
        image.getGraphics().drawImage(this.ts, 0, -n4, this);
        for (int i = n4; i < n4 + n3 + 1; i += this.m_jump_size) {
            this.g_ts.drawImage(this.suboff[n], 0, i - n3, this);
            this.g_ts.drawImage(image, 0, i, this);
            this.getGraphics().drawImage(this.ts, 0, 0, this);
            try {
                Thread.sleep(this.m_delay);
            }
            catch (InterruptedException ex) {}
        }
        final int n5 = n4 + n3;
        if (this.m_jump_size > 1) {
            this.g_ts.drawImage(this.suboff[n], 0, n5 - n3, this);
            this.g_ts.drawImage(image, 0, n5, this);
            this.getGraphics().drawImage(this.ts, 0, 0, this);
        }
        this.g_ts = this.ts.getGraphics();
        System.gc();
    }
    
    public synchronized void shrinkSub(final int n) {
        final int n2 = this.menu_coords[n * 7 + 3];
        final int n3 = this.menu_coords[n * 7 + 1] + 1;
        this.add_m -= n2;
        this.last_bs = n3 - this.bitheight - 1;
        this.addB -= this.menu_coords[n * 7 + 5];
        this.g_ts.clipRect(0, n3, this.d.width, this.d.height + this.extra_length - n3);
        final Image image = this.createImage(this.d.width, this.d.height + this.extra_length - n3 + this.m_jump_size);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.m_bgcolor);
        graphics.fillRect(0, 0, this.d.width, this.d.height + this.extra_length - n3 + this.m_jump_size);
        graphics.drawImage(this.ts, 0, -n3, this);
        for (int i = n3; i > n3 - n2 - 1; i -= this.m_jump_size) {
            this.g_ts.drawImage(image, 0, i, this);
            this.getGraphics().drawImage(this.ts, 0, 0, this);
            try {
                Thread.sleep(this.m_delay);
            }
            catch (InterruptedException ex) {}
        }
        final int n4 = n3 - n2;
        if (this.m_jump_size > 1) {
            this.g_ts.drawImage(image, 0, n4, this);
            this.getGraphics().drawImage(this.ts, 0, 0, this);
        }
        this.g_ts = this.ts.getGraphics();
    }
    
    public void USubHighlight(final int last_sub, final int n, int last_bitstart) {
        this.UndoH();
        ++last_bitstart;
        this.usub_h = n;
        this.last_sub = last_sub;
        this.last_level = n;
        this.last_bitstart = last_bitstart;
        final int n2 = this.menu_coords[n * 7 + 6];
        Color mu_sub_hlcolor = this.mu_sub_hlcolor;
        Color mu_sub_hlocolor = this.mu_sub_hlocolor;
        Color mu_sub_hltextcolor = this.mu_sub_hltextcolor;
        if (n > 0) {
            mu_sub_hlcolor = this.mcu_sub_hlcolor[n - 1];
            mu_sub_hlocolor = this.mcu_sub_hlocolor[n - 1];
            mu_sub_hltextcolor = this.mcu_sub_hltextcolor[n - 1];
        }
        int sub_indent = this.m_sub_indent;
        if (this.m_sub_icon_indent < 0) {
            sub_indent = this.m_sub_indent - this.m_sub_icon_indent + 1;
        }
        if (this.m_hl_text_only) {
            sub_indent = 3;
        }
        final int n3 = this.menu_coords[n * 7] - sub_indent;
        this.g_bitoff.drawImage(this.ts, 0, -last_bitstart, this);
        if (mu_sub_hlcolor != null) {
            this.g_bitoff.setColor(mu_sub_hlcolor);
            this.g_bitoff.fillRect(n3, 0, this.d.width - n3, this.bitheight);
        }
        if (mu_sub_hlocolor != null) {
            this.g_bitoff.setColor(mu_sub_hlocolor);
            this.g_bitoff.drawRect(n3, 0, this.d.width - (n3 + 1), this.bitheight);
        }
        final String s = this.subs[n2 + last_sub * 4 + 1];
        final int int1 = Integer.parseInt(this.subs[n2 + last_sub * 4 + 2]);
        if (this.u_pic != null && int1 > -1 && int1 < this.u_pic.length && this.u_pic[int1] != null) {
            this.g_bitoff.drawImage(this.u_pic[int1], this.menu_coords[n * 7] - this.m_sub_indent + this.m_sub_icon_indent, this.bitheight / 2 - this.u_pic[int1].getHeight(this) / 2, this);
        }
        this.g_bitoff.setColor(mu_sub_hltextcolor);
        this.g_bitoff.drawString(s, this.menu_coords[n * 7], this.bitheight / 2 - this.fm_sub_font.getHeight() / 2 + this.fm_sub_font.getAscent());
        this.getGraphics().drawImage(this.bitoff, 0, last_bitstart, this);
    }
    
    public void checkNextLevel(final boolean b, final int n, final int n2, final int n3) {
        if (b) {
            final int int1 = Integer.parseInt(this.subs[n + n2 * 4]);
            this.cur_link = this.subs[n + n2 * 4 + 3];
            if (int1 == -1) {
                return;
            }
            for (int i = this.cur_level; i > this.real_level; --i) {
                this.shrinkSub(i);
            }
            if (this.cur_level > this.real_level) {
                this.UndoSubHighlight();
            }
            this.cur_level = this.real_level;
            ++this.cur_level;
            this.CreateSub(int1 * 5, n2);
            this.growSub(this.cur_level, n2 + 1);
        }
    }
    
    public void showthehand(final int n, final int n2, final int n3) {
        this.cur_link = this.subs[n + n2 * 4 + 3];
        if (this.m_showhand && this.cur_link != "-1") {
            this.the_frame.setCursor(12);
            return;
        }
        this.the_frame.setCursor(0);
    }
    
    public void SubHighlight(final int last_sub, final int last_level, int last_bitstart, final boolean b, final int n, final int n2) {
        this.UndoH();
        ++last_bitstart;
        this.sub_highlighted = true;
        this.last_sub = last_sub;
        this.last_level = last_level;
        this.last_bitstart = last_bitstart;
        final int n3 = this.menu_coords[last_level * 7 + 6];
        final Color sub_hlcolor = this.m_sub_hlcolor;
        final Color sub_hlocolor = this.m_sub_hlocolor;
        final Color sub_hltextcolor = this.m_sub_hltextcolor;
        final Color color = this.mc_sub_hlcolor[last_level];
        final Color color2 = this.mc_sub_hlocolor[last_level];
        final Color color3 = this.mc_sub_hltextcolor[last_level];
        int sub_indent = this.m_sub_indent;
        this.showthehand(this.menu_coords[this.real_level * 7 + 6], last_sub, n2);
        if (this.m_sub_icon_indent < 0) {
            sub_indent = this.m_sub_indent - this.m_sub_icon_indent + 1;
        }
        if (this.m_hl_text_only) {
            sub_indent = 3;
        }
        final int n4 = this.menu_coords[last_level * 7] - sub_indent;
        this.g_bitoff.drawImage(this.ts, 0, -last_bitstart, this);
        if (color != null) {
            this.g_bitoff.setColor(color);
            this.g_bitoff.fillRect(n4, 0, this.d.width - n4, this.bitheight);
        }
        if (color2 != null) {
            this.g_bitoff.setColor(color2);
            this.g_bitoff.drawRect(n4, 0, this.d.width - (n4 + 1), this.bitheight);
        }
        final String s = this.subs[n3 + last_sub * 4 + 1];
        final int int1 = Integer.parseInt(this.subs[n3 + last_sub * 4 + 2]);
        if (this.i_pic != null && int1 > -1 && int1 < this.i_pic.length && this.i_pic[int1] != null) {
            this.g_bitoff.drawImage(this.s_pic[int1], this.menu_coords[last_level * 7] - this.m_sub_indent + this.m_sub_icon_indent, this.bitheight / 2 - this.s_pic[int1].getHeight(this) / 2, this);
        }
        this.g_bitoff.setColor(color3);
        this.g_bitoff.drawString(s, this.menu_coords[last_level * 7], this.bitheight / 2 - this.fm_sub_font.getHeight() / 2 + this.fm_sub_font.getAscent());
        this.getGraphics().drawImage(this.bitoff, 0, last_bitstart, this);
    }
    
    public synchronized void UndoSubHighlight() {
        this.getGraphics().drawImage(this.ts, 0, 0, this);
        this.usub_h = -1;
        this.sub_highlighted = false;
        this.last_sub = -1;
        this.last_level = -1;
        this.cur_link = "-1";
        this.the_frame.setCursor(0);
    }
    
    public void start() {
        if (!this.tag) {
            return;
        }
        if (this.first_run) {
            (this.trun = new Thread(this)).start();
            return;
        }
        this.trun.resume();
    }
    
    public void stop() {
        this.trun.interrupt();
    }
    
    public void destroy() {
        if (this.trun != null) {
            this.trun.stop();
            this.trun = null;
        }
    }
    
    public void run() {
        while (true) {
            if (this.first_run) {
                this.showStatus("Loading Menu Images...");
                this.FirstRun(this.getGraphics());
                this.drawMain();
                this.first_run = false;
                this.paintIt(this.getGraphics());
            }
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.first_run) {
            return true;
        }
        this.checkMouse(n, n2, false);
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.first_run) {
            return true;
        }
        this.checkMouse(n, n2, true);
        if (this.cur_link.equals("-1")) {
            return true;
        }
        final int index = this.cur_link.indexOf("|");
        String s;
        String prev_url;
        if (index > 0) {
            if (index < this.cur_link.length() - 1) {
                s = this.cur_link.substring(0, index);
                prev_url = this.cur_link.substring(index + 1);
            }
            else {
                prev_url = this.cur_link;
                s = this.m_loadwhere;
            }
        }
        else {
            prev_url = this.cur_link;
            s = this.m_loadwhere;
        }
        if (!prev_url.equals("-1")) {
            if (this.m_check_previous_link && this.prev_url.equals(prev_url)) {
                this.cur_link = "-1";
                return true;
            }
            try {
                if (s.equalsIgnoreCase("javascript")) {
                    JSObject.getWindow((Applet)this).eval(prev_url);
                }
                else {
                    this.goURL = new URL(this.getDocumentBase(), prev_url);
                    this.getAppletContext().showDocument(this.goURL, s);
                }
                this.prev_url = prev_url;
            }
            catch (MalformedURLException ex) {}
        }
        this.cur_link = "-1";
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouse_in = true;
        if (!this.tag) {
            this.showStatus(this.m_onsbtext);
            return true;
        }
        if (this.first_run) {
            this.showStatus("Loading Menu Images...");
        }
        else {
            this.showStatus(this.m_onsbtext);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouse_in = true;
        if (this.sub_highlighted || this.usub_h > -1) {
            this.UndoSubHighlight();
            this.on_sub_num = -1;
        }
        else if (this.last_drawn != -1) {
            this.getGraphics().drawImage(this.ts, 0, 0, this);
            this.last_drawn = -1;
        }
        if (!this.tag) {
            this.showStatus(this.m_onsbtext);
            return true;
        }
        if (this.first_run) {
            this.showStatus("Loading Menu Images...");
        }
        else {
            this.showStatus(this.m_offsbtext);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.first_run) {
            return true;
        }
        this.checkMouse(n, n2, false);
        return true;
    }
    
    public synchronized void checkMouse(final int n, final int n2, final boolean b) {
        if (this.cur_expand > -1 && this.cur_level > -1 && n2 < this.m_pic_ycord[this.cur_expand] + this.m_main_height + this.add_m && n2 > this.m_pic_ycord[this.cur_expand] + this.m_main_height) {
            this.mStart = this.m_pic_ycord[this.cur_expand] + this.m_main_height;
            final int n3 = (n2 - this.mStart) / this.bitheight + 1;
            final int n4 = this.menu_coords[this.cur_level * 7 + 1] + this.menu_coords[this.cur_level * 7 + 3] - 1;
            int n5 = this.addB + this.menu_coords[this.cur_level * 7 + 4];
            int n6 = 0;
            if (n2 > n4) {
                for (int i = this.cur_level - 1; i >= 0; --i) {
                    final int n7 = i * 7;
                    n5 += this.menu_coords[n7 + 4] - this.menu_coords[n7 + 7 + 5];
                    if (n3 <= n5) {
                        this.real_level = i;
                        this.MouseInSub(this.menu_coords[n7 + 4] - (n5 - n3), b, n3);
                        return;
                    }
                }
                return;
            }
            if (n2 > this.menu_coords[this.cur_level * 7 + 1]) {
                this.real_level = this.cur_level;
                this.MouseInSub(n3 - this.addB, b, n3);
                return;
            }
            for (int j = 0; j < this.cur_level; ++j) {
                final int n8 = j * 7;
                if (n3 <= n6 + this.menu_coords[n8 + 7 + 5]) {
                    this.real_level = j;
                    this.MouseInSub(n3 - n6, b, n3);
                    return;
                }
                n6 += this.menu_coords[n8 + 7 + 5];
            }
        }
        else {
            this.real_level = -1;
            if (this.sub_highlighted || this.usub_h > -1) {
                this.UndoSubHighlight();
            }
            this.on_sub_num = -1;
            if (this.cur_expand > -1) {
                if (!this.MouseInMain(n2, b, 0, this.cur_expand + 1, 0)) {
                    this.MouseInMain(n2, b, this.cur_expand + 1, this.num_main, this.add_m);
                }
                return;
            }
            if (!this.MouseInMain(n2, b, 0, this.num_main, 0)) {
                this.UndoH();
            }
        }
    }
    
    public void UndoH() {
        if (this.last_drawn != -1) {
            this.getGraphics().drawImage(this.ts, 0, 0, this);
            this.last_drawn = -1;
            this.mu_h = false;
        }
    }
    
    public boolean MouseInMain(final int n, final boolean b, final int n2, final int n3, final int n4) {
        for (int i = n2; i < n3; ++i) {
            if (n > this.m_pic_ycord[i] + n4 && n < this.m_pic_ycord[i] + this.m_main_height + n4) {
                if (i != this.cur_expand) {
                    if (i != this.last_drawn) {
                        this.getGraphics().drawImage(this.ts, 0, 0, this);
                        this.last_drawn = i;
                        if (b) {
                            this.ExpandSub(b, i);
                        }
                        else {
                            this.HighlightPic(i, this.m_pic_ycord[i] + n4);
                        }
                        return true;
                    }
                }
                else {
                    if (!this.mu_h || i != this.last_drawn) {
                        this.UndoH();
                    }
                    if (i != this.last_drawn) {
                        this.UHighlightPic(i, this.m_pic_ycord[i] + n4);
                        this.last_drawn = i;
                    }
                }
                if (b) {
                    if (i != this.cur_expand) {
                        this.ExpandSub(b, i);
                    }
                    else {
                        if (this.cur_level != -1) {
                            for (int j = this.cur_level; j > this.real_level; --j) {
                                this.shrinkSub(j);
                            }
                            this.mu_h = false;
                            this.cur_level = -1;
                            this.cur_expand = -1;
                            this.HighlightPic(i, this.m_pic_ycord[i] + n4);
                            this.cur_link = "-1";
                            return true;
                        }
                        if (!this.drawMainMenu(i)) {
                            this.cur_link = this.m_main_desturl[i];
                        }
                        else {
                            this.growSub(this.cur_level, 0);
                            this.cur_expand = i;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public void ExpandSub(final boolean b, final int cur_expand) {
        if (b) {
            if (!this.no_subs[cur_expand] && this.cur_level > -1) {
                for (int i = this.cur_level; i > this.real_level; --i) {
                    this.shrinkSub(i);
                }
            }
            if (!this.drawMainMenu(cur_expand)) {
                this.cur_link = this.m_main_desturl[cur_expand];
                return;
            }
            this.cur_link = this.m_main_desturl[cur_expand];
            this.growSub(this.cur_level, 0);
            this.mu_h = false;
            this.cur_expand = cur_expand;
        }
    }
    
    public void MouseInSub(final int on_sub_num, final boolean b, final int n) {
        final int n2 = this.mStart + this.bitheight * (n - 1);
        if (this.cur_link.equals("-1")) {
            final String cur_link = this.cur_link;
        }
        if (this.real_level >= this.cur_level) {
            if (on_sub_num != this.on_sub_num || this.last_level != this.cur_level) {
                if (this.sub_highlighted || this.usub_h > -1) {
                    this.UndoSubHighlight();
                }
                this.on_sub_num = on_sub_num;
                this.SubHighlight(on_sub_num - 1, this.cur_level, n2, b, n, n2);
            }
            if (b) {
                this.on_sub_num = on_sub_num;
                this.checkNextLevel(b, this.menu_coords[this.cur_level * 7 + 6], on_sub_num - 1, n2);
            }
            return;
        }
        if (this.menu_coords[(this.real_level + 1) * 7 + 5] != on_sub_num) {
            if (this.on_sub_num != on_sub_num || this.real_level != this.last_level) {
                if (this.sub_highlighted || this.usub_h > -1) {
                    this.UndoSubHighlight();
                }
                this.on_sub_num = on_sub_num;
                this.SubHighlight(on_sub_num - 1, this.real_level, n2, b, n, n2);
            }
            this.on_sub_num = on_sub_num;
            this.checkNextLevel(b, this.menu_coords[this.real_level * 7 + 6], on_sub_num - 1, n2);
            return;
        }
        if (this.sub_highlighted) {
            this.UndoSubHighlight();
        }
        if (this.usub_h != this.real_level) {
            if (this.usub_h > -1) {
                this.UndoSubHighlight();
            }
            this.USubHighlight(on_sub_num - 1, this.real_level, n2);
            return;
        }
        if (b) {
            for (int i = this.cur_level; i > this.real_level; --i) {
                this.shrinkSub(i);
            }
            this.cur_level = this.real_level;
        }
        this.on_sub_num = on_sub_num;
    }
    
    public void drawMain() {
        this.mainoff = this.createImage(this.d.width, this.m_main_height + 1);
        (this.g_mainoff = this.mainoff.getGraphics()).setFont(this.main_font);
        this.g_ts.setFont(this.main_font);
        if (this.m_tile_menus_only) {
            this.g_ts.clipRect(0, 0, this.d.width, this.m_main_height * this.m_maindesc.length + this.m_top_offset + 1);
        }
        if (this.m_bgcolor != null) {
            this.g_ts.setColor(this.m_bgcolor);
            this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
        }
        if (this.m_main_bgimage != null) {
            if (this.m_tile_bg) {
                int n = 0;
                int n2 = 0;
                final int width = this.m_main_bgimage.getWidth(this);
                final int height = this.m_main_bgimage.getHeight(this);
                if (this.main_gif_tile) {
                    n2 = -1;
                }
                while (true) {
                    this.g_ts.drawImage(this.m_main_bgimage, n, n2, this);
                    n += width;
                    if (n > this.d.width) {
                        n = 0;
                        n2 += height;
                        if (this.main_gif_tile) {
                            --n2;
                        }
                        if (this.m_tile_menus_only) {
                            if (n2 > this.m_main_height * this.m_maindesc.length) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (n2 > this.d.height) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            else {
                this.g_ts.drawImage(this.m_main_bgimage, 0, 0, this);
            }
        }
        for (int i = 0; i < this.m_maindesc.length; ++i) {
            final String[] idandText = this.getIdandText(this.m_maindesc[i]);
            this.m_mainicon[i] = Integer.parseInt(idandText[0]);
            this.m_maindesc[i] = idandText[1];
            if (this.i_pic != null && this.m_mainicon[i] > -1 && this.m_mainicon[i] < this.i_pic.length && this.i_pic[this.m_mainicon[i]] != null) {
                this.g_ts.drawImage(this.i_pic[this.m_mainicon[i]], this.m_main_icon_indent, this.m_main_height * i + this.m_top_offset + this.m_main_height / 2 - this.i_pic[this.m_mainicon[i]].getHeight(this) / 2, this);
            }
            if (this.m_main_ocolor != null) {
                this.g_ts.setColor(this.m_main_ocolor);
                this.g_ts.drawRect(0, this.m_main_height * i + this.m_top_offset, this.d.width - 1, this.m_main_height);
            }
            this.g_ts.setColor(this.m_main_textcolor);
            this.g_ts.drawString(this.m_maindesc[i], this.m_main_indent, this.m_main_height / 2 - this.fm_main_font.getHeight() / 2 + this.fm_main_font.getAscent() + this.m_main_height * i + this.m_top_offset);
            this.m_pic_ycord[i] = this.m_main_height * i + this.m_top_offset;
        }
        this.g_ts = this.ts.getGraphics();
    }
    
    public void FirstRun(final Graphics graphics) {
        this.g_ts.setColor(this.m_bgcolor);
        this.g_ts.fillRect(0, 0, this.d.width, this.d.height + this.extra_length + 1);
        this.g_ts.setFont(new Font("Helvetica", 0, 11));
        final FontMetrics fontMetrics = this.g_ts.getFontMetrics();
        this.g_ts.setColor(this.m_load_msgcolor);
        this.g_ts.drawString(this.m_load_msg, this.d.width / 2 - fontMetrics.stringWidth(this.m_load_msg) / 2, this.d.height / 2 + fontMetrics.getHeight() / 2);
        this.getGraphics().drawImage(this.ts, 0, 0, this);
        for (int i = this.break_pictype; i < this.num_icons; ++i) {
            if ((this.i_pic[i] = this.trackReturn(this.m_icon_file[i])) != null) {
                this.i_pic_stat[i] = true;
            }
            this.break_pictype = i;
        }
        for (int j = this.break_picload; j < this.num_icons; ++j) {
            if ((this.s_pic[j] = this.trackReturn(this.m_switchfile[j])) != null) {
                this.s_pic_stat[j] = true;
            }
            else {
                this.s_pic[j] = this.i_pic[j];
            }
            this.break_picload = j;
        }
        for (int k = this.break_up; k < this.num_icons; ++k) {
            if ((this.u_pic[k] = this.trackReturn(this.m_switchufile[k])) != null) {
                this.u_pic_stat[k] = true;
            }
            else {
                this.u_pic[k] = this.i_pic[k];
            }
            this.break_up = k;
        }
        if ((this.m_subbg = this.trackReturn(this.m_sub_bgfile)) != null) {
            this.m_bgfile_stat = true;
        }
        for (int l = this.break_bgload; l < this.level_max; ++l) {
            if ((this.mc_subbg[l] = this.trackReturn(this.mc_sub_bgfile[l])) != null) {
                this.mc_bgfile_stat[l] = true;
            }
            this.break_bgload = l;
        }
        this.m_main_bgimage = this.trackReturn(this.m_main_bgfile);
        this.setBackground(this.m_bgcolor);
        this.g_ts.setColor(this.m_bgcolor);
        this.g_ts.fillRect(0, 0, this.d.width, this.d.height + this.extra_length + 1);
        if (this.mouse_in) {
            this.showStatus(this.m_onsbtext);
            return;
        }
        this.showStatus(this.m_offsbtext);
    }
    
    public Image trackReturn(final String s) {
        final ocgifix ocgifix = new ocgifix();
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (s.equals("-1")) {
            return null;
        }
        final Image image = this.getImage(this.getDocumentBase(), s);
        if (image == null) {
            return null;
        }
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
    
    public String[] getIdandText(final String s) {
        final int index = s.indexOf("|");
        String substring;
        String substring2;
        if (index > 0) {
            if (index < s.length()) {
                substring = s.substring(0, index);
                substring2 = s.substring(index + 1);
            }
            else {
                substring2 = s;
                substring = "-1";
            }
        }
        else {
            substring2 = s;
            substring = "-1";
        }
        return new String[] { substring, substring2 };
    }
    
    public slidem() {
        this.usub_h = -1;
        this.mu_h = false;
        this.mouse_in = true;
        this.prev_url = "-1";
        this.m_showhand = false;
        this.last_bitstart = -1;
        this.cur_expand = -1;
        this.sub_highlighted = false;
        this.tag = false;
        this.first_run = true;
        this.m_check_previous_link = false;
        this.m_loadwhere = "_self";
        this.m_bgcolor = Color.white;
        this.m_jump_size = 2;
        this.m_delay = 10;
        this.m_tile_menus_only = true;
        this.m_hl_text_only = false;
        this.m_retract_from_offscreen = true;
        this.m_sub_indent = 40;
        this.m_sub_height = -1;
        this.m_sub_color = Color.yellow;
        this.m_sub_textcolor = Color.black;
        this.m_sub_hltextcolor = Color.red;
        this.m_sub_icon_indent = 1;
        this.m_load_msg = "Loading Images...";
        this.m_load_msgcolor = Color.white;
        this.mu_sub_hltextcolor = Color.red;
        this.url = "-1";
        this.m_onsbtext = "OpenCube - Sliding Tree Menu";
        this.m_offsbtext = "Java by OpenCube";
        this.m_main_height = -1;
        this.m_main_indent = 10;
        this.m_main_icon_indent = 1;
        this.m_main_bgfile = "-1";
        this.main_gif_tile = false;
        this.m_tile_bg = true;
        this.m_main_textcolor = Color.black;
        this.m_main_hltextcolor = Color.red;
        this.mu_main_hltextcolor = Color.red;
        this.last_drawn = -1;
        this.last_level = -1;
        this.cur_level = -1;
        this.cur_link = "-1";
    }
}
