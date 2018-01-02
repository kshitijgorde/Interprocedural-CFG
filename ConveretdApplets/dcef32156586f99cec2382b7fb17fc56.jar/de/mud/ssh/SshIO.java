// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class SshIO
{
    private static MessageDigest md5;
    private String idstr;
    private String idstr_sent;
    private static int debug;
    private SshCrypto crypto;
    String cipher_type;
    private int remotemajor;
    private int remoteminor;
    private int mymajor;
    private int myminor;
    private int useprotocol;
    private String login;
    private String password;
    public String dataToSend;
    public String hashHostKey;
    byte lastPacketSentType;
    private int phase;
    private final int PHASE_INIT = 0;
    private final int PHASE_SSH_RECEIVE_PACKET = 1;
    BigInteger rsa_e;
    BigInteger rsa_n;
    private final byte SSH_MSG_DISCONNECT = 1;
    private final byte SSH_SMSG_PUBLIC_KEY = 2;
    private final byte SSH_CMSG_SESSION_KEY = 3;
    private final byte SSH_CMSG_USER = 4;
    private final byte SSH_CMSG_AUTH_PASSWORD = 9;
    private final byte SSH_CMSG_REQUEST_PTY = 10;
    private final byte SSH_CMSG_WINDOW_SIZE = 11;
    private final byte SSH_CMSG_EXEC_SHELL = 12;
    private final byte SSH_SMSG_SUCCESS = 14;
    private final byte SSH_SMSG_FAILURE = 15;
    private final byte SSH_CMSG_STDIN_DATA = 16;
    private final byte SSH_SMSG_STDOUT_DATA = 17;
    private final byte SSH_SMSG_STDERR_DATA = 18;
    private final byte SSH_SMSG_EXITSTATUS = 20;
    private final byte SSH_MSG_IGNORE = 32;
    private final byte SSH_CMSG_EXIT_CONFIRMATION = 33;
    private final byte SSH_MSG_DEBUG = 36;
    private final byte SSH2_MSG_DISCONNECT = 1;
    private final byte SSH2_MSG_IGNORE = 2;
    private final byte SSH2_MSG_SERVICE_REQUEST = 5;
    private final byte SSH2_MSG_SERVICE_ACCEPT = 6;
    private final byte SSH2_MSG_KEXINIT = 20;
    private final byte SSH2_MSG_NEWKEYS = 21;
    private final byte SSH2_MSG_KEXDH_INIT = 30;
    private final byte SSH2_MSG_KEXDH_REPLY = 31;
    private String kexalgs;
    private String hostkeyalgs;
    private String encalgs2c;
    private String encalgc2s;
    private String macalgs2c;
    private String macalgc2s;
    private String compalgc2s;
    private String compalgs2c;
    private String langc2s;
    private String langs2;
    private int outgoingseq;
    private int incomingseq;
    private int SSH_CIPHER_NONE;
    private int SSH_CIPHER_IDEA;
    private int SSH_CIPHER_DES;
    private int SSH_CIPHER_3DES;
    private int SSH_CIPHER_TSS;
    private int SSH_CIPHER_RC4;
    private int SSH_CIPHER_BLOWFISH;
    private final int SSH_AUTH_RHOSTS = 1;
    private final int SSH_AUTH_RSA = 2;
    private final int SSH_AUTH_PASSWORD = 3;
    private final int SSH_AUTH_RHOSTS_RSA = 4;
    private boolean cansenddata;
    SshPacket currentpacket;
    byte[] one;
    
    public SshIO() {
        this.idstr = "";
        this.idstr_sent = "SSH/JTA (c) Marcus Meissner, Matthias L. Jugel\n";
        this.crypto = null;
        this.cipher_type = "IDEA";
        this.login = "";
        this.password = "";
        this.dataToSend = null;
        this.hashHostKey = null;
        this.phase = 0;
        this.outgoingseq = 0;
        this.incomingseq = 0;
        this.SSH_CIPHER_NONE = 0;
        this.SSH_CIPHER_IDEA = 1;
        this.SSH_CIPHER_DES = 2;
        this.SSH_CIPHER_3DES = 3;
        this.SSH_CIPHER_TSS = 4;
        this.SSH_CIPHER_RC4 = 5;
        this.SSH_CIPHER_BLOWFISH = 6;
        this.cansenddata = false;
        this.one = new byte[1];
        this.crypto = null;
    }
    
    public void setLogin(String user) {
        if (user == null) {
            user = "";
        }
        this.login = user;
    }
    
    public void setPassword(String password) {
        if (password == null) {
            password = "";
        }
        this.password = password;
    }
    
    protected abstract void write(final byte[] p0) throws IOException;
    
    public abstract String getTerminalType();
    
    private void write(final byte b) throws IOException {
        this.one[0] = b;
        this.write(this.one);
    }
    
    public void disconnect() {
        this.idstr = "";
        this.login = "";
        this.password = "";
        this.phase = 0;
        this.crypto = null;
    }
    
    public void setWindowSize(final int columns, final int rows) throws IOException {
        if (this.phase == 0) {
            System.err.println("sshio:setWindowSize(), sizing in init phase not supported.\n");
        }
        if (SshIO.debug > 1) {
            System.err.println("SSHIO:setWindowSize(" + columns + "," + rows + ")");
        }
        this.Send_SSH_CMSG_WINDOW_SIZE(columns, rows);
    }
    
    public synchronized void sendData(final String str) throws IOException {
        if (SshIO.debug > 1) {
            System.out.println("SshIO.send(" + str + ")");
        }
        if (this.dataToSend == null) {
            this.dataToSend = str;
        }
        else {
            this.dataToSend += str;
        }
        if (this.cansenddata) {
            this.Send_SSH_CMSG_STDIN_DATA(this.dataToSend);
            this.dataToSend = null;
        }
    }
    
    public byte[] handleSSH(final byte[] buff) throws IOException {
        if (SshIO.debug > 1) {
            System.out.println("SshIO.getPacket(" + buff + "," + buff.length + ")");
        }
        if (this.phase != 0) {
            String result = "";
            byte[] rest = this.currentpacket.addPayload(buff);
            if (this.currentpacket.isFinished()) {
                if (this.useprotocol == 1) {
                    result += this.handlePacket1((SshPacket1)this.currentpacket);
                    this.currentpacket = new SshPacket1(this.crypto);
                }
                else {
                    result += this.handlePacket2((SshPacket2)this.currentpacket);
                    this.currentpacket = new SshPacket2(this.crypto);
                }
            }
            while (rest != null) {
                rest = this.currentpacket.addPayload(rest);
                if (this.currentpacket.isFinished()) {
                    if (this.useprotocol == 1) {
                        result += this.handlePacket1((SshPacket1)this.currentpacket);
                        this.currentpacket = new SshPacket1(this.crypto);
                    }
                    else {
                        result += this.handlePacket2((SshPacket2)this.currentpacket);
                        this.currentpacket = new SshPacket2(this.crypto);
                    }
                }
            }
            return result.getBytes();
        }
        int boffset = 0;
        while (boffset < buff.length) {
            final byte b = buff[boffset++];
            this.idstr += (char)b;
            if (b == 10) {
                if (!this.idstr.substring(0, 4).equals("SSH-")) {
                    if (SshIO.debug > 0) {
                        System.out.print("Received data line: " + this.idstr);
                    }
                    this.idstr = "";
                }
                else {
                    ++this.phase;
                    this.remotemajor = Integer.parseInt(this.idstr.substring(4, 5));
                    String minorverstr = this.idstr.substring(6, 8);
                    if (!Character.isDigit(minorverstr.charAt(1))) {
                        minorverstr = minorverstr.substring(0, 1);
                    }
                    this.remoteminor = Integer.parseInt(minorverstr);
                    System.out.println("remotemajor " + this.remotemajor);
                    System.out.println("remoteminor " + this.remoteminor);
                    if (this.remotemajor == 2) {
                        this.mymajor = 2;
                        this.myminor = 0;
                        this.useprotocol = 2;
                    }
                    else {
                        this.mymajor = 1;
                        this.myminor = 5;
                        this.useprotocol = 1;
                    }
                    this.idstr_sent = "SSH-" + this.mymajor + "." + this.myminor + "-" + this.idstr_sent;
                    this.write(this.idstr_sent.getBytes());
                    if (this.useprotocol == 2) {
                        this.currentpacket = new SshPacket2(null);
                    }
                    else {
                        this.currentpacket = new SshPacket1(null);
                    }
                }
            }
        }
        if (boffset == buff.length) {
            return "".getBytes();
        }
        return "Must not have left over data after PHASE_INIT!\n".getBytes();
    }
    
    private String handlePacket2(final SshPacket2 p) throws IOException {
        switch (p.getType()) {
            case 2: {
                System.out.println("SSH2: SSH2_MSG_IGNORE");
                break;
            }
            case 1: {
                final int discreason = p.getInt32();
                final String discreason2 = p.getString();
                System.out.println("SSH2: SSH2_MSG_DISCONNECT(" + discreason + "," + discreason2 + "," + ")");
                return "\nSSH2 disconnect: " + discreason2 + "\n";
            }
            case 21: {
                System.out.println("SSH2: SSH2_MSG_NEWKEYS");
                this.sendPacket2(new SshPacket2((byte)21));
                final byte[] session_key = new byte[16];
                this.crypto = new SshCrypto(this.cipher_type, session_key);
                final SshPacket2 pn = new SshPacket2((byte)5);
                pn.putString("ssh-userauth");
                this.sendPacket2(pn);
                break;
            }
            case 6: {
                System.out.println("Service Accept: " + p.getString());
                break;
            }
            case 20: {
                System.out.println("SSH2: SSH2_MSG_KEXINIT");
                final byte[] kexcookie = p.getBytes(16);
                final String kexalgs = p.getString();
                System.out.println("- " + kexalgs);
                final String hostkeyalgs = p.getString();
                System.out.println("- " + hostkeyalgs);
                final String encalgc2s = p.getString();
                System.out.println("- " + encalgc2s);
                final String encalgs2c = p.getString();
                System.out.println("- " + encalgs2c);
                final String macalgc2s = p.getString();
                System.out.println("- " + macalgc2s);
                final String macalgs2c = p.getString();
                System.out.println("- " + macalgs2c);
                final String compalgc2s = p.getString();
                System.out.println("- " + compalgc2s);
                final String compalgs2c = p.getString();
                System.out.println("- " + compalgs2c);
                final String langc2s = p.getString();
                System.out.println("- " + langc2s);
                final String langs2c = p.getString();
                System.out.println("- " + langs2c);
                final byte[] fupp = p.getBytes(1);
                System.out.println("- first_kex_follows: " + fupp[0]);
                SshPacket2 pn2 = new SshPacket2((byte)20);
                final byte[] kexsend = new byte[16];
                pn2.putBytes(kexsend);
                pn2.putString("diffie-hellman-group1-sha1");
                pn2.putString("ssh-rsa");
                this.cipher_type = "NONE";
                final String ciphername = "none";
                pn2.putString("none");
                pn2.putString("none");
                pn2.putString("hmac-md5");
                pn2.putString("hmac-md5");
                pn2.putString("none");
                pn2.putString("none");
                pn2.putString("");
                pn2.putString("");
                pn2.putByte((byte)0);
                pn2.putInt32(0);
                this.sendPacket2(pn2);
                pn2 = new SshPacket2((byte)30);
                pn2.putMpInt(BigInteger.valueOf(-559038737L));
                this.sendPacket2(pn2);
                break;
            }
            case 31: {
                System.out.println("SSH2_MSG_KEXDH_REPLY");
                final int bloblen = p.getInt32();
                System.out.println("bloblen is " + bloblen);
                final String keytype = p.getString();
                System.out.println("KEXDH: " + keytype);
                if (keytype.equals("ssh-rsa")) {
                    this.rsa_e = p.getMpInt();
                    this.rsa_n = p.getMpInt();
                    String result = "\n\rSSH-RSA (" + this.rsa_n + "," + this.rsa_e + ")\n\r";
                    final BigInteger dhserverpub = p.getMpInt();
                    result = result + "DH Server Pub: " + dhserverpub + "\n\r";
                    final int siglen = p.getInt32();
                    final String sigstr = p.getString();
                    result = result + "Signature: ktype is " + sigstr + "\r\n";
                    final byte[] sigdata = p.getBytes(p.getInt32());
                    return result;
                }
                return "\n\rUnsupported kexdh algorithm " + keytype + "!\n\r";
            }
            default: {
                return "SSH2: handlePacket2 Unknown type " + p.getType();
            }
        }
        return "";
    }
    
    private String handlePacket1(final SshPacket1 p) throws IOException {
        if (SshIO.debug > 0) {
            System.out.println("1 packet to handle, type " + p.getType());
        }
        switch (p.getType()) {
            case 32: {
                return "";
            }
            case 1: {
                final String str = p.getString();
                this.disconnect();
                return str;
            }
            case 2: {
                final byte[] anti_spoofing_cookie = p.getBytes(8);
                final byte[] server_key_bits = p.getBytes(4);
                final byte[] server_key_public_exponent = p.getMpInt();
                final byte[] server_key_public_modulus = p.getMpInt();
                final byte[] host_key_bits = p.getBytes(4);
                final byte[] host_key_public_exponent = p.getMpInt();
                final byte[] host_key_public_modulus = p.getMpInt();
                final byte[] protocol_flags = p.getBytes(4);
                final byte[] supported_ciphers_mask = p.getBytes(4);
                final byte[] supported_authentications_mask = p.getBytes(4);
                final String ret = this.Send_SSH_CMSG_SESSION_KEY(anti_spoofing_cookie, server_key_public_modulus, host_key_public_modulus, supported_ciphers_mask, server_key_public_exponent, host_key_public_exponent);
                if (ret != null) {
                    return ret;
                }
                if (this.hashHostKey == null || this.hashHostKey.compareTo("") == 0) {
                    break;
                }
                final byte[] Md5_hostKey = SshIO.md5.digest(host_key_public_modulus);
                String hashHostKeyBis = "";
                for (int i = 0; i < Md5_hostKey.length; ++i) {
                    String hex = "";
                    final int[] v = { (Md5_hostKey[i] & 0xF0) >> 4, Md5_hostKey[i] & 0xF };
                    for (int j = 0; j < 1; ++j) {
                        switch (v[j]) {
                            case 10: {
                                hex += "a";
                                break;
                            }
                            case 11: {
                                hex += "b";
                                break;
                            }
                            case 12: {
                                hex += "c";
                                break;
                            }
                            case 13: {
                                hex += "d";
                                break;
                            }
                            case 14: {
                                hex += "e";
                                break;
                            }
                            case 15: {
                                hex += "f";
                                break;
                            }
                            default: {
                                hex += String.valueOf(v[j]);
                                break;
                            }
                        }
                    }
                    hashHostKeyBis += hex;
                }
                if (hashHostKeyBis.compareTo(this.hashHostKey) != 0) {
                    final String s = "";
                    this.password = s;
                    this.login = s;
                    return "\nHash value of the host key not correct \r\nlogin & password have been reset \r\n- erase the 'hashHostKey' parameter in the Html\r\n(it is used for auhentificating the server and prevent you from connecting \r\nto any other)\r\n";
                }
                break;
            }
            case 14: {
                if (SshIO.debug > 0) {
                    System.out.println("SSH_SMSG_SUCCESS (last packet was " + this.lastPacketSentType + ")");
                }
                if (this.lastPacketSentType == 3) {
                    this.Send_SSH_CMSG_USER();
                    break;
                }
                if (this.lastPacketSentType == 4) {
                    this.Send_SSH_CMSG_REQUEST_PTY();
                    return "\nEmpty password login.\r\n";
                }
                if (this.lastPacketSentType == 9) {
                    if (SshIO.debug > 0) {
                        System.out.println("login succesful");
                    }
                    this.Send_SSH_CMSG_REQUEST_PTY();
                    return "\nLogin & password accepted\r\n";
                }
                if (this.lastPacketSentType == 10) {
                    this.cansenddata = true;
                    if (this.dataToSend != null) {
                        this.Send_SSH_CMSG_STDIN_DATA(this.dataToSend);
                        this.dataToSend = null;
                    }
                    this.Send_SSH_CMSG_EXEC_SHELL();
                    break;
                }
                if (this.lastPacketSentType == 12) {
                    break;
                }
                break;
            }
            case 15: {
                if (SshIO.debug > 1) {
                    System.err.println("SSH_SMSG_FAILURE");
                }
                if (this.lastPacketSentType == 9) {
                    System.out.println("failed to log in");
                    this.Send_SSH_MSG_DISCONNECT("Failed to log in.");
                    this.disconnect();
                    return "\nLogin & password not accepted\r\n";
                }
                if (this.lastPacketSentType == 4) {
                    this.Send_SSH_CMSG_AUTH_PASSWORD();
                    break;
                }
                if (this.lastPacketSentType == 10) {
                    break;
                }
                break;
            }
            case 17: {
                return p.getString();
            }
            case 18: {
                final String str = "Error : " + p.getString();
                System.out.println("SshIO.handlePacket : STDERR_DATA " + str);
                return str;
            }
            case 20: {
                final int value = p.getInt32();
                this.Send_SSH_CMSG_EXIT_CONFIRMATION();
                System.out.println("SshIO : Exit status " + value);
                this.disconnect();
                break;
            }
            case 36: {
                final String str = p.getString();
                if (SshIO.debug > 0) {
                    System.out.println("SshIO.handlePacket :  DEBUG " + str);
                    return str;
                }
                return "";
            }
            default: {
                System.err.print("SshIO.handlePacket1: Packet Type unknown: " + p.getType());
                break;
            }
        }
        return "";
    }
    
    private void sendPacket1(final SshPacket1 packet) throws IOException {
        this.write(packet.getPayLoad(this.crypto));
        this.lastPacketSentType = packet.getType();
    }
    
    private void sendPacket2(final SshPacket2 packet) throws IOException {
        this.write(packet.getPayLoad(this.crypto, this.outgoingseq));
        ++this.outgoingseq;
        this.lastPacketSentType = packet.getType();
    }
    
    private String Send_SSH_CMSG_SESSION_KEY(final byte[] anti_spoofing_cookie, final byte[] server_key_public_modulus, final byte[] host_key_public_modulus, final byte[] supported_ciphers_mask, final byte[] server_key_public_exponent, final byte[] host_key_public_exponent) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3         /* host_key_public_modulus */
        //     1: arraylength    
        //     2: aload_2         /* server_key_public_modulus */
        //     3: arraylength    
        //     4: iadd           
        //     5: aload_1         /* anti_spoofing_cookie */
        //     6: arraylength    
        //     7: iadd           
        //     8: newarray        B
        //    10: astore          session_id_byte
        //    12: aload_3         /* host_key_public_modulus */
        //    13: iconst_0       
        //    14: aload           session_id_byte
        //    16: iconst_0       
        //    17: aload_3         /* host_key_public_modulus */
        //    18: arraylength    
        //    19: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    22: aload_2         /* server_key_public_modulus */
        //    23: iconst_0       
        //    24: aload           session_id_byte
        //    26: aload_3         /* host_key_public_modulus */
        //    27: arraylength    
        //    28: aload_2         /* server_key_public_modulus */
        //    29: arraylength    
        //    30: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    33: aload_1         /* anti_spoofing_cookie */
        //    34: iconst_0       
        //    35: aload           session_id_byte
        //    37: aload_3         /* host_key_public_modulus */
        //    38: arraylength    
        //    39: aload_2         /* server_key_public_modulus */
        //    40: arraylength    
        //    41: iadd           
        //    42: aload_1         /* anti_spoofing_cookie */
        //    43: arraylength    
        //    44: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //    47: getstatic       de/mud/ssh/SshIO.md5:Ljava/security/MessageDigest;
        //    50: aload           session_id_byte
        //    52: invokevirtual   java/security/MessageDigest.digest:([B)[B
        //    55: astore          hash_md5
        //    57: aload           supported_ciphers_mask
        //    59: iconst_3       
        //    60: baload         
        //    61: iconst_1       
        //    62: aload_0         /* this */
        //    63: getfield        de/mud/ssh/SshIO.SSH_CIPHER_BLOWFISH:I
        //    66: ishl           
        //    67: i2b            
        //    68: iand           
        //    69: ifeq            88
        //    72: aload_0         /* this */
        //    73: getfield        de/mud/ssh/SshIO.SSH_CIPHER_BLOWFISH:I
        //    76: i2b            
        //    77: istore          cipher_types
        //    79: aload_0         /* this */
        //    80: ldc             "Blowfish"
        //    82: putfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //    85: goto            225
        //    88: aload           supported_ciphers_mask
        //    90: iconst_3       
        //    91: baload         
        //    92: iconst_1       
        //    93: aload_0         /* this */
        //    94: getfield        de/mud/ssh/SshIO.SSH_CIPHER_IDEA:I
        //    97: ishl           
        //    98: iand           
        //    99: ifeq            118
        //   102: aload_0         /* this */
        //   103: getfield        de/mud/ssh/SshIO.SSH_CIPHER_IDEA:I
        //   106: i2b            
        //   107: istore          cipher_types
        //   109: aload_0         /* this */
        //   110: ldc             "IDEA"
        //   112: putfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //   115: goto            225
        //   118: aload           supported_ciphers_mask
        //   120: iconst_3       
        //   121: baload         
        //   122: iconst_1       
        //   123: aload_0         /* this */
        //   124: getfield        de/mud/ssh/SshIO.SSH_CIPHER_3DES:I
        //   127: ishl           
        //   128: iand           
        //   129: ifeq            148
        //   132: aload_0         /* this */
        //   133: getfield        de/mud/ssh/SshIO.SSH_CIPHER_3DES:I
        //   136: i2b            
        //   137: istore          cipher_types
        //   139: aload_0         /* this */
        //   140: ldc             "DES3"
        //   142: putfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //   145: goto            225
        //   148: aload           supported_ciphers_mask
        //   150: iconst_3       
        //   151: baload         
        //   152: iconst_1       
        //   153: aload_0         /* this */
        //   154: getfield        de/mud/ssh/SshIO.SSH_CIPHER_DES:I
        //   157: ishl           
        //   158: iand           
        //   159: ifeq            178
        //   162: aload_0         /* this */
        //   163: getfield        de/mud/ssh/SshIO.SSH_CIPHER_DES:I
        //   166: i2b            
        //   167: istore          cipher_types
        //   169: aload_0         /* this */
        //   170: ldc             "DES"
        //   172: putfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //   175: goto            225
        //   178: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   181: new             Ljava/lang/StringBuffer;
        //   184: dup            
        //   185: invokespecial   java/lang/StringBuffer.<init>:()V
        //   188: ldc             "SshIO: remote server does not supported IDEA, BlowFish or 3DES, support cypher mask is "
        //   190: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   193: aload           supported_ciphers_mask
        //   195: iconst_3       
        //   196: baload         
        //   197: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   200: ldc             ".\n"
        //   202: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   205: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   208: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   211: aload_0         /* this */
        //   212: ldc             "No more auth methods available."
        //   214: invokespecial   de/mud/ssh/SshIO.Send_SSH_MSG_DISCONNECT:(Ljava/lang/String;)Ljava/lang/String;
        //   217: pop            
        //   218: aload_0         /* this */
        //   219: invokevirtual   de/mud/ssh/SshIO.disconnect:()V
        //   222: ldc             "\rRemote server does not support IDEA/Blowfish/3DES blockcipher, closing connection.\r\n"
        //   224: areturn        
        //   225: getstatic       de/mud/ssh/SshIO.debug:I
        //   228: ifle            264
        //   231: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   234: new             Ljava/lang/StringBuffer;
        //   237: dup            
        //   238: invokespecial   java/lang/StringBuffer.<init>:()V
        //   241: ldc             "SshIO: Using "
        //   243: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   246: aload_0         /* this */
        //   247: getfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //   250: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   253: ldc             " blockcipher.\n"
        //   255: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   258: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   261: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   264: bipush          16
        //   266: newarray        B
        //   268: astore          random_bits1
        //   270: bipush          16
        //   272: newarray        B
        //   274: astore          random_bits2
        //   276: new             Ljava/security/SecureRandom;
        //   279: dup            
        //   280: aload           random_bits1
        //   282: invokespecial   java/security/SecureRandom.<init>:([B)V
        //   285: astore          random
        //   287: aload           random
        //   289: aload           random_bits1
        //   291: invokevirtual   java/security/SecureRandom.nextBytes:([B)V
        //   294: aload           random
        //   296: aload           random_bits2
        //   298: invokevirtual   java/security/SecureRandom.nextBytes:([B)V
        //   301: aload           random_bits1
        //   303: aload           random_bits2
        //   305: invokestatic    de/mud/ssh/SshMisc.addArrayOfBytes:([B[B)[B
        //   308: astore          session_key
        //   310: aload           random_bits1
        //   312: aload           hash_md5
        //   314: invokestatic    de/mud/ssh/SshMisc.XORArrayOfBytes:([B[B)[B
        //   317: astore          session_keyXored
        //   319: aload           session_keyXored
        //   321: aload           random_bits2
        //   323: invokestatic    de/mud/ssh/SshMisc.addArrayOfBytes:([B[B)[B
        //   326: astore          session_keyXored
        //   328: aload           session_keyXored
        //   330: aload           server_key_public_exponent
        //   332: aload_2         /* server_key_public_modulus */
        //   333: aload           host_key_public_exponent
        //   335: aload_3         /* host_key_public_modulus */
        //   336: invokestatic    de/mud/ssh/SshCrypto.encrypteRSAPkcs1Twice:([B[B[B[B[B)[B
        //   339: astore          encrypted_session_key
        //   341: iconst_0       
        //   342: istore          protocol_flags
        //   344: new             Lde/mud/ssh/SshPacket1;
        //   347: dup            
        //   348: iconst_3       
        //   349: invokespecial   de/mud/ssh/SshPacket1.<init>:(B)V
        //   352: astore          packet
        //   354: aload           packet
        //   356: iload           9
        //   358: invokevirtual   de/mud/ssh/SshPacket1.putByte:(B)V
        //   361: aload           packet
        //   363: aload_1         /* anti_spoofing_cookie */
        //   364: invokevirtual   de/mud/ssh/SshPacket1.putBytes:([B)V
        //   367: aload           packet
        //   369: aload           encrypted_session_key
        //   371: invokevirtual   de/mud/ssh/SshPacket1.putBytes:([B)V
        //   374: aload           packet
        //   376: iload           protocol_flags
        //   378: invokevirtual   de/mud/ssh/SshPacket1.putInt32:(I)V
        //   381: aload_0         /* this */
        //   382: aload           packet
        //   384: invokespecial   de/mud/ssh/SshIO.sendPacket1:(Lde/mud/ssh/SshPacket1;)V
        //   387: aload_0         /* this */
        //   388: new             Lde/mud/ssh/SshCrypto;
        //   391: dup            
        //   392: aload_0         /* this */
        //   393: getfield        de/mud/ssh/SshIO.cipher_type:Ljava/lang/String;
        //   396: aload           session_key
        //   398: invokespecial   de/mud/ssh/SshCrypto.<init>:(Ljava/lang/String;[B)V
        //   401: putfield        de/mud/ssh/SshIO.crypto:Lde/mud/ssh/SshCrypto;
        //   404: ldc             ""
        //   406: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                        Signature
        //  -----  ------  ----  --------------------------  ----------------------------
        //  79     9       9     cipher_types                B
        //  109    9       9     cipher_types                B
        //  139    9       9     cipher_types                B
        //  169    9       9     cipher_types                B
        //  0      407     0     this                        Lde/mud/ssh/SshIO;
        //  0      407     1     anti_spoofing_cookie        [B
        //  0      407     2     server_key_public_modulus   [B
        //  0      407     3     host_key_public_modulus     [B
        //  0      407     4     supported_ciphers_mask      [B
        //  0      407     5     server_key_public_exponent  [B
        //  0      407     6     host_key_public_exponent    [B
        //  310    97      10    session_key                 [B
        //  12     395     11    session_id_byte             [B
        //  57     350     12    hash_md5                    [B
        //  270    137     13    random_bits1                [B
        //  276    131     14    random_bits2                [B
        //  287    120     15    random                      Ljava/security/SecureRandom;
        //  319    88      16    session_keyXored            [B
        //  341    66      17    encrypted_session_key       [B
        //  344    63      18    protocol_flags              I
        //  354    53      19    packet                      Lde/mud/ssh/SshPacket1;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private String Send_SSH_MSG_DISCONNECT(final String reason) throws IOException {
        final SshPacket1 p = new SshPacket1((byte)1);
        p.putString(reason);
        this.sendPacket1(p);
        return "";
    }
    
    private String Send_SSH_CMSG_USER() throws IOException {
        if (SshIO.debug > 0) {
            System.err.println("Send_SSH_CMSG_USER(" + this.login + ")");
        }
        final SshPacket1 p = new SshPacket1((byte)4);
        p.putString(this.login);
        this.sendPacket1(p);
        return "";
    }
    
    private String Send_SSH_CMSG_AUTH_PASSWORD() throws IOException {
        final SshPacket1 p = new SshPacket1((byte)9);
        p.putString(this.password);
        this.sendPacket1(p);
        return "";
    }
    
    private String Send_SSH_CMSG_EXEC_SHELL() throws IOException {
        final SshPacket1 packet = new SshPacket1((byte)12);
        this.sendPacket1(packet);
        return "";
    }
    
    private String Send_SSH_CMSG_STDIN_DATA(final String str) throws IOException {
        final SshPacket1 packet = new SshPacket1((byte)16);
        packet.putString(str);
        this.sendPacket1(packet);
        return "";
    }
    
    private String Send_SSH_CMSG_WINDOW_SIZE(final int c, final int r) throws IOException {
        final SshPacket1 p = new SshPacket1((byte)11);
        p.putInt32(r);
        p.putInt32(c);
        p.putInt32(0);
        p.putInt32(0);
        this.sendPacket1(p);
        return "";
    }
    
    private String Send_SSH_CMSG_REQUEST_PTY() throws IOException {
        final SshPacket1 p = new SshPacket1((byte)10);
        p.putString(this.getTerminalType());
        p.putInt32(24);
        p.putInt32(80);
        p.putInt32(0);
        p.putInt32(0);
        p.putByte((byte)0);
        this.sendPacket1(p);
        return "";
    }
    
    private String Send_SSH_CMSG_EXIT_CONFIRMATION() throws IOException {
        final SshPacket1 packet = new SshPacket1((byte)33);
        this.sendPacket1(packet);
        return "";
    }
    
    static {
        try {
            SshIO.md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("SshIO: unable to load message digest algorithm: " + e);
            e.printStackTrace();
        }
        SshIO.debug = 0;
    }
}
