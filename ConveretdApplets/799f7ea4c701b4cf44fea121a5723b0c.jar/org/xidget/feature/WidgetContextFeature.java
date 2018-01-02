// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature;

import org.xidget.Log;
import java.util.HashMap;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Map;
import org.xidget.ifeature.IWidgetContextFeature;

public class WidgetContextFeature implements IWidgetContextFeature
{
    private Map<Object, StatefulContext> map;
    
    public WidgetContextFeature() {
        this.map = new HashMap<Object, StatefulContext>();
    }
    
    @Override
    public void createAssociation(final Object o, final StatefulContext statefulContext) {
        this.map.put(o, statefulContext);
    }
    
    @Override
    public void removeAssociation(final Object o) {
        this.map.remove(o);
    }
    
    @Override
    public StatefulContext getContext(final Object o) {
        final StatefulContext statefulContext = this.map.get(o);
        if (statefulContext == null) {
            Log.printf("xidget", "No context associated with widget: %s\n", o);
        }
        return statefulContext;
    }
}
