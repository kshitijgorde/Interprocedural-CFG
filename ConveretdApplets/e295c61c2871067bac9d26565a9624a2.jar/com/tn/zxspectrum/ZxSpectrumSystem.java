// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import com.tn.zx.peripheral.TzxFilePlayerStatus;
import com.tn.zx.peripheral.TapFilePlayerStatus;
import com.tn.zx.peripheral.MicrodriveCartridge;
import com.tn.components.PD765ADisk;
import com.tn.zx.ZxExpansionPort;
import java.io.IOException;
import com.tn.zx.peripheral.ZxPrinterConverter;
import com.tn.util.SampleStreamer;
import com.tn.zx.ZxKeyboardHandler;
import com.tn.components.MemoryFactory;
import com.tn.zx.peripheral.ZXSpectrumKeyboardRobot;
import com.tn.zx.peripheral.ZXSpectrumTzxFilePlayer;
import com.tn.zx.peripheral.ZXSpectrumTapFilePlayer;
import com.tn.zx.peripheral.IOJoystick;
import com.tn.zx.peripheral.ZxPrinter;
import com.tn.zx.peripheral.Microdrive;
import com.tn.zx.peripheral.Interface1a;

public class ZxSpectrumSystem
{
    private ZxSpectrumAbstract ivSpectrum;
    private Interface1a ivInterface1;
    private Microdrive[] ivMicrodrives;
    private ZxPrinter ivPrinter;
    private IOJoystick ivJoystick1;
    private IOJoystick ivJoystick2;
    private ZXSpectrumTapFilePlayer ivTapFilePlayer;
    private ZXSpectrumTzxFilePlayer ivTzxFilePlayer;
    private ZXSpectrumKeyboardRobot ivKeyboardRobot;
    private int ivSpectrumType;
    private MemoryFactory ivMemoryFactory;
    private ZxKeyboardHandler ivKeyboardHandler;
    private ZxSpectrumScreenConverter ivScreenConverter;
    private SampleStreamer ivSampleStreamer;
    private ZxPrinterConverter ivPrinterConverter;
    
    public ZxSpectrumSystem(final MemoryFactory pMemoryFactory, final ZxKeyboardHandler pKeyboardHandler, final ZxSpectrumScreenConverter pScreenConverter, final SampleStreamer pSampleStreamer, final ZxPrinterConverter pPrinterConverter) throws IOException {
        this.ivMemoryFactory = pMemoryFactory;
        this.ivKeyboardHandler = pKeyboardHandler;
        this.ivScreenConverter = pScreenConverter;
        this.ivSampleStreamer = pSampleStreamer;
        this.ivPrinterConverter = pPrinterConverter;
        this.initialize();
    }
    
    private static boolean activeStatusChanged(final boolean pActiveStatus, final Object pObject) {
        return pActiveStatus == (pObject == null);
    }
    
    private void assembleSystem() {
        if (this.ivInterface1 != null) {
            this.ivInterface1.connectToZXSpectrum(this.ivSpectrum);
        }
        for (int i = 0; i < 7; ++i) {
            if (this.ivMicrodrives[i] != null && this.ivInterface1 != null) {
                this.ivInterface1.connectMicrodrive(this.ivMicrodrives[i], i);
            }
        }
        if (this.ivPrinter != null) {
            this.ivPrinter.connectToZXSpectrum(this.ivSpectrum);
        }
        if (this.ivJoystick1 != null) {
            this.ivJoystick1.connectToZXSpectrum(this.ivSpectrum);
        }
        if (this.ivJoystick2 != null) {
            this.ivJoystick2.connectToZXSpectrum(this.ivSpectrum);
        }
        if (this.ivTapFilePlayer != null) {
            this.ivTapFilePlayer.connectToZXSpectrum(this.ivSpectrum);
        }
        if (this.ivTzxFilePlayer != null) {
            this.ivTzxFilePlayer.connectToZXSpectrum(this.ivSpectrum);
        }
        if (this.ivKeyboardRobot != null) {
            this.ivKeyboardRobot.connectToZXSpectrum(this.ivSpectrum);
        }
    }
    
    private void disassembleSystem() {
        if (this.ivInterface1 != null) {
            this.ivInterface1.disconnectFromZXSpectrum();
        }
        if (this.ivInterface1 != null) {
            for (int i = 0; i < 7; ++i) {
                this.ivInterface1.disconnectMicrodrive(i);
            }
        }
        if (this.ivPrinter != null) {
            this.ivPrinter.disconnectFromZXSpectrum();
        }
        if (this.ivJoystick1 != null) {
            this.ivJoystick1.disconnectFromZXSpectrum();
        }
        if (this.ivJoystick2 != null) {
            this.ivJoystick2.disconnectFromZXSpectrum();
        }
        if (this.ivTapFilePlayer != null) {
            this.ivTapFilePlayer.disconnectFromZXSpectrum();
        }
        if (this.ivTzxFilePlayer != null) {
            this.ivTzxFilePlayer.disconnectFromZXSpectrum();
        }
        if (this.ivKeyboardRobot != null) {
            this.ivKeyboardRobot.disconnectFromZXSpectrum();
        }
    }
    
    public Interface1a getInterface1() {
        return this.ivInterface1;
    }
    
    public IOJoystick getJoystick1() {
        return this.ivJoystick1;
    }
    
    public IOJoystick getJoystick2() {
        return this.ivJoystick2;
    }
    
    public Microdrive getMicrodrive(final int pMicrodriveId) {
        final int index = pMicrodriveId - 3;
        return this.ivMicrodrives[index];
    }
    
    public ZxPrinter getPrinter() {
        return this.ivPrinter;
    }
    
    public SampleStreamer getSampleStreamer() {
        return this.ivSpectrum.getSampleStreamer();
    }
    
    public ZxSpectrumAbstract getSpectrum() {
        return this.ivSpectrum;
    }
    
    public SpectrumSystemStatus getStatus() {
        final SpectrumSystemStatus result = new SpectrumSystemStatus();
        result.setSpectrumStatus(this.ivSpectrum.getStatus());
        if (this.ivInterface1 != null) {
            result.setInterface1Status(this.ivInterface1.getStatus());
        }
        for (int i = 0; i < 8; ++i) {
            if (this.ivMicrodrives[i] != null) {
                result.setMicrodriveStatus(3 + i, this.ivMicrodrives[i].getstatus());
            }
        }
        if (this.ivPrinter != null) {
            result.setPrinterStatus(this.ivPrinter.getStatus());
        }
        if (this.ivTapFilePlayer != null) {
            result.setTapFilePlayerStatus(this.ivTapFilePlayer.getStatus());
        }
        if (this.ivKeyboardRobot != null) {
            result.setKeyboardRobotStatus(this.ivKeyboardRobot.getStatus());
        }
        return result;
    }
    
    public void init() {
    }
    
    private void initialize() throws IOException {
        this.ivInterface1 = null;
        this.ivMicrodrives = new Microdrive[8];
        this.ivPrinter = null;
        this.ivJoystick1 = null;
        this.ivJoystick2 = null;
        this.ivTapFilePlayer = null;
        this.ivTzxFilePlayer = null;
        this.ivKeyboardRobot = null;
        this.selectSpectrum(16);
    }
    
    public void loadDskFile(final PD765ADisk pDisk) throws IOException {
        final int[] loadCommandKeyCodes = { 13 };
        this.selectKeyboardRobot(true);
        this.ivKeyboardRobot.setKeyCodeBuffer(loadCommandKeyCodes);
        this.selectSpectrum(19);
        ((ZxSpectrumPlus3)this.ivSpectrum).insertDisk(pDisk);
        this.reset();
    }
    
    public void loadMdrFile(final MicrodriveCartridge pCartridge, final boolean pUsr0) throws IOException {
        final int[][] loadCommandKeyCodes = { { 247, 13 }, { 10, 13, 82, 85, 78, 13 }, { 10, 10, 10, 13, 247, 13 } };
        int cmdIndex = 0;
        switch (this.ivSpectrumType) {
            case 15:
            case 16: {
                cmdIndex = 0;
                break;
            }
            case 17:
            case 18: {
                cmdIndex = (pUsr0 ? 2 : 1);
                break;
            }
            default: {
                throw new RuntimeException("loadMdrFile not supported for current spectrum type");
            }
        }
        this.selectKeyboardRobot(true);
        this.ivKeyboardRobot.setKeyCodeBuffer(loadCommandKeyCodes[cmdIndex]);
        this.selectInterface1(true);
        this.selectMicrodrive(3, true);
        this.ivMicrodrives[0].insertCartridge(pCartridge);
        this.reset();
    }
    
    public void loadTapFile(final TapFilePlayerStatus pStatus, final boolean pUsr0) {
        final int[][] loadCommandKeyCodes = { { 239, 34, 34, 13 }, { 239, 34, 34, 175, 13 }, { 13 }, { 13 }, { 10, 10, 10, 13, 239, 34, 34, 13 }, { 10, 10, 10, 13, 239, 34, 34, 175, 13 } };
        int cmdIndex = 0;
        switch (this.ivSpectrumType) {
            case 15:
            case 16: {
                cmdIndex = 0;
                break;
            }
            case 17:
            case 18:
            case 19: {
                cmdIndex = (pUsr0 ? 4 : 2);
                break;
            }
            default: {
                throw new RuntimeException("loadTapFile not supported for current spectrum type");
            }
        }
        cmdIndex += ((pStatus.getTapFileBytes()[3] != 0) ? 1 : 0);
        this.selectKeyboardRobot(true);
        this.ivKeyboardRobot.setKeyCodeBuffer(loadCommandKeyCodes[cmdIndex]);
        this.selectTapFilePlayer(true);
        this.ivTapFilePlayer.setStatus(pStatus);
        this.reset();
    }
    
    public void loadTzxFile(final TzxFilePlayerStatus pStatus, final boolean pUsr0) {
        final int[][] loadCommandKeyCodes = { { 239, 34, 34, 13 }, { 239, 34, 34, 175, 13 }, { 13 }, { 13 }, { 10, 10, 10, 13, 239, 34, 34, 13 }, { 10, 10, 10, 13, 239, 34, 34, 175, 13 } };
        int cmdIndex = 0;
        switch (this.ivSpectrumType) {
            case 15:
            case 16: {
                cmdIndex = 0;
                break;
            }
            case 17:
            case 18:
            case 19: {
                cmdIndex = (pUsr0 ? 4 : 2);
                break;
            }
            default: {
                throw new RuntimeException("loadTzxFile not supported for current spectrum type");
            }
        }
        cmdIndex += (ZXSpectrumTzxFilePlayer.isProgram(pStatus.getTzxFileBytes()) ? 0 : 1);
        int pause = 0;
        switch (this.ivSpectrumType) {
            case 19: {
                pause = 7000;
                break;
            }
            default: {
                pause = 1000;
                break;
            }
        }
        this.selectKeyboardRobot(true);
        this.ivKeyboardRobot.setKeyCodeBuffer(loadCommandKeyCodes[cmdIndex]);
        this.selectTzxFilePlayer(true);
        this.ivTzxFilePlayer.setTzxFileBuffer(pStatus.getTzxFileBytes(), pause);
        if (this.ivSpectrumType == 19) {
            ((ZxSpectrumPlus3)this.ivSpectrum).removeDisk();
        }
        this.reset();
    }
    
    public void reset() {
        this.ivSpectrum.reset();
        if (this.ivInterface1 != null) {
            this.ivInterface1.reset();
        }
        if (this.ivJoystick1 != null) {
            this.ivJoystick1.reset();
        }
        if (this.ivJoystick2 != null) {
            this.ivJoystick2.reset();
        }
    }
    
    public void runOneFrame() {
        this.ivSpectrum.runOneFrame();
    }
    
    public void selectInterface1(final boolean pActive) throws IOException {
        if (activeStatusChanged(pActive, this.ivInterface1)) {
            final Interface1a newIf1 = pActive ? new Interface1a(this.ivMemoryFactory) : null;
            this.disassembleSystem();
            this.ivInterface1 = newIf1;
            this.assembleSystem();
        }
    }
    
    public void selectJoystick1(final String pJoystickId) {
        IOJoystick newJoystick = null;
        if (pJoystickId != null && !pJoystickId.trim().equals("")) {
            newJoystick = IOJoystick.createJoystick(pJoystickId);
        }
        this.disassembleSystem();
        this.ivJoystick1 = newJoystick;
        this.assembleSystem();
    }
    
    public void selectJoystick2(final String pJoystickId) {
        IOJoystick newJoystick = null;
        if (pJoystickId != null && !pJoystickId.trim().equals("")) {
            newJoystick = IOJoystick.createJoystick(pJoystickId);
        }
        this.disassembleSystem();
        this.ivJoystick2 = newJoystick;
        this.assembleSystem();
    }
    
    public void selectKeyboardRobot(final boolean pActive) {
        if (activeStatusChanged(pActive, this.ivKeyboardRobot)) {
            final ZXSpectrumKeyboardRobot newRobot = pActive ? new ZXSpectrumKeyboardRobot() : null;
            this.disassembleSystem();
            this.ivKeyboardRobot = newRobot;
            this.assembleSystem();
        }
    }
    
    public void selectMicrodrive(final int pMicrodriveId, final boolean pActive) {
        final int index = pMicrodriveId - 3;
        if (activeStatusChanged(pActive, this.ivMicrodrives[index])) {
            final Microdrive newMicrodrive = pActive ? new Microdrive() : null;
            this.disassembleSystem();
            this.ivMicrodrives[index] = newMicrodrive;
            this.assembleSystem();
        }
    }
    
    public void selectPrinter(final boolean pActive) {
        if (activeStatusChanged(pActive, this.ivPrinter)) {
            final ZxPrinter newPrinter = pActive ? ((ZxPrinter)this.ivPrinterConverter) : null;
            this.disassembleSystem();
            this.ivPrinter = newPrinter;
            this.assembleSystem();
        }
    }
    
    public void selectSpectrum(final int pSpectrumType) throws IOException {
        if (this.ivSpectrum == null || this.ivSpectrumType != pSpectrumType) {
            ZxSpectrumAbstract newSpectrum = null;
            switch (pSpectrumType) {
                case 15: {
                    newSpectrum = new ZxSpectrum16K(this.ivMemoryFactory, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer);
                    break;
                }
                case 16: {
                    newSpectrum = new ZxSpectrum48K(this.ivMemoryFactory, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer);
                    break;
                }
                case 17: {
                    newSpectrum = new ZxSpectrum128K(this.ivMemoryFactory, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer);
                    break;
                }
                case 19: {
                    newSpectrum = new ZxSpectrumPlus3(this.ivMemoryFactory, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer);
                    break;
                }
                case 200: {
                    newSpectrum = new ZxScorpion(this.ivMemoryFactory, this.ivKeyboardHandler, this.ivScreenConverter, this.ivSampleStreamer);
                    break;
                }
                default: {
                    throw new RuntimeException("Invalid parameter pSpectrumType: " + pSpectrumType);
                }
            }
            this.disassembleSystem();
            this.ivSpectrum = newSpectrum;
            this.ivSpectrumType = pSpectrumType;
            this.assembleSystem();
            newSpectrum.init();
        }
    }
    
    public void selectTapFilePlayer(final boolean pActive) {
        if (activeStatusChanged(pActive, this.ivTapFilePlayer)) {
            final ZXSpectrumTapFilePlayer newPlayer = pActive ? new ZXSpectrumTapFilePlayer() : null;
            this.disassembleSystem();
            this.ivTapFilePlayer = newPlayer;
            this.assembleSystem();
        }
    }
    
    public void selectTzxFilePlayer(final boolean pActive) {
        if (activeStatusChanged(pActive, this.ivTzxFilePlayer)) {
            final ZXSpectrumTzxFilePlayer newPlayer = pActive ? new ZXSpectrumTzxFilePlayer() : null;
            this.disassembleSystem();
            this.ivTzxFilePlayer = newPlayer;
            this.assembleSystem();
        }
    }
    
    public void setSampleStreamer(final SampleStreamer pSampleStreamer) {
        this.ivSpectrum.setSampleStreamer(pSampleStreamer);
        this.ivSampleStreamer = pSampleStreamer;
    }
    
    public void setStatus(final SpectrumSystemStatus pStatus) throws IOException {
        if (pStatus.getSpectrumStatus() != null) {
            this.selectSpectrum(pStatus.getSpectrumStatus().getComponentType());
            this.ivSpectrum.setStatus(pStatus.getSpectrumStatus());
        }
        if (pStatus.getInterface1Status() != null) {
            this.selectInterface1(true);
            this.ivInterface1.setStatus(pStatus.getInterface1Status());
        }
        for (int i = 3; i <= 10; ++i) {
            if (pStatus.getMicrodriveStatus(i) != null) {
                this.selectMicrodrive(i, true);
                this.ivMicrodrives[i - 3].setStatus(pStatus.getMicrodriveStatus(i));
            }
        }
        if (pStatus.getPrinterStatus() != null) {
            this.selectPrinter(true);
            this.ivPrinter.setStatus(pStatus.getPrinterStatus());
        }
        if (pStatus.getTapFilePlayerStatus() != null) {
            this.selectTapFilePlayer(true);
            this.ivTapFilePlayer.setStatus(pStatus.getTapFilePlayerStatus());
        }
        if (pStatus.getKeyboardRobotStatus() != null) {
            this.selectKeyboardRobot(true);
            this.ivKeyboardRobot.setStatus(pStatus.getKeyboardRobotStatus());
        }
    }
    
    public void terminate() {
        this.ivSpectrum.terminate();
        this.ivSpectrum = null;
    }
}
