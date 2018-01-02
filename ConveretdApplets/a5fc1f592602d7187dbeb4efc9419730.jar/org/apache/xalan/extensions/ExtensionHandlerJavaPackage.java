// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import java.lang.reflect.Modifier;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.trace.ExtensionEvent;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.util.Vector;
import java.lang.reflect.Method;

public class ExtensionHandlerJavaPackage extends ExtensionHandlerJava
{
    static /* synthetic */ Class class$org$apache$xalan$extensions$XSLProcessorContext;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionCall;
    
    public ExtensionHandlerJavaPackage(final String namespaceUri, final String scriptLang, final String className) {
        super(namespaceUri, scriptLang, className);
    }
    
    public boolean isFunctionAvailable(String function) {
        try {
            final String fullName = super.m_className + function;
            final int lastDot = fullName.lastIndexOf(".");
            if (lastDot >= 0) {
                final Class myClass = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
                final Method[] methods = myClass.getMethods();
                final int nMethods = methods.length;
                function = fullName.substring(lastDot + 1);
                for (int i = 0; i < nMethods; ++i) {
                    if (methods[i].getName().equals(function)) {
                        return true;
                    }
                }
            }
        }
        catch (ClassNotFoundException ex) {}
        return false;
    }
    
    public boolean isElementAvailable(String element) {
        try {
            final String fullName = super.m_className + element;
            final int lastDot = fullName.lastIndexOf(".");
            if (lastDot >= 0) {
                final Class myClass = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
                final Method[] methods = myClass.getMethods();
                final int nMethods = methods.length;
                element = fullName.substring(lastDot + 1);
                for (int i = 0; i < nMethods; ++i) {
                    if (methods[i].getName().equals(element)) {
                        final Class[] paramTypes = methods[i].getParameterTypes();
                        if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom((ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext == null) ? (ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext = class$("org.apache.xalan.extensions.XSLProcessorContext")) : ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext) && paramTypes[1].isAssignableFrom((ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall == null) ? (ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall = class$("org.apache.xalan.templates.ElemExtensionCall")) : ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall)) {
                            return true;
                        }
                    }
                }
            }
        }
        catch (ClassNotFoundException ex) {}
        return false;
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        final int lastDot = funcName.lastIndexOf(".");
        try {
            if (funcName.endsWith(".new")) {
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = { null };
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
                    catch (InvocationTargetException ite) {
                        throw ite;
                    }
                    catch (Exception ex) {}
                }
                final String className = super.m_className + funcName.substring(0, lastDot);
                Class classObj;
                try {
                    classObj = ExtensionHandler.getClassForName(className);
                }
                catch (ClassNotFoundException e) {
                    throw new TransformerException(e);
                }
                c = MethodResolver.getConstructor(classObj, methodArgs, convertedArgs, exprContext);
                this.putToCache(methodKey, null, methodArgs, c);
                if (TransformerImpl.S_DEBUG) {
                    final TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
                    trans.getTraceManager().fireExtensionEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
                    Object result;
                    try {
                        result = c.newInstance(convertedArgs[0]);
                    }
                    catch (Exception e2) {
                        throw e2;
                    }
                    finally {
                        trans.getTraceManager().fireExtensionEndEvent(new ExtensionEvent(trans, c, convertedArgs[0]));
                    }
                    return result;
                }
                return c.newInstance(convertedArgs[0]);
            }
            else if (-1 != lastDot) {
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = { null };
                for (int i = 0; i < methodArgs.length; ++i) {
                    methodArgs[i] = args.elementAt(i);
                }
                Method m = (Method)this.getFromCache(methodKey, null, methodArgs);
                if (m != null && !TransformerImpl.S_DEBUG) {
                    try {
                        final Class[] paramTypes = m.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        return m.invoke(null, convertedArgs[0]);
                    }
                    catch (InvocationTargetException ite) {
                        throw ite;
                    }
                    catch (Exception ex2) {}
                }
                final String className = super.m_className + funcName.substring(0, lastDot);
                final String methodName = funcName.substring(lastDot + 1);
                Class classObj;
                try {
                    classObj = ExtensionHandler.getClassForName(className);
                }
                catch (ClassNotFoundException e) {
                    throw new TransformerException(e);
                }
                m = MethodResolver.getMethod(classObj, methodName, methodArgs, convertedArgs, exprContext, 1);
                this.putToCache(methodKey, null, methodArgs, m);
                if (TransformerImpl.S_DEBUG) {
                    final TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
                    trans.getTraceManager().fireExtensionEvent(m, null, convertedArgs[0]);
                    Object result;
                    try {
                        result = m.invoke(null, convertedArgs[0]);
                    }
                    catch (Exception e2) {
                        throw e2;
                    }
                    finally {
                        trans.getTraceManager().fireExtensionEndEvent(m, null, convertedArgs[0]);
                    }
                    return result;
                }
                return m.invoke(null, convertedArgs[0]);
            }
            else {
                if (args.size() < 1) {
                    throw new TransformerException(XSLMessages.createMessage("ER_INSTANCE_MTHD_CALL_REQUIRES", new Object[] { funcName }));
                }
                Object targetObject = args.elementAt(0);
                if (targetObject instanceof XObject) {
                    targetObject = ((XObject)targetObject).object();
                }
                final Object[] methodArgs = new Object[args.size() - 1];
                final Object[][] convertedArgs = { null };
                for (int i = 0; i < methodArgs.length; ++i) {
                    methodArgs[i] = args.elementAt(i + 1);
                }
                Method m = (Method)this.getFromCache(methodKey, targetObject, methodArgs);
                if (m != null) {
                    try {
                        final Class[] paramTypes = m.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        return m.invoke(targetObject, convertedArgs[0]);
                    }
                    catch (InvocationTargetException ite) {
                        throw ite;
                    }
                    catch (Exception ex3) {}
                }
                final Class classObj = targetObject.getClass();
                m = MethodResolver.getMethod(classObj, funcName, methodArgs, convertedArgs, exprContext, 2);
                this.putToCache(methodKey, targetObject, methodArgs, m);
                if (TransformerImpl.S_DEBUG) {
                    final TransformerImpl trans = (TransformerImpl)exprContext.getXPathContext().getOwnerObject();
                    trans.getTraceManager().fireExtensionEvent(m, targetObject, convertedArgs[0]);
                    Object result;
                    try {
                        result = m.invoke(targetObject, convertedArgs[0]);
                    }
                    catch (Exception e2) {
                        throw e2;
                    }
                    finally {
                        trans.getTraceManager().fireExtensionEndEvent(m, targetObject, convertedArgs[0]);
                    }
                    return result;
                }
                return m.invoke(targetObject, convertedArgs[0]);
            }
        }
        catch (InvocationTargetException ite2) {
            Throwable resultException = ite2;
            final Throwable targetException = ite2.getTargetException();
            if (targetException instanceof TransformerException) {
                throw (TransformerException)targetException;
            }
            if (targetException != null) {
                resultException = targetException;
            }
            throw new TransformerException(resultException);
        }
        catch (Exception e3) {
            throw new TransformerException(e3);
        }
    }
    
    public Object callFunction(final FuncExtFunction extFunction, final Vector args, final ExpressionContext exprContext) throws TransformerException {
        return this.callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
    }
    
    public void processElement(String localPart, final ElemTemplateElement element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        Method m = (Method)this.getFromCache(methodKey, null, null);
        if (null == m) {
            try {
                final String fullName = super.m_className + localPart;
                final int lastDot = fullName.lastIndexOf(".");
                if (lastDot < 0) {
                    throw new TransformerException(XSLMessages.createMessage("ER_INVALID_ELEMENT_NAME", new Object[] { fullName }));
                }
                Class classObj;
                try {
                    classObj = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
                }
                catch (ClassNotFoundException e) {
                    throw new TransformerException(e);
                }
                localPart = fullName.substring(lastDot + 1);
                m = MethodResolver.getElementMethod(classObj, localPart);
                if (!Modifier.isStatic(m.getModifiers())) {
                    throw new TransformerException(XSLMessages.createMessage("ER_ELEMENT_NAME_METHOD_STATIC", new Object[] { fullName }));
                }
            }
            catch (Exception e2) {
                throw new TransformerException(e2);
            }
            this.putToCache(methodKey, null, null, m);
        }
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireExtensionEvent(m, null, new Object[] { xpc, element });
                try {
                    result = m.invoke(null, xpc, element);
                }
                catch (Exception e3) {
                    throw e3;
                }
                finally {
                    transformer.getTraceManager().fireExtensionEndEvent(m, null, new Object[] { xpc, element });
                }
            }
            else {
                result = m.invoke(null, xpc, element);
            }
        }
        catch (InvocationTargetException ite) {
            Throwable resultException = ite;
            final Throwable targetException = ite.getTargetException();
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
