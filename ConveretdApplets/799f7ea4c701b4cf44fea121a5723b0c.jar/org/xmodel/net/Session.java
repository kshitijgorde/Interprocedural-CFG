// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import java.io.IOException;
import org.xmodel.external.IExternalReference;

public final class Session
{
    private Protocol C;
    private ILink A;
    private int B;
    
    public Session(final Protocol c, final ILink a, final int b) {
        this.C = c;
        this.A = a;
        this.B = b;
    }
    
    public void attach(final String s, final IExternalReference externalReference) throws IOException {
        this.C.attach(this.A, this.B, s, externalReference);
    }
    
    public void detach() throws IOException {
        this.C.detach(this.A, this.B);
    }
    
    public Object query(final IContext context, final String s, final int n) throws IOException {
        return this.C.query(this.A, this.B, context, s, n);
    }
    
    public Object[] execute(final StatefulContext statefulContext, final String[] array, final IModelObject modelObject, final int n) throws IOException {
        return this.C.execute(this.A, this.B, statefulContext, array, modelObject, n);
    }
    
    public final void debugGo() throws IOException {
        this.C.sendDebugGo(this.A, this.B);
    }
    
    public final void debugStop() throws IOException {
        this.C.sendDebugStop(this.A, this.B);
    }
    
    public final void debugStepIn() throws IOException {
        this.C.sendDebugStepIn(this.A, this.B);
    }
    
    public final void debugStepOut() throws IOException {
        this.C.sendDebugStepOut(this.A, this.B);
    }
    
    public final void debugStepOver() throws IOException {
        this.C.sendDebugStepOver(this.A, this.B);
    }
    
    public void close() {
        try {
            this.C.closeSession(this.A, this.B);
        }
        catch (IOException ex) {}
    }
}
