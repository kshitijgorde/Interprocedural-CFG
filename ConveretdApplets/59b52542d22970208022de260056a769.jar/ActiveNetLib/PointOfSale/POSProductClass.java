// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSProductClass implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int product_class_id;
    public int product_department_id;
    public String class_description;
    
    public POSProductClass() {
        this.product_class_id = -1;
        this.product_department_id = 0;
        this.class_description = null;
    }
    
    public String toString() {
        return this.class_description;
    }
}
