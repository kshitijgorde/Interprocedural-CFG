// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.compiler;

import javax.xml.transform.TransformerException;
import org.apache.xpath.functions.Function;

public class FuncLoader
{
    private int m_funcID;
    private String m_funcName;
    
    public String getName() {
        return this.m_funcName;
    }
    
    public FuncLoader(final String funcName, final int funcID) {
        this.m_funcID = funcID;
        this.m_funcName = funcName;
    }
    
    public Function getFunction() throws TransformerException {
        try {
            String className = this.m_funcName;
            if (className.indexOf(".") < 0) {
                className = "org.apache.xpath.functions." + className;
            }
            return (Function)ObjectFactory.newInstance(className, ObjectFactory.findClassLoader(), true);
        }
        catch (ObjectFactory.ConfigurationError e) {
            throw new TransformerException(e.getException());
        }
    }
}
