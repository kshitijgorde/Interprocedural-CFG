// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import logging.LogHolder;
import logging.LogType;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Observer;

public class SingleChannelDataChain extends AbstractDataChain
{
    private static final short FLAG_FLOW_CONTROL = Short.MIN_VALUE;
    private static final int CLOSE_CELL_CONNECTION_ERROR = 1;
    private int m_chainType;
    private boolean m_supportFlowControl;
    private AbstractDataChannel m_associatedChannel;
    private boolean m_firstUpstreamPacket;
    private int m_downstreamSendMeCount;
    private int m_downstreamSendMeLimit;
    private boolean m_bEnhancedChannelEncryption;
    
    public SingleChannelDataChain(final IDataChannelCreator dataChannelCreator, final DataChainErrorListener dataChainErrorListener, final int chainType, final boolean supportFlowControl, final int n, final int downstreamSendMeLimit, final boolean bEnhancedChannelEncryption) {
        super(dataChannelCreator, dataChainErrorListener);
        this.m_chainType = chainType;
        this.m_supportFlowControl = supportFlowControl;
        this.m_bEnhancedChannelEncryption = bEnhancedChannelEncryption;
        this.m_associatedChannel = this.createDataChannel();
        this.m_associatedChannel.getChannelMessageQueue().addObserver(this);
        this.m_firstUpstreamPacket = true;
        this.m_downstreamSendMeCount = 0;
        this.m_downstreamSendMeLimit = downstreamSendMeLimit;
    }
    
    public int getOutputBlockSize() {
        int nextPacketRecommandedOutputBlocksize = 0;
        synchronized (this.m_associatedChannel) {
            nextPacketRecommandedOutputBlocksize = this.m_associatedChannel.getNextPacketRecommandedOutputBlocksize();
        }
        return Math.max(0, nextPacketRecommandedOutputBlocksize - 3);
    }
    
    public void createPacketPayload(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        if (dataChainSendOrderStructure.getOrderData() != null) {
            int min;
            final int processedBytes = min = Math.min(dataChainSendOrderStructure.getOrderData().length, dataChainSendOrderStructure.getChannelCell().length - 3);
            if (this.m_supportFlowControl && dataChainSendOrderStructure.getAdditionalProtocolData() instanceof Boolean && (boolean)dataChainSendOrderStructure.getAdditionalProtocolData()) {
                min |= 0xFFFF8000;
            }
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(min);
                dataOutputStream.flush();
                if (this.m_firstUpstreamPacket) {
                    byteArrayOutputStream.write(this.m_chainType);
                    this.m_firstUpstreamPacket = false;
                }
                else {
                    byteArrayOutputStream.write(0);
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
        final Vector messageQueuesNotificationsList = this.getMessageQueuesNotificationsList();
        try {
            while (!Thread.interrupted()) {
                InternalChannelMessage firstMessage = null;
                InternalChannelMessageQueue internalChannelMessageQueue = null;
                synchronized (messageQueuesNotificationsList) {
                    while (messageQueuesNotificationsList.size() == 0) {
                        messageQueuesNotificationsList.wait();
                    }
                    internalChannelMessageQueue = messageQueuesNotificationsList.firstElement();
                    firstMessage = internalChannelMessageQueue.getFirstMessage();
                    internalChannelMessageQueue.removeFirstMessage();
                    messageQueuesNotificationsList.removeElementAt(0);
                }
                switch (firstMessage.getMessageCode()) {
                    default: {
                        continue;
                    }
                    case 1: {
                        try {
                            ++this.m_downstreamSendMeCount;
                            final ChainCell chainCell = new ChainCell(firstMessage.getMessageData());
                            if (this.m_supportFlowControl && this.m_downstreamSendMeCount >= this.m_downstreamSendMeLimit) {
                                LogHolder.log(7, LogType.NET, "got sendme - and packet counter is: " + this.m_downstreamSendMeCount);
                                final DataChainSendOrderStructure dataChainSendOrderStructure = new DataChainSendOrderStructure(new byte[0]);
                                dataChainSendOrderStructure.setAdditionalProtocolData(new Boolean(true));
                                this.orderPacket(dataChainSendOrderStructure);
                                this.m_downstreamSendMeCount = 0;
                            }
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(1, chainCell.getPayloadData()));
                        }
                        catch (InvalidChainCellException ex) {
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException(ex.toString())));
                        }
                        continue;
                    }
                    case 2: {
                        this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(2, null));
                        try {
                            if (firstMessage.getMessageData() != null) {
                                final ChainCell chainCell2 = new ChainCell(firstMessage.getMessageData());
                                if (chainCell2.getPayloadLength() == 0 && chainCell2.getPayloadType() == 1) {
                                    this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException("SingleChannelDataChain: run(): Last mix signaled connection error.")));
                                    this.propagateConnectionError();
                                }
                            }
                        }
                        catch (InvalidChainCellException ex2) {
                            this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException(ex2.toString())));
                        }
                        internalChannelMessageQueue.deleteObserver(this);
                        Thread.currentThread().interrupt();
                        continue;
                    }
                    case 3: {
                        this.addInputStreamQueueEntry(new DataChainInputStreamQueueEntry(new IOException("SingleChannelDataChain: run(): Channel signaled an exception - closing chain.")));
                        continue;
                    }
                }
            }
        }
        catch (InterruptedException ex3) {}
    }
    
    protected void orderPacket(final DataChainSendOrderStructure dataChainSendOrderStructure) {
        synchronized (this.m_associatedChannel) {
            this.m_associatedChannel.processSendOrder(dataChainSendOrderStructure);
        }
    }
    
    protected void outputStreamClosed() throws IOException {
        this.close();
    }
    
    protected void closeDataChain() {
        synchronized (this.m_associatedChannel) {
            try {
                this.m_associatedChannel.organizeChannelClose();
            }
            catch (IOException ex) {}
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
        private int m_payloadLen;
        private int m_payloadType;
        private boolean m_flowControlFlagSet;
        
        public ChainCell(final byte[] array) throws InvalidChainCellException {
            if (array.length < 3) {
                throw new InvalidChainCellException("SingleChannelDataChain: ChainCell: Constructor: Length of ChainCell must be at least 3 bytes.");
            }
            short short1 = 0;
            try {
                final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(array, 0, 3));
                short1 = dataInputStream.readShort();
                this.m_payloadType = dataInputStream.readByte();
            }
            catch (IOException ex) {}
            this.m_flowControlFlagSet = false;
            final short n = (short)(short1 & 0xFFFFFC00);
            if (SingleChannelDataChain.this.m_supportFlowControl && (n & 0xFFFF8000) == 0xFFFF8000) {
                this.m_flowControlFlagSet = true;
            }
            this.m_payloadLen = (short1 & 0x3FF);
            final int n2 = 3;
            if (n2 + this.m_payloadLen > array.length) {
                throw new InvalidChainCellException("SingleChannelDataChain: ChainCell: Constructor: ChainCell has invalid length-field.");
            }
            System.arraycopy(array, n2, this.m_payloadData = new byte[this.m_payloadLen], 0, this.m_payloadLen);
        }
        
        public byte[] getPayloadData() {
            return this.m_payloadData;
        }
        
        public int getPayloadType() {
            return this.m_payloadType;
        }
        
        public int getPayloadLength() {
            return this.m_payloadLen;
        }
        
        public boolean isFlowControlFlagSet() {
            return this.m_flowControlFlagSet;
        }
    }
}
