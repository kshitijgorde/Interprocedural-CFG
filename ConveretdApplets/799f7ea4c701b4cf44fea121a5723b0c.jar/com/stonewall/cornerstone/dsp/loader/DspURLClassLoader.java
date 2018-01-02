// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

import java.io.InputStream;
import org.jdom.input.SAXBuilder;
import java.util.HashMap;
import java.net.URL;
import org.xmodel.log.Log;
import org.jdom.Document;
import java.util.Map;
import java.net.URLClassLoader;

public class DspURLClassLoader extends URLClassLoader
{
    private Map<String, Document> documents;
    static final Log log;
    
    static {
        log = Log.getLog(DspURLClassLoader.class);
    }
    
    protected DspURLClassLoader() {
        super(new URL[0], DspURLClassLoader.class.getClassLoader());
    }
    
    protected void init(final URL url) throws Exception {
        this.addURL(url);
        this.documents = new HashMap<String, Document>();
    }
    
    protected Document getDocument(final String path) throws Exception {
        Document d = this.documents.get(path);
        if (d != null) {
            return d;
        }
        final InputStream istr = this.getResourceAsStream(path);
        final SAXBuilder sax = new SAXBuilder();
        d = sax.build(istr);
        this.documents.put(path, d);
        return d;
    }
}
