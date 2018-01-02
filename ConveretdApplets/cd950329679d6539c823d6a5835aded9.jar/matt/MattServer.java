// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class MattServer extends Thread
{
    private boolean running;
    
    public void run() {
        final long serverThreadSleepTime = Long.parseLong(MattProperties.getString("serverThreadSleepTime"));
        Logger.log("Server staring...");
        this.running = true;
        while (this.running) {
            Logger.log("Waking up");
            try {
                final Connection conn = DBHelper.getConnection();
                if (conn == null) {
                    break;
                }
                final PreparedStatement s = conn.prepareStatement("select * from job where status = ?");
                s.setInt(1, 0);
                final ResultSet results = s.executeQuery();
                while (results.next()) {
                    final BatchJob job = new BatchJob();
                    job.setJobId(results.getLong("id"));
                    job.setServerMode(true);
                    final String fileName = MattProperties.getString("tunometerPath") + System.getProperty("file.separator") + job.getJobId() + ".zip";
                    job.setFolder(new File(fileName));
                    job.start();
                }
                Logger.log("Going to sleep");
                try {
                    Thread.sleep(serverThreadSleepTime);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DBHelper.safeClose(conn, s, results);
            }
            catch (SQLException e2) {
                Logger.log("An SQL exception occured. Server shutting down");
                e2.printStackTrace();
                this.running = false;
            }
        }
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean running) {
        this.running = running;
    }
}
