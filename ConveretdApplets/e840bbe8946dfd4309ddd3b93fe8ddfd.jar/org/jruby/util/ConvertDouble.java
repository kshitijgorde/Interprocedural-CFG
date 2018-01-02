// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

public class ConvertDouble
{
    public static final double byteListToDouble(final ByteList bytes, final boolean strict) {
        return new DoubleConverter().parse(bytes, strict, false);
    }
    
    public static final double byteListToDouble19(final ByteList bytes, final boolean strict) {
        return new DoubleConverter().parse(bytes, strict, true);
    }
    
    public static class DoubleConverter
    {
        private byte[] bytes;
        private int index;
        private int endIndex;
        private boolean isStrict;
        private boolean is19;
        private char[] chars;
        private int charsIndex;
        
        public void init(final ByteList list, final boolean isStrict, final boolean is19) {
            this.bytes = list.getUnsafeBytes();
            this.index = list.begin();
            this.endIndex = this.index + list.length();
            this.isStrict = isStrict;
            this.is19 = is19;
            this.chars = new char[list.length() + 1];
            this.charsIndex = 0;
        }
        
        private byte next() {
            return this.bytes[this.index++];
        }
        
        private boolean previous() {
            --this.index;
            return false;
        }
        
        private boolean isDigit(final byte b) {
            return b >= 48 && b <= 57;
        }
        
        private boolean isEOS() {
            return this.index >= this.endIndex;
        }
        
        private boolean isExponent(final byte b) {
            return b == 101 || b == 69;
        }
        
        private boolean isWhitespace(final byte b) {
            return b == 32 || (b <= 13 && b >= 9 && b != 11);
        }
        
        private void addToResult(final byte b) {
            this.chars[this.charsIndex++] = (char)b;
        }
        
        private boolean eatUnderscores() {
            while (!this.isEOS()) {
                final byte value = this.next();
                if (value != 95) {
                    this.previous();
                    return this.isEOS();
                }
                if (!this.isStrict) {
                    continue;
                }
                this.strictError();
            }
            return true;
        }
        
        private double completeCalculation() {
            if (this.charsIndex == 0 || (this.charsIndex == 1 && this.chars[0] == '-')) {
                if (this.isStrict) {
                    this.strictError();
                }
                return 0.0;
            }
            if (this.isExponent((byte)this.chars[this.charsIndex - 1])) {
                if (this.isStrict) {
                    this.strictError();
                }
                this.addToResult((byte)48);
            }
            else if (this.isStrict && !this.isEOS()) {
                this.strictError();
            }
            return Double.parseDouble(new String(this.chars));
        }
        
        private void strictError() {
            throw new NumberFormatException("does not meet strict criteria");
        }
        
        public double parse(final ByteList list, final boolean strict, final boolean is19) {
            this.init(list, strict, is19);
            if (this.skipWhitespace()) {
                return this.completeCalculation();
            }
            if (this.parseOptionalSign()) {
                return this.completeCalculation();
            }
            this.parseDigits();
            return this.completeCalculation();
        }
        
        private boolean skipWhitespace() {
            while (!this.isEOS()) {
                final byte value = this.next();
                if (this.isWhitespace(value)) {
                    continue;
                }
                if (value != 95 || this.isStrict || this.is19) {
                    return this.previous();
                }
            }
            return true;
        }
        
        private boolean parseOptionalSign() {
            final byte sign = this.next();
            if (sign == 45) {
                this.addToResult(sign);
            }
            else if (sign != 43) {
                this.previous();
            }
            return this.isEOS();
        }
        
        private boolean parseDigits() {
            if (!this.isEOS()) {
                final byte value = this.next();
                if (this.isDigit(value)) {
                    this.addToResult(value);
                }
                else {
                    if (value == 46) {
                        this.addToResult(value);
                        return this.parseDecimalDigits();
                    }
                    return this.isEOS();
                }
            }
            else if (this.isStrict) {
                this.strictError();
            }
            while (!this.isEOS()) {
                final byte value = this.next();
                if (this.isDigit(value)) {
                    this.addToResult(value);
                }
                else {
                    if (value == 46) {
                        this.addToResult(value);
                        return this.parseDecimalDigits();
                    }
                    if (value == 95) {
                        this.verifyNumberAfterUnderscore();
                    }
                    else {
                        if (this.isExponent(value)) {
                            this.addToResult(value);
                            return this.parseExponent();
                        }
                        if (this.isWhitespace(value)) {
                            return this.skipWhitespace();
                        }
                        if (this.isStrict) {
                            this.strictError();
                        }
                        return this.isEOS();
                    }
                }
            }
            return true;
        }
        
        private boolean parseDecimalDigits() {
            if (this.isEOS()) {
                if (this.isStrict) {
                    this.strictError();
                }
                return true;
            }
            byte value = this.next();
            if (this.isDigit(value)) {
                this.addToResult(value);
            }
            else {
                if (value != 95) {
                    if (this.isStrict) {
                        this.strictError();
                    }
                    return true;
                }
                if (this.isStrict) {
                    this.strictError();
                }
                while (!this.isEOS()) {
                    value = this.next();
                    if (this.isDigit(value)) {
                        this.addToResult(value);
                        break;
                    }
                    if (value != 95) {
                        return true;
                    }
                }
            }
            while (!this.isEOS()) {
                value = this.next();
                if (this.isDigit(value)) {
                    this.addToResult(value);
                }
                else {
                    if (this.isExponent(value)) {
                        this.addToResult(value);
                        return this.parseExponent();
                    }
                    if (value == 95) {
                        this.verifyNumberAfterUnderscore();
                    }
                    else {
                        if (this.isWhitespace(value)) {
                            return this.skipWhitespace();
                        }
                        if (this.isStrict) {
                            this.strictError();
                        }
                        return true;
                    }
                }
            }
            return true;
        }
        
        private boolean parseExponent() {
            if (this.eatUnderscores()) {
                return this.isEOS();
            }
            byte value = this.next();
            if (value == 45 || value == 43) {
                this.addToResult(value);
            }
            else {
                this.previous();
            }
            while (!this.isEOS()) {
                value = this.next();
                if (this.isDigit(value)) {
                    this.addToResult(value);
                }
                else {
                    if (this.isWhitespace(value)) {
                        return this.skipWhitespace();
                    }
                    if (value != 95) {
                        if (this.isStrict) {
                            this.strictError();
                        }
                        return true;
                    }
                    this.verifyNumberAfterUnderscore();
                }
            }
            return true;
        }
        
        private void verifyNumberAfterUnderscore() {
            if (this.isStrict) {
                if (this.isEOS()) {
                    this.strictError();
                }
                final byte value = this.next();
                if (!this.isDigit(value)) {
                    this.previous();
                    this.strictError();
                }
                else {
                    this.addToResult(value);
                }
            }
        }
    }
}
