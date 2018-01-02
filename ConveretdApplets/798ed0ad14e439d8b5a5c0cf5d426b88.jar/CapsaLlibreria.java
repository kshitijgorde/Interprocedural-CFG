import java.util.Vector;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaLlibreria extends AbstractBox implements CommandsOwner
{
    public boolean amagat;
    public boolean max_width;
    int[] BuscaPosicioTipus;
    int[] BuscaTokensVTipus;
    int Composa;
    BoxPosition ImposaColor;
    static Class PosicioMesPropera;
    static Class addElement;
    
    public CapsaLlibreria() {
        this.amagat = false;
        this.max_width = false;
        this.BuscaPosicioTipus = new int[3];
        this.BuscaTokensVTipus = new int[3];
    }
    
    private void inicialitza(final AbstractBox abstractBox, final AbstractBox abstractBox2) {
        this.AfegirNoCalc(abstractBox);
        abstractBox.ImposaColor(Color.orange);
        if (abstractBox2 != null) {
            this.AfegirNoCalc(abstractBox2);
        }
    }
    
    public final void inicialitzaFills(final AbstractBox[] array) {
        if (array.length == 2) {
            this.inicialitza(array[0], array[1]);
            return;
        }
        this.inicialitza(array[1], array[2]);
        if (array[0].plainString().equals("0")) {
            this.amaga(null);
        }
    }
    
    public final void enCalculRect() {
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        if (super.nfills >= 1) {
            super.width = 2 * this.em(0.7894737f) + 2 * this.em(0.2631579f) + super.fill[0].width;
            super.height = Math.max(super.fill[0].height, this.em(0.7894737f));
            this.Composa = super.height;
            super.baseline = 0;
            super.fill[0].x = this.em(0.7894737f) + this.em(0.2631579f);
            super.fill[0].y = 0;
            if (super.nfills >= 2) {
                super.width = Math.max(super.width, super.fill[1].width + 2 * this.em(0.2631579f));
                super.fill[1].y = super.height + this.em(0.2631579f);
                super.height += super.fill[1].height + 2 * this.em(0.2631579f);
                super.fill[1].x = this.em(0.2631579f);
            }
            if (this.max_width && super.recommendedWidth != null) {
                super.width = Math.max(super.width, super.recommendedWidth.toUserUnit(super.EM, boxComponent.getDPI()));
            }
        }
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        if (GraphicsUtils.isFileGraphics(graphics)) {
            GraphicsUtils.setLineWidth(graphics, super.EM * 1.0f / 19.0f);
        }
        boxComponent.setColor(graphics, Color.orange);
        graphics.drawRect(0, 0, super.width, super.height);
        graphics.drawLine(this.em(0.7894737f), 0, this.em(0.7894737f), this.Composa - 1);
        graphics.drawLine(super.width - 1 - this.em(0.7894737f), 0, super.width - 1 - this.em(0.7894737f), this.Composa - 1);
        graphics.drawLine(super.width - 1, this.Composa - 1, 0, this.Composa - 1);
        if (GraphicsUtils.isFileGraphics(graphics)) {
            GraphicsUtils.setLineWidth(graphics, 1.0f);
        }
        final int em = this.em(0.7894737f);
        this.BuscaPosicioTipus[0] = this.em(0.7894737f) / 2;
        this.BuscaTokensVTipus[0] = this.em(0.15789473f);
        this.BuscaPosicioTipus[1] = this.em(0.631579f);
        this.BuscaTokensVTipus[1] = em - this.em(0.15789473f);
        this.BuscaPosicioTipus[2] = this.em(0.15789473f);
        this.BuscaTokensVTipus[2] = em - this.em(0.15789473f);
        if (this.amagat) {
            for (int i = 0; i < 3; ++i) {
                this.BuscaTokensVTipus[i] = em - this.BuscaTokensVTipus[i];
            }
        }
        graphics.fillPolygon(this.BuscaPosicioTipus, this.BuscaTokensVTipus, 3);
        for (int j = 0; j < 3; ++j) {
            final int[] buscaPosicioTipus = this.BuscaPosicioTipus;
            final int n = j;
            buscaPosicioTipus[n] += super.width - this.em(0.7894737f);
        }
        graphics.fillPolygon(this.BuscaPosicioTipus, this.BuscaTokensVTipus, 3);
    }
    
    private final boolean ActualitzaRect(int n, final int n2) {
        if (n > 0 && n < this.em(0.7894737f) && n2 < super.fill[0].height) {
            return true;
        }
        n -= super.width - this.em(0.7894737f);
        return n > 0 && n < this.em(0.7894737f) && n2 < super.fill[0].height;
    }
    
    public final boolean onMoved(final int n, final int n2, final BoxComponent boxComponent) {
        if (this.ActualitzaRect(n, n2)) {
            boxComponent.setCursor(new Cursor(0));
            return true;
        }
        return false;
    }
    
    public final boolean onDragged(final int n, final int n2, final BoxComponent boxComponent) {
        return this.ActualitzaRect(n, n2);
    }
    
    public final void amaga(final BoxComponent boxComponent) {
        if (!this.amagat) {
            if (boxComponent != null && this.esSubcapsa(boxComponent.getCaret().c)) {
                this.ImposaColor = boxComponent.getCaret();
            }
            else {
                this.ImposaColor = null;
            }
            this.amagat = true;
            super.nfills = 1;
        }
        else {
            if (boxComponent != null) {
                super.fill[1].Composa(boxComponent);
            }
            this.amagat = false;
            super.nfills = 2;
        }
    }
    
    public final boolean onClick(final int n, final int n2, final BoxComponent boxComponent) {
        final FormulaEditor formulaEditor = (FormulaEditor)boxComponent;
        if (this.ActualitzaRect(n, n2)) {
            FormulaEditor.push_backup(boxComponent, formulaEditor.curpos, this);
            this.amaga(boxComponent);
            if (boxComponent instanceof FormulaEditor) {
                formulaEditor.unselectArea();
            }
            this.ActualitzaRect(boxComponent);
            boxComponent.repaint();
            if (this.amagat) {
                if (this.ImposaColor != null) {
                    boxComponent.setCaret(null);
                }
                else {
                    boxComponent.setCaret(boxComponent.getCaret());
                }
            }
            else if (this.ImposaColor != null) {
                boxComponent.setCaret(this.ImposaColor);
            }
            return true;
        }
        return false;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return super.fill[this.I(point)].PosicioMesPropera(point, array);
    }
    
    static final void I(final BoxPosition boxPosition, final Vector vector, final Vector vector2) {
        final BoxPosition buscaTokensVTipus = boxPosition.BuscaTokensVTipus((CapsaLlibreria.PosicioMesPropera == null) ? (CapsaLlibreria.PosicioMesPropera = I("InterfaceBox")) : CapsaLlibreria.PosicioMesPropera);
        final AbstractBox c = boxPosition.BuscaPosicioTipus((CapsaLlibreria.addElement == null) ? (CapsaLlibreria.addElement = I("CapsaComandes")) : CapsaLlibreria.addElement).c;
        if (buscaTokensVTipus != null && buscaTokensVTipus.c != null) {
            final AbstractBox c2 = buscaTokensVTipus.c;
            for (int i = 0; i < c2.nfills; ++i) {
                if (c2.fill[i] instanceof CapsaLlibreria) {
                    final AbstractBox abstractBox = c2.fill[i].fill[1];
                    if (abstractBox instanceof CapsaComandes) {
                        final AbstractBox abstractBox2 = abstractBox.fill[0];
                        for (int j = 0; j < abstractBox2.nfills; ++j) {
                            String s = FormulaEditorCalc.cadenaOmega(abstractBox2.fill[j]);
                            final String trim = s.trim();
                            if (trim.length() > 0 && !trim.endsWith(";")) {
                                s += ";";
                            }
                            vector.addElement(s);
                            vector2.addElement(abstractBox2.fill[j]);
                        }
                    }
                }
                if (c2.fill[i] == c) {
                    break;
                }
            }
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        return "library";
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            boxScripting.appendText(this.amagat ? "{0}" : "{1}");
        }
        final int nfills = super.nfills;
        super.nfills = super.fill.length;
        super.onScript(boxScripting);
        super.nfills = nfills;
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        boxScripting.appendAttribute("closed", (this.amagat || (super.estil & 0x4) != 0x0) ? "true" : "false");
    }
    
    public final int getSplitFactor(final int n) {
        if (n < this.Composa) {
            return 500;
        }
        return 100;
    }
    
    static final Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
