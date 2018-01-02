import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class inv
{
    private Graphics paper;
    private Image gfx;
    private int[] invItem;
    private int numInInv;
    private int currSelected;
    private items item;
    
    public int getID() {
        if (this.numInInv > 0) {
            return this.invItem[this.currSelected];
        }
        return 0;
    }
    
    public void changeItem() {
        ++this.currSelected;
        if (this.currSelected >= this.numInInv) {
            this.currSelected = 0;
        }
        this.drawInv();
    }
    
    public inv(final Graphics paper, final Image gfx) {
        this.paper = paper;
        this.gfx = gfx;
        this.invItem = new int[10];
        this.numInInv = 0;
        this.currSelected = 0;
        this.invItem[0] = 0;
        this.invItem[1] = 0;
        this.invItem[2] = 0;
        this.invItem[3] = 0;
        this.invItem[4] = 0;
        this.invItem[5] = 0;
        this.item = new items();
    }
    
    public boolean addItem(final int n) {
        if (this.numInInv < 6) {
            this.invItem[this.numInInv] = n;
            this.currSelected = this.numInInv++;
            this.drawInv();
            return true;
        }
        return false;
    }
    
    public void doDraw(final int n, final int n2, int n3) {
        if (n3 > 0) {
            final int n4 = n3 / 10 * 20;
            n3 %= 10;
            n3 *= 20;
            this.paper.drawImage(this.gfx, n, n2, n + 20, n2 + 20, n3, n4, n3 + 20, n4 + 20, null);
        }
        else {
            this.paper.fillRect(n, n2, 20, 20);
        }
    }
    
    public String getName() {
        return this.item.getName(this.invItem[this.currSelected]);
    }
    
    public void removeFromInv() {
        this.invItem[this.currSelected] = this.invItem[this.numInInv - 1];
        --this.numInInv;
        this.currSelected = 0;
        this.drawInv();
    }
    
    public void drawInv() {
        int n = 20;
        final int n2 = 45;
        this.paper.setColor(Color.black);
        this.paper.fillRect(0, 36, 200, 50);
        for (int i = 0; i < this.numInInv; ++i) {
            this.doDraw(n, n2, this.invItem[i]);
            if (this.currSelected == i) {
                this.paper.setColor(Color.white);
                this.paper.drawRect(n - 2, n2 - 2, 22, 22);
                this.paper.setColor(Color.black);
            }
            n += 25;
        }
    }
}
