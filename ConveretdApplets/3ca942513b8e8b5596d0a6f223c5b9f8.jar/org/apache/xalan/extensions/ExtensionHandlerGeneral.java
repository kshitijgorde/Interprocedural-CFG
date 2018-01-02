// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.XPathProcessorException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xml.dtm.ref.DTMNodeList;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.objects.XObject;
import java.util.Vector;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import java.net.URL;
import org.apache.xml.utils.StringVector;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class ExtensionHandlerGeneral extends ExtensionHandler
{
    private String m_scriptSrc;
    private String m_scriptSrcURL;
    private Hashtable m_functions;
    private Hashtable m_elements;
    private Object m_engine;
    private Method m_engineCall;
    private static String BSF_MANAGER;
    private static final String DEFAULT_BSF_MANAGER = "org.apache.bsf.BSFManager";
    private static final String propName = "org.apache.xalan.extensions.bsf.BSFManager";
    private static final Integer ZEROINT;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class array$Ljava$lang$Object;
    
    public ExtensionHandlerGeneral(final String namespaceUri, final StringVector elemNames, final StringVector funcNames, final String scriptLang, final String scriptSrcURL, final String scriptSrc, final String systemId) throws TransformerException {
        super(namespaceUri, scriptLang);
        this.m_functions = new Hashtable();
        this.m_elements = new Hashtable();
        this.m_engineCall = null;
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
            URL url = null;
            try {
                url = new URL(this.m_scriptSrcURL);
            }
            catch (MalformedURLException mue) {
                final int indexOfColon = this.m_scriptSrcURL.indexOf(58);
                final int indexOfSlash = this.m_scriptSrcURL.indexOf(47);
                if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash) {
                    url = null;
                    throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), mue);
                }
                try {
                    url = new URL(new URL(SystemIDResolver.getAbsoluteURI(systemId)), this.m_scriptSrcURL);
                }
                catch (MalformedURLException mue2) {
                    throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), mue2);
                }
            }
            if (url != null) {
                try {
                    final URLConnection uc = url.openConnection();
                    final InputStream is = uc.getInputStream();
                    final byte[] bArray = new byte[uc.getContentLength()];
                    is.read(bArray);
                    this.m_scriptSrc = new String(bArray);
                }
                catch (IOException ioe) {
                    throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), ioe);
                }
            }
        }
        Object manager = null;
        try {
            manager = ObjectFactory.newInstance(ExtensionHandlerGeneral.BSF_MANAGER, ObjectFactory.findClassLoader(), true);
        }
        catch (ObjectFactory.ConfigurationError e) {
            e.printStackTrace();
        }
        if (manager == null) {
            throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_INIT_BSFMGR", null));
        }
        try {
            final Method loadScriptingEngine = manager.getClass().getMethod("loadScriptingEngine", (ExtensionHandlerGeneral.class$java$lang$String == null) ? (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")) : ExtensionHandlerGeneral.class$java$lang$String);
            this.m_engine = loadScriptingEngine.invoke(manager, scriptLang);
            final Method engineExec = this.m_engine.getClass().getMethod("exec", (ExtensionHandlerGeneral.class$java$lang$String == null) ? (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")) : ExtensionHandlerGeneral.class$java$lang$String, Integer.TYPE, Integer.TYPE, (ExtensionHandlerGeneral.class$java$lang$Object == null) ? (ExtensionHandlerGeneral.class$java$lang$Object = class$("java.lang.Object")) : ExtensionHandlerGeneral.class$java$lang$Object);
            engineExec.invoke(this.m_engine, "XalanScript", ExtensionHandlerGeneral.ZEROINT, ExtensionHandlerGeneral.ZEROINT, this.m_scriptSrc);
        }
        catch (Exception e2) {
            e2.printStackTrace();
            throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_CMPL_EXTENSN", null), e2);
        }
    }
    
    public boolean isFunctionAvailable(final String function) {
        return this.m_functions.get(function) != null;
    }
    
    public boolean isElementAvailable(final String element) {
        return this.m_elements.get(element) != null;
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        try {
            final Object[] argArray = new Object[args.size()];
            for (int i = 0; i < argArray.length; ++i) {
                Object o = args.elementAt(i);
                argArray[i] = ((o instanceof XObject) ? ((XObject)o).object() : o);
                o = argArray[i];
                if (null != o && o instanceof DTMIterator) {
                    argArray[i] = new DTMNodeList((DTMIterator)o);
                }
            }
            if (this.m_engineCall == null) {
                this.m_engineCall = this.m_engine.getClass().getMethod("call", (ExtensionHandlerGeneral.class$java$lang$Object == null) ? (ExtensionHandlerGeneral.class$java$lang$Object = class$("java.lang.Object")) : ExtensionHandlerGeneral.class$java$lang$Object, (ExtensionHandlerGeneral.class$java$lang$String == null) ? (ExtensionHandlerGeneral.class$java$lang$String = class$("java.lang.String")) : ExtensionHandlerGeneral.class$java$lang$String, (ExtensionHandlerGeneral.array$Ljava$lang$Object == null) ? (ExtensionHandlerGeneral.array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : ExtensionHandlerGeneral.array$Ljava$lang$Object);
            }
            return this.m_engineCall.invoke(this.m_engine, null, funcName, argArray);
        }
        catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            if (null != msg) {
                if (msg.startsWith("Stopping after fatal error:")) {
                    msg = msg.substring("Stopping after fatal error:".length());
                }
                throw new TransformerException(e);
            }
            throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_CREATE_EXTENSN", new Object[] { funcName, e }));
        }
    }
    
    public Object callFunction(final FuncExtFunction extFunction, final Vector args, final ExpressionContext exprContext) throws TransformerException {
        return this.callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
    }
    
    public void processElement(final String localPart, final ElemTemplateElement element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
        try {
            final Vector argv = new Vector(2);
            argv.addElement(xpc);
            argv.addElement(element);
            result = this.callFunction(localPart, argv, methodKey, transformer.getXPathContext().getExpressionContext());
        }
        catch (XPathProcessorException e) {
            throw new TransformerException(e.getMessage(), e);
        }
        if (result != null) {
            xpc.outputToResultTree(stylesheetTree, result);
        }
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
        ZEROINT = new Integer(0);
        ExtensionHandlerGeneral.BSF_MANAGER = ObjectFactory.lookUpFactoryClassName("org.apache.xalan.extensions.bsf.BSFManager", null, null);
        if (ExtensionHandlerGeneral.BSF_MANAGER == null) {
            ExtensionHandlerGeneral.BSF_MANAGER = "org.apache.bsf.BSFManager";
        }
    }
}
