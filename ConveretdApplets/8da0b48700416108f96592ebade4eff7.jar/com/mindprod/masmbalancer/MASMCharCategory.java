// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masmbalancer;

public enum MASMCharCategory
{
    EOL, 
    IGNORE, 
    PLAIN, 
    QUOTE, 
    SEMICOLON, 
    SPACE, 
    TICK;
    
    static MASMCharCategory categorise(final char theChar) {
        switch (theChar) {
            case '\t':
            case ' ':
            case ' ': {
                return MASMCharCategory.SPACE;
            }
            case '\n': {
                return MASMCharCategory.EOL;
            }
            case '\r':
            case '\u007f': {
                return MASMCharCategory.IGNORE;
            }
            case '\"': {
                return MASMCharCategory.QUOTE;
            }
            case '\'': {
                return MASMCharCategory.TICK;
            }
            case ';': {
                return MASMCharCategory.SEMICOLON;
            }
            default: {
                return MASMCharCategory.PLAIN;
            }
        }
    }
}
