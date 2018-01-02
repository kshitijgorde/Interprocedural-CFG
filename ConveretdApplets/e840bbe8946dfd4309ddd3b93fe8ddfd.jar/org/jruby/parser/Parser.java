// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.runtime.ThreadContext;
import org.jruby.RubyString;
import org.jruby.RubyHash;
import org.jruby.lexer.yacc.SyntaxException;
import java.io.IOException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyFile;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.load.LoadServiceResourceInputStream;
import java.io.InputStream;
import org.jruby.RubyArray;
import java.util.List;
import org.jruby.lexer.yacc.LexerSource;
import org.jruby.ast.Node;
import org.jruby.runtime.DynamicScope;
import org.jruby.util.ByteList;
import org.jruby.Ruby;

public class Parser
{
    private final Ruby runtime;
    private volatile long totalTime;
    private volatile int totalBytes;
    
    public Parser(final Ruby runtime) {
        this.runtime = runtime;
    }
    
    public long getTotalTime() {
        return this.totalTime;
    }
    
    public int getTotalBytes() {
        return this.totalBytes;
    }
    
    public Node parse(final String file, final ByteList content, final DynamicScope blockScope, final ParserConfiguration configuration) {
        configuration.setDefaultEncoding(content.getEncoding());
        return this.parse(file, content.bytes(), blockScope, configuration);
    }
    
    public Node parse(final String file, final byte[] content, final DynamicScope blockScope, final ParserConfiguration configuration) {
        final RubyArray list = this.getLines(configuration, this.runtime, file);
        final LexerSource lexerSource = LexerSource.getSource(file, content, list, configuration);
        return this.parse(file, lexerSource, blockScope, configuration);
    }
    
    public Node parse(final String file, final InputStream content, final DynamicScope blockScope, final ParserConfiguration configuration) {
        final RubyArray list = this.getLines(configuration, this.runtime, file);
        if (content instanceof LoadServiceResourceInputStream) {
            return this.parse(file, ((LoadServiceResourceInputStream)content).getBytes(), blockScope, configuration);
        }
        final LexerSource lexerSource = LexerSource.getSource(file, content, list, configuration);
        return this.parse(file, lexerSource, blockScope, configuration);
    }
    
    public Node parse(final String file, final LexerSource lexerSource, final DynamicScope blockScope, final ParserConfiguration configuration) {
        if (blockScope != null) {
            configuration.parseAsBlock(blockScope);
        }
        final long startTime = System.nanoTime();
        final RubyParser parser = RubyParserPool.getInstance().borrowParser(configuration.getVersion());
        RubyParserResult result = null;
        parser.setWarnings(this.runtime.getWarnings());
        try {
            result = parser.parse(configuration, lexerSource);
            if (result.getEndOffset() >= 0 && configuration.isSaveData()) {
                final IRubyObject verbose = this.runtime.getVerbose();
                this.runtime.setVerbose(this.runtime.getNil());
                try {
                    this.runtime.defineGlobalConstant("DATA", new RubyFile(this.runtime, file, lexerSource.getRemainingAsStream()));
                }
                catch (IOException e3) {
                    this.runtime.defineGlobalConstant("DATA", this.runtime.getNil());
                }
                this.runtime.setVerbose(verbose);
                result.setEndOffset(-1);
            }
        }
        catch (IOException e) {
            throw this.runtime.newSyntaxError("Problem reading source: " + e);
        }
        catch (SyntaxException e2) {
            switch (e2.getPid()) {
                case UNKNOWN_ENCODING:
                case NOT_ASCII_COMPATIBLE: {
                    throw this.runtime.newArgumentError(e2.getMessage());
                }
                default: {
                    final StringBuilder buffer = new StringBuilder(100);
                    buffer.append(e2.getPosition().getFile()).append(':');
                    buffer.append(e2.getPosition().getStartLine() + 1).append(": ");
                    buffer.append(e2.getMessage());
                    throw this.runtime.newSyntaxError(buffer.toString());
                }
            }
        }
        finally {
            RubyParserPool.getInstance().returnParser(parser);
        }
        if (result.getScope() != null) {
            result.getScope().growIfNeeded();
        }
        final Node ast = result.getAST();
        this.totalTime += System.nanoTime() - startTime;
        this.totalBytes += lexerSource.getOffset();
        return ast;
    }
    
    private RubyArray getLines(final ParserConfiguration configuration, final Ruby runtime, final String file) {
        RubyArray list = null;
        final IRubyObject scriptLines = runtime.getObject().fastGetConstantAt("SCRIPT_LINES__");
        if (!configuration.isEvalParse() && scriptLines != null && scriptLines instanceof RubyHash) {
            final RubyString filename = runtime.newString(file);
            final ThreadContext context = runtime.getCurrentContext();
            final IRubyObject object = ((RubyHash)scriptLines).op_aref(context, filename);
            list = (RubyArray)((object instanceof RubyArray) ? object : runtime.newArray());
            ((RubyHash)scriptLines).op_aset(context, filename, list);
        }
        return list;
    }
}
