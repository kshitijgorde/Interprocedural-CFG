import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Loop
{
    private String _strTitle;
    private Vector _vecWhile;
    private Vector _vecRepeat;
    
    public Loop() {
        this._strTitle = "";
        this._vecRepeat = new Vector();
        this._vecWhile = new Vector();
    }
    
    public Loop(final String title) {
        this._strTitle = title;
        this._vecRepeat = new Vector();
        this._vecWhile = new Vector();
    }
    
    public void setTitle(final String title) {
        this._strTitle = title;
    }
    
    public void setWhiles(final Vector whiles) {
        this._vecWhile = whiles;
    }
    
    public void setRepeats(final Vector repeats) {
        this._vecRepeat = repeats;
    }
    
    public String getTitle() {
        return this._strTitle;
    }
    
    public Vector getWhiles() {
        return this._vecWhile;
    }
    
    public Vector getRepeats() {
        return this._vecRepeat;
    }
    
    public void addWhile(final LoopLine line) {
        this._vecWhile.add(line);
    }
    
    public void addRepeat(final LoopLine line) {
        this._vecRepeat.add(line);
    }
}
