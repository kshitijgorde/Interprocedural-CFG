// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.text.Format;

public class BooleanFormat extends Format
{
    static int TRUESTR;
    static int FALSESTR;
    static int NULLSTR;
    private String pattern;
    private String[] strings;
    
    public BooleanFormat() {
        this.strings = new String[3];
        this.applyPattern(null);
    }
    
    public BooleanFormat(final String pattern) {
        this();
        this.applyPattern(pattern);
    }
    
    public void applyPattern(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            pattern = "true;false";
        }
        this.pattern = pattern;
        this.decomposePattern();
    }
    
    public String toPattern() {
        return (this.pattern == null) ? null : this.pattern;
    }
    
    private void decomposePattern() {
        final String[] strings = this.strings;
        final int n = 0;
        final String[] strings2 = this.strings;
        final int n2 = 1;
        final String[] strings3 = this.strings;
        final int n3 = 2;
        final String s = "";
        strings3[n3] = s;
        strings[n] = (strings2[n2] = s);
        if (this.pattern == null || this.pattern.length() == 0) {
            return;
        }
        String workStr = this.pattern;
        for (int i = 0; i < this.strings.length; ++i) {
            int index = workStr.indexOf(";");
            if (index < 0) {
                index = workStr.length();
            }
            if (index > 0) {
                this.strings[i] = workStr.substring(0, index).trim();
            }
            if (index >= workStr.length()) {
                break;
            }
            workStr = workStr.substring(index + 1, workStr.length());
        }
    }
    
    public StringBuffer format(final Boolean value, StringBuffer result, final FieldPosition pos) {
        final int i = (value == null) ? BooleanFormat.NULLSTR : (value ? BooleanFormat.TRUESTR : BooleanFormat.FALSESTR);
        if (result == null) {
            result = new StringBuffer();
        }
        result.append(this.strings[i]);
        return result;
    }
    
    public Boolean parse(final String text, final ParsePosition pos) {
        if (text == null) {
            return null;
        }
        final int posIndex = pos.getIndex();
        String s = text.substring(posIndex);
        int textLen = s.length();
        pos.setIndex(posIndex + textLen);
        if (textLen == 0) {
            return null;
        }
        final char[] chars = new char[textLen];
        s.getChars(0, textLen, chars, 0);
        for (int i = 0; i < textLen; ++i) {
            if (chars[i] == '_') {
                chars[i] = ' ';
            }
        }
        s = new String(chars);
        s = s.trim();
        textLen = s.length();
        if (textLen == 0) {
            return null;
        }
        if (s.equalsIgnoreCase(this.strings[BooleanFormat.TRUESTR])) {
            return new Boolean(true);
        }
        if (s.equalsIgnoreCase(this.strings[BooleanFormat.FALSESTR])) {
            return new Boolean(false);
        }
        if (s.equalsIgnoreCase(this.strings[BooleanFormat.NULLSTR])) {
            return null;
        }
        String abbrevTrue = "";
        String abbrevFalse = "";
        String abbrevNull = "";
        if (textLen <= this.strings[BooleanFormat.TRUESTR].length()) {
            abbrevTrue = this.strings[BooleanFormat.TRUESTR].substring(0, s.length());
        }
        if (textLen <= this.strings[BooleanFormat.FALSESTR].length()) {
            abbrevFalse = this.strings[BooleanFormat.FALSESTR].substring(0, s.length());
        }
        if (textLen <= this.strings[BooleanFormat.NULLSTR].length()) {
            abbrevNull = this.strings[BooleanFormat.NULLSTR].substring(0, s.length());
        }
        final boolean isTrue = s.equalsIgnoreCase(abbrevTrue);
        final boolean isFalse = s.equalsIgnoreCase(abbrevFalse);
        final boolean isNull = s.equalsIgnoreCase(abbrevNull);
        if (isTrue && !isFalse && !isNull) {
            return new Boolean(true);
        }
        if (isFalse && !isTrue && !isNull) {
            return new Boolean(false);
        }
        if (isNull && !isTrue && !isFalse) {
            return null;
        }
        pos.setIndex(posIndex);
        return null;
    }
    
    public final Object parseObject(final String source, final ParsePosition pos) {
        return this.parse(source, pos);
    }
    
    public final StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        return this.format(obj.toString(), toAppendTo, pos);
    }
    
    public String getTrueString() {
        return this.strings[BooleanFormat.TRUESTR];
    }
    
    public String getFalseString() {
        return this.strings[BooleanFormat.FALSESTR];
    }
    
    public String getNullString() {
        return this.strings[BooleanFormat.NULLSTR];
    }
    
    static {
        BooleanFormat.TRUESTR = 0;
        BooleanFormat.FALSESTR = 1;
        BooleanFormat.NULLSTR = 2;
    }
}
