// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xdom;

import org.jdom.Element;
import java.io.IOException;
import java.io.OutputStream;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import org.jdom.Document;

public class XPrinter
{
    public static void printDoc(final Document doc) {
        try {
            final XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
            xo.output(doc, (OutputStream)System.out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void printEle(final Element ele) {
        try {
            final XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
            xo.output(ele, (OutputStream)System.out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
