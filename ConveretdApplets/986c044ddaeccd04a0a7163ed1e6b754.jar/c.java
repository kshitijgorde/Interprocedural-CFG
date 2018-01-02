import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private Image[] _flddo;
    private int _fldint;
    public static int a;
    private boolean _fldfor;
    private int _fldnew;
    private int _fldif;
    
    public c() {
        this(10);
    }
    
    public c(final int fldint) {
        this._fldint = 0;
        this._fldfor = false;
        this._flddo = new Image[fldint];
        this._fldif = 0;
        this._fldint = fldint;
        this._fldnew = 0;
    }
    
    public synchronized void a(final Image image) {
        while (this.a() >= this._fldint && !this._fldfor) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace(System.err);
            }
        }
        final int n = (this._fldnew + this._fldif) % this._fldint;
        this._flddo[n] = null;
        this._flddo[n] = image;
        ++this._fldif;
        this.notifyAll();
    }
    
    public synchronized void _mthfor() {
        for (int i = 0; i < this._fldif; ++i) {
            this._flddo[(this._fldnew + this._fldif) % this._fldint] = null;
        }
        this._fldif = 0;
        this._fldnew = 0;
        this.notifyAll();
    }
    
    public int _mthdo() {
        return this._fldint;
    }
    
    public synchronized Image _mthif() {
        while (this.a() == 0) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace(System.err);
            }
        }
        final Image image = this._flddo[this._fldnew];
        this._flddo[this._fldnew] = null;
        ++this._fldnew;
        this._fldnew %= this._fldint;
        --this._fldif;
        this.notifyAll();
        return image;
    }
    
    public int a() {
        return this._fldif;
    }
    
    public synchronized void a(final boolean fldfor) {
        this._fldfor = fldfor;
        this.notifyAll();
    }
    
    static {
        c.a = 10;
    }
}
