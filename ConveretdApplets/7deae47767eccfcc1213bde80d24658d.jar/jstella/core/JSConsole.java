// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import jstella.cart.Cartridge;
import java.io.Serializable;

public class JSConsole implements Serializable
{
    private static final long serialVersionUID = -2643184369415869796L;
    public static final int DEFAULT_YSTART = 34;
    public static final int DEFAULT_DISPLAY_HEIGHT = 210;
    public static final int DEFAULT_DISPLAY_WIDTH = 160;
    private static final int TRASH_FRAMES = 60;
    private int myFrameRate;
    private JSConstants.DisplayFormat myDisplayFormat;
    private int myDisplayHeight;
    private int myDisplayWidth;
    private int myYStart;
    private transient IfcConsoleClient myConsoleClient;
    private JSController[] myControllers;
    private int mySwitches;
    private JSTIA myTIA;
    private JSSystem mySystem;
    private Cartridge myCart;
    private JSRiot myRiot;
    private JSVideo myVideo;
    private transient JSAudio myAudio;
    private int myTelevisionMode;
    
    public JSConsole(final IfcConsoleClient aConsoleClient) {
        this.myFrameRate = 60;
        this.myDisplayFormat = JSConstants.DisplayFormat.NTSC;
        this.myDisplayHeight = 210;
        this.myDisplayWidth = 160;
        this.myYStart = 34;
        this.myConsoleClient = null;
        this.myControllers = new JSController[2];
        this.mySwitches = 255;
        this.myTIA = null;
        this.mySystem = null;
        this.myCart = null;
        this.myRiot = null;
        this.myVideo = null;
        this.myAudio = null;
        this.myTelevisionMode = 0;
        this.setConsoleClient(aConsoleClient);
        this.initializeAudio();
        this.initializeVideo();
        this.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_RESET, false);
        this.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_SELECT, false);
        this.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_BW, false);
        this.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P0, false);
        this.flipSwitch(JSConstants.ConsoleSwitch.SWITCH_DIFFICULTY_P1, false);
        this.myControllers[0] = new JSController(JSConstants.Jack.LEFT);
        this.myControllers[1] = new JSController(JSConstants.Jack.RIGHT);
        this.mySystem = new JSSystem(this);
        this.myRiot = new JSRiot(this);
        this.myTIA = new JSTIA(this);
        this.mySystem.attach(this.myRiot);
        this.mySystem.attach(this.myTIA);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        final double zVersion = in.readDouble();
        in.defaultReadObject();
        final Object zAudioArrayObj = in.readUnshared();
        this.initializeAudio();
        if (zAudioArrayObj instanceof int[]) {
            final int[] zAudioRegisters = (int[])zAudioArrayObj;
            this.myAudio.setAudioRegisterData(zAudioRegisters);
        }
        this.adjustBackBuffer();
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.writeDouble(0.95);
        out.defaultWriteObject();
        final int[] zAudioRegisters = this.myAudio.getAudioRegisterData();
        out.writeUnshared(zAudioRegisters);
    }
    
    public void destroy() {
        if (this.myAudio != null) {
            this.myAudio.close();
            this.myAudio = null;
        }
    }
    
    private void detectDisplayHeight() throws JSException {
        this.mySystem.reset();
        for (int i = 0; i < 60; ++i) {
            this.myTIA.processFrame();
        }
        this.myDisplayHeight = this.myTIA.getDetectedYStop() - this.myTIA.getDetectedYStart();
        if (this.myDisplayHeight <= 0) {
            this.myDisplayHeight += this.myTIA.getVSyncOn();
        }
        this.myDisplayHeight = Math.min(this.myDisplayHeight, 300);
        if (this.myDisplayHeight < 100) {
            this.myDisplayHeight = 220;
            this.myYStart = 34;
            System.out.println("Warning: JStella was unable to detect the proper frame height");
        }
        this.myYStart = this.myTIA.getDetectedYStart();
        if (this.myDisplayFormat == JSConstants.DisplayFormat.PAL && this.myDisplayHeight == 210) {
            this.myDisplayHeight = 250;
        }
        this.adjustBackBuffer();
    }
    
    private void detectDisplayFormat() throws JSException {
        this.mySystem.reset();
        int zPalCount = 0;
        for (int i = 0; i < 60; ++i) {
            this.myTIA.processFrame();
        }
        for (int i = 0; i < 30; ++i) {
            this.myTIA.processFrame();
            if (this.myTIA.scanlines() > 285) {
                ++zPalCount;
            }
        }
        if (zPalCount >= 15) {
            this.setDisplayFormat(JSConstants.DisplayFormat.PAL);
        }
        else {
            this.setDisplayFormat(JSConstants.DisplayFormat.NTSC);
        }
    }
    
    public void changeYStart(final int aNewYStart) {
        if (aNewYStart != this.myYStart) {
            System.out.println("Debug - console: changing y-start - " + aNewYStart);
            this.myYStart = aNewYStart;
            this.myTIA.frameReset();
        }
    }
    
    public void changeDisplayHeight(final int aNewHeight) {
        if (aNewHeight != this.myDisplayHeight) {
            System.out.println("Debug - console: changing height - " + aNewHeight);
            this.myDisplayHeight = aNewHeight;
            this.adjustBackBuffer();
            this.myVideo.refresh();
            this.myTIA.frameReset();
        }
    }
    
    private void adjustBackBuffer() {
        this.getVideo().adjustBackBuffer(160, this.myDisplayHeight);
    }
    
    public int getDisplayWidth() {
        return this.myDisplayWidth;
    }
    
    public int getDisplayHeight() {
        return this.myDisplayHeight;
    }
    
    public int getYStart() {
        return this.myYStart;
    }
    
    public void setConsoleClient(final IfcConsoleClient aConsoleClient) {
        this.myConsoleClient = aConsoleClient;
    }
    
    public IfcConsoleClient getConsoleClient() {
        return this.myConsoleClient;
    }
    
    public JSController getController(final JSConstants.Jack jack) {
        return (jack == JSConstants.Jack.LEFT) ? this.myControllers[0] : this.myControllers[1];
    }
    
    public JSTIA getTIA() {
        return this.myTIA;
    }
    
    public JSVideo getVideo() {
        return this.myVideo;
    }
    
    public JSAudio getAudio() {
        return this.myAudio;
    }
    
    public JSSystem getSystem() {
        return this.mySystem;
    }
    
    public Cartridge getCartridge() {
        return this.myCart;
    }
    
    public JSRiot getRiot() {
        return this.myRiot;
    }
    
    public int getNominalFrameRate() {
        return this.myFrameRate;
    }
    
    public void setNominalFrameRate(final int aFrameRate) {
        this.myFrameRate = aFrameRate;
        this.getAudio().setNominalDisplayFrameRate(aFrameRate);
    }
    
    public JSConstants.DisplayFormat getDisplayFormat() {
        return this.myDisplayFormat;
    }
    
    private void setDisplayFormat(final JSConstants.DisplayFormat aDisplayFormat) {
        this.myDisplayFormat = aDisplayFormat;
        this.setNominalFrameRate(aDisplayFormat.getDisplayRate());
        this.getVideo().setTIAPalette(aDisplayFormat.getDisplayPalette());
        this.mySystem.reset();
    }
    
    private void reinstallCore() {
        this.mySystem.attach(this.myRiot);
        this.mySystem.attach(this.myTIA);
    }
    
    private void initializeVideo() {
        if (this.myVideo == null) {
            this.myVideo = new JSVideo(this);
        }
        this.getVideo().initialize();
    }
    
    private void initializeAudio() {
        if (this.myAudio == null) {
            this.myAudio = new JSAudio(this);
        }
        else {
            this.myAudio.close();
        }
        this.getAudio().setNominalDisplayFrameRate(this.getNominalFrameRate());
        this.getAudio().initialize();
    }
    
    public void setTelevisionMode(final int aTelevisionMode) {
        this.myTelevisionMode = aTelevisionMode;
    }
    
    public int getTelevisionMode() {
        return this.myTelevisionMode;
    }
    
    public void insertCartridge(final Cartridge aCart) throws JSException {
        this.insertCartridge(aCart, -1);
    }
    
    public void insertCartridge(final Cartridge aCart, final int aDisplayHeight) throws JSException {
        if (this.myCart != null && this.myCart != aCart) {
            this.mySystem.unattach(this.myCart);
            this.reinstallCore();
        }
        this.myVideo.clearBackBuffer();
        this.myVideo.clearBuffers();
        this.myCart = aCart;
        if (this.myCart != null) {
            this.myCart.setConsole(this);
            this.mySystem.attach(this.myCart);
        }
        this.mySystem.reset();
        this.detectDisplayFormat();
        if (aDisplayHeight <= 0) {
            this.detectDisplayHeight();
        }
        else {
            this.myDisplayHeight = aDisplayHeight;
        }
        this.adjustBackBuffer();
        if (this.myCart != null) {
            this.setTelevisionMode(1);
        }
        this.mySystem.reset();
        System.out.println("JStella display: YStart=" + this.myYStart + ", DisplayHeight=" + this.myDisplayHeight);
    }
    
    public static Cartridge createCartridge(final byte[] aROMData, final String aCartridgeType) throws JSException {
        if (aCartridgeType == null || aCartridgeType.trim().equals("")) {
            return Cartridge.create(aROMData);
        }
        return Cartridge.create(aROMData, aCartridgeType);
    }
    
    public static Cartridge createCartridge(final InputStream aInputStream, final String aCartridgeType) throws JSException {
        Cartridge zCart = null;
        try {
            if (aInputStream != null) {
                final byte[] zROMData = readByteArrayFromStream(aInputStream);
                if (zROMData == null) {
                    throw new JSException(JSException.ExceptionType.IO, "Could not read stream");
                }
                zCart = createCartridge(zROMData, aCartridgeType);
            }
            else {
                System.out.println("JSTELLA ERROR : attempting to read from a null stream");
            }
        }
        catch (IOException e) {
            throw new JSException(JSException.ExceptionType.IO, "Could not load ROM");
        }
        return zCart;
    }
    
    public static Cartridge createCartridge(final InputStream aInputStream) throws JSException {
        return createCartridge(aInputStream, null);
    }
    
    private static byte[] readByteArrayFromStream(final InputStream aStream) throws IOException {
        byte[] zReturn = null;
        final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
        int zInt = 0;
        while ((zInt = aStream.read()) != -1) {
            zBAOS.write(zInt);
        }
        zBAOS.close();
        zReturn = zBAOS.toByteArray();
        return zReturn;
    }
    
    public synchronized void doFrame() throws JSException {
        if (this.myVideo != null) {
            if (this.getTelevisionMode() == 1) {
                if (this.myCart != null) {
                    this.myTIA.processFrame();
                    this.myVideo.doFrameVideo();
                    this.myAudio.doFrameAudio(this.mySystem.getCycles(), this.getNominalFrameRate());
                }
            }
            else if (this.getTelevisionMode() == 3) {
                this.myVideo.doSnow();
            }
            else if (this.getTelevisionMode() == 2) {
                this.myVideo.doTestPattern();
            }
        }
        else {
            System.out.println("JStella Error : cannot animate");
            System.exit(1);
        }
    }
    
    public synchronized void updateVideoFrame() {
        if (this.myVideo != null) {
            this.myVideo.updateVideoFrame();
        }
    }
    
    public int readSwitches() {
        return this.mySwitches;
    }
    
    public void flipSwitch(final JSConstants.ConsoleSwitch aSwitchType, final boolean aSwitchDown) {
        if (aSwitchDown) {
            this.mySwitches &= ~aSwitchType.getBitMask();
        }
        else {
            this.mySwitches |= aSwitchType.getBitMask();
        }
    }
    
    public boolean isSwitchOn(final JSConstants.ConsoleSwitch aSwitch) {
        return (this.mySwitches & aSwitch.getBitMask()) == 0x0;
    }
    
    public void setPhosphorEnabled(final boolean aEnable) {
        this.getVideo().setPhosphorEnabled(aEnable);
    }
    
    public boolean isPhosphorEnabled() {
        return this.getVideo().getPhosphorEnabled();
    }
    
    public void setStereoSound(final boolean aEnable) {
        if (aEnable) {
            this.getAudio().setChannelNumber(2);
        }
        else {
            this.getAudio().setChannelNumber(1);
        }
    }
    
    public boolean isStereoSound() {
        return this.getAudio().getChannelNumber() == 2;
    }
    
    public void setSoundEnabled(final boolean aEnabled) {
        this.getAudio().setSoundEnabled(aEnabled);
    }
    
    public boolean isSoundEnabled() {
        return this.getAudio().isSoundEnabled();
    }
    
    public void grayCurrentFrame() {
        this.getVideo().grayCurrentFrame();
    }
    
    public void pauseAudio() {
        this.getAudio().pauseAudio();
    }
    
    public void debugDoFrame() {
        try {
            this.doFrame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void debugProcessFrame() {
        try {
            this.myTIA.processFrame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void debugDoFrameVideo() {
        try {
            this.myVideo.doFrameVideo();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
