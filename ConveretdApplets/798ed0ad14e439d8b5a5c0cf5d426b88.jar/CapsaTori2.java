// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaTori2 extends AbstractBox
{
    String append;
    
    public CapsaTori2() {
        super.color = 3;
        (super.dibs = new NeuterBox[2])[1] = NeuterBox.crea("=");
        super.ndibs = 2;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.append = (String)o;
        if (this.append.equals("sum")) {
            super.dibs[0] = NeuterBox.crea("sumatori");
        }
        if (this.append.equals("prod")) {
            super.dibs[0] = NeuterBox.crea("productori");
        }
        if (this.append.equals("bigcup")) {
            super.dibs[0] = NeuterBox.crea("bigcup");
        }
        if (this.append.equals("bigcap")) {
            super.dibs[0] = NeuterBox.crea("bigcap");
        }
    }
    
    public final int nombreMinimDeFills() {
        return 3;
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
        super.baseline = super.fill[2].height + this.em(0.1f) + super.dibs[0].baseline;
        final int n = super.fill[0].width + 2 * this.em(0.1f) + super.dibs[1].width + super.fill[1].width;
        final int n2 = this.em(0.2f) + Math.max(super.dibs[0].width, Math.max(n, super.fill[2].width)) / 2;
        super.dibs[0].x = n2 - super.dibs[0].width / 2;
        super.dibs[0].y = super.baseline - super.dibs[0].baseline;
        final int n3 = Math.max(super.dibs[1].baseline, Math.max(super.fill[0].baseline, super.fill[1].baseline)) + super.dibs[0].y + super.dibs[0].height + this.em(0.0f);
        super.fill[0].x = n2 - n / 2;
        super.fill[0].y = n3 - super.fill[0].baseline;
        super.dibs[1].x = super.fill[0].xw() + this.em(0.1f);
        super.dibs[1].y = n3 - super.dibs[1].baseline;
        super.fill[1].x = super.dibs[1].x + super.dibs[1].width + this.em(0.1f);
        super.fill[1].y = n3 - super.fill[1].baseline;
        super.fill[2].x = n2 - super.fill[2].width / 2;
        super.fill[2].y = 0;
        super.width = Math.max(super.dibs[0].x + super.dibs[0].width, Math.max(super.fill[1].xw(), super.fill[2].xw())) + this.em(0.2f);
        super.height = Math.max(super.fill[0].yh(), super.fill[1].yh());
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.append + "x";
        }
        if (boxScripting.scriptMode == 1) {
            return "munderover";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("mo");
            if (this.append.equals("sum")) {
                boxScripting.appendText("\u2211");
            }
            if (this.append.equals("prod")) {
                boxScripting.appendText("\u220f");
            }
            if (this.append.equals("bigcup")) {
                boxScripting.appendText("\u22c3");
            }
            if (this.append.equals("bigcap")) {
                boxScripting.appendText("\u22c2");
            }
            boxScripting.closeTag();
            boxScripting.openTag("mrow");
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
            boxScripting.openTag("mo");
            boxScripting.appendText("=");
            boxScripting.closeTag();
            XMLBoxUtils.mrow(super.fill[1], boxScripting);
            boxScripting.closeTag();
            XMLBoxUtils.mrow(super.fill[2], boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            if (this.append.equals("sum")) {
                boxScripting.appendScript("sum_operator");
            }
            else if (this.append.equals("prod")) {
                boxScripting.appendScript("product_operator");
            }
            boxScripting.appendText("(");
            super.fill[0].script(boxScripting);
            boxScripting.appendText(" in ");
            super.fill[1].script(boxScripting);
            boxScripting.appendText("..");
            super.fill[2].script(boxScripting);
            boxScripting.appendText(")");
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
