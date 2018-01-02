// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import com.stonewall.cornerstone.dm.cli.CLI;

public abstract class SshIO extends CLI
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
    private static String login;
    private static String password;
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
        throw new Error("Unresolved compilation problems: \n\tThe import de cannot be resolved\n\tThe import de cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tThe constructor SshPacket2(SshCrypto) refers to the missing type SshCrypto\n\tThe constructor SshPacket1(SshCrypto) refers to the missing type SshCrypto\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    public void setLogin(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void setPassword(final String s) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    protected abstract void write(final byte[] p0) throws IOException;
    
    public abstract String getTerminalType();
    
    private void write(final byte b) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public void disconnect() {
        throw new Error("Unresolved compilation problem: \n\tSshCrypto cannot be resolved to a type\n");
    }
    
    public synchronized void sendData(final String s) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public byte[] handleSSH(final byte[] array) throws IOException {
        throw new Error("Unresolved compilation problems: \n\tThe constructor SshPacket2(SshCrypto) refers to the missing type SshCrypto\n\tThe constructor SshPacket1(SshCrypto) refers to the missing type SshCrypto\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private String handlePacket2(final SshPacket2 sshPacket2) throws IOException {
        throw new Error("Unresolved compilation problems: \n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private String handlePacket1(final SshPacket1 sshPacket1) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private void sendPacket1(final SshPacket1 sshPacket1) throws IOException {
        throw new Error("Unresolved compilation problem: \n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private void sendPacket2(final SshPacket2 sshPacket2) throws IOException {
        throw new Error("Unresolved compilation problem: \n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private String Send_SSH_CMSG_SESSION_KEY(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final byte[] array5, final byte[] array6) throws IOException {
        throw new Error("Unresolved compilation problems: \n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private String Send_SSH_CMSG_USER() throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String Send_SSH_CMSG_AUTH_PASSWORD() throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    String Send_SSH_CMSG_EXEC_SHELL() throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String Send_SSH_CMSG_STDIN_DATA(final String s) throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String Send_SSH_CMSG_REQUEST_PTY() throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    private String Send_SSH_CMSG_EXIT_CONFIRMATION() throws IOException {
        throw new Error("Unresolved compilation problem: \n");
    }
}
