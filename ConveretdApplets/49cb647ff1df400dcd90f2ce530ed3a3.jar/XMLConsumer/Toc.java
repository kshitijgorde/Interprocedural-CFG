// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.util.Enumeration;
import java.net.MalformedURLException;
import hhapplet.URLFileHandler;
import BsscXML.IBsscXMLElementReader;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

public class Toc extends Consumer
{
    private URL m_rootURL;
    private Hashtable m_hPrjPath;
    private URL m_projURL;
    private Vector m_vRootTocPaths;
    
    public Toc(final URL url, final URL projURL) {
        super(url);
        this.m_rootURL = null;
        this.m_hPrjPath = null;
        this.m_projURL = null;
        this.m_vRootTocPaths = null;
        this.m_projURL = projURL;
        this.m_hPrjPath = new Hashtable();
    }
    
    public Vector getRootTocPaths() {
        return this.m_vRootTocPaths;
    }
    
    public void setRootTocPaths(final Vector vRootTocPaths) {
        this.m_vRootTocPaths = vRootTocPaths;
    }
    
    public Vector getProjTocPaths(final String s) {
        return this.m_hPrjPath.get(s);
    }
    
    public void consume(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader.getName().equals("toc")) {
            final String attribute = bsscXMLElementReader.getAttribute("root");
            try {
                this.m_rootURL = URLFileHandler.makeURL(this.getURL(), attribute, null);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            int n = 0;
            while (true) {
                final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
                if (child == null) {
                    break;
                }
                if (!child.getName().equals("project")) {
                    continue;
                }
                String s = child.getAttribute("url");
                if (s != null && s.length() != 0 && s.lastIndexOf("/") != s.length() - 1) {
                    s += "/";
                }
                final String attribute2 = child.getAttribute("path");
                if (s == null || attribute2 == null) {
                    continue;
                }
                if (this.m_hPrjPath.containsKey(s)) {
                    final Vector<String> vector = this.m_hPrjPath.get(s);
                    boolean b = false;
                    final Enumeration<String> elements = vector.elements();
                    while (elements.hasMoreElements()) {
                        if (elements.nextElement().equals(attribute2)) {
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        continue;
                    }
                    vector.addElement(attribute2);
                }
                else {
                    final Vector<String> vector2 = new Vector<String>();
                    vector2.addElement(attribute2);
                    this.m_hPrjPath.put(s, vector2);
                }
            }
        }
    }
    
    public URL getProjURL() {
        return this.m_projURL;
    }
    
    public URL getRootTocURL() {
        return this.m_rootURL;
    }
    
    public Enumeration getRemoteProjPaths() {
        return this.m_hPrjPath.keys();
    }
}
