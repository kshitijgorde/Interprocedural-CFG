// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.util.Enumeration;
import java.util.Vector;

public class EmuPanelVector extends Vector
{
    private Enumeration a;
    
    public EmuPanel a() {
        this.a = this.elements();
        return this.b();
    }
    
    public EmuPanel b() {
        if (this.a.hasMoreElements()) {
            return this.a.nextElement();
        }
        return null;
    }
    
    public void a(final EmuPanel emuPanel) {
        super.addElement(emuPanel);
    }
}
