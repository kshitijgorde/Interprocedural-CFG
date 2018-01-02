import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaCombinatoria extends AbstractBox
{
    public char tipus;
    
    public CapsaCombinatoria() {
        super.color = 3;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.tipus = ((String)o).charAt(0);
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.fill[0].y = 0 + this.em(0.0f);
        super.fill[1].y = super.fill[0].yh() + this.em(0.1f);
        super.height = super.fill[1].yh() + this.em(0.0f) + 0;
        final int width = super.fill[super.fill[0].width <= super.fill[1].width].width;
        super.width = this.em(0.1f) + this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, super.height)) + this.em(0.1f);
        super.fill[0].x = super.width + width / 2 - super.fill[0].width / 2;
        super.fill[1].x = super.width + width / 2 - super.fill[1].width / 2;
        super.width += width + this.em(0.1f) + this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, super.height)) + this.em(0.1f);
        super.baseline = super.height / 2 + this.em(0.25f);
    }
    
    protected final void I(final Graphics graphics) {
        final int height = super.height;
        final int width = super.width;
        final int em = this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus, height));
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
        if (point2.y < super.fill[0].yh()) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[1].PosicioMesProperaGlobal(new Point(point.x, super.fill[1].PosicioReal().y), array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        if (boxScripting.scriptMode == 0) {
            return this.tipus + "combinatori";
        }
        if (boxScripting.scriptMode == 2 && this.tipus == 'p') {
            return "combinations";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.csymbol(boxScripting, "binomial_coefficient");
        }
        super.onScript(boxScripting);
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
