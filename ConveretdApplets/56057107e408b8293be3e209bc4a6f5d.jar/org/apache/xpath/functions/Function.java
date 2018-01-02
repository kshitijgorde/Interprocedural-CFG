// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;

public class Function extends Expression
{
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 0) {
            throw new WrongNumberArgsException("0");
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        System.out.println("Error! Function.execute should not be called!");
        return null;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        throw new WrongNumberArgsException("0");
    }
}
