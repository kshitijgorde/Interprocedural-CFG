import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Level
{
    private int level;
    private int delay;
    int[] xco;
    int[] yco;
    int[] width;
    int[] height;
    int[] levelsPlayed;
    boolean[] fault;
    FindIt2Frame fi2f;
    int xprep;
    int yprep;
    int wprep;
    int hprep;
    int xclicked;
    int yclicked;
    int pic1w;
    int pic1h;
    int pic2w;
    int pic2h;
    String rightPic;
    String wrongPic;
    Random rg;
    int randLevel;
    
    public Level(final FindIt2Frame fi2f) {
        this.level = 0;
        this.xco = new int[5];
        this.yco = new int[5];
        this.width = new int[5];
        this.height = new int[5];
        this.fault = new boolean[5];
        this.randLevel = 0;
        this.fi2f = fi2f;
        this.rg = new Random();
        this.levelsPlayed = new int[fi2f.numberOfPictures];
    }
    
    public boolean check(int xclicked, int yclicked) {
        this.xclicked = xclicked;
        this.yclicked = yclicked;
        yclicked -= this.getOffsetY(1);
        xclicked -= this.getOffsetX(1);
        for (int i = 0; i < 5; ++i) {
            if (xclicked > this.xco[i] && xclicked < this.xco[i] + this.width[i] && yclicked > this.yco[i] && yclicked < this.yco[i] + this.height[i] && !this.fault[i]) {
                this.fault[i] = true;
                this.fi2f.addItemFound();
                this.fi2f.addToScore(this.level * 20);
                return true;
            }
        }
        return false;
    }
    
    private void getCoordinates() {
        for (int i = 0; i < 5; ++i) {
            this.xco[i] = this.fi2f.xcoV[5 * (this.getLogicLevel(0) - 1) + i];
        }
        for (int j = 0; j < 5; ++j) {
            this.yco[j] = this.fi2f.ycoV[5 * (this.getLogicLevel(0) - 1) + j];
        }
        for (int k = 0; k < 5; ++k) {
            this.width[k] = this.fi2f.wcoV[5 * (this.getLogicLevel(0) - 1) + k];
        }
        for (int l = 0; l < 5; ++l) {
            this.height[l] = this.fi2f.hcoV[5 * (this.getLogicLevel(0) - 1) + l];
        }
    }
    
    private void getData() {
        this.rightPic = this.fi2f.rightPic[this.getLogicLevel(0) - 1];
        this.wrongPic = this.fi2f.wrongPic[this.getLogicLevel(0) - 1];
        this.getCoordinates();
    }
    
    public int getDetails(final int n, final char c) {
        this.xprep = this.xco[n] + this.getOffsetX(1);
        this.yprep = this.yco[n] + this.getOffsetY(1);
        this.wprep = this.width[n];
        this.hprep = this.height[n];
        if (c == 'x') {
            return this.xprep;
        }
        if (c == 'y') {
            return this.yprep;
        }
        if (c == 'w') {
            return this.wprep;
        }
        if (c == 'h') {
            return this.hprep;
        }
        return 12345;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public Image getLevelImage(final int n) {
        if (n == 1) {
            final Image loadImage = this.fi2f.loadImage(this.rightPic);
            this.pic1w = this.fi2f.picW;
            this.pic1h = this.fi2f.picH;
            return loadImage;
        }
        final Image loadImage2 = this.fi2f.loadImage(this.wrongPic);
        this.pic2w = this.fi2f.picW;
        this.pic2h = this.fi2f.picH;
        return loadImage2;
    }
    
    public int getLogicLevel(final int n) {
        if (n == 1) {
            while (this.isPlayed(this.randLevel)) {
                this.randLevel = this.rg.randomInt(1, this.fi2f.numberOfPictures);
            }
        }
        this.levelsPlayed[this.getLevel() - 1] = this.randLevel;
        return this.randLevel;
    }
    
    public int getOffsetX(final int n) {
        if (n == 1) {
            return (this.fi2f.psWidth - this.pic1w) / 2;
        }
        return (this.fi2f.psWidth - this.pic2w) / 2;
    }
    
    public int getOffsetY(final int n) {
        if (n == 1) {
            return (this.fi2f.psHeight - this.pic1h) / 2;
        }
        return (this.fi2f.psHeight - this.pic2h) / 2;
    }
    
    private boolean isPlayed(final int n) {
        for (int i = 0; i < this.fi2f.numberOfPictures; ++i) {
            if (n == this.levelsPlayed[i]) {
                return true;
            }
        }
        return false;
    }
    
    public void next() {
        this.resetData();
        ++this.level;
        this.getLogicLevel(1);
        this.getData();
    }
    
    public void resetAllData() {
        this.level = 0;
        this.randLevel = 0;
        for (int i = 0; i < this.fi2f.numberOfPictures; ++i) {
            this.levelsPlayed[i] = 0;
        }
    }
    
    public void resetData() {
        for (int i = 0; i < 5; ++i) {
            this.fault[i] = false;
        }
        final int n = -100;
        this.yclicked = n;
        this.xclicked = n;
        final boolean b = false;
        this.hprep = (b ? 1 : 0);
        this.wprep = (b ? 1 : 0);
        this.yprep = (b ? 1 : 0);
        this.xprep = (b ? 1 : 0);
    }
}
