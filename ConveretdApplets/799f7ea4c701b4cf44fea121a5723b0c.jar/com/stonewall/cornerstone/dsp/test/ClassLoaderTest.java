// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import org.jdom.Document;
import java.io.InputStream;
import java.net.URL;
import org.jdom.output.XMLOutputter;
import org.jdom.input.SAXBuilder;
import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.dsp.DSP;
import com.stonewall.cornerstone.component.Bootstrap;

public class ClassLoaderTest
{
    public static void main(final String[] args) {
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            DSP.init();
            final Class c = Loader.loadClass("pix_ios_v6_1_1", "pix501", "CmdResParser");
            System.out.println("\n\nTesting loadClass");
            System.out.println("Class = " + c.newInstance());
            final Object[] v = new Object[0];
            final Object o = Loader.newInstance("screenos_v5_1_0", "ns5gt", "CmdResParser", v);
            System.out.println("\n\nTesting newInstance");
            System.out.println("Test = " + o);
            InputStream istr = Loader.getResourceAsStream("screenos_v5_1_0", "ns5gt", "errMessages.xml");
            SAXBuilder sax = new SAXBuilder();
            Document d = sax.build(istr);
            XMLOutputter op = new XMLOutputter();
            System.out.println("\n\nTesting getResource");
            System.out.println(op.outputString(d));
            istr.close();
            d = Loader.getDocument("screenos_v5_1_0", "ns5gt", "errMessages.xml");
            op = new XMLOutputter();
            System.out.println("\n\nTesting getDocument");
            System.out.println(op.outputString(d));
            URL dspUrl = new URL("dsp:///screenos_v5_1_0/ns5gt/errMessages.xml");
            istr = dspUrl.openStream();
            sax = new SAXBuilder();
            d = sax.build(istr);
            op = new XMLOutputter();
            System.out.println("\n\nTesting protocol handler");
            System.out.println(op.outputString(d));
            istr.close();
            dspUrl = new URL("dsp:///screenos_v5_0_0/ns25/errMessages.xml");
            istr = dspUrl.openStream();
            sax = new SAXBuilder();
            d = sax.build(istr);
            op = new XMLOutputter();
            System.out.println("\n\nTesting another software version");
            System.out.println(op.outputString(d));
            istr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
