// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.DataInputStream;

public final class ByteSequence extends DataInputStream
{
    private ByteArrayStream byte_stream;
    
    public ByteSequence(final byte[] bytes) {
        super(new ByteArrayStream(bytes));
        this.byte_stream = (ByteArrayStream)super.in;
    }
    
    public final int getIndex() {
        return this.byte_stream.getPosition();
    }
    
    final void unreadByte() {
        this.byte_stream.unreadByte();
    }
    
    private static final class ByteArrayStream extends ByteArrayInputStream
    {
        ByteArrayStream(final byte[] bytes) {
            super(bytes);
        }
        
        final int getPosition() {
            return super.pos;
        }
        
        final void unreadByte() {
            if (super.pos > 0) {
                --super.pos;
            }
        }
    }
}
