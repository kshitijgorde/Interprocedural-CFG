// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.program;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.SHFILEINFO;
import org.eclipse.swt.internal.win32.SHFILEINFOA;
import org.eclipse.swt.internal.win32.SHFILEINFOW;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.win32.PROCESS_INFORMATION;
import org.eclipse.swt.internal.win32.STARTUPINFO;
import org.eclipse.swt.internal.win32.SHELLEXECUTEINFO;
import org.eclipse.swt.internal.win32.FILETIME;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;

public final class Program
{
    String name;
    String command;
    String iconName;
    String extension;
    static final String[] ARGUMENTS;
    
    static {
        ARGUMENTS = new String[] { "%1", "%l", "%L" };
    }
    
    static String assocQueryString(final int n, final TCHAR tchar, final boolean b) {
        TCHAR tchar2 = new TCHAR(0, 1024);
        final int[] array = { tchar2.length() };
        final int n2 = 1056;
        int n3 = OS.AssocQueryString(n2, n, tchar, null, tchar2, array);
        if (n3 == -2147467261) {
            tchar2 = new TCHAR(0, array[0]);
            n3 = OS.AssocQueryString(n2, n, tchar, null, tchar2, array);
        }
        if (n3 != 0) {
            return null;
        }
        if (OS.IsWinCE || !b) {
            return tchar2.toString(0, Math.max(0, array[0] - 1));
        }
        final int expandEnvironmentStrings = OS.ExpandEnvironmentStrings(tchar2, null, 0);
        if (expandEnvironmentStrings != 0) {
            final TCHAR tchar3 = new TCHAR(0, expandEnvironmentStrings);
            OS.ExpandEnvironmentStrings(tchar2, tchar3, expandEnvironmentStrings);
            return tchar3.toString(0, Math.max(0, expandEnvironmentStrings - 1));
        }
        return "";
    }
    
    public static Program findProgram(String string) {
        if (string == null) {
            SWT.error(4);
        }
        if (string.length() == 0) {
            return null;
        }
        if (string.charAt(0) != '.') {
            string = "." + string;
        }
        final TCHAR tchar = new TCHAR(0, string, true);
        Program program = null;
        if (OS.IsWinCE) {
            final int[] array = { 0 };
            if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar, 0, 131097, array) != 0) {
                return null;
            }
            final int[] array2 = { 0 };
            if (OS.RegQueryValueEx(array[0], null, 0, null, (TCHAR)null, array2) == 0) {
                final TCHAR tchar2 = new TCHAR(0, array2[0] / TCHAR.sizeof);
                if (OS.RegQueryValueEx(array[0], null, 0, null, tchar2, array2) == 0) {
                    program = getProgram(tchar2.toString(0, tchar2.strlen()), string);
                }
            }
            OS.RegCloseKey(array[0]);
        }
        else {
            final String assocQueryString = assocQueryString(1, tchar, true);
            if (assocQueryString != null) {
                String name = assocQueryString(3, tchar, false);
                if (name == null) {
                    name = assocQueryString(4, tchar, false);
                }
                if (name == null) {
                    name = "";
                }
                String assocQueryString2 = assocQueryString(15, tchar, true);
                if (assocQueryString2 == null) {
                    assocQueryString2 = "";
                }
                program = new Program();
                program.name = name;
                program.command = assocQueryString;
                program.iconName = assocQueryString2;
                program.extension = string;
            }
        }
        return program;
    }
    
    public static String[] getExtensions() {
        String[] array = new String[1024];
        final TCHAR tchar = new TCHAR(0, 1024);
        final int[] array2 = { tchar.length() };
        final FILETIME filetime = new FILETIME();
        int n = 0;
        int n2 = 0;
        while (OS.RegEnumKeyEx(Integer.MIN_VALUE, n, tchar, array2, null, null, null, filetime) != 259) {
            final String string = tchar.toString(0, array2[0]);
            array2[0] = tchar.length();
            if (string.length() > 0 && string.charAt(0) == '.') {
                if (n2 == array.length) {
                    final String[] array3 = new String[array.length + 1024];
                    System.arraycopy(array, 0, array3, 0, array.length);
                    array = array3;
                }
                array[n2++] = string;
            }
            ++n;
        }
        if (n2 != array.length) {
            final String[] array4 = new String[n2];
            System.arraycopy(array, 0, array4, 0, n2);
            array = array4;
        }
        return array;
    }
    
    static String getKeyValue(final String s, final boolean b) {
        final TCHAR tchar = new TCHAR(0, s, true);
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar, 0, 131097, array) != 0) {
            return null;
        }
        String s2 = null;
        final int[] array2 = { 0 };
        if (OS.RegQueryValueEx(array[0], null, 0, null, (TCHAR)null, array2) == 0) {
            s2 = "";
            final int n = array2[0] / TCHAR.sizeof;
            if (n != 0) {
                final TCHAR tchar2 = new TCHAR(0, n);
                if (OS.RegQueryValueEx(array[0], null, 0, null, tchar2, array2) == 0) {
                    if (!OS.IsWinCE && b) {
                        final int expandEnvironmentStrings = OS.ExpandEnvironmentStrings(tchar2, null, 0);
                        if (expandEnvironmentStrings != 0) {
                            final TCHAR tchar3 = new TCHAR(0, expandEnvironmentStrings);
                            OS.ExpandEnvironmentStrings(tchar2, tchar3, expandEnvironmentStrings);
                            s2 = tchar3.toString(0, Math.max(0, expandEnvironmentStrings - 1));
                        }
                    }
                    else {
                        s2 = tchar2.toString(0, Math.max(0, tchar2.length() - 1));
                    }
                }
            }
        }
        if (array[0] != 0) {
            OS.RegCloseKey(array[0]);
        }
        return s2;
    }
    
    static Program getProgram(final String s, final String extension) {
        String keyValue = getKeyValue(s, false);
        if (keyValue == null || keyValue.length() == 0) {
            keyValue = s;
        }
        String keyValue2 = getKeyValue(String.valueOf(s) + "\\shell", true);
        if (keyValue2 == null || keyValue2.length() == 0) {
            keyValue2 = "open";
        }
        final String keyValue3 = getKeyValue(String.valueOf(s) + ("\\shell\\" + keyValue2 + "\\command"), true);
        if (keyValue3 == null || keyValue3.length() == 0) {
            return null;
        }
        String keyValue4 = getKeyValue(String.valueOf(s) + "\\DefaultIcon", true);
        if (keyValue4 == null) {
            keyValue4 = "";
        }
        final Program program = new Program();
        program.name = keyValue;
        program.command = keyValue3;
        program.iconName = keyValue4;
        program.extension = extension;
        return program;
    }
    
    public static Program[] getPrograms() {
        Program[] array = new Program[1024];
        final TCHAR tchar = new TCHAR(0, 1024);
        final int[] array2 = { tchar.length() };
        final FILETIME filetime = new FILETIME();
        int n = 0;
        int n2 = 0;
        while (OS.RegEnumKeyEx(Integer.MIN_VALUE, n, tchar, array2, null, null, null, filetime) != 259) {
            final String string = tchar.toString(0, array2[0]);
            array2[0] = tchar.length();
            final Program program = getProgram(string, null);
            if (program != null) {
                if (n2 == array.length) {
                    final Program[] array3 = new Program[array.length + 1024];
                    System.arraycopy(array, 0, array3, 0, array.length);
                    array = array3;
                }
                array[n2++] = program;
            }
            ++n;
        }
        if (n2 != array.length) {
            final Program[] array4 = new Program[n2];
            System.arraycopy(array, 0, array4, 0, n2);
            array = array4;
        }
        return array;
    }
    
    public static boolean launch(final String s) {
        return launch(s, null);
    }
    
    public static boolean launch(final String s, final String s2) {
        if (s == null) {
            SWT.error(4);
        }
        final int getProcessHeap = OS.GetProcessHeap();
        final TCHAR tchar = new TCHAR(0, s, true);
        final int n = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, tchar, n);
        int heapAlloc2 = 0;
        if (s2 != null && OS.PathIsExe(heapAlloc)) {
            final TCHAR tchar2 = new TCHAR(0, s2, true);
            final int n2 = tchar2.length() * TCHAR.sizeof;
            heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, n2);
            OS.MoveMemory(heapAlloc2, tchar2, n2);
        }
        final SHELLEXECUTEINFO shellexecuteinfo = new SHELLEXECUTEINFO();
        shellexecuteinfo.cbSize = SHELLEXECUTEINFO.sizeof;
        shellexecuteinfo.lpFile = heapAlloc;
        shellexecuteinfo.lpDirectory = heapAlloc2;
        shellexecuteinfo.nShow = 5;
        final boolean shellExecuteEx = OS.ShellExecuteEx(shellexecuteinfo);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (heapAlloc2 != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc2);
        }
        return shellExecuteEx;
    }
    
    public boolean execute(String string) {
        if (string == null) {
            SWT.error(4);
        }
        int i = 0;
        boolean b = true;
        String s = this.command;
        String substring = "";
        while (i < Program.ARGUMENTS.length) {
            final int index = this.command.indexOf(Program.ARGUMENTS[i]);
            if (index != -1) {
                b = false;
                s = this.command.substring(0, index);
                substring = this.command.substring(index + Program.ARGUMENTS[i].length(), this.command.length());
                break;
            }
            ++i;
        }
        if (b) {
            string = " \"" + string + "\"";
        }
        final String string2 = String.valueOf(s) + string + substring;
        final int getProcessHeap = OS.GetProcessHeap();
        final TCHAR tchar = new TCHAR(0, string2, true);
        final int n = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, tchar, n);
        final STARTUPINFO startupinfo = new STARTUPINFO();
        startupinfo.cb = STARTUPINFO.sizeof;
        final PROCESS_INFORMATION process_INFORMATION = new PROCESS_INFORMATION();
        final boolean createProcess = OS.CreateProcess(0, heapAlloc, 0, 0, false, 0, 0, 0, startupinfo, process_INFORMATION);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (process_INFORMATION.hProcess != 0) {
            OS.CloseHandle(process_INFORMATION.hProcess);
        }
        if (process_INFORMATION.hThread != 0) {
            OS.CloseHandle(process_INFORMATION.hThread);
        }
        return createProcess;
    }
    
    public ImageData getImageData() {
        if (this.extension != null) {
            final SHFILEINFO shfileinfo = OS.IsUnicode ? new SHFILEINFOW() : new SHFILEINFOA();
            OS.SHGetFileInfo(new TCHAR(0, this.extension, true), 128, shfileinfo, SHFILEINFO.sizeof, 273);
            if (shfileinfo.hIcon != 0) {
                final Image win32_new = Image.win32_new(null, 1, shfileinfo.hIcon);
                final ImageData imageData = win32_new.getImageData();
                win32_new.dispose();
                return imageData;
            }
        }
        int int1 = 0;
        String s = this.iconName;
        final int index = this.iconName.indexOf(44);
        if (index != -1) {
            s = this.iconName.substring(0, index);
            final String trim = this.iconName.substring(index + 1, this.iconName.length()).trim();
            try {
                int1 = Integer.parseInt(trim);
            }
            catch (NumberFormatException ex) {}
        }
        final int length = s.length();
        if (length > 1 && s.charAt(0) == '\"' && s.charAt(length - 1) == '\"') {
            s = s.substring(1, length - 1);
        }
        final TCHAR tchar = new TCHAR(0, s, true);
        final int[] array = { 0 };
        OS.ExtractIconEx(tchar, int1, null, array, 1);
        if (array[0] == 0) {
            return null;
        }
        final Image win32_new2 = Image.win32_new(null, 1, array[0]);
        final ImageData imageData2 = win32_new2.getImageData();
        win32_new2.dispose();
        return imageData2;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Program) {
            final Program program = (Program)o;
            return this.name.equals(program.name) && this.command.equals(program.command) && this.iconName.equals(program.iconName);
        }
        return false;
    }
    
    public int hashCode() {
        return this.name.hashCode() ^ this.command.hashCode() ^ this.iconName.hashCode();
    }
    
    public String toString() {
        return "Program {" + this.name + "}";
    }
}
