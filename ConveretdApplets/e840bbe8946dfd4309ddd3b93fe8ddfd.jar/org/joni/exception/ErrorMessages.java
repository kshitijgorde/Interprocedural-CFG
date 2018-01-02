// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.exception;

public interface ErrorMessages extends org.jcodings.exception.ErrorMessages
{
    public static final String MISMATCH = "mismatch";
    public static final String NO_SUPPORT_CONFIG = "no support in this configuration";
    public static final String ERR_MEMORY = "fail to memory allocation";
    public static final String ERR_MATCH_STACK_LIMIT_OVER = "match-stack limit over";
    public static final String ERR_TYPE_BUG = "undefined type (bug)";
    public static final String ERR_PARSER_BUG = "internal parser error (bug)";
    public static final String ERR_STACK_BUG = "stack error (bug)";
    public static final String ERR_UNDEFINED_BYTECODE = "undefined bytecode (bug)";
    public static final String ERR_UNEXPECTED_BYTECODE = "unexpected bytecode (bug)";
    public static final String ERR_DEFAULT_ENCODING_IS_NOT_SETTED = "default multibyte-encoding is not setted";
    public static final String ERR_SPECIFIED_ENCODING_CANT_CONVERT_TO_WIDE_CHAR = "can't convert to wide-char on specified multibyte-encoding";
    public static final String ERR_INVALID_ARGUMENT = "invalid argument";
    public static final String ERR_END_PATTERN_AT_LEFT_BRACE = "end pattern at left brace";
    public static final String ERR_END_PATTERN_AT_LEFT_BRACKET = "end pattern at left bracket";
    public static final String ERR_EMPTY_CHAR_CLASS = "empty char-class";
    public static final String ERR_PREMATURE_END_OF_CHAR_CLASS = "premature end of char-class";
    public static final String ERR_END_PATTERN_AT_ESCAPE = "end pattern at escape";
    public static final String ERR_END_PATTERN_AT_META = "end pattern at meta";
    public static final String ERR_END_PATTERN_AT_CONTROL = "end pattern at control";
    public static final String ERR_META_CODE_SYNTAX = "invalid meta-code syntax";
    public static final String ERR_CONTROL_CODE_SYNTAX = "invalid control-code syntax";
    public static final String ERR_CHAR_CLASS_VALUE_AT_END_OF_RANGE = "char-class value at end of range";
    public static final String ERR_CHAR_CLASS_VALUE_AT_START_OF_RANGE = "char-class value at start of range";
    public static final String ERR_UNMATCHED_RANGE_SPECIFIER_IN_CHAR_CLASS = "unmatched range specifier in char-class";
    public static final String ERR_TARGET_OF_REPEAT_OPERATOR_NOT_SPECIFIED = "target of repeat operator is not specified";
    public static final String ERR_TARGET_OF_REPEAT_OPERATOR_INVALID = "target of repeat operator is invalid";
    public static final String ERR_NESTED_REPEAT_OPERATOR = "nested repeat operator";
    public static final String ERR_UNMATCHED_CLOSE_PARENTHESIS = "unmatched close parenthesis";
    public static final String ERR_END_PATTERN_WITH_UNMATCHED_PARENTHESIS = "end pattern with unmatched parenthesis";
    public static final String ERR_END_PATTERN_IN_GROUP = "end pattern in group";
    public static final String ERR_UNDEFINED_GROUP_OPTION = "undefined group option";
    public static final String ERR_INVALID_POSIX_BRACKET_TYPE = "invalid POSIX bracket type";
    public static final String ERR_INVALID_LOOK_BEHIND_PATTERN = "invalid pattern in look-behind";
    public static final String ERR_INVALID_REPEAT_RANGE_PATTERN = "invalid repeat range {lower,upper}";
    public static final String ERR_TOO_BIG_NUMBER = "too big number";
    public static final String ERR_TOO_BIG_NUMBER_FOR_REPEAT_RANGE = "too big number for repeat range";
    public static final String ERR_UPPER_SMALLER_THAN_LOWER_IN_REPEAT_RANGE = "upper is smaller than lower in repeat range";
    public static final String ERR_EMPTY_RANGE_IN_CHAR_CLASS = "empty range in char class";
    public static final String ERR_MISMATCH_CODE_LENGTH_IN_CLASS_RANGE = "mismatch multibyte code length in char-class range";
    public static final String ERR_TOO_MANY_MULTI_BYTE_RANGES = "too many multibyte code ranges are specified";
    public static final String ERR_TOO_SHORT_MULTI_BYTE_STRING = "too short multibyte code string";
    public static final String ERR_TOO_BIG_BACKREF_NUMBER = "too big backref number";
    public static final String ERR_INVALID_BACKREF = "invalid backref number/name";
    public static final String ERR_NUMBERED_BACKREF_OR_CALL_NOT_ALLOWED = "numbered backref/call is not allowed. (use name)";
    public static final String ERR_INVALID_WIDE_CHAR_VALUE = "invalid wide-char value";
    public static final String ERR_EMPTY_GROUP_NAME = "group name is empty";
    public static final String ERR_INVALID_GROUP_NAME = "invalid group name <%n>";
    public static final String ERR_INVALID_CHAR_IN_GROUP_NAME = "invalid char in group name <%n>";
    public static final String ERR_UNDEFINED_NAME_REFERENCE = "undefined name <%n> reference";
    public static final String ERR_UNDEFINED_GROUP_REFERENCE = "undefined group <%n> reference";
    public static final String ERR_MULTIPLEX_DEFINED_NAME = "multiplex defined name <%n>";
    public static final String ERR_MULTIPLEX_DEFINITION_NAME_CALL = "multiplex definition name <%n> call";
    public static final String ERR_NEVER_ENDING_RECURSION = "never ending recursion";
    public static final String ERR_GROUP_NUMBER_OVER_FOR_CAPTURE_HISTORY = "group number is too big for capture history";
    public static final String ERR_NOT_SUPPORTED_ENCODING_COMBINATION = "not supported encoding combination";
    public static final String ERR_INVALID_COMBINATION_OF_OPTIONS = "invalid combination of options";
    public static final String ERR_OVER_THREAD_PASS_LIMIT_COUNT = "over thread pass limit count";
    public static final String ERR_TOO_BIG_SB_CHAR_VALUE = "too big singlebyte char value";
}
