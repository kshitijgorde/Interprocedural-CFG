// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.test;

import java.io.IOException;
import java.io.FileNotFoundException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;
import javax.xml.transform.Result;
import java.io.OutputStream;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import com.stonewall.cornerstone.dsp.DSP;

public class SimpleTransform
{
    public static void main(final String[] args) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException {
        try {
            DSP.init();
            final TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(new StreamSource("dsp:///screenos_v5_0_0/ns5gt/birds.xsl"));
            transformer.transform(new StreamSource("dsp:///screenos_v5_0_0/ns5gt/birds.xml"), new StreamResult(new FileOutputStream("c:/tmp/birds.out")));
            System.out.println("************* The result is in birds.out *************");
            transformer = tFactory.newTransformer(new StreamSource("http:///screenos_v5_0_0/ns5gt/birds.xsl"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
