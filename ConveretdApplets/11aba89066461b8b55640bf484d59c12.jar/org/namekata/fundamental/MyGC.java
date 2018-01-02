// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.util.ResourceBundle;
import java.util.GregorianCalendar;

public class MyGC extends GregorianCalendar
{
    private static final String[][] dayOfWeek;
    
    static {
        dayOfWeek = new String[][] { { ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("sun"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("mon"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("tue"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("wed"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("thu"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("fri"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("sat") }, { ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Sun"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Mon"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Tue"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Wed"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Thu"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Fri"), ResourceBundle.getBundle("org.namekata.fundamental/MyGC").getString("Sat") } };
    }
    
    public MyGC() {
    }
    
    public MyGC(final long t) {
        super.clear();
        super.setTimeInMillis(t);
    }
    
    @Override
    public void setTimeInMillis(final long t) {
        super.clear();
        super.setTimeInMillis(t);
    }
    
    public String getDayOfWeek(int type) {
        if (type != 1) {
            type = 0;
        }
        final int i = this.get(7) - 1;
        return MyGC.dayOfWeek[type][i];
    }
    
    @Override
    public long getTimeInMillis() {
        return super.getTimeInMillis();
    }
}
