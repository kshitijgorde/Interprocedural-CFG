// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xaction.debug.IDebugger;
import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScriptAction extends GuardedAction
{
    private static final String[] \u011c;
    private Set<String> \u011e;
    private List<IXAction> \u011d;
    
    static {
        \u011c = new String[] { "condition", "factory", "function", "matcher", "package", "when" };
    }
    
    public ScriptAction() {
        this.\u011e = new HashSet<String>();
        this.ignore(ScriptAction.\u011c);
    }
    
    public ScriptAction(final IModelObject modelObject) {
        this(ScriptAction.class.getClassLoader(), modelObject);
    }
    
    public ScriptAction(final IModelObject modelObject, final String... array) {
        this(ScriptAction.class.getClassLoader(), modelObject, array);
    }
    
    public ScriptAction(final ClassLoader classLoader, final IModelObject modelObject) {
        this(classLoader, modelObject, new String[0]);
    }
    
    public ScriptAction(final ClassLoader classLoader, final IModelObject root, final String... array) {
        if (root != null) {
            final XActionDocument xActionDocument = new XActionDocument(classLoader);
            xActionDocument.setRoot(root);
            this.\u011e = new HashSet<String>();
            this.ignore(ScriptAction.\u011c);
            this.ignore(array);
            this.configure(xActionDocument);
        }
    }
    
    public void ignore(final String... array) {
        for (int i = 0; i < array.length; ++i) {
            this.\u011e.add(array[i]);
        }
    }
    
    public List<IXAction> getActions() {
        if (this.\u011d == null) {
            return Collections.emptyList();
        }
        return this.\u011d;
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        IfAction ifAction = null;
        this.\u011d = new ArrayList<IXAction>();
        for (final IModelObject modelObject : xActionDocument.getRoot().getChildren()) {
            if (!this.\u011e.contains(modelObject.getType())) {
                final IXAction action = xActionDocument.getAction(modelObject);
                if (action == null) {
                    continue;
                }
                if (ifAction != null) {
                    if (action instanceof ElseAction) {
                        ((ElseAction)action).setIf(ifAction);
                    }
                    else if (action instanceof ElseifAction) {
                        ((ElseifAction)action).setIf(ifAction);
                    }
                    ifAction = null;
                }
                if (action instanceof IfAction) {
                    ifAction = (ElseifAction)action;
                }
                this.\u011d.add(action);
            }
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (!ScriptAction.debugging) {
            final Iterator<IXAction> iterator = this.\u011d.iterator();
            while (iterator.hasNext()) {
                final Object[] run = iterator.next().run(context);
                if (run != null) {
                    return run;
                }
            }
            return null;
        }
        final IDebugger debugger = ScriptAction.debuggers.get();
        try {
            debugger.push(context, this);
            final Iterator<IXAction> iterator2 = this.\u011d.iterator();
            while (iterator2.hasNext()) {
                final Object[] run2 = debugger.run(context, iterator2.next());
                if (run2 != null) {
                    return run2;
                }
            }
        }
        finally {
            debugger.pop();
        }
        debugger.pop();
        return null;
    }
}
