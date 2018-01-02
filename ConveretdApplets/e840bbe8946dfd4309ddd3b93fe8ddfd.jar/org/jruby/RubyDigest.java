// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.platform.Platform;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import org.jruby.util.ByteList;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import org.jruby.runtime.builtin.IRubyObject;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "Digest" })
public class RubyDigest
{
    private static Provider provider;
    private static final byte[] digits;
    
    public static void createDigest(final Ruby runtime) {
        RubyDigest.provider = AccessController.doPrivileged((PrivilegedAction<Provider>)new PrivilegedAction() {
            public Object run() {
                try {
                    return Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").newInstance();
                }
                catch (Throwable t) {
                    return null;
                }
            }
        });
        final RubyModule mDigest = runtime.defineModule("Digest");
        mDigest.defineAnnotatedMethods(RubyDigest.class);
        final RubyModule mDigestInstance = mDigest.defineModuleUnder("Instance");
        mDigestInstance.defineAnnotatedMethods(DigestInstance.class);
        final RubyClass cDigestClass = mDigest.defineClassUnder("Class", runtime.getObject(), DigestClass.DIGEST_CLASS_ALLOCATOR);
        cDigestClass.defineAnnotatedMethods(DigestClass.class);
        cDigestClass.includeModule(mDigestInstance);
        final RubyClass cDigestBase = mDigest.defineClassUnder("Base", cDigestClass, DigestBase.DIGEST_BASE_ALLOCATOR);
        cDigestBase.defineAnnotatedMethods(DigestBase.class);
    }
    
    private static MessageDigest createMessageDigest(final Ruby runtime, final String providerName) throws NoSuchAlgorithmException {
        if (RubyDigest.provider != null) {
            try {
                return MessageDigest.getInstance(providerName, RubyDigest.provider);
            }
            catch (NoSuchAlgorithmException ex) {}
        }
        return MessageDigest.getInstance(providerName);
    }
    
    private static ByteList toHex(final byte[] val) {
        final ByteList byteList = new ByteList(val.length * 2);
        for (int i = 0, j = val.length; i < j; ++i) {
            final int b = val[i] & 0xFF;
            byteList.append(RubyDigest.digits[b >> 4]);
            byteList.append(RubyDigest.digits[b & 0xF]);
        }
        return byteList;
    }
    
    private static IRubyObject toHexString(final Ruby runtime, final byte[] val) {
        return RubyString.newStringNoCopy(runtime, ByteList.plain(toHex(val)));
    }
    
    @JRubyMethod(name = { "hexencode" }, required = 1, meta = true)
    public static IRubyObject s_hexencode(final IRubyObject recv, final IRubyObject arg) {
        return toHexString(recv.getRuntime(), arg.convertToString().getBytes());
    }
    
    @JRubyMethod(name = { "const_missing" }, required = 1, module = true)
    public static IRubyObject const_missing(final ThreadContext ctx, final IRubyObject recv, final IRubyObject symbol) {
        final Ruby runtime = ctx.getRuntime();
        final String sym = ((RubySymbol)symbol).asJavaString();
        String libName;
        if ("SHA256".equals(sym) || "SHA384".equals(sym) || "SHA512".equals(sym)) {
            libName = "digest/sha2.jar";
        }
        else {
            libName = "digest/" + sym.toLowerCase();
        }
        runtime.getLoadService().require(libName);
        final RubyModule digest = runtime.getModule("Digest");
        if (!digest.hasConstant(sym)) {
            throw runtime.newNameError("unitialized constant Digest::" + sym, "Digest::" + sym);
        }
        return digest.getConstant(sym);
    }
    
    public static void createDigestMD5(final Ruby runtime) {
        runtime.getLoadService().require("digest.so");
        final RubyModule mDigest = runtime.fastGetModule("Digest");
        final RubyClass cDigestBase = mDigest.fastGetClass("Base");
        final RubyClass cDigest_MD5 = mDigest.defineClassUnder("MD5", cDigestBase, cDigestBase.getAllocator());
        cDigest_MD5.setInternalVariable("metadata", new Metadata("MD5", 64));
    }
    
    public static void createDigestRMD160(final Ruby runtime) {
        runtime.getLoadService().require("digest.so");
        if (RubyDigest.provider == null) {
            throw runtime.newLoadError("RMD160 not supported without BouncyCastle");
        }
        final RubyModule mDigest = runtime.fastGetModule("Digest");
        final RubyClass cDigestBase = mDigest.fastGetClass("Base");
        final RubyClass cDigest_RMD160 = mDigest.defineClassUnder("RMD160", cDigestBase, cDigestBase.getAllocator());
        cDigest_RMD160.setInternalVariable("metadata", new Metadata("RIPEMD160", 64));
    }
    
    public static void createDigestSHA1(final Ruby runtime) {
        runtime.getLoadService().require("digest.so");
        final RubyModule mDigest = runtime.fastGetModule("Digest");
        final RubyClass cDigestBase = mDigest.fastGetClass("Base");
        final RubyClass cDigest_SHA1 = mDigest.defineClassUnder("SHA1", cDigestBase, cDigestBase.getAllocator());
        cDigest_SHA1.setInternalVariable("metadata", new Metadata("SHA1", 64));
    }
    
    public static void createDigestSHA2(final Ruby runtime) {
        runtime.getLoadService().require("digest.so");
        try {
            createMessageDigest(runtime, "SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            throw runtime.newLoadError("SHA2 not supported");
        }
        final RubyModule mDigest = runtime.fastGetModule("Digest");
        final RubyClass cDigestBase = mDigest.fastGetClass("Base");
        final RubyClass cDigest_SHA2_256 = mDigest.defineClassUnder("SHA256", cDigestBase, cDigestBase.getAllocator());
        final Metadata sha256Metadata = new Metadata("SHA-256", 64);
        cDigest_SHA2_256.setInternalVariable("metadata", sha256Metadata);
        final RubyClass cDigest_SHA2_257 = mDigest.defineClassUnder("SHA384", cDigestBase, cDigestBase.getAllocator());
        cDigest_SHA2_257.setInternalVariable("metadata", new Metadata("SHA-384", 128));
        final RubyClass cDigest_SHA2_258 = mDigest.defineClassUnder("SHA512", cDigestBase, cDigestBase.getAllocator());
        cDigest_SHA2_258.setInternalVariable("metadata", new Metadata("SHA-512", 128));
    }
    
    static {
        RubyDigest.provider = null;
        digits = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
    }
    
    private static class Metadata
    {
        private final String name;
        private final int blockLength;
        
        Metadata(final String name, final int blockLength) {
            this.name = name;
            this.blockLength = blockLength;
        }
        
        String getName() {
            return this.name;
        }
        
        int getBlockLength() {
            return this.blockLength;
        }
    }
    
    @JRubyClass(name = { "Digest::MD5" }, parent = "Digest::Base")
    public static class MD5
    {
    }
    
    @JRubyClass(name = { "Digest::RMD160" }, parent = "Digest::Base")
    public static class RMD160
    {
    }
    
    @JRubyClass(name = { "Digest::SHA1" }, parent = "Digest::Base")
    public static class SHA1
    {
    }
    
    @JRubyClass(name = { "Digest::SHA256" }, parent = "Digest::Base")
    public static class SHA256
    {
    }
    
    @JRubyClass(name = { "Digest::SHA384" }, parent = "Digest::Base")
    public static class SHA384
    {
    }
    
    @JRubyClass(name = { "Digest::SHA512" }, parent = "Digest::Base")
    public static class SHA512
    {
    }
    
    @JRubyModule(name = { "Digest::Instance" })
    public static class DigestInstance
    {
        private static IRubyObject throwUnimplError(final IRubyObject self, final String name) {
            throw self.getRuntime().newRuntimeError(String.format("%s does not implement %s()", self.getMetaClass().getRealClass().getName(), name));
        }
        
        @JRubyMethod(name = { "update", "<<" }, required = 1)
        public static IRubyObject update(final ThreadContext ctx, final IRubyObject self, final IRubyObject arg) {
            return throwUnimplError(self, "update");
        }
        
        @JRubyMethod
        public static IRubyObject finish(final ThreadContext ctx, final IRubyObject self) {
            return throwUnimplError(self, "finish");
        }
        
        @JRubyMethod
        public static IRubyObject reset(final ThreadContext ctx, final IRubyObject self) {
            return throwUnimplError(self, "reset");
        }
        
        @JRubyMethod
        public static IRubyObject digest_length(final ThreadContext ctx, final IRubyObject self) {
            return digest(ctx, self, null).convertToString().length();
        }
        
        @JRubyMethod
        public static IRubyObject block_length(final ThreadContext ctx, final IRubyObject self) {
            return throwUnimplError(self, "block_length");
        }
        
        @JRubyMethod(name = { "==" }, required = 1)
        public static IRubyObject op_equal(final ThreadContext ctx, final IRubyObject self, final IRubyObject oth) {
            final RubyModule instance = (RubyModule)self.getRuntime().fastGetModule("Digest").fastGetConstantAt("Instance");
            RubyString str1;
            RubyString str2;
            if (oth.getMetaClass().getRealClass().hasModuleInHierarchy(instance)) {
                str1 = digest(ctx, self, null).convertToString();
                str2 = digest(ctx, oth, null).convertToString();
            }
            else {
                str1 = to_s(ctx, self).convertToString();
                str2 = oth.convertToString();
            }
            final boolean ret = str1.length().eql(str2.length()) && str1.eql(str2);
            return ret ? self.getRuntime().getTrue() : self.getRuntime().getFalse();
        }
        
        @JRubyMethod
        public static IRubyObject inspect(final ThreadContext ctx, final IRubyObject self) {
            return RubyString.newStringNoCopy(self.getRuntime(), ByteList.plain("#<" + self.getMetaClass().getRealClass().getName() + ": " + hexdigest(ctx, self, null) + ">"));
        }
        
        @JRubyMethod(name = { "new" })
        public static IRubyObject newObject(final ThreadContext ctx, final IRubyObject self) {
            return self.rbClone().callMethod(ctx, "reset");
        }
        
        @JRubyMethod(optional = 1)
        public static IRubyObject digest(final ThreadContext ctx, final IRubyObject self, final IRubyObject[] args) {
            IRubyObject value = null;
            if (args != null && args.length > 0) {
                self.callMethod(ctx, "reset");
                self.callMethod(ctx, "update", args[0]);
                value = self.callMethod(ctx, "finish");
                self.callMethod(ctx, "reset");
            }
            else {
                final IRubyObject clone = self.rbClone();
                value = clone.callMethod(ctx, "finish");
                clone.callMethod(ctx, "reset");
            }
            return value;
        }
        
        @JRubyMethod(name = { "digest!" })
        public static IRubyObject digest_bang(final ThreadContext ctx, final IRubyObject self) {
            final IRubyObject value = self.callMethod(ctx, "finish");
            self.callMethod(ctx, "reset");
            return value;
        }
        
        @JRubyMethod(optional = 1)
        public static IRubyObject hexdigest(final ThreadContext ctx, final IRubyObject self, final IRubyObject[] args) {
            return toHexString(ctx.getRuntime(), digest(ctx, self, args).convertToString().getBytes());
        }
        
        @JRubyMethod(name = { "hexdigest!" })
        public static IRubyObject hexdigest_bang(final ThreadContext ctx, final IRubyObject self) {
            return toHexString(ctx.getRuntime(), digest_bang(ctx, self).convertToString().getBytes());
        }
        
        @JRubyMethod
        public static IRubyObject to_s(final ThreadContext ctx, final IRubyObject self) {
            return self.callMethod(ctx, "hexdigest");
        }
        
        @JRubyMethod(name = { "length", "size" })
        public static IRubyObject length(final ThreadContext ctx, final IRubyObject self) {
            return self.callMethod(ctx, "digest_length");
        }
        
        @JRubyMethod
        public static IRubyObject file(final ThreadContext ctx, final IRubyObject self, final IRubyObject filename) {
            final Ruby runtime = self.getRuntime();
            final RubyString filenameStr = filename.convertToString();
            if (!RubyFileTest.directory_p(runtime, filenameStr).isTrue()) {
                final IRubyObject io = RuntimeHelpers.invoke(ctx, runtime.getFile(), "open", filenameStr, runtime.newString("rb"));
                try {
                    final RubyString buf = runtime.newString();
                    final RubyFixnum bufSize = runtime.newFixnum(16384);
                    while (!RuntimeHelpers.invoke(ctx, io, "read", bufSize, buf).isNil()) {
                        self.callMethod(ctx, "update", buf);
                    }
                }
                finally {
                    io.callMethod(ctx, "close");
                }
                return self;
            }
            if (Platform.IS_WINDOWS) {
                throw runtime.newErrnoEACCESError(filenameStr.asJavaString());
            }
            throw runtime.newErrnoEISDirError(filenameStr.asJavaString());
        }
    }
    
    @JRubyClass(name = { "Digest::Class" })
    public static class DigestClass extends RubyObject
    {
        protected static final ObjectAllocator DIGEST_CLASS_ALLOCATOR;
        
        public DigestClass(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
        }
        
        @JRubyMethod(name = { "digest" }, required = 1, rest = true, meta = true)
        public static IRubyObject s_digest(final ThreadContext ctx, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
            final Ruby runtime = recv.getRuntime();
            if (args.length < 1) {
                throw runtime.newArgumentError("no data given");
            }
            final RubyString str = args[0].convertToString();
            final IRubyObject[] newArgs = new IRubyObject[args.length - 1];
            System.arraycopy(args, 1, newArgs, 0, args.length - 1);
            final IRubyObject obj = ((RubyClass)recv).newInstance(ctx, newArgs, Block.NULL_BLOCK);
            return obj.callMethod(ctx, "digest", str);
        }
        
        @JRubyMethod(name = { "hexdigest" }, required = 1, optional = 1, meta = true)
        public static IRubyObject s_hexdigest(final ThreadContext ctx, final IRubyObject recv, final IRubyObject[] args, final Block unusedBlock) {
            final Ruby runtime = recv.getRuntime();
            final byte[] digest = recv.callMethod(ctx, "digest", args, Block.NULL_BLOCK).convertToString().getBytes();
            return toHexString(runtime, digest);
        }
        
        @JRubyMethod(meta = true)
        public static IRubyObject file(final ThreadContext ctx, final IRubyObject recv, final IRubyObject filename) {
            final IRubyObject obj = ((RubyClass)recv).newInstance(ctx, new IRubyObject[0], Block.NULL_BLOCK);
            return obj.callMethod(ctx, "file", filename);
        }
        
        static {
            DIGEST_CLASS_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new DigestClass(runtime, klass);
                }
            };
        }
    }
    
    @JRubyClass(name = { "Digest::Base" })
    public static class DigestBase extends RubyObject
    {
        protected static final ObjectAllocator DIGEST_BASE_ALLOCATOR;
        private MessageDigest algo;
        private int blockLength;
        
        public DigestBase(final Ruby runtime, final RubyClass type) {
            super(runtime, type);
            this.blockLength = 0;
            if (type == runtime.fastGetModule("Digest").fastGetClass("Base")) {
                throw runtime.newNotImplementedError("Digest::Base is an abstract class");
            }
            final Metadata metadata = this.getMetadata(type);
            if (metadata == null) {
                throw runtime.newNotImplementedError("the " + type + "() function is unimplemented on this machine");
            }
            try {
                this.setAlgorithm(metadata);
            }
            catch (NoSuchAlgorithmException e) {
                throw runtime.newNotImplementedError("the " + type + "() function is unimplemented on this machine");
            }
        }
        
        private Metadata getMetadata(final RubyModule type) {
            for (RubyModule current = type; current != null; current = current.getSuperClass()) {
                final Metadata metadata = (Metadata)current.getInternalVariable("metadata");
                if (metadata != null) {
                    return metadata;
                }
            }
            return null;
        }
        
        @JRubyMethod(required = 1)
        public IRubyObject initialize_copy(final IRubyObject obj) {
            if (this == obj) {
                return this;
            }
            ((RubyObject)obj).checkFrozen();
            final String name = ((DigestBase)obj).algo.getAlgorithm();
            try {
                this.algo = (MessageDigest)((DigestBase)obj).algo.clone();
            }
            catch (CloneNotSupportedException e) {
                throw this.getRuntime().newTypeError("Could not initialize copy of digest (" + name + ")");
            }
            return this;
        }
        
        @JRubyMethod(name = { "update", "<<" }, required = 1)
        public IRubyObject update(final IRubyObject obj) {
            final ByteList bytes = obj.convertToString().getByteList();
            this.algo.update(bytes.getUnsafeBytes(), bytes.getBegin(), bytes.getRealSize());
            return this;
        }
        
        @JRubyMethod
        public IRubyObject finish() {
            final IRubyObject digest = RubyString.newStringNoCopy(this.getRuntime(), this.algo.digest());
            this.algo.reset();
            return digest;
        }
        
        @JRubyMethod
        public IRubyObject digest_length() {
            return RubyFixnum.newFixnum(this.getRuntime(), this.algo.getDigestLength());
        }
        
        @JRubyMethod
        public IRubyObject block_length() {
            if (this.blockLength == 0) {
                throw this.getRuntime().newRuntimeError(this.getMetaClass() + " doesn't implement block_length()");
            }
            return RubyFixnum.newFixnum(this.getRuntime(), this.blockLength);
        }
        
        @JRubyMethod
        public IRubyObject reset() {
            this.algo.reset();
            return this;
        }
        
        private void setAlgorithm(final Metadata metadata) throws NoSuchAlgorithmException {
            this.algo = createMessageDigest(this.getRuntime(), metadata.getName());
            this.blockLength = metadata.getBlockLength();
        }
        
        static {
            DIGEST_BASE_ALLOCATOR = new ObjectAllocator() {
                public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                    return new DigestBase(runtime, klass);
                }
            };
        }
    }
}
