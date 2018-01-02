// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import org.xidget.ifeature.IBindFeature;
import java.util.List;
import java.util.Iterator;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import java.util.Collection;
import java.util.HashSet;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISelectionUpdateFeature;

public class SelectionUpdateFeature implements ISelectionUpdateFeature
{
    protected IXidget xidget;
    private boolean updating;
    
    public SelectionUpdateFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void updateWidget() {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final HashSet<Object> set = new HashSet<Object>(this.xidget.getFeature(ISelectionModelFeature.class).getSelection());
            final ISelectionWidgetFeature selectionWidgetFeature = this.xidget.getFeature(ISelectionWidgetFeature.class);
            final HashSet set2 = new HashSet<Object>(selectionWidgetFeature.getSelection());
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onSelect");
            final boolean b2 = context != null && scriptFeature.hasScript("onDeselect");
            for (final Object next : set2) {
                if (!set.contains(next)) {
                    if (b2) {
                        context.getScope().set("value", next);
                        scriptFeature.runScript("onDeselect", context);
                    }
                    selectionWidgetFeature.deselect(next);
                }
            }
            for (final Object next2 : set) {
                if (!set2.contains(next2)) {
                    if (b) {
                        context.getScope().set("value", next2);
                        scriptFeature.runScript("onSelect", context);
                    }
                    selectionWidgetFeature.select(next2);
                }
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void updateModel() {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final HashSet<Object> set = new HashSet<Object>(this.xidget.getFeature(ISelectionWidgetFeature.class).getSelection());
            final ISelectionModelFeature selectionModelFeature = this.xidget.getFeature(ISelectionModelFeature.class);
            final HashSet set2 = new HashSet<Object>(selectionModelFeature.getSelection());
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onSelect");
            final boolean b2 = context != null && scriptFeature.hasScript("onDeselect");
            for (final Object next : set2) {
                if (!set.contains(next)) {
                    if (b2) {
                        context.getScope().set("value", next);
                        scriptFeature.runScript("onDeselect", context);
                    }
                    selectionModelFeature.deselect(next);
                }
            }
            for (final Object next2 : set) {
                if (!set2.contains(next2)) {
                    if (b) {
                        context.getScope().set("value", next2);
                        scriptFeature.runScript("onSelect", context);
                    }
                    selectionModelFeature.select(next2);
                }
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void displaySelect(final List<?> list) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onSelect");
            final ISelectionWidgetFeature selectionWidgetFeature = this.xidget.getFeature(ISelectionWidgetFeature.class);
            for (final Object next : list) {
                if (b) {
                    context.getScope().set("value", next);
                    scriptFeature.runScript("onSelect", context);
                }
                selectionWidgetFeature.select(next);
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void displayDeselect(final List<?> list) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onDeselect");
            final ISelectionWidgetFeature selectionWidgetFeature = this.xidget.getFeature(ISelectionWidgetFeature.class);
            for (final Object next : list) {
                if (b) {
                    context.getScope().set("value", next);
                    scriptFeature.runScript("onDeselect", context);
                }
                selectionWidgetFeature.deselect(next);
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void modelSelect(final List<?> list) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onSelect");
            final ISelectionModelFeature selectionModelFeature = this.xidget.getFeature(ISelectionModelFeature.class);
            for (final Object next : list) {
                if (b) {
                    context.getScope().set("value", next);
                    scriptFeature.runScript("onSelect", context);
                }
                selectionModelFeature.select(next);
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void modelDeselect(final List<?> list) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final StatefulContext context = this.getContext();
            if (context == null) {
                return;
            }
            final IScriptFeature scriptFeature = this.xidget.getFeature(IScriptFeature.class);
            final boolean b = context != null && scriptFeature.hasScript("onDeselect");
            final ISelectionModelFeature selectionModelFeature = this.xidget.getFeature(ISelectionModelFeature.class);
            for (final Object next : list) {
                if (b) {
                    context.getScope().set("value", next);
                    scriptFeature.runScript("onDeselect", context);
                }
                selectionModelFeature.deselect(next);
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    protected StatefulContext getContext() {
        return this.xidget.getFeature(IBindFeature.class).getBoundContext();
    }
}
