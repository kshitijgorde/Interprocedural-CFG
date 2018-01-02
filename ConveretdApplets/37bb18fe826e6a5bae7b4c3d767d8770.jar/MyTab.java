import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class MyTab extends JPanel
{
    protected Puzzle puz;
    
    public MyTab() {
        this.puz = null;
    }
    
    public void setPuzzle(final Puzzle puz) {
        this.puz = puz;
        this.init();
    }
    
    protected abstract void init();
    
    protected abstract void exit();
}
