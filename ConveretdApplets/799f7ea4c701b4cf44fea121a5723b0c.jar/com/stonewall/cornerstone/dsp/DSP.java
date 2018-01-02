// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp;

import java.io.File;
import com.stonewall.cornerstone.dsp.packaging.Packaging;
import com.stonewall.cornerstone.dsp.loader.Loader;
import java.io.FileNotFoundException;
import com.stonewall.cornerstone.xpath.FunctionPackage;
import org.xmodel.log.Log;

public class DSP
{
    static final Log log;
    
    static {
        log = Log.getLog(DSP.class);
    }
    
    public static void init() throws Exception {
        FunctionPackage.install();
        com.stonewall.cornerstone.dsp.xpath.FunctionPackage.install();
        System.setProperty("java.protocol.handler.pkgs", "com.stonewall.cornerstone");
        final File dspHome = determineDspHome();
        final File[] files = dspHome.listFiles();
        if (files == null || files.length == 0) {
            DSP.log.error("Could not find dsp jar files in dsp home=" + dspHome.getPath());
            throw new FileNotFoundException("Could not find dsp jar files");
        }
        Loader.init(files);
        Packaging.init(files);
    }
    
    private static File determineDspHome() throws FileNotFoundException {
        final String dirName = System.getProperty("cornerstone.dsp.home");
        final File file = new File(dirName);
        if (file == null || !file.exists()) {
            throw new FileNotFoundException(dirName);
        }
        return file;
    }
}
