// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import java.lang.reflect.Modifier;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Element;
import java.lang.reflect.InvocationTargetException;
import org.apache.xpath.objects.XObject;
import java.lang.reflect.Method;
import javax.xml.transform.TransformerException;
import java.lang.reflect.Constructor;
import java.util.Vector;

public class ExtensionHandlerJavaPackage extends ExtensionHandlerJava
{
    static /* synthetic */ Class class$org$apache$xalan$extensions$XSLProcessorContext;
    static /* synthetic */ Class class$org$apache$xalan$templates$ElemExtensionCall;
    
    public ExtensionHandlerJavaPackage(final String namespaceUri, final String scriptLang, final String className) {
        super(namespaceUri, scriptLang, className);
    }
    
    public Object callFunction(final String funcName, final Vector args, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        final int lastDot = funcName.lastIndexOf(".");
        try {
            if (funcName.endsWith(".new")) {
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
                final String className = String.valueOf(super.m_className) + funcName.substring(0, lastDot);
                Class classObj;
                try {
                    classObj = ExtensionHandler.getClassForName(className);
                }
                catch (ClassNotFoundException e) {
                    throw new TransformerException(e);
                }
                c = MethodResolver.getConstructor(classObj, methodArgs, convertedArgs, exprContext);
                this.putToCache(methodKey, null, methodArgs, c);
                return c.newInstance(convertedArgs[0]);
            }
            if (lastDot != -1) {
                final Object[] methodArgs = new Object[args.size()];
                final Object[][] convertedArgs = new Object[1][];
                for (int i = 0; i < methodArgs.length; ++i) {
                    methodArgs[i] = args.elementAt(i);
                }
                Method m = (Method)this.getFromCache(methodKey, null, methodArgs);
                if (m != null) {
                    try {
                        final Class[] paramTypes = m.getParameterTypes();
                        MethodResolver.convertParams(methodArgs, convertedArgs, paramTypes, exprContext);
                        return m.invoke(null, convertedArgs[0]);
                    }
                    catch (Exception ex2) {}
                }
                final String className = String.valueOf(super.m_className) + funcName.substring(0, lastDot);
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
                return m.invoke(null, convertedArgs[0]);
            }
            if (args.size() < 1) {
                throw new TransformerException("Instance method call to method " + funcName + " requires an Object instance as first argument");
            }
            Object targetObject = args.elementAt(0);
            if (targetObject instanceof XObject) {
                targetObject = ((XObject)targetObject).object();
            }
            final Object[] methodArgs = new Object[args.size() - 1];
            final Object[][] convertedArgs = new Object[1][];
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
                catch (Exception ex3) {}
            }
            Class classObj = targetObject.getClass();
            m = MethodResolver.getMethod(classObj, funcName, methodArgs, convertedArgs, exprContext, 2);
            this.putToCache(methodKey, targetObject, methodArgs, m);
            return m.invoke(targetObject, convertedArgs[0]);
        }
        catch (InvocationTargetException ite) {
            final Throwable realException = ite.getTargetException();
            if (realException instanceof Exception) {
                throw new TransformerException(realException);
            }
            throw new TransformerException(ite);
        }
        catch (Exception e2) {
            throw new TransformerException(e2);
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
    
    public boolean isElementAvailable(String element) {
        try {
            final String fullName = String.valueOf(super.m_className) + element;
            final int lastDot = fullName.lastIndexOf(".");
            if (lastDot >= 0) {
                final Class myClass = ExtensionHandler.getClassForName(fullName.substring(0, lastDot));
                final Method[] methods = myClass.getMethods();
                final int nMethods = methods.length;
                element = fullName.substring(lastDot + 1);
                for (int i = 0; i < nMethods; ++i) {
                    if (methods[i].getName().equals(element)) {
                        final Class[] paramTypes = methods[i].getParameterTypes();
                        if (paramTypes.length == 2 && paramTypes[0].isAssignableFrom((ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext != null) ? ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext : (ExtensionHandlerJavaPackage.class$org$apache$xalan$extensions$XSLProcessorContext = class$("org.apache.xalan.extensions.XSLProcessorContext"))) && paramTypes[1].isAssignableFrom((ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall != null) ? ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall : (ExtensionHandlerJavaPackage.class$org$apache$xalan$templates$ElemExtensionCall = class$("org.apache.xalan.templates.ElemExtensionCall")))) {
                            return true;
                        }
                    }
                }
            }
        }
        catch (ClassNotFoundException ex) {}
        return false;
    }
    
    public boolean isFunctionAvailable(String function) {
        try {
            final String fullName = String.valueOf(super.m_className) + function;
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
    
    public void processElement(String localPart, final Element element, final TransformerImpl transformer, final Stylesheet stylesheetTree, final Node sourceTree, final Node sourceNode, final QName mode, final Object methodKey) throws TransformerException, IOException {
        Object result = null;
        Method m = (Method)this.getFromCache(methodKey, null, null);
        if (m == null) {
            try {
                final String fullName = String.valueOf(super.m_className) + localPart;
                final int lastDot = fullName.lastIndexOf(".");
                if (lastDot < 0) {
                    throw new TransformerException("Invalid element name specified " + fullName);
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
                    throw new TransformerException("Element name method must be static " + fullName);
                }
            }
            catch (Exception e3) {
                throw new TransformerException(e3);
            }
            this.putToCache(methodKey, null, null, m);
        }
        final XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree, sourceTree, sourceNode, mode);
        try {
            result = m.invoke(null, xpc, element);
        }
        catch (Exception e2) {
            throw new TransformerException(e2);
        }
        if (result != null) {
            xpc.outputToResultTree(stylesheetTree, result);
        }
    }
}
