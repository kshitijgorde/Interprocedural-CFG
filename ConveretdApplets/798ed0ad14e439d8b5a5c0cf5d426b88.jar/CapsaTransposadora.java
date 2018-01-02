// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaTransposadora extends CapsaSuperindex
{
    public CapsaTransposadora() {
        this.AfegirNoCalc(new SpecialSymbolBox("T"));
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "transpose";
        }
        if (boxScripting.scriptMode == 1) {
            return "msup";
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            super.onScript(boxScripting);
        }
        else if (boxScripting.scriptMode != 0) {
            if (boxScripting.scriptMode == 2) {
                boxScripting.appendText("'");
            }
        }
    }
}
