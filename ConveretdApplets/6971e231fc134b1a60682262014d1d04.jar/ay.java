import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ay extends Observable
{
    public static final int a = 1;
    public static final int _fldint = 2;
    public static final int _fldfor = -1;
    int _flddo;
    int _fldif;
    
    public ay(final int flddo) {
        this._flddo = flddo;
        this._fldif = -1;
    }
    
    public void a(final int fldif) {
        this._fldif = fldif;
        this.setChanged();
    }
}
