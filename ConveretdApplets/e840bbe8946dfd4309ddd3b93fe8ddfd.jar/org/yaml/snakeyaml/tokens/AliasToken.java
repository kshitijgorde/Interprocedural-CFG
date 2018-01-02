// 
// Decompiled by Procyon v0.5.30
// 

package org.yaml.snakeyaml.tokens;

import org.yaml.snakeyaml.error.Mark;

public final class AliasToken extends Token
{
    private final String value;
    
    public AliasToken(final String value, final Mark startMark, final Mark endMark) {
        super(startMark, endMark);
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    protected String getArguments() {
        return "value=" + this.value;
    }
    
    public ID getTokenId() {
        return ID.Alias;
    }
}
