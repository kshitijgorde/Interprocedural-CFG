// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.io.InputStream;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class MySQL
{
    private String _page;
    private Hashtable _params;
    String _return;
    String _fullURL;
    
    public MySQL() {
        this._params = new Hashtable();
    }
    
    public MySQL(final String page) {
        this._page = page;
        this._params = new Hashtable();
    }
    
    public void addParam(final String s, final String s2) {
        this._params.put(s, s2);
    }
    
    public void execute() {
        final ExecuteThread executeThread = new ExecuteThread(this);
        this._return = null;
        executeThread.start();
    }
    
    public String getFullURL() {
        final StringBuffer sb = new StringBuffer(this._page);
        sb.append('?');
        final Enumeration<String> keys = this._params.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(s);
            sb.append('=');
            sb.append((String)this._params.get(s));
            sb.append('&');
        }
        return sb.toString();
    }
    
    public String getPage() {
        return this._page;
    }
    
    public Hashtable getParams() {
        return this._params;
    }
    
    public synchronized String getReturn() {
        while (this._return == null) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        return this._return;
    }
    
    public void reset() {
        this._return = null;
        this._page = null;
        this._params = new Hashtable();
    }
    
    public void setPage(final String page) {
        this._page = page;
    }
    
    public synchronized void setResult(final String return1) {
        this._return = return1;
        this.notifyAll();
    }
    
    class ExecuteThread extends Thread
    {
        MySQL _parent;
        URL _target;
        int _buffer_size;
        
        ExecuteThread(final MySQL parent) {
            super("ExecuteThread");
            this._buffer_size = 10;
            if (parent == null) {
                throw new RuntimeException("ExecuteThread parent is null!");
            }
            this._parent = parent;
            parent._return = null;
            String s = parent.getPage() + "?";
            final Hashtable params = parent.getParams();
            final Enumeration<String> keys = params.keys();
            while (keys.hasMoreElements()) {
                final String s2 = keys.nextElement();
                s = s + s2 + "=" + URLEncoder.encode((String)params.get(s2)) + "&";
            }
            final String substring = s.substring(0, s.length() - 1);
            try {
                this._target = new URL(substring);
            }
            catch (Exception ex) {}
        }
        
        @Override
        public void run() {
            final byte[] array = new byte[this._buffer_size];
            String result = "";
            try {
                final InputStream openStream = this._target.openStream();
                int read;
                while ((read = openStream.read(array, 0, this._buffer_size)) > 0) {
                    if (read == this._buffer_size) {
                        result += new String(array);
                    }
                    else {
                        result += new String(array).substring(0, read);
                    }
                }
                openStream.close();
            }
            catch (Exception ex) {}
            this._parent.setResult(result);
        }
        
        public void setBufferSize(final int buffer_size) {
            this._buffer_size = buffer_size;
        }
    }
}
