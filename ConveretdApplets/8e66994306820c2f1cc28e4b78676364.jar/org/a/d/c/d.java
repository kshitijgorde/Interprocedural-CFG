// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d.c;

import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

public class d extends c
{
    private Object b;
    private int c;
    private static String[] z;
    
    public d() {
        this.c = 0;
    }
    
    public d(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
        this.c = 0;
    }
    
    public void a() {
        try {
            this.close();
        }
        catch (IOException ex) {}
        super.a();
    }
    
    public boolean a() {
        return this.readBoolean();
    }
    
    public void close() throws IOException {
        try {
            final ObjectInput objectInput = (ObjectInput)this.b;
            this.b = null;
            if (objectInput.readBoolean()) {
                objectInput.close();
            }
        }
        catch (Exception ex) {
            this.b = null;
        }
    }
    
    public int available() throws IOException {
        if (!this.readBoolean()) {
            return 0;
        }
        return 1;
    }
    
    public int read(final byte[] array) throws IOException {
        if (this.b == null) {
            throw new IOException(d.z[2]);
        }
        return ((ObjectInput)this.b).read(array);
    }
    
    public void write(final byte[] array) throws IOException {
        if (this.b == null) {
            throw new IOException(d.z[1]);
        }
        ((ObjectOutput)this.b).write(array);
    }
    
    public final boolean readBoolean() {
        return this.b != null;
    }
    
    public short readShort() {
        try {
            if (((ObjectInput)this.b).readBoolean()) {
                return 1;
            }
            return 0;
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public void writeObject(final Object b) throws IOException {
        if (!(b instanceof ObjectInput)) {
            throw new ClassCastException(d.z[0]);
        }
        if (!(b instanceof ObjectOutput)) {
            throw new ClassCastException(d.z[0]);
        }
        this.b = b;
        try {
            final ObjectOutput objectOutput = (ObjectOutput)this.b;
            if (!((ObjectInput)this.b).readBoolean()) {
                objectOutput.writeObject(this);
            }
        }
        catch (Exception ex) {
            this.b = null;
        }
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u000eb\u001bVb3|\u001b\u0018%5h\u0014\u0013f.*\u0017\u0005%4e\nVd)y\u0017\u0011k;h\u0012\u0013%.e^\u0017%0k\b\u0017+3eP9g0o\u001d\u0002L4z\u000b\u0002%9f\u001f\u0005v".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'Z';
                    break;
                }
                case 1: {
                    c2 = '\n';
                    break;
                }
                case 2: {
                    c2 = '~';
                    break;
                }
                case 3: {
                    c2 = 'v';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0014e^>j)~6\u0019j1*\u001a\u0013c3d\u001b\u0012%.e^\u0001w3~\u001bVq5$".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'Z';
                    break;
                }
                case 1: {
                    c4 = '\n';
                    break;
                }
                case 2: {
                    c4 = '~';
                    break;
                }
                case 3: {
                    c4 = 'v';
                    break;
                }
                default: {
                    c4 = '\u0005';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0014e^>j)~6\u0019j1*\u001a\u0013c3d\u001b\u0012%.e^\u0004`;n^\u0010w5gP".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'Z';
                    break;
                }
                case 1: {
                    c6 = '\n';
                    break;
                }
                case 2: {
                    c6 = '~';
                    break;
                }
                case 3: {
                    c6 = 'v';
                    break;
                }
                default: {
                    c6 = '\u0005';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        d.z = z;
    }
}
