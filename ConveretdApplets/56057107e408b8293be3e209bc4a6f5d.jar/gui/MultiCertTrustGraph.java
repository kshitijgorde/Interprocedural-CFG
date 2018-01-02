// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.util.Vector;
import java.util.Date;
import java.util.Enumeration;
import anon.crypto.JAPCertificate;
import anon.crypto.CertPathInfo;
import java.util.Hashtable;

public class MultiCertTrustGraph
{
    Hashtable m_rootNodes;
    Hashtable m_opNodes;
    Hashtable m_endNodes;
    
    public MultiCertTrustGraph(final CertPathInfo[] array) {
        this.createGraph(array);
    }
    
    private void createGraph(final CertPathInfo[] array) {
        this.m_rootNodes = new Hashtable();
        this.m_opNodes = new Hashtable();
        this.m_endNodes = new Hashtable();
        for (int i = 0; i < array.length; ++i) {
            final JAPCertificate rootCertificate = array[i].getRootCertificate();
            final JAPCertificate secondCertificate = array[i].getSecondCertificate();
            final JAPCertificate firstCertificate = array[i].getFirstCertificate();
            final boolean verified = array[i].isVerified();
            if (rootCertificate != null) {
                this.m_rootNodes.put(rootCertificate, new Node(rootCertificate, verified));
            }
            if (secondCertificate != null) {
                this.m_opNodes.put(secondCertificate, new Node(secondCertificate, verified));
            }
            this.m_endNodes.put(firstCertificate, new Node(firstCertificate, verified));
        }
        for (int j = 0; j < array.length; ++j) {
            final JAPCertificate rootCertificate2 = array[j].getRootCertificate();
            final JAPCertificate secondCertificate2 = array[j].getSecondCertificate();
            final JAPCertificate firstCertificate2 = array[j].getFirstCertificate();
            if (secondCertificate2 != null) {
                final Node node = this.m_opNodes.get(secondCertificate2);
                if (rootCertificate2 != null) {
                    this.m_rootNodes.get(rootCertificate2).addChild(node);
                    this.m_opNodes.remove(secondCertificate2);
                }
                node.addChild(new Node(array[j].getFirstCertificate()));
                this.m_endNodes.remove(firstCertificate2);
            }
            else if (rootCertificate2 != null) {
                ((Node)this.m_rootNodes.get(rootCertificate2)).addChild(new Node(array[j].getFirstCertificate()));
                this.m_endNodes.remove(firstCertificate2);
            }
        }
    }
    
    public Enumeration getRootNodes() {
        return this.m_rootNodes.elements();
    }
    
    public Enumeration getOperatorNodes() {
        return this.m_opNodes.elements();
    }
    
    public Enumeration getEndNodes() {
        return this.m_endNodes.elements();
    }
    
    public int countTrustedRootNodes() {
        int n = 0;
        final Enumeration rootNodes = this.getRootNodes();
        final Date date = new Date();
        while (rootNodes.hasMoreElements()) {
            final Node node = rootNodes.nextElement();
            if (node.isTrusted() && node.getCertificate().getValidity().isValid(date) && node.hasChildNodes()) {
                final Enumeration childNodes = node.getChildNodes();
                while (childNodes.hasMoreElements()) {
                    if (childNodes.nextElement().getCertificate().getValidity().isValid(date)) {
                        ++n;
                        break;
                    }
                }
            }
        }
        return n;
    }
    
    public final class Node
    {
        private JAPCertificate m_cert;
        private Vector m_childNodes;
        private boolean m_trusted;
        
        public Node(final JAPCertificate cert, final boolean trusted) {
            this.m_cert = cert;
            this.m_trusted = trusted;
            this.m_childNodes = new Vector();
        }
        
        public Node(final MultiCertTrustGraph multiCertTrustGraph, final JAPCertificate japCertificate) {
            this(multiCertTrustGraph, japCertificate, false);
        }
        
        public void addChild(final Node node) {
            if (!this.m_childNodes.contains(node)) {
                this.m_childNodes.addElement(node);
                node.m_trusted = this.m_trusted;
            }
        }
        
        public JAPCertificate getCertificate() {
            return this.m_cert;
        }
        
        public boolean isTrusted() {
            return this.m_trusted;
        }
        
        public Enumeration getChildNodes() {
            return this.m_childNodes.elements();
        }
        
        public boolean hasChildNodes() {
            return this.m_childNodes.size() > 0;
        }
        
        public int getWidth() {
            if (this.m_childNodes.size() == 0) {
                return 1;
            }
            return this.m_childNodes.size();
        }
    }
}
