// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.concurrent.locks.ReentrantLock;
import org.jruby.runtime.BlockCallback;
import java.io.IOException;
import org.jruby.runtime.marshal.UnmarshalStream;
import org.jcodings.Encoding;
import org.jruby.util.StringSupport;
import org.jruby.runtime.BlockBody;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.runtime.Block;
import org.jruby.runtime.Binding;
import org.jruby.runtime.ContextAwareBlockBody;
import org.jruby.runtime.Arity;
import org.jruby.parser.StaticScope;
import org.jruby.parser.LocalStaticScope;
import org.jruby.common.IRubyWarnings;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "Symbol" })
public class RubySymbol extends RubyObject
{
    private final String symbol;
    private final int id;
    private final ByteList symbolBytes;
    
    private RubySymbol(final Ruby runtime, final String internedSymbol, final ByteList symbolBytes) {
        super(runtime, runtime.getSymbol(), false, false);
        if (!runtime.is1_9()) {
            for (int length = symbolBytes.getBegin() + symbolBytes.getRealSize(), i = symbolBytes.getBegin(); i < length; ++i) {
                if (symbolBytes.getUnsafeBytes()[i] == 0) {
                    throw runtime.newSyntaxError("symbol cannot contain '\\0'");
                }
            }
        }
        this.symbol = internedSymbol;
        this.symbolBytes = symbolBytes;
        this.id = runtime.allocSymbolId();
    }
    
    private RubySymbol(final Ruby runtime, final String internedSymbol) {
        this(runtime, internedSymbol, ByteList.create(internedSymbol));
    }
    
    public static RubyClass createSymbolClass(final Ruby runtime) {
        final RubyClass symbolClass = runtime.defineClass("Symbol", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setSymbol(symbolClass);
        final RubyClass symbolMetaClass = symbolClass.getMetaClass();
        symbolClass.index = 8;
        symbolClass.setReifiedClass(RubySymbol.class);
        symbolClass.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubySymbol;
            }
        };
        symbolClass.defineAnnotatedMethods(RubySymbol.class);
        symbolMetaClass.undefineMethod("new");
        return symbolClass;
    }
    
    public int getNativeTypeIndex() {
        return 8;
    }
    
    public String asJavaString() {
        return this.symbol;
    }
    
    public String toString() {
        return this.symbol;
    }
    
    final ByteList getBytes() {
        return this.symbolBytes;
    }
    
    public final boolean eql(final IRubyObject other) {
        return other == this;
    }
    
    public boolean isImmediate() {
        return true;
    }
    
    public RubyClass getSingletonClass() {
        throw this.getRuntime().newTypeError("can't define singleton");
    }
    
    public static RubySymbol getSymbolLong(final Ruby runtime, final long id) {
        return runtime.getSymbolTable().lookup(id);
    }
    
    public static RubySymbol newSymbol(final Ruby runtime, final String name) {
        return runtime.getSymbolTable().getSymbol(name);
    }
    
    @Deprecated
    public RubyFixnum to_i() {
        return this.to_i(this.getRuntime());
    }
    
    @JRubyMethod(name = { "to_i" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum to_i(final ThreadContext context) {
        return this.to_i(context.getRuntime());
    }
    
    private final RubyFixnum to_i(final Ruby runtime) {
        return runtime.newFixnum(this.id);
    }
    
    @Deprecated
    public RubyFixnum to_int() {
        return this.to_int(this.getRuntime());
    }
    
    @JRubyMethod(name = { "to_int" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum to_int(final ThreadContext context) {
        return this.to_int(context.getRuntime());
    }
    
    private final RubyFixnum to_int(final Ruby runtime) {
        if (runtime.isVerbose()) {
            runtime.getWarnings().warn(IRubyWarnings.ID.SYMBOL_AS_INTEGER, "treating Symbol as an integer", new Object[0]);
        }
        return this.to_i(runtime);
    }
    
    @Deprecated
    public IRubyObject inspect() {
        return this.inspect(this.getRuntime());
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject inspect(final ThreadContext context) {
        return this.inspect(context.getRuntime());
    }
    
    private final IRubyObject inspect(final Ruby runtime) {
        ByteList bytes;
        if (isSymbolName(this.symbol)) {
            bytes = this.symbolBytes;
        }
        else {
            bytes = ((RubyString)RubyString.newString(runtime, this.symbolBytes).dump()).getByteList();
        }
        final ByteList result = new ByteList(bytes.getRealSize() + 1);
        result.append((byte)58);
        result.append(bytes);
        return RubyString.newString(runtime, result);
    }
    
    @Deprecated
    public IRubyObject inspect19() {
        return this.inspect19(this.getRuntime());
    }
    
    @JRubyMethod(name = { "inspect" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject inspect19(final ThreadContext context) {
        return this.inspect19(context.getRuntime());
    }
    
    private final IRubyObject inspect19(final Ruby runtime) {
        final ByteList result = new ByteList(this.symbolBytes.getRealSize() + 1);
        result.setEncoding(this.symbolBytes.getEncoding());
        result.append((byte)58);
        result.append(this.symbolBytes);
        RubyString str = RubyString.newString(runtime, result);
        if (this.isPrintable() && isSymbolName19(this.symbol)) {
            return str;
        }
        str = (RubyString)str.inspect19();
        final ByteList bytes = str.getByteList();
        bytes.set(0, 58);
        bytes.set(1, 34);
        return str;
    }
    
    public IRubyObject to_s() {
        return this.to_s(this.getRuntime());
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        return this.to_s(context.getRuntime());
    }
    
    private final IRubyObject to_s(final Ruby runtime) {
        return RubyString.newStringShared(runtime, this.symbolBytes);
    }
    
    public IRubyObject id2name() {
        return this.to_s(this.getRuntime());
    }
    
    @JRubyMethod(name = { "id2name" })
    public IRubyObject id2name(final ThreadContext context) {
        return this.to_s(context);
    }
    
    @JRubyMethod(name = { "===" }, required = 1)
    public IRubyObject op_eqq(final ThreadContext context, final IRubyObject other) {
        return super.op_equal(context, other);
    }
    
    @Deprecated
    public RubyFixnum hash() {
        return this.getRuntime().newFixnum(this.hashCode());
    }
    
    @JRubyMethod(name = { "hash" })
    public RubyFixnum hash(final ThreadContext context) {
        return context.getRuntime().newFixnum(this.hashCode());
    }
    
    public int hashCode() {
        return this.id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public boolean equals(final Object other) {
        return other == this;
    }
    
    @JRubyMethod(name = { "to_sym" })
    public IRubyObject to_sym() {
        return this;
    }
    
    @JRubyMethod(name = { "intern" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_sym19() {
        return this;
    }
    
    public IRubyObject taint(final ThreadContext context) {
        return this;
    }
    
    private RubyString newShared(final Ruby runtime) {
        return RubyString.newStringShared(runtime, this.symbolBytes);
    }
    
    private RubyString rubyStringFromString(final Ruby runtime) {
        return RubyString.newString(runtime, this.symbol);
    }
    
    @JRubyMethod(name = { "succ", "next" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject succ(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return newSymbol(runtime, this.newShared(runtime).succ19(context).toString());
    }
    
    @JRubyMethod(name = { "<=>" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_cmp(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubySymbol) {
            return this.newShared(runtime).op_cmp19(context, ((RubySymbol)other).newShared(runtime));
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "casecmp" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject casecmp(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        if (other instanceof RubySymbol) {
            return this.newShared(runtime).casecmp19(context, ((RubySymbol)other).newShared(runtime));
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "=~", "match" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_match19(final ThreadContext context, final IRubyObject other) {
        final Ruby runtime = context.getRuntime();
        return this.newShared(runtime).op_match19(context, other);
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject arg) {
        final Ruby runtime = context.getRuntime();
        return this.newShared(runtime).op_aref19(context, arg);
    }
    
    @JRubyMethod(name = { "[]", "slice" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject arg1, final IRubyObject arg2) {
        final Ruby runtime = context.getRuntime();
        return this.newShared(runtime).op_aref19(context, arg1, arg2);
    }
    
    @JRubyMethod(name = { "length", "size" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject length() {
        return this.newShared(this.getRuntime()).length19();
    }
    
    @JRubyMethod(name = { "empty?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject empty_p(final ThreadContext context) {
        return this.newShared(context.getRuntime()).empty_p(context);
    }
    
    @JRubyMethod(name = { "upcase" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject upcase(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return newSymbol(runtime, this.rubyStringFromString(runtime).upcase19(context).toString());
    }
    
    @JRubyMethod(name = { "downcase" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject downcase(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return newSymbol(runtime, this.rubyStringFromString(runtime).downcase19(context).toString());
    }
    
    @JRubyMethod(name = { "capitalize" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject capitalize(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return newSymbol(runtime, this.rubyStringFromString(runtime).capitalize19(context).toString());
    }
    
    @JRubyMethod(name = { "swapcase" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject swapcase(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        return newSymbol(runtime, this.rubyStringFromString(runtime).swapcase19(context).toString());
    }
    
    @JRubyMethod(name = { "encoding" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject encoding(final ThreadContext context) {
        return context.getRuntime().getEncodingService().getEncoding(this.symbolBytes.getEncoding());
    }
    
    @JRubyMethod
    public IRubyObject to_proc(final ThreadContext context) {
        final StaticScope scope = new LocalStaticScope(null);
        final BlockBody body = new ContextAwareBlockBody(scope, Arity.OPTIONAL, 3) {
            public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type) {
                final RubyArray array = ArgsUtil.convertToRubyArray(context.getRuntime(), value, false);
                if (array.isEmpty()) {
                    throw context.getRuntime().newArgumentError("no receiver given");
                }
                final IRubyObject receiver = array.shift(context);
                return RuntimeHelpers.invoke(context, receiver, RubySymbol.this.symbol, array.toJavaArray());
            }
            
            public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type) {
                final RubyArray array = (RubyArray)((aValue && value instanceof RubyArray) ? value : ArgsUtil.convertToRubyArray(context.getRuntime(), value, false));
                if (array.isEmpty()) {
                    throw context.getRuntime().newArgumentError("no receiver given");
                }
                final IRubyObject receiver = array.shift(context);
                return RuntimeHelpers.invoke(context, receiver, RubySymbol.this.symbol, array.toJavaArray());
            }
            
            public Block cloneBlock(final Binding binding) {
                return new Block(this, binding);
            }
            
            public Arity arity() {
                return Arity.OPTIONAL;
            }
            
            public String getFile() {
                return RubySymbol.this.symbol;
            }
            
            public int getLine() {
                return -1;
            }
        };
        final Block block = new Block(body, context.currentBinding());
        return RubyProc.newProc(context.getRuntime(), block, Block.Type.PROC);
    }
    
    private static boolean isIdentStart(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }
    
    private static boolean isIdentChar(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || c == '_';
    }
    
    private static boolean isIdentifier(final String s) {
        if (s == null || s.length() <= 0) {
            return false;
        }
        if (!isIdentStart(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (!isIdentChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isSpecialGlobalName(final String s) {
        if (s == null || s.length() <= 0) {
            return false;
        }
        final int length = s.length();
        switch (s.charAt(0)) {
            case '!':
            case '\"':
            case '$':
            case '&':
            case '\'':
            case '*':
            case '+':
            case ',':
            case '.':
            case '/':
            case '0':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case '\\':
            case '`':
            case '~': {
                return length == 1;
            }
            case '-': {
                return length == 1 || (length == 2 && isIdentChar(s.charAt(1)));
            }
            default: {
                for (int i = 0; i < length; ++i) {
                    if (!Character.isDigit(s.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
    
    private boolean isPrintable() {
        final Ruby runtime = this.getRuntime();
        int p = this.symbolBytes.getBegin();
        final int end = p + this.symbolBytes.getRealSize();
        final byte[] bytes = this.symbolBytes.getUnsafeBytes();
        int c;
        for (Encoding enc = this.symbolBytes.getEncoding(); p < end; p += StringSupport.codeLength(runtime, enc, c)) {
            c = StringSupport.codePoint(runtime, enc, bytes, p, end);
            if (!enc.isPrint(c)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isSymbolName19(final String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        final int length = s.length();
        final char c = s.charAt(0);
        return isSymbolNameCommon(s, c, length) || (c == '!' && (length == 1 || (length == 2 && (s.charAt(1) == '~' || s.charAt(1) == '=')))) || isSymbolLocal(s, c, length);
    }
    
    private static boolean isSymbolName(final String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        final int length = s.length();
        final char c = s.charAt(0);
        return isSymbolNameCommon(s, c, length) || isSymbolLocal(s, c, length);
    }
    
    private static boolean isSymbolNameCommon(final String s, final char c, final int length) {
        switch (c) {
            case '$': {
                return (length > 1 && isSpecialGlobalName(s.substring(1))) || isIdentifier(s.substring(1));
            }
            case '@': {
                int offset = 1;
                if (length >= 2 && s.charAt(1) == '@') {
                    ++offset;
                }
                return isIdentifier(s.substring(offset));
            }
            case '<': {
                return length == 1 || (length == 2 && (s.equals("<<") || s.equals("<="))) || (length == 3 && s.equals("<=>"));
            }
            case '>': {
                return length == 1 || (length == 2 && (s.equals(">>") || s.equals(">=")));
            }
            case '=': {
                return (length == 2 && (s.equals("==") || s.equals("=~"))) || (length == 3 && s.equals("==="));
            }
            case '*': {
                return length == 1 || (length == 2 && s.equals("**"));
            }
            case '+': {
                return length == 1 || (length == 2 && s.equals("+@"));
            }
            case '-': {
                return length == 1 || (length == 2 && s.equals("-@"));
            }
            case '%':
            case '&':
            case '/':
            case '^':
            case '`':
            case '|':
            case '~': {
                return length == 1;
            }
            case '[': {
                return s.equals("[]") || s.equals("[]=");
            }
            default: {
                return false;
            }
        }
    }
    
    private static boolean isSymbolLocal(final String s, final char c, final int length) {
        if (!isIdentStart(c)) {
            return false;
        }
        final boolean localID = c >= 'a' && c <= 'z';
        int last;
        for (last = 1; last < length; ++last) {
            final char d = s.charAt(last);
            if (!isIdentChar(d)) {
                break;
            }
        }
        if (last == length) {
            return true;
        }
        if (localID && last == length - 1) {
            final char d = s.charAt(last);
            return d == '!' || d == '?' || d == '=';
        }
        return false;
    }
    
    @JRubyMethod(name = { "all_symbols" }, meta = true)
    public static IRubyObject all_symbols(final ThreadContext context, final IRubyObject recv) {
        return context.getRuntime().getSymbolTable().all_symbols();
    }
    
    @Deprecated
    public static IRubyObject all_symbols(final IRubyObject recv) {
        return recv.getRuntime().getSymbolTable().all_symbols();
    }
    
    public static RubySymbol unmarshalFrom(final UnmarshalStream input) throws IOException {
        final RubySymbol result = newSymbol(input.getRuntime(), RubyString.byteListToString(input.unmarshalString()));
        input.registerLinkTarget(result);
        return result;
    }
    
    public Object toJava(final Class target) {
        if (target == String.class || target == CharSequence.class) {
            return this.symbol;
        }
        return super.toJava(target);
    }
    
    private static class ToProcCallback implements BlockCallback
    {
        private RubySymbol symbol;
        
        public ToProcCallback(final RubySymbol symbol) {
            this.symbol = symbol;
        }
        
        public IRubyObject call(final ThreadContext ctx, IRubyObject[] args, final Block blk) {
            if (args.length == 0) {
                throw this.symbol.getRuntime().newArgumentError("no receiver given");
            }
            if (args.length == 1 && args[0] instanceof RubyArray) {
                args = ((RubyArray)args[0]).toJavaArrayUnsafe();
            }
            final IRubyObject[] args2 = new IRubyObject[args.length - 1];
            System.arraycopy(args, 1, args2, 0, args2.length);
            return RuntimeHelpers.invoke(ctx, args[0], this.symbol.symbol, args2);
        }
    }
    
    public static final class SymbolTable
    {
        static final int DEFAULT_INITIAL_CAPACITY = 2048;
        static final int MAXIMUM_CAPACITY = 1073741824;
        static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private final ReentrantLock tableLock;
        private volatile SymbolEntry[] symbolTable;
        private int size;
        private int threshold;
        private final float loadFactor;
        private final Ruby runtime;
        
        public SymbolTable(final Ruby runtime) {
            this.tableLock = new ReentrantLock();
            this.runtime = runtime;
            this.loadFactor = 0.75f;
            this.threshold = 1536;
            this.symbolTable = new SymbolEntry[2048];
        }
        
        public RubySymbol getSymbol(final String name) {
            final int hash = name.hashCode();
            final SymbolEntry[] table = this.symbolTable;
            for (SymbolEntry e = getEntryFromTable(table, hash); e != null; e = e.next) {
                if (isSymbolMatch(name, hash, e)) {
                    return e.symbol;
                }
            }
            return this.createSymbol(name, ByteList.create(name), hash, table);
        }
        
        public RubySymbol getSymbol(final ByteList bytes) {
            final String name = bytes.toString();
            final int hash = name.hashCode();
            final SymbolEntry[] table = this.symbolTable;
            for (SymbolEntry e = getEntryFromTable(table, hash); e != null; e = e.next) {
                if (isSymbolMatch(name, hash, e)) {
                    return e.symbol;
                }
            }
            return this.createSymbol(name, bytes, hash, table);
        }
        
        public RubySymbol fastGetSymbol(final String internedName) {
            final SymbolEntry[] table = this.symbolTable;
            for (SymbolEntry e = getEntryFromTable(this.symbolTable, internedName.hashCode()); e != null; e = e.next) {
                if (isSymbolMatch(internedName, e)) {
                    return e.symbol;
                }
            }
            return this.fastCreateSymbol(internedName, table);
        }
        
        private static SymbolEntry getEntryFromTable(final SymbolEntry[] table, final int hash) {
            return table[hash & table.length - 1];
        }
        
        private static boolean isSymbolMatch(final String name, final int hash, final SymbolEntry entry) {
            return hash == entry.hash && name.equals(entry.name);
        }
        
        private static boolean isSymbolMatch(final String internedName, final SymbolEntry entry) {
            return internedName == entry.name;
        }
        
        private RubySymbol createSymbol(final String name, final ByteList value, final int hash, SymbolEntry[] table) {
            final ReentrantLock lock;
            (lock = this.tableLock).lock();
            try {
                final int potentialNewSize;
                if ((potentialNewSize = this.size + 1) > this.threshold) {
                    table = this.rehash();
                }
                else {
                    table = this.symbolTable;
                }
                int index;
                for (SymbolEntry e = table[index = (hash & table.length - 1)]; e != null; e = e.next) {
                    if (hash == e.hash && name.equals(e.name)) {
                        return e.symbol;
                    }
                }
                final String internedName;
                final RubySymbol symbol = new RubySymbol(this.runtime, internedName = name.intern(), value, null);
                table[index] = new SymbolEntry(hash, internedName, symbol, table[index]);
                this.size = potentialNewSize;
                this.symbolTable = table;
                return symbol;
            }
            finally {
                lock.unlock();
            }
        }
        
        private RubySymbol fastCreateSymbol(final String internedName, SymbolEntry[] table) {
            final ReentrantLock lock;
            (lock = this.tableLock).lock();
            try {
                final int potentialNewSize;
                if ((potentialNewSize = this.size + 1) > this.threshold) {
                    table = this.rehash();
                }
                else {
                    table = this.symbolTable;
                }
                int hash;
                int index;
                for (SymbolEntry e = table[index = ((hash = internedName.hashCode()) & table.length - 1)]; e != null; e = e.next) {
                    if (internedName == e.name) {
                        return e.symbol;
                    }
                }
                final RubySymbol symbol = new RubySymbol(this.runtime, internedName, (RubySymbol$1)null);
                table[index] = new SymbolEntry(hash, internedName, symbol, table[index]);
                this.size = potentialNewSize;
                this.symbolTable = table;
                return symbol;
            }
            finally {
                lock.unlock();
            }
        }
        
        public RubySymbol lookup(final String name) {
            final int hash = name.hashCode();
            SymbolEntry[] table;
            for (SymbolEntry e = (table = this.symbolTable)[hash & table.length - 1]; e != null; e = e.next) {
                if (hash == e.hash && name.equals(e.name)) {
                    return e.symbol;
                }
            }
            return null;
        }
        
        public RubySymbol lookup(final long id) {
            final SymbolEntry[] table = this.symbolTable;
            int i = table.length;
            while (--i >= 0) {
                for (SymbolEntry e = table[i]; e != null; e = e.next) {
                    if (id == e.symbol.id) {
                        return e.symbol;
                    }
                }
            }
            return null;
        }
        
        public RubyArray all_symbols() {
            final SymbolEntry[] table = this.symbolTable;
            final RubyArray array = this.runtime.newArray(this.size);
            int i = table.length;
            while (--i >= 0) {
                for (SymbolEntry e = table[i]; e != null; e = e.next) {
                    array.append(e.symbol);
                }
            }
            return array;
        }
        
        @Deprecated
        public void store(final RubySymbol symbol) {
            throw new UnsupportedOperationException();
        }
        
        private SymbolEntry[] rehash() {
            final SymbolEntry[] oldTable = this.symbolTable;
            final int oldCapacity;
            if ((oldCapacity = oldTable.length) >= 1073741824) {
                return oldTable;
            }
            final int newCapacity = oldCapacity << 1;
            final SymbolEntry[] newTable = new SymbolEntry[newCapacity];
            this.threshold = (int)(newCapacity * this.loadFactor);
            final int sizeMask = newCapacity - 1;
            int i = oldCapacity;
            while (--i >= 0) {
                final SymbolEntry e = oldTable[i];
                if (e != null) {
                    final SymbolEntry next = e.next;
                    final int idx = e.hash & sizeMask;
                    if (next == null) {
                        newTable[idx] = e;
                    }
                    else {
                        SymbolEntry lastRun = e;
                        int lastIdx = idx;
                        for (SymbolEntry last = next; last != null; last = last.next) {
                            final int k = last.hash & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }
                        newTable[lastIdx] = lastRun;
                        for (SymbolEntry p = e; p != lastRun; p = p.next) {
                            final int k = p.hash & sizeMask;
                            final SymbolEntry n = newTable[k];
                            newTable[k] = new SymbolEntry(p.hash, p.name, p.symbol, n);
                        }
                    }
                }
            }
            return this.symbolTable = newTable;
        }
        
        static class SymbolEntry
        {
            final int hash;
            final String name;
            final RubySymbol symbol;
            final SymbolEntry next;
            
            SymbolEntry(final int hash, final String name, final RubySymbol symbol, final SymbolEntry next) {
                this.hash = hash;
                this.name = name;
                this.symbol = symbol;
                this.next = next;
            }
        }
    }
}
