// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.List;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.Panel;

public class ReportBodyPanel extends Panel
{
    private Applet parApplet;
    private Frame parFrame;
    private String tit;
    private String desc;
    private int percentHit;
    private List files;
    private String keywords;
    stringTranslator st;
    
    public ReportBodyPanel(final Applet parentApplet, final Frame parentFrame, final List filesToSearch, final String kwords, final Color rpt_backround_colour, final Color rpt_hyperlink_colour, final Color rpt_text_colour, final stringTranslator strT) {
        this.setLayout(null);
        final Insets ins = this.getInsets();
        this.setSize(430, 270);
        this.parApplet = parentApplet;
        this.parFrame = parentFrame;
        this.files = filesToSearch;
        this.keywords = kwords;
        final LinkedListOfFiles hits = new LinkedListOfFiles();
        this.st = strT;
        for (int n = 0; n < this.files.getItemCount(); ++n) {
            final String currentFile = this.files.getItem(n);
            StringBuffer fileText = new StringBuffer();
            try {
                final URL urlFile = new URL(this.parApplet.getDocumentBase(), currentFile);
                final BufferedReader inStream = new BufferedReader(new InputStreamReader(urlFile.openStream()));
                String line;
                while ((line = inStream.readLine()) != null) {
                    fileText = fileText.append(line);
                }
                inStream.close();
                final String fText = fileText.toString();
                final String fTextlc = fText.toLowerCase();
                final String keywordslc = this.keywords.toLowerCase();
                int count = -1;
                for (int index = 0; index != -1; index = fTextlc.indexOf(keywordslc, index + 1), ++count) {}
                if (count > 0) {
                    int startI = fTextlc.indexOf("<meta name=\"description\" content=\"");
                    int endI = fTextlc.indexOf("\"", startI + 34);
                    String descriptionText;
                    if (startI != -1 && endI != -1) {
                        descriptionText = fText.substring(startI + 34, endI);
                    }
                    else {
                        descriptionText = this.st.noDescription;
                    }
                    startI = fTextlc.indexOf("<title>");
                    endI = fTextlc.indexOf("</title>", startI);
                    String titleText;
                    if (startI != -1 && endI != -1) {
                        titleText = fText.substring(startI + 7, endI);
                    }
                    else {
                        titleText = this.st.noTitle;
                    }
                    hits.add(count, titleText, descriptionText, this.files.getItem(n));
                }
            }
            catch (MalformedURLException ex) {
                final AttentionDialog ad = new AttentionDialog(this.parFrame, this.st.pleaseInformTheWebmasterForThisSite, String.valueOf(this.st.errorInTheFormatOfTheFilename) + this.files.getItem(n) + "\n" + this.st.itShouldBeOfTheForm + " http://my.site.co.uk/myfile.htm");
                ad.show();
            }
            catch (IOException ex2) {
                final AttentionDialog ad = new AttentionDialog(this.parFrame, this.st.pleaseInformTheWebmasterForThisSite, String.valueOf(this.st.errorReadingTheFollowingFile) + this.files.getItem(n));
                ad.show();
            }
            catch (SecurityException ex3) {
                final AttentionDialog ad = new AttentionDialog(this.parFrame, this.st.pleaseInformTheWebmasterForThisSite, String.valueOf(this.st.errorReadingTheFollowingFile) + this.files.getItem(n) + "\n" + this.st.youHaveBrokenJavaSecurityRules + "\n" + this.st.theFileToBeReadMustResideOnTheSameServerAsTheSearchApplet);
                ad.show();
            }
        }
        hits.normalise();
        final GridLayout gLayout = new GridLayout(1, 1, 2, 2);
        this.setLayout(gLayout);
        for (int x = 1; x <= hits.getItemCount(); ++x) {
            final LinkedListFileItem i = hits.getItem(x);
            final ReportRowPanel rrp = new ReportRowPanel(this.parApplet, i.titleText, i.descriptionText, i.linkText, i.rank, rpt_backround_colour, rpt_hyperlink_colour, rpt_text_colour);
            gLayout.setRows(x + 1);
            this.add(rrp);
        }
        if (hits.getItemCount() == 0) {
            final Panel p = new Panel();
            final Label text = new Label(this.st.yourQueryMatchedNoDocuments);
            text.setAlignment(0);
            text.setFont(new Font("Dialog", 1, 20));
            p.setLayout(new FlowLayout(0));
            p.add(text);
            this.add(p);
        }
    }
}
