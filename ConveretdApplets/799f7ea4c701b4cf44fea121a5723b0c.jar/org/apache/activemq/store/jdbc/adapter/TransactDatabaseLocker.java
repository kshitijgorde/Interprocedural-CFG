// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc.adapter;

import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.slf4j.Logger;
import org.apache.activemq.store.jdbc.DefaultDatabaseLocker;

public class TransactDatabaseLocker extends DefaultDatabaseLocker
{
    private static final Logger LOG;
    
    public TransactDatabaseLocker() {
    }
    
    public TransactDatabaseLocker(final JDBCPersistenceAdapter persistenceAdapter) throws IOException {
        this.setPersistenceAdapter(persistenceAdapter);
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
        //     2: putfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.stopping:Z
        //     5: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //     8: ldc             "Attempting to acquire the exclusive lock to become the Master broker"
        //    10: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;)V
        //    15: aconst_null    
        //    16: astore_1        /* statement */
        //    17: aload_0         /* this */
        //    18: aload_0         /* this */
        //    19: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.dataSource:Ljavax/sql/DataSource;
        //    22: invokeinterface javax/sql/DataSource.getConnection:()Ljava/sql/Connection;
        //    27: putfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.connection:Ljava/sql/Connection;
        //    30: aload_0         /* this */
        //    31: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.connection:Ljava/sql/Connection;
        //    34: iconst_0       
        //    35: invokeinterface java/sql/Connection.setAutoCommit:(Z)V
        //    40: aload_0         /* this */
        //    41: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.statements:Lorg/apache/activemq/store/jdbc/Statements;
        //    44: invokevirtual   org/apache/activemq/store/jdbc/Statements.getLockCreateStatement:()Ljava/lang/String;
        //    47: astore_2        /* sql */
        //    48: aload_0         /* this */
        //    49: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.connection:Ljava/sql/Connection;
        //    52: aload_2         /* sql */
        //    53: invokeinterface java/sql/Connection.prepareStatement:(Ljava/lang/String;)Ljava/sql/PreparedStatement;
        //    58: astore_1        /* statement */
        //    59: aload_1         /* statement */
        //    60: invokeinterface java/sql/PreparedStatement.getMetaData:()Ljava/sql/ResultSetMetaData;
        //    65: ifnull          85
        //    68: aload_1         /* statement */
        //    69: invokeinterface java/sql/PreparedStatement.executeQuery:()Ljava/sql/ResultSet;
        //    74: astore_3        /* rs */
        //    75: aload_3         /* rs */
        //    76: invokeinterface java/sql/ResultSet.next:()Z
        //    81: pop            
        //    82: goto            92
        //    85: aload_1         /* statement */
        //    86: invokeinterface java/sql/PreparedStatement.execute:()Z
        //    91: pop            
        //    92: aconst_null    
        //    93: aload_1         /* statement */
        //    94: if_acmpeq       444
        //    97: aload_1         /* statement */
        //    98: invokeinterface java/sql/PreparedStatement.close:()V
        //   103: goto            135
        //   106: astore_3        /* e1 */
        //   107: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc             "Caught while closing statement: "
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: aload_3         /* e1 */
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   126: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   129: aload_3         /* e1 */
        //   130: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   135: aconst_null    
        //   136: astore_1        /* statement */
        //   137: goto            444
        //   140: astore_2        /* sql */
        //   141: aload_0         /* this */
        //   142: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.stopping:Z
        //   145: ifeq            176
        //   148: new             Ljava/lang/Exception;
        //   151: dup            
        //   152: new             Ljava/lang/StringBuilder;
        //   155: dup            
        //   156: invokespecial   java/lang/StringBuilder.<init>:()V
        //   159: ldc             "Cannot start broker as being asked to shut down. Interrupted attempt to acquire lock: "
        //   161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: aload_2         /* e */
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   168: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   171: aload_2         /* e */
        //   172: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   175: athrow         
        //   176: aload_0         /* this */
        //   177: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   180: ifnull          255
        //   183: aload_0         /* this */
        //   184: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   187: aload_2         /* e */
        //   188: invokeinterface org/apache/activemq/util/Handler.handle:(Ljava/lang/Object;)V
        //   193: goto            283
        //   196: astore_3        /* handlerException */
        //   197: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   200: new             Ljava/lang/StringBuilder;
        //   203: dup            
        //   204: invokespecial   java/lang/StringBuilder.<init>:()V
        //   207: ldc             "The exception handler "
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: aload_0         /* this */
        //   213: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.exceptionHandler:Lorg/apache/activemq/util/Handler;
        //   216: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   219: invokevirtual   java/lang/Class.getCanonicalName:()Ljava/lang/String;
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: ldc             " threw this exception: "
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: aload_3         /* handlerException */
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   234: ldc             " while trying to handle this excpetion: "
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: aload_2         /* e */
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   243: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   246: aload_3         /* handlerException */
        //   247: invokeinterface org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   252: goto            283
        //   255: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   258: new             Ljava/lang/StringBuilder;
        //   261: dup            
        //   262: invokespecial   java/lang/StringBuilder.<init>:()V
        //   265: ldc             "Failed to acquire lock: "
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload_2         /* e */
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   274: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   277: aload_2         /* e */
        //   278: invokeinterface org/slf4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   283: aconst_null    
        //   284: aload_1         /* statement */
        //   285: if_acmpeq       384
        //   288: aload_1         /* statement */
        //   289: invokeinterface java/sql/PreparedStatement.close:()V
        //   294: goto            326
        //   297: astore_2        /* e1 */
        //   298: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   301: new             Ljava/lang/StringBuilder;
        //   304: dup            
        //   305: invokespecial   java/lang/StringBuilder.<init>:()V
        //   308: ldc             "Caught while closing statement: "
        //   310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: aload_2         /* e1 */
        //   314: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   317: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   320: aload_2         /* e1 */
        //   321: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   326: aconst_null    
        //   327: astore_1        /* statement */
        //   328: goto            384
        //   331: astore          4
        //   333: aconst_null    
        //   334: aload_1         /* statement */
        //   335: if_acmpeq       381
        //   338: aload_1         /* statement */
        //   339: invokeinterface java/sql/PreparedStatement.close:()V
        //   344: goto            379
        //   347: astore          e1
        //   349: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   352: new             Ljava/lang/StringBuilder;
        //   355: dup            
        //   356: invokespecial   java/lang/StringBuilder.<init>:()V
        //   359: ldc             "Caught while closing statement: "
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: aload           e1
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   369: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   372: aload           e1
        //   374: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   379: aconst_null    
        //   380: astore_1        /* statement */
        //   381: aload           4
        //   383: athrow         
        //   384: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   387: new             Ljava/lang/StringBuilder;
        //   390: dup            
        //   391: invokespecial   java/lang/StringBuilder.<init>:()V
        //   394: ldc             "Sleeping for "
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: aload_0         /* this */
        //   400: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.lockAcquireSleepInterval:J
        //   403: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   406: ldc             " milli(s) before trying again to get the lock..."
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   414: invokeinterface org/slf4j/Logger.debug:(Ljava/lang/String;)V
        //   419: aload_0         /* this */
        //   420: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.lockAcquireSleepInterval:J
        //   423: invokestatic    java/lang/Thread.sleep:(J)V
        //   426: goto            17
        //   429: astore_2        /* ie */
        //   430: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   433: ldc             "Master lock retry sleep interrupted"
        //   435: aload_2         /* ie */
        //   436: invokeinterface org/slf4j/Logger.warn:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   441: goto            17
        //   444: getstatic       org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.LOG:Lorg/slf4j/Logger;
        //   447: new             Ljava/lang/StringBuilder;
        //   450: dup            
        //   451: invokespecial   java/lang/StringBuilder.<init>:()V
        //   454: ldc             "Becoming the master on dataSource: "
        //   456: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   459: aload_0         /* this */
        //   460: getfield        org/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker.dataSource:Ljavax/sql/DataSource;
        //   463: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   466: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   469: invokeinterface org/slf4j/Logger.info:(Ljava/lang/String;)V
        //   474: return         
        //    Exceptions:
        //  throws java.lang.Exception
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  ---------------------------------------------------------------
        //  75     7       3     rs                Ljava/sql/ResultSet;
        //  107    28      3     e1                Ljava/sql/SQLException;
        //  48     92      2     sql               Ljava/lang/String;
        //  197    55      3     handlerException  Ljava/lang/Throwable;
        //  141    142     2     e                 Ljava/lang/Exception;
        //  298    28      2     e1                Ljava/sql/SQLException;
        //  349    30      5     e1                Ljava/sql/SQLException;
        //  430    11      2     ie                Ljava/lang/InterruptedException;
        //  0      475     0     this              Lorg/apache/activemq/store/jdbc/adapter/TransactDatabaseLocker;
        //  17     458     1     statement         Ljava/sql/PreparedStatement;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  97     103    106    135    Ljava/sql/SQLException;
        //  17     92     140    444    Ljava/lang/Exception;
        //  183    193    196    255    Ljava/lang/Throwable;
        //  288    294    297    326    Ljava/sql/SQLException;
        //  17     92     331    384    Any
        //  140    283    331    384    Any
        //  338    344    347    379    Ljava/sql/SQLException;
        //  331    333    331    384    Any
        //  419    426    429    444    Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 199, Size: 199
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
    
    static {
        LOG = LoggerFactory.getLogger(TransactDatabaseLocker.class);
    }
}
