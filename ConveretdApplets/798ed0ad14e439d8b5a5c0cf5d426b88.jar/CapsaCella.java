import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaCella extends AbstractBox
{
    public final void enCalculRect() {
        super.width = super.fill[0].width;
        super.height = super.fill[0].height;
        super.baseline = super.fill[0].baseline;
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        if (boxComponent instanceof FormulaEditor && ((FormulaEditor)boxComponent).isDesign()) {
            graphics.setColor(GraphicsUtils.colorDissimulat(graphics.getColor()));
            graphics.drawRect(0, 0, super.width, super.height);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1 || boxScripting.scriptMode == 0) {
            return "cell";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        for (int i = 0; i < super.nfills; ++i) {
            boxScripting.openTag("math");
            boxScripting.appendAttribute("xmlns", "http://www.w3.org/1998/Math/MathML");
            super.fill[i].script(boxScripting);
            boxScripting.closeTag();
        }
    }
}
