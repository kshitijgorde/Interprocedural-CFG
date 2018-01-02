import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FontMetrics;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Choice;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Random;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class AtomTransFrame extends Frame implements ComponentListener, ActionListener, AdjustmentListener, MouseMotionListener, MouseListener, ItemListener
{
    Thread engine;
    Dimension winSize;
    Image dbimage;
    Image memimage;
    Random random;
    int gridSizeX;
    int gridSizeY;
    Button blankButton;
    Checkbox stoppedCheck;
    CheckboxMenuItem colorCheck;
    CheckboxMenuItem eCheckItem;
    CheckboxMenuItem xCheckItem;
    CheckboxMenuItem restrictItem;
    CheckboxMenuItem dimensionsItem;
    CheckboxMenuItem axesItem;
    CheckboxMenuItem autoZoomItem;
    CheckboxMenuItem animatedZoomItem;
    MenuItem exitItem;
    Choice sliceChooser;
    Choice freqChooser;
    Choice dirChooser;
    Choice setupChooser;
    static final int SLICE_NONE = 0;
    static final int SLICE_X = 1;
    static final int SLICE_Y = 2;
    static final int SLICE_Z = 3;
    static final int STATE_1S = 0;
    static final int STATE_2S = 1;
    static final int STATE_2P1 = 2;
    static final int STATE_2PX = 3;
    static final int STATE_2PZ = 4;
    static final int STATE_3S = 5;
    static final int STATE_2PM1 = 6;
    static final int VIEW_XY = 0;
    static final int VIEW_XZ = 1;
    static final int VIEW_XZ12 = 2;
    static final int FREQ_12 = 0;
    static final int FREQ_13 = 1;
    static final int FREQ_14 = 2;
    static final int FREQ_23 = 3;
    static final int FREQ_24 = 4;
    static final int FREQ_34 = 5;
    Scrollbar speedBar;
    Scrollbar strengthBar;
    Scrollbar stepBar;
    Scrollbar resolutionBar;
    Scrollbar internalResBar;
    Scrollbar brightnessBar;
    Scrollbar scaleBar;
    Scrollbar sampleBar;
    View viewPotential;
    View viewX;
    View viewStates;
    View[] viewList;
    int viewCount;
    Orbital[] orbitals;
    int orbCount;
    Phasor[] phasors;
    int phasorCount;
    BasisState[] states;
    int stateCount;
    Complex[] newcoef;
    double dragZoomStart;
    double lastXRot;
    double lastYRot;
    double colorMult;
    double zoom;
    double[] rotmatrix;
    Rectangle viewAxes;
    Rectangle viewField;
    static final double pi = 3.141592653589793;
    static final double pi2 = 6.283185307179586;
    static final double root2 = 1.4142135623730951;
    static final double root2inv = 0.7071067811865476;
    int[] xpoints;
    int[] ypoints;
    int selectedPaneHandle;
    double freqMax;
    double freq;
    double freqPhase;
    PhaseColor[] phaseColors;
    Color purple;
    double resadj;
    boolean dragging;
    MemoryImageSource imageSource;
    int[] pixels;
    int sampleCount;
    int dataSize;
    static int maxModes;
    static int maxDispCoefs;
    static int viewDistance;
    int pause;
    AtomTrans applet;
    State selectedState;
    Phasor selectedPhasor;
    int selection;
    static final int SEL_NONE = 0;
    static final int SEL_POTENTIAL = 1;
    static final int SEL_X = 2;
    static final int SEL_STATES = 3;
    static final int SEL_HANDLE = 4;
    static final int MODE_ANGLE = 0;
    static final int MODE_SLICE = 1;
    static final int DIR_X = 0;
    static final int DIR_Y = 1;
    static final int DIR_Z = 2;
    static final int DIR_CCW = 3;
    static final int DIR_CW = 4;
    int[][] slicerPoints;
    double[][] sliceFaces;
    double[] sliceFace;
    int sliceFaceCount;
    double sliceval;
    int[] sampleMult;
    boolean selectedSlice;
    boolean settingScale;
    double magDragStart;
    int dragX;
    int dragY;
    int dragStartX;
    int dragStartY;
    double t;
    public static final double epsilon = 0.01;
    static final int panePad = 4;
    static final int phaseColorCount = 50;
    float funcr;
    float funci;
    int phiIndex;
    double bestBrightness;
    double userBrightMult;
    boolean manualScale;
    Color gray2;
    FontMetrics fontMetrics;
    AtomTransCanvas cv;
    boolean useBufferedImage;
    long lastFrameTime;
    double[] dipoleOpX;
    double[] dipoleOpY;
    double[] dipoleOpZ;
    int scaleValue;
    int[] setups;
    String[] setupStrings;
    
    public String getAppletInfo() {
        return "AtomTrans by Paul Falstad";
    }
    
    int getrand(final int n) {
        int nextInt = this.random.nextInt();
        if (nextInt < 0) {
            nextInt = -nextInt;
        }
        return nextInt % n;
    }
    
    AtomTransFrame(final AtomTrans applet) {
        super("Atomic Dipole Transitions Applet v1.5");
        this.gridSizeX = 200;
        this.gridSizeY = 200;
        this.dragging = false;
        this.selection = -1;
        this.userBrightMult = 1.0;
        this.useBufferedImage = false;
        this.dipoleOpX = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.526749, 0.0, -0.526749, 0.210938, 0.0, -0.210938, 0.124346, 0.0, -0.124346, 0.085203, 0.0, -0.085203, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.12132, 0.0, 2.12132, 1.25121, 0.0, -1.25121, 0.523487, 0.0, -0.523487, 0.315964, 0.0, -0.315964, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.383102, 0.0, -0.383102, -5.19615, 0.0, 5.19615, 2.23285, 0.0, -2.23285, 0.922468, 0.0, -0.922468, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.156074, 0.0, -0.156074, 0.997569, 0.0, -0.997569, -9.48683, 0.0, 9.48683, 3.47739, 0.0, -3.47739, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0930919, 0.0, -0.0930919, 0.395842, 0.0, -0.395842, 1.87806, 0.0, -1.87806, -15.0, 0.0, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.526749, -2.12132, 0.383102, 0.156074, 0.0930919, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, -0.866861, 0.0, 0.0, 0.764602, 0.0, -0.312148, 0.0, 0.0, 0.436072, 0.0, -0.178026, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.50145, 0.0, -1.50145, 0.0, 0.0, 0.540655, 0.0, -0.540655, 0.0, 0.0, 0.30835, 0.0, -0.30835, 0.0, -0.526749, 2.12132, -0.383102, -0.156074, -0.0930919, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.866861, 0.0, -2.12337, 0.0, 0.0, 0.312148, 0.0, -0.764602, 0.0, 0.0, 0.178026, 0.0, -0.436072, 0.210938, 1.25121, -5.19615, 0.997569, 0.395842, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -4.5, 0.0, 1.83712, 0.0, 0.0, 3.38335, 0.0, -1.38125, 0.0, 0.0, 1.32747, 0.0, -0.541939, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -3.18198, 0.0, 3.18198, 0.0, 0.0, 2.39239, 0.0, -2.39239, 0.0, 0.0, 0.938666, 0.0, -0.938666, 0.0, -0.210938, -1.25121, 5.19615, -0.997569, -0.395842, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.83712, 0.0, 4.5, 0.0, 0.0, 1.38125, 0.0, -3.38335, 0.0, 0.0, 0.541939, 0.0, -1.32747, 0.124346, 0.523487, 2.23285, -9.48683, 1.87806, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.582386, 0.0, -0.237758, 0.0, 0.0, -9.29516, 0.0, 3.79473, 0.0, 0.0, 4.93677, 0.0, -2.01543, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.411809, 0.0, -0.411809, 0.0, 0.0, -6.57267, 0.0, 6.57267, 0.0, 0.0, 3.49082, 0.0, -3.49082, 0.0, -0.124346, -0.523487, -2.23285, 9.48683, -1.87806, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.237758, 0.0, -0.582386, 0.0, 0.0, -3.79473, 0.0, 9.29516, 0.0, 0.0, 2.01543, 0.0, -4.93677, 0.085203, 0.315964, 0.922468, 3.47739, -15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.215914, 0.0, -0.0881464, 0.0, 0.0, 1.36191, 0.0, -0.555997, 0.0, 0.0, -15.3704, 0.0, 6.27495, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.152674, 0.0, -0.152674, 0.0, 0.0, 0.963015, 0.0, -0.963015, 0.0, 0.0, -10.8685, 0.0, 10.8685, 0.0, -0.085203, -0.315964, -0.922468, -3.47739, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0881464, 0.0, -0.215914, 0.0, 0.0, 0.555997, 0.0, -1.36191, 0.0, 0.0, -6.27495, 0.0, 15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.0, -4.5, 0.0, 0.0, 0.582386, 0.0, 0.0, 0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.50145, 0.0, 0.0, -3.18198, 0.0, 0.0, 0.411809, 0.0, 0.0, 0.152674, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.866861, 0.0, 0.866861, 1.83712, 0.0, -1.83712, -0.237758, 0.0, 0.237758, -0.0881464, 0.0, 0.0881464, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.50145, 0.0, 0.0, 3.18198, 0.0, 0.0, -0.411809, 0.0, 0.0, -0.152674, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.12337, 0.0, 0.0, 4.5, 0.0, 0.0, -0.582386, 0.0, 0.0, -0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.764602, 0.0, 0.0, 3.38335, 0.0, 0.0, -9.29516, 0.0, 0.0, 1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.540655, 0.0, 0.0, 2.39239, 0.0, 0.0, -6.57267, 0.0, 0.0, 0.963015, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.312148, 0.0, 0.312148, -1.38125, 0.0, 1.38125, 3.79473, 0.0, -3.79473, -0.555997, 0.0, 0.555997, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.540655, 0.0, 0.0, -2.39239, 0.0, 0.0, 6.57267, 0.0, 0.0, -0.963015, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.764602, 0.0, 0.0, -3.38335, 0.0, 0.0, 9.29516, 0.0, 0.0, -1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.436072, 0.0, 0.0, 1.32747, 0.0, 0.0, 4.93677, 0.0, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.30835, 0.0, 0.0, 0.938666, 0.0, 0.0, 3.49082, 0.0, 0.0, -10.8685, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.178026, 0.0, 0.178026, -0.541939, 0.0, 0.541939, -2.01543, 0.0, 2.01543, 6.27495, 0.0, -6.27495, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.30835, 0.0, 0.0, -0.938666, 0.0, 0.0, -3.49082, 0.0, 0.0, 10.8685, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.436072, 0.0, 0.0, -1.32747, 0.0, 0.0, -4.93677, 0.0, 0.0, 15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        this.dipoleOpY = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.526749, 0.0, 0.526749, 0.210938, 0.0, 0.210938, 0.124346, 0.0, 0.124346, 0.085203, 0.0, 0.085203, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.12132, 0.0, -2.12132, 1.25121, 0.0, 1.25121, 0.523487, 0.0, 0.523487, 0.315964, 0.0, 0.315964, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.383102, 0.0, 0.383102, -5.19615, 0.0, -5.19615, 2.23285, 0.0, 2.23285, 0.922468, 0.0, 0.922468, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.156074, 0.0, 0.156074, 0.997569, 0.0, 0.997569, -9.48683, 0.0, -9.48683, 3.47739, 0.0, 3.47739, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0930919, 0.0, 0.0930919, 0.395842, 0.0, 0.395842, 1.87806, 0.0, 1.87806, -15.0, 0.0, -15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.526749, 2.12132, -0.383102, -0.156074, -0.0930919, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.866861, 0.0, 0.0, 0.764602, 0.0, 0.312148, 0.0, 0.0, 0.436072, 0.0, 0.178026, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.50145, 0.0, 1.50145, 0.0, 0.0, 0.540655, 0.0, 0.540655, 0.0, 0.0, 0.30835, 0.0, 0.30835, 0.0, -0.526749, 2.12132, -0.383102, -0.156074, -0.0930919, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.866861, 0.0, 2.12337, 0.0, 0.0, 0.312148, 0.0, 0.764602, 0.0, 0.0, 0.178026, 0.0, 0.436072, -0.210938, -1.25121, 5.19615, -0.997569, -0.395842, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -4.5, 0.0, -1.83712, 0.0, 0.0, 3.38335, 0.0, 1.38125, 0.0, 0.0, 1.32747, 0.0, 0.541939, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -3.18198, 0.0, -3.18198, 0.0, 0.0, 2.39239, 0.0, 2.39239, 0.0, 0.0, 0.938666, 0.0, 0.938666, 0.0, -0.210938, -1.25121, 5.19615, -0.997569, -0.395842, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.83712, 0.0, -4.5, 0.0, 0.0, 1.38125, 0.0, 3.38335, 0.0, 0.0, 0.541939, 0.0, 1.32747, -0.124346, -0.523487, -2.23285, 9.48683, -1.87806, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.582386, 0.0, 0.237758, 0.0, 0.0, -9.29516, 0.0, -3.79473, 0.0, 0.0, 4.93677, 0.0, 2.01543, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.411809, 0.0, 0.411809, 0.0, 0.0, -6.57267, 0.0, -6.57267, 0.0, 0.0, 3.49082, 0.0, 3.49082, 0.0, -0.124346, -0.523487, -2.23285, 9.48683, -1.87806, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.237758, 0.0, 0.582386, 0.0, 0.0, -3.79473, 0.0, -9.29516, 0.0, 0.0, 2.01543, 0.0, 4.93677, -0.085203, -0.315964, -0.922468, -3.47739, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.215914, 0.0, 0.0881464, 0.0, 0.0, 1.36191, 0.0, 0.555997, 0.0, 0.0, -15.3704, 0.0, -6.27495, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.152674, 0.0, 0.152674, 0.0, 0.0, 0.963015, 0.0, 0.963015, 0.0, 0.0, -10.8685, 0.0, -10.8685, 0.0, -0.085203, -0.315964, -0.922468, -3.47739, 15.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0881464, 0.0, 0.215914, 0.0, 0.0, 0.555997, 0.0, 1.36191, 0.0, 0.0, -6.27495, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, -2.12337, 0.0, 0.0, 4.5, 0.0, 0.0, -0.582386, 0.0, 0.0, -0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.50145, 0.0, 0.0, 3.18198, 0.0, 0.0, -0.411809, 0.0, 0.0, -0.152674, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.866861, 0.0, -0.866861, 1.83712, 0.0, 1.83712, -0.237758, 0.0, -0.237758, -0.0881464, 0.0, -0.0881464, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.50145, 0.0, 0.0, 3.18198, 0.0, 0.0, -0.411809, 0.0, 0.0, -0.152674, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.12337, 0.0, 0.0, 4.5, 0.0, 0.0, -0.582386, 0.0, 0.0, -0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.764602, 0.0, 0.0, -3.38335, 0.0, 0.0, 9.29516, 0.0, 0.0, -1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.540655, 0.0, 0.0, -2.39239, 0.0, 0.0, 6.57267, 0.0, 0.0, -0.963015, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.312148, 0.0, -0.312148, -1.38125, 0.0, -1.38125, 3.79473, 0.0, 3.79473, -0.555997, 0.0, -0.555997, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.540655, 0.0, 0.0, -2.39239, 0.0, 0.0, 6.57267, 0.0, 0.0, -0.963015, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.764602, 0.0, 0.0, -3.38335, 0.0, 0.0, 9.29516, 0.0, 0.0, -1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.436072, 0.0, 0.0, -1.32747, 0.0, 0.0, -4.93677, 0.0, 0.0, 15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.30835, 0.0, 0.0, -0.938666, 0.0, 0.0, -3.49082, 0.0, 0.0, 10.8685, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.178026, 0.0, -0.178026, -0.541939, 0.0, -0.541939, -2.01543, 0.0, -2.01543, 6.27495, 0.0, 6.27495, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.30835, 0.0, 0.0, -0.938666, 0.0, 0.0, -3.49082, 0.0, 0.0, 10.8685, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.436072, 0.0, 0.0, -1.32747, 0.0, 0.0, -4.93677, 0.0, 0.0, 15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        this.dipoleOpZ = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.744936, 0.0, 0.0, 0.298311, 0.0, 0.0, 0.175852, 0.0, 0.0, 0.120495, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -3.0, 0.0, 0.0, 1.76947, 0.0, 0.0, 0.740323, 0.0, 0.0, 0.446841, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.541788, 0.0, 0.0, -7.34847, 0.0, 0.0, 3.15772, 0.0, 0.0, 1.30457, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.220722, 0.0, 0.0, 1.41077, 0.0, 0.0, -13.4164, 0.0, 0.0, 4.91777, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.131652, 0.0, 0.0, 0.559805, 0.0, 0.0, 2.65597, 0.0, 0.0, -21.2132, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.0, 0.0, 0.0, 0.764602, 0.0, 0.0, 0.0, 0.0, 0.436072, 0.0, 0.0, 0.0, 0.744936, -3.0, 0.541788, 0.220722, 0.131652, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.45185, 0.0, 0.0, 0.0, 0.0, 0.882887, 0.0, 0.0, 0.0, 0.0, 0.503533, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.0, 0.0, 0.0, 0.764602, 0.0, 0.0, 0.0, 0.0, 0.436072, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -4.5, 0.0, 0.0, 0.0, 0.0, 3.38335, 0.0, 0.0, 0.0, 0.0, 1.32747, 0.0, 0.0, 0.0, 0.298311, 1.76947, -7.34847, 1.41077, 0.559805, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -5.19615, 0.0, 0.0, 0.0, 0.0, 3.90676, 0.0, 0.0, 0.0, 0.0, 1.53283, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -4.5, 0.0, 0.0, 0.0, 0.0, 3.38335, 0.0, 0.0, 0.0, 0.0, 1.32747, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.582386, 0.0, 0.0, 0.0, 0.0, -9.29516, 0.0, 0.0, 0.0, 0.0, 4.93677, 0.0, 0.0, 0.0, 0.175852, 0.740323, 3.15772, -13.4164, 2.65597, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.672481, 0.0, 0.0, 0.0, 0.0, -10.7331, 0.0, 0.0, 0.0, 0.0, 5.70049, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.582386, 0.0, 0.0, 0.0, 0.0, -9.29516, 0.0, 0.0, 0.0, 0.0, 4.93677, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.215914, 0.0, 0.0, 0.0, 0.0, 1.36191, 0.0, 0.0, 0.0, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.120495, 0.446841, 1.30457, 4.91777, -21.2132, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.249316, 0.0, 0.0, 0.0, 0.0, 1.5726, 0.0, 0.0, 0.0, 0.0, -17.7482, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.215914, 0.0, 0.0, 0.0, 0.0, 1.36191, 0.0, 0.0, 0.0, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.0, -4.5, 0.0, 0.0, 0.582386, 0.0, 0.0, 0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.45185, 0.0, 0.0, -5.19615, 0.0, 0.0, 0.672481, 0.0, 0.0, 0.249316, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.12337, 0.0, 0.0, -4.5, 0.0, 0.0, 0.582386, 0.0, 0.0, 0.215914, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.764602, 0.0, 0.0, 3.38335, 0.0, 0.0, -9.29516, 0.0, 0.0, 1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.882887, 0.0, 0.0, 3.90676, 0.0, 0.0, -10.7331, 0.0, 0.0, 1.5726, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.764602, 0.0, 0.0, 3.38335, 0.0, 0.0, -9.29516, 0.0, 0.0, 1.36191, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.436072, 0.0, 0.0, 1.32747, 0.0, 0.0, 4.93677, 0.0, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.503533, 0.0, 0.0, 1.53283, 0.0, 0.0, 5.70049, 0.0, 0.0, -17.7482, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.436072, 0.0, 0.0, 1.32747, 0.0, 0.0, 4.93677, 0.0, 0.0, -15.3704, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        this.scaleValue = -1;
        this.setups = new int[] { 0, 15, 149, 1191, 0, 1, 0, 0, 15, 149, 1191, 2, 1, 0, 0, 15, 149, 1191, 3, 0, 0, 0, 22, 161, 1334, 2, 1, 1, 1, 27, 127, 1381, 2, 1, 3, 1, 27, 127, 1381, 3, 0, 3, 2, 27, 127, 1429, 4, 2, 3, 4, 27, 127, 1429, 3, 2, 3, 4, 27, 127, 1429, 0, 1, 3, 3, 27, 127, 1429, 2, 1, 3, 2, 27, 127, 1429, 2, 2, 3, 5, 27, 127, 1429, 2, 1, 3, 4, 27, 127, 1429, 2, 1, 3, 1, 27, 127, 1572, 2, 1, 4, 2, 38, 109, 1334, 3, 0, 3 };
        this.setupStrings = new String[] { "1s -> 2px", "1s -> 2pz", "1s -> 2p+1", "1s -> 3pz", "2s -> 3pz", "2s -> 3p+1", "2p+1 -> 3s+3dz2", "2pz -> 3d+1", "2pz -> 3dxz", "2px -> 3dxz", "2p+1 -> 3d+1", "3s -> 3dz2+3s", "2pz -> 3dz2", "2s -> 4pz", "2p+1 -> 3d+2", null };
        this.applet = applet;
    }
    
    public void init() {
        this.gray2 = new Color(127, 127, 127);
        System.getProperty("os.name");
        System.getProperty("java.version");
        final int n = 64;
        this.setLayout(new AtomTransLayout());
        (this.cv = new AtomTransCanvas(this)).addComponentListener(this);
        this.cv.addMouseMotionListener(this);
        this.cv.addMouseListener(this);
        this.add(this.cv);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("File");
        menuBar.add(menu);
        menu.add(this.exitItem = this.getMenuItem("Exit"));
        final Menu menu2 = new Menu("View");
        menuBar.add(menu2);
        menu2.add(this.eCheckItem = this.getCheckItem("Energy"));
        this.eCheckItem.setState(true);
        menu2.add(this.xCheckItem = this.getCheckItem("Position"));
        this.xCheckItem.setState(true);
        this.xCheckItem.disable();
        menu2.addSeparator();
        menu2.add(this.colorCheck = this.getCheckItem("Phase as Color"));
        this.colorCheck.setState(true);
        final Menu menu3 = new Menu("Options");
        menuBar.add(menu3);
        (this.restrictItem = this.getCheckItem("Restrict to N1, N2")).setState(true);
        menu3.add(this.dimensionsItem = this.getCheckItem("Show Dimensions"));
        menu3.add(this.axesItem = this.getCheckItem("Show Axes"));
        this.axesItem.setState(true);
        (this.autoZoomItem = this.getCheckItem("Auto Scale")).setState(true);
        (this.animatedZoomItem = this.getCheckItem("Animated Scaling")).setState(true);
        this.setMenuBar(menuBar);
        this.setupChooser = new Choice();
        for (int n2 = 0; this.setupStrings[n2] != null; ++n2) {
            this.setupChooser.add(this.setupStrings[n2]);
        }
        this.setupChooser.addItemListener(this);
        this.add(this.setupChooser);
        (this.sliceChooser = new Choice()).add("No Slicing");
        this.sliceChooser.add("Show X Slice");
        this.sliceChooser.add("Show Y Slice");
        this.sliceChooser.add("Show Z Slice");
        this.sliceChooser.addItemListener(this);
        this.add(this.sliceChooser);
        (this.freqChooser = new Choice()).add("Freq = n=1 <-> n=2");
        this.freqChooser.add("Freq = n=1 <-> n=3");
        this.freqChooser.add("Freq = n=1 <-> n=4");
        this.freqChooser.add("Freq = n=2 <-> n=3");
        this.freqChooser.add("Freq = n=2 <-> n=4");
        this.freqChooser.add("Freq = n=3 <-> n=4");
        this.freqChooser.addItemListener(this);
        (this.dirChooser = new Choice()).add("X Dir");
        this.dirChooser.add("Y Dir");
        this.dirChooser.add("Z Dir");
        this.dirChooser.add("CCW Circular");
        this.dirChooser.add("CW Circular");
        this.dirChooser.addItemListener(this);
        (this.stoppedCheck = new Checkbox("Stopped")).addItemListener(this);
        this.add(this.stoppedCheck);
        this.add(new Label("Simulation Speed", 1));
        this.add(this.speedBar = new Scrollbar(0, 6, 1, 1, 200));
        this.speedBar.addAdjustmentListener(this);
        new Label("Steps", 1);
        (this.stepBar = new Scrollbar(0, 40, 1, 1, 300)).addAdjustmentListener(this);
        new Label("Radiation Intensity", 1);
        (this.strengthBar = new Scrollbar(0, 130, 1, 85, 200)).addAdjustmentListener(this);
        this.add(new Label("Brightness", 1));
        this.add(this.brightnessBar = new Scrollbar(0, 240, 1, 1, 4000));
        this.brightnessBar.addAdjustmentListener(this);
        this.add(new Label("Image Resolution", 1));
        this.add(this.resolutionBar = new Scrollbar(0, n, 2, 20, 240));
        this.resolutionBar.addAdjustmentListener(this);
        new Label("Scale", 1);
        (this.scaleBar = new Scrollbar(0, 75, 1, 5, 1620)).addAdjustmentListener(this);
        this.add(new Label("http://www.falstad.com", 1));
        try {
            final String parameter = this.applet.getParameter("PAUSE");
            if (parameter != null) {
                this.pause = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {}
        this.phaseColors = new PhaseColor[400];
        for (int i = 0; i != 8; ++i) {
            for (int j = 0; j != 50; ++j) {
                this.phaseColors[i * 50 + j] = this.genPhaseColor(i, Math.atan(j / 50.0));
            }
        }
        this.purple = new Color(192, 60, 206);
        this.slicerPoints = new int[2][10];
        this.sliceFaces = new double[4][3];
        this.rotmatrix = new double[9];
        final double[] rotmatrix = this.rotmatrix;
        final int n3 = 0;
        final double[] rotmatrix2 = this.rotmatrix;
        final int n4 = 4;
        final double[] rotmatrix3 = this.rotmatrix;
        final int n5 = 8;
        final double n6 = 1.0;
        rotmatrix3[n5] = n6;
        rotmatrix[n3] = (rotmatrix2[n4] = n6);
        this.rotate(0.0, -1.5707963267948966);
        this.xpoints = new int[4];
        this.ypoints = new int[4];
        this.setupSimpson();
        this.setupStates();
        this.doSetup();
        this.random = new Random();
        this.reinit();
        this.cv.setBackground(Color.black);
        this.cv.setForeground(Color.white);
        this.resize(580, 500);
        this.handleResize();
        final Dimension size = this.getSize();
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        this.show();
    }
    
    void setupStates() {
        final int n = 5;
        this.stateCount = n * (n + 1) * (2 * n + 1) / 6;
        this.states = new BasisState[this.stateCount];
        int n2 = 1;
        int l = 0;
        int m = 0;
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState[] states = this.states;
            final int n3 = i;
            final BasisState basisState = new BasisState();
            states[n3] = basisState;
            final State state = (State)basisState;
            state.index = i;
            state.elevel = -1.0 / (2.0 * n2 * n2);
            ((BasisState)state).n = n2;
            if ((((BasisState)state).m = m) < (((BasisState)state).l = l)) {
                ++m;
            }
            else if (++l < n2) {
                m = -l;
            }
            else {
                ++n2;
                m = (l = 0);
            }
        }
    }
    
    MenuItem getMenuItem(final String s) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.addActionListener(this);
        return menuItem;
    }
    
    CheckboxMenuItem getCheckItem(final String s) {
        final CheckboxMenuItem checkboxMenuItem = new CheckboxMenuItem(s);
        checkboxMenuItem.addItemListener(this);
        return checkboxMenuItem;
    }
    
    PhaseColor genPhaseColor(final int n, double n2) {
        n2 += n * 3.141592653589793 / 4.0;
        n2 *= 0.954929658551372;
        final int n3 = (int)n2;
        final double n4 = n2 % 1.0;
        final double n5 = 1.0 - n4;
        PhaseColor phaseColor = null;
        switch (n3) {
            case 0:
            case 6: {
                phaseColor = new PhaseColor(1.0, n4, 0.0);
                break;
            }
            case 1: {
                phaseColor = new PhaseColor(n5, 1.0, 0.0);
                break;
            }
            case 2: {
                phaseColor = new PhaseColor(0.0, 1.0, n4);
                break;
            }
            case 3: {
                phaseColor = new PhaseColor(0.0, n5, 1.0);
                break;
            }
            case 4: {
                phaseColor = new PhaseColor(n4, 0.0, 1.0);
                break;
            }
            case 5: {
                phaseColor = new PhaseColor(1.0, 0.0, n5);
                break;
            }
        }
        return phaseColor;
    }
    
    void setupSimpson() {
        this.sampleCount = 15;
        this.sampleMult = new int[this.sampleCount];
        for (int i = 1; i < this.sampleCount; i += 2) {
            this.sampleMult[i] = 4;
            this.sampleMult[i + 1] = 2;
        }
        this.sampleMult[0] = (this.sampleMult[this.sampleCount - 1] = 1);
    }
    
    void handleResize() {
        this.reinit();
    }
    
    void reinit() {
        this.setResolution();
        final Dimension size = this.cv.getSize();
        this.winSize = size;
        final Dimension dimension = size;
        if (this.winSize.width == 0) {
            return;
        }
        this.dbimage = this.createImage(dimension.width, dimension.height);
        this.setupDisplay();
    }
    
    void createPhasors() {
        this.phasorCount = 0;
        if (this.viewStates == null) {
            return;
        }
        final int n = this.viewStates.height / 4;
        int n2 = 0;
        int y = this.viewStates.y;
        int n3 = 1;
        int n4 = 0;
        int n5 = 0;
        final int n6 = this.viewStates.height / 5;
        this.phasorCount = 32;
        this.phasors = new Phasor[this.phasorCount];
        int n7 = 0;
        for (int i = 0; i != this.stateCount; ++i) {
            if (n4 < 3) {
                final Phasor[] phasors = this.phasors;
                final int n8 = n7;
                final Phasor phasor = new Phasor(n2, y, n6, n6);
                phasors[n8] = phasor;
                final Phasor phasor2 = phasor;
                ++n7;
                phasor2.state = this.states[i];
                n2 += n6;
            }
            if (++n5 > n4) {
                n2 += n6;
                n5 = -(++n4);
                if (n4 >= n3) {
                    n2 = 0;
                    y += n6;
                    ++n3;
                    n5 = (n4 = 0);
                }
            }
        }
        this.createOrbitals();
    }
    
    void setupDisplay() {
        if (this.winSize == null) {
            return;
        }
        final int n = (this.viewPotential == null) ? 50 : this.viewPotential.height;
        final int n2 = (this.viewStates == null) ? 64 : this.viewStates.height;
        final View viewX = null;
        this.viewStates = viewX;
        this.viewPotential = viewX;
        this.viewX = viewX;
        this.viewList = new View[10];
        int viewCount = 0;
        if (this.eCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewPotential = new View());
        }
        if (this.xCheckItem.getState()) {
            this.viewList[viewCount++] = (this.viewX = new View());
        }
        this.viewList[viewCount++] = (this.viewStates = new View());
        this.viewCount = viewCount;
        int viewCount2 = this.viewCount;
        int height = this.winSize.height;
        if (n > 0 && this.viewPotential != null) {
            --viewCount2;
            height -= n;
        }
        if (n2 > 0 && this.viewStates != null) {
            --viewCount2;
            height -= n2;
        }
        final int n3 = height - 8 * (this.viewCount - 1);
        int n4 = 0;
        for (int i = 0; i != this.viewCount; ++i) {
            final View view = this.viewList[i];
            int height2 = (viewCount2 == 0) ? n3 : (n3 / viewCount2);
            if (view == this.viewPotential && n > 0) {
                height2 = n;
            }
            else if (view == this.viewStates && n2 > 0) {
                height2 = n2;
            }
            if ((view.paneY = n4) > 0) {
                n4 += 4;
            }
            view.x = 0;
            view.width = this.winSize.width;
            view.y = n4;
            view.height = height2;
            n4 += height2 + 4;
        }
        this.setSubViews();
    }
    
    void setSubViews() {
        this.pixels = null;
        if (this.useBufferedImage) {
            try {
                final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
                final Class<?> forName2 = Class.forName("java.awt.image.DataBufferInt");
                final Class<?> forName3 = Class.forName("java.awt.image.Raster");
                this.memimage = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(this.viewX.width), new Integer(this.viewX.height), new Integer(1));
                this.pixels = (int[])forName2.getMethod("getData", (Class[])null).invoke(forName3.getMethod("getDataBuffer", (Class<?>[])null).invoke(forName.getMethod("getRaster", (Class[])null).invoke(this.memimage, (Object[])null), (Object[])null), (Object[])null);
            }
            catch (Exception ex) {
                System.out.println("BufferedImage failed");
            }
        }
        if (this.pixels == null) {
            this.pixels = new int[this.viewX.width * this.viewX.height];
            for (int i = 0; i != this.viewX.width * this.viewX.height; ++i) {
                this.pixels[i] = -16777216;
            }
            (this.imageSource = new MemoryImageSource(this.viewX.width, this.viewX.height, this.pixels, 0, this.viewX.width)).setAnimated(true);
            this.imageSource.setFullBufferUpdates(true);
            this.memimage = this.cv.createImage(this.imageSource);
        }
        final int n = (int)(this.min(this.viewX.width, this.viewX.height) / 3.0);
        this.viewAxes = new Rectangle(this.viewX.x + this.winSize.width - n, this.viewX.y, n, n);
        this.viewField = new Rectangle(this.viewX.x + this.winSize.width - n, this.viewX.y + this.viewX.height - n, n, n);
        this.createPhasors();
    }
    
    int getTermWidth() {
        return 8;
    }
    
    void rotate(final double n, final double n2) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final double cos2 = Math.cos(n2);
        final double sin2 = Math.sin(n2);
        final double[] array = { cos, -sin * sin2, cos2 * sin, 0.0, cos2, sin2, -sin, -cos * sin2, cos * cos2 };
        final double[] rotmatrix = this.rotmatrix;
        this.rotmatrix = new double[9];
        for (int i = 0; i != 3; ++i) {
            for (int j = 0; j != 3; ++j) {
                double n3 = 0.0;
                for (int k = 0; k != 3; ++k) {
                    n3 += rotmatrix[k + i * 3] * array[j + k * 3];
                }
                this.rotmatrix[j + i * 3] = n3;
            }
        }
    }
    
    double max(final double n, final double n2) {
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    double min(final double n, final double n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    void setResolution() {
        final int gridSizeX = this.gridSizeX;
        final int n = this.resolutionBar.getValue() & 0xFFFFFFFE;
        this.gridSizeY = n;
        this.gridSizeX = n;
        if (gridSizeX == this.gridSizeX) {
            return;
        }
        this.dataSize = this.gridSizeX * 4;
        System.out.print("setResolution " + this.dataSize + " " + this.gridSizeX + "\n");
        this.resadj = 50.0 / this.dataSize;
        this.precomputeAll();
    }
    
    void computeView(final double n) {
        final boolean state = this.colorCheck.getState();
        for (int i = 0; i != this.orbCount; ++i) {
            this.orbitals[i].setupFrame(n);
        }
        final double n2 = 1.0 / this.zoom;
        final double[] rotmatrix = this.rotmatrix;
        final double n3 = this.viewX.width / this.viewX.height;
        final double n4 = this.dataSize / 2.0;
        final double n5 = this.dataSize / 2.0;
        final double n6 = this.dataSize / 2.0;
        double n7 = n2;
        double n8 = n2;
        if (n3 < 1.0) {
            n8 /= n3;
        }
        else {
            n7 *= n3;
        }
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        double n9 = 0.0;
        for (int j = 0; j != this.orbCount; ++j) {
            final double boundRadius = this.orbitals[j].getBoundRadius(this.colorMult);
            if (boundRadius > n9) {
                n9 = boundRadius;
            }
        }
        final double n10 = n9 * n9;
        for (int k = 0; k != this.gridSizeX; ++k) {
            for (int l = 0; l != this.gridSizeY; ++l) {
                final double n11 = (2 * k / this.gridSizeX - 1.0) * n7;
                final double n12 = -(2 * l / this.gridSizeY - 1.0) * n8;
                final double n13 = rotmatrix[2] * AtomTransFrame.viewDistance;
                final double n14 = rotmatrix[5] * AtomTransFrame.viewDistance;
                final double n15 = rotmatrix[8] * AtomTransFrame.viewDistance;
                final double n16 = rotmatrix[0] * n11 + rotmatrix[1] * n12 - rotmatrix[2];
                final double n17 = rotmatrix[3] * n11 + rotmatrix[4] * n12 - rotmatrix[5];
                final double n18 = rotmatrix[6] * n11 + rotmatrix[7] * n12 - rotmatrix[8];
                final double sqrt = Math.sqrt(n11 * n11 + n12 * n12 + 1.0);
                float n19 = 0.0f;
                float n20 = 0.0f;
                float n21 = 0.0f;
                final double n22 = n16 * n16 + n17 * n17 + n18 * n18;
                final double n23 = 2.0 * (n16 * n13 + n17 * n14 + n18 * n15);
                final double n24 = n23 * n23 - 4.0 * n22 * (n13 * n13 + n14 * n14 + n15 * n15 - n10);
                if (n24 < 0.0) {
                    this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                }
                else {
                    final double sqrt2 = Math.sqrt(n24);
                    double n25 = (-n23 - sqrt2) / (2.0 * n22);
                    double n26 = (-n23 + sqrt2) / (2.0 * n22);
                    if (selectedIndex != 0) {
                        double n27 = -100.0;
                        switch (selectedIndex) {
                            case 1: {
                                n27 = (this.sliceval - n13) / n16;
                                break;
                            }
                            case 2: {
                                n27 = (this.sliceval - n14) / n17;
                                break;
                            }
                            case 3: {
                                n27 = (this.sliceval - n15) / n18;
                                break;
                            }
                        }
                        if (n27 < n25 || n27 > n26) {
                            this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                        n26 = (n25 = n27);
                    }
                    final double n28 = (n26 - n25) / (this.sampleCount - 1);
                    double n29 = (n26 - n25) * sqrt;
                    int n30 = this.sampleCount - 1;
                    int n31 = 1;
                    double n32 = (n13 + n16 * n25) * n4;
                    double n33 = (n14 + n17 * n25) * n5;
                    double n34 = (n15 + n18 * n25) * n6;
                    if (selectedIndex != 0) {
                        n30 = 1;
                        n31 = 0;
                        n29 = 2.0;
                        if (n32 > n4 || n33 > n5 || n34 > n6 || n32 < -n4 || n33 < -n5 || n34 < -n6) {
                            this.fillSquare(k, l, 0.0f, 0.0f, 0.0f);
                            continue;
                        }
                    }
                    final double n35 = n16 * (n28 * n4);
                    final double n36 = n17 * (n28 * n5);
                    final double n37 = n18 * (n28 * n6);
                    final int n38 = this.dataSize / 2;
                    while (n31 < n30) {
                        final double sqrt3 = Math.sqrt(n32 * n32 + n33 * n33 + n34 * n34);
                        final double n39 = n34 / sqrt3;
                        final int n40 = (int)sqrt3;
                        final int n41 = (int)(n39 * n38 + n38);
                        float n42 = 0.0f;
                        float n43 = 0.0f;
                        this.calcPhiComponent(n32, n33);
                        for (int n44 = 0; n44 != this.orbCount; ++n44) {
                            this.orbitals[n44].computePoint(n40, n41);
                            n42 += this.funcr;
                            n43 += this.funci;
                        }
                        if (state) {
                            final double n45 = (n42 * n42 + n43 * n43) * this.sampleMult[n31];
                            final PhaseColor phaseColor = this.getPhaseColor(n42, n43);
                            n19 += (float)(phaseColor.r * n45);
                            n20 += (float)(phaseColor.g * n45);
                            n21 += (float)(phaseColor.b * n45);
                        }
                        else {
                            n20 = (n19 = (n21 += (n42 * n42 + n43 * n43) * this.sampleMult[n31]));
                        }
                        n32 += n35;
                        n33 += n36;
                        n34 += n37;
                        ++n31;
                    }
                    this.fillSquare(k, l, (float)(n19 * (n29 / n31)), (float)(n20 * (n29 / n31)), (float)(n21 * (n29 / n31)));
                }
            }
        }
    }
    
    void fillSquare(final int n, final int n2, float n3, float n4, float n5) {
        final int width = this.viewX.width;
        final int height = this.viewX.height;
        final int n6 = n * width / this.gridSizeX;
        final int n7 = n2 * height / this.gridSizeY;
        final int n8 = (n + 1) * width / this.gridSizeX;
        final int n9 = (n2 + 1) * height / this.gridSizeY;
        n3 *= (float)this.colorMult;
        n4 *= (float)this.colorMult;
        n5 *= (float)this.colorMult;
        if (n3 == 0.0f && n4 == 0.0f && n5 == 0.0f) {
            final int n10 = n9 * this.viewX.width;
            for (int i = n6; i < n8; ++i) {
                for (int j = n7 * this.viewX.width; j < n10; j += this.viewX.width) {
                    this.pixels[i + j] = -16777216;
                }
            }
            return;
        }
        final double max = this.max(n3, this.max(n4, n5));
        if (max > 255.0) {
            final double n11 = max / 255.0;
            n3 /= (float)n11;
            n4 /= (float)n11;
            n5 /= (float)n11;
        }
        final int n12 = -16777216 + ((int)n3 << 16) | (int)n4 << 8 | (int)n5;
        final int n13 = n9 * this.viewX.width;
        for (int k = n6; k < n8; ++k) {
            for (int l = n7 * this.viewX.width; l < n13; l += this.viewX.width) {
                this.pixels[k + l] = n12;
            }
        }
    }
    
    PhaseColor getPhaseColor(final double n, final double n2) {
        if (n == 0.0 && n2 == 0.0) {
            return this.phaseColors[0];
        }
        int n3;
        double n4;
        if (n2 >= 0.0) {
            if (n >= 0.0) {
                if (n >= n2) {
                    n3 = 0;
                    n4 = n2 / n;
                }
                else {
                    n3 = 50;
                    n4 = 1.0 - n / n2;
                }
            }
            else if (-n <= n2) {
                n3 = 100;
                n4 = -n / n2;
            }
            else {
                n3 = 150;
                n4 = 1.0 + n2 / n;
            }
        }
        else if (n <= 0.0) {
            if (n2 >= n) {
                n3 = 200;
                n4 = n2 / n;
            }
            else {
                n3 = 250;
                n4 = 1.0 - n / n2;
            }
        }
        else if (-n2 >= n) {
            n3 = 300;
            n4 = -n / n2;
        }
        else {
            n3 = 350;
            n4 = 1.0 + n2 / n;
        }
        return this.phaseColors[n3 + (int)(n4 * 49.0)];
    }
    
    void calcPhiComponent(final double n, final double n2) {
        if (n == 0.0 && n2 == 0.0) {
            this.phiIndex = 0;
            return;
        }
        int n3;
        double n4;
        if (n2 >= 0.0) {
            if (n >= 0.0) {
                if (n >= n2) {
                    n3 = 0;
                    n4 = n2 / n;
                }
                else {
                    n3 = 1;
                    n4 = 1.0 - n / n2;
                }
            }
            else if (-n <= n2) {
                n3 = 2;
                n4 = -n / n2;
            }
            else {
                n3 = 3;
                n4 = 1.0 + n2 / n;
            }
        }
        else if (n <= 0.0) {
            if (n2 >= n) {
                n3 = 4;
                n4 = n2 / n;
            }
            else {
                n3 = 5;
                n4 = 1.0 - n / n2;
            }
        }
        else if (-n2 >= n) {
            n3 = 6;
            n4 = -n / n2;
        }
        else {
            n3 = 7;
            n4 = 1.0 + n2 / n;
        }
        this.phiIndex = n3 * (this.dataSize + 1) + (int)(n4 * this.dataSize);
    }
    
    double getScaleRadius(final int n) {
        final int n2 = 0;
        final double n3 = -n * n * 2;
        return 0.5 * (-n3 + Math.sqrt(n3 * n3 - 4.0 * (n2 * (n2 + 1) * n * n)));
    }
    
    void setScale(final int n) {
        if (this.manualScale || !this.autoZoomItem.getState()) {
            return;
        }
        final int n2 = (int)(this.getScaleRadius(n) * 3.15);
        final int value = this.scaleBar.getValue();
        if (value != n2) {
            int n3 = n2 - value;
            if (n3 < -5 || n3 > 5) {
                n3 /= 3;
                if (n3 < -50) {
                    n3 = -50;
                }
                if (n3 > 50) {
                    n3 = 50;
                }
            }
            int value2 = value + n3;
            if (!this.animatedZoomItem.getState()) {
                value2 = n2;
            }
            this.scaleBar.setValue(value2);
            this.precomputeAll();
        }
    }
    
    void precomputeAll() {
        for (int i = 0; i != this.orbCount; ++i) {
            this.orbitals[i].precompute();
        }
    }
    
    int sign(final double n) {
        if (n < 0.0) {
            return -1;
        }
        return 1;
    }
    
    public void paint(final Graphics graphics) {
        this.cv.repaint();
    }
    
    public void updateAtomTrans(final Graphics graphics) {
        int n = 1;
        int scale = 1;
        switch (this.freqChooser.getSelectedIndex()) {
            case 0: {
                n = 1;
                scale = 2;
                break;
            }
            case 1: {
                n = 1;
                scale = 3;
                break;
            }
            case 2: {
                n = 1;
                scale = 4;
                break;
            }
            case 3: {
                n = 2;
                scale = 3;
                break;
            }
            case 4: {
                n = 2;
                scale = 4;
                break;
            }
            case 5: {
                n = 3;
                scale = 4;
                break;
            }
        }
        if (this.winSize == null || this.winSize.width == 0) {
            return;
        }
        final Graphics graphics2 = this.dbimage.getGraphics();
        graphics2.setColor(this.cv.getBackground());
        graphics2.fillRect(0, 0, this.winSize.width, this.winSize.height);
        graphics2.setColor(this.cv.getForeground());
        if (this.fontMetrics == null) {
            this.fontMetrics = graphics2.getFontMetrics();
        }
        boolean b = false;
        double n2 = 0.0;
        this.normalize();
        for (int i = 0; i != this.stateCount; ++i) {
            n2 += ((Complex)this.states[i]).magSquared();
        }
        double n3 = 1.0 / n2;
        if (n2 == 0.0) {
            n3 = 0.0;
        }
        final double sqrt = Math.sqrt(n3);
        this.setScale(scale);
        final boolean b2 = this.sliceChooser.getSelectedIndex() != 0;
        this.zoom = (b2 ? 8.0 : 16.55);
        this.colorMult = Math.exp(this.brightnessBar.getValue() / 100.0);
        this.computeView(sqrt);
        for (int j = 1; j != this.viewCount; ++j) {
            graphics2.setColor((j == this.selectedPaneHandle) ? Color.yellow : Color.gray);
            graphics2.drawLine(0, this.viewList[j].paneY, this.winSize.width, this.viewList[j].paneY);
        }
        if (this.viewPotential != null) {
            final double n4 = this.viewPotential.height * 1.9;
            graphics2.setColor(Color.darkGray);
            for (int k = 1; k != 16; ++k) {
                final int n5 = this.viewPotential.y - (int)(n4 * (-1.0 / (2.0 * k * k)));
                graphics2.drawLine(0, n5, this.winSize.width, n5);
            }
            final double scaler = this.getScaler();
            graphics2.setColor(Color.white);
            int n6 = -1;
            int n7 = -1;
            final int n8 = this.viewPotential.y + this.viewPotential.height - 1;
            for (int l = 0; l != this.winSize.width; ++l) {
                double n9 = (l - this.winSize.width / 2) * scaler;
                if (n9 < 0.0) {
                    n9 = -n9;
                }
                if (n9 < 0.001) {
                    n9 = 0.001;
                }
                final int n10 = this.viewPotential.y - (int)(n4 * (-1.0 / n9));
                if (n10 > n8) {
                    if (n6 != -1) {
                        graphics2.drawLine(n6, n7, n6, n8);
                        n6 = -1;
                    }
                }
                else if (n6 == -1 && l > 0) {
                    graphics2.drawLine(l, n8, l, n10);
                    n6 = l;
                    n7 = n10;
                }
                else {
                    if (n6 != -1) {
                        graphics2.drawLine(n6, n7, l, n10);
                    }
                    n6 = l;
                    n7 = n10;
                }
            }
            if (n2 != 0.0) {
                double n11 = 0.0;
                for (int n12 = 0; n12 != this.stateCount; ++n12) {
                    final BasisState basisState = this.states[n12];
                    n11 += ((Complex)basisState).magSquared() * n3 * ((State)basisState).elevel;
                }
                final int n13 = this.viewPotential.y - (int)(n4 * n11);
                graphics2.setColor(Color.red);
                graphics2.drawLine(0, n13, this.winSize.width, n13);
            }
            if (this.selectedState != null && !this.dragging) {
                graphics2.setColor(Color.yellow);
                final int n14 = this.viewPotential.y - (int)(n4 * this.selectedState.elevel);
                graphics2.drawLine(0, n14, this.winSize.width, n14);
            }
        }
        if (this.imageSource != null) {
            this.imageSource.newPixels();
        }
        graphics2.drawImage(this.memimage, this.viewX.x, this.viewX.y, null);
        graphics2.setColor(Color.white);
        if (b2) {
            this.drawCube(graphics2, false);
        }
        if (this.axesItem.getState()) {
            this.drawAxes(graphics2);
        }
        graphics2.setColor(Color.yellow);
        if (this.selectedState != null) {
            this.centerString(graphics2, this.selectedState.getText(), this.viewX.y + this.viewX.height - 5);
        }
        else if (this.dimensionsItem.getState()) {
            final double n15 = this.winSize.width * this.getScaler() * 52.9463;
            final double n16 = 91.1763 / (1.0 / (n * n) - 1.0 / (scale * scale)) + 0.5;
            final double n17 = 3.3356 * n16;
            double n18 = 0.0;
            if (n2 != 0.0) {
                for (int n19 = 0; n19 != this.stateCount; ++n19) {
                    final BasisState basisState2 = this.states[n19];
                    n18 += ((Complex)basisState2).magSquared() * n3 * ((State)basisState2).elevel;
                }
            }
            final double n20 = (int)(27.2 * n18 * 10.0) / 10.0;
            this.centerString(graphics2, "Screen width = " + (int)n15 + " pm, " + "Wavelength = " + (int)n16 + " nm", this.viewX.y + this.viewX.height - 25);
            this.centerString(graphics2, "Period = " + (int)n17 + " attoseconds, " + "<E> = " + n20 + " eV", this.viewX.y + this.viewX.height - 5);
        }
        if (this.viewStates != null) {
            this.drawPhasors(graphics2, this.viewStates);
            this.drawTransitions(graphics2, this.viewStates, n, scale);
        }
        final long n21 = 24 * this.speedBar.getValue();
        final boolean b3 = this.stoppedCheck.getState() || this.dragging;
        if (b3) {
            this.lastFrameTime = 0L;
        }
        else {
            b = false;
        }
        if (this.newcoef == null) {
            this.newcoef = new Complex[this.stateCount];
            for (int n22 = 0; n22 != this.stateCount; ++n22) {
                this.newcoef[n22] = new Complex();
            }
        }
        final double n23 = Math.exp(this.stepBar.getValue() / 20.0) * 0.02;
        final double n24 = Math.exp(this.strengthBar.getValue() / 20.0) / 409500.0;
        this.freq = ((State)this.getState(n, 0, 0)).elevel - ((State)this.getState(scale, 0, 0)).elevel;
        double[] array = null;
        boolean b4 = false;
        int n25 = 1;
        switch (this.dirChooser.getSelectedIndex()) {
            case 0: {
                array = this.dipoleOpX;
                break;
            }
            case 1: {
                array = this.dipoleOpY;
                break;
            }
            case 2: {
                array = this.dipoleOpZ;
                break;
            }
            case 3: {
                b4 = true;
                n25 = -1;
                break;
            }
            case 4: {
                b4 = true;
                break;
            }
        }
        final Complex complex = new Complex();
        final Complex complex2 = new Complex();
        final double n26 = ((State)this.getState(n, 0, 0)).elevel - 0.01;
        int n27 = 1;
        while (!b3) {
            final double n28 = n24 * Math.sin(this.t * this.freq) * n23;
            final double n29 = n24 * Math.cos(this.t * this.freq) * n23;
            this.freqPhase = this.t * this.freq % 6.283185307179586;
            for (int n30 = 0; n30 != this.stateCount; ++n30) {
                final BasisState basisState3 = this.states[n30];
                if (this.restrictItem.getState() && basisState3.n != n && basisState3.n != scale) {
                    this.newcoef[n30].set(0.0);
                }
                else {
                    complex2.set((Complex)basisState3);
                    for (int n31 = 0; n31 != this.stateCount; ++n31) {
                        final BasisState basisState4 = this.states[n31];
                        if (basisState4.n == n || basisState4.n == scale) {
                            if (b4) {
                                complex.set(n29 * this.dipoleOp(n30, n31, this.dipoleOpX), n28 * (-n25 * this.dipoleOp(n30, n31, this.dipoleOpY)));
                            }
                            else {
                                final double n32 = n28 * this.dipoleOp(n30, n31, array);
                                if (array == this.dipoleOpY) {
                                    complex.set(0.0, -n32);
                                }
                                else {
                                    complex.set(n32);
                                }
                            }
                            complex.multI();
                            complex.mult((Complex)basisState4);
                            complex2.addQuick(complex);
                        }
                    }
                    final double n33 = -(((State)basisState3).elevel - n26) * n23;
                    complex2.mult(Math.cos(n33), Math.sin(n33));
                    if (complex2.mag > 1.0E-6) {
                        this.newcoef[n30].set(complex2);
                    }
                    else {
                        this.newcoef[n30].set(0.0);
                    }
                }
            }
            for (int n34 = 0; n34 != this.stateCount; ++n34) {
                ((Complex)this.states[n34]).set(this.newcoef[n34]);
            }
            this.t += n23;
            final long currentTimeMillis = System.currentTimeMillis();
            if (n27 * 1000 >= n21 * (currentTimeMillis - this.lastFrameTime)) {
                break;
            }
            if (currentTimeMillis - this.lastFrameTime > 500L) {
                break;
            }
            ++n27;
        }
        double n35 = 0.0;
        double sin = 0.0;
        double sin2 = 0.0;
        switch (this.dirChooser.getSelectedIndex()) {
            case 0: {
                n35 = Math.sin(this.t * this.freq);
                break;
            }
            case 1: {
                sin = Math.sin(this.t * this.freq);
                break;
            }
            case 2: {
                sin2 = Math.sin(this.t * this.freq);
                break;
            }
            case 3:
            case 4: {
                n35 = Math.cos(this.t * this.freq);
                sin = n25 * Math.sin(this.t * this.freq);
                break;
            }
        }
        this.normalize();
        this.createOrbitals();
        Math.sin(this.t * this.freq);
        graphics2.setColor(Color.darkGray);
        final double n36 = 0.5;
        this.map3d(0.0, 0.0, 0.0, this.xpoints, this.ypoints, 0, this.viewField);
        this.map3d(n36, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewField);
        graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, n36, 0.0, this.xpoints, this.ypoints, 1, this.viewField);
        graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, 0.0, n36, this.xpoints, this.ypoints, 1, this.viewField);
        graphics2.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        graphics2.setColor(Color.white);
        this.map3d(n35 * n36, sin * n36, sin2 * n36, this.xpoints, this.ypoints, 1, this.viewField);
        this.drawArrow(graphics2, null, this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        if (!this.dimensionsItem.getState()) {
            graphics2.drawString("qE", this.viewField.x + (this.viewField.width - this.fontMetrics.stringWidth("qE")) / 2, this.viewField.y + this.viewField.height - 10);
        }
        graphics.drawImage(this.dbimage, 0, 0, this);
        this.lastFrameTime = System.currentTimeMillis();
        if (!b) {
            this.cv.repaint(this.pause);
        }
    }
    
    double dipoleOp(final int n, final int n2, final double[] array) {
        final BasisState basisState = this.states[n];
        final BasisState basisState2 = this.states[n2];
        if (basisState.n == basisState2.n) {
            return 0.0;
        }
        if (basisState.l > 2 || basisState2.l > 2) {
            return 0.0;
        }
        if (basisState.n > 5 || basisState2.n > 5) {
            return 0.0;
        }
        return array[this.getDipoleStateNum(basisState) * 32 + this.getDipoleStateNum(basisState2)];
    }
    
    int getDipoleStateNum(final BasisState basisState) {
        if (basisState.l == 0) {
            return basisState.n - 1;
        }
        if (basisState.l == 1) {
            return 5 + (basisState.n - 2) * 3 + basisState.m + 1;
        }
        return 17 + (basisState.n - 3) * 5 + basisState.m + 2;
    }
    
    double getScaler() {
        double n = this.viewX.width * this.zoom / 2.0;
        final double n2 = this.viewX.height * this.zoom / 2.0;
        final double n3 = this.viewX.width / this.viewX.height;
        if (n3 >= 1.0) {
            n /= n3;
        }
        return 1.0 / (2.0 * n / AtomTransFrame.viewDistance / (50.0 * (this.scaleBar.getValue() / 50.0)));
    }
    
    public void centerString(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, (this.winSize.width - this.fontMetrics.stringWidth(s)) / 2, n);
    }
    
    boolean visibleFace(final int n, final int n2, final int n3) {
        return (n - AtomTransFrame.viewDistance * this.rotmatrix[2]) * n + (n2 - AtomTransFrame.viewDistance * this.rotmatrix[5]) * n2 + (n3 - AtomTransFrame.viewDistance * this.rotmatrix[8]) * n3 < 0.0;
    }
    
    void drawPhasors(final Graphics graphics, final View view) {
        for (int i = 0; i != this.phasorCount; ++i) {
            final Phasor phasor = this.phasors[i];
            final State state = phasor.state;
            final int width = phasor.width;
            final int n = width / 2;
            final int n2 = phasor.x + n;
            final int n3 = phasor.y + n;
            graphics.setColor((this.selectedState == state) ? Color.yellow : ((((Complex)state).mag == 0.0) ? this.gray2 : Color.white));
            graphics.drawOval(n2 - n, n3 - n, width, width);
            final int n4 = (int)(((Complex)state).re * n);
            final int n5 = (int)(-((Complex)state).im * n);
            graphics.drawLine(n2, n3, n2 + n4, n3 + n5);
            graphics.drawLine(n2 + n4 - 1, n3 + n5, n2 + n4 + 1, n3 + n5);
            graphics.drawLine(n2 + n4, n3 + n5 - 1, n2 + n4, n3 + n5 + 1);
        }
    }
    
    void drawTransitions(final Graphics graphics, final View view, final int n, final int n2) {
        final int n3 = this.winSize.width / 2;
        if (this.phasors[0].width * 11 >= n3) {
            return;
        }
        final Complex complex = new Complex();
        final Complex complex2 = new Complex();
        boolean b = false;
        int n4 = 1;
        double[] array = null;
        switch (this.dirChooser.getSelectedIndex()) {
            case 0: {
                array = this.dipoleOpX;
                break;
            }
            case 1: {
                array = this.dipoleOpY;
                break;
            }
            case 2: {
                array = this.dipoleOpZ;
                break;
            }
            case 3: {
                b = true;
                n4 = -1;
                break;
            }
            case 4: {
                b = true;
                break;
            }
        }
        for (int i = 0; i != this.phasorCount; ++i) {
            final Phasor phasor = this.phasors[i];
            final BasisState basisState = (BasisState)phasor.state;
            final int width = phasor.width;
            final int n5 = width / 2;
            final int n6 = phasor.x + n5 + n3;
            final int n7 = phasor.y + n5;
            graphics.setColor((this.selectedState == basisState) ? Color.yellow : ((((Complex)basisState).mag == 0.0) ? this.gray2 : Color.white));
            complex2.set(0.0);
            for (int j = 0; j != this.stateCount; ++j) {
                final BasisState basisState2 = this.states[j];
                final int index = ((State)basisState).index;
                if (b) {
                    complex.set(this.dipoleOp(index, j, this.dipoleOpX) + -n4 * this.dipoleOp(index, j, this.dipoleOpY));
                }
                else {
                    final double dipoleOp = this.dipoleOp(index, j, array);
                    if (array == this.dipoleOpY) {
                        complex.set(0.0, -dipoleOp);
                    }
                    else {
                        complex.set(dipoleOp);
                    }
                }
                complex.multI();
                complex.mult((Complex)basisState2);
                complex2.addQuick(complex);
            }
            int n8 = (int)(Math.sqrt((complex2.re * complex2.re + complex2.im * complex2.im) * 10000.0 + 1.0E-8) * 3.5);
            if (n8 > 255) {
                n8 = 255;
            }
            if (n8 < 0) {
                n8 = 0;
            }
            graphics.setColor(new Color(0, 0, n8));
            graphics.fillOval(n6 - n5, n7 - n5, width, width);
            graphics.setColor((basisState.n == n || basisState.n == n2) ? this.purple : this.gray2);
            graphics.drawOval(n6 - n5, n7 - n5, width, width);
        }
    }
    
    void drawFunction(final Graphics graphics, final View view, final int n, final double[] array, final int n2, final int n3, final boolean b) {
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        final int n8 = this.winSize.width / 3;
        final int n9 = n8 * 4 / 5;
        final int n11;
        final int n10 = (n11 = (b ? (n9 / (n2 - 1)) : (n9 * (n2 / 2) / (n2 - 1)))) + n8 * n;
        for (int i = 0; i != n2; ++i) {
            final int n12 = n9 * i / (n2 - 1);
            final double n13 = array[i];
            final double n14 = n13 * n13;
            if (n14 > n6) {
                n6 = n14;
            }
            final int n15 = n12 - n11;
            n4 += n14 * n15;
            n5 += n14 * n15 * n15;
            n7 += n14;
        }
        final int n16 = n10;
        final double n17 = n4 / n7;
        final double n18 = n5 / n7;
        final double sqrt = Math.sqrt(n6);
        Math.sqrt(n18 - n17 * n17);
        int n19 = 0;
        view.scale = 1.0 / sqrt;
        if (view.scale > 1.0E8) {
            view.scale = 1.0E8;
        }
        graphics.setColor(Color.gray);
        graphics.drawLine(n10, view.y, n10, view.y + view.height);
        final double n20 = 0.9 * view.height;
        final int n21 = view.y + view.height / 2 + (int)n20 / 2;
        final double n22 = n20 * view.scale;
        graphics.setColor(Color.white);
        int n23 = -1;
        for (int j = 0; j != n2; ++j) {
            final int n24 = n9 * j / (n2 - 1) + n8 * n;
            final int n25 = n21 - (int)(n22 * array[j]);
            if (j % n3 == 1) {
                graphics.setColor(Color.gray);
                graphics.drawLine(n24, n21, n24, n21 + 4);
                graphics.setColor(Color.white);
            }
            if (n23 != -1) {
                graphics.drawLine(n23, n19, n24, n25);
            }
            n23 = n24;
            n19 = n25;
        }
        if (n6 > 0.0) {
            final double n26 = n17 + (n16 + 0.5);
            graphics.setColor(Color.red);
            graphics.drawLine((int)n26, view.y, (int)n26, view.y + view.height);
        }
    }
    
    void drawCube(final Graphics graphics, final boolean b) {
        final int selectedIndex = this.sliceChooser.getSelectedIndex();
        int sliceFaceCount = 0;
        for (int i = 0; i != 6; ++i) {
            final int n = (i == 0) ? -1 : ((i == 1) ? 1 : 0);
            final int n2 = (i == 2) ? -1 : ((i == 3) ? 1 : 0);
            final int n3 = (i == 4) ? -1 : ((i == 5) ? 1 : 0);
            if (b || this.visibleFace(n, n2, n3)) {
                final double[] array = new double[3];
                for (int j = 0; j != 4; ++j) {
                    this.computeFace(i, j, array);
                    this.map3d(array[0], array[1], array[2], this.xpoints, this.ypoints, j, this.viewX);
                }
                graphics.setColor(Color.gray);
                graphics.drawPolygon(this.xpoints, this.ypoints, 4);
                if (selectedIndex != 0 && i / 2 != selectedIndex - 1) {
                    if (this.selectedSlice) {
                        graphics.setColor(Color.yellow);
                    }
                    this.computeFace(i, 0, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], sliceFaceCount, this.viewX);
                    this.computeFace(i, 2, array);
                    array[selectedIndex - 1] = this.sliceval;
                    this.map3d(array[0], array[1], array[2], this.slicerPoints[0], this.slicerPoints[1], sliceFaceCount + 1, this.viewX);
                    graphics.drawLine(this.slicerPoints[0][sliceFaceCount], this.slicerPoints[1][sliceFaceCount], this.slicerPoints[0][sliceFaceCount + 1], this.slicerPoints[1][sliceFaceCount + 1]);
                    this.sliceFaces[sliceFaceCount / 2][0] = n;
                    this.sliceFaces[sliceFaceCount / 2][1] = n2;
                    this.sliceFaces[sliceFaceCount / 2][2] = n3;
                    sliceFaceCount += 2;
                }
            }
        }
        this.sliceFaceCount = sliceFaceCount;
    }
    
    void computeFace(final int n, int n2, final double[] array) {
        final int n3 = n >> 1;
        array[n3] = (((n & 0x1) == 0x0) ? -1 : 1);
        for (int i = 0; i != 3; ++i) {
            if (i != n3) {
                array[i] = (((n2 >> 1 ^ (n2 & 0x1)) == 0x0) ? -1 : 1);
                n2 >>= 1;
            }
        }
    }
    
    void drawAxes(final Graphics graphics) {
        graphics.setColor(Color.white);
        final double n = 0.5;
        this.map3d(0.0, 0.0, 0.0, this.xpoints, this.ypoints, 0, this.viewAxes);
        this.map3d(n, 0.0, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "x", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, n, 0.0, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "y", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
        this.map3d(0.0, 0.0, n, this.xpoints, this.ypoints, 1, this.viewAxes);
        this.drawArrow(graphics, "z", this.xpoints[0], this.ypoints[0], this.xpoints[1], this.ypoints[1]);
    }
    
    void drawArrow(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4) {
        this.drawArrow(graphics, s, n, n2, n3, n4, 5);
    }
    
    void drawArrow(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawLine(n, n2, n3, n4);
        final double sqrt = Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        if (sqrt > n5 / 2) {
            final double n6 = (n3 - n) / sqrt;
            final double n7 = (n4 - n2) / sqrt;
            graphics.drawLine(n3, n4, (int)(n7 * n5 - n6 * n5 + n3), (int)(-n6 * n5 - n7 * n5 + n4));
            graphics.drawLine(n3, n4, (int)(-n7 * n5 - n6 * n5 + n3), (int)(n6 * n5 - n7 * n5 + n4));
            if (s != null) {
                graphics.drawString(s, (int)(n3 + n6 * 10.0), (int)(n4 + n7 * 10.0));
            }
        }
    }
    
    void map3d(final double n, final double n2, final double n3, final int[] array, final int[] array2, final int n4, final Rectangle rectangle) {
        final double[] rotmatrix = this.rotmatrix;
        final double n5 = n * rotmatrix[0] + n2 * rotmatrix[3] + n3 * rotmatrix[6];
        final double n6 = n * rotmatrix[1] + n2 * rotmatrix[4] + n3 * rotmatrix[7];
        final double n7 = AtomTransFrame.viewDistance - (n * rotmatrix[2] + n2 * rotmatrix[5] + n3 * rotmatrix[8]);
        double n8 = rectangle.width * this.zoom / 2.0;
        double n9 = rectangle.height * this.zoom / 2.0;
        final double n10 = rectangle.width / rectangle.height;
        if (n10 < 1.0) {
            n9 *= n10;
        }
        else {
            n8 /= n10;
        }
        array[n4] = rectangle.x + rectangle.width / 2 + (int)(n8 * n5 / n7);
        array2[n4] = rectangle.y + rectangle.height / 2 - (int)(n9 * n6 / n7);
    }
    
    void unmap3d(final double[] array, final int n, final int n2, final double[] array2, final double[] array3) {
        double n3 = this.viewX.width * this.zoom / 2.0;
        double n4 = this.viewX.height * this.zoom / 2.0;
        final double n5 = this.viewX.width / this.viewX.height;
        if (n5 < 1.0) {
            n4 *= n5;
        }
        else {
            n3 /= n5;
        }
        final double n6 = (n - (this.viewX.x + this.viewX.width / 2)) / n3;
        final double n7 = -(n2 - (this.viewX.y + this.viewX.height / 2)) / n4;
        final double[] rotmatrix = this.rotmatrix;
        final double n8 = AtomTransFrame.viewDistance * rotmatrix[2];
        final double n9 = AtomTransFrame.viewDistance * rotmatrix[5];
        final double n10 = AtomTransFrame.viewDistance * rotmatrix[8];
        final double n11 = n6 * rotmatrix[0] + n7 * rotmatrix[1] - rotmatrix[2];
        final double n12 = n6 * rotmatrix[3] + n7 * rotmatrix[4] - rotmatrix[5];
        final double n13 = n6 * rotmatrix[6] + n7 * rotmatrix[7] - rotmatrix[8];
        final double n14 = ((array3[0] - n8) * array2[0] + (array3[1] - n9) * array2[1] + (array3[2] - n10) * array2[2]) / (array2[0] * n11 + array2[1] * n12 + array2[2] * n13);
        array[0] = n8 + n11 * n14;
        array[1] = n9 + n12 * n14;
        array[2] = n10 + n13 * n14;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.cv.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.handleResize();
        this.cv.repaint(this.pause);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.exitItem) {
            this.applet.destroyFrame();
            return;
        }
        this.cv.repaint();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource() == this.scaleBar) {
            if (this.scaleBar.getValue() == this.scaleValue) {
                return;
            }
            this.scaleValue = this.scaleBar.getValue();
            this.precomputeAll();
            this.manualScale = true;
        }
        if (adjustmentEvent.getSource() == this.brightnessBar) {
            this.userBrightMult = Math.exp(this.brightnessBar.getValue() / 100.0) / this.bestBrightness;
        }
        if (adjustmentEvent.getSource() == this.resolutionBar) {
            this.setResolution();
        }
        this.setupSimpson();
        this.cv.repaint(this.pause);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.dragging = true;
        this.edit(mouseEvent);
        this.dragX = mouseEvent.getX();
        this.dragY = mouseEvent.getY();
    }
    
    boolean csInRange(final int n, final int n2, final int n3) {
        if (n2 < n3) {
            return n >= n2 - 5 && n <= n3 + 5;
        }
        return n >= n3 - 5 && n <= n2 + 5;
    }
    
    void checkSlice(final int n, final int n2) {
        if (this.sliceChooser.getSelectedIndex() == 0) {
            this.selectedSlice = false;
            return;
        }
        this.selectedSlice = false;
        for (int i = 0; i != this.sliceFaceCount; i += 2) {
            final int n3 = this.slicerPoints[0][i];
            final int n4 = this.slicerPoints[0][i + 1];
            final int n5 = this.slicerPoints[1][i];
            final int n6 = this.slicerPoints[1][i + 1];
            if (this.csInRange(n, n3, n4)) {
                if (this.csInRange(n2, n5, n6)) {
                    double n7;
                    if (n3 == n4) {
                        n7 = Math.abs(n - n3);
                    }
                    else {
                        final double n8 = (n6 - n5) / (n4 - n3);
                        double n9 = n2 - (n5 - n8 * n3 + n8 * n);
                        if (n9 < 0.0) {
                            n9 = -n9;
                        }
                        n7 = n9 / Math.sqrt(1.0 + n8 * n8);
                    }
                    if (n7 < 6.0) {
                        this.selectedSlice = true;
                        this.sliceFace = this.sliceFaces[i / 2];
                        return;
                    }
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.dragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.dragX = x;
        this.dragY = y;
        final int selectedPaneHandle = this.selectedPaneHandle;
        final int selection = this.selection;
        final State selectedState = this.selectedState;
        this.selectedPaneHandle = -1;
        this.selection = 0;
        this.selectedState = null;
        for (int i = 1; i != this.viewCount; ++i) {
            final int n = y - this.viewList[i].paneY;
            if (n >= -3 && n <= 3) {
                this.selectedPaneHandle = i;
                this.selection = 4;
            }
        }
        if (this.viewX != null && this.viewX.inside(x, y)) {
            this.selection = 2;
            this.checkSlice(mouseEvent.getX(), mouseEvent.getY());
        }
        else if (this.viewPotential.contains(x, y)) {
            this.selection = 1;
        }
        else if (this.viewStates != null && this.viewStates.inside(x, y)) {
            this.findPhasor(this.viewStates, x, y);
        }
        if (selectedPaneHandle != this.selectedPaneHandle || selection != this.selection || selectedState != this.selectedState) {
            this.cv.repaint(this.pause);
        }
    }
    
    void findPhasor(final View view, final int n, final int n2) {
        for (int i = 0; i != this.phasorCount; ++i) {
            if (this.phasors[i].inside(n, n2)) {
                this.selectedPhasor = this.phasors[i];
                this.selectedState = this.selectedPhasor.state;
                this.selection = 3;
                return;
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.selection == 3) {
            this.editMagClick();
        }
        if (mouseEvent.getClickCount() == 2 && this.selectedState != null) {
            this.enterSelectedState();
        }
    }
    
    void enterSelectedState() {
        for (int i = 0; i != this.stateCount; ++i) {
            if (this.states[i] != this.selectedState) {
                ((Complex)this.states[i]).set(0.0);
            }
        }
        this.selectedState.convertBasisToDerived();
        ((Complex)this.selectedState).set(1.0);
        this.selectedState.convertDerivedToBasis();
        this.createOrbitals();
        this.cv.repaint(this.pause);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.dragging && this.selection != 0) {
            this.selectedPaneHandle = -1;
            this.selectedState = null;
            this.selectedPhasor = null;
            this.selection = 0;
            this.cv.repaint(this.pause);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        this.dragStartX = x;
        this.dragX = x;
        final int y = mouseEvent.getY();
        this.dragStartY = y;
        this.dragY = y;
        this.dragZoomStart = this.zoom;
        this.dragging = true;
        this.edit(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragging) {
            this.cv.repaint();
        }
        this.dragging = false;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() instanceof CheckboxMenuItem) {
            this.setupDisplay();
            this.cv.repaint(this.pause);
            return;
        }
        if (itemEvent.getItemSelectable() == this.setupChooser) {
            this.doSetup();
        }
        this.cv.repaint(this.pause);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.applet.destroyFrame();
            return true;
        }
        return super.handleEvent(event);
    }
    
    void edit(final MouseEvent mouseEvent) {
        if (this.selection == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        switch (this.selection) {
            case 4: {
                this.editHandle(y);
            }
            case 3: {
                this.editMag(x, y);
            }
            case 2: {
                this.editX(x, y);
            }
            default: {}
        }
    }
    
    void editHandle(final int n) {
        final int n2 = n - this.viewList[this.selectedPaneHandle].paneY;
        final View view = this.viewList[this.selectedPaneHandle - 1];
        final View view2 = this.viewList[this.selectedPaneHandle];
        final int n3 = 10;
        if (view.height + n2 < n3 || view2.height - n2 < n3) {
            return;
        }
        final View view3 = view;
        view3.height += n2;
        final View view4 = view2;
        view4.height -= n2;
        final View view5 = view2;
        view5.y += n2;
        final View view6 = view2;
        view6.paneY += n2;
        this.cv.repaint(this.pause);
        this.setSubViews();
    }
    
    void editX(final int n, final int n2) {
        boolean b = false;
        if (this.selectedSlice) {
            b = true;
        }
        if (!b) {
            this.rotate((this.dragX - n) / 40.0, -(this.dragY - n2) / 40.0);
            this.cv.repaint(this.pause);
            return;
        }
        if (b) {
            final double[] array = new double[3];
            this.unmap3d(array, n, n2, this.sliceFace, this.sliceFace);
            switch (this.sliceChooser.getSelectedIndex()) {
                case 1: {
                    this.sliceval = array[0];
                    break;
                }
                case 2: {
                    this.sliceval = array[1];
                    break;
                }
                case 3: {
                    this.sliceval = array[2];
                    break;
                }
            }
            if (this.sliceval < -0.99) {
                this.sliceval = -0.99;
            }
            if (this.sliceval > 0.99) {
                this.sliceval = 0.99;
            }
            this.cv.repaint(this.pause);
        }
    }
    
    void editMag(int n, int n2) {
        if (this.selectedPhasor == null) {
            return;
        }
        final int n3 = this.selectedPhasor.width / 2;
        final int n4 = this.selectedPhasor.x + n3;
        final int n5 = this.selectedPhasor.y + n3;
        n -= n4;
        n2 -= n5;
        double n6 = Math.sqrt(n * n + n2 * n2) / n3;
        final double atan2 = Math.atan2(-n2, n);
        if (n6 > 10.0) {
            n6 = 0.0;
        }
        if (n6 > 1.0) {
            n6 = 1.0;
        }
        ((Complex)this.selectedState).setMagPhase(n6, atan2);
        this.cv.repaint(this.pause);
        this.createOrbitals();
    }
    
    void editMagClick() {
        if (this.selectedState == null) {
            return;
        }
        if (this.magDragStart < 0.5) {
            ((Complex)this.selectedState).set(1.0, 0.0);
        }
        else {
            ((Complex)this.selectedState).set(0.0);
        }
        this.cv.repaint(this.pause);
        this.createOrbitals();
    }
    
    void createOrbitals() {
        int orbCount = 0;
        boolean b = false;
        final double n = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            final BasisState basisState = this.states[i];
            if (basisState.m == 0) {
                if (((Complex)basisState).mag > n) {
                    ++orbCount;
                    if (basisState.orb == null) {
                        b = true;
                    }
                }
                else if (basisState.orb != null) {
                    b = true;
                }
            }
            else if (basisState.m > 0) {
                if (((Complex)basisState).mag > n || ((Complex)this.getState(basisState.n, basisState.l, -basisState.m)).mag > n) {
                    ++orbCount;
                    if (basisState.orb == null) {
                        b = true;
                    }
                }
                else if (basisState.orb != null) {
                    b = true;
                }
            }
        }
        if (!b) {
            return;
        }
        this.orbCount = orbCount;
        this.orbitals = new Orbital[this.orbCount];
        int n2 = 0;
        for (int j = 0; j != this.stateCount; ++j) {
            final BasisState basisState2 = this.states[j];
            if ((basisState2.m == 0 && ((Complex)basisState2).mag > n) || (basisState2.m > 0 && (((Complex)basisState2).mag > n || ((Complex)this.getState(basisState2.n, basisState2.l, -basisState2.m)).mag > n))) {
                if (basisState2.orb == null) {
                    Orbital orb;
                    if (basisState2.l == 0) {
                        orb = new SOrbital(basisState2);
                    }
                    else if (basisState2.m == 0) {
                        orb = new MZeroOrbital(basisState2);
                    }
                    else {
                        orb = new PairedOrbital(basisState2);
                    }
                    orb.precompute();
                    basisState2.orb = orb;
                }
                this.orbitals[n2++] = basisState2.orb;
            }
            else {
                basisState2.orb = null;
            }
        }
    }
    
    void doClear() {
        for (int i = 0; i != this.stateCount; ++i) {
            ((Complex)this.states[i]).set(0.0);
        }
    }
    
    void normalize() {
        double n = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            n += ((Complex)this.states[i]).magSquared();
        }
        if (n == 0.0) {
            return;
        }
        final double n2 = 1.0 / Math.sqrt(n);
        for (int j = 0; j != this.stateCount; ++j) {
            ((Complex)this.states[j]).mult(n2);
        }
        this.cv.repaint(this.pause);
    }
    
    void maximize() {
        double mag = 0.0;
        for (int i = 0; i != this.stateCount; ++i) {
            if (((Complex)this.states[i]).mag > mag) {
                mag = ((Complex)this.states[i]).mag;
            }
        }
        if (mag == 0.0) {
            return;
        }
        for (int j = 0; j != this.stateCount; ++j) {
            ((Complex)this.states[j]).mult(1.0 / mag);
        }
        this.cv.repaint(this.pause);
    }
    
    BasisState getState(final int n, final int n2, final int n3) {
        final int n4 = n - 1;
        return this.states[n4 * (n4 + 1) * (2 * n4 + 1) / 6 + n2 * n2 + n2 + n3];
    }
    
    int getStateIndex(final int n, final int n2, final int n3) {
        final int n4 = n - 1;
        return n4 * (n4 + 1) * (2 * n4 + 1) / 6 + n2 * n2 + n2 + n3;
    }
    
    void setBrightness(final double n) {
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 1.0E30;
        for (int i = 0; i != this.orbCount; ++i) {
            final Orbital orbital = this.orbitals[i];
            final double brightness = orbital.getBrightness();
            if (brightness < n4) {
                n4 = brightness;
            }
            final BasisState state = orbital.state;
            double n5 = ((Complex)state).magSquared() * n;
            if (orbital.state.m != 0) {
                n5 += ((Complex)this.getState(state.n, state.l, -state.m)).magSquared() * n;
            }
            n3 += n5;
            n2 += n5 * brightness;
        }
        this.bestBrightness = 113.9 / (Math.sqrt(n4) * n3);
        this.brightnessBar.setValue((int)(Math.log(this.bestBrightness * this.userBrightMult) * 100.0));
    }
    
    double plgndr(final int n, final int n2, final double n3) {
        double n4 = 0.0;
        if (n2 < 0 || n2 > n || Math.abs(n3) > 1.0) {
            System.out.print("bad arguments in plgndr\n");
        }
        double n5 = 1.0;
        if (n2 > 0) {
            final double sqrt = Math.sqrt((1.0 - n3) * (1.0 + n3));
            double n6 = 1.0;
            for (int i = 1; i <= n2; ++i) {
                n5 *= -n6 * sqrt;
                n6 += 2.0;
            }
        }
        if (n == n2) {
            return n5;
        }
        double n7 = n3 * (2 * n2 + 1) * n5;
        if (n == n2 + 1) {
            return n7;
        }
        for (int j = n2 + 2; j <= n; ++j) {
            n4 = (n3 * (2 * j - 1) * n7 - (j + n2 - 1) * n5) / (j - n2);
            n5 = n7;
            n7 = n4;
        }
        return n4;
    }
    
    double hypser(int n, int n2, final double n3) {
        double n4 = 1.0;
        double n5 = 1.0;
        for (int i = 1; i <= 1000; ++i) {
            n4 *= n * n3 / (i * n2);
            if (n4 == 0.0) {
                return n5;
            }
            n5 += n4;
            ++n;
            ++n2;
        }
        System.out.print("convergence failure in hypser\n");
        return 0.0;
    }
    
    void setViewMatrix(final double n, final double n2) {
        for (int i = 0; i != 9; ++i) {
            this.rotmatrix[i] = 0.0;
        }
        final double[] rotmatrix = this.rotmatrix;
        final int n3 = 0;
        final double[] rotmatrix2 = this.rotmatrix;
        final int n4 = 4;
        final double[] rotmatrix3 = this.rotmatrix;
        final int n5 = 8;
        final double n6 = 1.0;
        rotmatrix3[n5] = n6;
        rotmatrix[n3] = (rotmatrix2[n4] = n6);
        this.rotate(n, n2);
    }
    
    void setXYViewExact() {
        this.setViewMatrix(0.0, 0.0);
    }
    
    void setXZView() {
        this.setViewMatrix(0.0, -1.2851969946503699);
    }
    
    void setXZViewExact() {
        this.setViewMatrix(0.0, -1.5707963267948966);
    }
    
    void doSetup() {
        final int n = this.setupChooser.getSelectedIndex() * 7;
        this.doClear();
        switch (this.setups[n]) {
            case 0: {
                ((Complex)this.getState(1, 0, 0)).set(1.0);
                break;
            }
            case 1: {
                ((Complex)this.getState(2, 0, 0)).set(1.0);
                break;
            }
            case 2: {
                ((Complex)this.getState(2, 1, 1)).set(1.0);
                break;
            }
            case 3: {
                ((Complex)this.getState(2, 1, 1)).set(0.7071067811865476);
                ((Complex)this.getState(2, 1, -1)).set(-0.7071067811865476);
                break;
            }
            case 4: {
                ((Complex)this.getState(2, 1, 0)).set(1.0);
                break;
            }
            case 5: {
                ((Complex)this.getState(3, 0, 0)).set(1.0);
                break;
            }
            case 6: {
                ((Complex)this.getState(2, 1, -1)).set(1.0);
                break;
            }
        }
        this.speedBar.setValue(this.setups[n + 1]);
        this.strengthBar.setValue(this.setups[n + 2]);
        this.brightnessBar.setValue(this.setups[n + 3]);
        this.dirChooser.select(this.setups[n + 4]);
        this.freqChooser.select(this.setups[n + 6]);
        switch (this.setups[n + 5]) {
            case 1: {
                this.setXZViewExact();
            }
            case 0: {
                this.setXYViewExact();
            }
            case 2: {
                this.setXZView();
            }
            default: {}
        }
    }
    
    static {
        AtomTransFrame.maxModes = 10;
        AtomTransFrame.maxDispCoefs = 8;
        AtomTransFrame.viewDistance = 12;
    }
    
    abstract class Orbital
    {
        BasisState state;
        int n;
        int l;
        int m;
        float reMult;
        float imMult;
        float[] dataR;
        float[] dataTh;
        float[] dataPhiR;
        float[] dataPhiI;
        int dshalf;
        double brightnessCache;
        final int distmult = 4;
        
        Orbital(final BasisState state) {
            this.n = state.n;
            this.l = state.l;
            this.m = state.m;
            this.state = state;
        }
        
        void setupFrame(final double n) {
            this.reMult = (float)(this.state.re * n);
            this.imMult = (float)(this.state.im * n);
        }
        
        double getBoundRadius(final double n) {
            int n2 = 1;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            final double n4 = n3 * n3 * n;
            for (int i = 0; i != AtomTransFrame.this.dataSize; ++i) {
                if (this.dataR[i] * this.dataR[i] * n4 > 32.0) {
                    n2 = i;
                }
            }
            return n2 / (AtomTransFrame.this.dataSize / 2.0);
        }
        
        void precompute() {
            this.dshalf = AtomTransFrame.this.dataSize / 2;
            final double n = AtomTransFrame.this.scaleBar.getValue() / 50.0;
            final int n2 = (this.m < 0) ? (-this.m) : this.m;
            final double pow = Math.pow(-1.0, this.m);
            final double n3 = this.radialNorm(this.n, this.l) * this.sphericalNorm(this.l, n2);
            this.dataR = new float[AtomTransFrame.this.dataSize];
            for (int i = 0; i != AtomTransFrame.this.dataSize; ++i) {
                final double n4 = 2.0 * (i * AtomTransFrame.this.resadj + 1.0E-8) * n / this.n;
                this.dataR[i] = (float)(AtomTransFrame.this.hypser(this.l + 1 - this.n, 2 * this.l + 2, n4) * (Math.pow(n4, this.l) * n3) * Math.exp(-n4 / 2.0));
            }
            if (this.l > 0) {
                this.dataTh = new float[AtomTransFrame.this.dataSize + 1];
                for (int j = 0; j != AtomTransFrame.this.dataSize + 1; ++j) {
                    this.dataTh[j] = (float)(pow * AtomTransFrame.this.plgndr(this.l, n2, (j - this.dshalf) / this.dshalf));
                }
            }
            if (this.m != 0) {
                this.dataPhiR = new float[8 * (AtomTransFrame.this.dataSize + 1)];
                this.dataPhiI = new float[8 * (AtomTransFrame.this.dataSize + 1)];
                int n5 = 0;
                for (int k = 0; k != 8; ++k) {
                    for (int l = 0; l <= AtomTransFrame.this.dataSize; ++l, ++n5) {
                        final double n6 = k * 3.141592653589793 / 4.0 + l * 0.7853981633974483 / AtomTransFrame.this.dataSize;
                        this.dataPhiR[n5] = (float)Math.cos(n6 * n2);
                        this.dataPhiI[n5] = (float)Math.sin(n6 * n2);
                    }
                }
            }
            this.brightnessCache = 0.0;
        }
        
        double getBrightness() {
            if (this.brightnessCache != 0.0) {
                return this.brightnessCache;
            }
            double n = 0.0;
            double n2 = 0.0;
            final double n3 = 1.0 / this.sphericalNorm(this.l, (this.m < 0) ? (-this.m) : this.m);
            for (int i = 0; i != AtomTransFrame.this.dataSize; ++i) {
                final double n4 = this.dataR[i] * n3;
                final double n5 = n4 * n4;
                n += n5 * n5 * i * i;
                n2 += i * i;
            }
            return this.brightnessCache = n / n2;
        }
        
        double radialNorm(final int n, final int n2) {
            this.factorial(n + n2);
            return Math.sqrt(4.0 * this.factorial(n + n2) / (n * n * n * n * this.factorial(n - n2 - 1))) / this.factorial(2 * n2 + 1);
        }
        
        double sphericalNorm(final int n, final int n2) {
            return Math.sqrt((2 * n + 1) * this.factorial(n - n2) / (12.566370614359172 * this.factorial(n + n2)));
        }
        
        double factorial(int i) {
            double n;
            for (n = 1.0; i > 1; n *= i--) {}
            return n;
        }
        
        abstract void computePoint(final int p0, final int p1);
    }
    
    class SOrbital extends Orbital
    {
        SOrbital(final BasisState basisState) {
            super(basisState);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n];
                AtomTransFrame.this.funcr = super.reMult * n3;
                AtomTransFrame.this.funci = super.imMult * n3;
            }
            catch (Exception ex) {
                final AtomTransFrame this$0 = AtomTransFrame.this;
                final AtomTransFrame this$2 = AtomTransFrame.this;
                final float n4 = 0.0f;
                this$2.funci = n4;
                this$0.funcr = n4;
                System.out.println("bad " + n + " " + n2);
            }
        }
    }
    
    class MZeroOrbital extends Orbital
    {
        MZeroOrbital(final BasisState basisState) {
            super(basisState);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n] * super.dataTh[n2];
                AtomTransFrame.this.funcr = n3 * super.reMult;
                AtomTransFrame.this.funci = n3 * super.imMult;
            }
            catch (Exception ex) {
                final AtomTransFrame this$0 = AtomTransFrame.this;
                final AtomTransFrame this$2 = AtomTransFrame.this;
                final float n4 = 0.0f;
                this$2.funci = n4;
                this$0.funcr = n4;
                System.out.println("bad " + n + " " + n2);
            }
        }
    }
    
    class PairedOrbital extends Orbital
    {
        BasisState negstate;
        float f1;
        float f2;
        float f3;
        float f4;
        
        PairedOrbital(final BasisState basisState) {
            super(basisState);
            this.negstate = AtomTransFrame.this.getState(basisState.n, basisState.l, -basisState.m);
        }
        
        void setupFrame(final double n) {
            final double n2 = super.state.re * n;
            final double n3 = super.state.im * n;
            final double n4 = this.negstate.re * n;
            final double n5 = this.negstate.im * n;
            final double pow = Math.pow(-1.0, super.m);
            final double n6 = n2 * pow;
            final double n7 = n3 * pow;
            this.f1 = (float)(n6 + n4);
            this.f2 = (float)(n5 - n7);
            this.f3 = (float)(n7 + n5);
            this.f4 = (float)(n6 - n4);
        }
        
        void computePoint(final int n, final int n2) {
            try {
                final float n3 = super.dataR[n] * super.dataTh[n2];
                final float n4 = super.dataPhiR[AtomTransFrame.this.phiIndex];
                final float n5 = super.dataPhiI[AtomTransFrame.this.phiIndex];
                AtomTransFrame.this.funcr = n3 * (this.f1 * n4 + this.f2 * n5);
                AtomTransFrame.this.funci = n3 * (this.f3 * n4 + this.f4 * n5);
            }
            catch (Exception ex) {
                final AtomTransFrame this$0 = AtomTransFrame.this;
                final AtomTransFrame this$2 = AtomTransFrame.this;
                final float n6 = 0.0f;
                this$2.funci = n6;
                this$0.funcr = n6;
                System.out.println("bad " + n + " " + n2);
            }
        }
    }
    
    class Phasor extends Rectangle
    {
        State state;
        
        Phasor(final int n, final int n2, final int n3, final int n4) {
            super(n, n2, n3, n4);
        }
    }
    
    abstract class State extends Complex
    {
        double elevel;
        int index;
        
        void convertDerivedToBasis() {
        }
        
        void convertBasisToDerived() {
        }
        
        void setBasisActive() {
        }
        
        abstract String getText();
    }
    
    class BasisState extends State
    {
        int n;
        int l;
        int m;
        Orbital orb;
        
        String getText() {
            return "n = " + this.n + ", l = " + this.l + ", m = " + this.m;
        }
    }
    
    class Complex
    {
        public double re;
        public double im;
        public double mag;
        public double phase;
        
        Complex() {
            final double n = 0.0;
            this.phase = n;
            this.mag = n;
            this.im = n;
            this.re = n;
        }
        
        Complex(final double n, final double n2) {
            this.set(n, n2);
        }
        
        double magSquared() {
            return this.mag * this.mag;
        }
        
        void set(final double re, final double im) {
            this.re = re;
            this.im = im;
            this.setMagPhase();
        }
        
        void set(final double re) {
            this.re = re;
            this.im = 0.0;
            this.setMagPhase();
        }
        
        void set(final Complex complex) {
            this.re = complex.re;
            this.im = complex.im;
            this.mag = complex.mag;
            this.phase = complex.phase;
        }
        
        void add(final double n) {
            this.re += n;
            this.setMagPhase();
        }
        
        void add(final double n, final double n2) {
            this.re += n;
            this.im += n2;
            this.setMagPhase();
        }
        
        void add(final Complex complex) {
            this.re += complex.re;
            this.im += complex.im;
            this.setMagPhase();
        }
        
        void addQuick(final Complex complex) {
            this.re += complex.re;
            this.im += complex.im;
        }
        
        void square() {
            this.set(this.re * this.re - this.im * this.im, 2.0 * this.re * this.im);
        }
        
        void mult(final double n, final double n2) {
            this.set(this.re * n - this.im * n2, this.re * n2 + this.im * n);
        }
        
        void mult(final double n) {
            this.re *= n;
            this.im *= n;
            this.mag *= n;
        }
        
        void multI() {
            final double re = this.re;
            this.re = -this.im;
            this.im = re;
        }
        
        void mult(final Complex complex) {
            this.mult(complex.re, complex.im);
        }
        
        void setMagPhase() {
            this.mag = Math.sqrt(this.re * this.re + this.im * this.im);
            this.phase = Math.atan2(this.im, this.re);
        }
        
        void setMagPhase(final double mag, final double phase) {
            this.mag = mag;
            this.phase = phase;
            this.re = mag * Math.cos(phase);
            this.im = mag * Math.sin(phase);
        }
        
        void rotate(final double n) {
            this.setMagPhase(this.mag, (this.phase + n) % 6.283185307179586);
        }
        
        void conjugate() {
            this.im = -this.im;
            this.phase = -this.phase;
        }
    }
    
    class PhaseColor
    {
        public double r;
        public double g;
        public double b;
        
        PhaseColor(final double r, final double g, final double b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
    
    class View extends Rectangle
    {
        double scale;
        int paneY;
        int[] pixels;
        
        View() {
        }
        
        View(final View view) {
            super(view);
        }
    }
}
