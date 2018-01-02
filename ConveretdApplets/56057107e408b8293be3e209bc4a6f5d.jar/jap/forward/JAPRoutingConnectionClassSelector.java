// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import anon.util.XMLUtil;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Enumeration;
import java.util.Vector;
import jap.JAPModel;
import java.util.Hashtable;
import java.util.Observable;

public class JAPRoutingConnectionClassSelector extends Observable
{
    public static final int CONNECTION_CLASS_ISDN64 = 0;
    public static final int CONNECTION_CLASS_ISDN128 = 1;
    public static final int CONNECTION_CLASS_DSL128 = 2;
    public static final int CONNECTION_CLASS_DSL192 = 3;
    public static final int CONNECTION_CLASS_DSL256 = 4;
    public static final int CONNECTION_CLASS_DSL384 = 5;
    public static final int CONNECTION_CLASS_DSL512 = 6;
    public static final int CONNECTION_CLASS_1MBIT = 7;
    public static final int CONNECTION_CLASS_USER = 8;
    private Hashtable m_connectionClasses;
    private int m_currentConnectionClass;
    
    public JAPRoutingConnectionClassSelector() {
        (this.m_connectionClasses = new Hashtable()).put(new Integer(0), new JAPRoutingConnectionClass(0, "routingConnectionClassIsdn64", 8000, 50));
        this.m_connectionClasses.put(new Integer(1), new JAPRoutingConnectionClass(1, "routingConnectionClassIsdn128", 16000, 50));
        this.m_connectionClasses.put(new Integer(2), new JAPRoutingConnectionClass(2, "routingConnectionClassDsl128", 16000, 50));
        this.m_connectionClasses.put(new Integer(3), new JAPRoutingConnectionClass(3, "routingConnectionClassDsl192", 24000, 50));
        this.m_connectionClasses.put(new Integer(4), new JAPRoutingConnectionClass(4, "routingConnectionClassDsl256", 32000, 50));
        this.m_connectionClasses.put(new Integer(5), new JAPRoutingConnectionClass(5, "routingConnectionClassDsl384", 48000, 50));
        this.m_connectionClasses.put(new Integer(6), new JAPRoutingConnectionClass(6, "routingConnectionClassDsl512", 64000, 50));
        this.m_connectionClasses.put(new Integer(7), new JAPRoutingConnectionClass(7, "routingConnectionClass1Mbit", 125000, 50));
        this.m_connectionClasses.put(new Integer(8), new JAPRoutingConnectionClass(8, "routingConnectionClassUser", 16000, 50));
        this.m_currentConnectionClass = 2;
    }
    
    public JAPRoutingConnectionClass getCurrentConnectionClass() {
        JAPRoutingConnectionClass japRoutingConnectionClass = null;
        synchronized (this.m_connectionClasses) {
            japRoutingConnectionClass = this.m_connectionClasses.get(new Integer(this.m_currentConnectionClass));
        }
        return japRoutingConnectionClass;
    }
    
    public void setCurrentConnectionClass(final int currentConnectionClass) {
        synchronized (this.m_connectionClasses) {
            final JAPRoutingConnectionClass japRoutingConnectionClass = this.m_connectionClasses.get(new Integer(currentConnectionClass));
            if (japRoutingConnectionClass != null) {
                int n = 0;
                int n2 = 0;
                if (this.m_currentConnectionClass != currentConnectionClass) {
                    n = 1;
                }
                this.m_currentConnectionClass = currentConnectionClass;
                if (JAPModel.getInstance().getRoutingSettings().getBandwidth() != japRoutingConnectionClass.getCurrentBandwidth() || JAPModel.getInstance().getRoutingSettings().getAllowedConnections() != japRoutingConnectionClass.getMaxSimultaneousConnections()) {
                    n2 = 1;
                }
                JAPModel.getInstance().getRoutingSettings().setBandwidth(japRoutingConnectionClass.getCurrentBandwidth());
                JAPModel.getInstance().getRoutingSettings().setAllowedConnections(japRoutingConnectionClass.getMaxSimultaneousConnections());
                if (n == 1) {
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(6));
                }
                if (n2 == 1) {
                    this.setChanged();
                    this.notifyObservers(new JAPRoutingMessage(7));
                }
            }
        }
    }
    
    public Vector getConnectionClasses() {
        final Vector<Object> vector = new Vector<Object>();
        synchronized (this.m_connectionClasses) {
            final Enumeration<Object> elements = this.m_connectionClasses.elements();
            while (elements.hasMoreElements()) {
                vector.addElement(elements.nextElement());
            }
        }
        return vector;
    }
    
    public Element getSettingsAsXml(final Document document) {
        final Element element = document.createElement("ConnectionClassSettings");
        final Element element2 = document.createElement("ConnectionClasses");
        final Element element3 = document.createElement("CurrentConnectionClass");
        synchronized (this.m_connectionClasses) {
            final Enumeration<JAPRoutingConnectionClass> elements = this.getConnectionClasses().elements();
            while (elements.hasMoreElements()) {
                element2.appendChild(elements.nextElement().getSettingsAsXml(document));
            }
            XMLUtil.setValue(element3, this.m_currentConnectionClass);
        }
        element.appendChild(element2);
        element.appendChild(element3);
        return element;
    }
    
    public boolean loadSettingsFromXml(final Element element) {
        boolean loadSettingsFromXml = true;
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "ConnectionClasses");
        if (element2 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: Error in XML structure (ConnectionClasses node): Using default connection classes.");
            loadSettingsFromXml = false;
        }
        else {
            final NodeList elementsByTagName = element2.getElementsByTagName("ConnectionClass");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element3 = (Element)elementsByTagName.item(i);
                final Element element4 = (Element)XMLUtil.getFirstChildByName(element3, "ClassIdentifier");
                if (element4 == null) {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: Error in XML structure (ClassIdentifier node): Skipping this connection class.");
                    loadSettingsFromXml = false;
                }
                else {
                    try {
                        final int int1 = Integer.parseInt(XMLUtil.parseValue(element4, "NOT_A_NUMBER"));
                        JAPRoutingConnectionClass japRoutingConnectionClass = null;
                        synchronized (this.m_connectionClasses) {
                            japRoutingConnectionClass = this.m_connectionClasses.get(new Integer(int1));
                        }
                        if (japRoutingConnectionClass != null) {
                            loadSettingsFromXml = japRoutingConnectionClass.loadSettingsFromXml(element3);
                        }
                        else {
                            LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: The connection class " + Integer.toString(int1) + " is not known in the system. Skipping the entry.");
                            loadSettingsFromXml = false;
                        }
                    }
                    catch (Exception ex) {
                        LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: Error while loading settings for a connection class. Skipping this class. (" + ex.toString() + ")");
                        loadSettingsFromXml = false;
                    }
                }
            }
        }
        final Element element5 = (Element)XMLUtil.getFirstChildByName(element, "CurrentConnectionClass");
        if (element5 == null) {
            LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: Error in XML structure (CurrentConnectionClass node): Using default value.");
            loadSettingsFromXml = false;
        }
        else {
            try {
                final int int2 = Integer.parseInt(XMLUtil.parseValue(element5, "NOT_A_NUMBER"));
                synchronized (this.m_connectionClasses) {
                    if (this.m_connectionClasses.get(new Integer(int2)) != null) {
                        this.setCurrentConnectionClass(int2);
                    }
                    else {
                        this.setCurrentConnectionClass(this.m_currentConnectionClass);
                        LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: The specified current connection class doesn't exist: Using default value.");
                        loadSettingsFromXml = false;
                    }
                }
            }
            catch (Exception ex2) {
                this.setCurrentConnectionClass(this.m_currentConnectionClass);
                LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClassSelector: loadSettingsFromXml: Invalid value of the current connection class setting: Using default value. (" + ex2.toString() + ")");
                loadSettingsFromXml = false;
            }
        }
        return loadSettingsFromXml;
    }
}
