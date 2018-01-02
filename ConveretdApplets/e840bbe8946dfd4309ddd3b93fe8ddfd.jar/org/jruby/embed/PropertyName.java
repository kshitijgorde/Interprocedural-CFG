// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

public enum PropertyName
{
    CLASSPATH("org.jruby.embed.class.path"), 
    LOCALCONTEXT_SCOPE("org.jruby.embed.localcontext.scope"), 
    LOCALVARIABLE_BEHAVIOR("org.jruby.embed.localvariable.behavior"), 
    LAZINESS("org.jruby.embed.laziness"), 
    CLASSLOADER("org.jruby.embed.classloader"), 
    COMPILEMODE("org.jruby.embed.compilemode"), 
    COMPATVERSION("org.jruby.embed.compat.version");
    
    private final String fqpn;
    
    private PropertyName(final String fqpn) {
        this.fqpn = fqpn;
    }
    
    public String toString() {
        return this.fqpn;
    }
    
    public static PropertyName getType(final String fqpn) {
        final PropertyName[] names = values();
        for (int i = 0; i < names.length; ++i) {
            if (fqpn.equals(names[i].toString())) {
                return names[i];
            }
        }
        return null;
    }
}
