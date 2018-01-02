// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;

public class HTMLTransfer extends ByteArrayTransfer
{
    static HTMLTransfer _instance;
    static final String HTML_FORMAT = "HTML Format";
    static final int HTML_FORMATID;
    static final String NUMBER = "00000000";
    static final String HEADER = "Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n";
    static final String PREFIX = "<html><body><!--StartFragment-->";
    static final String SUFFIX = "<!--EndFragment--></body></html>";
    static final String StartFragment = "StartFragment:";
    static final String EndFragment = "EndFragment:";
    
    static {
        HTMLTransfer._instance = new HTMLTransfer();
        HTML_FORMATID = Transfer.registerType("HTML Format");
    }
    
    public static HTMLTransfer getInstance() {
        return HTMLTransfer._instance;
    }
    
    public void javaToNative(final Object o, final TransferData transferData) {
        if (!this.checkHTML(o) || !this.isSupportedType(transferData)) {
            DND.error(2003);
        }
        final String s = (String)o;
        final int length = s.length();
        final char[] array = new char[length + 1];
        s.getChars(0, length, array, 0);
        final int wideCharToMultiByte = OS.WideCharToMultiByte(65001, 0, array, -1, null, 0, null, null);
        if (wideCharToMultiByte == 0) {
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = -2147221402;
            return;
        }
        final int length2 = "Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n".length();
        final int n = length2 + "<html><body><!--StartFragment-->".length();
        final int n2 = n + wideCharToMultiByte - 1;
        final int n3 = n2 + "<!--EndFragment--></body></html>".length();
        final StringBuffer sb = new StringBuffer("Version:0.9\r\nStartHTML:00000000\r\nEndHTML:00000000\r\nStartFragment:00000000\r\nEndFragment:00000000\r\n");
        final int length3 = "00000000".length();
        final int index = sb.toString().indexOf("00000000");
        final String string = Integer.toString(length2);
        sb.replace(index + length3 - string.length(), index + length3, string);
        final int index2 = sb.toString().indexOf("00000000", index);
        final String string2 = Integer.toString(n3);
        sb.replace(index2 + length3 - string2.length(), index2 + length3, string2);
        final int index3 = sb.toString().indexOf("00000000", index2);
        final String string3 = Integer.toString(n);
        sb.replace(index3 + length3 - string3.length(), index3 + length3, string3);
        final int index4 = sb.toString().indexOf("00000000", index3);
        final String string4 = Integer.toString(n2);
        sb.replace(index4 + length3 - string4.length(), index4 + length3, string4);
        sb.append("<html><body><!--StartFragment-->");
        sb.append(s);
        sb.append("<!--EndFragment--></body></html>");
        final int length4 = sb.length();
        final char[] array2 = new char[length4 + 1];
        sb.getChars(0, length4, array2, 0);
        final int wideCharToMultiByte2 = OS.WideCharToMultiByte(65001, 0, array2, -1, null, 0, null, null);
        final int globalAlloc = OS.GlobalAlloc(64, wideCharToMultiByte2);
        OS.WideCharToMultiByte(65001, 0, array2, -1, globalAlloc, wideCharToMultiByte2, null, null);
        transferData.stgmedium = new STGMEDIUM();
        transferData.stgmedium.tymed = 1;
        transferData.stgmedium.unionField = globalAlloc;
        transferData.stgmedium.pUnkForRelease = 0;
        transferData.result = 0;
    }
    
    public Object nativeToJava(final TransferData transferData) {
        if (!this.isSupportedType(transferData) || transferData.pIDataObject == 0) {
            return null;
        }
        final IDataObject dataObject = new IDataObject(transferData.pIDataObject);
        dataObject.AddRef();
        final STGMEDIUM stgmedium = new STGMEDIUM();
        final FORMATETC formatetc = transferData.formatetc;
        stgmedium.tymed = 1;
        transferData.result = this.getData(dataObject, formatetc, stgmedium);
        dataObject.Release();
        if (transferData.result != 0) {
            return null;
        }
        final int unionField = stgmedium.unionField;
        try {
            final int globalLock = OS.GlobalLock(unionField);
            if (globalLock != 0) {
                try {
                    final int multiByteToWideChar = OS.MultiByteToWideChar(65001, 0, globalLock, -1, null, 0);
                    if (multiByteToWideChar != 0) {
                        final char[] array = new char[multiByteToWideChar - 1];
                        OS.MultiByteToWideChar(65001, 0, globalLock, -1, array, array.length);
                        final String s = new String(array);
                        int int1 = 0;
                        int int2 = 0;
                        final int n = s.indexOf("StartFragment:") + "StartFragment:".length();
                        int i = n + 1;
                        while (i < s.length()) {
                            final String substring = s.substring(n, i);
                            try {
                                int1 = Integer.parseInt(substring);
                                ++i;
                            }
                            catch (NumberFormatException ex) {
                                break;
                            }
                        }
                        final int n2 = s.indexOf("EndFragment:") + "EndFragment:".length();
                        int j = n2 + 1;
                        while (j < s.length()) {
                            final String substring2 = s.substring(n2, j);
                            try {
                                int2 = Integer.parseInt(substring2);
                                ++j;
                            }
                            catch (NumberFormatException ex2) {
                                break;
                            }
                        }
                        if (int2 > int1) {
                            if (int2 <= C.strlen(globalLock)) {
                                final int multiByteToWideChar2 = OS.MultiByteToWideChar(65001, 0, globalLock + int1, int2 - int1, array, array.length);
                                if (multiByteToWideChar2 != 0) {
                                    String substring3 = new String(array, 0, multiByteToWideChar2);
                                    final String s2 = "<!--StartFragment -->\r\n";
                                    final int index = substring3.indexOf(s2);
                                    if (index != -1) {
                                        substring3 = substring3.substring(index + s2.length());
                                    }
                                    return substring3;
                                }
                            }
                        }
                    }
                }
                finally {
                    OS.GlobalUnlock(unionField);
                }
            }
            return null;
        }
        finally {
            OS.GlobalFree(unionField);
        }
    }
    
    protected int[] getTypeIds() {
        return new int[] { HTMLTransfer.HTML_FORMATID };
    }
    
    protected String[] getTypeNames() {
        return new String[] { "HTML Format" };
    }
    
    boolean checkHTML(final Object o) {
        return o != null && o instanceof String && ((String)o).length() > 0;
    }
    
    protected boolean validate(final Object o) {
        return this.checkHTML(o);
    }
}
