import java.util.Hashtable;
import java.util.Stack;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextBox extends AbstractBox
{
    private static int[] tcolor;
    private static String[] tag;
    public String cadena;
    public int type;
    boolean PosAnt_Fill;
    boolean PosSeg_Fill;
    boolean PosicioCaret;
    int PosicioReal;
    int append;
    int appendAttribute;
    int[] appendText;
    static final String[][] beginBox;
    private int lastPos;
    private int cachedLength;
    private char[] charTable;
    
    public TextBox() {
        this.type = 0;
        this.PosicioReal = 1;
        this.append = 1;
        this.lastPos = 0;
        this.cachedLength = 0;
        this.charTable = null;
        this.cadena = "";
        this.setType(0);
    }
    
    public TextBox(final String s, final int type) {
        this.type = 0;
        this.PosicioReal = 1;
        this.append = 1;
        this.lastPos = 0;
        this.cachedLength = 0;
        this.charTable = null;
        this.setType(type);
        this.setText(null, s);
    }
    
    public final void setType(final int type) {
        this.type = type;
        if (type == 4) {
            final boolean b = false;
            this.append = (b ? 1 : 0);
            this.PosicioReal = (b ? 1 : 0);
        }
        super.color = TextBox.tcolor[type];
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        String string = "";
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextBox) {
                string += ((TextBox)array[i]).cadena;
            }
        }
        this.setText(null, string);
    }
    
    public final void inicialitzaModalitat(final Object o) {
        int type = 0;
        if (o.equals("number")) {
            type = 2;
        }
        else if (o.equals("ident")) {
            type = 1;
        }
        else if (o.equals("symbol")) {
            type = 3;
        }
        else if (o.equals("ms")) {
            type = 4;
        }
        else if (o.equals("mtext")) {
            type = 5;
        }
        this.setType(type);
    }
    
    public final boolean rect_depen_germans() {
        return true;
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        super.width = 0;
        this.appendAttribute = 0;
        super.height = this.em();
        super.baseline = this.em(0.75f);
        this.charTable = this.cadena.toCharArray();
        this.lastPos = 0;
        this.cachedLength = 0;
        if (this.PosSeg_Fill() > 0) {
            super.width = this.em(this.cadena);
        }
        else if (this.type == 3) {
            super.width = this.em(0.5f);
        }
        if (this.getAccentContainer() == null && this.type == 3) {
            super.width += this.em(0.1f);
            this.appendAttribute = this.em(0.05f);
        }
        if (this.PosAnt_Fill(boxComponent)) {
            super.baseline -= this.em(0.15f);
            super.height -= (int)(0.6 * this.em(0.15f));
        }
        if (this.isBigOp(boxComponent)) {
            super.baseline -= this.em(0.1f);
            super.height -= this.em(0.1f);
        }
        if (this.getAccentContainer() != null && this.isBar()) {
            super.baseline -= (int)(this.em() * 0.4);
            super.height -= (int)(this.em() * 0.4);
        }
        this.doMicroChanges(boxComponent);
    }
    
    private void doMicroChanges(final BoxComponent boxComponent) {
        int max = 0;
        int max2 = 0;
        int[] computeMetrics = null;
        if (boxComponent instanceof Formula) {
            computeMetrics = ((Formula)boxComponent).getFontUtils().computeMetrics(boxComponent, super.I, this.cadena);
        }
        int[] verticalMetrics = null;
        int[] verticalMetrics2 = null;
        final AbstractBox rightOrNull = this.getRightOrNull();
        final AbstractBox leftOrNull = this.getLeftOrNull();
        if (leftOrNull != null && super.I.equals(leftOrNull.I)) {
            verticalMetrics2 = leftOrNull.getVerticalMetrics(boxComponent);
        }
        if (rightOrNull != null && super.I.equals(rightOrNull.I)) {
            verticalMetrics = rightOrNull.getVerticalMetrics(boxComponent);
        }
        if (computeMetrics != null) {
            if (verticalMetrics2 != null) {
                if (computeMetrics[0] + verticalMetrics2[2] < 1) {
                    max = 1;
                }
                else if (computeMetrics[1] + verticalMetrics2[3] < 1) {
                    max = 1;
                }
                if (computeMetrics[0] + verticalMetrics2[2] + max < 1) {
                    max = 2;
                }
            }
            else {
                max = Math.max(0, -Math.min(computeMetrics[0], computeMetrics[1]));
                if (computeMetrics[0] + computeMetrics[2] + max < 1) {
                    ++max;
                }
            }
            if (verticalMetrics == null) {
                max2 = Math.max(0, -Math.min(computeMetrics[2], computeMetrics[3]));
                if (computeMetrics[2] + computeMetrics[3] + max2 < 1) {
                    ++max2;
                }
            }
        }
        super.width += max;
        this.appendAttribute += max;
        super.width += max2;
        if (computeMetrics != null) {
            final int[] array = computeMetrics;
            final int n = 0;
            array[n] += max;
            final int[] array2 = computeMetrics;
            final int n2 = 1;
            array2[n2] += max;
            final int[] array3 = computeMetrics;
            final int n3 = 2;
            array3[n3] += max2;
            final int[] array4 = computeMetrics;
            final int n4 = 3;
            array4[n4] += max2;
        }
        this.appendText = computeMetrics;
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        final boolean mathRTL = boxComponent.getMathRTL();
        if (this.type == 0 && !this.isStyle(128) && boxComponent instanceof FormulaEditor && ((FormulaEditor)boxComponent).isDesign()) {
            graphics.setColor(Color.yellow);
            graphics.fillRect(0, 0, super.width, super.height);
            graphics.setColor(Color.blue);
        }
        if (this.PosSeg_Fill() == 0) {
            if (this.type == 3) {
                graphics.drawRect(this.appendAttribute, 0, super.width - 1 - this.appendAttribute, super.height - 1);
            }
        }
        else {
            final AbstractBox accentContainer = this.getAccentContainer();
            if (accentContainer != null && this.isBar()) {
                final int em = this.em(0.05f);
                final int n = 2 * em;
                if (this.isStretchy()) {
                    graphics.fillRect(em, super.baseline, accentContainer.width - n, Math.max(this.em(0.05f), 1));
                }
                else {
                    graphics.fillRect(em, super.baseline, super.width - n, Math.max(this.em(0.05f), 1));
                }
            }
            else if (accentContainer != null && (this.isBrackets() || this.isArrow()) && this.isStretchy()) {
                boxComponent.getFontUtils().drawHorizontalStretchy(graphics, 0, super.baseline, accentContainer.width, this.cadena.charAt(0));
            }
            else if (this.isVerticalBracket() && this.cadena.charAt(0) > '\u0080') {
                final int n2 = super.height - this.em(0.1f);
                boxComponent.getFontUtils().drawVerticalStretchy(graphics, 1, n2, n2, this.cadena.charAt(0));
            }
            else {
                int n3;
                if (this.PosAnt_Fill(boxComponent)) {
                    n3 = this.em(0.15f);
                }
                else if (this.isBigOp(boxComponent)) {
                    n3 = this.em(0.1f);
                }
                else {
                    n3 = 0;
                }
                AffineTransform transform = null;
                if (mathRTL && this.type != 3) {
                    final Graphics2D graphics2D = (Graphics2D)graphics;
                    transform = graphics2D.getTransform();
                    graphics2D.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, super.width, 0.0f));
                }
                boxComponent.getFontUtils().drawString(graphics, this.cadena, this.appendAttribute, super.baseline + n3);
                if (mathRTL && this.type != 3) {
                    ((Graphics2D)graphics).setTransform(transform);
                }
            }
        }
    }
    
    private final boolean isBar() {
        return this.PosSeg_Fill() == 1 && (this.cadena.charAt(0) == '¯' || this.cadena.charAt(0) == '\u0304' || this.cadena.charAt(0) == '\u0332');
    }
    
    public final boolean isBrackets() {
        return this.is(false, "\ufe37\ufe38\u23b4\u23b5\ufe35\ufe36^\u02c7\u0302");
    }
    
    public final boolean isVerticalBracket() {
        return this.is(false, "()[]|{}\ufe38\ufe37\u23b5\u23b4\ufe36\ufe35\u2016\u2309\u2308\u230b\u230a<\u2329>\u232a");
    }
    
    public final boolean isArrow() {
        return this.is(false, "\u2190\u2192\u21c0\u2194");
    }
    
    public final int nPosicions() {
        return this.PosSeg_Fill();
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        int n = 0;
        int posSeg_Fill = this.PosSeg_Fill();
        while (n + 1 != posSeg_Fill) {
            if (point2.x < this.em(this.PosicioCaret(0, (n + posSeg_Fill) / 2))) {
                posSeg_Fill = (n + posSeg_Fill) / 2;
            }
            else {
                n = (n + posSeg_Fill) / 2;
            }
        }
        if (2 * (point2.x - this.em(this.PosicioCaret(0, n))) > this.em(this.PosicioCaret(n, n + 1))) {
            ++n;
        }
        if (n < this.PosicioReal) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (n > this.PosSeg_Fill() - this.PosicioReal) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, n);
    }
    
    public final BoxPosition PosSeg(final int n, final AbstractBox[] array) {
        if (n == this.PosSeg_Fill() - this.append) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, n + 1);
    }
    
    public final BoxPosition PosSeg_Fora(final AbstractBox[] array) {
        if (this.PosSeg_Fill() < this.PosicioReal + this.append) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, this.PosicioReal);
    }
    
    public final BoxPosition PosAnt(final int n, final AbstractBox[] array) {
        if (n == this.PosicioReal) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, n - 1);
    }
    
    public final BoxPosition PosAnt_Fora(final AbstractBox[] array) {
        if (this.PosSeg_Fill() < this.PosicioReal + this.append) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return new BoxPosition(this, this.PosSeg_Fill() - this.append);
    }
    
    public final Point PosicioCaret(final Point point, final int n, final BoxComponent boxComponent) {
        if (this.charTable.length == 0) {
            return new Point(point.x + super.width / 2, point.y);
        }
        int n2 = this.em(this.charTable, 0, n) + this.appendAttribute;
        if (FontUtils.isRTL(this.charTable[0]) || boxComponent.getMathRTL()) {
            n2 = super.width - n2;
        }
        return new Point(point.x + n2, point.y);
    }
    
    public final Rectangle getRectangleSeleccio(final int n, final int n2, final BoxComponent boxComponent) {
        final Point posicioCaret = this.PosicioCaret(n, boxComponent);
        return new Rectangle(posicioCaret.x, posicioCaret.y, this.PosicioCaret(n2, boxComponent).x - posicioCaret.x, super.height);
    }
    
    public final AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new TextSelection(this, stack, stack2, b);
    }
    
    public final AbstractSelection SeleccionaDreta(final Stack stack) {
        final Stack<Integer> stack2 = new Stack<Integer>();
        stack2.push(new Integer(this.PosSeg_Fill()));
        return new TextSelection(this, stack, stack2, false);
    }
    
    public final AbstractSelection SeleccionaEsquerra(final Stack stack) {
        final Stack<Integer> stack2 = new Stack<Integer>();
        stack2.push(new Integer(0));
        return new TextSelection(this, stack2, stack, true);
    }
    
    public final boolean joins(final TextBox textBox) {
        return (this.type == 1 && textBox.type == 1) || (this.type == 1 && textBox.type == 2) || (this.type == 2 && textBox.type == 2) || (this.type == 0 && textBox.type == 0) || (this.type == 5 && textBox.type == 5);
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            boxScripting.appendText(Utils.netejaCadenaOTeclat(this.cadena));
        }
        else if (boxScripting.scriptMode == 2) {
            if (super.cadena_omega != null && super.cadena_omega.length() != 0) {
                boxScripting.appendText(super.cadena_omega, this.type);
            }
            boxScripting.appendText(this.cadena, this.type);
        }
        else {
            boxScripting.appendText(this.cadena);
        }
    }
    
    public final void onScript(final BoxScripting boxScripting, final int n, final int n2) {
        if (boxScripting.scriptMode == 1) {
            final int endNumber = endNumber(this.PosicioCaret(n, n2));
            if (endNumber > 0 && (this.type == 1 || this.type == 2)) {
                final String posicioCaret = this.PosicioCaret(n, n + endNumber);
                boxScripting.beginBox(this, "mn");
                boxScripting.appendText(posicioCaret);
                boxScripting.endBox(this, "mn");
                final String posicioCaret2 = this.PosicioCaret(n + endNumber, n2);
                if (posicioCaret2.length() > 0) {
                    boxScripting.beginBox(this, "mi");
                    boxScripting.appendText(posicioCaret2);
                    boxScripting.endBox(this, "mi");
                }
                return;
            }
        }
        if (boxScripting.scriptMode == 0) {
            boxScripting.appendText(Utils.netejaCadenaOTeclat(this.PosicioCaret(n, n2)));
        }
        else {
            boxScripting.appendText(this.PosicioCaret(n, n2));
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting, final int n, final int n2) {
        if (boxScripting.scriptMode == 1 && ((this.type == 1 && endNumber(this.PosicioCaret(n, n2)) > 0) || n2 - n <= 0)) {
            return null;
        }
        return this.scriptCommand(boxScripting);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (this.isStyle(128)) {
            if (this.hasAttributes() || !Attributes.iguals(this, this.getParentBox())) {
                return "span";
            }
            return null;
        }
        else {
            if (boxScripting.scriptMode == 1) {
                return TextBox.tag[this.type];
            }
            return super.scriptCommand(boxScripting);
        }
    }
    
    public static final boolean isNumber(final String s) {
        return endNumber(s) == s.length();
    }
    
    public static final int endNumber(final String s) {
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                return i;
            }
        }
        return length;
    }
    
    public final int getFactorEscalaAbsolut(final BoxComponent boxComponent) {
        if (this.PosAnt_Fill(boxComponent)) {
            return 225;
        }
        if (this.isBigOp(boxComponent)) {
            return 150;
        }
        return 100;
    }
    
    private final boolean PosAnt_Fill(final BoxComponent boxComponent) {
        return this.isDisplayStyle(boxComponent) && this.is(false, "\u222b\u222c\u222d\u222e\u222f\u2230");
    }
    
    public final boolean isBigOp(final BoxComponent boxComponent) {
        return this.is(this.PosAnt_Fill, "\u2211\u220f") || (!this.isDisplayStyle(boxComponent) && this.is(false, "\u222b\u222c\u222d\u222e\u222f\u2230"));
    }
    
    public final boolean isStretchy() {
        return this.is(this.PosicioCaret, "\ufe37\ufe38\u23b4\u23b5\ufe35\ufe36^\u02c7\u0302\u2190\u2192\u21c0\u2194¯\u0304\u0332");
    }
    
    private boolean is(final boolean b, final String s) {
        return b || (this.PosSeg_Fill() == 1 && s.indexOf("" + this.cadena.charAt(0)) >= 0);
    }
    
    public final boolean hasProperties() {
        return true;
    }
    
    public final void onUpdateAttributes(final BoxComponent boxComponent) {
        super.onUpdateAttributes(boxComponent);
        if (this.isStyle(128)) {
            this.setType(0);
        }
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        if (this.PosAnt_Fill) {
            boxScripting.appendAttribute("largeop", String.valueOf(this.PosAnt_Fill));
        }
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        this.PosAnt_Fill = Attributes.exchangeBool(hashtable, "largeop", n, this.PosAnt_Fill, false);
        this.PosSeg_Fill = Attributes.exchangeBool(hashtable, "accent", n, this.PosSeg_Fill, false);
        this.PosicioCaret = Attributes.exchangeBool(hashtable, "stretchy", n, this.PosicioCaret, false);
        if ((n == 0 || n == 4097) && this.PosAnt_Fill) {
            final boolean b = false;
            this.append = (b ? 1 : 0);
            this.PosicioReal = (b ? 1 : 0);
        }
    }
    
    public final boolean hasBeginEndPositions() {
        return this.PosicioReal == 0 || this.append == 0;
    }
    
    public final void setText(final BoxComponent boxComponent, final String cadena) {
        if (this.type != 0) {
            this.cadena = cadena.trim();
        }
        else {
            this.cadena = cadena;
        }
        if (boxComponent != null) {
            this.enCalculRect(boxComponent);
        }
        if (this.cadena.equals("\u2146")) {
            super.color = 0;
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 800;
    }
    
    public final BoxPosition PosSeg2(int x, final AbstractBox[] array, final BoxPosition boxPosition) {
        AbstractBox parentBox;
        if (x >= this.PosSeg_Fill() - this.append) {
            if (this.getParentBox() == null) {
                return null;
            }
            parentBox = this.getParentBox();
            x = super.p_en_pare + 1;
        }
        else {
            parentBox = this;
            ++x;
        }
        if (boxPosition == null) {
            return new BoxPosition(parentBox, x);
        }
        boxPosition.c = parentBox;
        boxPosition.x = x;
        return boxPosition;
    }
    
    public final BoxPosition PosAnt2(int p_en_pare, final AbstractBox[] array, final BoxPosition boxPosition) {
        AbstractBox parentBox;
        if (p_en_pare == this.PosicioReal) {
            if (this.getParentBox() == null) {
                return null;
            }
            parentBox = this.getParentBox();
            p_en_pare = super.p_en_pare;
        }
        else {
            parentBox = this;
            --p_en_pare;
        }
        if (boxPosition == null) {
            return new BoxPosition(parentBox, p_en_pare);
        }
        boxPosition.c = parentBox;
        boxPosition.x = p_en_pare;
        return boxPosition;
    }
    
    public final boolean isValid(final int n) {
        return n >= this.PosicioReal && n <= this.PosSeg_Fill() - this.append;
    }
    
    public final AbstractBox getAccentContainer() {
        AbstractBox abstractBox = this.getParentBox();
        if (abstractBox != null && abstractBox.nfills == 1) {
            if (abstractBox instanceof TokensBox) {
                abstractBox = abstractBox.getParentBox();
            }
            if (abstractBox != null && abstractBox.isAccentContainer()) {
                return abstractBox;
            }
        }
        return null;
    }
    
    final int PosSeg_Fill() {
        return this.cadena.length();
    }
    
    final String PosicioCaret(final int n, final int n2) {
        return this.cadena.substring(n, n2);
    }
    
    public final int[] getVerticalMetrics(final BoxComponent boxComponent) {
        if (!(boxComponent instanceof Formula)) {
            return null;
        }
        if (this.appendText == null) {
            return ((Formula)boxComponent).getFontUtils().computeMetrics(boxComponent, super.I, this.cadena);
        }
        return this.appendText;
    }
    
    static {
        TextBox.tcolor = new int[] { 0, 0, 2, 3, 4, 4 };
        TextBox.tag = new String[] { "mtext", "mi", "mn", "mo", "ms", "mtext" };
        beginBox = new String[][] { { "xwrckt", "times new roman", "0.0", "0.05", "1" } };
    }
}
