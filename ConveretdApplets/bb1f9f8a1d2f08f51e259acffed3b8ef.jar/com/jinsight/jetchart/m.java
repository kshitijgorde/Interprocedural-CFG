// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

class m extends Thread
{
    Graph a;
    Slice b;
    
    public void run() {
        final boolean g = GraphSerie.G;
        try {
            Thread.sleep(this.a.bu);
        }
        catch (InterruptedException ex) {
            if (g) {}
        }
        synchronized (this.a.bv) {
            while (true) {
                while (true) {
                    Label_0063: {
                        if (!g) {
                            break Label_0063;
                        }
                        try {
                            final m m = this;
                            m.a.bv.wait();
                        }
                        catch (InterruptedException ex2) {
                            if (g) {}
                        }
                    }
                    if (!this.a.bv.c) {
                        continue;
                    }
                    break;
                }
                this.a.bv.c = false;
                final m m = this;
                if (g) {
                    continue;
                }
                break;
            }
            if (this == this.a.br && this.a.bs) {
                this.a.bv.a();
                this.a.repaint();
            }
            this.a.bv.c = true;
            this.a.bv.notifyAll();
        }
        // monitorexit(this.a.bv)
    }
    
    m(final Graph a) {
        this.a = a;
    }
}
