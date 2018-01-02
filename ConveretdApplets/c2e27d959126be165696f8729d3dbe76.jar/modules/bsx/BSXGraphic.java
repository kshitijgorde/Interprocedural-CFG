// 
// Decompiled by Procyon v0.5.30
// 

package modules.bsx;

import java.util.Vector;

public class BSXGraphic extends Vector
{
    public BSXGraphic() {
    }
    
    public BSXGraphic(final int s) {
        super(s);
    }
    
    public void addPolygon(final BSXPolygon p) {
        this.addElement(p);
    }
}
