// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

public enum AttributeName
{
    READER("org.jruby.embed.reader"), 
    WRITER("org.jruby.embed.writer"), 
    ERROR_WRITER("org.jruby.embed.errorwriter"), 
    BASE_DIR("org.jruby.embed.basedir"), 
    LINENUMBER("org.jruby.embed.linenumber"), 
    UNICODE_ESCAPE("org.jruby.embed.unicode.escpe"), 
    SHARING_VARIABLES("org.jruby.embed.sharing.variables"), 
    TERMINATION("org.jruby.embed.termination"), 
    RECEIVER("org.jruby.embed.receiver");
    
    private final String fqpn;
    
    private AttributeName(final String fqpn) {
        this.fqpn = fqpn;
    }
    
    public String toString() {
        return this.fqpn;
    }
    
    public static AttributeName getType(final String fqpn) {
        final AttributeName[] names = values();
        for (int i = 0; i < names.length; ++i) {
            if (fqpn.equals(names[i].toString())) {
                return names[i];
            }
        }
        return null;
    }
}
