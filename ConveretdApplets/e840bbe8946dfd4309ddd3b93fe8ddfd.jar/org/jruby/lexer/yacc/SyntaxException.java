// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class SyntaxException extends RuntimeException
{
    private static final long serialVersionUID = -2130930815167932274L;
    private ISourcePosition position;
    private PID pid;
    
    public SyntaxException(final PID pid, final ISourcePosition position, final String lastLine, final String message, final Object... data) {
        super(prepareMessage(message, lastLine));
        this.pid = pid;
        this.position = position;
    }
    
    private static String prepareMessage(final String message, final String line) {
        if (line != null && line.length() > 5) {
            final boolean addNewline = message != null && message.endsWith("\n");
            return message + (addNewline ? "\n" : "") + line;
        }
        return message;
    }
    
    public ISourcePosition getPosition() {
        return this.position;
    }
    
    public PID getPid() {
        return this.pid;
    }
    
    public enum PID
    {
        BAD_HEX_NUMBER("BAD_HEX_NUMBER"), 
        BAD_IDENTIFIER("BAD_IDENTIFIER"), 
        BAD_OCTAL_DIGIT("BAD_OCTAL_DIGIT"), 
        BLOCK_ARG_UNEXPECTED("BLOCK_ARG_UNEXPECTED"), 
        BLOCK_ARG_AND_BLOCK_GIVEN("BLOCK_ARG_AND_BLOCK_GIVEN"), 
        CHARACTER_BAD("CHARACTER_BAD"), 
        CANNOT_CHANGE_SELF("CANNOT_CHANGE_SELF"), 
        CVAR_BAD_NAME("CVAR_BAD_NAME"), 
        DYNAMIC_CONSTANT_ASSIGNMENT("DYNAMIC_CONSTANT_ASSIGNMENT"), 
        EMPTY_BINARY_NUMBER("EMPTY_BINARY_NUMBER"), 
        FLOAT_MISSING_ZERO("FLOAT_MISSING_ZERO"), 
        GRAMMAR_ERROR("GRAMMAR_ERROR"), 
        INCOMPLETE_CHAR_SYNTAX("INCOMPLETE_CHAR_SYNTAX"), 
        INVALID_ASSIGNMENT("INVALID_ASSIGNMENT"), 
        INVALID_ESCAPE_SYNTAX("INVALID_ESCAPE_SYNTAX"), 
        IVAR_BAD_NAME("IVAR_BAD_NAME"), 
        MULTIPLE_ASSIGNMENT_IN_CONDITIONAL("MULTIPLE_ASSIGNMENT_IN_CONDITIONAL"), 
        REGEXP_UNKNOWN_OPTION("REGEXP_UNKNOWN_OPTION"), 
        STRING_HITS_EOF("STRING_HITS_EOF"), 
        STRING_MARKER_MISSING("STRING_MARKER_MISSING"), 
        STRING_UNKNOWN_TYPE("STRING_UNKNOWN_TYPE"), 
        TRAILING_UNDERSCORE_IN_NUMBER("TRAILING_UNDERSCORE_IN_NUMBER"), 
        DUBY_EXTENSIONS_OFF("DUBY_EXTENSIONS_OFF"), 
        BLOCK_GIVEN_TO_YIELD("BLOCK_GIVEN_TO_YIELD"), 
        VOID_VALUE_EXPRESSION("VOID_VALUE_EXPRESSION"), 
        UNKNOWN_ENCODING("UNKNOWN_ENCODING"), 
        NOT_ASCII_COMPATIBLE("NOT_ASCII_COMPATIBLE"), 
        MIXED_ENCODING("MIXED_ENCODNIG"), 
        NUL_IN_SYMBOL("NUL_IN_SYMBOL"), 
        REGEXP_ENCODING_MISMATCH("REGEXP_ENCODING_MISMATCH"), 
        INVALID_MULTIBYTE_CHAR("INVALID_MULTIBYTE_CHAR");
        
        private String id;
        
        private PID(final String id) {
            this.id = id;
        }
        
        public String getID() {
            return this.id;
        }
    }
}
