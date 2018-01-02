// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.util.IdentityHashMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Emitter
{
    public boolean headless;
    public boolean use_header;
    public boolean use_version;
    public boolean sort_keys;
    public String anchor_format;
    public boolean explicit_typing;
    public int best_width;
    public ScalarStyle style;
    public DocStage stage;
    public int level;
    public int indent;
    Map<Object, Long> markers;
    Map<Long, String> anchors;
    Map<String, Object> anchored;
    int bufsize;
    byte[] buffer;
    int marker;
    int bufpos;
    EmitterHandler emitter_handler;
    OutputHandler output_handler;
    Level[] levels;
    int lvl_idx;
    int lvl_capa;
    public Object bonus;
    private static final Pointer NEWLINE;
    private static final Pointer TWO_NEWLINES;
    private static final Pointer SPACE;
    private static final Pointer SLASH;
    private static final Pointer THREE_DASHES;
    private static final Pointer QUESTION_MARK_SPACE;
    private static final Pointer BANG;
    private static final Pointer BANG_SPACE;
    private static final Pointer TWO_BANGS;
    private static final Pointer BACKSLASH;
    private static final Pointer ZERO;
    private static final Pointer X;
    private static final Pointer SINGLE_QUOTE;
    private static final Pointer DOUBLE_QUOTE;
    private static final Pointer PIPE;
    private static final Pointer PLUS;
    private static final Pointer MINUS;
    private static final Pointer GT;
    private static final Pointer SQUARE_OPEN;
    private static final Pointer SQUARE_CLOSE;
    private static final Pointer CURLY_OPEN;
    private static final Pointer CURLY_CLOSE;
    private static final Pointer DASH_SPACE;
    private static final Pointer COMMA_SPACE;
    private static final Pointer COLON_SPACE;
    private static final Pointer EMPTY_ARRAY;
    private static final Pointer EMPTY_HASH;
    private static final Pointer COLON;
    private static final int SCAN_NONE = 0;
    private static final int SCAN_NONPRINT = 1;
    private static final int SCAN_INDENTED = 2;
    private static final int SCAN_WIDE = 4;
    private static final int SCAN_WHITEEDGE = 8;
    private static final int SCAN_NEWLINE = 16;
    private static final int SCAN_SINGLEQ = 32;
    private static final int SCAN_DOUBLEQ = 64;
    private static final int SCAN_INDIC_S = 128;
    private static final int SCAN_INDIC_C = 256;
    private static final int SCAN_NONL_E = 512;
    private static final int SCAN_MANYNL_E = 1024;
    private static final int SCAN_FLOWMAP = 2048;
    private static final int SCAN_FLOWSEQ = 4096;
    private static final int SCAN_DOCSEP = 8192;
    private static final Pointer EMPTY;
    private static final Pointer TILDE;
    private static final Pointer hex_table;
    private static final Pointer SLASH_QUOTE;
    private static final Pointer SLASH_SLASH;
    private static final Pointer SLASH_ZERO;
    private static final Pointer SLASH_A;
    private static final Pointer SLASH_B;
    private static final Pointer SLASH_F;
    private static final Pointer SLASH_R;
    private static final Pointer SLASH_T;
    private static final Pointer SLASH_V;
    private static final Pointer SLASH_E;
    private static final Pointer SLASH_N;
    
    public Emitter() {
        this.headless = false;
        this.use_header = false;
        this.use_version = false;
        this.sort_keys = false;
        this.anchor_format = null;
        this.explicit_typing = false;
        this.best_width = 80;
        this.style = ScalarStyle.None;
        this.stage = DocStage.open;
        this.indent = 2;
        this.level = -1;
        this.anchors = null;
        this.markers = null;
        this.anchored = null;
        this.bufsize = 4096;
        this.buffer = null;
        this.marker = -1;
        this.bufpos = 0;
        this.emitter_handler = null;
        this.output_handler = null;
        this.lvl_idx = 0;
        this.lvl_capa = 8;
        this.levels = new Level[this.lvl_capa];
        this.resetLevels();
        this.bonus = null;
    }
    
    public Level currentLevel() {
        return this.levels[this.lvl_idx - 1];
    }
    
    public Level parentLevel() {
        return this.levels[this.lvl_idx - 2];
    }
    
    public void popLevel() {
        if (this.lvl_idx <= 1) {
            return;
        }
        --this.lvl_idx;
    }
    
    public void addLevel(final int len, final LevelStatus status) {
        if (this.lvl_idx + 1 > this.lvl_capa) {
            this.lvl_capa += 8;
            this.levels = YAML.realloc(this.levels, this.lvl_capa);
        }
        this.levels[this.lvl_idx] = new Level();
        this.levels[this.lvl_idx].spaces = len;
        this.levels[this.lvl_idx].ncount = 0;
        this.levels[this.lvl_idx].domain = this.levels[this.lvl_idx - 1].domain;
        this.levels[this.lvl_idx].status = status;
        this.levels[this.lvl_idx].anctag = 0;
        ++this.lvl_idx;
    }
    
    public void resetLevels() {
        while (this.lvl_idx > 1) {
            this.popLevel();
        }
        if (this.lvl_idx < 1) {
            this.lvl_idx = 1;
            this.levels[0] = new Level();
            this.levels[0].spaces = -1;
            this.levels[0].ncount = 0;
            this.levels[0].domain = "";
            this.levels[0].anctag = 0;
        }
        this.levels[0].status = LevelStatus.header;
    }
    
    public void handler(final EmitterHandler hdlr) {
        this.emitter_handler = hdlr;
    }
    
    public void outputHandler(final OutputHandler hdlr) {
        this.output_handler = hdlr;
    }
    
    public void clear() {
        if (this.buffer == null) {
            this.buffer = new byte[this.bufsize];
        }
        this.buffer[0] = 0;
        this.marker = 0;
        this.bufpos = 0;
    }
    
    public void write(final Pointer _str, final int _len) {
        int len = _len;
        final byte[] bstr = _str.buffer;
        int str = _str.start;
        if (this.buffer == null) {
            this.clear();
        }
        final int at = this.marker;
        if (len + at >= this.bufsize - 1) {
            this.flush(0);
            while (true) {
                final int rest = this.bufsize - 1 - this.marker;
                if (len <= rest) {
                    break;
                }
                System.arraycopy(bstr, str, this.buffer, this.marker, rest);
                this.marker += rest;
                str += rest;
                len -= rest;
                this.flush(0);
            }
        }
        System.arraycopy(bstr, str, this.buffer, this.marker, len);
        this.marker += len;
        this.buffer[this.marker] = 0;
    }
    
    public void flush(int check_room) {
        if (check_room > 0) {
            if (this.bufsize - 1 > this.marker + check_room) {
                return;
            }
        }
        else {
            check_room = this.bufsize - 1;
        }
        if (check_room > this.marker) {
            check_room = this.marker;
        }
        this.output_handler.handle(this, this.buffer, check_room);
        this.bufpos += check_room;
        this.marker -= check_room;
    }
    
    public void emit(final Object n) {
        int indent = 0;
        final int x = 0;
        Level lvl = this.currentLevel();
        if (this.stage == DocStage.open && (!this.headless || this.use_header)) {
            if (this.use_version) {
                final String header = "--- %YAML:1.0 ";
                this.write(Pointer.create(header), header.length());
            }
            else {
                this.write(Emitter.THREE_DASHES, 4);
            }
            this.stage = DocStage.processing;
        }
        if (lvl.spaces >= 0) {
            indent = lvl.spaces + this.indent;
        }
        this.addLevel(indent, LevelStatus.open);
        final Level parent = lvl;
        lvl = this.currentLevel();
        boolean handle = true;
        if (this.anchors != null && this.markers.containsKey(n)) {
            final long oid = this.markers.get(n);
            if (this.anchors.containsKey(oid)) {
                final String anchor_name = this.anchors.get(oid);
                if (this.anchored == null) {
                    this.anchored = new HashMap<String, Object>();
                }
                if (!this.anchored.containsKey(anchor_name)) {
                    final String an = "&" + anchor_name + " ";
                    if (parent.status == LevelStatus.map && parent.ncount % 2 == 1) {
                        this.write(Emitter.QUESTION_MARK_SPACE, 2);
                        parent.status = LevelStatus.mapx;
                    }
                    this.write(Pointer.create(an), an.length());
                    this.anchored.put(anchor_name, null);
                    lvl.anctag = 1;
                }
                else {
                    final String an = "*" + anchor_name;
                    this.write(Pointer.create(an), an.length());
                    handle = false;
                }
            }
        }
        if (handle) {
            this.emitter_handler.handle(this, n);
        }
        this.popLevel();
        if (this.lvl_idx == 1) {
            this.write(Emitter.NEWLINE, 1);
            this.headless = false;
            this.stage = DocStage.open;
        }
    }
    
    public void emitTag(final String tag, final String ignore) {
        if (tag == null) {
            return;
        }
        if (ignore != null && ImplicitScanner2.tagcmp(tag, ignore) && !this.explicit_typing) {
            return;
        }
        final Level lvl = this.currentLevel();
        if (tag.length() == 0) {
            this.write(Emitter.BANG_SPACE, 2);
        }
        else if (tag.startsWith("tag:")) {
            final int taglen = tag.length();
            final Pointer ptag = Pointer.create(tag);
            this.write(Emitter.BANG, 1);
            if (tag.substring(4).startsWith("yaml.org,2002")) {
                final int skip = 4 + "yaml.org,2002".length() + 1;
                this.write(ptag.withStart(skip), taglen - skip);
            }
            else {
                int subd;
                for (subd = 4; subd < taglen && tag.charAt(subd) != ':'; ++subd) {}
                if (subd >= taglen || tag.charAt(subd) != ':') {
                    return;
                }
                if (subd > "yaml.org,2002".length() + 5 && tag.substring(subd - "yaml.org,2002".length()).startsWith("yaml.org,2002")) {
                    this.write(ptag.withStart(4), subd - "yaml.org,2002".length() - 5);
                    this.write(Emitter.SLASH, 1);
                    this.write(ptag.withStart(subd + 1), taglen - (subd + 1));
                }
                else {
                    this.write(ptag.withStart(4), subd - 4);
                    this.write(Emitter.SLASH, 1);
                    this.write(ptag.withStart(subd + 1), taglen - (subd + 1));
                }
            }
            this.write(Emitter.SPACE, 1);
        }
        else if (tag.startsWith("x-private:")) {
            this.write(Emitter.TWO_BANGS, 2);
            this.write(Pointer.create(tag.substring(10)), tag.length() - 10);
            this.write(Emitter.SPACE, 1);
        }
        lvl.anctag = 1;
    }
    
    public void emitIndent() {
        final Level lvl = this.currentLevel();
        if (this.bufpos == 0 && this.marker == 0) {
            return;
        }
        if (lvl.spaces >= 0) {
            final byte[] spcs = new byte[lvl.spaces + 2];
            spcs[0] = 10;
            spcs[lvl.spaces + 1] = 0;
            for (int i = 0; i < lvl.spaces; ++i) {
                spcs[i + 1] = 32;
            }
            this.write(Pointer.create(spcs, 0), lvl.spaces + 1);
        }
    }
    
    public int scanScalar(final int req_width, final Pointer _cursor, final int len) {
        final byte[] cursorb = _cursor.buffer;
        final int cursor = _cursor.start;
        int start = 0;
        int flags = 0;
        if (len < 1) {
            return flags;
        }
        switch (cursorb[cursor]) {
            case 33:
            case 34:
            case 35:
            case 37:
            case 38:
            case 39:
            case 42:
            case 62:
            case 64:
            case 91:
            case 93:
            case 96:
            case 123:
            case 124:
            case 125: {
                flags |= 0x80;
                break;
            }
            case 44:
            case 45:
            case 58:
            case 63: {
                if (len == 1 || cursorb[cursor + 1] == 32 || cursorb[cursor + 1] == 10) {
                    flags |= 0x80;
                    break;
                }
                break;
            }
        }
        if (cursorb[cursor + len - 1] != 10) {
            flags |= 0x200;
        }
        else if (len > 1 && cursorb[cursor + len - 2] == 10) {
            flags |= 0x400;
        }
        if ((len > 0 && (cursorb[cursor] == 32 || cursorb[cursor] == 9)) || (len > 1 && (cursorb[cursor + len - 1] == 32 || cursorb[cursor + len - 1] == 9))) {
            flags |= 0x8;
        }
        if (len >= 3 && cursorb[cursor] == 45 && cursorb[cursor + 1] == 45 && cursorb[cursor + 2] == 45) {
            flags |= 0x2000;
        }
        for (int i = 0; i < len; ++i) {
            final int ci = cursorb[cursor + i] & 0xFF;
            if (ci != 9 && ci != 10 && ci != 13 && (ci < 32 || ci > 126)) {
                flags |= 0x1;
            }
            else if (ci == 10) {
                flags |= 0x10;
                if (len - i >= 3 && cursorb[cursor + i + 1] == 45 && cursorb[cursor + i + 2] == 45 && cursorb[cursor + i + 3] == 45) {
                    flags |= 0x2000;
                }
                if (i + 1 < len && (cursorb[cursor + i + 1] == 32 || cursorb[cursor + i + 1] == 9)) {
                    flags |= 0x2;
                }
                if (req_width > 0 && i - start > req_width) {
                    flags |= 0x4;
                }
                start = i;
            }
            else if (ci == 39) {
                flags |= 0x20;
            }
            else if (ci == 34) {
                flags |= 0x40;
            }
            else if (ci == 93) {
                flags |= 0x1000;
            }
            else if (ci == 125) {
                flags |= 0x800;
            }
            else if ((ci == 32 && i + 1 < len && cursorb[cursor + i + 1] == 35) || (ci == 58 && ((i + 1 < len && cursorb[cursor + i + 1] == 32) || (i + 1 < len && cursorb[cursor + i + 1] == 10) || i == len - 1))) {
                flags |= 0x100;
            }
            else if (ci == 44 && (i == len - 1 || cursorb[cursor + i + 1] == 32 || cursorb[cursor + i + 1] == 10)) {
                flags |= 0x800;
                flags |= 0x1000;
            }
        }
        return flags;
    }
    
    public void emitScalar(final String tag, ScalarStyle force_style, final int force_indent, final int force_width, int keep_nl, Pointer _str, int len) {
        if (_str == null) {
            _str = Emitter.EMPTY;
        }
        byte[] bstr = _str.buffer;
        int str = _str.start;
        ScalarStyle favor_style = ScalarStyle.Literal;
        final Level parent = this.parentLevel();
        final Level lvl = this.currentLevel();
        if (len == 0 && (parent.status == LevelStatus.map || parent.status == LevelStatus.imap) && parent.ncount % 2 == 1 && ImplicitScanner2.tagcmp(tag, "tag:yaml.org,2002:null")) {
            _str = Emitter.TILDE;
            bstr = _str.buffer;
            str = _str.start;
            len = 1;
        }
        final int scan = this.scanScalar(force_width, _str, len);
        final String implicit = Parser.taguri("yaml.org,2002", ImplicitScanner2.matchImplicit(_str, len));
        if (!ImplicitScanner2.tagcmp(tag, implicit) && ImplicitScanner2.tagcmp(tag, "tag:yaml.org,2002:str")) {
            force_style = ScalarStyle.TwoQuote;
        }
        else {
            if (parent.status == LevelStatus.map && parent.ncount % 2 == 1 && tag != null && (implicit == null || !ImplicitScanner2.tagcmp(tag, implicit) || this.explicit_typing)) {
                this.write(Emitter.QUESTION_MARK_SPACE, 2);
                parent.status = LevelStatus.mapx;
            }
            this.emitTag(tag, implicit);
        }
        if (force_style == ScalarStyle.None) {
            if ((scan & 0x10) != 0x0) {
                force_style = ScalarStyle.Literal;
            }
            else {
                force_style = ScalarStyle.Plain;
            }
        }
        if (this.style == ScalarStyle.Fold) {
            favor_style = ScalarStyle.Fold;
        }
        if ((scan & 0x1) != 0x0) {
            force_style = ScalarStyle.TwoQuote;
        }
        else if ((scan & 0x8) != 0x0) {
            force_style = ScalarStyle.TwoQuote;
        }
        else if (force_style != ScalarStyle.Fold && (scan & 0x2) != 0x0) {
            force_style = ScalarStyle.Literal;
        }
        else if (force_style == ScalarStyle.Plain && (scan & 0x10) != 0x0) {
            force_style = favor_style;
        }
        else if (force_style == ScalarStyle.Plain && parent.status == LevelStatus.iseq && (scan & 0x1000) != 0x0) {
            force_style = ScalarStyle.TwoQuote;
        }
        else if (force_style == ScalarStyle.Plain && parent.status == LevelStatus.imap && (scan & 0x800) != 0x0) {
            force_style = ScalarStyle.TwoQuote;
        }
        else if (force_style == ScalarStyle.Plain && ((scan & 0x80) != 0x0 || (scan & 0x100) != 0x0)) {
            if ((scan & 0x10) != 0x0) {
                force_style = favor_style;
            }
            else {
                force_style = ScalarStyle.TwoQuote;
            }
        }
        if (force_indent > 0) {
            lvl.spaces = parent.spaces + force_indent;
        }
        else if ((scan & 0x2000) != 0x0) {
            lvl.spaces = parent.spaces + this.indent;
        }
        if ((parent.status == LevelStatus.map || parent.status == LevelStatus.mapx) && parent.ncount % 2 == 1 && force_style != ScalarStyle.Plain) {
            force_style = ScalarStyle.TwoQuote;
        }
        if ((parent.status == LevelStatus.imap || parent.status == LevelStatus.iseq) && force_style != ScalarStyle.Plain && force_style != ScalarStyle.OneQuote) {
            force_style = ScalarStyle.TwoQuote;
        }
        if ((scan & 0x200) != 0x0) {
            keep_nl = 40;
        }
        else if ((scan & 0x400) != 0x0) {
            keep_nl = 50;
        }
        switch (force_style) {
            case OneQuote: {
                this.emit1Quoted(force_width, _str, len);
                break;
            }
            case None:
            case TwoQuote: {
                this.emit2Quoted(force_width, _str, len);
                break;
            }
            case Fold: {
                this.emitFolded(force_width, keep_nl, _str, len);
                break;
            }
            case Literal: {
                this.emitLiteral(keep_nl, _str, len);
                break;
            }
            case Plain: {
                this.write(_str, len);
                break;
            }
        }
        if (parent.status == LevelStatus.mapx) {
            this.write(Emitter.NEWLINE, 1);
        }
    }
    
    public void escape(final Pointer _src, final int len) {
        final byte[] bsrc = _src.buffer;
        final int src = _src.start;
        for (int i = 0; i < len; ++i) {
            final int curr = bsrc[src + i] & 0xFF;
            if (curr < 32 || 126 < curr) {
                this.write(Emitter.BACKSLASH, 1);
                if (curr == 0) {
                    this.write(Emitter.ZERO, 1);
                }
                else {
                    this.write(Emitter.X, 1);
                    this.write(Emitter.hex_table.withStart((curr & 0xF0) >> 4), 1);
                    this.write(Emitter.hex_table.withStart(curr & 0xF), 1);
                }
            }
            else {
                this.write(_src.withStart(src + i), 1);
                if (curr == 92) {
                    this.write(Emitter.BACKSLASH, 1);
                }
            }
        }
    }
    
    public void emit1Quoted(final int width, final Pointer _str, final int len) {
        final byte[] bstr = _str.buffer;
        final int str = _str.start;
        boolean do_indent = false;
        final int mark = str;
        int start = str;
        int end = str;
        this.write(Emitter.SINGLE_QUOTE, 1);
        while (mark < str + len) {
            if (do_indent) {
                this.emitIndent();
                do_indent = false;
            }
            switch (bstr[mark]) {
                case 39: {
                    this.write(Emitter.SINGLE_QUOTE, 1);
                    continue;
                }
                case 10: {
                    end = mark + 1;
                    if (bstr[start] != 32 && bstr[start] != 10 && bstr[end] != 10 && bstr[end] != 32) {
                        this.write(Emitter.TWO_NEWLINES, 2);
                    }
                    else {
                        this.write(Emitter.NEWLINE, 1);
                    }
                    do_indent = true;
                    start = mark + 1;
                    continue;
                }
                case 32: {
                    if (width > 0 && bstr[start] != 32 && mark - end > width) {
                        do_indent = true;
                        end = mark + 1;
                        continue;
                    }
                    this.write(Emitter.SPACE, 1);
                    continue;
                }
                default: {
                    this.write(_str.withStart(mark), 1);
                    continue;
                }
            }
        }
        this.write(Emitter.SINGLE_QUOTE, 1);
    }
    
    public void emit2Quoted(final int width, final Pointer _str, final int len) {
        final byte[] bstr = _str.buffer;
        final int str = _str.start;
        int do_indent = 0;
        int mark = str;
        int start = str;
        int end = str;
        this.write(Emitter.DOUBLE_QUOTE, 1);
        while (mark < str + len) {
            if (do_indent > 0) {
                if (do_indent == 2) {
                    this.write(Emitter.BACKSLASH, 1);
                }
                this.emitIndent();
                do_indent = 0;
            }
            switch (bstr[mark]) {
                case 34: {
                    this.write(Emitter.SLASH_QUOTE, 2);
                    break;
                }
                case 92: {
                    this.write(Emitter.SLASH_SLASH, 2);
                    break;
                }
                case 0: {
                    this.write(Emitter.SLASH_ZERO, 2);
                    break;
                }
                case 7: {
                    this.write(Emitter.SLASH_A, 2);
                    break;
                }
                case 8: {
                    this.write(Emitter.SLASH_B, 2);
                    break;
                }
                case 12: {
                    this.write(Emitter.SLASH_F, 2);
                    break;
                }
                case 13: {
                    this.write(Emitter.SLASH_R, 2);
                    break;
                }
                case 9: {
                    this.write(Emitter.SLASH_T, 2);
                    break;
                }
                case 11: {
                    this.write(Emitter.SLASH_V, 2);
                    break;
                }
                case 27: {
                    this.write(Emitter.SLASH_E, 2);
                    break;
                }
                case 10: {
                    end = mark + 1;
                    this.write(Emitter.SLASH_N, 2);
                    do_indent = 2;
                    start = mark + 1;
                    if (start < str + len && (bstr[start] == 32 || bstr[start] == 10)) {
                        do_indent = 0;
                        break;
                    }
                    break;
                }
                case 32: {
                    if (width > 0 && bstr[start] != 32 && mark - end > width) {
                        do_indent = 1;
                        end = mark + 1;
                        break;
                    }
                    this.write(Emitter.SPACE, 1);
                    break;
                }
                default: {
                    this.escape(_str.withStart(mark), 1);
                    break;
                }
            }
            ++mark;
        }
        this.write(Emitter.DOUBLE_QUOTE, 1);
    }
    
    public void emitLiteral(final int keep_nl, final Pointer _str, final int len) {
        final byte[] bstr = _str.buffer;
        int mark;
        int start;
        int end;
        final int str = end = (start = (mark = _str.start));
        this.write(Emitter.PIPE, 1);
        if (keep_nl == 40) {
            this.write(Emitter.MINUS, 1);
        }
        else if (keep_nl == 50) {
            this.write(Emitter.PLUS, 1);
        }
        this.emitIndent();
        while (mark < str + len) {
            if (bstr[mark] == 10) {
                end = mark;
                if (bstr[start] != 32 && bstr[start] != 10 && bstr[end] != 10 && bstr[end] != 32) {
                    ++end;
                }
                this.write(_str.withStart(start), end - start);
                if (mark + 1 == str + len) {
                    if (keep_nl != 50) {
                        this.write(Emitter.NEWLINE, 1);
                    }
                }
                else {
                    this.emitIndent();
                }
                start = mark + 1;
            }
            ++mark;
        }
        end = str + len;
        if (start < end) {
            this.write(_str.withStart(start), end - start);
        }
    }
    
    public void emitFolded(int width, final int keep_nl, final Pointer _str, final int len) {
        final byte[] bstr = _str.buffer;
        int mark;
        int start;
        int end;
        final int str = end = (start = (mark = _str.start));
        this.write(Emitter.GT, 1);
        if (keep_nl == 40) {
            this.write(Emitter.MINUS, 1);
        }
        else if (keep_nl == 50) {
            this.write(Emitter.PLUS, 1);
        }
        this.emitIndent();
        if (width <= 0) {
            width = this.best_width;
        }
        while (mark < str + len) {
            switch (bstr[mark]) {
                case 10: {
                    this.write(_str.withStart(end), mark - end);
                    end = mark + 1;
                    if (bstr[start] != 32 && bstr[start] != 10 && bstr[end] != 10 && bstr[end] != 32) {
                        this.write(Emitter.NEWLINE, 1);
                    }
                    if (mark + 1 == str + len) {
                        if (keep_nl != 50) {
                            this.write(Emitter.NEWLINE, 1);
                        }
                    }
                    else {
                        this.emitIndent();
                    }
                    start = mark + 1;
                    break;
                }
                case 32: {
                    if (bstr[start] != 32 && mark - end > width) {
                        this.write(_str.withStart(end), mark - end);
                        this.emitIndent();
                        end = mark + 1;
                        break;
                    }
                    break;
                }
            }
            ++mark;
        }
        if (end < mark) {
            this.write(_str.withStart(end), mark - end);
        }
    }
    
    public void emitSeq(final String tag, final SeqStyle style) {
        final Level parent = this.parentLevel();
        final Level lvl = this.currentLevel();
        if (parent.status == LevelStatus.map && parent.ncount % 2 == 1) {
            this.write(Emitter.QUESTION_MARK_SPACE, 2);
            parent.status = LevelStatus.mapx;
        }
        this.emitTag(tag, "tag:yaml.org,2002:seq");
        if (style == SeqStyle.Inline || parent.status == LevelStatus.imap || parent.status == LevelStatus.iseq) {
            this.write(Emitter.SQUARE_OPEN, 1);
            lvl.status = LevelStatus.iseq;
        }
        else {
            lvl.status = LevelStatus.seq;
        }
    }
    
    public void emitMap(final String tag, final MapStyle style) {
        final Level parent = this.parentLevel();
        final Level lvl = this.currentLevel();
        if (parent.status == LevelStatus.map && parent.ncount % 2 == 1) {
            this.write(Emitter.QUESTION_MARK_SPACE, 2);
            parent.status = LevelStatus.mapx;
        }
        this.emitTag(tag, "tag:yaml.org,2002:map");
        if (style == MapStyle.Inline || parent.status == LevelStatus.imap || parent.status == LevelStatus.iseq) {
            this.write(Emitter.CURLY_OPEN, 1);
            lvl.status = LevelStatus.imap;
        }
        else {
            lvl.status = LevelStatus.map;
        }
    }
    
    public void emitItem(final Object n) {
        final Level lvl = this.currentLevel();
        switch (lvl.status) {
            case seq: {
                final Level parent = this.parentLevel();
                if (parent.status == LevelStatus.mapx && lvl.ncount == 0) {
                    if (parent.ncount % 2 == 0 && lvl.anctag == 0) {
                        lvl.spaces = parent.spaces;
                    }
                }
                else if (lvl.anctag == 0 && parent.status == LevelStatus.seq && lvl.ncount == 0) {
                    final int spcs = lvl.spaces - parent.spaces - 2;
                    if (spcs >= 0) {
                        for (int i = 0; i < spcs; ++i) {
                            this.write(Emitter.SPACE, 1);
                        }
                        this.write(Emitter.DASH_SPACE, 2);
                        break;
                    }
                }
                this.emitIndent();
                this.write(Emitter.DASH_SPACE, 2);
                break;
            }
            case iseq: {
                if (lvl.ncount > 0) {
                    this.write(Emitter.COMMA_SPACE, 2);
                    break;
                }
                break;
            }
            case map: {
                final Level parent = this.parentLevel();
                if (lvl.anctag == 0 && parent.status == LevelStatus.seq && lvl.ncount == 0) {
                    final int spcs = lvl.spaces - parent.spaces - 2;
                    if (spcs >= 0) {
                        for (int i = 0; i < spcs; ++i) {
                            this.write(Emitter.SPACE, 1);
                        }
                        break;
                    }
                }
                if (lvl.ncount % 2 == 0) {
                    this.emitIndent();
                    break;
                }
                this.write(Emitter.COLON_SPACE, 2);
                break;
            }
            case mapx: {
                if (lvl.ncount % 2 == 0) {
                    this.emitIndent();
                    lvl.status = LevelStatus.map;
                    break;
                }
                if (lvl.spaces > 0) {
                    final byte[] spcs2 = new byte[lvl.spaces];
                    Arrays.fill(spcs2, (byte)32);
                    this.write(Pointer.create(spcs2, 0), lvl.spaces);
                }
                this.write(Emitter.COLON_SPACE, 2);
                break;
            }
            case imap: {
                if (lvl.ncount <= 0) {
                    break;
                }
                if (lvl.ncount % 2 == 0) {
                    this.write(Emitter.COMMA_SPACE, 2);
                    break;
                }
                this.write(Emitter.COLON_SPACE, 2);
                break;
            }
        }
        final Level level = lvl;
        ++level.ncount;
        this.emit(n);
    }
    
    public void emitEnd() {
        final Level lvl = this.currentLevel();
        final Level parent = this.parentLevel();
        switch (lvl.status) {
            case seq: {
                if (lvl.ncount == 0) {
                    this.write(Emitter.EMPTY_ARRAY, 3);
                    break;
                }
                if (parent.status == LevelStatus.mapx) {
                    this.write(Emitter.NEWLINE, 1);
                    break;
                }
                break;
            }
            case iseq: {
                this.write(Emitter.SQUARE_CLOSE, 1);
                if (parent.status == LevelStatus.mapx) {
                    this.write(Emitter.NEWLINE, 1);
                    break;
                }
                break;
            }
            case map: {
                if (lvl.ncount == 0) {
                    this.write(Emitter.EMPTY_HASH, 3);
                    break;
                }
                if (lvl.ncount % 2 == 1) {
                    this.write(Emitter.COLON, 1);
                    break;
                }
                if (parent.status == LevelStatus.mapx) {
                    this.write(Emitter.NEWLINE, 1);
                    break;
                }
                break;
            }
            case imap: {
                this.write(Emitter.CURLY_CLOSE, 1);
                if (parent.status == LevelStatus.mapx) {
                    this.write(Emitter.NEWLINE, 1);
                    break;
                }
                break;
            }
        }
    }
    
    public long markNode(final Object n) {
        long oid = 0L;
        if (this.markers == null) {
            this.markers = new IdentityHashMap<Object, Long>();
        }
        if (!this.markers.containsKey(n)) {
            oid = this.markers.size() + 1;
            this.markers.put(n, oid);
        }
        else {
            oid = this.markers.get(n);
            if (this.anchors == null) {
                this.anchors = new HashMap<Long, String>();
            }
            if (!this.anchors.containsKey(oid)) {
                int idx = 0;
                final String anc = (this.anchor_format == null) ? "id%03d" : this.anchor_format;
                idx = this.anchors.size() + 1;
                final String anchor_name = String.format(anc, idx);
                this.anchors.put(oid, anchor_name);
            }
        }
        return oid;
    }
    
    static {
        NEWLINE = Pointer.create("\n");
        TWO_NEWLINES = Pointer.create("\n\n");
        SPACE = Pointer.create(" ");
        SLASH = Pointer.create("/");
        THREE_DASHES = Pointer.create("--- ");
        QUESTION_MARK_SPACE = Pointer.create("? ");
        BANG = Pointer.create("!");
        BANG_SPACE = Pointer.create("! ");
        TWO_BANGS = Pointer.create("!!");
        BACKSLASH = Pointer.create("\\");
        ZERO = Pointer.create("0");
        X = Pointer.create("x");
        SINGLE_QUOTE = Pointer.create("'");
        DOUBLE_QUOTE = Pointer.create("\"");
        PIPE = Pointer.create("|");
        PLUS = Pointer.create("+");
        MINUS = Pointer.create("-");
        GT = Pointer.create(">");
        SQUARE_OPEN = Pointer.create("[");
        SQUARE_CLOSE = Pointer.create("]");
        CURLY_OPEN = Pointer.create("{");
        CURLY_CLOSE = Pointer.create("}");
        DASH_SPACE = Pointer.create("- ");
        COMMA_SPACE = Pointer.create(", ");
        COLON_SPACE = Pointer.create(": ");
        EMPTY_ARRAY = Pointer.create("[]\n");
        EMPTY_HASH = Pointer.create("{}\n");
        COLON = Pointer.create(":");
        EMPTY = Pointer.create(new byte[0], 0);
        TILDE = Pointer.create("~");
        hex_table = Pointer.create("0123456789ABCDEF");
        SLASH_QUOTE = Pointer.create("\\\"");
        SLASH_SLASH = Pointer.create("\\\\");
        SLASH_ZERO = Pointer.create("\\0");
        SLASH_A = Pointer.create("\\a");
        SLASH_B = Pointer.create("\\b");
        SLASH_F = Pointer.create("\\f");
        SLASH_R = Pointer.create("\\r");
        SLASH_T = Pointer.create("\\t");
        SLASH_V = Pointer.create("\\v");
        SLASH_E = Pointer.create("\\e");
        SLASH_N = Pointer.create("\\n");
    }
    
    public static class Node
    {
        public int pos;
        public int indent;
        public boolean is_shortcut;
    }
}
