// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jcodings.specific.UTF8Encoding;
import org.jcodings.specific.EUCJPEncoding;
import org.jcodings.specific.ASCIIEncoding;
import org.jcodings.Encoding;
import org.jruby.Ruby;

public class RegexpOptions implements Cloneable
{
    private static ByteList WINDOWS31J;
    public static final RegexpOptions NULL_OPTIONS;
    private KCode kcode;
    private boolean fixed;
    private boolean once;
    private boolean extended;
    private boolean multiline;
    private boolean ignorecase;
    private boolean java;
    private boolean encodingNone;
    private boolean kcodeDefault;
    private boolean literal;
    
    public RegexpOptions() {
        this(KCode.NONE, true);
    }
    
    public RegexpOptions(final KCode kcode, final boolean isKCodeDefault) {
        this.kcode = kcode;
        this.kcodeDefault = isKCodeDefault;
        assert kcode != null : "kcode must always be set to something";
    }
    
    public boolean isExtended() {
        return this.extended;
    }
    
    public void setExtended(final boolean extended) {
        this.extended = extended;
    }
    
    public boolean isIgnorecase() {
        return this.ignorecase;
    }
    
    public void setIgnorecase(final boolean ignorecase) {
        this.ignorecase = ignorecase;
    }
    
    public boolean isFixed() {
        return this.fixed;
    }
    
    public void setFixed(final boolean fixed) {
        this.fixed = fixed;
    }
    
    public KCode getKCode() {
        return this.kcode;
    }
    
    public String getKCodeName() {
        return this.isKcodeDefault() ? null : this.getKCode().name().toLowerCase();
    }
    
    public void setExplicitKCode(final KCode kcode) {
        this.kcode = kcode;
        this.kcodeDefault = false;
    }
    
    private KCode getExplicitKCode() {
        if (this.kcodeDefault) {
            return null;
        }
        return this.kcode;
    }
    
    public boolean isKcodeDefault() {
        return this.kcodeDefault;
    }
    
    public boolean isMultiline() {
        return this.multiline;
    }
    
    public void setMultiline(final boolean multiline) {
        this.multiline = multiline;
    }
    
    public boolean isOnce() {
        return this.once;
    }
    
    public void setOnce(final boolean once) {
        this.once = once;
    }
    
    public boolean isJava() {
        return this.java;
    }
    
    public void setJava(final boolean java) {
        this.java = java;
    }
    
    public boolean isEncodingNone() {
        return this.encodingNone;
    }
    
    public void setEncodingNone(final boolean encodingNone) {
        this.encodingNone = encodingNone;
    }
    
    public boolean isLiteral() {
        return this.literal;
    }
    
    public void setLiteral(final boolean literal) {
        this.literal = literal;
    }
    
    public boolean isEmbeddable() {
        return this.multiline && this.ignorecase && this.extended;
    }
    
    public Encoding setup19(final Ruby runtime) {
        final KCode explicitKCode = this.getExplicitKCode();
        if (explicitKCode == KCode.NONE) {
            this.setEncodingNone(true);
            return ASCIIEncoding.INSTANCE;
        }
        if (explicitKCode == KCode.EUC) {
            this.setFixed(true);
            return EUCJPEncoding.INSTANCE;
        }
        if (explicitKCode == KCode.SJIS) {
            this.setFixed(true);
            return runtime.getEncodingService().loadEncoding(RegexpOptions.WINDOWS31J);
        }
        if (explicitKCode == KCode.UTF8) {
            this.setFixed(true);
            return UTF8Encoding.INSTANCE;
        }
        return null;
    }
    
    public int toEmbeddedOptions() {
        int options = this.toJoniOptions();
        if (this.once) {
            options |= 0x80;
        }
        if (this.literal) {
            options |= 0x100;
        }
        if (this.kcodeDefault) {
            options |= 0x200;
        }
        if (this.fixed) {
            options |= 0x400;
        }
        return options;
    }
    
    public int toJoniOptions() {
        int options = 0;
        if (this.multiline) {
            options |= 0x4;
        }
        if (this.ignorecase) {
            options |= 0x1;
        }
        if (this.extended) {
            options |= 0x2;
        }
        if (!this.isKcodeDefault()) {
            options |= this.kcode.bits();
        }
        return options;
    }
    
    public static RegexpOptions fromEmbeddedOptions(final int embeddedOptions) {
        final RegexpOptions options = fromJoniOptions(embeddedOptions);
        options.kcodeDefault = ((embeddedOptions & 0x200) != 0x0);
        options.setOnce((embeddedOptions & 0x80) != 0x0);
        options.setLiteral((embeddedOptions & 0x100) != 0x0);
        options.setFixed((embeddedOptions & 0x400) != 0x0);
        return options;
    }
    
    public static RegexpOptions fromJoniOptions(final int joniOptions) {
        final KCode kcode = KCode.fromBits(joniOptions);
        final RegexpOptions options = new RegexpOptions(kcode, kcode == KCode.NONE);
        options.setMultiline((joniOptions & 0x4) != 0x0);
        options.setIgnorecase((joniOptions & 0x1) != 0x0);
        options.setExtended((joniOptions & 0x2) != 0x0);
        options.setOnce((joniOptions & 0x80) != 0x0);
        return options;
    }
    
    public RegexpOptions withoutOnce() {
        final RegexpOptions options = (RegexpOptions)this.clone();
        options.setOnce(false);
        return options;
    }
    
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + ((this.kcode != null) ? this.kcode.hashCode() : 0);
        hash = 11 * hash + (this.fixed ? 1 : 0);
        hash = 11 * hash + (this.once ? 1 : 0);
        hash = 11 * hash + (this.extended ? 1 : 0);
        hash = 11 * hash + (this.multiline ? 1 : 0);
        hash = 11 * hash + (this.ignorecase ? 1 : 0);
        hash = 11 * hash + (this.java ? 1 : 0);
        hash = 11 * hash + (this.encodingNone ? 1 : 0);
        hash = 11 * hash + (this.kcodeDefault ? 1 : 0);
        hash = 11 * hash + (this.literal ? 1 : 0);
        return hash;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw new RuntimeException(cnse);
        }
    }
    
    public boolean equals(final Object other) {
        if (!(other instanceof RegexpOptions)) {
            return false;
        }
        final RegexpOptions o = (RegexpOptions)other;
        return o.encodingNone == this.encodingNone && o.extended == this.extended && o.fixed == this.fixed && o.ignorecase == this.ignorecase && o.java == this.java && o.kcode == this.kcode && o.kcodeDefault == this.kcodeDefault && o.multiline == this.multiline;
    }
    
    public String toString() {
        return "RegexpOptions(kcode: " + this.kcode + (this.encodingNone ? ", encodingNone" : "") + (this.extended ? ", extended" : "") + (this.fixed ? ", fixed" : "") + (this.ignorecase ? ", ignorecase" : "") + (this.java ? ", java" : "") + (this.kcodeDefault ? ", kcodeDefault" : "") + (this.literal ? ", literal" : "") + (this.multiline ? ", multiline" : "") + (this.once ? ", once" : "") + ")";
    }
    
    static {
        RegexpOptions.WINDOWS31J = new ByteList(new byte[] { 87, 105, 110, 100, 111, 119, 115, 45, 51, 49, 74 });
        NULL_OPTIONS = new RegexpOptions(KCode.NONE, true);
    }
}
