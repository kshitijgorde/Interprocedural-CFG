// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.discover;

import java.util.ArrayList;
import java.util.List;
import com.stonewall.cornerstone.entity.util.IpAddr;
import java.net.InetAddress;
import org.xmodel.log.Log;

public class Ping
{
    static final Log log;
    
    static {
        log = Log.getLog(Ping.class);
    }
    
    public static void main(final String[] args) {
        String address = "";
        address = "10.1.3.32";
        System.out.print(String.valueOf(address) + " - ");
        try {
            final InetAddress host = InetAddress.getByName(address);
            if (host.isReachable(1000)) {
                System.out.println("Alive.");
            }
            else {
                System.out.println("Timed out.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        IpAddr subnet = null;
        try {
            subnet = new IpAddr("10.1.3.0", "255.255.255.192");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        final int[] x = subnet.getFirstHost();
        final int[] y = subnet.getLastHost();
        while (x[0] <= y[0]) {
            while (x[1] <= y[1]) {
                while (x[2] <= y[2]) {
                    while (x[3] <= y[3]) {
                        address = String.valueOf(x[0]) + "." + x[1] + "." + x[2] + "." + x[3];
                        System.out.print(String.valueOf(address) + " - ");
                        try {
                            final InetAddress host2 = InetAddress.getByName(address);
                            if (host2.isReachable(3000)) {
                                System.out.println("Alive.");
                            }
                            else {
                                System.out.println("Timed out.");
                            }
                        }
                        catch (Exception ex3) {
                            ex3.printStackTrace();
                        }
                        final int[] array = x;
                        final int n = 3;
                        ++array[n];
                    }
                    final int[] array2 = x;
                    final int n2 = 2;
                    ++array2[n2];
                }
                final int[] array3 = x;
                final int n3 = 1;
                ++array3[n3];
            }
            final int[] array4 = x;
            final int n4 = 0;
            ++array4[n4];
        }
        System.out.println("Done.");
    }
    
    public static List<String> getAliveHosts(final String subnet, final String mask, final int timeout) {
        try {
            return getAliveHosts(new IpAddr(subnet, mask), timeout);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static List<String> getAliveHosts(final String start, final String end, final String mask, final int timeout) {
        try {
            return getAliveHosts(new IpAddr(start, mask), new IpAddr(end, mask), timeout);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static List<String> getAliveHosts(final String subnet, final int timeout) {
        try {
            return getAliveHosts(new IpAddr(subnet), timeout);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static List<String> getAliveHosts(final IpAddr subnet, final int timeout) {
        if (!subnet.isNetwork()) {
            return null;
        }
        final int[] x = subnet.getFirstHost();
        final int[] y = subnet.getLastHost();
        return getAliveHosts(x, y, timeout);
    }
    
    public static List<String> getAliveHosts(final IpAddr start, final IpAddr end, final int timeout) {
        final int[] x = start.getAddressOctets();
        final int[] y = end.getAddressOctets();
        return getAliveHosts(x, y, timeout);
    }
    
    private static List<String> getAliveHosts(final int[] x, final int[] y, int timeout) {
        if (timeout <= 0) {
            timeout = 3000;
        }
        else if (timeout > 30000) {
            timeout = 30000;
        }
        String address = "";
        final ArrayList<String> hosts = new ArrayList<String>();
        while (x[0] <= y[0]) {
            while (x[1] <= y[1]) {
                while (x[2] <= y[2]) {
                    while (x[3] <= y[3]) {
                        address = String.valueOf(x[0]) + "." + x[1] + "." + x[2] + "." + x[3];
                        try {
                            final InetAddress host = InetAddress.getByName(address);
                            if (host.isReachable(timeout)) {
                                hosts.add(address);
                            }
                        }
                        catch (Exception ex) {
                            Ping.log.warn("Error communicating with host - " + address, ex);
                        }
                        final int n = 3;
                        ++x[n];
                    }
                    final int n2 = 2;
                    ++x[n2];
                }
                final int n3 = 1;
                ++x[n3];
            }
            final int n4 = 0;
            ++x[n4];
        }
        return hosts;
    }
}
