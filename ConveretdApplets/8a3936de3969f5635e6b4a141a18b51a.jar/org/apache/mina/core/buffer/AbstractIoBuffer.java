// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.buffer;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.EOFException;
import java.io.ObjectStreamClass;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public abstract class AbstractIoBuffer extends IoBuffer
{
    private boolean autoExpand;
    private boolean recapacityAllowed;
    private int minimumCapacity;
    private int mark;
    
    protected AbstractIoBuffer(final IoBufferAllocator allocator, final int minimumCapacity) {
        this.recapacityAllowed = true;
        this.mark = -1;
        IoBuffer.setAllocator(allocator);
        this.recapacityAllowed = true;
        this.minimumCapacity = minimumCapacity;
    }
    
    protected abstract void buf(final ByteBuffer p0);
    
    @Override
    public final int capacity() {
        return this.buf().capacity();
    }
    
    @Override
    public final boolean isAutoExpand() {
        return this.autoExpand && this.recapacityAllowed;
    }
    
    @Override
    public final IoBuffer setAutoExpand(final boolean b) {
        if (!this.recapacityAllowed) {
            throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
        }
        this.autoExpand = true;
        return this;
    }
    
    private IoBuffer expand(int n, int n2, final boolean b) {
        if (!this.recapacityAllowed) {
            throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
        }
        n += n2;
        if (b) {
            int n3;
            if ((n2 = n) < 0) {
                n3 = Integer.MAX_VALUE;
            }
            else {
                final int highestOneBit = Integer.highestOneBit(n2);
                final int n4;
                n3 = (((n4 = highestOneBit << ((highestOneBit < n2) ? 1 : 0)) < 0) ? Integer.MAX_VALUE : n4);
            }
            n2 = n3;
        }
        else {
            n2 = n;
        }
        if (n2 > this.buf().capacity()) {
            final int n5 = n2;
            if (!this.recapacityAllowed) {
                throw new IllegalStateException("Derived buffers and their parent can't be expanded.");
            }
            if (n5 > this.buf().capacity()) {
                final int position = this.buf().position();
                final int limit = this.buf().limit();
                final ByteOrder order = this.buf().order();
                final ByteBuffer buf = this.buf();
                final ByteBuffer allocateNioBuffer = IoBuffer.getAllocator().allocateNioBuffer(n5, this.buf().isDirect());
                buf.clear();
                allocateNioBuffer.put(buf);
                this.buf(allocateNioBuffer);
                this.buf().limit(limit);
                if (this.mark >= 0) {
                    this.buf().position(this.mark);
                    this.buf().mark();
                }
                this.buf().position(position);
                this.buf().order(order);
            }
        }
        if (n > this.buf().limit()) {
            this.buf().limit(n);
        }
        return this;
    }
    
    @Override
    public final int position() {
        return this.buf().position();
    }
    
    @Override
    public final IoBuffer position(final int n) {
        this.autoExpand(n, 0);
        this.buf().position(n);
        if (this.mark > n) {
            this.mark = -1;
        }
        return this;
    }
    
    @Override
    public final int limit() {
        return this.buf().limit();
    }
    
    @Override
    public final IoBuffer limit(final int n) {
        this.autoExpand(n, 0);
        this.buf().limit(n);
        if (this.mark > n) {
            this.mark = -1;
        }
        return this;
    }
    
    @Override
    public final IoBuffer mark() {
        this.buf().mark();
        this.mark = this.buf().position();
        return this;
    }
    
    @Override
    public final IoBuffer reset() {
        this.buf().reset();
        return this;
    }
    
    @Override
    public final IoBuffer flip() {
        this.buf().flip();
        this.mark = -1;
        return this;
    }
    
    @Override
    public final int remaining() {
        return this.buf().limit() - this.buf().position();
    }
    
    @Override
    public final boolean hasRemaining() {
        return this.buf().limit() > this.buf().position();
    }
    
    @Override
    public final byte get() {
        return this.buf().get();
    }
    
    public final IoBuffer put(final byte b) {
        this.autoExpand(1);
        this.buf().put(b);
        return this;
    }
    
    @Override
    public final byte get(final int n) {
        return this.buf().get(n);
    }
    
    public final IoBuffer get(final byte[] array, final int n, final int n2) {
        this.buf().get(array, n, n2);
        return this;
    }
    
    public final IoBuffer put(final byte[] array, final int n, final int n2) {
        this.autoExpand(n2);
        this.buf().put(array, n, n2);
        return this;
    }
    
    @Override
    public final IoBuffer compact() {
        this.remaining();
        if (this.buf().capacity() == 0) {
            return this;
        }
        this.buf().compact();
        this.mark = -1;
        return this;
    }
    
    @Override
    public final ByteOrder order() {
        return this.buf().order();
    }
    
    @Override
    public final IoBuffer order(final ByteOrder byteOrder) {
        this.buf().order(byteOrder);
        return this;
    }
    
    @Override
    public int hashCode() {
        int n = 1;
        for (int position = this.buf().position(), i = this.buf().limit() - 1; i >= position; --i) {
            n = n * 31 + this.get(i);
        }
        return n;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof IoBuffer)) {
            return false;
        }
        final IoBuffer ioBuffer = (IoBuffer)o;
        if (this.remaining() != ioBuffer.remaining()) {
            return false;
        }
        for (int position = this.buf().position(), i = this.buf().limit() - 1, n = ioBuffer.limit() - 1; i >= position; --i, --n) {
            if (this.get(i) != ioBuffer.get(n)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.buf().isDirect()) {
            sb.append("DirectBuffer");
        }
        else {
            sb.append("HeapBuffer");
        }
        sb.append("[pos=");
        sb.append(this.buf().position());
        sb.append(" lim=");
        sb.append(this.buf().limit());
        sb.append(" cap=");
        sb.append(this.buf().capacity());
        sb.append(": ");
        sb.append(IoBufferHexDumper.getHexdump(this, 16));
        sb.append(']');
        return sb.toString();
    }
    
    @Override
    public final IoBuffer put(final IoBuffer ioBuffer) {
        final ByteBuffer buf = ioBuffer.buf();
        this.autoExpand(buf.remaining());
        this.buf().put(buf);
        return this;
    }
    
    @Override
    public final String getHexDump() {
        return IoBufferHexDumper.getHexdump(this, Integer.MAX_VALUE);
    }
    
    @Override
    public final Object getObject(final ClassLoader classLoader) throws ClassNotFoundException {
        if (!this.prefixedDataAvailable(4, Integer.MAX_VALUE)) {
            throw new BufferUnderflowException();
        }
        final int int1;
        if ((int1 = this.buf().getInt()) <= 4) {
            throw new BufferDataException("Object length should be greater than 4: " + int1);
        }
        final int limit = this.buf().limit();
        this.limit(this.buf().position() + int1);
        try {
            return new ObjectInputStream(this, new InputStream() {
                @Override
                public final int available() {
                    return AbstractIoBuffer.this.remaining();
                }
                
                @Override
                public final synchronized void mark(final int n) {
                    AbstractIoBuffer.this.mark();
                }
                
                @Override
                public final boolean markSupported() {
                    return true;
                }
                
                @Override
                public final int read() {
                    if (AbstractIoBuffer.this.hasRemaining()) {
                        return AbstractIoBuffer.this.get() & 0xFF;
                    }
                    return -1;
                }
                
                @Override
                public final int read(final byte[] array, final int n, int min) {
                    final int remaining;
                    if ((remaining = AbstractIoBuffer.this.remaining()) > 0) {
                        min = Math.min(remaining, min);
                        AbstractIoBuffer.this.get(array, n, min);
                        return min;
                    }
                    return -1;
                }
                
                @Override
                public final synchronized void reset() {
                    AbstractIoBuffer.this.reset();
                }
                
                @Override
                public final long skip(final long n) {
                    int n2;
                    if (n > 2147483647L) {
                        n2 = AbstractIoBuffer.this.remaining();
                    }
                    else {
                        n2 = Math.min(AbstractIoBuffer.this.remaining(), (int)n);
                    }
                    AbstractIoBuffer.this.skip(n2);
                    return n2;
                }
            }) {
                @Override
                protected final ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
                    final int read;
                    if ((read = this.read()) < 0) {
                        throw new EOFException();
                    }
                    switch (read) {
                        case 0: {
                            return super.readClassDescriptor();
                        }
                        case 1: {
                            return ObjectStreamClass.lookup(Class.forName(this.readUTF(), true, classLoader));
                        }
                        default: {
                            throw new StreamCorruptedException("Unexpected class descriptor type: " + read);
                        }
                    }
                }
                
                @Override
                protected final Class<?> resolveClass(final ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    final String name = objectStreamClass.getName();
                    try {
                        return Class.forName(name, false, classLoader);
                    }
                    catch (ClassNotFoundException ex) {
                        return super.resolveClass(objectStreamClass);
                    }
                }
            }.readObject();
        }
        catch (IOException ex) {
            throw new BufferDataException(ex);
        }
        finally {
            this.limit(limit);
        }
    }
    
    @Override
    public final IoBuffer putObject(final Object o) {
        final int position = this.buf().position();
        this.skip(4);
        try {
            final ObjectOutputStream objectOutputStream;
            (objectOutputStream = new ObjectOutputStream(this, new OutputStream() {
                @Override
                public final void write(final byte[] array, final int n, final int n2) {
                    AbstractIoBuffer.this.put(array, n, n2);
                }
                
                @Override
                public final void write(final int n) {
                    AbstractIoBuffer.this.put((byte)n);
                }
            }) {
                @Override
                protected final void writeClassDescriptor(final ObjectStreamClass objectStreamClass) throws IOException {
                    if (objectStreamClass.forClass().isPrimitive()) {
                        this.write(0);
                        super.writeClassDescriptor(objectStreamClass);
                        return;
                    }
                    this.write(1);
                    this.writeUTF(objectStreamClass.getName());
                }
            }).writeObject(o);
            objectOutputStream.flush();
        }
        catch (IOException ex) {
            throw new BufferDataException(ex);
        }
        final int position2 = this.buf().position();
        this.position(position);
        final int n = position2 - position - 4;
        this.autoExpand(4);
        this.buf().putInt(n);
        this.position(position2);
        return this;
    }
    
    @Override
    public final boolean prefixedDataAvailable(final int n, final int n2) {
        if (this.remaining() < n) {
            return false;
        }
        int int1 = 0;
        switch (n) {
            case 1: {
                int1 = (short)(this.get(this.buf().position()) & 0xFF);
                break;
            }
            case 2: {
                int1 = (this.buf().getShort(this.buf().position()) & 0xFFFF);
                break;
            }
            case 4: {
                int1 = this.buf().getInt(this.buf().position());
                break;
            }
            default: {
                throw new IllegalArgumentException("prefixLength: " + n);
            }
        }
        if (int1 < 0 || int1 > n2) {
            throw new BufferDataException("dataLength: " + int1);
        }
        return this.remaining() - n >= int1;
    }
    
    public final IoBuffer skip(final int n) {
        this.autoExpand(n);
        return this.position(this.buf().position() + n);
    }
    
    private IoBuffer autoExpand(int n) {
        if (this.isAutoExpand()) {
            final int n2 = n;
            n = 1;
            this.expand(this.buf().position(), n2, true);
        }
        return this;
    }
    
    private IoBuffer autoExpand(final int n, final int n2) {
        if (this.isAutoExpand()) {
            this.expand(n, 0, true);
        }
        return this;
    }
}
