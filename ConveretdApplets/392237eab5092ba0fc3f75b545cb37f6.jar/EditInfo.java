import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Scrollbar;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class EditInfo
{
    String name;
    String text;
    double value;
    double minval;
    double maxval;
    TextField textf;
    Scrollbar bar;
    Choice choice;
    Checkbox checkbox;
    boolean newDialog;
    boolean forceLargeM;
    boolean dimensionless;
    
    EditInfo(final String name, final double value, final double minval, final double maxval) {
        this.name = name;
        this.value = value;
        if (minval == 0.0 && maxval == 0.0 && value > 0.0) {
            this.minval = 1.0E10;
            while (this.minval > value / 100.0) {
                this.minval /= 10.0;
            }
            this.maxval = this.minval * 1000.0;
        }
        else {
            this.minval = minval;
            this.maxval = maxval;
        }
        this.forceLargeM = (this.name.indexOf("(ohms)") > 0 || this.name.indexOf("(Hz)") > 0);
        this.dimensionless = false;
    }
    
    EditInfo setDimensionless() {
        this.dimensionless = true;
        return this;
    }
}
