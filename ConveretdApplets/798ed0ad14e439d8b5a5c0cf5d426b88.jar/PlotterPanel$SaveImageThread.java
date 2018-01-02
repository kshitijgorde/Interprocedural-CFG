import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class PlotterPanel$SaveImageThread extends Thread
{
    private int[] buffer;
    private String format;
    private final PlotterPanel this$0;
    
    private PlotterPanel$SaveImageThread(final PlotterPanel this$0) {
        this.this$0 = this$0;
    }
    
    public final void run() {
        try {
            final URLConnection openConnection = new URL(OmegaSystem.translateURL(Utils.urlCat(Configuration.url_exec(), OmegaSystem.dual ? "dual.wiris.servlets.SaveBitmap" : "wiris.servlets.SaveBitmap"))).openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setAllowUserInteraction(false);
            PlotterPanel.I(this.this$0, openConnection.getOutputStream(), this.buffer, this.format);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            final String utf = dataInputStream.readUTF();
            dataInputStream.close();
            this.this$0.I.showDocument(Utils.urlCat(Configuration.url_save_download, utf));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        if (PlotterPanel.I(this.this$0) != null) {
            PlotterPanel.I(this.this$0).dispose();
            PlotterPanel.I(this.this$0, null);
        }
    }
    
    public final void saveImage(final int[] buffer, final String format) {
        this.buffer = buffer;
        this.format = format;
        PlotterPanel.I(this.this$0, new MessageBox(this.this$0.mainFrame, "Save image", "Generating the image...", 1));
        PlotterPanel.I(this.this$0).setModal(false);
        PlotterPanel.I(this.this$0).show();
        this.start();
    }
    
    PlotterPanel$SaveImageThread(final PlotterPanel plotterPanel, final PlotterPanel$1 plotterPanel$1) {
        this(plotterPanel);
    }
}
