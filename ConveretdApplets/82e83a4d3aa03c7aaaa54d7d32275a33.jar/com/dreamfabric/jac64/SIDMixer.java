// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public abstract class SIDMixer
{
    public static final int BYTES_PER_SAMPLE = 2;
    public static final boolean NO_SOUND = false;
    public static final boolean DEBUG = false;
    public static int DL_BUFFER_SIZE;
    public static final int SYNCH_BUFFER = 1760;
    public static final int EFX_NONE = 0;
    public static final int EFX_FLANGER_1 = 1;
    public static final int EFX_FLANGER_2 = 2;
    public static final int EFX_FLANGER_3 = 3;
    public static final int EFX_PHASER_1 = 4;
    public static final int EFX_PHASER_2 = 5;
    public static final int EFX_CHORUS_1 = 6;
    public static final int EFX_CHORUS_2 = 7;
    public static final int EFX_ECHO_1 = 8;
    public static final int EFX_ECHO_2 = 9;
    public static final int EFX_REV_SMALL = 10;
    public static final int EFX_REV_MED = 11;
    public static final int EFX_REV_LARGE = 12;
    public static final int EFX_FSWEEP = 13;
    public static final int EFX_FSWEEP_RES = 14;
    private String[] efxNames;
    public boolean fullSpeed;
    private SID psid;
    private SID6581[] channels;
    private int mfrq;
    private int mpos;
    private boolean soundOn;
    private SIDMixerListener listener;
    byte[] buffer;
    byte[] syncBuffer;
    int[] intBuffer;
    int[] noFltBuffer;
    boolean effects;
    public static final int LFO_WAVELEN = 500;
    int[] echo;
    int echoSize;
    int echoPos;
    int echoLFODiff;
    int echoLFODiffMax;
    int echoLFODepth;
    int echoFeedback;
    int echoLFOSpeed;
    int echoLFOPos;
    int echoDW;
    int maxefx;
    int minefx;
    int sidVol;
    int[] sidVolArr;
    long lastCycles;
    int filterVal;
    public int cutoff;
    public int resonance;
    int filterOn;
    int masterVolume;
    boolean lpOn;
    boolean hpOn;
    boolean bpOn;
    long vlp;
    long vhp;
    long vbp;
    long w0;
    long div1024Q;
    long exVlp;
    long exVhp;
    long exVo;
    long exw0lp;
    long exw0hp;
    int irq;
    int[] sine10Hz;
    
    public SIDMixer() {
        this.efxNames = new String[] { "EFX_NONE", "EFX_FLANGER_1", "EFX_FLANGER_2", "EFX_FLANGER_3", "EFX_PHASER_1", "EFX_PHASER_2", "EFX_CHORUS_1", "EFX_CHORUS_2", "EFX_ECHO_1", "EFX_ECHO_2", "EFX_REV_SMALL", "EFX_REV_MED", "EFX_REV_LARGE", "EFX_FSWEEP", "EFX_FSWEEP_RES" };
        this.fullSpeed = false;
        this.mfrq = 1;
        this.mpos = 1;
        this.soundOn = true;
        this.listener = null;
        this.buffer = new byte[88];
        this.syncBuffer = new byte[4096];
        this.intBuffer = new int[44];
        this.noFltBuffer = new int[44];
        this.effects = false;
        this.echoSize = 0;
        this.echoPos = 0;
        this.echoLFODiff = 0;
        this.echoLFODiffMax = 0;
        this.echoLFODepth = 50;
        this.echoFeedback = 0;
        this.echoLFOSpeed = 0;
        this.echoLFOPos = 0;
        this.echoDW = 50;
        this.sidVol = 15;
        this.sidVolArr = new int[44];
        this.lastCycles = 0L;
        this.filterVal = 0;
        this.cutoff = 0;
        this.resonance = 0;
        this.filterOn = 0;
        this.masterVolume = 100;
        this.lpOn = false;
        this.hpOn = false;
        this.bpOn = false;
        this.irq = 0;
    }
    
    public SIDMixer(final SID6581[] array, final SID sid) {
        this.efxNames = new String[] { "EFX_NONE", "EFX_FLANGER_1", "EFX_FLANGER_2", "EFX_FLANGER_3", "EFX_PHASER_1", "EFX_PHASER_2", "EFX_CHORUS_1", "EFX_CHORUS_2", "EFX_ECHO_1", "EFX_ECHO_2", "EFX_REV_SMALL", "EFX_REV_MED", "EFX_REV_LARGE", "EFX_FSWEEP", "EFX_FSWEEP_RES" };
        this.fullSpeed = false;
        this.mfrq = 1;
        this.mpos = 1;
        this.soundOn = true;
        this.listener = null;
        this.buffer = new byte[88];
        this.syncBuffer = new byte[4096];
        this.intBuffer = new int[44];
        this.noFltBuffer = new int[44];
        this.effects = false;
        this.echoSize = 0;
        this.echoPos = 0;
        this.echoLFODiff = 0;
        this.echoLFODiffMax = 0;
        this.echoLFODepth = 50;
        this.echoFeedback = 0;
        this.echoLFOSpeed = 0;
        this.echoLFOPos = 0;
        this.echoDW = 50;
        this.sidVol = 15;
        this.sidVolArr = new int[44];
        this.lastCycles = 0L;
        this.filterVal = 0;
        this.cutoff = 0;
        this.resonance = 0;
        this.filterOn = 0;
        this.masterVolume = 100;
        this.lpOn = false;
        this.hpOn = false;
        this.bpOn = false;
        this.irq = 0;
        this.init(array, sid);
    }
    
    public void init(final SID6581[] channels, final SID psid) {
        this.channels = channels;
        this.psid = psid;
        System.out.println("Micros per SIDGen: " + this.getMicrosPerGen());
        this.exw0hp = 105L;
        this.exw0lp = 104858L;
        this.sine10Hz = new int[500];
        for (int i = 0; i < 500; ++i) {
            this.sine10Hz[i] = (int)(500.0 + 500.0 * Math.sin(i * 2 * 3.1415 / 500.0));
        }
        this.setEFX(8);
        this.setEFX(0);
    }
    
    public void setListener(final SIDMixerListener listener) {
        this.listener = listener;
    }
    
    public void setEchoTime(final int n) {
        final int echoSize = n * 44000 / 1000;
        System.out.println("SamplesDelay: " + echoSize);
        this.echoSize = echoSize;
        this.echo = new int[this.echoSize];
        this.echoLFODiffMax = this.echoSize * this.echoLFODepth / 110;
        this.echoLFODiff = 0;
        this.echoPos = 0;
    }
    
    public int getEchoTime() {
        return 1000 * this.echoSize / 44000;
    }
    
    public int getEFXCount() {
        return this.efxNames.length;
    }
    
    public String getEFXName(final int n) {
        return this.efxNames[n];
    }
    
    public void setEchoFeedback(final int echoFeedback) {
        this.echoFeedback = echoFeedback;
    }
    
    public int getEchoFeedback() {
        return this.echoFeedback;
    }
    
    public void setEchoLFOSpeed(final int echoLFOSpeed) {
        this.echoLFOSpeed = echoLFOSpeed;
    }
    
    public int getEchoLFOSpeed() {
        return this.echoLFOSpeed;
    }
    
    public void setEchoDW(final int echoDW) {
        this.echoDW = echoDW;
    }
    
    public int getEchoDW() {
        return this.echoDW;
    }
    
    public void setEchoLFODepth(final int echoLFODepth) {
        this.echoLFODepth = echoLFODepth;
        this.echoLFODiffMax = this.echoSize * this.echoLFODepth / 110;
    }
    
    public int getEchoLFODepth() {
        return this.echoLFODepth;
    }
    
    public boolean getEffectsOn() {
        return this.effects;
    }
    
    public void setEFX(int n) {
        n %= this.efxNames.length;
        this.effects = true;
        switch (n) {
            case 0: {
                this.effects = false;
                break;
            }
            case 1: {
                this.setEchoTime(5);
                this.setEchoFeedback(75);
                this.setEchoLFOSpeed(1);
                this.setEchoLFODepth(35);
                this.setEchoDW(33);
                break;
            }
            case 2: {
                this.setEchoTime(15);
                this.setEchoFeedback(70);
                this.setEchoLFOSpeed(5);
                this.setEchoLFODepth(35);
                this.setEchoDW(35);
                break;
            }
            case 3: {
                this.setEchoTime(2);
                this.setEchoFeedback(85);
                this.setEchoLFOSpeed(3);
                this.setEchoLFODepth(55);
                this.setEchoDW(30);
                break;
            }
            case 4: {
                this.setEchoTime(10);
                this.setEchoFeedback(0);
                this.setEchoLFOSpeed(1);
                this.setEchoLFODepth(75);
                this.setEchoDW(50);
                break;
            }
            case 5: {
                this.setEchoTime(3);
                this.setEchoFeedback(0);
                this.setEchoLFOSpeed(2);
                this.setEchoLFODepth(85);
                this.setEchoDW(40);
                break;
            }
            case 6: {
                this.setEchoTime(25);
                this.setEchoFeedback(60);
                this.setEchoLFOSpeed(1);
                this.setEchoLFODepth(35);
                this.setEchoDW(35);
                break;
            }
            case 7: {
                this.setEchoTime(30);
                this.setEchoFeedback(50);
                this.setEchoLFOSpeed(5);
                this.setEchoLFODepth(25);
                this.setEchoDW(35);
                break;
            }
            case 8: {
                this.setEchoTime(150);
                this.setEchoFeedback(0);
                this.setEchoLFOSpeed(0);
                this.setEchoLFODepth(0);
                this.setEchoDW(33);
                break;
            }
            case 9: {
                this.setEchoTime(300);
                this.setEchoFeedback(33);
                this.setEchoLFOSpeed(0);
                this.setEchoLFODepth(0);
                this.setEchoDW(45);
                break;
            }
            case 10: {
                this.setEchoTime(70);
                this.setEchoFeedback(40);
                this.setEchoLFOSpeed(0);
                this.setEchoLFODepth(0);
                this.setEchoDW(33);
                break;
            }
            case 11: {
                this.setEchoTime(130);
                this.setEchoFeedback(50);
                this.setEchoLFOSpeed(0);
                this.setEchoLFODepth(0);
                this.setEchoDW(33);
                break;
            }
            case 12: {
                this.setEchoTime(100);
                this.setEchoFeedback(70);
                this.setEchoLFOSpeed(0);
                this.setEchoLFODepth(0);
                this.setEchoDW(40);
                break;
            }
        }
        if (this.listener != null) {
            this.listener.updateValues();
        }
    }
    
    public void setEffectsOn(final boolean effects) {
        this.effects = effects;
    }
    
    public boolean isEffectsOn() {
        return this.effects;
    }
    
    public void setFilterCutoffLO(final int n) {
        this.cutoff = ((this.cutoff & 0xFF8) | (n & 0x7));
        this.recalcFilter();
    }
    
    public void setFilterCutoffHI(final int n) {
        this.cutoff = ((this.cutoff & 0x7) | n << 3);
        this.recalcFilter();
    }
    
    public void setFilterResonance(final int resonance) {
        this.resonance = resonance;
        this.recalcFilter();
    }
    
    public void setFilterCtrl(final int n) {
        this.lpOn = ((n & 0x10) > 0);
        this.bpOn = ((n & 0x20) > 0);
        this.hpOn = ((n & 0x40) > 0);
    }
    
    public void setFilterOn(final int filterOn) {
        this.filterOn = filterOn;
    }
    
    public void setMoogFilterOn(final boolean b) {
    }
    
    public boolean isMoogFilterOn() {
        return false;
    }
    
    public void setMoogResonance(final int n) {
    }
    
    public int getMoogResonance() {
        return 0;
    }
    
    public void setMoogCutoff(final int n) {
    }
    
    public int getMoogCutoff() {
        return 0;
    }
    
    public void setMoogSpeed(final int n) {
    }
    
    public int getMoogSpeed() {
        return 0;
    }
    
    public void setMoogDepth(final int n) {
    }
    
    public int getMoogDepth() {
        return 0;
    }
    
    public void setVolume(final int sidVol) {
        this.sidVol = sidVol;
    }
    
    public void setVolume(final int sidVol, final long n) {
        if (this.lastCycles > 0L) {
            this.sidVolArr[(int)((n - this.lastCycles) / 32L) % 44] = sidVol;
        }
        else {
            this.sidVol = sidVol;
        }
    }
    
    private void recalcFilter() {
        this.w0 = (long)(6.283185307179586 * (30 + 12000 * this.cutoff / 2048) * 1.048576);
        this.div1024Q = (int)(1024.0 / (0.707 + 1.0 * this.resonance / 15.0));
    }
    
    public void reset() {
        this.exVo = 0L;
        this.exVhp = 0L;
        this.exVlp = 0L;
        this.cutoff = 0;
        this.resonance = 0;
        this.w0 = 0L;
        this.div1024Q = 0L;
        this.filterOn = 0;
        this.maxefx = 0;
        this.minefx = 0;
        for (int i = 0; i < 44; ++i) {
            this.sidVolArr[i] = -1;
        }
        this.setVolume(15);
    }
    
    public void printStatus() {
        System.out.println("SIDMixer  ----------------------------");
        System.out.println("Volume: " + this.sidVol);
        System.out.println("Max Efx:" + this.maxefx);
        System.out.println("Min Efx:" + this.minefx);
    }
    
    public void setFullSpeed(final boolean fullSpeed) {
        System.out.println("Set full speed: " + fullSpeed);
        this.fullSpeed = fullSpeed;
    }
    
    public boolean fullSpeed() {
        return this.fullSpeed;
    }
    
    public boolean updateSound(final long lastCycles) {
        ++this.irq;
        final boolean b = this.irq % 20 == 0;
        if (!this.hasSound()) {
            if (b) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return b;
        }
        if (this.fullSpeed && this.available() < 88) {
            return false;
        }
        this.lastCycles = lastCycles;
        if (this.soundOn) {
            if (this.psid != null) {
                final int[] generateSound = this.psid.generateSound(lastCycles);
                for (int i = 0; i < 44; ++i) {
                    this.intBuffer[i] = 0;
                    this.noFltBuffer[i] = generateSound[i] << 4;
                }
            }
            else {
                for (int j = 0; j < 44; ++j) {
                    this.intBuffer[j] = 0;
                    this.noFltBuffer[j] = 0;
                }
            }
            for (int k = 0; k < this.channels.length; ++k) {
                final int[] generateSound2 = this.channels[k].generateSound(lastCycles);
                int[] array;
                if ((this.filterOn & 1 << k) > 0) {
                    array = this.intBuffer;
                }
                else {
                    array = this.noFltBuffer;
                }
                for (int l = 0; l < 44; ++l) {
                    final int[] array2 = array;
                    final int n = l;
                    array2[n] += generateSound2[l] >> 2;
                }
            }
            for (int n2 = 0; n2 < 44; ++n2) {
                int n3 = this.intBuffer[n2];
                if (this.filterOn > 0) {
                    this.vbp -= 8L * this.w0 * this.vhp >> 20;
                    this.vlp -= 8L * this.w0 * this.vbp >> 20;
                    this.vhp = (this.vbp * this.div1024Q >> 10) - this.vlp - n3;
                    this.vbp -= 8L * this.w0 * this.vhp >> 20;
                    this.vlp -= 8L * this.w0 * this.vbp >> 20;
                    this.vhp = (this.vbp * this.div1024Q >> 10) - this.vlp - n3;
                    this.vbp -= 7L * this.w0 * this.vhp >> 20;
                    this.vlp -= 7L * this.w0 * this.vbp >> 20;
                    this.vhp = (this.vbp * this.div1024Q >> 10) - this.vlp - n3;
                    n3 = (int)((this.bpOn ? this.vbp : 0L) + (this.hpOn ? this.vhp : 0L) + (this.lpOn ? this.vlp : 0L));
                }
                final int n4 = n3 + this.noFltBuffer[n2];
                if (this.sidVolArr[n2] != -1) {
                    this.sidVol = this.sidVolArr[n2];
                    this.sidVolArr[n2] = -1;
                }
                final int n5 = n4 * this.sidVol >> 3;
                this.exVlp += (8L * this.exw0lp >> 8) * (n5 - this.exVlp) >> 12;
                this.exVhp += this.exw0hp * 8L * (this.exVlp - this.exVhp) >> 20;
                this.exVlp += (8L * this.exw0lp >> 8) * (n5 - this.exVlp) >> 12;
                this.exVhp += this.exw0hp * 8L * (this.exVlp - this.exVhp) >> 20;
                this.exVo = this.exVlp - this.exVhp;
                this.exVlp += (7L * this.exw0lp >> 8) * (n5 - this.exVlp) >> 12;
                this.exVhp += this.exw0hp * 7L * (this.exVlp - this.exVhp) >> 20;
                this.intBuffer[n2] = (int)this.exVo;
            }
            if (this.effects) {
                for (int n6 = 0; n6 < 44; ++n6) {
                    final int n7 = this.intBuffer[n6];
                    final int n8 = (this.echoPos + this.echoLFODiff) % this.echoSize;
                    int n9 = (n7 * (100 - this.echoDW) + this.echo[n8] * this.echoDW) / 100;
                    if (n9 > 32767) {
                        n9 = 32767;
                    }
                    if (n9 < -32767) {
                        n9 = -32767;
                    }
                    this.intBuffer[n6] = n9;
                    int n10 = n7 + this.echo[n8] * this.echoFeedback / 100;
                    if (n10 > this.maxefx) {
                        this.maxefx = n10;
                    }
                    if (n10 < this.minefx) {
                        this.minefx = n10;
                    }
                    if (n10 > 32767) {
                        n10 = 32767;
                    }
                    if (n10 < -32767) {
                        n10 = -32767;
                    }
                    this.echo[this.echoPos] = n10;
                    this.echoPos = (this.echoPos + 1) % this.echoSize;
                }
            }
            int n11 = 0;
            for (int n12 = 0; n12 < 44; ++n12) {
                this.buffer[n11++] = (byte)(this.intBuffer[n12] & 0xFF);
                this.buffer[n11++] = (byte)(this.intBuffer[n12] >> 8);
            }
        }
        this.write(this.buffer);
        if (b) {
            this.echoLFODiff = this.echoLFODiffMax * this.sine10Hz[this.echoLFOPos] / 1000;
            this.echoLFOPos = (this.echoLFOPos + this.echoLFOSpeed) % 500;
        }
        return b;
    }
    
    public int getMicrosPerGen() {
        return (int)(44000000L / 44000L);
    }
    
    public boolean soundOn() {
        return this.soundOn;
    }
    
    public void setSoundOn(final boolean soundOn) {
        if (!(this.soundOn = soundOn)) {
            for (int i = 0; i < this.buffer.length; ++i) {
                this.buffer[i] = 0;
            }
        }
    }
    
    public abstract void write(final byte[] p0);
    
    public abstract long getMicros();
    
    public abstract boolean hasSound();
    
    public abstract int available();
    
    public abstract int getMasterVolume();
    
    public abstract void setMasterVolume(final int p0);
    
    static {
        SIDMixer.DL_BUFFER_SIZE = 88000;
    }
}
