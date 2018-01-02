// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import org.xmodel.xpath.TextNode;
import org.xmodel.xpath.AttributeNode;
import java.util.List;
import org.xmodel.PathSyntaxException;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.ModelObject;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;

public class QueryProtocol
{
    public static IModelObject buildRequest(final IContext context, final String value) {
        final ModelObject modelObject = new ModelObject("request");
        final IVariableScope scope = context.getScope();
        for (final String s : scope.getVariables()) {
            final ModelObject modelObject2 = new ModelObject("assign");
            modelObject2.setAttribute("name", s);
            modelObject2.setValue(scope.get(s));
            modelObject.addChild(modelObject2);
        }
        modelObject.getCreateChild("query").setValue(value);
        return modelObject;
    }
    
    public static IExpression readRequest(final IModelObject modelObject, final IContext context) throws PathSyntaxException {
        final IVariableScope scope = context.getScope();
        for (final IModelObject modelObject2 : modelObject.getChildren("assignment")) {
            scope.set(Xlate.get(modelObject2, "name", (String)null), modelObject2.getValue());
        }
        return XPath.compileExpression(Xlate.get(modelObject, "query", (String)null));
    }
    
    public static IModelObject buildResponse(final Object o) {
        final ModelObject modelObject = new ModelObject("response");
        if (o instanceof List) {
            final List list = (List)o;
            if (list.size() > 0 && list.get(0) instanceof IModelObject) {
                for (final IModelObject modelObject2 : list) {
                    if (!(modelObject2 instanceof AttributeNode) && !(modelObject2 instanceof TextNode)) {
                        modelObject.addChild(modelObject2);
                    }
                }
                return modelObject;
            }
        }
        modelObject.setAttribute("result", o);
        return modelObject;
    }
    
    public static Object readResponse(final IModelObject modelObject) {
        final Object attribute = modelObject.getAttribute("result");
        if (attribute != null) {
            return attribute;
        }
        return modelObject.getChildren();
    }
}
