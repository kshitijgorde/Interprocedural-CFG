// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.thread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.PriorityQueue;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.AbstractQueue;

public class RunQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable
{
    private static final long serialVersionUID = 5595510919245408276L;
    private final PriorityQueue<E> q;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    
    public RunQueue() {
        this.lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.q = new PriorityQueue<E>();
    }
    
    public RunQueue(final int initialCapacity) {
        this.lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.q = new PriorityQueue<E>(initialCapacity, null);
    }
    
    public RunQueue(final int initialCapacity, final Comparator<? super E> comparator) {
        this.lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.q = new PriorityQueue<E>(initialCapacity, comparator);
    }
    
    public RunQueue(final Collection<? extends E> c) {
        this.lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.q = new PriorityQueue<E>(c);
    }
    
    public Comparator<? super E> comparator() {
        return this.q.comparator();
    }
    
    @Override
    public boolean offer(final E o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        this.lock.lock();
        try {
            final boolean offerResult = this.q.offer(o);
            assert offerResult;
            this.notEmpty.signal();
            return true;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void put(final E o) {
        this.offer(o);
    }
    
    @Override
    public boolean offer(final E o, final long timeout, final TimeUnit unit) {
        return this.offer(o);
    }
    
    @Override
    public E take() throws InterruptedException {
        this.lock.lockInterruptibly();
        try {
            while (this.q.size() == 0) {
                this.notEmpty.await();
            }
            return this.q.poll();
        }
        catch (InterruptedException ie) {
            this.notEmpty.signal();
            throw ie;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public E blockingPeek() throws InterruptedException {
        this.lock.lockInterruptibly();
        try {
            while (this.q.size() == 0) {
                this.notEmpty.await();
            }
            return this.q.peek();
        }
        catch (InterruptedException ie) {
            this.notEmpty.signal();
            throw ie;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public E poll() {
        this.lock.lock();
        try {
            return this.q.poll();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public E poll(final long timeout, final TimeUnit unit) throws InterruptedException {
        long ns = unit.toNanos(timeout);
        this.lock.lockInterruptibly();
        try {
            while (true) {
                final E x = this.q.poll();
                if (x != null) {
                    return x;
                }
                if (ns <= 0L) {
                    return null;
                }
                try {
                    ns = this.notEmpty.awaitNanos(ns);
                }
                catch (InterruptedException ie) {
                    this.notEmpty.signal();
                    throw ie;
                }
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public E peek() {
        this.lock.lock();
        try {
            return this.q.peek();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int size() {
        this.lock.lock();
        try {
            return this.q.size();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean remove(final Object o) {
        this.lock.lock();
        try {
            return this.q.remove(o);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean contains(final Object o) {
        this.lock.lock();
        try {
            return this.q.contains(o);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Object[] toArray() {
        this.lock.lock();
        try {
            return this.q.toArray();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public String toString() {
        this.lock.lock();
        try {
            return this.q.toString();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int drainTo(final Collection<? super E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c == this) {
            throw new IllegalArgumentException();
        }
        this.lock.lock();
        try {
            int n = 0;
            E e;
            while ((e = this.q.poll()) != null) {
                c.add((Object)e);
                ++n;
            }
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int drainTo(final Collection<? super E> c, final int maxElements) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c == this) {
            throw new IllegalArgumentException();
        }
        if (maxElements <= 0) {
            return 0;
        }
        this.lock.lock();
        try {
            int n;
            E e;
            for (n = 0; n < maxElements && (e = this.q.poll()) != null; ++n) {
                c.add((Object)e);
            }
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void clear() {
        this.lock.lock();
        try {
            this.q.clear();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public <T> T[] toArray(final T[] a) {
        this.lock.lock();
        try {
            return this.q.toArray(a);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        this.lock.lock();
        try {
            return new Itr<E>(this.q.iterator());
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private void writeObject(final ObjectOutputStream s) throws IOException {
        this.lock.lock();
        try {
            s.defaultWriteObject();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    private class Itr<E> implements Iterator<E>
    {
        private final Iterator<E> iter;
        
        Itr(final Iterator<E> i) {
            this.iter = i;
        }
        
        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }
        
        @Override
        public E next() {
            RunQueue.this.lock.lock();
            try {
                return this.iter.next();
            }
            finally {
                RunQueue.this.lock.unlock();
            }
        }
        
        @Override
        public void remove() {
            RunQueue.this.lock.lock();
            try {
                this.iter.remove();
            }
            finally {
                RunQueue.this.lock.unlock();
            }
            RunQueue.this.lock.unlock();
        }
    }
}
