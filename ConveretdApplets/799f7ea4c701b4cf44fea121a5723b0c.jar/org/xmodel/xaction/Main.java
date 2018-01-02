// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.IModelObject;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import org.xmodel.xml.XmlIO;

public class Main
{
    public static void main(final String[] array) throws Exception {
        final IModelObject read = new XmlIO().read(new FileInputStream(new File(array[0])));
        new XActionDocument(read).createScript(read, new String[0]).run();
    }
}
