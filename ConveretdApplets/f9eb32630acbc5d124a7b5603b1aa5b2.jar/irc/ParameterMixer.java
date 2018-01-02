// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class ParameterMixer implements ParameterProvider
{
    private ParameterProvider _p1;
    private ParameterProvider _p2;
    
    public ParameterMixer(final ParameterProvider p2, final ParameterProvider p3) {
        this._p1 = p2;
        this._p2 = p3;
    }
    
    public String getParameter(final String s) {
        final String parameter = this._p1.getParameter(s);
        if (parameter == null) {
            return this._p2.getParameter(s);
        }
        return parameter;
    }
}
