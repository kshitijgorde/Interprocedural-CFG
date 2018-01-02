import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Seg
{
    public int x;
    public int y;
    public int dx;
    public int dy;
    public int dist;
    public Color col;
    public boolean partition;
    public boolean seen;
    
    Seg(final int x, final int y, final int dx, final int dy, int dist, final int n) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.dist = dist;
        this.seen = false;
        dist /= 4;
        final int n2 = (this.dx != 0) ? 1 : 0;
        final int n3 = dist & 0x7;
        final int n4 = (dist >> 3 ^ n) % 6;
        final int n5 = (n3 + 2 + n2) * 70 / 8 + 80;
        switch (n4) {
            case 0: {
                this.col = new Color(n5, 20, 20);
                break;
            }
            case 1: {
                this.col = new Color(20, n5, 20);
                break;
            }
            case 2: {
                this.col = new Color(20, 20, n5);
                break;
            }
            case 3: {
                this.col = new Color(n5, n5, 20);
                break;
            }
            case 4: {
                this.col = new Color(20, n5, n5);
                break;
            }
            case 5: {
                this.col = new Color(n5, 20, n5);
                break;
            }
        }
    }
    
    int GetDir() {
        if (this.dx != 0) {
            return (this.dx < 0) ? 1 : -1;
        }
        return (this.dy < 0) ? 2 : -2;
    }
}
