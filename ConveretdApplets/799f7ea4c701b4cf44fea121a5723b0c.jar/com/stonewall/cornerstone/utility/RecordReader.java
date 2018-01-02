// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.Reader;

public class RecordReader
{
    public static final int Delimiter = 3;
    final int delimiter;
    final Reader reader;
    
    public RecordReader(final Reader reader) {
        this(reader, 3);
    }
    
    public RecordReader(final Reader reader, final int delimiter) {
        this.reader = reader;
        this.delimiter = delimiter;
    }
    
    public String next() {
        String result = null;
        final StringBuilder sb = new StringBuilder();
        try {
            while (true) {
                final int c = this.reader.read();
                if (c == -1 || c == this.delimiter) {
                    break;
                }
                sb.append((char)c);
            }
            result = ((sb.length() > 0) ? sb.toString() : null);
        }
        catch (IOException e) {
            result = null;
        }
        return result;
    }
    
    public void close() {
        try {
            this.reader.close();
        }
        catch (IOException ex) {}
    }
}
