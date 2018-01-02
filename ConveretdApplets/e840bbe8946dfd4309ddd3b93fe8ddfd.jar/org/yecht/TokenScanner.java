// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.io.IOException;

public class TokenScanner implements DefaultYAMLParser.yyInput
{
    public static final int QUOTELEN = 1024;
    private Parser parser;
    private Object lval;
    private int currentToken;
    public static final String[] tnames;
    private static final boolean[] YWORDC;
    private static final boolean[] DIGIT_OR_SIGN;
    
    public static void error(final String msg, final Parser parser) {
        if (parser.error_handler == null) {
            parser.error_handler = new ErrorHandler.Default();
        }
        parser.root = parser.root_on_error;
        parser.error_handler.handle(parser, msg);
    }
    
    public static DefaultYAMLParser.yyInput createScanner(final Parser parser) {
        switch (parser.input_type) {
            case YAML_UTF8: {
                return new TokenScanner(parser);
            }
            case Bytecode_UTF8: {
                return new BytecodeScanner(parser);
            }
            case YAML_UTF16: {
                error("UTF-16 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
                return null;
            }
            case YAML_UTF32: {
                error("UTF-32 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
                return null;
            }
            default: {
                return null;
            }
        }
    }
    
    public TokenScanner(final Parser parser) {
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
    
    private int isNewline(final int ptr) {
        return this.newlineLen(ptr);
    }
    
    private int newlineLen(final int ptr) {
        if (this.parser.buffer.buffer[ptr] == 10) {
            return 1;
        }
        if (this.parser.buffer.buffer[ptr] == 13 && this.parser.buffer.buffer[ptr + 1] == 10) {
            return 2;
        }
        return 0;
    }
    
    private int isNewline(final byte[] buff, final int ptr) {
        return this.newlineLen(buff, ptr);
    }
    
    private int newlineLen(final byte[] buff, final int ptr) {
        if (buff[ptr] == 10) {
            return 1;
        }
        if (buff[ptr] == 13 && buff[ptr + 1] == 10) {
            return 2;
        }
        return 0;
    }
    
    private void NEWLINE(final int ptr) {
        this.parser.lineptr = ptr + this.newlineLen(ptr);
        if (this.parser.lineptr > this.parser.linectptr) {
            final Parser parser = this.parser;
            ++parser.linect;
            this.parser.linectptr = this.parser.lineptr;
        }
    }
    
    private void RETURN_YAML_BLOCK(final QuotedString q, final int blockType, final int nlDoWhat) {
        final Node n = Node.allocStr();
        if (this.parser.taguri_expansion) {
            n.type_id = Parser.taguri("yaml.org,2002", "str");
        }
        else {
            n.type_id = "str";
        }
        final Data.Str dd = (Data.Str)n.data;
        dd.ptr = Pointer.create(q.str, 0);
        dd.len = q.idx;
        if (blockType == 20) {
            dd.style = ScalarStyle.Literal;
        }
        else {
            dd.style = ScalarStyle.Fold;
        }
        if (q.idx > 0 && nlDoWhat != 50) {
            int fc;
            for (fc = dd.len - 1; this.isNewline(dd.ptr.buffer, fc) > 0; --fc) {}
            if (nlDoWhat != 40 && fc < dd.len - 1) {
                ++fc;
            }
            dd.len = fc + 1;
        }
        this.lval = n;
    }
    
    private int GET_TRUE_YAML_INDENT() {
        final Level lvl_deep = this.parser.currentLevel();
        int indt_len = lvl_deep.spaces;
        if (lvl_deep.status == LevelStatus.seq || (indt_len == this.parser.cursor - this.parser.lineptr && lvl_deep.status != LevelStatus.map)) {
            final Parser parser = this.parser;
            --parser.lvl_idx;
            final Level lvl_over = this.parser.currentLevel();
            indt_len = lvl_over.spaces;
            final Parser parser2 = this.parser;
            ++parser2.lvl_idx;
        }
        return indt_len;
    }
    
    private void YYPOS(final int n) {
        this.parser.cursor = this.parser.token + n;
    }
    
    public void RETURN_IMPLICIT(final QuotedString q) {
        final Node n = Node.allocStr();
        this.parser.cursor = this.parser.token;
        final Data.Str dd = (Data.Str)n.data;
        dd.ptr = Pointer.create(q.str, 0);
        dd.len = q.idx;
        dd.style = ScalarStyle.Plain;
        this.lval = n;
        if (this.parser.implicit_typing) {
            ImplicitScanner2.tryTagImplicit(n, this.parser.taguri_expansion);
        }
    }
    
    private int real_yylex() throws IOException {
        if (this.parser.cursor == -1) {
            this.parser.read();
        }
        if (this.parser.force_token != 0) {
            final int t = this.parser.force_token;
            this.parser.force_token = 0;
            return t;
        }
        if (this.parser.lineptr != this.parser.cursor) {
            return this.document(0);
        }
        return this.header();
    }
    
    private int header() throws IOException {
        int doc_level = 0;
    Label_1745:
        while (true) {
            this.parser.token = this.parser.cursor;
            int gotoPoint = -1;
            byte yych = 0;
            int yyaccept = 0;
            while (gotoPoint != -2) {
                final int currentGoto = gotoPoint;
                gotoPoint = -2;
                switch (currentGoto) {
                    case -1: {
                        if (this.parser.limit - this.parser.cursor < 5) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 0: {
                                gotoPoint = 7;
                                continue;
                            }
                            case 9:
                            case 32: {
                                gotoPoint = 12;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 9;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 11;
                                continue;
                            }
                            case 35: {
                                gotoPoint = 5;
                                continue;
                            }
                            case 45: {
                                gotoPoint = 2;
                                continue;
                            }
                            case 46: {
                                gotoPoint = 4;
                                continue;
                            }
                            default: {
                                gotoPoint = 14;
                                continue;
                            }
                        }
                        break;
                    }
                    case 2: {
                        yyaccept = 0;
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
                        switch (yych) {
                            case 45: {
                                gotoPoint = 28;
                                continue;
                            }
                            default: {
                                gotoPoint = 3;
                                continue;
                            }
                        }
                        break;
                    }
                    case 3: {
                        this.YYPOS(0);
                        return this.document(doc_level);
                    }
                    case 4: {
                        yyaccept = 0;
                        final byte[] buffer2 = this.parser.buffer.buffer;
                        final Parser parser2 = this.parser;
                        final int marker2 = ++this.parser.cursor;
                        parser2.marker = marker2;
                        yych = buffer2[marker2];
                        switch (yych) {
                            case 46: {
                                gotoPoint = 21;
                                continue;
                            }
                            default: {
                                gotoPoint = 3;
                                continue;
                            }
                        }
                        break;
                    }
                    case 5: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        this.eatComments();
                        continue;
                    }
                    case 7: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        final Level lvl = this.parser.currentLevel();
                        if (lvl.spaces > -1) {
                            this.parser.popLevel();
                            this.YYPOS(0);
                            return 268;
                        }
                        this.YYPOS(0);
                        return 0;
                    }
                    case 9: {
                        yyaccept = 1;
                        final byte[] buffer3 = this.parser.buffer.buffer;
                        final Parser parser5 = this.parser;
                        final int marker3 = ++this.parser.cursor;
                        parser5.marker = marker3;
                        yych = buffer3[marker3];
                        gotoPoint = 18;
                        continue;
                    }
                    case 10: {
                        int indent = this.parser.token;
                        this.NEWLINE(indent);
                        while (indent < this.parser.cursor) {
                            if (this.isNewline(++indent) != 0) {
                                this.NEWLINE(indent);
                            }
                        }
                        doc_level = 0;
                        if (this.parser.buffer.buffer[this.parser.cursor] == 0) {
                            doc_level = -1;
                            this.parser.token = this.parser.cursor - 1;
                            continue;
                        }
                        if (this.parser.buffer.buffer[this.parser.lineptr] == 32) {
                            doc_level = this.parser.cursor - this.parser.lineptr;
                            continue;
                        }
                        continue;
                    }
                    case 11: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 17;
                                continue;
                            }
                            default: {
                                gotoPoint = 3;
                                continue;
                            }
                        }
                        break;
                    }
                    case 12: {
                        final Parser parser6 = this.parser;
                        ++parser6.cursor;
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        gotoPoint = 16;
                        continue;
                    }
                    case 13: {
                        doc_level = this.parser.cursor - this.parser.lineptr;
                        continue;
                    }
                    case 14: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 3;
                        continue;
                    }
                    case 15: {
                        final Parser parser7 = this.parser;
                        ++parser7.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 16: {
                        switch (yych) {
                            case 9:
                            case 32: {
                                gotoPoint = 15;
                                continue;
                            }
                            default: {
                                gotoPoint = 13;
                                continue;
                            }
                        }
                        break;
                    }
                    case 17: {
                        yyaccept = 1;
                        this.parser.marker = ++this.parser.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 18: {
                        switch (yych) {
                            case 10:
                            case 32: {
                                gotoPoint = 17;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 19;
                                continue;
                            }
                            default: {
                                gotoPoint = 10;
                                continue;
                            }
                        }
                        break;
                    }
                    case 19: {
                        final Parser parser8 = this.parser;
                        ++parser8.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 17;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 20: {
                        this.parser.cursor = this.parser.marker;
                        if (yyaccept == 0) {
                            gotoPoint = 3;
                            continue;
                        }
                        gotoPoint = 10;
                        continue;
                    }
                    case 21: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 46: {
                                gotoPoint = 22;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 22: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 23;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 27;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 25;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 23: {
                        final Parser parser9 = this.parser;
                        ++parser9.cursor;
                    }
                    case 24: {
                        final Level lvl = this.parser.currentLevel();
                        if (lvl.status == LevelStatus.header) {
                            continue;
                        }
                        if (lvl.spaces > -1) {
                            this.parser.popLevel();
                            this.YYPOS(0);
                            return 268;
                        }
                        this.YYPOS(0);
                        return 0;
                    }
                    case 25: {
                        final Parser parser10 = this.parser;
                        ++parser10.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 32: {
                                gotoPoint = 25;
                                continue;
                            }
                            default: {
                                gotoPoint = 24;
                                continue;
                            }
                        }
                        break;
                    }
                    case 27: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 23;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 28: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 45: {
                                gotoPoint = 29;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 29: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 30;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 34;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 32;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                    case 30: {
                        final Parser parser11 = this.parser;
                        ++parser11.cursor;
                    }
                    case 31: {
                        break Label_1745;
                    }
                    case 32: {
                        final Parser parser12 = this.parser;
                        ++parser12.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 32: {
                                gotoPoint = 32;
                                continue;
                            }
                            default: {
                                gotoPoint = 31;
                                continue;
                            }
                        }
                        break;
                    }
                    case 34: {
                        final Parser parser13 = this.parser;
                        ++parser13.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 30;
                                continue;
                            }
                            default: {
                                gotoPoint = 20;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
        }
        final Level lvl = this.parser.currentLevel();
        if (lvl.status == LevelStatus.header) {
            this.YYPOS(3);
            return this.directive();
        }
        if (lvl.spaces > -1) {
            this.parser.popLevel();
            this.YYPOS(0);
            return 268;
        }
        this.YYPOS(0);
        return 0;
    }
    
    private void spcOrLfStar() throws IOException {
        while (true) {
            if (this.parser.cursor + 3 >= this.parser.limit) {
                this.parser.read();
            }
            final byte ych = this.parser.buffer.buffer[this.parser.cursor];
            switch (ych) {
                case 10:
                case 32: {
                    final Parser parser = this.parser;
                    ++parser.cursor;
                    continue;
                }
                case 13: {
                    if (this.parser.buffer.buffer[this.parser.cursor + 1] == 10) {
                        final Parser parser2 = this.parser;
                        parser2.cursor += 2;
                        continue;
                    }
                }
                default: {}
            }
        }
    }
    
    private boolean ywordc() throws IOException {
        if (!TokenScanner.YWORDC[this.parser.buffer.buffer[this.parser.cursor] & 0xFF]) {
            return false;
        }
        final Parser parser = this.parser;
        ++parser.cursor;
        while (true) {
            if (this.parser.cursor == this.parser.limit) {
                this.parser.read();
            }
            if (!TokenScanner.YWORDC[this.parser.buffer.buffer[this.parser.cursor] & 0xFF]) {
                break;
            }
            final Parser parser2 = this.parser;
            ++parser2.cursor;
        }
        return true;
    }
    
    private boolean endspc() throws IOException {
        final byte ych = this.parser.buffer.buffer[this.parser.cursor];
        switch (ych) {
            case 32: {
                final Parser parser = this.parser;
                ++parser.cursor;
                while (true) {
                    if (this.parser.cursor == this.parser.limit) {
                        this.parser.read();
                    }
                    if (this.parser.buffer.buffer[this.parser.cursor] != 32) {
                        break;
                    }
                    final Parser parser2 = this.parser;
                    ++parser2.cursor;
                }
                return true;
            }
            case 13: {
                if (this.parser.buffer.buffer[this.parser.cursor + 1] != 10) {
                    return false;
                }
                final Parser parser3 = this.parser;
                ++parser3.cursor;
            }
            case 10: {
                final Parser parser4 = this.parser;
                ++parser4.cursor;
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean yblock() throws IOException {
        int plus;
        int ych;
        for (plus = 0, ych = (this.parser.buffer.buffer[this.parser.cursor + plus] & 0xFF); TokenScanner.DIGIT_OR_SIGN[ych]; ych = (this.parser.buffer.buffer[this.parser.cursor + plus] & 0xFF)) {
            ++plus;
            if (this.parser.limit - this.parser.cursor < plus) {
                this.parser.read();
            }
        }
        switch (ych) {
            case 32: {
                ++plus;
                while (true) {
                    if (this.parser.limit - this.parser.cursor < plus) {
                        this.parser.read();
                    }
                    if (this.parser.buffer.buffer[this.parser.cursor + plus] != 32) {
                        break;
                    }
                    ++plus;
                }
                final Parser parser = this.parser;
                parser.cursor += plus;
                return true;
            }
            case 13: {
                if (this.parser.buffer.buffer[this.parser.cursor + plus + 1] != 10) {
                    return false;
                }
                ++plus;
            }
            case 10: {
                ++plus;
                final Parser parser2 = this.parser;
                parser2.cursor += plus;
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private int document(int doc_level) throws IOException {
        boolean do_any = false;
        while (true) {
            Level lvl = this.parser.currentLevel();
            if (lvl.status == LevelStatus.header) {
                lvl.status = LevelStatus.doc;
            }
            this.parser.token = this.parser.cursor;
            if (this.parser.limit - this.parser.cursor < 3) {
                this.parser.read();
            }
            byte yych = this.parser.buffer.buffer[this.parser.cursor];
            switch (yych) {
                case 0: {
                    final Parser parser = this.parser;
                    ++parser.cursor;
                    if (lvl.spaces > -1) {
                        this.parser.popLevel();
                        this.YYPOS(0);
                        return 268;
                    }
                    this.YYPOS(0);
                    return 0;
                }
                case 9:
                case 32: {
                    final Parser parser2 = this.parser;
                    ++parser2.cursor;
                    while ((yych = this.parser.buffer.buffer[this.parser.cursor]) == 9 || yych == 32) {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        if (this.parser.cursor == this.parser.limit) {
                            this.parser.read();
                        }
                    }
                    break;
                }
                case 13: {
                    if (this.parser.buffer.buffer[this.parser.cursor + 1] != 10) {
                        do_any = true;
                        break;
                    }
                    final Parser parser4 = this.parser;
                    ++parser4.cursor;
                }
                case 10: {
                    final Parser parser5 = this.parser;
                    ++parser5.cursor;
                    this.spcOrLfStar();
                    int indent = this.parser.token;
                    this.NEWLINE(indent);
                    while (indent < this.parser.cursor) {
                        if (this.isNewline(++indent) != 0) {
                            this.NEWLINE(indent);
                        }
                    }
                    int indt_len = 0;
                    if (this.parser.buffer.buffer[this.parser.cursor] == 0) {
                        indt_len = -1;
                        this.parser.token = this.parser.cursor - 1;
                    }
                    else if (this.parser.buffer.buffer[this.parser.lineptr] == 32) {
                        indt_len = this.parser.cursor - this.parser.lineptr;
                    }
                    lvl = this.parser.currentLevel();
                    doc_level = 0;
                    if (this.parser.buffer.buffer[this.parser.cursor] == 35 || lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                        break;
                    }
                    if (lvl.spaces > indt_len) {
                        this.parser.popLevel();
                        this.YYPOS(0);
                        return 268;
                    }
                    if (lvl.spaces < indt_len) {
                        if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                            this.parser.addLevel(indt_len, LevelStatus.doc);
                            return 266;
                        }
                        break;
                    }
                    else {
                        if (indt_len == -1) {
                            return 0;
                        }
                        return 267;
                    }
                    break;
                }
                case 33: {
                    final Parser parser6 = this.parser;
                    ++parser6.cursor;
                    return this.transferMethod();
                }
                case 34: {
                    final Parser parser7 = this.parser;
                    ++parser7.cursor;
                    if (lvl.spaces >= doc_level) {
                        return this.doubleQuote();
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(doc_level, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                case 35: {
                    final Parser parser8 = this.parser;
                    ++parser8.cursor;
                    this.eatComments();
                    break;
                }
                case 38: {
                    final Parser parser9 = this.parser;
                    ++parser9.cursor;
                    if (this.ywordc()) {
                        this.lval = new String(this.parser.buffer.buffer, this.parser.token + 1, this.parser.cursor - (this.parser.token + 1), "ISO-8859-1");
                        this.parser.removeAnchor((String)this.lval);
                        return 257;
                    }
                    final Parser parser10 = this.parser;
                    --parser10.cursor;
                    do_any = true;
                    break;
                }
                case 39: {
                    final Parser parser11 = this.parser;
                    ++parser11.cursor;
                    if (lvl.spaces >= doc_level) {
                        return this.singleQuote();
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(doc_level, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                case 42: {
                    final Parser parser12 = this.parser;
                    ++parser12.cursor;
                    if (!this.ywordc()) {
                        final Parser parser13 = this.parser;
                        --parser13.cursor;
                        do_any = true;
                        break;
                    }
                    if (lvl.spaces >= doc_level) {
                        this.lval = new String(this.parser.buffer.buffer, this.parser.token + 1, this.parser.cursor - (this.parser.token + 1), "ISO-8859-1");
                        return 258;
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(doc_level, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                case 44:
                case 58: {
                    final Parser parser14 = this.parser;
                    ++parser14.cursor;
                    if (this.endspc()) {
                        if (this.parser.buffer.buffer[this.parser.token] == 58 && lvl.status != LevelStatus.imap && lvl.status != LevelStatus.iseq) {
                            lvl.status = LevelStatus.map;
                        }
                        this.YYPOS(1);
                        return this.parser.buffer.buffer[this.parser.token];
                    }
                    final Parser parser15 = this.parser;
                    --parser15.cursor;
                    do_any = true;
                    break;
                }
                case 45:
                case 63: {
                    final Parser parser16 = this.parser;
                    ++parser16.cursor;
                    if (!this.endspc()) {
                        final Parser parser17 = this.parser;
                        --parser17.cursor;
                        do_any = true;
                        break;
                    }
                    if (lvl.spaces >= this.parser.token - this.parser.lineptr) {
                        this.parser.force_token = 266;
                        if (this.parser.buffer.buffer[this.parser.cursor] == 35 || this.isNewline(this.parser.cursor) != 0 || this.isNewline(this.parser.cursor - 1) != 0) {
                            final Parser parser18 = this.parser;
                            --parser18.cursor;
                            this.parser.addLevel(this.parser.token + 1 - this.parser.lineptr, LevelStatus.seq);
                        }
                        else {
                            this.parser.addLevel(this.parser.cursor - this.parser.lineptr, LevelStatus.seq);
                        }
                        return this.parser.buffer.buffer[this.parser.token];
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(this.parser.token - this.parser.lineptr, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                case 62:
                case 124: {
                    final Parser parser19 = this.parser;
                    ++parser19.cursor;
                    if (this.yblock()) {
                        if (this.isNewline(this.parser.cursor - 1) != 0) {
                            final Parser parser20 = this.parser;
                            --parser20.cursor;
                        }
                        return this.scalarBlock();
                    }
                    final Parser parser21 = this.parser;
                    --parser21.cursor;
                    do_any = true;
                    break;
                }
                case 91: {
                    final Parser parser22 = this.parser;
                    ++parser22.cursor;
                    if (lvl.spaces >= doc_level) {
                        lvl = this.parser.currentLevel();
                        this.parser.addLevel(lvl.spaces + 1, LevelStatus.iseq);
                        return this.parser.buffer.buffer[this.parser.token];
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(doc_level, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                case 93:
                case 125: {
                    final Parser parser23 = this.parser;
                    ++parser23.cursor;
                    this.parser.popLevel();
                    return this.parser.buffer.buffer[this.parser.token];
                }
                case 123: {
                    final Parser parser24 = this.parser;
                    ++parser24.cursor;
                    if (lvl.spaces >= doc_level) {
                        lvl = this.parser.currentLevel();
                        this.parser.addLevel(lvl.spaces + 1, LevelStatus.imap);
                        return this.parser.buffer.buffer[this.parser.token];
                    }
                    if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                        this.parser.addLevel(doc_level, LevelStatus.doc);
                        this.YYPOS(0);
                        return 266;
                    }
                    break;
                }
                default: {
                    do_any = true;
                    break;
                }
            }
            if (do_any) {
                if (lvl.spaces >= doc_level) {
                    return this.plain();
                }
                if (lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                    this.parser.addLevel(doc_level, LevelStatus.doc);
                    this.YYPOS(0);
                    return 266;
                }
                do_any = false;
            }
        }
    }
    
    private int directive() throws IOException {
    Label_0752:
        while (true) {
            this.parser.toktmp = this.parser.cursor;
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
                                gotoPoint = 37;
                                continue;
                            }
                            case 9:
                            case 32: {
                                gotoPoint = 40;
                                continue;
                            }
                            case 37: {
                                gotoPoint = 38;
                                continue;
                            }
                            default: {
                                gotoPoint = 42;
                                continue;
                            }
                        }
                        break;
                    }
                    case 37: {
                        this.parser.cursor = this.parser.marker;
                        gotoPoint = 39;
                        continue;
                    }
                    case 38: {
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
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
                                gotoPoint = 45;
                                continue;
                            }
                            default: {
                                gotoPoint = 39;
                                continue;
                            }
                        }
                        break;
                    }
                    case 39: {
                        break Label_0752;
                    }
                    case 40: {
                        final Parser parser2 = this.parser;
                        ++parser2.cursor;
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        gotoPoint = 44;
                        continue;
                    }
                    case 41: {
                        continue;
                    }
                    case 42: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 39;
                        continue;
                    }
                    case 43: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 44: {
                        switch (yych) {
                            case 9:
                            case 32: {
                                gotoPoint = 43;
                                continue;
                            }
                            default: {
                                gotoPoint = 41;
                                continue;
                            }
                        }
                        break;
                    }
                    case 45: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
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
                                gotoPoint = 45;
                                continue;
                            }
                            case 58: {
                                gotoPoint = 47;
                                continue;
                            }
                            default: {
                                gotoPoint = 37;
                                continue;
                            }
                        }
                        break;
                    }
                    case 47: {
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
                                gotoPoint = 48;
                                continue;
                            }
                            default: {
                                gotoPoint = 37;
                                continue;
                            }
                        }
                        break;
                    }
                    case 48: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
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
                }
            }
        }
        this.parser.cursor = this.parser.toktmp;
        return 265;
    }
    
    private int getAndCheckIndentLength() {
        int indent = this.parser.token;
        this.NEWLINE(indent);
        while (indent < this.parser.cursor) {
            if (this.isNewline(++indent) != 0) {
                this.NEWLINE(indent);
            }
        }
        int indt_len = 0;
        if (this.parser.buffer.buffer[this.parser.cursor] == 0) {
            indt_len = -1;
        }
        else if (this.parser.buffer.buffer[this.parser.lineptr] == 32) {
            indt_len = this.parser.cursor - this.parser.lineptr;
        }
        return indt_len;
    }
    
    private void countAndAddNewlines(final QuotedString q) {
        int nl_count = 0;
        while (this.parser.token < this.parser.cursor) {
            final int nl_len = this.newlineLen(this.parser.token++);
            if (nl_len > 0) {
                ++nl_count;
                final Parser parser = this.parser;
                parser.token += nl_len - 1;
            }
        }
        if (nl_count <= 1) {
            q.cat(' ');
        }
        else {
            for (int i = 0; i < nl_count - 1; ++i) {
                q.cat('\n');
            }
        }
    }
    
    private int plain() throws IOException {
        final QuotedString q = new QuotedString();
        this.parser.cursor = this.parser.token;
        final Level plvl = this.parser.currentLevel();
        final Level lvl_deep = this.parser.currentLevel();
        int parentIndent = lvl_deep.spaces;
        if (lvl_deep.status == LevelStatus.seq || (parentIndent == this.parser.cursor - this.parser.lineptr && lvl_deep.status != LevelStatus.map)) {
            final Parser parser = this.parser;
            --parser.lvl_idx;
            final Level lvl_over = this.parser.currentLevel();
            parentIndent = lvl_over.spaces;
            final Parser parser2 = this.parser;
            ++parser2.lvl_idx;
        }
        boolean plain3 = false;
        boolean do_any = false;
        while (true) {
            this.parser.token = this.parser.cursor;
            do {
                plain3 = false;
                if (this.parser.limit - this.parser.cursor < 3) {
                    this.parser.read();
                }
                final byte yych = this.parser.buffer.buffer[this.parser.cursor];
                switch (yych) {
                    case 13: {
                        if (this.parser.buffer.buffer[this.parser.cursor + 1] != 10) {
                            do_any = true;
                            break;
                        }
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                    }
                    case 10: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        this.spcOrLfStar();
                        final int indt_len = this.getAndCheckIndentLength();
                        if (indt_len <= parentIndent) {
                            this.RETURN_IMPLICIT(q);
                            return 263;
                        }
                        this.countAndAddNewlines(q);
                        break;
                    }
                    case 0: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        this.RETURN_IMPLICIT(q);
                        return 263;
                    }
                    case 32: {
                        if (this.parser.buffer.buffer[this.parser.cursor + 1] == 35) {
                            final Parser parser6 = this.parser;
                            parser6.cursor += 2;
                            this.eatComments();
                            this.RETURN_IMPLICIT(q);
                            return 263;
                        }
                    }
                    case 9: {
                        final Parser parser7 = this.parser;
                        ++parser7.cursor;
                        if (q.idx != 0) {
                            plain3 = true;
                            break;
                        }
                        break;
                    }
                    case 125: {
                        final Parser parser8 = this.parser;
                        ++parser8.cursor;
                        if (plvl.status != LevelStatus.imap) {
                            if (this.parser.buffer.buffer[this.parser.cursor - 1] == 32 || this.isNewline(this.parser.cursor - 1) > 0) {
                                final Parser parser9 = this.parser;
                                --parser9.cursor;
                            }
                            q.cat(this.parser.buffer.buffer, this.parser.token, this.parser.cursor - this.parser.token);
                            break;
                        }
                        q.plain_is_inl();
                        this.RETURN_IMPLICIT(q);
                        return 263;
                    }
                    case 93: {
                        final Parser parser10 = this.parser;
                        ++parser10.cursor;
                        if (plvl.status != LevelStatus.iseq) {
                            if (this.parser.buffer.buffer[this.parser.cursor - 1] == 32 || this.isNewline(this.parser.cursor - 1) > 0) {
                                final Parser parser11 = this.parser;
                                --parser11.cursor;
                            }
                            q.cat(this.parser.buffer.buffer, this.parser.token, this.parser.cursor - this.parser.token);
                            break;
                        }
                        q.plain_is_inl();
                        this.RETURN_IMPLICIT(q);
                        return 263;
                    }
                    case 58: {
                        final Parser parser12 = this.parser;
                        ++parser12.cursor;
                        if (this.endspc()) {
                            this.RETURN_IMPLICIT(q);
                            return 263;
                        }
                        final Parser parser13 = this.parser;
                        --parser13.cursor;
                        do_any = true;
                        break;
                    }
                    case 44: {
                        final Parser parser14 = this.parser;
                        ++parser14.cursor;
                        if (!this.endspc()) {
                            final Parser parser15 = this.parser;
                            --parser15.cursor;
                            do_any = true;
                            break;
                        }
                        if (plvl.status != LevelStatus.iseq && plvl.status != LevelStatus.imap) {
                            if (this.parser.buffer.buffer[this.parser.cursor - 1] == 32 || this.isNewline(this.parser.cursor - 1) > 0) {
                                final Parser parser16 = this.parser;
                                --parser16.cursor;
                            }
                            q.cat(this.parser.buffer.buffer, this.parser.token, this.parser.cursor - this.parser.token);
                            break;
                        }
                        q.plain_is_inl();
                        this.RETURN_IMPLICIT(q);
                        return 263;
                    }
                    default: {
                        do_any = true;
                        break;
                    }
                }
                if (do_any) {
                    final Parser parser17 = this.parser;
                    ++parser17.cursor;
                    q.cat(this.parser.buffer.buffer, this.parser.token, this.parser.cursor - this.parser.token);
                    do_any = false;
                }
            } while (plain3);
        }
    }
    
    private int doubleQuote() throws IOException {
        int keep_nl = 1;
        final QuotedString q = new QuotedString();
    Label_1057:
        while (true) {
            this.parser.token = this.parser.cursor;
            int gotoPoint = -1;
            byte yych = 0;
            int yyaccept = 0;
            while (gotoPoint != -2) {
                final int currentGoto = gotoPoint;
                gotoPoint = -2;
                switch (currentGoto) {
                    case -1: {
                        if (this.parser.limit - this.parser.cursor < 4) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 0: {
                                gotoPoint = 58;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 53;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 55;
                                continue;
                            }
                            case 34: {
                                gotoPoint = 60;
                                continue;
                            }
                            case 92: {
                                gotoPoint = 57;
                                continue;
                            }
                            default: {
                                gotoPoint = 61;
                                continue;
                            }
                        }
                        break;
                    }
                    case 53: {
                        yyaccept = 0;
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
                        gotoPoint = 75;
                        continue;
                    }
                    case 54: {
                        int indent = this.parser.token;
                        this.NEWLINE(indent);
                        while (indent < this.parser.cursor) {
                            if (this.isNewline(++indent) != 0) {
                                this.NEWLINE(indent);
                            }
                        }
                        int indt_len = 0;
                        if (this.parser.buffer.buffer[this.parser.cursor] == 0) {
                            indt_len = -1;
                            this.parser.token = this.parser.cursor - 1;
                        }
                        else if (this.parser.buffer.buffer[this.parser.lineptr] == 32) {
                            indt_len = this.parser.cursor - this.parser.lineptr;
                        }
                        final int nl_count = 0;
                        final Level lvl = this.parser.currentLevel();
                        if (lvl.status != LevelStatus.str) {
                            this.parser.addLevel(indt_len, LevelStatus.str);
                        }
                        else if (indt_len < lvl.spaces) {}
                        if (keep_nl == 1) {
                            this.countAndAddNewlines(q);
                        }
                        keep_nl = 1;
                        continue;
                    }
                    case 55: {
                        final Parser parser2 = this.parser;
                        ++parser2.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 74;
                                continue;
                            }
                            default: {
                                gotoPoint = 56;
                                continue;
                            }
                        }
                        break;
                    }
                    case 56: {
                        q.cat(this.parser.buffer.buffer[this.parser.cursor - 1]);
                        continue;
                    }
                    case 57: {
                        yyaccept = 1;
                        final byte[] buffer2 = this.parser.buffer.buffer;
                        final Parser parser3 = this.parser;
                        final int marker2 = ++this.parser.cursor;
                        parser3.marker = marker2;
                        yych = buffer2[marker2];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 65;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 67;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 62;
                                continue;
                            }
                            case 34:
                            case 48:
                            case 92:
                            case 97:
                            case 98:
                            case 101:
                            case 102:
                            case 110:
                            case 114:
                            case 116:
                            case 118: {
                                gotoPoint = 69;
                                continue;
                            }
                            case 120: {
                                gotoPoint = 68;
                                continue;
                            }
                            default: {
                                gotoPoint = 56;
                                continue;
                            }
                        }
                        break;
                    }
                    case 58: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                    }
                    case 59: {
                        break Label_1057;
                    }
                    case 60: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 59;
                        continue;
                    }
                    case 61: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 56;
                        continue;
                    }
                    case 62: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        if (this.parser.limit - this.parser.cursor < 2) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 65;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 67;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 62;
                                continue;
                            }
                            default: {
                                gotoPoint = 64;
                                continue;
                            }
                        }
                        break;
                    }
                    case 64: {
                        this.parser.cursor = this.parser.marker;
                        if (yyaccept == 0) {
                            gotoPoint = 54;
                            continue;
                        }
                        gotoPoint = 56;
                        continue;
                    }
                    case 65: {
                        final Parser parser6 = this.parser;
                        ++parser6.cursor;
                        keep_nl = 0;
                        final Parser parser7 = this.parser;
                        --parser7.cursor;
                        continue;
                    }
                    case 67: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 65;
                                continue;
                            }
                            default: {
                                gotoPoint = 64;
                                continue;
                            }
                        }
                        break;
                    }
                    case 68: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
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
                            case 57:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102: {
                                gotoPoint = 71;
                                continue;
                            }
                            default: {
                                gotoPoint = 64;
                                continue;
                            }
                        }
                        break;
                    }
                    case 69: {
                        final Parser parser8 = this.parser;
                        ++parser8.cursor;
                        final byte ch = this.parser.buffer.buffer[this.parser.cursor - 1];
                        q.cat(this.escapeSeq(ch));
                        continue;
                    }
                    case 71: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
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
                            case 57:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102: {
                                gotoPoint = 72;
                                continue;
                            }
                            default: {
                                gotoPoint = 64;
                                continue;
                            }
                        }
                        break;
                    }
                    case 72: {
                        final Parser parser9 = this.parser;
                        ++parser9.cursor;
                        q.cat((byte)(int)Integer.valueOf(new String(this.parser.buffer.buffer, this.parser.token + 2, 2, "ISO-8859-1"), 16));
                        continue;
                    }
                    case 74: {
                        yyaccept = 0;
                        this.parser.marker = ++this.parser.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 75: {
                        switch (yych) {
                            case 10:
                            case 32: {
                                gotoPoint = 74;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 76;
                                continue;
                            }
                            default: {
                                gotoPoint = 54;
                                continue;
                            }
                        }
                        break;
                    }
                    case 76: {
                        final Parser parser10 = this.parser;
                        ++parser10.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 74;
                                continue;
                            }
                            default: {
                                gotoPoint = 64;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
        }
        final Node n = Node.allocStr();
        final Level lvl2 = this.parser.currentLevel();
        if (lvl2.status == LevelStatus.str) {
            this.parser.popLevel();
        }
        if (this.parser.taguri_expansion) {
            n.type_id = Parser.taguri("yaml.org,2002", "str");
        }
        else {
            n.type_id = "str";
        }
        final Data.Str dd = (Data.Str)n.data;
        dd.ptr = Pointer.create(q.str, 0);
        dd.len = q.idx;
        dd.style = ScalarStyle.TwoQuote;
        this.lval = n;
        return 263;
    }
    
    private int singleQuote() throws IOException {
        final QuotedString q = new QuotedString();
    Label_0764:
        while (true) {
            this.parser.token = this.parser.cursor;
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
                                gotoPoint = 85;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 79;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 81;
                                continue;
                            }
                            case 39: {
                                gotoPoint = 83;
                                continue;
                            }
                            default: {
                                gotoPoint = 86;
                                continue;
                            }
                        }
                        break;
                    }
                    case 79: {
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
                        gotoPoint = 90;
                        continue;
                    }
                    case 80: {
                        int indent = this.parser.token;
                        this.NEWLINE(indent);
                        while (indent < this.parser.cursor) {
                            if (this.isNewline(++indent) != 0) {
                                this.NEWLINE(indent);
                            }
                        }
                        int indt_len = 0;
                        if (this.parser.buffer.buffer[this.parser.cursor] == 0) {
                            indt_len = -1;
                            this.parser.token = this.parser.cursor - 1;
                        }
                        else if (this.parser.buffer.buffer[this.parser.lineptr] == 32) {
                            indt_len = this.parser.cursor - this.parser.lineptr;
                        }
                        int nl_count = 0;
                        final Level lvl = this.parser.currentLevel();
                        if (lvl.status != LevelStatus.str) {
                            this.parser.addLevel(indt_len, LevelStatus.str);
                        }
                        else if (indt_len < lvl.spaces) {}
                        while (this.parser.token < this.parser.cursor) {
                            final int nl_len = this.newlineLen(this.parser.token++);
                            if (nl_len > 0) {
                                ++nl_count;
                                final Parser parser2 = this.parser;
                                parser2.token += nl_len - 1;
                            }
                        }
                        if (nl_count <= 1) {
                            q.cat(' ');
                            continue;
                        }
                        for (int i = 0; i < nl_count - 1; ++i) {
                            q.cat('\n');
                        }
                        continue;
                    }
                    case 81: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 89;
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
                        q.cat(this.parser.buffer.buffer[this.parser.cursor - 1]);
                        continue;
                    }
                    case 83: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 39: {
                                gotoPoint = 87;
                                continue;
                            }
                            default: {
                                gotoPoint = 84;
                                continue;
                            }
                        }
                        break;
                    }
                    case 84: {
                        break Label_0764;
                    }
                    case 85: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 84;
                        continue;
                    }
                    case 86: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 82;
                        continue;
                    }
                    case 87: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        q.cat('\'');
                        continue;
                    }
                    case 89: {
                        this.parser.marker = ++this.parser.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 90: {
                        switch (yych) {
                            case 10:
                            case 32: {
                                gotoPoint = 89;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 91;
                                continue;
                            }
                            default: {
                                gotoPoint = 80;
                                continue;
                            }
                        }
                        break;
                    }
                    case 91: {
                        final Parser parser6 = this.parser;
                        ++parser6.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 89;
                                continue;
                            }
                            default: {
                                gotoPoint = 92;
                                continue;
                            }
                        }
                        break;
                    }
                    case 92: {
                        this.parser.cursor = this.parser.marker;
                        gotoPoint = 80;
                        continue;
                    }
                }
            }
        }
        final Node n = Node.allocStr();
        final Level lvl2 = this.parser.currentLevel();
        if (lvl2.status == LevelStatus.str) {
            this.parser.popLevel();
        }
        if (this.parser.taguri_expansion) {
            n.type_id = Parser.taguri("yaml.org,2002", "str");
        }
        else {
            n.type_id = "str";
        }
        final Data.Str dd = (Data.Str)n.data;
        dd.ptr = Pointer.create(q.str, 0);
        dd.len = q.idx;
        dd.style = ScalarStyle.OneQuote;
        this.lval = n;
        return 263;
    }
    
    private int transferMethod() throws IOException {
        final QuotedString q = new QuotedString();
    Label_0325:
        while (true) {
            this.parser.toktmp = this.parser.cursor;
            int gotoPoint = -1;
            byte yych = 0;
            while (gotoPoint != -2) {
                final int currentGoto = gotoPoint;
                gotoPoint = -2;
                switch (currentGoto) {
                    case -1: {
                        if (this.parser.limit - this.parser.cursor < 4) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 0: {
                                gotoPoint = 95;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 97;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 99;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 98;
                                continue;
                            }
                            case 92: {
                                gotoPoint = 101;
                                continue;
                            }
                            default: {
                                gotoPoint = 102;
                                continue;
                            }
                        }
                        break;
                    }
                    case 95: {
                        final Parser parser = this.parser;
                        ++parser.cursor;
                    }
                    case 96: {
                        break Label_0325;
                    }
                    case 97: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 96;
                        continue;
                    }
                    case 98: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 111;
                        continue;
                    }
                    case 99: {
                        final Parser parser2 = this.parser;
                        ++parser2.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 95;
                                continue;
                            }
                            default: {
                                gotoPoint = 100;
                                continue;
                            }
                        }
                        break;
                    }
                    case 100: {
                        q.cat(this.parser.buffer.buffer[this.parser.cursor - 1]);
                        continue;
                    }
                    case 101: {
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser3 = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser3.marker = marker;
                        yych = buffer[marker];
                        switch (yych) {
                            case 34:
                            case 48:
                            case 92:
                            case 97:
                            case 98:
                            case 101:
                            case 102:
                            case 110:
                            case 114:
                            case 116:
                            case 118: {
                                gotoPoint = 105;
                                continue;
                            }
                            case 120: {
                                gotoPoint = 103;
                                continue;
                            }
                            default: {
                                gotoPoint = 100;
                                continue;
                            }
                        }
                        break;
                    }
                    case 102: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 100;
                        continue;
                    }
                    case 103: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
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
                            case 57:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102: {
                                gotoPoint = 107;
                                continue;
                            }
                            default: {
                                gotoPoint = 104;
                                continue;
                            }
                        }
                        break;
                    }
                    case 104: {
                        this.parser.cursor = this.parser.marker;
                        gotoPoint = 100;
                        continue;
                    }
                    case 105: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        final byte ch = this.parser.buffer.buffer[this.parser.cursor - 1];
                        q.cat(this.escapeSeq(ch));
                        continue;
                    }
                    case 107: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
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
                            case 57:
                            case 65:
                            case 66:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101:
                            case 102: {
                                gotoPoint = 108;
                                continue;
                            }
                            default: {
                                gotoPoint = 104;
                                continue;
                            }
                        }
                        break;
                    }
                    case 108: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        q.cat((byte)(int)Integer.valueOf(new String(this.parser.buffer.buffer, this.parser.toktmp + 2, 2, "ISO-8859-1"), 16));
                        continue;
                    }
                    case 110: {
                        final Parser parser6 = this.parser;
                        ++parser6.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 111: {
                        switch (yych) {
                            case 32: {
                                gotoPoint = 110;
                                continue;
                            }
                            default: {
                                gotoPoint = 96;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
        }
        this.parser.cursor = this.parser.toktmp;
        if (this.parser.cursor == this.parser.token + 1) {
            return 261;
        }
        final Level lvl = this.parser.currentLevel();
        if (q.str[0] == 94) {
            this.lval = lvl.domain + new String(q.str, 1, q.idx - 1, "ISO-8859-1");
        }
        else {
            int carat = 0;
            final int qend = q.idx;
            while (++carat < qend && q.str[carat] != 94) {}
            if (carat < qend) {
                lvl.domain = new String(q.str, 0, carat, "ISO-8859-1");
                this.lval = lvl.domain + new String(q.str, carat + 1, qend - carat - 1, "ISO-8859-1");
            }
            else {
                this.lval = new String(q.str, 0, qend, "ISO-8859-1");
            }
        }
        return 259;
    }
    
    private int scalarBlock() throws IOException {
        final QuotedString q = new QuotedString();
        q.str[0] = 0;
        int lastIndent = 0;
        int parentIndent = -1;
        int blockType = 0;
        int nlDoWhat = 0;
        int forceIndent = -1;
        int yyt = this.parser.token;
        Level lvl = this.parser.currentLevel();
        switch (this.parser.buffer.buffer[yyt]) {
            case 124: {
                blockType = 20;
                break;
            }
            case 62: {
                blockType = 10;
                break;
            }
        }
        while (++yyt <= this.parser.cursor) {
            if (this.parser.buffer.buffer[yyt] == 45) {
                nlDoWhat = 40;
            }
            else if (this.parser.buffer.buffer[yyt] == 43) {
                nlDoWhat = 50;
            }
            else {
                if (!Character.isDigit((char)this.parser.buffer.buffer[yyt])) {
                    continue;
                }
                forceIndent = (char)this.parser.buffer.buffer[yyt] - '0';
            }
        }
        while (true) {
            this.parser.token = this.parser.cursor;
            int gotoPoint = -1;
            byte yych = 0;
            int yyaccept = 0;
            while (gotoPoint != -2) {
                final int currentGoto = gotoPoint;
                gotoPoint = -2;
                switch (currentGoto) {
                    case -1: {
                        if (this.parser.limit - this.parser.cursor < 5) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 0: {
                                gotoPoint = 120;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 114;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 116;
                                continue;
                            }
                            case 35: {
                                gotoPoint = 118;
                                continue;
                            }
                            case 45: {
                                gotoPoint = 122;
                                continue;
                            }
                            default: {
                                gotoPoint = 123;
                                continue;
                            }
                        }
                        break;
                    }
                    case 114: {
                        yyaccept = 0;
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser.marker = marker;
                        yych = buffer[marker];
                        gotoPoint = 133;
                        continue;
                    }
                    case 115: {
                        final int tok = this.parser.token;
                        int nl_count = 0;
                        int fold_nl = 0;
                        int nl_begin = 0;
                        final int indt_len = this.getAndCheckIndentLength();
                        lvl = this.parser.currentLevel();
                        if (lvl.status != LevelStatus.block) {
                            parentIndent = this.GET_TRUE_YAML_INDENT();
                            if (forceIndent > 0) {
                                forceIndent += parentIndent;
                            }
                            if (indt_len <= parentIndent) {
                                this.parser.cursor = this.parser.token;
                                this.RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                                return 264;
                            }
                            final int new_spaces = (forceIndent > 0) ? forceIndent : indt_len;
                            this.parser.addLevel(new_spaces, LevelStatus.block);
                            lastIndent = indt_len - new_spaces;
                            nl_begin = 1;
                            lvl = this.parser.currentLevel();
                        }
                        if (blockType == 10 && lastIndent == 0 && indt_len - lvl.spaces == 0) {
                            fold_nl = 1;
                        }
                        int nl_len;
                        for (int pacer = this.parser.token; pacer < this.parser.cursor; pacer += nl_len - 1) {
                            nl_len = this.newlineLen(pacer++);
                            if (nl_len > 0) {
                                ++nl_count;
                            }
                        }
                        if (fold_nl == 1 || nl_begin == 1) {
                            --nl_count;
                        }
                        if (nl_count < 1 && nl_begin == 0) {
                            q.cat(' ');
                        }
                        else {
                            for (int i = 0; i < nl_count; ++i) {
                                q.cat('\n');
                            }
                        }
                        lastIndent = indt_len - lvl.spaces;
                        final Parser parser2 = this.parser;
                        parser2.cursor -= lastIndent;
                        if (indt_len < lvl.spaces) {
                            this.parser.popLevel();
                            this.parser.cursor = this.parser.token;
                            this.RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                            return 264;
                        }
                        continue;
                    }
                    case 116: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 132;
                                continue;
                            }
                            default: {
                                gotoPoint = 117;
                                continue;
                            }
                        }
                        break;
                    }
                    case 117: {
                        q.cat(this.parser.buffer.buffer[this.parser.token]);
                        continue;
                    }
                    case 118: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        lvl = this.parser.currentLevel();
                        if (lvl.status != LevelStatus.block) {
                            this.eatComments();
                            this.parser.token = this.parser.cursor;
                            continue;
                        }
                        q.cat(this.parser.buffer.buffer[this.parser.token]);
                        continue;
                    }
                    case 120: {
                        final Parser parser5 = this.parser;
                        ++parser5.cursor;
                        final Parser parser6 = this.parser;
                        --parser6.cursor;
                        this.parser.popLevel();
                        this.RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                        return 264;
                    }
                    case 122: {
                        yyaccept = 1;
                        final byte[] buffer2 = this.parser.buffer.buffer;
                        final Parser parser7 = this.parser;
                        final int marker2 = ++this.parser.cursor;
                        parser7.marker = marker2;
                        yych = buffer2[marker2];
                        switch (yych) {
                            case 45: {
                                gotoPoint = 124;
                                continue;
                            }
                            default: {
                                gotoPoint = 117;
                                continue;
                            }
                        }
                        break;
                    }
                    case 123: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 117;
                        continue;
                    }
                    case 124: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 45: {
                                gotoPoint = 126;
                                continue;
                            }
                            default: {
                                gotoPoint = 125;
                                continue;
                            }
                        }
                        break;
                    }
                    case 125: {
                        this.parser.cursor = this.parser.marker;
                        if (yyaccept == 0) {
                            gotoPoint = 115;
                            continue;
                        }
                        gotoPoint = 117;
                        continue;
                    }
                    case 126: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 127;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 131;
                                continue;
                            }
                            case 32: {
                                gotoPoint = 129;
                                continue;
                            }
                            default: {
                                gotoPoint = 125;
                                continue;
                            }
                        }
                        break;
                    }
                    case 127: {
                        final Parser parser8 = this.parser;
                        ++parser8.cursor;
                    }
                    case 128: {
                        if (this.parser.token == this.parser.lineptr) {
                            if (blockType == 10 && q.idx > 0) {
                                final QuotedString quotedString = q;
                                --quotedString.idx;
                            }
                            q.cat('\n');
                            this.parser.popLevel();
                            this.parser.cursor = this.parser.token;
                            this.RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                            return 264;
                        }
                        q.cat(this.parser.buffer.buffer[this.parser.token]);
                        this.parser.cursor = this.parser.token + 1;
                        continue;
                    }
                    case 129: {
                        final Parser parser9 = this.parser;
                        ++parser9.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 32: {
                                gotoPoint = 129;
                                continue;
                            }
                            default: {
                                gotoPoint = 128;
                                continue;
                            }
                        }
                        break;
                    }
                    case 131: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 127;
                                continue;
                            }
                            default: {
                                gotoPoint = 125;
                                continue;
                            }
                        }
                        break;
                    }
                    case 132: {
                        yyaccept = 0;
                        this.parser.marker = ++this.parser.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 133: {
                        switch (yych) {
                            case 10:
                            case 32: {
                                gotoPoint = 132;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 134;
                                continue;
                            }
                            default: {
                                gotoPoint = 115;
                                continue;
                            }
                        }
                        break;
                    }
                    case 134: {
                        final Parser parser10 = this.parser;
                        ++parser10.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 132;
                                continue;
                            }
                            default: {
                                gotoPoint = 125;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private byte escapeSeq(final byte ch) {
        switch (ch) {
            case 48: {
                return 0;
            }
            case 97: {
                return 7;
            }
            case 98: {
                return 8;
            }
            case 101: {
                return 27;
            }
            case 102: {
                return 12;
            }
            case 110: {
                return 10;
            }
            case 114: {
                return 13;
            }
            case 116: {
                return 9;
            }
            case 118: {
                return 11;
            }
            default: {
                return ch;
            }
        }
    }
    
    private void eatComments() throws IOException {
    Label_0249:
        while (true) {
            this.parser.token = this.parser.cursor;
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
                                gotoPoint = 137;
                                continue;
                            }
                            case 10: {
                                gotoPoint = 139;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 140;
                                continue;
                            }
                            default: {
                                gotoPoint = 142;
                                continue;
                            }
                        }
                        break;
                    }
                    case 137: {
                        final Parser parser = this.parser;
                        ++parser.cursor;
                    }
                    case 138: {
                        break Label_0249;
                    }
                    case 139: {
                        final byte[] buffer = this.parser.buffer.buffer;
                        final Parser parser2 = this.parser;
                        final int marker = ++this.parser.cursor;
                        parser2.marker = marker;
                        yych = buffer[marker];
                        gotoPoint = 144;
                        continue;
                    }
                    case 140: {
                        final Parser parser3 = this.parser;
                        ++parser3.cursor;
                        switch (yych = this.parser.buffer.buffer[this.parser.cursor]) {
                            case 10: {
                                gotoPoint = 143;
                                continue;
                            }
                            default: {
                                gotoPoint = 141;
                                continue;
                            }
                        }
                        break;
                    }
                    case 141: {
                        continue;
                    }
                    case 142: {
                        yych = this.parser.buffer.buffer[++this.parser.cursor];
                        gotoPoint = 141;
                        continue;
                    }
                    case 143: {
                        this.parser.marker = ++this.parser.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                    }
                    case 144: {
                        switch (yych) {
                            case 10: {
                                gotoPoint = 143;
                                continue;
                            }
                            case 13: {
                                gotoPoint = 145;
                                continue;
                            }
                            default: {
                                gotoPoint = 138;
                                continue;
                            }
                        }
                        break;
                    }
                    case 145: {
                        final Parser parser4 = this.parser;
                        ++parser4.cursor;
                        if (this.parser.limit <= this.parser.cursor) {
                            this.parser.read();
                        }
                        yych = this.parser.buffer.buffer[this.parser.cursor];
                        switch (yych) {
                            case 10: {
                                gotoPoint = 143;
                                continue;
                            }
                            default: {
                                gotoPoint = 146;
                                continue;
                            }
                        }
                        break;
                    }
                    case 146: {
                        this.parser.cursor = this.parser.marker;
                        gotoPoint = 138;
                        continue;
                    }
                }
            }
        }
        this.parser.cursor = this.parser.token;
    }
    
    static {
        (tnames = new String[269])[0] = "ENDINPUT";
        TokenScanner.tnames[256] = "error";
        TokenScanner.tnames[44] = "COMMA";
        TokenScanner.tnames[45] = "DASH";
        TokenScanner.tnames[58] = "COLON";
        TokenScanner.tnames[63] = "QUESTION";
        TokenScanner.tnames[91] = "SQUAREO";
        TokenScanner.tnames[93] = "SQUAREC";
        TokenScanner.tnames[123] = "CURLYO";
        TokenScanner.tnames[125] = "CURLYC";
        TokenScanner.tnames[257] = "ANCHOR";
        TokenScanner.tnames[258] = "ALIAS";
        TokenScanner.tnames[264] = "BLOCK";
        TokenScanner.tnames[265] = "DOCSEP";
        TokenScanner.tnames[268] = "IEND";
        TokenScanner.tnames[267] = "INDENT";
        TokenScanner.tnames[266] = "IOPEN";
        TokenScanner.tnames[261] = "ITRANSFER";
        TokenScanner.tnames[263] = "PLAIN";
        TokenScanner.tnames[260] = "TAGURI";
        TokenScanner.tnames[259] = "TRANSFER";
        TokenScanner.tnames[262] = "WORD";
        YWORDC = new boolean[256];
        DIGIT_OR_SIGN = new boolean[256];
        for (char c = 'a'; c <= 'z'; ++c) {
            TokenScanner.YWORDC[c] = true;
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            TokenScanner.YWORDC[c] = true;
        }
        for (char c = '0'; c <= '9'; ++c) {
            TokenScanner.YWORDC[c] = true;
            TokenScanner.DIGIT_OR_SIGN[c] = true;
        }
        TokenScanner.YWORDC[95] = true;
        TokenScanner.YWORDC[45] = true;
        TokenScanner.DIGIT_OR_SIGN[45] = true;
        TokenScanner.DIGIT_OR_SIGN[43] = true;
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
                this.capa += 1024;
                this.str = YAML.realloc(this.str, this.capa);
            }
            this.str[this.idx++] = l;
            this.str[this.idx] = 0;
        }
        
        public void cat(final byte[] l, final int cs, final int cl) {
            while (this.idx + cl >= this.capa) {
                this.capa += 1024;
                this.str = YAML.realloc(this.str, this.capa);
            }
            System.arraycopy(l, cs, this.str, this.idx, cl);
            this.idx += cl;
            this.str[this.idx] = 0;
        }
        
        public void plain_is_inl() {
            for (int walker = this.idx - 1; walker > 0 && (this.str[walker] == 10 || this.str[walker] == 32 || this.str[walker] == 9); --walker) {
                --this.idx;
                this.str[walker] = 0;
            }
        }
    }
}
