// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import org.apache.xpath.XPathProcessorException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Element;
import org.apache.xpath.objects.XObject;
import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.StringVector;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class ExtensionHandlerGeneral extends ExtensionHandler
{
    private String m_scriptSrc;
    private String m_scriptSrcURL;
    private Hashtable m_functions;
    private Hashtable m_elements;
    private Object m_mgr;
    private Object m_engine;
    private static final String BSF_MANAGER = "com.ibm.bsf.BSFManager";
    private static Class managerClass;
    private static Method mgrLoadScriptingEngine;
    private static final String BSF_ENGINE = "com.ibm.bsf.BSFEngine";
    private static Method engineExec;
    private static Method engineCall;
    private static final Integer NEG1INT;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$lang$Object;
    
    static {
        NEG1INT = new Integer(-1);
        try {
            ExtensionHandlerGeneral.managerClass = Class.forName("com.ibm.bsf.BSFManager");
            ExtensionHandlerGeneral.mgrLoadScriptingEngine = ExtensionHandlerGeneral.managerClass.getMethod("loadScriptingEngine", (ExtensionHandlerGeneral.class$java$lang$String != null) ? ExtensionHandlerGeneral.class$java$lang$String : (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")));
            final Class engineClass = Class.forName("com.ibm.bsf.BSFEngine");
            ExtensionHandlerGeneral.engineExec = engineClass.getMethod("exec", (ExtensionHandlerGeneral.class$java$lang$String != null) ? ExtensionHandlerGeneral.class$java$lang$String : (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")), Integer.TYPE, Integer.TYPE, (ExtensionHandlerGeneral.class$java$lang$Object != null) ? ExtensionHandlerGeneral.class$java$lang$Object : (ExtensionHandlerGeneral.class$java$lang$Object = class$("java.lang.Object")));
            ExtensionHandlerGeneral.engineCall = engineClass.getMethod("call", (ExtensionHandlerGeneral.class$java$lang$Object != null) ? ExtensionHandlerGeneral.class$java$lang$Object : (ExtensionHandlerGeneral.class$java$lang$Object = class$("java.lang.Object")), (ExtensionHandlerGeneral.class$java$lang$String != null) ? ExtensionHandlerGeneral.class$java$lang$String : (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")), Class.forName("[Ljava.lang.Object;"));
        }
        catch (Exception e) {
            ExtensionHandlerGeneral.managerClass = null;
            ExtensionHandlerGeneral.mgrLoadScriptingEngine = null;
            ExtensionHandlerGeneral.engineExec = null;
            ExtensionHandlerGeneral.engineCall = null;
            e.printStackTrace();
        }
    }
    
    public ExtensionHandlerGeneral(final String namespaceUri, final StringVector elemNames, final StringVector funcNames, final String scriptLang, final String scriptSrcURL, final String scriptSrc) throws TransformerException {
        super(namespaceUri, scriptLang);
        this.m_functions = new Hashtable();
        this.m_elements = new Hashtable();
        if (elemNames != null) {
            final Object junk = new Object();
            for (int n = elemNames.size(), i = 0; i < n; ++i) {
                final String tok = elemNames.elementAt(i);
                this.m_elements.put(tok, junk);
            }
        }
        if (funcNames != null) {
            final Object junk = new Object();
            for (int n = funcNames.size(), i = 0; i < n; ++i) {
                final String tok = funcNames.elementAt(i);
                this.m_functions.put(tok, junk);
            }
        }
        this.m_scriptSrcURL = scriptSrcURL;
        this.m_scriptSrc = scriptSrc;
        if (this.m_scriptSrcURL != null) {
            throw new TransformerException("src attribute not yet supported for " + scriptLang);
        }
        if (ExtensionHandlerGeneral.managerClass == null) {
            throw new TransformerException("Could not initialize BSF manager");
        }
        try {
            this.m_mgr = ExtensionHandlerGeneral.managerClass.newInstance();
            this.m_engine = ExtensionHandlerGeneral.mgrLoadScriptingEngine.invoke(this.m_mgr, scriptLang);
            ExtensionHandlerGeneral.engineExec.invoke(this.m_engine, "XalanScript", ExtensionHandlerGeneral.NEG1INT, ExtensionHandlerGeneral.NEG1INT, this.m_scriptSrc);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new TransformerException("Could not compile extension", e);
        }
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        try {
            final Object[] argArray = new Object[args.size()];
            for (int i = 0; i < argArray.length; ++i) {
                final Object o = args.elementAt(i);
                argArray[i] = ((o instanceof XObject) ? ((XObject)o).object() : o);
            }
            return ExtensionHandlerGeneral.engineCall.invoke(this.m_engine, null, funcName, argArray);
        }
        catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if (msg != null) {
                if (msg.startsWith("Stopping after fatal error:")) {
                    msg = msg.substring("Stopping after fatal error:".length());
                }
                throw new TransformerException(e);
            }
            throw new TransformerException("Could not create extension: " + funcName + " because of: " + e);
        }
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public boolean isElementAvailable(final String element) {
        return this.m_elements.get(element) != null;
    }
    
    public boolean isFunctionAvailable(final String function) {
        return this.m_functions.get(function) != null;
    }
    
    public void processElement(final String localPart, final Element element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Node sourceTree, final Node sourceNode, final QName mode, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree, sourceTree, sourceNode, mode);
        try {
            final Vector argv = new Vector(2);
            argv.addElement(xpc);
            argv.addElement(element);
            result = this.callFunction(localPart, argv, methodKey, transformer.getXPathContext());
        }
        catch (XPathProcessorException e) {
            throw new TransformerException(e.getMessage(), e);
        }
        if (result != null) {
            xpc.outputToResultTree(stylesheetTree, result);
        }
    }
}
