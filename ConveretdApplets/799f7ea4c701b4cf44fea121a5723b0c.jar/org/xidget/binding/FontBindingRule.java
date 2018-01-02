// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xidget.ifeature.ITextWidgetFeature;
import java.util.EnumSet;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class FontBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(IWidgetFeature.class) != null;
    }
    
    @Override
    public IExpressionListener getListener(final TagProcessor tagProcessor, final IXidget xidget, final IModelObject modelObject) {
        return new Listener(xidget);
    }
    
    static EnumSet<ITextWidgetFeature.FontStyle> parseStyles(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final boolean contains = lowerCase.contains("italic");
        final boolean contains2 = lowerCase.contains("bold");
        if (contains) {
            return contains2 ? EnumSet.of(ITextWidgetFeature.FontStyle.italic, ITextWidgetFeature.FontStyle.bold) : EnumSet.of(ITextWidgetFeature.FontStyle.italic);
        }
        if (contains2) {
            return EnumSet.of(ITextWidgetFeature.FontStyle.bold);
        }
        return EnumSet.of(ITextWidgetFeature.FontStyle.plain);
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        private IModelObject node;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = list.get(0);
            this.setFont(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            if (this.node != null) {
                this.setFont(Xlate.get(this.node, ""));
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String font, final String s) {
            this.setFont(font);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.setFont(Xlate.get(modelObject, ""));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private void setFont(final String s) {
            final ITextWidgetFeature textWidgetFeature = this.xidget.getFeature(ITextWidgetFeature.class);
            if (textWidgetFeature != null) {
                final String[] split = s.split("\\s*,\\s*");
                if (split.length > 0) {
                    textWidgetFeature.setFontFamily(split[0]);
                }
                if (split.length > 1) {
                    textWidgetFeature.setFontSize(Double.parseDouble(split[1]));
                }
                if (split.length > 2) {
                    textWidgetFeature.setFontStyles(FontBindingRule.parseStyles(split[2]));
                }
            }
        }
    }
}
