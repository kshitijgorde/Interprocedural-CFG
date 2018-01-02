// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.event.EventListenerList;
import java.io.ObjectInputValidation;
import java.io.Serializable;

public abstract class AbstractDataset implements Dataset, Cloneable, Serializable, ObjectInputValidation
{
    private DatasetGroup group;
    private transient EventListenerList listenerList;
    static /* synthetic */ Class class$org$jfree$data$DatasetChangeListener;
    
    protected AbstractDataset() {
        this.group = new DatasetGroup();
        this.listenerList = new EventListenerList();
    }
    
    public DatasetGroup getGroup() {
        return this.group;
    }
    
    public void setGroup(final DatasetGroup group) {
        this.group = group;
    }
    
    public void addChangeListener(final DatasetChangeListener listener) {
        this.listenerList.add((AbstractDataset.class$org$jfree$data$DatasetChangeListener == null) ? (AbstractDataset.class$org$jfree$data$DatasetChangeListener = class$("org.jfree.data.DatasetChangeListener")) : AbstractDataset.class$org$jfree$data$DatasetChangeListener, listener);
    }
    
    public void removeChangeListener(final DatasetChangeListener listener) {
        this.listenerList.remove((AbstractDataset.class$org$jfree$data$DatasetChangeListener == null) ? (AbstractDataset.class$org$jfree$data$DatasetChangeListener = class$("org.jfree.data.DatasetChangeListener")) : AbstractDataset.class$org$jfree$data$DatasetChangeListener, listener);
    }
    
    protected void fireDatasetChanged() {
        this.notifyListeners(new DatasetChangeEvent(this, this));
    }
    
    protected void notifyListeners(final DatasetChangeEvent event) {
        final Object[] listeners = this.listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((AbstractDataset.class$org$jfree$data$DatasetChangeListener == null) ? (AbstractDataset.class$org$jfree$data$DatasetChangeListener = class$("org.jfree.data.DatasetChangeListener")) : AbstractDataset.class$org$jfree$data$DatasetChangeListener)) {
                ((DatasetChangeListener)listeners[i + 1]).datasetChanged(event);
            }
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AbstractDataset clone = (AbstractDataset)super.clone();
        clone.listenerList = new EventListenerList();
        return clone;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.listenerList = new EventListenerList();
        stream.registerValidation(this, 10);
    }
    
    public void validateObject() throws InvalidObjectException {
        this.fireDatasetChanged();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
