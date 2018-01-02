// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.util.Enumeration;
import java.util.Vector;

public class EmuRectVector extends Vector
{
    private Enumeration a;
    
    public EmuRect a() {
        this.a = this.elements();
        return this.b();
    }
    
    public EmuRect b() {
        if (this.a.hasMoreElements()) {
            return this.a.nextElement();
        }
        return null;
    }
    
    public void a(final EmuRect emuRect) {
        super.addElement(emuRect);
    }
}
