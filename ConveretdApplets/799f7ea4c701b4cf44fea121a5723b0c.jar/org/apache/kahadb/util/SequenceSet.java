// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

import java.io.DataOutput;
import java.io.IOException;
import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SequenceSet extends LinkedNodeList<Sequence>
{
    public void add(final Sequence value) {
        for (long i = value.first; i < value.last + 1L; ++i) {
            this.add(i);
        }
    }
    
    public boolean add(final long value) {
        if (this.isEmpty()) {
            this.addFirst(new Sequence(value));
            return true;
        }
        for (Sequence sequence = this.getHead(); sequence != null; sequence = sequence.getNext()) {
            if (sequence.isAdjacentToLast(value)) {
                sequence.last = value;
                if (sequence.getNext() != null) {
                    final Sequence next = sequence.getNext();
                    if (next.isAdjacentToFirst(value)) {
                        sequence.last = next.last;
                        next.unlink();
                    }
                }
                return true;
            }
            if (sequence.isAdjacentToFirst(value)) {
                sequence.first = value;
                if (sequence.getPrevious() != null) {
                    final Sequence prev = sequence.getPrevious();
                    if (prev.isAdjacentToLast(value)) {
                        sequence.first = prev.first;
                        prev.unlink();
                    }
                }
                return true;
            }
            if (value < sequence.first) {
                sequence.linkBefore(new Sequence(value));
                return true;
            }
            if (sequence.contains(value)) {
                return false;
            }
        }
        this.addLast(new Sequence(value));
        return true;
    }
    
    public long removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        final Sequence rc = this.removeFirstSequence(1L);
        return rc.first;
    }
    
    public Sequence removeLastSequence() {
        if (this.isEmpty()) {
            return null;
        }
        final Sequence rc = this.getTail();
        rc.unlink();
        return rc;
    }
    
    public Sequence removeFirstSequence(final long count) {
        if (this.isEmpty()) {
            return null;
        }
        for (Sequence sequence = this.getHead(); sequence != null; sequence = sequence.getNext()) {
            if (sequence.range() == count) {
                sequence.unlink();
                return sequence;
            }
            if (sequence.range() > count) {
                final Sequence rc = new Sequence(sequence.first, sequence.first + count);
                final Sequence sequence2 = sequence;
                sequence2.first += count;
                return rc;
            }
        }
        return null;
    }
    
    public List<Sequence> getMissing(long first, final long last) {
        final ArrayList<Sequence> rc = new ArrayList<Sequence>();
        if (first > last) {
            throw new IllegalArgumentException("First cannot be more than last");
        }
        if (this.isEmpty()) {
            rc.add(new Sequence(first, last));
            return rc;
        }
        for (Sequence sequence = this.getHead(); sequence != null && first <= last; sequence = sequence.getNext()) {
            if (sequence.contains(first)) {
                first = sequence.last + 1L;
            }
            else if (first < sequence.first) {
                if (last < sequence.first) {
                    rc.add(new Sequence(first, last));
                    return rc;
                }
                rc.add(new Sequence(first, sequence.first - 1L));
                first = sequence.last + 1L;
            }
        }
        if (first <= last) {
            rc.add(new Sequence(first, last));
        }
        return rc;
    }
    
    public List<Sequence> getReceived() {
        final ArrayList<Sequence> rc = new ArrayList<Sequence>(this.size());
        for (Sequence sequence = this.getHead(); sequence != null; sequence = sequence.getNext()) {
            rc.add(new Sequence(sequence.first, sequence.last));
        }
        return rc;
    }
    
    public boolean contains(final int first, final int last) {
        if (this.isEmpty()) {
            return false;
        }
        for (Sequence sequence = this.getHead(); sequence != null; sequence = sequence.getNext()) {
            if (sequence.first <= first && first <= sequence.last) {
                return last <= sequence.last;
            }
        }
        return false;
    }
    
    public static class Marshaller implements org.apache.kahadb.util.Marshaller<SequenceSet>
    {
        public static final Marshaller INSTANCE;
        
        public SequenceSet readPayload(final DataInput in) throws IOException {
            final SequenceSet value = new SequenceSet();
            for (int count = in.readInt(), i = 0; i < count; ++i) {
                if (in.readBoolean()) {
                    final Sequence sequence = new Sequence(in.readLong(), in.readLong());
                    value.addLast(sequence);
                }
                else {
                    final Sequence sequence = new Sequence(in.readLong());
                    value.addLast(sequence);
                }
            }
            return value;
        }
        
        public void writePayload(final SequenceSet value, final DataOutput out) throws IOException {
            out.writeInt(value.size());
            for (Sequence sequence = value.getHead(); sequence != null; sequence = sequence.getNext()) {
                if (sequence.range() > 1L) {
                    out.writeBoolean(true);
                    out.writeLong(sequence.first);
                    out.writeLong(sequence.last);
                }
                else {
                    out.writeBoolean(false);
                    out.writeLong(sequence.first);
                }
            }
        }
        
        public int getFixedSize() {
            return -1;
        }
        
        public SequenceSet deepCopy(final SequenceSet value) {
            final SequenceSet rc = new SequenceSet();
            for (Sequence sequence = value.getHead(); sequence != null; sequence = sequence.getNext()) {
                rc.add(new Sequence(sequence.first, sequence.last));
            }
            return rc;
        }
        
        public boolean isDeepCopySupported() {
            return true;
        }
        
        static {
            INSTANCE = new Marshaller();
        }
    }
}
