import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QueensvsKnights extends Applet
{
    static int count;
    static int queen;
    static int knight;
    int turn;
    int[][] array;
    int arrx;
    int arry;
    int orix;
    int oriy;
    int posx;
    int posy;
    int check;
    int check1;
    int empty;
    int[] bj;
    int[] bi;
    String str1;
    String str2;
    String str3;
    Font appFont;
    Image imageQueen;
    Image imageBoard;
    Image imageKnight;
    Image imageX;
    
    public QueensvsKnights() {
        this.turn = 0;
        this.array = new int[8][8];
        this.bj = new int[] { -2, -2, -1, 1, 2, 2, 1, -1 };
        this.bi = new int[] { -1, 1, 2, 2, 1, -1, -2, -2 };
    }
    
    public void NewMarkKnight(final int n, final int n2) {
        for (int i = 0; i < 8; ++i) {
            final int n3 = n + this.bi[i];
            final int n4 = n2 + this.bj[i];
            if (n3 >= 0 && n3 < 8 && n4 >= 0 && n4 < 8) {
                this.array[n3][n4] = 1;
            }
        }
    }
    
    public int checkEmpty(final int n, final int n2) {
        if (this.array[n][n2] != 0) {
            return 1;
        }
        return 0;
    }
    
    public int checkKnight(final int n, final int n2) {
        if (this.checkKnight1(n, n2)) {
            return 1;
        }
        if (this.checkKnight2(n, n2)) {
            return 1;
        }
        if (this.checkKnight3(n, n2)) {
            return 1;
        }
        if (this.checkKnight4(n, n2)) {
            return 1;
        }
        if (this.checkKnight5(n, n2)) {
            return 1;
        }
        if (this.checkKnight6(n, n2)) {
            return 1;
        }
        if (this.checkKnight7(n, n2)) {
            return 1;
        }
        if (this.checkKnight8(n, n2)) {
            return 1;
        }
        return 0;
    }
    
    public boolean checkKnight1(final int n, int i) {
        while (i >= 0) {
            if (this.array[n][i--] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight2(int n, int n2) {
        while (n <= 7 && n2 >= 0) {
            if (this.array[n++][n2--] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight3(int i, final int n) {
        while (i <= 7) {
            if (this.array[i++][n] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight4(int n, int n2) {
        while (n <= 7 && n2 <= 7) {
            if (this.array[n++][n2++] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight5(final int n, int i) {
        while (i <= 7) {
            if (this.array[n][i++] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight6(int n, int n2) {
        while (n >= 0 && n2 <= 7) {
            if (this.array[n--][n2++] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight7(int i, final int n) {
        while (i >= 0) {
            if (this.array[i--][n] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkKnight8(int n, int n2) {
        while (n >= 0 && n2 >= 0) {
            if (this.array[n--][n2--] == 2) {
                return true;
            }
        }
        return false;
    }
    
    public void init() {
        this.setBackground(Color.blue);
        this.appFont = new Font("Verdana", 1, 14);
        QueensvsKnights.count = 0;
        QueensvsKnights.queen = 0;
        QueensvsKnights.knight = 0;
        this.imageQueen = this.getImage(this.getCodeBase(), "queenShoji.gif");
        this.imageBoard = this.getImage(this.getCodeBase(), "boardGrid.gif");
        this.imageKnight = this.getImage(this.getCodeBase(), "knightshoji3d.gif");
        this.imageX = this.getImage(this.getCodeBase(), "pointX.gif");
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                this.array[i][j] = 0;
            }
        }
    }
    
    public void mark1(final int n, final int n2) {
        for (int n3 = n + 1; n3 <= 7 && n2 <= 7; this.array[n3++][n2] = 1) {}
    }
    
    public void mark2(final int n, final int n2) {
        for (int n3 = n2 - 1; n <= 7 && n3 >= 0; this.array[n][n3--] = 1) {}
    }
    
    public void mark3(final int n, final int n2) {
        for (int n3 = n - 1; n3 >= 0 && n2 <= 7; this.array[n3--][n2] = 1) {}
    }
    
    public void mark4(final int n, final int n2) {
        for (int n3 = n2 + 1; n <= 7 && n3 <= 7; this.array[n][n3++] = 1) {}
    }
    
    public void mark5(final int n, final int n2) {
        for (int n3 = n - 1, n4 = n2 - 1; n3 >= 0 && n4 >= 0; this.array[n3--][n4--] = 1) {}
    }
    
    public void mark6(final int n, final int n2) {
        for (int n3 = n + 1, n4 = n2 + 1; n3 <= 7 && n4 <= 7; this.array[n3++][n4++] = 1) {}
    }
    
    public void mark7(final int n, final int n2) {
        for (int n3 = n - 1, n4 = n2 + 1; n3 >= 0 && n4 <= 7; this.array[n3--][n4++] = 1) {}
    }
    
    public void mark8(final int n, final int n2) {
        for (int n3 = n + 1, n4 = n2 - 1; n3 <= 7 && n4 >= 0; this.array[n3++][n4--] = 1) {}
    }
    
    public boolean mouseDown(final Event event, final int orix, final int oriy) {
        ++QueensvsKnights.count;
        this.orix = orix;
        this.oriy = oriy;
        this.arrx = this.orix / 64;
        this.arry = this.oriy / 64;
        if ((event.modifiers & 0x4) != 0x0) {
            this.check = this.checkKnight(this.arrx, this.arry);
            this.empty = this.checkEmpty(this.arrx, this.arry);
            if (this.empty != 1 && this.check != 1) {
                this.turn = 1;
                this.array[this.arrx][this.arry] = 3;
                ++QueensvsKnights.queen;
                this.queenmark(this.arrx, this.arry);
                this.repaint();
                return true;
            }
            return false;
        }
        else {
            this.empty = this.checkEmpty(this.arrx, this.arry);
            if (this.empty != 1) {
                this.turn = 1;
                this.array[this.arrx][this.arry] = 2;
                ++QueensvsKnights.knight;
                this.NewMarkKnight(this.arrx, this.arry);
                this.repaint();
                return true;
            }
            return false;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.drawImage(this.imageBoard, 0, 0, 512, 512, this);
        if (this.turn == 1) {
            this.str1 = String.valueOf(QueensvsKnights.count);
            this.str2 = String.valueOf(QueensvsKnights.queen);
            this.str3 = String.valueOf(QueensvsKnights.knight);
            graphics.setFont(this.appFont);
            graphics.drawString("Mouse Clicks: " + this.str1, 520, 20);
            graphics.drawString("Queens Used: " + this.str2, 520, 40);
            graphics.drawString("Knights Used: " + this.str3, 520, 60);
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    if (this.array[i][j] == 3) {
                        graphics.drawImage(this.imageQueen, i * 64, j * 64, 64, 64, this);
                    }
                    if (this.array[i][j] == 2) {
                        graphics.drawImage(this.imageKnight, i * 64, j * 64, 64, 64, this);
                    }
                    if (this.array[i][j] == 1) {
                        graphics.drawImage(this.imageX, i * 64, j * 64, 64, 64, this);
                    }
                }
            }
        }
    }
    
    public void queenmark(final int n, final int n2) {
        if (n >= 0 && n <= 7 && n2 >= 0 && n2 <= 7) {
            this.mark1(n, n2);
            this.mark2(n, n2);
            this.mark3(n, n2);
            this.mark4(n, n2);
            this.mark5(n, n2);
            this.mark6(n, n2);
            this.mark7(n, n2);
            this.mark8(n, n2);
        }
    }
}
