import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Blackboard extends Applet implements Runnable
{
    private Thread loader;
    private boolean allLoaded;
    private Image buffer;
    private Image background;
    private Image[] textImage;
    private int textCount;
    private int[] textLine1;
    private int[] textLine2;
    private int[] textLine3;
    private int language;
    
    public Blackboard() {
        this.allLoaded = false;
        this.textCount = 14;
        this.textLine1 = new int[] { 0, 2, 8, 5, 9, 12 };
        this.textLine2 = new int[] { 1, 3, 8, 6, 10, 12 };
        this.textLine3 = new int[] { 1, 4, 8, 7, 11, 13 };
        System.out.println(this.getAppletInfo());
    }
    
    public String getAppletInfo() {
        return "Blackboard, Version 1.0" + System.getProperty("line.separator") + "Copyright (c) 1999 by R\u00fcdiger Appel, All Rights Reserved" + System.getProperty("line.separator") + "See also: http://www.3quarks.com";
    }
    
    public void init() {
        final Color colorParameter = this.getColorParameter("BackgroundColor");
        if (colorParameter != null) {
            this.setBackground(colorParameter);
        }
        try {
            if (this.getParameter("Language").trim().equalsIgnoreCase("Deutsch")) {
                this.language = 1;
            }
        }
        catch (Exception ex) {}
        if (this.language == 1) {
            this.textLine1[2] = 5;
            this.textLine2[2] = 6;
            this.textLine3[2] = 7;
            this.textLine1[3] = 8;
            this.textLine2[3] = 8;
            this.textLine3[3] = 8;
        }
    }
    
    private Color getColorParameter(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            switch (stringTokenizer.countTokens()) {
                case 1: {
                    final String trim = stringTokenizer.nextToken().trim();
                    if (trim.length() == 7 && trim.charAt(0) == '#') {
                        return new Color(Integer.parseInt(trim.substring(1, 3), 16), Integer.parseInt(trim.substring(3, 5), 16), Integer.parseInt(trim.substring(5, 7), 16));
                    }
                    return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                case 3: {
                    return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
            }
        }
        catch (Exception ex) {}
        return null;
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
        graphics2.clipRect(0, 0, width, height);
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, width, height);
        if (this.background != null) {
            final int width2 = this.background.getWidth(this);
            final int height2 = this.background.getHeight(this);
            for (int i = 0; i < width; i += width2) {
                for (int j = 0; j < height; j += height2) {
                    graphics2.drawImage(this.background, i, j, this);
                }
            }
        }
        if (this.textImage != null) {
            this.paintTextLine(graphics2, 17, 16, this.textLine1);
            this.paintTextLine(graphics2, 17, 45, this.textLine2);
            this.paintTextLine(graphics2, 17, 74, this.textLine3);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void paintTextLine(final Graphics graphics, int n, final int n2, final int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            final int n3 = array[i];
            if (n3 >= 0 && n3 < this.textImage.length && this.textImage[n3] != null) {
                graphics.drawImage(this.textImage[n3], n, n2, this);
                n += this.textImage[n3].getWidth(this);
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        final String s = (String)o;
        if (s.equalsIgnoreCase("RedButton")) {
            this.textLine1[1] = 2;
            this.textLine2[1] = 3;
            this.textLine3[1] = 4;
        }
        else if (s.equalsIgnoreCase("YellowButton")) {
            this.textLine1[1] = 3;
            this.textLine2[1] = 4;
            this.textLine3[1] = 2;
        }
        else {
            if (!s.equalsIgnoreCase("GreenButton")) {
                return super.action(event, o);
            }
            this.textLine1[1] = 4;
            this.textLine2[1] = 2;
            this.textLine3[1] = 3;
        }
        if (Math.random() > 0.5) {
            final int n = this.textLine2[1];
            this.textLine2[1] = this.textLine3[1];
            this.textLine3[1] = n;
        }
        this.randomize((this.language == 1) ? 2 : 3, 5, 6, 7);
        this.randomize(4, 9, 10, 11);
        this.repaint();
        return super.action(event, o);
    }
    
    public void randomize(final int n, final int n2, final int n3, final int n4) {
        switch ((int)(Object)new Double(Math.random() * 3.0)) {
            case 0: {
                this.textLine1[n] = n2;
                this.textLine2[n] = n3;
                this.textLine3[n] = n4;
                break;
            }
            case 1: {
                this.textLine1[n] = n3;
                this.textLine2[n] = n4;
                this.textLine3[n] = n2;
                break;
            }
            default: {
                this.textLine1[n] = n4;
                this.textLine2[n] = n2;
                this.textLine3[n] = n3;
                break;
            }
        }
        if (Math.random() > 0.5) {
            final int n5 = this.textLine2[n];
            this.textLine2[n] = this.textLine3[n];
            this.textLine3[n] = n5;
        }
    }
    
    public void start() {
        this.textLine1[1] = 2;
        this.textLine2[1] = 3;
        this.textLine3[1] = 4;
        this.textLine1[4] = 9;
        this.textLine2[4] = 10;
        this.textLine3[4] = 11;
        if (this.language == 1) {
            this.textLine1[2] = 5;
            this.textLine2[2] = 6;
            this.textLine3[2] = 7;
        }
        else {
            this.textLine1[3] = 5;
            this.textLine2[3] = 6;
            this.textLine3[3] = 7;
        }
        if (!this.allLoaded) {
            (this.loader = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loader != null) {
            this.loader.stop();
            this.loader = null;
        }
    }
    
    public void run() {
        if (Thread.currentThread() == this.loader) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.background = this.loadImage(mediaTracker, this.getParameter("BackgroundImage"), 1);
            if (this.background != null) {
                this.repaint();
            }
            final String parameter = this.getParameter("ImagePrefix");
            if (parameter != null) {
                this.textImage = new Image[this.textCount];
                for (int i = 1; i <= this.textCount; ++i) {
                    this.textImage[i - 1] = this.loadImage(mediaTracker, parameter + ((i < 10) ? "0" : "") + i + ".gif", 1 + i);
                }
                this.repaint();
            }
            this.allLoaded = true;
        }
    }
    
    private Image loadImage(final MediaTracker mediaTracker, final String s, final int n) {
        if (s != null) {
            final Image image = this.getImage(this.getCodeBase(), s);
            mediaTracker.addImage(image, n);
            try {
                mediaTracker.waitForID(n);
                if (!mediaTracker.isErrorID(n)) {
                    return image;
                }
            }
            catch (InterruptedException ex) {}
        }
        return null;
    }
}
