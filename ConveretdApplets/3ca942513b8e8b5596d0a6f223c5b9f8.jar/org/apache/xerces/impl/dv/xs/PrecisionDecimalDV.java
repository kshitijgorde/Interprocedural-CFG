// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

class PrecisionDecimalDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 4088;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return new XPrecisionDecimal(s);
        }
        catch (NumberFormatException ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "precisionDecimal" });
        }
    }
    
    public int compare(final Object o, final Object o2) {
        return ((XPrecisionDecimal)o).compareTo((XPrecisionDecimal)o2);
    }
    
    public int getFractionDigits(final Object o) {
        return ((XPrecisionDecimal)o).fracDigits;
    }
    
    public int getTotalDigits(final Object o) {
        return ((XPrecisionDecimal)o).totalDigits;
    }
    
    public boolean isIdentical(final Object o, final Object o2) {
        return o2 instanceof XPrecisionDecimal && o instanceof XPrecisionDecimal && ((XPrecisionDecimal)o).isIdentical((XPrecisionDecimal)o2);
    }
    
    static class XPrecisionDecimal
    {
        int sign;
        int totalDigits;
        int intDigits;
        int fracDigits;
        String ivalue;
        String fvalue;
        int pvalue;
        private String canonical;
        
        XPrecisionDecimal(final String ivalue) throws NumberFormatException {
            this.sign = 1;
            this.totalDigits = 0;
            this.intDigits = 0;
            this.fracDigits = 0;
            this.ivalue = "";
            this.fvalue = "";
            this.pvalue = 0;
            if (ivalue.equals("NaN")) {
                this.ivalue = ivalue;
                this.sign = 0;
            }
            if (ivalue.equals("+INF") || ivalue.equals("INF") || ivalue.equals("-INF")) {
                this.ivalue = ((ivalue.charAt(0) == '+') ? ivalue.substring(1) : ivalue);
                return;
            }
            this.initD(ivalue);
        }
        
        void initD(final String s) throws NumberFormatException {
            final int length = s.length();
            if (length == 0) {
                throw new NumberFormatException();
            }
            boolean b = false;
            int n = 0;
            int i = 0;
            if (s.charAt(0) == '+') {
                b = true;
            }
            else if (s.charAt(0) == '-') {
                b = true;
                this.sign = -1;
            }
            int n2;
            for (n2 = (b ? 1 : 0); n2 < length && s.charAt(n2) == '0'; ++n2) {}
            int n3;
            for (n3 = n2; n3 < length && TypeValidator.isDigit(s.charAt(n3)); ++n3) {}
            if (n3 < length) {
                if (s.charAt(n3) != '.' && s.charAt(n3) != 'E' && s.charAt(n3) != 'e') {
                    throw new NumberFormatException();
                }
                if (s.charAt(n3) == '.') {
                    for (n = (i = n3 + 1); i < length; ++i) {
                        if (!TypeValidator.isDigit(s.charAt(i))) {
                            break;
                        }
                    }
                }
                else {
                    this.pvalue = Integer.parseInt(s.substring(n3 + 1, length));
                }
            }
            if ((b ? 1 : 0) == n3 && n == i) {
                throw new NumberFormatException();
            }
            for (int j = n; j < i; ++j) {
                if (!TypeValidator.isDigit(s.charAt(j))) {
                    throw new NumberFormatException();
                }
            }
            this.intDigits = n3 - n2;
            this.fracDigits = i - n;
            if (this.intDigits > 0) {
                this.ivalue = s.substring(n2, n3);
            }
            if (this.fracDigits > 0) {
                this.fvalue = s.substring(n, i);
                if (i < length) {
                    this.pvalue = Integer.parseInt(s.substring(i + 1, length));
                }
            }
            this.totalDigits = this.intDigits + this.fracDigits;
        }
        
        public boolean equals(final Object o) {
            return o == this || (o instanceof XPrecisionDecimal && this.compareTo((XPrecisionDecimal)o) == 0);
        }
        
        private int compareFractionalPart(final XPrecisionDecimal xPrecisionDecimal) {
            if (this.fvalue.equals(xPrecisionDecimal.fvalue)) {
                return 0;
            }
            final StringBuffer sb = new StringBuffer(this.fvalue);
            final StringBuffer sb2 = new StringBuffer(xPrecisionDecimal.fvalue);
            this.truncateTrailingZeros(sb, sb2);
            return sb.toString().compareTo(sb2.toString());
        }
        
        private void truncateTrailingZeros(final StringBuffer sb, final StringBuffer sb2) {
            for (int n = sb.length() - 1; n >= 0 && sb.charAt(n) == '0'; --n) {
                sb.deleteCharAt(n);
            }
            for (int n2 = sb2.length() - 1; n2 >= 0 && sb2.charAt(n2) == '0'; --n2) {
                sb2.deleteCharAt(n2);
            }
        }
        
        public int compareTo(final XPrecisionDecimal xPrecisionDecimal) {
            if (this.sign == 0) {
                return 2;
            }
            if (this.ivalue.equals("INF") || xPrecisionDecimal.ivalue.equals("INF")) {
                if (this.ivalue.equals(xPrecisionDecimal.ivalue)) {
                    return 0;
                }
                if (this.ivalue.equals("INF")) {
                    return 1;
                }
                return -1;
            }
            else if (this.ivalue.equals("-INF") || xPrecisionDecimal.ivalue.equals("-INF")) {
                if (this.ivalue.equals(xPrecisionDecimal.ivalue)) {
                    return 0;
                }
                if (this.ivalue.equals("-INF")) {
                    return -1;
                }
                return 1;
            }
            else {
                if (this.sign != xPrecisionDecimal.sign) {
                    return (this.sign > xPrecisionDecimal.sign) ? 1 : -1;
                }
                return this.sign * this.compare(xPrecisionDecimal);
            }
        }
        
        private int compare(final XPrecisionDecimal xPrecisionDecimal) {
            if (this.pvalue == 0 && xPrecisionDecimal.pvalue == 0) {
                return this.intComp(xPrecisionDecimal);
            }
            if (this.pvalue == xPrecisionDecimal.pvalue) {
                return this.intComp(xPrecisionDecimal);
            }
            if (this.intDigits + this.pvalue != xPrecisionDecimal.intDigits + xPrecisionDecimal.pvalue) {
                return (this.intDigits + this.pvalue > xPrecisionDecimal.intDigits + xPrecisionDecimal.pvalue) ? 1 : -1;
            }
            if (this.pvalue > xPrecisionDecimal.pvalue) {
                final int n = this.pvalue - xPrecisionDecimal.pvalue;
                final StringBuffer sb = new StringBuffer(this.ivalue);
                final StringBuffer sb2 = new StringBuffer(this.fvalue);
                for (int i = 0; i < n; ++i) {
                    if (i < this.fracDigits) {
                        sb.append(this.fvalue.charAt(i));
                        sb2.deleteCharAt(i);
                    }
                    else {
                        sb.append('0');
                    }
                }
                return this.compareDecimal(sb.toString(), xPrecisionDecimal.ivalue, sb2.toString(), xPrecisionDecimal.fvalue);
            }
            final int n2 = xPrecisionDecimal.pvalue - this.pvalue;
            final StringBuffer sb3 = new StringBuffer(xPrecisionDecimal.ivalue);
            final StringBuffer sb4 = new StringBuffer(xPrecisionDecimal.fvalue);
            for (int j = 0; j < n2; ++j) {
                if (j < xPrecisionDecimal.fracDigits) {
                    sb3.append(xPrecisionDecimal.fvalue.charAt(j));
                    sb4.deleteCharAt(j);
                }
                else {
                    sb3.append('0');
                }
            }
            return this.compareDecimal(this.ivalue, sb3.toString(), this.fvalue, sb4.toString());
        }
        
        private int intComp(final XPrecisionDecimal xPrecisionDecimal) {
            if (this.intDigits != xPrecisionDecimal.intDigits) {
                return (this.intDigits > xPrecisionDecimal.intDigits) ? 1 : -1;
            }
            return this.compareDecimal(this.ivalue, xPrecisionDecimal.ivalue, this.fvalue, xPrecisionDecimal.fvalue);
        }
        
        private int compareDecimal(final String s, final String s2, final String s3, final String s4) {
            final int compareTo = s.compareTo(s3);
            if (compareTo != 0) {
                return (compareTo > 0) ? 1 : -1;
            }
            if (s2.equals(s4)) {
                return 0;
            }
            final StringBuffer sb = new StringBuffer(s2);
            final StringBuffer sb2 = new StringBuffer(s4);
            this.truncateTrailingZeros(sb, sb2);
            final int compareTo2 = sb.toString().compareTo(sb2.toString());
            return (compareTo2 == 0) ? 0 : ((compareTo2 > 0) ? 1 : -1);
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                this.makeCanonical();
            }
            return this.canonical;
        }
        
        private void makeCanonical() {
            this.canonical = "TBD by Working Group";
        }
        
        public boolean isIdentical(final XPrecisionDecimal xPrecisionDecimal) {
            return (this.ivalue.equals(xPrecisionDecimal.ivalue) && (this.ivalue.equals("INF") || this.ivalue.equals("-INF") || this.ivalue.equals("NaN"))) || (this.sign == xPrecisionDecimal.sign && this.intDigits == xPrecisionDecimal.intDigits && this.fracDigits == xPrecisionDecimal.fracDigits && this.pvalue == xPrecisionDecimal.pvalue && this.ivalue.equals(xPrecisionDecimal.ivalue) && this.fvalue.equals(xPrecisionDecimal.fvalue));
        }
    }
}
