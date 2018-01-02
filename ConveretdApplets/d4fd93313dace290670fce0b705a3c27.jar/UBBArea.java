import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBArea implements UBBComponent, UBBImageListener
{
    protected String name;
    protected UBB host;
    protected UBBComponent container;
    protected String[][] attributes;
    protected Rectangle bounds;
    protected Rectangle updateArea;
    protected boolean hidden;
    protected boolean backgroundClear;
    protected Color backgroundColor;
    protected UBBImageProxy backgroundImage;
    protected int bgX;
    protected int bgY;
    protected boolean bgTile;
    protected boolean bypassInit;
    protected boolean drawUpdatePending;
    protected Image image;
    protected UBBErrorHandler error;
    protected UBBTextTools textTools;
    protected UBBImageFactory imageFactory;
    protected UBBAnimationTimer animation;
    protected boolean animationFrameUpdate;
    protected boolean appendStates;
    protected String[][][] subtagAttributes;
    protected int[][] subtagAttributesType;
    protected static final int SUBTAG_STATE_ALL = 0;
    protected static final int SUBTAG_STATE_BASE = 1;
    protected static final int SUBTAG_STATE_ENTER = 2;
    protected static final int SUBTAG_STATE_PRESSED = 3;
    protected static final int SUBTAG_STATE_ENTERPRESSED = 4;
    protected static final int SUBTAG_ACTION_ENTER = 5;
    protected static final int SUBTAG_ACTION_EXIT = 6;
    protected static final int SUBTAG_ACTION_PRESSED = 7;
    protected static final int SUBTAG_ACTION_RELEASED = 8;
    protected static final int SUBTAG_ACTION_STUCK = 9;
    protected static final int SUBTAG_ACTION_UNSTUCK = 10;
    protected Vector listenersVector;
    protected UBBComponentListener[] listeners;
    protected Vector childrenVector;
    protected UBBComponent[] children;
    protected int tipTime;
    protected UBBTipTimer tipTimer;
    protected UBBTextTools.FormattedText tipText;
    protected int mouseModifier;
    protected boolean appendActions;
    protected String[][][] actions;
    protected static final int ACTION_ENTER = 0;
    protected static final int ACTION_EXIT = 1;
    protected static final int ACTION_PRESSED = 2;
    protected static final int ACTION_RELEASED = 3;
    protected static final int ACTION_STUCK = 4;
    protected static final int ACTION_UNSTUCK = 5;
    protected static final int CHILD_CLICK = 1;
    protected static final int STATE_UNDEFINED = 0;
    protected static final int STATE_BUTTON_OFF = 1;
    protected static final int STATE_BUTTON_ON = 2;
    protected static final int STATE_BUTTON_STICKY_NEVER = 4;
    protected static final int STATE_BUTTON_STICKY_ALWAYS = 8;
    protected static final int STATE_BUTTON_STICKY_POPUP = 16;
    
    public void setContainer(final UBBComponent container) {
        this.container = container;
    }
    
    public UBBComponent getContainer() {
        return this.container;
    }
    
    public void stop() {
        this.startChildren(false);
        if (this.tipTimer != null) {
            this.tipTimer.stop();
        }
    }
    
    public Rectangle getUpdateArea() {
        return this.updateArea;
    }
    
    public int getState() {
        return 0;
    }
    
    public boolean setState(final String s, final int n) {
        boolean b = false;
        String s2 = null;
        if (s != null && this.name != null && !s.equals(this.name)) {
            s2 = s;
        }
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                if (this.children[i].setState(s2, n)) {
                    b = true;
                }
            }
        }
        if (b) {
            this.updateUBBListeners(this.drawUpdatePending = true);
            return true;
        }
        return false;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void addUBBComponent(final UBBComponent ubbComponent, final int x, final int y) {
        if (ubbComponent != null) {
            if (ubbComponent.getContainer() == null) {
                ubbComponent.setContainer(this);
            }
            else {
                this.error.notify(this.name, 0, ubbComponent.getName() + " already has container " + ubbComponent.getContainer().getName(), null);
            }
            if (this.childrenVector == null) {
                this.childrenVector = new Vector(5, 5);
            }
            final Rectangle bounds = ubbComponent.getBounds();
            bounds.x = x;
            bounds.y = y;
            this.childrenVector.addElement(ubbComponent);
            ubbComponent.addUBBComponentListener(this);
        }
    }
    
    public void drawBackground(final Graphics graphics, final Rectangle rectangle) {
        if (graphics != null) {
            if (this.backgroundImage != null || this.backgroundColor != null) {
                if (this.backgroundColor != null) {
                    graphics.setColor(this.backgroundColor);
                    graphics.fillRect(0, 0, rectangle.width, rectangle.height);
                }
                if (this.backgroundImage != null && this.backgroundImage.isReady()) {
                    if (this.bgTile) {
                        this.backgroundImage.tile(graphics, -rectangle.x + this.bgX, -rectangle.y + this.bgY, this.bounds.width, this.bounds.height, this.backgroundColor);
                        return;
                    }
                    graphics.drawImage(this.backgroundImage.getImage(), -rectangle.x + this.bgX, -rectangle.y + this.bgY, null);
                }
            }
            else {
                if (this.container != null) {
                    this.container.drawBackground(graphics, new Rectangle(rectangle.x + this.bounds.x, rectangle.y + this.bounds.y, rectangle.width, rectangle.height));
                    return;
                }
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, rectangle.width, rectangle.height);
            }
        }
    }
    
    public String[][] create() {
        int n = -1;
        final String[][] array = new String[(this.attributes != null) ? this.attributes.length : 1][2];
        if (this.container != null && this.container.isHidden()) {
            this.hidden = true;
        }
        if (this.childrenVector != null) {
            this.children = new UBBComponent[this.childrenVector.size()];
            this.childrenVector.copyInto(this.children);
            this.childrenVector.removeAllElements();
            this.childrenVector = null;
        }
        if (this.bounds.x < 0) {
            if (this.container == null) {
                final Rectangle bounds = this.bounds;
                bounds.x += this.host.getBounds().width;
            }
            else {
                final Rectangle bounds2 = this.bounds;
                bounds2.x += this.container.getBounds().width;
            }
        }
        if (this.bounds.y < 0) {
            if (this.container == null) {
                final Rectangle bounds3 = this.bounds;
                bounds3.y += this.host.getBounds().height;
            }
            else {
                final Rectangle bounds4 = this.bounds;
                bounds4.y += this.container.getBounds().height;
            }
        }
        if (this.bounds.width <= 0) {
            if (this.container == null) {
                final Rectangle bounds5 = this.bounds;
                bounds5.width += this.host.getBounds().width - this.bounds.x;
            }
            else {
                final Rectangle bounds6 = this.bounds;
                bounds6.width += this.container.getBounds().width - this.bounds.x;
            }
            if (this.bounds.width <= 0) {
                this.bounds.width = 5;
            }
        }
        if (this.bounds.height <= 0) {
            if (this.container == null) {
                final Rectangle bounds7 = this.bounds;
                bounds7.height += this.host.getBounds().height - this.bounds.y;
            }
            else {
                final Rectangle bounds8 = this.bounds;
                bounds8.height += this.container.getBounds().height - this.bounds.y;
            }
            if (this.bounds.height <= 0) {
                this.bounds.height = 5;
            }
        }
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.length; ++i) {
                final String s = this.attributes[i][0];
                final String s2 = this.attributes[i][1];
                if (s != null && s2 != null) {
                    final int hashString = UBB.hashString(s);
                    try {
                        if (hashString == 6321) {
                            if (s2.toLowerCase().equals("none")) {
                                this.backgroundImage = null;
                            }
                            this.backgroundImage = this.imageFactory.loadImage(s2);
                        }
                        else if (hashString == 23768) {
                            this.appendActions = (s2.charAt(0) == 't');
                        }
                        else if (hashString == 20747) {
                            this.appendStates = (s2.charAt(0) == 't');
                        }
                        else if (hashString == 6350) {
                            if (s2.toLowerCase().equals("clear")) {
                                this.backgroundClear = true;
                                this.backgroundColor = null;
                            }
                            else {
                                this.backgroundClear = false;
                                this.backgroundColor = UBBImageFactory.parseColor(s2);
                            }
                        }
                        else if (hashString == 4873) {
                            this.bgTile = (s2.charAt(0) == 't');
                        }
                        else if (hashString == 4972) {
                            this.hidden = (s2.charAt(0) == 't');
                        }
                        else if (hashString == 1523) {
                            this.bgX = Integer.parseInt(s2);
                        }
                        else if (hashString == 1525) {
                            this.bgY = Integer.parseInt(s2);
                        }
                        else if (hashString == 7119) {
                            this.tipText = this.textTools.formatText(s2, 0, 0, "Dialog", 10, 0, null);
                        }
                        else if (hashString == 2716) {
                            this.tipTime = 750;
                            if (s2.charAt(0) != 't') {
                                this.tipTime = Integer.parseInt(s2);
                            }
                        }
                        else if (hashString == 4901) {
                            if (s2.toLowerCase().charAt(0) == 'c') {
                                this.mouseModifier ^= 0x80;
                            }
                        }
                        else {
                            array[++n][0] = s;
                            array[n][1] = s2;
                        }
                    }
                    catch (Exception ex) {
                        this.error.notify(this.name, 1, "AREA attribute error " + s + "=" + s2, ex);
                    }
                }
            }
        }
        int n2 = 5;
        int n3 = 0;
        do {
            final String[][] subtagAttributes = this.getSubtagAttributes(n2++, 0, true);
            if (subtagAttributes != null) {
                if (this.actions == null) {
                    this.actions = new String[6][][];
                }
                this.actions[n3] = subtagAttributes;
            }
        } while (++n3 <= 5);
        if (!this.bounds.isEmpty() && (this.backgroundColor != null || this.backgroundImage != null)) {
            this.image = this.host.createImage(this.bounds.width, this.bounds.height);
            this.drawUpdatePending = true;
        }
        this.updateArea = this.bounds;
        if (!this.bypassInit && this.children != null) {
            for (int j = 0; j < this.children.length; ++j) {
                final String[][] create = this.children[j].create();
                if (create != null) {
                    for (int k = 0; k < create.length; ++k) {
                        if (create[k][0] != null) {
                            this.error.notify(this.children[j].getName(), 1, "Bad attribute " + create[k][0] + "=" + create[k][1], null);
                        }
                    }
                }
            }
        }
        if (this.tipTime > 0) {
            if (this.container == null) {
                this.tipTimer = new UBBTipTimer(this, this.tipTime);
            }
            else {
                this.error.notify(this.name, 1, "tips ignored (not a top-level AREA)", null);
            }
        }
        if (n > -1) {
            return array;
        }
        return null;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (graphics != null && !this.hidden) {
            if (this.image != null) {
                if (this.drawUpdatePending) {
                    this.draw();
                }
                else if (this.tipTimer != null) {
                    final Graphics graphics2 = this.image.getGraphics();
                    if (graphics2 != null) {
                        this.tipTimer.paint(graphics2);
                        graphics2.dispose();
                    }
                }
                graphics.drawImage(this.image, this.bounds.x, this.bounds.y, null);
                return;
            }
            graphics.translate(this.bounds.x, this.bounds.y);
            this.drawChildren(graphics);
            graphics.translate(-this.bounds.x, -this.bounds.y);
            this.drawUpdatePending = false;
        }
    }
    
    public boolean animationUpdate() {
        boolean b = false;
        int length = 0;
        try {
            if (this.children != null) {
                length = this.children.length;
                for (int i = 0; i < length; ++i) {
                    if (this.children[i].animationUpdate()) {
                        b = true;
                    }
                }
            }
            if (this.animationFrameUpdate || b) {
                if (length > 0 && this.image != null) {
                    final Graphics graphics = this.image.getGraphics();
                    if (graphics != null) {
                        for (int j = 0; j < length; ++j) {
                            this.children[j].paint(graphics);
                        }
                        graphics.dispose();
                    }
                }
                this.animationFrameUpdate = false;
                return true;
            }
        }
        catch (Exception ex) {
            this.error.notify(this.name, 0, "animationUpdate", ex);
        }
        return false;
    }
    
    public UBBArea() {
        this.backgroundColor = Color.white;
        this.appendStates = true;
        this.mouseModifier = 128;
        this.appendActions = true;
    }
    
    public synchronized void draw() {
        if (this.image != null) {
            final Graphics graphics = this.image.getGraphics();
            if (graphics != null) {
                if (this.backgroundColor != null) {
                    graphics.setColor(this.backgroundColor);
                    graphics.fillRect(0, 0, this.bounds.width, this.bounds.height);
                }
                if (this.backgroundImage != null && this.backgroundImage.isReady()) {
                    if (this.bgTile) {
                        this.backgroundImage.tile(graphics, this.bgX, this.bgY, this.bounds.width, this.bounds.height, this.backgroundColor);
                    }
                    else {
                        graphics.drawImage(this.backgroundImage.getImage(), this.bgX, this.bgY, null);
                    }
                }
                this.drawChildren(graphics);
                if (this.tipTimer != null) {
                    this.tipTimer.paint(graphics);
                }
                graphics.dispose();
                this.drawUpdatePending = false;
            }
        }
    }
    
    public void destroy() {
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                this.children[i].destroy();
            }
        }
        if (this.backgroundImage != null) {
            this.backgroundImage.destroy();
            this.backgroundImage = null;
        }
        if (this.image != null) {
            this.image.flush();
            this.image = null;
        }
        if (this.listenersVector != null) {
            this.listenersVector.removeAllElements();
            this.listenersVector = null;
        }
        this.listeners = null;
    }
    
    public Point getAbsPosition() {
        Point absPosition;
        if (this.container == null) {
            absPosition = new Point(0, 0);
        }
        else {
            absPosition = this.container.getAbsPosition();
        }
        if (absPosition != null && this.bounds != null) {
            final Point point = absPosition;
            point.x += this.bounds.x;
            final Point point2 = absPosition;
            point2.y += this.bounds.y;
        }
        return absPosition;
    }
    
    public void childEvent(final UBBComponent ubbComponent, final int n) {
    }
    
    private void drawChildren(final Graphics graphics) {
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                try {
                    this.children[i].paint(graphics);
                }
                catch (Exception ex) {
                    this.error.notify(this.name, 0, "draw " + this.children[i].getName(), ex);
                }
            }
        }
    }
    
    protected void startChildren(final boolean b) {
        if (this.children != null) {
            UBBComponent ubbComponent = null;
            for (int i = 0; i < this.children.length; ++i) {
                try {
                    ubbComponent = this.children[i];
                    if (b) {
                        ubbComponent.start();
                    }
                    else {
                        ubbComponent.stop();
                    }
                }
                catch (Exception ex) {
                    this.error.notify(this.name, 0, (b ? "Starting " : "Stopping ") + ubbComponent.getName(), null);
                }
            }
        }
    }
    
    public void start() {
        this.attributes = null;
        this.subtagAttributes = null;
        this.subtagAttributesType = null;
        if (this.tipTimer != null) {
            this.tipTimer.start();
        }
        this.startChildren(true);
        if (this.tipTimer != null) {
            this.host.regMouseListener(this, true);
        }
    }
    
    public void initTipText(final Vector vector, final int n, final int n2) {
        if (this.children != null) {
            for (int i = 0; i < this.children.length; ++i) {
                this.children[i].initTipText(vector, n + this.bounds.x, n2 + this.bounds.y);
            }
        }
        if (this.tipText != null) {
            vector.addElement(this);
            vector.addElement(this.tipText);
            vector.addElement(new Rectangle(this.bounds.x + n, this.bounds.y + n2, this.bounds.width, this.bounds.height));
        }
    }
    
    public void addUBBComponentListener(final UBBComponentListener ubbComponentListener) {
        if (this.listenersVector == null) {
            this.listenersVector = new Vector(1, 3);
        }
        if (this.listenersVector != null) {
            this.listenersVector.addElement(ubbComponentListener);
            return;
        }
        this.error.notify(this.name, 0, "addUBBComponentListener", null);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String[][] getSubtagAttributes(final int n, final int n2, final boolean b) {
        String[][] subtagAttributes = null;
        if (this.container != null && ((b && this.appendActions) || (!b && this.appendStates))) {
            subtagAttributes = this.container.getSubtagAttributes(n, n2, b);
        }
        if (subtagAttributes != null) {
            if (this.subtagAttributes != null) {
                int n3;
                for (n3 = 0; n3 < this.subtagAttributesType.length && (this.subtagAttributesType[n3][0] != n || this.subtagAttributesType[n3][1] != n2); ++n3) {}
                if (n3 < this.subtagAttributesType.length) {
                    final String[][] array = new String[subtagAttributes.length + this.subtagAttributes[n3].length][2];
                    int n4 = 0;
                    for (int i = 0; i < subtagAttributes.length; ++i) {
                        array[n4] = subtagAttributes[i];
                        ++n4;
                    }
                    for (int j = 0; j < this.subtagAttributes[n3].length; ++j) {
                        array[n4] = this.subtagAttributes[n3][j];
                        ++n4;
                    }
                    subtagAttributes = array;
                }
            }
        }
        else if (this.subtagAttributes != null) {
            int n5;
            for (n5 = 0; n5 < this.subtagAttributesType.length && (this.subtagAttributesType[n5][0] != n || this.subtagAttributesType[n5][1] != n2); ++n5) {}
            if (n5 < this.subtagAttributesType.length) {
                subtagAttributes = this.subtagAttributes[n5];
            }
        }
        return subtagAttributes;
    }
    
    public Rectangle getBounds() {
        return this.bounds;
    }
    
    public void componentUpdate(final UBBComponent ubbComponent, final boolean b) {
        if (!this.drawUpdatePending) {
            if (b) {
                this.drawUpdatePending = true;
            }
            try {
                if (!ubbComponent.getUpdateArea().isEmpty() && this.image != null) {
                    final Graphics graphics = this.image.getGraphics();
                    if (graphics != null) {
                        try {
                            ubbComponent.paint(graphics);
                        }
                        catch (Exception ex) {
                            this.error.notify(this.name, 0, "componentUpdate paint", null);
                        }
                        graphics.dispose();
                    }
                }
            }
            catch (Exception ex2) {}
            try {
                this.updateUBBListeners(b && this.image == null);
            }
            catch (Exception ex3) {
                this.error.notify(this.name, 0, "componentUpdate notification", null);
            }
        }
    }
    
    public void init(final UBB host, final String name, final int n, final int n2, final String[][] attributes, final String s, final UBBTag[] array, final UBBImageFactory imageFactory, final UBBTextTools textTools, final UBBAnimationTimer animation, final UBBErrorHandler error, final boolean bypassInit) {
        this.host = host;
        this.name = name;
        this.bounds = new Rectangle(0, 0, n, n2);
        this.attributes = attributes;
        this.imageFactory = imageFactory;
        this.textTools = textTools;
        this.animation = animation;
        this.error = error;
        this.bypassInit = bypassInit;
        if (array != null) {
            final Vector<Integer> vector = new Vector<Integer>(15);
            for (int i = 0; i < array.length; ++i) {
                int n3;
                if (array[i].tag.equals("state")) {
                    n3 = 0;
                }
                else if (array[i].tag.equals("action")) {
                    n3 = 1;
                }
                else {
                    n3 = -1;
                }
                if (n3 != -1) {
                    int n4 = 0;
                    final String[][] attribute = array[i].attribute;
                    for (int j = 0; j < ((Integer)(Object)attribute).length; ++j) {
                        final String s2 = attribute[j][0];
                        final String s3 = attribute[j][1];
                        if (s2 != null && s3 != null && s3.equals("-")) {
                            int n5 = -1;
                            if (n3 == 0) {
                                if (s2.startsWith("all")) {
                                    n5 = 0;
                                    n4 = 3;
                                }
                                else if (s2.startsWith("out")) {
                                    n5 = 1;
                                    n4 = 3;
                                }
                                else if (s2.startsWith("overs")) {
                                    n5 = 4;
                                    n4 = 9;
                                }
                                else if (s2.startsWith("over")) {
                                    n5 = 2;
                                    n4 = 4;
                                }
                                else if (s2.startsWith("down")) {
                                    n5 = 3;
                                    n4 = 4;
                                }
                            }
                            else if (n3 == 1) {
                                if (s2.startsWith("over")) {
                                    n5 = 5;
                                    n4 = 4;
                                }
                                else if (s2.startsWith("out")) {
                                    n5 = 6;
                                    n4 = 3;
                                }
                                else if (s2.startsWith("down")) {
                                    n5 = 7;
                                    n4 = 4;
                                }
                                else if (s2.startsWith("click")) {
                                    n5 = 8;
                                    n4 = 5;
                                }
                                else if (s2.startsWith("stuck")) {
                                    n5 = 9;
                                    n4 = 5;
                                }
                                else if (s2.startsWith("unstuck")) {
                                    n5 = 10;
                                    n4 = 7;
                                }
                                else if (s2.equals("playstop")) {
                                    continue;
                                }
                            }
                            if (n5 > -1) {
                                attribute[j][0] = null;
                                vector.addElement((Integer)(Object)attribute);
                                vector.addElement(new Integer(n5));
                                if (s2.length() > n4) {
                                    try {
                                        vector.addElement(new Integer(Integer.parseInt(s2.substring(n4))));
                                    }
                                    catch (Exception ex) {
                                        vector.addElement(new Integer(0));
                                    }
                                }
                                else {
                                    vector.addElement(new Integer(0));
                                }
                            }
                            else {
                                error.notify(name, 1, "Unknown " + ((n3 == 0) ? "STATE" : "ACTION") + " " + s2, null);
                            }
                        }
                    }
                }
            }
            final int n6 = vector.size() / 3;
            this.subtagAttributes = new String[n6][][];
            this.subtagAttributesType = new int[n6][2];
            for (int k = 0; k < n6; ++k) {
                final String[][] array2 = (Object)vector.elementAt(k * 3);
                final int intValue = vector.elementAt(k * 3 + 1);
                final int intValue2 = vector.elementAt(k * 3 + 2);
                boolean b = false;
                for (int l = 0; l < k; ++l) {
                    if (this.subtagAttributesType[l][0] == intValue && this.subtagAttributesType[l][1] == intValue2) {
                        final String[][] array3 = this.subtagAttributes[l];
                        final String[][] array4 = new String[array3.length + array2.length][2];
                        int n7 = 0;
                        for (int n8 = 0; n8 < array3.length; ++n8) {
                            if (array3[n8][0] != null) {
                                array4[n7] = array3[n8];
                            }
                            ++n7;
                        }
                        for (int n9 = 0; n9 < array2.length; ++n9) {
                            array4[n7] = array2[n9];
                            ++n7;
                        }
                        this.subtagAttributes[l] = array4;
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    this.subtagAttributes[k] = array2;
                    this.subtagAttributesType[k][0] = intValue;
                    this.subtagAttributesType[k][1] = intValue2;
                }
                else {
                    this.subtagAttributesType[k][0] = -1;
                }
            }
            vector.removeAllElements();
        }
    }
    
    public boolean setHidden(final String s, final boolean hidden) {
        if (s == null || (this.name != null && s.equals(this.name))) {
            if (hidden != this.hidden) {
                this.hidden = hidden;
                if (this.children != null) {
                    for (int i = 0; i < this.children.length; ++i) {
                        this.children[i].setHidden(null, hidden);
                    }
                }
                if (s != null || !hidden) {
                    this.updateUBBListeners(true);
                }
            }
            return true;
        }
        if (this.children != null) {
            for (int j = 0; j < this.children.length; ++j) {
                if (this.children[j].setHidden(s, hidden)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int mouseEvent(final int n, final Point point) {
        if (this.tipTimer != null) {
            this.tipTimer.mouseEvent(n, point);
        }
        return this.mouseModifier;
    }
    
    public void updateUBBListeners(final boolean b) {
        if (this.listenersVector != null) {
            this.listeners = new UBBComponentListener[this.listenersVector.size()];
            this.listenersVector.copyInto(this.listeners);
            this.listenersVector.removeAllElements();
            this.listenersVector = null;
        }
        if (this.container == null) {
            this.draw();
        }
        if (this.listeners != null && this.bounds != null && !this.bounds.isEmpty()) {
            try {
                for (int i = 0; i < this.listeners.length; ++i) {
                    this.listeners[i].componentUpdate(this, b);
                }
            }
            catch (Exception ex) {
                this.error.notify(this.name, 1, "updateUBBListeners", ex);
            }
        }
    }
    
    public synchronized boolean clockEvent(final boolean b) {
        return false;
    }
    
    public void managedImageUpdate(final UBBImageListenerEvent ubbImageListenerEvent) {
    }
}
