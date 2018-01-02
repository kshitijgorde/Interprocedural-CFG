import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Shield implements Statics
{
    Image ShieldImage;
    SpacedInvaders si;
    ShieldSprite[][] aShield;
    final int OFFSET_X = 5;
    final int OFFSET_Y = 10;
    int start_x;
    int start_y;
    int ROWS;
    int COLS;
    int offsetx;
    int offsety;
    boolean[][] shieldLayout;
    
    public Shield(final int n, final int n2, final Image shieldImage, final SpacedInvaders si) {
        this.ROWS = 10;
        this.COLS = 80;
        this.offsetx = 40;
        this.offsety = 360;
        this.ShieldImage = shieldImage;
        this.si = si;
        this.aShield = new ShieldSprite[this.ROWS][this.COLS];
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                this.aShield[j][i] = new ShieldSprite(this.ShieldImage, (Applet)this.si, this.offsetx, this.offsety);
                this.offsety += 10;
            }
            this.offsetx += 5;
            this.offsety = 360;
        }
        final int n3 = 21;
        this.offsetx = 15;
        for (int k = this.offsetx; k < n3; ++k) {
            for (int l = 0; l < 10; ++l) {
                ((Sprite)this.aShield[l][k]).suspend();
            }
        }
        ((Sprite)this.aShield[0][0]).suspend();
        ((Sprite)this.aShield[0][1]).suspend();
        ((Sprite)this.aShield[0][13]).suspend();
        ((Sprite)this.aShield[0][14]).suspend();
        ((Sprite)this.aShield[1][0]).suspend();
        ((Sprite)this.aShield[1][14]).suspend();
        for (int n4 = 5; n4 < 10; ++n4) {
            ((Sprite)this.aShield[8][n4]).suspend();
        }
        for (int n5 = 5; n5 < 10; ++n5) {
            ((Sprite)this.aShield[9][n5]).suspend();
        }
        ((Sprite)this.aShield[0][21]).suspend();
        ((Sprite)this.aShield[0][22]).suspend();
        ((Sprite)this.aShield[0][34]).suspend();
        ((Sprite)this.aShield[0][35]).suspend();
        ((Sprite)this.aShield[1][21]).suspend();
        ((Sprite)this.aShield[1][35]).suspend();
        for (int n6 = 26; n6 < 31; ++n6) {
            ((Sprite)this.aShield[8][n6]).suspend();
        }
        for (int n7 = 26; n7 < 31; ++n7) {
            ((Sprite)this.aShield[9][n7]).suspend();
        }
        ((Sprite)this.aShield[0][42]).suspend();
        ((Sprite)this.aShield[0][43]).suspend();
        ((Sprite)this.aShield[0][55]).suspend();
        ((Sprite)this.aShield[0][56]).suspend();
        ((Sprite)this.aShield[1][42]).suspend();
        ((Sprite)this.aShield[1][56]).suspend();
        for (int n8 = 47; n8 < 52; ++n8) {
            ((Sprite)this.aShield[8][n8]).suspend();
        }
        for (int n9 = 47; n9 < 52; ++n9) {
            ((Sprite)this.aShield[9][n9]).suspend();
        }
        ((Sprite)this.aShield[0][64]).suspend();
        ((Sprite)this.aShield[0][65]).suspend();
        ((Sprite)this.aShield[0][78]).suspend();
        ((Sprite)this.aShield[0][79]).suspend();
        ((Sprite)this.aShield[1][64]).suspend();
        ((Sprite)this.aShield[1][79]).suspend();
        for (int n10 = 69; n10 < 75; ++n10) {
            ((Sprite)this.aShield[8][n10]).suspend();
        }
        for (int n11 = 69; n11 < 75; ++n11) {
            ((Sprite)this.aShield[9][n11]).suspend();
        }
        final int n12 = n3 + 21;
        this.offsetx = 36;
        for (int offsetx = this.offsetx; offsetx < n12; ++offsetx) {
            for (int n13 = 0; n13 < 10; ++n13) {
                ((Sprite)this.aShield[n13][offsetx]).suspend();
            }
        }
        final int n14 = n12 + 22;
        this.offsetx = 57;
        for (int offsetx2 = this.offsetx; offsetx2 < n14; ++offsetx2) {
            for (int n15 = 0; n15 < 10; ++n15) {
                ((Sprite)this.aShield[n15][offsetx2]).suspend();
            }
        }
    }
    
    public ShieldSprite[][] getShield() {
        return this.aShield;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                ((BitmapSprite)this.aShield[j][i]).paint(graphics);
            }
        }
    }
    
    public void reset() {
        for (int i = 0; i < this.COLS; ++i) {
            for (int j = 0; j < this.ROWS; ++j) {
                ((Sprite)this.aShield[j][i]).restore();
            }
        }
        ((Sprite)this.aShield[0][0]).suspend();
        ((Sprite)this.aShield[0][1]).suspend();
        ((Sprite)this.aShield[0][13]).suspend();
        ((Sprite)this.aShield[0][14]).suspend();
        ((Sprite)this.aShield[1][0]).suspend();
        ((Sprite)this.aShield[1][14]).suspend();
        for (int k = 5; k < 10; ++k) {
            ((Sprite)this.aShield[8][k]).suspend();
        }
        for (int l = 5; l < 10; ++l) {
            ((Sprite)this.aShield[9][l]).suspend();
        }
        ((Sprite)this.aShield[0][21]).suspend();
        ((Sprite)this.aShield[0][22]).suspend();
        ((Sprite)this.aShield[0][34]).suspend();
        ((Sprite)this.aShield[0][35]).suspend();
        ((Sprite)this.aShield[1][21]).suspend();
        ((Sprite)this.aShield[1][35]).suspend();
        for (int n = 26; n < 31; ++n) {
            ((Sprite)this.aShield[8][n]).suspend();
        }
        for (int n2 = 26; n2 < 31; ++n2) {
            ((Sprite)this.aShield[9][n2]).suspend();
        }
        ((Sprite)this.aShield[0][42]).suspend();
        ((Sprite)this.aShield[0][43]).suspend();
        ((Sprite)this.aShield[0][55]).suspend();
        ((Sprite)this.aShield[0][56]).suspend();
        ((Sprite)this.aShield[1][42]).suspend();
        ((Sprite)this.aShield[1][56]).suspend();
        for (int n3 = 47; n3 < 52; ++n3) {
            ((Sprite)this.aShield[8][n3]).suspend();
        }
        for (int n4 = 47; n4 < 52; ++n4) {
            ((Sprite)this.aShield[9][n4]).suspend();
        }
        ((Sprite)this.aShield[0][64]).suspend();
        ((Sprite)this.aShield[0][65]).suspend();
        ((Sprite)this.aShield[0][78]).suspend();
        ((Sprite)this.aShield[0][79]).suspend();
        ((Sprite)this.aShield[1][64]).suspend();
        ((Sprite)this.aShield[1][79]).suspend();
        for (int n5 = 69; n5 < 75; ++n5) {
            ((Sprite)this.aShield[8][n5]).suspend();
        }
        for (int n6 = 69; n6 < 75; ++n6) {
            ((Sprite)this.aShield[9][n6]).suspend();
        }
        final int n7 = 21;
        this.offsetx = 15;
        for (int offsetx = this.offsetx; offsetx < n7; ++offsetx) {
            for (int n8 = 0; n8 < 10; ++n8) {
                ((Sprite)this.aShield[n8][offsetx]).suspend();
            }
        }
        final int n9 = n7 + 21;
        this.offsetx = 36;
        for (int offsetx2 = this.offsetx; offsetx2 < n9; ++offsetx2) {
            for (int n10 = 0; n10 < 10; ++n10) {
                ((Sprite)this.aShield[n10][offsetx2]).suspend();
            }
        }
        final int n11 = n9 + 22;
        this.offsetx = 57;
        for (int offsetx3 = this.offsetx; offsetx3 < n11; ++offsetx3) {
            for (int n12 = 0; n12 < 10; ++n12) {
                ((Sprite)this.aShield[n12][offsetx3]).suspend();
            }
        }
    }
}
