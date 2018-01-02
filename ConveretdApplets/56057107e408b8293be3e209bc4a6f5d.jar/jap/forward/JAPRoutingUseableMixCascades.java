// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import forward.server.ForwardServerManager;
import anon.infoservice.InfoServiceHolder;
import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Enumeration;
import anon.infoservice.MixCascade;
import java.util.Vector;
import jap.JAPModel;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;

public final class JAPRoutingUseableMixCascades extends Observable implements Observer, Runnable
{
    private static final long MIXCASCADELIST_UPDATE_INTERVAL = 600000L;
    Hashtable m_allowedMixCascades;
    boolean m_allowAllAvailableCascades;
    Hashtable m_currentlyRunningMixCascades;
    Thread m_updateMixCascadesListThread;
    
    public JAPRoutingUseableMixCascades() {
        this.m_allowedMixCascades = new Hashtable();
        this.m_allowAllAvailableCascades = true;
        this.m_currentlyRunningMixCascades = new Hashtable();
        this.m_updateMixCascadesListThread = null;
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable == JAPModel.getInstance().getRoutingSettings()) {
            try {
                if (((JAPRoutingMessage)o).getMessageCode() == 1) {
                    synchronized (this) {
                        if (JAPModel.getInstance().getRoutingSettings().getRoutingMode() == 2 || JAPModel.getInstance().getRoutingSettings().getForwarderAddress().getTransportIdentifier().equals("local")) {
                            if (this.m_updateMixCascadesListThread == null) {
                                this.startMixCascadesListUpdateThread();
                            }
                        }
                        else if (this.m_updateMixCascadesListThread != null) {
                            this.stopMixCascadesListUpdateThread();
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void setAllowedMixCascades(final Vector vector) {
        synchronized (this.m_allowedMixCascades) {
            this.m_allowedMixCascades.clear();
            final Enumeration<MixCascade> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final MixCascade mixCascade = elements.nextElement();
                this.m_allowedMixCascades.put(mixCascade.getId(), mixCascade);
            }
        }
        synchronized (this) {
            if (this.m_updateMixCascadesListThread != null && !this.m_allowAllAvailableCascades) {
                this.updateUseableCascadesDatabase();
            }
            this.setChanged();
            this.notifyObservers(new JAPRoutingMessage(10));
        }
    }
    
    public void addToAllowedMixCascades(final MixCascade mixCascade) {
        if (mixCascade != null) {
            synchronized (this.m_allowedMixCascades) {
                this.m_allowedMixCascades.put(mixCascade.getId(), mixCascade);
            }
            synchronized (this) {
                if (this.m_updateMixCascadesListThread != null && !this.m_allowAllAvailableCascades) {
                    this.updateUseableCascadesDatabase();
                }
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(10));
            }
        }
    }
    
    public void removeFromAllowedMixCascades(final String s) {
        if (s != null) {
            int n = 0;
            synchronized (this.m_allowedMixCascades) {
                if (this.m_allowedMixCascades.remove(s) != null) {
                    n = 1;
                }
            }
            if (n == 1) {
                synchronized (this) {
                    if (this.m_updateMixCascadesListThread != null && !this.m_allowAllAvailableCascades) {
                        this.updateUseableCascadesDatabase();
                    }
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(10));
                }
            }
        }
    }
    
    public Vector getAllowedMixCascades() {
        final Vector<Object> vector = new Vector<Object>();
        synchronized (this.m_allowedMixCascades) {
            final Enumeration<Object> elements = this.m_allowedMixCascades.elements();
            while (elements.hasMoreElements()) {
                vector.addElement(elements.nextElement());
            }
        }
        return vector;
    }
    
    public void setAllowAllAvailableMixCascades(final boolean allowAllAvailableCascades) {
        synchronized (this) {
            if (this.m_allowAllAvailableCascades != allowAllAvailableCascades) {
                this.m_allowAllAvailableCascades = allowAllAvailableCascades;
                if (this.m_updateMixCascadesListThread != null) {
                    this.updateUseableCascadesDatabase();
                }
                this.setChanged();
                this.notifyObservers(new JAPRoutingMessage(9));
            }
        }
    }
    
    public boolean getAllowAllAvailableMixCascades() {
        boolean allowAllAvailableCascades = false;
        synchronized (this) {
            allowAllAvailableCascades = this.m_allowAllAvailableCascades;
        }
        return allowAllAvailableCascades;
    }
    
    public Element getSettingsAsXml(final Document document) {
        final Element element = document.createElement("AllowedMixCascadesSettings");
        final Element element2 = document.createElement("AllowAllAvailableMixCascades");
        final Element element3 = document.createElement("AllowedMixCascades");
        synchronized (this) {
            XMLUtil.setValue(element2, this.getAllowAllAvailableMixCascades());
            final Enumeration<MixCascade> elements = this.getAllowedMixCascades().elements();
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
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "AllowAllAvailableMixCascades");
        if (element2 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingUseableMixCascades: loadSettingsFromXml: Error in XML structure (AllowAllAvailableMixCascades node): Using default setting.");
            b = false;
        }
        else {
            this.setAllowAllAvailableMixCascades(XMLUtil.parseValue(element2, this.getAllowAllAvailableMixCascades()));
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "AllowedMixCascades");
        if (element3 == null) {
            LogHolder.log(3, LogType.MISC, "Error in XML structure (AllowedMixCascades node): Skip loading of allowed mixcascades.");
            b = false;
        }
        else {
            final NodeList elementsByTagName = element3.getElementsByTagName("MixCascade");
            final Vector<MixCascade> allowedMixCascades = new Vector<MixCascade>();
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element4 = (Element)elementsByTagName.item(i);
                try {
                    allowedMixCascades.addElement(new MixCascade(element4));
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "Error while loading one allowed MixCascade: Skipping this MixCascade (" + ex.toString() + ").");
                    b = false;
                }
            }
            this.setAllowedMixCascades(allowedMixCascades);
        }
        return b;
    }
    
    public void run() {
        int interrupted = 0;
        while (interrupted == 0 && !Thread.currentThread().isInterrupted()) {
            Hashtable<Object, MixCascade> mixCascades = (Hashtable<Object, MixCascade>)InfoServiceHolder.getInstance().getMixCascades();
            if (mixCascades == null) {
                mixCascades = new Hashtable<Object, MixCascade>();
            }
            synchronized (this.m_currentlyRunningMixCascades) {
                this.m_currentlyRunningMixCascades.clear();
                final Enumeration<MixCascade> elements = mixCascades.elements();
                while (elements.hasMoreElements()) {
                    final MixCascade mixCascade = elements.nextElement();
                    this.m_currentlyRunningMixCascades.put(mixCascade.getId(), mixCascade);
                }
            }
            this.updateUseableCascadesDatabase();
            synchronized (this.m_updateMixCascadesListThread) {
                interrupted = (Thread.interrupted() ? 1 : 0);
                if (interrupted != 0) {
                    continue;
                }
                try {
                    this.m_updateMixCascadesListThread.wait(600000L);
                }
                catch (Exception ex) {
                    interrupted = 1;
                }
            }
        }
        synchronized (this.m_currentlyRunningMixCascades) {
            this.m_currentlyRunningMixCascades.clear();
        }
        ForwardServerManager.getInstance().getAllowedCascadesDatabase().removeAllCascades();
    }
    
    private void updateUseableCascadesDatabase() {
        synchronized (this.m_currentlyRunningMixCascades) {
            synchronized (this.m_allowedMixCascades) {
                final boolean allowAllAvailableCascades = this.m_allowAllAvailableCascades;
                final Enumeration<MixCascade> elements = this.m_currentlyRunningMixCascades.elements();
                while (elements.hasMoreElements()) {
                    final MixCascade mixCascade = elements.nextElement();
                    if (allowAllAvailableCascades) {
                        ForwardServerManager.getInstance().getAllowedCascadesDatabase().addCascade(mixCascade);
                    }
                    else {
                        if (!this.m_allowedMixCascades.containsKey(mixCascade.getId())) {
                            continue;
                        }
                        ForwardServerManager.getInstance().getAllowedCascadesDatabase().addCascade(mixCascade);
                    }
                }
                final Enumeration<MixCascade> elements2 = ForwardServerManager.getInstance().getAllowedCascadesDatabase().getEntryList().elements();
                while (elements2.hasMoreElements()) {
                    final MixCascade mixCascade2 = elements2.nextElement();
                    if (this.m_currentlyRunningMixCascades.containsKey(mixCascade2.getId())) {
                        if (allowAllAvailableCascades || this.m_allowedMixCascades.containsKey(mixCascade2.getId())) {
                            continue;
                        }
                        ForwardServerManager.getInstance().getAllowedCascadesDatabase().removeCascade(mixCascade2.getId());
                    }
                    else {
                        ForwardServerManager.getInstance().getAllowedCascadesDatabase().removeCascade(mixCascade2.getId());
                    }
                }
            }
        }
    }
    
    private void startMixCascadesListUpdateThread() {
        synchronized (this) {
            if (this.m_updateMixCascadesListThread == null) {
                LogHolder.log(6, LogType.MISC, "JAPRoutingUseableMixCascades: startMixCascadesListUpdateThread: The mixcascade management thread of the forwarding server is started.");
                (this.m_updateMixCascadesListThread = new Thread(this)).setDaemon(true);
                this.m_updateMixCascadesListThread.start();
            }
            else {
                LogHolder.log(6, LogType.MISC, "JAPRoutingUseableMixCascades: startMixCascadesListUpdateThread: The mixcascade management thread of the forwarding server was already started.");
            }
        }
    }
    
    private void stopMixCascadesListUpdateThread() {
        LogHolder.log(6, LogType.MISC, "JAPRoutingUseableMixCascades: stopMixCascadesListUpdateThread: Shutdown the mixcascade management thread of the forwarding server...");
        synchronized (this) {
            if (this.m_updateMixCascadesListThread != null) {
                synchronized (this.m_updateMixCascadesListThread) {
                    this.m_updateMixCascadesListThread.interrupt();
                }
                try {
                    this.m_updateMixCascadesListThread.join();
                    LogHolder.log(6, LogType.MISC, "JAPRoutingUseableMixCascades: stopMixCascadesListUpdateThread: Mixcascade management thread of the forwarding server halted.");
                }
                catch (Exception ex) {}
                this.m_updateMixCascadesListThread = null;
            }
            else {
                LogHolder.log(6, LogType.MISC, "JAPRoutingUseableMixCascades: stopMixCascadesListUpdateThread: The mixcascade management thread of the forwarding server was not running.");
            }
        }
    }
}
