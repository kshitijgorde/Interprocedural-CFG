import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaSlider extends Applet
{
    final int NUMBUTTS = 2;
    int error;
    int buttonSizeX;
    int buttonSizeY;
    int picAreaTopLeftX;
    int picAreaTopLeftY;
    int picAreaBottomRightX;
    int picAreaBottomRightY;
    int[][] buttonPositions;
    int numPics;
    int lastPic;
    int currentLevel;
    int buttonActive;
    String picExt;
    String picDir;
    String audioDir;
    String baseUrl;
    Image bg;
    Image currImage;
    Image[] buttons;
    Image offScreenImage;
    SliderPuzzle puzzle;
    
    public JavaSlider() {
        this.numPics = 1;
        this.lastPic = 1;
        this.currentLevel = 1;
        this.buttonActive = -1;
        this.picExt = ".jpg";
        this.picDir = "";
        this.audioDir = "";
        this.baseUrl = "http://javaboutique.internet.com";
    }
    
    public void loadPuzzlePic(final int level) {
        final MediaTracker mt = new MediaTracker(this);
        if (this.lastPic > this.numPics) {
            this.lastPic = 1;
        }
        try {
            mt.addImage(this.currImage = this.getImage(new URL(this.baseUrl), this.picDir + "pic" + String.valueOf(this.lastPic) + this.picExt), 0);
        }
        catch (MalformedURLException ex) {}
        try {
            mt.waitForID(0);
        }
        catch (Exception ex2) {}
        this.puzzle = new SliderPuzzle(this.currImage, level, this.picAreaTopLeftX, this.picAreaTopLeftY, this.picAreaBottomRightX, this.picAreaBottomRightY);
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.buttonActive = -1;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics g) {
        if (this.error == 0) {
            g.drawImage(this.bg, 0, 0, this);
            this.puzzle.update(g);
            if (this.buttonActive > -1) {
                g.drawImage(this.buttons[this.buttonActive], this.buttonPositions[this.buttonActive][0], this.buttonPositions[this.buttonActive][1], this);
            }
        }
        else {
            switch (this.error) {
                case 1: {
                    g.drawString("Illegal ButtonSize Parameter: " + this.getParameter("ButtonSize"), 10, 20);
                    break;
                }
                case 2: {
                    g.drawString("Illegal PictureTopLeft Parameter: " + this.getParameter("PictureTopLeft"), 10, 20);
                    break;
                }
                case 3: {
                    g.drawString("Illegal PictureBottomRight Parameter: " + this.getParameter("PictureBottomRight"), 10, 20);
                    break;
                }
                case 4: {
                    g.drawString("Illegal ButtonPosition1 Parameter: " + this.getParameter("ButtonPosition1"), 10, 20);
                    break;
                }
                case 5: {
                    g.drawString("Illegal ButtonPosition2 Parameter: " + this.getParameter("ButtonPosition2"), 10, 20);
                    break;
                }
                case 6: {
                    g.drawString("Illegal ButtonPosition3 Parameter: " + this.getParameter("ButtonPosition3"), 10, 20);
                    break;
                }
                case 7: {
                    g.drawString("Illegal ButtonPosition4 Parameter: " + this.getParameter("ButtonPosition4"), 10, 20);
                    break;
                }
                case 8: {
                    g.drawString("Illegal NumberPictures Parameter: " + this.getParameter("NumberPictures"), 10, 20);
                    break;
                }
                case 9: {
                    g.drawString("Illegal PictureExtension Parameter: " + this.getParameter("PictureExtension"), 10, 20);
                    break;
                }
                case 10: {
                    g.drawString("Illegal ImageDir Parameter: " + this.getParameter("ImageDir"), 10, 20);
                    break;
                }
                case 11: {
                    g.drawString("Illegal AudioDir Parameter: " + this.getParameter("AudioDir"), 10, 20);
                    break;
                }
                default: {
                    g.drawString("Unknown Error...", 10, 20);
                    break;
                }
            }
        }
    }
    
    public void update(final Graphics g) {
        final Graphics offG = this.offScreenImage.getGraphics();
        this.paint(offG);
        g.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public void newDiff(final int level) {
        this.currentLevel = level;
        this.puzzle = new SliderPuzzle(this.currImage, level, this.picAreaTopLeftX, this.picAreaTopLeftY, this.picAreaBottomRightX, this.picAreaBottomRightY);
        this.repaint();
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.picAreaTopLeftX <= x && x <= this.picAreaBottomRightX && this.picAreaTopLeftY <= y && y <= this.picAreaBottomRightY) {
            this.puzzle.mouseClick(x, y);
        }
        else {
            switch (this.buttonActive) {
                case 0: {
                    this.puzzle.solvePuzzle();
                    break;
                }
                case 1: {
                    ++this.lastPic;
                    this.loadPuzzlePic(this.currentLevel);
                    break;
                }
            }
        }
        return true;
    }
    
    public void init() {
        StringTokenizer st = null;
        if (this.getParameter("ButtonSize") != null) {
            st = new StringTokenizer(this.getParameter("ButtonSize"), ",");
            try {
                this.buttonSizeX = Integer.parseInt(st.nextToken());
                this.buttonSizeY = Integer.parseInt(st.nextToken());
            }
            catch (NoSuchElementException e) {
                this.error = 1;
                System.out.println(e);
            }
        }
        else {
            this.error = 1;
        }
        if (this.getParameter("PictureTopLeft") != null) {
            st = new StringTokenizer(this.getParameter("PictureTopLeft"), ",");
            try {
                this.picAreaTopLeftX = Integer.parseInt(st.nextToken());
                this.picAreaTopLeftY = Integer.parseInt(st.nextToken());
            }
            catch (NoSuchElementException e) {
                this.error = 2;
                System.out.println(e);
            }
        }
        else {
            this.error = 2;
        }
        if (this.getParameter("PictureBottomRight") != null) {
            st = new StringTokenizer(this.getParameter("PictureBottomRight"), ",");
            try {
                this.picAreaBottomRightX = Integer.parseInt(st.nextToken());
                this.picAreaBottomRightY = Integer.parseInt(st.nextToken());
            }
            catch (NoSuchElementException e) {
                this.error = 3;
                System.out.println(e);
            }
        }
        else {
            this.error = 3;
        }
        this.buttonPositions = new int[2][2];
        int i = 0;
        do {
            if (this.getParameter("ButtonPosition" + String.valueOf(i + 1)) != null) {
                st = new StringTokenizer(this.getParameter("ButtonPosition" + String.valueOf(i + 1)), ",");
                try {
                    this.buttonPositions[i][0] = Integer.parseInt(st.nextToken());
                    this.buttonPositions[i][1] = Integer.parseInt(st.nextToken());
                }
                catch (NoSuchElementException e) {
                    this.error = 4 + i;
                    System.out.println(e);
                }
            }
            else {
                this.error = 4 + i;
            }
        } while (++i < 2);
        if (this.getParameter("NumberPictures") != null) {
            this.numPics = Integer.parseInt(this.getParameter("NumberPictures"));
        }
        else {
            this.error = 8;
        }
        if (this.getParameter("PictureExtension") == null) {
            this.error = 9;
        }
        else if (this.getParameter("PictureExtension").equals("jpg")) {
            this.picExt = ".jpg";
        }
        else if (this.getParameter("PictureExtension").equals("gif")) {
            this.picExt = ".gif";
        }
        else {
            this.error = 9;
        }
        if (this.getParameter("ImageDir") != null) {
            this.picDir = this.getParameter("ImageDir");
        }
        else {
            this.error = 10;
        }
        this.offScreenImage = this.createImage(this.size().width, this.size().height);
        if (this.error == 0) {
            final MediaTracker mt = new MediaTracker(this);
            Image tempImg = null;
            this.buttons = new Image[2];
            try {
                tempImg = this.getImage(new URL(this.baseUrl), this.picDir + "bg.jpg");
            }
            catch (MalformedURLException e) {
                this.error = 12;
                System.out.println("Bad Url");
            }
            mt.addImage(tempImg, 0);
            try {
                mt.waitForID(0);
            }
            catch (Exception ex) {}
            final ImageProducer allProducer = tempImg.getSource();
            ImageFilter filter = new CropImageFilter(0, 0, this.size().width, this.size().height);
            mt.addImage(this.bg = this.createImage(new FilteredImageSource(allProducer, filter)), 1);
            int j = 0;
            do {
                filter = new CropImageFilter(this.buttonSizeX * j, this.size().height - 1, this.buttonSizeX, this.buttonSizeY);
                mt.addImage(this.buttons[j] = this.createImage(new FilteredImageSource(allProducer, filter)), 1);
            } while (++j < 2);
            try {
                mt.waitForID(1);
            }
            catch (Exception ex2) {}
            this.loadPuzzlePic(this.currentLevel);
        }
    }
    
    public boolean mouseMove(final Event e, final int x, final int y) {
        if (this.error == 0) {
            int i = 0;
            while (true) {
                while (this.buttonPositions[i][0] > x || x > this.buttonPositions[i][0] + this.buttonSizeX || this.buttonPositions[i][1] > y || y > this.buttonPositions[i][1] + this.buttonSizeY) {
                    this.buttonActive = -1;
                    if (++i >= 2) {
                        this.repaint();
                        return true;
                    }
                }
                this.buttonActive = i;
                continue;
            }
        }
        return true;
    }
}
