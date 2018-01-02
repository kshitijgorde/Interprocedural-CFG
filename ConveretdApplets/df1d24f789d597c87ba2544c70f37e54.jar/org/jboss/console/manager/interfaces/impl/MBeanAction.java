// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.navtree.AppletBrowser;
import org.jboss.console.navtree.TreeContext;
import javax.management.ObjectName;
import org.jboss.console.navtree.AppletTreeAction;

public class MBeanAction implements AppletTreeAction
{
    protected ObjectName targetObjectName;
    protected String actionName;
    protected Object[] params;
    protected String[] signature;
    
    public MBeanAction() {
        this.targetObjectName = null;
        this.actionName = null;
        this.params = null;
        this.signature = null;
    }
    
    public MBeanAction(final ObjectName pName, final String pActionName, final Object[] pParams, final String[] pSignature) {
        this.targetObjectName = null;
        this.actionName = null;
        this.params = null;
        this.signature = null;
        this.targetObjectName = pName;
        this.actionName = pActionName;
        this.params = pParams;
        this.signature = pSignature;
    }
    
    public void doAction(final TreeContext tc, final AppletBrowser applet) {
        try {
            tc.getRemoteMBeanInvoker().invoke(this.targetObjectName, this.actionName, this.params, this.signature);
        }
        catch (Exception displayed) {
            displayed.printStackTrace();
        }
    }
}
