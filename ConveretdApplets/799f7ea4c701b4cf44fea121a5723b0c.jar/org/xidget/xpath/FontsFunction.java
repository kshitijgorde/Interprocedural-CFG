// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.ModelObject;
import java.util.ArrayList;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.function.Function;

public class FontsFunction extends Function
{
    public static final String name = "xi:fonts";
    private List<IModelObject> result;
    
    @Override
    public String getName() {
        return "xi:fonts";
    }
    
    @Override
    public ResultType getType() {
        return ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(0, 0);
        if (this.result != null) {
            return this.result;
        }
        final List<String> fonts = Creator.getToolkit().getFonts();
        this.result = new ArrayList<IModelObject>(fonts.size());
        for (final String value : fonts) {
            final ModelObject modelObject = new ModelObject("font");
            modelObject.setValue(value);
            this.result.add(modelObject);
        }
        return this.result;
    }
}
