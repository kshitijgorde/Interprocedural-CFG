// 
// Decompiled by Procyon v0.5.30
// 

package action.upload.params;

public class ConnectionParam
{
    private boolean _isOverwrite;
    
    public ConnectionParam(final boolean isOverwrite) {
        this._isOverwrite = isOverwrite;
    }
    
    public boolean isOverwrite() {
        return this._isOverwrite;
    }
}
