// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import org.apache.activemq.util.Handler;
import java.sql.Connection;
import javax.sql.DataSource;
import org.slf4j.Logger;

public class DefaultDatabaseLocker implements DatabaseLocker
{
    public static final long DEFAULT_LOCK_ACQUIRE_SLEEP_INTERVAL = 1000L;
    private static final Logger LOG;
    protected DataSource dataSource;
    protected Statements statements;
    protected long lockAcquireSleepInterval;
    protected Connection connection;
    protected boolean stopping;
    protected Handler<Exception> exceptionHandler;
    
    public DefaultDatabaseLocker() {
        this.lockAcquireSleepInterval = 1000L;
    }
    
    public DefaultDatabaseLocker(final JDBCPersistenceAdapter persistenceAdapter) throws IOException {
        this.lockAcquireSleepInterval = 1000L;
        this.setPersistenceAdapter(persistenceAdapter);
    }
    
    @Override
    public void setPersistenceAdapter(final JDBCPersistenceAdapter adapter) throws IOException {
        this.dataSource = adapter.getLockDataSource();
        this.statements = adapter.getStatements();
    }
    
    @Override
    public void start() throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: iconst_0       
        //     2: putfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.stopping:Z
        //     5: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //     8: ldc             "Attempting to acquire the exclusive lock to become the Master broker"
        //    10: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;)V
        //    15: aload_0         /* this */
        //    16: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.statements:Lorg/apache/activemq/store/jdbc/Statements;
        //    19: invokevirtual   org/apache/activemq/store/jdbc/Statements.getLockCreateStatement:()Ljava/lang/String;
        //    22: astore_1        /* sql */
        //    23: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //    26: new             Ljava/lang/StringBuilder;
        //    29: dup            
        //    30: invokespecial   java/lang/StringBuilder.<init>:()V
        //    33: ldc             "Locking Query is "
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: aload_1         /* sql */
        //    39: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    42: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    45: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;)V
        //    50: aconst_null    
        //    51: astore_2        /* statement */
        //    52: aload_0         /* this */
        //    53: aload_0         /* this */
        //    54: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.dataSource:Ljavax/sql/DataSource;
        //    57: invokeinterface javax/sql/DataSource.getConnection:()Ljava/sql/Connection;
        //    62: putfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //    65: aload_0         /* this */
        //    66: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //    69: iconst_0       
        //    70: invokeinterface java/sql/Connection.setAutoCommit:(Z)V
        //    75: aload_0         /* this */
        //    76: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //    79: aload_1         /* sql */
        //    80: invokeinterface java/sql/Connection.prepareStatement:(Ljava/lang/String;)Ljava/sql/PreparedStatement;
        //    85: astore_2        /* statement */
        //    86: aload_2         /* statement */
        //    87: invokeinterface java/sql/PreparedStatement.execute:()Z
        //    92: pop            
        //    93: aconst_null    
        //    94: aload_2         /* statement */
        //    95: if_acmpeq       570
        //    98: aload_2         /* statement */
        //    99: invokeinterface java/sql/PreparedStatement.close:()V
        //   104: goto            136
        //   107: astore_3        /* e1 */
        //   108: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   111: new             Ljava/lang/StringBuilder;
        //   114: dup            
        //   115: invokespecial   java/lang/StringBuilder.<init>:()V
        //   118: ldc             "Caught while closing statement: "
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: aload_3         /* e1 */
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: aload_3         /* e1 */
        //   131: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   136: aconst_null    
        //   137: astore_2        /* statement */
        //   138: goto            570
        //   141: astore_3        /* e */
        //   142: aload_0         /* this */
        //   143: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.stopping:Z
        //   146: ifeq            177
        //   149: new             Ljava/lang/Exception;
        //   152: dup            
        //   153: new             Ljava/lang/StringBuilder;
        //   156: dup            
        //   157: invokespecial   java/lang/StringBuilder.<init>:()V
        //   160: ldc             "Cannot start broker as being asked to shut down. Interrupted attempt to acquire lock: "
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: aload_3         /* e */
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   172: aload_3         /* e */
        //   173: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   176: athrow         
        //   177: aload_0         /* this */
        //   178: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   181: ifnull          259
        //   184: aload_0         /* this */
        //   185: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   188: aload_3         /* e */
        //   189: invokeinterface org/apache/activemq/util/Handler.handle:(Ljava/lang/Object;)V
        //   194: goto            287
        //   197: astore          handlerException
        //   199: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   202: new             Ljava/lang/StringBuilder;
        //   205: dup            
        //   206: invokespecial   java/lang/StringBuilder.<init>:()V
        //   209: ldc             "The exception handler "
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: aload_0         /* this */
        //   215: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   218: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   221: invokevirtual   java/lang/Class.getCanonicalName:()Ljava/lang/String;
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: ldc             " threw this exception: "
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: aload           handlerException
        //   234: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   237: ldc             " while trying to handle this exception: "
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: aload_3         /* e */
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   249: aload           handlerException
        //   251: invokeinterface org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   256: goto            287
        //   259: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   262: new             Ljava/lang/StringBuilder;
        //   265: dup            
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: ldc             "Lock failure: "
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: aload_3         /* e */
        //   275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   281: aload_3         /* e */
        //   282: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   287: aconst_null    
        //   288: aload_0         /* this */
        //   289: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   292: if_acmpeq       409
        //   295: aload_0         /* this */
        //   296: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   299: invokeinterface java/sql/Connection.close:()V
        //   304: goto            339
        //   307: astore          e1
        //   309: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   312: new             Ljava/lang/StringBuilder;
        //   315: dup            
        //   316: invokespecial   java/lang/StringBuilder.<init>:()V
        //   319: ldc             "Caught exception while closing connection: "
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: aload           e1
        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   329: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   332: aload           e1
        //   334: invokeinterface org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   339: aload_0         /* this */
        //   340: aconst_null    
        //   341: putfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   344: goto            409
        //   347: astore          5
        //   349: aconst_null    
        //   350: aload_0         /* this */
        //   351: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   354: if_acmpeq       406
        //   357: aload_0         /* this */
        //   358: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   361: invokeinterface java/sql/Connection.close:()V
        //   366: goto            401
        //   369: astore          e1
        //   371: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   374: new             Ljava/lang/StringBuilder;
        //   377: dup            
        //   378: invokespecial   java/lang/StringBuilder.<init>:()V
        //   381: ldc             "Caught exception while closing connection: "
        //   383: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: aload           e1
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   391: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   394: aload           e1
        //   396: invokeinterface org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   401: aload_0         /* this */
        //   402: aconst_null    
        //   403: putfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.connection:Ljava/sql/Connection;
        //   406: aload           5
        //   408: athrow         
        //   409: aconst_null    
        //   410: aload_2         /* statement */
        //   411: if_acmpeq       510
        //   414: aload_2         /* statement */
        //   415: invokeinterface java/sql/PreparedStatement.close:()V
        //   420: goto            452
        //   423: astore_3        /* e1 */
        //   424: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   427: new             Ljava/lang/StringBuilder;
        //   430: dup            
        //   431: invokespecial   java/lang/StringBuilder.<init>:()V
        //   434: ldc             "Caught while closing statement: "
        //   436: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   439: aload_3         /* e1 */
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   443: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   446: aload_3         /* e1 */
        //   447: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   452: aconst_null    
        //   453: astore_2        /* statement */
        //   454: goto            510
        //   457: astore          7
        //   459: aconst_null    
        //   460: aload_2         /* statement */
        //   461: if_acmpeq       507
        //   464: aload_2         /* statement */
        //   465: invokeinterface java/sql/PreparedStatement.close:()V
        //   470: goto            505
        //   473: astore          e1
        //   475: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   478: new             Ljava/lang/StringBuilder;
        //   481: dup            
        //   482: invokespecial   java/lang/StringBuilder.<init>:()V
        //   485: ldc             "Caught while closing statement: "
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   490: aload           e1
        //   492: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   495: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   498: aload           e1
        //   500: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   505: aconst_null    
        //   506: astore_2        /* statement */
        //   507: aload           7
        //   509: athrow         
        //   510: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   513: new             Ljava/lang/StringBuilder;
        //   516: dup            
        //   517: invokespecial   java/lang/StringBuilder.<init>:()V
        //   520: ldc             "Failed to acquire lock.  Sleeping for "
        //   522: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   525: aload_0         /* this */
        //   526: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.lockAcquireSleepInterval:J
        //   529: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   532: ldc             " milli(s) before trying again..."
        //   534: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   537: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   540: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;)V
        //   545: aload_0         /* this */
        //   546: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.lockAcquireSleepInterval:J
        //   549: invokestatic    java/lang/Thread.sleep:(J)V
        //   552: goto            52
        //   555: astore_3        /* ie */
        //   556: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   559: ldc             "Master lock retry sleep interrupted"
        //   561: aload_3         /* ie */
        //   562: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   567: goto            52
        //   570: getstatic       org/apache/activemq/store/jdbc/DefaultDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   573: new             Ljava/lang/StringBuilder;
        //   576: dup            
        //   577: invokespecial   java/lang/StringBuilder.<init>:()V
        //   580: ldc             "Becoming the master on dataSource: "
        //   582: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   585: aload_0         /* this */
        //   586: getfield        org/apache/activemq/store/jdbc/DefaultDatabaseLocker.dataSource:Ljavax/sql/DataSource;
        //   589: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   592: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   595: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;)V
        //   600: return         
        //    Exceptions:
        //  throws java.lang.Exception
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  ------------------------------------------------------
        //  108    28      3     e1                Ljava/sql/SQLException;
        //  199    57      4     handlerException  Ljava/lang/Throwable;
        //  309    30      4     e1                Ljava/sql/SQLException;
        //  371    30      6     e1                Ljava/sql/SQLException;
        //  142    267     3     e                 Ljava/lang/Exception;
        //  424    28      3     e1                Ljava/sql/SQLException;
        //  475    30      8     e1                Ljava/sql/SQLException;
        //  556    11      3     ie                Ljava/lang/InterruptedException;
        //  0      601     0     this              Lorg/apache/activemq/store/jdbc/DefaultDatabaseLocker;
        //  23     578     1     sql               Ljava/lang/String;
        //  52     549     2     statement         Ljava/sql/PreparedStatement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  98     104    107    136    Ljava/sql/SQLException;
        //  52     93     141    570    Ljava/lang/Exception;
        //  184    194    197    259    Ljava/lang/Throwable;
        //  295    304    307    339    Ljava/sql/SQLException;
        //  142    287    347    409    Any
        //  357    366    369    401    Ljava/sql/SQLException;
        //  347    349    347    409    Any
        //  414    420    423    452    Ljava/sql/SQLException;
        //  52     93     457    510    Any
        //  141    409    457    510    Any
        //  464    470    473    505    Ljava/sql/SQLException;
        //  457    459    457    510    Any
        //  545    552    555    570    Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 249, Size: 249
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void stop() throws Exception {
        this.stopping = true;
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                try {
                    this.connection.rollback();
                }
                catch (SQLException sqle) {
                    DefaultDatabaseLocker.LOG.warn("Exception while rollbacking the connection on shutdown", sqle);
                    try {
                        this.connection.close();
                    }
                    catch (SQLException ignored) {
                        DefaultDatabaseLocker.LOG.debug("Exception while closing connection on shutdown", ignored);
                    }
                }
                finally {
                    try {
                        this.connection.close();
                    }
                    catch (SQLException ignored2) {
                        DefaultDatabaseLocker.LOG.debug("Exception while closing connection on shutdown", ignored2);
                    }
                }
            }
        }
        catch (SQLException sqle) {
            DefaultDatabaseLocker.LOG.warn("Exception while checking close status of connection on shutdown", sqle);
        }
    }
    
    @Override
    public boolean keepAlive() {
        PreparedStatement statement = null;
        boolean result = false;
        try {
            statement = this.connection.prepareStatement(this.statements.getLockUpdateStatement());
            statement.setLong(1, System.currentTimeMillis());
            final int rows = statement.executeUpdate();
            if (rows == 1) {
                result = true;
            }
        }
        catch (Exception e) {
            DefaultDatabaseLocker.LOG.error("Failed to update database lock: " + e, e);
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException e2) {
                    DefaultDatabaseLocker.LOG.error("Failed to close statement", e2);
                }
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (SQLException e3) {
                    DefaultDatabaseLocker.LOG.error("Failed to close statement", e3);
                }
            }
        }
        return result;
    }
    
    public long getLockAcquireSleepInterval() {
        return this.lockAcquireSleepInterval;
    }
    
    @Override
    public void setLockAcquireSleepInterval(final long lockAcquireSleepInterval) {
        this.lockAcquireSleepInterval = lockAcquireSleepInterval;
    }
    
    public Handler getExceptionHandler() {
        return this.exceptionHandler;
    }
    
    public void setExceptionHandler(final Handler exceptionHandler) {
        this.exceptionHandler = (Handler<Exception>)exceptionHandler;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DefaultDatabaseLocker.class);
    }
}
