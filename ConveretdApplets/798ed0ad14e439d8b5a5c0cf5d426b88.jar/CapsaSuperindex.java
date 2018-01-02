import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaSuperindex extends AbstractBox
{
    public final boolean rect_depen_germans() {
        return true;
    }
    
    public final boolean script_depen_germans() {
        return true;
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
        if (super.p_en_pare > 0) {
            super.baseline = Math.max(super.fill[0].height, this.getParentBox().fill[super.p_en_pare - 1].baseline) + this.em(0.125f);
            super.height = super.baseline + this.getParentBox().fill[super.p_en_pare - 1].UnderBaseline();
        }
        else {
            super.baseline = Math.max(super.fill[0].height, this.em(0.75f)) + this.em(0.125f);
            super.height = super.baseline + this.em(0.25f);
        }
        super.width = 2 + super.fill[0].width;
        super.fill[0].x = 1;
        super.fill[0].y = 0;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        final Point point2 = new Point(point.x - posicioReal.x, point.y - posicioReal.y);
        if (point2.x < 0) {
            return this.PosSeg_Fora(array);
        }
        if (point2.x < super.width) {
            return super.fill[0].PosicioMesProperaGlobal(point, array);
        }
        return this.PosAnt_Fora(array);
    }
    
    public final int getFactorEscalaAbsolutFill(final BoxComponent boxComponent, final int n) {
        return 80;
    }
    
    public String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "^";
        }
        if (boxScripting.scriptMode == 1) {
            if (boxScripting.isTop()) {
                return "sup";
            }
            return "msup";
        }
        else {
            if (boxScripting.scriptMode == 2) {
                return "superindex_operator";
            }
            return null;
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
