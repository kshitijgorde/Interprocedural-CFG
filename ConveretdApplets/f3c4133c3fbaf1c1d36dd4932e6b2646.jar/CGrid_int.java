import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class CGrid_int extends Canvas
{
    Grid_int App;
    int ab;
    int ac;
    int ad;
    int ae;
    boolean af;
    boolean ag;
    boolean Sudoku;
    boolean jl;
    boolean jm;
    Point ga;
    Color CurColor;
    Color SelColor;
    int B;
    boolean ba;
    square_int[][] jn;
    Font AC;
    Font AD;
    int AE;
    int AF;
    int AG;
    int GA;
    int FA;
    int EA;
    int DA;
    int[] CA;
    String BA;
    String BC;
    String BD;
    boolean BE;
    boolean BF;
    boolean FB;
    Color GridColor;
    Color BlockColor;
    Color FontColor;
    Color NumColor;
    Color WrongColor;
    Point FG;
    private int jo;
    int GF;
    boolean kf;
    private boolean jp;
    boolean HI;
    
    public CGrid_int() {
        this.af = true;
        this.ag = false;
        this.Sudoku = false;
        this.ga = null;
        this.B = 22;
        this.AC = null;
        this.DA = 2;
        this.BA = null;
        this.BD = "";
        this.BE = false;
        this.WrongColor = Color.red;
        this.jo = 0;
        this.GF = 0;
        this.kf = false;
        this.HI = false;
    }
    
    String readShrtString(final DataInputStream dataInputStream) throws IOException {
        final byte[] array = new byte[dataInputStream.readByte() & 0xFF];
        dataInputStream.readFully(array);
        return new String(array, this.App.t);
    }
    
    void HD(final DataInputStream dataInputStream) throws IOException {
        this.setBackground(this.App.colParam("GRIDBACKCOLOR", Color.white));
        this.FG = new Point(1, 1);
        this.ad = 1000;
        this.ae = 1000;
        this.ab = dataInputStream.readByte();
        this.ac = dataInputStream.readByte();
        final byte byte1 = dataInputStream.readByte();
        this.GF = (((byte1 & 0x4) != 0x0) ? 2 : 0);
        final byte b = (byte)(byte1 & 0x3);
        this.kf = ((byte1 & 0x8) != 0x0);
        if (this.kf) {
            this.WrongColor = this.App.colParam("WRONGCOLOR", new Color(dataInputStream.readInt()));
        }
        this.ba = (b == 2);
        this.B = dataInputStream.readByte();
        if (this.ba) {
            this.FA = this.B - this.B / 5 - 2;
        }
        else {
            this.FA = (this.B - 2) * 7 / 11;
        }
        this.EA = (this.B + 2) * 4 / 11;
        if (this.EA < 8) {
            this.EA = 8;
        }
        this.AC = new Font("SansSerif", 0, this.EA);
        this.AD = new Font("SansSerif", 1, this.FA);
        final byte byte2 = dataInputStream.readByte();
        this.Sudoku = ((byte2 & 0x4) != 0x0);
        this.ag = ((byte2 & 0x1) != 0x0);
        if (this.ag || (byte2 & 0x2) != 0x0) {
            final byte[] array = new byte[dataInputStream.readByte()];
            dataInputStream.readFully(array);
            this.BA = new String(array, this.App.t);
        }
        this.GridColor = this.App.colParam("GRIDCOLOR", new Color(dataInputStream.readInt()));
        this.BlockColor = this.App.colParam("BLOCKCOLOR", new Color(dataInputStream.readInt()));
        this.FontColor = this.App.colParam("FONTCOLOR", new Color(dataInputStream.readInt()));
        if (this.App.V7) {
            this.NumColor = new Color(dataInputStream.readInt());
        }
        else {
            this.NumColor = this.FontColor;
        }
        this.NumColor = this.App.colParam("NUMCOLOR", this.NumColor);
        this.SelColor = this.App.colParam("SELCOLOR", new Color(dataInputStream.readInt()));
        this.CurColor = this.App.colParam("CURCOLOR", new Color(dataInputStream.readInt()));
        String s = null;
        final byte[] array2 = new byte[this.ab * this.ac];
        dataInputStream.readFully(array2);
        final String s2 = new String(array2, this.App.t);
        if (b == 0) {
            final byte[] array3 = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(array3);
            s = new String(array3, this.App.t);
        }
        dataInputStream.readFully(array2);
        this.jn = new square_int[this.ab][this.ac];
        int n = 0;
        String s3 = null;
        int n2 = 0;
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                if ((array2[i * this.ab + j] & 0x1) != 0x0) {
                    if (b == 0) {
                        s3 = s.substring(n * 3, n * 3 + 3).trim();
                    }
                    else if (b != 2) {
                        s3 = Integer.toString(n + 1);
                    }
                    ++n;
                }
                else {
                    s3 = null;
                }
                final char char1 = s2.charAt(i * this.ab + j);
                if (char1 != ' ' && char1 != '#') {
                    ++n2;
                }
                this.jn[j][i] = new square_int(char1, s3, array2[i * this.ab + j], this);
                if ((array2[i * this.ab + j] & 0x40) != 0x0) {
                    this.jn[j][i].RtNum = this.readShrtString(dataInputStream);
                }
                if (this.ga == null && this.jn[j][i].isLet()) {
                    this.ga = new Point(j, i);
                }
            }
        }
        this.BF = dataInputStream.readBoolean();
        if (this.BF) {
            final byte[] array4 = new byte[n2];
            dataInputStream.readFully(array4);
            this.BC = new String(array4, this.App.t);
        }
        else {
            this.kf = false;
        }
        int n3 = 0;
        for (int k = 0; k < this.ac; ++k) {
            for (int l = 0; l < this.ab; ++l) {
                final square_int square_int = this.jn[l][k];
                if (square_int.isLet() && this.BF) {
                    square_int.IT = this.BC.charAt(n3);
                    ++n3;
                }
                if ((square_int.JA & 0x8) != 0x0) {
                    square_int.JC = new Color(dataInputStream.readInt());
                }
                if ((square_int.JA & 0x10) != 0x0) {
                    square_int.JB = new Color(dataInputStream.readInt());
                }
                square_int.Rx = l * this.B + 1;
                square_int.Ry = k * this.B + 1;
                square_int.Rw = this.B - 1;
                square_int.Rh = this.B - 1;
                if ((square_int.JA & 0x2) != 0x0) {
                    final square_int square_int2 = square_int;
                    ++square_int2.Rx;
                    final square_int square_int3 = square_int;
                    --square_int3.Rw;
                    this.DA = 3;
                }
                else if (square_int.IU == ' ' && (l == 0 || this.jn[l - 1][k].IU == ' ')) {
                    final square_int square_int4 = square_int;
                    --square_int4.Rx;
                    final square_int square_int5 = square_int;
                    ++square_int5.Rw;
                    if (l == 0 && this.jl) {
                        final square_int square_int6 = square_int;
                        square_int6.Rx -= 2;
                        final square_int square_int7 = square_int;
                        square_int7.Rw += 2;
                    }
                }
                if (l != this.ab - 1 && (this.jn[l + 1][k].JA & 0x2) != 0x0) {
                    final square_int square_int8 = square_int;
                    --square_int8.Rw;
                }
                else if (square_int.IU == ' ' && (l == this.ab - 1 || this.jn[l + 1][k].IU == ' ')) {
                    final square_int square_int9 = square_int;
                    ++square_int9.Rw;
                    if (l == this.ab - 1 && this.jl) {
                        final square_int square_int10 = square_int;
                        square_int10.Rw += 2;
                    }
                }
                if ((square_int.JA & 0x4) != 0x0) {
                    final square_int square_int11 = square_int;
                    ++square_int11.Ry;
                    final square_int square_int12 = square_int;
                    --square_int12.Rh;
                }
                else if (square_int.IU == ' ' && (k == 0 || this.jn[l][k - 1].IU == ' ')) {
                    final square_int square_int13 = square_int;
                    --square_int13.Ry;
                    final square_int square_int14 = square_int;
                    ++square_int14.Rh;
                    if (k == 0 && this.jl) {
                        final square_int square_int15 = square_int;
                        square_int15.Ry -= 2;
                        final square_int square_int16 = square_int;
                        square_int16.Rh += 2;
                    }
                }
                if (k != this.ac - 1 && (this.jn[l][k + 1].JA & 0x4) != 0x0) {
                    final square_int square_int17 = square_int;
                    --square_int17.Rh;
                }
                else if (square_int.IU == ' ' && (k == this.ac - 1 || this.jn[l][k + 1].IU == ' ')) {
                    final square_int square_int18 = square_int;
                    ++square_int18.Rh;
                    if (k == this.ac - 1 && this.jl) {
                        final square_int square_int19 = square_int;
                        square_int19.Rh += 2;
                    }
                }
            }
        }
        this.GA = this.ab * this.B + 1 + this.App.U * 2;
        this.AG = this.ac * this.B + 1 + this.App.U * 2;
        if (this.App.jk) {
            this.GA += this.B;
            this.AG += this.B;
            this.jo = this.B;
        }
    }
    
    int HE() {
        int n = 0;
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                if (this.jn[j][i].IU == '?' && (!this.BF || this.jn[j][i].IT != '?')) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public void print(final Graphics graphics) {
        this.FB = true;
        try {
            this.paint(graphics);
        }
        finally {
            this.FB = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.AF == 0) {
            this.App.IE(graphics);
        }
        if (this.App.jk) {
            graphics.setColor(this.FontColor);
            graphics.setFont(new Font("SansSerif", 0, (this.B - 2) * 8 / 15));
            for (int i = 1; i <= this.ab; ++i) {
                graphics.drawString(this.App.QQ[i - 1], i * this.B + (this.B - graphics.getFontMetrics().stringWidth(this.App.QQ[i - 1])) / 2, this.AF);
            }
            for (int j = 1; j <= this.ac; ++j) {
                graphics.drawString(this.App.RR[j - 1], this.B - this.B / 6 - graphics.getFontMetrics().stringWidth(this.App.RR[j - 1]), this.B * 2 / 3 + j * this.B);
            }
            if (this.jl) {
                graphics.translate(2 + this.B, 2 + this.B);
            }
            else {
                graphics.translate(this.B, this.B);
            }
        }
        else if (this.jl) {
            graphics.translate(2, 2);
        }
        graphics.setColor(this.GridColor);
        for (int k = 0; k <= this.ab; ++k) {
            int n = 1;
            int l = 0;
            while (l < this.ac) {
                for (l = n; ((k < this.ab && this.jn[k][l - 1].JM()) || (k > 0 && this.jn[k - 1][l - 1].JM())) && l < this.ac; ++l) {}
                if (l == this.ac && ((k > 0 && this.jn[k - 1][this.ac - 1].JM()) || (k < this.ab && this.jn[k][this.ac - 1].JM()))) {
                    ++l;
                }
                if (l != n) {
                    if (this.jl && (k == 0 || k == this.ab)) {
                        if (k == 0) {
                            graphics.fillRect(-2, (n - 1) * this.B - 2, 3, this.B * (l - n) + 5);
                        }
                        if (k == this.ab) {
                            graphics.fillRect(this.B * k, (n - 1) * this.B - 2, 3, this.B * (l - n) + 5);
                        }
                    }
                    else {
                        graphics.drawLine(this.B * k, (n - 1) * this.B, this.B * k, this.B * (l - 1));
                    }
                }
                for (n = l; n < this.ac && ((k > 0 && !this.jn[k - 1][n - 1].JM()) || (k == 0 && !this.jn[0][n - 1].JM())) && ((k < this.ab && !this.jn[k][n - 1].JM()) || k == this.ab); ++n) {}
            }
        }
        for (int n2 = 0; n2 <= this.ac; ++n2) {
            int n3 = 1;
            int n4 = 0;
            while (n4 < this.ab) {
                for (n4 = n3; ((n2 < this.ac && this.jn[n4 - 1][n2].JM()) || (n2 > 0 && this.jn[n4 - 1][n2 - 1].JM())) && n4 < this.ab; ++n4) {}
                if (n4 == this.ab && ((n2 > 0 && this.jn[this.ab - 1][n2 - 1].JM()) || (n2 < this.ac && this.jn[this.ab - 1][n2].JM()))) {
                    ++n4;
                }
                if (n4 != n3) {
                    if (this.jl && (n2 == 0 || n2 == this.ac)) {
                        if (n2 == 0) {
                            graphics.fillRect(this.B * (n3 - 1) - 2, -2, this.B * (n4 - n3) + 5, 3);
                        }
                        if (n2 == this.ac) {
                            graphics.fillRect((n3 - 1) * this.B - 2, n2 * this.B, this.B * (n4 - n3) + 5, 3);
                        }
                    }
                    else {
                        graphics.drawLine(this.B * (n3 - 1), n2 * this.B, this.B * (n4 - 1), this.B * n2);
                    }
                }
                for (n3 = n4; n3 < this.ab && ((n2 > 0 && !this.jn[n3 - 1][n2 - 1].JM()) || (n2 == 0 && !this.jn[n3 - 1][0].JM())) && ((n2 < this.ac && !this.jn[n3 - 1][n2].JM()) || n2 == this.ac); ++n3) {}
            }
        }
        graphics.setFont(this.AC);
        for (int n5 = 0; n5 < this.ac; ++n5) {
            for (int n6 = 0; n6 < this.ab; ++n6) {
                final square_int square_int = this.jn[n6][n5];
                if (square_int.IX && !this.FB) {
                    if (this.ga.x == n6 && this.ga.y == n5) {
                        graphics.setColor(this.CurColor);
                    }
                    else {
                        graphics.setColor(this.SelColor);
                    }
                    graphics.fillRect(square_int.Rx, square_int.Ry, square_int.Rw, square_int.Rh);
                    square_int.IW = false;
                }
                else if (square_int.JC != null && (square_int.JA & 0x20) == 0x0) {
                    graphics.setColor(square_int.JC);
                    graphics.fillRect(square_int.Rx, square_int.Ry, square_int.Rw, square_int.Rh);
                }
                if ((square_int.JA & 0x20) != 0x0) {
                    if (square_int.JC == null) {
                        graphics.setColor(this.GridColor);
                    }
                    else {
                        graphics.setColor(square_int.JC);
                    }
                    graphics.drawOval(square_int.Rx, square_int.Ry, square_int.Rw, square_int.Rh);
                    graphics.drawOval(square_int.Rx + 1, square_int.Ry + 1, square_int.Rw - 2, square_int.Rh - 2);
                    graphics.drawOval(square_int.Rx, square_int.Ry + 1, square_int.Rw, square_int.Rh - 2);
                    graphics.drawOval(square_int.Rx + 1, square_int.Ry, square_int.Rw - 2, square_int.Rh);
                }
                if (this.jl && square_int.IU == ' ') {
                    graphics.setColor(this.GridColor);
                    if (n6 > 0 && this.jn[n6 - 1][n5].IU != ' ') {
                        graphics.fillRect(square_int.Rx, square_int.Ry, 2, square_int.Rh);
                    }
                    if (n6 < this.ab - 1 && this.jn[n6 + 1][n5].IU != ' ') {
                        graphics.fillRect(square_int.Rx + square_int.Rw - 2, square_int.Ry, 2, square_int.Rh);
                    }
                    if (n5 > 0 && this.jn[n6][n5 - 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx, square_int.Ry, square_int.Rw, 2);
                    }
                    if (n5 < this.ac - 1 && this.jn[n6][n5 + 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx, square_int.Ry + square_int.Rh - 2, square_int.Rw, 2);
                    }
                    if (n6 > 0 && n5 > 0 && this.jn[n6 - 1][n5].IU == ' ' && this.jn[n6][n5 - 1].IU == ' ' && this.jn[n6 - 1][n5 - 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx, square_int.Ry, 3, 3);
                    }
                    if (n6 > 0 && n5 < this.ac - 1 && this.jn[n6 - 1][n5].IU == ' ' && this.jn[n6][n5 + 1].IU == ' ' && this.jn[n6 - 1][n5 + 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx, square_int.Ry + square_int.Rh - 3, 3, 3);
                    }
                    if (n6 < this.ab - 1 && n5 > 0 && this.jn[n6 + 1][n5].IU == ' ' && this.jn[n6][n5 - 1].IU == ' ' && this.jn[n6 + 1][n5 - 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx + square_int.Rw - 3, square_int.Ry, 3, 3);
                    }
                    if (n6 < this.ab - 1 && n5 < this.ac - 1 && this.jn[n6 + 1][n5].IU == ' ' && this.jn[n6][n5 + 1].IU == ' ' && this.jn[n6 + 1][n5 + 1].IU != ' ') {
                        graphics.fillRect(square_int.Rx + square_int.Rw - 3, square_int.Ry + square_int.Rh - 3, 3, 3);
                    }
                }
                final String iv = square_int.IV;
                if (square_int.RtNum != null) {
                    graphics.setColor(this.NumColor);
                    graphics.drawString(square_int.RtNum, (n6 + 1) * this.B - this.DA - graphics.getFontMetrics().stringWidth(square_int.RtNum), n5 * this.B + this.AE);
                }
                if (iv != null) {
                    graphics.setColor(this.NumColor);
                    graphics.drawString(iv, n6 * this.B + this.DA, n5 * this.B + this.AE);
                }
                else if (square_int.IU == '#' && square_int.JC == null) {
                    graphics.setColor(this.BlockColor);
                    graphics.fillRect(square_int.Rx + this.GF, square_int.Ry + this.GF, square_int.Rw - this.GF * 2, square_int.Rh - this.GF * 2);
                }
                if ((square_int.JA & 0x4) != 0x0) {
                    graphics.setColor(this.GridColor);
                    graphics.fillRect(n6 * this.B, n5 * this.B - 1, this.B, 3);
                }
                if ((square_int.JA & 0x2) != 0x0) {
                    graphics.setColor(this.GridColor);
                    graphics.fillRect(n6 * this.B - 1, n5 * this.B, 3, this.B);
                }
            }
        }
        graphics.setFont(this.AD);
        graphics.setColor(this.FontColor);
        for (int n7 = 0; n7 < this.ac; ++n7) {
            for (int n8 = 0; n8 < this.ab; ++n8) {
                final square_int square_int2 = this.jn[n8][n7];
                final char iu = square_int2.IU;
                if (iu != '?' && iu != '#' && iu != ' ') {
                    if (square_int2.JB != null) {
                        graphics.setColor(square_int2.JB);
                    }
                    final boolean b = this.kf && Character.toUpperCase(square_int2.IT) != Character.toUpperCase(iu);
                    if (b) {
                        graphics.setColor(this.WrongColor);
                    }
                    graphics.drawString(String.valueOf(iu), n8 * this.B + 1 + (this.B - this.App.FM.charWidth(iu)) / 2, n7 * this.B + this.AF);
                    if (square_int2.JB != null || b) {
                        graphics.setColor(this.FontColor);
                    }
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.App.jk) {
            if (this.jl) {
                graphics.translate(2 + this.B, 2 + this.B);
            }
            else {
                graphics.translate(this.B, this.B);
            }
        }
        else if (this.jl) {
            graphics.translate(2, 2);
        }
        if (this.AF != 0) {
            for (int i = 0; i < this.ac; ++i) {
                for (int j = 0; j < this.ab; ++j) {
                    if (this.jn[j][i].IW) {
                        this.jn[j][i].JL(graphics, j, i);
                    }
                }
            }
        }
    }
    
    void HF(final Point point) {
        int x = point.x;
        int y = point.y;
        if (!this.jp) {
            while (y >= 0 && this.jn[x][y].isLet()) {
                --y;
                if (!this.jm && (this.jn[x][y + 1].JA & 0x4) != 0x0) {
                    break;
                }
            }
            ++y;
        }
        else {
            while (x >= 0 && this.jn[x][y].isLet()) {
                --x;
                if (!this.jm && (this.jn[x + 1][y].JA & 0x2) != 0x0) {
                    break;
                }
            }
            ++x;
        }
        final square_int square_int = this.jn[x][y];
        if (square_int.IZ[this.jp] || square_int.IQ[this.jp] == 0) {
            point.x = x;
            point.y = y;
        }
        else {
            ++x;
            ++y;
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < this.ab; ++j) {
                    for (int k = 0; k < this.ac; ++k) {
                        final square_int square_int2 = this.jn[j][k];
                        if (square_int2.IQ[i] == x && square_int2.R[i] == y && square_int2.JD[i] == 1 == this.jp) {
                            point.x = j;
                            point.y = k;
                            this.jp = (i == 1);
                            this.HF(point);
                            return;
                        }
                    }
                }
            }
        }
    }
    
    String HG(final char c) {
        byte[] array;
        try {
            array = new Character(c).toString().getBytes(this.App.OutputCharset);
        }
        catch (IOException ex) {
            array = new Character(c).toString().getBytes();
        }
        final byte b = array[0];
        final char c2 = (char)b;
        if ((array.length == 1 && b >= 65 && b <= 90) || (b >= 97 && b <= 122) || (b >= 48 && b >= 57) || c2 == '-') {
            return new Character(c2).toString();
        }
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            string = string + '%' + Integer.toString(array[i] & 0xFF, 16);
        }
        return string;
    }
    
    void HJ(final int n, final int n2, final boolean b) {
        int x = n;
        int y = n2;
        if (this.Sudoku) {
            this.jn[n][n2].JF(true);
            return;
        }
        if (b) {
            while (x >= 0 && this.jn[x][y].isLet()) {
                this.jn[x][y].JF(true);
                --x;
                if (!this.jm && (this.jn[x + 1][y].JA & 0x2) != 0x0) {
                    break;
                }
            }
            this.FG.x = x + 1;
            this.FG.y = y;
            for (int n3 = n + 1; n3 < this.ab && this.jn[n3][y].isLet(); ++n3) {
                if (!this.jm && (this.jn[n3][y].JA & 0x2) != 0x0) {
                    break;
                }
                this.jn[n3][y].JF(true);
            }
        }
        else {
            while (y >= 0 && this.jn[x][y].isLet()) {
                this.jn[x][y].JF(true);
                --y;
                if (!this.jm && (this.jn[x][y + 1].JA & 0x4) != 0x0) {
                    break;
                }
            }
            this.FG.y = y + 1;
            this.FG.x = x;
            for (int n4 = n2 + 1; n4 < this.ac && this.jn[x][n4].isLet() && (this.jm || (this.jn[x][n4].JA & 0x4) == 0x0); ++n4) {
                this.jn[x][n4].JF(true);
            }
        }
        final int n5 = b ? 1 : 0;
        final square_int square_int = this.jn[this.FG.x][this.FG.y];
        if (!this.HI && square_int.IQ[n5] != 0 && !square_int.IZ[n5]) {
            this.jp = b;
            this.HF(this.FG);
            this.HI = true;
            this.HJ(this.FG.x, this.FG.y, this.jp);
            return;
        }
        if ((square_int.IQ[n5] == 0 || square_int.IZ[n5]) && this.App.AA == 2) {
            this.App.OO[0].jc = b;
            this.App.OO[1].jc = !b;
            this.App.OO[!b].ix(this.FG, b);
            if (b == this.af) {
                this.FG.move(this.ga.x, this.ga.y);
                this.jp = !this.af;
                this.HF(this.FG);
                this.App.OO[b].ix(this.FG, this.jp);
            }
            else {
                this.App.OO[b].jh(-1);
            }
        }
        if (square_int.IQ[n5] > 0) {
            this.HJ(square_int.IQ[n5] - 1, square_int.R[n5] - 1, square_int.JD[n5] == 1);
        }
    }
    
    void HK(final int n, final int n2) {
        if (n < this.ab && n2 < this.ac && n >= 0 && n2 >= 0 && this.jn[n][n2].isLet()) {
            for (int i = 0; i < this.ac; ++i) {
                for (int j = 0; j < this.ab; ++j) {
                    this.jn[j][i].JF(false);
                }
            }
            this.ga.move(n, n2);
            final square_int square_int = this.jn[n][n2];
            if (!this.App.OneLetWds && !this.Sudoku) {
                if (this.af && ((n == this.ab - 1 && (!this.jn[n - 1][n2].isLet() || (!this.jm && (square_int.JA & 0x2) != 0x0))) || (n == 0 && (!this.jn[1][n2].isLet() || (!this.jm && (this.jn[1][n2].JA & 0x2) != 0x0))) || (n > 0 && n < this.ab - 1 && (!this.jn[n - 1][n2].isLet() || (!this.jm && (square_int.JA & 0x2) != 0x0)) && (!this.jn[n + 1][n2].isLet() || (!this.jm && (this.jn[n + 1][n2].JA & 0x2) != 0x0))))) {
                    this.HL();
                }
                else if (!this.af && ((n2 == this.ac - 1 && (!this.jn[n][n2 - 1].isLet() || (!this.jm && (square_int.JA & 0x4) != 0x0))) || (n2 == 0 && (!this.jn[n][1].isLet() || (!this.jm && (this.jn[n][1].JA & 0x4) != 0x0))) || (n2 > 0 && n2 < this.ac - 1 && (!this.jn[n][n2 - 1].isLet() || (!this.jm && (square_int.JA & 0x4) != 0x0)) && (!this.jn[n][n2 + 1].isLet() || (!this.jm && (this.jn[n][n2 + 1].JA & 0x4) != 0x0))))) {
                    this.HL();
                }
            }
            this.HI = false;
            this.HJ(n, n2, this.af);
            if (this.isVisible()) {
                this.repaint();
            }
        }
    }
    
    void HL() {
        this.af = !this.af;
    }
    
    void HM(final char c) {
        if (!this.ag && this.jn[this.ga.x][this.ga.y].IY) {
            return;
        }
        if (this.ag) {
            if (!this.jn[this.ga.x][this.ga.y].IY) {
                final int index = this.BD.indexOf(this.jn[this.ga.x][this.ga.y].IU);
                if (index != -1) {
                    this.BD = this.BD.substring(0, index) + ((index == this.BD.length() - 1) ? "" : this.BD.substring(index + 1));
                }
            }
            if (c != '?') {
                this.BD += String.valueOf(c);
            }
            this.App.ID(this.App.getGraphics());
        }
        this.jn[this.ga.x][this.ga.y].JK(c);
        if (this.ag) {
            final String iv = this.jn[this.ga.x][this.ga.y].IV;
            if (iv != null) {
                for (int i = 0; i < this.ac; ++i) {
                    for (int j = 0; j < this.ab; ++j) {
                        if (this.jn[j][i].IV != null && iv.compareTo(this.jn[j][i].IV) == 0) {
                            this.jn[j][i].JK(c);
                        }
                    }
                }
            }
        }
        this.HN();
    }
    
    void HN() {
        if (!this.BE && (this.App.ka != null || this.App.SS.length() != 0 || this.App.Q != null || this.App.P != null) && this.HE() == 0 && (!this.App.DD || this.HV())) {
            this.BE = true;
            if (this.App.ka != null) {
                this.App.ka.kd = true;
            }
            if (this.App.P != null) {
                this.App.ShowCompletionPic();
            }
            else if (this.App.Q != null) {
                this.App.IF(this.App.Q, this.App.getParameter("FINISHEDURLFRAME"), true);
            }
            else if (this.App.SS.length() != 0) {
                this.App.ShowCompletionMessage();
            }
        }
    }
    
    void HO(final MouseEvent mouseEvent) {
        final int n = (mouseEvent.getX() - this.App.U - this.jo) / this.B;
        final int n2 = (mouseEvent.getY() - this.App.U - this.jo) / this.B;
        this.requestFocus();
        if (n == this.ga.x && n2 == this.ga.y && (this.App.OneLetWds || (!this.af && (this.ga.x == this.ab - 1 || this.jn[this.ga.x + 1][this.ga.y].isLet() || this.ga.x == 0 || this.jn[this.ga.x - 1][this.ga.y].isLet())) || (this.af && (this.ga.y == this.ac - 1 || this.jn[this.ga.x][this.ga.y + 1].isLet() || this.ga.y == 0 || this.jn[this.ga.x][this.ga.y - 1].isLet())))) {
            this.HL();
        }
        this.HK(n, n2);
    }
    
    void GridKeyTyped(char upperCase) {
        if (upperCase == 'i' && this.App.t == "Cp1254") {
            upperCase = '\u0130';
        }
        else {
            final square_int square_int = this.jn[this.ga.x][this.ga.y];
            if (Character.toUpperCase(square_int.IT) == square_int.IT) {
                upperCase = Character.toUpperCase(upperCase);
            }
        }
        this.HY(upperCase);
    }
    
    boolean HP(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 10: {
                if (this.App.AA == 2) {
                    ClueView_int clueView_int = this.App.OO[!this.af];
                    int n;
                    if (clueView_int.iy < clueView_int.iq - 1) {
                        n = clueView_int.iy + 1;
                    }
                    else {
                        clueView_int = this.App.OO[this.af];
                        n = 0;
                        if (clueView_int.iq == 0) {
                            clueView_int = this.App.OO[!this.af];
                        }
                    }
                    this.af = (clueView_int.ib[n] == 1);
                    this.HK(clueView_int.ic[n] - 1, clueView_int.id[n] - 1);
                    break;
                }
                break;
            }
            case 127: {
                this.HM('?');
                this.repaint();
                break;
            }
            case 37: {
                int x;
                if (!this.af && !this.Sudoku && (this.App.OneLetWds || ((this.jn[this.ga.x][this.ga.y].JA & 0x2) == 0x0 && (this.ga.x == 0 || this.jn[this.ga.x - 1][this.ga.y].isLet())))) {
                    this.HL();
                    x = this.ga.x;
                }
                else {
                    for (x = this.ga.x - 1; x >= 0 && !this.jn[x][this.ga.y].isLet(); --x) {}
                }
                this.HK(x, this.ga.y);
                break;
            }
            case 39: {
                int x2;
                if (!this.af && !this.Sudoku && (this.App.OneLetWds || this.ga.x == this.ab - 1 || (this.jn[this.ga.x + 1][this.ga.y].isLet() && (this.jn[this.ga.x + 1][this.ga.y].JA & 0x2) == 0x0))) {
                    this.HL();
                    x2 = this.ga.x;
                }
                else {
                    for (x2 = this.ga.x + 1; x2 < this.ab && !this.jn[x2][this.ga.y].isLet(); ++x2) {}
                }
                this.HK(x2, this.ga.y);
                break;
            }
            case 38: {
                int y;
                if (this.af && !this.Sudoku && (this.App.OneLetWds || ((this.jn[this.ga.x][this.ga.y].JA & 0x4) == 0x0 && (this.ga.y == 0 || this.jn[this.ga.x][this.ga.y - 1].isLet())))) {
                    this.HL();
                    y = this.ga.y;
                }
                else {
                    for (y = this.ga.y - 1; y >= 0 && !this.jn[this.ga.x][y].isLet(); --y) {}
                }
                this.HK(this.ga.x, y);
                break;
            }
            case 40: {
                int y2;
                if (this.af && !this.Sudoku && (this.App.OneLetWds || this.ga.y == this.ac - 1 || (this.jn[this.ga.x][this.ga.y + 1].isLet() && (this.jn[this.ga.x][this.ga.y + 1].JA & 0x4) == 0x0))) {
                    this.HL();
                    y2 = this.ga.y;
                }
                else {
                    for (y2 = this.ga.y + 1; y2 < this.ac && !this.jn[this.ga.x][y2].isLet(); ++y2) {}
                }
                this.HK(this.ga.x, y2);
                break;
            }
            case 8: {
                final int x3 = this.ga.x;
                final int y3 = this.ga.y;
                if (this.af && this.ga.x > 0) {
                    this.HK(x3 - 1, y3);
                    if (this.ga.x != x3) {
                        this.HM('?');
                        break;
                    }
                    break;
                }
                else {
                    if (this.af || this.ga.y <= 0) {
                        break;
                    }
                    this.HK(x3, y3 - 1);
                    if (this.ga.y != y3) {
                        this.HM('?');
                        break;
                    }
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    void HQ(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getX() - this.App.U - this.jo;
        final int n2 = mouseEvent.getY() - this.App.U - this.jo;
        if (Math.abs(n / this.B - this.ga.x) > Math.abs(n2 / this.B - this.ga.y)) {
            if (!this.af && n / this.B != this.ga.x) {
                this.af = !this.af;
                this.HK(this.ga.x, this.ga.y);
            }
        }
        else if (this.af && n2 / this.B != this.ga.y) {
            this.af = !this.af;
            this.HK(this.ga.x, this.ga.y);
        }
    }
    
    void HR() {
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                if (this.jn[j][i].IX) {
                    this.jn[j][i].JK(this.jn[j][i].IT);
                }
            }
        }
    }
    
    void HS() {
        this.jn[this.ga.x][this.ga.y].JK(this.jn[this.ga.x][this.ga.y].IT);
    }
    
    void HT() {
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                final square_int square_int = this.jn[j][i];
                if (square_int.IU != ' ' && square_int.IU != '#') {
                    square_int.JK(square_int.IT);
                }
            }
        }
        this.BE = true;
    }
    
    void check() {
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                final square_int square_int = this.jn[j][i];
                if (square_int.isLet() && square_int.IU != '?' && square_int.IU != square_int.IT) {
                    square_int.JK('?');
                }
            }
        }
    }
    
    boolean HV() {
        for (int i = 0; i < this.ac; ++i) {
            for (int j = 0; j < this.ab; ++j) {
                final square_int square_int = this.jn[j][i];
                if (square_int.isLet() && ((square_int.IU == '?' && !this.BF) || square_int.IU != square_int.IT)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void HW(final boolean b) {
        final String parameter = this.App.getParameter("PROGRESS");
        String s = new String();
        if (parameter != null && parameter.length() != 0) {
            int i;
            int n;
            byte[] array;
            char char1;
            for (i = 0, n = 0, array = new byte[parameter.length()]; i < parameter.length(); ++i, array[n] = (byte)char1, ++n) {
                char1 = parameter.charAt(i);
                if (char1 == '%') {
                    char1 = (char)(Integer.parseInt(parameter.substring(i + 1, i + 3), 16) & 0xFF);
                    i += 2;
                }
            }
            try {
                s = new String(array, 0, n, this.App.t);
            }
            catch (IOException ex) {
                s = new String(array, 0, n);
            }
        }
        int n2 = 0;
        if (b || (parameter != null && parameter.length() != 0)) {
            for (int j = 0; j < this.ac; ++j) {
                for (int k = 0; k < this.ab; ++k) {
                    final square_int square_int = this.jn[k][j];
                    if (square_int.isLet()) {
                        if (parameter == null && square_int.IS != '?') {
                            square_int.JK(square_int.IS);
                            ++n2;
                        }
                        else {
                            square_int.JK((parameter == null) ? '?' : s.charAt(n2));
                        }
                        ++n2;
                    }
                }
            }
        }
        this.BE = false;
    }
    
    String HX(final int n) {
        String s = new String();
        if (n != 0) {
            String s2 = new String();
            int n2 = 1;
            for (int i = 0; i < this.ac; ++i) {
                for (int j = 0; j < this.ab; ++j) {
                    final square_int square_int = this.jn[j][i];
                    if (square_int.isLet() && ((j != 0 && this.jn[j - 1][i].isLet() && (square_int.JA & 0x2) == 0x0) || (j != this.ab - 1 && this.jn[j + 1][i].isLet() && (this.jn[j + 1][i].JA & 0x2) == 0x0))) {
                        if ((j == 0 || !this.jn[j - 1][i].isLet() || (this.jn[j - 1][i].isLet() && (square_int.JA & 0x2) != 0x0)) && s.length() != 0) {
                            s += '+';
                            if (n == 2) {
                                s2 += ((n2 != 0) ? "1" : "0");
                            }
                            n2 = 1;
                        }
                        if (n == 2 && square_int.IU != square_int.IT) {
                            n2 = 0;
                        }
                        if (square_int.IU == '?') {
                            s += '-';
                        }
                        else {
                            s += this.HG(square_int.IU);
                        }
                    }
                }
            }
            for (int k = 0; k < this.ab; ++k) {
                for (int l = 0; l < this.ac; ++l) {
                    final square_int square_int2 = this.jn[k][l];
                    if (square_int2.isLet() && ((l != 0 && this.jn[k][l - 1].isLet() && (square_int2.JA & 0x4) == 0x0) || (l != this.ac - 1 && this.jn[k][l + 1].isLet() && (this.jn[k][l + 1].JA & 0x4) == 0x0))) {
                        if ((l == 0 || !this.jn[k][l - 1].isLet() || (this.jn[k][l - 1].isLet() && (square_int2.JA & 0x4) != 0x0)) && s.length() != 0) {
                            s += '+';
                            if (n == 2) {
                                s2 += ((n2 != 0) ? "1" : "0");
                            }
                            n2 = 1;
                        }
                        if (n == 2 && square_int2.IU != square_int2.IT) {
                            n2 = 0;
                        }
                        if (square_int2.IU == '?') {
                            s += '-';
                        }
                        else {
                            s += this.HG(square_int2.IU);
                        }
                    }
                }
            }
            if (n == 2) {
                s = s2 + ((n2 != 0) ? "1" : "0") + '+' + s;
            }
            return s;
        }
        for (int n3 = 0; n3 < this.ac; ++n3) {
            for (int n4 = 0; n4 < this.ab; ++n4) {
                final square_int square_int3 = this.jn[n4][n3];
                if (square_int3.isLet()) {
                    if (square_int3.IU == '?') {
                        s += '-';
                    }
                    else {
                        s += this.HG(square_int3.IU);
                    }
                }
            }
        }
        return s;
    }
    
    void HY(final char c) {
        if (c <= '\r' || c == '\u007f') {
            return;
        }
        if ((!this.ag && !this.Sudoku) || this.BA == null || this.BA.indexOf(c) != -1) {
            int n = (!this.ag || this.BD.indexOf(c) == -1 || this.jn[this.ga.x][this.ga.y].IY) ? 1 : 0;
            if (n != 0) {
                if (this.Sudoku) {
                    for (int i = 0; i < this.ab; ++i) {
                        if (i != this.ga.x && c == this.jn[i][this.ga.y].IU) {
                            n = 0;
                        }
                    }
                    for (int j = 0; j < this.ac; ++j) {
                        if (j != this.ga.y && c == this.jn[this.ga.x][j].IU) {
                            n = 0;
                        }
                    }
                    for (int n2 = (int)Math.sqrt(this.ab), k = this.ga.x / n2 * n2; k < this.ga.x / n2 * n2 + n2; ++k) {
                        for (int l = this.ga.y / n2 * n2; l < this.ga.y / n2 * n2 + n2; ++l) {
                            if (c == this.jn[k][l].IU && k != this.ga.x && l != this.ga.y) {
                                n = 0;
                            }
                        }
                    }
                }
                if (n != 0) {
                    this.HM(c);
                }
            }
            if ((n != 0 || this.jn[this.ga.x][this.ga.y].IU == c) && !this.Sudoku) {
                this.HK(this.ga.x + (this.af ? 1 : 0), this.ga.y + (this.af ? 0 : 1));
            }
        }
    }
}
