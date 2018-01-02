// 
// Decompiled by Procyon v0.5.30
// 

package BsscXML;

public class BsscXMLDocument implements IBsscXMLDocumentBuilder, IBsscXMLDocumentReader
{
    BsscXMLElement m_root;
    BsscXMLConsumerChain m_ConsumerChain;
    
    public BsscXMLDocument() {
        this.m_root = null;
        this.m_ConsumerChain = null;
    }
    
    public void setRoot(final IBsscXMLElementBuilder bsscXMLElementBuilder) throws BsscXMLException {
        if (this.m_root != null) {
            throw new BsscXMLException("Only one Root element is allowed!");
        }
        if (bsscXMLElementBuilder instanceof BsscXMLElement) {
            this.m_root = (BsscXMLElement)bsscXMLElementBuilder;
            return;
        }
        throw new BsscXMLException("Type Mismatch!");
    }
    
    public IBsscXMLElementReader getRoot() {
        return this.m_root;
    }
    
    public void setReady(final boolean b) {
        if (this.m_ConsumerChain != null) {
            this.m_ConsumerChain.consume(this.m_root);
        }
    }
    
    public void removeConsumer(final IBsscXMLConsumer bsscXMLConsumer) {
        if (this.m_ConsumerChain != null) {
            this.m_ConsumerChain.remove(bsscXMLConsumer);
        }
    }
    
    public void addConsumer(final IBsscXMLConsumer bsscXMLConsumer) {
        if (this.m_ConsumerChain == null) {
            this.m_ConsumerChain = new BsscXMLConsumerChain();
        }
        this.m_ConsumerChain.add(bsscXMLConsumer);
    }
}
