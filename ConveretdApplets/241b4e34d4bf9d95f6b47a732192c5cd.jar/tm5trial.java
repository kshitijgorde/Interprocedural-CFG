import java.util.Random;
import java.net.InetAddress;
import java.awt.FontMetrics;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Event;
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

public final class tm5trial extends Applet implements Runnable
{
    public int folder;
    private String[] x0;
    private boolean[] x1;
    private Image x2;
    private URL x3;
    private String x4;
    private Color[] x5;
    private String x6;
    private URL x7;
    private String x8;
    private URL x9;
    private String x10;
    private int x11;
    private byte[] x12;
    private boolean x13;
    private Graphics x14;
    private Graphics x15;
    private Graphics x16;
    private boolean x17;
    private int x18;
    private Image x19;
    private boolean x20;
    private int x21;
    private Image x22;
    private byte[] x23;
    private String x24;
    private int x25;
    private String[] x26;
    private int x27;
    private int x28;
    private int x29;
    private Thread x30;
    private long x31;
    private int x32;
    private int x33;
    private String[] x34;
    private String x35;
    private int x36;
    private int x37;
    private String[] x38;
    private int x39;
    private int x40;
    private Rectangle x41;
    private int x42;
    private int x43;
    private String[][] x44;
    private boolean[] x45;
    private Color[][] x46;
    private Font[][] x47;
    private String x48;
    private long x49;
    private boolean x50;
    private Rectangle[] x51;
    private int[] x52;
    private int x53;
    private int x54;
    private String[] x55;
    private byte[] x56;
    private short[] x57;
    private boolean[] x58;
    
    public void clik(final int n) {
        this.x0(n, false);
        this.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        try {
            if (!this.x17) {
                return true;
            }
            int x33 = -1;
            for (int i = 0; i < this.x32; ++i) {
                if (this.x51[i].inside(event.x, event.y)) {
                    x33 = i;
                }
            }
            if (event.id == 502) {
                if (x33 > this.x32 || x33 < 0) {
                    return true;
                }
                final int n = this.x52[x33];
                if (n > this.x32 || n < 0) {
                    return true;
                }
                try {
                    if (this.x4.endsWith(".au")) {
                        this.play(this.x7, this.x4);
                    }
                }
                catch (OutOfMemoryError outOfMemoryError) {}
                this.x0(n, true);
            }
            if (event.id == 503) {
                if (x33 == this.x33) {
                    return true;
                }
                this.showStatus(" ");
                if (this.x35.endsWith(".au")) {
                    this.play(this.x7, this.x35);
                }
                if (this.x33 != -1) {
                    this.x2(this.x33, false);
                    this.x33 = -1;
                }
                if (x33 >= 0 && x33 < this.x32) {
                    this.x33 = x33;
                    this.showStatus(this.x55[this.x52[x33]]);
                    this.x2(this.x33, true);
                }
                System.gc();
            }
            if (event.id == 505 && this.x33 != -1) {
                this.x33 = -1;
                this.repaint();
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public void init() {
        System.gc();
        final String string = "x.copyright.textNcolour.textAcolour.textVcolour.textHcolour.textBcolour.x.textNstyle.x.textfont.textsize." + "x.x.menufile.x.x.x.x.menudeftarget.x.menustyles." + "x.x.imgwidth.imgheight.x.x.entryindent.entryheight." + "x.x.x.x.x.x.x.x.x.x.x.bgcolour.x." + "audiomove.audioclick.startlevel.escapepage.x.x";
        this.x38 = new String[50];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
        while (stringTokenizer.hasMoreTokens()) {
            this.x38[n] = stringTokenizer.nextToken();
            ++n;
        }
        this.x6 = this.x0(1, " ");
        this.x48 = this.x0(14, null);
        this.x8 = this.x0(19, "_top");
        this.x43 = this.x0(21, 50, 2, 3, 10);
        this.x54 = this.x0(24, 300, 10, 13, 10);
        this.x18 = this.x0(25, 50, 10, 14, 10);
        this.x28 = this.x0(28, 50, 0, 12, 10);
        this.x29 = this.x18 + this.x0(29, 50, -50, 0, 10);
        this.x5[0] = new Color(this.x0(41, 0, 0, 0, 16));
        this.x35 = this.x0(43, " ");
        this.x4 = this.x0(44, " ");
        this.x36 = this.x0(45, 49, 0, 2, 10);
        try {
            this.x7 = this.getDocumentBase();
            this.x3 = this.getCodeBase();
            this.x9 = new URL(this.x7, this.x0(46, null));
        }
        catch (Throwable t) {
            this.x9 = null;
        }
        int n2 = 0;
        do {
            this.x38[0] = "sub" + n2;
            this.x44[n2][0] = this.x0(0, "LINK:");
            this.x44[n2][1] = "sub" + n2 + ":";
        } while (++n2 < 10);
        this.x5[2] = Color.orange;
        this.x47 = new Font[this.x43][2];
        this.x46 = new Color[this.x43][5];
        int n3 = 0;
        do {
            this.x38[0] = this.x38[2 + n3];
            int n4 = Color.black.getRGB();
            if (n3 == 4) {
                n4 = this.x5[0].getRGB();
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
            final int x0 = this.x0(0, 0, 0, n4, 16);
            for (int i = 0; i < this.x43; ++i) {
                this.x38[0] = this.x38[2 + n3] + i;
                this.x46[i][n3] = new Color(this.x0(0, 0, 0, x0, 16));
            }
        } while (++n3 < 5);
        final int x2 = this.x0(8, 3, 0, 0, 10);
        final String x3 = this.x0(10, "TimesRoman");
        final int x4 = this.x0(11, 30, 6, 11, 10);
        for (int j = 0; j < this.x43; ++j) {
            this.x38[0] = this.x38[10] + j;
            final String x5 = this.x0(0, x3);
            this.x38[0] = this.x38[11] + j;
            final int x6 = this.x0(0, 30, 6, x4, 10);
            int n5 = 0;
            do {
                this.x38[0] = this.x38[8] + j;
                this.x47[j][n5] = new Font(x5, this.x0(0, 3, 0, (j == 0) ? 1 : x2, 10), x6);
            } while (++n5 < 2);
        }
        final int width = this.bounds().width;
        this.x41 = new Rectangle(0, 0, width, this.x29);
        this.x19 = this.createImage(width, this.x29);
        this.x15 = this.x19.getGraphics();
        this.x22 = this.createImage(width, this.bounds().height);
        this.x16 = this.x22.getGraphics();
        System.gc();
    }
    
    public void paint(final Graphics graphics) {
        if (this.x39 == 0 && this.x50 && this.x22 != null) {
            this.x0(graphics, this.bounds(), this.x5[0]);
            this.x0(graphics, this.x22, 0, 0);
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.setColor(Color.black);
        try {
            graphics.drawString(this.x26[1], 3, 28);
            graphics.drawString(this.x26[2], 3, 42);
            graphics.drawString(this.x26[3], 3, 56);
            graphics.drawString(this.x26[4], 3, 70);
            graphics.drawString(this.x26[5], 3, 84);
            graphics.drawString(this.x26[6], 3, 98);
            graphics.drawString(this.x26[7], 3, 14);
        }
        catch (Exception ex) {}
        if (this.x39 == 1) {
            String s = "Error: " + this.x40;
            if (this.x27 != -1) {
                s = s + " / " + this.x27;
            }
            graphics.drawString(s, 3, 140);
            this.showStatus("Applet reports " + s);
            return;
        }
        graphics.drawString((this.x20 ? "TRIAL DELAY... " : "Loading: ") + (12 - this.x21), 3, 120);
    }
    
    public void run() {
        try {
            this.x1();
        }
        catch (Exception ex2) {
            try {
                this.x1();
            }
            catch (Exception ex) {
                this.x40 = 0;
                System.out.println(ex.toString());
                this.x39 = 1;
            }
        }
        if (this.x39 > 0 && (this.x9 == null || this.x40 < 10)) {
            this.x50 = false;
            if (this.x9 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x9, "_self");
            }
            this.x20 = true;
            this.stop();
        }
        System.gc();
        final long n = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("JVM memory used before initialisation: " + (int)this.x31 / 1000 + " KB");
        System.out.println("JVM memory now used: " + (int)n / 1000 + " KB");
        System.out.println("Initialisation time: " + (System.currentTimeMillis() - this.x49) + " milliseconds");
    }
    
    public void start() {
        if (this.x30 == null) {
            (this.x30 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.x30 != null) {
            this.x30.stop();
            this.x30 = null;
        }
    }
    
    public tm5trial() {
        this.x27 = -1;
        this.x5 = new Color[4];
        this.x44 = new String[10][2];
        this.x11 = -1;
        this.x25 = -1;
        System.gc();
        this.x31 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        this.x49 = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
        try {
            this.x2 = this.createImage(this.size().width, this.size().height);
            this.paint(this.x14 = this.x2.getGraphics());
            graphics.drawImage(this.x2, 0, 0, null);
            this.x2.flush();
            this.x14.dispose();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
    }
    
    private void x0() {
        System.gc();
        this.repaint();
    }
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x38[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x38[n]);
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
    
    private boolean x0(final Graphics graphics) {
        final String[][] array = new String[1000][5];
        if (this.x48 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            URL url;
            try {
                url = new URL(this.x3, this.x48);
            }
            catch (Exception ex) {
                this.x40 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.x40 = 4;
                return false;
            }
            ++this.x21;
            this.x0();
            final byte[] array2 = new byte[100000];
            try {
                try {
                    dataInputStream.readFully(array2);
                }
                catch (EOFException ex3) {}
            }
            catch (IOException ex4) {
                this.x40 = 5;
                return false;
            }
            try {
                dataInputStream.close();
            }
            catch (IOException ex5) {}
            System.out.println("Index download time = " + (System.currentTimeMillis() - currentTimeMillis));
            ++this.x21;
            this.x0();
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array2);
            final StreamTokenizer streamTokenizer = new StreamTokenizer(byteArrayInputStream);
            int n = 0;
            boolean b = false;
            this.x32 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x27 = this.x32;
                if (this.x32 >= 998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.x9 == null) {
                        this.x40 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.x32;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x32;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x9 == null) {
                            this.x40 = 13;
                            return false;
                        }
                        ++this.x32;
                        n = 0;
                    }
                    array[this.x32][n] = streamTokenizer.sval;
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
            this.x32 = 0;
            while (!b2) {
                if (this.x32 >= 998) {
                    b2 = true;
                }
                this.x38[0] = "entry" + this.x32;
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
                        array[this.x32][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x32;
            }
            --this.x32;
        }
        ++this.x21;
        this.x0();
        if (this.x32 >= 200 && this.x9 == null) {
            this.x40 = 15;
            this.x27 = this.x32;
            return false;
        }
        for (int i = 0; i < this.x32; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x9 == null) {
                    this.x40 = 10;
                    this.x27 = i;
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
        array[this.x32][1] = "0";
        ++this.x21;
        this.x0();
        this.x34 = new String[this.x32 + 1];
        this.x55 = new String[this.x32 + 1];
        this.x0 = new String[this.x32 + 1];
        this.x58 = new boolean[this.x32 + 1];
        this.x45 = new boolean[this.x32 + 1];
        this.x1 = new boolean[this.x32 + 1];
        this.x57 = new short[this.x32 + 1];
        this.x23 = new byte[this.x32 + 1];
        this.x56 = new byte[this.x32 + 1];
        this.x12 = new byte[this.x32 + 1];
        this.x51 = new Rectangle[this.x32 + 1];
        this.x52 = new int[this.x32 + 1];
        for (int j = 0; j < this.x32; ++j) {
            this.x1[j] = false;
            this.x51[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x32; ++k) {
            this.x27 = k;
            this.x12[k] = 0;
            this.x34[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x9 == null) {
                    this.x40 = 10;
                    return false;
                }
                max = 1;
            }
            this.x23[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x58[k] = true;
                this.x56[k] = 0;
                this.x45[k] = (max <= this.x36);
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x32; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n4 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.x57[k] = (short)(l - k - 1);
                this.x1[n4] = true;
            }
            else {
                this.x58[k] = false;
                this.x56[k] = 1;
            }
            this.x0[k] = array[k][3];
            if (this.x0[k].toLowerCase().startsWith("start") || this.x0[k].toLowerCase().indexOf(";start") != -1) {
                this.x42 = k;
            }
            final int int2 = Integer.parseInt(array[k][2]);
            if (int2 > 0 && int2 < this.x43) {
                this.x56[k] = (byte)int2;
            }
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x55[k] = array[k][4];
            }
            else {
                this.x55[k] = " ";
            }
        }
        return true;
    }
    
    private void x0(final Graphics graphics, final int n, final int n2, final int n3, final boolean b) {
        final String s = this.x34[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final byte b2 = this.x56[n];
        final byte b3 = this.x12[n];
        final byte b4 = (byte)(b ? 3 : b3);
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
        final Font font = this.x47[b2][b];
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
        final int descent = fontMetrics.getDescent();
        final int n4 = (substring != null) ? fontMetrics.stringWidth(substring) : 0;
        final Color color = this.x46[b2][4];
        if (b && !color.equals(this.x5[0]) && substring != null) {
            graphics.setColor(color);
            graphics.fillRect((b2 == 4) ? (n2 + 16) : (n2 - 2), 0, (b2 != 4) ? (n4 + 22) : (n4 + 4), this.x41.height);
        }
        graphics.setColor(this.x46[b2][b4]);
        final int n5 = this.x54 / 2;
        final int n6 = (this.x18 - 3) / 2;
        final int n7 = n3 - this.x29 / 2 + n6;
        if (this.x58[n]) {
            for (int i = 0; i < n6; ++i) {
                if (this.x45[n]) {
                    graphics.drawLine(n2 + n5 - i + 3, n7 - i - 2, n2 + n5 + i + 3, n7 - i - 2);
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
        if (substring != null) {
            graphics.setFont(font);
            graphics.drawString(substring, n2 + 18, n3 - descent);
        }
    }
    
    private void x0(final int n, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x50 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(3000L);
            }
            catch (Exception ex) {}
            this.x50 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        if (this.x58[n]) {
            final boolean b2 = this.x45[n];
            this.x1(n, b);
            if (!b2) {
                this.x45[n] = true;
                if (b) {
                    this.x17 = false;
                    this.x2();
                    this.repaint();
                    this.x17 = true;
                }
            }
            this.folder = n;
            this.x13 = false;
            this.x2();
        }
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x0[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken().trim();
            int n2 = s.length();
            if (s.toLowerCase().startsWith("audio")) {
                try {
                    this.play(this.x7, s.substring(6, n2).trim());
                }
                catch (OutOfMemoryError outOfMemoryError) {}
            }
            if (s.toLowerCase().startsWith("sub")) {
                int n3 = 0;
                do {
                    if (s.toLowerCase().startsWith(this.x44[n3][1])) {
                        s = this.x44[n3][0] + s.substring(5, n2);
                        n2 = s.length();
                    }
                } while (++n3 < 10);
            }
            if (s.toLowerCase().startsWith("link")) {
                int index = s.indexOf(",");
                String s2;
                if (index == -1 || n2 - index < 2) {
                    s2 = this.x8;
                    index = n2;
                }
                else {
                    s2 = s.substring(index + 1, n2).trim();
                }
                final String trim = s.substring(5, index).trim();
                try {
                    url = new URL(this.x7, trim);
                }
                catch (Exception ex2) {
                    if (trim.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.x7, "mailto.htm");
                        }
                        catch (Exception ex3) {}
                    }
                    else {
                        System.out.println("Link failed: " + trim);
                    }
                }
                if (!this.x58[n] || this.x45[n]) {
                    this.getAppletContext().showDocument(url, s2);
                }
                if (this.x37 != -1) {
                    this.x12[this.x37] = 2;
                }
                this.x12[n] = 1;
                this.x37 = n;
                this.x2();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x48 = this.x48;
                final int folder = this.folder;
                try {
                    if (this.x48.equalsIgnoreCase(this.x10)) {
                        this.x11 = this.folder;
                    }
                }
                catch (Exception ex4) {}
                this.x48 = s.substring(6, n2).trim();
                this.x17 = false;
                this.x20 = false;
                this.x1();
                if (this.x39 > 0 && (this.x9 == null || this.x40 < 10)) {
                    this.x48 = x48;
                    this.x17 = false;
                    this.x20 = false;
                    this.x1();
                }
                try {
                    if (this.x39 == 0 && this.x48.equalsIgnoreCase(this.x10)) {
                        this.clik(this.x11);
                    }
                    else if (this.x39 == 0 && this.x48.equalsIgnoreCase(this.x24)) {
                        this.clik(this.x25);
                    }
                }
                catch (Exception ex5) {}
                this.x24 = x48;
                this.x25 = folder;
                if (this.x39 <= 0 || (this.x9 != null && this.x40 >= 10)) {
                    continue;
                }
                this.x50 = false;
                if (this.x9 == null) {
                    this.repaint();
                }
                else {
                    this.getAppletContext().showDocument(this.x9, "_self");
                }
                this.x20 = true;
                this.stop();
            }
        }
    }
    
    private void x0(final Graphics graphics, final Image image, final int n, final int n2) {
        if (image != null) {
            graphics.drawImage(image, n, n2, null);
            graphics.drawImage(image, n, n2, null);
        }
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void x1() {
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.x20) {
            final int n = 8;
            (this.x26 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x26[1] = "This copyright display";
            this.x26[2] = "is not shown in the";
            this.x26[3] = "purchasable version.";
            this.x26[4] = "Navigation Applet";
            this.x26[5] = "© Image Intelligence Ltd.";
            this.x26[6] = "www.imint.com";
            this.x26[7] = "TRIAL VERSION ONLY";
            for (int i = 1; i < n; ++i) {
                System.out.println(this.x26[i]);
            }
            this.x0(graphics, this.bounds(), this.x5[0]);
            this.x50 = false;
            char c = '\0';
            final char c2 = '\0';
            this.x33 = 0;
            this.folder = 0;
            this.x37 = -1;
            this.x39 = ((this.x9 == null) ? 2 : 3);
            this.x21 = 1;
            this.x0();
            this.x42 = -1;
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.x26[j];
            }
            for (char c3 = '\0'; c3 < string.length(); ++c3) {
                c += (char)(string.charAt(c3) * c3);
            }
            if (!this.x6.equalsIgnoreCase(this.x26[0])) {
                this.x40 = 9;
                this.x39 = 1;
                return;
            }
            if (c != 1448044) {
                this.x40 = 30;
                this.x39 = 1;
                return;
            }
            final String[] array2;
            final String[] array = array2 = new String[] { this.x3.getProtocol().toString().toLowerCase(), this.x3.getHost().toString().toLowerCase(), null, null };
            final int n2 = 1;
            array2[n2] += this.x3.getFile().toString().toLowerCase();
            try {
                array[2] = InetAddress.getByName(this.x3.getHost()).toString();
                array[3] = array[2].substring(array[2].indexOf("/") + 1);
            }
            catch (Exception ex3) {
                array[2] = "unavailable";
                array[3] = "...";
            }
            System.out.println(array[1]);
            System.out.println(array[2]);
            System.out.println(array[3]);
            final String[] array3 = array;
            final int n3 = 0;
            array3[n3] += array[0];
            char c4 = '\0';
            final int n4 = 5;
            final int n5 = 91;
            for (char c5 = c2; c5 < array[0].length(); ++c5) {
                c4 += (char)(array[0].charAt(c5) * c5);
            }
            if (c4 == n5 << n4) {}
            int n6 = 1;
            final int n7 = 3;
            final String[] array4 = new String[n7];
            final boolean[] array5 = new boolean[n7];
            array4[0] = "a8p4wafmqf";
            array5[0] = true;
            array4[1] = "ao3kbti65j";
            array5[1] = false;
            array4[2] = "bcvyih7izj";
            array5[2] = true;
            for (int k = 0; k < n7; ++k) {
                final String s = array5[k] ? array[3] : array[1];
                final char c6 = (char)(array4[k].charAt(0) + array4[k].charAt(array4[k].length() - 1) - '¾');
                if (c6 >= '\u0006' && c6 <= s.length() && this.x3.toString().length() >= c6 + '\u0007') {
                    if (!this.x7.toString().startsWith(this.x3.toString().substring(0, c6 + '\u0007'))) {
                        this.x40 = 2;
                        this.x39 = 1;
                        return;
                    }
                    int n8 = 0;
                    int n9 = 0;
                    final Random random = new Random(this.x5[2].getRGB());
                    for (char c7 = '\0'; c7 < c6; ++c7) {
                        n8 += s.charAt(c7) * (int)(random.nextDouble() * 100.0) * 17;
                    }
                    for (char c8 = '\0'; c8 < c6; ++c8) {
                        n9 += s.charAt(c6 - c8 - '\u0001') * (int)(random.nextDouble() * 100.0) * 23;
                    }
                    if (array4[k].equalsIgnoreCase(array4[k].charAt(0) + Integer.toString(n8, 36) + Integer.toString(n9, 36) + array4[k].charAt(array4[k].length() - 1))) {
                        n6 = 1;
                    }
                }
            }
            if (n6 != 1) {
                this.x40 = 2;
                this.x39 = 1;
                return;
            }
            this.x21 = 2;
            this.x0();
            try {
                if (!this.x0(graphics) && !this.x0(graphics)) {
                    this.x39 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x40 = 6;
                this.x39 = 1;
                return;
            }
            this.x21 = 8;
            this.x0();
            this.x50 = true;
            try {
                this.x2();
                if (this.x22 == null) {
                    this.x2();
                }
                if (this.x22 == null) {
                    this.x40 = 8;
                    this.x39 = 1;
                    return;
                }
                if (this.x53 > 50) {
                    this.x1(0, false);
                    this.x2();
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.x40 = 8;
                this.x39 = 1;
                return;
            }
            this.x21 = 12;
            this.x0();
            this.x20 = true;
            this.x17 = true;
            this.x39 = 0;
            if (this.x42 >= 0 && this.x42 < this.x32) {
                this.x0(this.x42, false);
            }
        }
        if (this.x50 && this.x22 != null) {
            this.x0(graphics, this.bounds(), this.x5[0]);
            this.x0(graphics, this.x22, 0, 0);
            this.repaint();
        }
    }
    
    private void x1(final int n, boolean b) {
        byte b2 = this.x23[n];
        final int[] array = new int[b2];
        final byte b3 = b2;
        --b2;
        for (int i = n; i >= 0; --i) {
            if (this.x23[i] == b2) {
                array[b2] = i;
                --b2;
            }
        }
        final byte b4 = b3;
        boolean b5 = false;
        for (int j = 0; j < this.x53; ++j) {
            if (n == this.x52[j]) {
                b5 = true;
            }
        }
        if (!b5) {
            for (int k = 0; k <= n; ++k) {
                if (this.x58[k]) {
                    this.x45[k] = true;
                }
            }
            this.x2();
            b = false;
        }
        for (int l = this.x53 - 1; l >= 0; --l) {
            final int n2 = this.x52[l];
            if (this.x58[n2] && this.x45[n2]) {
                boolean b6 = false;
                for (byte b7 = 0; b7 < b4; ++b7) {
                    if (n2 == array[b7]) {
                        b6 = true;
                    }
                }
                if (!b6) {
                    this.x45[n2] = false;
                    this.x33 = l;
                    if (b && this.x32 < 200) {
                        this.x17 = false;
                        this.x2();
                        this.repaint();
                        this.x17 = true;
                    }
                }
            }
        }
        for (int x33 = this.x53 - 1; x33 >= 0; --x33) {
            if (this.x52[x33] == n) {
                this.x33 = x33;
            }
        }
    }
    
    private void x2() {
        final Graphics x16 = this.x16;
        this.x0(x16, this.bounds(), this.x5[0]);
        int x17 = 0;
        for (int i = 0; i < this.x32; ++i) {
            this.x51[i].reshape(0, 0, 0, 0);
            this.x52[i] = 0;
        }
        for (short n = 0; n < this.x32; ++n) {
            final int n2 = this.x23[n] * this.x28 + 18;
            final int n3 = x17 * this.x29;
            this.x0(x16, n, n2, n3 + this.x29, false);
            this.x51[x17].reshape(0, n3, this.bounds().width, this.x29);
            this.x52[x17] = n;
            ++x17;
            if (this.x58[n] && !this.x45[n]) {
                for (short n4 = 0; n4 < this.x57[n]; ++n4) {
                    if (this.x58[n4 + n]) {
                        this.x45[n4 + n] = false;
                    }
                }
                n += this.x57[n];
            }
        }
        this.x53 = x17;
        this.x13 = false;
    }
    
    private void x2(final int n, final boolean b) {
        if (n > 499 || n < 0 || !this.x50) {
            return;
        }
        final int n2 = this.x52[n];
        this.x0(this.x15, this.x41, this.x5[0]);
        this.x0(this.x15, n2, 18, this.x29, b);
        final Rectangle rectangle = this.x51[n];
        final Graphics graphics = this.getGraphics();
        this.x0(graphics, this.x19, rectangle.x + this.x23[n2] * this.x28, rectangle.y);
        graphics.dispose();
    }
}
