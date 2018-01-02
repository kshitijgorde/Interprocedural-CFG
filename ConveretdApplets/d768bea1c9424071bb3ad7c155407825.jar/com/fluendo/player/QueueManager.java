// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;
import java.util.Vector;

public class QueueManager
{
    private static final int MAX_QUEUES = 32;
    private static Vector[] queues;
    private static Object[] syncs;
    private static boolean[] readWait;
    private static boolean[] writeWait;
    private static int[] sizes;
    private static int numqueues;
    static /* synthetic */ Class class$com$fluendo$player$QueueManager;
    
    public static int registerQueue(final int n) {
        Class class$;
        Class class$com$fluendo$player$QueueManager;
        if (QueueManager.class$com$fluendo$player$QueueManager == null) {
            class$com$fluendo$player$QueueManager = (QueueManager.class$com$fluendo$player$QueueManager = (class$ = class$("com.fluendo.player.QueueManager")));
        }
        else {
            class$ = (class$com$fluendo$player$QueueManager = QueueManager.class$com$fluendo$player$QueueManager);
        }
        final Class clazz = class$com$fluendo$player$QueueManager;
        int numqueues;
        synchronized (class$) {
            numqueues = QueueManager.numqueues;
            if (QueueManager.numqueues >= 32) {
                for (int i = 0; i < 32; ++i) {
                    if (QueueManager.queues[i] == null) {
                        numqueues = i;
                        break;
                    }
                }
            }
            if (numqueues >= 32) {
                Cortado.shutdown(new Throwable("no free queues available"));
                return 0;
            }
            QueueManager.queues[numqueues] = new Vector();
            QueueManager.syncs[numqueues] = new Object();
            QueueManager.sizes[numqueues] = n;
            QueueManager.readWait[numqueues] = false;
            QueueManager.writeWait[numqueues] = false;
            ++QueueManager.numqueues;
        }
        return numqueues;
    }
    
    public static void unRegisterQueue(final int n) {
        final Object o = QueueManager.syncs[n];
        QueueManager.queues[n] = null;
        QueueManager.syncs[n] = null;
        if (o != null) {
            synchronized (o) {
                o.notifyAll();
            }
        }
    }
    
    public static boolean isEmpty(final int n) {
        final Object o = QueueManager.syncs[n];
        final Vector vector = QueueManager.queues[n];
        if (o != null) {
            synchronized (o) {
                return vector.size() == 0;
            }
        }
        return false;
    }
    
    public static boolean isFilled(final int n) {
        final Object o = QueueManager.syncs[n];
        final Vector vector = QueueManager.queues[n];
        if (o != null) {
            synchronized (o) {
                return vector.size() >= QueueManager.sizes[n];
            }
        }
        return false;
    }
    
    public static void reset() {
        Class class$;
        Class class$com$fluendo$player$QueueManager;
        if (QueueManager.class$com$fluendo$player$QueueManager == null) {
            class$com$fluendo$player$QueueManager = (QueueManager.class$com$fluendo$player$QueueManager = (class$ = class$("com.fluendo.player.QueueManager")));
        }
        else {
            class$ = (class$com$fluendo$player$QueueManager = QueueManager.class$com$fluendo$player$QueueManager);
        }
        final Class clazz = class$com$fluendo$player$QueueManager;
        synchronized (class$) {
            QueueManager.numqueues = 0;
        }
    }
    
    public static void adjustOthers(final int n, final int n2) {
        for (int i = 0; i < QueueManager.numqueues; ++i) {
            if (i != n) {
                if (QueueManager.sizes[i] == Integer.MAX_VALUE || QueueManager.sizes[i] < 1) {
                    return;
                }
                synchronized (QueueManager.syncs[i]) {
                    if (QueueManager.sizes[i] < QueueManager.queues[i].size()) {
                        final int[] sizes = QueueManager.sizes;
                        final int n3 = i;
                        sizes[n3] += n2;
                        QueueManager.syncs[i].notify();
                    }
                }
            }
        }
    }
    
    public static void adjustThis(final int n, final int n2) {
        if (QueueManager.sizes[n] == Integer.MAX_VALUE || QueueManager.sizes[n] < 1) {
            return;
        }
        synchronized (QueueManager.syncs[n]) {
            if (QueueManager.sizes[n] < QueueManager.queues[n].size()) {
                final int[] sizes = QueueManager.sizes;
                sizes[n] += n2;
                QueueManager.syncs[n].notify();
            }
        }
    }
    
    public static void enqueue(final int n, final Object o) throws InterruptedException {
        final Object o2 = QueueManager.syncs[n];
        final Vector vector = QueueManager.queues[n];
        synchronized (o2) {
            while (vector.size() >= QueueManager.sizes[n]) {
                QueueManager.writeWait[n] = true;
                o2.wait();
                QueueManager.writeWait[n] = false;
                if (QueueManager.syncs[n] == null) {
                    return;
                }
            }
            vector.addElement(o);
            if (QueueManager.readWait[n]) {
                o2.notify();
            }
        }
    }
    
    public static Object dequeue(final int n) throws InterruptedException {
        final Object o = QueueManager.syncs[n];
        final Vector vector = QueueManager.queues[n];
        Object element = null;
        if (o == null) {
            return null;
        }
        synchronized (o) {
            while (vector.size() == 0) {
                QueueManager.readWait[n] = true;
                o.wait();
                QueueManager.readWait[n] = false;
                if (QueueManager.syncs[n] == null) {
                    return null;
                }
            }
            element = vector.elementAt(0);
            vector.removeElementAt(0);
            if (QueueManager.writeWait[n]) {
                o.notify();
            }
        }
        return element;
    }
    
    public static void dumpStats() {
        final StringBuffer sb = new StringBuffer();
        sb.append("queues:");
        for (int i = 0; i < QueueManager.numqueues; ++i) {
            sb.append(" [id:" + i + ", size:" + QueueManager.queues[i].size() + ", max:" + QueueManager.sizes[i] + "]");
        }
        Debug.log(4, sb.toString());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        QueueManager.queues = new Vector[32];
        QueueManager.syncs = new Object[32];
        QueueManager.readWait = new boolean[32];
        QueueManager.writeWait = new boolean[32];
        QueueManager.sizes = new int[32];
        QueueManager.numqueues = 0;
    }
}
