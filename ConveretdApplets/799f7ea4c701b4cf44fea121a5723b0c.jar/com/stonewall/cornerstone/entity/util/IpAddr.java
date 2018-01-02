// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.log.Log;

public class IpAddr
{
    private String addressString;
    private int address;
    private String netmaskString;
    private int netmask;
    private int maskLength;
    private int ipSubnet;
    private int broadcast;
    static final Log log;
    
    static {
        log = Log.getLog(IpAddr.class);
    }
    
    public static IpAddr ipAny() {
        try {
            return new IpAddr(0, 0);
        }
        catch (Exception e) {
            IpAddr.log.error("0.0.0.0/0.0.0.0", e);
            return null;
        }
    }
    
    public static IpAddr create(final String address, final String netmask) {
        if (netmask == null || netmask.equals("")) {
            return create(address);
        }
        IpAddr ip = null;
        try {
            ip = new IpAddr(address, netmask);
        }
        catch (Exception ex) {
            IpAddr.log.warn(ex);
        }
        return ip;
    }
    
    public static IpAddr create(final String address) {
        IpAddr ip = null;
        try {
            ip = new IpAddr(address);
        }
        catch (Exception ex) {
            IpAddr.log.warn(ex);
        }
        return ip;
    }
    
    public static boolean isAny(final String address) {
        try {
            final IpAddr ip = new IpAddr(address);
            return ip.isAny();
        }
        catch (Exception ex) {
            IpAddr.log.error(ex);
            return true;
        }
    }
    
    IpAddr() {
        this.ipSubnet = -1;
        this.broadcast = -1;
    }
    
    public IpAddr(String address) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        if (address == null || address.equals("")) {
            throw new Exception("Invalid IP Address - Must supply an address");
        }
        if (address.equalsIgnoreCase("any")) {
            address = ipAny().addressString;
        }
        final boolean hasMask = address.contains("/");
        this.addressString = address.split("/")[0];
        this.address = this.getAsBinary(this.addressString);
        if (hasMask) {
            this.maskLength = Integer.parseInt(address.split("/")[1]);
            if (!this.maskLengthToOctet()) {
                throw new Exception("Invalid IP Address");
            }
        }
        else if (!this.useClassfulMask()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final String address, final String netmask) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        if (address == null || address.equals("")) {
            throw new Exception("Invalid IP Address - Must supply an address");
        }
        if (netmask == null || netmask.equals("")) {
            throw new Exception("Invalid IP Address - Must supply a netmask");
        }
        this.addressString = address;
        this.address = this.getAsBinary(this.addressString);
        this.netmaskString = netmask;
        this.netmask = this.getAsBinary(this.netmaskString);
        if (!this.maskOctetToLength()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final int address, final int netmask) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        this.address = address;
        this.netmask = netmask;
        if (!this.maskOctetToLength()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final int addrOctet1, final int addrOctet2, final int addrOctet3, final int addrOctet4, final int maskOctet1, final int maskOctet2, final int maskOctet3, final int maskOctet4) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        this.address = this.getAsBinary(new int[] { addrOctet1, addrOctet2, addrOctet3, addrOctet4 });
        this.netmask = this.getAsBinary(new int[] { maskOctet1, maskOctet2, maskOctet3, maskOctet4 });
        if (!this.maskOctetToLength()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final int addrOctet1, final int addrOctet2, final int addrOctet3, final int addrOctet4) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        this.address = this.getAsBinary(new int[] { addrOctet1, addrOctet2, addrOctet3, addrOctet4 });
        if (!this.useClassfulMask()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final int[] addrOctets, final int[] maskOctets) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        if (addrOctets.length != 4 || maskOctets.length != 4) {
            throw new Exception("Invalid IP Address");
        }
        this.address = this.getAsBinary(addrOctets);
        this.netmask = this.getAsBinary(maskOctets);
        if (!this.maskOctetToLength()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    public IpAddr(final int[] addrOctets) throws Exception {
        this.ipSubnet = -1;
        this.broadcast = -1;
        if (addrOctets.length != 4) {
            throw new Exception("Invalid IP Address");
        }
        this.address = this.getAsBinary(addrOctets);
        if (!this.useClassfulMask()) {
            throw new Exception("Invalid IP Address");
        }
        if (!this.init()) {
            throw new Exception("Invalid IP Address");
        }
    }
    
    private boolean init() throws Exception {
        return this.findBroadcast() && this.findSubnet();
    }
    
    public boolean isAny() {
        return this.address == 0;
    }
    
    public boolean isBroadcast() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        return this.address == -1 || (addrOctet1 >= 1 && addrOctet1 <= 223 && this.address == this.broadcast);
    }
    
    public boolean isMulticast() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        return addrOctet1 >= 224 && addrOctet1 <= 247;
    }
    
    public boolean isReserved() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        return addrOctet1 >= 248 && addrOctet1 <= 255;
    }
    
    public boolean isInternet() {
        return false;
    }
    
    public boolean isHost() {
        return !this.isNetwork();
    }
    
    public boolean isNetwork() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        return this.maskLength != 32 && (addrOctet1 >= 1 && addrOctet1 <= 223 && this.getAddress() == this.ipSubnet);
    }
    
    public boolean isPrivate() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        final int addrOctet2 = this.getOctet(this.getAddress(), 2);
        return addrOctet1 == 10 || (addrOctet1 == 172 && addrOctet2 >= 16 && addrOctet2 <= 31) || (addrOctet1 == 192 && addrOctet2 == 168) || (addrOctet1 == 169 && addrOctet2 == 254);
    }
    
    public boolean isPublic() {
        return !this.isPrivate();
    }
    
    public boolean isLocal() {
        return this.isLoopback();
    }
    
    public boolean isLoopback() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        return addrOctet1 == 127;
    }
    
    public String getAddressClass() {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        if (addrOctet1 >= 0 && addrOctet1 <= 127) {
            return "A";
        }
        if (addrOctet1 >= 128 && addrOctet1 <= 191) {
            return "B";
        }
        if (addrOctet1 >= 192 && addrOctet1 <= 223) {
            return "C";
        }
        if (addrOctet1 >= 224 && addrOctet1 <= 247) {
            return "D";
        }
        if (addrOctet1 >= 248 && addrOctet1 <= 255) {
            return "E";
        }
        return "";
    }
    
    public IpAddr getSubnet() {
        if (this.ipSubnet == -1) {
            return null;
        }
        IpAddr subnet = null;
        try {
            subnet = new IpAddr(this.ipSubnet, this.netmask);
            return subnet;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public IpAddr getBroadcast() {
        if (this.broadcast == -1) {
            return null;
        }
        IpAddr bc = null;
        try {
            bc = new IpAddr(this.broadcast, -1);
            return bc;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public String getAddressString() {
        if (this.addressString == null) {
            this.addressString = this.getAsString(this.getAddress());
        }
        return this.addressString;
    }
    
    public int getAddress() {
        return this.address;
    }
    
    public int[] getAddressOctets() {
        return this.getOctets(this.getAddress());
    }
    
    public int getNetmask() {
        return this.netmask;
    }
    
    public String getNetmaskString() {
        if (this.netmaskString == null) {
            this.netmaskString = this.getAsString(this.getNetmask());
        }
        return this.netmaskString;
    }
    
    public int getNetmaskLength() {
        return this.maskLength;
    }
    
    private int getAsBinary(final int[] octets) throws Exception {
        int binary = 0;
        int i = 0;
        for (int n = 24; n > 0; n -= 8, ++i) {
            if (octets[i] > 255) {
                throw new Exception("IP Invalid");
            }
            final int octet = octets[i] << n;
            binary |= octet;
        }
        binary |= octets[i];
        return binary;
    }
    
    private int getAsBinary(final String s) throws Exception {
        int result = 0;
        int i = 0;
        final int max = s.length();
        int shift = 24;
        int binary = 0;
        while (i < max) {
            char c = s.charAt(i++);
            do {
                result *= 10;
                result -= Character.digit(c, 10);
                if (i >= max) {
                    break;
                }
                c = s.charAt(i++);
            } while (c != '.');
            result = -result;
            if (result > 255) {
                throw new Exception("IP Invalid");
            }
            if (shift == 0) {
                binary |= result;
            }
            else {
                binary |= result << shift;
            }
            shift -= 8;
            result = 0;
        }
        return binary;
    }
    
    private String getAsString(final int value) {
        final StringBuffer sb = new StringBuffer(15);
        for (int shift = 24; shift > 0; shift -= 8) {
            sb.append(Integer.toString(value >>> shift & 0xFF));
            sb.append('.');
        }
        sb.append(Integer.toString(value & 0xFF));
        return sb.toString();
    }
    
    private int[] getOctets(final int value) {
        return new int[] { value >>> 24 & 0xFF, value >>> 16 & 0xFF, value >>> 8 & 0xFF, value & 0xFF };
    }
    
    private int getOctet(final int value, final int index) {
        int shift = 0;
        switch (index) {
            case 1: {
                shift = 24;
                break;
            }
            case 2: {
                shift = 16;
                break;
            }
            case 3: {
                shift = 8;
                break;
            }
        }
        return value >>> shift & 0xFF;
    }
    
    public int[] getFirstHost() {
        if (!this.isNetwork()) {
            return null;
        }
        final int[] addrOctets = this.getOctets(this.getAddress());
        ++addrOctets[3];
        return addrOctets;
    }
    
    public int[] getLastHost() {
        if (!this.isNetwork()) {
            return null;
        }
        try {
            final IpAddr bcast = new IpAddr(this.broadcast, this.netmask);
            final int[] addrOctets = bcast.getOctets(bcast.getAddress());
            --addrOctets[3];
            return addrOctets;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public List<IpAddr> getAllSubnets() {
        final List<IpAddr> all = new ArrayList<IpAddr>();
        final String ip = this.getAddressString();
        if (ip.equals("0.0.0.0")) {
            try {
                all.add(new IpAddr("0.0.0.0/32"));
            }
            catch (Exception ex) {
                IpAddr.log.warn(this, ex);
            }
            return all;
        }
        for (int i = this.maskLength; i > 0; --i) {
            try {
                all.add(new IpAddr(String.valueOf(ip) + "/" + Integer.toString(i)).getSubnet());
            }
            catch (Exception ex2) {
                IpAddr.log.warn(this, ex2);
            }
        }
        return all;
    }
    
    @Override
    public boolean equals(final Object object) {
        return super.equals(object) || this.equals((IpAddr)object);
    }
    
    @Override
    public int hashCode() {
        return this.address;
    }
    
    public boolean contains(final IpAddr ip) {
        if (ip.isInternet()) {
            return false;
        }
        if (this.equals(ip)) {
            return true;
        }
        if (this.isAny()) {
            return true;
        }
        if (ip.isAny()) {
            return false;
        }
        if (this.isNetwork() && ip.isNetwork()) {
            final int mask1 = this.getNetmaskLength();
            final int mask2 = ip.getNetmaskLength();
            int mask3 = 0;
            int host1 = 0;
            int host2 = 0;
            if (mask1 == mask2) {
                return false;
            }
            if (mask1 > mask2) {
                mask3 = mask2;
            }
            else {
                mask3 = mask1;
            }
            final int subnet = (1 << 32 - mask3) - 1;
            if ((this.getAddress() & ~subnet) == (ip.getAddress() & ~subnet)) {
                host1 = (this.getAddress() & subnet);
                host2 = (ip.getAddress() & subnet);
                return (host1 == 0 && host2 != 0) || ((host2 != 0 || host1 == 0) && mask1 < mask2);
            }
            return false;
        }
        else {
            if (this.isNetwork()) {
                if (ip.getNetmaskLength() == 32) {
                    try {
                        return this.contains(new IpAddr(String.valueOf(ip.getAddressString()) + "/31"));
                    }
                    catch (Exception e) {
                        return false;
                    }
                }
                return this.contains(ip.getSubnet());
            }
            if (ip.isNetwork()) {
                if (this.getNetmaskLength() == 32) {
                    try {
                        final IpAddr a = new IpAddr(String.valueOf(this.getAddressString()) + "/31");
                        return a.contains(ip);
                    }
                    catch (Exception e) {
                        return false;
                    }
                }
                if (ip.contains(this.getSubnet())) {
                    return false;
                }
            }
            else if (this.getAddressString().equals(ip.getAddressString())) {
                return true;
            }
            return false;
        }
    }
    
    public boolean bidirectionallyContains(final IpAddr ip) {
        return this.contains(ip) || ip.contains(this);
    }
    
    public boolean equalsIgnoreMask(final IpAddr ip) {
        return !ip.isInternet() && this.getAddress() == ip.getAddress();
    }
    
    public int compare(final IpAddr ip) {
        if (ip.isInternet()) {
            return -1;
        }
        if (this.getNetmaskLength() != ip.getNetmaskLength()) {
            return -1;
        }
        final int[] a1 = this.getAddressOctets();
        final int[] a2 = ip.getAddressOctets();
        if (this.getAddress() == ip.getAddress()) {
            return 0;
        }
        if (a1[0] >= a2[0] && a1[1] >= a2[1] && a1[2] >= a2[2] && a1[3] >= a2[3]) {
            return 1;
        }
        return 2;
    }
    
    public int compareIgnoreMask(final IpAddr ip) {
        if (ip.isInternet()) {
            return -1;
        }
        final int[] a1 = this.getAddressOctets();
        final int[] a2 = ip.getAddressOctets();
        if (this.getAddress() == ip.getAddress()) {
            return 0;
        }
        if (a1[0] >= a2[0] && a1[1] >= a2[1] && a1[2] >= a2[2] && a1[3] >= a2[3]) {
            return 1;
        }
        return 2;
    }
    
    public static IpAddr getAddrByName(final String host) throws Exception {
        try {
            return new IpAddr(InetAddress.getByName(host).toString().split("/")[1]);
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public static String getAddrStringByName(final String host) throws Exception {
        try {
            return InetAddress.getByName(host).toString().split("/")[1];
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public static List<IpAddr> getNetworksInRange(final IpAddr a, final IpAddr b, final int[] inc, final int[] msk) throws Exception {
        if (inc.length != 4) {
            throw new Exception("Invalid Increments");
        }
        final List<IpAddr> networks = new ArrayList<IpAddr>();
        final int[] ip1 = a.getOctets(a.getAddress());
        final int[] ip2 = b.getOctets(b.getAddress());
        for (int o1 = ip1[0]; o1 <= ip2[0]; o1 += inc[0]) {
            for (int o2 = ip1[1]; o2 <= ip2[1]; o2 += inc[1]) {
                for (int o3 = ip1[2]; o3 <= ip2[2]; o3 += inc[2]) {
                    for (int o4 = ip1[3]; o4 <= ip2[3]; o4 += inc[3]) {
                        final int[] oct = { o1, o2, o3, o4 };
                        IpAddr ip3 = null;
                        try {
                            ip3 = new IpAddr(oct, msk);
                        }
                        catch (Exception e) {
                            throw e;
                        }
                        if (ip3 != null && ip3.isNetwork()) {
                            networks.add(ip3);
                        }
                        if (inc[3] == 0) {
                            break;
                        }
                    }
                    if (inc[2] == 0) {
                        break;
                    }
                }
                if (inc[1] == 0) {
                    break;
                }
            }
            if (inc[0] == 0) {
                break;
            }
        }
        return networks;
    }
    
    public static IpAddr createNetIp(final int[] addrOctets) throws Exception {
        final int[] maskOctets = getNetIpMask(addrOctets);
        return new IpAddr(addrOctets, maskOctets);
    }
    
    public static int[] getNetIpMask(final int[] addrOctets) throws Exception {
        if (addrOctets.length != 4) {
            throw new Exception("Invalid IP Address");
        }
        if (!validateAddr(addrOctets[0], addrOctets[1], addrOctets[2], addrOctets[3])) {
            throw new Exception("Invalid IP Address");
        }
        final String addrBinary = String.valueOf(decimalToBinary(addrOctets[0])) + decimalToBinary(addrOctets[1]) + decimalToBinary(addrOctets[2]) + decimalToBinary(addrOctets[3]);
        final char[] a = addrBinary.toCharArray();
        final char[] m = new char[32];
        boolean net = false;
        for (int j = a.length - 1; j >= 0; --j) {
            if (net || a[j] == '1') {
                net = true;
            }
            if (net) {
                m[j] = '1';
            }
            else {
                m[j] = '0';
            }
        }
        final char[] m2 = { m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7] };
        final char[] m3 = { m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15] };
        final char[] m4 = { m[16], m[17], m[18], m[19], m[20], m[21], m[22], m[23] };
        final char[] m5 = { m[24], m[25], m[26], m[27], m[28], m[29], m[30], m[31] };
        final int[] maskOctets = { binaryToDecimal(new String(m2)), binaryToDecimal(new String(m3)), binaryToDecimal(new String(m4)), binaryToDecimal(new String(m5)) };
        return maskOctets;
    }
    
    public static int[] getLooseNetIpMask(final int[] addrOctets) throws Exception {
        final int[] maskOctets = getNetIpMask(addrOctets);
        if (maskOctets[3] > 0) {
            return maskOctets;
        }
        if (maskOctets[2] > 0) {
            maskOctets[2] = 255;
        }
        else if (maskOctets[1] > 0) {
            maskOctets[1] = 255;
        }
        else {
            maskOctets[0] = 255;
        }
        return maskOctets;
    }
    
    public static String invertMask(final String netmask) throws Exception {
        if (!netmask.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            throw new Exception("Invalid IP Address - Invalid Subnet Mask");
        }
        final String[] maskOctets = netmask.split("\\.");
        final int maskOctet1 = Integer.parseInt(maskOctets[0]);
        final int maskOctet2 = Integer.parseInt(maskOctets[1]);
        final int maskOctet3 = Integer.parseInt(maskOctets[2]);
        final int maskOctet4 = Integer.parseInt(maskOctets[3]);
        final int invMaskOctet1 = 255 - maskOctet1;
        final int invMaskOctet2 = 255 - maskOctet2;
        final int invMaskOctet3 = 255 - maskOctet3;
        final int invMaskOctet4 = 255 - maskOctet4;
        final StringBuilder builder = new StringBuilder();
        builder.append(invMaskOctet1);
        builder.append('.');
        builder.append(invMaskOctet2);
        builder.append('.');
        builder.append(invMaskOctet3);
        builder.append('.');
        builder.append(invMaskOctet4);
        IpAddr.log.debug("in mask = " + netmask + "; outmask = " + builder.toString());
        return builder.toString();
    }
    
    public static IpAddr safeAddr(final String s) {
        IpAddr result = null;
        try {
            result = new IpAddr(s);
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static IpAddr safeAddr(final String address, final String mask) {
        IpAddr result = null;
        try {
            result = new IpAddr(address, mask);
        }
        catch (Exception ex) {}
        return result;
    }
    
    private static boolean validateAddr(final int addrOctet1, final int addrOctet2, final int addrOctet3, final int addrOctet4) {
        return addrOctet1 >= 0 && addrOctet1 <= 255 && addrOctet2 >= 0 && addrOctet2 <= 255 && addrOctet3 >= 0 && addrOctet3 <= 255 && addrOctet4 >= 0 && addrOctet4 <= 255;
    }
    
    private boolean maskOctetToLength() {
        int octet = 0;
        this.maskLength = 0;
        final int[] maskOctets = this.getOctets(this.getNetmask());
        octet = this.matchOctetToLength(maskOctets[0]);
        if (octet < 0) {
            return false;
        }
        this.maskLength += octet;
        octet = this.matchOctetToLength(maskOctets[1]);
        if (octet < 0) {
            return false;
        }
        this.maskLength += octet;
        octet = this.matchOctetToLength(maskOctets[2]);
        if (octet < 0) {
            return false;
        }
        this.maskLength += octet;
        octet = this.matchOctetToLength(maskOctets[3]);
        if (octet < 0) {
            return false;
        }
        this.maskLength += octet;
        return true;
    }
    
    private boolean maskLengthToOctet() {
        if (this.maskLength >= 24) {
            this.netmask = -256;
            final int maskOctet4 = this.matchLengthToOctet(this.maskLength - 24);
            if (maskOctet4 < 0) {
                return false;
            }
            this.netmask |= maskOctet4;
        }
        else if (this.maskLength >= 16) {
            this.netmask = -65536;
            final int maskOctet5 = this.matchLengthToOctet(this.maskLength - 16);
            if (maskOctet5 < 0) {
                return false;
            }
            this.netmask |= maskOctet5 << 8;
        }
        else if (this.maskLength >= 8) {
            this.netmask = -16777216;
            final int maskOctet6 = this.matchLengthToOctet(this.maskLength - 8);
            if (maskOctet6 < 0) {
                return false;
            }
            this.netmask |= maskOctet6 << 16;
        }
        else if (this.maskLength >= 1) {
            this.netmask = 0;
            final int maskOctet7 = this.matchLengthToOctet(this.maskLength);
            if (maskOctet7 < 0) {
                return false;
            }
            this.netmask |= maskOctet7 << 24;
        }
        return true;
    }
    
    private boolean useClassfulMask() {
        final String addrClass = this.getAddressClass();
        if (addrClass.equalsIgnoreCase("A")) {
            this.maskLength = 8;
            this.maskLengthToOctet();
            return true;
        }
        if (addrClass.equalsIgnoreCase("B")) {
            this.maskLength = 16;
            this.maskLengthToOctet();
            return true;
        }
        if (addrClass.equalsIgnoreCase("C")) {
            this.maskLength = 24;
            this.maskLengthToOctet();
            return true;
        }
        if (addrClass.equalsIgnoreCase("D")) {
            this.maskLength = 32;
            this.maskLengthToOctet();
            return true;
        }
        if (addrClass.equalsIgnoreCase("E")) {
            this.maskLength = 32;
            this.maskLengthToOctet();
            return true;
        }
        return false;
    }
    
    private boolean findSubnet() throws Exception {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        if (addrOctet1 < 1 || addrOctet1 > 223) {
            return true;
        }
        final int[] addrOctets = this.getOctets(this.getAddress());
        final int[] maskOctets = this.getOctets(this.getNetmask());
        final int[] subnetOctets = new int[4];
        int c;
        for (c = 0, c = 0; c < 4; ++c) {
            if (maskOctets[c] == 255) {
                subnetOctets[c] = addrOctets[c];
            }
            else if (maskOctets[c] == 0) {
                subnetOctets[c] = 0;
            }
            else {
                subnetOctets[c] = (addrOctets[c] & maskOctets[c]);
            }
        }
        this.ipSubnet = this.getAsBinary(subnetOctets);
        return true;
    }
    
    private boolean findBroadcast() throws Exception {
        final int addrOctet1 = this.getOctet(this.getAddress(), 1);
        if (addrOctet1 < 1 || addrOctet1 > 223) {
            return true;
        }
        final int[] addrOctets = this.getOctets(this.getAddress());
        final int[] maskOctets = this.getOctets(this.getNetmask());
        final int[] castOctets = new int[4];
        int c;
        int m;
        int i;
        for (c = 0, c = 0; c < 4; ++c) {
            if (maskOctets[c] == 255) {
                castOctets[c] = addrOctets[c];
            }
            else if (maskOctets[c] == 0) {
                castOctets[c] = 255;
            }
            else {
                m = ~maskOctets[c];
                m &= 0xFF;
                i = (addrOctets[c] | m);
                castOctets[c] = i;
            }
        }
        this.broadcast = this.getAsBinary(castOctets);
        return true;
    }
    
    private int matchOctetToLength(final int octet) {
        switch (octet) {
            case 0: {
                return 0;
            }
            case 128: {
                return 1;
            }
            case 192: {
                return 2;
            }
            case 224: {
                return 3;
            }
            case 240: {
                return 4;
            }
            case 248: {
                return 5;
            }
            case 252: {
                return 6;
            }
            case 254: {
                return 7;
            }
            case 255: {
                return 8;
            }
            default: {
                return -1;
            }
        }
    }
    
    private int matchLengthToOctet(final int length) {
        switch (length) {
            case 0: {
                return 0;
            }
            case 1: {
                return 128;
            }
            case 2: {
                return 192;
            }
            case 3: {
                return 224;
            }
            case 4: {
                return 240;
            }
            case 5: {
                return 248;
            }
            case 6: {
                return 252;
            }
            case 7: {
                return 254;
            }
            case 8: {
                return 255;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static int binaryToDecimal(final String binary) {
        return Integer.parseInt(binary, 2);
    }
    
    private static String decimalToBinary(final int decimal) {
        final StringBuffer buf = new StringBuffer(Integer.toBinaryString(decimal));
        while (buf.length() < 8) {
            buf.insert(0, '0');
        }
        return buf.toString();
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.getAddressString()) + "/" + this.maskLength;
    }
    
    @Deprecated
    public static int contain(final IpAddr ip1, final IpAddr ip2) {
        if (ip1.equals(ip2)) {
            return 0;
        }
        if (ip1.isAny() && ip2.isAny()) {
            return 0;
        }
        if (ip1.isAny()) {
            return 1;
        }
        if (ip2.isAny()) {
            return 2;
        }
        if (ip1.isNetwork() && ip2.isNetwork()) {
            final int mask1 = ip1.getNetmaskLength();
            final int mask2 = ip2.getNetmaskLength();
            int mask3 = 0;
            int host1 = 0;
            int host2 = 0;
            if (mask1 == mask2) {
                return -1;
            }
            if (mask1 > mask2) {
                mask3 = mask2;
            }
            else {
                mask3 = mask1;
            }
            final int subnet = (1 << 32 - mask3) - 1;
            if ((ip1.getAddress() & ~subnet) != (ip2.getAddress() & ~subnet)) {
                return -1;
            }
            host1 = (ip1.getAddress() & subnet);
            host2 = (ip2.getAddress() & subnet);
            if (host1 == 0 && host2 != 0) {
                return 1;
            }
            if (host2 == 0 && host1 != 0) {
                return 2;
            }
            if (mask1 < mask2) {
                return 1;
            }
            return 2;
        }
        else if (ip1.isNetwork()) {
            if (ip2.getNetmaskLength() == 32) {
                try {
                    return contain(ip1, new IpAddr(String.valueOf(ip2.getAddressString()) + "/31"));
                }
                catch (Exception e) {
                    return -2;
                }
            }
            if (ip1.equals(ip2.getSubnet()) || contain(ip1, ip2.getSubnet()) == 1) {
                return 1;
            }
            return -1;
        }
        else if (ip2.isNetwork()) {
            if (ip1.getNetmaskLength() == 32) {
                try {
                    return contain(new IpAddr(String.valueOf(ip1.getAddressString()) + "/31"), ip2);
                }
                catch (Exception e) {
                    return -2;
                }
            }
            if (ip2.equals(ip1.getSubnet()) || contain(ip2, ip1.getSubnet()) == 1) {
                return 2;
            }
            return -1;
        }
        else {
            if (ip1.getAddressString().equals(ip2.getAddressString())) {
                return 0;
            }
            return -1;
        }
    }
    
    private boolean equals(final IpAddr ip) {
        return this.address == ip.address && this.maskLength == ip.maskLength;
    }
}
