import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class mj implements ActionListener
{
    private final n ta;
    
    mj(final n ta) {
        this.ta = ta;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            this.ta.b(false);
            try {
                this.ta.b(n.a(this.ta).a().b("msgPrintingChart"));
                final PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintable(n._(this.ta)._().a(n.h(this.ta)));
                if (printerJob.printDialog()) {
                    try {
                        printerJob.print();
                        this.ta.b(n.a(this.ta).a().b(""));
                    }
                    catch (Exception ex) {
                        this.ta.b(n.a(this.ta).a().b("msgPrintError"));
                        ex.printStackTrace();
                    }
                }
                else {
                    this.ta.b(n.a(this.ta).a().b(""));
                }
            }
            catch (SecurityException ex2) {
                this.ta.b(n.a(this.ta).a().b("msgPrintError"));
                ex2.printStackTrace();
            }
            finally {
                this.ta.b(true);
            }
        }
    }
}
