// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public final class j implements d
{
    private FileWriter a;
    private File b;
    
    public j(final File b) {
        this.b = b;
        if (!this.b.getParentFile().exists() && !this.b.getParentFile().mkdirs()) {
            com.hw.client.util.a.d("unable to initialize path to log file, m_file => " + this.b);
            return;
        }
        if (this.b.exists() && !this.b.canWrite()) {
            com.hw.client.util.a.d("unable to write to log file, m_file => " + this.b);
            return;
        }
        try {
            this.a = new FileWriter(this.b.getAbsolutePath(), true);
        }
        catch (IOException ex) {
            com.hw.client.util.a.b("unable to open log file for writing", ex);
        }
    }
    
    public final void a() {
        if (this.a != null) {
            com.hw.client.util.a.a(this);
        }
    }
    
    public final void b() {
        com.hw.client.util.a.b(this);
        if (this.a != null) {
            try {
                this.a.close();
                this.a = null;
            }
            catch (IOException ex) {
                com.hw.client.util.a.b("unable to close log file", ex);
            }
        }
    }
    
    public final void a(final f f) {
        if (!(f instanceof h)) {
            return;
        }
        try {
            this.a.write(f.toString() + "\n");
            this.a.flush();
        }
        catch (IOException ex) {
            this.b();
            com.hw.client.util.a.b("unable to write to log file", ex);
        }
    }
}
