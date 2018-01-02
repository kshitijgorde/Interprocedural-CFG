// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.lang.reflect.Constructor;
import javax.xml.transform.TransformerException;

public class ExtensionNamespaceSupport
{
    String m_namespace;
    String m_handlerClass;
    Class[] m_sig;
    Object[] m_args;
    
    public ExtensionNamespaceSupport(final String namespace, final String handlerClass, final Object[] constructorArgs) {
        this.m_namespace = null;
        this.m_handlerClass = null;
        this.m_sig = null;
        this.m_args = null;
        this.m_namespace = namespace;
        this.m_handlerClass = handlerClass;
        this.m_args = constructorArgs;
        this.m_sig = new Class[this.m_args.length];
        for (int i = 0; i < this.m_args.length; ++i) {
            if (this.m_args[i] == null) {
                this.m_sig = null;
                break;
            }
            this.m_sig[i] = this.m_args[i].getClass();
        }
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public ExtensionHandler launch() throws TransformerException {
        ExtensionHandler handler = null;
        try {
            final Class cl = ExtensionHandler.getClassForName(this.m_handlerClass);
            Constructor con = null;
            if (this.m_sig != null) {
                con = cl.getConstructor((Class[])this.m_sig);
            }
            else {
                final Constructor[] cons = cl.getConstructors();
                for (int i = 0; i < cons.length; ++i) {
                    if (cons[i].getParameterTypes().length == this.m_args.length) {
                        con = cons[i];
                        break;
                    }
                }
            }
            if (con == null) {
                throw new TransformerException("ExtensionHandler constructor not found");
            }
            handler = con.newInstance(this.m_args);
        }
        catch (Exception e) {
            throw new TransformerException(e);
        }
        return handler;
    }
}
