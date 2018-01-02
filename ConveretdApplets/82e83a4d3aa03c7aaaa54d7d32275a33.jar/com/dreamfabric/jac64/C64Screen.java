// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.image.ImageProducer;
import javax.swing.JPanel;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.Color;

public class C64Screen extends C64Chips implements Observer
{
    public static final String version = "1.0 Beta1";
    public static final int SERIAL_ATN = 8;
    public static final int SERIAL_CLK_OUT = 16;
    public static final int SERIAL_DATA_OUT = 32;
    public static final int SERIAL_CLK_IN = 64;
    public static final int SERIAL_DATA_IN = 128;
    public static final boolean IRQDEBUG = false;
    public static final boolean SPRITEDEBUG = false;
    public static final boolean IODEBUG = false;
    public static final boolean VIC_MEM_DEBUG = false;
    public static final boolean BAD_LINE_DEBUG = false;
    public static final boolean STATE_DEBUG = false;
    public static final boolean DEBUG_IEC = false;
    public static final int IO_UPDATE = 17;
    private static final int VIC_IRQ = 1;
    private static final int CIA_TIMER_IRQ = 2;
    private static final int CIA_TIMER_NMI = 2;
    public static final int CYCLES_PER_LINE = 63;
    public static final int IO_OFFSET = 12288;
    public static final boolean SOUND_AVAIABLE = true;
    public static final Color TRANSPARENT_BLACK;
    public static final Color DARKER_0;
    public static final Color LIGHTER_0;
    public static final Color DARKER_N;
    public static final Color LIGHTER_N;
    public static final Color LED_ON;
    public static final Color LED_OFF;
    public static final Color LED_BORDER;
    public static final int LABEL_COUNT = 32;
    private Color[] darks;
    private Color[] lites;
    private int colIndex;
    private static final int SC_WIDTH = 384;
    private static final int SC_HEIGHT = 284;
    private final int SC_XOFFS = 32;
    private final int SC_SPXOFFS = 8;
    private final int FIRST_VISIBLE_VBEAM = 15;
    private final int SC_SPYOFFS = 16;
    private boolean soundOn;
    private IMonitor monitor;
    private int scanRate;
    private int targetScanTime;
    private int actualScanTime;
    private int sidUpdate;
    private long lastScan;
    private long nextScanLine;
    private long nextIOUpdate;
    private boolean DOUBLE;
    private int reset;
    private C64Canvas canvas;
    private int[] memory;
    private Keyboard keyboard;
    SID6581[] sid;
    SIDMixer mixer;
    CIA[] cia;
    C1541Chips c1541Chips;
    int iecLines;
    int cia2PRA;
    int cia2DDRA;
    AudioClip trackSound;
    AudioClip motorSound;
    private int lastTrack;
    private int lastSector;
    private boolean ledOn;
    private boolean motorOn;
    boolean emulateDisk;
    private int[] cbmcolor;
    public int vicBank;
    public int charSet;
    public int videoMatrix;
    public int videoMode;
    private int vicBase;
    private boolean badLine;
    private int spr0BlockSel;
    int vc;
    int vcBase;
    int rc;
    int vmli;
    int vPos;
    int mpos;
    boolean gfxVisible;
    boolean paintBorder;
    int borderColor;
    int bgColor;
    int irqMask;
    int irqFlags;
    boolean extended;
    boolean multiCol;
    boolean blankRow;
    boolean hideColumn;
    int[] multiColor;
    int[] collissionMask;
    Sprite[] sprites;
    private Color[] colors;
    private int horizScroll;
    private int vScroll;
    private Image image;
    private Graphics g2;
    private boolean debug;
    private int charMemoryIndex;
    private int[] vicCharCache;
    private int[] vicColCache;
    public int[] vicMemory;
    public Image screen;
    private Graphics scrGfx;
    private MemoryImageSource mis;
    int[] mem;
    int rnd;
    String message;
    String tmsg;
    int vbeam;
    boolean updating;
    boolean displayEnabled;
    boolean irqTriggered;
    long nextSID;
    long lastLine;
    long firstLine;
    long lastIRQ;
    int frq;
    private static final int[] IO_ADDRAND;
    private int lastRead;
    int lastX;
    int lastDLine;
    byte vicState;
    int oldDelta;
    int xPos;
    int lastSpriteRead;
    long lastCycle;
    private int accumulator;
    private int freq;
    private boolean msb_rising;
    private int shift_register;
    public static final int IMG_TOTWIDTH = 384;
    public static final int IMG_TOTHEIGHT = 284;
    public Image crtImage;
    long repaint;
    
    public C64Screen(final IMonitor monitor, final boolean double1) {
        this.darks = new Color[32];
        this.lites = new Color[32];
        this.colIndex = 0;
        this.soundOn = true;
        this.scanRate = 50;
        this.targetScanTime = 20000;
        this.actualScanTime = 20000;
        this.sidUpdate = 1000;
        this.lastScan = 0L;
        this.nextScanLine = 0L;
        this.nextIOUpdate = 0L;
        this.DOUBLE = false;
        this.reset = 100;
        this.iecLines = 0;
        this.cia2PRA = 0;
        this.cia2DDRA = 0;
        this.trackSound = null;
        this.motorSound = null;
        this.lastTrack = 0;
        this.lastSector = 0;
        this.ledOn = false;
        this.motorOn = false;
        this.emulateDisk = false;
        this.cbmcolor = VICConstants.COLOR_SETS[0];
        this.vicBase = 0;
        this.badLine = false;
        this.vc = 0;
        this.vcBase = 0;
        this.rc = 0;
        this.vmli = 0;
        this.vPos = 0;
        this.mpos = 0;
        this.gfxVisible = false;
        this.paintBorder = false;
        this.borderColor = this.cbmcolor[0];
        this.bgColor = this.cbmcolor[1];
        this.irqMask = 0;
        this.irqFlags = 0;
        this.extended = false;
        this.multiCol = false;
        this.blankRow = false;
        this.hideColumn = false;
        this.multiColor = new int[4];
        this.collissionMask = new int[432];
        this.sprites = new Sprite[8];
        this.colors = null;
        this.horizScroll = 0;
        this.vScroll = 0;
        this.debug = false;
        this.charMemoryIndex = 0;
        this.vicCharCache = new int[40];
        this.vicColCache = new int[40];
        this.vicMemory = new int[4096];
        this.screen = null;
        this.scrGfx = null;
        this.mis = null;
        this.mem = new int[112896];
        this.rnd = 754;
        this.tmsg = "";
        this.vbeam = 0;
        this.updating = false;
        this.displayEnabled = true;
        this.irqTriggered = false;
        this.nextSID = 0L;
        this.lastLine = 0L;
        this.firstLine = 0L;
        this.lastIRQ = 0L;
        this.lastRead = 0;
        this.lastX = 0;
        this.lastDLine = 0;
        this.vicState = 0;
        this.oldDelta = 0;
        this.xPos = 0;
        this.lastSpriteRead = -2;
        this.lastCycle = 0L;
        this.accumulator = 0;
        this.freq = 100;
        this.msb_rising = false;
        this.shift_register = 0;
        this.repaint = 0L;
        this.monitor = monitor;
        this.DOUBLE = double1;
        this.setScanRate(50.0);
        this.makeColors(this.darks, C64Screen.DARKER_0, C64Screen.DARKER_N);
        this.makeColors(this.lites, C64Screen.LIGHTER_0, C64Screen.LIGHTER_N);
    }
    
    private void makeColors(final Color[] array, final Color color, final Color color2) {
        final int alpha = color.getAlpha();
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha2 = color2.getAlpha();
        final int red2 = color2.getRed();
        final int green2 = color2.getGreen();
        final int blue2 = color2.getBlue();
        for (int n = 16, i = 0; i < n; ++i) {
            array[i] = (array[32 - i - 1] = new Color(((n - i) * red + i * red2) / n, ((n - i) * green + i * green2) / n, ((n - i) * blue + i * blue2) / n, ((n - i) * alpha + i * alpha2) / n));
        }
    }
    
    public void setDouble(final boolean double1) {
        this.DOUBLE = double1;
        this.image = null;
    }
    
    public void setColorSet(final int n) {
        if (n >= 0 && n < VICConstants.COLOR_SETS.length) {
            this.cbmcolor = VICConstants.COLOR_SETS[n];
        }
    }
    
    public SIDMixer getMixer() {
        return this.mixer;
    }
    
    public SID6581[] getSIDs() {
        return this.sid;
    }
    
    public CIA[] getCIAs() {
        return this.cia;
    }
    
    public void setScanRate(final double n) {
        this.targetScanTime = (int)(1000000.0 / n);
        this.scanRate = (int)n;
        this.sidUpdate = (int)(0.9692308f * 1000.0f * n / 50.0);
    }
    
    public int getScanRate() {
        return 1000000 / this.targetScanTime;
    }
    
    public int getActualScanRate() {
        return 1000000 / this.actualScanTime;
    }
    
    public JPanel getScreen() {
        return this.canvas;
    }
    
    public boolean ready() {
        return this.keyboard.ready;
    }
    
    public void dumpGfxStat() {
        this.monitor.info("Char MemoryIndex: 0x" + Integer.toString(this.charMemoryIndex, 16));
        this.monitor.info("CharSet adr: 0x" + Integer.toString(this.charSet, 16));
        this.monitor.info("VideoMode: " + this.videoMode);
        this.monitor.info("Vic Bank: 0x" + Integer.toString(this.vicBank, 16));
        this.monitor.info("Video Matrix: 0x" + Integer.toString(this.videoMatrix, 16));
        this.monitor.info("Text: extended = " + this.extended + " multicol = " + this.multiCol);
        this.monitor.info("24 Rows on? " + (((this.memory[65553] & 0x8) == 0x0) ? "yes" : "no"));
        this.monitor.info("YScroll = " + (this.memory[65553] & 0x7));
        this.monitor.info("d011 = " + this.memory[65553]);
        this.monitor.info("IRQ Latch: " + Integer.toString(this.memory[65561], 16));
        this.monitor.info("IRQ  Mask: " + Integer.toString(this.memory[65562], 16));
        this.monitor.info("IRQ RPos : " + (this.vicMemory[18] + ((this.vicMemory[17] & 0x80) << 1)));
        for (int i = 0; i < 8; ++i) {
            this.monitor.info("Sprite " + (i + 1) + " pos = " + (this.memory[65536 + i * 2] + (((this.memory[65552] & 1 << i) != 0x0) ? 256 : 0)) + ", " + this.memory[65536 + i * 2 + 1]);
        }
        this.monitor.info("IRQFlags: " + this.getIRQFlags());
        this.monitor.info("NMIFlags: " + this.getNMIFlags());
    }
    
    public void setSoundOn(final boolean b) {
        this.soundOn = b;
        this.mixer.setSoundOn(b);
    }
    
    public void setStick(final boolean stick) {
        this.keyboard.setStick(stick);
    }
    
    public void registerHotKey(final int n, final String s, final Object o) {
        this.keyboard.registerHotKey(n, s, o);
    }
    
    public void setKeyboardEmulation(final boolean extendedKeyboardEmulation) {
        this.monitor.info("Keyboard extended: " + extendedKeyboardEmulation);
        this.keyboard.stickExits = !extendedKeyboardEmulation;
        this.keyboard.extendedKeyboardEmulation = extendedKeyboardEmulation;
    }
    
    public void init(final CPU cpu) {
        super.init(cpu);
        this.memory = cpu.getMemory();
        (this.c1541Chips = cpu.getDrive().chips).initIEC2(this);
        (this.c1541Chips = cpu.getDrive().chips).setObserver(this);
        for (int i = 0; i < this.sprites.length; ++i) {
            this.sprites[i] = new Sprite();
            this.sprites[i].spriteNo = i;
        }
        (this.cia = new CIA[2])[0] = new CIA(this.memory, 68608, this);
        this.cia[1] = new CIA(this.memory, 68864, this);
        this.keyboard = new Keyboard(this, this.cia[0], this.memory);
        this.canvas = new C64Canvas(this, this.DOUBLE, this.keyboard);
        try {
            this.sid = new SID6581[3];
            (this.sid[0] = new SID6581(this.memory, 66560)).init();
            (this.sid[1] = new SID6581(this.memory, 66567)).init();
            (this.sid[2] = new SID6581(this.memory, 66574)).init();
            this.sid[0].next = this.sid[2];
            this.sid[1].next = this.sid[0];
            this.sid[2].next = this.sid[1];
            this.mixer = new SIDMixerSE(this.sid, null);
        }
        catch (Throwable t) {
            this.monitor.error("Error while initializing SID chip" + t);
            this.sid = null;
        }
        this.charMemoryIndex = 118784;
        for (int j = 0; j < 109056; ++j) {
            this.mem[j] = this.cbmcolor[6];
        }
        (this.mis = new MemoryImageSource(384, 284, this.mem, 0, 384)).setAnimated(true);
        this.mis.setFullBufferUpdates(true);
        this.screen = this.canvas.createImage(this.mis);
        this.initUpdate();
    }
    
    public void update(final Object o, final Object o2) {
        if (o != this.c1541Chips) {
            this.message = (String)o2;
        }
        else {
            this.updateDisk(o, o2);
        }
    }
    
    void restoreKey(final boolean b) {
        if (b) {
            this.setNMI(1);
        }
        else {
            this.clearNMI(1);
        }
    }
    
    public int performRead(int n, final long n2) {
        final int n3 = n >> 8 & 0xF;
        n &= C64Screen.IO_ADDRAND[n3];
        final int n4 = this.memory[n + 12288];
        switch (n) {
            case 53278:
            case 53279: {
                final int n5 = this.vicMemory[n & 0xFF];
                this.vicMemory[n & 0xFF] = 0;
                return n5;
            }
            case 53273: {
                return this.irqFlags;
            }
            case 53274: {
                return this.irqMask;
            }
            case 54299: {
                return this.sid[2].lastSample() & 0xFF;
            }
            case 54300: {
                return this.sid[2].adsrVol & 0xFF;
            }
            case 54297: {
                final int[] memory = this.memory;
                final int n6 = 66585;
                ++memory[n6];
                break;
            }
            case 54298: {
                this.memory[66586] = 255;
                break;
            }
            case 56320: {
                return this.keyboard.readDC00(this.cpu.lastReadOP);
            }
            case 56321: {
                return this.keyboard.readDC01(this.cpu.lastReadOP);
            }
            case 56576: {
                return (((this.cia2PRA | ~this.cia2DDRA) & 0x3F) | (this.iecLines & this.c1541Chips.iecLines)) & 0xFF;
            }
        }
        if (n3 == 13) {
            return this.cia[1].performRead(n + 12288, n2);
        }
        if (n3 == 12) {
            return this.cia[0].performRead(n + 12288, n2);
        }
        return n4;
    }
    
    public void updateCIA(final long n) {
        if (this.nextIOUpdate < n) {
            final int ioTic;
            if ((ioTic = this.ioTic()) != 0) {
                if ((ioTic & 0x1) == 0x1) {
                    this.setIRQ(2);
                }
                if ((ioTic & 0x2) == 0x2) {
                    this.setNMI(2);
                }
            }
            else {
                this.clearIRQ(2);
                this.clearNMI(2);
            }
            this.nextIOUpdate += 17L;
        }
        if (this.nextIOUpdate > this.cia[0].nextCIAUpdate) {
            this.nextIOUpdate = this.cia[0].nextCIAUpdate;
        }
        if (this.nextIOUpdate > this.cia[1].nextCIAUpdate) {
            this.nextIOUpdate = this.cia[1].nextCIAUpdate;
        }
    }
    
    public void performWrite(int n, int cia2DDRA, final long n2) {
        final int n3 = n >> 8 & 0xF;
        n &= C64Screen.IO_ADDRAND[n3];
        this.memory[n + 12288] = cia2DDRA;
        n += 12288;
        switch (n) {
            case 66564: {
                this.sid[0].setControl(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66565: {
                this.sid[0].setAD(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66566: {
                this.sid[0].setSR(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66562:
            case 66563: {
                this.sid[0].updatePulseWidth(n2);
                break;
            }
            case 66571: {
                this.sid[1].setControl(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66572: {
                this.sid[1].setAD(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66573: {
                this.sid[1].setSR(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66569:
            case 66570: {
                this.sid[1].updatePulseWidth(n2);
                break;
            }
            case 66578: {
                this.sid[2].setControl(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66579: {
                this.sid[2].setAD(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66580: {
                this.sid[2].setSR(cia2DDRA, this.cpu.cycles);
                break;
            }
            case 66576:
            case 66577: {
                this.sid[2].updatePulseWidth(n2);
                break;
            }
            case 66581: {
                this.mixer.setFilterCutoffLO(cia2DDRA & 0x7);
                break;
            }
            case 66582: {
                this.mixer.setFilterCutoffHI(cia2DDRA);
                break;
            }
            case 66583: {
                this.mixer.setFilterResonance(cia2DDRA >> 4);
                this.mixer.setFilterOn(cia2DDRA & 0xF);
                break;
            }
            case 66584: {
                this.mixer.setVolume(cia2DDRA & 0xF, this.cpu.cycles);
                this.mixer.setFilterCtrl(cia2DDRA);
                break;
            }
            case 65536:
            case 65538:
            case 65540:
            case 65542:
            case 65544:
            case 65546:
            case 65548:
            case 65550: {
                final int n4 = n - 12288 - 53248 >> 1;
                final Sprite sprite = this.sprites[n4];
                sprite.x &= 0x100;
                final Sprite sprite2 = this.sprites[n4];
                sprite2.x += cia2DDRA;
                break;
            }
            case 65552: {
                for (int i = 0, n5 = 1; i < 8; ++i, n5 <<= 1) {
                    final Sprite sprite3 = this.sprites[i];
                    sprite3.x &= 0xFF;
                    final Sprite sprite4 = this.sprites[i];
                    sprite4.x |= (((cia2DDRA & n5) != 0x0) ? 256 : 0);
                }
                break;
            }
            case 65553: {
                this.vicMemory[17] = cia2DDRA;
                if (this.vScroll != (cia2DDRA & 0x7)) {
                    this.vScroll = (cia2DDRA & 0x7);
                    final boolean badLine = this.badLine;
                    this.badLine = (this.displayEnabled && this.vbeam >= 48 && this.vbeam <= 247 && (this.vbeam & 0x7) == this.vScroll);
                    if (badLine != this.badLine) {
                        System.out.println("Changed badline to: " + this.badLine + " at " + (this.cpu.cycles - this.lastLine));
                    }
                }
                this.extended = ((cia2DDRA & 0x40) != 0x0);
                this.blankRow = ((cia2DDRA & 0x8) == 0x0);
                this.videoMode = ((this.extended ? 2 : 0) | (this.multiCol ? 1 : 0) | (((cia2DDRA & 0x20) != 0x0) ? 4 : 0));
                this.memory[65553] = ((this.memory[65553] & 0x7F) | (this.vbeam & 0x100) >> 1);
                break;
            }
            case 65554: {
                this.vicMemory[18] = cia2DDRA;
                this.memory[n] = (this.vbeam & 0xFF);
                break;
            }
            case 65557: {
                for (int j = 0, n6 = 1; j < 8; ++j, n6 <<= 1) {
                    this.sprites[j].enabled = ((cia2DDRA & n6) != 0x0);
                }
                break;
            }
            case 65558: {
                this.horizScroll = (cia2DDRA & 0x7);
                this.multiCol = ((cia2DDRA & 0x10) != 0x0);
                this.hideColumn = ((cia2DDRA & 0x8) == 0x0);
                this.videoMode = ((this.extended ? 2 : 0) | (this.multiCol ? 1 : 0) | (((this.memory[65553] & 0x20) != 0x0) ? 4 : 0));
                break;
            }
            case 65559: {
                for (int k = 0, n7 = 1; k < 8; ++k, n7 <<= 1) {
                    this.sprites[k].expandY = ((cia2DDRA & n7) != 0x0);
                }
                break;
            }
            case 65561: {
                if ((cia2DDRA & 0x80) != 0x0) {
                    cia2DDRA = 255;
                }
                this.irqFlags &= (0xFF ^ cia2DDRA);
                this.vicMemory[25] = (this.memory[n] = this.irqFlags);
                if ((this.irqMask & 0xF & this.irqFlags) == 0x0) {
                    this.clearIRQ(1);
                    break;
                }
                break;
            }
            case 65562: {
                this.irqMask = cia2DDRA;
                if ((this.irqMask & 0xF & this.irqFlags) != 0x0) {
                    this.irqFlags |= 0x80;
                    this.setIRQ(1);
                    break;
                }
                this.clearIRQ(1);
                break;
            }
            case 65565: {
                for (int l = 0, n8 = 1; l < 8; ++l, n8 <<= 1) {
                    this.sprites[l].expandX = ((cia2DDRA & n8) != 0x0);
                }
                break;
            }
            case 65563: {
                for (int n9 = 0, n10 = 1; n9 < 8; ++n9, n10 <<= 1) {
                    this.sprites[n9].priority = ((cia2DDRA & n10) != 0x0);
                }
                break;
            }
            case 65564: {
                for (int n11 = 0, n12 = 1; n11 < 8; ++n11, n12 <<= 1) {
                    this.sprites[n11].multicolor = ((cia2DDRA & n12) != 0x0);
                }
                break;
            }
            case 65568: {
                this.borderColor = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory = this.memory;
                final int n13 = 12288 + n;
                memory[n13] |= 0xF0;
                break;
            }
            case 65569: {
                this.bgColor = this.cbmcolor[cia2DDRA & 0xF];
                for (int n14 = 0; n14 < 8; ++n14) {
                    this.sprites[n14].color[0] = this.bgColor;
                }
                final int[] memory2 = this.memory;
                final int n15 = 12288 + n;
                memory2[n15] |= 0xF0;
                break;
            }
            case 65573: {
                for (int n16 = 0; n16 < 8; ++n16) {
                    this.sprites[n16].color[1] = this.cbmcolor[cia2DDRA & 0xF];
                }
                final int[] memory3 = this.memory;
                final int n17 = 12288 + n;
                memory3[n17] |= 0xF0;
                break;
            }
            case 65574: {
                for (int n18 = 0; n18 < 8; ++n18) {
                    this.sprites[n18].color[3] = this.cbmcolor[cia2DDRA & 0xF];
                }
                final int[] memory4 = this.memory;
                final int n19 = 12288 + n;
                memory4[n19] |= 0xF0;
                break;
            }
            case 65575: {
                this.sprites[0].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory5 = this.memory;
                final int n20 = 12288 + n;
                memory5[n20] |= 0xF0;
                break;
            }
            case 65576: {
                this.sprites[1].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory6 = this.memory;
                final int n21 = 12288 + n;
                memory6[n21] |= 0xF0;
                break;
            }
            case 65577: {
                this.sprites[2].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory7 = this.memory;
                final int n22 = 12288 + n;
                memory7[n22] |= 0xF0;
                break;
            }
            case 65578: {
                this.sprites[3].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory8 = this.memory;
                final int n23 = 12288 + n;
                memory8[n23] |= 0xF0;
                break;
            }
            case 65579: {
                this.sprites[4].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory9 = this.memory;
                final int n24 = 12288 + n;
                memory9[n24] |= 0xF0;
                break;
            }
            case 65580: {
                this.sprites[5].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory10 = this.memory;
                final int n25 = 12288 + n;
                memory10[n25] |= 0xF0;
                break;
            }
            case 65581: {
                this.sprites[6].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory11 = this.memory;
                final int n26 = 12288 + n;
                memory11[n26] |= 0xF0;
                break;
            }
            case 65582: {
                this.sprites[7].color[2] = this.cbmcolor[cia2DDRA & 0xF];
                final int[] memory12 = this.memory;
                final int n27 = 12288 + n;
                memory12[n27] |= 0xF0;
                break;
            }
            case 68608:
            case 68609:
            case 68610:
            case 68611: {
                this.cia[0].performWrite(n, cia2DDRA, this.cpu.cycles);
                this.keyboard.updateKeyboard();
                break;
            }
            case 68864: {
                this.cia[1].performWrite(n, cia2DDRA, this.cpu.cycles);
                this.setVideoMem();
                final int cia2PRA = this.cia2PRA;
                this.cia2PRA = cia2DDRA;
                cia2DDRA = (~this.cia2PRA & this.cia2DDRA);
                final int iecLines = this.iecLines;
                this.iecLines = ((cia2DDRA << 2 & 0x80) | (cia2DDRA << 2 & 0x40) | (cia2DDRA << 1 & 0x10));
                if (((iecLines ^ this.iecLines) & 0x10) != 0x0) {
                    this.c1541Chips.atnChanged((this.iecLines & 0x10) == 0x0);
                    break;
                }
                break;
            }
            case 68866: {
                this.cia2DDRA = cia2DDRA;
                System.out.println("C64: Wrote to DDRA (IEC): " + Integer.toHexString(cia2DDRA));
                this.cia[1].performWrite(n, cia2DDRA, this.cpu.cycles);
                break;
            }
            case 65560: {
                this.memory[n] = cia2DDRA;
                this.setVideoMem();
                break;
            }
            default: {
                if (n3 == 13) {
                    this.cia[1].performWrite(n, cia2DDRA, n2);
                }
                if (n3 == 12) {
                    this.cia[0].performWrite(n, cia2DDRA, n2);
                    break;
                }
                break;
            }
        }
    }
    
    private void printIECLines() {
        System.out.print("IEC/F: ");
        if ((this.iecLines & 0x10) == 0x0) {
            System.out.print("A1");
        }
        else {
            System.out.print("A0");
        }
        System.out.print(" C" + (((this.iecLines & 0x40) == 0x0) ? 1 : 0));
        System.out.print(" D" + (((this.iecLines & 0x80) == 0x0) ? 1 : 0));
        System.out.print(" c" + (((this.c1541Chips.iecLines & 0x40) == 0x0) ? 1 : 0));
        System.out.print(" d" + (((this.c1541Chips.iecLines & 0x80) == 0x0) ? 1 : 0));
        System.out.println(" => C" + (((this.iecLines & this.c1541Chips.iecLines & 0x80) == 0x0) ? 1 : 0) + " D" + (((this.iecLines & this.c1541Chips.iecLines & 0x40) == 0x0) ? 1 : 0));
    }
    
    private void setVideoMem() {
        this.vicBank = 3 - (this.memory[68864] & 0x3) << 14;
        this.charSet = (this.vicBank | (this.memory[65560] & 0xE) << 10);
        this.videoMatrix = (this.vicBank | (this.memory[65560] & 0xF0) << 6);
        this.vicBase = (this.vicBank | (this.memory[65560] & 0x8) << 10);
        this.spr0BlockSel = 1016 + this.videoMatrix;
        if ((this.memory[65560] & 0xC) != 0x4 || (this.vicBank & 0x4000) == 0x4000) {
            this.charMemoryIndex = this.charSet;
        }
        else {
            this.charMemoryIndex = (((this.memory[65560] & 0x2) == 0x0) ? 0 : 2048) + 118784;
        }
    }
    
    public int ioTic() {
        boolean b = false;
        boolean b2 = false;
        if (this.cia[1].updateCIA(this.cpu.cycles) > 0) {
            b2 = true;
        }
        else if (this.cia[0].updateCIA(this.cpu.cycles) > 0) {
            b = true;
        }
        return b2 ? 2 : (b ? 1 : 0);
    }
    
    private void initUpdate() {
        this.vc = 0;
        this.vcBase = 0;
        this.vmli = 0;
        this.updating = true;
        for (int i = 0; i < 8; ++i) {
            this.sprites[i].nextByte = 0;
            this.sprites[i].painting = false;
            this.sprites[i].spriteReg = 0;
        }
        if (this.colors == null) {
            this.colors = new Color[16];
            for (int j = 0; j < 16; ++j) {
                this.colors[j] = new Color(this.cbmcolor[j]);
            }
        }
        this.canvas.setBackground(this.colors[this.memory[65568] & 0xF]);
    }
    
    public void updateChips(final long lastCycle) {
        if (lastCycle > this.nextIOUpdate) {
            this.updateCIA(lastCycle);
        }
        if (this.lastCycle + 1L < lastCycle) {
            System.out.println("More than one cycle passed: " + (lastCycle - this.lastCycle) + " at " + lastCycle + " PC: " + Integer.toHexString(this.cpu.pc));
        }
        if (this.lastCycle == lastCycle) {
            System.out.println("No diff since last update!!!: " + (lastCycle - this.lastCycle) + " at " + lastCycle + " PC: " + Integer.toHexString(this.cpu.pc));
        }
        this.lastCycle = lastCycle;
        final int oldDelta = (int)(lastCycle - this.lastLine);
        if (this.badLine) {
            this.gfxVisible = true;
        }
        Label_2106: {
            switch (this.vicState) {
                case 0: {
                    this.vbeam = (this.vbeam + 1) % 312;
                    this.vPos = this.vbeam - 16;
                    if (this.vbeam == 15) {
                        ++this.colIndex;
                        if (this.colIndex >= 32) {
                            this.colIndex = 0;
                        }
                        this.initUpdate();
                    }
                    if ((this.irqMask & 0x2) != 0x0 && this.vicMemory[31] != 0 && (this.irqFlags & 0x2) == 0x0) {
                        this.irqFlags |= 0x52;
                        this.setIRQ(1);
                    }
                    if ((this.irqMask & 0x4) != 0x0 && this.vicMemory[30] != 0 && (this.irqFlags & 0x4) == 0x0) {
                        this.irqFlags |= 0x54;
                        this.setIRQ(1);
                    }
                    this.memory[65554] = (this.vbeam & 0xFF);
                    this.memory[65553] = ((this.memory[65553] & 0x7F) | (this.vbeam & 0x100) >> 1);
                    int n = ((this.vicMemory[17] & 0x80) << 1) + this.vicMemory[18];
                    if (n > 312) {
                        n &= 0xFF;
                    }
                    if ((this.irqFlags & 0x1) == 0x0 && n == this.vbeam) {
                        this.irqFlags |= 0x1;
                        if ((this.irqMask & 0x1) != 0x0) {
                            this.irqFlags |= 0x80;
                            this.irqTriggered = true;
                            this.setIRQ(1);
                            this.lastIRQ = this.cpu.cycles;
                        }
                    }
                    else {
                        this.irqTriggered = false;
                    }
                    if (this.vPos < 0 || this.vPos >= 284) {
                        this.cpu.baLowUntil = 0L;
                        this.vicState = 63;
                        break;
                    }
                    if (this.vbeam == 48) {
                        this.displayEnabled = ((this.memory[65553] & 0x10) != 0x0);
                    }
                    this.badLine = (this.displayEnabled && this.vbeam >= 48 && this.vbeam <= 247 && (this.vbeam & 0x7) == this.vScroll);
                    this.lastX = 0;
                    for (int i = 0; i < 384; ++i) {
                        this.collissionMask[i] = 0;
                    }
                }
                case 1: {
                    if (oldDelta < 1) {
                        this.vicState = 1;
                        break;
                    }
                    if (this.sprites[3].dma) {
                        this.sprites[3].readSpriteData();
                    }
                    if (this.sprites[5].dma) {
                        this.cpu.baLowUntil = this.lastLine + 6L;
                    }
                }
                case 3: {
                    if (oldDelta < 3) {
                        this.vicState = 3;
                        break;
                    }
                    if (this.sprites[4].dma) {
                        this.sprites[4].readSpriteData();
                    }
                    if (this.sprites[6].dma) {
                        this.cpu.baLowUntil = this.lastLine + 8L;
                    }
                }
                case 5: {
                    if (oldDelta < 5) {
                        this.vicState = 5;
                        break;
                    }
                    if (this.sprites[5].dma) {
                        this.sprites[5].readSpriteData();
                    }
                    if (this.sprites[7].dma) {
                        this.cpu.baLowUntil = this.lastLine + 10L;
                    }
                }
                case 7: {
                    if (oldDelta < 7) {
                        this.vicState = 7;
                        break;
                    }
                    if (this.sprites[6].dma) {
                        this.sprites[6].readSpriteData();
                    }
                }
                case 9: {
                    if (oldDelta < 9) {
                        this.vicState = 9;
                        break;
                    }
                    if (this.sprites[7].dma) {
                        this.sprites[7].readSpriteData();
                    }
                    if (this.blankRow) {
                        if (this.vbeam == 247) {
                            this.paintBorder = true;
                        }
                    }
                    else {
                        if (this.vbeam == 251) {
                            this.paintBorder = true;
                        }
                        if (this.vbeam == 51) {
                            this.paintBorder = false;
                            for (int j = 0; j < 7; ++j) {
                                if (!this.sprites[j].painting) {
                                    this.sprites[j].lineFinished = true;
                                }
                            }
                        }
                    }
                    if (this.vbeam == 55) {
                        this.paintBorder = false;
                        for (int k = 0; k < 7; ++k) {
                            if (!this.sprites[k].painting) {
                                this.sprites[k].lineFinished = true;
                            }
                        }
                    }
                }
                case 12: {
                    if (oldDelta < 11) {
                        this.vicState = 12;
                        break;
                    }
                    if (this.badLine) {
                        this.cpu.baLowUntil = this.lastLine + 54L;
                    }
                }
                case 14: {
                    if (oldDelta < 13) {
                        this.vicState = 14;
                        break;
                    }
                    this.paintBorder(this.mpos = this.vPos * 384, 16);
                    this.mpos += 16;
                    this.vc = this.vcBase;
                    this.vmli = 0;
                    if (this.badLine) {
                        this.cpu.baLowUntil = this.lastLine + 54L;
                        this.rc = 0;
                    }
                }
                case 15: {
                    if (oldDelta < 15) {
                        this.vicState = 15;
                        break;
                    }
                    this.paintBorder(this.mpos, 16);
                    this.mpos += 16;
                    for (int l = 0; l < 8; ++l) {
                        if (this.sprites[l].nextByte == 63) {
                            this.sprites[l].dma = false;
                        }
                    }
                }
                case 17: {
                    if (oldDelta < 16) {
                        this.vicState = 17;
                        break;
                    }
                    if (this.badLine) {
                        this.cpu.baLowUntil = this.lastLine + 54L;
                        this.vicCharCache[0] = this.memory[this.videoMatrix + (this.vcBase & 0x3FF)];
                        this.vicColCache[0] = this.memory[67584 + (this.vcBase & 0x3FF)];
                    }
                    for (int n2 = 0; n2 < this.horizScroll; ++n2) {
                        this.mem[this.mpos + n2] = this.bgColor;
                    }
                    this.drawGraphics(this.mpos + this.horizScroll, 1);
                    if (this.hideColumn) {
                        this.paintBorder(this.mpos, 8);
                    }
                    this.mpos += 8;
                    this.oldDelta = 16;
                    this.xPos = 40;
                    this.vicState = 18;
                    if (oldDelta == 16) {
                        break;
                    }
                }
                case 18: {
                    int n3 = oldDelta - this.oldDelta;
                    this.oldDelta = oldDelta;
                    if (this.vmli + n3 > 40) {
                        n3 = 40 - this.vmli;
                    }
                    if (this.badLine) {
                        this.cpu.baLowUntil = this.lastLine + 54L;
                        for (int vmli = this.vmli; vmli < this.vmli + n3; ++vmli) {
                            this.vicCharCache[vmli] = this.memory[this.videoMatrix + (this.vcBase + vmli & 0x3FF)];
                            this.vicColCache[vmli] = this.memory[67584 + (this.vcBase + vmli & 0x3FF)];
                        }
                    }
                    this.drawGraphics(this.mpos + this.horizScroll, n3);
                    if (!this.paintBorder) {
                        this.drawSprites(this.lastX, this.xPos);
                    }
                    this.lastX = this.xPos;
                    this.xPos += n3 << 3;
                    this.mpos += n3 << 3;
                    if (this.vmli < 40) {
                        if (oldDelta < 54) {
                            break;
                        }
                        int n4 = 1;
                        final int n5 = this.vPos + 16;
                        for (int n6 = 0; n6 < 8; ++n6) {
                            final Sprite sprite = this.sprites[n6];
                            if (sprite.enabled && this.memory[65537 + n6 * 2] == (n5 & 0xFF) && n5 < 270) {
                                sprite.nextByte = 0;
                                sprite.dma = true;
                                sprite.expFlipFlop = true;
                            }
                            n4 <<= 1;
                        }
                        if (this.sprites[0].dma) {
                            this.cpu.baLowUntil = this.lastLine + 59L;
                            break;
                        }
                        break;
                    }
                    else {
                        if (!this.paintBorder) {
                            this.drawSprites(this.lastX, this.xPos);
                        }
                        break Label_2106;
                    }
                    break;
                }
                case 57: {
                    if (oldDelta < 56) {
                        this.vicState = 57;
                        break;
                    }
                    if (this.hideColumn) {
                        this.paintBorder(this.mpos - 8, 8);
                    }
                    for (int n7 = 0; n7 < 8; ++n7) {
                        final Sprite sprite2 = this.sprites[n7];
                        if (!sprite2.dma) {
                            sprite2.painting = false;
                        }
                    }
                    if (this.sprites[1].dma) {
                        this.cpu.baLowUntil = this.lastLine + 61L;
                    }
                }
                case 58: {
                    if (oldDelta < 57) {
                        this.vicState = 58;
                        break;
                    }
                    for (int n8 = 0; n8 < 8; ++n8) {
                        final Sprite sprite3 = this.sprites[n8];
                        if (sprite3.dma) {
                            sprite3.painting = true;
                        }
                    }
                    if (this.rc == 7) {
                        this.vcBase = this.vc;
                        this.gfxVisible = false;
                    }
                    if (this.badLine || this.gfxVisible) {
                        this.rc = (this.rc + 1 & 0x7);
                        this.gfxVisible = true;
                    }
                    this.paintBorder(this.mpos, 16);
                    this.mpos += 16;
                    if (this.sprites[0].painting) {
                        this.sprites[0].readSpriteData();
                    }
                    if (this.sprites[2].dma) {
                        this.cpu.baLowUntil = this.lastLine + 63L;
                    }
                }
                case 60: {
                    if (oldDelta < 59) {
                        this.vicState = 60;
                        break;
                    }
                    this.paintBorder(this.mpos, 16);
                    this.mpos += 16;
                    if (this.sprites[1].painting) {
                        this.sprites[1].readSpriteData();
                    }
                }
                case 62: {
                    if (oldDelta < 61) {
                        this.vicState = 62;
                        break;
                    }
                    if (this.sprites[2].painting) {
                        this.sprites[2].readSpriteData();
                    }
                    if (this.sprites[3].dma) {
                        this.cpu.baLowUntil = this.lastLine + 65L;
                    }
                    this.vicState = 63;
                }
                case 63: {
                    if (oldDelta < 62) {
                        break;
                    }
                    this.lastLine += 63L;
                    if (this.updating && this.vPos == 285) {
                        this.mis.newPixels();
                        this.canvas.repaint();
                        this.actualScanTime = (this.actualScanTime * 9 + (int)(this.mixer.getMicros() - this.lastScan)) / 10;
                        this.lastScan = this.mixer.getMicros();
                        this.updating = false;
                    }
                    if (lastCycle > this.nextSID) {
                        this.mixer.updateSound(lastCycle);
                        this.nextSID += this.sidUpdate;
                        if (this.nextSID < this.cpu.cycles) {
                            this.nextSID = this.cpu.cycles + 10L;
                        }
                    }
                    this.vicState = 0;
                    if (oldDelta > 62) {
                        this.updateChips(lastCycle);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private final void paintBorder(int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.mem[n++] = this.borderColor;
        }
    }
    
    private final void drawGraphics(int n, final int n2) {
        if (!this.gfxVisible || this.paintBorder) {
            int i;
            for (n = (i = n - this.horizScroll); i < n + (n2 << 3); ++i) {
                this.mem[i] = this.borderColor;
            }
            this.vmli += n2;
            return;
        }
        int n3 = this.vmli + n2;
        if (n3 > 40) {
            n3 = 40;
        }
        int n4 = (this.vmli << 3) + this.horizScroll + 32;
        int bgColor = this.bgColor;
        if ((this.memory[65553] & 0x20) == 0x0) {
            if (this.multiCol) {
                this.multiColor[0] = this.bgColor;
                this.multiColor[1] = this.cbmcolor[this.memory[65570] & 0xF];
                this.multiColor[2] = this.cbmcolor[this.memory[65571] & 0xF];
            }
            while (this.vmli < n3) {
                final int n6;
                final int n5 = this.cbmcolor[n6 = (this.vicColCache[this.vmli] & 0xF)];
                int n7;
                if (this.extended) {
                    final int n8;
                    n7 = this.charMemoryIndex + (((n8 = this.vicCharCache[this.vmli]) & 0x3F) << 3);
                    bgColor = this.cbmcolor[this.memory[65569 + (n8 >> 6)] & 0xF];
                }
                else {
                    n7 = this.charMemoryIndex + (this.vicCharCache[this.vmli] << 3);
                }
                final int n9 = this.memory[n7 + this.rc];
                if (this.multiCol && n6 > 7) {
                    this.multiColor[3] = this.cbmcolor[n6 & 0x7];
                    for (int j = 0; j < 8; j += 2) {
                        final int n10 = n9 >> j & 0x3;
                        this.mem[n + 6 - j] = (this.mem[n + 7 - j] = this.multiColor[n10]);
                        int n11;
                        if (n10 > 1) {
                            n11 = 256;
                        }
                        else {
                            n11 = 0;
                        }
                        this.collissionMask[n4 + 7 - j] = (this.collissionMask[n4 + 6 - j] = n11);
                    }
                }
                else {
                    for (int k = 0; k < 8; ++k) {
                        if ((n9 & 1 << k) > 0) {
                            this.mem[n + 7 - k] = n5;
                            this.collissionMask[n4 + 7 - k] = 256;
                        }
                        else {
                            this.mem[n + 7 - k] = bgColor;
                            this.collissionMask[n4 + 7 - k] = 0;
                        }
                    }
                }
                if (this.multiCol && this.extended) {
                    for (int l = 0; l < 8; ++l) {
                        this.mem[n + 7 - l] = -16777216;
                    }
                }
                n += 8;
                ++this.vmli;
                ++this.vc;
                n4 += 8;
            }
        }
        else {
            int n12 = this.vicBase + (this.vc & 0x3FF) * 8 + this.rc;
            if (this.multiCol) {
                this.multiColor[0] = this.bgColor;
            }
            while (this.vmli < n3) {
                final int n13 = this.cbmcolor[(this.vicCharCache[this.vmli] & 0xF0) >> 4];
                final int n14 = this.cbmcolor[this.vicCharCache[this.vmli] & 0xF];
                final int n15 = this.memory[n12];
                if (this.multiCol) {
                    this.multiColor[1] = this.cbmcolor[this.vicCharCache[this.vmli] >> 4 & 0xF];
                    this.multiColor[2] = this.cbmcolor[this.vicCharCache[this.vmli] & 0xF];
                    this.multiColor[3] = this.cbmcolor[this.vicColCache[this.vmli] & 0xF];
                    for (int n16 = 0; n16 < 8; n16 += 2) {
                        final int n17;
                        this.mem[n + 6 - n16] = (this.mem[n + 7 - n16] = this.multiColor[n17 = (n15 >> n16 & 0x3)]);
                        int n18;
                        if (n17 > 1) {
                            n18 = 256;
                        }
                        else {
                            n18 = 0;
                        }
                        this.collissionMask[n4 + 7 - n16] = (this.collissionMask[n4 + 6 - n16] = n18);
                    }
                }
                else {
                    for (int n19 = 0; n19 < 8; ++n19) {
                        if ((n15 & 1 << n19) > 0) {
                            this.mem[7 - n19 + n] = n13;
                            this.collissionMask[n4 + 7 - n19] = 256;
                        }
                        else {
                            this.mem[7 - n19 + n] = n14;
                            this.collissionMask[n4 + 7 - n19] = 0;
                        }
                    }
                }
                if (this.extended) {
                    for (int n20 = 0; n20 < 8; ++n20) {
                        this.mem[n + 7 - n20] = -16777216;
                    }
                }
                n12 = (n12 + 8 & 0xFFFF);
                n += 8;
                n4 += 8;
                ++this.vc;
                ++this.vmli;
            }
        }
    }
    
    private final void drawSprites(final int n, final int n2) {
        int n3 = 256;
        final int n4 = 32 + (this.hideColumn ? 8 : 0);
        for (int i = 7; i >= 0; --i) {
            final Sprite sprite = this.sprites[i];
            n3 >>= 1;
            if (!sprite.lineFinished) {
                if (sprite.painting) {
                    final int n5 = sprite.x + 8;
                    final int n6 = this.vPos * 384;
                    if (n5 < n2) {
                        for (int j = (n > n5) ? n : n5; j < n2; ++j) {
                            final int pixel = sprite.getPixel();
                            if (pixel != 0 && j >= n4) {
                                final int[] collissionMask = this.collissionMask;
                                final int n7 = j;
                                final int n8 = collissionMask[n7] | n3;
                                collissionMask[n7] = n8;
                                final int n9 = n8;
                                if (!sprite.priority || (n9 & 0x100) == 0x0) {
                                    this.mem[n6 + j] = sprite.color[pixel];
                                }
                                if (n9 != n3) {
                                    if ((n9 & 0x100) != 0x0) {
                                        final int[] vicMemory = this.vicMemory;
                                        final int n10 = 31;
                                        vicMemory[n10] |= n3;
                                    }
                                    if ((n9 & 0xFF) != n3) {
                                        final int[] vicMemory2 = this.vicMemory;
                                        final int n11 = 30;
                                        vicMemory2[n11] |= (n9 & 0xFF);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.memory[65566] = this.vicMemory[30];
        this.memory[65567] = this.vicMemory[31];
    }
    
    public void reset() {
        this.initUpdate();
        this.mixer.reset();
        this.nextSID = this.cpu.cycles;
        this.lastLine = this.cpu.cycles;
        this.nextIOUpdate = this.cpu.cycles + 47L;
        this.vicState = 0;
        for (int i = 0; i < this.mem.length; ++i) {
            this.mem[i] = 0;
        }
        this.reset = 100;
        this.keyboard.reset();
        this.motorSound(false);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.image == null) {
            this.image = this.canvas.createImage(384, 284);
            (this.g2 = this.image.getGraphics()).setFont(new Font("Monospaced", 0, 11));
            this.crtImage = new BufferedImage(768, 568, 2);
            final Graphics graphics2 = this.crtImage.getGraphics();
            graphics2.setColor(C64Screen.TRANSPARENT_BLACK);
            for (int i = 0; i < 568; i += 2) {
                graphics2.drawLine(0, i, 768, i);
            }
        }
        this.g2.drawImage(this.screen, 0, 0, null);
        if (this.reset > 0) {
            this.g2.setColor(this.darks[this.colIndex]);
            int reset = 44;
            if (this.reset < 44) {
                reset = this.reset;
            }
            this.g2.drawString("JaC64 1.0 Beta1 - Java C64 - www.jac64.com", reset + 1, 9);
            this.g2.setColor(this.lites[this.colIndex]);
            this.g2.drawString("JaC64 1.0 Beta1 - Java C64 - www.jac64.com", reset, 8);
            --this.reset;
        }
        else {
            String string = "JaC64 ";
            if (this.message != null && this.message != "") {
                string += this.message;
            }
            else {
                this.colIndex = 0;
            }
            final String string2 = string + this.tmsg;
            this.g2.setColor(this.darks[this.colIndex]);
            this.g2.drawString(string2, 1, 9);
            this.g2.setColor(this.lites[this.colIndex]);
            this.g2.drawString(string2, 0, 8);
            if (this.ledOn) {
                this.g2.setColor(C64Screen.LED_ON);
            }
            else {
                this.g2.setColor(C64Screen.LED_OFF);
            }
            this.g2.fillRect(372, 3, 7, 1);
            this.g2.setColor(C64Screen.LED_BORDER);
            this.g2.drawRect(371, 2, 8, 2);
        }
        if (this.DOUBLE) {
            graphics.drawImage(this.image, 0, 0, 768, 568, null);
        }
        else {
            graphics.drawImage(this.image, 0, 0, null);
        }
    }
    
    public void updateDisk(final Object o, final Object o2) {
        final C1541Chips c1541Chips = this.c1541Chips;
        if (o2 == C1541Chips.HEAD_MOVED) {
            if (this.lastTrack != this.c1541Chips.currentTrack) {
                this.lastTrack = this.c1541Chips.currentTrack;
                this.trackSound();
            }
            else {
                this.trackSound();
            }
        }
        this.lastSector = this.c1541Chips.currentSector;
        if (this.motorOn != this.c1541Chips.motorOn) {
            this.motorSound(this.c1541Chips.motorOn);
        }
        this.tmsg = " track: " + this.lastTrack + " / " + this.lastSector;
        this.ledOn = this.c1541Chips.ledOn;
        this.motorOn = this.c1541Chips.motorOn;
    }
    
    private void trackSound() {
        if (this.trackSound != null) {
            this.trackSound.play();
        }
    }
    
    public void motorSound(final boolean b) {
        if (this.motorSound != null) {
            if (b) {
                this.motorSound.loop();
            }
            else {
                this.motorSound.stop();
            }
        }
    }
    
    public void setSounds(final AudioClip trackSound, final AudioClip motorSound) {
        this.trackSound = trackSound;
        this.motorSound = motorSound;
    }
    
    static {
        TRANSPARENT_BLACK = new Color(0, 0, 64, 64);
        DARKER_0 = new Color(0, 0, 64, 32);
        LIGHTER_0 = new Color(224, 224, 255, 48);
        DARKER_N = new Color(0, 0, 64, 112);
        LIGHTER_N = new Color(224, 224, 255, 160);
        LED_ON = new Color(96, 223, 96, 192);
        LED_OFF = new Color(32, 96, 32, 192);
        LED_BORDER = new Color(64, 96, 64, 160);
        IO_ADDRAND = new int[] { 53311, 53311, 53311, 53311, 54303, 54303, 54303, 54303, 55551, 55807, 56063, 56319, 56335, 56591, 57087, 57343 };
    }
    
    private class Sprite
    {
        boolean painting;
        boolean dma;
        int nextByte;
        int pointer;
        int x;
        int spriteNo;
        int spriteReg;
        boolean enabled;
        boolean expFlipFlop;
        boolean multicolor;
        boolean expandX;
        boolean expandY;
        boolean priority;
        boolean lineFinished;
        int pixelsLeft;
        int currentPixel;
        int[] color;
        
        private Sprite() {
            this.painting = false;
            this.dma = false;
            this.multicolor = false;
            this.expandX = false;
            this.expandY = false;
            this.priority = false;
            this.lineFinished = false;
            this.pixelsLeft = 0;
            this.currentPixel = 0;
            this.color = new int[4];
        }
        
        int getPixel() {
            if (this.lineFinished) {
                return 0;
            }
            --this.pixelsLeft;
            if (this.pixelsLeft > 0) {
                return this.currentPixel;
            }
            if (this.pixelsLeft <= 0 && this.spriteReg == 0) {
                this.currentPixel = 0;
                this.lineFinished = true;
                return 0;
            }
            if (this.multicolor) {
                this.currentPixel = (this.spriteReg & 0xC00000) >> 22;
                this.spriteReg = (this.spriteReg << 2 & 0xFFFFFF);
                this.pixelsLeft = 2;
            }
            else {
                this.currentPixel = (this.spriteReg & 0x800000) >> 22;
                this.spriteReg = (this.spriteReg << 1 & 0xFFFFFF);
                this.pixelsLeft = 1;
            }
            if (this.expandX) {
                this.pixelsLeft <<= 1;
            }
            return this.currentPixel;
        }
        
        void readSpriteData() {
            C64Screen.this.lastSpriteRead = this.spriteNo;
            this.pointer = C64Screen.this.vicBank + C64Screen.this.memory[C64Screen.this.spr0BlockSel + this.spriteNo] * 64;
            this.spriteReg = ((C64Screen.this.memory[this.pointer + this.nextByte++] & 0xFF) << 16 | (C64Screen.this.memory[this.pointer + this.nextByte++] & 0xFF) << 8 | C64Screen.this.memory[this.pointer + this.nextByte++]);
            if (!this.expandY) {
                this.expFlipFlop = false;
            }
            if (this.expFlipFlop) {
                this.nextByte -= 3;
            }
            this.expFlipFlop = !this.expFlipFlop;
            this.pixelsLeft = 0;
            this.lineFinished = false;
        }
    }
}
