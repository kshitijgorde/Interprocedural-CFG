// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

import java.awt.Component;

public class GetImageFileName extends Component
{
    public String GetFile(final String dialogTitle, final String startDirectory) {
        String sFileName = "";
        Filters.setTitle(dialogTitle);
        Filters.setDir(startDirectory);
        sFileName = Filters.Start();
        return sFileName;
    }
}
