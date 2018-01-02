import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.List;
import java.util.Vector;
import java.awt.Label;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hypermapv6 extends Applet implements MouseListener, MouseMotionListener, ActionListener, ItemListener
{
    Image iI_map_image;
    int ii_height;
    int ii_width;
    Dimension iD_offDimension;
    Image iI_offImage;
    Graphics iG_offGraphics;
    Color iC_pgColor;
    Color iC_fgColor;
    Color iC_bgColor;
    String is_lb_one_URL;
    int ii_lb_init_x;
    int ii_lb_init_y;
    int ii_lb_width;
    int ii_lb_height;
    String is_text_color;
    String is_lb_background_color;
    String is_label_color;
    String is_button_colour;
    String is_lb_font_name;
    int ii_lb_font_size;
    Font iF_lb_font;
    int ii_tt_state;
    Color iC_tt_colour;
    String is_tt_font_name;
    int ii_tt_font_size;
    Font iF_tt_font;
    FontMetrics iFM_tt_font_metric;
    Label iLA_tt_label;
    int ii_applet_x;
    int ii_applet_y;
    hypermapareav6 iMA_area;
    hypermapareav6 iMA_area_locked;
    boolean ib_area_locked;
    hypermapareav6 iMA_area_mouse_last_over;
    Vector iV_areas;
    int ii_active_area;
    boolean ib_ol_flag;
    Color iC_ol_colour;
    Color iC_sel_ol_colour;
    String is_target_window;
    List iLB_list_box;
    Button iBT_list_box_button;
    Label iLA_list_box_label;
    int ii_random_number;
    int ii_random_count;
    
    public String getAppletInfo() {
        return "Hypermap.com\nCopyright 1999 - 2003 by Vincent Bell\nvincentbell@yahoo.com";
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this.ii_random_number = (int)Math.floor(Math.random() * 100.0);
        this.ii_random_number = 30;
        this.iLB_list_box = new List(10);
        (this.iBT_list_box_button = new Button("Unlock")).addActionListener(this);
        this.iLA_list_box_label = new Label();
        this.iLA_tt_label = new Label();
        this.setLayout(null);
        (this.iLB_list_box = new List(10)).addItemListener(this);
        this.add(this.iLB_list_box);
        this.add(this.iBT_list_box_button);
        this.add(this.iLA_list_box_label);
        this.add(this.iLA_tt_label);
        try {
            final String parameter = this.getParameter("mapimage");
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                this.prepareImage(this.iI_map_image = this.getImage(new URL(this.getDocumentBase(), stringTokenizer.nextToken())), this);
                if (stringTokenizer.hasMoreTokens()) {
                    this.iC_pgColor = this.getColorParm(stringTokenizer.nextToken());
                }
            }
        }
        catch (Exception ex) {}
        try {
            final String parameter2 = this.getParameter("mapimagewidth");
            if (parameter2 != null) {
                this.ii_width = Integer.parseInt(parameter2);
            }
        }
        catch (Exception ex2) {}
        try {
            final String parameter3 = this.getParameter("mapimageheight");
            if (parameter3 != null) {
                this.ii_height = Integer.parseInt(parameter3);
            }
        }
        catch (Exception ex3) {}
        this.setSize(this.ii_width, this.ii_height);
        try {
            final String parameter4 = this.getParameter("outline");
            if (parameter4 != null) {
                this.iC_ol_colour = this.getColorParm(parameter4);
                this.ib_ol_flag = true;
            }
        }
        catch (Exception ex4) {}
        try {
            final String parameter5 = this.getParameter("selectedoutline");
            if (parameter5 != null) {
                this.iC_sel_ol_colour = this.getColorParm(parameter5);
                this.ib_ol_flag = true;
            }
        }
        catch (Exception ex5) {}
        try {
            final String parameter6 = this.getParameter("lboneurl");
            if (parameter6 != null) {
                this.is_lb_one_URL = parameter6;
            }
        }
        catch (Exception ex6) {}
        try {
            final String parameter7 = this.getParameter("lbinitx");
            if (parameter7 != null) {
                this.ii_lb_init_x = Integer.parseInt(parameter7);
            }
        }
        catch (Exception ex7) {}
        try {
            final String parameter8 = this.getParameter("lbinity");
            if (parameter8 != null) {
                this.ii_lb_init_y = Integer.parseInt(parameter8);
            }
        }
        catch (Exception ex8) {}
        try {
            final String parameter9 = this.getParameter("lbwidth");
            if (parameter9 != null) {
                this.ii_lb_width = Integer.parseInt(parameter9);
            }
        }
        catch (Exception ex9) {}
        try {
            final String parameter10 = this.getParameter("lbheight");
            if (parameter10 != null) {
                this.ii_lb_height = Integer.parseInt(parameter10);
            }
        }
        catch (Exception ex10) {}
        try {
            final String parameter11 = this.getParameter("lbtextcolor");
            if (parameter11 != null) {
                this.is_text_color = parameter11;
            }
        }
        catch (Exception ex11) {}
        try {
            final String parameter12 = this.getParameter("lbfontname");
            if (parameter12 != null) {
                this.is_lb_font_name = parameter12;
            }
        }
        catch (Exception ex12) {}
        try {
            final String parameter13 = this.getParameter("lbfontsize");
            if (parameter13 != null) {
                this.ii_lb_font_size = Integer.parseInt(parameter13);
            }
        }
        catch (Exception ex13) {}
        try {
            final String parameter14 = this.getParameter("lbbackgroundcolor");
            if (parameter14 != null) {
                this.is_lb_background_color = parameter14;
            }
        }
        catch (Exception ex14) {}
        try {
            final String parameter15 = this.getParameter("labelcolor");
            if (parameter15 != null) {
                this.is_label_color = parameter15;
            }
        }
        catch (Exception ex15) {}
        try {
            final String parameter16 = this.getParameter("buttoncolor");
            if (parameter16 != null) {
                this.is_button_colour = parameter16;
            }
        }
        catch (Exception ex16) {}
        try {
            final String parameter17 = this.getParameter("tooltipson");
            if (parameter17 != null) {
                if (parameter17.equalsIgnoreCase("yes")) {
                    this.ii_tt_state = 1;
                }
                else {
                    this.ii_tt_state = 0;
                }
            }
        }
        catch (Exception ex17) {}
        try {
            final String parameter18 = this.getParameter("tooltipscolor");
            if (parameter18 != null) {
                this.iC_tt_colour = this.getColorParm(parameter18);
            }
        }
        catch (Exception ex18) {}
        try {
            final String parameter19 = this.getParameter("tooltipsfontname");
            if (parameter19 != null) {
                this.is_tt_font_name = parameter19;
            }
        }
        catch (Exception ex19) {}
        try {
            final String parameter20 = this.getParameter("tooltipsfontsize");
            if (parameter20 != null) {
                this.ii_tt_font_size = Integer.parseInt(parameter20);
            }
        }
        catch (Exception ex20) {}
        this.iLB_list_box.setBounds(this.ii_lb_init_x, this.ii_lb_init_y + 20, this.ii_lb_width, this.ii_lb_height);
        this.iLB_list_box.setForeground(this.getColorParm(this.is_text_color));
        this.iLB_list_box.setBackground(this.getColorParm(this.is_lb_background_color));
        this.iLB_list_box.setVisible(false);
        this.iBT_list_box_button.setName("iBT_list_box_button");
        this.iBT_list_box_button.setBounds(this.ii_lb_init_x + this.ii_lb_width - 60, this.ii_lb_init_y + 4, 40, 14);
        this.iBT_list_box_button.setForeground(new Color(0));
        this.iBT_list_box_button.setBackground(this.getColorParm(this.is_button_colour));
        this.iBT_list_box_button.setVisible(false);
        this.iLA_list_box_label.setBounds(this.ii_lb_init_x, this.ii_lb_init_y, this.ii_lb_width, 20);
        this.iLA_list_box_label.setForeground(new Color(0));
        this.iLA_list_box_label.setBackground(this.getColorParm(this.is_label_color));
        this.iLA_list_box_label.setVisible(true);
        this.iF_lb_font = new Font(this.is_lb_font_name, 0, this.ii_lb_font_size);
        this.iLB_list_box.setFont(this.iF_lb_font);
        this.iBT_list_box_button.setFont(this.iF_lb_font);
        this.iLA_list_box_label.setFont(this.iF_lb_font);
        this.iLA_tt_label.setForeground(new Color(0));
        this.iLA_tt_label.setBackground(this.iC_tt_colour);
        this.setBackground(this.iC_tt_colour);
        if (this.ii_tt_state == 1) {
            this.iLA_tt_label.setEnabled(true);
        }
        else {
            this.iLA_tt_label.setEnabled(false);
        }
        this.iF_tt_font = new Font(this.is_tt_font_name, 0, this.ii_tt_font_size);
        this.iFM_tt_font_metric = this.getFontMetrics(this.iF_tt_font);
        this.iLA_tt_label.setFont(this.iF_tt_font);
        String parameter21 = null;
        int n = 1;
        do {
            try {
                parameter21 = this.getParameter("shape-" + n);
                if (parameter21 != null) {
                    switch (n) {
                        default: {
                            this.getShape(parameter21);
                            String parameter22 = null;
                            int n2 = 1;
                            do {
                                try {
                                    parameter22 = this.getParameter("url-" + n + "-" + n2);
                                    final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter22, ",");
                                    final String nextToken = stringTokenizer2.nextToken();
                                    try {
                                        final URL url = new URL(this.getDocumentBase(), nextToken);
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            this.iMA_area.setURL(url, stringTokenizer2.nextToken());
                                        }
                                        else {
                                            this.iMA_area.setURL(url, "missing description in html");
                                        }
                                    }
                                    catch (MalformedURLException ex21) {}
                                }
                                catch (Exception ex22) {}
                                ++n2;
                            } while (parameter22 != null);
                            String parameter23 = null;
                            int n3 = 1;
                            do {
                                try {
                                    parameter23 = this.getParameter("text-" + n + "-" + n3);
                                    if (parameter23 != null) {
                                        final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter23, "|");
                                        String nextToken2;
                                        if (stringTokenizer3.countTokens() > 1) {
                                            Integer.parseInt(stringTokenizer3.nextToken());
                                            nextToken2 = stringTokenizer3.nextToken();
                                        }
                                        else {
                                            nextToken2 = parameter23;
                                        }
                                        this.iMA_area.addDescription(nextToken2);
                                    }
                                }
                                catch (Exception ex23) {}
                                ++n3;
                            } while (parameter23 != null);
                            String parameter24 = null;
                            int n4 = 1;
                            do {
                                try {
                                    parameter24 = this.getParameter("window-" + n + "-" + n4);
                                    if (parameter24 != null) {
                                        final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter24, "|");
                                        String nextToken3;
                                        if (stringTokenizer4.countTokens() > 1) {
                                            Integer.parseInt(stringTokenizer4.nextToken());
                                            nextToken3 = stringTokenizer4.nextToken();
                                        }
                                        else {
                                            nextToken3 = parameter24;
                                        }
                                        this.iMA_area.addTarget(nextToken3);
                                    }
                                }
                                catch (Exception ex24) {}
                                ++n4;
                            } while (parameter24 != null);
                            this.iMA_area.sortIt();
                            this.iV_areas.addElement(this.iMA_area);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex25) {}
            ++n;
        } while (parameter21 != null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.iV_areas.trimToSize();
        this.ib_area_locked = false;
        this.ii_active_area = 0;
        this.msdwn();
    }
    
    private void moveListBox(final hypermapareav6 hypermapareav6) {
        this.iLB_list_box.setBounds(hypermapareav6.getlbx(), hypermapareav6.getlby() + 20, this.ii_lb_width, this.ii_lb_height - 20);
        this.repaint();
        this.iBT_list_box_button.setBounds(hypermapareav6.getlbx() + this.ii_lb_width - 44, hypermapareav6.getlby() + 4, 40, 14);
        this.repaint();
        this.iLA_list_box_label.setBounds(hypermapareav6.getlbx(), hypermapareav6.getlby(), this.ii_lb_width, 20);
        this.repaint();
    }
    
    private void moveToolTip(final hypermapareav6 hypermapareav6, final int n, final int n2) {
        if (this.ii_active_area >= 0) {
            this.iLA_tt_label.setVisible(true);
            this.iLA_tt_label.setText(hypermapareav6.getDescription());
            this.iLA_tt_label.setBounds(hypermapareav6.getlabelx(this.iFM_tt_font_metric.stringWidth(String.valueOf(hypermapareav6.getDescription()) + 5), this.ii_width), hypermapareav6.getlabely(), this.iFM_tt_font_metric.stringWidth(String.valueOf(hypermapareav6.getDescription()) + 5), this.iFM_tt_font_metric.getHeight());
            return;
        }
        this.iLA_tt_label.setVisible(false);
    }
    
    private Color getColorParm(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (s.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (s.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return new Color(255, 176, 32);
        }
        if (s.length() == 7 && s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return Color.black;
    }
    
    private void addHyperhmaparea161401() {
        final int n = 2;
        final int n2 = 2;
        this.iMA_area = new hypermapareav6(new Rectangle(n, n2, this.ii_width - 4 - n, this.ii_height - 4 - n2), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, "right", "bottom", "left", "right");
        try {
            this.iMA_area.setURL(new URL(this.getDocumentBase(), "http://www.bbc.co.uk"), "BBC");
            this.iMA_area.setURL(new URL(this.getDocumentBase(), "http://www.itn.co.uk"), "ITN");
            this.iMA_area.setURL(new URL(this.getDocumentBase(), "http://www.cnn.com"), "CNN");
        }
        catch (Exception ex) {}
        this.iMA_area.addDescription("About © hypermap.com");
        this.iMA_area.addTarget("About © hypermap.com");
        this.iV_areas.addElement(this.iMA_area);
    }
    
    private void getShapeSample1() {
        final int n = 203;
        final int n2 = 38;
        this.iMA_area = new hypermapareav6(new Rectangle(n, n2, 223 - n, 56 - n2), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, "bottom", "left", "top", "right");
    }
    
    private void getShapeSample2() {
        final int n = 252;
        final int n2 = 61;
        this.iMA_area = new hypermapareav6(new Rectangle(n, n2, 281 - n, 86 - n2), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, "left", "right", "top", "bottom");
    }
    
    private void getShapeSample3() {
        final int n = 187;
        final int n2 = 26;
        this.iMA_area = new hypermapareav6(new Rectangle(n, n2, 280 - n, 70 - n2), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, "left", "right", "top", "bottom");
    }
    
    private void getShape(final String s) {
        String nextToken = "";
        String nextToken2 = "";
        String nextToken3 = "";
        String nextToken4 = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String nextToken5 = stringTokenizer.nextToken();
        if (nextToken5.equalsIgnoreCase("rect")) {
            final String nextToken6 = stringTokenizer.nextToken();
            final String nextToken7 = stringTokenizer.nextToken();
            final String nextToken8 = stringTokenizer.nextToken();
            final String nextToken9 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            this.iMA_area = new hypermapareav6(new Rectangle(int1, int2, Integer.parseInt(stringTokenizer.nextToken()) - int1, Integer.parseInt(stringTokenizer.nextToken()) - int2), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, nextToken6, nextToken7, nextToken8, nextToken9);
            return;
        }
        if (nextToken5.equalsIgnoreCase("circle")) {
            final String nextToken10 = stringTokenizer.nextToken();
            final String nextToken11 = stringTokenizer.nextToken();
            final String nextToken12 = stringTokenizer.nextToken();
            final String nextToken13 = stringTokenizer.nextToken();
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken());
            final int int5 = Integer.parseInt(stringTokenizer.nextToken());
            this.iMA_area = new hypermapareav6(int3, int4, int5, int5, this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, nextToken10, nextToken11, nextToken12, nextToken13);
            return;
        }
        if (nextToken5.equalsIgnoreCase("ellipse")) {
            this.iMA_area = new hypermapareav6(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, stringTokenizer.nextToken(), stringTokenizer.nextToken(), stringTokenizer.nextToken(), stringTokenizer.nextToken());
            return;
        }
        if (nextToken5.equalsIgnoreCase("poly")) {
            final Polygon polygon = new Polygon();
            while (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
                nextToken2 = stringTokenizer.nextToken();
                nextToken3 = stringTokenizer.nextToken();
                nextToken4 = stringTokenizer.nextToken();
                polygon.addPoint(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            this.iMA_area = new hypermapareav6(polygon, this.ii_width, this.ii_height, this.ii_lb_width, this.ii_lb_height, nextToken, nextToken2, nextToken3, nextToken4);
        }
    }
    
    private void msdwn() {
        if (this.ii_active_area >= 0) {
            this.iLB_list_box.setVisible(true);
            this.ib_area_locked = true;
            this.iMA_area = this.iV_areas.elementAt(this.ii_active_area);
            this.iMA_area_locked = this.iMA_area;
            this.iLA_list_box_label.setText(this.iMA_area_locked.getDescription());
            this.iLA_list_box_label.setVisible(true);
            this.iBT_list_box_button.setLabel(".");
            this.iBT_list_box_button.setVisible(true);
            this.iLB_list_box.removeAll();
            this.moveListBox(this.iMA_area_locked);
            this.repaint();
            final Vector getareaurlvector = this.iMA_area.getareaurlvector();
            for (int i = 0; i < getareaurlvector.size(); ++i) {
                this.iLB_list_box.addItem(getareaurlvector.elementAt(i).getDescription());
            }
            this.iLB_list_box.validate();
            this.iLA_list_box_label.validate();
            this.iBT_list_box_button.validate();
            this.iLA_tt_label.setVisible(false);
            if (this.iMA_area.getareaurlvector().size() == 1 && this.is_lb_one_URL.equalsIgnoreCase("yes")) {
                this.showurl(1);
            }
            this.repaint();
        }
    }
    
    public hypermapareav6 selectedArea() {
        if (this.ib_area_locked) {
            return this.iMA_area_locked;
        }
        if (this.ii_active_area >= 0) {
            return this.iV_areas.elementAt(this.ii_active_area);
        }
        return this.iMA_area_mouse_last_over;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void showurl(final int n) {
        int selectedIndex;
        if (n == 1) {
            selectedIndex = 0;
        }
        else {
            selectedIndex = this.iLB_list_box.getSelectedIndex();
        }
        final hypermapareav6 selectedArea = this.selectedArea();
        final URL url = selectedArea.getareaurlvector().elementAt(selectedIndex).getURL();
        this.is_target_window = selectedArea.getTarget();
        if (this.is_target_window.equalsIgnoreCase("current")) {
            this.getAppletContext().showDocument(url);
            return;
        }
        this.getAppletContext().showDocument(url, this.is_target_window);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.iG_offGraphics == null || size.width != this.iD_offDimension.width || size.height != this.iD_offDimension.height) {
            this.iD_offDimension = size;
            this.iI_offImage = this.createImage(size.width, size.height);
            this.iG_offGraphics = this.iI_offImage.getGraphics();
        }
        if ((this.checkImage(this.iI_map_image, this) & 0x20) == 0x20) {
            this.iG_offGraphics.setColor(Color.blue);
            this.iG_offGraphics.fillRect(0, 0, size.width, size.height);
            this.iG_offGraphics.drawImage(this.iI_map_image, 0, 0, this);
            if (this.ii_active_area >= 0) {
                (this.iMA_area = this.iV_areas.elementAt(this.ii_active_area)).getBoundingBox();
                if (this.ib_ol_flag) {
                    this.iG_offGraphics.setColor(this.iC_ol_colour);
                    if (this.iMA_area.isRect()) {
                        final Rectangle rect = this.iMA_area.getRect();
                        this.iG_offGraphics.setColor(this.iC_ol_colour);
                        this.iG_offGraphics.drawRect(rect.x, rect.y, rect.width, rect.height);
                        this.iG_offGraphics.setColor(this.iC_ol_colour);
                        this.iG_offGraphics.drawRect(rect.x - 1, rect.y - 1, rect.width + 2, rect.height + 2);
                        this.iG_offGraphics.drawRect(rect.x + 1, rect.y + 1, rect.width - 2, rect.height - 2);
                        this.iG_offGraphics.drawRect(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 4);
                    }
                    else if (this.iMA_area.isEllipse()) {
                        final Rectangle ellipse = this.iMA_area.getEllipse();
                        this.iG_offGraphics.drawOval(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
                    }
                    else if (this.iMA_area.isPoly()) {
                        final Polygon poly = this.iMA_area.getPoly();
                        this.iG_offGraphics.drawPolygon(poly);
                        final int n = poly.npoints - 1;
                        this.iG_offGraphics.drawLine(poly.xpoints[n], poly.ypoints[n], poly.xpoints[0], poly.ypoints[0]);
                    }
                }
            }
            if (this.ib_area_locked && this.ib_ol_flag) {
                this.iG_offGraphics.setColor(this.iC_sel_ol_colour);
                if (this.iMA_area_locked.isRect()) {
                    final Rectangle rect2 = this.iMA_area_locked.getRect();
                    this.iG_offGraphics.setColor(this.iC_ol_colour);
                    this.iG_offGraphics.drawRect(rect2.x - 1, rect2.y - 1, rect2.width + 2, rect2.height + 2);
                    this.iG_offGraphics.drawRect(rect2.x, rect2.y, rect2.width, rect2.height);
                    this.iG_offGraphics.setColor(this.iC_sel_ol_colour);
                    this.iG_offGraphics.drawRect(rect2.x + 1, rect2.y + 1, rect2.width - 2, rect2.height - 2);
                    this.iG_offGraphics.drawRect(rect2.x + 2, rect2.y + 2, rect2.width - 4, rect2.height - 4);
                }
                else if (this.iMA_area_locked.isEllipse()) {
                    final Rectangle ellipse2 = this.iMA_area_locked.getEllipse();
                    this.iG_offGraphics.drawOval(ellipse2.x, ellipse2.y, ellipse2.width, ellipse2.height);
                }
                else if (this.iMA_area_locked.isPoly()) {
                    final Polygon poly2 = this.iMA_area_locked.getPoly();
                    this.iG_offGraphics.drawPolygon(poly2);
                    final int n2 = poly2.npoints - 1;
                    this.iG_offGraphics.drawLine(poly2.xpoints[n2], poly2.ypoints[n2], poly2.xpoints[0], poly2.ypoints[0]);
                }
            }
            final int n3 = (int)Math.floor(Math.random() * 256.0);
            final int n4 = (int)Math.floor(Math.random() * 256.0);
            final int n5 = (int)Math.floor(Math.random() * 256.0);
            final int n6 = (int)Math.floor(Math.random() * this.getSize().width);
            final int n7 = (int)Math.floor(Math.random() * this.getSize().height);
            this.iG_offGraphics.setColor(new Color(153, 0, 0));
            this.iG_offGraphics.drawString("© hypermap.com", 10, 15);
            this.iG_offGraphics.setColor(new Color(n3, n4, n5));
            this.iG_offGraphics.drawString("hypermap.com", n6, n7);
            ++this.ii_random_count;
            if (this.ii_random_count >= this.ii_random_number && this.ii_random_count <= this.ii_random_number + 10) {
                this.iG_offGraphics.setColor(new Color(153, 0, 0));
                this.iG_offGraphics.fill3DRect(5, 5, 125, 35, true);
                this.iG_offGraphics.setColor(Color.black);
                this.iG_offGraphics.drawString("© hypermap.com", 20, 25);
            }
            graphics.drawImage(this.iI_offImage, 0, 0, this);
            return;
        }
        this.iG_offGraphics.setColor(this.iC_bgColor);
        this.iG_offGraphics.fillRect(0, 0, size.width, size.height);
        this.iG_offGraphics.setFont(this.iF_tt_font);
        this.iG_offGraphics.setColor(this.iC_fgColor);
        this.iG_offGraphics.drawString("© hypermap.com", this.ii_width / 2, this.ii_height / 2);
        this.iG_offGraphics.drawString("hypermap loaded. Loading image...", this.ii_width / 2 + 15, this.ii_height / 2 + 15);
        graphics.drawImage(this.iI_offImage, 0, 0, this);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int ii_active_area = this.ii_active_area;
        this.ii_active_area = -1;
        for (int i = this.iV_areas.size() - 1; i >= 0; --i) {
            this.iMA_area = (hypermapareav6)this.iV_areas.elementAt(i);
            if (this.iMA_area.inside(mouseEvent.getX(), mouseEvent.getY())) {
                this.ii_active_area = i;
            }
        }
        if (this.ii_active_area >= 0 && this.ii_active_area != ii_active_area) {
            this.moveToolTip(this.iMA_area = this.iV_areas.elementAt(this.ii_active_area), mouseEvent.getX(), mouseEvent.getY());
            this.iLA_tt_label.setVisible(true);
            this.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.msdwn();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.ii_active_area = -1;
        this.iLA_tt_label.setVisible(false);
        this.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.iBT_list_box_button.getLabel().equalsIgnoreCase(".")) {
            this.iLB_list_box.setVisible(false);
            this.iBT_list_box_button.setLabel("..");
            this.iLB_list_box.setBounds(this.ii_lb_init_x, this.ii_lb_init_y + 20, this.ii_lb_width + 2, this.ii_lb_height - 20);
            this.repaint();
            this.iBT_list_box_button.setBounds(this.ii_lb_init_x + this.ii_lb_width - 44, this.ii_lb_init_y + 4, 40, 14);
            this.iLA_list_box_label.setBounds(this.ii_lb_init_x, this.ii_lb_init_y, this.ii_lb_width, 20);
            this.repaint();
            return;
        }
        this.iBT_list_box_button.setLabel(".");
        this.moveListBox(this.iMA_area_locked);
        this.iLB_list_box.setVisible(true);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.showurl(0);
    }
    
    public hypermapv6() {
        this.iC_pgColor = Color.yellow;
        this.iC_fgColor = Color.yellow;
        this.iC_bgColor = Color.black;
        this.is_lb_one_URL = "";
        this.ii_lb_width = 180;
        this.ii_lb_height = 80;
        this.is_text_color = "yellow";
        this.is_lb_background_color = "yellow";
        this.is_label_color = "yellow";
        this.is_button_colour = "yellow";
        this.is_lb_font_name = "Serif";
        this.ii_lb_font_size = 12;
        this.is_tt_font_name = "Serif";
        this.ii_tt_font_size = 12;
        this.ii_applet_x = 150;
        this.ii_applet_y = 150;
        this.ib_area_locked = false;
        this.iV_areas = new Vector(10, 5);
        this.ii_active_area = -1;
        this.ib_ol_flag = false;
        this.iC_ol_colour = Color.yellow;
        this.iC_sel_ol_colour = Color.red;
    }
}
