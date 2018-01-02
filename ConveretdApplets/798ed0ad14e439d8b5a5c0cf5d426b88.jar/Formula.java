import java.awt.FontMetrics;
import java.awt.AWTEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Formula implements MouseListener, MouseMotionListener, FocusListener, BoxComponent, BoxVisitor
{
    public static boolean LEFT_TO_RIGHT;
    public boolean mathRTL;
    public boolean displaystyle;
    public boolean fixReservedWords;
    static BoxScripting BuscaPosicioTipus;
    public AbstractBox lacapsa;
    public BoxPosition curpos;
    protected Vector Composa;
    protected BoxPosition I;
    protected BoxPosition Z;
    protected BoxPosition C;
    protected AbstractSelection B;
    static Formula DrawRectCorrecte;
    protected boolean MouScrollPerVeureCaret;
    protected boolean PosAnt;
    boolean D;
    boolean PosAnt2;
    protected static String F;
    private PaintListener paintListener;
    protected SelectionPainter J;
    boolean S;
    protected static String A;
    private FormulaTransferJ1_1 formulaTransfer;
    protected int E;
    public Color[] colors;
    public Point tl;
    private Image offImage;
    private Graphics offGraphics;
    private FontUtils fontUtil;
    private Point offLocation;
    private Dimension offMaxDimension;
    protected ScrollPaneWrapper G;
    private int perc;
    public AbstractBox mouseReleasedCapsa;
    public Rectangle currentBoxRectangle;
    public AbstractBox mouseExitedCapsa;
    protected boolean PosAnt_Fill;
    private ResourceProvider resourceProvider;
    public BoxComponent[] THIS;
    protected Component H;
    public String version;
    public static final Cursor HAND_CURSOR;
    public static final Cursor TEXT_CURSOR;
    public static final Cursor DEFAULT_CURSOR;
    public static final Cursor WAIT_CURSOR;
    public static final Font nonItalicFont;
    static Class PosSeg;
    
    public Formula(final ScrollPaneWrapper g, final Component h) {
        this.mathRTL = false;
        this.displaystyle = true;
        this.fixReservedWords = false;
        this.PosAnt = false;
        this.S = true;
        this.E = 0;
        this.fontUtil = null;
        this.offMaxDimension = new Dimension();
        this.perc = 100;
        this.mouseReleasedCapsa = null;
        this.mouseExitedCapsa = null;
        this.PosAnt_Fill = false;
        this.resourceProvider = null;
        this.THIS = new BoxComponent[] { this };
        this.colors = AbstractBox.default_colors.clone();
        this.fontUtil = new FontUtils();
        this.G = g;
        this.H = h;
        this.J = new SelectionPainter(this);
        if (this.H != null) {
            this.H.setBackground(this.colors[11]);
            this.H.addMouseListener(this);
            this.H.addMouseMotionListener(this);
            this.H.addFocusListener(this);
            this.addPaintListener(this.J);
        }
        if (this.G != null) {
            this.G.setUnitIncrement(10, 10);
        }
        this.B = null;
        this.MouScrollPerVeureCaret = true;
        this.D = false;
        this.PosAnt2 = false;
        this.setMargins(new Dimension(10, 16));
        this.formulaTransfer = new FormulaTransferJ1_1(this);
        if (OmegaSystem.versio_java >= 1) {
            this.setFormulaTransfer(null);
        }
    }
    
    public final void setFormulaTransfer(final Object formulaTransfer) {
        if (OmegaSystem.versio_java >= 1) {
            this.formulaTransfer.setFormulaTransfer(formulaTransfer);
        }
    }
    
    public final FormulaTransferJ1_1 getFormulaTransfer() {
        return this.formulaTransfer;
    }
    
    public final void setMargins(final Dimension dimension) {
        this.tl = new Point(dimension.width, dimension.height);
    }
    
    protected final boolean BuscaPosicioTipus() {
        return (this.MouScrollPerVeureCaret & this.PosAnt2) || this.PosAnt;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (Formula.DrawRectCorrecte != this && Formula.DrawRectCorrecte != null) {
            Formula.DrawRectCorrecte.PosAnt2 = false;
            Formula.DrawRectCorrecte.D = false;
            Formula.DrawRectCorrecte.lliberaOffScreen();
        }
        if (this.BuscaPosicioTipus()) {
            this.DEFAULT_CURSOR(this.getViewportRectangle());
        }
        if (OmegaSystem.versio_java == 0) {
            this.repaint();
        }
        this.D = true;
        this.PosAnt2 = true;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        Formula.DrawRectCorrecte = this;
        this.D = false;
    }
    
    public final void addPaintListener(final PaintListener paintListener) {
        this.paintListener = EventMulticaster.add(this.paintListener, paintListener);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        Rectangle rectangle = graphics.getClipBounds();
        if (rectangle == null) {
            return;
        }
        final Rectangle viewportRectangle = this.getViewportRectangle();
        if (viewportRectangle != null) {
            rectangle = rectangle.intersection(viewportRectangle);
        }
        if (rectangle.isEmpty()) {
            return;
        }
        final boolean b = this.BuscaPosicioTipus() && this.DEFAULT_CURSOR(rectangle);
        Graphics offGraphics;
        if (b) {
            offGraphics = this.offGraphics;
        }
        else {
            offGraphics = graphics;
        }
        AffineTransform transform = null;
        if (this.mathRTL) {
            final Graphics2D graphics2D = (Graphics2D)offGraphics;
            transform = graphics2D.getTransform();
            int n = offGraphics.getClipBounds().width;
            if (this.Z() > 0) {
                n = this.Z();
            }
            graphics2D.setTransform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, n, 0.0f));
        }
        if (this.paintListener != null) {
            this.paintListener.onPaint(this.H, offGraphics, true);
        }
        this.Composa(offGraphics);
        if (this.paintListener != null) {
            this.paintListener.onPaint(this.H, offGraphics, false);
        }
        if (this.mathRTL) {
            ((Graphics2D)graphics).setTransform(transform);
        }
        if (b) {
            graphics.drawImage(this.offImage, rectangle.x, rectangle.y, this.H);
        }
    }
    
    public final void print(final Graphics graphics) {
        AffineTransform transform = null;
        if (this.mathRTL) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            transform = graphics2D.getTransform();
            int n = graphics.getClipBounds().width;
            if (this.Z() > 0) {
                n = this.Z();
            }
            graphics2D.setTransform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, n, 0.0f));
        }
        this.Composa(graphics);
        if (this.mathRTL) {
            ((Graphics2D)graphics).setTransform(transform);
        }
    }
    
    public void repaint() {
        this.repaint((Rectangle)null);
    }
    
    public final void repaint(final AbstractBox abstractBox) {
        final Rectangle rectangleReal = abstractBox.RectangleReal();
        rectangleReal.translate(this.tl.x, this.tl.y);
        this.repaint(rectangleReal);
    }
    
    public final void repaint(final Rectangle rectangle) {
        if (this.lacapsa == null || this.H == null) {
            return;
        }
        if (this.H.getPeer() == null) {
            return;
        }
        if (this.G != null) {
            this.G.doLayout();
        }
        Rectangle bounds = rectangle;
        if (bounds == null) {
            bounds = this.H.getBounds();
        }
        this.H.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    protected final void Composa(final Graphics graphics) {
        final AbstractBox lacapsa = this.lacapsa;
        if (OmegaSystem.versio_java >= 2) {
            IsGraphics2D.setAntialiasing(graphics, true, OmegaSystem.versio_java);
        }
        if (lacapsa == null) {
            graphics.drawString("Rendering content...", 0, 20);
            return;
        }
        final Rectangle clipBounds = graphics.getClipBounds();
        if (this.colors[11] != null) {
            graphics.setColor(this.colors[11]);
            graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        graphics.translate(this.tl.x, this.tl.y);
        try {
            lacapsa.DrawRectCorrecte(this, graphics);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        graphics.translate(-this.tl.x, -this.tl.y);
        this.plotCurrentBox(graphics);
    }
    
    private void plotCurrentBox(final Graphics graphics) {
        if (this.currentBoxRectangle != null) {
            graphics.setColor(new Color(200, 200, 255));
            graphics.drawRect(this.currentBoxRectangle.x - 1, this.currentBoxRectangle.y - 1, this.currentBoxRectangle.width + 2, this.currentBoxRectangle.height + 2);
        }
    }
    
    protected final Rectangle I() {
        if (this.curpos == null) {
            return null;
        }
        if (!this.isEditablePosition(this.curpos)) {
            return null;
        }
        AbstractBox parentBox;
        for (AbstractBox c = this.curpos.c; c != null; c = parentBox) {
            parentBox = c.getParentBox();
            if (c instanceof TokensBox && parentBox instanceof TokensBox) {
                final Point posicioReal = c.PosicioReal();
                return new Rectangle(this.tl.x + posicioReal.x, this.tl.y + posicioReal.y, c.width, c.height);
            }
        }
        return null;
    }
    
    public final Rectangle getViewportRectangle() {
        if (this.G != null) {
            return new Rectangle(this.G.getScrollPosition(), this.G.getViewportSize());
        }
        if (this.H == null) {
            return null;
        }
        final Point location = this.H.getLocation();
        return new Rectangle(new Point(-location.x, -location.y), this.H.getSize());
    }
    
    private final boolean DEFAULT_CURSOR(final Rectangle rectangle) {
        if (this.H == null || rectangle.width <= 0 || rectangle.height <= 0) {
            return false;
        }
        final Dimension size = rectangle.getSize();
        if (this.offGraphics == null || size.width > this.offMaxDimension.width || size.height > this.offMaxDimension.height) {
            size.width = Math.max(size.width, this.offMaxDimension.width);
            size.height = Math.max(size.height, this.offMaxDimension.height);
            if (this.offGraphics != null) {
                this.offGraphics.dispose();
            }
            this.offImage = IsGraphics2D.createImage(this.H, size.width, size.height);
            if (this.offImage == null) {
                return false;
            }
            this.offGraphics = this.offImage.getGraphics();
            this.offMaxDimension = size;
            this.offLocation = new Point();
        }
        this.offGraphics.translate(this.offLocation.x - rectangle.x, this.offLocation.y - rectangle.y);
        this.offGraphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.offLocation = rectangle.getLocation();
        return true;
    }
    
    public final void lliberaOffScreen() {
        if (this.offImage != null) {
            this.offImage = null;
        }
        if (this.offGraphics != null) {
            this.offGraphics.dispose();
            this.offGraphics = null;
        }
    }
    
    public final AbstractSelection getSelectedArea() {
        return this.B;
    }
    
    public final void unselectArea() {
        this.B = null;
        this.C = null;
        this.J.update();
    }
    
    public final boolean isSelectionEmpty() {
        return this.B == null;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.I(mouseEvent)) {
            return;
        }
        this.E = 0;
        if (this.lacapsa == null) {
            return;
        }
        this.S = true;
        final Point point = new Point(this.I(mouseEvent.getPoint()));
        point.translate(-this.tl.x, -this.tl.y);
        if ((mouseEvent.getModifiers() & 0x10) != 0x0 && this.lacapsa.deepExplore(this, 2, point.x, point.y) != null) {
            return;
        }
        if (this.curpos != null && mouseEvent.getClickCount() == 2) {
            BoxPosition boxPosition = I(this.curpos);
            BoxPosition boxPosition2 = Z(this.curpos);
            if (!this.isEditablePosition(boxPosition2)) {
                boxPosition2 = this.curpos;
            }
            if (!this.isEditablePosition(boxPosition)) {
                boxPosition = this.curpos;
            }
            if (this.isEditablePosition(boxPosition) && this.isEditablePosition(boxPosition2)) {
                this.B = AbstractSelection.Selecciona(boxPosition, boxPosition2, this);
                this.E = 65536;
                if (this.B != null && !this.B.buida()) {
                    this.Z = this.curpos;
                    this.C = this.curpos;
                    this.J.update();
                }
            }
            return;
        }
        this.setCaret(this.I(mouseEvent.getPoint()));
        this.I = this.curpos;
        if (this.B != null) {
            if (this.B.getRectangles(this).contains(point) && this.B.espotcopiar()) {
                this.E = 131072;
                if (OmegaSystem.versio_java == 0) {
                    Formula.A = this.script(this.B);
                    this.setCursor(Formula.HAND_CURSOR);
                }
            }
            else {
                this.unselectArea();
                this.E = 65536;
            }
        }
        else {
            this.E = 65536;
        }
        if (this.E == 65536) {
            this.Z = this.findPositonFromPoint(point, true);
            if (this.Z != null) {
                this.C = new BoxPosition(this.Z);
            }
            else {
                this.E = 0;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.mouseReleasedCapsa != null) {
            this.mouseReleasedCapsa.processEvent(this, mouseEvent);
            this.mouseReleasedCapsa = null;
        }
        this.E = 0;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.S) {
            this.S = true;
            return;
        }
        final Point i = this.I(mouseEvent.getPoint());
        i.translate(-this.tl.x, -this.tl.y);
        if (this.lacapsa.deepExplore(this, 3, i.x, i.y) != null) {
            return;
        }
        BoxPosition boxPosition = this.findPositonFromPoint(i, true);
        if (boxPosition == null) {
            boxPosition = this.curpos;
        }
        this.C = boxPosition;
        if (this.C == null) {
            return;
        }
        if (this.E == 65536) {
            final AbstractSelection b = this.B;
            this.B = null;
            this.B = AbstractSelection.Selecciona(this.Z, boxPosition, this);
            this.B = this.I(this.B, b);
            this.J.update();
        }
        this.setCaret(boxPosition);
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (this.lacapsa == null) {
            return;
        }
        final Point i = this.I(mouseEvent.getPoint());
        i.translate(-this.tl.x, -this.tl.y);
        if (this.mouseExitedCapsa != null) {
            final Point posicioReal = this.mouseExitedCapsa.PosicioReal();
            if (!this.mouseExitedCapsa.esInterior(i.x - posicioReal.x, i.y - posicioReal.y)) {
                this.mouseExitedCapsa.onExited(this);
                this.mouseExitedCapsa = null;
            }
        }
        this.mouseExitedCapsa = this.lacapsa.deepExplore(this, 1, i.x, i.y);
        if (this.mouseExitedCapsa != null) {
            return;
        }
    }
    
    protected boolean I(final InputEvent inputEvent) {
        return false;
    }
    
    protected AbstractSelection I(final AbstractSelection abstractSelection, final AbstractSelection abstractSelection2) {
        return abstractSelection;
    }
    
    public void MouScrollPerVeureCaret() {
    }
    
    public Dimension getPreferredSize() {
        if (this.lacapsa == null) {
            return new Dimension();
        }
        return new Dimension(this.lacapsa.width + 2 * this.tl.x, this.lacapsa.height + 2 * this.tl.y);
    }
    
    public void setCaret(final BoxPosition curpos) {
        final BoxPosition curpos2 = this.curpos;
        this.curpos = curpos;
        if (!this.I(this.curpos, false)) {
            this.resetPosition();
        }
        else {
            this.notifyChangePosition(curpos2, this.curpos);
        }
    }
    
    public BoxPosition getCaret() {
        return this.curpos;
    }
    
    public final void setCaret(Point point) {
        final BoxPosition curpos = this.curpos;
        point = new Point(point);
        point.translate(-this.tl.x, -this.tl.y);
        this.notifyChangePosition(curpos, this.curpos = this.findPositonFromPoint(point, false));
    }
    
    protected void notifyChangePosition(final BoxPosition boxPosition, final BoxPosition boxPosition2) {
        if (!Utils.equals(boxPosition, boxPosition2)) {
            if (boxPosition != null && boxPosition.c != null) {
                boxPosition.c.focusLost(boxPosition2, this);
            }
            this.MouScrollPerVeureCaret();
        }
    }
    
    public Font getDefaultFont(final int n, final AbstractBox abstractBox) {
        return this.fontUtil.getCurrentFont(n, abstractBox);
    }
    
    public final void setFontSize(final int fontSize) {
        this.fontUtil.setFontSize(fontSize);
    }
    
    public final void setDefaultFont(final int n, final Font font) {
        this.fontUtil.setCurrentFont(n, font);
    }
    
    public int getDPI() {
        return 72 * this.perc / 100;
    }
    
    public Component getComponent() {
        return this.H;
    }
    
    public Point getOrigin() {
        return this.tl;
    }
    
    public FontMetrics getFontMetrics(final Font font) {
        if (this.H != null) {
            return this.H.getFontMetrics(font);
        }
        return IsGraphics2D.getFontMetrics(font);
    }
    
    public FontUtils getFontUtils() {
        if (this.fontUtil == null) {
            this.fontUtil = new FontUtils();
        }
        return this.fontUtil;
    }
    
    public final void setFontUtils(final FontUtils fontUtil) {
        if (fontUtil == null) {
            throw new NullPointerException();
        }
        this.fontUtil = fontUtil;
    }
    
    public ResourceProvider getResourceProvider() {
        if (this.resourceProvider == null) {
            this.resourceProvider = new ResourceProvider();
        }
        return this.resourceProvider;
    }
    
    public final void setResourceProvider(final ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }
    
    public void setColor(final Graphics graphics, final Color color) {
        graphics.setColor(color);
    }
    
    public void setString(final String s, final boolean b) {
        if (this.lacapsa != null) {
            this.unselectArea();
            this.lacapsa.notificaEsborrat(this);
        }
        final boolean b2 = b;
        BoxScripting buscaPosicioTipus;
        if (Formula.BuscaPosicioTipus.scriptMode == (b2 ? 1 : 0)) {
            buscaPosicioTipus = Formula.BuscaPosicioTipus;
        }
        else {
            buscaPosicioTipus = new BoxScripting(b2 ? 1 : 0);
        }
        final AbstractBox trimMainBox = this.trimMainBox(buscaPosicioTipus.parse(s));
        if (buscaPosicioTipus.getVersion() != null && buscaPosicioTipus.getVersion().length() > 0) {
            this.version = buscaPosicioTipus.getVersion();
            System.out.println("Content version format " + this.version);
        }
        trimMainBox.Composa(this);
        final BoxPosition andRemoveMarkup = trimMainBox.findAndRemoveMarkup(this, "\\caret");
        this.curpos = andRemoveMarkup;
        this.lacapsa = trimMainBox;
        if (andRemoveMarkup == null) {
            this.setCaret(new Point());
        }
        else {
            this.setCaret(andRemoveMarkup);
        }
    }
    
    public AbstractBox trimMainBox(AbstractBox trimMi) {
        if (this.fixReservedWords) {
            trimMi = this.trimMi(trimMi);
        }
        return trimMi;
    }
    
    private AbstractBox trimMi(final AbstractBox abstractBox) {
        if (abstractBox instanceof TextBox) {
            final TextBox textBox = (TextBox)abstractBox;
            if (textBox.type == 1 && textBox.fontMask == 0 && this.Composa.contains(textBox.cadena)) {
                textBox.fontMask = 2;
                textBox.fontPropia = Formula.nonItalicFont;
            }
        }
        for (int i = 0; i < abstractBox.nfills; ++i) {
            abstractBox.fill[i] = this.trimMi(abstractBox.fill[i]);
        }
        return abstractBox;
    }
    
    public boolean isEditablePosition(final BoxPosition boxPosition) {
        return false;
    }
    
    public final boolean isEditablePosition(final Point point) {
        point.translate(-this.tl.x, -this.tl.y);
        final BoxPosition posicioMesProperaGlobal = this.lacapsa.PosicioMesProperaGlobal(point, null);
        point.translate(this.tl.x, this.tl.y);
        return this.isEditablePosition(posicioMesProperaGlobal);
    }
    
    public boolean isEditable() {
        return false;
    }
    
    public BoxComponent[] getPointer() {
        return this.THIS;
    }
    
    public void finalitza() {
        this.paintListener = null;
        if (this.formulaTransfer != null) {
            this.formulaTransfer.unregister();
        }
        this.formulaTransfer = null;
        this.lacapsa = null;
        this.THIS[0] = null;
    }
    
    public AbstractBox parse(final String s) {
        return staticParse(s);
    }
    
    public static final AbstractBox staticParse(String string) {
        if (XMLOutput.countChildren(string) != 1) {
            string = "<mrow>" + string + "</mrow>";
        }
        return Formula.BuscaPosicioTipus.parse(string);
    }
    
    static final BoxPosition I(final BoxPosition boxPosition) {
        final BoxPosition buscaPosicioTipus = boxPosition.BuscaPosicioTipus((Formula.PosSeg == null) ? (Formula.PosSeg = I("TokensBox")) : Formula.PosSeg);
        if (buscaPosicioTipus.c instanceof TokensBox) {
            if (boxPosition.equals(buscaPosicioTipus)) {
                final BoxPosition boxPosition2 = buscaPosicioTipus;
                --boxPosition2.x;
            }
            return buscaPosicioTipus.c.PosAnt_Fill(buscaPosicioTipus.x);
        }
        return boxPosition.c.PosAnt(boxPosition.x);
    }
    
    static final BoxPosition Z(final BoxPosition boxPosition) {
        final BoxPosition buscaPosicioTipus = boxPosition.BuscaPosicioTipus((Formula.PosSeg == null) ? (Formula.PosSeg = I("TokensBox")) : Formula.PosSeg);
        if (buscaPosicioTipus.c instanceof TokensBox) {
            return buscaPosicioTipus.c.PosSeg_Fill(buscaPosicioTipus.x);
        }
        return boxPosition.c.PosSeg(boxPosition.x);
    }
    
    public BoxScripting getBoxScripting() {
        return new BoxScripting(1);
    }
    
    public String script(final AbstractBox abstractBox) {
        final BoxScripting boxScripting = this.getBoxScripting();
        abstractBox.script(boxScripting);
        return boxScripting.toString();
    }
    
    public final String script(final AbstractSelection abstractSelection) {
        final BoxScripting boxScripting = this.getBoxScripting();
        abstractSelection.script(boxScripting);
        return boxScripting.toString();
    }
    
    public void setCursor(final Cursor cursor) {
        if (this.H != null) {
            this.H.setCursor(cursor);
        }
    }
    
    public final void requestFocus() {
        if (this.H != null) {
            this.H.requestFocus();
        }
    }
    
    public Color getColor(final int n, final AbstractBox abstractBox) {
        if (n != 13 || abstractBox == null) {
            return this.colors[n];
        }
        if (abstractBox.error != null) {
            return this.colors[7];
        }
        return null;
    }
    
    public boolean isDesign() {
        return this.PosAnt_Fill;
    }
    
    public void setDesign(final boolean b) {
    }
    
    public int preVisitBox(final AbstractBox abstractBox, final int n, final int n2, final int n3) {
        if (abstractBox.esInterior(n2, n3)) {
            return 0;
        }
        return -1;
    }
    
    public int postVisitBox(final AbstractBox abstractBox, final int n, final int n2, final int n3) {
        boolean b = false;
        switch (n) {
            case 1: {
                b = abstractBox.onMoved(n2, n3, this);
                break;
            }
            case 2: {
                b = abstractBox.onClick(n2, n3, this);
                break;
            }
            case 3: {
                b = abstractBox.onDragged(n2, n3, this);
                break;
            }
        }
        if (b) {
            return 1;
        }
        return 0;
    }
    
    public final BoxPosition findPositonFromPoint(final Point point, final boolean b) {
        int n = 1;
        BoxPosition boxPosition = null;
        Point point2 = null;
        int tamanyCaret = 0;
        final BoxPosition boxPosition2 = new BoxPosition(this.lacapsa, 0);
        for (boolean b2 = this.DrawRectCorrecte(boxPosition2, b, true); b2; b2 = this.DrawRectCorrecte(boxPosition2, b, true)) {
            Point posicioCaret = null;
            if (this.I(boxPosition2, false)) {
                posicioCaret = boxPosition2.c.PosicioCaret(boxPosition2.x, this);
                tamanyCaret = boxPosition2.c.TamanyCaret(boxPosition2.x);
            }
            final Point posicioReal;
            final Point point3 = posicioReal = boxPosition2.c.PosicioReal();
            posicioReal.y += boxPosition2.c.getPositionBaseline(boxPosition2.x);
            point3.x = boxPosition2.c.PosicioCaret(boxPosition2.x, this).x;
            if (posicioCaret != null && posicioCaret.y <= point.y && point.y <= posicioCaret.y + tamanyCaret) {
                if (n != 0 || boxPosition == null || Math.abs(point.x - point3.x) < Math.abs(point.x - point2.x)) {
                    point2 = point3;
                    boxPosition = boxPosition2.copy(boxPosition);
                    n = 0;
                }
            }
            else if (n != 0) {
                if (boxPosition == null) {
                    point2 = point3;
                    boxPosition = boxPosition2.copy(boxPosition);
                }
                else {
                    final int abs = Math.abs(point.y - point3.y);
                    final int abs2 = Math.abs(point.y - point2.y);
                    final int abs3 = Math.abs(point.x - point3.x);
                    final int abs4 = Math.abs(point.x - point2.x);
                    if (abs < abs2 || (abs == abs2 && abs3 < abs4)) {
                        point2 = point3;
                        boxPosition = boxPosition2.copy(boxPosition);
                    }
                }
            }
        }
        return boxPosition;
    }
    
    protected final boolean DrawRectCorrecte(BoxPosition boxPosition, final boolean b, final boolean b2) {
        while (boxPosition != null) {
            if (boxPosition.c == null) {
                break;
            }
            if (b2) {
                boxPosition = boxPosition.c.PosSeg2(boxPosition.x, null, boxPosition);
            }
            else {
                boxPosition = boxPosition.c.PosAnt2(boxPosition.x, null, boxPosition);
            }
            if (this.I(boxPosition, b)) {
                break;
            }
        }
        return boxPosition != null;
    }
    
    protected boolean I(final BoxPosition boxPosition, final boolean b) {
        if (boxPosition == null || boxPosition.c == null) {
            return false;
        }
        if (boxPosition.c.isStyle(16) && !this.isDesign()) {
            return false;
        }
        if (b) {
            return boxPosition.c.isSelectable(boxPosition.x);
        }
        return boxPosition.c.isValid(boxPosition.x);
    }
    
    public void resetPosition() {
        Point posicioCaret;
        if (this.curpos != null) {
            posicioCaret = this.curpos.c.PosicioCaret(this.curpos.x, this);
            posicioCaret.translate(this.tl.x, this.tl.y);
        }
        else {
            posicioCaret = new Point();
        }
        this.setCaret(posicioCaret);
    }
    
    public int getFormulaWidth() {
        return this.lacapsa.width;
    }
    
    public int getFormulaHeight() {
        return this.lacapsa.height;
    }
    
    public final String box2String(final AbstractSelection abstractSelection) {
        final BoxScripting boxScripting = new BoxScripting(-1);
        abstractSelection.script(boxScripting);
        return boxScripting.toString();
    }
    
    protected final Point I(final Point point) {
        if (this.mathRTL) {
            return new Point(this.getComponent().getSize().width - point.x, point.y);
        }
        return new Point(point.x, point.y);
    }
    
    protected final int Z() {
        if (this.getComponent() != null && this.getComponent().getSize() != null) {
            return this.getComponent().getSize().width;
        }
        return -1;
    }
    
    public boolean getMathRTL() {
        return this.mathRTL;
    }
    
    public boolean isDisplayStyle() {
        return this.displaystyle;
    }
    
    static Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Formula.LEFT_TO_RIGHT = false;
        Formula.BuscaPosicioTipus = new BoxScripting(1);
        Formula.DrawRectCorrecte = null;
        HAND_CURSOR = new Cursor(12);
        TEXT_CURSOR = new Cursor(2);
        DEFAULT_CURSOR = new Cursor(0);
        WAIT_CURSOR = new Cursor(3);
        nonItalicFont = new Font("SansSerif", 0, 24);
    }
}
