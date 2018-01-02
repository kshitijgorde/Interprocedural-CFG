// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CParaStyleMgr
{
    private CRTEdit cParent;
    private CParaStyle cStyle;
    
    CParaStyleMgr(final CRTEdit cParent) {
        this.cParent = cParent;
    }
    
    CParaStyle getStyle(final String s) {
        CParaStyle cStyle;
        for (cStyle = this.cStyle; cStyle != null && !cStyle.getName().equals(s); cStyle = (CParaStyle)cStyle.getNext()) {}
        return cStyle;
    }
    
    CParaStyle getDefault() {
        final String defaultStyleName = this.cParent.getDefaultStyleName();
        CParaStyle cParaStyle = this.getStyle(defaultStyleName);
        if (cParaStyle == null) {
            cParaStyle = this.createIt(null, defaultStyleName);
        }
        return cParaStyle;
    }
    
    CParaStyle create(final String s, final String s2) {
        return this.createIt(this.getStyle(s2), s);
    }
    
    private CParaStyle createIt(CParaStyle default1, final String s) {
        CParaStyle style = this.getStyle(s);
        if (style == null) {
            if (default1 == null && !s.equals(this.cParent.getDefaultStyleName())) {
                default1 = this.getDefault();
            }
            style = new CParaStyle(this, default1, s);
            if (this.cStyle == null) {
                this.cStyle = style;
            }
            else {
                this.cStyle.addToEnd(style);
            }
        }
        return style;
    }
    
    String[] getStyles() {
        String[] array = null;
        int n = 0;
        for (CParaStyle cStyle = this.cStyle; cStyle != null; cStyle = (CParaStyle)cStyle.getNext()) {
            ++n;
        }
        if (n > 0) {
            array = new String[n];
            int n2 = 0;
            for (CParaStyle cStyle2 = this.cStyle; cStyle2 != null; cStyle2 = (CParaStyle)cStyle2.getNext()) {
                array[n2++] = cStyle2.getName();
            }
        }
        return array;
    }
    
    void clear() {
        this.cStyle = null;
    }
    
    void update(final CParaStyle cParaStyle, final boolean b) {
        for (CParaStyle cStyle = this.cStyle; cStyle != null; cStyle = (CParaStyle)cStyle.getNext()) {
            cStyle.clearRebuilt();
        }
        for (CParaStyle cStyle2 = this.cStyle; cStyle2 != null; cStyle2 = (CParaStyle)cStyle2.getNext()) {
            cStyle2.update(cParaStyle, b);
        }
    }
}
