// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.math.BigDecimal;
import java.io.Serializable;

public class POSTaxBracket implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public BigDecimal from_amount;
    public BigDecimal to_amount;
    public BigDecimal tax;
    
    public POSTaxBracket() {
        this.from_amount = null;
        this.to_amount = null;
        this.tax = null;
    }
}
