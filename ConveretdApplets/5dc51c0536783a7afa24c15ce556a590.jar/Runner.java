import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Runner
{
    public static final int DIR_NONE = 0;
    public static final int DIR_UP = 1;
    public static final int DIR_DOWN = 3;
    public static final int DIR_RIGHT = 4;
    public static final int DIR_LEFT = 12;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_INBARN = 1;
    public static final int STATE_GOINGOUT = 2;
    public static final int STATE_WEAK = 3;
    public static final int STATE_WEAK_INBARN = 4;
    public static final int STATE_DYING = 5;
    public static final int STATE_DIE = 6;
    public static final int DELAY_EATING = 1;
    public static final int DELAY_TUNEL = 3;
    public static final int IMAGE_FLIP_NONE = 0;
    public static final int IMAGE_FLIP_HOZR = 1;
    public static final int IMAGE_FLIP_VERT = 2;
    public static final int STEP_NORMALX = 10;
    public static final int STEP_NORMALY = 10;
    public static int[] directionArray;
    private static Image img;
    private static int numLineIm;
    private static int numColIm;
    private static int imWidth;
    private static int imHeight;
    private static int cell_x;
    private static int cell_y;
    private static int start_x;
    private static int start_y;
    private int origin_x;
    private int origin_y;
    private int stepsize_x;
    private int stepsize_y;
    private int direction;
    private int state;
    private int x;
    private int y;
    private int deltaImg;
    private int delay;
    private int flipImg;
    private int[] sequenceImgFrame;
    private int curFrameImg;
    
    Runner() {
        this.state = 0;
        this.x = 0;
        this.y = 0;
        this.deltaImg = 1;
        this.delay = 0;
        this.flipImg = 0;
        this.curFrameImg = 0;
    }
    
    public static void setImage(final Image img, final int numLineIm, final int numColIm) {
        Runner.img = img;
        Runner.numLineIm = numLineIm;
        Runner.numColIm = numColIm;
        Runner.imWidth = img.getWidth(null) / Runner.numColIm;
        Runner.imHeight = img.getHeight(null) / Runner.numLineIm;
        Runner.cell_x = Runner.imWidth / 2 + 1;
        Runner.cell_y = Runner.imHeight / 2 + 1;
    }
    
    public static int getCellX() {
        return Runner.cell_x;
    }
    
    public static int getCellY() {
        return Runner.cell_y;
    }
    
    public static void setStart(final int start_x, final int start_y) {
        Runner.start_x = start_x;
        Runner.start_y = start_y;
    }
    
    protected void initRunner() {
        final boolean b = false;
        this.y = (b ? 1 : 0);
        this.x = (b ? 1 : 0);
        this.deltaImg = 1;
        this.delay = 0;
    }
    
    public void setCurrentFrame(final int curFrameImg) {
        this.curFrameImg = curFrameImg;
    }
    
    public void setSequenceImgFrame(final int[] sequenceImgFrame) {
        this.sequenceImgFrame = sequenceImgFrame;
    }
    
    public void setOrigin(final int origin_x, final int origin_y) {
        this.origin_x = origin_x;
        this.origin_y = origin_y;
    }
    
    public int getOriginX() {
        return this.origin_x;
    }
    
    public int getOriginY() {
        return this.origin_y;
    }
    
    public void setStepSize(final int stepsize_x, final int stepsize_y) {
        this.stepsize_x = stepsize_x;
        this.stepsize_y = stepsize_y;
    }
    
    public boolean isSlowStep() {
        return this.stepsize_x < 10 || this.stepsize_y < 10;
    }
    
    protected void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public void calcuPosition() {
        switch (this.direction) {
            case 1: {
                this.y -= this.stepsize_y;
                if (-this.y >= Runner.cell_y) {
                    --this.origin_y;
                    this.y = 0;
                    break;
                }
                break;
            }
            case 3: {
                this.y += this.stepsize_y;
                if (this.y >= Runner.cell_y) {
                    ++this.origin_y;
                    this.y = 0;
                    break;
                }
                break;
            }
            case 4: {
                this.x += this.stepsize_x;
                if (this.x >= Runner.cell_x) {
                    ++this.origin_x;
                    this.x = 0;
                    break;
                }
                break;
            }
            case 12: {
                this.x -= this.stepsize_x;
                if (-this.x >= Runner.cell_x) {
                    --this.origin_x;
                    this.x = 0;
                    break;
                }
                break;
            }
        }
    }
    
    public boolean isInOrigin() {
        return this.x == 0 && this.y == 0;
    }
    
    public void displayRunner(final Graphics graphics, final int n, final int n2) {
        final int n3 = this.sequenceImgFrame[this.curFrameImg];
        int n4 = n3 % Runner.numColIm * Runner.imWidth;
        int n5 = n3 / Runner.numColIm * Runner.imHeight;
        int n6 = n4 + Runner.imWidth;
        int n7 = n5 + Runner.imHeight;
        switch (this.flipImg) {
            case 1: {
                final int n8 = n4;
                n4 = n6;
                n6 = n8;
                break;
            }
            case 2: {
                final int n9 = n5;
                n5 = n7;
                n7 = n9;
                break;
            }
        }
        graphics.drawImage(Runner.img, n, n2, n + Runner.imWidth, n2 + Runner.imHeight, n4, n5, n6, n7, null);
    }
    
    public int getCurrentDisplayPosition() {
        return (Runner.start_x + Runner.cell_x * this.origin_x + this.x & 0xFFFF) | Runner.start_y + Runner.cell_y * this.origin_y + this.y << 16;
    }
    
    public void nextImage() {
        this.curFrameImg += this.deltaImg;
        if (this.curFrameImg < 0) {
            this.deltaImg = 1;
            this.curFrameImg = 1;
        }
        if (this.curFrameImg >= this.sequenceImgFrame.length) {
            this.deltaImg = -1;
            this.curFrameImg = this.sequenceImgFrame.length - 1;
        }
    }
    
    public static boolean isOppositDirections(final int n, final int n2) {
        return n != n2 && (n & n2) > 0;
    }
    
    public static int getOppositDirections(final int n) {
        for (int i = 0; i < 4; ++i) {
            if (isOppositDirections(n, Runner.directionArray[i])) {
                return Runner.directionArray[i];
            }
        }
        return Runner.directionArray[4];
    }
    
    public static boolean isTurnDirections(final int n, final int n2) {
        return n != n2 && (n & n2) == 0x0;
    }
    
    public void setState(final int state) {
        this.state = state;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setDelay(final int delay) {
        this.delay = delay;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public boolean isDelaying() {
        if (this.delay > 0) {
            --this.delay;
            return true;
        }
        return false;
    }
    
    public boolean isKilledDistance(final Runner runner) {
        final int n = this.origin_x * Runner.cell_x + this.x;
        final int n2 = this.origin_y * Runner.cell_y + this.y;
        final int n3 = runner.origin_x * Runner.cell_x + runner.x;
        final int n4 = runner.origin_y * Runner.cell_y + runner.y;
        return Math.abs(n - n3) < Runner.cell_x && Math.abs(n2 - n4) < Runner.cell_y;
    }
    
    public void setFlipImage(final int flipImg) {
        this.flipImg = flipImg;
    }
    
    static {
        Runner.directionArray = new int[] { 1, 3, 12, 4, 0 };
        Runner.start_x = 0;
        Runner.start_y = 0;
    }
}
