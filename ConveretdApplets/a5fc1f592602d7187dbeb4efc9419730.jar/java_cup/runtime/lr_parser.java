// 
// Decompiled by Procyon v0.5.30
// 

package java_cup.runtime;

import java.util.Stack;

public abstract class lr_parser
{
    protected static final int _error_sync_size = 3;
    protected boolean _done_parsing;
    protected int tos;
    protected Symbol cur_token;
    protected Stack stack;
    protected short[][] production_tab;
    protected short[][] action_tab;
    protected short[][] reduce_tab;
    private Scanner _scanner;
    protected Symbol[] lookahead;
    protected int lookahead_pos;
    
    public lr_parser() {
        this._done_parsing = false;
        this.stack = new Stack();
    }
    
    public lr_parser(final Scanner scanner) {
        this();
        this.setScanner(scanner);
    }
    
    public abstract int EOF_sym();
    
    public abstract short[][] action_table();
    
    protected boolean advance_lookahead() {
        ++this.lookahead_pos;
        return this.lookahead_pos < this.error_sync_size();
    }
    
    protected Symbol cur_err_token() {
        return this.lookahead[this.lookahead_pos];
    }
    
    public void debug_message(final String s) {
        System.err.println(s);
    }
    
    public Symbol debug_parse() throws Exception {
        Symbol do_action = null;
        this.production_tab = this.production_table();
        this.action_tab = this.action_table();
        this.reduce_tab = this.reduce_table();
        this.debug_message("# Initializing parser");
        this.init_actions();
        this.user_init();
        this.cur_token = this.scan();
        this.debug_message("# Current Symbol is #" + this.cur_token.sym);
        this.stack.removeAllElements();
        this.stack.push(new Symbol(0, this.start_state()));
        this.tos = 0;
        this._done_parsing = false;
        while (!this._done_parsing) {
            if (this.cur_token.used_by_parser) {
                throw new Error("Symbol recycling detected (fix your scanner).");
            }
            final short get_action = this.get_action(this.stack.peek().parse_state, this.cur_token.sym);
            if (get_action > 0) {
                this.cur_token.parse_state = get_action - 1;
                this.cur_token.used_by_parser = true;
                this.debug_shift(this.cur_token);
                this.stack.push(this.cur_token);
                ++this.tos;
                this.cur_token = this.scan();
                this.debug_message("# Current token is " + this.cur_token);
            }
            else if (get_action < 0) {
                do_action = this.do_action(-get_action - 1, this, this.stack, this.tos);
                final short n = this.production_tab[-get_action - 1][0];
                final short n2 = this.production_tab[-get_action - 1][1];
                this.debug_reduce(-get_action - 1, n, n2);
                for (short n3 = 0; n3 < n2; ++n3) {
                    this.stack.pop();
                    --this.tos;
                }
                final short get_reduce = this.get_reduce(this.stack.peek().parse_state, n);
                this.debug_message("# Reduce rule: top state " + this.stack.peek().parse_state + ", lhs sym " + n + " -> state " + get_reduce);
                do_action.parse_state = get_reduce;
                do_action.used_by_parser = true;
                this.stack.push(do_action);
                ++this.tos;
                this.debug_message("# Goto state #" + get_reduce);
            }
            else {
                if (get_action != 0) {
                    continue;
                }
                this.syntax_error(this.cur_token);
                if (!this.error_recovery(true)) {
                    this.unrecovered_syntax_error(this.cur_token);
                    this.done_parsing();
                }
                else {
                    do_action = this.stack.peek();
                }
            }
        }
        return do_action;
    }
    
    public void debug_reduce(final int n, final int n2, final int n3) {
        this.debug_message("# Reduce with prod #" + n + " [NT=" + n2 + ", " + "SZ=" + n3 + "]");
    }
    
    public void debug_shift(final Symbol symbol) {
        this.debug_message("# Shift under term #" + symbol.sym + " to state #" + symbol.parse_state);
    }
    
    public void debug_stack() {
        StringBuffer sb = new StringBuffer("## STACK:");
        for (int i = 0; i < this.stack.size(); ++i) {
            final Symbol symbol = (Symbol)this.stack.elementAt(i);
            sb.append(" <state " + symbol.parse_state + ", sym " + symbol.sym + ">");
            if (i % 3 == 2 || i == this.stack.size() - 1) {
                this.debug_message(sb.toString());
                sb = new StringBuffer("         ");
            }
        }
    }
    
    public abstract Symbol do_action(final int p0, final lr_parser p1, final Stack p2, final int p3) throws Exception;
    
    public void done_parsing() {
        this._done_parsing = true;
    }
    
    public void dump_stack() {
        if (this.stack == null) {
            this.debug_message("# Stack dump requested, but stack is null");
            return;
        }
        this.debug_message("============ Parse Stack Dump ============");
        for (int i = 0; i < this.stack.size(); ++i) {
            this.debug_message("Symbol: " + ((Symbol)this.stack.elementAt(i)).sym + " State: " + ((Symbol)this.stack.elementAt(i)).parse_state);
        }
        this.debug_message("==========================================");
    }
    
    protected boolean error_recovery(final boolean b) throws Exception {
        if (b) {
            this.debug_message("# Attempting error recovery");
        }
        if (!this.find_recovery_config(b)) {
            if (b) {
                this.debug_message("# Error recovery fails");
            }
            return false;
        }
        this.read_lookahead();
        while (true) {
            if (b) {
                this.debug_message("# Trying to parse ahead");
            }
            if (this.try_parse_ahead(b)) {
                if (b) {
                    this.debug_message("# Parse-ahead ok, going back to normal parse");
                }
                this.parse_lookahead(b);
                return true;
            }
            if (this.lookahead[0].sym == this.EOF_sym()) {
                if (b) {
                    this.debug_message("# Error recovery fails at EOF");
                }
                return false;
            }
            if (b) {
                this.debug_message("# Consuming Symbol #" + this.lookahead[0].sym);
            }
            this.restart_lookahead();
        }
    }
    
    public abstract int error_sym();
    
    protected int error_sync_size() {
        return 3;
    }
    
    protected boolean find_recovery_config(final boolean b) {
        if (b) {
            this.debug_message("# Finding recovery state on stack");
        }
        final int right = this.stack.peek().right;
        int n = this.stack.peek().left;
        while (!this.shift_under_error()) {
            if (b) {
                this.debug_message("# Pop stack by one, state was # " + this.stack.peek().parse_state);
            }
            n = this.stack.pop().left;
            --this.tos;
            if (this.stack.empty()) {
                if (b) {
                    this.debug_message("# No recovery state found on stack");
                }
                return false;
            }
        }
        final short get_action = this.get_action(this.stack.peek().parse_state, this.error_sym());
        if (b) {
            this.debug_message("# Recover state found (#" + this.stack.peek().parse_state + ")");
            this.debug_message("# Shifting on error to state #" + (get_action - 1));
        }
        final Symbol symbol = new Symbol(this.error_sym(), n, right);
        symbol.parse_state = get_action - 1;
        symbol.used_by_parser = true;
        this.stack.push(symbol);
        ++this.tos;
        return true;
    }
    
    public Scanner getScanner() {
        return this._scanner;
    }
    
    protected final short get_action(final int n, final int n2) {
        final short[] array = this.action_tab[n];
        if (array.length < 20) {
            for (int i = 0; i < array.length; ++i) {
                final short n3 = array[i++];
                if (n3 == n2 || n3 == -1) {
                    return array[i];
                }
            }
            return 0;
        }
        int j = 0;
        int n4 = (array.length - 1) / 2 - 1;
        while (j <= n4) {
            final int n5 = (j + n4) / 2;
            if (n2 == array[n5 * 2]) {
                return array[n5 * 2 + 1];
            }
            if (n2 > array[n5 * 2]) {
                j = n5 + 1;
            }
            else {
                n4 = n5 - 1;
            }
        }
        return array[array.length - 1];
    }
    
    protected final short get_reduce(final int n, final int n2) {
        final short[] array = this.reduce_tab[n];
        if (array == null) {
            return -1;
        }
        for (int i = 0; i < array.length; ++i) {
            final short n3 = array[i++];
            if (n3 == n2 || n3 == -1) {
                return array[i];
            }
        }
        return -1;
    }
    
    protected abstract void init_actions() throws Exception;
    
    public Symbol parse() throws Exception {
        Symbol do_action = null;
        this.production_tab = this.production_table();
        this.action_tab = this.action_table();
        this.reduce_tab = this.reduce_table();
        this.init_actions();
        this.user_init();
        this.cur_token = this.scan();
        this.stack.removeAllElements();
        this.stack.push(new Symbol(0, this.start_state()));
        this.tos = 0;
        this._done_parsing = false;
        while (!this._done_parsing) {
            if (this.cur_token.used_by_parser) {
                throw new Error("Symbol recycling detected (fix your scanner).");
            }
            final short get_action = this.get_action(this.stack.peek().parse_state, this.cur_token.sym);
            if (get_action > 0) {
                this.cur_token.parse_state = get_action - 1;
                this.cur_token.used_by_parser = true;
                this.stack.push(this.cur_token);
                ++this.tos;
                this.cur_token = this.scan();
            }
            else if (get_action < 0) {
                do_action = this.do_action(-get_action - 1, this, this.stack, this.tos);
                final short n = this.production_tab[-get_action - 1][0];
                for (short n2 = this.production_tab[-get_action - 1][1], n3 = 0; n3 < n2; ++n3) {
                    this.stack.pop();
                    --this.tos;
                }
                do_action.parse_state = this.get_reduce(this.stack.peek().parse_state, n);
                do_action.used_by_parser = true;
                this.stack.push(do_action);
                ++this.tos;
            }
            else {
                if (get_action != 0) {
                    continue;
                }
                this.syntax_error(this.cur_token);
                if (!this.error_recovery(false)) {
                    this.unrecovered_syntax_error(this.cur_token);
                    this.done_parsing();
                }
                else {
                    do_action = this.stack.peek();
                }
            }
        }
        return do_action;
    }
    
    protected void parse_lookahead(final boolean b) throws Exception {
        Symbol do_action = null;
        this.lookahead_pos = 0;
        if (b) {
            this.debug_message("# Reparsing saved input with actions");
            this.debug_message("# Current Symbol is #" + this.cur_err_token().sym);
            this.debug_message("# Current state is #" + this.stack.peek().parse_state);
        }
        while (!this._done_parsing) {
            final short get_action = this.get_action(this.stack.peek().parse_state, this.cur_err_token().sym);
            if (get_action > 0) {
                this.cur_err_token().parse_state = get_action - 1;
                this.cur_err_token().used_by_parser = true;
                if (b) {
                    this.debug_shift(this.cur_err_token());
                }
                this.stack.push(this.cur_err_token());
                ++this.tos;
                if (!this.advance_lookahead()) {
                    if (b) {
                        this.debug_message("# Completed reparse");
                    }
                    return;
                }
                if (!b) {
                    continue;
                }
                this.debug_message("# Current Symbol is #" + this.cur_err_token().sym);
            }
            else if (get_action < 0) {
                do_action = this.do_action(-get_action - 1, this, this.stack, this.tos);
                final short n = this.production_tab[-get_action - 1][0];
                final short n2 = this.production_tab[-get_action - 1][1];
                if (b) {
                    this.debug_reduce(-get_action - 1, n, n2);
                }
                for (short n3 = 0; n3 < n2; ++n3) {
                    this.stack.pop();
                    --this.tos;
                }
                final short get_reduce = this.get_reduce(this.stack.peek().parse_state, n);
                do_action.parse_state = get_reduce;
                do_action.used_by_parser = true;
                this.stack.push(do_action);
                ++this.tos;
                if (!b) {
                    continue;
                }
                this.debug_message("# Goto state #" + get_reduce);
            }
            else {
                if (get_action == 0) {
                    this.report_fatal_error("Syntax error", do_action);
                    return;
                }
                continue;
            }
        }
    }
    
    public abstract short[][] production_table();
    
    protected void read_lookahead() throws Exception {
        this.lookahead = new Symbol[this.error_sync_size()];
        for (int i = 0; i < this.error_sync_size(); ++i) {
            this.lookahead[i] = this.cur_token;
            this.cur_token = this.scan();
        }
        this.lookahead_pos = 0;
    }
    
    public abstract short[][] reduce_table();
    
    public void report_error(final String s, final Object o) {
        System.err.print(s);
        if (o instanceof Symbol) {
            if (((Symbol)o).left != -1) {
                System.err.println(" at character " + ((Symbol)o).left + " of input");
            }
            else {
                System.err.println("");
            }
        }
        else {
            System.err.println("");
        }
    }
    
    public void report_fatal_error(final String s, final Object o) throws Exception {
        this.done_parsing();
        this.report_error(s, o);
        throw new Exception("Can't recover from previous error(s)");
    }
    
    protected void restart_lookahead() throws Exception {
        for (int i = 1; i < this.error_sync_size(); ++i) {
            this.lookahead[i - 1] = this.lookahead[i];
        }
        this.lookahead[this.error_sync_size() - 1] = this.cur_token;
        this.cur_token = this.scan();
        this.lookahead_pos = 0;
    }
    
    public Symbol scan() throws Exception {
        final Symbol next_token = this.getScanner().next_token();
        return (next_token != null) ? next_token : new Symbol(this.EOF_sym());
    }
    
    public void setScanner(final Scanner scanner) {
        this._scanner = scanner;
    }
    
    protected boolean shift_under_error() {
        return this.get_action(this.stack.peek().parse_state, this.error_sym()) > 0;
    }
    
    public abstract int start_production();
    
    public abstract int start_state();
    
    public void syntax_error(final Symbol symbol) {
        this.report_error("Syntax error", symbol);
    }
    
    protected boolean try_parse_ahead(final boolean b) throws Exception {
        final virtual_parse_stack virtual_parse_stack = new virtual_parse_stack(this.stack);
        while (true) {
            final short get_action = this.get_action(virtual_parse_stack.top(), this.cur_err_token().sym);
            if (get_action == 0) {
                return false;
            }
            if (get_action > 0) {
                virtual_parse_stack.push(get_action - 1);
                if (b) {
                    this.debug_message("# Parse-ahead shifts Symbol #" + this.cur_err_token().sym + " into state #" + (get_action - 1));
                }
                if (!this.advance_lookahead()) {
                    return true;
                }
                continue;
            }
            else {
                if (-get_action - 1 == this.start_production()) {
                    if (b) {
                        this.debug_message("# Parse-ahead accepts");
                    }
                    return true;
                }
                final short n = this.production_tab[-get_action - 1][0];
                final short n2 = this.production_tab[-get_action - 1][1];
                for (short n3 = 0; n3 < n2; ++n3) {
                    virtual_parse_stack.pop();
                }
                if (b) {
                    this.debug_message("# Parse-ahead reduces: handle size = " + n2 + " lhs = #" + n + " from state #" + virtual_parse_stack.top());
                }
                virtual_parse_stack.push(this.get_reduce(virtual_parse_stack.top(), n));
                if (!b) {
                    continue;
                }
                this.debug_message("# Goto state #" + virtual_parse_stack.top());
            }
        }
    }
    
    protected static short[][] unpackFromStrings(final String[] array) {
        final StringBuffer sb = new StringBuffer(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(array[i]);
        }
        int n = 0;
        final char c = (char)(sb.charAt(n) << 16 | sb.charAt(n + 1));
        n += 2;
        final short[][] array2 = new short[c][];
        for (char c2 = '\0'; c2 < c; ++c2) {
            final char c3 = (char)(sb.charAt(n) << 16 | sb.charAt(n + 1));
            n += 2;
            array2[c2] = new short[c3];
            for (char c4 = '\0'; c4 < c3; ++c4) {
                array2[c2][c4] = (short)(sb.charAt(n++) - '\u0002');
            }
        }
        return array2;
    }
    
    public void unrecovered_syntax_error(final Symbol symbol) throws Exception {
        this.report_fatal_error("Couldn't repair and continue parse", symbol);
    }
    
    public void user_init() throws Exception {
    }
}
