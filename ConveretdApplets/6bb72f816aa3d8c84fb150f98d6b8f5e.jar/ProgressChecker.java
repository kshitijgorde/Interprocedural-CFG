import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

class ProgressChecker extends RSApplet
{
    public static int per;
    public static int per2;
    public String filetocheck;
    public long filelength;
    
    public ProgressChecker(final String filetocheck, final long filelength) {
        this.filetocheck = filetocheck;
        this.filelength = filelength;
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        long length = 0L;
        ProgressChecker.per = 0;
        final File file = new File(this.filetocheck);
        while (length <= this.filelength) {
            try {
                Thread.sleep(5L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            length = file.length();
            ProgressChecker.per = (int)(length / this.filelength * 100.0);
            if (ProgressChecker.per > ProgressChecker.per2) {
                ProgressChecker.per2 = ProgressChecker.per;
                try {
                    client.instance.drawLoadingText(ProgressChecker.per, (ProgressChecker.per != 100) ? ("Downloading updates " + ProgressChecker.per + "%") : "Installing updates...");
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    static {
        ProgressChecker.per = 0;
        ProgressChecker.per2 = 0;
    }
}
