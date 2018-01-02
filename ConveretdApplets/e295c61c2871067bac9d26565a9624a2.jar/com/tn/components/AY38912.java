// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public class AY38912
{
    private int ivTCA;
    private int ivTCB;
    private int ivTCC;
    private int ivNPC;
    private int ivMixerIO;
    private int ivAmpA;
    private int ivAmpB;
    private int ivAmpC;
    private int ivEnvPC;
    private int ivEnvC;
    private int ivIO1;
    private int ivIO2;
    private int ivAddress;
    private int ivCountA;
    private int ivCountB;
    private int ivCountC;
    private int ivCountN;
    private int ivCountEnv;
    private int ivNNNCBA;
    private int ivEnv;
    private byte[] ivNoiseTable;
    private int ivNoiseIndex;
    private int[][] ivEnvelopeTables;
    private int ivEnvelopeIndex;
    private boolean ivClockDivider;
    
    public AY38912() {
        (this.ivEnvelopeTables = new int[16][])[0] = new int[] { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        this.ivEnvelopeTables[1] = this.ivEnvelopeTables[0];
        this.ivEnvelopeTables[2] = this.ivEnvelopeTables[0];
        this.ivEnvelopeTables[3] = this.ivEnvelopeTables[0];
        this.ivEnvelopeTables[4] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };
        this.ivEnvelopeTables[5] = this.ivEnvelopeTables[4];
        this.ivEnvelopeTables[6] = this.ivEnvelopeTables[4];
        this.ivEnvelopeTables[7] = this.ivEnvelopeTables[4];
        this.ivEnvelopeTables[8] = new int[] { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1 };
        this.ivEnvelopeTables[9] = this.ivEnvelopeTables[0];
        this.ivEnvelopeTables[10] = new int[] { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1 };
        this.ivEnvelopeTables[11] = new int[] { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 15 };
        this.ivEnvelopeTables[12] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1 };
        this.ivEnvelopeTables[13] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        this.ivEnvelopeTables[14] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1 };
        this.ivEnvelopeTables[15] = this.ivEnvelopeTables[4];
        this.ivNoiseTable = new byte[16384];
        for (int i = 0; i < 16384; ++i) {
            if (Math.random() >= 0.5) {
                this.ivNoiseTable[i] = 56;
            }
        }
    }
    
    public int getOutputABC() {
        int booleanOutput = this.ivNNNCBA | this.ivMixerIO;
        booleanOutput &= booleanOutput >> 3;
        int total = 0;
        if ((booleanOutput & 0x1) == 0x1) {
            total += (((this.ivAmpA & 0x10) == 0x0) ? (this.ivAmpA & 0xF) : this.ivEnv);
        }
        if ((booleanOutput & 0x2) == 0x2) {
            total += (((this.ivAmpB & 0x10) == 0x0) ? (this.ivAmpB & 0xF) : this.ivEnv);
        }
        if ((booleanOutput & 0x4) == 0x4) {
            total += (((this.ivAmpC & 0x10) == 0x0) ? (this.ivAmpC & 0xF) : this.ivEnv);
        }
        return total;
    }
    
    private int getRegister(final int pAddress) {
        this.latchAddress(pAddress);
        return this.readData();
    }
    
    public AY38912Status getStatus() {
        final AY38912Status result = new AY38912Status();
        result.setLatchedAddress(this.ivAddress);
        result.setAPitchLo(this.getRegister(0));
        result.setAPitchHi(this.getRegister(1));
        result.setBPitchLo(this.getRegister(2));
        result.setBPitchHi(this.getRegister(3));
        result.setCPitchLo(this.getRegister(4));
        result.setCPitchHi(this.getRegister(5));
        result.setNoisePitch(this.getRegister(6));
        result.setMixerIO(this.getRegister(7));
        result.setAVolume(this.getRegister(8));
        result.setBVolume(this.getRegister(9));
        result.setCVolume(this.getRegister(10));
        result.setEnvelopeDurationLo(this.getRegister(11));
        result.setEnvelopeDurationHi(this.getRegister(12));
        result.setEnvelopeShape(this.getRegister(13));
        result.setIOA(this.getRegister(14));
        result.setIOB(this.getRegister(15));
        this.latchAddress(result.getLatchedAddress());
        return result;
    }
    
    public void init() {
        this.reset();
    }
    
    public void latchAddress(final int pAddress) {
        this.ivAddress = (pAddress & 0xF);
    }
    
    public int readData() {
        switch (this.ivAddress) {
            case 0: {
                return this.ivTCA & 0xFF;
            }
            case 1: {
                return (this.ivTCA & 0xF00) >>> 8;
            }
            case 2: {
                return this.ivTCB & 0xFF;
            }
            case 3: {
                return (this.ivTCB & 0xF00) >>> 8;
            }
            case 4: {
                return this.ivTCC & 0xFF;
            }
            case 5: {
                return (this.ivTCC & 0xF00) >>> 8;
            }
            case 6: {
                return this.ivNPC & 0x1F;
            }
            case 7: {
                return this.ivMixerIO & 0xFF;
            }
            case 8: {
                return this.ivAmpA & 0x1F;
            }
            case 9: {
                return this.ivAmpB & 0x1F;
            }
            case 10: {
                return this.ivAmpC & 0x1F;
            }
            case 11: {
                return this.ivEnvPC & 0xFF;
            }
            case 12: {
                return (this.ivEnvPC & 0xFF00) >>> 8;
            }
            case 13: {
                return this.ivEnvC & 0xF;
            }
            case 14: {
                return 0;
            }
            case 15: {
                return 0;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void reset() {
        this.ivTCA = 0;
        this.ivTCB = 0;
        this.ivTCC = 0;
        this.ivNPC = 0;
        this.ivMixerIO = 0;
        this.ivAmpA = 0;
        this.ivAmpB = 0;
        this.ivAmpC = 0;
        this.ivEnvPC = 0;
        this.ivEnvC = 0;
        this.ivIO1 = 0;
        this.ivIO2 = 0;
        this.ivAddress = 0;
        this.ivNNNCBA = 0;
        this.ivEnvelopeIndex = 0;
    }
    
    private void setRegister(final int pAddress, final int pValue) {
        this.latchAddress(pAddress);
        this.writeData(pValue);
    }
    
    public void setStatus(final AY38912Status pStatus) {
        this.setRegister(0, pStatus.getAPitchLo());
        this.setRegister(1, pStatus.getAPitchHi());
        this.setRegister(2, pStatus.getBPitchLo());
        this.setRegister(3, pStatus.getBPitchHi());
        this.setRegister(4, pStatus.getCPitchLo());
        this.setRegister(5, pStatus.getCPitchHi());
        this.setRegister(6, pStatus.getNoisePitch());
        this.setRegister(7, pStatus.getMixerIO());
        this.setRegister(8, pStatus.getAVolume());
        this.setRegister(9, pStatus.getBVolume());
        this.setRegister(10, pStatus.getCVolume());
        this.setRegister(11, pStatus.getEnvelopeDurationLo());
        this.setRegister(12, pStatus.getEnvelopeDurationHi());
        this.setRegister(13, pStatus.getEnvelopeShape());
        this.setRegister(14, pStatus.getIOA());
        this.setRegister(15, pStatus.getIOB());
        this.latchAddress(pStatus.getLatchedAddress());
    }
    
    public void step() {
        if (this.ivCountA++ >= this.ivTCA) {
            this.ivCountA = 0;
            this.ivNNNCBA ^= 0x1;
        }
        if (this.ivCountB++ >= this.ivTCB) {
            this.ivCountB = 0;
            this.ivNNNCBA ^= 0x2;
        }
        if (this.ivCountC++ >= this.ivTCC) {
            this.ivCountC = 0;
            this.ivNNNCBA ^= 0x4;
        }
        this.ivClockDivider = !this.ivClockDivider;
        if (this.ivClockDivider) {
            if (--this.ivCountN <= 0) {
                this.ivCountN = this.ivNPC;
                this.ivNNNCBA ^= this.ivNoiseTable[this.ivNoiseIndex];
                if (++this.ivNoiseIndex >= this.ivNoiseTable.length) {
                    this.ivNoiseIndex = 0;
                }
            }
            if (this.ivCountEnv++ >= this.ivEnvPC) {
                this.ivCountEnv = 0;
                if (this.ivEnvelopeIndex + 1 < this.ivEnvelopeTables[this.ivEnvC].length) {
                    final int temp = this.ivEnvelopeTables[this.ivEnvC][++this.ivEnvelopeIndex];
                    if (temp >= 0) {
                        this.ivEnv = temp;
                    }
                    else {
                        this.ivEnvelopeIndex = 0;
                        this.ivEnv = this.ivEnvelopeTables[this.ivEnvC][0];
                    }
                }
            }
        }
    }
    
    public void step(final int pNumberOfCycles) {
        for (int i = 0; i < pNumberOfCycles; ++i) {
            this.step();
        }
    }
    
    public void terminate() {
    }
    
    @Override
    public String toString() {
        String result = "";
        result = String.valueOf(result) + "AY 3-8192\n";
        result = String.valueOf(result) + "  A     : TC=" + this.ivTCA + ", AMP=" + this.ivAmpA + "\n";
        result = String.valueOf(result) + "  B     : TC=" + this.ivTCB + ", AMP=" + this.ivAmpB + "\n";
        result = String.valueOf(result) + "  C     : TC=" + this.ivTCC + ", AMP=" + this.ivAmpC + "\n";
        result = String.valueOf(result) + "  Noise : PC=" + this.ivNPC + "\n";
        result = String.valueOf(result) + "  Env   : PC=" + this.ivEnvPC + ", C=" + this.ivEnvC + ", Value=" + this.ivEnv + "\n";
        result = String.valueOf(result) + "  Mixer : M =" + this.ivMixerIO + "\n";
        return result;
    }
    
    public void writeData(final int pValue) {
        switch (this.ivAddress) {
            case 0: {
                this.ivTCA = ((this.ivTCA & 0xF00) | (pValue & 0xFF));
                break;
            }
            case 1: {
                this.ivTCA = ((this.ivTCA & 0xFF) | (pValue & 0xF) << 8);
                break;
            }
            case 2: {
                this.ivTCB = ((this.ivTCB & 0xF00) | (pValue & 0xFF));
                break;
            }
            case 3: {
                this.ivTCB = ((this.ivTCB & 0xFF) | (pValue & 0xF) << 8);
                break;
            }
            case 4: {
                this.ivTCC = ((this.ivTCC & 0xF00) | (pValue & 0xFF));
                break;
            }
            case 5: {
                this.ivTCC = ((this.ivTCC & 0xFF) | (pValue & 0xF) << 8);
                break;
            }
            case 6: {
                this.ivNPC = (pValue & 0x1F);
                break;
            }
            case 7: {
                this.ivMixerIO = (pValue & 0xFF);
                break;
            }
            case 8: {
                this.ivAmpA = (pValue & 0x1F);
                break;
            }
            case 9: {
                this.ivAmpB = (pValue & 0x1F);
                break;
            }
            case 10: {
                this.ivAmpC = (pValue & 0x1F);
                break;
            }
            case 11: {
                this.ivEnvPC = ((this.ivEnvPC & 0xFF00) | (pValue & 0xFF));
                break;
            }
            case 12: {
                this.ivEnvPC = ((this.ivEnvPC & 0xFF) | (pValue & 0xFF) << 8);
                break;
            }
            case 13: {
                this.ivEnvC = (pValue & 0xF);
                this.ivEnv = this.ivEnvelopeTables[this.ivEnvC][0];
                this.ivEnvelopeIndex = 0;
                break;
            }
        }
    }
}
