import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaPer extends AbstractBox implements CommandsOwner
{
    public CapsaPer() {
        super.color = 3;
        super.dibs = new NeuterBox[] { NeuterBox.crea("for"), NeuterBox.crea("do"), NeuterBox.crea("end") };
        super.ndibs = super.dibs.length;
    }
    
    public final int nombreMinimDeFills() {
        return 3;
    }
    
    public final void enCalculRect() {
        final int baseline = super.fill[0].baseline;
        super.dibs[0].x = this.em(0.1f);
        super.dibs[0].y = 0 + baseline - super.dibs[0].baseline;
        super.fill[0].x = super.dibs[0].x + super.dibs[0].width + this.em(0.4f);
        super.fill[0].y = 0;
        super.dibs[1].x = super.fill[0].xw() + this.em(0.4f);
        super.dibs[1].y = 0 + baseline - super.dibs[1].baseline;
        super.fill[1].x = this.em(0.1f) + this.em(1.0f);
        super.fill[1].y = super.fill[0].yh() + this.em(0.0f);
        super.dibs[2].x = this.em(0.1f);
        super.dibs[2].y = super.fill[1].yh() + this.em(0.0f);
        super.fill[2].x = super.dibs[2].x + super.dibs[2].width + this.em(" ");
        super.fill[2].y = super.dibs[2].y;
        final AbstractNeuterBox abstractNeuterBox = super.dibs[2];
        abstractNeuterBox.y += super.fill[2].baseline - super.dibs[2].baseline;
        this.I();
        super.width += this.em(0.1f);
        super.baseline = this.em(0.75f);
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return this.I(point, array);
    }
    
    public final boolean fbvisible(final int n) {
        return n != 2;
    }
    
    public final boolean posicio_final() {
        return false;
    }
    
    public final boolean inserta_final() {
        return true;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "for";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            final boolean b = !(super.fill[2] instanceof EmptyBox);
            final boolean b2 = b && boxScripting.oneChild;
            if (b2) {
                boxScripting.openTag("mrow");
            }
            boxScripting.openTag("apply");
            XMLBoxUtils.csymbol(boxScripting, "for");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
            XMLBoxUtils.mrow(super.fill[1], boxScripting);
            boxScripting.closeTag();
            if (b) {
                super.fill[2].script(boxScripting);
                if (b2) {
                    boxScripting.closeTag();
                }
            }
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(" for ");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(" do ");
            super.fill[1].script(boxScripting);
            boxScripting.appendText(" end ");
            super.fill[2].script(boxScripting);
            boxScripting.appendText(" ");
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
