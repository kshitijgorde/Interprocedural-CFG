// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.varia;

import org.apache.log4j.spi.LoggerRepository;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;

public class ReloadingPropertyConfigurator implements Configurator
{
    PropertyConfigurator delegate;
    
    public ReloadingPropertyConfigurator() {
        this.delegate = new PropertyConfigurator();
    }
    
    public void doConfigure(final URL url, final LoggerRepository repository) {
    }
}
