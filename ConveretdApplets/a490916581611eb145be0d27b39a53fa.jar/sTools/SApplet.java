// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;

public class SApplet extends Applet
{
    public static Hashtable dataSources;
    public static Hashtable dataListeners;
    private static Object runningID;
    private static int nextID;
    public Vector dataConnections;
    
    public SApplet() {
        this.dataConnections = new Vector();
    }
    
    public synchronized void destroy() {
        Hashtable hashtable;
        synchronized (SApplet.dataSources) {
            hashtable = (Hashtable)SApplet.dataSources.clone();
        }
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            if (((SDataSource)SApplet.dataSources.get(nextElement)).getOwner() == this) {
                SApplet.dataSources.remove(nextElement);
            }
        }
        synchronized (SApplet.dataListeners) {
            hashtable = (Hashtable)SApplet.dataListeners.clone();
        }
        final Enumeration<Object> keys2 = hashtable.keys();
        while (keys2.hasMoreElements()) {
            final Object nextElement2 = keys2.nextElement();
            if (((SDataListener)SApplet.dataListeners.get(nextElement2)).getOwner() == this) {
                SApplet.dataListeners.remove(nextElement2);
            }
        }
    }
    
    private SDataConnection getExistingConnection(final int n, final int n2, final int n3) {
        return null;
    }
    
    public int makeDataConnection(final int n, final int n2, final int n3, final String s, final String s2) {
        final SDataListener dataListener = getDataListener(n2);
        final SDataSource dataSource = getDataSource(n);
        if (dataSource != null && dataListener != null) {
            SDataConnection existingConnection = this.getExistingConnection(n, n2, n3);
            if (existingConnection == null) {
                existingConnection = new SDataConnection(dataSource, dataListener, n3, s, s2);
                this.dataConnections.addElement(existingConnection);
            }
            return existingConnection.hashCode();
        }
        System.out.println(String.valueOf(String.valueOf(String.valueOf("DataConnection not made.  Listener:").concat(String.valueOf(dataListener))).concat(String.valueOf("  Source:"))).concat(String.valueOf(dataSource)));
        return 0;
    }
    
    public void clearAllData() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().clearData();
        }
    }
    
    public void clearData(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataConnection.getDataSource().hashCode() == n) {
                sDataConnection.clearData();
            }
        }
    }
    
    public void updateDataConnection(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataConnection.getDataSource().hashCode() == n) {
                sDataConnection.registerDatum();
            }
        }
    }
    
    public void updateDataConnections() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().registerDatum();
        }
    }
    
    public void deleteDataConnections() {
        this.dataConnections.removeAllElements();
    }
    
    public void deleteDataConnection(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (n == sDataConnection.hashCode()) {
                this.dataConnections.removeElement(sDataConnection);
            }
        }
    }
    
    public SDataConnection getDataConnectionFromDS(final SDataSource sDataSource) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataSource == sDataConnection.getDataSource()) {
                return sDataConnection;
            }
        }
        return null;
    }
    
    public SDataConnection getDataConnectionFromDL(final SDataListener sDataListener) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataListener == sDataConnection.getDataListener()) {
                return sDataConnection;
            }
        }
        return null;
    }
    
    public final void cleanupDataConnections() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            final SDataListener dataListener = sDataConnection.getDataListener();
            final SDataSource dataSource = sDataConnection.getDataSource();
            if (!SApplet.dataListeners.contains(dataListener) || !SApplet.dataSources.contains(dataSource)) {
                this.dataConnections.removeElement(sDataConnection);
            }
        }
    }
    
    public final Object getRunningID() {
        return SApplet.runningID;
    }
    
    public final void setRunningID(final Object runningID) {
        SApplet.runningID = runningID;
    }
    
    public final int getID() {
        return this.hashCode();
    }
    
    public static final void addDataSource(final Object o) {
        if (o instanceof SDataSource) {
            SApplet.dataSources.put(Integer.toString(o.hashCode()), o);
        }
        else {
            System.out.println(String.valueOf("Error: ds is not a DataSource in SApplet.addDataSource:").concat(String.valueOf(o.hashCode())));
        }
    }
    
    public final void removeDataSource(final int n) {
        final SDataSource dataSource = getDataSource(n);
        SDataConnection dataConnectionFromDS;
        do {
            dataConnectionFromDS = this.getDataConnectionFromDS(dataSource);
            if (dataConnectionFromDS != null) {
                this.dataConnections.removeElement(dataConnectionFromDS);
            }
        } while (dataConnectionFromDS != null);
        SApplet.dataSources.remove(Integer.toString(n));
    }
    
    public static final SDataSource getDataSource(final String s) {
        return SApplet.dataSources.get(s);
    }
    
    public static final SDataSource getDataSource(final int n) {
        return SApplet.dataSources.get(Integer.toString(n));
    }
    
    public final void removeDataListener(final int n) {
        final SDataListener dataListener = getDataListener(n);
        SDataConnection dataConnectionFromDL;
        do {
            dataConnectionFromDL = this.getDataConnectionFromDL(dataListener);
            if (dataConnectionFromDL != null) {
                this.dataConnections.removeElement(dataConnectionFromDL);
            }
        } while (dataConnectionFromDL != null);
        SApplet.dataListeners.remove(Integer.toString(n));
    }
    
    public static final void addDataListener(final Object o) {
        if (o instanceof SDataListener) {
            SApplet.dataListeners.put(Integer.toString(o.hashCode()), o);
        }
        else {
            System.out.println(String.valueOf("Error: dl is not a DataListener in SApplet.addDataListener:").concat(String.valueOf(o.hashCode())));
        }
    }
    
    public static final SDataListener getDataListener(final String s) {
        return SApplet.dataListeners.get(s);
    }
    
    public static final SDataListener getDataListener(final int n) {
        return SApplet.dataListeners.get(Integer.toString(n));
    }
    
    static {
        SApplet.dataSources = new Hashtable(50);
        SApplet.dataListeners = new Hashtable(20);
        SApplet.runningID = null;
        SApplet.nextID = 0;
    }
}
