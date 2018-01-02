import java.util.Stack;
import java.util.Vector;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class iim extends Applet implements Runnable
{
    Thread trun;
    boolean mouse_in;
    boolean bg_stat;
    int break_stand;
    int break_hl;
    int alignment;
    int maxiconwidth;
    int itadjust;
    int bitcenter;
    String cur_img_link;
    URL goURL;
    Dimension d;
    Font font;
    FontMetrics fm;
    Image[] suboff;
    Graphics[] g_suboff;
    Image ts;
    Graphics g_ts;
    Image bg;
    Image[] subsave;
    int level_max;
    int menux;
    int menuy;
    int menuwidth;
    int menuheight;
    int bitheight;
    int on_sub_num;
    int break_picload;
    int break_pictype;
    int break_bgload;
    int last_sub;
    int[] cur_itadjust;
    int[] cur_alignment;
    int[] cur_menuwidth;
    int[] def_menuloc;
    int lasticoid;
    private String m_load_msg;
    private Color m_load_msgcolor;
    Image bitoff;
    Graphics g_bitoff;
    private String m_italign;
    boolean sub_highlighted;
    boolean in_sub;
    boolean tag;
    boolean first_run;
    boolean rendering_images;
    Image[] i_pic;
    Image[] s_pic;
    int num_icons;
    private String[] m_icon_file;
    private String[] m_icon_switch_file;
    private String[] mc_sub_bgfile;
    private boolean[] mc_bgfile_stat;
    private Image[] mc_subbg;
    private Image m_subbg;
    private String m_sub_bgfile;
    private boolean m_bgfile_stat;
    private int m_it_space;
    private int m_forced_height;
    boolean[] gif_tile;
    private String[] m_cur_image_desturl;
    private String m_loadwhere;
    private Color m_page_bgcolor;
    private String m_bgimage_file;
    private Color m_outline_color;
    private Color m_menu_color;
    private Color m_menu_textcolor;
    private Color m_menu_hloutcolor;
    private Color m_menu_hltextcolor;
    private Color m_menu_bocolor;
    private Color[] m_cur_outline_color;
    private Color[] m_cur_menu_color;
    private Color[] m_cur_menu_textcolor;
    private Color[] m_cur_menu_hloutcolor;
    private Color[] m_cur_menu_hltextcolor;
    private Color[] m_cur_menu_bocolor;
    private int m_lrmargin;
    private String m_onsbtext;
    private String m_offsbtext;
    private String[] m_imagefile;
    private String[] m_switchfile;
    private int[] m_pic_xcord;
    private int[] m_pic_ycord;
    private int[] m_menu_xcord;
    private int[] m_menu_ycord;
    private int m_bgimage_x;
    private int m_bgimage_y;
    int m_max_subs;
    boolean m_center_text;
    int draw_spec_hl;
    boolean[] no_subs;
    int last_drawn;
    int num_images;
    int[] pic_width;
    int[] pic_height;
    int[] max_string_width;
    boolean[] image_loaded;
    String[] subs;
    String[] p_subs;
    int[] ItoM;
    int actual_level;
    Image[] s_picture;
    Image[] h_picture;
    MediaTracker m_media_track;
    int cur_sub_point;
    int cur_num_subs;
    int cur_level;
    int max_levels;
    boolean[] simg_status;
    boolean[] hlimg_status;
    int[] menu_coords;
    String[] cur_link;
    
    public void UndoHP(final int n) {
        this.getGraphics();
        this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
        if (this.bg_stat) {
            this.g_ts.drawImage(this.bg, this.m_bgimage_x, this.m_bgimage_y, this);
        }
        for (int i = 0; i < this.num_images; ++i) {
            if (this.simg_status[i]) {
                this.g_ts.drawImage(this.s_picture[i], this.m_pic_xcord[i], this.m_pic_ycord[i], this);
            }
        }
        this.paintIt();
    }
    
    public void UndoSubHighlight(final Graphics graphics, final int n) {
        this.sub_highlighted = false;
        this.last_sub = -1;
        if (this.m_cur_menu_hloutcolor[this.cur_level] != null) {
            this.g_suboff[this.cur_level].setColor(this.m_cur_menu_color[this.cur_level]);
            this.g_suboff[this.cur_level].drawRect(0, 0, this.suboff[this.cur_level].getWidth(this), this.suboff[this.cur_level].getHeight(this));
            this.g_suboff[this.cur_level].drawImage(this.subsave[this.cur_level], 0, 0, this);
        }
        this.highlightText(this.m_cur_menu_textcolor[this.cur_level], n, false);
    }
    
    public void start() {
        if (!this.tag) {
            return;
        }
        if (this.first_run) {
            (this.trun = new Thread(this)).start();
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouse_in = false;
        if (this.last_drawn != -1) {
            if (this.cur_level > 0) {
                this.UndoSpecifics(0, this.cur_level, false);
                this.getGraphics().drawImage(this.ts, 0, 0, this);
            }
            else {
                this.UndoHP(0);
            }
            this.last_drawn = -1;
        }
        if (this.cur_level > -1) {
            this.cur_link[this.cur_level] = "-1";
        }
        this.cur_level = -1;
        this.sub_highlighted = false;
        this.last_sub = -1;
        if (this.rendering_images) {
            this.showStatus("Loading Menu Images...");
        }
        else {
            this.showStatus(this.m_offsbtext);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.last_drawn != -1) {
            for (int i = this.cur_level; i >= 0; --i) {
                final int n3 = i * 7;
                if (n > this.menu_coords[n3] && n < this.menu_coords[n3] + this.menu_coords[n3 + 2] && n2 > this.menu_coords[n3 + 1] && n2 < this.menu_coords[n3 + 1] + this.menu_coords[n3 + 3]) {
                    this.cur_num_subs = this.menu_coords[n3 + 4];
                    this.actual_level = i;
                    this.MouseInSub(n2 - this.menu_coords[n3 + 1], i);
                    return true;
                }
            }
            this.actual_level = -1;
        }
        if (this.sub_highlighted) {
            this.UndoSubHighlight(this.getGraphics(), this.last_sub);
            this.cur_link[this.cur_level] = "-1";
            this.paintIt();
        }
        this.on_sub_num = -1;
        int j = this.num_images - 1;
        while (j >= 0) {
            if (n2 > this.m_pic_ycord[j] && n2 < this.m_pic_ycord[j] + this.pic_height[j] && n > this.m_pic_xcord[j] && n < this.m_pic_xcord[j] + this.pic_width[j]) {
                if (j != this.last_drawn) {
                    if (this.last_drawn != -1) {
                        if (n2 > this.m_pic_ycord[this.last_drawn] && n2 < this.m_pic_ycord[this.last_drawn] + this.pic_height[this.last_drawn] && n > this.m_pic_xcord[this.last_drawn] && n < this.m_pic_xcord[this.last_drawn] + this.pic_width[this.last_drawn]) {
                            return true;
                        }
                        if (this.cur_level > 0) {
                            this.UndoSpecifics(0, this.cur_level, false);
                            this.cur_level = 0;
                        }
                        else {
                            this.UndoHP(0);
                        }
                    }
                    this.last_drawn = j;
                    this.cur_img_link = this.m_cur_image_desturl[this.last_drawn];
                    this.cur_level = -1;
                    this.HighlightPic(j);
                    return true;
                }
                if (!this.sub_highlighted && this.cur_level > -1) {
                    this.cur_link[this.cur_level] = this.m_cur_image_desturl[this.last_drawn];
                }
                return true;
            }
            else {
                --j;
            }
        }
        if (this.last_drawn != -1 && this.no_subs[this.last_drawn]) {
            this.UndoHP(this.last_drawn);
            this.last_drawn = -1;
            this.cur_level = -1;
        }
        return true;
    }
    
    public void FirstRun(final Graphics graphics) {
        this.rendering_images = true;
        this.showStatus("Loading Menu Images...");
        this.g_ts.setColor(this.m_page_bgcolor);
        this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
        this.g_ts.setFont(new Font("Helvetica", 0, 11));
        final FontMetrics fontMetrics = this.g_ts.getFontMetrics();
        this.g_ts.setColor(this.m_load_msgcolor);
        this.g_ts.drawString(this.m_load_msg, this.d.width / 2 - fontMetrics.stringWidth(this.m_load_msg) / 2, this.d.height / 2 + fontMetrics.getHeight() / 2);
        this.getGraphics().drawImage(this.ts, 0, 0, this);
        for (int i = this.break_pictype; i < this.num_icons; ++i) {
            this.i_pic[i] = this.trackReturn(this.m_icon_file[i]);
            this.break_pictype = i;
        }
        for (int j = this.break_picload; j < this.num_icons; ++j) {
            if ((this.s_pic[j] = this.trackReturn(this.m_icon_switch_file[j])) == null) {
                this.s_pic[j] = this.i_pic[j];
            }
            this.break_picload = j;
        }
        if ((this.m_subbg = this.trackReturn(this.m_sub_bgfile)) != null) {
            this.m_bgfile_stat = true;
        }
        for (int k = this.break_bgload; k < this.level_max; ++k) {
            if ((this.mc_subbg[k] = this.trackReturn(this.mc_sub_bgfile[k])) != null) {
                this.mc_bgfile_stat[k] = true;
            }
            this.break_bgload = k;
        }
        for (int l = this.break_stand; l < this.num_images; ++l) {
            if ((this.s_picture[l] = this.trackReturn(this.m_imagefile[l])) != null) {
                if (l == 0) {
                    this.g_ts.setColor(this.m_page_bgcolor);
                    this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
                }
                this.simg_status[l] = true;
                this.g_ts.drawImage(this.s_picture[l], this.m_pic_xcord[l], this.m_pic_ycord[l], this);
                for (int n = 0; n <= this.cur_level; ++n) {
                    this.g_ts.drawImage(this.suboff[n], this.menu_coords[n * 7], this.menu_coords[n * 7 + 1], this);
                }
                this.getGraphics().drawImage(this.ts, 0, 0, this);
                this.pic_width[l] = this.s_picture[l].getWidth(this);
                this.pic_height[l] = this.s_picture[l].getHeight(this);
            }
            this.break_stand = l;
        }
        for (int break_hl = this.break_hl; break_hl < this.num_images; ++break_hl) {
            if ((this.h_picture[break_hl] = this.trackReturn(this.m_switchfile[break_hl])) != null) {
                this.hlimg_status[break_hl] = true;
                if (this.last_drawn != -1) {
                    if (this.last_drawn == break_hl) {
                        this.g_ts.drawImage(this.h_picture[break_hl], this.m_pic_xcord[break_hl], this.m_pic_ycord[break_hl], this);
                    }
                    for (int n2 = 0; n2 <= this.cur_level; ++n2) {
                        this.g_ts.drawImage(this.suboff[n2], this.menu_coords[n2 * 7], this.menu_coords[n2 * 7 + 1], this);
                    }
                    this.getGraphics().drawImage(this.ts, 0, 0, this);
                }
            }
            else {
                this.h_picture[break_hl] = this.s_picture[break_hl];
            }
            this.break_hl = break_hl;
        }
        if ((this.bg = this.trackReturn(this.m_bgimage_file)) != null) {
            this.bg_stat = true;
            this.UndoSpecifics(0, this.cur_level, true);
            for (int n3 = 0; n3 <= this.cur_level; ++n3) {
                this.g_ts.drawImage(this.suboff[n3], this.menu_coords[n3 * 7], this.menu_coords[n3 * 7 + 1], this);
            }
            this.getGraphics().drawImage(this.ts, 0, 0, this);
        }
        if (this.mouse_in) {
            this.showStatus(this.m_onsbtext);
        }
        else {
            this.showStatus(this.m_offsbtext);
        }
        this.rendering_images = false;
    }
    
    public void paintIt() {
        final Graphics graphics = this.getGraphics();
        if (!this.tag) {
            graphics.setColor(Color.black);
            graphics.drawString(this.m_onsbtext, 5, 30);
            return;
        }
        graphics.drawImage(this.ts, 0, 0, this);
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
    
    public void HighlightPic(final int draw_spec_hl) {
        int n = 0;
        int n2 = 0;
        if (this.no_subs[draw_spec_hl]) {
            if (this.hlimg_status[draw_spec_hl]) {
                this.draw_spec_hl = -1;
                this.g_ts.drawImage(this.h_picture[draw_spec_hl], this.m_pic_xcord[draw_spec_hl], this.m_pic_ycord[draw_spec_hl], this);
            }
            else {
                this.draw_spec_hl = draw_spec_hl;
            }
            this.paintIt();
            return;
        }
        final int cur_num_subs = new Integer(this.p_subs[this.ItoM[draw_spec_hl] + 3]) - new Integer(this.p_subs[this.ItoM[draw_spec_hl] + 2]) + 1;
        final int cur_sub_point = new Integer(this.p_subs[this.ItoM[draw_spec_hl] + 2]) * 4;
        this.alignment = new Integer(this.p_subs[this.ItoM[draw_spec_hl] + 1]);
        this.cur_alignment[0] = this.alignment;
        this.cur_sub_point = cur_sub_point;
        this.cur_num_subs = cur_num_subs;
        final int[] intValues = occcord.getIntValues(this.p_subs[this.ItoM[draw_spec_hl]], ",", 2);
        if (intValues != null) {
            n = intValues[0];
            n2 = intValues[1];
        }
        this.menuwidth = new Integer(this.p_subs[this.ItoM[draw_spec_hl] + 4]);
        this.menuheight = cur_num_subs * this.bitheight + 1;
        this.maxiconwidth = 0;
        for (int i = 0; i < cur_num_subs; ++i) {
            final int int1 = Integer.parseInt(this.subs[cur_sub_point + i * 4 + 2]);
            if (int1 > -1 && this.i_pic[int1] != null) {
                final int width = this.i_pic[int1].getWidth(this);
                if (width > this.maxiconwidth) {
                    this.maxiconwidth = width;
                }
            }
        }
        this.menuwidth = this.menuwidth + this.maxiconwidth + this.m_it_space + 1;
        this.cur_menuwidth[0] = this.menuwidth;
        if (n == -1 && n2 == -2) {
            final int n3 = this.d.width - (this.m_pic_xcord[draw_spec_hl] + this.s_picture[draw_spec_hl].getWidth(this) / 2 + this.menuwidth);
            if (n3 < 0) {
                this.menux = this.m_pic_xcord[draw_spec_hl] + this.s_picture[draw_spec_hl].getWidth(this) / 2 + (n3 - 1);
            }
            else {
                this.menux = this.m_pic_xcord[draw_spec_hl] + this.s_picture[draw_spec_hl].getWidth(this) / 2;
            }
            this.menuy = this.m_pic_ycord[draw_spec_hl] + (this.s_picture[draw_spec_hl].getHeight(this) - 2);
        }
        else {
            this.menux = this.m_pic_xcord[draw_spec_hl] + n;
            this.menuy = this.m_pic_ycord[draw_spec_hl] + n2;
        }
        this.menu_coords[0] = this.menux;
        this.menu_coords[1] = this.menuy;
        this.menu_coords[2] = this.menuwidth;
        this.menu_coords[3] = this.menuheight;
        this.menu_coords[4] = cur_num_subs;
        this.menu_coords[5] = draw_spec_hl;
        this.menu_coords[6] = this.cur_sub_point;
        if (this.hlimg_status[draw_spec_hl]) {
            this.g_ts.drawImage(this.h_picture[draw_spec_hl], this.m_pic_xcord[draw_spec_hl], this.m_pic_ycord[draw_spec_hl], this);
        }
        this.suboff[0] = this.createImage(this.menuwidth, this.menuheight);
        (this.g_suboff[0] = this.suboff[0].getGraphics()).setFont(this.font);
        this.g_suboff[0].setColor(this.m_cur_menu_color[0]);
        this.g_suboff[0].fillRect(0, 0, this.menuwidth, this.menuheight);
        if (this.mc_bgfile_stat[0]) {
            int n4 = 0;
            int n5 = 0;
            final int width2 = this.mc_subbg[0].getWidth(this);
            final int height = this.mc_subbg[0].getHeight(this);
            if (this.gif_tile[0]) {
                n5 = -1;
            }
            while (true) {
                this.g_suboff[0].drawImage(this.mc_subbg[0], n4, n5, this);
                n4 += width2;
                if (n4 > this.menuwidth + 1) {
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
        if (this.m_cur_menu_bocolor[0] != null) {
            this.g_suboff[0].setColor(this.m_cur_menu_bocolor[0]);
            this.g_suboff[0].drawRect(0, 0, this.menuwidth - 1, this.menuheight - 1);
        }
        int lrmargin = this.m_lrmargin;
        for (int j = 0; j < cur_num_subs; ++j) {
            final int int2 = Integer.parseInt(this.subs[cur_sub_point + j * 4 + 2]);
            if (int2 > -1 && this.i_pic[int2] != null) {
                final int width3 = this.i_pic[int2].getWidth(this);
                if (this.alignment == 2 || this.alignment == 3) {
                    lrmargin = this.menuwidth - this.m_lrmargin - width3;
                }
                this.g_suboff[0].drawImage(this.i_pic[int2], lrmargin, this.bitheight * j + this.bitheight / 2 - this.i_pic[int2].getHeight(this) / 2, this);
            }
        }
        this.def_menuloc[0] = lrmargin;
        this.itadjust = 0;
        if (this.maxiconwidth > 0) {
            this.itadjust = this.maxiconwidth + this.m_it_space;
        }
        this.cur_itadjust[0] = this.itadjust;
        for (int k = 0; k < cur_num_subs; ++k) {
            if (this.m_cur_outline_color[0] != null) {
                this.g_suboff[0].setColor(this.m_cur_outline_color[0]);
                this.g_suboff[0].drawRect(0, this.bitheight * k, this.menuwidth, this.bitheight);
            }
            this.g_suboff[0].setColor(this.m_cur_menu_textcolor[0]);
            if (this.alignment == 0) {
                this.g_suboff[0].drawString(this.subs[cur_sub_point + k * 4 + 1], this.m_lrmargin + this.itadjust, this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 1) {
                this.g_suboff[0].drawString(this.subs[cur_sub_point + k * 4 + 1], this.menuwidth - (this.fm.stringWidth(this.subs[cur_sub_point + k * 4 + 1]) + this.m_lrmargin), this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 2) {
                this.g_suboff[0].drawString(this.subs[cur_sub_point + k * 4 + 1], this.m_lrmargin, this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 3) {
                this.g_suboff[0].drawString(this.subs[cur_sub_point + k * 4 + 1], this.menuwidth - (this.fm.stringWidth(this.subs[cur_sub_point + k * 4 + 1]) + this.itadjust + this.m_lrmargin), this.bitheight * k + this.bitcenter);
            }
        }
        this.g_ts.drawImage(this.suboff[0], this.menux, this.menuy, this);
        this.paintIt();
        if (this.m_cur_menu_hloutcolor[0] != null) {
            this.subsave[0] = this.createImage(this.suboff[0].getWidth(this), this.suboff[0].getHeight(this));
            this.subsave[0].getGraphics().drawImage(this.suboff[0], 0, 0, this);
        }
        this.cur_level = 0;
    }
    
    public void highlightText(final Color color, final int n, final boolean b) {
        final int n2 = n * this.bitheight;
        this.bitoff = this.createImage(this.cur_menuwidth[this.cur_level], this.bitheight + 1);
        (this.g_bitoff = this.bitoff.getGraphics()).setFont(this.font);
        this.g_bitoff.drawImage(this.suboff[this.cur_level], 0, -n2, this);
        if (b && this.m_cur_menu_hloutcolor[this.cur_level] != null) {
            this.g_bitoff.setColor(this.m_cur_menu_hloutcolor[this.cur_level]);
            this.g_bitoff.drawRect(0, 0, this.cur_menuwidth[this.cur_level] - 1, this.bitheight);
        }
        this.g_bitoff.setColor(color);
        this.alignment = this.cur_alignment[this.cur_level];
        if (this.alignment == 0) {
            this.g_bitoff.drawString(this.subs[this.cur_sub_point + n * 4 + 1], this.m_lrmargin + this.cur_itadjust[this.cur_level], this.bitcenter);
        }
        else if (this.alignment == 1) {
            this.g_bitoff.drawString(this.subs[this.cur_sub_point + n * 4 + 1], this.cur_menuwidth[this.cur_level] - (this.fm.stringWidth(this.subs[this.cur_sub_point + n * 4 + 1]) + this.m_lrmargin), this.bitcenter);
        }
        else if (this.alignment == 2) {
            this.g_bitoff.drawString(this.subs[this.cur_sub_point + n * 4 + 1], this.m_lrmargin, this.bitcenter);
        }
        else if (this.alignment == 3) {
            this.g_bitoff.drawString(this.subs[this.cur_sub_point + n * 4 + 1], this.cur_menuwidth[this.cur_level] - (this.fm.stringWidth(this.subs[this.cur_sub_point + n * 4 + 1]) + this.cur_itadjust[this.cur_level] + this.m_lrmargin), this.bitcenter);
        }
        int lrmargin = this.m_lrmargin;
        final int int1 = Integer.parseInt(this.subs[this.cur_sub_point + n * 4 + 2]);
        this.lasticoid = int1;
        if (b) {
            if (int1 > -1 && this.s_pic[int1] != null) {
                final int width = this.s_pic[int1].getWidth(this);
                if (this.alignment == 2 || this.alignment == 3) {
                    lrmargin = this.cur_menuwidth[this.cur_level] - this.m_lrmargin - width;
                }
                this.g_bitoff.drawImage(this.s_pic[int1], lrmargin, this.bitheight / 2 - this.s_pic[int1].getHeight(this) / 2, this);
            }
        }
        else if (int1 > -1 && this.i_pic[int1] != null) {
            final int width2 = this.i_pic[int1].getWidth(this);
            if (this.alignment == 2 || this.alignment == 3) {
                lrmargin = this.cur_menuwidth[this.cur_level] - this.m_lrmargin - width2;
            }
            this.g_bitoff.drawImage(this.i_pic[int1], lrmargin, this.bitheight / 2 - this.i_pic[int1].getHeight(this) / 2, this);
        }
        this.g_ts.drawImage(this.bitoff, this.menu_coords[this.cur_level * 7], this.menu_coords[this.cur_level * 7 + 1] + n2, this);
        this.g_suboff[this.cur_level].drawImage(this.bitoff, 0, n2, this);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouse_in = true;
        if (this.rendering_images) {
            this.showStatus("Loading Menu Images...");
        }
        else {
            this.showStatus(this.m_onsbtext);
        }
        return true;
    }
    
    public void UndoSpecifics(final int n, final int n2, final boolean b) {
        this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
        if (this.bg_stat) {
            this.g_ts.drawImage(this.bg, this.m_bgimage_x, this.m_bgimage_y, this.m_page_bgcolor, this);
        }
        for (int i = 0; i < this.num_images; ++i) {
            if (this.simg_status[i]) {
                this.g_ts.drawImage(this.s_picture[i], this.m_pic_xcord[i], this.m_pic_ycord[i], this);
            }
        }
        if (this.last_drawn > -1 && b && this.hlimg_status[this.last_drawn]) {
            this.g_ts.drawImage(this.h_picture[this.last_drawn], this.m_pic_xcord[this.last_drawn], this.m_pic_ycord[this.last_drawn], this);
        }
    }
    
    public void CreateSub(final int n, final int n2) {
        if (this.lasticoid > -1) {
            this.i_pic[this.lasticoid].getWidth(this);
        }
        final int cur_num_subs = new Integer(this.p_subs[n + 3]) - new Integer(this.p_subs[n + 2]) + 1;
        final int cur_sub_point = new Integer(this.p_subs[n + 2]) * 4;
        this.alignment = new Integer(this.p_subs[n + 1]);
        this.cur_alignment[this.cur_level] = this.alignment;
        this.cur_sub_point = cur_sub_point;
        this.cur_num_subs = cur_num_subs;
        final int[] intValues = occcord.getIntValues(this.p_subs[n], ",", 2);
        if (intValues != null) {
            this.menux = intValues[0];
            this.menuy = intValues[1];
        }
        this.menuwidth = new Integer(this.p_subs[n + 4]);
        this.menuheight = cur_num_subs * this.bitheight + 1;
        this.maxiconwidth = 0;
        for (int i = 0; i < cur_num_subs; ++i) {
            final int int1 = Integer.parseInt(this.subs[cur_sub_point + i * 4 + 2]);
            if (int1 > -1 && this.i_pic[int1] != null) {
                final int width = this.i_pic[int1].getWidth(this);
                if (width > this.maxiconwidth) {
                    this.maxiconwidth = width;
                }
            }
        }
        this.menuwidth = this.menuwidth + this.maxiconwidth + this.m_it_space + 1;
        this.cur_menuwidth[this.cur_level] = this.menuwidth;
        this.menu_coords[this.cur_level * 7 + 2] = this.menuwidth;
        this.menu_coords[this.cur_level * 7 + 3] = this.menuheight;
        this.menu_coords[this.cur_level * 7 + 4] = this.cur_num_subs;
        this.menu_coords[this.cur_level * 7 + 5] = this.on_sub_num;
        this.menu_coords[this.cur_level * 7 + 6] = this.cur_sub_point;
        this.suboff[this.cur_level] = this.createImage(this.menuwidth, this.menuheight);
        this.subsave[this.cur_level] = this.createImage(this.menuwidth, this.menuheight);
        (this.g_suboff[this.cur_level] = this.suboff[this.cur_level].getGraphics()).setFont(this.font);
        this.g_suboff[this.cur_level].setColor(this.m_cur_menu_color[this.cur_level]);
        this.g_suboff[this.cur_level].fillRect(0, 0, this.menuwidth, this.menuheight);
        final Image image = this.mc_subbg[this.cur_level];
        if (this.mc_bgfile_stat[this.cur_level]) {
            int n3 = 0;
            int n4 = 0;
            final int width2 = image.getWidth(this);
            final int height = image.getHeight(this);
            if (this.gif_tile[this.cur_level]) {
                n4 = -1;
            }
            while (true) {
                this.g_suboff[this.cur_level].drawImage(image, n3, n4, this);
                n3 += width2;
                if (n3 > this.menuwidth + 1) {
                    n3 = 0;
                    n4 += height;
                    if (this.gif_tile[this.cur_level]) {
                        --n4;
                    }
                    if (n4 > this.menuheight + 1) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.m_cur_menu_bocolor[this.cur_level] != null) {
            this.g_suboff[this.cur_level].setColor(this.m_cur_menu_bocolor[this.cur_level]);
            this.g_suboff[this.cur_level].drawRect(0, 0, this.menuwidth - 1, this.menuheight - 1);
        }
        int lrmargin = this.m_lrmargin;
        for (int j = 0; j < cur_num_subs; ++j) {
            final int int2 = Integer.parseInt(this.subs[cur_sub_point + j * 4 + 2]);
            if (int2 > -1 && this.i_pic[int2] != null) {
                final int width3 = this.i_pic[int2].getWidth(this);
                if (this.alignment == 2 || this.alignment == 3) {
                    lrmargin = this.menuwidth - this.m_lrmargin - width3;
                }
                if (j == n2) {}
                this.g_suboff[this.cur_level].drawImage(this.i_pic[int2], lrmargin, this.bitheight * j + this.bitheight / 2 - this.i_pic[int2].getHeight(this) / 2, this);
            }
        }
        this.def_menuloc[this.cur_level] = lrmargin;
        if (this.menux == -1 && this.menuy == -2) {
            if (this.cur_alignment[this.cur_level - 1] == 2 || this.cur_alignment[this.cur_level - 1] == 3) {
                this.menu_coords[this.cur_level * 7 + 0] = this.menu_coords[(this.cur_level - 1) * 7] + this.cur_menuwidth[this.cur_level - 1] - 2;
            }
            else {
                this.menu_coords[this.cur_level * 7 + 0] = this.menu_coords[(this.cur_level - 1) * 7] + this.def_menuloc[this.cur_level - 1] - this.menuwidth - 2;
            }
            final int n5 = this.d.height - (this.menu_coords[(this.cur_level - 1) * 7 + 1] + n2 * this.bitheight + this.bitheight * cur_num_subs);
            if (n5 < 0) {
                this.menu_coords[this.cur_level * 7 + 1] = this.menu_coords[(this.cur_level - 1) * 7 + 1] + n2 * this.bitheight + n5 - 1;
            }
            else {
                this.menu_coords[this.cur_level * 7 + 1] = this.menu_coords[(this.cur_level - 1) * 7 + 1] + n2 * this.bitheight - 2;
            }
        }
        else {
            this.menu_coords[this.cur_level * 7 + 0] = this.menu_coords[(this.cur_level - 1) * 7] + this.menux;
            this.menu_coords[this.cur_level * 7 + 1] = this.menu_coords[(this.cur_level - 1) * 7 + 1] + this.menuy;
        }
        this.itadjust = 0;
        if (this.maxiconwidth > 0) {
            this.itadjust = this.maxiconwidth + this.m_it_space;
        }
        this.cur_itadjust[this.cur_level] = this.itadjust;
        for (int k = 0; k < cur_num_subs; ++k) {
            if (this.m_cur_outline_color[this.cur_level] != null) {
                this.g_suboff[this.cur_level].setColor(this.m_cur_outline_color[this.cur_level]);
                this.g_suboff[this.cur_level].drawRect(0, this.bitheight * k, this.menuwidth, this.bitheight);
            }
            this.g_suboff[this.cur_level].setColor(this.m_cur_menu_textcolor[this.cur_level]);
            if (this.alignment == 0) {
                this.g_suboff[this.cur_level].drawString(this.subs[cur_sub_point + k * 4 + 1], this.m_lrmargin + this.itadjust, this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 1) {
                this.g_suboff[this.cur_level].drawString(this.subs[cur_sub_point + k * 4 + 1], this.menuwidth - (this.fm.stringWidth(this.subs[cur_sub_point + k * 4 + 1]) + this.m_lrmargin), this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 2) {
                this.g_suboff[this.cur_level].drawString(this.subs[cur_sub_point + k * 4 + 1], this.m_lrmargin, this.bitheight * k + this.bitcenter);
            }
            else if (this.alignment == 3) {
                this.g_suboff[this.cur_level].drawString(this.subs[cur_sub_point + k * 4 + 1], this.menuwidth - (this.fm.stringWidth(this.subs[cur_sub_point + k * 4 + 1]) + this.itadjust + this.m_lrmargin), this.bitheight * k + this.bitcenter);
            }
        }
        this.g_ts.drawImage(this.suboff[this.cur_level], this.menu_coords[this.cur_level * 7], this.menu_coords[this.cur_level * 7 + 1], this);
        this.paintIt();
        if (this.m_cur_menu_hloutcolor[this.cur_level] != null) {
            this.subsave[this.cur_level] = this.createImage(this.suboff[this.cur_level].getWidth(this), this.suboff[this.cur_level].getHeight(this));
            this.subsave[this.cur_level].getGraphics().drawImage(this.suboff[this.cur_level], 0, 0, this);
        }
        this.on_sub_num = -1;
    }
    
    public void MouseInSub(final int n, final int cur_level) {
        boolean b = false;
        if (cur_level < this.cur_level) {
            b = true;
        }
        int i = 1;
        while (i <= this.cur_num_subs) {
            if (n > this.bitheight * (i - 1) && n < this.bitheight * i) {
                if (b) {
                    if (this.menu_coords[(cur_level + 1) * 7 + 5] != i) {
                        this.UndoSpecifics(cur_level + 1, this.cur_level, true);
                        for (int j = 0; j <= cur_level; ++j) {
                            final int n2 = j * 7;
                            this.g_ts.drawImage(this.suboff[j], this.menu_coords[n2], this.menu_coords[n2 + 1], this);
                        }
                        this.cur_level = cur_level;
                        this.cur_sub_point = this.menu_coords[this.cur_level * 7 + 6];
                        this.UndoSubHighlight(this.getGraphics(), this.menu_coords[(this.cur_level + 1) * 7 + 5] - 1);
                        this.on_sub_num = i;
                        this.SubHighlight(this.getGraphics(), i - 1);
                        return;
                    }
                    if (this.on_sub_num != -1) {
                        this.UndoSubHighlight(this.getGraphics(), this.last_sub);
                        this.paintIt();
                    }
                    this.sub_highlighted = false;
                    this.on_sub_num = -1;
                }
                else {
                    if (i != this.on_sub_num) {
                        if (this.sub_highlighted) {
                            this.UndoSubHighlight(this.getGraphics(), this.last_sub);
                        }
                        this.on_sub_num = i;
                        this.SubHighlight(this.getGraphics(), i - 1);
                        return;
                    }
                    break;
                }
            }
            else {
                ++i;
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        int n3;
        if (this.actual_level != -1) {
            n3 = this.actual_level;
        }
        else {
            n3 = this.cur_level;
        }
        String cur_img_link;
        if (n3 > -1) {
            cur_img_link = this.cur_link[n3];
        }
        else {
            cur_img_link = this.cur_img_link;
        }
        if (cur_img_link.equals("-1")) {
            return true;
        }
        final int index = cur_img_link.indexOf("|");
        String substring;
        String s;
        if (index > 0) {
            if (index < cur_img_link.length() - 1) {
                substring = cur_img_link.substring(0, index);
                s = cur_img_link.substring(index + 1);
            }
            else {
                substring = cur_img_link;
                s = this.m_loadwhere;
            }
        }
        else {
            substring = cur_img_link;
            s = this.m_loadwhere;
        }
        try {
            this.goURL = new URL(this.getDocumentBase(), substring);
        }
        catch (MalformedURLException ex) {
            return true;
        }
        this.getAppletContext().showDocument(this.goURL, s);
        return true;
    }
    
    public void run() {
        while (true) {
            if (this.first_run) {
                this.FirstRun(this.getGraphics());
                this.first_run = false;
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void destroy() {
        if (this.trun != null) {
            this.trun.stop();
            this.trun = null;
        }
    }
    
    public void init() {
        final String s = "Infinite Icon Menus, Copyright (c) 1998 OpenCube Technologies, Unregistered";
        this.d = this.size();
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.m_onsbtext = "Missing 'Notice' Tag";
            return;
        }
        final String key = ocekey.getKey(parameter, this.getDocumentBase());
        if (key.equals(s)) {
            this.tag = true;
            final String parameter2 = this.getParameter("subbgfile");
            if (parameter2 != null) {
                this.m_sub_bgfile = parameter2;
            }
            else {
                this.m_sub_bgfile = "-1";
            }
            final String parameter3 = this.getParameter("icontextspace");
            if (parameter3 != null) {
                this.m_it_space = Integer.parseInt(parameter3);
            }
            final String parameter4 = this.getParameter("italign");
            if (parameter4 != null) {
                this.m_italign = parameter4;
            }
            final String parameter5 = this.getParameter("menubitheight");
            if (parameter5 != null) {
                this.m_forced_height = Integer.parseInt(parameter5);
            }
            final String parameter6 = this.getParameter("menucolor");
            if (parameter6 != null) {
                this.m_menu_color = occcolor.ConvertColor(parameter6);
            }
            final String parameter7 = this.getParameter("menuhloutcolor");
            if (parameter7 != null) {
                this.m_menu_hloutcolor = occcolor.ConvertColor(parameter7);
            }
            final String parameter8 = this.getParameter("menuboxoutcolor");
            if (parameter8 != null) {
                this.m_menu_bocolor = occcolor.ConvertColor(parameter8);
            }
            final String parameter9 = this.getParameter("menuoutlinecolor");
            if (parameter9 != null) {
                this.m_outline_color = occcolor.ConvertColor(parameter9);
            }
            final String parameter10 = this.getParameter("bgimagefile");
            if (parameter10 != null) {
                this.m_bgimage_file = parameter10;
            }
            final String parameter11 = this.getParameter("loadmsgcolor");
            if (parameter11 != null) {
                this.m_load_msgcolor = occcolor.ConvertColor(parameter11);
            }
            final String parameter12 = this.getParameter("loadmsg");
            if (parameter12 != null) {
                this.m_load_msg = parameter12;
            }
            final String parameter13 = this.getParameter("menutextcolor");
            if (parameter13 != null) {
                this.m_menu_textcolor = occcolor.ConvertColor(parameter13);
            }
            final String parameter14 = this.getParameter("menuhltextcolor");
            if (parameter14 != null) {
                this.m_menu_hltextcolor = occcolor.ConvertColor(parameter14);
            }
            final String parameter15 = this.getParameter("onsbtext");
            if (parameter15 != null) {
                this.m_onsbtext = parameter15;
            }
            final String parameter16 = this.getParameter("offsbtext");
            if (parameter16 != null) {
                this.m_offsbtext = parameter16;
            }
            final String parameter17 = this.getParameter("bgimagexy");
            if (parameter17 != null) {
                final int[] intValues = occcord.getIntValues(parameter17, ",", 2);
                if (intValues != null) {
                    this.m_bgimage_x = intValues[0];
                    this.m_bgimage_y = intValues[1];
                }
            }
            final String parameter18 = this.getParameter("centertext");
            if (parameter18 != null) {
                this.m_center_text = Boolean.valueOf(parameter18);
            }
            final String parameter19 = this.getParameter("font");
            if (parameter19 != null) {
                this.font = ocfontc.getFontSD(parameter19, ",");
            }
            else {
                this.font = new Font("Helvetica", 0, 12);
            }
            this.setFont(this.font);
            this.fm = this.getFontMetrics(this.font);
            final String parameter20 = this.getParameter("lrmargin");
            if (parameter20 != null) {
                this.m_lrmargin = Integer.parseInt(parameter20);
            }
            final String parameter21 = this.getParameter("loadwhere");
            if (parameter21 != null) {
                this.m_loadwhere = parameter21;
            }
            final String parameter22 = this.getParameter("bgcolor");
            if (parameter22 != null) {
                this.m_page_bgcolor = occcolor.ConvertColor(parameter22);
            }
            this.setBackground(this.m_page_bgcolor);
            while (this.getParameter("iconfile" + this.num_icons) != null) {
                ++this.num_icons;
            }
            this.m_icon_file = new String[this.num_icons];
            this.m_icon_switch_file = new String[this.num_icons];
            this.i_pic = new Image[this.num_icons];
            this.s_pic = new Image[this.num_icons];
            for (int i = 0; i < this.num_icons; ++i) {
                final String parameter23 = this.getParameter("iconfile" + i);
                if (parameter23 != null) {
                    this.m_icon_file[i] = new String(parameter23);
                }
                else {
                    this.m_icon_file[i] = new String(" ");
                }
                final String parameter24 = this.getParameter("iconswitchfile" + i);
                if (parameter24 != null) {
                    this.m_icon_switch_file[i] = new String(parameter24);
                }
                else {
                    this.m_icon_switch_file[i] = this.m_icon_file[i];
                }
            }
            while (this.getParameter("imagefile" + this.num_images) != null) {
                ++this.num_images;
            }
            this.m_imagefile = new String[this.num_images];
            this.m_switchfile = new String[this.num_images];
            this.m_pic_xcord = new int[this.num_images];
            this.m_pic_ycord = new int[this.num_images];
            this.m_cur_image_desturl = new String[this.num_images];
            this.ItoM = new int[this.num_images];
            this.hlimg_status = new boolean[this.num_images];
            this.simg_status = new boolean[this.num_images];
            this.pic_width = new int[this.num_images];
            this.pic_height = new int[this.num_images];
            this.s_picture = new Image[this.num_images];
            this.h_picture = new Image[this.num_images];
            this.max_string_width = new int[this.num_images];
            this.no_subs = new boolean[this.num_images];
            final Vector vector = new Vector<String>();
            final Vector vector2 = new Vector<String>();
            int n = 0;
            int n2 = 0;
            Stack<String> stack = new Stack<String>();
            Stack<String> stack2 = new Stack<String>();
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            int n7 = 0;
            for (int j = 0; j < this.num_images; ++j) {
                String s2 = new Integer(j).toString();
                int n8 = 1;
                while (true) {
                    final String string = s2 + "-" + n2;
                    final String parameter25 = this.getParameter("desc" + string);
                    int k = 0;
                    int level_max = 0;
                    while (k != -1) {
                        k = string.indexOf("-", k + 1);
                        if (k == -1) {
                            break;
                        }
                        if (++level_max <= this.level_max) {
                            continue;
                        }
                        this.level_max = level_max;
                    }
                    if (parameter25 != null) {
                        if (this.getParameter("desc" + string + "-0") != null) {
                            vector.addElement(string);
                            ++n3;
                            stack.push(string);
                            stack2.push(new Integer(n * 4).toString());
                        }
                        else {
                            vector.addElement("-1");
                        }
                        String substring = "-1";
                        final int index = parameter25.indexOf("|");
                        String substring2;
                        if (index > 0) {
                            if (index < parameter25.length() - 1) {
                                substring = parameter25.substring(0, index);
                                substring2 = parameter25.substring(index + 1);
                            }
                            else {
                                substring2 = parameter25;
                            }
                        }
                        else {
                            substring2 = parameter25;
                        }
                        vector.addElement(substring2);
                        if (this.fm.stringWidth(substring2) + this.m_lrmargin * 2 > n4) {
                            n4 = this.fm.stringWidth(substring2) + this.m_lrmargin * 2;
                        }
                        vector.addElement(substring);
                        final String parameter26 = this.getParameter("desturl" + string);
                        if (parameter26 != null) {
                            vector.addElement(parameter26);
                        }
                        else {
                            vector.addElement("-1");
                        }
                        if (n2 == 0) {
                            final String parameter27 = this.getParameter("menuxy" + s2);
                            if (parameter27 != null) {
                                vector2.addElement(parameter27);
                            }
                            else {
                                vector2.addElement("-1,-2");
                            }
                            final String parameter28 = this.getParameter("italign" + s2);
                            if (parameter28 != null) {
                                vector2.addElement(parameter28);
                            }
                            else {
                                vector2.addElement(this.m_italign);
                            }
                            vector2.addElement(new Integer(n).toString());
                        }
                        ++n;
                        ++n2;
                    }
                    else {
                        if (this.no_subs[j]) {
                            int n9 = 0;
                            do {
                                vector2.addElement("0");
                            } while (++n9 < 5);
                        }
                        if (n2 > 0) {
                            vector2.addElement(new Integer(n - 1).toString());
                            vector2.addElement(new Integer(n4).toString());
                        }
                        ++n5;
                        ++n6;
                        if (n8 == 1) {
                            this.ItoM[j] = (n5 - 1) * 5;
                            n8 = 0;
                        }
                        if (n3 < 1) {
                            break;
                        }
                        s2 = stack.pop().toString();
                        --n3;
                        vector.setElementAt(new Integer(n5).toString(), new Integer(stack2.pop().toString()));
                        n2 = 0;
                        n4 = 0;
                    }
                }
                stack = new Stack<String>();
                n4 = 0;
                stack2 = new Stack<String>();
                n2 = 0;
                if (n6 > n7) {
                    n7 = n6;
                    n6 = 0;
                }
                final String parameter29 = this.getParameter("imagefile" + j);
                if (parameter29 != null) {
                    this.m_imagefile[j] = new String(parameter29);
                }
                else {
                    this.m_imagefile[j] = new String(" ");
                }
                final String parameter30 = this.getParameter("switchfile" + j);
                if (parameter30 != null) {
                    this.m_switchfile[j] = new String(parameter30);
                }
                else {
                    this.m_switchfile[j] = new String(" ");
                }
                final String parameter31 = this.getParameter("picxy" + j);
                if (parameter31 != null) {
                    final int[] intValues2 = occcord.getIntValues(parameter31, ",", 2);
                    if (intValues2 != null) {
                        this.m_pic_xcord[j] = intValues2[0];
                        this.m_pic_ycord[j] = intValues2[1];
                    }
                }
                final String parameter32 = this.getParameter("imagedesturl" + j);
                if (parameter32 != null) {
                    this.m_cur_image_desturl[j] = parameter32;
                }
                else {
                    this.m_cur_image_desturl[j] = null;
                }
                this.ts = this.createImage(this.d.width, this.d.height);
                (this.g_ts = this.ts.getGraphics()).setColor(this.m_page_bgcolor);
                this.g_ts.fillRect(0, 0, this.d.width, this.d.height);
            }
            this.menu_coords = new int[n7 * 7];
            this.cur_link = new String[n7];
            this.suboff = new Image[n7];
            this.g_suboff = new Graphics[n7];
            this.subsave = new Image[n7];
            vector.copyInto(this.subs = new String[vector.size()]);
            vector2.copyInto(this.p_subs = new String[vector2.size()]);
            for (int l = 0; l < this.num_images; ++l) {
                if (this.getParameter("desc" + l + "-0") == null) {
                    this.no_subs[l] = true;
                }
            }
            this.m_cur_outline_color = new Color[this.level_max];
            this.m_cur_menu_color = new Color[this.level_max];
            this.m_cur_menu_textcolor = new Color[this.level_max];
            this.m_cur_menu_hloutcolor = new Color[this.level_max];
            this.m_cur_menu_hltextcolor = new Color[this.level_max];
            this.m_cur_menu_bocolor = new Color[this.level_max];
            this.mc_sub_bgfile = new String[this.level_max];
            this.mc_subbg = new Image[this.level_max];
            this.mc_bgfile_stat = new boolean[this.level_max];
            this.cur_alignment = new int[this.level_max];
            this.cur_itadjust = new int[this.level_max];
            this.cur_menuwidth = new int[this.level_max];
            this.def_menuloc = new int[this.level_max];
            this.gif_tile = new boolean[this.level_max];
            for (int n10 = 0; n10 < this.level_max; ++n10) {
                final String parameter33 = this.getParameter("subbgfile" + n10);
                if (parameter33 != null) {
                    this.mc_sub_bgfile[n10] = parameter33;
                }
                else {
                    this.mc_sub_bgfile[n10] = this.m_sub_bgfile;
                }
                if (this.mc_sub_bgfile[n10].toLowerCase().lastIndexOf("gif") != -1) {
                    this.gif_tile[n10] = true;
                }
                final String parameter34 = this.getParameter("menucolor" + n10);
                if (parameter34 != null) {
                    this.m_cur_menu_color[n10] = occcolor.ConvertColor(parameter34);
                }
                else {
                    this.m_cur_menu_color[n10] = this.m_menu_color;
                }
                final String parameter35 = this.getParameter("menuhloutcolor" + n10);
                if (parameter35 != null) {
                    this.m_cur_menu_hloutcolor[n10] = occcolor.ConvertColor(parameter35);
                }
                else {
                    this.m_cur_menu_hloutcolor[n10] = this.m_menu_hloutcolor;
                }
                final String parameter36 = this.getParameter("menuboxoutcolor" + n10);
                if (parameter36 != null) {
                    this.m_cur_menu_bocolor[n10] = occcolor.ConvertColor(parameter36);
                }
                else {
                    this.m_cur_menu_bocolor[n10] = this.m_menu_bocolor;
                }
                final String parameter37 = this.getParameter("menuoutlinecolor" + n10);
                if (parameter37 != null) {
                    this.m_cur_outline_color[n10] = occcolor.ConvertColor(parameter37);
                }
                else {
                    this.m_cur_outline_color[n10] = this.m_outline_color;
                }
                final String parameter38 = this.getParameter("menutextcolor" + n10);
                if (parameter38 != null) {
                    this.m_cur_menu_textcolor[n10] = occcolor.ConvertColor(parameter38);
                }
                else {
                    this.m_cur_menu_textcolor[n10] = this.m_menu_textcolor;
                }
                final String parameter39 = this.getParameter("menuhltextcolor" + n10);
                if (parameter39 != null) {
                    this.m_cur_menu_hltextcolor[n10] = occcolor.ConvertColor(parameter39);
                }
                else {
                    this.m_cur_menu_hltextcolor[n10] = this.m_menu_hltextcolor;
                }
            }
            if (this.m_forced_height > 0) {
                this.bitheight = this.m_forced_height;
                if (this.bitheight < this.fm.getHeight()) {
                    this.bitheight = this.fm.getHeight();
                }
            }
            else {
                this.bitheight = this.fm.getHeight();
            }
            this.bitcenter = this.bitheight / 2 + (this.fm.getAscent() + this.fm.getLeading()) / 2;
            return;
        }
        if (key.equals("-1")) {
            this.m_onsbtext = "Evaluation Applet Error: Runs Locally Only";
            return;
        }
        this.m_onsbtext = "Incorrect Copyright Notice in 'Notice' tag";
    }
    
    public void paint(final Graphics graphics) {
        this.paintIt();
    }
    
    public void SubHighlight(final Graphics graphics, final int last_sub) {
        this.last_sub = last_sub;
        this.sub_highlighted = true;
        this.cur_link[this.cur_level] = this.subs[this.cur_sub_point + last_sub * 4 + 3];
        this.highlightText(this.m_cur_menu_hltextcolor[this.cur_level], last_sub, true);
        final int intValue = new Integer(this.subs[this.cur_sub_point + last_sub * 4]);
        if (intValue > -1) {
            ++this.cur_level;
            this.CreateSub(intValue * 5, last_sub);
            return;
        }
        this.paintIt();
    }
    
    public iim() {
        this.cur_img_link = "-1";
        this.lasticoid = -1;
        this.m_load_msg = "Loading Images...";
        this.m_load_msgcolor = Color.black;
        this.m_italign = "0";
        this.first_run = true;
        this.m_it_space = 1;
        this.m_forced_height = -1;
        this.m_loadwhere = "_self";
        this.m_page_bgcolor = Color.black;
        this.m_bgimage_file = "-1";
        this.m_menu_color = Color.yellow;
        this.m_menu_textcolor = Color.black;
        this.m_menu_hltextcolor = Color.black;
        this.m_lrmargin = 4;
        this.m_onsbtext = "OpenCube - Infinite Icon Menus";
        this.m_offsbtext = "Java by OpenCube";
        this.m_center_text = true;
        this.draw_spec_hl = -1;
        this.last_drawn = -1;
        this.m_media_track = new MediaTracker(this);
        this.cur_sub_point = -1;
        this.cur_level = -1;
    }
}
