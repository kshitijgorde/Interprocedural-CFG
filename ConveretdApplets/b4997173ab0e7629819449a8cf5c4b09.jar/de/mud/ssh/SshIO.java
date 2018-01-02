// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.util.Date;
import cryptix.crypt.MD5;
import java.io.IOException;

public abstract class SshIO
{
    private String identification_string;
    private String identification_string_sent;
    private static int debug;
    private boolean encryption;
    private SshCrypto crypto;
    SshPacket lastPacketReceived;
    private String login;
    private String password;
    public String dataToSend;
    public String hashHostKey;
    byte lastPacketSentType;
    private int phase;
    private final int PHASE_INIT = 0;
    private final int PHASE_SSH_RECEIVE_PACKET = 1;
    private final int SSH_MSG_DISCONNECT = 1;
    private final int SSH_SMSG_PUBLIC_KEY = 2;
    private final int SSH_CMSG_SESSION_KEY = 3;
    private final int SSH_CMSG_USER = 4;
    private final int SSH_CMSG_AUTH_PASSWORD = 9;
    private final int SSH_CMSG_REQUEST_PTY = 10;
    private final int SSH_CMSG_EXEC_SHELL = 12;
    private final int SSH_SMSG_SUCCESS = 14;
    private final int SSH_SMSG_FAILURE = 15;
    private final int SSH_CMSG_STDIN_DATA = 16;
    private final int SSH_SMSG_STDOUT_DATA = 17;
    private final int SSH_SMSG_STDERR_DATA = 18;
    private final int SSH_SMSG_EXITSTATUS = 20;
    private final int SSH_CMSG_EXIT_CONFIRMATION = 33;
    private final int SSH_MSG_DEBUG = 36;
    private int position;
    private int SSH_CIPHER_NONE;
    private int SSH_CIPHER_IDEA;
    private int SSH_CIPHER_DES;
    private int SSH_CIPHER_3DES;
    private int SSH_CIPHER_TSS;
    private int SSH_CIPHER_RC4;
    private final int SSH_AUTH_RHOSTS = 1;
    private final int SSH_AUTH_RSA = 2;
    private final int SSH_AUTH_PASSWORD = 3;
    private final int SSH_AUTH_RHOSTS_RSA = 4;
    private boolean cansenddata;
    byte[] one;
    
    public SshIO() {
        this.identification_string = "";
        this.identification_string_sent = "SSH-1.5-Java Ssh 1.1 (16/09/99) leo@mud.de, original by Cedric Gourio (javassh@france-mail.com)\n";
        this.encryption = false;
        this.login = "";
        this.password = "";
        this.dataToSend = null;
        this.hashHostKey = null;
        this.phase = 0;
        this.position = 0;
        this.SSH_CIPHER_NONE = 0;
        this.SSH_CIPHER_IDEA = 1;
        this.SSH_CIPHER_DES = 2;
        this.SSH_CIPHER_3DES = 3;
        this.SSH_CIPHER_TSS = 4;
        this.SSH_CIPHER_RC4 = 5;
        this.cansenddata = false;
        this.one = new byte[1];
        this.encryption = false;
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
    
    public synchronized byte[] handleSSH(final byte[] b) throws IOException {
        byte[] result = this.packetDone(this.handleBytes(b, 0, b.length));
        System.err.println("handleSSH " + b.length + " bytes, " + b.toString() + "");
        while (this.lastPacketReceived != null && this.lastPacketReceived.toBeFinished) {
            final byte[] buff = this.lastPacketReceived.unfinishedBuffer;
            final int start = this.lastPacketReceived.positionInUnfinishedBuffer;
            if (buff != null) {
                final byte[] rest = this.packetDone(this.handleBytes(buff, start, buff.length));
                if (rest == null) {
                    continue;
                }
                if (result != null) {
                    final byte[] tmp = new byte[rest.length + result.length];
                    System.arraycopy(result, 0, tmp, 0, result.length);
                    System.arraycopy(rest, 0, tmp, result.length, rest.length);
                    result = tmp;
                }
                else {
                    result = rest;
                }
            }
        }
        return result;
    }
    
    private byte[] packetDone(final SshPacket packet) throws IOException {
        if (packet == null) {
            return null;
        }
        this.lastPacketReceived = packet;
        final byte[] result = this.handlePacket(this.lastPacketReceived.getType(), this.lastPacketReceived.getData());
        return result;
    }
    
    protected abstract void write(final byte[] p0) throws IOException;
    
    public abstract String getTerminalType();
    
    private void write(final byte b) throws IOException {
        this.one[0] = b;
        this.write(this.one);
    }
    
    public void disconnect() {
        this.login = "";
        this.password = "";
        this.phase = 0;
        this.encryption = false;
        this.lastPacketReceived = null;
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
    
    private SshPacket handleBytes(final byte[] buff, final int offset, final int count) throws IOException {
        if (SshIO.debug > 1) {
            System.out.println("SshIO.getPacket(" + buff + "," + count + ")");
        }
        int boffset = offset;
        while (boffset < count) {
            final byte b = buff[boffset++];
            switch (this.phase) {
                case 0: {
                    this.identification_string += (char)b;
                    if (b == 10) {
                        ++this.phase;
                        this.write(this.identification_string_sent.getBytes());
                        this.position = 0;
                        final byte[] data = SshMisc.createString(this.identification_string);
                        final byte packet_type = 17;
                        return this.createPacket(packet_type, data);
                    }
                    continue;
                }
                case 1: {
                    final SshPacket result = this.lastPacketReceived.getPacketfromBytes(buff, boffset - 1, count, this.encryption, this.crypto);
                    return result;
                }
                default: {
                    continue;
                }
            }
        }
        return null;
    }
    
    private SshPacket createPacket(final byte newType, final byte[] newData) throws IOException {
        return new SshPacket(newType, newData, this.encryption, this.crypto);
    }
    
    private byte[] handlePacket(final byte packetType, final byte[] packetData) throws IOException {
        int boffset = 0;
        if (SshIO.debug > 0) {
            System.out.println("1 packet to handle, type " + packetType);
        }
        switch (packetType) {
            case 1: {
                final String str = SshMisc.getString(boffset, packetData);
                this.disconnect();
                return str.getBytes();
            }
            case 2: {
                final byte[] anti_spoofing_cookie = new byte[8];
                final byte[] server_key_bits = new byte[4];
                final byte[] host_key_bits = new byte[4];
                final byte[] protocol_flags = new byte[4];
                final byte[] supported_ciphers_mask = new byte[4];
                final byte[] supported_authentications_mask = new byte[4];
                for (int i = 0; i <= 7; ++i) {
                    anti_spoofing_cookie[i] = packetData[boffset++];
                }
                for (int j = 0; j <= 3; ++j) {
                    server_key_bits[j] = packetData[boffset++];
                }
                final byte[] server_key_public_exponent = SshMisc.getMpInt(boffset, packetData);
                boffset += server_key_public_exponent.length + 2;
                final byte[] server_key_public_modulus = SshMisc.getMpInt(boffset, packetData);
                boffset += server_key_public_modulus.length + 2;
                for (int k = 0; k <= 3; ++k) {
                    host_key_bits[k] = packetData[boffset++];
                }
                final byte[] host_key_public_exponent = SshMisc.getMpInt(boffset, packetData);
                boffset += host_key_public_exponent.length + 2;
                final byte[] host_key_public_modulus = SshMisc.getMpInt(boffset, packetData);
                boffset += host_key_public_modulus.length + 2;
                for (int l = 0; l < 4; ++l) {
                    protocol_flags[l] = packetData[boffset++];
                }
                for (int m = 0; m < 4; ++m) {
                    supported_ciphers_mask[m] = packetData[boffset++];
                }
                for (int i2 = 0; i2 < 4; ++i2) {
                    supported_authentications_mask[i2] = packetData[boffset++];
                }
                final byte[] ret = this.Send_SSH_CMSG_SESSION_KEY(anti_spoofing_cookie, server_key_public_modulus, host_key_public_modulus, supported_ciphers_mask, server_key_public_exponent, host_key_public_exponent);
                if (ret != null) {
                    return ret;
                }
                if (this.hashHostKey == null || this.hashHostKey.compareTo("") == 0) {
                    break;
                }
                final byte[] Md5_hostKey = MD5.hash(host_key_public_modulus);
                String hashHostKeyBis = "";
                for (int i3 = 0; i3 < Md5_hostKey.length; ++i3) {
                    String hex = "";
                    final int[] v = { (Md5_hostKey[i3] & 0xF0) >> 4, Md5_hostKey[i3] & 0xF };
                    for (int j2 = 0; j2 < 1; ++j2) {
                        switch (v[j2]) {
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
                                hex += String.valueOf(v[j2]);
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
                    return "\nHash value of the host key not correct \r\nlogin & password have been reset \r\n- erase the 'hashHostKey' parameter in the Html\r\n(it is used for auhentificating the server and prevent you from connecting \r\nto any other)\r\n".getBytes();
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
                    return "\nEmpty password login.\r\n".getBytes();
                }
                if (this.lastPacketSentType == 9) {
                    System.out.println("login succesful");
                    this.Send_SSH_CMSG_REQUEST_PTY();
                    return "\nLogin & password accepted\r\n".getBytes();
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
                if (this.lastPacketSentType == 9) {
                    System.out.println("failed to log in");
                    this.disconnect();
                    return "\nLogin & password not accepted\r\n".getBytes();
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
                final String str = SshMisc.getString(0, packetData);
                return str.getBytes();
            }
            case 18: {
                final String str = "Error : " + SshMisc.getString(0, packetData);
                System.out.println("SshIO.handlePacket : STDERR_DATA " + str);
                return str.getBytes();
            }
            case 20: {
                final int value = (packetData[0] << 24) + (packetData[1] << 16) + (packetData[2] << 8) + packetData[3];
                this.Send_SSH_CMSG_EXIT_CONFIRMATION();
                System.out.println("SshIO : Exit status " + value);
                this.disconnect();
                break;
            }
            case 36: {
                final String str = SshMisc.getString(0, packetData);
                if (SshIO.debug > 0) {
                    System.out.println("SshIO.handlePacket :  DEBUG " + str);
                    return str.getBytes();
                }
                return "".getBytes();
            }
            default: {
                System.err.print("SshIO.handlePacket : Packet Type unknown: " + packetType);
                break;
            }
        }
        return null;
    }
    
    private void sendPacket(final SshPacket packet) throws IOException {
        this.write(packet.getBytes());
        this.lastPacketSentType = packet.getType();
    }
    
    private byte[] Send_SSH_CMSG_SESSION_KEY(final byte[] anti_spoofing_cookie, final byte[] server_key_public_modulus, final byte[] host_key_public_modulus, final byte[] supported_ciphers_mask, final byte[] server_key_public_exponent, final byte[] host_key_public_exponent) throws IOException {
        final byte[] session_id_byte = new byte[host_key_public_modulus.length + server_key_public_modulus.length + anti_spoofing_cookie.length];
        System.arraycopy(host_key_public_modulus, 0, session_id_byte, 0, host_key_public_modulus.length);
        System.arraycopy(server_key_public_modulus, 0, session_id_byte, host_key_public_modulus.length, server_key_public_modulus.length);
        System.arraycopy(anti_spoofing_cookie, 0, session_id_byte, host_key_public_modulus.length + server_key_public_modulus.length, anti_spoofing_cookie.length);
        final byte[] hash_md5 = MD5.hash(session_id_byte);
        final byte cipher_type = (byte)this.SSH_CIPHER_IDEA;
        if ((1 << cipher_type & 0xFF & supported_ciphers_mask[3]) == 0x0) {
            System.err.println("SshIO: remote server does not supported IDEA only encryption, support cypher mask is " + supported_ciphers_mask[3] + ".\n");
            this.disconnect();
            return "\rRemote server does not support IDEA Key exchange, closing connection.\r\n".getBytes();
        }
        byte[] random_bits1 = new byte[16];
        byte[] random_bits2 = new byte[16];
        random_bits1 = (random_bits2 = MD5.hash("" + Math.random() * new Date().getTime()));
        random_bits1 = MD5.hash(SshMisc.addArrayOfBytes(MD5.hash(this.password + this.login), random_bits1));
        random_bits2 = MD5.hash(SshMisc.addArrayOfBytes(MD5.hash(this.password + this.login), random_bits2));
        final byte[] session_key = SshMisc.addArrayOfBytes(random_bits1, random_bits2);
        byte[] session_keyXored = SshMisc.XORArrayOfBytes(random_bits1, hash_md5);
        session_keyXored = SshMisc.addArrayOfBytes(session_keyXored, session_keyXored);
        final byte[] encrypted_session_key = SshCrypto.encrypteRSAPkcs1Twice(session_keyXored, server_key_public_exponent, server_key_public_modulus, host_key_public_exponent, host_key_public_modulus);
        final byte[] array;
        final byte[] protocol_flags = array = new byte[4];
        final int n = 0;
        final byte[] array2 = protocol_flags;
        final int n2 = 1;
        final byte[] array3 = protocol_flags;
        final int n3 = 2;
        final byte[] array4 = protocol_flags;
        final int n4 = 3;
        final boolean b = false;
        array3[n3] = (array4[n4] = (byte)(b ? 1 : 0));
        array[n] = (array2[n2] = (byte)(b ? 1 : 0));
        final int length = 1 + anti_spoofing_cookie.length + encrypted_session_key.length + protocol_flags.length;
        final byte[] data = new byte[length];
        int boffset = 0;
        data[boffset++] = cipher_type;
        for (int i = 0; i < 8; ++i) {
            data[boffset++] = anti_spoofing_cookie[i];
        }
        for (int j = 0; j < encrypted_session_key.length; ++j) {
            data[boffset++] = encrypted_session_key[j];
        }
        for (int k = 0; k < 4; ++k) {
            data[boffset++] = protocol_flags[k];
        }
        final byte packet_type = 3;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        if (cipher_type == (byte)this.SSH_CIPHER_IDEA) {
            final byte[] IDEAKey = new byte[16];
            for (int l = 0; l < 16; ++l) {
                IDEAKey[l] = session_key[l];
            }
            this.crypto = new SshCrypto(IDEAKey);
            this.encryption = true;
        }
        return null;
    }
    
    private byte[] Send_SSH_CMSG_USER() throws IOException {
        if (SshIO.debug > 0) {
            System.err.println("Send_SSH_CMSG_USER(" + this.login + ")");
        }
        final byte[] data = SshMisc.createString(this.login);
        final byte packet_type = 4;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        return null;
    }
    
    private byte[] Send_SSH_CMSG_AUTH_PASSWORD() throws IOException {
        final byte[] data = SshMisc.createString(this.password);
        final byte packet_type = 9;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        return null;
    }
    
    private byte[] Send_SSH_CMSG_EXEC_SHELL() throws IOException {
        final byte[] data = null;
        final byte packet_type = 12;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        this.lastPacketSentType = packet_type;
        return null;
    }
    
    private byte[] Send_SSH_CMSG_STDIN_DATA(final String str) throws IOException {
        final byte[] data = SshMisc.createString(str);
        final byte packet_type = 16;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        return null;
    }
    
    private byte[] Send_SSH_CMSG_REQUEST_PTY() throws IOException {
        final byte[] termType = SshMisc.createString(this.getTerminalType());
        final byte[] row = { 0, 0, 0, 24 };
        final byte[] col = { 0, 0, 0, 80 };
        final byte[] XPixels = new byte[4];
        final byte[] YPixels = new byte[4];
        final byte[] terminalModes = { 0 };
        final byte[] data = new byte[termType.length + 16 + terminalModes.length];
        int offset = 0;
        for (int i = 0; i < termType.length; ++i) {
            data[offset++] = termType[i];
        }
        for (int j = 0; j < 4; ++j) {
            data[offset++] = row[j];
        }
        for (int k = 0; k < 4; ++k) {
            data[offset++] = col[k];
        }
        for (int l = 0; l < 4; ++l) {
            data[offset++] = XPixels[l];
        }
        for (int m = 0; m < 4; ++m) {
            data[offset++] = YPixels[m];
        }
        for (int i2 = 0; i2 < terminalModes.length; ++i2) {
            data[offset++] = terminalModes[i2];
        }
        final byte packet_type = 10;
        final SshPacket packet = this.createPacket(packet_type, data);
        this.sendPacket(packet);
        return null;
    }
    
    private byte[] Send_SSH_CMSG_EXIT_CONFIRMATION() throws IOException {
        final byte packet_type = 33;
        final SshPacket packet = this.createPacket(packet_type, null);
        this.sendPacket(packet);
        return null;
    }
    
    static {
        SshIO.debug = 0;
    }
}
