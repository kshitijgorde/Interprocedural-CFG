// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9;

import com.otnip.tools.files.FileTools;
import java.io.File;
import com.otnip.irig106.chapter9.r.R;
import java.util.ArrayList;
import com.otnip.irig106.chapter9.g.G;

public class TMATS
{
    public String rawText;
    public G g;
    public ArrayList<R> rs;
    
    public TMATS(final String rawText) throws Exception {
        this.g = new G();
        this.rs = new ArrayList<R>();
        this.rawText = rawText;
        this.g.set(rawText);
        this.rs = R.parse(rawText);
    }
    
    public static void main(final String[] args) {
        try {
            final String text = FileTools.getText(new File("/home/dlpinto/test.txt"));
            final TMATS tmats = new TMATS(text);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
