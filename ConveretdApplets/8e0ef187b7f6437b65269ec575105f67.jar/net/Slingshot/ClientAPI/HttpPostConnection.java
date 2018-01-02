// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.URL;

class HttpPostConnection
{
    public short Flags;
    private URL url;
    private URLConnection Conn;
    private byte[] outBuf;
    private int outBufLen;
    private long connSignature;
    private long connId;
    private boolean bufferedConnection;
    
    HttpPostConnection(final URL url) {
        this.bufferedConnection = false;
        this.setURL(url);
        this.outBuf = new byte[2048];
    }
    
    public void setBuffered() {
        this.bufferedConnection = true;
    }
    
    public void setSignatureConnId(final long connSignature, final long connId) {
        this.connSignature = connSignature;
        this.connId = connId;
    }
    
    public void setURL(final URL url) {
        this.url = url;
    }
    
    public void sendPing() {
        final byte[] array = new byte[255];
        final int n = 0;
        final String s = "PING";
        final int packAny = MessageFormat.packAny((short)(-66), (byte)11, (short)s.length(), (short)0, (short)0, s.getBytes(), array, n);
        if (this.outBufLen == 0) {
            this.insertRequestHeader();
        }
        this.outBufPut(array, packAny);
    }
    
    public void outBufPut(final byte[] array, final int n) {
        final int outBufLen = this.outBufLen + n;
        if (outBufLen >= this.outBuf.length) {
            final byte[] outBuf = new byte[outBufLen - outBufLen % 1024 + 1024];
            System.arraycopy(this.outBuf, 0, outBuf, 0, this.outBufLen);
            this.outBuf = outBuf;
        }
        System.arraycopy(array, 0, this.outBuf, this.outBufLen, n);
        this.outBufLen = outBufLen;
    }
    
    public void close() {
        this.outBufLen = 0;
        this.Conn = null;
    }
    
    public void shutdown() {
        this.close();
        this.url = null;
    }
    
    public void send() throws IOException, FileNotFoundException {
        if (this.outBufLen > 0) {
            this.connect();
            final OutputStream outputStream = this.Conn.getOutputStream();
            outputStream.write(this.outBuf, 0, this.outBufLen);
            outputStream.close();
            this.Conn.getInputStream().close();
            this.outBufLen = 0;
            this.Conn = null;
        }
    }
    
    private void sendBufferedProxy() {
        final byte[] array = new byte[255];
        final int n = 0;
        if (this.bufferedConnection) {
            this.outBufPut(array, MessageFormat.setEnvelope((short)(-72), (byte)11, (short)0, (short)0, array, n));
        }
    }
    
    private void insertRequestHeader() {
        final byte[] array = new byte[256];
        final int setEnvelope;
        final int packShort = MessageFormat.packShort((short)(-83), (short)0, this.Flags, array, MessageFormat.packLong((short)(-71), (short)0, this.connId, array, MessageFormat.packLong((short)(-70), (short)0, this.connSignature, array, setEnvelope = MessageFormat.setEnvelope((short)(-69), (byte)11, (short)0, (short)0, array, 0))));
        MessageFormat.setEnvelope((short)(-69), (byte)11, (short)(packShort - setEnvelope), (short)0, array, 0);
        this.outBufPut(array, packShort);
        this.sendBufferedProxy();
    }
    
    public void send(final byte[] array, final int n) throws IOException {
        if (this.outBufLen == 0) {
            this.insertRequestHeader();
        }
        this.outBufPut(array, n);
        this.send();
    }
    
    public void bufferSend(final byte[] array, final int n) throws IOException {
        if (this.outBufLen == 0) {
            this.insertRequestHeader();
        }
        this.outBufPut(array, n);
    }
    
    public boolean bufferToSend() {
        return this.outBufLen > 0;
    }
    
    public void connect() throws IOException {
        if (this.Conn == null) {
            if (this.url == null) {
                throw new IOException("URL not set");
            }
            (this.Conn = new URL(this.url.toString()).openConnection()).setUseCaches(false);
            this.Conn.setRequestProperty("Accept", "text/html");
            this.Conn.setRequestProperty("Content-type", "application/octet-stream");
            this.Conn.setDoOutput(true);
            this.Conn.setDoInput(true);
            this.Conn.setAllowUserInteraction(false);
        }
    }
}
