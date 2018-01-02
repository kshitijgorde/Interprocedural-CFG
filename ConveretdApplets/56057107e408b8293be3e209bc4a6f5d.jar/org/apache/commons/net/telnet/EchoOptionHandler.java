// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.telnet;

public class EchoOptionHandler extends TelnetOptionHandler
{
    public EchoOptionHandler(final boolean initlocal, final boolean initremote, final boolean acceptlocal, final boolean acceptremote) {
        super(TelnetOption.ECHO, initlocal, initremote, acceptlocal, acceptremote);
    }
    
    public EchoOptionHandler() {
        super(TelnetOption.ECHO, false, false, false, false);
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
