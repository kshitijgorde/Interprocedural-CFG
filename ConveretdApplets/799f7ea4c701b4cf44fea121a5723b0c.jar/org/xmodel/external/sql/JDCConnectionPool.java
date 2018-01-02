// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external.sql;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Vector;

public class JDCConnectionPool
{
    private Vector<JDCConnection> E;
    private String D;
    private String B;
    private String C;
    private final long G = 60000L;
    private A F;
    private final int A = 10;
    
    public JDCConnectionPool(final String d, final String b, final String c) {
        this.D = d;
        this.B = b;
        this.C = c;
        this.E = new Vector<JDCConnection>(10);
        (this.F = new A(this)).start();
    }
    
    public synchronized void reapConnections() {
        final long n = System.currentTimeMillis() - 60000L;
        final Enumeration<JDCConnection> elements = this.E.elements();
        while (elements != null && elements.hasMoreElements()) {
            final JDCConnection jdcConnection = elements.nextElement();
            if (jdcConnection.inUse() && n > jdcConnection.getLastUse() && !jdcConnection.validate()) {
                this.A(jdcConnection);
            }
        }
    }
    
    public synchronized void closeConnections() {
        final Enumeration<JDCConnection> elements = this.E.elements();
        while (elements != null && elements.hasMoreElements()) {
            this.A(elements.nextElement());
        }
    }
    
    private synchronized void A(final JDCConnection jdcConnection) {
        this.E.removeElement(jdcConnection);
    }
    
    public synchronized Connection getConnection() throws SQLException {
        for (int i = 0; i < this.E.size(); ++i) {
            final JDCConnection jdcConnection = this.E.elementAt(i);
            if (jdcConnection.lease()) {
                return jdcConnection;
            }
        }
        final JDCConnection jdcConnection2 = new JDCConnection(DriverManager.getConnection(this.D, this.B, this.C), this);
        jdcConnection2.lease();
        this.E.addElement(jdcConnection2);
        return jdcConnection2;
    }
    
    public synchronized void returnConnection(final JDCConnection jdcConnection) {
        jdcConnection.expireLease();
    }
}
