// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.ast.NodeType;
import org.jruby.parser.StaticScope;
import java.math.BigInteger;
import org.jcodings.Encoding;
import org.jruby.util.ByteList;
import org.jruby.runtime.CallType;
import org.jruby.compiler.impl.BaseBodyCompiler;

public interface CacheCompiler
{
    void cacheCallSite(final BaseBodyCompiler p0, final String p1, final CallType p2);
    
    void cacheString(final BaseBodyCompiler p0, final ByteList p1, final int p2);
    
    void cacheByteList(final BaseBodyCompiler p0, final ByteList p1);
    
    void cacheEncoding(final BaseBodyCompiler p0, final Encoding p1);
    
    void cacheSymbol(final BaseBodyCompiler p0, final String p1);
    
    void cacheFixnum(final BaseBodyCompiler p0, final long p1);
    
    void cacheFloat(final BaseBodyCompiler p0, final double p1);
    
    void cacheBigInteger(final BaseBodyCompiler p0, final BigInteger p1);
    
    void cachedGetVariable(final BaseBodyCompiler p0, final String p1);
    
    void cachedSetVariable(final BaseBodyCompiler p0, final String p1, final CompilerCallback p2);
    
    void cacheRegexp(final BaseBodyCompiler p0, final ByteList p1, final int p2);
    
    void cacheDRegexp(final BaseBodyCompiler p0, final CompilerCallback p1, final int p2);
    
    void cacheClosure(final BaseBodyCompiler p0, final String p1, final int p2, final StaticScope p3, final String p4, final int p5, final boolean p6, final NodeType p7, final ASTInspector p8);
    
    void cacheClosure19(final BaseBodyCompiler p0, final String p1, final int p2, final StaticScope p3, final String p4, final int p5, final boolean p6, final NodeType p7, final String p8, final ASTInspector p9);
    
    void cacheSpecialClosure(final BaseBodyCompiler p0, final String p1);
    
    void cacheConstant(final BaseBodyCompiler p0, final String p1);
    
    void cacheConstantFrom(final BaseBodyCompiler p0, final String p1);
    
    void cacheStaticScope(final BaseBodyCompiler p0, final StaticScope p1);
    
    void cacheMethod(final BaseBodyCompiler p0, final String p1);
    
    void cacheMethod(final BaseBodyCompiler p0, final String p1, final int p2);
    
    void finish();
}
