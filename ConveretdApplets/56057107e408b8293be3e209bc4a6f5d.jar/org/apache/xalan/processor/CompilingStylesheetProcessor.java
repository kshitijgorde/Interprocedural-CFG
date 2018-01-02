// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.TemplatesHandler;

public class CompilingStylesheetProcessor extends TransformerFactoryImpl
{
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        return new CompilingStylesheetHandler(this);
    }
}
