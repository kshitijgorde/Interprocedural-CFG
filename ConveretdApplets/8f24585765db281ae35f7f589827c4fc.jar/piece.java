import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class piece
{
    Integer value;
    Integer x_pos;
    Integer y_pos;
    int width;
    int height;
    Graphics gc;
    int right;
    int left;
    int up;
    int down;
    Image pic;
    int flags;
    puzzle obj;
    loadThread img_thread;
    
    piece(final int n, final int n2, final Graphics gc, final int n3, final int n4, final Image image, final puzzle obj) {
        this.width = 50;
        this.height = 50;
        if (obj != null) {
            this.obj = obj;
            this.pic = null;
            (this.img_thread = new loadThread("thread" + n, obj.image_name + n2 + ".gif", this, n)).start();
        }
        this.value = new Integer(n2);
        this.gc = gc;
        this.x_pos = new Integer(n3);
        this.y_pos = new Integer(n4);
        final int right = n + 1;
        if (right == 4 || right == 8 || right == 12 || right == 16) {
            this.right = -1;
        }
        else {
            this.right = right;
        }
        final int left = n - 1;
        if (left == 3 || left == 7 || left == 11) {
            this.left = -1;
        }
        else {
            this.left = left;
        }
        final int up = n - 4;
        if (up < 0) {
            this.up = -1;
        }
        else {
            this.up = up;
        }
        final int down = n + 4;
        if (down >= 16) {
            this.down = -1;
            return;
        }
        this.down = down;
    }
    
    public void setImage(final Image pic) {
        if (pic != null) {
            this.pic = pic;
        }
    }
    
    public boolean point_inside(final int n, final int n2) {
        return n >= this.x_pos && n2 >= this.y_pos && n <= this.x_pos + this.width && n2 <= this.y_pos + this.height;
    }
    
    public boolean same_col(final int n) {
        return n > this.x_pos && n < this.x_pos + this.height;
    }
    
    public boolean same_row(final int n) {
        return n > this.y_pos && n < this.y_pos + this.width;
    }
    
    public boolean toLeft(final piece piece) {
        return this.x_pos < piece.x_pos;
    }
    
    public boolean toRight(final piece piece) {
        return this.x_pos > piece.x_pos;
    }
    
    public boolean above(final piece piece) {
        return this.y_pos < piece.y_pos;
    }
    
    public boolean below(final piece piece) {
        return this.y_pos > piece.y_pos;
    }
    
    public void xchg(final piece piece) {
        final Integer value = this.value;
        this.value = piece.value;
        piece.value = value;
        if (this.pic != null) {
            final Image pic = this.pic;
            this.pic = piece.pic;
            piece.pic = pic;
        }
        this.draw();
        piece.draw();
    }
    
    public void draw() {
        if (this.value == 16) {
            this.gc.setColor(Color.black);
            this.gc.fillRect(this.x_pos, this.y_pos, this.width, this.height);
            this.gc.drawRect(this.x_pos, this.y_pos, this.width, this.height);
            return;
        }
        if (this.obj.level_indicator.level() == 0) {
            this.gc.setColor(Color.yellow);
            this.gc.fillRect(this.x_pos, this.y_pos, this.width, this.height);
            this.gc.setColor(Color.black);
            this.gc.drawRect(this.x_pos, this.y_pos, this.width, this.height);
            this.gc.setFont(new Font("Times", 1, 16));
            this.gc.drawString(this.value.toString(), this.x_pos + (this.width - this.gc.getFontMetrics().stringWidth(this.value.toString())) / 2, this.y_pos + this.height / 2);
            return;
        }
        this.gc.setColor(Color.black);
        this.gc.drawRect(this.x_pos, this.y_pos, this.width, this.height);
        this.gc.drawImage(this.pic, this.x_pos, this.y_pos, null);
        if (this.obj.level_indicator.level() == 2) {
            this.gc.setColor(Color.yellow);
            this.gc.setFont(new Font("Times", 1, 16));
            this.gc.drawString(this.value.toString(), this.x_pos + (this.width - this.gc.getFontMetrics().stringWidth(this.value.toString())) / 2, this.y_pos + this.height / 2);
        }
    }
}
