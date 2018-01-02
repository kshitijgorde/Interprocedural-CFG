// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

public final class MultiSink implements ILogSink
{
    private ILogSink[] C;
    
    public MultiSink(final ILogSink... c) {
        this.C = c;
    }
    
    @Override
    public void log(final Log log, final int n, final Object o, final Throwable t) {
        ILogSink[] c;
        for (int length = (c = this.C).length, i = 0; i < length; ++i) {
            c[i].log(log, n, o, t);
        }
    }
    
    @Override
    public void log(final Log log, final int n, final Object o) {
        ILogSink[] c;
        for (int length = (c = this.C).length, i = 0; i < length; ++i) {
            c[i].log(log, n, o);
        }
    }
    
    @Override
    public void log(final Log log, final int n, final Throwable t) {
        ILogSink[] c;
        for (int length = (c = this.C).length, i = 0; i < length; ++i) {
            c[i].log(log, n, t);
        }
    }
}
