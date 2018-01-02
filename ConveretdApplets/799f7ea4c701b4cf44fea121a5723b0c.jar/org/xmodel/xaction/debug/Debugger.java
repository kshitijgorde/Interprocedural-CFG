// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.debug;

import java.util.Iterator;
import java.util.Collection;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.ExternalReference;
import org.xmodel.ModelObject;
import org.xmodel.IDispatcher;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.xaction.IXAction;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.BlockingDispatcher;
import java.util.concurrent.Semaphore;
import org.xmodel.IModelObject;
import java.util.Stack;
import org.xmodel.net.Protocol;

public class Debugger implements IDebugger
{
    public static final int defaultPort = 27700;
    private Protocol G;
    private Stack<Frame> B;
    private int C;
    private int D;
    private IModelObject A;
    private Semaphore F;
    private BlockingDispatcher E;
    
    public Debugger(final Protocol g, final IContext context) {
        this.G = g;
        this.B = new Stack<Frame>();
        this.F = new Semaphore(0);
        this.C = 1;
        this.E = new BlockingDispatcher();
        this.A = XPath.createExpression("$debug").queryFirst(context);
    }
    
    @Override
    public void stepOver() {
        synchronized (this) {
            this.C = this.D;
            this.B();
        }
    }
    
    @Override
    public void stepIn() {
        synchronized (this) {
            this.C = this.D + 1;
            this.B();
        }
    }
    
    @Override
    public void stepOut() {
        synchronized (this) {
            this.C = this.D - 1;
            this.B();
        }
    }
    
    @Override
    public void push(final IContext context, final ScriptAction scriptAction) {
        synchronized (this) {
            ++this.D;
            final Frame frame = new Frame(context, scriptAction);
            this.B.push(frame);
            this.A.addChild(A(frame, null));
        }
    }
    
    @Override
    public Object[] run(final IContext context, final IXAction ixAction) {
        synchronized (this) {
            if (this.C >= this.D) {
                new XmlDiffer(new DefaultXmlMatcher(true)).diffAndApply(this.A.getChild(this.A.getNumberOfChildren() - 1), A(this.B.peek(), ixAction));
                this.A();
            }
            return ixAction.run(context);
        }
    }
    
    @Override
    public void pop() {
        synchronized (this) {
            this.B.pop();
            final int numberOfChildren = this.A.getNumberOfChildren();
            if (numberOfChildren > 0) {
                this.A.removeChild(numberOfChildren - 1);
            }
            --this.D;
        }
    }
    
    private void A() {
        this.G.setDispatcher(this.E);
        do {
            this.E.process();
        } while (!this.F.tryAcquire());
    }
    
    private void B() {
        this.F.release();
    }
    
    private static IModelObject A(final Frame frame, final IXAction ixAction) {
        final ModelObject modelObject = new ModelObject("frame", Integer.toString(frame.hashCode(), 16));
        if (ixAction != null) {
            ixAction.getDocument().getRoot().setAttribute("debug:next", "");
        }
        final IModelObject cloneTree = frame.script.getDocument().getRoot().cloneTree();
        cloneTree.setID(Integer.toString(frame.hashCode(), 16));
        modelObject.getCreateChild("script").addChild(cloneTree);
        if (ixAction != null) {
            ixAction.getDocument().getRoot().removeAttribute("debug:next");
        }
        final Collection<String> variables = frame.context.getScope().getVariables();
        final IModelObject createChild = modelObject.getCreateChild("variables");
        for (final String id : variables) {
            final ExternalReference externalReference = new ExternalReference("variable");
            externalReference.setID(id);
            externalReference.setCachingPolicy(new ContextCachingPolicy(frame.context));
            externalReference.setDirty(true);
            createChild.addChild(externalReference);
        }
        return modelObject;
    }
    
    protected static class Frame
    {
        public IContext context;
        public ScriptAction script;
        
        public Frame(final IContext context, final ScriptAction script) {
            this.context = context;
            this.script = script;
        }
    }
}
