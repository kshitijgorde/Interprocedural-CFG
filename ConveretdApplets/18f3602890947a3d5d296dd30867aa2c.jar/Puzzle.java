import java.awt.Event;
import java.util.Random;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Puzzle extends Applet implements Runnable
{
    int AppletW;
    int AppletH;
    Thread t;
    Image[] Bitmaps;
    URL[] bitmapURL;
    int CurrentImage;
    int NumImages;
    int imageDelay;
    boolean Initialized;
    Image offScrImage;
    Graphics offScrGC;
    Image[][][] Pieces;
    int[] PieceW;
    int[] PieceH;
    Point[][] RightPos;
    Point pblack;
    boolean error;
    boolean completed;
    MediaTracker tracker;
    URL JumpTo;
    static final int dirLeft = 0;
    static final int dirRight = 1;
    static final int dirUp = 2;
    static final int dirDown = 3;
    
    public String getAppletInfo() {
        return "Puzzle v2.0, by Axel Fontaine";
    }
    
    void urlError(final String s) {
        final String string = "Puzzle: " + s + " URL not found";
        this.showStatus(string);
        System.err.println(string);
    }
    
    public void init() {
        final Dimension size = this.size();
        this.AppletW = size.width;
        this.AppletH = size.height;
        this.bitmapURL = new URL[255];
        String parameter;
        do {
            parameter = this.getParameter("IMAGE" + this.NumImages);
            if (parameter != null) {
                try {
                    this.bitmapURL[this.NumImages] = new URL(parameter);
                }
                catch (MalformedURLException ex) {
                    try {
                        this.bitmapURL[this.NumImages] = new URL(this.getDocumentBase(), parameter);
                    }
                    catch (MalformedURLException ex2) {
                        this.urlError("Image" + this.NumImages);
                        parameter = null;
                        --this.NumImages;
                    }
                }
                ++this.NumImages;
            }
        } while (parameter != null);
        System.err.println("Puzzle: Total images:" + this.NumImages);
        if (this.NumImages == 0) {
            this.error = true;
            this.repaint();
            return;
        }
        final String parameter2 = this.getParameter("JUMPTO");
        try {
            this.JumpTo = new URL(parameter2);
        }
        catch (MalformedURLException ex3) {
            try {
                this.JumpTo = new URL(this.getDocumentBase(), parameter2);
            }
            catch (MalformedURLException ex4) {
                this.urlError("Jump To");
            }
        }
        final String parameter3 = this.getParameter("DELAY");
        try {
            final Integer n = new Integer(parameter3);
            if (n > 0) {
                this.imageDelay = n;
            }
        }
        catch (NumberFormatException ex5) {}
        this.offScrImage = this.createImage(this.AppletW, this.AppletH);
        (this.offScrGC = this.offScrImage.getGraphics()).setColor(Color.black);
    }
    
    public void start() {
        if (this.t == null && !this.error) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && !this.error) {
            this.t.stop();
            this.t = null;
        }
    }
    
    public void updateOffScreen() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (i == this.pblack.x && j == this.pblack.y) {
                    this.offScrGC.fillRect(n, n2, this.AppletW / 4, this.AppletH / 4);
                }
                else {
                    this.offScrGC.drawImage(this.Pieces[this.CurrentImage][this.RightPos[i][j].x][this.RightPos[i][j].y], n, n2, this.AppletW / 4, this.AppletH / 4, Color.white, this);
                }
                n2 += this.AppletH / 4;
            }
            n += this.AppletW / 4;
            n2 = 0;
        }
    }
    
    public void run() {
        if (!this.Initialized) {
            this.repaint();
            this.Bitmaps = new Image[this.NumImages];
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.showStatus("Puzzle: Loading images...");
            for (int i = 0; i < this.NumImages; ++i) {
                System.err.println("Puzzle: Loading " + this.bitmapURL[i]);
                mediaTracker.addImage(this.Bitmaps[i] = this.getImage(this.bitmapURL[i]), i);
            }
            int n = 0;
            try {
                mediaTracker.waitForAll();
                n = (mediaTracker.isErrorAny() ? 0 : 1);
            }
            catch (InterruptedException ex) {}
            if (n == 0) {
                this.stop();
                this.showStatus("Puzzle: Error loading images !");
                this.error = true;
                this.repaint();
                return;
            }
            this.showStatus("Puzzle: Creating pieces... Please wait.");
            this.PieceW = new int[this.NumImages];
            this.PieceH = new int[this.NumImages];
            for (int j = 0; j < this.NumImages; ++j) {
                this.PieceW[j] = this.Bitmaps[j].getWidth(this) / 4;
                this.PieceH[j] = this.Bitmaps[j].getHeight(this) / 4;
            }
            this.Pieces = new Image[this.NumImages][4][4];
            this.RightPos = new Point[4][4];
            for (int k = 0; k < 4; ++k) {
                for (int l = 0; l < 4; ++l) {
                    this.showStatus("Puzzle: creating piece " + (k * 4 + l + 1) + "/" + 16);
                    this.RightPos[k][l] = new Point(k, l);
                    for (int n2 = 0; n2 < this.NumImages; ++n2) {
                        mediaTracker.addImage(this.Pieces[n2][k][l] = this.createImage(new FilteredImageSource(this.Bitmaps[n2].getSource(), new CropImageFilter(k * this.PieceW[n2], l * this.PieceH[n2], this.PieceW[n2], this.PieceH[n2]))), 0);
                    }
                }
            }
            this.pblack = new Point(3, 3);
            int n3 = 0;
            try {
                mediaTracker.waitForAll();
                n3 = (mediaTracker.isErrorAny() ? 0 : 1);
            }
            catch (InterruptedException ex2) {}
            if (n3 == 0) {
                this.stop();
                this.error = true;
                this.repaint();
                return;
            }
            this.showStatus("Puzzle: suffling pieces...");
            int n4 = 3;
            final Random random = new Random();
            for (int n5 = 0; n5 < 255; ++n5) {
                final Point point = new Point(this.RightPos[this.pblack.x][this.pblack.y]);
                boolean b = false;
                while (true) {
                    final int round = Math.round(random.nextFloat() * 3.0f);
                    if (round != n4) {
                        switch (round) {
                            case 0: {
                                if (this.pblack.x > 0) {
                                    this.RightPos[this.pblack.x][this.pblack.y] = this.RightPos[this.pblack.x - 1][this.pblack.y];
                                    this.RightPos[this.pblack.x - 1][this.pblack.y] = point;
                                    final Point pblack = this.pblack;
                                    --pblack.x;
                                    n4 = 1;
                                    b = true;
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (this.pblack.x < 3) {
                                    this.RightPos[this.pblack.x][this.pblack.y] = this.RightPos[this.pblack.x + 1][this.pblack.y];
                                    this.RightPos[this.pblack.x + 1][this.pblack.y] = point;
                                    final Point pblack2 = this.pblack;
                                    ++pblack2.x;
                                    n4 = 0;
                                    b = true;
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                if (this.pblack.y > 0) {
                                    this.RightPos[this.pblack.x][this.pblack.y] = this.RightPos[this.pblack.x][this.pblack.y - 1];
                                    this.RightPos[this.pblack.x][this.pblack.y - 1] = point;
                                    final Point pblack3 = this.pblack;
                                    --pblack3.y;
                                    n4 = 3;
                                    b = true;
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                if (this.pblack.y < 3) {
                                    this.RightPos[this.pblack.x][this.pblack.y] = this.RightPos[this.pblack.x][this.pblack.y + 1];
                                    this.RightPos[this.pblack.x][this.pblack.y + 1] = point;
                                    final Point pblack4 = this.pblack;
                                    ++pblack4.y;
                                    n4 = 2;
                                    b = true;
                                    break;
                                }
                                break;
                            }
                        }
                        if (b) {
                            break;
                        }
                        continue;
                    }
                }
            }
            this.showStatus("");
            this.Initialized = true;
        }
        this.repaint();
    Label_1057_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        ++this.CurrentImage;
                        if (this.CurrentImage == this.NumImages) {
                            this.CurrentImage = 0;
                        }
                        this.updateOffScreen();
                        this.repaint();
                        Thread.sleep(this.imageDelay);
                    }
                }
                catch (InterruptedException ex3) {
                    this.stop();
                    continue Label_1057_Outer;
                }
                continue;
            }
        }
    }
    
    public Point posToPuzzle(final Point point) {
        return new Point(point.x / (this.AppletW / 4), point.y / (this.AppletH / 4));
    }
    
    public void didSucceed() {
        this.completed = true;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (this.RightPos[i][j].x != i || this.RightPos[i][j].y != j) {
                    this.completed = false;
                    return;
                }
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.completed || this.error || !this.Initialized) {
            return false;
        }
        final Point posToPuzzle = this.posToPuzzle(new Point(n, n2));
        if ((this.pblack.y == posToPuzzle.y && (this.pblack.x == posToPuzzle.x + 1 || this.pblack.x == posToPuzzle.x - 1)) || (this.pblack.x == posToPuzzle.x && (this.pblack.y == posToPuzzle.y + 1 || this.pblack.y == posToPuzzle.y - 1))) {
            final Point point = this.RightPos[posToPuzzle.x][posToPuzzle.y];
            this.RightPos[posToPuzzle.x][posToPuzzle.y] = this.RightPos[this.pblack.x][this.pblack.y];
            this.RightPos[this.pblack.x][this.pblack.y] = point;
            this.pblack = posToPuzzle;
            this.didSucceed();
            this.repaint();
            if (this.completed && this.JumpTo != null) {
                this.getAppletContext().showDocument(this.JumpTo);
            }
        }
        return false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.error) {
            graphics.setColor(Color.black);
            graphics.drawString("Error !", 10, 10);
            graphics.drawString("Details on Java Console", 10, 40);
            return;
        }
        if (this.completed) {
            graphics.drawImage(this.Bitmaps[this.CurrentImage], 0, 0, this.AppletW, this.AppletH, Color.black, this);
            return;
        }
        if (this.Initialized) {
            graphics.drawImage(this.offScrImage, 0, 0, this);
            return;
        }
        graphics.setColor(Color.black);
        graphics.drawString("Loading...", 10, 25);
    }
    
    public Puzzle() {
        this.imageDelay = 200;
        this.Initialized = false;
        this.error = false;
        this.completed = false;
    }
}
