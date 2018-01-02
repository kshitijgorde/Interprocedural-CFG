// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.math.BigInteger;

public class SshPacket1 extends SshPacket
{
    private static final boolean debug = false;
    private byte[] packet_length_array;
    private int packet_length;
    private byte[] padding;
    private byte[] crc_array;
    private byte[] block;
    private byte[] encryptedBlock;
    private byte[] decryptedBlock;
    private SshCrypto crypto;
    private int position;
    private int phase_packet;
    private final int PHASE_packet_length = 0;
    private final int PHASE_block = 1;
    
    public SshPacket1(final SshCrypto sshCrypto) {
        throw new Error("Unresolved compilation problems: \n\tThe import de cannot be resolved\n\tThe import de cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n");
    }
    
    public SshPacket1(final byte b) {
        throw new Error("Unresolved compilation problems: \n\tThe import de cannot be resolved\n\tThe import de cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n");
    }
    
    public byte[] getMpInt() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public void putMpInt(final BigInteger bigInteger) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    byte[] getPayLoad(final SshCrypto sshCrypto) {
        throw new Error("Unresolved compilation problems: \n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshMisc cannot be resolved\n");
    }
    
    @Override
    public byte[] addPayload(final byte[] array) {
        throw new Error("Unresolved compilation problems: \n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    private boolean checkCrc() {
        throw new Error("Unresolved compilation problem: \n\tSshMisc cannot be resolved\n");
    }
}
