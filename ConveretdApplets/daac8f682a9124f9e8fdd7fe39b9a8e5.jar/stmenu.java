import java.awt.MediaTracker;
import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class stmenu extends Applet implements Runnable
{
    Thread trun;
    private FontMetrics subfm;
    private FontMetrics mainfm;
    private Dimension d;
    private Frame the_frame;
    private Image[] arErase;
    private Graphics g_arErase;
    boolean mouseIn;
    boolean animating;
    boolean init_sb;
    boolean syncarrow;
    private boolean m_showhand;
    int bottom_height;
    int er_id;
    boolean go_expand;
    boolean go_retract;
    int sbx;
    int rmainWidth;
    int rsubWidth;
    boolean bar_removed;
    boolean first_force;
    boolean tag;
    int subHL;
    int num_main;
    int max_subs;
    int total_subs;
    int[] num_mainsubs;
    boolean[] m_sub_expanded;
    int[] sub_id;
    private int[] sub_starty;
    private int[] sub_offy;
    int[] main_starty;
    int[] sub_start_index;
    int bitHeight;
    int[] main_icon_id;
    int[] sub_icon_id;
    private int curMain;
    private int offHeight;
    private boolean inputHeightFixed;
    private int curSub;
    Image bitoff;
    Graphics g_bitoff;
    Image bitorig;
    Graphics g_bitorig;
    Image bitsub;
    Graphics g_bitsub;
    Image endcap;
    private boolean m_tile_bg_insb;
    private int m_startopen_index;
    private boolean m_hide_sb;
    private boolean m_tilebgin_margins;
    private boolean m_unmaintext;
    private boolean m_unsubtext;
    private boolean m_clip_main_bg;
    private String[] m_main_url;
    private String m_loadwhere;
    private String[] m_sub_url;
    private Color m_main_texthlcolor;
    private Color m_main_textcolor;
    private Color m_arrow_activecolor;
    private Color m_arrowhlcolor;
    private Color m_arrowcolor;
    private Color m_main_outcolor;
    private Color m_main_hloutcolor;
    private Color m_main_boxcolor;
    private Color m_sub_boxcolor;
    private Color m_sub_outcolor;
    private Color m_sub_hloutcolor;
    private Color m_sub_textcolor;
    private Color m_sub_hltextcolor;
    private String m_onsbtext;
    private String m_offsbtext;
    private Color m_bgcolor;
    private Font m_main_font;
    private Font m_sub_font;
    private String[] m_sub_desc;
    private String[] m_main_desc;
    private int m_main_lmargin;
    private int m_main_rmargin;
    private int m_main_icon_voffset;
    private int m_sub_icon_voffset;
    private int m_main_icon_indent;
    private int m_main_text_indent;
    private boolean m_animate_arrows;
    private int m_sub_lmargin;
    private int m_sub_rmargin;
    private int m_sub_text_indent;
    private int m_sub_icon_indent;
    private int m_arrowxoffset;
    private int m_arrowyoffset;
    private int m_jump_size;
    private int m_delay;
    private int m_item_height;
    private int m_additional_clip_height;
    private String m_endcap_image;
    private int m_maintext_yoffset;
    private String m_load_msg;
    private Color m_load_msgcolor;
    private Font m_load_font;
    private int m_sbwidth;
    private int m_lineinc;
    private int m_si;
    private Color m_bc;
    private Color m_ac;
    private Color m_hc;
    private Color m_bgc;
    private Color m_sc;
    private Color m_oc;
    private Color m_mac;
    private Color m_unact;
    private Color m_machl;
    private boolean m_sbflat;
    private boolean m_indent_bubble;
    private String[] m_icon_file;
    private String[] m_icon_switchfile;
    private boolean m_tile_mainbg;
    private boolean m_tile_subbg;
    private String m_sub_bgfile;
    private String m_main_bgfile;
    private int maxExpandHeight;
    private int curExpandHeight;
    private int cur_mainHL;
    private int my;
    private int mx;
    private stsb sb;
    private int scroll_y;
    private int old_scrolly;
    private Image ts;
    private Graphics g_ts;
    private Image main;
    private Graphics g_main;
    boolean first_run;
    boolean mouse_in;
    Image[] i_pic;
    Image[] s_pic;
    Image mainBG;
    Image subBG;
    Dimension[] icon_d;
    int i_pic_break;
    int s_pic_break;
    int num_icons;
    Image sb_bg;
    
    public void init() {
        final String s = "SmartTree Menu (Developer Version), Copyright (c) 2000, OpenCube Inc.";
        this.d = this.size();
        int n = 0;
        int max_subs = 0;
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
            final String property = properties.getProperty("loadmsgcolor");
            if (property != null) {
                this.m_load_msgcolor = occcolor.ConvertColor(property);
            }
            final String property2 = properties.getProperty("onsbtext");
            if (property2 != null) {
                this.m_onsbtext = property2;
            }
            final String property3 = properties.getProperty("offsbtext");
            if (property3 != null) {
                this.m_offsbtext = property3;
            }
            final String property4 = properties.getProperty("loadmsg");
            if (property4 != null) {
                this.m_load_msg = property4;
            }
            final String property5 = properties.getProperty("maintextyoffset");
            if (property5 != null) {
                this.m_maintext_yoffset = Integer.parseInt(property5);
            }
            final String property6 = properties.getProperty("loadfont");
            if (property6 != null) {
                this.m_load_font = ocfontc.getFontSD(property6, ",");
            }
            else {
                this.m_load_font = new Font("Helvetica", 0, 12);
            }
            final String property7 = properties.getProperty("loadwhere");
            if (property7 != null) {
                this.m_loadwhere = property7;
            }
            final String property8 = properties.getProperty("animatearrows");
            if (property8 != null) {
                this.m_animate_arrows = Boolean.valueOf(property8);
            }
            final String property9 = properties.getProperty("showhand");
            if (property9 != null) {
                this.m_showhand = Boolean.valueOf(property9);
            }
            final String property10 = properties.getProperty("tilebginmargins");
            if (property10 != null) {
                this.m_tilebgin_margins = Boolean.valueOf(property10);
            }
            final String property11 = properties.getProperty("underlinemaintext");
            if (property11 != null) {
                this.m_unmaintext = Boolean.valueOf(property11);
            }
            final String property12 = properties.getProperty("hidebar");
            if (property12 != null) {
                this.m_hide_sb = Boolean.valueOf(property12);
            }
            final String property13 = properties.getProperty("underlinesubtext");
            if (property13 != null) {
                this.m_unsubtext = Boolean.valueOf(property13);
            }
            final String property14 = properties.getProperty("clipmainbg");
            if (property14 != null) {
                this.m_clip_main_bg = Boolean.valueOf(property14);
            }
            final String property15 = properties.getProperty("clipadditionheight");
            if (property15 != null) {
                this.m_additional_clip_height = Integer.parseInt(property15);
            }
            final String property16 = properties.getProperty("tilemainbg");
            if (property16 != null) {
                this.m_tile_mainbg = Boolean.valueOf(property16);
            }
            final String property17 = properties.getProperty("tilesubbg");
            if (property17 != null) {
                this.m_tile_subbg = Boolean.valueOf(property17);
            }
            final String property18 = properties.getProperty("bgcolor");
            if (property18 != null) {
                this.m_bgcolor = occcolor.ConvertColor(property18);
            }
            final String property19 = properties.getProperty("mainboxcolor");
            if (property19 != null) {
                this.m_main_boxcolor = occcolor.ConvertColor(property19);
            }
            final String property20 = properties.getProperty("maintextcolor");
            if (property20 != null) {
                this.m_main_textcolor = occcolor.ConvertColor(property20);
            }
            final String property21 = properties.getProperty("maintexthlcolor");
            if (property21 != null) {
                this.m_main_texthlcolor = occcolor.ConvertColor(property21);
            }
            final String property22 = properties.getProperty("mainoutcolor");
            if (property22 != null) {
                this.m_main_outcolor = occcolor.ConvertColor(property22);
            }
            final String property23 = properties.getProperty("mainhloutcolor");
            if (property23 != null) {
                this.m_main_hloutcolor = occcolor.ConvertColor(property23);
            }
            final String property24 = properties.getProperty("subboxcolor");
            if (property24 != null) {
                this.m_sub_boxcolor = occcolor.ConvertColor(property24);
            }
            final String property25 = properties.getProperty("suboutcolor");
            if (property25 != null) {
                this.m_sub_outcolor = occcolor.ConvertColor(property25);
            }
            final String property26 = properties.getProperty("subhloutcolor");
            if (property26 != null) {
                this.m_sub_hloutcolor = occcolor.ConvertColor(property26);
            }
            final String property27 = properties.getProperty("subtextcolor");
            if (property27 != null) {
                this.m_sub_textcolor = occcolor.ConvertColor(property27);
            }
            final String property28 = properties.getProperty("subhltextcolor");
            if (property28 != null) {
                this.m_sub_hltextcolor = occcolor.ConvertColor(property28);
            }
            final String property29 = properties.getProperty("arrowcolor");
            if (property29 != null) {
                this.m_arrowcolor = occcolor.ConvertColor(property29);
            }
            final String property30 = properties.getProperty("arrowhlcolor");
            if (property30 != null) {
                this.m_arrowhlcolor = occcolor.ConvertColor(property30);
            }
            final String property31 = properties.getProperty("arrowactivecolor");
            if (property31 != null) {
                this.m_arrow_activecolor = occcolor.ConvertColor(property31);
            }
            final String property32 = properties.getProperty("mainbgimage");
            if (property32 != null) {
                this.m_main_bgfile = property32;
            }
            final String property33 = properties.getProperty("subbgimage");
            if (property33 != null) {
                this.m_sub_bgfile = property33;
            }
            final String property34 = properties.getProperty("jumpsize");
            if (property34 != null) {
                this.m_jump_size = Integer.parseInt(property34);
            }
            final String property35 = properties.getProperty("startopenindex");
            if (property35 != null) {
                this.m_startopen_index = Integer.parseInt(property35);
            }
            final String property36 = properties.getProperty("jumpdelay");
            if (property36 != null) {
                this.m_delay = Integer.parseInt(property36);
            }
            final String property37 = properties.getProperty("mainlmargin");
            if (property37 != null) {
                this.m_main_lmargin = Integer.parseInt(property37);
            }
            final String property38 = properties.getProperty("mainrmargin");
            if (property38 != null) {
                this.m_main_rmargin = Integer.parseInt(property38);
            }
            final String property39 = properties.getProperty("sublmargin");
            if (property39 != null) {
                this.m_sub_lmargin = Integer.parseInt(property39);
            }
            final String property40 = properties.getProperty("subrmargin");
            if (property40 != null) {
                this.m_sub_rmargin = Integer.parseInt(property40);
            }
            this.m_sub_lmargin += this.m_main_lmargin;
            this.m_sub_rmargin += this.m_main_rmargin;
            final String property41 = properties.getProperty("maintextindent");
            if (property41 != null) {
                this.m_main_text_indent = Integer.parseInt(property41);
            }
            final String property42 = properties.getProperty("mainiconindent");
            if (property42 != null) {
                this.m_main_icon_indent = Integer.parseInt(property42);
            }
            final String property43 = properties.getProperty("subiconindent");
            if (property43 != null) {
                this.m_sub_icon_indent = Integer.parseInt(property43);
            }
            final String property44 = properties.getProperty("mainicontopoffset");
            if (property44 != null) {
                this.m_main_icon_voffset = Integer.parseInt(property44);
            }
            final String property45 = properties.getProperty("subicontopoffset");
            if (property45 != null) {
                this.m_sub_icon_voffset = Integer.parseInt(property45);
            }
            final String property46 = properties.getProperty("subtextindent");
            if (property46 != null) {
                this.m_sub_text_indent = Integer.parseInt(property46);
            }
            final String property47 = properties.getProperty("arrowyoffset");
            if (property47 != null) {
                this.m_arrowyoffset = Integer.parseInt(property47);
            }
            final String property48 = properties.getProperty("arrowxoffset");
            if (property48 != null) {
                this.m_arrowxoffset = Integer.parseInt(property48);
            }
            final String property49 = properties.getProperty("itemheight");
            if (property49 != null) {
                this.m_item_height = Integer.parseInt(property49);
                if (this.m_item_height > 0) {
                    this.inputHeightFixed = true;
                }
            }
            final String property50 = properties.getProperty("mainfont");
            if (property50 != null) {
                this.m_main_font = ocfontc.getFontSD(property50, ",");
            }
            else {
                this.m_main_font = new Font("Helvetica", 0, 12);
            }
            final String property51 = properties.getProperty("subfont");
            if (property51 != null) {
                this.m_sub_font = ocfontc.getFontSD(property51, ",");
            }
            else {
                this.m_sub_font = new Font("Helvetica", 0, 12);
            }
            final String property52 = properties.getProperty("endcapimage");
            if (property52 != null) {
                this.m_endcap_image = property52;
            }
            final String property53 = properties.getProperty("barwidth");
            if (property53 != null) {
                this.m_sbwidth = Integer.parseInt(property53);
            }
            final String property54 = properties.getProperty("barbuttoncolor");
            if (property54 != null) {
                this.m_bc = occcolor.ConvertColor(property54);
            }
            final String property55 = properties.getProperty("bararrowcolor");
            if (property55 != null) {
                this.m_ac = occcolor.ConvertColor(property55);
            }
            final String property56 = properties.getProperty("bararrowhlcolor");
            if (property56 != null) {
                this.m_hc = occcolor.ConvertColor(property56);
            }
            final String property57 = properties.getProperty("barbgcolor");
            if (property57 != null) {
                this.m_bgc = occcolor.ConvertColor(property57);
            }
            final String property58 = properties.getProperty("barslidecolor");
            if (property58 != null) {
                this.m_sc = occcolor.ConvertColor(property58);
            }
            final String property59 = properties.getProperty("baroutlinecolor");
            if (property59 != null) {
                this.m_oc = occcolor.ConvertColor(property59);
            }
            final String property60 = properties.getProperty("barbarrowcolor");
            if (property60 != null) {
                this.m_mac = occcolor.ConvertColor(property60);
            }
            else {
                this.m_mac = this.m_bc;
            }
            final String property61 = properties.getProperty("barbarrowhlcolor");
            if (property61 != null) {
                this.m_machl = occcolor.ConvertColor(property61);
            }
            else {
                this.m_machl = this.m_mac;
            }
            final String property62 = properties.getProperty("barinactivecolor");
            if (property62 != null) {
                this.m_unact = occcolor.ConvertColor(property62);
            }
            final String property63 = properties.getProperty("barslideinset");
            if (property63 != null) {
                this.m_si = Integer.parseInt(property63);
            }
            final String property64 = properties.getProperty("barflat");
            if (property64 != null) {
                this.m_sbflat = Boolean.valueOf(property64);
            }
            final String property65 = properties.getProperty("barindentbubble");
            if (property65 != null) {
                this.m_indent_bubble = Boolean.valueOf(property65);
            }
            final String property66 = properties.getProperty("tilebginbar");
            if (property66 != null) {
                this.m_tile_bg_insb = Boolean.valueOf(property66);
            }
            this.sbx = this.d.width - this.m_sbwidth;
            this.d = new Dimension(this.d.width - this.m_sbwidth, this.d.height);
            int total_subs = 0;
            while (properties.getProperty("maindesc" + n) != null) {
                while (properties.getProperty("subdesc" + n + "-" + max_subs) != null) {
                    ++max_subs;
                    ++total_subs;
                    if (max_subs > this.max_subs) {
                        this.max_subs = max_subs;
                    }
                }
                max_subs = 0;
                ++n;
                ++this.num_main;
            }
            this.total_subs = total_subs;
            this.sub_id = new int[this.total_subs];
            this.m_sub_desc = new String[this.total_subs];
            this.sub_icon_id = new int[this.total_subs];
            this.sub_starty = new int[this.total_subs];
            this.sub_offy = new int[this.total_subs];
            this.m_sub_url = new String[this.total_subs];
            this.m_main_url = new String[this.num_main];
            this.num_mainsubs = new int[this.num_main];
            this.m_main_desc = new String[this.num_main];
            this.main_icon_id = new int[this.num_main];
            this.m_sub_expanded = new boolean[this.num_main];
            this.main_starty = new int[this.num_main];
            this.sub_start_index = new int[this.num_main];
            this.arErase = new Image[this.num_main];
            int n2 = 0;
            for (int i = 0; i < this.num_main; ++i) {
                for (int n3 = 0; properties.getProperty("subdesc" + i + "-" + n3) != null; ++n3) {
                    final int[] num_mainsubs = this.num_mainsubs;
                    final int n4 = i;
                    ++num_mainsubs[n4];
                }
                for (int j = 0; j < this.num_mainsubs[i]; ++j) {
                    this.sub_id[n2] = i;
                    final String property67 = properties.getProperty("subdesc" + i + "-" + j);
                    if (property67 != null) {
                        this.m_sub_desc[n2] = property67;
                        final String[] idandText = this.getIdandText(this.m_sub_desc[n2]);
                        this.sub_icon_id[n2] = Integer.parseInt(idandText[0]);
                        this.m_sub_desc[n2] = idandText[1];
                    }
                    else {
                        this.m_sub_desc[n2] = "";
                        this.sub_icon_id[i] = -1;
                    }
                    final String property68 = properties.getProperty("suburl" + i + "-" + j);
                    if (property68 != null) {
                        this.m_sub_url[n2] = property68;
                    }
                    else {
                        this.m_sub_url[n2] = "-1";
                    }
                    ++n2;
                }
                final String property69 = properties.getProperty("maindesc" + i);
                if (property69 != null) {
                    this.m_main_desc[i] = new String(property69);
                    final String[] idandText2 = this.getIdandText(this.m_main_desc[i]);
                    this.main_icon_id[i] = Integer.parseInt(idandText2[0]);
                    this.m_main_desc[i] = idandText2[1];
                }
                else {
                    this.m_main_desc[i] = "";
                    this.main_icon_id[i] = -1;
                }
                final String property70 = properties.getProperty("mainurl" + i);
                if (property70 != null) {
                    this.m_main_url[i] = new String(property70);
                }
                else {
                    this.m_main_url[i] = "-1";
                }
            }
            int n5 = 0;
            for (int k = 0; k < this.num_mainsubs.length; ++k) {
                this.sub_start_index[k] = n5;
                n5 += this.num_mainsubs[k];
            }
            while (properties.getProperty("iconfile" + this.num_icons) != null) {
                ++this.num_icons;
            }
            this.m_icon_file = new String[this.num_icons];
            this.m_icon_switchfile = new String[this.num_icons];
            this.i_pic = new Image[this.num_icons];
            this.s_pic = new Image[this.num_icons];
            this.icon_d = new Dimension[this.num_icons];
            for (int l = 0; l < this.num_icons; ++l) {
                final String property71 = properties.getProperty("iconfile" + l);
                if (property71 != null) {
                    this.m_icon_file[l] = new String(property71);
                }
                else {
                    this.m_icon_file[l] = new String(" ");
                }
                final String property72 = properties.getProperty("iconswitchfile" + l);
                if (property72 != null) {
                    this.m_icon_switchfile[l] = new String(property72);
                }
                else {
                    this.m_icon_switchfile[l] = this.m_icon_file[l];
                }
            }
            return;
        }
        if (key.equals("-1")) {
            this.m_onsbtext = "Incorrect Base URL Registration";
            return;
        }
        this.m_onsbtext = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    private void initSB() {
        (this.sb = new stsb()).setBarStyle(this.m_si, this.m_bc, this.m_ac, this.m_hc, this.m_bgc, this.m_sc, this.m_oc, this.m_mac, this.m_machl, this.m_unact, this.m_sbflat, this.m_indent_bubble);
        if (this.sb_bg != null && this.m_tile_bg_insb) {
            this.sb.setBarBgImage(this.sb_bg);
        }
        this.sb.setBackground(this.m_bgc);
        this.setLayout(null);
        this.sb.reshape(this.sbx, 0, this.m_sbwidth, this.d.height);
        this.add(this.sb);
        this.init_sb = true;
    }
    
    public void paint(final Graphics graphics) {
        this.paintIt();
    }
    
    private synchronized void paintIt() {
        final Graphics graphics = this.getGraphics();
        if (!this.tag) {
            graphics.setColor(Color.yellow);
            graphics.drawString(this.m_onsbtext, 2, 20);
            return;
        }
        if (!this.first_run) {
            graphics.drawImage(this.main, 0, this.scroll_y, this);
            return;
        }
        if (this.ts != null) {
            graphics.drawImage(this.ts, 0, 0, this);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseIn = true;
        if (this.first_run && this.tag) {
            this.showStatus(this.m_load_msg);
        }
        else {
            this.showStatus(this.m_onsbtext);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.my = -1;
        this.mx = -1;
        if (this.animating) {
            return true;
        }
        this.undoHL();
        this.paintIt();
        this.mouseIn = false;
        this.showStatus(this.m_offsbtext);
        return true;
    }
    
    private void undoHL() {
        this.the_frame.setCursor(0);
        if (this.cur_mainHL != -1) {
            if (this.curMain != this.cur_mainHL || !this.m_animate_arrows) {
                this.undohlMainText(this.cur_mainHL);
                this.undoArrowHL(this.cur_mainHL);
                this.paintIt();
            }
            else {
                this.undohlMainText(this.cur_mainHL);
            }
            this.cur_mainHL = -1;
            return;
        }
        if (this.subHL != -1) {
            this.undohlSubText(this.subHL);
            this.subHL = -1;
        }
    }
    
    public boolean mouseMove(final Event event, final int mx, final int my) {
        this.my = my;
        this.mx = mx;
        if (this.animating) {
            return true;
        }
        this.mouseMove(mx, my);
        return true;
    }
    
    private void mouseMove(final int n, final int n2) {
        if (n < this.d.width - this.m_main_rmargin && n > this.m_main_lmargin) {
            this.checkHL(n, n2);
            return;
        }
        this.my = -1;
        this.mx = -1;
        this.undoHL();
    }
    
    public boolean mouseDrag(final Event event, final int mx, final int my) {
        this.my = my;
        this.mx = mx;
        return true;
    }
    
    private void checkHL(final int n, final int n2) {
        this.the_frame = new Frame();
        this.the_frame = (Frame)this.getParent();
        if (n < 0 && n2 < 0) {
            this.undoHL();
            this.the_frame.setCursor(0);
            this.paintIt();
            return;
        }
        final int checkYExpandMouse = this.checkYExpandMouse(n2, this.scroll_y);
        if (checkYExpandMouse != -1) {
            if (!this.first_run && this.cur_mainHL != checkYExpandMouse) {
                if (this.cur_mainHL > -1) {
                    if (this.m_animate_arrows && this.curMain == this.cur_mainHL) {
                        this.undohlMainText(this.cur_mainHL);
                    }
                    else {
                        this.undohlMainText(this.cur_mainHL);
                        this.undoArrowHL(this.cur_mainHL);
                    }
                }
                if (checkYExpandMouse != this.curMain) {
                    this.hlMainArrows(checkYExpandMouse, false);
                    this.hlMainText(checkYExpandMouse);
                }
                else if (!this.m_animate_arrows) {
                    this.hlMainArrows(checkYExpandMouse, false);
                    this.hlMainText(checkYExpandMouse);
                }
                else {
                    this.hlMainText(checkYExpandMouse);
                }
                this.cur_mainHL = checkYExpandMouse;
            }
        }
        else {
            if ((this.cur_mainHL != -1 && this.curMain != this.cur_mainHL) || (!this.m_animate_arrows && this.cur_mainHL != -1)) {
                this.undohlMainText(this.cur_mainHL);
                this.undoArrowHL(this.cur_mainHL);
                this.cur_mainHL = -1;
            }
            else if (this.cur_mainHL > -1) {
                this.undohlMainText(this.cur_mainHL);
                this.cur_mainHL = -1;
            }
            if (n < this.d.width - this.m_sub_rmargin && n > this.m_sub_lmargin) {
                final int checkSubPosition = this.checkSubPosition(n2);
                if (checkSubPosition != -1) {
                    if (this.subHL != -1 && this.subHL != checkSubPosition) {
                        this.undohlSubText(this.subHL);
                    }
                    if (this.subHL != checkSubPosition) {
                        this.hlSubText(checkSubPosition);
                        this.subHL = checkSubPosition;
                    }
                }
            }
            else if (this.subHL != -1) {
                this.undohlSubText(this.subHL);
                this.subHL = -1;
            }
        }
        this.paintIt();
    }
    
    private void hlMainText(final int n) {
        this.g_bitoff.drawImage(this.main, 0, -this.main_starty[n], this);
        this.g_bitoff.setFont(this.m_main_font);
        this.g_bitorig.drawImage(this.bitoff, 0, 0, this);
        System.out.println(this.m_main_url[n]);
        if (this.m_main_url[n] != "-1" && this.m_showhand) {
            this.the_frame.setCursor(12);
        }
        else {
            this.the_frame.setCursor(0);
        }
        int n2 = this.m_main_lmargin + this.m_main_text_indent;
        if (this.main_icon_id[n] > -1 && this.s_pic[this.main_icon_id[n]] != null) {
            this.g_bitoff.drawImage(this.s_pic[this.main_icon_id[n]], this.m_main_lmargin + this.m_main_icon_indent, this.m_main_icon_voffset + this.m_item_height / 2 - this.icon_d[this.main_icon_id[n]].height / 2, this);
            n2 = n2 + this.icon_d[this.main_icon_id[n]].width + this.m_main_icon_indent;
        }
        if (this.m_main_hloutcolor != null) {
            this.g_bitoff.setColor(this.m_main_hloutcolor);
            this.g_bitoff.drawRect(this.m_main_lmargin, 0, this.d.width - this.m_main_lmargin - this.m_main_rmargin - 1, this.m_item_height - 1);
        }
        this.g_bitoff.setColor(this.m_main_texthlcolor);
        final int n3 = this.m_item_height - this.mainfm.getDescent() - (this.m_item_height - this.mainfm.getHeight()) / 2 + this.m_maintext_yoffset;
        this.g_bitoff.drawString(this.m_main_desc[n], n2, n3);
        if (this.m_unmaintext) {
            this.g_bitoff.drawLine(n2 + 1, n3 + 1, n2 + this.mainfm.stringWidth(this.m_main_desc[n]) - 1, n3 + 1);
        }
        if (this.subHL != -1) {
            this.undohlSubText(this.subHL);
            this.subHL = -1;
        }
        this.g_main.drawImage(this.bitoff, 0, this.main_starty[n], this);
    }
    
    private void hlSubText(final int n) {
        this.g_bitoff.drawImage(this.main, 0, -this.sub_starty[n], this);
        this.g_bitoff.setFont(this.m_sub_font);
        this.g_bitsub.drawImage(this.bitoff, 0, 0, this);
        int n2 = this.m_sub_lmargin + this.m_sub_text_indent;
        if (this.sub_icon_id[n] > -1 && this.s_pic[this.sub_icon_id[n]] != null) {
            this.g_bitoff.drawImage(this.s_pic[this.sub_icon_id[n]], this.m_sub_lmargin + this.m_sub_icon_indent, this.m_sub_icon_voffset + this.m_item_height / 2 - this.icon_d[this.sub_icon_id[n]].height / 2, this);
            n2 = n2 + this.icon_d[this.sub_icon_id[n]].width + this.m_sub_icon_indent;
        }
        if (this.m_sub_hloutcolor != null) {
            this.g_bitoff.setColor(this.m_sub_hloutcolor);
            this.g_bitoff.drawRect(this.m_sub_lmargin, 0, this.d.width - this.m_sub_rmargin - this.m_sub_lmargin - 1, this.m_item_height - 1);
        }
        this.g_bitoff.setColor(this.m_sub_hltextcolor);
        if (this.m_sub_url[n] != "-1" && this.m_showhand) {
            this.the_frame.setCursor(12);
        }
        else {
            this.the_frame.setCursor(0);
        }
        final int n3 = this.m_item_height - this.subfm.getDescent() - (this.m_item_height - this.subfm.getHeight()) / 2;
        this.g_bitoff.drawString(this.m_sub_desc[n], n2, n3);
        if (this.m_unsubtext) {
            this.g_bitoff.drawLine(n2 + 1, n3 + 1, n2 + this.subfm.stringWidth(this.m_sub_desc[n]) - 1, n3 + 1);
        }
        this.g_main.drawImage(this.bitoff, 0, this.sub_starty[n], this);
    }
    
    private void undohlSubText(final int n) {
        this.g_main.drawImage(this.bitsub, 0, this.sub_starty[n], this);
    }
    
    private void undohlMainText(final int n) {
        this.g_bitorig.clipRect(this.m_arrowxoffset, this.m_arrowyoffset, 11, 8);
        this.g_bitorig.drawImage(this.main, 0, -this.main_starty[n], this);
        this.g_main.drawImage(this.bitorig, 0, this.main_starty[n], this);
        this.g_bitorig = this.bitorig.getGraphics();
    }
    
    private void hlMainArrows(final int n, final boolean b) {
        if (b) {
            this.drawAnimatedArrows(n, 8);
            return;
        }
        if (this.m_sub_expanded[n]) {
            this.drawArrow(2, this.m_arrowxoffset, this.main_starty[n] + this.m_arrowyoffset, this.m_arrowhlcolor);
            return;
        }
        this.drawArrow(1, this.m_arrowxoffset, this.main_starty[n] + this.m_arrowyoffset, this.m_arrowhlcolor);
    }
    
    private synchronized void drawAnimatedArrows(final int n, final int n2) {
        this.g_main.drawImage(this.arErase[n], this.m_arrowxoffset, this.main_starty[n] + this.m_arrowyoffset, this);
        if (this.m_animate_arrows) {
            this.g_main.setColor(this.m_arrow_activecolor);
        }
        else if (this.cur_mainHL == n) {
            this.g_main.setColor(this.m_arrowhlcolor);
        }
        else {
            this.g_main.setColor(this.m_arrowcolor);
        }
        final int n3 = (8 - n2) / 2;
        if (this.m_sub_expanded[n]) {
            if (n2 > 0) {
                int n4 = n3 + this.m_arrowxoffset;
                int n5 = 4 + this.main_starty[n] + this.m_arrowyoffset;
                for (int i = n2; i >= 0; i -= 2) {
                    this.g_main.drawLine(n4, n5, n4 + i, n5);
                    --n5;
                    ++n4;
                }
            }
        }
        else if (n2 > 0) {
            int n6 = n3 + this.m_arrowxoffset;
            int n7 = this.main_starty[n] + this.m_arrowyoffset;
            for (int j = n2; j >= 0; j -= 2) {
                this.g_main.drawLine(n6, n7, n6 + j, n7);
                ++n7;
                ++n6;
            }
        }
    }
    
    private void undoArrowHL(final int n) {
        if (this.m_sub_expanded[n]) {
            this.drawArrow(2, this.m_arrowxoffset, this.main_starty[n] + this.m_arrowyoffset, this.m_arrowcolor);
            return;
        }
        this.drawArrow(1, this.m_arrowxoffset, this.main_starty[n] + this.m_arrowyoffset, this.m_arrowcolor);
    }
    
    private int checkYExpandMouse(final int n, final int n2) {
        for (int i = 0; i < this.main_starty.length; ++i) {
            if (n >= this.main_starty[i] + n2 && n <= this.main_starty[i] + this.m_item_height + n2) {
                return i;
            }
        }
        return -1;
    }
    
    private int checkSubPosition(final int n) {
        for (int i = 0; i < this.sub_id.length; ++i) {
            if (this.m_sub_expanded[this.sub_id[i]] && n > this.sub_starty[i] + this.scroll_y && n < this.sub_starty[i] + this.m_item_height + this.scroll_y) {
                return i;
            }
        }
        return -1;
    }
    
    private void startRetract(final int er_id) {
        if (this.curMain > -1) {
            this.stop();
            if (this.curMain != er_id) {
                this.undoArrowHL(this.curMain);
            }
            if (this.m_animate_arrows) {
                this.drawArrow(2, this.m_arrowxoffset, this.main_starty[er_id] + this.m_arrowyoffset, this.m_arrow_activecolor);
                this.paintIt();
            }
            this.curMain = -1;
        }
        this.er_id = er_id;
        this.animating = true;
        this.go_retract = true;
        this.start();
    }
    
    private void retractItem(final int curMain) {
        final int n = this.num_mainsubs[curMain] * this.bitHeight;
        final int n2 = this.main_starty[curMain] + this.bitHeight;
        if (this.main_starty[curMain] + this.bitHeight * 2 > Math.abs(this.scroll_y - this.d.height)) {
            final int n3 = -(this.main_starty[curMain] + this.bitHeight * 2) + this.d.height;
            if (n3 < this.scroll_y) {
                for (int i = this.scroll_y; i > n3; i -= this.m_jump_size) {
                    this.scroll_y = i;
                    this.paintIt();
                    try {
                        Thread.sleep(this.m_delay);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        final Graphics graphics = this.getGraphics();
        graphics.clipRect(0, this.main_starty[curMain] + this.bitHeight + this.scroll_y, this.rsubWidth, this.d.height);
        final Image image = this.createImage(this.rsubWidth, this.maxExpandHeight - n2);
        image.getGraphics().drawImage(this.main, 0, -n2, this);
        final int n4 = this.main_starty[curMain] + this.bitHeight;
        for (int j = 0; j > -n; j -= this.m_jump_size) {
            graphics.drawImage(image, 0, j + (n4 + this.scroll_y), this);
            try {
                Thread.sleep(this.m_delay);
            }
            catch (InterruptedException ex2) {}
        }
        final int n5 = -n + n4;
        this.g_main.clipRect(0, n4, this.rsubWidth, this.main.getHeight(this) - n4);
        this.g_main.drawImage(image, 0, n5, this);
        this.paintIt();
        for (int k = curMain + 1; k < this.main_starty.length; ++k) {
            this.main_starty[k] -= n;
            for (int l = this.sub_start_index[k]; l < this.sub_start_index[k] + this.num_mainsubs[k]; ++l) {
                this.sub_starty[l] -= n;
            }
        }
        (this.g_main = this.main.getGraphics()).setColor(this.m_bgcolor);
        int n6 = this.main_starty[this.main_starty.length - 1] + this.bitHeight;
        if (this.m_sub_expanded[this.main_starty.length - 1] && this.main_starty.length - 1 != curMain) {
            n6 += this.num_mainsubs[this.main_starty.length - 1] * this.bitHeight;
        }
        if ((this.mainBG != null && this.m_clip_main_bg) || this.endcap != null) {
            this.g_main.fillRect(0, n6 + this.m_additional_clip_height, this.d.width, n);
        }
        else if (this.mainBG == null) {
            this.g_main.fillRect(0, n6, this.d.width, n);
        }
        if (this.curExpandHeight - n >= this.d.height) {
            int scroll_y = -this.main_starty[curMain];
            if (scroll_y < -(this.curExpandHeight - n) + this.d.height) {
                scroll_y = -(this.curExpandHeight - n) + this.d.height;
            }
            if (scroll_y < this.scroll_y) {
                for (int scroll_y2 = this.scroll_y; scroll_y2 > scroll_y; scroll_y2 -= 2) {
                    this.scroll_y = scroll_y2;
                    this.paintIt();
                    try {
                        Thread.sleep(this.m_delay);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
            else {
                for (int scroll_y3 = this.scroll_y; scroll_y3 < scroll_y; scroll_y3 += this.m_jump_size) {
                    this.scroll_y = scroll_y3;
                    this.paintIt();
                    try {
                        Thread.sleep(this.m_delay);
                    }
                    catch (InterruptedException ex4) {}
                }
            }
            this.scroll_y = scroll_y;
            this.paintIt();
        }
        else {
            final int scroll_y4 = 0;
            for (int scroll_y5 = this.scroll_y; scroll_y5 < scroll_y4; scroll_y5 += this.m_jump_size) {
                this.scroll_y = scroll_y5;
                this.paintIt();
                try {
                    Thread.sleep(this.m_delay);
                }
                catch (InterruptedException ex5) {}
            }
            this.scroll_y = scroll_y4;
            this.paintIt();
        }
        this.m_sub_expanded[curMain] = false;
        this.curExpandHeight -= n;
        this.hlMainArrows(curMain, true);
        this.checkHL(this.mx, this.my);
        this.paintIt();
        this.setScrollBar();
        this.curMain = curMain;
    }
    
    private void startExpand(final int er_id) {
        if (this.curMain > -1) {
            this.stop();
            if (this.curMain != er_id) {
                this.undoArrowHL(this.curMain);
            }
            if (this.m_animate_arrows) {
                this.drawArrow(1, this.m_arrowxoffset, this.main_starty[er_id] + this.m_arrowyoffset, this.m_arrow_activecolor);
                this.paintIt();
            }
            this.curMain = -1;
        }
        this.er_id = er_id;
        this.animating = true;
        this.go_expand = true;
        this.start();
    }
    
    private void expandItem(final int curMain) {
        System.gc();
        final int n = this.num_mainsubs[curMain] * this.bitHeight;
        final int n2 = this.main_starty[curMain] + this.bitHeight;
        if (this.curExpandHeight + n >= this.d.height) {
            int scroll_y = -this.main_starty[curMain];
            if (scroll_y < -(this.curExpandHeight + n) + this.d.height) {
                scroll_y = -(this.curExpandHeight + n) + this.d.height;
            }
            if (scroll_y < this.scroll_y) {
                for (int i = this.scroll_y; i > scroll_y; i -= this.m_jump_size) {
                    this.scroll_y = i;
                    this.paintIt();
                    try {
                        Thread.sleep(this.m_delay);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                for (int j = this.scroll_y; j < scroll_y; j += this.m_jump_size) {
                    this.scroll_y = j;
                    this.paintIt();
                    try {
                        Thread.sleep(this.m_delay);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            this.scroll_y = scroll_y;
            this.paintIt();
        }
        final Image image = this.createImage(this.rsubWidth, this.maxExpandHeight - n2);
        final Graphics graphics = image.getGraphics();
        final int n3 = -this.sub_offy[this.sub_start_index[curMain]];
        final int n4 = this.main_starty[curMain] + this.bitHeight;
        final Graphics graphics2 = this.getGraphics();
        graphics2.clipRect(0, this.main_starty[curMain] + this.bitHeight + this.scroll_y, this.rsubWidth, this.d.height);
        graphics.drawImage(this.ts, 0, n3, this);
        graphics.clipRect(0, n, this.rsubWidth, image.getHeight(this) - n);
        graphics.drawImage(this.main, 0, n - n4, this);
        for (int k = -n; k <= 0; k += this.m_jump_size) {
            graphics2.drawImage(image, 0, k + (n4 + this.scroll_y), this);
            try {
                Thread.sleep(this.m_delay);
            }
            catch (InterruptedException ex3) {}
        }
        this.g_main.clipRect(0, n4 - 1, this.rsubWidth, this.main.getHeight(this) - n4 + 1);
        this.g_main.drawImage(image, 0, n4, this);
        this.g_main = this.main.getGraphics();
        this.paintIt();
        for (int l = curMain + 1; l < this.main_starty.length; ++l) {
            this.main_starty[l] += n;
            for (int n5 = this.sub_start_index[l]; n5 < this.sub_start_index[l] + this.num_mainsubs[l]; ++n5) {
                this.sub_starty[n5] += n;
            }
        }
        this.m_sub_expanded[curMain] = true;
        this.curExpandHeight += n;
        this.hlMainArrows(curMain, true);
        if (!this.first_force) {
            this.checkHL(this.mx, this.my);
        }
        else {
            this.first_force = false;
        }
        this.paintIt();
        this.setScrollBar();
        this.curMain = curMain;
    }
    
    private void setScrollBar() {
        if (this.m_hide_sb && this.curExpandHeight <= this.d.height) {
            if (!this.bar_removed) {
                this.remove(this.sb);
                this.bar_removed = true;
            }
        }
        else {
            if (this.bar_removed) {
                this.add(this.sb);
                this.bar_removed = false;
            }
            this.sb.setValues(this.m_lineinc, this.d.height, this.curExpandHeight, Math.abs(this.scroll_y));
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.animating) {
            return true;
        }
        if (n >= this.d.width - this.m_main_rmargin || n <= this.m_main_lmargin) {
            this.mouseMove(n, n2);
            return true;
        }
        final int checkYExpandMouse = this.checkYExpandMouse(n2, this.scroll_y);
        if (checkYExpandMouse != -1 && checkYExpandMouse == this.cur_mainHL) {
            if (this.m_sub_expanded[checkYExpandMouse]) {
                this.startRetract(checkYExpandMouse);
            }
            else if (this.num_mainsubs[checkYExpandMouse] > 0) {
                this.startExpand(checkYExpandMouse);
            }
            this.loadURL(this.m_main_url[checkYExpandMouse]);
            return true;
        }
        this.activateSubURL(n, n2);
        return true;
    }
    
    private void activateSubURL(final int n, final int n2) {
        if (n < this.d.width - this.m_sub_rmargin && n > this.m_sub_lmargin) {
            for (int i = 0; i < this.m_sub_desc.length; ++i) {
                if (this.m_sub_expanded[this.sub_id[i]] && n2 > this.sub_starty[i] + this.scroll_y && n2 < this.sub_starty[i] + this.m_item_height + this.scroll_y && i == this.subHL) {
                    this.loadURL(this.m_sub_url[i]);
                }
            }
        }
    }
    
    private void loadURL(final String s) {
        if (s.equals("-1")) {
            return;
        }
        final int index = s.indexOf("|");
        String s2;
        String substring;
        if (index > 0) {
            if (index < s.length() - 1) {
                s2 = s.substring(0, index);
                substring = s.substring(index + 1);
            }
            else {
                substring = s;
                s2 = this.m_loadwhere;
            }
        }
        else {
            substring = s;
            s2 = this.m_loadwhere;
        }
        if (!substring.equals("-1")) {
            try {
                if (s2.equalsIgnoreCase("javascript")) {
                    JSObject.getWindow((Applet)this).eval(substring);
                    return;
                }
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), substring), s2);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    private void DrawMenu() {
        int n = 0;
        this.drawSubBG();
        for (int i = 0; i < this.m_sub_desc.length; ++i) {
            this.sub_starty[i] = n;
            this.sub_offy[i] = n;
            if (this.m_sub_boxcolor != null) {
                this.g_ts.setColor(this.m_sub_boxcolor);
                this.g_ts.fillRect(this.m_sub_lmargin, this.sub_starty[i], this.d.width - this.m_sub_rmargin - this.m_sub_lmargin, this.m_item_height);
            }
            if (this.m_sub_outcolor != null) {
                this.g_ts.setColor(this.m_sub_outcolor);
                this.g_ts.drawRect(this.m_sub_lmargin, this.sub_starty[i], this.d.width - this.m_sub_rmargin - this.m_sub_lmargin - 1, this.m_item_height - 1);
            }
            int n2 = this.m_sub_lmargin + this.m_sub_text_indent;
            if (this.sub_icon_id[i] > -1 && this.i_pic[this.sub_icon_id[i]] != null) {
                this.g_ts.drawImage(this.i_pic[this.sub_icon_id[i]], this.m_sub_lmargin + this.m_sub_icon_indent, this.sub_starty[i] + this.m_sub_icon_voffset + this.m_item_height / 2 - this.icon_d[this.sub_icon_id[i]].height / 2, this);
                n2 = n2 + this.icon_d[this.sub_icon_id[i]].width + this.m_sub_icon_indent;
            }
            this.g_ts.setColor(this.m_sub_textcolor);
            final int n3 = n + this.m_item_height - this.subfm.getDescent() - (this.m_item_height - this.subfm.getHeight()) / 2;
            this.g_ts.drawString(this.m_sub_desc[i], n2, n3);
            if (this.m_unsubtext) {
                this.g_ts.drawLine(n2 + 1, n3 + 1, n2 + this.subfm.stringWidth(this.m_sub_desc[i]) - 1, n3 + 1);
            }
            n += this.bitHeight;
        }
        int n4 = 0;
        this.m_arrowxoffset += this.d.width - this.m_main_rmargin - 20;
        this.m_arrowyoffset += this.m_item_height / 2 - 3;
        this.g_main.setFont(this.m_main_font);
        this.drawMainBG();
        for (int j = 0; j < this.m_main_desc.length; ++j) {
            this.main_starty[j] = n4;
            if (this.m_main_boxcolor != null) {
                this.g_main.setColor(this.m_main_boxcolor);
                this.g_main.fillRect(this.m_main_lmargin, this.main_starty[j], this.d.width - this.m_main_lmargin - this.m_main_rmargin, this.m_item_height);
            }
            if (this.m_main_outcolor != null) {
                this.g_main.setColor(this.m_main_outcolor);
                this.g_main.drawRect(this.m_main_lmargin, this.main_starty[j], this.d.width - this.m_main_lmargin - this.m_main_rmargin - 1, this.m_item_height - 1);
            }
            int n5 = this.m_main_lmargin + this.m_main_text_indent;
            if (this.main_icon_id[j] > -1 && this.i_pic[this.main_icon_id[j]] != null) {
                this.g_main.drawImage(this.i_pic[this.main_icon_id[j]], this.m_main_lmargin + this.m_main_icon_indent, this.main_starty[j] + this.m_main_icon_voffset + this.m_item_height / 2 - this.icon_d[this.main_icon_id[j]].height / 2, this);
                n5 = n5 + this.icon_d[this.main_icon_id[j]].width + this.m_main_icon_indent;
            }
            this.g_main.setColor(this.m_main_textcolor);
            final int n6 = this.main_starty[j] + this.m_item_height - this.mainfm.getDescent() - (this.m_item_height - this.mainfm.getHeight()) / 2 + this.m_maintext_yoffset;
            this.g_main.drawString(this.m_main_desc[j], n5, n6);
            if (this.m_unmaintext) {
                this.g_main.drawLine(n5 + 1, n6 + 1, n5 + this.mainfm.stringWidth(this.m_main_desc[j]) - 1, n6 + 1);
            }
            this.arErase[j] = this.createImage(9, 5);
            (this.g_arErase = this.arErase[j].getGraphics()).drawImage(this.main, -this.m_arrowxoffset, -(this.main_starty[j] + this.m_arrowyoffset), this);
            this.drawArrow(1, this.m_arrowxoffset, this.main_starty[j] + this.m_arrowyoffset, this.m_arrowcolor);
            n4 += this.bitHeight;
            int n7 = 0;
            for (int k = this.sub_start_index[j]; k < this.sub_start_index[j] + this.num_mainsubs[j]; ++k) {
                this.sub_starty[k] = n7 + (this.sub_id[k] + 1) * this.bitHeight;
                n7 += this.bitHeight;
            }
        }
    }
    
    private void drawMainBG() {
        if (this.mainBG != null) {
            int main_lmargin = 0;
            int rmainWidth = this.rmainWidth;
            final int n = 0;
            int n2 = this.d.height * 2;
            if (!this.m_tilebgin_margins) {
                main_lmargin = this.m_main_lmargin;
                rmainWidth = this.d.width - this.m_main_lmargin - this.m_main_rmargin;
            }
            if (this.m_clip_main_bg) {
                n2 = this.m_main_desc.length * this.bitHeight + this.m_additional_clip_height;
            }
            if (this.m_main_desc.length * this.bitHeight > n2) {
                n2 = this.m_main_desc.length * this.bitHeight;
            }
            this.g_main.clipRect(main_lmargin, n, rmainWidth, n2);
            if (this.m_tile_mainbg) {
                int n3 = 0;
                int n4 = 0;
                final int width = this.mainBG.getWidth(this);
                final int height = this.mainBG.getHeight(this);
                final int n5 = this.d.height * 2;
                while (true) {
                    this.g_main.drawImage(this.mainBG, n3, n4, this);
                    n3 += width;
                    if (n3 > this.rmainWidth) {
                        n3 = 0;
                        n4 += height;
                        if (this.m_clip_main_bg) {
                            if (n4 > this.m_main_desc.length * this.bitHeight + this.m_additional_clip_height) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (n4 > n5) {
                                break;
                            }
                            continue;
                        }
                    }
                }
            }
            else {
                this.g_main.drawImage(this.mainBG, 0, 0, this);
            }
            (this.g_main = this.main.getGraphics()).setFont(this.m_main_font);
            if (this.endcap != null) {
                this.g_main.drawImage(this.endcap, 0, this.m_main_desc.length * this.bitHeight, this);
            }
            this.sb_bg = this.createImage(this.m_sbwidth, this.d.height);
            this.sb_bg.getGraphics().drawImage(this.main, -this.d.width, 0, this);
        }
    }
    
    private void drawSubBG() {
        if (this.subBG != null) {
            if (!this.m_tilebgin_margins) {
                this.g_ts.clipRect(this.m_sub_lmargin, 0, this.d.width - this.m_sub_rmargin - this.m_sub_lmargin, this.ts.getHeight(this));
            }
            if (this.m_tile_subbg) {
                int n = 0;
                int n2 = 0;
                final int width = this.subBG.getWidth(this);
                final int height = this.subBG.getHeight(this);
                final int rsubWidth = this.rsubWidth;
                final int height2 = this.ts.getHeight(this);
                while (true) {
                    this.g_ts.drawImage(this.subBG, n, n2, this);
                    n += width;
                    if (n > rsubWidth) {
                        n = 0;
                        n2 += height;
                        if (n2 > height2) {
                            break;
                        }
                        continue;
                    }
                }
            }
            else {
                this.g_ts.drawImage(this.subBG, 0, 0, this);
            }
            if (!this.m_tilebgin_margins) {
                (this.g_ts = this.ts.getGraphics()).setFont(this.m_sub_font);
            }
        }
    }
    
    private void drawArrow(final int n, final int n2, final int n3, final Color color) {
        if (n == 1) {
            this.g_main.setColor(color);
            int n4 = n2;
            int n5 = n3;
            for (int i = 8; i >= 0; i -= 2) {
                this.g_main.drawLine(n4, n5, n4 + i, n5);
                ++n5;
                ++n4;
            }
        }
        if (n == 2) {
            this.g_main.setColor(color);
            int n6 = n2;
            int n7 = n3 + 4;
            for (int j = 8; j >= 0; j -= 2) {
                this.g_main.drawLine(n6, n7, n6 + j, n7);
                --n7;
                ++n6;
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.sb) {
            this.scrollIt(event.id, true);
        }
        return super.handleEvent(event);
    }
    
    public void scrollIt(final int n, final boolean b) {
        if (n == 602 || n == 601 || n == 605 || n == 604 || n == 603) {
            this.scroll_y = -this.sb.getValue();
            this.paintIt();
        }
    }
    
    public void start() {
        if (!this.tag) {
            return;
        }
        if (this.trun == null) {
            (this.trun = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.trun != null) {
            this.trun.stop();
            this.trun = null;
        }
    }
    
    public void destroy() {
        if (this.trun != null) {
            this.trun.stop();
            this.trun = null;
        }
    }
    
    private void loadImages() {
        for (int i = this.i_pic_break; i < this.num_icons; ++i) {
            if ((this.i_pic[i] = this.trackReturn(this.m_icon_file[i])) != null) {
                this.icon_d[i] = new Dimension(this.i_pic[i].getWidth(this), this.i_pic[i].getHeight(this));
            }
            this.i_pic_break = i;
        }
        for (int j = this.s_pic_break; j < this.num_icons; ++j) {
            if ((this.s_pic[j] = this.trackReturn(this.m_icon_switchfile[j])) == null) {
                this.s_pic[j] = this.i_pic[j];
            }
            this.s_pic_break = j;
        }
        this.mainBG = this.trackReturn(this.m_main_bgfile);
        if (this.mainBG != null) {
            this.m_main_boxcolor = null;
        }
        this.subBG = this.trackReturn(this.m_sub_bgfile);
        if (this.subBG != null) {
            this.m_sub_boxcolor = null;
        }
        if ((this.endcap = this.trackReturn(this.m_endcap_image)) != null) {
            if (this.mainBG != null) {
                this.m_clip_main_bg = true;
            }
            this.m_additional_clip_height = this.endcap.getHeight(this);
        }
    }
    
    private void firstRun() {
        this.setBackground(this.m_bgcolor);
        this.ts = this.createImage(this.d.width + this.m_sbwidth, this.d.height);
        (this.g_ts = this.ts.getGraphics()).setColor(this.m_bgcolor);
        this.g_ts.fillRect(0, 0, this.d.width + this.m_sbwidth, this.d.height);
        this.g_ts.setFont(this.m_load_font);
        final FontMetrics fontMetrics = this.g_ts.getFontMetrics(this.m_load_font);
        this.g_ts.setColor(this.m_load_msgcolor);
        this.g_ts.drawString(this.m_load_msg, (this.d.width + this.m_sbwidth) / 2 - fontMetrics.stringWidth(this.m_load_msg) / 2, this.d.height / 2 + fontMetrics.getHeight() / 2);
        this.getGraphics().drawImage(this.ts, 0, 0, this);
        this.showStatus(this.m_load_msg);
        this.loadImages();
        this.subfm = this.getFontMetrics(this.m_sub_font);
        this.mainfm = this.getFontMetrics(this.m_main_font);
        if (!this.inputHeightFixed) {
            final int height = this.subfm.getHeight();
            final int height2 = this.mainfm.getHeight();
            if (height > height2) {
                this.m_item_height = height;
            }
            else {
                this.m_item_height = height2;
            }
        }
        this.bitHeight = this.m_item_height;
        this.m_lineinc = this.bitHeight;
        System.out.println("Item Height = " + this.bitHeight);
        this.offHeight = this.bitHeight * this.m_sub_desc.length;
        System.out.println("Total Sub Menu Hieght = " + this.offHeight);
        this.bitoff = this.createImage(this.d.width, this.bitHeight);
        this.g_bitoff = this.bitoff.getGraphics();
        this.bitorig = this.createImage(this.d.width, this.bitHeight);
        this.g_bitorig = this.bitorig.getGraphics();
        this.bitsub = this.createImage(this.d.width, this.bitHeight);
        this.g_bitsub = this.bitsub.getGraphics();
        this.rsubWidth = this.d.width;
        if (this.m_tilebgin_margins) {
            this.rsubWidth += this.m_sbwidth;
        }
        if (this.offHeight < 1) {
            this.offHeight = 1;
        }
        this.ts = this.createImage(this.rsubWidth, this.offHeight);
        (this.g_ts = this.ts.getGraphics()).setFont(this.m_sub_font);
        this.g_ts.setColor(this.m_bgcolor);
        this.g_ts.fillRect(0, 0, this.rsubWidth, this.offHeight);
        this.bottom_height = this.d.height - this.bitHeight;
        this.curExpandHeight = this.bitHeight * this.m_main_desc.length;
        this.maxExpandHeight = this.offHeight + this.curExpandHeight + this.bottom_height;
        this.rmainWidth = this.d.width;
        if (this.m_tilebgin_margins) {
            this.rmainWidth += this.m_sbwidth;
        }
        this.main = this.createImage(this.rmainWidth, this.maxExpandHeight);
        (this.g_main = this.main.getGraphics()).setFont(this.m_main_font);
        this.g_main.setColor(this.m_bgcolor);
        this.g_main.fillRect(0, 0, this.rmainWidth, this.maxExpandHeight);
        this.DrawMenu();
        this.initSB();
        if (this.m_startopen_index > -1) {
            this.first_force = true;
            this.first_run = false;
            this.paintIt();
            this.curMain = -1;
            this.expandItem(this.m_startopen_index);
            return;
        }
        this.setScrollBar();
    }
    
    public void run() {
        if (this.first_run) {
            this.firstRun();
            this.first_run = false;
            if (this.mouseIn) {
                this.showStatus(this.m_onsbtext);
            }
            else {
                this.showStatus(this.m_offsbtext);
            }
            this.paintIt();
        }
        if (this.er_id > -1) {
            if (this.go_expand) {
                this.expandItem(this.er_id);
            }
            else if (this.go_retract) {
                this.retractItem(this.er_id);
            }
            this.er_id = -1;
            this.animating = false;
            this.go_expand = false;
            this.go_retract = false;
        }
        while (true) {
            if (this.m_animate_arrows) {
                if (this.curMain > -1) {
                    this.hlMainArrows(this.curMain, true);
                    this.paintIt();
                    try {
                        Thread.sleep(750L);
                    }
                    catch (InterruptedException ex) {}
                }
                while (this.curMain > -1) {
                    for (int i = 6; i > 0; i -= 2) {
                        this.drawAnimatedArrows(this.curMain, i);
                        this.paintIt();
                        try {
                            Thread.sleep(25L);
                        }
                        catch (InterruptedException ex2) {}
                    }
                    this.drawAnimatedArrows(this.curMain, 0);
                    this.paintIt();
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex3) {}
                    for (int j = 2; j < 8; j += 2) {
                        this.drawAnimatedArrows(this.curMain, j);
                        this.paintIt();
                        try {
                            Thread.sleep(25L);
                        }
                        catch (InterruptedException ex4) {}
                    }
                    this.hlMainArrows(this.curMain, true);
                    this.paintIt();
                    try {
                        Thread.sleep(400L);
                    }
                    catch (InterruptedException ex5) {}
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex6) {}
                this.stop();
            }
            else {
                this.stop();
            }
        }
    }
    
    public Image trackReturn(final String s) {
        if (s == null) {
            return null;
        }
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
    
    public stmenu() {
        this.mouseIn = false;
        this.animating = false;
        this.init_sb = false;
        this.syncarrow = false;
        this.m_showhand = false;
        this.er_id = -1;
        this.go_expand = false;
        this.go_retract = false;
        this.bar_removed = false;
        this.first_force = false;
        this.tag = false;
        this.subHL = -1;
        this.inputHeightFixed = false;
        this.curSub = -1;
        this.m_tile_bg_insb = true;
        this.m_startopen_index = -1;
        this.m_hide_sb = false;
        this.m_tilebgin_margins = false;
        this.m_unmaintext = false;
        this.m_unsubtext = false;
        this.m_clip_main_bg = false;
        this.m_loadwhere = "_self";
        this.m_main_texthlcolor = Color.red;
        this.m_main_textcolor = Color.white;
        this.m_arrow_activecolor = Color.yellow;
        this.m_arrowhlcolor = Color.yellow;
        this.m_arrowcolor = Color.lightGray;
        this.m_sub_textcolor = Color.black;
        this.m_sub_hltextcolor = Color.green;
        this.m_onsbtext = "OpenCube - SmartTree Menu";
        this.m_offsbtext = "Java by OpenCube";
        this.m_bgcolor = Color.black;
        this.m_main_font = new Font("Helvetica", 1, 10);
        this.m_sub_font = new Font("Helvetica", 0, 11);
        this.m_main_lmargin = 5;
        this.m_main_rmargin = 5;
        this.m_main_icon_indent = 4;
        this.m_main_text_indent = 4;
        this.m_animate_arrows = true;
        this.m_sub_lmargin = 10;
        this.m_sub_rmargin = 7;
        this.m_sub_text_indent = 5;
        this.m_sub_icon_indent = 4;
        this.m_jump_size = 2;
        this.m_delay = 10;
        this.m_item_height = 15;
        this.m_endcap_image = "-1";
        this.m_load_msg = "Loading Images...";
        this.m_load_msgcolor = Color.white;
        this.m_load_font = new Font("Helvetica", 0, 12);
        this.m_sbwidth = 12;
        this.m_si = 2;
        this.m_ac = Color.black;
        this.m_hc = Color.yellow;
        this.m_bgc = Color.white;
        this.m_mac = Color.lightGray;
        this.m_unact = Color.gray;
        this.m_machl = Color.yellow;
        this.m_sbflat = true;
        this.m_indent_bubble = false;
        this.m_tile_mainbg = true;
        this.m_tile_subbg = true;
        this.m_sub_bgfile = "-1";
        this.m_main_bgfile = "-1";
        this.cur_mainHL = -1;
        this.first_run = true;
        this.mouse_in = false;
    }
}
