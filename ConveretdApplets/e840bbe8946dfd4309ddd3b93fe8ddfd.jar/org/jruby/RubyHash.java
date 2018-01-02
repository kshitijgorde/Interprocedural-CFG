// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.NoSuchElementException;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import org.jruby.exceptions.RaiseException;
import java.util.Collection;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.marshal.UnmarshalStream;
import java.io.IOException;
import org.jruby.runtime.marshal.MarshalStream;
import org.jruby.common.IRubyWarnings;
import org.jruby.util.RecursiveComparator;
import java.util.Set;
import org.jruby.util.ByteList;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Visibility;
import org.jruby.runtime.Arity;
import java.util.Iterator;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.TypeConverter;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import java.util.concurrent.atomic.AtomicInteger;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.anno.JRubyClass;
import java.util.Map;

@JRubyClass(name = { "Hash" }, include = { "Enumerable" })
public class RubyHash extends RubyObject implements Map
{
    private static final ObjectAllocator HASH_ALLOCATOR;
    private RubyHashEntry[] table;
    protected int size;
    private int threshold;
    private static final int PROCDEFAULT_HASH_F = 1024;
    private IRubyObject ifNone;
    private static final int[] MRI_PRIMES;
    private static final int JAVASOFT_INITIAL_CAPACITY = 8;
    private static final int MRI_INITIAL_CAPACITY;
    private static final int INITIAL_THRESHOLD = 6;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    public static final RubyHashEntry NO_ENTRY;
    private int generation;
    private final RubyHashEntry head;
    private static final int HASH_SIGN_BIT_MASK = Integer.MAX_VALUE;
    private static final int MIN_CAPA = 8;
    private static final int ST_DEFAULT_MAX_DENSITY = 5;
    private static final boolean MRI_HASH = true;
    private static final boolean MRI_HASH_RESIZE = true;
    public static long collisions;
    private static final EntryMatchType MATCH_KEY;
    private static final EntryMatchType MATCH_ENTRY;
    private static final Found FOUND;
    private AtomicInteger iteratorCount;
    private static final EntryView DIRECT_KEY_VIEW;
    private static final EntryView KEY_VIEW;
    private static final EntryView DIRECT_VALUE_VIEW;
    private static final EntryView VALUE_VIEW;
    private static final EntryView DIRECT_ENTRY_VIEW;
    private static final EntryView ENTRY_VIEW;
    
    public static RubyClass createHashClass(final Ruby runtime) {
        final RubyClass hashc = runtime.defineClass("Hash", runtime.getObject(), RubyHash.HASH_ALLOCATOR);
        runtime.setHash(hashc);
        hashc.index = 10;
        hashc.setReifiedClass(RubyHash.class);
        hashc.kindOf = new RubyModule.KindOf() {
            public boolean isKindOf(final IRubyObject obj, final RubyModule type) {
                return obj instanceof RubyHash;
            }
        };
        hashc.includeModule(runtime.getEnumerable());
        hashc.defineAnnotatedMethods(RubyHash.class);
        return hashc;
    }
    
    public int getNativeTypeIndex() {
        return 10;
    }
    
    @JRubyMethod(name = { "[]" }, rest = true, meta = true)
    public static IRubyObject create(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final RubyClass klass = (RubyClass)recv;
        final Ruby runtime = context.getRuntime();
        if (args.length == 1) {
            IRubyObject tmp = TypeConverter.convertToTypeWithCheck(args[0], runtime.getHash(), "to_hash");
            if (!tmp.isNil()) {
                final RubyHash otherHash = (RubyHash)tmp;
                return new RubyHash(runtime, klass, otherHash);
            }
            tmp = TypeConverter.convertToTypeWithCheck(args[0], runtime.getArray(), "to_ary");
            if (!tmp.isNil()) {
                final RubyHash hash = (RubyHash)klass.allocate();
                final RubyArray arr = (RubyArray)tmp;
                for (int i = 0, j = arr.getLength(); i < j; ++i) {
                    final IRubyObject v = TypeConverter.convertToTypeWithCheck(arr.entry(i), runtime.getArray(), "to_ary");
                    IRubyObject key = runtime.getNil();
                    IRubyObject val = runtime.getNil();
                    if (!v.isNil()) {
                        switch (((RubyArray)v).getLength()) {
                            case 2: {
                                val = ((RubyArray)v).entry(1);
                            }
                            case 1: {
                                key = ((RubyArray)v).entry(0);
                                hash.fastASet(key, val);
                                break;
                            }
                        }
                    }
                }
                return hash;
            }
        }
        if ((args.length & 0x1) != 0x0) {
            throw runtime.newArgumentError("odd number of arguments for Hash");
        }
        final RubyHash hash = (RubyHash)klass.allocate();
        for (int k = 0; k < args.length; k += 2) {
            hash.op_aset(context, args[k], args[k + 1]);
        }
        return hash;
    }
    
    @JRubyMethod(name = { "try_convert" }, meta = true, compat = CompatVersion.RUBY1_9)
    public static IRubyObject try_convert(final ThreadContext context, final IRubyObject recv, final IRubyObject args) {
        return TypeConverter.convertToTypeWithCheck(args, context.getRuntime().getHash(), "to_hash");
    }
    
    public static final RubyHash newHash(final Ruby runtime) {
        return new RubyHash(runtime);
    }
    
    public static final RubyHash newHash(final Ruby runtime, final Map valueMap, final IRubyObject defaultValue) {
        assert defaultValue != null;
        return new RubyHash(runtime, valueMap, defaultValue);
    }
    
    private RubyHash(final Ruby runtime, final RubyClass klass, final RubyHash other) {
        super(runtime, klass);
        this.size = 0;
        this.generation = 0;
        (this.head = new RubyHashEntry()).prevAdded = (this.head.nextAdded = this.head);
        this.iteratorCount = new AtomicInteger(0);
        this.ifNone = runtime.getNil();
        this.threshold = 6;
        this.table = other.internalCopyTable(this.head);
        this.size = other.size;
    }
    
    public RubyHash(final Ruby runtime, final RubyClass klass) {
        super(runtime, klass);
        this.size = 0;
        this.generation = 0;
        (this.head = new RubyHashEntry()).prevAdded = (this.head.nextAdded = this.head);
        this.iteratorCount = new AtomicInteger(0);
        this.ifNone = runtime.getNil();
        this.alloc();
    }
    
    public RubyHash(final Ruby runtime) {
        this(runtime, runtime.getNil());
    }
    
    public RubyHash(final Ruby runtime, final IRubyObject defaultValue) {
        super(runtime, runtime.getHash());
        this.size = 0;
        this.generation = 0;
        (this.head = new RubyHashEntry()).prevAdded = (this.head.nextAdded = this.head);
        this.iteratorCount = new AtomicInteger(0);
        this.ifNone = defaultValue;
        this.alloc();
    }
    
    RubyHash(final Ruby runtime, final boolean objectSpace) {
        super(runtime, runtime.getHash(), objectSpace);
        this.size = 0;
        this.generation = 0;
        (this.head = new RubyHashEntry()).prevAdded = (this.head.nextAdded = this.head);
        this.iteratorCount = new AtomicInteger(0);
        this.alloc();
    }
    
    public RubyHash(final Ruby runtime, final Map valueMap, final IRubyObject defaultValue) {
        super(runtime, runtime.getHash());
        this.size = 0;
        this.generation = 0;
        (this.head = new RubyHashEntry()).prevAdded = (this.head.nextAdded = this.head);
        this.iteratorCount = new AtomicInteger(0);
        this.ifNone = defaultValue;
        this.alloc();
        for (final Entry e : valueMap.entrySet()) {
            this.internalPut(e.getKey(), e.getValue());
        }
    }
    
    private final void alloc() {
        this.threshold = 6;
        ++this.generation;
        this.head.nextAdded = (this.head.prevAdded = this.head);
        this.table = new RubyHashEntry[RubyHash.MRI_INITIAL_CAPACITY];
    }
    
    private static int JavaSoftHashValue(int h) {
        h ^= (h >>> 20 ^ h >>> 12);
        return h ^ h >>> 7 ^ h >>> 4;
    }
    
    private static int JavaSoftBucketIndex(final int h, final int length) {
        return h & length - 1;
    }
    
    private static int MRIHashValue(final int h) {
        return h & Integer.MAX_VALUE;
    }
    
    private static int MRIBucketIndex(final int h, final int length) {
        return (h & Integer.MAX_VALUE) % length;
    }
    
    private final void resize(final int newCapacity) {
        final RubyHashEntry[] oldTable = this.table;
        final RubyHashEntry[] newTable = new RubyHashEntry[newCapacity];
        for (int j = 0; j < oldTable.length; ++j) {
            RubyHashEntry entry = oldTable[j];
            oldTable[j] = null;
            while (entry != null) {
                final RubyHashEntry next = entry.next;
                final int i = bucketIndex(entry.hash, newCapacity);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
        this.table = newTable;
    }
    
    private final void JavaSoftCheckResize() {
        if (this.overThreshold()) {
            final RubyHashEntry[] tbl = this.table;
            if (tbl.length == 1073741824) {
                this.threshold = Integer.MAX_VALUE;
                return;
            }
            this.resizeAndAdjustThreshold(this.table);
        }
    }
    
    private boolean overThreshold() {
        return this.size > this.threshold;
    }
    
    private void resizeAndAdjustThreshold(final RubyHashEntry[] oldTable) {
        final int newCapacity = oldTable.length << 1;
        this.resize(newCapacity);
        this.threshold = newCapacity - (newCapacity >> 2);
    }
    
    private final void MRICheckResize() {
        if (this.size / this.table.length > 5) {
            final int forSize = this.table.length + 1;
            for (int i = 0, newCapacity = 8; i < RubyHash.MRI_PRIMES.length; ++i, newCapacity <<= 1) {
                if (newCapacity > forSize) {
                    this.resize(RubyHash.MRI_PRIMES[i]);
                    return;
                }
            }
        }
    }
    
    protected static int hashValue(final int h) {
        return MRIHashValue(h);
    }
    
    private static int bucketIndex(final int h, final int length) {
        return MRIBucketIndex(h, length);
    }
    
    private void checkResize() {
        this.MRICheckResize();
    }
    
    private void checkIterating() {
        if (this.iteratorCount.get() > 0) {
            throw this.getRuntime().newRuntimeError("can't add a new key into hash during iteration");
        }
    }
    
    private final void internalPut(final IRubyObject key, final IRubyObject value) {
        this.internalPut(key, value, true);
    }
    
    protected void internalPut(final IRubyObject key, final IRubyObject value, final boolean checkForExisting) {
        this.checkResize();
        final int hash = hashValue(key.hashCode());
        final int i = bucketIndex(hash, this.table.length);
        if (checkForExisting) {
            for (RubyHashEntry entry = this.table[i]; entry != null; entry = entry.next) {
                if (this.internalKeyExist(entry, hash, key)) {
                    entry.value = value;
                    return;
                }
            }
        }
        this.checkIterating();
        this.table[i] = new RubyHashEntry(hash, key, value, this.table[i], this.head);
        ++this.size;
    }
    
    protected IRubyObject internalGet(final IRubyObject key) {
        return this.internalGetEntry(key).value;
    }
    
    protected RubyHashEntry internalGetEntry(final IRubyObject key) {
        final int hash = hashValue(key.hashCode());
        for (RubyHashEntry entry = this.table[bucketIndex(hash, this.table.length)]; entry != null; entry = entry.next) {
            if (this.internalKeyExist(entry, hash, key)) {
                return entry;
            }
        }
        return RubyHash.NO_ENTRY;
    }
    
    private boolean internalKeyExist(final RubyHashEntry entry, final int hash, final IRubyObject key) {
        return entry.hash == hash && (entry.key == key || (!this.isComparedByIdentity() && key.eql(entry.key)));
    }
    
    protected RubyHashEntry internalDelete(final IRubyObject key) {
        return this.internalDelete(hashValue(key.hashCode()), RubyHash.MATCH_KEY, key);
    }
    
    protected RubyHashEntry internalDeleteEntry(final RubyHashEntry entry) {
        return this.internalDelete(hashValue(entry.key.hashCode()), RubyHash.MATCH_ENTRY, entry);
    }
    
    private final RubyHashEntry internalDelete(final int hash, final EntryMatchType matchType, final Object obj) {
        final int i = bucketIndex(hash, this.table.length);
        RubyHashEntry entry = this.table[i];
        if (entry != null) {
            RubyHashEntry prior = null;
            while (entry != null) {
                if (entry.hash == hash && matchType.matches(entry, obj)) {
                    if (prior != null) {
                        prior.next = entry.next;
                    }
                    else {
                        this.table[i] = entry.next;
                    }
                    entry.detach();
                    --this.size;
                    return entry;
                }
                prior = entry;
                entry = entry.next;
            }
        }
        return RubyHash.NO_ENTRY;
    }
    
    private final RubyHashEntry[] internalCopyTable(final RubyHashEntry destHead) {
        final RubyHashEntry[] newTable = new RubyHashEntry[this.table.length];
        for (RubyHashEntry entry = this.head.nextAdded; entry != this.head; entry = entry.nextAdded) {
            final int i = bucketIndex(entry.hash, this.table.length);
            newTable[i] = new RubyHashEntry(entry.hash, entry.key, entry.value, newTable[i], destHead);
        }
        return newTable;
    }
    
    public void visitAll(final Visitor visitor) {
        int startGeneration = this.generation;
        for (RubyHashEntry entry = this.head.nextAdded; entry != this.head; entry = entry.nextAdded) {
            if (startGeneration != this.generation) {
                startGeneration = this.generation;
                entry = this.head.nextAdded;
                if (entry == this.head) {
                    break;
                }
            }
            if (entry.isLive()) {
                visitor.visit(entry.key, entry.value);
            }
        }
    }
    
    @JRubyMethod(optional = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject[] args, final Block block) {
        this.modify();
        if (block.isGiven()) {
            if (args.length > 0) {
                throw this.getRuntime().newArgumentError("wrong number of arguments");
            }
            this.ifNone = this.getRuntime().newProc(Block.Type.PROC, block);
            this.flags |= 0x400;
        }
        else {
            Arity.checkArgumentCount(this.getRuntime(), args, 0, 1);
            if (args.length == 1) {
                this.ifNone = args[0];
            }
        }
        return this;
    }
    
    @Deprecated
    public IRubyObject default_value_get(final ThreadContext context, final IRubyObject[] args) {
        switch (args.length) {
            case 0: {
                return this.default_value_get(context);
            }
            case 1: {
                return this.default_value_get(context, args[0]);
            }
            default: {
                throw context.getRuntime().newArgumentError(args.length, 1);
            }
        }
    }
    
    @JRubyMethod(name = { "default" })
    public IRubyObject default_value_get(final ThreadContext context) {
        if ((this.flags & 0x400) != 0x0) {
            return this.getRuntime().getNil();
        }
        return this.ifNone;
    }
    
    @JRubyMethod(name = { "default" })
    public IRubyObject default_value_get(final ThreadContext context, final IRubyObject arg) {
        if ((this.flags & 0x400) != 0x0) {
            return RuntimeHelpers.invoke(context, this.ifNone, "call", this, arg);
        }
        return this.ifNone;
    }
    
    @JRubyMethod(name = { "default=" }, required = 1)
    public IRubyObject default_value_set(final IRubyObject defaultValue) {
        this.modify();
        this.ifNone = defaultValue;
        this.flags &= 0xFFFFFBFF;
        return this.ifNone;
    }
    
    @JRubyMethod
    public IRubyObject default_proc() {
        return ((this.flags & 0x400) != 0x0) ? this.ifNone : this.getRuntime().getNil();
    }
    
    private void checkDefaultProcArity(final IRubyObject proc) {
        int n = ((RubyProc)proc).getBlock().arity().getValue();
        if (((RubyProc)proc).getBlock().type == Block.Type.LAMBDA && n != 2 && (n >= 0 || n < -3)) {
            if (n < 0) {
                n = -n - 1;
            }
            throw this.getRuntime().newTypeError("default_proc takes two arguments (2 for " + n + ")");
        }
    }
    
    @JRubyMethod(name = { "default_proc=" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject set_default_proc(IRubyObject proc) {
        this.modify();
        final IRubyObject b = TypeConverter.convertToType(proc, this.getRuntime().getProc(), "to_proc");
        if (b.isNil() || !(b instanceof RubyProc)) {
            throw this.getRuntime().newTypeError("wrong default_proc type " + proc.getMetaClass() + " (expected Proc)");
        }
        proc = b;
        this.checkDefaultProcArity(proc);
        this.ifNone = proc;
        this.flags |= 0x400;
        return proc;
    }
    
    public void modify() {
        this.testFrozen("hash");
        if (this.isTaint() && this.getRuntime().getSafeLevel() >= 4) {
            throw this.getRuntime().newSecurityError("Insecure: can't modify hash");
        }
    }
    
    private IRubyObject inspectHash(final ThreadContext context) {
        final ByteList buffer = new ByteList();
        buffer.append(123);
        final boolean[] firstEntry = { true };
        this.visitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (!firstEntry[0]) {
                    buffer.append(44).append(32);
                }
                buffer.append(RubyObject.inspect(context, key).getByteList());
                buffer.append(61).append(62);
                buffer.append(RubyObject.inspect(context, value).getByteList());
                firstEntry[0] = false;
            }
        });
        buffer.append(125);
        return this.getRuntime().newString(buffer);
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect(final ThreadContext context) {
        if (this.size == 0) {
            return this.getRuntime().newString("{}");
        }
        if (this.getRuntime().isInspecting(this)) {
            return this.getRuntime().newString("{...}");
        }
        try {
            this.getRuntime().registerInspecting(this);
            return this.inspectHash(context);
        }
        finally {
            this.getRuntime().unregisterInspecting(this);
        }
    }
    
    @JRubyMethod(name = { "size", "length" })
    public RubyFixnum rb_size() {
        return this.getRuntime().newFixnum(this.size);
    }
    
    @JRubyMethod(name = { "empty?" })
    public RubyBoolean empty_p() {
        return (this.size == 0) ? this.getRuntime().getTrue() : this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "to_a" })
    public RubyArray to_a() {
        final Ruby runtime = this.getRuntime();
        try {
            final RubyArray result = RubyArray.newArray(runtime, this.size);
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    result.append(RubyArray.newArray(runtime, key, value));
                }
            });
            result.setTaint(this.isTaint());
            return result;
        }
        catch (NegativeArraySizeException nase) {
            throw this.concurrentModification();
        }
    }
    
    @JRubyMethod(name = { "to_s" })
    public IRubyObject to_s(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (runtime.isInspecting(this)) {
            return runtime.newString("{...}");
        }
        try {
            runtime.registerInspecting(this);
            return this.to_a().to_s();
        }
        finally {
            runtime.unregisterInspecting(this);
        }
    }
    
    @JRubyMethod(name = { "to_s" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_s19(final ThreadContext context) {
        return this.inspect(context);
    }
    
    @JRubyMethod(name = { "rehash" })
    public RubyHash rehash() {
        if (this.iteratorCount.get() > 0) {
            throw this.getRuntime().newRuntimeError("rehash during iteration");
        }
        this.modify();
        final RubyHashEntry[] oldTable = this.table;
        final RubyHashEntry[] newTable = new RubyHashEntry[oldTable.length];
        for (int j = 0; j < oldTable.length; ++j) {
            RubyHashEntry entry = oldTable[j];
            oldTable[j] = null;
            while (entry != null) {
                final RubyHashEntry next = entry.next;
                entry.hash = entry.key.hashCode();
                final int i = bucketIndex(entry.hash, newTable.length);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
        this.table = newTable;
        return this;
    }
    
    @JRubyMethod(name = { "to_hash" })
    public RubyHash to_hash() {
        return this;
    }
    
    public RubyHash convertToHash() {
        return this;
    }
    
    public final void fastASet(final IRubyObject key, final IRubyObject value) {
        this.internalPut(key, value);
    }
    
    public final RubyHash fastASetChained(final IRubyObject key, final IRubyObject value) {
        this.internalPut(key, value);
        return this;
    }
    
    public final void fastASetCheckString(final Ruby runtime, final IRubyObject key, final IRubyObject value) {
        if (key instanceof RubyString) {
            this.op_asetForString(runtime, (RubyString)key, value);
        }
        else {
            this.internalPut(key, value);
        }
    }
    
    public final void fastASetCheckString19(final Ruby runtime, final IRubyObject key, final IRubyObject value) {
        if (key.getMetaClass().getRealClass() == runtime.getString()) {
            this.op_asetForString(runtime, (RubyString)key, value);
        }
        else {
            this.internalPut(key, value);
        }
    }
    
    @Deprecated
    public IRubyObject op_aset(final IRubyObject key, final IRubyObject value) {
        return this.op_aset(this.getRuntime().getCurrentContext(), key, value);
    }
    
    @JRubyMethod(name = { "[]=", "store" }, required = 2, compat = CompatVersion.RUBY1_8)
    public IRubyObject op_aset(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
        this.modify();
        this.fastASetCheckString(context.getRuntime(), key, value);
        return value;
    }
    
    @JRubyMethod(name = { "[]=", "store" }, required = 2, compat = CompatVersion.RUBY1_9)
    public IRubyObject op_aset19(final ThreadContext context, final IRubyObject key, final IRubyObject value) {
        this.modify();
        this.fastASetCheckString19(context.getRuntime(), key, value);
        return value;
    }
    
    protected void op_asetForString(final Ruby runtime, RubyString key, final IRubyObject value) {
        final RubyHashEntry entry = this.internalGetEntry(key);
        if (entry != RubyHash.NO_ENTRY) {
            entry.value = value;
        }
        else {
            this.checkIterating();
            if (!key.isFrozen()) {
                key = key.strDup(runtime, key.getMetaClass().getRealClass());
                key.setFrozen(true);
            }
            this.internalPut(key, value, false);
        }
    }
    
    public IRubyObject aset(final IRubyObject key, final IRubyObject value) {
        return this.op_aset(this.getRuntime().getCurrentContext(), key, value);
    }
    
    public IRubyObject aref(final IRubyObject key) {
        return this.op_aref(this.getRuntime().getCurrentContext(), key);
    }
    
    public final IRubyObject fastARef(final IRubyObject key) {
        return this.internalGet(key);
    }
    
    public RubyBoolean compare(final ThreadContext context, final String method, final IRubyObject other, final Set<RecursiveComparator.Pair> seen) {
        final Ruby runtime = context.getRuntime();
        if (!(other instanceof RubyHash)) {
            if (!other.respondsTo("to_hash")) {
                return runtime.getFalse();
            }
            return RuntimeHelpers.rbEqual(context, other, this);
        }
        else {
            final RubyHash otherHash = (RubyHash)other;
            if (this.size != otherHash.size) {
                return runtime.getFalse();
            }
            try {
                this.visitAll(new Visitor() {
                    public void visit(final IRubyObject key, final IRubyObject value) {
                        final IRubyObject value2 = otherHash.fastARef(key);
                        if (value2 == null) {
                            throw new Mismatch();
                        }
                        if (!RecursiveComparator.compare(context, method, value, value2, seen).isTrue()) {
                            throw new Mismatch();
                        }
                    }
                });
            }
            catch (Mismatch e) {
                return runtime.getFalse();
            }
            return runtime.getTrue();
        }
    }
    
    @JRubyMethod(name = { "==" })
    public IRubyObject op_equal(final ThreadContext context, final IRubyObject other) {
        return RecursiveComparator.compare(context, "==", this, other, null);
    }
    
    @JRubyMethod(name = { "eql?" })
    public IRubyObject op_eql19(final ThreadContext context, final IRubyObject other) {
        return RecursiveComparator.compare(context, "eql?", this, other, null);
    }
    
    @JRubyMethod(name = { "[]" }, required = 1)
    public IRubyObject op_aref(final ThreadContext context, final IRubyObject key) {
        final IRubyObject value;
        return ((value = this.internalGet(key)) == null) ? this.callMethod(context, "default", key) : value;
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_8)
    public RubyFixnum hash() {
        final Ruby runtime = this.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        if (this.size == 0 || runtime.isInspecting(this)) {
            return RubyFixnum.zero(runtime);
        }
        final long[] hash = { this.size };
        try {
            runtime.registerInspecting(this);
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    final long[] val$hash = hash;
                    final int n = 0;
                    val$hash[n] ^= RuntimeHelpers.invokedynamic(context, key, 3).convertToInteger().getLongValue();
                    final long[] val$hash2 = hash;
                    final int n2 = 0;
                    val$hash2[n2] ^= RuntimeHelpers.invokedynamic(context, value, 3).convertToInteger().getLongValue();
                }
            });
        }
        finally {
            runtime.unregisterInspecting(this);
        }
        return RubyFixnum.newFixnum(runtime, hash[0]);
    }
    
    @JRubyMethod(name = { "hash" }, compat = CompatVersion.RUBY1_9)
    public RubyFixnum hash19() {
        final Ruby runtime = this.getRuntime();
        final ThreadContext context = runtime.getCurrentContext();
        return (RubyFixnum)this.getRuntime().execRecursiveOuter(new Ruby.RecursiveFunction() {
            public IRubyObject call(final IRubyObject obj, final boolean recur) {
                if (RubyHash.this.size == 0) {
                    return RubyFixnum.zero(runtime);
                }
                final long[] h = { RubyHash.this.size };
                if (recur) {
                    final long[] array = h;
                    final int n = 0;
                    array[n] ^= RubyNumeric.num2long(RuntimeHelpers.invokedynamic(context, runtime.getHash(), 3));
                }
                else {
                    RubyHash.this.visitAll(new Visitor() {
                        public void visit(final IRubyObject key, final IRubyObject value) {
                            final long[] val$h = h;
                            final int n = 0;
                            val$h[n] ^= RuntimeHelpers.invokedynamic(context, key, 3).convertToInteger().getLongValue();
                            final long[] val$h2 = h;
                            final int n2 = 0;
                            val$h2[n2] ^= RuntimeHelpers.invokedynamic(context, value, 3).convertToInteger().getLongValue();
                        }
                    });
                }
                return runtime.newFixnum(h[0]);
            }
        }, this);
    }
    
    @JRubyMethod(required = 1, optional = 1)
    public IRubyObject fetch(final ThreadContext context, final IRubyObject[] args, final Block block) {
        if (args.length == 2 && block.isGiven()) {
            this.getRuntime().getWarnings().warn(IRubyWarnings.ID.BLOCK_BEATS_DEFAULT_VALUE, "block supersedes default value argument", new Object[0]);
        }
        final IRubyObject value;
        if ((value = this.internalGet(args[0])) != null) {
            return value;
        }
        if (block.isGiven()) {
            return block.yield(context, args[0]);
        }
        if (args.length == 1) {
            throw this.getRuntime().newIndexError("key not found");
        }
        return args[1];
    }
    
    @JRubyMethod(name = { "has_key?", "key?", "include?", "member?" }, required = 1)
    public RubyBoolean has_key_p(final IRubyObject key) {
        return (this.internalGetEntry(key) == RubyHash.NO_ENTRY) ? this.getRuntime().getFalse() : this.getRuntime().getTrue();
    }
    
    private boolean hasValue(final ThreadContext context, final IRubyObject expected) {
        try {
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    if (RubyObject.equalInternal(context, value, expected)) {
                        throw RubyHash.FOUND;
                    }
                }
            });
            return false;
        }
        catch (Found found) {
            return true;
        }
    }
    
    @JRubyMethod(name = { "has_value?", "value?" }, required = 1)
    public RubyBoolean has_value_p(final ThreadContext context, final IRubyObject expected) {
        return this.getRuntime().newBoolean(this.hasValue(context, expected));
    }
    
    private void iteratorEntry() {
        this.iteratorCount.incrementAndGet();
    }
    
    private void iteratorExit() {
        this.iteratorCount.decrementAndGet();
    }
    
    private void iteratorVisitAll(final Visitor visitor) {
        try {
            this.iteratorEntry();
            this.visitAll(visitor);
        }
        finally {
            this.iteratorExit();
        }
    }
    
    public RubyHash eachCommon(final ThreadContext context, final Block block) {
        if (block.arity() == Arity.TWO_ARGUMENTS) {
            this.iteratorVisitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    block.yieldSpecific(context, key, value);
                }
            });
        }
        else {
            final Ruby runtime = context.getRuntime();
            this.iteratorVisitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    block.yield(context, RubyArray.newArray(runtime, key, value));
                }
            });
        }
        return this;
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject each(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.eachCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    @JRubyMethod(name = { "each" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject each19(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_pairCommon(context, block, true) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each");
    }
    
    public RubyHash each_pairCommon(final ThreadContext context, final Block block, final boolean oneNine) {
        final Ruby runtime = this.getRuntime();
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (oneNine) {
                    block.yield(context, RubyArray.newArray(runtime, key, value));
                }
                else {
                    block.yieldArray(context, RubyArray.newArray(runtime, key, value), null, null);
                }
            }
        });
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_pair(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_pairCommon(context, block, context.getRuntime().is1_9()) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_pair");
    }
    
    public RubyHash each_valueCommon(final ThreadContext context, final Block block) {
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                block.yield(context, value);
            }
        });
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_value(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_valueCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_value");
    }
    
    public RubyHash each_keyCommon(final ThreadContext context, final Block block) {
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                block.yield(context, key);
            }
        });
        return this;
    }
    
    @JRubyMethod
    public IRubyObject each_key(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.each_keyCommon(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_key");
    }
    
    @JRubyMethod(name = { "select!" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject select_bang(final ThreadContext context, final Block block) {
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_key");
        }
        if (this.keep_ifCommon(context, block)) {
            return this;
        }
        return context.getRuntime().getNil();
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject keep_if(final ThreadContext context, final Block block) {
        if (block.isGiven()) {
            this.keep_ifCommon(context, block);
            return this;
        }
        return RubyEnumerator.enumeratorize(context.getRuntime(), this, "each_key");
    }
    
    public boolean keep_ifCommon(final ThreadContext context, final Block block) {
        this.testFrozen("hash");
        final boolean[] modified = { false };
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (!block.yieldSpecific(context, key, value).isTrue()) {
                    modified[0] = true;
                    RubyHash.this.remove(key);
                }
            }
        });
        return modified[0];
    }
    
    @JRubyMethod
    public IRubyObject sort(final ThreadContext context, final Block block) {
        return this.to_a().sort_bang(context, block);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_8)
    public IRubyObject index(final ThreadContext context, final IRubyObject expected) {
        final IRubyObject key = this.internalIndex(context, expected);
        return (key != null) ? key : context.getRuntime().getNil();
    }
    
    @JRubyMethod(name = { "index" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject index19(final ThreadContext context, final IRubyObject expected) {
        context.getRuntime().getWarnings().warn(IRubyWarnings.ID.DEPRECATED_METHOD, "Hash#index is deprecated; use Hash#key", new Object[0]);
        return this.key(context, expected);
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject key(final ThreadContext context, final IRubyObject expected) {
        final IRubyObject key = this.internalIndex(context, expected);
        return (key != null) ? key : context.getRuntime().getNil();
    }
    
    private IRubyObject internalIndex(final ThreadContext context, final IRubyObject expected) {
        try {
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    if (RubyObject.equalInternal(context, value, expected)) {
                        throw new FoundKey(key);
                    }
                }
            });
            return null;
        }
        catch (FoundKey found) {
            return found.key;
        }
    }
    
    @JRubyMethod(name = { "indexes", "indices" }, rest = true)
    public RubyArray indices(final ThreadContext context, final IRubyObject[] indices) {
        return this.values_at(context, indices);
    }
    
    @JRubyMethod(name = { "keys" })
    public RubyArray keys() {
        final Ruby runtime = this.getRuntime();
        try {
            final RubyArray keys = RubyArray.newArray(runtime, this.size);
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    keys.append(key);
                }
            });
            return keys;
        }
        catch (NegativeArraySizeException nase) {
            throw this.concurrentModification();
        }
    }
    
    @JRubyMethod(name = { "values" })
    public RubyArray rb_values() {
        try {
            final RubyArray values = RubyArray.newArray(this.getRuntime(), this.size);
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    values.append(value);
                }
            });
            return values;
        }
        catch (NegativeArraySizeException nase) {
            throw this.concurrentModification();
        }
    }
    
    @JRubyMethod(name = { "shift" })
    public IRubyObject shift(final ThreadContext context) {
        this.modify();
        final RubyHashEntry entry = this.head.nextAdded;
        if (entry != this.head) {
            final RubyArray result = RubyArray.newArray(this.getRuntime(), entry.key, entry.value);
            this.internalDeleteEntry(entry);
            return result;
        }
        if ((this.flags & 0x400) != 0x0) {
            return RuntimeHelpers.invoke(context, this.ifNone, "call", this, this.getRuntime().getNil());
        }
        return this.ifNone;
    }
    
    public final boolean fastDelete(final IRubyObject key) {
        return this.internalDelete(key) != RubyHash.NO_ENTRY;
    }
    
    @JRubyMethod
    public IRubyObject delete(final ThreadContext context, final IRubyObject key, final Block block) {
        this.modify();
        final RubyHashEntry entry = this.internalDelete(key);
        if (entry != RubyHash.NO_ENTRY) {
            return entry.value;
        }
        if (block.isGiven()) {
            return block.yield(context, key);
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject select(final ThreadContext context, final Block block) {
        final Ruby runtime = this.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "select");
        }
        final RubyArray result = runtime.newArray();
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (block.yieldArray(context, runtime.newArray(key, value), null, null).isTrue()) {
                    result.append(runtime.newArray(key, value));
                }
            }
        });
        return result;
    }
    
    @JRubyMethod(name = { "select" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject select19(final ThreadContext context, final Block block) {
        final Ruby runtime = context.getRuntime();
        if (!block.isGiven()) {
            return RubyEnumerator.enumeratorize(runtime, this, "select");
        }
        final RubyHash result = newHash(runtime);
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (block.yieldArray(context, runtime.newArray(key, value), null, null).isTrue()) {
                    result.fastASet(key, value);
                }
            }
        });
        return result;
    }
    
    public RubyHash delete_ifInternal(final ThreadContext context, final Block block) {
        this.modify();
        final Ruby runtime = this.getRuntime();
        this.iteratorVisitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                if (block.yieldArray(context, RubyArray.newArray(runtime, key, value), null, null).isTrue()) {
                    RubyHash.this.delete(context, key, Block.NULL_BLOCK);
                }
            }
        });
        return this;
    }
    
    @JRubyMethod
    public IRubyObject delete_if(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.delete_ifInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "delete_if");
    }
    
    public RubyHash rejectInternal(final ThreadContext context, final Block block) {
        return ((RubyHash)this.dup()).delete_ifInternal(context, block);
    }
    
    @JRubyMethod
    public IRubyObject reject(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.rejectInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "reject");
    }
    
    public IRubyObject reject_bangInternal(final ThreadContext context, final Block block) {
        final int n = this.size;
        this.delete_if(context, block);
        if (n == this.size) {
            return this.getRuntime().getNil();
        }
        return this;
    }
    
    @JRubyMethod(name = { "reject!" })
    public IRubyObject reject_bang(final ThreadContext context, final Block block) {
        return block.isGiven() ? this.reject_bangInternal(context, block) : RubyEnumerator.enumeratorize(context.getRuntime(), this, "reject!");
    }
    
    @JRubyMethod(name = { "clear" })
    public RubyHash rb_clear() {
        this.modify();
        if (this.size > 0) {
            this.alloc();
            this.size = 0;
        }
        return this;
    }
    
    @JRubyMethod(name = { "invert" })
    public RubyHash invert(final ThreadContext context) {
        final RubyHash result = newHash(this.getRuntime());
        this.visitAll(new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                result.op_aset(context, value, key);
            }
        });
        return result;
    }
    
    @JRubyMethod(name = { "merge!", "update" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyHash merge_bang(final ThreadContext context, final IRubyObject other, final Block block) {
        final RubyHash otherHash = other.convertToHash();
        if (otherHash.empty_p().isTrue()) {
            return this;
        }
        this.modify();
        final Ruby runtime = this.getRuntime();
        otherHash.visitAll(new Visitor() {
            public void visit(final IRubyObject key, IRubyObject value) {
                if (block.isGiven()) {
                    final IRubyObject existing = RubyHash.this.internalGet(key);
                    if (existing != null) {
                        value = block.yield(context, RubyArray.newArrayNoCopy(runtime, new IRubyObject[] { key, existing, value }));
                    }
                }
                RubyHash.this.op_aset(context, key, value);
            }
        });
        return this;
    }
    
    @JRubyMethod(name = { "merge!", "update" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyHash merge_bang19(final ThreadContext context, final IRubyObject other, final Block block) {
        final RubyHash otherHash = other.convertToHash();
        this.modify();
        if (otherHash.empty_p().isTrue()) {
            return this;
        }
        final Ruby runtime = this.getRuntime();
        otherHash.visitAll(new Visitor() {
            public void visit(final IRubyObject key, IRubyObject value) {
                if (block.isGiven()) {
                    final IRubyObject existing = RubyHash.this.internalGet(key);
                    if (existing != null) {
                        value = block.yield(context, RubyArray.newArrayNoCopy(runtime, new IRubyObject[] { key, existing, value }));
                    }
                }
                RubyHash.this.op_aset(context, key, value);
            }
        });
        return this;
    }
    
    @JRubyMethod
    public RubyHash merge(final ThreadContext context, final IRubyObject other, final Block block) {
        return ((RubyHash)this.dup()).merge_bang(context, other, block);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_8)
    public RubyHash initialize_copy(final ThreadContext context, final IRubyObject other) {
        return this.replace(context, other);
    }
    
    @JRubyMethod(name = { "initialize_copy" }, required = 1, visibility = Visibility.PRIVATE, compat = CompatVersion.RUBY1_9)
    public RubyHash initialize_copy19(final ThreadContext context, final IRubyObject other) {
        return this.replace19(context, other);
    }
    
    @JRubyMethod(name = { "replace" }, required = 1, compat = CompatVersion.RUBY1_8)
    public RubyHash replace(final ThreadContext context, final IRubyObject other) {
        return this.replaceCommon(context, other, new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                RubyHash.this.op_aset(context, key, value);
            }
        });
    }
    
    @JRubyMethod(name = { "replace" }, required = 1, compat = CompatVersion.RUBY1_9)
    public RubyHash replace19(final ThreadContext context, final IRubyObject other) {
        return this.replaceCommon19(context, other, new Visitor() {
            public void visit(final IRubyObject key, final IRubyObject value) {
                RubyHash.this.op_aset19(context, key, value);
            }
        });
    }
    
    private RubyHash replaceCommon(final ThreadContext context, final IRubyObject other, final Visitor visitor) {
        final RubyHash otherHash = other.convertToHash();
        if (this == otherHash) {
            return this;
        }
        this.rb_clear();
        if (!this.isComparedByIdentity() && otherHash.isComparedByIdentity()) {
            this.setComparedByIdentity(true);
        }
        otherHash.visitAll(visitor);
        this.ifNone = otherHash.ifNone;
        if ((otherHash.flags & 0x400) != 0x0) {
            this.flags |= 0x400;
        }
        else {
            this.flags &= 0xFFFFFBFF;
        }
        return this;
    }
    
    private RubyHash replaceCommon19(final ThreadContext context, final IRubyObject other, final Visitor visitor) {
        final RubyHash otherHash = other.convertToHash();
        this.rb_clear();
        if (this == otherHash) {
            return this;
        }
        if (!this.isComparedByIdentity() && otherHash.isComparedByIdentity()) {
            this.setComparedByIdentity(true);
        }
        otherHash.visitAll(visitor);
        this.ifNone = otherHash.ifNone;
        if ((otherHash.flags & 0x400) != 0x0) {
            this.flags |= 0x400;
        }
        else {
            this.flags &= 0xFFFFFBFF;
        }
        return this;
    }
    
    @JRubyMethod(name = { "values_at" }, rest = true)
    public RubyArray values_at(final ThreadContext context, final IRubyObject[] args) {
        final RubyArray result = RubyArray.newArray(this.getRuntime(), args.length);
        for (int i = 0; i < args.length; ++i) {
            result.append(this.op_aref(context, args[i]));
        }
        return result;
    }
    
    @JRubyMethod(name = { "assoc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject assoc(final ThreadContext context, final IRubyObject obj) {
        try {
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    if (RubyObject.equalInternal(context, obj, key)) {
                        throw new FoundPair(key, value);
                    }
                }
            });
            return context.getRuntime().getNil();
        }
        catch (FoundPair found) {
            return context.getRuntime().newArray(found.key, found.value);
        }
    }
    
    @JRubyMethod(name = { "rassoc" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rassoc(final ThreadContext context, final IRubyObject obj) {
        try {
            this.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    if (RubyObject.equalInternal(context, obj, value)) {
                        throw new FoundPair(key, value);
                    }
                }
            });
            return context.getRuntime().getNil();
        }
        catch (FoundPair found) {
            return context.getRuntime().newArray(found.key, found.value);
        }
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten(final ThreadContext context) {
        final RubyArray ary = this.to_a();
        ary.callMethod(context, "flatten!", RubyFixnum.one(context.getRuntime()));
        return ary;
    }
    
    @JRubyMethod(name = { "flatten" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject flatten(final ThreadContext context, final IRubyObject level) {
        final RubyArray ary = this.to_a();
        ary.callMethod(context, "flatten!", level);
        return ary;
    }
    
    @JRubyMethod(name = { "compare_by_identity" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getCompareByIdentity(final ThreadContext context) {
        this.modify();
        this.setComparedByIdentity(true);
        return this;
    }
    
    @JRubyMethod(name = { "compare_by_identity?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject getCompareByIdentity_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isComparedByIdentity());
    }
    
    @JRubyMethod(compat = CompatVersion.RUBY1_9)
    public IRubyObject dup(final ThreadContext context) {
        final RubyHash dup = (RubyHash)super.dup();
        dup.setComparedByIdentity(this.isComparedByIdentity());
        return dup;
    }
    
    @JRubyMethod(name = { "clone" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject rbClone(final ThreadContext context) {
        final RubyHash clone = (RubyHash)super.rbClone();
        clone.setComparedByIdentity(this.isComparedByIdentity());
        return clone;
    }
    
    public boolean hasDefaultProc() {
        return (this.flags & 0x400) != 0x0;
    }
    
    public IRubyObject getIfNone() {
        return this.ifNone;
    }
    
    public static void marshalTo(final RubyHash hash, final MarshalStream output) throws IOException {
        output.registerLinkTarget(hash);
        output.writeInt(hash.size);
        try {
            hash.visitAll(new Visitor() {
                public void visit(final IRubyObject key, final IRubyObject value) {
                    try {
                        output.dumpObject(key);
                        output.dumpObject(value);
                    }
                    catch (IOException e) {
                        throw new VisitorIOException(e);
                    }
                }
            });
        }
        catch (VisitorIOException e) {
            throw (IOException)e.getCause();
        }
        if (!hash.ifNone.isNil()) {
            output.dumpObject(hash.ifNone);
        }
    }
    
    public static RubyHash unmarshalFrom(final UnmarshalStream input, final boolean defaultValue) throws IOException {
        final RubyHash result = newHash(input.getRuntime());
        input.registerLinkTarget(result);
        for (int size = input.unmarshalInt(), i = 0; i < size; ++i) {
            result.fastASetCheckString(input.getRuntime(), input.unmarshalObject(), input.unmarshalObject());
        }
        if (defaultValue) {
            result.default_value_set(input.unmarshalObject());
        }
        return result;
    }
    
    public Class getJavaClass() {
        return Map.class;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public boolean containsKey(final Object key) {
        return this.internalGet(JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), key)) != null;
    }
    
    public boolean containsValue(final Object value) {
        return this.hasValue(this.getRuntime().getCurrentContext(), JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), value));
    }
    
    public Object get(final Object key) {
        final IRubyObject gotten = this.internalGet(JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), key));
        return (gotten == null) ? null : gotten.toJava(Object.class);
    }
    
    public Object put(final Object key, final Object value) {
        this.internalPut(JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), key), JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), value));
        return value;
    }
    
    public Object remove(final Object key) {
        final IRubyObject rubyKey = JavaUtil.convertJavaToUsableRubyObject(this.getRuntime(), key);
        return this.internalDelete(rubyKey).value;
    }
    
    public void putAll(final Map map) {
        final Ruby runtime = this.getRuntime();
        for (final Entry entry : map.entrySet()) {
            this.internalPut(JavaUtil.convertJavaToUsableRubyObject(runtime, entry.getKey()), JavaUtil.convertJavaToUsableRubyObject(runtime, entry.getValue()));
        }
    }
    
    public void clear() {
        this.rb_clear();
    }
    
    public boolean equals(final Object other) {
        return other instanceof RubyHash && (this == other || this.op_equal(this.getRuntime().getCurrentContext(), (IRubyObject)other).isTrue());
    }
    
    public Set keySet() {
        return new BaseSet(RubyHash.KEY_VIEW);
    }
    
    public Set directKeySet() {
        return new BaseSet(RubyHash.DIRECT_KEY_VIEW);
    }
    
    public Collection values() {
        return new BaseCollection(RubyHash.VALUE_VIEW);
    }
    
    public Collection directValues() {
        return new BaseCollection(RubyHash.DIRECT_VALUE_VIEW);
    }
    
    public Set entrySet() {
        return new BaseSet(RubyHash.ENTRY_VIEW);
    }
    
    public Set directEntrySet() {
        return new BaseSet(RubyHash.DIRECT_ENTRY_VIEW);
    }
    
    private final RaiseException concurrentModification() {
        return this.getRuntime().newConcurrencyError("Detected invalid hash contents due to unsynchronized modifications with concurrent users");
    }
    
    protected boolean isComparedByIdentity() {
        return (this.flags & 0x2000) != 0x0;
    }
    
    public void setComparedByIdentity(final boolean comparedByIdentity) {
        if (comparedByIdentity) {
            this.flags |= 0x2000;
        }
        else {
            this.flags &= 0xFFFFDFFF;
        }
    }
    
    static {
        HASH_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new RubyHash(runtime, klass);
            }
        };
        MRI_PRIMES = new int[] { 11, 19, 37, 67, 131, 283, 521, 1033, 2053, 4099, 8219, 16427, 32771, 65581, 131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259, 33554467, 67108879, 134217757, 268435459, 536870923, 1073741909, 0 };
        MRI_INITIAL_CAPACITY = RubyHash.MRI_PRIMES[0];
        NO_ENTRY = new RubyHashEntry();
        RubyHash.collisions = 0L;
        MATCH_KEY = new EntryMatchType() {
            public boolean matches(final RubyHashEntry entry, final Object obj) {
                final IRubyObject key = entry.key;
                return obj == key || ((IRubyObject)obj).eql(key);
            }
        };
        MATCH_ENTRY = new EntryMatchType() {
            public boolean matches(final RubyHashEntry entry, final Object obj) {
                return entry.equals(obj);
            }
        };
        FOUND = new Found();
        DIRECT_KEY_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return entry.key;
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                return o instanceof IRubyObject && hash.internalGet((IRubyObject)o) != null;
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                return o instanceof IRubyObject && hash.internalDelete((IRubyObject)o) != RubyHash.NO_ENTRY;
            }
        };
        KEY_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return entry.key.toJava(Object.class);
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                return hash.containsKey(o);
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                return hash.remove(o) != null;
            }
        };
        DIRECT_VALUE_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return entry.value;
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                if (!(o instanceof IRubyObject)) {
                    return false;
                }
                final IRubyObject obj = (IRubyObject)o;
                return hash.hasValue(obj.getRuntime().getCurrentContext(), obj);
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                if (!(o instanceof IRubyObject)) {
                    return false;
                }
                final IRubyObject obj = (IRubyObject)o;
                final IRubyObject key = hash.internalIndex(obj.getRuntime().getCurrentContext(), obj);
                return key != null && hash.internalDelete(key) != RubyHash.NO_ENTRY;
            }
        };
        VALUE_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return entry.value.toJava(Object.class);
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                return hash.containsValue(o);
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                final IRubyObject value = JavaUtil.convertJavaToUsableRubyObject(hash.getRuntime(), o);
                final IRubyObject key = hash.internalIndex(hash.getRuntime().getCurrentContext(), value);
                return key != null && hash.internalDelete(key) != RubyHash.NO_ENTRY;
            }
        };
        DIRECT_ENTRY_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return entry;
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                if (!(o instanceof RubyHashEntry)) {
                    return false;
                }
                final RubyHashEntry entry = (RubyHashEntry)o;
                final RubyHashEntry candidate = hash.internalGetEntry(entry.key);
                return candidate != RubyHash.NO_ENTRY && entry.equals(candidate);
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                return o instanceof RubyHashEntry && hash.internalDeleteEntry((RubyHashEntry)o) != RubyHash.NO_ENTRY;
            }
        };
        ENTRY_VIEW = new EntryView() {
            public Object convertEntry(final Ruby runtime, final RubyHashEntry entry) {
                return new ConvertingEntry(runtime, entry);
            }
            
            public boolean contains(final RubyHash hash, final Object o) {
                if (!(o instanceof ConvertingEntry)) {
                    return false;
                }
                final ConvertingEntry entry = (ConvertingEntry)o;
                final RubyHashEntry candidate = hash.internalGetEntry(entry.entry.key);
                return candidate != RubyHash.NO_ENTRY && entry.entry.equals(candidate);
            }
            
            public boolean remove(final RubyHash hash, final Object o) {
                if (!(o instanceof ConvertingEntry)) {
                    return false;
                }
                final ConvertingEntry entry = (ConvertingEntry)o;
                return hash.internalDeleteEntry(entry.entry) != RubyHash.NO_ENTRY;
            }
        };
    }
    
    public static final class RubyHashEntry implements Entry
    {
        private IRubyObject key;
        private IRubyObject value;
        private RubyHashEntry next;
        private RubyHashEntry prevAdded;
        private RubyHashEntry nextAdded;
        private int hash;
        
        RubyHashEntry() {
            this.key = RubyBasicObject.NEVER;
        }
        
        public RubyHashEntry(final int h, final IRubyObject k, final IRubyObject v, final RubyHashEntry e, final RubyHashEntry head) {
            this.key = k;
            this.value = v;
            this.next = e;
            this.hash = h;
            if (head != null) {
                this.prevAdded = head.prevAdded;
                this.nextAdded = head;
                this.nextAdded.prevAdded = this;
                this.prevAdded.nextAdded = this;
            }
        }
        
        public void detach() {
            if (this.prevAdded != null) {
                this.prevAdded.nextAdded = this.nextAdded;
                this.nextAdded.prevAdded = this.prevAdded;
                this.prevAdded = null;
            }
        }
        
        public boolean isLive() {
            return this.prevAdded != null;
        }
        
        public Object getKey() {
            return this.key;
        }
        
        public Object getJavaifiedKey() {
            return this.key.toJava(Object.class);
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public Object getJavaifiedValue() {
            return this.value.toJava(Object.class);
        }
        
        public Object setValue(final Object value) {
            final IRubyObject oldValue = this.value;
            if (value instanceof IRubyObject) {
                this.value = (IRubyObject)value;
                return oldValue;
            }
            throw new UnsupportedOperationException("directEntrySet() doesn't support setValue for non IRubyObject instance entries, convert them manually or use entrySet() instead");
        }
        
        public boolean equals(final Object other) {
            if (!(other instanceof RubyHashEntry)) {
                return false;
            }
            final RubyHashEntry otherEntry = (RubyHashEntry)other;
            return (this.key == otherEntry.key || this.key.eql(otherEntry.key)) && (this.value == otherEntry.value || this.value.equals(otherEntry.value));
        }
        
        public int hashCode() {
            return this.key.hashCode() ^ this.value.hashCode();
        }
    }
    
    private abstract static class EntryMatchType
    {
        public abstract boolean matches(final RubyHashEntry p0, final Object p1);
    }
    
    public abstract static class Visitor
    {
        public abstract void visit(final IRubyObject p0, final IRubyObject p1);
    }
    
    private static class Found extends RuntimeException
    {
        public synchronized Throwable fillInStackTrace() {
            return null;
        }
    }
    
    private static class FoundKey extends Found
    {
        public final IRubyObject key;
        
        FoundKey(final IRubyObject key) {
            this.key = key;
        }
    }
    
    private static class FoundPair extends FoundKey
    {
        public final IRubyObject value;
        
        FoundPair(final IRubyObject key, final IRubyObject value) {
            super(key);
            this.value = value;
        }
    }
    
    private static class Mismatch extends RuntimeException
    {
    }
    
    private static class VisitorIOException extends RuntimeException
    {
        VisitorIOException(final Throwable cause) {
            super(cause);
        }
    }
    
    private class BaseSet extends AbstractSet
    {
        final EntryView view;
        
        public BaseSet(final EntryView view) {
            this.view = view;
        }
        
        public Iterator iterator() {
            return new BaseIterator(this.view);
        }
        
        public boolean contains(final Object o) {
            return this.view.contains(RubyHash.this, o);
        }
        
        public void clear() {
            RubyHash.this.clear();
        }
        
        public int size() {
            return RubyHash.this.size;
        }
        
        public boolean remove(final Object o) {
            return this.view.remove(RubyHash.this, o);
        }
    }
    
    private class BaseCollection extends AbstractCollection
    {
        final EntryView view;
        
        public BaseCollection(final EntryView view) {
            this.view = view;
        }
        
        public Iterator iterator() {
            return new BaseIterator(this.view);
        }
        
        public boolean contains(final Object o) {
            return this.view.contains(RubyHash.this, o);
        }
        
        public void clear() {
            RubyHash.this.clear();
        }
        
        public int size() {
            return RubyHash.this.size;
        }
        
        public boolean remove(final Object o) {
            return this.view.remove(RubyHash.this, o);
        }
    }
    
    private class BaseIterator implements Iterator
    {
        private final EntryView view;
        private RubyHashEntry entry;
        private boolean peeking;
        private int startGeneration;
        
        public BaseIterator(final EntryView view) {
            this.view = view;
            this.entry = RubyHash.this.head;
            this.startGeneration = RubyHash.this.generation;
        }
        
        private void advance(final boolean consume) {
            if (!this.peeking) {
                do {
                    if (this.startGeneration != RubyHash.this.generation) {
                        this.startGeneration = RubyHash.this.generation;
                        this.entry = RubyHash.this.head;
                    }
                    this.entry = this.entry.nextAdded;
                } while (this.entry != RubyHash.this.head && !this.entry.isLive());
            }
            this.peeking = !consume;
        }
        
        public Object next() {
            this.advance(true);
            if (this.entry == RubyHash.this.head) {
                this.peeking = true;
                throw new NoSuchElementException();
            }
            return this.view.convertEntry(RubyHash.this.getRuntime(), this.entry);
        }
        
        public boolean hasNext() {
            this.advance(false);
            return this.entry != RubyHash.this.head;
        }
        
        public void remove() {
            if (this.entry == RubyHash.this.head) {
                throw new IllegalStateException("Iterator out of range");
            }
            RubyHash.this.internalDeleteEntry(this.entry);
        }
    }
    
    private abstract static class EntryView
    {
        public abstract Object convertEntry(final Ruby p0, final RubyHashEntry p1);
        
        public abstract boolean contains(final RubyHash p0, final Object p1);
        
        public abstract boolean remove(final RubyHash p0, final Object p1);
    }
    
    private static class ConvertingEntry implements Entry
    {
        private final RubyHashEntry entry;
        private final Ruby runtime;
        
        public ConvertingEntry(final Ruby runtime, final RubyHashEntry entry) {
            this.entry = entry;
            this.runtime = runtime;
        }
        
        public Object getKey() {
            return this.entry.key.toJava(Object.class);
        }
        
        public Object getValue() {
            return this.entry.value.toJava(Object.class);
        }
        
        public Object setValue(final Object o) {
            return this.entry.setValue(JavaUtil.convertJavaToUsableRubyObject(this.runtime, o));
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof ConvertingEntry)) {
                return false;
            }
            final ConvertingEntry other = (ConvertingEntry)o;
            return this.entry.equals(other.entry);
        }
        
        public int hashCode() {
            return this.entry.hashCode();
        }
    }
}
