// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.config.TagException;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import java.util.Stack;
import org.xidget.IXidget;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xidget.config.AbstractTagHandler;

public class XidgetTagHandler extends AbstractTagHandler implements IXidgetFeature
{
    private Class<? extends IXidget> xidgetClass;
    private Stack<IXidget> xidgets;
    private String styleAttribute;
    private String styleValue;
    
    public XidgetTagHandler(final Class<? extends IXidget> clazz) {
        this(clazz, null, null);
    }
    
    public XidgetTagHandler(final Class<? extends IXidget> xidgetClass, final String styleAttribute, final String styleValue) {
        this.xidgetClass = xidgetClass;
        this.xidgets = new Stack<IXidget>();
        this.styleAttribute = styleAttribute;
        this.styleValue = styleValue;
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return this.styleAttribute == null || Xlate.get(modelObject, this.styleAttribute, "").equals(this.styleValue);
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = (tagHandler != null) ? tagHandler.getFeature(IXidgetFeature.class) : null;
        final IXidget xidget = (xidgetFeature != null) ? xidgetFeature.getXidget() : null;
        try {
            final IXidget xidget2 = (IXidget)this.xidgetClass.newInstance();
            this.xidgets.push(xidget2);
            return xidget2.startConfig(tagProcessor, xidget, modelObject);
        }
        catch (InstantiationException ex) {
            throw new TagException("Unable to create xidget of class: " + this.xidgetClass, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new TagException("Access denied for xidget class: " + this.xidgetClass, ex2);
        }
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidget xidget = this.xidgets.pop();
        xidget.endConfig(tagProcessor, modelObject);
        if (tagHandler == null) {
            tagProcessor.addRoot(xidget);
        }
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IXidgetFeature.class) {
            return (T)this;
        }
        return super.getFeature(clazz);
    }
    
    @Override
    public IXidget getXidget() {
        return this.xidgets.peek();
    }
}
