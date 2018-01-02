// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.comp;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class TaskManager
{
    private ThreadPool threadPool;
    private boolean fullShutDown;
    private Job currentJob;
    private int workerCount;
    public static int NET_STATUS_INACTIVE;
    public static int NET_STATUS_CONNECTING;
    public static int NET_STATUS_CONNECTED;
    public static int NET_STATUS_CLOSING;
    public static int NET_STATUS_CLOSED;
    public static int NET_STATUS_ERROR_CANT_CONNECT;
    public static int NET_STATUS_ERROR_WRITE_ERROR;
    public static int NET_STATUS_ERROR_READ_ERROR;
    public static int NET_STATUS_ERROR_BAD_PEER;
    private boolean networkingEnabled;
    private ArrayList<NetworkWorker> networkWorkers;
    private int nextNetworkWorkerID;
    
    public TaskManager() {
        this(0);
    }
    
    public TaskManager(int availableProcessors) {
        if (availableProcessors <= 0) {
            availableProcessors = Runtime.getRuntime().availableProcessors();
        }
        this.threadPool = new ThreadPool(this, availableProcessors);
        this.workerCount = availableProcessors;
    }
    
    public synchronized void shutDown() {
        if (this.fullShutDown) {
            return;
        }
        this.fullShutDown = true;
        if (this.currentJob != null) {
            this.currentJob.cancel();
        }
        if (this.threadPool != null) {
            this.threadPool.shutDown = true;
        }
        this.threadPool = null;
        this.shutDownNetwork();
        this.notifyAll();
    }
    
    public int getThreadPoolSize() {
        return this.workerCount;
    }
    
    public synchronized void setThreadPoolSize(final int workerCount) {
        this.workerCount = workerCount;
    }
    
    public synchronized Job createJob() {
        if (this.fullShutDown) {
            throw new IllegalStateException("Can't execute tasks after shutdown.");
        }
        if (this.currentJob != null) {
            this.currentJob.cancel();
        }
        if (this.workerCount != this.threadPool.getSize()) {
            this.threadPool.shutDown = true;
            this.notifyAll();
            this.threadPool = new ThreadPool(this, this.workerCount);
        }
        this.currentJob = new Job(this, (Collection)null);
        this.newNetworkJob(this.currentJob.jobID);
        return this.currentJob;
    }
    
    public synchronized boolean busy() {
        return this.currentJob != null;
    }
    
    private synchronized void finish(final Job job) {
        if (this.currentJob == job) {
            this.currentJob = null;
            if (this.workerCount != this.threadPool.getSize()) {
                this.threadPool.shutDown = true;
            }
        }
        this.notifyAll();
    }
    
    private synchronized Object[] nextTask(final boolean b) {
        if (this.currentJob == null || this.fullShutDown) {
            return null;
        }
        final MandelbrotTask access$300 = this.currentJob.nextTask(b);
        if (access$300 == null) {
            return null;
        }
        return new Object[] { access$300, this.currentJob };
    }
    
    public boolean getNetworkingEnabled() {
        return this.networkingEnabled;
    }
    
    public void setNetworkingEnabled(final boolean networkingEnabled) {
        if (networkingEnabled == this.networkingEnabled) {
            return;
        }
        this.networkingEnabled = networkingEnabled;
        if (!networkingEnabled && this.networkWorkers != null) {
            final Iterator<NetworkWorker> iterator = this.networkWorkers.iterator();
            while (iterator.hasNext()) {
                iterator.next().finish(false);
            }
            this.networkWorkers = null;
        }
    }
    
    public int addNetworkWorker(final String s, final int n) {
        if (!this.networkingEnabled) {
            throw new IllegalStateException("Can't add network worker when networing is disabled");
        }
        final NetworkWorker networkWorker = new NetworkWorker(s, n, this.currentJob);
        if (this.networkWorkers == null) {
            this.networkWorkers = new ArrayList<NetworkWorker>();
        }
        this.networkWorkers.add(networkWorker);
        networkWorker.start();
        return networkWorker.id;
    }
    
    public int getNetworkWorkerCount() {
        if (this.networkWorkers == null) {
            return 0;
        }
        return this.networkWorkers.size();
    }
    
    public NetworkWorkerInfo getNetworkWorkerInfo(final int n) {
        if (this.networkWorkers == null) {
            return null;
        }
        for (final NetworkWorker networkWorker : this.networkWorkers) {
            if (networkWorker.id == n) {
                return new NetworkWorkerInfo(networkWorker.host, networkWorker.port, networkWorker.status, networkWorker.tasksDone, networkWorker.id);
            }
        }
        return null;
    }
    
    public NetworkWorkerInfo[] getAllNetworkWorkerInfo() {
        if (this.networkWorkers == null) {
            return new NetworkWorkerInfo[0];
        }
        final NetworkWorkerInfo[] array = new NetworkWorkerInfo[this.networkWorkers.size()];
        for (int i = 0; i < array.length; ++i) {
            final NetworkWorker networkWorker = this.networkWorkers.get(i);
            array[i] = new NetworkWorkerInfo(networkWorker.host, networkWorker.port, networkWorker.status, networkWorker.tasksDone, networkWorker.id);
        }
        return array;
    }
    
    public boolean removeNetworkWorker(final int n) {
        if (this.networkWorkers == null) {
            return false;
        }
        for (int i = 0; i < this.networkWorkers.size(); ++i) {
            if (this.networkWorkers.get(i).id == n) {
                this.networkWorkers.get(i).finish(false);
                this.networkWorkers.remove(i);
                return true;
            }
        }
        return false;
    }
    
    private void shutDownNetwork() {
        if (this.networkWorkers == null) {
            return;
        }
        final Iterator<NetworkWorker> iterator = this.networkWorkers.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish(true);
        }
        this.networkWorkers = null;
    }
    
    private void newNetworkJob(final int n) {
        if (this.networkWorkers == null) {
            return;
        }
        final Iterator<NetworkWorker> iterator = this.networkWorkers.iterator();
        while (iterator.hasNext()) {
            iterator.next().newJob(n);
        }
    }
    
    static {
        TaskManager.NET_STATUS_INACTIVE = 0;
        TaskManager.NET_STATUS_CONNECTING = 1;
        TaskManager.NET_STATUS_CONNECTED = 2;
        TaskManager.NET_STATUS_CLOSING = 3;
        TaskManager.NET_STATUS_CLOSED = 4;
        TaskManager.NET_STATUS_ERROR_CANT_CONNECT = -1;
        TaskManager.NET_STATUS_ERROR_WRITE_ERROR = -2;
        TaskManager.NET_STATUS_ERROR_READ_ERROR = -3;
        TaskManager.NET_STATUS_ERROR_BAD_PEER = -4;
    }
    
    public static class Job
    {
        private static int nextJobID;
        private final int jobID;
        private final TaskManager owner;
        private final ArrayList<MandelbrotTask> tasks;
        private volatile boolean closed;
        private volatile boolean finished;
        private volatile boolean canceled;
        private int nextTask;
        private int nextRepeatTask;
        private volatile int finishedTaskCount;
        private final ArrayList<MandelbrotTask> waitingFinishedTasks;
        private final ArrayList<MandelbrotTask> networkedTasks;
        
        private Job(final TaskManager owner, final Collection<? extends MandelbrotTask> collection) {
            this.jobID = Job.nextJobID++;
            this.owner = owner;
            if (collection == null) {
                this.tasks = new ArrayList<MandelbrotTask>();
            }
            else {
                this.tasks = new ArrayList<MandelbrotTask>(collection);
            }
            for (int i = this.tasks.size() - 1; i >= 0; --i) {
                if (this.tasks.get(i) == null) {
                    this.tasks.remove(i);
                }
            }
            this.waitingFinishedTasks = new ArrayList<MandelbrotTask>();
            this.networkedTasks = new ArrayList<MandelbrotTask>();
        }
        
        private void finish(final MandelbrotTask mandelbrotTask) {
            synchronized (this.owner) {
                if (this.finished || mandelbrotTask.getJobNumber() != this.jobID || mandelbrotTask.isDone()) {
                    return;
                }
                mandelbrotTask.makeDone();
                ++this.finishedTaskCount;
                this.waitingFinishedTasks.add(mandelbrotTask);
                if (this.closed && this.finishedTaskCount == this.tasks.size()) {
                    this.finished = true;
                    this.owner.finish(this);
                }
            }
        }
        
        private MandelbrotTask nextTask(final boolean b) {
            synchronized (this.owner) {
                if (this.finished) {
                    return null;
                }
                if (this.nextTask < this.tasks.size()) {
                    final MandelbrotTask mandelbrotTask = this.tasks.get(this.nextTask++);
                    if (b) {
                        this.networkedTasks.add(mandelbrotTask);
                    }
                    return mandelbrotTask;
                }
                if (b || !this.closed) {
                    return null;
                }
                if (this.nextRepeatTask >= this.networkedTasks.size()) {
                    return null;
                }
                while (this.nextRepeatTask < this.networkedTasks.size()) {
                    final MandelbrotTask mandelbrotTask2 = this.networkedTasks.get(this.nextRepeatTask++);
                    if (!mandelbrotTask2.isDone()) {
                        return mandelbrotTask2;
                    }
                }
                return null;
            }
        }
        
        public void add(final MandelbrotTask mandelbrotTask) {
            if (mandelbrotTask == null) {
                return;
            }
            if (this.closed) {
                throw new IllegalStateException("Can't add a new task to a job after the job has been closed.");
            }
            synchronized (this.owner) {
                mandelbrotTask.setJobNumber(this.jobID);
                this.tasks.add(mandelbrotTask);
                this.owner.notifyAll();
            }
        }
        
        public synchronized void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.finishedTaskCount == this.tasks.size()) {
                this.finished = true;
                this.owner.finish(this);
            }
        }
        
        public double fractionDone() {
            if (this.tasks.size() == 0) {
                return 1.0;
            }
            return this.finishedTaskCount / this.tasks.size();
        }
        
        public int finishedTaskCount() {
            return this.finishedTaskCount;
        }
        
        public int totalTaskCount() {
            return this.tasks.size();
        }
        
        public void cancel() {
            synchronized (this.owner) {
                this.finished = true;
                this.canceled = true;
                this.closed = true;
                this.owner.finish(this);
            }
        }
        
        public boolean isFinished() {
            return this.finished;
        }
        
        public boolean isCanceled() {
            return this.canceled;
        }
        
        public MandelbrotTask[] finishedTasks() {
            synchronized (this.owner) {
                if (this.waitingFinishedTasks.size() == 0) {
                    return new MandelbrotTask[0];
                }
                final MandelbrotTask[] array = new MandelbrotTask[this.waitingFinishedTasks.size()];
                this.waitingFinishedTasks.toArray(array);
                this.waitingFinishedTasks.clear();
                return array;
            }
        }
        
        public boolean await(final int n) {
            synchronized (this.owner) {
                if (this.finished) {
                    return true;
                }
                try {
                    if (n <= 0) {
                        this.owner.wait();
                    }
                    else {
                        this.owner.wait(n);
                    }
                }
                catch (InterruptedException ex) {}
                return this.finished;
            }
        }
        
        static {
            Job.nextJobID = 1;
        }
    }
    
    private static class ThreadPool
    {
        final Worker[] pool;
        final TaskManager owner;
        volatile boolean shutDown;
        
        ThreadPool(final TaskManager owner, final int n) {
            this.owner = owner;
            this.pool = new Worker[n];
            final int priority = Thread.currentThread().getPriority();
            for (int i = 0; i < n; ++i) {
                (this.pool[i] = new Worker()).setDaemon(true);
                try {
                    this.pool[i].setPriority(priority - 1);
                }
                catch (Exception ex) {}
                this.pool[i].start();
            }
        }
        
        int getSize() {
            return this.pool.length;
        }
        
        class Worker extends Thread
        {
            public void run() {
                int n = 0;
                try {
                    while (!ThreadPool.this.shutDown) {
                        Object[] access$400;
                        do {
                            synchronized (ThreadPool.this.owner) {
                                access$400 = ThreadPool.this.owner.nextTask(false);
                                if (access$400 != null || ThreadPool.this.shutDown) {
                                    continue;
                                }
                                try {
                                    ThreadPool.this.owner.wait();
                                }
                                catch (InterruptedException ex) {}
                            }
                        } while (access$400 == null && !ThreadPool.this.shutDown);
                        if (ThreadPool.this.shutDown) {
                            break;
                        }
                        final MandelbrotTask mandelbrotTask = (MandelbrotTask)access$400[0];
                        final Job job = (Job)access$400[1];
                        mandelbrotTask.run();
                        ++n;
                        job.finish(mandelbrotTask);
                    }
                }
                finally {
                    System.out.println("Compute thread exiting after " + n + " tasks.");
                }
            }
        }
    }
    
    public static class NetworkWorkerInfo
    {
        public final String host;
        public final int port;
        public final int status;
        public final int tasksDone;
        public final int workerID;
        
        public NetworkWorkerInfo(final String host, final int port, final int status, final int tasksDone, final int workerID) {
            this.host = host;
            this.port = port;
            this.status = status;
            this.tasksDone = tasksDone;
            this.workerID = workerID;
        }
    }
    
    private class NetworkWorker extends Thread
    {
        final String host;
        final int port;
        final int id;
        int peerProcessCount;
        String outgoingMessage;
        volatile int tasksDone;
        volatile int status;
        ArrayList<Object[]> outstandingTasks;
        
        void newJob(final int n) {
            this.sendMessage("NEWJOB " + n);
        }
        
        synchronized void finish(final boolean b) {
            if (this.status == TaskManager.NET_STATUS_CONNECTED || this.status == TaskManager.NET_STATUS_CONNECTING) {
                this.status = TaskManager.NET_STATUS_CLOSING;
                if (b) {
                    this.outgoingMessage = "SHUTDOWN";
                }
                else {
                    this.outgoingMessage = "SIGNOFF";
                }
            }
            this.notifyAll();
            this.interrupt();
        }
        
        synchronized void newTask(final Object[] array) {
            this.outstandingTasks.add(array);
        }
        
        synchronized void sendMessage(final String outgoingMessage) {
            this.outgoingMessage = outgoingMessage;
            this.notifyAll();
        }
        
        synchronized void finishTask(final int n, final int n2, final int[] results) {
            for (int i = 0; i < this.outstandingTasks.size(); ++i) {
                final Object[] array = this.outstandingTasks.get(i);
                final MandelbrotTask mandelbrotTask = (MandelbrotTask)array[0];
                if (mandelbrotTask.getJobNumber() == n && mandelbrotTask.getRowNumber() == n2) {
                    this.outstandingTasks.remove(i);
                    this.notifyAll();
                    ((Job)array[1]).finish(mandelbrotTask);
                    mandelbrotTask.setResults(results);
                    ++this.tasksDone;
                    return;
                }
            }
        }
        
        NetworkWorker(final String host, final int port, final Job job) {
            this.outstandingTasks = new ArrayList<Object[]>();
            this.host = host;
            this.port = port;
            this.id = TaskManager.this.nextNetworkWorkerID++;
            if (job != null) {
                this.outgoingMessage = "NEWJOB " + job.jobID;
            }
            this.status = TaskManager.NET_STATUS_CONNECTING;
            try {
                this.setPriority(Thread.currentThread().getPriority() - 1);
                this.setDaemon(true);
            }
            catch (Exception ex) {}
            System.out.println("Created Network Worker");
        }
        
        public void run() {
            Socket socket;
            BufferedReader bufferedReader;
            PrintWriter printWriter;
            try {
                socket = new Socket(this.host, this.port);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream());
            }
            catch (Exception ex) {
                this.status = TaskManager.NET_STATUS_ERROR_CANT_CONNECT;
                System.out.println("Network thread exiting after 0 tasks; status = " + this.status);
                return;
            }
            if (this.status == TaskManager.NET_STATUS_CLOSING) {
                return;
            }
            System.out.println("Connected");
            try {
                printWriter.println("DISTRIBUTED ULTIMATE MANDELBROT");
                printWriter.flush();
                if (printWriter.checkError()) {
                    throw new Exception();
                }
                final String line = bufferedReader.readLine();
                if (line == null || !line.startsWith("DISTRIBUTED ULTIMATE MANDELBROT")) {
                    this.status = TaskManager.NET_STATUS_ERROR_BAD_PEER;
                    return;
                }
                try {
                    this.peerProcessCount = Integer.parseInt(line.substring("DISTRIBUTED ULTIMATE MANDELBROT".length()).trim());
                }
                catch (NumberFormatException ex2) {
                    this.peerProcessCount = 1;
                }
                if (this.peerProcessCount <= 0) {
                    this.peerProcessCount = 1;
                }
                this.status = TaskManager.NET_STATUS_CONNECTED;
                new ReaderThread(bufferedReader).start();
                while (true) {
                    if (this.outgoingMessage != null) {
                        printWriter.println(this.outgoingMessage);
                        printWriter.flush();
                        this.outgoingMessage = null;
                    }
                    if (this.status != TaskManager.NET_STATUS_CONNECTED) {
                        break;
                    }
                    synchronized (this) {
                        while (this.status == TaskManager.NET_STATUS_CONNECTED && this.outstandingTasks.size() >= this.peerProcessCount) {
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex3) {}
                        }
                    }
                    Object[] access$400 = null;
                    while (access$400 == null && this.status == TaskManager.NET_STATUS_CONNECTED) {
                        synchronized (TaskManager.this) {
                            access$400 = TaskManager.this.nextTask(true);
                            if (access$400 != null || this.status != TaskManager.NET_STATUS_CONNECTED) {
                                continue;
                            }
                            try {
                                TaskManager.this.wait();
                            }
                            catch (InterruptedException ex4) {}
                        }
                    }
                    if (access$400 == null || this.status != TaskManager.NET_STATUS_CONNECTED) {
                        continue;
                    }
                    if (this.outgoingMessage != null) {
                        printWriter.println(this.outgoingMessage);
                        this.outgoingMessage = null;
                    }
                    this.newTask(access$400);
                    printWriter.println(MandelbrotNetworkTaskServer.encode((MandelbrotTask)access$400[0]));
                    printWriter.flush();
                    if (printWriter.checkError()) {
                        throw new Exception();
                    }
                }
            }
            catch (Exception ex5) {
                if (this.status != TaskManager.NET_STATUS_CLOSING) {
                    this.status = TaskManager.NET_STATUS_ERROR_WRITE_ERROR;
                }
            }
            finally {
                try {
                    socket.close();
                }
                catch (Exception ex6) {}
                if (this.status == TaskManager.NET_STATUS_CLOSING) {
                    this.status = TaskManager.NET_STATUS_CLOSED;
                }
                System.out.println("Network thread exiting after " + this.tasksDone + " tasks; status = " + this.status);
            }
        }
        
        class ReaderThread extends Thread
        {
            BufferedReader in;
            
            ReaderThread(final BufferedReader in) {
                this.in = in;
                this.setDaemon(true);
            }
            
            public void run() {
                try {
                    while (NetworkWorker.this.status == TaskManager.NET_STATUS_CONNECTED) {
                        final String line = this.in.readLine();
                        if (line == null) {
                            throw new Exception();
                        }
                        final Scanner scanner = new Scanner(line);
                        final int nextInt = scanner.nextInt();
                        final int nextInt2 = scanner.nextInt();
                        final int nextInt3 = scanner.nextInt();
                        final int[] array = new int[nextInt3];
                        for (int i = 0; i < nextInt3; ++i) {
                            array[i] = scanner.nextInt();
                        }
                        scanner.close();
                        if (NetworkWorker.this.status != TaskManager.NET_STATUS_CONNECTED) {
                            continue;
                        }
                        NetworkWorker.this.finishTask(nextInt, nextInt2, array);
                    }
                }
                catch (Exception ex) {
                    if (NetworkWorker.this.status == TaskManager.NET_STATUS_CONNECTED) {
                        NetworkWorker.this.status = TaskManager.NET_STATUS_ERROR_READ_ERROR;
                    }
                }
            }
        }
    }
}
