import java.awt.Font;
import java.awt.Color;
import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class puzzle extends Applet
{
    public piece[] pieces;
    int i;
    int x;
    int y;
    int cur_val;
    int start_x;
    int start_y;
    int row_size;
    int piece_width;
    int piece_height;
    private int blank_piece_pos;
    Graphics myG;
    simple_puzzle simple;
    random_value value_supplier;
    MediaTracker tracker;
    selector level_indicator;
    Thread loadThread;
    String image_name;
    media_checker checker;
    boolean images_loaded;
    boolean user_waiting;
    
    public void init() {
        this.myG = this.getGraphics();
        this.checker = null;
        this.tracker = new MediaTracker(this);
        this.level_indicator = new selector(0, this, 100, 5);
        this.image_name = this.getParameter("PREFIX");
        this.x = this.start_x;
        this.y = this.start_y;
        this.i = 0;
        while (this.i <= 15) {
            this.cur_val = this.value_supplier.getRandomValue();
            if (this.cur_val == 16) {
                this.blank_piece_pos = this.i;
            }
            this.pieces[this.i] = new piece(this.i, this.cur_val, this.myG, this.x, this.y, null, this);
            this.x += 50;
            if (this.x > 200) {
                this.x = 50;
                this.y += 50;
            }
            ++this.i;
        }
        this.simple = new simple_puzzle(15, this);
        this.resize(this.pieces[0].width * 6, this.pieces[0].height * 6);
        (this.checker = new media_checker(this.myG, this)).start();
    }
    
    public void start() {
    }
    
    public void stop() {
        if (this.loadThread != null) {
            this.loadThread.stop();
            this.loadThread = null;
        }
    }
    
    public void run() {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.point_inside(n, n2)) {
            final int n3 = (n - this.start_x) / this.piece_width + this.row_size * ((n2 - this.start_y) / this.piece_height);
            if (this.pieces[this.blank_piece_pos].same_row(n2)) {
                if (this.pieces[n3].toLeft(this.pieces[this.blank_piece_pos])) {
                    int i = this.pieces[this.blank_piece_pos].left;
                    while (i != this.pieces[n3].left) {
                        this.pieces[i].xchg(this.pieces[this.blank_piece_pos]);
                        this.blank_piece_pos = i;
                        i = this.pieces[this.blank_piece_pos].left;
                        if (this.simple.puzzle_complete()) {
                            this.myG.drawString("Congratulations!!!", 75, 275);
                        }
                    }
                }
                else if (this.pieces[n3].toRight(this.pieces[this.blank_piece_pos])) {
                    int j = this.pieces[this.blank_piece_pos].right;
                    while (j != this.pieces[n3].right) {
                        this.pieces[j].xchg(this.pieces[this.blank_piece_pos]);
                        this.blank_piece_pos = j;
                        j = this.pieces[this.blank_piece_pos].right;
                        if (this.simple.puzzle_complete()) {
                            this.myG.drawString("Congratulations!!!", 75, 275);
                        }
                    }
                }
            }
            else if (this.pieces[this.blank_piece_pos].same_col(n)) {
                if (this.pieces[n3].above(this.pieces[this.blank_piece_pos])) {
                    int k = this.pieces[this.blank_piece_pos].up;
                    while (k != this.pieces[n3].up) {
                        this.pieces[k].xchg(this.pieces[this.blank_piece_pos]);
                        this.blank_piece_pos = k;
                        k = this.pieces[this.blank_piece_pos].up;
                        if (this.simple.puzzle_complete()) {
                            this.myG.drawString("Congratulations!!!", 75, 275);
                        }
                    }
                }
                else if (this.pieces[n3].below(this.pieces[this.blank_piece_pos])) {
                    int l = this.pieces[this.blank_piece_pos].down;
                    while (l != this.pieces[n3].down) {
                        this.pieces[l].xchg(this.pieces[this.blank_piece_pos]);
                        this.blank_piece_pos = l;
                        l = this.pieces[this.blank_piece_pos].down;
                        if (this.simple.puzzle_complete()) {
                            this.myG.drawString("Congratulations!!!", 75, 275);
                        }
                    }
                }
            }
        }
        else if (this.level_indicator.inside(n, n2)) {
            if (this.images_loaded) {
                if (this.user_waiting) {
                    final String s = "Image loading complete!!";
                    this.myG.setColor(Color.pink);
                    this.myG.fillRect(0, 251, 300, 300);
                    this.myG.setFont(new Font("Times", 1, 8));
                    this.myG.setColor(Color.black);
                    this.myG.drawString(s, 10, 275);
                    this.user_waiting = false;
                }
                this.level_indicator.change();
                this.level_indicator.paint();
                this.paint(this.myG);
            }
            else {
                this.user_waiting = true;
                final String s2 = "All Images not loaded, try again soon...";
                this.myG.setFont(new Font("Times", 1, 8));
                this.myG.setColor(Color.black);
                this.myG.drawString(s2, 10, 275);
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.pink);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i <= 15; ++i) {
            this.pieces[i].draw();
        }
        this.level_indicator.paint();
    }
    
    int getLocValue(final int n) {
        return this.pieces[n].value;
    }
    
    int getBlankLocValue() {
        return this.pieces[this.blank_piece_pos].value;
    }
    
    public boolean point_inside(final int n, final int n2) {
        return n >= this.start_x && n2 >= this.start_y && n <= this.start_x + this.row_size * this.piece_width && n2 <= this.start_y + this.row_size * this.piece_width;
    }
    
    public puzzle() {
        this.pieces = new piece[16];
        this.start_x = 50;
        this.start_y = 50;
        this.row_size = 4;
        this.piece_width = 50;
        this.piece_height = 50;
        this.value_supplier = new random_value(1, 16);
        this.images_loaded = false;
        this.user_waiting = false;
    }
}
