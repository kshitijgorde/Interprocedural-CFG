// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import org.apache.log4j.Logger;
import javax.swing.AbstractAction;

class ExitAction extends AbstractAction
{
    private static final Logger LOG;
    public static final ExitAction INSTANCE;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$ExitAction;
    
    public void actionPerformed(final ActionEvent aIgnore) {
        ExitAction.LOG.info("shutting down");
        System.exit(0);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        LOG = Logger.getLogger((ExitAction.class$org$apache$log4j$chainsaw$ExitAction == null) ? (ExitAction.class$org$apache$log4j$chainsaw$ExitAction = class$("org.apache.log4j.chainsaw.ExitAction")) : ExitAction.class$org$apache$log4j$chainsaw$ExitAction);
        INSTANCE = new ExitAction();
    }
}
