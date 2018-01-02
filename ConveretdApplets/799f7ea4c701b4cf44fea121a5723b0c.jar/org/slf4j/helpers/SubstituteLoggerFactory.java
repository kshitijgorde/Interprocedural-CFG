// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;

public class SubstituteLoggerFactory implements ILoggerFactory
{
    final List loggerNameList;
    
    public SubstituteLoggerFactory() {
        this.loggerNameList = new ArrayList();
    }
    
    public Logger getLogger(final String name) {
        this.loggerNameList.add(name);
        return NOPLogger.NOP_LOGGER;
    }
    
    public List getLoggerNameList() {
        return this.loggerNameList;
    }
}
