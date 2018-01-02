// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.applet.AppletStub;
import java.io.IOException;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import com.tn.util.ProgressIndicator;
import java.awt.Frame;
import com.tn.util.FileLoader;
import com.tn.util.ProgressIndicatorImpl;
import java.applet.Applet;

public class ZZSpectrumApplet extends Applet implements Runnable
{
    private static final String copyright = "© Copyright Troels Noergaard, 2001-2006. All Rights Reserved.";
    private static final String version = "v2.2";
    private ZXSpectrumScreenCanvas ivScreenCanvas;
    private Thread ivThread;
    private ProgressIndicatorImpl ivProgressIndicator;
    private FileLoader ivFileLoader;
    private ZXPrinterCanvas ivPrinterCanvas;
    private Frame ivPrinterFrame;
    private ZZSpectrumController ivController;
    
    public ZZSpectrumApplet() {
        this.ivScreenCanvas = null;
        this.ivProgressIndicator = null;
        this.ivFileLoader = null;
        this.ivPrinterCanvas = null;
        this.ivPrinterFrame = null;
    }
    
    @Override
    public String getAppletInfo() {
        return "ZZSpectrumAppletv2.2\n© Copyright Troels Noergaard, 2001-2006. All Rights Reserved.\n";
    }
    
    public synchronized ZZSpectrumController getController() {
        if (this.ivController == null) {
            try {
                this.ivController = new ZZSpectrumController(this.getScreenCanvas(), this.getPrinterCanvas(), this.getFileLoader());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivController;
    }
    
    private FileLoader getFileLoader() {
        if (this.ivFileLoader == null) {
            try {
                this.ivFileLoader = new FileLoader(this.getCodeBase(), this.getProgressIndicator());
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivFileLoader;
    }
    
    @Override
    public String[][] getParameterInfo() {
        return new String[][] { { "spectrumtype", "16K, 48K, 128K, PLUS3 or SCORPION", "Type of spectrum hardware to emulate" }, { "if1", "Y or N", "Y=Interface1 attached" }, { "mdr", "0 to 8", "Number of microdrives attached (only makes sense if interface1 is attached)" }, { "snapshot", "filename", "URL of snapshot file to load into the emulator on startup. Can be .SNA (mirage), .Z80 (z80), .ZX (kgb), .TAP (tape file), .TZX (tape file), .MDR (microdrive file) or .DSK (+3 extended disk file)" }, { "usr0", "Y or N", "Y=go into 48K mode (only valid when loading TAP and MDR files on 128K hardware" }, { "truespeed", "Y or N", "Y=adjust speed to 100% spectrumspeed (if possible), N=as high speed as possible - this is the default" }, { "joystick", "<null>, KEMPSTON, CURSOR, SINCLAIR1, SINCLAIR2", "Type of joystick hardware to emulate" }, { "palette", "B/W, COLOUR, ISSUE2", "Colour palette to use" }, { "printer", "Y or N", "Y=ZX Printer attached (use the ZZPrinter applet to capture and display the output)" } };
    }
    
    private ZXPrinterCanvas getPrinterCanvas() {
        if (this.ivPrinterCanvas == null) {
            try {
                (this.ivPrinterCanvas = new ZXPrinterCanvas()).setName("PrinterCanvas");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivPrinterCanvas;
    }
    
    private Frame getPrinterFrame() {
        if (this.ivPrinterFrame == null) {
            try {
                (this.ivPrinterFrame = new Frame("ZX Printer")).setBackground(Color.darkGray);
                this.ivPrinterFrame.setLayout(new BorderLayout());
                this.ivPrinterFrame.setSize(272, 560);
                this.ivPrinterFrame.add(this.getPrinterCanvas(), "Center");
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivPrinterFrame;
    }
    
    private ProgressIndicatorImpl getProgressIndicator() {
        if (this.ivProgressIndicator == null) {
            try {
                (this.ivProgressIndicator = new ProgressIndicatorImpl()).setName("ProgressIndicator");
                this.ivProgressIndicator.setVisible(false);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivProgressIndicator;
    }
    
    private ZXSpectrumScreenCanvas getScreenCanvas() {
        if (this.ivScreenCanvas == null) {
            try {
                (this.ivScreenCanvas = new ZXSpectrumScreenCanvas()).setName("ScreenCanvas");
                this.ivScreenCanvas.setSupportResize(true);
            }
            catch (Throwable ivjExc) {
                this.handleException(ivjExc);
            }
        }
        return this.ivScreenCanvas;
    }
    
    @Override
    public boolean handleEvent(final Event e) {
        switch (e.id) {
            case 501: {
                this.getScreenCanvas().requestFocus();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private void handleException(final Throwable exception) {
        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        exception.printStackTrace(System.out);
    }
    
    @Override
    public void init() {
        try {
            super.init();
            this.setName("ZZ Spectrum");
            this.setLayout(new BorderLayout());
            this.add(this.getScreenCanvas(), "Center");
            this.add(this.getProgressIndicator(), "South");
            this.initConnections();
            this.getScreenCanvas().requestFocus();
        }
        catch (Throwable ivjExc) {
            this.handleException(ivjExc);
        }
    }
    
    private void initConnections() throws Exception {
    }
    
    public void loadSnap(final String pFileName) throws IOException {
        this.loadSnap(pFileName, false);
    }
    
    public void loadSnap(final String pFileName, final boolean pUsr0) throws IOException {
        this.getController().loadSnap(pFileName, pUsr0);
    }
    
    public static void main(final String[] args) {
        for (int n = 0; n < args.length; ++n) {
            System.out.println(args[n]);
        }
        System.out.println("MAIN");
        final ZZSpectrumApplet applet = new ZZSpectrumApplet();
        final Frame frame = new Frame("Applet");
        frame.setSize(800, 600);
        frame.add("Center", applet);
        frame.show();
        applet.setStub(new AppletStubImpl());
        applet.init();
        applet.start();
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        this.showCopyright(g);
        System.out.print(".");
    }
    
    private void parseParameters() throws IOException {
        final String spectrumtype = this.getParameter("spectrumtype");
        if (spectrumtype != null) {
            this.setSpectrumType(spectrumtype);
        }
        final String ts = this.getParameter("truespeed");
        if (ts != null && ts.toUpperCase().equals("Y")) {
            this.setModeTrueSpeed(true);
        }
        else {
            this.setModeTrueSpeed(false);
        }
        boolean usr0 = false;
        final String u0 = this.getParameter("usr0");
        if (u0 != null && u0.toUpperCase().equals("Y")) {
            usr0 = true;
        }
        final String snapshot = this.getParameter("snapshot");
        if (snapshot != null) {
            this.loadSnap(snapshot, usr0);
        }
        final String joystick = this.getParameter("joystick");
        if (joystick != null) {
            this.setJoystick(joystick);
        }
        final String palette = this.getParameter("palette");
        if (palette != null) {
            this.setPalette(palette);
        }
        final String if1 = this.getParameter("if1");
        if (if1 != null && if1.toUpperCase().equals("Y")) {
            this.setInterface1Enabled(true);
        }
        final String mdr = this.getParameter("mdr");
        if (mdr != null && mdr.length() == 1 && Character.isDigit(mdr.charAt(0))) {
            for (int mdrCnt = mdr.charAt(0) - '0', i = 0; i < Math.min(8, mdrCnt); ++i) {
                this.setMicrodriveEnabled(i, true);
            }
        }
        final String printer = this.getParameter("printer");
        if (printer != null && printer.toUpperCase().equals("Y")) {
            this.setPrinterEnabled(true);
        }
    }
    
    public void reset() {
        this.ivController.reset();
    }
    
    @Override
    public void run() {
        System.out.println("ZZ Spectrum v2.2 started");
        try {
            this.getScreenCanvas().setVisible(false);
            this.repaint();
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.getController().initialize();
            this.parseParameters();
            this.getScreenCanvas().setVisible(true);
            this.getController().run();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        System.out.println("ZZ Spectrum ended");
    }
    
    public void setFocus() {
        this.getScreenCanvas().requestFocus();
    }
    
    public void setInterface1Enabled(final boolean pInterface1Enabled) {
        this.getController().setInterface1Enabled(pInterface1Enabled);
    }
    
    public void setJoystick(final String pJoystickId) {
        this.getController().setJoystick1(pJoystickId);
    }
    
    public void setMicrodriveEnabled(final int pNumber, final boolean pEnabled) {
        this.getController().setMicrodriveEnabled(pNumber, pEnabled);
    }
    
    public void setModeTrueSpeed(final boolean pModeTrueSpeed) {
        this.getController().setModeTrueSpeed(pModeTrueSpeed);
    }
    
    public void setPalette(final String pPaletteId) {
        this.getController().setPalette(pPaletteId);
    }
    
    public void setPrinterEnabled(final boolean pPrinterEnabled) {
        this.getController().setPrinterEnabled(pPrinterEnabled);
        this.getPrinterFrame().setVisible(pPrinterEnabled);
    }
    
    public void setSoundEnabled(final boolean pSoundEnabled) {
        this.getController().setSoundEnabled(pSoundEnabled);
    }
    
    public void setSpectrumType(final int pSpectrumType) throws IOException {
        this.getController().setSpectrumType(pSpectrumType);
    }
    
    public void setSpectrumType(final String pSpectrumType) throws IOException {
        this.getController().setSpectrumType(pSpectrumType);
    }
    
    public boolean setZZControlPanel(final ZZControlPanel pZZControlPanel) {
        return this.getController().setControlPanel(pZZControlPanel);
    }
    
    private void showCopyright(final Graphics g) {
        final String copyright1 = "ZZ Spectrumv2.2";
        final String copyright2 = "(c) 2006, Troels Noergaard";
        final String copyright3 = "email: tron@zzspectrum.org";
        final Dimension d = this.getSize();
        final FontMetrics fm = g.getFontMetrics();
        final int textWidth1 = fm.stringWidth(copyright1);
        final int textWidth2 = fm.stringWidth(copyright2);
        final int textWidth3 = fm.stringWidth(copyright3);
        final int textHeight = fm.getAscent();
        g.setColor(Color.red);
        g.drawString(copyright1, (d.width - textWidth1) / 2, (d.height - textHeight * 4) / 2);
        g.drawString(copyright2, (d.width - textWidth2) / 2, (d.height - textHeight) / 2);
        g.drawString(copyright3, (d.width - textWidth3) / 2, (d.height + textHeight * 2) / 2);
    }
    
    public void snapshotLoadInternal() throws IOException {
        this.getController().snapshotLoadInternal();
    }
    
    public void snapshotSaveInternal() {
        this.getController().snapshotSaveInternal();
    }
    
    @Override
    public void start() {
        if (this.ivThread == null) {
            (this.ivThread = new Thread(this, "ZZ Spectrum v2.2")).start();
        }
    }
    
    @Override
    public void stop() {
        this.getController().stop();
        if (this.ivThread != null) {
            this.ivThread = null;
        }
    }
}
