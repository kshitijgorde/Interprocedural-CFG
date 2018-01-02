// 
// Decompiled by Procyon v0.5.30
// 

package com.eaio.nativecall;

public class Win32Verifier implements Verifier
{
    public boolean supports() throws SecurityException {
        return System.getProperty("os.name").startsWith("Windows");
    }
    
    public String verifyModuleName(String module) {
        if (module == null || module.length() == 0) {
            return this.getDefaultModule();
        }
        if (module.indexOf(47) != -1) {
            module = module.replace('/', '\\');
        }
        return module;
    }
    
    public String verifyFunctionName(final String function) {
        if (function == null || function.length() == 0) {
            throw new NullPointerException();
        }
        return function;
    }
    
    public String getDefaultModule() {
        return "kernel32";
    }
    
    public Object handleString(final String val, final String module, final String function) {
        if (function.charAt(function.length() - 1) == 'W') {
            final char[] array = new char[val.length() + 1];
            val.getChars(0, val.length(), array, 0);
            return array;
        }
        final byte[] buf = new byte[val.length() + 1];
        val.getBytes(0, val.length(), buf, 0);
        return buf;
    }
}
