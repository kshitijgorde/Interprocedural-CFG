// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.loading;

import java.security.ProtectionDomain;

public interface Translator
{
    byte[] transform(final ClassLoader p0, final String p1, final Class p2, final ProtectionDomain p3, final byte[] p4) throws Exception;
    
    void unregisterClassLoader(final ClassLoader p0);
}
