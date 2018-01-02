import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class TextReader1
{
    static int numPages;
    static String[] wordArray;
    static String thisFilename;
    
    static void GetFileName(final Applet thisParent, final String defaultName) {
        final String strTemp = thisParent.getParameter("FILENAME");
        if (strTemp != null) {
            TextReader1.thisFilename = strTemp;
        }
        else {
            TextReader1.thisFilename = defaultName;
        }
    }
    
    static void ReadText(final Applet thisParent, final int extraPages, final int maxPages) {
        TextReader1.wordArray = new String[1];
        try {
            final URL url = new URL(thisParent.getCodeBase(), TextReader1.thisFilename);
            String line = "";
            final DataInputStream source = new DataInputStream(url.openStream());
            thisParent.getAppletContext().showStatus("Reading in words...");
            TextReader1.numPages = 0;
            while ((line = source.readLine()) != null && TextReader1.numPages < maxPages) {
                ++TextReader1.numPages;
            }
            thisParent.getAppletContext().showStatus("Read in " + TextReader1.numPages + " words.");
            TextReader1.wordArray = new String[TextReader1.numPages + 1 + extraPages];
            source.close();
        }
        catch (MalformedURLException e) {
            thisParent.getAppletContext().showStatus("Malformed URL");
        }
        catch (IOException e) {
            thisParent.getAppletContext().showStatus("IO exception");
        }
        catch (Exception e) {
            thisParent.getAppletContext().showStatus("Unknown exception");
        }
        try {
            final URL url = new URL(thisParent.getCodeBase(), TextReader1.thisFilename);
            String line = "";
            int intCounter;
            DataInputStream source2;
            for (intCounter = 0, source2 = new DataInputStream(url.openStream()); (line = source2.readLine()) != null && intCounter < maxPages; ++intCounter) {
                TextReader1.wordArray[intCounter] = line;
            }
            source2.close();
        }
        catch (MalformedURLException e) {
            thisParent.getAppletContext().showStatus("Malformed URL");
        }
        catch (IOException e) {
            thisParent.getAppletContext().showStatus("IO Exception");
        }
        catch (Exception e) {
            thisParent.getAppletContext().showStatus("Unknown exception");
        }
    }
}
