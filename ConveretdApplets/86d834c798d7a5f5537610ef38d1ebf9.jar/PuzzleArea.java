import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.util.Vector;
import java.awt.Rectangle;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PuzzleArea extends Canvas
{
    SlidePuzzle controller;
    Image puzzleImage;
    MediaTracker tracker;
    Dimension sz;
    AudioClip actionSound;
    Image[][] imagePieces;
    int[][][] ordening;
    ScrollingThread scrollingThread;
    Rectangle scrollingRect;
    public int threadProgress;
    int scrollingTime;
    public int totalFrames;
    int oldopenh;
    int oldopenv;
    int oldmedx;
    int oldmedy;
    boolean imagePiecesCreated;
    boolean showAll;
    boolean alternatePic;
    boolean slidablePiece;
    boolean piecesHaveBorders;
    boolean enableScrolling;
    boolean piecesHaveButtonEffect;
    int blockwidth;
    int blockheight;
    int hnum;
    int vnum;
    int hpos;
    int vpos;
    int openh;
    int openv;
    Vector randomList;
    
    public PuzzleArea(final SlidePuzzle controller) {
        this.threadProgress = -1;
        this.imagePiecesCreated = false;
        this.showAll = false;
        this.alternatePic = false;
        this.slidablePiece = false;
        this.controller = controller;
        this.hnum = controller.hnum;
        this.vnum = controller.vnum;
        this.scrollingTime = controller.scrollingTime;
        this.piecesHaveBorders = controller.piecesHaveBorders;
        this.enableScrolling = controller.enableScrolling;
        this.piecesHaveButtonEffect = controller.piecesHaveButtonEffect;
        this.sz = new Dimension(50 * this.vnum, 50 * this.hnum);
        if (controller.imageURL != null) {
            this.tracker = new MediaTracker(this);
            this.puzzleImage = controller.getImage(controller.imageURL);
            this.tracker.addImage(this.puzzleImage, 0);
            try {
                this.tracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
        }
        this.imagePiecesCreated = this.createImagePieces();
        this.setBackground(controller.backgroundColor);
        try {
            this.actionSound = controller.getAudioClip(new URL(controller.getCodeBase(), controller.getParameter("SOUNDFILE")));
        }
        catch (MalformedURLException ex2) {}
    }
    
    private void Count() {
        this.controller.Count();
    }
    
    private Rectangle calculateToRepaint(final int n, final int n2, final int n3, final int n4) {
        return new Rectangle(n2 * this.blockwidth, n * this.blockheight, this.blockwidth, this.blockheight).union(new Rectangle(n4 * this.blockwidth, n3 * this.blockheight, this.blockwidth, this.blockheight));
    }
    
    private boolean checkImage() {
        if (this.alternatePic) {
            return true;
        }
        if (this.tracker == null) {
            this.prepareAlternateImage();
            return false;
        }
        if (this.tracker.isErrorID(0)) {
            this.prepareAlternateImage();
            return false;
        }
        return true;
    }
    
    private void createAlternateImage() {
        if (this.puzzleImage != null) {
            this.puzzleImage = null;
        }
        this.puzzleImage = this.createImage(this.vnum * this.blockwidth, this.hnum * this.blockheight);
        final Graphics graphics = this.puzzleImage.getGraphics();
        final Font font = graphics.getFont();
        graphics.getFontMetrics();
        boolean b = false;
        int size = font.getSize();
        final String name = font.getName();
        final int style = font.getStyle();
        graphics.setFont(new Font(name, style, 40));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        while (!b) {
            if (fontMetrics.getHeight() <= this.blockheight - 5 && fontMetrics.stringWidth("100") <= this.blockwidth - 2) {
                b = true;
            }
            else {
                graphics.setFont(new Font(name, style, --size));
                fontMetrics = graphics.getFontMetrics();
            }
        }
        final int ascent = fontMetrics.getAscent();
        for (int i = 0; i < this.hnum; ++i) {
            for (int j = 0; j < this.vnum; ++j) {
                final String string = new Integer(i * this.vnum + j + 1).toString();
                graphics.drawString(string, j * this.blockwidth + (this.blockwidth - fontMetrics.stringWidth(string)) / 2, i * this.blockheight + this.blockheight - (this.blockheight - ascent) / 2);
            }
        }
        this.controller.updateLabel(this.controller.solveThePuzzleText);
        graphics.dispose();
    }
    
    private boolean createImagePieces() {
        if (!this.estimateImageDimension()) {
            return false;
        }
        this.imagePieces = new Image[this.hnum][this.vnum];
        for (int i = 0; i < this.hnum; ++i) {
            for (int j = 0; j < this.vnum; ++j) {
                this.imagePieces[i][j] = this.createImage(new FilteredImageSource(this.puzzleImage.getSource(), new CropImageFilter(j * this.blockwidth, i * this.blockheight, this.blockwidth, this.blockheight)));
            }
        }
        return true;
    }
    
    private void createOrdening() {
        this.ordening = new int[this.hnum][this.vnum][2];
        if (!this.controller.alwaysSolvable) {
            this.randomList = new RandomListCreator(this.hnum * this.vnum).getRandomList();
            for (int i = 0; i < this.hnum; ++i) {
                for (int j = 0; j < this.vnum; ++j) {
                    final int intValue = this.randomList.elementAt(i * this.vnum + j);
                    final int n = intValue % this.vnum;
                    final int n2 = (intValue - n) / this.vnum;
                    if (n2 == this.hnum - 1 && n == this.vnum - 1) {
                        this.openh = i;
                        this.openv = j;
                    }
                    this.ordening[i][j][0] = n2;
                    this.ordening[i][j][1] = n;
                }
            }
        }
        else {
            final int n3 = this.hnum * this.vnum * 5;
            this.showAll = false;
            final Vector vector = new Vector<Dimension>(4);
            this.initializeOrdening();
            for (int k = 0; k < n3; ++k) {
                vector.removeAllElements();
                final int hnum = this.hnum;
                final int vnum = this.vnum;
                for (int l = this.openh - 1; l <= this.openh + 1; ++l) {
                    for (int n4 = this.openv - 1; n4 <= this.openv + 1; ++n4) {
                        if (this.testSlidablePiece(l, n4) && (l != hnum || n4 != vnum)) {
                            vector.addElement(new Dimension(l, n4));
                        }
                    }
                }
                int n6;
                for (int n5 = n6 = vector.size(); n6 == n5; n6 = (int)(Object)new Double(Math.floor(Math.random() * n5))) {}
                final Dimension dimension = vector.elementAt(n6);
                final int width = dimension.width;
                final int height = dimension.height;
                final int n7 = this.ordening[this.openh][this.openv][0];
                final int n8 = this.ordening[this.openh][this.openv][1];
                this.ordening[this.openh][this.openv][0] = this.ordening[width][height][0];
                this.ordening[this.openh][this.openv][1] = this.ordening[width][height][1];
                this.ordening[width][height][0] = n7;
                this.ordening[width][height][1] = n8;
                final int openh = this.openh;
                final int openv = this.openv;
                this.openh = width;
                this.openv = height;
            }
        }
    }
    
    private void createTheThread() {
        this.scrollingThread = null;
        final int n = 100;
        if (this.scrollingTime < 100) {
            this.scrollingTime = 100;
        }
        this.scrollingRect = this.calculateToRepaint(this.openh, this.openv, this.oldopenh, this.oldopenv);
        this.totalFrames = this.scrollingTime / n;
        this.oldmedy = this.openh * this.blockheight;
        this.oldmedx = this.openv * this.blockwidth;
        (this.scrollingThread = new ScrollingThread(this, n, this.scrollingTime)).start();
    }
    
    private void drawScrolling(final Graphics graphics) {
        final int n = this.openv * this.blockwidth;
        final int n2 = this.openh * this.blockheight;
        final int n3 = this.oldopenv * this.blockwidth;
        final int n4 = this.oldopenh * this.blockheight;
        final int interpolate = this.interpolate(n, n3, this.threadProgress + 1, this.totalFrames + 1);
        final int interpolate2 = this.interpolate(n2, n4, this.threadProgress + 1, this.totalFrames + 1);
        int n5;
        int n6;
        int oldmedx;
        int oldmedy;
        if (interpolate == this.oldmedx) {
            n5 = this.blockwidth;
            n6 = Math.abs(interpolate2 - this.oldmedy);
            oldmedx = interpolate;
            if (interpolate2 > this.oldmedy) {
                oldmedy = this.oldmedy;
            }
            else {
                oldmedy = interpolate2 + this.blockheight;
            }
        }
        else {
            n5 = Math.abs(interpolate - this.oldmedx);
            n6 = this.blockheight;
            oldmedy = interpolate2;
            if (interpolate > this.oldmedx) {
                oldmedx = this.oldmedx;
            }
            else {
                oldmedx = interpolate + this.blockwidth;
            }
        }
        final Color color = graphics.getColor();
        graphics.setColor(this.controller.emptySpaceColor);
        graphics.fillRect(oldmedx, oldmedy, n5, n6);
        graphics.setColor(color);
        if (this.piecesHaveBorders) {
            GraphicsExtension.drawBetter3DRect(graphics, n, n2, this.blockwidth - 2, this.blockheight - 2, false);
        }
        graphics.drawImage(this.imagePieces[this.ordening[this.oldopenh][this.oldopenv][0]][this.ordening[this.oldopenh][this.oldopenv][1]], interpolate, interpolate2, this);
        if (this.piecesHaveBorders) {
            GraphicsExtension.drawBetter3DRect(graphics, interpolate, interpolate2, this.blockwidth - 2, this.blockheight - 2, !this.piecesHaveButtonEffect);
        }
        this.oldmedx = interpolate;
        this.oldmedy = interpolate2;
    }
    
    private boolean estimateImageDimension() {
        if (!this.checkImage()) {
            return false;
        }
        if (!this.alternatePic) {
            int width;
            while ((width = this.puzzleImage.getWidth(this)) < 0) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            int height;
            while ((height = this.puzzleImage.getHeight(this)) < 0) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {}
            }
            final int n = width - width % this.vnum;
            final int n2 = height - height % this.hnum;
            this.updateSize(n, n2);
            this.blockwidth = n / this.vnum;
            this.blockheight = n2 / this.hnum;
        }
        return true;
    }
    
    public Dimension getMinimumSize() {
        return this.sz;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    private void initializeOrdening() {
        this.openh = this.hnum - 1;
        this.openv = this.vnum - 1;
        for (int i = 0; i < this.hnum; ++i) {
            for (int j = 0; j < this.vnum; ++j) {
                this.ordening[i][j][0] = i;
                this.ordening[i][j][1] = j;
            }
        }
    }
    
    private int interpolate(final int n, final int n2, final int n3, final int n4) {
        return n + (n2 - n) / n4 * n3;
    }
    
    private boolean isImageSolved() {
        if (this.openh != this.hnum - 1 || this.openv != this.vnum - 1) {
            return false;
        }
        for (int i = 0; i < this.hnum; ++i) {
            for (int j = 0; j < this.vnum; ++j) {
                if (this.ordening[i][j][0] != i || this.ordening[i][j][1] != j) {
                    return false;
                }
            }
        }
        this.controller.userHasWon();
        this.repaint();
        return true;
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.imagePiecesCreated || this.threadProgress != -1) {
            return false;
        }
        final int openv = n / this.blockwidth;
        final int openh = n2 / this.blockheight;
        Rectangle calculateToRepaint = null;
        if (this.testSlidablePiece(openh, openv)) {
            calculateToRepaint = this.calculateToRepaint(openh, openv, this.openh, this.openv);
            final int n3 = this.ordening[this.openh][this.openv][0];
            final int n4 = this.ordening[this.openh][this.openv][1];
            this.ordening[this.openh][this.openv][0] = this.ordening[openh][openv][0];
            this.ordening[this.openh][this.openv][1] = this.ordening[openh][openv][1];
            this.ordening[openh][openv][0] = n3;
            this.ordening[openh][openv][1] = n4;
            this.oldopenh = this.openh;
            this.oldopenv = this.openv;
            this.openh = openh;
            this.openv = openv;
            this.Count();
            if (this.enableScrolling) {
                this.createTheThread();
            }
            if (this.actionSound != null) {
                this.actionSound.play();
            }
            this.showAll = this.isImageSolved();
        }
        if (calculateToRepaint != null && !this.enableScrolling) {
            this.repaint(calculateToRepaint.x, calculateToRepaint.y, calculateToRepaint.width, calculateToRepaint.height);
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.imagePiecesCreated || this.threadProgress != -1 || !this.piecesHaveButtonEffect || !this.piecesHaveBorders) {
            return false;
        }
        final int hpos = this.hpos;
        final int vpos = this.vpos;
        this.hpos = this.openh;
        this.vpos = this.openv;
        this.repaint(vpos * this.blockwidth, hpos * this.blockheight, this.blockwidth, this.blockheight);
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.imagePiecesCreated || this.threadProgress != -1) {
            return false;
        }
        final int vpos = this.vpos;
        final int hpos = this.hpos;
        this.vpos = n / this.blockwidth;
        this.hpos = n2 / this.blockheight;
        final Rectangle calculateToRepaint = this.calculateToRepaint(hpos, vpos, this.hpos, this.vpos);
        this.slidablePiece = this.testSlidablePiece(this.hpos, this.vpos);
        if (this.piecesHaveBorders && (this.vpos != vpos || this.hpos != hpos)) {
            this.repaint(calculateToRepaint.x, calculateToRepaint.y, calculateToRepaint.width, calculateToRepaint.height);
        }
        return false;
    }
    
    private boolean outOfBounds(final int n, final int n2) {
        return n < 0 || n >= this.hnum || n2 < 0 || n2 >= this.vnum;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public Dimension preferredSize() {
        return this.getMinimumSize();
    }
    
    private void prepareAlternateImage() {
        this.alternatePic = true;
        this.controller.setNoImage();
        this.blockwidth = 50;
        this.blockheight = 50;
        this.updateSize(this.vnum * this.blockwidth, this.hnum * this.blockheight);
    }
    
    public void scramble() {
        this.createOrdening();
        if (this.isImageSolved()) {
            this.scramble();
        }
        if (this.imagePiecesCreated) {
            this.controller.updateLabel(this.controller.solveThePuzzleText);
        }
        this.showAll = false;
        this.repaint();
    }
    
    public void scrollRepaint() {
        this.repaint(this.scrollingRect.x, this.scrollingRect.y, this.scrollingRect.width, this.scrollingRect.height);
    }
    
    private boolean testSlidablePiece(final int n, final int n2) {
        return !this.outOfBounds(n, n2) && ((n == this.openh && Math.abs(n2 - this.openv) == 1) || (n2 == this.openv && Math.abs(n - this.openh) == 1)) && !this.showAll;
    }
    
    public void update(final Graphics graphics) {
        if (this.alternatePic) {
            this.createAlternateImage();
            this.imagePiecesCreated = this.createImagePieces();
            this.alternatePic = false;
        }
        if (this.threadProgress == -1 || !graphics.getClipRect().union(this.scrollingRect).equals(this.scrollingRect)) {
            if (!this.imagePiecesCreated) {
                return;
            }
            for (int i = 0; i < this.hnum; ++i) {
                for (int j = 0; j < this.vnum; ++j) {
                    if (i == this.openh && j == this.openv && !this.showAll) {
                        final Color color = graphics.getColor();
                        graphics.setColor(this.controller.emptySpaceColor);
                        graphics.fillRect(j * this.blockwidth, i * this.blockheight, this.blockwidth, this.blockheight);
                        graphics.setColor(color);
                    }
                    else {
                        graphics.drawImage(this.imagePieces[this.ordening[i][j][0]][this.ordening[i][j][1]], j * this.blockwidth, i * this.blockheight, this);
                    }
                    if (this.piecesHaveBorders && !this.showAll) {
                        final Color color2 = graphics.getColor();
                        graphics.setColor(this.controller.emptySpaceColor);
                        graphics.drawRect(j * this.blockwidth, i * this.blockheight, this.blockwidth - 1, this.blockheight - 1);
                        graphics.setColor(color2);
                        GraphicsExtension.drawBetter3DRect(graphics, j * this.blockwidth, i * this.blockheight, this.blockwidth - 2, this.blockheight - 2, (!this.slidablePiece || !this.piecesHaveButtonEffect || this.hpos != i || this.vpos != j) && (i != this.openh || j != this.openv));
                    }
                }
            }
        }
        if (this.threadProgress != -1) {
            this.drawScrolling(graphics);
        }
    }
    
    private void updateSize(final int width, final int height) {
        if (width >= 0) {
            this.sz.width = width;
        }
        if (height >= 0) {
            this.sz.height = height;
        }
    }
}
