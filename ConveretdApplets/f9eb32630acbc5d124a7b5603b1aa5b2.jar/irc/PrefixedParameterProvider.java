// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class PrefixedParameterProvider implements ParameterProvider
{
    private String _prefix;
    private ParameterProvider _source;
    
    public PrefixedParameterProvider(final ParameterProvider source, final String prefix) {
        this._source = source;
        this._prefix = prefix;
    }
    
    public String getParameter(final String s) {
        return this._source.getParameter(this._prefix + s);
    }
}
