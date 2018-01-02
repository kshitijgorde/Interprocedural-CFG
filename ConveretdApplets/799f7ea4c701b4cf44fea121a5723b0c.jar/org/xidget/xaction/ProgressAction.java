// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import java.util.Iterator;
import org.xmodel.xaction.debug.IDebugger;
import org.xmodel.xaction.XActionException;
import org.xidget.Creator;
import org.xidget.ifeature.IAsyncFeature;
import org.xmodel.xaction.IXAction;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.ScriptAction;

public class ProgressAction extends ScriptAction
{
    @Override
    protected Object[] doAction(final IContext context) {
        final ThreadRunnable threadRunnable = new ThreadRunnable(context);
        final Thread thread = new Thread(threadRunnable, "Worker");
        thread.setDaemon(false);
        thread.start();
        return threadRunnable.result;
    }
    
    private class ActionRunnable implements Runnable
    {
        private IContext context;
        private IXAction action;
        public Object[] result;
        
        public ActionRunnable(final IContext context, final IXAction action) {
            this.context = context;
            this.action = action;
        }
        
        public Object[] invokeAndWait() {
            try {
                this.result = null;
                Creator.getToolkit().getFeature(IAsyncFeature.class).runWait(this);
                return this.result;
            }
            catch (Exception ex) {
                throw new XActionException(ex);
            }
        }
        
        @Override
        public void run() {
            if (!ProgressAction.debugging) {
                this.result = this.action.run(this.context);
            }
            else {
                this.result = ProgressAction.debuggers.get().run(this.context, this.action);
            }
        }
    }
    
    private class ThreadRunnable implements Runnable
    {
        private IContext context;
        public Object[] result;
        
        public ThreadRunnable(final IContext context) {
            this.context = context;
        }
        
        @Override
        public void run() {
            if (!ProgressAction.debugging) {
                final Iterator<IXAction> iterator = ProgressAction.this.getActions().iterator();
                while (iterator.hasNext()) {
                    this.result = new ActionRunnable(this.context, iterator.next()).invokeAndWait();
                    if (this.result != null) {
                        return;
                    }
                }
            }
            else {
                final IDebugger debugger = ProgressAction.debuggers.get();
                try {
                    debugger.push(this.context, ProgressAction.this);
                    final Iterator<IXAction> iterator2 = ProgressAction.this.getActions().iterator();
                    while (iterator2.hasNext()) {
                        this.result = new ActionRunnable(this.context, iterator2.next()).invokeAndWait();
                        if (this.result != null) {
                            return;
                        }
                    }
                }
                finally {
                    debugger.pop();
                }
                debugger.pop();
            }
        }
    }
}
