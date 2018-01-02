// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class LoginResult
{
    private IModelObject root;
    
    public LoginResult(final boolean status, final String reason) {
        this.root = new Element("en:loginResult");
        this.root.getCreateChild("en:status").setValue(String.valueOf(status));
        if (reason != null) {
            this.root.getCreateChild("en:reason").setValue(reason);
        }
    }
    
    public LoginResult(final IModelObject e) {
        this.root = e;
    }
    
    public boolean succeeded() {
        return Xlate.get(this.root.getFirstChild("en:status"), false);
    }
    
    public String getReason() {
        return Xlate.get(this.root.getFirstChild("en:reason"), (String)null);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
}
