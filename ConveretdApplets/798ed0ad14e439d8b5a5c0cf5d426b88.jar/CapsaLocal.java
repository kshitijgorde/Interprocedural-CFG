import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaLocal extends AbstractBox
{
    public CapsaLocal() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("local") };
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = super.fill[0].baseline - super.dibs[0].baseline;
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width + this.em(0.4f);
        super.fill[0].y = 0;
        super.width = super.fill[0].xw() + this.em(0.1f);
        super.height = super.fill[0].yh() + 0;
        super.baseline = this.em(0.75f);
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
        return super.fill[0].PosicioMesProperaGlobal(point, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "local";
        }
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.csymbol(boxScripting, "local");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" local ");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(";");
        }
    }
    
    public final boolean posicio_final() {
        return true;
    }
    
    public final boolean inserta_final() {
        return true;
    }
    
    public final int getSplitFactor(final int n) {
        for (int i = 0; i < super.ndibs; ++i) {
            if (n > super.dibs[i].y && n < super.dibs[i].yh()) {
                return 800;
            }
        }
        return 600;
    }
}
