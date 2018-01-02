// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;
import java.sql.SQLException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;

public class DefaultIOExceptionHandler implements IOExceptionHandler
{
    private static final Logger LOG;
    private BrokerService broker;
    private boolean ignoreAllErrors;
    private boolean ignoreNoSpaceErrors;
    private boolean ignoreSQLExceptions;
    private boolean stopStartConnectors;
    private String noSpaceMessage;
    private String sqlExceptionMessage;
    private long resumeCheckSleepPeriod;
    private AtomicBoolean stopStartInProgress;
    
    public DefaultIOExceptionHandler() {
        this.ignoreAllErrors = false;
        this.ignoreNoSpaceErrors = true;
        this.ignoreSQLExceptions = true;
        this.stopStartConnectors = false;
        this.noSpaceMessage = "space";
        this.sqlExceptionMessage = "";
        this.resumeCheckSleepPeriod = 5000L;
        this.stopStartInProgress = new AtomicBoolean(false);
    }
    
    @Override
    public void handle(final IOException exception) {
        if (this.ignoreAllErrors) {
            DefaultIOExceptionHandler.LOG.info("Ignoring IO exception, " + exception, exception);
            return;
        }
        if (this.ignoreNoSpaceErrors) {
            for (Throwable cause = exception; cause != null && cause instanceof IOException; cause = cause.getCause()) {
                if (cause.getMessage().contains(this.noSpaceMessage)) {
                    DefaultIOExceptionHandler.LOG.info("Ignoring no space left exception, " + exception, exception);
                    return;
                }
            }
        }
        if (this.ignoreSQLExceptions) {
            for (Throwable cause = exception; cause != null; cause = cause.getCause()) {
                if (cause instanceof SQLException && cause.getMessage().contains(this.sqlExceptionMessage)) {
                    DefaultIOExceptionHandler.LOG.info("Ignoring SQLException, " + exception, cause);
                    return;
                }
            }
        }
        if (!this.stopStartConnectors) {
            DefaultIOExceptionHandler.LOG.info("Stopping the broker due to IO exception, " + exception, exception);
            new Thread("Stopping the broker due to IO exception") {
                @Override
                public void run() {
                    try {
                        DefaultIOExceptionHandler.this.broker.stop();
                    }
                    catch (Exception e) {
                        DefaultIOExceptionHandler.LOG.warn("Failure occurred while stopping broker", e);
                    }
                }
            }.start();
            return;
        }
        if (!this.stopStartInProgress.compareAndSet(false, true)) {
            return;
        }
        DefaultIOExceptionHandler.LOG.info("Initiating stop/restart of broker transport due to IO exception, " + exception, exception);
        new Thread("stop transport connectors on IO exception") {
            @Override
            public void run() {
                try {
                    final ServiceStopper stopper = new ServiceStopper();
                    DefaultIOExceptionHandler.this.broker.stopAllConnectors(stopper);
                }
                catch (Exception e) {
                    DefaultIOExceptionHandler.LOG.warn("Failure occurred while stopping broker connectors", e);
                }
            }
        }.start();
        new Thread("restart transport connectors post IO exception") {
            @Override
            public void run() {
                try {
                    while (this.isPersistenceAdapterDown()) {
                        DefaultIOExceptionHandler.LOG.info("waiting for broker persistence adapter checkpoint to succeed before restarting transports");
                        TimeUnit.MILLISECONDS.sleep(DefaultIOExceptionHandler.this.resumeCheckSleepPeriod);
                    }
                    DefaultIOExceptionHandler.this.broker.startAllConnectors();
                }
                catch (Exception e) {
                    DefaultIOExceptionHandler.LOG.warn("Failure occurred while restarting broker connectors", e);
                }
                finally {
                    DefaultIOExceptionHandler.this.stopStartInProgress.compareAndSet(true, false);
                }
            }
            
            private boolean isPersistenceAdapterDown() {
                boolean checkpointSuccess = false;
                try {
                    DefaultIOExceptionHandler.this.broker.getPersistenceAdapter().checkpoint(true);
                    checkpointSuccess = true;
                }
                catch (Throwable t) {}
                return !checkpointSuccess;
            }
        }.start();
    }
    
    @Override
    public void setBrokerService(final BrokerService broker) {
        this.broker = broker;
    }
    
    public boolean isIgnoreAllErrors() {
        return this.ignoreAllErrors;
    }
    
    public void setIgnoreAllErrors(final boolean ignoreAllErrors) {
        this.ignoreAllErrors = ignoreAllErrors;
    }
    
    public boolean isIgnoreNoSpaceErrors() {
        return this.ignoreNoSpaceErrors;
    }
    
    public void setIgnoreNoSpaceErrors(final boolean ignoreNoSpaceErrors) {
        this.ignoreNoSpaceErrors = ignoreNoSpaceErrors;
    }
    
    public String getNoSpaceMessage() {
        return this.noSpaceMessage;
    }
    
    public void setNoSpaceMessage(final String noSpaceMessage) {
        this.noSpaceMessage = noSpaceMessage;
    }
    
    public boolean isIgnoreSQLExceptions() {
        return this.ignoreSQLExceptions;
    }
    
    public void setIgnoreSQLExceptions(final boolean ignoreSQLExceptions) {
        this.ignoreSQLExceptions = ignoreSQLExceptions;
    }
    
    public String getSqlExceptionMessage() {
        return this.sqlExceptionMessage;
    }
    
    public void setSqlExceptionMessage(final String sqlExceptionMessage) {
        this.sqlExceptionMessage = sqlExceptionMessage;
    }
    
    public boolean isStopStartConnectors() {
        return this.stopStartConnectors;
    }
    
    public void setStopStartConnectors(final boolean stopStartConnectors) {
        this.stopStartConnectors = stopStartConnectors;
    }
    
    public long getResumeCheckSleepPeriod() {
        return this.resumeCheckSleepPeriod;
    }
    
    public void setResumeCheckSleepPeriod(final long resumeCheckSleepPeriod) {
        this.resumeCheckSleepPeriod = resumeCheckSleepPeriod;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DefaultIOExceptionHandler.class);
    }
}
