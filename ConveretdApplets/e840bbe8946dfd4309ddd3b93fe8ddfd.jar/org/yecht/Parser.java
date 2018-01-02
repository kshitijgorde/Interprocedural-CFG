// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Parser
{
    public Object root;
    public Object root_on_error;
    boolean implicit_typing;
    boolean taguri_expansion;
    NodeHandler handler;
    ErrorHandler error_handler;
    BadAnchorHandler bad_anchor_handler;
    ParserInput input_type;
    IOType io_type;
    public int bufsize;
    public Pointer buffer;
    public int linectptr;
    public int lineptr;
    public int token;
    public int toktmp;
    public int cursor;
    public int marker;
    public int limit;
    public int linect;
    int last_token;
    int force_token;
    public boolean eof;
    JechtIO io;
    Map<String, Node> anchors;
    Map<String, Node> bad_anchors;
    Map<String, Node> prepared_anchors;
    Level[] levels;
    int lvl_idx;
    int lvl_capa;
    public Object bonus;
    
    private Parser() {
        this.bad_anchor_handler = new BadAnchorHandler.Default();
        this.cursor = -1;
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
        }
        this.levels[0].status = LevelStatus.header;
    }
    
    public void popLevel() {
        if (this.lvl_idx <= 1) {
            return;
        }
        --this.lvl_idx;
    }
    
    public void resetCursor() {
        if (this.buffer == null) {
            this.buffer = Pointer.create(new byte[this.bufsize], 0);
        }
        this.buffer.buffer[this.buffer.start] = 0;
        this.cursor = -1;
        this.lineptr = -1;
        this.linectptr = -1;
        this.token = -1;
        this.toktmp = -1;
        this.marker = -1;
        this.limit = -1;
        this.root = null;
        this.root_on_error = null;
        this.linect = 0;
        this.eof = false;
        this.last_token = 0;
        this.force_token = 0;
    }
    
    public void setRootOnError(final Object roer) {
        this.root_on_error = roer;
    }
    
    public static Parser newParser() {
        final Parser p = new Parser();
        p.lvl_capa = 8;
        p.levels = new Level[p.lvl_capa];
        p.input_type = ParserInput.YAML_UTF8;
        p.io_type = IOType.Str;
        p.io = null;
        p.anchors = null;
        p.bad_anchors = null;
        p.prepared_anchors = null;
        p.implicit_typing = true;
        p.taguri_expansion = false;
        p.bufsize = 4096;
        p.buffer = null;
        p.lvl_idx = 0;
        p.resetLevels();
        return p;
    }
    
    public void handler(final NodeHandler hdlr) {
        this.handler = hdlr;
    }
    
    public void implicitTyping(final boolean flag) {
        this.implicit_typing = flag;
    }
    
    public void taguriExpansion(final boolean flag) {
        this.taguri_expansion = flag;
    }
    
    public void errorHandler(final ErrorHandler hdlr) {
        this.error_handler = hdlr;
    }
    
    public void badAnchorHandler(final BadAnchorHandler hdlr) {
        this.bad_anchor_handler = hdlr;
    }
    
    public void setInputType(final ParserInput input_type) {
        this.input_type = input_type;
    }
    
    public void file(final InputStream fp, IoFileRead read) {
        this.resetCursor();
        this.io_type = IOType.File;
        if (read == null) {
            read = new IoFileRead.Default();
        }
        this.io = new JechtIO.File(fp, read);
    }
    
    public void str(final Pointer ptr, final int len, final IoStrRead read) {
        this.resetCursor();
        this.io_type = IOType.Str;
        final JechtIO.Str ss = new JechtIO.Str();
        this.io = ss;
        ss.beg = ptr.start;
        ss.ptr = ptr;
        ss.end = ptr.start + len;
        if (read == null) {
            ss.read = new IoStrRead.Default();
        }
        else {
            ss.read = read;
        }
    }
    
    public void str(final Pointer ptr, final IoStrRead read) {
        this.str(ptr, ptr.buffer.length - ptr.start, read);
    }
    
    public Level currentLevel() {
        return this.levels[this.lvl_idx - 1];
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
        ++this.lvl_idx;
    }
    
    public int moveTokens() {
        if (this.token == -1) {
            return 0;
        }
        final int skip = this.limit - this.token;
        if (skip < 0) {
            return 0;
        }
        final int count;
        if ((count = this.token - this.buffer.start) != 0) {
            System.arraycopy(this.buffer.buffer, this.token, this.buffer.buffer, this.buffer.start, skip);
            this.token = this.buffer.start;
            this.marker -= count;
            this.cursor -= count;
            this.toktmp -= count;
            this.limit -= count;
            this.lineptr -= count;
            this.linectptr -= count;
        }
        return skip;
    }
    
    public void checkLimit(final int len) {
        if (this.cursor == -1) {
            this.cursor = this.buffer.start;
            this.lineptr = this.buffer.start;
            this.linectptr = this.buffer.start;
            this.marker = this.buffer.start;
        }
        this.limit = this.buffer.start + len;
    }
    
    public int read() throws IOException {
        int len = 0;
        int skip = 0;
        switch (this.io_type) {
            case Str: {
                skip = this.moveTokens();
                len = ((JechtIO.Str)this.io).read.read(this.buffer, (JechtIO.Str)this.io, 4095, skip);
                break;
            }
            case File: {
                skip = this.moveTokens();
                len = ((JechtIO.File)this.io).read.read(this.buffer, (JechtIO.File)this.io, 4095, skip);
                break;
            }
        }
        this.checkLimit(len);
        return len;
    }
    
    public int read(final int max_size) throws IOException {
        int len = 0;
        int skip = 0;
        switch (this.io_type) {
            case Str: {
                skip = this.moveTokens();
                len = ((JechtIO.Str)this.io).read.read(this.buffer, (JechtIO.Str)this.io, max_size, skip);
                break;
            }
            case File: {
                skip = this.moveTokens();
                len = ((JechtIO.File)this.io).read.read(this.buffer, (JechtIO.File)this.io, max_size, skip);
                break;
            }
        }
        this.checkLimit(len);
        return len;
    }
    
    public Object parse() {
        this.resetLevels();
        this.yechtparse();
        return this.root;
    }
    
    private void yechtparse() {
        try {
            new DefaultYAMLParser(this).yyparse(TokenScanner.createScanner(this));
        }
        catch (IOException e) {
            this.root = this.root_on_error;
        }
    }
    
    public Object addNode(final Node n) {
        if (n.id == null) {
            n.id = this.handler.handle(this, n);
        }
        return n.id;
    }
    
    public Node addAnchor(final String a, final Node n) {
        n.anchor = a;
        if (this.bad_anchors != null && this.bad_anchors.containsKey(a) && n.kind != KindTag.Str) {
            final Node bad = this.bad_anchors.get(a);
            n.id = bad.id;
            this.handler.handle(this, n);
        }
        if (this.anchors == null) {
            this.anchors = new HashMap<String, Node>();
        }
        this.anchors.put(a, n);
        return n;
    }
    
    public void removeAnchor(final String a) {
        if (this.anchors == null) {
            this.anchors = new HashMap<String, Node>();
        }
        this.anchors.put(a, null);
    }
    
    public Node getAnchor(final String a) {
        Node n = null;
        if (this.anchors != null && this.anchors.containsKey(a)) {
            n = this.anchors.get(a);
            if (n != null) {
                return n;
            }
            if (this.bad_anchors == null) {
                this.bad_anchors = new HashMap<String, Node>();
            }
            if (!this.bad_anchors.containsKey(a)) {
                n = this.bad_anchor_handler.handle(this, a);
                this.bad_anchors.put(a, n);
            }
            else {
                n = this.bad_anchors.get(a);
            }
        }
        if (n == null) {
            n = this.bad_anchor_handler.handle(this, a);
        }
        if (n.anchor == null) {
            n.anchor = a;
        }
        return n;
    }
    
    public static void addTransfer(final String uri, final Node n, final boolean taguri) {
        if (!taguri) {
            n.type_id = uri;
            return;
        }
        n.type_id = ImplicitScanner.typeIdToUri(uri);
    }
    
    public static String xprivate(final String type_id) {
        return "x-private:" + type_id;
    }
    
    public static String taguri(final String domain, final String type_id) {
        return "tag:" + domain + ":" + type_id;
    }
    
    public static boolean tryImplicit(final Node n) {
        return true;
    }
}
