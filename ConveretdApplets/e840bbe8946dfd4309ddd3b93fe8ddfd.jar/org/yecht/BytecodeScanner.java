// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.io.IOException;

public class BytecodeScanner implements DefaultYAMLParser.yyInput
{
    public static final int QUOTELEN = 128;
    private Parser parser;
    private Object lval;
    private int currentToken;
    private static final int Start = 1;
    private static final int Document = 2;
    private static final int Directive = 3;
    private static final int Comment = 4;
    private static final int Scalar = 5;
    private static final int Scalar2 = 6;
    private static final int ScalarEnd = 7;
    
    public BytecodeScanner(final Parser parser) {
        this.currentToken = -1;
        this.parser = parser;
    }
    
    public Object value() {
        return this.lval;
    }
    
    public int token() {
        return this.currentToken;
    }
    
    public boolean advance() throws IOException {
        this.currentToken = this.real_yylex();
        return this.currentToken != 0;
    }
    
    private void YYPOS(final int n) {
        this.parser.cursor = this.parser.token + n;
    }
    
    private void FORCE_NEXT_TOKEN(final int n) {
        this.parser.force_token = n;
    }
    
    private void CHK_NL(final int ptr) {
        if (this.parser.buffer.buffer[ptr - 1] == 10 && ptr > this.parser.linectptr) {
            this.parser.lineptr = ptr;
            final Parser parser = this.parser;
            ++parser.linect;
            this.parser.linectptr = this.parser.lineptr;
        }
    }
    
    private boolean ADD_BYTE_LEVEL(final Level lvl, final int len, final LevelStatus s) {
        switch (lvl.status) {
            case seq: {
                ++lvl.ncount;
                this.parser.addLevel(len, LevelStatus.open);
                this.YYPOS(0);
                return true;
            }
            case map: {
                ++lvl.ncount;
                this.parser.addLevel(len, s);
                return false;
            }
            case open: {
                lvl.status = s;
                return false;
            }
            default: {
                this.parser.addLevel(len, s);
                return false;
            }
        }
    }
    
    private int real_yylex() throws IOException {
        Level lvl = null;
        QuotedString q = null;
        int tok = -1;
        if (this.parser.cursor == -1) {
            this.parser.read();
        }
        if (this.parser.force_token != 0) {
            final int t = this.parser.force_token;
            this.parser.force_token = 0;
            return t;
        }
        int mainLoopGoto = 1;
        lvl = this.parser.currentLevel();
        if (lvl.status == LevelStatus.doc) {
            mainLoopGoto = 2;
        }
        this.parser.token = this.parser.cursor;
        int gotoPoint = -1;
        byte yych = 0;
        while (gotoPoint != -2) {
            final int currentGoto = gotoPoint;
            gotoPoint = -2;
            Label_0480: {
                switch (currentGoto) {
                    case -1: {
                        if (this.parser.limit - this.parser.cursor < 3) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 0: {
                                gotoPoint = 2;
                                continue;
                            }
                            case 68: {
                                gotoPoint = 3;
                                continue;
                            }
                            default: {
                                gotoPoint = 5;
                                continue;
                            }
                        }
                        break;
                    }
                    case 2: {
                        this.parser.cursor = this.parser.marker;
                        gotoPoint = 4;
                        continue;
                    }
                    case 3: {
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 6;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 8;
                                continue;
                            }
                            default: {
                                gotoPoint = 4;
                                continue;
                            }
                        }
                        break;
                    }
                    case 4: {
                        this.YYPOS(0);
                        mainLoopGoto = 2;
                    }
                    case 5: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 4;
                        continue;
                    }
                    case 6: {
                        final Parser parser2 = this.parser;
                        ++parser2.cursor;
                        if (lvl.status == LevelStatus.header) {
                            this.CHK_NL(this.parser.cursor);
                            mainLoopGoto = 3;
                            break Label_0480;
                        }
                        if (lvl.spaces > -1) {
                            this.parser.popLevel();
                            this.YYPOS(0);
                            return 268;
                        }
                        this.YYPOS(0);
                        return 0;
                    }
                    case 8: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 6;
                                continue;
                            }
                            default: {
                                gotoPoint = 2;
                                continue;
                            }
                        }
                        break;
                    }
                    default: {
                        continue;
                    }
                }
            }
        }
    Label_0548:
        while (true) {
            while (true) {
                switch (mainLoopGoto) {
                    case 1:
                    case 2: {
                        lvl = this.parser.currentLevel();
                        if (lvl.status == LevelStatus.header) {
                            lvl.status = LevelStatus.doc;
                        }
                        this.parser.token = this.parser.cursor;
                        gotoPoint = -1;
                        yych = 0;
                        while (gotoPoint != -2) {
                            final int currentGoto = gotoPoint;
                            gotoPoint = -2;
                            switch (currentGoto) {
                                case -1: {
                                    if (this.parser.limit - this.parser.cursor < 3) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 0: {
                                            gotoPoint = 30;
                                            continue;
                                        }
                                        case 10: {
                                            gotoPoint = 27;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 29;
                                            continue;
                                        }
                                        case 65: {
                                            gotoPoint = 19;
                                            continue;
                                        }
                                        case 68: {
                                            gotoPoint = 12;
                                            continue;
                                        }
                                        case 69: {
                                            gotoPoint = 16;
                                            continue;
                                        }
                                        case 77: {
                                            gotoPoint = 14;
                                            continue;
                                        }
                                        case 80: {
                                            gotoPoint = 13;
                                            continue;
                                        }
                                        case 81: {
                                            gotoPoint = 15;
                                            continue;
                                        }
                                        case 82: {
                                            gotoPoint = 21;
                                            continue;
                                        }
                                        case 83: {
                                            gotoPoint = 17;
                                            continue;
                                        }
                                        case 84: {
                                            gotoPoint = 23;
                                            continue;
                                        }
                                        case 99: {
                                            gotoPoint = 25;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 11:
                                case 12: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 41;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 44;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 13: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 41;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 43;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 14: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 38;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 40;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 15: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 35;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 37;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 16: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 32;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 34;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 17: {
                                    final Parser parser4 = this.parser;
                                    ++parser4.cursor;
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                                        return 45;
                                    }
                                    mainLoopGoto = 5;
                                    continue Label_0548;
                                }
                                case 19: {
                                    final Parser parser5 = this.parser;
                                    ++parser5.cursor;
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                                        return 45;
                                    }
                                    this.lval = this.getInline();
                                    this.parser.removeAnchor((String)this.lval);
                                    this.CHK_NL(this.parser.cursor);
                                    return 257;
                                }
                                case 21: {
                                    final Parser parser6 = this.parser;
                                    ++parser6.cursor;
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                                        return 45;
                                    }
                                    this.lval = this.getInline();
                                    this.parser.popLevel();
                                    if (this.parser.buffer.buffer[this.parser.cursor - 1] == 10) {
                                        final Parser parser7 = this.parser;
                                        --parser7.cursor;
                                    }
                                    return 258;
                                }
                                case 23: {
                                    final Parser parser8 = this.parser;
                                    ++parser8.cursor;
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                                        return 45;
                                    }
                                    final String qstr = this.getInline();
                                    this.CHK_NL(this.parser.cursor);
                                    if (qstr.charAt(0) != '!') {
                                        this.lval = qstr;
                                        return 260;
                                    }
                                    final int qidx = qstr.length();
                                    if (qidx == 1) {
                                        return 261;
                                    }
                                    lvl = this.parser.currentLevel();
                                    if (qstr.charAt(1) == '^') {
                                        this.lval = lvl.domain + qstr.substring(2);
                                    }
                                    else {
                                        final int carat = qstr.indexOf(94);
                                        if (carat != -1) {
                                            lvl.domain = qstr.substring(1, carat);
                                            this.lval = lvl.domain + qstr.substring(carat + 1);
                                        }
                                        else {
                                            this.lval = qstr.substring(1);
                                        }
                                    }
                                    return 259;
                                }
                                case 25: {
                                    final Parser parser9 = this.parser;
                                    ++parser9.cursor;
                                    mainLoopGoto = 4;
                                    continue Label_0548;
                                }
                                case 27: {
                                    final Parser parser10 = this.parser;
                                    ++parser10.cursor;
                                    this.CHK_NL(this.parser.cursor);
                                    if (lvl.status == LevelStatus.seq) {
                                        return 267;
                                    }
                                    if (lvl.status != LevelStatus.map) {
                                        mainLoopGoto = 2;
                                        continue Label_0548;
                                    }
                                    if (lvl.ncount % 2 == 1) {
                                        return 58;
                                    }
                                    return 267;
                                }
                                case 29: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 27;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 30: {
                                    final Parser parser11 = this.parser;
                                    ++parser11.cursor;
                                    if (lvl.spaces > -1) {
                                        this.parser.popLevel();
                                        this.YYPOS(0);
                                        return 268;
                                    }
                                    this.YYPOS(0);
                                    return 0;
                                }
                                case 32: {
                                    final Parser parser12 = this.parser;
                                    ++parser12.cursor;
                                    if (lvl.status == LevelStatus.seq && lvl.ncount == 0) {
                                        final Level level = lvl;
                                        ++level.ncount;
                                        this.YYPOS(0);
                                        this.FORCE_NEXT_TOKEN(93);
                                        return 91;
                                    }
                                    if (lvl.status == LevelStatus.map && lvl.ncount == 0) {
                                        final Level level2 = lvl;
                                        ++level2.ncount;
                                        this.YYPOS(0);
                                        this.FORCE_NEXT_TOKEN(125);
                                        return 123;
                                    }
                                    this.parser.popLevel();
                                    lvl = this.parser.currentLevel();
                                    if (lvl.status == LevelStatus.seq) {
                                        this.FORCE_NEXT_TOKEN(267);
                                    }
                                    else if (lvl.status == LevelStatus.map) {
                                        if (lvl.ncount % 2 == 1) {
                                            this.FORCE_NEXT_TOKEN(58);
                                        }
                                        else {
                                            this.FORCE_NEXT_TOKEN(267);
                                        }
                                    }
                                    this.CHK_NL(this.parser.cursor);
                                    return 268;
                                }
                                case 34: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 32;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 35: {
                                    final Parser parser13 = this.parser;
                                    ++parser13.cursor;
                                    boolean complex = false;
                                    if (lvl.ncount % 2 == 0 && (lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                                        complex = true;
                                    }
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.seq)) {
                                        return 45;
                                    }
                                    this.CHK_NL(this.parser.cursor);
                                    if (complex) {
                                        this.FORCE_NEXT_TOKEN(266);
                                        return 63;
                                    }
                                    return 266;
                                }
                                case 37: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 35;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 38: {
                                    final Parser parser14 = this.parser;
                                    ++parser14.cursor;
                                    boolean complex = false;
                                    if (lvl.ncount % 2 == 0 && (lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                                        complex = true;
                                    }
                                    if (this.ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.map)) {
                                        return 45;
                                    }
                                    this.CHK_NL(this.parser.cursor);
                                    if (complex) {
                                        this.FORCE_NEXT_TOKEN(266);
                                        return 63;
                                    }
                                    return 266;
                                }
                                case 40: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 38;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 41: {
                                    final Parser parser15 = this.parser;
                                    ++parser15.cursor;
                                    if (lvl.spaces > -1) {
                                        this.parser.popLevel();
                                        this.YYPOS(0);
                                        return 268;
                                    }
                                    this.YYPOS(0);
                                    return 0;
                                }
                                case 43: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 41;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 44: {
                                    final Parser parser16 = this.parser;
                                    ++parser16.cursor;
                                    switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                        case 10: {
                                            gotoPoint = 41;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 11;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    continue;
                                }
                            }
                        }
                    }
                    case 3: {
                        this.parser.token = this.parser.cursor;
                        gotoPoint = -1;
                        yych = 0;
                        while (gotoPoint != -2) {
                            final int currentGoto = gotoPoint;
                            gotoPoint = -2;
                            switch (currentGoto) {
                                case -1: {
                                    if (this.parser.limit - this.parser.cursor < 2) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 0: {
                                            gotoPoint = 47;
                                            continue;
                                        }
                                        case 86: {
                                            gotoPoint = 48;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 50;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 47: {
                                    this.parser.cursor = this.parser.marker;
                                    gotoPoint = 49;
                                    continue;
                                }
                                case 48: {
                                    final byte[] buffer2 = this.parser.buffer.buffer;
                                    final Parser parser17 = this.parser;
                                    final int marker2 = ++this.parser.cursor;
                                    parser17.marker = marker2;
                                    yych = buffer2[marker2];
                                    switch (yych) {
                                        case 46:
                                        case 47:
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                        case 58:
                                        case 59:
                                        case 60:
                                        case 61:
                                        case 62:
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                        case 67:
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                        case 76:
                                        case 77:
                                        case 78:
                                        case 79:
                                        case 80:
                                        case 81:
                                        case 82:
                                        case 83:
                                        case 84:
                                        case 85:
                                        case 86:
                                        case 87:
                                        case 88:
                                        case 89:
                                        case 90:
                                        case 91:
                                        case 92:
                                        case 93:
                                        case 94:
                                        case 95:
                                        case 97:
                                        case 98:
                                        case 99:
                                        case 100:
                                        case 101:
                                        case 102:
                                        case 103:
                                        case 104:
                                        case 105:
                                        case 106:
                                        case 107:
                                        case 108:
                                        case 109:
                                        case 110:
                                        case 111:
                                        case 112:
                                        case 113:
                                        case 114:
                                        case 115:
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                        case 120:
                                        case 121:
                                        case 122: {
                                            gotoPoint = 51;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 49;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 49: {
                                    this.parser.cursor = this.parser.token;
                                    return 265;
                                }
                                case 50: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    gotoPoint = 49;
                                    continue;
                                }
                                case 51: {
                                    final Parser parser18 = this.parser;
                                    ++parser18.cursor;
                                    if (this.parser.limit - this.parser.cursor < 2) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 46:
                                        case 47:
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                        case 59:
                                        case 60:
                                        case 61:
                                        case 62:
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                        case 67:
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                        case 76:
                                        case 77:
                                        case 78:
                                        case 79:
                                        case 80:
                                        case 81:
                                        case 82:
                                        case 83:
                                        case 84:
                                        case 85:
                                        case 86:
                                        case 87:
                                        case 88:
                                        case 89:
                                        case 90:
                                        case 91:
                                        case 92:
                                        case 93:
                                        case 94:
                                        case 95:
                                        case 97:
                                        case 98:
                                        case 99:
                                        case 100:
                                        case 101:
                                        case 102:
                                        case 103:
                                        case 104:
                                        case 105:
                                        case 106:
                                        case 107:
                                        case 108:
                                        case 109:
                                        case 110:
                                        case 111:
                                        case 112:
                                        case 113:
                                        case 114:
                                        case 115:
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                        case 120:
                                        case 121:
                                        case 122: {
                                            gotoPoint = 51;
                                            continue;
                                        }
                                        case 58: {
                                            gotoPoint = 53;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 47;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 53: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 46:
                                        case 47:
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                        case 58:
                                        case 59:
                                        case 60:
                                        case 61:
                                        case 62:
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                        case 67:
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                        case 76:
                                        case 77:
                                        case 78:
                                        case 79:
                                        case 80:
                                        case 81:
                                        case 82:
                                        case 83:
                                        case 84:
                                        case 85:
                                        case 86:
                                        case 87:
                                        case 88:
                                        case 89:
                                        case 90:
                                        case 91:
                                        case 92:
                                        case 93:
                                        case 94:
                                        case 95:
                                        case 97:
                                        case 98:
                                        case 99:
                                        case 100:
                                        case 101:
                                        case 102:
                                        case 103:
                                        case 104:
                                        case 105:
                                        case 106:
                                        case 107:
                                        case 108:
                                        case 109:
                                        case 110:
                                        case 111:
                                        case 112:
                                        case 113:
                                        case 114:
                                        case 115:
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                        case 120:
                                        case 121:
                                        case 122: {
                                            gotoPoint = 54;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 47;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 54: {
                                    final Parser parser19 = this.parser;
                                    ++parser19.cursor;
                                    if (this.parser.limit - this.parser.cursor < 2) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 10: {
                                            gotoPoint = 56;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 58;
                                            continue;
                                        }
                                        case 46:
                                        case 47:
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                        case 58:
                                        case 59:
                                        case 60:
                                        case 61:
                                        case 62:
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                        case 67:
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 75:
                                        case 76:
                                        case 77:
                                        case 78:
                                        case 79:
                                        case 80:
                                        case 81:
                                        case 82:
                                        case 83:
                                        case 84:
                                        case 85:
                                        case 86:
                                        case 87:
                                        case 88:
                                        case 89:
                                        case 90:
                                        case 91:
                                        case 92:
                                        case 93:
                                        case 94:
                                        case 95:
                                        case 97:
                                        case 98:
                                        case 99:
                                        case 100:
                                        case 101:
                                        case 102:
                                        case 103:
                                        case 104:
                                        case 105:
                                        case 106:
                                        case 107:
                                        case 108:
                                        case 109:
                                        case 110:
                                        case 111:
                                        case 112:
                                        case 113:
                                        case 114:
                                        case 115:
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                        case 120:
                                        case 121:
                                        case 122: {
                                            gotoPoint = 54;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 47;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 56: {
                                    final Parser parser20 = this.parser;
                                    ++parser20.cursor;
                                    this.CHK_NL(this.parser.cursor);
                                    mainLoopGoto = 3;
                                    continue Label_0548;
                                }
                                case 58: {
                                    final Parser parser21 = this.parser;
                                    ++parser21.cursor;
                                    switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                        case 10: {
                                            gotoPoint = 56;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 47;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    continue;
                                }
                            }
                        }
                    }
                    case 4: {
                        this.parser.token = this.parser.cursor;
                        gotoPoint = -1;
                        yych = 0;
                        while (gotoPoint != -2) {
                            final int currentGoto = gotoPoint;
                            gotoPoint = -2;
                            switch (currentGoto) {
                                case -1: {
                                    if (this.parser.limit - this.parser.cursor < 2) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 0: {
                                            gotoPoint = 61;
                                            continue;
                                        }
                                        case 10: {
                                            gotoPoint = 62;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 64;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 66;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 61:
                                case 62: {
                                    final Parser parser22 = this.parser;
                                    ++parser22.cursor;
                                }
                                case 63: {
                                    this.CHK_NL(this.parser.cursor);
                                    mainLoopGoto = 2;
                                    continue Label_0548;
                                }
                                case 64: {
                                    final Parser parser23 = this.parser;
                                    ++parser23.cursor;
                                    switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                        case 10: {
                                            gotoPoint = 67;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 65;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 65: {
                                    mainLoopGoto = 4;
                                    continue Label_0548;
                                }
                                case 66: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    gotoPoint = 65;
                                    continue;
                                }
                                case 67: {
                                    final Parser parser24 = this.parser;
                                    ++parser24.cursor;
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    gotoPoint = 63;
                                    continue;
                                }
                                default: {
                                    continue;
                                }
                            }
                        }
                    }
                    case 5: {
                        q = new QuotedString();
                        q.str[0] = 0;
                    }
                    case 6: {
                        tok = this.parser.cursor;
                        gotoPoint = -1;
                        yych = 0;
                        while (gotoPoint != -2) {
                            final int currentGoto = gotoPoint;
                            gotoPoint = -2;
                            switch (currentGoto) {
                                case -1: {
                                    if (this.parser.limit - this.parser.cursor < 3) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 0: {
                                            gotoPoint = 74;
                                            continue;
                                        }
                                        case 10: {
                                            gotoPoint = 70;
                                            continue;
                                        }
                                        case 13: {
                                            gotoPoint = 72;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 76;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 70: {
                                    final Parser parser25 = this.parser;
                                    ++parser25.cursor;
                                    switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                        case 67: {
                                            gotoPoint = 78;
                                            continue;
                                        }
                                        case 78: {
                                            gotoPoint = 80;
                                            continue;
                                        }
                                        case 90: {
                                            gotoPoint = 83;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 71;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 71: {
                                    this.parser.cursor = tok;
                                    mainLoopGoto = 7;
                                    continue Label_0548;
                                }
                                case 72: {
                                    final Parser parser26 = this.parser;
                                    ++parser26.cursor;
                                    switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                        case 10: {
                                            gotoPoint = 77;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 73;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 73: {
                                    q.cat(this.parser.buffer.buffer[tok]);
                                    mainLoopGoto = 6;
                                    continue Label_0548;
                                }
                                case 74: {
                                    final Parser parser27 = this.parser;
                                    ++parser27.cursor;
                                    this.parser.cursor = tok;
                                    mainLoopGoto = 7;
                                    continue Label_0548;
                                }
                                case 76: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    gotoPoint = 73;
                                    continue;
                                }
                                case 77: {
                                    yych = this.parser.buffer.buffer[++this.parser.cursor];
                                    switch (yych) {
                                        case 67: {
                                            gotoPoint = 78;
                                            continue;
                                        }
                                        case 78: {
                                            gotoPoint = 80;
                                            continue;
                                        }
                                        case 90: {
                                            gotoPoint = 83;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 71;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 78: {
                                    final Parser parser28 = this.parser;
                                    ++parser28.cursor;
                                    this.CHK_NL(tok + 1);
                                    mainLoopGoto = 6;
                                    continue Label_0548;
                                }
                                case 80: {
                                    final Parser parser29 = this.parser;
                                    ++parser29.cursor;
                                    if (this.parser.limit <= this.parser.cursor) {
                                        this.parser.read();
                                    }
                                    yych = this.parser.buffer.buffer[this.parser.cursor];
                                    switch (yych) {
                                        case 48:
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57: {
                                            gotoPoint = 80;
                                            continue;
                                        }
                                        default: {
                                            gotoPoint = 82;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                case 82: {
                                    this.CHK_NL(tok + 1);
                                    if (tok + 2 < this.parser.cursor) {
                                        final int count = tok + 2;
                                        for (int total = Integer.valueOf(new String(this.parser.buffer.buffer, tok + 2, this.parser.cursor - (tok + 2)), 10), i = 0; i < total; ++i) {
                                            q.cat('\n');
                                        }
                                    }
                                    else {
                                        q.cat('\n');
                                    }
                                    mainLoopGoto = 6;
                                    continue Label_0548;
                                }
                                case 83: {
                                    final Parser parser30 = this.parser;
                                    ++parser30.cursor;
                                    this.CHK_NL(tok + 1);
                                    q.cat((byte)0);
                                    mainLoopGoto = 6;
                                    continue Label_0548;
                                }
                                default: {
                                    continue;
                                }
                            }
                        }
                    }
                    case 7: {
                        break Label_0548;
                    }
                    default: {
                        continue;
                    }
                }
            }
            break;
        }
        final Node n = Node.allocStr();
        final Data.Str dd = (Data.Str)n.data;
        dd.ptr = Pointer.create(q.str, 0);
        dd.len = q.idx;
        this.lval = n;
        this.parser.popLevel();
        if (this.parser.implicit_typing) {
            ImplicitScanner2.tryTagImplicit(n, this.parser.taguri_expansion);
        }
        return 263;
    }
    
    private String getInline() throws IOException {
        String str = "";
        int tok = -1;
        Label_0339: {
        Label_0229:
            while (true) {
                tok = this.parser.cursor;
                int gotoPoint = -1;
                byte yych = 0;
                while (gotoPoint != -2) {
                    final int currentGoto = gotoPoint;
                    gotoPoint = -2;
                    switch (currentGoto) {
                        case -1: {
                            if (this.parser.limit - this.parser.cursor < 2) {
                                this.parser.read();
                            }
                            yych = this.parser.buffer.buffer[this.parser.cursor];
                            switch (yych) {
                                case 0: {
                                    gotoPoint = 91;
                                    continue;
                                }
                                case 10: {
                                    gotoPoint = 87;
                                    continue;
                                }
                                case 13: {
                                    gotoPoint = 89;
                                    continue;
                                }
                                default: {
                                    gotoPoint = 93;
                                    continue;
                                }
                            }
                            break;
                        }
                        case 87: {
                            final Parser parser = this.parser;
                            ++parser.cursor;
                        }
                        case 88: {
                            break Label_0229;
                        }
                        case 89: {
                            final Parser parser2 = this.parser;
                            ++parser2.cursor;
                            switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                                case 10: {
                                    gotoPoint = 94;
                                    continue;
                                }
                                default: {
                                    gotoPoint = 90;
                                    continue;
                                }
                            }
                            break;
                        }
                        case 90: {
                            str += (char)this.parser.buffer.buffer[tok];
                        }
                        case 91: {
                            break Label_0339;
                        }
                        case 93: {
                            yych = this.parser.buffer.buffer[++this.parser.cursor];
                            gotoPoint = 90;
                            continue;
                        }
                        case 94: {
                            final Parser parser3 = this.parser;
                            ++parser3.cursor;
                            yych = this.parser.buffer.buffer[this.parser.cursor];
                            gotoPoint = 88;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            this.CHK_NL(this.parser.cursor);
            return str;
        }
        final Parser parser4 = this.parser;
        ++parser4.cursor;
        this.parser.cursor = tok;
        return str;
    }
    
    private static class QuotedString
    {
        public int idx;
        public int capa;
        public byte[] str;
        
        public QuotedString() {
            this.idx = 0;
            this.capa = 100;
            this.str = new byte[100];
        }
        
        public void cat(final char l) {
            this.cat((byte)l);
        }
        
        public void cat(final byte l) {
            if (this.idx + 1 >= this.capa) {
                this.capa += 128;
                this.str = YAML.realloc(this.str, this.capa);
            }
            this.str[this.idx++] = l;
            this.str[this.idx] = 0;
        }
    }
}
