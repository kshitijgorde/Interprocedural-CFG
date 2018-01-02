// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.telnet;

public class SuppressGAOptionHandler extends TelnetOptionHandler
{
    public SuppressGAOptionHandler(final boolean initlocal, final boolean initremote, final boolean acceptlocal, final boolean acceptremote) {
        super(TelnetOption.SUPPRESS_GO_AHEAD, initlocal, initremote, acceptlocal, acceptremote);
    }
    
    public SuppressGAOptionHandler() {
        super(TelnetOption.SUPPRESS_GO_AHEAD, false, false, false, false);
    }
    
    public int[] answerSubnegotiation(final int[] suboptionData, final int suboptionLength) {
        return null;
    }
    
    public int[] startSubnegotiationLocal() {
        return null;
    }
    
    public int[] startSubnegotiationRemote() {
        return null;
    }
}
