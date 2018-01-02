// 
// Decompiled by Procyon v0.5.30
// 

package com.cloudpath.loader;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;

public class LightLog
{
    private static final StringBuffer cls_Messages;
    
    protected static final void log(final String s) {
        LightLog.cls_Messages.append(s);
        LightLog.cls_Messages.append('\n');
        System.out.println(s);
    }
    
    protected static void save() {
        FileOutputStream fileOutputStream = null;
        try {
            final File tempFile = File.createTempFile("xpcloader_", ".log");
            fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(LightLog.cls_Messages.toString().getBytes());
            System.out.println("Log saved to " + tempFile.getAbsolutePath());
        }
        catch (IOException ex) {
            System.out.println("Error while saving log.");
            ex.printStackTrace();
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    static {
        cls_Messages = new StringBuffer();
    }
}
