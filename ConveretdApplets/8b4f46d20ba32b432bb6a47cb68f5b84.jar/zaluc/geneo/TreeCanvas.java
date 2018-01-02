// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import zaluc.utils.EntryDlg;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.applet.AppletContext;
import java.awt.Component;
import zaluc.utils.Timer;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import zaluc.utils.Callback;
import java.awt.Panel;

class TreeCanvas extends Panel implements ImageUser, ParserUpdates, Callback, Runnable, ActionListener, MouseListener, KeyListener
{
    Geneo geneo;
    Globals globals;
    MainPanel panel;
    MainFrame frame;
    String fileNameStr;
    int totalRecordCount;
    int curRecordCount;
    int rowHeight;
    int firstRow;
    int secondRow;
    int thirdRow;
    int statusRow;
    Box3D loadBox;
    Box3D loadBar;
    int loadTextX;
    int loadTextY;
    Person personUnderMenu;
    MenuItem find;
    MenuItem zoomIn;
    MenuItem zoomOut;
    MenuItem viewAll;
    MenuItem details;
    MenuItem center;
    MenuItem help;
    boolean parsing;
    PeopleList people;
    Image offscreenImage;
    Graphics offscreenGraphics;
    Color backgroundColor;
    Color foregroundColor;
    Image bkgImage;
    Font statusFont;
    int treeFontSize;
    int canvasWidth;
    int canvasHeight;
    private static final int TASK_VIEW_ALL = 1;
    private static final int TASK_VERT_SCROLL_TIMEOUT = 2;
    private static final int TASK_HORZ_SCROLL_TIMEOUT = 3;
    private static final int TASK_NEW_HTML_TARGET = 4;
    private static final int STATE_LOADING = 0;
    private static final int STATE_NORMAL = 1;
    private int state;
    private static final int SETUP_ADD_NOTIFY = 1;
    private static final int SETUP_NOTIFY_SETUP = 2;
    private static final int SETUP_SET_BOUNDS = 4;
    private static final int SETUP_INIT_TREE = 8;
    private static final int SETUP_NEED_INIT = 7;
    private static final int SETUP_DONE = 15;
    private int setupFlags;
    private static final Cursor loadingCursor;
    private static final Cursor normalCursor;
    private static final Cursor pendingCursor;
    private String password;
    boolean okToDraw;
    boolean imageReady;
    boolean keepRefreshing;
    boolean keyboardEnabled;
    String status;
    Dimension bkgImageDim;
    private static final int SCROLL_TIMEOUT = 250;
    private Timer vertScrollTimer;
    private Timer horzScrollTimer;
    private int vertScrollPos;
    private int horzScrollPos;
    private boolean debug;
    static final int loadBoxBorder = 5;
    static final int loadBoxSpacer = 2;
    
    public TreeCanvas(final Geneo geneo, final Globals globals, final MainPanel panel) {
        this.fileNameStr = "";
        this.parsing = true;
        this.state = 1;
        this.okToDraw = false;
        this.imageReady = false;
        this.keepRefreshing = true;
        this.keyboardEnabled = true;
        this.debug = false;
        this.geneo = geneo;
        this.globals = globals;
        this.panel = panel;
        this.frame = null;
        this.addMouseListener(this);
        this.addKeyListener(this);
        if (this.globals.statusCode == 0) {
            if (this.globals.backgroundImage != null) {
                new ImageLoader(this.globals.backgroundImage, this, this);
            }
            new Thread(this, "Parser Thread").start();
        }
    }
    
    public TreeCanvas(final Geneo geneo, final Globals globals, final MainFrame frame) {
        this.fileNameStr = "";
        this.parsing = true;
        this.state = 1;
        this.okToDraw = false;
        this.imageReady = false;
        this.keepRefreshing = true;
        this.keyboardEnabled = true;
        this.debug = false;
        this.geneo = geneo;
        this.globals = globals;
        this.panel = null;
        this.frame = frame;
        this.addMouseListener(this);
        this.addKeyListener(this);
        if (this.globals.statusCode == 0) {
            if (this.globals.backgroundImage != null) {
                new ImageLoader(this.globals.backgroundImage, this, this);
            }
            new Thread(this, "Parser Thread").start();
        }
    }
    
    public void updateContext(final AppletContext appletContext) {
        this.globals.updateContext(appletContext);
    }
    
    public void setPrimary(final int n) {
        this.setCenterPerson(this.people.getPersonFromId(n));
    }
    
    public void setPrimary() {
        this.setCenterPerson(this.people.getSelectedPerson());
    }
    
    public void run() {
        this.state = 0;
        this.setCursor(TreeCanvas.loadingCursor);
        Parser.parseFile(this.globals, this);
    }
    
    public void setImage(final Image bkgImage) {
        this.bkgImageDim = new Dimension(bkgImage.getWidth(null), bkgImage.getHeight(null));
        this.bkgImage = bkgImage;
        this.redraw();
    }
    
    public synchronized void addNotify() {
        super.addNotify();
        final Graphics graphics = this.getGraphics();
        this.getBounds();
        this.statusFont = graphics.getFont();
        this.rowHeight = graphics.getFontMetrics().getHeight();
        this.firstRow = this.rowHeight;
        this.secondRow = this.rowHeight * 2;
        this.thirdRow = this.rowHeight * 3;
        this.statusRow = this.secondRow;
        this.treeFontSize = this.globals.initialZoom;
        if (this.debug) {
            System.out.println("TreeCanvas::addNotify - setting setupFlags");
        }
        this.setupFlags |= 0x1;
        if (this.setupFlags == 7) {
            if (this.debug) {
                System.out.println("TreeCanvas::addNotify - initializing tree");
            }
            this.initTree();
        }
    }
    
    public synchronized void notifyFileName(final String s) {
        this.fileNameStr = "Parsing " + s;
    }
    
    public synchronized void notifySetup(final PeopleList people, final String password, final int totalRecordCount) {
        this.people = people;
        this.password = password;
        this.totalRecordCount = totalRecordCount;
        if (this.frame != null && password != null) {
            this.frame.passwordRequired(true);
        }
        this.setupFlags |= 0x2;
        if (this.setupFlags == 7) {
            this.initTree();
        }
    }
    
    private void calcLoadBoxSizes(final Rectangle rectangle) {
        final int stringWidth = this.getFontMetrics(this.statusFont).stringWidth(this.fileNameStr);
        final int n = stringWidth + 10;
        final int n2 = 2 * this.rowHeight + 10 + 2;
        final int n3 = (rectangle.width - n) / 2;
        final int n4 = 2;
        final int n5 = n3 + 5;
        final int n6 = n4 + 5 + this.rowHeight + 2;
        this.loadTextX = n3 + 5;
        this.loadTextY = n4 + 5 + this.rowHeight - 2;
        this.loadBox = new Box3D(this.globals, n3, n4, n, n2);
        this.loadBar = new Box3D(this.globals, n5, n6, stringWidth, this.rowHeight);
    }
    
    public synchronized void updateRecordCount(final int curRecordCount) {
        this.curRecordCount = curRecordCount;
        if (this.loadBar != null) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.loadBar.fill(graphics, this.curRecordCount, this.totalRecordCount);
            }
            if (this.keepRefreshing && this.curRecordCount % 10 == 1) {
                this.keepRefreshing = (this.people.shouldDraw() && this.curRecordCount < 32);
                if (this.setupFlags == 15 && this.frame != null && this.people.getCenterPerson() != null && this.people.getCenterPerson().fullName != null) {
                    this.frame.setTitle("InterneTree: " + this.people.getCenterPerson().fullName);
                    this.people.setCenterPerson(this.people.getCenterPerson());
                    this.people.calcTree();
                    this.people.setHorz(this.people.getHorzCurPos());
                    this.people.setVert(this.people.getVertCurPos());
                    this.adjustScrollBars();
                    this.redraw();
                }
            }
        }
    }
    
    public void updateStatus(final String status) {
        if (this.status == null) {
            this.status = status;
            return;
        }
        this.status = String.valueOf(this.status) + ", " + status;
    }
    
    public synchronized void notifyDone() {
        this.parsing = false;
        this.setCursor(TreeCanvas.normalCursor);
        this.state = 1;
        if (this.globals.statusCode == 0) {
            this.setCenterPerson(this.people.getCenterPerson());
        }
        this.setupFlags |= 0x2;
        if (this.setupFlags == 7) {
            this.initTree();
        }
        if (this.setupFlags == 15) {
            this.redraw();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.offscreenImage != null) {
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    private void createOffscreenImage() {
        this.offscreenImage = this.createImage(this.canvasWidth, this.canvasHeight);
        if (this.offscreenImage == null) {
            System.out.println("TreeCanvas::createOffscreenImage - createImage failed");
            return;
        }
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        if (this.offscreenGraphics != null) {
            this.setTreeFonts();
            this.calcLoadBoxSizes(this.getBounds());
            return;
        }
        System.out.println("TreeCanvas::createOffscreenImage - getGraphics failed");
    }
    
    private void initTree() {
        if (this.setupFlags == 7) {
            this.createOffscreenImage();
            if (this.people != null) {
                this.people.setScreenSize(this.canvasHeight, this.canvasWidth);
                this.people.home();
                this.people.calcTree();
                this.people.setHorz(this.people.getHorzCurPos());
                this.people.setVert(this.people.getVertCurPos());
            }
            this.adjustScrollBars();
            this.redraw();
            this.setupFlags |= 0x8;
        }
    }
    
    private void redraw() {
        if (this.offscreenGraphics != null) {
            final Rectangle bounds = this.getBounds();
            switch (this.globals.statusCode) {
                case 0: {
                    if (this.bkgImage == null || this.globals.clearBackground) {
                        this.offscreenGraphics.setColor(this.globals.backgroundColor);
                        this.offscreenGraphics.fillRect(0, 0, bounds.width, bounds.height);
                        this.offscreenGraphics.setColor(this.globals.foregroundColor);
                    }
                    if (this.bkgImage != null) {
                        switch (this.globals.bkgImageLayout) {
                            case 1: {
                                for (int i = 0; i < bounds.height; i += this.bkgImageDim.height) {
                                    for (int j = 0; j < bounds.width; j += this.bkgImageDim.width) {
                                        this.offscreenGraphics.drawImage(this.bkgImage, j, i, this);
                                    }
                                }
                                break;
                            }
                            default: {
                                this.offscreenGraphics.drawImage(this.bkgImage, (bounds.width - this.bkgImageDim.width) / 2, (bounds.height - this.bkgImageDim.height) / 2, this);
                                break;
                            }
                        }
                    }
                    if (this.people != null) {
                        this.people.drawTree(this.offscreenGraphics);
                    }
                    if (!this.parsing) {
                        break;
                    }
                    this.offscreenGraphics.setFont(this.statusFont);
                    if (this.loadBar != null) {
                        this.loadBox.draw(this.offscreenGraphics);
                        this.offscreenGraphics.drawString(this.fileNameStr, this.loadTextX, this.loadTextY);
                        this.loadBar.draw(this.offscreenGraphics);
                        this.loadBar.fill(this.offscreenGraphics, this.curRecordCount, this.totalRecordCount);
                        break;
                    }
                    break;
                }
                case 1: {
                    this.offscreenGraphics.setColor(Color.black);
                    this.offscreenGraphics.drawString(this.fileNameStr, 0, this.firstRow);
                    this.offscreenGraphics.drawString(this.globals.statusDesc, 0, this.statusRow);
                    break;
                }
                case 2: {
                    final FontMetrics fontMetrics = this.offscreenGraphics.getFontMetrics();
                    int n = 0;
                    int n2 = 0;
                    for (int k = 0; k < Globals.paramInfo.length; ++k) {
                        final int stringWidth = fontMetrics.stringWidth(Globals.paramInfo[k][0]);
                        if (stringWidth > n) {
                            n = stringWidth;
                        }
                        final int stringWidth2 = fontMetrics.stringWidth(Globals.paramInfo[k][1]);
                        if (stringWidth2 > n2) {
                            n2 = stringWidth2;
                        }
                    }
                    final int n3 = bounds.x + 2;
                    final int n4 = n3 + fontMetrics.stringWidth("    ");
                    final int n5 = n4 + n + 10;
                    final int n6 = n5 + n2 + 10;
                    this.offscreenGraphics.drawString(this.fileNameStr, 0, this.firstRow);
                    this.offscreenGraphics.drawString("Bad Parameter: " + this.globals.statusDesc, n3, this.statusRow);
                    this.offscreenGraphics.drawString("Valid parameters are as follows:", n3, this.statusRow + this.firstRow);
                    this.offscreenGraphics.drawString(Globals.paramHeadings[0], n4, this.statusRow + this.secondRow);
                    this.offscreenGraphics.drawString(Globals.paramHeadings[1], n5, this.statusRow + this.secondRow);
                    this.offscreenGraphics.drawString(Globals.paramHeadings[2], n6, this.statusRow + this.secondRow);
                    for (int l = 0; l < Globals.paramInfo.length; ++l) {
                        this.offscreenGraphics.drawString(Globals.paramInfo[l][0], n4, this.statusRow + this.thirdRow + l * this.rowHeight);
                        this.offscreenGraphics.drawString(Globals.paramInfo[l][1], n5, this.statusRow + this.thirdRow + l * this.rowHeight);
                        this.offscreenGraphics.drawString(Globals.paramInfo[l][2], n6, this.statusRow + this.thirdRow + l * this.rowHeight);
                    }
                    break;
                }
            }
            this.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final MenuItem menuItem = (MenuItem)actionEvent.getSource();
        if (menuItem == this.find) {
            this.find();
            return;
        }
        if (menuItem == this.zoomIn) {
            this.zoomIn();
            return;
        }
        if (menuItem == this.zoomOut) {
            this.zoomOut();
            return;
        }
        if (menuItem == this.details) {
            this.showDetails(this.personUnderMenu);
            return;
        }
        if (menuItem == this.center) {
            this.setCenterPerson(this.personUnderMenu);
            return;
        }
        if (menuItem == this.viewAll) {
            this.viewAll();
            return;
        }
        if (menuItem == this.help) {
            this.helpContents();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        switch (this.state) {
            case 1: {
                final int modifiers = mouseEvent.getModifiers();
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                final int clickCount = mouseEvent.getClickCount();
                if (this.debug) {
                    System.out.println("Mouse click at (" + x + "," + y + ")");
                }
                if ((modifiers & 0x4) != 0x0) {
                    final PopupMenu popupMenu = new PopupMenu();
                    this.add(popupMenu);
                    if (this.people != null) {
                        if (this.find == null) {
                            this.find = new MenuItem("Find");
                            this.zoomIn = new MenuItem("Zoom In");
                            this.zoomOut = new MenuItem("Zoom Out");
                            this.viewAll = new MenuItem("View All");
                            this.details = new MenuItem("Details");
                            this.center = new MenuItem("Make Primary Individual");
                            this.help = new MenuItem("Help");
                            this.find.addActionListener(this);
                            this.zoomIn.addActionListener(this);
                            this.zoomOut.addActionListener(this);
                            this.viewAll.addActionListener(this);
                            this.details.addActionListener(this);
                            this.center.addActionListener(this);
                            this.help.addActionListener(this);
                        }
                        popupMenu.add(this.find);
                        popupMenu.add(this.zoomIn);
                        popupMenu.add(this.zoomOut);
                        if (this.password != null) {
                            popupMenu.add(this.viewAll);
                        }
                        if ((this.personUnderMenu = this.people.getPersonUnderPoint(x, y)) != null) {
                            popupMenu.addSeparator();
                            popupMenu.add(this.details);
                            popupMenu.add(this.center);
                        }
                        popupMenu.addSeparator();
                    }
                    popupMenu.add(this.help);
                    popupMenu.show(this, x, y);
                    return;
                }
                if ((modifiers & 0x8) != 0x0) {
                    if (this.people != null) {
                        final Person personUnderPoint = this.people.getPersonUnderPoint(x, y);
                        if (personUnderPoint != null) {
                            this.setCenterPerson(personUnderPoint);
                        }
                    }
                }
                else {
                    if (this.people == null) {
                        break;
                    }
                    if (clickCount <= 1) {
                        final DrawingObject boxUnderPoint = this.people.getBoxUnderPoint(x, y);
                        if (boxUnderPoint != null) {
                            this.people.setSelectedBox(this.offscreenGraphics, boxUnderPoint);
                            this.repaint();
                        }
                    }
                    else {
                        final Person personUnderPoint2 = this.people.getPersonUnderPoint(x, y);
                        if (personUnderPoint2 == null) {
                            break;
                        }
                        this.showDetails(personUnderPoint2);
                    }
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.state == 0) {
            return;
        }
        final int keyCode = keyEvent.getKeyCode();
        final int modifiers = keyEvent.getModifiers();
        Label_0402: {
            if (this.keyboardEnabled) {
                if ((modifiers & 0x2) == 0x0) {
                    switch (keyCode) {
                        case 33: {
                            this.pageUp();
                            return;
                        }
                        case 34: {
                            this.pageDown();
                            return;
                        }
                        case 38: {
                            this.incUp();
                            return;
                        }
                        case 40: {
                            this.incDown();
                            return;
                        }
                        case 37: {
                            this.incLeft();
                            return;
                        }
                        case 39: {
                            this.incRight();
                            return;
                        }
                        case 36: {
                            this.home();
                            return;
                        }
                        case 70: {
                            this.find();
                            return;
                        }
                        case 82: {
                            this.forceRedraw();
                            return;
                        }
                        case 68: {
                            this.showDetails(this.people.getSelectedPerson());
                            return;
                        }
                        case 80: {
                            this.setPrimary();
                            return;
                        }
                        case 86: {
                            this.viewAll();
                            return;
                        }
                        case 72:
                        case 156: {
                            this.helpContents();
                            return;
                        }
                        case 65: {
                            this.helpAbout();
                            return;
                        }
                        case 84: {
                            this.testKeyCodes();
                            return;
                        }
                        case 73: {
                            this.zoomIn();
                            return;
                        }
                        case 79: {
                            this.zoomOut();
                            return;
                        }
                        default: {
                            switch (keyEvent.getKeyChar()) {
                                case '+':
                                case '=': {
                                    this.zoomIn();
                                    return;
                                }
                                case '-':
                                case '_': {
                                    this.zoomOut();
                                    return;
                                }
                                case '/':
                                case '?': {
                                    this.helpContents();
                                    return;
                                }
                                default: {
                                    break Label_0402;
                                }
                            }
                            break;
                        }
                    }
                }
                else {
                    switch (keyCode) {
                        case 33: {
                            this.pageLeft();
                            return;
                        }
                        case 34: {
                            this.pageRight();
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private Frame getFrameParent() {
        if (this.frame != null) {
            return this.frame;
        }
        return this.globals.appletFrameParent;
    }
    
    private void setTreeFonts() {
        if (this.offscreenGraphics != null) {
            final Font font = new Font("Helvetica", 1, this.treeFontSize);
            final Font font2 = new Font("Helvetica", 0, this.treeFontSize);
            final FontMetrics fontMetrics = this.offscreenGraphics.getFontMetrics(font);
            final FontMetrics fontMetrics2 = this.offscreenGraphics.getFontMetrics(font2);
            if (this.people != null) {
                this.people.setFonts(font, font2, fontMetrics, fontMetrics2);
            }
        }
        else {
            System.out.println("TreeCanvas::setTreeFonts - offscreenGraphics is null");
        }
    }
    
    public void pageUp() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.pageUp()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void pageDown() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.pageDown()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void pageLeft() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.pageLeft()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void pageRight() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.pageRight()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void incUp() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.incUp()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void incDown() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.incDown()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void incLeft() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.incLeft()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void incRight() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.incRight()) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public synchronized void setVert(final int vertScrollPos) {
        if (this.state == 0) {
            return;
        }
        this.vertScrollPos = vertScrollPos;
        if (this.vertScrollTimer == null) {
            this.vertScrollTimer = new Timer(250L, this, 2);
            return;
        }
        this.vertScrollTimer.reset();
    }
    
    public synchronized void setHorz(final int horzScrollPos) {
        if (this.state == 0) {
            return;
        }
        this.horzScrollPos = horzScrollPos;
        if (this.horzScrollTimer == null) {
            this.horzScrollTimer = new Timer(250L, this, 3);
            return;
        }
        this.horzScrollTimer.reset();
    }
    
    private void doVertAbsoluteScroll() {
        if (this.people != null && this.people.setVert(this.vertScrollPos)) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    private void doHorzAbsoluteScroll() {
        if (this.people != null && this.people.setHorz(this.horzScrollPos)) {
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void home() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.people.home()) {
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public synchronized void setBounds(final int n, final int n2, final int canvasWidth, final int canvasHeight) {
        super.setBounds(n, n2, canvasWidth, canvasHeight);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        if (this.debug) {
            System.out.println("TreeCanvas::setBounds - setting setupFlags");
        }
        this.setupFlags |= 0x4;
        if (this.setupFlags == 7) {
            if (this.debug) {
                System.out.println("TreeCanvas::setBounds - initializing tree");
            }
            this.initTree();
            return;
        }
        if (this.setupFlags == 15) {
            if (this.debug) {
                System.out.println("TreeCanvas::setBounds - resizing tree");
            }
            this.createOffscreenImage();
            if (this.people != null) {
                this.people.setScreenSize(this.canvasHeight, this.canvasWidth);
                this.people.calcTree();
                this.people.setHorz(this.people.getHorzCurPos());
                this.people.setVert(this.people.getVertCurPos());
            }
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    private void adjustScrollBars() {
        if (this.people != null) {
            if (this.frame != null) {
                this.frame.adjustScrollBars(this.people.getHorzCurPos(), this.canvasWidth, this.people.getHorzMinPos(), this.people.getHorzMaxPos(), this.people.getVertCurPos(), this.canvasHeight, this.people.getVertMinPos(), this.people.getVertMaxPos());
                return;
            }
            if (this.panel != null) {
                this.panel.adjustScrollBars(this.people.getHorzCurPos(), this.canvasWidth, this.people.getHorzMinPos(), this.people.getHorzMaxPos(), this.people.getVertCurPos(), this.canvasHeight, this.people.getVertMinPos(), this.people.getVertMaxPos());
            }
        }
    }
    
    public synchronized void callback(final int n, final int n2, final String htmlTarget, final String s) {
        switch (n) {
            case 1: {
                if (n2 != 0) {
                    break;
                }
                if (htmlTarget.equals(this.password)) {
                    this.password = null;
                    if (this.frame != null) {
                        this.frame.passwordRequired(false);
                    }
                    this.people.showHidden();
                    this.forceRedraw();
                    return;
                }
                new InfoDlg(this.getFrameParent(), this.globals.context, this.globals.htmlTarget, this, "Invalid Password", "Invalid Password", true).show();
            }
            case 2: {
                this.vertScrollTimer = null;
                this.doVertAbsoluteScroll();
            }
            case 3: {
                this.horzScrollTimer = null;
                this.doHorzAbsoluteScroll();
            }
            case 4: {
                this.globals.htmlTarget = htmlTarget;
            }
            default: {
                System.out.println("TreeCanvas#callback: Unknown task id: " + n);
                break;
            }
        }
    }
    
    public void find() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.keyboardEnabled) {
            this.keyboardEnabled = false;
            new FindDlg(this.getFrameParent(), this.globals.context, this.globals.htmlTarget, this, this.people).show();
            this.keyboardEnabled = true;
        }
    }
    
    public void zoomIn() {
        if (this.state == 0) {
            return;
        }
        final int n = this.treeFontSize + 2;
        if (this.people != null && n >= 0 && this.offscreenGraphics != null) {
            this.treeFontSize += 2;
            this.setTreeFonts();
            this.people.calcTree();
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void zoomOut() {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && this.treeFontSize > 2 && this.offscreenGraphics != null) {
            this.treeFontSize -= 2;
            this.setTreeFonts();
            this.people.calcTree();
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void forceRedraw() {
        this.setCenterPerson(this.people.getCenterPerson());
    }
    
    public void showDetails() {
        this.showDetails(this.people.getSelectedPerson());
    }
    
    public void showDetails(Person centerPerson) {
        boolean b = false;
        String string = null;
        final DecimalFormat decimalFormat = new DecimalFormat("0000");
        if (this.state == 0) {
            return;
        }
        if (this.people != null) {
            if (centerPerson == null) {
                centerPerson = this.people.getCenterPerson();
            }
            if (this.globals.detailLoc != null) {
                try {
                    string = String.valueOf(this.globals.detailLoc) + "/UHP-" + decimalFormat.format(centerPerson.id) + ".html";
                    final URL url = new URL(this.globals.documentBase, string);
                    if (url != null) {
                        if (this.globals.context != null) {
                            if (this.globals.htmlTarget != null) {
                                System.out.println("Showing details at: " + this.globals.htmlTarget);
                                this.globals.context.showDocument(url, this.globals.htmlTarget);
                            }
                            else {
                                System.out.println("Showing details");
                                this.globals.context.showDocument(url);
                            }
                            System.out.println("Back from showing details");
                            b = true;
                        }
                        else {
                            System.out.println("Invalid applet context");
                        }
                    }
                    else {
                        System.out.println("Couldn't create URL from \"" + string + "\"");
                    }
                }
                catch (MalformedURLException ex) {
                    System.out.println("MalformedURLException: " + ex);
                    System.out.println("  On URL: \"" + string + "\"");
                }
            }
            if (!b) {
                new InfoDlg(this.getFrameParent(), this.globals.context, this.globals.htmlTarget, this, "Details of " + centerPerson.fullName, centerPerson.details, false).show();
            }
        }
    }
    
    public void setCenterPerson(final Person centerPerson) {
        if (this.state == 0) {
            return;
        }
        if (this.people != null && centerPerson != null) {
            if (this.frame != null) {
                this.frame.setTitle("InterneTree: " + centerPerson.fullName);
            }
            this.people.setCenterPerson(centerPerson);
            this.people.calcTree();
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public synchronized void viewAll() {
        if (this.state == 0) {
            return;
        }
        if (this.password != null) {
            this.keyboardEnabled = false;
            new EntryDlg(this.getFrameParent(), "Password", "Enter Password", true, null, this, 1).show();
            this.keyboardEnabled = true;
        }
    }
    
    public synchronized void back() {
        if (this.people != null && this.people.back()) {
            if (this.frame != null) {
                this.frame.setTitle("InterneTree: " + this.people.getCenterPerson().fullName);
            }
            this.people.calcTree();
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public synchronized void forward() {
        if (this.people != null && this.people.forward()) {
            if (this.frame != null) {
                this.frame.setTitle("InterneTree: " + this.people.getCenterPerson().fullName);
            }
            this.people.calcTree();
            this.people.setHorz(this.people.getHorzCurPos());
            this.people.setVert(this.people.getVertCurPos());
            this.adjustScrollBars();
            this.redraw();
        }
    }
    
    public void helpContents() {
        if (this.globals.helpUrl != null) {
            if (this.globals.htmlTarget != null) {
                System.out.println("Showing details at: " + this.globals.helpUrl);
                this.globals.context.showDocument(this.globals.helpUrl, this.globals.htmlTarget);
                return;
            }
            System.out.println("Showing details at: " + this.globals.helpUrl);
            this.globals.context.showDocument(this.globals.helpUrl);
        }
    }
    
    public void helpAbout() {
        if (this.state == 0) {
            return;
        }
        new InfoDlg(this.getFrameParent(), this.globals.context, this.globals.htmlTarget, this, "About InterneTree (tm)", "InterneTree (tm) was developed for Genealogy.com by\nDon Baldwin, mailto:donb@qualcomm.com  (c) 2000 Don Baldwin.\nAll rights reserved.", true).show();
    }
    
    private void testKeyCodes() {
        System.out.println("VK_0             = " + 48);
        System.out.println("VK_1             = " + 49);
        System.out.println("VK_2             = " + 50);
        System.out.println("VK_3             = " + 51);
        System.out.println("VK_4             = " + 52);
        System.out.println("VK_5             = " + 53);
        System.out.println("VK_6             = " + 54);
        System.out.println("VK_7             = " + 55);
        System.out.println("VK_8             = " + 56);
        System.out.println("VK_9             = " + 57);
        System.out.println("VK_A             = " + 65);
        System.out.println("VK_ACCEPT        = " + 30);
        System.out.println("VK_ADD           = " + 107);
        System.out.println("VK_ALT           = " + 18);
        System.out.println("VK_B             = " + 66);
        System.out.println("VK_BACK_QUOTE    = " + 192);
        System.out.println("VK_BACK_SLASH    = " + 92);
        System.out.println("VK_BACK_SPACE    = " + 8);
        System.out.println("VK_C             = " + 67);
        System.out.println("VK_CANCEL        = " + 3);
        System.out.println("VK_CAPS_LOCK     = " + 20);
        System.out.println("VK_CLEAR         = " + 12);
        System.out.println("VK_CLOSE_BRACKET = " + 93);
        System.out.println("VK_COMMA         = " + 44);
        System.out.println("VK_CONTROL       = " + 17);
        System.out.println("VK_CONVERT       = " + 28);
        System.out.println("VK_D             = " + 68);
        System.out.println("VK_DECIMAL       = " + 110);
        System.out.println("VK_DELETE        = " + 127);
        System.out.println("VK_DIVIDE        = " + 111);
        System.out.println("VK_DOWN          = " + 40);
        System.out.println("VK_E             = " + 69);
        System.out.println("VK_END           = " + 35);
        System.out.println("VK_ENTER         = " + 10);
        System.out.println("VK_EQUALS        = " + 61);
        System.out.println("VK_ESCAPE        = " + 27);
        System.out.println("VK_F             = " + 70);
        System.out.println("VK_F1            = " + 112);
        System.out.println("VK_F10           = " + 121);
        System.out.println("VK_F11           = " + 122);
        System.out.println("VK_F12           = " + 123);
        System.out.println("VK_F2            = " + 113);
        System.out.println("VK_F3            = " + 114);
        System.out.println("VK_F4            = " + 115);
        System.out.println("VK_F5            = " + 116);
        System.out.println("VK_F6            = " + 117);
        System.out.println("VK_F7            = " + 118);
        System.out.println("VK_F8            = " + 119);
        System.out.println("VK_F9            = " + 120);
        System.out.println("VK_FINAL         = " + 24);
        System.out.println("VK_G             = " + 71);
        System.out.println("VK_H             = " + 72);
        System.out.println("VK_HELP          = " + 156);
        System.out.println("VK_HOME          = " + 36);
        System.out.println("VK_I             = " + 73);
        System.out.println("VK_INSERT        = " + 155);
        System.out.println("VK_J             = " + 74);
        System.out.println("VK_K             = " + 75);
        System.out.println("VK_KANA          = " + 21);
        System.out.println("VK_KANJI         = " + 25);
        System.out.println("VK_L             = " + 76);
        System.out.println("VK_LEFT          = " + 37);
        System.out.println("VK_M             = " + 77);
        System.out.println("VK_META          = " + 157);
        System.out.println("VK_MODECHANGE    = " + 31);
        System.out.println("VK_MULTIPLY      = " + 106);
        System.out.println("VK_N             = " + 78);
        System.out.println("VK_NONCONVERT    = " + 29);
        System.out.println("VK_NUM_LOCK      = " + 144);
        System.out.println("VK_NUMPAD0       = " + 96);
        System.out.println("VK_NUMPAD1       = " + 97);
        System.out.println("VK_NUMPAD2       = " + 98);
        System.out.println("VK_NUMPAD3       = " + 99);
        System.out.println("VK_NUMPAD4       = " + 100);
        System.out.println("VK_NUMPAD5       = " + 101);
        System.out.println("VK_NUMPAD6       = " + 102);
        System.out.println("VK_NUMPAD7       = " + 103);
        System.out.println("VK_NUMPAD8       = " + 104);
        System.out.println("VK_NUMPAD9       = " + 105);
        System.out.println("VK_O             = " + 79);
        System.out.println("VK_OPEN_BRACKET  = " + 91);
        System.out.println("VK_P             = " + 80);
        System.out.println("VK_PAGE_DOWN     = " + 34);
        System.out.println("VK_PAGE_UP       = " + 33);
        System.out.println("VK_PAUSE         = " + 19);
        System.out.println("VK_PERIOD        = " + 46);
        System.out.println("VK_PRINTSCREEN   = " + 154);
        System.out.println("VK_Q             = " + 81);
        System.out.println("VK_QUOTE         = " + 222);
        System.out.println("VK_R             = " + 82);
        System.out.println("VK_RIGHT         = " + 39);
        System.out.println("VK_S             = " + 83);
        System.out.println("VK_SCROLL_LOCK   = " + 145);
        System.out.println("VK_SEMICOLON     = " + 59);
        System.out.println("VK_SEPARATER     = " + 108);
        System.out.println("VK_SHIFT         = " + 16);
        System.out.println("VK_SLASH         = " + 47);
        System.out.println("VK_SPACE         = " + 32);
        System.out.println("VK_SUBTRACT      = " + 109);
        System.out.println("VK_T             = " + 84);
        System.out.println("VK_TAB           = " + 9);
        System.out.println("VK_U             = " + 85);
        System.out.println("VK_UNDEFINED     = " + 0);
        System.out.println("VK_UP            = " + 38);
        System.out.println("VK_V             = " + 86);
        System.out.println("VK_W             = " + 87);
        System.out.println("VK_X             = " + 88);
        System.out.println("VK_Y             = " + 89);
        System.out.println("VK_Z             = " + 90);
    }
    
    static {
        loadingCursor = new Cursor(3);
        normalCursor = new Cursor(0);
        pendingCursor = new Cursor(1);
    }
}
