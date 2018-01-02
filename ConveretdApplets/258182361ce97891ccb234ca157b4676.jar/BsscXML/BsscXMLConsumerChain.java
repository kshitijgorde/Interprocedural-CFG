// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

import java.util.Enumeration;
import java.util.Vector;

public class BsscXMLConsumerChain implements IBsscXMLConsumer
{
    private Vector m_vecConsumer;
    
    public BsscXMLConsumerChain() {
        this.m_vecConsumer = null;
        (this.m_vecConsumer = new Vector()).removeAllElements();
    }
    
    public boolean add(final IBsscXMLConsumer bsscXMLConsumer) {
        this.m_vecConsumer.addElement(bsscXMLConsumer);
        return true;
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        final Enumeration<IBsscXMLConsumer> elements = this.m_vecConsumer.elements();
        while (elements.hasMoreElements()) {
            final IBsscXMLConsumer nextElement = elements.nextElement();
            if (nextElement instanceof IBsscXMLConsumer) {
                nextElement.consume(bsscXMLElementReader);
            }
        }
    }
    
    public boolean remove(final IBsscXMLConsumer bsscXMLConsumer) {
        return this.m_vecConsumer.removeElement(bsscXMLConsumer);
    }
}
