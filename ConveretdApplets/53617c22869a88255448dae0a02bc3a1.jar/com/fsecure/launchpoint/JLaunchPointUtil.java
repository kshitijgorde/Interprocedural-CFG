// 
// Decompiled by Procyon v0.5.30
// 

package com.fsecure.launchpoint;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.security.AccessControlException;
import java.io.File;

class JLaunchPointUtil
{
    private static final String JAVA_TEMP_FOLDER_PROPERTIES = "java.io.tmpdir";
    
    private JLaunchPointUtil() {
        throw new AssertionError();
    }
    
    public static final void deleteDownloadedFile(final String s) {
        final File file = new File(s);
        if (file.isFile()) {
            file.delete();
        }
    }
    
    public static final String getTempFoler() throws AccessControlException {
        return System.getProperty("java.io.tmpdir");
    }
    
    public static final String constructTempFilePath(final String s) {
        final StringBuffer sb = new StringBuffer("");
        sb.append(getTempFoler()).append(File.separator).append(s);
        return sb.toString();
    }
    
    public static final String showProgressioninPercentage(final int n, final int n2) {
        final DecimalFormat decimalFormat = new DecimalFormat("#");
        String format = "";
        final double double1 = Double.parseDouble(String.valueOf(n));
        try {
            if (n2 != 0) {
                format = decimalFormat.format(double1 / n2 * 100.0);
            }
        }
        catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
        return format;
    }
    
    public static final boolean executeProgram(final String s, final String s2) throws Exception {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[] { s.trim(), "-language:" + s2 }).start().getInputStream()));
            bufferedReader.readLine();
            bufferedReader.close();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}
