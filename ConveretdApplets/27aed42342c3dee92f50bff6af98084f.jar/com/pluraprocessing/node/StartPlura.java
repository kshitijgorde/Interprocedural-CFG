// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import com.pluraprocessing.node.customer.IPluraConnector;
import com.pluraprocessing.node.customer.PluraConnectorFactory;
import com.pluraprocessing.common.domain.RequestedWork;
import java.util.ArrayList;

public class StartPlura implements IStartPlura
{
    private ArrayList<PluraThread> pluraThreads;
    
    @Override
    public void run(final RequestedWork work) throws IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final String oldVersion = PluraRuntime.getInstance().getOldVersionId();
        if (!PluraRuntime.getInstance().usesLimitedAPI()) {
            IPluraConnector plura = PluraConnectorFactory.getPluraConnector();
            PluraRuntime.getInstance().createPluraCustomerObject(plura.getNumberOfPiecesOfDataInMemory(), plura.isBandwidthApplication());
            plura = null;
        }
        else {
            PluraRuntime.getInstance().createRestrictedPluraCustomerObject();
        }
        if (oldVersion == null || !oldVersion.equals(PluraRuntime.getInstance().getCurrentVersionId()) || this.pluraThreads == null) {
            this.stopComputeInAllPluraThreads();
            this.pluraThreads = new ArrayList<PluraThread>();
            for (int i = 0; i < PluraRuntime.getInstance().getNumberThreadsToStart(); ++i) {
                final PluraThread thread = new PluraThread(new Plura(PluraRuntime.getInstance().getApplet()), work);
                this.pluraThreads.add(thread);
                thread.startPluraThread();
            }
        }
        else {
            for (int numberNewThreads = PluraRuntime.getInstance().getNumberThreadsToStart() - this.pluraThreads.size(), j = 0; j < numberNewThreads; ++j) {
                final PluraThread thread2 = new PluraThread(new Plura(PluraRuntime.getInstance().getApplet()), work);
                this.pluraThreads.add(thread2);
                thread2.startPluraThread();
            }
            for (int j = 0; j < PluraRuntime.getInstance().getNumberThreadsToStart(); ++j) {
                this.pluraThreads.get(j).startPluraThread();
            }
        }
    }
    
    @Override
    public synchronized void stopPluraThreads(final boolean keepThreadsAlive, final boolean killStartPluraThread) {
        if (killStartPluraThread) {
            PluraRuntime.getInstance().getStartPluraThread().stopThread();
        }
        PluraRuntime.getInstance().setStopped(true);
        if (this.pluraThreads != null) {
            for (int i = 0; i < this.pluraThreads.size(); ++i) {
                if (this.pluraThreads.get(i) != null) {
                    this.pluraThreads.get(i).stopPluraThread();
                }
            }
            if (!keepThreadsAlive) {
                this.stopComputeInAllPluraThreads();
                this.pluraThreads = null;
            }
        }
    }
    
    @Override
    public synchronized void stopComputeInAllPluraThreads() {
        if (this.pluraThreads != null) {
            for (int i = 0; i < this.pluraThreads.size(); ++i) {
                if (this.pluraThreads.get(i) != null) {
                    this.pluraThreads.get(i).stopPluraCompute();
                }
            }
        }
    }
    
    @Override
    public synchronized boolean pluraIsStopped() {
        if (this.pluraThreads != null) {
            for (final PluraThread thread : this.pluraThreads) {
                if (thread.getState().equals(Thread.State.RUNNABLE)) {
                    return false;
                }
            }
        }
        return true;
    }
}
