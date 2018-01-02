import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextField;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class reader extends Applet implements ActionListener, KeyListener, MouseListener, ItemListener
{
    String graph_file;
    Image graph;
    MediaTracker tracker;
    TextField T_Anchor;
    Button B_Anchor;
    Button B_Print_Matlab;
    Button B_Print_Latex;
    Button B_Print_HTML;
    Checkbox C_Show_Saved;
    Graphics applet_graphics;
    boolean show_saved;
    int basex;
    int basey;
    int graph_width;
    int graph_height;
    float graph_background_R;
    float graph_background_G;
    float graph_background_B;
    Color graph_background_color;
    String graph_fontname;
    static final int GRAPH_FONTSTYLE = 2;
    int graph_fontsize;
    Font graph_font;
    FontMetrics graph_fontmetrics;
    int pointer_size;
    int hollow_pointer_size_added;
    int hollow_pointer_size;
    float pointer_R;
    float pointer_G;
    float pointer_B;
    Color pointer_color;
    static final int POINTER_STEPSIZE_CONTORL = 10;
    static final int POINTER_STEPSIZE_SHIFT = 10;
    static final int POINTER_STEPSIZE_ALT = 5;
    static final int POINTER_STEPSIZE_META = 5;
    int curx;
    int cury;
    int nanchor;
    int[] anchorx;
    int[] anchory;
    double[] fanchorx;
    double[] fanchory;
    String[] anchorname;
    static final double tolerable_tiny = 1.0E-14;
    static final double tolerable_relative_error = 1.0E-4;
    double ax;
    double ay;
    double bx;
    double by;
    double det;
    int nsaved;
    static final int MAXSAVE = 512;
    int[] savex;
    int[] savey;
    double fcurx;
    double fcury;
    double[] fsavex;
    double[] fsavey;
    String status_mesg;
    static boolean already_been;
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.graph_file = this.getParameter("graph_file");
        if (this.graph_file.startsWith("http:") || this.graph_file.startsWith("HTTP:")) {
            this.graph = this.getImage(this.getDocumentBase(), this.graph_file);
        }
        else {
            this.graph = this.getToolkit().getImage(this.graph_file);
        }
        this.showStatus(this.status_mesg = "Loading " + this.graph_file + "...");
        System.out.println();
        System.out.println();
        System.out.println(this.status_mesg);
        this.tracker.addImage(this.graph, 0);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        this.add("South", panel);
        panel.setLayout(new FlowLayout());
        panel.add(this.B_Anchor = new Button(this.anchorname[this.nanchor]));
        this.B_Anchor.addActionListener(this);
        panel.add(this.T_Anchor = new TextField("0.  0. "));
        this.T_Anchor.addKeyListener(this);
        panel.add(this.B_Print_Matlab = new Button("MLB"));
        this.B_Print_Matlab.addActionListener(this);
        panel.add(this.B_Print_Latex = new Button("LTX"));
        this.B_Print_Latex.addActionListener(this);
        panel.add(this.B_Print_HTML = new Button("HTML"));
        this.B_Print_HTML.addActionListener(this);
        panel.add(this.C_Show_Saved = new Checkbox("Show saved", this.show_saved));
        this.C_Show_Saved.addItemListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.B_Anchor.setEnabled(false);
        this.T_Anchor.setEnabled(false);
        this.B_Print_Matlab.setEnabled(false);
        this.B_Print_Latex.setEnabled(false);
        this.B_Print_HTML.setEnabled(false);
        this.C_Show_Saved.setEnabled(false);
        final String parameter = this.getParameter("basex");
        if (parameter != null) {
            this.basex = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("basey");
        if (parameter2 != null) {
            this.basey = Integer.parseInt(parameter2);
        }
        this.basex += this.getBounds().x;
        this.basey += this.getBounds().y;
        final String parameter3 = this.getParameter("graph_width");
        if (parameter3 != null) {
            this.graph_width = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("graph_height");
        if (parameter4 != null) {
            this.graph_height = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("pointer_size");
        if (parameter5 != null) {
            this.pointer_size = Integer.parseInt(parameter5);
        }
        this.hollow_pointer_size = this.pointer_size + this.hollow_pointer_size_added;
        final String parameter6 = this.getParameter("pointer_R");
        if (parameter6 != null) {
            this.pointer_R = new Float(parameter6);
        }
        final String parameter7 = this.getParameter("pointer_G");
        if (parameter7 != null) {
            this.pointer_G = new Float(parameter7);
        }
        final String parameter8 = this.getParameter("pointer_B");
        if (parameter8 != null) {
            this.pointer_B = new Float(parameter8);
        }
        this.pointer_color = new Color(this.pointer_R, this.pointer_G, this.pointer_B);
        final String parameter9 = this.getParameter("graph_fontsize");
        if (parameter9 != null) {
            this.graph_fontsize = Integer.parseInt(parameter9);
        }
        final String parameter10 = this.getParameter("graph_fontname");
        if (parameter10 != null) {
            this.graph_fontname = parameter10;
        }
        this.graph_font = new Font(this.graph_fontname, 2, this.graph_fontsize);
        final String parameter11 = this.getParameter("graph_background_R");
        if (parameter11 != null) {
            this.graph_background_R = new Float(parameter11);
        }
        final String parameter12 = this.getParameter("graph_background_G");
        if (parameter12 != null) {
            this.graph_background_G = new Float(parameter12);
        }
        final String parameter13 = this.getParameter("graph_background_B");
        if (parameter13 != null) {
            this.graph_background_B = new Float(parameter13);
        }
        this.graph_background_color = new Color(this.graph_background_R, this.graph_background_G, this.graph_background_B);
        this.applet_graphics = this.getGraphics();
        this.graph_fontmetrics = this.applet_graphics.getFontMetrics(this.graph_font);
        try {
            this.tracker.waitForID(0);
            this.status_mesg = "Click in the graph. You will see your pointer.";
        }
        catch (Exception ex) {
            this.status_mesg = "Exception: " + ex.getMessage();
            System.out.println(this.status_mesg);
        }
        this.showStatus(this.status_mesg);
    }
    
    public void draw_pointer(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.fillPolygon(new int[] { n + n3, n, n - n3, n }, new int[] { n2, n2 + n3, n2, n2 - n3 }, 4);
    }
    
    public void draw_hollow_pointer(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.drawPolygon(new int[] { n + n3, n, n - n3, n }, new int[] { n2, n2 + n3, n2, n2 - n3 }, 4);
    }
    
    public boolean in_graph(final int n, final int n2) {
        return n >= this.basex && n2 >= this.basey && n <= this.basex + this.graph_width - 1 && n2 <= this.basey + this.graph_height - 1;
    }
    
    public void xorpaint_cur_pointer(final Graphics graphics) {
        graphics.setColor(this.graph_background_color);
        graphics.setXORMode(this.pointer_color);
        if (this.in_graph(this.curx, this.cury)) {
            this.draw_pointer(graphics, this.curx, this.cury, this.pointer_size);
        }
    }
    
    public void xorpaint_saved(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.graph_background_color);
        graphics.setXORMode(this.pointer_color);
        for (int i = n; i <= n2; ++i) {
            if (this.in_graph(this.savex[i], this.savey[i])) {
                this.draw_hollow_pointer(graphics, this.savex[i], this.savey[i], this.hollow_pointer_size);
                graphics.setFont(this.graph_font);
                final String string = Integer.toString(i + 1);
                graphics.drawString(string, this.savex[i] - this.graph_fontmetrics.stringWidth(string) / 2, this.savey[i] + this.graph_fontmetrics.getHeight() + this.hollow_pointer_size);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
        this.showStatus(this.status_mesg);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.graph_background_color);
        graphics.setPaintMode();
        graphics.fillRect(this.basex, this.basey, this.graph_width, this.graph_height);
        if (this.tracker.isErrorID(0)) {
            this.showStatus(this.status_mesg = "Bad image. Check by clicking the top-most link.");
            graphics.setFont(this.graph_font);
            graphics.setXORMode(this.pointer_color);
            graphics.drawString(this.status_mesg, this.basex + (this.graph_width - this.graph_fontmetrics.stringWidth(this.status_mesg)) / 2, this.basey + (this.graph_height + this.graph_fontmetrics.getHeight()) / 2);
        }
        else {
            graphics.drawImage(this.graph, this.basex, this.basey, this.graph_width, this.graph_height, this);
        }
        this.xorpaint_cur_pointer(graphics);
        if (this.show_saved) {
            this.xorpaint_saved(graphics, 0, this.nsaved - 1);
        }
    }
    
    public void move_pointer_to(final int curx, final int cury) {
        this.xorpaint_cur_pointer(this.applet_graphics);
        this.curx = curx;
        this.cury = cury;
        if (this.nanchor == 3) {
            final int n = this.curx - this.anchorx[0];
            final int n2 = this.cury - this.anchory[0];
            this.fcurx = (this.by * n - this.bx * n2) / this.det + this.fanchorx[0];
            this.fcury = (-this.ay * n + this.ax * n2) / this.det + this.fanchory[0];
            this.status_mesg = "s to save: " + this.fcurx + "  " + this.fcury;
            if (!this.in_graph(this.curx, this.cury)) {
                this.status_mesg = String.valueOf(this.status_mesg) + " (warning: point out of graph)";
            }
        }
        else {
            this.status_mesg = "Move the pointer around... press return to submit your anchor " + (this.nanchor + 1);
        }
        this.xorpaint_cur_pointer(this.applet_graphics);
        this.goto_command_mode();
    }
    
    public boolean calculate_frame() {
        final int n = this.anchorx[1] - this.anchorx[0];
        final int n2 = this.anchory[1] - this.anchory[0];
        final int n3 = this.anchorx[2] - this.anchorx[0];
        final int n4 = this.anchory[2] - this.anchory[0];
        final double n5 = (Math.abs(n) + Math.abs(n2) + Math.abs(n3) + Math.abs(n4)) / 4;
        if (n5 < this.pointer_size + 1) {
            return false;
        }
        if (Math.abs(n / n5 * n4 / n5 - n2 / n5 * n3 / n5) < 1.0E-4) {
            return false;
        }
        final double n6 = this.fanchorx[1] - this.fanchorx[0];
        final double n7 = this.fanchory[1] - this.fanchory[0];
        final double n8 = this.fanchorx[2] - this.fanchorx[0];
        final double n9 = this.fanchory[2] - this.fanchory[0];
        final double n10 = (Math.abs(n6) + Math.abs(n7) + Math.abs(n8) + Math.abs(n9)) / 4.0;
        if (n10 < 1.0E-14) {
            return false;
        }
        if (Math.abs(n6 / n10 * n9 / n10 - n7 / n10 * n8 / n10) < 1.0E-4) {
            return false;
        }
        final double n11 = n6 * n9 - n7 * n8;
        this.ax = (n * n9 - n3 * n7) / n11;
        this.ay = (n2 * n9 - n4 * n7) / n11;
        this.bx = (-n * n8 + n3 * n6) / n11;
        this.by = (-n2 * n8 + n4 * n6) / n11;
        this.det = this.ax * this.by - this.ay * this.bx;
        return true;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus(this.status_mesg);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        switch (mouseEvent.getModifiers()) {
            case 2:
            case 18: {
                this.move_pointer_to(this.curx - 1, this.cury);
            }
            case 6: {
                this.move_pointer_to(this.curx + 1, this.cury);
            }
            case 1:
            case 17: {
                this.move_pointer_to(this.curx, this.cury + 1);
            }
            case 5: {
                this.move_pointer_to(this.curx, this.cury - 1);
            }
            case 10: {
                this.move_pointer_to(this.curx, this.cury + 1);
            }
            case 9: {
                this.move_pointer_to(this.curx, this.cury - 1);
            }
            default: {
                this.move_pointer_to(mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    public void goto_command_mode() {
        this.showStatus(this.status_mesg);
        this.T_Anchor.setEnabled(false);
        this.B_Anchor.setEnabled(this.nanchor == 3);
        this.requestFocus();
    }
    
    public void goto_input_mode() {
        this.showStatus(this.status_mesg);
        this.B_Anchor.setEnabled(true);
        this.T_Anchor.setEnabled(true);
        this.T_Anchor.requestFocus();
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void anchor_button_pressed() {
        switch (this.nanchor) {
            case 0:
            case 1:
            case 2: {
                this.anchorx[this.nanchor] = this.curx;
                this.anchory[this.nanchor] = this.cury;
                try {
                    final String trim = this.T_Anchor.getText().trim();
                    final int index = trim.indexOf(" ");
                    this.fanchorx[this.nanchor] = Double.valueOf(trim.substring(0, index));
                    this.fanchory[this.nanchor] = Double.valueOf(trim.substring(index + 1));
                    this.status_mesg = "Anchor " + (this.nanchor + 1) + " set at " + this.fanchorx[this.nanchor] + " " + this.fanchory[this.nanchor] + ((this.nanchor == 2) ? ". Done! Click on" : ". More chicks...  [sic]");
                }
                catch (Exception ex) {
                    this.status_mesg = "Wrong input: re-input two numbers separated by space";
                    this.goto_input_mode();
                    return;
                }
                if (this.nanchor == 1) {
                    if (this.anchorx[1] == this.anchorx[0] && this.anchory[1] == this.anchory[0]) {
                        this.status_mesg = "Same as anchor 1 on screen: reset anchor 2";
                        this.goto_command_mode();
                        return;
                    }
                    if (this.fanchorx[1] == this.fanchorx[0] && this.fanchory[1] == this.fanchory[0]) {
                        this.status_mesg = "Same as anchor 1 in values: re-input anchor 2";
                        this.goto_input_mode();
                        return;
                    }
                }
                if (this.nanchor == 2) {
                    if (this.anchorx[2] == this.anchorx[0] && this.anchory[2] == this.anchory[0]) {
                        this.status_mesg = "Same as anchor 1 on screen: reset anchor 3";
                        this.goto_command_mode();
                        return;
                    }
                    if (this.fanchorx[2] == this.fanchorx[0] && this.fanchory[2] == this.fanchory[0]) {
                        this.status_mesg = "Same as anchor 1 in values: re-input anchor 3";
                        this.goto_input_mode();
                        return;
                    }
                    if (this.anchorx[2] == this.anchorx[1] && this.anchory[2] == this.anchory[1]) {
                        this.status_mesg = "Same as anchor 2 on screen: reset anchor 3";
                        this.goto_command_mode();
                        return;
                    }
                    if (this.fanchorx[2] == this.fanchorx[1] && this.fanchory[2] == this.fanchory[1]) {
                        this.status_mesg = "Same as anchor 2 in values: re-input anchor 3";
                        this.goto_input_mode();
                        return;
                    }
                    if (!this.calculate_frame()) {
                        this.nanchor = 0;
                        this.status_mesg = "Bad triple anchors: reset them all";
                        this.B_Anchor.setLabel(this.anchorname[this.nanchor]);
                        this.goto_command_mode();
                        return;
                    }
                }
                ++this.nanchor;
                this.B_Anchor.setLabel(this.anchorname[this.nanchor]);
                this.goto_command_mode();
            }
            case 3: {
                if (this.show_saved) {
                    this.xorpaint_saved(this.applet_graphics, 0, this.nsaved - 1);
                }
                this.nsaved = 0;
                this.nanchor = 0;
                this.B_Anchor.setLabel(this.anchorname[this.nanchor]);
                this.B_Print_Matlab.setEnabled(false);
                this.B_Print_Latex.setEnabled(false);
                this.B_Print_HTML.setEnabled(false);
                this.C_Show_Saved.setEnabled(false);
                this.status_mesg = "Move the pointer around... press return to submit your anchor " + (this.nanchor + 1);
                this.goto_command_mode();
            }
            default: {}
        }
    }
    
    public int pointer_stepsize(final KeyEvent keyEvent) {
        if (keyEvent.isControlDown()) {
            return 10;
        }
        if (keyEvent.isShiftDown()) {
            return 10;
        }
        if (keyEvent.isAltDown()) {
            return 5;
        }
        if (keyEvent.isMetaDown()) {
            return 5;
        }
        return 1;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() != this.T_Anchor) {
            return;
        }
        switch (keyEvent.getKeyCode()) {
            case 0:
            case 10: {}
            default: {
                this.showStatus(this.status_mesg = "[" + this.T_Anchor.getText() + "]");
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.T_Anchor) {
            switch (keyEvent.getKeyCode()) {
                case 10: {
                    this.anchor_button_pressed();
                    keyEvent.consume();
                }
                case 88: {
                    this.showStatus("x spy: " + this.status_mesg);
                    keyEvent.consume();
                }
                default: {}
            }
        }
        else {
            keyEvent.consume();
            switch (keyEvent.getKeyCode()) {
                case 10: {
                    if (this.nanchor != 3) {
                        this.status_mesg = "Now you can key in the values. Press return to submit";
                        if (reader.already_been) {
                            this.T_Anchor.setText("");
                        }
                        else {
                            reader.already_been = true;
                        }
                        this.goto_input_mode();
                        return;
                    }
                    this.showStatus(this.status_mesg = "Return is invalid now. To reset anchors, you must click the button");
                }
                case 83: {
                    if (this.nanchor == 3) {
                        if (this.nsaved >= 512) {
                            this.showStatus(this.status_mesg = "Too much saved (" + 512 + " rec.), reduce inventory please");
                            return;
                        }
                        this.savex[this.nsaved] = this.curx;
                        this.savey[this.nsaved] = this.cury;
                        this.fsavex[this.nsaved] = this.fcurx;
                        this.fsavey[this.nsaved] = this.fcury;
                        if (this.show_saved) {
                            this.xorpaint_saved(this.applet_graphics, this.nsaved, this.nsaved);
                        }
                        ++this.nsaved;
                        this.status_mesg = String.valueOf(this.fcurx) + "  " + this.fcury + " saved; total " + this.nsaved + " rec.";
                        if (this.nsaved == 1) {
                            this.B_Print_Matlab.setEnabled(true);
                            this.B_Print_Latex.setEnabled(true);
                            this.B_Print_HTML.setEnabled(true);
                            this.C_Show_Saved.setEnabled(true);
                        }
                    }
                    else {
                        this.status_mesg = "Cannot because the anchors are not yet set!";
                    }
                    this.showStatus(this.status_mesg);
                }
                case 68: {
                    if (this.nsaved == 0) {
                        this.status_mesg = "no records to be deleted";
                    }
                    else {
                        int n = -1;
                        double n2 = 1.0E20;
                        for (int i = 0; i < this.nsaved; ++i) {
                            final double n3 = (this.curx - this.savex[i]) * (this.curx - this.savex[i]) + (this.cury - this.savey[i]) * (this.cury - this.savey[i]);
                            if (n3 <= n2) {
                                n2 = n3;
                                n = i;
                            }
                        }
                        if (this.show_saved) {
                            this.xorpaint_saved(this.applet_graphics, n, this.nsaved - 1);
                        }
                        for (int j = n; j < this.nsaved - 1; ++j) {
                            this.savex[j] = this.savex[j + 1];
                            this.savey[j] = this.savey[j + 1];
                            this.fsavex[j] = this.fsavex[j + 1];
                            this.fsavey[j] = this.fsavey[j + 1];
                        }
                        --this.nsaved;
                        if (this.show_saved) {
                            this.xorpaint_saved(this.applet_graphics, n, this.nsaved - 1);
                        }
                        this.status_mesg = "1 rec. deleted, " + this.nsaved + " remaining";
                    }
                    if (this.nsaved == 0) {
                        this.B_Print_Matlab.setEnabled(false);
                        this.B_Print_Latex.setEnabled(false);
                        this.B_Print_HTML.setEnabled(false);
                        this.C_Show_Saved.setEnabled(false);
                    }
                    this.showStatus(this.status_mesg);
                }
                case 67: {
                    if (this.show_saved) {
                        this.xorpaint_saved(this.applet_graphics, 0, this.nsaved - 1);
                    }
                    this.nsaved = 0;
                    this.B_Print_Matlab.setEnabled(false);
                    this.B_Print_Latex.setEnabled(false);
                    this.B_Print_HTML.setEnabled(false);
                    this.C_Show_Saved.setEnabled(false);
                    this.showStatus(this.status_mesg = "all records deleted");
                }
                case 88: {
                    this.showStatus(this.status_mesg);
                }
                case 37: {
                    this.move_pointer_to(this.curx - this.pointer_stepsize(keyEvent), this.cury);
                }
                case 39: {
                    this.move_pointer_to(this.curx + this.pointer_stepsize(keyEvent), this.cury);
                }
                case 40: {
                    this.move_pointer_to(this.curx, this.cury + this.pointer_stepsize(keyEvent));
                }
                case 38: {
                    this.move_pointer_to(this.curx, this.cury - this.pointer_stepsize(keyEvent));
                }
                default: {}
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.B_Anchor) {
            this.anchor_button_pressed();
        }
        if (source == this.B_Print_Matlab) {
            this.print_in_Matlab();
        }
        if (source == this.B_Print_Latex) {
            this.print_in_Latex();
        }
        if (source == this.B_Print_HTML) {
            this.print_in_HTML();
        }
        this.goto_command_mode();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.xorpaint_saved(this.applet_graphics, 0, this.nsaved - 1);
        this.show_saved = !this.show_saved;
        this.goto_command_mode();
    }
    
    public void print_in_Matlab() {
        System.out.println("\n%% total " + this.nsaved + " rec. %%");
        for (int i = 0; i < this.nsaved; ++i) {
            System.out.println(String.valueOf(this.fsavex[i]) + " " + this.fsavey[i]);
        }
    }
    
    public void print_in_Latex() {
        int i = 0;
        String string = "{|l";
        String string2 = "No.";
        String string3 = "X";
        String string4 = "Y";
        while (i < this.nsaved) {
            string = String.valueOf(string) + "|c";
            string2 = String.valueOf(string2) + "& " + (i + 1);
            string3 = String.valueOf(string3) + "& " + this.fsavex[i];
            string4 = String.valueOf(string4) + "& " + this.fsavey[i];
            ++i;
        }
        final String string5 = String.valueOf(string) + "|}";
        final String string6 = String.valueOf(string2) + "\\\\";
        final String string7 = String.valueOf(string3) + "\\\\";
        final String string8 = String.valueOf(string4) + "\\\\";
        System.out.println("\n%% total " + this.nsaved + " rec. %%");
        System.out.println("\\documentstyle[fullpage,12pt]{article}");
        System.out.println("\\begin{document}");
        System.out.println("\\begin{center}");
        System.out.println("Table I. X-Y Plot\\\\");
        System.out.println("\\begin{tiny}");
        System.out.println("\\begin{tabular}" + string5 + "\\hline");
        System.out.println(string6);
        System.out.println("\\hline");
        System.out.println(string7);
        System.out.println("\\hline");
        System.out.println(string8);
        System.out.println("\\hline\\end{tabular}");
        System.out.println("\\end{tiny}");
        System.out.println("\\end{center}");
        System.out.println("\\end{document}");
    }
    
    public void print_in_HTML() {
        System.out.println("\n<!-- total " + this.nsaved + " rec. -->");
        System.out.println("<center><TABLE BORDER=1>");
        System.out.println("<TR><TH>#</TH><TH>X</TH><TH>Y</TH></TR>");
        for (int i = 0; i < this.nsaved; ++i) {
            System.out.println("<TR><TH>" + (i + 1) + "</TH><TH>" + this.fsavex[i] + "</TH><TH>" + this.fsavey[i] + "</TH></TR>");
        }
        System.out.println("</TABLE></center>");
    }
    
    public String getAppletInfo() {
        return "Graph Reader V2.0 (C) Ju Li (liju99@mit.edu)";
    }
    
    public reader() {
        this.show_saved = true;
        this.basex = 10;
        this.basey = 10;
        this.graph_width = 510;
        this.graph_height = 440;
        this.graph_background_R = 1.0f;
        this.graph_background_G = 1.0f;
        this.graph_background_B = 1.0f;
        this.graph_fontname = "TimesRoman";
        this.graph_fontsize = 12;
        this.pointer_size = 2;
        this.hollow_pointer_size_added = 1;
        this.pointer_R = 0.0f;
        this.pointer_G = 0.0f;
        this.pointer_B = 0.0f;
        this.curx = -10000;
        this.cury = -10000;
        this.anchorx = new int[3];
        this.anchory = new int[3];
        this.fanchorx = new double[3];
        this.fanchory = new double[3];
        this.anchorname = new String[] { "Set 1st anchor (x1 y1):", "Set 2nd anchor (x2 y2):", "Set 3rd anchor (x3 y3):", "Press again to reset" };
        this.savex = new int[512];
        this.savey = new int[512];
        this.fsavex = new double[512];
        this.fsavey = new double[512];
    }
}
