// 
// Decompiled by Procyon v0.5.30
// 

package en;

import java.util.Date;
import java.awt.image.ImageObserver;
import java.util.Vector;
import en.javafx.Parameter;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.DirectColorModel;
import en.javafx.ParameterHandler;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.applet.Applet;

public abstract class JavaFX extends Applet implements Runnable, ImageProducer
{
    private ColorModel colormodel;
    protected int[] pixels;
    private ImageConsumer consumer;
    private Image ximage;
    protected int width;
    protected int height;
    private Thread fxThread;
    protected boolean verbose;
    protected boolean loadMsg;
    protected boolean init;
    protected Color backgroundColor;
    protected Color foregroundColor;
    protected ParameterHandler parmHandler;
    
    public JavaFX() {
        this.verbose = false;
        this.loadMsg = true;
        this.init = false;
        this.backgroundColor = new Color(8421504);
        this.foregroundColor = new Color(16777215);
        this.parmHandler = new ParameterHandler(this);
        System.out.println("JavaFX 1.3 - Copyright (C) WaterLogic Real-Time Graphics 2001\n".concat(String.valueOf(String.valueOf(this.effectName()))));
    }
    
    public abstract boolean checkSite(final String p0);
    
    public abstract String effectName();
    
    public abstract void initFX() throws Exception;
    
    public abstract void FX() throws Exception;
    
    public final void start() {
        if (!this.checkSite(this.getCodeBase().toString())) {
            System.exit(-1);
        }
        if (this.getParameter("verbose") != null) {
            this.verbose = true;
        }
        else {
            this.verbose = false;
        }
        if (this.getParameter("disableLoadingMessage") != null) {
            this.loadMsg = false;
        }
        else {
            this.loadMsg = true;
        }
        if (this.verbose) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("JavaFX: start() (").append(this.getAppletContext().getClass().getName()).append(")"))));
        }
        try {
            if (this.fxThread == null) {
                (this.fxThread = new Thread(this, "JavaFX")).setPriority(1);
                this.fxThread.start();
            }
        }
        catch (Exception ex) {
            final ErrorDialog errorDialog = new ErrorDialog(this.effectName(), ex.getMessage(), ex);
            errorDialog.pack();
            errorDialog.show();
        }
    }
    
    public final void stop() {
        if (this.verbose) {
            System.out.println("JavaFX: stop()");
        }
        if (this.fxThread != null && this.fxThread.isAlive()) {
            this.fxThread.stop();
        }
        this.fxThread = null;
    }
    
    public final void run() {
        try {
            if (this.verbose) {
                System.out.println("JavaFX: run()");
            }
            this.width = Integer.decode(this.getParameter("width"));
            this.height = Integer.decode(this.getParameter("height"));
            if (this.getParameter("foregroundColor") != null) {
                this.foregroundColor = Color.decode(this.getParameter("foregroundColor"));
            }
            if (this.getParameter("backgroundColor") != null) {
                this.backgroundColor = Color.decode(this.getParameter("backgroundColor"));
            }
            if (this.verbose) {
                System.err.println("JavaFX: Codebase     = ".concat(String.valueOf(String.valueOf(this.getCodeBase().toString()))));
                System.err.println("JavaFX: Documentbase = ".concat(String.valueOf(String.valueOf(this.getDocumentBase().toString()))));
                System.err.println(String.valueOf(String.valueOf(new StringBuffer("JavaFX: Applet size  = ").append(this.width).append("x").append(this.height))));
            }
            this.colormodel = new DirectColorModel(32, 16711680, 65280, 255, 0);
            this.pixels = new int[this.width * this.height];
            this.ximage = Toolkit.getDefaultToolkit().createImage(this);
            this.showLoadingMessage();
            this.initFX();
            this.FX();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            final ErrorDialog errorDialog = new ErrorDialog(this.effectName(), ex.getMessage(), ex);
            errorDialog.pack();
            errorDialog.show();
        }
    }
    
    private final void showLoadingMessage() {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(this.backgroundColor);
        graphics.fillRect(0, 0, this.width, this.height);
        if (!this.loadMsg) {
            return;
        }
        graphics.setColor(this.foregroundColor);
        graphics.drawString("WaterLogic Real-Time Graphics", 5, 20);
        graphics.drawString("    www.waterlogic.com.sg    ", 5, 40);
        graphics.drawString(this.effectName(), 5, 60);
        graphics.drawString("   Loading, please wait...   ", 5, 80);
    }
    
    public final Parameter getJavaFXParameterObject(final String s) {
        return this.parmHandler.getParameterObject(s);
    }
    
    public final void setJavaFXParameters(final Vector vector, final Vector vector2) throws Exception {
        this.parmHandler.setParameters(vector, vector2);
    }
    
    public final void getJavaFXParameters(final Vector vector, final Vector vector2) {
        this.parmHandler.getParameters(vector, vector2);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.init) {
            this.update(this.pixels);
        }
        else {
            this.showLoadingMessage();
        }
    }
    
    public final synchronized void update(final Object o) {
        if (this.consumer != null) {
            this.consumer.setPixels(0, 0, this.width, this.height, this.colormodel, (int[])o, 0, this.width);
            this.consumer.imageComplete(2);
        }
        final Graphics graphics = this.getGraphics();
        if (this.ximage != null && graphics != null) {
            graphics.drawImage(this.ximage, 0, 0, null);
        }
    }
    
    public final synchronized void addConsumer(final ImageConsumer consumer) {
        (this.consumer = consumer).setDimensions(this.width, this.height);
        this.consumer.setHints(30);
        this.consumer.setColorModel(this.colormodel);
    }
    
    public final synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        return true;
    }
    
    public final synchronized void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.consumer) {
            this.consumer.setPixels(0, 0, this.width, this.height, this.colormodel, this.pixels, 0, this.width);
            this.consumer.imageComplete(2);
        }
    }
    
    static {
        if (1354870381117L <= System.currentTimeMillis()) {
            System.out.println("This java program was processed with an unregistered version of Condensity and it expired on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
            throw new Error();
        }
        System.out.println("This java program was processed with an unregistered version of Condensity and will expire on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
    }
}
