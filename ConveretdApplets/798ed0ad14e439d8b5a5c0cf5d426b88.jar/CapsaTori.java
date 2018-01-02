// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaTori extends AbstractBox
{
    String appendScript;
    
    public CapsaTori() {
        super.color = 3;
    }
    
    public final void inicialitzaModalitat(final Object o) {
        this.appendScript = (String)o;
        if (this.appendScript.equals("sum")) {
            super.dibs = new NeuterBox[] { NeuterBox.crea("sumatori") };
        }
        if (this.appendScript.equals("prod")) {
            super.dibs = new NeuterBox[] { NeuterBox.crea("productori") };
        }
        if (this.appendScript.equals("bigcup")) {
            super.dibs = new NeuterBox[] { NeuterBox.crea("bigcup") };
        }
        if (this.appendScript.equals("bigcap")) {
            super.dibs = new NeuterBox[] { NeuterBox.crea("bigcap") };
        }
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
        final int n = this.em(0.2f) + Math.max(super.dibs[0].width, super.fill[0].width) / 2;
        super.dibs[0].x = n - super.dibs[0].width / 2;
        super.dibs[0].y = super.baseline - super.dibs[0].baseline;
        super.fill[0].x = n - super.fill[0].width / 2;
        super.fill[0].y = super.dibs[0].y + super.dibs[0].height + this.em(0.1f);
        super.width = Math.max(super.dibs[0].x + super.dibs[0].width, super.fill[0].xw()) + this.em(0.2f);
        super.height = super.fill[0].yh();
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.appendScript;
        }
        if (boxScripting.scriptMode == 1) {
            return "munder";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("mo");
            if (this.appendScript.equals("sum")) {
                boxScripting.appendText("\u2211");
            }
            if (this.appendScript.equals("prod")) {
                boxScripting.appendText("\u220f");
            }
            if (this.appendScript.equals("bigcup")) {
                boxScripting.appendText("\u22c3");
            }
            if (this.appendScript.equals("bigcap")) {
                boxScripting.appendText("\u22c2");
            }
            boxScripting.closeTag();
            XMLBoxUtils.mrow(super.fill[0], boxScripting);
        }
        else if (boxScripting.scriptMode == 0) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode == 2) {
            if (this.appendScript.equals("sum")) {
                boxScripting.appendScript("sum_operator");
            }
            else if (this.appendScript.equals("prod")) {
                boxScripting.appendScript("product_operator");
            }
            boxScripting.appendText("(");
            if (super.fill[0] instanceof TokensVBox) {
                final AbstractBox abstractBox = super.fill[0];
                if (abstractBox.nfills > 0) {
                    abstractBox.fill[0].script(boxScripting);
                }
                if (abstractBox.nfills > 1) {
                    boxScripting.appendText(" where (");
                    for (int i = 1; i < abstractBox.nfills; ++i) {
                        if (i > 1) {
                            boxScripting.appendText(")&(");
                        }
                        abstractBox.fill[i].script(boxScripting);
                    }
                    boxScripting.appendText(")");
                }
            }
            else {
                super.fill[0].script(boxScripting);
            }
            boxScripting.appendText(")");
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 700;
    }
}
