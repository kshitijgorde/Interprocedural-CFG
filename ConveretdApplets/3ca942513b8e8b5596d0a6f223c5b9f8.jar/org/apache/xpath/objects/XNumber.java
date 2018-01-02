// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xml.utils.WrappedRuntimeException;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;

public class XNumber extends XObject
{
    static final long serialVersionUID = -2720400709619020193L;
    double m_val;
    
    public XNumber(final double d) {
        this.m_val = d;
    }
    
    public XNumber(final Number num) {
        this.m_val = num.doubleValue();
        super.m_obj = num;
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
    
    public double num(final XPathContext xctxt) throws TransformerException {
        return this.m_val;
    }
    
    public boolean bool() {
        return !Double.isNaN(this.m_val) && this.m_val != 0.0;
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
                    if (s.charAt(len - 1) == '0') {
                        return s.substring(0, len - 1);
                    }
                    return s;
                }
                else {
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
                        return sign + s.substring(0, 1) + s.substring(2, e) + zeros(exp - nDigits);
                    }
                    while (s.charAt(e - 1) == '0') {
                        --e;
                    }
                    if (exp > 0) {
                        return sign + s.substring(0, 1) + s.substring(2, 2 + exp) + "." + s.substring(2 + exp, e);
                    }
                    return sign + "0." + zeros(-1 - exp) + s.substring(0, 1) + s.substring(2, e);
                }
            }
        }
    }
    
    private static String zeros(final int n) {
        if (n < 1) {
            return "";
        }
        final char[] buf = new char[n];
        for (int i = 0; i < n; ++i) {
            buf[i] = '0';
        }
        return new String(buf);
    }
    
    public Object object() {
        if (null == super.m_obj) {
            super.m_obj = new Double(this.m_val);
        }
        return super.m_obj;
    }
    
    public boolean equals(final XObject obj2) {
        final int t = obj2.getType();
        try {
            if (t == 4) {
                return obj2.equals(this);
            }
            if (t == 1) {
                return obj2.bool() == this.bool();
            }
            return this.m_val == obj2.num();
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
    }
    
    public boolean isStableNumber() {
        return true;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        visitor.visitNumberLiteral(owner, this);
    }
}
