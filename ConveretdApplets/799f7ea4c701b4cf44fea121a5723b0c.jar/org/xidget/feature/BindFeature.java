// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature;

import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.Iterator;
import org.xmodel.Xlate;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.Log;
import java.util.Arrays;
import java.util.ArrayList;
import org.xidget.binding.IXidgetBinding;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.List;
import org.xidget.IXidget;
import org.xidget.ifeature.IBindFeature;

public class BindFeature implements IBindFeature
{
    protected IXidget xidget;
    protected List<StatefulContext> contexts;
    protected List<IXidgetBinding> bindBeforeChildren;
    protected List<IXidgetBinding> bindAfterChildren;
    private List<String> ignore;
    
    public BindFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.contexts = new ArrayList<StatefulContext>(1);
    }
    
    public BindFeature(final IXidget xidget, final String[] array) {
        this(xidget);
        this.ignore = Arrays.asList(array);
    }
    
    @Override
    public void addBindingBeforeChildren(final IXidgetBinding xidgetBinding) {
        if (this.bindBeforeChildren == null) {
            this.bindBeforeChildren = new ArrayList<IXidgetBinding>();
        }
        this.bindBeforeChildren.add(xidgetBinding);
    }
    
    @Override
    public void addBindingAfterChildren(final IXidgetBinding xidgetBinding) {
        if (this.bindAfterChildren == null) {
            this.bindAfterChildren = new ArrayList<IXidgetBinding>();
        }
        this.bindAfterChildren.add(xidgetBinding);
    }
    
    @Override
    public void remove(final IXidgetBinding xidgetBinding) {
        if (this.bindAfterChildren != null) {
            this.bindAfterChildren.remove(xidgetBinding);
        }
    }
    
    @Override
    public void bind(final StatefulContext statefulContext) {
        Log.printf("xidget", "bind: %s with %s\n", this.xidget, statefulContext);
        this.contexts.add(statefulContext);
        final IWidgetContextFeature widgetContextFeature = this.xidget.getFeature(IWidgetContextFeature.class);
        final IWidgetCreationFeature widgetCreationFeature = this.xidget.getFeature(IWidgetCreationFeature.class);
        if (widgetContextFeature != null && widgetCreationFeature != null) {
            final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
            if (lastWidgets != null) {
                Object[] array;
                for (int length = (array = lastWidgets).length, i = 0; i < length; ++i) {
                    widgetContextFeature.createAssociation(array[i], statefulContext);
                }
            }
        }
        final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
        if (scriptFeature != null) {
            scriptFeature.runScript("onOpen", statefulContext);
        }
        if (this.bindBeforeChildren != null) {
            final Iterator<IXidgetBinding> iterator = this.bindBeforeChildren.iterator();
            while (iterator.hasNext()) {
                iterator.next().bind(statefulContext);
            }
        }
        for (final IXidget xidget : this.xidget.getChildren()) {
            final IModelObject config = xidget.getConfig();
            if (!this.shouldIgnore(config) && Xlate.get(config, "context", Xlate.childGet(config, "context", (String)null)) == null) {
                xidget.getFeature(IBindFeature.class).bind(statefulContext);
            }
        }
        if (this.bindAfterChildren != null) {
            final Iterator<IXidgetBinding> iterator3 = this.bindAfterChildren.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().bind(statefulContext);
            }
        }
    }
    
    @Override
    public void unbind(final StatefulContext statefulContext) {
        Log.printf("xidget", "unbind: %s with %s\n", this.xidget, statefulContext);
        final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
        if (scriptFeature != null) {
            scriptFeature.runScript("onClose", statefulContext);
        }
        if (this.bindBeforeChildren != null) {
            final Iterator<IXidgetBinding> iterator = this.bindBeforeChildren.iterator();
            while (iterator.hasNext()) {
                iterator.next().unbind(statefulContext);
            }
        }
        final Iterator<IXidget> iterator2 = this.xidget.getChildren().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().getFeature(IBindFeature.class).unbind(statefulContext);
        }
        if (this.bindAfterChildren != null) {
            final Iterator<IXidgetBinding> iterator3 = this.bindAfterChildren.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().unbind(statefulContext);
            }
        }
        this.contexts.remove(statefulContext);
        final IWidgetContextFeature widgetContextFeature = this.xidget.getFeature(IWidgetContextFeature.class);
        final IWidgetCreationFeature widgetCreationFeature = this.xidget.getFeature(IWidgetCreationFeature.class);
        if (widgetContextFeature != null && widgetCreationFeature != null) {
            final Object[] lastWidgets = widgetCreationFeature.getLastWidgets();
            if (lastWidgets != null) {
                Object[] array;
                for (int length = (array = lastWidgets).length, i = 0; i < length; ++i) {
                    widgetContextFeature.removeAssociation(array[i]);
                }
            }
        }
        final String value = Xlate.get(this.xidget.getConfig(), "assign", (String)null);
        if (value != null) {
            statefulContext.set(value, Collections.emptyList());
        }
    }
    
    protected boolean shouldIgnore(final IModelObject modelObject) {
        return this.ignore != null && this.ignore.contains(modelObject.getType());
    }
    
    @Override
    public List<StatefulContext> getBoundContexts() {
        return this.contexts;
    }
    
    @Override
    public StatefulContext getBoundContext() {
        if (this.contexts.size() > 1) {
            throw new IllegalStateException("More than one context is bound to: " + this.xidget);
        }
        return (this.contexts.size() > 0) ? this.contexts.get(0) : null;
    }
}
