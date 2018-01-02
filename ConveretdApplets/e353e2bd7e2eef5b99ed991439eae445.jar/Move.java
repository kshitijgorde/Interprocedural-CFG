// 
// Decompiled by Procyon v0.5.30
// 

public class Move
{
    private int \u00ed;
    private int \u00ee;
    private int \u00ef;
    private int \u00c0;
    private int \u00f0;
    private boolean \u00f1;
    private boolean \u00f2;
    
    Move() {
        this.\u00ed = 0;
        this.\u00ee = 0;
        this.\u00ef = 0;
        this.\u00c0 = 0;
        this.\u00f0 = 0;
        this.\u00f1 = false;
        this.\u00f2 = false;
    }
    
    public void set(final int \u00ed, final int \u00ee, final int \u00ef, final int \u00e0, final boolean \u00f1, final int \u00f0, final boolean \u00f2) {
        this.\u00ed = \u00ed;
        this.\u00ee = \u00ee;
        this.\u00ef = \u00ef;
        this.\u00c0 = \u00e0;
        this.\u00f1 = \u00f1;
        this.\u00f0 = \u00f0;
        this.\u00f2 = \u00f2;
    }
    
    public void set(final int \u00ed, final int \u00ee, final int \u00ef, final int \u00e0) {
        this.\u00ed = \u00ed;
        this.\u00ee = \u00ee;
        this.\u00ef = \u00ef;
        this.\u00c0 = \u00e0;
        this.\u00f1 = false;
        this.\u00f0 = 0;
        this.\u00f2 = false;
    }
    
    public int getType() {
        return this.\u00ed;
    }
    
    public int getRow() {
        return this.\u00ee;
    }
    
    public int getColumn() {
        return this.\u00ef;
    }
    
    public int getValue() {
        return this.\u00c0;
    }
    
    public boolean getMode() {
        return this.\u00f1;
    }
    
    public int getPreviousValue() {
        return this.\u00f0;
    }
    
    public boolean getPreviousMode() {
        return this.\u00f2;
    }
}
