import java.awt.Event;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Color;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ipfree extends Applet implements Runnable
{
    private String[] x0;
    private boolean[] x1;
    private byte[] x2;
    private String[] x3;
    private String[] x4;
    private byte[] x5;
    private boolean[] x6;
    private Image x7;
    private URL x8;
    public Color[] colour;
    private String x9;
    private int x10;
    private URL x11;
    private String x12;
    private URL x13;
    private Frame x14;
    private Graphics x15;
    private boolean x16;
    private String[] x17;
    private int x18;
    private int x19;
    private Thread x20;
    private int x21;
    private String[] x22;
    private int x23;
    private int x24;
    private Color[] x25;
    private Font x26;
    private boolean x27;
    public ibfree[] win;
    
    private int x0(final int[] array, final int n) {
        new Font("Helvetica", 0, 11);
        final Image image = this.createImage(10, 10);
        final Graphics graphics = image.getGraphics();
        int max = 0;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, this.x0(graphics, array[i], 0, 0, false, this.x19));
        }
        image.flush();
        graphics.dispose();
        return max + 28;
    }
    
    public void click(final int n, final boolean b) {
        URL url = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.x0[n], ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            final int length = trim.length();
            if (trim.toLowerCase().startsWith("link")) {
                int index = trim.indexOf(",");
                String s;
                if (index == -1 || length - index < 2) {
                    s = this.x12;
                    index = length;
                }
                else {
                    s = trim.substring(index + 1, length).trim();
                }
                final String trim2 = trim.substring(5, index).trim();
                try {
                    url = new URL(this.x11, trim2);
                }
                catch (Exception ex) {
                    if (trim2.toLowerCase().startsWith("mailto")) {
                        try {
                            url = new URL(this.x11, "mailto.htm");
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
        int n = 0;
        do {
            if (this.win[n] != null) {
                this.win[n].dispose();
            }
            this.win[n] = null;
        } while (++n < 2);
    }
    
    public void drawBox(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        graphics.setColor(this.colour[0]);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(this.colour[5]);
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n, n2, n, n2 + n5);
        graphics.setColor(this.colour[3]);
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 1, n2 + 1);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n5 - 1);
        graphics.setColor(this.colour[4]);
        graphics.drawLine(n + 1, n2 + n5 - 1, n + n3 - 1, n2 + n5 - 1);
        graphics.drawLine(n + n3 - 1, n2 + n5 - 1, n + n3 - 1, n2 + 1);
        graphics.setColor(this.colour[1]);
        graphics.drawLine(n, n2 + n5, n + n3, n2 + n5);
        graphics.drawLine(n + n3, n2 + n5, n + n3, n2);
    }
    
    private void x0(final int n, final boolean b, int n2, int n3, final Rectangle rectangle) {
        if (this.x23 == 1) {
            return;
        }
        if (b) {
            this.x10 = 0;
        }
        final int x10 = this.x10;
        final int n4 = b ? this.x21 : this.x5[n];
        if (n4 == 0) {
            return;
        }
        final int[] array = new int[n4];
        int n5 = 0;
        int n6 = b ? 0 : (n + 1);
        final byte b2 = (byte)(b ? 0 : (this.x2[n] + 1));
        final int n7 = n6;
        for (int n8 = n7 + n4, i = n7; i < n8; ++i) {
            if (this.x2[i] == b2) {
                array[n5] = n6;
                ++n5;
            }
            ++n6;
        }
        final int n9 = n5;
        final int x11 = this.x19;
        int x12 = this.x0(array, n9);
        if (!b) {
            x12 -= 18;
        }
        final int n10 = 18;
        final int n11 = n9 * x11 + n10 + 8;
        int max;
        if (b) {
            max = n11 + 22;
        }
        else {
            int height = this.x14.getToolkit().getScreenSize().height;
            if (rectangle != null) {
                height = rectangle.y + rectangle.height;
            }
            final int n12 = height - n3;
            final int max2 = Math.max(0, n11 - n12);
            max = Math.max(n11, n12);
            n3 -= max2;
        }
        final int n13 = max - n10;
        final Image image = this.createImage(x12 + 1, max);
        final Graphics graphics = image.getGraphics();
        final Image image2 = this.createImage(x12 + 1, max);
        final Graphics graphics2 = image2.getGraphics();
        this.drawBox(graphics, 0, 0, x12, max, n13, false);
        if (b) {
            graphics.setColor(this.colour[4]);
            graphics.fillRect(2, 2, x12 - 4, 16);
            graphics.setColor(this.colour[1]);
            graphics.setFont(new Font(this.x26.getName(), 1, 11));
            graphics.drawString("Site index", 7, 14);
        }
        final Graphics graphics3 = image.getGraphics();
        graphics2.drawImage(image, 0, 0, null);
        final Rectangle[] array2 = new Rectangle[n9];
        for (int j = 0; j < n9; ++j) {
            int n14 = x11 * (j + 1);
            if (b) {
                n14 += 26;
            }
            else {
                n14 += 4;
            }
            if (this.x3[array[j]].toLowerCase().indexOf("separator:") != -1) {
                this.x0(graphics3, 4, n14 - x11 / 2, x12 - 8);
                this.x0(graphics2, 4, n14 - x11 / 2, x12 - 8);
            }
            else {
                this.x0(graphics3, array[j], 4, n14, false, x11);
                this.x0(graphics2, array[j], 4, n14, true, x11);
                if (this.x6[array[j]]) {
                    graphics3.setColor(this.x25[0]);
                    graphics2.setColor(this.x25[1]);
                    final int n15 = n14 - x11 / 2 + 4;
                    final int n16 = x12 - 12;
                    int n17 = 0;
                    do {
                        graphics3.drawLine(n16 + n17, n15 - n17, n16 + n17, n15 - 7 + n17);
                        graphics2.drawLine(n16 + n17, n15 - n17, n16 + n17, n15 - 7 + n17);
                    } while (++n17 < 4);
                }
            }
            array2[j] = new Rectangle(4, n14 - x11, x12 - 8, x11);
        }
        final Rectangle rectangle2 = new Rectangle(0, 0, x12 + 1, max);
        if (!b) {
            if (n2 + x12 + 1 > this.x14.getToolkit().getScreenSize().width) {
                n2 -= rectangle.width + x12 - 6;
            }
            rectangle2.move(n2, n3);
        }
        else {
            rectangle2.move(2, 600);
        }
        this.win[x10].make(rectangle2, image, image2, array2, n9, array, rectangle);
        if (image != null) {
            image.flush();
        }
        if (image2 != null) {
            image2.flush();
        }
    }
    
    private void x0(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(this.colour[0].darker());
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.setColor(this.colour[0].brighter());
        graphics.drawLine(n, n2 + 1, n + n3, n2 + 1);
    }
    
    private int x0(final Graphics graphics, final int n, int n2, final int n3, final boolean b, final int n4) {
        final String s = this.x3[n];
        if (s == null || s.length() <= 0) {
            return 0;
        }
        final int n5 = b ? 1 : 0;
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
        if (b && !this.x25[2].equals(this.colour[0])) {
            graphics.setColor(this.x25[2]);
            graphics.fillRect(0, n3 - n4, 300, n4);
        }
        if (n == 0 || n >= 20) {
            graphics.setColor(this.x25[n5]);
            int n6 = 0;
            do {
                final int min = Math.min(2, (n6 < 2) ? (-n6 - 1) : (5 - n6));
                graphics.drawLine(n2 + 3 + n6, n3 - n4 / 2 - min, n2 + 3 + n6, n3 - n4 / 2 + min);
            } while (++n6 < 5);
            n2 += 12;
        }
        else {
            n2 += 4;
        }
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.x26);
        final int n7 = fontMetrics.getAscent() - fontMetrics.getDescent();
        final int stringWidth = fontMetrics.stringWidth(substring);
        graphics.setFont(this.x26);
        graphics.setColor(this.x25[n5]);
        graphics.drawString(substring, n2, n3 - (n4 - n7) / 2);
        return n2 + stringWidth;
    }
    
    private boolean x0() {
        final String[][] array = new String[110][5];
        URL url;
        try {
            url = new URL(this.x8, "menu.txt");
        }
        catch (Exception ex) {
            this.x24 = 3;
            return false;
        }
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(url.openStream());
        }
        catch (Exception ex2) {
            this.x24 = 4;
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
            this.x24 = 5;
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
        this.x21 = 0;
        streamTokenizer.eolIsSignificant(true);
        while (!b) {
            this.x18 = this.x21;
            if (this.x21 >= 99) {
                b = true;
            }
            int nextToken;
            try {
                nextToken = streamTokenizer.nextToken();
            }
            catch (Exception ex6) {
                if (this.x13 == null) {
                    this.x24 = 13;
                    return false;
                }
                nextToken = 0;
            }
            if (nextToken == -1) {
                b = true;
                if (n == 5) {
                    ++this.x21;
                }
            }
            if (nextToken == 10 && n != 0) {
                ++this.x21;
                n = 0;
            }
            if (nextToken == 34) {
                if (n >= 5) {
                    if (this.x13 == null) {
                        this.x24 = 13;
                        return false;
                    }
                    ++this.x21;
                    n = 0;
                }
                array[this.x21][n] = streamTokenizer.sval;
                ++n;
            }
        }
        try {
            byteArrayInputStream.close();
        }
        catch (Exception ex7) {}
        for (int i = 0; i < this.x21; ++i) {
            try {
                Integer.parseInt(array[i][1]);
            }
            catch (NumberFormatException ex8) {
                if (this.x13 == null) {
                    this.x24 = 10;
                    this.x18 = i;
                    return false;
                }
                array[i][1] = "1";
            }
            try {
                Integer.parseInt(array[i][2]);
            }
            catch (NumberFormatException ex9) {
                array[i][2] = "0";
            }
        }
        array[this.x21][1] = "0";
        System.gc();
        this.x21 += 20;
        this.x3 = new String[this.x21 + 1];
        this.x4 = new String[this.x21 + 1];
        this.x0 = new String[this.x21 + 1];
        this.x6 = new boolean[this.x21 + 1];
        this.x1 = new boolean[this.x21 + 1];
        this.x5 = new byte[this.x21 + 1];
        this.x2 = new byte[this.x21 + 1];
        for (int j = 0; j < this.x21; ++j) {
            this.x1[j] = false;
        }
        this.x3[0] = "text:About this menu";
        this.x2[0] = 0;
        this.x6[0] = true;
        this.x5[0] = 18;
        int n2 = 0;
        do {
            this.x3[n2 + 1] = "text:" + this.x17[n2 + 1];
            this.x6[n2 + 1] = false;
            this.x2[n2 + 1] = 1;
            this.x0[n2 + 1] = "link:" + this.x17[3];
        } while (++n2 < 18);
        this.x1[18] = true;
        this.x3[19] = "separator:";
        this.x2[19] = 0;
        this.x6[19] = false;
        int max = 1;
        for (int k = 0; k < this.x21 - 20; ++k) {
            this.x18 = k;
            this.x3[k + 20] = array[k][0].trim();
            final int n3 = max;
            max = Math.max(1, Integer.parseInt(array[k][1]));
            if (max - n3 > 1 || max > 50) {
                max = 1;
            }
            this.x2[k + 20] = (byte)(max - 1);
            if (Integer.parseInt(array[k + 1][1]) > max) {
                this.x6[k + 20] = true;
                int n4 = 0;
                int l;
                for (l = k + 1; l < this.x21 - 20; ++l) {
                    final int int1 = Integer.parseInt(array[l][1]);
                    if (int1 == max + 1) {
                        n4 = l;
                    }
                    if (int1 <= max) {
                        break;
                    }
                }
                this.x5[k + 20] = (byte)(l - k - 1);
                this.x1[n4 + 20] = true;
            }
            else {
                this.x6[k + 20] = false;
            }
            this.x0[k + 20] = array[k][3];
            if (array[k][4] != null && array[k][4].length() > 0) {
                this.x4[k + 20] = array[k][4];
            }
            else {
                this.x4[k + 20] = " ";
            }
        }
        return true;
    }
    
    private String x0(final int n, final String s) {
        final String parameter = this.getParameter(this.x22[n]);
        return (parameter != null) ? parameter : s;
    }
    
    private int x0(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n4;
        try {
            final String parameter = this.getParameter(this.x22[n]);
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
        this.x25[0] = new Color(this.x0(2, 0, 0, -4548270, 16));
        this.x25[1] = new Color(this.x0(3, 0, 0, -1252166, 16));
        this.x25[2] = new Color(this.x0(4, 0, 0, -9673935, 16));
        this.colour[0] = new Color(this.x0(8, 0, 0, -4014171, 16));
        this.colour[3] = new Color(this.x0(9, 0, 0, -2039855, 16));
        this.colour[4] = new Color(this.x0(10, 0, 0, -7238558, 16));
        this.colour[5] = new Color(this.x0(11, 0, 0, -4014171, 16));
        this.colour[1] = new Color(this.x0(12, 0, 0, Color.black.getRGB(), 16));
        final int x0 = this.x0(13, 8, 0, 8, 10);
        if (x0 != 8) {
            this.x0(x0);
        }
        this.x9 = this.x0(1, " ");
        this.x12 = "_top";
        this.x19 = this.x0(7, 30, 6, 11, 10);
        this.x26 = new Font(this.x0(6, "TimesRoman"), this.x0(5, 3, 0, 0, 10), this.x19);
        this.x19 += 3;
        try {
            this.x11 = this.getDocumentBase();
            this.x8 = this.getCodeBase();
            this.x13 = new URL(this.x11, this.x0(0, null));
        }
        catch (Throwable t) {
            this.x13 = null;
        }
        this.colour[2] = Color.orange;
    }
    
    private void x1() {
        if (!this.x16) {
            final int n = 19;
            (this.x17 = new String[n])[0] = "Image Intelligence Ltd. 1998 (www.imint.com)";
            this.x17[1] = "iPOP Mini START-button 2.0";
            this.x17[2] = "© Image Intelligence Ltd.";
            this.x17[3] = "http://www.imint.com";
            this.x17[4] = "FREE NON-COMMERCIAL VERSION";
            this.x17[5] = " ";
            this.x17[6] = "HOW TO USE THIS MENU";
            this.x17[7] = "1. To close, click the x-box on the topbar.";
            this.x17[8] = "2. To re-open, move the mouse over the";
            this.x17[9] = "'launch menu' button on the webpage.";
            this.x17[10] = "3. To minimize, click the arrow box on";
            this.x17[11] = "the topbar.";
            this.x17[12] = "4. To maximize again, click or move the";
            this.x17[13] = "mouse over the sidebar.";
            this.x17[14] = "5. To reposition, drag the topbar. NB: no";
            this.x17[15] = "display during dragging.";
            this.x17[16] = "6. To load a page, just click on an entry.";
            this.x17[17] = "7. Only use on non-commercial websites.";
            this.x17[18] = "8. Click here to link to us and get yours.";
            int n2 = 1;
            do {
                System.out.println(this.x17[n2]);
            } while (++n2 < 4);
            this.x27 = false;
            char c = '\0';
            this.x23 = 2;
            this.repaint();
            String string = "";
            for (int i = 0; i < n; ++i) {
                string += this.x17[i];
            }
            for (char c2 = '\0'; c2 < string.length(); ++c2) {
                c += (char)(string.charAt(c2) * c2);
            }
            if (!this.x9.equalsIgnoreCase(this.x17[0])) {
                this.x24 = 9;
                this.x23 = 1;
                return;
            }
            if (c != 14807919) {
                this.x24 = 30;
                this.x23 = 1;
                return;
            }
            final String[] x17 = this.x17;
            final int n3 = 1;
            x17[n3] += "6";
            try {
                if (!this.x0() && !this.x0()) {
                    this.x23 = 1;
                    return;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                this.x24 = 6;
                this.x23 = 1;
                return;
            }
            this.x27 = true;
            this.x16 = true;
        }
        try {
            this.x0(0, true, 0, 0, null);
        }
        catch (Exception ex2) {
            System.out.println(ex2);
            this.x24 = 8;
            this.x23 = 1;
            return;
        }
        this.x23 = 0;
        this.repaint();
    }
    
    public ipfree() {
        this.x14 = new Frame();
        this.x18 = -1;
        this.colour = new Color[6];
        this.x25 = new Color[3];
        this.x22 = new String[] { "escapepage", "copyright", "textcol1", "textcol2", "textcol3", "textstyle", "textfont", "textsize", "bgcol1", "bgcol2", "bgcol3", "bgcol4", "bgcol5", "colourscheme" };
    }
    
    public void launch() {
        if (this.win[0].onView) {
            this.x3();
        }
    }
    
    private synchronized void x2() {
        int n = 0;
        try {
            int n2 = 0;
            do {
                int n3 = -1;
                int n4 = 0;
                do {
                    if (this.win[n4].hasMouse) {
                        n3 = n4;
                    }
                } while (++n4 < 2);
                if (n3 == -1) {
                    if (n == 0) {
                        n = 1;
                    }
                    else {
                        int n5 = 1;
                        do {
                            this.win[n5].close();
                        } while (++n5 < 2);
                        n = 0;
                    }
                }
                else {
                    n = 0;
                }
            } while (++n2 < 10);
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.x3();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.x3();
        return true;
    }
    
    public void move(final int n, final int n2, final Rectangle rectangle, final Rectangle rectangle2) {
        if (n2 < 0 || n2 >= this.x21) {
            return;
        }
        if (this.x6[n2]) {
            this.x10 = n + 1;
            if (this.x10 < 2) {
                this.x0(n2, false, rectangle.x + rectangle.width + rectangle2.x, rectangle.y + rectangle2.y - 4, rectangle2);
            }
        }
    }
    
    public void order() {
        int n = -1;
        int n2 = 0;
        do {
            if (this.win[n2].hasMouse) {
                n = n2;
            }
        } while (++n2 < 2);
        if (n == -1) {
            return;
        }
        int n3 = n + 1;
        final int focus = this.win[n].getFocus();
        if (focus < 0 || focus > this.x21) {
            return;
        }
        if (this.x6[focus]) {
            ++n3;
        }
        if (n3 >= 2) {
            return;
        }
        for (int i = n3; i < 2; ++i) {
            this.win[i].close();
        }
    }
    
    public void paint(final Graphics graphics) {
        String s = null;
        if (this.x23 == 1) {
            s = "Error: " + this.x24;
            if (this.x18 != -1) {
                s = s + " / " + this.x18;
            }
            System.out.println("Applet reports " + s);
        }
        try {
            final int n = this.bounds().width - 1;
            final int n2 = this.bounds().height - 1;
            this.drawBox(graphics, 0, 0, n, n2, n2, false);
            graphics.setColor(this.x25[0]);
            graphics.setFont(new Font("Helvetica", 1, 12));
            if (this.x23 == 0) {
                s = "Launch menu";
            }
            if (this.x23 == 2) {
                s = "Loading...";
            }
            graphics.drawString(s, 8, 16);
        }
        catch (Exception ex) {}
    }
    
    private void x3() {
        try {
            this.destroy();
            this.x20.stop();
            this.x20 = null;
            this.x20 = new Thread(this);
            this.x16 = true;
            this.x20.start();
        }
        catch (Throwable t) {
            System.out.println(t);
        }
    }
    
    public void run() {
        boolean b = false;
        try {
            final String property = System.getProperty("os.name");
            if (property.toLowerCase().indexOf("win") != -1) {
                b = true;
            }
            System.out.println(property);
        }
        catch (Exception ex2) {}
        if (!b) {
            try {
                this.getAppletContext().showDocument(this.x13, "_self");
                return;
            }
            catch (Exception ex3) {
                return;
            }
        }
        this.x10 = 0;
        this.win = new ibfree[2];
        int n = 0;
        do {
            this.win[n] = new ibfree(this.x14, n, this, 2, this.colour[0]);
        } while (++n < 2);
        try {
            this.x1();
        }
        catch (Exception ex4) {
            try {
                this.x1();
            }
            catch (Exception ex) {
                this.x24 = 0;
                System.out.println(ex);
                this.x23 = 1;
            }
        }
        System.gc();
        if (this.x23 > 0 && (this.x13 == null || this.x24 < 10)) {
            this.x27 = false;
            if (this.x13 == null) {
                this.repaint();
            }
            else {
                this.getAppletContext().showDocument(this.x13, "_self");
            }
            this.x16 = true;
            this.stop();
        }
        while (this.x27) {
            this.x2();
            System.gc();
        }
    }
    
    private void x0(int n) {
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
        this.colour[5] = new Color(221 + 34 * n3, 221 + 34 * n4, 221 + 34 * n5);
        this.colour[3] = new Color(170 + 51 * n3, 170 + 51 * n4, 170 + 51 * n5);
        this.colour[0] = new Color(153 + 51 * n3, 153 + 51 * n4, 153 + 51 * n5);
        this.colour[4] = new Color(102 + 51 * n3, 102 + 51 * n4, 102 + 51 * n5);
        this.x25[0] = new Color(51 + 51 * n3, 51 + 51 * n4, 51 + 51 * n5);
        this.x25[1] = this.colour[5];
        this.x25[2] = this.colour[4];
        this.colour[1] = new Color(51 * n3, 51 * n4, 51 * n5);
    }
    
    public void showMessage(final int n) {
        if (n >= 0 && n < this.x21) {
            this.showStatus(this.x4[n]);
        }
    }
    
    public void start() {
        if (this.x20 == null) {
            (this.x20 = new Thread(this)).start();
        }
        this.x27 = true;
    }
    
    public void stop() {
        if (this.x20 != null) {
            this.x20.stop();
            this.x20 = null;
        }
        this.x27 = false;
        this.destroy();
    }
    
    public void update(final Graphics graphics) {
        this.x7 = this.createImage(this.size().width, this.size().height);
        this.paint(this.x15 = this.x7.getGraphics());
        graphics.drawImage(this.x7, 0, 0, null);
        this.x7.flush();
        this.x15.dispose();
    }
}
