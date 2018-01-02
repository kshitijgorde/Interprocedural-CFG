import java.util.Vector;
import java.io.DataInputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamTokenizer;
import java.io.StringBufferInputStream;
import java.util.Properties;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PhantomScroll extends Applet implements Runnable
{
    public static final int TILE = 0;
    public static final int CENTER = 1;
    private Thread scrollThread;
    private int[] bgPixels;
    private int[] fgPixels;
    private int[] backPixels;
    private int[] canvasPixels;
    private int[] textWidths;
    private int[][] fadePixels;
    private int[][] textPixels;
    private int[][][] linkRanges;
    private Hashtable[][] links;
    private Dimension bgSize;
    private Dimension fgSize;
    private Image bgImage;
    private Image fgImage;
    private int textHeight;
    private int currentText;
    private int width;
    private int height;
    private int offset;
    private int bgDisplay;
    private int textDisp;
    private int speed;
    private int delay;
    private int fadeIndex;
    private int clearAmount;
    private int count;
    private int textCount;
    private int currentLink;
    private int pressedLink;
    private int mouseX;
    private int mouseY;
    private int amplitude;
    private int period;
    private Color bgColor;
    private Color fgColor;
    private Color linkColor;
    private Color activeLinkColor;
    private boolean fadeIncremented;
    private boolean pressed;
    private boolean entered;
    private String defaultTarget;
    
    public boolean mouseEnter(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        return this.entered = true;
    }
    
    public Image getBackgroundImage() {
        return this.bgImage;
    }
    
    private Rectangle effectiveBounds(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final Rectangle intersection = new Rectangle(n3, n4, n9, n10).intersection(new Rectangle(n, n2));
        intersection.translate(n7 - n3, n8 - n4);
        return intersection.intersection(new Rectangle(n5, n6));
    }
    
    private int parseInt(final String s, final int n) {
        if (s == null) {
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            return n;
        }
    }
    
    private void colorPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int[] array2 = new int[4];
        final int[] array3 = new int[4];
        final float[] array4 = new float[3];
        final float[] array5 = new float[3];
        this.decomposePixel(n7, array3);
        Color.RGBtoHSB(array3[1], array3[2], array3[3], array5);
        for (int i = 0; i < n6; ++i) {
            if (n4 + i >= 0 && n4 + i < n2) {
                for (int j = 0; j < n5; ++j) {
                    if (n3 + j >= 0 && n3 + j < n) {
                        double cos = Math.cos(j * 3.141592653589793 / n5);
                        int n8 = 0;
                        do {
                            cos *= cos;
                        } while (++n8 < 4);
                        final double n9 = 1.0 - cos;
                        final int n10 = (int)(255.0 * n9);
                        final int n11 = (i + n4) * n + j + n3;
                        this.decomposePixel(array[n11], array2);
                        Color.RGBtoHSB(array2[1], array2[2], array2[3], array4);
                        final float n12 = array4[1];
                        final float n13 = array4[2];
                        array2[1] = (n10 * array3[1] + (255 - n10) * array2[1]) / 255;
                        array2[2] = (n10 * array3[2] + (255 - n10) * array2[2]) / 255;
                        array2[3] = (n10 * array3[3] + (255 - n10) * array2[3]) / 255;
                        Color.RGBtoHSB(array2[1], array2[2], array2[3], array4);
                        array4[1] = (float)(n9 * array5[1] + (1.0 - n9) * n12);
                        array4[2] = (float)(0.4 * n9 * array5[2] + (1.0 - 0.4 * n9) * n13);
                        array[n11] = (array2[0] << 24 | (0xFFFFFF & Color.HSBtoRGB(array4[0], array4[1], array4[2])));
                    }
                }
            }
        }
    }
    
    private void handleDisplacement(final int n) {
        this.offset += n;
        if (this.offset + this.textWidths[this.currentText] < 0) {
            this.offset = this.width + this.offset + this.textWidths[this.currentText];
            ++this.currentText;
            if (this.currentText == this.textCount) {
                this.currentText = 0;
            }
        }
        else if (this.offset > this.width) {
            --this.currentText;
            if (this.currentText < 0) {
                this.currentText = this.textCount - 1;
            }
            this.offset = this.offset - this.width - this.textWidths[this.currentText];
        }
    }
    
    private void updateBackground() {
        if (this.textPixels != null) {
            this.textDisp = (this.height - this.textHeight) / 2;
            this.backPixels = new int[this.width * this.textHeight];
            this.canvasPixels = new int[this.width * this.textHeight];
            final int rgb = this.bgColor.getRGB();
            for (int i = 0; i < this.width * this.textHeight; ++i) {
                this.canvasPixels[i] = (0xFF000000 | rgb);
            }
            if (this.bgPixels != null) {
                if (this.bgDisplay == 0) {
                    for (int j = -(this.textDisp % this.bgSize.height); j < this.textHeight; j += this.bgSize.height) {
                        for (int k = 0; k < this.width; k += this.bgSize.width) {
                            this.overlayPixels(this.bgPixels, this.bgSize.width, this.bgSize.height, 0, 0, this.bgSize.width, this.bgSize.height, this.canvasPixels, this.width, this.textHeight, k, j, false, false);
                        }
                    }
                }
                else if (this.bgDisplay == 1) {
                    this.overlayPixels(this.bgPixels, this.bgSize.width, this.bgSize.height, 0, 0, this.bgSize.width, this.bgSize.height, this.canvasPixels, this.width, this.textHeight, (this.width - this.bgSize.width) / 2, (this.textHeight - this.bgSize.height) / 2, false, false);
                }
            }
            for (int l = 0; l < this.width * this.textHeight; ++l) {
                this.backPixels[l] = (0xFF000000 | (0xFFFFFF & this.canvasPixels[l]));
            }
        }
    }
    
    public Color getBackgroundColor() {
        return new Color(this.bgColor.getRGB());
    }
    
    private int[] getPixels(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
        return array;
    }
    
    private void filterPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, int n11) {
        final Rectangle effectiveBounds = this.effectiveBounds(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n12 = n * n2;
        final int n13 = n9 - n3;
        final int n14 = n10 - n4;
        final int[] array3 = new int[3];
        n11 |= 0xFFFFFF;
        effectiveBounds.translate(-n13, -n14);
        final int n15 = effectiveBounds.x + effectiveBounds.width + 1;
        final int n16 = effectiveBounds.y + effectiveBounds.height + 1;
        for (int i = effectiveBounds.x - 1; i < n15; ++i) {
            int n17 = 0;
            do {
                array3[n17] = 0;
            } while (++n17 < 3);
            for (int j = effectiveBounds.y - 1; j < n16; ++j) {
                final int n18 = (j + 2) % 3;
                final int n19 = (j + 1) * n + i;
                array3[n18] = 0;
                int n20 = -1;
                do {
                    final int n21 = n19 + n20;
                    if (n21 >= 0 && n21 < n12 && i + n20 >= 0 && i + n20 < n) {
                        final int[] array4 = array3;
                        final int n22 = n18;
                        array4[n22] += (array[n21] >> 24 & 0xFF) * ((n20 == 0) ? 3 : 1);
                    }
                } while (++n20 <= 1);
                final int n23 = i + n13;
                final int n24 = j + n14;
                if (n23 >= 0 && n23 < n7 && n24 >= 0 && n24 < n8) {
                    int n25 = 0;
                    final int n26 = j + 3;
                    final int n27 = n26 + 1;
                    for (int n28 = n26 + 2, k = n26; k <= n28; ++k) {
                        n25 += ((k == n27) ? 3 : 1) * array3[k % 3];
                    }
                    int n29;
                    if (j >= 0 && j < n2 && (i >= 0 & i < n)) {
                        n29 = (array[j * n + i] & 0xFFFFFF);
                    }
                    else {
                        n29 = n11;
                    }
                    array2[n24 * n7 + n23] = (n25 / 25 << 24 | n29);
                }
            }
        }
    }
    
    private void overlayPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int n9, final int n10, final boolean b, final boolean b2) {
        final Rectangle effectiveBounds = this.effectiveBounds(n, n2, n3, n4, n7, n8, n9, n10, n5, n6);
        final int n11 = n9 - n3;
        final int n12 = n10 - n4;
        int i = effectiveBounds.y * n7;
        int n13 = (effectiveBounds.y - n12) * n;
        final int n14 = effectiveBounds.x + effectiveBounds.width;
        final int n15 = (effectiveBounds.y + effectiveBounds.height) * n7;
        int fadeIndex = this.fadeIndex;
        final int n16 = this.width / 2;
        final int[] array3 = new int[4];
        final int[] array4 = new int[4];
        final int[] array5 = new int[4];
        int fadeIncremented = this.fadeIncremented ? 1 : 0;
        this.decomposePixel(this.activeLinkColor.getRGB(), array5);
        while (i < n15) {
            int x;
            if (effectiveBounds.x <= n16) {
                x = effectiveBounds.x;
            }
            else {
                x = 2 * n16 - effectiveBounds.x;
            }
            for (int j = effectiveBounds.x; j < n14; ++j) {
                this.decomposePixel(array[n13 + j - n11], array3);
                if (array3[0] != 0) {
                    final int n17 = i + j;
                    final int n18 = array2[n17];
                    if (this.currentLink >= 0 && !b && this.linkRanges[this.currentText][this.currentLink][0] <= j - n9 && j - n9 <= this.linkRanges[this.currentText][this.currentLink][1]) {
                        array3[1] = array5[1];
                        array3[2] = array5[2];
                        array3[3] = array5[3];
                    }
                    if (b) {
                        array3[0] = this.clearAmount;
                    }
                    else if (b2) {
                        array3[0] = array3[0] * this.fadePixels[fadeIndex][x] / 255;
                    }
                    final int n19 = 255 - array3[0];
                    this.decomposePixel(n18, array4);
                    array4[1] = (n19 * array4[1] + array3[0] * array3[1]) / 255;
                    array4[2] = (n19 * array4[2] + array3[0] * array3[2]) / 255;
                    array4[3] = (n19 * array4[3] + array3[0] * array3[3]) / 255;
                    array2[n17] = (0xFF000000 | array4[1] << 16 | array4[2] << 8 | array4[3]);
                }
                if (j < n16) {
                    ++x;
                }
                else {
                    --x;
                }
            }
            if (fadeIncremented != 0) {
                ++fadeIndex;
            }
            else {
                --fadeIndex;
            }
            if (fadeIndex >= this.fadePixels.length) {
                --fadeIndex;
                fadeIncremented = 0;
            }
            else if (fadeIndex < 0) {
                ++fadeIndex;
                fadeIncremented = 1;
            }
            i += n7;
            n13 += n;
        }
    }
    
    public String getAppletInfo() {
        return "Uldarico Muico Jr., um@mail.com\n\r" + "PhantomScroll 1.0 January 25, 2000\n\r" + "Copyright (c) 2000 Uldarico Muico, Jr.\n\r";
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    private double parseUnity(final String s, final double n) {
        if (s == null) {
            return n;
        }
        try {
            return this.normalize(Double.valueOf(s));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            return n;
        }
    }
    
    private void decomposePixel(final int n, final int[] array) {
        array[0] = (n >> 24 & 0xFF);
        array[1] = (n >> 16 & 0xFF);
        array[2] = (n >> 8 & 0xFF);
        array[3] = (n & 0xFF);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.entered = false;
        return true;
    }
    
    private void createFade() {
        final double n = 0.8;
        final int textHeight = this.textHeight;
        final int n2 = this.width / 2 + 1;
        final int n3 = textHeight / 2 + 1;
        this.fadePixels = new int[n3][];
        for (int i = 0; i < n3; ++i) {
            this.fadePixels[i] = new int[n2];
            double n4;
            int j;
            for (n4 = n * (0.65 - 0.35 * Math.cos(6.283185307179586 * i / textHeight)), j = 0; j < (int)(n4 * n2); ++j) {
                this.fadePixels[i][j] = (int)((0.5 - 0.5 * Math.cos(6.283185307179586 / n4 * j / this.width)) * 255.0);
            }
            while (j < n2) {
                this.fadePixels[i][j] = 255;
                ++j;
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.pressed = false;
        if (this.currentLink >= 0 && this.currentLink == this.pressedLink) {
            final String s = this.links[this.currentText][this.currentLink].get("target");
            this.getAppletContext().showDocument(this.links[this.currentText][this.currentLink].get("href"), (s == null) ? this.defaultTarget : s);
        }
        return true;
    }
    
    private Image parseImage(final String s, final Image image) {
        if (s != null) {
            return this.getImage(this.parseURL(s, null, true));
        }
        return image;
    }
    
    private int searchBracket(final String s, int index) {
        while (true) {
            final char char1 = s.charAt(index);
            if (char1 == '>') {
                return index;
            }
            if (char1 == '\"' || char1 == '\'') {
                index = s.indexOf(char1, index + 1);
            }
            if (index == -1) {
                return -1;
            }
            ++index;
        }
    }
    
    private Properties parseTagProperties(final String s) {
        final Properties properties = new Properties();
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringBufferInputStream(s));
        String sval = null;
        streamTokenizer.resetSyntax();
        streamTokenizer.wordChars(33, 65535);
        streamTokenizer.quoteChar(39);
        streamTokenizer.quoteChar(34);
        streamTokenizer.ordinaryChar(61);
        try {
            if (streamTokenizer.nextToken() != -1) {
                ((Hashtable<String, String>)properties).put("type", streamTokenizer.sval);
            }
            for (int i = streamTokenizer.nextToken(); i != -1; i = streamTokenizer.nextToken()) {
                if (i == 61 && sval != null) {
                    streamTokenizer.nextToken();
                    ((Hashtable<String, String>)properties).put(sval.toLowerCase(), streamTokenizer.sval);
                    sval = null;
                }
                else if (i == -3) {
                    sval = streamTokenizer.sval;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.pressed = true;
        this.pressedLink = ((this.currentLink >= 0) ? this.currentLink : -1);
        return true;
    }
    
    public void run() {
        while (this.scrollThread != null) {
            this.repaint();
            ++this.count;
            if (!this.pressed) {
                int n = -this.speed;
                if (this.period != 0) {
                    n -= (int)(this.amplitude * Math.cos(6.283185307179586 * this.count / this.period));
                }
                this.handleDisplacement(n);
            }
            if (this.fadeIncremented) {
                ++this.fadeIndex;
            }
            else {
                --this.fadeIndex;
            }
            if (this.fadeIndex >= this.fadePixels.length) {
                --this.fadeIndex;
                this.fadeIncremented = false;
            }
            else if (this.fadeIndex < 0) {
                ++this.fadeIndex;
                this.fadeIncremented = true;
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {
                this.scrollThread = null;
            }
        }
    }
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.linkColor = this.parseColor(this.getParameter("linkColor"), new Color(255));
        this.activeLinkColor = this.parseColor(this.getParameter("activeLinkColor"), new Color(16711680));
        this.setForeground(this.parseColor(this.getParameter("fgColor"), new Color(16777215)));
        this.setBackground(this.parseColor(this.getParameter("bgColor"), new Color(0)));
        this.setTarget(this.getParameter("target"));
        final String parameter = this.getParameter("font");
        final Font font = new Font((parameter == null) ? "Helvetica" : parameter, this.parseInt(this.getParameter("fontStyle"), 0), this.parseInt(this.getParameter("fontSize"), 12));
        final String parameter2 = this.getParameter("text");
        if (parameter2 != null) {
            this.setText(parameter2, font);
        }
        else {
            this.setText(this.parseURL(this.getParameter("textFile"), null, true), font);
        }
        final String parameter3 = this.getParameter("bgImage");
        if (parameter3 != null) {
            this.setBackground(this.parseImage(parameter3, null), null, this.parseInt(this.getParameter("bgDisplay"), 0) % 2);
        }
        final String parameter4 = this.getParameter("fgImage");
        if (parameter4 != null) {
            this.setForeground(this.parseImage(parameter4, null));
        }
        this.setSpeed(this.parseInt(this.getParameter("speed"), 5));
        this.setDelay(this.parseInt(this.getParameter("delay"), 80));
        this.setAmplitude(this.parseInt(this.getParameter("amplitude"), 6));
        this.setPeriod(this.parseInt(this.getParameter("period"), 30));
        this.setTrail(this.parseUnity(this.getParameter("trail"), 0.8));
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void setForeground(final Image fgImage) {
        this.fgImage = fgImage;
        if (this.prepareImage(fgImage, this)) {
            this.fgPixels = this.getPixels(this.fgImage);
            this.fgSize.width = this.fgImage.getWidth(null);
            this.fgSize.height = this.fgImage.getHeight(null);
            this.updateForeground();
            this.repaint();
        }
    }
    
    public void setForeground(final Color color) {
        this.fgColor = new Color(color.getRGB());
        this.fgPixels = null;
        this.updateForeground();
        this.repaint();
    }
    
    public void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    private Color parseColor(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        try {
            if (s.indexOf(" ") != -1) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s);
                int n = 255;
                while (stringTokenizer.hasMoreTokens()) {
                    n = (n << 8 | Integer.parseInt(stringTokenizer.nextToken()));
                }
                return new Color(n);
            }
            return new Color(0xFF000000 | Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            return color;
        }
    }
    
    public Color getLinkColor() {
        return new Color(this.linkColor.getRGB());
    }
    
    public void setLinkColor(final Color color) {
        this.linkColor = new Color(color.getRGB());
        this.updateForeground();
    }
    
    public boolean mouseMove(final Event event, final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.scrollThread == null) {
            this.repaint();
        }
        return true;
    }
    
    public void stop() {
        this.scrollThread = null;
    }
    
    private Dimension getTextSize(final String s, final FontMetrics fontMetrics) {
        final Dimension dimension = new Dimension();
        dimension.width = fontMetrics.stringWidth(s) + 2 * fontMetrics.charWidth(' ');
        final Dimension dimension2 = dimension;
        dimension2.height += fontMetrics.getAscent() + fontMetrics.getDescent() + 3;
        return dimension;
    }
    
    private double normalize(double n) {
        if (n < 0.0) {
            n = 0.0;
        }
        else if (n > 1.0) {
            n = 1.0;
        }
        return n;
    }
    
    private URL parseURL(final String s, final URL url, final boolean b) {
        if (s == null) {
            return url;
        }
        try {
            if (s.indexOf(":") != -1) {
                return new URL(s);
            }
            return new URL(b ? this.getCodeBase() : this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return url;
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.textPixels != null) {
            final int currentLink = this.currentLink;
            if (this.entered && this.textDisp <= this.mouseY && this.mouseY <= this.textDisp + this.textHeight && this.linkRanges[this.currentText].length > 0) {
                this.currentLink = this.searchActiveLink();
                if (this.currentLink != currentLink) {
                    this.showStatus((this.currentLink == -1) ? "" : this.links[this.currentText][this.currentLink].get("alt"));
                }
            }
            else if (currentLink >= 0) {
                this.showStatus("");
                this.currentLink = -1;
            }
            this.overlayPixels(this.backPixels, this.width, this.textHeight, 0, 0, this.width, this.textHeight, this.canvasPixels, this.width, this.textHeight, 0, 0, true, false);
            this.overlayPixels(this.textPixels[this.currentText], this.textWidths[this.currentText], this.textHeight, 0, 0, this.textWidths[this.currentText], this.textHeight, this.canvasPixels, this.width, this.textHeight, this.offset, 0, false, true);
            final Image image = this.createImage(new MemoryImageSource(this.width, this.textHeight, this.canvasPixels, 0, this.width));
            graphics.drawImage(image, 0, this.textDisp, null);
            image.flush();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            if (image == this.bgImage) {
                this.setBackground(this.bgImage, this.bgColor, this.bgDisplay);
            }
            else if (image == this.fgImage) {
                this.setForeground(this.fgImage);
            }
        }
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    public void start() {
        if (this.scrollThread == null) {
            (this.scrollThread = new Thread(this)).start();
        }
    }
    
    public PhantomScroll() {
        this.bgSize = new Dimension();
        this.fgSize = new Dimension();
        this.currentLink = -1;
        this.pressedLink = -1;
        this.fadeIncremented = true;
    }
    
    public Image getForegroundImage() {
        return this.fgImage;
    }
    
    private int[] createTextPixels(final String s, final Font font, final Color color) {
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final Dimension textSize = this.getTextSize(s, fontMetrics);
        final int n = textSize.width * textSize.height;
        fontMetrics.getAscent();
        fontMetrics.getDescent();
        final int n2 = ((color == null) ? -8421505 : color.getRGB()) & 0xFFFFFF;
        final Image image = this.createImage(textSize.width, textSize.height);
        final Graphics graphics = image.getGraphics();
        final int[] array = new int[4];
        graphics.setColor(new Color(-16777216));
        graphics.fillRect(0, 0, textSize.width, textSize.height);
        graphics.setColor(new Color(-1));
        graphics.setFont(font);
        graphics.drawString(s, (textSize.width - fontMetrics.stringWidth(s)) / 2, fontMetrics.getAscent());
        graphics.dispose();
        final int[] pixels = this.getPixels(image);
        for (int i = 0; i < n; ++i) {
            this.decomposePixel(pixels[i], array);
            pixels[i] = ((array[1] + array[2] + array[3]) / 3 << 24 | n2);
        }
        return pixels;
    }
    
    public void setTrail(final double n) {
        this.clearAmount = 255 - (int)(255.0 * this.normalize(n));
    }
    
    public int getPeriod() {
        return this.period;
    }
    
    public void setPeriod(final int period) {
        this.period = period;
    }
    
    public String getTarget() {
        return new String(this.defaultTarget);
    }
    
    public void setBackground(final Image bgImage, final Color color, final int n) {
        this.bgImage = bgImage;
        this.bgDisplay = n % 2;
        if (color != null) {
            this.bgColor = new Color(color.getRGB());
        }
        if (this.prepareImage(bgImage, this)) {
            this.bgPixels = this.getPixels(this.bgImage);
            this.bgSize.width = this.bgImage.getWidth(null);
            this.bgSize.height = this.bgImage.getHeight(null);
            this.updateBackground();
            this.paint();
        }
    }
    
    public void setBackground(final Color color) {
        this.bgColor = new Color(color.getRGB());
        this.bgPixels = null;
        this.bgImage = null;
        this.updateBackground();
        this.paint();
    }
    
    public void setTarget(final String s) {
        this.defaultTarget = ((s == null) ? "_self" : new String(s));
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgPixels != null) {
            if (this.bgDisplay == 0) {
                for (int i = 0; i < this.height; i += this.bgSize.height) {
                    for (int j = 0; j < this.width; j += this.bgSize.width) {
                        graphics.drawImage(this.bgImage, j, i, this.bgColor, null);
                    }
                }
            }
            else if (this.bgDisplay == 1) {
                graphics.setColor(this.bgColor);
                graphics.fillRect(0, 0, this.width, this.height);
                graphics.drawImage(this.bgImage, (this.width - this.bgSize.width) / 2, (this.height - this.bgSize.height) / 2, null);
            }
        }
        else {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.width, this.height);
        }
        this.update(graphics);
    }
    
    private void paint() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
            graphics.dispose();
        }
    }
    
    public Color getForegroundColor() {
        return new Color(this.fgColor.getRGB());
    }
    
    public Color getActiveLinkColor() {
        return new Color(this.activeLinkColor.getRGB());
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "text", "HTML", "message to be displayed" }, { "textFile", "URL", "text file that contains message" }, { "font", "String", "name of font" }, { "fontStyle", "0-4", "font style" }, { "fontSize", "int", "font size" }, { "bgImage", "URL", "background image" }, { "bgDisplay", "0-1", "method of displaying background image" }, { "bgColor", "RGB", "background color" }, { "fgImage", "URL", "foreground/text image pattern" }, { "fgColor", "RGB", "foreground/text color" }, { "linkColor", "RGB", "color of hyperlinks" }, { "activeLinkColor", "RGB", "color of active hyperlinks" }, { "speed", "int", "average pixels per iteration" }, { "delay", "int", "milliseconds per iteration" }, { "amplitude", "int", "amplitude (pixels) of oscillation" }, { "period", "int", "period (iterations) per oscillation" }, { "trail", "0.0-1.0", "intensity of text trail" }, { "target", "String", "name of the default target frame" } };
    }
    
    private void updateForeground() {
        if (this.textPixels != null) {
            if (this.fgPixels != null) {
                for (int i = 0; i < this.textCount; ++i) {
                    for (int j = 0; j < this.textHeight; ++j) {
                        for (int k = 0; k < this.textWidths[i]; ++k) {
                            final int n = j * this.textWidths[i] + k;
                            this.textPixels[i][n] = ((this.textPixels[i][n] & 0xFF000000) | (0xFFFFFF & this.fgPixels[j % this.fgSize.height * this.fgSize.width + k % this.fgSize.width]));
                        }
                    }
                }
            }
            else {
                final int n2 = 0xFFFFFF & this.fgColor.getRGB();
                for (int l = 0; l < this.textCount; ++l) {
                    for (int n3 = 0; n3 < this.textWidths[l] * this.textHeight; ++n3) {
                        this.textPixels[l][n3] = ((this.textPixels[l][n3] & 0xFF000000) | n2);
                    }
                }
            }
            for (int n4 = 0; n4 < this.textCount; ++n4) {
                for (int n5 = 0; n5 < this.linkRanges[n4].length; ++n5) {
                    final int n6 = this.linkRanges[n4][n5][1] - this.linkRanges[n4][n5][0] + 1;
                    this.colorPixels(this.textPixels[n4], this.textWidths[n4], this.textHeight, this.linkRanges[n4][n5][0] - n6 / 8, 0, 5 * n6 / 4, this.textHeight, this.linkColor.getRGB());
                }
            }
        }
    }
    
    public void setActiveLinkColor(final Color color) {
        this.activeLinkColor = new Color(color.getRGB());
    }
    
    public void resize(final int width, final int height) {
        super.resize(this.width = width, this.height = height);
        this.width = this.size().width;
        this.height = this.size().height;
        this.updateBackground();
    }
    
    private int searchActiveLink() {
        int n = this.linkRanges[this.currentText].length - 1;
        int i = 0;
        while (i <= n) {
            final int n2 = (n + i) / 2;
            final int n3 = this.mouseX - this.offset;
            if (n3 < this.linkRanges[this.currentText][n2][0]) {
                n = n2 - 1;
            }
            else {
                if (n3 <= this.linkRanges[this.currentText][n2][1]) {
                    return n2;
                }
                i = n2 + 1;
            }
        }
        return -1;
    }
    
    public void setDelay(final int delay) {
        this.delay = delay;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public boolean mouseDrag(final Event event, final int mouseX, final int mouseY) {
        this.handleDisplacement(mouseX - this.mouseX);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.repaint();
        return true;
    }
    
    public void setAmplitude(final int amplitude) {
        this.amplitude = amplitude;
    }
    
    public int getAmplitude() {
        return this.amplitude;
    }
    
    public void setText(final URL url, final Font font) {
        String s2;
        final String s = s2 = "PhantomScroll was developed by <a href=\"mailto:um@mail.com\">Uldarico Muico Jr.</a>";
        if (url != null) {
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openConnection().getInputStream());
                s2 = dataInputStream.readLine();
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    s2 += line;
                }
                dataInputStream.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                s2 = s;
            }
        }
        this.setText(s2, font);
    }
    
    public void setText(String s, final Font font) {
        s += "<br>";
        final Vector vector = new Vector<String>();
        final Vector vector2 = new Vector<Hashtable<K, String>>();
        final Vector vector3 = new Vector<Vector<Hashtable<K, int[]>>>();
        int n = 0;
        this.textCount = 0;
        while (true) {
            final int index = s.indexOf(60, n);
            if (index == -1) {
                break;
            }
            final int searchBracket = this.searchBracket(s, index);
            final Properties tagProperties = this.parseTagProperties(s.substring(index + 1, searchBracket));
            while (vector.size() <= this.textCount) {
                vector.addElement(new String());
            }
            while (vector3.size() <= this.textCount) {
                vector3.addElement(new Vector<Hashtable<K, int[]>>());
            }
            vector.setElementAt(vector.elementAt(this.textCount).concat(s.substring(n, index)), this.textCount);
            final String property = tagProperties.getProperty("type");
            if (property.equals("br")) {
                if (vector2.size() > 0) {
                    String s2 = s.substring(searchBracket + 1);
                    s = s.substring(0, searchBracket + 1);
                    for (int i = 0; i < vector2.size(); ++i) {
                        final Hashtable<K, String> hashtable = vector2.elementAt(i);
                        s = s + "</" + hashtable.get("type") + ">";
                        s2 = hashtable.get("tag") + s2;
                        hashtable.remove("tag");
                    }
                    s = s + "<br>" + s2;
                }
                else {
                    ++this.textCount;
                }
            }
            else if (property.equals("a")) {
                final URL url = this.parseURL(tagProperties.getProperty("href"), this.getDocumentBase(), false);
                final String property2 = tagProperties.getProperty("target");
                final String property3 = tagProperties.getProperty("alt");
                final Hashtable<String, String> hashtable2 = new Hashtable<String, String>();
                hashtable2.put("type", "a");
                hashtable2.put("href", (String)url);
                if (property2 != null) {
                    hashtable2.put("target", property2);
                }
                hashtable2.put("range", (String)(Object)new int[] { vector.elementAt(this.textCount).length(), 0 });
                hashtable2.put("tag", s.substring(index, searchBracket + 1));
                hashtable2.put("alt", (property3 == null) ? url.toString() : property3);
                vector2.addElement((Hashtable<K, String>)hashtable2);
            }
            else if (property.equals("/a")) {
                for (int j = vector2.size() - 1; j >= 0; --j) {
                    final Hashtable<K, String> hashtable3 = vector2.elementAt(j);
                    if (hashtable3.get("type") == "a") {
                        ((int[])(Object)hashtable3.get("range"))[1] = vector.elementAt(this.textCount).length() - 1;
                        vector3.elementAt(this.textCount).addElement((Hashtable<K, int[]>)hashtable3);
                        vector2.removeElementAt(j);
                        break;
                    }
                }
            }
            n = searchBracket + 1;
        }
        vector2.removeAllElements();
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.textWidths = new int[this.textCount];
        this.links = new Hashtable[this.textCount][];
        this.linkRanges = new int[this.textCount][][];
        this.textPixels = new int[this.textCount][];
        for (int k = 0; k < this.textCount; ++k) {
            final String s3 = vector.elementAt(k);
            final int[] textPixels = this.createTextPixels(s3, font, this.fgColor);
            final Dimension textSize = this.getTextSize(s3, fontMetrics);
            this.textWidths[k] = textSize.width;
            this.textHeight = textSize.height;
            this.textPixels[k] = new int[this.textWidths[k] * this.textHeight];
            final Vector<Hashtable<K, int[]>> vector4 = vector3.elementAt(k);
            this.linkRanges[k] = new int[vector4.size()][];
            this.links[k] = new Hashtable[vector4.size()];
            for (int l = 0; l < vector4.size(); ++l) {
                this.links[k][l] = vector4.elementAt(l);
                final int[] array = this.links[k][l].get("range");
                (this.linkRanges[k][l] = new int[2])[0] = fontMetrics.stringWidth(s3.substring(0, array[0])) + fontMetrics.charWidth(' ');
                this.linkRanges[k][l][1] = this.linkRanges[k][l][0] + fontMetrics.stringWidth(s3.substring(array[0], array[1] + 1));
                for (int n2 = this.linkRanges[k][l][0]; n2 <= this.linkRanges[k][l][1] - 1; ++n2) {
                    final int[] array2 = textPixels;
                    final int n3 = (fontMetrics.getAscent() + 2) * this.textWidths[k] + n2;
                    array2[n3] |= 0xFF000000;
                }
            }
            this.filterPixels(textPixels, this.textWidths[k], this.textHeight, 0, 0, this.textWidths[k], this.textHeight, this.textPixels[k], this.textWidths[k], this.textHeight, 0, 0, 0);
        }
        this.offset = this.width;
        this.updateBackground();
        this.updateForeground();
        this.createFade();
    }
}
