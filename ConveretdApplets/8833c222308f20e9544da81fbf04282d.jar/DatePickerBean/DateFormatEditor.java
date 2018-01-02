// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.beans.PropertyEditorSupport;

public class DateFormatEditor extends PropertyEditorSupport
{
    public String getJavaInitializationString() {
        return "\"" + ((String)this.getValue()).substring(0, 2) + "\"";
    }
    
    public String[] getTags() {
        return new String[] { "01. dd_mm_yyyy", "02. mm_dd_yyyy", "03. dd_mmm_yyyy", "04. dd_mmmm_yyyy", "05. mmm_dd_yyyy", "06. mmmm_dd_yyyy", "07. ldow_dd_mmm_yyyy", "08. ldow_dd_mmmm_yyyy", "09. ldow_mmm_dd_yyyy", "10. ldow_mmmm_dd_yyyy", "11. sdow_dd_mmm_yyyy", "12. sdow_dd_mmmm_yyyy", "13. sdow_mmm_dd_yyyy", "14. sdow_mmmm_dd_yyyy" };
    }
}
