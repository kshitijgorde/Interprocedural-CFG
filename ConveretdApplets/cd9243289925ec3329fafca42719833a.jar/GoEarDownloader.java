import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GoEarDownloader extends Applet
{
    private static final long serialVersionUID = 1L;
    
    public void init() {
        try {
            System.out.println("Loaded");
            final URL dobd = new URL("http://www.goear.com/tracker758.php?f=" + this.getParameter("FilesMapGoearMp3Id"));
            final URLConnection connection = dobd.openConnection();
            connection.setRequestProperty("User-Agent", this.getParameter("ua"));
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String GoEarUrl = "";
            String totalLine = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                totalLine = String.valueOf(totalLine) + inputLine;
            }
            in.close();
            GoEarUrl = this.inbtwn(totalLine, "<song path=\"", "\"");
            if (GoEarUrl != null) {
                this.getAppletContext().showDocument(new URL("javascript:DownloadGoear(\"" + GoEarUrl + "\");"));
            }
            else {
                System.out.println("File not found");
                this.getAppletContext().showDocument(new URL("javascript:FileNotFound();"));
            }
        }
        catch (IOException IOerror) {
            System.out.println("sordek Whoops!!");
        }
    }
    
    public void start() {
        try {
            this.getAppletContext().showDocument(new URL("javascript:JavaLoaded();"));
        }
        catch (IOException IOerror) {
            System.out.println("Whoops!!");
        }
    }
    
    public void destroy() {
        try {
            this.getAppletContext().showDocument(new URL("javascript:JavaDestroy();"));
        }
        catch (IOException IOerror) {
            System.out.println("Whoops!!");
        }
    }
    
    private String inbtwn(final String input, final String startcut, final String finishcut) {
        String output = null;
        try {
            final String[] arr1 = input.split(startcut);
            final String[] arr2 = arr1[1].split(finishcut);
            output = arr2[0];
        }
        catch (Exception ex) {
            return null;
        }
        return output;
    }
}
