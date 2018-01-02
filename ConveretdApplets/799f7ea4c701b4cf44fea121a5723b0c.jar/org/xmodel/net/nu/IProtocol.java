// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import org.xmodel.IModelObject;
import java.nio.ByteBuffer;

public interface IProtocol
{
    void initialize(final ByteBuffer p0);
    
    void finalize(final ByteBuffer p0, final int p1, final int p2, final int p3);
    
    IModelObject readElement(final int p0, final ByteBuffer p1);
    
    int writeElement(final int p0, final ByteBuffer p1, final IModelObject p2);
    
    public enum Type
    {
        sessionOpenRequest("sessionOpenRequest", 0), 
        sessionOpenResponse("sessionOpenResponse", 1), 
        sessionCloseRequest("sessionCloseRequest", 2), 
        error("error", 3), 
        attachRequest("attachRequest", 4), 
        attachResponse("attachResponse", 5), 
        detachRequest("detachRequest", 6), 
        syncRequest("syncRequest", 7), 
        syncResponse("syncResponse", 8), 
        addChild("addChild", 9), 
        removeChild("removeChild", 10), 
        changeAttribute("changeAttribute", 11), 
        clearAttribute("clearAttribute", 12), 
        changeDirty("changeDirty", 13), 
        queryRequest("queryRequest", 14), 
        queryResponse("queryResponse", 15), 
        executeRequest("executeRequest", 16), 
        executeResponse("executeResponse", 17), 
        debugStepIn("debugStepIn", 18), 
        debugStepOver("debugStepOver", 19), 
        debugStepOut("debugStepOut", 20);
        
        static {
            A = new Type[] { Type.sessionOpenRequest, Type.sessionOpenResponse, Type.sessionCloseRequest, Type.error, Type.attachRequest, Type.attachResponse, Type.detachRequest, Type.syncRequest, Type.syncResponse, Type.addChild, Type.removeChild, Type.changeAttribute, Type.clearAttribute, Type.changeDirty, Type.queryRequest, Type.queryResponse, Type.executeRequest, Type.executeResponse, Type.debugStepIn, Type.debugStepOver, Type.debugStepOut };
        }
        
        private Type(final String s, final int n) {
        }
    }
}
