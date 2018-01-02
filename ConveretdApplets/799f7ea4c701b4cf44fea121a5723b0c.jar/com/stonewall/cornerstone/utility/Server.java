// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Collections;
import java.util.List;
import java.net.Inet4Address;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.net.NetworkInterface;
import java.net.InetAddress;
import org.xmodel.log.Log;

public class Server
{
    static final Log log;
    
    static {
        log = Log.getLog(Server.class);
    }
    
    public Time currentTime() {
        return new Time();
    }
    
    public String getAddress(final IntRole role) {
        String result = null;
        try {
            final InetAddress address = this.getInetAddress(role);
            if (address != null) {
                result = address.getHostAddress();
            }
        }
        catch (Exception e) {
            Server.log.error(role, e);
        }
        return result;
    }
    
    public InetAddress getInetAddress(final IntRole role) throws Exception {
        InetAddress result = null;
        final NetworkInterface intf = NetworkInterface.getByName(this.identifier(role));
        if (intf != null) {
            result = this.getIpv4Address(intf);
        }
        return result;
    }
    
    public void setAddress(final IntRole role, final IpAddr address) throws Exception {
        String cmd = null;
        switch (this.platform()) {
            case Linux: {
                cmd = "/sbin/ifconfig $name $addr netmask $mask";
                break;
            }
            case Windows: {
                cmd = "netsh interface ip set address name=$name static $addr $mask";
                break;
            }
            case Unsupported: {
                throw new IllegalArgumentException(role.toString());
            }
        }
        cmd = cmd.replace("$name", this.identifier(role));
        cmd = cmd.replace("$addr", address.getAddressString());
        cmd = cmd.replace("$mask", address.getNetmaskString());
        this.execute(cmd);
        Server.log.info("(" + cmd + ") - succeeded.");
    }
    
    public Platform platform() {
        Platform result = Platform.Unsupported;
        final String name = System.getProperty("os.name").toUpperCase();
        switch (name.charAt(0)) {
            case 'L': {
                result = Platform.Linux;
                break;
            }
            case 'W': {
                result = Platform.Windows;
                break;
            }
        }
        return result;
    }
    
    public String identifier(final IntRole r) {
        return this.ethernetIdentifiers().get(r);
    }
    
    private void execute(final String cmd) throws Exception {
        final Process child = Runtime.getRuntime().exec(cmd);
        child.waitFor();
        if (child.exitValue() != 0) {
            throw new Exception("(" + cmd + ") - failed.");
        }
    }
    
    private Map<IntRole, String> ethernetIdentifiers() {
        int index = 0;
        String name = null;
        final Map<IntRole, String> result = new HashMap<IntRole, String>();
        IntRole[] values;
        for (int length = (values = IntRole.values()).length, i = 0; i < length; ++i) {
            final IntRole r = values[i];
            result.put(r, "not-installed");
        }
        for (final NetworkInterface intf : this.interfaces()) {
            if (this.isEthernet(intf) && this.notVirtual(intf) && this.hasAddress(intf)) {
                name = intf.getName();
                switch (index++) {
                    default: {
                        continue;
                    }
                    case 0: {
                        result.put(IntRole.management, name);
                        continue;
                    }
                    case 1: {
                        result.put(IntRole.communication, name);
                        continue;
                    }
                }
            }
        }
        return result;
    }
    
    private InetAddress getIpv4Address(final NetworkInterface intf) {
        InetAddress result = null;
        for (final InetAddress inet : Enumerations.list(intf.getInetAddresses())) {
            if (inet instanceof Inet4Address) {
                result = inet;
                break;
            }
        }
        return result;
    }
    
    private List<NetworkInterface> interfaces() {
        try {
            return Enumerations.list(NetworkInterface.getNetworkInterfaces());
        }
        catch (Exception e) {
            Server.log.error(e);
            return Collections.emptyList();
        }
    }
    
    private boolean hasAddress(final NetworkInterface intf) {
        return intf.getInetAddresses().hasMoreElements();
    }
    
    private boolean isEthernet(final NetworkInterface intf) {
        return intf.getName().startsWith("eth") || intf.getName().startsWith("wlan") || intf.getName().startsWith("en");
    }
    
    private boolean notVirtual(final NetworkInterface intf) {
        return !intf.getName().contains(":");
    }
    
    public enum IntRole
    {
        management("management", 0), 
        communication("communication", 1);
        
        private IntRole(final String s, final int n) {
        }
    }
    
    public enum Platform
    {
        Linux("Linux", 0), 
        Windows("Windows", 1), 
        Unsupported("Unsupported", 2);
        
        private Platform(final String s, final int n) {
        }
    }
}
