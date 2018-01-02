import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaRepetir extends AbstractBox implements CommandsOwner
{
    public CapsaRepetir() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("repeat"), NeuterBox.crea("until") };
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 2;
    }
    
    public final void enCalculRect() {
        super.baseline = this.em(0.75f);
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = 0 + super.baseline - super.dibs[0].baseline;
        super.fill[0].x = this.em(0.1f) + this.em(1.0f);
        super.fill[0].y = super.dibs[0].y + super.dibs[0].height + this.em(0.0f);
        super.dibs[1].x = this.em(0.1f);
        super.dibs[1].y = super.fill[0].yh() + this.em(0.0f);
        super.fill[1].x = super.dibs[1].x + super.dibs[1].width + this.em(0.4f);
        super.fill[1].y = super.fill[0].yh() + this.em(0.0f);
        super.width = Math.max(super.fill[0].xw(), super.fill[1].xw()) + this.em(0.1f);
        super.height = super.dibs[1].y + super.dibs[1].height + 0;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return this.I(point, array);
    }
    
    public final boolean posicio_final() {
        return false;
    }
    
    public final boolean inserta_final() {
        return true;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "repeat";
        }
        if (boxScripting.scriptMode == 1) {
            return "apply";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            XMLBoxUtils.csymbol(boxScripting, "repeat");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
            XMLBoxUtils.mrow(super.fill[1], boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" repeat ");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(" until ");
            super.fill[1].script(boxScripting);
        }
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
