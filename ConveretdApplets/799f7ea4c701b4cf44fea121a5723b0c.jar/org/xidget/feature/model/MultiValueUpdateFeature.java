// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import java.util.Iterator;
import java.util.List;
import org.xidget.ifeature.model.IMultiValueWidgetFeature;
import org.xidget.ifeature.model.IMultiValueModelFeature;
import org.xmodel.diff.ListDiffer;
import org.xidget.IXidget;
import org.xidget.ifeature.model.IMultiValueUpdateFeature;

public class MultiValueUpdateFeature implements IMultiValueUpdateFeature
{
    protected IXidget xidget;
    private ListDiffer differ;
    private boolean updating;
    
    public MultiValueUpdateFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.differ = new ListDiffer();
    }
    
    @Override
    public void updateWidget() {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            final List<?> values = this.xidget.getFeature(IMultiValueModelFeature.class).getValues();
            final IMultiValueWidgetFeature multiValueWidgetFeature = this.xidget.getFeature(IMultiValueWidgetFeature.class);
            this.differ.diff(multiValueWidgetFeature.getValues(), values);
            for (final ListDiffer.Change change : this.differ.getChanges()) {
                if (change.rIndex >= 0) {
                    for (int i = 0; i < change.count; ++i) {
                        multiValueWidgetFeature.insertValue(change.lIndex + i, values.get(change.rIndex + i));
                    }
                }
                else {
                    for (int j = 0; j < change.count; ++j) {
                        multiValueWidgetFeature.removeValue(change.lIndex);
                    }
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
            final List<?> values = this.xidget.getFeature(IMultiValueWidgetFeature.class).getValues();
            final IMultiValueModelFeature multiValueModelFeature = this.xidget.getFeature(IMultiValueModelFeature.class);
            this.differ.diff(multiValueModelFeature.getValues(), values);
            for (final ListDiffer.Change change : this.differ.getChanges()) {
                if (change.rIndex >= 0) {
                    for (int i = 0; i < change.count; ++i) {
                        multiValueModelFeature.insertValue(change.lIndex + i, values.get(change.rIndex + i));
                    }
                }
                else {
                    for (int j = 0; j < change.count; ++j) {
                        multiValueModelFeature.removeValue(change.lIndex);
                    }
                }
            }
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void displayInsert(final int n, final Object o) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueWidgetFeature.class).insertValue(n, o);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void displayUpdate(final int n, final Object o) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueWidgetFeature.class).updateValue(n, o);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void displayRemove(final int n) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueWidgetFeature.class).removeValue(n);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void modelInsert(final int n, final Object o) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueModelFeature.class).insertValue(n, o);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void modelUpdate(final int n, final Object o) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueModelFeature.class).updateValue(n, o);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
    
    @Override
    public void modelRemove(final int n) {
        if (this.updating) {
            return;
        }
        this.updating = true;
        try {
            this.xidget.getFeature(IMultiValueModelFeature.class).removeValue(n);
        }
        finally {
            this.updating = false;
        }
        this.updating = false;
    }
}
