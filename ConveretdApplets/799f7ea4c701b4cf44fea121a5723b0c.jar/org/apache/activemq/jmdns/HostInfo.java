// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.net.Inet6Address;
import java.net.Inet4Address;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.net.NetworkInterface;
import java.net.InetAddress;
import java.util.logging.Logger;

class HostInfo
{
    private static Logger logger;
    protected String name;
    protected InetAddress address;
    protected NetworkInterface interfaze;
    private int hostNameCount;
    
    public HostInfo(final InetAddress address, final String name) {
        this.address = address;
        this.name = name;
        if (address != null) {
            try {
                this.interfaze = NetworkInterface.getByInetAddress(address);
            }
            catch (Exception exception) {
                HostInfo.logger.log(Level.WARNING, "LocalHostInfo() exception ", exception);
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public InetAddress getAddress() {
        return this.address;
    }
    
    public NetworkInterface getInterface() {
        return this.interfaze;
    }
    
    synchronized String incrementHostName() {
        ++this.hostNameCount;
        final int plocal = this.name.indexOf(".local.");
        final int punder = this.name.lastIndexOf("-");
        return this.name = this.name.substring(0, (punder == -1) ? plocal : punder) + "-" + this.hostNameCount + ".local.";
    }
    
    boolean shouldIgnorePacket(final DatagramPacket packet) {
        boolean result = false;
        if (this.getAddress() != null) {
            final InetAddress from = packet.getAddress();
            if (from != null) {
                if (from.isLinkLocalAddress() && !this.getAddress().isLinkLocalAddress()) {
                    result = true;
                }
                if (from.isLoopbackAddress() && !this.getAddress().isLoopbackAddress()) {
                    result = true;
                }
            }
        }
        return result;
    }
    
    DNSRecord.Address getDNSAddressRecord(final DNSRecord.Address address) {
        return (28 == address.type) ? this.getDNS6AddressRecord() : this.getDNS4AddressRecord();
    }
    
    DNSRecord.Address getDNS4AddressRecord() {
        if (this.getAddress() != null && (this.getAddress() instanceof Inet4Address || (this.getAddress() instanceof Inet6Address && ((Inet6Address)this.getAddress()).isIPv4CompatibleAddress()))) {
            return new DNSRecord.Address(this.getName(), 1, 1, 3600, this.getAddress());
        }
        return null;
    }
    
    DNSRecord.Address getDNS6AddressRecord() {
        if (this.getAddress() != null && this.getAddress() instanceof Inet6Address) {
            return new DNSRecord.Address(this.getName(), 28, 1, 3600, this.getAddress());
        }
        return null;
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("local host info[");
        buf.append((this.getName() != null) ? this.getName() : "no name");
        buf.append(", ");
        buf.append((this.getInterface() != null) ? this.getInterface().getDisplayName() : "???");
        buf.append(":");
        buf.append((this.getAddress() != null) ? this.getAddress().getHostAddress() : "no address");
        buf.append("]");
        return buf.toString();
    }
    
    static {
        HostInfo.logger = Logger.getLogger(HostInfo.class.toString());
    }
}
