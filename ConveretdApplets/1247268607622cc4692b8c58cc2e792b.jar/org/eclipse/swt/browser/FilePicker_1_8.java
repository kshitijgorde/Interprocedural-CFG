// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class FilePicker_1_8 extends FilePicker
{
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return FilePicker_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePicker_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePicker_1_8.this.Release();
            }
        };
        this.filePicker = new XPCOMObject(new int[] { 2, 0, 0, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }) {
            public int method0(final int[] array) {
                return FilePicker_1_8.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return FilePicker_1_8.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return FilePicker_1_8.this.Release();
            }
            
            public int method3(final int[] array) {
                return FilePicker_1_8.this.Init(array[0], array[1], (short)array[2]);
            }
            
            public int method4(final int[] array) {
                return FilePicker_1_8.this.AppendFilters(array[0]);
            }
            
            public int method5(final int[] array) {
                return FilePicker_1_8.this.AppendFilter(array[0], array[1]);
            }
            
            public int method6(final int[] array) {
                return FilePicker_1_8.this.GetDefaultString(array[0]);
            }
            
            public int method7(final int[] array) {
                return FilePicker_1_8.this.SetDefaultString(array[0]);
            }
            
            public int method8(final int[] array) {
                return FilePicker_1_8.this.GetDefaultExtension(array[0]);
            }
            
            public int method9(final int[] array) {
                return FilePicker_1_8.this.SetDefaultExtension(array[0]);
            }
            
            public int method10(final int[] array) {
                return FilePicker_1_8.this.GetFilterIndex(array[0]);
            }
            
            public int method11(final int[] array) {
                return FilePicker_1_8.this.SetFilterIndex(array[0]);
            }
            
            public int method12(final int[] array) {
                return FilePicker_1_8.this.GetDisplayDirectory(array[0]);
            }
            
            public int method13(final int[] array) {
                return FilePicker_1_8.this.SetDisplayDirectory(array[0]);
            }
            
            public int method14(final int[] array) {
                return FilePicker_1_8.this.GetFile(array[0]);
            }
            
            public int method15(final int[] array) {
                return FilePicker_1_8.this.GetFileURL(array[0]);
            }
            
            public int method16(final int[] array) {
                return FilePicker_1_8.this.GetFiles(array[0]);
            }
            
            public int method17(final int[] array) {
                return FilePicker_1_8.this.Show(array[0]);
            }
        };
    }
    
    String parseAString(final int n) {
        if (n == 0) {
            return null;
        }
        final int nsEmbedString_Length = XPCOM.nsEmbedString_Length(n);
        final int nsEmbedString_get = XPCOM.nsEmbedString_get(n);
        final char[] array = new char[nsEmbedString_Length];
        C.memmove(array, nsEmbedString_get, nsEmbedString_Length * 2);
        return new String(array);
    }
}
