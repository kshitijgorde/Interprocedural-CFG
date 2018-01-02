// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.math.BigDecimal;
import java.io.Serializable;

public class POSReceiptItemDiscount implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int discount_number;
    public BigDecimal percentage;
    public POSFeeDetail details;
    public int pos_product_id;
    public int coupon_id;
    public boolean always_recalculate;
    
    public POSReceiptItemDiscount() {
        this.discount_number = 0;
        this.percentage = null;
        this.details = null;
        this.pos_product_id = -1;
        this.coupon_id = 0;
        this.always_recalculate = true;
    }
}
