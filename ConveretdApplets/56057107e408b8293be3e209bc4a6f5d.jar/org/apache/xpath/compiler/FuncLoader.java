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
    
    public FuncLoader(final String funcName, final int funcID) {
        this.m_funcID = funcID;
        this.m_funcName = funcName;
    }
    
    public Function getFunction() throws TransformerException {
        try {
            Class function;
            if (this.m_funcName.indexOf(".") < 0) {
                final String classname = "org.apache.xpath.functions." + this.m_funcName;
                function = Class.forName(classname);
            }
            else {
                function = Class.forName(this.m_funcName);
            }
            final Function func = function.newInstance();
            return func;
        }
        catch (ClassNotFoundException e) {
            throw new TransformerException(e);
        }
        catch (IllegalAccessException e2) {
            throw new TransformerException(e2);
        }
        catch (InstantiationException e3) {
            throw new TransformerException(e3);
        }
    }
    
    public String getName() {
        return this.m_funcName;
    }
}
