// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.helpers.FileWatchdog;

class PropertyWatchdog extends FileWatchdog
{
    PropertyWatchdog(final String s) {
        super(s);
    }
    
    public void doOnChange() {
        new PropertyConfigurator().doConfigure(super.filename, LogManager.getLoggerRepository());
    }
}
