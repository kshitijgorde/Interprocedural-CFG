// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ListSelectionEvent;
import java.util.EventListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.EventListenerList;
import java.util.BitSet;
import java.io.Serializable;

public class DefaultListSelectionModel implements ListSelectionModel, Cloneable, Serializable
{
    private static final int MIN = -1;
    private static final int MAX = Integer.MAX_VALUE;
    private int selectionMode;
    private int minIndex;
    private int maxIndex;
    private int anchorIndex;
    private int leadIndex;
    private int firstAdjustedIndex;
    private int lastAdjustedIndex;
    private boolean isAdjusting;
    private int firstChangedIndex;
    private int lastChangedIndex;
    private BitSet value;
    protected EventListenerList listenerList;
    protected boolean leadAnchorNotificationEnabled;
    static /* synthetic */ Class class$javax$swing$event$ListSelectionListener;
    
    public DefaultListSelectionModel() {
        this.selectionMode = 2;
        this.minIndex = Integer.MAX_VALUE;
        this.maxIndex = -1;
        this.anchorIndex = -1;
        this.leadIndex = -1;
        this.firstAdjustedIndex = Integer.MAX_VALUE;
        this.lastAdjustedIndex = -1;
        this.isAdjusting = false;
        this.firstChangedIndex = Integer.MAX_VALUE;
        this.lastChangedIndex = -1;
        this.value = new BitSet(32);
        this.listenerList = new EventListenerList();
        this.leadAnchorNotificationEnabled = true;
    }
    
    public void addListSelectionListener(final ListSelectionListener listSelectionListener) {
        this.listenerList.add((DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener != null) ? DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener : (DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")), listSelectionListener);
    }
    
    public void addSelectionInterval(final int n, final int n2) {
        if (n == -1 || n2 == -1) {
            return;
        }
        if (this.getSelectionMode() != 2) {
            this.setSelectionInterval(n, n2);
            return;
        }
        this.updateLeadAnchorIndices(n, n2);
        this.changeSelection(Integer.MAX_VALUE, -1, Math.min(n, n2), Math.max(n, n2));
    }
    
    private void changeSelection(final int n, final int n2, final int n3, final int n4) {
        this.changeSelection(n, n2, n3, n4, true);
    }
    
    private void changeSelection(final int n, final int n2, final int n3, final int n4, final boolean b) {
        for (int i = Math.min(n3, n); i <= Math.max(n4, n2); ++i) {
            int contains = this.contains(n, n2, i) ? 1 : 0;
            int contains2 = this.contains(n3, n4, i) ? 1 : 0;
            if (contains2 != 0 && contains != 0) {
                if (b) {
                    contains = 0;
                }
                else {
                    contains2 = 0;
                }
            }
            if (contains2 != 0) {
                this.set(i);
            }
            if (contains != 0) {
                this.clear(i);
            }
        }
        this.fireValueChanged();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private void clear(final int n) {
        if (!this.value.get(n)) {
            return;
        }
        this.value.clear(n);
        this.markAsDirty(n);
        if (n == this.minIndex) {
            ++this.minIndex;
            while (this.minIndex <= this.maxIndex) {
                if (this.value.get(this.minIndex)) {
                    break;
                }
                ++this.minIndex;
            }
        }
        if (n == this.maxIndex) {
            --this.maxIndex;
            while (this.minIndex <= this.maxIndex) {
                if (this.value.get(this.maxIndex)) {
                    break;
                }
                --this.maxIndex;
            }
        }
        if (this.isSelectionEmpty()) {
            this.minIndex = Integer.MAX_VALUE;
            this.maxIndex = -1;
        }
    }
    
    public void clearSelection() {
        this.removeSelectionInterval(this.minIndex, this.maxIndex);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final DefaultListSelectionModel defaultListSelectionModel = (DefaultListSelectionModel)super.clone();
        defaultListSelectionModel.value = (BitSet)this.value.clone();
        defaultListSelectionModel.listenerList = new EventListenerList();
        return defaultListSelectionModel;
    }
    
    private boolean contains(final int n, final int n2, final int n3) {
        return n3 >= n && n3 <= n2;
    }
    
    private void fireValueChanged() {
        if (this.lastAdjustedIndex == -1) {
            return;
        }
        if (this.getValueIsAdjusting()) {
            this.firstChangedIndex = Math.min(this.firstChangedIndex, this.firstAdjustedIndex);
            this.lastChangedIndex = Math.max(this.lastChangedIndex, this.lastAdjustedIndex);
        }
        final int firstAdjustedIndex = this.firstAdjustedIndex;
        final int lastAdjustedIndex = this.lastAdjustedIndex;
        this.firstAdjustedIndex = Integer.MAX_VALUE;
        this.lastAdjustedIndex = -1;
        this.fireValueChanged(firstAdjustedIndex, lastAdjustedIndex);
    }
    
    protected void fireValueChanged(final int n, final int n2) {
        this.fireValueChanged(n, n2, this.getValueIsAdjusting());
    }
    
    protected void fireValueChanged(final int n, final int n2, final boolean b) {
        final Object[] listenerList = this.listenerList.getListenerList();
        ListSelectionEvent listSelectionEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener != null) ? DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener : (DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")))) {
                if (listSelectionEvent == null) {
                    listSelectionEvent = new ListSelectionEvent(this, n, n2, b);
                }
                ((ListSelectionListener)listenerList[i + 1]).valueChanged(listSelectionEvent);
            }
        }
    }
    
    protected void fireValueChanged(final boolean b) {
        if (this.lastChangedIndex == -1) {
            return;
        }
        final int firstChangedIndex = this.firstChangedIndex;
        final int lastChangedIndex = this.lastChangedIndex;
        this.firstChangedIndex = Integer.MAX_VALUE;
        this.lastChangedIndex = -1;
        this.fireValueChanged(firstChangedIndex, lastChangedIndex, b);
    }
    
    public int getAnchorSelectionIndex() {
        return this.anchorIndex;
    }
    
    public int getLeadSelectionIndex() {
        return this.leadIndex;
    }
    
    public int getMaxSelectionIndex() {
        return this.maxIndex;
    }
    
    public int getMinSelectionIndex() {
        return this.isSelectionEmpty() ? -1 : this.minIndex;
    }
    
    public int getSelectionMode() {
        return this.selectionMode;
    }
    
    public boolean getValueIsAdjusting() {
        return this.isAdjusting;
    }
    
    public void insertIndexInterval(final int n, final int n2, final boolean b) {
        final int n3 = b ? n : (n + 1);
        final int n4 = n3 + n2 - 1;
        for (int i = this.maxIndex; i >= n3; --i) {
            this.setState(i + n2, this.value.get(i));
        }
        final boolean value = this.value.get(n);
        for (int j = n3; j <= n4; ++j) {
            this.setState(j, value);
        }
    }
    
    public boolean isLeadAnchorNotificationEnabled() {
        return this.leadAnchorNotificationEnabled;
    }
    
    public boolean isSelectedIndex(final int n) {
        return n >= this.minIndex && n <= this.maxIndex && this.value.get(n);
    }
    
    public boolean isSelectionEmpty() {
        return this.minIndex > this.maxIndex;
    }
    
    private void markAsDirty(final int n) {
        this.firstAdjustedIndex = Math.min(this.firstAdjustedIndex, n);
        this.lastAdjustedIndex = Math.max(this.lastAdjustedIndex, n);
    }
    
    public void removeIndexInterval(final int n, final int n2) {
        final int min = Math.min(n, n2);
        final int n3 = Math.max(n, n2) - min + 1;
        for (int i = min; i <= this.maxIndex; ++i) {
            this.setState(i, this.value.get(i + n3));
        }
    }
    
    public void removeListSelectionListener(final ListSelectionListener listSelectionListener) {
        this.listenerList.remove((DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener != null) ? DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener : (DefaultListSelectionModel.class$javax$swing$event$ListSelectionListener = class$("javax.swing.event.ListSelectionListener")), listSelectionListener);
    }
    
    public void removeSelectionInterval(final int n, final int n2) {
        if (n == -1 || n2 == -1) {
            return;
        }
        this.updateLeadAnchorIndices(n, n2);
        final int min = Math.min(n, n2);
        int n3 = Math.max(n, n2);
        final int n4 = Integer.MAX_VALUE;
        final int n5 = -1;
        if (this.getSelectionMode() != 2 && min > this.minIndex && n3 < this.maxIndex) {
            n3 = this.maxIndex;
        }
        this.changeSelection(min, n3, n4, n5);
    }
    
    private void set(final int n) {
        if (this.value.get(n)) {
            return;
        }
        this.value.set(n);
        this.markAsDirty(n);
        this.minIndex = Math.min(this.minIndex, n);
        this.maxIndex = Math.max(this.maxIndex, n);
    }
    
    public void setAnchorSelectionIndex(final int anchorIndex) {
        this.updateLeadAnchorIndices(anchorIndex, this.leadIndex);
        this.anchorIndex = anchorIndex;
        this.fireValueChanged();
    }
    
    public void setLeadAnchorNotificationEnabled(final boolean leadAnchorNotificationEnabled) {
        this.leadAnchorNotificationEnabled = leadAnchorNotificationEnabled;
    }
    
    public void setLeadSelectionIndex(final int n) {
        int anchorIndex = this.anchorIndex;
        if (anchorIndex == -1 || n == -1) {
            return;
        }
        if (this.leadIndex == -1) {
            this.leadIndex = n;
        }
        if (this.getSelectionMode() == 0) {
            anchorIndex = n;
        }
        final int min = Math.min(this.anchorIndex, this.leadIndex);
        final int max = Math.max(this.anchorIndex, this.leadIndex);
        final int min2 = Math.min(anchorIndex, n);
        final int max2 = Math.max(anchorIndex, n);
        if (this.value.get(this.anchorIndex)) {
            this.changeSelection(min, max, min2, max2);
        }
        else {
            this.changeSelection(min2, max2, min, max, false);
        }
        this.anchorIndex = anchorIndex;
        this.leadIndex = n;
    }
    
    public void setSelectionInterval(int n, final int n2) {
        if (n == -1 || n2 == -1) {
            return;
        }
        if (this.getSelectionMode() == 0) {
            n = n2;
        }
        this.updateLeadAnchorIndices(n, n2);
        this.changeSelection(this.minIndex, this.maxIndex, Math.min(n, n2), Math.max(n, n2));
    }
    
    public void setSelectionMode(final int selectionMode) {
        switch (selectionMode) {
            case 0:
            case 1:
            case 2: {
                this.selectionMode = selectionMode;
            }
            default: {
                throw new IllegalArgumentException("invalid selectionMode");
            }
        }
    }
    
    private void setState(final int n, final boolean b) {
        if (b) {
            this.set(n);
        }
        else {
            this.clear(n);
        }
    }
    
    public void setValueIsAdjusting(final boolean isAdjusting) {
        if (isAdjusting != this.isAdjusting) {
            this.fireValueChanged(this.isAdjusting = isAdjusting);
        }
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + " " + Integer.toString(this.hashCode()) + " " + (String.valueOf(this.getValueIsAdjusting() ? "~" : "=") + this.value.toString());
    }
    
    private void updateLeadAnchorIndices(final int anchorIndex, final int leadIndex) {
        if (this.leadAnchorNotificationEnabled) {
            if (this.anchorIndex != anchorIndex) {
                if (this.anchorIndex != -1) {
                    this.markAsDirty(this.anchorIndex);
                }
                this.markAsDirty(anchorIndex);
            }
            if (this.leadIndex != leadIndex) {
                if (this.leadIndex != -1) {
                    this.markAsDirty(this.leadIndex);
                }
                this.markAsDirty(leadIndex);
            }
        }
        this.anchorIndex = anchorIndex;
        this.leadIndex = leadIndex;
    }
}
