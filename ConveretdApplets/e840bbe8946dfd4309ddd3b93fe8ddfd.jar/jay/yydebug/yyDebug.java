// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

public interface yyDebug
{
    void push(final int p0, final Object p1);
    
    void lex(final int p0, final int p1, final String p2, final Object p3);
    
    void shift(final int p0, final int p1, final int p2);
    
    void pop(final int p0);
    
    void reject();
    
    void discard(final int p0, final int p1, final String p2, final Object p3);
    
    void reduce(final int p0, final int p1, final int p2, final String p3, final int p4);
    
    void shift(final int p0, final int p1);
    
    void accept(final Object p0);
    
    void error(final String p0);
}
