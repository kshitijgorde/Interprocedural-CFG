// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.util.Vector;
import com.tn.components.PD765ADisk;
import com.tn.zx.peripheral.MicrodriveCartridge;
import com.tn.zx.peripheral.TzxFilePlayerStatus;
import com.tn.zx.peripheral.TapFilePlayerStatus;
import com.tn.zxspectrum.SpectrumStatus;
import com.tn.zxspectrum.snapshot.SnapshotParser;
import java.awt.event.KeyEvent;
import java.io.IOException;
import com.tn.zx.peripheral.ZxPrinterConverter;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.zx.peripheral.ZxPrinter;
import com.tn.util.SampleStreamerJSyn2;
import com.tn.util.SampleStreamerJavax;
import com.tn.zxspectrum.ZxSpectrumScreenConverter;
import com.tn.zxspectrum.ZxSpectrumScreenConverterPatternMethod;
import com.tn.components.MemoryFactory;
import java.lang.reflect.Constructor;
import java.awt.Component;
import java.awt.Graphics;
import com.tn.util.FileLoader;
import com.tn.zxspectrum.SpectrumSystemStatus;
import com.tn.util.SampleStreamer;
import com.tn.zxspectrum.ZxSpectrumKeyboardHandlerImpl;
import com.tn.zxspectrum.ZxSpectrumScreenConverterAbstract;
import com.tn.zxspectrum.ZxSpectrumSystem;
import java.awt.event.KeyListener;

public class ZZSpectrumController implements KeyListener
{
    private ZxSpectrumSystem ivSpectrumSystem;
    private ZxSpectrumScreenConverterAbstract ivScreenConverter;
    private ZxSpectrumKeyboardHandlerImpl ivKeyboardHandler;
    private SampleStreamer ivSampleStreamer;
    private volatile boolean ivEmulatorIsReserved;
    private volatile boolean ivEmulatorReserveRequest;
    private String ivPaletteId;
    private boolean ivModeTrueSpeed;
    private boolean ivEmulatorInitialized;
    private boolean ivEmulatorTerminate;
    private ZXSpectrumScreenCanvas ivScreenCanvas;
    private SpectrumSystemStatus ivSaveStatus;
    private FileLoader ivFileLoader;
    private ZZControlPanel ivZZControlPanel;
    private ZXPrinterCanvas ivPrinterCanvas;
    
    public ZZSpectrumController(final ZXSpectrumScreenCanvas pScreenCanvas, final ZXPrinterCanvas pPrinterCanvas, final FileLoader pFileLoader) {
        this.ivScreenCanvas = pScreenCanvas;
        this.ivPrinterCanvas = pPrinterCanvas;
        this.ivFileLoader = pFileLoader;
    }
    
    private ZxSpectrumScreenConverterAbstract createScreenConverter14(final Graphics pGraphics, final Component pComponent) throws Throwable {
        final Class cls = Class.forName("com.tn.zxspectrum.ZxSpectrumScreenConverterPatternMethod14");
        final Constructor c = cls.getConstructor(Graphics.class, Component.class);
        return c.newInstance(pGraphics, pComponent);
    }
    
    private synchronized void emulatorRelease() {
        this.ivEmulatorIsReserved = false;
        this.notifyAll();
    }
    
    private synchronized void emulatorReserve() {
        while (this.ivEmulatorIsReserved) {
            this.ivEmulatorReserveRequest = true;
            try {
                this.wait(100L);
            }
            catch (Exception ex) {}
        }
        this.ivEmulatorIsReserved = true;
        this.ivEmulatorReserveRequest = false;
    }
    
    private boolean emulatorReserveIsRequested() {
        return this.ivEmulatorReserveRequest;
    }
    
    public String getJoystick1Id() {
        if (!this.ivEmulatorInitialized) {
            return null;
        }
        if (this.ivSpectrumSystem.getJoystick1() != null) {
            return this.ivSpectrumSystem.getJoystick1().getId();
        }
        return null;
    }
    
    public boolean getModeTrueSpeed() {
        return this.ivModeTrueSpeed;
    }
    
    public String getPaletteId() {
        return this.ivPaletteId;
    }
    
    public boolean getSoundEnabled() {
        return this.ivEmulatorInitialized && this.ivSpectrumSystem.getSampleStreamer() != null;
    }
    
    public boolean getSoundSupported() {
        return this.ivSampleStreamer != null;
    }
    
    public SpectrumSystemStatus getStatus() {
        if (!this.ivEmulatorInitialized) {
            return null;
        }
        try {
            this.emulatorReserve();
            final SpectrumSystemStatus s = this.ivSpectrumSystem.getStatus();
            return s;
        }
        finally {
            this.emulatorRelease();
        }
    }
    
    private void handleException(final Throwable exception) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        exception.printStackTrace(System.out);
    }
    
    public void initialize() throws IOException {
        System.out.println("java.version: " + System.getProperty("java.version"));
        final MemoryFactory mf = new MemoryFactory(this.ivFileLoader);
        if (System.getProperty("java.version").compareTo("1.4") >= 0) {
            try {
                this.ivScreenConverter = this.createScreenConverter14(this.ivScreenCanvas.getGraphics(), this.ivScreenCanvas);
                System.out.println("Java 1.4 screen emulation activated");
            }
            catch (Throwable t) {
                System.out.println("Java 1.4 screen emulation not available: " + t);
            }
        }
        if (this.ivScreenConverter == null) {
            this.ivScreenConverter = new ZxSpectrumScreenConverterPatternMethod(this.ivScreenCanvas.getGraphics(), this.ivScreenCanvas);
            System.out.println("Java 1.1 screen emulation activated");
        }
        this.ivScreenConverter.setSourceWindow(32, 48, 320, 272);
        this.ivScreenConverter.setTargetWindow(0, 0, this.ivScreenCanvas.getSize().width, this.ivScreenCanvas.getSize().height);
        this.ivScreenCanvas.setScreenConverter(this.ivScreenConverter);
        this.ivPaletteId = "COLOUR";
        this.ivKeyboardHandler = new ZxSpectrumKeyboardHandlerImpl();
        this.ivScreenCanvas.addKeyListener(this);
        this.ivScreenCanvas.getParent().addKeyListener(this);
        this.ivSampleStreamer = null;
        if (System.getProperty("java.version").compareTo("1.2") >= 0) {
            try {
                this.ivSampleStreamer = new SampleStreamerJavax();
                System.out.println("Javax audio streamer activated.");
            }
            catch (Throwable t) {
                System.out.println("Javax audio could not be started: " + t);
            }
        }
        if (this.ivSampleStreamer == null) {
            try {
                this.ivSampleStreamer = new SampleStreamerJSyn2();
                System.out.println("JSyn audio streamer activated.");
            }
            catch (Throwable t) {
                System.out.println("JSyn audio could not be started: " + t + "\nSound emulation will not be available.");
            }
        }
        ZxPrinterConverter pc = null;
        if (this.ivPrinterCanvas != null) {
            pc = new ZxPrinter();
            this.ivPrinterCanvas.setPrinterConverter(pc);
        }
        (this.ivSpectrumSystem = new ZxSpectrumSystem(mf, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer, pc)).init();
        this.ivEmulatorInitialized = true;
        this.notifyChangeListeners();
    }
    
    public boolean isInitialized() {
        return this.ivEmulatorInitialized;
    }
    
    @Override
    public void keyPressed(final KeyEvent pKeyEvent) {
        try {
            if (!this.ivEmulatorInitialized) {
                return;
            }
            if (pKeyEvent.getKeyCode() == 112) {
                this.snapshotSaveInternal();
            }
            else if (pKeyEvent.getKeyCode() == 113) {
                if (this.ivSaveStatus != null) {
                    try {
                        this.snapshotLoadInternal();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                if (this.ivSpectrumSystem.getJoystick1() != null && this.ivSpectrumSystem.getJoystick1().keyPressed(pKeyEvent)) {
                    return;
                }
                this.ivKeyboardHandler.keyPressed(pKeyEvent);
            }
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent pKeyEvent) {
        try {
            if (!this.ivEmulatorInitialized) {
                return;
            }
            if (this.ivSpectrumSystem.getJoystick1() != null && this.ivSpectrumSystem.getJoystick1().keyReleased(pKeyEvent)) {
                return;
            }
            this.ivKeyboardHandler.keyReleased(pKeyEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent pKeyEvent) {
    }
    
    public void loadSnap(final String pFileName, final boolean pUsr0) throws IOException {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        final byte[] snap = this.ivFileLoader.readFile(pFileName, -1);
        final SnapshotParser sm = new SnapshotParser(snap);
        try {
            this.emulatorReserve();
            final Vector v = sm.getComponentStates();
            for (int i = 0; i < v.size(); ++i) {
                final Object cs = v.elementAt(i);
                if (cs instanceof SpectrumSystemStatus) {
                    this.resetSetup();
                    this.ivSpectrumSystem.setStatus((SpectrumSystemStatus)cs);
                }
                if (cs instanceof SpectrumStatus) {
                    this.resetSetup();
                    final SpectrumSystemStatus sss = new SpectrumSystemStatus();
                    sss.setSpectrumStatus((SpectrumStatus)cs);
                    this.ivSpectrumSystem.setStatus(sss);
                }
                if (cs instanceof TapFilePlayerStatus && i == 0) {
                    this.ivSpectrumSystem.selectTzxFilePlayer(false);
                    this.ivSpectrumSystem.loadTapFile((TapFilePlayerStatus)cs, pUsr0);
                }
                if (cs instanceof TzxFilePlayerStatus && i == 0) {
                    this.ivSpectrumSystem.selectTapFilePlayer(false);
                    this.ivSpectrumSystem.loadTzxFile((TzxFilePlayerStatus)cs, pUsr0);
                }
                if (cs instanceof MicrodriveCartridge && i == 0) {
                    this.ivSpectrumSystem.loadMdrFile((MicrodriveCartridge)cs, pUsr0);
                }
                if (cs instanceof PD765ADisk && i == 0) {
                    this.ivSpectrumSystem.loadDskFile((PD765ADisk)cs);
                }
            }
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    private void notifyChangeListeners() {
        if (this.ivZZControlPanel != null) {
            try {
                this.ivZZControlPanel.refresh();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
    }
    
    public void reset() {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.ivSpectrumSystem.reset();
        }
        finally {
            this.emulatorRelease();
        }
        this.emulatorRelease();
    }
    
    private void resetSetup() throws IOException {
        this.ivSpectrumSystem.selectInterface1(false);
        this.ivSpectrumSystem.selectKeyboardRobot(false);
        this.ivSpectrumSystem.selectTapFilePlayer(false);
        this.ivSpectrumSystem.selectTzxFilePlayer(false);
    }
    
    public void run() {
        try {
            this.emulatorReserve();
            final int nextTic = 0;
            long currentTimeMillis = 0L;
            long timeMillisNextFrame = 0L;
            while (!this.ivEmulatorTerminate) {
                this.ivSpectrumSystem.runOneFrame();
                if (this.ivModeTrueSpeed) {
                    timeMillisNextFrame += 20L;
                    currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis < timeMillisNextFrame) {
                        try {
                            Thread.sleep(timeMillisNextFrame - currentTimeMillis);
                        }
                        catch (InterruptedException ex) {}
                    }
                    else if (currentTimeMillis - timeMillisNextFrame > 40L) {
                        timeMillisNextFrame = currentTimeMillis;
                    }
                }
                else {
                    try {
                        Thread.sleep(1L);
                    }
                    catch (InterruptedException ex2) {}
                }
                if (this.emulatorReserveIsRequested()) {
                    this.emulatorRelease();
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex3) {}
                    this.emulatorReserve();
                }
            }
        }
        catch (Throwable t) {
            this.handleException(t);
            return;
        }
        finally {
            this.terminate();
            this.emulatorRelease();
        }
        this.terminate();
        this.emulatorRelease();
    }
    
    public boolean setControlPanel(final ZZControlPanel pZZControlPanel) {
        if (this.ivZZControlPanel == null || this.ivZZControlPanel == pZZControlPanel) {
            this.ivZZControlPanel = pZZControlPanel;
            return true;
        }
        return false;
    }
    
    public void setFocus() {
        this.ivScreenCanvas.requestFocus();
    }
    
    public void setInterface1Enabled(final boolean pInterface1Enabled) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.ivSpectrumSystem.selectInterface1(pInterface1Enabled);
        }
        catch (IOException e) {
            this.handleException(e);
            return;
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setJoystick1(final String pJoystickId) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.ivSpectrumSystem.selectJoystick1(pJoystickId);
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setMicrodriveEnabled(final int pNumber, final boolean pEnabled) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.ivSpectrumSystem.selectMicrodrive(3 + pNumber, pEnabled);
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setModeTrueSpeed(final boolean pModeTrueSpeed) {
        this.ivModeTrueSpeed = pModeTrueSpeed;
        this.notifyChangeListeners();
    }
    
    public void setPalette(final String pPaletteId) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        this.ivScreenConverter.setColorTable(pPaletteId);
        this.ivPaletteId = pPaletteId;
        this.notifyChangeListeners();
    }
    
    public void setPrinterEnabled(final boolean pPrinterEnabled) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.ivSpectrumSystem.selectPrinter(pPrinterEnabled);
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setSoundEnabled(final boolean pSoundEnabled) {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            if (pSoundEnabled) {
                this.ivSpectrumSystem.setSampleStreamer(this.ivSampleStreamer);
            }
            else {
                this.ivSpectrumSystem.setSampleStreamer(null);
            }
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setSpectrumType(final int pSpectrumType) throws IOException {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        int type = -1;
        switch (pSpectrumType) {
            case 0: {
                type = 15;
                break;
            }
            case 1: {
                type = 16;
                break;
            }
            case 2: {
                type = 17;
                break;
            }
            case 5: {
                type = 19;
                break;
            }
            case 6: {
                type = 200;
                break;
            }
        }
        try {
            this.emulatorReserve();
            this.resetSetup();
            this.ivSpectrumSystem.selectSpectrum(type);
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void setSpectrumType(final String pSpectrumType) throws IOException {
        int type = -1;
        if (pSpectrumType.toUpperCase().equals("16K")) {
            type = 0;
        }
        else if (pSpectrumType.toUpperCase().equals("48K")) {
            type = 1;
        }
        else if (pSpectrumType.toUpperCase().equals("128K")) {
            type = 2;
        }
        else if (pSpectrumType.toUpperCase().equals("PLUS3")) {
            type = 5;
        }
        else if (pSpectrumType.toUpperCase().equals("SCORPION")) {
            type = 6;
        }
        this.setSpectrumType(type);
    }
    
    public void setStatus(final SpectrumSystemStatus pStatus) throws IOException {
        if (!this.ivEmulatorInitialized) {
            return;
        }
        try {
            this.emulatorReserve();
            this.resetSetup();
            this.ivSpectrumSystem.setStatus(pStatus);
        }
        finally {
            this.emulatorRelease();
            this.notifyChangeListeners();
        }
        this.emulatorRelease();
        this.notifyChangeListeners();
    }
    
    public void snapshotLoadInternal() throws IOException {
        if (this.ivSaveStatus != null) {
            this.setStatus(this.ivSaveStatus);
        }
    }
    
    public void snapshotSaveInternal() {
        this.ivSaveStatus = this.getStatus();
    }
    
    public void stop() {
        this.ivEmulatorTerminate = true;
    }
    
    private void terminate() {
        this.ivSpectrumSystem.terminate();
        this.ivScreenConverter.terminate();
        if (this.ivSampleStreamer != null) {
            if (this.ivSampleStreamer instanceof SampleStreamerJSyn2) {
                ((SampleStreamerJSyn2)this.ivSampleStreamer).terminate();
            }
            if (this.ivSampleStreamer instanceof SampleStreamerJavax) {
                ((SampleStreamerJavax)this.ivSampleStreamer).terminate();
            }
        }
    }
}
