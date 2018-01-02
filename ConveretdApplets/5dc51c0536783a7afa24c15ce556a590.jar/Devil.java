// 
// Decompiled by Procyon v0.5.30
// 

class Devil extends Runner
{
    private static final int DEVIL_STEPX = 10;
    private static final int DEVIL_STEPY = 10;
    private static final int DEVIL_SLOW_STEPX = 4;
    private static final int DEVIL_SLOW_STEPY = 4;
    private static final int DEVIL_TURN_DELAY = 1;
    private static final int DEVIL_SLOWDOWN_DELAY = 3;
    private int curStepX;
    private int curStepY;
    private int delay;
    private int timeInsideStable;
    private int nextDirection;
    private boolean needCalcuNewDirection;
    private static final int DEVIL_RANDOM_BASE = 9;
    private static final int[][] ranLevel;
    private static final int[][] weakTime;
    private static final int DEVIL_INBARN_TIME1 = 20;
    private static final int DEVIL_INBARN_TIME2 = 50;
    private static final int DEVIL_INBARN_TIME3 = 60;
    private static final int DEVIL_INBARN_TIME4 = 70;
    private static final int DEVIL_INBARN_TIME5 = 80;
    private static final int[][] inBarnTime;
    private static final int DEVIL_WEAK_IDX = 4;
    private int count;
    private int index;
    private int level;
    private int[][] devilSequenceImg;
    private int changeDirAble;
    
    public Devil() {
        this.curStepX = 10;
        this.curStepY = 10;
        this.delay = 0;
        this.timeInsideStable = 10;
        this.needCalcuNewDirection = false;
        this.count = 0;
        this.index = 0;
        this.level = 0;
        this.devilSequenceImg = new int[][] { { 15, 16, 17, 18, 19 }, { 20, 21, 22, 23, 24 }, { 25, 26, 27, 28, 29 }, { 30, 31, 32, 33, 34 }, { 35, 36, 37, 38, 39 }, { 40, 41, 42, 43, 44 } };
        this.changeDirAble = 0;
    }
    
    public void initDevil(final int index) {
        this.index = index;
        this.initRunner();
        this.setStepSize(this.curStepX, this.curStepY);
        this.setState(1);
        this.setSequenceImgFrame(this.devilSequenceImg[this.index]);
        this.count = 0;
        this.delay = 0;
        this.changeDirAble = 0;
    }
    
    public void setLevel(int level) {
        if (level > 7) {
            level = 7;
        }
        this.level = level;
    }
    
    public void setDevilStepSize(final int curStepX, final int curStepY) {
        this.curStepX = curStepX;
        this.curStepY = curStepY;
        this.setStepSize(this.curStepX, this.curStepY);
    }
    
    public void setDevilDirection(final int direction) {
        this.setDirection(direction);
    }
    
    public void setTurnDelay() {
        this.delay = 1;
    }
    
    public void setTempSlowDown() {
        this.delay = 4;
    }
    
    public boolean isTurnDelaying() {
        if (this.delay > 0) {
            --this.delay;
            return true;
        }
        return false;
    }
    
    public boolean getRandom() {
        if (this.index > 4) {
            this.index = 4;
        }
        return (int)(Math.random() * 65535.0) % Devil.ranLevel[this.level][this.index] <= 9;
    }
    
    public void nextDevilImage() {
        final int n = Devil.weakTime[this.level][this.index] - this.count;
        if ((this.getState() == 3 || this.getState() == 4) && (n > 80 || n % 5 > 2)) {
            this.setSequenceImgFrame(this.devilSequenceImg[4]);
        }
        else {
            this.setSequenceImgFrame(this.devilSequenceImg[this.index]);
        }
        if (this.getState() == 5) {
            this.setSequenceImgFrame(this.devilSequenceImg[this.index]);
            this.setCurrentFrame(4);
        }
        else {
            switch (this.getDirection()) {
                case 1: {
                    this.setCurrentFrame(0);
                    break;
                }
                case 3: {
                    this.setCurrentFrame(1);
                    break;
                }
                case 4: {
                    this.setCurrentFrame(2);
                    break;
                }
                case 12: {
                    this.setCurrentFrame(3);
                    break;
                }
            }
        }
    }
    
    public void Timer() {
        if (this.getState() == 3 || this.getState() == 4) {
            this.setStepSize(4, 4);
            ++this.count;
            if (this.count >= Devil.weakTime[this.level][this.index]) {
                this.count = 0;
                if (this.getState() == 4) {
                    this.setState(2);
                }
                else {
                    this.setState(0);
                }
                this.setStepSize(10, 10);
            }
        }
        else if (this.getState() == 1) {
            ++this.count;
            if (this.count >= Devil.inBarnTime[this.level][this.index]) {
                this.count = 0;
                this.setState(2);
            }
        }
        else if (this.getState() == 5) {
            this.count = 0;
            this.setStepSize(10, 10);
        }
        else if (this.isSlowStep()) {
            ++this.count;
            if (this.count == 4) {
                this.count = 0;
                this.setStepSize(10, 10);
            }
        }
        if (!this.isTurnDelaying() && this.changeDirAble > 0) {
            --this.changeDirAble;
        }
    }
    
    public void setweakState() {
        if (this.getState() == 1 || this.getState() == 4) {
            this.setState(4);
        }
        else {
            this.setState(3);
        }
        this.count = 0;
    }
    
    public boolean changeDirAllow() {
        return this.changeDirAble == 0;
    }
    
    public void turnOffChangeDir() {
        this.changeDirAble = 1;
    }
    
    static {
        ranLevel = new int[][] { { 10, 12, 14, 16, 16 }, { 10, 11, 14, 15, 15 }, { 10, 11, 12, 12, 12 }, { 9, 10, 11, 12, 12 }, { 9, 10, 10, 12, 12 }, { 9, 9, 10, 11, 11 }, { 9, 9, 9, 10, 10 }, { 9, 9, 9, 9, 9 } };
        weakTime = new int[][] { { 200, 250, 300, 400, 400 }, { 200, 250, 280, 300, 300 }, { 190, 200, 220, 250, 250 }, { 170, 190, 200, 200, 200 }, { 150, 160, 200, 180, 150 }, { 130, 140, 140, 150, 150 }, { 80, 110, 120, 120, 120 }, { 50, 80, 100, 100, 100 } };
        inBarnTime = new int[][] { { 20, 100, 120, 130, 180 }, { 20, 90, 100, 120, 100 }, { 20, 70, 100, 90, 100 }, { 20, 50, 80, 90, 90 }, { 20, 40, 70, 60, 70 }, { 15, 30, 50, 50, 50 }, { 10, 25, 40, 40, 45 }, { 5, 10, 25, 25, 30 } };
    }
}
