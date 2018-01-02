// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum.snapshot;

import java.io.IOException;
import java.io.RandomAccessFile;
import com.tn.zx.peripheral.Interface1Status;
import com.tn.components.AY38912Status;
import com.tn.zxspectrum.SpectrumSystemStatus;
import com.tn.zx.peripheral.TzxFilePlayerStatus;
import com.tn.zx.peripheral.TapFilePlayerStatus;
import com.tn.zx.peripheral.MicrodriveCartridgeImpl;
import com.tn.zxspectrum.SpectrumULAStatus;
import com.tn.components.MemoryStatus;
import com.tn.z80.Z80Status;
import com.tn.zxspectrum.SpectrumStatus;
import com.tn.util.Array;
import com.tn.components.PD765ASector;
import com.tn.components.PD765ADiskImpl;
import java.util.Vector;

public class SnapshotParser
{
    protected Vector ivComponentStates;
    public static final int FORMAT_UNRECOGNIZED = -1;
    public static final int FORMAT_Z80V145 = 0;
    public static final int FORMAT_Z80V2 = 1;
    public static final int FORMAT_Z80V3 = 2;
    public static final int FORMAT_KGB = 3;
    public static final int FORMAT_MIRAGE = 4;
    public static final int FORMAT_TAP = 5;
    public static final int FORMAT_MDR = 6;
    public static final int FORMAT_TZX = 7;
    public static final int FORMAT_DSK = 8;
    
    public SnapshotParser() {
        this.ivComponentStates = new Vector();
    }
    
    public SnapshotParser(final byte[] aSnapshotBytes) {
        this();
        this.ivComponentStates = this.decodeSnapshot(aSnapshotBytes);
    }
    
    private Vector decodeDsk(final byte[] pSnapshotBytes) {
        try {
            final PD765ADiskImpl disk = new PD765ADiskImpl();
            final int numberOfTracks = pSnapshotBytes[48] & 0xFF;
            final int numberOfSides = pSnapshotBytes[49] & 0xFF;
            int ix = 256;
            for (int c = 0; c < numberOfTracks; ++c) {
                if (pSnapshotBytes[52 + c] > 0 && ix < pSnapshotBytes.length) {
                    for (int h = 0; h < numberOfSides; ++h) {
                        final int numberOfSectors = pSnapshotBytes[ix + 21] & 0xFF;
                        int six = ix + 24;
                        ix += 256;
                        for (int r = 0; r < numberOfSectors; ++r) {
                            final PD765ASector sector = new PD765ASector();
                            sector.setC(pSnapshotBytes[six++] & 0xFF);
                            sector.setH(pSnapshotBytes[six++] & 0xFF);
                            sector.setR(pSnapshotBytes[six++] & 0xFF);
                            sector.setN(pSnapshotBytes[six++] & 0xFF);
                            sector.setSt1(pSnapshotBytes[six++] & 0xFF);
                            sector.setSt2(pSnapshotBytes[six++] & 0xFF);
                            final int sectorSize = Array.toIntLsbf(pSnapshotBytes, six, 2);
                            six += 2;
                            sector.setData(Array.subArray(pSnapshotBytes, ix, sectorSize));
                            ix += sectorSize;
                            disk.setSector(c, h, r, sector);
                        }
                    }
                }
            }
            final Vector result = new Vector();
            result.addElement(disk);
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException("Error in DSK file: " + e);
        }
    }
    
    private Vector decodeKGB(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        final SpectrumStatus spectrumStatus = new SpectrumStatus();
        spectrumStatus.setComponentType(16);
        final Z80Status z80Status = new Z80Status();
        z80Status.setIFF1(pSnapshotBytes[49426] != 0);
        z80Status.setIFF2(pSnapshotBytes[49426] != 0);
        z80Status.setBC(Array.toIntMsbf(pSnapshotBytes, 49434, 2));
        z80Status.setBC2(Array.toIntMsbf(pSnapshotBytes, 49436, 2));
        z80Status.setDE(Array.toIntMsbf(pSnapshotBytes, 49438, 2));
        z80Status.setDE2(Array.toIntMsbf(pSnapshotBytes, 49440, 2));
        z80Status.setHL(Array.toIntMsbf(pSnapshotBytes, 49442, 2));
        z80Status.setHL2(Array.toIntMsbf(pSnapshotBytes, 49444, 2));
        z80Status.setIX(Array.toIntMsbf(pSnapshotBytes, 49446, 2));
        z80Status.setIY(Array.toIntMsbf(pSnapshotBytes, 49448, 2));
        z80Status.setI(Array.toIntMsbf(pSnapshotBytes, 49450, 1));
        z80Status.setR(Array.toIntMsbf(pSnapshotBytes, 49451, 1));
        z80Status.setA2(Array.toIntMsbf(pSnapshotBytes, 49455, 1));
        z80Status.setA(Array.toIntMsbf(pSnapshotBytes, 49457, 1));
        z80Status.setF2(this.srToF(Array.toIntMsbf(pSnapshotBytes, 49459, 1)));
        z80Status.setF(this.srToF(Array.toIntMsbf(pSnapshotBytes, 49461, 1)));
        z80Status.setPC(Array.toIntMsbf(pSnapshotBytes, 49464, 2));
        z80Status.setSP(Array.toIntMsbf(pSnapshotBytes, 49468, 2));
        switch (Array.toIntMsbf(pSnapshotBytes, 49474, 1)) {
            case -1: {
                z80Status.setIntMode(0);
                break;
            }
            case 0: {
                z80Status.setIntMode(1);
                break;
            }
            case 1: {
                z80Status.setIntMode(2);
                break;
            }
            default: {
                throw new RuntimeException("Invalid interruptmode (not -1, 0 or 1!)");
            }
        }
        spectrumStatus.setZ80Status(z80Status);
        spectrumStatus.setMemoryStatus(10, new MemoryStatus(pSnapshotBytes, 132, 16384));
        spectrumStatus.setMemoryStatus(11, new MemoryStatus(pSnapshotBytes, 16516, 16384));
        spectrumStatus.setMemoryStatus(12, new MemoryStatus(pSnapshotBytes, 32900, 16384));
        final SpectrumULAStatus ulaStatus = new SpectrumULAStatus();
        ulaStatus.setPortFE(0);
        ulaStatus.setPort7FFD(0);
        ulaStatus.setCycle(0);
        spectrumStatus.setULAStatus(ulaStatus);
        result.addElement(spectrumStatus);
        return result;
    }
    
    private Vector decodeMdr(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        result.addElement(new MicrodriveCartridgeImpl(pSnapshotBytes));
        return result;
    }
    
    private Vector decodeMirage(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        final SpectrumStatus spectrumStatus = new SpectrumStatus();
        spectrumStatus.setComponentType(16);
        final Z80Status z80Status = new Z80Status();
        z80Status.setI(Array.toIntLsbf(pSnapshotBytes, 0, 1));
        z80Status.setHL2(Array.toIntLsbf(pSnapshotBytes, 1, 2));
        z80Status.setDE2(Array.toIntLsbf(pSnapshotBytes, 3, 2));
        z80Status.setBC2(Array.toIntLsbf(pSnapshotBytes, 5, 2));
        z80Status.setAF2(Array.toIntLsbf(pSnapshotBytes, 7, 2));
        z80Status.setHL(Array.toIntLsbf(pSnapshotBytes, 9, 2));
        z80Status.setDE(Array.toIntLsbf(pSnapshotBytes, 11, 2));
        z80Status.setBC(Array.toIntLsbf(pSnapshotBytes, 13, 2));
        z80Status.setIY(Array.toIntLsbf(pSnapshotBytes, 15, 2));
        z80Status.setIX(Array.toIntLsbf(pSnapshotBytes, 17, 2));
        z80Status.setIFF1((pSnapshotBytes[19] & 0x4) != 0x0);
        z80Status.setIFF2((pSnapshotBytes[19] & 0x4) != 0x0);
        z80Status.setR(Array.toIntLsbf(pSnapshotBytes, 20, 1));
        z80Status.setAF(Array.toIntLsbf(pSnapshotBytes, 21, 2));
        z80Status.setSP(Array.toIntLsbf(pSnapshotBytes, 23, 2));
        z80Status.setIntMode(Array.toIntLsbf(pSnapshotBytes, 25, 1));
        final int sp = z80Status.getSP();
        if (sp < 16384 || sp > 65534) {
            throw new RuntimeException("Snapshot error, SP within ROM area");
        }
        z80Status.setPC((pSnapshotBytes[27 + sp - 16384] & 0xFF) | (pSnapshotBytes[27 + sp - 16383] << 8 & 0xFF00));
        pSnapshotBytes[27 + sp - 16383] = (pSnapshotBytes[27 + sp - 16384] = 0);
        z80Status.setSP(sp + 2 & 0xFFFF);
        spectrumStatus.setZ80Status(z80Status);
        spectrumStatus.setMemoryStatus(10, new MemoryStatus(pSnapshotBytes, 27, 16384));
        spectrumStatus.setMemoryStatus(11, new MemoryStatus(pSnapshotBytes, 16411, 16384));
        spectrumStatus.setMemoryStatus(12, new MemoryStatus(pSnapshotBytes, 32795, 16384));
        final SpectrumULAStatus ulaStatus = new SpectrumULAStatus();
        ulaStatus.setPortFE(pSnapshotBytes[26] | 0xF8);
        ulaStatus.setPort7FFD(0);
        ulaStatus.setCycle(0);
        spectrumStatus.setULAStatus(ulaStatus);
        result.addElement(spectrumStatus);
        return result;
    }
    
    private Vector decodeSnapshot(final byte[] pSnapshotBytes) {
        final int type = getSnapshotType(pSnapshotBytes);
        switch (type) {
            case 3: {
                return this.decodeKGB(pSnapshotBytes);
            }
            case 4: {
                return this.decodeMirage(pSnapshotBytes);
            }
            case 0: {
                return this.decodeZ80V1(pSnapshotBytes);
            }
            case 1: {
                return this.decodeZ80V2V3(pSnapshotBytes);
            }
            case 2: {
                return this.decodeZ80V2V3(pSnapshotBytes);
            }
            case 5: {
                return this.decodeTap(pSnapshotBytes);
            }
            case 6: {
                return this.decodeMdr(pSnapshotBytes);
            }
            case 7: {
                return this.decodeTzx(pSnapshotBytes);
            }
            case 8: {
                return this.decodeDsk(pSnapshotBytes);
            }
            default: {
                throw new RuntimeException("Unrecognized snapshot format");
            }
        }
    }
    
    private Vector decodeTap(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        result.addElement(new TapFilePlayerStatus(pSnapshotBytes, 0, false));
        return result;
    }
    
    private Vector decodeTzx(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        result.addElement(new TzxFilePlayerStatus(pSnapshotBytes));
        return result;
    }
    
    private Vector decodeZ80V1(final byte[] pSnapshotBytes) {
        final Vector result = new Vector();
        final SpectrumStatus spectrumStatus = new SpectrumStatus();
        spectrumStatus.setComponentType(16);
        if (pSnapshotBytes[12] == -1) {
            pSnapshotBytes[12] = 1;
        }
        final Z80Status z80Status = new Z80Status();
        z80Status.setAF(Array.toIntMsbf(pSnapshotBytes, 0, 2));
        z80Status.setBC(Array.toIntLsbf(pSnapshotBytes, 2, 2));
        z80Status.setHL(Array.toIntLsbf(pSnapshotBytes, 4, 2));
        z80Status.setPC(Array.toIntLsbf(pSnapshotBytes, 6, 2));
        z80Status.setSP(Array.toIntLsbf(pSnapshotBytes, 8, 2));
        z80Status.setI(Array.toIntLsbf(pSnapshotBytes, 10, 1));
        z80Status.setR((pSnapshotBytes[11] & 0x7F) | (pSnapshotBytes[12] & 0x1) << 7);
        z80Status.setDE(Array.toIntLsbf(pSnapshotBytes, 13, 2));
        z80Status.setBC2(Array.toIntLsbf(pSnapshotBytes, 15, 2));
        z80Status.setDE2(Array.toIntLsbf(pSnapshotBytes, 17, 2));
        z80Status.setHL2(Array.toIntLsbf(pSnapshotBytes, 19, 2));
        z80Status.setAF2(Array.toIntMsbf(pSnapshotBytes, 21, 2));
        z80Status.setIY(Array.toIntLsbf(pSnapshotBytes, 23, 2));
        z80Status.setIX(Array.toIntLsbf(pSnapshotBytes, 25, 2));
        z80Status.setIFF1(pSnapshotBytes[27] != 0);
        z80Status.setIFF2(pSnapshotBytes[28] != 0);
        z80Status.setIntMode(Array.toIntLsbf(pSnapshotBytes, 29, 1) & 0x3);
        spectrumStatus.setZ80Status(z80Status);
        if ((pSnapshotBytes[12] & 0x20) == 0x0) {
            spectrumStatus.setMemoryStatus(10, new MemoryStatus(pSnapshotBytes, 30, 16384));
            spectrumStatus.setMemoryStatus(11, new MemoryStatus(pSnapshotBytes, 16414, 16384));
            spectrumStatus.setMemoryStatus(12, new MemoryStatus(pSnapshotBytes, 32798, 16384));
        }
        else {
            final byte[] memory = new byte[49152];
            decompressZ80(pSnapshotBytes, 30, pSnapshotBytes.length - 30 - 4, memory, 0, 49152);
            spectrumStatus.setMemoryStatus(10, new MemoryStatus(memory, 0, 16384));
            spectrumStatus.setMemoryStatus(11, new MemoryStatus(memory, 16384, 16384));
            spectrumStatus.setMemoryStatus(12, new MemoryStatus(memory, 32768, 16384));
        }
        final SpectrumULAStatus ulaStatus = new SpectrumULAStatus();
        ulaStatus.setPortFE(pSnapshotBytes[12] >> 1 | 0xF8);
        ulaStatus.setPort7FFD(0);
        ulaStatus.setCycle(0);
        spectrumStatus.setULAStatus(ulaStatus);
        result.addElement(spectrumStatus);
        return result;
    }
    
    private Vector decodeZ80V2V3(final byte[] pSnapshotBytes) {
        final SpectrumSystemStatus systemStatus = new SpectrumSystemStatus();
        if (pSnapshotBytes[12] == -1) {
            pSnapshotBytes[12] = 1;
        }
        int hardware = 0;
        boolean if1 = false;
        if (pSnapshotBytes[30] == 23) {
            switch (pSnapshotBytes[34]) {
                case 0: {
                    hardware = 16;
                    if1 = false;
                    break;
                }
                case 1: {
                    hardware = 16;
                    if1 = true;
                    break;
                }
                case 3: {
                    hardware = 17;
                    if1 = false;
                    break;
                }
                case 4: {
                    hardware = 17;
                    if1 = true;
                    break;
                }
                default: {
                    throw new RuntimeException("Unsupported/Unknown hardware specification in byte 34 (v2.01): " + pSnapshotBytes[34]);
                }
            }
        }
        else {
            switch (pSnapshotBytes[34]) {
                case 0: {
                    hardware = 16;
                    if1 = false;
                    break;
                }
                case 1: {
                    hardware = 16;
                    if1 = true;
                    break;
                }
                case 4: {
                    hardware = 17;
                    if1 = false;
                    break;
                }
                case 5: {
                    hardware = 17;
                    if1 = true;
                    break;
                }
                default: {
                    throw new RuntimeException("Unsupported/Unknown hardware specification in byte 34 (v3): " + pSnapshotBytes[34]);
                }
            }
        }
        final SpectrumStatus spectrumStatus = new SpectrumStatus();
        spectrumStatus.setComponentType(hardware);
        final Z80Status z80Status = new Z80Status();
        z80Status.setAF(Array.toIntMsbf(pSnapshotBytes, 0, 2));
        z80Status.setBC(Array.toIntLsbf(pSnapshotBytes, 2, 2));
        z80Status.setHL(Array.toIntLsbf(pSnapshotBytes, 4, 2));
        z80Status.setSP(Array.toIntLsbf(pSnapshotBytes, 8, 2));
        z80Status.setI(Array.toIntLsbf(pSnapshotBytes, 10, 1));
        z80Status.setR((pSnapshotBytes[11] & 0x7F) | (pSnapshotBytes[12] & 0x1) << 7);
        z80Status.setDE(Array.toIntLsbf(pSnapshotBytes, 13, 2));
        z80Status.setBC2(Array.toIntLsbf(pSnapshotBytes, 15, 2));
        z80Status.setDE2(Array.toIntLsbf(pSnapshotBytes, 17, 2));
        z80Status.setHL2(Array.toIntLsbf(pSnapshotBytes, 19, 2));
        z80Status.setAF2(Array.toIntMsbf(pSnapshotBytes, 21, 2));
        z80Status.setIY(Array.toIntLsbf(pSnapshotBytes, 23, 2));
        z80Status.setIX(Array.toIntLsbf(pSnapshotBytes, 25, 2));
        z80Status.setIFF1(pSnapshotBytes[27] != 0);
        z80Status.setIFF2(pSnapshotBytes[28] != 0);
        z80Status.setIntMode(Array.toIntLsbf(pSnapshotBytes, 29, 1) & 0x3);
        z80Status.setPC(Array.toIntLsbf(pSnapshotBytes, 32, 2));
        spectrumStatus.setZ80Status(z80Status);
        int[] pageTable = null;
        switch (hardware) {
            case 16: {
                pageTable = new int[] { -1, -1, -1, -1, 11, 12, -1, -1, 10, -1, -1, -1 };
                break;
            }
            case 17: {
                pageTable = new int[] { -1, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, -1 };
                break;
            }
            default: {
                throw new RuntimeException("Unknown hardware specification!!!");
            }
        }
        int ptr = 32 + Array.toIntLsbf(pSnapshotBytes, 30, 2);
        while (ptr < pSnapshotBytes.length) {
            final int blockLen = Array.toIntLsbf(pSnapshotBytes, ptr, 2);
            final int blockNbr = Array.toIntLsbf(pSnapshotBytes, ptr + 2, 1);
            ptr += 3;
            if (blockNbr < 0 || blockNbr > 11) {
                throw new RuntimeException("Unknown page: " + blockNbr);
            }
            if (pageTable[blockNbr] == -1) {
                throw new RuntimeException("Unsupported page: " + blockNbr);
            }
            if (blockLen == 65535) {
                spectrumStatus.setMemoryStatus(pageTable[blockNbr], new MemoryStatus(pSnapshotBytes, ptr, 16384));
                ptr += 16384;
            }
            else {
                final byte[] memory = new byte[16384];
                decompressZ80(pSnapshotBytes, ptr, blockLen, memory, 0, 16384);
                spectrumStatus.setMemoryStatus(pageTable[blockNbr], new MemoryStatus(memory));
                ptr += blockLen;
            }
        }
        final SpectrumULAStatus ulaStatus = new SpectrumULAStatus();
        ulaStatus.setPortFE(pSnapshotBytes[12] >> 1 | 0xF8);
        ulaStatus.setPort7FFD(pSnapshotBytes[35] & 0xFF);
        ulaStatus.setCycle(0);
        spectrumStatus.setULAStatus(ulaStatus);
        if (hardware == 17) {
            final AY38912Status ayStatus = new AY38912Status();
            ayStatus.setAPitchLo(pSnapshotBytes[39]);
            ayStatus.setAPitchHi(pSnapshotBytes[40]);
            ayStatus.setBPitchLo(pSnapshotBytes[41]);
            ayStatus.setBPitchHi(pSnapshotBytes[42]);
            ayStatus.setCPitchLo(pSnapshotBytes[43]);
            ayStatus.setCPitchHi(pSnapshotBytes[44]);
            ayStatus.setNoisePitch(pSnapshotBytes[45]);
            ayStatus.setMixerIO(pSnapshotBytes[46]);
            ayStatus.setAVolume(pSnapshotBytes[47]);
            ayStatus.setBVolume(pSnapshotBytes[48]);
            ayStatus.setCVolume(pSnapshotBytes[49]);
            ayStatus.setEnvelopeDurationLo(pSnapshotBytes[50]);
            ayStatus.setEnvelopeDurationHi(pSnapshotBytes[51]);
            ayStatus.setEnvelopeShape(pSnapshotBytes[52]);
            ayStatus.setIOA(pSnapshotBytes[53]);
            ayStatus.setIOB(pSnapshotBytes[54]);
            ayStatus.setLatchedAddress(pSnapshotBytes[38]);
            spectrumStatus.setAY38912Status(ayStatus);
        }
        systemStatus.setSpectrumStatus(spectrumStatus);
        if (if1) {
            final Interface1Status if1Status = new Interface1Status();
            if1Status.setRomActive(pSnapshotBytes[36] == -1);
            systemStatus.setInterface1Status(if1Status);
            for (int i = 0; i < 8; ++i) {}
        }
        final Vector result = new Vector();
        result.addElement(systemStatus);
        return result;
    }
    
    protected static void decompressZ80(final byte[] aSourceData, final int aSourceOffset, final int aSourceLength, final byte[] aTargetData, final int aTargetOffset, final int aTargetLength) {
        int sourceEndIndex;
        int targetEndIndex;
        int s;
        int t;
        for (sourceEndIndex = aSourceOffset + aSourceLength, targetEndIndex = aTargetOffset + aTargetLength, s = aSourceOffset, t = aTargetOffset; s < sourceEndIndex && t < targetEndIndex; aTargetData[t++] = aSourceData[s++]) {
            if (aSourceData[s] == -19 && s < sourceEndIndex - 1) {
                if (aSourceData[s + 1] == -19) {
                    if (s > sourceEndIndex - 4) {
                        throw new IllegalArgumentException("Invalid compression data");
                    }
                    final int count = aSourceData[s + 2] & 0xFF;
                    final byte b = aSourceData[s + 3];
                    s += 4;
                    if (t + count > targetEndIndex) {
                        throw new IllegalArgumentException("Decompressed data larger than expected");
                    }
                    for (int i = 0; i < count; ++i) {
                        aTargetData[t++] = b;
                    }
                }
                else {}
            }
            else {}
        }
        if (s < sourceEndIndex) {
            throw new IllegalArgumentException("Decompressed data larger than expected");
        }
        if (t < targetEndIndex) {
            throw new IllegalArgumentException("Decompressed data smaller than expected");
        }
        if (s > sourceEndIndex || t > targetEndIndex) {
            throw new IllegalArgumentException("Serious screw up in decompression algorithm");
        }
    }
    
    private int fToSr(final int anF) {
        final boolean s = (anF & 0x80) != 0x0;
        final boolean z = (anF & 0x40) != 0x0;
        final boolean v = (anF & 0x4) != 0x0;
        final boolean c = (anF & 0x1) != 0x0;
        return (s ? 8 : 0) | (z ? 4 : 0) | (v ? 2 : 0) | (c ? 1 : 0);
    }
    
    public Vector getComponentStates() {
        return this.ivComponentStates;
    }
    
    public static final int getSnapshotType(final byte[] aSnapshotData) {
        boolean z80v145 = true;
        boolean z80v146 = true;
        boolean z80v147 = true;
        boolean kgb = true;
        boolean mirage = true;
        boolean tap = true;
        boolean mdr = true;
        boolean tzx = true;
        boolean dsk = true;
        final int length = aSnapshotData.length;
        if (length != 49486) {
            kgb = false;
        }
        if (length != 49179) {
            mirage = false;
        }
        if (length < 32) {
            z80v145 = false;
            z80v146 = false;
            z80v147 = false;
        }
        else {
            if ((aSnapshotData[12] & 0x20) != 0x0 && aSnapshotData[12] != -1 && (aSnapshotData[length - 4] != 0 || aSnapshotData[length - 3] != -19 || aSnapshotData[length - 2] != -19 || aSnapshotData[length - 1] != 0)) {
                z80v145 = false;
            }
            if (((aSnapshotData[12] & 0x20) == 0x0 || aSnapshotData[12] == -1) && length != 49182) {
                z80v145 = false;
            }
            if (aSnapshotData[6] != 0 || aSnapshotData[7] != 0) {
                z80v146 = false;
                z80v147 = false;
            }
            if (aSnapshotData[30] != 23 || aSnapshotData[31] != 0) {
                z80v146 = false;
            }
            if (aSnapshotData[30] != 54 || aSnapshotData[31] != 0) {
                z80v147 = false;
            }
        }
        int i;
        for (i = 0; i + 2 <= length; i += 2 + Array.toIntLsbf(aSnapshotData, i, 2)) {}
        if (i != length || i == 0) {
            tap = false;
        }
        if (length != 137923) {
            mdr = false;
        }
        if (length < 8) {
            tzx = false;
        }
        else {
            final byte[] tag = Array.subArray(aSnapshotData, 0, 8);
            if (!Array.b2x(tag).equals("5A5854617065211A")) {
                tzx = false;
            }
        }
        if (length < 256) {
            dsk = false;
        }
        else {
            final byte[] tag = Array.subArray(aSnapshotData, 0, 34);
            if (!Array.b2x(tag).equals("455854454E444544204350432044534B2046696C650D0A4469736B2D496E666F0D0A")) {
                dsk = false;
            }
        }
        if (dsk) {
            return 8;
        }
        if (tzx) {
            return 7;
        }
        if (z80v145) {
            return 0;
        }
        if (z80v146) {
            return 1;
        }
        if (z80v147) {
            return 2;
        }
        if (kgb) {
            return 3;
        }
        if (mirage) {
            return 4;
        }
        if (tap) {
            return 5;
        }
        if (mdr) {
            return 6;
        }
        return -1;
    }
    
    public static void main(final String[] args) throws Exception {
    }
    
    private static byte[] readFile(final String aFileName) throws IOException {
        final RandomAccessFile f = new RandomAccessFile(aFileName, "r");
        final int l = (int)f.length();
        final byte[] b = new byte[l];
        f.readFully(b);
        f.close();
        return b;
    }
    
    private int srToF(final int anSr) {
        final boolean s = (anSr & 0x8) != 0x0;
        final boolean z = (anSr & 0x4) != 0x0;
        final boolean v = (anSr & 0x2) != 0x0;
        final boolean c = (anSr & 0x1) != 0x0;
        return (s ? 128 : 0) | (z ? 64 : 0) | (v ? 4 : 0) | (c ? 1 : 0);
    }
}
