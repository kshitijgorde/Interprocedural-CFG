// 
// Decompiled by Procyon v0.5.30
// 

class LEDMessage
{
    int[] letcol;
    boolean[][] msg;
    FuncInfo fi;
    int h;
    int w;
    int WIDTH;
    int HEIGHT;
    int TOTAL;
    Letters let;
    Index index;
    
    public LEDMessage(final int h, final int w, final Letters let) {
        this.h = h;
        this.w = w;
        this.HEIGHT = 5 * this.h;
        this.WIDTH = 5 * this.w;
        this.let = let;
    }
    
    void setmsg(final FuncInfo fi) {
        this.fi = fi;
        int n = 0;
        for (int i = 0; i < this.fi.text.length(); ++i) {
            n += this.let.getLetter(this.fi.text.charAt(i)).width + 1;
        }
        if (this.fi.centered && n <= this.w) {
            final int n2 = (this.w - n) / 2;
            this.fi.startspace = n2;
            this.fi.endspace = n2;
            if (n2 * 2 < this.w) {
                final FuncInfo fi2 = this.fi;
                ++fi2.startspace;
            }
        }
        this.TOTAL = n + this.fi.startspace + this.fi.endspace;
        this.msg = new boolean[this.TOTAL][this.h];
        for (int j = 0; j < this.TOTAL; ++j) {
            for (int k = 0; k < this.h; ++k) {
                this.msg[j][k] = false;
            }
        }
        this.letcol = new int[this.TOTAL];
        for (int l = 0; l < this.TOTAL; ++l) {
            this.letcol[l] = 1;
        }
        int startspace = this.fi.startspace;
        int char1 = 114;
        for (int n3 = 0; n3 < this.fi.text.length(); ++n3) {
            this.index = this.let.getLetter(this.fi.text.charAt(n3));
            if (this.fi.color.length() > 0) {
                try {
                    char1 = this.fi.color.charAt(n3);
                }
                catch (IndexOutOfBoundsException ex) {
                    System.out.println("Out of bounds in LEDMessage.setmsg");
                }
            }
            for (int width = this.index.width, n4 = 0; n4 < width; ++n4) {
                for (int n5 = 0; n5 < this.h; ++n5) {
                    try {
                        this.msg[startspace + n4][n5] = this.index.letter[n4][n5];
                    }
                    catch (IndexOutOfBoundsException ex2) {}
                    if (char1 == 114) {
                        this.letcol[startspace + n4] = 1;
                    }
                    else if (char1 == 103) {
                        this.letcol[startspace + n4] = 2;
                    }
                    else if (char1 == 98) {
                        this.letcol[startspace + n4] = 3;
                    }
                    else if (char1 == 121) {
                        this.letcol[startspace + n4] = 4;
                    }
                    else if (char1 == 111) {
                        this.letcol[startspace + n4] = 5;
                    }
                    else if (char1 == 112) {
                        this.letcol[startspace + n4] = 6;
                    }
                    else if (char1 == 119) {
                        this.letcol[startspace + n4] = 7;
                    }
                    else if (char1 == 99) {
                        this.letcol[startspace + n4] = 8;
                    }
                }
            }
            startspace += this.index.width + 1;
        }
    }
    
    boolean getLED(final int n, final int n2) {
        return n >= 0 && n < this.TOTAL && n2 >= 0 && n2 < this.h && this.msg[n][n2];
    }
    
    int getColor(final int n) {
        if (n >= 0 && n < this.TOTAL) {
            return this.letcol[n];
        }
        return 1;
    }
    
    int length() {
        return this.TOTAL;
    }
    
    boolean inRange(final int n) {
        return n >= 0 && n < this.TOTAL;
    }
}
