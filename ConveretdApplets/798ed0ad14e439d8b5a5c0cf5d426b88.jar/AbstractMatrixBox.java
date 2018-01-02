import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Stack;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AbstractMatrixBox extends AbstractBox
{
    public int columnes;
    public int files;
    protected int[] PosAnt;
    protected int[] Z;
    public int toCopy;
    protected String C;
    protected String B;
    protected boolean D;
    protected boolean F;
    public boolean autoincrement;
    public boolean rowcol;
    protected Color J;
    protected Color S;
    protected int A;
    protected int[] E;
    protected int[] G;
    int[] PosAnt_Fill;
    int PosAnt_Fora;
    int PosSeg;
    private String stringAlign;
    private int alignRowNumber;
    int[] PosSeg_Fill;
    protected String PosSeg_Fora;
    private String[] columnwidth;
    String[] PosicioMesProperaGlobal;
    String[] PosicioReal;
    String[] SubstitueixCapsa;
    private String[] rowheight;
    protected String Treure;
    protected String addElement;
    protected String appendAttribute;
    protected String black;
    protected String calculateCellPositions;
    public String emptyCellScript;
    public int row_minoccur;
    public int row_maxoccur;
    public int column_minoccur;
    public int column_maxoccur;
    private int marge_exterior;
    static boolean calculateMaxDimensions;
    
    public AbstractMatrixBox() {
        this(3, 3);
    }
    
    public AbstractMatrixBox(final int columnes, final int files) {
        this.C = "";
        this.B = "";
        this.D = true;
        this.F = false;
        this.autoincrement = false;
        this.rowcol = true;
        this.J = Color.black;
        this.S = Color.lightGray;
        this.stringAlign = "";
        this.alignRowNumber = -1;
        this.PosSeg_Fora = "";
        this.Treure = "";
        this.addElement = "";
        this.appendAttribute = "";
        this.black = "";
        this.calculateCellPositions = "";
        this.row_minoccur = 1;
        this.row_maxoccur = 1000;
        this.column_minoccur = 1;
        this.column_maxoccur = 1000;
        this.columnes = columnes;
        this.files = files;
        super.color = 3;
    }
    
    public final int nombreMinimDeFills() {
        return this.files * this.columnes;
    }
    
    public void enCalculRect(final BoxComponent boxComponent) {
        if (this.columnes == 0 || this.files == 0) {
            return;
        }
        if (this.B.length() == 0) {
            this.marge_exterior = 0;
        }
        else {
            this.marge_exterior = Attributes.strToDim(this, this.B, boxComponent.getDPI());
        }
        this.calculateMaxDimensions(boxComponent.getDPI());
        this.calculateCellPositions(boxComponent);
    }
    
    public final void calculateMaxDimensions(final int n) {
        this.E = new int[this.columnes];
        this.G = new int[this.files];
        this.PosAnt_Fill = new int[this.files];
        for (int i = 0; i < this.columnes; ++i) {
            this.E[i] = 0;
        }
        for (int j = 0; j < this.files; ++j) {
            this.G[j] = 0;
            this.PosAnt_Fill[j] = 0;
        }
        this.A = 0;
        for (int k = 0; k < this.columnes; ++k) {
            for (int l = 0; l < this.files; ++l) {
                final AbstractBox abstractBox = super.fill[k + l * this.columnes];
                this.E[k] = Math.max(this.E[k], abstractBox.width);
                this.G[l] = Math.max(this.G[l], abstractBox.height - abstractBox.baseline);
                this.PosAnt_Fill[l] = Math.max(this.PosAnt_Fill[l], abstractBox.baseline);
            }
            this.A = Math.max(this.A, this.E[k]);
        }
        for (int n2 = 0; n2 < this.files; ++n2) {
            final int[] g = this.G;
            final int n3 = n2;
            g[n3] += this.PosAnt_Fill[n2];
        }
        if (this.rowheight != null) {
            int n4 = 0;
            for (int n5 = 0; n5 < this.files; ++n5) {
                if (n5 < this.rowheight.length) {
                    n4 = n5;
                }
                if (!this.rowheight[n4].equals("auto")) {
                    final int n6 = Attributes.strToDim(this, this.rowheight[n4], n) - this.G[n5];
                    final int[] g2 = this.G;
                    final int n7 = n5;
                    g2[n7] += n6;
                    final int[] posAnt_Fill = this.PosAnt_Fill;
                    final int n8 = n5;
                    posAnt_Fill[n8] += n6 / 2;
                }
            }
        }
        if (this.columnwidth != null) {
            int n9 = 0;
            for (int n10 = 0; n10 < this.columnes; ++n10) {
                if (n10 < this.columnwidth.length) {
                    n9 = n10;
                }
                if (!this.columnwidth[n9].equals("auto")) {
                    this.E[n10] = Math.max(this.E[n10], Attributes.strToDim(this, this.columnwidth[n9], n));
                }
            }
        }
    }
    
    public final void calculateCellPositions(final BoxComponent boxComponent) {
        this.PosAnt = new int[this.columnes + 1];
        this.Z = new int[this.files + 1];
        this.PosAnt[0] = this.I(boxComponent, 0) + this.C();
        int i;
        for (i = 1; i < this.columnes; ++i) {
            this.PosAnt[i] = this.PosAnt[i - 1] + this.E[i - 1] + this.I(boxComponent, i);
        }
        this.PosAnt[this.columnes] = this.PosAnt[this.columnes - 1] + this.E[i - 1];
        this.Z[0] = this.Z(boxComponent, 0) + this.Z();
        for (int j = 1; j < this.files; ++j) {
            this.Z[j] = this.Z[j - 1] + this.G[j - 1] + this.Z(boxComponent, j);
        }
        this.Z[this.files] = this.Z[this.files - 1] + this.G[this.files - 1];
        for (int k = 0; k < this.files; ++k) {
            for (int l = 0; l < this.columnes; ++l) {
                final AbstractBox abstractBox = super.fill[l + k * this.columnes];
                int align;
                if (this.PosSeg_Fill == null || this.PosSeg_Fill.length == 0) {
                    align = Attributes.getAlign(this, 1);
                }
                else if (l < this.PosSeg_Fill.length) {
                    align = this.PosSeg_Fill[l];
                }
                else {
                    align = this.PosSeg_Fill[this.PosSeg_Fill.length - 1];
                }
                if (align == 1) {
                    abstractBox.x = this.PosAnt[l] + (this.E[l] - abstractBox.width) / 2;
                }
                else if (align == 0) {
                    abstractBox.x = this.PosAnt[l];
                }
                else if (align == 2) {
                    abstractBox.x = this.PosAnt[l] + (this.E[l] - abstractBox.width);
                }
                abstractBox.y = this.Z[k] + this.PosAnt_Fill[k] - abstractBox.baseline;
            }
        }
        super.width = this.PosAnt[this.columnes] + this.I(boxComponent, this.columnes) + this.C();
        super.height = this.Z[this.files] + this.Z(boxComponent, this.files) + this.Z();
        this.PosSeg = -1;
        if (this.stringAlign == null || this.stringAlign.length() == 0) {
            this.alignRowNumber = -1;
            this.PosSeg = Utils.indexOf(Attributes.css_valign, Attributes.getStyle(this, "vertical-align"));
            if (this.PosSeg < 0) {
                this.PosSeg = 0;
            }
        }
        if (this.PosSeg >= 0) {
            switch (this.PosSeg) {
                case 0: {
                    super.baseline = super.height / 2 + this.em(0.25f);
                    break;
                }
                case 4: {
                    super.baseline = super.height;
                    break;
                }
                case 2: {
                    super.baseline = super.height / 2;
                    break;
                }
                case 1: {
                    super.baseline = super.fill[0].y + super.fill[0].baseline;
                    break;
                }
                case 3: {
                    super.baseline = super.fill[(this.files - 1) * this.columnes].y + super.fill[(this.files - 1) * this.columnes].baseline;
                    break;
                }
            }
        }
        else {
            int baseline;
            int height;
            int n;
            if (this.alignRowNumber < 0 || this.alignRowNumber >= this.files) {
                baseline = 0;
                height = super.height;
                n = height / 2;
            }
            else {
                baseline = this.Z[this.alignRowNumber];
                height = this.G[this.alignRowNumber];
                n = this.PosAnt_Fill[this.alignRowNumber];
            }
            switch (this.PosAnt_Fora) {
                case 0: {
                    super.baseline = baseline + height / 2 + this.em(0.25f);
                    break;
                }
                case 4: {
                    super.baseline = baseline + n;
                    break;
                }
                case 2: {
                    super.baseline = baseline + height / 2;
                    break;
                }
                case 1: {
                    super.baseline = baseline;
                    break;
                }
                case 3: {
                    super.baseline = baseline + height - 1;
                    break;
                }
            }
        }
    }
    
    public final boolean hasProperties() {
        return true;
    }
    
    public boolean hasMenuProperties() {
        return true;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.C()) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x > super.width - this.C()) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        int n;
        for (n = 0; n + 1 < this.columnes && this.PosAnt[n + 1] <= point2.x; ++n) {}
        int n2;
        for (n2 = 0; n2 + 1 < this.files && this.Z[n2 + 1] <= point2.y; ++n2) {}
        return super.fill[n + n2 * this.columnes].PosicioMesProperaGlobal(point, array);
    }
    
    public final BoxPosition PosSeg(final int n, final AbstractBox[] array) {
        if (array == null) {
            return super.PosSeg(n, array);
        }
        if (n % this.columnes < this.columnes - 1) {
            return new BoxPosition(this, n + 1);
        }
        return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
    }
    
    public final BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        if (array == null) {
            return super.PosSeg_Fora(array);
        }
        final int index = Utils.indexOf(array, this);
        if (index > 0) {
            final int index2 = Utils.indexOf(super.fill, array[index - 1]);
            if (index2 >= 0) {
                return new BoxPosition(this, index2 / this.columnes * this.columnes);
            }
        }
        else if (index < 0) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, 0);
    }
    
    public final BoxPosition PosSeg_Fill(final int n, final AbstractBox[] array) {
        if (array == null) {
            return super.PosSeg_Fill(n, array);
        }
        return new BoxPosition(this, n);
    }
    
    public final BoxPosition PosAnt(final int n, final AbstractBox[] array) {
        if (array == null) {
            return super.PosAnt(n, array);
        }
        if (n % this.columnes > 0) {
            return new BoxPosition(this, n - 1);
        }
        return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        if (array == null) {
            return super.PosAnt_Fora(array);
        }
        final int index = Utils.indexOf(array, this);
        if (index > 0) {
            final int index2 = Utils.indexOf(super.fill, array[index - 1]);
            if (index2 >= 0) {
                return new BoxPosition(this, (index2 / this.columnes + 1) * this.columnes - 1);
            }
        }
        else if (index < 0) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, 0);
    }
    
    public final BoxPosition PosAnt_Fill(final int n, final AbstractBox[] array) {
        if (array == null) {
            return super.PosAnt_Fill(n, array);
        }
        return new BoxPosition(this, n);
    }
    
    public final Rectangle getRectangleSeleccio(final int n, int n2, final BoxComponent boxComponent) {
        if (n == n2) {
            return new Rectangle(0, 0, 0, 0);
        }
        final Point posicioReal = this.PosicioReal();
        --n2;
        final int n3 = n % this.columnes;
        final int n4 = n / this.columnes;
        final int n5 = n2 % this.columnes;
        final int n6 = n2 / this.columnes;
        return Utils.unio(new Rectangle(posicioReal.x + this.PosAnt[n3], posicioReal.y + this.Z[n4], this.PosAnt[n3 + 1] - this.PosAnt[n3], this.Z[n4 + 1] - this.Z[n4]), new Rectangle(posicioReal.x + this.PosAnt[n5], posicioReal.y + this.Z[n6], this.PosAnt[n5 + 1] - this.PosAnt[n5], this.Z[n6 + 1] - this.Z[n6]));
    }
    
    public final BoxPosition EliminaFiles(final BoxComponent boxComponent, final int n, final int n2) {
        if (n == 0 && n2 == this.files) {
            final AbstractBox parentBox = this.getParentBox();
            parentBox.SubstitueixCapsa(new MarkupBox("\\caret"), super.p_en_pare, boxComponent);
            return parentBox.findAndRemoveMarkup(boxComponent, "<mo>caret</mo>");
        }
        this.files -= n2 - n;
        for (int i = n; i < n2; ++i) {
            for (int j = 0; j < this.columnes; ++j) {
                this.Treure(n * this.columnes, boxComponent);
            }
        }
        if (n == 0) {
            return super.fill[0].PosSeg_Fora();
        }
        return super.fill[n * this.columnes - 1].PosAnt_Fora();
    }
    
    public final BoxPosition AfegeixFila(final BoxComponent boxComponent, final int n) {
        ++this.files;
        for (int i = 0; i < this.columnes; ++i) {
            this.Afegir(this.PosAnt(boxComponent), n * this.columnes, boxComponent);
        }
        return super.fill[n * this.columnes].PosSeg_Fora();
    }
    
    public final BoxPosition EliminaColumnes(final BoxComponent boxComponent, final int n, final int n2) {
        if (n == 0 && n2 == this.columnes) {
            final AbstractBox parentBox = this.getParentBox();
            parentBox.SubstitueixCapsa(new MarkupBox("\\caret"), super.p_en_pare, boxComponent);
            return parentBox.findAndRemoveMarkup(boxComponent, "<mo>caret</mo>");
        }
        this.columnes -= n2 - n;
        for (int i = 0; i < this.files; ++i) {
            for (int j = n; j < n2; ++j) {
                this.Treure(n + i * this.columnes, boxComponent);
            }
        }
        if (n == this.columnes) {
            return super.fill[n - 1].PosAnt_Fora();
        }
        return super.fill[n].PosSeg_Fora();
    }
    
    public final BoxPosition AfegeixColumna(final BoxComponent boxComponent, final int n) {
        ++this.columnes;
        for (int i = 0; i < this.files; ++i) {
            this.Afegir(this.PosAnt(boxComponent), n + i * this.columnes, boxComponent);
        }
        return super.fill[n].PosSeg_Fora();
    }
    
    protected final AbstractBox PosAnt(final BoxComponent boxComponent) {
        if (boxComponent != null && this.emptyCellScript != null) {
            return boxComponent.parse(this.emptyCellScript);
        }
        return new EmptyBox();
    }
    
    public final boolean isEmpty(final BoxComponent boxComponent) {
        final Class<? extends AbstractBox> class1 = this.PosAnt(boxComponent).getClass();
        for (int i = 0; i < super.nfills; ++i) {
            if (class1 != super.fill[i].getClass() && !MarkupBox.isCaret(super.fill[i])) {
                return false;
            }
        }
        return true;
    }
    
    public final AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new MatrixSelection(this, stack, stack2, b);
    }
    
    public boolean fbvisible(final int n) {
        return true;
    }
    
    protected int Z() {
        return this.marge_exterior;
    }
    
    protected int C() {
        return this.marge_exterior;
    }
    
    protected int I(final BoxComponent boxComponent, final int n) {
        if (n == 0 || n == this.columnes) {
            if (this.SubstitueixCapsa != null && this.SubstitueixCapsa.length > 0) {
                return Attributes.strToDim(this, this.SubstitueixCapsa[0], boxComponent.getDPI());
            }
            return this.em(0.2f);
        }
        else {
            if (this.PosicioReal != null && this.PosicioReal.length > 0) {
                final int length = this.PosicioReal.length;
                String s;
                if (n - 1 >= length) {
                    s = this.PosicioReal[length - 1];
                }
                else {
                    s = this.PosicioReal[n - 1];
                }
                return Attributes.strToDim(this, s, boxComponent.getDPI());
            }
            return this.em(0.4f);
        }
    }
    
    protected int Z(final BoxComponent boxComponent, final int n) {
        if (n == 0 || n == this.files) {
            if (this.SubstitueixCapsa != null && this.SubstitueixCapsa.length > 1) {
                return Attributes.strToDim(this, this.SubstitueixCapsa[1], boxComponent.getDPI());
            }
            return this.em(0.125f);
        }
        else {
            if (this.PosicioMesProperaGlobal != null && this.PosicioMesProperaGlobal.length > 0) {
                final int length = this.PosicioMesProperaGlobal.length;
                String s;
                if (n - 1 >= length) {
                    s = this.PosicioMesProperaGlobal[length - 1];
                }
                else {
                    s = this.PosicioMesProperaGlobal[n - 1];
                }
                return Attributes.strToDim(this, s, boxComponent.getDPI());
            }
            return this.em(0.25f);
        }
    }
    
    public String scriptCommand(final BoxScripting boxScripting) {
        return "mtable";
    }
    
    public void attributes(final BoxScripting boxScripting) {
        this.matrixAttributes(boxScripting);
    }
    
    public final void matrixAttributes(final BoxScripting boxScripting) {
    }
    
    public void exchangeProperties(final Hashtable hashtable, final int n) {
        this.PosSeg_Fora = Attributes.exchangeString(hashtable, "columnalign", n, this.PosSeg_Fora, "");
        this.stringAlign = Attributes.exchangeString(hashtable, "align", n, this.stringAlign, "");
        this.Treure = Attributes.exchangeString(hashtable, "rowheight", n, this.Treure, "");
        this.addElement = Attributes.exchangeString(hashtable, "columnwidth", n, this.addElement, "");
        this.appendAttribute = Attributes.exchangeString(hashtable, "rowspacing", n, this.appendAttribute, "");
        this.black = Attributes.exchangeString(hashtable, "columnspacing", n, this.black, "");
        this.calculateCellPositions = Attributes.exchangeString(hashtable, "framespacing", n, this.calculateCellPositions, "");
        if (n == 0 || n == 4097) {
            final String exchangeString = Attributes.exchangeString(hashtable, "groupalign", n, "", "");
            if (exchangeString.length() > 0) {
                final String substitute = Utils.substitute(Utils.substitute(exchangeString, '{', ""), '}', "");
                this.PosSeg_Fora = substitute;
                this.PosSeg_Fill = translateAlign(substitute, Attributes.halign, 2);
            }
            if (this.PosSeg_Fora != null && this.PosSeg_Fora.length() > 0) {
                this.PosSeg_Fill = translateAlign(this.PosSeg_Fora, Attributes.halign, 2);
            }
            if (this.appendAttribute != null && this.appendAttribute.length() > 0) {
                this.PosicioMesProperaGlobal = Utils.split(this.appendAttribute);
            }
            if (this.black != null && this.black.length() > 0) {
                this.PosicioReal = Utils.split(this.black);
            }
            if (this.calculateCellPositions != null && this.calculateCellPositions.length() > 0) {
                this.SubstitueixCapsa = Utils.split(this.calculateCellPositions);
            }
            this.PosAnt_Fora = 0;
            if (this.stringAlign != null && this.stringAlign.length() > 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(this.stringAlign);
                if (stringTokenizer.hasMoreTokens()) {
                    final int index = Utils.indexOf(Attributes.mathml_valign, stringTokenizer.nextToken());
                    if (index != -1) {
                        this.PosAnt_Fora = index;
                    }
                    this.alignRowNumber = -1;
                    if (stringTokenizer.hasMoreTokens()) {
                        try {
                            this.alignRowNumber = Integer.parseInt(stringTokenizer.nextToken());
                            if (-this.files <= this.alignRowNumber && this.alignRowNumber < 0) {
                                this.alignRowNumber += this.files;
                            }
                            else if (0 < this.alignRowNumber && this.alignRowNumber <= this.files) {
                                --this.alignRowNumber;
                            }
                            else {
                                this.alignRowNumber = -1;
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            if (this.Treure != null && this.Treure.length() > 0) {
                this.rowheight = Utils.split(this.Treure);
            }
            if (this.addElement != null && this.addElement.length() > 0) {
                this.columnwidth = Utils.split(this.addElement);
            }
        }
    }
    
    public static final int[] translateAlign(final String s, final String[] array, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final Vector vector = new Vector<Integer>();
        while (stringTokenizer.hasMoreTokens()) {
            int index = Utils.indexOf(array, stringTokenizer.nextToken());
            if (index == -1) {
                index = n;
            }
            vector.addElement(new Integer(index));
        }
        final int[] array2 = new int[vector.size()];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = vector.elementAt(i);
        }
        return array2;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        this.submatrix(boxScripting, 0, 0, this.columnes, this.files);
    }
    
    protected void I(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        for (int i = n2; i < n4; ++i) {
            boxScripting.openTag("mtr");
            if (this.rowheight != null) {
                if (i < this.rowheight.length) {
                    n5 = i;
                }
                boxScripting.appendAttribute("height", this.rowheight[n5]);
            }
            for (int j = n; j < n3; ++j) {
                boxScripting.openTag("mtd");
                super.fill[j + i * this.columnes].script(boxScripting);
                boxScripting.closeTag();
            }
            boxScripting.closeTag();
        }
    }
    
    public void submatrix(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        this.I(boxScripting, n, n2, n3, n4);
    }
    
    public void buildContextualMenu(final FormulaEditor formulaEditor) {
    }
    
    public int getSplitFactor(final int n) {
        final int z = this.Z();
        if (n > z && n < super.width - z) {
            return 600;
        }
        return 0;
    }
    
    static {
        AbstractMatrixBox.calculateMaxDimensions = true;
    }
}
