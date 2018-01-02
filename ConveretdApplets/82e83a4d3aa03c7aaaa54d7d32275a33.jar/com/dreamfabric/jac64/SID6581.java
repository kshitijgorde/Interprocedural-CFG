// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class SID6581 extends SID
{
    public static final int UPDATE_CYCLES = 1000;
    public static final int PER_SEC = 1000;
    public static final boolean DEBUG = false;
    private static final double DECAY_LOG = -4.852030263919617;
    private int[] memory;
    private int sidbase;
    public static final int SAMPLE_RATE = 44000;
    public static final double FRQCONV = 0.058725380897521974;
    public static final int TRIANGLE = 1;
    public static final int SAW = 2;
    public static final int PULSE = 4;
    public static final int NOISE = 8;
    public static final int NONE = 0;
    private int[] ST_LOOKUP;
    private int[] PST_LOOKUP;
    private int[] PT_LOOKUP;
    private int[] PS_LOOKUP;
    public static final String[] WAVE;
    private final int ATTACK = 1;
    private final int DECAY = 2;
    private final int SUSTAIN = 3;
    private final int RELEASE = 4;
    private final int FINISHED = 5;
    public static final String[] ADSR_PHASES;
    static final int waveLen = 44000;
    private static int[] sawWave;
    private static int[] triangleWave;
    private static int[] triangleWaveD2;
    private static int[] pulseWave;
    public static final int GENLEN = 44;
    public static final int GENS_PER_IRQ = 20;
    public static final int SAMPLE_BITS = 12;
    public static final int MAXGENLEN = 440;
    public static final double SAMPLES_PER_MICRO = 0.044;
    public static final int CYCLES_PER_SAMPLE = 22;
    public static final int VOLUME_SIZE = 4096;
    private static final int MICROSEC_PER_GEN = 1000;
    public static final int DELAY_LEN = 880;
    byte[] buffer;
    int delPos;
    int diffMin;
    int diffDt;
    int[] delay;
    int generated;
    int smp;
    int pwid;
    long nextSample;
    long next_nextSample;
    int noiseData;
    long noise_reg;
    boolean debugGen;
    boolean sync;
    boolean ring;
    boolean testBit;
    int testZero;
    private int[] outBuffer;
    long[] attackTime;
    public static final int ADSR_BITS = 22;
    public static final int ADSR_RATE_BITS = 32;
    long ATTACK_MAX;
    long RELEASE_FINISH_LEVEL;
    int ATTACK_FUZZ;
    long[] attackDelta;
    long[] decayExp;
    long adsrLevel22;
    long adsrSusLevel22;
    long decayFactor;
    long releaseFactor;
    long currentDecayFactor;
    long currentAttackRate;
    int adsrPhase;
    int adsrBug;
    int ad;
    int sr;
    public double adsrLevel;
    long lastAttackTime;
    boolean soundOn;
    public static final int FRQ_BITS = 4;
    public static final long WAVELEN = 704000L;
    public static final long WAVELEN_HALF = 352000L;
    public long frq;
    public double trueFrq;
    public int wave;
    public long next_frq;
    public SID6581 next;
    private long lastCycles;
    private int decayTimes;
    private int outputVal;
    public int lastSample;
    public int adsrVol;
    long nanos;
    long total;
    int[] pwidArr;
    
    public SID6581(final int[] memory, final int sidbase) {
        this.sidbase = 0;
        this.ST_LOOKUP = RS6581Waves.wave6581__ST;
        this.PST_LOOKUP = RS6581Waves.wave6581_PST;
        this.PT_LOOKUP = RS6581Waves.wave6581_P_T;
        this.PS_LOOKUP = RS6581Waves.wave6581_PS_;
        this.buffer = new byte[4096];
        this.delPos = 0;
        this.diffMin = 0;
        this.diffDt = 1;
        this.delay = new int[880];
        this.pwid = 22000;
        this.nextSample = 0L;
        this.noiseData = 0;
        this.noise_reg = 8388600L;
        this.debugGen = false;
        this.sync = false;
        this.ring = false;
        this.testBit = false;
        this.testZero = 0;
        this.attackTime = new long[] { 2000L, 8000L, 16000L, 24000L, 38000L, 56000L, 68000L, 80000L, 100000L, 250000L, 500000L, 800000L, 1000000L, 3000000L, 5000000L, 8000000L };
        this.ATTACK_MAX = 1069547520L;
        this.RELEASE_FINISH_LEVEL = 4194303L;
        this.ATTACK_FUZZ = 128;
        this.attackDelta = new long[this.attackTime.length];
        this.decayExp = new long[] { 4216751472L, 4275278366L, 4285111523L, 4288394265L, 4290814737L, 4292149050L, 4292646253L, 4292994329L, 4293388850L, 4294335848L, 4294651560L, 4294769958L, 4294809425L, 4294914671L, 4294935721L, 4294947561L };
        this.adsrLevel22 = 0L;
        this.adsrSusLevel22 = 0L;
        this.decayFactor = 0L;
        this.releaseFactor = 0L;
        this.currentDecayFactor = 0L;
        this.currentAttackRate = 0L;
        this.adsrPhase = 1;
        this.adsrBug = 0;
        this.lastAttackTime = 0L;
        this.soundOn = false;
        this.frq = 1L;
        this.trueFrq = 0.0;
        this.lastCycles = 0L;
        this.decayTimes = 0;
        this.outputVal = 0;
        this.lastSample = 0;
        this.adsrVol = 0;
        this.pwidArr = new int[44];
        this.intBuffer = new int[2048];
        this.outBuffer = new int[2048];
        this.memory = memory;
        this.sidbase = sidbase;
        System.out.println("SIDBASE: " + this.sidbase);
        System.out.println("GENLEN: 44");
        for (int i = 0; i < this.attackTime.length; ++i) {
            this.attackDelta[i] = 1069547520L / (int)(44000L * this.attackTime[i] / 1000000L);
        }
        if (SID6581.sawWave == null) {
            SID6581.sawWave = new int[44000];
            SID6581.triangleWave = new int[44000];
            SID6581.triangleWaveD2 = new int[44000];
            SID6581.pulseWave = new int[88000];
        }
        double n = 0.0;
        final double n2 = 0.09306818181818181;
        for (int j = 0; j < 44000; ++j) {
            SID6581.sawWave[j] = (int)n;
            n += n2;
        }
        for (int k = 0; k < 44000; ++k) {
            SID6581.pulseWave[k] = 4095;
            SID6581.pulseWave[k + 44000] = 0;
        }
        double n3 = 0.0;
        final double n4 = 0.18613636363636363;
        for (int l = 0; l < 22000; ++l) {
            SID6581.triangleWave[l] = (int)n3;
            SID6581.triangleWave[l + 22000] = (int)(4095.0 - n3);
            SID6581.triangleWaveD2[l] = (int)(n3 / 2.0);
            SID6581.triangleWaveD2[l + 22000] = (int)((4095.0 - n3) / 2.0);
            n3 += n4;
        }
    }
    
    public void init() {
        this.wave = 0;
    }
    
    public void setControl(final int n, final long n2) {
        this.wave = n >> 4;
        final boolean testBit = this.testBit;
        this.testBit = ((n & 0x8) != 0x0);
        if (testBit && !this.testBit) {
            this.testZero = 0;
            this.nextSample = 0L;
            this.noise_reg = 8388600L;
        }
        if ((n & 0x1) > 0) {
            this.soundOn(n2);
        }
        else {
            this.soundOff(n2);
        }
        this.sync = ((n & 0x2) != 0x0);
        this.ring = ((n & 0x4) != 0x0);
    }
    
    public void soundOn(final long lastAttackTime) {
        if (!this.soundOn) {
            if ((this.sr & 0xF) > this.ad >> 4) {
                this.adsrBug = 1441;
            }
            this.currentAttackRate = this.attackDelta[(this.memory[this.sidbase + 5] & 0xF0) >> 4];
            this.decayFactor = this.decayExp[this.memory[this.sidbase + 5] & 0xF];
            this.releaseFactor = this.decayExp[this.memory[this.sidbase + 6] & 0xF];
            this.adsrSusLevel22 = (this.memory[this.sidbase + 6] & 0xF8) << 22;
            this.currentDecayFactor = this.decayFactor;
            this.adsrPhase = 1;
            this.lastAttackTime = lastAttackTime;
            this.soundOn = true;
        }
    }
    
    public void soundOff(final long n) {
        this.adsrPhase = 4;
        this.soundOn = false;
        this.debugGen = false;
        this.currentDecayFactor = this.releaseFactor;
        this.adsrSusLevel22 = this.RELEASE_FINISH_LEVEL;
    }
    
    public void setAD(final int ad, final long n) {
        this.currentAttackRate = this.attackDelta[(this.memory[this.sidbase + 5] & 0xF0) >> 4];
        this.decayFactor = this.decayExp[this.memory[this.sidbase + 5] & 0xF];
        this.ad = ad;
        if (this.adsrPhase != 4) {
            if (this.currentDecayFactor > this.decayFactor) {
                this.adsrBug = 1441;
            }
            this.currentDecayFactor = this.decayFactor;
        }
    }
    
    public void setSR(final int sr, final long n) {
        final int n2 = (this.memory[this.sidbase + 6] & 0xF0) + 8 << 22;
        this.releaseFactor = this.decayExp[this.memory[this.sidbase + 6] & 0xF];
        this.sr = sr;
        if (this.adsrPhase == 4) {
            if (this.currentDecayFactor > this.releaseFactor) {
                this.adsrBug = 1441;
            }
            this.currentDecayFactor = this.releaseFactor;
        }
        else if (this.adsrPhase == 3 && n2 < this.adsrSusLevel22) {
            this.currentDecayFactor = this.releaseFactor;
            this.adsrPhase = 4;
        }
        this.adsrSusLevel22 = n2;
    }
    
    public void reset() {
        this.wave = 0;
        this.soundOff(0L);
    }
    
    public void printStatus() {
        System.out.println("SID: " + this.getSIDNo() + " ----------------------------");
        System.out.println("Wave: " + SID6581.WAVE[this.wave]);
        System.out.println("Frequency: " + this.frq + "  PWid:" + this.pwid + " " + this.trueFrq);
        System.out.println("ADSRLevel: " + (this.adsrLevel22 >> 22));
        System.out.println("ADSRPhase: " + SID6581.ADSR_PHASES[this.adsrPhase]);
        System.out.println("AD reg: " + Integer.toString(this.memory[this.sidbase + 5], 16));
        System.out.println("SR reg: " + Integer.toString(this.memory[this.sidbase + 6], 16));
        System.out.println("WavePos: " + this.nextSample + " pulse => " + ((this.nextSample < this.pwid) ? "0xfff" : "0"));
        if (this.ring) {
            System.out.println("RING MODULATION");
        }
        if (this.sync) {
            System.out.println("SYNCHRONIZATION");
        }
    }
    
    private int getSIDNo() {
        return (this.sidbase & 0xF) / 7;
    }
    
    public int[] generateSound(final long n) {
        this.updateSound(n);
        this.generated = 0;
        return this.outBuffer;
    }
    
    public void updatePulseWidth(final long n) {
        if ((this.wave & 0x4) != 0x0 && this.lastCycles > 0L) {
            this.pwidArr[(int)((n - this.lastCycles) / 32L) % 44] = (((this.memory[this.sidbase + 3] & 0xF) << 8) + this.memory[this.sidbase + 2]) * 44000 / 4095;
        }
        else {
            this.pwid = (((this.memory[this.sidbase + 3] & 0xF) << 8) + this.memory[this.sidbase + 2]) * 44000 / 4095;
        }
    }
    
    public void updateSound(final long lastCycles) {
        this.lastCycles = lastCycles;
        this.frq = (int)(0.5 + 16 * ((this.memory[this.sidbase + 1] << 8) + this.memory[this.sidbase]) * 0.058725380897521974);
        this.trueFrq = 16 * ((this.memory[this.sidbase + 1] << 8) + this.memory[this.sidbase]) * 0.058725380897521974;
        this.pwid = (((this.memory[this.sidbase + 3] & 0xF) << 8) + this.memory[this.sidbase + 2]) * 44000 / 4095;
        if (this.next != null) {
            this.next_frq = this.next.frq;
        }
        int generated = this.generated;
        switch (this.wave) {
            case 0: {
                for (int i = 0; i < 44; ++i) {
                    this.intBuffer[generated++] = 4095;
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                }
                break;
            }
            case 1: {
                if (this.ring) {
                    for (int j = 0; j < 44; ++j) {
                        final boolean b2;
                        final boolean b = (b2 = (this.nextSample >= 352000L)) ^ this.next_nextSample >= 352000L;
                        long nextSample = this.nextSample;
                        if (b2 != b) {
                            nextSample += (b2 ? -352000L : 352000L);
                        }
                        this.intBuffer[generated++] = SID6581.triangleWave[(int)(nextSample >> 4)];
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                        this.next_nextSample = (this.next_nextSample + this.next_frq) % 704000L;
                    }
                    break;
                }
                if (!this.sync) {
                    for (int k = 0; k < 44; ++k) {
                        this.intBuffer[generated++] = SID6581.triangleWave[(int)(this.nextSample >> 4)];
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                    }
                    break;
                }
                for (int l = 0; l < 44; ++l) {
                    this.intBuffer[generated++] = SID6581.triangleWave[(int)(this.nextSample >> 4)];
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                    this.next_nextSample += this.next_frq;
                    if (this.next_nextSample > 704000L) {
                        this.nextSample = 0L;
                        this.next_nextSample -= 704000L;
                    }
                }
                break;
            }
            case 2: {
                if (!this.sync) {
                    for (int n = 0; n < 44; ++n) {
                        this.intBuffer[generated++] = SID6581.sawWave[(int)(this.nextSample >> 4)];
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                    }
                    break;
                }
                for (int n2 = 0; n2 < 44; ++n2) {
                    this.intBuffer[generated++] = SID6581.sawWave[(int)(this.nextSample >> 4)];
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                    this.next_nextSample += this.next_frq;
                    if (this.next_nextSample > 704000L) {
                        this.nextSample = 0L;
                        this.next_nextSample -= 704000L;
                    }
                }
                break;
            }
            case 3: {
                if (!this.sync) {
                    for (int n3 = 0; n3 < 44; ++n3) {
                        this.intBuffer[generated++] = this.ST_LOOKUP[SID6581.sawWave[(int)(this.nextSample >> 4)]];
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                    }
                    break;
                }
                for (int n4 = 0; n4 < 44; ++n4) {
                    this.intBuffer[generated++] = this.ST_LOOKUP[SID6581.sawWave[(int)(this.nextSample >> 4)]];
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                    this.next_nextSample += this.next_frq;
                    if (this.next_nextSample > 704000L) {
                        this.nextSample = 0L;
                        this.next_nextSample -= 704000L;
                    }
                }
                break;
            }
            case 4: {
                if (!this.sync) {
                    for (int n5 = 0; n5 < 44; ++n5) {
                        if (this.pwidArr[n5] != -1) {
                            this.pwid = this.pwidArr[n5];
                            this.pwidArr[n5] = -1;
                        }
                        this.intBuffer[generated++] = SID6581.pulseWave[this.pwid + (int)(this.nextSample >> 4)];
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                    }
                    break;
                }
                for (int n6 = 0; n6 < 44; ++n6) {
                    if (this.pwidArr[n6] != -1) {
                        this.pwid = this.pwidArr[n6];
                        this.pwidArr[n6] = -1;
                    }
                    this.intBuffer[generated++] = SID6581.pulseWave[this.pwid + (int)(this.nextSample >> 4)];
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                    this.next_nextSample += this.next_frq;
                    if (this.next_nextSample > 704000L) {
                        this.nextSample = 0L;
                        this.next_nextSample -= 704000L;
                    }
                }
                break;
            }
            case 5:
            case 6:
            case 7: {
                int[] array;
                int[] array2;
                if (this.wave == 6) {
                    array = this.PS_LOOKUP;
                    array2 = SID6581.sawWave;
                }
                else if (this.wave == 5) {
                    array = this.PT_LOOKUP;
                    array2 = SID6581.triangleWaveD2;
                }
                else {
                    array = this.PST_LOOKUP;
                    array2 = SID6581.sawWave;
                }
                if (!this.sync) {
                    for (int n7 = 0; n7 < 44; ++n7) {
                        if (this.pwidArr[n7] != -1) {
                            this.pwid = this.pwidArr[n7];
                            this.pwidArr[n7] = -1;
                        }
                        this.intBuffer[generated++] = ((SID6581.pulseWave[this.pwid + (int)(this.nextSample >> 4)] != 0) ? array[array2[(int)(this.nextSample >> 4)]] : 0);
                        this.nextSample = (this.nextSample + this.frq) % 704000L;
                    }
                    break;
                }
                for (int n8 = 0; n8 < 44; ++n8) {
                    if (this.pwidArr[n8] != -1) {
                        this.pwid = this.pwidArr[n8];
                        this.pwidArr[n8] = -1;
                    }
                    this.intBuffer[generated++] = ((SID6581.pulseWave[this.pwid + (int)(this.nextSample >> 4)] != 0) ? array[array2[(int)(this.nextSample >> 4)]] : 0);
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                    this.next_nextSample += this.next_frq;
                    if (this.next_nextSample > 704000L) {
                        this.nextSample = 0L;
                        this.next_nextSample -= 704000L;
                    }
                }
                break;
            }
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15: {
                int n9 = 1375;
                for (int n10 = 0; n10 < 44; ++n10) {
                    int n11 = 0;
                    int n12 = 0;
                    while (n9 < 0) {
                        final int n13 = (int)(this.noise_reg >> 22 ^ this.noise_reg >> 17) & 0x1;
                        this.noise_reg <<= 1;
                        this.noise_reg &= 0x7FFFFFL;
                        this.noise_reg |= n13;
                        if (n12 < 4) {
                            n11 += (int)((this.noise_reg & 0x400000L) >> 11 | (this.noise_reg & 0x100000L) >> 10 | (this.noise_reg & 0x10000L) >> 7 | (this.noise_reg & 0x2000L) >> 5 | (this.noise_reg & 0x800L) >> 4 | (this.noise_reg & 0x80L) >> 1 | (this.noise_reg & 0x10L) << 1 | (this.noise_reg & 0x4L) << 2);
                            ++n12;
                        }
                        n9 += 1375;
                    }
                    if (n12 > 0) {
                        this.noiseData = n11 / n12;
                    }
                    n9 -= (int)this.frq;
                    this.intBuffer[generated++] = this.noiseData;
                    this.nextSample = (this.nextSample + this.frq) % 704000L;
                }
                break;
            }
            default: {
                System.out.println("WAVE NOT IMPLEMENTED: " + this.wave);
                break;
            }
        }
        int generated2 = this.generated;
        for (int n14 = 0; n14 < 44; ++n14) {
            if (this.adsrBug > 0) {
                --this.adsrBug;
            }
            else if (this.adsrPhase == 1) {
                this.adsrLevel22 += this.currentAttackRate;
                if (this.adsrLevel22 + this.ATTACK_FUZZ > this.ATTACK_MAX) {
                    this.adsrLevel22 = this.ATTACK_MAX;
                    this.adsrPhase = 2;
                }
            }
            else if (this.adsrPhase == 2 || this.adsrPhase == 4) {
                ++this.decayTimes;
                this.adsrLevel22 = this.adsrLevel22 * this.currentDecayFactor >> 32;
                if (this.adsrLevel22 < this.adsrSusLevel22) {
                    if (this.adsrPhase == 2) {
                        this.decayTimes = 0;
                        this.adsrLevel22 = this.adsrSusLevel22;
                        this.adsrPhase = 3;
                    }
                    else {
                        this.adsrSusLevel22 = 0L;
                        this.adsrPhase = 5;
                    }
                }
            }
            this.adsrVol = (int)(this.adsrLevel22 >> 22);
            this.adsrLevel = (this.adsrLevel22 >> 22) / 255.0;
            if (this.testBit) {
                ++this.testZero;
                this.outBuffer[generated2] = 0;
            }
            else {
                this.outBuffer[generated2] = 1024 + (this.intBuffer[generated2] * this.adsrVol >> 7);
            }
            ++generated2;
        }
    }
    
    public int lastSample() {
        return this.intBuffer[this.smp++ % 44] >> 3 & 0xFF;
    }
    
    static {
        WAVE = new String[] { "NONE", "TRIANGLE", "SAW", "TRI_SAW", "PULSE", "TRI_PULSE", "SAW_PULSE", "TRI_SAW_PULSE", "NOISE", "NOISE_T", "NOISE_S", "NOISE_TS", "NOISE_P", "NOISE_TP", "NOISE_SP", "NOISE_TSP" };
        ADSR_PHASES = new String[] { "-", "Attack", "Decay", "Sustain", "Relase", "Finished" };
    }
}
