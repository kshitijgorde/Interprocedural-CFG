// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.manager.interfaces.TreeAction;

public class HttpLinkTreeAction implements TreeAction
{
    protected String target;
    protected String frame;
    
    public HttpLinkTreeAction() {
        this.target = null;
        this.frame = null;
    }
    
    public HttpLinkTreeAction(final String target) {
        this.target = null;
        this.frame = null;
        this.target = target;
    }
    
    public HttpLinkTreeAction(final String target, final String frame) {
        this.target = null;
        this.frame = null;
        this.target = target;
        this.frame = frame;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public String getFrame() {
        return this.frame;
    }
}
