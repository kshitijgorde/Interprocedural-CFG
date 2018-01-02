// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.math.BigDecimal;
import java.io.Serializable;

public class POSKeyboardProductFee implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int pos_product_fee_id;
    public int charge_id;
    public int gl_account_id;
    public String fee_description;
    public BigDecimal fee_amount;
    public boolean[] taxable_by;
    
    public POSKeyboardProductFee() {
        this.pos_product_fee_id = -1;
        this.charge_id = 0;
        this.gl_account_id = 0;
        this.fee_description = null;
        this.fee_amount = null;
        this.taxable_by = new boolean[POSTax.number_of_taxes];
    }
}
