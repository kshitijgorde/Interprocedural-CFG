// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

class XDecimal
{
    int sign;
    int totalDigits;
    int intDigits;
    int fracDigits;
    String ivalue;
    String fvalue;
    boolean integer;
    private String canonical;
    
    XDecimal(final String content) throws NumberFormatException {
        this.sign = 1;
        this.totalDigits = 0;
        this.intDigits = 0;
        this.fracDigits = 0;
        this.ivalue = "";
        this.fvalue = "";
        this.integer = false;
        this.initD(content);
    }
    
    XDecimal(final String content, final boolean integer) throws NumberFormatException {
        this.sign = 1;
        this.totalDigits = 0;
        this.intDigits = 0;
        this.fracDigits = 0;
        this.ivalue = "";
        this.fvalue = "";
        this.integer = false;
        if (integer) {
            this.initI(content);
        }
        else {
            this.initD(content);
        }
    }
    
    void initD(final String content) throws NumberFormatException {
        final int len = content.length();
        if (len == 0) {
            throw new NumberFormatException();
        }
        int intStart = 0;
        int intEnd = 0;
        int fracStart = 0;
        int fracEnd = 0;
        if (content.charAt(0) == '+') {
            intStart = 1;
        }
        else if (content.charAt(0) == '-') {
            intStart = 1;
            this.sign = -1;
        }
        int actualIntStart;
        for (actualIntStart = intStart; actualIntStart < len && content.charAt(actualIntStart) == '0'; ++actualIntStart) {}
        for (intEnd = actualIntStart; intEnd < len && TypeValidator.isDigit(content.charAt(intEnd)); ++intEnd) {}
        if (intEnd < len) {
            if (content.charAt(intEnd) != '.') {
                throw new NumberFormatException();
            }
            fracStart = intEnd + 1;
            fracEnd = len;
        }
        if (intStart == intEnd && fracStart == fracEnd) {
            throw new NumberFormatException();
        }
        while (fracEnd > fracStart && content.charAt(fracEnd - 1) == '0') {
            --fracEnd;
        }
        for (int fracPos = fracStart; fracPos < fracEnd; ++fracPos) {
            if (!TypeValidator.isDigit(content.charAt(fracPos))) {
                throw new NumberFormatException();
            }
        }
        this.intDigits = intEnd - actualIntStart;
        this.fracDigits = fracEnd - fracStart;
        this.totalDigits = ((this.intDigits == 0) ? 1 : this.intDigits) + this.fracDigits;
        if (this.intDigits > 0) {
            this.ivalue = content.substring(actualIntStart, intEnd);
            if (this.fracDigits > 0) {
                this.fvalue = content.substring(fracStart, fracEnd);
            }
        }
        else if (this.fracDigits > 0) {
            this.fvalue = content.substring(fracStart, fracEnd);
        }
        else {
            this.sign = 0;
        }
    }
    
    void initI(final String content) throws NumberFormatException {
        final int len = content.length();
        if (len == 0) {
            throw new NumberFormatException();
        }
        int intStart = 0;
        int intEnd = 0;
        if (content.charAt(0) == '+') {
            intStart = 1;
        }
        else if (content.charAt(0) == '-') {
            intStart = 1;
            this.sign = -1;
        }
        int actualIntStart;
        for (actualIntStart = intStart; actualIntStart < len && content.charAt(actualIntStart) == '0'; ++actualIntStart) {}
        for (intEnd = actualIntStart; intEnd < len && TypeValidator.isDigit(content.charAt(intEnd)); ++intEnd) {}
        if (intEnd < len) {
            throw new NumberFormatException();
        }
        if (intStart == intEnd) {
            throw new NumberFormatException();
        }
        this.intDigits = intEnd - actualIntStart;
        this.fracDigits = 0;
        this.totalDigits = ((this.intDigits == 0) ? 1 : this.intDigits);
        if (this.intDigits > 0) {
            this.ivalue = content.substring(actualIntStart, intEnd);
        }
        else {
            this.sign = 0;
        }
        this.integer = true;
    }
    
    public boolean equals(final Object val) {
        if (val == this) {
            return true;
        }
        if (!(val instanceof XDecimal)) {
            return false;
        }
        final XDecimal oval = (XDecimal)val;
        return this.sign == oval.sign && (this.sign == 0 || (this.intDigits == oval.intDigits && this.fracDigits == oval.fracDigits && this.ivalue.equals(oval.ivalue) && this.fvalue.equals(oval.fvalue)));
    }
    
    public int compareTo(final XDecimal val) {
        if (this.sign != val.sign) {
            return (this.sign > val.sign) ? 1 : -1;
        }
        if (this.sign == 0) {
            return 0;
        }
        return this.sign * this.intComp(val);
    }
    
    private int intComp(final XDecimal val) {
        if (this.intDigits != val.intDigits) {
            return (this.intDigits > val.intDigits) ? 1 : -1;
        }
        int ret = this.ivalue.compareTo(val.ivalue);
        if (ret != 0) {
            return (ret > 0) ? 1 : -1;
        }
        ret = this.fvalue.compareTo(val.fvalue);
        return (ret == 0) ? 0 : ((ret > 0) ? 1 : -1);
    }
    
    public synchronized String toString() {
        if (this.canonical == null) {
            this.makeCanonical();
        }
        return this.canonical;
    }
    
    private void makeCanonical() {
        if (this.sign == 0) {
            if (this.integer) {
                this.canonical = "0";
            }
            else {
                this.canonical = "0.0";
            }
            return;
        }
        if (this.integer && this.sign > 0) {
            this.canonical = this.ivalue;
            return;
        }
        final StringBuffer buffer = new StringBuffer(this.totalDigits + 2);
        if (this.sign == -1) {
            buffer.append('-');
        }
        if (this.intDigits != 0) {
            buffer.append(this.ivalue);
        }
        else {
            buffer.append('0');
        }
        if (!this.integer) {
            buffer.append('.');
            if (this.fracDigits != 0) {
                buffer.append(this.fvalue);
            }
            else {
                buffer.append('0');
            }
        }
        this.canonical = buffer.toString();
    }
}
