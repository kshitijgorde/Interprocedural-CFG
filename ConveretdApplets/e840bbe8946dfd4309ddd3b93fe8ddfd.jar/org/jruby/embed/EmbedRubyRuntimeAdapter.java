// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

import java.io.InputStream;
import java.io.Reader;
import org.jruby.RubyRuntimeAdapter;

public interface EmbedRubyRuntimeAdapter extends RubyRuntimeAdapter
{
    EmbedEvalUnit parse(final String p0, final int... p1);
    
    EmbedEvalUnit parse(final Reader p0, final String p1, final int... p2);
    
    EmbedEvalUnit parse(final PathType p0, final String p1, final int... p2);
    
    EmbedEvalUnit parse(final InputStream p0, final String p1, final int... p2);
}
