import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
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

public final class tr5trial extends Applet implements Runnable
{
    private int x0;
    private int x1;
    private String[] x2;
    private boolean[] x3;
    private byte[] x4;
    private byte[] x5;
    private String[] x6;
    private boolean[] x7;
    private String[] x8;
    private byte[] x9;
    private short[] x10;
    private boolean[] x11;
    private Image x12;
    private URL x13;
    private String x14;
    private Color[] x15;
    private String x16;
    private Rectangle[] x17;
    private URL x18;
    private String x19;
    private Dimension x20;
    private Dimension x21;
    private Dimension x22;
    private URL x23;
    private int x24;
    private int x25;
    public int folder;
    private boolean x26;
    private int x27;
    private int x28;
    private Graphics x29;
    private Graphics x30;
    private Graphics x31;
    private Graphics x32;
    private boolean[] x33;
    private boolean x34;
    private int x35;
    private Image x36;
    private Image x37;
    private Image x38;
    private Image[] x39;
    private String[] x40;
    private int x41;
    private int x42;
    private boolean x43;
    private int x44;
    private Image x45;
    private Image x46;
    private String[] x47;
    private int x48;
    private int x49;
    private int x50;
    private Thread x51;
    private int x52;
    private int x53;
    private int x54;
    private boolean x55;
    private int x56;
    private String x57;
    private int x58;
    private int x59;
    private Rectangle x60;
    private String[] x61;
    private int x62;
    private int x63;
    private Rectangle x64;
    private boolean x65;
    private Rectangle x66;
    private int x67;
    private int x68;
    private int x69;
    private int x70;
    private int x71;
    private int x72;
    private String[][] x73;
    private Image[] x74;
    private Color[][] x75;
    private Font[][] x76;
    private int[][] x77;
    private int x78;
    private String x79;
    private boolean x80;
    private Rectangle[] x81;
    private int[] x82;
    private int x83;
    private int x84;
    private int x85;
    private int x86;
    private int x87;
    private int x88;
    
    private void x0() {
        int n = 0;
        int x70 = 0;
        int n2 = 0;
        do {
            this.x33[n2] = false;
        } while (++n2 < 4);
        this.x22 = this.x0(false);
        final int width = this.x22.width;
        final int height = this.x22.height;
        final int width2 = this.x60.width;
        final int height2 = this.x60.height;
        final int abs = Math.abs(this.x67);
        final int abs2 = Math.abs(this.x69);
        if (height > height2 && abs2 != 0) {
            n = this.x70;
        }
        if (width > width2 - n && this.x67 != 0) {
            x70 = this.x70;
        }
        if (height > height2 - x70 && this.x69 != 0) {
            this.x33[2] = true;
            n = this.x70;
        }
        if (width > width2 - n && this.x67 != 0) {
            this.x33[3] = true;
        }
        int n3 = 0;
        int x71 = 0;
        if ((height > height2 && abs2 != 0) || abs2 == 2) {
            n3 = this.x70;
        }
        if ((width > width2 - n3 && this.x67 != 0) || abs == 2) {
            x71 = this.x70;
        }
        if ((height > height2 - x71 && this.x69 != 0) || abs2 == 2) {
            this.x33[0] = true;
            n3 = this.x70;
        }
        if ((width > width2 - n3 && this.x67 != 0) || abs == 2) {
            this.x33[1] = true;
        }
        if (!this.x33[2]) {
            this.x85 = 0;
            this.x88 = 0;
        }
        if (!this.x33[3]) {
            this.x35 = (this.x65 ? 100 : 0);
            this.x86 = (this.x65 ? (this.x21.width - this.x60.width) : 0);
        }
        final int width3 = this.bounds().width;
        final int height3 = this.bounds().height;
        int x72 = this.x25;
        int x73 = this.x28;
        int n4 = width3 - this.x25 - this.x27;
        int n5 = height3 - this.x28 - this.x24;
        if (this.x33[0] || Math.abs(this.x69) == 2) {
            if (this.x69 < 0) {
                x72 += this.x70;
            }
            n4 -= this.x70;
        }
        if (this.x33[1] || Math.abs(this.x67) == 2) {
            if (this.x67 < 0) {
                x73 += this.x70;
            }
            n5 -= this.x70;
        }
        this.x60 = new Rectangle(x72, x73, n4, n5);
        (this.x30 = this.x37.getGraphics()).clipRect(x72 - this.x64.x, x73 - this.x64.y, n4, n5);
    }
    
    private void x1() {
        System.gc();
        this.repaint();
    }
    
    private void x0(final int n, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x80 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(2999L);
            }
            catch (Exception ex) {}
            this.x80 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        if (this.x11[n]) {
            if (this.x84 >= this.x1) {
                final boolean b2 = this.x7[n];
                this.x1(n, b);
                if (!b2) {
                    this.x7[n] = true;
                    if (b) {
                        this.x2();
                    }
                }
            }
            else {
                this.x7[n] = !this.x7[n];
                if (b) {
                    this.x2();
                }
            }
            this.folder = n;
            this.x26 = false;
            this.x6();
        }
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x2[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken().trim();
            int n2 = s.length();
            if (s.toLowerCase().startsWith("audio")) {
                try {
                    this.play(this.x18, s.substring(6, n2).trim());
                }
                catch (OutOfMemoryError outOfMemoryError) {}
            }
            if (s.toLowerCase().startsWith("sub")) {
                int n3 = 0;
                do {
                    if (s.toLowerCase().startsWith(this.x73[n3][1])) {
                        s = this.x73[n3][0] + s.substring(5, n2);
                        n2 = s.length();
                    }
                } while (++n3 < 10);
            }
            if (s.toLowerCase().startsWith("link")) {
                int index = s.indexOf(",");
                String s2;
                if (index == -1 || n2 - index < 2) {
                    s2 = this.x19;
                    index = n2;
                }
                else {
                    s2 = s.substring(index + 1, n2).trim();
                }
                final String trim = s.substring(5, index).trim();
                try {
                    url = new URL(this.x18, trim);
                }
                catch (Exception ex2) {
                    if (trim.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.x18, "mailto.htm");
                        }
                        catch (Exception ex3) {}
                    }
                    else {
                        System.out.println("Link failed: " + trim);
                    }
                }
                if (!this.x11[n] || this.x7[n]) {
                    this.getAppletContext().showDocument(url, s2);
                }
                if (this.x59 != -1) {
                    this.x4[this.x59] = 2;
                }
                this.x4[n] = 1;
                this.x59 = n;
                this.x6();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x79 = this.x79;
                this.x79 = s.substring(6, n2).trim();
                this.x34 = false;
                this.x43 = false;
                this.x5();
                if (this.x62 > 0 && (this.x23 == null || this.x63 < 10)) {
                    this.x79 = x79;
                    this.x34 = false;
                    this.x43 = false;
                    this.x5();
                }
                if (this.x62 > 0 && (this.x23 == null || this.x63 < 10)) {
                    this.x80 = false;
                    if (this.x23 == null) {
                        this.repaint();
                    }
                    else {
                        this.getAppletContext().showDocument(this.x23, "_self");
                    }
                    this.x43 = true;
                    this.stop();
                }
            }
            if (s.toLowerCase().startsWith("script")) {
                try {
                    JSObject.getWindow((Applet)this).eval(s.substring(7, n2));
                }
                catch (Throwable t) {
                    System.out.println(t);
                }
            }
        }
    }
    
    public void clik(final int n) {
        this.x0(n, false);
        this.repaint();
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle) {
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void x1(final int n, boolean b) {
        byte b2 = this.x5[n];
        final int[] array = new int[b2];
        final byte b3 = b2;
        --b2;
        for (int i = n; i >= 0; --i) {
            if (this.x5[i] == b2) {
                array[b2] = i;
                --b2;
            }
        }
        final byte b4 = b3;
        boolean b5 = false;
        for (int j = 0; j < this.x84; ++j) {
            if (n == this.x82[j]) {
                b5 = true;
            }
        }
        if (!b5) {
            for (int k = 0; k <= n; ++k) {
                if (this.x11[k]) {
                    this.x7[k] = true;
                }
            }
            this.x6();
            b = false;
        }
        for (int l = this.x84 - 1; l >= 0; --l) {
            final int n2 = this.x82[l];
            if (this.x11[n2] && this.x7[n2]) {
                boolean b6 = false;
                for (byte b7 = 0; b7 < b4; ++b7) {
                    if (n2 == array[b7]) {
                        b6 = true;
                    }
                }
                if (!b6) {
                    this.x7[n2] = false;
                    this.x56 = l;
                    if (b && this.x84 < 200) {
                        this.x2();
                    }
                }
            }
        }
        for (int x56 = this.x84 - 1; x56 >= 0; --x56) {
            if (this.x82[x56] == n) {
                this.x56 = x56;
            }
        }
    }
    
    private int[] x0(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final boolean b) {
        final int[] array3 = new int[n9];
        if (array2 != null) {
            System.arraycopy(array2, 0, array3, 0, n9);
        }
        int n10 = 255;
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                final int n11 = (i + n2) * n7 + (j + n);
                final int n12 = (i + n4) * n8 + (j + n3);
                try {
                    if (b) {
                        n10 = (array[n11] >> 24 & 0xFF);
                    }
                    if (n10 > 128) {
                        array3[n12] = array[n11];
                    }
                }
                catch (IndexOutOfBoundsException ex) {}
            }
        }
        return array3;
    }
    
    private void x2() {
        this.x34 = false;
        try {
            if (this.x0 != 0) {
                this.x3();
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
        this.x6();
        this.repaint();
        this.x34 = true;
    }
    
    private int x0(final Graphics graphics, final int n, int n2, final int n3, final boolean b) {
        final String s = this.x6[n];
        if (s == null || s.length() <= 0) {
            return 0;
        }
        final byte b2 = this.x9[n];
        final byte b3 = this.x4[n];
        byte b4 = (byte)((b3 > 0) ? (b3 + 1) : b3);
        if (b) {
            b4 = 1;
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
        final String s2 = "img1:";
        String s3 = null;
        final int index3 = s.toLowerCase().indexOf(s2);
        if (index3 != -1) {
            int index4 = s.indexOf(";", index3);
            if (index4 == -1) {
                index4 = length;
            }
            s3 = s.substring(index3 + 5, index4);
        }
        final int index5 = s.toLowerCase().indexOf("img" + (b4 + 1) + ":");
        if (index5 != -1) {
            int index6 = s.indexOf(";", index5);
            if (index6 == -1) {
                index6 = length;
            }
            s3 = s.substring(index5 + 5, index6);
        }
        final Font font = this.x76[b2][b];
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
        final int descent = fontMetrics.getDescent();
        int n5;
        final int n4 = n5 = ((substring != null) ? fontMetrics.stringWidth(substring) : 0);
        Image image = null;
        if (s3 != null) {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                image = this.getImage(this.x13, s3);
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex2) {}
                n5 += image.getWidth(this);
            }
            catch (Exception ex) {
                System.out.println("E28 " + ex);
            }
        }
        final Color color = this.x75[b2][4];
        if (b && !color.equals(this.x15[0]) && (substring != null || image != null)) {
            graphics.setColor(color);
            graphics.fillRect((this.x55 && b2 == 4) ? (n2 - 2 + this.x83) : (n2 - 2), 0, (this.x55 && b2 != 4) ? (n5 + this.x83 + 4) : (n5 + 4), this.x66.height);
        }
        if (this.x55) {
            graphics.setColor(this.x75[b2][b ? 3 : b3]);
            final int n6 = this.x20.width / 2;
            final int n7 = (this.x20.height - 3) / 2;
            final int n8 = n3 - this.x50 / 2 + n7;
            if (this.x11[n]) {
                for (int i = 0; i < n7; ++i) {
                    if (this.x7[n]) {
                        graphics.drawLine(n2 + n6 - i, n8 - i, n2 + n6 + i, n8 - i);
                    }
                    else {
                        graphics.drawLine(n2 + n7 + n6 - i, n8 - n7 - i, n2 + n7 + n6 - i, n8 - n7 + i);
                    }
                }
            }
            else {
                int n9 = 0;
                do {
                    final int min = Math.min(2, (n9 < 2) ? (-n9 - 1) : (5 - n9));
                    graphics.drawLine(n2 + 7 + n9, n8 - 5 - min, n2 + 7 + n9, n8 - 5 + min);
                } while (++n9 < 5);
            }
            n2 += this.x83;
        }
        if (substring != null) {
            final byte b5 = (byte)(b ? 3 : b3);
            if (this.x65) {
                n2 -= n5;
            }
            n2 += this.x77[b2][0];
            graphics.setFont(font);
            if (this.x78 > 0) {
                if (this.x78 > 1) {
                    if (b) {
                        graphics.setColor(this.x75[b2][7]);
                    }
                    else {
                        graphics.setColor(this.x75[b2][6]);
                    }
                    int n10 = 0;
                    do {
                        int n11 = 0;
                        do {
                            graphics.drawString(substring, n2 + n10, n3 + n11 + this.x77[b2][1] - descent);
                        } while (++n11 < 3);
                    } while (++n10 < 3);
                }
                graphics.setColor(this.x75[b2][5]);
                graphics.drawString(substring, n2 + 1, n3 + 1 + this.x77[b2][1] - descent);
            }
            graphics.setColor(this.x75[b2][b5]);
            graphics.drawString(substring, n2, n3 + this.x77[b2][1] - descent);
        }
        if (image != null) {
            n2 += this.x41;
            graphics.drawImage(image, n2 + n4, n3 - this.x50 + this.x42, this);
        }
        return n2 + n5;
    }
    
    private void x3() {
        final int n = this.x84 * this.x50;
        final Rectangle rectangle = this.x81[this.x56];
        final int max = Math.max(0, rectangle.y + rectangle.height - this.x88);
        this.x6();
        final int n2 = this.x84 * this.x50 - n;
        final int width = this.x60.width;
        final int n3 = this.x60.height - max;
        final int x = this.x60.x;
        final int n4 = this.x60.y + max;
        Graphics graphics = this.getGraphics();
        if (n2 > 0) {
            graphics.clipRect(x, this.x60.y, width, max);
            graphics.drawImage(this.x46, x - this.x86, this.x60.y - this.x88, null);
            graphics = this.getGraphics();
        }
        graphics.clipRect(x, n4, width, n3);
        final int n5 = x - this.x86;
        final int n6 = n4 - this.x88 - max;
        if (n2 > 0) {
            final int n7 = n6 - n2;
            for (int i = 0; i <= n2; i += this.x0) {
                graphics.drawImage(this.x46, n5, n7 + i, null);
            }
            graphics.drawImage(this.x46, n5, n7 + n2, null);
            return;
        }
        for (int j = 0; j > n2; j -= this.x0) {
            graphics.drawImage(this.x36, n5, n6 + j, null);
        }
        graphics.drawImage(this.x46, n5, n6, null);
    }
    
    private void x2(final int n, final boolean b) {
        if (n >= this.x52 || n < 0 || !this.x80) {
            return;
        }
        int n2 = 7;
        final int n3 = this.x82[n];
        byte b2 = (byte)((this.x9[n3] + 2) * 4);
        if (b) {
            b2 += 2;
        }
        if (this.x11[n3]) {
            if (this.x7[n3]) {
                ++b2;
                n2 = 6;
            }
        }
        else if (b) {
            ++b2;
        }
        else {
            b2 += this.x4[n3];
        }
        final int n4 = this.x65 ? (-this.x20.width) : 0;
        final Rectangle rectangle = this.x81[n];
        final int n5 = this.x5[n3] * this.x49;
        if (this.x40[0] != null && this.x39[0] != null) {
            final int width = this.x39[0].getWidth(null);
            final int height = this.x39[0].getHeight(null);
            final int n6 = this.x46.getWidth(null) / width;
            final int n7 = this.x46.getHeight(null) / height;
            final int n8 = this.x64.x + n5 + rectangle.x + this.x53;
            for (int i = 0; i <= n7; ++i) {
                for (int j = 0; j <= n6; ++j) {
                    this.x32.drawImage(this.x39[0], j * width - n8, i * height - rectangle.y, null);
                }
            }
        }
        else {
            this.x0(this.x32, this.x66, this.x15[0]);
        }
        if (!this.x55) {
            if (n5 != 0 || n2 != 7) {
                this.x32.drawImage(this.x74[n2], n4, 0, null);
            }
            this.x32.drawImage(this.x74[b2], n4, 0, null);
        }
        this.x0(this.x32, n3, this.x65 ? (-this.x83) : this.x83, this.x50, b);
        int n9;
        if (this.x65) {
            n9 = this.x21.width - n5 - this.x53;
        }
        else {
            n9 = n5 + this.x53;
        }
        final int n10 = n9 + (rectangle.x + this.x60.x - this.x86);
        final int n11 = rectangle.y + this.x60.y - this.x88;
        final Graphics x4 = this.x4();
        x4.drawImage(this.x38, n10, n11, null);
        x4.dispose();
    }
    
    private void x0(final int n, int x, int y) {
        final int n2 = (n == 1) ? (x - this.x17[1].x) : (y - this.x17[0].y);
        final int n3 = (n == 1) ? (this.x22.width - this.x60.width) : (this.x22.height - this.x60.height + 20);
        final int n4 = (n == 1) ? this.x35 : this.x85;
        final int n5 = (n == 1) ? this.x17[1].width : this.x17[0].height;
        int n6;
        if (n2 < this.x70) {
            n6 = Math.max(0, n4 - this.x68);
        }
        else if (n2 > n5 - this.x70) {
            n6 = Math.min(100, n4 + this.x68);
        }
        else {
            n6 = Math.max(Math.min((n2 - this.x70) * 100 / (n5 - 2 * this.x70), 100), 0);
        }
        final int n7 = n3 * n6 / 100;
        if (n == 1) {
            this.x35 = n6;
            this.x86 = n7;
        }
        else {
            this.x85 = n6;
            this.x88 = n7;
        }
        System.gc();
        x = this.x64.x;
        y = this.x64.y;
        final Graphics graphics = this.getGraphics();
        try {
            if (this.x40[1] != null && this.x39[1] != null) {
                this.x31.drawImage(this.x39[1], -x, -y, null);
            }
            else {
                this.x0(this.x31, this.x64, this.x15[0]);
            }
            this.x0(this.x31, x, y);
            this.x30.drawImage(this.x46, this.x60.x - x - this.x86, this.x60.y - y - this.x88, null);
            graphics.drawImage(this.x37, x, y, null);
        }
        catch (OutOfMemoryError outOfMemoryError) {}
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private Dimension x0(final boolean b) {
        int n = 0;
        int max = 0;
        final Image image = this.createImage(2, 2);
        final Graphics graphics = image.getGraphics();
        for (short n2 = 0; n2 < this.x52; ++n2) {
            ++n;
            max = Math.max(max, (this.x5[n2] + 1) * this.x49 + this.x83 + this.x0(graphics, n2, 0, 0, false));
            if (!b && this.x11[n2] && !this.x7[n2]) {
                n2 += this.x10[n2];
            }
        }
        image.flush();
        return new Dimension(max, b ? (this.x52 * this.x50 + 10) : (n * this.x50 + 10));
    }
    
    private boolean x0(final Graphics graphics) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[4];
        int n = 0;
        do {
            if (this.x40[n] != null) {
                try {
                    mediaTracker.addImage(array[n] = this.getImage(this.x13, this.x40[n]), n);
                }
                catch (Exception ex) {}
            }
        } while (++n < 4);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.x63 = 1;
            return false;
        }
        int n2 = 0;
        while (!mediaTracker.isErrorID(n2)) {
            if (++n2 >= 4) {
                ++this.x44;
                this.x1();
                if (array[2] != null) {
                    final int width = this.x20.width;
                    final int height = this.x20.height;
                    int[] x0 = new int[width * height];
                    final int width2 = array[2].getWidth(null);
                    final int height2 = array[2].getHeight(null);
                    final int n3 = (this.x72 + 2) * (width + 1) - 1;
                    final int n4 = 4 * (height + 1) - 1;
                    if (this.x23 == null && (n3 > width2 || n4 > height2)) {
                        this.x63 = 25;
                        return false;
                    }
                    final int min = Math.min(n3, width2);
                    final int min2 = Math.min(n4, height2);
                    final int[] array2 = new int[width2 * height2];
                    final int[] array3 = new int[n3 * n4];
                    for (int i = 0; i < n3 * n4; ++i) {
                        array3[i] = this.x15[1].getRGB();
                    }
                    final int[] x2 = this.x0(array[2], 0, 0, width2, height2);
                    for (int j = 0; j < min2; ++j) {
                        System.arraycopy(x2, j * width2, array3, j * n3, min);
                    }
                    for (int k = 0; k < this.x72 + 2; ++k) {
                        int n5 = 0;
                        do {
                            final int n6 = k * 4 + n5;
                            x0 = this.x0(array3, x0, k * (width + 1), n5 * (height + 1), 0, 0, width, height, n3, width, width * height, false);
                            this.x74[n6] = this.createImage(new MemoryImageSource(width, height, this.x0(x0, width * height, this.x15[1]), 0, width));
                        } while (++n5 < 4);
                    }
                    for (int l = 0; l < this.x72 * 4; ++l) {
                        final int width3 = this.x20.width;
                        final int height3 = this.x20.height;
                        this.x63 = 27;
                        this.x48 = l;
                        if (this.x74[l] == null) {
                            if (this.x23 == null) {
                                return false;
                            }
                            this.x74[l] = this.createImage(width3, height3);
                            final Graphics graphics2 = this.x74[l].getGraphics();
                            graphics2.setColor(this.x15[1]);
                            graphics2.fillRect(0, 0, width3, height3);
                            graphics2.dispose();
                        }
                    }
                }
                else {
                    if (!this.x55) {
                        this.x53 -= this.x83;
                    }
                    this.x55 = true;
                }
                ++this.x44;
                this.x1();
                if (array[3] != null) {
                    final int x3 = this.x70;
                    final int x4 = this.x70;
                    final int width4 = array[3].getWidth(null);
                    final int height4 = array[3].getHeight(null);
                    final int[] array4 = new int[width4 * height4];
                    final int[] x5 = this.x0(this.x0(array[3], 0, 0, width4, height4), width4 * height4, this.x15[1]);
                    if (6 * x3 > width4 || x4 > height4) {
                        this.x63 = 26;
                        return false;
                    }
                    int n7 = 0;
                    do {
                        this.x39[n7 + 5] = this.createImage(new MemoryImageSource(x3, x4, this.x0(x5, null, n7 * (x3 + 1), 0, 0, 0, x3, x4, width4, x3, x3 * x4, false), 0, x3));
                    } while (++n7 < 6);
                    final int max = Math.max(this.x17[0].height, this.x17[1].width);
                    int[] x6 = new int[this.x70 * max];
                    for (int n8 = 0; n8 < max / this.x70; ++n8) {
                        x6 = this.x0(x5, x6, 5 * x3 + 5, 0, 0, n8 * this.x70, x3, x4, width4, x3, x3 * max, false);
                    }
                    final int height5 = this.x17[0].height;
                    this.x39[2] = this.createImage(new MemoryImageSource(this.x70, height5, this.x0(x5, this.x0(x5, x6, 0, 0, 0, 0, x3, x4, width4, x3, x3 * height5, true), 4 * x3 + 4, 0, 0, height5 - this.x70, x3, x4, width4, x3, x3 * height5, true), 0, this.x70));
                    final int width5 = this.x17[1].width;
                    final int[] x7 = this.x0(x5, this.x0(x5, x6, 0, 0, 0, 0, x3, x4, width4, x3, x3 * width5, true), 4 * x3 + 4, 0, 0, width5 - this.x70, x3, x4, width4, x3, x3 * width5, true);
                    final int[] array5 = new int[this.x70 * width5];
                    for (int n9 = 0; n9 < width5; ++n9) {
                        for (int n10 = 0; n10 < this.x70; ++n10) {
                            final int n11 = n9 * this.x70 + n10;
                            final int n12 = n10 * width5 + n9;
                            try {
                                array5[n12] = x7[n11];
                            }
                            catch (IndexOutOfBoundsException ex3) {}
                        }
                    }
                    this.x39[3] = this.createImage(new MemoryImageSource(width5, this.x70, array5, 0, width5));
                }
                else {
                    final int x8 = this.x70;
                    final int n13 = this.x70 / 2;
                    final int n14 = x8 / 3;
                    int n15 = 0;
                    do {
                        this.x39[n15 + 5] = this.createImage(x8, x8);
                        final Graphics graphics3 = this.x39[n15 + 5].getGraphics();
                        graphics3.setColor(this.x15[0]);
                        graphics3.fillRect(0, 0, x8, x8);
                        if (n15 == 2 || n15 == 1) {
                            graphics3.setColor(this.x75[1][0]);
                            for (int n16 = 0; n16 < n14; ++n16) {
                                if (n15 == 2) {
                                    graphics3.drawLine(n13 - n16, n14 + n16 - 2, n13 + n16, n14 + n16 - 2);
                                    graphics3.drawLine(n13 - n16, x8 - n16 - 2, n13 + n16, x8 - n16 - 2);
                                }
                                else {
                                    graphics3.drawLine(n14 + n16 - 2, n13 - n16, n14 + n16 - 2, n13 + n16);
                                    graphics3.drawLine(x8 - n16 - 2, n13 - n16, x8 - n16 - 2, n13 + n16);
                                }
                            }
                        }
                    } while (++n15 < 6);
                    this.x39[2] = this.createImage(this.x70, this.x17[0].height);
                    final Graphics graphics4 = this.x39[2].getGraphics();
                    graphics4.setColor(this.x15[0]);
                    graphics4.fillRect(0, 0, this.x70, this.x17[0].height);
                    graphics4.setColor(this.x75[1][0]);
                    graphics4.drawLine(n13, n13, n13, this.x17[0].height - n13);
                    this.x39[3] = this.createImage(this.x17[1].width, this.x70);
                    final Graphics graphics5 = this.x39[3].getGraphics();
                    graphics5.setColor(this.x15[0]);
                    graphics5.fillRect(0, 0, this.x17[1].width, this.x70);
                    graphics5.setColor(this.x75[1][0]);
                    graphics5.drawLine(n13, n13, this.x17[1].width - n13, n13);
                }
                ++this.x44;
                this.x1();
                int n17 = 0;
                do {
                    if (array[n17] != null) {
                        final int width6 = array[n17].getWidth(null);
                        final int height6 = array[n17].getHeight(null);
                        final int[] array6 = new int[width6 * height6];
                        this.x39[n17] = this.createImage(new MemoryImageSource(width6, height6, this.x0(array[n17], 0, 0, width6, height6), 0, width6));
                    }
                } while (++n17 < 2);
                final int width7 = this.x64.width;
                final int height7 = this.x64.height;
                final Dimension x9 = this.x0(true);
                this.x21 = new Dimension(Math.max(x9.width, width7), Math.max(x9.height, height7));
                this.x22 = this.x0(false);
                System.gc();
                return true;
            }
        }
        this.x63 = 20 + n2;
        return false;
    }
    
    private boolean x1(final Graphics graphics) {
        final String[][] array = new String[10000][5];
        if (this.x79 != null) {
            URL url;
            try {
                url = new URL(this.x13, this.x79);
            }
            catch (Exception ex) {
                this.x63 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.x63 = 4;
                return false;
            }
            ++this.x44;
            this.x1();
            final byte[] array2 = new byte[1000000];
            try {
                try {
                    dataInputStream.readFully(array2);
                }
                catch (EOFException ex3) {}
            }
            catch (IOException ex4) {
                this.x63 = 5;
                return false;
            }
            try {
                dataInputStream.close();
            }
            catch (IOException ex5) {}
            ++this.x44;
            this.x1();
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.x52 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x48 = this.x52;
                if (this.x52 >= 9998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.x23 == null) {
                        this.x63 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.x52;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x52;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x23 == null) {
                            this.x63 = 13;
                            return false;
                        }
                        ++this.x52;
                        n = 0;
                    }
                    array[this.x52][n] = streamTokenizer.sval;
                    ++n;
                }
            }
            try {
                byteArrayInputStream.close();
            }
            catch (Exception ex7) {}
        }
        else {
            boolean b2 = false;
            this.x52 = 0;
            while (!b2) {
                if (this.x52 >= 998) {
                    b2 = true;
                }
                this.x61[0] = "entry" + this.x52;
                final String x0 = this.x0(0, "end");
                if (x0.equalsIgnoreCase("end")) {
                    b2 = true;
                }
                else {
                    final StringTokenizer stringTokenizer = new StringTokenizer(x0, "|");
                    int n2 = 0;
                    do {
                        String nextToken2;
                        try {
                            nextToken2 = stringTokenizer.nextToken();
                        }
                        catch (Exception ex8) {
                            nextToken2 = " ";
                        }
                        array[this.x52][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x52;
            }
            --this.x52;
        }
        ++this.x44;
        this.x1();
        for (int i = 0; i < this.x52; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x23 == null) {
                    this.x63 = 10;
                    this.x48 = i;
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
        array[this.x52][1] = "0";
        ++this.x44;
        this.x1();
        this.x6 = new String[this.x52 + 1];
        this.x8 = new String[this.x52 + 1];
        this.x2 = new String[this.x52 + 1];
        this.x11 = new boolean[this.x52 + 1];
        this.x7 = new boolean[this.x52 + 1];
        this.x3 = new boolean[this.x52 + 1];
        this.x10 = new short[this.x52 + 1];
        this.x5 = new byte[this.x52 + 1];
        this.x9 = new byte[this.x52 + 1];
        this.x4 = new byte[this.x52 + 1];
        this.x81 = new Rectangle[this.x52 + 1];
        this.x82 = new int[this.x52 + 1];
        for (int j = 0; j < this.x52; ++j) {
            this.x3[j] = false;
            this.x81[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x52; ++k) {
            this.x48 = k;
            this.x4[k] = 0;
            this.x6[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x23 == null) {
                    this.x63 = 10;
                    return false;
                }
                max = 1;
            }
            this.x5[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x11[k] = true;
                this.x9[k] = 0;
                this.x7[k] = (max <= this.x58);
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x52; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n4 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.x10[k] = (short)(l - k - 1);
                this.x3[n4] = true;
            }
            else {
                this.x11[k] = false;
                this.x9[k] = 1;
            }
            this.x2[k] = array[k][3];
            if (this.x2[k].toLowerCase().startsWith("start") || this.x2[k].toLowerCase().indexOf(";start") != -1) {
                this.x71 = k;
            }
            final int int2 = Integer.parseInt(array[k][2]);
            if (int2 > 0 && int2 < this.x72) {
                this.x9[k] = (byte)int2;
            }
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x8[k] = array[k][4];
            }
            else {
                this.x8[k] = " ";
            }
        }
        return true;
    }
    
    private Graphics x4() {
        final Graphics graphics = this.getGraphics();
        this.x0(graphics, this.x60);
        return graphics;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x61[n]);
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
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x61[n]);
        return (parameter != null) ? parameter : s;
    }
    
    public void init() {
        System.gc();
        final String string = "dummy.copyright.textNcolour.textAcolour.textVcolour.textHcolour.textBcolour.textScolour.textNstyle.textHstyle.textfont.textsize." + "textYoffset.textXoffset.menufile.menuYoffset.menuXoffset.menuclosetrigger.menuanimspeed.menudeftarget.menualign.menustyles." + "imgtranscol.imgfile.imgwidth.imgheight.imgXoffset.imgYoffset.entryindent.entryheight." + "frameimage.framewidth.frametop.framebottom.frameleft.frameright.scrollimages.scrollwidth.scrollhoriz.scrollvert.scrolljump.bgcolour.bgimage." + "audiomove.audioclick.startlevel.escapepage.textsymbolseparation.textshadow";
        this.x61 = new String[50];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
        while (stringTokenizer.hasMoreTokens()) {
            this.x61[n] = stringTokenizer.nextToken();
            ++n;
        }
        this.x16 = this.x0(1, " ");
        this.x79 = this.x0(14, null);
        this.x54 = this.x0(15, 300, -300, 0, 10);
        this.x53 = this.x0(16, 300, -300, 2, 10);
        this.x1 = this.x0(17, 50, 0, 0, 10);
        this.x0 = this.x0(18, 50, 0, 10, 10);
        this.x19 = this.x0(19, "_top");
        this.x65 = (this.x0(20, 1, 0, 0, 10) == 1);
        this.x72 = this.x0(21, 50, 2, 3, 10);
        this.x15[1] = new Color(this.x0(22, 0, 0, 12632256, 16));
        this.x40[2] = this.x0(23, null);
        final int x0 = this.x0(24, 300, 10, 13, 10);
        final int x2 = this.x0(25, 50, 10, 14, 10);
        this.x20 = new Dimension(x0, x2);
        this.x41 = this.x0(26, 100, -100, 0, 10);
        this.x42 = this.x0(27, 100, -100, 0, 10);
        this.x49 = this.x0(28, 50, 0, 12, 10);
        this.x50 = x2 + this.x0(29, 50, -50, 0, 10);
        this.x40[1] = this.x0(30, null);
        final int x3 = this.x0(31, 30, 0, 0, 10);
        this.x28 = this.x0(32, 100, 0, x3, 10);
        this.x24 = this.x0(33, 100, 0, x3, 10);
        this.x25 = this.x0(34, 30, 0, x3, 10);
        this.x27 = this.x0(35, 30, 0, x3, 10);
        if (this.x40[1] == null) {
            this.x28 = 0;
            this.x24 = 0;
            this.x25 = 0;
            this.x27 = 0;
        }
        this.x40[3] = this.x0(36, null);
        this.x70 = this.x0(37, 19, 7, 11, 10);
        this.x67 = this.x0(38, 2, -2, 1, 10);
        this.x69 = this.x0(39, 2, -2, 1, 10);
        this.x68 = this.x0(40, 100, 10, 10, 10);
        this.x15[0] = new Color(this.x0(41, 0, 0, 0, 16));
        this.x40[0] = this.x0(42, null);
        this.x57 = this.x0(43, " ");
        this.x14 = this.x0(44, " ");
        this.x58 = this.x0(45, 49, 0, 2, 10);
        try {
            this.x18 = this.getDocumentBase();
            this.x13 = this.getCodeBase();
            this.x23 = new URL(this.x18, this.x0(46, null));
        }
        catch (Throwable t) {
            this.x23 = null;
        }
        this.x83 = this.x0(47, 50, 0, 18, 10);
        this.x78 = this.x0(48, 2, 0, 0, 10);
        this.x15[2] = Color.orange;
        int n2 = 0;
        do {
            this.x61[0] = "sub" + n2;
            this.x73[n2][0] = this.x0(0, "LINK:");
            this.x73[n2][1] = "sub" + n2 + ":";
        } while (++n2 < 10);
        this.x76 = new Font[this.x72][2];
        this.x75 = new Color[this.x72][8];
        this.x77 = new int[this.x72][2];
        int n3 = 0;
        do {
            this.x61[0] = this.x61[2 + n3];
            int n4 = Color.black.getRGB();
            if (n3 == 5) {
                n4 = Color.gray.getRGB();
            }
            if (n3 == 4) {
                n4 = this.x15[0].getRGB();
            }
            if (n3 == 3) {
                n4 = Color.red.getRGB();
            }
            if (n3 == 2) {
                n4 = Color.blue.getRGB();
            }
            if (n3 == 1) {
                n4 = Color.green.getRGB();
            }
            final int x4 = this.x0(0, 0, 0, n4, 16);
            for (int i = 0; i < this.x72; ++i) {
                this.x61[0] = this.x61[2 + n3] + i;
                this.x75[i][n3] = new Color(this.x0(0, 0, 0, x4, 16));
                if (n3 == 5) {
                    final int red = this.x15[0].getRed();
                    final int green = this.x15[0].getGreen();
                    final int blue = this.x15[0].getBlue();
                    final int red2 = this.x75[i][4].getRed();
                    final int green2 = this.x75[i][4].getGreen();
                    final int blue2 = this.x75[i][4].getBlue();
                    final int red3 = this.x75[i][5].getRed();
                    final int green3 = this.x75[i][5].getGreen();
                    final int blue3 = this.x75[i][5].getBlue();
                    this.x75[i][6] = new Color((red3 + red) / 2, (green3 + green) / 2, (blue3 + blue) / 2);
                    this.x75[i][7] = new Color((red2 + red3) / 2, (green2 + green3) / 2, (blue2 + blue3) / 2);
                }
            }
        } while (++n3 < 6);
        final int[] array = { this.x0(8, 3, 0, 0, 10), 0 };
        array[1] = this.x0(9, 3, 0, array[0], 10);
        final String x5 = this.x0(10, "TimesRoman");
        final int x6 = this.x0(11, 30, 6, 11, 10);
        final int x7 = this.x0(12, 50, -50, 0, 10);
        final int x8 = this.x0(13, 50, -50, 0, 10);
        for (int j = 0; j < this.x72; ++j) {
            this.x61[0] = this.x61[10] + j;
            final String x9 = this.x0(0, x5);
            this.x61[0] = this.x61[11] + j;
            final int x10 = this.x0(0, 30, 6, x6, 10);
            int n5 = 0;
            do {
                this.x61[0] = this.x61[8] + j;
                this.x76[j][n5] = new Font(x9, this.x0(0, 3, 0, (j == 0) ? 1 : array[n5], 10), x10);
                this.x61[0] = this.x61[12 + n5] + j;
                this.x77[j][1 - n5] = this.x0(0, 50, -50, (n5 == 0) ? x8 : x7, 10);
            } while (++n5 < 2);
        }
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        final int x11 = this.x25;
        final int x12 = this.x28;
        final int n6 = width - this.x25 - this.x27;
        final int n7 = height - this.x28 - this.x24;
        int x13 = this.x25;
        int x14 = this.x28;
        final int n8 = n6 - this.x70;
        final int x15 = this.x70;
        int x16 = this.x25;
        int x17 = this.x28;
        final int x18 = this.x70;
        final int n9 = n7 - this.x70;
        if (this.x69 > 0) {
            x16 = width - this.x27 - this.x70;
        }
        if (this.x69 < 0) {
            x13 += this.x70;
        }
        if (this.x67 > 0) {
            x14 = height - this.x24 - this.x70;
        }
        if (this.x67 < 0) {
            x17 += this.x70;
        }
        this.x17[0] = new Rectangle(x16, x17, x18, n9);
        this.x17[1] = new Rectangle(x13, x14, n8, x15);
        this.x60 = new Rectangle(x11, x12, n6, n7);
        this.x64 = new Rectangle(x11, x12, n6, n7);
        this.x37 = this.createImage(n6, n7);
        this.x31 = this.x37.getGraphics();
        this.x66 = new Rectangle(0, 0, n6 * 2, this.x50);
        this.x38 = this.createImage(n6 * 2, this.x50);
        this.x32 = this.x38.getGraphics();
        this.x74 = new Image[(this.x72 + 2) * 4];
        System.gc();
    }
    
    private void x5() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.x43) {
            final int n = 8;
            (this.x47 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x47[1] = "TRIAL VERSION ONLY";
            this.x47[2] = "This copyright display";
            this.x47[3] = "is not shown in the";
            this.x47[4] = "purchasable version.";
            this.x47[5] = "Navigation Applet";
            this.x47[6] = "© Image Intelligence Ltd.";
            this.x47[7] = "www.imint.com";
            this.x0(graphics, this.bounds(), this.x15[0]);
            this.x80 = false;
            this.x85 = 0;
            this.x35 = 0;
            char c = '\0';
            this.x56 = 0;
            this.folder = 0;
            this.x87 = 0;
            this.x86 = 0;
            this.x88 = 0;
            this.x59 = -1;
            this.x62 = ((this.x23 == null) ? 2 : 3);
            this.x44 = 1;
            this.x1();
            this.x71 = -1;
            String string = "";
            for (int i = 0; i < n; ++i) {
                string += this.x47[i];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.x16.equalsIgnoreCase(this.x47[0])) {
                this.x63 = 9;
                this.x62 = 1;
                return;
            }
            if (c != 1499310) {
                this.x63 = 30;
                this.x62 = 1;
                return;
            }
            final String[] array2;
            final String[] array = array2 = new String[] { this.x13.getProtocol().toString().toLowerCase(), this.x13.getHost().toString().toLowerCase(), null, null };
            final int n2 = 1;
            array2[n2] += this.x13.getFile().toString().toLowerCase();
            try {
                array[2] = InetAddress.getByName(this.x13.getHost()).toString();
                array[3] = array[2].substring(array[2].indexOf("/") + 1);
            }
            catch (Exception ex4) {
                array[2] = "unavailable";
                array[3] = "...";
            }
            System.out.println(array[1]);
            System.out.println(array[2]);
            System.out.println(array[3]);
            this.x44 = 2;
            this.x1();
            try {
                if (!this.x1(graphics) && !this.x1(graphics)) {
                    this.x62 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x63 = 6;
                this.x62 = 1;
                return;
            }
            this.x44 = 8;
            this.x1();
            try {
                if (!this.x0(graphics) && !this.x0(graphics)) {
                    this.x62 = 1;
                    return;
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.x63 = 7;
                this.x62 = 1;
                return;
            }
            this.x44 = 11;
            this.x1();
            this.x80 = true;
            try {
                this.x6();
                if (this.x46 == null) {
                    this.x6();
                }
                if (this.x46 == null) {
                    this.x63 = 8;
                    this.x62 = 1;
                    return;
                }
                if (this.x84 > 50) {
                    this.x1(0, false);
                    this.x6();
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3);
                this.x63 = 8;
                this.x62 = 1;
                return;
            }
            this.x44 = 12;
            this.x1();
            this.x43 = true;
            this.x34 = true;
            if (this.x65) {
                this.x86 = this.x21.width - this.x60.width + this.x70;
                this.x35 = 100;
            }
            this.x62 = 0;
            if (this.x71 >= 0 && this.x71 < this.x52) {
                this.x0(this.x71, false);
            }
        }
        this.repaint();
    }
    
    private int[] x0(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return array;
    }
    
    private int x0(int n, int n2, final boolean b) {
        if (b) {
            if (this.x17[0].inside(n, n2) && this.x33[2]) {
                return 0;
            }
            if (this.x17[1].inside(n, n2) && this.x33[3]) {
                return 1;
            }
            return -1;
        }
        else {
            if (!this.x60.inside(n, n2)) {
                return -1;
            }
            n -= this.x60.x;
            n2 -= this.x60.y - this.x88;
            for (int i = 0; i < this.x52; ++i) {
                if (this.x81[i].inside(n, n2)) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    private void x6() {
        this.x22 = this.x0(false);
        final int max = Math.max(this.x22.width * 2, this.x64.width);
        final int max2 = Math.max(this.x22.height * 2, this.x64.height);
        if (this.x36 != null) {
            this.x36.flush();
        }
        if (this.x0 != 0 && this.x46 != null) {
            this.x36 = this.createImage(max, max2);
            this.x36.getGraphics().drawImage(this.x46, 0, 0, null);
        }
        if (this.x46 != null) {
            this.x46.flush();
        }
        this.x46 = this.createImage(max, max2);
        final Graphics graphics = this.x46.getGraphics();
        graphics.setColor(this.x15[0]);
        graphics.fillRect(0, 0, max, max2);
        if (this.x40[0] != null && this.x39[0] != null) {
            final int width = this.x39[0].getWidth(null);
            final int height = this.x39[0].getHeight(null);
            final int n = max / width;
            for (int n2 = max2 / height, i = 0; i <= n2; ++i) {
                for (int j = 0; j <= n; ++j) {
                    graphics.drawImage(this.x39[0], j * width, i * height, null);
                }
            }
        }
        int x84 = 0;
        int n3 = 0;
        final boolean[] array = new boolean[this.x52];
        for (int k = 0; k < this.x52; ++k) {
            this.x81[k].reshape(0, 0, 0, 0);
            this.x82[k] = 0;
            array[k] = false;
        }
        for (short n4 = 0; n4 < this.x52; ++n4) {
            if (n4 == 0) {
                this.showStatus(" ");
            }
            final int n5 = x84 * this.x50 + this.x54;
            ++x84;
            final byte b = this.x5[n4];
            if (this.x11[n4] && this.x7[n4]) {
                array[b] = true;
            }
            for (byte b2 = 0; b2 <= b; ++b2) {
                int n6 = b2 * this.x49 + this.x53;
                int n7 = 0;
                if (this.x65) {
                    n6 = max - n6 - this.x20.width;
                }
                if (array[b2]) {
                    n7 = 1;
                }
                if (b2 == b - 1) {
                    n7 = (this.x3[n4] ? 3 : 2);
                }
                if (!this.x55) {
                    if (b2 == b) {
                        n7 = (this.x9[n4] + 2) * 4;
                        if (b2 != 0) {
                            graphics.drawImage(this.x74[7], n6, n5, null);
                        }
                        if (this.x11[n4]) {
                            if (this.x7[n4]) {
                                ++n7;
                                graphics.drawImage(this.x74[6], n6, n5, null);
                            }
                        }
                        else {
                            n7 += this.x4[n4];
                        }
                    }
                    graphics.drawImage(this.x74[n7], n6, n5, null);
                    if (n4 == 0) {
                        graphics.drawImage(this.x74[n7], n6, n5, null);
                    }
                    if (b2 == b - 1 && this.x11[n4]) {
                        n7 = (this.x7[n4] ? 5 : 4);
                    }
                    graphics.drawImage(this.x74[n7], n6, n5, null);
                }
            }
            final int n8 = n5 + this.x50;
            int n9 = b * this.x49 + this.x83 + this.x53;
            if (this.x65) {
                n9 = max - n9;
            }
            this.x0(graphics, n4, n9, n8, false);
            this.x81[x84 - 1].reshape(0, n8 - this.x50, this.x60.width, this.x50);
            if ((this.x82[x84 - 1] = n4) == this.folder) {
                n3 = x84 - 1;
            }
            if (this.x3[n4]) {
                array[b - 1] = false;
            }
            if (this.x11[n4] && !this.x7[n4]) {
                for (short n10 = 0; n10 < this.x10[n4]; ++n10) {
                    if (this.x11[n10 + n4]) {
                        this.x7[n10 + n4] = false;
                    }
                }
                n4 += this.x10[n4];
            }
        }
        this.x84 = x84;
        if (this.x26 && this.x84 != 0) {
            this.x85 = n3 * 100 / this.x84;
        }
        this.x26 = false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        final int x0 = this.x0(n, n2, true);
        if (x0 == 0 || x0 == 1) {
            this.x0(x0, n, n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.x56 != -1) {
            this.x56 = -1;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.x34) {
            return true;
        }
        final int x0 = this.x0(n, n2, false);
        if (x0 == this.x56) {
            return true;
        }
        this.showStatus(" ");
        if (this.x57.endsWith(".au")) {
            this.play(this.x18, this.x57);
        }
        if (this.x56 != -1) {
            this.x2(this.x56, false);
            this.x56 = -1;
        }
        if (x0 >= 0 && x0 < this.x52) {
            this.x56 = x0;
            this.showStatus(this.x8[this.x82[x0]]);
            this.x2(this.x56, true);
        }
        System.gc();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            final int x0 = this.x0(n, n2, true);
            if (x0 == 0 || x0 == 1) {
                this.x0(x0, n, n2);
            }
            if (!this.x34) {
                return true;
            }
            final int x2 = this.x0(n, n2, false);
            if (x2 > this.x52 || x2 < 0) {
                return true;
            }
            final int n3 = this.x82[x2];
            if (n3 > this.x52 || n3 < 0) {
                return true;
            }
            try {
                if (this.x14.endsWith(".au")) {
                    this.play(this.x18, this.x14);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {}
            this.x0(n3, true);
        }
        catch (Throwable t) {}
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.x62 == 0 && this.x80 && this.x46 != null) {
            this.showStatus(" ");
            this.x0(graphics, this.bounds(), this.x15[0]);
            if (this.x40[1] != null && this.x39[1] != null) {
                graphics.drawImage(this.x39[1], 0, 0, null);
            }
            this.x0(graphics, this.x64);
            this.x0();
            this.x0(graphics, 0, 0);
            this.x0(graphics, this.x60);
            graphics.drawImage(this.x46, this.x60.x - this.x86, this.x60.y - this.x88, null);
            graphics.drawImage(this.x46, this.x60.x - this.x86, this.x60.y - this.x88, null);
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.setColor(Color.black);
        try {
            int n = 1;
            do {
                graphics.drawString(this.x47[n], 3, 14 * n);
            } while (++n < 8);
        }
        catch (Exception ex) {}
        if (this.x62 == 1) {
            String s = "Error: " + this.x63;
            if (this.x48 != -1) {
                s = s + " / " + this.x48;
            }
            graphics.drawString(s, 3, 140);
            this.showStatus("Applet reports " + s);
            return;
        }
        graphics.drawString(this.x43 ? "TRIAL DELAY: 3 secs" : ("Loading: " + (12 - this.x44)), 3, 120);
    }
    
    private void x0(final Graphics graphics, final int n, final int n2) {
        this.showStatus(" ");
        final int n3 = this.x17[0].x - n;
        final int n4 = this.x17[0].y - n2;
        final int n5 = this.x17[1].x - n;
        final int n6 = this.x17[1].y - n2;
        if (this.x39[8] != null && (this.x33[0] || this.x33[1])) {
            graphics.drawImage(this.x39[8], n3, n6, null);
        }
        if (this.x33[0]) {
            final int n7 = this.x17[0].height - 2 * this.x70;
            if (this.x39[2] != null) {
                graphics.drawImage(this.x39[2], n3, n4, null);
                graphics.drawImage(this.x39[2], n3, n4, null);
            }
            final int max = Math.max(Math.min(this.x70 + this.x85 * n7 / 100, n7), this.x70);
            if (this.x39[7] != null) {
                graphics.drawImage(this.x39[7], n3, n4 + max, null);
                graphics.drawImage(this.x39[7], n3, n4 + max, null);
            }
        }
        if (this.x33[1]) {
            final int n8 = this.x17[1].width - 2 * this.x70;
            if (this.x39[3] != null) {
                graphics.drawImage(this.x39[3], n5, n6, null);
                graphics.drawImage(this.x39[3], n5, n6, null);
            }
            final int max2 = Math.max(Math.min(this.x70 + this.x35 * n8 / 100, n8), this.x70);
            if (this.x39[6] != null) {
                graphics.drawImage(this.x39[6], n5 + max2, n6, null);
                graphics.drawImage(this.x39[6], n5 + max2, n6, null);
            }
        }
    }
    
    public void run() {
        try {
            this.x5();
        }
        catch (Exception ex2) {
            try {
                this.x5();
            }
            catch (Exception ex) {
                this.x63 = 0;
                System.out.println(ex.toString());
                this.x62 = 1;
            }
        }
        if (this.x62 > 0 && (this.x23 == null || this.x63 < 10)) {
            this.x80 = false;
            if (this.x23 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x23, "_self");
            }
            this.x43 = true;
            this.stop();
        }
        System.gc();
    }
    
    public void start() {
        if (this.x51 == null) {
            (this.x51 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.x51 != null) {
            this.x51.stop();
            this.x51 = null;
        }
    }
    
    public tr5trial() {
        this.x48 = -1;
        this.x39 = new Image[11];
        this.x17 = new Rectangle[2];
        this.x33 = new boolean[4];
        this.x15 = new Color[4];
        this.x40 = new String[4];
        this.x73 = new String[10][2];
    }
    
    private int[] x0(final int[] array, final int n, final Color color) {
        final int[] array2 = new int[n];
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = this.x15[0].getRed();
        final int green2 = this.x15[0].getGreen();
        final int blue2 = this.x15[0].getBlue();
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i] >> 16 & 0xFF;
            final int n3 = array[i] >> 8 & 0xFF;
            final int n4 = array[i] & 0xFF;
            if (Math.abs(n2 - red) < 10 && Math.abs(n3 - green) < 10 && Math.abs(n4 - blue) < 10) {
                array2[i] = (0x0 | red2 << 16 | green2 << 8 | blue2);
            }
            else {
                array2[i] = (0xFF000000 | n2 << 16 | n3 << 8 | n4);
            }
        }
        return array2;
    }
    
    public void update(final Graphics graphics) {
        this.x12 = this.createImage(this.size().width, this.size().height);
        this.paint(this.x29 = this.x12.getGraphics());
        graphics.drawImage(this.x12, 0, 0, null);
        this.x12.flush();
        this.x29.dispose();
    }
}
