// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.util;

import java.io.IOException;
import java.io.OutputStream;

public class TeeStream extends OutputStream
{
    protected OutputStream[] _list;
    
    public TeeStream(final OutputStream o1, final OutputStream o2) {
        this._list = null;
        Debug.checkArgument(o1 != null && o2 != null, "o1 != null && o2 != null");
        (this._list = new OutputStream[2])[0] = o1;
        this._list[1] = o2;
    }
    
    public TeeStream(final OutputStream[] list) {
        this._list = null;
        Debug.checkArgument(list.length > 0, "list.length > 0");
        for (int i = 0; i < list.length; ++i) {
            Debug.checkArgument(list[i] != null, "list[i] != null");
        }
        this._list = list;
    }
    
    public void close() throws IOException {
        for (int i = 0; i < this._list.length; ++i) {
            this._list[i].close();
        }
    }
    
    public void flush() throws IOException {
        for (int i = 0; i < this._list.length; ++i) {
            this._list[i].flush();
        }
    }
    
    public void write(final int b) throws IOException {
        for (int i = 0; i < this._list.length; ++i) {
            this._list[i].write(b);
        }
    }
    
    public void write(final byte[] b) throws IOException {
        for (int i = 0; i < this._list.length; ++i) {
            this._list[i].write(b);
        }
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        for (int i = 0; i < this._list.length; ++i) {
            this._list[i].write(b, off, len);
        }
    }
}
