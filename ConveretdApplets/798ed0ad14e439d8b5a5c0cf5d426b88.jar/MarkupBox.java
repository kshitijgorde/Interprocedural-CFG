// 
// Decompiled by Procyon v0.5.30
// 

public class MarkupBox extends AbstractBox
{
    public final String markup;
    
    public MarkupBox(final String markup) {
        this.markup = markup;
    }
    
    public static final MarkupBox createBox(String string) {
        if (string.charAt(0) != '\\') {
            string = "\\" + string;
        }
        if ("\\caret".equals(string)) {
            return new MarkupBox("\\caret");
        }
        if ("\\beginselection".equals(string)) {
            return new MarkupBox("\\beginselection");
        }
        if ("\\endselection".equals(string)) {
            return new MarkupBox("\\endselection");
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.appendText(this.markup.substring(1));
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.markup;
        }
        if (boxScripting.scriptMode == 1) {
            return "mo";
        }
        return null;
    }
    
    public static final boolean isCaret(final AbstractBox abstractBox) {
        return isMarkup(abstractBox, "\\caret") || (abstractBox instanceof TokensBox && abstractBox.nfills == 1 && isCaret(abstractBox.fill[0]));
    }
    
    public static final boolean isMarkup(final AbstractBox abstractBox, final String s) {
        return abstractBox instanceof MarkupBox && ((MarkupBox)abstractBox).markup.equals(s);
    }
}
