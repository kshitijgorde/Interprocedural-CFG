// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

import java.net.InetAddress;
import anon.infoservice.ListenerInterface;
import java.util.StringTokenizer;
import java.util.Vector;

public class ORAcl
{
    private Vector m_Constraints;
    
    public ORAcl() {
        this.m_Constraints = new Vector();
    }
    
    public void add(final String s) throws Exception {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String nextToken = stringTokenizer.nextToken();
        boolean b = false;
        if (nextToken.equals("accept")) {
            b = true;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ":");
        final String nextToken2 = stringTokenizer2.nextToken();
        final String nextToken3 = stringTokenizer2.nextToken();
        int int1;
        int int2;
        if (nextToken3.equals("*")) {
            int1 = 0;
            int2 = 65535;
        }
        else {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(nextToken3, "-");
            int1 = Integer.parseInt(stringTokenizer3.nextToken());
            if (stringTokenizer3.hasMoreTokens()) {
                int2 = Integer.parseInt(stringTokenizer3.nextToken());
            }
            else {
                int2 = int1;
            }
        }
        String nextToken4;
        String s2;
        if (nextToken2.equals("*")) {
            nextToken4 = "0.0.0.0";
            s2 = "0.0.0.0";
        }
        else {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(nextToken2, "/");
            nextToken4 = stringTokenizer4.nextToken();
            if (stringTokenizer4.hasMoreElements()) {
                s2 = stringTokenizer4.nextToken();
                try {
                    int n = Integer.parseInt(s2);
                    if (n >= 0) {
                        s2 = "";
                        for (int i = 0; i < 4; ++i) {
                            if (n >= 8) {
                                s2 += 255;
                            }
                            else if (n == 0) {
                                s2 += 0;
                            }
                            else {
                                s2 += 255 - ((int)Math.pow(2.0, 8 - n) - 1);
                            }
                            n = Math.max(0, n - 8);
                            if (i != 3) {
                                s2 += ".";
                            }
                        }
                    }
                }
                catch (NumberFormatException ex) {}
            }
            else {
                s2 = "255.255.255.255";
            }
        }
        this.m_Constraints.addElement(new AclElement(b, nextToken4, s2, int1, int2));
    }
    
    public boolean isAllowed(final String s, final int n) {
        if (!ListenerInterface.isValidIP(s)) {
            return false;
        }
        try {
            for (int i = 0; i < this.m_Constraints.size(); ++i) {
                final AclElement aclElement = this.m_Constraints.elementAt(i);
                if (aclElement.isContained(s, n)) {
                    return aclElement.isAccept();
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public boolean isAllowed(final int n) {
        try {
            for (int i = 0; i < this.m_Constraints.size(); ++i) {
                final AclElement aclElement = this.m_Constraints.elementAt(i);
                if (aclElement.isContained(null, n)) {
                    return aclElement.isAccept();
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private class AclElement
    {
        byte[] arAdrWithMask;
        byte[] arAdrMask;
        int portLow;
        int portHigh;
        boolean bIsAccept;
        
        public AclElement(final boolean bIsAccept, final String s, final String s2, final int portLow, final int portHigh) throws Exception {
            this.arAdrWithMask = InetAddress.getByName(s).getAddress();
            this.arAdrMask = InetAddress.getByName(s2).getAddress();
            for (int i = 0; i < 4; ++i) {
                final byte[] arAdrWithMask = this.arAdrWithMask;
                final int n = i;
                arAdrWithMask[n] &= this.arAdrMask[i];
            }
            this.portLow = portLow;
            this.portHigh = portHigh;
            this.bIsAccept = bIsAccept;
        }
        
        public boolean isContained(final String s, final int n) throws Exception {
            if (n < this.portLow || n > this.portHigh) {
                return false;
            }
            if (s != null) {
                final byte[] address = InetAddress.getByName(s).getAddress();
                for (int i = 0; i < 4; ++i) {
                    if ((address[i] & this.arAdrMask[i]) != this.arAdrWithMask[i]) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        public boolean isAccept() {
            return this.bIsAccept;
        }
    }
}
