// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class FloatDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 2032;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XFloat(content);
        }
        catch (NumberFormatException ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "float" });
        }
    }
    
    public int compare(final Object value1, final Object value2) {
        return ((XFloat)value1).compareTo((XFloat)value2);
    }
    
    private static final class XFloat
    {
        private float value;
        private String canonical;
        
        public XFloat(final String s) throws NumberFormatException {
            try {
                this.value = Float.parseFloat(s);
            }
            catch (NumberFormatException nfe) {
                if (s.equals("INF")) {
                    this.value = Float.POSITIVE_INFINITY;
                }
                else if (s.equals("-INF")) {
                    this.value = Float.NEGATIVE_INFINITY;
                }
                else {
                    if (!s.equals("NaN")) {
                        throw nfe;
                    }
                    this.value = Float.NaN;
                }
            }
        }
        
        public boolean equals(final Object val) {
            if (val == this) {
                return true;
            }
            if (!(val instanceof XFloat)) {
                return false;
            }
            final XFloat oval = (XFloat)val;
            return this.value == oval.value || (this.value != this.value && oval.value != oval.value);
        }
        
        private int compareTo(final XFloat val) {
            final float oval = val.value;
            if (this.value < oval) {
                return -1;
            }
            if (this.value > oval) {
                return 1;
            }
            if (this.value == oval) {
                return 0;
            }
            if (this.value == this.value) {
                return -1;
            }
            if (oval != oval) {
                return 0;
            }
            return 1;
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                if (this.value == Float.POSITIVE_INFINITY) {
                    this.canonical = "INF";
                }
                else if (this.value == Float.NEGATIVE_INFINITY) {
                    this.canonical = "-INF";
                }
                else if (this.value != this.value) {
                    this.canonical = "NaN";
                }
                else if (this.value == 0.0f) {
                    this.canonical = "0.0E1";
                }
                else {
                    this.canonical = Float.toString(this.value);
                    if (this.canonical.indexOf(69) == -1) {
                        int len = this.canonical.length();
                        final char[] chars = new char[len + 3];
                        this.canonical.getChars(0, len, chars, 0);
                        final int edp = (chars[0] == '-') ? 2 : 1;
                        if (this.value >= 1.0f || this.value <= -1.0f) {
                            int i;
                            int dp;
                            for (dp = (i = this.canonical.indexOf(46)); i > edp; --i) {
                                chars[i] = chars[i - 1];
                            }
                            chars[edp] = '.';
                            while (chars[len - 1] == '0') {
                                --len;
                            }
                            if (chars[len - 1] == '.') {
                                ++len;
                            }
                            chars[len++] = 'E';
                            final int shift = dp - edp;
                            chars[len++] = (char)(shift + 48);
                        }
                        else {
                            int nzp;
                            for (nzp = edp + 1; chars[nzp] == '0'; ++nzp) {}
                            chars[edp - 1] = chars[nzp];
                            chars[edp] = '.';
                            for (int i = nzp + 1, j = edp + 1; i < len; ++i, ++j) {
                                chars[j] = chars[i];
                            }
                            len -= nzp - edp;
                            if (len == edp + 1) {
                                chars[len++] = '0';
                            }
                            chars[len++] = 'E';
                            chars[len++] = '-';
                            final int shift2 = nzp - edp;
                            chars[len++] = (char)(shift2 + 48);
                        }
                        this.canonical = new String(chars, 0, len);
                    }
                }
            }
            return this.canonical;
        }
    }
}
