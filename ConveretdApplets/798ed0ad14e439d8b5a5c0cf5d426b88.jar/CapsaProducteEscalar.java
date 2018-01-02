import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaProducteEscalar extends AbstractBox
{
    public char tipus;
    
    public CapsaProducteEscalar() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea(",") };
        super.ndibs = super.dibs.length;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.tipus = ((String)o).charAt(0);
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.baseline = Math.max(super.fill[0].baseline, super.fill[1].baseline);
        super.baseline = Math.max(super.baseline, super.dibs[0].baseline);
        super.fill[0].y = super.baseline - super.fill[0].baseline;
        super.dibs[0].y = super.baseline - super.dibs[0].baseline;
        super.fill[1].y = super.baseline - super.fill[1].baseline;
        super.height = Math.max(super.fill[0].yh(), super.fill[1].yh());
        super.height = Math.max(super.height, super.dibs[0].y + super.dibs[0].height);
        super.fill[0].x = this.em(0.1f) + this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, super.height));
        super.dibs[0].x = super.fill[0].xw();
        super.fill[1].x = super.dibs[0].x + super.dibs[0].width;
        super.width = super.fill[1].xw() + this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, super.height)) + this.em(0.1f);
    }
    
    protected final void I(final Graphics graphics) {
        final int height = super.height;
        final int width = super.width;
        final int em = this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, height + 1));
        CapsaParentesis.dibuixaParentesis(this.tipus, super.I, super.EM, super.baseline, false, new Rectangle(this.em(0.1f), 0, em, height), graphics);
        CapsaParentesis.dibuixaParentesis(this.tipus, super.I, super.EM, super.baseline, true, new Rectangle(width - em - this.em(0.1f), 0, em, height), graphics);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.em(0.1f)) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x >= super.width - this.em(0.1f)) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        if (point2.x < super.dibs[0].x) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[1].PosicioMesProperaGlobal(point, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "scalarproduct";
        }
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("scalarproduct");
            boxScripting.closeTag();
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText("(");
            super.fill[0].script(boxScripting);
            boxScripting.appendText("*");
            super.fill[1].script(boxScripting);
            boxScripting.appendText(")");
        }
    }
    
    public final int getSplitFactor(final int n) {
        if (n < Math.min(super.fill[0].y, super.fill[1].y)) {
            return 610;
        }
        if (n > Math.max(super.fill[0].yh(), super.fill[1].yh())) {
            return 610;
        }
        return 590;
    }
}
