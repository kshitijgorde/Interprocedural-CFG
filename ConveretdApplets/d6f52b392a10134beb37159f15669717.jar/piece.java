import java.awt.image.BufferedImage;

// 
// Decompiled by Procyon v0.5.30
// 

class piece extends cluster
{
    static int pw;
    static int ph;
    static int wover;
    static int hover;
    cluster c;
    int inn;
    int ine;
    int ins;
    int inw;
    int ashift;
    int bshift;
    
    piece(final int n, final int n2) {
        this.lowx = n;
        this.lowy = n2;
        this.highx = n;
        this.highy = n2;
        this.b = this.b;
        this.rotn = 0;
        this.posx = n * piece.pw * 2;
        this.posy = n2 * piece.ph * 2;
        (this.parr = new piece[1])[0] = this;
        this.c = this;
        this.b = new BufferedImage(piece.pw + piece.wover * 2, piece.ph + piece.hover * 2, 2);
        this.clip = cluster.cliparr[0][0];
    }
}
