// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import anon.util.JAPMessages;
import java.util.Observable;
import java.util.Enumeration;
import forward.server.ServerSocketPropagandist;
import java.util.Vector;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

public class JAPRoutingInfoServiceRegistrationTableModel extends AbstractTableModel implements Observer
{
    private static final long serialVersionUID = 1L;
    private Vector m_propagandaInstances;
    
    public JAPRoutingInfoServiceRegistrationTableModel() {
        this.m_propagandaInstances = new Vector();
    }
    
    public void updatePropagandaInstancesList(final Vector vector) {
        final Enumeration<ServerSocketPropagandist> elements = vector.elements();
        synchronized (this.m_propagandaInstances) {
            int n = 0;
            while (elements.hasMoreElements()) {
                final ServerSocketPropagandist serverSocketPropagandist = elements.nextElement();
                if (!this.m_propagandaInstances.contains(serverSocketPropagandist)) {
                    serverSocketPropagandist.addObserver(this);
                    if (serverSocketPropagandist.getCurrentState() != 3) {
                        this.m_propagandaInstances.addElement(serverSocketPropagandist);
                        ++n;
                    }
                    else {
                        serverSocketPropagandist.deleteObserver(this);
                    }
                }
            }
            if (n > 0) {
                this.fireTableRowsInserted(this.m_propagandaInstances.size() - n, this.m_propagandaInstances.size() - 1);
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        synchronized (this.m_propagandaInstances) {
            if (this.m_propagandaInstances.contains(observable)) {
                if (((ServerSocketPropagandist)observable).getCurrentState() == 3) {
                    observable.deleteObserver(this);
                    final int index = this.m_propagandaInstances.indexOf(observable);
                    this.m_propagandaInstances.removeElement(observable);
                    this.fireTableRowsDeleted(index, index);
                }
                else {
                    final int index2 = this.m_propagandaInstances.indexOf(observable);
                    this.fireTableRowsUpdated(index2, index2);
                }
            }
        }
    }
    
    public int getRowCount() {
        return this.m_propagandaInstances.size();
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public String getColumnName(final int n) {
        String s = null;
        if (n == 0) {
            s = JAPMessages.getString("routingInfoServiceRegistrationTableColumn0Name");
        }
        if (n == 1) {
            s = JAPMessages.getString("routingInfoServiceRegistrationTableColumn1Name");
        }
        return s;
    }
    
    public Object getValueAt(final int n, final int n2) {
        Object o = null;
        final ServerSocketPropagandist serverSocketPropagandist = this.m_propagandaInstances.elementAt(n);
        if (n2 == 0) {
            o = serverSocketPropagandist.getInfoService().getName();
        }
        if (n2 == 1) {
            if (serverSocketPropagandist.getCurrentState() == 0) {
                o = JAPMessages.getString("routingInfoServiceRegistrationTableStateRegistrated");
            }
            if (serverSocketPropagandist.getCurrentState() == 1) {
                o = JAPMessages.getString("routingInfoServiceRegistrationTableStateConnecting");
            }
            if (serverSocketPropagandist.getCurrentState() == 2) {
                o = JAPMessages.getString("routingInfoServiceRegistrationTableStateReconnecting");
            }
            if (serverSocketPropagandist.getCurrentState() == 3) {
                o = JAPMessages.getString("routingInfoServiceRegistrationTableStateHalted");
            }
        }
        return o;
    }
    
    public void clearPropagandaInstancesTable() {
        synchronized (this.m_propagandaInstances) {
            final int size = this.m_propagandaInstances.size();
            if (size > 0) {
                final Enumeration<ServerSocketPropagandist> elements = this.m_propagandaInstances.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().deleteObserver(this);
                }
                this.m_propagandaInstances.removeAllElements();
                this.fireTableRowsDeleted(0, size - 1);
            }
        }
    }
}
