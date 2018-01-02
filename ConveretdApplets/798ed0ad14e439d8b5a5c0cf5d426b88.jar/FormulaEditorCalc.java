import java.awt.image.ImageObserver;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputEvent;
import java.awt.event.ComponentEvent;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Component;
import java.util.Hashtable;
import java.beans.PropertyChangeListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaEditorCalc extends FormulaEditor implements ItemListener, ActionListener, PaintListener, ComponentListener, PropertyChangeListener, Runnable, WirisFormulaInterface
{
    private PlotterFrameManager plotterManager;
    public WList llistaErrors;
    Hashtable ActualitzaRect;
    public String tipusToolbar;
    boolean BuscaPareTipus;
    Component O;
    public static boolean showKernel;
    public boolean calcButtonVisible;
    private Image sponsor;
    public boolean dialogShown;
    private String edicioArgument;
    private int systemDim;
    private int vectorDim;
    private int matrixRows;
    private int matrixCols;
    protected boolean BuscaTokensVTipus;
    public boolean pendingSetContent;
    public Calculator calculator;
    public long igonreEventTime;
    public boolean waitCursorState;
    public Cursor backupCursor;
    public boolean overrideDocumentBase;
    static boolean DemanaInsertaTex;
    static long FALSE;
    CapsaComandes InsertString;
    AbstractBox[] MarcaErrorsWarnings;
    Vector NovaCapsa;
    static Class ObteOmegaStrings2;
    static Class PosAnt_Fora;
    static Class PosSeg_Fill;
    static Class PosSeg_Fora;
    static Class PosicioCaret;
    static Class PosicioReal;
    static Class ProcessaOrdre;
    static Class RespostaPlotters;
    static Class TRUE;
    static Class Treure;
    static Class TreureNoCalc;
    
    public FormulaEditorCalc(final String s, final WList llistaErrors, final String tipusToolbar, final boolean buscaTokensVTipus, final ScrollPaneWrapper scrollPaneWrapper, final AppInterface appInterface, final boolean buscaPareTipus, final Component component) {
        super(s, scrollPaneWrapper, appInterface, component);
        this.tipusToolbar = null;
        this.calcButtonVisible = true;
        this.dialogShown = false;
        this.systemDim = 3;
        this.vectorDim = 3;
        this.matrixRows = 3;
        this.matrixCols = 3;
        this.calculator = new Calculator();
        this.InsertString = null;
        this.MarcaErrorsWarnings = null;
        this.NovaCapsa = new Vector();
        if (appInterface != null) {
            this.addPropertyChangeListener(appInterface);
        }
        if (scrollPaneWrapper != null) {
            scrollPaneWrapper.getScrollPane().addComponentListener(this);
        }
        else if (component != null) {
            component.addComponentListener(this);
        }
        this.llistaErrors = llistaErrors;
        this.tipusToolbar = tipusToolbar;
        this.BuscaPareTipus = buscaPareTipus;
        this.BuscaTokensVTipus = buscaTokensVTipus;
        this.ActualitzaRect = new Hashtable();
        this.omega_restart();
        this.addPaintListener(this);
        this.setTimeout(10000);
        this.plotterManager = PlotterFrameManager.newInstance();
        if (this.calculator.loading()) {
            this.calculator.I.getPropertyChangeSupport().addPropertyChangeListener(this);
        }
    }
    
    public final void setTimeout(final int timeout) {
        this.calculator.setTimeout(timeout);
    }
    
    public final void omega_restart() {
    }
    
    public final void inicialitza() {
        super.inicialitza();
        this.BuscaTokensVTipus();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (FormulaEditorCalc.DemanaInsertaTex) {
            FormulaEditorCalc.FALSE = mouseEvent.getWhen() - new Date().getTime();
            if (Math.abs(FormulaEditorCalc.FALSE) < 100000L) {
                FormulaEditorCalc.FALSE = 0L;
            }
            else {
                System.out.println("Setting delta time: " + FormulaEditorCalc.FALSE);
            }
            FormulaEditorCalc.DemanaInsertaTex = false;
        }
        super.mousePressed(mouseEvent);
    }
    
    protected final boolean I(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final char keyChar = keyEvent.getKeyChar();
        if (keyEvent.isControlDown() && !keyEvent.isAltDown()) {
            if (!keyEvent.isShiftDown()) {
                if (keyCode == 84) {
                    this.ProcessaOrdre("comment");
                    return true;
                }
                if (keyCode == 191 || keyCode == 47 || keyChar == '\u00e7' || keyCode == 222 || keyCode == 129) {
                    this.ProcessaOrdre("Bparenthesis");
                    return false;
                }
                if (keyCode == 187 || keyCode == 521 || keyChar == '+' || keyCode == 186 || keyCode == 128) {
                    this.ProcessaOrdre("bparenthesis");
                    return false;
                }
                if (keyCode == 153 || keyCode == 226 || keyChar == '>') {
                    this.ProcessaOrdre("leq");
                    return true;
                }
            }
            else if (keyCode == 153 || keyCode == 226 || keyChar == '>') {
                this.ProcessaOrdre("geq");
                return true;
            }
            if (keyEvent.isShiftDown()) {
                switch (keyCode) {
                    case 48: {
                        this.ProcessaOrdre(":=");
                        return true;
                    }
                    case 110: {
                        this.ProcessaOrdre("power");
                        return true;
                    }
                    case 73: {
                        this.ProcessaOrdre("integral");
                        return true;
                    }
                    case 80: {
                        this.ProcessaOrdre("iintegral");
                        return true;
                    }
                    case 75: {
                        FormulaEditorCalc.showKernel = !FormulaEditorCalc.showKernel;
                        this.calculator.setDebug(FormulaEditorCalc.showKernel);
                        return true;
                    }
                    case 67:
                    case 155: {
                        this.comprovaResultats(false, false);
                        this.MouScrollPerVeureCaret();
                        this.repaint();
                        return true;
                    }
                    case 84: {
                        this.DemanaInsertaTex();
                        return true;
                    }
                }
            }
            else {
                switch (keyCode) {
                    case 10: {
                        this.calcButtonVisible = false;
                        this.ActualitzaRect();
                        return true;
                    }
                    case 48: {
                        this.ProcessaOrdre("eq");
                        return true;
                    }
                    case 49: {
                        this.ProcessaOrdre("neq");
                        return true;
                    }
                    case 55: {
                        this.ProcessaOrdre("frac");
                        return true;
                    }
                    case 56:
                    case 57: {
                        this.ProcessaOrdre("pparenthesis");
                        return true;
                    }
                    case 110: {
                        this.ProcessaOrdre("_");
                        return true;
                    }
                    case 38: {
                        this.ProcessaOrdre("power");
                        return true;
                    }
                    case 40: {
                        this.ProcessaOrdre("_");
                        return true;
                    }
                    case 65: {
                        this.ProcessaOrdre("root");
                        return true;
                    }
                    case 81: {
                        this.ProcessaOrdre("sqrt");
                        return true;
                    }
                    case 68: {
                        this.ProcessaOrdre("differentiate");
                        return true;
                    }
                    case 73: {
                        this.ProcessaOrdre("Oi");
                        return true;
                    }
                    case 80: {
                        this.ProcessaOrdre("Opi");
                        return true;
                    }
                    case 69: {
                        this.ProcessaOrdre("Oe");
                        return true;
                    }
                    case 76: {
                        this.ProcessaOrdre("limit");
                        return true;
                    }
                }
            }
        }
        return super.I(keyEvent);
    }
    
    protected final void C() {
        if (this.novaComanda()) {
            this.repaint();
        }
        else {
            super.C();
        }
    }
    
    protected final void B() {
        if (!this.isEditablePosition(super.curpos.BuscaPosicioTipus((FormulaEditorCalc.PosicioCaret == null) ? (FormulaEditorCalc.PosicioCaret = I("TokensVBox")) : FormulaEditorCalc.PosicioCaret))) {
            return;
        }
        super.B();
    }
    
    private final void ActualitzaRect() {
        if (!this.BuscaTokensVTipus) {
            return;
        }
        this.Afegir(true);
        final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.PosSeg_Fill == null) ? (FormulaEditorCalc.PosSeg_Fill = I("InterfaceBox")) : FormulaEditorCalc.PosSeg_Fill);
        if (buscaTokensVTipus.x == buscaTokensVTipus.c.nfills - 1) {
            this.BuscaPareTipus(false);
        }
        else {
            this.setCaret(buscaTokensVTipus.c.fill[buscaTokensVTipus.x + 1].PosSeg_Fora());
        }
        this.repaint();
    }
    
    private final void Afegir(final boolean b) {
        if (super.context != null) {
            super.context.actionPerformed(new ActionEvent(this, 1999, "calculant"));
        }
        if (this.InsertString != null) {
            return;
        }
        this.AfegirCapsaPredeterminada((CapsaComandes)super.curpos.BuscaPareTipus((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora), b);
    }
    
    private final void AfegirCapsaPredeterminada(final CapsaComandes insertString, final boolean b) {
        if (insertString == null) {
            return;
        }
        this.InsertString = insertString;
        this.InsertString.calculant = true;
        this.InsertString.setPlotters(null);
        if (this.O != null) {
            this.O.setVisible(false);
        }
        final Vector vector = new Vector();
        final Vector vector2 = new Vector();
        this.InsertString.ObteOmegaStrings2(vector, vector2, true);
        final String[] array = new String[vector.size()];
        this.MarcaErrorsWarnings = new AbstractBox[vector2.size()];
        vector.copyInto(array);
        vector2.copyInto(this.MarcaErrorsWarnings);
        for (int i = 0; i < this.MarcaErrorsWarnings.length; ++i) {
            this.MarcaErrorsWarnings[i].netejaErrors();
        }
        try {
            this.calculator.evaluate(this, array, super.H, b);
        }
        catch (Exception ex) {
            if (this.InsertString != null) {
                this.InsertString.calculant = false;
            }
            this.InsertString = null;
        }
        this.repaint();
    }
    
    private final void AfegirNoCalc(final OmegaClient omegaClient, final String[] array) {
        final AbstractBox[] array2 = new AbstractBox[2];
        int n = 1;
        final CapsaComandes insertString = this.InsertString;
        this.InsertString.calculant = false;
        this.InsertString = null;
        final AbstractBox abstractBox = insertString.fill[0];
        final int n2 = array.length - abstractBox.nfills;
        if (!(insertString.getParentBox() instanceof CapsaLlibreria)) {
            for (int i = 0; i < abstractBox.nfills; ++i) {
                final AbstractBox parse = MathML2Box.newMathML2Box().parse(array[i + n2]);
                final CapsaComanda capsaComanda = (CapsaComanda)abstractBox.fill[i];
                if ((capsaComanda.estil & 0x1) == 0x0) {
                    this.push_backup(super.curpos, capsaComanda);
                    if (n == 0) {
                        FormulaEditor.I(super.N);
                    }
                    n = 0;
                    array2[0] = capsaComanda.TreureNoCalc(0);
                    array2[1] = parse;
                    capsaComanda.TreureTot(this);
                    capsaComanda.inicialitzaFills(array2);
                    capsaComanda.Composa(this);
                    capsaComanda.getParentBox().ActualitzaRect(this);
                }
            }
        }
        try {
            this.MarcaErrorsWarnings(omegaClient, this.MarcaErrorsWarnings);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.RespostaPlotters(omegaClient, insertString, insertString, insertString);
        }
        catch (OException ex2) {
            System.out.println(ex2.toString());
        }
        if (this.O != null && this.calcButtonVisible) {
            this.O.setVisible(true);
        }
        this.resetPosition();
    }
    
    public final void MarcaErrorsWarnings(final Object o, final AbstractBox[] array) {
        this.llistaErrors.removeAllItems();
        final Vector<String> vector = new Vector<String>();
        this.ActualitzaRect.clear();
        final Vector errorsWarnings = ((OmegaClient)o).getErrorsWarnings();
        for (int i = 0; i < errorsWarnings.size(); ++i) {
            final ErrorWarning errorWarning = errorsWarnings.elementAt(i);
            AbstractBox abstractBox = null;
            final int max = Math.max(errorWarning.column - 1, 0);
            final int max2 = Math.max(errorWarning.line - 1, 0);
            final WirisBoxScripting wirisBoxScripting = new WirisBoxScripting(new int[] { max });
            if (array[max2] != null) {
                array[max2].script(wirisBoxScripting);
            }
            final AbstractBox[] boxes = wirisBoxScripting.getBoxes();
            if (boxes.length > 0) {
                abstractBox = boxes[0];
            }
            if (abstractBox != null) {
                abstractBox.error = errorWarning.classification;
                this.ActualitzaRect.put(new Integer(i), abstractBox);
            }
            if (errorWarning.classification.length() > 2) {
                vector.addElement(i + ": " + errorWarning.classification + ": " + errorWarning.text);
            }
            else {
                vector.addElement(i + ": " + errorWarning.text);
            }
        }
        this.llistaErrors.add(vector);
    }
    
    public final Color getColor(final int n, final AbstractBox abstractBox) {
        if (n != 13 || abstractBox == null || abstractBox.error == null) {
            return super.getColor(n, abstractBox);
        }
        final String error = abstractBox.error;
        if (error.indexOf("Warning") != -1 || error.indexOf("Warning") != -1) {
            return super.colors[8];
        }
        if (error.indexOf("Error") != -1 || error.indexOf("Error") != -1) {
            return super.colors[7];
        }
        return null;
    }
    
    public final void RespostaPlotters(final Object o, final CapsaComandes capsaComandes, final AbstractBox abstractBox, final AbstractBox abstractBox2) {
        this.plotterManager.setMainFrame(this.getMainFrame());
        final Vector graphics = ((OmegaClient)o).getGraphics();
        final int size = graphics.size();
        final Vector vector = new Vector<Dialog3d>();
        for (int i = 0; i < size; ++i) {
            final InputStream inputStream = graphics.elementAt(i);
            if (isGraphics3D(inputStream)) {
                vector.addElement(new Dialog3d(inputStream, abstractBox2, this, super.context));
            }
            else {
                vector.addElement((Dialog3d)new PlotterPanel(new PlotCanvas(inputStream, abstractBox2, this, super.context)));
            }
        }
        final PlotterPanel[] array = new PlotterPanel[vector.size()];
        vector.copyInto(array);
        this.plotterManager.createFrame((capsaComandes != null) ? capsaComandes : abstractBox, array);
    }
    
    public static final boolean isGraphics3D(final InputStream inputStream) {
        final byte[] array = { 87, 51, 68 };
        boolean b = true;
        try {
            for (int i = 0; i < 3; ++i) {
                if (inputStream.read() != array[i]) {
                    b = false;
                }
            }
            inputStream.reset();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    final void I(final OmegaClient omegaClient, final String[] array) {
        this.AfegirNoCalc(omegaClient, array);
        this.MouScrollPerVeureCaret();
        this.repaint();
    }
    
    private final void stop() {
        if (this.InsertString != null) {
            try {
                this.calculator.interrupt();
            }
            catch (OException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void comprovaResultats(final boolean b, final boolean b2) {
        final ComprovaResultats comprovaResultats = new ComprovaResultats(this, super.lacapsa, this.calculator.I, b);
        comprovaResultats.start();
        if (b2) {
            try {
                comprovaResultats.join();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.llistaErrors) {
            final AbstractBox abstractBox = this.ActualitzaRect.get(new Integer(this.llistaErrors.getSelectedIndex()));
            if (abstractBox != null) {
                AbstractBox parentBox;
                for (parentBox = abstractBox; parentBox.getParentBox() != null && parentBox.p_en_pare < parentBox.getParentBox().nfills && parentBox.getParentBox().fill[parentBox.p_en_pare] == parentBox; parentBox = parentBox.getParentBox()) {}
                if (parentBox == super.lacapsa) {
                    this.setCaret(abstractBox.PosSeg_Fora());
                    this.repaint();
                }
            }
            this.requestFocus();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (!this.ProcessaOrdre(actionEvent.getActionCommand())) {
            this.F();
            super.context.actionPerformed(actionEvent);
        }
    }
    
    public final void newSession() {
        this.stop();
        this.llistaErrors.removeAllItems();
        this.clearUndoMemory();
        if (super.lacapsa != null) {
            super.lacapsa.notificaEsborrat(this);
        }
        (super.lacapsa = new InterfaceBox()).inicialitzaFills();
        super.lacapsa.Composa(this);
        super.curpos = null;
        this.unselectArea();
        this.changeNotification();
        this.resetPosition();
    }
    
    private final void BuscaPareTipus(final boolean b) {
        final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.PosSeg_Fill == null) ? (FormulaEditorCalc.PosSeg_Fill = I("InterfaceBox")) : FormulaEditorCalc.PosSeg_Fill);
        if (!(buscaTokensVTipus.c.getParentBox() instanceof InterfaceBox)) {
            return;
        }
        final BoxPosition posAnt = super.curpos.c.PosAnt(super.curpos.x);
        if (b && posAnt.equals(super.curpos)) {
            final BoxPosition boxPosition = buscaTokensVTipus;
            --boxPosition.x;
        }
        this.push_backup(super.curpos, buscaTokensVTipus.c);
        final CapsaComandes capsaComandes = new CapsaComandes();
        capsaComandes.inicialitzaFills();
        buscaTokensVTipus.c.Afegir(capsaComandes, buscaTokensVTipus.x + 1, this);
        this.setCaret(capsaComandes.PosSeg_Fora());
    }
    
    private final boolean novaComanda() {
        final BoxPosition posAnt = super.curpos.c.PosAnt(super.curpos.x);
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.PosicioReal == null) ? (FormulaEditorCalc.PosicioReal = I("MultilineBox")) : FormulaEditorCalc.PosicioReal);
        final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
        final BoxPosition buscaTokensVTipus2 = posAnt.BuscaTokensVTipus((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
        if (buscaPosicioTipus.c == null) {
            return false;
        }
        if (!(buscaPosicioTipus.c.getParentBox() instanceof CapsaComanda) || !(buscaTokensVTipus.c.getParentBox() instanceof CapsaComandes) || !this.isEditablePosition(buscaTokensVTipus)) {
            this.B();
            return true;
        }
        if ((!buscaTokensVTipus.equals(buscaTokensVTipus2) || posAnt.equals(super.curpos)) && !buscaTokensVTipus.c.fill[buscaTokensVTipus.x].isEmpty(this)) {
            final BoxPosition boxPosition = buscaTokensVTipus;
            --boxPosition.x;
        }
        this.push_backup(super.curpos, buscaTokensVTipus.c);
        final CapsaComanda capsaComanda = new CapsaComanda();
        capsaComanda.inicialitzaFills();
        buscaTokensVTipus.c.Afegir(capsaComanda, buscaTokensVTipus.x + 1, this);
        this.setCaret(capsaComanda.PosSeg_Fora());
        return true;
    }
    
    private final void BuscaTokensVTipus() {
        if (this.O == null) {
            return;
        }
        final Rectangle viewportRectangle = this.getViewportRectangle();
        if (super.curpos == null) {
            return;
        }
        final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
        final Point posicioReal = buscaTokensVTipus.c.PosicioReal();
        posicioReal.translate(super.tl.x, super.tl.y);
        final Point posicioCaret = super.curpos.c.PosicioCaret(super.curpos.x, this);
        posicioCaret.translate(super.tl.x, super.tl.y);
        final Dimension size = this.O.getSize();
        final int n = posicioReal.x + buscaTokensVTipus.c.width + 100;
        final int n2 = posicioReal.y + buscaTokensVTipus.c.height / 2 - size.height / 2;
        final int n3 = n - n % 50;
        final int n4 = viewportRectangle.x + viewportRectangle.width - size.width - 10;
        final int n5 = viewportRectangle.y + viewportRectangle.height - size.height - 10;
        final int n6 = viewportRectangle.y + 10;
        final int min = Math.min(n3, n4);
        int n7 = Math.max(Math.min(n2, n5), n6);
        if (posicioCaret.x >= min - 25 && posicioCaret.x <= min + size.width + 25) {
            if (n7 < viewportRectangle.y + viewportRectangle.height / 2) {
                n7 = Math.min(posicioReal.y + buscaTokensVTipus.c.height + 25, n5);
            }
            else {
                n7 = Math.max(posicioReal.y - size.height - 25, n6);
            }
        }
        final Point location = this.O.getLocation();
        if (location.x != min || location.y != n7) {
            this.O.setLocation(min, n7);
            this.O.validate();
        }
    }
    
    private final void FALSE() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.ProcessaOrdre == null) ? (FormulaEditorCalc.ProcessaOrdre = I("CapsaSi")) : FormulaEditorCalc.ProcessaOrdre);
        final CapsaSi capsaSi = (CapsaSi)buscaPosicioTipus.c;
        if (capsaSi != null) {
            this.push_backup(super.curpos, buscaPosicioTipus.c);
            if (buscaPosicioTipus.x == capsaSi.nfills - capsaSi.extra) {
                final BoxPosition boxPosition = buscaPosicioTipus;
                --boxPosition.x;
            }
            final int n = 2 * (buscaPosicioTipus.x / 2);
            final MultilineBox multilineBox = new MultilineBox();
            multilineBox.inicialitzaFills();
            capsaSi.Afegir(multilineBox, n + 2, this);
            final TokensBox tokensBox = new TokensBox();
            tokensBox.AfegirNoCalc(new ArgumentBox("condition"));
            capsaSi.Afegir(tokensBox, n + 2, this);
            this.setCaret(capsaSi.PosSeg_Fill(n + 1));
        }
    }
    
    private final void InsertString() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.ProcessaOrdre == null) ? (FormulaEditorCalc.ProcessaOrdre = I("CapsaSi")) : FormulaEditorCalc.ProcessaOrdre);
        final CapsaSi capsaSi = (CapsaSi)buscaPosicioTipus.c;
        if (capsaSi != null && buscaPosicioTipus.x >= 2 && buscaPosicioTipus.x < capsaSi.nfills - 1) {
            this.push_backup(super.curpos, buscaPosicioTipus.c);
            capsaSi.Treure(2 * (buscaPosicioTipus.x / 2), 2 * (buscaPosicioTipus.x / 2) + 2, this);
            this.setCaret(capsaSi.PosSeg_Fill(2 * (buscaPosicioTipus.x / 2) - 1));
        }
    }
    
    private final void MarcaErrorsWarnings() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.ProcessaOrdre == null) ? (FormulaEditorCalc.ProcessaOrdre = I("CapsaSi")) : FormulaEditorCalc.ProcessaOrdre);
        final CapsaSi capsaSi = (CapsaSi)buscaPosicioTipus.c;
        if (capsaSi != null && (capsaSi.nfills - capsaSi.extra) % 2 == 0) {
            this.push_backup(super.curpos, buscaPosicioTipus.c);
            final MultilineBox multilineBox = new MultilineBox();
            multilineBox.inicialitzaFills();
            capsaSi.Afegir(multilineBox, capsaSi.nfills - capsaSi.extra, this);
            this.setCaret(capsaSi.PosSeg_Fill(capsaSi.nfills - capsaSi.extra - 2));
        }
    }
    
    private final void NovaCapsa() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.ProcessaOrdre == null) ? (FormulaEditorCalc.ProcessaOrdre = I("CapsaSi")) : FormulaEditorCalc.ProcessaOrdre);
        final CapsaSi capsaSi = (CapsaSi)buscaPosicioTipus.c;
        if (capsaSi != null && (capsaSi.nfills - capsaSi.extra) % 2 == 1) {
            this.push_backup(super.curpos, buscaPosicioTipus.c);
            capsaSi.Treure(capsaSi.nfills - capsaSi.extra - 1, this);
            this.resetPosition();
        }
    }
    
    private final void ObteOmegaStrings2(final int n, final char c) {
        String string = "<mfenced open=\"{\" close=\"}\"><mtable align=\"center\">";
        for (int i = 0; i < n; ++i) {
            string += "<mtr><mtd/></mtr>";
        }
        this.InsertString(string + "</mtable></mfenced>");
    }
    
    private final void PosAnt() {
        if (super.B == null) {
            final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.RespostaPlotters == null) ? (FormulaEditorCalc.RespostaPlotters = I("CapsaParentesis")) : FormulaEditorCalc.RespostaPlotters);
            if (buscaTokensVTipus.c instanceof TokensVBox) {
                this.push_backup(super.curpos, buscaTokensVTipus.c);
                ((TokensVBox)buscaTokensVTipus.c).Treure(buscaTokensVTipus.x, this);
                this.resetPosition();
            }
        }
    }
    
    private final void PosAnt_Fora(final boolean b, final boolean b2) {
        this.unselectArea();
        final BoxPosition buscaTokensVTipus = super.curpos.BuscaTokensVTipus((FormulaEditorCalc.RespostaPlotters == null) ? (FormulaEditorCalc.RespostaPlotters = I("CapsaParentesis")) : FormulaEditorCalc.RespostaPlotters);
        if (buscaTokensVTipus.c instanceof TokensVBox) {
            this.push_backup(super.curpos, buscaTokensVTipus.c);
            final TokensVBox tokensVBox = (TokensVBox)buscaTokensVTipus.c;
            final int n = b ? 0 : 1;
            if (!b2) {
                tokensVBox.AfegirCapsaPredeterminada(buscaTokensVTipus.x + n, this);
            }
            else {
                tokensVBox.Afegir(this.parse("<maction actiontype=\"argument\"><mtext/></maction><mo>=</mo><maction actiontype=\"argument\"><mtext/></maction>"), buscaTokensVTipus.x + n, this);
            }
            this.setCaret(tokensVBox.PosSeg_Fill(buscaTokensVTipus.x - 1 + n));
        }
    }
    
    public final void novaMatriu(final int n, final int n2, final char c) {
        if (super.B != null) {
            final AbstractBox contents = super.B.getContents();
            if (contents instanceof MatrixBox) {
                final MatrixBox matrixBox = (MatrixBox)contents;
                matrixBox.tipus = c;
                matrixBox.tipusd = c;
                matrixBox.ActualitzaRect(this);
                return;
            }
        }
        this.InsertString(this.script(MatrixBox.NovaCapsa(n, n2, c)));
    }
    
    public final void nouVector(final int n, final char c) {
        String string = "<mfenced open=\"[\" close=\"]\"><mrow>";
        for (int i = 0; i < n; ++i) {
            String s;
            if (i == 0) {
                s = string + "<mo>caret</mo>";
            }
            else {
                s = string + "<mo>,</mo>";
            }
            string = s + "<maction actiontype=\"argument\"><mtext/></maction>";
        }
        this.InsertString(string + "</mrow></mfenced>");
    }
    
    private final void PosSeg_Fill() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.TRUE == null) ? (FormulaEditorCalc.TRUE = I("CapsaComanda")) : FormulaEditorCalc.TRUE).BuscaPosicioTipus((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
        final BoxPosition buscaPosicioTipus2 = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.Treure == null) ? (FormulaEditorCalc.Treure = I("CapsaLlibreria")) : FormulaEditorCalc.Treure);
        if (buscaPosicioTipus2.c == null) {
            buscaPosicioTipus.posicioPare();
            final AbstractBox c = buscaPosicioTipus.c;
            this.push_backup(super.curpos, c);
            final int x = buscaPosicioTipus.x;
            final AbstractBox treureNoCalc = c.TreureNoCalc(x);
            CapsaComanda.eliminaResultats(this, treureNoCalc);
            treureNoCalc.netejaErrors();
            final CapsaLlibreria capsaLlibreria = new CapsaLlibreria();
            capsaLlibreria.inicialitzaFills(this.parse("library"), treureNoCalc);
            c.Afegir(capsaLlibreria, x, this);
        }
        else {
            buscaPosicioTipus2.posicioPare();
            final AbstractBox c2 = buscaPosicioTipus2.c;
            this.push_backup(super.curpos, c2);
            final int x2 = buscaPosicioTipus2.x;
            c2.Afegir(c2.TreureNoCalc(x2).fill[1], x2, this);
        }
        this.resetPosition();
        this.repaint();
        this.requestFocus();
    }
    
    private final void PosSeg_Fora() {
        final BoxPosition buscaPosicioTipus = super.curpos.BuscaPosicioTipus((FormulaEditorCalc.TRUE == null) ? (FormulaEditorCalc.TRUE = I("CapsaComanda")) : FormulaEditorCalc.TRUE);
        if (buscaPosicioTipus.x > 0) {
            this.setCaret(buscaPosicioTipus.c.PosAnt_Fora(null));
        }
        final CapsaComanda capsaComanda = (CapsaComanda)super.curpos.BuscaPareTipus((FormulaEditorCalc.TRUE == null) ? (FormulaEditorCalc.TRUE = I("CapsaComanda")) : FormulaEditorCalc.TRUE);
        if (capsaComanda != null) {
            this.push_backup(super.curpos, capsaComanda);
            capsaComanda.comentat(this);
            this.repaint();
        }
    }
    
    private final void PosicioCaret() {
        this.setSelectionStyle(1, true);
    }
    
    private final void PosicioReal() {
        this.setSelectionStyle(1, false);
    }
    
    public final boolean ProcessaOrdre(final String s) {
        this.dialogShown = false;
        if (Utils.equals(s, "compute") || Utils.equals(s, "calcula_text")) {
            this.ActualitzaRect();
            this.calcButtonVisible = true;
        }
        else if (Utils.equals(s, "novaComanda")) {
            this.novaComanda();
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "newblock")) {
            this.BuscaPareTipus(true);
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "newsession")) {
            if (new MessageBox(this.getMainFrame(), "New session", "Do you want a new session?", 12).showDialog().equals("Yes")) {
                this.plotterManager.newSession();
                this.newSession();
                this.repaint();
            }
            this.dialogShown = true;
        }
        else if (Utils.equals(s, "addelse")) {
            this.MarcaErrorsWarnings();
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "removeelse")) {
            this.NovaCapsa();
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "addelseif")) {
            this.FALSE();
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "removeelseif")) {
            this.InsertString();
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "limit")) {
            final AbstractBox contents;
            if (super.B == null || !((contents = super.B.getContents()) instanceof CapsaLimit)) {
                return this.ProcessaOrdre("plain_limit");
            }
            final CapsaLimit capsaLimit = (CapsaLimit)contents;
            capsaLimit.setTipus(0);
            capsaLimit.Composa(this);
            capsaLimit.ActualitzaRect(this);
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "leftlimit")) {
            final AbstractBox contents2;
            if (super.B == null || !((contents2 = super.B.getContents()) instanceof CapsaLimit)) {
                return this.ProcessaOrdre("plain_leftlimit");
            }
            final CapsaLimit capsaLimit2 = (CapsaLimit)contents2;
            capsaLimit2.setTipus(-1);
            capsaLimit2.Composa(this);
            capsaLimit2.ActualitzaRect(this);
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "rightlimit")) {
            final AbstractBox contents3;
            if (super.B == null || !((contents3 = super.B.getContents()) instanceof CapsaLimit)) {
                return this.ProcessaOrdre("plain_rightlimit");
            }
            final CapsaLimit capsaLimit3 = (CapsaLimit)contents3;
            capsaLimit3.setTipus(1);
            capsaLimit3.Composa(this);
            capsaLimit3.ActualitzaRect(this);
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "stop")) {
            this.stop();
        }
        else if (Utils.equals(s, "comment")) {
            this.PosSeg_Fora();
        }
        else if (Utils.equals(s, "localcomment")) {
            this.PosicioCaret();
        }
        else if (Utils.equals(s, "treucomentarilocal")) {
            this.PosicioReal();
        }
        else if (Utils.equals(s, "library")) {
            this.PosSeg_Fill();
        }
        else if (Utils.equals(s, "removeresults")) {
            CapsaComanda.eliminaResultats(this, super.lacapsa);
            this.llistaErrors.removeAllItems();
            this.ActualitzaRect.clear();
            super.lacapsa.netejaErrors();
            this.resetPosition();
            this.changeNotification();
            this.repaint();
        }
        else if (Utils.equals(s, "argument")) {
            final String inputDialog = this.inputDialog("Argument", "Label", this.edicioArgument);
            if (inputDialog != null) {
                this.edicioArgument = inputDialog;
                this.InsertString("<maction actiontype=\"argument\"><mtext>" + XMLParser.string2CharData(this.edicioArgument.replace(' ', ' ')) + "</mtext></maction>");
                this.repaint();
            }
        }
        else if (Utils.equals(s, "logoicon")) {
            super.context.showDocumentNewWindow("http://www.wiris.com");
        }
        else if (Utils.equals(s, "vertlist")) {
            final int dim = this.getDim("Vertical list", "Rows", this.systemDim, 1000);
            if (dim == -1) {
                return true;
            }
            this.ObteOmegaStrings2(this.systemDim = dim, 'B');
            this.MouScrollPerVeureCaret();
        }
        else if (Utils.equals(s, "solvesystem")) {
            final int dim2 = this.getDim("Equations System", "Equations", this.systemDim, 1000);
            if (dim2 == -1) {
                return true;
            }
            this.systemDim = dim2;
            String string = "<mrow><mi>solve</mi><mfenced open=\"{\" close=\"}\"><mtable align=\"center\">";
            for (int i = 0; i < this.systemDim; ++i) {
                string += "<mtr><mtd><maction actiontype=\"argument\"><mtext/></maction><mo>=</mo><maction actiontype=\"argument\"><mtext/></maction></mtd></mtr>";
            }
            this.InsertString(string + "</mtable></mfenced></mrow>");
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (Utils.equals(s, "removeitem")) {
            this.PosAnt();
            this.repaint();
        }
        else if (Utils.equals(s, "additemdown")) {
            this.PosAnt_Fora(false, false);
            this.repaint();
        }
        else if (Utils.equals(s, "additemup")) {
            this.PosAnt_Fora(true, false);
            this.repaint();
        }
        else if (Utils.equals(s, "addeqdown")) {
            this.PosAnt_Fora(false, true);
            this.repaint();
        }
        else if (Utils.equals(s, "addequp")) {
            this.PosAnt_Fora(true, true);
            this.repaint();
        }
        else if (Utils.equals(s, "choice")) {
            this.PosAnt_Fora(true, true);
            this.repaint();
        }
        else if (s.endsWith("matrix") && !Utils.equals(s, "identitymatrix")) {
            if (super.B == null || !(super.B.getContents() instanceof MatrixBox)) {
                String s2;
                if (s.charAt(0) == 'v') {
                    s2 = "determinant";
                }
                else if (s.charAt(0) == 'X') {
                    s2 = "Table";
                }
                else {
                    s2 = "matrix";
                }
                String[] inputDialog2 = { "" + this.matrixRows, "" + this.matrixCols };
                int matrixRows = this.matrixRows;
                int matrixCols = this.matrixCols;
                int j = 1;
                while (j != 0) {
                    inputDialog2 = this.inputDialog(s2, new String[] { "Rows", "Columns" }, inputDialog2);
                    if (inputDialog2 == null) {
                        return true;
                    }
                    try {
                        matrixRows = Integer.parseInt(inputDialog2[0]);
                        matrixCols = Integer.parseInt(inputDialog2[1]);
                        if (matrixCols <= 0 || matrixRows <= 0 || matrixRows * matrixCols > 1000) {
                            throw new NumberFormatException();
                        }
                        j = 0;
                    }
                    catch (NumberFormatException ex) {
                        final MessageBox messageBox = new MessageBox(this.getMainFrame(), "Error", Utils.parameter("The dimensionsare not correct", " (" + inputDialog2[0] + ", " + inputDialog2[1] + ") "), 1);
                        this.dialogShown = true;
                        messageBox.show();
                    }
                }
                this.matrixRows = matrixRows;
                this.matrixCols = matrixCols;
            }
            this.novaMatriu(this.matrixCols, this.matrixRows, s.charAt(0));
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else if (s.endsWith("vector")) {
            final int dim3 = this.getDim("vector", "Dimension", this.vectorDim, 1000);
            if (dim3 == -1) {
                return true;
            }
            this.nouVector(this.vectorDim = dim3, s.charAt(0));
            this.MouScrollPerVeureCaret();
            this.repaint();
        }
        else {
            if (!Utils.equals(s, "no_translate")) {
                boolean b = super.ProcessaOrdre(s);
                if (!b) {
                    b = super.ProcessaOrdre(FormulaToolBarFactory.getInstance(this.getResourceProvider()).getCommand(s));
                }
                return b;
            }
            if (super.B != null) {
                final AbstractBox contents4 = super.B.getContents();
                if (contents4 != null) {
                    contents4.className = "no_translate";
                }
            }
        }
        this.requestFocus();
        return true;
    }
    
    public final void setDesign(final boolean b) {
    }
    
    public final void DemanaInsertaTex() {
        final MessageBox messageBox = new MessageBox(this.getMainFrame(), "Insertar", 3);
        messageBox.add("Texto Wiris-TeX a insertar");
        final TextField textField = new TextField(super.M, 40);
        messageBox.add(textField);
        if (messageBox.showDialog().equals("Ok")) {
            this.InsertString(super.M = textField.getText());
        }
    }
    
    public static final String cadenaOmega(final AbstractBox abstractBox) {
        final WirisBoxScripting wirisBoxScripting = new WirisBoxScripting();
        abstractBox.script(wirisBoxScripting);
        return wirisBoxScripting.toString();
    }
    
    private String inputDialog(final String s, final String s2, final String s3) {
        final String[] inputDialog = this.inputDialog(s, new String[] { s2 }, new String[] { s3 });
        if (inputDialog == null) {
            return null;
        }
        return inputDialog[0];
    }
    
    private String[] inputDialog(final String s, final String[] array, final String[] array2) {
        final MessageBox messageBox = new MessageBox(this.getMainFrame(), s, 3);
        final TextField[] array3 = new TextField[array.length];
        for (int i = 0; i < array.length; ++i) {
            String s2;
            if (i < array2.length) {
                s2 = array2[i];
            }
            else {
                s2 = "";
            }
            array3[i] = new TextField(s2, 7);
            messageBox.add(array[i], array3[i]);
        }
        String[] array4;
        if ("Ok".equals(messageBox.showDialog())) {
            array4 = new String[array.length];
            for (int j = 0; j < array.length; ++j) {
                array4[j] = array3[j].getText();
            }
        }
        else {
            array4 = null;
        }
        this.dialogShown = true;
        messageBox.dispose();
        return array4;
    }
    
    private int getDim(final String s, final String s2, final int n, final int n2) {
        int i = 1;
        int int1 = -1;
        while (i != 0) {
            final String[] inputDialog = this.inputDialog(s, new String[] { s2 }, new String[] { "" + n });
            if (inputDialog == null) {
                return -1;
            }
            try {
                int1 = Integer.parseInt(inputDialog[0]);
                if (int1 <= 0 || int1 > n2) {
                    throw new NumberFormatException();
                }
                i = 0;
            }
            catch (NumberFormatException ex) {
                final MessageBox messageBox = new MessageBox(this.getMainFrame(), "Error", Utils.parameter("The dimensionis not correct", " " + inputDialog[0] + " "), 1);
                this.dialogShown = true;
                messageBox.show();
            }
        }
        return int1;
    }
    
    public final void onPaint(final Object o, final Graphics graphics, final boolean b) {
        if (b && this.O != null) {
            this.O.setBackground(super.colors[11]);
        }
    }
    
    public static final int opcions(final FormulaEditorCalc formulaEditorCalc) {
        int n = -2147483645;
        if (formulaEditorCalc != null && formulaEditorCalc.BuscaPareTipus) {
            n |= 0x40000000;
        }
        return n;
    }
    
    public static final String[] evaluate(final FormulaEditorCalc formulaEditorCalc, final OmegaClient omegaClient, final String[] array) {
        return omegaClient.evaluate(array, opcions(formulaEditorCalc));
    }
    
    public final BoxPosition InsertString(final Formula formula, BoxPosition boxPosition, final AbstractBox abstractBox) {
        if (boxPosition.c.isStyle(128) && !this.ProcessaOrdre(abstractBox)) {
            return boxPosition;
        }
        if (abstractBox instanceof InterfaceBox) {
            return this.InsertString(formula, boxPosition, abstractBox.fill[0]);
        }
        if (abstractBox instanceof CapsaLlibreria) {
            final BoxPosition searchTokensVPosition = boxPosition.searchTokensVPosition((FormulaEditorCalc.PosSeg_Fill == null) ? (FormulaEditorCalc.PosSeg_Fill = I("InterfaceBox")) : FormulaEditorCalc.PosSeg_Fill);
            if (searchTokensVPosition != null) {
                final BoxPosition boxPosition2 = searchTokensVPosition;
                ++boxPosition2.x;
                FormulaEditor.push_backup(formula, boxPosition, searchTokensVPosition.c);
                searchTokensVPosition.c.Afegir(abstractBox, searchTokensVPosition.x, formula);
                return searchTokensVPosition;
            }
            return boxPosition;
        }
        else {
            if (abstractBox instanceof CapsaComanda || abstractBox instanceof CapsaComandes) {
                final TokensBox tokensBox = new TokensBox();
                tokensBox.inicialitzaFills(abstractBox);
                return this.InsertString(formula, boxPosition, tokensBox);
            }
            if (abstractBox instanceof TokensVBox || abstractBox instanceof TokensBox) {
                for (int i = 0; i < abstractBox.nfills; ++i) {
                    if (abstractBox.fill[i] instanceof CapsaLlibreria) {
                        abstractBox.fill[i] = abstractBox.fill[i].fill[1];
                    }
                }
                if (abstractBox.fill[0] instanceof CapsaComanda) {
                    final BoxPosition searchTokensVPosition2 = boxPosition.searchTokensVPosition((FormulaEditorCalc.TreureNoCalc == null) ? (FormulaEditorCalc.TreureNoCalc = I("CommandsOwner")) : FormulaEditorCalc.TreureNoCalc);
                    if (searchTokensVPosition2 != null) {
                        FormulaEditor.push_backup(formula, boxPosition, searchTokensVPosition2.c);
                        while (abstractBox.nfills > 0) {
                            boxPosition = this.InsertString(formula, boxPosition, abstractBox.TreureNoCalc(0).fill[0]);
                            FormulaEditor.I(super.N);
                            boxPosition = FormulaEditor.I(formula, boxPosition);
                            FormulaEditor.I(super.N);
                        }
                        return boxPosition;
                    }
                    final BoxPosition searchTokensVPosition3 = boxPosition.searchTokensVPosition((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
                    if (searchTokensVPosition3 != null) {
                        FormulaEditor.push_backup(formula, boxPosition, searchTokensVPosition3.c);
                        if (searchTokensVPosition3.searchPosition((FormulaEditorCalc.Treure == null) ? (FormulaEditorCalc.Treure = I("CapsaLlibreria")) : FormulaEditorCalc.Treure) != null) {
                            CapsaComanda.eliminaResultats(null, abstractBox);
                        }
                        if (searchTokensVPosition3.x < searchTokensVPosition3.c.nfills && searchTokensVPosition3.c.fill[searchTokensVPosition3.x].isEmpty(formula)) {
                            searchTokensVPosition3.c.TreureNoCalc(searchTokensVPosition3.x);
                            final BoxPosition boxPosition3 = searchTokensVPosition3;
                            --boxPosition3.x;
                        }
                        while (abstractBox.nfills > 0) {
                            final BoxPosition boxPosition4 = searchTokensVPosition3;
                            ++boxPosition4.x;
                            searchTokensVPosition3.c.Afegir(abstractBox.TreureNoCalc(0), searchTokensVPosition3.x, formula);
                        }
                        return searchTokensVPosition3;
                    }
                    return boxPosition;
                }
                else if (abstractBox.fill[0] instanceof CapsaComandes) {
                    final BoxPosition searchPosition = boxPosition.searchPosition((FormulaEditorCalc.TreureNoCalc == null) ? (FormulaEditorCalc.TreureNoCalc = I("CommandsOwner")) : FormulaEditorCalc.TreureNoCalc);
                    if (searchPosition != null) {
                        FormulaEditor.push_backup(formula, boxPosition, searchPosition.c);
                        while (abstractBox.nfills > 0) {
                            boxPosition = this.InsertString(formula, boxPosition, abstractBox.TreureNoCalc(0).fill[0]);
                            FormulaEditor.I(super.N);
                        }
                        return boxPosition;
                    }
                    final BoxPosition searchPosition2 = boxPosition.searchPosition((FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora);
                    if (searchPosition2 != null) {
                        searchPosition2.posicioPare();
                        if (searchPosition2.c != null && searchPosition2.c instanceof TokensVBox) {
                            FormulaEditor.push_backup(formula, boxPosition, searchPosition2.c);
                            if (searchPosition2.x < searchPosition2.c.nfills && searchPosition2.c.fill[searchPosition2.x].isEmpty(formula)) {
                                searchPosition2.c.TreureNoCalc(searchPosition2.x);
                                final BoxPosition boxPosition5 = searchPosition2;
                                --boxPosition5.x;
                            }
                            while (abstractBox.nfills > 0) {
                                final BoxPosition boxPosition6 = searchPosition2;
                                ++boxPosition6.x;
                                searchPosition2.c.Afegir(abstractBox.TreureNoCalc(0), searchPosition2.x, formula);
                            }
                            searchPosition2.c = searchPosition2.c.fill[searchPosition2.x];
                            searchPosition2.x = searchPosition2.c.p_en_pare;
                            return searchPosition2;
                        }
                    }
                }
            }
            return super.InsertString(formula, boxPosition, abstractBox);
        }
    }
    
    protected final boolean ProcessaOrdre(final AbstractBox abstractBox) {
        if (abstractBox instanceof TextBox) {
            return true;
        }
        if (abstractBox instanceof SpaceBox) {
            return true;
        }
        if (abstractBox instanceof MarkupBox) {
            return true;
        }
        if (abstractBox instanceof TokensBox || abstractBox instanceof TokensVBox) {
            for (int i = 0; i < abstractBox.nfills; ++i) {
                if (!this.ProcessaOrdre(abstractBox.fill[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public final boolean espotborrardirectament(final AbstractBox abstractBox) {
        return super.espotborrardirectament(abstractBox) || abstractBox instanceof SpecialSymbolBox;
    }
    
    protected final boolean I(final MouseEvent mouseEvent) {
        boolean i = super.I(mouseEvent);
        if ((super.version == null || !super.version.equals("1.0")) && mouseEvent.isPopupTrigger() && this.tipusToolbar.equals("floating")) {
            this.F();
            this.addMenuSeparator();
            this.addMenuItem("Toolbar", "floatingToolbar", super.context, true);
            this.addMenuItem("Error window", "errorWindow", super.context, true);
            i = true;
        }
        return i;
    }
    
    public final void notifyChangePosition(final BoxPosition boxPosition, final BoxPosition boxPosition2) {
        super.notifyChangePosition(boxPosition, boxPosition2);
        this.BuscaTokensVTipus();
    }
    
    public final void changeNotification() {
        int size;
        AbstractBox abstractBox;
        int nfills;
        int n;
        for (size = this.NovaCapsa.size(), abstractBox = super.lacapsa.fill[0], nfills = abstractBox.nfills, n = 0; n < size && n < nfills && this.NovaCapsa.elementAt(n) == abstractBox.fill[n]; ++n) {}
        int n2;
        int n3;
        for (n2 = size - 1, n3 = nfills - 1; n2 >= 0 && n3 >= 0 && this.NovaCapsa.elementAt(n2) == abstractBox.fill[n3]; --n2, --n3) {}
        for (int i = n; i <= n2; ++i) {
            this.firePropertyChange("groupRemoved", Boolean.TRUE, new Integer(i));
        }
        for (int j = n; j <= n3; ++j) {
            this.firePropertyChange("groupAdded", Boolean.TRUE, new Integer(j));
        }
        this.NovaCapsa.removeAllElements();
        for (int k = 0; k < nfills; ++k) {
            this.NovaCapsa.addElement(abstractBox.fill[k]);
        }
        super.changeNotification();
        this.firePropertyChange("contentChanged", Boolean.TRUE, Boolean.FALSE);
    }
    
    protected final void F() {
        final FormulaEditor l = FormulaEditor.L;
        super.F();
        if (this != l) {
            this.llistaErrors.removeItemListener((ItemListener)l);
            this.llistaErrors.addItemListener(this);
            this.firePropertyChange("activeFormula", l, this);
            if (l != null && l instanceof FormulaEditorCalc) {
                ((FormulaEditorCalc)l).firePropertyChange("activeFormula", l, this);
            }
        }
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        this.imposeWidth();
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final AbstractBox trimMainBox(final AbstractBox abstractBox) {
        if (abstractBox instanceof InterfaceBox || abstractBox instanceof CapsaComandes) {
            return abstractBox;
        }
        return super.trimMainBox(abstractBox);
    }
    
    protected final AbstractSelection I(final AbstractSelection abstractSelection, final AbstractSelection abstractSelection2) {
        if (this.isDesign()) {
            return abstractSelection;
        }
        if (abstractSelection == null) {
            return null;
        }
        final AbstractBox mama = abstractSelection.getMama();
        if (mama != null && mama.isStyle(2)) {
            return abstractSelection2;
        }
        return abstractSelection;
    }
    
    public final void setInitialString(final String s, final boolean b, final boolean b2, final boolean b3, final String s2, final boolean b4) {
        if (b4) {
            FormulaEditorParse.I(this, s, b, b2, b3, s2);
        }
        else {
            this.setString(s, b3);
            if (b) {
                this.calculateAll(true);
            }
            this.doAction(s2);
            this.repaint();
            this.MouScrollPerVeureCaret();
            this.inicialitza();
            if (b2) {
                this.comprovaResultats(true, true);
                this.repaint();
            }
        }
    }
    
    public final void setString(final String s, final boolean b) {
        super.setString(s, b);
        this.imposeWidth();
    }
    
    public final void waitCursor() {
        if (this.getComponent().getCursor().getType() != 3) {
            this.backupCursor = this.getComponent().getCursor();
        }
        this.waitCursorState = true;
        super.setCursor(new Cursor(3));
    }
    
    public final void setCursor(final Cursor cursor) {
        if (this.waitCursorState) {
            if (cursor.getType() != 3) {
                this.backupCursor = cursor;
            }
        }
        else {
            super.setCursor(cursor);
        }
    }
    
    protected final boolean I(final InputEvent inputEvent) {
        if (this.calculator.isCalculating()) {
            this.waitCursor();
            return true;
        }
        return inputEvent.getWhen() < this.igonreEventTime + FormulaEditorCalc.FALSE;
    }
    
    public final int getBlocksCount() {
        if (super.lacapsa instanceof InterfaceBox) {
            return ((InterfaceBox)super.lacapsa).getBlockCount();
        }
        return 0;
    }
    
    public final AbstractBox getBlock(final int n) {
        if (super.lacapsa instanceof InterfaceBox) {
            return ((InterfaceBox)super.lacapsa).getBlock(n);
        }
        return null;
    }
    
    public final void clearAllPlotters() {
        for (int blocksCount = this.getBlocksCount(), i = 0; i < blocksCount; ++i) {
            final AbstractBox block = this.getBlock(i);
            if (block instanceof CapsaComandes) {
                ((CapsaComandes)block).setPlotters(null);
            }
        }
        this.plotterManager.newSession();
    }
    
    public final void setSeed(final int seed) {
        this.calculator.setSeed(seed);
    }
    
    public final PlotterFrameManager getPlotterManager() {
        return this.plotterManager;
    }
    
    public final void setLoadedPLotters(final Vector vector) {
        this.plotterManager.clear();
        for (int i = 0; i < vector.size(); ++i) {
            final PlotterPanel plotterPanel = vector.get(i);
            final CapsaComandes capsaComandes = this.getCapsaComandes(plotterPanel.getGroup());
            if (capsaComandes != null) {
                final PlotterCanvas plotComp = plotterPanel.getPlotComp();
                if (plotterPanel instanceof Dialog3d) {
                    ((Canvas3d)plotComp).box = capsaComandes;
                }
                else {
                    ((PlotCanvas)plotComp).capsaComandes = capsaComandes;
                }
                this.plotterManager.add(plotterPanel, capsaComandes);
            }
        }
    }
    
    public final CapsaComandes getCapsaComandes(final int n) {
        AbstractBox lacapsa = super.lacapsa;
        System.out.println("fill: " + lacapsa.fill);
        while (!(lacapsa instanceof TokensVBox) && lacapsa.fill[0] != null) {
            lacapsa = lacapsa.fill[0];
        }
        if (n <= lacapsa.fill.length) {
            return (CapsaComandes)lacapsa.fill[n - 1];
        }
        return null;
    }
    
    public final boolean exists(final AbstractBox abstractBox) {
        AbstractBox lacapsa;
        for (lacapsa = super.lacapsa; !(lacapsa instanceof TokensVBox); lacapsa = lacapsa.fill[0]) {}
        for (int i = 0; i < lacapsa.fill.length; ++i) {
            if (lacapsa.fill[i].equals(abstractBox)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean getWithDocBase() {
        return this.overrideDocumentBase || super.version == null || !super.version.equals("1.0");
    }
    
    public final void calculateAll(final boolean b) {
        if (b) {
            new Thread(this).start();
        }
        else {
            this.run();
        }
    }
    
    public final boolean isEditable() {
        return super.isEditable() && !this.loading();
    }
    
    public final boolean loading() {
        return this.calculator.loading();
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("loaded")) {
            this.calculator.I.getPropertyChangeSupport().removePropertyChangeListener(this);
            this.repaint();
            if (this.D() && this.getComponent().hasFocus()) {
                super.K.setVisible(true);
            }
        }
    }
    
    public final void paintWirisLoading(final Graphics graphics) {
        if (this.sponsor == null) {
            this.sponsor = this.getResourceProvider().getInternalImage("Icones/wiris.png");
        }
        if (this.sponsor == null) {
            return;
        }
        final int n = 305;
        final int n2 = 64;
        final Rectangle bounds = this.getComponent().getBounds();
        this.paint(graphics);
        try {
            graphics.setColor(new Color(255, 255, 255, 50));
            graphics.fillRect(0, 0, bounds.width, bounds.height);
            graphics.setColor(new Color(0, 0, 0, 50));
            graphics.fillRect(0, 0, bounds.width, bounds.height);
            graphics.drawImage(this.sponsor, (bounds.width - n) / 2, (bounds.height - n2) / 2, this.getComponent());
        }
        catch (Throwable t) {}
    }
    
    public final void run() {
        for (int nfills = super.lacapsa.fill[0].nfills, i = 0; i < nfills; ++i) {
            final AbstractBox abstractBox = super.lacapsa.fill[0].fill[i];
            if (abstractBox instanceof CapsaComandes) {
                final CapsaComandes capsaComandes = (CapsaComandes)abstractBox;
                capsaComandes.calculant = true;
                this.repaint();
                this.AfegirCapsaPredeterminada(capsaComandes, false);
                this.calculator.waitTerminate();
            }
        }
    }
    
    public final boolean isCalculating() {
        return this.calculator.isCalculating() || this.pendingSetContent;
    }
    
    public final void doAction(final String s) {
    }
    
    public final String getSelection() {
        if (super.B != null) {
            return this.script(super.B);
        }
        return null;
    }
    
    public final Rectangle getBoxEditorRectangle(final Point point) {
        return null;
    }
    
    public final Rectangle getBoxEditorRectangle() {
        return null;
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
        FormulaEditorCalc.showKernel = false;
        FormulaEditorCalc.DemanaInsertaTex = true;
        Latex2Box.setLatex2Box((FormulaEditorCalc.ObteOmegaStrings2 == null) ? (FormulaEditorCalc.ObteOmegaStrings2 = I("Latex2BoxCalc")) : FormulaEditorCalc.ObteOmegaStrings2);
        MathML2Box.setMathMLToBox((FormulaEditorCalc.PosAnt_Fora == null) ? (FormulaEditorCalc.PosAnt_Fora = I("MathML2BoxCalc")) : FormulaEditorCalc.PosAnt_Fora);
        XMLBoxUtils.setTopBoxClasses(new Class[] { (FormulaEditorCalc.PosSeg_Fill == null) ? (FormulaEditorCalc.PosSeg_Fill = I("InterfaceBox")) : FormulaEditorCalc.PosSeg_Fill, (FormulaEditorCalc.PosSeg_Fora == null) ? (FormulaEditorCalc.PosSeg_Fora = I("CapsaComandes")) : FormulaEditorCalc.PosSeg_Fora });
        XMLBundle.setValue("PE_DIAL_COLOR", "Colors");
        XMLBundle.setValue("PROPERTY", "Property");
    }
}
