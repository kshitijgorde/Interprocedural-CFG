// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.io.IOException;
import java.math.BigInteger;

public class SshPacket2 extends SshPacket
{
    private static final boolean debug = true;
    private byte[] packet_length_array;
    private int packet_length;
    private int padlen;
    private byte[] crc_array;
    private int position;
    private int phase_packet;
    private final int PHASE_packet_length = 0;
    private final int PHASE_block = 1;
    private SshCrypto crypto;
    private byte[] block;
    
    public SshPacket2(final SshCrypto sshCrypto) {
        throw new Error("Unresolved compilation problems: \n\tThe import de cannot be resolved\n\tThe import de cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    public SshPacket2(final byte b) {
        throw new Error("Unresolved compilation problems: \n\tThe import de cannot be resolved\n\tThe import de cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
    
    public BigInteger getMpInt() {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    @Override
    public void putMpInt(final BigInteger bigInteger) {
        throw new Error("Unresolved compilation problem: \n");
    }
    
    public byte[] getPayLoad(final SshCrypto sshCrypto, final long n) throws IOException {
        throw new Error("Unresolved compilation problems: \n\tSshCrypto cannot be resolved to a type\n\tSshMisc cannot be resolved\n");
    }
    
    @Override
    public byte[] addPayload(final byte[] array) {
        throw new Error("Unresolved compilation problems: \n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n\tSshCrypto cannot be resolved to a type\n");
    }
}
