// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.util.Hashtable;
import java.awt.Scrollbar;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.Properties;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

public final class TerminalWin extends Canvas implements Terminal, KeyListener, AdjustmentListener, MouseListener, MouseMotionListener, ComponentListener, FocusListener
{
    boolean metaKeyKludge;
    boolean ctrlKeyKludge;
    boolean tildeTypedKludge;
    boolean tildePressedKludge;
    boolean pendingShow;
    static final boolean DEBUG = false;
    static final boolean DEBUGKEYEVENT = false;
    public static final int GRAVITY_SOUTHWEST = 0;
    public static final int GRAVITY_NORTHWEST = 1;
    public static final int MIN_ROWS = 2;
    public static final int MIN_COLS = 8;
    public static final int MAX_COLS = 512;
    public static final int MAX_ROWS = 512;
    TerminalListener listener;
    TerminalClipboard clipboard;
    TerminalInterpreter interpreter;
    FixedScrollbar scrollbar;
    boolean haveScrollbar;
    PopupMenu popupmenu;
    Panel myPanel;
    Frame ownerFrame;
    public int popupButton;
    TerminalMenuHandler menuHandler;
    String title;
    Properties props;
    boolean propsChanged;
    String savedGeomPos;
    boolean insideConstructor;
    byte bsCharacter;
    byte delCharacter;
    boolean haveFocus;
    boolean repaintPending;
    boolean cursorHollow;
    boolean cursorDrawn;
    boolean complexScroll;
    boolean fullRefresh;
    int dirtyTop;
    int dirtyBottom;
    int dirtyLeft;
    int dirtyRight;
    int resizeGravity;
    int rows;
    int cols;
    int vpixels;
    int hpixels;
    int borderWidth;
    int borderHeight;
    int windowTop;
    int windowBottom;
    int windowLeft;
    int windowRight;
    int charWidth;
    int charHeight;
    int charMaxAscent;
    int charMaxDescent;
    int charLeading;
    int baselineIndex;
    int curRow;
    int curCol;
    int lastCursorRow;
    int lastCursorCol;
    int selectRowAnchor;
    int selectColAnchor;
    int selectRowLast;
    int selectColLast;
    boolean hasSelection;
    boolean selectReverse;
    String selectDelims;
    int selectClickRow;
    boolean selectClickState;
    long lastLeftClick;
    int curAttr;
    int curRowSave;
    int curColSave;
    int curAttrSave;
    Color origBgColor;
    Color origFgColor;
    Color cursorColor;
    public static final Color[] termColors;
    public static final String[] termColorNames;
    char[][] screen;
    int[][] attributes;
    int saveLines;
    int visTop;
    int saveVisTop;
    public static final int ATTR_CHARNOTDRAWN = 0;
    public static final int ATTR_LINEDRAW = 256;
    public static final int ATTR_SELECTED = 4096;
    public static final int ATTR_CHARDRAWN = 32768;
    public static final int MASK_ATTR = 65535;
    public static final int MASK_FGCOL = 16711680;
    public static final int MASK_BGCOL = -16777216;
    public static final int SHIFT_FGCOL = 16;
    public static final int SHIFT_BGCOL = 24;
    public static final char[] spacerow;
    public static final int[] zerorow;
    boolean[] tabStops;
    boolean[] termOptions;
    Image memImage;
    Graphics memGraphics;
    Dimension memImageSize;
    public Font plainFont;
    public Font boldFont;
    boolean waitForMore;
    boolean insideUpdate;
    
    public TerminalWin(final Frame ownerFrame, final TerminalInterpreter interpreter, final Properties initProps) throws IllegalArgumentException {
        this.metaKeyKludge = false;
        this.ctrlKeyKludge = false;
        this.tildeTypedKludge = false;
        this.tildePressedKludge = false;
        this.pendingShow = true;
        this.popupButton = 4;
        this.borderWidth = 2;
        this.borderHeight = 2;
        this.selectClickRow = -1;
        this.lastLeftClick = 0L;
        this.tabStops = new boolean[512];
        this.waitForMore = false;
        this.insideUpdate = false;
        this.scrollbar = null;
        this.haveScrollbar = false;
        this.title = null;
        this.termOptions = new boolean[18];
        this.cursorDrawn = false;
        this.cursorHollow = false;
        this.curAttr = 32768;
        this.curRow = 0;
        this.curCol = 0;
        this.visTop = 0;
        this.saveVisTop = 0;
        this.repaintPending = false;
        this.savedGeomPos = "";
        this.ownerFrame = ownerFrame;
        (this.interpreter = interpreter).setTerminal(this);
        this.setProperties(initProps, this.insideConstructor = true);
        this.insideConstructor = false;
        this.propsChanged = false;
        this.resetTabs();
        ownerFrame.addComponentListener(this);
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.addFocusListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public TerminalWin(final Frame ownerFrame, final TerminalInterpreter interpreter) throws IllegalArgumentException, NoSuchElementException {
        this(ownerFrame, interpreter, TerminalDefProps.defaultProperties);
    }
    
    public void setMenus(final TerminalMenuHandler menus) {
        this.menuHandler = menus;
    }
    
    public TerminalMenuHandler getMenus() {
        return this.menuHandler;
    }
    
    public void updateMenus() {
        if (this.menuHandler != null) {
            this.menuHandler.update();
        }
    }
    
    public void setProperties(final Properties newProps, final boolean merge) throws IllegalArgumentException, NoSuchElementException {
        final Properties oldProps = this.props;
        this.props = new Properties(TerminalDefProps.defaultProperties);
        if (merge && oldProps != null) {
            final Enumeration enum1 = oldProps.keys();
            while (enum1.hasMoreElements()) {
                final String name = enum1.nextElement();
                final String value = oldProps.getProperty(name);
                ((Hashtable<String, String>)this.props).put(name, value);
            }
        }
        final Enumeration enum1 = newProps.keys();
        while (enum1.hasMoreElements()) {
            final String name = enum1.nextElement();
            final String value = newProps.getProperty(name);
            if (!TerminalDefProps.isProperty(name)) {
                throw new NoSuchElementException("unknown terminal-property '" + name + "'");
            }
        }
        for (int i = this.termOptions.length; i < TerminalDefProps.defaultPropDesc.length; ++i) {
            final String name = TerminalDefProps.defaultPropDesc[i][0];
            String value = newProps.getProperty(name);
            if (value == null) {
                value = this.props.getProperty(name);
            }
            if (!merge && oldProps != null) {
                final String oldVal = oldProps.getProperty(name);
                this.setProperty(name, value, !value.equals(oldVal));
            }
            else {
                this.setProperty(name, value, this.insideConstructor);
            }
        }
        for (int i = 0; i < this.termOptions.length; ++i) {
            final String name = TerminalDefProps.defaultPropDesc[i][0];
            String value = newProps.getProperty(name);
            if (value == null) {
                value = this.props.getProperty(name);
            }
            if (!merge && oldProps != null) {
                final String oldVal = oldProps.getProperty(name);
                this.setProperty(name, value, !value.equals(oldVal));
            }
            else {
                this.setProperty(name, value, this.insideConstructor);
            }
        }
    }
    
    public Properties getProperties() {
        return this.props;
    }
    
    public boolean getPropsChanged() {
        return this.propsChanged;
    }
    
    public void setPropsChanged(final boolean value) {
        this.propsChanged = value;
    }
    
    public void setProperty(final String key, final String value) throws IllegalArgumentException, NoSuchElementException {
        this.setProperty(key, value, false);
    }
    
    public synchronized void setProperty(final String key, final String value, final boolean forceSet) throws IllegalArgumentException, NoSuchElementException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore          isEqual
        //     3: aload_0         /* this */
        //     4: aload_1         /* key */
        //     5: invokevirtual   mindbright/terminal/TerminalWin.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //     8: dup            
        //     9: astore          val
        //    11: ifnull          31
        //    14: aload           val
        //    16: aload_2         /* value */
        //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    20: ifeq            31
        //    23: iconst_1       
        //    24: istore          isEqual
        //    26: iload_3         /* forceSet */
        //    27: ifne            31
        //    30: return         
        //    31: iconst_0       
        //    32: istore          i
        //    34: iload           i
        //    36: aload_0         /* this */
        //    37: getfield        mindbright/terminal/TerminalWin.termOptions:[Z
        //    40: arraylength    
        //    41: if_icmpge       68
        //    44: getstatic       mindbright/terminal/TerminalDefProps.defaultPropDesc:[[Ljava/lang/String;
        //    47: iload           i
        //    49: aaload         
        //    50: iconst_0       
        //    51: aaload         
        //    52: aload_1         /* key */
        //    53: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    56: ifeq            62
        //    59: goto            68
        //    62: iinc            i, 1
        //    65: goto            34
        //    68: iload           i
        //    70: aload_0         /* this */
        //    71: getfield        mindbright/terminal/TerminalWin.termOptions:[Z
        //    74: arraylength    
        //    75: if_icmpge       148
        //    78: aload_2         /* value */
        //    79: ldc             "true"
        //    81: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    84: ifne            128
        //    87: aload_2         /* value */
        //    88: ldc             "false"
        //    90: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    93: ifne            128
        //    96: new             Ljava/lang/IllegalArgumentException;
        //    99: dup            
        //   100: new             Ljava/lang/StringBuffer;
        //   103: dup            
        //   104: invokespecial   java/lang/StringBuffer.<init>:()V
        //   107: ldc             "value for '"
        //   109: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   112: aload_1         /* key */
        //   113: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   116: ldc             "' must be 'true' or 'false'"
        //   118: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   121: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: aload_0         /* this */
        //   129: iload           i
        //   131: new             Ljava/lang/Boolean;
        //   134: dup            
        //   135: aload_2         /* value */
        //   136: invokespecial   java/lang/Boolean.<init>:(Ljava/lang/String;)V
        //   139: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   142: invokevirtual   mindbright/terminal/TerminalWin.setOption:(IZ)V
        //   145: goto            950
        //   148: aload_1         /* key */
        //   149: ldc             "te"
        //   151: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   154: ifeq            181
        //   157: aload_0         /* this */
        //   158: getfield        mindbright/terminal/TerminalWin.interpreter:Lmindbright/terminal/TerminalInterpreter;
        //   161: instanceof      Lmindbright/terminal/TerminalXTerm;
        //   164: ifeq            950
        //   167: aload_0         /* this */
        //   168: getfield        mindbright/terminal/TerminalWin.interpreter:Lmindbright/terminal/TerminalInterpreter;
        //   171: checkcast       Lmindbright/terminal/TerminalXTerm;
        //   174: aload_2         /* value */
        //   175: invokevirtual   mindbright/terminal/TerminalXTerm.setTerminalType:(Ljava/lang/String;)V
        //   178: goto            950
        //   181: aload_1         /* key */
        //   182: ldc             "fn"
        //   184: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   187: ifeq            207
        //   190: aload_0         /* this */
        //   191: aload_2         /* value */
        //   192: aload_0         /* this */
        //   193: ldc             "fs"
        //   195: invokevirtual   mindbright/terminal/TerminalWin.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   198: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   201: invokespecial   mindbright/terminal/TerminalWin.setFont:(Ljava/lang/String;I)V
        //   204: goto            950
        //   207: aload_1         /* key */
        //   208: ldc             "fs"
        //   210: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   213: ifeq            267
        //   216: aload_0         /* this */
        //   217: aload_0         /* this */
        //   218: ldc             "fn"
        //   220: invokevirtual   mindbright/terminal/TerminalWin.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //   223: aload_2         /* value */
        //   224: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   227: invokespecial   mindbright/terminal/TerminalWin.setFont:(Ljava/lang/String;I)V
        //   230: goto            950
        //   233: astore          e
        //   235: new             Ljava/lang/IllegalArgumentException;
        //   238: dup            
        //   239: new             Ljava/lang/StringBuffer;
        //   242: dup            
        //   243: invokespecial   java/lang/StringBuffer.<init>:()V
        //   246: ldc             "value for '"
        //   248: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   251: aload_1         /* key */
        //   252: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   255: ldc             "' must be an integer"
        //   257: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   260: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   263: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   266: athrow         
        //   267: aload_1         /* key */
        //   268: ldc             "sl"
        //   270: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   273: ifeq            346
        //   276: aload_2         /* value */
        //   277: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   280: istore          sl
        //   282: iload           sl
        //   284: iflt            295
        //   287: iload           sl
        //   289: sipush          8192
        //   292: if_icmple       303
        //   295: new             Ljava/lang/NumberFormatException;
        //   298: dup            
        //   299: invokespecial   java/lang/NumberFormatException.<init>:()V
        //   302: athrow         
        //   303: aload_0         /* this */
        //   304: iload           sl
        //   306: invokespecial   mindbright/terminal/TerminalWin.setSaveLines:(I)V
        //   309: goto            950
        //   312: astore          e
        //   314: new             Ljava/lang/IllegalArgumentException;
        //   317: dup            
        //   318: new             Ljava/lang/StringBuffer;
        //   321: dup            
        //   322: invokespecial   java/lang/StringBuffer.<init>:()V
        //   325: ldc             "value for '"
        //   327: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   330: aload_1         /* key */
        //   331: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   334: ldc             "' must be an integer (0-8192)"
        //   336: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   339: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   342: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   345: athrow         
        //   346: aload_1         /* key */
        //   347: ldc             "sb"
        //   349: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   352: ifeq            523
        //   355: aload_0         /* this */
        //   356: getfield        mindbright/terminal/TerminalWin.myPanel:Ljava/awt/Panel;
        //   359: ifnull          950
        //   362: aload_2         /* value */
        //   363: ldc             "left"
        //   365: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   368: ifne            380
        //   371: aload_2         /* value */
        //   372: ldc             "right"
        //   374: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   377: ifeq            467
        //   380: aload_0         /* this */
        //   381: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   384: ifnull          950
        //   387: aload_0         /* this */
        //   388: getfield        mindbright/terminal/TerminalWin.myPanel:Ljava/awt/Panel;
        //   391: aload_0         /* this */
        //   392: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   395: invokevirtual   java/awt/Panel.remove:(Ljava/awt/Component;)V
        //   398: aload_2         /* value */
        //   399: ldc             "right"
        //   401: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   404: ifeq            423
        //   407: aload_0         /* this */
        //   408: getfield        mindbright/terminal/TerminalWin.myPanel:Ljava/awt/Panel;
        //   411: aload_0         /* this */
        //   412: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   415: ldc             "East"
        //   417: invokevirtual   java/awt/Panel.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //   420: goto            436
        //   423: aload_0         /* this */
        //   424: getfield        mindbright/terminal/TerminalWin.myPanel:Ljava/awt/Panel;
        //   427: aload_0         /* this */
        //   428: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   431: ldc             "West"
        //   433: invokevirtual   java/awt/Panel.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //   436: aload_0         /* this */
        //   437: iconst_1       
        //   438: putfield        mindbright/terminal/TerminalWin.haveScrollbar:Z
        //   441: aload_0         /* this */
        //   442: invokevirtual   mindbright/terminal/TerminalWin.updateScrollbarValues:()V
        //   445: aload_0         /* this */
        //   446: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   449: aload_2         /* value */
        //   450: invokevirtual   mindbright/terminal/TerminalWin$FixedScrollbar.setWindowSide:(Ljava/lang/String;)V
        //   453: aload_0         /* this */
        //   454: getfield        mindbright/terminal/TerminalWin.ownerFrame:Ljava/awt/Frame;
        //   457: invokevirtual   java/awt/Frame.pack:()V
        //   460: aload_0         /* this */
        //   461: invokevirtual   mindbright/terminal/TerminalWin.requestFocus:()V
        //   464: goto            950
        //   467: aload_2         /* value */
        //   468: ldc             "none"
        //   470: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   473: ifeq            513
        //   476: aload_0         /* this */
        //   477: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   480: ifnull          494
        //   483: aload_0         /* this */
        //   484: getfield        mindbright/terminal/TerminalWin.myPanel:Ljava/awt/Panel;
        //   487: aload_0         /* this */
        //   488: getfield        mindbright/terminal/TerminalWin.scrollbar:Lmindbright/terminal/TerminalWin$FixedScrollbar;
        //   491: invokevirtual   java/awt/Panel.remove:(Ljava/awt/Component;)V
        //   494: aload_0         /* this */
        //   495: getfield        mindbright/terminal/TerminalWin.ownerFrame:Ljava/awt/Frame;
        //   498: invokevirtual   java/awt/Frame.pack:()V
        //   501: aload_0         /* this */
        //   502: invokevirtual   mindbright/terminal/TerminalWin.requestFocus:()V
        //   505: aload_0         /* this */
        //   506: iconst_0       
        //   507: putfield        mindbright/terminal/TerminalWin.haveScrollbar:Z
        //   510: goto            950
        //   513: new             Ljava/lang/IllegalArgumentException;
        //   516: dup            
        //   517: ldc             "scrollbar can be right, left or none"
        //   519: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   522: athrow         
        //   523: aload_1         /* key */
        //   524: ldc             "bg"
        //   526: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   529: ifne            550
        //   532: aload_1         /* key */
        //   533: ldc             "fg"
        //   535: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   538: ifne            550
        //   541: aload_1         /* key */
        //   542: ldc             "cc"
        //   544: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   547: ifeq            657
        //   550: aload_2         /* value */
        //   551: iconst_0       
        //   552: invokevirtual   java/lang/String.charAt:(I)C
        //   555: invokestatic    java/lang/Character.isDigit:(C)Z
        //   558: ifeq            570
        //   561: aload_2         /* value */
        //   562: invokestatic    mindbright/terminal/TerminalWin.getTermRGBColor:(Ljava/lang/String;)Ljava/awt/Color;
        //   565: astore          c
        //   567: goto            576
        //   570: aload_2         /* value */
        //   571: invokestatic    mindbright/terminal/TerminalWin.getTermColor:(Ljava/lang/String;)Ljava/awt/Color;
        //   574: astore          c
        //   576: goto            591
        //   579: astore          e
        //   581: new             Ljava/lang/IllegalArgumentException;
        //   584: dup            
        //   585: ldc             "valid colors: 'color-name' or '<r>,<g>,<b>'"
        //   587: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   590: athrow         
        //   591: aload_1         /* key */
        //   592: ldc             "bg"
        //   594: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   597: ifeq            617
        //   600: aload_0         /* this */
        //   601: aload           c
        //   603: putfield        mindbright/terminal/TerminalWin.origBgColor:Ljava/awt/Color;
        //   606: aload_0         /* this */
        //   607: aload_0         /* this */
        //   608: getfield        mindbright/terminal/TerminalWin.origBgColor:Ljava/awt/Color;
        //   611: invokevirtual   mindbright/terminal/TerminalWin.setBackground:(Ljava/awt/Color;)V
        //   614: goto            649
        //   617: aload_1         /* key */
        //   618: ldc             "cc"
        //   620: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   623: ifeq            635
        //   626: aload_0         /* this */
        //   627: aload           c
        //   629: putfield        mindbright/terminal/TerminalWin.cursorColor:Ljava/awt/Color;
        //   632: goto            649
        //   635: aload_0         /* this */
        //   636: aload           c
        //   638: putfield        mindbright/terminal/TerminalWin.origFgColor:Ljava/awt/Color;
        //   641: aload_0         /* this */
        //   642: aload_0         /* this */
        //   643: getfield        mindbright/terminal/TerminalWin.origFgColor:Ljava/awt/Color;
        //   646: invokevirtual   mindbright/terminal/TerminalWin.setForeground:(Ljava/awt/Color;)V
        //   649: aload_0         /* this */
        //   650: iconst_0       
        //   651: invokevirtual   mindbright/terminal/TerminalWin.makeAllDirty:(Z)V
        //   654: goto            950
        //   657: aload_1         /* key */
        //   658: ldc             "rg"
        //   660: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   663: ifeq            715
        //   666: aload_2         /* value */
        //   667: ldc             "top"
        //   669: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   672: ifeq            681
        //   675: iconst_1       
        //   676: istore          rg
        //   678: goto            706
        //   681: aload_2         /* value */
        //   682: ldc             "bottom"
        //   684: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   687: ifeq            696
        //   690: iconst_0       
        //   691: istore          rg
        //   693: goto            706
        //   696: new             Ljava/lang/IllegalArgumentException;
        //   699: dup            
        //   700: ldc             "reszize gravity can be 'top' or 'bottom'"
        //   702: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   705: athrow         
        //   706: aload_0         /* this */
        //   707: iload           7
        //   709: putfield        mindbright/terminal/TerminalWin.resizeGravity:I
        //   712: goto            950
        //   715: aload_1         /* key */
        //   716: ldc             "de"
        //   718: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   721: ifeq            770
        //   724: aload_2         /* value */
        //   725: ldc             "DEL"
        //   727: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   730: ifeq            742
        //   733: aload_0         /* this */
        //   734: bipush          127
        //   736: putfield        mindbright/terminal/TerminalWin.delCharacter:B
        //   739: goto            950
        //   742: aload_2         /* value */
        //   743: ldc             "BS"
        //   745: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   748: ifeq            760
        //   751: aload_0         /* this */
        //   752: bipush          8
        //   754: putfield        mindbright/terminal/TerminalWin.delCharacter:B
        //   757: goto            950
        //   760: new             Ljava/lang/IllegalArgumentException;
        //   763: dup            
        //   764: ldc             "delete character can be 'DEL' or 'BS'"
        //   766: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   769: athrow         
        //   770: aload_1         /* key */
        //   771: ldc             "bs"
        //   773: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   776: ifeq            825
        //   779: aload_2         /* value */
        //   780: ldc             "DEL"
        //   782: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   785: ifeq            797
        //   788: aload_0         /* this */
        //   789: bipush          127
        //   791: putfield        mindbright/terminal/TerminalWin.bsCharacter:B
        //   794: goto            950
        //   797: aload_2         /* value */
        //   798: ldc             "BS"
        //   800: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   803: ifeq            815
        //   806: aload_0         /* this */
        //   807: bipush          8
        //   809: putfield        mindbright/terminal/TerminalWin.bsCharacter:B
        //   812: goto            950
        //   815: new             Ljava/lang/IllegalArgumentException;
        //   818: dup            
        //   819: ldc             "backspace character can be 'DEL' or 'BS'"
        //   821: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   824: athrow         
        //   825: aload_1         /* key */
        //   826: ldc             "gm"
        //   828: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   831: ifeq            843
        //   834: aload_0         /* this */
        //   835: aload_2         /* value */
        //   836: iconst_1       
        //   837: invokevirtual   mindbright/terminal/TerminalWin.setGeometry:(Ljava/lang/String;Z)V
        //   840: goto            950
        //   843: aload_1         /* key */
        //   844: ldc             "sd"
        //   846: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   849: ifeq            918
        //   852: aload_2         /* value */
        //   853: iconst_0       
        //   854: invokevirtual   java/lang/String.charAt:(I)C
        //   857: bipush          34
        //   859: if_icmpne       877
        //   862: aload_2         /* value */
        //   863: aload_2         /* value */
        //   864: invokevirtual   java/lang/String.length:()I
        //   867: iconst_1       
        //   868: isub           
        //   869: invokevirtual   java/lang/String.charAt:(I)C
        //   872: bipush          34
        //   874: if_icmpeq       902
        //   877: new             Ljava/lang/StringBuffer;
        //   880: dup            
        //   881: invokespecial   java/lang/StringBuffer.<init>:()V
        //   884: ldc             "\""
        //   886: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   889: aload_2         /* value */
        //   890: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   893: ldc             "\""
        //   895: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   898: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   901: astore_2        /* value */
        //   902: aload_0         /* this */
        //   903: aload_2         /* value */
        //   904: iconst_1       
        //   905: aload_2         /* value */
        //   906: invokevirtual   java/lang/String.length:()I
        //   909: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   912: putfield        mindbright/terminal/TerminalWin.selectDelims:Ljava/lang/String;
        //   915: goto            950
        //   918: new             Ljava/util/NoSuchElementException;
        //   921: dup            
        //   922: new             Ljava/lang/StringBuffer;
        //   925: dup            
        //   926: invokespecial   java/lang/StringBuffer.<init>:()V
        //   929: ldc             "unknown terminal-property '"
        //   931: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   934: aload_1         /* key */
        //   935: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   938: ldc             "'"
        //   940: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   943: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   946: invokespecial   java/util/NoSuchElementException.<init>:(Ljava/lang/String;)V
        //   949: athrow         
        //   950: aload_0         /* this */
        //   951: getfield        mindbright/terminal/TerminalWin.props:Ljava/util/Properties;
        //   954: aload_1         /* key */
        //   955: aload_2         /* value */
        //   956: invokevirtual   java/util/Properties.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   959: pop            
        //   960: iload           isEqual
        //   962: ifne            974
        //   965: aload_0         /* this */
        //   966: iconst_1       
        //   967: putfield        mindbright/terminal/TerminalWin.propsChanged:Z
        //   970: aload_0         /* this */
        //   971: invokevirtual   mindbright/terminal/TerminalWin.updateMenus:()V
        //   974: return         
        //    Exceptions:
        //  throws java.lang.IllegalArgumentException
        //  throws java.util.NoSuchElementException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------
        //  235    32      7     e         Ljava/lang/NumberFormatException;
        //  282    27      7     sl        I
        //  314    32      7     e         Ljava/lang/NumberFormatException;
        //  567    3       7     c         Ljava/awt/Color;
        //  576    3       7     c         Ljava/awt/Color;
        //  581    10      8     e         Ljava/lang/NumberFormatException;
        //  591    63      7     c         Ljava/awt/Color;
        //  678    3       7     rg        I
        //  693    3       7     rg        I
        //  0      975     0     this      Lmindbright/terminal/TerminalWin;
        //  0      975     1     key       Ljava/lang/String;
        //  0      975     2     value     Ljava/lang/String;
        //  0      975     3     forceSet  Z
        //  34     941     4     i         I
        //  3      972     5     isEqual   Z
        //  11     964     6     val       Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  216    230    233    267    Ljava/lang/NumberFormatException;
        //  276    309    312    346    Ljava/lang/NumberFormatException;
        //  550    576    579    591    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Color getTermRGBColor(final String value) throws NumberFormatException {
        final int c1 = value.indexOf(44);
        final int c2 = value.lastIndexOf(44);
        if (c1 == -1 || c2 == -1) {
            throw new NumberFormatException();
        }
        final int r = Integer.parseInt(value.substring(0, c1).trim());
        final int g = Integer.parseInt(value.substring(c1 + 1, c2).trim());
        final int b = Integer.parseInt(value.substring(c2 + 1).trim());
        final Color c3 = new Color(r, g, b);
        return c3;
    }
    
    public static Color getTermColor(final String name) throws IllegalArgumentException {
        int i;
        for (i = 0; i < TerminalWin.termColors.length && !TerminalWin.termColorNames[i].equalsIgnoreCase(name); ++i) {}
        if (i == TerminalWin.termColors.length) {
            throw new IllegalArgumentException("Unknown color: " + name);
        }
        return TerminalWin.termColors[i];
    }
    
    public void setGeometry(final String geometry, final boolean doResize) throws IllegalArgumentException {
        final int delim = geometry.indexOf(120);
        int delX = geometry.indexOf(43);
        int delY = geometry.indexOf(45);
        if (delY != -1) {
            delX = ((delX > delY || delX == -1) ? delY : delX);
        }
        int co;
        int ro;
        int xSz;
        int ySz;
        try {
            if (delim == -1) {
                throw new Exception();
            }
            co = Integer.parseInt(geometry.substring(0, delim).trim());
            ro = Integer.parseInt(geometry.substring(delim + 1, (delX == -1) ? geometry.length() : delX).trim());
            xSz = 2 * this.borderWidth + this.charWidth * co;
            ySz = 2 * this.borderHeight + this.charHeight * ro;
            if (delX != -1) {
                delY = geometry.indexOf(43, delX + 1);
                if (delY == -1) {
                    delY = geometry.indexOf(45, delX + 1);
                    if (delY == -1) {
                        throw new Exception();
                    }
                }
                final Dimension sDim = Toolkit.getDefaultToolkit().getScreenSize();
                final Insets fIns = this.ownerFrame.getInsets();
                final int sbSz = this.haveScrollbar ? this.scrollbar.getSize().width : 0;
                int xPos = Integer.parseInt(geometry.substring(delX + 1, delY).trim());
                int yPos = Integer.parseInt(geometry.substring(delY + 1).trim());
                if (geometry.charAt(delX) == '-') {
                    xPos = sDim.width - xSz - xPos - fIns.left - fIns.right - sbSz;
                }
                if (geometry.charAt(delY) == '-') {
                    yPos = sDim.height - ySz - yPos - fIns.top - fIns.bottom;
                }
                this.savedGeomPos = geometry.substring(delX).trim();
                if (this.isShowing()) {
                    this.ownerFrame.setLocation(xPos, yPos);
                }
            }
            else {
                this.savedGeomPos = "";
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("geometry must be '<cols>x<rows>[position]', e.g. '80x24+0-0'");
        }
        if (doResize) {
            this.setSize(xSz, ySz);
            if (this.isShowing()) {
                this.componentResized(null);
                this.ownerFrame.pack();
                this.requestFocus();
            }
            else {
                this.pendingShow = true;
                this.setWindowSize(ro, co);
                this.resetWindow();
                this.clearScreen();
            }
        }
    }
    
    public String getProperty(final String key) {
        return this.props.getProperty(key);
    }
    
    private final void setFont(final String name, final int size) {
        this.plainFont = new Font(name, 0, size);
        this.boldFont = new Font(name, 1, size);
        super.setFont(this.plainFont);
        this.getDimensionOfText(0, 0);
        if (this.isShowing()) {
            this.componentResized(null);
        }
    }
    
    public void setFont(final Font font) {
        this.setFont(font.getName(), font.getSize());
    }
    
    public void setTitle(final String title) {
        this.title = title;
        this.signalWindowChanged(this.rows, this.cols, this.vpixels, this.hpixels);
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setPopupButton(final int buttonNum) {
        switch (buttonNum) {
            case 1: {
                this.popupButton = 16;
                break;
            }
            case 2: {
                this.popupButton = 8;
                break;
            }
            case 3: {
                this.popupButton = 4;
                break;
            }
        }
    }
    
    public PopupMenu getPopupMenu(final String header) {
        if (this.popupmenu != null) {
            return this.popupmenu;
        }
        this.add(this.popupmenu = new PopupMenu(header));
        return this.popupmenu;
    }
    
    void updateScrollbarValues() {
        if (this.haveScrollbar) {
            this.scrollbar.setValues(this.visTop, this.rows, 0, this.saveVisTop + this.rows);
            this.scrollbar.setBlockIncrement(this.rows);
        }
    }
    
    public Panel getPanelWithScrollbar() {
        if (this.myPanel != null) {
            return this.myPanel;
        }
        this.haveScrollbar = true;
        this.scrollbar = new FixedScrollbar(1);
        this.updateScrollbarValues();
        this.scrollbar.addAdjustmentListener(this);
        (this.myPanel = new Panel(new BorderLayout())).add(this, "Center");
        final String sb = this.getProperty("sb");
        if (sb.equals("left")) {
            this.myPanel.add(this.scrollbar, "West");
        }
        else if (sb.equals("right")) {
            this.myPanel.add(this.scrollbar, "East");
        }
        else {
            this.haveScrollbar = false;
        }
        if (this.haveScrollbar) {
            this.scrollbar.setWindowSide(sb);
        }
        return this.myPanel;
    }
    
    private final void setSaveLines(int n) {
        final int oldSaveLines = this.saveLines;
        boolean outOfMemory = false;
        n = ((n < 0) ? 0 : n);
        n = ((n > 8192) ? 8192 : n);
        if (this.saveLines != n) {
            final char[][] oldScreen = this.screen;
            final int[][] oldAttributes = this.attributes;
            this.saveLines = n;
            try {
                this.setWindowSize(this.rows, this.cols);
            }
            catch (OutOfMemoryError e) {
                this.saveLines = oldSaveLines;
                this.setWindowSize(this.rows, this.cols);
                outOfMemory = true;
            }
            final int toRow = 0;
            int fromRow;
            int copyRows;
            if (oldSaveLines < this.saveLines) {
                fromRow = 0;
                copyRows = oldSaveLines + this.rows;
            }
            else if (this.saveVisTop <= this.saveLines) {
                fromRow = 0;
                copyRows = this.saveVisTop + this.rows;
            }
            else {
                fromRow = this.saveVisTop - this.saveLines;
                copyRows = this.saveLines + this.rows;
                this.saveVisTop -= fromRow;
            }
            System.arraycopy(oldScreen, fromRow, this.screen, toRow, copyRows);
            System.arraycopy(oldAttributes, fromRow, this.attributes, toRow, copyRows);
            this.visTop = this.saveVisTop;
            this.updateScrollbarValues();
            if (outOfMemory) {
                outOfMemory = false;
                this.write("\n\rOut of memory allocating scrollback buffer, reverting to " + this.saveLines + " lines!");
            }
        }
    }
    
    public void clearSaveLines() {
        final char[][] oldScreen = this.screen;
        final int[][] oldAttributes = this.attributes;
        this.setWindowSize(this.rows, this.cols);
        final int fromRow = this.saveVisTop;
        System.arraycopy(oldScreen, this.saveVisTop, this.screen, 0, this.rows);
        System.arraycopy(oldAttributes, this.saveVisTop, this.attributes, 0, this.rows);
        this.saveVisTop = 0;
        this.visTop = 0;
        this.updateScrollbarValues();
        this.makeAllDirty(true);
    }
    
    public void setWindowSize(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        this.screen = new char[rows + this.saveLines][cols];
        this.attributes = new int[rows + this.saveLines][cols];
    }
    
    public void setInterpreter(final TerminalInterpreter interpreter) {
        if (interpreter != null) {
            this.interpreter = interpreter;
        }
    }
    
    public String terminalType() {
        return this.interpreter.terminalType();
    }
    
    public int rows() {
        return this.rows;
    }
    
    public int cols() {
        return this.cols;
    }
    
    public int vpixels() {
        return this.vpixels;
    }
    
    public int hpixels() {
        return this.hpixels;
    }
    
    protected final void makeAllDirty(final boolean instantUpdate) {
        this.updateDirtyArea(0, 0, this.rows, this.cols);
        if (instantUpdate && this.isShowing()) {
            final Graphics g = this.getGraphics();
            if (g != null) {
                this.update(g);
            }
        }
        else {
            this.repaint();
        }
    }
    
    protected final void updateDirtyArea(final int top, final int left, final int bottom, final int right) {
        if (top < this.dirtyTop) {
            this.dirtyTop = top;
        }
        if (bottom > this.dirtyBottom) {
            this.dirtyBottom = bottom;
        }
        if (left < this.dirtyLeft) {
            this.dirtyLeft = left;
        }
        if (right > this.dirtyRight) {
            this.dirtyRight = right;
        }
    }
    
    public final synchronized void write(char c) {
        if (this.visTop != this.saveVisTop && this.termOptions[6]) {
            this.repaintPending = false;
            this.visTop = this.saveVisTop;
            if (this.haveScrollbar) {
                this.scrollbar.setValue(this.visTop);
            }
            this.updateDirtyArea(0, 0, this.rows, this.cols);
        }
        final int ic;
        if ((ic = this.interpreter.interpretChar(c)) != -1) {
            c = (char)ic;
            if (this.curCol == this.cols) {
                if (this.termOptions[1]) {
                    ++this.curRow;
                    this.curCol = 0;
                    if (this.curRow == this.windowBottom) {
                        this.scrollUp(1);
                        this.curRow = this.windowBottom - 1;
                    }
                }
                else {
                    --this.curCol;
                }
            }
            if (this.termOptions[3]) {
                this.insertChars(1);
            }
            this.updateDirtyArea(this.curRow, this.curCol, this.curRow + 1, this.curCol + 1);
            final int idxRow = this.visTop + this.curRow;
            this.attributes[idxRow][this.curCol] = this.curAttr;
            this.screen[idxRow][this.curCol++] = c;
        }
        this.repaint();
    }
    
    public final synchronized void write(final char[] c, final int off, final int len) {
        this.waitForMore = true;
        for (int end = off + len, i = off; i < end; ++i) {
            this.write(c[i]);
        }
    }
    
    public final synchronized void write(final byte[] c, final int off, final int len) {
        this.waitForMore = true;
        for (int end = off + len, i = off; i < end; ++i) {
            this.write((char)c[i]);
        }
    }
    
    public final void write(final String s) {
        final char[] carr = s.toCharArray();
        this.write(carr, 0, carr.length);
    }
    
    public void writeLineDrawChar(final char c) {
        if (this.curCol == this.cols) {
            if (this.termOptions[1]) {
                ++this.curRow;
                this.curCol = 0;
                if (this.curRow == this.windowBottom) {
                    this.scrollUp(1);
                    this.curRow = this.windowBottom - 1;
                }
            }
            else {
                --this.curCol;
            }
        }
        this.updateDirtyArea(this.curRow, this.curCol, this.curRow + 1, this.curCol + 1);
        final int idxRow = this.visTop + this.curRow;
        this.attributes[idxRow][this.curCol] = (this.curAttr | 0x100);
        this.screen[idxRow][this.curCol++] = c;
    }
    
    public void addTerminalListener(final TerminalListener listener) {
        this.listener = listener;
    }
    
    public void addTerminalClipboard(final TerminalClipboard clipboard) {
        this.clipboard = clipboard;
    }
    
    public final void sendBytes(final byte[] b) {
        if (this.listener != null) {
            try {
                this.listener.sendBytes(b);
            }
            catch (IOException ex) {}
        }
    }
    
    public void doBell() {
        if (this.termOptions[13]) {
            this.setOption(0, !this.termOptions[0]);
            this.setOption(0, !this.termOptions[0]);
        }
        else {
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            if (toolkit != null) {
                try {
                    toolkit.beep();
                }
                catch (Exception e) {
                    this.setOption(13, true);
                    this.doBell();
                }
            }
        }
    }
    
    public void doBS() {
        this.cursorBackward(1);
    }
    
    public void doTab() {
        if (this.curCol < this.windowRight) {
            int i;
            for (i = this.curCol + 1; i < this.windowRight && !this.tabStops[i]; ++i) {}
            this.curCol = ((i < this.windowRight) ? i : (this.windowRight - 1));
        }
    }
    
    public void doTabs(int n) {
        while (n-- > 0) {
            this.doTab();
        }
    }
    
    public void doBackTabs(int n) {
        if (this.curCol > 0 && n >= 0) {
            int i;
            for (i = this.curCol - 1; i >= 0 && (!this.tabStops[i] || --n != 0); --i) {}
            this.curCol = ((i < 0) ? 0 : i);
        }
    }
    
    public void setTab(final int col) {
        this.tabStops[col] = true;
    }
    
    public void clearTab(final int col) {
        this.tabStops[col] = false;
    }
    
    public void resetTabs() {
        for (int i = 0; i < 512; ++i) {
            if (i % 8 == 0) {
                this.tabStops[i] = true;
            }
            else {
                this.tabStops[i] = false;
            }
        }
    }
    
    public void clearAllTabs() {
        for (int i = 0; i < 512; ++i) {
            this.tabStops[i] = false;
        }
    }
    
    public void doCR() {
        this.curCol = this.windowLeft;
    }
    
    public void doLF() {
        ++this.curRow;
        if (this.curRow == this.windowBottom) {
            this.scrollUp(1);
            this.curRow = this.windowBottom - 1;
        }
        if (this.termOptions[11] && this.termOptions[4]) {
            this.doCR();
        }
    }
    
    public void resetInterpreter() {
        this.interpreter.vtReset();
        this.makeAllDirty(true);
    }
    
    public void resetWindow() {
        this.windowTop = 0;
        this.windowBottom = this.rows;
        this.windowLeft = 0;
        this.windowRight = this.cols;
        this.complexScroll = false;
    }
    
    public void setWindow(final int top, final int bottom) {
        this.setWindow(top, 0, bottom, this.cols);
    }
    
    public void setWindow(final int top, final int left, final int bottom, final int right) {
        this.windowTop = top;
        this.windowLeft = left;
        this.windowBottom = bottom;
        this.windowRight = right;
        if (this.hasSelection) {
            final int selRowAnch = this.selectRowAnchor - this.visTop;
            final int selRowLast = this.selectRowLast - this.visTop;
            if (top != 0 && (selRowAnch >= 0 || selRowLast >= 0)) {
                if ((selRowAnch >= top || selRowLast >= top) && (selRowAnch < bottom || selRowLast < bottom)) {
                    this.clearSelection();
                }
            }
            else if (selRowAnch >= bottom || selRowLast >= bottom) {
                this.clearSelection();
            }
        }
        if (this.windowLeft != 0 || this.windowRight != this.cols) {
            this.complexScroll = true;
        }
        else {
            this.complexScroll = false;
        }
    }
    
    public int getWindowTop() {
        return this.windowTop;
    }
    
    public int getWindowBottom() {
        return this.windowBottom;
    }
    
    public int getWindowLeft() {
        return this.windowLeft;
    }
    
    public int getWindowRight() {
        return this.windowRight;
    }
    
    public int getCursorV() {
        return this.curRow;
    }
    
    public int getCursorH() {
        return this.curCol;
    }
    
    public void cursorSetPos(int v, int h, final boolean relative) {
        int maxV = this.rows - 1;
        int maxH = this.cols - 1;
        int minV = 0;
        int minH = 0;
        if (relative) {
            v += this.windowTop;
            maxV = this.windowBottom - 1;
            minV = this.windowTop;
            h += this.windowLeft;
            maxH = this.windowRight - 1;
            minH = this.windowLeft;
        }
        if (v < minV) {
            v = minV;
        }
        if (h < minH) {
            h = minH;
        }
        if (v > maxV) {
            v = maxV;
        }
        if (h > maxH) {
            h = maxH;
        }
        this.curRow = v;
        this.curCol = h;
    }
    
    public void cursorUp(final int n) {
        final int min = (this.curRow < this.windowTop) ? 0 : this.windowTop;
        this.curRow -= n;
        if (this.curRow < min) {
            this.curRow = min;
        }
    }
    
    public void cursorDown(final int n) {
        final int max = (this.curRow > this.windowBottom - 1) ? (this.rows - 1) : (this.windowBottom - 1);
        this.curRow += n;
        if (this.curRow > max) {
            this.curRow = max;
        }
    }
    
    public void cursorForward(final int n) {
        this.curCol += n;
        if (this.curCol > this.windowRight) {
            this.curCol = this.windowRight;
        }
    }
    
    public void cursorBackward(final int n) {
        this.curCol -= n;
        if (this.curCol < this.windowLeft) {
            if (this.termOptions[2]) {
                this.curCol = this.windowRight - (this.windowLeft - this.curCol);
                this.cursorUp(1);
            }
            else {
                this.curCol = this.windowLeft;
            }
        }
    }
    
    public void cursorIndex(final int n) {
        if (this.curRow > this.windowBottom || this.curRow + n < this.windowBottom) {
            this.cursorDown(n);
        }
        else {
            final int m = this.windowBottom - this.curRow;
            this.cursorDown(m);
            this.scrollUp(n - m + 1);
        }
    }
    
    public void cursorIndexRev(final int n) {
        if (this.curRow < this.windowTop || this.curRow - n >= this.windowTop) {
            this.cursorUp(n);
        }
        else {
            final int m = this.curRow - this.windowTop;
            this.scrollDown(n - m);
            this.cursorUp(m);
        }
    }
    
    public void cursorSave() {
        this.curRowSave = this.curRow;
        this.curColSave = this.curCol;
        this.curAttrSave = this.curAttr;
    }
    
    public void cursorRestore() {
        this.curRow = this.curRowSave;
        this.curCol = this.curColSave;
        this.curAttr = this.curAttrSave;
    }
    
    public void scrollUp(final int n) {
        final int windowHeight = this.windowBottom - this.windowTop;
        int j = this.windowTop;
        if (this.complexScroll) {
            if (n < windowHeight) {
                j = windowHeight - n + this.windowTop;
                for (int i = this.windowTop; i < j; ++i) {
                    System.arraycopy(this.screen[this.visTop + i + n], this.windowLeft, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                    System.arraycopy(this.attributes[this.visTop + i + n], this.windowLeft, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                }
            }
            for (int i = j; i < this.windowBottom; ++i) {
                System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                System.arraycopy(TerminalWin.zerorow, 0, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
            }
        }
        else if (this.windowTop == 0 && this.windowBottom == this.rows && this.saveLines > 0) {
            final int sl = (n < windowHeight) ? n : windowHeight;
            if (this.visTop + sl > this.saveLines) {
                if (this.hasSelection) {
                    if (this.selectRowAnchor - n >= 0 && this.selectRowLast - n >= 0) {
                        this.selectRowAnchor -= n;
                        this.selectRowLast -= n;
                    }
                    else {
                        this.clearSelection();
                    }
                }
                final int ll = windowHeight - sl;
                System.arraycopy(this.screen, sl, this.screen, 0, this.saveLines + ll);
                System.arraycopy(this.attributes, sl, this.attributes, 0, this.saveLines + ll);
                for (int i = windowHeight - sl; i < windowHeight; ++i) {
                    this.screen[this.saveLines + i] = new char[this.cols];
                    this.attributes[this.saveLines + i] = new int[this.cols];
                }
            }
            else {
                this.visTop += sl;
                this.saveVisTop = this.visTop;
                this.updateScrollbarValues();
            }
        }
        else {
            if (n < windowHeight) {
                j = windowHeight - n + this.windowTop;
                System.arraycopy(this.screen, this.visTop + this.windowTop + n, this.screen, this.visTop + this.windowTop, windowHeight - n);
                System.arraycopy(this.attributes, this.visTop + this.windowTop + n, this.attributes, this.visTop + this.windowTop, windowHeight - n);
            }
            for (int i = j; i < this.windowBottom; ++i) {
                this.screen[this.visTop + i] = new char[this.cols];
                this.attributes[this.visTop + i] = new int[this.cols];
            }
        }
        this.updateDirtyArea(this.windowTop, this.windowLeft, this.windowBottom, this.windowRight);
    }
    
    public void scrollDown(final int n) {
        final int windowHeight = this.windowBottom - this.windowTop;
        int j = this.windowBottom;
        if (this.complexScroll) {
            if (n < windowHeight) {
                j = this.windowTop + n;
                for (int i = this.windowBottom - 1; i >= j; --i) {
                    System.arraycopy(this.screen[this.visTop + i - n], this.windowLeft, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                    System.arraycopy(this.attributes[this.visTop + i - n], this.windowLeft, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                }
            }
            for (int i = this.windowTop; i < j; ++i) {
                System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                System.arraycopy(TerminalWin.zerorow, 0, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
            }
        }
        else {
            if (n < windowHeight) {
                j = this.windowTop + n;
                System.arraycopy(this.screen, this.visTop + this.windowTop, this.screen, this.visTop + this.windowTop + n, windowHeight - n);
                System.arraycopy(this.attributes, this.visTop + this.windowTop, this.attributes, this.visTop + this.windowTop + n, windowHeight - n);
            }
            for (int i = this.windowTop; i < j; ++i) {
                this.screen[this.visTop + i] = new char[this.cols];
                this.attributes[this.visTop + i] = new int[this.cols];
            }
        }
        this.updateDirtyArea(this.windowTop, 0, this.windowBottom, this.cols);
    }
    
    public void clearBelow() {
        this.clearRight();
        final int[] attrLine = new int[this.cols];
        for (int i = 0; i < this.cols; ++i) {
            attrLine[i] = (this.curAttr & 0xFFFF00C0);
        }
        for (int i = this.curRow + 1; i < this.windowBottom; ++i) {
            this.screen[this.visTop + i] = new char[this.cols];
            System.arraycopy(attrLine, 0, this.attributes[this.visTop + i] = new int[this.cols], 0, this.cols);
        }
        this.updateDirtyArea(this.curRow, 0, this.windowBottom, this.cols);
    }
    
    public void clearAbove() {
        this.clearLeft();
        final int[] attrLine = new int[this.cols];
        for (int i = 0; i < this.cols; ++i) {
            attrLine[i] = (this.curAttr & 0xFFFF00C0);
        }
        for (int i = this.windowTop; i < this.curRow; ++i) {
            this.screen[this.visTop + i] = new char[this.cols];
            System.arraycopy(attrLine, 0, this.attributes[this.visTop + i] = new int[this.cols], 0, this.cols);
        }
        this.updateDirtyArea(this.windowTop, 0, this.curRow, this.cols);
    }
    
    public void clearScreen() {
        final int[] attrLine = new int[this.cols];
        for (int i = 0; i < this.cols; ++i) {
            attrLine[i] = (this.curAttr & 0xFFFF00C0);
        }
        for (int i = this.windowTop; i < this.windowBottom; ++i) {
            this.screen[this.saveVisTop + i] = new char[this.cols];
            System.arraycopy(attrLine, 0, this.attributes[this.saveVisTop + i] = new int[this.cols], 0, this.cols);
        }
        this.dirtyTop = 0;
        this.dirtyBottom = this.rows;
        this.dirtyLeft = 0;
        this.dirtyRight = this.cols;
        this.repaint();
    }
    
    public void clearRight() {
        System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + this.curRow], this.curCol, this.cols - this.curCol);
        for (int i = this.curCol; i < this.cols; ++i) {
            this.attributes[this.visTop + this.curRow][i] = (this.curAttr & 0xFFFF00C0);
        }
        this.updateDirtyArea(this.curRow, this.curCol, this.curRow + 1, this.cols);
    }
    
    public void clearLeft() {
        System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + this.curRow], 0, this.curCol);
        for (int i = 0; i < this.curCol; ++i) {
            this.attributes[this.visTop + this.curRow][i] = (this.curAttr & 0xFFFF00C0);
        }
        this.dirtyLeft = 0;
        this.updateDirtyArea(this.curRow, 0, this.curRow + 1, this.curCol);
    }
    
    public void clearLine() {
        this.screen[this.visTop + this.curRow] = new char[this.cols];
        this.attributes[this.visTop + this.curRow] = new int[this.cols];
        for (int i = 0; i < this.cols; ++i) {
            this.attributes[this.visTop + this.curRow][i] = (this.curAttr & 0xFFFF00C0);
        }
        this.dirtyLeft = 0;
        this.dirtyRight = this.cols;
        this.updateDirtyArea(this.curRow, 0, this.curRow + 1, this.cols);
    }
    
    public void eraseChars(int n) {
        if (n > this.cols - this.curCol) {
            n = this.cols - this.curCol;
        }
        System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + this.curRow], this.curCol, n);
        for (int i = 0; i < n; ++i) {
            this.attributes[this.visTop + this.curRow][this.curCol + i] = (this.curAttr & 0xFFFF00C0);
        }
        this.updateDirtyArea(this.curRow, this.curCol, this.curRow, this.curCol + n);
    }
    
    public void insertChars(final int n) {
        int edge = this.windowRight;
        if (this.curCol < this.windowLeft || this.curCol > this.windowRight) {
            return;
        }
        if (this.curCol + n < this.windowRight) {
            edge = this.curCol + n;
            System.arraycopy(this.screen[this.visTop + this.curRow], this.curCol, this.screen[this.visTop + this.curRow], edge, this.windowRight - edge);
            System.arraycopy(this.attributes[this.visTop + this.curRow], this.curCol, this.attributes[this.visTop + this.curRow], edge, this.windowRight - edge);
        }
        System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + this.curRow], this.curCol, edge - this.curCol);
        for (int i = this.curCol; i < edge; ++i) {
            this.attributes[this.visTop + this.curRow][i] = (this.curAttr & 0xFFFF00C0);
        }
        this.updateDirtyArea(this.curRow, this.curCol, this.curRow + 1, this.windowRight);
    }
    
    public void deleteChars(final int n) {
        int edge = this.curCol;
        if (this.curCol < this.windowLeft || this.curCol > this.windowRight) {
            return;
        }
        if (this.curCol + n < this.windowRight) {
            edge = this.windowRight - n;
            System.arraycopy(this.screen[this.visTop + this.curRow], this.curCol + n, this.screen[this.visTop + this.curRow], this.curCol, edge - this.curCol);
            System.arraycopy(this.attributes[this.visTop + this.curRow], this.curCol + n, this.attributes[this.visTop + this.curRow], this.curCol, edge - this.curCol);
        }
        System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + this.curRow], edge, this.windowRight - edge);
        for (int i = edge; i < this.windowRight; ++i) {
            this.attributes[this.visTop + this.curRow][i] = (this.curAttr & 0xFFFF00C0);
        }
        this.updateDirtyArea(this.curRow, this.curCol, this.curRow + 1, this.windowRight);
    }
    
    public void insertLines(final int n) {
        int edge = this.windowBottom;
        if (this.curRow < this.windowTop || this.curRow > this.windowBottom) {
            return;
        }
        if (this.complexScroll) {
            if (this.curRow + n < this.windowBottom) {
                edge = this.curRow + n;
                for (int i = this.windowBottom - 1; i >= edge; --i) {
                    System.arraycopy(this.screen[this.visTop + i - n], this.windowLeft, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                    System.arraycopy(this.attributes[this.visTop + i - n], this.windowLeft, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                }
            }
            for (int i = this.curRow; i < edge; ++i) {
                System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                System.arraycopy(TerminalWin.zerorow, 0, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
            }
        }
        else {
            if (this.curRow + n < this.windowBottom) {
                edge = this.curRow + n;
                System.arraycopy(this.screen, this.visTop + this.curRow, this.screen, this.visTop + edge, this.windowBottom - edge);
                System.arraycopy(this.attributes, this.visTop + this.curRow, this.attributes, this.visTop + edge, this.windowBottom - edge);
            }
            final int[] attrLine = new int[this.cols];
            for (int i = 0; i < this.cols; ++i) {
                attrLine[i] = (this.curAttr & 0xFFFF00C0);
            }
            for (int i = this.curRow; i < edge; ++i) {
                this.screen[this.visTop + i] = new char[this.cols];
                System.arraycopy(attrLine, 0, this.attributes[this.visTop + i] = new int[this.cols], 0, this.cols);
            }
        }
        this.updateDirtyArea(this.curRow, 0, this.windowBottom, this.cols);
    }
    
    public void deleteLines(final int n) {
        int edge = this.curRow;
        if (this.curRow < this.windowTop || this.curRow > this.windowBottom) {
            return;
        }
        if (this.complexScroll) {
            if (this.curRow + n < this.windowBottom) {
                edge = this.windowBottom - n - 1;
                for (int i = this.curRow; i <= edge; ++i) {
                    System.arraycopy(this.screen[this.visTop + i + n], this.windowLeft, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                    System.arraycopy(this.attributes[this.visTop + i + n], this.windowLeft, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                }
            }
            for (int i = edge; i < this.windowBottom; ++i) {
                System.arraycopy(TerminalWin.spacerow, 0, this.screen[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
                System.arraycopy(TerminalWin.zerorow, 0, this.attributes[this.visTop + i], this.windowLeft, this.windowRight - this.windowLeft);
            }
        }
        else {
            if (this.curRow + n < this.windowBottom) {
                edge = this.windowBottom - n;
                System.arraycopy(this.screen, this.visTop + this.curRow + n, this.screen, this.visTop + this.curRow, edge - this.curRow);
                System.arraycopy(this.attributes, this.visTop + this.curRow + n, this.attributes, this.visTop + this.curRow, edge - this.curRow);
            }
            final int[] attrLine = new int[this.cols];
            for (int i = 0; i < this.cols; ++i) {
                attrLine[i] = (this.curAttr & 0xFFFF00C0);
            }
            for (int i = edge; i < this.windowBottom; ++i) {
                this.screen[this.visTop + i] = new char[this.cols];
                System.arraycopy(attrLine, 0, this.attributes[this.visTop + i] = new int[this.cols], 0, this.cols);
            }
        }
        this.updateDirtyArea(this.curRow, 0, this.windowBottom, this.cols);
    }
    
    public void setOption(final int opt, final boolean val) {
        if (opt > this.termOptions.length || opt < 0) {
            return;
        }
        ((Hashtable<String, String>)this.props).put(TerminalDefProps.defaultPropDesc[opt][0], String.valueOf(val));
        switch (opt) {
            case 0: {
                if (val != this.termOptions[opt]) {
                    this.termOptions[opt] = val;
                    final Color swap = this.origBgColor;
                    this.origBgColor = this.origFgColor;
                    this.origFgColor = swap;
                    this.makeAllDirty(true);
                    break;
                }
                break;
            }
            case 9: {
                this.repaint();
            }
            case 15: {
                if (this.termOptions[opt] != val && this.termOptions[16]) {
                    this.setProperty("gm", (val ? 132 : 80) + "x" + this.rows + this.savedGeomPos);
                    this.cursorSetPos(0, 0, false);
                    break;
                }
                break;
            }
            case 16: {
                if (this.menuHandler != null) {
                    this.menuHandler.setEnabledOpt(15, val);
                    break;
                }
                break;
            }
        }
        this.termOptions[opt] = val;
        if (this.menuHandler != null) {
            this.menuHandler.setStateOpt(opt, val);
        }
    }
    
    public boolean getOption(final int opt) {
        return opt <= this.termOptions.length && opt >= 0 && this.termOptions[opt];
    }
    
    public void setAttribute(final int attr, final boolean val) {
        if (val) {
            this.curAttr |= attr;
        }
        else {
            this.curAttr &= ~attr;
        }
    }
    
    public boolean getAttribute(final int attr) {
        return (this.curAttr & attr) == attr;
    }
    
    public void setForegroundColor(int c) {
        if (c >= 0 && c < 8) {
            if ((this.curAttr & 0x1) != 0x0) {
                c += 8;
            }
            this.curAttr &= 0xFF00FFBF;
            this.curAttr |= (0x40 | c << 16);
        }
        else {
            this.curAttr &= 0xFFFFFFBF;
        }
    }
    
    public void setBackgroundColor(final int c) {
        if (c >= 0 && c < 8) {
            this.curAttr &= 0xFFFF7F;
            this.curAttr |= (0x80 | c << 24);
        }
        else {
            this.curAttr &= 0xFFFFFF7F;
        }
    }
    
    public void clearAllAttributes() {
        this.curAttr = 32768;
    }
    
    public void signalWindowChanged(final int rows, final int cols, final int vpixels, final int hpixels) {
        if (this.listener != null) {
            this.listener.signalWindowChanged(rows, cols, vpixels, hpixels);
        }
    }
    
    public void keyTyped(final KeyEvent e) {
        char c = e.getKeyChar();
        switch (c) {
            case '~': {
                if (!this.tildePressedKludge) {
                    this.tildeTypedKludge = true;
                    c = '~';
                    if (this.termOptions[11]) {
                        this.write(c);
                    }
                    if (this.listener != null) {
                        try {
                            this.listener.typedChar(c);
                        }
                        catch (IOException ioe) {}
                    }
                    else {
                        this.typedChar(c);
                    }
                }
            }
            default: {}
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        final int virtKey = e.getKeyCode();
        final int mod = e.getModifiers();
        char c = e.getKeyChar();
        final int gMode = 0;
        if (this.specialKeyHandler(c, virtKey, mod)) {
            return;
        }
        this.interpreter.keyHandler(virtKey, mod);
        if (c != '\uffff' && c != '\0') {
            final int transC;
            if ((transC = this.keyKludgeFilter(c, virtKey, mod)) == -1) {
                return;
            }
            c = (char)transC;
            if (this.termOptions[11]) {
                this.write(c);
            }
            if (this.listener != null) {
                try {
                    if ((c == '\n' || c == '\r') && this.termOptions[4]) {
                        c = '\n';
                        this.listener.typedChar('\r');
                    }
                    this.listener.typedChar(c);
                }
                catch (IOException ioe) {}
            }
            else {
                this.typedChar(c);
            }
            if (this.visTop != this.saveVisTop && this.termOptions[5]) {
                this.visTop = this.saveVisTop;
                if (this.haveScrollbar) {
                    this.scrollbar.setValue(this.visTop);
                }
                this.makeAllDirty(false);
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        final int virtKey = e.getKeyCode();
        switch (virtKey) {
            case 18: {
                this.metaKeyKludge = false;
                break;
            }
            case 17: {
                this.ctrlKeyKludge = false;
                break;
            }
        }
    }
    
    final boolean specialKeyHandler(final int c, final int virtKey, final int mod) {
        boolean keyProcessed = false;
        switch (virtKey) {
            case 18: {
                if (this.ctrlKeyKludge) {
                    this.ctrlKeyKludge = false;
                }
                else {
                    this.metaKeyKludge = true;
                }
                keyProcessed = true;
                break;
            }
            case 17: {
                this.ctrlKeyKludge = true;
                keyProcessed = true;
                break;
            }
            case 8: {
                final byte[] b = { this.bsCharacter };
                this.sendBytes(b);
                keyProcessed = true;
                break;
            }
            case 127: {
                final byte[] b = { this.delCharacter };
                this.sendBytes(b);
                keyProcessed = true;
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                if (mod == 1 || this.termOptions[7]) {
                    this.localPageCtrlKeys(virtKey);
                    keyProcessed = true;
                    break;
                }
                break;
            }
            case 155: {
                if (mod == 1) {
                    this.doPaste();
                    keyProcessed = true;
                    break;
                }
                if (mod == 2) {
                    this.doCopy();
                    keyProcessed = true;
                    break;
                }
                break;
            }
            case 16:
            case 20: {
                keyProcessed = true;
                break;
            }
        }
        return keyProcessed;
    }
    
    final int keyKludgeFilter(final char c, final int virtKey, final int mod) {
        int transC = c;
        if ((mod & 0x2) != 0x0) {
            switch (virtKey) {
                case 77: {
                    transC = 13;
                    break;
                }
                case 32: {
                    if (this.termOptions[14]) {
                        transC = 0;
                        break;
                    }
                    break;
                }
                default: {
                    if (virtKey >= 65 && virtKey <= 90) {
                        transC = this.ctrlAlphaKey(virtKey);
                        break;
                    }
                    if (c == '@') {
                        transC = 0;
                        break;
                    }
                    if (c == '[') {
                        transC = 27;
                        break;
                    }
                    if (c == '\\') {
                        transC = 28;
                        break;
                    }
                    if (c == ']') {
                        transC = 29;
                        break;
                    }
                    if (c == '^') {
                        transC = 30;
                        break;
                    }
                    if (c == '_') {
                        transC = 31;
                        break;
                    }
                    break;
                }
            }
        }
        else {
            if (transC == 10 && virtKey == 10 && !this.ctrlKeyKludge) {
                transC = 13;
            }
            else if (c == '~') {
                if (!this.tildeTypedKludge) {
                    this.tildePressedKludge = true;
                }
                else {
                    transC = -1;
                }
            }
            else if (c == '\uffff') {
                transC = -1;
            }
            else if (c == '\uff7e') {
                transC = -1;
            }
            if (transC != -1 && this.metaKeyKludge && this.listener != null) {
                try {
                    this.listener.typedChar('\u001b');
                }
                catch (IOException ex) {}
            }
        }
        return transC;
    }
    
    final int ctrlAlphaKey(final int virtKey) {
        int ctrlC = 0;
        switch (virtKey) {
            case 65: {
                ctrlC = 1;
                break;
            }
            case 66: {
                ctrlC = 2;
                break;
            }
            case 67: {
                ctrlC = 3;
                break;
            }
            case 68: {
                ctrlC = 4;
                break;
            }
            case 69: {
                ctrlC = 5;
                break;
            }
            case 70: {
                ctrlC = 6;
                break;
            }
            case 71: {
                ctrlC = 7;
                break;
            }
            case 72: {
                ctrlC = 8;
                break;
            }
            case 73: {
                ctrlC = 9;
                break;
            }
            case 74: {
                ctrlC = 10;
                break;
            }
            case 75: {
                ctrlC = 11;
                break;
            }
            case 76: {
                ctrlC = 12;
                break;
            }
            case 77: {
                ctrlC = 13;
                break;
            }
            case 78: {
                ctrlC = 14;
                break;
            }
            case 79: {
                ctrlC = 15;
                break;
            }
            case 80: {
                ctrlC = 16;
                break;
            }
            case 81: {
                ctrlC = 17;
                break;
            }
            case 82: {
                ctrlC = 18;
                break;
            }
            case 83: {
                ctrlC = 19;
                break;
            }
            case 84: {
                ctrlC = 20;
                break;
            }
            case 85: {
                ctrlC = 21;
                break;
            }
            case 86: {
                ctrlC = 22;
                break;
            }
            case 87: {
                ctrlC = 23;
                break;
            }
            case 88: {
                ctrlC = 24;
                break;
            }
            case 89: {
                ctrlC = 25;
                break;
            }
            case 90: {
                ctrlC = 26;
                break;
            }
        }
        return ctrlC;
    }
    
    public void typedChar(final char c) {
    }
    
    public final void localPageCtrlKeys(final int virtKey) {
        switch (virtKey) {
            case 33: {
                this.visTop -= this.rows;
                if (this.visTop < 0) {
                    this.visTop = 0;
                }
                this.updateScrollbarValues();
                this.makeAllDirty(true);
                break;
            }
            case 34: {
                this.visTop += this.rows;
                if (this.visTop > this.saveVisTop) {
                    this.visTop = this.saveVisTop;
                }
                this.updateScrollbarValues();
                this.makeAllDirty(true);
                break;
            }
            case 36: {
                this.visTop = 0;
                this.updateScrollbarValues();
                this.makeAllDirty(true);
                break;
            }
            case 35: {
                this.visTop = this.saveVisTop;
                this.updateScrollbarValues();
                this.makeAllDirty(true);
                break;
            }
        }
    }
    
    public void focusGained(final FocusEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(2));
        this.haveFocus = true;
        this.updateFocusCursor();
    }
    
    public void focusLost(final FocusEvent e) {
        this.metaKeyKludge = false;
        this.ctrlKeyKludge = false;
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.haveFocus = false;
        this.updateFocusCursor();
    }
    
    final synchronized void updateFocusCursor() {
        final Graphics g = this.getGraphics();
        if (g != null) {
            this.hideCursor(g);
            this.showCursor(g);
        }
    }
    
    public void componentMoved(final ComponentEvent e) {
    }
    
    public void emulateComponentShown() {
        this.componentShown(new ComponentEvent(this.ownerFrame, 0));
    }
    
    public synchronized void componentShown(final ComponentEvent e) {
        if (e.getComponent() == this.ownerFrame && this.pendingShow && this.pendingShow) {
            try {
                this.wait(500L);
            }
            catch (InterruptedException ex) {}
            this.pendingShow = false;
            this.setGeometry(this.getProperty("gm"), true);
        }
    }
    
    public void componentHidden(final ComponentEvent e) {
    }
    
    public synchronized void componentResized(final ComponentEvent e) {
        final Dimension dim = this.getSize();
        final int newCols = (dim.width - 2 * this.borderWidth) / this.charWidth;
        final int newRows = (dim.height - 2 * this.borderHeight) / this.charHeight;
        int oldCols = this.cols;
        final int oldRows = this.rows;
        final char[][] oldScreen = this.screen;
        final int[][] oldAttributes = this.attributes;
        if (this.pendingShow || (e != null && e.getComponent() != this) || newCols <= 0 || newRows <= 0) {
            return;
        }
        this.vpixels = dim.height;
        this.hpixels = dim.width;
        if (newCols != oldCols) {
            this.clearSelection();
        }
        if (newRows != this.rows || newCols != this.cols) {
            this.setWindowSize(newRows, newCols);
            this.resetWindow();
            this.clearScreen();
            oldCols = ((oldCols < newCols) ? oldCols : newCols);
            if (this.resizeGravity == 1) {
                for (int copyRows = ((oldRows < newRows) ? oldRows : newRows) + this.saveVisTop, i = 0; i < copyRows; ++i) {
                    System.arraycopy(oldScreen[i], 0, this.screen[i], 0, oldCols);
                    System.arraycopy(oldAttributes[i], 0, this.attributes[i], 0, oldCols);
                }
            }
            else {
                if (this.hasSelection) {
                    this.selectRowAnchor += newRows - oldRows;
                    this.selectRowLast += newRows - oldRows;
                }
                int copyRows2;
                int fromTop;
                int toTop;
                if (oldRows < newRows) {
                    final int linesAdd = newRows - oldRows;
                    copyRows2 = oldRows + this.saveVisTop;
                    fromTop = 0;
                    this.curRow += linesAdd;
                    if (this.saveVisTop - linesAdd < 0) {
                        toTop = linesAdd;
                    }
                    else {
                        toTop = 0;
                        this.visTop -= linesAdd;
                        this.saveVisTop -= linesAdd;
                    }
                }
                else {
                    final int linesLost = oldRows - newRows;
                    toTop = 0;
                    this.curRow -= linesLost;
                    if (this.curRow < 0) {
                        this.curRow = 0;
                    }
                    if (this.saveVisTop + linesLost > this.saveLines) {
                        copyRows2 = newRows + this.saveVisTop;
                        fromTop = linesLost;
                    }
                    else {
                        copyRows2 = oldRows + this.saveVisTop;
                        fromTop = 0;
                        this.visTop += linesLost;
                        this.saveVisTop += linesLost;
                    }
                }
                for (int j = 0; j < copyRows2; ++j) {
                    System.arraycopy(oldScreen[j + fromTop], 0, this.screen[j + toTop], 0, oldCols);
                    System.arraycopy(oldAttributes[j + fromTop], 0, this.attributes[j + toTop], 0, oldCols);
                }
            }
            if (this.curRow >= newRows) {
                this.curRow = newRows - 1;
            }
            if (this.curCol >= newCols) {
                this.curCol = newCols - 1;
            }
            if (this.lastCursorRow >= newRows || this.lastCursorCol >= newCols) {
                this.cursorDrawn = false;
                this.cursorHollow = false;
            }
            this.updateScrollbarValues();
            this.signalWindowChanged(this.rows, this.cols, this.vpixels, this.hpixels);
            this.memGraphics = null;
            final String newGM = this.cols + "x" + this.rows + this.savedGeomPos;
            this.propsChanged = true;
            ((Hashtable<String, String>)this.props).put("gm", newGM);
            this.updateMenus();
            this.makeAllDirty(false);
            this.requestFocus();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        final int adjValue = e.getValue();
        if (adjValue >= 0 && adjValue <= this.saveVisTop) {
            this.visTop = adjValue;
            this.makeAllDirty(false);
        }
    }
    
    public void selectAll() {
        this.selectRowAnchor = 0;
        this.selectColAnchor = 0;
        this.selectRowLast = this.saveVisTop + this.curRow;
        this.selectColLast = this.curCol;
        this.makeSelection(this.selectRowAnchor, this.selectColAnchor, this.selectRowLast, this.selectColLast);
        this.hasSelection = true;
        if (this.clipboard != null) {
            this.clipboard.selectionAvailable(true);
            if (this.termOptions[17]) {
                this.doCopy();
            }
        }
    }
    
    public void makeSelection(int startRow, final int startCol, int endRow, final int endCol) {
        if (startRow != endRow) {
            for (int i = startCol; i < this.cols; ++i) {
                final int[] array = this.attributes[startRow];
                final int n = i;
                array[n] |= 0x1000;
            }
            for (int i = startRow + 1; i < endRow; ++i) {
                for (int j = 0; j < this.cols; ++j) {
                    final int[] array2 = this.attributes[i];
                    final int n2 = j;
                    array2[n2] |= 0x1000;
                }
            }
            for (int i = 0; i <= endCol; ++i) {
                final int[] array3 = this.attributes[endRow];
                final int n3 = i;
                array3[n3] |= 0x1000;
            }
        }
        else {
            for (int i = startCol; i <= endCol; ++i) {
                final int[] array4 = this.attributes[startRow];
                final int n4 = i;
                array4[n4] |= 0x1000;
            }
        }
        startRow -= this.visTop;
        endRow -= this.visTop;
        if (startRow < 0) {
            startRow = 0;
        }
        if (endRow < 0) {
            endRow = 0;
        }
        this.updateDirtyArea(startRow, 0, endRow + 1, this.cols);
        this.repaint();
    }
    
    public void clearSelection(int startRow, final int startCol, int endRow, final int endCol) {
        if (startRow != endRow) {
            for (int i = startCol; i < this.cols; ++i) {
                final int[] array = this.attributes[startRow];
                final int n = i;
                array[n] &= 0xFFFFEFFF;
            }
            for (int i = startRow + 1; i < endRow; ++i) {
                for (int j = 0; j < this.cols; ++j) {
                    final int[] array2 = this.attributes[i];
                    final int n2 = j;
                    array2[n2] &= 0xFFFFEFFF;
                }
            }
            for (int i = 0; i <= endCol; ++i) {
                final int[] array3 = this.attributes[endRow];
                final int n3 = i;
                array3[n3] &= 0xFFFFEFFF;
            }
        }
        else {
            for (int i = startCol; i <= endCol; ++i) {
                final int[] array4 = this.attributes[startRow];
                final int n4 = i;
                array4[n4] &= 0xFFFFEFFF;
            }
        }
        startRow -= this.visTop;
        endRow -= this.visTop;
        if (startRow < 0) {
            startRow = 0;
        }
        if (endRow < 0) {
            endRow = 0;
        }
        this.updateDirtyArea(startRow, 0, endRow + 1, this.cols);
        this.repaint();
    }
    
    public void clearSelection() {
        if (!this.hasSelection) {
            return;
        }
        if (this.selectReverse) {
            this.clearSelection(this.selectRowLast, this.selectColLast, this.selectRowAnchor, this.selectColAnchor);
        }
        else {
            this.clearSelection(this.selectRowAnchor, this.selectColAnchor, this.selectRowLast, this.selectColLast);
        }
        this.hasSelection = false;
        if (this.clipboard != null) {
            this.clipboard.selectionAvailable(false);
        }
    }
    
    final int mouseRow(final int y) {
        int mouseRow = (y - this.borderHeight) / this.charHeight;
        if (mouseRow < 0) {
            mouseRow = 0;
        }
        else if (mouseRow >= this.rows) {
            mouseRow = this.rows - 1;
        }
        return mouseRow;
    }
    
    final int mouseCol(final int x) {
        int mouseCol = (x - this.borderWidth) / this.charWidth;
        if (mouseCol < 0) {
            mouseCol = 0;
        }
        else if (mouseCol >= this.cols) {
            mouseCol = this.cols - 1;
        }
        return mouseCol;
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getModifiers() == 16) {
            this.requestFocus();
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        final long now = System.currentTimeMillis();
        int mouseRow = this.mouseRow(e.getY());
        final int mouseCol = this.mouseCol(e.getX());
        if (e.getModifiers() == (this.popupButton | 0x2) && this.popupmenu != null) {
            this.ctrlKeyKludge = false;
            this.popupmenu.show(this, e.getX(), e.getY());
        }
        this.interpreter.mouseHandler(mouseRow, mouseCol, true, e.getModifiers());
        mouseRow += this.visTop;
        this.clearSelection();
        this.selectRowAnchor = mouseRow;
        this.selectColAnchor = mouseCol;
        this.selectRowLast = mouseRow;
        this.selectColLast = mouseCol;
        if (now - this.lastLeftClick < 250L) {
            this.doClickSelect(mouseRow, mouseCol);
        }
        else {
            this.selectClickRow = -1;
            this.selectClickState = false;
        }
        this.lastLeftClick = now;
    }
    
    public void mouseReleased(final MouseEvent e) {
        final int mouseRow = this.mouseRow(e.getY());
        final int mouseCol = this.mouseCol(e.getX());
        if (this.listener != null) {
            if (e.getModifiers() == 16) {
                if (this.hasSelection) {
                    this.clipboard.selectionAvailable(true);
                    if (this.termOptions[17]) {
                        this.doCopy();
                    }
                }
            }
            else if (e.getModifiers() == 8) {
                this.doPaste();
            }
        }
        this.interpreter.mouseHandler(mouseRow, mouseCol, false, e.getModifiers());
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public synchronized void mouseDragged(final MouseEvent e) {
        int mouseRow = (e.getY() - this.borderHeight) / this.charHeight;
        int mouseCol = (e.getX() - this.borderWidth) / this.charWidth;
        if (mouseRow < 0) {
            mouseRow = 0;
        }
        else if (mouseRow >= this.rows) {
            mouseRow = this.rows - 1;
        }
        if (mouseCol < 0) {
            mouseCol = 0;
        }
        else if (mouseCol >= this.cols) {
            mouseCol = this.cols - 1;
        }
        mouseRow += this.visTop;
        if (mouseRow == this.selectRowLast && mouseCol == this.selectColLast) {
            return;
        }
        final boolean backwardSelection = this.selectRowAnchor > mouseRow || (this.selectRowAnchor == mouseRow && mouseCol < this.selectColAnchor);
        if (backwardSelection != this.selectReverse) {
            if (this.selectReverse) {
                this.clearSelection(this.selectRowLast, this.selectColLast, this.selectRowAnchor, this.selectColAnchor);
            }
            else {
                this.clearSelection(this.selectRowAnchor, this.selectColAnchor, this.selectRowLast, this.selectColLast);
            }
            this.selectReverse = backwardSelection;
            this.selectRowLast = this.selectRowAnchor;
            this.selectColLast = this.selectColAnchor;
        }
        final boolean backwardsFromLast = this.selectRowLast > mouseRow || (this.selectRowLast == mouseRow && mouseCol < this.selectColLast);
        if (this.selectReverse) {
            if (backwardsFromLast) {
                this.makeSelection(mouseRow, mouseCol, this.selectRowLast, this.selectColLast);
            }
            else {
                this.clearSelection(this.selectRowLast, this.selectColLast, mouseRow, mouseCol);
            }
        }
        else if (backwardsFromLast) {
            this.clearSelection(mouseRow, mouseCol, this.selectRowLast, this.selectColLast);
        }
        else {
            this.makeSelection(this.selectRowLast, this.selectColLast, mouseRow, mouseCol);
        }
        this.selectReverse = backwardSelection;
        this.selectRowLast = mouseRow;
        this.selectColLast = mouseCol;
        if (this.selectRowAnchor == this.selectRowLast && this.selectColAnchor == this.selectColLast) {
            this.hasSelection = false;
        }
        else {
            this.hasSelection = true;
        }
    }
    
    final int nextPrintedChar(final int row, final int col) {
        int i;
        for (i = col; i < this.cols && this.screen[row][i] == '\0'; ++i) {}
        return i;
    }
    
    final int prevPrintedChar(final int row, final int col) {
        int i;
        for (i = col; i >= 0 && this.screen[row][i] == '\0'; --i) {}
        return i;
    }
    
    final String addSpaces(final int start, final int end) {
        String res = "";
        int n = end - start;
        if (end == this.cols) {
            n = -1;
        }
        for (int i = 0; i <= n; ++i) {
            res += " ";
        }
        return res;
    }
    
    public String getSelection() {
        if (!this.hasSelection) {
            return null;
        }
        String eol;
        if (this.termOptions[8]) {
            eol = "\r\n";
        }
        else {
            eol = "\r";
        }
        int startRow;
        int startCol;
        int endRow;
        int endCol;
        if (this.selectReverse) {
            startRow = this.selectRowLast;
            startCol = this.selectColLast;
            endRow = this.selectRowAnchor;
            endCol = this.selectColAnchor;
        }
        else {
            startRow = this.selectRowAnchor;
            startCol = this.selectColAnchor;
            endRow = this.selectRowLast;
            endCol = this.selectColLast;
        }
        String result = "";
        if (startRow != endRow) {
            int i;
            for (i = startCol; i < this.cols; ++i) {
                if (this.screen[startRow][i] == '\0') {
                    final int n = this.nextPrintedChar(startRow, i);
                    result += this.addSpaces(i, n);
                    i = n - 1;
                }
                else {
                    result += this.screen[startRow][i];
                }
            }
            if (i == this.cols) {
                result += eol;
            }
            for (i = startRow + 1; i < endRow; ++i) {
                for (int j = 0; j < this.cols; ++j) {
                    if (this.screen[i][j] == '\0') {
                        final int n = this.nextPrintedChar(i, j);
                        result += this.addSpaces(j, n);
                        j = n - 1;
                    }
                    else {
                        result += this.screen[i][j];
                    }
                }
                result += eol;
            }
            for (i = 0; i <= endCol; ++i) {
                if (this.screen[endRow][i] == '\0') {
                    final int n = this.nextPrintedChar(endRow, i);
                    result += this.addSpaces(i, n);
                    i = n - 1;
                }
                else {
                    result += this.screen[endRow][i];
                }
            }
            if (i == this.cols) {
                result += eol;
            }
        }
        else {
            int i;
            for (i = startCol; i <= endCol; ++i) {
                if (this.screen[startRow][i] == '\0') {
                    final int n = this.nextPrintedChar(startRow, i);
                    result += this.addSpaces(i, n);
                    i = n - 1;
                }
                else {
                    result += this.screen[startRow][i];
                }
            }
            if (i == this.cols) {
                result += eol;
            }
        }
        return result;
    }
    
    public void doClickSelect(final int row, final int col) {
        if (this.selectClickRow == row && this.selectClickState) {
            this.selectColAnchor = 0;
            this.selectColLast = this.cols - 1;
        }
        else {
            if (this.screen[row][col] != '\0') {
                int i;
                for (i = col; i >= 0 && this.selectDelims.indexOf(this.screen[row][i]) == -1 && this.screen[row][i] != '\0'; --i) {}
                this.selectColAnchor = i + 1;
                for (i = col; i < this.cols && this.selectDelims.indexOf(this.screen[row][i]) == -1 && this.screen[row][i] != '\0'; ++i) {}
                this.selectColLast = i - 1;
            }
            else {
                this.selectColAnchor = this.prevPrintedChar(row, col) + 1;
                this.selectColLast = this.nextPrintedChar(row, col) - 1;
            }
            this.selectColAnchor = ((this.selectColAnchor > col) ? col : this.selectColAnchor);
            this.selectColLast = ((this.selectColLast < col) ? col : this.selectColLast);
        }
        this.selectClickState = !this.selectClickState;
        this.selectClickRow = row;
        this.selectRowAnchor = row;
        this.selectRowLast = row;
        this.selectReverse = false;
        this.hasSelection = true;
        this.makeSelection(this.selectRowAnchor, this.selectColAnchor, this.selectRowLast, this.selectColLast);
    }
    
    public void doCopy() {
        if (this.clipboard != null) {
            this.clipboard.setSelection(this.getSelection());
        }
    }
    
    public void doPaste() {
        if (this.clipboard != null) {
            final String selection = this.clipboard.getSelection();
            if (selection != null) {
                if (this.termOptions[11]) {
                    this.write(selection);
                }
                this.sendBytes(selection.getBytes());
            }
        }
    }
    
    public Dimension getDimensionOfText(final int rows, final int cols) {
        final FontMetrics fm = this.getFontMetrics(this.getFont());
        this.charWidth = -1;
        this.charHeight = fm.getHeight();
        this.charMaxAscent = fm.getMaxAscent();
        this.charMaxDescent = fm.getMaxDescent();
        this.charLeading = fm.getLeading();
        this.baselineIndex = this.charMaxAscent + this.charLeading - 1;
        if (this.charWidth == -1) {
            this.charWidth = fm.charWidth('W');
        }
        return new Dimension(cols * this.charWidth + 2 * this.borderHeight, rows * this.charHeight + 2 * this.borderWidth);
    }
    
    public Dimension getPreferredSize() {
        final Dimension dim = this.getDimensionOfText(this.rows, this.cols);
        return dim;
    }
    
    public Dimension getMinimumSize() {
        return this.getDimensionOfText(2, 8);
    }
    
    public Dimension getMaximumSize() {
        return this.getDimensionOfText(512, 512);
    }
    
    protected final void clearDirtyArea(final Graphics source, final Graphics dest) {
        final boolean clearAll = this.dirtyLeft == 0 && this.dirtyRight == this.cols && this.dirtyTop == 0 && this.dirtyBottom == this.rows;
        int x;
        int y;
        int w;
        int h;
        if (clearAll) {
            final Dimension dim = this.getSize();
            x = 0;
            y = 0;
            w = dim.width;
            h = dim.height;
        }
        else {
            x = this.borderWidth + this.charWidth * this.dirtyLeft;
            y = this.borderHeight + this.dirtyTop * this.charHeight;
            w = this.charWidth * (this.dirtyRight - this.dirtyLeft);
            h = this.charHeight * (this.dirtyBottom - this.dirtyTop);
        }
        source.setColor(this.origBgColor);
        source.fillRect(x, y, w, h);
        source.setColor(this.origFgColor);
        dest.setClip(x, y, w, h);
    }
    
    public void repaint() {
        if (!this.repaintPending && this.isShowing() && !this.pendingShow) {
            super.repaint();
            this.repaintPending = true;
        }
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    final Rectangle getClipRect(final Graphics g) {
        Rectangle clipRect = g.getClipBounds();
        if (clipRect == null) {
            final Dimension winSize = this.getSize();
            clipRect = new Rectangle(0, 0, winSize.width, winSize.height);
        }
        return clipRect;
    }
    
    public synchronized void update(final Graphics g) {
        if (this.hpixels == 0 || this.vpixels == 0) {
            final Dimension dim = this.getSize();
            this.vpixels = dim.height;
            this.hpixels = dim.width;
            if (this.hpixels == 0 || this.vpixels == 0) {
                return;
            }
        }
        int wcnt = 1;
        while (this.waitForMore) {
            this.waitForMore = false;
            try {
                this.wait(wcnt * 25);
            }
            catch (InterruptedException ex) {}
            if (wcnt++ > 3) {
                this.waitForMore = false;
            }
        }
        if (this.memGraphics == null || this.memImageSize == null || this.hpixels != this.memImageSize.width || this.vpixels != this.memImageSize.height) {
            this.memImageSize = new Dimension(this.hpixels, this.vpixels);
            this.memImage = this.createImage(this.hpixels, this.vpixels);
        }
        this.memGraphics = this.memImage.getGraphics();
        Rectangle clipRect;
        if (!this.repaintPending) {
            this.dirtyTop = 0;
            this.dirtyBottom = this.rows;
            this.dirtyLeft = 0;
            this.dirtyRight = this.cols;
            clipRect = this.getClipRect(g);
            this.memGraphics.setClip(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
            this.memGraphics.setColor(this.origBgColor);
            this.memGraphics.fillRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
            this.memGraphics.setColor(this.origFgColor);
        }
        else {
            if (this.dirtyTop == this.rows) {
                this.dirtyTop = this.curRow;
            }
            if (this.dirtyBottom == 0) {
                this.dirtyBottom = this.curRow + 1;
            }
            if (this.dirtyRight == 0) {
                this.dirtyRight = this.curCol + 1;
            }
            if (this.dirtyLeft == this.cols) {
                this.dirtyLeft = this.curCol;
            }
            this.clearDirtyArea(this.memGraphics, g);
            clipRect = this.getClipRect(g);
        }
        for (int i = this.dirtyTop; i < this.dirtyBottom; ++i) {
            final int y = this.borderHeight + i * this.charHeight;
            final int[] attrRow = this.attributes[this.visTop + i];
            final char[] charRow = this.screen[this.visTop + i];
            for (int j = this.dirtyLeft; j < this.dirtyRight; ++j) {
                final int attr = attrRow[j];
                final int attrMasked = attr & 0xFFFF;
                if (attrMasked != 0) {
                    final int x = this.borderWidth + this.charWidth * j;
                    if ((attr & 0x10) != 0x0 ^ (attr & 0x1000) != 0x0) {
                        if ((attr & 0x40) != 0x0) {
                            this.memGraphics.setColor(TerminalWin.termColors[(attr & 0xFF0000) >>> 16]);
                        }
                        this.memGraphics.fillRect(x, y, this.charWidth, this.charHeight);
                        if ((attr & 0x80) != 0x0) {
                            this.memGraphics.setColor(TerminalWin.termColors[(attr & 0xFF000000) >>> 24]);
                        }
                        else {
                            this.memGraphics.setColor(this.origBgColor);
                        }
                    }
                    else {
                        if ((attr & 0x80) != 0x0) {
                            this.memGraphics.setColor(TerminalWin.termColors[(attr & 0xFF000000) >>> 24]);
                            this.memGraphics.fillRect(x, y, this.charWidth, this.charHeight);
                            this.memGraphics.setColor(this.origFgColor);
                        }
                        if ((attr & 0x40) != 0x0) {
                            this.memGraphics.setColor(TerminalWin.termColors[(attr & 0xFF0000) >>> 16]);
                        }
                    }
                    if ((attrMasked & 0x8000) != 0x0) {
                        if ((attr & 0x100) != 0x0) {
                            this.drawLineDrawChar(this.memGraphics, x, y, this.baselineIndex, charRow[j]);
                        }
                        else if ((attr & 0x1) != 0x0) {
                            this.memGraphics.setFont(this.boldFont);
                            this.memGraphics.drawChars(charRow, j, 1, x, y + this.baselineIndex);
                            this.memGraphics.setFont(this.plainFont);
                        }
                        else {
                            this.memGraphics.drawChars(charRow, j, 1, x, y + this.baselineIndex);
                        }
                        if ((attr & 0x4) != 0x0) {
                            this.memGraphics.drawLine(x, y + this.baselineIndex, x + this.charWidth, y + this.baselineIndex);
                        }
                    }
                    this.memGraphics.setColor(this.origFgColor);
                }
            }
        }
        g.drawImage(this.memImage, 0, 0, this);
        final Rectangle cursor = new Rectangle(this.borderWidth + this.charWidth * this.lastCursorCol, this.borderHeight + this.lastCursorRow * this.charHeight, this.charWidth, this.charHeight);
        if (!clipRect.intersects(cursor)) {
            g.setClip(0, 0, this.hpixels, this.vpixels);
            this.hideCursor(g);
        }
        else {
            final Rectangle intersection = clipRect.intersection(cursor);
            g.setClip(0, 0, this.hpixels, this.vpixels);
            if (!intersection.equals(cursor) && !this.insideUpdate) {
                g.setColor(this.origBgColor);
                g.fillRect(0, 0, this.hpixels, this.vpixels);
                g.setColor(this.origFgColor);
                this.insideUpdate = true;
                this.update(g);
                this.insideUpdate = false;
                return;
            }
        }
        this.showCursor(g);
        this.repaintPending = false;
        this.dirtyTop = this.rows;
        this.dirtyBottom = 0;
        this.dirtyLeft = this.cols;
        this.dirtyRight = 0;
    }
    
    final synchronized void hideCursor(final Graphics g) {
        if (this.cursorDrawn) {
            final int x = this.borderWidth + this.charWidth * this.lastCursorCol;
            final int y = this.borderHeight + this.lastCursorRow * this.charHeight;
            if ((this.attributes[this.visTop + this.lastCursorRow][this.lastCursorCol] & 0x10) != 0x0) {
                g.setColor(this.origFgColor);
            }
            else {
                g.setColor(this.origBgColor);
            }
            g.setXORMode(this.cursorColor);
            if (this.cursorHollow) {
                g.drawRect(x, y, this.charWidth, this.charHeight - 1);
            }
            else {
                g.fillRect(x, y, this.charWidth, this.charHeight);
            }
            g.setColor(this.origFgColor);
            g.setPaintMode();
            this.cursorDrawn = false;
        }
    }
    
    final synchronized void showCursor(final Graphics g) {
        if (this.termOptions[9] && this.curCol < this.cols && this.curRow < this.rows) {
            final int x = this.borderWidth + this.charWidth * this.curCol;
            final int y = this.borderHeight + this.curRow * this.charHeight;
            g.setColor(this.cursorColor);
            if ((this.attributes[this.visTop + this.curRow][this.curCol] & 0x10) != 0x0) {
                g.setXORMode(this.origFgColor);
            }
            else {
                g.setXORMode(this.origBgColor);
            }
            if (this.haveFocus) {
                g.fillRect(x, y, this.charWidth, this.charHeight);
                this.cursorHollow = false;
            }
            else {
                g.drawRect(x, y, this.charWidth, this.charHeight - 1);
                this.cursorHollow = true;
            }
            g.setPaintMode();
            this.cursorDrawn = true;
            this.lastCursorRow = this.curRow;
            this.lastCursorCol = this.curCol;
        }
    }
    
    final void drawLineDrawChar(final Graphics g, final int x, final int y, final int bi, final char c) {
        final int x2 = x + this.charWidth / 2;
        final int y2 = y + this.charHeight / 2;
        final int xx = x + this.charWidth;
        final int yy = y + this.charHeight;
        switch (c) {
            case '}': {
                final char[] ca = { '£' };
                g.drawChars(ca, 0, 1, x, y + bi);
            }
            case '.': {}
            case ',': {}
            case '+': {}
            case '-': {}
            case 'h': {}
            case '~': {}
            case 'f': {
                final char[] ca = { '°' };
                g.drawChars(ca, 0, 1, x, y + bi);
                break;
            }
            case '`': {
                final int[] polyX = new int[4];
                final int[] polyY = new int[4];
                polyX[0] = x2;
                polyY[0] = y;
                polyX[1] = xx;
                polyY[1] = y2;
                polyX[2] = x2;
                polyY[2] = yy;
                polyX[3] = x;
                polyY[3] = y2;
                g.fillPolygon(polyX, polyY, 4);
            }
            case 'z': {}
            case '{': {}
            case 'i': {}
            case 'y': {}
            case 'g': {
                final char[] ca2 = { '±' };
                g.drawChars(ca2, 0, 1, x, y + bi);
                break;
            }
            case 'o': {
                g.drawLine(x, y, xx, y);
            }
            case 'p': {}
            case 's': {
                g.drawLine(x, yy, xx, yy);
            }
            case 'l': {
                g.drawLine(x2, yy, x2, y2);
                g.drawLine(x2, y2, xx, y2);
                break;
            }
            case 'k': {
                g.drawLine(x, y2, x2, y2);
                g.drawLine(x2, y2, x2, yy);
                break;
            }
            case 'm': {
                g.drawLine(x2, y, x2, y2);
                g.drawLine(x2, y2, xx, y2);
                break;
            }
            case 'j': {
                g.drawLine(x2, y, x2, y2);
                g.drawLine(x2, y2, x, y2);
                break;
            }
            case 'q': {
                g.drawLine(x, y2, xx, y2);
                break;
            }
            case 'x': {
                g.drawLine(x2, y, x2, yy);
                break;
            }
            case 'n': {
                g.drawLine(x2, y, x2, yy);
                g.drawLine(x, y2, xx, y2);
                break;
            }
            case 'u': {
                g.drawLine(x2, y, x2, yy);
                g.drawLine(x, y2, x2, y2);
                break;
            }
            case 't': {
                g.drawLine(x2, y, x2, yy);
                g.drawLine(x2, y2, xx, y2);
                break;
            }
            case 'v': {
                g.drawLine(x, y2, xx, y2);
                g.drawLine(x2, y2, x2, y);
                break;
            }
            case 'w': {
                g.drawLine(x, y2, xx, y2);
                g.drawLine(x2, y2, x2, yy);
                break;
            }
        }
    }
    
    static {
        termColors = new Color[] { Color.black, Color.red.darker(), Color.green.darker(), Color.yellow.darker(), Color.blue.darker(), Color.magenta.darker(), Color.cyan.darker(), Color.white, Color.darkGray, Color.red, Color.green, Color.yellow, Color.blue, Color.magenta, Color.cyan, Color.white };
        termColorNames = new String[] { "black", "red", "green", "yellow", "blue", "magenta", "cyan", "white", "i_black", "i_red", "i_green", "i_ yellow", "i_blue", "i_magenta", "i_cyan", "i_white" };
        spacerow = new char[512];
        zerorow = new int[512];
        for (int i = 0; i < 512; ++i) {
            TerminalWin.spacerow[i] = ' ';
            TerminalWin.zerorow[i] = 0;
        }
    }
    
    class FixedScrollbar extends Scrollbar
    {
        protected boolean onRight;
        
        public FixedScrollbar(final int orientation) {
            super(orientation);
            this.onRight = true;
        }
        
        public void setWindowSide(final String sb) {
            this.onRight = sb.equals("right");
        }
    }
}
