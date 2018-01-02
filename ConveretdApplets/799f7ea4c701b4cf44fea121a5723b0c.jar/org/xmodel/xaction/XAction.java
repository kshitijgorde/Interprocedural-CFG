// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xml.IXmlIO;
import org.xmodel.xml.XmlIO;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.diff.ConfiguredXmlMatcher;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Xlate;
import org.xmodel.ModelObjectFactory;
import org.xmodel.IModelObjectFactory;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.ModelObject;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.XPath;
import org.xmodel.xaction.debug.IDebugger;
import org.xmodel.xpath.expression.IExpression;

public abstract class XAction implements IXAction
{
    private final IExpression C;
    private final IExpression A;
    private final IExpression B;
    protected static ThreadLocal<IDebugger> debuggers;
    protected static boolean debugging;
    protected XActionDocument document;
    
    static {
        XAction.debuggers = new ThreadLocal<IDebugger>();
        XAction.debugging = false;
    }
    
    public XAction() {
        this.C = XPath.createExpression("ancestor-or-self::*/factory");
        this.A = XPath.createExpression("ancestor-or-self::*/matcher");
        this.B = XPath.createExpression("ancestor-or-self::*/classLoader");
    }
    
    @Override
    public void configure(final XActionDocument document) {
        this.document = document;
    }
    
    @Override
    public IVariableScope run() {
        final StatefulContext statefulContext = new StatefulContext(new ModelObject("root"));
        this.run(statefulContext);
        return statefulContext.getScope();
    }
    
    @Override
    public final Object[] run(final IContext context) {
        return this.doRun(context);
    }
    
    public abstract Object[] doRun(final IContext p0);
    
    @Override
    public final void setDocument(final XActionDocument document) {
        this.document = document;
    }
    
    @Override
    public final XActionDocument getDocument() {
        if (this.document == null) {
            this.document = new XActionDocument();
        }
        return this.document;
    }
    
    public static final void setDebugger(final IDebugger debugger) {
        if (debugger != null) {
            XAction.debugging = true;
            XAction.debuggers.set(debugger);
        }
        else {
            XAction.debugging = false;
            XAction.debuggers.set(null);
        }
    }
    
    public static final IDebugger getDebugger() {
        return XAction.debuggers.get();
    }
    
    protected IModelObjectFactory getFactory(final IModelObject modelObject) {
        final IModelObject queryFirst = this.C.queryFirst(modelObject);
        if (queryFirst == null) {
            return new ModelObjectFactory();
        }
        final String value = Xlate.get(queryFirst, (String)null);
        if (value == null) {
            this.getDocument().error("Class name is undefined in factory element: " + ModelAlgorithms.createIdentityPath(queryFirst));
        }
        ClassLoader classLoader = null;
        final IModelObject queryFirst2 = this.B.queryFirst(modelObject);
        if (queryFirst2 != null) {
            classLoader = (ClassLoader)queryFirst2.getValue();
        }
        if (classLoader == null) {
            classLoader = this.getClass().getClassLoader();
        }
        try {
            return (IModelObjectFactory)classLoader.loadClass(value).newInstance();
        }
        catch (Exception ex) {
            this.getDocument().error("Unable to resolve IModelObjectFactory class: " + value);
            return new ModelObjectFactory();
        }
    }
    
    protected IXmlMatcher getMatcher(final IModelObject modelObject) {
        final IModelObject queryFirst = this.A.queryFirst(modelObject);
        if (queryFirst == null) {
            return new ConfiguredXmlMatcher();
        }
        final String value = Xlate.get(queryFirst, (String)null);
        if (value == null) {
            this.getDocument().error("Class name is undefined in matcher element: " + ModelAlgorithms.createIdentityPath(queryFirst));
        }
        ClassLoader classLoader = null;
        final IModelObject queryFirst2 = this.B.queryFirst(modelObject);
        if (queryFirst2 != null) {
            classLoader = (ClassLoader)queryFirst2.getValue();
        }
        if (classLoader == null) {
            classLoader = this.getDocument().getClassLoader();
        }
        try {
            return (IXmlMatcher)classLoader.loadClass(value).newInstance();
        }
        catch (Exception ex) {
            this.getDocument().error("Unable to resolve IXmlMatcher class: " + value);
            return new DefaultXmlMatcher();
        }
    }
    
    @Override
    public String toString() {
        final XActionDocument document = this.getDocument();
        if (document != null) {
            final IModelObject root = document.getRoot();
            if (root != null) {
                final XmlIO xmlIO = new XmlIO();
                xmlIO.skipOutputPrefix("break");
                xmlIO.setOutputStyle(IXmlIO.Style.printable);
                return xmlIO.write(root);
            }
        }
        return "(no document)";
    }
}
