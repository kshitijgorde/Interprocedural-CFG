// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

import javax.xml.transform.SourceLocator;

public class DTMConfigurationException extends DTMException
{
    static final long serialVersionUID = -4607874078818418046L;
    
    public DTMConfigurationException() {
        super("Configuration Error");
    }
    
    public DTMConfigurationException(final String msg) {
        super(msg);
    }
    
    public DTMConfigurationException(final Throwable e) {
        super(e);
    }
    
    public DTMConfigurationException(final String msg, final Throwable e) {
        super(msg, e);
    }
    
    public DTMConfigurationException(final String message, final SourceLocator locator) {
        super(message, locator);
    }
    
    public DTMConfigurationException(final String message, final SourceLocator locator, final Throwable e) {
        super(message, locator, e);
    }
}
