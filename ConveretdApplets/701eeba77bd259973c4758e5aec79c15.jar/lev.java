import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class lev
{
    public ent[] curEnt;
    public ent[] movInRm;
    public int numInRm;
    private items item;
    private Graphics paper;
    private Image gfx;
    private Image gfx2;
    
    public void moveDown() {
        this.paper.copyArea(0, 20, 240, 220, 0, -20);
    }
    
    public void drawBottom(final int n, final int n2) {
        int n3 = 0;
        final int n4 = 220;
        final int n5 = n2 + 6;
        for (int i = n - 5; i <= n + 6; ++i) {
            final int n6 = i + n5 * 200;
            this.doDraw(n3, n4, this.curEnt[n6].z, true);
            n3 += 20;
            if (this.curEnt[n6].moves) {
                this.addToMvArray(n6);
            }
        }
    }
    
    public lev(final String s, final Graphics paper, final Image gfx, final Image gfx2) {
        final int n = s.length() - 1;
        this.curEnt = new ent[n];
        this.movInRm = new ent[100];
        this.numInRm = 0;
        this.paper = paper;
        this.gfx = gfx;
        this.gfx2 = gfx2;
        this.item = new items();
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            boolean moves = false;
            boolean dirU = false;
            boolean dirD = false;
            boolean dirL = false;
            boolean dirR = false;
            int moveSpeed = 4;
            final int id = this.item.getID(s.charAt(i));
            switch (id) {
                case 96: {
                    moves = true;
                    dirL = true;
                    break;
                }
                case 87: {
                    moves = true;
                    dirL = true;
                    break;
                }
                case 88: {
                    moves = true;
                    dirU = true;
                    break;
                }
                case 94: {
                    moves = true;
                    dirR = true;
                    break;
                }
                case 92: {
                    moves = true;
                    dirU = true;
                    break;
                }
                case 90: {
                    moves = true;
                    dirR = true;
                    break;
                }
                case 85: {
                    moves = true;
                    dirD = true;
                    break;
                }
                case 100: {
                    moves = true;
                    dirU = true;
                    moveSpeed = 2;
                    break;
                }
                case 102: {
                    moves = true;
                    dirD = true;
                    break;
                }
                case 104: {
                    moves = true;
                    dirR = true;
                    moveSpeed = 5;
                    break;
                }
                case 106: {
                    moves = true;
                    dirL = true;
                    moveSpeed = 2;
                    break;
                }
                case 108: {
                    moves = true;
                    dirL = true;
                    break;
                }
            }
            final int n4 = n2 + n3 * 200;
            this.curEnt[n4] = new ent(n2 * 20, n3 * 20, id);
            this.curEnt[n4].moves = moves;
            this.curEnt[n4].dirU = dirU;
            this.curEnt[n4].dirD = dirD;
            this.curEnt[n4].dirL = dirL;
            this.curEnt[n4].dirR = dirR;
            this.curEnt[n4].moveSpeed = moveSpeed;
            if (++n2 > 199) {
                n2 = 0;
                ++n3;
            }
        }
    }
    
    public void removeFromRm(final int n) {
        --this.numInRm;
        if (this.numInRm < 0) {
            this.numInRm = 0;
        }
        if (this.numInRm > 0 && n != this.numInRm) {
            this.movInRm[n].moves = this.movInRm[this.numInRm].moves;
            this.movInRm[n].dirU = this.movInRm[this.numInRm].dirU;
            this.movInRm[n].dirD = this.movInRm[this.numInRm].dirD;
            this.movInRm[n].dirL = this.movInRm[this.numInRm].dirL;
            this.movInRm[n].dirR = this.movInRm[this.numInRm].dirR;
            this.movInRm[n].x = this.movInRm[this.numInRm].x;
            this.movInRm[n].y = this.movInRm[this.numInRm].y;
            this.movInRm[n].z = this.movInRm[this.numInRm].z;
            this.movInRm[n].i = this.movInRm[this.numInRm].i;
            this.movInRm[n].moveSpeed = this.movInRm[this.numInRm].moveSpeed;
        }
        this.movInRm[this.numInRm].z = 0;
    }
    
    public void drawTop(final int n, final int n2) {
        int n3 = 0;
        final int n4 = 0;
        final int n5 = n2 - 5;
        for (int i = n - 5; i <= n + 6; ++i) {
            final int n6 = i + n5 * 200;
            this.doDraw(n3, n4, this.curEnt[n6].z, true);
            n3 += 20;
            if (this.curEnt[n6].moves) {
                this.addToMvArray(n6);
            }
        }
    }
    
    public void reDist(final int z) {
        int n;
        int n2;
        for (n = 27000, n2 = (int)(n * Math.random()) + 1; this.curEnt[n2].z > 0; n2 = (int)(n * Math.random()) + 1) {}
        this.curEnt[n2].z = z;
    }
    
    public void addToMvArray(final int n) {
        this.movInRm[this.numInRm] = new ent(this.curEnt[n].x, this.curEnt[n].y, this.curEnt[n].z);
        this.movInRm[this.numInRm].moves = this.curEnt[n].moves;
        this.movInRm[this.numInRm].dirU = this.curEnt[n].dirU;
        this.movInRm[this.numInRm].dirD = this.curEnt[n].dirD;
        this.movInRm[this.numInRm].dirL = this.curEnt[n].dirL;
        this.movInRm[this.numInRm].moveSpeed = this.curEnt[n].moveSpeed;
        this.movInRm[this.numInRm++].dirR = this.curEnt[n].dirR;
        this.curEnt[n].z = 0;
        this.curEnt[n].moves = false;
    }
    
    public void drawRight(final int n, final int n2) {
        final int n3 = 220;
        int n4 = 0;
        final int n5 = n + 6;
        for (int i = n2 - 5; i <= n2 + 6; ++i) {
            final int n6 = n5 + i * 200;
            this.doDraw(n3, n4, this.curEnt[n6].z, true);
            n4 += 20;
            if (this.curEnt[n6].moves) {
                this.addToMvArray(n6);
            }
        }
    }
    
    public void updateWindow(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        for (int i = n2 - 5; i <= n2 + 6; ++i) {
            for (int j = n - 5; j <= n + 6; ++j) {
                this.doDraw(n3, n4, this.curEnt[j + i * 200].z, true);
                n3 += 20;
            }
            n3 = 0;
            n4 += 20;
        }
    }
    
    public void moveUp() {
        this.paper.copyArea(0, 0, 240, 220, 0, 20);
    }
    
    public void doDraw(final int n, final int n2, int n3, final boolean b) {
        if (n3 > 0) {
            final int n4 = n3 / 10 * 20;
            n3 %= 10;
            n3 *= 20;
            if (b) {
                this.paper.drawImage(this.gfx, n, n2, n + 20, n2 + 20, n3, n4, n3 + 20, n4 + 20, null);
            }
            else {
                this.paper.drawImage(this.gfx2, n, n2, n + 20, n2 + 20, n3, n4, n3 + 20, n4 + 20, null);
            }
        }
        else {
            this.paper.setColor(Color.black);
            this.paper.fillRect(n, n2, 20, 20);
        }
    }
    
    public int collide(final ent ent, final ent ent2) {
        if (ent2.z == 0) {
            return 0;
        }
        final int x = ent.x;
        final int y = ent.y;
        final int x2 = ent2.x;
        if (x2 > x - 20 && x2 < x + 20) {
            final int y2 = ent2.y;
            if (y2 > y - 20 && y2 < y + 20) {
                return ent2.z;
            }
        }
        return 0;
    }
    
    public void drawLeft(final int n, final int n2) {
        final int n3 = 0;
        int n4 = 0;
        final int n5 = n - 5;
        for (int i = n2 - 5; i <= n2 + 6; ++i) {
            final int n6 = n5 + i * 200;
            this.doDraw(n3, n4, this.curEnt[n6].z, true);
            n4 += 20;
            if (this.curEnt[n6].moves) {
                this.addToMvArray(n6);
            }
        }
    }
    
    public void moveLeft() {
        this.paper.copyArea(0, 0, 220, 240, 20, 0);
    }
    
    public void moveRight() {
        this.paper.copyArea(20, 0, 220, 240, -20, 0);
    }
    
    public void setUpWindow(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        this.numInRm = 0;
        for (int i = n2 - 5; i <= n2 + 6; ++i) {
            for (int j = n - 5; j <= n + 6; ++j) {
                final int n5 = j + i * 200;
                this.doDraw(n3, n4, this.curEnt[n5].z, true);
                n3 += 20;
                if (this.curEnt[n5].moves) {
                    this.addToMvArray(n5);
                }
            }
            n3 = 0;
            n4 += 20;
        }
    }
    
    public int getNearestSpace(final int n, final int n2) {
        for (int i = n - 1; i < n + 2; ++i) {
            if (this.curEnt[i + n2 * 200].z == 0 && (n2 != n2 || i != n)) {
                return i + n2 * 200;
            }
        }
        final int n3 = n2 - 1;
        for (int j = n - 1; j < n + 2; ++j) {
            if (this.curEnt[j + n3 * 200].z == 0 && (n3 != n2 || j != n)) {
                return j + n3 * 200;
            }
        }
        final int n4 = n2 + 1;
        for (int k = n - 1; k < n + 2; ++k) {
            if (this.curEnt[k + n4 * 200].z == 0 && (n4 != n2 || k != n)) {
                return k + n4 * 200;
            }
        }
        return 0;
    }
}
