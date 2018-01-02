// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Element;
import java.lang.reflect.InvocationTargetException;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Vector;

public class ExtensionHandlerJavaClass extends ExtensionHandlerJava
{
    private Class m_classObj;
    private Object m_defaultInstance;
    static /* synthetic */ Class class$org$apache$xalan$extensions$XSLProcessorContext;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionCall;
    static /* synthetic */ Class class$org$apache$xalan$extensions$ExpressionContext;
    
    public ExtensionHandlerJavaClass(final String namespaceUri, final String scriptLang, final String className) {
        super(namespaceUri, scriptLang, className);
        this.m_classObj = null;
        this.m_defaultInstance = null;
        try {
            this.m_classObj = ExtensionHandler.getClassForName(className);
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        try {
            if (funcName.equals("new")) {
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = new Object[1][];
                for (int i = 0; i < methodArgs.length; ++i) {
                    methodArgs[i] = args.elementAt(i);
                }
                Constructor c = (Constructor)this.getFromCache(methodKey, null, methodArgs);
                if (c != null) {
                    try {
                        final Class[] paramTypes = c.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        return c.newInstance(convertedArgs[0]);
                    }
                    catch (Exception ex) {}
                }
                c = MethodResolver.getConstructor(this.m_classObj, methodArgs, convertedArgs, exprContext);
                this.putToCache(methodKey, null, methodArgs, c);
                return c.newInstance(convertedArgs[0]);
            }
            Object targetObject = null;
            final Object[] methodArgs = new Object[args.size()];
            final Object[][] convertedArgs = new Object[1][];
            for (int j = 0; j < methodArgs.length; ++j) {
                methodArgs[j] = args.elementAt(j);
            }
            Method m = (Method)this.getFromCache(methodKey, null, methodArgs);
            if (m != null) {
                try {
                    final Class[] paramTypes = m.getParameterTypes();
                    MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                    if (Modifier.isStatic(m.getModifiers())) {
                        return m.invoke(null, convertedArgs[0]);
                    }
                    int nTargetArgs = convertedArgs[0].length;
                    if (((ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext != null) ? ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext : (ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext = class$("org.apache.xalan.extensions.ExpressionContext"))).isAssignableFrom(paramTypes[0])) {
                        --nTargetArgs;
                    }
                    if (methodArgs.length <= nTargetArgs) {
                        return m.invoke(this.m_defaultInstance, convertedArgs[0]);
                    }
                    return m.invoke(methodArgs[0], convertedArgs[0]);
                }
                catch (Exception ex2) {}
            }
            int resolveType;
            if (args.size() > 0) {
                targetObject = methodArgs[0];
                if (targetObject instanceof XObject) {
                    targetObject = ((XObject)targetObject).object();
                }
                if (this.m_classObj.isAssignableFrom(targetObject.getClass())) {
                    resolveType = 4;
                }
                else {
                    resolveType = 3;
                }
            }
            else {
                targetObject = null;
                resolveType = 3;
            }
            m = MethodResolver.getMethod(this.m_classObj, funcName, methodArgs, convertedArgs, exprContext, resolveType);
            this.putToCache(methodKey, null, methodArgs, m);
            if (resolveType == 4) {
                return m.invoke(targetObject, convertedArgs[0]);
            }
            if (Modifier.isStatic(m.getModifiers())) {
                return m.invoke(null, convertedArgs[0]);
            }
            if (this.m_defaultInstance == null) {
                this.m_defaultInstance = this.m_classObj.newInstance();
            }
            return m.invoke(this.m_defaultInstance, convertedArgs[0]);
        }
        catch (InvocationTargetException ite) {
            final Throwable realException = ite.getTargetException();
            if (realException instanceof Exception) {
                throw new TransformerException(realException);
            }
            throw new TransformerException(ite);
        }
        catch (Exception e) {
            throw new TransformerException(e);
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
        final Method[] methods = this.m_classObj.getMethods();
        for (int nMethods = methods.length, i = 0; i < nMethods; ++i) {
            if (methods[i].getName().equals(element)) {
                final Class[] paramTypes = methods[i].getParameterTypes();
                if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom((ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext != null) ? ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext : (ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext = class$("org.apache.xalan.extensions.XSLProcessorContext"))) && paramTypes[1].isAssignableFrom((ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall != null) ? ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall : (ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall = class$("org.apache.xalan.templates.ElemExtensionCall")))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isFunctionAvailable(final String function) {
        final Method[] methods = this.m_classObj.getMethods();
        for (int nMethods = methods.length, i = 0; i < nMethods; ++i) {
            if (methods[i].getName().equals(function)) {
                return true;
            }
        }
        return false;
    }
    
    public void processElement(final String localPart, final Element element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Node sourceTree, final Node sourceNode, final QName mode, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        Method m = (Method)this.getFromCache(methodKey, null, null);
        if (m == null) {
            try {
                m = MethodResolver.getElementMethod(this.m_classObj, localPart);
                if (this.m_defaultInstance == null && !Modifier.isStatic(m.getModifiers())) {
                    this.m_defaultInstance = this.m_classObj.newInstance();
                }
            }
            catch (Exception e) {
                throw new TransformerException(e.getMessage(), e);
            }
            this.putToCache(methodKey, null, null, m);
        }
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree, sourceTree, sourceNode, mode);
        try {
            result = m.invoke(this.m_defaultInstance, xpc, element);
        }
        catch (Exception e2) {
            throw new TransformerException(e2.getMessage(), e2);
        }
        if (result != null) {
            xpc.outputToResultTree(stylesheetTree, result);
        }
    }
}
