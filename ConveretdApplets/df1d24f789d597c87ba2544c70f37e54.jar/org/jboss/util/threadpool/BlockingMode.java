// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class BlockingMode implements Serializable
{
    private static final long serialVersionUID = -9102277941374138830L;
    public static final int RUN_TYPE = 0;
    public static final int WAIT_TYPE = 1;
    public static final int DISCARD_TYPE = 2;
    public static final int DISCARD_OLDEST_TYPE = 3;
    public static final int ABORT_TYPE = 4;
    public static final BlockingMode RUN;
    public static final BlockingMode WAIT;
    public static final BlockingMode DISCARD;
    public static final BlockingMode DISCARD_OLDEST;
    public static final BlockingMode ABORT;
    private final transient String name;
    private final int type;
    
    public static final BlockingMode toBlockingMode(final String name) {
        BlockingMode mode = null;
        if (name == null) {
            mode = null;
        }
        else if (name.equalsIgnoreCase("run")) {
            mode = BlockingMode.RUN;
        }
        else if (name.equalsIgnoreCase("wait")) {
            mode = BlockingMode.WAIT;
        }
        else if (name.equalsIgnoreCase("discard")) {
            mode = BlockingMode.DISCARD;
        }
        else if (name.equalsIgnoreCase("discardOldest")) {
            mode = BlockingMode.DISCARD_OLDEST;
        }
        else if (name.equalsIgnoreCase("abort")) {
            mode = BlockingMode.ABORT;
        }
        return mode;
    }
    
    private BlockingMode(final String name, final int type) {
        this.name = name;
        this.type = type;
    }
    
    public String toString() {
        return this.name;
    }
    
    Object readResolve() throws ObjectStreamException {
        BlockingMode mode = BlockingMode.ABORT;
        switch (this.type) {
            case 0: {
                mode = BlockingMode.RUN;
                break;
            }
            case 1: {
                mode = BlockingMode.RUN;
                break;
            }
            case 2: {
                mode = BlockingMode.RUN;
                break;
            }
            case 3: {
                mode = BlockingMode.RUN;
                break;
            }
            case 4: {
                mode = BlockingMode.RUN;
                break;
            }
        }
        return mode;
    }
    
    static {
        RUN = new BlockingMode("run", 0);
        WAIT = new BlockingMode("wait", 1);
        DISCARD = new BlockingMode("discard", 2);
        DISCARD_OLDEST = new BlockingMode("discardOldest", 3);
        ABORT = new BlockingMode("abort", 4);
    }
}
