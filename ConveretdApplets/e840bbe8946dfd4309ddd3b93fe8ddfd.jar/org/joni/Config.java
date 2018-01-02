// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import java.io.PrintStream;

public interface Config extends org.jcodings.Config
{
    public static final int CHAR_TABLE_SIZE = 256;
    public static final boolean USE_NAMED_GROUP = true;
    public static final boolean USE_SUBEXP_CALL = true;
    public static final boolean USE_BACKREF_WITH_LEVEL = true;
    public static final boolean USE_MONOMANIAC_CHECK_CAPTURES_IN_ENDLESS_REPEAT = true;
    public static final boolean USE_NEWLINE_AT_END_OF_STRING_HAS_EMPTY_LINE = true;
    public static final boolean USE_WARNING_REDUNDANT_NESTED_REPEAT_OPERATOR = false;
    public static final boolean CASE_FOLD_IS_APPLIED_INSIDE_NEGATIVE_CCLASS = true;
    public static final boolean USE_MATCH_RANGE_MUST_BE_INSIDE_OF_SPECIFIED_RANGE = false;
    public static final boolean USE_CAPTURE_HISTORY = false;
    public static final boolean USE_VARIABLE_META_CHARS = true;
    public static final boolean USE_WORD_BEGIN_END = true;
    public static final boolean USE_POSIX_API_REGION_OPTION = true;
    public static final boolean USE_FIND_LONGEST_SEARCH_ALL_OF_RANGE = true;
    public static final boolean USE_COMBINATION_EXPLOSION_CHECK = false;
    public static final int NREGION = 10;
    public static final int MAX_BACKREF_NUM = 1000;
    public static final int MAX_REPEAT_NUM = 100000;
    public static final int MAX_MULTI_BYTE_RANGES_NUM = 10000;
    public static final boolean USE_WARN = true;
    public static final boolean USE_PARSE_TREE_NODE_RECYCLE = true;
    public static final boolean USE_OP_PUSH_OR_JUMP_EXACT = true;
    public static final boolean USE_SHARED_CCLASS_TABLE = false;
    public static final boolean USE_QTFR_PEEK_NEXT = true;
    public static final int INIT_MATCH_STACK_SIZE = 64;
    public static final int DEFAULT_MATCH_STACK_LIMIT_SIZE = 0;
    public static final int NUMBER_OF_POOLED_STACKS = 4;
    public static final boolean DONT_OPTIMIZE = false;
    public static final int MAX_CAPTURE_HISTORY_GROUP = 31;
    public static final int CHECK_STRING_THRESHOLD_LEN = 7;
    public static final int CHECK_BUFF_MAX_SIZE = 16384;
    public static final PrintStream log = System.out;
    public static final PrintStream err = System.err;
    public static final boolean DEBUG_ALL = false;
    public static final boolean DEBUG = false;
    public static final boolean DEBUG_PARSE_TREE = false;
    public static final boolean DEBUG_COMPILE = false;
    public static final boolean DEBUG_COMPILE_BYTE_CODE_INFO = false;
    public static final boolean DEBUG_SEARCH = false;
    public static final boolean DEBUG_MATCH = false;
    public static final boolean DEBUG_ASM = true;
    public static final boolean DEBUG_ASM_EXEC = true;
}
