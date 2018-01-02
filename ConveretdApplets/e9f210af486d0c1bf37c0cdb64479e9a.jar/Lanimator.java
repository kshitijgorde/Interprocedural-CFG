import java.applet.AppletContext;
import java.awt.Event;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lanimator extends Applet implements Runnable
{
    int[][] layer;
    Graphics offscreen;
    Thread runner;
    Image workspace;
    Image tempImage;
    int winWidth;
    int winHeight;
    Color bgColour;
    int[] pInts;
    double[] pDubs;
    int layers;
    int delay;
    Rocket[] rocket;
    Water[] water;
    Pic[] pic;
    StatSprite[] statSprite;
    LRSprite[] lrSprite;
    UDSprite[] udSprite;
    int pics;
    int waters;
    int statSprites;
    int lrSprites;
    int udSprites;
    URL homePage;
    
    public void init() {
        this.showStatus("Please wait - Initializing Applet");
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), this.getParameter("Filename"));
        mediaTracker.addImage(image, 0);
        this.winWidth = this.size().width;
        this.winHeight = this.size().height;
        try {
            this.homePage = new URL("http://www.soft.net.uk/watkins/lanimator/index.html");
        }
        catch (MalformedURLException ex) {}
        this.pInts = new int[40];
        this.pDubs = new double[40];
        final String parameter = this.getParameter("BGColour");
        if (parameter == null) {
            this.bgColour = new Color(0, 0, 0);
        }
        else {
            this.parseInts(parameter);
            this.bgColour = new Color(this.pInts[0], this.pInts[1], this.pInts[2]);
            this.workspace = this.createImage(this.winWidth, this.winHeight);
            this.offscreen = this.workspace.getGraphics();
            this.setBackground(this.bgColour);
        }
        final String parameter2 = this.getParameter("Delay");
        if (parameter2 == null) {
            this.delay = 100;
        }
        else {
            this.delay = Integer.valueOf(parameter2);
        }
        this.layers = Integer.valueOf(this.getParameter("Layers"));
        this.layer = new int[this.layers][2];
        this.parseInts(this.getParameter("Totals"));
        this.pics = this.pInts[0];
        this.waters = this.pInts[1];
        this.statSprites = this.pInts[2];
        this.lrSprites = this.pInts[3];
        this.udSprites = this.pInts[4];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        if (this.pics != 0) {
            this.pic = new Pic[this.pics];
        }
        if (this.waters != 0) {
            this.water = new Water[this.waters];
        }
        if (this.statSprites != 0) {
            this.statSprite = new StatSprite[this.statSprites];
        }
        if (this.lrSprites != 0) {
            this.lrSprite = new LRSprite[this.lrSprites];
        }
        if (this.udSprites != 0) {
            this.udSprite = new UDSprite[this.udSprites];
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.showStatus("The Loading Process Was Interrupted");
        }
        for (int i = 0; i < this.layers; ++i) {
            final String string = "Layer" + String.valueOf(i);
            final int intValue = Integer.valueOf(new StringTokenizer(this.getParameter(string)).nextToken());
            if (intValue == 0) {
                this.parseInts(this.getParameter(string));
                final MediaTracker mediaTracker2 = new MediaTracker(this);
                final Image image2 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(this.pInts[1], this.pInts[2], this.pInts[3], this.pInts[4])));
                mediaTracker2.addImage(image2, 0);
                try {
                    mediaTracker2.waitForAll();
                }
                catch (InterruptedException ex3) {}
                this.pic[n] = new Pic(this.pInts[5], this.pInts[6], image2);
                this.layer[i][0] = 0;
                this.layer[i][1] = n;
                ++n;
            }
            else if (intValue == 1) {
                this.parseDubs(this.getParameter(string));
                boolean b = false;
                if (this.pDubs[8] == 1.0) {
                    b = true;
                }
                this.water[n2] = new Water((int)this.pDubs[1], (int)this.pDubs[2], (int)this.pDubs[3], (int)this.pDubs[4], this.pDubs[5], this.pDubs[6], this.pDubs[7], b, (int)this.pDubs[9], (int)this.pDubs[10], (int)this.pDubs[11], (int)this.pDubs[12]);
                this.layer[i][0] = 1;
                this.layer[i][1] = n2;
                ++n2;
            }
            else if (intValue == 2) {
                this.parseInts(this.getParameter(string));
                final Color[] array = { new Color(255, 0, 0), new Color(255, 255, 0), new Color(255, 255, 255), new Color(255, 0, 255), new Color(0, 255, 0), new Color(128, 128, 255), new Color(0, 255, 255), new Color(255, 172, 87) };
                this.rocket = new Rocket[this.pInts[5]];
                for (int j = 0; j < this.pInts[5]; ++j) {
                    this.rocket[j] = new Rocket((int)(Math.random() * 40.0) + 40, array[j % 7], this.pInts[3], this.pInts[4]);
                }
                Rocket.xoffset = this.pInts[1];
                Rocket.yoffset = this.pInts[2];
                this.layer[i][0] = 2;
                this.layer[i][1] = this.pInts[5];
            }
            else if (intValue == 3) {
                this.parseInts(this.getParameter(string));
                final Image[] array2 = new Image[this.pInts[5]];
                final MediaTracker mediaTracker3 = new MediaTracker(this);
                for (int k = 0; k < this.pInts[5]; ++k) {
                    mediaTracker3.addImage(array2[k] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(this.pInts[1] + k * this.pInts[3], this.pInts[2], this.pInts[3], this.pInts[4]))), k);
                }
                try {
                    mediaTracker3.waitForAll();
                }
                catch (InterruptedException ex4) {}
                this.statSprite[n3] = new StatSprite(this.pInts[6], this.pInts[7], this.pInts[5], this.pInts[8], array2);
                this.layer[i][0] = 3;
                this.layer[i][1] = n3;
                ++n3;
            }
            else if (intValue == 4) {
                this.parseDubs(this.getParameter(string));
                final int n6 = (int)this.pDubs[1];
                final int n7 = (int)this.pDubs[2];
                final int n8 = (int)this.pDubs[3];
                final int n9 = (int)this.pDubs[4];
                final int n10 = (int)this.pDubs[5];
                final Image[] array3 = new Image[2 * (int)this.pDubs[5]];
                final MediaTracker mediaTracker4 = new MediaTracker(this);
                if (this.pDubs[7] == 0.0) {
                    if (this.pDubs[12] == 0.0) {
                        if (this.pDubs[6] == 0.0) {
                            for (int l = 0; l < n10; ++l) {
                                mediaTracker4.addImage(array3[l] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + l * n8, n7, n8, n9))), 0);
                            }
                        }
                        else {
                            for (int n11 = 0; n11 < n10; ++n11) {
                                final Image image3 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n11 * n8, n7, n8, n9)));
                                final MediaTracker mediaTracker5 = new MediaTracker(this);
                                mediaTracker5.addImage(image3, 0);
                                try {
                                    mediaTracker5.waitForAll();
                                }
                                catch (InterruptedException ex5) {}
                                this.horFlipImage(image3);
                                mediaTracker4.addImage(array3[n11] = image3, 0);
                            }
                        }
                    }
                    else if (this.pDubs[6] == 0.0) {
                        for (int n12 = 0; n12 < n10; ++n12) {
                            mediaTracker4.addImage(array3[n12 + n10] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n12 * n8, n7, n8, n9))), 0);
                        }
                    }
                    else {
                        for (int n13 = 0; n13 < n10; ++n13) {
                            final Image image4 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n13 * n8, n7, n8, n9)));
                            final MediaTracker mediaTracker6 = new MediaTracker(this);
                            mediaTracker6.addImage(image4, 0);
                            try {
                                mediaTracker6.waitForAll();
                            }
                            catch (InterruptedException ex6) {}
                            this.horFlipImage(image4);
                            mediaTracker4.addImage(array3[n13 + n10] = this.tempImage, 0);
                        }
                    }
                }
                else if (this.pDubs[6] == 0.0) {
                    for (int n14 = 0; n14 < n10 * 2; ++n14) {
                        mediaTracker4.addImage(array3[n14] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n14 * n8, n7, n8, n9))), 0);
                    }
                }
                else {
                    for (int n15 = 0; n15 < n10; ++n15) {
                        mediaTracker4.addImage(array3[n15] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n15 * n8, n7, n8, n9))), 0);
                    }
                    for (int n16 = 0; n16 < n10; ++n16) {
                        final Image image5 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n6 + n16 * n8, n7, n8, n9)));
                        final MediaTracker mediaTracker7 = new MediaTracker(this);
                        mediaTracker7.addImage(image5, 0);
                        try {
                            mediaTracker7.waitForAll();
                        }
                        catch (InterruptedException ex7) {}
                        this.horFlipImage(image5);
                        mediaTracker4.addImage(array3[n16 + n10] = this.tempImage, 0);
                    }
                }
                try {
                    mediaTracker4.waitForAll();
                }
                catch (InterruptedException ex8) {}
                boolean b2 = false;
                if (this.pDubs[7] == 1.0) {
                    b2 = true;
                }
                boolean b3 = false;
                if (this.pDubs[12] == 0.0) {
                    b3 = true;
                }
                this.lrSprite[n4] = new LRSprite((int)this.pDubs[8], (int)this.pDubs[9], (int)this.pDubs[10], (int)this.pDubs[5], b2, b3, this.pDubs[13], this.pDubs[11], array3);
                this.layer[i][0] = 4;
                this.layer[i][1] = n4;
                ++n4;
            }
            else if (intValue == 5) {
                this.parseDubs(this.getParameter(string));
                final int n17 = (int)this.pDubs[1];
                final int n18 = (int)this.pDubs[2];
                final int n19 = (int)this.pDubs[3];
                final int n20 = (int)this.pDubs[4];
                final int n21 = (int)this.pDubs[5];
                final Image[] array4 = new Image[2 * (int)this.pDubs[5]];
                final MediaTracker mediaTracker8 = new MediaTracker(this);
                if (this.pDubs[7] == 0.0) {
                    if (this.pDubs[12] == 0.0) {
                        if (this.pDubs[6] == 0.0) {
                            for (int n22 = 0; n22 < n21; ++n22) {
                                mediaTracker8.addImage(array4[n22] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n22 * n19, n18, n19, n20))), 0);
                            }
                        }
                        else {
                            for (int n23 = 0; n23 < n21; ++n23) {
                                final Image image6 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n23 * n19, n18, n19, n20)));
                                final MediaTracker mediaTracker9 = new MediaTracker(this);
                                mediaTracker9.addImage(image6, 0);
                                try {
                                    mediaTracker9.waitForAll();
                                }
                                catch (InterruptedException ex9) {}
                                this.verFlipImage(image6);
                                mediaTracker8.addImage(array4[n23] = image6, 0);
                            }
                        }
                    }
                    else if (this.pDubs[6] == 0.0) {
                        for (int n24 = 0; n24 < n21; ++n24) {
                            mediaTracker8.addImage(array4[n24 + n21] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n24 * n19, n18, n19, n20))), 0);
                        }
                    }
                    else {
                        for (int n25 = 0; n25 < n21; ++n25) {
                            final Image image7 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n25 * n19, n18, n19, n20)));
                            final MediaTracker mediaTracker10 = new MediaTracker(this);
                            mediaTracker10.addImage(image7, 0);
                            try {
                                mediaTracker10.waitForAll();
                            }
                            catch (InterruptedException ex10) {}
                            this.verFlipImage(image7);
                            mediaTracker8.addImage(array4[n25 + n21] = this.tempImage, 0);
                        }
                    }
                }
                else if (this.pDubs[6] == 0.0) {
                    for (int n26 = 0; n26 < n21 * 2; ++n26) {
                        mediaTracker8.addImage(array4[n26] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n26 * n19, n18, n19, n20))), 0);
                    }
                }
                else {
                    for (int n27 = 0; n27 < n21; ++n27) {
                        mediaTracker8.addImage(array4[n27] = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n27 * n19, n18, n19, n20))), 0);
                    }
                    for (int n28 = 0; n28 < n21; ++n28) {
                        final Image image8 = this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n17 + n28 * n19, n18, n19, n20)));
                        final MediaTracker mediaTracker11 = new MediaTracker(this);
                        mediaTracker11.addImage(image8, 0);
                        try {
                            mediaTracker11.waitForAll();
                        }
                        catch (InterruptedException ex11) {}
                        this.verFlipImage(image8);
                        mediaTracker8.addImage(array4[n28 + n21] = this.tempImage, 0);
                    }
                }
                try {
                    mediaTracker8.waitForAll();
                }
                catch (InterruptedException ex12) {}
                boolean b4 = false;
                if (this.pDubs[7] == 1.0) {
                    b4 = true;
                }
                boolean b5 = false;
                if (this.pDubs[12] == 0.0) {
                    b5 = true;
                }
                this.udSprite[n5] = new UDSprite((int)this.pDubs[8], (int)this.pDubs[9], (int)this.pDubs[10], (int)this.pDubs[5], b4, b5, this.pDubs[13], this.pDubs[11], array4);
                this.layer[i][0] = 5;
                this.layer[i][1] = n5;
                ++n5;
            }
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        while (this.runner != null) {
            this.repaint();
            try {
                currentTimeMillis += this.delay;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(this.bgColour);
        this.offscreen.fillRect(0, 0, this.winWidth, this.winHeight);
        for (int i = 0; i < this.layers; ++i) {
            switch (this.layer[i][0]) {
                case 0: {
                    this.pic[this.layer[i][1]].draw(this.offscreen);
                    break;
                }
                case 1: {
                    this.water[this.layer[i][1]].draw(this.offscreen, this.workspace);
                    break;
                }
                case 2: {
                    for (int j = 0; j < this.layer[i][1]; ++j) {
                        this.rocket[j].draw(this.offscreen);
                    }
                    break;
                }
                case 3: {
                    this.statSprite[this.layer[i][1]].draw(this.offscreen);
                    break;
                }
                case 4: {
                    this.lrSprite[this.layer[i][1]].draw(this.offscreen);
                    break;
                }
                case 5: {
                    this.udSprite[this.layer[i][1]].draw(this.offscreen);
                    break;
                }
            }
        }
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    void parseInts(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.pInts[n] = Integer.valueOf(stringTokenizer.nextToken());
            ++n;
        }
    }
    
    void parseDubs(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.pDubs[n] = Double.valueOf(stringTokenizer.nextToken());
            ++n;
        }
    }
    
    void horFlipImage(final Image image) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int n = width * height;
        final int[] array = new int[n];
        final int[] array2 = new int[n];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < width * height; i += width) {
            for (int j = 0; j < width; ++j) {
                array2[i + width - j - 1] = array[i + j];
            }
        }
        this.tempImage = this.createImage(new MemoryImageSource(width, height, array2, 0, width));
    }
    
    void verFlipImage(final Image image) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int n = width * height;
        final int[] array = new int[n];
        final int[] array2 = new int[n];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < width * height; i += width) {
            System.arraycopy(array, i, array2, n - width - i, width);
        }
        this.tempImage = this.createImage(new MemoryImageSource(width, height, array2, 0, width));
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.runner.stop();
        final AppletContext appletContext = this.getAppletContext();
        if (this.homePage != null) {
            appletContext.showDocument(this.homePage);
        }
        return true;
    }
}
