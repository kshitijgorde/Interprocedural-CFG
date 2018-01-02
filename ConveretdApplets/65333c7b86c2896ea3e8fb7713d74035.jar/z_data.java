import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class z_data
{
    public static final int drawRect = 1;
    public static final int draw3DRect = 2;
    public static final int drawRoundRect = 3;
    public static final int drawOval = 4;
    public static final int drawArc = 5;
    public static final int fillRect = 6;
    public static final int fill3DRect = 7;
    public static final int fillRoundRect = 8;
    public static final int fillOval = 9;
    public static final int fillArc = 10;
    public static final int fillZne = 11;
    public static final int drawString = 20;
    public static final int drawLine = 21;
    public static final int drawImage = 22;
    public static final int drawImagePart = 23;
    public static final int freehand = 24;
    public static final int erase = 25;
    public static final int bak = 26;
    public static final int loadImage = 30;
    public static final int setFont = 31;
    public static final int setColor = 32;
    public static final int changeClr = 33;
    public static final int symbol = 950;
    public static final int clear = 999;
    int iEpaisseur;
    int iAction;
    int[] r;
    int[] rr;
    String s;
    int iL;
    boolean valid;
    
    public z_data() {
        this.iEpaisseur = 1;
        this.iAction = -1;
        this.r = new int[60];
        this.rr = new int[60];
        this.iL = -1;
        this.valid = false;
    }
    
    public z_data(final String s, final int n, final int[] array) {
        this.iEpaisseur = 1;
        this.iAction = -1;
        this.r = new int[60];
        this.rr = new int[60];
        this.iL = -1;
        this.valid = false;
        this.set(s, n, array);
        this.valid = true;
    }
    
    public z_data(final String s) {
        this.iEpaisseur = 1;
        this.iAction = -1;
        this.r = new int[60];
        this.rr = new int[60];
        this.iL = -1;
        this.valid = false;
        final int n = 59;
        int il = 0;
        int n2 = 0;
        int int1 = 0;
        final int[] array = new int[70];
        int n3 = 0;
        if (s == null) {
            this.valid = false;
            return;
        }
        while (true) {
            final int index = s.indexOf(n, n3);
            String s2;
            if (index > 0) {
                s2 = new String(s.substring(n3, index).trim());
            }
            else {
                s2 = new String(s.substring(n3).trim());
            }
            boolean b;
            try {
                int1 = Integer.parseInt(s2);
                b = (this.iAction != 20 || n2 != 1);
            }
            catch (NumberFormatException ex) {
                b = false;
            }
            if (index > -1 || s2.length() > 0) {
                if (b) {
                    if (n2 == 0) {
                        this.iAction = int1;
                    }
                    else if (il < 70) {
                        array[il++] = int1;
                    }
                }
                else {
                    this.s = new String(s2);
                }
                ++n2;
            }
            if (index == -1) {
                break;
            }
            n3 = index + 1;
        }
        for (int i = 0; i < il; ++i) {
            if (this.iAction == 24) {
                if (i == 0) {
                    this.iEpaisseur = array[0];
                }
                else {
                    final int n4 = (i - 1) / 2;
                    if (i % 2 != 0) {
                        this.r[n4] = array[i];
                    }
                    else {
                        this.rr[n4] = array[i];
                    }
                }
            }
            else {
                this.r[i] = array[i];
            }
        }
        this.iL = il;
        this.valid = (n2 > 1);
    }
    
    public boolean isValid() {
        return this.valid;
    }
    
    public void set(final String s, final int iAction, final int[] r) {
        this.s = s;
        this.iAction = iAction;
        this.r = r;
        if (this.r != null) {
            this.iL = r.length;
        }
    }
    
    public String toString() {
        String s = String.valueOf(String.valueOf(new StringBuffer("").append(this.iAction).append(" ; ")));
        if (this.s != null && this.s.length() > 0) {
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(this.s).append(" ; ")));
        }
        if (this.r != null) {
            for (int i = 0; i < this.r.length; ++i) {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(this.r[i]).append(" ; ")));
            }
        }
        return s;
    }
    
    public void paint(final cDraw cDraw, final Graphics graphics) {
        switch (this.iAction) {
            case 1: {
                if (this.iL > 3) {
                    cDraw.drawRect(graphics, this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], false);
                    break;
                }
                break;
            }
            case 2: {
                if (this.iL > 4) {
                    graphics.draw3DRect(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4] == 0);
                    break;
                }
                if (this.iL > 3) {
                    graphics.draw3DRect(this.r[0], this.r[1], this.r[2], this.r[3], false);
                    break;
                }
                break;
            }
            case 3: {
                if (this.iL > 5) {
                    graphics.drawRoundRect(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], this.r[5]);
                    break;
                }
                break;
            }
            case 4: {
                if (this.iL > 4) {
                    cDraw.drawOval(graphics, this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], false);
                    break;
                }
                break;
            }
            case 5: {
                if (this.iL > 5) {
                    graphics.drawArc(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], this.r[5]);
                    break;
                }
                break;
            }
            case 24: {
                if (this.iL <= 2) {
                    break;
                }
                graphics.drawPolyline(this.r, this.rr, (this.iL - 1) / 2);
                if (this.iEpaisseur > 1) {
                    for (int i = 1; i < this.iEpaisseur; ++i) {
                        for (int j = 0; j < (this.iL - 1) / 2; ++j) {
                            this.r[j] -= i;
                        }
                        graphics.drawPolyline(this.r, this.rr, (this.iL - 1) / 2);
                        for (int k = 0; k < (this.iL - 1) / 2; ++k) {
                            this.r[k] = this.r[k] + i + i;
                        }
                        graphics.drawPolyline(this.r, this.rr, (this.iL - 1) / 2);
                        for (int l = 0; l < (this.iL - 1) / 2; ++l) {
                            this.r[l] -= i;
                            this.rr[l] -= i;
                        }
                        graphics.drawPolyline(this.r, this.rr, (this.iL - 1) / 2);
                        for (int n = 0; n < (this.iL - 1) / 2; ++n) {
                            this.rr[n] = this.rr[n] + i + i;
                        }
                        graphics.drawPolyline(this.r, this.rr, (this.iL - 1) / 2);
                        for (int n2 = 0; n2 < (this.iL - 1) / 2; ++n2) {
                            this.rr[n2] -= i;
                        }
                    }
                    break;
                }
                break;
            }
            case 6: {
                if (this.iL > 3) {
                    graphics.fillRect(this.r[0], this.r[1], this.r[2], this.r[3]);
                    break;
                }
                break;
            }
            case 7: {
                if (this.iL > 4) {
                    graphics.fill3DRect(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4] == 0);
                    break;
                }
                if (this.iL > 3) {
                    graphics.fill3DRect(this.r[0], this.r[1], this.r[2], this.r[3], false);
                    break;
                }
                break;
            }
            case 8: {
                if (this.iL > 5) {
                    graphics.fillRoundRect(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], this.r[5]);
                    break;
                }
                break;
            }
            case 9: {
                if (this.iL > 3) {
                    graphics.fillOval(this.r[0], this.r[1], this.r[2], this.r[3]);
                    break;
                }
                break;
            }
            case 10: {
                if (this.iL > 5) {
                    graphics.fillArc(this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], this.r[5]);
                    break;
                }
                break;
            }
            case 11: {
                if (this.iL > 2) {
                    cDraw.fillZone(this.r[0], this.r[1]);
                    break;
                }
                break;
            }
            case 20: {
                if (this.iL > 1) {
                    graphics.drawString(this.s, this.r[0], this.r[1]);
                    break;
                }
                break;
            }
            case 21: {
                if (this.iL > 3) {
                    cDraw.drawLine(graphics, this.r[0], this.r[1], this.r[2], this.r[3], this.r[4], this.r[5]);
                    break;
                }
                break;
            }
            case 22: {
                if (this.iL <= 2 || cDraw.img[this.r[0]] == null) {
                    break;
                }
                if (this.iL > 4) {
                    graphics.drawImage(cDraw.img[this.r[0]], this.r[1], this.r[2], this.r[3], this.r[4], null);
                    break;
                }
                graphics.drawImage(cDraw.img[this.r[0]], this.r[1], this.r[2], null);
                break;
            }
            case 31: {
                if (this.iL > 1) {
                    graphics.setFont(new Font(this.s, this.r[0], this.r[1]));
                    break;
                }
                break;
            }
            case 32: {
                if (this.iL > 0) {
                    graphics.setColor(new Color(this.r[0]));
                    cDraw.fgColor = this.r[0];
                    break;
                }
                break;
            }
        }
    }
}
