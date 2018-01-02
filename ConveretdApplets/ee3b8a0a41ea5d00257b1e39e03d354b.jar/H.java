import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Component;
import I.I;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class H extends Container implements MouseListener, MouseMotionListener, Runnable
{
    private static String I;
    private boolean add;
    private boolean addMouseListener;
    private boolean addMouseMotionListener;
    private int append;
    private int booleanValue;
    Container charAt;
    private X clipRect;
    private X createImage;
    private X dispose;
    private V doLayout;
    private V drawImage;
    private Image[][] getBounds;
    private int[] getGraphics;
    private int getHeight;
    private int getParameter;
    private ztmPlayer getSize;
    private String getWidth;
    private int hasMoreTokens;
    private int intersection;
    private int length;
    private int nextToken;
    private int parseInt;
    private int parseLong;
    private int processMouseEvent;
    private boolean remove;
    private long setBounds;
    private long setEnabled;
    private boolean setLayout;
    private boolean setSize;
    volatile boolean Z;
    
    public H(final ztmPlayer getSize) {
        H.I = I.I.I(599);
        this.add = (null != getSize.s);
        if (this.add) {
            final String parameter = getSize.getParameter(I.I.I(607));
            this.addMouseListener = (null == parameter || Boolean.valueOf(parameter));
            try {
                this.setEnabled = Long.parseLong(getSize.getParameter(I.I.I(621)));
                if (this.setEnabled < 500L) {
                    this.setEnabled = 500L;
                }
            }
            catch (Exception ex) {
                this.setEnabled = 3000L;
            }
        }
        this.getSize = getSize;
        this.I();
        if (this.add) {
            new Thread(this, I.I.I(636)).start();
        }
    }
    
    public final void I() {
        final boolean b = false;
        final ztmPlayer getSize = this.getSize;
        if ((b ? 1 : 0) == ztmPlayer.C) {
            this.setSize = true;
        }
        final boolean length = false;
        this.processMouseEvent = (length ? 1 : 0);
        this.parseLong = (length ? 1 : 0);
        this.parseInt = (length ? 1 : 0);
        this.nextToken = (length ? 1 : 0);
        this.length = (length ? 1 : 0);
        this.remove = true;
        if (null != this.charAt) {
            this.remove(this.charAt);
        }
        this.setLayout(null);
        this.setBounds(0, 0, this.getSize.getSize().width, this.getSize.getSize().height);
        this.drawImage();
        this.setLayout = false;
        if (!this.add) {
            return;
        }
        if (!this.dispose(this.getSize.getParameter(I.I.I(464)))) {
            this.dispose(H.I);
        }
        if (this.add && !this.addMouseMotionListener && null == (this.doLayout = this.getGraphics(this.getSize.getSize().width - this.processMouseEvent))) {
            this.add = false;
            return;
        }
        final boolean b2 = false;
        this.intersection = (b2 ? 1 : 0);
        this.hasMoreTokens = (b2 ? 1 : 0);
        (this.charAt = new B(this)).setLayout(new FlowLayout(0, 0, 0));
        this.doLayout(this.getWidth);
        this.add(this.charAt);
        this.charAt.addMouseListener(this);
        this.charAt.addMouseMotionListener(this);
    }
    
    public final boolean Z() {
        return this.addMouseListener;
    }
    
    public final int C() {
        if (this.addMouseMotionListener) {
            return this.hasMoreTokens;
        }
        return this.intersection;
    }
    
    public final int B() {
        return this.append;
    }
    
    public final void add() {
        if (this.addMouseListener) {
            return;
        }
        if (this.remove) {
            this.height(true);
            this.intersection(0.0f);
        }
        this.remove = false;
    }
    
    public final void addMouseListener() {
        if (this.addMouseListener || this.remove) {
            return;
        }
        this.remove = false;
        final int n = 15;
        final int n2 = 40;
        for (int i = 0; i <= n; ++i) {
            if (!this.setLayout) {
                this.intersection(1.0f);
                this.remove = true;
                return;
            }
            this.intersection(i / n);
            try {
                Thread.sleep(n2);
            }
            catch (InterruptedException ex) {}
        }
        this.remove = true;
        this.height(false);
    }
    
    public final void setBounds(final int n, final int n2, final int parseInt, final int parseLong) {
        int length = 0;
        int nextToken = 0;
        this.parseInt = parseInt;
        this.parseLong = parseLong;
        Label_0331: {
            switch (this.append) {
                case 0: {
                    nextToken = 0;
                    switch (this.booleanValue) {
                        case 0: {
                            length = 0;
                            break Label_0331;
                        }
                        case 1: {
                            length = (parseInt - this.hasMoreTokens) / 2;
                            break Label_0331;
                        }
                        case 2: {
                            length = parseInt - this.hasMoreTokens;
                            break Label_0331;
                        }
                        default: {
                            break Label_0331;
                        }
                    }
                    break;
                }
                case 1: {
                    nextToken = parseLong - this.intersection;
                    switch (this.booleanValue) {
                        case 0: {
                            length = 0;
                            break Label_0331;
                        }
                        case 1: {
                            length = (parseInt - this.hasMoreTokens) / 2;
                            break Label_0331;
                        }
                        case 2: {
                            length = parseInt - this.hasMoreTokens;
                            break Label_0331;
                        }
                        default: {
                            break Label_0331;
                        }
                    }
                    break;
                }
                case 2: {
                    length = 0;
                    switch (this.booleanValue) {
                        case 0: {
                            nextToken = 0;
                            break Label_0331;
                        }
                        case 1: {
                            nextToken = (parseLong - this.intersection) / 2;
                            break Label_0331;
                        }
                        case 2: {
                            nextToken = parseLong - this.intersection;
                            break Label_0331;
                        }
                        default: {
                            break Label_0331;
                        }
                    }
                    break;
                }
                case 3: {
                    length = parseInt - this.hasMoreTokens;
                    switch (this.booleanValue) {
                        case 0: {
                            nextToken = 0;
                            break Label_0331;
                        }
                        case 1: {
                            nextToken = (parseLong - this.intersection) / 2;
                            break Label_0331;
                        }
                        case 2: {
                            nextToken = parseLong - this.intersection;
                            break Label_0331;
                        }
                        default: {
                            break Label_0331;
                        }
                    }
                    break;
                }
            }
        }
        if (null != this.charAt) {
            this.length = length;
            this.nextToken = nextToken;
            if (!this.addMouseListener) {
                if (this.setLayout) {
                    this.height(false);
                }
                this.remove = false;
                this.addMouseListener();
            }
            else {
                this.charAt.setBounds(this.length, this.nextToken, this.hasMoreTokens, this.intersection);
            }
        }
    }
    
    public final void I(final int n) {
        if (this.add) {
            switch (n) {
                case 1: {
                    this.clipRect.setEnabled(true);
                    break;
                }
                case 2: {
                    this.createImage.setEnabled(true);
                    break;
                }
                case 3: {
                    this.dispose.setEnabled(true);
                    break;
                }
            }
        }
    }
    
    public final void Z(final int n) {
        if (this.add) {
            switch (n) {
                case 1: {
                    this.clipRect.setEnabled(false);
                    break;
                }
                case 2: {
                    this.createImage.setEnabled(false);
                    break;
                }
                case 3: {
                    this.dispose.setEnabled(false);
                    break;
                }
            }
        }
    }
    
    public final void I(final float n) {
        if (this.add && null != this.doLayout && !this.Z) {
            this.doLayout.I(n);
        }
    }
    
    public final void I(final double n) {
        if (this.add && null != this.drawImage) {
            this.drawImage.I((float)n);
        }
    }
    
    private void addMouseMotionListener(final Component component) {
        if (this.addMouseMotionListener) {
            this.intersection += component.getSize().height;
            if (this.hasMoreTokens < component.getSize().width) {
                this.hasMoreTokens = component.getSize().width;
            }
        }
        else {
            this.hasMoreTokens += component.getSize().width;
            if (this.intersection < component.getSize().height) {
                this.intersection = component.getSize().height;
            }
        }
        this.charAt.setSize(this.hasMoreTokens, this.intersection);
        this.charAt.add(component);
        this.charAt.doLayout();
    }
    
    private void append() {
        this.addMouseMotionListener(this.clipRect);
        this.clipRect.addMouseListener(new D(this));
        this.clipRect.addMouseListener(this);
        this.clipRect.addMouseMotionListener(this);
    }
    
    private void booleanValue() {
        this.addMouseMotionListener(this.createImage);
        this.createImage.addMouseListener(new F(this));
        this.createImage.addMouseListener(this);
        this.createImage.addMouseMotionListener(this);
    }
    
    private void charAt() {
        if (this.setSize) {
            return;
        }
        this.addMouseMotionListener(this.dispose);
        this.dispose.addMouseListener(new J(this));
        this.dispose.addMouseListener(this);
        this.dispose.addMouseMotionListener(this);
    }
    
    private void clipRect() {
        this.addMouseMotionListener(this.drawImage);
        this.drawImage.addMouseListener(new S(this));
        this.drawImage.addMouseMotionListener(new A(this));
        this.drawImage.addMouseListener(this);
        this.drawImage.addMouseMotionListener(this);
    }
    
    private void createImage() {
        this.addMouseMotionListener(this.doLayout);
        this.doLayout.addMouseListener(new E(this));
        this.doLayout.addMouseMotionListener(new G(this));
        this.doLayout.addMouseListener(this);
        this.doLayout.addMouseMotionListener(this);
    }
    
    private boolean dispose(String lowerCase) {
        if (null == lowerCase) {
            return false;
        }
        lowerCase = lowerCase.toLowerCase();
        switch (lowerCase.charAt(0)) {
            case 'l': {
                this.append = 2;
                this.addMouseMotionListener = true;
                break;
            }
            case 'r': {
                this.append = 3;
                this.addMouseMotionListener = true;
                break;
            }
            case 't': {
                this.append = 0;
                this.addMouseMotionListener = false;
                break;
            }
            case 'b': {
                this.append = 1;
                this.addMouseMotionListener = false;
                break;
            }
            default: {
                return false;
            }
        }
        switch (lowerCase.charAt(1)) {
            case 'l':
            case 't': {
                this.booleanValue = 0;
                break;
            }
            case 'c': {
                this.booleanValue = 1;
                break;
            }
            case 'b':
            case 'r': {
                this.booleanValue = 2;
                break;
            }
            default: {
                return false;
            }
        }
        int n5;
        int n4;
        int n3;
        int n2;
        int n = n2 = (n3 = (n4 = (n5 = 0)));
        this.processMouseEvent = 0;
        this.getWidth = "";
        for (int i = 3; i < lowerCase.length(); ++i) {
            switch (lowerCase.charAt(i)) {
                case 'p': {
                    if (n2 != 0) {
                        return false;
                    }
                    if (!this.addMouseMotionListener) {
                        this.processMouseEvent += this.clipRect.getSize().width;
                    }
                    else {
                        this.processMouseEvent = 0;
                    }
                    this.getWidth += lowerCase.charAt(i);
                    n2 = 1;
                    break;
                }
                case 's': {
                    if (n != 0) {
                        return false;
                    }
                    if (!this.addMouseMotionListener) {
                        this.processMouseEvent += this.createImage.getSize().width;
                    }
                    else {
                        this.processMouseEvent = 0;
                    }
                    this.getWidth += lowerCase.charAt(i);
                    n = 1;
                    break;
                }
                case 'm': {
                    if (n3 != 0) {
                        return false;
                    }
                    if (!this.addMouseMotionListener) {
                        this.processMouseEvent += (this.setSize ? 0 : this.dispose.getSize().width);
                    }
                    else {
                        this.processMouseEvent = 0;
                    }
                    this.getWidth += lowerCase.charAt(i);
                    n3 = 1;
                    break;
                }
                case 'b': {
                    if (n4 != 0) {
                        return false;
                    }
                    if (!this.addMouseMotionListener) {
                        this.getWidth += lowerCase.charAt(i);
                        n4 = 1;
                        break;
                    }
                    break;
                }
                case 'v': {
                    if (n5 != 0) {
                        return false;
                    }
                    final int n6 = 100;
                    final V v = null;
                    final V graphics = this.getGraphics(n6);
                    this.drawImage = graphics;
                    if (v != graphics && !this.addMouseMotionListener) {
                        this.processMouseEvent += n6;
                        this.getWidth += lowerCase.charAt(i);
                        n5 = 1;
                        break;
                    }
                    break;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void doLayout(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case 'p': {
                    this.append();
                    break;
                }
                case 's': {
                    this.booleanValue();
                    break;
                }
                case 'm': {
                    this.charAt();
                    break;
                }
                case 'b': {
                    this.createImage();
                    break;
                }
                case 'v': {
                    this.clipRect();
                    break;
                }
            }
        }
    }
    
    private void drawImage() {
        if (this.add && null != this.getSize.s) {
            int n2;
            int n = n2 = 0;
            String s = this.getSize.getParameter(I.I.I(490));
            if (null == s) {
                s = I.I.I(509);
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, I.I.I(529));
            this.getGraphics = new int[8];
            try {
                while (stringTokenizer.hasMoreTokens() && n2 <= 8) {
                    final int n3 = n;
                    final int[] getGraphics = this.getGraphics;
                    final int n4 = n2;
                    final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                    getGraphics[n4] = int1;
                    n = n3 + int1;
                    ++n2;
                }
            }
            catch (Exception ex) {}
            if (8 != n2) {
                this.add = false;
            }
            if (this.add) {
                this.getParameter = this.getSize.j.getHeight(null) >> 1;
                this.getHeight = this.getSize.j.getWidth(null);
                if (n > this.getHeight) {
                    T.I(I.I.I(531));
                    this.add = false;
                }
            }
            if (this.add) {
                this.getBounds = new Image[8][2];
                for (int i = 0; i < 2; ++i) {
                    int j;
                    for (int n5 = j = 0; j < 8; ++j) {
                        this.getBounds[j][i] = this.createImage(new MemoryImageSource(this.getGraphics[j], this.getParameter, this.getSize.s, n5 + i * this.getHeight * this.getParameter, this.getHeight));
                        n5 += this.getGraphics[j];
                    }
                }
            }
        }
        if (this.add) {
            this.clipRect = new X(this.getBounds[0][0], this.getBounds[1][0], this);
            (this.createImage = new X(this.getBounds[2][0], this.getBounds[2][1], this)).setEnabled(true);
            this.dispose = new X(this.getBounds[7][0], this.getBounds[7][1], this);
        }
    }
    
    private Image getBounds(final int n, final Image image, final Image image2, final Image image3) {
        final Image image4 = this.getSize.createImage(n, image.getHeight(null));
        final Graphics graphics = image4.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        final int n2 = n - image2.getWidth(null);
        for (int width = image3.getWidth(null), i = image.getWidth(null); i < n2; i += width) {
            graphics.drawImage(image3, i, 0, null);
        }
        graphics.drawImage(image2, n2, 0, null);
        graphics.dispose();
        return image4;
    }
    
    private V getGraphics(final int n) {
        if (!this.add) {
            return null;
        }
        int n2 = n;
        final int n3 = this.getGraphics[3] + this.getGraphics[6] + this.getGraphics[5];
        if (n2 < n3) {
            n2 = n3;
        }
        final V v = new V(this.getBounds(n2, this.getBounds[3][0], this.getBounds[6][0], this.getBounds[5][0]), this.getBounds(n2, this.getBounds[3][1], this.getBounds[6][1], this.getBounds[5][1]), this.getBounds[4][0], this.getBounds[4][1], this.getSize, this.getGraphics[3], n2 - this.getGraphics[6]);
        v.setEnabled(true);
        return v;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.getParameter();
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        this.getParameter();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.getParameter();
        super.processMouseEvent(mouseEvent);
    }
    
    private synchronized void getParameter() {
        this.setBounds = this.setEnabled;
    }
    
    private synchronized void getSize(final long n) {
        this.setBounds -= n;
    }
    
    private synchronized long hasMoreTokens() {
        return this.setBounds;
    }
    
    private synchronized void height(final boolean setLayout) {
        this.setLayout = setLayout;
    }
    
    private void intersection(float n) {
        if (n > 1.0f) {
            n = 1.0f;
        }
        if (n < 0.0f) {
            n = 0.0f;
        }
        switch (this.append) {
            case 0: {
                this.charAt.setBounds(this.length, this.nextToken - (int)(n * this.intersection), this.hasMoreTokens, this.intersection);
                break;
            }
            case 1: {
                this.charAt.setBounds(this.length, this.nextToken + (int)(n * this.intersection), this.hasMoreTokens, this.intersection);
                break;
            }
            case 2: {
                this.charAt.setBounds(this.length - (int)(n * this.hasMoreTokens), this.nextToken, this.hasMoreTokens, this.intersection);
                break;
            }
            case 3: {
                this.charAt.setBounds(this.length + (int)(n * this.hasMoreTokens), this.nextToken, this.hasMoreTokens, this.intersection);
                break;
            }
        }
    }
    
    public final void run() {
        while (null != this.getSize.Q) {
            if (this.hasMoreTokens() > 0L) {
                this.getSize(100L);
                this.add();
            }
            else {
                this.addMouseListener();
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        this.getSize = null;
        this.doLayout = null;
    }
    
    final void I(final Graphics graphics) {
        if (!ztmPlayer.II) {
            final Rectangle bounds = this.charAt.getBounds();
            final Rectangle intersection = bounds.intersection(this.getSize.z.getBounds());
            graphics.clipRect((bounds.x < 0) ? (-bounds.x) : 0, (bounds.y < 0) ? (-bounds.y) : 0, intersection.getSize().width, intersection.getSize().height);
        }
    }
    
    static final ztmPlayer I(final H h) {
        return h.getSize;
    }
    
    static final V Z(final H h) {
        return h.drawImage;
    }
    
    static final V C(final H h) {
        return h.doLayout;
    }
}
