// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.math.BigDecimal;
import borland.jbcl.util.Variant;

public class BigDecimalFormatter extends VariantFormatter
{
    private int scale;
    
    public BigDecimalFormatter(final int scale) {
        this.scale = -1;
        this.scale = scale;
    }
    
    public String format(final Variant value) {
        final String s = (value == null || value.isNull()) ? "" : ((this.scale >= 0) ? value.getBigDecimal().setScale(this.scale, 4).toString() : value.getBigDecimal().toString());
        return s;
    }
    
    public void parse(final String stringValue, final Variant value) {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        BigDecimal bn = new BigDecimal(stringValue);
        if (this.scale >= 0) {
            bn = bn.setScale(this.scale, 4);
        }
        value.setBigDecimal(bn);
    }
    
    public int getVariantType() {
        return 10;
    }
}
