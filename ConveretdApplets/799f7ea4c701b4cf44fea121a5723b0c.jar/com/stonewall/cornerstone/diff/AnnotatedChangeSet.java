// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import java.util.Iterator;
import org.xmodel.IChangeRecord;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xmodel.log.Log;
import org.xmodel.IBoundChangeRecord;
import java.util.List;
import org.xmodel.IChangeSet;

public class AnnotatedChangeSet implements IChangeSet
{
    final List<IBoundChangeRecord> changeSet;
    static final Log log;
    
    static {
        log = Log.getLog(AnnotatedChangeSet.class);
    }
    
    public AnnotatedChangeSet() {
        this.changeSet = new ArrayList<IBoundChangeRecord>();
    }
    
    @Override
    public void setAttribute(final IModelObject object, final String attrName, final Object attrValue) {
        if (!attrName.equals("id")) {
            this.changeSet.add(new AttrChanged(object, attrName, attrValue.toString()));
        }
    }
    
    @Override
    public void removeAttribute(final IModelObject object, final String attrName) {
        this.changeSet.add(new AttrRemoved(object, attrName));
    }
    
    public void setText(final IModelObject element, final String text) {
        this.changeSet.add(new TextChanged(element, text));
    }
    
    @Override
    public void addChild(final IModelObject object, final IModelObject child) {
        this.changeSet.add(new ChildAdded(object, child));
    }
    
    @Override
    public void addChild(final IModelObject object, final IModelObject child, final int index) {
        this.changeSet.add(new ChildAdded(object, child, index));
    }
    
    @Override
    public void removeChild(final IModelObject object, final IModelObject child) {
        this.changeSet.add(new ChildRemoved(object, child));
    }
    
    @Override
    public void removeChild(final IModelObject object, final IModelObject child, final int index) {
        this.changeSet.add(new ChildRemoved(object, child, index));
    }
    
    @Override
    public List<IBoundChangeRecord> getRecords() {
        return this.changeSet;
    }
    
    public List<IChangeRecord> getUnboundRecords() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int getSize() {
        return this.changeSet.size();
    }
    
    public IChangeSet createUndoSet() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void applyChanges() {
        for (final IBoundChangeRecord lcr : this.changeSet) {
            try {
                ((IAnnotatedRecord)lcr).annotate();
            }
            catch (Exception e) {
                AnnotatedChangeSet.log.error(lcr, e);
            }
        }
        for (final IBoundChangeRecord lcr : this.changeSet) {
            try {
                lcr.applyChange();
            }
            catch (Exception e) {
                AnnotatedChangeSet.log.error(lcr, e);
            }
        }
        for (final IBoundChangeRecord lcr : this.changeSet) {
            try {
                final IBoundChangeRecord undo = lcr.createUndoRecord();
                if (undo == null) {
                    continue;
                }
                undo.applyChange();
            }
            catch (Exception e) {
                AnnotatedChangeSet.log.error(lcr, e);
            }
        }
    }
    
    public void applyChanges(final IModelObject object) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clearChanges() {
        this.changeSet.clear();
    }
    
    public void normalize() {
        throw new UnsupportedOperationException();
    }
    
    public void addRecord(final IBoundChangeRecord record) {
        throw new UnsupportedOperationException();
    }
    
    public void removeRecord(final IBoundChangeRecord record) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void run() {
        this.applyChanges();
    }
}
