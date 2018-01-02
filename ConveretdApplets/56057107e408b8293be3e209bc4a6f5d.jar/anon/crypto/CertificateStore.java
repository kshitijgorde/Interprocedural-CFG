// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.NodeList;
import anon.util.XMLUtil;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import anon.util.IXMLEncodable;
import java.util.Observable;

public class CertificateStore extends Observable implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "TrustedCertificates";
    private Hashtable m_trustedCertificates;
    private Hashtable m_lockTable;
    private int m_lockIdPointer;
    
    public static String getXmlSettingsRootNodeName() {
        return "TrustedCertificates";
    }
    
    public CertificateStore() {
        this.m_trustedCertificates = new Hashtable();
        this.m_lockTable = new Hashtable();
        this.m_lockIdPointer = 0;
    }
    
    public Vector getAllCertificates() {
        final Vector<CertificateInfoStructure> vector = new Vector<CertificateInfoStructure>();
        synchronized (this) {
            synchronized (this.m_trustedCertificates) {
                final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
                while (elements.hasMoreElements()) {
                    vector.addElement(elements.nextElement().getInfoStructure());
                }
            }
        }
        return vector;
    }
    
    public Vector getUnavailableCertificatesByType(final int n) {
        final Vector<CertificateInfoStructure> vector = new Vector<CertificateInfoStructure>();
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.getCertificateType() == n && !certificateContainer.isAvailable()) {
                    vector.addElement(certificateContainer.getInfoStructure());
                }
            }
        }
        return vector;
    }
    
    public CertificateInfoStructure getCertificateInfoStructure(final JAPCertificate japCertificate, final int n) {
        final CertificateContainer certificateContainer = this.m_trustedCertificates.get(this.getCertificateId(japCertificate, n));
        if (certificateContainer != null) {
            return certificateContainer.getInfoStructure();
        }
        return null;
    }
    
    public CertificateInfoStructure getCertificateInfoStructure(final JAPCertificate japCertificate) {
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.getCertificate().equals(japCertificate)) {
                    return certificateContainer.getInfoStructure();
                }
            }
        }
        return null;
    }
    
    public Vector getAvailableCertificatesByType(final int n) {
        final Vector<CertificateInfoStructure> vector = new Vector<CertificateInfoStructure>();
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.getCertificateType() == n && certificateContainer.isAvailable()) {
                    vector.addElement(certificateContainer.getInfoStructure());
                }
            }
        }
        return vector;
    }
    
    public int addCertificateWithVerification(final CertPath certPath, final int n, final boolean b) {
        int nextAvailableLockId = -1;
        if (n == 2 || n == 3) {
            boolean b2 = false;
            synchronized (this.m_trustedCertificates) {
                int n2 = 1;
                if (n == 3) {
                    n2 = 5;
                }
                if (!this.m_trustedCertificates.containsKey(this.getCertificateId(certPath.getFirstCertificate(), n))) {
                    final CertificateContainer certificateContainer = new CertificateContainer(certPath, n, true);
                    this.m_trustedCertificates.put(this.getCertificateId(certPath.getFirstCertificate(), n), certificateContainer);
                    final Enumeration elements = this.getAvailableCertificatesByType(n2).elements();
                    boolean verifier = false;
                    while (elements.hasMoreElements() && !verifier) {
                        final JAPCertificate certificate = elements.nextElement().getCertificate();
                        verifier = certPath.isVerifier(certificate);
                        if (verifier) {
                            certificateContainer.setParentCertificate(certificate);
                        }
                    }
                    b2 = true;
                }
                if (!b) {
                    nextAvailableLockId = this.getNextAvailableLockId();
                    this.m_lockTable.put(new Integer(nextAvailableLockId), this.getCertificateId(certPath.getFirstCertificate(), n));
                    this.m_trustedCertificates.get(this.getCertificateId(certPath.getFirstCertificate(), n)).getLockList().addElement(new Integer(nextAvailableLockId));
                }
                else {
                    this.m_trustedCertificates.get(this.getCertificateId(certPath.getFirstCertificate(), n)).enableOnlyHardRemovable();
                }
            }
            if (b2) {
                this.setChanged();
                this.notifyObservers(new Integer(n));
            }
        }
        return nextAvailableLockId;
    }
    
    public synchronized int addCertificateWithoutVerification(final JAPCertificate japCertificate, final int n, final boolean b, final boolean b2) {
        return this.addCertificateWithoutVerification(CertPath.getRootInstance(japCertificate), n, b, b2);
    }
    
    public synchronized int addCertificateWithoutVerification(final CertPath certPath, final int n, final boolean b, final boolean b2) {
        int nextAvailableLockId = -1;
        boolean b3 = false;
        if (certPath == null) {
            return nextAvailableLockId;
        }
        synchronized (this.m_trustedCertificates) {
            if (!this.m_trustedCertificates.containsKey(this.getCertificateId(certPath.getFirstCertificate(), n))) {
                this.m_trustedCertificates.put(this.getCertificateId(certPath.getFirstCertificate(), n), new CertificateContainer(certPath, n, false));
                if (n == 1 || n == 5) {
                    this.activateAllDependentCertificates(certPath.getFirstCertificate());
                }
                b3 = true;
            }
            if (!b) {
                nextAvailableLockId = this.getNextAvailableLockId();
                this.m_lockTable.put(new Integer(nextAvailableLockId), this.getCertificateId(certPath.getFirstCertificate(), n));
                this.m_trustedCertificates.get(this.getCertificateId(certPath.getFirstCertificate(), n)).getLockList().addElement(new Integer(nextAvailableLockId));
            }
            else {
                this.m_trustedCertificates.get(this.getCertificateId(certPath.getFirstCertificate(), n)).enableOnlyHardRemovable();
            }
            if (b2) {
                this.m_trustedCertificates.get(this.getCertificateId(certPath.getFirstCertificate(), n)).enableNotRemovable();
            }
        }
        if (b3) {
            this.setChanged();
            this.notifyObservers(new Integer(n));
        }
        return nextAvailableLockId;
    }
    
    public synchronized void removeCertificateLock(final int n) {
        synchronized (this.m_trustedCertificates) {
            CertificateContainer certificateContainer = null;
            try {
                certificateContainer = this.m_trustedCertificates.get(this.m_lockTable.get(new Integer(n)));
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.MISC, "Error while removing certificate lock. There is no lock with ID " + Integer.toString(n) + ".");
            }
            if (certificateContainer != null) {
                certificateContainer.getLockList().removeElement(new Integer(n));
                if (!certificateContainer.isOnlyHardRemovable() && certificateContainer.getLockList().size() == 0) {
                    this.removeCertificate(certificateContainer.getInfoStructure());
                }
            }
            this.m_lockTable.remove(new Integer(n));
        }
    }
    
    public synchronized void removeCertificate(final CertificateInfoStructure certificateInfoStructure) {
        CertificateContainer certificateContainer = null;
        synchronized (this.m_trustedCertificates) {
            certificateContainer = this.m_trustedCertificates.get(this.getCertificateId(certificateInfoStructure.getCertificate(), certificateInfoStructure.getCertificateType()));
            if (certificateContainer != null) {
                if (certificateContainer.getCertificateType() == 1 || certificateContainer.getCertificateType() == 5) {
                    this.deactivateAllDependentCertificates(certificateContainer.getCertificate());
                }
                final Enumeration elements = certificateContainer.getLockList().elements();
                while (elements.hasMoreElements()) {
                    this.m_lockTable.put(elements.nextElement(), "");
                }
                this.m_trustedCertificates.remove(this.getCertificateId(certificateInfoStructure.getCertificate(), certificateInfoStructure.getCertificateType()));
            }
        }
        if (certificateContainer != null) {
            this.setChanged();
            this.notifyObservers(new Integer(certificateContainer.getCertificateType()));
        }
    }
    
    public void removeAllCertificates() {
        synchronized (this.m_trustedCertificates) {
            final Enumeration<Object> keys = this.m_lockTable.keys();
            while (keys.hasMoreElements()) {
                this.m_lockTable.put(keys.nextElement(), "");
            }
            if (this.m_trustedCertificates.size() > 0) {
                final Enumeration<Object> keys2 = this.m_trustedCertificates.keys();
                while (keys2.hasMoreElements()) {
                    final Object nextElement = keys2.nextElement();
                    if (!((CertificateContainer)this.m_trustedCertificates.get(nextElement)).isNotRemovable()) {
                        this.m_trustedCertificates.remove(nextElement);
                    }
                }
                this.setChanged();
            }
        }
        this.notifyObservers();
    }
    
    public synchronized void reset() {
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                certificateContainer.setEnabled(certificateContainer.isEnabled());
            }
        }
    }
    
    public synchronized void setEnabled(final CertificateInfoStructure certificateInfoStructure, final boolean enabled) {
        synchronized (this.m_trustedCertificates) {
            final CertificateContainer certificateContainer = this.m_trustedCertificates.get(this.getCertificateId(certificateInfoStructure.getCertificate(), certificateInfoStructure.getCertificateType()));
            if (certificateContainer != null && certificateContainer.isEnabled() != enabled) {
                certificateContainer.setEnabled(enabled);
                if (certificateContainer.getCertificateType() == 1 || certificateContainer.getCertificateType() == 5) {
                    if (enabled) {
                        this.activateAllDependentCertificates(certificateContainer.getCertificate());
                    }
                    else {
                        this.deactivateAllDependentCertificates(certificateContainer.getCertificate());
                    }
                }
                this.setChanged();
            }
        }
        this.notifyObservers();
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("TrustedCertificates");
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.isOnlyHardRemovable() && !certificateContainer.isNotRemovable()) {
                    element.appendChild(certificateContainer.toXmlElement(document));
                }
            }
        }
        return element;
    }
    
    public void loadSettingsFromXml(final Element element) {
        synchronized (this.m_trustedCertificates) {
            this.removeAllCertificates();
            final NodeList elementsByTagName = element.getElementsByTagName("CertificateContainer");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element2 = (Element)elementsByTagName.item(i);
                try {
                    final CertificateContainer certificateContainer = new CertificateContainer(element2);
                    if (certificateContainer.getCertificateNeedsVerification()) {
                        this.addCertificateWithVerification(certificateContainer.getCertPath(), certificateContainer.getCertificateType(), true);
                    }
                    else {
                        this.addCertificateWithoutVerification(certificateContainer.getCertPath(), certificateContainer.getCertificateType(), true, certificateContainer.isNotRemovable());
                    }
                    this.setEnabled(certificateContainer.getInfoStructure(), certificateContainer.isEnabled());
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "Error while loading a CertificateContainer. Skipping this entry. Error: " + ex.toString() + " - Invalid container was: " + XMLUtil.toString(element2));
                }
            }
        }
    }
    
    private void activateAllDependentCertificates(final JAPCertificate parentCertificate) {
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.getCertificateNeedsVerification() && certificateContainer.getParentCertificate() == null && certificateContainer.getCertPath().isVerifier(parentCertificate)) {
                    certificateContainer.setParentCertificate(parentCertificate);
                    certificateContainer.setEnabled(true);
                }
            }
        }
    }
    
    private void deactivateAllDependentCertificates(final JAPCertificate japCertificate) {
        synchronized (this.m_trustedCertificates) {
            final Enumeration<CertificateContainer> elements = this.m_trustedCertificates.elements();
            while (elements.hasMoreElements()) {
                final CertificateContainer certificateContainer = elements.nextElement();
                if (certificateContainer.getCertificateNeedsVerification()) {
                    final JAPCertificate parentCertificate = certificateContainer.getParentCertificate();
                    if (parentCertificate == null || !parentCertificate.equals(japCertificate)) {
                        continue;
                    }
                    certificateContainer.setParentCertificate(null);
                    certificateContainer.setEnabled(false);
                }
            }
        }
    }
    
    private int getNextAvailableLockId() {
        while (this.m_lockTable.containsKey(new Integer(this.m_lockIdPointer)) || this.m_lockIdPointer == -1) {
            ++this.m_lockIdPointer;
        }
        return this.m_lockIdPointer;
    }
    
    private String getCertificateId(final JAPCertificate japCertificate, final int n) {
        return japCertificate.getId() + Integer.toString(n);
    }
}
