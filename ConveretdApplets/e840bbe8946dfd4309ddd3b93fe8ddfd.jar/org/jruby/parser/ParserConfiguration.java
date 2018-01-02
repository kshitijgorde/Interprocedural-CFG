// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.parser;

import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.util.KCode;
import org.jruby.runtime.encoding.EncodingService;
import org.jruby.RubyInstanceConfig;
import org.jruby.util.SafePropertyAccessor;
import org.jruby.util.ByteList;
import org.jruby.Ruby;
import org.jcodings.Encoding;
import org.jruby.CompatVersion;
import org.jruby.runtime.DynamicScope;

public class ParserConfiguration
{
    private DynamicScope existingScope;
    private boolean asBlock;
    private int lineNumber;
    private boolean inlineSource;
    private boolean isEvalParse;
    private boolean extraPositionInformation;
    private boolean isDubyExtensionsEnabled;
    private boolean isDebug;
    private boolean saveData;
    private CompatVersion version;
    private Encoding defaultEncoding;
    private Ruby runtime;
    private static final ByteList USASCII;
    
    public ParserConfiguration(final Ruby runtime, final int lineNumber, final boolean inlineSource, final CompatVersion version) {
        this(runtime, lineNumber, false, inlineSource, version);
    }
    
    public ParserConfiguration(final Ruby runtime, final int lineNumber, final boolean extraPositionInformation, final boolean inlineSource, final CompatVersion version) {
        this(runtime, lineNumber, extraPositionInformation, inlineSource, true, version, false);
    }
    
    public ParserConfiguration(final Ruby runtime, final int lineNumber, final boolean extraPositionInformation, final boolean inlineSource, final boolean isFileParse, final CompatVersion version, final boolean saveData) {
        this.existingScope = null;
        this.asBlock = false;
        this.lineNumber = 0;
        this.inlineSource = false;
        this.isEvalParse = true;
        this.extraPositionInformation = false;
        this.isDubyExtensionsEnabled = SafePropertyAccessor.getBoolean("jruby.duby.enabled", false);
        this.isDebug = false;
        this.saveData = false;
        this.runtime = runtime;
        this.inlineSource = inlineSource;
        this.lineNumber = lineNumber;
        this.extraPositionInformation = extraPositionInformation;
        this.isEvalParse = !isFileParse;
        this.version = version;
        this.saveData = saveData;
    }
    
    public ParserConfiguration(final Ruby runtime, final int lineNumber, final boolean extraPositionInformation, final boolean inlineSource, final boolean isFileParse, final RubyInstanceConfig config) {
        this(runtime, lineNumber, extraPositionInformation, inlineSource, isFileParse, false, config);
    }
    
    public ParserConfiguration(final Ruby runtime, final int lineNumber, final boolean extraPositionInformation, final boolean inlineSource, final boolean isFileParse, final boolean saveData, final RubyInstanceConfig config) {
        this(runtime, lineNumber, extraPositionInformation, inlineSource, isFileParse, config.getCompatVersion(), saveData);
        this.isDebug = config.isParserDebug();
    }
    
    public void setDefaultEncoding(final Encoding encoding) {
        this.defaultEncoding = encoding;
    }
    
    public Encoding getDefaultEncoding() {
        if (this.defaultEncoding == null) {
            this.defaultEncoding = this.getEncodingService().loadEncoding(ParserConfiguration.USASCII);
        }
        return this.defaultEncoding;
    }
    
    public EncodingService getEncodingService() {
        return this.runtime.getEncodingService();
    }
    
    public void setEvalParse(final boolean isEvalParse) {
        this.isEvalParse = isEvalParse;
    }
    
    public void setExtraPositionInformation(final boolean extraPositionInformation) {
        this.extraPositionInformation = extraPositionInformation;
    }
    
    public boolean hasExtraPositionInformation() {
        return this.extraPositionInformation;
    }
    
    public boolean isDebug() {
        return this.isDebug;
    }
    
    public boolean isEvalParse() {
        return this.isEvalParse;
    }
    
    public KCode getKCode() {
        return this.runtime.getKCode();
    }
    
    public int getLineNumber() {
        return this.lineNumber;
    }
    
    public void parseAsBlock(final DynamicScope existingScope) {
        this.asBlock = true;
        this.existingScope = existingScope;
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public DynamicScope getScope() {
        if (this.asBlock) {
            return this.existingScope;
        }
        return new ManyVarsDynamicScope(new LocalStaticScope(null), this.existingScope);
    }
    
    public CompatVersion getVersion() {
        return this.version;
    }
    
    public boolean isSaveData() {
        return this.saveData;
    }
    
    public boolean isInlineSource() {
        return this.inlineSource;
    }
    
    public boolean isDubyExtensionsEnabled() {
        return this.isDubyExtensionsEnabled;
    }
    
    static {
        USASCII = new ByteList(new byte[] { 85, 83, 45, 65, 83, 67, 73, 73 });
    }
}
