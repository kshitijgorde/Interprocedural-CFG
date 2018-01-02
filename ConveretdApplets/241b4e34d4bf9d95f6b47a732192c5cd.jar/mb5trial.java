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

public final class mb5trial extends Applet implements Runnable
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
    private URL x17;
    private String x18;
    private Dimension x19;
    private Dimension x20;
    private Dimension x21;
    private URL x22;
    private int x23;
    private String x24;
    private int x25;
    private int x26;
    public int folder;
    private boolean x27;
    private int x28;
    private int x29;
    private int x30;
    private Graphics x31;
    private Graphics x32;
    private boolean[] x33;
    private boolean x34;
    private Image x35;
    private Image x36;
    private Image[] x37;
    private String[] x38;
    private int x39;
    private int x40;
    private boolean x41;
    private int x42;
    private Image x43;
    private Image x44;
    private String x45;
    private int x46;
    private String[] x47;
    private int x48;
    private int x49;
    private Graphics x50;
    private Thread x51;
    private boolean x52;
    private int x53;
    private int x54;
    private int x55;
    private boolean x56;
    private int x57;
    private String x58;
    private Rectangle x59;
    private String[] x60;
    private int x61;
    private int x62;
    private Rectangle x63;
    private int x64;
    private int x65;
    private String[][] x66;
    private Image[] x67;
    private Color[][] x68;
    private Font[][] x69;
    private byte[][] x70;
    private boolean x71;
    private int x72;
    private String x73;
    private boolean x74;
    private Rectangle[] x75;
    private int[] x76;
    private int x77;
    
    private void x0() {
        System.gc();
        this.repaint();
    }
    
    private void x0(final int n, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x74 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(2999L);
            }
            catch (Exception ex) {}
            this.x74 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x2[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken().trim();
            int n2 = s.length();
            if (s.toLowerCase().startsWith("audio")) {
                try {
                    this.play(this.x17, s.substring(6, n2).trim());
                }
                catch (OutOfMemoryError outOfMemoryError) {}
            }
            if (s.toLowerCase().startsWith("sub")) {
                int n3 = 0;
                do {
                    if (s.toLowerCase().startsWith(this.x66[n3][1])) {
                        s = this.x66[n3][0] + s.substring(5, n2);
                        n2 = s.length();
                    }
                } while (++n3 < 10);
            }
            if (s.toLowerCase().startsWith("link")) {
                int index = s.indexOf(",");
                String s2;
                if (index == -1 || n2 - index < 2) {
                    s2 = this.x18;
                    index = n2;
                }
                else {
                    s2 = s.substring(index + 1, n2).trim();
                }
                final String trim = s.substring(5, index).trim();
                try {
                    url = new URL(this.x17, trim);
                }
                catch (Exception ex2) {
                    if (trim.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.x17, "mailto.htm");
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
                this.x4();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x73 = this.x73;
                final int folder = this.folder;
                try {
                    if (this.x73.equalsIgnoreCase(this.x24)) {
                        this.x25 = this.folder;
                    }
                }
                catch (Exception ex4) {}
                this.x73 = s.substring(6, n2).trim();
                this.x34 = false;
                this.x41 = false;
                this.x3();
                if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
                    this.x73 = x73;
                    this.x34 = false;
                    this.x41 = false;
                    this.x3();
                }
                try {
                    if (this.x61 == 0 && this.x73.equalsIgnoreCase(this.x24)) {
                        this.clik(this.x25);
                    }
                    else if (this.x61 == 0 && this.x73.equalsIgnoreCase(this.x45)) {
                        this.clik(this.x46);
                    }
                }
                catch (Exception ex5) {}
                this.x45 = x73;
                this.x46 = folder;
                if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
                    this.x74 = false;
                    if (this.x22 == null) {
                        this.repaint();
                    }
                    else {
                        this.getAppletContext().showDocument(this.x22, "_self");
                    }
                    this.x41 = true;
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
        this.x2(n, true);
        this.x0(n, true);
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
        for (int j = 0; j < this.x77; ++j) {
            if (n == this.x76[j]) {
                b5 = true;
            }
        }
        if (!b5) {
            for (int k = 0; k <= n; ++k) {
                if (this.x11[k]) {
                    this.x7[k] = true;
                }
            }
            this.x4();
            b = false;
        }
        for (int l = this.x77 - 1; l >= 0; --l) {
            final int n2 = this.x76[l];
            if (this.x11[n2] && this.x7[n2]) {
                boolean b6 = false;
                for (byte b7 = 0; b7 < b4; ++b7) {
                    if (n2 == array[b7]) {
                        b6 = true;
                    }
                }
                if (!b6) {
                    this.x7[n2] = false;
                    this.x57 = l;
                    if (b) {
                        this.x1();
                    }
                }
            }
        }
        for (int x57 = this.x77 - 1; x57 >= 0; --x57) {
            if (this.x76[x57] == n) {
                this.x57 = x57;
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
                    if (n10 == 1 || n10 == 255) {
                        array3[n12] = array[n11];
                    }
                }
                catch (IndexOutOfBoundsException ex) {}
            }
        }
        return array3;
    }
    
    private void x1() {
        try {
            if (this.x0 != 0) {
                this.x2();
            }
            else {
                this.x4();
            }
        }
        catch (Throwable t) {
            System.gc();
        }
        this.repaint();
        this.x34 = true;
    }
    
    private void x0(final Graphics graphics, final int n, final Rectangle rectangle) {
        final String s = this.x6[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final byte b = this.x9[n];
        final int n2 = this.x4[n] ? 1 : 0;
        final int x = rectangle.x;
        int y = rectangle.y;
        final byte b2 = this.x70[b][2];
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
        if (this.x56) {
            if (b != 4) {
                final byte b3 = (byte)(this.x7[n] ? (b + 1) : b);
                final int n3 = (rectangle.height < 25) ? 3 : 6;
                int x2 = rectangle.x;
                final int y2 = rectangle.y;
                int n4 = rectangle.width - 1;
                int n5 = this.x19.height - 1;
                int n6 = y2 + (rectangle.height - n5) / 2;
                if (b3 > 0) {
                    n5 += n3;
                }
                if (b3 > 1) {
                    n6 -= n3;
                }
                if (b3 == 2 || (b3 >= 5 && b3 <= 8) || b3 == 11) {
                    n5 += n3;
                }
                graphics.setColor(this.x68[b][2].darker().darker());
                ++n4;
                ++n5;
                graphics.fill3DRect(x2, n6, n4, n5, true);
                --n4;
                --n5;
                graphics.setColor(this.x68[b][2]);
                graphics.fill3DRect(x2, n6, n4, n5, true);
                if (rectangle.height > 24) {
                    x2 += 3;
                    n6 += 3;
                    n4 -= 6;
                    n5 -= 6;
                    graphics.fill3DRect(x2, n6, n4, n5, false);
                    ++x2;
                    ++n6;
                    n4 -= 2;
                    n5 -= 2;
                    if (n2 == 1 && b3 < 5) {
                        graphics.setColor(this.x68[b][2].brighter());
                    }
                    graphics.fill3DRect(x2, n6, n4, n5, true);
                }
                if (b3 > 4) {
                    int n7 = rectangle.x + n3;
                    final int y3 = rectangle.y;
                    int n8 = rectangle.width - n3 * 2 - 1;
                    int n9 = this.x19.height - 1;
                    int n10 = y3 + (rectangle.height - n9) / 2;
                    if (b3 == 6 || b3 == 7 || b3 == 11) {
                        n9 += n3;
                    }
                    if (b3 >= 7 && b3 <= 9) {
                        n10 -= n3;
                    }
                    if (b3 == 8) {
                        n9 += 2 * n3;
                    }
                    if (b3 == 10) {
                        n9 -= n3;
                    }
                    if (b3 == 9 || b3 == 10) {
                        ++n9;
                    }
                    graphics.setColor(this.x68[b][4].darker().darker());
                    ++n8;
                    ++n9;
                    graphics.fill3DRect(n7, n10, n8, n9, true);
                    --n8;
                    --n9;
                    graphics.setColor(this.x68[b][4]);
                    graphics.fill3DRect(n7, n10, n8, n9, true);
                    if (rectangle.height > 24) {
                        n7 += 3;
                        n10 += 3;
                        n8 -= 6;
                        n9 -= 6;
                        graphics.fill3DRect(n7, n10, n8, n9, false);
                        if (n2 == 1) {
                            graphics.setColor(this.x68[b][4].brighter());
                        }
                        ++n7;
                        ++n10;
                        n8 -= 2;
                        n9 -= 2;
                        graphics.fill3DRect(n7, n10, n8, n9, true);
                    }
                }
            }
            else {
                graphics.setColor(this.x68[b][2]);
                if (n2 == 1) {
                    graphics.setColor(this.x68[b][2].brighter());
                }
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        int n11 = 0;
        if (substring != null) {
            final byte b4 = (byte)(this.x70[b][3] + ((b > 4) ? 6 : 0));
            final Font font = this.x69[b][n2];
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
            final String[] x3 = this.x0(substring, rectangle.width - b4 * 2, fontMetrics);
            int n12 = 0;
            int n13 = 0;
            do {
                if (x3[n13].length() != 0) {
                    ++n12;
                }
            } while (++n13 < 4);
            final byte b5 = (byte)(x + this.x70[b][0]);
            final byte b6 = this.x70[b][4];
            y += rectangle.height / 2 - n12 * b6 / 2 + this.x70[b][1] + fontMetrics.getAscent() - fontMetrics.getDescent();
            int n14 = 0;
            final int n15 = (b < 4) ? 5 : 6;
            for (byte b7 = 0; b7 < n12; ++b7) {
                if (b2 == 0) {
                    n14 = b5 + rectangle.width / 2 - fontMetrics.stringWidth(x3[b7]) / 2;
                }
                if (b2 == 2) {
                    n14 = b5 + b4;
                }
                if (b2 == 1) {
                    n14 = b5 + rectangle.width - fontMetrics.stringWidth(x3[b7]) - b4;
                }
                final byte b8 = (byte)(y + b7 * b6);
                if (this.x72 > 0) {
                    if (this.x72 > 1) {
                        graphics.setColor(this.x68[b][n15]);
                        int n16 = 0;
                        do {
                            byte b9 = 0;
                            do {
                                graphics.drawString(x3[b7], n14 + n16, b8 + b9);
                            } while (++b9 < 3);
                        } while (++n16 < 3);
                    }
                    graphics.setColor(this.x68[b][3]);
                    graphics.drawString(x3[b7], n14 + 1, b8 + 1);
                }
                graphics.setColor(this.x68[b][n2]);
                graphics.drawString(x3[b7], n14, b8);
            }
            n11 = n14 + fontMetrics.stringWidth(x3[n12 - 1]);
        }
        if (image != null && (b2 == 2 || substring == null)) {
            graphics.drawImage(image, n11 + this.x39, y + (rectangle.height - image.getHeight(null)) / 2 + this.x40, this);
        }
    }
    
    private void x2() {
        this.x34 = false;
        Graphics graphics = this.getGraphics();
        final int n = this.x77 * this.x49;
        final Rectangle rectangle = this.x75[this.x57];
        final int max = Math.max(0, rectangle.y + rectangle.height);
        int n2 = 0;
        for (int i = 0; i < this.x77; ++i) {
            n2 = Math.max(n2, this.x75[i].y);
        }
        this.x4();
        final int n3 = this.x77 * this.x49 - n;
        final int width = rectangle.width;
        for (int j = 0; j < this.x77; ++j) {
            n2 = Math.max(n2, this.x75[j].y);
        }
        final Image image = this.createImage(width, Math.max(1, n2 + rectangle.height - max));
        final Graphics graphics2 = image.getGraphics();
        final int x = this.x59.x;
        final int y = this.x59.y;
        if (n3 > 0) {
            graphics.clipRect(x, y, this.x59.width, max);
            graphics.drawImage(this.x44, x, y, null);
            graphics.drawImage(this.x44, x, y, null);
            graphics = this.getGraphics();
            graphics2.drawImage(this.x44, -rectangle.x, -y - max, null);
        }
        else {
            graphics2.drawImage(this.x35, -rectangle.x, -y - max, null);
        }
        graphics.clipRect(x + rectangle.x, y + max, width, this.x59.height - max);
        final int n4 = (this.x0 > 10) ? (this.x0 - 10) : 1;
        final int n5 = (this.x0 > 10) ? 0 : (10 - this.x0);
        if (n3 > 0) {
            for (int k = 0; k < n3; k += n4) {
                graphics.drawImage(image, x + rectangle.x, max + k - n3, null);
                try {
                    Thread.sleep(n5);
                }
                catch (Exception ex) {}
            }
        }
        else {
            final Image image2 = this.createImage(width, this.x59.height);
            final Graphics graphics3 = image2.getGraphics();
            for (int l = 0; l > n3; l -= n4) {
                graphics3.drawImage(this.x44, -rectangle.x, 0, null);
                graphics3.drawImage(image, 0, max + l - y, null);
                graphics.drawImage(image2, x + rectangle.x, y, null);
                try {
                    Thread.sleep(n5);
                }
                catch (Exception ex2) {}
            }
        }
        System.gc();
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private boolean x0(final Graphics graphics) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[5];
        int n = 0;
        do {
            if (this.x38[n] != null) {
                try {
                    mediaTracker.addImage(array[n] = this.getImage(this.x13, this.x38[n]), n);
                }
                catch (Exception ex) {}
            }
        } while (++n < 5);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.x62 = 1;
            return false;
        }
        int n2 = 0;
        while (!mediaTracker.isErrorID(n2)) {
            if (++n2 >= 5) {
                ++this.x42;
                this.x0();
                if (array[2] != null) {
                    final int width = this.x19.width;
                    final int height = this.x19.height;
                    final int[][] array2 = new int[3][width * height];
                    if (array[4] != null) {
                        final int width2 = array[4].getWidth(null);
                        final int height2 = array[4].getHeight(null);
                        if (width2 != width && height2 != 3 * height + 2) {
                            this.x62 = 11;
                            return false;
                        }
                        final int[] array3 = new int[width2 * height2];
                        final int[] x0 = this.x0(this.x0(array[4], 0, 0, width2, height2), width2 * height2, this.x15[1], false);
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
                    final int width3 = array[2].getWidth(null);
                    final int height3 = array[2].getHeight(null);
                    final int[] array4 = new int[width3 * height3];
                    final int[] x2 = this.x0(this.x0(array[2], 0, 0, width3, height3), width3 * height3, this.x15[1], true);
                    if (this.x22 == null && (4 * (width + 1) > width3 + 1 || (height + 1) * 3 > height3 + 1)) {
                        this.x62 = 25;
                        return false;
                    }
                    int n5 = 0;
                    do {
                        int n6 = n5 * (width + 1);
                        int n7 = 0;
                        if (n5 > 3) {
                            n6 = (n5 - 4) * (width + 1);
                            n7 = height + 1;
                        }
                        if (n5 > 7) {
                            n6 = (n5 - 8) * (width + 1);
                            n7 = 2 * (height + 1);
                        }
                        int n8 = 0;
                        do {
                            this.x67[n5 * 3 + n8] = this.createImage(new MemoryImageSource(width, height, this.x0(x2, array2[n8], n6, n7, 0, 0, width, height, width3, width, width * height, true), 0, width));
                        } while (++n8 < 3);
                    } while (++n5 < 12);
                    int x3 = 0;
                    do {
                        final int width4 = this.x19.width;
                        final int height4 = this.x19.height;
                        this.x62 = 27;
                        this.x48 = x3;
                        if (this.x67[x3] == null) {
                            if (this.x22 == null) {
                                return false;
                            }
                            this.x67[x3] = this.createImage(width4, height4);
                            final Graphics graphics2 = this.x67[x3].getGraphics();
                            graphics2.setColor(this.x15[1]);
                            graphics2.fillRect(0, 0, width4, height4);
                            graphics2.dispose();
                        }
                    } while (++x3 < 35);
                }
                else {
                    this.x56 = true;
                }
                ++this.x42;
                this.x0();
                final int width5 = this.bounds().width;
                final int height5 = this.bounds().height;
                this.x20 = new Dimension(width5, height5);
                int n9 = 0;
                do {
                    if (array[n9] != null) {
                        final int width6 = array[n9].getWidth(null);
                        final int height6 = array[n9].getHeight(null);
                        final int[] array5 = new int[width6 * height6];
                        array[n9] = this.createImage(new MemoryImageSource(width6, height6, this.x0(array[n9], 0, 0, width6, height6), 0, width6));
                        this.x43 = this.createImage(width5, height5);
                        this.x0(array[n9], width6, height6, width5, height5, 0);
                        this.x37[n9] = this.createImage(width5, height5);
                        this.x37[n9].getGraphics().drawImage(this.x43, 0, 0, null);
                    }
                } while (++n9 < 2);
                System.gc();
                return true;
            }
        }
        this.x62 = 20 + n2;
        return false;
    }
    
    private boolean x1(final Graphics graphics) {
        final String[][] array = new String[1000][5];
        if (this.x73 != null) {
            URL url;
            try {
                url = new URL(this.x13, this.x73);
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
            ++this.x42;
            this.x0();
            final byte[] array2 = new byte[100000];
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
            ++this.x42;
            this.x0();
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.x53 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x48 = this.x53;
                if (this.x53 >= 998) {
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
                        ++this.x53;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x53;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x22 == null) {
                            this.x62 = 13;
                            return false;
                        }
                        ++this.x53;
                        n = 0;
                    }
                    array[this.x53][n] = streamTokenizer.sval;
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
            this.x53 = 0;
            while (!b2) {
                if (this.x53 >= 998) {
                    b2 = true;
                }
                this.x60[0] = "entry" + this.x53;
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
                        array[this.x53][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x53;
            }
            --this.x53;
        }
        ++this.x42;
        this.x0();
        if (this.x53 >= 200 && this.x22 == null) {
            this.x62 = 15;
            this.x48 = this.x53;
            return false;
        }
        for (int i = 0; i < this.x53; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x22 == null) {
                    this.x62 = 10;
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
        array[this.x53][1] = "0";
        ++this.x42;
        this.x0();
        this.x6 = new String[this.x53 + 1];
        this.x8 = new String[this.x53 + 1];
        this.x2 = new String[this.x53 + 1];
        this.x11 = new boolean[this.x53 + 1];
        this.x7 = new boolean[this.x53 + 1];
        this.x3 = new boolean[this.x53 + 1];
        this.x10 = new short[this.x53 + 1];
        this.x5 = new byte[this.x53 + 1];
        this.x9 = new byte[this.x53 + 1];
        this.x4 = new boolean[this.x53 + 1];
        this.x75 = new Rectangle[this.x53 + 1];
        this.x76 = new int[this.x53 + 1];
        for (int j = 0; j < this.x53; ++j) {
            this.x3[j] = false;
            this.x75[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x53; ++k) {
            this.x48 = k;
            this.x4[k] = false;
            this.x6[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x22 == null) {
                    this.x62 = 10;
                    return false;
                }
                max = 1;
            }
            this.x5[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x11[k] = true;
                this.x7[k] = false;
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x53; ++l) {
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
            }
            this.x2[k] = array[k][3];
            if (this.x2[k].toLowerCase().startsWith("start") || this.x2[k].toLowerCase().indexOf(";start") != -1) {
                this.x64 = k;
            }
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x8[k] = array[k][4];
            }
            else {
                this.x8[k] = " ";
            }
        }
        for (int n5 = 0; n5 < this.x53; ++n5) {
            int n6 = 0;
            if (this.x5[n5] == 0) {
                if (!this.x11[n5]) {
                    n6 = 4;
                }
                else {
                    n6 = 0;
                }
            }
            if (this.x5[n5] == 1) {
                if (!this.x11[n5]) {
                    if (this.x3[n5]) {
                        n6 = 3;
                    }
                    else {
                        n6 = 2;
                    }
                }
                else {
                    boolean b3 = false;
                    int n7 = 0;
                    try {
                        for (int n8 = n5 + 1; n8 < this.x53; ++n8) {
                            if (this.x5[n8] != 2 && n7 == 0) {
                                n7 = 1;
                                if (this.x5[n8] == 0) {
                                    b3 = true;
                                }
                            }
                        }
                        if (b3 || n7 == 0) {
                            n6 = 10;
                        }
                        else {
                            n6 = 5;
                        }
                    }
                    catch (Exception ex11) {
                        n6 = 10;
                    }
                }
            }
            if (this.x5[n5] == 2) {
                if (!this.x3[n5]) {
                    n6 = 8;
                }
                else {
                    try {
                        if (this.x5[n5 + 1] == 0) {
                            n6 = 9;
                        }
                        else {
                            n6 = 7;
                        }
                    }
                    catch (Exception ex12) {
                        n6 = 9;
                    }
                }
            }
            this.x9[n5] = (byte)n6;
        }
        return true;
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
        this.x16 = this.x0(1, " ");
        this.x73 = this.x0(16, null);
        this.x55 = this.x0(17, 300, -300, 0, 10);
        this.x54 = this.x0(18, 300, -300, 0, 10);
        this.x1 = 0;
        this.x0 = this.x0(20, 50, 0, 10, 10);
        this.x18 = this.x0(21, "_top");
        this.x65 = 13;
        this.x15[1] = new Color(this.x0(22, 0, 0, 12632256, 16));
        this.x15[3] = new Color(this.x0(23, 0, 0, 0, 16));
        this.x38[4] = this.x0(24, null);
        this.x38[2] = this.x0(25, null);
        final int x0 = this.x0(26, 300, 10, 100, 10);
        final int x2 = this.x0(27, 200, 10, 20, 10);
        this.x19 = new Dimension(x0, x2);
        this.x39 = this.x0(28, 100, -100, 0, 10);
        this.x40 = this.x0(29, 100, -100, -13, 10);
        this.x49 = x2 + this.x0(30, 50, -50, 0, 10);
        this.x38[1] = this.x0(31, null);
        final int x3 = this.x0(32, 30, 0, 0, 10);
        this.x30 = this.x0(33, 100, 0, x3, 10);
        this.x23 = this.x0(34, 100, 0, x3, 10);
        this.x26 = this.x0(35, 30, 0, x3, 10);
        this.x29 = this.x0(36, 30, 0, x3, 10);
        if (this.x38[1] == null) {
            this.x30 = 0;
            this.x23 = 0;
            this.x26 = 0;
            this.x29 = 0;
        }
        this.x15[0] = new Color(this.x0(41, 0, 0, 0, 16));
        this.x38[0] = this.x0(42, null);
        this.x58 = this.x0(43, " ");
        this.x14 = this.x0(44, " ");
        try {
            this.x17 = this.getDocumentBase();
            this.x13 = this.getCodeBase();
            this.x22 = new URL(this.x17, this.x0(45, null));
        }
        catch (Throwable t) {
            this.x22 = null;
        }
        this.x72 = this.x0(46, 2, 0, 0, 10);
        int n = 0;
        do {
            this.x60[0] = "sub" + n;
            this.x66[n][0] = this.x0(0, "LINK:");
            this.x66[n][1] = "sub" + n + ":";
        } while (++n < 10);
        this.x15[2] = Color.orange;
        this.x69 = new Font[this.x65][2];
        this.x68 = new Color[this.x65][7];
        this.x70 = new byte[this.x65][5];
        int n2 = 0;
        do {
            this.x60[0] = this.x60[2 + n2];
            int n3 = Color.black.getRGB();
            if (n2 == 4) {
                n3 = Color.blue.getRGB();
            }
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
            for (int i = 0; i < this.x65; ++i) {
                this.x60[0] = this.x60[2 + n2] + i;
                this.x68[i][n2] = new Color(this.x0(0, 0, 0, x4, 16));
                if (n2 == 4) {
                    final int red = this.x68[i][4].getRed();
                    final int green = this.x68[i][4].getGreen();
                    final int blue = this.x68[i][4].getBlue();
                    final int red2 = this.x68[i][2].getRed();
                    final int green2 = this.x68[i][2].getGreen();
                    final int blue2 = this.x68[i][2].getBlue();
                    final int red3 = this.x68[i][3].getRed();
                    final int green3 = this.x68[i][3].getGreen();
                    final int blue3 = this.x68[i][3].getBlue();
                    this.x68[i][6] = new Color((red3 + red) / 2, (green3 + green) / 2, (blue3 + blue) / 2);
                    this.x68[i][5] = new Color((red3 + red2) / 2, (green3 + green2) / 2, (blue3 + blue2) / 2);
                }
            }
        } while (++n2 < 5);
        int n4 = 0;
        do {
            this.x68[3][n4] = this.x68[2][n4];
            this.x68[10][n4] = this.x68[5][n4];
            this.x68[7][n4] = this.x68[8][n4];
            this.x68[9][n4] = this.x68[8][n4];
        } while (++n4 < 7);
        final int[] array = new int[2];
        final int[] array2 = new int[5];
        array[0] = this.x0(7, 3, 0, 0, 10);
        array[1] = this.x0(8, 3, 0, 0, 10);
        final String x5 = this.x0(9, "TimesRoman");
        final int x6 = this.x0(10, 30, 6, 11, 10);
        int n5 = 0;
        do {
            array2[n5] = this.x0(11 + n5, 50, -50, 0, 10);
        } while (++n5 < 4);
        array2[4] = this.x0(14, 50, 0, x6, 10);
        for (int j = 0; j < this.x65; ++j) {
            this.x60[0] = this.x60[9] + j;
            final String x7 = this.x0(0, x5);
            this.x60[0] = this.x60[10] + j;
            final int x8 = this.x0(0, 30, 6, x6, 10);
            int n6 = 0;
            do {
                this.x60[0] = this.x60[7] + j;
                this.x69[j][n6] = new Font(x7, this.x0(0, 3, 0, array[n6], 10), x8);
            } while (++n6 < 2);
            int n7 = 0;
            do {
                this.x60[0] = this.x60[11 + n7] + j;
                if (n7 == 2) {
                    this.x70[j][n7] = (byte)this.x0(0, 2, 0, array2[n7], 10);
                }
                else if (n7 == 4) {
                    this.x70[j][n7] = (byte)this.x0(0, 50, 0, array2[n7], 10);
                }
                else {
                    this.x70[j][n7] = (byte)this.x0(0, 50, -50, array2[n7], 10);
                }
            } while (++n7 < 5);
        }
        this.x69[3][0] = this.x69[2][0];
        this.x69[3][1] = this.x69[2][1];
        this.x69[7][0] = this.x69[8][0];
        this.x69[7][1] = this.x69[8][1];
        this.x69[9][0] = this.x69[8][0];
        this.x69[9][1] = this.x69[8][1];
        this.x69[10][0] = this.x69[5][0];
        this.x69[10][1] = this.x69[5][1];
        int n8 = 0;
        do {
            try {
                this.x70[3][n8] = this.x70[2][n8];
                this.x70[7][n8] = this.x70[8][n8];
                this.x70[9][n8] = this.x70[8][n8];
                this.x70[10][n8] = this.x70[5][n8];
            }
            catch (Exception ex) {}
        } while (++n8 < 5);
        this.x59 = new Rectangle(this.x26, this.x30, this.bounds().width - this.x26 - this.x29, this.bounds().height - this.x30 - this.x23);
        this.x63 = new Rectangle(0, 0, this.x19.width, this.x49);
        this.x36 = this.createImage(this.x19.width, this.x49);
        this.x32 = this.x36.getGraphics();
        this.x67 = new Image[this.x65 * 6];
        this.x50 = this.getGraphics();
        System.gc();
    }
    
    private void x3() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.x41) {
            final int n = 8;
            (this.x47 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x47[1] = "TRIAL VERSION ONLY";
            this.x47[2] = "This copyright display";
            this.x47[3] = "is not shown in the";
            this.x47[4] = "purchasable version.";
            this.x47[5] = "Navigation Applet";
            this.x47[6] = "© Image Intelligence Ltd.";
            this.x47[7] = "www.imint.com";
            for (int i = 1; i < n; ++i) {
                System.out.println(this.x47[i]);
            }
            this.x0(graphics, this.bounds(), this.x15[0]);
            this.x74 = false;
            char c = '\0';
            this.x57 = -1;
            this.folder = 0;
            this.x61 = ((this.x22 == null) ? 2 : 3);
            this.x42 = 1;
            this.x0();
            this.x64 = -1;
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.x47[j];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.x16.equalsIgnoreCase(this.x47[0])) {
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
            this.x42 = 2;
            this.x0();
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
            this.x42 = 8;
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
            this.x42 = 11;
            this.x0();
            this.x74 = true;
            try {
                this.x4();
                if (this.x44 == null) {
                    this.x4();
                }
                if (this.x44 == null) {
                    this.x62 = 8;
                    this.x61 = 1;
                    return;
                }
                if (this.x77 > 50) {
                    this.x1(0, false);
                    this.x4();
                }
            }
            catch (Exception ex3) {
                System.out.println(ex3);
                this.x62 = 8;
                this.x61 = 1;
                return;
            }
            this.x42 = 12;
            this.x0();
            this.x41 = true;
            this.x34 = true;
            this.x61 = 0;
            if (this.x64 >= 0 && this.x64 < this.x53) {
                this.x0(this.x64, false);
            }
        }
        if (this.x74 && this.x44 != null) {
            this.x0(graphics, this.bounds(), this.x15[0]);
            if (this.x38[1] != null && this.x37[1] != null) {
                graphics.drawImage(this.x37[1], 0, 0, null);
            }
            this.x0(graphics, this.x59);
            graphics.drawImage(this.x44, this.x59.x, this.x59.y, null);
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
        if (!this.x59.inside(n, n2)) {
            return -1;
        }
        n -= this.x59.x;
        n2 -= this.x59.y;
        for (int i = 0; i < this.x53; ++i) {
            if (this.x75[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private void x4() {
        final int width = this.x20.width;
        final int height = this.x20.height;
        if (this.x35 != null) {
            this.x35.flush();
        }
        if (this.x0 != 0 && this.x44 != null) {
            this.x35 = this.createImage(width, height);
            this.x35.getGraphics().drawImage(this.x44, 0, 0, null);
        }
        if (this.x44 != null) {
            this.x44.flush();
        }
        this.x44 = this.createImage(width, height);
        Graphics graphics = this.x44.getGraphics();
        if (this.x38[0] != null && this.x37[0] != null) {
            graphics.drawImage(this.x37[0], 0, 0, null);
        }
        else {
            graphics.setColor(this.x15[0]);
            graphics.fillRect(0, 0, width, height);
        }
        for (int i = 0; i < this.x53; ++i) {
            this.x75[i].reshape(0, 0, 0, 0);
            this.x76[i] = 0;
        }
        int n = 0;
        int x77 = 0;
        int n2 = 0;
        for (short n3 = 0; n3 < this.x53; ++n3) {
            if (this.x5[n3] == 0) {
                ++n2;
                n = 0;
            }
            final int n4 = (n2 - 1) * this.x19.width;
            final int n5 = n * this.x49;
            ++n;
            ++x77;
            int n6 = this.x9[n3] * 3;
            if (this.x11[n3] && this.x7[n3]) {
                n6 += 3;
            }
            final int n7 = n6 + (this.x4[n3] ? 2 : 0);
            this.showStatus(" ");
            if (!this.x56) {
                graphics.drawImage(this.x67[n7], n4 + this.x54, n5 + this.x55, null);
                if (n3 == 0) {
                    System.gc();
                    graphics.drawImage(this.x67[n7], n4 + this.x54, n5 + this.x55, null);
                }
            }
            this.x75[x77 - 1].reshape(n4, (n - 1) * this.x49, this.x19.width, this.x49);
            this.x0(graphics, n3, this.x75[x77 - 1]);
            graphics = this.x44.getGraphics();
            this.x76[x77 - 1] = n3;
            if (this.x11[n3] && !this.x7[n3]) {
                for (short n8 = 0; n8 < this.x10[n3]; ++n8) {
                    if (this.x11[n8 + n3]) {
                        this.x7[n8 + n3] = false;
                    }
                }
                n3 += this.x10[n3];
            }
        }
        this.x77 = x77;
        this.x27 = false;
    }
    
    public mb5trial() {
        this.x48 = -1;
        this.x37 = new Image[11];
        this.x33 = new boolean[4];
        this.x15 = new Color[4];
        this.x57 = -1;
        this.x38 = new String[5];
        this.x66 = new String[10][2];
        this.x25 = -1;
        this.x46 = -1;
        this.x60 = new String[] { "dummy", "copyright", "textNcolour", "textHcolour", "textBcolour", "textScolour", "textAcolour", "textNstyle", "textHstyle", "textfont", "textsize", "textXoffset", "textYoffset", "textposition", "textmargin", "textspacing", "menufile", "menuYoffset", "menuXoffset", "menuclosetrigger", "menuanimspeed", "menudeftarget", "imgtranscol", "imghktranscol", "imgbasefile", "imgfile", "imgwidth", "imgheight", "imgXoffset", "imgYoffset", "entryheight", "frameimage", "framewidth", "frametop", "framebottom", "frameleft", "frameright", "scrollimages", "scrollwidth", "scrollvert", "scrolljump", "bgcolour", "bgimage", "audiomove", "audioclick", "escapepage", "textshadow" };
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        try {
            if (!this.x52 || !this.x34) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            if (x0 == this.x57) {
                return true;
            }
            this.showStatus(" ");
            if (this.x57 != -1) {
                for (int i = 0; i < this.x53; ++i) {
                    this.x4[i] = false;
                }
                this.x57 = -1;
                this.x4();
            }
            if (x0 >= 0 && x0 < this.x53) {
                this.x57 = x0;
                this.showStatus(this.x8[this.x76[x0]]);
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            this.x1(0, true);
            if (this.x57 != -1) {
                for (int i = 0; i < this.x53; ++i) {
                    this.x4[i] = false;
                }
                this.x57 = -1;
                this.x4();
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        try {
            if (!this.x34) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            for (int i = 0; i < this.x53; ++i) {
                this.x4[i] = false;
            }
            if (this.x57 == -1) {
                for (int j = this.x77 - 1; j >= 0; --j) {
                    final int n3 = this.x76[j];
                    if (this.x11[n3] && this.x7[n3]) {
                        this.x2(n3, true);
                    }
                }
            }
            if (x0 == this.x57) {
                return true;
            }
            if (this.x57 != -1) {
                this.x57 = -1;
            }
            this.showStatus(" ");
            if (x0 >= 0 && x0 < this.x53) {
                this.x57 = x0;
                this.showStatus(this.x8[this.x76[x0]]);
                final int n4 = this.x76[x0];
                if (n4 > this.x53 || n4 < 0) {
                    return true;
                }
                this.x4[n4] = true;
                if (this.x11[n4] && this.x5[n4] < 1) {
                    this.x2(n4, true);
                }
                else {
                    this.x4();
                }
                this.repaint();
            }
            System.gc();
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (!this.x34) {
                return true;
            }
            this.x52 = false;
            final int x0 = this.x0(n, n2);
            if (x0 > this.x53 || x0 < 0) {
                return true;
            }
            final int n3 = this.x76[x0];
            if (n3 > this.x53 || n3 < 0) {
                return true;
            }
            this.x57 = x0;
            try {
                if (this.x14.endsWith(".au")) {
                    this.play(this.x17, this.x14);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {}
            this.x0(n3, true);
            if (this.x11[n3] && this.x5[n3] > 0) {
                this.x2(n3, true);
            }
            this.repaint();
        }
        catch (Throwable t) {}
        return true;
    }
    
    private void x2(final int folder, final boolean b) {
        System.gc();
        if (this.x11[folder]) {
            final boolean b2 = this.x7[folder];
            this.x1(folder, b);
            if (!b2) {
                this.x7[folder] = true;
                if (b) {
                    this.x1();
                }
            }
            this.folder = folder;
            this.x27 = false;
            this.x4();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.x61 == 0 && this.x74 && this.x44 != null) {
            this.x0(graphics, this.bounds(), this.x15[0]);
            if (this.x38[1] != null && this.x37[1] != null) {
                graphics.drawImage(this.x37[1], 0, 0, null);
            }
            this.x0(graphics, this.x59);
            graphics.drawImage(this.x44, this.x59.x, this.x59.y, null);
            graphics.drawImage(this.x44, this.x59.x, this.x59.y, null);
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
        if (this.x61 == 1) {
            String s = "Error: " + this.x62;
            if (this.x48 != -1) {
                s = s + " / " + this.x48;
            }
            graphics.drawString(s, 3, 140);
            this.showStatus("Applet reports " + s);
            return;
        }
        graphics.drawString(this.x41 ? "TRIAL DELAY: 3 secs" : ("Loading: " + (12 - this.x42)), 3, 120);
    }
    
    public void run() {
        try {
            this.x3();
        }
        catch (Exception ex2) {
            try {
                this.x3();
            }
            catch (Exception ex) {
                this.x62 = 0;
                System.out.println(ex.toString());
                this.x61 = 1;
            }
        }
        if (this.x61 > 0 && (this.x22 == null || this.x62 < 10)) {
            this.x74 = false;
            if (this.x22 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x22, "_self");
            }
            this.x41 = true;
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
    
    private void x0(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Graphics graphics = this.x43.getGraphics();
        graphics.clipRect(0, n5, n3, n4);
        final int n6 = n3 / n;
        for (int n7 = n4 / n2, i = 0; i <= n7; ++i) {
            for (int j = 0; j <= n6; ++j) {
                graphics.drawImage(image, j * n, i * n2 + n5, null);
            }
        }
    }
    
    private int[] x0(final int[] array, final int n, final Color color, final boolean b) {
        final int[] array2 = new int[n];
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = this.x15[1].getRed();
        final int green2 = this.x15[1].getGreen();
        final int blue2 = this.x15[1].getBlue();
        final int red3 = this.x15[3].getRed();
        final int green3 = this.x15[3].getGreen();
        final int blue3 = this.x15[3].getBlue();
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i] >> 16 & 0xFF;
            final int n3 = array[i] >> 8 & 0xFF;
            final int n4 = array[i] & 0xFF;
            if (Math.abs(n2 - red) < 10 && Math.abs(n3 - green) < 10 && Math.abs(n4 - blue) < 10) {
                array2[i] = (0x0 | red2 << 16 | green2 << 8 | blue2);
            }
            else if (b && Math.abs(n2 - red3) < 10 && Math.abs(n3 - green3) < 10 && Math.abs(n4 - blue3) < 10) {
                array2[i] = (0x1000000 | red2 << 16 | green2 << 8 | blue2);
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
            this.paint(this.x31 = this.x12.getGraphics());
            graphics.drawImage(this.x12, 0, 0, null);
            this.x12.flush();
            this.x31.dispose();
        }
        catch (Throwable t) {
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
