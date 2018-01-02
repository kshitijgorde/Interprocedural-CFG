// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;

public class MemoryTools
{
    public static int getPhysicalMemory(final long timeout) throws Exception {
        int result = -1;
        final String info32 = "C:\\Program Files\\Common Files\\Microsoft Shared\\MSInfo\\msinfo32.exe";
        final File tempDir = new File(System.getProperty("java.io.tmpdir"));
        final File outputFile = new File(tempDir, "ResourcesMemory.txt");
        if (outputFile.exists()) {
            outputFile.delete();
            System.out.println("Deleteing:  " + outputFile.exists());
        }
        final ProcessBuilder processBuilder = new ProcessBuilder(new String[] { info32, "/categories", "ResourcesMemory", "/report", outputFile.getAbsolutePath() });
        final Process process = processBuilder.start();
        process.waitFor();
        final long startTime = System.currentTimeMillis();
        while (!outputFile.exists() && System.currentTimeMillis() - startTime < timeout) {
            Thread.sleep(100L);
        }
        final FileInputStream fis = new FileInputStream(outputFile);
        final InputStreamReader isr = new InputStreamReader(fis, "UTF-16");
        final BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.contains("Total Physical Memory")) {
                final StringTokenizer str = new StringTokenizer(line, " \t");
                str.nextToken();
                str.nextToken();
                str.nextToken();
                String temp = str.nextToken();
                temp = temp.replace(",", "");
                result = (int)Double.parseDouble(temp);
            }
        }
        fis.close();
        return result;
    }
    
    public static long getUsedMemory() {
        final Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
    
    public static long getRemainingMemory() {
        final Runtime runtime = Runtime.getRuntime();
        return runtime.maxMemory() - getUsedMemory();
    }
    
    private MemoryTools() {
        throw new UnsupportedOperationException("Utility Constructor");
    }
}
