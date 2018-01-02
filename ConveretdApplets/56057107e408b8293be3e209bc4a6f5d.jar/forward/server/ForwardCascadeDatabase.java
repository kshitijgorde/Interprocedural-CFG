// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

import logging.LogHolder;
import logging.LogType;
import java.util.Vector;
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.infoservice.MixCascade;
import java.util.Hashtable;

public class ForwardCascadeDatabase
{
    private Hashtable m_allowedCascades;
    
    public ForwardCascadeDatabase() {
        this.m_allowedCascades = new Hashtable();
    }
    
    public MixCascade getMixCascadeById(final String s) {
        return this.m_allowedCascades.get(s);
    }
    
    public Element toXmlNode(final Document document) {
        final Element element = document.createElement("AllowedCascades");
        synchronized (this.m_allowedCascades) {
            final Enumeration<MixCascade> elements = this.m_allowedCascades.elements();
            while (elements.hasMoreElements()) {
                element.appendChild(elements.nextElement().toXmlElement(document));
            }
        }
        return element;
    }
    
    public Vector getEntryList() {
        final Vector<Object> vector = new Vector<Object>();
        synchronized (this.m_allowedCascades) {
            final Enumeration<Object> elements = this.m_allowedCascades.elements();
            while (elements.hasMoreElements()) {
                vector.addElement(elements.nextElement());
            }
        }
        return vector;
    }
    
    public void addCascade(final MixCascade mixCascade) {
        synchronized (this.m_allowedCascades) {
            if (!this.m_allowedCascades.containsKey(mixCascade.getId())) {
                LogHolder.log(6, LogType.MISC, "ForwardCascadeDatabase: addCascade: The mixcascade " + mixCascade.getMixNames() + " was added to the list of useable cascades for the clients.");
            }
            this.m_allowedCascades.put(mixCascade.getId(), mixCascade);
        }
    }
    
    public void removeCascade(final String s) {
        synchronized (this.m_allowedCascades) {
            final MixCascade mixCascade = this.m_allowedCascades.get(s);
            if (mixCascade != null) {
                LogHolder.log(6, LogType.MISC, "ForwardCascadeDatabase: removeCascade: The mixcascade " + mixCascade.getMixNames() + " was removed from the list of useable cascades for the clients.");
            }
            this.m_allowedCascades.remove(s);
        }
    }
    
    public void removeAllCascades() {
        synchronized (this.m_allowedCascades) {
            LogHolder.log(6, LogType.MISC, "ForwardCascadeDatabase: removeAllCascades: All mixcascades were removed from the list of useable cascades for the clients.");
            this.m_allowedCascades.clear();
        }
    }
}
