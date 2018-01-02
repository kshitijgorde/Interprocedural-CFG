// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import java.io.InputStream;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xml.XmlIO;
import java.util.Collections;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class LoadFunction extends Function
{
    public static final String name = "dsp:load";
    private static final IExpression swExpr;
    private static final IExpression hwExpr;
    
    static {
        swExpr = XPath.createExpression("$software");
        hwExpr = XPath.createExpression("$hardware");
    }
    
    @Override
    public String getName() {
        return "dsp:load";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final String name = this.getArgument(0).evaluateString(context);
        final String sw = LoadFunction.swExpr.evaluateString(context);
        final String hw = LoadFunction.hwExpr.evaluateString(context);
        try {
            final Loader loader = new Loader(sw, hw);
            final InputStream is = loader.getResourceAsStream(name);
            if (is == null) {
                return Collections.emptyList();
            }
            final XmlIO io = new XmlIO();
            final IModelObject object = io.read(is);
            if (object == null) {
                return Collections.emptyList();
            }
            object.setAttribute("url", "file:///src/" + loader.getPath(name));
            return Collections.singletonList(object);
        }
        catch (Exception e) {
            throw new ExpressionException(this, "Cannot load file:" + name, e);
        }
    }
}
