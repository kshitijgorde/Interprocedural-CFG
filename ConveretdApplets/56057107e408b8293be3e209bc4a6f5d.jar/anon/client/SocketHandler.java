// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import anon.transport.connection.IStreamConnection;
import java.util.Observable;

public class SocketHandler extends Observable
{
    private IStreamConnection m_underlyingConnection;
    private SocketInputStreamImplementation m_socketInputStream;
    private SocketOutputStreamImplementation m_socketOutputStream;
    private Object m_internalSynchronization;
    
    public SocketHandler(final IStreamConnection underlyingConnection) throws IOException {
        this.m_underlyingConnection = underlyingConnection;
        if (this.m_underlyingConnection.getCurrentState() == 2) {
            throw new IOException("Connection allready closed");
        }
        this.m_internalSynchronization = new Object();
        this.m_socketInputStream = new SocketInputStreamImplementation(this.m_underlyingConnection.getInputStream());
        this.m_socketOutputStream = new SocketOutputStreamImplementation(this.m_underlyingConnection.getOutputStream());
    }
    
    public void closeSocket() {
        try {
            this.m_underlyingConnection.close();
        }
        catch (IOException ex) {}
    }
    
    public InputStream getInputStream() {
        return this.m_socketInputStream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_socketOutputStream;
    }
    
    private void handleIOException(final IOException ex) {
        synchronized (this.m_internalSynchronization) {
            this.setChanged();
            this.notifyObservers(ex);
        }
    }
    
    private void handleEndOfInputStream() {
        this.handleIOException(new IOException("SocketHandler: handleEndOfInputStream(): Unexpected end of input stream."));
    }
    
    private void handleInputStreamClose() {
    }
    
    private void handleOutputStreamClose() {
    }
    
    private class SocketInputStreamImplementation extends InputStream
    {
        private InputStream m_underlyingStream;
        
        public SocketInputStreamImplementation(final InputStream underlyingStream) {
            this.m_underlyingStream = underlyingStream;
        }
        
        public int read() throws IOException {
            int read;
            try {
                read = this.m_underlyingStream.read();
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
            if (read == -1) {
                SocketHandler.this.handleEndOfInputStream();
            }
            return read;
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            int read;
            try {
                read = this.m_underlyingStream.read(array, n, n2);
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
            if (read == -1) {
                SocketHandler.this.handleEndOfInputStream();
            }
            return read;
        }
        
        public int available() throws IOException {
            int available;
            try {
                available = this.m_underlyingStream.available();
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
            return available;
        }
        
        public void close() {
            SocketHandler.this.handleInputStreamClose();
        }
    }
    
    private class SocketOutputStreamImplementation extends OutputStream
    {
        private OutputStream m_underlyingStream;
        
        public SocketOutputStreamImplementation(final OutputStream underlyingStream) {
            this.m_underlyingStream = underlyingStream;
        }
        
        public void write(final int n) throws IOException {
            try {
                this.m_underlyingStream.write(n);
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            try {
                this.m_underlyingStream.write(array, n, n2);
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
        }
        
        public void flush() throws IOException {
            try {
                this.m_underlyingStream.flush();
            }
            catch (IOException ex) {
                SocketHandler.this.handleIOException(ex);
                throw ex;
            }
        }
        
        public void close() {
            SocketHandler.this.handleOutputStreamClose();
        }
    }
}
