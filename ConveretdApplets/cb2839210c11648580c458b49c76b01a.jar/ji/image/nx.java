// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.print.PrinterJob;

class nx
{
    public static final PrinterJob a(final boolean b, final int n, final String jobName) {
        try {
            final PrinterJob printerJob = PrinterJob.getPrinterJob();
            if (printerJob != null) {
                printerJob.setJobName(jobName);
            }
            return printerJob;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
