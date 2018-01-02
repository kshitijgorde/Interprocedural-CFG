import java.awt.Event;
import java.net.HttpURLConnection;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.net.URLConnection;
import java.util.Vector;
import java.io.DataInputStream;
import java.awt.Container;
import java.util.Date;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.net.InetAddress;
import java.util.StringTokenizer;
import java.util.Locale;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;
import java.awt.Choice;
import java.awt.Color;
import java.util.Properties;
import java.awt.TextField;
import java.awt.Checkbox;
import java.util.Random;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AniS extends Applet implements Runnable
{
    Button startstop;
    boolean sixlegs;
    boolean remoteImages;
    boolean isStartStop;
    boolean isLooping;
    boolean alive;
    boolean start_looping;
    boolean blank_screen;
    Button looprock;
    boolean isLooprock;
    boolean isRocking;
    Button stepBack;
    Button stepForward;
    Button stepFirst;
    Button stepLast;
    Button showit;
    boolean isShowit;
    boolean showSame;
    boolean isStepping;
    boolean noControls;
    Button playAudio;
    boolean isPlayAudio;
    Scrollbar speed;
    boolean isSpeed;
    int framesPerSecond;
    int dwell;
    Scrollbar setFrameScrollbar;
    String setFrameText;
    boolean isSetFrameScrollbar;
    Label frameScrollbarLabel;
    Button refresh;
    Button autoOnOff;
    boolean autoState;
    boolean isAuto;
    boolean isRefresh;
    boolean firstLoad;
    boolean quietLoadem;
    boolean quietReload;
    boolean veryQuietReload;
    OnOffCanvas[] onoff;
    boolean showAllOnOff;
    Panel ptonoff;
    int onOffHeight;
    int onOffWidth;
    int onOffSpacing;
    boolean isOnOff;
    boolean debug;
    Random rand;
    Button zoom;
    boolean isZoom;
    boolean isZoomed;
    boolean keepZoom;
    boolean activeZoom;
    int[] preserve;
    int[] dwellRates;
    boolean useDwellRates;
    Scrollbar fader;
    boolean isFader;
    boolean fadem;
    String faderLabel;
    Checkbox[] overlay;
    int[] overlayTransparentAmount;
    int[] overlayLinks;
    boolean[] overlayHidden;
    boolean[] overlayStatic;
    boolean[] overlayZoom;
    boolean baseStatic;
    int[] overlayOrder;
    boolean isOverlay;
    boolean isPortal;
    TextField frameLabelField;
    Properties myMessages;
    String[] frameLabel;
    boolean hasFrameLabel;
    Color background;
    Color foreground;
    Choice enhanceChoice;
    int old_enhIndex;
    RotatorThing rotator;
    Enhance enh;
    Choice numFramesChoice;
    int defaultNumFramesChoice;
    int[] frameChoices;
    int[] numFramesMode;
    boolean resizeWithImages;
    boolean roamStop;
    boolean useArchive;
    boolean useImageArchive;
    AniCanvas can;
    int imageHeight;
    int imageWidth;
    int imageWindowHeight;
    int imageWindowWidth;
    int baseImageHeight;
    int baseImageWidth;
    int numFrames;
    int firstFrame;
    int lastFrame;
    int incFrame;
    boolean userSetFrames;
    int totalFrames;
    int loadedFrames;
    int baseNumber;
    int numFadesPerFrame;
    int numOverlays;
    int numOverlayLabels;
    int numPortals;
    int numPortalLocs;
    int currentFrame;
    int loopDirection;
    int addDwell;
    int minDwell;
    int pauseOnLast;
    int pausePercent;
    int pad;
    String fileOfFilenames;
    String[] filenameList;
    String audioFilename;
    String[][] overFilenameList;
    String[][] portalFilenameList;
    boolean useTransparentImage;
    int transparency;
    int transparency2;
    URL imageBase;
    boolean usingIP;
    Image[] baseImages;
    Image[] enhancedImages;
    Image[][] portalImages;
    Image[][] overlayImages;
    URL[] baseImagesURL;
    int[] portalHeight;
    int[] portalWidth;
    int[] portalX;
    int[] portalY;
    int[] overlayX;
    int[] overlayY;
    boolean[] portalScaling;
    int refreshRate;
    boolean refreshWarning;
    boolean useCaching;
    int antiCacheIndex;
    boolean useAntiCaching;
    String excludeCaching;
    boolean doExcludeCaching;
    boolean useProgressBar;
    Frame myFrame;
    boolean doingNav;
    double[][] latlon;
    private Thread animator_thread;
    private RefreshImages ri;
    
    public AniS() {
        this.sixlegs = false;
        this.remoteImages = false;
        this.isStartStop = false;
        this.isLooping = false;
        this.alive = true;
        this.start_looping = false;
        this.blank_screen = false;
        this.isLooprock = false;
        this.isRocking = false;
        this.isShowit = false;
        this.showSame = false;
        this.isStepping = false;
        this.noControls = false;
        this.isPlayAudio = false;
        this.isSpeed = false;
        this.framesPerSecond = 20;
        this.dwell = 500;
        this.isSetFrameScrollbar = false;
        this.autoOnOff = null;
        this.autoState = true;
        this.isAuto = false;
        this.isRefresh = false;
        this.firstLoad = true;
        this.quietLoadem = false;
        this.quietReload = false;
        this.veryQuietReload = false;
        this.showAllOnOff = false;
        this.isOnOff = false;
        this.debug = false;
        this.isZoom = false;
        this.isZoomed = false;
        this.keepZoom = false;
        this.activeZoom = false;
        this.useDwellRates = false;
        this.isFader = false;
        this.fadem = false;
        this.overlayTransparentAmount = null;
        this.overlayLinks = null;
        this.overlayHidden = null;
        this.overlayStatic = null;
        this.overlayZoom = null;
        this.baseStatic = false;
        this.overlayOrder = null;
        this.isOverlay = false;
        this.isPortal = false;
        this.frameLabel = null;
        this.hasFrameLabel = false;
        this.enhanceChoice = null;
        this.old_enhIndex = -1;
        this.rotator = null;
        this.enh = null;
        this.numFramesChoice = null;
        this.defaultNumFramesChoice = 0;
        this.resizeWithImages = true;
        this.roamStop = false;
        this.useArchive = false;
        this.useImageArchive = false;
        this.numFrames = -1;
        this.firstFrame = 0;
        this.lastFrame = 0;
        this.incFrame = 1;
        this.userSetFrames = false;
        this.totalFrames = 0;
        this.loadedFrames = 0;
        this.baseNumber = 0;
        this.numFadesPerFrame = 1;
        this.numOverlays = -1;
        this.numOverlayLabels = -1;
        this.numPortals = -1;
        this.numPortalLocs = -1;
        this.currentFrame = -1;
        this.loopDirection = 1;
        this.addDwell = 0;
        this.minDwell = 30;
        this.pauseOnLast = 0;
        this.pausePercent = 0;
        this.pad = 2;
        this.fileOfFilenames = null;
        this.useTransparentImage = false;
        this.transparency = -1;
        this.transparency2 = -1;
        this.imageBase = null;
        this.usingIP = false;
        this.baseImages = null;
        this.enhancedImages = null;
        this.portalImages = null;
        this.overlayImages = null;
        this.baseImagesURL = null;
        this.refreshWarning = true;
        this.useCaching = false;
        this.antiCacheIndex = 0;
        this.useAntiCaching = true;
        this.doExcludeCaching = false;
        this.useProgressBar = true;
        this.doingNav = false;
        this.latlon = null;
        this.animator_thread = null;
        this.ri = null;
    }
    
    public void init() {
        if (this.debug) {
            System.out.println("####   init() called....");
        }
        this.numFrames = 0;
        this.lastFrame = 0;
        this.background = this.getBackground();
        this.foreground = this.getForeground();
        this.rand = new Random(System.currentTimeMillis());
        final Locale default1 = Locale.getDefault();
        final String parameter = this.getParameter("locale");
        String s;
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            try {
                s = "anismessages_" + stringTokenizer.nextToken().trim().trim() + ".properties";
            }
            catch (Exception ex) {
                System.out.println("!!!!  Problem with locale parameter = " + ex);
                s = "anismessages.properties";
            }
        }
        else {
            s = "anismessages_" + default1.getLanguage() + ".properties";
        }
        this.myMessages = new Properties();
        try {
            this.myMessages.load(this.getClass().getResourceAsStream(s));
        }
        catch (Exception ex5) {
            try {
                System.out.println("!!!!  Could not find: " + s + " ... using default");
                this.myMessages.load(this.getClass().getResourceAsStream("anismessages.properties"));
            }
            catch (Exception ex6) {
                System.out.println("!!!!   Cannot read language properties at all...");
            }
        }
        final String trim = System.getProperty("java.version").trim();
        System.err.println("Java Runtime Version = " + trim);
        if (trim.startsWith("1.1") || trim.startsWith("1.2") || trim.startsWith("1.3") || trim.startsWith("1.4.0") || trim.startsWith("1.4.1")) {
            this.roamStop = true;
        }
        final String parameter2 = this.getParameter("backcolor");
        if (parameter2 != null) {
            this.setBackground(this.background = new Color(Integer.parseInt(parameter2.substring(1), 16)));
        }
        final String parameter3 = this.getParameter("forecolor");
        if (parameter3 != null) {
            this.setForeground(this.foreground = new Color(Integer.parseInt(parameter3.substring(1), 16)));
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        this.myFrame = (Frame)parent;
        this.usingIP = false;
        final String parameter4 = this.getParameter("use_IP");
        if (parameter4 != null && parameter4.equalsIgnoreCase("true")) {
            this.usingIP = true;
        }
        this.quietReload = false;
        this.quietLoadem = false;
        final String parameter5 = this.getParameter("quiet_reload");
        if (parameter5 != null && parameter5.equalsIgnoreCase("true")) {
            this.quietReload = true;
        }
        if (parameter5 != null && parameter5.equalsIgnoreCase("very")) {
            this.quietReload = true;
            this.veryQuietReload = true;
        }
        final String parameter6 = this.getParameter("debug");
        if (parameter6 != null && parameter6.equalsIgnoreCase("true")) {
            this.debug = true;
        }
        final String parameter7 = this.getParameter("lat_lon");
        this.doingNav = false;
        if (parameter7 != null) {
            this.latlon = new double[2][2];
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter7, ",");
            if (stringTokenizer2.countTokens() == 4) {
                this.doingNav = true;
                this.latlon[0][0] = Double.valueOf(stringTokenizer2.nextToken().trim());
                this.latlon[0][1] = Double.valueOf(stringTokenizer2.nextToken().trim());
                this.latlon[1][0] = Double.valueOf(stringTokenizer2.nextToken().trim());
                this.latlon[1][1] = Double.valueOf(stringTokenizer2.nextToken().trim());
                System.out.println("####  doing Nav okay");
            }
            else {
                System.out.println("#### Invalid lat_lon string: " + parameter7);
            }
        }
        final String parameter8 = this.getParameter("no_controls");
        if (parameter8 != null && parameter8.equalsIgnoreCase("true")) {
            this.noControls = true;
            this.pad = 0;
        }
        final String parameter9 = this.getParameter("use_progress_bar");
        if (parameter9 != null) {
            if (parameter9.equalsIgnoreCase("true")) {
                this.useProgressBar = true;
            }
            if (parameter9.equalsIgnoreCase("false")) {
                this.useProgressBar = false;
            }
        }
        this.doExcludeCaching = false;
        this.excludeCaching = this.getParameter("exclude_caching");
        if (this.excludeCaching != null) {
            this.doExcludeCaching = true;
        }
        this.useCaching = false;
        final String parameter10 = this.getParameter("use_caching");
        if (parameter10 != null && parameter10.equalsIgnoreCase("true")) {
            this.useCaching = true;
        }
        if (this.debug) {
            System.out.println("####  useCaching = " + this.useCaching);
        }
        this.useAntiCaching = true;
        final String parameter11 = this.getParameter("use_anti_caching");
        if (parameter11 != null) {
            if (parameter11.equalsIgnoreCase("false")) {
                this.useAntiCaching = false;
            }
            if (parameter11.equalsIgnoreCase("true")) {
                this.useAntiCaching = true;
            }
        }
        this.baseStatic = false;
        final String parameter12 = this.getParameter("base_static");
        if (parameter12 != null && parameter12.equalsIgnoreCase("true")) {
            this.baseStatic = true;
        }
        final String parameter13 = this.getParameter("image_base");
        if (parameter13 == null) {
            this.imageBase = this.getDocumentBase();
            if (this.usingIP) {
                try {
                    this.imageBase = new URL(this.imageBase.getProtocol(), InetAddress.getByName(this.imageBase.getHost()).getHostAddress(), this.imageBase.getFile());
                    if (this.debug) {
                        System.out.println("####  image_base URL adjusted to: " + this.imageBase);
                    }
                }
                catch (Exception ex7) {}
            }
        }
        else {
            try {
                this.imageBase = new URL(parameter13);
            }
            catch (Exception ex8) {
                System.err.println("!!!! Invalid URL specified for image_base: " + parameter13);
                this.imageBase = null;
            }
        }
        if (this.getParameter("use_image_archive") != null) {
            this.useImageArchive = true;
        }
        if (this.useImageArchive && trim.startsWith("1.1")) {
            this.useImageArchive = false;
            System.out.println("Note:  use_archive attribute ignored for old Java version = " + trim);
        }
        if (this.getParameter("use_archive") != null) {
            this.useArchive = true;
        }
        if (this.useArchive && trim.startsWith("1.1")) {
            this.useArchive = false;
            System.out.println("Note:  use_archive attribute ignored for old Java version = " + trim);
        }
        String s2 = this.getParameter("basename");
        if (s2 == null) {
            s2 = this.getParameter("basenames");
        }
        final String parameter14 = this.getParameter("filenames");
        this.fileOfFilenames = this.getParameter("file_of_filenames");
        this.audioFilename = this.getParameter("audio_filename");
        String parameter15 = this.getParameter("frame_label_width");
        if (this.getParameter("enable_png") != null) {
            this.sixlegs = true;
        }
        if (this.getParameter("keep_zoom") != null) {
            this.keepZoom = true;
        }
        if (this.getParameter("active_zoom") != null) {
            this.activeZoom = true;
        }
        if (parameter15 == null) {
            parameter15 = "20";
        }
        int int1 = Integer.parseInt(parameter15.trim());
        if (int1 < 2 || int1 > 99) {
            int1 = 20;
        }
        this.filenameList = null;
        this.fader = null;
        final String parameter16 = this.getParameter("fader_label");
        this.faderLabel = this.myMessages.getProperty("set_fade_level");
        if (parameter16 != null) {
            this.faderLabel = parameter16;
        }
        final String parameter17 = this.getParameter("auto_refresh");
        if (parameter17 != null) {
            this.refreshRate = Integer.parseInt(parameter17.trim());
        }
        else {
            this.refreshRate = -1;
        }
        final String parameter18 = this.getParameter("start_frame");
        int int2 = 0;
        if (parameter18 != null) {
            int2 = Integer.parseInt(parameter18.trim());
            this.start_looping = false;
        }
        String s3 = this.getParameter("default_num_frames_choice");
        if (s3 == null) {
            s3 = this.getParameter("default_num_frames_choices");
        }
        this.defaultNumFramesChoice = 0;
        if (s3 != null) {
            this.defaultNumFramesChoice = Integer.parseInt(s3.trim()) - 1;
            if (this.defaultNumFramesChoice < 0) {
                this.defaultNumFramesChoice = 0;
            }
        }
        this.useDwellRates = false;
        this.dwellRates = null;
        final String parameter19 = this.getParameter("dwell_rates");
        if (parameter19 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter19, ",");
            final int countTokens = stringTokenizer3.countTokens();
            this.dwellRates = new int[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                this.dwellRates[i] = Integer.parseInt(stringTokenizer3.nextToken().trim());
            }
            this.useDwellRates = true;
        }
        final String parameter20 = this.getParameter("no_enh");
        this.enh = null;
        if (parameter20 == null) {
            try {
                if (this.useArchive) {
                    this.enh = new Enhance(this.getClass().getResource("enh.tab"));
                }
                else {
                    this.enh = new Enhance(new URL(this.imageBase, "enh.tab"));
                }
            }
            catch (Exception ex9) {
                this.enh = null;
            }
        }
        if (s2 != null) {
            final String parameter21 = this.getParameter("base_starting_number");
            if (parameter21 != null) {
                this.baseNumber = Integer.parseInt(parameter21.trim());
            }
            else {
                this.baseNumber = 0;
            }
            String s4 = this.getParameter("num_frames");
            if (s4 == null) {
                s4 = this.getParameter("num_images");
            }
            if (s4 != null) {
                this.numFrames = Integer.parseInt(s4.trim());
                this.lastFrame = this.numFrames - 1;
                this.filenameList = this.getNamesUsingBaseName(s2, this.numFrames);
            }
            else {
                System.err.println("!!!! invalid/missing 'num_frames' parameter...");
                System.exit(1);
            }
        }
        else if (parameter14 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter14, ",");
            this.numFrames = stringTokenizer4.countTokens();
            this.lastFrame = this.numFrames - 1;
            this.filenameList = this.getNamesUsingList(stringTokenizer4, this.numFrames);
        }
        else if (this.fileOfFilenames != null) {
            this.getFileOfFilenames();
        }
        else {
            System.err.println("!!!! No filenames found in PARAMeters...");
            System.exit(1);
        }
        final String parameter22 = this.getParameter("minimum_dwell");
        if (parameter22 != null) {
            this.minDwell = Integer.parseInt(parameter22.trim());
            if (this.minDwell < 5) {
                this.minDwell = 5;
            }
        }
        final String[] array = { this.getParameter("controls"), this.getParameter("bottom_controls") };
        final Panel[] array2 = { new Panel(), new Panel() };
        final Panel[] array3 = new Panel[2];
        final Panel[] array4 = new Panel[2];
        for (int j = 0; j < 2; ++j) {
            array2[j].setLayout(new FlowLayout(1, 10, 1));
            array2[j].setBackground(this.background);
            array2[j].setForeground(this.foreground);
            if (array[j] != null) {
                final StringTokenizer stringTokenizer5 = new StringTokenizer(array[j], ",");
                while (stringTokenizer5.hasMoreTokens()) {
                    final String trim2 = stringTokenizer5.nextToken().trim();
                    if (trim2.equals("startstop")) {
                        (this.startstop = new Button(this.myMessages.getProperty("start"))).setBackground(this.background);
                        this.startstop.setForeground(this.foreground);
                        this.isStartStop = true;
                        this.start_looping = true;
                        this.isLooping = false;
                        array2[j].add(this.startstop);
                    }
                    else if (trim2.equals("audio")) {
                        if (this.audioFilename == null) {
                            continue;
                        }
                        (this.playAudio = new Button(this.myMessages.getProperty("audio"))).setBackground(this.background);
                        this.playAudio.setForeground(this.foreground);
                        this.isPlayAudio = true;
                        array2[j].add(this.playAudio);
                    }
                    else if (trim2.equals("framelabel")) {
                        (this.frameLabelField = new TextField(int1)).setBackground(this.background);
                        this.frameLabelField.setForeground(this.foreground);
                        this.frameLabelField.setEditable(false);
                        this.hasFrameLabel = true;
                        array2[j].add(this.frameLabelField);
                    }
                    else if (trim2.equals("looprock")) {
                        (this.looprock = new Button(this.myMessages.getProperty("rock"))).setBackground(this.background);
                        this.looprock.setForeground(this.foreground);
                        this.isLooprock = true;
                        this.isRocking = false;
                        array2[j].add(this.looprock);
                    }
                    else if (trim2.equals("numframeschoice")) {
                        (this.numFramesChoice = new Choice()).setBackground(this.background);
                        this.numFramesChoice.setForeground(this.foreground);
                        this.myMessages.getProperty("frames");
                        try {
                            String s5 = this.getParameter("num_frames_choice");
                            if (s5 == null) {
                                s5 = this.getParameter("num_frames_choices");
                            }
                            if (s5 == null) {
                                continue;
                            }
                            final StringTokenizer stringTokenizer6 = new StringTokenizer(s5, ",");
                            final int countTokens2 = stringTokenizer6.countTokens();
                            this.frameChoices = new int[countTokens2];
                            this.numFramesMode = new int[countTokens2];
                            for (int k = 0; k < countTokens2; ++k) {
                                String s6 = stringTokenizer6.nextToken().trim();
                                if (s6.equalsIgnoreCase("all")) {
                                    this.frameChoices[k] = -1;
                                    this.numFramesMode[k] = 0;
                                }
                                else {
                                    if (s6.startsWith("+*")) {
                                        this.numFramesMode[k] = 2;
                                        s6 = s6.substring(2);
                                    }
                                    else if (s6.startsWith("-*")) {
                                        this.numFramesMode[k] = -2;
                                        s6 = s6.substring(2);
                                    }
                                    else if (s6.startsWith("+")) {
                                        this.numFramesMode[k] = 1;
                                        s6 = s6.substring(1);
                                    }
                                    else if (s6.startsWith("-")) {
                                        this.numFramesMode[k] = -1;
                                        s6 = s6.substring(1);
                                    }
                                    else {
                                        this.numFramesMode[k] = -1;
                                    }
                                    this.frameChoices[k] = Integer.parseInt(s6);
                                }
                                this.numFramesChoice.addItem(s6 + " frames");
                            }
                            this.numFramesChoice.select(this.defaultNumFramesChoice);
                            this.userSetFrames = true;
                            array2[j].add(this.numFramesChoice);
                        }
                        catch (Exception ex10) {
                            System.out.println("!!!!  Problem with num_frames_choices");
                        }
                    }
                    else if (trim2.equals("enhance")) {
                        if (this.enh != null) {
                            (this.enhanceChoice = new Choice()).setBackground(this.background);
                            this.enhanceChoice.setForeground(this.foreground);
                            try {
                                final String[] names = this.enh.getNames();
                                this.enhanceChoice.addItem(this.myMessages.getProperty("pick_enhancement"));
                                for (int l = 0; l < names.length; ++l) {
                                    this.enhanceChoice.addItem(names[l]);
                                }
                            }
                            catch (Exception ex2) {
                                System.err.println(ex2);
                            }
                            array2[j].add(this.enhanceChoice);
                            this.enhanceChoice.enable(false);
                        }
                        else {
                            System.err.println("!!!! No enhancement file enh.tab found!");
                        }
                    }
                    else if (trim2.startsWith("auto_toggle") && this.refreshRate > 0) {
                        if (trim2.endsWith("/off")) {
                            this.autoOnOff = new Button(this.myMessages.getProperty("auto_on"));
                            this.autoState = false;
                        }
                        else {
                            this.autoState = true;
                            this.autoOnOff = new Button(this.myMessages.getProperty("auto_off"));
                        }
                        this.autoOnOff.setBackground(this.background);
                        this.autoOnOff.setForeground(this.foreground);
                        array2[j].add(this.autoOnOff);
                    }
                    else if (trim2.equals("refresh")) {
                        (this.refresh = new Button(this.myMessages.getProperty("refresh"))).setBackground(this.background);
                        this.refresh.setForeground(this.foreground);
                        array2[j].add(this.refresh);
                        this.isRefresh = true;
                    }
                    else if (trim2.equals("step")) {
                        this.stepBack = new Button("<");
                        this.stepForward = new Button(">");
                        this.stepBack.setBackground(this.background);
                        this.stepBack.setForeground(this.foreground);
                        this.stepForward.setBackground(this.background);
                        this.stepForward.setForeground(this.foreground);
                        array2[j].add(this.stepBack);
                        array2[j].add(this.stepForward);
                        this.isStepping = true;
                    }
                    else if (trim2.equals("firstlast")) {
                        this.stepFirst = new Button("<|");
                        this.stepLast = new Button("|>");
                        this.stepFirst.setBackground(this.background);
                        this.stepFirst.setForeground(this.foreground);
                        this.stepLast.setBackground(this.background);
                        this.stepLast.setForeground(this.foreground);
                        array2[j].add(this.stepFirst);
                        array2[j].add(this.stepLast);
                    }
                    else if (trim2.startsWith("show")) {
                        (this.showit = new Button(this.myMessages.getProperty("show"))).setBackground(this.background);
                        this.showit.setForeground(this.foreground);
                        this.showit.setEnabled(false);
                        if (trim2.indexOf("/same") > 0) {
                            this.showSame = true;
                        }
                        else {
                            this.showSame = false;
                        }
                        this.isShowit = true;
                        array2[j].add(this.showit);
                    }
                    else if (trim2.equals("setframe")) {
                        final String parameter23 = this.getParameter("setframe_label");
                        this.setFrameText = this.myMessages.getProperty("set_frame_label");
                        if (parameter23 != null && parameter23.indexOf("*") != -1) {
                            this.setFrameText = parameter23;
                        }
                        (this.setFrameScrollbar = new Scrollbar(0, this.currentFrame, 1, 0, this.numFrames)).setBackground(this.background);
                        this.setFrameScrollbar.setForeground(this.foreground);
                        this.frameScrollbarLabel = new Label(parameter23, 1);
                        this.isSetFrameScrollbar = true;
                        final Panel panel = new Panel();
                        panel.setLayout(new BorderLayout());
                        panel.setBackground(this.background);
                        panel.setForeground(this.foreground);
                        panel.add("North", this.frameScrollbarLabel);
                        panel.add("South", this.setFrameScrollbar);
                        array2[j].add(panel);
                    }
                    else if (trim2.equals("speed")) {
                        (this.speed = new Scrollbar(0, this.framesPerSecond, 10, 2, 10000 / this.minDwell)).setBackground(this.background);
                        this.speed.setForeground(this.foreground);
                        this.isSpeed = true;
                        final Panel panel2 = new Panel();
                        panel2.setLayout(new BorderLayout());
                        panel2.setBackground(this.background);
                        panel2.setForeground(this.foreground);
                        panel2.add("North", new Label(this.myMessages.getProperty("set_animation_speed"), 1));
                        panel2.add("South", this.speed);
                        array2[j].add(panel2);
                    }
                    else if (trim2.equals("rotator")) {
                        final String parameter24 = this.getParameter("rotator_labels");
                        String[] array5 = { "1", String.valueOf(1 + this.numFrames / 2) };
                        if (parameter24 != null) {
                            final StringTokenizer stringTokenizer7 = new StringTokenizer(parameter24, ",");
                            final int countTokens3 = stringTokenizer7.countTokens();
                            array5 = new String[countTokens3];
                            for (int n = 0; n < countTokens3; ++n) {
                                array5[n] = stringTokenizer7.nextToken().trim();
                            }
                        }
                        final String parameter25 = this.getParameter("rotator_color");
                        Color yellow = Color.yellow;
                        if (parameter25 != null) {
                            yellow = new Color(Integer.parseInt(parameter25.substring(1), 16));
                        }
                        this.rotator = new RotatorThing(this, array5, this.numFrames, this.foreground, yellow);
                        array2[j].add(this.rotator);
                    }
                    else if (trim2.startsWith("toggle")) {
                        final String parameter26 = this.getParameter("toggle_size");
                        if (trim2.toLowerCase().indexOf("/showall") != -1) {
                            this.showAllOnOff = true;
                        }
                        else {
                            this.showAllOnOff = false;
                        }
                        this.onOffWidth = 10;
                        this.onOffHeight = 10;
                        this.onOffSpacing = 3;
                        if (parameter26 != null) {
                            final StringTokenizer stringTokenizer8 = new StringTokenizer(parameter26, ",");
                            this.onOffWidth = Integer.parseInt(stringTokenizer8.nextToken().trim());
                            if (stringTokenizer8.hasMoreTokens()) {
                                this.onOffHeight = Integer.parseInt(stringTokenizer8.nextToken().trim());
                            }
                            if (stringTokenizer8.hasMoreTokens()) {
                                this.onOffSpacing = Integer.parseInt(stringTokenizer8.nextToken().trim());
                            }
                        }
                        this.onoff = new OnOffCanvas[this.numFrames];
                        (this.ptonoff = new Panel()).setLayout(new FlowLayout(1, this.onOffSpacing, 0));
                        this.ptonoff.setBackground(this.background);
                        this.ptonoff.setForeground(this.foreground);
                        this.ptonoff.removeAll();
                        for (int n2 = 0; n2 < this.numFrames; ++n2) {
                            (this.onoff[n2] = new OnOffCanvas(this, n2)).setBackground(Color.green);
                            this.onoff[n2].resize(this.onOffWidth, this.onOffHeight);
                            this.onoff[n2].resize(this.onOffWidth, this.onOffHeight);
                            this.onoff[n2].setActive(false);
                        }
                        if (this.showAllOnOff) {
                            for (int n3 = 0; n3 < this.numFrames; ++n3) {
                                this.ptonoff.add(this.onoff[n3]);
                            }
                        }
                        for (int firstFrame = this.firstFrame; firstFrame <= this.lastFrame; firstFrame += this.incFrame) {
                            this.onoff[firstFrame].setActive(true);
                            if (!this.showAllOnOff) {
                                this.ptonoff.add(this.onoff[firstFrame]);
                            }
                        }
                        (array3[j] = new Panel()).setBackground(this.background);
                        array3[j].setForeground(this.foreground);
                        array3[j].setLayout(new BorderLayout(0, 0));
                        final Label label = new Label(this.myMessages.getProperty("toggle_help"), 1);
                        label.setFont(new Font("SansSerif", 0, 10));
                        array3[j].add("North", this.ptonoff);
                        if (trim2.toLowerCase().indexOf("/nohelp") == -1) {
                            array3[j].add("South", label);
                        }
                        this.isOnOff = true;
                    }
                    else if (trim2.equals("zoom")) {
                        (this.zoom = new Button(this.myMessages.getProperty("zoom"))).setBackground(this.background);
                        this.zoom.setForeground(this.foreground);
                        this.zoom.enable(false);
                        this.isZoom = true;
                        array2[j].add(this.zoom);
                        this.activeZoom = false;
                    }
                    else if (trim2.equals("fader")) {
                        (this.fader = new Scrollbar(0, this.currentFrame, 1, 0, 10)).setBackground(this.background);
                        this.fader.setForeground(this.foreground);
                        final Panel panel3 = new Panel();
                        panel3.setLayout(new BorderLayout());
                        panel3.setBackground(this.background);
                        panel3.setForeground(this.foreground);
                        panel3.add("North", new Label(this.faderLabel, 1));
                        panel3.add("South", this.fader);
                        this.isFader = true;
                        this.fadem = true;
                        array2[j].add(panel3);
                        this.fader.enable(false);
                    }
                    else if (trim2.equals("overlay")) {
                        final String parameter27 = this.getParameter("overlay_labels");
                        final String parameter28 = this.getParameter("overlay_labels_colors");
                        final String parameter29 = this.getParameter("overlay_radio");
                        final String parameter30 = this.getParameter("overlay_transparent_amount");
                        final String parameter31 = this.getParameter("overlay_links");
                        final String parameter32 = this.getParameter("overlay_zorder");
                        final String parameter33 = this.getParameter("overlay_static");
                        final String parameter34 = this.getParameter("overlay_zoom");
                        int n4 = 1;
                        this.overlayLinks = null;
                        this.overlayOrder = null;
                        this.overlayHidden = null;
                        this.overlayStatic = null;
                        this.overlayZoom = null;
                        if (parameter27 != null) {
                            final StringTokenizer stringTokenizer9 = new StringTokenizer(parameter27, ",");
                            StringTokenizer stringTokenizer10 = null;
                            if (parameter30 != null) {
                                stringTokenizer10 = new StringTokenizer(parameter30, ",");
                            }
                            this.numOverlayLabels = stringTokenizer9.countTokens();
                            this.overlayTransparentAmount = new int[this.numOverlayLabels];
                            this.overlay = new Checkbox[this.numOverlayLabels];
                            (array4[j] = new Panel()).setLayout(new BorderLayout(0, 0));
                            array4[j].setBackground(this.background);
                            array4[j].setForeground(this.foreground);
                            Panel panel4 = new Panel();
                            panel4.setLayout(new FlowLayout(1, this.pad, this.pad));
                            panel4.setBackground(this.background);
                            panel4.setForeground(this.foreground);
                            CheckboxGroup checkboxGroup = null;
                            StringTokenizer stringTokenizer11 = null;
                            boolean b = false;
                            if (parameter29 != null) {
                                stringTokenizer11 = new StringTokenizer(parameter29, ",");
                                if (stringTokenizer11.countTokens() == 1) {
                                    b = true;
                                }
                                checkboxGroup = new CheckboxGroup();
                            }
                            StringTokenizer stringTokenizer12 = null;
                            this.overlayLinks = new int[this.numOverlayLabels];
                            this.overlayHidden = new boolean[this.numOverlayLabels];
                            this.overlayStatic = new boolean[this.numOverlayLabels];
                            this.overlayZoom = new boolean[this.numOverlayLabels];
                            StringTokenizer stringTokenizer13 = null;
                            if (parameter28 != null) {
                                stringTokenizer13 = new StringTokenizer(parameter28, ",");
                            }
                            if (parameter31 != null) {
                                stringTokenizer12 = new StringTokenizer(parameter31, ",");
                            }
                            StringTokenizer stringTokenizer14 = null;
                            this.overlayOrder = new int[this.numOverlayLabels];
                            if (parameter32 != null) {
                                stringTokenizer14 = new StringTokenizer(parameter32, ",");
                            }
                            StringTokenizer stringTokenizer15 = null;
                            if (parameter33 != null) {
                                stringTokenizer15 = new StringTokenizer(parameter33, ",");
                            }
                            StringTokenizer stringTokenizer16 = null;
                            if (parameter34 != null) {
                                stringTokenizer16 = new StringTokenizer(parameter34, ",");
                            }
                            for (int n5 = 0; n5 < this.numOverlayLabels; ++n5) {
                                String s7 = stringTokenizer9.nextToken().trim();
                                boolean state = false;
                                boolean b2 = false;
                                this.overlayHidden[n5] = false;
                                if (s7.startsWith("/")) {
                                    final String substring = s7.substring(1);
                                    if (n4 == 0) {
                                        System.out.println("!!!!   HTML Error - only 2 lines of overlay_labels permitted!");
                                    }
                                    array4[j].add("North", panel4);
                                    s7 = substring;
                                    panel4 = new Panel();
                                    panel4.setLayout(new FlowLayout(1, this.pad, this.pad));
                                    panel4.setBackground(this.background);
                                    panel4.setForeground(this.foreground);
                                    n4 = 0;
                                }
                                if (s7.endsWith("/on")) {
                                    s7 = s7.substring(0, s7.length() - 3);
                                    state = true;
                                }
                                else if (s7.endsWith("/always")) {
                                    s7 = s7.substring(0, s7.length() - 7);
                                    state = true;
                                    b2 = true;
                                }
                                else if (s7.endsWith("/hidden")) {
                                    s7 = s7.substring(0, s7.length() - 7);
                                    state = false;
                                    b2 = true;
                                    this.overlayHidden[n5] = true;
                                }
                                (this.overlay[n5] = new Checkbox(s7)).setBackground(this.background);
                                this.overlay[n5].setState(state);
                                if (stringTokenizer13 == null) {
                                    this.overlay[n5].setForeground(this.foreground);
                                }
                                else {
                                    this.overlay[n5].setForeground(new Color(Integer.parseInt(stringTokenizer13.nextToken().trim().substring(1), 16)));
                                }
                                if (checkboxGroup != null && !b2) {
                                    if (b) {
                                        this.overlay[n5].setCheckboxGroup(checkboxGroup);
                                    }
                                    else if (stringTokenizer11.hasMoreTokens()) {
                                        final String trim3 = stringTokenizer11.nextToken().trim();
                                        if (trim3 != null && trim3.equalsIgnoreCase("true")) {
                                            this.overlay[n5].setCheckboxGroup(checkboxGroup);
                                        }
                                    }
                                }
                                if (!b2) {
                                    panel4.add(this.overlay[n5]);
                                }
                                if (parameter30 == null) {
                                    this.overlayTransparentAmount[n5] = 100;
                                }
                                else {
                                    this.overlayTransparentAmount[n5] = Integer.parseInt(stringTokenizer10.nextToken().trim());
                                }
                                if (parameter31 == null) {
                                    this.overlayLinks[n5] = 0;
                                }
                                else {
                                    this.overlayLinks[n5] = Integer.parseInt(stringTokenizer12.nextToken().trim());
                                }
                                if (parameter32 == null) {
                                    this.overlayOrder[n5] = n5;
                                }
                                else {
                                    this.overlayOrder[Integer.parseInt(stringTokenizer14.nextToken().trim())] = n5;
                                }
                                if (stringTokenizer15 == null) {
                                    this.overlayStatic[n5] = false;
                                }
                                else {
                                    final String trim4 = stringTokenizer15.nextToken().trim();
                                    if (trim4.startsWith("t") || trim4.startsWith("T") || trim4.startsWith("y") || trim4.startsWith("Y") || trim4.startsWith("1")) {
                                        this.overlayStatic[n5] = true;
                                    }
                                    else {
                                        this.overlayStatic[n5] = false;
                                    }
                                }
                                if (stringTokenizer16 == null) {
                                    this.overlayZoom[n5] = true;
                                }
                                else {
                                    final String trim5 = stringTokenizer16.nextToken().trim();
                                    if (trim5.startsWith("f") || trim5.startsWith("F") || trim5.startsWith("n") || trim5.startsWith("N") || trim5.startsWith("0")) {
                                        this.overlayZoom[n5] = false;
                                    }
                                    else {
                                        this.overlayZoom[n5] = true;
                                    }
                                }
                            }
                            for (int n6 = 0; n6 < this.numOverlayLabels; ++n6) {
                                if (this.overlayLinks[n6] > 0) {
                                    for (int n7 = 0; n7 < this.numOverlayLabels; ++n7) {
                                        if (this.overlayLinks[n7] == -this.overlayLinks[n6] && this.overlayHidden[n7]) {
                                            this.overlay[n7].setState(this.overlay[n6].getState());
                                        }
                                    }
                                }
                            }
                            this.isOverlay = true;
                            if (n4 != 0) {
                                array4[j].add("North", panel4);
                            }
                            else {
                                array4[j].add("South", panel4);
                            }
                        }
                        else {
                            System.err.println("!!!! Need 'overlay_labels' parameter");
                        }
                    }
                    else {
                        System.err.println("!!!! Unrecognized token=" + trim2);
                    }
                }
            }
        }
        final String parameter35 = this.getParameter("frame_label");
        if (parameter35 != null) {
            final StringTokenizer stringTokenizer17 = new StringTokenizer(parameter35, ",");
            if (stringTokenizer17.countTokens() != this.numFrames) {
                System.err.println("!!!! Error: the number of frame_labels is not equal to the number of frames = " + this.numFrames);
                this.frameLabel = null;
            }
            else {
                this.frameLabel = new String[this.numFrames];
                for (int n8 = 0; n8 < this.numFrames; ++n8) {
                    String s8 = "  " + stringTokenizer17.nextToken() + "  ";
                    final int index = s8.indexOf("$$");
                    if (index > -1) {
                        final int index2 = s8.indexOf("$$", index + 1);
                        if (index2 > -1) {
                            final String substring2 = s8.substring(index + 2, index2);
                            new Date();
                            s8 = s8.substring(0, index - 1) + " " + new Date(Date.parse(substring2)).toString().substring(4) + " " + s8.substring(index2 + 2);
                        }
                    }
                    this.frameLabel[n8] = s8.trim();
                }
            }
        }
        final String parameter36 = this.getParameter("rate");
        if (parameter36 != null) {
            this.framesPerSecond = Integer.parseInt(parameter36.trim());
            if (this.isSpeed) {
                this.speed.setValue(this.framesPerSecond);
            }
        }
        final String parameter37 = this.getParameter("rocking");
        if (parameter37 != null) {
            if (parameter37.trim().equalsIgnoreCase("true")) {
                this.isRocking = true;
            }
            else {
                this.isRocking = false;
            }
        }
        String s9 = this.getParameter("loop");
        if (s9 == null) {
            s9 = this.getParameter("start_looping");
        }
        if (s9 != null) {
            if (s9.trim().equalsIgnoreCase("true")) {
                this.start_looping = true;
            }
            else {
                this.start_looping = false;
            }
        }
        final String parameter38 = this.getParameter("pause");
        this.pauseOnLast = 0;
        if (parameter38 != null) {
            this.pauseOnLast = Integer.parseInt(parameter38.trim());
        }
        if (this.pauseOnLast < 0 || this.pauseOnLast > 20000) {
            System.err.println("!!!! Invalid pause value = " + this.pauseOnLast);
            System.exit(1);
        }
        final String parameter39 = this.getParameter("pause_percent");
        this.pausePercent = 0;
        if (parameter39 != null) {
            this.pausePercent = Integer.parseInt(parameter39.trim());
        }
        if (this.pausePercent < 0 || this.pausePercent > 1000) {
            System.err.println("!!!! Invalid pause value = " + this.pausePercent);
            System.exit(1);
        }
        String s10 = this.getParameter("transparency");
        if (s10 == null) {
            s10 = this.getParameter("transparent");
        }
        this.transparency = -1;
        this.transparency2 = -1;
        if (s10 != null) {
            if (s10.equalsIgnoreCase("image")) {
                this.useTransparentImage = true;
                if (this.debug) {
                    System.out.println("####  useTransparentImage = true");
                }
            }
            else {
                this.useTransparentImage = false;
                final int index3 = s10.indexOf(",");
                if (index3 < 0) {
                    this.transparency = Integer.parseInt(s10.trim().substring(1), 16);
                    this.transparency2 = this.transparency;
                }
                else {
                    this.transparency = Integer.parseInt(s10.substring(0, index3).trim().substring(1), 16);
                    this.transparency2 = Integer.parseInt(s10.substring(index3 + 1).trim().substring(1), 16);
                }
            }
        }
        final String parameter40 = this.getParameter("fade");
        if (parameter40 != null) {
            if (parameter40.trim().equalsIgnoreCase("true")) {
                this.fadem = true;
            }
            else {
                this.fadem = false;
            }
        }
        if (this.isFader) {
            this.fadem = true;
        }
        final String parameter41 = this.getParameter("blank_screen");
        if (parameter41 != null) {
            if (parameter41.trim().equalsIgnoreCase("true")) {
                this.blank_screen = true;
            }
            else {
                this.blank_screen = false;
            }
        }
        final String parameter42 = this.getParameter("scale_portal_images");
        boolean showPortalPointers = true;
        final String parameter43 = this.getParameter("portal_locations");
        if (parameter43 != null) {
            try {
                final StringTokenizer stringTokenizer18 = new StringTokenizer(parameter43, ",");
                this.numPortalLocs = stringTokenizer18.countTokens();
                this.portalWidth = new int[this.numPortalLocs];
                this.portalHeight = new int[this.numPortalLocs];
                this.portalX = new int[this.numPortalLocs];
                this.portalY = new int[this.numPortalLocs];
                this.portalScaling = new boolean[this.numPortalLocs];
                StringTokenizer stringTokenizer19 = null;
                if (parameter42 != null) {
                    stringTokenizer19 = new StringTokenizer(parameter42, ",");
                }
                for (int n9 = 0; n9 < this.numPortalLocs; ++n9) {
                    final StringTokenizer stringTokenizer20 = new StringTokenizer(stringTokenizer18.nextToken(), "&");
                    this.portalX[n9] = Integer.parseInt(stringTokenizer20.nextToken().trim());
                    this.portalY[n9] = Integer.parseInt(stringTokenizer20.nextToken().trim());
                    this.portalWidth[n9] = Integer.parseInt(stringTokenizer20.nextToken().trim());
                    this.portalHeight[n9] = Integer.parseInt(stringTokenizer20.nextToken().trim());
                    if (stringTokenizer19 != null) {
                        this.portalScaling[n9] = false;
                        if (stringTokenizer19.nextToken().toLowerCase().startsWith("t")) {
                            this.portalScaling[n9] = true;
                        }
                    }
                }
                this.isPortal = true;
                final String parameter44 = this.getParameter("portal_pointers");
                if (parameter44 != null && (parameter44.trim().equalsIgnoreCase("off") || parameter44.trim().equalsIgnoreCase("false"))) {
                    showPortalPointers = false;
                }
            }
            catch (Exception ex3) {
                System.err.println("!!!! Problem parsing portal locations or scaling: " + ex3);
                System.exit(1);
            }
        }
        this.overlayX = null;
        this.overlayY = null;
        final String parameter45 = this.getParameter("overlay_locations");
        if (parameter45 != null) {
            try {
                final StringTokenizer stringTokenizer21 = new StringTokenizer(parameter45, ",");
                final int countTokens4 = stringTokenizer21.countTokens();
                this.overlayX = new int[countTokens4];
                this.overlayY = new int[countTokens4];
                for (int n10 = 0; n10 < countTokens4; ++n10) {
                    final StringTokenizer stringTokenizer22 = new StringTokenizer(stringTokenizer21.nextToken(), "&");
                    this.overlayX[n10] = Integer.parseInt(stringTokenizer22.nextToken().trim());
                    this.overlayY[n10] = Integer.parseInt(stringTokenizer22.nextToken().trim());
                }
            }
            catch (Exception ex4) {
                System.err.println("!!!! Problem parsing overlay locations: " + ex4);
                System.exit(1);
            }
        }
        final int int3 = Integer.parseInt(this.getParameter("height").trim());
        this.imageHeight = int3 - 100;
        if (this.imageHeight < 50) {
            this.imageHeight = int3;
        }
        this.imageWidth = Integer.parseInt(this.getParameter("width").trim());
        final String parameter46 = this.getParameter("image_size");
        if (parameter46 != null) {
            final StringTokenizer stringTokenizer23 = new StringTokenizer(parameter46, ",");
            this.imageWidth = Integer.parseInt(stringTokenizer23.nextToken().trim());
            this.imageHeight = Integer.parseInt(stringTokenizer23.nextToken().trim());
        }
        final String parameter47 = this.getParameter("image_window_size");
        if (parameter47 != null) {
            final StringTokenizer stringTokenizer24 = new StringTokenizer(parameter47, ",");
            this.imageWindowWidth = Integer.parseInt(stringTokenizer24.nextToken().trim());
            this.imageWindowHeight = Integer.parseInt(stringTokenizer24.nextToken().trim());
            this.resizeWithImages = false;
        }
        else {
            this.imageWindowHeight = this.imageHeight;
            this.imageWindowWidth = this.imageWidth;
            this.resizeWithImages = true;
        }
        final String parameter48 = this.getParameter("image_preserve");
        if (parameter48 != null) {
            final StringTokenizer stringTokenizer25 = new StringTokenizer(parameter48, ",");
            final int countTokens5 = stringTokenizer25.countTokens();
            this.preserve = new int[countTokens5];
            if (countTokens5 % 4 != 0) {
                System.out.println("!!!!  Number of coordinates for 'image_preserve' must be a multiple of 4...=" + countTokens5);
                this.preserve = null;
            }
            else {
                for (int n11 = 0; n11 < countTokens5; ++n11) {
                    this.preserve[n11] = Integer.parseInt(stringTokenizer25.nextToken().trim());
                }
            }
        }
        else {
            this.preserve = null;
        }
        (this.can = new AniCanvas(this, this.blank_screen, this.background, this.roamStop, this.preserve, this.myMessages.getProperty("no_images_yet"))).resize(this.imageWindowWidth, this.imageWindowHeight);
        this.can.setShowPortalPointers(showPortalPointers);
        this.can.setLatLonCorners(this.doingNav, this.latlon);
        this.setLayout(new BorderLayout(this.pad, this.pad));
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout(this.pad, this.pad));
        panel5.setBackground(this.background);
        panel5.setForeground(this.foreground);
        final Panel panel6 = new Panel();
        panel6.setLayout(new BorderLayout(this.pad, this.pad));
        panel6.setBackground(this.background);
        panel6.setForeground(this.foreground);
        if (array2[0] != null) {
            panel5.add("North", array2[0]);
        }
        if (array4[0] != null) {
            panel5.add(array4[0]);
        }
        if (array3[0] != null) {
            panel6.add("South", array3[0]);
        }
        panel6.add("North", panel5);
        if (!this.noControls) {
            this.add("North", panel6);
        }
        final Panel panel7 = new Panel();
        panel7.setLayout(new FlowLayout(1, this.pad, this.pad));
        panel7.setBackground(this.background);
        panel7.setForeground(this.foreground);
        panel7.add(this.can);
        this.add("Center", panel7);
        final Panel panel8 = new Panel();
        panel8.setLayout(new BorderLayout(this.pad, this.pad));
        panel8.setBackground(this.background);
        panel8.setForeground(this.foreground);
        final Panel panel9 = new Panel();
        panel9.setLayout(new BorderLayout(this.pad, this.pad));
        panel9.setBackground(this.background);
        panel9.setForeground(this.foreground);
        if (array2[1] != null) {
            panel8.add("North", array2[1]);
        }
        if (array4[1] != null) {
            panel8.add("South", array4[1]);
        }
        if (array3[1] != null) {
            panel9.add("South", array3[1]);
        }
        panel9.add("North", panel8);
        if (!this.noControls) {
            this.add("South", panel9);
        }
        if (this.isSetFrameScrollbar) {
            this.setFrameScrollbar.resize(100, 19);
        }
        this.validate();
        this.show();
        this.setWhichFrames(this.defaultNumFramesChoice);
        this.can.setCurrentFrame(int2);
        if (this.start_looping && this.numFrames > 1) {
            this.isLooping = true;
            if (this.isStartStop) {
                this.startstop.setLabel(this.myMessages.getProperty("stop"));
            }
        }
        if (this.isShowit && !this.fadem) {
            this.showit.setEnabled(!this.isLooping);
        }
        if (this.isZoom) {
            this.zoom.enable(true);
        }
        this.old_enhIndex = -1;
        if (this.enhanceChoice != null) {
            this.enhanceChoice.enable(true);
        }
        if (this.isFader) {
            this.fader.enable(true);
        }
        System.err.println("AnimationS applet (AniS) -- $Revision: 2.91 $ $Date: 2007/06/15 14:28:53 $".replace('$', ' '));
        if (int2 != 0) {
            this.setCurrentFrame(true, int2);
        }
        this.can.setCanZoom(this.activeZoom);
    }
    
    void getFileOfFilenames() {
        boolean b = false;
        try {
            DataInputStream dataInputStream;
            if (this.useArchive) {
                dataInputStream = new DataInputStream(this.getClass().getResourceAsStream(this.fileOfFilenames));
            }
            else {
                URL url = new URL(this.imageBase, this.fileOfFilenames);
                if (url.getProtocol().startsWith("http") && this.useAntiCaching && this.fileOfFilenames.indexOf("?") == -1) {
                    this.antiCacheIndex = (int)(this.rand.nextFloat() * 1.6777216E7f);
                    url = new URL(this.imageBase, this.fileOfFilenames + "?" + this.antiCacheIndex);
                }
                final URLConnection openConnection = url.openConnection();
                openConnection.setUseCaches(false);
                openConnection.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                openConnection.setRequestProperty("Pragma", "no-cache");
                openConnection.connect();
                dataInputStream = new DataInputStream(openConnection.getInputStream());
            }
            final Vector vector = new Vector<String>();
            final Vector<String[]> vector2 = new Vector<String[]>();
            final Vector vector3 = new Vector<String>();
            final Vector<String> vector4 = new Vector<String>();
            while (true) {
                final String line = dataInputStream.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("#")) {
                    continue;
                }
                String s = line.trim();
                if (s.length() == 0) {
                    continue;
                }
                final int index = s.indexOf("\"");
                if (index >= 0) {
                    final int lastIndex = s.lastIndexOf("\"");
                    if (index == lastIndex) {
                        System.err.println("!!!! Format error processing frameLabel in file " + this.fileOfFilenames + " on line = " + s);
                        vector3.addElement(" ");
                    }
                    else {
                        vector3.addElement(s.substring(index + 1, lastIndex));
                    }
                    s = (s.substring(0, index) + s.substring(lastIndex + 1)).trim();
                }
                final int index2 = s.indexOf(" [");
                if (index2 > 0) {
                    final int lastIndex2 = s.lastIndexOf("]");
                    if (lastIndex2 < 0) {
                        System.err.println("!!!!  Format error processing dwell rates in file " + this.fileOfFilenames + " on line = " + s);
                        vector4.addElement("300");
                    }
                    else {
                        b = true;
                        vector4.addElement(s.substring(index2 + 2, lastIndex2));
                        s = (s.substring(0, index2 + 1) + s.substring(lastIndex2 + 1)).trim();
                    }
                }
                final int index3 = s.indexOf(" ");
                if (index3 > 0) {
                    final String trim = s.substring(0, index3).trim();
                    final String trim2 = s.substring(index3).trim();
                    vector.addElement(trim);
                    final StringTokenizer stringTokenizer = new StringTokenizer(trim2, "=");
                    if (stringTokenizer.countTokens() != 2) {
                        System.err.println("!!!! formmatting problem in file " + this.fileOfFilenames + " ... bad line = " + s);
                        System.exit(1);
                    }
                    final String trim3 = stringTokenizer.nextToken().trim();
                    if (trim3.equalsIgnoreCase("overlay")) {
                        final String[] namesUsingList = this.getNamesUsingList(new StringTokenizer(stringTokenizer.nextToken().trim(), ","), -1);
                        vector2.addElement(namesUsingList);
                        if (this.numOverlays < 1) {
                            this.numOverlays = namesUsingList.length;
                        }
                        if (this.numOverlays == namesUsingList.length) {
                            continue;
                        }
                        System.err.println("!!!! You must have the same number of  overlays for each frame in file " + this.fileOfFilenames);
                        System.exit(1);
                    }
                    else if (trim3.equalsIgnoreCase("portal")) {
                        final String[] namesUsingList2 = this.getNamesUsingList(new StringTokenizer(stringTokenizer.nextToken().trim(), ","), -1);
                        vector2.addElement(namesUsingList2);
                        if (this.numPortals < 1) {
                            this.numPortals = namesUsingList2.length;
                        }
                        if (this.numPortals == namesUsingList2.length) {
                            continue;
                        }
                        System.err.println("You must have the same number of  portals for each frame in file " + this.fileOfFilenames);
                        System.exit(1);
                    }
                    else {
                        System.err.println("!!!! formmatting problem in file " + this.fileOfFilenames + ": line contains = but neither 'overlay' or 'portal' in: " + s);
                        System.exit(1);
                    }
                }
                else {
                    vector.addElement(s);
                }
            }
            dataInputStream.close();
            this.numFrames = vector.size();
            if (this.lastFrame < this.firstFrame || this.lastFrame >= this.numFrames) {
                this.lastFrame = this.numFrames - 1;
            }
            if (this.rotator != null) {
                this.rotator.setNumFrames(this.numFrames);
            }
            if (this.isSetFrameScrollbar) {
                this.setFrameScrollbar.setMaximum(this.numFrames);
            }
            this.filenameList = new String[this.numFrames];
            if (vector3.size() == this.numFrames) {
                this.frameLabel = new String[this.numFrames];
            }
            if (b) {
                this.dwellRates = new int[this.numFrames];
            }
            for (int i = 0; i < this.numFrames; ++i) {
                this.filenameList[i] = vector.elementAt(i);
                if (vector3.size() == this.numFrames) {
                    String s2 = "  " + vector3.elementAt(i) + "  ";
                    final int index4 = s2.indexOf("$$");
                    if (index4 > -1) {
                        final int index5 = s2.indexOf("$$", index4 + 1);
                        if (index5 > -1) {
                            final String substring = s2.substring(index4 + 2, index5);
                            new Date();
                            s2 = s2.substring(0, index4 - 1) + " " + new Date(Date.parse(substring)).toString().substring(4) + " " + s2.substring(index5 + 2);
                        }
                    }
                    this.frameLabel[i] = s2.trim();
                }
                if (b) {
                    this.dwellRates[i] = Integer.parseInt(vector4.elementAt(i));
                }
            }
            if (this.numOverlays > 0) {
                this.overFilenameList = new String[this.numOverlays][this.numFrames];
                for (int j = 0; j < this.numFrames; ++j) {
                    final String[] array = vector2.elementAt(j);
                    for (int k = 0; k < this.numOverlays; ++k) {
                        this.overFilenameList[k][j] = array[k];
                    }
                }
            }
            if (this.numPortals > 0) {
                this.portalFilenameList = new String[this.numPortals][this.numFrames];
                for (int l = 0; l < this.numFrames; ++l) {
                    final String[] array2 = vector2.elementAt(l);
                    for (int n = 0; n < this.numPortals; ++n) {
                        this.portalFilenameList[n][l] = array2[n];
                    }
                }
            }
        }
        catch (Exception ex) {
            System.err.println("!!!! Problem: " + ex);
        }
        if (b) {
            this.useDwellRates = true;
        }
    }
    
    String[] getNamesUsingBaseName(final String s, final int n) {
        final String[] array = new String[n];
        for (int i = 0; i < n; ++i) {
            int n2 = i + this.baseNumber;
            if (s.indexOf("*") >= 0) {
                String s2 = " ";
                final int index = s.indexOf("*");
                if (index == 0) {
                    s2 = n2 + s.substring(1);
                }
                else if (index == s.length() - 1) {
                    s2 = s.substring(0, index) + n2;
                }
                else if (index > 0) {
                    s2 = s.substring(0, index) + n2 + s.substring(index + 1);
                }
                array[i] = s2;
            }
            else if (s.indexOf("?") >= 0) {
                String string;
                int lastIndex;
                for (string = s; (lastIndex = string.lastIndexOf("?")) >= 0; string = string.substring(0, lastIndex) + String.valueOf(n2 % 10) + string.substring(lastIndex + 1), n2 /= 10) {}
                array[i] = string;
            }
            else {
                array[i] = s + n2;
            }
        }
        return array;
    }
    
    String[] getNamesUsingList(final StringTokenizer stringTokenizer, int n) {
        final int countTokens = stringTokenizer.countTokens();
        if (n <= 0) {
            n = countTokens;
        }
        final String[] array = new String[n];
        for (int i = 0; i < n; ++i) {
            if (stringTokenizer.hasMoreTokens()) {
                array[i] = stringTokenizer.nextToken().trim();
            }
            else {
                array[i] = array[i - 1];
            }
        }
        return array;
    }
    
    Image[] getImages(final String[] array, final int n, final boolean b, final int n2, final int n3, final int n4) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] array2 = new Image[n];
        final int length = array.length;
        int imageHeight = -1;
        int imageWidth = -1;
        if (b) {
            this.baseImagesURL = new URL[n];
        }
        int n5 = n2;
        URLConnection urlConnection = null;
        String s = "Waiting...";
        for (int i = n2; i <= n3; i += n4) {
            ++this.loadedFrames;
            if (this.useProgressBar) {
                this.can.setProgress(true, this.loadedFrames, this.totalFrames);
                if (this.quietLoadem && !this.veryQuietReload) {
                    this.can.showProgressBar(this.can.getGraphics());
                }
                if (this.totalFrames != 0) {
                    s = this.myMessages.getProperty("loaded") + " " + this.loadedFrames * 100 / this.totalFrames + "%";
                }
            }
            else {
                s = this.myMessages.getProperty("loaded") + " " + this.loadedFrames + " " + this.myMessages.getProperty("out_of") + " " + this.totalFrames;
            }
            this.showStatus(s);
            if (i >= length) {
                array2[i] = array2[i - 1];
            }
            else {
                if (i > n2) {
                    boolean b2 = false;
                    int j = n2;
                    while (j < i) {
                        if (i != j && array[i].equals(array[j])) {
                            array2[i] = array2[j];
                            if (array2[i] == null) {
                                break;
                            }
                            b2 = true;
                            if (this.debug) {
                                System.out.println("####   Duplicate filename for file: " + array[i]);
                                break;
                            }
                            break;
                        }
                        else {
                            j += n4;
                        }
                    }
                    if (b2) {
                        continue;
                    }
                }
                try {
                    URL url;
                    if (this.useArchive) {
                        url = this.getClass().getResource(array[i]);
                        urlConnection = url.openConnection();
                    }
                    else {
                        url = null;
                        if (this.useImageArchive) {
                            url = this.getClass().getResource(array[i]);
                        }
                        if (url == null) {
                            if (array[i].toLowerCase().startsWith("http://")) {
                                url = new URL(array[i]);
                            }
                            else {
                                url = new URL(this.imageBase, array[i]);
                            }
                        }
                        boolean b3 = false;
                        if (url.getProtocol().startsWith("http")) {
                            b3 = true;
                        }
                        if (!this.useCaching || (this.doExcludeCaching && array[i].indexOf(this.excludeCaching) != -1)) {
                            if (this.useAntiCaching && b3 && array[i].indexOf("?") == -1) {
                                this.antiCacheIndex = (int)(this.rand.nextFloat() * 1.6777216E7f);
                                if (array[i].toLowerCase().startsWith("http://")) {
                                    url = new URL(array[i] + "?" + this.antiCacheIndex);
                                }
                                else {
                                    url = new URL(this.imageBase, array[i] + "?" + this.antiCacheIndex);
                                }
                            }
                            urlConnection = url.openConnection();
                            urlConnection.setUseCaches(false);
                            if (this.debug) {
                                System.out.println("####   useCaching = false");
                            }
                            urlConnection.setRequestProperty("Cache-Control", "no-store,max-age=0,no-cache");
                            urlConnection.setRequestProperty("Expires", "0");
                            urlConnection.setRequestProperty("Pragma", "no-cache");
                            urlConnection.connect();
                        }
                        else {
                            urlConnection = url.openConnection();
                            urlConnection.setUseCaches(true);
                            if (this.debug) {
                                System.out.println("####   useCaching = true");
                            }
                        }
                        if (this.debug) {
                            System.out.println("####   URLConn=" + urlConnection);
                        }
                    }
                    if (b) {
                        this.baseImagesURL[i] = url;
                    }
                    if (this.sixlegs && array[i].endsWith("png")) {
                        array2[i] = Toolkit.getDefaultToolkit().createImage((ImageProducer)Class.forName("com.sixlegs.image.png.PngImage").getConstructor(Class.forName("java.net.URL")).newInstance(urlConnection.getURL()));
                    }
                    else {
                        try {
                            if (this.debug) {
                                System.out.println("####   Reading " + array[i]);
                            }
                            final int contentLength = urlConnection.getContentLength();
                            final BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
                            final byte[] array3 = new byte[contentLength];
                            for (int k = 0; k < contentLength; ++k) {
                                final int read = bufferedInputStream.read();
                                if (read == -1) {
                                    break;
                                }
                                array3[k] = (byte)read;
                            }
                            array2[i] = Toolkit.getDefaultToolkit().createImage(array3);
                            bufferedInputStream.close();
                        }
                        catch (Exception ex3) {
                            try {
                                urlConnection.setDefaultUseCaches(urlConnection.getUseCaches());
                                array2[i] = this.getImage(urlConnection.getURL());
                                if (this.refreshWarning) {
                                    System.err.println("Note:  Auto-refresh may not work correctly.");
                                }
                                this.refreshWarning = false;
                            }
                            catch (Exception ex) {
                                System.err.println("!!!!  Problem loading image:" + urlConnection.getURL() + " = " + ex);
                            }
                        }
                    }
                    if (!this.quietLoadem) {
                        mediaTracker.addImage(array2[i], i);
                        mediaTracker.waitForID(i);
                        if (this.debug) {
                            System.err.println("#### trying for image #" + i + " filename=" + array[i]);
                        }
                        if (mediaTracker.isErrorID(i)) {
                            s = this.myMessages.getProperty("missing_image") + ": " + array[i];
                            System.err.println(s);
                            this.showStatus(s);
                            if (i > n2) {
                                array2[i] = array2[n5];
                            }
                            else {
                                array2[i] = null;
                            }
                        }
                        else {
                            this.imageHeight = array2[i].getHeight(this);
                            this.imageWidth = array2[i].getWidth(this);
                            if (this.imageHeight > 0 && this.imageWidth > 0 && i == 0 && this.firstLoad) {
                                if (this.resizeWithImages) {
                                    this.can.resize(this.imageWidth, this.imageHeight);
                                }
                                this.validate();
                                this.firstLoad = false;
                            }
                            if (this.debug) {
                                System.out.println("####   image width=" + this.imageWidth + "  height=" + this.imageHeight);
                            }
                            this.can.setForcedImage(array2[i], s);
                            if (imageHeight < 0 || imageWidth < 0) {
                                imageHeight = this.imageHeight;
                                imageWidth = this.imageWidth;
                            }
                            else if (imageHeight != this.imageHeight || imageWidth != this.imageWidth) {
                                s = this.myMessages.getProperty("images_size") + " " + array[i] + " " + this.myMessages.getProperty("differs");
                                System.err.println(s);
                                this.showStatus(s);
                            }
                        }
                        mediaTracker.removeImage(array2[i]);
                    }
                }
                catch (Exception ex2) {
                    System.err.println("!!!! Error loading Image:" + ex2);
                    ex2.printStackTrace();
                }
                n5 = i;
                if (urlConnection != null && urlConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection)urlConnection).disconnect();
                }
            }
        }
        return array2;
    }
    
    public void setWhichFrames(final int n) {
        if (this.userSetFrames) {
            if (this.numFramesMode[n] == 0) {
                this.setWhichFrames(0, this.numFrames - 1, 1);
            }
            else if (this.numFramesMode[n] == 2) {
                if (this.frameChoices[n] == 1) {
                    this.setWhichFrames(0, 0, 1);
                }
                else {
                    final int n2 = (this.numFrames - 1) / (this.frameChoices[n] - 1);
                    this.setWhichFrames(0, n2 * this.frameChoices[n] - n2, n2);
                }
            }
            else if (this.numFramesMode[n] == -2) {
                if (this.frameChoices[n] == 1) {
                    this.setWhichFrames(this.numFrames - 1, this.numFrames - 1, 1);
                }
                else {
                    final int n3 = (this.numFrames - 1) / (this.frameChoices[n] - 1);
                    this.setWhichFrames(this.numFrames - 1 - n3 * this.frameChoices[n] + n3, this.numFrames - 1, n3);
                }
            }
            else if (this.numFramesMode[n] == 1) {
                this.setWhichFrames(0, this.frameChoices[n] - 1, 1);
            }
            else if (this.numFramesMode[n] == -1) {
                this.setWhichFrames(this.numFrames - this.frameChoices[n], this.numFrames - 1, 1);
            }
            else {
                this.setWhichFrames(this.firstFrame, this.lastFrame, this.incFrame);
            }
        }
        else {
            this.setWhichFrames(0, this.numFrames - 1, 1);
        }
    }
    
    public void setWhichFrames(final int firstFrame, final int lastFrame, final int incFrame) {
        final boolean isLooping = this.isLooping;
        this.isLooping = false;
        this.can.getZoomState();
        if (!this.keepZoom) {
            this.can.resetZoom();
        }
        this.setCurrentFrame(true, this.firstFrame);
        this.loopDirection = 1;
        if (this.debug) {
            System.out.println("####  first, last, inc=" + firstFrame + " " + lastFrame + " " + incFrame);
        }
        this.firstFrame = firstFrame;
        if (this.firstFrame < 0) {
            this.firstFrame = 0;
        }
        if (this.firstFrame >= this.numFrames) {
            this.firstFrame = 0;
        }
        this.lastFrame = lastFrame;
        if (this.lastFrame < this.firstFrame) {
            this.lastFrame = this.firstFrame;
        }
        if (this.lastFrame >= this.numFrames) {
            this.lastFrame = this.numFrames - 1;
        }
        this.incFrame = incFrame;
        if (this.incFrame >= this.numFrames) {
            this.incFrame = this.numFrames - 1;
        }
        if (this.incFrame < 1) {
            this.incFrame = 1;
        }
        if (this.debug) {
            System.out.println("####  now first, last, inc=" + this.firstFrame + " " + this.lastFrame + " " + this.incFrame);
        }
        if (this.isOnOff) {
            this.isOnOff = false;
            for (int i = 0; i < this.numFrames; ++i) {
                if (this.onoff[i] != null) {
                    this.onoff[i].setState(false);
                }
            }
            this.ptonoff.removeAll();
            for (int j = 0; j < this.numFrames; ++j) {
                (this.onoff[j] = new OnOffCanvas(this, j)).setBackground(Color.green);
                this.onoff[j].resize(this.onOffWidth, this.onOffHeight);
                this.onoff[j].setState(false);
                this.onoff[j].setActive(false);
            }
            if (this.showAllOnOff) {
                for (int k = 0; k < this.numFrames; ++k) {
                    this.ptonoff.add(this.onoff[k]);
                }
            }
            for (int l = this.firstFrame; l <= this.lastFrame; l += this.incFrame) {
                this.onoff[l].setActive(true);
                if (!this.showAllOnOff) {
                    this.ptonoff.add(this.onoff[l]);
                }
            }
            this.isOnOff = true;
        }
        this.reloadImages();
        this.isLooping = isLooping;
        if (this.isShowit && !this.fadem) {
            this.showit.setEnabled(!this.isLooping);
        }
        this.repaint();
    }
    
    public void setFilenameList(final String[] filenameList) {
        final boolean isLooping = this.isLooping;
        this.isLooping = false;
        this.filenameList = filenameList;
        this.numFrames = this.filenameList.length;
        this.lastFrame = this.numFrames - 1;
        this.setCurrentFrame(true, this.firstFrame);
        if (this.rotator != null) {
            this.rotator.setNumFrames(this.numFrames);
        }
        if (this.isSetFrameScrollbar) {
            this.setFrameScrollbar.setMaximum(this.numFrames);
        }
        this.isLooping = isLooping;
        if (this.isShowit && !this.fadem) {
            this.showit.setEnabled(!this.isLooping);
        }
    }
    
    public void setOverlayFilenameList(final String[][] overFilenameList) {
        this.overFilenameList = overFilenameList;
    }
    
    public void setPortalFilenameList(final String[][] portalFilenameList) {
        this.portalFilenameList = portalFilenameList;
    }
    
    public boolean getOverlayState(final int n) {
        return this.isOverlay && n >= 0 && n < this.overlay.length && this.overlay[n].getState();
    }
    
    public synchronized void reloadImages() {
        final boolean isLooping = this.isLooping;
        this.isLooping = false;
        this.can.getZoomState();
        if (!this.keepZoom) {
            this.can.resetZoom();
            this.myFrame.setCursor(0);
        }
        this.setCurrentFrame(true, this.firstFrame);
        if (this.fadem) {
            this.numFrames = this.filenameList.length;
            this.lastFrame = this.numFrames - 1;
            if (this.rotator != null) {
                this.rotator.setNumFrames(this.numFrames);
            }
            if (this.isSetFrameScrollbar) {
                this.setFrameScrollbar.setMaximum(this.numFrames);
            }
        }
        if (this.fileOfFilenames != null) {
            this.getFileOfFilenames();
        }
        this.myFrame.setCursor(3);
        boolean baseStatic = this.baseStatic;
        if (this.baseImages != null && (this.baseImages.length != this.numFrames || !this.baseStatic)) {
            baseStatic = false;
            for (int i = 0; i < this.baseImages.length; ++i) {
                if (this.baseImages[i] != null) {
                    this.baseImages[i].flush();
                    this.baseImages[i] = null;
                }
            }
        }
        if (this.isOverlay && this.numOverlays < 1) {
            final String parameter = this.getParameter("overlay_basenames");
            final String parameter2 = this.getParameter("overlay_filenames");
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                this.numOverlays = stringTokenizer.countTokens();
                this.overFilenameList = new String[this.numOverlays][];
                for (int j = 0; j < this.numOverlays; ++j) {
                    this.overFilenameList[j] = this.getNamesUsingBaseName(stringTokenizer.nextToken().trim(), this.numFrames);
                }
            }
            else if (parameter2 != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2, ",");
                this.numOverlays = stringTokenizer2.countTokens();
                this.overFilenameList = new String[this.numOverlays][];
                for (int k = 0; k < this.numOverlays; ++k) {
                    this.overFilenameList[k] = this.getNamesUsingList(new StringTokenizer(stringTokenizer2.nextToken().trim(), "&"), this.numFrames);
                }
            }
            else {
                System.err.println("!!!! No filenames found in PARAMeters...");
            }
        }
        if (this.isPortal) {
            if (this.numPortals < 1) {
                final String parameter3 = this.getParameter("portal_basenames");
                final String parameter4 = this.getParameter("portal_filenames");
                if (parameter3 != null) {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter3, ",");
                    this.numPortals = stringTokenizer3.countTokens();
                    this.portalFilenameList = new String[this.numPortals][];
                    for (int l = 0; l < this.numPortals; ++l) {
                        this.portalFilenameList[l] = this.getNamesUsingBaseName(stringTokenizer3.nextToken().trim(), this.numFrames);
                    }
                }
                else if (parameter4 != null) {
                    final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter4, ",");
                    this.numPortals = stringTokenizer4.countTokens();
                    this.portalFilenameList = new String[this.numPortals][];
                    for (int n = 0; n < this.numPortals; ++n) {
                        this.portalFilenameList[n] = this.getNamesUsingList(new StringTokenizer(stringTokenizer4.nextToken().trim(), "&"), this.numFrames);
                    }
                }
                else {
                    System.err.println("!!!! No filenames found in PARAMeters...");
                }
            }
            if (this.numPortals != this.numPortalLocs) {
                System.err.println("!!!! Number of portal filenames (" + this.numPortals + ") must be equal to number of portal locations (" + this.numPortalLocs + "(");
                System.exit(1);
            }
        }
        this.totalFrames = 0;
        this.loadedFrames = 0;
        final int totalFrames = (this.lastFrame - this.firstFrame) / this.incFrame + 1;
        if (this.debug) {
            System.out.println("####  actual=" + totalFrames + " numFrames=" + this.numFrames);
        }
        if (this.firstLoad || !baseStatic) {
            if (this.baseStatic) {
                this.totalFrames = this.numFrames;
            }
            else {
                this.totalFrames = totalFrames;
            }
        }
        if (this.isOverlay) {
            for (int n2 = 0; n2 < this.numOverlays; ++n2) {
                if (this.firstLoad) {
                    if (this.overlayStatic[n2]) {
                        this.totalFrames += this.numFrames;
                    }
                    else {
                        this.totalFrames += totalFrames;
                    }
                }
                else if (!this.overlayStatic[n2]) {
                    this.totalFrames += totalFrames;
                }
            }
        }
        if (this.debug) {
            System.out.println("####  totalFrames = " + this.totalFrames);
        }
        if (this.isPortal) {
            this.totalFrames += this.numPortals * totalFrames;
        }
        if (this.firstLoad || !baseStatic || this.baseImages == null) {
            if (!this.fadem) {
                if (this.baseStatic) {
                    this.baseImages = this.getImages(this.filenameList, this.numFrames, true, 0, this.numFrames - 1, 1);
                }
                else {
                    this.baseImages = this.getImages(this.filenameList, this.numFrames, true, this.firstFrame, this.lastFrame, this.incFrame);
                }
            }
            else {
                final Image[] images = this.getImages(this.filenameList, this.numFrames, false, 0, this.numFrames - 1, 1);
                this.can.pleaseWaitMessage(this.myMessages.getProperty("wait_faded_images"));
                this.baseImages = new FadeImages(this, images, this.transparency).getImages();
                this.numFadesPerFrame = this.baseImages.length / Math.max(this.numFrames - 1, 2);
                this.numFrames = this.baseImages.length;
                this.lastFrame = this.numFrames - 1;
                if (this.rotator != null) {
                    this.rotator.setNumFrames(this.numFrames);
                }
                if (this.isSetFrameScrollbar) {
                    this.setFrameScrollbar.setMaximum(this.numFrames);
                }
                if (this.isFader) {
                    this.setCurrentFrame(true, this.firstFrame);
                    this.fader.setValues(this.currentFrame, 1, this.firstFrame, this.numFrames);
                }
                this.isOnOff = false;
                if (this.isStartStop) {
                    this.startstop.setLabel(this.myMessages.getProperty("start"));
                    this.isLooping = false;
                    if (this.isShowit && !this.fadem) {
                        this.showit.setEnabled(true);
                    }
                    this.setCurrentFrame(true, this.firstFrame);
                }
            }
        }
        this.baseImageWidth = this.imageWidth;
        this.baseImageHeight = this.imageHeight;
        if (this.debug) {
            System.out.println("####  base width=" + this.baseImageWidth + " height=" + this.baseImageHeight);
        }
        if (this.resizeWithImages) {
            this.can.resize(this.baseImageWidth, this.baseImageHeight);
        }
        this.validate();
        this.can.setBaseImages(this.baseImages, this.baseImageWidth, this.baseImageHeight);
        if (this.isOverlay) {
            final long currentTimeMillis = System.currentTimeMillis();
            boolean b = false;
            if (this.overlayImages != null) {
                final int length = this.overlayImages.length;
                final int length2 = this.overlayImages[0].length;
                for (int n3 = 0; n3 < length; ++n3) {
                    if (!this.overlayStatic[n3]) {
                        if (this.overlayImages[n3] != null) {
                            for (int n4 = 0; n4 < length2; ++n4) {
                                if (this.overlayImages[n3][n4] != null) {
                                    this.overlayImages[n3][n4].flush();
                                    this.overlayImages[n3][n4] = null;
                                }
                            }
                        }
                    }
                }
                b = true;
                if (length != this.numOverlays || length2 != this.numFrames) {
                    this.overlayImages = new Image[this.numOverlays][this.numFrames];
                    b = false;
                }
            }
            else {
                this.overlayImages = new Image[this.numOverlays][this.numFrames];
            }
            for (int n5 = 0; n5 < this.numOverlays; ++n5) {
                if (!b || !this.overlayStatic[n5] || this.overlayImages[n5] == null) {
                    if (this.useTransparentImage) {
                        if (this.overlayStatic[n5]) {
                            this.overlayImages[n5] = this.getImages(this.overFilenameList[n5], this.numFrames, false, 0, this.numFrames - 1, 1);
                        }
                        else {
                            this.overlayImages[n5] = this.getImages(this.overFilenameList[n5], this.numFrames, false, this.firstFrame, this.lastFrame, this.incFrame);
                        }
                    }
                    else {
                        int firstFrame;
                        int lastFrame;
                        int incFrame;
                        if (this.overlayStatic[n5]) {
                            firstFrame = 0;
                            lastFrame = this.numFrames - 1;
                            incFrame = 1;
                        }
                        else {
                            firstFrame = this.firstFrame;
                            lastFrame = this.lastFrame;
                            incFrame = this.incFrame;
                        }
                        final Image[] images2 = this.getImages(this.overFilenameList[n5], this.numFrames, false, firstFrame, lastFrame, incFrame);
                        for (int n6 = firstFrame; n6 <= lastFrame; n6 += incFrame) {
                            if (images2[n6] != null) {
                                if (n6 > firstFrame && images2[n6] == images2[n6 - incFrame]) {
                                    this.overlayImages[n5][n6] = this.overlayImages[n5][n6 - incFrame];
                                }
                                else {
                                    TransparentImage transparentImage;
                                    if (this.transparency == this.transparency2) {
                                        transparentImage = new TransparentImage(this, images2[n6], this.overlayTransparentAmount[n5], this.transparency);
                                    }
                                    else {
                                        transparentImage = new TransparentImage(this, images2[n6], this.overlayTransparentAmount[n5], this.transparency, this.transparency2);
                                    }
                                    this.overlayImages[n5][n6] = transparentImage.getImage();
                                }
                            }
                            else {
                                this.overlayImages[n5][n6] = null;
                            }
                        }
                        for (int n7 = 0; n7 < this.numFrames; ++n7) {
                            if (images2[n7] != null) {
                                images2[n7].flush();
                                images2[n7] = null;
                            }
                        }
                    }
                }
            }
            this.can.setOverlayOrder(this.overlayOrder);
            this.can.setOverlayZoom(this.overlayZoom);
            this.can.setOverlays(this.overlayImages, this.overlay, this.overlayX, this.overlayY);
            if (this.debug) {
                System.out.println("####  Elapsed time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
        }
        if (this.isPortal) {
            if (this.portalImages != null) {
                for (int n8 = 0; n8 < this.portalImages.length; ++n8) {
                    if (this.portalImages[n8] != null) {
                        for (int n9 = 0; n9 < this.portalImages[n8].length; ++n9) {
                            if (this.portalImages[n8][n9] != null) {
                                this.portalImages[n8][n9].flush();
                                this.portalImages[n8][n9] = null;
                            }
                        }
                    }
                }
            }
            this.portalImages = new Image[this.numPortals][];
            for (int n10 = 0; n10 < this.numPortals; ++n10) {
                this.portalImages[n10] = this.getImages(this.portalFilenameList[n10], this.numFrames, false, 0, this.numFrames - 1, 1);
            }
            this.can.setPortals(this.portalImages, this.portalWidth, this.portalHeight, this.portalX, this.portalY, this.portalScaling);
        }
        this.imageHeight = this.baseImageHeight;
        this.imageWidth = this.baseImageWidth;
        this.myFrame.setCursor(0);
        this.isLooping = isLooping;
        if (this.isShowit && !this.fadem) {
            this.showit.setEnabled(!this.isLooping);
        }
        if (this.useProgressBar) {
            this.can.setProgress(false, -1, -1);
        }
        this.can.setCurrentFrame(this.currentFrame);
        this.quietLoadem = this.quietReload;
        this.repaint();
        System.gc();
    }
    
    public synchronized void setEnhancement(final int old_enhIndex) {
        if (old_enhIndex == this.old_enhIndex) {
            return;
        }
        if (this.enhancedImages == null) {
            this.enhancedImages = new Image[this.baseImages.length];
            for (int i = 0; i < this.baseImages.length; ++i) {
                this.enhancedImages[i] = this.baseImages[i];
            }
        }
        this.baseImages = this.enh.EnhanceImages(this, this.enhancedImages, old_enhIndex - 1);
        this.can.setBaseImages(this.baseImages, this.baseImageWidth, this.baseImageHeight);
        this.old_enhIndex = old_enhIndex;
        if (this.enhanceChoice != null) {
            this.enhanceChoice.select(old_enhIndex);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.startstop) {
            this.isLooping = !this.isLooping;
            if (this.isLooping && this.isStartStop) {
                this.startstop.setLabel(this.myMessages.getProperty("stop"));
            }
            else {
                this.startstop.setLabel(this.myMessages.getProperty("start"));
            }
            if (this.isShowit && !this.fadem) {
                this.showit.setEnabled(!this.isLooping);
            }
            return true;
        }
        if (event.target == this.showit) {
            try {
                String string = "_self";
                if (!this.showSame) {
                    string = this.baseImagesURL[this.currentFrame].toString();
                }
                this.getAppletContext().showDocument(this.baseImagesURL[this.currentFrame], string);
            }
            catch (Exception ex) {
                System.out.println("!!!! showDocument problem:" + ex);
            }
            return true;
        }
        if (event.target == this.autoOnOff) {
            if (this.isAuto) {
                this.stopAutoRefresh();
            }
            else {
                this.startAutoRefresh();
            }
            return true;
        }
        if (event.target == this.playAudio) {
            try {
                this.play(new URL(this.imageBase, this.audioFilename));
            }
            catch (Exception ex2) {
                System.err.println(ex2);
            }
            return true;
        }
        if (event.target == this.numFramesChoice) {
            this.setWhichFrames(this.numFramesChoice.getSelectedIndex());
            return true;
        }
        if (event.target == this.enhanceChoice) {
            this.setEnhancement(this.enhanceChoice.getSelectedIndex());
            return true;
        }
        if (event.target == this.refresh) {
            this.reloadImages();
            return true;
        }
        if (event.target == this.stepBack) {
            this.setCurrentFrame(false, this.loopDirection = -1, true);
            return true;
        }
        if (event.target == this.stepForward) {
            this.setCurrentFrame(false, this.loopDirection = 1, true);
            return true;
        }
        if (event.target == this.stepFirst) {
            if (this.isStartStop) {
                this.startstop.setLabel(this.myMessages.getProperty("start"));
            }
            this.isLooping = false;
            this.setCurrentFrame(true, this.firstFrame);
            if (this.isShowit && !this.fadem) {
                this.showit.setEnabled(!this.isLooping);
            }
            return true;
        }
        if (event.target == this.stepLast) {
            if (this.isStartStop) {
                this.startstop.setLabel(this.myMessages.getProperty("start"));
            }
            this.isLooping = false;
            this.setCurrentFrame(true, this.numFrames - 1);
            if (this.isShowit && !this.fadem) {
                this.showit.setEnabled(!this.isLooping);
            }
            return true;
        }
        if (event.target == this.zoom) {
            if (!this.isZoomed) {
                this.myFrame.setCursor(12);
                this.zoom.setLabel(this.myMessages.getProperty("reset"));
                this.isZoomed = true;
                this.can.setCanZoom(true);
            }
            else {
                this.myFrame.setCursor(0);
                this.can.resetZoom();
                this.can.repaint();
            }
            this.validate();
            return true;
        }
        if (event.target == this.looprock) {
            this.isRocking = !this.isRocking;
            if (this.isRocking) {
                this.looprock.setLabel(this.myMessages.getProperty("loop"));
            }
            else {
                this.looprock.setLabel(this.myMessages.getProperty("rock"));
                this.setCurrentFrame(false, this.loopDirection = 1);
            }
            return true;
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.key == 1007 || event.key == 1006) {
            if (event.id == 403 && this.isStepping) {
                int n2;
                if (event.key == 1007) {
                    n2 = 1;
                }
                else {
                    n2 = -1;
                }
                this.setCurrentFrame(false, n2);
            }
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1004) {
            return false;
        }
        for (int i = 0; i < this.numOverlayLabels; ++i) {
            if (event.target == this.overlay[i]) {
                if (this.overlayLinks[i] > 0) {
                    for (int j = 0; j < this.numOverlayLabels; ++j) {
                        if (this.overlayLinks[j] == -this.overlayLinks[i]) {
                            if (this.overlayHidden[j]) {
                                this.overlay[j].setState(this.overlay[i].getState());
                            }
                            else if (this.overlay[i].getState()) {
                                this.overlay[j].setState(true);
                            }
                        }
                    }
                }
                if (this.overlay[i].getCheckboxGroup() != null) {
                    for (int k = 0; k < this.numOverlayLabels; ++k) {
                        if (this.overlay[k].getCheckboxGroup() != null && this.overlayLinks[k] > 0) {
                            for (int l = 0; l < this.numOverlayLabels; ++l) {
                                if (this.overlayLinks[l] == -this.overlayLinks[k] && this.overlayHidden[l]) {
                                    this.overlay[l].setState(this.overlay[k].getState());
                                }
                            }
                        }
                    }
                }
                this.can.repaint();
            }
        }
        if (event.target == this.fader) {
            final int value = this.fader.getValue();
            if (value == this.currentFrame) {
                return true;
            }
            this.setCurrentFrame(true, value);
        }
        if (event.target == this.setFrameScrollbar) {
            final int value2 = this.setFrameScrollbar.getValue();
            if (value2 == this.currentFrame) {
                return true;
            }
            this.setCurrentFrame(true, value2);
        }
        return super.handleEvent(event);
    }
    
    public void setUnzoomed() {
        if (this.isZoom) {
            this.zoom.setLabel(this.myMessages.getProperty("zoom"));
            this.myFrame.setCursor(0);
            if (this.can != null) {
                this.can.setCanZoom(false);
            }
        }
        this.isZoomed = false;
    }
    
    public synchronized void setCurrentFrame(final boolean b, final int n) {
        this.setCurrentFrame(b, n, false);
    }
    
    public synchronized void setCurrentFrame(final boolean b, int currentFrame, final boolean b2) {
        if (!b) {
            currentFrame *= this.incFrame;
        }
        if (this.numFrames <= 0) {
            return;
        }
        if (this.isOnOff && this.currentFrame != -1) {
            if (this.onoff[this.currentFrame].getState()) {
                this.onoff[this.currentFrame / this.numFadesPerFrame].setColor(Color.green);
            }
            else {
                this.onoff[this.currentFrame / this.numFadesPerFrame].setColor(Color.red);
            }
        }
        if (b) {
            this.currentFrame = currentFrame;
        }
        else {
            do {
                this.currentFrame += currentFrame;
                if (this.currentFrame > this.lastFrame) {
                    if (this.isRocking && !b2) {
                        this.currentFrame = this.lastFrame - this.incFrame;
                        if (this.currentFrame < this.firstFrame) {
                            this.currentFrame = this.firstFrame;
                        }
                        this.loopDirection = -this.loopDirection;
                        currentFrame = -currentFrame;
                    }
                    else {
                        this.currentFrame = this.firstFrame;
                    }
                }
                else if (this.currentFrame < this.firstFrame) {
                    if (this.isRocking && !b2) {
                        this.currentFrame = this.firstFrame + this.incFrame;
                        this.loopDirection = -this.loopDirection;
                        currentFrame = -currentFrame;
                    }
                    else {
                        this.currentFrame = this.lastFrame;
                    }
                }
                if (!this.isOnOff) {
                    break;
                }
            } while (!this.onoff[this.currentFrame / this.numFadesPerFrame].getState());
        }
        if (this.isOnOff) {
            this.onoff[this.currentFrame / this.numFadesPerFrame].setColor(Color.blue);
        }
        if (this.rotator != null) {
            this.rotator.setCurrentFrame(this.currentFrame / this.numFadesPerFrame);
        }
        if (this.currentFrame == this.numFrames - 1) {
            if (this.debug) {
                System.out.println("####  Memory used=" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            }
            if (this.pausePercent > 0) {
                if (this.framesPerSecond > 10) {
                    this.addDwell = this.dwell * this.pausePercent / 100;
                }
                else {
                    this.addDwell = this.dwell;
                }
            }
            else {
                this.addDwell = this.pauseOnLast;
            }
        }
        else {
            this.addDwell = 0;
        }
        if (this.isSetFrameScrollbar) {
            final int index = this.setFrameText.indexOf("*");
            this.frameScrollbarLabel.setText(this.setFrameText.substring(0, index) + (this.currentFrame + 1) + this.setFrameText.substring(index + 1));
            this.setFrameScrollbar.setValue(this.currentFrame);
        }
        this.can.setCurrentFrame(this.currentFrame);
        if (this.hasFrameLabel && this.frameLabel != null) {
            this.frameLabelField.setText(this.frameLabel[this.currentFrame / this.numFadesPerFrame]);
        }
    }
    
    public void start() {
        if (this.debug) {
            System.out.println("####   AniS got start...");
        }
        if (this.animator_thread == null) {
            this.alive = true;
            (this.animator_thread = new Thread(this)).start();
        }
        if (this.debug) {
            System.out.println("####   autoState=" + this.autoState);
        }
        if (this.autoState) {
            this.startAutoRefresh();
        }
    }
    
    public void destroy() {
        if (this.debug) {
            System.out.println("####   AniS got destroy...");
        }
        this.stopAutoRefresh();
        this.stop();
    }
    
    public void stop() {
        if (this.debug) {
            System.out.println("####   AniS got stop...");
        }
        this.alive = false;
        this.animator_thread = null;
    }
    
    public void startAutoRefresh() {
        if (this.debug) {
            System.out.println("####  startAutoRefresh called");
        }
        if (this.refreshRate > 0 && this.ri == null) {
            (this.ri = new RefreshImages(this.refreshRate, this)).start();
        }
        this.isAuto = true;
        if (this.autoOnOff != null) {
            this.autoOnOff.setLabel(this.myMessages.getProperty("auto_off"));
        }
    }
    
    public void stopAutoRefresh() {
        if (this.debug) {
            System.out.println("####  stopAutoRefresh called");
        }
        if (this.ri != null && this.ri.isAlive()) {
            this.ri.killit();
        }
        this.ri = null;
        this.isAuto = false;
        if (this.autoOnOff != null) {
            this.autoOnOff.setLabel(this.myMessages.getProperty("auto_on"));
        }
    }
    
    public void run() {
        while (this.alive && Thread.currentThread() == this.animator_thread) {
            if (this.isLooping) {
                this.setCurrentFrame(false, this.loopDirection);
                if (this.useDwellRates) {
                    this.dwell = this.dwellRates[this.currentFrame];
                    this.addDwell = 0;
                }
                else {
                    if (this.isSpeed) {
                        this.framesPerSecond = this.speed.getValue();
                    }
                    this.dwell = 10000 / this.framesPerSecond;
                    if (this.dwell < this.minDwell) {
                        this.dwell = this.minDwell;
                    }
                }
                try {
                    Thread.sleep(this.dwell + this.addDwell);
                }
                catch (InterruptedException ex) {}
            }
            else {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
}
