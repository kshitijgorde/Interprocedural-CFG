// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.xmodel.xml.XmlIO;
import org.xmodel.ModelAlgorithms;
import java.util.Collections;
import org.xmodel.IPath;
import java.util.Iterator;
import org.xmodel.Xlate;
import java.util.ArrayList;
import org.xmodel.xpath.XPath;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;

public class XActionDocument
{
    private static Log D;
    private final String G = "xm:compiled";
    private final IExpression F;
    private XActionDocument C;
    private ClassLoader A;
    private IModelObject B;
    private List<String> E;
    
    static {
        XActionDocument.D = Log.getLog("org.xmodel.xaction");
    }
    
    public XActionDocument() {
        this(null, XActionDocument.class.getClassLoader());
    }
    
    public XActionDocument(final ClassLoader classLoader) {
        this(null, classLoader);
    }
    
    public XActionDocument(final IModelObject modelObject) {
        this(modelObject, XActionDocument.class.getClassLoader());
    }
    
    public XActionDocument(final IModelObject root, final ClassLoader a) {
        this.F = XPath.createExpression("for $a in reverse( ancestor-or-self::*)return $a/package");
        this.E = new ArrayList<String>();
        this.A = a;
        if (root != null) {
            this.setRoot(root);
        }
    }
    
    public XActionDocument getRootDocument() {
        XActionDocument xActionDocument = this;
        for (XActionDocument xActionDocument2 = this.getParentDocument(); xActionDocument2 != null; xActionDocument2 = xActionDocument2.getParentDocument()) {
            xActionDocument = xActionDocument2;
        }
        return xActionDocument;
    }
    
    public XActionDocument getParentDocument() {
        return this.C;
    }
    
    public void setRoot(final IModelObject b) {
        this.B = b;
        this.E.add("org.xmodel.xaction");
        final Iterator<IModelObject> iterator = this.F.query(b, null).iterator();
        while (iterator.hasNext()) {
            this.E.add(Xlate.get((IModelObject)iterator.next(), ""));
        }
    }
    
    public IModelObject getRoot() {
        return this.B;
    }
    
    public void addPackage(final String s) {
        this.E.remove(s);
        this.E.add(0, s);
    }
    
    public void removePackage(final String s) {
        this.E.remove(s);
    }
    
    public List<String> getPackages() {
        return this.E;
    }
    
    public void setClassLoader(final ClassLoader a) {
        this.A = a;
    }
    
    public ClassLoader getClassLoader() {
        return this.A;
    }
    
    public String getString() {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return "";
        }
        return Xlate.get(root, "");
    }
    
    public String getString(final IPath path) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return "";
        }
        return Xlate.get(path.queryFirst(root), "");
    }
    
    public String getString(final String s) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return "";
        }
        return Xlate.get(root.getFirstChild(s), "");
    }
    
    public List<String> getStrings(final IPath path) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return Collections.emptyList();
        }
        final List<IModelObject> query = path.query(root, null);
        final ArrayList list = new ArrayList<String>(query.size());
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            list.add(Xlate.get((IModelObject)iterator.next(), ""));
        }
        return (List<String>)list;
    }
    
    public List<String> getStrings(final String s) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return Collections.emptyList();
        }
        final List<IModelObject> children = root.getChildren(s);
        final ArrayList list = new ArrayList<String>(children.size());
        final Iterator<IModelObject> iterator = children.iterator();
        while (iterator.hasNext()) {
            list.add(Xlate.get((IModelObject)iterator.next(), ""));
        }
        return (List<String>)list;
    }
    
    public IExpression getExpression() {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return null;
        }
        return this.getExpression(root);
    }
    
    public IExpression getExpression(final String s, final boolean b) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return null;
        }
        if (b) {
            final IExpression expression = this.getExpression(root.getAttributeNode(s));
            if (expression != null) {
                return expression;
            }
        }
        return this.getExpression(root.getFirstChild(s));
    }
    
    public IExpression getExpression(final IPath path) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return null;
        }
        return this.getExpression(path.queryFirst(root));
    }
    
    public List<IExpression> getExpressions(final IPath path) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return Collections.emptyList();
        }
        final List<IModelObject> query = path.query(root, null);
        final ArrayList list = new ArrayList<IExpression>(query.size());
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            final IExpression expression = this.getExpression(iterator.next());
            if (expression != null) {
                list.add(expression);
            }
        }
        return (List<IExpression>)list;
    }
    
    public List<IExpression> getExpressions(final String s) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return Collections.emptyList();
        }
        final List<IModelObject> children = root.getChildren(s);
        final ArrayList list = new ArrayList<IExpression>(children.size());
        final Iterator<IModelObject> iterator = children.iterator();
        while (iterator.hasNext()) {
            final IExpression expression = this.getExpression(iterator.next());
            if (expression != null) {
                list.add(expression);
            }
        }
        return (List<IExpression>)list;
    }
    
    public IExpression getExpression(final IModelObject modelObject) {
        if (modelObject == null) {
            return null;
        }
        final String trim = Xlate.get(modelObject, "").trim();
        if (trim.length() == 0) {
            return null;
        }
        IExpression expression = null;
        try {
            expression = (IExpression)modelObject.getAttribute("xm:compiled");
        }
        catch (ClassCastException ex) {}
        if (expression == null) {
            expression = XPath.createExpression(trim);
            try {
                modelObject.setAttribute("xm:compiled", expression);
            }
            catch (UnsupportedOperationException ex2) {}
        }
        return expression;
    }
    
    public ScriptAction createScript(final String... array) {
        final ScriptAction scriptAction = new ScriptAction();
        scriptAction.ignore(array);
        scriptAction.configure(this);
        return scriptAction;
    }
    
    public ScriptAction createChildScript(final String s, final String... array) {
        final IModelObject firstChild = this.B.getFirstChild(s);
        if (firstChild == null) {
            return null;
        }
        final ScriptAction scriptAction = new ScriptAction();
        scriptAction.ignore(array);
        scriptAction.configure(this.getDocument(firstChild));
        return scriptAction;
    }
    
    public ScriptAction createScript(final IModelObject modelObject, final String... array) {
        if (modelObject == null) {
            return null;
        }
        final ScriptAction scriptAction = new ScriptAction();
        scriptAction.ignore(array);
        scriptAction.configure(this.getDocument(modelObject));
        return scriptAction;
    }
    
    public IXAction getAction(final IModelObject modelObject) {
        if (modelObject == null) {
            return null;
        }
        final String value = Xlate.get(modelObject, "class", (String)null);
        if (value == null || !value.endsWith("Action")) {
            for (int i = this.E.size() - 1; i >= 0; --i) {
                final IXAction action = this.getAction(this.E.get(i), null, modelObject);
                if (action != null) {
                    return action;
                }
            }
        }
        else if (value.indexOf(46) > 0) {
            final int lastIndex = value.lastIndexOf(46);
            final IXAction action2 = this.getAction(value.substring(0, lastIndex), value.substring(lastIndex + 1), modelObject);
            if (action2 != null) {
                return action2;
            }
        }
        else {
            for (int j = this.E.size() - 1; j >= 0; --j) {
                final IXAction action3 = this.getAction(this.E.get(j), value, modelObject);
                if (action3 != null) {
                    return action3;
                }
            }
        }
        System.out.println("Unable to resolve IXAction class: " + ModelAlgorithms.createIdentityPath(modelObject));
        System.out.println(new XmlIO().write(2, modelObject));
        System.out.println("\nPackages (processed from last to first): ");
        final Iterator<String> iterator = this.E.iterator();
        while (iterator.hasNext()) {
            System.out.println("    " + iterator.next());
        }
        return null;
    }
    
    protected IXAction getAction(final String s, String s2, final IModelObject modelObject) {
        if (s2 != null && s.length() != 0) {
            s2 = String.valueOf(s) + "." + s2;
        }
        if (s2 == null) {
            final StringBuilder sb = new StringBuilder(modelObject.getType());
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            sb.insert(0, String.valueOf(s) + ".");
            sb.append("Action");
            s2 = sb.toString();
        }
        try {
            final XActionDocument document = this.getDocument(modelObject);
            modelObject.setAttribute("xaction", "true");
            final IXAction ixAction = (IXAction)this.A.loadClass(s2).newInstance();
            ixAction.setDocument(document);
            ixAction.configure(document);
            return ixAction;
        }
        catch (ClassNotFoundException ex3) {
            return null;
        }
        catch (IllegalAccessException ex) {
            XActionDocument.D.exception(ex);
            return null;
        }
        catch (InstantiationException ex2) {
            XActionDocument.D.exception(ex2);
            return null;
        }
    }
    
    public IXAction getAction(final IPath path) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return null;
        }
        return this.getAction(path.queryFirst(root));
    }
    
    public IXAction getAction(final String s) {
        final IModelObject root = this.getRoot();
        if (root == null) {
            return null;
        }
        return this.getAction(root.getFirstChild(s));
    }
    
    public List<IXAction> getActions(final List<IModelObject> list) {
        final ArrayList<IXAction> list2 = new ArrayList<IXAction>(list.size());
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            final IXAction action = this.getAction(iterator.next());
            if (action != null) {
                list2.add(action);
            }
        }
        return list2;
    }
    
    public List<IXAction> getActions(final IExpression expression) {
        final List<IModelObject> query = expression.query(this.getRoot(), null);
        final ArrayList list = new ArrayList<IXAction>(query.size());
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            final IXAction action = this.getAction(iterator.next());
            if (action != null) {
                list.add(action);
            }
        }
        return (List<IXAction>)list;
    }
    
    public void error(final String s, final Exception ex) {
        final StringWriter stringWriter = new StringWriter();
        stringWriter.append(s);
        stringWriter.append('\n');
        ex.printStackTrace(new PrintWriter(stringWriter));
        System.err.println(stringWriter.toString());
    }
    
    public void error(final String s) {
        System.err.println(s);
    }
    
    public XActionDocument getDocument(final IModelObject root) {
        final XActionDocument xActionDocument = new XActionDocument(this.A);
        xActionDocument.C = this;
        xActionDocument.setRoot(root);
        final Iterator<String> iterator = this.E.iterator();
        while (iterator.hasNext()) {
            xActionDocument.addPackage(iterator.next());
        }
        return xActionDocument;
    }
    
    public XActionDocument getDocument(final IPath path) {
        final IModelObject queryFirst = path.queryFirst(this.getRoot());
        return (queryFirst != null) ? this.getDocument(queryFirst) : null;
    }
    
    public XActionDocument getDocument(final String s) {
        final IModelObject firstChild = this.getRoot().getFirstChild(s);
        return (firstChild != null) ? this.getDocument(firstChild) : null;
    }
    
    @Override
    public String toString() {
        return ModelAlgorithms.createIdentityPath(this.getRoot()).toString();
    }
}
