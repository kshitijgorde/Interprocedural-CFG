// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jcodings.Encoding;

public enum KCode
{
    NIL((String)null, "ASCII", 0), 
    NONE("NONE", "ASCII", 0), 
    UTF8("UTF8", "NonStrictUTF8", 64), 
    SJIS("SJIS", "NonStrictSJIS", 48), 
    EUC("EUC", "NonStrictEUCJP", 32);
    
    private final String kcode;
    private final String encodingName;
    private final int code;
    private volatile Encoding encoding;
    
    private KCode(final String kcode, final String encodingName, final int code) {
        this.kcode = kcode;
        this.encodingName = encodingName;
        this.code = code;
    }
    
    public static KCode create(final Ruby runtime, final String lang) {
        if (lang == null) {
            return KCode.NIL;
        }
        if (lang.length() == 0) {
            return KCode.NONE;
        }
        switch (lang.charAt(0)) {
            case 'E':
            case 'e': {
                return KCode.EUC;
            }
            case 'S':
            case 's': {
                return KCode.SJIS;
            }
            case 'U':
            case 'u': {
                return KCode.UTF8;
            }
            case 'A':
            case 'N':
            case 'a':
            case 'n': {
                return KCode.NONE;
            }
            default: {
                return KCode.NIL;
            }
        }
    }
    
    public IRubyObject kcode(final Ruby runtime) {
        return (this.kcode == null) ? runtime.getNil() : runtime.newString(this.kcode);
    }
    
    public String getKCode() {
        return this.kcode;
    }
    
    public int bits() {
        return this.code;
    }
    
    public static KCode fromBits(final int bits) {
        if ((bits & 0x40) != 0x0) {
            return KCode.UTF8;
        }
        if ((bits & 0x30) == 0x30) {
            return KCode.SJIS;
        }
        if ((bits & 0x20) != 0x0) {
            return KCode.EUC;
        }
        return KCode.NONE;
    }
    
    public Encoding getEncoding() {
        if (this.encoding == null) {
            this.encoding = Encoding.load(this.encodingName);
        }
        return this.encoding;
    }
    
    public String toString() {
        return this.name();
    }
}
