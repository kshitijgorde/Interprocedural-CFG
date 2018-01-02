// 
// Decompiled by Procyon v0.5.30
// 

public class MSArcade implements MSController
{
    private MoonStar moonStar;
    private int timer;
    
    public MSArcade(final MoonStar moonStar) {
        this.timer = 0;
        this.moonStar = moonStar;
        moonStar.mSController = this;
        moonStar.mSGame.init(new int[15][15], 250, 3, 3, this);
    }
    
    public void gameOver() {
        this.moonStar.mSMenu.sendHighScore(true);
        this.moonStar.mSController = null;
        this.moonStar.mSMenu.actionPerformed(6);
    }
    
    public boolean doWormStop() {
        ++this.timer;
        if (this.timer >= 12) {
            this.moonStar.mSGame.arcadeLevelup();
            this.moonStar.mSMenu.addLevel();
            this.timer = 0;
        }
        return true;
    }
}
