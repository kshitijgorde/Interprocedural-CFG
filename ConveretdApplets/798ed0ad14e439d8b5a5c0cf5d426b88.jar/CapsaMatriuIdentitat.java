import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaMatriuIdentitat extends AbstractBox
{
    public CapsaMatriuIdentitat() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("I") };
        super.dibs[0].setFontName("Serif");
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        return 80;
    }
    
    public final void enCalculRect() {
        super.baseline = super.dibs[0].baseline;
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = 0;
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width + this.em(0.05f);
        super.fill[0].y = Math.max(this.em(1.0f) - super.fill[0].baseline, super.dibs[0].height / 2);
        super.width = super.fill[0].xw() + this.em(0.1f);
        super.height = Math.max(super.dibs[0].y + super.dibs[0].height, super.fill[0].yh());
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
            return "identitymatrix";
        }
        if (boxScripting.scriptMode == 1) {
            return "msub";
        }
        if (boxScripting.scriptMode == 2) {
            return "identity_matrix";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("ident");
            boxScripting.appendAttribute("definitionURL", "http://www.wiris.com/XML/csymbol");
            boxScripting.closeTag();
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
        }
        else {
            super.onScript(boxScripting);
        }
    }
    
    public final int getSplitFactor(final int n) {
        for (int i = 0; i < super.ndibs; ++i) {
            if (n > super.dibs[i].y && n < super.dibs[i].yh()) {
                return 800;
            }
        }
        return 700;
    }
}
