// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import org.xmodel.ModelAlgorithms;
import org.xidget.config.TagException;
import org.xidget.config.TagProcessor;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public abstract class Xidget implements IXidget
{
    private IXidget parent;
    private List<IXidget> children;
    private IModelObject config;
    
    protected abstract void createFeatures();
    
    private final void setParent(final IXidget parent) {
        this.parent = parent;
    }
    
    @Override
    public final IXidget getParent() {
        return this.parent;
    }
    
    @Override
    public final List<IXidget> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<IXidget>();
        }
        return this.children;
    }
    
    @Override
    public void addChild(final IXidget xidget) {
        ((Xidget)xidget).setParent(this);
        this.getChildren().add(xidget);
    }
    
    @Override
    public void addChild(final int n, final IXidget xidget) {
        throw new InternalError("Badly shrinked");
    }
    
    @Override
    public void removeChild(final IXidget xidget) {
        throw new InternalError("Badly shrinked");
    }
    
    @Override
    public boolean startConfig(final TagProcessor tagProcessor, final IXidget xidget, final IModelObject config) throws TagException {
        if (xidget != null) {
            xidget.addChild(this);
        }
        this.config = config;
        this.createFeatures();
        return true;
    }
    
    @Override
    public final void endConfig(final TagProcessor tagProcessor, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public final IModelObject getConfig() {
        return this.config;
    }
    
    @Override
    public final void createWidget() {
        throw new InternalError("Badly shrinked");
    }
    
    @Override
    public final void destroyWidget() {
        throw new InternalError("Badly shrinked");
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        Log.printf("xidget", "Feature '%s' not found on xidget %s.\n", clazz.getSimpleName(), this.getConfig());
        return null;
    }
    
    @Override
    public String toString() {
        if (this.config == null) {
            return "unconfigured";
        }
        final StringBuilder sb = new StringBuilder();
        if (this.config.getID().length() > 0) {
            sb.append(this.config.getType());
            sb.append("#");
            sb.append(this.config.getID());
        }
        else {
            sb.append(ModelAlgorithms.createIdentityPath(this.config));
        }
        return sb.toString();
    }
}
