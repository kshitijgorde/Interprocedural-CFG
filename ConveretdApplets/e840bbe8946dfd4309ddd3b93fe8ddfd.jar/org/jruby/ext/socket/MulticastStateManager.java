// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.net.MulticastSocket;

public class MulticastStateManager
{
    private MulticastSocket multicastSocket;
    private ArrayList membershipGroups;
    public static final int IP_ADD_MEMBERSHIP = 12;
    
    public MulticastStateManager() {
        this.membershipGroups = new ArrayList();
    }
    
    public void addMembership(final byte[] ipaddr_buf) throws IOException {
        String ipString = "";
        if (ipaddr_buf.length >= 4) {
            ipString += String.valueOf(ipaddr_buf[0] & 0xFF);
            ipString += ".";
            ipString += String.valueOf(ipaddr_buf[1] & 0xFF);
            ipString += ".";
            ipString += String.valueOf(ipaddr_buf[2] & 0xFF);
            ipString += ".";
            ipString += String.valueOf(ipaddr_buf[3] & 0xFF);
        }
        this.membershipGroups.add(ipString);
        this.updateMemberships();
    }
    
    public void rebindToPort(final int port) throws IOException {
        if (this.multicastSocket != null) {
            this.multicastSocket.close();
        }
        this.multicastSocket = new MulticastSocket(port);
        this.updateMemberships();
    }
    
    public MulticastSocket getMulticastSocket() {
        return this.multicastSocket;
    }
    
    private void updateMemberships() throws IOException {
        if (this.multicastSocket == null) {
            return;
        }
        for (int i = 0; i < this.membershipGroups.size(); ++i) {
            final String ipString = this.membershipGroups.get(i);
            final InetAddress group = InetAddress.getByName(ipString);
            this.multicastSocket.joinGroup(group);
        }
    }
}
