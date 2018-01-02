// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

import org.slf4j.Logger;
import java.io.Serializable;

abstract class NamedLoggerBase implements Serializable, Logger
{
    protected String a;
    
    public String a() {
        return this.a;
    }
}
