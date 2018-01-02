import java.util.Stack;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Insets;

// 
// Decompiled by Procyon v0.5.30
// 

public class TokensBox extends AbstractBox
{
    public Length paddingTop;
    public Length paddingLeft;
    public Length paddingRight;
    public Length paddingBottom;
    public Length lineHeight;
    public int alignment;
    private Insets insets;
    private static boolean FIXED_HEIGHT_CARET;
    public final Vector lines;
    
    public TokensBox() {
        this.paddingTop = new Length();
        this.paddingLeft = new Length();
        this.paddingRight = new Length();
        this.paddingBottom = new Length();
        this.lineHeight = new Length("2px");
        this.alignment = 0;
        this.insets = new Insets(0, 0, 0, 0);
        this.lines = new Vector(8);
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.InserirCapsa(null, array[i]);
        }
    }
    
    public final boolean rect_depen_germans() {
        if (super.nfills > 0) {
            return super.fill[0].rect_depen_germans();
        }
        return super.rect_depen_germans();
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        if (boxComponent == null) {
            return;
        }
        final int dpi = boxComponent.getDPI();
        final Insets insets = new Insets(this.paddingTop.toUserUnit(super.EM, dpi), this.paddingLeft.toUserUnit(super.EM, dpi), this.paddingBottom.toUserUnit(super.EM, dpi), this.paddingRight.toUserUnit(super.EM, dpi));
        final int userUnit = this.lineHeight.toUserUnit(super.EM, dpi);
        int width;
        if (super.recommendedWidth != null) {
            width = super.recommendedWidth.toUserUnit(super.EM, dpi) - insets.left - insets.right;
        }
        else {
            width = -1;
        }
        this.adjustPixels(boxComponent);
        if (this.getParentBox() instanceof MultilineBox) {
            this.buildLines(width);
        }
        else {
            this.buildLines(Integer.MAX_VALUE);
        }
        final int size = this.lines.size();
        int top = insets.top;
        int n = insets.left;
        int alignment = this.alignment;
        if (width < 0 || alignment < 0 || alignment > 3) {
            alignment = 0;
        }
        super.height = 0;
        super.width = width;
        for (int i = 0; i < size; ++i) {
            final TokensBox$Line tokensBox$Line = this.lines.elementAt(i);
            TokensBox$Line.I(tokensBox$Line);
            final int n2 = tokensBox$Line.bounds.height - userUnit;
            if (n2 < 0) {
                final Rectangle bounds = tokensBox$Line.bounds;
                bounds.height -= n2;
                final TokensBox$Line tokensBox$Line2 = tokensBox$Line;
                tokensBox$Line2.baseline -= n2 / 2;
            }
            super.height += tokensBox$Line.bounds.height;
            if (TokensBox$Line.Z(tokensBox$Line) > width) {
                n = insets.left;
            }
            else if (alignment == 2) {
                n = width - TokensBox$Line.Z(tokensBox$Line) + insets.left;
            }
            else if (alignment == 1) {
                n = (width - TokensBox$Line.Z(tokensBox$Line)) / 2 + insets.left;
            }
            TokensBox$Line.I(tokensBox$Line, n, top, alignment == 3 && i < size - 1, width);
            if (super.width < TokensBox$Line.Z(tokensBox$Line)) {
                super.width = TokensBox$Line.Z(tokensBox$Line);
            }
            top += tokensBox$Line.bounds.height;
        }
        if (size >= 1) {
            final TokensBox$Line tokensBox$Line3 = this.lines.firstElement();
            super.baseline = tokensBox$Line3.baseline;
            if (size == 1 && alignment == 0) {
                super.width = tokensBox$Line3.bounds.width;
            }
        }
        else {
            super.baseline = super.height / 2;
        }
        super.height += insets.top + insets.bottom;
        super.width += insets.left + insets.right;
    }
    
    public final void onUpdateAttributes(final BoxComponent boxComponent) {
        this.alignment = Attributes.getAlign(this, -1);
        if (this.alignment == -1) {
            if (this.getParentBox() == null) {
                this.alignment = 0;
            }
            else if (this.getParentBox() instanceof MultilineBox) {
                this.alignment = ((MultilineBox)this.getParentBox()).alignment;
            }
            else {
                this.alignment = Attributes.getAlign(this.getParentBox(), 0);
            }
        }
        super.onUpdateAttributes(boxComponent);
    }
    
    public final Point PosicioCaret(final Point point, int n, final BoxComponent boxComponent) {
        Point point2;
        if (super.nfills == 0 || n >= super.nfills) {
            final TokensBox$Line tokensBox$Line = this.lines.lastElement();
            final Point location;
            point2 = (location = tokensBox$Line.bounds.getLocation());
            location.x += point.x + tokensBox$Line.bounds.width;
            final Point point3 = point2;
            point3.y += point.y + tokensBox$Line.baseline;
            n = super.nfills;
        }
        else {
            if (n <= 0) {
                n = 0;
            }
            point2 = new Point(point.x + super.fill[n].x, point.y + super.fill[n].y + super.fill[n].baseline);
        }
        if (TokensBox.FIXED_HEIGHT_CARET) {
            final Point point4 = point2;
            point4.y -= this.em(0.75f);
        }
        else if (super.nfills > 0) {
            n = Math.max(n - 1, 0);
            final Point point5 = point2;
            point5.y -= super.fill[n].baseline;
        }
        return point2;
    }
    
    public final int TamanyCaret(final int n) {
        if (TokensBox.FIXED_HEIGHT_CARET) {
            if (super.nfills <= 0) {
                return this.em();
            }
            if (n <= 0) {
                return super.fill[0].em();
            }
            if (n < super.nfills) {
                return super.fill[n - 1].em();
            }
            return super.fill[super.nfills - 1].em();
        }
        else {
            if (super.nfills <= 0) {
                return this.em();
            }
            if (n <= 0) {
                return super.fill[0].height;
            }
            if (n < super.nfills) {
                return super.fill[n - 1].height;
            }
            return super.fill[super.nfills - 1].height;
        }
    }
    
    public final AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new TokensSelection(this, stack, stack2, b);
    }
    
    public final AbstractSelection SeleccionaDreta(final Stack stack) {
        final Stack<Integer> stack2 = new Stack<Integer>();
        stack2.push(new Integer(super.nfills));
        return new TokensSelection(this, stack, stack2, false);
    }
    
    public final AbstractSelection SeleccionaEsquerra(final Stack stack) {
        final Stack<Integer> stack2 = new Stack<Integer>();
        stack2.push(new Integer(0));
        return new TokensSelection(this, stack2, stack, true);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        final TokensBox$Line line = this.getLineAt(point2.y);
        BoxPosition posicioMesProperaGlobal;
        if (point2.x <= super.fill[line.firstIndex].x) {
            posicioMesProperaGlobal = new BoxPosition(this, line.firstIndex);
        }
        else if (point2.x >= super.fill[line.lastIndex].x + super.fill[line.lastIndex].width - 1) {
            posicioMesProperaGlobal = new BoxPosition(this, line.lastIndex + 1);
        }
        else {
            final AbstractBox box = line.getBoxAt(point2.x);
            if (box == null) {
                return null;
            }
            posicioMesProperaGlobal = box.PosicioMesProperaGlobal(point, array);
        }
        if (line.lastIndex != super.nfills - 1 && posicioMesProperaGlobal.c == this && posicioMesProperaGlobal.x == line.lastIndex + 1) {
            final BoxPosition boxPosition = posicioMesProperaGlobal;
            --boxPosition.x;
        }
        return posicioMesProperaGlobal;
    }
    
    public final TokensBox$Line getLineAt(final int n) {
        int i = this.lines.size() - 1;
        int n2 = 0;
        TokensBox$Line tokensBox$Line;
        do {
            final int n3 = (n2 + i) / 2;
            tokensBox$Line = this.lines.elementAt(n3);
            if (n < tokensBox$Line.bounds.y) {
                i = n3 - 1;
            }
            else {
                if (n <= tokensBox$Line.bounds.y + tokensBox$Line.bounds.height) {
                    break;
                }
                n2 = n3 + 1;
            }
        } while (i >= n2);
        return tokensBox$Line;
    }
    
    public final int getLineIndex(final int n) {
        int i = this.lines.size() - 1;
        int n2 = 0;
        while (i >= n2) {
            final int n3 = (n2 + i) / 2;
            final TokensBox$Line tokensBox$Line = this.lines.elementAt(n3);
            if (n < tokensBox$Line.firstIndex) {
                i = n3 - 1;
            }
            else {
                if (n <= tokensBox$Line.lastIndex) {
                    return n3;
                }
                n2 = n3 + 1;
            }
        }
        return -1;
    }
    
    private void buildLines(int n) {
        this.lines.removeAllElements();
        TokensBox$Line tokensBox$Line = new TokensBox$Line(this);
        if (super.nfills > 0) {
            for (int i = 0; i < super.nfills; ++i) {
                SpaceBox.restoreBoxWidth(super.fill[i]);
            }
            if (n < 0) {
                n = Integer.MAX_VALUE;
            }
            if (n != Integer.MAX_VALUE) {
                n += 3;
            }
            int n2 = -1;
            int j = 0;
            int n3 = 0;
            int n4 = 0;
            int n5 = 1;
            while (j < super.nfills) {
                int boxType;
                if (n2 == -1) {
                    boxType = SpaceBox.getBoxType(super.fill[j]);
                }
                else {
                    boxType = n2;
                }
                n2 = -1;
                final int n6 = 1;
                final int width = super.fill[j].width;
                if (boxType == 1) {
                    tokensBox$Line.lastIndex = j;
                    this.lines.addElement(tokensBox$Line);
                    tokensBox$Line = new TokensBox$Line(this);
                    tokensBox$Line.lastIndex = j;
                    ++j;
                    tokensBox$Line.firstIndex = j;
                    n3 = 0;
                    n4 = 0;
                    n5 = 1;
                }
                else {
                    if (boxType == 5) {
                        if (tokensBox$Line.bounds.width + width > n && tokensBox$Line.firstIndex != j) {
                            this.lines.addElement(tokensBox$Line);
                            tokensBox$Line = new TokensBox$Line(this);
                            tokensBox$Line.firstIndex = j;
                        }
                        else {
                            TokensBox$Line.I(tokensBox$Line, n3);
                            TokensBox$Line.Z(tokensBox$Line, n4);
                        }
                        TokensBox$Line.Z(tokensBox$Line, width);
                        n3 = 0;
                        n4 = 0;
                        n5 = 1;
                    }
                    else {
                        if (n5 != 0 || (tokensBox$Line.bounds.width + width > n && tokensBox$Line.firstIndex != j)) {
                            n3 += n6;
                            n4 += width;
                        }
                        else {
                            TokensBox$Line.I(tokensBox$Line, n3);
                            TokensBox$Line.Z(tokensBox$Line, n4);
                            TokensBox$Line.I(tokensBox$Line, n6);
                            TokensBox$Line.Z(tokensBox$Line, width);
                            n3 = 0;
                            n4 = 0;
                        }
                        n5 = 0;
                    }
                    final Rectangle bounds = tokensBox$Line.bounds;
                    bounds.width += width;
                    j += n6;
                    tokensBox$Line.lastIndex = j - 1;
                }
            }
        }
        this.lines.addElement(tokensBox$Line);
    }
    
    public final void enAfegir(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        super.enAfegir(boxComponent, abstractBox, n);
        if (abstractBox instanceof TextBox) {
            this.split((TextBox)abstractBox);
        }
    }
    
    private void split(final TextBox textBox) {
        final String cadena = textBox.cadena;
        final int n = textBox.p_en_pare + 1;
        int i;
        for (i = 0; i < cadena.length(); ++i) {
            final char char1 = cadena.charAt(i);
            if (char1 == ' ') {
                this.AfegirNoCalc(new SpaceBox(0), n);
                break;
            }
            if (char1 == '\u2028') {
                this.AfegirNoCalc(new SpaceBox(1), n);
                break;
            }
        }
        if (i == cadena.length()) {
            return;
        }
        textBox.cadena = cadena.substring(0, i);
        if (i < cadena.length() - 1) {
            this.AfegirNoCalc(new TextBox(cadena.substring(i + 1), textBox.type), n + 1);
        }
        if (i == 0) {
            this.TreureNoCalc(textBox.p_en_pare);
        }
    }
    
    public final BoxPosition InsertStringAndFindCaret(final BoxComponent boxComponent, final int n, final String s) {
        return this.InsertAndFindCaret(boxComponent, boxComponent.parse(s), n);
    }
    
    public final BoxPosition InsertAndFindCaret(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        BoxPosition inserirCapsa = this.InserirCapsa(boxComponent, n, abstractBox, true);
        final BoxPosition andRemoveMarkup = this.findAndRemoveMarkup(boxComponent, "\\caret");
        if (andRemoveMarkup != null) {
            inserirCapsa = andRemoveMarkup;
        }
        return inserirCapsa;
    }
    
    public final void InserirCapsa(final BoxComponent boxComponent, final AbstractBox abstractBox) {
        this.InserirCapsa(boxComponent, super.nfills, abstractBox, false);
    }
    
    public final BoxPosition InserirCapsa(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        return this.InserirCapsa(boxComponent, n, abstractBox, false);
    }
    
    private static void ferInsertaFinal(final BoxComponent boxComponent, final AbstractBox abstractBox, final AbstractBox abstractBox2, final boolean b) {
        final AbstractBox abstractBox3 = abstractBox.fill[abstractBox.nfills - 1];
        if (abstractBox3 instanceof EmptyBox) {
            if (abstractBox2 instanceof TokensBox || abstractBox2 instanceof EmptyBox) {
                abstractBox.SubstitueixCapsa(abstractBox2, abstractBox.nfills - 1, boxComponent);
            }
            else {
                final TokensBox tokensBox = new TokensBox();
                tokensBox.AfegirNoCalc(abstractBox2);
                abstractBox.SubstitueixCapsa(tokensBox, abstractBox.nfills - 1, boxComponent);
            }
        }
        else if (abstractBox3 instanceof TokensBox) {
            ((TokensBox)abstractBox3).InserirCapsa(boxComponent, abstractBox3.nfills, abstractBox2, b);
        }
        else if (abstractBox3 instanceof TokensVBox) {
            ferInsertaFinal(boxComponent, abstractBox3, abstractBox2, b);
        }
    }
    
    private BoxPosition InserirCapsa(final BoxComponent boxComponent, final int n, final AbstractBox abstractBox, final boolean b) {
        int n2 = 0;
        if (n > 0 && super.fill[n - 1].inserta_final()) {
            final AbstractBox abstractBox2 = super.fill[n - 1];
            ferInsertaFinal(boxComponent, abstractBox2, abstractBox, b);
            return abstractBox2.PosAnt_Fora();
        }
        if (b && super.nfills == 0 && abstractBox instanceof TokensBox && this.hasAttributes() && abstractBox.hasAttributes()) {
            this.I(abstractBox);
            while (abstractBox.nfills != 0) {
                super.Afegir(abstractBox.TreureNoCalc(), n, boxComponent);
                ++n2;
            }
        }
        else if (abstractBox instanceof TokensBox && !abstractBox.hasAttributes()) {
            while (abstractBox.nfills != 0) {
                final AbstractBox treureNoCalc = abstractBox.TreureNoCalc();
                if (b && n2 == 0 && treureNoCalc instanceof ArgumentBox && n < super.nfills) {
                    continue;
                }
                if (b && (abstractBox.nfills == 0 || (abstractBox.nfills == 1 && abstractBox.fill[0] instanceof MarkupBox)) && treureNoCalc instanceof ArgumentBox && n > 0) {
                    break;
                }
                super.Afegir(treureNoCalc, n, boxComponent);
                ++n2;
            }
        }
        else if (!(abstractBox instanceof EmptyBox)) {
            this.Afegir(abstractBox, n, boxComponent);
            ++n2;
        }
        BoxPosition boxPosition;
        if (b) {
            if (!Formula.LEFT_TO_RIGHT) {
                boxPosition = this.joinChildren(n, n + n2, boxComponent);
            }
            else {
                boxPosition = this.joinChildren(n, n, boxComponent);
            }
        }
        else if (!Formula.LEFT_TO_RIGHT) {
            boxPosition = this.PosicioTokens(n + n2, null, false);
        }
        else {
            boxPosition = this.PosicioTokens(n, null, false);
        }
        return boxPosition;
    }
    
    public final BoxPosition joinChildren(final int n, int n2, final BoxComponent boxComponent) {
        if (!(boxComponent instanceof FormulaEditor)) {
            return new BoxPosition(this, n2);
        }
        BoxPosition boxPosition = null;
        for (int i = n - 1; i < super.nfills; ++i) {
            final FormulaEditor formulaEditor = (FormulaEditor)boxComponent;
            final int countJoins = formulaEditor.countJoins(this, i);
            if (i < n2 && n2 <= i + countJoins) {
                int n3 = 0;
                for (int j = i; j < n2; ++j) {
                    n3 += ((TextBox)super.fill[j]).cadena.length();
                }
                n2 = i;
                boxPosition = new BoxPosition(super.fill[i], n3);
            }
            else if (n2 > i) {
                n2 -= countJoins;
            }
            formulaEditor.doJoin(this, i);
            if (countJoins <= 0) {}
        }
        if (boxPosition != null) {
            return boxPosition;
        }
        return this.PosicioTokens(n2, null);
    }
    
    public final BoxPosition ChangeByString(final BoxComponent boxComponent, final int n, final String s) {
        this.Treure(n, boxComponent);
        return this.InsertStringAndFindCaret(boxComponent, n, s);
    }
    
    public final BoxPosition findAndRemoveMarkup(final BoxComponent boxComponent, final String s) {
        BoxPosition boxPosition = null;
        for (int i = 0; i < super.nfills; ++i) {
            BoxPosition boxPosition2;
            if (MarkupBox.isMarkup(super.fill[i], s)) {
                this.Treure(i, boxComponent);
                if (super.nfills == 0) {
                    final EmptyBox emptyBox = new EmptyBox();
                    emptyBox.posaAtribut(boxComponent, Attributes.obteAtributs(this));
                    this.getParentBox().SubstitueixCapsa(emptyBox, super.p_en_pare, boxComponent);
                    boxPosition2 = new BoxPosition(emptyBox, 0);
                }
                else {
                    boxPosition2 = this.joinChildren(i, i, boxComponent);
                }
            }
            else {
                boxPosition2 = super.fill[i].findAndRemoveMarkup(boxComponent, s);
            }
            if (boxPosition == null) {
                boxPosition = boxPosition2;
            }
        }
        return boxPosition;
    }
    
    public final void findAndRemoveMarkups(final BoxComponent boxComponent, final String[] array, final BoxPosition[] array2) {
        for (int i = 0; i < super.nfills; ++i) {
            final AbstractBox abstractBox = super.fill[i];
            for (int j = 0; j < array.length; ++j) {
                if (array2[j] == null) {
                    if (MarkupBox.isMarkup(abstractBox, array[j])) {
                        this.Treure(i, boxComponent);
                        array2[j] = new BoxPosition(this, i);
                        for (int k = 0; k < array2.length; ++k) {
                            if (array2[k] != null) {
                                if (array2[k].c == this) {
                                    if (array2[k].x > i) {
                                        final BoxPosition boxPosition = array2[k];
                                        --boxPosition.x;
                                    }
                                }
                            }
                        }
                        --i;
                        break;
                    }
                    abstractBox.findAndRemoveMarkups(boxComponent, array, array2);
                }
            }
        }
        if (super.nfills == 0) {
            this.getParentBox().SubstitueixCapsa(new EmptyBox(), super.p_en_pare, boxComponent);
        }
    }
    
    public final int nPosicions() {
        return super.nfills;
    }
    
    private BoxPosition PosicioTokens(final int n, final AbstractBox[] array) {
        return this.PosicioTokens(n, array, true);
    }
    
    private BoxPosition PosicioTokens(final int n, final AbstractBox[] array, final boolean b) {
        if (array == null && n < super.nfills && b && !super.fill[n].posicio_inicial()) {
            return super.fill[n].PosSeg_Fora(array);
        }
        if (array == null && n - 1 >= 0 && b && !super.fill[n - 1].posicio_final()) {
            return super.fill[n - 1].PosAnt_Fora(array);
        }
        return new BoxPosition(this, n);
    }
    
    public final BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        if (array == null && !super.fill[0].posicio_inicial()) {
            return this.PosSeg(0, array);
        }
        return this.PosicioTokens(0, array);
    }
    
    public final BoxPosition PosSeg_Fill(final int n, final AbstractBox[] array) {
        if (n >= super.nfills) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        if (array == null && !super.fill[n].posicio_final()) {
            return this.PosSeg(n + 1, array);
        }
        return this.PosicioTokens(n + 1, array);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        if (array == null && !super.fill[super.nfills - 1].posicio_final()) {
            return this.PosAnt(super.nfills, array);
        }
        return this.PosicioTokens(super.nfills, array);
    }
    
    public final BoxPosition PosAnt_Fill(final int n, final AbstractBox[] array) {
        if (n < 0) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (array == null && !super.fill[n].posicio_inicial()) {
            return this.PosAnt(n, array);
        }
        return this.PosicioTokens(n, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            if (this.isStyle(128)) {
                return "p";
            }
            if (boxScripting.scriptVariant == 2 || boxScripting.scriptVariant == 3 || boxScripting.isTop() || this.hasAttributes() || (boxScripting.oneChild && XMLBoxUtils.countScriptChildren(this) != 1)) {
                if (this.needsMStyle()) {
                    return "mstyle";
                }
                return "mrow";
            }
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting, final int n, final int n2) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.onScript(boxScripting, super.fill, n, n2, boxScripting.oneChild && this.scriptCommand(boxScripting) == null);
        }
        else {
            super.onScript(boxScripting, n, n2);
        }
    }
    
    public final int getSplitFactor(final int n) {
        if (n > this.insets.top && n < super.height - this.insets.bottom) {
            return 400;
        }
        return 0;
    }
    
    public final boolean isValid(final int n) {
        return (n <= 0 || super.fill[n - 1].posicio_final()) && (n >= super.nfills || super.fill[n].posicio_inicial());
    }
    
    private void adjustPixels(final BoxComponent boxComponent) {
        final int em = this.em(0.1f);
        for (int i = 1; i < super.nfills; ++i) {
            final int[] verticalMetrics = super.fill[i - 1].getVerticalMetrics(boxComponent);
            final int[] verticalMetrics2 = super.fill[i].getVerticalMetrics(boxComponent);
            if (verticalMetrics != null) {
                if (verticalMetrics2 != null) {
                    while (verticalMetrics[2] + verticalMetrics2[0] > em && verticalMetrics[3] + verticalMetrics2[1] > em) {
                        final AbstractBox abstractBox = super.fill[i - 1];
                        --abstractBox.width;
                        final int[] array = verticalMetrics;
                        final int n = 2;
                        --array[n];
                        final int[] array2 = verticalMetrics;
                        final int n2 = 3;
                        --array2[n2];
                    }
                }
            }
        }
    }
    
    static {
        TokensBox.FIXED_HEIGHT_CARET = false;
    }
}
