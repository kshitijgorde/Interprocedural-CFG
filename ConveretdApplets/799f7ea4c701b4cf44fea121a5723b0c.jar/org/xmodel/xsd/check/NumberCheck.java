// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.math.BigDecimal;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class NumberCheck extends AbstractCheck
{
    IModelObject R;
    IModelObject Q;
    IModelObject T;
    IModelObject P;
    String O;
    String S;
    
    public NumberCheck(final IModelObject modelObject) {
        super(modelObject);
        this.R = modelObject.getFirstChild("float");
        this.Q = modelObject.getFirstChild("integer");
        this.T = modelObject.getFirstChild("min");
        this.P = modelObject.getFirstChild("max");
        this.O = Xlate.get(this.T, "");
        this.S = Xlate.get(this.P, "");
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "");
        if (this.Q != null && value.contains(".")) {
            return false;
        }
        try {
            if (this.T != null) {
                if (this.O.length() >= 18) {
                    final BigDecimal bigDecimal = new BigDecimal(this.O);
                    final BigDecimal bigDecimal2 = new BigDecimal(value);
                    if (Xlate.get(this.T, "exclusive", false)) {
                        if (bigDecimal2.compareTo(bigDecimal) <= 0) {
                            return false;
                        }
                    }
                    else if (bigDecimal2.compareTo(bigDecimal) < 0) {
                        return false;
                    }
                }
                else if (this.R != null) {
                    final double doubleValue = Double.valueOf(this.O);
                    final double doubleValue2 = Double.valueOf(value);
                    if (Xlate.get(this.T, "exclusive", false)) {
                        if (doubleValue2 <= doubleValue) {
                            return false;
                        }
                    }
                    else if (doubleValue2 < doubleValue) {
                        return false;
                    }
                }
                else {
                    final long longValue = Long.valueOf(this.O);
                    final long longValue2 = Long.valueOf(value);
                    if (Xlate.get(this.T, "exclusive", false)) {
                        if (longValue2 <= longValue) {
                            return false;
                        }
                    }
                    else if (longValue2 < longValue) {
                        return false;
                    }
                }
            }
            if (this.P != null) {
                if (this.S.length() >= 18) {
                    final BigDecimal bigDecimal3 = new BigDecimal(this.S);
                    final BigDecimal bigDecimal4 = new BigDecimal(value);
                    if (Xlate.get(this.P, "exclusive", false)) {
                        if (bigDecimal4.compareTo(bigDecimal3) >= 0) {
                            return false;
                        }
                    }
                    else if (bigDecimal4.compareTo(bigDecimal3) > 0) {
                        return false;
                    }
                }
                else if (this.R != null) {
                    final double doubleValue3 = Double.valueOf(this.S);
                    final double doubleValue4 = Double.valueOf(value);
                    if (Xlate.get(this.P, "exclusive", false)) {
                        if (doubleValue4 >= doubleValue3) {
                            return false;
                        }
                    }
                    else if (doubleValue4 > doubleValue3) {
                        return false;
                    }
                }
                else {
                    final long longValue3 = Long.valueOf(this.S);
                    final long longValue4 = Long.valueOf(value);
                    if (Xlate.get(this.P, "exclusive", false)) {
                        if (longValue4 >= longValue3) {
                            return false;
                        }
                    }
                    else if (longValue4 > longValue3) {
                        return false;
                    }
                }
            }
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
