import java.text.MessageFormat;

// 
// Decompiled by Procyon v0.5.30
// 

class SearchThread extends Thread
{
    SearchApplet4ech sa;
    SearchFrame sf;
    String[] phrases;
    String[] filedata;
    String[] lc_filedata;
    boolean case_sen;
    
    SearchThread(final SearchApplet4ech sa, final SearchFrame sf, final String[] phrases, final String[] filedata, final String[] lc_filedata, final boolean case_sen) {
        this.sa = sa;
        this.sf = sf;
        this.phrases = phrases;
        this.filedata = filedata;
        this.lc_filedata = lc_filedata;
        this.case_sen = case_sen;
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.sa.setStopSearch(false);
        this.sa.clearMatchedDocuments();
        this.sf.clearList();
        this.sf.setStatus("");
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.filedata.length; ++i) {
            n = i + 1;
            if (this.sa.getStopSearch()) {
                this.sa.setStopSearch(false);
                break;
            }
            boolean b = true;
            for (int j = 0; j < this.phrases.length; ++j) {
                if (!this.case_sen && this.lc_filedata[i] == null) {
                    this.lc_filedata[i] = this.filedata[i].toLowerCase();
                    this.sa.setLowerCaseFileData(i, this.lc_filedata[i]);
                }
                if ((this.case_sen ? this.filedata[i] : this.lc_filedata[i]).indexOf(this.phrases[j]) == -1) {
                    b = false;
                    break;
                }
            }
            if (b) {
                ++n2;
                final int index = this.filedata[i].indexOf("<title>");
                this.sf.updateList((index > -1) ? this.filedata[i].substring(index + 7, this.filedata[i].indexOf("</title>")) : "Untitled");
                this.sa.addMatchedDocument(i);
            }
        }
        this.sf.setStatus(MessageFormat.format(this.sa.getMessage("search.search_complete_count_matched"), String.valueOf(n2), String.valueOf(n), String.valueOf(this.filedata.length)));
        if (this.sa.getTimedStatus()) {
            System.out.println("Search Time: " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }
}
