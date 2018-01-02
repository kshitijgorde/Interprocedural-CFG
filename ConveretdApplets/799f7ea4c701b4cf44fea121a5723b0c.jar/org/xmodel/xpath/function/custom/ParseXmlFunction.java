// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.IChangeSet;
import org.xmodel.xml.XmlException;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xml.XmlIO;
import org.xmodel.xpath.function.Function;

public class ParseXmlFunction extends Function
{
    public static final String name = "parse-xml";
    XmlIO o;
    XmlDiffer n;
    
    public ParseXmlFunction() {
        this.o = new XmlIO();
        this.n = new XmlDiffer();
    }
    
    @Override
    public String getName() {
        return "parse-xml";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        try {
            return Collections.singletonList(this.o.read(this.getArgument(0).evaluateString(context)));
        }
        catch (XmlException ex) {
            throw new ExpressionException(this, "XML parsing error: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        try {
            final IModelObject read = this.o.read(s2);
            final IModelObject read2 = this.o.read(s);
            if (this.n.diff(read, read2, null)) {
                this.getParent().notifyRemove(this, context, Collections.singletonList(read));
                this.getParent().notifyAdd(this, context, Collections.singletonList(read2));
            }
        }
        catch (XmlException ex) {
            this.getParent().handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        try {
            context.getModel().revert();
            final String evaluateString = expression.evaluateString(context);
            context.getModel().restore();
            this.notifyChange(expression, context, expression.evaluateString(context), evaluateString);
        }
        catch (ExpressionException ex) {
            this.getParent().handleException(this, context, ex);
        }
    }
}
