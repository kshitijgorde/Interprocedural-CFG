import java.util.Random;
import java.io.File;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.util.Date;
import java.awt.image.PixelGrabber;
import java.awt.Shape;
import java.net.URLConnection;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.awt.Rectangle;
import java.awt.MediaTracker;
import java.awt.Image;
import netscape.javascript.JSObject;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Touchup extends Applet implements ActionListener, MouseMotionListener, WindowListener
{
    private boolean isApplet;
    private Frame appletFrame;
    private final int minEditableWidth = 150;
    private boolean tooSmallToEdit;
    private final String touchSiteStr = "http://members.shaw.ca/jonespm2/software.htm";
    private final String imageDispStr = "Image displayed by";
    private final String copyString = "© 2002 by Peter Jones";
    private final String emailStr = "(jonespm2@shaw.ca)";
    private final String appNameStr = "Touchup";
    private final String appVerStr = "3.0a";
    private final String imageParam = "image";
    private final String saveURLParam = "record";
    private final String specialParam = "special";
    private final String lowResParam = "lowres";
    private final String regNameParam = "regname";
    private final String regURLParam = "regurl";
    private final String regCodeParam = "regcode";
    private final String exifOrientParam = "exiforient";
    private final String languageParam = "secondarylanguage";
    private final String debugParam = "debug";
    private final String titleParam = "title";
    private final String titleJustifyParam = "justify";
    private final String infoParam = "info";
    private final String userBtnTitleParam = "btntitle";
    private final String userBtnJScriptParam = "btnscript";
    private final String jScriptParam = "clickscript";
    private final String clickTitleParam = "clicktitle";
    private final String entryJScriptParam = "entryscript";
    private final String exitJScriptParam = "exitscript";
    private final String linkParam = "link";
    private final String linkTargetParam = "target";
    private final String pageColorParam = "pagecolor";
    private final String backgroundParam = "background";
    private final String foregroundParam = "foreground";
    private final String gammaParam = "gamma";
    private final String brightnessParam = "brightness";
    private final String contrastParam = "contrast";
    private final String posterParam = "posterize";
    private final String saturationParam = "saturation";
    private final String alphaColorParam = "alphacolor";
    private final String alphaParam = "alpha";
    private final String colorParam = "color";
    private final String negativeParam = "negative";
    private final String rotateParam = "rotate";
    private final String mirrorParam = "mirror";
    private final String flipParam = "flip";
    private final String zoomParam = "zoom";
    private final String horizParam = "horizontal";
    private final String vertParam = "vertical";
    private final String redParam = "red";
    private final String blueParam = "blue";
    private final String greenParam = "green";
    private final String redChannelParam = "redchannel";
    private final String blueChannelParam = "bluechannel";
    private final String greenChannelParam = "greenchannel";
    private final String modeParam = "mode";
    private final String haloParam = "halo";
    private final String fakeSkyParam = "fakesky";
    private final String syncBtnParam = "showsync";
    private final String descTextParam = "desctext";
    private final String descFgColorParam = "descfgcolor";
    private final String descBgColorParam = "descbgcolor";
    private final String noiseLevelParam = "noiseLevel";
    private final String noiseFreqParam = "noiseFrequency";
    private final String distanceScaleParam = "distScale";
    private final String distanceUnitsParam = "distUnits";
    private final int langCount = 20;
    private String[] languageText;
    private int[] languageTextLen;
    private String languageFile;
    private int phraseCount;
    private int[] phraseStart;
    private JSObject mainWindow;
    private Image myPictureMod;
    private Image zoomImage;
    private boolean zoomAreaOn;
    private int zoomMouseX;
    private int zoomMouseY;
    private boolean zoomDistOn;
    private int zoomMouseXStartDist;
    private int zoomMouseYStartDist;
    private int zoomX;
    private int zoomY;
    private final int zoomSizeDefault = 160;
    private int zoomSize;
    private int zoomSizeHalf;
    private int zoomSizeQuarter;
    private int zoomBorder;
    private int zoomAreaFactor;
    private int pixelXZoomStart;
    private int pixelYZoomStart;
    private final String distUnitsDefault = "pixels";
    private float distScale;
    private String distUnits;
    private int[] originalPixels;
    private int[] resultPixels;
    private int resultWidth;
    private int resultHeight;
    private TouchupImageFilter colorFilter;
    private MediaTracker medTracker;
    private int mainImageBytesTotal;
    private int mainImageBytesRead;
    private long mainImageStartLoad;
    private String imageLastModified;
    private String imageLoadErrString;
    private int mainImageLoadCount;
    private VWrappingLabel descArea;
    private String descText;
    private boolean descShown;
    private String descFgColor;
    private String descBgColor;
    int descAdj;
    private boolean browserIsNetscape;
    private final int modeHidden = 0;
    final int modeControls = 1;
    final int modeZoom = 2;
    final int modeInfo = 3;
    private String saveModeURL;
    private boolean inDumpCode;
    private String special;
    private String regName;
    private String regURL;
    private String regCode;
    private String regDescription;
    private boolean registered;
    private boolean exifOrientEnabled;
    private boolean touchupDebug;
    private int exifOrientation;
    private int languageIndex;
    private int languageCount;
    private boolean languageUseSecondary;
    int touchupMode;
    private boolean loaded;
    private boolean hidden;
    private int horizValue;
    private int vertValue;
    private float zoomValue;
    private float tuningAmount;
    private int paintCount;
    private String enterTouchupScript;
    private String exitedTouchupScript;
    private String userBtnTitle;
    private String userBtnJScript;
    String callDetails;
    String clickTitle;
    String linkURL;
    private String linkTarget;
    private String imageTitle;
    private String titleJustify;
    private String pageColor;
    private String backgroundColor;
    private String foregroundColor;
    private String alphaColor;
    private String imageValue;
    private boolean imageProtected;
    boolean viewOnly;
    private boolean basicOnly;
    private String docBaseURL;
    boolean TouchupShown;
    private boolean textHalo;
    private boolean colorButtons;
    private boolean advancedButtons;
    private boolean getAppletsSupported;
    private boolean showSyncButton;
    private boolean lowResLoaded;
    private Image myLowResMod;
    private boolean spriteTest;
    private Image spriteImage;
    private int spriteW;
    private int spriteH;
    private boolean spriteLoaded;
    private int[] spritePixels;
    private boolean imageLoaded;
    private boolean loadingImage;
    int imageHeight;
    int imageWidth;
    private int filteredHeight;
    private int filteredWidth;
    private int appletWidth;
    private int appletHeight;
    int minWidth;
    int minHeight;
    private String browserNavigatorInfo;
    private int histogramDisplay;
    private String previousImage;
    private boolean firstImageLoad;
    private boolean paintRequiresErase;
    private Image offscreen;
    private boolean updatingEnabled;
    private boolean updatePending;
    String messageBarString;
    private boolean justStarted;
    String infoString;
    private String exifString;
    private Rectangle infoRect;
    private Rectangle interfaceRect;
    private int pressedBtnIndex;
    private int pressedXCoord;
    private int pressedYCoord;
    private boolean pressedDragStarted;
    Rectangle[] btnRect;
    int[] btnID;
    private int currButtonIndex;
    private int currButtonID;
    int btnCount;
    int btnMiniStart;
    private int btnMaxY;
    private Image btnInterface;
    private VWrappingLabel pictureInfoArea;
    private String pictureInfoText;
    private Image btnMiniPanelEdit;
    private Image btnMiniPanelZoom;
    private Image btnMiniPanelInfo;
    private Image btnColorPanel;
    private Image btnSavePic;
    private boolean saveMode;
    private final int saveBtnXOffset = 143;
    private final int saveBtnYOffset = 54;
    private final int infoHeight = 18;
    private final int interfaceTop = 0;
    private final int interfaceLeft = 0;
    private final int interfaceHeight;
    private final int interfaceWidth = 208;
    private final int btnSize = 23;
    private MediaTracker btnMedTracker;
    private int statusBaseLine;
    private int graphLeft;
    private final int graphWidth = 125;
    private final int graphMargin = 14;
    private final int graphBoxSize = 15;
    private final int graphButtonGap = 8;
    private final int graphDefaultTextIndent = 6;
    private Rectangle graphRect;
    private Rectangle graphFrameRect;
    private Rectangle graphLessRect;
    private Rectangle graphMoreRect;
    private Rectangle graphDefaultRect;
    private int graphBarY;
    private int graphBarLeft;
    private int graphBarRight;
    private float graphDefaultValue;
    private float graphLeftValue;
    private float graphMidValue;
    private float graphRightValue;
    private String graphTitle;
    private float graphValue;
    private float graphAddAmount;
    private boolean graphValueInteger;
    private int graphIndicatorX;
    private boolean graphChanged;
    private boolean graphOn;
    private final int NOISE_LEVEL_DEFAULT = -30;
    private final int ID_DONE = 0;
    private final int ID_RESET = 1;
    private final int ID_GAMMA = 2;
    private final int ID_BRIGHTNESS = 3;
    private final int ID_CONTRAST = 4;
    private final int ID_ALPHA = 5;
    private final int ID_POSTERIZE = 6;
    private final int ID_NOISEFREQ = 7;
    private final int ID_NOISELEVEL = 8;
    private final int ID_RED = 9;
    private final int ID_GREEN = 10;
    private final int ID_BLUE = 11;
    private final int ID_SATURATION = 12;
    private final int ID_NEGATIVE = 13;
    private final int ID_BLACKANDWHITE = 14;
    private final int ID_HISTOGRAM = 15;
    private final int ID_LANGUAGE = 16;
    private final int ID_CUSTOM = 17;
    private final int ID_ZOOM = 18;
    private final int ID_HORIZONTAL = 19;
    private final int ID_VERTICAL = 20;
    private final int ID_MIRROR = 21;
    private final int ID_FLIP = 22;
    private final int ID_ROTATE = 23;
    private final int ID_HTML = 24;
    private final int ID_BENCHMARK = 25;
    final int ID_HELP = 26;
    private final int ID_GRAPH_LESS = 27;
    private final int ID_GRAPH_MORE = 28;
    private final int ID_GRAPH_DEFAULT = 29;
    private final int ID_GRAPH = 30;
    private final int ID_MINI_EDIT = 31;
    private final int ID_MINI_ZOOM = 32;
    private final int ID_MINI_INFO = 33;
    private final int ID_COLOR_COLORS = 34;
    private final int ID_COLOR_RED = 35;
    private final int ID_COLOR_GREEN = 36;
    private final int ID_COLOR_BLUE = 37;
    private final int ID_COLOR_NORMAL = 38;
    private final int ID_COLOR_INVERT = 39;
    private final int ID_COLOR_OFF = 40;
    private final int ID_PERFORMING_BENCHMARK = 41;
    private final int ID_APPLET_HERE = 42;
    private final int ID_MAGNIFICATION = 43;
    private final int ID_ZOOM_INSTRUCTIONS = 44;
    private final int ID_PICT_INFO_TITLE = 45;
    private final int ID_BENCHMARK_TITLE = 46;
    private final int ID_NOT_REGISTERED = 47;
    private final int ID_REGISTERED_TO = 48;
    private final int ID_LOADING_PICTURE = 49;
    private final int ID_BYTES = 50;
    private final int ID_OFFLINE = 51;
    private final int ID_LAST_MODIFIED = 52;
    private final int ID_SECONDS_LEFT = 53;
    private final int ID_SAVE_SETTINGS = 54;
    private final int ID_SAVE_SUCCESS = 55;
    private final int ID_SAVE_FAILED = 56;
    private final int ID_LAST_PHRASE = 56;
    private final String onStr = "on";
    private final String offStr = "off";
    
    private boolean showCopyrightOnImage() {
        return !this.tooSmallToEdit && (this.viewOnly || this.basicOnly) && !this.registered;
    }
    
    public void benchmarkTest() {
        final int n = 20;
        this.recomputeImageNoRepaint();
        final Calendar instance = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            this.recomputeImageNoRepaint();
        }
        final Calendar instance2 = Calendar.getInstance();
        final long n2 = instance2.get(14) - instance.get(14) + 1000 * (instance2.get(13) - instance.get(13)) + 60000 * (instance2.get(12) - instance.get(12));
        final long n3 = n * (this.imageHeight * this.imageWidth);
        final long n4 = n3 / n2;
        final String s = new String("");
        final String string = "This benchmark filters the current image " + n + " times and reports the time taken.\n" + "Performance will vary according to CPU, free memory, drive space, OS, browser and Java Runtime Environment.\n\n" + "Browser Info: " + this.browserNavigatorInfo + "\n\n" + "Pixels: " + n3 + " (" + n + " * " + this.imageWidth + "*" + this.imageHeight + ")\n" + "Milli-Seconds: " + n2 + "\n" + "Pixels/Milli-Sec: " + n4 + "\n" + "Seconds per filter: " + n2 / (n * 1000);
        final int languageIndex = this.getLanguageIndex();
        String s2 = string + "\n\nLanguage dump, length: " + this.languageTextLen[languageIndex] + "\n" + "\n   [Offset] Current Language (English)\n";
        try {
            for (int j = 0; j < this.phraseCount; ++j) {
                s2 = s2 + (j + 1) + ". [" + this.phraseStart[j] + "] " + this.getPhrase(j);
                if (languageIndex != 0) {
                    this.loadLanguage(0);
                    s2 = s2 + " (" + this.getPhrase(j) + ")";
                    this.loadLanguage(languageIndex);
                }
                s2 += "\n";
            }
        }
        catch (Exception ex) {}
        this.displayDialog(this.getPhrase(46), s2);
    }
    
    public int getRotate() {
        return this.colorFilter.getRotate();
    }
    
    public void setRotate(int rotate) {
        rotate = 90 * (rotate / 90);
        if (rotate >= 360) {
            rotate = 0;
        }
        this.colorFilter.setRotate(rotate);
        this.paintRequiresErase = true;
        this.recomputeImage();
    }
    
    private String buildParamLine(final String s, final String s2) {
        if (s2 == null || s2.length() <= 0) {
            return "";
        }
        if (this.saveMode) {
            return s + "=" + s2 + "&";
        }
        return "<PARAM NAME=\"" + s + "\" VALUE=\"" + s2 + "\">\n";
    }
    
    private String buildParamLine(final String s, final double n) {
        final float truncToTwoDecimals = this.truncToTwoDecimals((float)n);
        if (truncToTwoDecimals != 1.0f) {
            return this.buildParamLine(s, "" + truncToTwoDecimals);
        }
        return "";
    }
    
    public String getAppletInfo() {
        return this.getAppDesc() + " " + "(jonespm2@shaw.ca)";
    }
    
    boolean showCopyrightInStatus() {
        return (this.viewOnly || this.basicOnly) && this.registered;
    }
    
    String getPhrase(final int n) {
        String substring = "?";
        if (n < this.phraseCount) {
            try {
                int length;
                if (n == this.phraseCount - 1) {
                    length = this.languageText[this.getLanguageIndex()].length();
                }
                else {
                    length = this.phraseStart[n + 1] - 2;
                }
                substring = this.languageText[this.getLanguageIndex()].substring(this.phraseStart[n], length);
            }
            catch (Exception ex) {}
        }
        return substring;
    }
    
    public void setClick(final String clickTitle, final String callDetails) {
        this.clickTitle = clickTitle;
        this.callDetails = callDetails;
    }
    
    private void infoDrawString(final Graphics graphics, final String s, final int n, final int n2) {
        this.haloDrawString(graphics, s, n, n2, Color.black, Color.white);
    }
    
    public String htmlCode() {
        String string = "";
        if (!this.saveMode) {
            string = "<APPLET MAYSCRIPT STYLE=\"ALIGN=LEFT LEFT: 0px; WIDTH: " + this.imageWidth + "px; TOP: 0px; HEIGHT: " + this.imageHeight + "px\" NAME=Touchup CODE=Touchup.class ARCHIVE=Touchup.jar WIDTH=" + this.imageWidth + " HEIGHT=" + this.imageHeight + ">\n";
        }
        String s = string + this.buildParamLine("image", this.imageValue);
        if (!this.saveMode) {
            final String string2 = s + "<IMG SRC=\"" + this.imageValue + "\" HEIGHT=\"" + this.imageHeight + "\" WIDTH=\"" + this.imageWidth + "\">\n" + "<BR>" + this.getPhrase(42) + "\n" + this.buildParamLine("regname", this.regName) + this.buildParamLine("regurl", this.regURL);
            String s2 = this.regCode;
            if (!this.isOnline() && this.special.equalsIgnoreCase("church7")) {
                s2 = this.nameURLToCode(this.regName, this.regURL);
            }
            s = string2 + this.buildParamLine("regcode", s2);
        }
        if (this.languageUseSecondary) {
            s += this.buildParamLine("secondarylanguage", "on");
        }
        if (this.exifOrientEnabled) {
            s += this.buildParamLine("exiforient", "on");
        }
        String s3 = s + this.buildParamLine("btntitle", this.userBtnTitle) + this.buildParamLine("btnscript", this.userBtnJScript) + this.buildParamLine("clicktitle", this.clickTitle) + this.buildParamLine("clickscript", this.callDetails) + this.buildParamLine("link", this.linkURL) + this.buildParamLine("target", this.linkTarget);
        if (!this.saveMode) {
            s3 = s3 + this.buildParamLine("pagecolor", this.pageColor) + this.buildParamLine("foreground", this.foregroundColor) + this.buildParamLine("background", this.backgroundColor) + this.buildParamLine("descfgcolor", this.descFgColor) + this.buildParamLine("descbgcolor", this.descBgColor) + this.buildParamLine("alphacolor", this.alphaColor);
        }
        String s4 = s3 + this.buildParamLine("title", this.imageTitle) + this.buildParamLine("justify", this.titleJustify);
        if (this.textHalo) {
            s4 += this.buildParamLine("halo", "on");
        }
        if (this.colorFilter.getBlackAndWhite()) {
            s4 += this.buildParamLine("color", "off");
        }
        if (this.colorFilter.getNegative()) {
            s4 += this.buildParamLine("negative", "on");
        }
        String s5 = s4 + this.buildParamLineZeroDefault("rotate", this.colorFilter.getRotate());
        if (this.colorFilter.getMirror()) {
            s5 += this.buildParamLine("mirror", "on");
        }
        if (this.colorFilter.getFlip()) {
            s5 += this.buildParamLine("flip", "on");
        }
        String s6 = s5 + this.buildParamLine("gamma", this.colorFilter.getViewerGamma() * this.colorFilter.getImageGamma()) + this.buildParamLineZeroDefault("posterize", this.colorFilter.getPosterSteps()) + this.buildParamLine("contrast", this.colorFilter.getViewerContrast() * this.colorFilter.getImageContrast()) + this.buildParamLineZeroDefault("brightness", this.colorFilter.getViewerBrightness() + this.colorFilter.getImageBrightness()) + this.buildParamLine("saturation", this.colorFilter.getViewerSaturation() * this.colorFilter.getImageSaturation()) + this.buildParamLineZeroDefault("alpha", this.colorFilter.getAlpha()) + this.buildParamLine("red", this.colorFilter.getViewerRed() * this.colorFilter.getImageRed()) + this.buildParamLine("green", this.colorFilter.getViewerGreen() * this.colorFilter.getImageGreen()) + this.buildParamLine("blue", this.colorFilter.getViewerBlue() * this.colorFilter.getImageBlue()) + this.buildParamLineOneDefault("redchannel", this.colorFilter.getRedChannel()) + this.buildParamLineOneDefault("greenchannel", this.colorFilter.getGreenChannel()) + this.buildParamLineOneDefault("bluechannel", this.colorFilter.getBlueChannel()) + this.buildParamLine("desctext", this.descText) + this.buildParamLineZeroDefault("horizontal", this.horizValue) + this.buildParamLineZeroDefault("vertical", this.vertValue);
        if (this.distScale != 1.0f) {
            s6 += this.buildParamLine("distScale", this.distScale);
        }
        if (!"pixels".equals(this.distUnits)) {
            s6 += this.buildParamLine("distUnits", this.distUnits);
        }
        if (this.zoomValue != 1.0f) {
            s6 += this.buildParamLine("zoom", this.zoomValue);
        }
        if (this.colorFilter.getNoiseFrequency() > 0) {
            s6 = s6 + this.buildParamLineZeroDefault("noiseLevel", this.colorFilter.getNoiseLevel()) + this.buildParamLineZeroDefault("noiseFrequency", this.colorFilter.getNoiseFrequency());
        }
        if (!this.saveMode) {
            s6 = s6 + "</APPLET>\n" + "<BR>\n";
        }
        else if (s6.length() > 1) {
            s6 = s6.substring(0, s6.length() - 1);
        }
        return s6;
    }
    
    private void addButtons() {
        this.btnInterface = this.getJARImage("btnpanel.gif");
        this.btnMiniPanelEdit = this.getJARImage("minipaneledit.gif");
        this.btnMiniPanelZoom = this.getJARImage("minipanelzoom.gif");
        this.btnMiniPanelInfo = this.getJARImage("minipanelinfo.gif");
        this.btnColorPanel = this.getJARImage("colorpanel.gif");
        this.btnSavePic = this.getJARImage("save.gif");
        (this.btnMedTracker = new MediaTracker(this)).addImage(this.btnInterface, 0);
        this.btnMedTracker.addImage(this.btnMiniPanelEdit, 0);
        this.btnMedTracker.addImage(this.btnMiniPanelZoom, 0);
        this.btnMedTracker.addImage(this.btnMiniPanelInfo, 0);
        this.btnMedTracker.addImage(this.btnColorPanel, 0);
        this.btnMedTracker.addImage(this.btnSavePic, 0);
        try {
            this.btnMedTracker.waitForAll();
        }
        catch (Exception ex) {
            this.touchupError("Can't load panel images", ex.getMessage());
        }
        this.graphFrameRect = new Rectangle(this.graphLeft, 0, 125, this.interfaceHeight - 18);
        this.infoRect = new Rectangle(0, this.graphFrameRect.y + this.graphFrameRect.height, this.graphFrameRect.x + this.graphFrameRect.width, 18);
        this.graphRect = new Rectangle(this.graphFrameRect);
        this.graphBarY = this.graphRect.y + this.graphRect.height - 23;
        this.graphBarLeft = this.graphRect.x + 14;
        this.graphBarRight = this.graphRect.x + this.graphRect.width - 14;
        this.graphLessRect = new Rectangle(this.graphBarLeft, this.graphBarY - 15 - 12, 16, 16);
        (this.graphMoreRect = new Rectangle(this.graphLessRect)).setLocation(this.graphBarRight - 15 - 1, this.graphMoreRect.y);
        this.graphDefaultRect = new Rectangle(this.graphLessRect);
        this.graphDefaultRect.x = this.graphLessRect.x + this.graphLessRect.width + 8;
        this.graphDefaultRect.width = this.graphMoreRect.x - this.graphDefaultRect.x - 8;
        this.btnCount = 0;
        this.btnMaxY = 0;
        final Rectangle rectangle = new Rectangle(1, 1, 22, 24);
        this.interfaceRect = new Rectangle(rectangle);
        this.addBtn(rectangle, 0);
        this.addBtn(rectangle, 1);
        this.addBtn(rectangle, 2);
        this.addBtn(rectangle, 3);
        this.addBtn(rectangle, 4);
        this.addBtn(rectangle, 5);
        this.addBtn(rectangle, 6);
        this.addBtn(rectangle, 7);
        this.addBtn(rectangle, 8);
        rectangle.setLocation(1, rectangle.y + 23 + 1);
        this.addBtn(rectangle, 9);
        this.addBtn(rectangle, 10);
        this.addBtn(rectangle, 11);
        this.addBtn(rectangle, 12);
        this.addBtn(rectangle, 13);
        this.addBtn(rectangle, 14);
        this.addBtn(rectangle, 15);
        this.addBtn(rectangle, 16);
        this.addBtn(rectangle, 17);
        rectangle.setLocation(1, rectangle.y + 23 + 1);
        this.addBtn(rectangle, 18);
        this.addBtn(rectangle, 19);
        this.addBtn(rectangle, 20);
        this.addBtn(rectangle, 21);
        this.addBtn(rectangle, 22);
        this.addBtn(rectangle, 23);
        this.addBtn(rectangle, 24);
        this.addBtn(rectangle, 25);
        this.addBtn(rectangle, 26);
        this.addBtn(new Rectangle(this.graphLessRect), 27);
        this.addBtn(new Rectangle(this.graphMoreRect), 28);
        this.addBtn(new Rectangle(this.graphDefaultRect), 29);
        this.addBtn(new Rectangle(this.graphRect), 30);
        this.btnMiniStart = this.btnCount;
        this.setLayout(new BorderLayout());
    }
    
    private void paintControlsLow(final Graphics graphics) {
        if (this.touchupMode == 1) {
            if (this.btnMedTracker.checkID(0)) {
                if (this.btnInterface != null) {
                    graphics.drawImage(this.btnInterface, 0, this.descAdj, this);
                }
                if (this.saveMode && this.btnSavePic != null) {
                    graphics.drawImage(this.btnSavePic, 143, this.descAdj + 54, this);
                }
                boolean b = false;
                if (this.userBtnJScript != null && this.userBtnJScript.length() != 0) {
                    b = true;
                }
                if (!b) {
                    this.eraseButton(graphics, this.findButtonIdx(17));
                }
                if (this.imageProtected) {
                    this.eraseButton(graphics, this.findButtonIdx(6));
                    this.eraseButton(graphics, this.findButtonIdx(7));
                    this.eraseButton(graphics, this.findButtonIdx(8));
                }
                this.drawGraph(graphics);
                this.drawInfo(graphics);
            }
        }
        else if (this.touchupMode == 0 && this.TouchupShown) {
            this.drawTouchupButton(graphics);
        }
    }
    
    private boolean isOnline() {
        return this.isApplet && this.getCodeBase().toString().indexOf("http://") != -1;
    }
    
    public boolean getNegative() {
        return this.colorFilter.getNegative();
    }
    
    public void setNegative(final boolean negative) {
        this.colorFilter.setNegative(negative);
        this.recomputeImage();
    }
    
    public void zoomAreaIn() {
        ++this.zoomAreaFactor;
        this.recreateZoomArea();
        this.zoomStatusBar();
    }
    
    public synchronized void destroy() {
        deepClean(this);
    }
    
    public void syncApplets() {
        final double viewerGamma = this.colorFilter.getViewerGamma();
        final double viewerContrast = this.colorFilter.getViewerContrast();
        final int viewerBrightness = this.colorFilter.getViewerBrightness();
        final double viewerSaturation = this.colorFilter.getViewerSaturation();
        final double viewerRed = this.colorFilter.getViewerRed();
        final double viewerGreen = this.colorFilter.getViewerGreen();
        final double viewerBlue = this.colorFilter.getViewerBlue();
        final int redChannel = this.colorFilter.getRedChannel();
        final int greenChannel = this.colorFilter.getGreenChannel();
        final int blueChannel = this.colorFilter.getBlueChannel();
        final int alphaColor = this.colorFilter.getAlphaColor();
        final double alpha = this.colorFilter.getAlpha();
        final boolean blackAndWhite = this.colorFilter.getBlackAndWhite();
        final boolean negative = this.colorFilter.getNegative();
        int n = 0;
        try {
            final Enumeration<Applet> applets = this.getAppletContext().getApplets();
            while (applets.hasMoreElements()) {
                ++n;
                final Applet applet = applets.nextElement();
                if (applet instanceof Touchup) {
                    final Touchup touchup = (Touchup)applet;
                    if (touchup == this) {
                        continue;
                    }
                    touchup.syncSetApplet(viewerContrast, viewerBrightness, viewerGamma, viewerSaturation, viewerRed, viewerGreen, viewerBlue, redChannel, greenChannel, blueChannel, alphaColor, alpha, blackAndWhite, negative);
                }
            }
        }
        catch (Exception ex) {
            this.touchupError("syncApplets, getAppletContext failure.", ex.getMessage());
        }
    }
    
    public void flip() {
        this.setFlip(!this.colorFilter.getFlip());
    }
    
    private float roundHundreds(final double n) {
        return (int)(100.0 * n) / 100.0f;
    }
    
    public void loadImage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final float n, final float n2, final float n3, final float n4, final float n5, final int imageBrightness, final float n6) {
        this.colorFilter.setImageRGBGamma(n, n2, n3);
        this.colorFilter.setImageGamma(n4);
        this.colorFilter.setImageContrast(n5);
        this.colorFilter.setImageBrightness(imageBrightness);
        this.colorFilter.setImageSaturation(n6);
        this.loadImageLow(s, s2, s3, s4, s5, s6);
    }
    
    public void setUserBtn(final String userBtnTitle, final String userBtnJScript) {
        this.userBtnTitle = userBtnTitle;
        this.userBtnJScript = userBtnJScript;
        if (this.touchupMode == 1) {
            this.hideEditButtons();
            this.showEditButtons();
        }
        this.repaint();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void loadImage(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this.loadImage(s, s2, s3, s4, s5, s6, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0, 1.0f);
    }
    
    private int intToByte(int n) {
        if (n < 0) {
            n = 0;
        }
        else if (n > 255) {
            n = 255;
        }
        return n;
    }
    
    public void recomputeImage() {
        this.recomputeImageNoRepaint();
        this.repaint();
    }
    
    private void drawUnlicenced(final Graphics graphics) {
        int n = this.minWidth / 6;
        for (int i = Math.max(this.minHeight / 5, this.descAdj + 15); i < this.minHeight - 20; i += this.minHeight / 5) {
            for (int j = n + this.minWidth / 12; j < this.minWidth - 70; j += this.minWidth / 3) {
                if ((i >= this.descAdj + this.interfaceHeight + 15 || j >= 228) && (this.histogramDisplay == 0 || j > 260 || i < this.minHeight - 140)) {
                    graphics.drawString("Shareware", j, i);
                }
            }
            n = this.minWidth / 6 - n;
        }
    }
    
    public boolean getMirror() {
        return this.colorFilter.getMirror();
    }
    
    public void setMirror(final boolean mirror) {
        this.colorFilter.setMirror(mirror);
        this.recomputeImage();
    }
    
    private float roundTenths(final float n) {
        int n2;
        if (n < 0.0f) {
            n2 = (int)(10.0f * n - 0.5f);
        }
        else {
            n2 = (int)(10.0f * n + 0.5f);
        }
        return n2 / 10.0f;
    }
    
    public void init() {
    }
    
    public float getGreen() {
        return (float)this.colorFilter.getViewerGreen();
    }
    
    public void setGreen(final float graphValue) {
        this.colorFilter.setViewerGreen(graphValue);
        if (this.currButtonID == 10) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    public void recomputeImageNoRepaint() {
        if (this.imageLoaded && this.imageWidth != -1) {
            if (!this.updatingEnabled) {
                this.updatePending = true;
                return;
            }
            this.updatePending = false;
            if (this.resultWidth != this.imageWidth || this.resultHeight != this.imageHeight) {
                this.resultPixels = new int[this.imageWidth * this.imageHeight];
                this.resultWidth = this.imageWidth;
                this.resultHeight = this.imageHeight;
            }
            this.colorFilter.filterImage(this.originalPixels, this.resultPixels, this.imageHeight, this.imageWidth);
            this.filteredWidth = this.colorFilter.getFilterWidth();
            this.filteredHeight = this.colorFilter.getFilterHeight();
            if (this.spriteTest && this.spriteLoaded) {
                this.colorFilter.applySprite(this.resultPixels, this.spritePixels, 16777215, this.filteredHeight, this.filteredWidth, this.spriteH, this.spriteW, -this.horizValue, -this.vertValue);
            }
            this.myPictureMod = this.createImage(new MemoryImageSource(this.filteredWidth, this.filteredHeight, this.resultPixels, 0, this.filteredWidth));
            this.btnCount = this.btnMiniStart;
            final Rectangle rectangle = new Rectangle(6, 6, 23, 23);
            if (!this.viewOnly) {
                if (!this.basicOnly) {
                    this.addBtn(rectangle, 31);
                }
                this.addBtn(rectangle, 32);
                if (this.infoPresent()) {
                    this.addBtn(rectangle, 33);
                }
            }
        }
        System.gc();
    }
    
    public void run() {
        this.repaint();
    }
    
    public float getImageGamma() {
        return (float)this.colorFilter.getImageGamma();
    }
    
    int inButton(final int n, int n2) {
        int buttonIdx = -1;
        int n3 = -1;
        n2 -= this.descAdj;
        if (this.touchupMode == 1 && this.graphRect.contains(n, n2)) {
            buttonIdx = this.findButtonIdx(30);
        }
        if (this.interfaceRect.contains(n, n2)) {
            int btnMiniStart = 0;
            int n4 = this.btnMiniStart;
            if (this.touchupMode != 1) {
                btnMiniStart = this.btnMiniStart;
                n4 = this.btnCount;
            }
            for (int i = btnMiniStart; i < n4; ++i) {
                if (this.btnRect[i].contains(n, n2)) {
                    buttonIdx = i;
                    n3 = this.btnID[buttonIdx];
                    if (this.imageProtected && (n3 == 6 || n3 == 7 || n3 == 8)) {
                        buttonIdx = -1;
                    }
                    if (n3 == 17 && this.userBtnJScript.length() == 0) {
                        buttonIdx = -1;
                    }
                    i = n4;
                }
            }
            if (this.currButtonID == 13 && (n3 == 27 || n3 == 28 || n3 == 29 || n3 == 30)) {
                buttonIdx = -1;
            }
        }
        if (n3 == 33 && !this.infoPresent()) {
            buttonIdx = -1;
        }
        return buttonIdx;
    }
    
    private void depressedButton(final Graphics graphics, final int n) {
        if (n != -1) {
            final Rectangle rectangle = new Rectangle(this.btnRect[n]);
            rectangle.setBounds(new Rectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1));
            this.drawDepressed(graphics, rectangle);
        }
    }
    
    public void setImageGamma(final double imageGamma) {
        this.colorFilter.setImageGamma(imageGamma);
        this.recomputeImage();
    }
    
    private String buildParamLineOneDefault(final String s, final int n) {
        if (n != 1) {
            return this.buildParamLine(s, "" + n);
        }
        return "";
    }
    
    private String buildParamLineZeroDefault(final String s, final int n) {
        if (n != 0) {
            return this.buildParamLine(s, "" + n);
        }
        return "";
    }
    
    private String buildParamLineZeroDefault(final String s, final double n) {
        final float truncToTwoDecimals = this.truncToTwoDecimals((float)n);
        if (truncToTwoDecimals != 0.0f) {
            return this.buildParamLine(s, "" + truncToTwoDecimals);
        }
        return "";
    }
    
    private void drawRadio(final Graphics graphics, int n, int n2) {
        final int n3 = 5;
        graphics.setColor(Color.black);
        ++n;
        ++n2;
        for (int i = 0; i < n3; ++i) {
            graphics.drawLine(n, n2 + i, n + n3 - 1, n2 + i);
        }
        --n;
        --n2;
        graphics.setColor(Color.gray);
        graphics.drawLine(n + 2, n2, n + n3 - 1, n2);
        graphics.drawLine(n + 2, n2 + n3 + 1, n + n3 - 1, n2 + n3 + 1);
        graphics.drawLine(n, n2 + 2, n, n2 + n3 - 1);
        graphics.drawLine(n + n3 + 1, n2 + 2, n + n3 + 1, n2 + n3 - 1);
    }
    
    public String convertExtendedChars(final String s) {
        String s2 = new String("");
        int n = 0;
        boolean b = false;
        while (!b) {
            final int index = s.indexOf("\\n", n);
            if (index != -1) {
                s2 = s2 + s.substring(n, index) + '\n';
            }
            else {
                b = true;
                s2 += s.substring(n);
            }
            n = index + 2;
        }
        return s2;
    }
    
    public void setHyperlink(final String linkURL, final String linkTarget) {
        if (linkURL.length() == 0) {
            this.linkURL = null;
        }
        else {
            this.linkURL = linkURL;
        }
        this.linkTarget = linkTarget;
    }
    
    public float getRed() {
        return (float)this.colorFilter.getViewerRed();
    }
    
    public void setRed(final float graphValue) {
        this.colorFilter.setViewerRed(graphValue);
        if (this.currButtonID == 9) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    private String getTouchupParam(final String s) {
        if (this.isApplet) {
            return this.getParameter(s);
        }
        return "";
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        System.out.println("windowClosing");
        final Window window = windowEvent.getWindow();
        if (window.equals(this)) {
            this.appletFrame.dispose();
            return;
        }
        if (window.equals(this.appletFrame)) {
            this.appletFrame.dispose();
        }
    }
    
    public void setTParam(final int n, final float n2) {
        switch (n) {
            case 0: {
                this.setNoiseFrequency((int)n2);
            }
            case 1: {
                this.setNoiseLevel((int)n2);
            }
            default: {}
        }
    }
    
    public float getTParam(final int n) {
        float n2 = 0.0f;
        switch (n) {
            case 0: {
                n2 = this.getNoiseFrequency();
                break;
            }
            case 1: {
                n2 = this.getNoiseLevel();
                break;
            }
        }
        return n2;
    }
    
    private String urlToBase(String substring) {
        final int lastIndex = substring.lastIndexOf("/");
        if (lastIndex != -1) {
            substring = substring.substring(0, lastIndex + 1);
        }
        return substring;
    }
    
    public void setDesc(final String s, final String descFgColor, final String descBgColor) {
        if (s == null) {
            this.descText = new String("");
        }
        else if (s.length() == 0) {
            this.descText = "";
        }
        else {
            this.descText = new String(s);
        }
        if (descFgColor.length() == 0) {
            this.descFgColor = this.foregroundColor;
        }
        else {
            this.descFgColor = descFgColor;
        }
        if (descBgColor.length() == 0) {
            this.descBgColor = this.backgroundColor;
        }
        else {
            this.descBgColor = descBgColor;
        }
        final boolean descShown = this.descText.length() != 0;
        if (this.descArea == null) {
            this.descArea = new VWrappingLabel(this.descText);
        }
        this.descArea.setText(this.descText);
        if (descShown) {
            this.add(this.descArea, "North");
        }
        else {
            this.remove(this.descArea);
        }
        this.descArea.setForeground(this.stringToColor(this.descFgColor));
        this.descArea.setBackground(this.stringToColor(this.descBgColor));
        this.doLayout();
        this.descShown = descShown;
        this.descAdj = 0;
        final Dimension size = this.descArea.getSize();
        if (this.descShown) {
            size.height = this.descArea.computeHeight();
            this.descArea.setSize(size.width, size.height);
            this.descAdj = size.height + 5;
        }
    }
    
    public void blackAndWhite() {
        this.setBlackAndWhite(!this.colorFilter.getBlackAndWhite());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.isApplet) {
            System.out.println("actionPerformed");
            if (actionEvent.getID() == 201) {
                System.out.println("WINDOW_CLOSING");
                this.appletFrame.dispose();
            }
        }
    }
    
    private void paintControls(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintControlsLow(this.offscreen.getGraphics());
            graphics.drawImage(this.offscreen, 0, 0, this);
            return;
        }
        this.paintControlsLow(graphics);
    }
    
    private void drawHistogram(final Graphics graphics) {
        long n = 0L;
        int i = 0;
        int n2 = 0;
        while (i < 3) {
            int j = 0;
            do {
                if (j > 2 && j < 253 && this.colorFilter.histogram[n2] > n) {
                    n = this.colorFilter.histogram[n2];
                }
                ++j;
                ++n2;
            } while (j < 256);
            ++i;
        }
        if (n == 0L) {
            n = 1L;
        }
        final int n3 = this.minHeight - 20;
        int n4 = 0;
        do {
            final int n5 = (int)(100L * Math.min(n, this.colorFilter.histogram[n4]) / n);
            final int n6 = (int)(100L * Math.min(n, this.colorFilter.histogram[n4 + 256]) / n);
            final int n7 = (int)(100L * Math.min(n, this.colorFilter.histogram[n4 + 512]) / n);
            if (this.colorFilter.getBlackAndWhite()) {
                final int n8 = (n5 + n6 + n7) / 3;
                graphics.setColor(Color.gray);
                graphics.drawLine(n4 + 6, n3 - n8, n4 + 6, n3);
            }
            else {
                int n9 = 99;
                do {
                    final int n10 = n4 + 6;
                    if (n9 == n5) {
                        if (!this.colorFilter.getBlackAndWhite()) {
                            graphics.setColor(Color.red);
                        }
                        graphics.drawLine(n10, n3 - n9, n10, n3);
                    }
                    else if (n9 == n6) {
                        if (!this.colorFilter.getBlackAndWhite()) {
                            graphics.setColor(Color.green);
                        }
                        graphics.drawLine(n10, n3 - n9, n10, n3);
                    }
                    else {
                        if (n9 != n7) {
                            continue;
                        }
                        if (!this.colorFilter.getBlackAndWhite()) {
                            graphics.setColor(Color.blue);
                        }
                        graphics.drawLine(n10, n3 - n9, n10, n3);
                    }
                } while (--n9 > 0);
            }
        } while (++n4 < 256);
        int n11 = 0;
        do {
            graphics.setColor(new Color(n11, n11, n11));
            graphics.drawLine(n11 + 6, n3 + 12, n11 + 6, n3 + 3);
        } while (++n11 < 256);
        graphics.setColor(Color.green);
    }
    
    public void negative() {
        this.setNegative(!this.colorFilter.getNegative());
    }
    
    public void resetImage() {
        if (this.languageUseSecondary) {
            this.loadLanguage(1);
        }
        else {
            this.loadLanguage(0);
        }
        this.infoString = this.getPhrase(1);
        this.colorFilter.setRGBChannels(1, 1, 1);
        this.colorFilter.setViewerRed(1.0);
        this.colorFilter.setViewerBlue(1.0);
        this.colorFilter.setViewerGreen(1.0);
        this.colorFilter.setBright(1.0);
        this.colorFilter.setViewerGamma(1.0);
        this.colorFilter.setViewerContrast(1.0);
        this.colorFilter.setViewerBrightness(0);
        this.colorFilter.setViewerSaturation(1.0);
        this.colorFilter.setAlpha(0.0);
        this.colorFilter.setThreshold(-1);
        this.colorFilter.setBlackAndWhite(false);
        this.colorFilter.setNegative(false);
        final int rotate = this.colorFilter.getRotate();
        if (rotate == 90 || rotate == 270 || this.zoomValue != 1.0f || this.horizValue != 0 || this.vertValue != 0) {
            this.paintRequiresErase = true;
        }
        this.colorFilter.setRotate(0);
        this.colorFilter.setMirror(false);
        this.colorFilter.setFlip(false);
        this.colorFilter.setMatrixView(0);
        this.colorFilter.setFakeSky(false);
        this.textHalo = false;
        this.colorFilter.setTitlebar(false);
        this.zoomValue = 1.0f;
        this.tuningAmount = 1.1f;
        if (!this.basicOnly && !this.viewOnly && !this.imageProtected) {
            this.colorFilter.setPosterSteps(0);
            this.colorFilter.setNoiseFrequency(0);
            this.colorFilter.setNoiseLevel(-30);
        }
        this.setGraphValue(this.graphDefaultValue);
        this.setCursor(new Cursor(0));
        this.applyExifOrientation();
        this.recomputeImage();
    }
    
    private void addBtn(final Rectangle rectangle, final int n) {
        this.btnRect[this.btnCount] = new Rectangle(rectangle);
        final Rectangle rectangle2 = this.btnRect[this.btnCount];
        ++rectangle2.width;
        final Rectangle rectangle3 = this.btnRect[this.btnCount];
        ++rectangle3.height;
        this.btnID[this.btnCount] = n;
        ++this.btnCount;
        this.interfaceRect.add(rectangle);
        rectangle.setLocation(rectangle.x + 23, rectangle.y);
    }
    
    private float truncToTwoDecimals(final float n) {
        return (int)((n + 0.005) * 100.0) / 100.0f;
    }
    
    public void repaintInfoNow() {
        this.drawInfo(this.getGraphics());
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.imageWidth < 2 || this.imageHeight < 2) {
            return;
        }
        if (!this.viewOnly && this.touchupMode != 1) {
            if (mouseEvent.getID() == 504) {
                this.mouseEnteredTouchup();
                if (!this.TouchupShown) {
                    this.TouchupShown = true;
                    this.redrawButtonArea();
                }
            }
            else if (mouseEvent.getID() == 505) {
                this.mouseExitedTouchup();
                if (this.TouchupShown) {
                    this.setAppletStatus("");
                    this.TouchupShown = false;
                    this.redrawButtonArea();
                }
            }
        }
        if (mouseEvent.getID() == 501) {
            if (this.viewOnly) {
                this.pressedBtnIndex = -1;
            }
            else {
                this.pressedBtnIndex = this.inButton(x, y);
                this.pressedXCoord = x;
                this.pressedYCoord = y;
                if (this.pressedBtnIndex != -1 && this.pressedBtnIndex != 30) {
                    this.depressedButton(this.getGraphics(), this.pressedBtnIndex);
                }
                this.pressedDragStarted = (this.pressedBtnIndex == -1 && (this.currButtonID == 18 || this.currButtonID == 19 || this.currButtonID == 20));
                if (this.pressedDragStarted) {
                    this.setCursor(new Cursor(12));
                }
            }
        }
        if (mouseEvent.getID() == 502) {
            boolean b = this.pressedBtnIndex != -1;
            final int inButton = this.inButton(x, y);
            if (this.pressedDragStarted) {
                this.setCursor(new Cursor(0));
            }
            if (this.pressedDragStarted && this.pressedXCoord >= 0 && this.pressedYCoord >= 0 && (Math.abs(x - this.pressedXCoord) > 5 || Math.abs(y - this.pressedYCoord) > 5)) {
                b = true;
                this.horizValue = this.horizValue + x - this.pressedXCoord;
                this.vertValue = this.vertValue + y - this.pressedYCoord;
                if (this.currButtonID == 19) {
                    this.setGraphValue(this.horizValue);
                }
                if (this.currButtonID == 20) {
                    this.setGraphValue(this.vertValue);
                }
                this.pressedXCoord = -1;
                this.pressedYCoord = -1;
            }
            if (this.zoomAreaOn) {
                if ((mouseEvent.getModifiers() & 0x4) != 0x0 | (mouseEvent.getModifiers() & 0x1) != 0x0) {
                    this.zoomAreaOut();
                    return;
                }
                this.zoomAreaIn();
            }
            else {
                if (this.touchupMode == 1) {
                    if (this.pressedBtnIndex != inButton) {
                        this.pressedBtnIndex = -1;
                        this.paintControls(this.getGraphics());
                    }
                    else if (this.performButton(x, y)) {
                        return;
                    }
                }
                if (this.inCopyrightArea(x, y)) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.getAppURL()), "_blank");
                        return;
                    }
                    catch (Exception ex) {
                        this.touchupError("processMouseEvent, showDocument failure.", ex.getMessage());
                        return;
                    }
                }
                if (this.touchupMode != 1) {
                    if (inButton != -1 && this.pressedBtnIndex == inButton && !this.viewOnly) {
                        this.depressedRectangle(this.btnRect[inButton]);
                        this.infoString = this.getButtonTip(this.btnID[inButton]);
                        this.repaintInfoNow();
                        switch (this.btnID[inButton]) {
                            case 31: {
                                this.showEditButtons();
                                break;
                            }
                            case 32: {
                                if (this.imageWidth > 1 && this.imageHeight > 1) {
                                    this.touchupMode = 2;
                                    this.redrawButtonArea();
                                    this.createZoomArea(x, y);
                                    break;
                                }
                                break;
                            }
                            case 33: {
                                this.repaintNow();
                                this.showPictureInfo();
                                break;
                            }
                        }
                    }
                    else if (x < this.minWidth && y < this.minHeight && (mouseEvent.getModifiers() & 0x4) == 0x0 && this.pressedBtnIndex == -1) {
                        if (!this.executeUserScript(this.callDetails)) {
                            this.callDetails = null;
                        }
                        if (this.linkURL != null) {
                            try {
                                final URL fileToURL = this.fileToURL(this.linkURL);
                                if (mouseEvent.isShiftDown()) {
                                    this.getAppletContext().showDocument(fileToURL, "_blank");
                                }
                                else if (this.linkTarget == null) {
                                    this.getAppletContext().showDocument(fileToURL);
                                }
                                else {
                                    this.getAppletContext().showDocument(fileToURL, this.linkTarget);
                                }
                            }
                            catch (Exception ex2) {
                                this.touchupError("Link URL \"" + this.linkURL + "\" invalid.", ex2.getMessage());
                            }
                        }
                    }
                }
                if (b) {
                    this.pressedBtnIndex = -1;
                    this.repaintNow();
                }
                this.pressedDragStarted = false;
            }
        }
    }
    
    private String nameURLToCode(final String s, final String s2) {
        return "" + this.stringToHashNumber(s + s2);
    }
    
    private void doRealInit() {
        try {
            this.directoryList();
        }
        catch (Exception ex) {}
        this.touchupMode = 0;
        this.initLanguages();
        this.loadLanguage(this.getLanguageIndex());
        this.setLayout(new FlowLayout(0));
        this.addKeyListener(new KeyListener() {
            {
                Touchup.this.getClass();
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
                final char keyChar = keyEvent.getKeyChar();
                if (keyChar == '0') {
                    Touchup.this.zoomAreaReset();
                    return;
                }
                if (keyChar == '1') {
                    Touchup.this.zoomAreaIn();
                    return;
                }
                if (keyChar == '2') {
                    Touchup.this.zoomAreaOut();
                    return;
                }
                if (keyChar == '3' | keyChar == '=' | keyChar == '+') {
                    Touchup.this.zoomAreaExpand();
                    return;
                }
                if (keyChar == '4' | keyChar == '-') {
                    Touchup.this.zoomAreaShrink();
                    return;
                }
                if (keyChar == '\n') {
                    Touchup.this.modeOff();
                    return;
                }
                if (keyChar == '\u001b') {
                    Touchup.this.modeOff();
                    return;
                }
                if (keyChar == '\b') {
                    Touchup.this.modeOff();
                    return;
                }
                if (keyChar == 'd') {
                    Touchup.this.zoomSetDistOrigin();
                    return;
                }
                if (keyChar == 's') {
                    Touchup.this.zoomSetDistOff();
                }
            }
            
            public void keyPressed(final KeyEvent keyEvent) {
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
            }
        });
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
            }
            
            {
                Touchup.this.getClass();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            {
                Touchup.this.getClass();
            }
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                String appletStatus = Touchup.this.messageBarString;
                int n = 0;
                if (Touchup.this.touchupMode == Touchup.this.modeControls) {
                    appletStatus = Touchup.this.getAppDesc();
                }
                else if ((Touchup.this.linkURL != null || Touchup.this.callDetails != null) && x < Touchup.this.minWidth && y < Touchup.this.minHeight) {
                    n = 12;
                    if (Touchup.this.callDetails != null) {
                        appletStatus = Touchup.this.clickTitle;
                    }
                    else {
                        appletStatus = Touchup.this.linkURL;
                    }
                }
                if (Touchup.this.inCopyrightArea(x, y)) {
                    n = 12;
                    appletStatus = Touchup.this.getPhrase(Touchup.this.ID_HELP) + " " + Touchup.this.getAppURL();
                }
                if (Touchup.this.showCopyrightInStatus()) {
                    if (appletStatus.length() == 0) {
                        appletStatus = Touchup.this.getDisplayedByTitle();
                    }
                    else {
                        appletStatus = appletStatus + "  (" + Touchup.this.getDisplayedByTitle() + ")";
                    }
                }
                Touchup.this.setCursor(new Cursor(n));
                if (Touchup.this.touchupMode == Touchup.this.modeControls) {
                    String s = Touchup.this.getAppDesc();
                    final int inButton = Touchup.this.inButton(x, y);
                    if (inButton != -1) {
                        s = Touchup.this.getButtonTip(Touchup.this.btnID[inButton]);
                    }
                    if (s.compareTo(Touchup.this.infoString) != 0) {
                        Touchup.this.infoString = new String(s);
                        Touchup.this.repaintInfoNow();
                    }
                }
                else {
                    if (Touchup.this.touchupMode == Touchup.this.modeZoom) {
                        Touchup.this.createZoomArea(x, y);
                        Touchup.this.zoomStatusBar();
                        return;
                    }
                    if (Touchup.this.touchupMode != Touchup.this.modeInfo) {
                        if (!Touchup.this.TouchupShown && !Touchup.this.viewOnly && Touchup.this.imageWidth > 1 && Touchup.this.imageHeight > 1) {
                            Touchup.this.TouchupShown = true;
                            Touchup.this.redrawButtonArea();
                        }
                        if (y < 30 + Touchup.this.descAdj && x < 75) {
                            for (int i = Touchup.this.btnMiniStart; i < Touchup.this.btnCount; ++i) {
                                if (Touchup.this.btnRect[i].contains(x, y - Touchup.this.descAdj)) {
                                    appletStatus = Touchup.this.getPhrase(Touchup.this.btnID[i]);
                                }
                            }
                        }
                    }
                }
                Touchup.this.setAppletStatus(appletStatus);
            }
        });
        this.addButtons();
        this.medTracker = new MediaTracker(this);
        if (this.isApplet) {
            this.loadImageLow(this.imageValue, this.getStrParam("lowres"), this.getStrParam("desctext"), this.getStrParam("info"), this.imageTitle, this.titleJustify);
        }
        else {
            this.loadImageLow(this.imageValue, "", "", "", "", "");
        }
        this.repaint();
    }
    
    public void setAlphaColor(final int n, final int n2, final int n3) {
        this.colorFilter.setAlphaColor(-16777216 + (this.intToByte(n) << 16) + (this.intToByte(n2) << 8) + this.intToByte(n3));
    }
    
    public int getAlphaColor() {
        return this.colorFilter.getAlphaColor();
    }
    
    public void paint(final Graphics graphics) {
        if (this.justStarted) {
            this.justStarted = false;
            graphics.drawString("Initializing applet...", 6, this.statusBaseLine);
            this.colorFilter = new TouchupImageFilter();
            if (this.isApplet) {
                this.readAppletParams();
            }
            this.doRealInit();
            this.repaint();
            return;
        }
        this.appletWidth = this.getBounds().width;
        this.appletHeight = this.getBounds().height;
        this.minWidth = Math.min(this.imageWidth, this.appletWidth);
        this.minHeight = Math.min(this.descAdj + this.imageHeight, this.appletHeight);
        if (this.offscreen == null) {
            this.offscreen = this.createImage(this.appletWidth, this.appletHeight);
        }
        if (this.offscreen != null) {
            this.paintDetails(this.offscreen.getGraphics());
            graphics.drawImage(this.offscreen, 0, 0, this);
            return;
        }
        this.paintDetails(graphics);
    }
    
    public int getNoiseFrequency() {
        return this.colorFilter.getNoiseFrequency();
    }
    
    public void setNoiseFrequency(final int noiseFrequency) {
        this.colorFilter.setNoiseFrequency(noiseFrequency);
        if (this.currButtonID == 7) {
            this.setGraphValue(noiseFrequency);
        }
        this.recomputeImage();
    }
    
    private boolean executeUserScript(final String s) {
        boolean b = true;
        if (s != null && s.length() != 0) {
            try {
                JSObject.getWindow((Applet)this).eval(s);
            }
            catch (Exception ex) {
                b = false;
                this.touchupError("JavaScript error.", ex.getMessage());
            }
        }
        return b;
    }
    
    public Touchup() {
        this.isApplet = true;
        this.appletFrame = null;
        this.tooSmallToEdit = false;
        this.languageText = new String[20];
        this.languageTextLen = new int[20];
        this.languageFile = null;
        this.phraseCount = 0;
        this.phraseStart = new int[100];
        this.mainWindow = null;
        this.zoomImage = null;
        this.zoomAreaOn = false;
        this.zoomDistOn = false;
        this.zoomMouseXStartDist = 100;
        this.zoomMouseYStartDist = 100;
        this.zoomSize = 160;
        this.zoomSizeHalf = this.zoomSize / 2;
        this.zoomSizeQuarter = this.zoomSize / 4;
        this.zoomBorder = 3;
        this.zoomAreaFactor = 2;
        this.pixelXZoomStart = 0;
        this.pixelYZoomStart = 0;
        this.distScale = 1.0f;
        this.distUnits = new String("pixels");
        this.resultPixels = null;
        this.resultWidth = -1;
        this.resultHeight = -1;
        this.mainImageBytesTotal = -1;
        this.mainImageBytesRead = -1;
        this.mainImageStartLoad = -1L;
        this.imageLastModified = "";
        this.imageLoadErrString = "";
        this.mainImageLoadCount = 0;
        this.descArea = null;
        this.descText = null;
        this.descShown = false;
        this.descFgColor = "";
        this.descBgColor = "";
        this.descAdj = 0;
        this.browserIsNetscape = false;
        this.saveModeURL = "";
        this.inDumpCode = false;
        this.regName = "";
        this.regURL = "";
        this.regCode = "";
        this.regDescription = "";
        this.registered = false;
        this.exifOrientEnabled = false;
        this.touchupDebug = false;
        this.exifOrientation = 1;
        this.languageIndex = -1;
        this.languageCount = 20;
        this.languageUseSecondary = false;
        this.touchupMode = 0;
        this.loaded = false;
        this.hidden = false;
        this.horizValue = 0;
        this.vertValue = 0;
        this.zoomValue = 1.0f;
        this.tuningAmount = 1.1f;
        this.paintCount = 0;
        this.enterTouchupScript = "";
        this.exitedTouchupScript = "";
        this.userBtnJScript = "";
        this.imageValue = "";
        this.imageProtected = false;
        this.viewOnly = false;
        this.basicOnly = false;
        this.docBaseURL = null;
        this.TouchupShown = false;
        this.textHalo = false;
        this.colorButtons = false;
        this.advancedButtons = false;
        this.getAppletsSupported = false;
        this.showSyncButton = false;
        this.lowResLoaded = false;
        this.myLowResMod = null;
        this.spriteTest = false;
        this.spriteImage = null;
        this.spriteW = 0;
        this.spriteH = 0;
        this.spriteLoaded = false;
        this.spritePixels = null;
        this.imageLoaded = false;
        this.loadingImage = false;
        this.imageHeight = 0;
        this.imageWidth = 0;
        this.filteredHeight = 0;
        this.filteredWidth = 0;
        this.appletWidth = 0;
        this.appletHeight = 0;
        this.minWidth = 0;
        this.minHeight = 0;
        this.browserNavigatorInfo = "N/A";
        this.histogramDisplay = 0;
        this.previousImage = "";
        this.firstImageLoad = true;
        this.paintRequiresErase = false;
        this.offscreen = null;
        this.updatingEnabled = true;
        this.updatePending = false;
        this.messageBarString = "";
        this.justStarted = true;
        this.infoString = "";
        this.exifString = "";
        this.pressedBtnIndex = -1;
        this.pressedXCoord = -1;
        this.pressedYCoord = -1;
        this.pressedDragStarted = false;
        this.btnRect = new Rectangle[40];
        this.btnID = new int[40];
        this.currButtonIndex = -1;
        this.currButtonID = -1;
        this.btnCount = 0;
        this.btnMiniStart = 0;
        this.btnMaxY = 0;
        this.btnInterface = null;
        this.pictureInfoArea = null;
        this.pictureInfoText = "";
        this.btnMiniPanelEdit = null;
        this.btnMiniPanelZoom = null;
        this.btnMiniPanelInfo = null;
        this.btnColorPanel = null;
        this.btnSavePic = null;
        this.saveMode = true;
        this.interfaceHeight = 90;
        this.statusBaseLine = 20;
        this.graphLeft = 208;
        this.graphDefaultValue = 1.0f;
        this.graphLeftValue = 0.0f;
        this.graphMidValue = 1.0f;
        this.graphRightValue = 10.0f;
        this.graphTitle = "";
        this.graphValue = 1.0f;
        this.graphAddAmount = 0.1f;
        this.graphValueInteger = false;
        this.graphIndicatorX = -1;
        this.graphOn = false;
    }
    
    private String getTitleOfPhrase(final int n) {
        String s = this.getPhrase(n);
        final int index = s.indexOf(":");
        if (index != -1) {
            s = s.substring(0, index);
        }
        return s;
    }
    
    public int getVertical() {
        return this.vertValue;
    }
    
    public void setVertical(final int vertValue) {
        this.paintRequiresErase = true;
        this.vertValue = vertValue;
        if (this.currButtonID == 20) {
            this.setGraphValue(vertValue);
        }
        this.recomputeImage();
    }
    
    private void drawCenterString(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n - 4 * s.length() / 2, n2);
    }
    
    public float getGamma() {
        return (float)this.colorFilter.getViewerGamma();
    }
    
    public void setGamma(final double viewerGamma) {
        this.colorFilter.setViewerGamma(viewerGamma);
        if (this.currButtonID == 2) {
            this.setGraphValue((float)viewerGamma);
        }
        this.recomputeImage();
    }
    
    public void dumpCode() {
        if (this.inDumpCode) {
            return;
        }
        this.inDumpCode = true;
        String s = this.htmlCode();
        if (this.saveMode) {
            final Point locationOnScreen = this.getLocationOnScreen();
            final TouchupQuery touchupQuery = new TouchupQuery(this.appletFrame, this.getAppName(), this.getPhrase(54) + "?", locationOnScreen.x + 65, locationOnScreen.y + 100);
            touchupQuery.show();
            this.repaintNow();
            if (touchupQuery.answerYes) {
                String s2 = "fileToURL";
                String string = "";
                try {
                    final URL fileToURL = this.fileToURL(this.saveModeURL + s);
                    string = fileToURL.toString();
                    s2 = "openConnection";
                    final URLConnection openConnection = fileToURL.openConnection();
                    s2 = "getInputStream";
                    openConnection.getInputStream().read(new byte[10], 0, 1);
                    s = s + "\n\n" + this.getPhrase(55);
                }
                catch (Exception ex) {
                    s = s + "\n\n" + this.getPhrase(56) + "\ndumpCode, " + s2 + " failure.\nfileURL: " + string + "\nException getMessage: " + ex.getMessage();
                }
                this.displayDialog(this.getPhrase(54), "The following adjustments have been saved: " + s);
            }
        }
        else {
            this.displayDialog("Touchup HTML code for displaying " + this.imageValue, s);
        }
        this.inDumpCode = false;
    }
    
    public void hidePictureInfo() {
        this.setAppletStatus("");
        this.touchupMode = 0;
        if (this.pictureInfoArea != null) {
            this.remove(this.pictureInfoArea);
            this.doLayout();
            this.pictureInfoArea = null;
            this.repaintNow();
        }
    }
    
    public void getSystemInfo() {
        this.docBaseURL = this.urlToBase(this.getDocumentBase().toString());
        this.touchupDebug = this.getStrParam("debug").equalsIgnoreCase("on");
        if (this.touchupDebug) {
            try {
                this.browserNavigatorInfo = "Java Class Version: " + System.getProperty("java.class.version") + "  " + "Java Version: " + System.getProperty("java.version") + "\n";
                this.mainWindow = JSObject.getWindow((Applet)this);
                if (this.mainWindow != null) {
                    final JSObject jsObject = (JSObject)this.mainWindow.getMember("document");
                    final JSObject jsObject2 = (JSObject)this.mainWindow.getMember("navigator");
                    if (jsObject != null & jsObject2 != null) {
                        this.browserNavigatorInfo = this.browserNavigatorInfo + "  " + (String)jsObject2.getMember("appName") + " " + (String)jsObject2.getMember("appVersion");
                        this.browserIsNetscape = (this.browserNavigatorInfo.indexOf("Netscape") != -1);
                    }
                }
            }
            catch (Exception ex) {}
        }
        this.getAppletsSupported = true;
        try {
            this.getAppletContext().getApplets();
        }
        catch (Exception ex2) {
            this.getAppletsSupported = false;
        }
    }
    
    public float getAlpha() {
        return (float)this.colorFilter.getAlpha();
    }
    
    public void setAlpha(final float graphValue) {
        this.colorFilter.setAlpha(graphValue);
        if (this.currButtonID == 5) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    private void setLanguageIndex(final int languageIndex) {
        this.languageIndex = languageIndex;
    }
    
    private int getLanguageIndex() {
        return this.languageIndex;
    }
    
    public void showEditButtons() {
        this.setGraph(false, this.getGamma(), 1.0f, 0.001f, 1.0f, 5.0f, 0.0f);
        this.currButtonID = 2;
        this.currButtonIndex = this.findButtonIdx(this.currButtonID);
        this.graphOn = true;
        if (this.imageWidth != -1) {
            this.touchupMode = 1;
            this.setAppletStatus("");
            this.TouchupShown = false;
            this.repaint();
        }
    }
    
    private void drawInfo(final Graphics graphics) {
        if (this.touchupMode == 1) {
            final Rectangle rectangle2;
            final Rectangle rectangle = rectangle2 = new Rectangle(this.infoRect);
            rectangle2.y += this.descAdj + 1;
            graphics.setColor(Color.lightGray);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height - 1);
            graphics.setColor(Color.black);
            graphics.drawRect(rectangle.x, rectangle.y - 1, rectangle.width, rectangle.height);
            graphics.setColor(Color.black);
            if (this.infoString.length() > 0) {
                final Shape clip = graphics.getClip();
                graphics.clipRect(0, rectangle.y, rectangle.width - 2, rectangle.height);
                graphics.drawString(this.infoString, 4, rectangle.y + rectangle.height - 5);
                graphics.setClip(clip);
            }
        }
    }
    
    private void colorClick(final int n, final int n2) {
        final Rectangle rectangle = new Rectangle(51, 26, 77, 44);
        if (rectangle.contains(n, n2)) {
            final int n3 = (n - rectangle.x) / 27;
            final int n4 = (n2 - rectangle.y) / 16;
            if (n - rectangle.x - n3 * 27 < 8) {
                int n5 = 1;
                switch (n4) {
                    case 0: {
                        n5 = 1;
                        break;
                    }
                    case 1: {
                        n5 = -1;
                        break;
                    }
                    case 2: {
                        n5 = 0;
                        break;
                    }
                }
                int redChannel = this.colorFilter.getRedChannel();
                int greenChannel = this.colorFilter.getGreenChannel();
                int blueChannel = this.colorFilter.getBlueChannel();
                switch (n3) {
                    case 0: {
                        redChannel = n5;
                        break;
                    }
                    case 1: {
                        greenChannel = n5;
                        break;
                    }
                    case 2: {
                        blueChannel = n5;
                        break;
                    }
                }
                this.colorFilter.setRGBChannels(redChannel, greenChannel, blueChannel);
                this.recomputeImage();
            }
        }
    }
    
    private Image loadSprite(final String s) {
        Image image = null;
        if (s != null && s.length() > 0) {
            Image loadImageWithProgress = null;
            try {
                loadImageWithProgress = this.loadImageWithProgress(s);
            }
            catch (Exception ex) {}
            if (loadImageWithProgress != null) {
                this.medTracker.addImage(loadImageWithProgress, 0);
                try {
                    this.medTracker.waitForAll();
                }
                catch (Exception ex2) {}
                final int width = loadImageWithProgress.getWidth(null);
                final int height = loadImageWithProgress.getHeight(null);
                if (width > 0) {
                    final int[] array = new int[width * height];
                    final PixelGrabber pixelGrabber = new PixelGrabber(loadImageWithProgress, 0, 0, width, height, array, 0, width);
                    try {
                        pixelGrabber.grabPixels();
                        this.lowResLoaded = true;
                        final int[] array2 = new int[width * height];
                        this.colorFilter.filterImage(array, array2, height, width);
                        final int filterWidth = this.colorFilter.getFilterWidth();
                        image = this.createImage(new MemoryImageSource(filterWidth, this.colorFilter.getFilterHeight(), array2, 0, filterWidth));
                    }
                    catch (InterruptedException ex3) {}
                    this.repaint();
                }
            }
            loadImageWithProgress.flush();
        }
        return image;
    }
    
    public void setThreshold(final int threshold) {
        this.colorFilter.setThreshold(threshold);
        this.recomputeImage();
    }
    
    private int findButtonIdx(final int n) {
        int n2 = -1;
        for (int i = 0; i < this.btnCount; ++i) {
            if (this.btnID[i] == n) {
                n2 = i;
            }
        }
        return n2;
    }
    
    public int getThreshold() {
        return this.colorFilter.getThreshold();
    }
    
    private void loadNextLanguage() {
        boolean loadLanguage = false;
        int languageIndex = this.getLanguageIndex();
        while (!loadLanguage) {
            ++languageIndex;
            loadLanguage = this.loadLanguage(languageIndex);
            if (!loadLanguage && languageIndex != 0) {
                this.languageCount = languageIndex;
                languageIndex = -1;
            }
        }
    }
    
    public int getGreenChannel() {
        return this.colorFilter.getGreenChannel();
    }
    
    public void paintDetails(final Graphics graphics) {
        final Shape clip = graphics.getClip();
        final int interfaceHeight = this.interfaceHeight;
        final int imageWidth = this.imageWidth;
        final int imageHeight = this.imageHeight;
        int n = 0;
        this.appletWidth = this.getBounds().width;
        this.appletHeight = this.getBounds().height;
        this.minWidth = Math.min(this.imageWidth, this.appletWidth);
        this.minHeight = Math.min(this.imageHeight + this.descAdj, this.appletHeight);
        this.stringToHashNumber("http://members.shaw.ca/jonespm2/software.htm");
        this.stringToHashNumber("Image displayed by");
        this.stringToHashNumber("© 2002 by Peter Jones");
        this.stringToHashNumber("(jonespm2@shaw.ca)");
        this.stringToHashNumber("Touchup");
        if (!this.stringToHashNumber("http://members.shaw.ca/jonespm2/software.htm").equals(String.valueOf(4674687352420714665L)) || !this.stringToHashNumber("Image displayed by").equals(String.valueOf(6580649370059270824L)) || !this.stringToHashNumber("© 2002 by Peter Jones").equals(String.valueOf(7478963644588893157L)) || !this.stringToHashNumber("(jonespm2@shaw.ca)").equals(String.valueOf(7292978622786784328L)) || !this.stringToHashNumber("Touchup").equals(String.valueOf(5462490809375709970L))) {
            this.imageLoaded = false;
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
            return;
        }
        if (!this.loadingImage && this.medTracker.checkID(0)) {
            if (this.touchupMode == 1) {
                graphics.setColor(Color.green);
            }
            else {
                graphics.setColor(this.stringToColor(this.foregroundColor));
            }
            if (this.isApplet) {
                n = (this.urlToBase(this.getCodeBase().toString()).equals(this.docBaseURL) ? 0 : 1);
            }
            if (n != 0) {
                graphics.drawString("Error: base URL for Applet and HTML page must match.", 6, this.minHeight / 2);
                return;
            }
            if (this.imageLoaded) {
                if (this.paintRequiresErase || this.offscreen != null) {
                    this.paintRequiresErase = false;
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
                }
                final int filterWidth = this.colorFilter.getFilterWidth();
                final int filterHeight = this.colorFilter.getFilterHeight();
                this.minHeight = Math.min(filterHeight + this.descAdj, this.appletHeight);
                this.minWidth = Math.min(filterWidth, this.appletWidth);
                final int n2 = (int)(filterWidth * this.zoomValue);
                final int n3 = (int)(filterHeight * this.zoomValue);
                int n4 = 0;
                do {
                    if (this.zoomValue != 1.0f) {
                        this.minHeight = Math.min(n3 + this.descAdj, this.appletHeight);
                        this.minWidth = Math.min(n2, this.appletWidth);
                        if (n2 > this.appletWidth && n3 > this.appletHeight) {
                            graphics.drawImage(this.myPictureMod, this.horizValue + (this.appletWidth - n2) / 2, this.vertValue + this.descAdj + (this.appletHeight - n3) / 2, n2, n3, this);
                        }
                        else {
                            graphics.drawImage(this.myPictureMod, this.horizValue, this.vertValue + this.descAdj, n2, n3, this);
                        }
                    }
                    else {
                        graphics.drawImage(this.myPictureMod, this.horizValue, this.vertValue + this.descAdj, filterWidth, filterHeight, this);
                    }
                    if (this.imageTitle != null && this.touchupMode != 3) {
                        this.drawTitle(graphics, this.imageTitle);
                    }
                    if (this.zoomAreaOn) {
                        this.paintZoomArea(graphics);
                    }
                    if (this.touchupMode == 0) {
                        n4 = 2;
                    }
                    graphics.setClip(clip);
                } while (++n4 < 1);
                graphics.setColor(this.getBackground());
                if (this.touchupMode != 1) {
                    graphics.fillRect(0, this.descAdj - 5, this.minWidth, 5 + Math.max(0, this.getVertical()));
                }
                else {
                    graphics.fillRect(0, this.descAdj - 5, this.minWidth, 5);
                }
            }
            if (this.imageWidth == -1) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
                this.titleDrawString(graphics, "Touchup unable to load image: " + this.imageValue, 6, this.statusBaseLine);
                this.titleDrawString(graphics, this.imageLoadErrString, 6, this.statusBaseLine + 20);
                return;
            }
            if (this.touchupMode == 1) {
                if (this.btnMedTracker.checkID(0)) {
                    if (this.btnInterface != null) {
                        graphics.drawImage(this.btnInterface, 0, this.descAdj, this);
                    }
                    if (this.saveMode && this.btnSavePic != null) {
                        graphics.drawImage(this.btnSavePic, 143, this.descAdj + 54, this);
                    }
                    boolean b = false;
                    if (this.userBtnJScript != null && this.userBtnJScript.length() != 0) {
                        b = true;
                    }
                    if (!b) {
                        this.eraseButton(graphics, this.findButtonIdx(17));
                    }
                    if (this.imageProtected) {
                        this.eraseButton(graphics, this.findButtonIdx(6));
                        this.eraseButton(graphics, this.findButtonIdx(7));
                        this.eraseButton(graphics, this.findButtonIdx(8));
                    }
                    this.drawGraph(graphics);
                    this.drawInfo(graphics);
                }
                this.setForeground(Color.green);
                graphics.setColor(Color.green);
                if (!this.registered) {
                    this.drawUnlicenced(graphics);
                }
                if (this.histogramDisplay != 0) {
                    graphics.clipRect(0, interfaceHeight, this.appletWidth, this.appletHeight - interfaceHeight);
                    this.drawHistogram(graphics);
                    graphics.setClip(clip);
                }
            }
            if (this.touchupMode == 0) {
                if (this.TouchupShown) {
                    this.drawTouchupButton(graphics);
                }
                if (this.showCopyrightOnImage()) {
                    final Color color = graphics.getColor();
                    graphics.setColor(Color.black);
                    graphics.drawString(this.getDisplayedByTitle(), 7, this.minHeight - 6 + 1);
                    graphics.setColor(Color.white);
                    graphics.drawString(this.getDisplayedByTitle(), 6, this.minHeight - 6);
                    graphics.setColor(color);
                }
            }
        }
        else {
            boolean b2 = true;
            int n5 = this.statusBaseLine + this.descAdj;
            if (this.myLowResMod != null && this.myLowResMod.getWidth(this) > 0) {
                b2 = false;
                graphics.drawImage(this.myLowResMod, 0, this.descAdj, this.appletWidth, this.appletHeight, this);
            }
            if (b2) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
            }
            this.titleDrawString(graphics, this.confirmRegistration(this.regName, this.regURL, this.regCode), 6, n5);
            n5 += 15;
            if (this.mainImageBytesRead <= 0) {
                this.titleDrawString(graphics, this.getAppName() + " " + this.getPhrase(49) + "...", 6, n5);
            }
            else {
                this.titleDrawString(graphics, this.getAppName() + " " + this.getPhrase(49) + ", " + this.mainImageBytesTotal + " " + this.getPhrase(50) + ".", 6, n5);
                n5 += 15;
                if (this.imageLastModified.length() > 0) {
                    this.titleDrawString(graphics, this.imageLastModified, 6, n5);
                    n5 += 15;
                }
                if (this.mainImageBytesTotal > 0) {
                    final Rectangle rectangle = new Rectangle(6, n5 - 9, 100, 12);
                    final Rectangle rectangle2 = new Rectangle(6, n5 - 9, 100 * this.mainImageBytesRead / this.mainImageBytesTotal, 12);
                    graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                    graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                    final long n6 = new Date().getTime() - this.mainImageStartLoad;
                    if (n6 > 0L) {
                        this.titleDrawString(graphics, n6 * (this.mainImageBytesTotal - this.mainImageBytesRead) / this.mainImageBytesRead / 100L / 10.0f + " " + this.getPhrase(53) + ".", 112, n5 + 2);
                    }
                }
            }
        }
        super.paint(graphics);
        if (this.descArea != null && this.descShown) {
            this.descArea.paint(graphics);
        }
    }
    
    private void drawDepressed(final Graphics graphics, final Rectangle rectangle) {
        graphics.setColor(Color.gray);
        rectangle.y += this.descAdj;
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1);
        graphics.setColor(Color.black);
        graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
        graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height - 1);
    }
    
    private String getLanguageFileName(final int n) {
        if (n == 0) {
            return "englishtouchup.txt";
        }
        return "touchuplanguage" + (n + 1) + ".txt";
    }
    
    private Image getJARImage(final String s) {
        Image image;
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(s));
            final byte[] array = new byte[10000];
            bufferedInputStream.read(array, 0, 10000);
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (Exception ex) {
            image = this.getImage(this.getDocumentBase(), s);
        }
        return image;
    }
    
    private boolean loadLanguage(final int languageIndex) {
        boolean b = true;
        this.setLanguageIndex(languageIndex);
        if (languageIndex >= this.languageCount) {
            return false;
        }
        try {
            if (this.languageText[languageIndex] == null) {
                b = false;
                final String languageFileName = this.getLanguageFileName(languageIndex);
                final URL fileToURL = this.fileToURL(languageFileName);
                this.languageFile = new String(languageFileName);
                InputStream inputStream = this.getClass().getResourceAsStream(languageFileName);
                if (inputStream == null) {
                    inputStream = fileToURL.openStream();
                }
                final int n = 10000;
                final byte[] array = new byte[n];
                int n2 = 0;
                int i = 1;
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    while (i > 0) {
                        i = bufferedInputStream.read(array, n2, n);
                        if (n2 == 0 && i > 0 && array[0] == 60) {
                            return false;
                        }
                        n2 += i;
                    }
                    if (++n2 > 1) {
                        final byte b2 = array[n2];
                        final byte b3 = array[n2 - 1];
                    }
                }
                catch (Exception ex) {}
                if ((this.languageTextLen[languageIndex] = n2) > 0) {
                    this.languageText[languageIndex] = new String(array, 0, n2);
                }
            }
            b = true;
            int j = 0;
            this.phraseCount = 0;
            while (j < this.languageTextLen[languageIndex]) {
                this.phraseStart[this.phraseCount++] = j;
                j = this.languageText[languageIndex].indexOf("\n", j) + 1;
                if (j == 0) {
                    j = this.languageTextLen[languageIndex];
                }
            }
        }
        catch (Exception ex2) {}
        return b;
    }
    
    public float getSaturation() {
        return (float)this.colorFilter.getViewerSaturation();
    }
    
    public void setSaturation(final float graphValue) {
        this.colorFilter.setViewerSaturation(graphValue);
        if (this.currButtonID == 12) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    public void setDistanceCalibration(final float distScale, final String distUnits) {
        this.distScale = distScale;
        this.distUnits = distUnits;
    }
    
    public void recreateZoomArea() {
        this.createZoomArea(this.zoomMouseX, this.zoomMouseY);
    }
    
    public void createZoomArea(final int zoomMouseX, final int zoomMouseY) {
        if (!this.zoomAreaOn) {
            this.zoomStatusBar();
        }
        this.zoomAreaOn = true;
        this.setCursor(new Cursor(1));
        this.filteredWidth = this.colorFilter.getFilterWidth();
        this.filteredHeight = this.colorFilter.getFilterHeight();
        this.zoomMouseX = zoomMouseX;
        this.zoomMouseY = zoomMouseY;
        final int n = this.zoomSize / this.zoomAreaFactor;
        final int n2 = n / 2;
        this.zoomX = Math.max(this.zoomBorder, Math.min(zoomMouseX - this.zoomSizeHalf, this.minWidth - this.zoomSize - this.zoomBorder));
        this.zoomY = Math.max(this.zoomBorder + this.descAdj + Math.max(0, this.getVertical()), Math.min(zoomMouseY - this.zoomSizeHalf, this.minHeight - this.zoomSize - this.zoomBorder - 12));
        final int n3 = -this.getHorizontal();
        final int n4 = -this.descAdj - this.getVertical();
        final int n5 = this.zoomSize(this.minWidth + n3) - n;
        final int n6 = this.zoomSize(this.minHeight + n4) - n;
        this.pixelXZoomStart = Math.max(n3, Math.min(this.zoomSize(zoomMouseX + n3) - n2, n5));
        this.pixelYZoomStart = Math.max(n4, Math.min(this.zoomSize(zoomMouseY + n4) - n2, n6));
        this.zoomImage = this.createImage(new MemoryImageSource(n, n, this.resultPixels, Math.max(0, this.pixelXZoomStart) + Math.max(0, this.pixelYZoomStart) * this.filteredWidth, this.filteredWidth));
        this.paintZoomArea(this.getGraphics());
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void mirror() {
        this.setMirror(!this.colorFilter.getMirror());
    }
    
    private void touchupError(final String s, final String s2) {
        this.displayDialog("Touchup Error", s + "\n" + s2);
    }
    
    public void zoomAreaExpand() {
        this.zoomSize += 10;
        this.zoomSizeHalf += 5;
        this.recreateZoomArea();
    }
    
    public void paintZoomArea(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.drawRect(this.zoomX - 1, this.zoomY - 1, this.zoomSize + 1, this.zoomSize + 1);
        graphics.setColor(Color.gray);
        graphics.drawRect(this.zoomX - 2, this.zoomY - 2, this.zoomSize + 3, this.zoomSize + 3);
        graphics.setColor(Color.black);
        graphics.drawImage(this.zoomImage, this.zoomX, this.zoomY, this.zoomSize, this.zoomSize, this);
        graphics.setColor(Color.gray);
        graphics.fillRect(this.zoomX - 2, this.zoomY + this.zoomSize + 1, this.zoomSize + 4, 13);
        graphics.setColor(Color.black);
        graphics.drawRect(this.zoomX - 3, this.zoomY - 3, this.zoomSize + 5, this.zoomSize + 5 + 12);
        graphics.setColor(Color.white);
        if (this.zoomDistOn) {
            final int n = this.zoomSize / this.zoomAreaFactor;
            final int n2 = this.zoomMouseXStartDist - (this.pixelXZoomStart + (this.zoomMouseX - this.zoomX) / this.zoomAreaFactor);
            final int n3 = this.zoomMouseYStartDist - (this.pixelYZoomStart - this.descAdj + (this.zoomMouseY - this.zoomY) / this.zoomAreaFactor);
            Math.abs(n2);
            Math.abs(n3);
            final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
            int n4 = this.zoomMouseXStartDist + this.horizValue;
            int n5 = this.zoomMouseYStartDist + this.descAdj * 2 + this.vertValue;
            Shape clip = null;
            boolean b = false;
            if (new Rectangle(this.zoomX, this.zoomY, this.zoomSize, this.zoomSize).contains(n4, n5)) {
                n4 = this.zoomX + (this.zoomMouseXStartDist - this.pixelXZoomStart) * this.zoomAreaFactor;
                if (sqrt > 10.0) {}
                n5 = this.zoomY + (this.zoomMouseYStartDist + this.descAdj - this.pixelYZoomStart) * this.zoomAreaFactor;
                b = true;
                clip = graphics.getClip();
                graphics.clipRect(this.zoomX, this.zoomY, this.zoomSize, this.zoomSize);
            }
            graphics.setColor(Color.green);
            graphics.drawRect(n4 - 1, n5 - 1, 2, 2);
            graphics.setColor(Color.black);
            graphics.drawRect(n4 - 2, n5 - 2, 4, 4);
            graphics.setColor(Color.green);
            graphics.drawLine(n4, n5, this.zoomMouseX, this.zoomMouseY);
            if (b) {
                graphics.setClip(clip);
            }
            graphics.setColor(Color.white);
            graphics.drawString("Pos: " + (this.zoomMouseX - this.horizValue) + ", " + (this.zoomMouseY - this.descAdj - this.vertValue) + "  Dist: " + this.roundHundreds((float)sqrt * this.distScale) + " " + this.distUnits, this.zoomX, this.zoomY + this.zoomSize + 12);
        }
        else {
            graphics.drawString("Pos: " + (this.zoomMouseX - this.horizValue) + ", " + (this.zoomMouseY - this.descAdj - this.vertValue) + "  'D' for distance", this.zoomX, this.zoomY + this.zoomSize + 12);
        }
        graphics.setColor(color);
    }
    
    private void drawGraphButton(final Graphics graphics, final Rectangle rectangle, final Color color, final Color color2) {
        final Color color3 = graphics.getColor();
        graphics.setColor(color);
        graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
        graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height - 1);
        graphics.setColor(color2);
        graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y + 1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
        graphics.drawLine(rectangle.x + 1, rectangle.y + rectangle.height - 1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
        graphics.setColor(Color.black);
        graphics.drawLine(rectangle.x + rectangle.width, rectangle.y + 1, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        graphics.drawLine(rectangle.x + 1, rectangle.y + rectangle.height, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        graphics.setColor(color3);
    }
    
    private void drawTouchupButton(final Graphics graphics) {
        final Color color = graphics.getColor();
        for (int i = this.btnMiniStart; i < this.btnCount; ++i) {
            Image image = null;
            switch (this.btnID[i]) {
                case 31: {
                    image = this.btnMiniPanelEdit;
                    break;
                }
                case 32: {
                    image = this.btnMiniPanelZoom;
                    break;
                }
                case 33: {
                    image = this.btnMiniPanelInfo;
                    break;
                }
            }
            if (image != null) {
                graphics.drawImage(image, this.btnRect[i].x - 1, this.descAdj + this.btnRect[i].y - 1, this);
            }
        }
        graphics.setColor(color);
    }
    
    String getButtonTip(final int n) {
        String s = null;
        switch (n) {
            case 24: {
                if (this.saveMode) {
                    s = this.getPhrase(54);
                    break;
                }
                s = this.getPhrase(n);
                break;
            }
            case 26: {
                s = this.getPhrase(n) + " " + this.getAppURL();
                break;
            }
            case 17: {
                s = this.userBtnTitle;
                break;
            }
            case 27:
            case 28:
            case 29:
            case 30: {
                s = this.getPhrase(30);
                break;
            }
            default: {
                s = this.getPhrase(n);
                break;
            }
        }
        return s;
    }
    
    public void hideEditButtons() {
        this.touchupMode = 0;
        this.paintRequiresErase = true;
        this.repaint();
    }
    
    private long myHash(final String s) {
        long n = 7023846965912266599L;
        final byte[] bytes = s.getBytes();
        for (int length = bytes.length, i = 0; i < length; ++i) {
            n = (n << 2) + (n >> 30 | 0x3L) + bytes[i];
        }
        return n;
    }
    
    public void enableUpdating() {
        this.updatingEnabled = true;
        if (this.updatePending) {
            this.recomputeImage();
        }
    }
    
    public void disableUpdating() {
        this.updatingEnabled = false;
    }
    
    public boolean getApplyImageLevelFilters() {
        return this.colorFilter.getApplyImageLevelFilters();
    }
    
    public void setApplyImageLevelFilters(final boolean applyImageLevelFilters) {
        this.colorFilter.setApplyImageLevelFilters(applyImageLevelFilters);
        this.recomputeImage();
    }
    
    void setAppletStatus(final String s) {
        if (this.isApplet) {
            this.getAppletContext().showStatus(s);
        }
    }
    
    public float getBrightness() {
        return this.colorFilter.getViewerBrightness();
    }
    
    public void setBrightness(final int viewerBrightness) {
        this.colorFilter.setViewerBrightness(viewerBrightness);
        if (this.currButtonID == 3) {
            this.setGraphValue(viewerBrightness);
        }
        this.recomputeImage();
    }
    
    private void loadImageLow(final String s, final String s2, final String s3, final String s4, final String imageTitle, final String titleJustify) {
        Image image = null;
        boolean b = false;
        if (!this.firstImageLoad && s.equals(this.previousImage)) {
            return;
        }
        if (!this.firstImageLoad) {
            this.setHyperlink("", "");
        }
        this.firstImageLoad = false;
        this.hidePictureInfo();
        this.paintRequiresErase = true;
        this.pictureInfoText = null;
        this.imageValue = s;
        this.previousImage = s;
        this.imageLoaded = false;
        this.loadingImage = true;
        this.imageWidth = 0;
        this.mainImageBytesTotal = -1;
        this.zoomAreaOn = false;
        this.setHorizontal(0);
        this.setVertical(0);
        this.setDesc(this.convertExtendedChars(s3), this.descFgColor, this.descBgColor);
        this.setPictureInfo(this.convertExtendedChars(s4));
        this.lowResLoaded = false;
        if (this.myLowResMod != null) {
            this.myLowResMod.flush();
        }
        this.myLowResMod = null;
        this.repaintNow();
        this.imageTitle = imageTitle;
        this.titleJustify = titleJustify;
        this.loadLowRes(s2);
        if (this.spriteTest) {
            try {
                this.spriteImage = this.loadSprite("sprite.gif");
                this.spriteW = this.spriteImage.getWidth(this);
                this.spriteH = this.spriteImage.getHeight(this);
                this.spritePixels = new int[this.spriteW * this.spriteH];
                final PixelGrabber pixelGrabber = new PixelGrabber(this.spriteImage, 0, 0, this.spriteW, this.spriteH, this.spritePixels, 0, this.spriteW);
                try {
                    pixelGrabber.grabPixels();
                    this.spriteLoaded = true;
                }
                catch (Exception ex3) {}
            }
            catch (Exception ex4) {}
        }
        if (s.length() == 0) {
            image = this.createImage(new MemoryImageSource(1, 1, new int[] { this.stringToColor(this.pageColor).getRGB() }, 0, 1));
            b = true;
            this.loadingImage = false;
        }
        else {
            try {
                image = this.loadImageWithProgress(s);
                b = true;
            }
            catch (Exception ex5) {}
        }
        if (image == null) {
            this.loadingImage = false;
            this.imageWidth = -1;
            this.repaintNow();
        }
        if (!b || image == null) {
            return;
        }
        this.medTracker.addImage(image, 0);
        try {
            this.medTracker.waitForAll();
        }
        catch (Exception ex) {
            this.touchupError("(waitForAll) Can't load image \"" + s + "\".", ex.getMessage());
        }
        this.loadingImage = false;
        this.imageWidth = image.getWidth(null);
        this.imageHeight = image.getHeight(null);
        this.colorFilter.setWidthHeight(this.imageWidth, this.imageHeight);
        if (this.imageWidth != -1) {
            this.originalPixels = new int[this.imageWidth * this.imageHeight];
            final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 0, 0, this.imageWidth, this.imageHeight, this.originalPixels, 0, this.imageWidth);
            try {
                pixelGrabber2.grabPixels();
                this.imageLoaded = true;
            }
            catch (InterruptedException ex2) {
                this.touchupError("loadImage, PixelGrabber failure.", ex2.getMessage());
            }
        }
        image.flush();
        this.recomputeImage();
    }
    
    private void mouseEnteredTouchup() {
        if (!this.executeUserScript(this.enterTouchupScript)) {
            this.enterTouchupScript = "";
        }
    }
    
    private void mouseExitedTouchup() {
        if (!this.executeUserScript(this.exitedTouchupScript)) {
            this.exitedTouchupScript = "";
        }
    }
    
    public void rotate() {
        this.setRotate(this.colorFilter.getRotate() + 90);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public boolean getBlackAndWhite() {
        return this.colorFilter.getBlackAndWhite();
    }
    
    public void setBlackAndWhite(final boolean blackAndWhite) {
        this.colorFilter.setBlackAndWhite(blackAndWhite);
        this.recomputeImage();
    }
    
    public void help() {
        final String appURL = this.getAppURL();
        try {
            this.getAppletContext().showDocument(new URL(appURL), "_blank");
        }
        catch (Exception ex) {
            this.displayDialog("Touchup Help", "Unable to load local Touchup Help.  Visit " + appURL);
        }
    }
    
    String getDisplayedByTitle() {
        return "Image displayed by" + " " + this.getAppName();
    }
    
    String getAppDesc() {
        return this.getAppName() + " " + "© 2002 by Peter Jones";
    }
    
    private String getAppName() {
        return "Touchup" + " " + "3.0a";
    }
    
    boolean inCopyrightArea(final int n, final int n2) {
        if (this.touchupMode != 0 || !this.showCopyrightOnImage() || this.tooSmallToEdit) {
            return false;
        }
        if (this.touchupMode != 0) {
            return n < 320 && n2 > this.minHeight - 20 && n2 < this.minHeight;
        }
        return n < 160 && n2 > this.minHeight - 20 && n2 < this.minHeight;
    }
    
    private void directoryList() {
        final String[] list = new File(this.getCodeBase().toString()).list();
        for (int i = 0; i < list.length; ++i) {
            final File file = new File(list[i]);
            if (file.isFile()) {
                System.out.println("File " + file.getName());
            }
            else if (file.isDirectory()) {
                System.out.println("Directory " + file.getName());
            }
        }
    }
    
    public float getContrast() {
        return (float)this.colorFilter.getViewerContrast();
    }
    
    public boolean getFlip() {
        return this.colorFilter.getFlip();
    }
    
    public void setFlip(final boolean flip) {
        this.colorFilter.setFlip(flip);
        this.recomputeImage();
    }
    
    public void setRGBChannels(final int n, final int n2, final int n3) {
        this.colorFilter.setRGBChannels(n, n2, n3);
        this.recomputeImage();
    }
    
    public void setContrast(final float graphValue) {
        this.colorFilter.setViewerContrast(graphValue);
        if (this.currButtonID == 4) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    public void zoomAreaOff() {
        this.zoomDistOn = false;
        this.zoomAreaOn = false;
        this.setCursor(new Cursor(12));
        this.touchupMode = 0;
        this.setAppletStatus("");
        this.repaintNow();
        this.zoomAreaFactor = 2;
    }
    
    public void modeOff() {
        this.zoomDistOn = false;
        if (this.touchupMode == 3) {
            this.hidePictureInfo();
            return;
        }
        this.zoomAreaOff();
    }
    
    private void eraseButton(final Graphics graphics, final int n) {
        if (n != -1) {
            graphics.setColor(Color.lightGray);
            final Rectangle rectangle2;
            final Rectangle rectangle = rectangle2 = new Rectangle(this.btnRect[n]);
            rectangle2.y += this.descAdj;
            graphics.fillRect(rectangle.x + 1, rectangle.y + 1, rectangle.width - 3, rectangle.height - 4);
        }
    }
    
    private void applyExifOrientation() {
        if (this.exifOrientEnabled) {
            boolean mirror = false;
            boolean flip = false;
            int rotate = 0;
            switch (this.exifOrientation) {
                case 2: {
                    mirror = true;
                    break;
                }
                case 3: {
                    rotate = 180;
                    break;
                }
                case 4: {
                    flip = true;
                    break;
                }
                case 5: {
                    flip = true;
                    rotate = 90;
                    break;
                }
                case 6: {
                    rotate = 270;
                    break;
                }
                case 7: {
                    mirror = true;
                    rotate = 90;
                    break;
                }
                case 8: {
                    rotate = 90;
                    break;
                }
            }
            this.colorFilter.setMirror(mirror);
            this.colorFilter.setFlip(flip);
            this.colorFilter.setRotate(rotate);
        }
    }
    
    private boolean performButton(final int n, int n2) {
        final int currButtonIndex = this.currButtonIndex;
        boolean b = false;
        n2 -= this.descAdj;
        if (this.graphRect.contains(n, n2) && this.currButtonIndex != -1) {
            final float graphValue = this.graphValue;
            b = true;
            if (this.currButtonID == 13) {
                this.colorClick(n - this.graphRect.x, n2);
                return true;
            }
            float graphDefaultValue;
            if (n2 < this.graphLessRect.y + this.graphLessRect.height + 1) {
                float graphAddAmount;
                if (this.graphAddAmount != 0.0f) {
                    graphAddAmount = this.graphAddAmount;
                }
                else {
                    graphAddAmount = 0.1f;
                }
                if (this.graphLessRect.contains(n, n2)) {
                    this.depressedRectangle(this.graphLessRect);
                    graphDefaultValue = this.graphValue - graphAddAmount;
                    this.setGraphValue(graphDefaultValue);
                }
                else if (this.graphMoreRect.contains(n, n2)) {
                    this.depressedRectangle(this.graphMoreRect);
                    graphDefaultValue = this.graphValue + graphAddAmount;
                    this.setGraphValue(graphDefaultValue);
                }
                else {
                    if (!this.graphDefaultRect.contains(n, n2)) {
                        return true;
                    }
                    this.depressedRectangle(this.graphDefaultRect);
                    graphDefaultValue = this.graphDefaultValue;
                    this.setGraphValue(graphDefaultValue);
                }
            }
            else {
                this.graphIndicatorX = Math.max(Math.min(n, this.graphBarRight), this.graphBarLeft);
                this.graphIndicatorX -= this.graphRect.x;
                final int n3 = this.graphBarLeft - this.graphRect.x;
                final int n4 = this.graphRect.width / 2;
                final int n5 = (this.graphBarRight - this.graphBarLeft) / 2;
                if (this.graphIndicatorX <= n4) {
                    graphDefaultValue = (this.graphIndicatorX - n3) / n5 * (this.graphMidValue - this.graphLeftValue) + this.graphLeftValue;
                }
                else {
                    graphDefaultValue = (this.graphIndicatorX - n4) / n5 * (this.graphRightValue - this.graphMidValue) + this.graphMidValue;
                }
            }
            final float min = Math.min(Math.max(graphDefaultValue, this.graphLeftValue), this.graphRightValue);
            if (this.graphValueInteger) {
                this.graphValue = (int)min;
            }
            else {
                this.graphValue = min;
            }
            if (this.graphValue == graphValue) {
                try {
                    Thread.sleep(133L);
                }
                catch (Exception ex) {}
                this.drawGraph(this.getGraphics());
            }
            else {
                switch (this.currButtonID) {
                    case 2: {
                        this.setGamma(min);
                        break;
                    }
                    case 3: {
                        this.setBrightness((int)min);
                        break;
                    }
                    case 4: {
                        this.setContrast(min);
                        break;
                    }
                    case 5: {
                        this.setAlpha(min);
                        break;
                    }
                    case 6: {
                        if (!this.imageProtected) {
                            this.setPosterize((int)min);
                            break;
                        }
                        break;
                    }
                    case 7: {
                        this.setNoiseFrequency((int)min);
                        break;
                    }
                    case 8: {
                        this.setNoiseLevel((int)min);
                        break;
                    }
                    case 9: {
                        this.setRed(min);
                        break;
                    }
                    case 10: {
                        this.setGreen(min);
                        break;
                    }
                    case 11: {
                        this.setBlue(min);
                        break;
                    }
                    case 12: {
                        this.setSaturation(min);
                        break;
                    }
                    case 18: {
                        this.setZoom(min);
                        break;
                    }
                    case 19: {
                        this.setHorizontal((int)min);
                        break;
                    }
                    case 20: {
                        this.setVertical((int)min);
                        break;
                    }
                }
            }
        }
        n2 += this.descAdj;
        final int inButton = this.inButton(n, n2);
        if (inButton != -1 && this.btnID[inButton] != 30) {
            b = true;
            this.graphChanged = false;
            this.depressedButton(this.getGraphics(), inButton);
            switch (inButton) {
                case 0: {
                    this.touchupMode = 0;
                    this.TouchupShown = true;
                    this.paintRequiresErase = true;
                    break;
                }
                case 1: {
                    this.resetImage();
                    break;
                }
                case 2: {
                    this.setGraph(false, this.getGamma(), 1.0f, 0.001f, 1.0f, 5.0f, 0.0f);
                    break;
                }
                case 3: {
                    this.setGraph(true, this.getBrightness(), 0.0f, -255.0f, 0.0f, 255.0f, 10.0f);
                    break;
                }
                case 4: {
                    this.setGraph(false, this.getContrast(), 1.0f, 0.001f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 5: {
                    this.setGraph(false, this.getAlpha(), 0.0f, 0.0f, 0.5f, 1.0f, 0.1f);
                    break;
                }
                case 6: {
                    this.setGraph(true, this.getPosterize(), 0.0f, 0.0f, 32.0f, 255.0f, 1.0f);
                    break;
                }
                case 7: {
                    this.setGraph(true, this.getNoiseFrequency(), 0.0f, 0.0f, 128.0f, 255.0f, 1.0f);
                    break;
                }
                case 8: {
                    this.setGraph(true, this.getNoiseLevel(), -30.0f, -255.0f, 0.0f, 255.0f, 5.0f);
                    break;
                }
                case 9: {
                    this.setGraph(false, this.getRed(), 1.0f, 0.001f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 10: {
                    this.setGraph(false, this.getGreen(), 1.0f, 0.001f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 11: {
                    this.setGraph(false, this.getBlue(), 1.0f, 0.001f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 12: {
                    this.setGraph(false, this.getSaturation(), 1.0f, 0.001f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 13: {
                    this.graphChanged = true;
                    break;
                }
                case 14: {
                    this.blackAndWhite();
                    break;
                }
                case 15: {
                    this.histogramDisplay = (this.histogramDisplay + 1) % 2;
                    this.colorFilter.setCreateHistogram(this.histogramDisplay != 0);
                    if (this.histogramDisplay == 0 || this.colorFilter.getHaveHistogram()) {
                        this.repaint();
                        break;
                    }
                    this.recomputeImage();
                    break;
                }
                case 17: {
                    this.userButton();
                    break;
                }
                case 18: {
                    this.setGraph(false, this.getZoom(), 1.0f, 0.2f, 1.0f, 3.0f, 0.0f);
                    break;
                }
                case 19: {
                    this.setGraph(true, this.getHorizontal(), 0.0f, -this.imageWidth, 0.0f, this.imageWidth, 5.0f);
                    break;
                }
                case 20: {
                    this.setGraph(true, this.getVertical(), 0.0f, -this.imageHeight, 0.0f, this.imageHeight, 5.0f);
                    break;
                }
                case 21: {
                    this.mirror();
                    break;
                }
                case 22: {
                    this.flip();
                    break;
                }
                case 23: {
                    this.rotate();
                    break;
                }
                case 16: {
                    this.loadNextLanguage();
                    this.infoString = this.getPhrase(16);
                    this.repaint();
                    break;
                }
                case 24: {
                    this.dumpCode();
                    break;
                }
                case 25: {
                    final String s = new String(this.infoString);
                    this.infoString = this.getPhrase(41);
                    this.repaintInfoNow();
                    this.eraseButton(this.getGraphics(), inButton);
                    final int histogramDisplay = this.histogramDisplay;
                    this.histogramDisplay = 0;
                    this.colorFilter.setCreateHistogram(false);
                    this.benchmarkTest();
                    this.histogramDisplay = histogramDisplay;
                    this.colorFilter.setCreateHistogram(histogramDisplay != 0);
                    this.infoString = new String(s);
                    this.repaintInfoNow();
                    this.recomputeImage();
                    break;
                }
                case 26: {
                    this.help();
                    break;
                }
            }
            if (!this.graphChanged) {
                this.currButtonIndex = currButtonIndex;
                this.currButtonID = this.btnID[this.currButtonIndex];
            }
            else {
                this.currButtonIndex = inButton;
                this.currButtonID = this.btnID[inButton];
            }
            this.graphOn = true;
            this.repaintNow();
        }
        return b;
    }
    
    private String stringToHashNumber(final String s) {
        final Random random = new Random();
        random.setSeed(this.myHash(s));
        return "" + Math.abs(random.nextLong());
    }
    
    private void loadLowRes(final String s) {
        if (s != null && s.length() > 0) {
            Image loadImageWithProgress = null;
            try {
                loadImageWithProgress = this.loadImageWithProgress(s);
            }
            catch (Exception ex) {}
            if (loadImageWithProgress != null) {
                this.medTracker.addImage(loadImageWithProgress, 0);
                try {
                    this.medTracker.waitForAll();
                }
                catch (Exception ex2) {}
                final int width = loadImageWithProgress.getWidth(null);
                final int height = loadImageWithProgress.getHeight(null);
                if (width > 0) {
                    final int[] array = new int[width * height];
                    final PixelGrabber pixelGrabber = new PixelGrabber(loadImageWithProgress, 0, 0, width, height, array, 0, width);
                    try {
                        pixelGrabber.grabPixels();
                        this.lowResLoaded = true;
                        final int[] array2 = new int[width * height];
                        this.colorFilter.filterImage(array, array2, height, width);
                        final int filterWidth = this.colorFilter.getFilterWidth();
                        this.myLowResMod = this.createImage(new MemoryImageSource(filterWidth, this.colorFilter.getFilterHeight(), array2, 0, filterWidth));
                    }
                    catch (InterruptedException ex3) {}
                    this.repaint();
                }
            }
            loadImageWithProgress.flush();
        }
    }
    
    private String confirmRegistration(final String s, final String s2, final String s3) {
        String s4 = this.getPhrase(47);
        final String lowerCase = s2.toLowerCase();
        if (!this.isApplet) {
            return "";
        }
        final String lowerCase2 = this.getCodeBase().toString().toLowerCase();
        this.registered = (s.length() > 0);
        if (this.registered) {
            if (lowerCase.length() == 0) {
                this.registered = false;
                s4 = "regURL parameter missing from APPLET statement";
            }
            else if (this.isOnline() && lowerCase2.indexOf(lowerCase) == -1) {
                this.registered = false;
                s4 = "Not registered for this URL";
            }
            if (this.registered) {
                if (s3.length() == 0) {
                    this.registered = false;
                    s4 = "RegCode parameter missing from APPLET statement";
                }
                else if (!s3.equalsIgnoreCase(this.nameURLToCode(s, s2))) {
                    this.registered = false;
                    s4 = "RegCode parameter is incorrect";
                }
            }
        }
        if (this.registered) {
            s4 = this.getPhrase(48) + " " + s;
        }
        if (!this.isOnline()) {
            s4 = s4 + " " + this.getPhrase(51);
        }
        return s4;
    }
    
    public void repaintNow() {
        this.paint(this.getGraphics());
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    private void drawButton(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(n, n2, n3, n4, true);
        graphics.draw3DRect(n, n2, n3, n4, true);
        graphics.setColor(Color.black);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
    }
    
    public void readAppletParams() {
        this.languageUseSecondary = this.getStrParam("secondarylanguage").equalsIgnoreCase("on");
        if (this.languageUseSecondary) {
            this.setLanguageIndex(1);
        }
        else {
            this.setLanguageIndex(0);
        }
        this.getSystemInfo();
        this.regName = this.getStrParam("regname");
        this.regURL = this.getStrParam("regurl");
        this.regCode = this.getStrParam("regcode");
        this.special = this.getStrParam("special");
        this.saveModeURL = this.getStrParam("record");
        this.saveMode = (this.saveModeURL.length() > 0);
        this.regDescription = this.confirmRegistration(this.regName, this.regURL, this.regCode);
        this.exifOrientEnabled = this.getStrParam("exiforient").equalsIgnoreCase("on");
        this.imageValue = this.getStrParam("image");
        this.backgroundColor = this.getStrParamDefault("background", "7F7F7F");
        this.pageColor = this.getStrParamDefault("pagecolor", "FFFFFF");
        this.setBackground(this.stringToColor(this.pageColor));
        this.foregroundColor = this.getStrParamDefault("foreground", "000000");
        this.setForeground(this.stringToColor(this.foregroundColor));
        this.alphaColor = this.getStrParamDefault("alphacolor", "FFFFFF");
        this.descFgColor = this.getStrParamDefault("descfgcolor", "000000");
        this.descBgColor = this.getStrParamDefault("descbgcolor", "FFFFFF");
        this.enterTouchupScript = this.getStrParam("entryscript");
        this.exitedTouchupScript = this.getStrParam("exitscript");
        this.userBtnTitle = this.getStrParam("btntitle");
        this.userBtnJScript = this.getStrParam("btnscript");
        this.callDetails = this.getParameter("clickscript");
        this.clickTitle = this.getStrParam("clicktitle");
        this.linkTarget = this.getStrParam("target");
        this.linkURL = this.getParameter("link");
        if (this.linkURL != null) {
            this.setCursor(new Cursor(12));
        }
        this.imageTitle = this.getStrParam("title");
        this.titleJustify = this.getStrParam("justify");
        final Color stringToColor = this.stringToColor(this.alphaColor);
        this.colorFilter.setAlphaColor((stringToColor.getRed() << 16) + (stringToColor.getGreen() << 8) + stringToColor.getBlue());
        this.colorFilter.setWidthHeight(this.appletWidth, this.appletHeight);
        this.colorFilter.setMatrixView(0);
        final String strParam = this.getStrParam("mode");
        this.viewOnly = strParam.equalsIgnoreCase("view");
        this.basicOnly = strParam.equalsIgnoreCase("basic");
        this.tooSmallToEdit = (this.getBounds().width < 150);
        if (this.tooSmallToEdit) {
            this.viewOnly = true;
        }
        this.colorFilter.setImageRed(this.getParam("red"));
        this.colorFilter.setImageGreen(this.getParam("green"));
        this.colorFilter.setImageBlue(this.getParam("blue"));
        this.colorFilter.setRGBChannels((int)this.getParam("redchannel"), (int)this.getParam("greenchannel"), (int)this.getParam("bluechannel"));
        this.colorFilter.setImageBrightness((int)this.getParamZeroDefault("brightness"));
        this.colorFilter.setImageGamma(this.getParam("gamma"));
        this.colorFilter.setImageContrast(this.getParam("contrast"));
        this.colorFilter.setPosterSteps((int)this.getParamZeroDefault("posterize"));
        this.colorFilter.setImageSaturation(this.getParam("saturation"));
        this.colorFilter.setAlpha(this.getParamZeroDefault("alpha"));
        this.zoomValue = this.getParam("zoom");
        this.horizValue = (int)this.getParamZeroDefault("horizontal");
        this.vertValue = (int)this.getParamZeroDefault("vertical");
        this.colorFilter.setNoiseLevel((int)this.getParamDefault("noiseLevel", -30.0f));
        this.colorFilter.setNoiseFrequency((int)this.getParamZeroDefault("noiseFrequency"));
        this.distScale = this.getParam("distScale");
        this.distUnits = this.getStrParam("distUnits");
        if (this.distUnits.length() == 0) {
            this.distUnits = new String("pixels");
        }
        this.imageProtected = (this.colorFilter.getPosterSteps() != 0 || this.colorFilter.getNoiseFrequency() != 0);
        this.colorFilter.setBlackAndWhite(this.getStrParam("color").equalsIgnoreCase("off"));
        this.colorFilter.setNegative(this.getStrParam("negative").equalsIgnoreCase("on"));
        this.colorFilter.setRotate((int)this.getParamZeroDefault("rotate"));
        this.colorFilter.setMirror(this.getStrParam("mirror").equalsIgnoreCase("on"));
        this.colorFilter.setFlip(this.getStrParam("flip").equalsIgnoreCase("on"));
        this.colorFilter.setFakeSky(this.getStrParam("fakesky").equalsIgnoreCase("on"));
        this.textHalo = this.getStrParam("halo").equalsIgnoreCase("on");
        this.showSyncButton = this.getStrParam("showsync").equalsIgnoreCase("on");
    }
    
    private Color stringToColor(final String s) {
        if (s != null && s.length() == 6) {
            return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
        }
        return new Color(0, 0, 0);
    }
    
    private String getStrParamDefault(final String s, final String s2) {
        final String touchupParam = this.getTouchupParam(s);
        if (touchupParam == null) {
            return s2;
        }
        return touchupParam;
    }
    
    private float getParamDefault(final String s, final float n) {
        float floatValue = n;
        final String touchupParam = this.getTouchupParam(s);
        if (touchupParam != null) {
            try {
                floatValue = Float.valueOf(touchupParam);
            }
            catch (Exception ex) {}
        }
        return floatValue;
    }
    
    public void setRGB(final float n, final float n2, final float n3) {
        this.colorFilter.setViewerRed(n);
        this.colorFilter.setViewerGreen(n2);
        this.colorFilter.setViewerBlue(n3);
        this.recomputeImage();
    }
    
    public void zoomAreaOut() {
        if (this.zoomAreaFactor > 2) {
            --this.zoomAreaFactor;
            this.recreateZoomArea();
            this.zoomStatusBar();
            return;
        }
        this.zoomAreaOff();
    }
    
    private void haloDrawString(final Graphics graphics, final String s, final int n, final int n2, final Color color, final Color color2) {
        graphics.setColor(color);
        int n3 = -1;
        do {
            int n4 = -1;
            do {
                graphics.drawString(s, n + n3, n2 + n4);
            } while (++n4 <= 1);
        } while (++n3 <= 1);
        graphics.setColor(color2);
        graphics.drawString(s, n, n2);
    }
    
    private void titleDrawString(final Graphics graphics, final String s, final int n, final int n2) {
        if (this.textHalo) {
            this.haloDrawString(graphics, s, n, n2, this.stringToColor(this.backgroundColor), this.stringToColor(this.foregroundColor));
            return;
        }
        graphics.setColor(this.stringToColor(this.foregroundColor));
        graphics.drawString(s, n, n2);
    }
    
    private void depressedRectangle(final Rectangle rectangle) {
        this.drawDepressed(this.getGraphics(), new Rectangle(rectangle));
    }
    
    public void setEntryExitScripts(final String enterTouchupScript, final String exitedTouchupScript) {
        this.enterTouchupScript = enterTouchupScript;
        this.exitedTouchupScript = exitedTouchupScript;
    }
    
    private void initLanguages() {
        int n = 0;
        do {
            this.languageText[n] = null;
        } while (++n < 20);
    }
    
    private void setGraph(final boolean graphValueInteger, float n, final float graphDefaultValue, final float graphLeftValue, final float graphMidValue, final float graphRightValue, final float graphAddAmount) {
        this.graphChanged = true;
        this.graphValueInteger = graphValueInteger;
        this.graphDefaultValue = graphDefaultValue;
        this.graphLeftValue = graphLeftValue;
        this.graphMidValue = graphMidValue;
        this.graphRightValue = graphRightValue;
        n = Math.min(n, this.graphRightValue);
        n = Math.max(n, this.graphLeftValue);
        this.graphValue = n;
        this.graphTitle = "none";
        this.graphAddAmount = graphAddAmount;
        this.setGraphValue(n);
    }
    
    String getAppURL() {
        return "http://members.shaw.ca/jonespm2/software.htm";
    }
    
    private void drawGraph(final Graphics graphics) {
        if (this.currButtonID == 13) {
            final Rectangle rectangle = new Rectangle(this.graphFrameRect);
            new Rectangle(this.graphFrameRect);
            final int n = 26 + this.descAdj;
            final int n2 = n - 10;
            final int n3 = rectangle.x + 6;
            if (this.btnColorPanel != null) {
                graphics.drawImage(this.btnColorPanel, rectangle.x, this.descAdj, this);
            }
            graphics.setColor(Color.black);
            graphics.drawString(this.getPhrase(34), n3, n2);
            graphics.drawString(this.getPhrase(35), rectangle.x + 51 - 6, n2);
            graphics.drawString(this.getPhrase(36), rectangle.x + 78 - 6, n2);
            graphics.drawString(this.getPhrase(37), rectangle.x + 105 - 6, n2);
            graphics.drawString(this.getPhrase(38), n3, n + 7);
            graphics.drawString(this.getPhrase(39), n3, n + 16 + 7);
            graphics.drawString(this.getPhrase(40), n3, n + 32 + 7);
            this.drawRadio(graphics, rectangle.x + 51, this.channelToIndex(this.colorFilter.getRedChannel()) * 16 + n);
            this.drawRadio(graphics, rectangle.x + 78, this.channelToIndex(this.colorFilter.getGreenChannel()) * 16 + n);
            this.drawRadio(graphics, rectangle.x + 105, this.channelToIndex(this.colorFilter.getBlueChannel()) * 16 + n);
            return;
        }
        final Color color = new Color(128, 128, 128);
        final Color lightGray = Color.lightGray;
        final Color black = Color.black;
        final Color white = Color.white;
        final Rectangle rectangle3;
        final Rectangle rectangle2 = rectangle3 = new Rectangle(this.graphFrameRect);
        rectangle3.y += this.descAdj;
        graphics.setColor(Color.black);
        graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(rectangle2.x, rectangle2.y + 1, rectangle2.width, rectangle2.height - 1, true);
        if (this.graphOn) {
            final Rectangle rectangle5;
            final Rectangle rectangle4 = rectangle5 = new Rectangle(this.graphRect);
            rectangle5.y += this.descAdj;
            graphics.setColor(lightGray);
            graphics.setColor(black);
            final int n4 = rectangle4.y + 15;
            final int n5 = this.descAdj + this.graphBarY;
            final int graphBarLeft = this.graphBarLeft;
            final int graphBarRight = this.graphBarRight;
            final int n6 = graphBarRight - graphBarLeft;
            if (this.graphIndicatorX > 0) {
                int n7 = 1;
                int n8 = rectangle4.x + this.graphIndicatorX;
                int n9 = n5 - 3;
                int n10 = 0;
                do {
                    graphics.drawLine(n8, n9, n8 + n7, n9);
                    n7 += 2;
                    --n8;
                    --n9;
                } while (++n10 < 4);
            }
            final Rectangle rectangle7;
            final Rectangle rectangle6 = rectangle7 = new Rectangle(this.graphLessRect);
            rectangle7.y += this.descAdj;
            this.drawGraphButton(graphics, rectangle6, white, color);
            graphics.drawLine(rectangle6.x + 6, rectangle6.y + 8, rectangle6.x + 10, rectangle6.y + 8);
            final Rectangle rectangle9;
            final Rectangle rectangle8 = rectangle9 = new Rectangle(this.graphMoreRect);
            rectangle9.y += this.descAdj;
            this.drawGraphButton(graphics, rectangle8, white, color);
            graphics.drawLine(rectangle8.x + 6, rectangle8.y + 8, rectangle8.x + 10, rectangle8.y + 8);
            graphics.drawLine(rectangle8.x + 8, rectangle8.y + 6, rectangle8.x + 8, rectangle8.y + 10);
            final Rectangle rectangle11;
            final Rectangle rectangle10 = rectangle11 = new Rectangle(this.graphDefaultRect);
            rectangle11.y += this.descAdj;
            this.drawGraphButton(graphics, rectangle10, white, color);
            graphics.drawString(this.getPhrase(29), rectangle10.x + 6, rectangle10.y + rectangle10.height - 3);
            this.graphTitle = this.getTitleOfPhrase(this.currButtonIndex);
            if (this.graphValueInteger) {
                graphics.drawString(this.graphTitle + ": " + (int)this.graphValue, graphBarLeft, n4);
            }
            else {
                graphics.drawString(this.graphTitle + ": " + this.truncToTwoDecimals(this.graphValue), graphBarLeft, n4);
            }
            graphics.drawLine(graphBarLeft, n5, graphBarRight, n5);
            graphics.drawLine(graphBarLeft, n5, graphBarLeft, n5 + 4);
            graphics.drawLine(graphBarLeft + n6 / 2, n5, graphBarLeft + n6 / 2, n5 + 4);
            graphics.drawLine(graphBarRight, n5, graphBarRight, n5 + 4);
            graphics.drawLine(graphBarLeft + n6 / 4, n5, graphBarLeft + n6 / 4, n5 + 2);
            graphics.drawLine(graphBarLeft + 3 * n6 / 4, n5, graphBarLeft + 3 * n6 / 4, n5 + 2);
            final int n11 = n5 + 16;
            if (this.graphValueInteger) {
                this.drawCenterString(graphics, "" + (int)this.graphLeftValue, graphBarLeft, n11);
                this.drawCenterString(graphics, "" + (int)this.graphMidValue, graphBarLeft + n6 / 2 - 1, n11);
                this.drawCenterString(graphics, "" + (int)this.graphRightValue, graphBarRight - 3, n11);
                return;
            }
            this.drawCenterString(graphics, "" + this.truncToTwoDecimals(this.graphLeftValue), graphBarLeft, n11);
            this.drawCenterString(graphics, "" + this.truncToTwoDecimals(this.graphMidValue), graphBarLeft + n6 / 2 - 1, n11);
            this.drawCenterString(graphics, "" + this.truncToTwoDecimals(this.graphRightValue), graphBarRight - 3, n11);
        }
    }
    
    public float getBlue() {
        return (float)this.colorFilter.getViewerBlue();
    }
    
    public void setBlue(final float graphValue) {
        this.colorFilter.setViewerBlue(graphValue);
        if (this.currButtonID == 11) {
            this.setGraphValue(graphValue);
        }
        this.recomputeImage();
    }
    
    public void syncSetApplet(final double viewerContrast, final int viewerBrightness, final double viewerGamma, final double viewerSaturation, final double viewerRed, final double viewerGreen, final double viewerBlue, final int n, final int n2, final int n3, final int alphaColor, final double alpha, final boolean blackAndWhite, final boolean negative) {
        this.colorFilter.setViewerContrast(viewerContrast);
        this.colorFilter.setViewerGamma(viewerGamma);
        this.colorFilter.setViewerBrightness(viewerBrightness);
        this.colorFilter.setViewerSaturation(viewerSaturation);
        this.colorFilter.setViewerRed(viewerRed);
        this.colorFilter.setViewerGreen(viewerGreen);
        this.colorFilter.setViewerBlue(viewerBlue);
        this.colorFilter.setBlackAndWhite(blackAndWhite);
        this.colorFilter.setNegative(negative);
        this.colorFilter.setRGBChannels(n, n2, n3);
        this.colorFilter.setAlphaColor(alphaColor);
        this.colorFilter.setAlpha(alpha);
        this.recomputeImage();
    }
    
    private String getStrParam(final String s) {
        final String touchupParam = this.getTouchupParam(s);
        if (touchupParam == null) {
            return "";
        }
        return touchupParam;
    }
    
    private float getParam(final String s) {
        float floatValue = 1.0f;
        final String touchupParam = this.getTouchupParam(s);
        if (touchupParam != null) {
            try {
                floatValue = Float.valueOf(touchupParam);
            }
            catch (Exception ex) {}
        }
        return floatValue;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setTitle(final String imageTitle, final String titleJustify) {
        this.imageTitle = imageTitle;
        this.titleJustify = titleJustify;
        this.repaint();
    }
    
    private void drawTitle(final Graphics graphics, final String s) {
        int n = 16 + this.descAdj;
        if (this.touchupMode != 2 && s != null) {
            int stringWidth;
            try {
                stringWidth = this.getFontMetrics(this.getFont()).stringWidth(s);
            }
            catch (Exception ex) {
                stringWidth = (int)(5.5f * s.length());
            }
            int n2;
            if (this.titleJustify == null) {
                n2 = 6;
            }
            else if (this.titleJustify.equalsIgnoreCase("right")) {
                n2 = this.minWidth - 6 - stringWidth;
            }
            else if (this.titleJustify.equalsIgnoreCase("center")) {
                n2 = (this.minWidth - stringWidth) / 2;
            }
            else if (this.titleJustify.equalsIgnoreCase("bottom")) {
                n2 = this.minWidth - 6 - stringWidth;
                n = this.minHeight - 6;
            }
            else {
                n2 = 6;
            }
            this.titleDrawString(graphics, s, n2, n);
        }
    }
    
    private Frame findParentFrame() {
        for (Container parent = this; parent != null; parent = parent.getParent()) {
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
        }
        return null;
    }
    
    private void displayDialog(final String s, final String s2) {
        new TouchupWin(s, s2);
    }
    
    public void zoomSetDistOrigin() {
        final int n = this.zoomSize / this.zoomAreaFactor;
        this.zoomDistOn = true;
        this.zoomMouseXStartDist = this.pixelXZoomStart + (this.zoomMouseX - this.zoomX) / this.zoomAreaFactor;
        this.zoomMouseYStartDist = this.pixelYZoomStart - this.descAdj + (this.zoomMouseY - this.zoomY) / this.zoomAreaFactor;
        this.repaintNow();
    }
    
    public int getHorizontal() {
        return this.horizValue;
    }
    
    public void setHorizontal(final int horizValue) {
        this.paintRequiresErase = true;
        this.horizValue = horizValue;
        if (this.currButtonID == 19) {
            this.setGraphValue(horizValue);
        }
        this.recomputeImage();
    }
    
    public int getRedChannel() {
        return this.colorFilter.getRedChannel();
    }
    
    public int getBlueChannel() {
        return this.colorFilter.getBlueChannel();
    }
    
    public void zoomSetDistOff() {
        this.zoomDistOn = false;
        this.repaintNow();
    }
    
    public float getZoom() {
        return this.zoomValue;
    }
    
    public void setZoom(final float n) {
        this.paintRequiresErase = true;
        this.zoomValue = n;
        if (this.currButtonID == 18) {
            this.setGraphValue(n);
        }
        this.recomputeImage();
    }
    
    public void redrawButtonArea() {
        this.repaint(0, this.descAdj, 100, 35);
    }
    
    public String getDescription() {
        String s = this.imageValue;
        if (this.imageTitle != null && this.imageTitle.length() > 0) {
            s = this.imageTitle;
        }
        return s;
    }
    
    public void setPlatformGammaCompensation(final float n) {
        this.colorFilter.setPlatformGamma(n);
        this.recomputeImage();
    }
    
    public float getPlatformGammaCompensation() {
        return (float)this.colorFilter.getplatformGamma();
    }
    
    public void userButton() {
        if (!this.executeUserScript(this.userBtnJScript)) {
            this.userBtnJScript = "";
        }
    }
    
    public void zoomAreaReset() {
        this.zoomSize = 160;
        this.zoomSizeHalf = this.zoomSize / 2;
        this.zoomAreaFactor = 2;
        this.recreateZoomArea();
        this.zoomStatusBar();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    protected static void deepClean(final Container container) {
        for (int i = 0; i < container.getComponentCount(); ++i) {
            final Component component = container.getComponent(i);
            if (component instanceof Container) {
                deepClean((Container)component);
            }
        }
        container.removeAll();
    }
    
    public boolean infoPresent() {
        return this.pictureInfoText.length() > 0 || this.exifString.length() > 0;
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void setPictureInfo(final String s) {
        if (s.length() > 0) {
            this.pictureInfoText = new String(s);
            return;
        }
        this.pictureInfoText = "";
    }
    
    public void showPictureInfo() {
        if (this.infoPresent()) {
            final String phrase = this.getPhrase(45);
            final String s = new String(this.pictureInfoText);
            s.trim().toLowerCase();
            final int length = s.length();
            final boolean startsWith = s.startsWith("http://");
            if (s.length() <= 4 || (s.lastIndexOf(".htm") <= length - 6 && s.lastIndexOf(".txt") <= length - 5 && !startsWith)) {
                if (this.exifString.length() > 0) {
                    this.displayDialog(phrase, this.exifString + this.pictureInfoText);
                    return;
                }
                this.displayDialog(phrase, this.pictureInfoText);
            }
            else {
                if (this.exifString.length() > 0) {
                    this.displayDialog(phrase, this.exifString);
                }
                String string;
                if (startsWith) {
                    string = s;
                }
                else {
                    string = this.getCodeBase() + s;
                }
                try {
                    this.getAppletContext().showDocument(new URL(string), "_blank");
                }
                catch (Exception ex) {
                    this.touchupError("showPictureInfo, showDocument failure.", ex.getMessage());
                }
            }
        }
    }
    
    private float getParamZeroDefault(final String s) {
        return this.getParamDefault(s, 0.0f);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "String", "URL of image to be displayed" } };
    }
    
    public int getNoiseLevel() {
        return this.colorFilter.getNoiseLevel();
    }
    
    public void setNoiseLevel(final int noiseLevel) {
        this.colorFilter.setNoiseLevel(noiseLevel);
        if (this.currButtonID == 8) {
            this.setGraphValue(noiseLevel);
        }
        this.recomputeImage();
    }
    
    public void zoomAreaShrink() {
        if (this.zoomSize > 60) {
            this.zoomSize -= 10;
            this.zoomSizeHalf -= 5;
            this.recreateZoomArea();
        }
    }
    
    private int channelToIndex(final int n) {
        int n2 = 1;
        switch (n) {
            case 1: {
                n2 = 0;
                break;
            }
            case -1: {
                n2 = 1;
                break;
            }
            case 0: {
                n2 = 2;
                break;
            }
        }
        return n2;
    }
    
    private Image loadImageWithProgress(final String s) throws Exception {
        final int n = ++this.mainImageLoadCount;
        this.imageLoadErrString = "";
        Image image;
        try {
            this.mainImageBytesTotal = -1;
            this.mainImageBytesRead = -1;
            final URL fileToURL = this.fileToURL(s);
            this.imageLastModified = "";
            final URLConnection openConnection = fileToURL.openConnection();
            final long lastModified = openConnection.getLastModified();
            if (lastModified != 0L) {
                this.imageLastModified = this.getPhrase(52) + " " + new Date(lastModified).toString();
            }
            this.mainImageBytesTotal = openConnection.getContentLength();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            this.mainImageBytesRead = 0;
            this.repaintNow();
            int n2 = 100000;
            int n3 = 0;
            int n4 = 1000;
            final int n5 = 4000;
            int i = 1;
            byte[] array = new byte[n2];
            this.mainImageStartLoad = new Date().getTime();
            while (i > 0) {
                if (n3 + n4 > n2) {
                    final byte[] array2 = array;
                    final int n6 = 3 * n2 / 2;
                    array = new byte[n6];
                    System.arraycopy(array2, 0, array, 0, n3);
                    n2 = n6;
                }
                i = bufferedInputStream.read(array, n3, n4);
                if (n4 < n5) {
                    n4 *= 2;
                }
                if (n != this.mainImageLoadCount) {
                    return null;
                }
                if (i <= 0) {
                    continue;
                }
                n3 += i;
                this.mainImageBytesRead += i;
                this.repaintNow();
            }
            this.exifString = "";
            final exifInfo exifInfo = new exifInfo(array, n3);
            this.exifString = new String(exifInfo.pullExifInfo());
            this.exifOrientation = exifInfo.getExifOrientation();
            this.applyExifOrientation();
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (Exception ex) {
            System.out.println("loadImageWithProgress: " + ex.getMessage());
            this.imageLoadErrString = new String(ex.getMessage());
            throw ex;
        }
        return image;
    }
    
    public void zoomStatusBar() {
        this.setAppletStatus(this.getPhrase(43) + ": " + this.zoomAreaFactor + "  " + this.getPhrase(44));
    }
    
    private URL fileToURL(String substring) throws Exception {
        if (!this.isApplet) {
            return new URL(substring);
        }
        String s = "";
        if (this.isApplet) {
            s = this.getCodeBase().toString();
        }
        URL url;
        if (substring.startsWith("http://")) {
            url = new URL(substring);
        }
        else {
            if (s.length() > 0) {
                s = s.substring(0, s.length() - 1);
            }
            final URL url2 = new URL(s + "/" + substring);
            while (substring.startsWith("../")) {
                substring = substring.substring(3);
                final int lastIndex = s.lastIndexOf("/");
                if (lastIndex != -1) {
                    s = s.substring(0, lastIndex);
                }
            }
            if (s.length() == 0) {
                url = new URL(substring);
            }
            else {
                url = new URL(s + "/" + substring);
            }
        }
        return url;
    }
    
    public float getPosterize() {
        return this.colorFilter.getPosterSteps();
    }
    
    public void setPosterize(final int posterSteps) {
        this.colorFilter.setPosterSteps(posterSteps);
        if (this.currButtonID == 6) {
            this.setGraphValue(posterSteps);
        }
        this.recomputeImage();
    }
    
    public int zoomSize(final int n) {
        return (int)(n / this.zoomValue);
    }
    
    private void setGraphValue(float graphValue) {
        graphValue = Math.min(graphValue, this.graphRightValue);
        graphValue = Math.max(graphValue, this.graphLeftValue);
        this.graphValue = graphValue;
        final int n = this.graphBarRight - this.graphBarLeft;
        int n2;
        if (graphValue >= this.graphMidValue) {
            n2 = (int)(n / 2 * (graphValue - this.graphMidValue) / (this.graphRightValue - this.graphMidValue));
        }
        else {
            n2 = (int)(n / 2 * (graphValue - this.graphLeftValue) / (this.graphMidValue - this.graphLeftValue)) - n / 2;
        }
        this.graphIndicatorX = this.graphRect.width / 2 + n2;
    }
    
    private float rowColToScale(final int n) {
        float tuningAmount = 1.0f;
        if (n == 2) {
            tuningAmount = this.tuningAmount;
        }
        else if (n == 0) {
            tuningAmount = 1.0f / this.tuningAmount;
        }
        return tuningAmount;
    }
}
