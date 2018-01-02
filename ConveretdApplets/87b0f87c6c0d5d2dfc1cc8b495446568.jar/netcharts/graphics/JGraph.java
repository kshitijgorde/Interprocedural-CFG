// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import javax.swing.border.Border;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Date;
import netcharts.util.NFVersion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import netcharts.util.NFTokenInput;
import netcharts.util.NFParse;
import netcharts.util.NFParamDef;
import netcharts.util.NFCdf;
import java.net.URL;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.Observable;
import netcharts.util.NFDebug;
import java.util.Enumeration;
import netcharts.util.NFUtil;
import netcharts.util.NFGlobal;
import java.awt.Component;
import netcharts.util.NFEventAdapter;
import java.awt.Color;
import netcharts.util.NFContext;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import netcharts.util.NFColor;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import netcharts.util.NFLoadParams;
import netcharts.util.NFParam;
import java.applet.Applet;
import java.util.Observer;
import javax.swing.JPanel;

public class JGraph extends JPanel implements Observer, NFDwellObserver
{
    protected Applet a;
    protected NFLegend legend;
    protected NFParam param;
    protected NFLoadParams loadParams;
    protected boolean graphChanged;
    protected boolean legendChanged;
    protected boolean incrementalUpdate;
    protected NFActiveRegion dwell;
    protected static final int DWELL_OFFSET_DEFAULT = 20;
    protected int dwellOffset;
    protected boolean layoutChanged;
    protected boolean dwellOn;
    protected boolean zoomEnabled;
    protected NFRegion background;
    protected NFActiveLabel backgroundActiveLabel;
    protected NFPatternFill backgroundPatternFill;
    protected double SCALE_DEFAULT;
    protected double scale;
    protected boolean autoScale;
    protected boolean antiAliasing;
    public static final int NOZOOM = 0;
    public static final int ZOOMIN = 1;
    public static final int ZOOMOUT = 2;
    public static final int MOUSE_META_DOWN_DEFAULT = 2;
    public int mouseMetaDown;
    public static final int MOUSE_SHIFT_DOWN_DEFAULT = 1;
    public int mouseShiftDown;
    public static final int MOUSE_CONTROL_DOWN_DEFAULT = 2;
    public int mouseControlDown;
    public int zoomAction;
    public int dwellAction;
    public static final String METADATA_DESCRIPTION_KEYWORD = "Description";
    private final boolean DEBUG = false;
    private boolean initialized;
    private boolean doBanner;
    private int licenseType;
    private Image offscreen;
    private Dimension offscreensize;
    private Graphics og;
    private NFLabel header;
    private NFLabel footer;
    private NFLabel leftTitle;
    private NFLabel rightTitle;
    private NFLabel dwellLabel;
    private JPanel bannerButton;
    protected static final int DEFAULT_CHART_ELEMENT_SPACING = -1;
    protected int chartElementSpacing;
    protected static final int GAP = 10;
    private Rectangle rect;
    protected boolean stopped;
    protected NFColor colorTable;
    private String errMessage;
    protected Vector notesets;
    public static final int PLOT_AREA_X = 0;
    public static final int PLOT_AREA_Y = 1;
    public static final int PLOT_AREA_WIDTH = 2;
    public static final int PLOT_AREA_HEIGHT = 3;
    protected double[] plotAreaRect;
    protected static final boolean ACTIVELABELS_ENABLED_DEFAULT = true;
    private boolean rubberOn;
    private int rubberAX;
    private int rubberAY;
    private int rubberX;
    private int rubberY;
    private int rubberW;
    private int rubberH;
    private boolean legendDrag;
    private boolean noteDrag;
    protected Vector observers;
    public boolean useDoubleBuffer;
    private Dimension baseSize;
    private boolean initBaseSize;
    public static boolean loadLogicalFont;
    protected Object paintLock;
    protected Properties runtimeProperties;
    protected int groupSize;
    protected char groupSymbol;
    protected char decSymbol;
    protected Hashtable chartMetaData;
    protected boolean dwellStarted;
    protected NFContext context;
    private Rectangle graphRect;
    private Rectangle backRect;
    private Vector dbErrors;
    private boolean firstError;
    private double oldScale;
    private Color XORColor;
    
    public JGraph() {
        this.legend = null;
        this.param = null;
        this.loadParams = null;
        this.graphChanged = false;
        this.legendChanged = false;
        this.incrementalUpdate = false;
        this.dwell = null;
        this.dwellOffset = 20;
        this.layoutChanged = false;
        this.dwellOn = false;
        this.zoomEnabled = false;
        this.background = null;
        this.backgroundActiveLabel = null;
        this.backgroundPatternFill = null;
        this.SCALE_DEFAULT = 0.0;
        this.scale = this.SCALE_DEFAULT;
        this.autoScale = false;
        this.antiAliasing = false;
        this.mouseMetaDown = 2;
        this.mouseShiftDown = 1;
        this.mouseControlDown = 2;
        this.zoomAction = 501;
        this.dwellAction = 501;
        this.initialized = false;
        this.doBanner = true;
        this.licenseType = 0;
        this.offscreen = null;
        this.offscreensize = new Dimension();
        this.og = null;
        this.header = null;
        this.footer = null;
        this.leftTitle = null;
        this.rightTitle = null;
        this.dwellLabel = null;
        this.bannerButton = null;
        this.chartElementSpacing = -1;
        this.rect = new Rectangle();
        this.stopped = true;
        this.colorTable = new NFColor();
        this.errMessage = null;
        this.notesets = new Vector();
        this.plotAreaRect = new double[] { -1.0, -1.0, -1.0, -1.0 };
        this.rubberOn = false;
        this.legendDrag = false;
        this.noteDrag = false;
        this.observers = new Vector();
        this.useDoubleBuffer = true;
        this.baseSize = new Dimension(400, 250);
        this.initBaseSize = true;
        this.paintLock = new Object();
        this.runtimeProperties = new Properties();
        this.groupSize = 3;
        this.groupSymbol = ',';
        this.decSymbol = '.';
        this.chartMetaData = null;
        this.dwellStarted = false;
        this.context = null;
        this.graphRect = new Rectangle();
        this.backRect = new Rectangle();
        this.dbErrors = new Vector();
        this.firstError = true;
        this.oldScale = 0.0;
        this.XORColor = new Color(200, 200, 200);
    }
    
    public void setContext(final NFContext nfContext) {
        this.context = nfContext;
        if (this.loadParams != null) {
            this.loadParams.setContext(nfContext);
        }
    }
    
    public NFContext getContext() {
        return this.context;
    }
    
    public void initGraph(final Applet applet) {
        this.setApplet(applet);
        final NFEventAdapter nfEventAdapter = new NFEventAdapter(this);
        this.createDwell(null);
        this.dwellOn = true;
        this.startDwell();
        this.dwellChanged();
    }
    
    public void setApplet(final Applet a) {
        this.a = a;
    }
    
    public Applet getApplet() {
        return this.a;
    }
    
    protected void createDwell(NFLabel nfLabel) {
        if (this.dwell == null) {
            this.dwell = new NFActiveRegion(250L, this);
        }
        if (nfLabel == null) {
            nfLabel = new NFLabel();
            nfLabel.setLabel("OFF");
        }
        this.dwell.setLabel(nfLabel, this);
        this.createBackgroundActiveLabel();
    }
    
    private void startDwell() {
        if (this.dwell != null && !this.dwellStarted) {
            this.dwell.start();
            this.dwellStarted = true;
        }
    }
    
    private void stopDwell() {
        if (this.dwell != null && this.dwellStarted) {
            this.dwell.stop();
            this.dwellStarted = false;
        }
    }
    
    public void print(final Graphics graphics) {
        Dimension size = this.size();
        if (NFGlobal.getVendor() == 4 && NFUtil.getBoolean(this.getRuntimeProperties().getProperty("NetscapePrintFix"), false)) {
            size = new Dimension((int)NFUtil.rint(size.width * 0.75), (int)NFUtil.rint(size.height * 0.75));
        }
        this.print(graphics, size);
    }
    
    public void print(final Graphics graphics, final Dimension dimension) {
        this.print(graphics, true, dimension);
        if (NFContext.getUserAgentType() == 0) {
            this.print(graphics, false, dimension);
        }
    }
    
    public void print(final Graphics graphics, final boolean b, final Dimension dimension) {
        final boolean useDoubleBuffer = this.useDoubleBuffer;
        if (b) {
            this.setUseDoubleBuffer(false);
        }
        this.update(graphics, new Rectangle(dimension), true);
        if (b) {
            this.setUseDoubleBuffer(useDoubleBuffer);
        }
    }
    
    private void drawEval(final Graphics graphics, final Rectangle rectangle) {
        if (NFLicense2.isLicenseEvaluation() && NFLicense2.getShowBanner()) {
            NFUtil.showEval(graphics, rectangle);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void setHeader(final NFLabel header) {
        if (this.header != null) {
            final NFActiveLabel activeLabel = this.header.getActiveLabel();
            if (header != null) {
                header.setActiveLabel(activeLabel);
                header.setRegion(this.header.getRegion());
            }
            else if (activeLabel != null && this.dwell != null) {
                this.dwell.removeLabel(activeLabel);
            }
        }
        if ((this.header = header) != null) {
            this.addTitleActiveLabelParamInfo(header, "Header");
        }
    }
    
    public void setFooter(final NFLabel footer) {
        if (this.footer != null) {
            final NFActiveLabel activeLabel = this.footer.getActiveLabel();
            if (footer != null) {
                footer.setActiveLabel(activeLabel);
                footer.setRegion(this.footer.getRegion());
            }
            else if (activeLabel != null && this.dwell != null) {
                this.dwell.removeLabel(activeLabel);
            }
        }
        if ((this.footer = footer) != null) {
            this.addTitleActiveLabelParamInfo(footer, "Footer");
        }
    }
    
    public void setLeftTitle(final NFLabel leftTitle) {
        if (this.leftTitle != null) {
            final NFActiveLabel activeLabel = this.leftTitle.getActiveLabel();
            if (leftTitle != null) {
                leftTitle.setActiveLabel(activeLabel);
                leftTitle.setRegion(this.leftTitle.getRegion());
            }
            else if (activeLabel != null && this.dwell != null) {
                this.dwell.removeLabel(activeLabel);
            }
        }
        if ((this.leftTitle = leftTitle) != null) {
            this.addTitleActiveLabelParamInfo(leftTitle, "LeftTitle");
        }
    }
    
    public void setRightTitle(final NFLabel rightTitle) {
        if (this.rightTitle != null) {
            final NFActiveLabel activeLabel = this.rightTitle.getActiveLabel();
            if (rightTitle != null) {
                rightTitle.setActiveLabel(activeLabel);
                rightTitle.setRegion(this.rightTitle.getRegion());
            }
            else if (activeLabel != null && this.dwell != null) {
                this.dwell.removeLabel(activeLabel);
            }
        }
        if ((this.rightTitle = rightTitle) != null) {
            this.addTitleActiveLabelParamInfo(rightTitle, "RightTitle");
        }
    }
    
    public void setBackground(final NFRegion background) {
        this.background = background;
    }
    
    public void setScale(final double n) {
        this.scale = n;
        if (this.background != null) {
            this.background.setScale(n);
        }
        if (this.header != null) {
            this.header.setScale(n);
        }
        if (this.footer != null) {
            this.footer.setScale(n);
        }
        if (this.leftTitle != null) {
            this.leftTitle.setScale(n);
        }
        if (this.rightTitle != null) {
            this.rightTitle.setScale(n);
        }
        if (this.legend != null) {
            this.legend.setScale(n);
        }
        if (this.dwellLabel != null) {
            this.dwellLabel.setScale(n);
        }
        if (this.notesets != null) {
            NFNoteSet.setScale(this.notesets, n);
        }
        this.layoutChanged = true;
    }
    
    private void setScale(final int n, final int n2) {
        final double scale = n / this.baseSize.width;
        final double scale2 = n2 / this.baseSize.height;
        if (scale < scale2) {
            this.setScale(scale);
        }
        else {
            this.setScale(scale2);
        }
    }
    
    public void setUseDoubleBuffer(final boolean useDoubleBuffer) {
        if (!(this.useDoubleBuffer = useDoubleBuffer)) {
            if (this.offscreen != null) {
                this.offscreen.flush();
                this.offscreen = null;
            }
            if (this.og != null) {
                this.og.dispose();
                this.og = null;
            }
        }
    }
    
    public boolean getUseDoubleBuffer() {
        return this.useDoubleBuffer;
    }
    
    public NFActiveRegion getActiveRegion() {
        return this.dwell;
    }
    
    public Color defaultColor(final int n) {
        return this.colorTable.getDefaultColor(n);
    }
    
    public int getNoteSetIndex(final NFActiveLabel nfActiveLabel) {
        return NFNoteSet.getNoteSetIndex(this.notesets, nfActiveLabel);
    }
    
    public NFActiveLabel getNoteSetActiveLabel(final int n) {
        return NFNoteSet.getNoteSetActiveLabel(this.notesets, n);
    }
    
    public int getLegendIndex(final NFActiveLabel nfActiveLabel) {
        if (this.legend == null) {
            return -1;
        }
        return this.legend.getLegendIndex(nfActiveLabel);
    }
    
    public int getLegendItemCount() {
        if (this.legend == null) {
            return -1;
        }
        return this.legend.getItemCount();
    }
    
    public NFActiveLabel getLegendActiveLabel(final int n) {
        if (this.legend == null) {
            return null;
        }
        return this.legend.getLegendActiveLabel(n);
    }
    
    public Vector getMetaData(final String s) {
        if (this.chartMetaData == null || s == null) {
            return null;
        }
        return this.chartMetaData.get(s.toLowerCase());
    }
    
    public Enumeration getMetaDataKeys() {
        if (this.chartMetaData == null) {
            return null;
        }
        return this.chartMetaData.keys();
    }
    
    public void putMetaData(final String s, final Vector vector) {
        if (s == null) {
            return;
        }
        if (this.chartMetaData == null) {
            this.chartMetaData = new Hashtable();
        }
        this.chartMetaData.put(s.toLowerCase(), vector);
    }
    
    public void clearMetaData() {
        if (this.chartMetaData != null) {
            this.chartMetaData.clear();
        }
    }
    
    protected void dwellChanged() {
    }
    
    private void debug(final String s) {
        NFDebug.print(512L, "JGraph: " + s);
    }
    
    public void update(final Observable observable, final Object o) {
        final String s = (String)o;
        if (s.equals("LoadParams")) {
            try {
                this.loadParams();
                this.display();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        if (s.equals("UserInput")) {
            this.repaint();
        }
    }
    
    public boolean dwellDisplay(final boolean b, final NFActiveLabel nfActiveLabel) {
        if (b) {
            NFDebug.print(16L, "JGraph: DwellOn  <" + nfActiveLabel.label + "> param=" + nfActiveLabel.selectedItemParam + " index=" + nfActiveLabel.selectedItemIndex);
        }
        else {
            NFDebug.print(16L, "JGraph: DwellOff  <" + nfActiveLabel.label + "> param=" + nfActiveLabel.selectedItemParam + " index=" + nfActiveLabel.selectedItemIndex);
            this.display();
        }
        return false;
    }
    
    public boolean dwellPress(final Event event, final int n, final int n2, final NFActiveLabel nfActiveLabel) {
        if (nfActiveLabel.url != null && nfActiveLabel.target != null && nfActiveLabel.target.equals("LOADPARAMS")) {
            NFDebug.print(16L, "JGraph: Dwell LoadParams from <" + nfActiveLabel.url + ">");
            try {
                this.loadParams(nfActiveLabel.url);
                this.loadParams("Update;");
            }
            catch (Exception ex) {
                NFDebug.print("JGraph: ***************************");
                NFDebug.print("JGraph: LOADPARAMS Error");
                String s = ex.getMessage();
                final int index = s.indexOf("NFParamURL: ");
                if (index > -1) {
                    s = s.substring(index + 12);
                }
                NFDebug.print("JGraph: " + s);
                NFDebug.print("JGraph: ***************************");
            }
            finally {
                return true;
            }
        }
        NFDebug.print(16L, "JGraph: DwellPress at " + n + "," + n2);
        return false;
    }
    
    protected void updateGraph() {
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle rectangle) {
    }
    
    protected void drawGraphLite(final Graphics graphics) {
    }
    
    public Image drawImage(final int width, final int height) {
        final Rectangle rectangle = new Rectangle(this.size());
        if (width > 0) {
            rectangle.width = width;
        }
        if (height > 0) {
            rectangle.height = height;
        }
        final Image image = this.createImage(rectangle.width, rectangle.height);
        if (image == null) {
            NFDebug.print("JGraph.drawImage: createImage() returned NULL");
        }
        final Graphics graphics = image.getGraphics();
        if (graphics == null) {
            image.flush();
            NFDebug.print("JGraph.drawImage: getGraphics() returned NULL");
        }
        this.update(graphics, rectangle);
        graphics.dispose();
        return image;
    }
    
    public void updateData() {
        if (this.loadParams == null) {
            return;
        }
        this.loadParams.updateData();
        if (this.loadParams.loadDataNeeded()) {
            this.display(true);
        }
    }
    
    public void update(final Graphics graphics) {
        this.update(graphics, false);
    }
    
    public void update(final Graphics graphics, final boolean b) {
        this.update(graphics, new Rectangle(this.size()), b);
    }
    
    public void update(final Graphics graphics, final Rectangle rectangle) {
        this.update(graphics, rectangle, false);
    }
    
    public void update(final Graphics og, final Rectangle rectangle, final boolean b) {
        final Insets insets = this.getInsets();
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        rectangle.x += insets.left;
        rectangle.y += insets.top;
        rectangle.width -= insets.left + insets.right;
        rectangle.height -= insets.top + insets.bottom;
        if (this.stopped) {
            this.updateGraph();
            return;
        }
        synchronized (this.paintLock) {
            if (NFUtil.getJDKVersion() >= 1.2) {
                NFOffscreenImage.setAntiAliasing(og, this.antiAliasing);
            }
            if (this.doBanner) {
                this.drawBanner(og, rectangle);
                this.drawInsets(og, rectangle2);
                return;
            }
            if (this.errMessage != null) {
                this.showError(og, rectangle);
                this.drawInsets(og, rectangle2);
                return;
            }
            if (this.loadParams != null && this.loadParams.loadDataNeeded()) {
                try {
                    if (this.loadParams.loadData(this.dbErrors)) {
                        return;
                    }
                    this.displayError(this.dbErrors);
                    this.loadParams();
                    this.layoutChanged = true;
                }
                catch (Exception ex) {
                    this.errMessage = getErrorMsg(ex, false);
                }
                if (this.errMessage != null) {
                    this.showError(og, rectangle);
                    this.drawInsets(og, rectangle2);
                    return;
                }
            }
            if (NFDebug.enabled(512L)) {
                NFDebug.timerReset();
            }
            if (this.layoutChanged || (this.legendChanged && this.legend != null && this.legend.enabled()) || !this.useDoubleBuffer || this.offscreen == null || rectangle2.width != this.offscreensize.width || rectangle2.height != this.offscreensize.height) {
                if (this.autoScale) {
                    this.setScale(rectangle.width, rectangle.height);
                }
                if (rectangle.width < 1 || rectangle.height < 1) {
                    this.updateGraph();
                    this.drawInsets(og, rectangle2);
                    this.fireGraphTooSmall(new Dimension(rectangle.width, rectangle.height));
                    return;
                }
                if (this.offscreen != null && (rectangle2.width != this.offscreensize.width || rectangle2.height != this.offscreensize.height)) {
                    this.offscreen.flush();
                    this.offscreen = null;
                    if (this.og != null) {
                        this.og.dispose();
                        this.og = null;
                    }
                }
                if (this.useDoubleBuffer && this.offscreen == null) {
                    this.offscreen = this.createImage(rectangle2.width, rectangle2.height);
                    this.offscreensize = new Dimension(rectangle2.width, rectangle2.height);
                    if (this.offscreen != null) {
                        this.og = this.offscreen.getGraphics();
                    }
                }
                if (this.og == null) {
                    this.og = og;
                    if (this.offscreen != null) {
                        this.offscreen.flush();
                        this.offscreen = null;
                    }
                }
                if (NFUtil.getJDKVersion() >= 1.2) {
                    NFOffscreenImage.setRenderingHints(og, this.og);
                }
                this.og.setColor(this.a.getBackground());
                this.og.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                this.rect.x = rectangle.x;
                this.rect.y = rectangle.y;
                this.rect.width = rectangle.width;
                this.rect.height = rectangle.height;
                if (this.dwellLabel != null) {
                    this.dwellLabel.setParentBounds(this.rect);
                }
                int chartElementSpacing;
                if (this.chartElementSpacing == -1) {
                    if (rectangle.width < rectangle.height) {
                        chartElementSpacing = rectangle.width / 50;
                    }
                    else {
                        chartElementSpacing = rectangle.height / 50;
                    }
                    if (chartElementSpacing < 2) {
                        chartElementSpacing = 2;
                    }
                }
                else {
                    chartElementSpacing = this.chartElementSpacing;
                }
                final Rectangle plotArea = this.getPlotArea(this.rect);
                Rectangle buildLayout = null;
                if (plotArea != null) {
                    buildLayout = this.buildLayout(this.a, this.og, this.rect, plotArea, chartElementSpacing, this.background, this.header, this.footer, this.leftTitle, this.rightTitle, this.legend);
                    final Rectangle graphRect = this.graphRect;
                    final Rectangle rect = this.rect;
                    final int x = plotArea.x;
                    rect.x = x;
                    graphRect.x = x;
                    final Rectangle graphRect2 = this.graphRect;
                    final Rectangle rect2 = this.rect;
                    final int y = plotArea.y;
                    rect2.y = y;
                    graphRect2.y = y;
                    final Rectangle graphRect3 = this.graphRect;
                    final Rectangle rect3 = this.rect;
                    final int width = plotArea.width;
                    rect3.width = width;
                    graphRect3.width = width;
                    final Rectangle graphRect4 = this.graphRect;
                    final Rectangle rect4 = this.rect;
                    final int height = plotArea.height;
                    rect4.height = height;
                    graphRect4.height = height;
                }
                else {
                    this.layoutGraph(this.a, this.og, this.rect, chartElementSpacing, this.background, this.header, this.footer, this.leftTitle, this.rightTitle);
                    this.graphRect.x = this.rect.x;
                    this.graphRect.y = this.rect.y;
                    this.graphRect.width = this.rect.width;
                    this.graphRect.height = this.rect.height;
                    if (this.legend != null && this.legend.enabled() && this.legend.isOutside()) {
                        this.legend.adjustRect(this.og, this.graphRect, chartElementSpacing);
                    }
                }
                this.layoutChanged = true;
                this.drawGraph(this.og, this.graphRect);
                if (this.legend != null && this.legend.enabled() && this.legend.isOutside()) {
                    this.legend.draw(this, this.og, (buildLayout != null) ? buildLayout : this.rect, chartElementSpacing, this.colorTable);
                }
                this.layoutChanged = false;
                this.legendChanged = false;
                this.graphChanged = false;
            }
            else if (this.graphChanged) {
                this.graphRect.x = this.rect.x;
                this.graphRect.y = this.rect.y;
                this.graphRect.width = this.rect.width;
                this.graphRect.height = this.rect.height;
                final Graphics create = this.og.create();
                create.clipRect(this.graphRect.x, this.graphRect.y, this.graphRect.width, this.graphRect.height);
                this.drawBackground(this.a, create, this.background);
                create.dispose();
                this.layoutChanged = false;
                this.drawGraph(this.og, this.graphRect);
                this.graphChanged = false;
            }
            else if (this.incrementalUpdate) {
                this.drawGraphLite(this.og);
                this.incrementalUpdate = false;
            }
            this.drawInsets(this.og, rectangle2);
            if (b) {
                this.drawEval(this.og, rectangle);
                this.layoutChanged = true;
            }
            if (this.offscreen == null) {
                this.og = null;
            }
            else if (this.notifyGraphOffscreenObservers(this.offscreen, og, true)) {
                og.drawImage(this.offscreen, 0, 0, null);
                this.notifyGraphOffscreenObservers(this.offscreen, og, false);
            }
            this.notifyGraphObservers(og);
        }
        if (NFDebug.enabled(512L)) {
            NFDebug.timerSplit("JGraph: Draw Graph");
        }
    }
    
    private void displayError(final Vector vector) {
        for (int size = vector.size(), i = 0; i < size; ++i) {
            this.displayError(getErrorMsg(vector.elementAt(i), false));
        }
        vector.removeAllElements();
    }
    
    private void displayError(final String s) {
        NFDebug.print(s);
    }
    
    public static String getErrorMsg(final Exception ex) {
        return getErrorMsg(ex, true);
    }
    
    public static String getErrorMsg(final Exception ex, final boolean b) {
        String s = ex.getMessage();
        if (s != null) {
            final int index = s.indexOf(58);
            if (b && index > 0 && index + 2 < s.length()) {
                s = s.substring(index + 2);
            }
        }
        else {
            s = ex.toString();
            ex.printStackTrace();
        }
        return s;
    }
    
    protected void display() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
            graphics.dispose();
        }
    }
    
    protected void display(final Graphics graphics) {
        this.update(graphics);
    }
    
    protected void display(final boolean graphChanged) {
        this.graphChanged = graphChanged;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics);
            graphics.dispose();
        }
        this.graphChanged = false;
    }
    
    protected void reset() {
    }
    
    public void clean() {
        if (this.loadParams != null) {
            this.loadParams.clean();
        }
        try {
            this.loadParams();
        }
        catch (Exception ex) {}
        if (this.legend != null) {
            this.legend.clearDataSets();
            this.legend = null;
        }
        if (this.dwell != null) {
            this.dwell.removeAllLabels();
        }
        if (this.runtimeProperties != null) {
            this.runtimeProperties.clear();
        }
        else {
            this.runtimeProperties = new Properties();
        }
    }
    
    public final void loadParams(final Applet applet) throws Exception {
        this.defineParams();
        try {
            this.loadParams.loadParams(applet);
            this.loadParams();
        }
        catch (Exception ex) {
            this.errMessage = getErrorMsg(ex);
            this.checkLicense();
        }
    }
    
    private void checkLicense() {
        if (this.initialized) {
            return;
        }
        try {
            this.licenseType = NFLicense2.check(this.a, (Vector)this.param.get("LicenseKeys"), (String)this.param.get("LicenseURL"));
            this.doBanner = (this.licenseType != 2);
            if (this.doBanner && this.licenseType == 1 && !NFLicense2.getShowBanner()) {
                this.doBanner = false;
            }
            if (this.doBanner) {
                this.addBannerButton();
            }
        }
        catch (Exception ex) {}
        this.initialized = true;
    }
    
    public final void loadParams(final URL url) throws Exception {
        try {
            this.defineParams();
            this.loadParams.loadParams(url);
        }
        catch (Exception ex) {
            this.checkLicense();
            throw ex;
        }
    }
    
    public final void loadParams(final String s) throws Exception {
        try {
            this.defineParams();
            this.loadParams.loadParams(s);
        }
        catch (Exception ex) {
            this.checkLicense();
            throw ex;
        }
    }
    
    public final void loadParams(final StringBuffer sb) throws Exception {
        try {
            this.defineParams();
            this.loadParams.loadParams(sb);
        }
        catch (Exception ex) {
            this.checkLicense();
            throw ex;
        }
    }
    
    public final void loadParams(final String s, final Object o) throws Exception {
        try {
            this.defineParams();
            this.loadParams.loadData(s, o);
        }
        catch (Exception ex) {
            this.checkLicense();
            throw ex;
        }
    }
    
    public final void loadParams(final String s, final int n, final String s2) throws Exception {
        this.defineParams();
        this.loadParams.loadParams(s, n, s2, this);
    }
    
    public final void loadParams(final NFCdf nfCdf, final Vector vector) throws Exception {
        this.defineParams();
        nfCdf.loadParams(this.loadParams, vector);
    }
    
    public Properties getRuntimeProperties() {
        return this.runtimeProperties;
    }
    
    public void setRuntimeProperties(final Properties runtimeProperties) {
        this.runtimeProperties = runtimeProperties;
    }
    
    protected synchronized void defineParams() {
        if (this.param != null) {
            return;
        }
        (this.loadParams = new NFLoadParams(this, this.a)).setContext(this.context);
        this.loadParams.loadRuntimeProperties(this.getRuntimeProperties());
        (this.param = this.loadParams.getParam()).addObserver(this);
        final NFParam param = this.param;
        param.defineDebugParams("DebugSet");
        param.defineDebugParams("DebugClear");
        param.defineLicenseParams();
        param.defineJdbcParams();
        param.defineString("ChartName", "NoNameChart");
        param.defineChartType("ChartType", -1);
        param.defineNumber("ScaleFactor", new Integer(0));
        param.defineNumber("DwellOffset", null);
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(param.defineNumber("ChartWidth", new Integer(400)));
        vector.addElement(param.defineNumber("ChartHeight", new Integer(250)));
        param.defineTuple("ChartSize", vector);
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(param.defineString("NumberDecimalSymbol", "."));
        vector2.addElement(param.defineString("NumberGroupSymbol", ","));
        vector2.addElement(param.defineNumber("NumberGroupSize", new Integer(3)));
        param.defineTuple("NumberFormat", vector2);
        param.defineString("FontEncoding", null);
        param.defineRegion("Background");
        param.defineActiveLabel("BackgroundActiveLabel");
        this.param.defineTuple("BackgroundFillPattern", NFPatternFill.getPatternFillParamTuple(param, "BackgroundFillPattern"));
        param.defineLabel("Header", true, true);
        param.defineRegion("HeaderBox");
        param.defineLabel("Footer", true, true);
        param.defineRegion("FooterBox");
        param.defineLabel("LeftTitle", true, true);
        param.defineRegion("LeftTitleBox");
        param.defineLabel("RightTitle", true, true);
        param.defineRegion("RightTitleBox");
        param.defineNumber("ActiveClicks", new Integer(1));
        param.defineLabel("DwellLabel");
        param.defineRegion("DwellBox");
        param.defineRegion("DwellLabelBox");
        param.defineActiveLabel("FooterActiveLabel");
        param.defineActiveLabel("HeaderActiveLabel");
        param.defineActiveLabel("RightTitleActiveLabel");
        param.defineActiveLabel("LeftTitleActiveLabel");
        NFLegend.defineParams(param, "Legend");
        param.defineVector("ColorTable", param.defineColor("DefaultColor"));
        param.defineNoteSets();
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(param.defineString("MetaData-name", null));
        param.defineVector("MetaData", param.defineTuple("MetaData-tuple", vector3, true));
        param.defineString("AntiAlias", "OFF");
        this.loadParams.getParse().setDateFormats(NFParse.parseStringVector(NFUtil.getString(this.getRuntimeProperties().getProperty("DateFormats"), null), true));
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("ZOOMIN", new Integer(1));
        hashtable.put("ZOOMOUT", new Integer(2));
        hashtable.put("NOZOOM", new Integer(0));
        this.param.defineSymbol("MouseMetaDown", hashtable, new Integer(2));
        this.param.defineSymbol("MouseShiftDown", hashtable, new Integer(1));
        this.param.defineSymbol("MouseControlDown", hashtable, new Integer(2));
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(this.param.defineNumber("PlotAreaX", null));
        vector4.addElement(this.param.defineNumber("PlotAreaY", null));
        vector4.addElement(this.param.defineNumber("PlotAreaWidth", null));
        vector4.addElement(this.param.defineNumber("PlotAreaHeight", null));
        this.param.defineTuple("PlotArea", vector4);
        this.param.defineNumber("ChartElementSpacing", null);
    }
    
    protected void loadParams() throws Exception {
        this.checkLicense();
        if (this.initBaseSize) {
            if (this.a != null) {
                this.baseSize = this.a.size();
            }
            this.initBaseSize = false;
        }
        if (this.param.changed("ChartType")) {
            NFUtil.getNumber(this.param.get("ChartType"), -1);
        }
        if (this.param.changed("ChartName")) {
            final String s = (String)this.param.get("ChartName");
        }
        if (this.param.changed("ScaleFactor")) {
            final double number = NFUtil.getNumber(this.param.get("ScaleFactor"), this.SCALE_DEFAULT);
            this.autoScale = (Double.isNaN(number) || number < 0.0);
            this.setScale(number);
            final Number n = (Number)this.param.get("ChartWidth");
            if (n != null) {
                this.baseSize.width = n.intValue();
            }
            final Number n2 = (Number)this.param.get("ChartHeight");
            if (n2 != null) {
                this.baseSize.height = n2.intValue();
            }
        }
        if (this.param.changed("DwellOffset")) {
            this.graphChanged = true;
            this.dwellOffset = NFUtil.getNumber(this.param.get("DwellOffset"), 20);
        }
        if (this.param.changed("ChartSize")) {
            final Vector vector = (Vector)this.param.get("ChartSize");
            if (vector != null && vector.size() > 0) {
                final int intValue = vector.elementAt(0).intValue();
                final int intValue2 = vector.elementAt(1).intValue();
                if (this.a != null) {
                    this.a.resize(intValue, intValue2);
                }
                this.baseSize.width = intValue;
                this.baseSize.height = intValue2;
            }
        }
        if (this.param.changed("DwellLabel")) {
            if (this.dwellOn && !this.stopped && this.dwell != null) {
                this.stopDwell();
            }
            this.createDwell(this.dwellLabel = NFLabel.loadParams(this.param, this.param.get("DwellLabel")));
            if (this.dwellLabel != null) {
                this.dwellOn = true;
                this.dwell.setDocument(this.a, NFUtil.getNumber(this.param.get("ActiveClicks"), 1));
            }
            if (this.dwellOn && !this.stopped && this.dwell != null) {
                this.startDwell();
            }
            this.dwellChanged();
            if (this.legend != null) {
                this.legend.setDwell(this.dwell);
            }
            if (this.notesets != null) {
                NFNoteSet.setDwell(this.notesets, this.dwell);
            }
            this.graphChanged = true;
        }
        if (this.dwellLabel != null && this.param.changed("DwellBox")) {
            this.loadDwellBox(this.param, "DwellBox");
        }
        if (this.dwellLabel != null && this.param.changed("DwellLabelBox")) {
            this.loadDwellBox(this.param, "DwellLabelBox");
        }
        if (this.param.changed("Background")) {
            this.layoutChanged = true;
            this.setBackground(NFRegion.loadParams(this.param, this.param.get("Background")));
        }
        if (this.param.changed("BackgroundActiveLabel")) {
            this.loadBackgroundActiveLabel("BackgroundActiveLabel");
        }
        if (this.param.changed("BackgroundFillPattern")) {
            this.layoutChanged = true;
            this.backgroundPatternFill = NFPatternFill.loadPatternFill((Vector)this.param.get("BackgroundFillPattern"));
        }
        if (this.param.changed("Header")) {
            this.layoutChanged = true;
            this.setHeader(NFLabel.loadParams(this.param, this.param.get("Header"), 0, true, true));
        }
        if (this.header != null && this.param.changed("HeaderBox")) {
            this.layoutChanged = true;
            this.header.setRegion(NFRegion.loadParams(this.param, this.param.get("HeaderBox")));
        }
        if (this.param.changed("Footer")) {
            this.layoutChanged = true;
            this.setFooter(NFLabel.loadParams(this.param, this.param.get("Footer"), 0, true, true));
        }
        if (this.footer != null && this.param.changed("FooterBox")) {
            this.layoutChanged = true;
            this.footer.setRegion(NFRegion.loadParams(this.param, this.param.get("FooterBox")));
        }
        if (this.param.changed("LeftTitle")) {
            this.layoutChanged = true;
            this.setLeftTitle(NFLabel.loadParams(this.param, this.param.get("LeftTitle"), 0, true, true));
        }
        if (this.leftTitle != null && this.param.changed("LeftTitleBox")) {
            this.layoutChanged = true;
            this.leftTitle.setRegion(NFRegion.loadParams(this.param, this.param.get("LeftTitleBox")));
        }
        if (this.param.changed("RightTitle")) {
            this.layoutChanged = true;
            this.setRightTitle(NFLabel.loadParams(this.param, this.param.get("RightTitle"), 0, true, true));
        }
        if (this.rightTitle != null && this.param.changed("RightTitleBox")) {
            this.layoutChanged = true;
            this.rightTitle.setRegion(NFRegion.loadParams(this.param, this.param.get("RightTitleBox")));
        }
        if (this.footer != null && this.param.changed("FooterActiveLabel")) {
            this.setActiveLabel(this.footer, "FooterActiveLabel");
            this.addTitleActiveLabelParamInfo(this.footer, "Footer");
        }
        if (this.header != null && this.param.changed("HeaderActiveLabel")) {
            this.setActiveLabel(this.header, "HeaderActiveLabel");
            this.addTitleActiveLabelParamInfo(this.header, "Header");
        }
        if (this.rightTitle != null && this.param.changed("RightTitleActiveLabel")) {
            this.setActiveLabel(this.rightTitle, "RightTitleActiveLabel");
            this.addTitleActiveLabelParamInfo(this.rightTitle, "RightTitle");
        }
        if (this.leftTitle != null && this.param.changed("LeftTitleActiveLabel")) {
            this.setActiveLabel(this.leftTitle, "LeftTitleActiveLabel");
            this.addTitleActiveLabelParamInfo(this.leftTitle, "LeftTitle");
        }
        if (this.legend == null && this.param.changed("Legend")) {
            this.legend = new NFLegend(this.dwell);
            if (this.legend != null) {
                for (int i = 0; i < this.observers.size(); ++i) {
                    final Object element = this.observers.elementAt(i);
                    if (element instanceof NFDragObserver) {
                        this.legend.addObserver(element);
                    }
                }
            }
        }
        if (this.legend != null && this.legend.loadParams(this.param, "Legend")) {
            this.layoutChanged = true;
        }
        if (this.param.changed("ColorTable")) {
            this.layoutChanged = true;
            this.colorTable.setDefaultTable((Vector)this.param.get("ColorTable"));
        }
        if (this.param.changed("ActiveClicks") && this.dwell != null) {
            this.dwell.setClickCount(NFUtil.getNumber(this.param.get("ActiveClicks"), 1));
        }
        if (this.param.changed("NumberFormat")) {
            this.layoutChanged = true;
            this.loadNumberFormat((Vector)this.param.get("NumberFormat"));
        }
        if (this.param.changed("FontEncoding")) {
            NFTokenInput.setFontEncoding((String)this.param.get("FontEncoding"));
        }
        if ((this.param.changed("NoteSets") || this.notesets.size() > 0) && NFNoteSet.loadAllParams(this.param, this.notesets, this.dwell)) {
            this.layoutChanged = true;
            if (this.notesets.size() > 0) {
                NFNoteSet.setAllMapComponent(this, this.notesets);
                for (int j = 0; j < this.observers.size(); ++j) {
                    final Object element2 = this.observers.elementAt(j);
                    if (element2 instanceof NFDragObserver) {
                        NFNoteSet.addObserver(element2, this.notesets);
                    }
                }
            }
        }
        if (this.param.changed("MetaData")) {
            this.clearMetaData();
            final Vector vector2 = (Vector)this.param.get("MetaData");
            if (vector2 != null) {
                final Enumeration<Vector> elements = vector2.elements();
                while (elements.hasMoreElements()) {
                    final Vector vector3 = (Vector)elements.nextElement().clone();
                    final String s2 = vector3.elementAt(0);
                    if (this.chartMetaData == null) {
                        this.chartMetaData = new Hashtable();
                    }
                    vector3.removeElementAt(0);
                    this.putMetaData(s2, vector3);
                }
            }
        }
        if (this.param.changed("AntiAlias")) {
            this.layoutChanged = true;
            final String s3 = (String)this.param.get("AntiAlias");
            this.antiAliasing = (s3 != null && s3.equalsIgnoreCase("ON"));
            if (this.dwell != null) {
                this.dwell.setAntiAliasing(this.antiAliasing);
            }
        }
        if (this.param.changed("MouseMetaDown")) {
            this.mouseMetaDown = NFUtil.getNumber(this.param.get("MouseMetaDown"), 2);
        }
        if (this.param.changed("MouseShiftDown")) {
            this.mouseShiftDown = NFUtil.getNumber(this.param.get("MouseShiftDown"), 1);
        }
        if (this.param.changed("MouseControlDown")) {
            this.mouseControlDown = NFUtil.getNumber(this.param.get("MouseControlDown"), 2);
        }
        if (this.param.changed("ChartElementSpacing")) {
            this.layoutChanged = true;
            this.chartElementSpacing = NFUtil.getNumber(this.param.get("ChartElementSpacing"), -1);
        }
        if (this.param.changed("PlotArea")) {
            this.layoutChanged = true;
            this.loadPlotAreaParams();
        }
    }
    
    private void loadDwellBox(final NFParam nfParam, final String s) throws Exception {
        final NFRegion loadParams = NFRegion.loadParams(nfParam, nfParam.get(s));
        if (loadParams != null) {
            this.dwellLabel.setRegion(loadParams);
        }
    }
    
    private void layoutGraph(final Applet applet, final Graphics graphics, final Rectangle rectangle, final int n, final NFRegion nfRegion, final NFLabel nfLabel, final NFLabel nfLabel2, final NFLabel nfLabel3, final NFLabel nfLabel4) {
        this.layoutGraph(applet, graphics, rectangle, rectangle, n, nfRegion, nfLabel, nfLabel2, nfLabel3, nfLabel4);
    }
    
    private void layoutGraph(final Applet applet, final Graphics graphics, final Rectangle rectangle, final Rectangle rectangle2, final int n, final NFRegion nfRegion, final NFLabel nfLabel, final NFLabel nfLabel2, final NFLabel nfLabel3, final NFLabel nfLabel4) {
        final Rectangle rectangle3 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final NFRegionBorder drawBackground = this.drawBackground(applet, graphics, rectangle2, nfRegion);
        final Rectangle rectangle4 = rectangle3;
        rectangle4.x += drawBackground.left;
        final Rectangle rectangle5 = rectangle3;
        rectangle5.y += drawBackground.top;
        final Rectangle rectangle6 = rectangle3;
        rectangle6.width -= drawBackground.left + drawBackground.right;
        final Rectangle rectangle7 = rectangle3;
        rectangle7.height -= drawBackground.top + drawBackground.bottom;
        final Rectangle rectangle8 = rectangle3;
        rectangle8.x += n;
        final Rectangle rectangle9 = rectangle3;
        rectangle9.y += n;
        final Rectangle rectangle10 = rectangle3;
        rectangle10.width -= 2 * n;
        final Rectangle rectangle11 = rectangle3;
        rectangle11.height -= 2 * n;
        final NFRegionBorder drawLabels = this.drawLabels(graphics, rectangle3, n, nfLabel, nfLabel2, nfLabel3, nfLabel4);
        final Rectangle rectangle12 = rectangle3;
        rectangle12.x += drawLabels.left;
        final Rectangle rectangle13 = rectangle3;
        rectangle13.y += drawLabels.top;
        final Rectangle rectangle14 = rectangle3;
        rectangle14.width -= drawLabels.left + drawLabels.right;
        final Rectangle rectangle15 = rectangle3;
        rectangle15.height -= drawLabels.top + drawLabels.bottom;
        rectangle.x = rectangle3.x;
        rectangle.y = rectangle3.y;
        rectangle.width = rectangle3.width;
        rectangle.height = rectangle3.height;
    }
    
    private Rectangle buildLayout(final Applet applet, final Graphics graphics, final Rectangle rectangle, final Rectangle rectangle2, final int n, final NFRegion nfRegion, final NFLabel nfLabel, final NFLabel nfLabel2, final NFLabel nfLabel3, final NFLabel nfLabel4, final NFLegend nfLegend) {
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        final NFRegionBorder nfRegionBorder2 = new NFRegionBorder();
        if (nfRegion != null) {
            nfRegion.getBorder(nfRegionBorder2);
        }
        final NFRegionBorder nfRegionBorder3 = nfRegionBorder;
        nfRegionBorder3.left += nfRegionBorder2.left + n;
        final NFRegionBorder nfRegionBorder4 = nfRegionBorder;
        nfRegionBorder4.top += nfRegionBorder2.top + n;
        final NFRegionBorder nfRegionBorder5 = nfRegionBorder;
        nfRegionBorder5.right += nfRegionBorder2.right + n;
        final NFRegionBorder nfRegionBorder6 = nfRegionBorder;
        nfRegionBorder6.bottom += nfRegionBorder2.bottom + n;
        final NFRegionBorder drawLabels = this.drawLabels(graphics, rectangle2, n, nfLabel, nfLabel2, nfLabel3, nfLabel4, false);
        final NFRegionBorder nfRegionBorder7 = nfRegionBorder;
        nfRegionBorder7.left += drawLabels.left;
        final NFRegionBorder nfRegionBorder8 = nfRegionBorder;
        nfRegionBorder8.top += drawLabels.top;
        final NFRegionBorder nfRegionBorder9 = nfRegionBorder;
        nfRegionBorder9.right += drawLabels.right;
        final NFRegionBorder nfRegionBorder10 = nfRegionBorder;
        nfRegionBorder10.bottom += drawLabels.bottom;
        final NFRegionBorder nfRegionBorder11 = new NFRegionBorder();
        Rectangle rectangle3 = null;
        if (nfLegend != null && nfLegend.enabled() && nfLegend.isOutside()) {
            final Rectangle rectangle4 = new Rectangle(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            nfLegend.adjustRect(graphics, rectangle4, n);
            nfRegionBorder11.left = rectangle2.x - rectangle4.x;
            nfRegionBorder11.top = rectangle2.y - rectangle4.y;
            nfRegionBorder11.right = rectangle2.width - rectangle4.width - nfRegionBorder11.left;
            nfRegionBorder11.bottom = rectangle2.height - rectangle4.height - nfRegionBorder11.top;
            final Rectangle rectangle5;
            rectangle3 = (rectangle5 = new Rectangle(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height));
            rectangle5.x -= nfRegionBorder11.left;
            final Rectangle rectangle6 = rectangle3;
            rectangle6.x += nfRegionBorder11.right;
            final Rectangle rectangle7 = rectangle3;
            rectangle7.y -= nfRegionBorder11.top;
            final Rectangle rectangle8 = rectangle3;
            rectangle8.y += nfRegionBorder11.bottom;
        }
        final NFRegionBorder nfRegionBorder12 = nfRegionBorder;
        nfRegionBorder12.left += nfRegionBorder11.left;
        final NFRegionBorder nfRegionBorder13 = nfRegionBorder;
        nfRegionBorder13.top += nfRegionBorder11.top;
        final NFRegionBorder nfRegionBorder14 = nfRegionBorder;
        nfRegionBorder14.right += nfRegionBorder11.right;
        final NFRegionBorder nfRegionBorder15 = nfRegionBorder;
        nfRegionBorder15.bottom += nfRegionBorder11.bottom;
        final Rectangle rectangle9 = new Rectangle(rectangle2.x - nfRegionBorder.left, rectangle2.y - nfRegionBorder.top, rectangle2.width + nfRegionBorder.left + nfRegionBorder.right, rectangle2.height + nfRegionBorder.top + nfRegionBorder.bottom);
        final Rectangle rectangle10 = new Rectangle(rectangle9.x, rectangle9.y, rectangle9.width, rectangle9.height);
        rectangle10.add(rectangle);
        this.layoutGraph(applet, graphics, rectangle9, rectangle10, n, nfRegion, nfLabel, nfLabel2, nfLabel3, nfLabel4);
        return rectangle3;
    }
    
    private NFRegionBorder drawBackground(final Applet applet, final Graphics graphics, final Rectangle rectangle, final NFRegion nfRegion) {
        this.backRect.x = rectangle.x;
        this.backRect.y = rectangle.y;
        this.backRect.width = rectangle.width;
        this.backRect.height = rectangle.height;
        return this.drawBackground(applet, graphics, nfRegion);
    }
    
    private NFRegionBorder drawBackground(final Applet applet, final Graphics graphics, final NFRegion nfRegion) {
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        if (nfRegion == null) {
            final NFRegion nfRegion2 = new NFRegion();
            nfRegion2.setColor((applet.getBackground() == null) ? this.getBackground() : applet.getBackground());
            nfRegion2.setBorder(0);
            nfRegion2.draw(graphics, this.backRect.x, this.backRect.y, this.backRect.width, this.backRect.height, this.backgroundPatternFill);
        }
        else {
            final boolean b;
            if (b = (nfRegion.getColor() == null)) {
                nfRegion.setColor((applet.getBackground() == null) ? this.getBackground() : applet.getBackground());
            }
            nfRegion.draw(graphics, this.backRect.x, this.backRect.y, this.backRect.width, this.backRect.height, this.backgroundPatternFill);
            nfRegion.getBorder(nfRegionBorder);
            if (b) {
                nfRegion.setColor(null);
            }
        }
        if (this.backgroundActiveLabel != null) {
            this.backgroundActiveLabel.setBounds(this.backRect.x, this.backRect.y, this.backRect.width, this.backRect.height);
        }
        return nfRegionBorder;
    }
    
    protected Dimension getBounds(final NFLabel nfLabel, final Graphics graphics) {
        if (nfLabel == null) {
            return null;
        }
        final Dimension bounds = nfLabel.getBounds(graphics);
        if (bounds.width <= 0 && bounds.height <= 0) {
            return null;
        }
        return bounds;
    }
    
    protected NFRegionBorder drawLabels(final Graphics graphics, final Rectangle rectangle, final int n, final NFLabel nfLabel, final NFLabel nfLabel2, final NFLabel nfLabel3, final NFLabel nfLabel4) {
        return this.drawLabels(graphics, rectangle, n, nfLabel, nfLabel2, nfLabel3, nfLabel4, true);
    }
    
    protected NFRegionBorder drawLabels(final Graphics graphics, final Rectangle rectangle, final int n, final NFLabel nfLabel, final NFLabel nfLabel2, final NFLabel nfLabel3, final NFLabel nfLabel4, final boolean b) {
        final NFLabel[] array = { nfLabel, nfLabel2, nfLabel3, nfLabel4 };
        final int[] array2 = { 2, 3, 1, 0 };
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        for (int i = 0; i < array.length; ++i) {
            final Dimension bounds = this.getBounds(array[i], graphics);
            if (bounds != null) {
                Rectangle rectangle2 = null;
                switch (array2[i]) {
                    case 2: {
                        rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, bounds.height);
                        nfRegionBorder.top = bounds.height + n;
                        break;
                    }
                    case 3: {
                        rectangle2 = new Rectangle(rectangle.x, rectangle.y + rectangle.height - bounds.height, rectangle.width, bounds.height);
                        nfRegionBorder.bottom = bounds.height + n;
                        break;
                    }
                    case 1: {
                        rectangle2 = new Rectangle(rectangle.x, rectangle.y + nfRegionBorder.top, bounds.width, rectangle.height - nfRegionBorder.top - nfRegionBorder.bottom);
                        nfRegionBorder.left = bounds.width + n;
                        break;
                    }
                    case 0: {
                        rectangle2 = new Rectangle(rectangle.x + rectangle.width - bounds.width, rectangle.y + nfRegionBorder.top, bounds.width, rectangle.height - nfRegionBorder.top - nfRegionBorder.bottom);
                        nfRegionBorder.right = bounds.width + n;
                        break;
                    }
                }
                if (b) {
                    array[i].externallyJustify(rectangle2, bounds);
                    array[i].draw(this, graphics);
                }
            }
        }
        return nfRegionBorder;
    }
    
    protected void addBannerButton() {
        if (this.bannerButton != null) {
            return;
        }
        (this.bannerButton = new JPanel()).setLayout(new FlowLayout(1));
        if (this.licenseType == 1) {
            final JButton button = new JButton(" Press Here To Continue ");
            button.addActionListener(new ActionListener() {
                private final /* synthetic */ Component a = this;
                
                public void actionPerformed(final ActionEvent actionEvent) {
                    this.a.handleEvent(NFEventAdapter.convertActionEvent(this.a, actionEvent));
                }
            });
            this.bannerButton.add(button);
        }
        this.bannerButton.setBackground(Color.white);
        this.bannerButton.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.add("South", this.bannerButton);
    }
    
    private void drawBanner(final Graphics graphics, final Rectangle rectangle) {
        String string = "(unknown)";
        final Date expirationDate = NFLicense2.getExpirationDate();
        if (expirationDate != null) {
            string = " " + (expirationDate.getMonth() + 1) + "/" + expirationDate.getDate() + "/" + (expirationDate.getYear() + 1900);
        }
        final String[] array = { "VERSION " + NFVersion.version, "", "This is an unlicensed version for Evaluation Use Only.", "Your evaluation period will expire on " + string, "To order a licensed copy and eliminate this message, ", "please visit our web site at http://www.visualmining.com", "or call our sales department at (800) 308-0731." };
        final String[] array2 = { "VERSION " + NFVersion.version, "", "The evaluation period for this product has expired.", "To order a licensed copy, ", "please visit our web site at http://www.visualmining.com", "or call our sales department at (800) 308-0731." };
        String[] array3;
        if (this.licenseType == 1) {
            array3 = array;
        }
        else {
            array3 = array2;
        }
        if (NFVersion.patchLevel.length() > 0 && !NFVersion.patchLevel.equals("0")) {
            final StringBuffer sb = new StringBuffer();
            final String[] array4 = array3;
            final int n = 0;
            array4[n] = sb.append(array4[n]).append(".").append(NFVersion.patchLevel).toString();
        }
        final Font font = NFUtil.getFont("TimesRoman", 1, rectangle.width / 10);
        final Font font2 = NFUtil.getFont("TimesRoman", 0, rectangle.width / 28);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth("NET");
        final int stringWidth2 = fontMetrics.stringWidth("CHARTS");
        int n2 = rectangle.y + fontMetrics.getHeight();
        final int n3 = rectangle.x + (rectangle.width - (stringWidth + stringWidth2)) / 2;
        graphics.setColor(Color.white);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(NFColor.get("xE00000"));
        graphics.drawString("NET", n3, n2);
        final int n4 = n3 + stringWidth;
        graphics.setColor(Color.black);
        graphics.drawString("CHARTS", n4, n2);
        graphics.setFont(font2);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final int height = fontMetrics2.getHeight();
        for (int i = 0; i < array3.length; ++i) {
            n2 += height;
            graphics.drawString(array3[i], rectangle.x + (rectangle.width - fontMetrics2.stringWidth(array3[i])) / 2, n2);
        }
        if (this.bannerButton != null && this.bannerButton.isVisible()) {
            try {
                this.paintChildren(graphics);
            }
            catch (Exception ex) {}
        }
    }
    
    private void showError(final Graphics graphics, final Rectangle rectangle) {
        if (this.firstError) {
            NFParam.printError("ERROR: " + this.errMessage);
            this.firstError = false;
        }
        final Font font = NFUtil.getFont("TimesRoman", 1, rectangle.width / 30);
        graphics.setColor(Color.red.darker());
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        String s = this.errMessage;
        String substring = "";
        final int lastIndex = s.lastIndexOf(": ");
        if (lastIndex > 0 && lastIndex + 2 < s.length()) {
            substring = s.substring(lastIndex + 2);
            s = s.substring(0, lastIndex + 1);
        }
        final String[] array = { "*** PARAMETER ERROR ***", "", s, "", substring };
        graphics.setColor(Color.white);
        int n = rectangle.y + (rectangle.height - array.length * height) / 2;
        if (n < rectangle.y + height) {
            n = rectangle.y + height;
        }
        for (int i = 0; i < array.length; ++i) {
            graphics.drawString(array[i], rectangle.x + (rectangle.width - fontMetrics.stringWidth(array[i])) / 2, n);
            n += height;
        }
    }
    
    public final boolean action(final Event event, final Object o) {
        if (this.doBanner && this.licenseType == 1) {
            this.doBanner = false;
            this.remove(this.bannerButton);
            this.bannerButton = null;
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, 0, this.size().width, this.size().height);
                graphics.dispose();
            }
            this.display();
            return true;
        }
        return true;
    }
    
    public void stop(final Applet applet) {
        if (this.stopped) {
            return;
        }
        if (this.dwell != null && this.dwellOn) {
            this.stopDwell();
        }
        if (this.loadParams != null) {
            this.loadParams.stop();
        }
        this.stopped = true;
    }
    
    public void start(final Applet applet) {
        if (!this.stopped) {
            return;
        }
        if (this.dwell != null && this.dwellOn) {
            this.startDwell();
        }
        if (this.loadParams != null) {
            this.loadParams.start();
        }
        this.stopped = false;
    }
    
    public void addDwellObserver(final NFDwellObserver nfDwellObserver) {
        if (this.dwell != null) {
            this.addObserver(nfDwellObserver);
        }
    }
    
    public void removeDwellObserver(final NFDwellObserver nfDwellObserver) {
        if (this.dwell != null) {
            this.dwell.removeObserver(nfDwellObserver);
        }
    }
    
    public void addObserver(final Object o) {
        if (o == null) {
            return;
        }
        if (!this.observers.contains(o)) {
            this.debug("add observer " + o);
            this.observers.addElement(o);
        }
        if (o instanceof NFDwellObserver && this.dwell != null) {
            this.dwell.addObserver((NFDwellObserver)o);
        }
        if (o instanceof NFDragObserver) {
            if (this.legend != null) {
                this.legend.addObserver(o);
            }
            if (this.notesets != null) {
                NFNoteSet.addObserver(o, this.notesets);
            }
        }
    }
    
    public void removeObserver(final Object o) {
        if (o == null) {
            return;
        }
        if (this.observers.contains(o)) {
            this.debug("remove observer " + o);
            this.observers.removeElement(o);
        }
        if (o instanceof NFDwellObserver && this.dwell != null) {
            this.dwell.removeObserver((NFDwellObserver)o);
        }
        if (o instanceof NFDragObserver) {
            if (this.legend != null) {
                this.legend.removeObserver(o);
            }
            if (this.notesets != null) {
                NFNoteSet.removeObserver(o, this.notesets);
            }
        }
    }
    
    public void addGraphObserver(final NFGraphObserver nfGraphObserver) {
        this.addObserver(nfGraphObserver);
    }
    
    public void removeGraphObserver(final NFGraphObserver nfGraphObserver) {
        this.removeObserver(nfGraphObserver);
    }
    
    protected void notifyGraphObservers(final Graphics graphics) {
        for (int size = this.observers.size(), i = 0; i < size; ++i) {
            final Object element = this.observers.elementAt(i);
            if (element instanceof NFGraphObserver) {
                ((NFGraphObserver)element).graphDrawn(graphics);
            }
        }
    }
    
    protected boolean notifyGraphOffscreenObservers(final Image image, final Graphics graphics, final boolean b) {
        final int size = this.observers.size();
        boolean b2 = true;
        for (int i = 0; i < size; ++i) {
            final Object element = this.observers.elementAt(i);
            if (element instanceof NFGraphOffscreenObserver) {
                final NFGraphOffscreenObserver nfGraphOffscreenObserver = (NFGraphOffscreenObserver)element;
                if (b) {
                    b2 = (b2 && nfGraphOffscreenObserver.preOffscreenDraw(image, graphics));
                }
                else {
                    nfGraphOffscreenObserver.postOffscreenDraw(image, graphics);
                }
            }
        }
        return b2;
    }
    
    public boolean abortPreDrag(final double n, final double n2, final double n3, final double n4) {
        if (this.observers == null) {
            return false;
        }
        final Enumeration<NFDragObserver> elements = this.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            if (!nextElement.preDrag(this, n, n2, n3, n4)) {
                return true;
            }
        }
        return false;
    }
    
    public void notifyPostDrag(final double n, final double n2) {
        if (this.observers == null) {
            return;
        }
        final Enumeration<NFDragObserver> elements = this.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            nextElement.postDrag(this, n, n2);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.doBanner && this.dwell != null && this.dwellOn) {
            this.dwell.mousePos(n, n2);
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.doBanner && this.dwell != null && this.dwellOn) {
            this.dwell.mousePos(-1000, -1000);
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int rubberAX, final int rubberAY) {
        if (this.doBanner) {
            return true;
        }
        if ((event.shiftDown() || event.controlDown() || event.metaDown()) && this.zoom(event)) {
            return false;
        }
        if (this.legend != null && this.legend.enabled() && this.legend.isInside() && this.legend.mouseDown(event, rubberAX, rubberAY)) {
            return this.legendDrag = true;
        }
        if (this.notesets.size() > 0 && NFNoteSet.mouseDown(event, rubberAX, rubberAY, this.notesets)) {
            return this.noteDrag = true;
        }
        this.dwellEvent(event, rubberAX, rubberAY, event.id == this.dwellAction);
        if (this.zoomEnabled && this.graphRect.inside(rubberAX, rubberAY)) {
            this.rubberBox(this.rubberOn = true, this.rubberAX = rubberAX, this.rubberAY = rubberAY);
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.legendDrag) {
            if (this.legend.mouseDrag(event, n, n2)) {
                this.graphChanged = true;
                this.repaint();
            }
            else {
                this.legendDrag = false;
            }
            return false;
        }
        if (this.noteDrag) {
            if (NFNoteSet.mouseDrag(event, n, n2, this.notesets)) {
                this.layoutChanged = true;
                this.repaint();
            }
            else {
                this.noteDrag = false;
            }
            return false;
        }
        if (!this.rubberOn) {
            return this.mouseMove(event, n, n2);
        }
        this.rubberBox(false, 0, 0);
        int min = n;
        int min2 = n2;
        final Rectangle zoomRect = this.getZoomRect();
        if (zoomRect == null) {
            return this.mouseMove(event, n, n2);
        }
        if (!zoomRect.inside(min, min2)) {
            min = Math.min(Math.max(min, zoomRect.x), zoomRect.x + zoomRect.width);
            min2 = Math.min(Math.max(min2, zoomRect.y), zoomRect.y + zoomRect.height);
        }
        this.rubberBox(true, min, min2);
        return false;
    }
    
    protected Rectangle getZoomRect() {
        return this.graphRect;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if ((event.shiftDown() || event.controlDown() || event.metaDown()) && this.zoom(event)) {
            return false;
        }
        if (this.legendDrag) {
            this.legendDrag = false;
            if (this.legend.mouseUp(event, n, n2)) {
                this.graphChanged = true;
                this.repaint();
                return false;
            }
            this.dwellEvent(event, n, n2, true);
            return false;
        }
        else if (this.noteDrag) {
            this.noteDrag = false;
            if (NFNoteSet.mouseUp(event, n, n2, this.notesets)) {
                this.layoutChanged = true;
                this.repaint();
                return false;
            }
            this.dwellEvent(event, n, n2, true);
            return false;
        }
        else {
            if (this.rubberOn) {
                this.rubberBox(false, 0, 0);
                this.rubberOn = false;
                if (this.rubberW > 5 && this.rubberH > 5 && this.graphRect.inside(n, n2)) {
                    this.zoom(this.rubberX, this.rubberY, this.rubberW, this.rubberH);
                }
                return false;
            }
            return this.dwellEvent(event, n, n2, event.id == this.dwellAction) && false;
        }
    }
    
    private boolean dwellMatch(final int n, final int n2) {
        return this.dwell != null && this.dwellOn && this.dwell.findMatch(n, n2) != null;
    }
    
    private boolean dwellEvent(final Event event, final int n, final int n2, final boolean b) {
        return this.dwell != null && this.dwellOn && this.dwell.mouseDown(event, n, n2, b);
    }
    
    private void toggleScale() {
        if (this.autoScale) {
            this.autoScale = false;
            this.setScale(0.0);
        }
        else {
            this.autoScale = true;
            this.setScale(-1.0);
        }
        this.display(this.layoutChanged = true);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 83) {
            this.toggleScale();
            return false;
        }
        switch (n) {
            case 1004:
            case 1005:
            case 1006:
            case 1007: {
                this.scroll(n);
                break;
            }
        }
        if (!this.zoomEnabled) {
            return false;
        }
        switch (n) {
            case 1003: {
                this.zoomIn();
                break;
            }
            case 1002: {
                this.zoomOut();
                break;
            }
            case 1000:
            case 1001: {
                this.zoomHome();
                break;
            }
            case 27: {
                if (this.rubberOn) {
                    this.rubberBox(false, 0, 0);
                    this.rubberOn = false;
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    private void rubberBox(final boolean b, int rubberAX, int rubberAY) {
        if (b) {
            if (rubberAX < this.rubberAX) {
                this.rubberX = rubberAX;
                rubberAX = this.rubberAX;
            }
            else {
                this.rubberX = this.rubberAX;
            }
            if (rubberAY < this.rubberAY) {
                this.rubberY = rubberAY;
                rubberAY = this.rubberAY;
            }
            else {
                this.rubberY = this.rubberAY;
            }
            this.rubberW = rubberAX - this.rubberX;
            this.rubberH = rubberAY - this.rubberY;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        synchronized (graphics) {
            graphics.setColor(Color.black);
            graphics.setXORMode(this.XORColor);
            graphics.drawRect(this.rubberX, this.rubberY, this.rubberW, this.rubberH);
            graphics.setPaintMode();
        }
        graphics.dispose();
    }
    
    public boolean isZoomable() {
        return this.zoomEnabled;
    }
    
    private boolean zoom(final Event event) {
        if (!this.zoomEnabled || this.zoomAction != event.id) {
            return false;
        }
        if (event.shiftDown()) {
            return this.zoom(this.mouseShiftDown);
        }
        if (event.controlDown()) {
            return this.zoom(this.mouseControlDown);
        }
        return event.metaDown() && this.zoom(this.mouseMetaDown);
    }
    
    private boolean zoom(final int n) {
        if (!this.zoomEnabled) {
            return false;
        }
        switch (n) {
            case 1: {
                this.zoomIn();
                return true;
            }
            case 2: {
                this.zoomOut();
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected void zoom(final int n, final int n2, final int n3, final int n4) {
    }
    
    public void zoomIn() {
    }
    
    public void zoomOut() {
    }
    
    public void zoomHome() {
    }
    
    public void scroll(final int n) {
    }
    
    public NFLoadParams getLoadParams() {
        this.defineParams();
        return this.loadParams;
    }
    
    public NFParam getParam() {
        this.defineParams();
        return this.param;
    }
    
    public NFParse getParse() {
        this.defineParams();
        return this.loadParams.getParse();
    }
    
    public int getNoteSetIndex(final NFNoteSet set) {
        for (int i = 0; i < this.notesets.size(); ++i) {
            if (this.notesets.elementAt(i) == set) {
                return i;
            }
        }
        return -1;
    }
    
    public Vector stringsToURLs(final Vector vector) {
        if (vector == null) {
            return null;
        }
        final Vector<Object> vector2 = new Vector<Object>();
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            Object o = null;
            try {
                o = new URL(this.a.getDocumentBase(), s);
            }
            catch (Exception ex) {
                NFDebug.print("NetCharts: Invalid URL: " + s + " " + ex);
            }
            finally {
                vector2.addElement(o);
            }
        }
        return vector2;
    }
    
    public void setActiveLabel(final NFLabel nfLabel, final String s) throws Exception {
        final NFActiveLabel loadActiveLabel = this.loadActiveLabel(nfLabel.getActiveLabel(), s);
        this.layoutChanged = true;
        nfLabel.setActiveLabel(loadActiveLabel);
    }
    
    public void loadBackgroundActiveLabel(final String s) {
        Rectangle rectangle = null;
        if (this.backgroundActiveLabel != null) {
            rectangle = new Rectangle(this.backgroundActiveLabel.xmin, this.backgroundActiveLabel.ymin, this.backgroundActiveLabel.xmax - this.backgroundActiveLabel.xmin, this.backgroundActiveLabel.ymax - this.backgroundActiveLabel.ymin);
        }
        this.backgroundActiveLabel = this.loadActiveLabel(this.backgroundActiveLabel, s, 0);
        this.createBackgroundActiveLabel();
        if (rectangle != null) {
            this.backgroundActiveLabel.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    protected void createBackgroundActiveLabel() {
        if (this.backgroundActiveLabel == null) {
            this.backgroundActiveLabel = new NFActiveLabel();
            if (this.dwell != null) {
                this.dwell.addLabel(this.backgroundActiveLabel, 0);
            }
        }
        this.backgroundActiveLabel.selectedItemParam = "Background";
        this.backgroundActiveLabel.selectedItemIndex = 0;
    }
    
    public NFActiveLabel loadActiveLabel(final NFActiveLabel nfActiveLabel, final String s) {
        return this.loadActiveLabel(nfActiveLabel, s, -1);
    }
    
    public NFActiveLabel loadActiveLabel(final NFActiveLabel nfActiveLabel, final String s, final int n) {
        NFActiveLabel loadParams = null;
        try {
            final Vector vector = (Vector)this.param.get(s);
            if (vector.size() > 0) {
                loadParams = NFActiveLabel.loadParams(this.param, vector.elementAt(0));
            }
        }
        catch (Exception ex) {
            loadParams = null;
            NFDebug.print("setActiveLabel:" + ex.toString());
        }
        if (this.dwell != null) {
            if (nfActiveLabel != null) {
                this.dwell.removeLabel(nfActiveLabel);
            }
            if (loadParams == null) {
                loadParams = new NFActiveLabel();
            }
            this.dwell.addLabel(loadParams, n);
        }
        return loadParams;
    }
    
    protected void addTitleActiveLabelParamInfo(final NFLabel nfLabel, final String selectedItemParam) {
        NFActiveLabel activeLabel = nfLabel.getActiveLabel();
        if (activeLabel == null) {
            activeLabel = new NFActiveLabel();
            nfLabel.setActiveLabel(activeLabel);
            if (this.dwell != null) {
                this.dwell.addLabel(activeLabel);
            }
        }
        activeLabel.selectedItemParam = selectedItemParam;
        activeLabel.selectedItemIndex = 0;
    }
    
    public void clearActiveLabel(final NFLabel nfLabel) {
        if (nfLabel == null) {
            return;
        }
        final NFActiveLabel activeLabel = nfLabel.getActiveLabel();
        if (this.dwell != null && activeLabel != null) {
            this.dwell.removeLabel(activeLabel);
        }
    }
    
    public boolean reloadNeeded(final Date date) {
        return this.loadParams != null && this.loadParams.reloadNeeded(date);
    }
    
    public void reload(final NFCdf nfCdf, final Vector vector) throws Exception {
        if (this.loadParams != null) {
            this.loadParams.clearDependList();
            this.loadParams(nfCdf, vector);
        }
    }
    
    protected void drawInsets(final Graphics graphics, final Rectangle rectangle) {
        final Insets insets = this.getInsets();
        if (insets.top > 0 || insets.bottom > 0 || insets.left > 0 || insets.right > 0) {
            final Color color = graphics.getColor();
            if (this.background == null || this.background.getColor() == null) {
                graphics.setColor(this.a.getBackground());
            }
            else {
                graphics.setColor(this.background.getColor());
            }
            graphics.fillRect(0, 0, insets.left, rectangle.height);
            graphics.fillRect(rectangle.width - insets.right, 0, insets.right, rectangle.height);
            graphics.fillRect(0, 0, rectangle.width, insets.top);
            graphics.fillRect(0, rectangle.height - insets.bottom, rectangle.width, insets.bottom);
            graphics.setColor(color);
        }
        final Border border = this.getBorder();
        if (border != null) {
            border.paintBorder(this, graphics, 0, 0, rectangle.width, rectangle.height);
        }
    }
    
    protected void fireGraphTooSmall(final Dimension dimension) {
        synchronized (this.observers) {
            for (int i = 0; i < this.observers.size(); ++i) {
                final Object element = this.observers.elementAt(i);
                if (element instanceof NFGraphObserver) {
                    ((NFGraphObserver)element).graphTooSmall(dimension);
                }
            }
        }
    }
    
    public String getTextualDescription() {
        final String stringFromVector = getStringFromVector(this.getMetaData("Description"));
        if (stringFromVector != null) {
            return stringFromVector;
        }
        final String textualChartType = this.getTextualChartType();
        String label = null;
        if (this.header != null) {
            label = this.header.getLabel();
        }
        return "A " + textualChartType + ((label != null) ? (" entitled \"" + label + "\"") : "");
    }
    
    protected String getTextualChartType() {
        final String name = this.getClass().getName();
        String s = null;
        final String s2 = "chart";
        for (int i = 0; i < NFGlobal.graphClassNames.length; ++i) {
            if (name.equals(NFGlobal.graphClassNames[i])) {
                s = NFGlobal.classSymbol[i].toLowerCase();
                break;
            }
        }
        if (s == null) {
            s = s2;
        }
        final int index = s.indexOf(s2);
        if (index > 0) {
            s = s.substring(0, index) + " " + s.substring(index);
        }
        return s;
    }
    
    protected static String getStringFromVector(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < vector.size(); ++i) {
            if (i > 0) {
                sb.append(" ");
            }
            final Object element = vector.elementAt(i);
            if (element != null) {
                sb.append(element.toString());
            }
        }
        return sb.toString();
    }
    
    public void setGrouping(final int groupSize, final char groupSymbol) {
        this.groupSize = groupSize;
        this.groupSymbol = groupSymbol;
    }
    
    public char getGroupSymbol() {
        return this.groupSymbol;
    }
    
    public int getGroupSize() {
        return this.groupSize;
    }
    
    public void setDecimalSymbol(final char decSymbol) {
        this.decSymbol = decSymbol;
    }
    
    public char getDecimalSymbol() {
        return this.decSymbol;
    }
    
    protected void loadNumberFormat(final Vector vector) {
        String s = NFUtil.getString(vector, 0, String.valueOf('.'));
        if (s.length() <= 0) {
            s = String.valueOf('.');
        }
        String s2 = NFUtil.getString(vector, 1, String.valueOf(','));
        if (s2.length() <= 0) {
            s2 = String.valueOf(',');
        }
        final int number = NFUtil.getNumber(vector, 2, 3);
        this.setDecimalSymbol(s.charAt(0));
        this.setGrouping(number, s2.charAt(0));
    }
    
    protected void loadPlotAreaParams() throws Exception {
        final Vector vector = (Vector)this.param.get("PlotArea");
        this.plotAreaRect[0] = NFUtil.getNumber(vector, 0, -1.0);
        this.plotAreaRect[1] = NFUtil.getNumber(vector, 1, -1.0);
        this.plotAreaRect[2] = NFUtil.getNumber(vector, 2, -1.0);
        this.plotAreaRect[3] = NFUtil.getNumber(vector, 3, -1.0);
    }
    
    protected Rectangle getPlotArea(final Rectangle rectangle) {
        if (this.plotAreaRect[0] != -1.0 && this.plotAreaRect[1] != -1.0 && this.plotAreaRect[2] != -1.0 && this.plotAreaRect[3] != -1.0) {
            return new Rectangle(rectangle.x + this.convertValue(this.plotAreaRect[0], rectangle.width), rectangle.y + this.convertValue(this.plotAreaRect[1], rectangle.height), this.convertValue(this.plotAreaRect[2], rectangle.width), this.convertValue(this.plotAreaRect[3], rectangle.height));
        }
        return null;
    }
    
    protected int convertValue(double n, final int n2) {
        if (n < 0.0) {
            n = -n;
        }
        if (n < 1.0) {
            return (int)NFUtil.rint(n * n2);
        }
        return (int)NFUtil.rint(n);
    }
    
    static {
        JGraph.loadLogicalFont = false;
        if (NFUtil.getJDKVersion() >= 1.2 && !JGraph.loadLogicalFont) {
            NFOffscreenImage.getAllFonts();
            JGraph.loadLogicalFont = true;
        }
        NFGlobal.getVendor();
    }
}
