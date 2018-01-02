// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;

public final class NumberFormat
{
    private Format format;
    private int parseIndex;
    
    public NumberFormat(final String s) throws ParseException {
        this.format = this.parseFormat(s);
        if (this.format.integer != null && this.format.fraction != null && this.format.integer.round != '\0' && this.format.fraction.round != '\0') {
            throw new NumberFormatException("Illegal rounding specifier in integer formatting.");
        }
    }
    
    public String format(double n) {
        String s = "";
        if (this.format.min != Double.MIN_VALUE) {
            if (this.format.minInclusive) {
                if (n < this.format.min) {
                    s = "-";
                    n = this.format.min;
                }
            }
            else if (n <= this.format.min) {
                s = "-";
                n = this.format.min;
            }
        }
        if (this.format.max != Double.MAX_VALUE) {
            if (this.format.maxInclusive) {
                if (n > this.format.max) {
                    s = "+";
                    n = this.format.max;
                }
            }
            else if (n >= this.format.max) {
                s = "+";
                n = this.format.max;
            }
        }
        final long n2 = (long)n;
        final double n3 = n - n2;
        final StringBuilder sb = new StringBuilder();
        if (this.format.integer != null) {
            this.formatInteger(n2, sb);
        }
        if (this.format.fraction != null) {
            sb.append(this.format.fraction.separator);
            this.formatFraction(n3, sb);
        }
        sb.append(s);
        if (this.format.fraction.fill != '\0') {
            final int index = sb.indexOf(this.format.fraction.separator);
            if (index >= 0) {
                for (int i = sb.length() - index - 1; i < this.format.fraction.length; ++i) {
                    sb.append(this.format.fraction.fill);
                }
            }
        }
        return sb.toString();
    }
    
    private void formatInteger(final long n, final StringBuilder sb) {
        String s = Long.toString(n, this.format.integer.base);
        if (this.format.integer.fill != ' ' && s.charAt(0) == '-') {
            sb.append('-');
            s = s.substring(1);
        }
        if (this.format.integer.fill != '\0') {
            for (int i = s.length(); i < this.format.integer.length; ++i) {
                sb.append(this.format.integer.fill);
            }
        }
        sb.append(s);
    }
    
    private void formatFraction(final double n, final StringBuilder sb) {
        double n2 = Math.abs(n * this.format.fraction.normalizer);
        switch (this.format.fraction.round) {
            case '+': {
                n2 = Math.ceil(n2);
            }
            case '-': {
                n2 = Math.floor(n2);
                break;
            }
        }
        String s = Long.toString(Math.round(n2), this.format.fraction.base);
        if (this.format.fraction.fill != '0') {
            int n3;
            for (n3 = s.length() - 1; n3 >= 0 && s.charAt(n3) == '0'; --n3) {}
            s = s.substring(0, n3 + 1);
        }
        sb.append(s);
    }
    
    public Number parse(final String s) {
        return 0;
    }
    
    private Format parseFormat(final String s) throws ParseException {
        final Format format = new Format(null);
        format.integer = new IntegerFormat(null);
        this.parseIndex = 0;
        this.parseFill(s, format.integer);
        if (s.charAt(this.parseIndex) == '+') {
            format.integer.plusPrefix = true;
            ++this.parseIndex;
        }
        this.parseCommonFormat(s, format.integer);
        this.parseLimits(s, format);
        if (this.parseIndex >= s.length()) {
            return format;
        }
        format.fraction = new FractionFormat();
        format.fraction.base = format.integer.base;
        final char char1 = s.charAt(this.parseIndex);
        if (char1 != '.' && char1 != ',') {
            throw new ParseException(s, this.parseIndex);
        }
        format.fraction.separator = new StringBuilder().append(char1).toString();
        ++this.parseIndex;
        this.parseFill(s, format.fraction);
        this.parseCommonFormat(s, format.fraction);
        format.fraction.normalizer = Math.pow(format.fraction.base, format.fraction.length);
        this.parseLimits(s, format);
        return format;
    }
    
    private void parseFill(final String s, final CommonFormat commonFormat) {
        if (this.parseIndex >= s.length()) {
            return;
        }
        final char char1 = s.charAt(this.parseIndex);
        if (char1 == '0' || char1 == ' ') {
            commonFormat.fill = char1;
            ++this.parseIndex;
        }
    }
    
    private void parseLimits(final String s, final Format format) throws ParseException {
        if (this.parseIndex >= s.length()) {
            return;
        }
        if (s.charAt(this.parseIndex) == '<') {
            ++this.parseIndex;
            if (this.parseIndex < s.length() && s.charAt(this.parseIndex) == '=') {
                ++this.parseIndex;
                format.maxInclusive = true;
            }
            format.max = this.nextDouble(s);
        }
        if (this.parseIndex >= s.length()) {
            return;
        }
        if (s.charAt(this.parseIndex) == '>') {
            ++this.parseIndex;
            if (this.parseIndex < s.length() && s.charAt(this.parseIndex) == '=') {
                ++this.parseIndex;
                format.minInclusive = true;
            }
            format.min = this.nextDouble(s);
        }
    }
    
    private void parseCommonFormat(final String s, final CommonFormat commonFormat) throws ParseException {
        if (s.charAt(this.parseIndex) == '*') {
            commonFormat.length = 0;
            ++this.parseIndex;
        }
        else {
            commonFormat.length = this.nextInteger(s);
        }
        if (this.parseIndex >= s.length()) {
            return;
        }
        final char char1 = s.charAt(this.parseIndex);
        if (Character.isLetter(char1)) {
            commonFormat.base = this.parseBase(char1);
            if (commonFormat.base < 0 && this.parseStyle(char1) == null) {
                throw new ParseException(s, this.parseIndex);
            }
            ++this.parseIndex;
        }
        if (this.parseIndex >= s.length()) {
            return;
        }
        final char char2 = s.charAt(this.parseIndex);
        if (char2 == '+' || char2 == '-') {
            commonFormat.round = char2;
            ++this.parseIndex;
        }
        if (this.parseIndex >= s.length()) {
            return;
        }
        commonFormat.style = this.parseStyle(s.charAt(this.parseIndex));
        if (commonFormat.style != null) {
            ++this.parseIndex;
        }
    }
    
    private int nextInteger(final String s) throws ParseException {
        try {
            final int parseIndex = this.parseIndex;
            while (this.parseIndex < s.length() && Character.isDigit(s.charAt(this.parseIndex))) {
                ++this.parseIndex;
            }
            return Integer.parseInt(s.substring(parseIndex, this.parseIndex));
        }
        catch (NumberFormatException ex) {
            throw new ParseException(s, this.parseIndex);
        }
    }
    
    private double nextDouble(final String s) throws ParseException {
        try {
            final int parseIndex = this.parseIndex;
            while (this.parseIndex < s.length()) {
                final char char1 = s.charAt(this.parseIndex);
                if (char1 != '-' && char1 != '.' && !Character.isDigit(char1)) {
                    break;
                }
                ++this.parseIndex;
            }
            return Double.parseDouble(s.substring(parseIndex, this.parseIndex));
        }
        catch (NumberFormatException ex) {
            throw new ParseException(s, this.parseIndex);
        }
    }
    
    private int parseBase(final char c) {
        switch (c) {
            case 'b': {
                return 2;
            }
            case 'o': {
                return 8;
            }
            case 'x': {
                return 16;
            }
            case 'Z': {
                return 36;
            }
            default: {
                return -1;
            }
        }
    }
    
    private Style parseStyle(final char c) {
        switch (c) {
            case 'e': {
                return Style.engineering;
            }
            case 'g': {
                return Style.scientific;
            }
            default: {
                return null;
            }
        }
    }
    
    public static void main(final String[] array) throws Exception {
        final NumberFormat numberFormat = new NumberFormat(" 4. 2");
        double[] array2;
        for (int length = (array2 = new double[] { -12.34, 12.34, 1.2, 12.3, 1.23 }).length, i = 0; i < length; ++i) {
            final double n = array2[i];
            System.out.printf("|%s| %f\n", numberFormat.format(n), n);
        }
    }
    
    private static class CommonFormat
    {
        public char fill;
        public int length;
        public int base;
        public char round;
        public Style style;
        
        private CommonFormat() {
            this.base = 10;
            this.round = '\0';
            this.style = Style.floating;
        }
    }
    
    private static final class Format
    {
        public IntegerFormat integer;
        public FractionFormat fraction;
        public double min;
        public double max;
        public boolean minInclusive;
        public boolean maxInclusive;
        
        private Format() {
            this.min = Double.MIN_VALUE;
            this.max = Double.MAX_VALUE;
        }
    }
    
    public static final class FractionFormat extends CommonFormat
    {
        public String separator;
        public double normalizer;
        
        public FractionFormat() {
            super(null);
        }
    }
    
    private static final class IntegerFormat extends CommonFormat
    {
        public boolean plusPrefix;
        
        private IntegerFormat() {
            super(null);
        }
    }
    
    public enum Style
    {
        floating("floating", 0), 
        scientific("scientific", 1), 
        engineering("engineering", 2);
        
        private Style(final String s, final int n) {
        }
    }
}
