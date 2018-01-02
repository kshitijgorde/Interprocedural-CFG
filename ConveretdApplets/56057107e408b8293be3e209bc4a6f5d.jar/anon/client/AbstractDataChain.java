// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.ByteArrayInputStream;
import anon.TooMuchDataForPacketException;
import java.io.InterruptedIOException;
import java.util.Observable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Vector;
import java.util.Observer;
import anon.AnonChannel;

public abstract class AbstractDataChain implements AnonChannel, Observer, Runnable
{
    private DataChainInputStreamImplementation m_inputStream;
    private DataChainOutputStreamImplementation m_outputStream;
    private Vector m_messageQueuesNotifications;
    private IDataChannelCreator m_channelCreator;
    private DataChainErrorListener m_errorListener;
    private boolean m_chainClosed;
    private Thread m_downstreamThread;
    
    public AbstractDataChain(final IDataChannelCreator channelCreator, final DataChainErrorListener errorListener) {
        this.m_channelCreator = channelCreator;
        this.m_errorListener = errorListener;
        this.m_inputStream = new DataChainInputStreamImplementation();
        this.m_outputStream = new DataChainOutputStreamImplementation();
        this.m_messageQueuesNotifications = new Vector();
        this.m_chainClosed = false;
        (this.m_downstreamThread = new Thread(this, "AbstractDataChain: Downstream-Organizer Thread")).setDaemon(true);
        this.m_downstreamThread.start();
    }
    
    public InputStream getInputStream() {
        return this.m_inputStream;
    }
    
    public OutputStream getOutputStream() {
        return this.m_outputStream;
    }
    
    public boolean isClosed() {
        return this.m_chainClosed;
    }
    
    public void close() {
        if (!this.m_chainClosed) {
            this.m_chainClosed = true;
            try {
                this.getOutputStream().close();
            }
            catch (IOException ex) {}
            try {
                this.getInputStream().close();
            }
            catch (IOException ex2) {}
            this.closeDataChain();
            try {
                this.m_downstreamThread.join();
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof InternalChannelMessageQueue) {
            synchronized (this.m_messageQueuesNotifications) {
                this.m_messageQueuesNotifications.addElement(observable);
                this.m_messageQueuesNotifications.notify();
            }
        }
    }
    
    protected Vector getMessageQueuesNotificationsList() {
        return this.m_messageQueuesNotifications;
    }
    
    protected void addInputStreamQueueEntry(final DataChainInputStreamQueueEntry dataChainInputStreamQueueEntry) {
        this.m_inputStream.addToQueue(dataChainInputStreamQueueEntry);
    }
    
    protected AbstractDataChannel createDataChannel() {
        return this.m_channelCreator.createDataChannel(this);
    }
    
    protected void interruptDownstreamThread() {
        this.m_downstreamThread.interrupt();
    }
    
    protected void propagateConnectionError() {
        this.m_errorListener.dataChainErrorSignaled();
    }
    
    public abstract int getOutputBlockSize();
    
    public abstract void createPacketPayload(final DataChainSendOrderStructure p0);
    
    public abstract void run();
    
    protected abstract void orderPacket(final DataChainSendOrderStructure p0);
    
    protected abstract void outputStreamClosed() throws IOException;
    
    protected abstract void closeDataChain();
    
    private class DataChainOutputStreamImplementation extends OutputStream
    {
        private boolean m_closed;
        private Object m_internalStreamSynchronization;
        
        public DataChainOutputStreamImplementation() {
            this.m_closed = false;
            this.m_internalStreamSynchronization = new Object();
        }
        
        public void write(final int n) throws IOException {
            this.write(new byte[] { (byte)n });
        }
        
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            synchronized (this.m_internalStreamSynchronization) {
                if (this.m_closed) {
                    throw new IOException("Stream is closed.");
                }
                final byte[] array2 = new byte[n2];
                System.arraycopy(array, n, array2, 0, n2);
                final DataChainSendOrderStructure dataChainSendOrderStructure = new DataChainSendOrderStructure(array2);
                synchronized (dataChainSendOrderStructure.getSynchronizationObject()) {
                    AbstractDataChain.this.orderPacket(dataChainSendOrderStructure);
                    if (!dataChainSendOrderStructure.isProcessingDone()) {
                        try {
                            dataChainSendOrderStructure.getSynchronizationObject().wait();
                        }
                        catch (InterruptedException ex) {
                            throw new InterruptedIOException("InterruptedException: " + ex.toString());
                        }
                    }
                    if (dataChainSendOrderStructure.getThrownException() != null) {
                        throw dataChainSendOrderStructure.getThrownException();
                    }
                    if (dataChainSendOrderStructure.getProcessedBytes() < n2) {
                        throw new TooMuchDataForPacketException(dataChainSendOrderStructure.getProcessedBytes());
                    }
                }
            }
        }
        
        public void close() throws IOException {
            if (!this.m_closed) {
                synchronized (this.m_internalStreamSynchronization) {
                    this.m_closed = true;
                    AbstractDataChain.this.outputStreamClosed();
                }
            }
        }
    }
    
    private class DataChainInputStreamImplementation extends InputStream
    {
        private boolean m_closed;
        private Vector m_queueEntries;
        
        private DataChainInputStreamImplementation() {
            this.m_queueEntries = new Vector();
            this.m_closed = false;
        }
        
        public int read() throws IOException {
            final byte[] array = { 0 };
            int i;
            do {
                i = this.read(array);
            } while (i == 0);
            int read = -1;
            if (i == 1) {
                read = new ByteArrayInputStream(array).read();
            }
            return read;
        }
        
        public int read(final byte[] array, int length, int n) throws IOException {
            int n2 = 0;
            if (array.length < length) {
                length = array.length;
            }
            if (array.length < length + n) {
                n = array.length - length;
            }
            if (n > 0) {
                synchronized (this.m_queueEntries) {
                    if (this.m_closed) {
                        throw new IOException("Stream is closed.");
                    }
                    if (this.m_queueEntries.size() == 0) {
                        try {
                            this.m_queueEntries.wait();
                        }
                        catch (InterruptedException ex) {
                            throw new InterruptedIOException("InterruptedException: " + ex.toString());
                        }
                    }
                    if (this.m_queueEntries.size() > 0) {
                        DataChainInputStreamQueueEntry dataChainInputStreamQueueEntry = this.m_queueEntries.firstElement();
                        switch (dataChainInputStreamQueueEntry.getType()) {
                            case 2: {
                                n2 = -1;
                                break;
                            }
                            case 1: {
                                while (this.m_queueEntries.size() > 0 && dataChainInputStreamQueueEntry.getType() == 1) {
                                    if (n2 >= n) {
                                        break;
                                    }
                                    final int min = Math.min(n - n2, dataChainInputStreamQueueEntry.getData().length - dataChainInputStreamQueueEntry.getAlreadyReadBytes());
                                    System.arraycopy(dataChainInputStreamQueueEntry.getData(), dataChainInputStreamQueueEntry.getAlreadyReadBytes(), array, length + n2, min);
                                    n2 += min;
                                    dataChainInputStreamQueueEntry.setAlreadyReadBytes(dataChainInputStreamQueueEntry.getAlreadyReadBytes() + min);
                                    if (dataChainInputStreamQueueEntry.getAlreadyReadBytes() != dataChainInputStreamQueueEntry.getData().length) {
                                        continue;
                                    }
                                    this.m_queueEntries.removeElementAt(0);
                                    if (this.m_queueEntries.size() <= 0) {
                                        continue;
                                    }
                                    dataChainInputStreamQueueEntry = this.m_queueEntries.firstElement();
                                }
                                break;
                            }
                            case 3: {
                                final IOException ioException = dataChainInputStreamQueueEntry.getIOException();
                                this.m_queueEntries.removeElementAt(0);
                                if (ioException != null) {
                                    throw ioException;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            return n2;
        }
        
        public int available() throws IOException {
            int n = 0;
            synchronized (this.m_queueEntries) {
                if (this.m_closed) {
                    throw new IOException("Stream is closed.");
                }
                if (this.m_queueEntries.size() > 0) {
                    int n2 = 0;
                    DataChainInputStreamQueueEntry dataChainInputStreamQueueEntry = this.m_queueEntries.elementAt(n2);
                    while (dataChainInputStreamQueueEntry != null) {
                        ++n2;
                        if (dataChainInputStreamQueueEntry.getType() == 1) {
                            n += dataChainInputStreamQueueEntry.getData().length - dataChainInputStreamQueueEntry.getAlreadyReadBytes();
                            if (n2 < this.m_queueEntries.size()) {
                                dataChainInputStreamQueueEntry = this.m_queueEntries.elementAt(n2);
                            }
                            else {
                                dataChainInputStreamQueueEntry = null;
                            }
                        }
                        else {
                            dataChainInputStreamQueueEntry = null;
                        }
                    }
                }
            }
            return n;
        }
        
        public void close() {
            if (!this.m_closed) {
                synchronized (this.m_queueEntries) {
                    this.m_closed = true;
                    this.m_queueEntries.removeAllElements();
                    this.m_queueEntries.notifyAll();
                }
            }
        }
        
        public void addToQueue(final DataChainInputStreamQueueEntry dataChainInputStreamQueueEntry) {
            synchronized (this.m_queueEntries) {
                boolean b = true;
                if (this.m_closed) {
                    b = false;
                }
                else if (this.m_queueEntries.size() > 0 && this.m_queueEntries.lastElement().getType() == 2) {
                    b = false;
                }
                if (b) {
                    this.m_queueEntries.addElement(dataChainInputStreamQueueEntry);
                    this.m_queueEntries.notify();
                }
            }
        }
    }
}
