// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.ModelObject;
import java.util.ArrayList;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.Creator;
import java.util.Collections;
import org.xidget.IToolkit;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xaction.Conventions;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class OpenFileDialogAction extends GuardedAction
{
    private IExpression targetExpr;
    private IExpression folderExpr;
    private IExpression filterExpr;
    private IExpression descExpr;
    private IExpression typeExpr;
    private String var;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.var = Conventions.getVarName(xActionDocument.getRoot(), false, "assign");
        this.typeExpr = xActionDocument.getExpression("type", true);
        if (this.typeExpr == null) {
            this.typeExpr = XPath.createExpression("'openOne'");
        }
        this.folderExpr = xActionDocument.getExpression("folder", true);
        this.filterExpr = xActionDocument.getExpression("filter", true);
        this.descExpr = xActionDocument.getExpression("description", true);
        this.targetExpr = xActionDocument.getExpression("target", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IToolkit.FileDialogType value = IToolkit.FileDialogType.valueOf(this.typeExpr.evaluateString(context));
        final String s = (this.descExpr != null) ? this.descExpr.evaluateString(context) : "";
        if (this.var != null) {
            final IVariableScope scope = context.getScope();
            if (scope != null) {
                scope.set(this.var, Collections.emptyList());
            }
        }
        final String[] openFileDialog = Creator.getToolkit().openFileDialog((StatefulContext)context, this.folderExpr, this.filterExpr, s, value);
        if (value == IToolkit.FileDialogType.openMany) {
            final ArrayList<ModelObject> list = new ArrayList<ModelObject>();
            String[] array;
            for (int length = (array = openFileDialog).length, i = 0; i < length; ++i) {
                final String value2 = array[i];
                final ModelObject modelObject = new ModelObject("path");
                modelObject.setValue(value2);
                list.add(modelObject);
            }
            if (this.targetExpr != null) {
                final IModelObject queryFirst = this.targetExpr.queryFirst(context);
                if (queryFirst != null) {
                    queryFirst.removeChildren("path");
                    final Iterator<Object> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        queryFirst.addChild(iterator.next());
                    }
                }
            }
            if (this.var != null) {
                final IVariableScope scope2 = context.getScope();
                if (scope2 != null) {
                    scope2.set(this.var, (List<IModelObject>)list);
                }
            }
        }
        else {
            if (this.targetExpr != null) {
                final IModelObject queryFirst2 = this.targetExpr.queryFirst(context);
                if (queryFirst2 != null) {
                    queryFirst2.setValue((openFileDialog.length > 0) ? openFileDialog[0] : "");
                }
            }
            if (this.var != null) {
                final IVariableScope scope3 = context.getScope();
                if (scope3 != null) {
                    scope3.set(this.var, (openFileDialog.length > 0) ? openFileDialog[0] : "");
                }
            }
        }
        return null;
    }
}
