// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.shared;

import java.io.EOFException;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Hashtable;
import java.io.DataInputStream;

public class StreamSplit
{
    private DataInputStream m_dis;
    private boolean m_streamEnd;
    
    public StreamSplit(final DataInputStream dis) {
        this.m_dis = dis;
        this.m_streamEnd = false;
    }
    
    public Hashtable readHeaders() throws IOException {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        int n = 0;
        while (true) {
            final String line = this.m_dis.readLine();
            if (line == null) {
                this.m_streamEnd = true;
                break;
            }
            if (line.equals("")) {
                if (n != 0) {
                    break;
                }
            }
            else {
                n = 1;
            }
            final int index = line.indexOf(":");
            if (index == -1) {
                continue;
            }
            hashtable.put(line.substring(0, index).toLowerCase(), line.substring(index + 1).trim());
        }
        return hashtable;
    }
    
    public Hashtable readHeaders(final URLConnection urlConnection) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        int n = 1;
        while (true) {
            final String headerFieldKey = urlConnection.getHeaderFieldKey(n);
            if (headerFieldKey == null) {
                break;
            }
            hashtable.put(headerFieldKey.toLowerCase(), urlConnection.getHeaderField(n));
            ++n;
        }
        return hashtable;
    }
    
    public void skipToBoundary(final String s) throws IOException {
        this.readToBoundary(s);
    }
    
    public byte[] readToBoundary(final String s) throws IOException {
        final ResizableByteArrayOutputStream resizableByteArrayOutputStream = new ResizableByteArrayOutputStream();
        StringBuffer sb = new StringBuffer();
        int n = 0;
        int n2 = 0;
        while (true) {
            byte byte1;
            try {
                byte1 = this.m_dis.readByte();
            }
            catch (EOFException ex) {
                this.m_streamEnd = true;
                break;
            }
            if (byte1 == 10 || byte1 == 13) {
                final String string = sb.toString();
                if (s != null && string.startsWith(s)) {
                    if (string.substring(s.length()).equals("--")) {
                        this.m_streamEnd = true;
                    }
                    n2 = n;
                    break;
                }
                sb = new StringBuffer();
                n = n2 + 1;
            }
            else {
                sb.append((char)byte1);
            }
            ++n2;
            resizableByteArrayOutputStream.write(byte1);
        }
        resizableByteArrayOutputStream.close();
        resizableByteArrayOutputStream.resize(n2);
        return resizableByteArrayOutputStream.toByteArray();
    }
    
    public boolean isAtStreamEnd() {
        return this.m_streamEnd;
    }
}
