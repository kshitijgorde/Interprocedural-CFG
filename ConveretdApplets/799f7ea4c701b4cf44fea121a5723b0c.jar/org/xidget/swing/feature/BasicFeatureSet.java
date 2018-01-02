// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import org.xidget.feature.DragAndDropFeature;
import org.xidget.feature.WidgetContextFeature;
import org.xidget.feature.ScriptFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.IKeyFeature;
import org.xidget.ifeature.IDragAndDropFeature;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.IFeatured;

public class BasicFeatureSet implements IFeatured
{
    private IScriptFeature scriptFeature;
    private IWidgetContextFeature contextFeature;
    private IDragAndDropFeature dndFeature;
    private IKeyFeature keyFeature;
    
    public BasicFeatureSet(final IXidget xidget) {
        this.scriptFeature = new ScriptFeature(xidget);
        this.contextFeature = new WidgetContextFeature();
        this.dndFeature = new DragAndDropFeature(xidget);
        this.keyFeature = new KeyFeature(xidget);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IScriptFeature.class) {
            return (T)this.scriptFeature;
        }
        if (clazz == IWidgetContextFeature.class) {
            return (T)this.contextFeature;
        }
        if (clazz == IDragAndDropFeature.class) {
            return (T)this.dndFeature;
        }
        if (clazz == IKeyFeature.class) {
            return (T)this.keyFeature;
        }
        return null;
    }
}
