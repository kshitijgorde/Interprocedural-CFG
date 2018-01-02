// 
// Decompiled by Procyon v0.5.30
// 

public class Map
{
    public static final short ICECUBE = 2;
    public static final short PLATTFORM = 3;
    public static final short BARREL = 4;
    public short[] data;
    public int width;
    public int height;
    public int pos;
    public int ofs;
    
    public Map(final int width, final int height) {
        this.pos = 0;
        this.ofs = 0;
        this.data = new short[width * height];
        this.width = width;
        this.height = height;
    }
    
    public void create() {
        final short value = 0;
        for (int x = 0; x < this.width; ++x) {
            this.setAt(x, 9, (short)1);
            if (x < 20 && x % 9 == 0) {
                for (int q = 0; q < 3; ++q) {
                    this.setAt(x + q, 7, (short)3);
                }
            }
            if (x % 7 == 0 && x < 21) {
                this.setAt(x, 8, (short)2);
            }
        }
        for (int y = 0; y < 10; ++y) {
            this.setAt(0, y, (short)2);
            this.setAt(this.width - 1, y, (short)2);
        }
        for (int a = 9; a < 15; ++a) {
            if (a < 12) {
                this.setAt(a, 3, (short)3);
            }
            else if (a > 12) {
                this.setAt(a, 2, (short)3);
            }
            else {
                this.setAt(a, 2, (short)2);
            }
        }
        this.setAt(6, 5, (short)3);
        this.setAt(25, 4, (short)3);
        this.setAt(26, 3, (short)3);
        this.setAt(27, 2, (short)3);
        for (int a2 = 26; a2 < 32; ++a2) {
            this.setAt(a2, 1, (short)3);
        }
        for (int a3 = 0; a3 < 5; ++a3) {
            this.setAt(40, a3, (short)2);
            this.setAt(44 + a3, 2, (short)2);
            this.setAt(47 + a3, 6, (short)3);
        }
        this.setAt(41, 2, (short)3);
        this.setAt(45, 0, (short)2);
        this.setAt(48, 1, (short)2);
        this.setAt(48, 0, (short)2);
        for (int a4 = 0; a4 < 9; ++a4) {
            this.setAt(41 + a4, 4, (short)2);
            if (a4 != 4 && a4 != 3 && a4 != 8) {
                this.setAt(52, a4, (short)2);
            }
        }
        for (int a5 = 0; a5 < 4; ++a5) {
            this.setAt(52 + a5, 2, (short)2);
            this.setAt(53 + a5, 5, (short)3);
            this.setAt(58 + a5, 4, (short)3);
            this.setAt(72 + a5, 6, (short)3);
        }
        for (int a6 = 0; a6 < 3; ++a6) {
            this.setAt(68 - a6, 2, (short)3);
            this.setAt(67 - a6, 6, (short)3);
        }
        this.setAt(72, 4, (short)3);
        this.setAt(71, 3, (short)3);
        this.setAt(70, 2, (short)3);
        for (int y2 = 0; y2 < 5; ++y2) {
            for (int x2 = this.width - y2; x2 < this.width; ++x2) {
                this.setAt(x2, y2, (short)2);
            }
        }
        for (int y3 = 5; y3 < 8; ++y3) {
            for (int x3 = this.width - 5; x3 < this.width; ++x3) {
                this.setAt(x3, y3, (short)2);
            }
        }
    }
    
    public boolean canStandOn(final int x, final int y) {
        return this.getAt(x, y) != 0;
    }
    
    public void setAt(final int x, final int y, final short value) {
        this.data[x + y * this.width] = value;
    }
    
    public short getAt(int x, final int y) {
        x = ((x < 0) ? 0 : x);
        if (y >= this.height) {
            return 255;
        }
        if (y >= 0) {
            return this.data[x + y * this.width];
        }
        return this.data[x];
    }
    
    public boolean isWalkable(final int x, final int y) {
        final int v = this.getAt(x, y);
        return v <= 1 || v == 3;
    }
    
    public boolean isBlocking(final int x, final int y) {
        final int v = this.getAt(x, y);
        return v == 2 || v == 4;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void centerAround(final int x) {
        if (x < 160) {
            this.ofs = 0;
            this.pos = 0;
        }
        else if (x - 160 > this.width - 11 << 5) {
            this.ofs = 0;
            this.pos = this.width - 11 << 5;
        }
        else {
            this.ofs = (x - 160 & 0x1F);
            this.pos = x - 160;
        }
    }
}
