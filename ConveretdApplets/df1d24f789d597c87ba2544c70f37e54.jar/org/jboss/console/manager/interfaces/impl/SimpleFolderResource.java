// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.manager.interfaces.ManageableResource;

public class SimpleFolderResource implements ManageableResource
{
    protected String folderName;
    
    public SimpleFolderResource(final String folderName) {
        this.folderName = null;
        this.folderName = folderName;
    }
    
    public String getId() {
        return this.folderName;
    }
}
