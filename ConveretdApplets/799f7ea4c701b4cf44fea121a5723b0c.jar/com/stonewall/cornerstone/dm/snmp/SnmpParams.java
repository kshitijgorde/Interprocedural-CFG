// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

public class SnmpParams
{
    public static final int NOAUTH = 0;
    public static final int MD5 = 1;
    public static final int SHA1 = 2;
    public static final int NOPRIV = 0;
    public static final int CBC_DES = 1;
    public String addr;
    public int port;
    public int version;
    public String community;
    public int timeout;
    public int retries;
    public String engineId;
    public String contextName;
    public int authProtocol;
    public String userName;
    public String userAuth;
    public int privProtocol;
    public String privPassword;
    
    public SnmpParams() {
        this.addr = "";
        this.port = 161;
        this.version = 1;
        this.community = "public";
        this.timeout = 300;
        this.retries = 3;
        this.engineId = "";
        this.contextName = "";
        this.authProtocol = 0;
        this.userName = "";
        this.userAuth = "";
        this.privProtocol = 0;
        this.privPassword = "";
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Version: ");
        sb.append((this.version == 1) ? "1" : ((this.version == 2) ? "2c" : "3"));
        sb.append("\nAddress: ");
        sb.append(this.addr);
        sb.append("\nPort: ");
        sb.append(this.port);
        sb.append("\nTimeout: ");
        sb.append(this.timeout);
        sb.append("\nRetries: ");
        sb.append(this.retries);
        if (this.version == 3) {
            sb.append("\nEngineId: ");
            sb.append(this.engineId);
            sb.append("\nContextName: ");
            sb.append(this.contextName);
            sb.append("\nAuth Protocol: ");
            sb.append((this.authProtocol == 0) ? "NOAUTH" : ((this.authProtocol == 1) ? "MD5" : "SHA1"));
            sb.append("\nUserName: ");
            sb.append(this.userName);
            sb.append("\nUserAuth: ");
            sb.append(this.userAuth);
            sb.append("\nPriv Protocol: ");
            sb.append((this.privProtocol == 0) ? "NOPRIV" : "CBC_DES");
            sb.append("\nPriv Password: ");
            sb.append(this.privPassword);
        }
        else {
            sb.append("\nCommunity: ");
            sb.append(this.community);
        }
        sb.append('\n');
        return sb.toString();
    }
    
    boolean equals(final SnmpParams rhs) {
        if (!this.addr.equals(rhs.addr) || this.port != rhs.port || this.version != rhs.version || this.timeout != rhs.timeout || this.retries != rhs.retries) {
            return false;
        }
        boolean result = true;
        switch (this.version) {
            case 1:
            case 2: {
                result = this.community.equals(rhs.community);
                break;
            }
            case 3: {
                result = (this.engineId.equals(rhs.engineId) && this.contextName.equals(rhs.contextName) && this.authProtocol == rhs.authProtocol && this.userName.equals(rhs.userName) && this.userAuth.equals(rhs.userAuth) && this.privProtocol == rhs.privProtocol && this.privPassword.equals(rhs.privPassword));
                break;
            }
        }
        return result;
    }
    
    byte[] getEngineId() {
        int n = 0;
        final byte[] result = new byte[this.engineId.length() / 2];
        try {
            char[] b;
            String digit;
            Long aLong;
            for (int i = 0; i < this.engineId.length(); b = new char[] { this.engineId.charAt(i), this.engineId.charAt(++i) }, digit = new String(b), aLong = Long.valueOf(digit, 16), result[n++] = (byte)(Object)aLong, ++i) {}
        }
        catch (Exception ex) {}
        return result;
    }
}
