// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSCustomerScanType implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public static final int by_customer_id = -1;
    public static final int by_membership_pass_id = -2;
    public int scan_type_id;
    public String scan_type_name;
    
    public POSCustomerScanType() {
        this.scan_type_id = 0;
        this.scan_type_name = null;
    }
    
    public POSCustomerScanType(final int type_id, final String type_name) {
        this.scan_type_id = 0;
        this.scan_type_name = null;
        this.scan_type_id = type_id;
        this.scan_type_name = type_name;
    }
    
    public String toString() {
        return this.scan_type_name;
    }
}
