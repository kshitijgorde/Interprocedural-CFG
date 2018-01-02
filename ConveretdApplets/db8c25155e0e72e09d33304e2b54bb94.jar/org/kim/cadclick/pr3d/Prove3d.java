// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import javax.swing.ImageIcon;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.ByteArrayOutputStream;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.URL;
import a.b;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Hashtable;
import b.d;
import c.a;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.applet.Applet;

public class Prove3d extends Applet implements Runnable, ImageObserver, o, MouseListener, MouseMotionListener, KeyListener, f, MouseWheelListener
{
    private a dictionary;
    private d versionInfo;
    private String viewOrientation;
    private String motionOrientation;
    private boolean bInitCalled;
    private Hashtable imageHash;
    private boolean firstRunLoop;
    protected Object synchronizedObject;
    protected boolean fieldEventOcurred;
    protected int appletWidth;
    protected int appletHeight;
    protected float vrmlBytesRead;
    private Image appletImage;
    protected Graphics appletGraphics;
    private String appletParam_startView;
    private boolean appletParam_startAnimated;
    private Color appletParam_textcolor;
    private Color appletParam_backcolor;
    private Color appletParam_shadowcolor;
    private String appletParam_background;
    protected Image buttonImage;
    private Thread appletMainThread;
    protected h boundViewpoint;
    private h boundBackground;
    private g rootNode;
    private boolean fitSceneOnLoad;
    private Image backgroundImage;
    protected boolean mouseDownEvent;
    protected float avatarProjectionDistance;
    protected float avatarHeadRadius;
    protected float avatarVisibilityRange;
    protected boolean avatarHeadlight;
    protected float[] actualAvatarMatrix;
    protected float[] actualTranslateMatrix;
    protected boolean currShapeHasTexture;
    protected boolean currShapeIsEmissive;
    protected boolean currShapeIsNotTransparent;
    protected int currShapeRGBAlfa;
    protected int currShapeREDComp;
    protected int currShapeGREENComp;
    protected int currShapeBLUEComp;
    protected int currShapeEmissAlfaRGB;
    protected int currShapeEmissRED;
    protected int currShapeEmissGREEN;
    protected int currShapeEmissBLUE;
    protected int[] currShapeTexture;
    protected int currShapeTransparency;
    protected int currShapeAlfa;
    private c[] m_class;
    private float[][] currSetNormVectors;
    private boolean currSetCCW;
    private boolean currSetIsNotSolid;
    protected int allVisiblePointsNum;
    protected int allVisibleFacesNum;
    protected int allVisibleNormalsNum;
    protected float[] actualLightining;
    protected int actualLightsNumber;
    private int sizeOfShapeArray;
    private h[] shapes;
    private float[][] shapeLightining;
    private int[] lightsNumberArray;
    private float[][] avatarMatrixies;
    private float[][] translateMatrixies;
    protected int numberOfShapes;
    protected int currSetNumber;
    private int[] arrayOfIndexedShapes;
    private int numOfTransparencyDependentShapes;
    private String textOnStdout;
    private String doingText;
    private int numberForMediaTrucker;
    protected boolean appletParam_statistics;
    protected int appletParam_bilinear;
    protected int appletParam_antialiasing;
    private int appletParam_forcegc;
    private float appletParam_transparency;
    private boolean appletParam_startTransparency;
    private int appletParam_rotationDuration;
    private float appletParam_collisionRadius;
    private float appletParam_cameraAngle;
    private String appletParam_sceneName;
    private float appletParam_zoomspeed;
    private String viewTips;
    private String moveTips;
    private i renderer;
    Stv_Toolbar viewBar;
    Stv_Toolbar motionBar;
    HelpCanvas help;
    private int mouse_x;
    private int mouse_y;
    private int mouse_dx;
    private int mouse_dy;
    private int mouseFirstDown_x;
    private int mouseFirstDown_y;
    private boolean keyboardSelected;
    private float RADIUS;
    private Vector transparencyVector;
    private float[] viewAxisAngleVector;
    private float[] startSceneRotVector;
    private int viewRotareCount;
    private float viewRotareNumber;
    private float[] viewRotVector;
    private float[] viewZRotVector;
    private long sleepingTime;
    private float angleIncrement;
    private float[] rootRotationOnStart;
    private float[] avatarPositionOnStart;
    private int movementType;
    private static final float[] bI;
    private static final float[] bL;
    private static final float[] bF;
    private static final float[] bS;
    private static final float[] bA;
    private static final float[] bC;
    private static final float[] bR;
    private float[] isometric;
    private float[] front;
    private float[] back;
    private float[] top;
    private float[] bottom;
    private float[] left;
    private float[] right;
    
    static {
        bI = new float[] { -0.96f, -0.36f, -0.6f, 1.32f };
        bL = new float[] { -1.0f, 0.0f, 0.0f, 1.5707964f };
        bF = new float[] { 0.0f, -1.0f, -1.0f, 3.1415927f };
        bS = new float[] { 1.0f, 0.0f, 0.0f, 0.0f };
        bA = new float[] { 1.0f, 0.0f, 0.0f, 3.1415927f };
        bC = new float[] { -1.0f, 1.0f, 1.0f, 2.10094f };
        bR = new float[] { -1.0f, -1.0f, -1.0f, 2.10094f };
    }
    
    public static void main(final String[] array) {
        new MainAppletContext(new Prove3d(), 800, 600, array);
    }
    
    public Prove3d() {
        this.viewBar = null;
        this.motionBar = null;
        this.isometric = Prove3d.bI;
        this.front = Prove3d.bL;
        this.back = Prove3d.bF;
        this.top = Prove3d.bS;
        this.bottom = Prove3d.bA;
        this.left = Prove3d.bC;
        this.right = Prove3d.bR;
        this.imageHash = new Hashtable();
        this.synchronizedObject = new Object();
        this.b();
        this.sizeOfShapeArray = 100;
        this.shapes = new h[this.sizeOfShapeArray];
        this.shapeLightining = new float[this.sizeOfShapeArray][40];
        this.lightsNumberArray = new int[this.sizeOfShapeArray];
        this.avatarMatrixies = new float[this.sizeOfShapeArray][12];
        this.translateMatrixies = new float[this.sizeOfShapeArray][12];
        this.arrayOfIndexedShapes = new int[this.sizeOfShapeArray];
        this.transparencyVector = new Vector();
        this.viewAxisAngleVector = new float[4];
        this.startSceneRotVector = new float[4];
        this.bInitCalled = false;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        this.addKeyListener(this);
    }
    
    public void b() {
        this.firstRunLoop = true;
        this.fieldEventOcurred = true;
        this.vrmlBytesRead = 0.0f;
        this.avatarProjectionDistance = 1.0f;
        this.numberOfShapes = 0;
        this.numOfTransparencyDependentShapes = 0;
        this.textOnStdout = null;
        this.appletParam_startTransparency = false;
        this.viewRotareCount = -1;
        this.viewRotareNumber = 0.0f;
    }
    
    public void init() {
        if (this.getParameter("debugLevel") != null && !this.getParameter("debugLevel").equals("")) {
            org.kim.cadclick.common.utils.a.int(this.getParameter("debugLevel"));
        }
        else {
            org.kim.cadclick.common.utils.a.int("error");
        }
        this.versionInfo = new a.a();
        org.kim.cadclick.common.utils.a.if(this.versionInfo.for());
        org.kim.cadclick.common.utils.a.a(String.valueOf(d.a) + this.versionInfo.a());
        b.a(this);
        org.kim.cadclick.common.utils.a.do("<Click2CAD.initializeDictionary()>Initializing dictionary...");
        try {
            String s = this.getParameter("dictionaryFile");
            if (!org.kim.cadclick.common.utils.c.int(s)) {
                s = org.kim.cadclick.common.utils.c.a(b.a().toString(), this.getParameter("dictionaryFile"));
            }
            try {
                this.dictionary = new a(new URL(s), Integer.parseInt(this.getParameter("language")));
            }
            catch (Exception ex2) {
                this.dictionary = new a(new URL(s), this.getParameter("language"));
            }
        }
        catch (Exception ex) {
            org.kim.cadclick.common.utils.a.new(ex.getLocalizedMessage());
            JOptionPane.showConfirmDialog(this, "Loading of dictionary failed!\n" + ex.getLocalizedMessage(), "Problem...", -1);
            this.stop();
        }
        org.kim.cadclick.common.utils.a.do("<Click2CAD.initializeDictionary()>Initializing dictionary done...");
        this.l();
        final Dimension size = this.getSize();
        this.appletWidth = size.width;
        this.appletHeight = size.height;
        this.e();
        final String parameter = this.getParameter("debugWindow");
        if (parameter != null && parameter.equalsIgnoreCase("true")) {
            final Frame frame = new Frame("Debug");
            final DebugPanel debugPanel = new DebugPanel();
            debugPanel.a(this);
            frame.add(debugPanel);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
            frame.toFront();
        }
    }
    
    private void e() {
        this.i();
        this.c();
        if (this.bInitCalled) {
            this.b();
        }
        else {
            this.setLayout(null);
            this.m();
            this.f();
        }
        this.h();
        this.long();
        if (this.viewBar != null) {
            this.viewBar.if();
        }
        if (this.motionBar != null) {
            this.motionBar.if();
        }
        this.sleepingTime = 10L;
        this.actualLightining = new float[40];
        this.m_class = new c[500];
        int length = this.m_class.length;
        while (--length >= 0) {
            this.m_class[length] = new c();
        }
        (this.renderer = new i()).a(this, this.appletWidth, this.appletHeight);
        this.renderer.a(false);
        this.bInitCalled = true;
    }
    
    public void c() {
        try {
            this.appletParam_background = this.getParameter("background");
            if (this.appletParam_background != null) {
                final Image else1 = this.else(this.appletParam_background);
                if (else1 != null) {
                    final BufferedImage backgroundImage = new BufferedImage(this.appletWidth, this.appletHeight, 1);
                    backgroundImage.getGraphics().setColor(Color.white);
                    backgroundImage.getGraphics().fillRect(0, 0, this.appletWidth, this.appletHeight);
                    backgroundImage.getGraphics().drawImage(else1, (this.appletWidth - else1.getWidth(this)) / 2, (this.appletHeight - else1.getHeight(this)) / 2, null);
                    this.backgroundImage = backgroundImage;
                }
            }
        }
        catch (Exception ex) {
            System.out.print("Unable to load background image :" + this.appletParam_background);
        }
    }
    
    public Image else() {
        try {
            return this.else(this.getParameter("buttonImages"));
        }
        catch (Exception ex) {
            System.out.print("Unable to load background image :" + this.appletParam_background);
            return null;
        }
    }
    
    public void i() {
        try {
            this.appletParam_sceneName = this.getParameter("scene");
        }
        catch (Exception ex) {}
        this.appletParam_textcolor = this.a("textcolor", 255, 255, 255);
        this.appletParam_shadowcolor = this.a("shadowcolor", 128, 128, 128);
        this.setBackground(this.appletParam_backcolor = this.a("backcolor", 0, 0, 0));
        this.appletParam_transparency = this.a("textureTransparency", 0.5f, true);
        this.appletParam_statistics = this.if("statistics", false);
        this.appletParam_forcegc = this.a("forcegc", 10000);
        this.appletParam_bilinear = this.goto("bilinear");
        this.appletParam_antialiasing = this.goto("antialiasing");
        this.appletParam_rotationDuration = this.a("rotationDuration", 3);
        this.appletParam_startTransparency = this.if("startTransparent", false);
        this.appletParam_collisionRadius = this.a("collisionRadius", 0.0f, false);
        this.appletParam_zoomspeed = this.a("zoomspeed", 1.0f, true);
        this.appletParam_cameraAngle = this.a("cameraAngle", 0.785398f, true);
        if (this.appletParam_cameraAngle < 0.0f || this.appletParam_cameraAngle > 3.141592653589793) {
            this.appletParam_cameraAngle = 0.785398f;
        }
        this.RADIUS = 1.0f / this.a("turnspeed", 1.0f, true) * 100.0f;
        this.avatarHeadlight = this.if("headlightOn", true);
        this.appletParam_startView = this.getParameter("startView");
        this.appletParam_startAnimated = this.if("startAnimated", false);
        if (this.appletParam_startAnimated) {
            this.mouse_dx = -10;
            this.mouse_dy = 10;
            this.movementType = 5;
        }
        this.viewOrientation = this.getParameter("viewBar");
        this.motionOrientation = this.getParameter("motionBar");
        this.viewTips = this.getParameter("viewTips");
        this.moveTips = this.getParameter("moveTips");
        this.fitSceneOnLoad = this.if("fitSceneOnLoad", false);
    }
    
    private void long() {
        this.void();
        if (this.viewBar != null) {
            this.remove(this.viewBar);
            this.viewBar = null;
        }
        if (this.motionBar != null) {
            this.remove(this.motionBar);
            this.motionBar = null;
        }
        String parameter = this.getParameter("buttonHide");
        if (parameter == null) {
            parameter = "";
        }
        if (this.viewOrientation == null) {
            this.viewOrientation = "off";
        }
        else {
            this.viewOrientation = this.viewOrientation.trim();
        }
        if (this.motionOrientation == null) {
            this.motionOrientation = "off";
        }
        else {
            this.motionOrientation = this.motionOrientation.trim();
        }
        Point point = new Point();
        if (this.viewOrientation.equalsIgnoreCase("top")) {
            point = new Point(5, 5);
            if (this.viewBar == null) {
                this.viewBar = new Stv_Toolbar(0, 0, "view", 0, parameter);
            }
        }
        else if (this.viewOrientation.equalsIgnoreCase("right")) {
            point = new Point(this.appletWidth - Stv_Button.if - 5, 5);
            if (this.viewBar == null) {
                this.viewBar = new Stv_Toolbar(0, 0, "view", 1, parameter);
            }
        }
        else if (this.viewOrientation.equalsIgnoreCase("bottom")) {
            point = new Point(5, this.appletHeight - Stv_Button.a - 5);
            if (this.viewBar == null) {
                this.viewBar = new Stv_Toolbar(0, 0, "view", 0, parameter);
            }
        }
        else if (this.viewOrientation.equalsIgnoreCase("left")) {
            point = new Point(5, 5);
            if (this.viewBar == null) {
                this.viewBar = new Stv_Toolbar(0, 0, "view", 1, parameter);
            }
        }
        if (this.viewBar != null) {
            final String[] array = null;
            String[] array2;
            if (Boolean.valueOf(this.getParameter("showTooltips").toLowerCase()).equals(Boolean.TRUE)) {
                array2 = new String[] { this.dictionary.a("View_Front"), this.dictionary.a("View_Right"), this.dictionary.a("View_Top"), this.dictionary.a("View_Back"), this.dictionary.a("View_Left"), this.dictionary.a("View_Bottom"), this.dictionary.a("View_Isometric") };
            }
            else {
                array2 = new String[7];
            }
            if (this.viewTips != null && this.viewTips.length() > 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(this.viewTips, "|");
                for (int n = 0; stringTokenizer.hasMoreTokens() && n < array2.length; ++n) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (nextToken.length() > 1) {
                        array2[n] = nextToken;
                    }
                }
            }
            this.viewBar.a(new Stv_Button(0, 0, 0, array2[0], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(1, 0, 1, array2[1], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(2, 0, 2, array2[2], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(3, 0, 3, array2[3], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(4, 0, 4, array2[4], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(5, 0, 5, array2[5], this.buttonImage, Stv_Toolbar.a));
            this.viewBar.a(new Stv_Button(6, 0, 6, array2[6], this.buttonImage, Stv_Toolbar.a));
            this.add(this.viewBar);
            this.viewBar.setLocation(point);
        }
        if (this.motionOrientation.equalsIgnoreCase("top")) {
            point = new Point(5, 5);
            if (this.motionBar == null) {
                this.motionBar = new Stv_Toolbar(0, 0, "nav", 0, parameter);
            }
        }
        else if (this.motionOrientation.equalsIgnoreCase("right")) {
            point = new Point(this.appletWidth - Stv_Button.if - 5, 5);
            if (this.motionBar == null) {
                this.motionBar = new Stv_Toolbar(0, 0, "nav", 1, parameter);
            }
        }
        else if (this.motionOrientation.equalsIgnoreCase("bottom")) {
            point = new Point(5, this.appletHeight - Stv_Button.a - 5);
            if (this.motionBar == null) {
                this.motionBar = new Stv_Toolbar(0, 0, "nav", 0, parameter);
            }
        }
        else if (this.motionOrientation.equalsIgnoreCase("left")) {
            point = new Point(5, 5);
            if (this.motionBar == null) {
                this.motionBar = new Stv_Toolbar(0, 0, "nav", 1, parameter);
            }
        }
        if (this.motionBar != null) {
            final String[] array3 = null;
            String[] array4;
            if (Boolean.valueOf(this.getParameter("showTooltips").toLowerCase()).equals(Boolean.TRUE)) {
                array4 = new String[] { this.dictionary.a("Button_Rotate"), this.dictionary.a("Button_Move"), this.dictionary.a("Button_Select"), this.dictionary.a("Button_Zoom"), this.dictionary.a("Button_ToggleTransparency"), this.dictionary.a("Button_ToggleAnimation"), this.dictionary.a("Button_StartView") };
            }
            else {
                array4 = new String[7];
            }
            if (this.moveTips != null && this.moveTips.length() > 0) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(this.moveTips, "|");
                for (int n2 = 0; stringTokenizer2.hasMoreTokens() && n2 < array4.length; ++n2) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    if (nextToken2.length() > 1) {
                        array4[n2] = nextToken2;
                    }
                }
            }
            this.motionBar.a(new Stv_Button(0, 2, 7, array4[0], this.buttonImage, Stv_Toolbar.a), "movement", !this.appletParam_startAnimated);
            this.motionBar.a(new Stv_Button(1, 2, 8, array4[1], this.buttonImage, Stv_Toolbar.a), "movement", false);
            this.motionBar.a(new Stv_Button(2, 2, 9, array4[2], this.buttonImage, Stv_Toolbar.a), "movement", false);
            this.motionBar.a(new Stv_Button(3, 2, 10, array4[3], this.buttonImage, Stv_Toolbar.a), "movement", false);
            this.motionBar.a(new Stv_Button(4, 1, 11, array4[4], this.buttonImage, Stv_Toolbar.a, this.appletParam_startTransparency));
            this.motionBar.a(new Stv_Button(5, 2, 12, array4[5], this.buttonImage, Stv_Toolbar.a), "movement", this.appletParam_startAnimated);
            this.motionBar.a(new Stv_Button(6, 0, 13, array4[6], this.buttonImage, Stv_Toolbar.a));
            this.add(this.motionBar);
            this.motionBar.setLocation(point);
        }
        if (this.viewBar != null && this.motionBar != null) {
            final Rectangle bounds = this.viewBar.getBounds();
            final Rectangle bounds2 = this.motionBar.getBounds();
            if (this.motionOrientation.equalsIgnoreCase(this.viewOrientation)) {
                if (bounds.x + bounds.width > this.appletWidth) {
                    this.motionBar.setLocation(bounds.x - bounds.width, bounds.y);
                }
                else {
                    this.motionBar.setLocation(bounds.x + bounds.width, bounds.y);
                }
            }
            else if (this.viewOrientation.equalsIgnoreCase("left") && !this.motionOrientation.equalsIgnoreCase("right")) {
                this.motionBar.setLocation(bounds2.x + bounds.width, bounds2.y);
            }
            else if (this.viewOrientation.equalsIgnoreCase("top") && !this.motionOrientation.equalsIgnoreCase("bottom")) {
                this.motionBar.setLocation(bounds2.x, bounds2.y + bounds.height);
            }
            else if (this.motionOrientation.equalsIgnoreCase("left") && !this.viewOrientation.equalsIgnoreCase("right")) {
                this.viewBar.setLocation(bounds.x + bounds2.width, bounds.y);
            }
            else if (this.motionOrientation.equalsIgnoreCase("top") && !this.viewOrientation.equalsIgnoreCase("bottom")) {
                this.viewBar.setLocation(bounds.x, bounds.y + bounds2.height);
            }
        }
    }
    
    protected final void a(final j j, final boolean b) {
        this.currSetNormVectors = j.a5;
        this.currSetCCW = j.be.cj;
        this.currSetIsNotSolid = !j.a9.cj;
        this.currShapeIsEmissive &= (this.currSetNormVectors != null);
        if (this.currShapeIsNotTransparent == b) {
            this.lightsNumberArray[this.currSetNumber] = -1;
            if (this.do(((h)j.a6.b6).aR.ck)) {
                return;
            }
            if (this.currShapeIsEmissive) {
                this.goto();
            }
            float[] ck = null;
            final h h = (h)j.a4.b6;
            if (h != null) {
                ck = h.aR.ck;
            }
            this.a(j.bc.b3, ck, j.bb.b3, j.a3);
        }
    }
    
    private final void a(final int[] array, final float[] array2, int[] array3, final int[] array4) {
        if (array3.length == 0) {
            array3 = array;
        }
        if (array2 == null) {
            this.currShapeHasTexture = false;
        }
        else if (array2 != null && array2.length == 0) {
            this.currShapeHasTexture = false;
        }
        int i = 0;
        this.renderer.byte();
        while (i < array.length) {
            final c c = this.m_class[array[i]];
            c c2 = this.m_class[array[i + 1]];
            c c3 = this.m_class[array[i + 2]];
            if (this.currShapeHasTexture) {
                final int n = array3[i] << 1;
                c.goto = array2[n];
                c.else = 1.0f - array2[n + 1];
                final int n2 = array3[i + 1] << 1;
                c2.goto = array2[n2];
                c2.else = 1.0f - array2[n2 + 1];
                final int n3 = array3[i + 2] << 1;
                c3.goto = array2[n3];
                c3.else = 1.0f - array2[n3 + 1];
            }
            if (this.currShapeIsEmissive) {
                c.byte = this.currSetNormVectors[array4[i]][3];
                c2.byte = this.currSetNormVectors[array4[i + 1]][3];
                c3.byte = this.currSetNormVectors[array4[i + 2]][3];
            }
            i += 3;
            while (true) {
                final float new1 = c.new;
                final float for1 = c.for;
                final float if1 = c.if;
                final float n4 = c2.new - new1;
                final float n5 = c2.for - for1;
                final float n6 = c2.if - if1;
                final float n7 = c3.new - new1;
                final float n8 = c3.for - for1;
                final float n9 = c3.if - if1;
                final float n10 = new1 * (n5 * n9 - n6 * n8) + for1 * (n6 * n7 - n4 * n9) + if1 * (n4 * n8 - n5 * n7);
                if ((n10 > 0.0f ^ this.currSetCCW) || this.currSetIsNotSolid) {
                    if (!this.currSetIsNotSolid) {
                        if (this.currSetCCW) {
                            this.renderer.a(c, c2, c3);
                        }
                        else {
                            this.renderer.a(c, c3, c2);
                        }
                    }
                    else if (n10 > 0.0f) {
                        this.renderer.a(c, c3, c2);
                    }
                    else {
                        this.renderer.a(c, c2, c3);
                    }
                    ++this.allVisibleFacesNum;
                }
                c2 = c3;
                final int n11;
                if ((n11 = array[i]) == -1) {
                    break;
                }
                c3 = this.m_class[n11];
                if (this.currShapeHasTexture) {
                    final int n12 = array3[i] << 1;
                    c3.goto = array2[n12];
                    c3.else = 1.0f - array2[n12 + 1];
                }
                if (this.currShapeIsEmissive) {
                    c3.byte = this.currSetNormVectors[array4[i]][3];
                }
                ++i;
            }
            ++i;
        }
    }
    
    protected final void if(final j j, final boolean b) {
        if (b) {
            this.lightsNumberArray[this.currSetNumber] = -1;
            if (this.do(((h)j.a6.b6).aR.ck)) {
                return;
            }
            final int n = ((h)j.a6.b6).aR.ck.length / 3;
            this.renderer.byte();
            for (int i = 0; i < n; ++i) {
                if (this.m_class[i].a == 0) {
                    this.renderer.a(this.m_class[i].char, this.m_class[i].case, this.m_class[i].try);
                }
            }
        }
    }
    
    protected final void do(final j j, final boolean b) {
        if (b) {
            this.lightsNumberArray[this.currSetNumber] = -1;
            if (this.do(((h)j.a6.b6).aR.ck)) {
                return;
            }
            this.a(j.bc.b3);
        }
    }
    
    private final void a(final int[] array) {
        this.renderer.byte();
        for (int i = 0; i < array.length; ++i) {
            c c = this.m_class[array[i++]];
            while (array[i] != -1) {
                final c c2 = this.m_class[array[i++]];
                if ((c.a & c2.a) == 0x0) {
                    this.renderer.if(c, c2);
                }
                c = c2;
            }
        }
    }
    
    protected final void do(final boolean b) {
        if (b) {
            this.currSetNumber = 0;
            while (this.currSetNumber < this.numberOfShapes) {
                if (this.lightsNumberArray[this.currSetNumber] != -1) {
                    this.actualAvatarMatrix = this.avatarMatrixies[this.currSetNumber];
                    this.actualTranslateMatrix = this.translateMatrixies[this.currSetNumber];
                    this.actualLightining = this.shapeLightining[this.currSetNumber];
                    this.actualLightsNumber = this.lightsNumberArray[this.currSetNumber];
                    this.shapes[this.currSetNumber].a(b);
                }
                ++this.currSetNumber;
            }
            this.g();
            return;
        }
        for (int i = 0; i < this.numOfTransparencyDependentShapes; ++i) {
            this.currSetNumber = this.arrayOfIndexedShapes[i];
            if (this.lightsNumberArray[this.currSetNumber] != -1) {
                this.actualAvatarMatrix = this.avatarMatrixies[this.currSetNumber];
                this.actualTranslateMatrix = this.translateMatrixies[this.currSetNumber];
                this.actualLightining = this.shapeLightining[this.currSetNumber];
                this.actualLightsNumber = this.lightsNumberArray[this.currSetNumber];
                this.shapes[this.currSetNumber].a(b);
            }
        }
    }
    
    protected final void a(final h h) {
        if (this.numberOfShapes == this.sizeOfShapeArray) {
            final h[] shapes = new h[this.sizeOfShapeArray << 1];
            System.arraycopy(this.shapes, 0, shapes, 0, this.sizeOfShapeArray);
            this.shapes = shapes;
            final float[][] shapeLightining = new float[this.sizeOfShapeArray << 1][40];
            System.arraycopy(this.shapeLightining, 0, shapeLightining, 0, this.sizeOfShapeArray);
            this.shapeLightining = shapeLightining;
            final float[][] avatarMatrixies = new float[this.sizeOfShapeArray << 1][12];
            System.arraycopy(this.avatarMatrixies, 0, avatarMatrixies, 0, this.sizeOfShapeArray);
            this.avatarMatrixies = avatarMatrixies;
            final float[][] translateMatrixies = new float[this.sizeOfShapeArray << 1][12];
            System.arraycopy(this.translateMatrixies, 0, translateMatrixies, 0, this.sizeOfShapeArray);
            this.translateMatrixies = translateMatrixies;
            final int[] lightsNumberArray = new int[this.sizeOfShapeArray << 1];
            System.arraycopy(this.lightsNumberArray, 0, lightsNumberArray, 0, this.sizeOfShapeArray);
            this.lightsNumberArray = lightsNumberArray;
            this.sizeOfShapeArray <<= 1;
        }
        this.shapes[this.numberOfShapes] = h;
        System.arraycopy(this.actualAvatarMatrix, 0, this.avatarMatrixies[this.numberOfShapes], 0, 12);
        System.arraycopy(this.actualTranslateMatrix, 0, this.translateMatrixies[this.numberOfShapes], 0, 12);
        this.lightsNumberArray[this.numberOfShapes] = this.actualLightsNumber;
        if (this.actualLightsNumber != 0) {
            System.arraycopy(this.actualLightining, 0, this.shapeLightining[this.numberOfShapes], 0, this.actualLightsNumber << 2);
        }
        ++this.numberOfShapes;
    }
    
    private final boolean do(final float[] array) {
        if (array.length > this.m_class.length * 3) {
            this.m_class = new c[array.length / 3];
            int length = this.m_class.length;
            while (--length >= 0) {
                this.m_class[length] = new c();
            }
        }
        int i;
        int n;
        int n2;
        float n3;
        float n4;
        float n5;
        c c;
        float[] a;
        int a2;
        for (i = 0, n = 0, n2 = -1; i < array.length; n3 = array[i++], n4 = array[i++], n5 = array[i++], c = this.m_class[n], a = k.a(n3, n4, n5, this.actualAvatarMatrix, true), c.new = a[0], c.for = a[1], c.if = a[2], a2 = this.a(a, c), c.a = a2, n2 &= a2, ++n) {}
        this.allVisiblePointsNum += n;
        return n2 != 0;
    }
    
    private final void goto() {
        int i = 0;
        final float[] actualLightining = this.actualLightining;
        while (i < this.currSetNormVectors.length) {
            final float[] array = this.currSetNormVectors[i++];
            final float[] a = k.a(array[0], array[1], array[2], this.actualTranslateMatrix, false);
            final float n = a[0];
            final float n2 = a[1];
            final float n3 = a[2];
            float n4 = 0.0f;
            if (this.currSetIsNotSolid) {
                for (int j = 0; j < this.actualLightsNumber; ++j) {
                    final int n5 = j << 2;
                    final float n6 = actualLightining[n5 + 3] * (n * actualLightining[n5] + n2 * actualLightining[n5 + 1] + n3 * actualLightining[n5 + 2]);
                    n4 += ((n6 <= 0.0f) ? (-n6) : n6);
                }
            }
            else {
                for (int k = 0; k < this.actualLightsNumber; ++k) {
                    final int n7 = k << 2;
                    final float n8 = actualLightining[n7 + 3] * (n * actualLightining[n7] + n2 * actualLightining[n7 + 1] + n3 * actualLightining[n7 + 2]);
                    n4 += ((n8 <= 0.0f) ? 0.0f : n8);
                }
            }
            array[3] = n4;
        }
        this.allVisibleNormalsNum += i;
    }
    
    protected void a(final float n, final float n2, final float n3, final float[] array) {
        final float[] a = k.a(n, n2, n3, this.actualAvatarMatrix, true);
        final int n4 = 1;
        array[n4] *= -1.0f;
        System.arraycopy(a, 0, array, 0, 3);
    }
    
    protected byte[] a(final URL url, final boolean b) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            float n = 0.0f;
            final URLConnection openConnection = url.openConnection();
            openConnection.connect();
            int contentLength = openConnection.getContentLength();
            if (contentLength < 1) {
                contentLength = Integer.MAX_VALUE;
            }
            inputStream = openConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream(2048);
            final byte[] array = new byte[2048];
            while (true) {
                final int read = inputStream.read(array);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
                byteArrayOutputStream.flush();
                n += read;
                if (!b) {
                    continue;
                }
                this.if(n / contentLength);
            }
            inputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (SecurityException ex) {
            ex.printStackTrace();
            this.case("Can't load file " + url + " : " + ex.getMessage());
        }
        catch (MalformedURLException ex2) {
            ex2.printStackTrace();
            this.case("Wrong url: " + url);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
            this.case("Connection failed: " + url);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (Throwable t) {}
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                }
                catch (Throwable t2) {}
            }
        }
        return null;
    }
    
    protected int[] a(final String s, final boolean b) {
        int[] a = this.imageHash.get(s);
        if (a != null) {
            return a;
        }
        final Image else1 = this.else(s);
        if (else1 != null) {
            a = this.a(s, else1, b);
            this.imageHash.put(s, a);
        }
        return a;
    }
    
    private int[] a(final String s, final Image image, final boolean b) {
        try {
            int appletWidth = this.appletWidth;
            int appletHeight = this.appletHeight;
            final int n = 512;
            final int width = image.getWidth(null);
            final int height = image.getHeight(null);
            if (b) {
                final int max = Math.max(width, height);
                if (max > 256) {
                    appletHeight = (appletWidth = n);
                }
                else if (max > 128) {
                    appletHeight = (appletWidth = 256);
                }
                else if (max > 64) {
                    appletHeight = (appletWidth = 128);
                }
                else if (max > 32) {
                    appletHeight = (appletWidth = 64);
                }
                else {
                    appletHeight = (appletWidth = 32);
                }
            }
            final int[] array = new int[appletWidth * appletHeight];
            if (width == appletWidth && height == appletHeight) {
                this.a(image, 0, 0, appletWidth, appletHeight, array, 0, appletWidth);
            }
            else {
                final int[] array2 = new int[width * height];
                this.a(image, 0, 0, width, height, array2, 0, width);
                final int n2 = (width << 16) / appletWidth;
                final int n3 = (height << 16) / appletHeight;
                int n4 = 0;
                int n5 = 0;
                while (--appletHeight >= 0) {
                    int n6 = 0;
                    final int n7 = (n4 >> 16) * width;
                    n4 += n3;
                    while (--appletWidth >= 0) {
                        array[n5++] = array2[(n6 >> 16) + n7];
                        n6 += n2;
                    }
                }
            }
            return array;
        }
        catch (Exception ex) {
            this.byte("Error in image: " + s);
            ex.printStackTrace();
            return null;
        }
    }
    
    private void m() {
        try {
            final ImageIcon imageIcon = new ImageIcon(this.else());
            this.buttonImage = imageIcon.getImage();
            Stv_Button.a(imageIcon.getIconWidth() / 4, imageIcon.getIconWidth() / 4);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void h() {
        try {
            this.appletImage = this.createImage(this.appletWidth, this.appletHeight);
            this.appletGraphics = this.appletImage.getGraphics();
        }
        catch (Exception ex) {
            this.case("error while initializing screen");
        }
    }
    
    protected void case(final String textOnStdout) {
        System.out.println(textOnStdout);
        if (textOnStdout != null) {
            this.textOnStdout = textOnStdout;
        }
        this.repaint();
    }
    
    protected void byte(final String s) {
        System.out.println(s);
        this.showStatus(s);
    }
    
    protected void a(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        try {
            new PixelGrabber(image, n, n2, n3, n4, array, n5, n6).grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    protected URL int(String substring) throws MalformedURLException {
        final int lastIndex = substring.lastIndexOf("http:");
        if (lastIndex != -1) {
            substring = substring.substring(lastIndex);
        }
        URL url;
        if (substring.indexOf(58) != -1 && substring.indexOf(58) < substring.indexOf(47)) {
            url = new URL(substring);
        }
        else {
            url = new URL(this.getDocumentBase(), substring);
        }
        return url;
    }
    
    private boolean if(final String s, final boolean b) {
        try {
            final String lowerCase = this.getParameter(s).toLowerCase();
            return lowerCase.equals("on") | lowerCase.equals("true");
        }
        catch (Exception ex) {
            return b;
        }
    }
    
    private int a(final String s, final int n) {
        try {
            return Math.abs(Integer.parseInt(this.getParameter(s)));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private float a(final String s, final float n, final boolean b) {
        try {
            float n2 = Float.valueOf(this.getParameter(s));
            if (b) {
                n2 = Math.abs(n2);
            }
            return n2;
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private int goto(final String s) {
        try {
            final String lowerCase = this.getParameter(s).toLowerCase();
            if (lowerCase.equals("on")) {
                return 1;
            }
            return lowerCase.equals("oneshot") ? 2 : 0;
        }
        catch (Exception ex) {
            return 2;
        }
    }
    
    private Color a(final String s, final int n, final int n2, final int n3) {
        try {
            final int int1 = Integer.parseInt(this.getParameter(s), 16);
            return new Color(int1 >> 16 & 0xFF, int1 >> 8 & 0xFF, int1 & 0xFF);
        }
        catch (Exception ex) {
            return new Color(n, n2, n3);
        }
    }
    
    private Image else(final String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        try {
            final Image image = this.getToolkit().getImage(this.int(s));
            final MediaTracker mediaTracker = new MediaTracker(this);
            final int d = this.d();
            mediaTracker.addImage(image, d);
            mediaTracker.waitForID(d);
            if (image.getWidth(this) < 0 || image.getHeight(this) < 0) {
                throw new Exception();
            }
            return image;
        }
        catch (Exception ex) {
            this.byte("image <" + s + "> not fetched correctly");
            return null;
        }
    }
    
    protected void a(final long n) {
        Thread.yield();
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        if (this.textOnStdout != null) {
            while (true) {
                this.repaint();
                this.a(1000L);
            }
        }
        else {
            try {
                if (this.firstRunLoop) {
                    if (this.appletParam_sceneName != null) {
                        this.char(this.appletParam_sceneName);
                    }
                    this.firstRunLoop = false;
                }
                int n = 0;
                int n2 = 0;
                while (this.appletMainThread != null) {
                    if (this.rootNode == null) {
                        if (this.textOnStdout == null) {
                            this.case("Scene not loaded Error");
                        }
                        return;
                    }
                    synchronized (this.synchronizedObject) {
                        if (this.fieldEventOcurred) {
                            n2 = 0;
                            this.renderer.a(this.rootNode, false);
                            this.fieldEventOcurred = false;
                            this.a(0, 0, 0);
                            if (this.appletParam_statistics) {
                                this.repaint();
                            }
                        }
                        else {
                            if (++n2 == 1) {
                                this.renderer.a(this.rootNode, true);
                            }
                            else {
                                this.a(100L);
                            }
                            this.repaint();
                        }
                    }
                    // monitorexit(this.synchronizedObject)
                    this.a(this.sleepingTime);
                    if (++n <= this.appletParam_forcegc) {
                        continue;
                    }
                    System.gc();
                    n = 0;
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {
                if (this.textOnStdout == null) {
                    this.case("Out of Memory error");
                }
            }
            catch (Exception ex) {
                if (this.textOnStdout == null) {
                    this.case("Scene error");
                }
                ex.printStackTrace();
            }
        }
    }
    
    public void destroy() {
        this.imageHash.clear();
    }
    
    private void f() {
        if (this.if("help", true)) {
            (this.help = new HelpCanvas(this, this.dictionary)).setVisible(false);
            this.add(this.help);
        }
        else {
            this.help = null;
        }
    }
    
    protected final void g() {
        float[] array = new float[this.sizeOfShapeArray];
        if (this.arrayOfIndexedShapes.length < this.sizeOfShapeArray) {
            this.arrayOfIndexedShapes = new int[this.sizeOfShapeArray];
            array = new float[this.sizeOfShapeArray];
        }
        this.numOfTransparencyDependentShapes = 0;
        final float[] array2 = new float[3];
        for (int i = 0; i < this.numberOfShapes; ++i) {
            if (this.lightsNumberArray[i] != -1) {
                final j j = (j)this.shapes[i].aI.b6;
                if (j != null) {
                    this.actualAvatarMatrix = this.avatarMatrixies[i];
                    this.a(j.a2[0], j.a2[1], j.a2[2], array2);
                    this.arrayOfIndexedShapes[this.numOfTransparencyDependentShapes] = i;
                    array[this.numOfTransparencyDependentShapes++] = array2[2];
                }
            }
        }
        for (int k = this.numOfTransparencyDependentShapes >> 1; k > 0; k >>= 1) {
            for (int l = k; l < this.numOfTransparencyDependentShapes; ++l) {
                for (int n = l - k; n >= 0 && array[n] > array[n + k]; n -= k) {
                    final float n2 = array[n];
                    array[n] = array[n + k];
                    array[n + k] = n2;
                    final int n3 = this.arrayOfIndexedShapes[n];
                    this.arrayOfIndexedShapes[n] = this.arrayOfIndexedShapes[n + k];
                    this.arrayOfIndexedShapes[n + k] = n3;
                }
            }
        }
    }
    
    public synchronized void stop() {
        try {
            if (this.appletMainThread != null) {
                this.appletMainThread = null;
            }
        }
        catch (Exception ex) {}
        System.gc();
    }
    
    private void j() {
        synchronized (this.synchronizedObject) {
            this.a(0, 0, -1);
        }
        // monitorexit(this.synchronizedObject)
    }
    
    public void char(final String s) throws IllegalArgumentException, Stv_X3DException {
        final e e = new e(this, this.firstRunLoop);
        g rootNode;
        try {
            rootNode = (g)e.a(s, "...");
        }
        catch (Exception ex) {
            rootNode = null;
        }
        if (rootNode != null) {
            e.a(rootNode);
        }
        synchronized (this.synchronizedObject) {
            if (rootNode != null) {
                this.rootNode = rootNode;
            }
            this.fieldEventOcurred = true;
            this.boundViewpoint = new h("defView", 12, this);
            if (this.boundBackground == null) {
                this.new(this.appletParam_background);
            }
            final String parameter = this.getParameter("lightURL");
            if (parameter != null) {
                this.try("loading lights");
                this.if(0.0f);
                parameter.trim();
                this.if(1.0f);
            }
            System.gc();
        }
        // monitorexit(this.synchronizedObject)
        this.k();
        if (this.fitSceneOnLoad) {
            this.k();
        }
        this.renderer.a(true);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void start() {
        if (this.appletMainThread == null) {
            System.gc();
            (this.appletMainThread = new Thread(this, "Applet Main Thread")).start();
        }
        this.requestFocus();
    }
    
    public void paint(final Graphics graphics) {
        if (this.textOnStdout == null) {
            final String s = "";
            if (this.renderer == null || !this.renderer.do()) {
                if (graphics == null || this.appletGraphics == null) {
                    return;
                }
                if (this.backgroundImage != null) {
                    this.appletGraphics.drawImage(this.backgroundImage, 0, 0, this.appletWidth, this.appletHeight, null);
                }
                final int n = 90;
                this.appletGraphics.setColor(Color.white);
                this.appletGraphics.fillRect(this.appletWidth / 2 - n, this.appletHeight / 2 - 20, n * 2, 40);
                this.appletGraphics.setColor(Color.black);
                this.appletGraphics.setFont(this.appletGraphics.getFont().deriveFont(12.0f).deriveFont(0));
                this.appletGraphics.drawString(s, this.appletWidth / 2 - n + 5, this.appletHeight / 2 - 6);
                if (this.doingText != null) {
                    final int charsWidth = this.getToolkit().getFontMetrics(this.appletGraphics.getFont()).charsWidth(s.toCharArray(), 0, s.length());
                    final int n2 = (n * 2 - charsWidth - 10 - this.getToolkit().getFontMetrics(this.appletGraphics.getFont()).charsWidth(this.doingText.toCharArray(), 0, this.doingText.length())) / 2;
                    this.appletGraphics.setColor(Color.blue);
                    this.appletGraphics.drawString(this.doingText, this.appletWidth / 2 - n + charsWidth + 5 + n2, this.appletHeight / 2 - 6);
                }
                this.appletGraphics.setColor(Color.gray);
                this.appletGraphics.drawRect(this.appletWidth / 2 - n, this.appletHeight / 2 - 20, n * 2, 40);
                this.appletGraphics.drawLine(this.appletWidth / 2 - n, this.appletHeight / 2, this.appletWidth / 2 + n, this.appletHeight / 2);
                this.appletGraphics.setColor(this.appletParam_shadowcolor);
                this.appletGraphics.fillRect(this.appletWidth / 2 - n + 1, this.appletHeight / 2 + 1, (int)((n * 2 - 1) * this.vrmlBytesRead), 19);
            }
            else if (this.renderer.as != null) {
                this.appletGraphics.drawImage(this.renderer.as, 0, 0, null);
            }
            graphics.drawImage(this.appletImage, 0, 0, null);
            if (this.mouseDownEvent && this.movementType == 4) {
                graphics.setColor(Color.white);
                graphics.setXORMode(Color.black);
                graphics.drawRect((this.mouseFirstDown_x < this.mouse_x) ? this.mouseFirstDown_x : this.mouse_x, (this.mouseFirstDown_y < this.mouse_y) ? this.mouseFirstDown_y : this.mouse_y, Math.abs(this.mouse_x - this.mouseFirstDown_x), Math.abs(this.mouse_y - this.mouseFirstDown_y));
            }
            return;
        }
        graphics.setColor(this.appletParam_backcolor);
        graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
        graphics.setFont(graphics.getFont().deriveFont(12.0f).deriveFont(0));
        final int size = graphics.getFont().getSize();
        graphics.setColor(this.appletParam_shadowcolor);
        graphics.fillRect(0, this.appletHeight / 2 - size + 1, this.appletWidth, size + 2);
        graphics.setColor(this.appletParam_textcolor);
        graphics.drawString(this.textOnStdout, this.appletWidth / 2 - this.getToolkit().getFontMetrics(graphics.getFont().deriveFont(12.0f).deriveFont(0)).charsWidth(this.textOnStdout.toCharArray(), 0, this.textOnStdout.length()) / 2, this.appletHeight / 2);
    }
    
    public n do(final String s, final String s2) {
        int i = 0;
    Label_0103:
        while (i < o.K.length) {
            if (!o.K[i].equals(s)) {
                ++i;
            }
            else {
                switch (o.v[i]) {
                    case 0: {
                        return new g(s2, i, this);
                    }
                    case 1: {
                        return new h(s2, i, this);
                    }
                    case 3: {
                        return new j(s2, i, this);
                    }
                    default: {
                        break Label_0103;
                    }
                }
            }
        }
        return null;
    }
    
    protected void if(float vrmlBytesRead) {
        if (vrmlBytesRead < 0.0f) {
            vrmlBytesRead = 0.0f;
        }
        else if (vrmlBytesRead > 1.0f) {
            vrmlBytesRead = 1.0f;
        }
        this.vrmlBytesRead = vrmlBytesRead;
        this.repaint();
    }
    
    private synchronized int d() {
        return this.numberForMediaTrucker++;
    }
    
    protected boolean do(final float[] array, final float[] array2) {
        if (array2[0] == -1.0f && array2[1] == -1.0f && array2[2] == -1.0f) {
            return true;
        }
        final float[] array3 = new float[3];
        final float[] array4 = new float[3];
        k.if(array3, array4, array, array2);
        final float n = array3[0];
        final float n2 = array3[1];
        final float n3 = array3[2];
        final float n4 = array4[0];
        final float n5 = array4[1];
        final float n6 = array4[2];
        final float[] array5 = new float[3];
        this.a(n, n2, n3, array5);
        final int a = this.a(array5, null);
        if (a == 0) {
            return true;
        }
        this.a(n, n2, n6, array5);
        final int n7 = a & this.a(array5, null);
        if (n7 == 0) {
            return true;
        }
        this.a(n, n5, n3, array5);
        final int n8 = n7 & this.a(array5, null);
        if (n8 == 0) {
            return true;
        }
        this.a(n, n5, n6, array5);
        final int n9 = n8 & this.a(array5, null);
        if (n9 == 0) {
            return true;
        }
        this.a(n4, n2, n3, array5);
        final int n10 = n9 & this.a(array5, null);
        if (n10 == 0) {
            return true;
        }
        this.a(n4, n2, n6, array5);
        final int n11 = n10 & this.a(array5, null);
        if (n11 == 0) {
            return true;
        }
        this.a(n4, n5, n3, array5);
        final int n12 = n11 & this.a(array5, null);
        if (n12 == 0) {
            return true;
        }
        this.a(n4, n5, n6, array5);
        return (n12 & this.a(array5, null)) == 0x0;
    }
    
    private int a(final float[] array, final c c) {
        if (array[2] <= -this.avatarHeadRadius) {
            final float try1 = this.avatarProjectionDistance / array[2];
            final float char1 = array[0] * try1 + this.appletWidth / 2;
            float n;
            if (c != null) {
                c.try = try1;
                c.char = char1;
                final float case1 = -array[1] * try1 + this.appletHeight / 2;
                c.case = case1;
                n = case1;
            }
            else {
                n = array[1] * try1 + this.appletHeight / 2;
            }
            int n2 = 0;
            if (char1 < 0.0f) {
                n2 |= 0x1;
            }
            else if (char1 > this.appletWidth) {
                n2 |= 0x2;
            }
            if (n < 0.0f) {
                n2 |= 0x4;
            }
            else if (n > this.appletHeight) {
                n2 |= 0x8;
            }
            if (array[2] < -this.avatarVisibilityRange) {
                n2 |= 0x20;
            }
            return n2;
        }
        return 16;
    }
    
    protected final float[] char() {
        final float n = this.boundViewpoint.aZ.ck[0];
        final float n2 = this.boundViewpoint.aZ.ck[1];
        final float n3 = this.boundViewpoint.aZ.ck[2];
        final float n4 = this.boundViewpoint.aD.ck[0];
        final float n5 = this.boundViewpoint.aD.ck[1];
        final float n6 = this.boundViewpoint.aD.ck[2];
        final float n7 = this.boundViewpoint.aD.ck[3];
        final float[] array = new float[12];
        k.a(n4, n5, n6, -n7, array);
        array[9] = -n * array[0] - n2 * array[3] - n3 * array[6];
        array[10] = -n * array[1] - n2 * array[4] - n3 * array[7];
        array[11] = -n * array[2] - n2 * array[5] - n3 * array[8];
        for (g g = (g)this.boundViewpoint.ax; g != null && g != this.rootNode; g = (g)g.ax) {
            g.try();
            k.if(g.bo, g.bo);
            k.do(g.bo, array, array);
        }
        return array;
    }
    
    public void a(final n n) {
        if (this.boundBackground == null) {
            this.boundBackground = (h)n;
        }
        if (this.boundBackground.aH != null) {
            this.renderer.a(this.boundBackground.aH);
            return;
        }
        final int n2 = (int)(this.boundBackground.aX.ck[0] * 255.0f) << 16 | (int)(this.boundBackground.aX.ck[1] * 255.0f) << 8 | (int)(this.boundBackground.aX.ck[2] * 255.0f);
        final int n3 = this.appletWidth * this.appletHeight;
        final int[] array = new int[n3];
        int n4 = n3;
        while (--n4 >= 0) {
            array[n4] = n2;
        }
        this.renderer.a(array);
    }
    
    private void new(final String s) {
        final h h = new h("defBack", 6, this);
        int[] a = null;
        if (this.backgroundImage != null) {
            if (this.imageHash.get(s) != null) {}
            a = this.a(s, this.backgroundImage, false);
            if (a != null) {
                this.imageHash.put(s, a);
            }
        }
        if (a == null) {
            final int rgb = this.appletParam_backcolor.getRGB();
            final int n = this.appletWidth * this.appletHeight;
            a = new int[n];
            int n2 = n;
            while (--n2 >= 0) {
                a[n2] = rgb;
            }
        }
        this.renderer.a(a);
    }
    
    private void if(final float[] array) {
        this.keyboardSelected = false;
        this.j();
        this.movementType = 6;
        this.viewAxisAngleVector[0] = array[0];
        this.viewAxisAngleVector[1] = array[1];
        this.viewAxisAngleVector[2] = array[2];
        this.viewAxisAngleVector[3] = array[3];
        System.arraycopy(this.rootNode.do("rotation").ck, 0, this.startSceneRotVector, 0, 4);
        this.viewRotVector = k.do(this.viewAxisAngleVector, this.startSceneRotVector);
        final int n = (int)(this.viewRotVector[3] * 180.0f / 3.1415927f);
        this.viewZRotVector = k.for(this.viewRotVector, this.viewAxisAngleVector, this.startSceneRotVector);
        final int n2 = (int)(this.viewZRotVector[3] * 180.0f / 3.1415927f);
        final float n3 = this.appletParam_rotationDuration * 100 / this.sleepingTime;
        if (n3 == 0.0f) {
            this.viewRotareCount = 0;
        }
        else {
            this.viewRotareCount = Math.max(n, n2);
            this.viewRotareNumber = 0.0f;
            this.angleIncrement = this.viewRotareCount / n3;
        }
        this.a(0, 0, 0);
    }
    
    private void k() {
        if (this.rootNode == null) {
            return;
        }
        final float[] array = new float[3];
        final float[] array2 = new float[3];
        this.rootNode.if(array, array2);
        this.rootNode.do("center").a(0, -1, array);
        this.rootNode.do("bboxSize").a(0, -1, array2);
        this.rootRotationOnStart = new float[4];
        final float[] ck = this.rootNode.do("rotation").ck;
        if (this.appletParam_startView != null) {
            if (this.appletParam_startView.equalsIgnoreCase("Front")) {
                System.arraycopy(this.front, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Right")) {
                System.arraycopy(this.right, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Top")) {
                System.arraycopy(this.top, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Back")) {
                System.arraycopy(this.back, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Left")) {
                System.arraycopy(this.left, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Bottom")) {
                System.arraycopy(this.bottom, 0, ck, 0, 4);
            }
            else if (this.appletParam_startView.equalsIgnoreCase("Isometric")) {
                System.arraycopy(this.isometric, 0, ck, 0, 4);
            }
        }
        System.arraycopy(ck, 0, this.rootRotationOnStart, 0, 4);
        this.rootNode.do("rotation").a(0, -1, ck);
        if (this.appletParam_startTransparency) {
            this.a(this.appletParam_transparency);
        }
        final float max = Math.max(Math.max(array2[0], array2[1]), array2[2]);
        final float min = Math.min(Math.min(array2[0], array2[1]), array2[2]);
        this.boundViewpoint.do("fieldOfView").ci = this.appletParam_cameraAngle;
        float n = max * 5.0f / 10.0f / (float)Math.tan(this.boundViewpoint.aW.ci / 2.0f) + (array[2] + min / 2.0f);
        if (n < this.appletParam_collisionRadius + max / 2.0f + array[2]) {
            n = this.appletParam_collisionRadius + max / 2.0f + array[2];
        }
        (this.avatarPositionOnStart = new float[3])[0] = array[0];
        this.avatarPositionOnStart[1] = array[1];
        this.avatarPositionOnStart[2] = n;
        this.boundViewpoint.do("position").a(0, -1, this.avatarPositionOnStart);
        this.avatarVisibilityRange = 6000.0f;
        this.avatarHeadRadius = 0.125f;
        this.avatarProjectionDistance = -Math.min(this.appletWidth >> 1, this.appletHeight >> 1) / (float)Math.tan(this.boundViewpoint.aW.ci / 2.0f);
    }
    
    private synchronized void a(int mouse_dx, int mouse_dy, final int n) {
        if (n == -1) {
            this.movementType = 0;
            return;
        }
        int n2 = (n != 506 && n != 403) ? 1 : 0;
        boolean b = mouse_dx == 0 && mouse_dy == 0;
        final float[] ck = this.boundViewpoint.do("position").ck;
        final float[] array = new float[3];
        final float n3 = this.appletParam_collisionRadius + Math.max(Math.max(this.rootNode.bs.ck[0], this.rootNode.bs.ck[1]), this.rootNode.bs.ck[2]) + this.rootNode.bk.ck[2];
        switch (this.movementType) {
            case 4: {
                if (mouse_dx == 0 || mouse_dy == 0) {
                    break;
                }
                if (n != 502) {
                    break;
                }
                final float n4 = ck[2] - this.rootNode.bk.ck[2];
                final float abs = Math.abs(this.avatarProjectionDistance / n4);
                float n5;
                if (Math.abs(mouse_dx) > Math.abs(mouse_dy)) {
                    n5 = Math.abs((float)mouse_dx) / this.appletWidth;
                }
                else {
                    n5 = Math.abs(mouse_dy) / this.appletHeight;
                }
                final float n6 = this.mouseFirstDown_x + mouse_dx / 2;
                final float n7 = this.mouseFirstDown_y + mouse_dy / 2;
                final float n8 = this.appletWidth / 2 - n6;
                final float n9 = this.appletHeight / 2 - n7;
                array[0] = ck[0] - n8 / abs;
                array[1] = ck[1] + n9 / abs;
                array[2] = n4 * n5 + this.rootNode.bk.ck[2];
                if (this.appletParam_collisionRadius >= 0.0f && array[2] < n3) {
                    System.out.print("You can get closer to object");
                    array[2] = n3;
                }
                this.boundViewpoint.do("position").a(0, -1, array);
                break;
            }
            case 2: {
                if (b) {
                    break;
                }
                if (n2 != 0) {
                    break;
                }
                final float abs2 = Math.abs(this.avatarProjectionDistance / Math.abs(this.rootNode.bk.ck[2] - ck[2]));
                array[0] = ck[0] + -mouse_dx / abs2;
                array[1] = ck[1] + mouse_dy / abs2;
                array[2] = ck[2];
                this.boundViewpoint.do("position").a(0, -1, array);
                break;
            }
            case 1: {
                if (b) {
                    break;
                }
                if (n2 != 0) {
                    break;
                }
                final float[] ck2 = this.rootNode.do("translation").ck;
                array[0] = ck[0];
                array[1] = ck[1];
                array[2] = ck[2] - mouse_dy * this.appletParam_zoomspeed;
                if (this.appletParam_collisionRadius >= 0.0f && array[2] < n3) {
                    array[2] = n3;
                }
                this.boundViewpoint.do("position").a(0, -1, array);
                break;
            }
            case 5: {
                if (mouse_dx == 0 && mouse_dy == 0) {
                    mouse_dx = this.mouse_dx;
                    mouse_dy = this.mouse_dy;
                }
                else {
                    this.mouse_dx = mouse_dx;
                    this.mouse_dy = mouse_dy;
                }
                n2 = 0;
                b = (mouse_dx == 0 && mouse_dy == 0);
            }
            case 3: {
                if (b) {
                    break;
                }
                if (n2 != 0) {
                    break;
                }
                this.a(mouse_dx, mouse_dy);
                break;
            }
            case 6: {
                if (this.viewRotareCount < 0) {
                    break;
                }
                this.byte();
                break;
            }
        }
    }
    
    private void byte() {
        final float viewRotareNumber = this.viewRotareNumber + this.angleIncrement;
        this.viewRotareNumber = viewRotareNumber;
        if (viewRotareNumber < this.viewRotareCount) {
            final float[] array = new float[12];
            final float[] array2 = new float[12];
            final float[] array3 = new float[12];
            k.a(this.startSceneRotVector[0], this.startSceneRotVector[1], this.startSceneRotVector[2], this.startSceneRotVector[3], array3);
            final float n = this.viewRotVector[3] * 180.0f / 3.1415927f / this.viewRotareCount;
            k.a(this.viewRotVector[0], this.viewRotVector[1], this.viewRotVector[2], (float)(3.141592653589793 * this.viewRotareNumber * n / 180.0), array);
            k.do(array3, array, array2);
            final float[] array4 = new float[12];
            final float[] array5 = new float[12];
            final float n2 = this.viewZRotVector[3] * 180.0f / 3.1415927f / this.viewRotareCount;
            k.a(this.viewRotVector[0], this.viewRotVector[1], this.viewRotVector[2], (float)(3.141592653589793 * (this.viewRotareCount - this.viewRotareNumber) * n / 180.0), array4);
            k.do(array2, array4, array5);
            final float[] array6 = new float[12];
            final float[] array7 = new float[12];
            k.a(this.viewZRotVector[0], this.viewZRotVector[1], this.viewZRotVector[2], (float)(3.141592653589793 * this.viewRotareNumber * n2 / 180.0), array6);
            k.do(array5, array6, array7);
            final float[] array8 = new float[12];
            k.if(array4, array8);
            final float[] array9 = new float[12];
            k.do(array7, array8, array9);
            this.rootNode.do("rotation").a(0, -1, k.if(array9));
        }
        else {
            this.rootNode.do("rotation").a(0, -1, this.viewAxisAngleVector);
            this.viewRotareCount = -1;
            this.movementType = 0;
        }
    }
    
    private void a(final int n, final int n2) {
        final float[] array = new float[12];
        final float[] array2 = new float[12];
        final float[] ck = this.rootNode.do("rotation").ck;
        k.a(ck[0], ck[1], ck[2], ck[3], array);
        k.do(array, k.a(this.RADIUS, -n, n2), array2);
        this.rootNode.do("rotation").a(0, -1, k.if(array2));
    }
    
    public synchronized void a(final float n) {
        if (this.rootNode != null && this.transparencyVector.size() == 0) {
            this.rootNode.a(this.transparencyVector);
        }
        this.rootNode.a(this.transparencyVector, n, 0);
    }
    
    public void try(final String doingText) {
        this.doingText = doingText;
    }
    
    public void if(final int n) {
        switch (n) {
            case 0: {
                this.if(this.front);
                break;
            }
            case 1: {
                this.if(this.right);
                break;
            }
            case 2: {
                this.if(this.top);
                break;
            }
            case 3: {
                this.if(this.back);
                break;
            }
            case 4: {
                this.if(this.left);
                break;
            }
            case 5: {
                this.if(this.bottom);
                break;
            }
            case 6: {
                this.if(this.isometric);
                break;
            }
            case 7: {
                this.a(0, 0, -1);
                this.movementType = 3;
                break;
            }
            case 8: {
                this.keyboardSelected = false;
                this.j();
                this.movementType = 2;
                break;
            }
            case 9: {
                this.keyboardSelected = false;
                this.j();
                this.movementType = 4;
                break;
            }
            case 10: {
                this.keyboardSelected = false;
                this.j();
                this.movementType = 1;
                break;
            }
            case 11: {
                if (!(this.appletParam_startTransparency = !this.appletParam_startTransparency)) {
                    this.a(10.0f);
                    break;
                }
                this.a(this.appletParam_transparency);
                break;
            }
            case 12: {
                this.keyboardSelected = false;
                if (this.movementType == 5) {
                    this.j();
                    break;
                }
                this.j();
                this.a(-5, this.movementType = 5, 0);
                break;
            }
            case 13: {
                this.j();
                synchronized (this.synchronizedObject) {
                    this.boundViewpoint.do("position").a(0, -1, this.avatarPositionOnStart);
                    this.rootNode.do("rotation").a(0, -1, this.rootRotationOnStart);
                }
                // monitorexit(this.synchronizedObject)
                break;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.mouseDownEvent = true;
            this.mouse_x = mouseEvent.getX();
            this.mouse_y = mouseEvent.getY();
            this.mouseFirstDown_x = this.mouse_x;
            this.mouseFirstDown_y = this.mouse_y;
        }
        else if (this.help != null && (this.getParameter("helpWindowEnabled") == null || this.getParameter("helpWindowEnabled").equalsIgnoreCase("true"))) {
            this.help.setVisible(!this.help.isVisible());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.a(mouseEvent);
            this.mouseDownEvent = false;
            final int n = mouseEvent.getX() - this.mouseFirstDown_x;
            final int n2 = mouseEvent.getY() - this.mouseFirstDown_y;
            if (this.movementType != 6) {
                try {
                    this.a(n, n2, mouseEvent.getID());
                }
                catch (NullPointerException ex) {}
            }
        }
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        final int movementType = this.movementType;
        this.movementType = 1;
        this.a(0, mouseWheelEvent.getWheelRotation() * -30, 506);
        this.movementType = movementType;
    }
    
    private void a(final MouseEvent mouseEvent) {
        if (this.movementType == 0 || this.keyboardSelected) {
            this.movementType = 3;
        }
        if (mouseEvent.isControlDown()) {
            this.keyboardSelected = true;
            this.movementType = 1;
        }
        else if (mouseEvent.isShiftDown()) {
            this.keyboardSelected = true;
            this.movementType = 2;
        }
    }
    
    private void void() {
        final String parameter = this.getParameter("tooltipBackground");
        final String parameter2 = this.getParameter("tooltipForeground");
        final String parameter3 = this.getParameter("tooltipBorder");
        if (parameter != null && !parameter.equals("")) {
            UIManager.put("ToolTip.background", Color.decode(parameter));
        }
        if (parameter2 != null && !parameter2.equals("")) {
            UIManager.put("ToolTip.foreground", Color.decode(parameter2));
        }
        if (parameter3 != null && !parameter3.equals("")) {
            UIManager.put("ToolTip.border", BorderFactory.createLineBorder(Color.decode(parameter3)));
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.a(mouseEvent);
            if (this.movementType == 4) {
                final Graphics graphics = this.getGraphics();
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawRect(this.mouseFirstDown_x, this.mouseFirstDown_y, mouseEvent.getX() - this.mouseFirstDown_x, mouseEvent.getY() - this.mouseFirstDown_y);
            }
            else if (this.movementType != 6) {
                try {
                    this.a(mouseEvent.getX() - this.mouse_x, mouseEvent.getY() - this.mouse_y, mouseEvent.getID());
                }
                catch (NullPointerException ex) {}
            }
            this.mouse_x = mouseEvent.getX();
            this.mouse_y = mouseEvent.getY();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case '2': {
                this.if(this.front);
                break;
            }
            case '3': {
                this.if(this.right);
                break;
            }
            case '4': {
                this.if(this.top);
                break;
            }
            case '5': {
                this.if(this.back);
                break;
            }
            case '6': {
                this.if(this.left);
                break;
            }
            case '7': {
                this.if(this.bottom);
                break;
            }
            case '8': {
                this.if(this.isometric);
                break;
            }
            case '9': {
                if (!(this.appletParam_startTransparency = !this.appletParam_startTransparency)) {
                    this.a(10.0f);
                }
                else {
                    this.a(this.appletParam_transparency);
                }
                final Stv_Button a = this.motionBar.a(11);
                a.toggle_on = !a.toggle_on;
                if (a.toggle_on) {
                    a.status = 0;
                }
                else {
                    a.status = 2;
                }
                a.repaint();
                break;
            }
            case '0': {
                this.keyboardSelected = false;
                final Stv_Button a2 = this.motionBar.a(12);
                if (this.movementType == 5) {
                    this.j();
                    a2.status = 2;
                    a2.repaint();
                    final Stv_Button a3 = this.motionBar.a(7);
                    a3.status = 0;
                    a3.repaint();
                    break;
                }
                this.j();
                this.movementType = 5;
                a2.group.if.status = 2;
                a2.group.if.repaint();
                a2.status = 0;
                a2.repaint();
                this.a(-5, 5, 0);
                break;
            }
            case '\u001b': {
                if (this.help != null && this.help.isVisible()) {
                    this.help.setVisible(false);
                    break;
                }
                break;
            }
            default: {
                this.if(this.isometric);
                break;
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void l() {
        final String parameter = this.getParameter("viewIso");
        final String parameter2 = this.getParameter("viewFront");
        final String parameter3 = this.getParameter("viewBack");
        final String parameter4 = this.getParameter("viewTop");
        final String parameter5 = this.getParameter("viewBottom");
        final String parameter6 = this.getParameter("viewLeft");
        final String parameter7 = this.getParameter("viewRight");
        String s = "";
        try {
            s = "viewIso";
            if (parameter != null && !parameter.equals("")) {
                this.isometric = for(parameter);
            }
            s = "viewFront";
            if (parameter2 != null && !parameter2.equals("")) {
                this.front = for(parameter2);
            }
            s = "viewBack";
            if (parameter3 != null && !parameter3.equals("")) {
                this.back = for(parameter3);
            }
            s = "viewTop";
            if (parameter4 != null && !parameter4.equals("")) {
                this.top = for(parameter4);
            }
            s = "viewBottom";
            if (parameter5 != null && !parameter5.equals("")) {
                this.bottom = for(parameter5);
            }
            s = "viewLeft";
            if (parameter6 != null && !parameter6.equals("")) {
                this.left = for(parameter6);
            }
            s = "viewRight";
            if (parameter7 != null && !parameter7.equals("")) {
                this.right = for(parameter7);
            }
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Could not resolve view definition: " + s);
        }
    }
    
    private static float[] for(final String s) {
        final float[] array = new float[4];
        final String[] split = s.split(";");
        for (int i = 0; i < split.length; ++i) {
            array[i] = Float.parseFloat(split[i]);
        }
        return array;
    }
    
    public void a(final float[] array) {
        this.if(array);
    }
}
