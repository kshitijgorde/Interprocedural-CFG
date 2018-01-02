// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class FilePickerFactory_1_8 extends FilePickerFactory
{
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return FilePickerFactory_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePickerFactory_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePickerFactory_1_8.this.Release();
            }
        };
        this.factory = new XPCOMObject(new int[] { 2, 0, 0, 3, 1 }) {
            public int method0(final int[] array) {
                return FilePickerFactory_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePickerFactory_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePickerFactory_1_8.this.Release();
            }
            
            public int method3(final int[] array) {
                return FilePickerFactory_1_8.this.CreateInstance(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return FilePickerFactory_1_8.this.LockFactory(array[0]);
            }
        };
    }
    
    int CreateInstance(final int n, final int n2, final int n3) {
        final FilePicker_1_8 filePicker_1_8 = new FilePicker_1_8();
        filePicker_1_8.AddRef();
        C.memmove(n3, new int[] { filePicker_1_8.getAddress() }, C.PTR_SIZEOF);
        return 0;
    }
}
