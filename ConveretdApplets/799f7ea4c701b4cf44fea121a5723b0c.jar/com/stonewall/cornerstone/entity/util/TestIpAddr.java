// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.util.Iterator;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestIpAddr
{
    static final String prompt = "IPTEST> ";
    
    public static void main(final String[] args) {
        final String menu = "\n ******************* \n *  IpAddr Tester  * \n ******************* \nEnter a test number to run: \n  1> Quick Test \n  2> Complete Test \n  3> Custom Test \n  q> Quit \n";
        System.out.println(menu);
        System.out.print("IPTEST> ");
        String input = "";
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = in.readLine();
        }
        catch (IOException ioEx) {
            System.err.println(ioEx.getMessage());
        }
        while (true) {
            input = input.trim();
            if (input.length() > 0) {
                if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                    break;
                }
                if (input.equals("1")) {
                    runQuickTest();
                }
                else if (input.equals("2")) {
                    runCompleteTest();
                }
                else if (input.equals("3")) {
                    runCustomTest();
                }
                else {
                    System.out.println("Invalid selection.");
                }
            }
            System.out.println(menu);
            System.out.print("IPTEST> ");
            try {
                input = in.readLine();
            }
            catch (IOException ioEx) {
                System.err.println(ioEx.getMessage());
            }
        }
        System.out.println("Good-bye!");
    }
    
    public static void runQuickTest() {
        System.out.println("Running Quick Test ... ... ... ");
        IpAddr ip = null;
        try {
            ip = new IpAddr("10.1.1.128/26");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (ip == null) {
            System.err.println("  Construct IpAddr         :  FAILED");
            return;
        }
        System.out.println("  Construct IpAddr         :  PASSED");
        if (ip.getNetmaskString().equals("255.255.255.192")) {
            System.out.println("  Properties - Mask        :  PASSED");
        }
        else {
            System.err.println("  Properties - Mask        :  FAILED");
        }
        if (ip.getNetmaskLength() == 26) {
            System.out.println("  Properties - Mask Length :  PASSED");
        }
        else {
            System.err.println("  Properties - Mask Length :  FAILED");
        }
        if (ip.getSubnet().getAddressString().equals("10.1.1.128")) {
            System.out.println("  Properties - Subnet      :  PASSED");
        }
        else {
            System.err.println("  Properties - Subnet      :  FAILED");
        }
        if (ip.getBroadcast().getAddressString().equals("10.1.1.191")) {
            System.out.println("  Properties - Broadcast   :  PASSED");
        }
        else {
            System.err.println("  Properties - Broadcast   :  FAILED");
        }
        if (ip.getAddressClass().equalsIgnoreCase("A")) {
            System.out.println("  Properties - Class       :  PASSED");
        }
        else {
            System.err.println("  Properties - Class       :  FAILED");
        }
        try {
            IpAddr testIpSubnets = null;
            testIpSubnets = new IpAddr("10.1.1.128/30");
            if (testIpSubnets.getAllSubnets().size() == 30) {
                System.out.println("  Get all IP Subnets       :  PASSED");
            }
            else {
                System.err.println("  Get all IP Subnets       :  FAILED");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            final IpAddr a = new IpAddr("10.1.10.0/28");
            final IpAddr b = new IpAddr("10.10.100.255/28");
            final int[] inc = { 0, 2, 10, 10 };
            final int[] msk = { 255, 255, 255, 240 };
            if (IpAddr.getNetworksInRange(a, b, inc, msk).size() == 200) {
                System.out.println("  Get Networks in Range    :  PASSED");
            }
            else {
                System.err.println("  Get Networks in Range    :  FAILED");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        IpAddr ip2 = null;
        IpAddr ip3 = null;
        IpAddr ip4 = null;
        IpAddr ip5 = null;
        try {
            ip2 = new IpAddr("10.1.1.128/26");
            ip3 = new IpAddr("10.1.1.192/26");
            ip4 = new IpAddr("10.1.1.128/24");
            ip5 = new IpAddr("10.1.1.128/24");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        if (ip2.equals((Object)ip3)) {
            System.err.println("  IP Comparison (Different IP, Same Mask) :  FAILED");
        }
        else {
            System.out.println("  IP Comparison (Different IP, Same Mask) :  PASSED");
        }
        if (ip2.equals((Object)ip4)) {
            System.err.println("  IP Comparison (Same IP, Different Mask) :  FAILED");
        }
        else {
            System.out.println("  IP Comparison (Same IP, Different Mask) :  PASSED");
        }
        if (ip4.equals((Object)ip5)) {
            System.out.println("  IP Comparison (Identical)               :  PASSED");
        }
        else {
            System.err.println("  IP Comparison (Identical)               :  FAILED");
        }
        IpAddr ip6 = null;
        IpAddr ip7 = null;
        IpAddr ip8 = null;
        IpAddr ip9 = null;
        IpAddr ip10 = null;
        try {
            ip6 = new IpAddr("1.0.0.0/16");
            ip7 = new IpAddr("1.0.0.1/16");
            ip8 = new IpAddr("2.3.4.0/24");
            ip9 = new IpAddr("2.3.4.0/21");
            ip10 = new IpAddr("2.0.0.0/12");
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        if (ip6.contains(ip6)) {
            System.out.println("  IP Containment (Identical)              :  PASSED");
        }
        else {
            System.err.println("  IP Containment (Identical)              :  FAILED");
        }
        if (ip6.contains(ip7)) {
            System.out.println("  IP Containment (1st contains 2nd)       :  PASSED");
        }
        else {
            System.err.println("  IP Containment (1st contains 2nd)       :  FAILED");
        }
        if (!ip6.contains(ip8)) {
            System.out.println("  IP Containment (Not related)            :  PASSED");
        }
        else {
            System.err.println("  IP Containment (Not related)            :  FAILED");
        }
        if (!ip8.contains(ip9)) {
            System.out.println("  IP Containment (Not related - Mask)     :  PASSED");
        }
        else {
            System.err.println("  IP Containment (Not related - Mask)     :  FAILED");
        }
        if (!ip8.contains(ip10)) {
            System.out.println("  IP Containment (2nd contains 1st)       :  PASSED");
        }
        else {
            System.err.println("  IP Containment (2nd contains 1st)       :  FAILED");
        }
    }
    
    public static void runCompleteTest() {
        System.out.println("Test Not Implemented. ");
    }
    
    public static void runCustomTest() {
        final String menu = "\n ******************** \n *  Custom IP Test  * \n ******************** \nEnter a single IP Address or a pair of IP Addresses (seperated by a space): \n";
        System.out.println(menu);
        System.out.print("IPTEST> ");
        String input = "";
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = in.readLine();
        }
        catch (IOException ioEx) {
            System.err.println(ioEx.getMessage());
        }
        while (true) {
            input = input.trim();
            if (input.length() > 0) {
                String s1;
                String s2;
                if (input.contains(" ")) {
                    s1 = input.split(" ")[0];
                    s2 = input.split(" ")[1];
                }
                else {
                    s1 = input;
                    s2 = null;
                }
                if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                    break;
                }
                if (s2 == null) {
                    ipProperties(s1);
                }
                else {
                    ipComparison(s1, s2);
                }
            }
            System.out.println(menu);
            System.out.print("IPTEST> ");
            try {
                input = in.readLine();
            }
            catch (IOException ioEx2) {
                System.err.println(ioEx2.getMessage());
            }
        }
    }
    
    public static void ipProperties(final String s) {
        IpAddr ip = null;
        try {
            ip = new IpAddr(s);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
        if (ip != null) {
            String type = "";
            if (ip.isAny()) {
                type = String.valueOf(type) + "ANY ";
            }
            if (ip.isBroadcast()) {
                type = String.valueOf(type) + "Broadcast ";
            }
            if (ip.isHost()) {
                type = String.valueOf(type) + "Host ";
            }
            if (ip.isLoopback()) {
                type = String.valueOf(type) + "Loopback ";
            }
            if (ip.isMulticast()) {
                type = String.valueOf(type) + "Multicast ";
            }
            if (ip.isNetwork()) {
                type = String.valueOf(type) + "Network ";
            }
            if (ip.isPrivate()) {
                type = String.valueOf(type) + "(Private) ";
            }
            if (ip.isReserved()) {
                type = "Reserved ";
            }
            System.out.println("Properties for IP Address (" + ip.toString() + "):");
            System.out.println("===============================================");
            System.out.println("Address String:   " + ip.getAddressString());
            System.out.println("Netmask String:   " + ip.getNetmaskString());
            System.out.println("Netmask Length:   " + ip.getNetmaskLength());
            System.out.println("Subnet Address:   " + ip.getSubnet().getAddressString());
            System.out.println("Broadcast Addr:   " + ip.getBroadcast().getAddressString());
            System.out.println("Type of Address:  " + type);
            System.out.println("Class of Address: " + ip.getAddressClass());
            System.out.println("List of all super-nets: ");
            for (final IpAddr i : ip.getAllSubnets()) {
                System.out.println("  " + i.toString());
            }
        }
    }
    
    public static void ipComparison(final String s1, final String s2) {
        IpAddr ip1 = null;
        IpAddr ip2 = null;
        try {
            ip1 = new IpAddr(s1);
            ip2 = new IpAddr(s2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        if (ip1.contains(ip2)) {
            System.out.println(" The 1st address contains the 2nd.");
        }
        else if (ip2.contains(ip1)) {
            System.out.println(" The 2nd address contains the 1st.");
        }
        else {
            System.out.println("-1 - The two addresses are not related.");
        }
    }
}
