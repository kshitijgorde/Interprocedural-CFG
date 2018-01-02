// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

import sun.misc.ServiceConfigurationError;
import java.util.Iterator;
import sun.misc.Service;

final class Verifiers
{
    private static Verifier v;
    static /* synthetic */ Class class$com$eaio$nativecall$Verifier;
    
    static final void init() throws ServiceConfigurationError, SecurityException {
        Class class$com$eaio$nativecall$Verifier;
        if ((class$com$eaio$nativecall$Verifier = Verifiers.class$com$eaio$nativecall$Verifier) == null) {
            class$com$eaio$nativecall$Verifier = (Verifiers.class$com$eaio$nativecall$Verifier = class("[Lcom.eaio.nativecall.Verifier;", false));
        }
        Class class$com$eaio$nativecall$Verifier2;
        if ((class$com$eaio$nativecall$Verifier2 = Verifiers.class$com$eaio$nativecall$Verifier) == null) {
            class$com$eaio$nativecall$Verifier2 = (Verifiers.class$com$eaio$nativecall$Verifier = class("[Lcom.eaio.nativecall.Verifier;", false));
        }
        final Iterator i = Service.providers((Class<Object>)class$com$eaio$nativecall$Verifier, class$com$eaio$nativecall$Verifier2.getClassLoader());
        Verifier ver = null;
        while (i.hasNext()) {
            ver = i.next();
            if (ver.supports()) {
                Verifiers.v = ver;
                break;
            }
        }
    }
    
    static final Verifier getInstance() {
        return Verifiers.v;
    }
    
    static /* synthetic */ Class class(final String s, final boolean b) {
        try {
            final Class<?> forName = Class.forName(s);
            if (!b) {
                forName.getComponentType();
            }
            return forName;
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Verifiers.v = null;
    }
}
