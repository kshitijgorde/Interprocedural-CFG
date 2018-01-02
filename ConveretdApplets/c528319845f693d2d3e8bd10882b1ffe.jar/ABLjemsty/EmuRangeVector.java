// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.util.Enumeration;
import java.util.Vector;

public class EmuRangeVector extends Vector
{
    private Enumeration a;
    
    public EmuRange a() {
        this.a = this.elements();
        return this.b();
    }
    
    public EmuRange b() {
        if (this.a.hasMoreElements()) {
            return this.a.nextElement();
        }
        return null;
    }
    
    public EmuRange a(final EmuRange emuRange) {
        EmuRange emuRange2 = null;
        final int c = this.c(emuRange);
        if (c < 0) {
            final int n = -(c + 1);
            emuRange2 = (EmuRange)this.elementAt(n);
            super.setElementAt(emuRange, n);
        }
        else {
            super.insertElementAt(emuRange, c);
        }
        return emuRange2;
    }
    
    public boolean b(final EmuRange emuRange) {
        final int c = this.c(emuRange);
        if (c < 0) {
            return false;
        }
        super.insertElementAt(emuRange, c);
        return true;
    }
    
    public EmuRange a(final int n) {
        return super.elementAt(n);
    }
    
    private int c(final EmuRange emuRange) {
        int i = 0;
        while (i < this.size()) {
            final EmuRange a = this.a(i);
            if (emuRange.c <= a.c + a.d) {
                if (a.c > emuRange.c + emuRange.d) {
                    return i;
                }
                return -(i + 1);
            }
            else {
                ++i;
            }
        }
        return i;
    }
}
