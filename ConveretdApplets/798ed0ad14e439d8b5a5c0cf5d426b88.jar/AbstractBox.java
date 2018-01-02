import java.awt.AWTEvent;
import java.util.Stack;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class AbstractBox
{
    static boolean ActualitzaRect;
    static boolean Afegir;
    public static final Color[] default_colors;
    public String cadena_omega;
    public int color;
    public Color forced_color;
    public Font fontPropia;
    public int fontMask;
    protected Font I;
    public int nominalFontSize;
    public int EM;
    public FontMetrics fontMetrics;
    public int factorescala;
    public Hashtable styles;
    public int scriptlevel;
    public Length recommendedWidth;
    public BoxComponent[] _cc;
    public int forca_estil;
    public int estil;
    private AbstractBox parent;
    public int p_en_pare;
    public AbstractBox[] fill;
    public int nfills;
    public AbstractNeuterBox[] dibs;
    public int ndibs;
    public int x;
    public int y;
    public int width;
    public int height;
    public int baseline;
    public String error;
    public String id;
    public String xml_lang;
    public String internalID;
    public String className;
    static Class AfegirNoCalc;
    static Class CalculRect;
    
    public AbstractBox() {
        this.color = -1;
        this.forced_color = null;
        this.fontPropia = null;
        this.I = null;
        this.EM = 0;
        this.fontMetrics = null;
        this.factorescala = 100;
        this.styles = null;
        this.scriptlevel = Integer.MIN_VALUE;
        this.recommendedWidth = null;
        this.forca_estil = 0;
        this.estil = 0;
        this.parent = null;
        this.p_en_pare = -1;
        this.fill = null;
        this.nfills = 0;
        this.dibs = null;
        this.ndibs = 0;
        this.baseline = 0;
        this.id = "";
        this.xml_lang = "";
        this.internalID = null;
        this.className = null;
    }
    
    public final void inicialitzaFills(final AbstractBox abstractBox) {
        this.inicialitzaFills(new AbstractBox[] { abstractBox });
    }
    
    public void inicialitzaFills(final AbstractBox[] array) {
        for (int i = 0; i < array.length; ++i) {
            final int kindChildBox = this.kindChildBox(i, array.length);
            AbstractBox abstractBox = array[i];
            if (kindChildBox == 1 && !(abstractBox instanceof TokensBox) && !(abstractBox instanceof EmptyBox)) {
                final TokensBox tokensBox = new TokensBox();
                tokensBox.inicialitzaFills(abstractBox);
                abstractBox = tokensBox;
            }
            this.AfegirNoCalc(abstractBox);
        }
    }
    
    public final void inicialitzaFills(final AbstractBox abstractBox, final AbstractBox abstractBox2) {
        this.inicialitzaFills(new AbstractBox[] { abstractBox, abstractBox2 });
    }
    
    public void inicialitzaFills() {
        while (this.nfills < this.nombreMinimDeFills()) {
            this.AfegirNoCalc(new EmptyBox());
        }
    }
    
    public void inicialitzaModalitat(final Object o) {
    }
    
    public int nombreMinimDeFills() {
        return 0;
    }
    
    public final AbstractBox getParentBox() {
        return this.parent;
    }
    
    public final void setParentBox(final AbstractBox parent) {
        this.parent = parent;
    }
    
    public final void AfegirNoCalc(final AbstractBox abstractBox) {
        this.Afegir(abstractBox, this.nfills, null);
    }
    
    public final void AfegirNoCalc(final AbstractBox abstractBox, final int n) {
        this.Afegir(abstractBox, n, null);
    }
    
    public final void Afegir(final AbstractBox abstractBox, final BoxComponent boxComponent) {
        this.Afegir(abstractBox, this.nfills, boxComponent);
    }
    
    public final void Afegir(AbstractBox correctKind, final int p_en_pare, final BoxComponent boxComponent) {
        final AbstractBox[] fill = this.fill;
        this.fill = new AbstractBox[((fill == null) ? 0 : fill.length) + 1];
        if (correctKind.getParentBox() != null) {
            correctKind.getParentBox().fill[correctKind.p_en_pare] = null;
        }
        correctKind = this.getCorrectKind(correctKind, p_en_pare, this.nfills + 1);
        correctKind.setParentBox(this);
        correctKind.p_en_pare = p_en_pare;
        int i;
        for (i = 0; i < p_en_pare; ++i) {
            this.fill[i] = fill[i];
        }
        this.fill[i] = correctKind;
        for (int j = this.nfills - 1; j >= i; --j) {
            final AbstractBox[] fill2 = this.fill;
            final int n = j + 1;
            final AbstractBox abstractBox = fill[j];
            fill2[n] = abstractBox;
            ++abstractBox.p_en_pare;
        }
        ++this.nfills;
        if (boxComponent != null) {
            correctKind.AfegirNoCalc(boxComponent);
        }
        correctKind.enAfegir(boxComponent);
        this.enAfegir(boxComponent, correctKind, i);
        if (boxComponent != null) {
            correctKind.CalculRect(boxComponent);
            this.ActualitzaRect(boxComponent);
        }
        else {
            ++OmegaSystem.contador1;
        }
    }
    
    public final AbstractBox getCorrectKind(AbstractBox abstractBox, final int n, final int n2) {
        final int kindChildBox = this.kindChildBox(n, n2);
        Class<AbstractBox> clazz = null;
        if (kindChildBox == 3) {
            clazz = (Class<AbstractBox>)((AbstractBox.AfegirNoCalc == null) ? (AbstractBox.AfegirNoCalc = I("MultilineBox")) : AbstractBox.AfegirNoCalc);
        }
        if (kindChildBox == 2) {
            clazz = (Class<AbstractBox>)((AbstractBox.CalculRect == null) ? (AbstractBox.CalculRect = I("TokensVBox")) : AbstractBox.CalculRect);
        }
        if (clazz != null && !clazz.isInstance(abstractBox)) {
            AbstractBox abstractBox2;
            try {
                abstractBox2 = clazz.getConstructor((Class<?>[])null).newInstance((Object[])null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return abstractBox;
            }
            abstractBox2.inicialitzaFills(abstractBox);
            abstractBox = abstractBox2;
        }
        return abstractBox;
    }
    
    public final int getDepth() {
        if (this.getParentBox() == null) {
            return 0;
        }
        return this.getParentBox().getDepth() + 1;
    }
    
    public final void enAfegir(final BoxComponent boxComponent) {
    }
    
    public void enAfegir(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
    }
    
    public void enTreure() {
    }
    
    public final AbstractBox TreureNoCalc() {
        return this.TreureNoCalc(this.nfills - 1);
    }
    
    public final void Treure(final int n, final BoxComponent boxComponent) {
        this.Treure(n, n + 1, boxComponent);
    }
    
    public final void Treure(final int n, final int n2, final BoxComponent boxComponent) {
        for (int i = n; i < n2; ++i) {
            this.fill[i].notificaEsborrat(boxComponent);
        }
        this.TreureNoCalc(n, n2);
        if (boxComponent != null) {
            this.ActualitzaRect(boxComponent);
        }
    }
    
    public final AbstractBox TreureNoCalc(final int n) {
        return this.TreureNoCalc(n, n + 1);
    }
    
    public final AbstractBox TreureNoCalc(final int n, final int n2) {
        if (n >= n2) {
            return null;
        }
        this.nfills -= n2 - n;
        final AbstractBox abstractBox = this.fill[n];
        final AbstractBox[] fill = this.fill;
        this.fill = new AbstractBox[fill.length - n2 + n];
        for (int i = 0; i < n; ++i) {
            this.fill[i] = fill[i];
        }
        for (int j = n; j < n2; ++j) {
            fill[j].setParentBox(null);
            fill[j].p_en_pare = 0;
        }
        for (int k = n2; k < fill.length; ++k) {
            final AbstractBox[] fill2 = this.fill;
            final int n3 = k - (n2 - n);
            final AbstractBox abstractBox2 = fill[k];
            fill2[n3] = abstractBox2;
            abstractBox2.p_en_pare -= n2 - n;
        }
        this.enTreure();
        return abstractBox;
    }
    
    public final void TreureTotNoCalc() {
        this.Treure(0, this.nfills, null);
    }
    
    public final void TreureTot(final BoxComponent boxComponent) {
        this.Treure(0, this.nfills, boxComponent);
    }
    
    public final void notificaEsborrat(final BoxComponent boxComponent) {
        if (this.fill != null) {
            for (int i = 0; i < this.fill.length; ++i) {
                if (this.fill[i] != null) {
                    this.fill[i].notificaEsborrat(boxComponent);
                }
                this.fill[i] = null;
            }
        }
        if (this.dibs != null) {
            for (int j = 0; j < this.dibs.length; ++j) {
                if (this.dibs[j] != null) {
                    this.dibs[j].enEsborrar();
                }
                this.dibs[j] = null;
            }
        }
        this.enEsborrar(boxComponent);
        this.setParentBox(null);
        this.p_en_pare = 0;
    }
    
    public final void enEsborrar(final BoxComponent boxComponent) {
    }
    
    public final void focusLost(final BoxPosition boxPosition, final BoxComponent boxComponent) {
    }
    
    public final void SubstitueixCapsa(AbstractBox correctKind, final int p_en_pare, final BoxComponent boxComponent) {
        if (p_en_pare < this.nfills) {
            this.fill[p_en_pare].notificaEsborrat(boxComponent);
            if (this.fill[p_en_pare].getParentBox() != null) {
                this.fill[p_en_pare].getParentBox().fill[this.fill[p_en_pare].p_en_pare] = null;
            }
            correctKind = this.getCorrectKind(correctKind, p_en_pare, this.nfills);
            correctKind.setParentBox(this);
            correctKind.p_en_pare = p_en_pare;
            this.fill[p_en_pare] = correctKind;
            if (boxComponent != null) {
                correctKind.AfegirNoCalc(boxComponent);
            }
            correctKind.enAfegir(boxComponent);
            this.enAfegir(boxComponent, correctKind, p_en_pare);
            if (boxComponent != null) {
                correctKind.CalculRect(boxComponent);
                this.ActualitzaRect(boxComponent);
            }
        }
        else {
            this.Afegir(correctKind, boxComponent);
        }
    }
    
    public final void DrawRectCorrecte(final BoxComponent boxComponent, final Graphics graphics) {
        if (!boxComponent.isDesign() && ((this.estil & 0x4) != 0x0 || Attributes.getStyle(this, "display").equals("none") || Attributes.getStyle(this, "visibility").equals("hidden"))) {
            return;
        }
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds != null && (this.x + this.width < clipBounds.x || this.y + this.height < clipBounds.y || this.x > clipBounds.x + clipBounds.width || this.y > clipBounds.y + clipBounds.height)) {
            return;
        }
        final Font font = graphics.getFont();
        final Color color = graphics.getColor();
        final int x = this.x;
        final int y = this.y;
        try {
            graphics.translate(this.x, this.y);
            if (AbstractBox.ActualitzaRect) {
                graphics.setColor(Color.orange);
                graphics.drawLine(0, 0, this.width - 1, 0);
                graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
                graphics.drawLine(this.width - 1, this.height - 1, 0, this.height - 1);
                graphics.drawLine(0, this.height - 1, 0, 0);
                graphics.setColor(Color.yellow);
                if (this.getVerticalMetrics(boxComponent) != null) {
                    final int[] verticalMetrics = this.getVerticalMetrics(boxComponent);
                    graphics.drawLine(verticalMetrics[0], 0, -verticalMetrics[2] + this.width - 1, 0);
                    graphics.drawLine(-verticalMetrics[2] + this.width - 1, 0, -verticalMetrics[3] + this.width - 1, this.height - 1);
                    graphics.drawLine(-verticalMetrics[3] + this.width - 1, this.height - 1, verticalMetrics[1], this.height - 1);
                    graphics.drawLine(verticalMetrics[1], this.height - 1, verticalMetrics[0], 0);
                }
            }
            if (AbstractBox.Afegir) {
                graphics.setColor(Color.green);
                graphics.drawLine(0, this.baseline, this.width - 1, this.baseline);
            }
            final Color color2 = boxComponent.getColor(13, this);
            if (color2 != null) {
                graphics.setColor(color2);
                graphics.fillRect(0, 0, this.width + 1, this.height + 1);
                graphics.setColor(color2.darker());
                graphics.drawRect(0, 0, this.width, this.height);
            }
            if (this.forced_color == null) {
                boolean editStatic = false;
                if (boxComponent instanceof FormulaEditor) {
                    editStatic = ((FormulaEditor)boxComponent).editStatic;
                }
                if ((this.estil & 0x1) != 0x0) {
                    boxComponent.setColor(graphics, boxComponent.getColor(10, this));
                }
                else if ((this.estil & 0x20) != 0x0) {
                    boxComponent.setColor(graphics, boxComponent.getColor(3, this));
                }
                else if (!this.isStyle(64) && editStatic) {
                    boxComponent.setColor(graphics, boxComponent.getColor(3, this));
                }
                else if ((this.estil & 0x2) != 0x0) {
                    boxComponent.setColor(graphics, Color.black);
                }
                else if (this.color >= 0) {
                    boxComponent.setColor(graphics, boxComponent.getColor(this.color, this));
                }
            }
            else {
                boxComponent.setColor(graphics, this.forced_color);
            }
            if (this.I == null) {
                return;
            }
            graphics.setFont(this.I);
            if (OmegaSystem.versio_java > 2) {
                GraphicsUtils.setTextAntialiasing(graphics, this.I);
            }
            this.onPaint(boxComponent, graphics);
            for (int i = 0; i < this.ndibs; ++i) {
                try {
                    graphics.translate(this.dibs[i].x, this.dibs[i].y);
                    this.dibs[i].onPaint(boxComponent, graphics);
                }
                finally {
                    graphics.translate(-this.dibs[i].x, -this.dibs[i].y);
                }
            }
            for (int j = 0; j < this.nfills; ++j) {
                try {
                    this.fill[j].DrawRectCorrecte(boxComponent, graphics);
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            graphics.setFont(font);
            graphics.setColor(color);
        }
        finally {
            graphics.translate(-x, -y);
        }
    }
    
    public void enCalculRect() {
    }
    
    public void enCalculRect(final BoxComponent boxComponent) {
        this.enCalculRect();
    }
    
    protected final void Afegir(final BoxComponent boxComponent) {
        if (this.isStyle(16) && !boxComponent.isDesign()) {
            this.width = 0;
            this.height = 0;
            this.baseline = 0;
            for (int i = 0; i < this.nfills; ++i) {
                this.fill[i].x = 0;
                this.fill[i].y = 0;
            }
            return;
        }
        if (this.nfills > 0 && this.fill[0].rect_depen_germans()) {
            this.fill[0].Afegir(boxComponent);
        }
        final int dpi = boxComponent.getDPI();
        this.width = Attributes.getStyleDim(this, "width", -1, dpi);
        this.height = Attributes.getStyleDim(this, "height", -1, dpi);
        this.enCalculRect(boxComponent);
        if (this.styles == null) {
            return;
        }
        this.width = Math.max(this.width, Attributes.getStyleDim(this, "min-width", 0, dpi));
    }
    
    protected final void I() {
        this.width = 0;
        this.height = 0;
        for (int i = 0; i < this.nfills; ++i) {
            this.width = Math.max(this.width, this.fill[i].x + this.fill[i].width);
            this.height = Math.max(this.height, this.fill[i].y + this.fill[i].height);
        }
        for (int j = 0; j < this.ndibs; ++j) {
            this.width = Math.max(this.width, this.dibs[j].x + this.dibs[j].width);
            this.height = Math.max(this.height, this.dibs[j].y + this.dibs[j].height);
        }
    }
    
    public final void ActualitzaRect(final BoxComponent boxComponent) {
        if (this.nfills < this.nombreMinimDeFills()) {
            return;
        }
        for (int i = 0; i < this.nfills; ++i) {
            if (this.fill[i].rect_depen_germans()) {
                this.fill[i].Afegir(boxComponent);
            }
        }
        final int width = this.width;
        final int height = this.height;
        final int baseline = this.baseline;
        this.Afegir(boxComponent);
        if ((width != this.width || height != this.height || baseline != this.baseline) && this.getParentBox() != null) {
            this.getParentBox().ActualitzaRect(boxComponent);
        }
    }
    
    public final boolean CalculRect(final BoxComponent boxComponent) {
        this._cc = boxComponent.getPointer();
        if (this.dibs != null) {
            for (int i = 0; i < this.dibs.length; ++i) {
                this.dibs[i].calculRect(boxComponent);
            }
        }
        for (int j = 0; j < this.nfills; ++j) {
            this.fill[j].CalculRect(boxComponent);
        }
        this.Afegir(boxComponent);
        return true;
    }
    
    public final void Actualitza(final BoxComponent boxComponent, final int n) {
        this.fill[n].Composa(boxComponent);
        this.ActualitzaRect(boxComponent);
    }
    
    public final void update(final BoxComponent boxComponent) {
        this.Composa(boxComponent);
        final AbstractBox parentBox = this.getParentBox();
        if (parentBox != null) {
            parentBox.ActualitzaRect(boxComponent);
        }
    }
    
    public final void Composa(final BoxComponent boxComponent) {
        this.AfegirNoCalc(boxComponent);
        this.CalculRect(boxComponent);
    }
    
    protected final void AfegirNoCalc(final BoxComponent boxComponent) {
        this.onUpdateAttributes(boxComponent);
        for (int i = 0; i < this.nfills; ++i) {
            this.fill[i].AfegirNoCalc(boxComponent);
        }
    }
    
    public void onUpdateAttributes(final BoxComponent boxComponent) {
        if (Attributes.getStyle(this, "display").equals("none")) {
            this.setStyle(16, true);
        }
        else {
            this.estil &= 0xFFFFFFEF;
            this.forca_estil &= 0xFFFFFFEF;
        }
        final AbstractBox parentBox = this.getParentBox();
        if (parentBox != null) {
            this.setAttributes(computeAttributesFromParent(parentBox.getAttributes(), this.getAttributes(), new Attributes(boxComponent.getDefaultFont(this.color, this), 15, null, boxComponent.isDisplayStyle() ? 0 : 256, 256)));
            final String style = Attributes.getStyle(this, "width");
            if (style.length() > 0) {
                this.recommendedWidth = new Length(style);
            }
            else if (parentBox != null) {
                final Length recommendedWidth = parentBox.getRecommendedWidth(this.p_en_pare, boxComponent);
                if (recommendedWidth != null) {
                    this.recommendedWidth = recommendedWidth;
                }
            }
        }
        if (parentBox == null) {
            this.factorescala = this.getFactorEscala(boxComponent);
        }
        else {
            this.factorescala = this.getParentBox().getFactorEscalaFill(boxComponent, this.p_en_pare);
        }
        final Font computeNomialFont = this.computeNomialFont(boxComponent);
        this.nominalFontSize = computeNomialFont.getSize();
        this.I = this.DrawRectCorrecte(computeNomialFont, this.factorescala, boxComponent);
        this.fontMetrics = this.EM(boxComponent, this.factorescala);
        this.EM = this.em();
        if (this.dibs != null) {
            for (int i = 0; i < this.dibs.length; ++i) {
                this.dibs[i].setFont(boxComponent, this.DrawRectCorrecte(computeNomialFont, this.factorescala * this.getFactorEscalaDibAbsolut(i) / 100, boxComponent));
            }
        }
    }
    
    private static Attributes computeAttributesFromParent(final Attributes attributes, final Attributes attributes2, final Attributes attributes3) {
        return Attributes.complement(Attributes.join(attributes, attributes2), Attributes.restrict(attributes3, attributes, true));
    }
    
    public Length getRecommendedWidth(final int n, final BoxComponent boxComponent) {
        return null;
    }
    
    public boolean rect_depen_germans() {
        return false;
    }
    
    public boolean script_depen_germans() {
        return false;
    }
    
    final int CalculRect() {
        return 60;
    }
    
    public int getFactorEscalaAbsolut(final BoxComponent boxComponent) {
        return 100;
    }
    
    public int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        return 100;
    }
    
    public int getFactorEscalaDibAbsolut(final int n) {
        return 100;
    }
    
    private final int getFactorEscala(final BoxComponent boxComponent) {
        int factorEscalaAbsolut;
        if (this.scriptlevel > 1000) {
            factorEscalaAbsolut = (int)(100.0 * Math.pow(0.800000011920929, this.scriptlevel - 1000));
        }
        else {
            factorEscalaAbsolut = this.getFactorEscalaAbsolut(boxComponent);
            if (this.scriptlevel != Integer.MIN_VALUE) {
                factorEscalaAbsolut *= (int)Math.pow(0.800000011920929, this.scriptlevel);
            }
            if (this.getParentBox() != null) {
                factorEscalaAbsolut = factorEscalaAbsolut * this.getParentBox().getFactorEscalaFill(boxComponent, this.p_en_pare) / 100;
            }
        }
        return this.factorescala = Math.max(factorEscalaAbsolut, this.CalculRect());
    }
    
    private final int getFactorEscalaFill(final BoxComponent boxComponent, final int n) {
        return Math.max(this.getFactorEscala(boxComponent) * this.getFactorEscalaAbsolutFill(boxComponent, n) * this.fill[n].getFactorEscalaAbsolut(boxComponent) / 10000, this.CalculRect());
    }
    
    public final Font getNominalFont() {
        if (this.I == null) {
            return null;
        }
        return this.singletonFont(new Font(this.I.getName(), this.I.getStyle(), this.nominalFontSize));
    }
    
    public final Font singletonFont(final Font font) {
        if (this.fontPropia != null && font.equals(this.fontPropia)) {
            return this.fontPropia;
        }
        if (this.I != null && font.equals(this.I)) {
            return this.I;
        }
        final AbstractBox parentBox = this.getParentBox();
        if (parentBox != null) {
            if (parentBox.fontPropia != null && font.equals(parentBox.fontPropia)) {
                return parentBox.fontPropia;
            }
            if (parentBox.I != null && font.equals(parentBox.I)) {
                return parentBox.I;
            }
        }
        return font;
    }
    
    private final Font computeNomialFont(final BoxComponent boxComponent) {
        final Attributes attributes = new Attributes(boxComponent.getDefaultFont(this.color, this), 15);
        attributes.join(this.getAttributes());
        final Font font = attributes.font;
        return this.singletonFont(new Font(font.getName(), font.getStyle(), font.getSize()));
    }
    
    protected void I(final Graphics graphics) {
    }
    
    protected void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        this.I(graphics);
    }
    
    public final int UnderBaseline() {
        return this.height - this.baseline;
    }
    
    public final int xw() {
        return this.x + this.width;
    }
    
    public final int yh() {
        return this.y + this.height;
    }
    
    public final int xm() {
        return this.x + this.width / 2;
    }
    
    public final int yb() {
        return this.y + this.baseline;
    }
    
    public final Rectangle RectangleReal() {
        final Point posicioReal = this.PosicioReal();
        return new Rectangle(posicioReal.x, posicioReal.y, this.width, this.height);
    }
    
    public final Point PosicioReal() {
        if (this.getParentBox() == null) {
            return new Point(this.x, this.y);
        }
        final Point posicioReal = this.getParentBox().PosicioReal();
        posicioReal.translate(this.x, this.y);
        return posicioReal;
    }
    
    protected final AbstractBox Composa(final int n, final int n2) {
        if (this.esInterior(n, n2)) {
            for (int i = 0; i < this.nfills; ++i) {
                final AbstractBox composa = this.fill[i].Composa(n - this.fill[i].x, n2 - this.fill[i].y);
                if (composa != null) {
                    return composa;
                }
            }
            return this;
        }
        return null;
    }
    
    public final boolean esInterior(final int n, final int n2) {
        return n >= 0 && n2 >= 0 && n < this.width && n2 < this.height;
    }
    
    public boolean onClick(final int n, final int n2, final BoxComponent boxComponent) {
        return false;
    }
    
    public boolean onMoved(final int n, final int n2, final BoxComponent boxComponent) {
        return false;
    }
    
    public final void onExited(final BoxComponent boxComponent) {
    }
    
    public final boolean onKeyPressed(final KeyEvent keyEvent, final BoxComponent boxComponent) {
        return false;
    }
    
    public boolean onDragged(final int n, final int n2, final BoxComponent boxComponent) {
        return false;
    }
    
    public int nPosicions() {
        return this.nfills;
    }
    
    public BoxPosition PosicioMesPropera(Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        posicioReal.setLocation(point.x - posicioReal.x, point.y - posicioReal.y);
        point = posicioReal;
        if (point.x < this.width / 2) {
            return this.PosSeg_Fora(array);
        }
        return this.PosAnt_Fora(array);
    }
    
    public final BoxPosition PosicioMesProperaGlobal(Point projeccio, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        AbstractBox composa = this.Composa(projeccio.x - posicioReal.x, projeccio.y - posicioReal.y);
        if (composa == null) {
            projeccio = Utils.projeccio(projeccio, this.RectangleReal());
            composa = this;
        }
        if (composa != null) {
            return composa.PosicioMesPropera(projeccio, array);
        }
        return null;
    }
    
    protected final BoxPosition I(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        for (int i = 0; i < this.nfills - 1; ++i) {
            final int n = this.fill[i].y + this.fill[i].height;
            if (point2.y < n + (this.fill[i + 1].y - n) / 2) {
                return this.fill[i].PosicioMesProperaGlobal(point, array);
            }
        }
        return this.fill[this.nfills - 1].PosicioMesProperaGlobal(point, array);
    }
    
    protected final int I(final Point point) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        int n = 0;
        int nfills = this.nfills;
        while (n + 1 != nfills) {
            final int n2 = (n + nfills) / 2;
            if (point2.y < this.fill[n2].y) {
                nfills = n2;
            }
            else {
                n = n2;
            }
        }
        return n;
    }
    
    public BoxPosition PosSeg(final int n, final AbstractBox[] array) {
        if (n < this.nfills) {
            return this.fill[n].PosSeg_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosSeg_Fill(this.p_en_pare, array);
        }
        return new BoxPosition(this, n);
    }
    
    public BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        if (array != null) {
            final int index = Utils.indexOf(array, this);
            if (index > 0) {
                final int index2 = Utils.indexOf(this.fill, array[index - 1]);
                if (index2 >= 0) {
                    return this.fill[index2].PosSeg_Fora(array);
                }
            }
            else if (index < 0 && this.getParentBox() != null) {
                return this.getParentBox().PosSeg_Fill(this.p_en_pare, array);
            }
            return new BoxPosition(this, 0);
        }
        if (this.nfills != 0) {
            return this.fill[0].PosSeg_Fora(array);
        }
        if (this.getParentBox() == null) {
            return new BoxPosition(this, 0);
        }
        return this.getParentBox().PosSeg_Fill(this.p_en_pare, array);
    }
    
    public BoxPosition PosSeg_Fill(final int n, final AbstractBox[] array) {
        if (n < this.nfills - 1) {
            return this.fill[n + 1].PosSeg_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosSeg_Fill(this.p_en_pare, array);
        }
        return null;
    }
    
    public BoxPosition PosAnt(final int n, final AbstractBox[] array) {
        if (n > 0) {
            return this.fill[n - 1].PosAnt_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosAnt_Fill(this.p_en_pare, array);
        }
        return new BoxPosition(this, n);
    }
    
    public BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        if (array != null) {
            final int index = Utils.indexOf(array, this);
            if (index > 0) {
                final int index2 = Utils.indexOf(this.fill, array[index - 1]);
                if (index2 >= 0) {
                    return this.fill[index2].PosAnt_Fora(array);
                }
            }
            else if (index < 0 && this.getParentBox() != null) {
                return this.getParentBox().PosAnt_Fill(this.p_en_pare, array);
            }
            return new BoxPosition(this, 0);
        }
        if (this.nfills != 0) {
            return this.fill[this.nfills - 1].PosAnt_Fora(array);
        }
        if (this.getParentBox() == null) {
            return new BoxPosition(this, 0);
        }
        return this.getParentBox().PosAnt_Fill(this.p_en_pare, array);
    }
    
    public BoxPosition PosAnt_Fill(final int n, final AbstractBox[] array) {
        if (n > 0) {
            return this.fill[n - 1].PosAnt_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosAnt_Fill(this.p_en_pare, array);
        }
        return this.PosSeg_Fora(array);
    }
    
    public final BoxPosition PosSeg(final int n) {
        return this.PosSeg(n, null);
    }
    
    public final BoxPosition PosAnt(final int n) {
        return this.PosAnt(n, null);
    }
    
    public final BoxPosition PosSeg_Fora() {
        return this.PosSeg_Fora(null);
    }
    
    public final BoxPosition PosAnt_Fora() {
        return this.PosAnt_Fora(null);
    }
    
    public final BoxPosition PosSeg_Fill(final int n) {
        return this.PosSeg_Fill(n, null);
    }
    
    public final BoxPosition PosAnt_Fill(final int n) {
        return this.PosAnt_Fill(n, null);
    }
    
    public BoxPosition PosSeg2(int x, final AbstractBox[] array, final BoxPosition boxPosition) {
        AbstractBox parentBox;
        if (x >= this.nPosicions()) {
            parentBox = this.getParentBox();
            if (parentBox == null) {
                return null;
            }
            x = this.p_en_pare + 1;
        }
        else {
            parentBox = this.fill[x];
            x = 0;
        }
        if (boxPosition == null) {
            return new BoxPosition(parentBox, x);
        }
        boxPosition.c = parentBox;
        boxPosition.x = x;
        return boxPosition;
    }
    
    public BoxPosition PosAnt2(int x, final AbstractBox[] array, final BoxPosition boxPosition) {
        AbstractBox parentBox;
        if (x <= 0) {
            parentBox = this.getParentBox();
            if (parentBox == null) {
                return null;
            }
            x = this.p_en_pare;
        }
        else {
            parentBox = this.fill[x - 1];
            x = this.fill[x - 1].nPosicions();
        }
        if (boxPosition == null) {
            return new BoxPosition(parentBox, x);
        }
        boxPosition.c = parentBox;
        boxPosition.x = x;
        return boxPosition;
    }
    
    public int TamanyCaret(final int n) {
        return this.height;
    }
    
    public final Point PosicioCaret(final int n, final BoxComponent boxComponent) {
        return this.PosicioCaret(this.PosicioReal(), n, boxComponent);
    }
    
    public Point PosicioCaret(final Point point, final int n, final BoxComponent boxComponent) {
        if (n >= 0 && n < this.nfills) {
            return new Point(point.x + this.fill[n].x, point.y + this.fill[n].y);
        }
        return point;
    }
    
    public int getPositionBaseline(final int n) {
        return this.baseline;
    }
    
    public Rectangle getRectangleSeleccio(final int n, final int n2, final BoxComponent boxComponent) {
        return this.RectangleReal();
    }
    
    public final String plainString() {
        final BoxScripting boxScripting = new BoxScripting(-1);
        this.script(boxScripting);
        return boxScripting.toString();
    }
    
    public final void netejaErrors() {
        this.error = null;
        for (int i = 0; i < this.nfills; ++i) {
            this.fill[i].netejaErrors();
        }
    }
    
    public BoxPosition findAndRemoveMarkup(final BoxComponent boxComponent, final String s) {
        for (int i = 0; i < this.nfills; ++i) {
            if (MarkupBox.isMarkup(this.fill[i], s)) {
                final EmptyBox emptyBox = new EmptyBox();
                this.SubstitueixCapsa(emptyBox, i, boxComponent);
                return new BoxPosition(emptyBox, 0);
            }
            final BoxPosition andRemoveMarkup = this.fill[i].findAndRemoveMarkup(boxComponent, s);
            if (andRemoveMarkup != null) {
                return andRemoveMarkup;
            }
        }
        return null;
    }
    
    public void findAndRemoveMarkups(final BoxComponent boxComponent, final String[] array, final BoxPosition[] array2) {
        for (int i = 0; i < this.nfills; ++i) {
            this.fill[i].findAndRemoveMarkups(boxComponent, array, array2);
        }
    }
    
    protected final Font DrawRectCorrecte(final Font font, final int n, final BoxComponent boxComponent) {
        if (font == null) {
            return null;
        }
        return this.singletonFont(new Font(font.getName(), font.getStyle(), (int)(font.getSize() * n / 100.0f) * boxComponent.getDPI() / 72));
    }
    
    final FontMetrics EM(final BoxComponent boxComponent, final int n) {
        if (this.I == null) {
            return null;
        }
        if (this.getParentBox() != null && this.I == this.getParentBox().I) {
            return this.getParentBox().fontMetrics;
        }
        return boxComponent.getFontMetrics(this.I);
    }
    
    public final int em() {
        if (this.fontMetrics == null) {
            return 0;
        }
        return em(this.fontMetrics);
    }
    
    public static final int em(final FontMetrics fontMetrics) {
        if (OmegaSystem.versio_java >= 2) {
            return fontMetrics.getAscent() + fontMetrics.getDescent() / 2;
        }
        return fontMetrics.getAscent() + fontMetrics.getDescent();
    }
    
    public final int em(final float n) {
        return Math.round(n * this.EM);
    }
    
    public final int em(final String s) {
        return this._cc[0].getFontUtils().width(this._cc[0], this.I, s);
    }
    
    public final int em(final char[] array, final int n, final int n2) {
        return this._cc[0].getFontUtils().charWidth(this._cc[0], this.I, array, n, n2);
    }
    
    public boolean fbvisible(final int n) {
        return true;
    }
    
    public AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return null;
    }
    
    public AbstractSelection SeleccionaDreta(final Stack stack) {
        return null;
    }
    
    public AbstractSelection SeleccionaEsquerra(final Stack stack) {
        return null;
    }
    
    public final void ImposaColor(final Color color) {
        this.posaAtribut(null, new Attributes(color));
    }
    
    public final void ImposaFontPropia(final Font font) {
        this.posaAtribut(null, new Attributes(font, 15));
    }
    
    public final boolean esSubcapsa(final AbstractBox abstractBox) {
        for (AbstractBox parentBox = abstractBox; parentBox != null; parentBox = parentBox.getParentBox()) {
            if (parentBox == this) {
                return true;
            }
        }
        return false;
    }
    
    private final void setStyle(final int n, final int n2, final boolean b) {
        this.posaAtribut(null, new Attributes(n, n2), true, b);
    }
    
    public final void setStyleRec(final int n, final boolean b) {
        if (b) {
            this.setStyle(n, n, true);
        }
        else {
            this.setStyle(0, n, true);
        }
    }
    
    public final void setStyle(final int n, final boolean b) {
        if (b) {
            this.setStyle(n, n, false);
        }
        else {
            this.setStyle(0, n, false);
        }
    }
    
    public final boolean isStyle(final int n) {
        return (this.estil & n) != 0x0;
    }
    
    public static final int copiaEstil(int n, final int n2, final int n3) {
        n &= ~n3;
        n |= n2;
        return n;
    }
    
    public final void posaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        this.posaAtribut(boxComponent, attributes, true, true);
    }
    
    public final void setAttributes(final Attributes attributes) {
        this.fontPropia = attributes.font;
        this.fontMask = attributes.fontMask;
        this.forced_color = attributes.color;
        this.estil = attributes.estil;
        this.forca_estil = attributes.quins_estil;
    }
    
    public final Attributes getAttributes() {
        return new Attributes(this.fontPropia, this.fontMask, this.forced_color, this.estil, this.forca_estil);
    }
    
    public final void posaAtribut(final BoxComponent boxComponent, final Attributes attributes, final boolean b, final boolean b2) {
        if (attributes.modifiesColor(this.color)) {
            Attributes attributes2;
            if (b) {
                attributes2 = this.getAttributes();
                attributes2.join(attributes);
            }
            else {
                attributes2 = new Attributes(attributes);
                attributes2.join(this.getAttributes());
            }
            this.setAttributes(attributes2);
        }
        if (b2) {
            for (int i = 0; i < this.nfills; ++i) {
                this.fill[i].posaAtribut(null, attributes, b, b2);
            }
        }
        if (this instanceof TokensBox) {
            ((TokensBox)this).joinChildren(0, 0, boxComponent);
        }
        if (boxComponent != null) {
            this.Composa(boxComponent);
        }
    }
    
    public boolean posicio_final() {
        return true;
    }
    
    public boolean posicio_inicial() {
        return true;
    }
    
    public boolean inserta_final() {
        return false;
    }
    
    public boolean hasProperties() {
        return false;
    }
    
    public boolean hasMenuProperties() {
        return false;
    }
    
    public void buildContextualMenu(final FormulaEditor formulaEditor) {
    }
    
    public void processEvent(final BoxComponent boxComponent, final AWTEvent awtEvent) {
    }
    
    public void exchangeProperties(final Hashtable hashtable, final int n) {
    }
    
    public final void exchangeInheritableProperties(final Hashtable hashtable, final int n) {
        Attributes.exchangeTitle(hashtable, "General", n);
        this.id = Attributes.exchangeString(hashtable, "id", n, this.id, "");
        this.className = Attributes.exchangeString(hashtable, "class", n, this.className, "");
        this.xml_lang = Attributes.exchangeString(hashtable, "xml:lang", n, this.xml_lang, "");
        final String exchangeString = Attributes.exchangeString(hashtable, "scriptlevel", n, this.getScriptLevel(), "");
        if (n == 0 || n == 4097) {
            this.setScriptLevel(exchangeString, n == 4097);
        }
    }
    
    public final Hashtable getInehritableProperties() {
        final Hashtable hashtable = new Hashtable();
        this.exchangeInheritableProperties(hashtable, 1);
        return hashtable;
    }
    
    public final boolean onValidate(final BoxComponent boxComponent) {
        return true;
    }
    
    public void onScript(final BoxScripting boxScripting) {
        this.onScript(boxScripting, 0, this.nfills);
    }
    
    public void onScript(final BoxScripting boxScripting, final int n, final int n2) {
        boxScripting.onScript(this, this.fill, n, n2);
    }
    
    public final void script(final BoxScripting boxScripting) {
        final String scriptCommand = this.scriptCommand(boxScripting);
        boxScripting.beginBox(this, scriptCommand);
        this.onScript(boxScripting);
        boxScripting.endBox(this, scriptCommand);
    }
    
    public void attributes(final BoxScripting boxScripting) {
    }
    
    public final void script(final BoxScripting boxScripting, final int n, final int n2) {
        final String scriptCommand = this.scriptCommand(boxScripting, n, n2);
        boxScripting.beginBox(this, scriptCommand);
        this.onScript(boxScripting, n, n2);
        boxScripting.endBox(this, scriptCommand);
    }
    
    public String scriptCommand(final BoxScripting boxScripting, final int n, final int n2) {
        return this.scriptCommand(boxScripting);
    }
    
    public String scriptCommand(final BoxScripting boxScripting) {
        return null;
    }
    
    public final void scriptId(final BoxScripting boxScripting) {
        boxScripting.properties(this.getInehritableProperties());
    }
    
    public final boolean needsMStyle() {
        return this.getInehritableProperties().containsKey("scriptlevel");
    }
    
    public final boolean hasAttributes() {
        return !this.getInehritableProperties().isEmpty();
    }
    
    final void I(final AbstractBox abstractBox) {
        this.exchangeInheritableProperties(abstractBox.getInehritableProperties(), 4097);
    }
    
    public final void setScriptLevel(final String s, final boolean b) {
        if (s == null || s.length() == 0) {
            return;
        }
        if (!b || this.scriptlevel == Integer.MIN_VALUE) {
            this.scriptlevel = 0;
        }
        if (s.startsWith("+")) {
            this.scriptlevel += Integer.parseInt(s.substring(1));
        }
        else if (s.startsWith("-")) {
            this.scriptlevel += Integer.parseInt(s);
        }
        else {
            this.scriptlevel = 1000 + Integer.parseInt(s);
        }
    }
    
    public final String getScriptLevel() {
        if (this.scriptlevel != Integer.MIN_VALUE) {
            if (this.scriptlevel >= 1000) {
                return "" + (this.scriptlevel - 1000);
            }
            if (this.scriptlevel < 0) {
                return "" + this.scriptlevel;
            }
            if (this.scriptlevel > 0) {
                return "+" + this.scriptlevel;
            }
        }
        return null;
    }
    
    public int kindChildBox(final int n, final int n2) {
        return 0;
    }
    
    public boolean isEmpty(final BoxComponent boxComponent) {
        for (int i = 0; i < this.nfills; ++i) {
            if (!(this.fill[i] instanceof EmptyBox)) {
                return false;
            }
        }
        return true;
    }
    
    public final AbstractBox deepExplore(final BoxVisitor boxVisitor, final int n, final int n2, final int n3) {
        final int preVisitBox = boxVisitor.preVisitBox(this, n, n2, n3);
        if (preVisitBox == -1) {
            return null;
        }
        if (preVisitBox == 1) {
            return this;
        }
        for (int i = 0; i < this.nfills; ++i) {
            final AbstractBox deepExplore = this.fill[i].deepExplore(boxVisitor, n, n2 - this.fill[i].x, n3 - this.fill[i].y);
            if (deepExplore != null) {
                return deepExplore;
            }
        }
        if (boxVisitor.postVisitBox(this, n, n2, n3) == 1) {
            return this;
        }
        return null;
    }
    
    public int getSplitFactor(final int n) {
        return 0;
    }
    
    public boolean isValid(final int n) {
        return false;
    }
    
    public boolean isSelectable(final int n) {
        return this.isValid(n);
    }
    
    public final boolean isEditable(final int n) {
        return true;
    }
    
    public final boolean isSymbol(final int n) {
        return false;
    }
    
    public final boolean areAttributesInterior() {
        return false;
    }
    
    public final boolean isAccentContainer() {
        return false;
    }
    
    int[] getVerticalMetrics(final BoxComponent boxComponent) {
        return null;
    }
    
    public final AbstractBox getLeftOrNull() {
        final AbstractBox parentBox = this.getParentBox();
        if (parentBox == null) {
            return null;
        }
        if (this.p_en_pare < 1) {
            return null;
        }
        return parentBox.fill[this.p_en_pare - 1];
    }
    
    public final AbstractBox getRightOrNull() {
        final AbstractBox parentBox = this.getParentBox();
        if (parentBox == null) {
            return null;
        }
        if (this.p_en_pare + 1 >= parentBox.nfills) {
            return null;
        }
        return parentBox.fill[this.p_en_pare + 1];
    }
    
    public final boolean isDisplayStyle(final BoxComponent boxComponent) {
        if ((this.forca_estil & 0x100) != 0x0) {
            return (this.estil & 0x100) == 0x0;
        }
        return boxComponent.isDisplayStyle();
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
        AbstractBox.ActualitzaRect = false;
        AbstractBox.Afegir = false;
        default_colors = new Color[] { new Color(0, 0, 0), new Color(100, 100, 0), new Color(100, 100, 100), new Color(0, 0, 255), new Color(100, 100, 100), new Color(255, 0, 0), new Color(0, 100, 0), new Color(255, 180, 55), new Color(210, 255, 210), Color.orange, new Color(170, 0, 0), Color.white, Color.black };
    }
}
