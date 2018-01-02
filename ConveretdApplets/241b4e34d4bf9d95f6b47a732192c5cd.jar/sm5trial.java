import java.util.Random;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Event;
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

public final class sm5trial extends Applet implements Runnable
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
    private Dimension x9;
    private URL x10;
    private String x11;
    private boolean[] x12;
    private int x13;
    private boolean x14;
    private Graphics x15;
    private Graphics x16;
    private Graphics x17;
    private boolean x18;
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
    private Graphics x29;
    private Thread x30;
    private boolean x31;
    private long x32;
    private int x33;
    private String[] x34;
    private int x35;
    private String x36;
    private String[] x37;
    private int x38;
    private int x39;
    private Rectangle x40;
    private int x41;
    private int x42;
    private String[][] x43;
    private Image[] x44;
    private boolean[] x45;
    private Color[][] x46;
    private Font[][] x47;
    private byte[][] x48;
    private int x49;
    private int x50;
    private String x51;
    private long x52;
    private boolean x53;
    private Rectangle[] x54;
    private int[] x55;
    private String[] x56;
    private int x57;
    private byte[] x58;
    private short[] x59;
    private boolean[] x60;
    
    public void clik(final int n) {
        this.x0(n, false);
        this.repaint();
    }
    
    public void init() {
        System.gc();
        final String string = "x.copyright.textNcolour.textHcolour.textBcolour.x.textNstyle.x.textfont.textsize." + "x.x.textposition.textmargin.textspacing.menufile.x.x.x.x.menudeftarget.menustyles." + "x.x.x.imgwidth.imgheight.x.x.entryheight." + "x.x.x.x.x.x.x.x.x.x.bgcolour.x." + "audiomove.audioclick.escapepage.x.textXpress.textYpress";
        this.x37 = new String[50];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
        while (stringTokenizer.hasMoreTokens()) {
            this.x37[n] = stringTokenizer.nextToken();
            ++n;
        }
        this.x6 = this.x0(1, " ");
        this.x51 = this.x0(15, null);
        this.x8 = this.x0(20, "_top");
        this.x42 = this.x0(21, 50, 2, 3, 10);
        this.x5[1] = new Color(this.x0(22, 0, 0, 12632256, 16));
        final int x0 = this.x0(25, 300, 10, 150, 10);
        final int x2 = this.x0(26, 200, 10, 30, 10);
        this.x28 = x2 + this.x0(29, 200, 0, 0, 10);
        this.x9 = new Dimension(x0, x2);
        this.x5[0] = new Color(this.x0(40, 0, 0, 0, 16));
        this.x36 = this.x0(42, " ");
        this.x4 = this.x0(43, " ");
        try {
            this.x7 = this.getDocumentBase();
            this.x3 = this.getCodeBase();
            this.x10 = new URL(this.x7, this.x0(44, null));
        }
        catch (Throwable t) {
            this.x10 = null;
        }
        this.x49 = this.x0(46, 5, -5, 1, 10);
        this.x50 = this.x0(47, 5, -5, 1, 10);
        int n2 = 0;
        do {
            this.x37[0] = "sub" + n2;
            this.x43[n2][0] = this.x0(0, "LINK:");
            this.x43[n2][1] = "sub" + n2 + ":";
        } while (++n2 < 10);
        this.x5[2] = Color.orange;
        this.x47 = new Font[this.x42][2];
        this.x46 = new Color[this.x42][6];
        this.x48 = new byte[this.x42][5];
        int n3 = 0;
        do {
            this.x37[0] = this.x37[2 + n3];
            int n4 = Color.black.getRGB();
            if (n3 == 2) {
                n4 = Color.blue.getRGB();
            }
            if (n3 == 1) {
                n4 = Color.red.getRGB();
            }
            final int x3 = this.x0(0, 0, 0, n4, 16);
            for (int i = 0; i < this.x42; ++i) {
                this.x37[0] = this.x37[2 + n3] + i;
                this.x46[i][n3] = new Color(this.x0(0, 0, 0, x3, 16));
            }
        } while (++n3 < 3);
        final int[] array = new int[5];
        final int x4 = this.x0(6, 3, 0, 0, 10);
        final String x5 = this.x0(8, "TimesRoman");
        final int x6 = this.x0(9, 30, 6, 11, 10);
        int n5 = 0;
        do {
            array[n5] = this.x0(10 + n5, 50, -50, 0, 10);
        } while (++n5 < 4);
        array[4] = this.x0(14, 50, 0, x6, 10);
        for (int j = 0; j < this.x42; ++j) {
            this.x37[0] = this.x37[8] + j;
            final String x7 = this.x0(0, x5);
            this.x37[0] = this.x37[9] + j;
            final int x8 = this.x0(0, 30, 6, x6, 10);
            int n6 = 0;
            do {
                this.x37[0] = this.x37[6] + j;
                this.x47[j][n6] = new Font(x7, this.x0(0, 3, 0, x4, 10), x8);
            } while (++n6 < 2);
            int n7 = 0;
            do {
                this.x37[0] = this.x37[10 + n7] + j;
                if (n7 == 2) {
                    this.x48[j][n7] = (byte)this.x0(0, 2, 0, array[n7], 10);
                }
                else if (n7 == 4) {
                    this.x48[j][n7] = (byte)this.x0(0, 50, 0, array[n7], 10);
                }
                else {
                    this.x48[j][n7] = (byte)this.x0(0, 50, -50, array[n7], 10);
                }
            } while (++n7 < 5);
        }
        final int width = this.bounds().width;
        final int height = this.bounds().height;
        this.x40 = new Rectangle(0, 0, width, this.x28);
        this.x19 = this.createImage(width, this.x28);
        this.x16 = this.x19.getGraphics();
        this.x22 = this.createImage(width, height);
        this.x17 = this.x22.getGraphics();
        this.x29 = this.getGraphics();
        System.gc();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (!this.x18) {
                return true;
            }
            this.x31 = true;
            final int x0 = this.x0(n, n2);
            if (x0 >= 0 && x0 < this.x33) {
                this.x35 = x0;
                this.x0(1);
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        try {
            if (!this.x31 || !this.x18) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            if (x0 == this.x35) {
                return true;
            }
            this.showStatus(" ");
            if (this.x35 != -1) {
                this.x0(0);
                this.x35 = -1;
            }
            if (x0 >= 0 && x0 < this.x33) {
                this.x35 = x0;
                this.showStatus(this.x56[this.x55[x0]]);
                this.x0(1);
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            if (this.x35 != -1) {
                this.x0(0);
                this.x35 = -1;
            }
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        try {
            if (!this.x18) {
                return true;
            }
            final int x0 = this.x0(n, n2);
            if (x0 == this.x35) {
                return true;
            }
            this.showStatus(" ");
            if (this.x35 != -1) {
                this.x0(0);
                this.x35 = -1;
            }
            if (x0 >= 0 && x0 < this.x33) {
                this.x35 = x0;
                this.showStatus(this.x56[this.x55[x0]]);
                this.x0(2);
            }
            System.gc();
        }
        catch (Throwable t) {}
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (!this.x18) {
                return true;
            }
            this.x31 = false;
            final int x0 = this.x0(n, n2);
            if (x0 > this.x33 || x0 < 0) {
                return true;
            }
            final int n3 = this.x55[x0];
            if (n3 > this.x33 || n3 < 0) {
                return true;
            }
            this.x35 = x0;
            try {
                if (this.x4.endsWith(".au")) {
                    this.play(this.x7, this.x4);
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
        if (this.x38 == 0 && this.x53 && this.x22 != null) {
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
        if (this.x38 == 1) {
            String s = "Error: " + this.x39;
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
            this.x2();
        }
        catch (Exception ex2) {
            try {
                this.x2();
            }
            catch (Exception ex) {
                this.x39 = 0;
                System.out.println(ex.toString());
                this.x38 = 1;
            }
        }
        if (this.x38 > 0 && (this.x10 == null || this.x39 < 10)) {
            this.x53 = false;
            if (this.x10 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x10, "_self");
            }
            this.x20 = true;
            this.stop();
        }
        System.gc();
        final long n = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("JVM memory used before initialisation: " + (int)this.x32 / 1000 + " KB");
        System.out.println("JVM memory now used: " + (int)n / 1000 + " KB");
        System.out.println("Initialisation time: " + (System.currentTimeMillis() - this.x52) + " milliseconds");
    }
    
    public sm5trial() {
        this.x27 = -1;
        this.x5 = new Color[3];
        this.x43 = new String[10][2];
        this.x13 = -1;
        this.x25 = -1;
        System.gc();
        this.x32 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        this.x52 = System.currentTimeMillis();
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
    
    public void update(final Graphics graphics) {
        try {
            this.x2 = this.createImage(this.size().width, this.size().height);
            this.paint(this.x15 = this.x2.getGraphics());
            graphics.drawImage(this.x2, 0, 0, null);
            this.x2.flush();
            this.x15.dispose();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
        }
    }
    
    private void x0(final int folder, final boolean b) {
        if ((int)(8.0 * Math.random()) == 2) {
            this.x53 = false;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(3000L);
            }
            catch (Exception ex) {}
            this.x53 = true;
            this.paint(this.getGraphics());
        }
        System.gc();
        if (this.x60[folder]) {
            final boolean b2 = this.x45[folder];
            this.x1(folder, b);
            if (!b2) {
                this.x45[folder] = true;
                if (b) {
                    this.x1();
                }
            }
            this.folder = folder;
            this.x14 = false;
            this.x3();
        }
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x0[folder], ";");
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken().trim();
            int n = s.length();
            if (s.toLowerCase().startsWith("audio")) {
                try {
                    this.play(this.x7, s.substring(6, n).trim());
                }
                catch (OutOfMemoryError outOfMemoryError) {}
            }
            if (s.toLowerCase().startsWith("sub")) {
                int n2 = 0;
                do {
                    if (s.toLowerCase().startsWith(this.x43[n2][1])) {
                        s = this.x43[n2][0] + s.substring(5, n);
                        n = s.length();
                    }
                } while (++n2 < 10);
            }
            if (s.toLowerCase().startsWith("link")) {
                int index = s.indexOf(",");
                String s2;
                if (index == -1 || n - index < 2) {
                    s2 = this.x8;
                    index = n;
                }
                else {
                    s2 = s.substring(index + 1, n).trim();
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
                if (!this.x60[folder] || this.x45[folder]) {
                    this.getAppletContext().showDocument(url, s2);
                }
                this.x3();
                if (b) {
                    this.repaint();
                }
            }
            if (s.toLowerCase().startsWith("drill")) {
                final String x51 = this.x51;
                final int folder2 = this.folder;
                try {
                    if (this.x51.equalsIgnoreCase(this.x11)) {
                        this.x13 = this.folder;
                    }
                }
                catch (Exception ex4) {}
                this.x51 = s.substring(6, n).trim();
                this.x18 = false;
                this.x20 = false;
                this.x2();
                if (this.x38 > 0 && (this.x10 == null || this.x39 < 10)) {
                    this.x51 = x51;
                    this.x18 = false;
                    this.x20 = false;
                    this.x2();
                }
                try {
                    if (this.x38 == 0 && this.x51.equalsIgnoreCase(this.x11)) {
                        this.clik(this.x13);
                    }
                    else if (this.x38 == 0 && this.x51.equalsIgnoreCase(this.x24)) {
                        this.clik(this.x25);
                    }
                }
                catch (Exception ex5) {}
                this.x24 = x51;
                this.x25 = folder2;
                if (this.x38 <= 0 || (this.x10 != null && this.x39 >= 10)) {
                    continue;
                }
                this.x53 = false;
                if (this.x10 == null) {
                    this.repaint();
                }
                else {
                    this.getAppletContext().showDocument(this.x10, "_self");
                }
                this.x20 = true;
                this.stop();
            }
        }
    }
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x37[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private void x0() {
        System.gc();
        this.repaint();
    }
    
    private void x0(final Graphics graphics, final int n, final Rectangle rectangle, final boolean b) {
        final String s = this.x34[n];
        if (s == null || s.length() <= 0) {
            return;
        }
        final byte b2 = this.x58[n];
        final int n2 = this.x12[n] ? 1 : 0;
        int x = rectangle.x;
        int n3 = rectangle.y + rectangle.height / 2;
        if (b) {
            n3 += this.x50;
            x += this.x49;
        }
        final byte b3 = this.x48[b2][2];
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
        graphics.setColor(this.x46[b2][2]);
        if (n2 == 1) {
            graphics.setColor(this.x46[b2][2].brighter());
        }
        final int x2 = rectangle.x;
        final int y = rectangle.y;
        int width = this.x9.width;
        int height = this.x9.height;
        int n4 = y + (rectangle.height - height) / 2;
        int n5 = x2 + (rectangle.width - width) / 2;
        graphics.fill3DRect(n5, n4, width, height, !b);
        if (rectangle.height > 24) {
            n5 += 3;
            n4 += 3;
            width -= 6;
            height -= 6;
            graphics.fill3DRect(n5, n4, width, height, b);
            ++n5;
            ++n4;
            width -= 2;
            height -= 2;
            graphics.fill3DRect(n5, n4, width, height, !b);
        }
        final Font font = this.x47[b2][n2];
        graphics.setFont(font);
        graphics.setColor(this.x46[b2][n2]);
        if (b3 == 2) {
            final int n6 = font.getSize() / 2;
            if (this.x60[n]) {
                for (byte b4 = 0; b4 < n6; ++b4) {
                    if (this.x45[n]) {
                        graphics.drawLine(x + n6 - b4 + 3, n3 - b4 + 2, x + n6 + b4 + 3, n3 - b4 + 2);
                    }
                    else {
                        graphics.drawLine(x + n6 * 2 - b4, n3 - b4, x + n6 * 2 - b4, n3 + b4);
                    }
                }
            }
            else {
                byte b5 = 0;
                do {
                    final int min = Math.min(2, (b5 < 2) ? (-b5 - 1) : (5 - b5));
                    graphics.drawLine(x + 7 + b5, n3 - min, x + 7 + b5, n3 + min);
                } while (++b5 < 5);
            }
        }
        if (substring != null) {
            final int n7 = this.x48[b2][3] + ((b3 == 2) ? (font.getSize() + 2) : 0);
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
            int n8 = x + this.x48[b2][0];
            final int n9 = n3 + (this.x48[b2][1] + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2);
            if (b3 == 0) {
                n8 += rectangle.width / 2 - fontMetrics.stringWidth(substring) / 2;
            }
            if (b3 == 2) {
                n8 += n7;
            }
            if (b3 == 1) {
                n8 += rectangle.width - fontMetrics.stringWidth(substring) - n7;
            }
            graphics.drawString(substring, n8, n9);
        }
    }
    
    private boolean x0(final Graphics graphics) {
        final String[][] array = new String[1000][5];
        if (this.x51 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            URL url;
            try {
                url = new URL(this.x3, this.x51);
            }
            catch (Exception ex) {
                this.x39 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.x39 = 4;
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
                this.x39 = 5;
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
            this.x33 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.x27 = this.x33;
                if (this.x33 >= 998) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.x10 == null) {
                        this.x39 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.x33;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.x33;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.x10 == null) {
                            this.x39 = 13;
                            return false;
                        }
                        ++this.x33;
                        n = 0;
                    }
                    array[this.x33][n] = streamTokenizer.sval;
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
            this.x33 = 0;
            while (!b2) {
                if (this.x33 >= 998) {
                    b2 = true;
                }
                this.x37[0] = "entry" + this.x33;
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
                        array[this.x33][n2] = nextToken2;
                    } while (++n2 < 5);
                }
                ++this.x33;
            }
            --this.x33;
        }
        ++this.x21;
        this.x0();
        if (this.x33 >= 200 && this.x10 == null) {
            this.x39 = 15;
            this.x27 = this.x33;
            return false;
        }
        for (int i = 0; i < this.x33; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex9) {
                if (this.x10 == null) {
                    this.x39 = 10;
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
        array[this.x33][1] = "0";
        ++this.x21;
        this.x0();
        this.x34 = new String[this.x33 + 1];
        this.x56 = new String[this.x33 + 1];
        this.x0 = new String[this.x33 + 1];
        this.x60 = new boolean[this.x33 + 1];
        this.x45 = new boolean[this.x33 + 1];
        this.x1 = new boolean[this.x33 + 1];
        this.x59 = new short[this.x33 + 1];
        this.x23 = new byte[this.x33 + 1];
        this.x58 = new byte[this.x33 + 1];
        this.x12 = new boolean[this.x33 + 1];
        this.x54 = new Rectangle[this.x33 + 1];
        this.x55 = new int[this.x33 + 1];
        for (int j = 0; j < this.x33; ++j) {
            this.x1[j] = false;
            this.x54[j] = new Rectangle(0, 0, 0, 0);
        }
        int max = 1;
        for (int k = 0; k < this.x33; ++k) {
            this.x27 = k;
            this.x12[k] = false;
            this.x34[k] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                if (this.x10 == null) {
                    this.x39 = 10;
                    return false;
                }
                max = 1;
            }
            this.x23[k] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x60[k] = true;
                this.x58[k] = 0;
                this.x45[k] = false;
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x33; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n4 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.x59[k] = (short)(l - k - 1);
                this.x1[n4] = true;
            }
            else {
                this.x60[k] = false;
                this.x58[k] = 2;
            }
            this.x0[k] = array[k][3];
            if (this.x0[k].toLowerCase().startsWith("start") || this.x0[k].toLowerCase().indexOf(";start") != -1) {
                this.x41 = k;
            }
            final int int2 = Integer.parseInt(array[k][2]);
            if (int2 > 0 && int2 < this.x42) {
                this.x58[k] = (byte)int2;
            }
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x56[k] = array[k][4];
            }
            else {
                this.x56[k] = " ";
            }
        }
        return true;
    }
    
    private void x0(final Graphics graphics, final Image image, final int n, final int n2) {
        if (image != null) {
            graphics.drawImage(image, n, n2, null);
            graphics.drawImage(image, n, n2, null);
        }
    }
    
    private void x0(final int n) {
        final int x35 = this.x35;
        if (x35 > this.x33 || x35 < 0) {
            return;
        }
        final int n2 = this.x55[x35];
        this.x12[n2] = (n == 2);
        final Rectangle rectangle = this.x54[x35];
        this.x0(this.x16, this.x40, this.x5[0]);
        this.x0(this.x16, n2, this.x40, n == 1);
        final Graphics graphics = this.getGraphics();
        this.x0(graphics, this.x19, rectangle.x, rectangle.y);
        graphics.dispose();
    }
    
    private void x0(final Graphics graphics, final Rectangle rectangle, final Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private int x0(final int n, final int n2) {
        for (int i = 0; i < this.x33; ++i) {
            if (this.x54[i].inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x37[n]);
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
    
    private void x1() {
        this.x3();
        this.repaint();
        this.x18 = true;
    }
    
    private void x1(final int n, final boolean b) {
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
        for (int j = this.x57 - 1; j >= 0; --j) {
            final int n2 = this.x55[j];
            if (this.x60[n2] && this.x45[n2]) {
                boolean b5 = false;
                for (byte b6 = 0; b6 < b4; ++b6) {
                    if (n2 == array[b6]) {
                        b5 = true;
                    }
                }
                if (!b5) {
                    this.x45[n2] = false;
                    this.x35 = j;
                    if (b) {
                        this.x1();
                    }
                }
            }
        }
        for (int k = this.x57 - 1; k >= 0; --k) {
            if (this.x55[k] == n) {
                this.x35 = k;
            }
        }
    }
    
    private void x2() {
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
            this.x53 = false;
            char c = '\0';
            final char c2 = '\0';
            this.x35 = 0;
            this.folder = 0;
            this.x38 = ((this.x10 == null) ? 2 : 3);
            this.x21 = 1;
            this.x0();
            this.x41 = -1;
            String string = "";
            for (int j = 0; j < n; ++j) {
                string += this.x26[j];
            }
            for (char c3 = '\0'; c3 < string.length(); ++c3) {
                c += (char)(string.charAt(c3) * c3);
            }
            if (!this.x6.equalsIgnoreCase(this.x26[0])) {
                this.x39 = 9;
                this.x38 = 1;
                return;
            }
            if (c != 1448044) {
                this.x39 = 30;
                this.x38 = 1;
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
                        this.x39 = 2;
                        this.x38 = 1;
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
                this.x39 = 2;
                this.x38 = 1;
                return;
            }
            this.x21 = 2;
            this.x0();
            try {
                if (!this.x0(graphics) && !this.x0(graphics)) {
                    this.x38 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x39 = 6;
                this.x38 = 1;
                return;
            }
            this.x21 = 8;
            this.x0();
            this.x53 = true;
            try {
                this.x3();
                if (this.x22 == null) {
                    this.x3();
                }
                if (this.x22 == null) {
                    this.x39 = 8;
                    this.x38 = 1;
                    return;
                }
                if (this.x57 > 50) {
                    this.x1(0, false);
                    this.x3();
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
                this.x39 = 8;
                this.x38 = 1;
                return;
            }
            this.x21 = 12;
            this.x0();
            this.x20 = true;
            this.x18 = true;
            this.x38 = 0;
            if (this.x41 >= 0 && this.x41 < this.x33) {
                this.x0(this.x41, false);
            }
        }
        if (this.x53 && this.x22 != null) {
            this.x0(graphics, this.bounds(), this.x5[0]);
            this.x0(graphics, this.x22, 0, 0);
            this.repaint();
        }
    }
    
    private void x3() {
        final Graphics x17 = this.x17;
        this.x0(x17, this.bounds(), this.x5[0]);
        for (int i = 0; i < this.x33; ++i) {
            this.x54[i].reshape(0, 0, 0, 0);
            this.x55[i] = 0;
        }
        int x18 = 0;
        for (short n = 0; n < this.x33; ++n) {
            final int n2 = x18 * this.x28;
            if (this.x12[n]) {
                this.x12[n] = false;
            }
            this.x54[x18].reshape(0, n2, this.bounds().width, this.x28);
            this.x0(x17, n, this.x54[x18], false);
            this.x55[x18] = n;
            ++x18;
            if (this.x60[n] && !this.x45[n]) {
                for (short n3 = 0; n3 < this.x59[n]; ++n3) {
                    if (this.x60[n3 + n]) {
                        this.x45[n3 + n] = false;
                    }
                }
                n += this.x59[n];
            }
        }
        this.x57 = x18;
        this.x14 = false;
    }
}
