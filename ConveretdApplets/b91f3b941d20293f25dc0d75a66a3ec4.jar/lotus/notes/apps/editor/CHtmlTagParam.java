// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

final class CHtmlTagParam
{
    private String cParam;
    private String cValue;
    
    CHtmlTagParam(final String s, final String cValue) {
        this.cParam = s.toUpperCase();
        this.cValue = cValue;
    }
    
    String getParam() {
        return this.cParam;
    }
    
    String getValue() {
        return this.cValue;
    }
}
