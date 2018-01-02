// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FilenameFilter;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.io.File;

public class BatchJob extends Thread
{
    private File folder;
    private boolean running;
    ABCFinder finder;
    ODCFTranscriber transcriber;
    private long jobId;
    public static final int NEW = 0;
    public static final int TRANSCRIBING = 1;
    public static final int SEARCHING = 2;
    public static final int DONE = 3;
    public static final int ERROR = 4;
    private boolean serverMode;
    public static Results results;
    
    public BatchJob() {
        this.folder = null;
        this.running = false;
        this.finder = null;
        this.transcriber = null;
        this.serverMode = false;
        this.jobId = -1L;
    }
    
    public boolean chooseFolder() {
        final JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(2);
        fc.setSelectedFile(new File("" + ((Hashtable<K, Object>)MattProperties.instance()).get("BatchPath")));
        final int returnVal = fc.showOpenDialog(MattGuiNB.instance());
        if (returnVal == 0) {
            this.setFolder(fc.getSelectedFile());
            MattProperties.instance().setProperty("BatchPath", "" + this.folder);
            MattProperties.instance();
            MattProperties.save();
            return true;
        }
        return false;
    }
    
    public void run() {
        final WavFilter filter = new WavFilter();
        this.running = true;
        Connection conn = null;
        try {
            if (this.serverMode) {
                conn = DBHelper.getConnection();
                final PreparedStatement s = conn.prepareStatement("update job set status = ? where id = ?");
                s.setLong(1, 1L);
                s.setLong(2, this.jobId);
                s.execute();
            }
            (BatchJob.results = new Results()).log("" + MattProperties.instance());
            File[] files;
            if (this.folder.isDirectory()) {
                files = this.folder.listFiles(filter);
            }
            else if (this.folder.toString().endsWith(".zip")) {
                UnZipper.unzip("" + this.folder);
                this.folder = new File(UnZipper.getPath());
                files = this.folder.listFiles(filter);
            }
            else {
                files = new File[] { this.folder };
            }
            for (int i = 0; i < files.length; ++i) {
                MattGuiNB.instance().clearGraphs();
                MattGuiNB.instance().clearMatches();
                MattGuiNB.instance().getTxtABC().setText("");
                if (this.transcriber != null) {
                    this.transcriber.cleanup();
                }
                this.finder = new ABCFinder();
                (this.transcriber = MattGuiNB.instance().getTranscriber()).setInputFile(files[i].toString());
                MattGuiNB.instance().setTranscriber(this.transcriber);
                this.transcriber.loadAudio();
                this.transcriber.transcribe();
                this.finder.setSearchString(this.transcriber.getAbcTranscription());
                this.finder.setStartIn(((Hashtable<K, Object>)MattProperties.instance()).get("SearchCorpus").toString());
                if (this.serverMode) {
                    conn = DBHelper.getConnection();
                    final PreparedStatement s2 = conn.prepareStatement("update job set status = ?, transcription = ? where id = ?");
                    s2.setLong(1, 2L);
                    s2.setString(2, this.transcriber.getAbcTranscription());
                    s2.setLong(3, this.jobId);
                    s2.execute();
                }
                this.finder.setTranscribedNotes(this.transcriber.getTranscribedNotes());
                this.finder.findFromIndex();
                if (!this.running) {
                    break;
                }
                final PriorityQueue<ABCMatch> matches = this.finder.getPq();
                final StringBuffer result = new StringBuffer();
                final String delim = "\t";
                result.append(files[i].getName());
                result.append(delim);
                for (int j = 0; j < 10; ++j) {
                    final ABCMatch match = matches.poll();
                    if (match != null) {
                        result.append(match.getTitle());
                        result.append(delim);
                        result.append(match.getX());
                        if (match.getRepititions() != -1) {
                            result.append(delim);
                            result.append(match.getWhich());
                            result.append(delim);
                            result.append(match.getRepititions());
                        }
                        result.append(delim);
                        result.append(match.getEditDistance());
                        result.append(delim);
                        final float normalisedEd = match.getEditDistance() / this.transcriber.getTranscribedNotes().length * 100.0f;
                        result.append(normalisedEd);
                        result.append(delim);
                        if (this.serverMode) {
                            conn = DBHelper.getConnection();
                            final String sql = "INSERT INTO `tunometer`.`match` (`jobId` ,`tuneId` ,`ed`, `correct`) VALUES (?, ?, ?, ?)";
                            final PreparedStatement s3 = conn.prepareStatement(sql);
                            s3.setLong(1, this.jobId);
                            s3.setLong(2, match.getIndex());
                            s3.setFloat(3, match.getEditDistance());
                            s3.setBoolean(4, false);
                            s3.execute();
                            DBHelper.safeClose(conn, s3, null);
                        }
                    }
                }
                BatchJob.results.log(result.toString());
                if (this.serverMode) {
                    conn = DBHelper.getConnection();
                    final PreparedStatement s4 = conn.prepareStatement("update job set status = ? where id = ?");
                    s4.setLong(1, 3L);
                    s4.setLong(2, this.jobId);
                    s4.execute();
                }
                DBHelper.safeClose(conn, null, null);
            }
        }
        catch (Exception e) {
            Logger.log("Problem with batch job");
            e.printStackTrace();
        }
        finally {
            BatchJob.results.close();
        }
        Logger.log("Garbage collecting...");
        System.gc();
        Logger.log("Done.");
        this.running = false;
    }
    
    public boolean isRunning() {
        return this.running || (this.finder != null && this.finder.isRunning());
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
        if (this.finder != null) {
            this.finder.setRunning(running);
        }
    }
    
    public File getFolder() {
        return this.folder;
    }
    
    public void setFolder(final File folder) {
        this.folder = folder;
    }
    
    public long getJobId() {
        return this.jobId;
    }
    
    public void setJobId(final long jobId) {
        this.jobId = jobId;
    }
    
    public boolean isServerMode() {
        return this.serverMode;
    }
    
    public void setServerMode(final boolean serverMode) {
        this.serverMode = serverMode;
    }
    
    static {
        BatchJob.results = null;
    }
}
