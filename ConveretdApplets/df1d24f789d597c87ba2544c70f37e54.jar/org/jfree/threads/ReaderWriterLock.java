// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.threads;

import java.util.Enumeration;
import java.util.Vector;

public class ReaderWriterLock
{
    private Vector waiters;
    
    public ReaderWriterLock() {
        this.waiters = new Vector();
    }
    
    public synchronized void lockRead() {
        final Thread currentThread = Thread.currentThread();
        final int index = this.getIndex(currentThread);
        ReaderWriterNode readerWriterNode;
        if (index == -1) {
            readerWriterNode = new ReaderWriterNode(currentThread, 0);
            this.waiters.addElement(readerWriterNode);
        }
        else {
            readerWriterNode = this.waiters.elementAt(index);
        }
        while (this.getIndex(currentThread) > this.firstWriter()) {
            try {
                this.wait();
            }
            catch (Exception ex) {
                System.err.println("ReaderWriterLock.lockRead(): exception.");
                System.err.print(ex.getMessage());
            }
        }
        final ReaderWriterNode readerWriterNode2 = readerWriterNode;
        ++readerWriterNode2.nAcquires;
    }
    
    public synchronized void lockWrite() {
        final Thread currentThread = Thread.currentThread();
        final int index = this.getIndex(currentThread);
        ReaderWriterNode readerWriterNode;
        if (index == -1) {
            readerWriterNode = new ReaderWriterNode(currentThread, 1);
            this.waiters.addElement(readerWriterNode);
        }
        else {
            readerWriterNode = this.waiters.elementAt(index);
            if (readerWriterNode.state == 0) {
                throw new IllegalArgumentException("Upgrade lock");
            }
            readerWriterNode.state = 1;
        }
        while (this.getIndex(currentThread) != 0) {
            try {
                this.wait();
            }
            catch (Exception ex) {
                System.err.println("ReaderWriterLock.lockWrite(): exception.");
                System.err.print(ex.getMessage());
            }
        }
        final ReaderWriterNode readerWriterNode2 = readerWriterNode;
        ++readerWriterNode2.nAcquires;
    }
    
    public synchronized void unlock() {
        final int index = this.getIndex(Thread.currentThread());
        if (index > this.firstWriter()) {
            throw new IllegalArgumentException("Lock not held");
        }
        final ReaderWriterNode readerWriterNode2;
        final ReaderWriterNode readerWriterNode = readerWriterNode2 = this.waiters.elementAt(index);
        --readerWriterNode2.nAcquires;
        if (readerWriterNode.nAcquires == 0) {
            this.waiters.removeElementAt(index);
        }
        this.notifyAll();
    }
    
    private int firstWriter() {
        int n = 0;
        final Enumeration<ReaderWriterNode> elements = (Enumeration<ReaderWriterNode>)this.waiters.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().state == 1) {
                return n;
            }
            ++n;
        }
        return Integer.MAX_VALUE;
    }
    
    private int getIndex(final Thread thread) {
        int n = 0;
        final Enumeration<ReaderWriterNode> elements = (Enumeration<ReaderWriterNode>)this.waiters.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().t == thread) {
                return n;
            }
            ++n;
        }
        return -1;
    }
}
