// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import anon.tor.cells.PaddingCell;
import anon.tor.cells.CreatedCell;
import anon.util.ByteArrayUtil;
import anon.tor.cells.RelayCell;
import java.util.Enumeration;
import anon.tor.cells.DestroyCell;
import anon.tor.cells.Cell;
import logging.LogHolder;
import logging.LogType;
import java.util.Random;
import java.security.SecureRandom;
import java.io.IOException;
import anon.crypto.MyRandom;
import java.util.Hashtable;
import java.util.Vector;
import anon.tor.ordescription.ORDescriptor;

public final class Circuit implements Runnable
{
    public static final int MAX_STREAMS_OVER_CIRCUIT = 1000;
    private OnionRouter m_FirstOR;
    private ORDescriptor m_lastORDescription;
    private FirstOnionRouterConnection m_FirstORConnection;
    private Vector m_onionRouters;
    private int m_circID;
    private Hashtable m_streams;
    private volatile int m_State;
    private volatile int m_iRelayErrors;
    private static final int STATE_CLOSED = 0;
    private static final int STATE_SHUTDOWN = 1;
    private static final int STATE_READY = 2;
    private static final int STATE_CREATING = 3;
    private int m_streamCounter;
    private int m_circuitLength;
    private int m_MaxStreamsPerCircuit;
    private volatile int m_recvCellCounter;
    private volatile int m_sendCellCounter;
    private boolean m_destroyed;
    private byte[] m_resolvedData;
    private Object m_oResolveSync;
    private Object m_oSendCellCounterSync;
    private Object m_oSendSync;
    private Object m_oDestroyedByPeerSync;
    private volatile boolean m_bReceivedCreatedOrExtendedCell;
    private Object m_oNotifySync;
    private MyRandom m_rand;
    private Thread m_threadSendCellLoop;
    private CellQueue m_cellqueueSend;
    
    public Circuit(final int circID, final FirstOnionRouterConnection firstORConnection, final Vector vector) throws IOException {
        this.m_oResolveSync = new Object();
        this.m_oSendCellCounterSync = new Object();
        this.m_oSendSync = new Object();
        this.m_oDestroyedByPeerSync = new Object();
        this.m_oNotifySync = new Object();
        this.m_FirstORConnection = firstORConnection;
        this.m_circID = circID;
        this.m_streams = new Hashtable();
        this.m_streamCounter = 0;
        this.m_MaxStreamsPerCircuit = 1000;
        this.m_onionRouters = (Vector)vector.clone();
        this.m_circuitLength = vector.size();
        this.m_lastORDescription = this.m_onionRouters.elementAt(this.m_circuitLength - 1);
        if (this.m_onionRouters.size() < 1) {
            throw new IOException("No Onionrouters defined for this circuit");
        }
        this.m_recvCellCounter = 1000;
        this.m_sendCellCounter = 1000;
        this.m_rand = new MyRandom(new SecureRandom());
        this.m_State = 3;
        this.m_destroyed = false;
        this.m_iRelayErrors = 0;
        this.m_cellqueueSend = new CellQueue();
        (this.m_threadSendCellLoop = new Thread(this, "Tor - Circuit - SendCellLoop")).setDaemon(true);
        this.m_threadSendCellLoop.start();
    }
    
    private void addToSendCellCounter(final int n) {
        synchronized (this.m_oSendCellCounterSync) {
            this.m_sendCellCounter += n;
        }
    }
    
    protected void create() throws IOException {
        LogHolder.log(7, LogType.TOR, "[TOR] Creating Circuit '" + this.m_circID + "'");
        this.m_FirstOR = new OnionRouter(this.m_circID, this.m_onionRouters.elementAt(0));
        try {
            synchronized (this.m_oNotifySync) {
                this.m_bReceivedCreatedOrExtendedCell = false;
                this.m_FirstORConnection.send(this.m_FirstOR.createConnection());
                this.m_oNotifySync.wait(15000L);
            }
            if (this.m_State != 3 || !this.m_bReceivedCreatedOrExtendedCell) {
                throw new IOException("Error during Circuit creation");
            }
            LogHolder.log(7, LogType.TOR, "[TOR] created!");
            for (int i = 1; i < this.m_onionRouters.size(); ++i) {
                final ORDescriptor orDescriptor = this.m_onionRouters.elementAt(i);
                LogHolder.log(7, LogType.TOR, "[TOR] trying to extend!");
                synchronized (this.m_oNotifySync) {
                    this.m_bReceivedCreatedOrExtendedCell = false;
                    this.m_FirstORConnection.send(this.m_FirstOR.extendConnection(orDescriptor));
                    this.m_oNotifySync.wait(25000L);
                }
                if (this.m_State != 3 || !this.m_bReceivedCreatedOrExtendedCell) {
                    throw new IOException("Error during Circuit creation");
                }
                LogHolder.log(7, LogType.TOR, "[TOR] extended!");
            }
            this.m_State = 2;
            LogHolder.log(7, LogType.MISC, "[TOR] Circuit '" + this.m_circID + "' ready!!! - Length of this Circuit : " + this.m_circuitLength + " Onionrouters");
        }
        catch (Exception ex) {
            try {
                if (!this.m_destroyed) {
                    this.send(new DestroyCell(this.m_circID));
                }
            }
            catch (Throwable t) {}
            this.m_State = 0;
            throw new IOException(ex.getLocalizedMessage());
        }
    }
    
    public synchronized void shutdown() {
        if (this.m_State == 0 || this.m_State == 1) {
            return;
        }
        if (this.m_streams.isEmpty()) {
            this.close();
        }
        this.m_State = 1;
    }
    
    public synchronized void close() {
        if (this.m_State == 0) {
            return;
        }
        try {
            final Enumeration<TorChannel> elements = this.m_streams.elements();
            while (elements.hasMoreElements()) {
                try {
                    elements.nextElement().close();
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
        this.m_streams.clear();
        try {
            this.m_FirstORConnection.send(new DestroyCell(this.m_circID));
            LogHolder.log(7, LogType.TOR, "[TOR] circuit " + this.m_circID + " destroyed!");
        }
        catch (Exception ex3) {}
        this.m_State = 0;
        try {
            this.m_threadSendCellLoop.join(2000L);
        }
        catch (InterruptedException ex4) {}
        this.m_FirstORConnection.notifyCircuitClosed(this);
    }
    
    public void destroyedByPeer() {
        synchronized (this.m_oDestroyedByPeerSync) {
            try {
                final Enumeration<TorChannel> elements = this.m_streams.elements();
                while (elements.hasMoreElements()) {
                    try {
                        elements.nextElement().closedByPeer();
                    }
                    catch (Exception ex) {}
                }
                this.m_streams.clear();
                this.m_FirstORConnection.notifyCircuitClosed(this);
            }
            catch (Exception ex2) {}
            this.m_State = 0;
        }
        synchronized (this.m_oNotifySync) {
            this.m_oNotifySync.notify();
        }
    }
    
    public boolean isClosed() {
        return this.m_State == 0;
    }
    
    public boolean isShutdown() {
        return this.m_State == 1 || this.m_State == 0;
    }
    
    public void dispatchCell(final Cell cell) throws IOException {
        try {
            if (cell instanceof RelayCell) {
                if (this.m_State == 3) {
                    if (!this.m_FirstOR.checkExtendedCell((RelayCell)cell)) {
                        this.send(new DestroyCell(this.m_circID));
                        this.m_State = 0;
                        this.destroyedByPeer();
                        this.m_destroyed = true;
                        return;
                    }
                    synchronized (this.m_oNotifySync) {
                        this.m_bReceivedCreatedOrExtendedCell = true;
                        this.m_oNotifySync.notify();
                        return;
                    }
                }
                --this.m_recvCellCounter;
                if (this.m_recvCellCounter < 900) {
                    this.send(new RelayCell(this.m_circID, (byte)5, 0, null));
                    this.m_recvCellCounter += 100;
                }
                final RelayCell decryptCell = this.m_FirstOR.decryptCell((RelayCell)cell);
                final Integer streamID = decryptCell.getStreamID();
                if (decryptCell.getStreamID() == 0) {
                    switch (decryptCell.getRelayCommand()) {
                        case 5: {
                            this.addToSendCellCounter(100);
                            break;
                        }
                        default: {
                            LogHolder.log(7, LogType.TOR, "Upps...");
                            break;
                        }
                    }
                }
                else if (this.m_streams.containsKey(streamID)) {
                    if (decryptCell.getRelayCommand() == 12) {
                        final byte[] payload = decryptCell.getPayload();
                        this.m_resolvedData = ByteArrayUtil.copy(payload, 11, ((payload[9] & 0xFF) << 8) + (payload[10] & 0xFF));
                        synchronized (this.m_oNotifySync) {
                            this.m_oNotifySync.notify();
                            return;
                        }
                    }
                    final TorChannel torChannel = this.m_streams.get(streamID);
                    if (torChannel != null) {
                        if (torChannel.dispatchCell(decryptCell) != 0) {
                            ++this.m_iRelayErrors;
                            if (this.m_iRelayErrors > 10) {
                                this.shutdown();
                            }
                        }
                    }
                    else {
                        LogHolder.log(7, LogType.TOR, "Upps...");
                    }
                }
                else {
                    LogHolder.log(7, LogType.TOR, "Upps...Unknown stream");
                }
            }
            else {
                if (cell instanceof CreatedCell) {
                    if (!this.m_FirstOR.checkCreatedCell(cell)) {
                        LogHolder.log(7, LogType.TOR, "[TOR] Should never be here - 'created' cell was wrong");
                        this.m_State = 0;
                        this.destroyedByPeer();
                        return;
                    }
                    LogHolder.log(7, LogType.TOR, "[TOR] Connected to the first OR");
                    synchronized (this.m_oNotifySync) {
                        this.m_bReceivedCreatedOrExtendedCell = true;
                        this.m_oNotifySync.notify();
                        return;
                    }
                }
                if (!(cell instanceof PaddingCell)) {
                    if (cell instanceof DestroyCell) {
                        LogHolder.log(7, LogType.TOR, "[TOR] recieved destroycell - circuit destroyed - reason: " + Integer.toString(cell.getPayload()[0]));
                        this.m_destroyed = true;
                        this.destroyedByPeer();
                    }
                    else {
                        LogHolder.log(7, LogType.MISC, "tor kein bekannter cell type");
                    }
                }
            }
        }
        catch (Exception ex) {
            this.destroyedByPeer();
            throw new IOException("Unable to dispatch the cell \n" + ex.getLocalizedMessage());
        }
    }
    
    public void send(final Cell cell) throws IOException, Exception {
        if (this.m_State == 0) {
            throw new IOException("circuit alread closed");
        }
        this.m_cellqueueSend.addElement(cell);
    }
    
    public void sendUrgent(Cell encryptCell) throws IOException, Exception {
        if (this.m_State == 0) {
            throw new IOException("circuit alread closed");
        }
        synchronized (this.m_oSendSync) {
            if (encryptCell instanceof RelayCell) {
                encryptCell = this.m_FirstOR.encryptCell((RelayCell)encryptCell);
                this.addToSendCellCounter(-1);
            }
            this.m_FirstORConnection.send(encryptCell);
        }
    }
    
    public String resolveDNS(final String s) {
        if (this.m_State != 2) {
            return null;
        }
        synchronized (this.m_oResolveSync) {
            Integer n;
            synchronized (this.m_streams) {
                do {
                    n = new Integer(this.m_rand.nextInt(65535));
                } while (this.m_streams.containsKey(n));
                this.m_streams.put(n, n);
            }
            final RelayCell relayCell = new RelayCell(this.getCircID(), (byte)11, n, s.getBytes());
            synchronized (this.m_oNotifySync) {
                try {
                    this.m_resolvedData = null;
                    this.send(relayCell);
                    this.m_oNotifySync.wait(20000L);
                }
                catch (Exception ex) {
                    this.m_streams.remove(n);
                    return null;
                }
            }
            this.m_streams.remove(n);
            if (this.m_State == 0 || this.m_resolvedData == null || this.m_resolvedData[0] != 4 || this.m_resolvedData[1] != 4) {
                return null;
            }
            final StringBuffer sb = new StringBuffer();
            sb.append(Integer.toString(this.m_resolvedData[2] & 0xFF));
            sb.append('.');
            sb.append(Integer.toString(this.m_resolvedData[3] & 0xFF));
            sb.append('.');
            sb.append(Integer.toString(this.m_resolvedData[4] & 0xFF));
            sb.append('.');
            sb.append(Integer.toString(this.m_resolvedData[5] & 0xFF));
            return sb.toString();
        }
    }
    
    protected void close(final int n) throws Exception {
        if (this.m_State == 0) {
            return;
        }
        final byte[] array = { 6 };
        final Integer n2 = new Integer(n);
        if (this.m_streams.containsKey(n2)) {
            this.m_streams.remove(n2);
            this.send(new RelayCell(this.m_circID, (byte)3, n, array));
            if (this.m_State == 1 && this.m_streams.isEmpty()) {
                this.close();
            }
        }
    }
    
    public int getCircID() {
        return this.m_circID;
    }
    
    public synchronized TorChannel createChannel(final String s, final int n) throws IOException {
        final TorChannel torChannel = new TorChannel();
        final int connectChannel = this.connectChannel(torChannel, s, n);
        if (connectChannel != 0) {
            throw new IOException("Circuit:createChannel(addr,port) failed! Reason:" + Integer.toString(connectChannel));
        }
        return torChannel;
    }
    
    protected int connectChannel(final TorChannel torChannel, final String s, final int n) {
        try {
            if (this.isShutdown()) {
                LogHolder.log(7, LogType.TOR, "Circuit:connectChannel() - Circuit Closed - cannot connect");
                return -9;
            }
            int n2 = 0;
            Integer n3;
            synchronized (this) {
                ++this.m_streamCounter;
                synchronized (this.m_streams) {
                    do {
                        n3 = new Integer(this.m_rand.nextInt(65535));
                    } while (this.m_streams.contains(n3));
                    torChannel.setStreamID(n3);
                    torChannel.setCircuit(this);
                    this.m_streams.put(n3, torChannel);
                }
            }
            if (!torChannel.connect(s, n)) {
                synchronized (this) {
                    this.m_streams.remove(n3);
                }
                LogHolder.log(7, LogType.TOR, "Circuit:connectChannel() - Channel could not be created");
                n2 = -6;
            }
            if (this.m_streamCounter >= this.m_MaxStreamsPerCircuit) {
                this.shutdown();
            }
            return n2;
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.TOR, "Circuit:connectChannel() - Unkown Error", t);
            return -1;
        }
    }
    
    public boolean isAllowed(final String s, final int n) {
        return this.m_lastORDescription.getAcl().isAllowed(s, n);
    }
    
    public void setMaxNrOfStreams(final int maxStreamsPerCircuit) {
        if (maxStreamsPerCircuit > 0 && maxStreamsPerCircuit <= 1000) {
            this.m_MaxStreamsPerCircuit = maxStreamsPerCircuit;
        }
        if (this.m_streamCounter >= 1000) {
            this.shutdown();
        }
    }
    
    public void run() {
        try {
            while (this.m_State != 0) {
                while (this.m_cellqueueSend.isEmpty()) {
                    try {
                        if (this.m_State == 0) {
                            return;
                        }
                        Thread.sleep(100L);
                    }
                    catch (Exception ex) {}
                }
                Cell cell = this.m_cellqueueSend.removeElement();
                while (this.m_sendCellCounter <= 0 && this.m_State != 0) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception ex2) {}
                }
                synchronized (this.m_oSendSync) {
                    if (!(cell instanceof RelayCell)) {
                        LogHolder.log(7, LogType.TOR, "Tor-Circuit-sendCellLoop: sending no releay cell.");
                    }
                    else {
                        final TorChannel torChannel = this.m_streams.get(((RelayCell)cell).getStreamID());
                        cell = this.m_FirstOR.encryptCell((RelayCell)cell);
                        this.addToSendCellCounter(-1);
                        if (torChannel != null) {
                            torChannel.decreaseSendRelayCellsWaitingForDelivery();
                        }
                    }
                    this.m_FirstORConnection.send(cell);
                }
            }
        }
        catch (Throwable t) {
            this.destroyedByPeer();
        }
    }
}
