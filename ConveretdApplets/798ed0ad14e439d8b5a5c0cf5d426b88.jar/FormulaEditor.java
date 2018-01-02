import java.awt.MenuItem;
import java.beans.PropertyChangeListener;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Component;
import java.beans.PropertyChangeSupport;
import java.awt.PopupMenu;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaEditor extends Formula implements KeyListener, ActionListener
{
    protected boolean AfegeixColumna;
    protected WCaret K;
    public final AppInterface context;
    protected static FormulaEditor L;
    protected String M;
    protected Stack N;
    protected Stack AfegeixFila;
    public AbstractBox boxToDoProperties;
    protected PopupMenu Afegir;
    public boolean editStatic;
    public boolean enableEditAccelerator;
    protected Attributes AfegirCapsaPredeterminada;
    protected BoxPosition AfegirNoCalc;
    protected PropertyChangeSupport Delete;
    static Class EXPAND;
    static Class EliminaColumnes;
    static Class EliminaFiles;
    
    public FormulaEditor(final String s, final ScrollPaneWrapper scrollPaneWrapper, final AppInterface appInterface, final Component component) {
        super(scrollPaneWrapper, component);
        this.M = "";
        this.N = new Stack();
        this.AfegeixFila = new Stack();
        this.editStatic = false;
        this.enableEditAccelerator = true;
        this.Delete = new PropertyChangeSupport(this);
        this.context = appInterface;
        try {
            this.Afegir = new PopupMenu();
        }
        catch (HeadlessException ex) {
            this.Afegir = null;
        }
        if (appInterface != null) {
            this.setResourceProvider(appInterface.getResourceProvider());
            if (OmegaSystem.context == null) {
                OmegaSystem.context = appInterface;
            }
        }
        this.setCursor(new Cursor(2));
        if (super.H != null) {
            if (this.Afegir != null) {
                super.H.add(this.Afegir);
            }
            super.H.addKeyListener(this);
            (this.K = new WCaret(super.H)).setVisible(false);
            this.addPaintListener(this.K);
        }
        if (s != null) {
            this.setString(s, true);
        }
        else {
            super.lacapsa = null;
        }
    }
    
    public void inicialitza() {
        this.notifyChangePosition(null, null);
        this.AfegirCapsaPredeterminada = null;
    }
    
    public final void finalitza() {
        this.K.stop();
        super.finalitza();
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
        this.F();
        super.focusGained(focusEvent);
        if (this.D()) {
            this.K.setVisible(true);
        }
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        super.focusLost(focusEvent);
        this.K.setVisible(false);
    }
    
    public final void resetPosition() {
        if (super.curpos != null && super.lacapsa.esSubcapsa(super.curpos.c)) {
            super.resetPosition();
        }
        else {
            final Rectangle bounds = this.K.getBounds();
            final Point location = bounds.getLocation();
            location.y += bounds.height / 2;
            this.setCaret(location);
        }
    }
    
    public void setCaret(final BoxPosition caret) {
        super.I = null;
        super.setCaret(caret);
    }
    
    public final void setCaretCoordinates(final int[] array) {
        super.H.requestFocus();
        final BoxScripting boxScripting = new BoxScripting(1);
        boxScripting.findPosition(array);
        super.lacapsa.script(boxScripting);
        this.setCaret(boxScripting.getPosition());
    }
    
    public final int[] getCaretCoordinates() {
        final BoxScripting boxScripting = new BoxScripting(1, "", "");
        boxScripting.findCoordinates(super.curpos);
        super.lacapsa.script(boxScripting);
        boxScripting.toString();
        return boxScripting.getCoordinates();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.I(mouseEvent)) {
            return;
        }
        if (this.I(mouseEvent)) {
            this.setCaret(this.I(mouseEvent.getPoint()));
            this.EliminaFiles(this.I(mouseEvent.getPoint()));
            return;
        }
        super.mousePressed(mouseEvent);
        if (!this.isEditable()) {
            return;
        }
        this.requestFocus();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.I(mouseEvent)) {
            this.EliminaFiles(this.I(mouseEvent.getPoint()));
            super.E = 0;
            return;
        }
        if (OmegaSystem.versio_java == 0) {
            if (super.E == 131072 && this.isEditable()) {
                final Point point = new Point(this.I(mouseEvent.getPoint()));
                point.translate(-super.tl.x, -super.tl.y);
                if (!super.B.getRectangles(this).contains(point)) {
                    this.unselectArea();
                    this.InsertString(Formula.A);
                    this.repaint();
                }
                else if (Utils.equals(super.curpos, super.Z)) {
                    this.unselectArea();
                }
                this.setCursor(Formula.TEXT_CURSOR);
            }
        }
        else {
            if (super.E != 65536 && super.B != null) {
                this.unselectArea();
            }
            this.setCursor(Formula.DEFAULT_CURSOR);
        }
        super.mouseReleased(mouseEvent);
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.isConsumed()) {
            return;
        }
        int n = 0;
        try {
            if (super.curpos != null) {
                for (AbstractBox abstractBox = super.curpos.c; abstractBox != null; abstractBox = abstractBox.getParentBox()) {
                    if (abstractBox.onKeyPressed(keyEvent, this)) {
                        n = 1;
                    }
                }
            }
            if (n == 0) {
                if (!this.AfegeixColumna(keyEvent)) {
                    if ((this.isEditable() || this.Composa()) && this.I(keyEvent)) {
                        n = 1;
                    }
                }
                else {
                    n = 1;
                }
            }
            if (n != 0) {
                keyEvent.consume();
            }
            super.J.update();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected final boolean AfegeixColumna(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        BoxPosition i = null;
        if (keyEvent.isControlDown() && !keyEvent.isAltDown()) {
            if (keyEvent.isShiftDown()) {
                switch (keyCode) {
                    case 88: {
                        this.ShowXML();
                        return true;
                    }
                    case 66: {
                        this.ShowBoxes();
                        return true;
                    }
                    case 68: {
                        this.ProcessaOrdre("design");
                        return true;
                    }
                    case 67: {
                        this.ProcessaOrdre("copytext");
                        return true;
                    }
                }
            }
            else {
                switch (keyCode) {
                    case 67:
                    case 155: {
                        this.ProcessaOrdre("copy");
                        return true;
                    }
                    case 77: {
                        this.ProcessaOrdre("copymoodle");
                        return true;
                    }
                }
            }
        }
        if (keyCode >= 33 && keyCode <= 40) {
            BoxPosition caret = null;
            boolean b;
            if (!keyEvent.isShiftDown()) {
                this.unselectArea();
                b = false;
            }
            else {
                b = true;
                if (super.B == null) {
                    super.Z = new BoxPosition(super.curpos);
                    super.C = new BoxPosition(super.curpos);
                }
            }
            if (keyEvent.isControlDown()) {
                switch (keyCode) {
                    case 37: {
                        caret = Formula.I(super.curpos);
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    case 39: {
                        caret = Formula.Z(super.curpos);
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    case 35: {
                        caret = goLeft(new BoxPosition(super.lacapsa, super.lacapsa.nfills), true);
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    case 36: {
                        caret = goLeft(new BoxPosition(super.lacapsa, 0), false);
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
            else {
                switch (keyCode) {
                    case 35: {
                        caret = this.Afegir();
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    case 36: {
                        caret = this.AfegirCapsaPredeterminada();
                        if (b) {
                            super.C = new BoxPosition(caret);
                            break;
                        }
                        break;
                    }
                    case 37: {
                        caret = goLeft(super.curpos, true);
                        if (b) {
                            super.C = this.goLeftSelection(super.C, super.Z, true);
                            break;
                        }
                        break;
                    }
                    case 39: {
                        caret = goLeft(super.curpos, false);
                        if (b) {
                            super.C = this.goLeftSelection(super.C, super.Z, false);
                            break;
                        }
                        break;
                    }
                    case 38: {
                        i = super.curpos;
                        if (super.I != null) {
                            i = super.I;
                        }
                        caret = this.goUp(super.curpos, i, true);
                        if (b) {
                            super.C = this.goUpSelection(super.C, super.Z, i, true);
                            break;
                        }
                        break;
                    }
                    case 40: {
                        i = super.curpos;
                        if (super.I != null) {
                            i = super.I;
                        }
                        caret = this.goUp(super.curpos, i, false);
                        if (b) {
                            super.C = this.goUpSelection(super.C, super.Z, i, false);
                            break;
                        }
                        break;
                    }
                    case 33: {
                        if (super.I == null) {
                            i = super.curpos;
                        }
                        else {
                            i = super.I;
                        }
                        caret = this.movePageUp(super.curpos, i, true);
                        if (keyEvent.isShiftDown()) {
                            super.C = this.movePageUp(super.C, i, true);
                            break;
                        }
                        break;
                    }
                    case 34: {
                        if (super.I == null) {
                            i = super.curpos;
                        }
                        else {
                            i = super.I;
                        }
                        caret = this.movePageUp(super.curpos, i, false);
                        if (keyEvent.isShiftDown()) {
                            super.C = this.movePageUp(super.C, i, false);
                            break;
                        }
                        break;
                    }
                }
            }
            if (b) {
                final AbstractSelection b2 = super.B;
                super.B = AbstractSelection.Selecciona(super.Z, super.C, this);
                super.B = this.I(super.B, b2);
                if (super.B == null || super.B.buida()) {
                    this.unselectArea();
                }
                else {
                    super.C = super.B.getSelpos();
                    caret = super.B.getCurpos();
                }
            }
            if (this.I(caret, false)) {
                this.setCaret(caret);
                super.I = i;
                return true;
            }
        }
        return false;
    }
    
    protected boolean I(final KeyEvent keyEvent) {
        if (super.curpos == null) {
            return false;
        }
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.isControlDown() && !keyEvent.isAltDown() && !keyEvent.isShiftDown()) {
            switch (keyCode) {
                case 86: {
                    if (this.enableEditAccelerator) {
                        this.ProcessaOrdre("paste");
                    }
                    return true;
                }
                case 88: {
                    if (this.enableEditAccelerator) {
                        this.ProcessaOrdre("cut");
                    }
                    return true;
                }
                case 84: {
                    this.ProcessaOrdre("comment");
                    return true;
                }
                case 90: {
                    if (this.enableEditAccelerator) {
                        this.undo();
                    }
                    return true;
                }
                case 89: {
                    if (this.enableEditAccelerator) {
                        this.redo();
                    }
                    return true;
                }
                case 115: {
                    if (super.B != null && super.B instanceof TokensSelection) {
                        final AbstractBox contents = super.B.getContents();
                        if (contents != null && contents.hasProperties()) {
                            this.boxToDoProperties = contents;
                            this.ProcessaOrdre("properties");
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        if (!keyEvent.isControlDown() && !keyEvent.isAltDown()) {
            switch (keyCode) {
                case 155: {
                    if (keyEvent.isShiftDown()) {
                        this.ProcessaOrdre("paste");
                        return true;
                    }
                    return false;
                }
                case 127: {
                    if (keyEvent.isShiftDown()) {
                        this.ProcessaOrdre("cut");
                    }
                    else {
                        this.Suprimir();
                        this.repaint();
                    }
                    return true;
                }
                case 8: {
                    this.Delete();
                    this.repaint();
                    return true;
                }
                case 10: {
                    if (keyEvent.isShiftDown()) {
                        this.B();
                    }
                    else {
                        this.C();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.isConsumed()) {
            return;
        }
        try {
            this.keyTyped2(keyEvent);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void keyTyped2(final KeyEvent keyEvent) {
        if (!this.isEditable() && !this.Composa()) {
            return;
        }
        if (keyEvent.getKeyChar() == '\u007f') {
            return;
        }
        if (keyEvent.isControlDown()) {
            return;
        }
        if (!OmegaSystem.macOS && keyEvent.isAltDown()) {
            return;
        }
        if (OmegaSystem.macOS && keyEvent.isMetaDown()) {
            return;
        }
        if (!this.D()) {
            return;
        }
        final String char2script = this.char2script(keyEvent.getKeyChar());
        if (char2script.length() > 0) {
            this.InsertString(char2script);
            this.repaint();
        }
    }
    
    public final String char2script(final char c) {
        String s;
        if (super.curpos != null && super.curpos.c.isStyle(128)) {
            s = MathML2Box.char2Html(c);
        }
        else {
            s = MathML2Box.char2MathML(c, null);
        }
        return s;
    }
    
    public final BoxPosition moveSelection(final BoxPosition boxPosition, final BoxPosition boxPosition2, final BoxPosition boxPosition3, final boolean b, final boolean b2) {
        final AbstractSelection selecciona = AbstractSelection.Selecciona(boxPosition2, boxPosition, this);
        BoxPosition boxPosition4 = boxPosition;
        boolean b3;
        do {
            b3 = false;
            final BoxPosition boxPosition5 = boxPosition4;
            if (b) {
                boxPosition4 = goLeft(boxPosition5, b2, true);
            }
            else {
                boxPosition4 = this.goUp(boxPosition5, boxPosition3, b2, true);
            }
            if (boxPosition4 == null || boxPosition4.equals(boxPosition5)) {
                return boxPosition4;
            }
            final AbstractSelection selecciona2 = AbstractSelection.Selecciona(boxPosition2, boxPosition4, this);
            if (selecciona == null && selecciona2 == null) {
                b3 = true;
            }
            if (selecciona == null || selecciona2 == null) {
                continue;
            }
            final BoxScripting boxScripting = this.getBoxScripting();
            final BoxScripting boxScripting2 = this.getBoxScripting();
            selecciona.script(boxScripting);
            selecciona2.script(boxScripting2);
            if (!boxScripting.toString().equals(boxScripting2.toString())) {
                continue;
            }
            b3 = true;
        } while (b3);
        return boxPosition4;
    }
    
    public final BoxPosition goLeftSelection(final BoxPosition boxPosition, final BoxPosition boxPosition2, final boolean b) {
        return this.moveSelection(boxPosition, boxPosition2, null, true, b);
    }
    
    public static final BoxPosition goLeft(final BoxPosition boxPosition, final boolean b) {
        return goLeft(boxPosition, b, false);
    }
    
    private static BoxPosition goLeft(final BoxPosition boxPosition, final boolean b, final boolean b2) {
        BoxPosition boxPosition2 = new BoxPosition(boxPosition);
        boolean b3 = false;
        do {
            if (b) {
                boxPosition2 = boxPosition2.c.PosAnt2(boxPosition2.x, null, boxPosition2);
            }
            else {
                boxPosition2 = boxPosition2.c.PosSeg2(boxPosition2.x, null, boxPosition2);
            }
            if (boxPosition2 == null) {
                b3 = true;
            }
            else if (!b2) {
                if (!boxPosition2.c.isValid(boxPosition2.x)) {
                    continue;
                }
                if (!boxPosition2.c.isEditable(boxPosition2.x)) {
                    if (!boxPosition2.c._cc[0].isDesign()) {
                        boxPosition2 = null;
                    }
                    b3 = true;
                }
                else {
                    if (boxPosition2.c.isSymbol(boxPosition2.x)) {
                        continue;
                    }
                    b3 = true;
                }
            }
            else {
                if (!boxPosition2.c.isSelectable(boxPosition2.x)) {
                    continue;
                }
                if (!boxPosition2.c.isEditable(boxPosition2.x)) {
                    if (!boxPosition2.c._cc[0].isDesign()) {
                        boxPosition2 = null;
                    }
                    b3 = true;
                }
                else {
                    if (boxPosition2.c.isSymbol(boxPosition2.x)) {
                        continue;
                    }
                    b3 = true;
                }
            }
        } while (!b3);
        if (boxPosition2 == null) {
            return boxPosition;
        }
        return boxPosition2;
    }
    
    public final BoxPosition goUpSelection(final BoxPosition boxPosition, final BoxPosition boxPosition2, final BoxPosition boxPosition3, final boolean b) {
        return this.moveSelection(boxPosition, boxPosition2, boxPosition3, false, b);
    }
    
    public final BoxPosition goUp(final BoxPosition boxPosition, final BoxPosition boxPosition2, final boolean b) {
        return this.goUp(boxPosition, boxPosition2, b, false);
    }
    
    private BoxPosition goUp(final BoxPosition boxPosition, final BoxPosition boxPosition2, final boolean b, final boolean b2) {
        final Point point = new Point(boxPosition2.c.PosicioCaret(boxPosition2.x, this).x, boxPosition.c.PosicioReal().y + boxPosition.c.getPositionBaseline(boxPosition.x));
        BoxPosition copy = null;
        Point point2 = null;
        int n = 0;
        BoxPosition posSeg2 = new BoxPosition(super.lacapsa, 0);
        do {
            if (this.I(posSeg2, b2)) {
                final Point point3 = new Point(posSeg2.c.PosicioCaret(posSeg2.x, this).x, posSeg2.c.PosicioReal().y + posSeg2.c.getPositionBaseline(posSeg2.x));
                if ((b && point3.y < point.y) || (!b && point3.y > point.y)) {
                    final int depth = BoxPosition.commonAncestor(boxPosition, posSeg2, null, null).getDepth();
                    if (copy == null || depth > n || (depth == n && ((b && point3.y > point2.y) || (!b && point3.y < point2.y) || (point3.y == point2.y && Math.abs(point3.x - point.x) < Math.abs(point2.x - point.x))))) {
                        point2 = point3;
                        copy = posSeg2.copy(copy);
                        n = depth;
                    }
                }
            }
            posSeg2 = posSeg2.c.PosSeg2(posSeg2.x, null, posSeg2);
        } while (posSeg2 != null);
        if (copy == null) {
            return boxPosition;
        }
        return copy;
    }
    
    public final void InsertString(final String s) {
        String script = null;
        boolean b = false;
        if (!this.D() && !this.Composa()) {
            return;
        }
        if (super.B != null) {
            if (super.B.espotsubstituir()) {
                script = this.script(super.B);
                this.suprSelection();
                b = true;
            }
            else {
                this.Suprimir(true);
                b = true;
            }
        }
        this.unselectArea();
        if (s == null || s.length() == 0) {
            return;
        }
        final BoxPosition insertString = this.InsertString(this, super.curpos, s, script);
        if (b) {
            I(this.N);
        }
        this.setCaret(insertString);
    }
    
    public final Attributes getInsertStringFormat() {
        final Attributes obteAtributs = Attributes.obteAtributs(this, super.curpos);
        if (this.AfegirCapsaPredeterminada != null) {
            obteAtributs.join(this.AfegirCapsaPredeterminada);
        }
        return obteAtributs;
    }
    
    public final BoxPosition movePageUp(final BoxPosition boxPosition, final BoxPosition boxPosition2, final boolean b) {
        BoxPosition goUp = boxPosition;
        int n;
        if (super.G != null) {
            n = super.G.getViewportSize().height;
        }
        else {
            n = this.getComponent().getSize().height;
        }
        final int y = boxPosition.c.PosicioCaret(boxPosition.x, this).y;
        int y2;
        BoxPosition boxPosition3;
        do {
            boxPosition3 = goUp;
            goUp = this.goUp(goUp, boxPosition2, b);
            y2 = goUp.c.PosicioCaret(goUp.x, this).y;
        } while (((b && y - y2 < n) || (!b && y2 - y < n)) && !goUp.equals(boxPosition3));
        return goUp;
    }
    
    private final BoxPosition InsertString(final Formula formula, final BoxPosition boxPosition, final String s, final String s2) {
        final boolean b = s2 != null && s2.length() > 0 && s.indexOf("caret") >= 0;
        final AbstractBox parse = formula.parse(s);
        parse.posaAtribut(formula, this.getInsertStringFormat(), false, true);
        AbstractBox parse2;
        if (b) {
            parse2 = formula.parse(s2);
        }
        else {
            parse2 = null;
        }
        return this.InsertString(formula, boxPosition, parse, parse2);
    }
    
    private final BoxPosition InsertString(final Formula formula, final BoxPosition boxPosition, final AbstractBox abstractBox, final AbstractBox abstractBox2) {
        BoxPosition boxPosition2 = this.InsertString(formula, boxPosition, abstractBox);
        if (abstractBox2 != null) {
            boxPosition2 = this.InsertString(formula, boxPosition2, abstractBox2);
            I(this.N);
        }
        return boxPosition2;
    }
    
    public BoxPosition InsertString(final Formula formula, BoxPosition boxPosition, AbstractBox abstractBox) {
        if (abstractBox instanceof MultilineBox && abstractBox.nfills == 1 && abstractBox.fill[0] instanceof TokensBox) {
            abstractBox = abstractBox.fill[0];
        }
        if (abstractBox instanceof MultilineBox) {
            boolean b = false;
            if (boxPosition.c instanceof EmptyBox && boxPosition.c.getParentBox() != null && boxPosition.c.getParentBox() instanceof MultilineBox) {
                b = true;
            }
            else {
                boxPosition = I(formula, boxPosition);
            }
            final BoxPosition buscaPosicioTipus = boxPosition.BuscaPosicioTipus((FormulaEditor.EXPAND == null) ? (FormulaEditor.EXPAND = I("MultilineBox")) : FormulaEditor.EXPAND);
            final BoxPosition andRemoveMarkup = abstractBox.findAndRemoveMarkup(formula, "\\caret");
            if (andRemoveMarkup != null) {
                boxPosition = andRemoveMarkup;
            }
            push_backup(formula, boxPosition, buscaPosicioTipus.c);
            final int nfills = abstractBox.nfills;
            while (abstractBox.nfills > 0) {
                buscaPosicioTipus.c.Afegir(abstractBox.TreureNoCalc(), buscaPosicioTipus.x, formula);
            }
            final MultilineBox multilineBox = (MultilineBox)buscaPosicioTipus.c;
            if (buscaPosicioTipus.x > 0 && !b) {
                final BoxPosition boxPosition2 = buscaPosicioTipus;
                --boxPosition2.x;
                multilineBox.UneixLinees(buscaPosicioTipus.x, this);
            }
            boxPosition = multilineBox.UneixLinees(buscaPosicioTipus.x + nfills - 1, this);
            return boxPosition;
        }
        if (boxPosition.c instanceof TextBox) {
            final TextBox textBox = (TextBox)boxPosition.c;
            if (textBox.hasBeginEndPositions()) {
                if (abstractBox instanceof TokensBox && abstractBox.nfills == 1 && (abstractBox.fill[0] instanceof TextBox || abstractBox.fill[0] instanceof SpaceBox)) {
                    abstractBox = abstractBox.fill[0];
                }
                if (abstractBox instanceof SpaceBox) {
                    abstractBox = new TextBox(" ", 0);
                }
                if (!(abstractBox instanceof TextBox)) {
                    return boxPosition;
                }
                final TextBox textBox2 = (TextBox)abstractBox;
                textBox.cadena = textBox.cadena.substring(0, boxPosition.x) + textBox2.cadena + textBox.cadena.substring(boxPosition.x);
                textBox.update(formula);
                if (!Formula.LEFT_TO_RIGHT) {
                    return new BoxPosition(textBox, boxPosition.x + textBox2.cadena.length());
                }
                return new BoxPosition(textBox, boxPosition.x);
            }
        }
        if (boxPosition.c instanceof TokensBox) {
            final AbstractBox c = boxPosition.c;
            final int x = boxPosition.x;
            push_backup(formula, boxPosition, boxPosition.c);
            final Object expand = EXPAND(formula, boxPosition, c);
            final BoxPosition insertAndFindCaret = ((TokensBox)c).InsertAndFindCaret(formula, abstractBox, x);
            if (!DEFAULT_CURSOR(formula, c)) {
                return Delete(c, formula, expand, null, null);
            }
            return insertAndFindCaret;
        }
        else if (boxPosition.c instanceof EmptyBox) {
            final AbstractBox parentBox = boxPosition.c.getParentBox();
            final int p_en_pare = boxPosition.c.p_en_pare;
            push_backup(formula, boxPosition, parentBox);
            if (!(abstractBox instanceof TokensBox)) {
                final AbstractBox abstractBox2 = new TokensBox();
                abstractBox2.inicialitzaFills(abstractBox);
                abstractBox = abstractBox2;
            }
            final Object expand2 = EXPAND(formula, boxPosition, parentBox);
            parentBox.SubstitueixCapsa(abstractBox, p_en_pare, formula);
            final BoxPosition andRemoveMarkup2 = parentBox.findAndRemoveMarkup(formula, "\\caret");
            if (!DEFAULT_CURSOR(formula, parentBox)) {
                return Delete(parentBox, formula, expand2, null, null);
            }
            if (andRemoveMarkup2 != null) {
                return andRemoveMarkup2;
            }
            if (!Formula.LEFT_TO_RIGHT) {
                return abstractBox.PosAnt_Fora(null);
            }
            return abstractBox.PosSeg_Fora(null);
        }
        else {
            if (!(boxPosition.c.getParentBox() instanceof TokensBox)) {
                return null;
            }
            final TokensBox tokensBox = (TokensBox)boxPosition.c.getParentBox();
            push_backup(formula, boxPosition, tokensBox);
            final Object expand3 = EXPAND(formula, boxPosition, tokensBox);
            final BoxPosition insertAndFindCaret2 = tokensBox.InsertAndFindCaret(formula, abstractBox, AfegeixFila(formula, boxPosition).x);
            if (!DEFAULT_CURSOR(formula, tokensBox)) {
                return Delete(tokensBox, formula, expand3, null, null);
            }
            return insertAndFindCaret2;
        }
    }
    
    public final void insertString(final String s) {
        this.InsertString(s);
        this.repaint();
        this.requestFocus();
    }
    
    public AbstractBox trimMainBox(final AbstractBox abstractBox) {
        if (!(abstractBox instanceof MultilineBox)) {
            final MultilineBox multilineBox = new MultilineBox();
            multilineBox.AfegirNoCalc(abstractBox);
            multilineBox.setAttributes(abstractBox.getAttributes());
            return multilineBox;
        }
        return abstractBox;
    }
    
    private static final BoxPosition AfegeixFila(final BoxComponent boxComponent, final BoxPosition boxPosition) {
        final TokensBox tokensBox = (TokensBox)boxPosition.c.getParentBox();
        if (boxPosition.c instanceof TextBox) {
            final BoxPosition boxPosition2 = new BoxPosition(tokensBox, boxPosition.c.p_en_pare + 1);
            final String right = BoxScripting.right(boxComponent, boxPosition.c, boxPosition.x);
            final TextBox textBox = (TextBox)boxPosition.c;
            textBox.setText(boxComponent, textBox.cadena.substring(0, boxPosition.x));
            tokensBox.InserirCapsa(boxComponent, boxComponent.parse(right), boxPosition2.x);
            return boxPosition2;
        }
        final BoxPosition boxPosition3 = new BoxPosition(tokensBox, boxPosition.c.p_en_pare);
        tokensBox.Treure(boxPosition.c.p_en_pare, boxComponent);
        return boxPosition3;
    }
    
    protected void C() {
        this.B();
    }
    
    protected void B() {
        this.setCaret(I(this, super.curpos));
        this.repaint();
    }
    
    protected static final BoxPosition I(final Formula formula, final BoxPosition boxPosition) {
        final BoxPosition buscaPosicioTipus = boxPosition.BuscaPosicioTipus((FormulaEditor.EliminaColumnes == null) ? (FormulaEditor.EliminaColumnes = I("TokensVBox")) : FormulaEditor.EliminaColumnes);
        push_backup(formula, boxPosition, buscaPosicioTipus.c);
        if (buscaPosicioTipus.c instanceof MultilineBox) {
            final MultilineBox multilineBox = (MultilineBox)buscaPosicioTipus.c;
            final AbstractSelection selecciona = AbstractSelection.Selecciona(boxPosition, multilineBox.PosAnt_Fill(buscaPosicioTipus.x + 1), formula);
            if (selecciona != null) {
                final AbstractBox parse = formula.parse(formula.script(selecciona));
                selecciona.suprimeix(formula);
                multilineBox.Afegir(parse, buscaPosicioTipus.x + 1, formula);
            }
            else {
                multilineBox.AfegirCapsaPredeterminada(buscaPosicioTipus.x + 1, formula);
            }
            return multilineBox.PosSeg_Fill(buscaPosicioTipus.x);
        }
        if (buscaPosicioTipus.c instanceof TokensVBox) {
            final BoxPosition boxPosition2 = new BoxPosition(boxPosition);
            boxPosition2.posicioPare();
            int x;
            if (boxPosition2.equals(buscaPosicioTipus) && boxPosition.x == 0) {
                x = buscaPosicioTipus.x;
            }
            else {
                x = buscaPosicioTipus.x + 1;
            }
            ((TokensVBox)buscaPosicioTipus.c).AfegirCapsaPredeterminada(x, formula);
            return buscaPosicioTipus.c.PosSeg_Fill(buscaPosicioTipus.x);
        }
        return boxPosition;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.ProcessaOrdre(actionEvent.getActionCommand())) {
            this.F();
            if (this.context != null) {
                this.context.actionPerformed(actionEvent);
            }
        }
    }
    
    public final void Delete() {
        this.Suprimir(false);
    }
    
    public final void Suprimir() {
        this.Suprimir(true);
    }
    
    private void Suprimir(final boolean b) {
        if (super.B != null) {
            if (super.B.excludeSel == null) {
                this.suprSelection();
                this.changeNotification();
                this.repaint();
            }
            else {
                String s = this.script(super.B.excludeSel);
                if (super.B.excludeSel.deldret) {
                    s = "<mo>caret</mo>" + s;
                }
                this.suprSelection();
                this.InsertString(s);
            }
        }
        else {
            super.Z = new BoxPosition(super.curpos);
            super.C = this.goLeftSelection(super.curpos, super.Z, !b);
            if (!this.isEditablePosition(super.C)) {
                return;
            }
            if (super.C.c != super.curpos.c && super.C.c instanceof TokensBox && super.curpos.c instanceof TokensBox && (super.C.c.getParentBox() == super.curpos.c || super.C.c == super.curpos.c.getParentBox())) {
                super.C = this.goLeftSelection(super.C, super.Z, !b);
            }
            if (super.C.c instanceof TokensVBox && super.C.c.nfills == 1 && super.curpos.c instanceof EmptyBox) {
                super.C = this.goLeftSelection(super.C, super.Z, !b);
            }
            super.B = AbstractSelection.Selecciona(super.C, super.curpos, this);
            if (super.B == null) {
                return;
            }
            if (super.B.espotborrardirectament(this)) {
                this.suprSelection();
                this.changeNotification();
            }
            else {
                final AbstractBox contents = super.B.getContents();
                if (contents != null && super.curpos.c instanceof TokensBox && contents == super.curpos.c.getParentBox()) {
                    if (b) {
                        super.B.excludeSel = AbstractSelection.Selecciona(super.curpos, super.curpos.c.PosSeg_Fora(), this);
                    }
                    else {
                        super.B.excludeSel = AbstractSelection.Selecciona(super.curpos, super.curpos.c.PosAnt_Fora(), this);
                    }
                }
                else {
                    this.setCaret(super.B.getCurpos());
                }
            }
        }
    }
    
    public boolean espotborrardirectament(final AbstractBox abstractBox) {
        return abstractBox instanceof TextBox || abstractBox instanceof MarkupBox || abstractBox instanceof SpaceBox;
    }
    
    public final void cut() {
        if (OmegaSystem.versio_java >= 1) {
            this.getFormulaTransfer().cut();
        }
        else {
            this.copy();
            if (this.isEditable()) {
                this.Suprimir();
            }
        }
    }
    
    public final void copy() {
        if (OmegaSystem.versio_java >= 1) {
            this.getFormulaTransfer().copy();
        }
        else if (super.B != null && super.B.espotcopiar()) {
            Formula.F = this.script(super.B);
        }
    }
    
    public final void copyText(final int n) {
        if (OmegaSystem.versio_java >= 1) {
            this.getFormulaTransfer().copyText(n);
        }
    }
    
    public final void paste() {
        if (OmegaSystem.versio_java >= 1) {
            this.getFormulaTransfer().paste();
        }
        else if (this.isEditable() && Formula.F != null) {
            this.InsertString(Formula.F);
        }
    }
    
    private final BoxPosition Afegir() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditor.EXPAND == null) ? (FormulaEditor.EXPAND = I("MultilineBox")) : FormulaEditor.EXPAND);
        if (buscaPosicioTipus.c == null) {
            return super.curpos;
        }
        return buscaPosicioTipus.c.PosAnt_Fill(buscaPosicioTipus.x + 1);
    }
    
    private final BoxPosition AfegirCapsaPredeterminada() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditor.EXPAND == null) ? (FormulaEditor.EXPAND = I("MultilineBox")) : FormulaEditor.EXPAND);
        if (buscaPosicioTipus.c == null) {
            return super.curpos;
        }
        return buscaPosicioTipus.c.PosSeg_Fill(buscaPosicioTipus.x - 1);
    }
    
    public final Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (super.G != null) {
            final Dimension viewportSize = super.G.getViewportSize();
            if (viewportSize.width < preferredSize.width && viewportSize.width != 0) {
                final int round = Math.round(0.4f * viewportSize.width);
                preferredSize.width = viewportSize.width + ((preferredSize.width - viewportSize.width) / round + 1) * round;
            }
            if (viewportSize.height < preferredSize.height && viewportSize.height != 0) {
                final int round2 = Math.round(0.4f * viewportSize.height);
                preferredSize.height = viewportSize.height + ((preferredSize.height - viewportSize.height) / round2 + 1) * round2;
            }
        }
        return preferredSize;
    }
    
    public final void imposeWidth() {
        final Component component = this.getComponent();
        if (component != null && super.lacapsa != null) {
            int n;
            if (super.G != null) {
                n = super.G.getViewportSize().width;
            }
            else {
                n = component.getSize().width;
            }
            super.lacapsa.recommendedWidth = Length.create(n - 2 * super.tl.x, this.getDPI());
            super.lacapsa.Composa(this);
            this.resetPosition();
            this.repaint();
        }
    }
    
    public final void MouScrollPerVeureCaret() {
        if (super.lacapsa == null) {
            return;
        }
        if (super.curpos == null) {
            return;
        }
        final Point posicioCaret = super.curpos.c.PosicioCaret(super.curpos.x, this);
        if (super.G == null) {
            return;
        }
        super.G.invalidate();
        super.G.validate();
        final Rectangle viewportRectangle = this.getViewportRectangle();
        final int max = Math.max(0, viewportRectangle.width - 15);
        final int max2 = Math.max(0, viewportRectangle.height - 15);
        int x = viewportRectangle.x;
        int y = viewportRectangle.y;
        final int round = Math.round(max * 0.4f);
        final int round2 = Math.round(max * 0.3f);
        final int round3 = Math.round(max2 * 0.1f);
        final int round4 = Math.round(max2 * 0.1f);
        if (posicioCaret.x + super.tl.x < x) {
            x = posicioCaret.x + super.tl.x - round2;
        }
        else if (posicioCaret.x + super.tl.x > x + max) {
            x = posicioCaret.x + super.tl.x - max + round;
        }
        if (posicioCaret.y + super.tl.y < y) {
            y = posicioCaret.y + super.tl.y - round3;
        }
        else if (super.tl.y + posicioCaret.y + super.curpos.c.TamanyCaret(super.curpos.x) > y + max2) {
            y = super.tl.y + posicioCaret.y + super.curpos.c.TamanyCaret(super.curpos.x) - max2 + round4;
        }
        super.S = (super.E != 0);
        final Dimension preferredSize = this.getPreferredSize();
        int min = Math.min(x, preferredSize.width - viewportRectangle.width);
        int min2 = Math.min(y, preferredSize.height - viewportRectangle.height);
        if (min < 0) {
            min = 0;
        }
        if (min2 < 0) {
            min2 = 0;
        }
        if (viewportRectangle.x != min || viewportRectangle.y != min2) {
            super.G.setScrollPosition(min, min2);
            super.G.invalidate();
            super.G.validate();
        }
    }
    
    private void matrixCommand(final Object o) {
        AbstractMatrixBox abstractMatrixBox = null;
        int x = 0;
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditor.EliminaFiles == null) ? (FormulaEditor.EliminaFiles = I("AbstractMatrixBox")) : FormulaEditor.EliminaFiles);
        if (buscaPosicioTipus.c instanceof AbstractMatrixBox) {
            abstractMatrixBox = (AbstractMatrixBox)buscaPosicioTipus.c;
            x = buscaPosicioTipus.x;
        }
        if (abstractMatrixBox == null && this.boxToDoProperties != null && this.boxToDoProperties instanceof AbstractMatrixBox) {
            abstractMatrixBox = (AbstractMatrixBox)this.boxToDoProperties;
        }
        if (abstractMatrixBox != null) {
            this.unselectArea();
            this.push_backup(super.curpos, abstractMatrixBox);
            final AbstractMatrixBox abstractMatrixBox2 = abstractMatrixBox;
            int n = x / abstractMatrixBox2.columnes;
            int n2 = x % abstractMatrixBox2.columnes;
            if (o.equals("addrowup") || o.equals("addrowdown")) {
                if (!o.equals("addrowup")) {
                    ++n;
                }
                this.setCaret(abstractMatrixBox2.AfegeixFila(this, n));
            }
            else if (o.equals("addcolumnleft") || o.equals("addcolumnright")) {
                if (!o.equals("addcolumnleft")) {
                    ++n2;
                }
                this.setCaret(abstractMatrixBox2.AfegeixColumna(this, n2));
            }
            else if (o.equals("removerow")) {
                abstractMatrixBox2.EliminaFiles(this, n, n + 1);
                this.resetPosition();
            }
            else if (o.equals("removecolumn")) {
                abstractMatrixBox2.EliminaColumnes(this, n2, n2 + 1);
                this.resetPosition();
            }
            if (!this.D()) {
                this.setCaret(goLeft(super.curpos, true));
            }
            this.changeNotification();
            this.repaint();
        }
    }
    
    protected boolean ProcessaOrdre(final String s) {
        if (s.startsWith("insert:")) {
            this.insertString(s.substring("insert:".length()));
            return true;
        }
        if (Utils.equals(s, "cut")) {
            this.cut();
            this.repaint();
        }
        else if (Utils.equals(s, "copy")) {
            this.copy();
            this.repaint();
        }
        else if (Utils.equals(s, "copytext")) {
            this.copyText(1);
            this.repaint();
        }
        else if (Utils.equals(s, "copymoodle")) {
            this.copyText(2);
            this.repaint();
        }
        else if (Utils.equals(s, "paste")) {
            this.paste();
            this.repaint();
        }
        else if (Utils.equals(s, "delete")) {
            this.Delete();
        }
        else if (Utils.equals(s, "noeditable")) {
            this.setSelectionStyle(2, true);
        }
        else if (Utils.equals(s, "invisible")) {
            this.setSelectionStyle(4, true);
        }
        else if (Utils.equals(s, "visible")) {
            this.setSelectionStyle(4, false);
        }
        else if (Utils.equals(s, "undo")) {
            this.undo();
        }
        else if (Utils.equals(s, "redo")) {
            this.redo();
        }
        else if (Utils.equals(s, "removerow") || Utils.equals(s, "removecolumn") || Utils.equals(s, "addrowdown") || Utils.equals(s, "addrowup") || Utils.equals(s, "addcolumnright") || Utils.equals(s, "addcolumnleft") || Utils.equals(s, "makedynamic")) {
            this.matrixCommand(s);
        }
        else if (!Utils.equals(s, "properties")) {
            if (Utils.equals(s, "tab")) {
                this.tab(1);
            }
            else if (Utils.equals(s, "bold")) {
                final Attributes attributes = new Attributes();
                attributes.setType(0);
                attributes.setBold(true);
                this.canviaAtributSeleccio(attributes);
            }
            else if (Utils.equals(s, "nobold")) {
                final Attributes attributes2 = new Attributes();
                attributes2.setType(0);
                attributes2.setBold(false);
                this.canviaAtributSeleccio(attributes2);
            }
            else if (Utils.equals(s, "italic")) {
                final Attributes attributes3 = new Attributes();
                attributes3.setType(0);
                attributes3.setItalic(true);
                this.canviaAtributSeleccio(attributes3);
            }
            else {
                if (!Utils.equals(s, "noitalic")) {
                    return false;
                }
                final Attributes attributes4 = new Attributes();
                attributes4.setType(0);
                attributes4.setItalic(false);
                this.canviaAtributSeleccio(attributes4);
            }
        }
        this.requestFocus();
        return true;
    }
    
    public final Frame getMainFrame() {
        Component component;
        for (component = super.H; component != null && !(component instanceof Frame); component = component.getParent()) {}
        return (Frame)component;
    }
    
    protected void notifyChangePosition(final BoxPosition boxPosition, final BoxPosition boxPosition2) {
        if (this.AfegirNoCalc != null && !this.AfegirNoCalc.equals(boxPosition2)) {
            this.AfegirCapsaPredeterminada = null;
            this.AfegirNoCalc = null;
        }
        final Rectangle i = this.I();
        if ((i != null && !i.equals(super.currentBoxRectangle)) || (i == null && super.currentBoxRectangle != null)) {
            this.repaint();
        }
        super.currentBoxRectangle = i;
        if (super.curpos != null) {
            final Rectangle afegirNoCalc = this.AfegirNoCalc(super.curpos);
            this.K.setBounds(afegirNoCalc.x, afegirNoCalc.y, afegirNoCalc.width, afegirNoCalc.height);
            if (super.mathRTL && this.Z() > 0) {
                this.K.setReflection(this.Z());
            }
        }
        super.notifyChangePosition(boxPosition, boxPosition2);
        this.K.setVisible(super.D && this.isEditablePosition(super.curpos));
        this.firePropertyChange("position", boxPosition, boxPosition2);
    }
    
    private final Rectangle AfegirNoCalc(final BoxPosition boxPosition) {
        final Point posicioCaret = boxPosition.c.PosicioCaret(boxPosition.x, this);
        return new Rectangle(super.tl.x + posicioCaret.x, super.tl.y + posicioCaret.y, 2, boxPosition.c.TamanyCaret(boxPosition.x));
    }
    
    protected final boolean D() {
        return this.isEditablePosition(super.curpos);
    }
    
    public final boolean isEditablePosition(final BoxPosition boxPosition) {
        return boxPosition != null && (Attributes.obteAtributs(this, boxPosition).estil & 0xA) == 0x0;
    }
    
    protected final boolean I(final BoxPosition boxPosition, final boolean b) {
        boolean b2 = super.I(boxPosition, b);
        if (b2 && !b) {
            b2 = this.isEditablePosition(boxPosition);
        }
        return b2;
    }
    
    protected final boolean Composa() {
        return super.B != null && !super.B.getMama().isStyle(2);
    }
    
    public boolean isEditable() {
        return true;
    }
    
    public final void ShowXML() {
        final MessageBox messageBox = new MessageBox(this.getMainFrame(), "XML", 3);
        messageBox.add("MATHML Script");
        final TextArea textArea = new TextArea(this.M, 30, 60);
        messageBox.setResizable(true);
        AbstractBox abstractBox;
        if (super.B != null && super.B.espotsubstituir()) {
            abstractBox = this.parse(this.script(super.B));
            abstractBox.Composa(this);
        }
        else {
            abstractBox = super.lacapsa;
        }
        final BoxScripting boxScripting = new BoxScripting(1, "  ", "\n");
        abstractBox.script(boxScripting);
        textArea.setText(XMLParser.string2CharData(XMLParser.opt_char2entity, boxScripting.toString()));
        messageBox.add(textArea, MessageBox.EXPAND);
        if (messageBox.showDialog().equals("Ok")) {
            this.M = textArea.getText();
            if (super.B != null && super.B.espotsubstituir()) {
                super.curpos = super.B.suprimeix(this);
                if (!super.B.esValida) {
                    super.B = null;
                }
                super.curpos = this.InsertString(this, super.curpos, MathML2Box.newMathML2Box().parse(this.M));
            }
            else {
                this.setString(this.M, true);
                this.changeNotification();
            }
        }
    }
    
    public final void ShowBoxes() {
        AbstractBox abstractBox;
        if (super.B != null && super.B.espotsubstituir()) {
            abstractBox = super.B.getMama();
        }
        else {
            abstractBox = super.lacapsa;
        }
        ShowBoxes(this.getMainFrame(), abstractBox);
    }
    
    public static final void ShowBoxes(final Frame frame, final AbstractBox abstractBox) {
        try {
            final StringBuffer sb = new StringBuffer();
            final MessageBox messageBox = new MessageBox(frame, "Boxes", 1);
            final TextArea textArea = new TextArea("", 30, 80);
            messageBox.setResizable(true);
            textArea.setFont(new Font("Monospaced", 0, 12));
            final Stack stack = new Stack<AbstractBox>();
            stack.push(abstractBox);
            int n = 0;
            while (!stack.empty()) {
                final AbstractBox abstractBox2 = stack.pop();
                if (abstractBox2 == null) {
                    --n;
                    sb.append(")");
                }
                else {
                    sb.append('\n');
                    sb.append("[" + ((n < 10) ? " " : "") + n + "] ");
                    for (int i = 0; i < n; ++i) {
                        sb.append("'  ");
                    }
                    sb.append(((TextBox)abstractBox2).getClass().getName());
                    sb.append(" enbl=" + (((abstractBox2.estil & 0x8) != 0x0) ? "false" : "true"));
                    sb.append(" fenbl=" + (((abstractBox2.forca_estil & 0x8) != 0x0) ? "true" : "false"));
                    ++n;
                    sb.append('(');
                    if (abstractBox2 instanceof TextBox) {
                        sb.append(((TextBox)abstractBox2).cadena);
                    }
                    stack.push(null);
                    for (int j = abstractBox2.nfills - 1; j >= 0; --j) {
                        stack.push(abstractBox2.fill[j]);
                    }
                }
            }
            textArea.setText(sb.toString());
            messageBox.add(textArea);
            messageBox.show();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            System.err.println("End");
        }
    }
    
    public final void tab(final int n) {
    }
    
    private static final boolean DEFAULT_CURSOR(final BoxComponent boxComponent, final AbstractBox abstractBox) {
        return abstractBox == null || (abstractBox.onValidate(boxComponent) && DEFAULT_CURSOR(boxComponent, abstractBox.getParentBox()));
    }
    
    private static final BoxPosition Delete(final AbstractBox abstractBox, final BoxComponent boxComponent, final Object o, final Stack stack, final BoxPosition boxPosition) {
        final Object[] array = (Object[])o;
        final String s = (String)array[0];
        final int[] array2 = (int[])array[1];
        final int[] array3 = (int[])array[2];
        AbstractBox lacapsa;
        if (array3 != null) {
            final AbstractBox parse = boxComponent.parse(s);
            final BoxPosition boxPosition2 = new BoxPosition(EliminaColumnes(abstractBox), array3);
            if (stack != null && boxPosition != null) {
                stack.push(EXPAND(boxComponent, boxPosition, boxPosition2.c.fill[boxPosition2.x]));
            }
            boxPosition2.c.SubstitueixCapsa(parse, boxPosition2.x, boxComponent);
            lacapsa = boxPosition2.c.fill[boxPosition2.x];
        }
        else {
            final Formula formula = (Formula)boxComponent;
            if (stack != null && boxPosition != null) {
                stack.push(EXPAND(boxComponent, boxPosition, formula.lacapsa));
            }
            formula.setString(s, true);
            lacapsa = formula.lacapsa;
        }
        BoxPosition boxPosition3;
        if (array2 != null) {
            boxPosition3 = new BoxPosition(lacapsa, array2);
        }
        else {
            boxPosition3 = lacapsa.PosSeg_Fora();
        }
        if (array[3] != null) {
            boxPosition3 = Delete(abstractBox, boxComponent, array[3], stack, boxPosition3);
            I(stack);
        }
        return boxPosition3;
    }
    
    private static final Object EXPAND(final BoxComponent boxComponent, final BoxPosition boxPosition, AbstractBox parentBox) {
        for (BoxScripting boxScripting = ((Formula)boxComponent).getBoxScripting(); parentBox.getParentBox() != null && parentBox.scriptCommand(boxScripting) == null; parentBox = parentBox.getParentBox()) {}
        int[] coordinates;
        if (parentBox.getParentBox() != null) {
            coordinates = new BoxPosition(parentBox.getParentBox(), parentBox.p_en_pare).coordinates(EliminaColumnes(parentBox));
        }
        else {
            coordinates = null;
        }
        return new Object[] { boxComponent.script(parentBox), (boxPosition == null) ? null : boxPosition.coordinates(parentBox), coordinates, null };
    }
    
    private static final AbstractBox EliminaColumnes(AbstractBox parentBox) {
        while (parentBox.getParentBox() != null) {
            parentBox = parentBox.getParentBox();
        }
        return parentBox;
    }
    
    public final void unDo(final Stack stack, final Stack stack2) {
        this.unselectArea();
        if (stack.empty()) {
            return;
        }
        this.setCaret(Delete(super.lacapsa, this, stack.pop(), stack2, super.curpos));
        this.changeNotification();
        this.repaint();
    }
    
    protected static final void I(final Stack stack) {
        if (stack.size() < 2) {
            return;
        }
        final Object[] array = stack.pop();
        final Object[] array2 = stack.pop();
        Object[] array3;
        for (array3 = array; array3[3] != null; array3 = (Object[])array3[3]) {}
        array3[3] = array2;
        stack.push(array);
    }
    
    public final void push_backup(final BoxPosition boxPosition, final AbstractBox abstractBox) {
        this.AfegeixFila.removeAllElements();
        this.N.push(EXPAND(this, boxPosition, abstractBox));
        this.changeNotification();
    }
    
    public static final void push_backup(final BoxComponent boxComponent, final BoxPosition boxPosition, final AbstractBox abstractBox) {
        if (boxComponent != null && boxComponent instanceof FormulaEditor) {
            ((FormulaEditor)boxComponent).push_backup(boxPosition, abstractBox);
        }
    }
    
    public final void canviaAtributSeleccio(final Attributes attributes) {
        if (super.B == null) {
            if (this.AfegirCapsaPredeterminada == null) {
                this.AfegirCapsaPredeterminada = new Attributes(attributes);
            }
            else {
                this.AfegirCapsaPredeterminada.join(attributes);
            }
            this.AfegirNoCalc = super.curpos;
            return;
        }
        this.push_backup(super.curpos, super.B.getMama());
        final int size = this.N.size();
        super.B = super.B.canviaAtribut(this, attributes);
        if (this.N.size() > size) {
            I(this.N);
        }
        super.curpos = super.B.getCurpos();
        this.repaint();
    }
    
    public final void setSelectionStyle(final int n, final boolean b) {
        this.canviaAtributSeleccio(new Attributes(n, b));
    }
    
    protected void F() {
        FormulaEditor.L = this;
        OmegaSystem.context = this.context;
    }
    
    public boolean getIsUndoEmpty() {
        return this.N.isEmpty();
    }
    
    public boolean getIsRedoEmpty() {
        return this.AfegeixFila.isEmpty();
    }
    
    public boolean getHasSelection() {
        return super.B != null;
    }
    
    public final void undo() {
        this.unDo(this.N, this.AfegeixFila);
    }
    
    public final void redo() {
        this.unDo(this.AfegeixFila, this.N);
    }
    
    public void changeNotification() {
        this.AfegeixColumna = true;
    }
    
    public final void paint(final Graphics graphics) {
        this.flushChanged();
        super.paint(graphics);
    }
    
    public final void flushChanged() {
        if (this.AfegeixColumna) {
            this.AfegeixColumna = false;
            this.firePropertyChange("xmlChanged");
        }
    }
    
    public final void firePropertyChange(final String s) {
        this.Delete.firePropertyChange(s, Boolean.TRUE, Boolean.FALSE);
    }
    
    public final void firePropertyChange(final String s, final Object o, final Object o2) {
        this.Delete.firePropertyChange(s, o, o2);
    }
    
    public final void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.Delete.addPropertyChangeListener(propertyChangeListener);
    }
    
    public final void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.Delete.removePropertyChangeListener(propertyChangeListener);
    }
    
    public final PropertyChangeSupport getPropertyChangeSupport() {
        return this.Delete;
    }
    
    public final void actionNotification(final String s) {
    }
    
    public final void clearUndoMemory() {
        this.N.removeAllElements();
        this.AfegeixFila.removeAllElements();
        this.actionNotification("emptyUndo");
    }
    
    public final void addMenuSeparator() {
        if (this.Afegir.getItemCount() == 0) {
            return;
        }
        if (this.Afegir.getItem(this.Afegir.getItemCount() - 1).getLabel().equals("-")) {
            return;
        }
        this.Afegir.addSeparator();
    }
    
    public final void addMenuItem(final String s, final String actionCommand, final ActionListener actionListener, final boolean enabled) {
        final MenuItem menuItem = new MenuItem(s);
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(actionListener);
        menuItem.setEnabled(enabled);
        this.Afegir.add(menuItem);
    }
    
    protected boolean I(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger() && super.lacapsa != null) {
            final Point i = this.I(mouseEvent.getPoint());
            this.boxToDoProperties = super.lacapsa.deepExplore(this, 5, i.x - super.tl.x, i.y - super.tl.y);
            this.Afegir.removeAll();
            if (this.boxToDoProperties != null) {
                if (this.isDesign() || !this.boxToDoProperties.isStyle(2)) {
                    this.boxToDoProperties.buildContextualMenu(this);
                }
                return this.Afegir.getItemCount() > 0;
            }
        }
        return false;
    }
    
    protected final void EliminaFiles(final Point point) {
        if (this.Afegir.getItemCount() == 1) {
            this.ProcessaOrdre(this.Afegir.getItem(0).getActionCommand());
        }
        else {
            this.Afegir.show(super.H, point.x, point.y);
        }
    }
    
    public int postVisitBox(final AbstractBox abstractBox, final int n, final int n2, final int n3) {
        switch (n) {
            case 5: {
                if (abstractBox.hasMenuProperties()) {
                    return 1;
                }
                break;
            }
        }
        return super.postVisitBox(abstractBox, n, n2, n3);
    }
    
    public void setAcceleratorByMenu(final boolean b) {
        this.enableEditAccelerator = !b;
    }
    
    public final BoxPosition suprSelection(final AbstractSelection abstractSelection) {
        this.push_backup(super.curpos, abstractSelection.getMama());
        return abstractSelection.suprimeix(this);
    }
    
    public final void suprSelection() {
        final BoxPosition suprSelection = this.suprSelection(super.B);
        if (!super.B.esValida) {
            super.B = null;
        }
        else {
            final BoxPosition boxPosition = suprSelection;
            super.C = boxPosition;
            super.Z = boxPosition;
        }
        this.setCaret(suprSelection);
    }
    
    public final int countJoins(final TokensBox tokensBox, final int n) {
        if (n < 0 || n + 1 >= tokensBox.nfills) {
            return 0;
        }
        final TextBox textBox = (tokensBox.fill[n] instanceof TextBox) ? ((TextBox)tokensBox.fill[n]) : null;
        final TextBox textBox2 = (tokensBox.fill[n + 1] instanceof TextBox) ? ((TextBox)tokensBox.fill[n + 1]) : null;
        if (textBox != null && textBox2 != null && textBox.joins(textBox2) && Attributes.iguals(textBox, textBox2)) {
            return 1;
        }
        return 0;
    }
    
    public final void doJoin(final TokensBox tokensBox, final int n) {
        final int countJoins = this.countJoins(tokensBox, n);
        if (countJoins == -1) {
            final TextBox textBox = (tokensBox.fill[n] instanceof TextBox) ? ((TextBox)tokensBox.fill[n]) : null;
            final TextBox textBox2 = new TextBox(textBox.cadena.substring(1), textBox.type);
            textBox2.setAttributes(textBox.getAttributes());
            textBox.cadena = textBox.cadena.substring(0, 1);
            if (tokensBox._cc != null) {
                textBox.enCalculRect(this);
            }
            tokensBox.Afegir(textBox2, n + 1, (textBox._cc != null) ? this : null);
        }
        if (countJoins == 1) {
            final TextBox textBox3 = (tokensBox.fill[n] instanceof TextBox) ? ((TextBox)tokensBox.fill[n]) : null;
            final TextBox textBox4 = (tokensBox.fill[n + 1] instanceof TextBox) ? ((TextBox)tokensBox.fill[n + 1]) : null;
            final StringBuffer sb = new StringBuffer();
            final TextBox textBox5 = textBox3;
            textBox5.cadena = sb.append(textBox5.cadena).append(textBox4.cadena).toString();
            if (textBox3._cc != null) {
                textBox3.enCalculRect(this);
            }
            tokensBox.Treure(n + 1, (textBox3._cc != null) ? this : null);
        }
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
        FormulaEditor.L = null;
    }
}
