// 
// Decompiled by Procyon v0.5.30
// 

class Man extends Runner
{
    private static final int MAN_STEPX = 10;
    private static final int MAN_STEPY = 10;
    private int nextDirection;
    private int count;
    private int countDying;
    private int level;
    private int[][] manSequenceImg;
    
    public Man() {
        this.count = 0;
        this.countDying = 0;
        this.level = 0;
        this.manSequenceImg = new int[][] { { 0, 1, 2, 3, 4 }, { 5, 6, 7, 8, 9 }, { 10, 11, 12, 13, 14 } };
    }
    
    public void initMan() {
        this.initRunner();
        this.setStepSize(10, 10);
        this.setState(0);
        this.setSequenceImgFrame(this.manSequenceImg[0]);
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public void setNextDirection(final int nextDirection) {
        this.nextDirection = nextDirection;
    }
    
    public int getNextDirection() {
        return this.nextDirection;
    }
    
    public void setManDirection(final int direction) {
        this.setDirection(direction);
        switch (direction) {
            case 1: {
                this.setFlipImage(2);
                this.setSequenceImgFrame(this.manSequenceImg[1]);
                break;
            }
            case 3: {
                this.setFlipImage(0);
                this.setSequenceImgFrame(this.manSequenceImg[1]);
                break;
            }
            case 4: {
                this.setFlipImage(0);
                this.setSequenceImgFrame(this.manSequenceImg[0]);
                break;
            }
            case 12: {
                this.setFlipImage(1);
                this.setSequenceImgFrame(this.manSequenceImg[0]);
                break;
            }
        }
    }
    
    public void Timer() {
        if (this.isSlowStep()) {
            ++this.count;
            if (this.count == 4) {
                this.count = 0;
                this.setStepSize(10, 10);
            }
        }
    }
    
    public void setManDied() {
        this.setSequenceImgFrame(this.manSequenceImg[2]);
    }
}
