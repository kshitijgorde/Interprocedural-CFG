// 
// Decompiled by Procyon v0.5.30
// 

package en.network;

import java.util.Vector;
import java.io.Serializable;

public class Network implements Serializable
{
    Vector nodes;
    Vector connections;
    Vector sequence;
    public Vector nodeUIs;
    public Vector connectionUIs;
    boolean sync;
    boolean verbose;
    volatile boolean loop;
    volatile boolean executing;
    
    public Network() {
        this.nodes = new Vector(64);
        this.connections = new Vector(64);
        this.sequence = new Vector(64);
        this.nodeUIs = new Vector(64);
        this.connectionUIs = new Vector(64);
        this.sync = false;
        this.verbose = true;
        this.loop = false;
        this.executing = false;
    }
    
    public final void addNode(final y y) {
        if (y == null) {
            return;
        }
        y.p(this);
        this.nodes.addElement(y);
    }
    
    public final boolean connect(final v v, final v v2) {
        final y p2 = v.p();
        final y p3 = v2.p();
        if (p2 == null || p3 == null || v == null || v2 == null) {
            return false;
        }
        if (p2 == p3) {
            return false;
        }
        if (!v.d(v2)) {
            return false;
        }
        if (!v.a(v2)) {
            return false;
        }
        final k k = new k(v, v2);
        v.p(k);
        v2.p(k);
        this.connections.addElement(k);
        this.updateSequenceID(p2, p3);
        this.sync = false;
        if (this.verbose) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Network: connected [").append(p2.p()).append(".").append(v.d()).append(" -> ").append(p3.p()).append(".").append(v2.d()).append("]"))));
        }
        return true;
    }
    
    public final synchronized void execute() throws Exception {
        if (!this.sync) {
            this.updateSequence();
        }
        if (this.loop) {
            while (this.loop) {
                this.executeOnce();
            }
        }
        else {
            this.executeOnce();
        }
    }
    
    protected final void executeOnce() throws Exception {
        this.executing = true;
        for (int i = 0; i < this.sequence.size(); ++i) {
            ((y)this.sequence.elementAt(i)).p();
        }
        this.executing = false;
    }
    
    public final void updateSequence() {
        int i = 0;
        int j = 0;
        for (int k = 0; k < this.nodes.size(); ++k) {
            final y y = this.nodes.elementAt(k);
            if (y.i < i) {
                i = y.i;
            }
            if (y.i > j) {
                j = y.i;
            }
        }
        this.sequence.removeAllElements();
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Network: sequence [min = ").append(i).append(", max = ").append(j).append("]"))));
        for (int l = i; l <= j; ++l) {
            for (int n = 0; n < this.nodes.size(); ++n) {
                final y y2 = this.nodes.elementAt(n);
                if (y2.i == l) {
                    this.sequence.addElement(y2);
                }
            }
        }
        this.sync = true;
    }
    
    public final void updateSequenceID(final y y, final y y2) {
        if (y.i >= y2.i) {
            y2.i = y.i + 1;
            for (int i = 0; i < y2.p().size(); ++i) {
                this.updateSequenceID(y2, ((k)y2.p().elementAt(i)).p());
            }
        }
    }
    
    public final void printStatus() {
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Network: ").append(this.nodesCount()).append(" nodes, ").append(this.connectionsCount()).append(" connections"))));
    }
    
    public final int nodesCount() {
        return this.nodes.size();
    }
    
    public final int connectionsCount() {
        return this.connections.size();
    }
}
