// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import javax.naming.NamingException;
import javax.naming.CompoundName;
import javax.naming.Name;
import java.util.Properties;
import java.io.Serializable;
import javax.naming.NameParser;

public class NamingParser implements NameParser, Serializable
{
    private static final long serialVersionUID = 2925203703371001031L;
    static Properties syntax;
    
    public static Properties getSyntax() {
        return NamingParser.syntax;
    }
    
    public Name parse(final String name) throws NamingException {
        return new CompoundName(name, NamingParser.syntax);
    }
    
    static {
        NamingParser.syntax = new FastNamingProperties();
    }
}
