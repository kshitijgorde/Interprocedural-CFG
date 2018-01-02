// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.net.URL;
import java.io.InputStream;

public class SELoader implements Loader
{
    String codebase;
    
    public SELoader() {
        this.codebase = null;
    }
    
    public SELoader(final String codebase) {
        this.codebase = null;
        this.codebase = codebase;
    }
    
    public InputStream getResourceStream(final String s) {
        try {
            URL resource = this.getClass().getResource(s);
            System.out.println("URL: " + resource);
            System.out.println("Read ROM " + s);
            if (resource == null) {
                resource = new URL(this.codebase + s);
            }
            return resource.openConnection().getInputStream();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
