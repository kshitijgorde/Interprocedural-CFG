// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.applet.Applet;

public class Dte extends Nbr
{
    public Dte(final String s, final String s2, final double n, final double n2, final Applet applet) {
        super(s, s2, n, n2, 0, 0, applet);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (this.getText().equals("0")) {
            this.setText("");
        }
        else if (this.getText().equals("TODAY")) {
            this.setText(simpleDateFormat.format(new Date()));
        }
        else if (this.getText().equals("NEXT_MONTH")) {
            final Date date = new Date();
            final GregorianCalendar gregorianCalendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDate());
            gregorianCalendar.add(2, 1);
            date.setYear(gregorianCalendar.get(1));
            date.setMonth(gregorianCalendar.get(2));
            date.setDate(gregorianCalendar.get(5));
            this.setText(simpleDateFormat.format(date));
        }
    }
    
    public boolean keypressOk(final char c) {
        return true;
    }
    
    public Date toDate() throws NumberFormatException {
        final String text = this.getText();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        Label_0167: {
            try {
                date = simpleDateFormat.parse(text);
            }
            catch (Exception ex) {
                if (text.length() == 8) {
                    final String string = String.valueOf(text.substring(0, 2)) + "/" + text.substring(2, 4) + "/" + text.substring(4, 8);
                    try {
                        date = simpleDateFormat.parse(string);
                        break Label_0167;
                    }
                    catch (Exception ex2) {
                        throw new NumberFormatException(String.valueOf(Nbr.INPUT_ERROR_MSG) + super._sName + ".");
                    }
                }
                throw new NumberFormatException(String.valueOf(Nbr.INPUT_ERROR_MSG) + super._sName + ".");
            }
        }
        this.setText(simpleDateFormat.format(date));
        return date;
    }
}
