import java.applet.Applet;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class a implements Runnable
{
    private c a;
    private URL _fldfor;
    private boolean _fldnew;
    private Applet _fldif;
    private String _fldint;
    private boolean _fldtry;
    int _flddo;
    
    public a(final c a, final URL fldfor, final String fldint, final Applet fldif, final int flddo, final boolean fldtry) {
        this.a = a;
        this._fldfor = fldfor;
        this._fldnew = true;
        this._fldif = fldif;
        this._flddo = flddo;
        this._fldint = fldint;
        this._fldtry = fldtry;
    }
    
    public void run() {
        for (int mthdo = this.a._mthdo(), i = 0; i < mthdo; ++i) {
            if (!this._fldnew) {
                break;
            }
            if (this._fldtry) {
                this.a.a(this._fldif.getImage(this._fldfor, this._fldint + "?preview=1"));
            }
            else {
                this.a.a(this._fldif.getImage(this._fldfor, this._fldint + "+" + System.currentTimeMillis()));
            }
            try {
                Thread.sleep(this._flddo);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace(System.err);
            }
        }
        while (this._fldnew) {
            if (this._fldtry) {
                this.a.a(this._fldif.getImage(this._fldfor, this._fldint + "?preview=1"));
            }
            else {
                this.a.a(this._fldif.getImage(this._fldfor, this._fldint + "+" + System.currentTimeMillis()));
            }
        }
    }
    
    public void a(final boolean fldnew) {
        this._fldnew = fldnew;
    }
}
