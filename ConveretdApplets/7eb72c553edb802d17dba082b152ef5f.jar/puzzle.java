import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Event;
import java.awt.Canvas;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class puzzle extends Applet
{
    Image origImage;
    Graphics bg;
    Graphics cg;
    int rows;
    int columns;
    boolean reset;
    Vector puzzle;
    int imgHeight;
    int imgWidth;
    int sliceHeight;
    int sliceWidth;
    int swapCount;
    int clickCount;
    int jumbleCount;
    int swapImage1Position;
    int swapImage2Position;
    Applet controller;
    appletController ac;
    boolean started;
    Canvas imgCanvas;
    
    void puzzle_MouseEnter(final Event event) {
        this.repaint();
    }
    
    public void btnStartClicked() {
        this.jumbleCount = 0;
        this.started = true;
        this.randomize();
        this.clickCount = 0;
        this.repaint();
    }
    
    void imgCanvas_MouseDown(final Event event) {
        final int r = event.y / this.sliceHeight;
        final int c = event.x / this.sliceWidth;
        final int imgPosition = r * this.columns + c;
        this.reset = false;
        ++this.swapCount;
        final imageTracker it = this.puzzle.elementAt(imgPosition);
        it.color = Color.red;
        if (this.swapCount == 1) {
            this.swapImage1Position = imgPosition;
        }
        else {
            this.swapImage2Position = imgPosition;
        }
        if (this.swapCount == 2) {
            this.swapImages(this.swapImage1Position, this.swapImage2Position, false);
            this.swapCount = 0;
            ++this.clickCount;
            ((puzzleControl)this.controller).setCount(this.clickCount);
        }
        if (this.jumbleCount == 0 && this.clickCount > 0) {
            ((puzzleControl)this.controller).setStatus("YOU WIN!!");
        }
        this.repaint();
    }
    
    private void randomize() {
        final int num = this.rows * this.columns;
        this.reset = false;
        this.jumbleCount = 0;
        for (int i = 0; i < num * 2; ++i) {
            final int num2 = (int)(Math.random() * 1000.0) % num;
            final int num3 = (int)(Math.random() * 1000.0) % num;
            if (num2 != num3) {
                this.swapImages(num2, num3, true);
            }
        }
        for (int j = 0; j < num; ++j) {
            final imageTracker it1 = this.puzzle.elementAt(j);
            if (it1.truePosition != j) {
                ++this.jumbleCount;
            }
        }
    }
    
    public void btnResetClicked() {
        this.reset = true;
        this.puzzle.removeAllElements();
        this.setUpImages();
        this.clickCount = 0;
        this.jumbleCount = 0;
        this.started = false;
        this.repaint();
    }
    
    public void init() {
        super.init();
        String param = this.getParameter("Rows");
        if (param != null) {
            this.rows = Integer.parseInt(param);
        }
        else {
            this.rows = 5;
        }
        param = this.getParameter("Columns");
        if (param != null) {
            this.columns = Integer.parseInt(param);
        }
        else {
            this.columns = 3;
        }
        param = this.getParameter("Image");
        if (param != null) {
            this.origImage = this.getImage(this.getCodeBase(), param);
        }
        else {
            this.origImage = this.getImage(this.getCodeBase(), "gorilla2.gif");
        }
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.origImage, 0);
        try {
            tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.imgWidth = this.origImage.getWidth(this);
        this.imgHeight = this.origImage.getHeight(this);
        this.sliceHeight = this.imgHeight / this.rows;
        this.sliceWidth = this.imgWidth / this.columns;
        this.puzzle = new Vector(this.rows * this.columns);
        this.setLayout(null);
        this.addNotify();
        this.resize(this.imgWidth, this.imgHeight);
        (this.imgCanvas = new Canvas()).reshape(0, 0, this.imgWidth, this.imgHeight);
        this.add(this.imgCanvas);
        this.cg = this.imgCanvas.getGraphics();
        this.setUpImages();
        appletController.setJigSaw(this);
        this.controller = appletController.getController();
        this.repaint();
    }
    
    private void swapImages(final int pos1, final int pos2, final boolean randomFlag) {
        final imageTracker it1 = this.puzzle.elementAt(pos1);
        final imageTracker it2 = this.puzzle.elementAt(pos2);
        it1.color = Color.white;
        it2.color = Color.white;
        final int tempX = it1.currXpos;
        final int tempY = it1.currYpos;
        it1.currXpos = it2.currXpos;
        it1.currYpos = it2.currYpos;
        it2.currXpos = tempX;
        it2.currYpos = tempY;
        this.puzzle.removeElementAt(pos1);
        this.puzzle.insertElementAt(it2, pos1);
        this.puzzle.removeElementAt(pos2);
        this.puzzle.insertElementAt(it1, pos2);
        if (!randomFlag) {
            if (it2.truePosition == pos1) {
                --this.jumbleCount;
            }
            if (it1.truePosition == pos2) {
                --this.jumbleCount;
            }
        }
    }
    
    private void initImages(final Graphics g) {
        for (int i = 0; i < this.rows * this.columns; ++i) {
            final imageTracker it = this.puzzle.elementAt(i);
            if (this.reset) {
                g.drawImage(it.image, it.xPos, it.yPos, this);
                g.setColor(Color.white);
                g.drawRect(it.xPos, it.yPos, this.sliceWidth, this.sliceHeight);
                it.reset();
            }
            else {
                g.drawImage(it.image, it.currXpos, it.currYpos, this);
                g.setColor(it.color);
                g.drawRect(it.currXpos, it.currYpos, this.sliceWidth, this.sliceHeight);
            }
        }
        if (!this.reset) {
            for (int i = 0; i < this.rows * this.columns; ++i) {
                final imageTracker it = this.puzzle.elementAt(i);
                if (it.color != Color.white) {
                    g.setColor(it.color);
                    g.drawRect(it.currXpos, it.currYpos, this.sliceWidth, this.sliceHeight);
                }
            }
        }
    }
    
    private void setUpImages() {
        int y = 0;
        for (int i = 0; i < this.rows; ++i) {
            int x = 0;
            for (int j = 0; j < this.columns; ++j) {
                final CropImageFilter myCropFilter = new CropImageFilter(x, y, this.sliceWidth, this.sliceHeight);
                final Image tempImage = this.createImage(new FilteredImageSource(this.origImage.getSource(), myCropFilter));
                this.puzzle.addElement(new imageTracker(tempImage, i * this.columns + j, x, y));
                x += this.sliceWidth;
            }
            y += this.sliceHeight;
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        this.initImages(this.cg);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.imgCanvas && event.id == 501) {
            if (this.started) {
                this.imgCanvas_MouseDown(event);
            }
            else {
                this.repaint();
                ((puzzleControl)this.controller).setStatus("Press Start");
            }
            return true;
        }
        if (event.target == this && event.id == 504) {
            this.puzzle_MouseEnter(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    public puzzle() {
        this.reset = true;
        this.started = false;
    }
}
