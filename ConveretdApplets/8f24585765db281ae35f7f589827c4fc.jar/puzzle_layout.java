// 
// Decompiled by Procyon v0.5.30
// 

abstract class puzzle_layout
{
    protected int[] win;
    protected int win_index;
    protected int start_index;
    protected int i;
    puzzle puz;
    
    puzzle_layout() {
        this.win = new int[16];
    }
    
    public int startPos() {
        return this.start_index;
    }
    
    public int winPos() {
        return this.win_index;
    }
    
    public abstract boolean puzzle_complete();
}
