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
import java.awt.Polygon;
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

public final class sb5trial extends Applet implements Runnable
{
    private int x0;
    private int x1;
    private String[] x2;
    private boolean[] x3;
    private boolean[] x4;
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
    private String x25;
    private int x26;
    private int x27;
    public int folder;
    private boolean x28;
    private int x29;
    private int x30;
    private int x31;
    private Graphics x32;
    private Graphics x33;
    private Graphics x34;
    private Graphics x35;
    private boolean[] x36;
    private boolean x37;
    private Image x38;
    private Image x39;
    private Image x40;
    private Image[] x41;
    private String[] x42;
    private int x43;
    private int x44;
    private boolean x45;
    private int x46;
    private Image x47;
    private Image x48;
    private String x49;
    private int x50;
    private String[] x51;
    private int x52;
    private int x53;
    private Graphics x54;
    private Thread x55;
    private boolean x56;
    private int x57;
    private int x58;
    private int x59;
    private boolean x60;
    private int x61;
    private String x62;
    private Rectangle x63;
    private String[] x64;
    private int x65;
    private int x66;
    private Rectangle x67;
    private Rectangle x68;
    private int x69;
    private int x70;
    private int x71;
    private int x72;
    private int x73;
    private String[][] x74;
    private Image[] x75;
    private Color[][] x76;
    private Font[][] x77;
    private byte[][] x78;
    private boolean x79;
    private int x80;
    private int x81;
    private int x82;
    private String x83;
    private boolean x84;
    private Rectangle[] x85;
    private int[] x86;
    private int x87;
    private int x88;
    private int x89;
    
    private void x0() {
        final int n = 0;
        int n2 = 0;
        do {
            this.x36[n2] = false;
        } while (++n2 < 4);
        this.x22 = this.x0(false);
        final int height = this.x22.height;
        final int height2 = this.x63.height;
        final int abs = Math.abs(this.x70);
        if (height > height2 - n && this.x70 != 0) {
            this.x36[2] = true;
            final int x71 = this.x71;
        }
        if ((height > height2 - 0 && this.x70 != 0) || abs == 2) {
            this.x36[0] = true;
            final int x72 = this.x71;
        }
        if (!this.x36[2]) {
            this.x88 = 0;
            this.x89 = 0;
        }
        (this.x33 = this.x39.getGraphics()).clipRect(this.x63.x - this.x67.x, this.x63.y - this.x67.y, this.x63.width, this.x63.height);
    }
    
    private void x1() {
        System.gc();
        this.repaint();
    }
    
    private void x0(final int folder, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x84 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(2999L);
            }
            catch (Exception ex) {}
            this.x84 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        if (this.x11[folder]) {
            if (this.x87 >= this.x1) {
                final boolean b2 = this.x7[folder];
                this.x1(folder, b);
                if (!b2) {
                    this.x7[folder] = true;
                    if (b) {
                        this.x2();
                    }
                }
            }
            else {
                this.x7[folder] = !this.x7[folder];
                if (b) {
                    this.x2();
                }
            }
            this.folder = folder;
            this.x28 = false;
            this.x6();
        }
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x2[folder], ";");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken().trim();
            int n = s.length();
            if (s.toLowerCase().startsWith("audio")) {
                try {
                    this.play(this.x18, s.substring(6, n).trim());
                }
                catch (OutOfMemoryError outOfMemoryError) {}
            }
            if (s.toLowerCase().startsWith("sub")) {
                int n2 = 0;
                do {
                    if (s.toLowerCase().startsWith(this.x74[n2][1])) {
                        s = this.x74[n2][0] + s.substring(5, n);
                        n = s.length();
                    }
                } while (++n2 < 10);
            }
            if (s.toLowerCase().startsWith("link")) {
                int index = s.indexOf(",");
                String s2;
                if (index == -1 || n - index < 2) {
                    s2 = this.x19;
                    index = n;
                }
                else {
                    s2 = s.substring(index + 1, n).trim();
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
                if (!this.x11[folder] || this.x7[folder]) {
                    this.getAppletContext().showDocument(url, s2);
                }
                this.x6();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x83 = this.x83;
                final int folder2 = this.folder;
                if (this.x83.equalsIgnoreCase(this.x25)) {
                    this.x26 = this.folder;
                }
                this.x83 = s.substring(6, n).trim();
                this.x37 = false;
                this.x45 = false;
                this.x5();
                if (this.x65 > 0 && (this.x23 == null || this.x66 < 10)) {
                    this.x83 = x83;
                    this.x37 = false;
                    this.x45 = false;
                    this.x5();
                }
                if (this.x65 == 0 && this.x83.equalsIgnoreCase(this.x25)) {
                    this.clik(this.x26);
                }
                else if (this.x65 == 0 && this.x83.equalsIgnoreCase(this.x49)) {
                    this.clik(this.x50);
                }
                this.x49 = x83;
                this.x50 = folder2;
                if (this.x65 > 0 && (this.x23 == null || this.x66 < 10)) {
                    this.x84 = false;
                    if (this.x23 == null) {
                        this.repaint();
                    }
                    else {
                        this.getAppletContext().showDocument(this.x23, "_self");
                    }
                    this.x45 = true;
                    this.stop();
                }
            }
            if (s.toLowerCase().startsWith("script")) {
                try {
                    JSObject.getWindow((Applet)this).eval(s.substring(7, n));
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
    
    private void x1(final int n, final boolean b) {
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
        for (int j = this.x87 - 1; j >= 0; --j) {
            final int n2 = this.x86[j];
            if (this.x11[n2] && this.x7[n2]) {
                boolean b5 = false;
                for (byte b6 = 0; b6 < b4; ++b6) {
                    if (n2 == array[b6]) {
                        b5 = true;
                    }
                }
                if (!b5) {
                    this.x7[n2] = false;
                    this.x61 = j;
                    if (b) {
                        this.x2();
                    }
                }
            }
        }
        for (int k = this.x87 - 1; k >= 0; --k) {
            if (this.x86[k] == n) {
                this.x61 = k;
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
        try {
            if (this.x0 != 0) {
                this.x3();
            }
            else {
                this.x6();
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
        this.repaint();
        this.x37 = true;
    }
    
    private void x0(final Graphics graphics, final int n, final Rectangle rectangle, final boolean b) {
        final String s = this.x6[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final byte b2 = this.x9[n];
        final int n2 = this.x4[n] ? 1 : 0;
        int x = rectangle.x;
        int y = rectangle.y;
        if (b) {
            y += this.x82;
            x += this.x81;
        }
        final byte b3 = this.x78[b2][2];
        this.x0(graphics, rectangle);
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
        if (n2 == 1) {
            final int index5 = s.toLowerCase().indexOf("img2:");
            if (index5 != -1) {
                int index6 = s.indexOf(";", index5);
                if (index6 == -1) {
                    index6 = length;
                }
                s3 = s.substring(index5 + 5, index6);
            }
        }
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
            }
            catch (Exception ex) {
                System.out.println("E28 " + ex);
            }
        }
        if (this.x60) {
            graphics.setColor(this.x76[b2][2]);
            if (n2 == 1) {
                graphics.setColor(this.x76[b2][2].brighter());
            }
            final int x2 = rectangle.x;
            final int y2 = rectangle.y;
            int width = this.x20.width;
            int height = this.x20.height;
            int n3 = y2 + (rectangle.height - height) / 2;
            int n4 = x2 + (rectangle.width - width) / 2;
            graphics.fill3DRect(n4, n3, width, height, !b);
            if (rectangle.height > 24) {
                n4 += 3;
                n3 += 3;
                width -= 6;
                height -= 6;
                graphics.fill3DRect(n4, n3, width, height, b);
                ++n4;
                ++n3;
                width -= 2;
                height -= 2;
                graphics.fill3DRect(n4, n3, width, height, !b);
            }
            if (b3 == 2) {
                int n5 = 2;
                if (this.x11[n]) {
                    n5 = (this.x7[n] ? 0 : 1);
                }
                graphics.setColor(this.x76[b2][n2]);
                final int n6 = 14;
                final int n7 = 14;
                final byte b4 = (byte)(x + this.x78[b2][3] + this.x78[b2][0]);
                final int n8 = y + (rectangle.height - n7) / 2 + this.x78[b2][1];
                if (n5 == 1 || n5 == 0) {
                    final Polygon polygon = new Polygon();
                    if (n5 == 1) {
                        polygon.addPoint(b4 + n6 / 2, n8 + 1);
                    }
                    else {
                        polygon.addPoint(b4 + 2, n8 + n7 / 2 - 1);
                    }
                    polygon.addPoint(b4 + n6 - 2, n8 + n7 / 2 - 1);
                    polygon.addPoint(b4 + n6 / 2, n8 + n7 - 3);
                    if (b2 == 0) {
                        graphics.fillPolygon(polygon);
                    }
                    else {
                        graphics.drawPolygon(polygon);
                    }
                }
                if (n5 == 2) {
                    if (b2 == 2) {
                        graphics.fillOval(b4 + n6 / 2 + 1, n8 + n7 / 2 - 2, 5, 5);
                    }
                    else if (b2 == 5) {
                        graphics.drawOval(b4 + n6 / 2 + 1, n8 + n7 / 2 - 2, 5, 5);
                    }
                }
            }
        }
        int n9 = 0;
        if (substring != null) {
            final byte b5 = (byte)(this.x78[b2][3] + ((this.x60 && b3 == 2) ? 16 : 0));
            final Font font = this.x77[b2][n2];
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
            final String[] x3 = this.x0(substring, rectangle.width - b5 * 2, fontMetrics);
            int n10 = 0;
            int n11 = 0;
            do {
                if (x3[n11].length() != 0) {
                    ++n10;
                }
            } while (++n11 < 4);
            x += this.x78[b2][0];
            final byte b6 = this.x78[b2][4];
            y += rectangle.height / 2 - n10 * b6 / 2 + this.x78[b2][1] + fontMetrics.getAscent() - fontMetrics.getDescent();
            int n12 = 0;
            for (byte b7 = 0; b7 < n10; ++b7) {
                if (b3 == 0) {
                    n12 = x + rectangle.width / 2 - fontMetrics.stringWidth(x3[b7]) / 2;
                }
                if (b3 == 2) {
                    n12 = x + b5;
                }
                if (b3 == 1) {
                    n12 = x + rectangle.width - fontMetrics.stringWidth(x3[b7]) - b5;
                }
                final byte b8 = (byte)(y + b7 * b6);
                if (this.x80 > 0) {
                    if (this.x80 > 1) {
                        graphics.setColor(this.x76[b2][5]);
                        int n13 = 0;
                        do {
                            byte b9 = 0;
                            do {
                                graphics.drawString(x3[b7], n12 + n13, b8 + b9);
                            } while (++b9 < 3);
                        } while (++n13 < 3);
                    }
                    graphics.setColor(this.x76[b2][3]);
                    graphics.drawString(x3[b7], n12 + 1, b8 + 1);
                }
                graphics.setColor(this.x76[b2][n2]);
                graphics.drawString(x3[b7], n12, b8);
            }
            n9 = n12 + fontMetrics.stringWidth(x3[n10 - 1]);
        }
        if (image != null && (b3 == 2 || substring == null)) {
            graphics.drawImage(image, x + n9 + this.x43, y + (rectangle.height - image.getHeight(null)) / 2 + this.x44, this);
        }
    }
    
    private synchronized void x3() {
        this.x37 = false;
        Graphics graphics = this.getGraphics();
        final int n = this.x87 * this.x53;
        final Rectangle rectangle = this.x85[this.x61];
        final int max = Math.max(0, rectangle.y + rectangle.height - this.x89);
        this.x6();
        final int n2 = this.x87 * this.x53 - n;
        final int width = this.x63.width;
        final int n3 = this.x63.height - max;
        final int x = this.x63.x;
        final int n4 = this.x63.y + max;
        if (n2 > 0) {
            graphics.clipRect(x, this.x63.y, width, max);
            graphics.drawImage(this.x48, x, this.x63.y - this.x89, null);
            graphics = this.getGraphics();
        }
        graphics.clipRect(x, n4, width, n3);
        final int n5 = n4 - this.x89 - max;
        if (n2 > 0) {
            final int n6 = n5 - n2;
            for (int i = 0; i < n2; i += this.x0) {
                graphics.drawImage(this.x48, x, n6 + i, null);
            }
        }
        else {
            for (int j = 0; j > n2; j -= this.x0) {
                graphics.drawImage(this.x38, x, n5 + j, null);
            }
        }
        System.gc();
    }
    
    private void x0(final int n) {
        final int x61 = this.x61;
        if (x61 > this.x57 || x61 < 0) {
            return;
        }
        final int n2 = this.x86[x61];
        this.x4[n2] = (n == 2);
        final Rectangle rectangle = this.x85[x61];
        if (this.x42[0] != null && this.x41[0] != null) {
            this.x35.drawImage(this.x41[0], -rectangle.x, -rectangle.y, null);
        }
        else {
            this.x35.setColor(this.x15[0]);
            this.x0(this.x35, this.x68, this.x15[0]);
        }
        if (!this.x60) {
            int n3 = this.x9[n2] * 3 + n;
            if (this.x11[n2] && this.x7[n2]) {
                n3 += 3;
            }
            this.x35.drawImage(this.x75[n3], this.x58, this.x59, null);
            this.x35.drawImage(this.x75[n3], this.x58, this.x59, null);
        }
        this.x0(this.x35, n2, this.x68, n == 1);
        final Graphics x62 = this.x4();
        x62.drawImage(this.x40, rectangle.x + this.x63.x, rectangle.y + this.x63.y - this.x89, null);
        x62.dispose();
    }
    
    private void x1(int y) {
        final int n = y - this.x17[0].y;
        final int n2 = this.x22.height - this.x63.height + 20;
        final int x88 = this.x88;
        final int height = this.x17[0].height;
        int x89;
        if (n < this.x71) {
            x89 = Math.max(0, x88 - this.x69);
        }
        else if (n > height - this.x71) {
            x89 = Math.min(100, x88 + this.x69);
        }
        else {
            x89 = Math.max(Math.min((n - this.x71) * 100 / (height - 2 * this.x71), 100), 0);
        }
        this.x89 = n2 * x89 / 100;
        this.x88 = x89;
        System.gc();
        final int x90 = this.x67.x;
        y = this.x67.y;
        final Graphics graphics = this.getGraphics();
        try {
            if (this.x42[1] != null && this.x41[1] != null) {
                this.x34.drawImage(this.x41[1], -x90, -y, null);
            }
            else {
                this.x0(this.x34, this.x67, this.x15[0]);
            }
            this.x0(this.x34, x90, y);
            this.x33.drawImage(this.x48, this.x63.x - x90, this.x63.y - y - this.x89, null);
            graphics.drawImage(this.x39, x90, y, null);
        }
        catch (OutOfMemoryError outOfMemoryError) {}
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private Dimension x0(final boolean b) {
        int n = 0;
        for (short n2 = 0; n2 < this.x57; ++n2) {
            ++n;
            if (!b && this.x11[n2] && !this.x7[n2]) {
                n2 += this.x10[n2];
            }
        }
        final int width = this.x63.width;
        int n3 = n * this.x53;
        if (b) {
            n3 = this.x57 * this.x53;
        }
        return new Dimension(width, n3);
    }
    
    private boolean x0(final Graphics graphics) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[5];
        int n = 0;
        do {
            if (this.x42[n] != null) {
                try {
                    mediaTracker.addImage(array[n] = this.getImage(this.x13, this.x42[n]), n);
                }
                catch (Exception ex) {}
            }
        } while (++n < 5);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.x66 = 1;
            return false;
        }
        int n2 = 0;
        while (!mediaTracker.isErrorID(n2)) {
            if (++n2 >= 5) {
                ++this.x46;
                this.x1();
                if (array[2] != null || array[4] != null) {
                    final int width = this.x20.width;
                    final int height = this.x20.height;
                    final int[][] array2 = new int[3][width * height];
                    if (array[4] != null) {
                        final int width2 = array[4].getWidth(null);
                        final int height2 = array[4].getHeight(null);
                        if (width2 != width && height2 != 3 * height + 2) {
                            this.x66 = 11;
                            return false;
                        }
                        final int[] array3 = new int[width2 * height2];
                        final int[] x0 = this.x0(this.x0(array[4], 0, 0, width2, height2), width2 * height2, this.x15[1]);
                        int n3 = 0;
                        do {
                            array2[n3] = this.x0(x0, null, 0, n3 * (height + 1), 0, 0, width, height, width2, width, width * height, false);
                        } while (++n3 < 3);
                    }
                    else {
                        int n4 = 0;
                        do {
                            for (int i = 0; i < width * height; ++i) {
                                array2[n4][i] = 0;
                            }
                        } while (++n4 < 3);
                    }
                    int width3;
                    int[] x2;
                    if (this.x42[2] == null) {
                        width3 = this.x73 * (width + 1) - 1;
                        final int n5 = 3 * (height + 1) - 1;
                        x2 = new int[width3 * n5];
                        for (int j = 0; j < width3 * n5; ++j) {
                            x2[j] = 0;
                        }
                    }
                    else {
                        width3 = array[2].getWidth(null);
                        final int height3 = array[2].getHeight(null);
                        final int[] array4 = new int[width3 * height3];
                        x2 = this.x0(this.x0(array[2], 0, 0, width3, height3), width3 * height3, this.x15[1]);
                        if (this.x23 == null && (this.x73 * (width + 1) > width3 + 1 || (height + 1) * 3 > height3 + 1)) {
                            this.x66 = 25;
                            return false;
                        }
                    }
                    for (int k = 0; k < this.x73; ++k) {
                        final int n6 = k * (width + 1);
                        int n7 = 0;
                        do {
                            this.x75[k * 3 + n7] = this.createImage(new MemoryImageSource(width, height, this.x0(x2, array2[n7], n6, n7 * (height + 1), 0, 0, width, height, width3, width, width * height, true), 0, width));
                        } while (++n7 < 3);
                    }
                    for (int l = 0; l < this.x73 * 3; ++l) {
                        final int width4 = this.x20.width;
                        final int height4 = this.x20.height;
                        this.x66 = 27;
                        this.x52 = l;
                        if (this.x75[l] == null) {
                            if (this.x23 == null) {
                                return false;
                            }
                            this.x75[l] = this.createImage(width4, height4);
                            final Graphics graphics2 = this.x75[l].getGraphics();
                            graphics2.setColor(this.x15[1]);
                            graphics2.fillRect(0, 0, width4, height4);
                            graphics2.dispose();
                        }
                    }
                }
                else {
                    this.x60 = true;
                    this.x20.width = Math.min(this.x20.width, this.x63.width);
                }
                ++this.x46;
                this.x1();
                if (array[3] != null) {
                    final int x3 = this.x71;
                    final int x4 = this.x71;
                    final int width5 = array[3].getWidth(null);
                    final int height5 = array[3].getHeight(null);
                    final int[] array5 = new int[width5 * height5];
                    final int[] x5 = this.x0(this.x0(array[3], 0, 0, width5, height5), width5 * height5, this.x15[1]);
                    if (6 * x3 > width5 || x4 > height5) {
                        this.x66 = 12;
                        return false;
                    }
                    int n8 = 0;
                    do {
                        this.x41[n8 + 5] = this.createImage(new MemoryImageSource(x3, x4, this.x0(x5, null, n8 * (x3 + 1), 0, 0, 0, x3, x4, width5, x3, x3 * x4, false), 0, x3));
                    } while (++n8 < 6);
                    final int height6 = this.x17[0].height;
                    int[] x6 = new int[this.x71 * height6];
                    for (int n9 = 0; n9 < height6 / this.x71; ++n9) {
                        x6 = this.x0(x5, x6, 5 * x3 + 5, 0, 0, n9 * this.x71, x3, x4, width5, x3, x3 * height6, false);
                    }
                    this.x41[2] = this.createImage(new MemoryImageSource(this.x71, height6, this.x0(x5, this.x0(x5, x6, 0, 0, 0, 0, x3, x4, width5, x3, x3 * height6, true), 4 * x3 + 4, 0, 0, height6 - this.x71, x3, x4, width5, x3, x3 * height6, true), 0, this.x71));
                }
                else {
                    final int x7 = this.x71;
                    final int height7 = this.x17[0].height;
                    final int n10 = this.x71 / 2;
                    int n11 = 0;
                    do {
                        this.x41[n11 + 5] = this.createImage(x7, x7);
                        if (n11 == 2) {
                            final Graphics graphics3 = this.x41[n11 + 5].getGraphics();
                            graphics3.setColor(this.x76[0][2]);
                            graphics3.fill3DRect(0, 0, x7, x7, true);
                            graphics3.fill3DRect(2, 2, x7 - 4, x7 - 4, false);
                            final Polygon polygon = new Polygon();
                            polygon.addPoint(1, n10);
                            polygon.addPoint(n10, 2);
                            polygon.addPoint(x7 - 3, n10);
                            polygon.addPoint(n10, x7 - 3);
                            graphics3.setColor(this.x76[1][0]);
                            graphics3.fillPolygon(polygon);
                            graphics3.setColor(this.x76[0][2].darker());
                            graphics3.drawLine(1, n10, x7 - 4, n10);
                        }
                    } while (++n11 < 6);
                    this.x41[2] = this.createImage(this.x71, height7);
                    final Graphics graphics4 = this.x41[2].getGraphics();
                    graphics4.setColor(this.x15[0]);
                    graphics4.fillRect(0, 0, this.x71, height7);
                    graphics4.setColor(this.x76[0][2]);
                    graphics4.fill3DRect(n10 - 3, 0, this.x71 - n10 * 2 + 6, height7, true);
                    graphics4.fill3DRect(n10 - 1, 2, this.x71 - n10 * 2 + 2, height7 - 4, false);
                }
                ++this.x46;
                this.x1();
                final int width6 = this.bounds().width;
                final int height8 = this.bounds().height;
                if (array[1] != null) {
                    final int width7 = array[1].getWidth(null);
                    final int height9 = array[1].getHeight(null);
                    final int[] array6 = new int[width7 * height9];
                    array[1] = this.createImage(new MemoryImageSource(width7, height9, this.x0(array[1], 0, 0, width7, height9), 0, width7));
                    this.x47 = this.createImage(width6, height8);
                    this.x0(array[1], width7, height9, width6, height8, 0);
                    this.x41[1] = this.createImage(width6, height8);
                    this.x41[1].getGraphics().drawImage(this.x47, 0, 0, null);
                }
                final int width8 = this.x67.width;
                final int height10 = this.x67.height;
                final Dimension x8 = this.x0(true);
                final int max = Math.max(x8.width, width8);
                final int max2 = Math.max(x8.height, height10);
                this.x21 = new Dimension(max, max2);
                this.x22 = this.x0(false);
                if (array[0] != null) {
                    final int x9 = this.x67.x;
                    final int y = this.x67.y;
                    final int n12 = max + x9;
                    final int n13 = max2 + y;
                    final int width9 = array[0].getWidth(null);
                    final int height11 = array[0].getHeight(null);
                    final int[] array7 = new int[width9 * height11];
                    array[0] = this.createImage(new MemoryImageSource(width9, height11, this.x0(array[0], 0, 0, width9, height11), 0, width9));
                    this.x47 = this.createImage(n12, n13);
                    this.x0(array[0], width9, height11, n12, n13, 0);
                    this.x41[0] = this.createImage(n12, n13);
                    this.x41[0].getGraphics().drawImage(this.x47, 0, 0, null);
                }
                int n14 = 0;
                do {
                    if (array[n14] != null) {
                        array[n14].flush();
                    }
                    array[n14] = null;
                } while (++n14 < 5);
                System.gc();
                return true;
            }
        }
        this.x66 = 20 + n2;
        return false;
    }
    
    private boolean x1(final Graphics graphics) {
        final String[][] array = new String[1000][5];
        if (this.x83 != null) {
            URL url;
            try {
                url = new URL(this.x13, this.x83);
            }
            catch (Exception ex) {
                this.x66 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.x66 = 4;
                return false;
            }
            ++this.x46;
            this.x1();
            final byte[] array2 = new byte[100000];
            try {
                try {
                    dataInputStream.readFully(array2);
                }
                catch (EOFException ex3) {}
            }
            catch (IOException ex4) {
                this.x66 = 5;
                return false;
            }
            try {
                dataInputStream.close();
            }
            catch (IOException ex5) {}
            ++this.x46;
            this.x1();
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.x57 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x52 = this.x57;
                if (this.x57 >= 998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.x23 == null) {
                        this.x66 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.x57;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x57;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x23 == null) {
                            this.x66 = 13;
                            return false;
                        }
                        ++this.x57;
                        n = 0;
                    }
                    array[this.x57][n] = streamTokenizer.sval;
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
            this.x57 = 0;
            while (!b2) {
                if (this.x57 >= 998) {
                    b2 = true;
                }
                this.x64[0] = "entry" + this.x57;
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
                        array[this.x57][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x57;
            }
            --this.x57;
        }
        ++this.x46;
        this.x1();
        if (this.x57 >= 200 && this.x23 == null) {
            this.x66 = 15;
            this.x52 = this.x57;
            return false;
        }
        for (int i = 0; i < this.x57; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x23 == null) {
                    this.x66 = 10;
                    this.x52 = i;
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
        array[this.x57][1] = "0";
        ++this.x46;
        this.x1();
        this.x6 = new String[this.x57 + 1];
        this.x8 = new String[this.x57 + 1];
        this.x2 = new String[this.x57 + 1];
        this.x11 = new boolean[this.x57 + 1];
        this.x7 = new boolean[this.x57 + 1];
        this.x3 = new boolean[this.x57 + 1];
        this.x10 = new short[this.x57 + 1];
        this.x5 = new byte[this.x57 + 1];
        this.x9 = new byte[this.x57 + 1];
        this.x4 = new boolean[this.x57 + 1];
        this.x85 = new Rectangle[this.x57 + 1];
        this.x86 = new int[this.x57 + 1];
        for (int j = 0; j < this.x57; ++j) {
            this.x3[j] = false;
            this.x85[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x57; ++k) {
            this.x52 = k;
            this.x4[k] = false;
            this.x6[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x23 == null) {
                    this.x66 = 10;
                    return false;
                }
                max = 1;
            }
            this.x5[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x11[k] = true;
                this.x9[k] = 0;
                this.x7[k] = false;
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x57; ++l) {
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
                this.x9[k] = 2;
            }
            this.x2[k] = array[k][3];
            if (this.x2[k].toLowerCase().startsWith("start") || this.x2[k].toLowerCase().indexOf(";start") != -1) {
                this.x72 = k;
            }
            final int int2 = Integer.parseInt(array[k][2]);
            if (int2 > 0 && int2 < this.x73) {
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
        this.x0(graphics, this.x63);
        return graphics;
    }
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x64[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x64[n]);
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
        this.x16 = this.x0(1, " ");
        this.x83 = this.x0(15, null);
        this.x59 = this.x0(16, 300, -300, 0, 10);
        this.x58 = this.x0(17, 300, -300, 2, 10);
        this.x1 = this.x0(18, 50, 0, 0, 10);
        this.x0 = this.x0(19, 50, 0, 10, 10);
        this.x19 = this.x0(20, "_top");
        this.x73 = this.x0(21, 50, 2, 3, 10);
        this.x15[1] = new Color(this.x0(22, 0, 0, 12632256, 16));
        this.x42[4] = this.x0(23, null);
        this.x42[2] = this.x0(24, null);
        final int x0 = this.x0(25, 300, 10, 150, 10);
        final int x2 = this.x0(26, 200, 10, 30, 10);
        this.x53 = x2 + this.x0(29, 200, 0, 0, 10);
        this.x20 = new Dimension(x0, x2);
        this.x43 = this.x0(27, 100, -100, 0, 10);
        this.x44 = this.x0(28, 100, -100, 0, 10);
        this.x42[1] = this.x0(30, null);
        final int x3 = this.x0(31, 30, 0, 0, 10);
        this.x31 = this.x0(32, 100, 0, x3, 10);
        this.x24 = this.x0(33, 100, 0, x3, 10);
        this.x27 = this.x0(34, 30, 0, x3, 10);
        this.x30 = this.x0(35, 30, 0, x3, 10);
        if (this.x42[1] == null) {
            this.x31 = 0;
            this.x24 = 0;
            this.x27 = 0;
            this.x30 = 0;
        }
        this.x42[3] = this.x0(36, null);
        this.x71 = this.x0(37, 19, 7, 11, 10);
        this.x70 = this.x0(38, 2, -1, 0, 10);
        this.x69 = this.x0(39, 100, 10, 10, 10);
        this.x15[0] = new Color(this.x0(40, 0, 0, 0, 16));
        this.x42[0] = this.x0(41, null);
        this.x62 = this.x0(42, " ");
        this.x14 = this.x0(43, " ");
        try {
            this.x18 = this.getDocumentBase();
            this.x13 = this.getCodeBase();
            this.x23 = new URL(this.x18, this.x0(44, null));
        }
        catch (Throwable t) {
            this.x23 = null;
        }
        this.x80 = this.x0(45, 2, 0, 0, 10);
        this.x81 = this.x0(46, 5, -5, 1, 10);
        this.x82 = this.x0(47, 5, -5, 1, 10);
        int n = 0;
        do {
            this.x64[0] = "sub" + n;
            this.x74[n][0] = this.x0(0, "LINK:");
            this.x74[n][1] = "sub" + n + ":";
        } while (++n < 10);
        this.x15[2] = Color.orange;
        this.x77 = new Font[this.x73][2];
        this.x76 = new Color[this.x73][6];
        this.x78 = new byte[this.x73][5];
        int n2 = 0;
        do {
            this.x64[0] = this.x64[2 + n2];
            int n3 = Color.black.getRGB();
            if (n2 == 3) {
                n3 = Color.gray.getRGB();
            }
            if (n2 == 2) {
                n3 = Color.blue.getRGB();
            }
            if (n2 == 1) {
                n3 = Color.red.getRGB();
            }
            final int x4 = this.x0(0, 0, 0, n3, 16);
            for (int i = 0; i < this.x73; ++i) {
                this.x64[0] = this.x64[2 + n2] + i;
                this.x76[i][n2] = new Color(this.x0(0, 0, 0, x4, 16));
                if (n2 == 3) {
                    this.x76[i][5] = new Color((this.x76[i][3].getRed() + this.x76[i][2].getRed()) / 2, (this.x76[i][3].getGreen() + this.x76[i][2].getGreen()) / 2, (this.x76[i][3].getBlue() + this.x76[i][2].getBlue()) / 2);
                }
            }
        } while (++n2 < 4);
        final int[] array = new int[2];
        final int[] array2 = new int[5];
        array[0] = this.x0(6, 3, 0, 0, 10);
        array[1] = this.x0(7, 3, 0, array[0], 10);
        final String x5 = this.x0(8, "TimesRoman");
        final int x6 = this.x0(9, 30, 6, 11, 10);
        int n4 = 0;
        do {
            array2[n4] = this.x0(10 + n4, 50, -50, 0, 10);
        } while (++n4 < 4);
        array2[4] = this.x0(14, 50, 0, x6, 10);
        for (int j = 0; j < this.x73; ++j) {
            this.x64[0] = this.x64[8] + j;
            final String x7 = this.x0(0, x5);
            this.x64[0] = this.x64[9] + j;
            final int x8 = this.x0(0, 30, 6, x6, 10);
            int n5 = 0;
            do {
                this.x64[0] = this.x64[6] + j;
                this.x77[j][n5] = new Font(x7, this.x0(0, 3, 0, array[n5], 10), x8);
            } while (++n5 < 2);
            int n6 = 0;
            do {
                this.x64[0] = this.x64[10 + n6] + j;
                if (n6 == 2) {
                    this.x78[j][n6] = (byte)this.x0(0, 2, 0, array2[n6], 10);
                }
                else if (n6 == 4) {
                    this.x78[j][n6] = (byte)this.x0(0, 50, 0, array2[n6], 10);
                }
                else {
                    this.x78[j][n6] = (byte)this.x0(0, 50, -50, array2[n6], 10);
                }
            } while (++n6 < 5);
        }
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        int x9 = this.x27;
        final int x10 = this.x31;
        int n7 = width - this.x27 - this.x30;
        final int n8 = height - this.x31 - this.x24;
        int x11 = this.x27;
        final int x12 = this.x31;
        this.x17[1] = new Rectangle(x11, x12, this.x71, n8);
        if (this.x70 > 0) {
            x11 = width - this.x30 - this.x71;
        }
        this.x17[0] = new Rectangle(x11, x12, this.x71, n8);
        this.x67 = new Rectangle(x9, x10, n7, n8);
        this.x39 = this.createImage(n7, n8);
        this.x34 = this.x39.getGraphics();
        if (this.x70 != 0) {
            n7 -= this.x71;
        }
        if (this.x70 < 0 || this.x70 == 2) {
            x9 += this.x71;
        }
        if (this.x70 == 2) {
            n7 -= this.x71;
        }
        this.x63 = new Rectangle(x9, x10, n7, n8);
        this.x68 = new Rectangle(0, 0, n7, this.x53);
        this.x40 = this.createImage(n7, this.x53);
        this.x35 = this.x40.getGraphics();
        this.x75 = new Image[this.x73 * 6];
        this.x54 = this.getGraphics();
        System.gc();
    }
    
    private void x5() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.x45) {
            final int n = 8;
            (this.x51 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x51[1] = "TRIAL VERSION ONLY";
            this.x51[2] = "This copyright display";
            this.x51[3] = "is not shown in the";
            this.x51[4] = "purchasable version.";
            this.x51[5] = "Navigation Applet";
            this.x51[6] = "© Image Intelligence Ltd.";
            this.x51[7] = "www.imint.com";
            for (int i = 1; i < n; ++i) {
                System.out.println(this.x51[i]);
            }
            this.x0(graphics, this.bounds(), this.x15[0]);
            this.x84 = false;
            this.x88 = 0;
            char c = '\0';
            this.x61 = 0;
            this.folder = 0;
            this.x89 = 0;
            this.x65 = ((this.x23 == null) ? 2 : 3);
            this.x46 = 1;
            this.x1();
            this.x72 = -1;
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.x51[j];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.x16.equalsIgnoreCase(this.x51[0])) {
                this.x66 = 9;
                this.x65 = 1;
                return;
            }
            if (c != 1499310) {
                this.x66 = 30;
                this.x65 = 1;
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
            this.x46 = 2;
            this.x1();
            try {
                if (!this.x1(graphics) && !this.x1(graphics)) {
                    this.x65 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x66 = 6;
                this.x65 = 1;
                return;
            }
            this.x46 = 8;
            this.x1();
            try {
                if (!this.x0(graphics) && !this.x0(graphics)) {
                    this.x65 = 1;
                    return;
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.x66 = 7;
                this.x65 = 1;
                return;
            }
            this.x46 = 11;
            this.x1();
            this.x84 = true;
            try {
                this.x6();
                if (this.x48 == null) {
                    this.x6();
                }
                if (this.x48 == null) {
                    this.x66 = 8;
                    this.x65 = 1;
                    return;
                }
                if (this.x87 > 50) {
                    this.x1(0, false);
                    this.x6();
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3);
                this.x66 = 8;
                this.x65 = 1;
                return;
            }
            this.x46 = 12;
            this.x1();
            this.x45 = true;
            this.x37 = true;
            this.x65 = 0;
            if (this.x72 >= 0 && this.x72 < this.x57) {
                this.x0(this.x72, false);
            }
        }
        if (this.x84 && this.x48 != null) {
            this.x0(graphics, this.bounds(), this.x15[0]);
            if (this.x42[1] != null && this.x41[1] != null) {
                graphics.drawImage(this.x41[1], 0, 0, null);
            }
            this.x0(graphics, this.x67);
            this.x0();
            this.x0(graphics, 0, 0);
            this.x0(graphics, this.x63);
            graphics.drawImage(this.x48, this.x63.x, this.x63.y - this.x89, null);
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
    
    private int x0(int n, int n2) {
        if (!this.x63.inside(n, n2)) {
            return -1;
        }
        n -= this.x63.x;
        n2 -= this.x63.y - this.x89;
        for (int i = 0; i < this.x57; ++i) {
            if (this.x85[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private void x6() {
        final int width = this.x21.width;
        final int height = this.x21.height;
        if (this.x38 != null) {
            this.x38.flush();
        }
        if (this.x0 != 0 && this.x48 != null) {
            this.x38 = this.createImage(width, height);
            this.x38.getGraphics().drawImage(this.x48, 0, 0, null);
        }
        if (this.x48 != null) {
            this.x48.flush();
        }
        this.x48 = this.createImage(width, height);
        Graphics graphics = this.x48.getGraphics();
        if (this.x42[0] != null && this.x41[0] != null) {
            graphics.drawImage(this.x41[0], 0, 0, null);
        }
        else {
            graphics.setColor(this.x15[0]);
            graphics.fillRect(0, 0, width, height);
        }
        for (int i = 0; i < this.x57; ++i) {
            this.x85[i].reshape(0, 0, 0, 0);
            this.x86[i] = 0;
        }
        int x87 = 0;
        for (short n = 0; n < this.x57; ++n) {
            final int n2 = x87 * this.x53;
            ++x87;
            int n3 = this.x9[n] * 3;
            if (this.x4[n]) {
                this.x4[n] = false;
            }
            if (this.x11[n] && this.x7[n]) {
                n3 += 3;
            }
            this.showStatus(" ");
            if (!this.x60) {
                graphics.drawImage(this.x75[n3], this.x58, n2 + this.x59, null);
                if (n == 0) {
                    graphics.drawImage(this.x75[n3], this.x58, n2 + this.x59, null);
                }
            }
            this.x85[x87 - 1].reshape(0, (x87 - 1) * this.x53, this.x63.width, this.x53);
            if (n == this.folder) {
                this.x29 = x87 - 1;
            }
            this.x0(graphics, n, this.x85[x87 - 1], false);
            graphics = this.x48.getGraphics();
            this.x86[x87 - 1] = n;
            if (this.x11[n] && !this.x7[n]) {
                for (short n4 = 0; n4 < this.x10[n]; ++n4) {
                    if (this.x11[n4 + n]) {
                        this.x7[n4 + n] = false;
                    }
                }
                n += this.x10[n];
            }
        }
        this.x87 = x87;
        if (this.x28 && this.x87 != 0) {
            this.x88 = this.x29 * 100 / this.x87;
        }
        this.x28 = false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (!this.x37) {
                return true;
            }
            this.x56 = true;
            final int x0 = this.x0(n, n2);
            if (x0 >= 0 && x0 < this.x57) {
                this.x61 = x0;
                this.x0(1);
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        try {
            if (this.x17[0].inside(n, n2) && this.x36[2]) {
                this.x1(n2);
            }
            if (!this.x56 || !this.x37) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            if (x0 == this.x61) {
                return true;
            }
            this.showStatus(" ");
            if (this.x61 != -1) {
                this.x0(0);
                this.x61 = -1;
            }
            if (x0 >= 0 && x0 < this.x57) {
                this.x61 = x0;
                this.showStatus(this.x8[this.x86[x0]]);
                this.x0(1);
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            if (this.x61 != -1) {
                this.x0(0);
                this.x61 = -1;
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        try {
            if (!this.x37) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            if (x0 == this.x61) {
                return true;
            }
            this.showStatus(" ");
            if (this.x61 != -1) {
                this.x0(0);
                this.x61 = -1;
            }
            if (x0 >= 0 && x0 < this.x57) {
                this.x61 = x0;
                this.showStatus(this.x8[this.x86[x0]]);
                this.x0(2);
            }
            System.gc();
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (this.x17[0].inside(n, n2) && this.x36[2]) {
                this.x1(n2);
            }
            if (!this.x37) {
                return true;
            }
            this.x56 = false;
            final int x0 = this.x0(n, n2);
            if (x0 > this.x57 || x0 < 0) {
                return true;
            }
            final int n3 = this.x86[x0];
            if (n3 > this.x57 || n3 < 0) {
                return true;
            }
            this.x61 = x0;
            try {
                if (this.x14.endsWith(".au")) {
                    this.play(this.x18, this.x14);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {}
            this.x0(0);
            this.x0(n3, true);
        }
        catch (Throwable t) {}
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.x65 == 0 && this.x84 && this.x48 != null) {
            this.showStatus(" ");
            this.x0(graphics, this.bounds(), this.x15[0]);
            if (this.x42[1] != null && this.x41[1] != null) {
                graphics.drawImage(this.x41[1], 0, 0, null);
            }
            this.x0(graphics, this.x67);
            this.x0();
            this.x0(graphics, 0, 0);
            this.x0(graphics, this.x63);
            graphics.drawImage(this.x48, this.x63.x, this.x63.y - this.x89, null);
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.setColor(Color.black);
        try {
            int n = 1;
            do {
                graphics.drawString(this.x51[n], 3, 14 * n);
            } while (++n < 8);
        }
        catch (Exception ex) {}
        if (this.x65 == 1) {
            String s = "Error: " + this.x66;
            if (this.x52 != -1) {
                s = s + " / " + this.x52;
            }
            graphics.drawString(s, 3, 140);
            this.showStatus("Applet reports " + s);
            return;
        }
        graphics.drawString(this.x45 ? "TRIAL DELAY: 3 secs" : ("Loading: " + (12 - this.x46)), 3, 120);
    }
    
    private void x0(final Graphics graphics, final int n, final int n2) {
        this.showStatus(" ");
        final int n3 = this.x17[0].x - n;
        final int n4 = this.x17[0].y - n2;
        final int n5 = this.x17[1].x - n;
        final int n6 = this.x17[0].height - 2 * this.x71;
        final int n7 = this.x17[1].height - 2 * this.x71;
        final int max = Math.max(Math.min(this.x71 + this.x88 * n6 / 100, n6), this.x71);
        if (this.x41[2] != null) {
            graphics.drawImage(this.x41[2], n3, n4, null);
            graphics.drawImage(this.x41[2], n5, n4, null);
        }
        if (this.x41[7] != null) {
            graphics.drawImage(this.x41[7], n3, n4 + max, null);
            graphics.drawImage(this.x41[7], n5, n4 + max, null);
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
                this.x66 = 0;
                System.out.println(ex.toString());
                this.x65 = 1;
            }
        }
        if (this.x65 > 0 && (this.x23 == null || this.x66 < 10)) {
            this.x84 = false;
            if (this.x23 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x23, "_self");
            }
            this.x45 = true;
            this.stop();
        }
        System.gc();
    }
    
    public sb5trial() {
        this.x52 = -1;
        this.x41 = new Image[11];
        this.x17 = new Rectangle[2];
        this.x36 = new boolean[4];
        this.x15 = new Color[3];
        this.x42 = new String[5];
        this.x74 = new String[10][2];
        this.x26 = -1;
        this.x50 = -1;
        this.x64 = new String[] { "dummy", "copyright", "textNcolour", "textHcolour", "textBcolour", "textScolour", "textNstyle", "textHstyle", "textfont", "textsize", "textXoffset", "textYoffset", "textposition", "textmargin", "textspacing", "menufile", "menuYoffset", "menuXoffset", "menuclosetrigger", "menuanimspeed", "menudeftarget", "menustyles", "imgtranscol", "imgbasefile", "imgfile", "imgwidth", "imgheight", "imgXoffset", "imgYoffset", "entryheight", "frameimage", "framewidth", "frametop", "framebottom", "frameleft", "frameright", "scrollimages", "scrollwidth", "scrollvert", "scrolljump", "bgcolour", "bgimage", "audiomove", "audioclick", "escapepage", "textshadow", "textXpress", "textYpress" };
    }
    
    public void start() {
        if (this.x55 == null) {
            (this.x55 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.x55 != null) {
            this.x55.stop();
            this.x55 = null;
        }
    }
    
    private void x0(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Graphics graphics = this.x47.getGraphics();
        graphics.clipRect(0, n5, n3, n4);
        final int n6 = n3 / n;
        for (int n7 = n4 / n2, i = 0; i <= n7; ++i) {
            for (int j = 0; j <= n6; ++j) {
                graphics.drawImage(image, j * n, i * n2 + n5, null);
            }
        }
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
        try {
            this.x12 = this.createImage(this.size().width, this.size().height);
            this.paint(this.x32 = this.x12.getGraphics());
            graphics.drawImage(this.x12, 0, 0, null);
            this.x12.flush();
            this.x32.dispose();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
    }
    
    private String[] x0(final String s, final int n, final FontMetrics fontMetrics) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String[] array = new String[4];
        String s2 = "";
        String s3 = "";
        int n2 = 0;
        int n3 = 0;
        do {
            array[n3] = "";
        } while (++n3 < 4);
        while (stringTokenizer.hasMoreTokens() && n2 < 4) {
            String nextToken = stringTokenizer.nextToken();
            String string = s3 + " " + nextToken;
            if (nextToken.equalsIgnoreCase("§")) {
                if (n2 < 4) {
                    array[n2] = s3.trim();
                }
                ++n2;
                string = "";
                s3 = "";
                nextToken = "";
            }
            if (fontMetrics.stringWidth(string) > n) {
                if (n2 < 4) {
                    array[n2] = s3.trim();
                }
                if (array[n2].length() == 0) {
                    if (n2 < 4) {
                        array[n2] = nextToken.trim();
                    }
                    s3 = "";
                }
                else {
                    s3 = nextToken;
                }
                ++n2;
            }
            else {
                s3 = string;
            }
            s2 = "";
        }
        if (n2 < 4) {
            array[n2] = (s3 + " " + s2).trim();
        }
        return array;
    }
}
