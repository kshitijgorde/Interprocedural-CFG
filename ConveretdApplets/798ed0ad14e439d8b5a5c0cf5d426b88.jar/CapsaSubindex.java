import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaSubindex extends AbstractBox
{
    static int PosAnt_Fora;
    
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
            super.height = Math.max(this.getParentBox().fill[super.p_en_pare - 1].baseline * (100 - CapsaSubindex.PosAnt_Fora) / 100, this.getParentBox().fill[super.p_en_pare - 1].height - super.fill[0].baseline);
            super.baseline = this.getParentBox().fill[super.p_en_pare - 1].baseline;
        }
        else {
            super.height = Math.max(this.em(0.75f) * (100 - CapsaSubindex.PosAnt_Fora) / 100, this.em() - super.fill[0].baseline);
            super.baseline = this.em(0.75f);
        }
        super.fill[0].x = 1;
        super.fill[0].y = super.height;
        super.width = 2 + super.fill[0].width;
        super.height += super.fill[0].height;
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
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "_";
        }
        if (boxScripting.scriptMode == 1) {
            if (boxScripting.isTop()) {
                return "sub";
            }
            return "msub";
        }
        else {
            if (boxScripting.scriptMode == 2) {
                return "subindex_operator";
            }
            return null;
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
    
    static {
        CapsaSubindex.PosAnt_Fora = 50;
    }
}
