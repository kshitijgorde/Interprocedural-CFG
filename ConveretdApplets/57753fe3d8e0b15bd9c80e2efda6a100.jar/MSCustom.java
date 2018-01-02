// 
// Decompiled by Procyon v0.5.30
// 

public class MSCustom implements MSController
{
    private MoonStar moonStar;
    
    public MSCustom(final MoonStar moonStar, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.moonStar = moonStar;
        moonStar.mSController = this;
        moonStar.mSGame.init(new int[n][n2], n3, n4, n5, this);
    }
    
    public void gameOver() {
        this.moonStar.mSController = null;
        this.moonStar.mSMenu.actionPerformed(6);
    }
    
    public boolean doWormStop() {
        return true;
    }
}
