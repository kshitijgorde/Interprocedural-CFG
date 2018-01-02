import java.awt.Cursor;
import java.net.InetAddress;
import java.awt.Event;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
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

public final class imsmfree extends Applet implements Runnable
{
    private boolean l0;
    private String[] l1;
    private String[] l2;
    private String[] l3;
    private Image l4;
    private URL l5;
    private String[] l6;
    private Color[] l7;
    private String l8;
    private URL l9;
    private String l10;
    private URL l11;
    private int l12;
    private Graphics l13;
    private Graphics l14;
    private int l15;
    private int l16;
    private boolean l17;
    private int[] l18;
    private Image l19;
    private Image l20;
    private int l21;
    private boolean l22;
    private Image l23;
    private String[] l24;
    private int l25;
    public Thread m_tree;
    private int l26;
    private int l27;
    private String[] l28;
    private int[][] l29;
    private int l30;
    private int l31;
    private Rectangle l32;
    private Rectangle l33;
    private Color[] l34;
    private Font l35;
    private String l36;
    private int l37;
    private boolean l38;
    private int l39;
    
    public void click(final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.l1[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            final int length = trim.length();
            if (trim.toLowerCase().startsWith("link")) {
                int index = trim.indexOf(",");
                String s;
                if (index == -1 || length - index < 2) {
                    s = this.l10;
                    index = length;
                }
                else {
                    s = trim.substring(index + 1, length).trim();
                }
                final String trim2 = trim.substring(5, index).trim();
                try {
                    this.getAppletContext().showDocument(new URL(this.l9, trim2), s);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private void l0(final int n) {
        this.l14.setColor(this.l7[0]);
        this.l14.fillRect(0, 0, this.l33.width, this.l33.height);
        if (n < -1) {
            return;
        }
        try {
            String s = "";
            if (n >= 0) {
                final String s2 = this.l2[n];
                if (s2 == null || s2.length() <= 0) {
                    return;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, ";");
                while (stringTokenizer.hasMoreTokens()) {
                    if (this.l0(stringTokenizer.nextToken().trim(), "text", null)) {
                        s = this.l6[0];
                    }
                }
            }
            else {
                s = this.l24[6];
            }
            if (s != null) {
                final Rectangle l33 = this.l33;
                final int n2 = 10;
                final FontMetrics fontMetrics = this.l14.getFontMetrics(this.l35);
                final String[] l34 = this.l0(s, l33.width - 8, fontMetrics, n2);
                final int n3 = fontMetrics.getAscent() + 1;
                for (int i = 0; i < n2; ++i) {
                    try {
                        fontMetrics.stringWidth(l34[i]);
                        final int n4 = (i + 1) * n3;
                        final int n5 = 4;
                        this.l14.setFont(this.l35);
                        this.l14.setColor(this.l34[0]);
                        this.l14.drawString(l34[i], n5 + 1, n4 + 1);
                        this.l14.setColor(this.l34[1]);
                        this.l14.drawString(l34[i], n5, n4);
                    }
                    catch (Exception ex) {}
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    private boolean l0(final String s, final String s2, final String s3) {
        this.l6[0] = null;
        this.l6[1] = null;
        try {
            final int length = s2.length();
            final int length2 = s.length();
            if (s.toLowerCase().startsWith(s2)) {
                int index = s.indexOf(",");
                if (index == -1 || s3 == null) {
                    index = length2;
                    this.l6[1] = s3;
                }
                else {
                    this.l6[1] = s.substring(index + 1, length2).trim();
                }
                this.l6[0] = s.substring(length + 1, index).trim();
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean l0() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array = new Image[3];
        final String[] array2 = { "mainimage", "focusimage", "imagemap" };
        int n = 0;
        do {
            array[n] = null;
            this.l28[5] = array2[n];
            final String l0 = this.l0(5, null);
            int n2 = (l0 != null) ? 1 : 0;
            if (n2 != 0) {
                try {
                    mediaTracker.addImage(array[n] = this.getImage(this.l5, l0), 0);
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {
                    n2 = 0;
                }
            }
            if (n2 == 0 || mediaTracker.isErrorID(0)) {
                if (this.l11 == null) {
                    this.l31 = 20;
                    return false;
                }
                array[n] = null;
            }
        } while (++n < 3);
        final int width = array[0].getWidth(null);
        final int height = array[0].getHeight(null);
        this.l29 = new int[4][width * height];
        this.l32 = new Rectangle(4, 4, this.l39 - this.l33.width - 12, this.l15 - 8);
        if (!this.l0) {
            this.l32 = new Rectangle(128, 4, this.l39 - this.l33.width - 12, this.l15 - 8);
        }
        int n3 = 0;
        do {
            if (array[n3] != null) {
                final int[] array3 = new int[width * height];
                final PixelGrabber pixelGrabber = new PixelGrabber(array[n3], 0, 0, width, height, array3, 0, width);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                try {
                    System.arraycopy(array3, 0, this.l29[n3], 0, width * height);
                }
                catch (Exception ex3) {}
            }
        } while (++n3 < 3);
        this.l20 = this.createImage(new MemoryImageSource(width, height, this.l29[0], 0, width));
        this.l19 = this.createImage(this.l39, this.l15);
        final int n4 = this.l39 - 1;
        final int n5 = this.l15 - 1;
        final Graphics graphics = this.l19.getGraphics();
        graphics.setColor(this.l7[3]);
        graphics.fillRect(0, 0, n4, n5);
        graphics.setColor(this.l7[2]);
        graphics.fillRect(0, 0, n4 - 1, n5 - 1);
        graphics.setColor(this.l7[1]);
        graphics.fillRect(1, 1, n4 - 2, n5 - 2);
        final int n6 = this.l33.x - 1;
        final int n7 = this.l33.width + 2;
        graphics.setColor(this.l7[3]);
        graphics.fillRect(n6, 3, n7, n5 - 5);
        graphics.setColor(this.l7[2]);
        graphics.fillRect(n6, 3, n7 - 1, n5 - 6);
        graphics.setColor(this.l7[1]);
        graphics.fillRect(n6 + 1, 4, n7 - 2, n5 - 7);
        final int n8 = this.l32.x - 1;
        final int n9 = this.l32.width + 2;
        graphics.setColor(this.l7[2]);
        graphics.fillRect(n8, 3, n9, n5 - 5);
        graphics.setColor(this.l7[3]);
        graphics.fillRect(n8, 3, n9 - 1, n5 - 6);
        graphics.setColor(this.l7[1]);
        graphics.fillRect(n8 + 1, 4, n9 - 2, n5 - 7);
        System.gc();
        final int width2 = this.l32.width;
        final int height2 = this.l32.height;
        try {
            this.l18 = new int[500];
            int l2 = 1;
            this.l18[0] = Color.white.getRGB();
            for (int i = 0; i < width2 * height2; ++i) {
                boolean b = true;
                final int n10 = this.l29[2][i];
                for (int j = 0; j < l2; ++j) {
                    if (n10 == this.l18[j]) {
                        b = false;
                    }
                }
                if (b) {
                    this.l18[l2] = this.l29[2][i];
                    ++l2;
                }
            }
            this.l37 = l2;
        }
        catch (Exception ex4) {}
        return true;
    }
    
    private boolean l1() {
        final String[][] array = new String[this.l21][5];
        if (this.l36 != null) {
            URL url;
            try {
                url = new URL(this.l5, this.l36);
            }
            catch (Exception ex) {
                this.l31 = 3;
                return false;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(url.openStream());
            }
            catch (Exception ex2) {
                this.l31 = 4;
                return false;
            }
            final byte[] array2 = new byte[this.l21 * 250];
            try {
                try {
                    dataInputStream.readFully(array2);
                }
                catch (EOFException ex3) {}
            }
            catch (IOException ex4) {
                this.l31 = 5;
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
            this.l26 = 0;
            streamTokenizer.eolIsSignificant(true);
            while (!b) {
                this.l25 = this.l26;
                if (this.l26 >= this.l21 - 2) {
                    b = true;
                }
                int nextToken;
                try {
                    nextToken = streamTokenizer.nextToken();
                }
                catch (Exception ex6) {
                    if (this.l11 == null) {
                        this.l31 = 13;
                        return false;
                    }
                    nextToken = 0;
                }
                if (nextToken == -1) {
                    b = true;
                    if (n == 5) {
                        ++this.l26;
                    }
                }
                if (nextToken == 10 && n != 0) {
                    ++this.l26;
                    n = 0;
                }
                if (nextToken == 34) {
                    if (n >= 5) {
                        if (this.l11 == null) {
                            this.l31 = 13;
                            return false;
                        }
                        ++this.l26;
                        n = 0;
                    }
                    array[this.l26][n] = streamTokenizer.sval;
                    ++n;
                }
            }
            try {
                byteArrayInputStream.close();
            }
            catch (Exception ex7) {}
        }
        System.gc();
        this.l2 = new String[this.l26 + 1];
        this.l3 = new String[this.l26 + 1];
        this.l1 = new String[this.l26 + 1];
        for (int i = 0; i < this.l26; ++i) {
            this.l25 = i;
            this.l2[i] = array[i][0].trim();
            this.l1[i] = array[i][3];
            if (array[i][4] != null && array[i][4].length() > 0) {
                this.l3[i] = array[i][4];
            }
            else {
                this.l3[i] = " ";
            }
        }
        return true;
    }
    
    private String l0(final int n, final String s) {
        final String parameter = this.getParameter(this.l28[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int l0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.l28[n]);
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
    
    public boolean handleEvent(final Event event) {
        try {
            if (event.id == 502 && event.modifiers > 0) {
                this.getAppletContext().showDocument(new URL(this.l24[3]), "_blank");
                return true;
            }
            final int x = event.x;
            final int y = event.y;
            boolean b = false;
            int l12 = -1;
            if (this.l32.inside(x, y)) {
                final int n = x - this.l32.x;
                final int n2 = y - this.l32.y;
                if (event.id == 503) {
                    this.l0(n, n2);
                    this.l12 = -1;
                    for (int i = 0; i < this.l37; ++i) {
                        if (this.l16 == this.l18[i]) {
                            this.l12 = i - 1;
                        }
                    }
                }
                b = (this.l12 >= 0 && this.l12 < this.l26);
                if (b) {
                    l12 = this.l12;
                }
            }
            if (event.id == 502) {
                if (!this.l17) {
                    return true;
                }
                this.l17 = false;
                if (b) {
                    this.l1(3);
                    this.click(l12);
                    this.l1(12);
                }
            }
            else if (event.id == 503) {
                if (this.l12 == this.l27) {
                    return true;
                }
                if (!this.l17) {
                    return true;
                }
                this.l17 = false;
                this.l27 = this.l12;
                if (b) {
                    this.l0(l12);
                    this.showStatus(this.l3[l12]);
                }
                else {
                    this.l0(-2);
                }
                this.update(this.getGraphics());
            }
            else if (event.id == 505) {
                this.l27 = -1;
                this.l0(this.l12 = -1);
                this.update(this.getGraphics());
                this.l1(0);
            }
            else if (event.id == 504) {
                this.l1(12);
            }
        }
        catch (Exception ex) {}
        return this.l17 = true;
    }
    
    public imsmfree() {
        this.l25 = -1;
        this.l12 = -1;
        this.l27 = -1;
        this.l30 = 2;
        this.l6 = new String[2];
        this.l28 = new String[] { "escapepage", "copyright", "-", "width", "height", "dummy" };
    }
    
    public void init() {
        this.l8 = this.l0(1, "");
        this.l7 = new Color[4];
        (this.l34 = new Color[2])[1] = new Color(192, 192, 0);
        this.l34[0] = Color.black;
        this.l7[0] = new Color(64, 64, 64);
        this.l7[1] = new Color(64, 64, 64);
        this.l7[2] = new Color(128, 128, 128);
        this.l7[3] = new Color(32, 32, 32);
        this.l21 = 1000;
        this.l10 = "_top";
        this.l35 = new Font("Helvetica", 0, 11);
        try {
            this.l9 = this.getDocumentBase();
            this.l5 = this.getCodeBase();
            this.l11 = new URL(this.l9, this.l0(0, null));
        }
        catch (Throwable t) {
            this.l11 = null;
        }
        this.l36 = "menu.txt";
        this.l39 = this.l0(3, 2000, 0, 0, 10);
        this.l15 = this.l0(4, 2000, 0, 0, 10);
        this.l0 = ((int)Math.round(Math.random() * 2.0) - 1 == 0);
        final int n = 120;
        if (this.l0) {
            this.l33 = new Rectangle(this.l39 - n - 4, 4, n, this.l15 - 8);
        }
        else {
            this.l33 = new Rectangle(4, 4, n, this.l15 - 8);
        }
        this.l23 = this.createImage(this.l33.width, this.l33.height);
        (this.l14 = this.l23.getGraphics()).setColor(this.l7[0]);
        this.l14.fillRect(0, 0, this.l33.width, this.l33.height);
        this.l19 = this.createImage(this.l39, this.l15);
    }
    
    private void l2() {
        if (!this.l22) {
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this.l7[0]);
            graphics.fillRect(0, 0, this.l39, this.l15);
            this.l38 = false;
            this.l30 = 2;
            this.repaint();
            final int n = 7;
            (this.l24 = new String[n])[0] = "iMMap Mini SensoMap";
            this.l24[1] = "© Image Intelligence Software Ltd.";
            this.l24[2] = "www.imint.com";
            this.l24[3] = "http://www.imint.com/free.htm";
            this.l24[4] = "Right-click for your own free version.";
            this.l24[5] = "Image Intelligence Software Ltd. (www.imint.com)";
            this.l24[6] = this.l24[0] + " § " + this.l24[1] + " § " + this.l24[2] + " § § " + this.l24[4];
            int n2 = 0;
            do {
                System.out.println(this.l24[n2]);
            } while (++n2 < 3);
            char c = '\0';
            String string = "";
            for (int i = 0; i < n - 1; ++i) {
                string += this.l24[i];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.l8.equalsIgnoreCase(this.l24[5])) {
                this.l31 = 9;
                this.l30 = 1;
                return;
            }
            if (c != 1525849) {
                this.l31 = 30;
                this.l30 = 1;
                return;
            }
            final String[] array2;
            final String[] array = array2 = new String[] { this.l5.getProtocol().toString().toLowerCase(), this.l5.getHost().toString().toLowerCase(), null, null };
            final int n3 = 1;
            array2[n3] += this.l5.getFile().toString().toLowerCase();
            try {
                array[2] = InetAddress.getByName(this.l5.getHost()).toString();
                array[3] = array[2].substring(array[2].indexOf("/") + 1);
            }
            catch (Exception ex) {}
            if (array[3].startsWith("10.") || array[3].startsWith("172.") || array[3].startsWith("192.168.")) {
                this.l31 = 2;
                this.l30 = 1;
                System.out.println("E2");
                return;
            }
            try {
                if (!this.l1() && !this.l1()) {
                    this.l30 = 1;
                    return;
                }
            }
            catch (Exception ex2) {
                this.l31 = 6;
                this.l30 = 1;
                return;
            }
            try {
                if (!this.l0() && !this.l0()) {
                    this.l30 = 1;
                    return;
                }
            }
            catch (Exception ex3) {
                this.l31 = 7;
                this.l30 = 1;
                return;
            }
        }
        this.l38 = true;
        this.l22 = true;
        this.l30 = 0;
        this.l0(-1);
        this.update(this.getGraphics());
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.l38 && this.l30 == 0) {
                if (this.l19 != null) {
                    graphics.drawImage(this.l19, 0, 0, null);
                }
                if (this.l23 != null) {
                    graphics.drawImage(this.l23, this.l33.x, this.l33.y, null);
                }
                graphics.clipRect(this.l32.x, this.l32.y, this.l32.width, this.l32.height);
                graphics.drawImage(this.l20, this.l32.x, this.l32.y, null);
                return;
            }
            if (this.l30 == 1 || this.l30 == 2) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, this.l39, this.l15);
                graphics.setFont(new Font("Helvetica", 0, 11));
                graphics.setColor(Color.black);
                if (this.l30 == 1) {
                    String s = "Error: " + this.l31;
                    if (this.l25 != -1) {
                        s = s + " / " + this.l25;
                    }
                    graphics.drawString(s, 3, 12);
                    System.out.println(s);
                }
            }
            else if (this.l30 == 3) {
                graphics.setColor(this.l7[0]);
                graphics.fillRect(0, 0, this.l39, this.l15);
                graphics.setColor(this.l34[1]);
                graphics.drawString("Loading...", 10, 10);
            }
        }
        catch (Exception ex) {}
    }
    
    private void l0(final int n, final int n2) {
        final int width = this.l20.getWidth(null);
        final int height = this.l20.getHeight(null);
        if (n < 0 || n2 < 0 || n > width || n2 > height) {
            return;
        }
        final int l16 = this.l16;
        this.l16 = this.l29[2][n + n2 * width];
        if (this.l16 == l16) {
            return;
        }
        if (this.l16 != Color.white.getRGB()) {
            for (int i = 0; i < width * height; ++i) {
                this.l29[3][i] = ((this.l29[2][i] == this.l16) ? this.l29[1][i] : this.l29[0][i]);
            }
            this.l20 = this.createImage(new MemoryImageSource(width, height, this.l29[3], 0, width));
        }
        else {
            this.l20 = this.createImage(new MemoryImageSource(width, height, this.l29[0], 0, width));
        }
        this.update(this.getGraphics());
        System.gc();
    }
    
    public void run() {
        try {
            this.l2();
        }
        catch (Exception ex) {
            try {
                this.l2();
            }
            catch (Exception ex2) {
                this.l31 = 0;
                this.l30 = 1;
            }
        }
        if (this.l30 > 0 && (this.l11 == null || this.l31 < 10)) {
            this.l38 = false;
            if (this.l11 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.l11, "_self");
            }
            this.l22 = true;
            this.stop();
        }
        this.l17 = true;
    }
    
    private void l1(final int n) {
        try {
            this.setCursor(new Cursor(n));
        }
        catch (Exception ex) {}
    }
    
    public void start() {
        if (this.m_tree == null) {
            (this.m_tree = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_tree != null) {
            this.m_tree.stop();
            this.m_tree = null;
        }
    }
    
    public void update(final Graphics graphics) {
        try {
            this.l4 = this.createImage(this.l39, this.l15);
            this.paint(this.l13 = this.l4.getGraphics());
            graphics.drawImage(this.l4, 0, 0, null);
        }
        catch (Exception ex) {}
    }
    
    private String[] l0(final String s, final int n, final FontMetrics fontMetrics, final int n2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String[] array = new String[n2];
        String s2 = "";
        String s3 = "";
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            array[i] = "";
        }
        while (stringTokenizer.hasMoreTokens() && n3 < n2) {
            String nextToken = stringTokenizer.nextToken();
            String string = s3 + " " + nextToken;
            if (nextToken.equalsIgnoreCase("§")) {
                if (n3 < n2) {
                    array[n3] = s3.trim();
                }
                ++n3;
                string = "";
                s3 = "";
                nextToken = "";
            }
            if (fontMetrics.stringWidth(string) > n) {
                if (n3 < n2) {
                    array[n3] = s3.trim();
                }
                if (array[n3].length() == 0) {
                    if (n3 < n2) {
                        array[n3] = nextToken.trim();
                    }
                    s3 = "";
                }
                else {
                    s3 = nextToken;
                }
                ++n3;
            }
            else {
                s3 = string;
            }
            s2 = "";
        }
        if (n3 < n2) {
            array[n3] = (s3 + " " + s2).trim();
        }
        return array;
    }
}
