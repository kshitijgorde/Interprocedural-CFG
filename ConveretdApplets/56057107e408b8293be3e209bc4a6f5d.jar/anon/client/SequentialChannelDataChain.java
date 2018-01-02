// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import java.util.Vector;

public class SequentialChannelDataChain extends AbstractDataChain
{
    private static final int CHAIN_ID_LENGTH = 8;
    private static final short FLAG_UNKNOWN_CHAIN_ID = 8192;
    private static final short FLAG_CONNECTION_ERROR = Short.MIN_VALUE;
    private static final short FLAG_NEW_CHAIN = 8192;
    private static final short FLAG_FAST_RESPONSE = Short.MIN_VALUE;
    private static final short FLAG_STREAM_CLOSED = 16384;
    private Vector m_associatedChannels;
    private boolean m_firstDownstreamPacket;
    private volatile byte[] m_chainId;
    private int m_maximumOutputBlocksize;
    private Object m_sendSynchronization;
    private volatile boolean m_chainClosed;
    private long m_chainTimeout;
    
    public SequentialChannelDataChain(final IDataChannelCreator dataChannelCreator, final DataChainErrorListener dataChainErrorListener, final long chainTimeout) {
        super(dataChannelCreator, dataChainErrorListener);
        this.m_associatedChannels = new Vector();
        this.m_sendSynchronization = new Object();
        this.m_firstDownstreamPacket = true;
        this.m_chainClosed = false;
        this.m_chainTimeout = chainTimeout;
        final AbstractDataChannel dataChannel = this.createDataChannel();
        final int nextPacketRecommandedOutputBlocksize = dataChannel.getNextPacketRecommandedOutputBlocksize();
        try {
            dataChannel.organizeChannelClose();
        }
        catch (IOException ex) {}
        this.m_maximumOutputBlocksize = nextPacketRecommandedOutputBlocksize - 2 + 1;
    }
    
    public int getOutputBlockSize() {
        return this.m_maximumOutputBlocksize;
    }
    
    public void createPacketPayload(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        if (dataChainSendOrderStructure.getOrderData() != null) {
            final SendOrderProtocolData sendOrderProtocolData = (SendOrderProtocolData)dataChainSendOrderStructure.getAdditionalProtocolData();
            boolean b = false;
            int processedBytes;
            if (sendOrderProtocolData.getChannelEntry().getProcessedUpstreamPackets() == 0 && this.m_chainId != null) {
                processedBytes = Math.min(dataChainSendOrderStructure.getOrderData().length, dataChainSendOrderStructure.getChannelCell().length - 2 - 8);
                b = true;
                LogHolder.log(7, LogType.NET, "SequentialChannelDataChain: createPacketPayload(): Resuming existent chain.");
            }
            else {
                processedBytes = Math.min(dataChainSendOrderStructure.getOrderData().length, dataChainSendOrderStructure.getChannelCell().length - 2);
            }
            int n = processedBytes;
            if (dataChainSendOrderStructure.getOrderData().length > processedBytes || sendOrderProtocolData.enforceFastResponse()) {
                n |= 0xFFFF8000;
            }
            if (sendOrderProtocolData.sendUpstreamClose()) {
                n |= 0x4000;
                LogHolder.log(7, LogType.NET, "SequentialChannelDataChain: createPacketPayload(): Sending STREAM_CLOSE.");
            }
            if (sendOrderProtocolData.getChannelEntry().getProcessedUpstreamPackets() == 0 && !b) {
                n |= 0x2000;
                LogHolder.log(7, LogType.NET, "SequentialChannelDataChain: createPacketPayload(): Sending NEW_CHAIN.");
            }
            sendOrderProtocolData.getChannelEntry().incProcessedUpstreamPackets();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(n);
                dataOutputStream.flush();
                if (b) {
                    byteArrayOutputStream.write(this.m_chainId);
                }
                byteArrayOutputStream.write(dataChainSendOrderStructure.getOrderData(), 0, processedBytes);
                byteArrayOutputStream.flush();
            }
            catch (IOException ex) {}
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, dataChainSendOrderStructure.getChannelCell(), 0, byteArrayOutputStream.toByteArray().length);
            dataChainSendOrderStructure.setProcessedBytes(processedBytes);
        }
    }
    
    public void run() {
        try {
            DataChainChannelListEntry dataChainChannelListEntry = null;
            while (!Thread.interrupted()) {
                if (dataChainChannelListEntry == null) {
                    synchronized (this.m_associatedChannels) {
                        if (this.m_associatedChannels.size() == 0 && !this.m_firstDownstreamPacket) {
                            final Thread thread = new Thread(new Runnable() {
                                public void run() {
                                    SequentialChannelDataChain.this.orderPacketInternal(new DataChainSendOrderStructure(new byte[0]), false, true);
                                }
                            }, "SequentialChannelDataChain: Datachain keep-alive thread");
                            thread.setDaemon(true);
                            thread.start();
                        }
                        while (this.m_associatedChannels.size() == 0) {
                            this.m_associatedChannels.wait();
                        }
                        dataChainChannelListEntry = this.m_associatedChannels.firstElement();
                    }
                }
                final InternalChannelMessage waitForNextMessage = dataChainChannelListEntry.getChannel().getChannelMessageQueue().waitForNextMessage();
                dataChainChannelListEntry.getChannel().getChannelMessageQueue().removeFirstMessage();
                switch (waitForNextMessage.getMessageCode()) {
                    case 1: {
                        ChainCell chainCell = null;
                        try {
                            chainCell = new ChainCell(waitForNextMessage.getMessageData());
                        }
                        catch (InvalidChainCellException ex) {
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException(ex.toString())));
                            Thread.currentThread().interrupt();
                        }
                        if (chainCell == null) {
                            continue;
                        }
                        if (chainCell.getReceivedChainId() != null) {
                            this.m_chainId = chainCell.getReceivedChainId();
                        }
                        if (chainCell.getPayloadData().length > 0) {
                            LogHolder.log(7, LogType.NET, "SequentialChannelDataChain: run(): Data received.");
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(1, chainCell.getPayloadData()));
                        }
                        if (chainCell.isUnknownChainIdFlagSet()) {
                            LogHolder.log(3, LogType.NET, "SequentialChannelDataChain: run(): Last mix signaled unknown chain ID.");
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException("SequentialChannelDataChain: run(): Last mix signaled unknown chain ID.")));
                        }
                        if (chainCell.isDownstreamClosedFlagSet()) {
                            LogHolder.log(7, LogType.NET, "SequentialChannelDataChain: run(): Received downstream-close flag.");
                            Thread.currentThread().interrupt();
                            continue;
                        }
                        synchronized (dataChainChannelListEntry) {
                            dataChainChannelListEntry.incProcessedDownstreamPackets();
                            dataChainChannelListEntry.notify();
                            continue;
                        }
                    }
                    case 2: {
                        try {
                            if (waitForNextMessage.getMessageData() != null) {
                                final ChainCell chainCell2 = new ChainCell(waitForNextMessage.getMessageData());
                                if (chainCell2.getPayloadData().length == 0 && chainCell2.isConnectionErrorFlagSet()) {
                                    LogHolder.log(3, LogType.NET, "SequentialChannelDataChain: run(): Last mix signaled a connection-error.");
                                    this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException("SequentialChannelDataChain: run(): Last mix signaled a connection-error.")));
                                    this.propagateConnectionError();
                                }
                            }
                        }
                        catch (InvalidChainCellException ex2) {
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException(ex2.toString())));
                        }
                        if (dataChainChannelListEntry.getProcessedDownstreamPackets() == 0) {
                            LogHolder.log(3, LogType.NET, "SequentialChannelDataChain: run(): Last mix sent CHANNEL_CLOSE immediately without data-packets.");
                            Thread.currentThread().interrupt();
                            continue;
                        }
                        synchronized (this.m_associatedChannels) {
                            this.m_associatedChannels.removeElementAt(0);
                        }
                        dataChainChannelListEntry = null;
                        continue;
                    }
                    case 3: {
                        this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException("SingleChannelDataChain: run(): Channel signaled an exception - closing chain.")));
                        synchronized (dataChainChannelListEntry) {
                            dataChainChannelListEntry.notify();
                        }
                        Thread.currentThread().interrupt();
                    }
                    default: {
                        continue;
                    }
                }
            }
        }
        catch (InterruptedException ex3) {}
        this.m_chainClosed = true;
        this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(2, null));
        synchronized (this.m_associatedChannels) {
            while (this.m_associatedChannels.size() > 0) {
                final DataChainChannelListEntry dataChainChannelListEntry2 = this.m_associatedChannels.firstElement();
                synchronized (dataChainChannelListEntry2) {
                    dataChainChannelListEntry2.notify();
                }
                this.m_associatedChannels.removeElementAt(0);
            }
        }
    }
    
    protected void orderPacket(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        this.orderPacketInternal(dataChainSendOrderStructure, false, false);
    }
    
    private void orderPacketInternal(final DataChainSendOrderStructure dataChainSendOrderStructure, final boolean b, final boolean b2) {
        synchronized (this.m_sendSynchronization) {
            if (!this.m_chainClosed) {
                DataChainChannelListEntry dataChainChannelListEntry = null;
                synchronized (this.m_associatedChannels) {
                    if (this.m_associatedChannels.size() > 0) {
                        dataChainChannelListEntry = this.m_associatedChannels.lastElement();
                    }
                }
                boolean processSendOrder = false;
                if (dataChainChannelListEntry != null) {
                    dataChainSendOrderStructure.setAdditionalProtocolData(new SendOrderProtocolData(dataChainChannelListEntry, b, b2));
                    processSendOrder = dataChainChannelListEntry.getChannel().processSendOrder(dataChainSendOrderStructure);
                }
                if (!processSendOrder) {
                    if (dataChainChannelListEntry != null) {
                        synchronized (dataChainChannelListEntry) {
                            if (dataChainChannelListEntry.getProcessedDownstreamPackets() == 0) {
                                try {
                                    dataChainChannelListEntry.wait();
                                }
                                catch (InterruptedException ex) {
                                    dataChainSendOrderStructure.setThrownException(new InterruptedIOException("SequentialChannelDataChain: orderPacketInternal(): Waiting for available channel was interrupted: " + ex.toString()));
                                    dataChainSendOrderStructure.processingDone();
                                    return;
                                }
                                if (dataChainChannelListEntry.getProcessedDownstreamPackets() == 0) {
                                    dataChainSendOrderStructure.setThrownException(new IOException("SequentialChannelDataChain: orderPacketInternal(): Chain already closed."));
                                    dataChainSendOrderStructure.processingDone();
                                }
                            }
                        }
                    }
                    final DataChainChannelListEntry dataChainChannelListEntry2 = new DataChainChannelListEntry(this.createDataChannel());
                    synchronized (this.m_associatedChannels) {
                        this.m_associatedChannels.addElement(dataChainChannelListEntry2);
                        this.m_associatedChannels.notifyAll();
                    }
                    dataChainSendOrderStructure.setAdditionalProtocolData(new SendOrderProtocolData(dataChainChannelListEntry2, b, b2));
                    dataChainChannelListEntry2.getChannel().processSendOrder(dataChainSendOrderStructure);
                }
            }
            else {
                dataChainSendOrderStructure.setThrownException(new IOException("SequentialChannelDataChain: orderPacketInternal(): Chain already closed."));
                dataChainSendOrderStructure.processingDone();
            }
        }
    }
    
    protected void outputStreamClosed() throws IOException {
        this.close();
    }
    
    protected void closeDataChain() {
        synchronized (this.m_sendSynchronization) {
            if (!this.m_chainClosed) {
                this.orderPacketInternal(new DataChainSendOrderStructure(new byte[0]), true, false);
                this.m_chainClosed = true;
                this.interruptDownstreamThread();
            }
        }
    }
    
    private class InvalidChainCellException extends Exception
    {
        public InvalidChainCellException(final String s) {
            super(s);
        }
    }
    
    private class ChainCell
    {
        private static final short DATALENGTH_MASK = 1023;
        private byte[] m_payloadData;
        private byte[] m_receivedChainId;
        private boolean m_unknownChainIdFlagSet;
        private boolean m_connectionErrorFlagSet;
        private boolean m_downstreamClosedFlagSet;
        
        public ChainCell(final byte[] array) throws InvalidChainCellException {
            if (array.length < 2) {
                throw new InvalidChainCellException("SequentialChannelDataChain: ChainCell: Constructor: Length of chaincell must be at least 2 bytes.");
            }
            short short1 = 0;
            try {
                short1 = new DataInputStream(new ByteArrayInputStream(array, 0, 2)).readShort();
            }
            catch (IOException ex) {}
            final short n = (short)(short1 & 0xFFFFFC00);
            if ((n & 0x2000) == 0x2000) {
                this.m_unknownChainIdFlagSet = true;
            }
            else {
                this.m_unknownChainIdFlagSet = false;
            }
            if ((n & 0xFFFF8000) == 0xFFFF8000) {
                this.m_connectionErrorFlagSet = true;
            }
            else {
                this.m_connectionErrorFlagSet = false;
            }
            if ((n & 0x4000) == 0x4000) {
                this.m_downstreamClosedFlagSet = true;
            }
            else {
                this.m_downstreamClosedFlagSet = false;
            }
            int n2 = 2;
            final short n3 = (short)(short1 & 0x3FF);
            if (SequentialChannelDataChain.this.m_firstDownstreamPacket) {
                if (array.length < n2 + 8 + n3) {
                    throw new InvalidChainCellException("SequentialChannelDataChain: ChainCell: Constructor: First downstream chaincell must contain Chain-ID.");
                }
                System.arraycopy(array, n2, this.m_receivedChainId = new byte[8], 0, 8);
                n2 += 8;
                SequentialChannelDataChain.this.m_firstDownstreamPacket = false;
            }
            else {
                if (n2 + n3 > array.length) {
                    throw new InvalidChainCellException("SequentialChannelDataChain: ChainCell: Constructor: Chaincell has invalid length-field.");
                }
                this.m_receivedChainId = null;
            }
            System.arraycopy(array, n2, this.m_payloadData = new byte[n3], 0, n3);
        }
        
        public byte[] getPayloadData() {
            return this.m_payloadData;
        }
        
        public byte[] getReceivedChainId() {
            return this.m_receivedChainId;
        }
        
        public boolean isUnknownChainIdFlagSet() {
            return this.m_unknownChainIdFlagSet;
        }
        
        public boolean isDownstreamClosedFlagSet() {
            return this.m_downstreamClosedFlagSet;
        }
        
        public boolean isConnectionErrorFlagSet() {
            return this.m_connectionErrorFlagSet;
        }
    }
    
    private class SendOrderProtocolData
    {
        private DataChainChannelListEntry m_channelEntry;
        private boolean m_sendUpstreamClose;
        private boolean m_enforceFastResponse;
        
        public SendOrderProtocolData(final DataChainChannelListEntry channelEntry, final boolean sendUpstreamClose, final boolean enforceFastResponse) {
            this.m_channelEntry = channelEntry;
            this.m_sendUpstreamClose = sendUpstreamClose;
            this.m_enforceFastResponse = enforceFastResponse;
        }
        
        public DataChainChannelListEntry getChannelEntry() {
            return this.m_channelEntry;
        }
        
        public boolean sendUpstreamClose() {
            return this.m_sendUpstreamClose;
        }
        
        public boolean enforceFastResponse() {
            return this.m_enforceFastResponse;
        }
    }
}
