// 
// Decompiled by Procyon v0.5.30
// 

package MSBTree;

import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentListener;
import netscape.javascript.JSObject;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.Scrollbar;
import java.awt.Image;
import java.awt.Panel;

public class RTreeList extends Panel implements Runnable
{
    public Image backImage;
    Image backImage2;
    treeCanvas drawArea;
    Scrollbar Scroll;
    private Vector ImNames;
    private Vector Images;
    public static final int EXPAND_ON_CLICK = 0;
    public static final int EXPAND_ON_ENTER = 1;
    public int expandOn;
    public static final int NO_ANIMATION = 0;
    public static final int ANIMATION_FROM_TOP = 1;
    public static final int ANIMATION_FROM_BOTTOM = 2;
    public static final int ANIMATION_FROM_LEFT = 3;
    public static final int ANIMATION_FROM_RIGHT = 4;
    public static final int LINE_NORMAL = 0;
    public static final int LINE_DOTS = 1;
    public static final int LINE_DASHED = 2;
    public int lineType;
    public int tipDelay;
    static final boolean showCopyright = true;
    int copyrightH;
    boolean copyrightSelected;
    int animation;
    public int animationDelay;
    public String loadingText;
    boolean openingURL;
    public String openingURLText;
    public Color backColor;
    public Color backColor2;
    public int iconsWidth;
    public Color scrollColor;
    Image nodeIcon;
    Image nodeSelectedIcon;
    Color nodeColor;
    Color nodeSelectedColor;
    Color nodeBackColor;
    Color nodeSelectedBackColor;
    Image nodeBackImage;
    boolean nodeCenterText;
    boolean nodeRaised;
    Font nodeFont;
    boolean isLoading;
    public Image loadingImage;
    public Font loadingFont;
    int cursorX;
    int cursorY;
    int cursorBase;
    public boolean showTip;
    public Font tipFont;
    public Color tipColor;
    public Color tipBorder;
    public Color tipBack;
    public RNode Root;
    public boolean showRoot;
    Vector displayedNodes;
    public int levelTab;
    public int leftMargin;
    public int topMargin;
    public Color lineColor;
    public Color plusminusboxColor;
    public boolean drawLine;
    public boolean drawPlusMinus;
    RNode currentNode;
    public Image plusImage;
    public Image minusImage;
    public Applet parentApplet;
    public String defaultTarget;
    long enterTime;
    long showingTipTime;
    int currentY;
    public int itemHeight;
    public int itemWidth;
    boolean inAnimation;
    public String JSOnEnter;
    public String JSOnExit;
    public String JSOnLoad;
    public String JSOnLoadEnd;
    public String JSOnExpand;
    public String JSOnSelect;
    public String JSOnDblClick;
    
    public void drawLineWithStyle(final Graphics g, final int x1, final int y1, final int x2, final int y2, final int lineType) {
        if (lineType == 0) {
            g.drawLine(x1, y1, x2, y2);
        }
        else {
            int segment = 2;
            int segmentspace = 4;
            if (lineType == 1) {
                segment = 1;
                segmentspace = 2;
            }
            final int h = Math.abs(y2 - y1);
            final int w = Math.abs(x2 - x1);
            final double hipo = Math.sqrt(h * h + w * w);
            final double Cos = w / hipo;
            final double Sin = h / hipo;
            int Xslope = 1;
            int Yslope = 1;
            if (x2 < x1) {
                Xslope = -1;
            }
            if (y2 < y1) {
                Yslope = -1;
            }
            int subx1 = 0;
            int suby1 = 0;
            int subx2 = 0;
            int suby2 = 0;
            int subsegment = 0;
            Label_0273: {
                break Label_0273;
                do {
                    if (h < Math.abs(suby1)) {
                        break;
                    }
                    if (w < Math.abs(subx2)) {
                        subx2 = w * Xslope;
                    }
                    if (h < Math.abs(suby2)) {
                        suby2 = h * Yslope;
                    }
                    g.drawLine(x1 + subx1, y1 + suby1, x1 + subx2, y1 + suby2);
                    subsegment = subsegment + segment + segmentspace;
                    suby2 = (int)(Sin * (subsegment + segment)) * Yslope;
                    subx2 = (int)(Cos * (subsegment + segment)) * Xslope;
                    suby1 = (int)(Sin * subsegment) * Yslope;
                    subx1 = (int)(Cos * subsegment) * Xslope;
                } while (w >= Math.abs(subx1));
            }
        }
    }
    
    public Image loadImageUrl(final String urlAdd) {
        if (urlAdd.toUpperCase().compareTo("NULL") == 0) {
            return null;
        }
        for (int i = 0; i < this.ImNames.size(); ++i) {
            final String name = this.ImNames.elementAt(i);
            if (name.compareTo(urlAdd) == 0) {
                return this.Images.elementAt(i);
            }
        }
        try {
            final MediaTracker mt = new MediaTracker(this);
            Image im = null;
            if (this.parentApplet == null) {
                InputStream in = null;
                byte[] b = null;
                int size = 0;
                in = this.getClass().getResourceAsStream(urlAdd);
                if (in == null) {
                    return null;
                }
                size = in.available();
                b = new byte[size];
                in.read(b);
                im = Toolkit.getDefaultToolkit().createImage(b);
            }
            else {
                final String tmpURL = this.parentApplet.getCodeBase() + urlAdd;
                System.out.println(tmpURL);
                im = this.parentApplet.getImage(new URL(tmpURL));
            }
            mt.addImage(im, 0);
            mt.waitForID(0);
            final int Width_im = im.getWidth(null);
            this.Images.addElement(im);
            this.ImNames.addElement(urlAdd);
            return im;
        }
        catch (Exception e) {
            final String err = e.getMessage();
            System.out.println(err);
            return null;
        }
    }
    
    public void processParameter(final RNode currentNode, final String ParamName, final String ParamValue) {
        try {
            if (ParamValue.length() == 0) {
                return;
            }
            if (ParamName.compareTo("TREE_ANIMATION") == 0) {
                if (ParamValue.compareTo("NO") == 0) {
                    this.animation = 0;
                }
                if (ParamValue.compareTo("TOP") == 0) {
                    this.animation = 1;
                }
                if (ParamValue.compareTo("BOTTOM") == 0) {
                    this.animation = 2;
                }
                if (ParamValue.compareTo("LEFT") == 0) {
                    this.animation = 3;
                }
                if (ParamValue.compareTo("RIGHT") == 0) {
                    this.animation = 4;
                }
            }
            if (ParamName.compareTo("TREE_EXPAND_MODE") == 0) {
                if (ParamValue.compareTo("ON_CLICK") == 0) {
                    this.expandOn = 0;
                }
                if (ParamValue.compareTo("ON_ENTER") == 0) {
                    this.expandOn = 1;
                }
            }
            if (ParamName.compareTo("TREE_ON_ENTER") == 0) {
                this.JSOnEnter = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_EXIT") == 0) {
                this.JSOnExit = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_LOAD") == 0) {
                this.JSOnLoad = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_LOAD_END") == 0) {
                this.JSOnLoadEnd = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_DBL_CLICK") == 0) {
                this.JSOnDblClick = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_SELECT") == 0) {
                this.JSOnSelect = ParamValue;
            }
            if (ParamName.compareTo("TREE_ON_EXPAND") == 0) {
                this.JSOnExpand = ParamValue;
            }
            if (ParamName.compareTo("TREE_BACK_IMAGE") == 0) {
                this.backImage = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("PLUS_ICON") == 0) {
                this.plusImage = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("MINUS_ICON") == 0) {
                this.minusImage = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("TREE_TARGET") == 0) {
                this.defaultTarget = ParamValue;
            }
            if (ParamName.compareTo("TREE_LOAD_TEXT") == 0) {
                this.loadingText = ParamValue;
            }
            if (ParamName.compareTo("TREE_OPEN_TEXT") == 0) {
                this.openingURLText = ParamValue;
            }
            try {
                if (ParamName.compareTo("TREE_TIP_DELAY") == 0) {
                    this.tipDelay = new Integer(ParamValue);
                }
            }
            catch (Exception ex) {}
            if (ParamName.compareTo("TREE_DRAW_LINE") == 0) {
                this.drawLine = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("TREE_LINE_TYPE") == 0) {
                if (ParamValue.compareTo("NORMAL") == 0) {
                    this.lineType = 0;
                }
                if (ParamValue.compareTo("DASHED") == 0) {
                    this.lineType = 2;
                }
                if (ParamValue.compareTo("DOTS") == 0) {
                    this.lineType = 1;
                }
            }
            if (ParamName.compareTo("TREE_DRAW_PLUS_MINUS") == 0) {
                this.drawPlusMinus = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("TREE_TAB") == 0) {
                this.levelTab = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_ICON_WIDTH") == 0) {
                this.iconsWidth = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_ANIMATION_DELAY") == 0) {
                this.animationDelay = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_LEFT_MARGIN") == 0) {
                this.leftMargin = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_TOP_MARGIN") == 0) {
                this.topMargin = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_ITEM_HEIGHT") == 0) {
                this.itemHeight = new Integer(ParamValue);
            }
            if (ParamName.compareTo("TREE_LINE_COLOR") == 0) {
                this.lineColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TREE_BOX_COLOR") == 0) {
                this.plusminusboxColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TREE_SCROLL_COLOR") == 0) {
                this.scrollColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TREE_BACK_COLOR") == 0) {
                this.backColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_COLOR") == 0) {
                this.nodeColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_CENTER_TEXT") == 0) {
                this.nodeCenterText = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("NODE_BACK_IMAGE") == 0) {
                this.nodeBackImage = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("TREE_TIP_FONT") == 0) {
                this.tipFont = this.convertFont(ParamValue);
            }
            if (ParamName.compareTo("TREE_TIP_COLOR") == 0) {
                this.tipColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TREE_TIP_BACK_COLOR") == 0) {
                this.tipBack = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TREE_TIP_BORDER_COLOR") == 0) {
                this.tipBorder = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_SELECTED_COLOR") == 0) {
                this.nodeSelectedColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_BACK_COLOR") == 0) {
                this.nodeBackColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_BACK_SELECTED_COLOR") == 0) {
                this.nodeSelectedBackColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("NODE_ICON") == 0) {
                this.nodeIcon = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("NODE_RAISED") == 0) {
                this.nodeRaised = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("NODE_EXPANDED_ICON") == 0) {
                this.nodeSelectedIcon = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("NODE_FONT") == 0) {
                this.nodeFont = this.convertFont(ParamValue);
            }
            if (ParamName.compareTo("FONT") == 0) {
                currentNode.font = this.convertFont(ParamValue);
            }
            if (ParamName.compareTo("TARGET") == 0) {
                currentNode.target = ParamValue;
            }
            if (ParamName.compareTo("TIP") == 0) {
                currentNode.tipText = ParamValue;
            }
            if (ParamName.compareTo("COLOR") == 0) {
                currentNode.color = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("SELECTED_COLOR") == 0) {
                currentNode.selectedColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("BACK_COLOR") == 0) {
                currentNode.backColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("BACK_SELECTED_COLOR") == 0) {
                currentNode.selectedBackColor = this.convertColor(ParamValue);
            }
            if (ParamName.compareTo("TEXT") == 0) {
                currentNode.text = ParamValue;
            }
            if (ParamName.compareTo("LINK") == 0) {
                currentNode.userValue = ParamValue;
            }
            if (ParamName.compareTo("BACK_IMAGE") == 0) {
                currentNode.backImage = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("CENTER_TEXT") == 0) {
                currentNode.centerText = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("ICON") == 0) {
                currentNode.icon = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("DATA_FILE") == 0) {
                currentNode.childrenDefinitionFile = ParamValue;
                if (currentNode.expanded) {
                    this.loadNode(currentNode);
                }
            }
            if (ParamName.compareTo("EXPANDED_ICON") == 0) {
                currentNode.expandedIcon = this.loadImageUrl(ParamValue);
            }
            if (ParamName.compareTo("RAISED") == 0) {
                currentNode.raisedEffectOnSelection = (ParamValue.compareTo("Y") == 0);
            }
            if (ParamName.compareTo("EXPANDED") == 0) {
                currentNode.expanded = (ParamValue.compareTo("Y") == 0);
                if (currentNode.expanded) {
                    this.loadNode(currentNode);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    void performAction(final RNode n) {
        if (this.JSOnDblClick.length() > 0) {
            try {
                final JSObject JS = JSObject.getWindow(this.parentApplet);
                final Object[] o = { n.Name };
                JS.call(this.JSOnDblClick, o);
            }
            catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
        if (n.userValue != null && this.parentApplet != null && n.userValue instanceof String) {
            String add = "";
            String target = "";
            final String val = (String)n.userValue;
            final int p = val.indexOf("|");
            if (p > -1) {
                add = val.substring(0, p);
                target = val.substring(p + 1, val.length());
            }
            else {
                add = val;
                target = this.defaultTarget;
            }
            try {
                this.openingURL = true;
                this.paint(this.getGraphics());
                String urlAdd = add;
                if (add.indexOf("://") == -1) {
                    urlAdd = this.parentApplet.getCodeBase() + add;
                }
                if (n.target.length() > 0) {
                    target = n.target;
                }
                this.parentApplet.getAppletContext().showDocument(new URL(urlAdd), target);
            }
            catch (Exception ex) {}
            this.openingURL = false;
            this.paint(this.getGraphics());
        }
    }
    
    private String[] convertList(String items) {
        final String[] itema = new String[500];
        int itemCount = 0;
        for (int p = items.indexOf("|"); p >= 0; p = items.indexOf("|")) {
            itema[itemCount++] = items.substring(0, p);
            items = items.substring(p + 1, items.length());
        }
        if (items.compareTo("") != 0) {
            itema[itemCount++] = items;
        }
        if (itemCount == 0) {
            return null;
        }
        final String[] result = new String[itemCount];
        for (int i = 0; i < itemCount; ++i) {
            result[i] = itema[i];
        }
        return result;
    }
    
    private void startLoading() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        if (this.JSOnLoad.length() > 0) {
            try {
                final JSObject JS = JSObject.getWindow(this.parentApplet);
                JS.call(this.JSOnLoad, (Object[])null);
            }
            catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
    
    public RTreeList() {
        this.backImage = null;
        this.backImage2 = null;
        this.drawArea = new treeCanvas();
        this.Scroll = new Scrollbar(1);
        this.ImNames = new Vector(10, 5);
        this.Images = new Vector(10, 5);
        this.expandOn = 0;
        this.lineType = 1;
        this.tipDelay = 300;
        this.copyrightH = 0;
        this.copyrightSelected = false;
        this.animation = 0;
        this.animationDelay = 5;
        this.loadingText = "Loading ...";
        this.openingURL = false;
        this.openingURLText = "Opening URL ...";
        this.backColor = Color.lightGray;
        this.backColor2 = null;
        this.iconsWidth = 16;
        this.nodeIcon = null;
        this.nodeSelectedIcon = null;
        this.nodeColor = Color.black;
        this.nodeSelectedColor = Color.black;
        this.nodeFont = new Font("Arial", 0, 12);
        this.isLoading = true;
        this.loadingImage = null;
        this.loadingFont = new Font("Arial", 1, 12);
        this.showTip = false;
        this.tipFont = new Font("Arial", 0, 10);
        this.tipColor = Color.black;
        this.tipBorder = Color.gray;
        this.tipBack = Color.yellow;
        this.Root = new RNode();
        this.showRoot = true;
        this.displayedNodes = new Vector(10, 5);
        this.levelTab = 20;
        this.leftMargin = 10;
        this.topMargin = 20;
        this.lineColor = Color.darkGray;
        this.plusminusboxColor = Color.white;
        this.drawLine = true;
        this.drawPlusMinus = true;
        this.currentNode = null;
        this.defaultTarget = "_top";
        this.enterTime = 0L;
        this.showingTipTime = 0L;
        this.currentY = 0;
        this.itemHeight = 20;
        this.inAnimation = false;
        this.JSOnEnter = "";
        this.JSOnExit = "";
        this.JSOnLoad = "";
        this.JSOnLoadEnd = "";
        this.JSOnExpand = "";
        this.JSOnSelect = "";
        this.JSOnDblClick = "";
        this.Root.expanded = true;
        this.Root.level = 0;
        this.Root.Name = "Root";
        this.drawArea.parentPanel = this;
        this.Scroll.addAdjustmentListener(this.drawArea);
        this.add("East", this.Scroll);
        this.setLayout(new BorderLayout());
        this.add("Center", this.drawArea);
    }
    
    public void addFirstLevelNode(final RNode n) {
        if (this.Root.getChildrenNumber() > 3) {
            return;
        }
        this.Root.addChild(n);
    }
    
    public void paint(final Graphics g) {
        int visibleNodes = this.countNodesToDraw(this.Root);
        if (!this.showRoot) {
            --visibleNodes;
        }
        this.drawArea.setSize(this.getSize().width, this.getSize().height);
        if (visibleNodes * this.itemHeight > this.drawArea.getSize().height - this.copyrightH) {
            this.Scroll.setVisible(true);
            this.add("East", this.Scroll);
            this.doLayout();
            this.drawArea.setSize(this.getSize().width - this.Scroll.getSize().width, visibleNodes * this.itemHeight + this.copyrightH);
            this.Scroll.setMinimum(0);
            this.Scroll.setMaximum(visibleNodes * this.itemHeight + this.copyrightH);
            this.Scroll.setVisibleAmount(this.getSize().height);
            this.Scroll.setUnitIncrement(4);
            this.Scroll.setBlockIncrement(this.getSize().height - 10);
            this.Scroll.setVisible(true);
            this.Scroll.setValue(this.cursorBase);
        }
        else {
            this.Scroll.setVisible(false);
            this.remove(this.Scroll);
            this.doLayout();
            this.drawArea.setSize(this.getSize().width, this.getSize().height);
            this.Scroll.setVisible(false);
            this.cursorBase = 0;
        }
        if (this.cursorBase + this.Scroll.getVisibleAmount() > this.drawArea.getSize().height) {
            int newBase = this.drawArea.getSize().height - this.Scroll.getVisibleAmount();
            if (newBase < 0) {
                newBase = 0;
            }
            this.Scroll.setValue(newBase);
            this.cursorBase = newBase;
        }
        super.paint(g);
        this.drawArea.paint(this.drawArea.getGraphics());
    }
    
    public int countNodesToDraw(final RNode n) {
        int childrenCount = 0;
        if (n.expanded) {
            for (int i = 0; i < n.getChildrenNumber(); ++i) {
                childrenCount += this.countNodesToDraw(n.getChild(i));
            }
        }
        return childrenCount + 1;
    }
    
    private void endLoading() {
        if (!this.isLoading) {
            return;
        }
        this.isLoading = false;
        if (this.JSOnLoadEnd.length() > 0) {
            try {
                final JSObject JS = JSObject.getWindow(this.parentApplet);
                JS.call(this.JSOnLoadEnd, (Object[])null);
            }
            catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }
    }
    
    public RNode findNode(RNode n, final String Name) {
        final int childrenCount = 0;
        if (n == null) {
            n = this.Root;
        }
        if (n.Name.toLowerCase().compareTo(Name.toLowerCase()) == 0) {
            return n;
        }
        for (int i = 0; i < n.getChildrenNumber(); ++i) {
            final RNode child = this.findNode(n.getChild(i), Name);
            if (child != null) {
                return child;
            }
        }
        return null;
    }
    
    public Font convertFont(final String f) {
        final String[] items = this.convertList(f);
        if (items == null) {
            return null;
        }
        if (items.length < 3) {
            return null;
        }
        int s = 0;
        if (items[1].compareTo("BOLD") == 0) {
            s = 1;
        }
        if (items[1].compareTo("ITALIC") == 0) {
            s = 2;
        }
        try {
            return new Font(items[0], s, new Integer(items[2]));
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public void createTreeFromFile(final RNode startNode, String sFile) {
        String fileData = "";
        String param = "";
        String value = "";
        RNode currentNode = startNode;
        this.startLoading();
        this.drawArea.paint(this.drawArea.getGraphics());
        if (startNode != null) {
            startNode.childrenDefinitionLoaded = true;
        }
        try {
            BufferedReader in = null;
            if (this.parentApplet != null && sFile.indexOf(":/") == -1) {
                sFile = this.parentApplet.getCodeBase() + sFile;
            }
            System.out.println(this.parentApplet.getCodeBase());
            System.out.println(this.parentApplet.getDocumentBase());
            final URL u = new URL(sFile);
            in = new BufferedReader(new InputStreamReader(u.openStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                fileData = fileData + inputLine + '\r' + '\n';
            }
            in.close();
        }
        catch (Exception e) {
            final String err = e.getMessage();
            System.out.println("Could not open file: " + sFile + " " + e);
        }
        String line = "";
        while (fileData.length() > 0) {
            int p = fileData.indexOf("" + '\r' + '\n');
            final int p2 = fileData.indexOf("" + '\n');
            if (p <= p2 && p >= 0) {
                line = fileData.substring(0, p);
                fileData = fileData.substring(p + 2, fileData.length());
            }
            if (p > p2 && p2 >= 0) {
                line = fileData.substring(0, p2);
                fileData = fileData.substring(p2 + 1, fileData.length());
            }
            if (p2 == -1 && p == -1) {
                line = fileData;
                fileData = "";
            }
            p = line.indexOf("=");
            if (p > -1) {
                param = line.substring(0, p);
                value = line.substring(p + 1, line.length());
                param = param.toUpperCase();
                if (param.compareTo("CHILD") == 0) {
                    currentNode = new RNode();
                    currentNode.Name = value;
                    currentNode.color = this.nodeColor;
                    currentNode.selectedBackColor = this.nodeSelectedBackColor;
                    currentNode.selectedColor = this.nodeSelectedColor;
                    currentNode.selectedBackColor = this.nodeBackColor;
                    currentNode.icon = this.nodeIcon;
                    currentNode.expandedIcon = this.nodeSelectedIcon;
                    currentNode.raisedEffectOnSelection = this.nodeRaised;
                    currentNode.expanded = false;
                    currentNode.centerText = this.nodeCenterText;
                    currentNode.backImage = this.nodeBackImage;
                    currentNode.font = this.nodeFont;
                    if (startNode == this.Root) {
                        this.addFirstLevelNode(currentNode);
                    }
                    else {
                        startNode.addChild(currentNode);
                    }
                }
                else {
                    this.processParameter(currentNode, param, value);
                }
            }
        }
        this.endLoading();
    }
    
    public void createTreeFromParameters(final Applet pApplet) {
        this.startLoading();
        this.drawArea.paint(this.drawArea.getGraphics());
        this.processParameter(null, "TREE_ON_ENTER", this.getStringParam("TREE_ON_ENTER", ""));
        this.processParameter(null, "TREE_ON_EXIT", this.getStringParam("TREE_ON_EXIT", ""));
        this.processParameter(null, "TREE_ON_LOAD", this.getStringParam("TREE_ON_LOAD", ""));
        this.processParameter(null, "TREE_ON_LOAD_END", this.getStringParam("TREE_ON_LOAD_END", ""));
        this.processParameter(null, "TREE_ON_SELECT", this.getStringParam("TREE_ON_SELECT", ""));
        this.processParameter(null, "TREE_ON_EXPAND", this.getStringParam("TREE_ON_EXPAND", ""));
        this.processParameter(null, "TREE_ON_DBL_CLICK", this.getStringParam("TREE_ON_DBL_CLICK", ""));
        this.processParameter(null, "TREE_BORDER", this.getStringParam("TREE_BORDER", ""));
        this.processParameter(null, "TREE_ANIMATION", this.getStringParam("TREE_ANIMATION", ""));
        this.processParameter(null, "TREE_EXPAND_MODE", this.getStringParam("TREE_EXPAND_MODE", ""));
        this.processParameter(null, "TREE_DRAW_PLUS_MINUS", this.getStringParam("TREE_DRAW_PLUS_MINUS", ""));
        this.processParameter(null, "TREE_TAB", this.getStringParam("TREE_TAB", ""));
        this.processParameter(null, "TREE_BACK_COLOR", this.getStringParam("TREE_BACK_COLOR", ""));
        this.processParameter(null, "TREE_LINE_COLOR", this.getStringParam("TREE_LINE_COLOR", ""));
        this.processParameter(null, "TREE_ITEM_HEIGHT", this.getStringParam("TREE_ITEM_HEIGHT", ""));
        this.processParameter(null, "TREE_BACK_COLOR", this.getStringParam("TREE_BACK_COLOR", ""));
        this.processParameter(null, "TREE_BOX_COLOR", this.getStringParam("TREE_BOX_COLOR", ""));
        this.processParameter(null, "TREE_LEFT_MARGIN", this.getStringParam("TREE_LEFT_MARGIN", ""));
        this.processParameter(null, "TREE_TOP_MARGIN", this.getStringParam("TREE_TOP_MARGIN", ""));
        this.processParameter(null, "PLUS_ICON", this.getStringParam("PLUS_ICON", ""));
        this.processParameter(null, "MINUS_ICON", this.getStringParam("MINUS_ICON", ""));
        this.processParameter(null, "TREE_TARGET", this.getStringParam("TREE_TARGET", ""));
        this.processParameter(null, "TREE_TIP_COLOR", this.getStringParam("TREE_TIP_COLOR", ""));
        this.processParameter(null, "TREE_TIP_BACK_COLOR", this.getStringParam("TREE_TIP_BACK_COLOR", ""));
        this.processParameter(null, "TREE_TIP_BORDER_COLOR", this.getStringParam("TREE_TIP_BORDER_COLOR", ""));
        this.processParameter(null, "TREE_TIP_FONT", this.getStringParam("TREE_TIP_FONT", ""));
        this.processParameter(null, "TREE_PAGE_BACK_COLOR", this.getStringParam("TREE_PAGE_BACK_COLOR", ""));
        this.processParameter(null, "TREE_BACK_IMAGE", this.getStringParam("TREE_BACK_IMAGE", ""));
        this.processParameter(null, "TREE_SCROLL_COLOR", this.getStringParam("TREE_SCROLL_COLOR", ""));
        this.processParameter(null, "TREE_TIP_DELAY", this.getStringParam("TREE_TIP_DELAY", ""));
        this.processParameter(null, "TREE_LINE_TYPE", this.getStringParam("TREE_LINE_TYPE", ""));
        this.processParameter(null, "TREE_DRAW_LINE", this.getStringParam("TREE_DRAW_LINE", ""));
        this.processParameter(null, "TREE_LOAD_TEXT", this.getStringParam("TREE_LOAD_TEXT", ""));
        this.processParameter(null, "TREE_OPEN_TEXT", this.getStringParam("TREE_OPEN_TEXT", ""));
        this.processParameter(null, "TREE_ICON_WIDTH", this.getStringParam("TREE_ICON_WIDTH", ""));
        this.processParameter(null, "TREE_ANIMATION_DELAY", this.getStringParam("TREE_ANIMATION_DELAY", ""));
        this.processParameter(null, "NODE_COLOR", this.getStringParam("NODE_COLOR", ""));
        this.processParameter(null, "NODE_RAISED", this.getStringParam("NODE_RAISED", ""));
        this.processParameter(null, "NODE_SELECTED_COLOR", this.getStringParam("NODE_SELECTED_COLOR", ""));
        this.processParameter(null, "NODE_BACK_COLOR", this.getStringParam("NODE_BACK_COLOR", ""));
        this.processParameter(null, "NODE_SELECTED_BACK_COLOR", this.getStringParam("NODE_BACK_SELECTED_COLOR", ""));
        this.processParameter(null, "NODE_ICON", this.getStringParam("NODE_ICON", ""));
        this.processParameter(null, "NODE_CENTER_TEXT", this.getStringParam("NODE_CENTER_TEXT", ""));
        this.processParameter(null, "NODE_BACK_IMAGE", this.getStringParam("NODE_BACK_IMAGE", ""));
        this.processParameter(null, "NODE_EXPANDED_ICON", this.getStringParam("NODE_EXPANDED_ICON", ""));
        this.processParameter(null, "NODE_FONT", this.getStringParam("NODE_FONT", ""));
        this.createNode(null);
        this.endLoading();
    }
    
    private void createNode(final RNode parent) {
        String pName = "";
        String cName = "";
        int child = 1;
        if (parent != null) {
            pName = parent.Name;
        }
        else {
            pName = "ROOT";
        }
        if (parent == null) {
            if (this.getStringParam("SHOW_ROOT", "").compareTo("Y") == 0) {
                this.showRoot = true;
            }
            else {
                this.showRoot = false;
            }
            this.Root.color = this.nodeColor;
            this.Root.selectedBackColor = this.nodeSelectedBackColor;
            this.Root.selectedColor = this.nodeSelectedColor;
            this.Root.selectedBackColor = this.nodeBackColor;
            this.Root.icon = this.nodeIcon;
            this.Root.expandedIcon = this.nodeSelectedIcon;
            this.Root.raisedEffectOnSelection = this.nodeRaised;
            this.Root.centerText = this.nodeCenterText;
            this.Root.backImage = this.nodeBackImage;
            this.Root.font = this.nodeFont;
            this.createNodeFromParameters(this.Root, "ROOT");
            if (this.Root.childrenDefinitionFile.length() > 0) {
                this.loadNode(this.Root);
            }
        }
        for (cName = this.getStringParam(pName + "_" + child, ""); cName.length() > 0; cName = this.getStringParam(pName + "_" + child, "")) {
            final RNode n = new RNode();
            n.Name = cName;
            n.color = this.nodeColor;
            n.selectedBackColor = this.nodeSelectedBackColor;
            n.selectedColor = this.nodeSelectedColor;
            n.selectedBackColor = this.nodeBackColor;
            n.icon = this.nodeIcon;
            n.expandedIcon = this.nodeSelectedIcon;
            n.raisedEffectOnSelection = this.nodeRaised;
            n.centerText = this.nodeCenterText;
            n.backImage = this.nodeBackImage;
            n.expanded = false;
            n.font = this.nodeFont;
            if (parent == null) {
                this.addFirstLevelNode(n);
            }
            else {
                parent.addChild(n);
            }
            this.createNodeFromParameters(n, cName);
            this.createNode(n);
            ++child;
        }
    }
    
    private String[] getItemsParameter(final String key) {
        final String items = this.getStringParam(key, "");
        return this.convertList(items);
    }
    
    public void drawNode(final Graphics g, final RNode n, final int level, final boolean redrawChildren) {
        RNode lastChild = null;
        final int x = this.levelTab * level + this.leftMargin;
        n.level = level;
        int nodeIndex = 0;
        if (level >= 1 || this.showRoot) {
            n.xPos = x;
            n.yPos = this.currentY;
            n.draw(g, x, this.currentY, this.itemHeight, this.itemWidth);
            nodeIndex = this.displayedNodes.size();
            this.currentY += this.itemHeight;
            this.displayedNodes.addElement(n);
        }
        else {
            n.xPos = this.leftMargin;
            n.yPos = this.currentY;
            nodeIndex = -1;
        }
        if (redrawChildren && n.expanded) {
            for (int i = 0; i < n.getChildrenNumber(); ++i) {
                this.drawNode(g, n.getChild(i), level + 1, true);
                lastChild = n.getChild(i);
            }
            if (this.drawLine) {
                g.setColor(this.lineColor);
                if (lastChild != null) {
                    this.drawLineWithStyle(g, n.xPos, n.yPos + n.hSize, n.xPos, lastChild.yPos + lastChild.hSize / 2, this.lineType);
                    for (int j = nodeIndex + 1; j < this.displayedNodes.size(); ++j) {
                        final RNode Child = this.displayedNodes.elementAt(j);
                        if (n.level + 1 == Child.level) {
                            this.drawLineWithStyle(g, n.xPos, Child.yPos + Child.hSize / 2, Child.xPos, Child.yPos + Child.hSize / 2, this.lineType);
                            String plusminus = "";
                            if (this.drawPlusMinus && Child.expanded && Child.hasChildren()) {
                                plusminus = "-";
                            }
                            if (this.drawPlusMinus && !Child.expanded && Child.hasChildren()) {
                                plusminus = "+";
                            }
                            if (plusminus.compareTo("-") == 0 && this.minusImage != null) {
                                int recSide = this.minusImage.getWidth(null);
                                final int h = this.minusImage.getHeight(null);
                                if (h > recSide) {
                                    recSide = h;
                                }
                                final int pmX = n.xPos + (this.levelTab - recSide) / 2;
                                final int pmY = Child.yPos + (this.itemHeight - recSide) / 2;
                                g.drawImage(this.minusImage, pmX, pmY, null);
                            }
                            if (plusminus.compareTo("+") == 0 && this.plusImage != null) {
                                int recSide = this.plusImage.getWidth(null);
                                final int h = this.plusImage.getHeight(null);
                                if (h > recSide) {
                                    recSide = h;
                                }
                                final int pmX = n.xPos + (this.levelTab - recSide) / 2;
                                final int pmY = Child.yPos + (this.itemHeight - recSide) / 2;
                                g.drawImage(this.plusImage, pmX, pmY, null);
                            }
                            if (plusminus.length() > 0 && this.minusImage == null && this.plusImage == null) {
                                g.setFont(new Font("Arial", 0, 8));
                                int recSide = g.getFontMetrics().stringWidth(plusminus);
                                if (g.getFontMetrics().getHeight() > recSide) {
                                    recSide = g.getFontMetrics().getHeight();
                                }
                                final int pmX2 = n.xPos + (this.levelTab - recSide) / 2;
                                final int pmY2 = Child.yPos + (this.itemHeight - recSide) / 2;
                                g.setColor(this.plusminusboxColor);
                                g.fillRect(pmX2, pmY2, recSide, recSide);
                                g.setColor(this.lineColor);
                                g.drawRect(pmX2, pmY2, recSide, recSide);
                                final int toCenterXtext = (recSide - g.getFontMetrics().stringWidth(plusminus)) / 2;
                                final int toCenterYtext = (recSide - g.getFontMetrics().getHeight()) / 2;
                                g.drawString(plusminus, pmX2 + toCenterXtext + 1, pmY2 + g.getFontMetrics().getHeight() + toCenterYtext - 2);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void expandNode(final String Name, final boolean expand) {
        final RNode n = this.findNode(this.Root, Name);
        if (n != null) {
            n.expanded = expand;
            if (n.expanded) {
                this.loadNode(n);
            }
        }
    }
    
    public void clear(final RNode n) {
        for (int i = 0; i < n.getChildrenNumber(); ++i) {
            this.clear(n.getChild(i));
        }
        n.deleteChildren();
        n.childrenDefinitionFile = "";
    }
    
    private String getParameter(final String key, final String def) {
        if (this.parentApplet.getParameter(key) != null) {
            return this.parentApplet.getParameter(key);
        }
        return def;
    }
    
    public void refresh() {
        this.drawArea.paint(this.drawArea.getGraphics());
        this.paint(this.getGraphics());
    }
    
    public void run() {
        this.init();
    }
    
    private void init() {
        if (this.parentApplet != null) {
            this.createTreeFromParameters(this.parentApplet);
        }
        if (this.scrollColor != null) {
            this.Scroll.setBackground(this.scrollColor);
        }
        this.itemWidth = this.getSize().width;
        this.loadNode(this.Root);
        this.setBackground(this.backColor);
        this.paintAll(this.getGraphics());
        this.drawArea.paint(this.drawArea.getGraphics());
        try {
            while (this.inAnimation) {
                this.drawArea.paint(this.drawArea.getGraphics());
                Thread.sleep(this.animationDelay);
            }
            Thread.sleep(200L);
            this.drawArea.paint(this.drawArea.getGraphics());
        }
        catch (Exception ex) {}
    }
    
    private String getStringParam(final String Param, final String def) {
        return this.getParameter(Param, def);
    }
    
    private void createNodeFromParameters(final RNode n, final String nodeName) {
        this.processParameter(n, "FONT", this.getStringParam(nodeName + "_FONT", ""));
        this.processParameter(n, "COLOR", this.getStringParam(nodeName + "_COLOR", ""));
        this.processParameter(n, "SELECTED_COLOR", this.getStringParam(nodeName + "_SELECTED_COLOR", ""));
        this.processParameter(n, "BACK_COLOR", this.getStringParam(nodeName + "_BACK_COLOR", ""));
        this.processParameter(n, "BACK_SELECTED_COLOR", this.getStringParam(nodeName + "_BACK_SELECTED_COLOR", ""));
        this.processParameter(n, "TEXT", this.getStringParam(nodeName + "_TEXT", ""));
        this.processParameter(n, "LINK", this.getStringParam(nodeName + "_LINK", ""));
        this.processParameter(n, "ICON", this.getStringParam(nodeName + "_ICON", ""));
        this.processParameter(n, "EXPANDED_ICON", this.getStringParam(nodeName + "_EXPANDED_ICON", ""));
        this.processParameter(n, "RAISED", this.getStringParam(nodeName + "_RAISED", ""));
        this.processParameter(n, "BACK_IMAGE", this.getStringParam(nodeName + "_BACK_IMAGE", ""));
        this.processParameter(n, "CENTER_TEXT", this.getStringParam(nodeName + "_CENTER_TEXT", ""));
        this.processParameter(n, "DATA_FILE", this.getStringParam(nodeName + "_DATA_FILE", ""));
        this.processParameter(n, "TIP", this.getStringParam(nodeName + "_TIP", ""));
        this.processParameter(n, "TARGET", this.getStringParam(nodeName + "_TARGET", ""));
        this.processParameter(n, "EXPANDED", this.getStringParam(nodeName + "_EXPANDED", ""));
    }
    
    RNode getCurrentNode(final int x, final int y) {
        for (int i = 0; i < this.displayedNodes.size(); ++i) {
            final RNode n = this.displayedNodes.elementAt(i);
            if (n.xPos <= x && n.xPosText + n.wSizeText >= x && n.yPos <= y && n.yPos + n.hSize >= y) {
                return n;
            }
        }
        return null;
    }
    
    void loadNode(final RNode n) {
        if (n.expanded && n.childrenDefinitionFile.length() > 0 && !n.childrenDefinitionLoaded) {
            this.createTreeFromFile(n, n.childrenDefinitionFile);
            n.childrenDefinitionLoaded = true;
        }
    }
    
    public Color convertColor(final String c) {
        if (c.compareTo("NULL") == 0) {
            return null;
        }
        if (c.compareTo("RED") == 0) {
            return Color.red;
        }
        if (c.compareTo("BLACK") == 0) {
            return Color.black;
        }
        if (c.compareTo("BLUE") == 0) {
            return Color.blue;
        }
        if (c.compareTo("CYAN") == 0) {
            return Color.cyan;
        }
        if (c.compareTo("DARKGRAY") == 0) {
            return Color.darkGray;
        }
        if (c.compareTo("GRAY") == 0) {
            return Color.gray;
        }
        if (c.compareTo("GREEN") == 0) {
            return Color.green;
        }
        if (c.compareTo("LIGHTGRAY") == 0) {
            return Color.lightGray;
        }
        if (c.compareTo("MAGENTA") == 0) {
            return Color.magenta;
        }
        if (c.compareTo("ORANGE") == 0) {
            return Color.orange;
        }
        if (c.compareTo("PINK") == 0) {
            return Color.pink;
        }
        if (c.compareTo("WHITE") == 0) {
            return Color.white;
        }
        if (c.compareTo("YELLOW") == 0) {
            return Color.yellow;
        }
        try {
            return Color.decode(c);
        }
        catch (Exception e) {
            return Color.black;
        }
    }
    
    void paintLoadingText(final Graphics g) {
        try {
            int iwidth = 0;
            if (this.loadingImage != null) {
                g.drawImage(this.loadingImage, 20, 20, null);
                iwidth = this.loadingImage.getWidth(null);
            }
            g.setFont(this.loadingFont);
            if (this.loadingText != null) {
                g.drawString(this.loadingText, iwidth + 10 + 15, 20 + g.getFontMetrics().getHeight());
            }
        }
        catch (Exception ex) {}
    }
    
    private class treeCanvas extends Canvas implements AdjustmentListener, MouseMotionListener, MouseListener
    {
        private int xStepAnimation;
        private int currentAnimationx;
        private int yStepAnimation;
        private int currentAnimationy;
        private Image treeLayer;
        private Image backLayer;
        private Image finalImage;
        public RTreeList parentPanel;
        
        private void drawBackImage(final Graphics g) {
            if (RTreeList.this.backImage == null) {
                return;
            }
            final int ImageW = RTreeList.this.backImage.getWidth(null);
            final int ImageH = RTreeList.this.backImage.getHeight(null);
            if (ImageW == -1 || ImageH == -1) {
                return;
            }
            for (int j = 0; j < this.getSize().width; j += ImageW) {
                for (int i = 0; i < this.getSize().height; i += ImageH) {
                    g.drawImage(RTreeList.this.backImage, j, i, null);
                }
            }
        }
        
        public void adjustmentValueChanged(final AdjustmentEvent e) {
            RTreeList.this.cursorBase = e.getValue();
            if (RTreeList.this.cursorBase + RTreeList.this.Scroll.getVisibleAmount() > RTreeList.this.drawArea.getSize().height) {
                int newBase = RTreeList.this.drawArea.getSize().height - RTreeList.this.Scroll.getVisibleAmount();
                if (newBase < 0) {
                    newBase = 0;
                }
                RTreeList.this.Scroll.setValue(newBase);
                RTreeList.this.cursorBase = newBase;
            }
            this.paint(this.getGraphics());
        }
        
        public void mouseDragged(final MouseEvent e) {
        }
        
        public void mouseClicked(final MouseEvent e) {
            boolean textClicked = false;
            if (RTreeList.this.inAnimation) {
                return;
            }
            RTreeList.this.cursorX = e.getX();
            RTreeList.this.cursorY = e.getY() + RTreeList.this.cursorBase;
            try {
                if (RTreeList.this.cursorY > this.getSize().height - RTreeList.this.copyrightH) {
                    RTreeList.this.parentApplet.getAppletContext().showDocument(new URL("http://rreport.8m.com"));
                }
            }
            catch (Exception ex) {}
            RNode n = null;
            int i;
            for (i = 0; i < RTreeList.this.displayedNodes.size(); ++i) {
                n = RTreeList.this.displayedNodes.elementAt(i);
                if (RTreeList.this.cursorX >= n.xPos + RTreeList.this.iconsWidth && n.yPos <= RTreeList.this.cursorY && n.yPos + n.hSize >= RTreeList.this.cursorY) {
                    textClicked = true;
                    break;
                }
                if (n.xPos - RTreeList.this.levelTab <= RTreeList.this.cursorX && RTreeList.this.cursorX < n.xPosText + n.wSizeText && n.yPos <= RTreeList.this.cursorY && n.yPos + n.hSize >= RTreeList.this.cursorY) {
                    break;
                }
            }
            if (i < RTreeList.this.displayedNodes.size()) {
                if (textClicked) {
                    RTreeList.this.performAction(n);
                }
                if (n.hasChildren() && (e.getClickCount() > 1 || !textClicked || (e.getClickCount() == 1 && n.userValue == null))) {
                    n.expanded = !n.expanded;
                    if (n.expanded) {
                        RTreeList.this.loadNode(n);
                        if (RTreeList.this.JSOnExpand.length() > 0) {
                            try {
                                final JSObject JS = JSObject.getWindow(RTreeList.this.parentApplet);
                                final Object[] o = { n.Name };
                                JS.call(RTreeList.this.JSOnExpand, (Object[])null);
                            }
                            catch (Exception e2) {
                                System.out.println(e2.getMessage());
                            }
                        }
                    }
                    this.parentPanel.paint(this.parentPanel.getGraphics());
                    this.paint(this.getGraphics());
                }
            }
        }
        
        public void mousePressed(final MouseEvent e) {
        }
        
        public treeCanvas() {
            RTreeList.this.getClass();
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
        }
        
        public void mouseMoved(final MouseEvent e) {
            if (RTreeList.this.inAnimation) {
                return;
            }
            final boolean deleteTip = false;
            boolean redraw = false;
            boolean resize = false;
            RTreeList.this.cursorX = e.getX();
            RTreeList.this.cursorY = e.getY() + RTreeList.this.cursorBase;
            final RNode n = RTreeList.this.getCurrentNode(RTreeList.this.cursorX, RTreeList.this.cursorY);
            if (n == RTreeList.this.currentNode && n != null && System.currentTimeMillis() - RTreeList.this.enterTime > RTreeList.this.tipDelay && RTreeList.this.showingTipTime == 0L) {
                RTreeList.this.showingTipTime = System.currentTimeMillis();
                redraw = true;
            }
            if (n != RTreeList.this.currentNode) {
                if (RTreeList.this.currentNode != null) {
                    RTreeList.this.currentNode.selected = false;
                    boolean collapse = false;
                    if (RTreeList.this.expandOn == 1 && RTreeList.this.currentNode != null && n != RTreeList.this.Root && RTreeList.this.currentNode.getChildrenNumber() > 0) {
                        collapse = true;
                    }
                    if (n != null && n.parent == RTreeList.this.currentNode) {
                        collapse = false;
                    }
                    if (collapse) {
                        RTreeList.this.currentNode.expanded = false;
                        resize = true;
                    }
                }
                RTreeList.this.enterTime = 0L;
                RTreeList.this.showingTipTime = 0L;
                if (n != null) {
                    n.selected = true;
                    RTreeList.this.enterTime = System.currentTimeMillis();
                    if (RTreeList.this.JSOnSelect.length() > 0) {
                        try {
                            final JSObject JS = JSObject.getWindow(RTreeList.this.parentApplet);
                            final Object[] o = { n.Name };
                            JS.call(RTreeList.this.JSOnSelect, o);
                        }
                        catch (Exception e2) {
                            System.out.println(e2.getMessage());
                        }
                    }
                    if (RTreeList.this.expandOn == 1 && n.hasChildren()) {
                        n.expanded = true;
                        RTreeList.this.loadNode(n);
                        resize = true;
                    }
                }
                RTreeList.this.currentNode = n;
                redraw = true;
            }
            if (!RTreeList.this.copyrightSelected && RTreeList.this.cursorY > this.getSize().height - RTreeList.this.copyrightH) {
                RTreeList.this.copyrightSelected = true;
                redraw = true;
            }
            if (RTreeList.this.copyrightSelected && RTreeList.this.cursorY <= this.getSize().height - RTreeList.this.copyrightH) {
                RTreeList.this.copyrightSelected = false;
                redraw = true;
            }
            if (redraw) {
                if (resize) {
                    this.parentPanel.paint(this.parentPanel.getGraphics());
                }
                this.paint(this.getGraphics());
            }
        }
        
        public void mouseReleased(final MouseEvent e) {
        }
        
        public void paint(final Graphics g) {
            boolean firstTime = false;
            boolean createImages = false;
            if (g == null) {
                return;
            }
            if (RTreeList.this.isLoading) {
                RTreeList.this.paintLoadingText(g);
                return;
            }
            if (this.paintAnimationScene(g)) {
                return;
            }
            if (this.treeLayer == null) {
                createImages = true;
                firstTime = true;
            }
            if (this.treeLayer != null && this.getSize().height != this.treeLayer.getHeight(null)) {
                createImages = true;
            }
            if (createImages) {
                RTreeList.this.cursorBase = 0;
                this.backLayer = this.createImage(this.getSize().width, this.getSize().height);
                this.finalImage = this.createImage(this.getSize().width, this.getSize().height);
                this.treeLayer = this.createImage(this.getSize().width, this.getSize().height);
            }
            if (RTreeList.this.backColor != null && (RTreeList.this.backColor != RTreeList.this.backColor2 || createImages)) {
                final Graphics g2 = this.backLayer.getGraphics();
                g2.setColor(RTreeList.this.backColor);
                g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
                RTreeList.this.backColor2 = RTreeList.this.backColor;
            }
            if (RTreeList.this.backImage != RTreeList.this.backImage2 || createImages) {
                this.drawBackImage(this.backLayer.getGraphics());
            }
            if (RTreeList.this.backImage2 != null && RTreeList.this.backImage == null && RTreeList.this.backColor != null) {
                final Graphics g2 = this.backLayer.getGraphics();
                g2.setColor(RTreeList.this.backColor);
                g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
            }
            RTreeList.this.backImage2 = RTreeList.this.backImage;
            RTreeList.this.currentY = RTreeList.this.topMargin;
            RTreeList.this.displayedNodes.removeAllElements();
            this.treeLayer.getGraphics().drawImage(this.backLayer, 0, 0, null);
            RTreeList.this.drawNode(this.treeLayer.getGraphics(), RTreeList.this.Root, 0, true);
            if (RTreeList.this.currentNode != null && RTreeList.this.showingTipTime != 0L && RTreeList.this.currentNode.tipText.length() > 0) {
                final Graphics g3 = this.treeLayer.getGraphics();
                g3.setFont(RTreeList.this.tipFont);
                final int tipW = g3.getFontMetrics().stringWidth(RTreeList.this.currentNode.tipText);
                final int tipH = g3.getFontMetrics().getHeight();
                g3.setColor(RTreeList.this.tipBack);
                g3.fillRect(RTreeList.this.cursorX, RTreeList.this.cursorY + 20, tipW + 6, tipH + 4);
                g3.setColor(RTreeList.this.tipBorder);
                g3.drawRect(RTreeList.this.cursorX, RTreeList.this.cursorY + 20, tipW + 6, tipH + 4);
                g3.setColor(RTreeList.this.tipColor);
                g3.drawString(RTreeList.this.currentNode.tipText, RTreeList.this.cursorX + 3, RTreeList.this.cursorY + 20 + tipH);
            }
            if (!RTreeList.this.openingURL) {
                final Graphics g3 = this.treeLayer.getGraphics();
                g3.setFont(new Font("Arial", 0, 10));
                if (RTreeList.this.nodeColor != null) {
                    g3.setColor(RTreeList.this.nodeColor);
                }
                else {
                    g3.setColor(Color.black);
                }
                RTreeList.this.copyrightH = g3.getFontMetrics().getHeight() + 10;
                if (RTreeList.this.copyrightSelected) {
                    g3.setColor(RTreeList.this.backColor.brighter());
                    if (RTreeList.this.nodeSelectedColor != null) {
                        g3.setColor(RTreeList.this.nodeSelectedColor);
                    }
                }
                g3.drawString("Courtesy of http://rreport.8m.com", 10, this.getSize().height - RTreeList.this.copyrightH / 2);
            }
            if (RTreeList.this.openingURL) {
                final Graphics g3 = this.treeLayer.getGraphics();
                g3.setFont(new Font("Arial", 0, 10));
                if (RTreeList.this.backColor != Color.black) {
                    g3.setColor(Color.black);
                }
                else {
                    g3.setColor(Color.white);
                }
                g3.drawString(RTreeList.this.openingURLText, 10, this.getSize().height - RTreeList.this.copyrightH / 2);
            }
            if (RTreeList.this.animation != 0 && firstTime) {
                this.xStepAnimation = 1;
                this.yStepAnimation = 1;
                int startx = -1 * this.getSize().width;
                int starty = -1 * this.getSize().height;
                if (RTreeList.this.animation == 1) {
                    this.xStepAnimation = 0;
                    startx = 0;
                }
                if (RTreeList.this.animation == 2) {
                    this.yStepAnimation *= -1;
                    this.xStepAnimation = 0;
                    startx = 0;
                    starty *= -1;
                }
                if (RTreeList.this.animation == 3) {
                    this.yStepAnimation = 0;
                    starty = 0;
                }
                if (RTreeList.this.animation == 4) {
                    this.xStepAnimation *= -1;
                    this.yStepAnimation = 0;
                    starty = 0;
                    startx *= -1;
                }
                this.currentAnimationx = startx;
                this.currentAnimationy = starty;
                RTreeList.this.inAnimation = true;
                return;
            }
            if (this.finalImage != null) {
                this.finalImage.getGraphics().drawImage(this.backLayer, 0, 0, null);
                this.finalImage.getGraphics().drawImage(this.treeLayer, 0, 0, null);
                g.drawImage(this.finalImage, 0, 0, this.getSize().width, this.parentPanel.getSize().height, 0, RTreeList.this.cursorBase, this.getSize().width, RTreeList.this.cursorBase + this.parentPanel.getSize().height, null);
            }
        }
        
        public void mouseEntered(final MouseEvent e) {
            if (RTreeList.this.JSOnEnter.length() > 0) {
                try {
                    final JSObject JS = JSObject.getWindow(RTreeList.this.parentApplet);
                    JS.call(RTreeList.this.JSOnEnter, (Object[])null);
                }
                catch (Exception e2) {
                    final String err = e2.getMessage();
                    System.out.println(err);
                }
            }
        }
        
        public void mouseExited(final MouseEvent e) {
            for (int i = 0; i < RTreeList.this.displayedNodes.size(); ++i) {
                RTreeList.this.displayedNodes.elementAt(i).selected = false;
            }
            RTreeList.this.refresh();
            if (RTreeList.this.JSOnExit.length() > 0) {
                try {
                    final JSObject JS = JSObject.getWindow(RTreeList.this.parentApplet);
                    JS.call(RTreeList.this.JSOnExit, (Object[])null);
                }
                catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        
        private boolean paintAnimationScene(final Graphics g) {
            if (!RTreeList.this.inAnimation) {
                return false;
            }
            if (Math.abs(this.currentAnimationx) > Math.abs(this.xStepAnimation) || Math.abs(this.currentAnimationy) > Math.abs(this.yStepAnimation)) {
                this.currentAnimationx += this.xStepAnimation;
                this.currentAnimationy += this.yStepAnimation;
                if (RTreeList.this.animation == 1) {
                    this.finalImage.getGraphics().drawImage(this.backLayer, 0, this.currentAnimationy + this.treeLayer.getHeight(null), null);
                }
                if (RTreeList.this.animation == 2) {
                    this.finalImage.getGraphics().drawImage(this.backLayer, 0, this.currentAnimationy - this.backLayer.getHeight(null), null);
                }
                if (RTreeList.this.animation == 3) {
                    this.finalImage.getGraphics().drawImage(this.backLayer, this.treeLayer.getWidth(null) + this.currentAnimationx, 0, null);
                }
                if (RTreeList.this.animation == 4) {
                    this.finalImage.getGraphics().drawImage(this.backLayer, this.currentAnimationx - this.backLayer.getWidth(null), 0, null);
                }
                this.finalImage.getGraphics().drawImage(this.treeLayer, this.currentAnimationx, this.currentAnimationy, null);
                g.drawImage(this.finalImage, 0, 0, null);
            }
            else {
                RTreeList.this.inAnimation = false;
            }
            return RTreeList.this.inAnimation;
        }
    }
}
