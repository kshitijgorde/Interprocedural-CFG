// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

public class RefreshTreeAction implements AppletTreeAction
{
    protected boolean forceRefresh;
    
    public RefreshTreeAction(final boolean force) {
        this.forceRefresh = false;
        this.forceRefresh = force;
    }
    
    public void doAction(final TreeContext tc, final AppletBrowser applet) {
        applet.refreshTree(this.forceRefresh);
    }
}
