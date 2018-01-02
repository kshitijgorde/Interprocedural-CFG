// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.yecht.KindTag;
import org.jruby.RubyKernel;
import java.util.HashMap;
import org.jruby.util.ByteList;
import org.yecht.ImplicitScanner2;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyClass;
import org.jruby.RubyEnumerable;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import org.jruby.util.TypeConverter;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.runtime.ThreadContext;
import org.yecht.ScalarStyle;
import org.jruby.RubyString;
import org.yecht.Data;
import org.yecht.Node;
import org.jruby.RubyTime;
import org.jruby.RubyNumeric;
import org.jruby.runtime.builtin.IRubyObject;
import org.yecht.Pointer;
import org.jruby.Ruby;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class DefaultResolver
{
    private static Map<String, ObjectCreator> scalarTypes;
    
    private static int extractInt(final byte[] buff, final int p, final int pend) {
        int len;
        for (len = 0; p + len < pend && Character.isDigit((char)buff[p + len]); ++len) {}
        try {
            return Integer.parseInt(new String(buff, p, len, "ISO-8859-1"));
        }
        catch (UnsupportedEncodingException e) {
            return -1;
        }
    }
    
    public static IRubyObject makeTime(final Ruby runtime, final Pointer str, final int len) {
        int ptr = str.start;
        final int pend = ptr + len;
        IRubyObject year = runtime.newFixnum(0);
        IRubyObject mon = runtime.newFixnum(0);
        IRubyObject day = runtime.newFixnum(0);
        IRubyObject hour = runtime.newFixnum(0);
        IRubyObject min = runtime.newFixnum(0);
        IRubyObject sec = runtime.newFixnum(0);
        IRubyObject addSec = runtime.newFixnum(0);
        long usec = 0L;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            year = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 4;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            while (!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) {
                ++ptr;
            }
            mon = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 2;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            while (!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) {
                ++ptr;
            }
            day = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 2;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            while (!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) {
                ++ptr;
            }
            hour = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 2;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            while (!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) {
                ++ptr;
            }
            min = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 2;
        if (str.buffer[ptr] != 0 && ptr < pend) {
            while (!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) {
                ++ptr;
            }
            sec = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }
        ptr += 2;
        if (ptr < pend && str.buffer[ptr] == 46) {
            int end;
            for (end = ptr + 1; Character.isDigit((char)str.buffer[end]) && end < pend; ++end) {}
            final byte[] padded = { 48, 48, 48, 48, 48, 48 };
            int begin = ptr + 1;
            int extraSeconds = 0;
            if (end - begin > 6) {
                extraSeconds = end - begin - 6;
                begin += extraSeconds;
                addSec = runtime.newFixnum(extractInt(str.buffer, begin - extraSeconds, begin));
            }
            System.arraycopy(str.buffer, begin, padded, 0, end - begin);
            try {
                usec = Long.parseLong(new String(padded, 0, 6, "ISO-8859-1"));
            }
            catch (UnsupportedEncodingException ex) {}
        }
        else {
            usec = 0L;
        }
        while (ptr < pend && str.buffer[ptr] != 90 && str.buffer[ptr] != 43 && str.buffer[ptr] != 45 && str.buffer[ptr] != 0) {
            ++ptr;
        }
        if (ptr < pend && (str.buffer[ptr] == 45 || str.buffer[ptr] == 43)) {
            int lenx;
            for (lenx = 1; ptr + lenx < pend && Character.isDigit((char)str.buffer[ptr + lenx]); ++lenx) {}
            if (str.buffer[ptr] == 43) {
                ++ptr;
                --lenx;
            }
            Label_1034: {
                try {
                    long tz_offset = Long.parseLong(new String(str.buffer, ptr, lenx, "ISO-8859-1")) * 3600L;
                    for (ptr += lenx; ptr < pend && str.buffer[ptr] != 58 && str.buffer[ptr] != 0; ++ptr) {}
                    if (ptr < pend && str.buffer[ptr] == 58) {
                        ++ptr;
                        if (tz_offset < 0L) {
                            tz_offset -= extractInt(str.buffer, ptr, pend) * 60;
                        }
                        else {
                            tz_offset += extractInt(str.buffer, ptr, pend) * 60;
                        }
                    }
                    final IRubyObject time = runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "utc", new IRubyObject[] { year, mon, day, hour, min, sec });
                    final long tmp = RubyNumeric.num2long(time.callMethod(runtime.getCurrentContext(), "to_i")) - tz_offset;
                    return ((RubyTime)runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "at", new IRubyObject[] { runtime.newFixnum(tmp), runtime.newFixnum(usec) })).op_plus(addSec);
                }
                catch (UnsupportedEncodingException e) {
                    break Label_1034;
                }
                return ((RubyTime)runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "utc", new IRubyObject[] { year, mon, day, hour, min, sec, runtime.newFixnum(usec) })).op_plus(addSec);
            }
            System.err.println("oopsie, returning null");
            return null;
        }
        return ((RubyTime)runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "utc", new IRubyObject[] { year, mon, day, hour, min, sec, runtime.newFixnum(usec) })).op_plus(addSec);
    }
    
    private static boolean handleScalar(final Ruby runtime, final Node n, final String type_id, final IRubyObject[] ref, final YAMLExtra x) throws UnsupportedEncodingException {
        final Data.Str ds = (Data.Str)n.data;
        final ThreadContext ctx = runtime.getCurrentContext();
        boolean transferred = true;
        IRubyObject obj = null;
        if (type_id == null) {
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
        }
        else if (ds.style == ScalarStyle.Plain && ds.len > 1 && ds.ptr.buffer[ds.ptr.start] == 58) {
            obj = x.DefaultResolver.callMethod(ctx, "transfer", new IRubyObject[] { runtime.newString("tag:ruby.yaml.org,2002:sym"), RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start + 1, ds.len - 1) });
        }
        else {
            final ObjectCreator oc = DefaultResolver.scalarTypes.get(type_id);
            if (oc != null) {
                obj = oc.create(runtime, n, ds);
            }
            else if (type_id.startsWith("int")) {
                n.strBlowAwayCommas();
                obj = RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len), 10, true);
            }
            else if (type_id.startsWith("float")) {
                n.strBlowAwayCommas();
                obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
                obj = obj.callMethod(ctx, "to_f");
            }
            else if (type_id.startsWith("timestamp")) {
                obj = makeTime(runtime, ds.ptr, ds.len);
            }
            else if (type_id.startsWith("merge")) {
                obj = x.MergeKey.callMethod(ctx, "new");
            }
            else if (type_id.startsWith("default")) {
                obj = x.DefaultKey.callMethod(ctx, "new");
            }
            else {
                transferred = false;
                obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
            }
        }
        ref[0] = obj;
        return transferred;
    }
    
    public static boolean handleSeq(final Ruby runtime, final Node n, final String type_id, final IRubyObject[] ref) {
        final boolean transferred = type_id == null || "seq".equals(type_id);
        final Data.Seq dl = (Data.Seq)n.data;
        final Object[] items = dl.items;
        final RubyArray obj = RubyArray.newArray(runtime, dl.idx);
        for (int i = 0; i < dl.idx; ++i) {
            final IRubyObject _obj = (IRubyObject)items[i];
            if (_obj instanceof PossibleLinkNode) {
                ((PossibleLinkNode)_obj).addLink(new ArrayStorageLink(obj, i, _obj));
            }
            obj.store(i, _obj);
        }
        ref[0] = obj;
        return transferred;
    }
    
    public static boolean handleMap(final Ruby runtime, final Node n, final String type_id, final IRubyObject[] ref, final YAMLExtra x) {
        final boolean transferred = type_id == null || "map".equals(type_id);
        final ThreadContext ctx = runtime.getCurrentContext();
        final Data.Map dm = (Data.Map)n.data;
        final Object[] keys = dm.keys;
        final Object[] vals = dm.values;
        RubyHash obj = RubyHash.newHash(runtime);
        final RubyClass cMergeKey = x.MergeKey;
        final RubyClass cDefaultKey = x.DefaultKey;
        for (int i = 0; i < dm.idx; ++i) {
            final IRubyObject k = (IRubyObject)keys[i];
            IRubyObject v = (IRubyObject)vals[i];
            if (null == v) {
                v = runtime.getNil();
            }
            boolean skip_aset = false;
            if (cMergeKey.isInstance(k)) {
                IRubyObject tmp = null;
                if (!(tmp = TypeConverter.convertToTypeWithCheck(v, runtime.getHash(), "to_hash")).isNil()) {
                    final RubyHash dup = (RubyHash)v.callMethod(ctx, "dup");
                    dup.callMethod(ctx, "update", obj);
                    obj = dup;
                    skip_aset = true;
                }
                else if (!(tmp = v.checkArrayType()).isNil()) {
                    final IRubyObject end = ((RubyArray)tmp).pop(ctx);
                    final IRubyObject tmph = TypeConverter.convertToTypeWithCheck(end, runtime.getHash(), "to_hash");
                    if (!tmph.isNil()) {
                        final RubyHash dup2 = (RubyHash)tmph.callMethod(ctx, "dup");
                        tmp = ((RubyArray)tmp).reverse();
                        ((RubyArray)tmp).append(obj);
                        RubyEnumerable.callEach(runtime, ctx, tmp, new BlockCallback() {
                            public IRubyObject call(final ThreadContext _ctx, final IRubyObject[] largs, final Block blk) {
                                final IRubyObject entry = largs[0];
                                IRubyObject tmp = null;
                                if (!(tmp = TypeConverter.convertToTypeWithCheck(entry, runtime.getHash(), "to_hash")).isNil()) {
                                    dup2.callMethod(_ctx, "update", tmp);
                                }
                                return runtime.getNil();
                            }
                        });
                        obj = dup2;
                        skip_aset = true;
                    }
                }
            }
            else if (cDefaultKey.isInstance(k)) {
                obj.callMethod(ctx, "default=", v);
                skip_aset = true;
            }
            if (!skip_aset) {
                if (v instanceof PossibleLinkNode) {
                    ((PossibleLinkNode)v).addLink(new HashStorageLink(obj, k, v));
                }
                obj.fastASet(k, v);
            }
        }
        ref[0] = obj;
        return transferred;
    }
    
    public static boolean orgHandler(final IRubyObject self, final Node n, final IRubyObject[] ref, final YAMLExtra x) {
        final Ruby runtime = self.getRuntime();
        String type_id = n.type_id;
        boolean transferred = false;
        if (type_id != null && type_id.startsWith("tag:yaml.org,2002:")) {
            type_id = type_id.substring(18);
        }
        try {
            switch (n.kind) {
                case Str: {
                    transferred = handleScalar(runtime, n, type_id, ref, x);
                    break;
                }
                case Seq: {
                    transferred = handleSeq(runtime, n, type_id, ref);
                    break;
                }
                case Map: {
                    transferred = handleMap(runtime, n, type_id, ref, x);
                    break;
                }
            }
        }
        catch (UnsupportedEncodingException ex) {}
        return transferred;
    }
    
    @JRubyMethod
    public static IRubyObject node_import(final IRubyObject self, final IRubyObject node) {
        final Node n = (Node)node.dataGetStructChecked();
        final IRubyObject[] _obj = { null };
        if (!orgHandler(self, n, _obj, ((org.yecht.ruby.Node)node).x)) {
            _obj[0] = self.callMethod(self.getRuntime().getCurrentContext(), "transfer", new IRubyObject[] { self.getRuntime().newString(n.type_id), _obj[0] });
        }
        return _obj[0];
    }
    
    @JRubyMethod
    public static IRubyObject detect_implicit(final IRubyObject self, final IRubyObject val) {
        final IRubyObject tmp = TypeConverter.convertToTypeWithCheck(val, self.getRuntime().getString(), "to_str");
        if (!tmp.isNil()) {
            final ByteList bl = ((RubyString)tmp).getByteList();
            final String type_id = ImplicitScanner2.matchImplicit(Pointer.create(bl.bytes, bl.begin), bl.realSize);
            return self.getRuntime().newString(type_id);
        }
        return RubyString.newEmptyString(self.getRuntime());
    }
    
    static {
        (DefaultResolver.scalarTypes = new HashMap<String, ObjectCreator>()).put("null", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.getNil();
            }
        });
        DefaultResolver.scalarTypes.put("binary", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                final ThreadContext ctx = runtime.getCurrentContext();
                final IRubyObject obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
                obj.callMethod(ctx, "tr!", new IRubyObject[] { runtime.newString("\n\t "), runtime.newString("") });
                final IRubyObject arr = obj.callMethod(ctx, "unpack", runtime.newString("m"));
                return ((RubyArray)arr).shift(ctx);
            }
        });
        DefaultResolver.scalarTypes.put("bool#yes", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.getTrue();
            }
        });
        DefaultResolver.scalarTypes.put("bool#no", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.getFalse();
            }
        });
        DefaultResolver.scalarTypes.put("int#hex", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                n.strBlowAwayCommas();
                return RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len), 16, true);
            }
        });
        DefaultResolver.scalarTypes.put("int#oct", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                n.strBlowAwayCommas();
                return RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len), 8, true);
            }
        });
        DefaultResolver.scalarTypes.put("int#base60", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) throws UnsupportedEncodingException {
                long sixty = 1L;
                long total = 0L;
                n.strBlowAwayCommas();
                int colon;
                for (int ptr = ds.ptr.start, end = ptr + ds.len; end > ptr; end = colon) {
                    long bnum = 0L;
                    for (colon = end - 1; colon >= ptr && ds.ptr.buffer[colon] != 58; --colon) {}
                    bnum = Integer.parseInt(new String(ds.ptr.buffer, colon + 1, end - (colon + 1), "ISO-8859-1"));
                    total += bnum * sixty;
                    sixty *= 60L;
                }
                return runtime.newFixnum(total);
            }
        });
        DefaultResolver.scalarTypes.put("float#base60", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) throws UnsupportedEncodingException {
                long sixty = 1L;
                double total = 0.0;
                n.strBlowAwayCommas();
                int colon;
                for (int ptr = ds.ptr.start, end = ptr + ds.len; end > ptr; end = colon) {
                    double bnum = 0.0;
                    for (colon = end - 1; colon >= ptr && ds.ptr.buffer[colon] != 58; --colon) {}
                    bnum = Double.parseDouble(new String(ds.ptr.buffer, colon + 1, end - (colon + 1), "ISO-8859-1"));
                    total += bnum * sixty;
                    sixty *= 60L;
                }
                return runtime.newFloat(total);
            }
        });
        DefaultResolver.scalarTypes.put("float#nan", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.newFloat(Double.NaN);
            }
        });
        DefaultResolver.scalarTypes.put("float#inf", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.newFloat(Double.POSITIVE_INFINITY);
            }
        });
        DefaultResolver.scalarTypes.put("float#neginf", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return runtime.newFloat(Double.NEGATIVE_INFINITY);
            }
        });
        DefaultResolver.scalarTypes.put("timestamp#iso8601", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return DefaultResolver.makeTime(runtime, ds.ptr, ds.len);
            }
        });
        DefaultResolver.scalarTypes.put("timestamp#spaced", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return DefaultResolver.makeTime(runtime, ds.ptr, ds.len);
            }
        });
        DefaultResolver.scalarTypes.put("timestamp#ymd", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) throws UnsupportedEncodingException {
                final IRubyObject year = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 0, 4, "ISO-8859-1")));
                final IRubyObject mon = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 5, 2, "ISO-8859-1")));
                final IRubyObject day = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 8, 2, "ISO-8859-1")));
                RubyKernel.require(runtime.getTopSelf(), runtime.newString("date"), Block.NULL_BLOCK);
                return runtime.getClass("Date").callMethod(runtime.getCurrentContext(), "new", new IRubyObject[] { year, mon, day });
            }
        });
        DefaultResolver.scalarTypes.put("str", new ObjectCreator() {
            public IRubyObject create(final Ruby runtime, final Node n, final Data.Str ds) {
                return RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
            }
        });
    }
    
    private interface ObjectCreator
    {
        IRubyObject create(final Ruby p0, final Node p1, final Data.Str p2) throws UnsupportedEncodingException;
    }
}
