import java.awt.Event;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class it3free extends Applet implements Runnable
{
    private boolean I0;
    private String[] I1;
    private boolean[] I2;
    private byte[] I3;
    private byte[] I4;
    private String[] I5;
    private boolean[] I6;
    private String[] I7;
    private short[] I8;
    private boolean[] I9;
    private Image I10;
    private URL I11;
    private Color[] I12;
    private String I13;
    private Rectangle[] I14;
    private URL I15;
    private Dimension I16;
    private int I17;
    private Rectangle[] I18;
    private URL I19;
    private int I20;
    private int I21;
    private int I22;
    private boolean I23;
    private int I24;
    private int I25;
    private Graphics I26;
    private Graphics I27;
    private Graphics I28;
    private Graphics I29;
    private boolean I30;
    private int I31;
    private int I32;
    private int I33;
    private Image I34;
    private Image I35;
    private Image I36;
    private Image[] I37;
    private boolean I38;
    private int I39;
    private int I40;
    private Image I41;
    private String[] I42;
    private int I43;
    private int I44;
    private int I45;
    private Thread I46;
    private int I47;
    private int I48;
    private int I49;
    private int I50;
    private Rectangle I51;
    private String[] I52;
    private int I53;
    private int I54;
    private Rectangle I55;
    private Rectangle I56;
    private int I57;
    private int I58;
    private int I59;
    private Rectangle I60;
    private boolean I61;
    private int I62;
    private int I63;
    private Image[] I64;
    private Color[] I65;
    private Font I66;
    private String I67;
    private int I68;
    private String I69;
    private boolean I70;
    private String I71;
    private boolean I72;
    private Rectangle[] I73;
    private int[] I74;
    private int I75;
    private int I76;
    private int I77;
    private int I78;
    private int I79;
    
    private int I0(final int n) {
        byte b = 0;
        for (short n2 = 0; n2 < n; ++n2) {
            b += this.I3[n2];
            if (this.I9[n2] && !this.I6[n2]) {
                n2 += this.I8[n2];
            }
        }
        return b;
    }
    
    private void I0() {
        for (int i = 0; i < this.I47; ++i) {
            int n = 0;
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.I66);
            final int n2 = this.I51.width - 24 - this.I4[i] * this.I44;
            final int n3 = this.I5[i].toLowerCase().indexOf("text:") + 5;
            if (n3 > 4) {
                int n4 = this.I5[i].toLowerCase().indexOf(";", n3);
                if (n4 == -1) {
                    n4 = this.I5[i].length();
                }
                final String[] i2 = this.I0(this.I5[i].substring(n3, n4), n2, fontMetrics, !this.I9[i], 0);
                int n5 = 0;
                int n6 = 39;
                do {
                    if (n5 == 0 && i2[n6].length() != 0) {
                        n = n6 + 1;
                        n5 = 1;
                    }
                } while (--n6 > 0);
            }
            this.I3[i] = (byte)Math.max(n, 1);
        }
        this.I32 = Math.max(this.I0(true), this.I55.height);
        this.I33 = this.I0(false);
    }
    
    private void I1(final int n) {
        System.gc();
        if (this.I9[n]) {
            final boolean b = this.I6[n];
            this.I2(n);
            this.I6[n] = !b;
            this.I30 = false;
            this.I5();
            this.repaint();
            this.I30 = true;
            this.I22 = n;
            this.I23 = false;
        }
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.I1[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            final int length = trim.length();
            if (trim.toLowerCase().startsWith("link")) {
                int index = trim.indexOf(",");
                String trim2;
                if (index == -1 || length - index < 2) {
                    trim2 = "_top";
                    index = length;
                }
                else {
                    trim2 = trim.substring(index + 1, length).trim();
                }
                final String trim3 = trim.substring(5, index).trim();
                try {
                    url = new URL(this.I15, trim3);
                }
                catch (Exception ex) {
                    if (trim3.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.I15, "mailto.htm");
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        System.out.println("Link failed: " + trim3);
                    }
                }
                if (!this.I9[n] || this.I6[n]) {
                    this.getAppletContext().showDocument(url, trim2);
                }
                this.I50 = n;
                this.I5();
                this.repaint();
            }
        }
    }
    
    private void I0(final Graphics graphics, final Rectangle rectangle) {
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void I2(final int n) {
        byte b = this.I4[n];
        final int[] array = new int[b];
        final byte b2 = b;
        --b;
        for (int i = n; i >= 0; --i) {
            if (this.I4[i] == b) {
                array[b] = i;
                --b;
            }
        }
        final byte b3 = b2;
        for (int j = this.I17 - 1; j >= 0; --j) {
            final int n2 = this.I74[j];
            if (this.I9[n2] && this.I6[n2]) {
                boolean b4 = false;
                for (byte b5 = 0; b5 < b3; ++b5) {
                    if (n2 == array[b5]) {
                        b4 = true;
                    }
                }
                if (!b4) {
                    this.I6[n2] = false;
                }
            }
        }
    }
    
    private void I0(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int n5 = b ? 0 : 3;
        graphics.setColor(this.I12[3 - n5]);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(this.I12[n5]);
        graphics.fillRect(n, n2, n3 - 1, n4 - 1);
        graphics.setColor(this.I12[4]);
        graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
    }
    
    private void I0(final Graphics graphics, final int n) {
        graphics.setColor(this.I12[4]);
        int n2 = 6;
        int n3 = 6;
        int n4 = 0;
        do {
            if (n == 0) {
                n2 = 6 - n4 * 2;
            }
            if (n == 1) {
                n3 = 6 + n4 * 2;
            }
            if (n == 2) {
                n2 = 6 + n4 * 2;
            }
            if (n == 3) {
                n3 = 6 - n4 * 2;
            }
            graphics.drawLine(n2, n3, n2, n3);
        } while (++n4 < 4);
    }
    
    private void I0(final Graphics graphics, final int n, final int n2, final int n3, final boolean b, final int n4) {
        final String s = this.I5[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final int length = s.length();
        String substring = null;
        final int index = s.toLowerCase().indexOf("text:");
        if (index != -1) {
            int index2 = s.indexOf(";", index);
            if (index2 == -1) {
                index2 = length;
            }
            substring = s.substring(index + 5, index2);
        }
        int max = 0;
        if (substring != null) {
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.I66);
            final int descent = fontMetrics.getDescent();
            final String[] i0 = this.I0(substring, n4, fontMetrics, !this.I9[n], 0);
            for (byte b2 = 0; b2 < this.I3[n]; ++b2) {
                max = Math.max(fontMetrics.stringWidth(i0[b2]), max);
            }
            final Color color = this.I65[2];
            if (b && !color.equals(this.I12[0]) && substring != null) {
                graphics.setColor(color);
                graphics.fillRect(n2 - 2, 0, max + 4, this.I3[n] * this.I45);
            }
            graphics.setFont(this.I66);
            graphics.setColor(this.I65[0]);
            for (byte b3 = 0; b3 < this.I3[n]; ++b3) {
                graphics.drawString(i0[b3], n2, n3 + this.I45 * b3 + 0 - descent);
            }
        }
    }
    
    private void I0(final int n, final boolean b) {
        if (n > 499 || n < 0 || this.I0) {
            return;
        }
        final int n2 = this.I74[n];
        final int n3 = this.I3[n2] * this.I45;
        final int width = this.I51.width;
        final Rectangle rectangle = this.I73[n];
        final int x = rectangle.x;
        this.I35 = this.createImage(width + 18, n3);
        (this.I29 = this.I35.getGraphics()).setColor(this.I12[5]);
        this.I29.fillRect(0, 0, width + 18, n3);
        this.I0(this.I29, n2, 2, this.I45, b, width - x - 6);
        final Graphics graphics = this.getGraphics();
        this.I0(graphics, this.I51);
        graphics.drawImage(this.I35, x + this.I51.x, rectangle.y + this.I51.y - this.I79, null);
        graphics.dispose();
    }
    
    private void I3(int y) {
        final int n = y - this.I60.y;
        final int height = this.I60.height;
        final int n2 = this.I0 ? (this.I31 - this.I51.height + 20) : (this.I33 - this.I51.height + 20);
        final int n3 = this.I0 ? this.I76 : this.I77;
        int n4;
        if (n < this.I63) {
            n4 = Math.max(0, n3 - this.I59);
        }
        else if (n > height - this.I63) {
            n4 = Math.min(100, n3 + this.I59);
        }
        else {
            n4 = Math.max(Math.min((n - this.I63) * 100 / (height - 2 * this.I63), 100), 0);
        }
        if (!this.I0) {
            this.I79 = n2 * n4 / 100;
            this.I77 = n4;
        }
        else {
            this.I39 = n2 * n4 / 100;
            this.I76 = n4;
        }
        System.gc();
        final int x = this.I55.x;
        y = this.I55.y;
        final Graphics graphics = this.getGraphics();
        try {
            this.I0(this.I28, this.I55, this.I12[0]);
            this.I0(this.I28, x, y);
            if (!this.I0) {
                this.I27.drawImage(this.I41, this.I51.x - x, this.I51.y - y - this.I79, null);
            }
            else {
                this.I27.drawImage(this.I36, this.I51.x - x, this.I51.y - y - this.I39, null);
            }
            graphics.drawImage(this.I34, x, y, null);
        }
        catch (OutOfMemoryError outOfMemoryError) {}
    }
    
    private void I0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private int I0(final boolean b) {
        byte b2 = 0;
        for (short n = 0; n < this.I47; ++n) {
            b2 += this.I3[n];
            if (!b && this.I9[n] && !this.I6[n]) {
                n += this.I8[n];
            }
        }
        return b2 * this.I45 + 10;
    }
    
    private boolean I1() {
        int n = 0;
        do {
            this.I64[n] = this.createImage(12, 14);
            final Graphics graphics = this.I64[n].getGraphics();
            graphics.setColor(this.I12[5]);
            graphics.fillRect(0, 0, 12, 14);
            if (n == 5 || n == 4) {
                this.I0(graphics, 0);
            }
            if (n == 1 || n == 2 || n == 4) {
                this.I0(graphics, 1);
            }
            if (n == 2 || n == 3) {
                this.I0(graphics, 2);
            }
            if (n == 1 || n == 2 || n == 3) {
                this.I0(graphics, 3);
            }
        } while (++n < 7);
        this.I64[6] = this.createImage(7, 7);
        this.I64[7] = this.createImage(7, 7);
        final Graphics graphics2 = this.I64[7].getGraphics();
        graphics2.setColor(Color.white);
        graphics2.fillRect(0, 0, 6, 6);
        graphics2.setColor(Color.black);
        graphics2.drawRect(0, 0, 6, 6);
        graphics2.drawLine(2, 3, 4, 3);
        final Graphics graphics3 = this.I64[6].getGraphics();
        graphics3.drawImage(this.I64[7], 0, 0, null);
        graphics3.drawLine(3, 2, 3, 4);
        int n2 = 8;
        do {
            this.I64[n2] = this.createImage(14, 11);
            final Graphics graphics4 = this.I64[n2].getGraphics();
            graphics4.setColor(this.I12[5]);
            graphics4.fillRect(0, 0, 14, 11);
        } while (++n2 < 11);
        final Graphics graphics5 = this.I64[8].getGraphics();
        graphics5.setColor(Color.white);
        graphics5.fillRect(2, 3, 11, 7);
        graphics5.setColor(Color.lightGray);
        graphics5.fillRect(3, 4, 10, 6);
        graphics5.setColor(Color.yellow);
        graphics5.fillRect(3, 4, 9, 5);
        graphics5.fillRect(2, 1, 6, 2);
        graphics5.setColor(Color.gray);
        graphics5.drawLine(2, 10, 12, 10);
        graphics5.drawLine(1, 2, 1, 9);
        graphics5.drawLine(13, 3, 13, 9);
        graphics5.drawLine(8, 2, 12, 2);
        graphics5.drawLine(7, 1, 7, 1);
        graphics5.drawLine(2, 1, 2, 1);
        graphics5.drawLine(3, 0, 6, 0);
        final Graphics graphics6 = this.I64[9].getGraphics();
        graphics6.drawImage(this.I64[8], 0, 0, null);
        graphics6.setColor(Color.yellow);
        graphics6.fillRect(2, 3, 11, 6);
        graphics6.setColor(Color.gray);
        graphics6.fillRect(0, 5, 11, 2);
        graphics6.drawLine(11, 7, 11, 8);
        graphics6.drawLine(1, 7, 1, 8);
        graphics6.drawLine(12, 9, 12, 9);
        graphics6.drawLine(2, 9, 2, 9);
        graphics6.setColor(Color.white);
        graphics6.drawLine(1, 6, 9, 6);
        graphics6.drawLine(2, 7, 2, 8);
        final Graphics graphics7 = this.I64[10].getGraphics();
        graphics7.setColor(Color.white);
        graphics7.fillRect(0, 0, 9, 10);
        graphics7.setColor(Color.gray);
        graphics7.drawRect(0, 0, 9, 10);
        graphics7.setColor(Color.blue);
        graphics7.drawLine(2, 2, 4, 2);
        graphics7.drawLine(2, 4, 4, 4);
        graphics7.drawLine(2, 6, 7, 6);
        graphics7.drawLine(2, 8, 7, 8);
        graphics7.setColor(Color.red);
        graphics7.fillRect(6, 2, 2, 3);
        graphics7.dispose();
        final int i63 = this.I63;
        final int n3 = this.I63 / 2;
        final int height = this.I60.height;
        this.I37[3] = this.createImage(i63, i63);
        this.I0(this.I37[3].getGraphics(), 0, 0, i63, i63, true);
        this.I37[2] = this.createImage(i63, height);
        final Graphics graphics8 = this.I37[2].getGraphics();
        graphics8.setColor(this.I12[1]);
        graphics8.fillRect(0, 0, i63, height);
        this.I0(graphics8, 0, 0, i63, i63, true);
        this.I0(graphics8, 0, height - i63, i63, i63, true);
        graphics8.setColor(this.I12[3]);
        int n4 = 0;
        do {
            graphics8.drawLine(5 - n4, 3 + n4, 5 + n4, 3 + n4);
            graphics8.drawLine(5 - n4, 7 + height - i63 - n4, 5 + n4, 7 + height - i63 - n4);
        } while (++n4 < 4);
        final int width = this.bounds().width;
        final int height2 = this.bounds().height;
        this.I37[1] = this.createImage(width, height2);
        final Graphics graphics9 = this.I37[1].getGraphics();
        this.I0(graphics9, 0, 0, width, 20, true);
        this.I14[0] = new Rectangle(0, 20, 45, 17);
        this.I0(graphics9, 0, 20, 45, 17, true);
        this.I14[1] = new Rectangle(45, 20, width - 45, 17);
        this.I0(graphics9, 45, 20, width - 45, 17, true);
        this.I0(graphics9, 0, 36, width, height2 - 36, true);
        this.I14[2] = new Rectangle(width - 16, 4, 12, 12);
        this.I0(graphics9, width - 16, 4, 12, 12, true);
        graphics9.setColor(this.I12[0]);
        graphics9.fillRect(width - 14, 8, 6, 6);
        graphics9.setColor(this.I12[3]);
        graphics9.fillRect(width - 11, 6, 5, 5);
        this.I14[3] = new Rectangle(width - 28, 4, 12, 12);
        this.I0(graphics9, width - 28, 4, 12, 12, true);
        graphics9.setColor(Color.red);
        graphics9.fillRect(width - 26, 8, 6, 6);
        graphics9.setColor(Color.blue);
        graphics9.fillRect(width - 24, 8, 5, 5);
        graphics9.setColor(Color.yellow);
        graphics9.fillRect(width - 25, 6, 4, 4);
        this.I0(graphics9, 3, 39, width - 6, height2 - 42, false);
        graphics9.setColor(this.I65[1]);
        graphics9.setFont(new Font(this.I66.getName(), 0, 11));
        graphics9.drawString(this.I69, 10, 15);
        graphics9.drawString(this.I67, 10, 33);
        graphics9.drawString("TREE DISPLAY", 50, 33);
        System.gc();
        return true;
    }
    
    private boolean I2() {
        final String[][] array = new String[50][5];
        URL url;
        try {
            url = new URL(this.I11, this.I71);
        }
        catch (Exception ex) {
            this.I54 = 3;
            return false;
        }
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(url.openStream());
        }
        catch (Exception ex2) {
            this.I54 = 4;
            return false;
        }
        final byte[] array2 = new byte[100000];
        try {
            try {
                dataInputStream.readFully(array2);
            }
            catch (EOFException ex3) {}
        }
        catch (IOException ex4) {
            this.I54 = 5;
            return false;
        }
        try {
            dataInputStream.close();
        }
        catch (IOException ex5) {}
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
        final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
        int n = 0;
        boolean b = false;
        this.I47 = 0;
        streamTokenizer.eolIsSignificant(true);
        while (!b) {
            this.I43 = this.I47;
            if (this.I47 >= 50) {
                b = true;
            }
            int nextToken;
            try {
                nextToken = streamTokenizer.nextToken();
            }
            catch (Exception ex6) {
                if (this.I19 == null) {
                    this.I54 = 13;
                    return false;
                }
                nextToken = 0;
            }
            if (nextToken == -1) {
                b = true;
                if (n == 5) {
                    ++this.I47;
                }
            }
            if (nextToken == 10 && n != 0) {
                ++this.I47;
                n = 0;
            }
            if (nextToken == 34) {
                if (n >= 5) {
                    if (this.I19 == null) {
                        this.I54 = 13;
                        return false;
                    }
                    ++this.I47;
                    n = 0;
                }
                try {
                    array[this.I47][n] = streamTokenizer.sval;
                }
                catch (Exception ex7) {
                    b = true;
                }
                ++n;
            }
        }
        try {
            byteArrayInputStream.close();
        }
        catch (Exception ex8) {}
        if (this.I47 >= 50) {
            this.I54 = 15;
            this.I43 = this.I47;
            return false;
        }
        for (int i = 0; i < this.I47; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.I19 == null) {
                    this.I54 = 10;
                    this.I43 = i;
                    return false;
                }
                array[i][1] = "1";
            }
            try {
                Integer.parseInt(array[i][2]);
            }
            catch (NumberFormatException ex10) {
                array[i][2] = "0";
            }
        }
        array[this.I47][1] = "0";
        this.I5 = new String[this.I47 + 1];
        this.I7 = new String[this.I47 + 1];
        this.I1 = new String[this.I47 + 1];
        this.I9 = new boolean[this.I47 + 1];
        this.I6 = new boolean[this.I47 + 1];
        this.I2 = new boolean[this.I47 + 1];
        this.I8 = new short[this.I47 + 1];
        this.I4 = new byte[this.I47 + 1];
        this.I3 = new byte[this.I47 + 1];
        this.I73 = new Rectangle[this.I47 + 1];
        this.I18 = new Rectangle[this.I47 + 1];
        this.I74 = new int[this.I47 + 1];
        for (int j = 0; j < this.I47; ++j) {
            this.I2[j] = false;
            this.I73[j] = new Rectangle(0, 0, 0, 0);
            this.I18[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.I47; ++k) {
            this.I43 = k;
            this.I5[k] = array[k][0].trim();
            final int n2 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n2 > 1 || max > 50) {
                if (this.I19 == null) {
                    this.I54 = 10;
                    return false;
                }
                max = 1;
            }
            this.I4[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.I9[k] = true;
                this.I6[k] = (max <= this.I49);
                int n3 = 0;
                int l;
                for (l = k + 1; l < this.I47; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n3 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.I8[k] = (short)(l - k - 1);
                this.I2[n3] = true;
            }
            else {
                this.I9[k] = false;
            }
            this.I1[k] = array[k][3];
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.I7[k] = array[k][4];
            }
            else {
                this.I7[k] = " ";
            }
        }
        return true;
    }
    
    private String I0(final int n, final String s) {
        final String parameter = this.getParameter(this.I52[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int I0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.I52[n]);
            if (parameter != null) {
                n6 = Integer.parseInt(parameter, n5);
            }
            if (n5 == 10) {
                n6 = Math.min(Math.max(n6, n3), n2);
            }
        }
        catch (NumberFormatException ex) {
            n6 = n4;
        }
        return n6;
    }
    
    public void init() {
        System.gc();
        this.I4(this.I0(2, 7, 0, 7, 10));
        this.I13 = this.I0(1, " ");
        this.I45 = 14;
        this.I66 = new Font("Helvetica", 0, 11);
        this.I69 = "iTREE MINI-MENU";
        this.I67 = "INFO";
        try {
            this.I15 = this.getDocumentBase();
            this.I11 = this.getCodeBase();
            this.I19 = new URL(this.I15, this.I0(0, null));
        }
        catch (Throwable t) {
            this.I19 = null;
        }
        this.I12[2] = Color.blue;
        this.I71 = "menu.txt";
        this.I44 = 12;
        this.I25 = 40;
        this.I20 = 4;
        this.I21 = 4;
        this.I24 = 4;
        this.I63 = 11;
        this.I62 = 1;
        this.I57 = this.I63;
        this.I59 = 10;
        this.I49 = 0;
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        final int i21 = this.I21;
        final int i22 = this.I25;
        final int n = width - this.I21 - this.I24;
        final int n2 = height - this.I25 - this.I20;
        this.I55 = new Rectangle(i21, i22, n, n2);
        this.I34 = this.createImage(n, n2);
        this.I28 = this.I34.getGraphics();
        final int i23 = this.I21;
        final int i24 = this.I25;
        final int i25 = this.I63;
        final int n3 = n2;
        final int n4 = width - this.I24 - this.I63;
        final int n5 = n - i25;
        this.I60 = new Rectangle(n4, i24, i25, n3);
        this.I51 = new Rectangle(i21, i22, n5, n2);
        (this.I27 = this.I34.getGraphics()).clipRect(i21 - this.I55.x, i22 - this.I55.y, n5, n2);
        System.gc();
    }
    
    private void I3() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.I38) {
            final int n = 6;
            (this.I42 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.I42[1] = "Navigation Applet";
            this.I42[2] = "© Image Intelligence Ltd.";
            this.I42[3] = "http://www.imint.com";
            this.I42[4] = "Version 3.0 - iTree Mini Menu";
            this.I42[5] = "iTree Mini-Menu, version 3.0 § © Image Intelligence Ltd. 1999 § http://www.imint.com § FREE APPLET FOR NON-COMMERCIAL USE ONLY § § You can get your own copy of this applet from the imint.com website. § § INSTRUCTIONS § § 1. There are two buttons in the top right-hand corner with which you can toggle the colour scheme and font size. § 2. There are two tabs: an information panel and a tree display. Click on 'tree display' to return to the menu listing. § 3. Operation is otherwise much like a Windows Explorer tree - open and close folders to reveal new menu sections, click on any item to load the page in question, and watch the browser status bar for messages about the pages as you pass the mouse over the entry.";
            for (int i = 1; i < n - 1; ++i) {
                System.out.println(this.I42[i]);
            }
            this.I0(graphics, this.bounds(), this.I12[0]);
            this.I72 = false;
            this.I77 = 0;
            char c = '\0';
            this.I48 = 0;
            this.I22 = 0;
            this.I78 = 0;
            this.I79 = 0;
            this.I50 = -1;
            this.I53 = ((this.I19 == null) ? 2 : 3);
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.I42[j];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.I13.equalsIgnoreCase(this.I42[0])) {
                this.I54 = 9;
                this.I53 = 1;
                return;
            }
            if (c != 33037569) {
                this.I54 = 30;
                this.I53 = 1;
                return;
            }
            try {
                if (!this.I2() && !this.I2()) {
                    this.I53 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.I54 = 6;
                this.I53 = 1;
                return;
            }
            this.I0();
            try {
                if (!this.I1() && !this.I1()) {
                    this.I53 = 1;
                    return;
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.I54 = 7;
                this.I53 = 1;
                return;
            }
            if (this.I47 > 60) {
                this.I54 = 31;
                this.I53 = 1;
                return;
            }
            this.I4();
            this.I72 = true;
            try {
                this.I5();
                if (this.I41 == null) {
                    this.I5();
                }
                if (this.I41 == null) {
                    this.I54 = 8;
                    this.I53 = 1;
                    return;
                }
                if (this.I75 > 50) {
                    this.I2(0);
                    this.I5();
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3);
                this.I54 = 8;
                this.I53 = 1;
                return;
            }
            this.I38 = true;
            this.I30 = true;
            this.I53 = 0;
        }
        this.repaint();
    }
    
    public it3free() {
        this.I43 = -1;
        this.I64 = new Image[12];
        this.I37 = new Image[4];
        this.I14 = new Rectangle[4];
        this.I12 = new Color[6];
        this.I50 = -1;
        this.I65 = new Color[3];
        this.I68 = 2;
        this.I52 = new String[] { "escapepage", "copyright", "colourscheme" };
    }
    
    private int I0(int n, int n2, final boolean b) {
        if (b) {
            if (this.I60.inside(n, n2) && this.I61) {
                return 0;
            }
            int n3 = 0;
            while (!this.I14[n3].inside(n, n2)) {
                if (++n3 >= 4) {
                    return -1;
                }
            }
            return n3 + 1;
        }
        else {
            if (!this.I51.inside(n, n2)) {
                return -1;
            }
            n -= this.I51.x;
            n2 -= this.I51.y - this.I79;
            for (int i = 0; i < this.I47; ++i) {
                if (this.I18[i].inside(n, n2)) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    private void I4() {
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.I66);
        final int width = this.I51.width;
        final String[] i0 = this.I0(this.I42[5], width - 6, fontMetrics, true, 0);
        int n = 0;
        int n2 = 0;
        int n3 = 39;
        do {
            if (n2 == 0 && i0[n3].length() != 0) {
                n = n3 + 1;
                n2 = 1;
            }
        } while (--n3 > 0);
        this.I31 = Math.max(this.I51.height, n * this.I45);
        this.I36 = this.createImage(width, this.I31 + 20);
        final Graphics graphics = this.I36.getGraphics();
        graphics.setColor(this.I12[5]);
        graphics.fillRect(0, 0, width, this.I31 + 20);
        graphics.setFont(this.I66);
        graphics.setColor(this.I65[0]);
        for (int j = 0; j < n; ++j) {
            graphics.drawString(i0[j], 2, (j + 1) * this.I45);
        }
    }
    
    private void I5() {
        final int width = this.I51.width;
        final int n = this.I32 + 50;
        if (this.I41 != null) {
            this.I41.flush();
        }
        this.I41 = this.createImage(width, n);
        final Graphics graphics = this.I41.getGraphics();
        graphics.setColor(this.I12[5]);
        graphics.fillRect(0, 0, width, n);
        int i17 = 0;
        int n2 = 0;
        final boolean[] array = new boolean[this.I47];
        for (int j = 0; j < this.I47; ++j) {
            this.I73[j].reshape(0, 0, 0, 0);
            this.I18[j].reshape(0, 0, 0, 0);
            this.I74[j] = 0;
            array[j] = false;
        }
        for (short n3 = 0; n3 < this.I47; ++n3) {
            if (n3 == 0) {
                this.showStatus(" ");
            }
            this.I75 = this.I0(n3) + 1;
            final int n4 = this.I0(n3) * this.I45 + 0;
            ++i17;
            final byte b = this.I4[n3];
            if (this.I9[n3] && this.I6[n3]) {
                array[b] = true;
            }
            for (byte b2 = 0; b2 <= b; ++b2) {
                final int n5 = b2 * this.I44 + 1;
                int n6 = 0;
                if (array[b2]) {
                    n6 = 1;
                }
                if (b2 == b - 1) {
                    n6 = (this.I2[n3] ? 3 : 2);
                }
                if (b2 == b) {
                    n6 = 10;
                    if (b2 != 0) {
                        graphics.drawImage(this.I64[5], n5, n4, null);
                    }
                    if (this.I9[n3]) {
                        n6 = 8;
                        if (this.I6[n3]) {
                            ++n6;
                            graphics.drawImage(this.I64[4], n5, n4, null);
                        }
                    }
                }
                try {
                    if (b2 == b) {
                        if (this.I9[n3]) {
                            graphics.drawImage(this.I64[n6], n5, n4 + 1, null);
                        }
                        else {
                            graphics.drawImage(this.I64[n6], n5 + 2, n4 + 1, null);
                        }
                    }
                    else {
                        graphics.drawImage(this.I64[n6], n5, n4, null);
                    }
                    if (b2 == b - 1 && this.I9[n3]) {
                        n6 = (this.I6[n3] ? 7 : 6);
                        graphics.drawImage(this.I64[n6], n5 + 3, n4 + 3, null);
                    }
                }
                catch (Exception ex) {}
                if ((n6 == 1 || n6 == 2 || ((n6 == 6 || n6 == 7) && !this.I2[n3]) || (this.I6[n3] && b2 == b)) && this.I3[n3] > 1) {
                    for (byte b3 = 1; b3 < this.I3[n3]; ++b3) {
                        graphics.drawImage(this.I64[1], n5, n4 + b3 * this.I45, null);
                    }
                }
            }
            final int n7 = b * this.I44 + 18;
            this.I0(graphics, n3, n7, n4 + this.I45, false, this.I51.width - n7 - 6);
            this.I73[i17 - 1].reshape(n7 - 2, n4, this.I51.width - n7 + 4, this.I3[n3] * this.I45);
            this.I18[i17 - 1].reshape(0, n4, this.I51.width, this.I3[n3] * this.I45);
            if ((this.I74[i17 - 1] = n3) == this.I22) {
                n2 = i17 - 1;
            }
            if (this.I2[n3]) {
                array[b - 1] = false;
            }
            if (this.I9[n3] && !this.I6[n3]) {
                for (short n8 = 0; n8 < this.I8[n3]; ++n8) {
                    if (this.I9[n8 + n3]) {
                        this.I6[n8 + n3] = false;
                    }
                }
                n3 += this.I8[n3];
            }
        }
        this.I17 = i17;
        if (this.I23 && this.I75 != 0) {
            this.I77 = n2 * 100 / this.I75;
        }
        this.I23 = false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.I0(n, n2, true) == 0) {
            this.I3(n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.I48 != -1) {
            this.I48 = -1;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        try {
            if (!this.I30) {
                return true;
            }
            final int i0 = this.I0(n, n2, false);
            if (i0 == this.I48) {
                return true;
            }
            this.showStatus(" ");
            if (this.I48 != -1) {
                this.I0(this.I48, false);
                this.I48 = -1;
            }
            if (i0 >= 0 && i0 < this.I47) {
                this.I48 = i0;
                this.showStatus(this.I7[this.I74[i0]]);
                this.I0(this.I48, true);
            }
            System.gc();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            final int i0 = this.I0(n, n2, true);
            if (i0 == 0) {
                this.I3(n2);
            }
            if (i0 > 0) {
                if (i0 == 1) {
                    this.I0 = true;
                    this.I76 = 0;
                    this.I39 = 0;
                }
                if (i0 == 2) {
                    this.I0 = false;
                }
                if (i0 == 3) {
                    this.I70 = !this.I70;
                    this.I66 = new Font(this.I66.getName(), this.I66.getStyle(), this.I66.getSize() + (this.I70 ? 1 : -1));
                    this.I5();
                    this.I4();
                }
                if (i0 == 4) {
                    this.I4(7);
                    this.I1();
                    this.I4();
                    this.I5();
                }
                this.repaint();
            }
            if (!this.I30) {
                return true;
            }
            final int i2 = this.I0(n, n2, false);
            if (i2 > this.I47 || i2 < 0) {
                return true;
            }
            final int n3 = this.I74[i2];
            if (n3 > this.I47 || n3 < 0) {
                return true;
            }
            this.I1(n3);
        }
        catch (Throwable t) {}
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.I53 != 0 || !this.I72 || this.I41 == null) {
            if (this.I53 == 1 || this.I53 == 2) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
                graphics.setFont(new Font("Helvetica", 0, 11));
                graphics.setColor(Color.black);
                if (this.I53 == 1) {
                    String s = "Error: " + this.I54;
                    if (this.I43 != -1) {
                        s = s + " / " + this.I43;
                    }
                    graphics.drawString(s, 3, 140);
                }
                if (this.I53 == 2) {
                    graphics.drawString("Loading resources: " + (12 - this.I40), 3, 120);
                }
            }
            else if (this.I53 == 3) {
                this.I0(graphics, this.bounds(), this.I12[0]);
                graphics.setColor(this.I65[0]);
                graphics.drawString("Loading...", 10, 10);
            }
            return;
        }
        this.showStatus(" ");
        this.I0(graphics, this.bounds(), this.I12[5]);
        if (this.I37[1] != null) {
            graphics.drawImage(this.I37[1], 0, 0, null);
        }
        graphics.setColor(this.I12[this.I0 ? 4 : 0]);
        graphics.drawLine(1, 36, 44 + (this.I0 ? 0 : 2), 36);
        graphics.setColor(this.I12[this.I0 ? 0 : 4]);
        graphics.drawLine(44 + (this.I0 ? 0 : 2), 36, this.bounds().width - 2, 36);
        this.I0(graphics, this.I55);
        this.I61 = false;
        this.I33 = this.I0(false);
        final int n = this.I0 ? this.I31 : this.I33;
        final int abs = Math.abs(this.I62);
        if (n > this.I51.height && abs != 0) {
            this.I61 = true;
        }
        else {
            if (!this.I0) {
                this.I77 = 0;
            }
            this.I79 = 0;
        }
        final int max = Math.max(this.I63 / 2, this.I51.height * (this.I60.height - 2 * this.I63) / n);
        if (!this.I0) {
            this.I57 = max;
        }
        else {
            this.I58 = max;
        }
        this.I37[3] = this.createImage(this.I63, max);
        this.I0(this.I37[3].getGraphics(), 0, 0, this.I63, max, true);
        this.I0(graphics, 0, 0);
        this.I0(graphics, this.I51);
        if (!this.I0) {
            graphics.drawImage(this.I41, this.I51.x, this.I51.y - this.I79, null);
            return;
        }
        graphics.drawImage(this.I36, this.I51.x, this.I51.y - this.I39, null);
    }
    
    private void I0(final Graphics graphics, final int n, final int n2) {
        this.showStatus(" ");
        final int n3 = this.I60.x - n;
        final int n4 = this.I60.y - n2;
        final int n5 = this.I0 ? this.I76 : this.I77;
        final int n6 = this.I0 ? this.I58 : this.I57;
        if (this.I37[2] != null) {
            graphics.drawImage(this.I37[2], n3, n4, null);
            graphics.drawImage(this.I37[2], n3, n4, null);
        }
        final int n7 = this.I60.height - this.I63 - n6;
        final int max = Math.max(Math.min(this.I63 + n5 * n7 / 100, n7), this.I63);
        if (this.I37[3] != null && this.I61) {
            graphics.drawImage(this.I37[3], n3, n4 + max, null);
            graphics.drawImage(this.I37[3], n3, n4 + max, null);
        }
    }
    
    public void run() {
        try {
            this.I3();
        }
        catch (Exception ex2) {
            try {
                this.I3();
            }
            catch (Exception ex) {
                this.I54 = 0;
                System.out.println(ex.toString());
                this.I53 = 1;
            }
        }
        if (this.I53 > 0) {
            System.out.println("Report: " + this.I54);
            this.I72 = false;
            if (this.I19 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.I19, "_self");
            }
            this.I38 = true;
            this.stop();
        }
        System.gc();
    }
    
    private void I4(int n) {
        int n2 = (int)Math.round(Math.random() * 6.0);
        if (n2 == 0) {
            n2 = 6;
        }
        if (n == 7) {
            n = n2;
        }
        if (n > 6 || n < 0) {
            n = 4;
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        if (n == 1 || n == 4 || n == 6) {
            n3 = 1;
        }
        if (n == 2 || n == 4 || n == 5) {
            n4 = 1;
        }
        if (n == 3 || n == 5 || n == 6) {
            n5 = 1;
        }
        this.I12[5] = new Color(238 + 17 * n3, 238 + 17 * n4, 238 + 17 * n5);
        this.I12[1] = new Color(204 + 34 * n3, 204 + 34 * n4, 204 + 34 * n5);
        this.I12[0] = new Color(153 + 51 * n3, 153 + 51 * n4, 153 + 51 * n5);
        this.I12[4] = new Color(102 + 51 * n3, 102 + 51 * n4, 102 + 51 * n5);
        this.I12[3] = new Color(51 + 51 * n3, 51 + 51 * n4, 51 + 51 * n5);
        this.I65[0] = this.I12[3];
        this.I65[1] = this.I12[0];
        this.I65[2] = this.I12[1];
    }
    
    public void start() {
        if (this.I46 == null) {
            (this.I46 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.I46 != null) {
            this.I46.stop();
            this.I46 = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.I10 = this.createImage(this.size().width, this.size().height);
        this.paint(this.I26 = this.I10.getGraphics());
        graphics.drawImage(this.I10, 0, 0, null);
        this.I10.flush();
        this.I26.dispose();
    }
    
    private String[] I0(final String s, int n, final FontMetrics fontMetrics, final boolean b, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final int n3 = 40;
        final String[] array = new String[n3];
        String s2 = "";
        String s3 = "";
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            array[i] = "";
        }
        while (stringTokenizer.hasMoreTokens() && n4 < n3) {
            String nextToken = stringTokenizer.nextToken();
            String string = s3 + " " + nextToken;
            if (nextToken.equalsIgnoreCase("§")) {
                if (n4 < n3) {
                    array[n4] = s3.trim();
                }
                ++n4;
                string = "";
                s3 = "";
                nextToken = "";
            }
            if (fontMetrics.stringWidth(string) > n - 2 * this.I68) {
                if (n4 == 0 && b) {
                    n += this.I44 - n2;
                }
                if (n4 < n3) {
                    array[n4] = s3.trim();
                }
                if (array[n4].length() == 0) {
                    if (n4 < n3) {
                        array[n4] = nextToken.trim();
                    }
                    s3 = "";
                }
                else {
                    s3 = nextToken;
                }
                ++n4;
            }
            else {
                s3 = string;
            }
            s2 = "";
        }
        if (n4 < n3) {
            array[n4] = (s3 + " " + s2).trim();
        }
        return array;
    }
}
