// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.util.Spliterator;
import java.util.stream.StreamSupport;
import java.nio.CharBufferSpliterator;
import java.util.stream.IntStream;
import java.nio.ByteOrder;
import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;
import java.nio.BufferUnderflowException;
import java.nio.StringCharBuffer;
import java.io.IOException;
import java.nio.HeapCharBuffer;
import java.nio.Buffer;
import java.nio.CharBuffer;

public final class WString implements CharSequence, Comparable
{
    private String string;
    
    public WString(final String s) {
        if (s == null) {
            throw new NullPointerException("String initializer must be non-null");
        }
        this.string = s;
    }
    
    public String toString() {
        return this.string;
    }
    
    public boolean equals(final Object o) {
        return o instanceof WString && this.toString().equals(o.toString());
    }
    
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    public int compareTo(final Object o) {
        return this.toString().compareTo(o.toString());
    }
    
    public int length() {
        return this.toString().length();
    }
    
    public char charAt(final int index) {
        return this.toString().charAt(index);
    }
    
    public CharSequence subSequence(final int start, final int end) {
        return CharBuffer.wrap(this.toString()).subSequence(start, end);
    }
}
