// 
// Decompiled by Procyon v0.5.30
// 

class Bonus extends Runner
{
    private int level;
    private int count;
    private int countdown;
    private int numperlevel;
    private boolean visual;
    private boolean bonusReady;
    private static final int[] bonusTable;
    private static final int BONUS_DISPLAY_TIME = 200;
    private static final int BONUS_DISPLAY_RESULT_TIME = 60;
    private static final int[][] sequenceImg;
    private int[] bonusKind;
    
    public Bonus() {
        this.level = 0;
        this.count = 0;
        this.countdown = 0;
        this.numperlevel = 2;
        this.visual = false;
        this.bonusReady = false;
        this.bonusKind = new int[] { 0, 0, 0, 0 };
    }
    
    public void initBonus() {
    }
    
    public boolean isVisual() {
        return this.visual;
    }
    
    public int takeBonus() {
        this.countdown = 60;
        this.setSequenceImgFrame(Bonus.sequenceImg[1]);
        this.bonusReady = false;
        return Bonus.bonusTable[this.bonusKind[this.numperlevel]];
    }
    
    public boolean canTakeBonus() {
        return this.visual && this.bonusReady;
    }
    
    public void setLevel(final int level) {
        this.level = level;
        if (level < Bonus.bonusTable.length) {
            this.bonusKind[0] = level;
            this.bonusKind[1] = level;
        }
        else {
            this.bonusKind[0] = (int)(101.0 * Math.random()) % Bonus.bonusTable.length;
            this.bonusKind[1] = (int)(101.0 * Math.random()) % Bonus.bonusTable.length;
        }
        this.count = 100 + (int)(800.0 * Math.random());
        this.numperlevel = 2;
    }
    
    public void Timer() {
        if (this.countdown > 0) {
            --this.countdown;
            if (this.countdown == 0 && this.visual) {
                this.visual = false;
            }
        }
        else {
            --this.count;
        }
        if (this.count <= 0 && !this.visual && this.numperlevel > 0 && Math.random() < 0.2) {
            this.visual = true;
            this.bonusReady = true;
            --this.numperlevel;
            this.countdown = 200;
            this.setSequenceImgFrame(Bonus.sequenceImg[0]);
            this.setCurrentFrame(this.bonusKind[this.numperlevel]);
            this.count = 150 + (int)(800.0 * Math.random());
        }
    }
    
    public boolean isLastDisplay() {
        return this.visual && this.countdown == 1;
    }
    
    static {
        bonusTable = new int[] { 400, 800, 1600, 3200, 6400 };
        sequenceImg = new int[][] { { 40, 41, 42, 43, 44 }, { 45, 46, 47, 48, 49 } };
    }
}
