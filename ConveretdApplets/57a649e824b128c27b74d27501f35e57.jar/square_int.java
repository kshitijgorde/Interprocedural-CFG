import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Graphics;
import java.awt.Color;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

class square_int implements Serializable
{
    char IU;
    char IT;
    char IS;
    String IV;
    boolean IW;
    boolean IX;
    boolean IY;
    boolean[] IZ;
    byte JA;
    int Rx;
    int Ry;
    int Rw;
    int Rh;
    Color JB;
    Color JC;
    int[] IQ;
    int[] R;
    String RtNum;
    byte[] JD;
    CGrid_int JE;
    
    square_int(final char iu, final String iv, final byte ja, final CGrid_int je) {
        this.IW = false;
        this.IX = false;
        this.JB = null;
        this.JC = null;
        this.RtNum = null;
        this.IU = iu;
        this.IV = iv;
        this.JA = ja;
        this.IY = (iu != '?');
        this.IS = (this.IY ? iu : '?');
        this.JE = je;
        this.IQ = new int[2];
        this.R = new int[2];
        this.JD = new byte[2];
        this.IZ = new boolean[2];
    }
    
    boolean isLet() {
        return this.IU != '#' && this.IU != ' ';
    }
    
    void JF(final boolean ix) {
        if (this.IX != ix) {
            this.IX = ix;
            this.IW = true;
        }
    }
    
    void JK(final char iu) {
        this.IY = false;
        if (this.IU != iu) {
            if (iu == ' ' || iu == '-') {
                this.IU = '?';
            }
            else {
                this.IU = iu;
            }
            this.IW = true;
            this.JE.repaint();
        }
    }
    
    void JL(final Graphics graphics, int n, int n2) {
        if (this.IX && !this.JE.FB) {
            if (n == this.JE.ga.x && n2 == this.JE.ga.y) {
                graphics.setColor(this.JE.CurColor);
            }
            else {
                graphics.setColor(this.JE.SelColor);
            }
        }
        else if (this.JC == null || (this.JA & 0x20) != 0x0) {
            graphics.setColor(this.JE.getBackground());
        }
        else {
            graphics.setColor(this.JC);
        }
        n *= this.JE.B;
        n2 *= this.JE.B;
        graphics.fillRect(this.Rx, this.Ry, this.Rw, this.Rh);
        if ((this.JA & 0x20) != 0x0) {
            if (this.JC == null) {
                graphics.setColor(this.JE.GridColor);
            }
            else {
                graphics.setColor(this.JC);
            }
            graphics.drawOval(this.Rx, this.Ry, this.Rw, this.Rh);
            graphics.drawOval(this.Rx + 1, this.Ry + 1, this.Rw - 2, this.Rh - 2);
            graphics.drawOval(this.Rx + 1, this.Ry, this.Rw - 2, this.Rh);
            graphics.drawOval(this.Rx, this.Ry + 1, this.Rw, this.Rh - 2);
        }
        graphics.setColor(this.JE.NumColor);
        if (this.IV != null) {
            graphics.setFont(this.JE.AC);
            graphics.drawString(this.IV, n + this.JE.DA, n2 + this.JE.AE);
        }
        if (this.RtNum != null) {
            graphics.setFont(this.JE.AC);
            graphics.drawString(this.RtNum, n + this.JE.B - this.JE.DA - graphics.getFontMetrics().stringWidth(this.RtNum), n2 + this.JE.AE);
        }
        if (this.IU != '?') {
            graphics.setFont(this.JE.AD);
            if (this.JB == null) {
                graphics.setColor(this.JE.FontColor);
            }
            else {
                graphics.setColor(this.JB);
            }
            if (this.JE.kf && Character.toUpperCase(this.IT) != Character.toUpperCase(this.IU)) {
                graphics.setColor(this.JE.WrongColor);
            }
            graphics.drawString(String.valueOf(this.IU), n + 1 + (this.JE.B - graphics.getFontMetrics().charWidth(this.IU)) / 2, n2 + this.JE.AF);
        }
        this.IW = false;
    }
    
    boolean JM() {
        return this.IU != ' ';
    }
    
    void JN(final DataInputStream dataInputStream, final byte b) throws IOException {
        final byte byte1 = dataInputStream.readByte();
        if (byte1 != 0) {
            byte byte2 = dataInputStream.readByte();
            final int n = ((byte2 & 0x80) != 0x0) ? 1 : 0;
            if (n == 1) {
                byte2 &= 0x7F;
            }
            this.IQ[b] = byte1;
            this.R[b] = byte2;
            this.JD[b] = (byte)n;
            this.JE.jn[byte1 - 1][byte2 - 1].JN(dataInputStream, (byte)n);
        }
        else {
            this.IQ[b] = -1;
        }
    }
}
