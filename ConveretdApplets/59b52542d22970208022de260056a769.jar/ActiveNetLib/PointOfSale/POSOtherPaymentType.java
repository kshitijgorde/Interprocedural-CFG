// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.io.Serializable;

public class POSOtherPaymentType implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public int payment_type_id;
    public String payment_type;
    
    public POSOtherPaymentType() {
        this.payment_type_id = 0;
        this.payment_type = null;
    }
    
    public String toString() {
        return this.payment_type;
    }
}
