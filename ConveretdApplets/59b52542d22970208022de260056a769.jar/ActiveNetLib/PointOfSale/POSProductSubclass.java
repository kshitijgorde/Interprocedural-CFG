// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSProductSubclass implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int product_subclass_id;
    public int product_class_id;
    public String subclass_description;
    
    public POSProductSubclass() {
        this.product_subclass_id = 0;
        this.product_class_id = 0;
        this.subclass_description = null;
    }
    
    public String toString() {
        return this.subclass_description;
    }
}
