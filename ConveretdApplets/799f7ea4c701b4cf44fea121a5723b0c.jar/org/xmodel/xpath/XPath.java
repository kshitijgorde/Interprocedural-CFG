// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath;

import java.util.Iterator;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xml.XmlIO;
import java.util.HashMap;
import org.xmodel.xpath.expression.P;
import org.xmodel.xpath.parser.generated.ParseException;
import java.io.Reader;
import org.xmodel.xpath.parser.generated.A;
import java.io.StringReader;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.PathSyntaxException;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IPath;
import java.util.Map;
import org.xmodel.E;
import org.xmodel.D;

public class XPath extends D implements E
{
    private static ThreadLocal<Map<String, IPath>> H;
    private static ThreadLocal<Map<String, IExpression>> I;
    private static Log G;
    
    static {
        XPath.H = new ThreadLocal<Map<String, IPath>>();
        XPath.I = new ThreadLocal<Map<String, IExpression>>();
        XPath.G = Log.getLog("org.xmodel.xml");
    }
    
    public XPath(final String s) throws PathSyntaxException {
        this.compile(s);
    }
    
    public static List<IModelObject> query(final String s) {
        return createExpression(s).query(null);
    }
    
    public static List<IModelObject> query(final IContext context, final String s) {
        return createExpression(s).query(context, null);
    }
    
    public static IModelObject queryFirst(final String s) {
        return createExpression(s).queryFirst();
    }
    
    public static IModelObject queryFirst(final IContext context, final String s) {
        return createExpression(s).queryFirst(context);
    }
    
    public static IPath createPath(final String s) {
        try {
            final Map<String, IPath> b = B();
            IPath path = b.get(s);
            if (path == null) {
                path = new XPath(s);
                b.put(s, path);
            }
            return path;
        }
        catch (PathSyntaxException ex) {
            XPath.G.warn(ex.getMessage());
            return null;
        }
    }
    
    public static IExpression createExpression(final String s) {
        try {
            final Map<String, IExpression> a = A();
            IExpression \u00ed = a.get(s);
            if (\u00ed == null) {
                try {
                    final A a2 = new A(new StringReader(s));
                    a2.A(s);
                    \u00ed = a2.\u00ed();
                    a.put(s, \u00ed);
                }
                catch (ParseException ex) {
                    throw new PathSyntaxException("Syntax Error: " + s, ex);
                }
            }
            return \u00ed;
        }
        catch (PathSyntaxException ex2) {
            XPath.G.exception(ex2);
            return null;
        }
    }
    
    public static IExpression createExpression(final String s, final boolean b) {
        if (b) {
            return createExpression(s);
        }
        try {
            final A a = new A(new StringReader(s));
            a.A(s);
            return a.\u00ed();
        }
        catch (ParseException ex) {
            XPath.G.exception(ex);
            return null;
        }
    }
    
    public void compile(final String s) throws PathSyntaxException {
        final A a = new A(new StringReader(s));
        a.A(s);
        try {
            a.D(this);
        }
        catch (ParseException ex) {
            throw new PathSyntaxException("Syntax Error: " + s, ex);
        }
    }
    
    public static IExpression compileExpression(final String s) throws PathSyntaxException {
        try {
            final A a = new A(new StringReader(s));
            a.A(s);
            return a.\u00ed();
        }
        catch (ParseException ex) {
            throw new PathSyntaxException("Syntax Error: " + s, ex);
        }
    }
    
    public static IPath convertToPath(IExpression argument) {
        if (argument instanceof P) {
            argument = argument.getArgument(0);
        }
        if (argument != null && argument instanceof org.xmodel.xpath.expression.A) {
            return ((org.xmodel.xpath.expression.A)argument).P();
        }
        return null;
    }
    
    public static IExpression convertToExpression(final IPath path) {
        return new P(new org.xmodel.xpath.expression.A(path));
    }
    
    private static Map<String, IPath> B() {
        Map<String, IPath> map = XPath.H.get();
        if (map == null) {
            map = new HashMap<String, IPath>();
            XPath.H.set(map);
        }
        return map;
    }
    
    private static Map<String, IExpression> A() {
        Map<String, IExpression> map = XPath.I.get();
        if (map == null) {
            map = new HashMap<String, IExpression>();
            XPath.I.set(map);
        }
        return map;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o.toString().equals(this.toString());
    }
    
    public IPath clone(final P p) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        final int length = this.length();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(this.getPathElement(i).toString());
            if (i < length - 1) {
                sb.append('/');
            }
        }
        return sb.toString();
    }
    
    public static void main(final String[] array) throws Exception {
        final String s = "<a id='5'>  <b x='9'>1</b>  <b>2    <c>i</c>    <c>ii</c>    <c>iii</c>  </b>  <?pi?>  <b>3</b>  <b>4    <c>i</c>    <c>ii</c>    <c>iii</c>  </b>  <b>5</b></a>";
        final XmlIO xmlIO = new XmlIO();
        final IModelObject read = xmlIO.read(s);
        System.out.println(xmlIO.write(read));
        final Iterator<IModelObject> iterator = createPath("/a/b[ 2]/c[ 2]/following::*").query(read, null).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("path=" + ModelAlgorithms.createIdentityPath(createExpression("/a/b/@x").queryFirst(read)));
    }
}
