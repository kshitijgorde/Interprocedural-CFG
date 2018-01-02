// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.net.MalformedURLException;
import org.xmodel.external.CachingException;
import java.net.URISyntaxException;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Collections;
import java.net.URI;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xml.XmlIO;
import org.xmodel.xml.IXmlIO;

public class DocFunction extends Function
{
    public static final String name = "doc";
    private IXmlIO y;
    
    public DocFunction() {
        this.y = new XmlIO();
    }
    
    @Override
    public String getName() {
        return "doc";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        try {
            final List<IModelObject> a = this.A(new URI(this.getArgument(0).evaluateString(context)));
            if (a != null) {
                return a;
            }
            return Collections.emptyList();
        }
        catch (URISyntaxException ex) {
            throw new ExpressionException(this, "Unable to evaluate doc function:", ex);
        }
        catch (CachingException ex2) {
            throw new ExpressionException(this, "Unable to evaluate doc function:", ex2);
        }
    }
    
    private List<IModelObject> A(final URI uri) throws ExpressionException {
        try {
            return Collections.singletonList(this.y.read(uri.toURL().openStream()));
        }
        catch (MalformedURLException ex) {
            throw new ExpressionException(this, String.format("Unable to perform query, %s.", this), ex);
        }
        catch (Exception ex2) {
            return Collections.emptyList();
        }
    }
}
