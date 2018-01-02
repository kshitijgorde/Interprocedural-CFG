// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import org.w3c.dom.NodeList;
import anon.infoservice.Database;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.infoservice.InfoServiceHolder;
import anon.infoservice.InfoServiceDBEntry;
import java.util.Enumeration;
import forward.server.ServerSocketPropagandist;
import jap.JAPModel;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

public class JAPRoutingRegistrationInfoServices extends Observable implements Observer, Runnable
{
    private static final long INFOSERVICELIST_UPDATE_INTERVAL = 600000L;
    Hashtable m_registrationInfoServices;
    boolean m_registerAtAllAvailableInfoServices;
    boolean m_propagandaIsRunning;
    Vector m_runningInfoServiceRegistrations;
    Thread m_updateInfoServiceListThread;
    static /* synthetic */ Class class$anon$infoservice$InfoServiceDBEntry;
    
    public JAPRoutingRegistrationInfoServices() {
        this.m_registrationInfoServices = new Hashtable();
        this.m_registerAtAllAvailableInfoServices = true;
        this.m_propagandaIsRunning = false;
        this.m_runningInfoServiceRegistrations = new Vector();
        this.m_updateInfoServiceListThread = null;
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable == JAPModel.getInstance().getRoutingSettings()) {
            try {
                if (((JAPRoutingMessage)o).getMessageCode() == 2) {
                    synchronized (this) {
                        synchronized (this.m_runningInfoServiceRegistrations) {
                            final Enumeration<ServerSocketPropagandist> elements = ((Vector)((JAPRoutingMessage)o).getMessageData()).elements();
                            while (elements.hasMoreElements()) {
                                final InfoServiceDBEntry infoService = elements.nextElement().getInfoService();
                                if (!this.m_runningInfoServiceRegistrations.contains(infoService.getId())) {
                                    this.m_runningInfoServiceRegistrations.addElement(infoService.getId());
                                }
                            }
                        }
                    }
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 4) {
                    synchronized (this) {
                        this.m_propagandaIsRunning = true;
                        if (this.m_registerAtAllAvailableInfoServices) {
                            this.startInfoServiceListUpdateThread();
                        }
                    }
                }
                if (((JAPRoutingMessage)o).getMessageCode() == 5) {
                    synchronized (this) {
                        if (this.m_registerAtAllAvailableInfoServices) {
                            this.stopInfoServiceListUpdateThread();
                        }
                        this.m_propagandaIsRunning = false;
                        synchronized (this.m_runningInfoServiceRegistrations) {
                            this.m_runningInfoServiceRegistrations.removeAllElements();
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void setRegistrationInfoServices(final Vector vector) {
        synchronized (this.m_registrationInfoServices) {
            this.m_registrationInfoServices.clear();
            final Enumeration<InfoServiceDBEntry> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final InfoServiceDBEntry infoServiceDBEntry = elements.nextElement();
                if (infoServiceDBEntry.hasPrimaryForwarderList()) {
                    this.m_registrationInfoServices.put(infoServiceDBEntry.getId(), infoServiceDBEntry);
                }
            }
        }
        synchronized (this) {
            if (!this.m_registerAtAllAvailableInfoServices) {
                synchronized (this.m_runningInfoServiceRegistrations) {
                    final Enumeration<InfoServiceDBEntry> elements2 = this.m_registrationInfoServices.elements();
                    while (elements2.hasMoreElements()) {
                        final InfoServiceDBEntry infoServiceDBEntry2 = elements2.nextElement();
                        if (!this.m_runningInfoServiceRegistrations.contains(infoServiceDBEntry2.getId())) {
                            JAPModel.getInstance().getRoutingSettings().addPropagandaInstance(infoServiceDBEntry2);
                            if (!this.m_propagandaIsRunning) {
                                continue;
                            }
                            this.m_runningInfoServiceRegistrations.addElement(infoServiceDBEntry2.getId());
                        }
                    }
                }
            }
            this.setChanged();
            this.notifyObservers(new JAPRoutingMessage(12));
        }
    }
    
    public void addToRegistrationInfoServices(final InfoServiceDBEntry infoServiceDBEntry) {
        if (infoServiceDBEntry != null && infoServiceDBEntry.hasPrimaryForwarderList()) {
            synchronized (this.m_registrationInfoServices) {
                this.m_registrationInfoServices.put(infoServiceDBEntry.getId(), infoServiceDBEntry);
            }
            synchronized (this) {
                if (!this.m_registerAtAllAvailableInfoServices) {
                    synchronized (this.m_runningInfoServiceRegistrations) {
                        if (!this.m_runningInfoServiceRegistrations.contains(infoServiceDBEntry.getId())) {
                            JAPModel.getInstance().getRoutingSettings().addPropagandaInstance(infoServiceDBEntry);
                            if (this.m_propagandaIsRunning) {
                                this.m_runningInfoServiceRegistrations.addElement(infoServiceDBEntry.getId());
                            }
                        }
                    }
                }
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(12));
            }
        }
    }
    
    public void removeFromRegistrationInfoServices(final String s) {
        if (s != null) {
            int n = 0;
            synchronized (this.m_registrationInfoServices) {
                if (this.m_registrationInfoServices.remove(s) != null) {
                    n = 1;
                }
            }
            if (n == 1) {
                synchronized (this) {
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(12));
                }
            }
        }
    }
    
    public Vector getRegistrationInfoServices() {
        final Vector<Object> vector = new Vector<Object>();
        synchronized (this.m_registrationInfoServices) {
            final Enumeration<Object> elements = this.m_registrationInfoServices.elements();
            while (elements.hasMoreElements()) {
                vector.addElement(elements.nextElement());
            }
        }
        return vector;
    }
    
    public Vector getRegistrationInfoServicesForStartup() {
        Vector vector = new Vector();
        synchronized (this) {
            if (this.m_registerAtAllAvailableInfoServices) {
                vector = InfoServiceHolder.getInstance().getInfoservicesWithForwarderList();
            }
            else {
                vector = this.getRegistrationInfoServices();
            }
        }
        return vector;
    }
    
    public void setRegisterAtAllAvailableInfoServices(final boolean registerAtAllAvailableInfoServices) {
        synchronized (this) {
            if (this.m_registerAtAllAvailableInfoServices != registerAtAllAvailableInfoServices) {
                this.m_registerAtAllAvailableInfoServices = registerAtAllAvailableInfoServices;
                if (this.m_propagandaIsRunning) {
                    if (registerAtAllAvailableInfoServices) {
                        this.startInfoServiceListUpdateThread();
                    }
                    else {
                        this.stopInfoServiceListUpdateThread();
                    }
                }
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(11));
            }
        }
    }
    
    public boolean getRegisterAtAllAvailableInfoServices() {
        boolean registerAtAllAvailableInfoServices = false;
        synchronized (this) {
            registerAtAllAvailableInfoServices = this.m_registerAtAllAvailableInfoServices;
        }
        return registerAtAllAvailableInfoServices;
    }
    
    public Element getSettingsAsXml(final Document document) {
        final Element element = document.createElement("InfoServiceRegistrationSettings");
        final Element element2 = document.createElement("UseAllPrimaryInfoServices");
        final Element element3 = document.createElement("RegistrationInfoServices");
        synchronized (this) {
            XMLUtil.setValue(element2, this.getRegisterAtAllAvailableInfoServices());
            final Enumeration<InfoServiceDBEntry> elements = this.getRegistrationInfoServices().elements();
            while (elements.hasMoreElements()) {
                element3.appendChild(elements.nextElement().toXmlElement(document));
            }
        }
        element.appendChild(element2);
        element.appendChild(element3);
        return element;
    }
    
    public boolean loadSettingsFromXml(final Element element) {
        boolean b = true;
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "UseAllPrimaryInfoServices");
        if (element2 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingRegistrationInfoServices: loadSettingsFromXml: Error in XML structure (UseAllPrimaryInfoServices node): Using default setting.");
            b = false;
        }
        else {
            this.setRegisterAtAllAvailableInfoServices(XMLUtil.parseValue(element2, this.getRegisterAtAllAvailableInfoServices()));
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "RegistrationInfoServices");
        if (element3 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingRegistrationInfoServices: loadSettingsFromXml: Error in XML structure (RegistrationInfoServices node): Skip loading of registration infoservices.");
            b = false;
        }
        else {
            final NodeList elementsByTagName = element3.getElementsByTagName("InfoService");
            final Vector<InfoServiceDBEntry> registrationInfoServices = new Vector<InfoServiceDBEntry>();
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element4 = (Element)elementsByTagName.item(i);
                try {
                    InfoServiceDBEntry infoServiceDBEntry = new InfoServiceDBEntry(element4, Long.MAX_VALUE);
                    final InfoServiceDBEntry infoServiceDBEntry2 = (InfoServiceDBEntry)Database.getInstance((JAPRoutingRegistrationInfoServices.class$anon$infoservice$InfoServiceDBEntry == null) ? (JAPRoutingRegistrationInfoServices.class$anon$infoservice$InfoServiceDBEntry = class$("anon.infoservice.InfoServiceDBEntry")) : JAPRoutingRegistrationInfoServices.class$anon$infoservice$InfoServiceDBEntry).getEntryById(infoServiceDBEntry.getId());
                    if (infoServiceDBEntry2 != null) {
                        infoServiceDBEntry = infoServiceDBEntry2;
                    }
                    if (infoServiceDBEntry.hasPrimaryForwarderList()) {
                        registrationInfoServices.addElement(infoServiceDBEntry);
                    }
                    else {
                        LogHolder.log(3, LogType.MISC, "JAPRoutingRegistrationInfoServices: loadSettingsFromXml: Error while loading one registration InfoService: The InfoService " + infoServiceDBEntry.getName() + " has no primary forwarder list: Skipping this infoservice.");
                        b = false;
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingRegistrationInfoServices: loadSettingsFromXml: Error while loading one registration InfoService: Skipping this infoservice (" + ex.toString() + ").");
                    b = false;
                }
            }
            this.setRegistrationInfoServices(registrationInfoServices);
        }
        return b;
    }
    
    public void run() {
        int i = 0;
        while (i == 0) {
            synchronized (this.m_updateInfoServiceListThread) {
                i = (Thread.interrupted() ? 1 : 0);
                if (i == 0) {
                    try {
                        this.m_updateInfoServiceListThread.wait(600000L);
                    }
                    catch (Exception ex) {
                        i = 1;
                    }
                }
            }
            if (i == 0) {
                final Hashtable infoServices = InfoServiceHolder.getInstance().getInfoServices();
                if (infoServices == null) {
                    continue;
                }
                final Enumeration<InfoServiceDBEntry> elements = infoServices.elements();
                while (elements.hasMoreElements()) {
                    final InfoServiceDBEntry infoServiceDBEntry = elements.nextElement();
                    if (infoServiceDBEntry.hasPrimaryForwarderList()) {
                        synchronized (this.m_runningInfoServiceRegistrations) {
                            if (this.m_runningInfoServiceRegistrations.contains(infoServiceDBEntry.getId())) {
                                continue;
                            }
                            JAPModel.getInstance().getRoutingSettings().addPropagandaInstance(infoServiceDBEntry);
                            this.m_runningInfoServiceRegistrations.addElement(infoServiceDBEntry.getId());
                        }
                    }
                }
            }
        }
    }
    
    private void startInfoServiceListUpdateThread() {
        synchronized (this) {
            if (this.m_updateInfoServiceListThread == null) {
                LogHolder.log(6, LogType.MISC, "JAPRoutingRegistrationInfoServices: startInfoServiceListUpdateThread: The infoservice registration management thread is started.");
                (this.m_updateInfoServiceListThread = new Thread(this)).setDaemon(true);
                this.m_updateInfoServiceListThread.start();
            }
            else {
                LogHolder.log(6, LogType.MISC, "JAPRoutingRegistrationInfoServices: startInfoServiceListUpdateThread: The infoservice registration management thread was already started.");
            }
        }
    }
    
    private void stopInfoServiceListUpdateThread() {
        LogHolder.log(6, LogType.MISC, "JAPRoutingRegistrationInfoServices: stopInfoServiceListUpdateThread: Shutdown the infoservice registration management thread...");
        synchronized (this) {
            if (this.m_updateInfoServiceListThread != null) {
                synchronized (this.m_updateInfoServiceListThread) {
                    this.m_updateInfoServiceListThread.interrupt();
                }
                try {
                    this.m_updateInfoServiceListThread.join();
                    LogHolder.log(6, LogType.MISC, "JAPRoutingRegistrationInfoServices: stopInfoServiceListUpdateThread: Infoservice registration management thread halted.");
                }
                catch (Exception ex) {}
                this.m_updateInfoServiceListThread = null;
            }
            else {
                LogHolder.log(6, LogType.MISC, "JAPRoutingRegistrationInfoServices: stopInfoServiceListUpdateThread: Infoservice registration management thread was not running.");
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
