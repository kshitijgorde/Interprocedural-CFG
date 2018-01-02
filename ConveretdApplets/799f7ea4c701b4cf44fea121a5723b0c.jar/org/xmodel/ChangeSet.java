// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import org.xmodel.record.RemoveChildBoundRecord;
import org.xmodel.record.AddChildBoundRecord;
import org.xmodel.record.AbstractBoundRecord;
import org.xmodel.record.ClearAttributeBoundRecord;
import org.xmodel.record.ChangeAttributeBoundRecord;
import java.util.List;

public class ChangeSet implements IChangeSet
{
    protected List<IBoundChangeRecord> records;
    private boolean A;
    
    @Override
    public void applyChanges() {
        if (this.records == null) {
            return;
        }
        for (int i = 0; i < this.records.size(); ++i) {
            this.records.get(i).applyChange();
        }
    }
    
    @Override
    public void clearChanges() {
        this.records = null;
    }
    
    @Override
    public void setAttribute(final IModelObject modelObject, final String s, final Object o) {
        if (!this.A && s.length() == 0) {
            final Object value = modelObject.getValue();
            if (o == null && value != null && value.toString().length() == 0) {
                return;
            }
            if (value == null && o != null && o.toString().length() == 0) {
                return;
            }
        }
        AbstractBoundRecord abstractBoundRecord;
        if (o != null) {
            if (o.equals("")) {
                abstractBoundRecord = new ChangeAttributeBoundRecord(modelObject, s);
            }
            else {
                abstractBoundRecord = new ChangeAttributeBoundRecord(modelObject, s, o);
            }
        }
        else {
            abstractBoundRecord = new ClearAttributeBoundRecord(modelObject, s);
        }
        this.addRecord(abstractBoundRecord);
    }
    
    @Override
    public void removeAttribute(final IModelObject modelObject, final String s) {
        if (!this.A && s.length() == 0) {
            final Object value = modelObject.getValue();
            if (value != null && value.toString().length() == 0) {
                return;
            }
        }
        this.addRecord(new ClearAttributeBoundRecord(modelObject, s));
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final IModelObject modelObject2) {
        this.addRecord(new AddChildBoundRecord(modelObject, modelObject2));
    }
    
    @Override
    public void addChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.addRecord(new AddChildBoundRecord(modelObject, modelObject2, n));
    }
    
    @Override
    public void removeChild(final IModelObject modelObject, final IModelObject modelObject2) {
        this.addRecord(new RemoveChildBoundRecord(modelObject, modelObject2));
    }
    
    @Override
    public void removeChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.addRecord(new RemoveChildBoundRecord(modelObject, modelObject2, n));
    }
    
    public void addRecord(final IBoundChangeRecord boundChangeRecord) {
        if (this.records == null) {
            this.records = new ArrayList<IBoundChangeRecord>();
        }
        this.records.add(boundChangeRecord);
    }
    
    @Override
    public List<IBoundChangeRecord> getRecords() {
        if (this.records == null) {
            return Collections.emptyList();
        }
        return this.records;
    }
    
    @Override
    public int getSize() {
        return (this.records != null) ? this.records.size() : 0;
    }
    
    @Override
    public void run() {
        this.applyChanges();
    }
    
    @Override
    public String toString() {
        final int size = this.getSize();
        final StringBuilder sb = new StringBuilder();
        sb.append("records=");
        sb.append(size);
        sb.append("\n");
        if (this.records != null) {
            final Iterator<IBoundChangeRecord> iterator = this.records.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next().toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
