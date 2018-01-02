// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht.ruby;

import org.yecht.KindTag;
import org.yecht.ImplicitScanner;
import org.jruby.util.TypeConverter;
import org.yecht.MapPart;
import org.yecht.Data;
import org.yecht.Node;
import org.jruby.RubyEnumerable;
import java.util.List;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import org.jruby.runtime.BlockCallback;
import org.jruby.RubyClass;
import org.jruby.RubyNumeric;
import org.jruby.anno.JRubyMethod;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.jruby.RubyArray;
import org.jruby.RubyModule;
import org.jruby.RubyString;
import org.jruby.runtime.builtin.IRubyObject;

public class Resolver
{
    public static IRubyObject const_find(final IRubyObject self, final IRubyObject const_name) {
        RubyModule tclass = self.getRuntime().getObject();
        final RubyArray tparts = ((RubyString)const_name).split(self.getRuntime().getCurrentContext(), self.getRuntime().newString("::"));
        for (int i = 0; i < tparts.getLength(); ++i) {
            final String tpart = tparts.entry(i).toString();
            try {
                tclass = (RubyModule)tclass.getConstant(tpart);
            }
            catch (Exception e) {
                return self.getRuntime().getNil();
            }
        }
        return tclass;
    }
    
    @JRubyMethod
    public static IRubyObject initialize(final IRubyObject self) {
        ((RubyObject)self).fastSetInstanceVariable("@tags", RubyHash.newHash(self.getRuntime()));
        return self;
    }
    
    @JRubyMethod
    public static IRubyObject add_type(final IRubyObject self, final IRubyObject taguri, final IRubyObject cls) {
        final IRubyObject tags = self.callMethod(self.getRuntime().getCurrentContext(), "tags");
        ((RubyHash)tags).fastASet(taguri, cls);
        return self.getRuntime().getNil();
    }
    
    @JRubyMethod
    public static IRubyObject use_types_at(final IRubyObject self, final IRubyObject hsh) {
        ((RubyObject)self).fastSetInstanceVariable("@tags", hsh);
        return self.getRuntime().getNil();
    }
    
    @JRubyMethod
    public static IRubyObject detect_implicit(final IRubyObject self, final IRubyObject val) {
        return RubyString.newEmptyString(self.getRuntime());
    }
    
    @JRubyMethod
    public static IRubyObject transfer(final IRubyObject self, IRubyObject type, IRubyObject val) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        if (type.isNil() || type.convertToString().getByteList().realSize == 0) {
            type = self.callMethod(ctx, "detect_implicit", val);
        }
        if (!type.isNil() && type.convertToString().getByteList().realSize != 0) {
            final IRubyObject colon = runtime.newString(":");
            final IRubyObject tags = self.callMethod(ctx, "tags");
            IRubyObject subclass;
            IRubyObject target_class = subclass = ((RubyHash)tags).op_aref(ctx, type);
            IRubyObject obj = runtime.getNil();
            if (target_class.isNil()) {
                final RubyArray subclass_parts = runtime.newArray();
                final RubyArray parts = ((RubyString)type).split(ctx, colon);
                while (parts.getLength() > 1) {
                    subclass_parts.unshift(parts.pop(ctx));
                    final IRubyObject partial = parts.join(ctx, colon);
                    target_class = ((RubyHash)tags).op_aref(ctx, partial);
                    if (target_class.isNil()) {
                        ((RubyString)partial).append(colon);
                        target_class = ((RubyHash)tags).op_aref(ctx, partial);
                    }
                    if (!target_class.isNil()) {
                        subclass = target_class;
                        if (subclass_parts.getLength() > 0 && target_class.respondsTo("yaml_tag_subclasses?") && target_class.callMethod(ctx, "yaml_tag_subclasses?").isTrue()) {
                            subclass = subclass_parts.join(ctx, colon);
                            subclass = target_class.callMethod(ctx, "yaml_tag_read_class", subclass);
                            final IRubyObject subclass_v = const_find(self, subclass);
                            if (subclass_v != runtime.getNil()) {
                                subclass = subclass_v;
                            }
                            else {
                                if (target_class != runtime.getObject() || subclass_v != runtime.getNil()) {
                                    throw runtime.newTypeError("invalid subclass");
                                }
                                target_class = runtime.getModule("YAML").getConstant("Object");
                                type = subclass;
                                subclass = target_class;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
            if (target_class.respondsTo("call")) {
                obj = target_class.callMethod(ctx, "call", new IRubyObject[] { type, val });
            }
            else if (target_class.respondsTo("yaml_new")) {
                obj = target_class.callMethod(ctx, "yaml_new", new IRubyObject[] { subclass, type, val });
            }
            else if (!target_class.isNil()) {
                if (subclass == runtime.getBignum()) {
                    obj = RubyNumeric.str2inum(runtime, val.convertToString(), 10);
                }
                else {
                    obj = ((RubyClass)subclass).allocate();
                }
                if (obj.respondsTo("yaml_initialize")) {
                    obj.callMethod(ctx, "yaml_initialize", new IRubyObject[] { type, val });
                }
                else if (!obj.isNil() && val instanceof RubyHash) {
                    final IRubyObject _obj = obj;
                    final IRubyObject _val = val;
                    RubyEnumerable.callEach(runtime, ctx, val, new BlockCallback() {
                        public IRubyObject call(final ThreadContext _ctx, final IRubyObject[] largs, final Block blk) {
                            final IRubyObject ivname = ((RubyArray)largs[0]).entry(0);
                            final String ivn = "@" + ivname.convertToString().toString();
                            final IRubyObject valueToSet = ((RubyArray)largs[0]).entry(1);
                            if (valueToSet instanceof PossibleLinkNode) {
                                final List<StorageLink> sls = ((PossibleLinkNode)valueToSet).getLinks();
                                for (int i = 0, j = sls.size(); i < j; ++i) {
                                    final StorageLink sl = sls.get(i);
                                    if (sl instanceof HashStorageLink && ((HashStorageLink)sl).hash == _val) {
                                        sls.set(i, new ObjectStorageLink(_obj, ivn, valueToSet));
                                    }
                                }
                            }
                            _obj.getInstanceVariables().setInstanceVariable(ivn, valueToSet);
                            return runtime.getNil();
                        }
                    });
                }
            }
            else {
                final RubyArray parts2 = ((RubyString)type).split(ctx, colon);
                final IRubyObject scheme = parts2.shift(ctx);
                if (scheme.convertToString().toString().equals("x-private")) {
                    final IRubyObject name = parts2.join(ctx, colon);
                    obj = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("PrivateType").callMethod(ctx, "new", new IRubyObject[] { name, val });
                }
                else {
                    final IRubyObject domain = parts2.shift(ctx);
                    final IRubyObject name2 = parts2.join(ctx, colon);
                    obj = ((RubyModule)runtime.getModule("YAML").getConstant("Yecht")).getConstant("DomainType").callMethod(ctx, "new", new IRubyObject[] { domain, name2, val });
                }
            }
            val = obj;
        }
        return val;
    }
    
    @JRubyMethod
    public static IRubyObject node_import(final IRubyObject self, final IRubyObject node) {
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        final Node n = (Node)node.dataGetStructChecked();
        final YAMLExtra x = ((org.yecht.ruby.Node)node).x;
        IRubyObject obj = null;
        switch (n.kind) {
            case Str: {
                final Data.Str dd = (Data.Str)n.data;
                obj = RubyString.newStringShared(runtime, dd.ptr.buffer, dd.ptr.start, dd.len);
                break;
            }
            case Seq: {
                final Data.Seq ds = (Data.Seq)n.data;
                obj = RubyArray.newArray(runtime, ds.idx);
                for (int i = 0; i < ds.idx; ++i) {
                    final IRubyObject obj2 = (IRubyObject)n.seqRead(i);
                    ((RubyArray)obj).store(i, obj2);
                }
                break;
            }
            case Map: {
                final Data.Map dm = (Data.Map)n.data;
                obj = RubyHash.newHash(runtime);
                final RubyClass cMergeKey = x.MergeKey;
                final RubyClass cDefaultKey = x.DefaultKey;
                final RubyClass cHash = runtime.getHash();
                final RubyClass cArray = runtime.getArray();
                for (int j = 0; j < dm.idx; ++j) {
                    final IRubyObject k = (IRubyObject)n.mapRead(MapPart.Key, j);
                    IRubyObject v = (IRubyObject)n.mapRead(MapPart.Value, j);
                    if (null == v) {
                        v = runtime.getNil();
                    }
                    boolean skip_aset = false;
                    if (cMergeKey.isInstance(k)) {
                        if (cHash.isInstance(v)) {
                            final IRubyObject dup = v.callMethod(ctx, "dup");
                            dup.callMethod(ctx, "update", obj);
                            obj = dup;
                            skip_aset = true;
                        }
                        else if (cArray.isInstance(v)) {
                            final IRubyObject end = ((RubyArray)v).pop(ctx);
                            if (cHash.isInstance(end)) {
                                final IRubyObject dup2 = end.callMethod(ctx, "dup");
                                v = ((RubyArray)v).reverse();
                                ((RubyArray)v).append(obj);
                                RubyEnumerable.callEach(runtime, ctx, v, new BlockCallback() {
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
                        ((RubyHash)obj).fastASet(k, v);
                    }
                }
                break;
            }
        }
        if (n.type_id != null) {
            obj = self.callMethod(ctx, "transfer", new IRubyObject[] { runtime.newString(n.type_id), obj });
        }
        return obj;
    }
    
    @JRubyMethod
    public static IRubyObject tagurize(final IRubyObject self, IRubyObject val) {
        final IRubyObject tmp = val.checkStringType();
        if (!tmp.isNil()) {
            final String taguri = ImplicitScanner.typeIdToUri(tmp.toString());
            val = self.getRuntime().newString(taguri);
        }
        return val;
    }
}
