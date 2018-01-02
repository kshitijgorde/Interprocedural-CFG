// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import BsscXML.IBsscXMLElementReader;
import BsscXML.BsscXML;
import java.net.URL;
import BsscXML.IBsscXMLConsumer;

public abstract class Consumer implements IBsscXMLConsumer
{
    private URL m_url;
    
    public void process(final boolean b) {
        BsscXML.parseXML(this, this.m_url, b);
    }
    
    public void process() {
        this.process(true);
    }
    
    public Consumer(final URL url) {
        this.m_url = null;
        this.m_url = url;
    }
    
    public abstract void consume(final IBsscXMLElementReader p0);
    
    public URL getURL() {
        return this.m_url;
    }
}
