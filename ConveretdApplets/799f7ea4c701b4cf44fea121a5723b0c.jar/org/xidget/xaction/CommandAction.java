// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import java.util.Iterator;
import java.util.Date;
import org.xmodel.Xlate;
import java.util.Collections;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.diff.RegularChangeSet;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.ModelListener;
import org.xmodel.IModelObject;
import java.text.DateFormat;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.listeners.UndoListener;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelListener;
import org.xmodel.external.NonSyncingListener;
import org.xmodel.xaction.GuardedAction;

public class CommandAction extends GuardedAction
{
    private NonSyncingListener deepListener;
    private IModelListener shallowListener;
    private static int count;
    private IExpression summaryExpr;
    private IExpression historyExpr;
    private IExpression listenDeepExpr;
    private IExpression listenShallowExpr;
    private UndoListener undoListener;
    private ScriptAction doScript;
    private ScriptAction undoScript;
    private ScriptAction redoScript;
    private DateFormat dateFormat;
    
    static {
        CommandAction.count = 0;
    }
    
    public CommandAction() {
        this.deepListener = new NonSyncingListener() {
            @Override
            public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                super.notifyAddChild(modelObject, modelObject2, n);
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyAddChild(modelObject, modelObject2, n);
                }
            }
            
            @Override
            public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                super.notifyRemoveChild(modelObject, modelObject2, n);
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyRemoveChild(modelObject, modelObject2, n);
                }
            }
            
            @Override
            public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyChange(modelObject, s, o, o2);
                }
            }
            
            @Override
            public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyClear(modelObject, s, o);
                }
            }
        };
        this.shallowListener = new ModelListener() {
            @Override
            public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyAddChild(modelObject, modelObject2, n);
                }
            }
            
            @Override
            public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyRemoveChild(modelObject, modelObject2, n);
                }
            }
            
            @Override
            public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyChange(modelObject, s, o, o2);
                }
            }
            
            @Override
            public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
                if (CommandAction.this.undoListener != null) {
                    CommandAction.this.undoListener.notifyClear(modelObject, s, o);
                }
            }
        };
        this.dateFormat = DateFormat.getDateTimeInstance();
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.summaryExpr = xActionDocument.getExpression("summary", true);
        this.historyExpr = xActionDocument.getExpression("history", true);
        this.listenDeepExpr = xActionDocument.getExpression("listenDeep", true);
        this.listenShallowExpr = xActionDocument.getExpression("listenShallow", true);
        this.doScript = xActionDocument.createChildScript("do", new String[0]);
        this.undoScript = xActionDocument.createChildScript("undo", new String[0]);
        this.redoScript = xActionDocument.createChildScript("redo", new String[0]);
        if (this.listenDeepExpr == null && this.listenShallowExpr == null && this.redoScript == null) {
            this.redoScript = this.doScript;
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.historyExpr.queryFirst(context);
        if (queryFirst == null) {
            return this.doScript.run(context);
        }
        final Command command = new Command();
        Command.access$0(command, new StatefulContext(context));
        this.copyVariables(context, command.redoContext);
        final Object[] array = null;
        Object[] array2;
        if (this.listenShallowExpr != null || this.listenDeepExpr != null) {
            final RegularChangeSet set = new RegularChangeSet();
            final RegularChangeSet set2 = new RegularChangeSet();
            command.setChangeSets(set, set2);
            this.undoListener = new UndoListener(set, set2);
            final List<IModelObject> list = (this.listenDeepExpr != null) ? this.listenDeepExpr.query(context, null) : Collections.emptyList();
            final List<IModelObject> list2 = (this.listenShallowExpr != null) ? this.listenShallowExpr.query(context, null) : Collections.emptyList();
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.deepListener.install(iterator.next());
            }
            final Iterator<IModelObject> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().addModelListener(this.shallowListener);
            }
            array2 = this.doScript.run(context);
            final Iterator<IModelObject> iterator3 = list.iterator();
            while (iterator3.hasNext()) {
                this.deepListener.uninstall(iterator3.next());
            }
            final Iterator<IModelObject> iterator4 = list2.iterator();
            while (iterator4.hasNext()) {
                iterator4.next().removeModelListener(this.shallowListener);
            }
            this.undoListener = null;
        }
        else {
            array2 = this.doScript.run(context);
        }
        Command.access$2(command, new StatefulContext(context));
        this.copyVariables(context, command.undoContext);
        if (command.undoSet == null || command.undoSet.getSize() > 0) {
            final int value = Xlate.get(queryFirst, "index", 0);
            Xlate.set(queryFirst, "index", value + 1);
            for (int numberOfChildren = queryFirst.getNumberOfChildren(), i = value; i < numberOfChildren; ++i) {
                queryFirst.removeChild(value);
            }
            final IModelObject cloneTree = this.getDocument().getRoot().cloneTree();
            cloneTree.setAttribute("summary", this.summaryExpr.evaluateString(context));
            cloneTree.setID(new StringBuilder().append(CommandAction.count++).toString());
            cloneTree.setAttribute("instance", command);
            cloneTree.setAttribute("time", this.dateFormat.format(new Date()));
            queryFirst.addChild(cloneTree);
        }
        return array2;
    }
    
    private void copyVariables(final IContext context, final IContext context2) {
        context2.getScope().copyFrom(context.getScope());
    }
    
    public class Command
    {
        private IContext redoContext;
        private IContext undoContext;
        private IChangeSet undoSet;
        private IChangeSet redoSet;
        
        public void setChangeSets(final IChangeSet undoSet, final IChangeSet redoSet) {
            this.undoSet = undoSet;
            this.redoSet = redoSet;
        }
        
        public boolean canUndo() {
            return CommandAction.this.undoScript != null || this.undoSet != null;
        }
        
        public void undo() {
            if (this.undoSet != null) {
                this.undoSet.applyChanges();
            }
            if (CommandAction.this.undoScript != null) {
                CommandAction.this.undoScript.run(this.undoContext);
            }
        }
        
        public void redo() {
            if (this.redoSet != null) {
                this.redoSet.applyChanges();
            }
            if (CommandAction.this.redoScript != null) {
                CommandAction.this.redoScript.run(this.redoContext);
            }
        }
        
        static /* synthetic */ void access$0(final Command command, final IContext redoContext) {
            command.redoContext = redoContext;
        }
        
        static /* synthetic */ void access$2(final Command command, final IContext undoContext) {
            command.undoContext = undoContext;
        }
    }
}
