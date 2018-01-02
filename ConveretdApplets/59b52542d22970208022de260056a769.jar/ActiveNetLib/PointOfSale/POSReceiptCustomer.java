// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.util.List;
import java.math.BigDecimal;
import java.io.Serializable;

public class POSReceiptCustomer implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int customer_id;
    public String name;
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;
    public String tax_country;
    public String tax_state;
    public BigDecimal credit_available;
    public List<Integer> membership_discount_keys;
    public String err;
    
    public POSReceiptCustomer() {
        this.customer_id = 0;
        this.name = null;
        this.address1 = null;
        this.address2 = null;
        this.city = null;
        this.state = null;
        this.zip = null;
        this.tax_country = null;
        this.tax_state = null;
        this.membership_discount_keys = null;
        this.err = null;
    }
}
