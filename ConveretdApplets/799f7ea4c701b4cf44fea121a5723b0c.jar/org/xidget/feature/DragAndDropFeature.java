// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature;

import org.xidget.ifeature.IScriptFeature;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.IXidget;
import org.xidget.ifeature.IDragAndDropFeature;

public class DragAndDropFeature implements IDragAndDropFeature
{
    protected IXidget xidget;
    private IExpression dragExpr;
    private IExpression dropExpr;
    
    public DragAndDropFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public boolean isDragEnabled() {
        return this.xidget.getConfig().getFirstChild("onDrag") != null;
    }
    
    @Override
    public boolean isDropEnabled() {
        return this.xidget.getConfig().getFirstChild("onDrop") != null;
    }
    
    @Override
    public boolean canDrag(final StatefulContext statefulContext) {
        if (this.dragExpr == null) {
            this.dragExpr = XPath.createExpression(Xlate.get(this.xidget.getConfig().getFirstChild("onDrag"), "when", "true()"));
        }
        return this.dragExpr.evaluateBoolean(statefulContext);
    }
    
    @Override
    public boolean canDrop(final StatefulContext statefulContext) {
        if (this.dropExpr == null) {
            this.dropExpr = XPath.createExpression(Xlate.get(this.xidget.getConfig().getFirstChild("onDrop"), "when", "true()"));
        }
        return this.dropExpr.evaluateBoolean(statefulContext);
    }
    
    @Override
    public void drag(final StatefulContext statefulContext) {
        this.xidget.getFeature(IScriptFeature.class).runScript("onDrag", statefulContext);
    }
    
    @Override
    public void drop(final StatefulContext statefulContext) {
        this.xidget.getFeature(IScriptFeature.class).runScript("onDrop", statefulContext);
    }
}
