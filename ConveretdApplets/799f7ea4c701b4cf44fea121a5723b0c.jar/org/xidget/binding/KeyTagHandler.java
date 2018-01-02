// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.IXidget;
import org.xmodel.PathSyntaxException;
import org.xmodel.xaction.IXAction;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.XPath;
import org.xmodel.Xlate;
import org.xidget.ifeature.IKeyFeature;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class KeyTagHandler extends AbstractTagHandler
{
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
        if (xidget.getFeature(IKeyFeature.class) == null) {
            throw new TagException(String.format("Key bindings not supported by xidget, %s.", xidget.getConfig().getType()));
        }
        final boolean value = Xlate.get(modelObject, "override", true);
        final String value2 = Xlate.get(modelObject, "keys", "");
        if (value2.length() == 0) {
            return;
        }
        try {
            final IExpression compileExpression = XPath.compileExpression(value2);
            final XActionDocument xActionDocument = new XActionDocument(modelObject);
            xActionDocument.addPackage("org.xidget.xaction");
            xActionDocument.addPackage("org.xidget.layout.xaction");
            xActionDocument.setClassLoader(tagProcessor.getClassLoader());
            xidget.getFeature(IBindFeature.class).addBindingAfterChildren(new KeyBinding(compileExpression, xidget, value, xActionDocument.createScript(new String[0])));
        }
        catch (PathSyntaxException ex) {
            throw new TagException(String.format("Error in expression: %s", modelObject), ex);
        }
    }
    
    private class KeyBinding implements IXidgetBinding
    {
        private IExpression expression;
        private Listener listener;
        
        public KeyBinding(final IExpression expression, final IXidget xidget, final boolean b, final IXAction ixAction) {
            this.expression = expression;
            this.listener = new Listener(xidget, b, ixAction);
        }
        
        @Override
        public void bind(final StatefulContext statefulContext) {
            this.expression.addNotifyListener(statefulContext, this.listener);
        }
        
        @Override
        public void unbind(final StatefulContext statefulContext) {
            this.expression.removeNotifyListener(statefulContext, this.listener);
        }
    }
    
    private class Listener extends ExpressionListener
    {
        private IXidget xidget;
        private boolean override;
        private IXAction script;
        
        public Listener(final IXidget xidget, final boolean override, final IXAction script) {
            this.xidget = xidget;
            this.override = override;
            this.script = script;
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, String s, String s2) {
            if (s2 == null) {
                s2 = "";
            }
            if (s == null) {
                s = "";
            }
            final IKeyFeature keyFeature = this.xidget.getFeature(IKeyFeature.class);
            if (!s2.equals("")) {
                keyFeature.unbind(s2, this.script);
            }
            if (!s.equals("")) {
                keyFeature.bind(s, this.override, this.script);
            }
        }
    }
}
