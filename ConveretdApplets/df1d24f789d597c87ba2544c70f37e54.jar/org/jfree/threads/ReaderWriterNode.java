// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.threads;

class ReaderWriterNode
{
    static final int READER = 0;
    static final int WRITER = 1;
    Thread t;
    int state;
    int nAcquires;
    
    ReaderWriterNode(final Thread t, final int state) {
        this.t = t;
        this.state = state;
        this.nAcquires = 0;
    }
}
