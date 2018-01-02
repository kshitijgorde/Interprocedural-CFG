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
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class tw5trial extends Applet implements Runnable
{
    private int x0;
    private int x1;
    private String[] x2;
    private boolean[] x3;
    private byte[] x4;
    private byte[] x5;
    private byte[] x6;
    private String[] x7;
    private boolean[] x8;
    private String[] x9;
    private byte[] x10;
    private short[] x11;
    private boolean[] x12;
    private Image x13;
    private URL x14;
    private String x15;
    private Color[] x16;
    private String x17;
    private URL x18;
    private String x19;
    private Dimension x20;
    private int x21;
    private URL x22;
    private int x23;
    private int x24;
    public int folder;
    private boolean x25;
    private int x26;
    private int x27;
    private Graphics x28;
    private Graphics x29;
    private Graphics x30;
    private Graphics x31;
    private boolean x32;
    private int x33;
    private int x34;
    private Image x35;
    private Image x36;
    private Image x37;
    private Image[] x38;
    private String[] x39;
    private int x40;
    private int x41;
    private boolean x42;
    private int x43;
    private Image x44;
    private Image x45;
    private String[] x46;
    private int x47;
    private int x48;
    private int x49;
    private Thread x50;
    private int x51;
    private int x52;
    private int x53;
    private boolean x54;
    private int x55;
    private String x56;
    private int x57;
    private int x58;
    private Rectangle x59;
    private String[] x60;
    private int x61;
    private int x62;
    private Rectangle x63;
    private Rectangle x64;
    private int x65;
    private Rectangle x66;
    private boolean x67;
    private boolean x68;
    private int x69;
    private int x70;
    private int x71;
    private int x72;
    private String[][] x73;
    private Image[] x74;
    private Color[][] x75;
    private Font[][] x76;
    private int x77;
    private int[][] x78;
    private int x79;
    private String x80;
    private boolean x81;
    private Rectangle[] x82;
    private int[] x83;
    private int x84;
    private int x85;
    private int x86;
    private int x87;
    private int x88;
    
    private int x0(final int n) {
        byte b = 0;
        for (short n2 = 0; n2 < n; ++n2) {
            b += this.x4[n2];
            if (this.x12[n2] && !this.x8[n2]) {
                n2 += this.x11[n2];
            }
        }
        return b;
    }
    
    private void x0() {
        for (int i = 0; i < this.x51; ++i) {
            int n = 0;
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.x76[this.x10[i]][0]);
            final int n2 = this.x59.width - this.x84 - this.x52 - this.x6[i] * this.x48;
            final int n3 = this.x7[i].toLowerCase().indexOf("text:") + 5;
            if (n3 > 4) {
                int n4 = this.x7[i].toLowerCase().indexOf(";", n3);
                if (n4 == -1) {
                    n4 = this.x7[i].length();
                }
                final String[] x0 = this.x0(this.x7[i].substring(n3, n4), n2, fontMetrics, !this.x12[i], this.x78[this.x10[i]][2]);
                int n5 = 0;
                do {
                    if (x0[n5].length() != 0) {
                        ++n;
                    }
                } while (++n5 < 20);
            }
            this.x4[i] = (byte)Math.max(n, 1);
        }
        this.x33 = Math.max(this.x0(true), this.x63.height);
        this.x34 = this.x0(false);
    }
    
    private void x1() {
        this.x68 = false;
        this.x67 = false;
        this.x34 = this.x0(false);
        final int abs = Math.abs(this.x69);
        if (this.x34 > this.x59.height && abs != 0) {
            this.x67 = true;
        }
        else {
            this.x86 = 0;
            this.x88 = 0;
        }
        if (abs != 0) {
            this.x68 = true;
        }
    }
    
    private void x2() {
        System.gc();
        this.repaint();
    }
    
    private void x0(final int n, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x81 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(2999L);
            }
            catch (Exception ex) {}
            this.x81 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        if (this.x12[n]) {
            final boolean b2 = this.x8[n];
            if (this.x21 >= this.x1 || !b) {
                this.x1(n, b);
            }
            this.x8[n] = !b2;
            if (b) {
                this.x3();
            }
            this.folder = n;
            this.x25 = false;
            this.x7();
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
                if (!this.x12[n] || this.x8[n]) {
                    this.getAppletContext().showDocument(url, s2);
                }
                if (this.x58 != -1) {
                    this.x5[this.x58] = 2;
                }
                this.x5[n] = 1;
                this.x58 = n;
                this.x7();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x80 = this.x80;
                this.x80 = s.substring(6, n2).trim();
                this.x32 = false;
                this.x42 = false;
                this.x6();
                if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
                    this.x80 = x80;
                    this.x32 = false;
                    this.x42 = false;
                    this.x6();
                }
                if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
                    this.x81 = false;
                    if (this.x22 == null) {
                        this.repaint();
                    }
                    else {
                        this.getAppletContext().showDocument(this.x22, "_self");
                    }
                    this.x42 = true;
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
        byte b2 = this.x6[n];
        final int[] array = new int[b2];
        final byte b3 = b2;
        --b2;
        for (int i = n; i >= 0; --i) {
            if (this.x6[i] == b2) {
                array[b2] = i;
                --b2;
            }
        }
        final byte b4 = b3;
        boolean b5 = false;
        for (int j = 0; j < this.x21; ++j) {
            if (n == this.x83[j]) {
                b5 = true;
            }
        }
        if (!b5) {
            for (int k = 0; k <= n; ++k) {
                if (this.x12[k]) {
                    this.x8[k] = true;
                }
            }
            this.x7();
            b = false;
        }
        for (int l = this.x21 - 1; l >= 0; --l) {
            final int n2 = this.x83[l];
            if (this.x12[n2] && this.x8[n2]) {
                boolean b6 = false;
                for (byte b7 = 0; b7 < b4; ++b7) {
                    if (n2 == array[b7]) {
                        b6 = true;
                    }
                }
                if (!b6) {
                    this.x8[n2] = false;
                    this.x55 = l;
                    if (b && this.x51 < 200) {
                        this.x3();
                    }
                }
            }
        }
        for (int x55 = this.x21 - 1; x55 >= 0; --x55) {
            if (this.x83[x55] == n) {
                this.x55 = x55;
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
    
    private void x3() {
        this.x32 = false;
        try {
            if (this.x0 != 0) {
                this.x4();
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
        this.x7();
        this.repaint();
        this.x32 = true;
    }
    
    private void x0(final Graphics graphics, final int n, int n2, final int n3, final boolean b, final int n4) {
        final String s = this.x7[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final byte b2 = this.x10[n];
        final byte b3 = this.x5[n];
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
        Image image = null;
        if (s3 != null) {
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                image = this.getImage(this.x14, s3);
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex2) {}
            }
            catch (Exception ex) {
                System.out.println("E28 " + ex);
            }
        }
        if (this.x54) {
            graphics.setColor(this.x75[b2][b ? 3 : b3]);
            final int n5 = this.x20.width / 2;
            final int n6 = (this.x20.height - 3) / 2;
            final int n7 = n3 - this.x49 / 2 + n6;
            if (this.x12[n]) {
                for (int i = 0; i < n6; ++i) {
                    if (this.x8[n]) {
                        graphics.drawLine(n2 + n5 - i, n7 - i, n2 + n5 + i, n7 - i);
                    }
                    else {
                        graphics.drawLine(n2 + n6 + n5 - i, n7 - n6 - i, n2 + n6 + n5 - i, n7 - n6 + i);
                    }
                }
            }
            else {
                int n8 = 0;
                do {
                    final int min = Math.min(2, (n8 < 2) ? (-n8 - 1) : (5 - n8));
                    graphics.drawLine(n2 + 7 + n8, n7 - 5 - min, n2 + 7 + n8, n7 - 5 + min);
                } while (++n8 < 5);
            }
            n2 += this.x84;
        }
        int stringWidth = 0;
        if (substring != null) {
            final Font font = this.x76[b2][b];
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
            final int descent = fontMetrics.getDescent();
            final String[] x0 = this.x0(substring, n4, fontMetrics, !this.x12[n], this.x78[b2][2]);
            final byte b5 = (byte)(b ? 3 : b3);
            n2 += this.x78[b2][0];
            graphics.setFont(font);
            for (byte b6 = 0; b6 < this.x4[n]; ++b6) {
                final int n9 = n3 + this.x49 * b6 + this.x78[b2][1] - descent;
                if (this.x79 > 0) {
                    if (this.x79 > 1) {
                        graphics.setColor(this.x75[b2][6]);
                        int n10 = 0;
                        do {
                            int n11 = 0;
                            do {
                                graphics.drawString(x0[b6], n2 + n10, n9 + n11);
                            } while (++n11 < 3);
                        } while (++n10 < 3);
                    }
                    graphics.setColor(this.x75[b2][5]);
                    graphics.drawString(x0[b6], n2 + 1, n9 + 1);
                }
                graphics.setColor(this.x75[b2][b5]);
                graphics.drawString(x0[b6], n2, n9);
                if (b6 == 0 && !this.x12[n]) {
                    n2 = n2 - this.x48 + this.x78[b2][2];
                }
            }
            stringWidth = fontMetrics.stringWidth(x0[this.x4[n] - 1]);
        }
        if (image != null) {
            n2 += this.x40;
            graphics.drawImage(image, n2 + stringWidth, n3 - this.x49 + this.x41, this);
        }
    }
    
    private synchronized void x4() {
        final int n = this.x85 * this.x49;
        final Rectangle rectangle = this.x82[this.x55];
        final int max = Math.max(0, rectangle.y + rectangle.height - this.x88);
        this.x7();
        final int n2 = this.x85 * this.x49 - n;
        final int width = this.x59.width;
        final int n3 = this.x59.height - max;
        final int x = this.x59.x;
        final int n4 = this.x59.y + max;
        Graphics graphics = this.getGraphics();
        if (n2 > 0) {
            graphics.clipRect(x, this.x59.y, width, max);
            graphics.drawImage(this.x45, x, this.x59.y - this.x88, null);
            graphics = this.getGraphics();
        }
        graphics.clipRect(x, n4, width, n3);
        final int n5 = n4 - this.x88 - max;
        if (n2 > 0) {
            final int n6 = n5 - n2;
            for (int i = 0; i <= n2; i += this.x0) {
                graphics.drawImage(this.x45, x, n6 + i, null);
            }
            graphics.drawImage(this.x45, x, n6 + n2, null);
            return;
        }
        for (int j = 0; j > n2; j -= this.x0) {
            graphics.drawImage(this.x35, x, n5 + j, null);
        }
        graphics.drawImage(this.x45, x, n5, null);
    }
    
    private void x2(final int n, final boolean b) {
        if (n >= this.x51 || n < 0) {
            return;
        }
        int n2 = 7;
        final int n3 = this.x83[n];
        byte b2 = (byte)((this.x10[n3] + 2) * 4);
        if (b) {
            b2 += 2;
        }
        if (this.x12[n3]) {
            if (this.x8[n3]) {
                ++b2;
                n2 = 6;
            }
        }
        else if (b) {
            ++b2;
        }
        else {
            b2 += this.x5[n3];
        }
        final int n4 = this.x4[n3] * this.x49;
        final int width = this.x59.width;
        final int n5 = this.x54 ? (width + this.x84) : width;
        this.x37 = this.createImage(n5, n4);
        this.x31 = this.x37.getGraphics();
        final Rectangle rectangle = this.x82[n];
        final int n6 = this.x6[n3] * this.x48;
        final int n7 = 0;
        if (this.x39[0] != null && this.x38[0] != null) {
            final int width2 = this.x38[0].getWidth(null);
            final int height = this.x38[0].getHeight(null);
            final int n8 = width / width2;
            for (int n9 = this.x45.getHeight(null) / height, i = 0; i <= n9; ++i) {
                for (int j = 0; j <= n8; ++j) {
                    this.x31.drawImage(this.x38[0], j * width2 - n6 - rectangle.x - this.x52, i * height - rectangle.y, null);
                }
            }
        }
        else {
            this.x31.setColor(this.x16[0]);
            this.x31.fillRect(0, 0, n5, n4);
        }
        if (!this.x54) {
            if (n6 != 0 || n2 != 7) {
                this.x31.drawImage(this.x74[n2], n7, 0, null);
            }
            this.x31.drawImage(this.x74[b2], n7, 0, null);
            if (this.x12[n3] && this.x8[n3] && this.x4[n3] > 1) {
                for (byte b3 = 1; b3 < this.x4[n3]; ++b3) {
                    this.x31.drawImage(this.x74[1], 0, b3 * this.x49, null);
                }
            }
        }
        final int n10 = n7 + this.x84;
        this.x0(this.x31, n3, n10, this.x49, b, width - (n10 + n6 + this.x52));
        final int n11 = rectangle.x + this.x59.x + n6 + this.x52;
        final int n12 = rectangle.y + this.x59.y - this.x88;
        final Graphics x5 = this.x5();
        x5.drawImage(this.x37, n11, n12, null);
        x5.dispose();
    }
    
    private void x1(int y) {
        final int n = y - this.x66.y;
        final int n2 = this.x34 - this.x59.height + 20;
        final int x86 = this.x86;
        final int height = this.x66.height;
        int x87;
        if (n < this.x70) {
            x87 = Math.max(0, x86 - this.x65);
        }
        else if (n > height - this.x70) {
            x87 = Math.min(100, x86 + this.x65);
        }
        else {
            x87 = Math.max(Math.min((n - this.x70) * 100 / (height - 2 * this.x70), 100), 0);
        }
        this.x88 = n2 * x87 / 100;
        this.x86 = x87;
        System.gc();
        final int x88 = this.x63.x;
        y = this.x63.y;
        final Graphics graphics = this.getGraphics();
        try {
            if (this.x39[1] != null && this.x38[1] != null) {
                this.x30.drawImage(this.x38[1], -x88, -y, null);
            }
            else {
                this.x0(this.x30, this.x63, this.x16[0]);
            }
            this.x0(this.x30, x88, y);
            this.x29.drawImage(this.x45, this.x59.x - x88, this.x59.y - y - this.x88, null);
            graphics.drawImage(this.x36, x88, y, null);
        }
        catch (OutOfMemoryError outOfMemoryError) {}
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private int x0(final boolean b) {
        byte b2 = 0;
        for (short n = 0; n < this.x51; ++n) {
            b2 += this.x4[n];
            if (!b && this.x12[n] && !this.x8[n]) {
                n += this.x11[n];
            }
        }
        return b2 * this.x49 + 10;
    }
    
    private boolean x0(final Graphics graphics) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[4];
        int n = 0;
        do {
            if (this.x39[n] != null) {
                try {
                    mediaTracker.addImage(array[n] = this.getImage(this.x14, this.x39[n]), n);
                }
                catch (Exception ex) {}
            }
        } while (++n < 4);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.x62 = 1;
            return false;
        }
        int n2 = 0;
        while (!mediaTracker.isErrorID(n2)) {
            if (++n2 >= 4) {
                ++this.x43;
                this.x2();
                if (array[2] != null) {
                    final int width = this.x20.width;
                    final int height = this.x20.height;
                    int[] x0 = new int[width * height];
                    final int width2 = array[2].getWidth(null);
                    final int height2 = array[2].getHeight(null);
                    final int n3 = (this.x72 + 2) * (width + 1) - 1;
                    final int n4 = 4 * (height + 1) - 1;
                    if (this.x22 == null && (n3 > width2 || n4 > height2)) {
                        this.x62 = 25;
                        return false;
                    }
                    final int min = Math.min(n3, width2);
                    final int min2 = Math.min(n4, height2);
                    final int[] array2 = new int[width2 * height2];
                    final int[] array3 = new int[n3 * n4];
                    for (int i = 0; i < n3 * n4; ++i) {
                        array3[i] = this.x16[1].getRGB();
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
                            this.x74[n6] = this.createImage(new MemoryImageSource(width, height, this.x0(x0, width * height, this.x16[1]), 0, width));
                        } while (++n5 < 4);
                    }
                    for (int l = 0; l < (this.x72 + 2) * 4; ++l) {
                        final int width3 = this.x20.width;
                        final int height3 = this.x20.height;
                        this.x62 = 27;
                        this.x47 = l;
                        if (this.x74[l] == null) {
                            if (this.x22 == null) {
                                return false;
                            }
                            this.x74[l] = this.createImage(width3, height3);
                            final Graphics graphics2 = this.x74[l].getGraphics();
                            graphics2.setColor(this.x16[1]);
                            graphics2.fillRect(0, 0, width3, height3);
                            graphics2.dispose();
                        }
                    }
                }
                else {
                    if (!this.x54) {
                        this.x52 -= this.x84;
                    }
                    this.x54 = true;
                }
                ++this.x43;
                this.x2();
                if (array[3] != null) {
                    final int x3 = this.x70;
                    final int x4 = this.x70;
                    final int width4 = array[3].getWidth(null);
                    final int height4 = array[3].getHeight(null);
                    final int[] array4 = new int[width4 * height4];
                    final int[] x5 = this.x0(this.x0(array[3], 0, 0, width4, height4), width4 * height4, this.x16[1]);
                    if (6 * x3 > width4 || x4 > height4) {
                        this.x62 = 26;
                        return false;
                    }
                    int n7 = 0;
                    do {
                        this.x38[n7 + 5] = this.createImage(new MemoryImageSource(x3, x4, this.x0(x5, null, n7 * (x3 + 1), 0, 0, 0, x3, x4, width4, x3, x3 * x4, false), 0, x3));
                    } while (++n7 < 6);
                    final int height5 = this.x66.height;
                    int[] x6 = new int[this.x70 * height5];
                    for (int n8 = 0; n8 < height5 / this.x70; ++n8) {
                        x6 = this.x0(x5, x6, 5 * x3 + 5, 0, 0, n8 * this.x70, x3, x4, width4, x3, x3 * height5, false);
                    }
                    this.x38[2] = this.createImage(new MemoryImageSource(this.x70, height5, this.x0(x5, this.x0(x5, x6, 0, 0, 0, 0, x3, x4, width4, x3, x3 * height5, true), 4 * x3 + 4, 0, 0, height5 - this.x70, x3, x4, width4, x3, x3 * height5, true), 0, this.x70));
                }
                else {
                    final int x7 = this.x70;
                    final int n9 = this.x70 / 2;
                    final int n10 = n9 / 2;
                    int n11 = 0;
                    do {
                        this.x38[n11 + 5] = this.createImage(x7, x7);
                        final Graphics graphics3 = this.x38[n11 + 5].getGraphics();
                        graphics3.setColor(this.x16[0]);
                        graphics3.fillRect(0, 0, x7, x7);
                        if (n11 == 2) {
                            graphics3.setColor(this.x75[1][0]);
                            for (int n12 = 0; n12 < n10; ++n12) {
                                graphics3.drawLine(n9 - n12, n10 + n12 - 1, n9 + n12, n10 + n12 - 1);
                                graphics3.drawLine(n9 - n12, n10 * 3 - n12 + 1, n9 + n12, n10 * 3 - n12 + 1);
                            }
                        }
                    } while (++n11 < 6);
                    this.x38[2] = this.createImage(this.x70, this.x66.height);
                    final Graphics graphics4 = this.x38[2].getGraphics();
                    graphics4.setColor(this.x16[0]);
                    graphics4.fillRect(0, 0, this.x70, this.x66.height);
                    graphics4.setColor(this.x75[1][0]);
                    graphics4.drawLine(n9, n9, n9, this.x66.height - n9);
                }
                ++this.x43;
                this.x2();
                int n13 = 0;
                do {
                    if (array[n13] != null) {
                        final int width5 = array[n13].getWidth(null);
                        final int height6 = array[n13].getHeight(null);
                        final int[] array5 = new int[width5 * height6];
                        this.x38[n13] = this.createImage(new MemoryImageSource(width5, height6, this.x0(array[n13], 0, 0, width5, height6), 0, width5));
                    }
                } while (++n13 < 2);
                System.gc();
                return true;
            }
        }
        this.x62 = 20 + n2;
        return false;
    }
    
    private boolean x1(final Graphics graphics) {
        final String[][] array = new String[10000][5];
        if (this.x80 != null) {
            URL url;
            try {
                url = new URL(this.x14, this.x80);
            }
            catch (Exception ex) {
                this.x62 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.x62 = 4;
                return false;
            }
            ++this.x43;
            this.x2();
            final byte[] array2 = new byte[1000000];
            try {
                try {
                    dataInputStream.readFully(array2);
                }
                catch (EOFException ex3) {}
            }
            catch (IOException ex4) {
                this.x62 = 5;
                return false;
            }
            try {
                dataInputStream.close();
            }
            catch (IOException ex5) {}
            ++this.x43;
            this.x2();
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.x51 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x47 = this.x51;
                if (this.x51 >= 9998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.x22 == null) {
                        this.x62 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.x51;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x51;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x22 == null) {
                            this.x62 = 13;
                            return false;
                        }
                        ++this.x51;
                        n = 0;
                    }
                    array[this.x51][n] = streamTokenizer.sval;
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
            this.x51 = 0;
            while (!b2) {
                if (this.x51 >= 998) {
                    b2 = true;
                }
                this.x60[0] = "entry" + this.x51;
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
                        array[this.x51][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x51;
            }
            --this.x51;
        }
        ++this.x43;
        this.x2();
        for (int i = 0; i < this.x51; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x22 == null) {
                    this.x62 = 10;
                    this.x47 = i;
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
        array[this.x51][1] = "0";
        ++this.x43;
        this.x2();
        this.x7 = new String[this.x51 + 1];
        this.x9 = new String[this.x51 + 1];
        this.x2 = new String[this.x51 + 1];
        this.x12 = new boolean[this.x51 + 1];
        this.x8 = new boolean[this.x51 + 1];
        this.x3 = new boolean[this.x51 + 1];
        this.x11 = new short[this.x51 + 1];
        this.x6 = new byte[this.x51 + 1];
        this.x10 = new byte[this.x51 + 1];
        this.x5 = new byte[this.x51 + 1];
        this.x4 = new byte[this.x51 + 1];
        this.x82 = new Rectangle[this.x51 + 1];
        this.x83 = new int[this.x51 + 1];
        for (int j = 0; j < this.x51; ++j) {
            this.x3[j] = false;
            this.x82[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x51; ++k) {
            this.x47 = k;
            this.x5[k] = 0;
            this.x7[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x22 == null) {
                    this.x62 = 10;
                    return false;
                }
                max = 1;
            }
            this.x6[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x12[k] = true;
                this.x10[k] = 0;
                this.x8[k] = (max <= this.x57);
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x51; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n4 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.x11[k] = (short)(l - k - 1);
                this.x3[n4] = true;
            }
            else {
                this.x12[k] = false;
                this.x10[k] = 1;
            }
            this.x2[k] = array[k][3];
            if (this.x2[k].toLowerCase().startsWith("start") || this.x2[k].toLowerCase().indexOf(";start") != -1) {
                this.x71 = k;
            }
            final int int2 = Integer.parseInt(array[k][2]);
            if (int2 > 0 && int2 < this.x72) {
                this.x10[k] = (byte)int2;
            }
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x9[k] = array[k][4];
            }
            else {
                this.x9[k] = " ";
            }
        }
        return true;
    }
    
    private Graphics x5() {
        final Graphics graphics = this.getGraphics();
        this.x0(graphics, this.x59);
        return graphics;
    }
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x60[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x60[n]);
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
        final String string = "dummy.copyright.textNcolour.textAcolour.textVcolour.textHcolour.textBcolour.textScolour.textNstyle.textHstyle.textfont.textsize." + "textYoffset.textXoffset.menufile.menuYoffset.menuXoffset.menuclosetrigger.menuanimspeed.menudeftarget.menualign.menustyles." + "imgtranscol.imgfile.imgwidth.imgheight.imgXoffset.imgYoffset.entryindent.entryheight." + "frameimage.framewidth.frametop.framebottom.frameleft.frameright.scrollimages.scrollwidth.textleftmargin.scrollvert.scrolljump.bgcolour.bgimage." + "audiomove.audioclick.startlevel.escapepage.textsymbolseparation.textshadow";
        this.x60 = new String[50];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
        while (stringTokenizer.hasMoreTokens()) {
            this.x60[n] = stringTokenizer.nextToken();
            ++n;
        }
        this.x17 = this.x0(1, " ");
        this.x80 = this.x0(14, null);
        this.x53 = this.x0(15, 300, -300, 0, 10);
        this.x52 = this.x0(16, 300, -300, 2, 10);
        this.x1 = this.x0(17, 50, 0, 0, 10);
        this.x0 = this.x0(18, 50, 0, 10, 10);
        this.x19 = this.x0(19, "_top");
        this.x72 = this.x0(21, 50, 2, 3, 10);
        this.x16[1] = new Color(this.x0(22, 0, 0, 12632256, 16));
        this.x39[2] = this.x0(23, null);
        final int x0 = this.x0(24, 300, 10, 13, 10);
        final int x2 = this.x0(25, 50, 10, 14, 10);
        this.x20 = new Dimension(x0, x2);
        this.x40 = this.x0(26, 100, -100, 0, 10);
        this.x41 = this.x0(27, 100, -100, 0, 10);
        this.x48 = this.x0(28, 50, 0, 12, 10);
        this.x49 = x2 + this.x0(29, 50, -50, 0, 10);
        this.x39[1] = this.x0(30, null);
        final int x3 = this.x0(31, 30, 0, 0, 10);
        this.x27 = this.x0(32, 100, 0, x3, 10);
        this.x23 = this.x0(33, 100, 0, x3, 10);
        this.x24 = this.x0(34, 30, 0, x3, 10);
        this.x26 = this.x0(35, 30, 0, x3, 10);
        if (this.x39[1] == null) {
            this.x27 = 0;
            this.x23 = 0;
            this.x24 = 0;
            this.x26 = 0;
        }
        this.x39[3] = this.x0(36, null);
        this.x70 = this.x0(37, 19, 7, 11, 10);
        this.x69 = this.x0(39, 2, -2, 1, 10);
        this.x65 = this.x0(40, 100, 10, 10, 10);
        this.x16[0] = new Color(this.x0(41, 0, 0, 0, 16));
        this.x39[0] = this.x0(42, null);
        this.x56 = this.x0(43, " ");
        this.x15 = this.x0(44, " ");
        this.x57 = this.x0(45, 49, 0, 2, 10);
        try {
            this.x18 = this.getDocumentBase();
            this.x14 = this.getCodeBase();
            this.x22 = new URL(this.x18, this.x0(46, null));
        }
        catch (Throwable t) {
            this.x22 = null;
        }
        this.x84 = this.x0(47, 50, 0, 18, 10);
        this.x79 = this.x0(48, 2, 0, 0, 10);
        int n2 = 0;
        do {
            this.x60[0] = "sub" + n2;
            this.x73[n2][0] = this.x0(0, "LINK:");
            this.x73[n2][1] = "sub" + n2 + ":";
        } while (++n2 < 10);
        this.x16[2] = Color.orange;
        this.x76 = new Font[this.x72][2];
        this.x75 = new Color[this.x72][8];
        this.x78 = new int[this.x72][3];
        int n3 = 0;
        do {
            this.x60[0] = this.x60[2 + n3];
            int n4 = Color.black.getRGB();
            if (n3 == 5) {
                n4 = Color.gray.getRGB();
            }
            if (n3 == 4) {
                n4 = this.x16[0].getRGB();
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
                this.x60[0] = this.x60[2 + n3] + i;
                this.x75[i][n3] = new Color(this.x0(0, 0, 0, x4, 16));
                if (n3 == 5) {
                    this.x75[i][6] = new Color((this.x75[i][5].getRed() + this.x16[0].getRed()) / 2, (this.x75[i][5].getGreen() + this.x16[0].getGreen()) / 2, (this.x75[i][5].getBlue() + this.x16[0].getBlue()) / 2);
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
            this.x60[0] = this.x60[10] + j;
            final String x9 = this.x0(0, x5);
            this.x60[0] = this.x60[11] + j;
            final int x10 = this.x0(0, 30, 6, x6, 10);
            int n5 = 0;
            do {
                this.x60[0] = this.x60[8] + j;
                this.x76[j][n5] = new Font(x9, this.x0(0, 3, 0, (j == 0) ? 1 : array[n5], 10), x10);
                this.x60[0] = this.x60[12 + n5] + j;
                this.x78[j][1 - n5] = this.x0(0, 50, -50, (n5 == 0) ? x8 : x7, 10);
            } while (++n5 < 2);
            this.x60[0] = this.x60[38] + j;
            this.x78[j][2] = this.x0(0, 20, -20, 0, 10);
        }
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        int x11 = this.x24;
        final int x12 = this.x27;
        int n6 = width - this.x24 - this.x26;
        final int n7 = height - this.x27 - this.x23;
        this.x63 = new Rectangle(x11, x12, n6, n7);
        this.x36 = this.createImage(n6, n7);
        this.x30 = this.x36.getGraphics();
        int x13 = this.x24;
        final int x14 = this.x27;
        final int x15 = this.x70;
        final int n8 = n7;
        if (this.x69 < 0) {
            x13 = width - this.x26 - this.x70;
        }
        if (this.x69 > 0) {
            x11 += x15;
        }
        if (this.x69 != 0) {
            n6 -= x15;
        }
        this.x66 = new Rectangle(x13, x14, x15, n8);
        this.x59 = new Rectangle(x11, x12, n6, n7);
        (this.x29 = this.x36.getGraphics()).clipRect(x11 - this.x63.x, x12 - this.x63.y, n6, n7);
        this.x74 = new Image[(this.x72 + 2) * 4];
        System.gc();
    }
    
    private void x6() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.x42) {
            final int n = 8;
            (this.x46 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x46[1] = "TRIAL VERSION ONLY";
            this.x46[2] = "This copyright display";
            this.x46[3] = "is not shown in the";
            this.x46[4] = "purchasable version.";
            this.x46[5] = "Navigation Applet";
            this.x46[6] = "© Image Intelligence Ltd.";
            this.x46[7] = "www.imint.com";
            for (int i = 1; i < n; ++i) {
                System.out.println(this.x46[i]);
            }
            this.x0(graphics, this.bounds(), this.x16[0]);
            this.x81 = false;
            this.x86 = 0;
            char c = '\0';
            this.x55 = 0;
            this.folder = 0;
            this.x87 = 0;
            this.x88 = 0;
            this.x58 = -1;
            this.x61 = ((this.x22 == null) ? 2 : 3);
            this.x43 = 1;
            this.x2();
            this.x71 = -1;
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.x46[j];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.x17.equalsIgnoreCase(this.x46[0])) {
                this.x62 = 9;
                this.x61 = 1;
                return;
            }
            if (c != 1499310) {
                this.x62 = 30;
                this.x61 = 1;
                return;
            }
            final String[] array2;
            final String[] array = array2 = new String[] { this.x14.getProtocol().toString().toLowerCase(), this.x14.getHost().toString().toLowerCase(), null, null };
            final int n2 = 1;
            array2[n2] += this.x14.getFile().toString().toLowerCase();
            try {
                array[2] = InetAddress.getByName(this.x14.getHost()).toString();
                array[3] = array[2].substring(array[2].indexOf("/") + 1);
            }
            catch (Exception ex4) {
                array[2] = "unavailable";
                array[3] = "...";
            }
            System.out.println(array[1]);
            System.out.println(array[2]);
            System.out.println(array[3]);
            this.x43 = 2;
            this.x2();
            try {
                if (!this.x1(graphics) && !this.x1(graphics)) {
                    this.x61 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x62 = 6;
                this.x61 = 1;
                return;
            }
            this.x43 = 8;
            this.x2();
            this.x0();
            try {
                if (!this.x0(graphics) && !this.x0(graphics)) {
                    this.x61 = 1;
                    return;
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.x62 = 7;
                this.x61 = 1;
                return;
            }
            this.x43 = 11;
            this.x2();
            this.x81 = true;
            try {
                this.x7();
                if (this.x45 == null) {
                    this.x7();
                }
                if (this.x45 == null) {
                    this.x62 = 8;
                    this.x61 = 1;
                    return;
                }
                if (this.x85 > 50) {
                    this.x1(0, false);
                    this.x7();
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3);
                this.x62 = 8;
                this.x61 = 1;
                return;
            }
            this.x43 = 12;
            this.x2();
            this.x42 = true;
            this.x32 = true;
            this.x61 = 0;
            if (this.x71 >= 0 && this.x71 < this.x51) {
                this.x0(this.x71, false);
            }
        }
        if (this.x81 && this.x45 != null) {
            this.x0(graphics, this.bounds(), this.x16[0]);
            if (this.x39[1] != null && this.x38[1] != null) {
                graphics.drawImage(this.x38[1], 0, 0, null);
            }
            this.x0(graphics, this.x63);
            this.x1();
            this.x0(graphics, 0, 0);
            this.x0(graphics, this.x59);
            graphics.drawImage(this.x45, this.x59.x, this.x59.y - this.x88, null);
            this.repaint();
        }
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
            if (this.x66.inside(n, n2) && this.x67) {
                return 0;
            }
            return -1;
        }
        else {
            if (!this.x59.inside(n, n2)) {
                return -1;
            }
            n -= this.x59.x;
            n2 -= this.x59.y - this.x88;
            for (int i = 0; i < this.x51; ++i) {
                if (this.x82[i].inside(n, n2)) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    private void x7() {
        final int width = this.x59.width;
        final int n = Math.max(this.x59.height, this.x0(false)) + this.x59.height;
        if (this.x35 != null) {
            this.x35.flush();
        }
        if (this.x0 != 0 && this.x45 != null) {
            this.x35 = this.createImage(width, n);
            this.x35.getGraphics().drawImage(this.x45, 0, 0, null);
        }
        if (this.x45 != null) {
            this.x45.flush();
        }
        this.x45 = this.createImage(width, n);
        final Graphics graphics = this.x45.getGraphics();
        graphics.setColor(this.x16[0]);
        graphics.fillRect(0, 0, width, n);
        if (this.x39[0] != null && this.x38[0] != null) {
            final int width2 = this.x38[0].getWidth(null);
            final int height = this.x38[0].getHeight(null);
            final int n2 = width / width2;
            for (int n3 = n / height, i = 0; i <= n3; ++i) {
                for (int j = 0; j <= n2; ++j) {
                    graphics.drawImage(this.x38[0], j * width2, i * height, null);
                }
            }
        }
        int x21 = 0;
        int n4 = 0;
        final boolean[] array = new boolean[this.x51];
        for (int k = 0; k < this.x51; ++k) {
            this.x82[k].reshape(0, 0, 0, 0);
            this.x83[k] = 0;
            array[k] = false;
        }
        for (short n5 = 0; n5 < this.x51; ++n5) {
            if (n5 == 0) {
                this.showStatus(" ");
            }
            this.x85 = this.x0(n5) + 1;
            final int n6 = this.x0(n5) * this.x49 + this.x53;
            ++x21;
            final byte b = this.x6[n5];
            if (this.x12[n5] && this.x8[n5]) {
                array[b] = true;
            }
            for (byte b2 = 0; b2 <= b; ++b2) {
                final int n7 = b2 * this.x48 + this.x52;
                int n8 = 0;
                if (array[b2]) {
                    n8 = 1;
                }
                if (b2 == b - 1) {
                    n8 = (this.x3[n5] ? 3 : 2);
                }
                if (!this.x54) {
                    if (b2 == b) {
                        n8 = (this.x10[n5] + 2) * 4;
                        if (b2 != 0) {
                            graphics.drawImage(this.x74[7], n7, n6, null);
                        }
                        if (this.x12[n5]) {
                            if (this.x8[n5]) {
                                ++n8;
                                graphics.drawImage(this.x74[6], n7, n6, null);
                            }
                        }
                        else {
                            n8 += this.x5[n5];
                        }
                    }
                    try {
                        graphics.drawImage(this.x74[n8], n7, n6, null);
                        if (n5 == 0) {
                            graphics.drawImage(this.x74[n8], n7, n6, null);
                        }
                        if (b2 == b - 1 && this.x12[n5]) {
                            n8 = (this.x8[n5] ? 5 : 4);
                        }
                        graphics.drawImage(this.x74[n8], n7, n6, null);
                    }
                    catch (Exception ex) {}
                    if ((n8 == 1 || n8 == 2 || ((n8 == 4 || n8 == 5) && !this.x3[n5]) || (this.x8[n5] && b2 == b)) && this.x4[n5] > 1) {
                        for (byte b3 = 1; b3 < this.x4[n5]; ++b3) {
                            graphics.drawImage(this.x74[1], n7, n6 + b3 * this.x49, null);
                        }
                    }
                }
            }
            final int n9 = b * this.x48 + this.x84 + this.x52;
            this.x0(graphics, n5, n9, n6 + this.x49, false, this.x59.width - n9);
            this.x82[x21 - 1].reshape(0, n6, this.x59.width, this.x4[n5] * this.x49);
            if ((this.x83[x21 - 1] = n5) == this.folder) {
                n4 = x21 - 1;
            }
            if (this.x3[n5]) {
                array[b - 1] = false;
            }
            if (this.x12[n5] && !this.x8[n5]) {
                for (short n10 = 0; n10 < this.x11[n5]; ++n10) {
                    if (this.x12[n10 + n5]) {
                        this.x8[n10 + n5] = false;
                    }
                }
                n5 += this.x11[n5];
            }
        }
        this.x21 = x21;
        if (this.x25 && this.x85 != 0) {
            this.x86 = n4 * 100 / this.x85;
        }
        this.x25 = false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.x0(n, n2, true) == 0) {
            this.x1(n2);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.x55 != -1) {
            this.x55 = -1;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.x32) {
            return true;
        }
        final int x0 = this.x0(n, n2, false);
        if (x0 == this.x55) {
            return true;
        }
        this.showStatus(" ");
        if (this.x56.endsWith(".au")) {
            this.play(this.x18, this.x56);
        }
        if (this.x55 != -1) {
            this.x2(this.x55, false);
            this.x55 = -1;
        }
        if (x0 >= 0 && x0 < this.x51) {
            this.x55 = x0;
            this.showStatus(this.x9[this.x83[x0]]);
            this.x2(this.x55, true);
        }
        System.gc();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (this.x0(n, n2, true) == 0) {
                this.x1(n2);
            }
            if (!this.x32) {
                return true;
            }
            final int x0 = this.x0(n, n2, false);
            if (x0 > this.x51 || x0 < 0) {
                return true;
            }
            final int n3 = this.x83[x0];
            if (n3 > this.x51 || n3 < 0) {
                return true;
            }
            try {
                if (this.x15.endsWith(".au")) {
                    this.play(this.x18, this.x15);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {}
            this.x0(n3, true);
        }
        catch (Throwable t) {}
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.x61 == 0 && this.x81 && this.x45 != null) {
            this.showStatus(" ");
            this.x0(graphics, this.bounds(), this.x16[0]);
            if (this.x39[1] != null && this.x38[1] != null) {
                graphics.drawImage(this.x38[1], 0, 0, null);
            }
            this.x0(graphics, this.x63);
            this.x1();
            this.x0(graphics, 0, 0);
            this.x0(graphics, this.x59);
            graphics.drawImage(this.x45, this.x59.x, this.x59.y - this.x88, null);
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.setColor(Color.black);
        try {
            int n = 1;
            do {
                graphics.drawString(this.x46[n], 3, 14 * n);
            } while (++n < 8);
        }
        catch (Exception ex) {}
        if (this.x61 == 1) {
            String s = "Error: " + this.x62;
            if (this.x47 != -1) {
                s = s + " / " + this.x47;
            }
            graphics.drawString(s, 3, 140);
            this.showStatus("Applet reports " + s);
            return;
        }
        graphics.drawString(this.x42 ? "TRIAL DELAY: 3 secs" : ("Loading: " + (12 - this.x43)), 3, 120);
    }
    
    private void x0(final Graphics graphics, final int n, final int n2) {
        this.showStatus(" ");
        final int n3 = this.x66.x - n;
        final int n4 = this.x66.y - n2;
        if (this.x68) {
            if (this.x67 || this.x69 == -2 || this.x69 == 2) {
                final int n5 = this.x66.height - 2 * this.x70;
                if (this.x38[2] != null) {
                    graphics.drawImage(this.x38[2], n3, n4, null);
                    graphics.drawImage(this.x38[2], n3, n4, null);
                }
                final int max = Math.max(Math.min(this.x70 + this.x86 * n5 / 100, n5), this.x70);
                if (this.x38[7] != null) {
                    graphics.drawImage(this.x38[7], n3, n4 + max, null);
                    graphics.drawImage(this.x38[7], n3, n4 + max, null);
                }
            }
            else if (this.x39[1] == null || this.x38[1] == null) {
                graphics.setColor(this.x16[0]);
                graphics.fillRect(n3, n4, this.x66.width, this.x66.height);
            }
        }
    }
    
    public void run() {
        try {
            this.x6();
        }
        catch (Exception ex2) {
            try {
                this.x6();
            }
            catch (Exception ex) {
                this.x62 = 0;
                System.out.println(ex.toString());
                this.x61 = 1;
            }
        }
        if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
            this.x81 = false;
            if (this.x22 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x22, "_self");
            }
            this.x42 = true;
            this.stop();
        }
        System.gc();
    }
    
    public void start() {
        if (this.x50 == null) {
            (this.x50 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.x50 != null) {
            this.x50.stop();
            this.x50 = null;
        }
    }
    
    private int[] x0(final int[] array, final int n, final Color color) {
        final int[] array2 = new int[n];
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = this.x16[0].getRed();
        final int green2 = this.x16[0].getGreen();
        final int blue2 = this.x16[0].getBlue();
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
    
    public tw5trial() {
        this.x47 = -1;
        this.x38 = new Image[11];
        this.x16 = new Color[4];
        this.x58 = -1;
        this.x39 = new String[4];
        this.x77 = 2;
        this.x73 = new String[10][2];
    }
    
    public void update(final Graphics graphics) {
        try {
            this.x13 = this.createImage(this.size().width, this.size().height);
            this.paint(this.x28 = this.x13.getGraphics());
            graphics.drawImage(this.x13, 0, 0, null);
            this.x13.flush();
            this.x28.dispose();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
    }
    
    private String[] x0(final String s, int n, final FontMetrics fontMetrics, final boolean b, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final int n3 = 20;
        final String[] array = new String[n3];
        String s2 = "";
        String s3 = "";
        int n4 = 0;
        if (this.x54) {
            n -= this.x84;
        }
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
            if (fontMetrics.stringWidth(string) > n - 2 * this.x77) {
                if (n4 == 0 && b) {
                    n += this.x48 - n2;
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
