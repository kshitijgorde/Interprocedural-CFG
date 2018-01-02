// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.security.PrivateKey;
import mindbright.security.PublicKey;
import mindbright.security.RSAPrivateKey;
import java.util.Random;
import java.math.BigInteger;
import java.io.IOException;
import mindbright.security.MessageDigest;
import mindbright.security.RSAPublicKey;
import mindbright.security.KeyPair;
import mindbright.security.Cipher;
import mindbright.security.SecureRandom;

public abstract class SSH
{
    public static boolean DEBUG;
    public static boolean DEBUGMORE;
    public static final boolean NETSCAPE_SECURITY_MODEL = false;
    public static final int SSH_VER_MAJOR = 1;
    public static final int SSH_VER_MINOR = 5;
    public static final String VER_SSHPKG = "v1.2.1";
    public static final String VER_MINDTERM = "MindTerm v1.2.1";
    public static final String VER_MINDTUNL = "MindTunnel v1.2.1";
    public static final String CVS_NAME = "$Name:  $";
    public static final String CVS_DATE = "$Date: 2001/04/06 17:39:31 $";
    public static final int DEFAULTPORT = 22;
    public static final int SESSION_KEY_LENGTH = 256;
    public static final int SERVER_KEY_LENGTH = 768;
    public static final int HOST_KEY_LENGTH = 1024;
    public static final int PROTOFLAG_SCREEN_NUMBER = 1;
    public static final int PROTOFLAG_HOST_IN_FWD_OPEN = 2;
    public static final int MSG_ANY = -1;
    public static final int MSG_NONE = 0;
    public static final int MSG_DISCONNECT = 1;
    public static final int SMSG_PUBLIC_KEY = 2;
    public static final int CMSG_SESSION_KEY = 3;
    public static final int CMSG_USER = 4;
    public static final int CMSG_AUTH_RHOSTS = 5;
    public static final int CMSG_AUTH_RSA = 6;
    public static final int SMSG_AUTH_RSA_CHALLENGE = 7;
    public static final int CMSG_AUTH_RSA_RESPONSE = 8;
    public static final int CMSG_AUTH_PASSWORD = 9;
    public static final int CMSG_REQUEST_PTY = 10;
    public static final int CMSG_WINDOW_SIZE = 11;
    public static final int CMSG_EXEC_SHELL = 12;
    public static final int CMSG_EXEC_CMD = 13;
    public static final int SMSG_SUCCESS = 14;
    public static final int SMSG_FAILURE = 15;
    public static final int CMSG_STDIN_DATA = 16;
    public static final int SMSG_STDOUT_DATA = 17;
    public static final int SMSG_STDERR_DATA = 18;
    public static final int CMSG_EOF = 19;
    public static final int SMSG_EXITSTATUS = 20;
    public static final int MSG_CHANNEL_OPEN_CONFIRMATION = 21;
    public static final int MSG_CHANNEL_OPEN_FAILURE = 22;
    public static final int MSG_CHANNEL_DATA = 23;
    public static final int MSG_CHANNEL_CLOSE = 24;
    public static final int MSG_CHANNEL_CLOSE_CONFIRMATION = 25;
    public static final int MSG_CHANNEL_INPUT_EOF = 24;
    public static final int MSG_CHANNEL_OUTPUT_CLOSED = 25;
    public static final int SMSG_X11_OPEN = 27;
    public static final int CMSG_PORT_FORWARD_REQUEST = 28;
    public static final int MSG_PORT_OPEN = 29;
    public static final int CMSG_AGENT_REQUEST_FORWARDING = 30;
    public static final int SMSG_AGENT_OPEN = 31;
    public static final int MSG_IGNORE = 32;
    public static final int CMSG_EXIT_CONFIRMATION = 33;
    public static final int CMSG_X11_REQUEST_FORWARDING = 34;
    public static final int CMSG_AUTH_RHOSTS_RSA = 35;
    public static final int MSG_DEBUG = 36;
    public static final int CMSG_REQUEST_COMPRESSION = 37;
    public static final int CMSG_MAX_PACKET_SIZE = 38;
    public static final int CMSG_AUTH_TIS = 39;
    public static final int SMSG_AUTH_TIS_CHALLENGE = 40;
    public static final int CMSG_AUTH_TIS_RESPONSE = 41;
    public static final int CMSG_AUTH_SDI = 16;
    public static final int CMSG_ACM_OK = 64;
    public static final int CMSG_ACM_ACCESS_DENIED = 65;
    public static final int CMSG_ACM_NEXT_CODE_REQUIRED = 66;
    public static final int CMSG_ACM_NEXT_CODE = 67;
    public static final int CMSG_ACM_NEW_PIN_REQUIRED = 68;
    public static final int CMSG_ACM_NEW_PIN_ACCEPTED = 69;
    public static final int CMSG_ACM_NEW_PIN_REJECTED = 70;
    public static final int CMSG_ACM_NEW_PIN = 71;
    public static final int IDX_CIPHER_CLASS = 0;
    public static final int IDX_CIPHER_NAME = 1;
    public static final String[][] cipherClasses;
    public static final int CIPHER_NONE = 0;
    public static final int CIPHER_IDEA = 1;
    public static final int CIPHER_DES = 2;
    public static final int CIPHER_3DES = 3;
    public static final int CIPHER_TSS = 4;
    public static final int CIPHER_RC4 = 5;
    public static final int CIPHER_BLOWFISH = 6;
    public static final int CIPHER_RESERVED = 7;
    public static final int CIPHER_NOTSUPPORTED = 8;
    public static final int CIPHER_DEFAULT = 3;
    public static final String[] authTypeDesc;
    public static final int AUTH_RHOSTS = 1;
    public static final int AUTH_RSA = 2;
    public static final int AUTH_PASSWORD = 3;
    public static final int AUTH_RHOSTS_RSA = 4;
    public static final int AUTH_TIS = 5;
    public static final int AUTH_KERBEROS = 6;
    public static final int PASS_KERBEROS_TGT = 7;
    public static final int AUTH_SDI = 8;
    public static final int AUTH_NOTSUPPORTED;
    public static final int AUTH_DEFAULT = 3;
    static final String[] proxyTypes;
    static final int[] defaultProxyPorts;
    public static final int PROXY_NONE = 0;
    public static final int PROXY_HTTP = 1;
    public static final int PROXY_SOCKS4 = 2;
    public static final int PROXY_SOCKS5_DNS = 3;
    public static final int PROXY_SOCKS5_IP = 4;
    public static final int PROXY_NOTSUPPORTED;
    public static final int TTY_OP_END = 0;
    public static final int TTY_OP_ISPEED = 192;
    public static final int TTY_OP_OSPEED = 193;
    public static final int MAIN_CHAN_NUM = -1;
    public static final int CONNECT_CHAN_NUM = -2;
    public static final int LISTEN_CHAN_NUM = -3;
    public static final int UNKNOWN_CHAN_NUM = -4;
    public static final String KNOWN_HOSTS_FILE = "known_hosts";
    public static final int SRV_HOSTKEY_KNOWN = 0;
    public static final int SRV_HOSTKEY_NEW = 1;
    public static final int SRV_HOSTKEY_CHANGED = 2;
    public static SecureRandom secureRandom;
    protected byte[] sessionKey;
    protected byte[] sessionId;
    protected Cipher sndCipher;
    protected Cipher rcvCipher;
    protected int cipherType;
    protected byte[] srvCookie;
    protected KeyPair srvServerKey;
    protected KeyPair srvHostKey;
    protected int protocolFlags;
    protected int supportedCiphers;
    protected int supportedAuthTypes;
    protected boolean isAnSSHClient;
    
    public SSH() {
        this.isAnSSHClient = true;
    }
    
    public static String getVersionId(final boolean client) {
        String idStr = "SSH-1.5-";
        idStr += (client ? "MindTerm v1.2.1" : "MindTunnel v1.2.1");
        return idStr;
    }
    
    public static String[] getProxyTypes() {
        return SSH.proxyTypes;
    }
    
    public static int getProxyType(final String typeName) throws IllegalArgumentException {
        int i;
        for (i = 0; i < SSH.proxyTypes.length && !SSH.proxyTypes[i].equalsIgnoreCase(typeName); ++i) {}
        if (i == SSH.PROXY_NOTSUPPORTED) {
            throw new IllegalArgumentException("Proxytype " + typeName + " not supported");
        }
        return i;
    }
    
    public static String listSupportedProxyTypes() {
        String list = "";
        for (int i = 0; i < SSH.proxyTypes.length; ++i) {
            list = list + SSH.proxyTypes[i] + " ";
        }
        return list;
    }
    
    public static String getCipherName(final int cipherType) {
        return SSH.cipherClasses[cipherType][1];
    }
    
    public static int getCipherType(final String cipherName) {
        int i = 0;
        while (i < SSH.cipherClasses.length) {
            final String clN = SSH.cipherClasses[i][0];
            final String ciN = SSH.cipherClasses[i][1];
            if (ciN.equalsIgnoreCase(cipherName)) {
                if (SSH.cipherClasses[i][0] == null) {
                    i = SSH.cipherClasses.length;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return i;
    }
    
    public static String getAuthName(final int authType) {
        return SSH.authTypeDesc[authType];
    }
    
    public static int getAuthType(final String authName) throws IllegalArgumentException {
        int i;
        for (i = 1; i < SSH.authTypeDesc.length && !SSH.authTypeDesc[i].equalsIgnoreCase(authName); ++i) {}
        if (i == SSH.AUTH_NOTSUPPORTED) {
            throw new IllegalArgumentException("Authtype " + authName + " not supported");
        }
        return i;
    }
    
    static int cntListSize(final String authList) {
        int cnt = 1;
        int n;
        for (int i = 0; i < authList.length() && (n = authList.indexOf(44, i)) != -1; i = n + 1, ++cnt) {}
        return cnt;
    }
    
    public static int[] getAuthTypes(final String authList) throws IllegalArgumentException {
        final int len = cntListSize(authList);
        final int[] authTypes = new int[len];
        int l = 0;
        for (int i = 0; i < len; ++i) {
            int r = authList.indexOf(44, l);
            if (r == -1) {
                r = authList.length();
            }
            final String type = authList.substring(l, r).trim();
            authTypes[i] = getAuthType(type);
            l = r + 1;
        }
        return authTypes;
    }
    
    public static String listSupportedCiphers() {
        String list = "";
        for (int i = 0; i < SSH.cipherClasses.length; ++i) {
            if (SSH.cipherClasses[i][0] != null) {
                list = list + SSH.cipherClasses[i][1] + " ";
            }
        }
        return list;
    }
    
    public static String[] getCiphers() {
        int n = 0;
        for (int i = 0; i < SSH.cipherClasses.length; ++i) {
            if (SSH.cipherClasses[i][0] != null) {
                ++n;
            }
        }
        final String[] ciphers = new String[n];
        n = 0;
        for (int i = 0; i < SSH.cipherClasses.length; ++i) {
            if (SSH.cipherClasses[i][0] != null) {
                ciphers[n++] = SSH.cipherClasses[i][1];
            }
        }
        return ciphers;
    }
    
    public static String listSupportedAuthTypes() {
        String list = "";
        for (int i = 1; i < SSH.authTypeDesc.length; ++i) {
            list = list + SSH.authTypeDesc[i] + " ";
        }
        return list;
    }
    
    public static String[] getAuthTypeList() {
        final String[] auths = new String[SSH.authTypeDesc.length];
        for (int i = 1; i < SSH.authTypeDesc.length; ++i) {
            auths[i - 1] = SSH.authTypeDesc[i];
        }
        auths[SSH.authTypeDesc.length - 1] = "custom...";
        return auths;
    }
    
    boolean isCipherSupported(final int cipherType) {
        final int cipherMask = 1 << cipherType;
        return (cipherMask & this.supportedCiphers) != 0x0;
    }
    
    boolean isAuthTypeSupported(final int authType) {
        final int authTypeMask = 1 << authType;
        return (authTypeMask & this.supportedAuthTypes) != 0x0;
    }
    
    boolean isProtocolFlagSet(final int protFlag) {
        final int protFlagMask = 1 << protFlag;
        return (protFlagMask & this.protocolFlags) != 0x0;
    }
    
    public static void initSeedGenerator() {
        if (SSH.secureRandom != null) {
            return;
        }
        SSH.secureRandom = new SecureRandom();
    }
    
    public static SecureRandom secureRandom() {
        if (SSH.secureRandom == null) {
            SSH.secureRandom = new SecureRandom();
        }
        return SSH.secureRandom;
    }
    
    public static void log(final String msg) {
        if (SSH.DEBUG) {
            System.out.println(msg);
        }
    }
    
    public static void logExtra(final String msg) {
        if (SSH.DEBUGMORE) {
            System.out.println(msg);
        }
    }
    
    public static void logDebug(final String msg) {
        if (SSH.DEBUG) {
            System.out.println(msg);
        }
    }
    
    public static void logIgnore(final SSHPduInputStream pdu) {
        if (SSH.DEBUG) {
            System.out.println("MSG_IGNORE received...(len = " + pdu.length + ")");
        }
    }
    
    void generateSessionId() throws IOException {
        final byte[] srvKey = ((RSAPublicKey)this.srvServerKey.getPublic()).getN().toByteArray();
        final byte[] hstKey = ((RSAPublicKey)this.srvHostKey.getPublic()).getN().toByteArray();
        int len = srvKey.length + hstKey.length + this.srvCookie.length;
        if (srvKey[0] == 0) {
            --len;
        }
        if (hstKey[0] == 0) {
            --len;
        }
        final byte[] message = new byte[len];
        if (hstKey[0] == 0) {
            System.arraycopy(hstKey, 1, message, 0, hstKey.length - 1);
            len = hstKey.length - 1;
        }
        else {
            System.arraycopy(hstKey, 0, message, 0, hstKey.length);
            len = hstKey.length;
        }
        if (srvKey[0] == 0) {
            System.arraycopy(srvKey, 1, message, len, srvKey.length - 1);
            len += srvKey.length - 1;
        }
        else {
            System.arraycopy(srvKey, 0, message, len, srvKey.length);
            len += srvKey.length;
        }
        System.arraycopy(this.srvCookie, 0, message, len, this.srvCookie.length);
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(message);
            this.sessionId = md5.digest();
        }
        catch (Exception e) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
    }
    
    protected void initClientCipher() throws IOException {
        this.initCipher(false);
    }
    
    protected void initServerCipher() throws IOException {
        this.initCipher(true);
    }
    
    protected void initCipher(final boolean server) throws IOException {
        this.sndCipher = Cipher.getInstance(SSH.cipherClasses[this.cipherType][0]);
        this.rcvCipher = Cipher.getInstance(SSH.cipherClasses[this.cipherType][0]);
        if (this.sndCipher == null) {
            throw new IOException("Cipher " + SSH.cipherClasses[this.cipherType][1] + " not found, can't use it");
        }
        if (this.cipherType == 5) {
            if (server) {
                final int len = this.sessionKey.length / 2;
                final byte[] key = new byte[len];
                System.arraycopy(this.sessionKey, 0, key, 0, len);
                this.sndCipher.setKey(key);
                System.arraycopy(this.sessionKey, len, key, 0, len);
                this.rcvCipher.setKey(key);
            }
            else {
                final int len = this.sessionKey.length / 2;
                final byte[] key = new byte[len];
                System.arraycopy(this.sessionKey, 0, key, 0, len);
                this.rcvCipher.setKey(key);
                System.arraycopy(this.sessionKey, len, key, 0, len);
                this.sndCipher.setKey(key);
            }
        }
        else {
            this.sndCipher.setKey(this.sessionKey);
            this.rcvCipher.setKey(this.sessionKey);
        }
    }
    
    public static String generateKeyFiles(final KeyPair kp, final String fileName, final String passwd, final String comment) throws IOException {
        SSHRSAKeyFile.createKeyFile(kp, passwd, fileName, comment);
        final RSAPublicKey pubKey = (RSAPublicKey)kp.getPublic();
        final SSHRSAPublicKeyString pks = new SSHRSAPublicKeyString("", comment, pubKey.getE(), pubKey.getN());
        pks.toFile(fileName + ".pub");
        return pks.toString();
    }
    
    public static KeyPair generateRSAKeyPair(final int bits, final SecureRandom secRand) {
        final BigInteger one = new BigInteger("1");
        BigInteger p;
        BigInteger q;
        while (true) {
            final int l = SecureRandom.secureLevel;
            SecureRandom.secureLevel = 2;
            p = new BigInteger(bits / 2, 64, secRand);
            q = new BigInteger(bits - bits / 2, 64, secRand);
            SecureRandom.secureLevel = l;
            if (p.compareTo(q) == 0) {
                continue;
            }
            if (q.compareTo(p) < 0) {
                final BigInteger t = q;
                q = p;
                p = t;
            }
            final BigInteger t = p.gcd(q);
            if (t.compareTo(one) != 0) {
                continue;
            }
            break;
        }
        final BigInteger p_1 = p.subtract(one);
        final BigInteger q_1 = q.subtract(one);
        final BigInteger phi = p_1.multiply(q_1);
        final BigInteger G = p_1.gcd(q_1);
        final BigInteger F = phi.divide(G);
        BigInteger e = one.shiftLeft(5);
        e = e.subtract(one);
        BigInteger t;
        do {
            e = e.add(one.add(one));
            t = e.gcd(phi);
        } while (t.compareTo(one) != 0);
        final BigInteger d = e.modInverse(phi);
        final BigInteger n = p.multiply(q);
        final BigInteger u = p.modInverse(q);
        final KeyPair kp = new KeyPair(new RSAPublicKey(e, n), new RSAPrivateKey(e, n, d, u, p, q));
        return kp;
    }
    
    static {
        SSH.DEBUG = false;
        SSH.DEBUGMORE = false;
        cipherClasses = new String[][] { { "NoEncrypt", "none" }, { "IDEA", "idea" }, { "DES", "des" }, { "DES3", "3des" }, { null, "tss" }, { "RC4", "rc4" }, { "Blowfish", "blowfish" }, { null, "reserved" } };
        authTypeDesc = new String[] { "_N/A_", "rhosts", "rsa", "passwd", "rhostsrsa", "tis", "kerberos", "kerbtgt", "sdi-token" };
        AUTH_NOTSUPPORTED = SSH.authTypeDesc.length;
        proxyTypes = new String[] { "none", "http", "socks4", "socks5-proxy-dns", "socks5-local-dns" };
        defaultProxyPorts = new int[] { 0, 8080, 1080, 1080, 1080 };
        PROXY_NOTSUPPORTED = SSH.proxyTypes.length;
    }
}
