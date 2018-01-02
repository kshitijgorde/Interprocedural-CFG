// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import logging.LogHolder;
import logging.LogType;
import anon.util.Base64;
import anon.transport.connection.util.QueuedChunkReader;
import com.skype.StreamListener;
import anon.util.ObjectQueue;
import anon.transport.address.IAddress;
import java.io.IOException;
import com.skype.SkypeException;
import com.skype.ApplicationListener;
import com.skype.Stream;
import com.skype.Application;
import anon.transport.address.SkypeAddress;

public class SkypeConnection implements IChunkConnection
{
    public static final int IDLE_TIME_OUT = 480000;
    private final SkypeReader m_reader;
    private final SkypeWriter m_writer;
    private final SkypeAddress m_localAddress;
    private final SkypeAddress m_remoteAddress;
    private final Application m_application;
    private final Stream m_appStream;
    private int m_state;
    private ApplicationListener m_listner;
    
    public SkypeConnection(final Stream appStream) {
        if (appStream == null) {
            throw new NullPointerException("No Application Stream provided");
        }
        this.m_appStream = appStream;
        this.m_reader = new SkypeReader(this.m_appStream);
        this.m_writer = new SkypeWriter(this.m_appStream);
        this.m_application = this.m_appStream.getApplication();
        final String name = this.m_application.getName();
        final String friend = this.m_appStream.getFriend();
        this.m_localAddress = new SkypeAddress("<unresolved>", name);
        this.m_remoteAddress = new SkypeAddress(friend, name);
        this.m_state = 1;
        this.m_listner = new ApplicationListener() {
            public void connected(final Stream stream) throws SkypeException {
            }
            
            public void disconnected(final Stream stream) throws SkypeException {
                if (stream.getId().equals(SkypeConnection.this.m_appStream.getId())) {
                    try {
                        SkypeConnection.this.close(false);
                    }
                    catch (IOException ex) {}
                }
            }
        };
        this.m_application.addApplicationListener(this.m_listner);
    }
    
    public IChunkReader getChunkReader() {
        return this.m_reader;
    }
    
    public IChunkWriter getChunkWriter() {
        return this.m_writer;
    }
    
    public int getCurrentState() {
        return this.m_state;
    }
    
    public IAddress getLocalAddress() {
        return this.m_localAddress;
    }
    
    public IAddress getRemoteAddress() {
        return this.m_remoteAddress;
    }
    
    public int getTimeout() throws ConnectionException {
        return 0;
    }
    
    public void setTimeout(final int n) throws ConnectionException {
        throw new UnsuportedCommandException("Timeout could not be changed for Connection of Skype");
    }
    
    public void close(final boolean b) throws IOException {
        if (this.m_state != 2) {
            this.m_state = 2;
            this.m_application.removeApplicationListener(this.m_listner);
            this.m_reader.tearDown();
            this.m_writer.close();
            try {
                if (b) {
                    this.m_appStream.disconnect();
                }
            }
            catch (SkypeException ex) {
                throw new IOException(ex.getMessage());
            }
        }
    }
    
    public void close() throws IOException {
        this.close(true);
    }
    
    private static class SkypeReader implements IChunkReader
    {
        public static final int MAX_MESSAGE_LENGTH = 65535;
        private ObjectQueue m_readBuffer;
        private Stream m_appStream;
        private StreamListener m_listner;
        private QueuedChunkReader m_baseReader;
        
        public SkypeReader(final Stream appStream) {
            this.m_appStream = appStream;
            this.m_readBuffer = new ObjectQueue();
            this.m_baseReader = new QueuedChunkReader(this.m_readBuffer);
            this.m_listner = new StreamListener() {
                private final /* synthetic */ SkypeReader this$0 = this$0;
                
                public void textReceived(final String s) throws SkypeException {
                    final byte[] decode = Base64.decode(s);
                    if (decode != null && decode.length > 0) {
                        this.this$0.m_readBuffer.push(decode);
                    }
                }
                
                public void datagramReceived(final String s) throws SkypeException {
                    LogHolder.log(4, LogType.TRANSPORT, "Received Datagram from Skype, but we only expect Streams.");
                }
            };
            this.m_appStream.addStreamListener(this.m_listner);
        }
        
        public int availableChunks() throws ConnectionException {
            return this.m_baseReader.availableChunks();
        }
        
        public byte[] readChunk() throws ConnectionException {
            return this.m_baseReader.readChunk();
        }
        
        public void close() throws IOException {
            this.m_appStream.removeStreamListener(this.m_listner);
            this.m_baseReader.close();
        }
        
        public void tearDown() throws IOException {
            this.m_appStream.removeStreamListener(this.m_listner);
            this.m_baseReader.tearDown();
        }
    }
    
    private static class SkypeWriter implements IChunkWriter
    {
        private Stream m_appStream;
        private boolean m_isClosed;
        
        public SkypeWriter(final Stream appStream) {
            this.m_appStream = appStream;
            this.m_isClosed = false;
        }
        
        public void writeChunk(final byte[] array) throws ConnectionException {
            if (!this.m_isClosed) {
                final String encode = Base64.encode(array, false);
                try {
                    this.m_appStream.write(encode);
                }
                catch (SkypeException ex) {
                    throw new ConnectionException(ex);
                }
            }
        }
        
        public void close() throws IOException {
            this.m_isClosed = true;
        }
    }
}
