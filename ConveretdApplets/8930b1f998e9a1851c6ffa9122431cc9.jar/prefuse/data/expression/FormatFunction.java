// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import prefuse.data.Tuple;

class FormatFunction extends StringFunction
{
    public FormatFunction() {
        super(2);
    }
    
    public String getName() {
        return "FORMAT";
    }
    
    public Object get(final Tuple tuple) {
        final double double1 = this.param(0).getDouble(tuple);
        final int int1 = this.param(1).getInt(tuple);
        final DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getInstance();
        decimalFormat.setMinimumFractionDigits(int1);
        decimalFormat.setMaximumFractionDigits(int1);
        return decimalFormat.format(double1);
    }
}
