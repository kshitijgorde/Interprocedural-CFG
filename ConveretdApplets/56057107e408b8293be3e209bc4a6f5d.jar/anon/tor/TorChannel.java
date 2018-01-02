// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import logging.LogHolder;
import logging.LogType;
import anon.tor.cells.Cell;
import anon.tor.cells.RelayCell;
import anon.util.ByteArrayUtil;
import java.io.IOException;
import anon.shared.AbstractChannel;

public class TorChannel extends AbstractChannel
{
    private static final int MAX_CELL_DATA = 498;
    protected Circuit m_circuit;
    private volatile int m_recvcellcounter;
    private volatile int m_sendcellcounter;
    private volatile int m_iSendRelayCellsWaitingForDelivery;
    private volatile boolean m_bChannelCreated;
    private volatile boolean m_bCreateError;
    private Object m_oWaitForOpen;
    private Object m_oSyncSendCellCounter;
    private Object m_oSyncSend;
    private Object m_oSyncSendRelayCellsWaitingForDelivery;
    private volatile boolean m_bDoNotCloseChannelOnError;
    
    public TorChannel() {
        this.m_oWaitForOpen = new Object();
        this.m_oSyncSendCellCounter = new Object();
        this.m_oSyncSend = new Object();
        this.m_oSyncSendRelayCellsWaitingForDelivery = new Object();
        this.m_bDoNotCloseChannelOnError = false;
        this.m_iSendRelayCellsWaitingForDelivery = 0;
    }
    
    private void addToSendCellCounter(final int n) {
        synchronized (this.m_oSyncSendCellCounter) {
            this.m_sendcellcounter += n;
        }
    }
    
    protected void decreaseSendRelayCellsWaitingForDelivery() {
        synchronized (this.m_oSyncSendRelayCellsWaitingForDelivery) {
            --this.m_iSendRelayCellsWaitingForDelivery;
        }
    }
    
    protected void setStreamID(final int id) {
        super.m_id = id;
    }
    
    protected void setCircuit(final Circuit circuit) {
        this.m_circuit = circuit;
    }
    
    public int getOutputBlockSize() {
        return 498;
    }
    
    protected void send(final byte[] array, int n) throws IOException {
        if (super.m_bIsClosed || super.m_bIsClosedByPeer) {
            throw new IOException("Tor channel is closed");
        }
        synchronized (this.m_oSyncSend) {
            byte[] copy = array;
            while (n != 0 && !super.m_bIsClosed) {
                RelayCell relayCell;
                if (n > 498) {
                    relayCell = new RelayCell(this.m_circuit.getCircID(), (byte)2, super.m_id, ByteArrayUtil.copy(copy, 0, 498));
                    copy = ByteArrayUtil.copy(copy, 498, n - 498);
                    n -= 498;
                }
                else {
                    relayCell = new RelayCell(this.m_circuit.getCircID(), (byte)2, super.m_id, ByteArrayUtil.copy(copy, 0, n));
                    n = 0;
                }
                try {
                    while ((this.m_sendcellcounter <= 0 || this.m_iSendRelayCellsWaitingForDelivery > 10) && !super.m_bIsClosed && !super.m_bIsClosedByPeer) {
                        try {
                            Thread.sleep(100L);
                        }
                        catch (Exception ex) {}
                    }
                    synchronized (this.m_oSyncSendRelayCellsWaitingForDelivery) {
                        ++this.m_iSendRelayCellsWaitingForDelivery;
                    }
                    this.m_circuit.send(relayCell);
                }
                catch (Throwable t) {
                    throw new IOException("TorChannel send - error in sending a cell!");
                }
                this.addToSendCellCounter(-1);
            }
        }
    }
    
    private void internalClose() {
        this.m_bCreateError = true;
        if (!this.m_bDoNotCloseChannelOnError) {
            this.close();
        }
        else {
            final RelayCell relayCell = new RelayCell(this.m_circuit.getCircID(), (byte)3, super.m_id, new byte[] { 6 });
            try {
                this.m_circuit.sendUrgent(relayCell);
            }
            catch (Exception ex) {}
        }
    }
    
    public void close() {
        this.m_bCreateError = true;
        super.close();
        synchronized (this.m_oWaitForOpen) {
            this.m_oWaitForOpen.notify();
        }
    }
    
    public boolean isClosed() {
        return this.m_bCreateError;
    }
    
    public void closedByPeer() {
        this.m_bCreateError = true;
        if (!this.m_bDoNotCloseChannelOnError) {
            super.closedByPeer();
        }
        synchronized (this.m_oWaitForOpen) {
            this.m_oWaitForOpen.notify();
        }
    }
    
    protected void close_impl() {
        try {
            if (!super.m_bIsClosed) {
                this.m_circuit.close(super.m_id);
            }
        }
        catch (Exception ex) {}
    }
    
    protected void setDoNotCloseChannelOnErrorDuringConnect(final boolean bDoNotCloseChannelOnError) {
        this.m_bDoNotCloseChannelOnError = bDoNotCloseChannelOnError;
    }
    
    protected boolean connect(final String s, final int n) {
        try {
            if (super.m_bIsClosed || super.m_bIsClosedByPeer) {
                return false;
            }
            this.m_recvcellcounter = 500;
            this.m_sendcellcounter = 500;
            final RelayCell relayCell = new RelayCell(this.m_circuit.getCircID(), (byte)1, super.m_id, ByteArrayUtil.conc((s + ":" + Integer.toString(n)).getBytes(), new byte[1]));
            this.m_bChannelCreated = false;
            this.m_bCreateError = false;
            this.m_circuit.sendUrgent(relayCell);
            synchronized (this.m_oWaitForOpen) {
                final long currentTimeMillis = System.currentTimeMillis();
                long n2;
                for (int i = 60000; i > 0; i -= (int)n2) {
                    try {
                        this.m_oWaitForOpen.wait(i);
                    }
                    catch (InterruptedException ex) {
                        LogHolder.log(7, LogType.TOR, "InterruptedException in TorChannel:connect()");
                    }
                    if (this.m_bCreateError) {
                        LogHolder.log(7, LogType.TOR, "TorChannel - connect() - establishing channel over circuit NOT successful. Channel was closed before!");
                        return false;
                    }
                    if (this.m_bChannelCreated) {
                        this.m_bDoNotCloseChannelOnError = false;
                        LogHolder.log(7, LogType.TOR, "TorChannel - connect() - establishing channel over circuit successful. Time needed [ms]: " + Long.toString(System.currentTimeMillis() - currentTimeMillis));
                        return true;
                    }
                    n2 = System.currentTimeMillis() - currentTimeMillis;
                    if (n2 < 0L) {
                        return false;
                    }
                }
            }
            LogHolder.log(7, LogType.TOR, "TorChannel - connect() - establishing channel over circuit NOT successful. Timed out!");
            this.internalClose();
            return false;
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.TOR, "Exception in TorChannel:connect()");
            this.internalClose();
            return false;
        }
    }
    
    public int dispatchCell(final RelayCell relayCell) {
        int n = 0;
        switch (relayCell.getRelayCommand()) {
            case 4: {
                this.m_bChannelCreated = true;
                this.m_bDoNotCloseChannelOnError = false;
                synchronized (this.m_oWaitForOpen) {
                    this.m_oWaitForOpen.notify();
                    break;
                }
            }
            case 5: {
                this.addToSendCellCounter(50);
                break;
            }
            case 2: {
                --this.m_recvcellcounter;
                if (this.m_recvcellcounter < 250) {
                    final RelayCell relayCell2 = new RelayCell(this.m_circuit.getCircID(), (byte)5, super.m_id, null);
                    try {
                        this.m_circuit.sendUrgent(relayCell2);
                    }
                    catch (Throwable t) {
                        this.closedByPeer();
                        return n;
                    }
                    this.m_recvcellcounter += 50;
                }
                try {
                    final byte[] relayPayload = relayCell.getRelayPayload();
                    this.recv(relayPayload, 0, relayPayload.length);
                    break;
                }
                catch (Exception ex) {
                    this.closedByPeer();
                    return n;
                }
            }
            case 3: {
                final byte b = relayCell.getPayload()[0];
                LogHolder.log(7, LogType.TOR, "RELAY_END: Relay stream closed with reason: " + b);
                if (b == 1) {
                    n = -1;
                }
                this.closedByPeer();
                break;
            }
            default: {
                this.closedByPeer();
                break;
            }
        }
        return n;
    }
    
    public boolean isClosedByPeer() {
        return super.m_bIsClosedByPeer;
    }
}
