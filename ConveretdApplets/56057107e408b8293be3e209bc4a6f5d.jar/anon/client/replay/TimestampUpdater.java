// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.replay;

import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import java.util.Observable;
import anon.client.MixParameters;
import java.util.Observer;

public class TimestampUpdater implements Observer
{
    private MixParameters[] m_mixParameters;
    private boolean m_responseReceived;
    private Object m_internalSynchronization;
    
    public TimestampUpdater(final MixParameters[] mixParameters, final ReplayControlChannel replayControlChannel) throws Exception {
        this.m_mixParameters = mixParameters;
        this.m_responseReceived = false;
        synchronized (this.m_internalSynchronization = new Object()) {
            replayControlChannel.getMessageDistributor().addObserver(this);
            replayControlChannel.requestTimestamps();
            while (!this.m_responseReceived) {
                this.m_internalSynchronization.wait();
            }
        }
        for (int i = 0; i < this.m_mixParameters.length; ++i) {
            if (this.m_mixParameters[i].getReplayTimestamp() == null) {
                throw new Exception("TimestampUpdater: Constructor: Timestamp of Mix '" + this.m_mixParameters[i].getMixId() + "' is missing.");
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof Vector) {
            LogHolder.log(7, LogType.NET, "TimestampUpdater: update(): Received some timestamps.");
            final Enumeration<ReplayTimestamp> elements = ((Vector)o).elements();
            final Vector vector = new Vector<Integer>();
            while (elements.hasMoreElements()) {
                final ReplayTimestamp replayTimestamp = elements.nextElement();
                int n;
                boolean b;
                for (n = 0, b = false; n < this.m_mixParameters.length && !b; ++n) {
                    if (this.m_mixParameters[n].getMixId().equals(replayTimestamp.getMixId())) {
                        this.m_mixParameters[n].setReplayTimestamp(replayTimestamp);
                        b = true;
                        if (vector.contains(new Integer(n))) {
                            LogHolder.log(6, LogType.NET, "TimestampUpdater: update(): Received timestamp for Mix '" + replayTimestamp.getMixId() + "' twice.");
                        }
                        else {
                            vector.addElement(new Integer(n));
                        }
                    }
                }
                if (!b) {
                    LogHolder.log(6, LogType.NET, "TimestampUpdater: update(): Received timestamp of Mix '" + replayTimestamp.getMixId() + "' is not necessary for the current cascade.");
                }
            }
            for (int i = 0; i < this.m_mixParameters.length; ++i) {
                if (!vector.contains(new Integer(i))) {
                    LogHolder.log(3, LogType.NET, "TimestampUpdater: update(): Timestamp of Mix '" + this.m_mixParameters[i].getMixId() + "' is missing.");
                }
            }
            synchronized (this.m_internalSynchronization) {
                this.m_responseReceived = true;
                this.m_internalSynchronization.notifyAll();
                return;
            }
        }
        if (o instanceof Exception) {
            LogHolder.log(3, LogType.NET, "TimestampUpdater: update(): Received exception: " + o.toString());
            synchronized (this.m_internalSynchronization) {
                this.m_responseReceived = true;
                this.m_internalSynchronization.notifyAll();
            }
        }
    }
}
