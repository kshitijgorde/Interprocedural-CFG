// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import java.util.Collection;
import java.util.ArrayList;
import org.xmodel.Xlate;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.AttributeNode;
import org.xmodel.xpath.TextNode;
import org.xmodel.xpath.expression.IExpression;
import java.util.List;
import org.xmodel.ModelObject;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;

public class ExecutionProtocol
{
    public static IModelObject buildRequest(final IContext context, final String[] array, final IModelObject modelObject) {
        final ModelObject modelObject2 = new ModelObject("request");
        buildScope(context, array, modelObject2);
        modelObject2.addChild(modelObject.cloneTree());
        return modelObject2;
    }
    
    public static IModelObject readRequest(final IModelObject modelObject, final IContext context) {
        readScope(modelObject, context);
        return modelObject.getFirstChild("script");
    }
    
    public static IModelObject buildResponse(final IContext context, final Object[] array) {
        final ModelObject modelObject = new ModelObject("response");
        buildScope(context, null, modelObject);
        if (array != null) {
            final ModelObject modelObject2 = new ModelObject("results");
            modelObject.addChild(modelObject2);
            for (final Object value : array) {
                final ModelObject modelObject3 = new ModelObject("result");
                modelObject3.setValue(value);
                modelObject2.addChild(modelObject3);
            }
        }
        return modelObject;
    }
    
    public static Object[] readResponse(final IModelObject modelObject, final IContext context) {
        readScope(modelObject, context);
        Object[] array = null;
        final IModelObject firstChild = modelObject.getFirstChild("results");
        if (firstChild != null) {
            final List<IModelObject> children = firstChild.getChildren("result");
            array = new Object[children.size()];
            for (int i = 0; i < children.size(); ++i) {
                array[i] = children.get(i).getValue();
            }
        }
        return array;
    }
    
    public static void buildScope(final IContext context, String[] array, final IModelObject modelObject) {
        final IVariableScope scope = context.getScope();
        if (array == null) {
            array = scope.getVariables().toArray(new String[0]);
        }
        String[] array2;
        for (int length = (array2 = array).length, i = 0; i < length; ++i) {
            final String s = array2[i];
            final ModelObject modelObject2 = new ModelObject("assign");
            modelObject2.setAttribute("name", s);
            final Object value = scope.get(s);
            if (value != null) {
                if (scope.getType(s, context) == IExpression.ResultType.NODES) {
                    for (final IModelObject modelObject3 : (List<IModelObject>)value) {
                        if (modelObject3 instanceof TextNode || modelObject3 instanceof AttributeNode) {
                            modelObject2.setValue(modelObject3.getValue());
                        }
                        else {
                            modelObject2.addChild(modelObject3.cloneTree());
                        }
                    }
                }
                else {
                    modelObject2.setValue(value);
                }
                modelObject.addChild(modelObject2);
            }
        }
    }
    
    public static void readScope(final IModelObject modelObject, final IContext context) {
        final IVariableScope scope = context.getScope();
        for (final IModelObject modelObject2 : modelObject.getChildren("assign")) {
            final String value = Xlate.get(modelObject2, "name", (String)null);
            if (modelObject2.getNumberOfChildren() > 0) {
                scope.set(value, new ArrayList<IModelObject>(modelObject2.getChildren()));
                modelObject2.removeChildren();
            }
            else {
                scope.set(value, modelObject2.getValue());
            }
        }
    }
}
