// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.general;

import java.io.Serializable;
import java.util.EventObject;

public class SeriesChangeEvent extends EventObject implements Serializable
{
    private static final long serialVersionUID = 1593866085210089052L;
    
    public SeriesChangeEvent(final Object source) {
        super(source);
    }
}
