// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

class A extends Thread
{
    private JDCConnectionPool B;
    private final long A = 300000L;
    
    A(final JDCConnectionPool b) {
        this.setDaemon(true);
        this.B = b;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300000L);
            }
            catch (InterruptedException ex) {}
            this.B.reapConnections();
        }
    }
}
