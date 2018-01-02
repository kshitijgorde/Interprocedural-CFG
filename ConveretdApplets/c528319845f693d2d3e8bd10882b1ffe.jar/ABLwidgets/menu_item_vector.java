// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.util.Enumeration;
import java.util.Vector;

public class menu_item_vector extends Vector
{
    private Enumeration a;
    
    public menu_item a() {
        this.a = this.elements();
        return this.b();
    }
    
    public menu_item b() {
        if (this.a.hasMoreElements()) {
            return this.a.nextElement();
        }
        return null;
    }
    
    public void a(final menu_item menu_item) {
        super.addElement(menu_item);
    }
}
