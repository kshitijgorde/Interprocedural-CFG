// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import junit.framework.Assert;
import javax.jms.MessageConsumer;
import java.util.concurrent.TimeUnit;
import javax.jms.Message;
import java.util.concurrent.CountDownLatch;
import javax.jms.BytesMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.util.Iterator;
import javax.jms.JMSException;
import org.apache.activemq.command.ActiveMQQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.jms.Connection;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Random;
import javax.jms.Destination;
import junit.framework.TestCase;

public class AcidTestTool extends TestCase
{
    protected int recordSize;
    protected int batchSize;
    protected int workerThinkTime;
    protected Destination target;
    private Random random;
    private byte[] data;
    private int workerCount;
    private AtomicBoolean ignoreJMSErrors;
    private ActiveMQConnectionFactory factory;
    private Connection connection;
    private AtomicInteger publishedBatches;
    private AtomicInteger consumedBatches;
    private List<Throwable> errors;
    
    public AcidTestTool() {
        this.recordSize = 1024;
        this.batchSize = 5;
        this.workerThinkTime = 500;
        this.random = new Random();
        this.workerCount = 10;
        this.ignoreJMSErrors = new AtomicBoolean(false);
        this.publishedBatches = new AtomicInteger(0);
        this.consumedBatches = new AtomicInteger(0);
        this.errors = Collections.synchronizedList(new ArrayList<Throwable>());
    }
    
    protected void setUp() throws Exception {
        this.factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        this.target = new ActiveMQQueue(this.getClass().getName());
    }
    
    protected void tearDown() throws Exception {
        if (this.connection != null) {
            try {
                this.connection.close();
            }
            catch (Throwable t) {}
            this.connection = null;
        }
    }
    
    private void reconnect() throws InterruptedException, JMSException {
        if (this.connection != null) {
            try {
                this.connection.close();
            }
            catch (Throwable t) {}
            this.connection = null;
        }
        long reconnectDelay = 1000L;
        while (this.connection == null) {
            if (reconnectDelay > 10000L) {
                reconnectDelay = 10000L;
            }
            try {
                (this.connection = this.factory.createConnection()).start();
            }
            catch (JMSException e) {
                Thread.sleep(reconnectDelay);
                reconnectDelay *= 2L;
            }
        }
    }
    
    public void testAcidTransactions() throws Throwable {
        System.out.println("Client threads write records using: Record Size: " + this.recordSize + ", Batch Size: " + this.batchSize + ", Worker Think Time: " + this.workerThinkTime);
        this.data = new byte[this.recordSize];
        for (int i = 0; i < this.data.length; ++i) {
            this.data[i] = (byte)i;
        }
        System.out.println("==============================================");
        System.out.println("===> Start the server now.");
        System.out.println("==============================================");
        this.reconnect();
        System.out.println("Starting " + this.workerCount + " Workers...");
        final ArrayList<Worker> workers = new ArrayList<Worker>();
        for (int j = 0; j < this.workerCount; ++j) {
            final String workerId = "worker-" + j;
            Worker w = new ConsumerWorker(this.connection.createSession(true, 0), workerId, 5000L);
            workers.add(w);
            new Thread(w, "Consumer:" + workerId).start();
            w = new ProducerWorker(this.connection.createSession(true, 0), workerId);
            workers.add(w);
            new Thread(w, "Producer:" + workerId).start();
        }
        System.out.println("Waiting for " + this.workerCount * 10 + " batches to be delivered.");
        while (this.publishedBatches.get() < this.workerCount * 5) {
            System.out.println("Stats: Produced Batches: " + this.publishedBatches.get() + ", Consumed Batches: " + this.consumedBatches.get());
            Thread.sleep(1000L);
        }
        System.out.println("==============================================");
        System.out.println("===> Server is under load now.  Kill it!");
        System.out.println("==============================================");
        this.ignoreJMSErrors.set(true);
        System.out.println("Waiting for all workers to exit due to server shutdown.");
        for (final Worker worker : workers) {
            while (!worker.waitForExit(1000L)) {
                System.out.println("==============================================");
                System.out.println("===> Server is under load now.  Kill it!");
                System.out.println("==============================================");
                System.out.println("Stats: Produced Batches: " + this.publishedBatches.get() + ", Consumed Batches: " + this.consumedBatches.get());
            }
        }
        workers.clear();
        if (this.errors.size() > 0) {
            throw this.errors.get(0);
        }
        System.out.println("==============================================");
        System.out.println("===> Start the server now.");
        System.out.println("==============================================");
        this.reconnect();
        System.out.println("Restarted.");
        for (int j = 0; j < this.workerCount; ++j) {
            final String workerId = "worker-" + j;
            final Worker w = new ConsumerWorker(this.connection.createSession(true, 0), workerId, 5000L);
            workers.add(w);
            new Thread(w, "Consumer:" + workerId).start();
        }
        System.out.println("Waiting for restarted consumers to finish consuming all messages..");
        for (final Worker worker : workers) {
            while (!worker.waitForExit(5000L)) {
                System.out.println("Waiting for restarted consumers to finish consuming all messages..");
                System.out.println("Stats: Produced Batches: " + this.publishedBatches.get() + ", Consumed Batches: " + this.consumedBatches.get());
            }
        }
        workers.clear();
        System.out.println("Workers finished..");
        System.out.println("Stats: Produced Batches: " + this.publishedBatches.get() + ", Consumed Batches: " + this.consumedBatches.get());
        if (this.errors.size() > 0) {
            throw this.errors.get(0);
        }
    }
    
    public static void main(final String[] args) {
        try {
            final AcidTestTool tool = new AcidTestTool();
            tool.setUp();
            tool.testAcidTransactions();
            tool.tearDown();
        }
        catch (Throwable e) {
            System.out.println("Test Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private final class ProducerWorker implements Worker
    {
        private Session session;
        private MessageProducer producer;
        private BytesMessage message;
        private CountDownLatch doneLatch;
        
        ProducerWorker(final Session session, final String workerId) throws JMSException {
            this.doneLatch = new CountDownLatch(1);
            this.session = session;
            (this.producer = session.createProducer(AcidTestTool.this.target)).setDeliveryMode(2);
            (this.message = session.createBytesMessage()).setStringProperty("workerId", workerId);
            this.message.writeBytes(AcidTestTool.this.data);
        }
        
        @Override
        public void run() {
            try {
                int batchId = 0;
                while (true) {
                    for (int msgId = 0; msgId < AcidTestTool.this.batchSize; ++msgId) {
                        try {
                            Thread.sleep(AcidTestTool.this.random.nextInt(AcidTestTool.this.workerThinkTime));
                        }
                        catch (InterruptedException e3) {
                            return;
                        }
                        this.message.setIntProperty("batch-id", batchId);
                        this.message.setIntProperty("msg-id", msgId);
                        this.producer.send(this.message);
                    }
                    this.session.commit();
                    AcidTestTool.this.publishedBatches.incrementAndGet();
                    ++batchId;
                }
            }
            catch (JMSException e) {
                if (!AcidTestTool.this.ignoreJMSErrors.get()) {
                    e.printStackTrace();
                    AcidTestTool.this.errors.add(e);
                }
            }
            catch (Throwable e2) {
                e2.printStackTrace();
                AcidTestTool.this.errors.add(e2);
            }
            finally {
                System.out.println("Producer exiting.");
                this.doneLatch.countDown();
            }
        }
        
        @Override
        public boolean waitForExit(final long i) throws InterruptedException {
            return this.doneLatch.await(i, TimeUnit.MILLISECONDS);
        }
    }
    
    private final class ConsumerWorker implements Worker
    {
        private Session session;
        private MessageConsumer consumer;
        private final long timeout;
        private CountDownLatch doneLatch;
        
        ConsumerWorker(final Session session, final String workerId, final long timeout) throws JMSException {
            this.doneLatch = new CountDownLatch(1);
            this.session = session;
            this.timeout = timeout;
            this.consumer = session.createConsumer(AcidTestTool.this.target, "workerId='" + workerId + "'");
        }
        
        @Override
        public void run() {
            try {
                int batchId = 0;
            Block_5:
                while (true) {
                    for (int msgId = 0; msgId < AcidTestTool.this.batchSize; ++msgId) {
                        try {
                            Thread.sleep(AcidTestTool.this.random.nextInt(AcidTestTool.this.workerThinkTime));
                        }
                        catch (InterruptedException e3) {
                            return;
                        }
                        final Message message = this.consumer.receive(this.timeout);
                        if (msgId > 0) {
                            Assert.assertNotNull((Object)message);
                            Assert.assertEquals(message.getIntProperty("batch-id"), batchId);
                            Assert.assertEquals(message.getIntProperty("msg-id"), msgId);
                        }
                        else {
                            if (message == null) {
                                break Block_5;
                            }
                            Assert.assertEquals(msgId, message.getIntProperty("msg-id"));
                            batchId = message.getIntProperty("batch-id");
                        }
                    }
                    this.session.commit();
                    AcidTestTool.this.consumedBatches.incrementAndGet();
                }
                System.out.println("At end of batch an don't have a next batch to process.  done.");
            }
            catch (JMSException e) {
                if (!AcidTestTool.this.ignoreJMSErrors.get()) {
                    e.printStackTrace();
                    AcidTestTool.this.errors.add(e);
                }
            }
            catch (Throwable e2) {
                e2.printStackTrace();
                AcidTestTool.this.errors.add(e2);
            }
            finally {
                System.out.println("Consumer exiting.");
                this.doneLatch.countDown();
            }
        }
        
        @Override
        public boolean waitForExit(final long i) throws InterruptedException {
            return this.doneLatch.await(i, TimeUnit.MILLISECONDS);
        }
    }
    
    private interface Worker extends Runnable
    {
        boolean waitForExit(final long p0) throws InterruptedException;
    }
}
