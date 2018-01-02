// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io.sql;

import prefuse.data.Table;
import prefuse.data.io.DataIOException;
import prefuse.util.StringLib;
import prefuse.util.PrefuseConfig;
import prefuse.util.collections.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class DataSourceWorker extends Thread
{
    private static Logger s_logger;
    private static DataSourceWorker s_instance;
    private static CopyOnWriteArrayList s_queue;
    
    public static synchronized void submit(final Entry entry) {
        if (DataSourceWorker.s_queue == null) {
            DataSourceWorker.s_queue = new CopyOnWriteArrayList();
        }
        if (DataSourceWorker.s_instance == null) {
            DataSourceWorker.s_instance = new DataSourceWorker();
        }
        DataSourceWorker.s_queue.add(entry);
        synchronized (DataSourceWorker.s_instance) {
            DataSourceWorker.s_instance.notify();
        }
    }
    
    private DataSourceWorker() {
        super("prefuse_DatabaseWorker");
        final int int1 = PrefuseConfig.getInt("data.io.worker.threadPriority");
        if (int1 >= 1 && int1 <= 10) {
            this.setPriority(int1);
        }
        this.setDaemon(true);
        this.start();
    }
    
    public void run() {
        while (true) {
            Entry entry = null;
            synchronized (DataSourceWorker.s_queue) {
                if (DataSourceWorker.s_queue.size() > 0) {
                    entry = (Entry)DataSourceWorker.s_queue.remove(0);
                }
            }
            if (entry != null) {
                try {
                    if (entry.listener != null) {
                        entry.listener.preQuery(entry);
                    }
                    entry.ds.getData(entry.table, entry.query, entry.keyField, entry.lock);
                    if (entry.listener == null) {
                        continue;
                    }
                    entry.listener.postQuery(entry);
                }
                catch (DataIOException ex) {
                    DataSourceWorker.s_logger.warning(ex.getMessage() + "\n" + StringLib.getStackTrace(ex));
                }
            }
            else {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    static {
        DataSourceWorker.s_logger = Logger.getLogger(DataSourceWorker.class.getName());
    }
    
    public interface Listener
    {
        void preQuery(final Entry p0);
        
        void postQuery(final Entry p0);
    }
    
    public static class Entry
    {
        DatabaseDataSource ds;
        Listener listener;
        Table table;
        String query;
        String keyField;
        Object lock;
        
        public Entry(final DatabaseDataSource ds, final Table table, final String query, final String keyField, final Object lock, final Listener listener) {
            this.ds = ds;
            this.table = table;
            this.query = query;
            this.keyField = keyField;
            this.lock = lock;
            this.listener = listener;
        }
    }
}
