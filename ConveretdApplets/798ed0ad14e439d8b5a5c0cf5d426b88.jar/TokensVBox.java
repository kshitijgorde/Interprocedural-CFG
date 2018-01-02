import java.util.Stack;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Insets;

// 
// Decompiled by Procyon v0.5.30
// 

public class TokensVBox extends AbstractBox
{
    public Length paddingTop;
    public Length paddingLeft;
    public Length paddingRight;
    public Length paddingBottom;
    public Length interSpace;
    private Insets insets;
    Class Afegeix;
    private String baselineType;
    private boolean comas;
    boolean Z;
    public boolean showEmptyChildren;
    private int type;
    public int alignment;
    public static boolean columnAlign;
    static Class PosAnt;
    
    public TokensVBox() {
        this.paddingTop = new Length();
        this.paddingLeft = new Length();
        this.paddingRight = new Length();
        this.paddingBottom = new Length();
        this.interSpace = new Length();
        this.insets = new Insets(0, 0, 0, 0);
        this.baselineType = "center";
        this.comas = false;
        this.Z = false;
        this.showEmptyChildren = true;
        this.type = 1;
        this.alignment = 0;
        this.setDefaultChildClass((TokensVBox.PosAnt == null) ? (TokensVBox.PosAnt = I("EmptyBox")) : TokensVBox.PosAnt);
    }
    
    public final void inicialitzaModalitat(final Object o) {
        if (o instanceof String) {
            this.setBaselineType((String)o);
        }
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        if (array.length == 0) {
            this.inicialitzaFills();
        }
        else {
            super.inicialitzaFills(array);
        }
    }
    
    public final void inicialitzaFills() {
        this.TreureTotNoCalc();
        try {
            final AbstractBox abstractBox = this.Afegeix.newInstance();
            abstractBox.inicialitzaFills();
            this.AfegirNoCalc(abstractBox);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean fbvisible(final int n) {
        return this.showEmptyChildren;
    }
    
    public final void setDefaultChildClass(final Class afegeix) {
        this.Afegeix = afegeix;
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void setBaselineType(final String baselineType) {
        if (baselineType != null && baselineType.equals("box")) {
            this.baselineType = "center";
            this.comas = true;
        }
        else {
            this.baselineType = baselineType;
            this.comas = false;
        }
    }
    
    public final void enCalculRect() {
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        final int dpi = boxComponent.getDPI();
        final Insets insets = new Insets(this.paddingTop.toUserUnit(super.EM, dpi), this.paddingLeft.toUserUnit(super.EM, dpi), this.paddingBottom.toUserUnit(super.EM, dpi), this.paddingRight.toUserUnit(super.EM, dpi));
        final int userUnit = this.interSpace.toUserUnit(super.EM, dpi);
        super.height = insets.top;
        for (int i = 0; i < super.nfills; ++i) {
            super.fill[i].x = insets.left;
            super.fill[i].y = super.height;
            super.height += super.fill[i].height + userUnit;
            super.width = Math.max(super.width, insets.left + super.fill[i].width + insets.right);
        }
        super.height += -userUnit + insets.bottom;
        if (this.baselineType != null && this.baselineType.equals("top")) {
            super.baseline = super.fill[0].y + super.fill[0].baseline;
        }
        else if (this.baselineType != null && this.baselineType.equals("bottom")) {
            super.baseline = super.fill[super.nfills - 1].y + super.fill[super.nfills - 1].baseline;
        }
        else if (super.nfills == 1) {
            super.baseline = super.fill[0].y + super.fill[0].baseline;
        }
        else {
            super.baseline = super.height / 2 + this.em(0.25f);
        }
        if (this.alignment != 0 && super.recommendedWidth != null) {
            super.width = super.recommendedWidth.toUserUnit(super.EM, dpi) - insets.left - insets.right;
        }
        for (int j = 0; j < super.nfills; ++j) {
            if (this.alignment == 1) {
                final AbstractBox abstractBox = super.fill[j];
                abstractBox.x += (super.width - super.fill[j].width) / 2;
            }
            else if (this.alignment == 2) {
                final AbstractBox abstractBox2 = super.fill[j];
                abstractBox2.x += super.width - super.fill[j].width;
            }
        }
    }
    
    public final void onUpdateAttributes(final BoxComponent boxComponent) {
        this.alignment = Attributes.getAlign(this, -1);
        if (this.alignment == -1) {
            if (this.getParentBox() != null) {
                this.alignment = Attributes.getAlign(this.getParentBox(), 0);
            }
            else {
                this.alignment = 0;
            }
        }
        super.onUpdateAttributes(boxComponent);
    }
    
    public final void AfegirCapsaPredeterminada(final int n, final BoxComponent boxComponent) {
        try {
            final AbstractBox abstractBox = this.Afegeix.newInstance();
            abstractBox.inicialitzaFills();
            this.Afegir(abstractBox, n, boxComponent);
        }
        catch (InstantiationException ex) {
            System.out.println("caca de la vaca");
        }
        catch (IllegalAccessException ex2) {
            System.out.println("caca de la vaca");
        }
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final int i = this.I(point);
        if (array == null) {
            return super.fill[i].PosicioMesPropera(point, array);
        }
        if (point.x - this.PosicioReal().x < this.insets.left) {
            return new BoxPosition(this, i);
        }
        return super.fill[i].PosicioMesPropera(point, array);
    }
    
    public final BoxPosition PosSeg(final int n, final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosSeg(n, array);
        }
        if (n < super.nfills) {
            return super.fill[n].PosSeg_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return this.PosAnt_Fora(array);
    }
    
    public final BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosSeg_Fora(array);
        }
        return new BoxPosition(this, 0);
    }
    
    public final BoxPosition PosSeg_Fill(final int n, final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosSeg_Fill(n, array);
        }
        return new BoxPosition(this, n + 1);
    }
    
    public final BoxPosition PosAnt(final int n, final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosAnt(n, array);
        }
        if (n > 0) {
            return super.fill[n - 1].PosAnt_Fora(array);
        }
        if (this.getParentBox() != null) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return this.PosSeg_Fora(array);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosAnt_Fora(array);
        }
        return new BoxPosition(this, super.nfills);
    }
    
    public final BoxPosition PosAnt_Fill(final int n, final AbstractBox[] array) {
        if (this.Z || array == null) {
            return super.PosAnt_Fill(n, array);
        }
        return new BoxPosition(this, n);
    }
    
    public final Rectangle getRectangleSeleccio(int n, int nfills, final BoxComponent boxComponent) {
        if (n >= nfills) {
            return new Rectangle(0, 0, 0, 0);
        }
        if (n < 0) {
            n = 0;
        }
        if (nfills > super.nfills) {
            nfills = super.nfills;
        }
        Rectangle rectangle = super.fill[n].RectangleReal();
        for (int i = n + 1; i < nfills; ++i) {
            rectangle = Utils.unio(rectangle, super.fill[i].RectangleReal());
        }
        return rectangle;
    }
    
    public final Rectangles getRectanglesSeleccio(int n, int nfills) {
        if (n >= nfills) {
            return new Rectangles();
        }
        if (n < 0) {
            n = 0;
        }
        if (nfills > super.nfills) {
            nfills = super.nfills;
        }
        final Rectangles rectangles = new Rectangles();
        for (int i = n; i < nfills; ++i) {
            rectangles.Afegeix(super.fill[i].RectangleReal());
        }
        return rectangles;
    }
    
    public AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new TokensVSelection(this, stack, stack2, b);
    }
    
    public String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            switch (this.getType()) {
                case 0: {
                    return null;
                }
                case 2: {
                    return "vlist";
                }
                case 1: {
                    return "mtable";
                }
            }
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        this.onScript(boxScripting, 0, super.nfills);
    }
    
    public void onScript(final BoxScripting boxScripting, final int n, final int n2) {
        if (boxScripting.scriptMode == 1) {
            if (this.getType() == 1) {
                for (int i = n; i < n2; ++i) {
                    boxScripting.openTag("mtr");
                    boxScripting.openTag("mtd");
                    super.fill[i].script(boxScripting);
                    boxScripting.closeTag();
                    boxScripting.closeTag();
                }
            }
            else {
                super.onScript(boxScripting, n, n2);
            }
        }
        else if (boxScripting.scriptMode == 0) {
            boxScripting.appendScript("\\beginv" + (this.comas ? "box" : this.baselineType) + " ");
            for (int j = n; j < n2; ++j) {
                if (j > n) {
                    boxScripting.appendScript("\\cr ");
                }
                super.fill[j].script(boxScripting);
            }
            boxScripting.appendScript("\\endv" + (this.comas ? "box" : this.baselineType) + " ");
        }
        else if (boxScripting.scriptMode == -1 || boxScripting.scriptMode == 2) {
            for (int k = n; k < n2; ++k) {
                super.fill[k].script(boxScripting);
                if (this.comas) {
                    if (k + 1 < n2) {
                        boxScripting.appendText(",");
                    }
                }
                else {
                    boxScripting.appendText("\n");
                }
            }
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public final void setType(final int type) {
        this.type = type;
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        if (this.baselineType != null && this.type == 1) {
            boxScripting.appendAttribute("align", this.baselineType);
        }
        final String scriptCommand = this.scriptCommand(boxScripting);
        if (scriptCommand != null && scriptCommand.equals("mtable") && TokensVBox.columnAlign) {
            boxScripting.appendAttribute("columnalign", "left");
            boxScripting.appendAttribute("rowspacing", "0");
        }
    }
    
    public final Length getRecommendedWidth(final int n, final BoxComponent boxComponent) {
        if (super.recommendedWidth != null) {
            int n2 = super.recommendedWidth.toUserUnit(super.EM, boxComponent.getDPI());
            if (n2 >= 0) {
                n2 = Math.max(0, n2 - this.paddingLeft.toUserUnit(super.EM, boxComponent.getDPI()) - this.paddingRight.toUserUnit(super.EM, boxComponent.getDPI()));
            }
            return Length.create(n2, boxComponent.getDPI());
        }
        return null;
    }
    
    public boolean isSelectable(final int n) {
        return true;
    }
    
    public final int getPositionBaseline(final int n) {
        if (n == 0) {
            return super.fill[0].y;
        }
        return super.fill[n - 1].y + super.fill[n - 1].height + 1;
    }
    
    static final Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        TokensVBox.columnAlign = false;
    }
}
