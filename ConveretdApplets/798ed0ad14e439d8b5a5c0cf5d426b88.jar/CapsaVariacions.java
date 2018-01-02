import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaVariacions extends AbstractBox
{
    String PosAnt_Fill;
    
    public CapsaVariacions() {
        super.color = 3;
        super.dibs = new NeuterBox[] { null, NeuterBox.crea(",") };
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.PosAnt_Fill = (String)o;
        String posAnt_Fill = this.PosAnt_Fill;
        if (posAnt_Fill == "PR") {
            posAnt_Fill = "P";
        }
        super.dibs[0] = NeuterBox.crea(posAnt_Fill);
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        return 80;
    }
    
    public final int getFactorEscalaDibAbsolut(final int n) {
        if (n == 1) {
            return 80;
        }
        return 100;
    }
    
    public final void enCalculRect() {
        if (this.PosAnt_Fill.equals("PR")) {
            super.fill[1].y = 0;
            if (this.em(0.3f) + super.fill[1].UnderBaseline() < super.dibs[0].height / 2) {
                super.dibs[0].y = super.fill[1].y + super.fill[1].baseline - this.em(0.3f);
            }
            else {
                super.dibs[0].y = super.fill[1].yh() - super.dibs[0].height / 2 + 1;
            }
            if (super.fill[0].baseline < this.em(1.0f) - super.dibs[0].height / 2) {
                super.fill[0].y = super.dibs[0].y + this.em(1.0f);
            }
            else {
                super.fill[0].y = super.dibs[0].y + super.dibs[0].height / 2 + 1;
            }
            super.dibs[0].x = this.em(0.1f);
            final AbstractBox abstractBox = super.fill[1];
            final AbstractBox abstractBox2 = super.fill[0];
            final int n = super.dibs[0].x + super.dibs[0].width + this.em(0.05f);
            abstractBox2.x = n;
            abstractBox.x = n;
            super.width = Math.max(super.fill[1].xw(), super.fill[0].xw()) + this.em(0.1f);
            super.height = super.fill[0].yh();
            super.baseline = super.dibs[0].y + super.dibs[0].baseline;
            super.ndibs = 1;
            return;
        }
        int n2 = super.fill[0].baseline;
        if (super.nfills == 2) {
            n2 = Math.max(n2, super.fill[1].baseline);
        }
        super.baseline = super.dibs[0].baseline + Math.max(0, n2 - this.em(1.0f));
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = super.baseline - super.dibs[0].baseline;
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width + this.em(0.05f);
        super.fill[0].y = super.dibs[0].y + this.em(1.0f) - super.fill[0].baseline;
        super.dibs[1].x = super.fill[0].xw();
        super.dibs[1].y = super.dibs[0].y + this.em(1.0f) - super.dibs[1].baseline;
        if (super.nfills == 2) {
            super.fill[1].x = super.dibs[1].x + super.dibs[1].width;
            super.fill[1].y = super.dibs[0].y + this.em(1.0f) - super.fill[1].baseline;
            super.width = super.fill[1].xw() + this.em(0.1f);
            super.height = Math.max(super.fill[0].yh(), super.fill[1].yh());
        }
        else {
            super.width = super.fill[0].xw() + this.em(0.1f);
            super.height = super.fill[0].yh();
        }
        if (super.nfills == 2) {
            super.ndibs = 2;
        }
        else {
            super.ndibs = 1;
        }
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
        if (super.nfills != 2) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        if (this.PosAnt_Fill.equals("PR")) {
            if (point2.y < super.dibs[0].y + super.dibs[0].height / 2) {
                return super.fill[1].PosicioMesProperaGlobal(point, array);
            }
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        else {
            if (point2.x < super.dibs[1].x) {
                return super.fill[0].PosicioMesProperaGlobal(point, array);
            }
            return super.fill[1].PosicioMesProperaGlobal(point, array);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.PosAnt_Fill + "nm";
        }
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        if (boxScripting.scriptMode == 2) {
            if (this.PosAnt_Fill.equals("C")) {
                return "combinations";
            }
            if (this.PosAnt_Fill.equals("CR")) {
                return "combinations_with_repetition";
            }
            if (this.PosAnt_Fill.equals("V")) {
                return "variations";
            }
            if (this.PosAnt_Fill.equals("VR")) {
                return "variations_with_repetition";
            }
            if (this.PosAnt_Fill.equals("P")) {
                return "permutations";
            }
            if (this.PosAnt_Fill.equals("PR")) {
                return "permutations_with_repetition";
            }
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.csymbol(boxScripting, this.PosAnt_Fill);
        }
        super.onScript(boxScripting);
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
