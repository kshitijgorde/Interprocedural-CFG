// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import java.io.InputStream;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.dsp.DSP;

public class DevDeployTest
{
    public static void main(final String[] args) {
        try {
            DSP.init();
            try {
                final Class c = Loader.loadClass("screenos_v5_1_0", "ns5gt", "Config");
                System.out.println("\n\nTesting loadClass");
                System.out.println("Class = " + c);
            }
            catch (Exception cnfe) {
                cnfe.printStackTrace();
            }
            final Class c = Loader.loadClass("screenos_v5_1_0", "ns5gt", "OperationIterator");
            System.out.println("\n\nTesting loadClass");
            System.out.println("Class = " + c);
            final InputStream is = Loader.getResourceAsStream("screenos_v5_1_0", "ns5gt", "ConfigParser.jj");
            System.out.println("\n\nTesting getResource" + is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
