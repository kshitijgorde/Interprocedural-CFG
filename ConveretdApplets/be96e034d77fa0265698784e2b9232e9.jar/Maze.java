import java.awt.Event;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Maze extends Applet
{
    static final int view_width = 400;
    static final int view_height = 400;
    int zscale;
    static final int map_unit = 128;
    static final int view_offset = 16;
    static final int step_size = 32;
    RangeSet rset;
    Graphics gc;
    Image buffer_img;
    int state;
    static final int STATE_TITLE = 1;
    static final int STATE_GENERATING = 2;
    static final int STATE_PLAY = 3;
    static final int STATE_FINISH = 4;
    public int percentdone;
    boolean showMaze;
    boolean showSolution;
    boolean solving;
    final int viewz = 50;
    int viewx;
    int viewy;
    int ang;
    int dx;
    int dy;
    int px;
    int py;
    int walk_step;
    int view_dx;
    int view_dy;
    int mazew;
    int mazeh;
    boolean deepdebug;
    boolean all_visible;
    boolean new_game;
    int[][] mazecells;
    int[][] mazedists;
    int[][] seencells;
    BSPNode bsp_root;
    boolean map_mode;
    int map_scale;
    int traverse_node_ct;
    int traverse_ssector_ct;
    int drawrect_ct;
    int drawrect_late_ct;
    int drawrect_segment_ct;
    int nesting;
    public static int[] masks;
    public MazeBuilder mazebuilder;
    final int ESCAPE = 27;
    Font smallBannerFont;
    Font largeBannerFont;
    static int[] skill_x;
    static int[] skill_y;
    static int[] skill_rooms;
    static int[] skill_partct;
    
    public Maze() {
        this.zscale = 200;
        this.percentdone = 0;
        this.deepdebug = false;
        this.all_visible = false;
        this.new_game = false;
        this.nesting = 0;
    }
    
    public void NewMaze(final BSPNode bsp_root, final int[][] mazecells, final int[][] mazedists, final int px, final int py) {
        final boolean showMaze = false;
        this.solving = showMaze;
        this.showSolution = showMaze;
        this.showMaze = showMaze;
        this.mazecells = mazecells;
        this.mazedists = mazedists;
        this.seencells = new int[this.mazew + 1][this.mazeh + 1];
        this.bsp_root = bsp_root;
        this.dx = 1;
        this.dy = 0;
        this.px = px;
        this.py = py;
        this.walk_step = 0;
        this.view_dx = this.dx << 16;
        this.view_dy = this.dy << 16;
        this.ang = 0;
        this.map_mode = false;
        this.map_scale = 10;
        this.state = 3;
        this.redraw();
        this.repaint();
    }
    
    void BuildInterrupted() {
        this.state = 1;
        this.redraw();
        this.mazebuilder = null;
    }
    
    final int viewd_unscale(final int n) {
        return n >> 16;
    }
    
    final double radify(final int n) {
        return n * 3.141592653589793 / 180.0;
    }
    
    boolean clipt(final int n, final int n2, final FloatPair floatPair) {
        if (n > 0) {
            final double p3 = n2 * 1.0 / n;
            if (p3 > floatPair.p2) {
                return false;
            }
            if (p3 > floatPair.p1) {
                floatPair.p1 = p3;
            }
        }
        else if (n < 0) {
            final double p4 = n2 * 1.0 / n;
            if (p4 < floatPair.p1) {
                return false;
            }
            if (p4 < floatPair.p2) {
                floatPair.p2 = p4;
            }
        }
        else if (n2 > 0) {
            return false;
        }
        return true;
    }
    
    boolean clip3d(final RangePair rangePair) {
        final int x1 = rangePair.x1;
        final int z1 = rangePair.z1;
        final int x2 = rangePair.x2;
        final int z2 = rangePair.z2;
        if (z1 > -4 && z2 > -4) {
            return false;
        }
        if (x1 > -z1 && x2 > -z2) {
            return false;
        }
        if (-x1 > -z1 && -x2 > -z2) {
            return false;
        }
        final int n = x2 - x1;
        final int n2 = z2 - z1;
        final FloatPair floatPair = new FloatPair(0.0, 1.0);
        if (!this.clipt(-n - n2, x1 + z1, floatPair)) {
            return false;
        }
        if (!this.clipt(n - n2, -x1 + z1, floatPair)) {
            return false;
        }
        if (!this.clipt(-n2, z1 - 4, floatPair)) {
            return false;
        }
        if (floatPair.p2 < 1.0) {
            rangePair.x2 = (int)(x1 + floatPair.p2 * n);
            rangePair.z2 = (int)(z1 + floatPair.p2 * n2);
        }
        if (floatPair.p1 > 0.0) {
            rangePair.x1 += (int)(floatPair.p1 * n);
            rangePair.z1 += (int)(floatPair.p1 * n2);
        }
        return true;
    }
    
    void drawrect(final Seg seg, int n, int n2, int n3, int n4) {
        int n5 = 0;
        int n6 = 100;
        ++this.drawrect_ct;
        n -= this.viewx;
        n2 -= this.viewy;
        n5 -= 50;
        n3 -= this.viewx;
        n4 -= this.viewy;
        n6 -= 50;
        final int n7 = -n5;
        final int n8 = -this.viewd_unscale(this.view_dy * n - this.view_dx * n2);
        final int n9 = -this.viewd_unscale(this.view_dx * n + this.view_dy * n2);
        final int n10 = -n6;
        final RangePair rangePair = new RangePair(n8, n9, -this.viewd_unscale(this.view_dy * n3 - this.view_dx * n4), -this.viewd_unscale(this.view_dx * n3 + this.view_dy * n4));
        if (!this.clip3d(rangePair)) {
            return;
        }
        final int n11 = n7 * this.zscale / rangePair.z1 + 200;
        final int n12 = n10 * this.zscale / rangePair.z1 + 200;
        final int n13 = n7 * this.zscale / rangePair.z2 + 200;
        final int n14 = n10 * this.zscale / rangePair.z2 + 200;
        final int n15 = rangePair.x1 * this.zscale / rangePair.z1 + 200;
        final int n16 = rangePair.x2 * this.zscale / rangePair.z2 + 200;
        if (n15 >= n16) {
            return;
        }
        int i = n15;
        final int n17 = n16 - n15;
        this.gc.setColor(seg.col);
        boolean b = false;
        ++this.drawrect_late_ct;
        while (i <= n16) {
            final Point point = new Point(i, n16);
            if (!this.rset.intersect(point)) {
                break;
            }
            final int x = point.x;
            final int y = point.y;
            this.gc.fillPolygon(new int[] { x, x, y + 1, y + 1 }, new int[] { n11 + (x - n15) * (n13 - n11) / n17, n12 + (x - n15) * (n14 - n12) / n17 + 1, n14 + (y - n16) * (n14 - n12) / n17 + 1, n13 + (y - n16) * (n13 - n11) / n17 }, 4);
            b = true;
            this.rset.remove(x, y);
            i = y + 1;
            ++this.drawrect_segment_ct;
        }
        if (b && !seg.seen) {
            int n18 = seg.x / 128;
            int n19 = seg.y / 128;
            final int n20 = seg.dx / 128;
            final int n21 = seg.dy / 128;
            final int sign = MazeBuilder.getSign(n20);
            final int sign2 = MazeBuilder.getSign(n21);
            final int abs = Math.abs(n20 + n21);
            final int n22 = (n20 != 0) ? 1 : 4;
            if (n20 < 0) {
                --n18;
            }
            if (n21 < 0) {
                --n19;
            }
            seg.seen = true;
            for (int j = 0; j != abs; ++j) {
                final int[] array = this.seencells[n18];
                final int n23 = n19;
                array[n23] |= n22;
                n18 += sign;
                n19 += sign2;
            }
        }
    }
    
    void redraw() {
        this.gc = this.buffer_img.getGraphics();
        switch (this.state) {
            case 1: {
                this.redraw_title(this.gc);
                break;
            }
            case 2: {
                this.redraw_generating(this.gc);
                break;
            }
            case 3: {
                this.redraw_play(this.gc);
                break;
            }
            case 4: {
                this.redraw_finish(this.gc);
                break;
            }
        }
        this.update(this.getGraphics());
    }
    
    public void centerString(final Graphics graphics, final FontMetrics fontMetrics, final String s, final int n) {
        graphics.drawString(s, (400 - fontMetrics.stringWidth(s)) / 2, n);
    }
    
    void redraw_title(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 400, 400);
        graphics.setFont(this.largeBannerFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.red);
        this.centerString(graphics, fontMetrics, "MAZE", 100);
        graphics.setColor(Color.blue);
        graphics.setFont(this.smallBannerFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        this.centerString(graphics, fontMetrics2, "by Paul Falstad", 160);
        this.centerString(graphics, fontMetrics2, "www.falstad.com", 190);
        graphics.setColor(Color.black);
        this.centerString(graphics, fontMetrics2, "To start, select a skill level.", 250);
        this.centerString(graphics, fontMetrics2, "(Press a number from 0 to 9,", 300);
        this.centerString(graphics, fontMetrics2, "or a letter from A to F)", 320);
        this.centerString(graphics, fontMetrics2, "v1.2", 350);
    }
    
    void redraw_finish(final Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, 400, 400);
        graphics.setFont(this.largeBannerFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.yellow);
        this.centerString(graphics, fontMetrics, "You won!", 100);
        graphics.setColor(Color.orange);
        graphics.setFont(this.smallBannerFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        this.centerString(graphics, fontMetrics2, "Congratulations!", 160);
        graphics.setColor(Color.white);
        this.centerString(graphics, fontMetrics2, "Hit any key to restart", 300);
    }
    
    void redraw_generating(final Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillRect(0, 0, 400, 400);
        graphics.setFont(this.largeBannerFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.red);
        this.centerString(graphics, fontMetrics, "Building maze", 150);
        graphics.setFont(this.smallBannerFont);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        graphics.setColor(Color.black);
        this.centerString(graphics, fontMetrics2, this.percentdone + "% completed", 200);
        this.centerString(graphics, fontMetrics2, "Hit escape to stop", 300);
    }
    
    void redraw_play(final Graphics graphics) {
        this.viewx = this.px * 128 + 64;
        this.viewx += this.viewd_unscale(this.view_dx * (32 * this.walk_step - 16));
        this.viewy = this.py * 128 + 64;
        this.viewy += this.viewd_unscale(this.view_dy * (32 * this.walk_step - 16));
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 400, 200);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, 200, 400, 200);
        graphics.setColor(Color.white);
        this.rset.set(0, 399);
        final boolean traverse_node_ct = false;
        this.drawrect_segment_ct = (traverse_node_ct ? 1 : 0);
        this.drawrect_late_ct = (traverse_node_ct ? 1 : 0);
        this.drawrect_ct = (traverse_node_ct ? 1 : 0);
        this.traverse_ssector_ct = (traverse_node_ct ? 1 : 0);
        this.traverse_node_ct = (traverse_node_ct ? 1 : 0);
        this.traverse_node(this.bsp_root);
        if (this.map_mode) {
            this.draw_map(graphics);
        }
    }
    
    public void draw_map(final Graphics graphics) {
        graphics.setColor(Color.white);
        final int n = this.px * 128 + 64 + this.viewd_unscale(this.view_dx * (32 * this.walk_step));
        final int n2 = this.py * 128 + 64 + this.viewd_unscale(this.view_dy * (32 * this.walk_step));
        final int n3 = -n * this.map_scale / 128 + 200;
        final int n4 = -n2 * this.map_scale / 128 + 200;
        int n5 = -n3 / this.map_scale;
        int n6 = -n4 / this.map_scale;
        if (n5 < 0) {
            n5 = 0;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        int mazew = (400 - n3) / this.map_scale + 1;
        int mazeh = (400 - n4) / this.map_scale + 1;
        if (mazew >= this.mazew) {
            mazew = this.mazew;
        }
        if (mazeh >= this.mazeh) {
            mazeh = this.mazeh;
        }
        for (int i = n6; i <= mazeh; ++i) {
            for (int j = n5; j <= mazew; ++j) {
                final int n7 = j * this.map_scale + n3;
                final int n8 = 399 - (i * this.map_scale + n4);
                final int n9 = j * this.map_scale + n3 + this.map_scale;
                final int n10 = 399 - (i * this.map_scale + n4 + this.map_scale);
                final int n11 = this.seencells[j][i];
                final boolean b = (n11 & 0x1) != 0x0;
                final boolean b2 = j < this.mazew && ((i < this.mazeh) ? ((this.mazecells[j][i] & 0x1) != 0x0) : ((this.mazecells[j][i - 1] & 0x2) != 0x0));
                graphics.setColor(b ? Color.white : Color.gray);
                if ((b || this.showMaze) && b2) {
                    graphics.drawLine(n7, n8, n9, n8);
                }
                final boolean b3 = (n11 & 0x4) != 0x0;
                final boolean b4 = i < this.mazeh && ((j < this.mazew) ? ((this.mazecells[j][i] & 0x4) != 0x0) : ((this.mazecells[j - 1][i] & 0x8) != 0x0));
                graphics.setColor(b3 ? Color.white : Color.gray);
                if ((b3 || this.showMaze) && b4) {
                    graphics.drawLine(n7, n8, n7, n10);
                }
            }
        }
        if (this.showSolution) {
            int px = this.px;
            int py = this.py;
            int k = this.mazedists[px][py];
            graphics.setColor(Color.yellow);
            while (k > 1) {
                int l;
                for (l = 0; l != 4; ++l) {
                    if ((this.mazecells[px][py] & Maze.masks[l]) == 0x0) {
                        try {
                            final int n12 = MazeBuilder.dirsx[l];
                            final int n13 = MazeBuilder.dirsy[l];
                            final int n14 = this.mazedists[px + n12][py + n13];
                            if (n14 < k) {
                                final int n15 = px * this.map_scale + n3 + this.map_scale / 2;
                                final int n16 = 399 - (py * this.map_scale + n4) - this.map_scale / 2;
                                graphics.drawLine(n15, n16, n15 + n12 * this.map_scale, n16 + -n13 * this.map_scale);
                                px += n12;
                                py += n13;
                                k = n14;
                                break;
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                if (l == 4) {
                    this.dbg("HELP!");
                }
            }
        }
        graphics.setColor(Color.red);
        final int n17 = 200;
        final int n18 = 200;
        final int n19 = this.map_scale / 2;
        graphics.fillOval(n17 - n19 / 2, n18 - n19 / 2, n19, n19);
        final int n20 = 7 * this.map_scale / 16;
        final int n21 = n17 + (n20 * this.view_dx >> 16);
        final int n22 = n18 - (n20 * this.view_dy >> 16);
        final int n23 = this.map_scale / 4;
        final int n24 = n17 + (n23 * this.view_dx >> 16);
        final int n25 = n18 - (n23 * this.view_dy >> 16);
        final int n26 = this.map_scale / 8;
        final int n27 = -(n23 * this.view_dy) >> 16;
        final int n28 = -(n23 * this.view_dx) >> 16;
        graphics.drawLine(n17, n18, n21, n22);
        graphics.drawLine(n21, n22, n24 + n27, n25 + n28);
        graphics.drawLine(n21, n22, n24 - n27, n25 - n28);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.buffer_img, 0, 0, this);
        if (this.solving) {
            this.solveStep();
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.buffer_img, 0, 0, this);
    }
    
    void dbg(final String s) {
        System.out.println(s);
    }
    
    void traverse_node(final BSPNode bspNode) {
        ++this.traverse_node_ct;
        if (bspNode.isleaf) {
            this.traverse_ssector((BSPLeaf)bspNode);
            return;
        }
        final BSPBranch bspBranch = (BSPBranch)bspNode;
        if (this.deepdebug) {
            this.dbg("                               ".substring(0, this.nesting) + "traverse_node " + bspBranch.x + " " + bspBranch.y + " " + bspBranch.dx + " " + bspBranch.dy + " " + bspBranch.xl + " " + bspBranch.yl + " " + bspBranch.xu + " " + bspBranch.yu);
        }
        ++this.nesting;
        final int n = (this.viewx - bspBranch.x) * bspBranch.dy - (this.viewy - bspBranch.y) * bspBranch.dx;
        final BSPNode lbranch = bspBranch.lbranch;
        final BSPNode rbranch = bspBranch.rbranch;
        if (n >= 0 && this.bbox_visible(rbranch.yu, rbranch.yl, rbranch.xl, rbranch.xu)) {
            this.traverse_node(rbranch);
        }
        if (this.bbox_visible(lbranch.yu, lbranch.yl, lbranch.xl, lbranch.xu)) {
            this.traverse_node(lbranch);
        }
        if (n < 0 && this.bbox_visible(rbranch.yu, rbranch.yl, rbranch.xl, rbranch.xu)) {
            this.traverse_node(rbranch);
        }
        --this.nesting;
    }
    
    boolean bbox_visible(int n, int n2, int n3, int n4) {
        if (this.all_visible) {
            return true;
        }
        if (this.rset.isEmpty()) {
            return false;
        }
        if (this.ang >= 45 && this.ang <= 135 && this.viewy > n) {
            return false;
        }
        if (this.ang >= 225 && this.ang <= 315 && this.viewy < n2) {
            return false;
        }
        if (this.ang >= 135 && this.ang <= 225 && this.viewx < n3) {
            return false;
        }
        if ((this.ang >= 315 || this.ang <= 45) && this.viewx > n4) {
            return false;
        }
        n3 -= this.viewx;
        n2 -= this.viewy;
        n4 -= this.viewx;
        n -= this.viewy;
        int n5 = n3;
        int n6 = n4;
        int n7 = n2;
        int n8 = n;
        if (n2 < 0 && n > 0) {
            n7 = n2;
            n8 = n;
            if (n3 < 0) {
                if (n4 > 0) {
                    return true;
                }
                n6 = (n5 = n4);
            }
            else {
                n6 = (n5 = n3);
            }
        }
        else if (n3 < 0 && n4 > 0) {
            if (n2 < 0) {
                n8 = (n7 = n);
            }
            else {
                n8 = (n7 = n2);
            }
        }
        else if ((n3 > 0 && n2 > 0) || (n3 < 0 && n2 < 0)) {
            n5 = n4;
            n6 = n3;
        }
        final RangePair rangePair = new RangePair(-this.viewd_unscale(this.view_dy * n5 - this.view_dx * n7), -this.viewd_unscale(this.view_dx * n5 + this.view_dy * n7), -this.viewd_unscale(this.view_dy * n6 - this.view_dx * n8), -this.viewd_unscale(this.view_dx * n6 + this.view_dy * n8));
        if (!this.clip3d(rangePair)) {
            return false;
        }
        int n9 = rangePair.x1 * this.zscale / rangePair.z1 + 200;
        int n10 = rangePair.x2 * this.zscale / rangePair.z2 + 200;
        if (n9 > n10) {
            final int n11 = n9;
            n9 = n10;
            n10 = n11;
        }
        return this.rset.intersect(new Point(n9, n10));
    }
    
    void traverse_ssector(final BSPLeaf bspLeaf) {
        final Vector slist = bspLeaf.slist;
        ++this.traverse_ssector_ct;
        if (this.deepdebug) {
            this.dbg("                               ".substring(0, this.nesting) + "traverse_ssector " + bspLeaf.xl + " " + bspLeaf.yl + " " + bspLeaf.xu + " " + bspLeaf.yu);
        }
        for (int i = 0; i != slist.size(); ++i) {
            final Seg seg = slist.elementAt(i);
            final int x = seg.x;
            final int y = seg.y;
            final int n = x + seg.dx;
            final int n2 = y + seg.dy;
            if (this.deepdebug) {
                this.dbg("                               ".substring(0, this.nesting) + " traverse_ssector(" + i + ") " + x + " " + y + " " + seg.dx + " " + seg.dy);
            }
            this.drawrect(seg, x, y, n, n2);
        }
    }
    
    boolean check_move(final int n) {
        int n2 = this.ang / 90;
        if (n == -1) {
            n2 = (n2 + 2 & 0x3);
        }
        return (this.mazecells[this.px][this.py] & Maze.masks[n2]) == 0x0;
    }
    
    void rotate_step() {
        this.ang = (this.ang + 1800) % 360;
        this.view_dx = (int)(Math.cos(this.radify(this.ang)) * 65536.0);
        this.view_dy = (int)(Math.sin(this.radify(this.ang)) * 65536.0);
        this.move_step();
    }
    
    void move_step() {
        this.redraw();
        try {
            Thread.currentThread();
            Thread.sleep(25L);
        }
        catch (Exception ex) {}
    }
    
    void rotate_finish() {
        this.dx = (int)Math.cos(this.radify(this.ang));
        this.dy = (int)Math.sin(this.radify(this.ang));
        this.log_position();
    }
    
    void walk_finish(final int n) {
        this.px += n * this.dx;
        this.py += n * this.dy;
        if (this.px < 0 || this.py < 0 || this.px >= this.mazew || this.py >= this.mazeh) {
            this.state = 4;
            this.redraw();
        }
        this.walk_step = 0;
        this.log_position();
    }
    
    void log_position() {
        if (!this.deepdebug) {
            return;
        }
        this.dbg("x=" + this.viewx / 128 + " (" + this.viewx + ") y=" + this.viewy / 128 + " (" + this.viewy + ") ang=" + this.ang + " dx=" + this.dx + " dy=" + this.dy + " " + this.view_dx + " " + this.view_dy);
    }
    
    synchronized void walk(final int n) {
        if (!this.check_move(n)) {
            return;
        }
        for (int i = 0; i != 4; ++i) {
            this.walk_step += n;
            this.move_step();
        }
        this.walk_finish(n);
    }
    
    synchronized void rotate(final int n) {
        final int ang = this.ang;
        for (int n2 = 4, i = 0; i != n2; ++i) {
            this.ang = ang + n * (90 * (i + 1)) / n2;
            this.rotate_step();
        }
        this.rotate_finish();
    }
    
    void rotateTo(final int n) {
        final int n2 = this.ang / 90;
        if (n != n2) {
            if (n == (n2 + 2 & 0x3)) {
                this.rotate(1);
                this.rotate(1);
            }
            else if (n == (n2 + 1 & 0x3)) {
                this.rotate(1);
            }
            else {
                this.rotate(-1);
            }
        }
    }
    
    synchronized void solveStep() {
        this.solving = false;
        final int n = this.mazedists[this.px][this.py];
        this.gc.setColor(Color.yellow);
        if (n > 1) {
            int i;
            for (i = 0; i != 4; ++i) {
                if ((this.mazecells[this.px][this.py] & Maze.masks[i]) == 0x0) {
                    try {
                        if (this.mazedists[this.px + MazeBuilder.dirsx[i]][this.py + MazeBuilder.dirsy[i]] < n) {
                            break;
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            if (i == 4) {
                this.dbg("HELP!");
            }
            this.rotateTo(i);
            this.walk(1);
            this.repaint(25L);
            this.solving = true;
            return;
        }
        int j;
        for (j = 0; j != 4; ++j) {
            if ((this.mazecells[this.px][this.py] & Maze.masks[j]) <= 0) {
                final int n2 = MazeBuilder.dirsx[j];
                final int n3 = MazeBuilder.dirsy[j];
                if (this.px + n2 < 0 || this.px + n2 >= this.mazew || this.py + n3 < 0) {
                    break;
                }
                if (this.py + n3 >= this.mazeh) {
                    break;
                }
            }
        }
        this.rotateTo(j);
        this.walk(1);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (this.state) {
            case 1: {
                if (n >= 48 && n <= 57) {
                    this.Build(n - 48);
                    break;
                }
                if (n >= 97 && n <= 102) {
                    this.Build(n - 97 + 10);
                    break;
                }
                break;
            }
            case 2: {
                if (n == 27) {
                    this.mazebuilder.Interrupt();
                    this.BuildInterrupted();
                    break;
                }
                break;
            }
            case 3: {
                switch (n) {
                    case 56:
                    case 107:
                    case 1004: {
                        this.walk(1);
                        break;
                    }
                    case 52:
                    case 104:
                    case 1006: {
                        this.rotate(1);
                        break;
                    }
                    case 54:
                    case 108:
                    case 1007: {
                        this.rotate(-1);
                        break;
                    }
                    case 50:
                    case 106:
                    case 1005: {
                        this.walk(-1);
                        break;
                    }
                    case 27:
                    case 65385: {
                        if (this.solving) {
                            this.solving = false;
                        }
                        else {
                            this.state = 1;
                        }
                        this.redraw();
                        break;
                    }
                    case 23: {
                        this.px += this.dx;
                        this.py += this.dy;
                        this.redraw();
                        break;
                    }
                    case 9:
                    case 109: {
                        this.map_mode = !this.map_mode;
                        this.redraw();
                        break;
                    }
                    case 122: {
                        this.showMaze = !this.showMaze;
                        this.redraw();
                        break;
                    }
                    case 115: {
                        this.showSolution = !this.showSolution;
                        this.redraw();
                        break;
                    }
                    case 19: {
                        if (this.solving) {
                            this.solving = false;
                            break;
                        }
                        this.solving = true;
                        this.repaint(25L);
                        break;
                    }
                    case 43:
                    case 61: {
                        ++this.map_scale;
                        this.redraw();
                        break;
                    }
                    case 45: {
                        --this.map_scale;
                        if (this.map_scale < 1) {
                            this.map_scale = 1;
                        }
                        this.redraw();
                        break;
                    }
                }
                break;
            }
            case 4: {
                this.state = 1;
                this.redraw();
                break;
            }
        }
        return true;
    }
    
    public void init() {
        this.state = 1;
        this.buffer_img = this.createImage(400, 400);
        this.rset = new RangeSet();
        this.largeBannerFont = new Font("TimesRoman", 1, 48);
        this.smallBannerFont = new Font("TimesRoman", 1, 16);
        final MazeBuilder mazeBuilder = new MazeBuilder();
        final FloatPair floatPair = new FloatPair(0.0, 0.0);
        final RangePair rangePair = new RangePair(0, 0, 0, 0);
        final Seg seg = new Seg(0, 0, 0, 0, 0, 0);
        final RangeSetElement rangeSetElement = new RangeSetElement(0, 0);
    }
    
    public void start() {
        this.redraw();
    }
    
    public void stop() {
    }
    
    public void Build(final int n) {
        this.state = 2;
        this.percentdone = 0;
        this.redraw();
        this.mazebuilder = new MazeBuilder();
        this.mazew = Maze.skill_x[n];
        this.mazeh = Maze.skill_y[n];
        this.mazebuilder.Build(this, this.mazew, this.mazeh, Maze.skill_rooms[n], Maze.skill_partct[n]);
    }
    
    static {
        Maze.masks = new int[] { 8, 2, 4, 1 };
        Maze.skill_x = new int[] { 4, 12, 15, 20, 25, 25, 35, 35, 40, 60, 70, 80, 90, 110, 150, 300 };
        Maze.skill_y = new int[] { 4, 12, 15, 15, 20, 25, 25, 35, 40, 60, 70, 75, 75, 90, 120, 240 };
        Maze.skill_rooms = new int[] { 0, 2, 2, 3, 4, 5, 10, 10, 20, 45, 45, 50, 50, 60, 80, 160 };
        Maze.skill_partct = new int[] { 60, 600, 900, 1200, 2100, 2700, 3300, 5000, 6000, 13500, 19800, 25000, 29000, 45000, 85000, 340000 };
    }
}
