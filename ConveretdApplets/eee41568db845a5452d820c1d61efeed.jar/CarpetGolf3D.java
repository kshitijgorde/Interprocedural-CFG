import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.util.Random;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CarpetGolf3D extends Applet implements Runnable
{
    Color m_colorPlayfield;
    final Color m_colorWindow;
    int[] m_nKeyBuffer;
    Thread m_threadAnim;
    boolean m_bInitialized;
    boolean m_bPracticeMode;
    boolean m_bPrev;
    boolean m_bNext;
    boolean m_bReplay;
    String m_stringError;
    String m_stringException;
    String m_stringParamName;
    boolean m_bMouseInWindow;
    boolean m_bButtonDown;
    boolean m_bClick;
    int m_nMouseX;
    int m_nMouseY;
    final int PLAYFIELD_LEFT_MARGIN = 15;
    final int PLAYFIELD_RIGHT_MARGIN = 100;
    final int PLAYFIELD_Y_MARGIN = 15;
    public static int m_nPlayfieldWidth;
    public static int m_nPlayfieldHeight;
    int m_nPlayfieldX;
    int m_nPlayfieldY;
    final int SCORECARD_FONT_SIZE = 14;
    final Color SCORECARD_BG_COLOR;
    final Color SCORECARD_HEADING_COLOR;
    final Color SCORECARD_HOLE_COLOR;
    final Color SCORECARD_PAR_COLOR;
    final Color SCORECARD_STROKES_COLOR;
    final Color SCORECARD_LINE_COLOR;
    final int SCORECARD_LINE_MARGIN = 5;
    final long SCORECARD_SCROLL_TIME = 1999L;
    int[] m_nScorecardColumnX;
    final int m_nScorecardRowY = 35;
    final int SCORECARD_HOLE_X = 30;
    final int SCORECARD_PAR_X = 62;
    final int SCORECARD_STROKES_X = 120;
    Image m_imageScorecard;
    Image m_imageScrollBuffer;
    Image m_imagePrevHole;
    boolean m_bDisplayScorecard;
    long m_lScorecardScrollEnd;
    MediaTracker m_mediaTracker;
    Image m_imageLoading;
    Image m_imageHoleParStrokes;
    int m_nHPScaptionX;
    final int HPS_CAPTION_Y = 15;
    final int HOLE_X = 42;
    final int HOLE_Y = 4;
    final int PAR_X = 42;
    final int PAR_Y = 24;
    final int STROKES_X = 42;
    final int STROKES_Y = 44;
    Image m_imageDigits;
    Image[] m_imageDigit;
    final int DIGIT_WIDTH = 7;
    final int DIGIT_HEIGHT = 8;
    Joystick m_joystick;
    final int JOYSTICK_Y = 150;
    boolean m_bZoomIn;
    boolean m_bZoomOut;
    boolean m_bXplus;
    boolean m_bXminus;
    boolean m_bYplus;
    boolean m_bYminus;
    boolean m_bZplus;
    boolean m_bZminus;
    boolean m_bPitchUp;
    boolean m_bPitchDown;
    Image m_imageSwingButtonOff;
    Image m_imageSwingButtonOn;
    Image m_imageSwingButtonDown;
    int m_nSwingButtonXmin;
    int m_nSwingButtonXmax;
    int m_nSwingButtonYmin;
    int m_nSwingButtonYmax;
    final int SWING_BUTTON_OFF = 0;
    final int SWING_BUTTON_ON = 1;
    final int SWING_BUTTON_DOWN = 2;
    final int SWING_BUTTON_Y = 265;
    int m_nSwingButtonState;
    int m_nSwingButtonNewState;
    long m_lSwingStartTime;
    Image m_imageAimingBar;
    int m_nAimingBarX;
    int m_nAimingBarY;
    int m_nAimingBarXsize;
    int m_nAimingBarYsize;
    double m_dAimingFactor;
    boolean m_bRedrawAimingBar;
    final int AIMING_BAR_X_MARGIN = 3;
    final int AIMING_BAR_Y_MARGIN = 3;
    final int AIMING_BAR_CAPTION_HEIGHT = 14;
    final int AIMING_BAR_Y = 232;
    final long AIMING_BAR_TIME = 3000L;
    final Color AIMING_BAR_COLOR;
    Arrow m_arrow;
    AudioClip m_acStroke;
    AudioClip m_acRebound;
    AudioClip m_acWaterhaz;
    AudioClip m_acHoleout;
    AudioClip m_acHouse;
    final int m_nHistoryFrames = 10;
    int m_nFrameNumber;
    long[] m_lTime;
    long m_lLatestTime;
    double m_dSecondsPerFrame;
    final int NO_COLLISION = 0;
    final int BARRIER_COLLISION = 1;
    final int WATERHAZARD_COLLISION = 2;
    final int CUP_COLLISION = 3;
    final int HOUSE_COLLISION = 4;
    final int DUCT_COLLISION = 5;
    final int WINDMILL_COLLISION = 6;
    final int SLIDINGBRIDGE_COLLISION = 7;
    final int MAX_HOLES = 18;
    int m_nHoles;
    int m_ndxCurrHole;
    Hole3D[] m_hole;
    Auxiliary m_auxiliaryChain;
    Barrier3D m_barrierChain;
    Cup3D m_cup;
    Duct3D m_ductChain;
    Fairway3D m_fairwayChain;
    Golfball3D m_golfball;
    House3D m_houseChain;
    SlidingBridge m_slidingbridgeChain;
    Slope3D m_slopeChain;
    Tee3D m_tee;
    VantagePoint m_vantagepointChain;
    WaterHazard3D m_waterhazardChain;
    Windmill m_windmillChain;
    final int MAX_3D_OBJECTS = 200;
    int m_nObjects;
    cgObject[] m_object;
    cgViewpoint m_viewpoint;
    final double DOLLY_SPEED = 5.0;
    final double PAN_SPEED = 0.6;
    public static final double TESSELLATION_GRID = 5.0;
    
    public static double[] tessellateRange(final double n, final double n2) {
        int n3 = 2;
        final double n4 = n % 5.0;
        for (double n5 = n + (5.0 - n4); n5 < n2; n5 += 5.0, ++n3) {}
        final double[] array = new double[n3];
        array[0] = n;
        double n6;
        int n7;
        for (n6 = n + (5.0 - n4), n7 = 1; n6 < n2; n6 += 5.0, ++n7) {
            array[n7] = n6;
        }
        array[n7] = n2;
        return array;
    }
    
    private void DisplayHoleAndPar(final Graphics graphics) {
        this.DisplayValue(graphics, 42, 4, this.m_ndxCurrHole + 1);
        this.DisplayValue(graphics, 42, 24, this.m_hole[this.m_ndxCurrHole].m_nPar);
    }
    
    public void start() {
        if (this.m_threadAnim == null) {
            (this.m_threadAnim = new Thread(this)).start();
        }
    }
    
    private void DisplayValue(final Graphics graphics, final int n, final int n2, final int n3) {
        Graphics graphics2;
        if (graphics == null) {
            graphics2 = this.getGraphics();
        }
        else {
            graphics2 = graphics;
        }
        final int n4 = n3 / 10 % 10;
        if (n3 >= 10) {
            graphics2.drawImage(this.m_imageDigit[n4], this.m_nHPScaptionX + n, 15 + n2, null);
        }
        else {
            graphics2.setColor(Color.black);
            graphics2.fillRect(this.m_nHPScaptionX + n, 15 + n2, 7, 8);
        }
        graphics2.drawImage(this.m_imageDigit[n3 % 10], this.m_nHPScaptionX + n + 7, 15 + n2, null);
    }
    
    public void stop() {
        if (this.m_threadAnim != null) {
            this.m_threadAnim.stop();
            this.m_threadAnim = null;
        }
    }
    
    public boolean keyUp(final Event event, final int n) {
        switch (n) {
            case 43:
            case 61: {
                this.m_bZoomIn = false;
                break;
            }
            case 45: {
                this.m_bZoomOut = false;
                break;
            }
            case 88:
            case 120: {
                this.m_bXminus = false;
                this.m_bXplus = false;
                break;
            }
            case 89:
            case 121: {
                this.m_bYminus = false;
                this.m_bYplus = false;
                break;
            }
            case 90:
            case 122: {
                this.m_bZminus = false;
                this.m_bZplus = false;
                break;
            }
            case 84:
            case 116: {
                this.m_bPitchDown = false;
                this.m_bPitchUp = false;
                break;
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.m_bMouseInWindow = false;
        this.m_bButtonDown = false;
        this.m_bClick = false;
        return true;
    }
    
    public CarpetGolf3D() {
        this.m_colorPlayfield = new Color(12632256);
        this.m_colorWindow = new Color(76, 76, 76);
        this.m_nKeyBuffer = new int[4];
        this.SCORECARD_BG_COLOR = new Color(16777164);
        this.SCORECARD_HEADING_COLOR = Color.black;
        this.SCORECARD_HOLE_COLOR = Color.black;
        this.SCORECARD_PAR_COLOR = Color.black;
        this.SCORECARD_STROKES_COLOR = Color.black;
        this.SCORECARD_LINE_COLOR = Color.black;
        this.m_nScorecardColumnX = new int[] { -138, 12 };
        this.m_mediaTracker = new MediaTracker(this);
        this.m_imageDigit = new Image[10];
        this.AIMING_BAR_COLOR = Color.red;
        this.m_lTime = new long[10];
        this.m_hole = new Hole3D[18];
        this.m_object = new cgObject[200];
    }
    
    public boolean mouseMove(final Event event, final int nMouseX, final int nMouseY) {
        this.m_nMouseX = nMouseX;
        this.m_nMouseY = nMouseY;
        this.m_bMouseInWindow = true;
        this.m_bButtonDown = false;
        return true;
    }
    
    private void endHole() {
        this.UpdateScorecard(this.m_ndxCurrHole + 1);
        this.m_bDisplayScorecard = true;
        this.m_imagePrevHole.getGraphics().drawImage(this.m_viewpoint.m_imageViewplane, 0, 0, null);
        if (this.m_ndxCurrHole < this.m_nHoles - 1) {
            this.SelectHole(this.m_ndxCurrHole + 1);
        }
        else {
            this.SelectHole(0);
        }
        this.m_lScorecardScrollEnd = System.currentTimeMillis() + 1999L;
    }
    
    private void DisplayStrokes(final Graphics graphics) {
        this.DisplayValue(graphics, 42, 44, this.m_golfball.m_nStrokes);
    }
    
    private void SelectHole(final int ndxCurrHole) {
        if (ndxCurrHole >= this.m_nHoles) {
            return;
        }
        this.m_ndxCurrHole = ndxCurrHole;
        this.m_auxiliaryChain = this.m_hole[ndxCurrHole].m_auxiliaryChain;
        this.m_barrierChain = this.m_hole[ndxCurrHole].m_barrierChain;
        this.m_cup = this.m_hole[ndxCurrHole].m_cup;
        this.m_ductChain = this.m_hole[ndxCurrHole].m_ductChain;
        this.m_fairwayChain = this.m_hole[ndxCurrHole].m_fairwayChain;
        this.m_houseChain = this.m_hole[ndxCurrHole].m_houseChain;
        this.m_golfball = this.m_hole[ndxCurrHole].m_golfball;
        this.m_slidingbridgeChain = this.m_hole[ndxCurrHole].m_slidingbridgeChain;
        this.m_slopeChain = this.m_hole[ndxCurrHole].m_slopeChain;
        this.m_tee = this.m_hole[ndxCurrHole].m_tee;
        this.m_vantagepointChain = this.m_hole[ndxCurrHole].m_vantagepointChain;
        this.m_waterhazardChain = this.m_hole[ndxCurrHole].m_waterhazardChain;
        this.m_windmillChain = this.m_hole[ndxCurrHole].m_windmillChain;
        this.m_golfball.Reset();
        if (this.m_bInitialized) {
            this.DisplayHoleAndPar(null);
            this.DisplayStrokes(null);
        }
        this.m_nObjects = 0;
        for (Auxiliary auxiliary = this.m_auxiliaryChain; auxiliary != null; auxiliary = auxiliary.m_auxiliaryNext) {
            this.m_object[this.m_nObjects] = auxiliary.m_object;
            ++this.m_nObjects;
        }
        for (Barrier3D barrier3D = this.m_barrierChain; barrier3D != null; barrier3D = barrier3D.m_barrierNext) {
            this.m_object[this.m_nObjects] = barrier3D.m_object;
            ++this.m_nObjects;
        }
        if (this.m_cup != null) {
            this.m_object[this.m_nObjects] = this.m_cup.m_object;
            ++this.m_nObjects;
        }
        for (Duct3D duct3D = this.m_ductChain; duct3D != null; duct3D = duct3D.m_ductNext) {
            this.m_object[this.m_nObjects] = duct3D.m_objectEntrance;
            ++this.m_nObjects;
            this.m_object[this.m_nObjects] = duct3D.m_objectExit;
            ++this.m_nObjects;
        }
        for (Fairway3D fairway3D = this.m_fairwayChain; fairway3D != null; fairway3D = fairway3D.m_fairwayNext) {
            this.m_object[this.m_nObjects] = fairway3D.m_object;
            ++this.m_nObjects;
        }
        if (this.m_houseChain != null) {
            this.m_houseChain.transformFlagChain(System.currentTimeMillis());
            for (House3D house3D = this.m_houseChain; house3D != null; house3D = house3D.m_houseNext) {
                this.m_object[this.m_nObjects] = house3D.m_object;
                ++this.m_nObjects;
                this.m_object[this.m_nObjects] = house3D.m_objectFlag1;
                ++this.m_nObjects;
            }
        }
        if (this.m_slidingbridgeChain != null) {
            this.m_slidingbridgeChain.transformBridgeChain(System.currentTimeMillis());
            for (SlidingBridge slidingBridge = this.m_slidingbridgeChain; slidingBridge != null; slidingBridge = slidingBridge.m_slidingbridgeNext) {
                this.m_object[this.m_nObjects] = slidingBridge.m_objectPool;
                ++this.m_nObjects;
                this.m_object[this.m_nObjects] = slidingBridge.m_objectBridge1;
                ++this.m_nObjects;
            }
        }
        for (Slope3D slope3D = this.m_slopeChain; slope3D != null; slope3D = slope3D.m_slopeNext) {
            this.m_object[this.m_nObjects] = slope3D.m_object;
            ++this.m_nObjects;
        }
        if (this.m_tee != null) {
            this.m_object[this.m_nObjects] = this.m_tee.m_object;
            ++this.m_nObjects;
        }
        for (WaterHazard3D waterHazard3D = this.m_waterhazardChain; waterHazard3D != null; waterHazard3D = waterHazard3D.m_waterhazardNext) {
            this.m_object[this.m_nObjects] = waterHazard3D.m_object;
            ++this.m_nObjects;
        }
        if (this.m_windmillChain != null) {
            this.m_windmillChain.transformBladeChain(System.currentTimeMillis());
            for (Windmill windmill = this.m_windmillChain; windmill != null; windmill = windmill.m_windmillNext) {
                this.m_object[this.m_nObjects] = windmill.m_object;
                ++this.m_nObjects;
                this.m_object[this.m_nObjects] = windmill.m_objectBlades1;
                ++this.m_nObjects;
            }
        }
        this.m_bClick = false;
        this.m_arrow.m_bShow = false;
        this.m_object[this.m_nObjects] = this.m_arrow.m_object;
        ++this.m_nObjects;
        if (this.m_vantagepointChain != null && this.m_golfball != null) {
            this.m_vantagepointChain.set(this.m_viewpoint, this.m_golfball);
            this.m_viewpoint.setFratio(1.0);
            this.m_viewpoint.render(this.m_object, this.m_nObjects);
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.m_nKeyBuffer[3] = this.m_nKeyBuffer[2];
        this.m_nKeyBuffer[2] = this.m_nKeyBuffer[1];
        this.m_nKeyBuffer[1] = this.m_nKeyBuffer[0];
        this.m_nKeyBuffer[0] = n;
        if (this.m_nKeyBuffer[3] == 73 && this.m_nKeyBuffer[2] == 78 && this.m_nKeyBuffer[1] == 70 && this.m_nKeyBuffer[0] == 79) {
            this.stop();
            final Graphics graphics = this.getGraphics();
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final Dimension size = this.size();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.black);
            graphics.drawString("CarpetGolf3D", 20, fontMetrics.getHeight());
            graphics.drawString("(c) 1997 Edward R. Hobbs", 20, 2 * fontMetrics.getHeight());
            graphics.drawString("edhobbs@aol.com", 20, 3 * fontMetrics.getHeight());
            graphics.drawString("All rights reserved.", 20, 4 * fontMetrics.getHeight());
        }
        switch (n) {
            case 43:
            case 61: {
                this.m_bZoomIn = true;
                break;
            }
            case 45: {
                this.m_bZoomOut = true;
                break;
            }
            case 120: {
                this.m_bXminus = true;
                break;
            }
            case 88: {
                this.m_bXplus = true;
                break;
            }
            case 121: {
                this.m_bYminus = true;
                break;
            }
            case 89: {
                this.m_bYplus = true;
                break;
            }
            case 122: {
                this.m_bZminus = true;
                break;
            }
            case 90: {
                this.m_bZplus = true;
                break;
            }
            case 116: {
                this.m_bPitchDown = true;
                break;
            }
            case 84: {
                this.m_bPitchUp = true;
                break;
            }
            case 80:
            case 112: {
                if (this.m_bPracticeMode) {
                    this.m_bPrev = true;
                    break;
                }
                break;
            }
            case 78:
            case 110: {
                if (this.m_bPracticeMode) {
                    this.m_bNext = true;
                    break;
                }
                break;
            }
            case 82:
            case 114: {
                if (this.m_bPracticeMode) {
                    this.m_bReplay = true;
                    break;
                }
                break;
            }
            case 86:
            case 118: {
                final cgMatrix cgMatrix = new cgMatrix();
                cgMatrix.unitInverse(this.m_viewpoint.m_matrix);
                final cgVector cgVector = new cgVector(cgMatrix.m_dXz, cgMatrix.m_dYz, cgMatrix.m_dZz);
                if (cgVector.m_dZ < 0.0) {
                    final double n2 = this.m_viewpoint.m_vector.m_dZ / Math.abs(cgVector.m_dZ);
                    this.showStatus("( " + this.roundoff(this.m_viewpoint.m_vector.m_dX) + ", " + this.roundoff(this.m_viewpoint.m_vector.m_dY) + ", " + this.roundoff(this.m_viewpoint.m_vector.m_dZ) + ", " + this.roundoff(this.m_viewpoint.m_vector.m_dX + cgVector.m_dX * n2) + ", " + this.roundoff(this.m_viewpoint.m_vector.m_dY + cgVector.m_dY * n2) + " )");
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    private double roundoff(final double n) {
        return Math.round(n * 10.0) / 10.0;
    }
    
    public boolean mouseDown(final Event event, final int nMouseX, final int nMouseY) {
        this.m_nMouseX = nMouseX;
        this.m_nMouseY = nMouseY;
        this.m_bMouseInWindow = true;
        this.m_bButtonDown = true;
        return this.m_bClick = true;
    }
    
    public boolean mouseEnter(final Event event, final int nMouseX, final int nMouseY) {
        this.m_nMouseX = nMouseX;
        this.m_nMouseY = nMouseY;
        return this.m_bMouseInWindow = true;
    }
    
    public boolean mouseDrag(final Event event, final int nMouseX, final int nMouseY) {
        this.m_nMouseX = nMouseX;
        this.m_nMouseY = nMouseY;
        this.m_bMouseInWindow = true;
        return this.m_bButtonDown = true;
    }
    
    public String getAppletInfo() {
        return "Name:   CarpetGolf3D\r\n" + "Author: Edward R. Hobbs (edhobbs@aol.com)\r\n" + "\r\n" + "Copyright (c) 1997 Edward R. Hobbs\r\n" + "All rights reserved.";
    }
    
    private boolean GolfballPhysics() {
        Barrier3D barrier3D = null;
        Duct3D duct3D = null;
        House3D house3D = null;
        SlidingBridge slidingBridge = null;
        WaterHazard3D waterHazard3D = null;
        Windmill windmill = null;
        boolean b = false;
        if (this.m_golfball.m_slidingbridge != null) {
            if (!this.m_golfball.m_slidingbridge.golfballPhysics(this.m_golfball, this.m_lLatestTime, this.m_dSecondsPerFrame)) {
                return false;
            }
            if (this.m_golfball.m_bWaterBall) {
                this.m_acWaterhaz.play();
                final Golfball3D golfball = this.m_golfball;
                ++golfball.m_nStrokes;
                this.m_golfball.m_bWaterBall = false;
                this.DisplayStrokes(null);
                b = true;
            }
        }
        if (this.m_golfball.m_house != null && !this.m_golfball.m_house.GolfballPhysics(this.m_golfball, this.m_lLatestTime)) {
            return false;
        }
        if (this.m_golfball.m_windmill != null && !this.m_golfball.m_windmill.golfballPhysics(this.m_golfball, this.m_lLatestTime)) {
            return false;
        }
        if (this.m_golfball.m_duct != null && !this.m_golfball.m_duct.GolfballPhysics(this.m_golfball, this.m_lLatestTime)) {
            return false;
        }
        if (this.m_golfball.m_cup != null) {
            if (this.m_golfball.m_cup.GolfballPhysics(this.m_golfball, this.m_lLatestTime)) {
                this.endHole();
            }
            return false;
        }
        boolean acceleration = false;
        for (Slope3D slope3D = this.m_slopeChain; slope3D != null && !(acceleration = slope3D.acceleration(this.m_golfball, this.m_dSecondsPerFrame)); slope3D = slope3D.m_slopeNext) {}
        if (acceleration) {
            this.m_golfball.m_dFinalVelocity = this.m_golfball.m_dVelocity;
        }
        else {
            this.m_golfball.m_dFinalVelocity = this.m_golfball.m_dVelocity - this.m_dSecondsPerFrame * 1.6;
        }
        if (this.m_golfball.m_dFinalVelocity < 0.0) {
            this.m_golfball.m_dFinalVelocity = 0.0;
        }
        if (acceleration && this.m_golfball.m_dFinalVelocity == 0.0) {
            this.m_golfball.m_dFinalVelocity = 0.01;
        }
        this.m_golfball.m_dDistance = this.m_dSecondsPerFrame * (this.m_golfball.m_dVelocity + this.m_golfball.m_dFinalVelocity) / 2.0;
        this.m_golfball.m_dX1 = this.m_golfball.m_dX0 + Math.cos(this.m_golfball.m_dAngle) * this.m_golfball.m_dDistance;
        this.m_golfball.m_dY1 = this.m_golfball.m_dY0 + Math.sin(this.m_golfball.m_dAngle) * this.m_golfball.m_dDistance;
        this.m_golfball.m_dXmin = Math.min(this.m_golfball.m_dX0, this.m_golfball.m_dX1);
        this.m_golfball.m_dYmin = Math.min(this.m_golfball.m_dY0, this.m_golfball.m_dY1);
        this.m_golfball.m_dXmax = Math.max(this.m_golfball.m_dX0, this.m_golfball.m_dX1);
        this.m_golfball.m_dYmax = Math.max(this.m_golfball.m_dY0, this.m_golfball.m_dY1);
        while (this.m_golfball.m_dDistance > 0.0) {
            Barrier3D barrier3D2 = this.m_barrierChain;
            double n = -1.0;
            while (barrier3D2 != null) {
                final double collisionAnalysis = barrier3D2.CollisionAnalysis(this.m_golfball, false);
                if (collisionAnalysis >= 0.0 && (n < 0.0 || collisionAnalysis < n)) {
                    n = collisionAnalysis;
                    barrier3D = barrier3D2;
                }
                barrier3D2 = barrier3D2.m_barrierNext;
            }
            Duct3D duct3D2 = this.m_ductChain;
            double n2 = -1.0;
            while (duct3D2 != null) {
                final double collisionAnalysis2 = duct3D2.CollisionAnalysis(this.m_golfball, false, this.m_lLatestTime);
                if (collisionAnalysis2 >= 0.0 && (n2 < 0.0 || collisionAnalysis2 < n2)) {
                    n2 = collisionAnalysis2;
                    duct3D = duct3D2;
                }
                duct3D2 = duct3D2.m_ductNext;
            }
            House3D house3D2 = this.m_houseChain;
            double n3 = -1.0;
            while (house3D2 != null) {
                final double collisionAnalysis3 = house3D2.CollisionAnalysis(this.m_golfball, false, this.m_lLatestTime);
                if (collisionAnalysis3 >= 0.0 && (n3 < 0.0 || collisionAnalysis3 < n3)) {
                    n3 = collisionAnalysis3;
                    house3D = house3D2;
                }
                house3D2 = house3D2.m_houseNext;
            }
            SlidingBridge slidingBridge2 = this.m_slidingbridgeChain;
            double n4 = -1.0;
            while (slidingBridge2 != null) {
                final double collisionAnalysis4 = slidingBridge2.collisionAnalysis(this.m_golfball, false, this.m_lLatestTime);
                if (collisionAnalysis4 >= 0.0 && (n4 < 0.0 || collisionAnalysis4 < n4)) {
                    n4 = collisionAnalysis4;
                    slidingBridge = slidingBridge2;
                }
                slidingBridge2 = slidingBridge2.m_slidingbridgeNext;
            }
            WaterHazard3D waterHazard3D2 = this.m_waterhazardChain;
            double n5 = -1.0;
            while (waterHazard3D2 != null) {
                final double collisionAnalysis5 = waterHazard3D2.CollisionAnalysis(this.m_golfball, false);
                if (collisionAnalysis5 >= 0.0 && (n5 < 0.0 || collisionAnalysis5 < n5)) {
                    n5 = collisionAnalysis5;
                    waterHazard3D = waterHazard3D2;
                }
                waterHazard3D2 = waterHazard3D2.m_waterhazardNext;
            }
            Windmill windmill2 = this.m_windmillChain;
            double n6 = -1.0;
            while (windmill2 != null) {
                final double collisionAnalysis6 = windmill2.collisionAnalysis(this.m_golfball, false, this.m_lLatestTime);
                if (collisionAnalysis6 >= 0.0 && (n6 < 0.0 || collisionAnalysis6 < n6)) {
                    n6 = collisionAnalysis6;
                    windmill = windmill2;
                }
                windmill2 = windmill2.m_windmillNext;
            }
            final double collisionAnalysis7 = this.m_cup.CollisionAnalysis(this.m_golfball, false, this.m_lLatestTime);
            int n7 = 0;
            double n8 = 999999.0;
            if (n >= 0.0 && n < n8) {
                n8 = n;
                n7 = 1;
            }
            if (n2 >= 0.0 && n2 < n8) {
                n8 = n2;
                n7 = 5;
            }
            if (n3 >= 0.0 && n3 < n8) {
                n8 = n3;
                n7 = 4;
            }
            if (n4 >= 0.0 && n4 < n8) {
                n8 = n4;
                n7 = 7;
            }
            if (n5 >= 0.0 && n5 < n8) {
                n8 = n5;
                n7 = 2;
            }
            if (n6 >= 0.0 && n6 < n8) {
                n8 = n6;
                n7 = 6;
            }
            if (collisionAnalysis7 >= 0.0 && collisionAnalysis7 < n8) {
                n7 = 3;
            }
            switch (n7) {
                case 1: {
                    barrier3D.CollisionAnalysis(this.m_golfball, true);
                    this.m_acRebound.play();
                    break;
                }
                case 5: {
                    duct3D.CollisionAnalysis(this.m_golfball, true, this.m_lLatestTime);
                    if (this.m_golfball.m_duct != null) {
                        this.m_acHouse.play();
                        break;
                    }
                    break;
                }
                case 4: {
                    house3D.CollisionAnalysis(this.m_golfball, true, this.m_lLatestTime);
                    if (this.m_golfball.m_house == null) {
                        this.m_acRebound.play();
                        break;
                    }
                    this.m_acHouse.play();
                    break;
                }
                case 7: {
                    slidingBridge.collisionAnalysis(this.m_golfball, true, this.m_lLatestTime);
                    if (this.m_golfball.m_bWaterBall) {
                        this.m_acWaterhaz.play();
                        final Golfball3D golfball2 = this.m_golfball;
                        ++golfball2.m_nStrokes;
                        this.m_golfball.m_bWaterBall = false;
                        this.DisplayStrokes(null);
                        b = true;
                        break;
                    }
                    break;
                }
                case 2: {
                    waterHazard3D.CollisionAnalysis(this.m_golfball, true);
                    this.m_acWaterhaz.play();
                    final Golfball3D golfball3 = this.m_golfball;
                    ++golfball3.m_nStrokes;
                    this.m_golfball.m_bWaterBall = false;
                    this.DisplayStrokes(null);
                    b = true;
                    break;
                }
                case 6: {
                    windmill.collisionAnalysis(this.m_golfball, true, this.m_lLatestTime);
                    if (this.m_golfball.m_windmill == null) {
                        this.m_acRebound.play();
                        break;
                    }
                    this.m_acHouse.play();
                    break;
                }
                case 3: {
                    this.m_cup.CollisionAnalysis(this.m_golfball, true, this.m_lLatestTime);
                    if (this.m_golfball.m_cup != null) {
                        this.m_acHoleout.play();
                        this.m_golfball.m_bHoledOut = true;
                        break;
                    }
                    break;
                }
            }
            if (n7 != 0 && this.m_golfball.m_cup == null && this.m_golfball.m_house == null && this.m_golfball.m_duct == null && this.m_golfball.m_windmill == null && this.m_golfball.m_slidingbridge == null) {
                continue;
            }
            break;
        }
        if (this.m_golfball.m_house == null && this.m_golfball.m_windmill == null && this.m_golfball.m_slidingbridge == null && this.m_golfball.m_duct == null && this.m_golfball.m_cup == null) {
            this.m_golfball.m_dX0 = this.m_golfball.m_dX1;
            this.m_golfball.m_dY0 = this.m_golfball.m_dY1;
            this.m_golfball.m_dVelocity = this.m_golfball.m_dFinalVelocity;
            if (this.m_golfball.m_dVelocity <= 0.0) {
                this.m_golfball.m_dVelocity = 0.0;
                b = true;
            }
        }
        if (this.m_golfball.m_house != null || this.m_golfball.m_windmill != null || this.m_golfball.m_duct != null || this.m_golfball.m_cup != null) {
            this.m_golfball.m_bVisible = false;
        }
        else {
            this.m_golfball.m_bVisible = true;
            final double dx0 = this.m_golfball.m_dX0;
            final double dy0 = this.m_golfball.m_dY0;
            double n9 = -1.0;
            if (this.m_golfball.m_slidingbridge != null) {
                this.m_golfball.m_dZ0 = this.m_golfball.m_slidingbridge.m_dBridgeZ;
            }
            else {
                for (Slope3D slope3D2 = this.m_slopeChain; slope3D2 != null; slope3D2 = slope3D2.m_slopeNext) {
                    n9 = slope3D2.getZcoord(dx0, dy0);
                    if (n9 >= 0.0) {
                        break;
                    }
                }
                if (n9 < 0.0) {
                    for (Fairway3D fairway3D = this.m_fairwayChain; fairway3D != null; fairway3D = fairway3D.m_fairwayNext) {
                        n9 = fairway3D.getZcoord(dx0, dy0);
                        if (n9 >= 0.0) {
                            break;
                        }
                    }
                }
                this.m_golfball.m_dZ0 = Math.max(n9, 0.0);
            }
        }
        if (!this.m_bPracticeMode && this.m_golfball.m_nStrokes >= 7 && this.m_golfball.m_dVelocity == 0.0 && !this.m_golfball.m_bHoledOut && !this.m_golfball.m_bStrokeLimit) {
            this.m_golfball.m_bStrokeLimit = true;
            this.m_golfball.m_nStrokes = 7;
            this.m_golfball.SetString("Stroke Limit");
            this.DisplayStrokes(null);
        }
        return b;
    }
    
    public boolean mouseUp(final Event event, final int nMouseX, final int nMouseY) {
        this.m_nMouseX = nMouseX;
        this.m_nMouseY = nMouseY;
        this.m_bMouseInWindow = true;
        this.m_bButtonDown = false;
        return true;
    }
    
    private void UpdateScorecard(final int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        final Graphics graphics = this.m_imageScorecard.getGraphics();
        graphics.setColor(this.SCORECARD_BG_COLOR);
        graphics.fillRect(0, 0, CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight);
        graphics.setFont(new Font("Helvetica", 0, 14));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n12 = 0;
        int n14;
        do {
            if (n12 % 9 == 0) {
                graphics.setColor(this.SCORECARD_HEADING_COLOR);
                n2 = this.m_nScorecardColumnX[n12 / 9] + CarpetGolf3D.m_nPlayfieldWidth / 2;
                final int n13 = 35;
                final String s = new String("Hole");
                final int stringWidth = fontMetrics.stringWidth(s);
                n7 = stringWidth / 2 - 5;
                graphics.drawString(s, n2 + 30 - stringWidth, n13);
                final String s2 = new String("Par");
                final int stringWidth2 = fontMetrics.stringWidth(s2);
                n8 = stringWidth2 / 2 - 5;
                graphics.drawString(s2, n2 + 62 - stringWidth2, n13);
                final String s3 = new String("Strokes");
                final int stringWidth3 = fontMetrics.stringWidth(s3);
                n9 = stringWidth3 / 2 - 10;
                graphics.drawString(s3, n2 + 120 - stringWidth3, n13);
                n3 = 0;
                n5 = 0;
                graphics.setColor(this.SCORECARD_LINE_COLOR);
                n10 = n2 + 30 - stringWidth - 5;
                n11 = n2 + 120 + 5;
                graphics.drawLine(n10, n13 - fontMetrics.getAscent() - 7, n11, n13 - fontMetrics.getAscent() - 7);
                graphics.drawLine(n10, n13 + fontMetrics.getDescent() + 7, n11, n13 + fontMetrics.getDescent() + 7);
            }
            n14 = 35 + fontMetrics.getHeight() * (n12 % 9 + 2);
            graphics.setColor(this.SCORECARD_HOLE_COLOR);
            final int n15 = n12 + 1;
            final String s4 = new String(" " + n15);
            graphics.drawString(s4, n2 + 30 - fontMetrics.stringWidth(s4) - n7, n14);
            if (n12 < n) {
                graphics.setColor(this.SCORECARD_PAR_COLOR);
                final String s5 = new String(" " + this.m_hole[n12].m_nPar);
                graphics.drawString(s5, n2 + 62 - fontMetrics.stringWidth(s5) - n8, n14);
                n3 += this.m_hole[n12].m_nPar;
                n4 += this.m_hole[n12].m_nPar;
                graphics.setColor(this.SCORECARD_STROKES_COLOR);
                final String s6 = new String(" " + this.m_hole[n12].m_golfball.m_nStrokes);
                graphics.drawString(s6, n2 + 120 - fontMetrics.stringWidth(s6) - n9, n14);
                n5 += this.m_hole[n12].m_golfball.m_nStrokes;
                n6 += this.m_hole[n12].m_golfball.m_nStrokes;
            }
            if (n15 % 9 == 0) {
                n14 += 2 * fontMetrics.getHeight();
                graphics.setColor(this.SCORECARD_HEADING_COLOR);
                String s7;
                if (n15 == 9) {
                    s7 = new String("Out:");
                }
                else {
                    s7 = new String("In:");
                }
                graphics.drawString(s7, n2 + 30 - fontMetrics.stringWidth(s7), n14);
                graphics.setColor(this.SCORECARD_PAR_COLOR);
                final String s8 = new String(" " + n3);
                graphics.drawString(s8, n2 + 62 - fontMetrics.stringWidth(s8) - n8, n14);
                graphics.setColor(this.SCORECARD_STROKES_COLOR);
                final String s9 = new String(" " + n5);
                graphics.drawString(s9, n2 + 120 - fontMetrics.stringWidth(s9) - n9, n14);
                graphics.setColor(this.SCORECARD_LINE_COLOR);
                graphics.drawLine(n10, n14 - fontMetrics.getAscent() - 7, n11, n14 - fontMetrics.getAscent() - 7);
            }
            if (n15 % 18 == 0) {
                n14 += fontMetrics.getHeight();
                graphics.setColor(this.SCORECARD_HEADING_COLOR);
                final String s10 = new String("Total:");
                graphics.drawString(s10, n2 + 30 - fontMetrics.stringWidth(s10), n14);
                graphics.setColor(this.SCORECARD_PAR_COLOR);
                final String s11 = new String(" " + n4);
                graphics.drawString(s11, n2 + 62 - fontMetrics.stringWidth(s11) - n8, n14);
                graphics.setColor(this.SCORECARD_STROKES_COLOR);
                final String s12 = new String(" " + n6);
                graphics.drawString(s12, n2 + 120 - fontMetrics.stringWidth(s12) - n9, n14);
            }
        } while (++n12 < 18);
        final int n16 = n14 + 2 * fontMetrics.getHeight();
        graphics.setColor(this.SCORECARD_HEADING_COLOR);
        String s13;
        if (n == this.m_nHoles) {
            s13 = new String("Click to play again");
        }
        else {
            s13 = new String("Click to continue");
        }
        final int stringWidth4 = fontMetrics.stringWidth(s13);
        final int n17 = (CarpetGolf3D.m_nPlayfieldWidth - stringWidth4) / 2;
        graphics.drawString(s13, n17, n16);
        graphics.drawRect(n17 - 5, n16 - fontMetrics.getAscent() - 5, stringWidth4 + 10, fontMetrics.getHeight() + 10);
    }
    
    public void run() {
        if (!this.m_bInitialized) {
            this.Initialization();
        }
        while (true) {
            if (this.m_bDisplayScorecard && this.m_bClick) {
                this.m_bDisplayScorecard = false;
                this.m_bClick = false;
                if (this.m_lScorecardScrollEnd < this.m_lLatestTime) {
                    this.m_lScorecardScrollEnd = this.m_lLatestTime + 1999L;
                }
                else {
                    this.m_lScorecardScrollEnd = 0L;
                }
            }
            this.drawPlayfield();
            final Graphics graphics = this.getGraphics();
            if (this.m_nSwingButtonState != this.m_nSwingButtonNewState) {
                switch (this.m_nSwingButtonState = this.m_nSwingButtonNewState) {
                    case 0: {
                        graphics.drawImage(this.m_imageSwingButtonOff, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                        break;
                    }
                    case 1: {
                        graphics.drawImage(this.m_imageSwingButtonOn, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                        break;
                    }
                    case 2: {
                        graphics.drawImage(this.m_imageSwingButtonDown, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                        break;
                    }
                }
            }
            if (this.m_bRedrawAimingBar) {
                int n;
                if (this.m_dAimingFactor == 0.0) {
                    n = this.m_nAimingBarXsize - 6;
                    graphics.setColor(Color.black);
                }
                else {
                    n = (int)((this.m_nAimingBarXsize - 6) * this.m_dAimingFactor);
                    graphics.setColor(this.AIMING_BAR_COLOR);
                }
                graphics.fillRect(this.m_nAimingBarX + 3, this.m_nAimingBarY + 3 + 14, n, this.m_nAimingBarYsize - 6 - 14);
                this.m_bRedrawAimingBar = false;
            }
            if (this.m_lLatestTime >= this.m_lScorecardScrollEnd) {
                if (this.m_bDisplayScorecard) {
                    graphics.drawImage(this.m_imageScorecard, this.m_nPlayfieldX, this.m_nPlayfieldY, null);
                }
                else {
                    this.m_joystick.draw(graphics);
                    graphics.drawImage(this.m_viewpoint.m_imageViewplane, this.m_nPlayfieldX, this.m_nPlayfieldY, null);
                }
            }
            else {
                final double max = Math.max(1.0 - (this.m_lScorecardScrollEnd - this.m_lLatestTime) / 1999.0, 0.0);
                final Graphics graphics2 = this.m_imageScrollBuffer.getGraphics();
                if (this.m_bDisplayScorecard) {
                    graphics2.drawImage(this.m_imagePrevHole, -(int)(max * CarpetGolf3D.m_nPlayfieldWidth), 0, null);
                    graphics2.drawImage(this.m_imageScorecard, (int)(CarpetGolf3D.m_nPlayfieldWidth * (1.0 - max)), 0, null);
                }
                else {
                    graphics2.drawImage(this.m_imageScorecard, -(int)(max * CarpetGolf3D.m_nPlayfieldWidth), 0, null);
                    graphics2.drawImage(this.m_viewpoint.m_imageViewplane, (int)(CarpetGolf3D.m_nPlayfieldWidth * (1.0 - max)), 0, null);
                }
                graphics2.dispose();
                graphics.drawImage(this.m_imageScrollBuffer, this.m_nPlayfieldX, this.m_nPlayfieldY, null);
            }
            graphics.dispose();
            System.gc();
        }
    }
    
    private void DrawProgressBar(final int n, final int n2, final int n3, final int n4, final double n5) {
        final Graphics graphics = this.m_imageLoading.getGraphics();
        graphics.setColor(Color.blue);
        graphics.fillRect(n, n2, (int)(n3 * n5), n4);
        graphics.dispose();
        final Graphics graphics2 = this.getGraphics();
        graphics2.drawImage(this.m_imageLoading, 0, 0, null);
        graphics2.dispose();
    }
    
    private synchronized void drawPlayfield() {
        if (this.m_stringError != null) {
            final Graphics graphics = this.m_viewpoint.m_imageViewplane.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight);
            graphics.setColor(Color.black);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int n = fontMetrics.getHeight() + 2;
            graphics.drawString("Parameter error:", 10, n);
            final int n2 = n + fontMetrics.getHeight();
            graphics.drawString(this.m_stringParamName, 10, n2);
            final int n3 = n2 + fontMetrics.getHeight();
            graphics.drawString(this.m_stringException, 10, n3);
            graphics.drawString(this.m_stringError, 10, n3 + fontMetrics.getHeight());
            return;
        }
        if (this.m_bPrev) {
            this.m_bPrev = false;
            if (this.m_ndxCurrHole > 0) {
                this.SelectHole(this.m_ndxCurrHole - 1);
            }
        }
        if (this.m_bNext) {
            this.m_bNext = false;
            if (this.m_ndxCurrHole < this.m_nHoles - 1) {
                this.SelectHole(this.m_ndxCurrHole + 1);
            }
        }
        if (this.m_bReplay) {
            this.m_bReplay = false;
            this.SelectHole(this.m_ndxCurrHole);
        }
        this.m_lLatestTime = System.currentTimeMillis();
        final int n4 = this.m_nFrameNumber % 10;
        if (this.m_nFrameNumber > 10) {
            this.m_dSecondsPerFrame = (int)(this.m_lLatestTime - this.m_lTime[n4]) / 10.0 / 1000.0;
        }
        else {
            this.m_dSecondsPerFrame = 0.1;
        }
        this.m_lTime[n4] = this.m_lLatestTime;
        ++this.m_nFrameNumber;
        boolean b = false;
        if (this.m_golfball.m_dVelocity > 0.0 && this.GolfballPhysics()) {
            this.m_vantagepointChain.set(this.m_viewpoint, this.m_golfball);
            b = true;
        }
        if (this.m_golfball.m_bStrokeLimit && this.m_golfball.m_string == null) {
            this.endHole();
        }
        if (this.m_houseChain != null) {
            this.m_houseChain.transformFlagChain(this.m_lLatestTime);
        }
        if (this.m_windmillChain != null) {
            this.m_windmillChain.transformBladeChain(this.m_lLatestTime);
        }
        if (this.m_slidingbridgeChain != null) {
            this.m_slidingbridgeChain.transformBridgeChain(this.m_lLatestTime);
        }
        if (this.m_bZoomIn ^ this.m_bZoomOut) {
            this.m_viewpoint.setFratio(this.m_viewpoint.getFratio() * (1.0 + (this.m_bZoomIn ? 1 : -1) * 0.7 * this.m_dSecondsPerFrame));
            b = true;
        }
        if (this.m_bXplus ^ this.m_bXminus) {
            final double n5 = this.m_bXplus ? 1 : -1;
            final cgVector vector = this.m_viewpoint.m_vector;
            vector.m_dX += 5.0 * n5 * this.m_dSecondsPerFrame;
            b = true;
        }
        if (this.m_bYplus ^ this.m_bYminus) {
            final double n6 = this.m_bYplus ? 1 : -1;
            final cgVector vector2 = this.m_viewpoint.m_vector;
            vector2.m_dY += 5.0 * n6 * this.m_dSecondsPerFrame;
            b = true;
        }
        if (this.m_bZplus ^ this.m_bZminus) {
            final double n7 = this.m_bZplus ? 1 : -1;
            final cgVector vector3 = this.m_viewpoint.m_vector;
            vector3.m_dZ += 5.0 * n7 * this.m_dSecondsPerFrame;
            b = true;
        }
        if (this.m_bPitchUp ^ this.m_bPitchDown) {
            this.m_viewpoint.m_matrix.rotateSelf(-0.6 * (this.m_bPitchUp ? 1 : -1) * this.m_dSecondsPerFrame, 0.0, 0.0);
            b = true;
        }
        this.m_joystick.update(this.m_nMouseX, this.m_nMouseY, this.m_bMouseInWindow, this.m_bButtonDown);
        if (this.m_joystick.m_dYvalue != 0.0) {
            final double n8 = 1.0 - Math.abs(this.m_viewpoint.m_matrix.m_dZz);
            if (n8 > 0.0) {
                final double n9 = 5.0 * this.m_joystick.m_dYvalue / n8;
                final cgVector vector4 = this.m_viewpoint.m_vector;
                vector4.m_dX += n9 * this.m_viewpoint.m_matrix.m_dZx * this.m_dSecondsPerFrame;
                final cgVector vector5 = this.m_viewpoint.m_vector;
                vector5.m_dY += n9 * this.m_viewpoint.m_matrix.m_dZy * this.m_dSecondsPerFrame;
                b = true;
            }
        }
        if (this.m_joystick.m_dXvalue != 0.0) {
            this.m_viewpoint.m_matrix.rotateWorld(0.0, 0.0, -0.6 * this.m_joystick.m_dXvalue * this.m_dSecondsPerFrame);
            b = true;
        }
        if (b) {
            this.m_viewpoint.render(this.m_object, this.m_nObjects);
        }
        else {
            if (this.m_houseChain != null) {
                this.m_houseChain.renderFlagChain(this.m_viewpoint);
            }
            if (this.m_windmillChain != null) {
                this.m_windmillChain.renderBladeChain(this.m_viewpoint);
            }
            if (this.m_slidingbridgeChain != null) {
                this.m_slidingbridgeChain.renderBridgeChain(this.m_viewpoint);
            }
        }
        if (this.m_bClick && this.m_golfball.m_dVelocity == 0.0 && this.m_golfball.m_string == null && !this.m_bDisplayScorecard) {
            final int n10 = this.m_nMouseX - this.m_nPlayfieldX;
            final int n11 = this.m_nMouseY - this.m_nPlayfieldY;
            if (n10 < CarpetGolf3D.m_nPlayfieldWidth && n11 < CarpetGolf3D.m_nPlayfieldHeight) {
                this.m_arrow.aim(n10, n11, this.m_golfball, this.m_viewpoint);
            }
        }
        if (this.m_arrow.m_bShow && this.m_nSwingButtonState == 0) {
            this.m_nSwingButtonNewState = 1;
        }
        if (!this.m_arrow.m_bShow && this.m_nSwingButtonState == 1) {
            this.m_nSwingButtonNewState = 0;
        }
        if (this.m_bClick && !this.m_bDisplayScorecard) {
            if (this.m_nSwingButtonNewState == 1 && this.m_nMouseX >= this.m_nSwingButtonXmin && this.m_nMouseX <= this.m_nSwingButtonXmax && this.m_nMouseY >= this.m_nSwingButtonYmin && this.m_nMouseY <= this.m_nSwingButtonYmax) {
                this.m_nSwingButtonNewState = 2;
                this.m_lSwingStartTime = System.currentTimeMillis();
                this.m_arrow.m_bShow = false;
            }
            this.m_bClick = false;
        }
        if (this.m_nSwingButtonNewState == 2 && (!this.m_bButtonDown || this.m_nMouseX < this.m_nSwingButtonXmin || this.m_nMouseX > this.m_nSwingButtonXmax || this.m_nMouseY < this.m_nSwingButtonYmin || this.m_nMouseY > this.m_nSwingButtonYmax)) {
            this.m_nSwingButtonNewState = 0;
            final double n12 = this.m_dAimingFactor / 2.0 - 0.25;
            this.m_golfball.m_dAngle = this.m_arrow.m_dAngle - n12 * Math.abs(n12 * 4.0);
            this.m_golfball.m_dVelocity = this.m_arrow.m_dLength / 10.0 * 10.0;
            final Golfball3D golfball = this.m_golfball;
            ++golfball.m_nStrokes;
            this.DisplayStrokes(null);
            this.m_acStroke.play();
        }
        double dAimingFactor = this.m_dAimingFactor;
        if (this.m_nSwingButtonNewState == 2) {
            dAimingFactor = (System.currentTimeMillis() - this.m_lSwingStartTime) / 3000.0;
            if (dAimingFactor > 1.0) {
                dAimingFactor = 1.0;
            }
        }
        else if (this.m_golfball.m_dVelocity == 0.0) {
            dAimingFactor = 0.0;
        }
        if (dAimingFactor != this.m_dAimingFactor) {
            this.m_dAimingFactor = dAimingFactor;
            this.m_bRedrawAimingBar = true;
        }
        this.m_golfball.render(this.m_viewpoint);
        this.m_viewpoint.draw(this.m_golfball, this.m_arrow);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final Color color = new Color(124, 124, 124);
        final Color color2 = new Color(28, 28, 28);
        final int n = 5;
        final int n2 = 2;
        if (!this.m_bInitialized) {
            return;
        }
        graphics.setColor(this.m_colorWindow);
        graphics.fillRect(0, 0, size.width, size.height);
        for (int i = 0; i < n; ++i) {
            graphics.setColor(color);
            graphics.drawLine(i, i, size.width - i - 1, i);
            graphics.drawLine(i, i, i, size.height - i - 1);
            graphics.setColor(color2);
            graphics.drawLine(i, size.height - i - 1, size.width - i - 1, size.height - i - 1);
            graphics.drawLine(size.width - i - 1, i, size.width - i - 1, size.height - i - 1);
        }
        for (int j = 0; j < n2; ++j) {
            graphics.setColor(color2);
            graphics.drawLine(this.m_nPlayfieldX - j, this.m_nPlayfieldY - j, this.m_nPlayfieldX + CarpetGolf3D.m_nPlayfieldWidth + j, this.m_nPlayfieldY - j);
            graphics.drawLine(this.m_nPlayfieldX - j, this.m_nPlayfieldY - j, this.m_nPlayfieldX - j, this.m_nPlayfieldY + CarpetGolf3D.m_nPlayfieldHeight + j);
            graphics.setColor(color);
            graphics.drawLine(this.m_nPlayfieldX + CarpetGolf3D.m_nPlayfieldWidth + j, this.m_nPlayfieldY + CarpetGolf3D.m_nPlayfieldHeight + j, this.m_nPlayfieldX + CarpetGolf3D.m_nPlayfieldWidth + j, this.m_nPlayfieldY - j);
            graphics.drawLine(this.m_nPlayfieldX + CarpetGolf3D.m_nPlayfieldWidth + j, this.m_nPlayfieldY + CarpetGolf3D.m_nPlayfieldHeight + j, this.m_nPlayfieldX - j, this.m_nPlayfieldY + CarpetGolf3D.m_nPlayfieldHeight + j);
        }
        this.m_joystick.forceDraw(graphics);
        switch (this.m_nSwingButtonState) {
            case 0: {
                graphics.drawImage(this.m_imageSwingButtonOff, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                break;
            }
            case 1: {
                graphics.drawImage(this.m_imageSwingButtonOn, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                break;
            }
            case 2: {
                graphics.drawImage(this.m_imageSwingButtonDown, this.m_nSwingButtonXmin, this.m_nSwingButtonYmin, null);
                break;
            }
        }
        graphics.drawImage(this.m_imageAimingBar, this.m_nAimingBarX, this.m_nAimingBarY, null);
        graphics.drawImage(this.m_imageHoleParStrokes, this.m_nHPScaptionX, 15, null);
        this.DisplayHoleAndPar(graphics);
        if (this.m_golfball != null) {
            this.DisplayStrokes(graphics);
        }
    }
    
    private void Initialization() {
        this.m_imageHoleParStrokes = this.getImage(this.getDocumentBase(), "hps.gif");
        this.m_mediaTracker.addImage(this.m_imageHoleParStrokes, 0);
        this.m_imageDigits = this.getImage(this.getDocumentBase(), "digits.gif");
        this.m_mediaTracker.addImage(this.m_imageDigits, 1);
        Joystick.m_imageBackground = this.getImage(this.getDocumentBase(), "JoystickBase.gif");
        this.m_mediaTracker.addImage(Joystick.m_imageBackground, 2);
        Joystick.m_imageKnob = this.getImage(this.getDocumentBase(), "JoystickKnob.gif");
        this.m_mediaTracker.addImage(Joystick.m_imageKnob, 3);
        this.m_imageSwingButtonOff = this.getImage(this.getDocumentBase(), "SwingOff.gif");
        this.m_mediaTracker.addImage(this.m_imageSwingButtonOff, 4);
        this.m_imageSwingButtonOn = this.getImage(this.getDocumentBase(), "SwingOn.gif");
        this.m_mediaTracker.addImage(this.m_imageSwingButtonOn, 5);
        this.m_imageSwingButtonDown = this.getImage(this.getDocumentBase(), "SwingDown.gif");
        this.m_mediaTracker.addImage(this.m_imageSwingButtonDown, 6);
        this.m_imageAimingBar = this.getImage(this.getDocumentBase(), "AimingBar.gif");
        this.m_mediaTracker.addImage(this.m_imageAimingBar, 7);
        final Dimension size = this.size();
        this.m_imageLoading = this.createImage(size.width, size.height);
        final Graphics graphics = this.m_imageLoading.getGraphics();
        final int n = 200;
        final int n2 = 30;
        final int n3 = 2;
        final int n4 = (size.width - n) / 2;
        final int n5 = size.height * 2 / 3 - n2 / 2;
        final int n6 = n4 + n3;
        final int n7 = n5 + n3;
        final int n8 = n - 2 * n3;
        final int n9 = n2 - 2 * n3;
        final int n10 = 8;
        int n11 = -1;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Helvetica", 1, 36));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String s = "Carpet Golf 3-D";
        final int n12 = (size.width - fontMetrics.stringWidth(s)) / 2;
        final int n13 = size.height / 3;
        graphics.drawString(s, n12 + 1, n13 + 1);
        graphics.setColor(Fairway3D.FAIRWAY_COLOR);
        graphics.drawString(s, n12 - 2, n13 - 2);
        final int n14 = n13 + fontMetrics.getDescent();
        graphics.setColor(Color.black);
        graphics.setFont(new Font("Helvetica", 0, 14));
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final int n15 = n14 + (fontMetrics2.getAscent() + fontMetrics2.getLeading());
        final String s2 = "Copyright © 1997-1998 Edward R. Hobbs";
        graphics.drawString(s2, (size.width - fontMetrics2.stringWidth(s2)) / 2, n15);
        final int n16 = n15 + fontMetrics2.getDescent();
        graphics.setColor(Color.black);
        for (int i = 0; i < n3; ++i) {
            graphics.drawRect(n4 + i, n5 + i, n - 2 * i - 1, n2 - 2 * i - 1);
        }
        final String s3 = "Loading graphics...";
        graphics.drawString(s3, (size.width - fontMetrics2.stringWidth(s3)) / 2, n5 - fontMetrics2.getDescent() - 2);
        graphics.dispose();
        while (true) {
            int n17 = 0;
            for (int j = 0; j < n10; ++j) {
                if (this.m_mediaTracker.checkID(j, true)) {
                    ++n17;
                }
            }
            if (n17 > n11) {
                n11 = n17;
                this.DrawProgressBar(n6, n7, n8, n9, n11 / n10);
            }
            if (n11 >= n10) {
                break;
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics2 = this.m_imageLoading.getGraphics();
        graphics2.setFont(new Font("Helvetica", 0, 14));
        final FontMetrics fontMetrics3 = graphics2.getFontMetrics();
        graphics2.setColor(Color.white);
        graphics2.fillRect(0, n16, size.width, size.height - n16);
        graphics2.setColor(Color.black);
        for (int k = 0; k < n3; ++k) {
            graphics2.drawRect(n4 + k, n5 + k, n - 2 * k - 1, n2 - 2 * k - 1);
        }
        final String s4 = "Loading sounds...";
        graphics2.drawString(s4, (size.width - fontMetrics3.stringWidth(s4)) / 2, n5 - fontMetrics3.getDescent() - 2);
        graphics2.dispose();
        this.DrawProgressBar(n6, n7, n8, n9, 0.0);
        (this.m_acRebound = this.getAudioClip(this.getDocumentBase(), "rebound.au")).play();
        this.DrawProgressBar(n6, n7, n8, n9, 0.2);
        (this.m_acStroke = this.getAudioClip(this.getDocumentBase(), "stroke.au")).play();
        this.DrawProgressBar(n6, n7, n8, n9, 0.4);
        (this.m_acWaterhaz = this.getAudioClip(this.getDocumentBase(), "waterhaz.au")).play();
        this.DrawProgressBar(n6, n7, n8, n9, 0.6);
        (this.m_acHoleout = this.getAudioClip(this.getDocumentBase(), "holeout.au")).play();
        this.DrawProgressBar(n6, n7, n8, n9, 0.8);
        (this.m_acHouse = this.getAudioClip(this.getDocumentBase(), "house.au")).play();
        this.DrawProgressBar(n6, n7, n8, n9, 1.0);
        final Graphics graphics3 = this.m_imageLoading.getGraphics();
        graphics3.setFont(new Font("Helvetica", 0, 14));
        final FontMetrics fontMetrics4 = graphics3.getFontMetrics();
        graphics3.setColor(Color.white);
        graphics3.fillRect(0, n16, size.width, size.height - n16);
        graphics3.setColor(Color.black);
        for (int l = 0; l < n3; ++l) {
            graphics3.drawRect(n4 + l, n5 + l, n - 2 * l - 1, n2 - 2 * l - 1);
        }
        final String s5 = "Loading course data...";
        graphics3.drawString(s5, (size.width - fontMetrics4.stringWidth(s5)) / 2, n5 - fontMetrics4.getDescent() - 2);
        graphics3.dispose();
        this.DrawProgressBar(n6, n7, n8, n9, 0.0);
        while (this.m_nHoles < 18) {
            this.m_stringParamName = new String("hole" + new Integer(this.m_nHoles + 1).toString());
            final String parameter = this.getParameter(this.m_stringParamName);
            if (parameter == null) {
                break;
            }
            this.m_hole[this.m_nHoles] = new Hole3D(parameter);
            if (this.m_hole[this.m_nHoles].m_stringError != null) {
                this.m_stringError = this.m_hole[this.m_nHoles].m_stringError;
                this.m_stringException = this.m_hole[this.m_nHoles].m_stringException;
                break;
            }
            ++this.m_nHoles;
            this.DrawProgressBar(n6, n7, n8, n9, this.m_nHoles / 18.0);
        }
        this.m_imageLoading.flush();
        this.m_nPlayfieldX = 15;
        this.m_nPlayfieldY = 15;
        CarpetGolf3D.m_nPlayfieldWidth = size.width - 115;
        CarpetGolf3D.m_nPlayfieldHeight = size.height - 30;
        this.m_viewpoint = new cgViewpoint(CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight, this);
        (this.m_viewpoint.m_matrixLight = new cgMatrix(new cgVector(1.0, 0.0, 0.0), new cgVector(0.0, 1.0, 0.0), new cgVector(0.0, 0.0, 1.0))).rotateWorld(0.7853981633974483, -0.39269908169872414, 0.0);
        this.m_imageScorecard = this.createImage(CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight);
        this.m_imageScrollBuffer = this.createImage(CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight);
        this.m_imagePrevHole = this.createImage(CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight);
        final Random random = new Random();
        final double n18 = 0.3;
        final double n19 = 0.45;
        final double n20 = 0.6;
        final double n21 = 1.0;
        final double n22 = 0.3;
        final double n23 = 0.5;
        final int n24 = CarpetGolf3D.m_nPlayfieldWidth * CarpetGolf3D.m_nPlayfieldHeight;
        final int[] array = new int[n24];
        for (int n25 = 0; n25 < n24; ++n25) {
            array[n25] = (0xFF000000 | Color.HSBtoRGB((float)(n18 + random.nextDouble() * (n19 - n18)), (float)(n20 + random.nextDouble() * (n21 - n20)), (float)(n22 + random.nextDouble() * (n23 - n22))));
        }
        this.m_viewpoint.m_imageBackgrnd = this.createImage(new MemoryImageSource(CarpetGolf3D.m_nPlayfieldWidth, CarpetGolf3D.m_nPlayfieldHeight, array, 0, CarpetGolf3D.m_nPlayfieldWidth));
        final int n26 = (this.m_nPlayfieldX + CarpetGolf3D.m_nPlayfieldWidth + size.width) / 2;
        this.m_joystick = new Joystick(n26, 150, this);
        this.m_nHPScaptionX = n26 - this.m_imageHoleParStrokes.getWidth(null) / 2;
        this.m_arrow = new Arrow();
        int n27 = 0;
        do {
            this.m_imageDigit[n27] = this.createImage(7, 8);
            final Graphics graphics4 = this.m_imageDigit[n27].getGraphics();
            graphics4.drawImage(this.m_imageDigits, -7 * n27, 0, null);
            graphics4.dispose();
        } while (++n27 < 10);
        final int width = this.m_imageSwingButtonOff.getWidth(null);
        final int height = this.m_imageSwingButtonOff.getHeight(null);
        this.m_nSwingButtonXmin = n26 - width / 2;
        this.m_nSwingButtonXmax = this.m_nSwingButtonXmin + width;
        this.m_nSwingButtonYmin = 265 - height / 2;
        this.m_nSwingButtonYmax = this.m_nSwingButtonYmin + height;
        this.m_nSwingButtonState = 0;
        this.m_nSwingButtonNewState = 0;
        this.m_nAimingBarXsize = this.m_imageAimingBar.getWidth(null);
        this.m_nAimingBarYsize = this.m_imageAimingBar.getHeight(null);
        this.m_nAimingBarX = n26 - this.m_nAimingBarXsize / 2;
        this.m_nAimingBarY = 232 - this.m_nAimingBarYsize / 2;
        this.m_dAimingFactor = 0.0;
        this.m_bRedrawAimingBar = false;
        final String parameter2 = this.getParameter("mode");
        if (parameter2 != null && parameter2.equalsIgnoreCase("practice")) {
            this.m_bPracticeMode = true;
        }
        if (this.m_nHoles <= 0 && this.m_stringError == null) {
            this.m_stringError = new String("No course data found!");
        }
        else {
            this.SelectHole(0);
        }
        this.m_bInitialized = true;
        this.repaint();
    }
}
