// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.gdip.Gdip;

public class Transform extends Resource
{
    public int handle;
    
    public Transform(final Device device) {
        this(device, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    public Transform(final Device device, final float[] array) {
        this(device, checkTransform(array)[0], array[1], array[2], array[3], array[4], array[5]);
    }
    
    public Transform(final Device device, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        super(device);
        this.device.checkGDIP();
        this.handle = Gdip.Matrix_new(n, n2, n3, n4, n5, n6);
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    static float[] checkTransform(final float[] array) {
        if (array == null) {
            SWT.error(4);
        }
        if (array.length < 6) {
            SWT.error(5);
        }
        return array;
    }
    
    void destroy() {
        Gdip.Matrix_delete(this.handle);
        this.handle = 0;
    }
    
    public void getElements(final float[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        if (array.length < 6) {
            SWT.error(5);
        }
        Gdip.Matrix_GetElements(this.handle, array);
    }
    
    public void identity() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_SetElements(this.handle, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    public void invert() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (Gdip.Matrix_Invert(this.handle) != 0) {
            SWT.error(10);
        }
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public boolean isIdentity() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return Gdip.Matrix_IsIdentity(this.handle);
    }
    
    public void multiply(final Transform transform) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (transform == null) {
            SWT.error(4);
        }
        if (transform.isDisposed()) {
            SWT.error(5);
        }
        Gdip.Matrix_Multiply(this.handle, transform.handle, 0);
    }
    
    public void rotate(final float n) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Rotate(this.handle, n, 0);
    }
    
    public void scale(final float n, final float n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Scale(this.handle, n, n2, 0);
    }
    
    public void setElements(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_SetElements(this.handle, n, n2, n3, n4, n5, n6);
    }
    
    public void shear(final float n, final float n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Shear(this.handle, n, n2, 0);
    }
    
    public void transform(final float[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        Gdip.Matrix_TransformPoints(this.handle, array, array.length / 2);
    }
    
    public void translate(final float n, final float n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.Matrix_Translate(this.handle, n, n2, 0);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Transform {*DISPOSED*}";
        }
        final float[] array = new float[6];
        this.getElements(array);
        return "Transform {" + array[0] + "," + array[1] + "," + array[2] + "," + array[3] + "," + array[4] + "," + array[5] + "}";
    }
}
