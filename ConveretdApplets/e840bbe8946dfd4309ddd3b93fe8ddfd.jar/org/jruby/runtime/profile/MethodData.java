// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import java.util.ArrayList;

public class MethodData extends InvocationSet
{
    public int serialNumber;
    
    public MethodData(final int serial) {
        this.serialNumber = serial;
        this.invocations = new ArrayList<Invocation>();
    }
    
    public int[] parents() {
        final IntList p = new IntList();
        for (final Invocation inv : this.invocations) {
            if (inv.getParent() != null) {
                final int serial = inv.getParent().getMethodSerialNumber();
                if (p.contains(serial)) {
                    continue;
                }
                p.add(serial);
            }
        }
        return p.toIntArray();
    }
    
    public int[] children() {
        final IntList p = new IntList();
        for (final Invocation inv : this.invocations) {
            for (final Integer childSerial : inv.getChildren().keySet()) {
                if (!p.contains(childSerial)) {
                    p.add(childSerial);
                }
            }
        }
        return p.toIntArray();
    }
    
    public InvocationSet invocationsForParent(final int parentSerial) {
        final ArrayList<Invocation> p = new ArrayList<Invocation>();
        for (final Invocation inv : this.invocations) {
            final int serial = inv.getParent().getMethodSerialNumber();
            if (serial == parentSerial) {
                p.add(inv.getParent());
            }
        }
        return new InvocationSet(p);
    }
    
    public InvocationSet rootInvocationsFromParent(final int parentSerial) {
        final ArrayList<Invocation> p = new ArrayList<Invocation>();
        for (final Invocation inv : this.invocations) {
            final int serial = inv.getParent().getMethodSerialNumber();
            if (serial == parentSerial && inv.getRecursiveDepth() == 1) {
                p.add(inv);
            }
        }
        return new InvocationSet(p);
    }
    
    public InvocationSet invocationsFromParent(final int parentSerial) {
        final ArrayList<Invocation> p = new ArrayList<Invocation>();
        for (final Invocation inv : this.invocations) {
            final int serial = inv.getParent().getMethodSerialNumber();
            if (serial == parentSerial) {
                p.add(inv);
            }
        }
        return new InvocationSet(p);
    }
    
    public InvocationSet rootInvocationsOfChild(final int childSerial) {
        final ArrayList<Invocation> p = new ArrayList<Invocation>();
        for (final Invocation inv : this.invocations) {
            final Invocation childInv = inv.getChildren().get(childSerial);
            if (childInv != null && childInv.getRecursiveDepth() == 1) {
                p.add(childInv);
            }
        }
        return new InvocationSet(p);
    }
    
    public InvocationSet invocationsOfChild(final int childSerial) {
        final ArrayList<Invocation> p = new ArrayList<Invocation>();
        for (final Invocation inv : this.invocations) {
            final Invocation childInv = inv.getChildren().get(childSerial);
            if (childInv != null) {
                p.add(childInv);
            }
        }
        return new InvocationSet(p);
    }
    
    public long totalTime() {
        long t = 0L;
        for (final Invocation inv : this.invocations) {
            if (inv.getRecursiveDepth() == 1) {
                t += inv.getDuration();
            }
        }
        return t;
    }
    
    public long childTime() {
        long t = 0L;
        for (final Invocation inv : this.invocations) {
            if (inv.getRecursiveDepth() == 1) {
                t += inv.childTime();
            }
        }
        return t;
    }
    
    private static class IntList
    {
        private int[] ints;
        private int size;
        
        private IntList() {
            this.ints = new int[10];
        }
        
        public void add(final int i) {
            if (this.size == this.ints.length) {
                final int[] newInts = new int[(int)(this.ints.length * 1.5 + 1.0)];
                System.arraycopy(this.ints, 0, newInts, 0, this.ints.length);
                this.ints = newInts;
            }
            this.ints[this.size++] = i;
        }
        
        public boolean contains(final int i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.ints[j] == i) {
                    return true;
                }
            }
            return false;
        }
        
        public int[] toIntArray() {
            final int[] newInts = new int[this.size];
            System.arraycopy(this.ints, 0, newInts, 0, this.size);
            return newInts;
        }
    }
}
