// 
// Decompiled by Procyon v0.5.30
// 

public class XMLBoxUtils
{
    private static Class[] topBoxClasses;
    
    public static final void mrow(final AbstractBox abstractBox, final BoxScripting boxScripting) {
        boxScripting.oneChild = true;
        abstractBox.script(boxScripting);
    }
    
    public static final String toValidMathML(final AbstractBox abstractBox, final boolean b) {
        final BoxScripting boxScripting = new BoxScripting(1);
        boxScripting.ignoreID = !b;
        final boolean b2 = !isTopBox(abstractBox);
        if (b2) {
            boxScripting.openTag("math");
            boxScripting.beginInnerFormat(abstractBox, "math");
        }
        boxScripting.appendAttribute("xmlns", "http://www.w3.org/1998/Math/MathML");
        script(boxScripting, abstractBox);
        if (b2) {
            boxScripting.endInnerFormat(abstractBox, "math");
            boxScripting.closeTag();
        }
        return boxScripting.toString();
    }
    
    public static final String toValidMathML(final AbstractSelection abstractSelection, final boolean b) {
        final BoxScripting boxScripting = new BoxScripting(1);
        boxScripting.ignoreID = !b;
        if (abstractSelection instanceof TokensVSelection && isTopBox(((TokensVSelection)abstractSelection).TV.getParentBox())) {
            abstractSelection.script(boxScripting);
            return boxScripting.toString();
        }
        boxScripting.openTag("math");
        boxScripting.appendAttribute("xmlns", "http://www.w3.org/1998/Math/MathML");
        abstractSelection.script(boxScripting);
        boxScripting.closeTag();
        return boxScripting.toString();
    }
    
    private static boolean isTopBox(final AbstractBox abstractBox) {
        if (XMLBoxUtils.topBoxClasses == null) {
            return false;
        }
        for (int i = 0; i < XMLBoxUtils.topBoxClasses.length; ++i) {
            if (XMLBoxUtils.topBoxClasses[i].isInstance(abstractBox)) {
                return true;
            }
        }
        return false;
    }
    
    public static final void setTopBoxClasses(final Class[] topBoxClasses) {
        XMLBoxUtils.topBoxClasses = topBoxClasses;
    }
    
    private static void script(final BoxScripting boxScripting, final AbstractBox abstractBox) {
        if (abstractBox.script_depen_germans()) {
            boxScripting.openTag(abstractBox.scriptCommand(boxScripting));
            boxScripting.openTag("mrow");
            boxScripting.closeTag();
            abstractBox.onScript(boxScripting);
            boxScripting.closeTag();
        }
        else {
            abstractBox.script(boxScripting);
        }
    }
    
    public static final void onScript(final BoxScripting boxScripting, final AbstractBox[] array, final int n, final int n2, final boolean b) {
        int k;
        for (int i = n - 1; i < n2; i = k) {
            int n3;
            for (n3 = i + 1; n3 < n2 && array[n3].script_depen_germans(); ++n3) {}
            for (int j = n3 - 1; j > i; --j) {
                boxScripting.openTag(array[j].scriptCommand(boxScripting));
            }
            if (i >= n) {
                if (b) {
                    mrow(array[i], boxScripting);
                }
                else {
                    array[i].script(boxScripting);
                }
            }
            else if (n3 > n) {
                boxScripting.openTag("mrow");
                boxScripting.closeTag();
            }
            for (k = i + 1; k < n3; ++k) {
                array[k].onScript(boxScripting);
                boxScripting.closeTag();
            }
        }
    }
    
    public static final int countScriptChildren(final AbstractBox abstractBox) {
        int n = 1;
        for (int i = 1; i < abstractBox.nfills; ++i) {
            if (!abstractBox.fill[i].script_depen_germans()) {
                ++n;
            }
        }
        return n;
    }
    
    public static final void csymbol(final BoxScripting boxScripting, final String s) {
        boxScripting.openTag("csymbol");
        boxScripting.appendAttribute("definitionURL", "http://www.wiris.com/XML/csymbol");
        boxScripting.appendText(s);
        boxScripting.closeTag();
    }
    
    public static final void mo(final BoxScripting boxScripting, final String s) {
        boxScripting.openTag("mo");
        boxScripting.appendText(s);
        boxScripting.closeTag();
    }
    
    public static final void beginBox(final BoxScripting boxScripting, final AbstractBox abstractBox, final String s) {
        if (s == null) {
            return;
        }
        boxScripting.beginOuterFormat(abstractBox, s);
        boxScripting.openTag(s);
        boxScripting.beginInnerFormat(abstractBox, s);
        abstractBox.attributes(boxScripting);
        abstractBox.scriptId(boxScripting);
    }
    
    public static final void endBox(final BoxScripting boxScripting, final AbstractBox abstractBox, final String s) {
        if (s == null) {
            return;
        }
        boxScripting.endInnerFormat(abstractBox, s);
        boxScripting.closeTag();
        boxScripting.endOuterFormat(abstractBox, s);
    }
}
