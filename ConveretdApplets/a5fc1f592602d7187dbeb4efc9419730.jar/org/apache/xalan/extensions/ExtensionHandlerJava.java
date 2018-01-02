// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.util.Hashtable;

public abstract class ExtensionHandlerJava extends ExtensionHandler
{
    protected String m_className;
    private Hashtable m_cachedMethods;
    
    protected ExtensionHandlerJava(final String namespaceUri, final String scriptLang, final String className) {
        super(namespaceUri, scriptLang);
        this.m_className = "";
        this.m_cachedMethods = new Hashtable();
        this.m_className = className;
    }
    
    public Object getFromCache(final Object methodKey, final Object objType, final Object[] methodArgs) {
        return this.m_cachedMethods.get(methodKey);
    }
    
    public Object putToCache(final Object methodKey, final Object objType, final Object[] methodArgs, final Object methodObj) {
        return this.m_cachedMethods.put(methodKey, methodObj);
    }
}
