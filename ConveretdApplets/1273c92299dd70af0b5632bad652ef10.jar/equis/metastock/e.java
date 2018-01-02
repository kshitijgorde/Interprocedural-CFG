// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Event;
import java.awt.Scrollbar;

class e extends Scrollbar
{
    d a;
    
    public e(final d a) {
        super(0, 0, 0, 0, 0);
        this.a = a;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id >= 601 && event.id <= 605) {
            final int n = MS4Java.d[0].n - MS4Java.d[0].m;
            final int value = ((e)event.target).getValue();
            final int n2 = value + n;
            if (value >= MS4Java.d[0].k.a && n2 <= MS4Java.d[0].k.b) {
                MS4Java.d[0].m = value;
                MS4Java.d[0].n = n2;
                MS4Java.e.repaint();
            }
        }
        return true;
    }
}
