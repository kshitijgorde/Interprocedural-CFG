import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class SpinButton
{
    int x;
    int y;
    int number;
    int maxVal;
    int runner;
    Font font1;
    boolean up;
    boolean down;
    long timeSet;
    
    public void DrawControl(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, 25, 18);
        g.setColor(Color.lightGray);
        g.fill3DRect(this.x + 25, this.y, 14, 9, !this.up);
        g.fill3DRect(this.x + 25, this.y + 9, 14, 9, !this.down);
        g.setColor(Color.black);
        this.UpTriangle(g, this.x + 28, this.y + 2, 8, 5);
        this.DownTriangle(g, this.x + 29, this.y + 16, 7, 4);
        g.setFont(this.font1);
        g.drawString("" + this.number, this.x + 5, this.y + 14);
    }
    
    public void Update() {
        ++this.runner;
        if (this.runner % 2 == 0) {
            if (this.up) {
                if (this.number < this.maxVal) {
                    ++this.number;
                }
                else {
                    this.number = 0;
                }
            }
            if (this.down) {
                if (this.number > 0) {
                    --this.number;
                }
                else {
                    this.number = this.maxVal;
                }
            }
        }
    }
    
    public void mouseUp() {
        final boolean b = false;
        this.down = b;
        this.up = b;
    }
    
    public void DownTriangle(final Graphics g, final int x, final int y, final int w, final int h) {
        final int[] a = { x, x + w / 2, x + w };
        final int[] b = { y - h, y, y - h };
        g.fillPolygon(a, b, 3);
    }
    
    public SpinButton(final int x, final int y, final int max) {
        this.font1 = new Font("Dialog", 1, 14);
        this.x = x;
        this.y = y;
        this.maxVal = max;
    }
    
    public int GetNumber() {
        return this.number;
    }
    
    public boolean mouseDown(final int xx, final int yy) {
        if (xx > this.x + 25 && xx < this.x + 40 && yy > this.y && yy < this.y + 9) {
            return this.up = true;
        }
        return xx > this.x + 25 && xx < this.x + 40 && yy > this.y + 9 && yy < this.y + 18 && (this.down = true);
    }
    
    public void init() {
        this.number = 0;
    }
    
    public void UpTriangle(final Graphics g, final int x, final int y, final int w, final int h) {
        final int[] a = { x, x + w / 2, x + w };
        final int[] b = { y + h, y, y + h };
        g.fillPolygon(a, b, 3);
    }
}
