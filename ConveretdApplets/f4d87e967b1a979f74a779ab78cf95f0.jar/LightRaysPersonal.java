import java.util.Date;
import java.awt.event.MouseEvent;
import en.products.AppletPair;
import java.applet.Applet;
import java.awt.Cursor;
import en.network.Network;
import en.products.LightRaysPersonal$LightPulsate;
import en.products.LightRaysPersonal$LightMotion;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import en.JavaFX;

// 
// Decompiled by Procyon v0.5.30
// 

public class LightRaysPersonal extends JavaFX implements MouseListener, MouseMotionListener
{
    public dp dimension;
    public da frame;
    public da foreground;
    public da background;
    public w mask;
    public LightRaysPersonal$LightMotion motion;
    public dd motionType;
    public LightRaysPersonal$LightPulsate pulsate;
    public dv timer;
    public u frameMask;
    public s blend;
    public dd blendMode;
    public da screen;
    public x copyImage;
    public r display;
    public z scroller;
    public da banner;
    public w bannerBlend;
    public o foregroundFileName;
    public o backgroundFileName;
    public o frameFileName;
    public o maskFileName;
    public q vlight;
    public Network net;
    public boolean showStatus;
    public String statusMessage;
    public Cursor hand;
    public Cursor norm;
    
    public LightRaysPersonal() {
        this.net = new Network();
        this.showStatus = false;
        this.statusMessage = "LightRays 1.0 by WaterLogic Real-Time Graphics";
        this.hand = new Cursor(12);
        this.norm = new Cursor(0);
    }
    
    private final void setupNetwork() throws Exception {
        this.dimension = new dp(super.width, super.height);
        this.frame = new da();
        this.foreground = new da();
        this.background = new da();
        this.mask = new w();
        this.motion = new LightRaysPersonal$LightMotion(this);
        this.motionType = new dd();
        this.pulsate = new LightRaysPersonal$LightPulsate(this);
        this.timer = new dv();
        this.frameMask = new u();
        this.blend = new s();
        this.blendMode = new dd();
        this.screen = new da(super.width, super.height, super.pixels);
        this.copyImage = new x();
        this.display = new r(this);
        this.vlight = new q(0.8f, 0.05f, 0.05f);
        this.scroller = new z();
        this.banner = new da(this);
        this.bannerBlend = new w(this);
        this.foregroundFileName = new o();
        this.backgroundFileName = new o();
        this.frameFileName = new o();
        this.maskFileName = new o();
        this.frameMask.p(true);
        this.frame.p(this);
        this.frame.p.p(true);
        this.foreground.p.p(true);
        this.background.p.p(true);
        this.mask.p.p(true);
        this.scroller.n.p(0.055f);
        this.scroller.v.p = 2000L;
        this.banner.p("lightrays/personaledition.gif");
        this.bannerBlend.p("blendmodes/blendmode_screen.jpg");
        this.net.addNode(this.dimension);
        this.net.addNode(this.foreground);
        this.net.addNode(this.background);
        this.net.addNode(this.screen);
        this.net.addNode(this.copyImage);
        this.net.addNode(this.timer);
        this.net.addNode(this.motion);
        this.net.addNode(this.motionType);
        this.net.addNode(this.pulsate);
        this.net.addNode(this.vlight);
        this.net.addNode(this.blendMode);
        this.net.addNode(this.blend);
        this.net.addNode(this.frameMask);
        this.net.addNode(this.frame);
        this.net.addNode(this.mask);
        this.net.addNode(this.display);
        this.net.addNode(this.backgroundFileName);
        this.net.addNode(this.foregroundFileName);
        this.net.addNode(this.frameFileName);
        this.net.addNode(this.maskFileName);
        this.net.addNode(this.scroller);
        this.net.addNode(this.banner);
        this.net.addNode(this.bannerBlend);
        this.net.connect(this.dimension.p, this.motion.in_dimension);
        this.net.connect(this.dimension.p, this.frame.d);
        this.net.connect(this.dimension.p, this.mask.d);
        this.net.connect(this.dimension.p, this.foreground.d);
        this.net.connect(this.dimension.p, this.background.d);
        this.net.connect(this.motionType.p, this.motion.in_type);
        this.net.connect(this.timer.d, this.motion.in_time);
        this.net.connect(this.timer.d, this.pulsate.in_time);
        this.net.connect(this.foreground.d, this.copyImage.p);
        this.net.connect(this.screen.d, this.copyImage.d);
        this.net.connect(this.copyImage.d, this.vlight.n);
        this.net.connect(this.motion.out_light, this.vlight.d);
        this.net.connect(this.pulsate.output, this.vlight.a);
        this.net.connect(this.blendMode.p, this.blend.v);
        this.net.connect(this.background.d, this.blend.p);
        this.net.connect(this.vlight.n, this.blend.d);
        this.net.connect(this.vlight.n, this.blend.a);
        this.net.connect(this.blend.a, this.frameMask.p);
        this.net.connect(this.blend.a, this.frameMask.n);
        this.net.connect(this.frame.d, this.frameMask.d);
        this.net.connect(this.mask.d, this.frameMask.a);
        this.net.connect(this.bannerBlend.d, this.scroller.a);
        this.net.connect(this.banner.d, this.scroller.p);
        this.net.connect(this.frameMask.n, this.scroller.d);
        this.net.connect(this.scroller.d, this.display.p);
        this.net.connect(this.foregroundFileName.p, this.foreground.p);
        this.net.connect(this.backgroundFileName.p, this.background.p);
        this.net.connect(this.frameFileName.p, this.frame.p);
        this.net.connect(this.maskFileName.p, this.mask.p);
        this.net.printStatus();
    }
    
    private final void readParameters() throws Exception {
        this.setupNetwork();
        super.parmHandler.register("blendmode", 4, 0, this.blend.p().length, this.blendMode.p.p);
        super.parmHandler.register("lightPulsateSpeed", 1.0f, 0.0f, 10.0f, this.pulsate.in_speed.p);
        super.parmHandler.register("lightMotionType", 1, 0, 6, this.motionType.p.p);
        super.parmHandler.register("lightMotionSpeed", 1.0f, 0.0f, 5.0f, this.motion.in_speed.p);
        super.parmHandler.register("lightMotionScaleX", 0.9f, 0.0f, 1.0f, this.motion.in_xscale.p);
        super.parmHandler.register("lightMotionScaleY", 0.9f, 0.0f, 1.0f, this.motion.in_yscale.p);
        super.parmHandler.register("lightRadiusX", 0.5f, 0.0f, 0.99f, this.vlight.p.p);
        super.parmHandler.register("lightRadiusY", 0.5f, 0.0f, 0.99f, this.vlight.p.d);
        super.parmHandler.register("lightAlpha", 0.8f, 0.0f, 1.0f, this.pulsate.in_max.p);
        super.parmHandler.register("lightAlphaMin", 0.15f, 0.0f, 1.0f, this.pulsate.in_min.p);
        super.parmHandler.register("lightInteractive", true, this.motion.useMouse.p);
        super.parmHandler.register("foregroundImage", null, this.foregroundFileName.p.p);
        super.parmHandler.register("backgroundImage", null, this.backgroundFileName.p.p);
        super.parmHandler.register("frame", null, this.frameFileName.p.p);
        super.parmHandler.register("mask", null, this.maskFileName.p.p);
        super.parmHandler.register("foregroundActive", true, this.vlight.l.p);
        super.parmHandler.register("backgroundActive", true, this.blend.l.p);
        super.parmHandler.register("frameActive", true, this.frameMask.l.p);
        super.parmHandler.readParameters();
        System.out.print(super.parmHandler.toString());
    }
    
    public final void initFX() throws Exception {
        this.readParameters();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        AppletPair.setEffect(this);
    }
    
    public final void FX() throws Exception {
        while (true) {
            this.vlight.initialize();
            this.net.execute();
            this.showMessage();
        }
    }
    
    public final String effectName() {
        return "LightRays 1.0 Personal Edition";
    }
    
    public final boolean checkSite(final String s) {
        return true;
    }
    
    private final void showMessage() {
        if (this.showStatus) {
            this.getAppletContext().showStatus(this.statusMessage);
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(this.hand);
        this.showStatus = true;
        this.motion.mouseEntered = true;
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(this.norm);
        this.showStatus = false;
        this.motion.mouseEntered = false;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.setCursor(this.hand);
        this.showStatus = true;
        this.motion.mouseX = mouseEvent.getX();
        this.motion.mouseY = mouseEvent.getY();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    static {
        if (1354870381117L <= System.currentTimeMillis()) {
            System.out.println("This java program was processed with an unregistered version of Condensity and it expired on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
            throw new Error();
        }
        System.out.println("This java program was processed with an unregistered version of Condensity and will expire on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
    }
}
