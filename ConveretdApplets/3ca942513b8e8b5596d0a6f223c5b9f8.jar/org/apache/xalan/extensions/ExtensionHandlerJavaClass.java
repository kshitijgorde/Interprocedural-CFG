// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.functions.FuncExtFunction;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import java.lang.reflect.Modifier;
import org.apache.xalan.trace.ExtensionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import org.apache.xalan.transformer.TransformerImpl;
import java.util.Vector;
import java.lang.reflect.Method;

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
    
    public boolean isFunctionAvailable(final String function) {
        final Method[] methods = this.m_classObj.getMethods();
        for (int nMethods = methods.length, i = 0; i < nMethods; ++i) {
            if (methods[i].getName().equals(function)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isElementAvailable(final String element) {
        final Method[] methods = this.m_classObj.getMethods();
        for (int nMethods = methods.length, i = 0; i < nMethods; ++i) {
            if (methods[i].getName().equals(element)) {
                final Class[] paramTypes = methods[i].getParameterTypes();
                if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom((ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext == null) ? (ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext = class$("org.apache.xalan.extensions.XSLProcessorContext")) : ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$XSLProcessorContext) && paramTypes[1].isAssignableFrom((ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall == null) ? (ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall = class$("org.apache.xalan.templates.ElemExtensionCall")) : ExtensionHandlerJavaClass.class$org$apache$xalan$templates$ElemExtensionCall)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        try {
            final TransformerImpl trans = (exprContext != null) ? ((TransformerImpl)exprContext.getXPathContext().getOwnerObject()) : null;
            if (funcName.equals("new")) {
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = { null };
                for (int i = 0; i < methodArgs.length; ++i) {
                    methodArgs[i] = args.elementAt(i);
                }
                Constructor c = null;
                if (methodKey != null) {
                    c = (Constructor)this.getFromCache(methodKey, null, methodArgs);
                }
                if (c != null && !trans.getDebug()) {
                    try {
                        final Class[] paramTypes = c.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        return c.newInstance(convertedArgs[0]);
                    }
                    catch (InvocationTargetException ite) {
                        throw ite;
                    }
                    catch (Exception ex) {}
                }
                c = MethodResolver.getConstructor(this.m_classObj, methodArgs, convertedArgs, exprContext);
                if (methodKey != null) {
                    this.putToCache(methodKey, null, methodArgs, c);
                }
                if (trans != null && trans.getDebug()) {
                    trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
                    Object result;
                    try {
                        result = c.newInstance(convertedArgs[0]);
                    }
                    catch (Exception e) {
                        throw e;
                    }
                    finally {
                        trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
                    }
                    return result;
                }
                return c.newInstance(convertedArgs[0]);
            }
            else {
                Object targetObject = null;
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = { null };
                for (int j = 0; j < methodArgs.length; ++j) {
                    methodArgs[j] = args.elementAt(j);
                }
                Method m = null;
                if (methodKey != null) {
                    m = (Method)this.getFromCache(methodKey, null, methodArgs);
                }
                if (m != null && !trans.getDebug()) {
                    try {
                        final Class[] paramTypes = m.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        if (Modifier.isStatic(m.getModifiers())) {
                            return m.invoke(null, convertedArgs[0]);
                        }
                        int nTargetArgs = convertedArgs[0].length;
                        if (((ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext == null) ? (ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext = class$("org.apache.xalan.extensions.ExpressionContext")) : ExtensionHandlerJavaClass.class$org$apache$xalan$extensions$ExpressionContext).isAssignableFrom(paramTypes[0])) {
                            --nTargetArgs;
                        }
                        if (methodArgs.length <= nTargetArgs) {
                            return m.invoke(this.m_defaultInstance, convertedArgs[0]);
                        }
                        targetObject = methodArgs[0];
                        if (targetObject instanceof XObject) {
                            targetObject = ((XObject)targetObject).object();
                        }
                        return m.invoke(targetObject, convertedArgs[0]);
                    }
                    catch (InvocationTargetException ite2) {
                        throw ite2;
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
                if (methodKey != null) {
                    this.putToCache(methodKey, null, methodArgs, m);
                }
                if (4 == resolveType) {
                    if (trans != null && trans.getDebug()) {
                        trans.getTraceManager().fireExtensionEvent(m, targetObject, convertedArgs[0]);
                        Object result2;
                        try {
                            result2 = m.invoke(targetObject, convertedArgs[0]);
                        }
                        catch (Exception e2) {
                            throw e2;
                        }
                        finally {
                            trans.getTraceManager().fireExtensionEndEvent(m, targetObject, convertedArgs[0]);
                        }
                        return result2;
                    }
                    return m.invoke(targetObject, convertedArgs[0]);
                }
                else if (Modifier.isStatic(m.getModifiers())) {
                    if (trans != null && trans.getDebug()) {
                        trans.getTraceManager().fireExtensionEvent(m, null, convertedArgs[0]);
                        Object result2;
                        try {
                            result2 = m.invoke(null, convertedArgs[0]);
                        }
                        catch (Exception e2) {
                            throw e2;
                        }
                        finally {
                            trans.getTraceManager().fireExtensionEndEvent(m, null, convertedArgs[0]);
                        }
                        return result2;
                    }
                    return m.invoke(null, convertedArgs[0]);
                }
                else {
                    if (null == this.m_defaultInstance) {
                        if (trans != null && trans.getDebug()) {
                            trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, this.m_classObj));
                            try {
                                this.m_defaultInstance = this.m_classObj.newInstance();
                            }
                            catch (Exception e3) {
                                throw e3;
                            }
                            finally {
                                trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, this.m_classObj));
                            }
                        }
                        else {
                            this.m_defaultInstance = this.m_classObj.newInstance();
                        }
                    }
                    if (trans != null && trans.getDebug()) {
                        trans.getTraceManager().fireExtensionEvent(m, this.m_defaultInstance, convertedArgs[0]);
                        Object result2;
                        try {
                            result2 = m.invoke(this.m_defaultInstance, convertedArgs[0]);
                        }
                        catch (Exception e2) {
                            throw e2;
                        }
                        finally {
                            trans.getTraceManager().fireExtensionEndEvent(m, this.m_defaultInstance, convertedArgs[0]);
                        }
                        return result2;
                    }
                    return m.invoke(this.m_defaultInstance, convertedArgs[0]);
                }
            }
        }
        catch (InvocationTargetException ite3) {
            Throwable resultException = ite3;
            final Throwable targetException = ite3.getTargetException();
            if (targetException instanceof TransformerException) {
                throw (TransformerException)targetException;
            }
            if (targetException != null) {
                resultException = targetException;
            }
            throw new TransformerException(resultException);
        }
        catch (Exception e4) {
            throw new TransformerException(e4);
        }
    }
    
    public Object callFunction(final FuncExtFunction extFunction, final Vector args, final ExpressionContext exprContext) throws TransformerException {
        return this.callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
    }
    
    public void processElement(final String localPart, final ElemTemplateElement element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        Method m = (Method)this.getFromCache(methodKey, null, null);
        if (null == m) {
            try {
                m = MethodResolver.getElementMethod(this.m_classObj, localPart);
                if (null == this.m_defaultInstance && !Modifier.isStatic(m.getModifiers())) {
                    if (transformer.getDebug()) {
                        transformer.getTraceManager().fireExtensionEvent(new ExtensionEvent(transformer, this.m_classObj));
                        try {
                            this.m_defaultInstance = this.m_classObj.newInstance();
                        }
                        catch (Exception e) {
                            throw e;
                        }
                        finally {
                            transformer.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(transformer, this.m_classObj));
                        }
                    }
                    else {
                        this.m_defaultInstance = this.m_classObj.newInstance();
                    }
                }
            }
            catch (Exception e) {
                throw new TransformerException(e.getMessage(), e);
            }
            this.putToCache(methodKey, null, null, m);
        }
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
        try {
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireExtensionEvent(m, this.m_defaultInstance, new Object[] { xpc, element });
                try {
                    result = m.invoke(this.m_defaultInstance, xpc, element);
                }
                catch (Exception e2) {
                    throw e2;
                }
                finally {
                    transformer.getTraceManager().fireExtensionEndEvent(m, this.m_defaultInstance, new Object[] { xpc, element });
                }
            }
            else {
                result = m.invoke(this.m_defaultInstance, xpc, element);
            }
        }
        catch (InvocationTargetException e3) {
            final Throwable targetException = e3.getTargetException();
            if (targetException instanceof TransformerException) {
                throw (TransformerException)targetException;
            }
            if (targetException != null) {
                throw new TransformerException(targetException.getMessage(), targetException);
            }
            throw new TransformerException(e3.getMessage(), e3);
        }
        catch (Exception e4) {
            throw new TransformerException(e4.getMessage(), e4);
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
}
