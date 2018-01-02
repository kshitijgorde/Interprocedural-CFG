// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Enumeration;
import org.apache.xerces.xni.Augmentations;

public class AugmentationsImpl implements Augmentations
{
    private AugmentationsItemsContainer fAugmentationsContainer;
    
    public AugmentationsImpl() {
        this.fAugmentationsContainer = new SmallContainer();
    }
    
    public Object putItem(final String key, final Object item) {
        final Object oldValue = this.fAugmentationsContainer.putItem(key, item);
        if (oldValue == null && this.fAugmentationsContainer.isFull()) {
            this.fAugmentationsContainer = this.fAugmentationsContainer.expand();
        }
        return oldValue;
    }
    
    public Object getItem(final String key) {
        return this.fAugmentationsContainer.getItem(key);
    }
    
    public Object removeItem(final String key) {
        return this.fAugmentationsContainer.removeItem(key);
    }
    
    public Enumeration keys() {
        return this.fAugmentationsContainer.keys();
    }
    
    public void clear() {
        this.fAugmentationsContainer.clear();
    }
    
    public String toString() {
        return this.fAugmentationsContainer.toString();
    }
    
    abstract class AugmentationsItemsContainer
    {
        public abstract Object putItem(final Object p0, final Object p1);
        
        public abstract Object getItem(final Object p0);
        
        public abstract Object removeItem(final Object p0);
        
        public abstract Enumeration keys();
        
        public abstract void clear();
        
        public abstract boolean isFull();
        
        public abstract AugmentationsItemsContainer expand();
    }
    
    class SmallContainer extends AugmentationsItemsContainer
    {
        static final int SIZE_LIMIT = 10;
        final Object[] fAugmentations;
        int fNumEntries;
        
        SmallContainer() {
            this.fAugmentations = new Object[20];
            this.fNumEntries = 0;
        }
        
        public Enumeration keys() {
            return new SmallContainerKeyEnumeration();
        }
        
        public Object getItem(final Object key) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(key)) {
                    return this.fAugmentations[i + 1];
                }
            }
            return null;
        }
        
        public Object putItem(final Object key, final Object item) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(key)) {
                    final Object oldValue = this.fAugmentations[i + 1];
                    this.fAugmentations[i + 1] = item;
                    return oldValue;
                }
            }
            this.fAugmentations[this.fNumEntries * 2] = key;
            this.fAugmentations[this.fNumEntries * 2 + 1] = item;
            ++this.fNumEntries;
            return null;
        }
        
        public Object removeItem(final Object key) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(key)) {
                    final Object oldValue = this.fAugmentations[i + 1];
                    for (int j = i; j < this.fNumEntries * 2 - 2; j += 2) {
                        this.fAugmentations[j] = this.fAugmentations[j + 2];
                        this.fAugmentations[j + 1] = this.fAugmentations[j + 3];
                    }
                    this.fAugmentations[this.fNumEntries * 2 - 2] = null;
                    this.fAugmentations[this.fNumEntries * 2 - 1] = null;
                    --this.fNumEntries;
                    return oldValue;
                }
            }
            return null;
        }
        
        public void clear() {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                this.fAugmentations[i] = null;
                this.fAugmentations[i + 1] = null;
            }
            this.fNumEntries = 0;
        }
        
        public boolean isFull() {
            return this.fNumEntries == 10;
        }
        
        public AugmentationsItemsContainer expand() {
            final LargeContainer expandedContainer = new LargeContainer();
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                expandedContainer.putItem(this.fAugmentations[i], this.fAugmentations[i + 1]);
            }
            return expandedContainer;
        }
        
        public String toString() {
            final StringBuffer buff = new StringBuffer();
            buff.append("SmallContainer - fNumEntries == " + this.fNumEntries);
            for (int i = 0; i < 20; i += 2) {
                buff.append("\nfAugmentations[");
                buff.append(i);
                buff.append("] == ");
                buff.append(this.fAugmentations[i]);
                buff.append("; fAugmentations[");
                buff.append(i + 1);
                buff.append("] == ");
                buff.append(this.fAugmentations[i + 1]);
            }
            return buff.toString();
        }
        
        class SmallContainerKeyEnumeration implements Enumeration
        {
            Object[] enumArray;
            int next;
            
            SmallContainerKeyEnumeration() {
                this.enumArray = new Object[SmallContainer.this.fNumEntries];
                this.next = 0;
                for (int i = 0; i < SmallContainer.this.fNumEntries; ++i) {
                    this.enumArray[i] = SmallContainer.this.fAugmentations[i * 2];
                }
            }
            
            public boolean hasMoreElements() {
                return this.next < this.enumArray.length;
            }
            
            public Object nextElement() {
                if (this.next >= this.enumArray.length) {
                    throw new NoSuchElementException();
                }
                final Object nextVal = this.enumArray[this.next];
                this.enumArray[this.next] = null;
                ++this.next;
                return nextVal;
            }
        }
    }
    
    class LargeContainer extends AugmentationsItemsContainer
    {
        final Hashtable fAugmentations;
        
        LargeContainer() {
            this.fAugmentations = new Hashtable();
        }
        
        public Object getItem(final Object key) {
            return this.fAugmentations.get(key);
        }
        
        public Object putItem(final Object key, final Object item) {
            return this.fAugmentations.put(key, item);
        }
        
        public Object removeItem(final Object key) {
            return this.fAugmentations.remove(key);
        }
        
        public Enumeration keys() {
            return this.fAugmentations.keys();
        }
        
        public void clear() {
            this.fAugmentations.clear();
        }
        
        public boolean isFull() {
            return false;
        }
        
        public AugmentationsItemsContainer expand() {
            return this;
        }
        
        public String toString() {
            final StringBuffer buff = new StringBuffer();
            buff.append("LargeContainer");
            final Enumeration enum1 = this.fAugmentations.keys();
            while (enum1.hasMoreElements()) {
                final Object key = enum1.nextElement();
                buff.append("\nkey == ");
                buff.append(key);
                buff.append("; value == ");
                buff.append(this.fAugmentations.get(key));
            }
            return buff.toString();
        }
    }
}
