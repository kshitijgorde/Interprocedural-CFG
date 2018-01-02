// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.loader;

public class LoaderException extends Exception
{
    static final long serialVersionUID = 0L;
    
    LoaderException(final String msg) {
        super(msg);
    }
    
    LoaderException(final Exception e) {
        super(e);
    }
}
