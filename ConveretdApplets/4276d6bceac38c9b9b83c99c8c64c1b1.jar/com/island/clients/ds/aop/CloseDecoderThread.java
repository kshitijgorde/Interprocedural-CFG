// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop;

import java.io.InputStream;

public class CloseDecoderThread extends Thread
{
    protected DataDecoder decoder;
    
    public CloseDecoderThread(final DataDecoder decoder) {
        this.decoder = decoder;
    }
    
    public void run() {
        try {
            final InputStream istream = this.decoder.getIstream();
            if (istream != null) {
                istream.close();
            }
        }
        catch (Exception ex) {}
    }
}
