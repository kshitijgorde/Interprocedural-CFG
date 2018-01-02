// 
// Decompiled by Procyon v0.5.30
// 

package jap.forward;

import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.JAPMessages;

public class JAPRoutingConnectionClass
{
    private int m_connectionClassIdentifier;
    private String m_connectionClassName;
    private int m_maximumBandwidth;
    private int m_relativeBandwidth;
    
    public JAPRoutingConnectionClass(final int connectionClassIdentifier, final String connectionClassName, final int maximumBandwidth, final int relativeBandwidth) {
        this.m_connectionClassIdentifier = connectionClassIdentifier;
        this.m_connectionClassName = connectionClassName;
        this.m_maximumBandwidth = maximumBandwidth;
        this.setRelativeBandwidth(relativeBandwidth);
    }
    
    public int getIdentifier() {
        return this.m_connectionClassIdentifier;
    }
    
    public int getMaximumBandwidth() {
        return this.m_maximumBandwidth;
    }
    
    public void setMaximumBandwidth(final int maximumBandwidth) {
        if (this.m_connectionClassIdentifier == 8) {
            synchronized (this) {
                this.m_maximumBandwidth = maximumBandwidth;
                this.setRelativeBandwidth(this.getRelativeBandwidth());
            }
        }
    }
    
    public int getCurrentBandwidth() {
        int n = 0;
        synchronized (this) {
            n = this.m_maximumBandwidth * this.m_relativeBandwidth / 100;
        }
        return n;
    }
    
    public int getMaxSimultaneousConnections() {
        return this.getCurrentBandwidth() / 4000;
    }
    
    public int getRelativeBandwidth() {
        return this.m_relativeBandwidth;
    }
    
    public void setRelativeBandwidth(final int relativeBandwidth) {
        synchronized (this) {
            if (relativeBandwidth > this.getMinimumRelativeBandwidth()) {
                this.m_relativeBandwidth = relativeBandwidth;
            }
            else {
                this.m_relativeBandwidth = this.getMinimumRelativeBandwidth();
            }
        }
    }
    
    public int getMinimumRelativeBandwidth() {
        final int maximumBandwidth = this.m_maximumBandwidth;
        return (400000 + (maximumBandwidth - 1)) / maximumBandwidth;
    }
    
    public String toString() {
        return JAPMessages.getString(this.m_connectionClassName);
    }
    
    public synchronized Element getSettingsAsXml(final Document document) {
        final Element element = document.createElement("ConnectionClass");
        final Element element2 = document.createElement("ClassIdentifier");
        final Element element3 = document.createElement("MaximumBandwidth");
        final Element element4 = document.createElement("RelativeBandwidth");
        XMLUtil.setValue(element2, this.getIdentifier());
        XMLUtil.setValue(element3, this.getMaximumBandwidth());
        XMLUtil.setValue(element4, this.getRelativeBandwidth());
        element.appendChild(element2);
        element.appendChild(element3);
        element.appendChild(element4);
        return element;
    }
    
    public boolean loadSettingsFromXml(final Element element) {
        boolean b = true;
        synchronized (this) {
            try {
                if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ClassIdentifier"), this.m_connectionClassIdentifier + 1) != this.m_connectionClassIdentifier) {
                    throw new Exception("JAPRoutingConnectionClass: loadSettingsFromXml: The class identifer doesn't match to this class (class: " + Integer.toString(this.m_connectionClassIdentifier) + ").");
                }
                if (this.m_connectionClassIdentifier == 8) {
                    final int value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "MaximumBandwidth"), -1);
                    if (value < 4000) {
                        throw new Exception("JAPRoutingConnectionClass: loadSettingsFromXml: Invalid maximum bandwidth value (class: " + Integer.toString(this.m_connectionClassIdentifier) + ").");
                    }
                    this.m_maximumBandwidth = value;
                    this.setRelativeBandwidth(50);
                }
                else if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "MaximumBandwidth"), this.m_maximumBandwidth + 1) != this.m_maximumBandwidth) {
                    throw new Exception("JAPRoutingConnectionClass: loadSettingsFromXml: The maximum bandwidth doesn't match to this class (class: " + Integer.toString(this.m_connectionClassIdentifier) + ").");
                }
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.NET, "JAPRoutingConnectionClass: loadSettingsFromXml: Loading the settings for this connection class failed: " + ex.toString());
                b = false;
            }
            if (b = true) {
                final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "RelativeBandwidth");
                if (element2 == null) {
                    LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClass: loadSettingsFromXml: Error in XML structure (RelativeBandwidth node for class " + Integer.toString(this.m_connectionClassIdentifier) + "): Using default value.");
                    b = false;
                }
                else {
                    final int value2 = XMLUtil.parseValue(element2, -1);
                    if (value2 < this.getMinimumRelativeBandwidth()) {
                        LogHolder.log(3, LogType.MISC, "JAPRoutingConnectionClass: loadSettingsFromXml: Invalid relative bandwidth value for class " + Integer.toString(this.m_connectionClassIdentifier) + ": Using default value.");
                        b = false;
                    }
                    else {
                        this.setRelativeBandwidth(value2);
                    }
                }
            }
        }
        return b;
    }
}
