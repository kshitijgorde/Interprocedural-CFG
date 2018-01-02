// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.io.InputStream;

public class MELoader implements Loader
{
    String codebase;
    
    public MELoader() {
    }
    
    public MELoader(final String codebase) {
        this.codebase = codebase;
    }
    
    public InputStream getResourceStream(final String s) {
        try {
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
