// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import javax.xml.transform.TransformerException;

public class XNumber extends XObject
{
    double m_val;
    
    public XNumber(final double d) {
        this.m_val = d;
    }
    
    public boolean bool() {
        return !Double.isNaN(this.m_val) && this.m_val != 0.0;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        return this.m_val == obj2.num();
    }
    
    public int getType() {
        return 2;
    }
    
    public String getTypeString() {
        return "#NUMBER";
    }
    
    public double num() {
        return this.m_val;
    }
    
    public Object object() {
        return new Double(this.m_val);
    }
    
    public String str() {
        if (Double.isNaN(this.m_val)) {
            return "NaN";
        }
        if (Double.isInfinite(this.m_val)) {
            if (this.m_val > 0.0) {
                return "Infinity";
            }
            return "-Infinity";
        }
        else {
            final double num = this.m_val;
            String s = Double.toString(num);
            final int len = s.length();
            if (s.charAt(len - 2) == '.' && s.charAt(len - 1) == '0') {
                s = s.substring(0, len - 2);
                if (s.equals("-0")) {
                    return "0";
                }
                return s;
            }
            else {
                int e = s.indexOf(69);
                if (e < 0) {
                    return s;
                }
                final int exp = Integer.parseInt(s.substring(e + 1));
                String sign;
                if (s.charAt(0) == '-') {
                    sign = "-";
                    s = s.substring(1);
                    --e;
                }
                else {
                    sign = "";
                }
                final int nDigits = e - 2;
                if (exp >= nDigits) {
                    return String.valueOf(sign) + s.substring(0, 1) + s.substring(2, e) + zeros(exp - nDigits);
                }
                if (exp > 0) {
                    return String.valueOf(sign) + s.substring(0, 1) + s.substring(2, 2 + exp) + "." + s.substring(2 + exp, e);
                }
                return String.valueOf(sign) + "0." + zeros(-1 - exp) + s.substring(0, 1) + s.substring(2, e);
            }
        }
    }
    
    private static String zeros(final int n) {
        final char[] buf = new char[n];
        for (int i = 0; i < n; ++i) {
            buf[i] = '0';
        }
        return new String(buf);
    }
}
