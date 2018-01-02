import java.util.Random;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerLine extends Applet implements Runnable
{
    private Thread timer;
    private Thread loader;
    private boolean allLoaded;
    private Image buffer;
    private Image background;
    private Rectangle tickerRect;
    private Vector textLines;
    private int textIndex;
    private int lineIndex;
    private int charWidth;
    private int charHeight;
    private int[] fontPixels;
    private int[] backPixels;
    private int[] linePixels;
    private Image lineBuffer;
    private int queueLength;
    private int queueIndex;
    private int[] queueColor;
    private char[] queueChar;
    private int charDelay;
    private int lineDelay;
    private boolean playSound;
    private AudioClip charSound;
    private AudioClip spaceSound;
    private AudioClip returnSound;
    private int currentColor;
    private char currentSign;
    
    public TickerLine() {
        this.allLoaded = false;
        this.textLines = new Vector();
        this.charDelay = 200;
        this.lineDelay = 1000;
        this.playSound = true;
        System.out.println(this.getAppletInfo());
    }
    
    public String getAppletInfo() {
        return "TickerLine, Version 1.0" + System.getProperty("line.separator") + "Copyright (c) 1999 by R\u00fcdiger Appel, All Rights Reserved" + System.getProperty("line.separator") + "See also: http://www.3quarks.com";
    }
    
    public void init() {
        final String parameter = this.getParameter("EnableSound");
        if (parameter != null && parameter.trim().equalsIgnoreCase("yes")) {
            this.playSound = true;
        }
        else if (parameter != null && parameter.trim().equalsIgnoreCase("no")) {
            this.playSound = false;
        }
        else {
            this.playSound = (!System.getProperty("os.name").equalsIgnoreCase("Windows 95") && !System.getProperty("os.name").equalsIgnoreCase("Windows 98"));
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("BackgroundColor"), ",");
            if (stringTokenizer.countTokens() == 1) {
                final String trim = stringTokenizer.nextToken().trim();
                this.setBackground(new Color(Integer.parseInt(trim.substring(1, 3), 16), Integer.parseInt(trim.substring(3, 5), 16), Integer.parseInt(trim.substring(5, 7), 16)));
                return;
            }
            this.setBackground(new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim())));
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.buffer == null) {
            this.buffer = this.createImage(width, height);
        }
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, width, height);
        if (this.background != null) {
            graphics2.drawImage(this.background, (width - this.background.getWidth(this)) / 2, (height - this.background.getHeight(this)) / 2, this);
        }
        if (this.lineBuffer != null) {
            graphics2.drawImage(this.lineBuffer, this.tickerRect.x, this.tickerRect.y, this.tickerRect.width, this.tickerRect.height, this);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    private void drawLine() {
        for (int i = 0; i < this.queueLength; ++i) {
            this.drawChar(i);
        }
        this.lineBuffer = this.createImage(new MemoryImageSource(this.tickerRect.width, this.tickerRect.height, this.linePixels, 0, this.tickerRect.width));
    }
    
    private void drawChar(final int n) {
        final char c = (char)(this.queueChar[n] - ' ');
        final int n2 = this.queueColor[n];
        int n3 = n * this.charWidth;
        int n4 = c / ' ' * this.charHeight * this.charWidth * 32 + c % ' ' * this.charWidth;
        for (int i = 0; i < this.charHeight; ++i) {
            for (int j = 0; j < this.charWidth; ++j) {
                final int n5 = this.fontPixels[n4] & 0xFF;
                if (n5 == 0) {
                    this.linePixels[n3] = this.backPixels[n3];
                }
                else {
                    this.linePixels[n3] = (0xFF000000 | ((this.backPixels[n3] >> 16 & 0xFF) * n5 + (n2 >> 16 & 0xFF) * (255 - n5) >> 8 & 0xFF) << 16 | ((this.backPixels[n3] >> 8 & 0xFF) * n5 + (n2 >> 8 & 0xFF) * (255 - n5) >> 8 & 0xFF) << 8 | ((this.backPixels[n3] & 0xFF) * n5 + (n2 & 0xFF) * (255 - n5) >> 8 & 0xFF));
                }
                ++n3;
                ++n4;
            }
            n3 += this.charWidth * (this.queueLength - 1);
            n4 += this.charWidth * 31;
        }
    }
    
    public void enableSound(final boolean playSound) {
        this.playSound = playSound;
    }
    
    public boolean isSoundEnabled() {
        return this.playSound;
    }
    
    public void start() {
        if (this.allLoaded) {
            this.tickerInit();
            (this.timer = new Thread(this)).start();
            return;
        }
        (this.loader = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.loader != null) {
            this.loader.stop();
            this.loader = null;
        }
        if (this.timer != null) {
            this.timer.stop();
            this.timer = null;
        }
    }
    
    public void run() {
        if (Thread.currentThread() == this.loader) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.background = this.loadImage(mediaTracker, "BackgroundImage", 0);
            if (this.background != null) {
                this.repaint();
            }
            final Image loadImage = this.loadImage(mediaTracker, "TickerFont", 1);
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("TickerRect"), ",");
                this.tickerRect = new Rectangle(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
            }
            catch (Exception ex) {
                this.tickerRect = new Rectangle(this.size());
            }
            if (loadImage != null) {
                final int width = loadImage.getWidth(this);
                final int height = loadImage.getHeight(this);
                this.charWidth = width / 32;
                this.charHeight = height / 3;
                final int width2 = this.charWidth * (this.tickerRect.width / this.charWidth);
                final Rectangle tickerRect = this.tickerRect;
                tickerRect.y += (this.tickerRect.height - this.charHeight) / 2;
                this.tickerRect.height = this.charHeight;
                final Rectangle tickerRect2 = this.tickerRect;
                tickerRect2.x += (this.tickerRect.width - width2) / 2;
                this.tickerRect.width = width2;
                this.fontPixels = new int[width * height];
                final PixelGrabber pixelGrabber = new PixelGrabber(loadImage, 0, 0, width, height, this.fontPixels, 0, width);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                if ((pixelGrabber.status() & 0x80) != 0x0) {
                    this.fontPixels = null;
                }
            }
            this.backPixels = new int[this.tickerRect.width * this.tickerRect.height];
            if (this.background == null) {
                final int n = this.tickerRect.width * this.tickerRect.height;
                final int rgb = this.getBackground().getRGB();
                for (int i = 0; i < n; ++i) {
                    this.backPixels[i] = rgb;
                }
            }
            else {
                final PixelGrabber pixelGrabber2 = new PixelGrabber(this.background, this.tickerRect.x, this.tickerRect.y, this.tickerRect.width, this.tickerRect.height, this.backPixels, 0, this.tickerRect.width);
                try {
                    pixelGrabber2.grabPixels();
                }
                catch (Exception ex3) {}
                if ((pixelGrabber2.status() & 0x80) != 0x0) {
                    this.backPixels = null;
                }
            }
            this.lineBuffer = this.createImage(this.tickerRect.width, this.tickerRect.height);
            this.linePixels = new int[this.tickerRect.width * this.tickerRect.height];
            this.queueLength = this.tickerRect.width / this.charWidth;
            this.queueChar = new char[this.queueLength];
            this.queueColor = new int[this.queueLength];
            this.loadText();
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("TickerDelay"), ",");
                this.charDelay = Integer.parseInt(stringTokenizer2.nextToken().trim());
                this.lineDelay = Integer.parseInt(stringTokenizer2.nextToken().trim());
            }
            catch (Exception ex4) {}
            this.charSound = this.loadAudioClip("CharSound");
            this.spaceSound = this.loadAudioClip("SpaceSound");
            this.returnSound = this.loadAudioClip("ReturnSound");
            if (this.fontPixels != null && this.backPixels != null && this.linePixels != null && this.lineBuffer != null && this.queueChar != null && this.queueColor != null && !this.textLines.isEmpty()) {
                this.tickerInit();
                this.allLoaded = true;
                (this.timer = new Thread(this)).start();
            }
        }
        if (Thread.currentThread() != this.timer) {
            return;
        }
        while (true) {
            this.tickerStep();
            if (this.currentSign == '\0') {
                if (this.playSound && this.returnSound != null) {
                    this.returnSound.play();
                }
                try {
                    Thread.sleep(this.lineDelay);
                }
                catch (InterruptedException ex5) {}
            }
            this.drawLine();
            this.repaint();
            if (this.playSound && this.currentSign != '\0') {
                if (this.currentSign == ' ' && this.spaceSound != null) {
                    this.spaceSound.play();
                }
                else if (this.charSound != null) {
                    this.charSound.play();
                }
            }
            try {
                Thread.sleep(this.charDelay);
            }
            catch (InterruptedException ex6) {}
        }
    }
    
    private Image loadImage(final MediaTracker mediaTracker, final String s, final int n) {
        try {
            final Image image = this.getImage(this.getCodeBase(), this.getParameter(s).trim());
            mediaTracker.addImage(image, n);
            mediaTracker.waitForID(n);
            if (!mediaTracker.isErrorID(n)) {
                return image;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private AudioClip loadAudioClip(final String s) {
        try {
            return this.getAudioClip(this.getCodeBase(), this.getParameter(s));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void loadText() {
        final String parameter = this.getParameter("TickerText");
        if (parameter != null) {
            this.addTextLine(parameter);
            return;
        }
        final String parameter2 = this.getParameter("TickerUrl");
        if (parameter2 == null) {
            this.addTextLine("TickerLine, Version 1.0");
            this.addTextLine("Copyright (c) 1999 by R\u00fcdiger Appel, All Rights Reserved");
            this.addTextLine("See also: http://www.3quarks.com");
            return;
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getCodeBase(), parameter2).openStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                this.addTextLine(line);
            }
            dataInputStream.close();
        }
        catch (Exception ex) {}
    }
    
    private void addTextLine(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= ' ' && char1 <= '\u0080') {
                sb.append(char1);
            }
            else if (char1 == '\u00e4') {
                sb.append("ae");
            }
            else if (char1 == '\u00f6') {
                sb.append("oe");
            }
            else if (char1 == '\u00fc') {
                sb.append("ue");
            }
            else if (char1 == '\u00c4') {
                sb.append("Ae");
            }
            else if (char1 == '\u00d6') {
                sb.append("Oe");
            }
            else if (char1 == '\u00dc') {
                sb.append("Ue");
            }
            else if (char1 == '\u00df') {
                sb.append("ss");
            }
        }
        this.textLines.addElement(sb.toString());
    }
    
    private void tickerInit() {
        for (int i = 0; i < this.queueLength; ++i) {
            this.queueChar[i] = ' ';
        }
        this.queueIndex = 0;
        this.textIndex = 0;
        this.lineIndex = 0;
        this.currentColor = -16777216;
    }
    
    private void tickerStep() {
        this.currentSign = this.getNextSign();
        if (this.currentSign == '\0') {
            for (int i = 0; i < this.queueLength; ++i) {
                this.queueChar[i] = ' ';
            }
            this.queueIndex = 0;
            return;
        }
        if (this.queueIndex < this.queueLength) {
            this.queueChar[this.queueIndex] = this.currentSign;
            this.queueColor[this.queueIndex] = this.currentColor;
            ++this.queueIndex;
            return;
        }
        for (int j = 0; j < this.queueLength - 1; ++j) {
            this.queueChar[j] = this.queueChar[j + 1];
            this.queueColor[j] = this.queueColor[j + 1];
        }
        this.queueChar[this.queueLength - 1] = this.currentSign;
        this.queueColor[this.queueLength - 1] = this.currentColor;
    }
    
    private char getNextSign() {
        final String s = this.textLines.elementAt(this.lineIndex);
        if (this.textIndex >= s.length()) {
            this.textIndex = 0;
            this.lineIndex = (this.lineIndex + 1) % this.textLines.size();
            return '\0';
        }
        char c = s.charAt(this.textIndex++);
        if (c == '#') {
            if (this.textIndex >= s.length()) {
                return this.getNextSign();
            }
            c = s.charAt(this.textIndex++);
            if (c != '#') {
                try {
                    final String substring = s.substring(this.textIndex - 1, this.textIndex + 5);
                    if (substring.equalsIgnoreCase("random")) {
                        this.currentColor = (0xFF000000 | new Random().nextInt());
                    }
                    else {
                        this.currentColor = new Color(Integer.parseInt(substring.substring(0, 2), 16), Integer.parseInt(substring.substring(2, 4), 16), Integer.parseInt(substring.substring(4, 6), 16)).getRGB();
                    }
                }
                catch (Exception ex) {}
                this.textIndex += 5;
                return this.getNextSign();
            }
        }
        return c;
    }
}
