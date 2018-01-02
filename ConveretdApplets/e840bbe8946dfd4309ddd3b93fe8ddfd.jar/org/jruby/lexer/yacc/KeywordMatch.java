// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

public class KeywordMatch
{
    public static RubyYaccLexer.Keyword match(final byte[] words) {
        switch (words.length) {
            case 2: {
                return match2(words);
            }
            case 3: {
                return match3(words);
            }
            case 4: {
                return match4(words);
            }
            case 5: {
                return match5(words);
            }
            case 6: {
                return match6(words);
            }
            case 8: {
                return match8(words);
            }
            case 12: {
                return match12(words);
            }
            default: {
                return null;
            }
        }
    }
    
    public static RubyYaccLexer.Keyword match2(final byte[] words) {
        Label_0099: {
            switch (words[0]) {
                case 100: {
                    if (words[1] == 111) {
                        return RubyYaccLexer.Keyword.DO;
                    }
                    break;
                }
                case 105: {
                    switch (words[1]) {
                        case 102: {
                            return RubyYaccLexer.Keyword.IF;
                        }
                        case 110: {
                            return RubyYaccLexer.Keyword.IN;
                        }
                        default: {
                            break Label_0099;
                        }
                    }
                    break;
                }
                case 111: {
                    if (words[1] == 114) {
                        return RubyYaccLexer.Keyword.OR;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match3(final byte[] words) {
        switch (words[0]) {
            case 97: {
                if (words[1] == 110 && words[2] == 100) {
                    return RubyYaccLexer.Keyword.AND;
                }
                break;
            }
            case 100: {
                if (words[1] == 101 && words[2] == 102) {
                    return RubyYaccLexer.Keyword.DEF;
                }
                break;
            }
            case 101: {
                if (words[1] == 110 && words[2] == 100) {
                    return RubyYaccLexer.Keyword.END;
                }
                break;
            }
            case 102: {
                if (words[1] == 111 && words[2] == 114) {
                    return RubyYaccLexer.Keyword.FOR;
                }
                break;
            }
            case 110: {
                switch (words[1]) {
                    case 105: {
                        if (words[2] == 108) {
                            return RubyYaccLexer.Keyword.NIL;
                        }
                        break;
                    }
                    case 111: {
                        if (words[2] == 116) {
                            return RubyYaccLexer.Keyword.NOT;
                        }
                        break;
                    }
                }
                break;
            }
            case 69: {
                if (words[1] == 78 && words[2] == 68) {
                    return RubyYaccLexer.Keyword.LEND;
                }
                break;
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match4(final byte[] words) {
        switch (words[0]) {
            case 99: {
                if (words[1] == 97 && words[2] == 115 && words[3] == 101) {
                    return RubyYaccLexer.Keyword.CASE;
                }
                break;
            }
            case 101: {
                if (words[1] == 108 && words[2] == 115 && words[3] == 101) {
                    return RubyYaccLexer.Keyword.ELSE;
                }
                break;
            }
            case 110: {
                if (words[1] == 101 && words[2] == 120 && words[3] == 116) {
                    return RubyYaccLexer.Keyword.NEXT;
                }
                break;
            }
            case 114: {
                if (words[1] == 101 && words[2] == 100 && words[3] == 111) {
                    return RubyYaccLexer.Keyword.REDO;
                }
                break;
            }
            case 115: {
                if (words[1] == 101 && words[2] == 108 && words[3] == 102) {
                    return RubyYaccLexer.Keyword.SELF;
                }
                break;
            }
            case 116: {
                switch (words[1]) {
                    case 104: {
                        if (words[2] == 101 && words[3] == 110) {
                            return RubyYaccLexer.Keyword.THEN;
                        }
                        break;
                    }
                    case 114: {
                        if (words[2] == 117 && words[3] == 101) {
                            return RubyYaccLexer.Keyword.TRUE;
                        }
                        break;
                    }
                }
                break;
            }
            case 119: {
                if (words[1] == 104 && words[2] == 101 && words[3] == 110) {
                    return RubyYaccLexer.Keyword.WHEN;
                }
                break;
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match5(final byte[] words) {
        switch (words[0]) {
            case 97: {
                if (words[1] == 108 && words[2] == 105 && words[3] == 97 && words[4] == 115) {
                    return RubyYaccLexer.Keyword.ALIAS;
                }
                break;
            }
            case 98: {
                switch (words[1]) {
                    case 101: {
                        if (words[2] == 103 && words[3] == 105 && words[4] == 110) {
                            return RubyYaccLexer.Keyword.BEGIN;
                        }
                        break;
                    }
                    case 114: {
                        if (words[2] == 101 && words[3] == 97 && words[4] == 107) {
                            return RubyYaccLexer.Keyword.BREAK;
                        }
                        break;
                    }
                }
                break;
            }
            case 99: {
                if (words[1] == 108 && words[2] == 97 && words[3] == 115 && words[4] == 115) {
                    return RubyYaccLexer.Keyword.CLASS;
                }
                break;
            }
            case 101: {
                if (words[1] == 108 && words[2] == 115 && words[3] == 105 && words[4] == 102) {
                    return RubyYaccLexer.Keyword.ELSIF;
                }
                break;
            }
            case 102: {
                if (words[1] == 97 && words[2] == 108 && words[3] == 115 && words[4] == 101) {
                    return RubyYaccLexer.Keyword.FALSE;
                }
                break;
            }
            case 114: {
                if (words[1] == 101 && words[2] == 116 && words[3] == 114 && words[4] == 121) {
                    return RubyYaccLexer.Keyword.RETRY;
                }
                break;
            }
            case 115: {
                if (words[1] == 117 && words[2] == 112 && words[3] == 101 && words[4] == 114) {
                    return RubyYaccLexer.Keyword.SUPER;
                }
                break;
            }
            case 117: {
                if (words[1] == 110) {
                    switch (words[2]) {
                        case 100: {
                            if (words[3] == 101 && words[4] == 102) {
                                return RubyYaccLexer.Keyword.UNDEF;
                            }
                            break;
                        }
                        case 116: {
                            if (words[3] == 105 && words[4] == 108) {
                                return RubyYaccLexer.Keyword.UNTIL;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            case 121: {
                if (words[1] == 105 && words[2] == 101 && words[3] == 108 && words[4] == 100) {
                    return RubyYaccLexer.Keyword.YIELD;
                }
                break;
            }
            case 119: {
                if (words[1] == 104 && words[2] == 105 && words[3] == 108 && words[4] == 101) {
                    return RubyYaccLexer.Keyword.WHILE;
                }
                break;
            }
            case 66: {
                if (words[1] == 69 && words[2] == 71 && words[3] == 73 && words[4] == 78) {
                    return RubyYaccLexer.Keyword.LBEGIN;
                }
                break;
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match6(final byte[] words) {
        switch (words[0]) {
            case 101: {
                if (words[1] == 110 && words[2] == 115 && words[3] == 117 && words[4] == 114 && words[5] == 101) {
                    return RubyYaccLexer.Keyword.ENSURE;
                }
                break;
            }
            case 109: {
                if (words[1] == 111 && words[2] == 100 && words[3] == 117 && words[4] == 108 && words[5] == 101) {
                    return RubyYaccLexer.Keyword.MODULE;
                }
                break;
            }
            case 114: {
                if (words[1] == 101) {
                    switch (words[2]) {
                        case 116: {
                            if (words[3] == 117 && words[4] == 114 && words[5] == 110) {
                                return RubyYaccLexer.Keyword.RETURN;
                            }
                            break;
                        }
                        case 115: {
                            if (words[3] == 99 && words[4] == 117 && words[5] == 101) {
                                return RubyYaccLexer.Keyword.RESCUE;
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            case 117: {
                if (words[1] == 110 && words[2] == 108 && words[3] == 101 && words[4] == 115 && words[5] == 115) {
                    return RubyYaccLexer.Keyword.UNLESS;
                }
                break;
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match8(final byte[] words) {
        Label_0220: {
            switch (words[0]) {
                case 100: {
                    if ((words[1] == 101 && words[2] == 102 && words[3] == 105 && words[4] == 110 && words[5] == 101 && words[6] == 100) || words[7] == 63) {
                        return RubyYaccLexer.Keyword.DEFINED_P;
                    }
                    break;
                }
                case 95: {
                    if (words[1] != 95) {
                        break;
                    }
                    switch (words[2]) {
                        case 76: {
                            if (words[3] == 73 && words[4] == 78 && words[5] == 69 && words[6] == 95 && words[7] == 95) {
                                return RubyYaccLexer.Keyword.__LINE__;
                            }
                        }
                        case 70: {
                            if (words[3] == 73 && words[4] == 76 && words[5] == 69 && words[6] == 95 && words[7] == 95) {
                                return RubyYaccLexer.Keyword.__FILE__;
                            }
                            break Label_0220;
                        }
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public static RubyYaccLexer.Keyword match12(final byte[] words) {
        if (words[0] == 95 && words[1] == 95 && words[2] == 101 && words[3] == 110 && words[4] == 99 && words[5] == 111 && words[6] == 100 && words[7] == 105 && words[8] == 110 && words[9] == 103 && words[10] == 95 && words[11] == 95) {
            return RubyYaccLexer.Keyword.__ENCODING__;
        }
        return null;
    }
}
