import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.awt.Point;
import java.util.Vector;
import java.awt.Color;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jpathfinder extends Applet
{
    Scrollbar v_bar;
    Scrollbar h_bar;
    int bar_size;
    boolean loading;
    boolean h_show;
    boolean v_show;
    Color bgcolor;
    Color fgcolor;
    Color pscolor;
    Color dtcolor;
    Color bdcolor;
    Color hgcolor;
    Color ovcolor;
    Color open_color;
    Color close_color;
    Color end_color;
    String window;
    static final int ICON_WIDTH = 16;
    static final int ICON_HEIGHT = 16;
    int INDENT_STEP;
    int GAP;
    int LINE_STEP;
    int x_align;
    int y_align;
    int y_adjust;
    int mode;
    Vector chars_width;
    Vector font_info;
    Point mouse_point;
    int base;
    Vector node_set;
    DataInputStream conf_data;
    int[] open_tri_x;
    int[] open_tri_y;
    int[] close_tri_x;
    int[] close_tri_y;
    boolean tri_node;
    int font_size;
    int font_asc;
    int font_des;
    int x_offset;
    int y_offset;
    int w;
    int h;
    int pre_real_w;
    int pre_real_h;
    int real_w;
    int real_h;
    Image bufImg;
    Graphics bufGraphics;
    boolean legal;
    
    public Jpathfinder() {
        this.v_bar = null;
        this.h_bar = null;
        this.bar_size = 15;
        this.loading = true;
        this.h_show = true;
        this.v_show = true;
        this.bgcolor = new Color(255, 255, 255);
        this.fgcolor = new Color(0, 0, 0);
        this.pscolor = Color.white;
        this.dtcolor = new Color(255, 0, 0);
        this.bdcolor = new Color(255, 0, 0);
        this.hgcolor = Color.red;
        this.ovcolor = new Color(0, 0, 255);
        this.open_color = Color.blue;
        this.close_color = Color.red;
        this.end_color = Color.blue;
        this.window = "_self";
        this.INDENT_STEP = 15;
        this.GAP = 6;
        this.LINE_STEP = 4;
        this.x_align = 10;
        this.y_align = 10;
        this.y_adjust = 0;
        this.mode = 2;
        this.chars_width = new Vector();
        this.font_info = new Vector();
        this.base = 0;
        this.node_set = new Vector();
        this.open_tri_x = new int[] { 2, 16, 9 };
        this.open_tri_y = new int[] { 5, 5, 12 };
        this.close_tri_x = new int[] { 5, 12, 5 };
        this.close_tri_y = new int[] { 2, 9, 16 };
        this.tri_node = true;
        this.w = 0;
        this.h = 0;
        this.pre_real_w = 0;
        this.pre_real_h = 0;
        this.real_w = 0;
        this.real_h = 0;
        this.bufImg = null;
        this.bufGraphics = null;
        this.legal = true;
    }
    
    public void DrawString(final Graphics graphics, final Color color, final String s, final int n, final int n2) {
        int n3 = n;
        final char[] array = { 'g', 'j', 'p', 'q', 'y' };
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(s.charAt(i) - ' ');
            final String s2 = this.font_info.elementAt(c);
            final int intValue = this.chars_width.elementAt(c);
            for (int j = 0; j < this.font_size; ++j) {
                for (int k = 0; k < intValue; ++k) {
                    if (s2.charAt(j * intValue + k) == '0') {
                        int n4 = 0;
                        if (j >= this.font_size - 2) {
                            for (int l = -1; l < 0; ++l) {
                                for (int n5 = -2; n5 <= 2; ++n5) {
                                    if (j + l >= 0 && j + l < this.font_size && k + n5 >= 0 && k + n5 < intValue && s2.charAt((j + l) * intValue + k + n5) == '0') {
                                        ++n4;
                                    }
                                }
                            }
                        }
                        else {
                            n4 = 3;
                        }
                        if (n4 >= 2) {
                            graphics.setColor(color);
                            graphics.drawLine(n3 + k, n2 + j, n3 + k, n2 + j);
                        }
                    }
                }
            }
            n3 += intValue;
        }
    }
    
    public void calc_position() {
        int real_w = 0;
        int real_h = 0;
        int n = 0;
        this.base = this.font_asc;
        for (int i = 0; i < this.node_set.size(); i = this.get_next_index(i)) {
            final Node node = this.node_set.elementAt(i);
            if (node.visible) {
                final int start_x = this.x_offset + this.x_align + node.level * this.INDENT_STEP;
                final int start_y = this.y_offset + this.y_align + n * this.LINE_STEP;
                node.start_x = start_x;
                node.start_y = start_y;
                final int[] array = new int[3];
                final int[] array2 = new int[3];
                for (int j = 0; j < 3; ++j) {
                    array[j] = this.open_tri_x[j] + start_x;
                    array2[j] = this.open_tri_y[j] + start_y;
                }
                node.open_position = new Polygon(array, array2, 3);
                for (int k = 0; k < 3; ++k) {
                    array[k] = this.close_tri_x[k] + start_x;
                    array2[k] = this.close_tri_y[k] + start_y;
                }
                node.close_position = new Polygon(array, array2, 3);
                node.end_position = new Rectangle(start_x + 6, start_y + 5, 8, 8);
                node.plus_minus = new Rectangle(start_x + 3, start_y + 3, 10, 10);
                final int n2 = this.get_str_len(node.label) + 2;
                int font_asc;
                if (this.has_descent(node.label)) {
                    font_asc = this.font_asc + this.font_des;
                }
                else {
                    font_asc = this.font_asc;
                }
                font_asc += 2;
                final int n3 = (font_asc - 7) / 2 - this.y_adjust;
                if (!node.allow_expand) {
                    node.label_position = new Rectangle(start_x + 16 + this.GAP - 1, start_y - n3, n2 + 2, font_asc + 2);
                }
                else {
                    node.label_position = new Rectangle(start_x + 16 + this.GAP - 2, start_y - n3, n2 + 2, font_asc + 2);
                }
                node.sensitive = new Rectangle(start_x, start_y, 16 + this.GAP + n2, font_asc);
                this.node_set.setElementAt(node, i);
                if (start_x - this.x_offset + 16 + this.GAP + n2 + 2 > real_w) {
                    real_w = start_x - this.x_offset + 16 + this.GAP + n2 + 2;
                }
                if (start_y - this.y_offset + font_asc + 3 > real_h) {
                    real_h = start_y - this.y_offset + font_asc + 3;
                }
                this.node_set.setElementAt(node, i);
                ++n;
            }
        }
        this.pre_real_w = this.real_w;
        this.pre_real_h = this.real_h;
        this.real_w = real_w;
        this.real_h = real_h;
        if (this.real_h >= this.h - this.bar_size && this.real_w < this.w - this.bar_size) {
            this.v_bar.reshape(this.w - this.bar_size, 1, this.bar_size, this.h - 2);
            this.h_bar.reshape(1, this.h - 2, this.w - this.bar_size - 2, 0);
            this.v_bar.setValues(-this.y_offset, 20, 0, this.real_h - this.h + 40);
            this.h_show = false;
            this.v_show = true;
        }
        if (this.real_h >= this.h - this.bar_size && this.real_w >= this.w - this.bar_size) {
            this.v_bar.reshape(this.w - this.bar_size, 1, this.bar_size, this.h - this.bar_size - 2);
            this.h_bar.reshape(1, this.h - this.bar_size, this.w - this.bar_size - 2, this.bar_size);
            this.v_bar.setValues(-this.y_offset, 20, 0, this.real_h - this.h + 40);
            this.h_bar.setValues(-this.x_offset, 20, 0, this.real_w - this.w + 40);
            this.h_show = true;
            this.v_show = true;
        }
        if (this.real_h < this.h - this.bar_size && this.real_w < this.w - this.bar_size) {
            this.v_bar.reshape(this.w - 1, 1, 0, this.h - this.bar_size - 2);
            this.h_bar.reshape(1, this.h - 2, this.w - this.bar_size - 2, 0);
            this.h_show = false;
            this.v_show = false;
        }
        if (this.real_h < this.h - this.bar_size && this.real_w >= this.w - this.bar_size) {
            this.v_bar.reshape(this.w - 1, 1, 0, this.h - this.bar_size - 2);
            this.h_bar.reshape(1, this.h - this.bar_size, this.w - 2, this.bar_size);
            this.h_bar.setValues(-this.x_offset, 20, 0, this.real_w - this.w + 40);
            this.h_show = true;
            this.v_show = false;
        }
    }
    
    public Color get_color(final String s) {
        Integer n = null;
        Integer n2 = null;
        Integer n3 = null;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            if (stringTokenizer.hasMoreTokens()) {
                n = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                n2 = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            if (stringTokenizer.hasMoreTokens()) {
                n3 = new Integer(this.remove_space(stringTokenizer.nextToken()));
            }
            return new Color(n, n2, n3);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int get_next_index(final int n) {
        final Node node = this.node_set.elementAt(n);
        int i;
        for (i = n + 1; i < this.node_set.size(); ++i) {
            final Node node2 = this.node_set.elementAt(i);
            if (node2.visible) {
                int j = node2.parent;
                boolean b = true;
                while (j > 0) {
                    final Node node3 = this.node_set.elementAt(j);
                    if (!node3.visible) {
                        b = false;
                        break;
                    }
                    j = node3.parent;
                }
                if (b) {
                    break;
                }
            }
        }
        return i;
    }
    
    public String get_next_string(final String s) {
        final String substring = s.substring(s.indexOf(34) + 1);
        return substring.substring(0, substring.indexOf(34));
    }
    
    public int get_str_len(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += (int)this.chars_width.elementAt(s.charAt(i) - ' ');
        }
        return n;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                if (event.target == this.v_bar) {
                    this.y_offset = -(int)event.arg;
                }
                if (event.target == this.h_bar) {
                    this.x_offset = -(int)event.arg;
                }
                this.calc_position();
                this.repaint();
                this.validate();
            }
            case 503: {
                this.mouse_moved(event);
                break;
            }
            case 501: {
                this.mouse_pressed(event);
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean has_descent(final String s) {
        final String[] array = { "g", "j", "p", "q", "y" };
        for (int i = 0; i < array.length; ++i) {
            if (s.indexOf(array[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    public void init() {
        this.w = this.size().width;
        this.h = this.size().height;
        final Date date = new Date();
        final int month = date.getMonth();
        final int n = date.getYear() + 1900;
        final String host = this.getCodeBase().getHost();
        if (n == 2002 && month > 6 && host.indexOf("mw6tech") < 0) {
            return;
        }
        final Color get_color = this.get_color(this.getParameter("backColor"));
        if (get_color != null) {
            this.bgcolor = get_color;
        }
        this.setBackground(this.bgcolor);
        final Color get_color2 = this.get_color(this.getParameter("dotColor"));
        if (get_color2 != null) {
            this.dtcolor = get_color2;
        }
        final Color get_color3 = this.get_color(this.getParameter("borderColor"));
        if (get_color3 != null) {
            this.bdcolor = get_color3;
        }
        final Color get_color4 = this.get_color(this.getParameter("textColor"));
        if (get_color4 != null) {
            this.fgcolor = get_color4;
        }
        final Color get_color5 = this.get_color(this.getParameter("highColor"));
        if (get_color5 != null) {
            this.hgcolor = get_color5;
        }
        final Color get_color6 = this.get_color(this.getParameter("mousePressedColor"));
        if (get_color6 != null) {
            this.pscolor = get_color6;
        }
        final Color get_color7 = this.get_color(this.getParameter("mouseOverColor"));
        if (get_color7 != null) {
            this.ovcolor = get_color7;
        }
        final Color get_color8 = this.get_color(this.getParameter("openColor"));
        if (get_color8 != null) {
            this.open_color = get_color8;
        }
        final Color get_color9 = this.get_color(this.getParameter("closeColor"));
        if (get_color9 != null) {
            this.close_color = get_color9;
        }
        final Color get_color10 = this.get_color(this.getParameter("endColor"));
        if (get_color10 != null) {
            this.end_color = get_color10;
        }
        final String parameter = this.getParameter("window");
        if (parameter != null) {
            this.window = parameter;
        }
        final String parameter2 = this.getParameter("triNode");
        if (parameter2 != null) {
            if (parameter2.equals("Y") || parameter2.equals("y")) {
                this.tri_node = true;
            }
            else {
                this.tri_node = false;
            }
        }
        final String parameter3 = this.getParameter("barSize");
        if (parameter3 != null) {
            this.bar_size = new Integer(parameter3);
            if (this.bar_size > 20) {
                this.bar_size = 20;
            }
        }
        final String parameter4 = this.getParameter("xAlign");
        if (parameter4 != null) {
            this.x_align = new Integer(parameter4);
        }
        final String parameter5 = this.getParameter("yAlign");
        if (parameter5 != null) {
            this.y_align = new Integer(parameter5);
        }
        final String parameter6 = this.getParameter("yAdjust");
        if (parameter6 != null) {
            this.y_adjust = new Integer(parameter6);
        }
        final String parameter7 = this.getParameter("indent");
        if (parameter7 != null) {
            this.INDENT_STEP = new Integer(parameter7);
        }
        final String parameter8 = this.getParameter("gap");
        if (parameter8 != null) {
            this.GAP = new Integer(parameter8);
        }
        final String parameter9 = this.getParameter("mode");
        if (parameter9 != null) {
            this.mode = new Integer(parameter9);
        }
        if (this.mode == 2) {
            this.tri_node = true;
        }
        final String parameter10 = this.getParameter("lineSpace");
        if (parameter10 != null) {
            this.LINE_STEP = new Integer(parameter10);
        }
        (this.h_bar = new Scrollbar(0)).setLineIncrement(20);
        this.h_bar.reshape(1, this.h, this.w, 0);
        (this.v_bar = new Scrollbar(1)).setLineIncrement(20);
        this.v_bar.reshape(this.w, 1, 0, this.h);
        this.setLayout(null);
        this.add(this.h_bar);
        this.add(this.v_bar);
        final String parameter11 = this.getParameter("fontFile");
        if (parameter11 != null) {
            try {
                final InputStream openStream = new URL(this.getCodeBase(), parameter11).openStream();
                int n2 = 0;
                final byte[] array = new byte[20000];
                final DataInputStream dataInputStream = new DataInputStream(openStream);
                boolean b = false;
                while (!b) {
                    try {
                        array[n2++] = dataInputStream.readByte();
                    }
                    catch (Exception ex) {
                        b = true;
                    }
                }
                this.font_size = array[0];
                this.font_asc = array[1];
                this.font_des = array[2];
                int n3 = 3;
                for (int i = 32; i <= 126; ++i) {
                    final byte b2 = array[n3++];
                    this.chars_width.addElement(new Integer(b2));
                    int n4 = this.font_size * b2 / 8;
                    if (this.font_size * b2 % 8 != 0) {
                        ++n4;
                    }
                    final int n5 = n4 * 8;
                    final int n6 = b2 * this.font_size;
                    String s = "";
                    for (int j = 0; j < n4; ++j) {
                        final byte b3 = array[n3++];
                        if (j < n4 - 1 || n5 == n6) {
                            for (int k = 0; k < 8; ++k) {
                                if ((b3 >> 7 - k & 0x1) == 0x1) {
                                    s = String.valueOf(s) + "1";
                                }
                                else {
                                    s = String.valueOf(s) + "0";
                                }
                            }
                        }
                        else {
                            for (int n7 = n6 - 8 * (n4 - 1), l = 0; l < n7; ++l) {
                                if ((b3 >> n7 - l & 0x1) == 0x1) {
                                    s = String.valueOf(s) + "1";
                                }
                                else {
                                    s = String.valueOf(s) + "0";
                                }
                            }
                        }
                    }
                    this.font_info.addElement(s);
                }
            }
            catch (Exception ex2) {}
        }
        try {
            this.conf_data = new DataInputStream(new URL(String.valueOf(String.valueOf(this.getCodeBase())) + "conf.dat").openStream());
        }
        catch (Exception ex3) {}
        final Vector<Integer> vector = new Vector<Integer>();
        int n8 = 0;
        if (host.indexOf("mw6tech") < 0) {
            n8 = 1;
        }
        try {
            for (String s2 = this.conf_data.readLine(); s2 != null; s2 = this.conf_data.readLine()) {
                final int index = s2.indexOf("<root ");
                if (index >= 0 && n8 == 0) {
                    final String substring = s2.substring(index + 5);
                    final String substring2 = substring.substring(0, substring.lastIndexOf(">"));
                    final Node node = new Node();
                    node.parent = -1;
                    node.level = 0;
                    final String substring3 = substring2.substring(substring2.indexOf("title="));
                    node.label = this.get_next_string(substring3);
                    final int index2 = substring3.indexOf("url=");
                    if (index2 >= 0) {
                        node.URL = this.parse_url(this.get_next_string(substring3.substring(index2)));
                    }
                    if (this.mode == 1) {
                        node.expand = true;
                        node.allow_expand = true;
                        node.visible = true;
                    }
                    else {
                        node.expand = false;
                        node.allow_expand = true;
                        node.visible = true;
                    }
                    this.node_set.addElement(node);
                    vector.removeAllElements();
                    vector.addElement(new Integer(this.node_set.size() - 1));
                }
                else if (n8 != 0 && index >= 0) {
                    s2 = "<root title=\"mw6tech.com Jpathfinder applet demo\">";
                    final String substring4 = s2.substring(index + 5);
                    final String substring5 = substring4.substring(0, substring4.lastIndexOf(">"));
                    final Node node2 = new Node();
                    node2.parent = -1;
                    node2.level = 0;
                    final String substring6 = substring5.substring(substring5.indexOf("title="));
                    node2.label = this.get_next_string(substring6);
                    final int index3 = substring6.indexOf("url=");
                    if (index3 >= 0) {
                        node2.URL = this.parse_url(this.get_next_string(substring6.substring(index3)));
                    }
                    if (this.mode == 1) {
                        node2.expand = true;
                        node2.allow_expand = true;
                        node2.visible = true;
                    }
                    else {
                        node2.expand = false;
                        node2.allow_expand = true;
                        node2.visible = true;
                    }
                    this.node_set.addElement(node2);
                    vector.removeAllElements();
                    vector.addElement(new Integer(this.node_set.size() - 1));
                    n8 = 0;
                }
                final int index4 = s2.indexOf("<leaf ");
                if (index4 >= 0) {
                    final String substring7 = s2.substring(index4 + 5);
                    final String substring8 = substring7.substring(0, substring7.lastIndexOf(">"));
                    final Node node3 = new Node();
                    node3.level = vector.size();
                    if (node3.level == 1 && this.mode == 1) {
                        node3.visible = true;
                    }
                    node3.parent = vector.lastElement();
                    final String substring9 = substring8.substring(substring8.indexOf("title="));
                    node3.label = this.get_next_string(substring9);
                    final int index5 = substring9.indexOf("url=");
                    if (index5 >= 0) {
                        node3.URL = this.parse_url(this.get_next_string(substring9.substring(index5)));
                    }
                    this.node_set.addElement(node3);
                }
                final int index6 = s2.indexOf("<branch ");
                if (index6 >= 0) {
                    final String substring10 = s2.substring(index6 + 7);
                    final Node node4 = new Node();
                    node4.level = vector.size();
                    if (node4.level == 1 && this.mode == 1) {
                        node4.visible = true;
                    }
                    node4.parent = vector.lastElement();
                    final String substring11 = substring10.substring(substring10.indexOf("title="));
                    node4.label = this.get_next_string(substring11);
                    final int index7 = substring11.indexOf("url=");
                    if (index7 >= 0) {
                        node4.URL = this.parse_url(this.get_next_string(substring11.substring(index7)));
                    }
                    this.node_set.addElement(node4);
                    vector.addElement(new Integer(this.node_set.size() - 1));
                }
                if (s2.indexOf("</branch>") >= 0) {
                    vector.removeElementAt(vector.size() - 1);
                }
            }
        }
        catch (Exception ex4) {}
        for (int n9 = 0; n9 < this.node_set.size(); ++n9) {
            final Node node5 = this.node_set.elementAt(n9);
            if (n9 != this.node_set.size() - 1) {
                if (((Node)this.node_set.elementAt(n9 + 1)).parent == n9) {
                    node5.allow_expand = true;
                    this.node_set.setElementAt(node5, n9);
                }
                else if (node5.level == 0 && this.mode == 2) {
                    node5.end_root = true;
                }
            }
            else if (node5.level == 0 && this.mode == 2) {
                node5.end_root = true;
            }
        }
        this.base = this.font_asc;
        this.LINE_STEP += this.font_asc + this.font_des;
        this.calc_position();
        this.loading = false;
        this.repaint();
    }
    
    public boolean isInside(final Rectangle rectangle, final Point point) {
        final int x = point.x;
        final int y = point.y;
        return rectangle != null && (x >= rectangle.x - 3 && x <= rectangle.x + rectangle.width + 3 && y >= rectangle.y - 3 && y <= rectangle.y + rectangle.height + 3);
    }
    
    public void mouse_moved(final Event event) {
        this.mouse_point = new Point(event.x, event.y);
        for (int i = 0; i < this.node_set.size(); ++i) {
            final Node node = this.node_set.elementAt(i);
            if (node.mouse_over) {
                node.mouse_over = false;
                this.node_set.setElementAt(node, i);
            }
        }
        int n = -1;
        for (int j = 0; j < this.node_set.size(); j = this.get_next_index(j)) {
            final Node node2 = this.node_set.elementAt(j);
            if (this.isInside(node2.sensitive, this.mouse_point) && node2.visible) {
                node2.mouse_over = true;
                this.node_set.setElementAt(node2, j);
                n = j;
                break;
            }
        }
        for (int k = 0; k < this.node_set.size(); ++k) {
            if (k != n) {
                final Node node3 = this.node_set.elementAt(k);
                if (node3.highlight) {
                    node3.highlight = false;
                    this.node_set.setElementAt(node3, k);
                }
            }
        }
        this.repaint();
        this.validate();
    }
    
    public void mouse_pressed(final Event event) {
        final int x = event.x;
        final int y = event.y;
        this.mouse_point = new Point(x, y);
        for (int i = 0; i < this.node_set.size(); i = this.get_next_index(i)) {
            final Node node = this.node_set.elementAt(i);
            if (this.isInside(node.sensitive, this.mouse_point) && node.visible) {
                node.highlight = true;
                this.node_set.setElementAt(node, i);
                if (node.expand && node.allow_expand && !node.end_root) {
                    node.expand = false;
                    node.highlight = true;
                    this.node_set.setElementAt(node, i);
                    for (int j = i + 1; j < this.node_set.size(); ++j) {
                        final Node node2 = this.node_set.elementAt(j);
                        if (node2.parent == i) {
                            node2.visible = false;
                            node2.highlight = false;
                            this.node_set.setElementAt(node2, j);
                        }
                        if (node2.parent == node.parent) {
                            break;
                        }
                    }
                    this.calc_position();
                    if (!this.h_show) {
                        this.x_offset = 0;
                        this.calc_position();
                    }
                    if (!this.v_show) {
                        this.y_offset = 0;
                        this.calc_position();
                    }
                }
                else if (!node.expand && node.allow_expand && !node.end_root) {
                    node.expand = true;
                    node.highlight = true;
                    this.node_set.setElementAt(node, i);
                    for (int k = i + 1; k < this.node_set.size(); ++k) {
                        final Node node3 = this.node_set.elementAt(k);
                        if (node3.parent == i) {
                            node3.visible = true;
                            this.node_set.setElementAt(node3, k);
                        }
                        if (node3.parent == node.parent) {
                            break;
                        }
                    }
                    this.calc_position();
                    final int n = this.h - y - this.LINE_STEP;
                    if (n <= this.real_h - this.pre_real_h && i > 0) {
                        if (this.real_h - this.pre_real_h >= this.h) {
                            this.y_offset += -node.label_position.y;
                        }
                        else {
                            this.y_offset += -(this.real_h - this.pre_real_h - n + this.LINE_STEP / 2);
                        }
                        this.calc_position();
                    }
                }
                this.repaint();
                this.validate();
                if (node.URL != null) {
                    try {
                        this.getAppletContext().showDocument(new URL(node.URL), this.window);
                    }
                    catch (Exception ex) {}
                }
                return;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public String parse_url(String s) {
        if (s.indexOf("http://") >= 0 || s.indexOf("https://") >= 0) {
            return s;
        }
        URL codeBase = null;
        try {
            codeBase = this.getCodeBase();
        }
        catch (Exception ex) {}
        final String host = codeBase.getHost();
        String s2 = codeBase.getFile();
        s = this.remove_space(s);
        if (s.startsWith("/")) {
            return "http://" + host + s;
        }
        if (s.startsWith("../")) {
            for (int i = s.indexOf("../"); i >= 0; i = s.indexOf("../")) {
                s = s.substring(i + 3);
                s2 = s2.substring(0, s2.lastIndexOf("/"));
            }
            return "http://" + host + s2.substring(0, s2.lastIndexOf("/")) + "/" + s;
        }
        return "http://" + host + s2.substring(0, s2.lastIndexOf("/") + 1) + s;
    }
    
    public String remove_space(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                sb.append(char1);
            }
        }
        return new String(sb);
    }
    
    public void update(final Graphics graphics) {
        if (this.bufImg == null) {
            this.bufImg = this.createImage(this.w, this.h);
            (this.bufGraphics = this.bufImg.getGraphics()).setColor(this.bgcolor);
            this.bufGraphics.fillRect(0, 0, this.size().width, this.size().height);
        }
        else {
            this.bufGraphics.clearRect(0, 0, this.w, this.h);
            this.bufGraphics.setColor(this.bgcolor);
            this.bufGraphics.fillRect(0, 0, this.size().width, this.size().height);
        }
        for (int i = 0; i < this.node_set.size(); i = this.get_next_index(i)) {
            final Node node = this.node_set.elementAt(i);
            if (node.visible) {
                final int start_x = node.start_x;
                final int start_y = node.start_y;
                if (node.highlight) {
                    this.bufGraphics.setColor(this.hgcolor);
                    this.bufGraphics.fillRect(node.label_position.x - 1, node.label_position.y - 1, node.label_position.width + 1, node.label_position.height + 2);
                    this.DrawString(this.bufGraphics, this.pscolor, node.label, node.label_position.x, node.label_position.y + 2);
                }
                else {
                    this.DrawString(this.bufGraphics, this.fgcolor, node.label, node.label_position.x, node.label_position.y + 2);
                }
                if (node.mouse_over && !node.highlight) {
                    this.DrawString(this.bufGraphics, this.ovcolor, node.label, node.label_position.x, node.label_position.y + 2);
                    this.bufGraphics.setColor(this.ovcolor);
                    this.bufGraphics.drawRect(node.label_position.x - 1, node.label_position.y + node.label_position.height, node.label_position.width, 1);
                }
                this.bufGraphics.setColor(this.dtcolor);
                if (node.parent >= 0) {
                    boolean b = false;
                    for (int j = i + 1; j < this.node_set.size(); ++j) {
                        if (((Node)this.node_set.elementAt(j)).parent == i) {
                            b = true;
                            break;
                        }
                    }
                    int n = 0;
                    if (!b) {
                        n = 4;
                    }
                    for (int k = 2; k < (this.INDENT_STEP + n) / 2 - 1; ++k) {
                        this.bufGraphics.drawString(".", start_x + n - 2 * k + 4, start_y + 8 + 1);
                    }
                }
                if (this.tri_node) {
                    if (node.expand) {
                        this.bufGraphics.setColor(this.open_color);
                        this.bufGraphics.fillPolygon(node.open_position);
                    }
                    else if (node.allow_expand) {
                        this.bufGraphics.setColor(this.close_color);
                        this.bufGraphics.fillPolygon(node.close_position);
                    }
                    else {
                        this.bufGraphics.setColor(this.end_color);
                        this.bufGraphics.fillRect(node.end_position.x, node.end_position.y, node.end_position.width, node.end_position.height);
                    }
                }
                else if (node.expand) {
                    this.bufGraphics.setColor(this.close_color);
                    this.bufGraphics.drawRect(node.plus_minus.x, node.plus_minus.y, node.plus_minus.width, node.plus_minus.height);
                    this.bufGraphics.drawLine(node.plus_minus.x + 3, node.plus_minus.y + 5, node.plus_minus.x + 7, node.plus_minus.y + 5);
                }
                else if (node.allow_expand) {
                    this.bufGraphics.setColor(this.open_color);
                    this.bufGraphics.drawRect(node.plus_minus.x, node.plus_minus.y, node.plus_minus.width, node.plus_minus.height);
                    this.bufGraphics.drawLine(node.plus_minus.x + 3, node.plus_minus.y + 5, node.plus_minus.x + 7, node.plus_minus.y + 5);
                    this.bufGraphics.drawLine(node.plus_minus.x + 5, node.plus_minus.y + 3, node.plus_minus.x + 5, node.plus_minus.y + 7);
                }
                else {
                    this.bufGraphics.setColor(this.end_color);
                    this.bufGraphics.fillRect(node.end_position.x, node.end_position.y, node.end_position.width, node.end_position.height);
                }
                if (node.expand) {
                    int n2 = -1;
                    for (int l = i + 1; l < this.node_set.size(); ++l) {
                        final Node node2 = this.node_set.elementAt(l);
                        if (node2.parent == i) {
                            n2 = l;
                        }
                        if (node2.parent == node.parent) {
                            break;
                        }
                    }
                    int n3 = -1;
                    if (n2 > 0) {
                        n3 = this.node_set.elementAt(n2).start_y + 8 - 1;
                    }
                    final int n4 = start_y + 16 - 1;
                    this.bufGraphics.setColor(this.dtcolor);
                    for (int n5 = n4; n5 <= n3 + 2; n5 += 2) {
                        this.bufGraphics.drawString(".", start_x + 8 - 1, n5);
                    }
                }
            }
        }
        if (this.v_show && this.h_show) {
            this.bufGraphics.clearRect(this.w - this.bar_size - 1, this.h - this.bar_size - 1, this.bar_size, this.bar_size);
        }
        this.bufGraphics.setColor(this.bdcolor);
        this.bufGraphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.drawImage(this.bufImg, 0, 0, this);
    }
    
    class Node
    {
        public Rectangle label_position;
        public Polygon open_position;
        public Polygon close_position;
        public Rectangle end_position;
        public Rectangle plus_minus;
        public Rectangle sensitive;
        public int start_x;
        public int start_y;
        public int parent;
        public int level;
        public String label;
        public String URL;
        public boolean expand;
        public boolean allow_expand;
        public boolean visible;
        public boolean highlight;
        public boolean mouse_over;
        public boolean end_root;
        
        Node() {
            this.expand = false;
            this.allow_expand = false;
            this.visible = false;
            this.highlight = false;
            this.mouse_over = false;
            this.end_root = false;
        }
    }
}
