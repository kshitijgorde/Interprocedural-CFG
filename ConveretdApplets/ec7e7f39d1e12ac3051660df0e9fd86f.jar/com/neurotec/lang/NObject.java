// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.neurotec.jna.ptr.HNObjectByReference;
import com.neurotec.jna.ptr.HNObject;

public class NObject extends NAbstractDisposable
{
    static final NObjectLibrary LIBRARY;
    public static final NNativeType NATIVE_TYPE;
    private NNativeType nativeType;
    private HNObject handle;
    private boolean ownsHandle;
    private NObject owner;
    
    protected NObject(final HNObject handle, final NNativeType requiredType, final boolean claimHandle) {
        this.handle = handle;
        if (claimHandle) {
            this.ownsHandle = true;
        }
        if (requiredType == null) {
            throw new NullPointerException("requiredType");
        }
        if (HNObject.NULL.equals(handle)) {
            throw new NullPointerException("handle");
        }
        if (!requiredType.isInstanceOfType(handle)) {
            throw new IllegalArgumentException("handle is not an instance of requiredType");
        }
        this.nativeType = getNativeType(handle);
        final HNObjectByReference rhValue = new HNObjectByReference();
        NResult.check(NObject.LIBRARY.NObjectGetOwner(handle, rhValue));
        final HNObject hOwner = rhValue.getValue();
        if (hOwner.getPointer() != null) {
            this.owner = (hOwner.equals(handle) ? this : new NDummyObject(hOwner, this));
        }
    }
    
    public static void copyParameters(final NObject srcObj, final NObject dstObj) {
        if (srcObj == null) {
            throw new IllegalArgumentException("srcObj");
        }
        if (dstObj == null) {
            throw new IllegalArgumentException("dstObj");
        }
        NResult.check(NObject.LIBRARY.NObjectCopyParameters(dstObj.getHandle(), srcObj.getHandle()));
    }
    
    public static void free(final HNObject hObject) {
        NObject.LIBRARY.NObjectFree(hObject);
    }
    
    public static NNativeType getNativeType(final HNObject handle) {
        if (handle == null) {
            throw new NullPointerException("handle");
        }
        final PointerByReference rValue = new PointerByReference();
        NResult.check(NObject.LIBRARY.NObjectGetType(handle, rValue));
        return new NNativeType(new NNativeType.NNativeTypeLibrary.HNType(rValue.getValue()));
    }
    
    protected void dispose(final boolean disposing) {
        if (disposing) {
            final NDummyObject dummyOwner = (NDummyObject)this.owner;
            if (dummyOwner != null) {
                dummyOwner.detachFromSource();
            }
        }
        if (this.ownsHandle) {
            free(this.handle);
        }
        this.handle = null;
        this.owner = null;
    }
    
    protected final void claimHandle() {
        if (this.ownsHandle) {
            throw new UnsupportedOperationException("Handle is already claimed");
        }
        if (this.getHandle().getPointer() == null) {
            throw new UnsupportedOperationException("No handle to claim");
        }
        this.ownsHandle = true;
    }
    
    protected final void embedObject(final NObject obj) {
        this.check();
        if (obj == null) {
            throw new NullPointerException("obj");
        }
        obj.owner = this;
    }
    
    protected final void releaseObjectNoDispose(final NObject obj) {
        this.check();
        if (obj != null) {
            if (obj.owner != this) {
                throw new IllegalArgumentException("The specified obj is not owned by this NObject");
            }
            obj.owner = null;
        }
    }
    
    protected final void releaseObject(final NObject obj) {
        this.releaseObjectNoDispose(obj);
        if (obj != null) {
            obj.dispose();
        }
    }
    
    protected final boolean isOwned() {
        NDummyObject dummy = null;
        if (this instanceof NDummyObject) {
            dummy = (NDummyObject)this;
        }
        return (dummy != null) ? (dummy.getSource() != null) : (this.owner != null && !(this.owner instanceof NDummyObject));
    }
    
    public final HNObject releaseHandle() {
        this.check();
        if (this.owner != null) {
            throw new UnsupportedOperationException("The NObject is owned by another NObject");
        }
        if (!this.ownsHandle) {
            throw new UnsupportedOperationException("The NObject does not own its handle");
        }
        final HNObject handle = this.handle;
        this.ownsHandle = false;
        return handle;
    }
    
    public final NNativeType getNativeType() {
        return this.nativeType;
    }
    
    public final void reset() {
        NResult.check(NObject.LIBRARY.NObjectReset(this.handle));
    }
    
    public final Object getParameter(final short partId, final int parameterId) {
        return this.getParameter(partId, parameterId, null);
    }
    
    public final Object getParameter(final short partId, final int parameterId, final Class parameterClass) {
        return NParameters.getValue(this.getNativeType(), this, partId, parameterId, parameterClass);
    }
    
    public final void setParameter(final short partId, final int parameterId, final Object value) {
        this.setParameter(partId, parameterId, null, value);
    }
    
    public final void setParameter(final short partId, final int parameterId, final Class parameterClass, final Object value) {
        NParameters.setValue(this.getNativeType(), this, partId, parameterId, parameterClass, value);
    }
    
    public final Object getParameter(final int parameterId) {
        return this.getParameter(parameterId, null);
    }
    
    public final Object getParameter(final int parameterId, final Class parameterClass) {
        return NParameters.getValue(this.getNativeType(), this, (short)0, parameterId, parameterClass);
    }
    
    public final void setParameter(final int parameterId, final Object value) {
        this.setParameter(parameterId, null, value);
    }
    
    public final void setParameter(final int parameterId, final Class parameterClass, final Object value) {
        NParameters.setValue(this.getNativeType(), this, (short)0, parameterId, parameterClass, value);
    }
    
    public final HNObject getHandle() {
        this.check();
        return this.handle;
    }
    
    public NObject getOwner() {
        this.check();
        return this.owner;
    }
    
    public final void setOwner(final NObject value) {
        if (value != null) {
            final HNObjectByReference rhOwner = new HNObjectByReference();
            NResult.check(NObject.LIBRARY.NObjectGetOwner(this.handle, rhOwner));
            if (!rhOwner.getValue().equals(value.getHandle())) {
                throw new IllegalArgumentException("The unmanaged NObject wrapped by this is not owned by unmanaged NObject wrapped by value");
            }
        }
        if (this.owner instanceof NDummyObject) {
            final NDummyObject dummyOwner = (NDummyObject)this.owner;
            if (dummyOwner != null) {
                dummyOwner.detachFromSource();
            }
        }
        this.owner = value;
    }
    
    public int getFlags() {
        final IntByReference rValue = new IntByReference();
        NResult.check(NObject.LIBRARY.NObjectGetFlags(this.handle, rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NObjectLibrary)Native.loadLibrary("NCore", NObjectLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        NATIVE_TYPE = new NNativeType(NObject.LIBRARY.NObjectTypeOf());
    }
    
    private class NDummyObject extends NObject
    {
        private NObject source;
        
        public NDummyObject(final HNObject handle, final NObject source) {
            super(handle, NObject.getNativeType(handle), false);
            if (source == null) {
                throw new NullPointerException("source");
            }
            this.source = source;
        }
        
        public NObject getSource() {
            return this.source;
        }
        
        private void detachFromSource() {
            if (this.source != null) {
                this.source = null;
                this.dispose();
            }
        }
    }
    
    public interface NObjectLibrary extends NLibrary
    {
        NNativeType.NNativeTypeLibrary.HNType NObjectTypeOf();
        
        int NObjectCopyParameters(final HNObject p0, final HNObject p1);
        
        void NObjectFree(final HNObject p0);
        
        int NObjectGetType(final HNObject p0, final PointerByReference p1);
        
        int NObjectGetOwner(final HNObject p0, final HNObjectByReference p1);
        
        int NObjectGetFlags(final HNObject p0, final IntByReference p1);
        
        int NObjectReset(final HNObject p0);
        
        public interface NObjectCallback extends StdCallLibrary.StdCallCallback
        {
            void invoke(final HNObject p0, final Pointer p1);
        }
    }
}
