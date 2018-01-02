// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FingerClient extends SocketClient
{
    public static final int DEFAULT_PORT = 79;
    private static final String __LONG_FLAG = "/W ";
    private transient StringBuffer __query;
    private transient char[] __buffer;
    
    public FingerClient() {
        this.__query = new StringBuffer(64);
        this.__buffer = new char[1024];
        this.setDefaultPort(79);
    }
    
    public String query(final boolean longOutput, final String username) throws IOException {
        final StringBuffer result = new StringBuffer(this.__buffer.length);
        final BufferedReader input = new BufferedReader(new InputStreamReader(this.getInputStream(longOutput, username)));
        while (true) {
            final int read = input.read(this.__buffer, 0, this.__buffer.length);
            if (read <= 0) {
                break;
            }
            result.append(this.__buffer, 0, read);
        }
        input.close();
        return result.toString();
    }
    
    public String query(final boolean longOutput) throws IOException {
        return this.query(longOutput, "");
    }
    
    public InputStream getInputStream(final boolean longOutput, final String username) throws IOException {
        this.__query.setLength(0);
        if (longOutput) {
            this.__query.append("/W ");
        }
        this.__query.append(username);
        this.__query.append("\r\n");
        final DataOutputStream output = new DataOutputStream(new BufferedOutputStream(this._output_, 1024));
        output.writeBytes(this.__query.toString());
        output.flush();
        return this._input_;
    }
    
    public InputStream getInputStream(final boolean longOutput) throws IOException {
        return this.getInputStream(longOutput, "");
    }
}
