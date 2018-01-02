// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.IXidget;
import org.xmodel.PathSyntaxException;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class BindingTagHandler extends AbstractTagHandler
{
    private IBindingRule rule;
    private boolean beforeChildren;
    
    public BindingTagHandler(final IBindingRule bindingRule) {
        this(bindingRule, false);
    }
    
    public BindingTagHandler(final IBindingRule rule, final boolean beforeChildren) {
        this.rule = rule;
        this.beforeChildren = beforeChildren;
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        this.bind(tagProcessor, tagHandler, modelObject);
        return false;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    private void bind(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        final IXidget xidget = xidgetFeature.getXidget();
        if (!this.rule.applies(xidget, modelObject)) {
            return;
        }
        final String value = Xlate.get(modelObject, "");
        if (value.length() == 0) {
            return;
        }
        try {
            final XidgetBinding xidgetBinding = new XidgetBinding(XPath.compileExpression(value), this.rule.getListener(tagProcessor, xidget, modelObject));
            final IBindFeature bindFeature = xidget.getFeature(IBindFeature.class);
            if (this.beforeChildren) {
                bindFeature.addBindingBeforeChildren(xidgetBinding);
            }
            else {
                bindFeature.addBindingAfterChildren(xidgetBinding);
            }
        }
        catch (PathSyntaxException ex) {
            throw new TagException(String.format("Error in expression: %s", modelObject), ex);
        }
    }
}
