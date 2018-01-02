import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaDiv extends AbstractBox
{
    public CapsaDiv() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.creaRectangle(), NeuterBox.creaRectangle(), NeuterBox.creaRectangle(), NeuterBox.creaRectangle(), NeuterBox.creaRectangle() };
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.baseline = Math.max(super.fill[0].baseline, super.fill[1].baseline);
        super.fill[0].y = super.baseline - super.fill[0].baseline;
        super.fill[1].y = super.baseline - super.fill[1].baseline;
        super.dibs[0].y = 0;
        super.dibs[0].height = Math.max(super.fill[0].yh(), super.fill[1].yh()) + this.em(0.0f);
        super.dibs[1].y = super.dibs[0].y + super.dibs[0].height;
        super.dibs[1].height = this.em(0.05f);
        int n = this.em(0.3f) + super.fill[0].width;
        if (super.nfills == 4 && n < this.em(0.3f) + super.fill[2].width) {
            n = this.em(0.3f) + super.fill[2].width;
        }
        super.fill[0].x = n - super.fill[0].width;
        super.dibs[0].x = super.fill[0].xw() + this.em(0.7f);
        super.dibs[0].width = this.em(0.05f);
        super.dibs[1].x = super.dibs[0].x;
        super.fill[1].x = super.dibs[0].x + super.dibs[0].width + this.em(0.3f);
        int n2 = super.fill[1].width;
        if (super.nfills == 4 && n2 < super.fill[3].width) {
            n2 = super.fill[3].width;
        }
        super.dibs[1].width = super.fill[1].x + n2 + this.em(0.3f) - super.dibs[1].x;
        super.width = super.dibs[1].x + super.dibs[1].width + this.em(0.3f);
        super.height = super.dibs[1].y + super.dibs[1].height;
        if (super.nfills == 4) {
            final AbstractNeuterBox abstractNeuterBox = super.dibs[2];
            final AbstractNeuterBox abstractNeuterBox2 = super.dibs[2];
            final AbstractNeuterBox abstractNeuterBox3 = super.dibs[3];
            final AbstractNeuterBox abstractNeuterBox4 = super.dibs[3];
            final AbstractNeuterBox abstractNeuterBox5 = super.dibs[4];
            final AbstractNeuterBox abstractNeuterBox6 = super.dibs[4];
            final int em = this.em(0.1f);
            abstractNeuterBox6.height = em;
            abstractNeuterBox5.width = em;
            abstractNeuterBox4.height = em;
            abstractNeuterBox3.width = em;
            abstractNeuterBox2.height = em;
            abstractNeuterBox.width = em;
            super.dibs[2].y = super.dibs[1].y + super.dibs[1].height + this.em(0.1f);
            super.dibs[3].y = super.dibs[2].y + super.dibs[2].height + this.em(0.1f);
            super.dibs[4].y = super.dibs[3].y + super.dibs[3].height + this.em(0.1f);
            super.fill[2].y = super.dibs[4].y + super.dibs[4].height + this.em(0.1f);
            super.fill[3].y = super.dibs[1].y + super.dibs[1].height + this.em(0.1f);
            super.fill[2].x = n + this.em(0.7f) - this.em(0.2f) - super.fill[2].width;
            super.fill[3].x = super.fill[1].x;
            super.dibs[4].x = Math.max(Math.max(super.fill[0].xm() + super.dibs[4].width / 2 + this.em(0.1f), super.fill[2].x), 2 * this.em(0.1f) + 2 * super.dibs[4].width);
            super.dibs[3].x = super.dibs[4].x - this.em(0.1f) - super.dibs[3].width;
            super.dibs[2].x = super.dibs[3].x - this.em(0.1f) - super.dibs[2].width;
            super.height = Math.max(super.fill[2].yh(), super.fill[3].yh());
        }
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < this.em(0.3f)) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        if (point2.x >= super.width - this.em(0.3f)) {
            return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
        }
        if (point2.x < super.dibs[0].x) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return super.fill[1].PosicioMesProperaGlobal(point, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        if (boxScripting.scriptMode == 0) {
            if (super.nfills == 4) {
                return "quoremdone";
            }
            return "quorem";
        }
        else {
            if (boxScripting.scriptMode == 2) {
                return "typographic_quotient_and_remainder";
            }
            return null;
        }
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            if (super.nfills == 2) {
                XMLBoxUtils.csymbol(boxScripting, "quorem");
            }
            else {
                XMLBoxUtils.csymbol(boxScripting, "quoremdone");
            }
        }
        else if (boxScripting.scriptMode == 1) {
            super.fill[0].script(boxScripting);
            super.fill[1].script(boxScripting);
            return;
        }
        super.onScript(boxScripting);
    }
}
