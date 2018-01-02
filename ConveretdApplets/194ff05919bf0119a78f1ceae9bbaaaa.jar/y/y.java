// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.NoSuchElementException;
import java.io.EOFException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

public final class y implements ak
{
    private String[] a;
    
    public y() {
        this.a = new String[8192];
    }
    
    public final void a(final InputStream inputStream) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                final short short1 = dataInputStream.readShort();
                String utf;
                try {
                    utf = dataInputStream.readUTF();
                }
                catch (EOFException ex) {
                    throw new IOException("bad little dict");
                }
                if (short1 >= 0) {
                    this.a[short1] = utf;
                }
            }
        }
        catch (EOFException ex2) {}
        catch (NoSuchElementException ex3) {}
    }
    
    public final String a(final int n) {
        final String s;
        if ((s = this.a[n & 0xFFFF]) == null) {
            return "X";
        }
        return s;
    }
}
