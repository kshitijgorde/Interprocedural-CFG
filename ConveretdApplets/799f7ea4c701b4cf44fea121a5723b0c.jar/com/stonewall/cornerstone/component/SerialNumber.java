// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import com.stonewall.cornerstone.utility.IdentityFactory;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class SerialNumber
{
    private final String type;
    private String content;
    
    public SerialNumber(final String type) {
        this.content = null;
        this.type = type;
    }
    
    public void write() throws IOException {
        final File file = this.file();
        final FileWriter writer = new FileWriter(file);
        writer.write(this.content.toCharArray());
        writer.close();
    }
    
    public void read() throws IOException {
        try {
            final File file = this.file();
            final char[] bfr = new char[64];
            final FileReader reader = new FileReader(file);
            final int n = reader.read(bfr);
            this.content = new String(bfr, 0, n);
        }
        catch (Exception e) {
            this.content = new IdentityFactory().next();
            this.write();
        }
    }
    
    @Override
    public String toString() {
        return this.content;
    }
    
    private File file() {
        return new File(String.valueOf(this.dir()) + "/." + this.type + ".sn");
    }
    
    private String dir() {
        File result = new File("/etc");
        if (!result.exists()) {
            result = this.findRoot();
        }
        return result.getAbsolutePath();
    }
    
    private File findRoot() {
        File result = null;
        File[] listRoots;
        for (int length = (listRoots = File.listRoots()).length, i = 0; i < length; ++i) {
            final File f = result = listRoots[i];
            final char drive = f.getPath().charAt(0);
            if (drive != 'A' && drive != 'B') {
                break;
            }
        }
        return result;
    }
}
