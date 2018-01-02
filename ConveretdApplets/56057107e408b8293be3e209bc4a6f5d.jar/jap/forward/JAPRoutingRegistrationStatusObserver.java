// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import java.util.Enumeration;
import jap.JAPModel;
import forward.server.ServerSocketPropagandist;
import java.util.Vector;
import java.util.Observer;
import java.util.Observable;

public class JAPRoutingRegistrationStatusObserver extends Observable implements Observer
{
    public static final int STATE_DISABLED = 0;
    public static final int STATE_INITIAL_REGISTRATION = 1;
    public static final int STATE_NO_REGISTRATION = 2;
    public static final int STATE_SUCCESSFUL_REGISTRATION = 3;
    public static final int ERROR_NO_ERROR = 0;
    public static final int ERROR_NO_KNOWN_PRIMARY_INFOSERVICES = 1;
    public static final int ERROR_INFOSERVICE_CONNECT_ERROR = 2;
    public static final int ERROR_VERIFICATION_ERROR = 3;
    public static final int ERROR_UNKNOWN_ERROR = 4;
    private Vector m_propagandaInstances;
    private int m_currentState;
    private int m_currentErrorCode;
    static /* synthetic */ Class class$forward$server$ServerSocketPropagandist;
    
    public JAPRoutingRegistrationStatusObserver() {
        this.m_propagandaInstances = new Vector();
        this.m_currentState = 0;
        this.m_currentErrorCode = 0;
    }
    
    public int getCurrentState() {
        return this.m_currentState;
    }
    
    public int getCurrentErrorCode() {
        return this.m_currentErrorCode;
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable.getClass().equals((JAPRoutingRegistrationStatusObserver.class$forward$server$ServerSocketPropagandist == null) ? (JAPRoutingRegistrationStatusObserver.class$forward$server$ServerSocketPropagandist = class$("forward.server.ServerSocketPropagandist")) : JAPRoutingRegistrationStatusObserver.class$forward$server$ServerSocketPropagandist)) {
            synchronized (this.m_propagandaInstances) {
                if (this.m_propagandaInstances.contains(observable) && ((ServerSocketPropagandist)observable).getCurrentState() == 3) {
                    observable.deleteObserver(this);
                    this.m_propagandaInstances.removeElement(observable);
                    this.updateCurrentState(false);
                }
            }
        }
        try {
            if (observable == JAPModel.getInstance().getRoutingSettings()) {
                int n = 0;
                if (((JAPRoutingMessage)o).getMessageCode() == 5) {
                    synchronized (this) {
                        if (this.m_currentState != 0) {
                            this.m_currentState = 0;
                            this.m_currentErrorCode = 0;
                            n = 1;
                        }
                    }
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 3) {
                    synchronized (this) {
                        if (this.m_currentState != 1) {
                            this.m_currentState = 1;
                            this.m_currentErrorCode = 0;
                            n = 1;
                        }
                    }
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 2) {
                    this.updatePropagandaInstancesList((Vector)((JAPRoutingMessage)o).getMessageData());
                    this.updateCurrentState(false);
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 4) {
                    this.updateCurrentState(true);
                }
                if (n == 1) {
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(14));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void updateCurrentState(final boolean b) {
        synchronized (this.m_propagandaInstances) {
            synchronized (this) {
                if (this.m_currentState == 2 || this.m_currentState == 3 || (this.m_currentState == 1 && b)) {
                    int currentState = 2;
                    int currentErrorCode = 1;
                    if (this.m_propagandaInstances.size() > 0) {
                        currentErrorCode = 4;
                        final Enumeration elements = this.m_propagandaInstances.elements();
                        while (currentState != 3 && elements.hasMoreElements()) {
                            final ServerSocketPropagandist serverSocketPropagandist = elements.nextElement();
                            if (serverSocketPropagandist.getCurrentState() == 0) {
                                currentState = 3;
                                currentErrorCode = 0;
                            }
                            else {
                                if (serverSocketPropagandist.getCurrentState() != 1 && serverSocketPropagandist.getCurrentState() != 2) {
                                    continue;
                                }
                                if (currentErrorCode == 4 && serverSocketPropagandist.getCurrentErrorCode() == 2) {
                                    currentErrorCode = 2;
                                }
                                if ((currentErrorCode != 4 && currentErrorCode != 2) || serverSocketPropagandist.getCurrentErrorCode() != 1) {
                                    continue;
                                }
                                currentErrorCode = 3;
                            }
                        }
                    }
                    if (currentState != this.m_currentState || currentErrorCode != this.m_currentErrorCode) {
                        this.m_currentState = currentState;
                        this.m_currentErrorCode = currentErrorCode;
                        this.setChanged();
                        this.notifyObservers(new JAPRoutingMessage(14));
                    }
                }
            }
        }
    }
    
    private void updatePropagandaInstancesList(final Vector vector) {
        final Enumeration<ServerSocketPropagandist> elements = vector.elements();
        synchronized (this.m_propagandaInstances) {
            while (elements.hasMoreElements()) {
                final ServerSocketPropagandist serverSocketPropagandist = elements.nextElement();
                if (!this.m_propagandaInstances.contains(serverSocketPropagandist)) {
                    serverSocketPropagandist.addObserver(this);
                    if (serverSocketPropagandist.getCurrentState() != 3) {
                        this.m_propagandaInstances.addElement(serverSocketPropagandist);
                    }
                    else {
                        serverSocketPropagandist.deleteObserver(this);
                    }
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
