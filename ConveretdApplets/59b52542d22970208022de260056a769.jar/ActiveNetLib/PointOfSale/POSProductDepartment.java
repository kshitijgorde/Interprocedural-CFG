// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSProductDepartment implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int product_department_id;
    public String department_description;
    
    public POSProductDepartment() {
        this.product_department_id = 0;
        this.department_description = null;
    }
    
    public String toString() {
        return this.department_description;
    }
}
