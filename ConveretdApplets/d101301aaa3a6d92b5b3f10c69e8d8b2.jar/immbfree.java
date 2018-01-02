import java.awt.Event;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class immbfree extends Applet implements Runnable
{
    private Image m0;
    private String[] m1;
    private boolean[] m2;
    private byte[] m3;
    private String[] m4;
    private String[] m5;
    private int[] m6;
    private int m7;
    private int[] m8;
    private boolean[] m9;
    private Image m10;
    private URL m11;
    private Color[] m12;
    private String m13;
    private URL m14;
    private String m15;
    private int m16;
    private URL m17;
    private Graphics m18;
    private Graphics m19;
    private boolean m20;
    private boolean m21;
    private String[] m22;
    private int m23;
    private int m24;
    private Thread m25;
    private int m26;
    private int m27;
    private int m28;
    private int m29;
    private int m30;
    private int[] m31;
    private Image[] m32;
    private Rectangle[][] m33;
    private int[] m34;
    private Image[] m35;
    private int m36;
    private Rectangle[] m37;
    private int[] m38;
    private int m39;
    private int[][] m40;
    private int m41;
    private String[] m42;
    private int m43;
    private int m44;
    private int m45;
    private Color[] m46;
    private Font m47;
    private String m48;
    private String m49;
    private String m50;
    private boolean m51;
    
    private void m0(final Rectangle rectangle) {
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        final int width2 = this.m37[1].width;
        final int height2 = this.m37[1].height;
        final boolean[] array = new boolean[4];
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        final int[] array4 = new int[4];
        int n = 0;
        do {
            array[n] = true;
        } while (++n < 4);
        array3[0] = rectangle.y;
        array3[1] = height - rectangle.height - rectangle.y;
        array3[3] = (array3[2] = height);
        array2[1] = (array2[0] = width);
        array2[2] = rectangle.x;
        array2[3] = width - rectangle.width - rectangle.x;
        array4[0] = rectangle.y;
        array4[1] = height - rectangle.height - rectangle.y;
        array4[2] = rectangle.x;
        array4[3] = width - rectangle.width - rectangle.x;
        int n2 = 0;
        do {
            if (height2 > array3[n2]) {
                array[n2] = false;
            }
            if (width2 > array2[n2]) {
                array[n2] = false;
            }
        } while (++n2 < 4);
        int n3 = 0;
        int n4 = 0;
        do {
            if (array[n4]) {
                ++n3;
            }
        } while (++n4 < 4);
        int m16 = 0;
        if (n3 > 0) {
            int n5 = 20000;
            int n6 = 0;
            do {
                if (array[n6] && array4[n6] < n5) {
                    n5 = array4[n6];
                    m16 = n6;
                }
            } while (++n6 < 4);
        }
        else {
            int n7 = 0;
            int n8 = 0;
            do {
                if (array2[n8] * array3[n8] > n7) {
                    n7 = array2[n8] * array3[n8];
                    m16 = n8;
                }
            } while (++n8 < 4);
        }
        if (this.m16 > -1 && this.m16 <= 3) {
            m16 = this.m16;
        }
        this.m45 = m16;
        int x = rectangle.x;
        int y = rectangle.y;
        if (m16 == 0 || m16 == 1) {
            if (width2 > width - x) {
                x = width - width2;
            }
            y += ((m16 == 0) ? (-height2) : rectangle.height);
        }
        if (m16 == 2 || m16 == 3) {
            if (height2 > height - y) {
                y = height - height2;
            }
            x += ((m16 == 2) ? (-width2) : rectangle.width);
        }
        this.m37[1].move(x, y);
    }
    
    private int m0(final int[] array, final int n) {
        final Image image = this.createImage(10, 10);
        final Graphics graphics = image.getGraphics();
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += this.m0(graphics, array[i], 0, 0, 4, false, this.m24) + 2;
        }
        n2 += 12;
        image.flush();
        graphics.dispose();
        return n2;
    }
    
    private void m0(final int n) {
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.m1[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            final int length = trim.length();
            if (trim.toLowerCase().startsWith("link")) {
                int index = trim.indexOf(",");
                String s;
                if (index == -1 || length - index < 2) {
                    s = this.m15;
                    index = length;
                }
                else {
                    s = trim.substring(index + 1, length).trim();
                }
                final String trim2 = trim.substring(5, index).trim();
                try {
                    url = new URL(this.m14, trim2);
                }
                catch (Exception ex) {
                    if (trim2.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.m14, "mailto.htm");
                        }
                        catch (Exception ex2) {}
                    }
                    else {
                        System.out.println("Link failed: " + trim2);
                    }
                }
                this.getAppletContext().showDocument(url, s);
            }
        }
    }
    
    public void destroy() {
    }
    
    private void m0(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(this.m12[0]);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(this.m12[5]);
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n, n2, n, n2 + n4 - 1);
        graphics.setColor(this.m12[3]);
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 1, n2 + 1);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 2);
        graphics.setColor(this.m12[4]);
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + 1);
        graphics.setColor(this.m12[1]);
        graphics.drawLine(n, n2 + n4 - 1, n + n3, n2 + n4 - 1);
        graphics.drawLine(n + n3, n2 + n4 - 1, n + n3, n2);
    }
    
    private void m0(final int n, final Rectangle rectangle) {
        if (this.m43 == 1) {
            return;
        }
        final int n2 = this.m31[1];
        final int n3 = this.m38[n2];
        final int m24 = this.m24;
        final int m25 = this.m0(this.m40[n2], n3);
        final int n4 = m24 + 6;
        this.m35[1] = this.createImage(m25 + 1, n4);
        final Graphics graphics = this.m35[1].getGraphics();
        this.m32[1] = this.createImage(m25 + 1, n4);
        final Graphics graphics2 = this.m32[1].getGraphics();
        this.m0(graphics, 0, 0, m25, n4);
        final Graphics graphics3 = this.m35[1].getGraphics();
        graphics2.drawImage(this.m35[1], 0, 0, null);
        int n5 = 4;
        for (int i = 0; i < n3; ++i) {
            final int n6 = this.m40[n2][i];
            int n7;
            if (this.m4[n6].toLowerCase().indexOf("separator:") != -1) {
                this.m0(graphics3, n5 + 2, 3, m24 - 1);
                this.m0(graphics2, n5 + 2, 3, m24 - 1);
                n7 = n5 + 6;
            }
            else {
                this.m0(graphics3, n6, n5, m24 + 2, m25, false, m24);
                n7 = this.m0(graphics2, n6, n5, m24 + 2, m25, true, m24) + 2;
            }
            this.m33[1][i] = new Rectangle(n5, 3, n7 - n5, m24 + 1);
            n5 = n7;
        }
    }
    
    private void m0(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(this.m12[0].darker());
        graphics.drawLine(n, n2, n, n2 + n3);
        graphics.setColor(this.m12[0].brighter());
        graphics.drawLine(n + 1, n2, n + 1, n2 + n3);
    }
    
    private int m0(final Graphics graphics, final int n, int n2, int n3, final int n4, final boolean b, final int n5) {
        final String s = this.m4[n];
        if (s == null || s.length() <= 0) {
            return 0;
        }
        final int n6 = (this.m3[n] == 0) ? 1 : 0;
        final int n7 = b ? 1 : 0;
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
        if (substring == null || substring.length() < 1) {
            return 0;
        }
        final Font font = (n6 != 0) ? new Font(this.m47.getName(), 1, this.m47.getSize()) : this.m47;
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
        final int n8 = fontMetrics.getAscent() - fontMetrics.getDescent();
        final int stringWidth = fontMetrics.stringWidth(substring);
        final int n9 = (n6 != 0) ? 4 : 12;
        if (n7 == 1 && !this.m46[2].equals(this.m12[0])) {
            graphics.setColor(this.m46[2]);
            graphics.fillRect(n2, n3 - n5, (n6 != 0) ? (n4 - n2 - 2) : (stringWidth + n9 + 2), n5 + n6);
        }
        if (n6 == 0) {
            graphics.setColor(this.m46[n7]);
            int n10 = 0;
            do {
                final int min = Math.min(2, (n10 < 2) ? (-n10 - 1) : (5 - n10));
                graphics.drawLine(n2 + 3 + n10, n3 - n5 / 2 - min, n2 + 3 + n10, n3 - n5 / 2 + min);
            } while (++n10 < 5);
        }
        n2 += n9;
        graphics.setFont(font);
        graphics.setColor(this.m46[n7]);
        if (n6 != 0) {
            --n3;
        }
        graphics.drawString(substring, n2, n3 - (n5 - n8) / 2);
        return n2 + stringWidth;
    }
    
    private int m0(final int n, final int n2, final boolean b) {
        try {
            if (n2 == -1) {
                int n3 = 0;
                for (int i = 0; i < this.m7; ++i) {
                    if (this.m3[i] == 0) {
                        if (b) {
                            this.m40[n][n3] = i;
                        }
                        ++n3;
                    }
                }
                this.m38[n] = n3;
            }
            else {
                if (!this.m9[n2]) {
                    return -1;
                }
                this.m38[n] = this.m8[n2];
                if (this.m38[n] == 0) {
                    return -1;
                }
                int n4 = 0;
                int n5 = n2 + 1;
                final byte b2 = (byte)(this.m3[n2] + 1);
                final int n6 = n5;
                for (int n7 = n6 + this.m38[n], j = n6; j < n7; ++j) {
                    if (this.m3[j] == b2) {
                        if (b) {
                            this.m40[n][n4] = n5;
                        }
                        ++n4;
                    }
                    ++n5;
                }
                this.m38[n] = n4;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            return -1;
        }
        return this.m38[n];
    }
    
    private void m0() {
        final Image image = this.createImage(this.m37[1].width, this.m37[1].height);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.m35[1], 0, 0, null);
        if (this.m29 >= 0) {
            final Rectangle rectangle = this.m33[1][this.m29];
            graphics.clipRect(rectangle.x - this.m37[1].x, rectangle.y - this.m37[1].y, rectangle.width, rectangle.height);
            graphics.drawImage(this.m32[1], 0, 0, null);
        }
        this.m19.drawImage(image, this.m37[1].x, this.m37[1].y, null);
    }
    
    private byte[] m0(final String s) {
        final byte[] array = new byte[20000];
        try {
            if (s != null) {
                final DataInputStream dataInputStream = new DataInputStream(new URL(this.m11, s).openStream());
                try {
                    dataInputStream.readFully(array);
                }
                catch (EOFException ex) {}
                try {
                    dataInputStream.close();
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex3) {
            return null;
        }
        return array;
    }
    
    private boolean m1() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (this.m49 != null) {
            try {
                mediaTracker.addImage(this.m35[0] = this.getImage(this.getCodeBase(), this.m49), 0);
            }
            catch (Exception ex) {}
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.m44 = 1;
            return false;
        }
        if (mediaTracker.isErrorID(0)) {
            this.m44 = 20;
            return false;
        }
        System.out.println("Image loaded: " + this.m49);
        if (this.m35[0] == null) {
            return false;
        }
        final int width = this.m35[0].getWidth(null);
        final int height = this.m35[0].getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.m35[0], 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex3) {}
        final Image image = this.createImage(new MemoryImageSource(width, height, array, 0, width));
        this.m35[0] = this.createImage(width, height);
        this.m35[0].getGraphics().drawImage(image, 0, 0, null);
        return true;
    }
    
    private boolean m2() {
        final byte[] array = new byte[20000];
        final byte[] m0 = this.m0(this.m50);
        if (m0 == null) {
            this.m44 = 5;
            return false;
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(m0);
        final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
        int n = 0;
        final String[] array2 = new String[1000];
        for (int n2 = 0; n < 1000 && n2 < 1000; ++n2) {
            try {
                streamTokenizer.nextToken();
                if (streamTokenizer.sval != null) {
                    ++n;
                    array2[n] = streamTokenizer.sval;
                }
            }
            catch (Exception ex) {}
        }
        int n3 = 0;
        final int[] array3 = new int[4];
        int n4 = 0;
        do {
            try {
                if (array2[n4].equalsIgnoreCase("coords")) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(array2[n4 + 1], ",");
                    for (int n5 = 0; n5 < 4 && stringTokenizer.hasMoreTokens(); ++n5) {
                        array3[n5] = Integer.parseInt(stringTokenizer.nextToken());
                    }
                    this.m33[0][n3] = new Rectangle(array3[0], array3[1], array3[2] - array3[0], array3[3] - array3[1]);
                    ++n3;
                }
            }
            catch (Exception ex2) {}
        } while (++n4 < 1000);
        this.m38[0] = n3;
        try {
            byteArrayInputStream.close();
        }
        catch (Exception ex3) {}
        System.out.println("Map items read: " + n3 + " from " + this.m50);
        return true;
    }
    
    private boolean m3() {
        final String[][] array = new String[60][5];
        if (this.m48 != null) {
            final byte[] array2 = new byte[20000];
            final byte[] m0 = this.m0(this.m48);
            if (m0 == null) {
                this.m44 = 4;
                return false;
            }
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(m0);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.m7 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.m23 = this.m7;
                if (this.m7 >= 4998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex) {
                    if (this.m17 == null) {
                        this.m44 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.m7;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.m7;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.m17 == null) {
                            this.m44 = 13;
                            return false;
                        }
                        ++this.m7;
                        n = 0;
                    }
                    array[this.m7][n] = streamTokenizer.sval;
                    ++n;
                }
            }
            try {
                byteArrayInputStream.close();
            }
            catch (Exception ex2) {}
        }
        System.out.println("Menu length: " + this.m7);
        for (int i = 0; i < this.m7; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex3) {
                if (this.m17 == null) {
                    this.m44 = 10;
                    this.m23 = i;
                    return false;
                }
                array[i][1] = "1";
            }
            try {
                Integer.parseInt(array[i][2]);
            }
            catch (NumberFormatException ex4) {
                array[i][2] = "0";
            }
        }
        array[this.m7][1] = "0";
        System.gc();
        this.m4 = new String[this.m7 + 1];
        this.m5 = new String[this.m7 + 1];
        this.m1 = new String[this.m7 + 1];
        this.m9 = new boolean[this.m7 + 1];
        this.m2 = new boolean[this.m7 + 1];
        this.m8 = new int[this.m7 + 1];
        this.m6 = new int[this.m7 + 1];
        this.m3 = new byte[this.m7 + 1];
        for (int j = 0; j < this.m7; ++j) {
            this.m2[j] = false;
        }
        int max = 1;
        for (int k = 0; k < this.m7; ++k) {
            this.m23 = k;
            this.m4[k] = array[k][0].trim();
            final int n2 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n2 > 1 || max > 50) {
                max = 1;
            }
            this.m3[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.m9[k] = true;
                int n3 = 0;
                int l;
                for (l = k + 1; l < this.m7; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n3 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.m8[k] = l - k - 1;
                this.m2[n3] = true;
            }
            else {
                this.m9[k] = false;
            }
            this.m1[k] = array[k][3];
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.m5[k] = array[k][4];
            }
            else {
                this.m5[k] = " ";
            }
        }
        this.m41 = 1;
        this.m40 = new int[10][10];
        this.m38 = new int[1000];
        this.m36 = this.m0(0, -1, false);
        for (int n4 = 0; n4 < this.m7; ++n4) {
            if (this.m9[n4]) {
                ++this.m41;
                this.m36 = Math.max(this.m36, this.m0(0, n4, false));
            }
        }
        this.m40 = new int[this.m41 + 1][this.m36 + 1];
        this.m38 = new int[this.m41 + 1];
        this.m33 = new Rectangle[3][this.m36 + 1];
        this.m0(this.m41 = 0, -1, true);
        this.m34[0] = -1;
        for (int n5 = 0; n5 < this.m7; ++n5) {
            if (this.m9[n5]) {
                ++this.m41;
                this.m6[n5] = this.m41;
                this.m0(this.m41, n5, true);
            }
            else {
                this.m6[n5] = -1;
            }
        }
        System.out.println("Extracted " + this.m41 + " panels with maximum size " + this.m36);
        return true;
    }
    
    private int m0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.m42[n]);
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
    
    private String m0(final int n, final String s) {
        final String parameter = this.getParameter(this.m42[n]);
        return (parameter != null) ? parameter : s;
    }
    
    public boolean handleEvent(final Event event) {
        if (!this.m20) {
            return true;
        }
        this.m20 = false;
        final int x = event.x;
        final int y = event.y;
        try {
            this.m0(x, y);
            final int m30 = (this.m28 >= 0 && this.m29 >= 0) ? this.m40[this.m31[this.m28]][this.m29] : -1;
            final boolean b = m30 >= 0 && m30 < this.m7;
            final boolean b2 = this.m29 != this.m27;
            if (event.id == 502 && b) {
                this.m0(m30);
                this.repaint();
            }
            if (event.id == 503 || event.id == 504 || event.id == 505) {
                if (this.m39 == 2 && b2 && b) {
                    if (this.m28 == 1) {
                        this.m0();
                    }
                    if (this.m30 != m30) {
                        this.showStatus(this.m5[m30]);
                    }
                    this.m30 = m30;
                }
                if (this.m39 == 1 && this.m28 == 0 && b) {
                    this.m1(m30);
                }
                else if (this.m39 == 2 && this.m28 < 1) {
                    if (!b2) {
                        this.m29 = -1;
                        this.m0();
                    }
                    else if (this.m29 != this.m34[1] || this.m29 < 0) {
                        this.showStatus("");
                        this.update(this.m19);
                        this.m31[1] = -1;
                        this.m37[1] = null;
                        this.m39 = 1;
                        this.m34[1] = -1;
                    }
                }
            }
            if (event.id == 505) {
                this.repaint();
                this.m31[1] = -1;
                this.m37[1] = null;
                this.m39 = 1;
                this.m34[1] = -1;
                this.m28 = -1;
                this.m29 = -1;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return this.m20 = true;
    }
    
    public immbfree() {
        this.m16 = -1;
        this.m12 = new Color[7];
        this.m23 = -1;
        this.m31 = new int[3];
        this.m39 = 1;
        this.m34 = new int[3];
        this.m35 = new Image[3];
        this.m32 = new Image[3];
        this.m37 = new Rectangle[3];
        this.m28 = -1;
        this.m29 = -1;
        this.m26 = -1;
        this.m27 = -1;
        this.m46 = new Color[3];
        this.m30 = -1;
        this.m42 = new String[] { "escapepage", "copyright", "hue", "saturation", "contrast", "textstyle", "textfont", "textsize", "bgcolour", "showtitles", "menufile", "mapfile", "imagefile", "direction" };
    }
    
    public void init() {
        final int m0 = this.m0(2, 100, -1, -1, 10);
        final int m2 = this.m0(3, 100, 0, 50, 10);
        final int m3 = this.m0(4, 100, 0, 80, 10);
        float n;
        if (m0 < 0 || m0 > 100) {
            n = (float)Math.random();
        }
        else {
            n = m0 / 100.0f;
        }
        final float[] array = new float[6];
        int n2 = 0;
        do {
            final float n3 = (100 - m2) * n2 * 2.0f / 100.0f + 1.0f;
            array[n2] = ((m2 != 0) ? (1.0f / n3) : 0.0f);
        } while (++n2 < 6);
        final float n4 = m3 * 1.25f / 100.0f;
        this.m12[5] = Color.getHSBColor(n, array[5], 1.0f - 0.01f * n4);
        this.m12[3] = Color.getHSBColor(n, array[4], 1.0f - 0.1333333f * n4);
        this.m12[0] = Color.getHSBColor(n, array[3], 1.0f - 0.2f * n4);
        this.m12[4] = Color.getHSBColor(n, array[2], 1.0f - 0.4f * n4);
        this.m46[0] = Color.getHSBColor(n, array[1], 1.0f - 0.6f * n4);
        this.m12[1] = Color.getHSBColor(n, array[0], 1.0f - 0.8f * n4);
        this.m46[1] = this.m12[5];
        this.m46[2] = this.m12[4];
        this.m12[6] = new Color(this.m0(8, 0, 0, 0, 16));
        this.m13 = this.m0(1, " ");
        this.m15 = "_top";
        this.m24 = this.m0(7, 30, 6, 11, 10);
        this.m47 = new Font(this.m0(6, "TimesRoman"), this.m0(5, 3, 0, 0, 10), this.m24);
        this.m24 += 3;
        try {
            this.m14 = this.getDocumentBase();
            this.m11 = this.getCodeBase();
            this.m17 = new URL(this.m14, this.m0(0, (String)null));
        }
        catch (Throwable t) {
            this.m17 = null;
        }
        this.m12[2] = Color.magenta;
        this.m48 = this.m0(10, (String)null);
        this.m50 = this.m0(11, (String)null);
        this.m49 = this.m0(12, (String)null);
        this.m16 = this.m0(13, 3, -1, -1, 10);
        this.m19 = this.getGraphics();
        this.m37[0] = new Rectangle(0, 0, this.bounds().width, this.bounds().height);
    }
    
    private void m4() {
        final int n = 9;
        (this.m22 = new String[n])[0] = "Image Intelligence Ltd. 1999 (www.imint.com)";
        this.m22[1] = "iMMap Mini Bar 1.0";
        this.m22[2] = "© Image Intelligence Ltd.";
        this.m22[3] = "http://www.imint.com";
        this.m22[4] = "     For private non-commercial use only     ";
        this.m22[5] = "             APPLET BY IMINT.COM             ";
        this.m22[6] = " Please wait - menu loading for this website ";
        this.m22[7] = "© IMINT.COM";
        this.m22[8] = "Loading...";
        int n2 = 1;
        do {
            System.out.println(this.m22[n2]);
        } while (++n2 < 5);
        final String[] array = { this.m22[6], this.m22[5], this.m22[4] };
        final long currentTimeMillis = System.currentTimeMillis();
        this.m0(this.getGraphics(), array, true);
        System.gc();
        this.m51 = false;
        char c = '\0';
        this.m43 = 2;
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += this.m22[i];
        }
        for (char c2 = '\0'; c2 < string.length(); ++c2) {
            c += (char)(string.charAt(c2) * c2);
        }
        if (!this.m13.equalsIgnoreCase(this.m22[0])) {
            this.m44 = 9;
            this.m43 = 1;
            return;
        }
        if (c != 2682124) {
            this.m44 = 30;
            this.m43 = 1;
            System.out.println("E30");
            return;
        }
        try {
            if (!this.m3() && !this.m3()) {
                this.m43 = 1;
                return;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            this.m44 = 6;
            this.m43 = 1;
            return;
        }
        if (this.m7 > 101) {
            this.m44 = 6;
            this.m43 = 1;
            return;
        }
        try {
            if (!this.m2()) {
                this.m44 = 7;
                this.m43 = 1;
                return;
            }
        }
        catch (Exception ex2) {
            System.out.println(ex2);
            this.m44 = 7;
            this.m43 = 1;
            return;
        }
        try {
            if (!this.m1()) {
                this.m44 = 8;
                this.m43 = 1;
                return;
            }
        }
        catch (Exception ex3) {
            System.out.println(ex3);
            this.m44 = 8;
            this.m43 = 1;
            return;
        }
        try {
            Thread.sleep(7000L + currentTimeMillis - System.currentTimeMillis());
        }
        catch (Exception ex4) {}
        this.m0(this.getGraphics(), array, false);
        System.gc();
        this.m51 = true;
        this.m43 = 0;
        this.repaint();
        this.m20 = true;
    }
    
    private void m0(final int n, final int n2) {
        this.m26 = this.m28;
        this.m27 = this.m29;
        this.m28 = -1;
        this.m29 = -1;
        for (int i = this.m39; i >= 0; --i) {
            if (this.m37[i] != null && this.m28 == -1 && this.m37[i].inside(n, n2)) {
                this.m28 = i;
            }
        }
        if (this.m28 >= 0) {
            for (int j = 0; j < this.m38[this.m31[this.m28]]; ++j) {
                if (this.m33[this.m28][j] != null && this.m29 == -1 && this.m33[this.m28][j].inside(n, n2)) {
                    this.m29 = j;
                }
            }
        }
    }
    
    private synchronized void m1(final int n) {
        if (this.m29 >= 0 && n >= 0 && n < this.m7) {
            this.m31[1] = this.m6[n];
            this.m39 = 2;
            final Rectangle rectangle = this.m33[this.m28][this.m29];
            this.m34[1] = this.m29;
            final int width = rectangle.width;
            final int height = rectangle.height;
            final Image image = this.createImage(width, height);
            final Graphics graphics = image.getGraphics();
            this.m0(graphics, 0, 0, width, height);
            graphics.clipRect(3, 3, width - 6, height - 6);
            graphics.drawImage(this.m35[0], -rectangle.x, -rectangle.y, null);
            this.m19.drawImage(image, rectangle.x, rectangle.y, null);
            this.m0(n, rectangle);
            final int width2 = this.m35[1].getWidth(null);
            final int height2 = this.m35[1].getHeight(null);
            this.m37[1] = new Rectangle(0, 0, width2, height2);
            this.m0(rectangle);
            int x = this.m37[1].x;
            int y = this.m37[1].y;
            for (int i = 0; i < this.m38[this.m31[1]]; ++i) {
                this.m33[1][i].translate(x, y);
            }
            int n2 = 1;
            do {
                final Image image2 = this.createImage(width2 * n2 / 20, height2 * n2 / 20);
                image2.getGraphics().drawImage(this.m35[1], 0, 0, width2 * n2 / 20, height2 * n2 / 20, null);
                if (this.m45 == 0) {
                    y = rectangle.y - height2 * n2 / 20;
                }
                if (this.m45 == 2) {
                    x = rectangle.x - width2 * n2 / 20;
                }
                this.m19.drawImage(image2, x, y, null);
                try {
                    Thread.sleep(5L);
                }
                catch (Exception ex) {}
            } while (++n2 < 21);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.m12[6]);
        graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
        if (!this.m51) {
            if (this.m0 != null) {
                graphics.drawImage(this.m0, 0, 0, null);
            }
            return;
        }
        if (this.m35[0] != null) {
            graphics.drawImage(this.m35[0], 0, 0, null);
        }
        System.gc();
    }
    
    public void run() {
        this.m19.setColor(this.m12[6]);
        this.m19.fillRect(0, 0, this.bounds().width, this.bounds().height);
        if (this.bounds().height < 50 || this.bounds().width < 400) {
            this.m19.setColor(Color.red);
            this.m19.drawString("Applet too small", 0, 20);
            return;
        }
        try {
            this.m4();
        }
        catch (Exception ex2) {
            try {
                this.m4();
            }
            catch (Exception ex) {
                this.m44 = 0;
                System.out.println(ex.toString());
                this.m43 = 1;
            }
        }
        System.gc();
        if (this.m43 > 0 && (this.m17 == null || this.m44 < 10)) {
            this.m19.setColor(Color.red);
            this.m19.drawString("E" + this.m44, 0, 12);
            this.m51 = false;
            if (this.m17 != null) {
                this.getAppletContext().showDocument(this.m17, "_self");
            }
            this.stop();
        }
    }
    
    private void m0(final Graphics graphics, final String[] array, final boolean b) {
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        final Font[] array2 = { new Font("TimesRoman", 3, 12), new Font("TimesRoman", 1, 24), new Font("TimesRoman", 3, 12) };
        final int[] array3 = new int[3];
        final int[] array4 = new int[3];
        final int[][] array5 = new int[3][200];
        final Color[][] array6 = new Color[3][200];
        final String[][] array7 = new String[3][200];
        int n = 0;
        do {
            int n2 = 0;
            array3[n] = array[n].length();
            graphics.setFont(array2[n]);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            array4[n] = (width - fontMetrics.stringWidth(array[n])) / 2;
            for (int i = 0; i < array3[n]; ++i) {
                array7[n][i] = array[n].substring(i, i + 1);
                array5[n][i] = n2;
                n2 += fontMetrics.stringWidth(array[n].substring(i, i + 1));
                float n3 = i / array3[n];
                if (n == 1) {
                    n3 *= 3.0f;
                }
                if (n == 2) {
                    n3 = n3 * 1.2f - 0.1f;
                }
                while (n3 >= 1.0f) {
                    --n3;
                }
                array6[n][i] = Color.getHSBColor(n3, 1.0f, 1.0f);
            }
        } while (++n < 3);
        this.m0 = this.createImage(width, height);
        final Graphics graphics2 = this.m0.getGraphics();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        do {
            final long currentTimeMillis = System.currentTimeMillis();
            graphics2.setColor(Color.black);
            graphics2.fillRect(0, 0, width, height);
            int n7 = 0;
            do {
                graphics2.setFont(array2[n7]);
                int n8 = 0;
                if (n7 == 0) {
                    n8 = 11;
                }
                if (n7 == 1) {
                    n8 = 34;
                }
                if (n7 == 2) {
                    n8 = 46;
                }
                for (int j = 0; j < array3[n7]; ++j) {
                    int n9;
                    if (b) {
                        n9 = Math.min(array5[n7][j], n6 - (array3[n7] - j) * 50);
                    }
                    else {
                        n9 = Math.max(array5[n7][j], n6 - (array3[n7] - j) * 50 + width);
                    }
                    graphics2.setColor(array6[n7][j]);
                    graphics2.drawString(array7[n7][j], array4[n7] + n9, n8);
                }
            } while (++n7 < 3);
            graphics.drawImage(this.m0, 0, 0, null);
            final long n10 = System.currentTimeMillis() - currentTimeMillis;
            ++n4;
            n5 += (int)n10;
            n6 += Math.max(n5 / n4, 4);
        } while (++n6 < 2500);
    }
    
    public void start() {
        if (this.m25 == null) {
            (this.m25 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m25 != null) {
            this.m25.stop();
            this.m25 = null;
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            this.m10 = this.createImage(this.size().width, this.size().height);
            this.paint(this.m18 = this.m10.getGraphics());
            graphics.drawImage(this.m10, 0, 0, null);
            this.m10.flush();
            this.m18.dispose();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
    }
}
