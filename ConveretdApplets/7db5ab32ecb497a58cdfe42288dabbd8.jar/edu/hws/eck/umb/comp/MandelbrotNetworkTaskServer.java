// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.comp;

import java.io.IOException;
import java.util.TimerTask;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.concurrent.ArrayBlockingQueue;

public class MandelbrotNetworkTaskServer
{
    public static final String HANDSHAKE = "DISTRIBUTED ULTIMATE MANDELBROT";
    public static final String NEWJOB = "NEWJOB";
    public static final String SIGNOFF = "SIGNOFF";
    public static final String SHUTDOWN = "SHUTDOWN";
    public static final int DEFAULT_PORT = 17071;
    private static ArrayBlockingQueue<MandelbrotTask> tasks;
    private static ArrayBlockingQueue<MandelbrotTask> finishedTasks;
    private static Timer timer;
    private static Worker[] workers;
    private static boolean quiet;
    private static volatile int jobNum;
    private static volatile int connectionID;
    
    public static void main(final String[] array) {
        int n = 17071;
        int int1 = -1;
        boolean b = false;
        try {
            int i = 0;
            while (i < array.length) {
                final String s = array[i++];
                if (s.equalsIgnoreCase("-shutdown")) {
                    String s2 = "localhost";
                    if (i < array.length) {
                        s2 = array[i];
                    }
                    if (i + 1 < array.length) {
                        try {
                            n = Integer.parseInt(array[i + 1]);
                        }
                        catch (NumberFormatException ex3) {}
                    }
                    println("Sending SHUTDOWN command to " + s2 + ":" + n);
                    doShutdown(s2, n);
                    println("Command sent; exiting.");
                    System.exit(0);
                }
                if (s.equalsIgnoreCase("-port")) {
                    n = Integer.parseInt(array[i++]);
                }
                else if (s.equalsIgnoreCase("-processcount")) {
                    int1 = Integer.parseInt(array[i++]);
                }
                else if (s.equalsIgnoreCase("-timeout")) {
                    TimeoutTask.timeout = Integer.parseInt(array[i++]);
                }
                else if (s.equalsIgnoreCase("-once")) {
                    b = true;
                }
                else {
                    if (!s.equalsIgnoreCase("-quiet")) {
                        throw new IllegalArgumentException();
                    }
                    MandelbrotNetworkTaskServer.quiet = true;
                }
            }
        }
        catch (Exception ex4) {
            err_println("Bad command line argument.");
            System.exit(1);
            return;
        }
        MandelbrotNetworkTaskServer.tasks = new ArrayBlockingQueue<MandelbrotTask>(25);
        MandelbrotNetworkTaskServer.finishedTasks = new ArrayBlockingQueue<MandelbrotTask>(25);
        try {
            if (int1 <= 0) {
                final int availableProcessors = Runtime.getRuntime().availableProcessors();
                if (availableProcessors == 1 || int1 == 0) {
                    int1 = availableProcessors;
                }
                else {
                    int1 = availableProcessors - 1;
                }
            }
            MandelbrotNetworkTaskServer.workers = new Worker[int1];
            for (int j = 0; j < MandelbrotNetworkTaskServer.workers.length; ++j) {
                (MandelbrotNetworkTaskServer.workers[j] = new Worker()).setDaemon(true);
                MandelbrotNetworkTaskServer.workers[j].setPriority(MandelbrotNetworkTaskServer.workers[j].getPriority() - 1);
                MandelbrotNetworkTaskServer.workers[j].start();
            }
        }
        catch (Exception ex5) {
            err_println("Cound not create worker threads.");
            System.exit(1);
            return;
        }
        MandelbrotNetworkTaskServer.timer = new Timer(true);
        while (true) {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(n);
            }
            catch (Exception ex) {
                err_println("Could not create listener on port " + n);
                err_println("Error: " + ex);
                System.exit(1);
                return;
            }
            println("Listening on port " + n + "...");
            TimeoutTask.set();
            try {
                final Socket accept = serverSocket.accept();
                serverSocket.close();
                ++MandelbrotNetworkTaskServer.connectionID;
                final ConnectionThread connectionThread = new ConnectionThread(accept, MandelbrotNetworkTaskServer.connectionID);
                connectionThread.start();
                while (connectionThread.isAlive()) {
                    try {
                        connectionThread.join();
                    }
                    catch (InterruptedException ex6) {}
                }
                if (b) {
                    System.exit(0);
                }
                TimeoutTask.set();
            }
            catch (Exception ex2) {
                err_println("Server socket has shut down unexpectedly.");
                err_println("Error: " + ex2);
                System.exit(1);
            }
        }
    }
    
    private static void doShutdown(final String s, final int n) {
        try {
            final PrintWriter printWriter = new PrintWriter(new Socket(s, n).getOutputStream());
            printWriter.println("DISTRIBUTED ULTIMATE MANDELBROT");
            printWriter.println("SHUTDOWN");
            printWriter.flush();
            printWriter.close();
        }
        catch (Exception ex) {
            err_println("An error occurred while trying to send the SHUTDOWN: " + ex);
            System.exit(1);
        }
    }
    
    private static void println(final String s) {
        if (!MandelbrotNetworkTaskServer.quiet) {
            System.out.println(s);
        }
    }
    
    private static void err_println(final String s) {
        if (!MandelbrotNetworkTaskServer.quiet) {
            System.err.println(s);
        }
    }
    
    public static String encode(final MandelbrotTask mandelbrotTask) {
        final StringBuffer sb = new StringBuffer();
        sb.append(mandelbrotTask.getJobNumber());
        sb.append(' ');
        sb.append(mandelbrotTask.getRowNumber());
        sb.append(' ');
        sb.append(mandelbrotTask.getXmin());
        sb.append(' ');
        sb.append(mandelbrotTask.getXmax());
        sb.append(' ');
        sb.append(mandelbrotTask.getYval());
        sb.append(' ');
        sb.append(mandelbrotTask.getColumnCount());
        sb.append(' ');
        sb.append(mandelbrotTask.getMaxIterations());
        sb.append(' ');
        sb.append(mandelbrotTask.isHighPrecision());
        return sb.toString();
    }
    
    private static MandelbrotTask decode(final String s) {
        final Scanner scanner = new Scanner(s);
        final int nextInt = scanner.nextInt();
        final MandelbrotTask mandelbrotTask = new MandelbrotTask(scanner.nextInt(), scanner.nextBigDecimal(), scanner.nextBigDecimal(), scanner.nextBigDecimal(), scanner.nextInt(), scanner.nextInt(), scanner.nextBoolean());
        mandelbrotTask.setJobNumber(nextInt);
        return mandelbrotTask;
    }
    
    private static void endJob(final int jobNum) {
        synchronized (MandelbrotNetworkTaskServer.tasks) {
            MandelbrotNetworkTaskServer.jobNum = jobNum;
            MandelbrotNetworkTaskServer.tasks.clear();
            MandelbrotNetworkTaskServer.tasks.notifyAll();
        }
    }
    
    static {
        MandelbrotNetworkTaskServer.timer = new Timer(true);
    }
    
    private static class TimeoutTask extends TimerTask
    {
        static TimerTask task;
        static int timeout;
        
        static synchronized void set() {
            if (TimeoutTask.task != null) {
                TimeoutTask.task.cancel();
            }
            if (TimeoutTask.timeout > 0) {
                TimeoutTask.task = new TimeoutTask();
                MandelbrotNetworkTaskServer.timer.schedule(TimeoutTask.task, TimeoutTask.timeout * 60000L);
            }
        }
        
        static synchronized void clear() {
            if (TimeoutTask.task != null) {
                TimeoutTask.task.cancel();
            }
            TimeoutTask.task = null;
        }
        
        public void run() {
            println("Exiting because activity timeout has expired.");
            System.exit(0);
        }
        
        static {
            TimeoutTask.timeout = 30;
        }
    }
    
    private static class ConnectionThread extends Thread
    {
        final Socket socket;
        final int myConnectionID;
        volatile int tasksDone;
        
        ConnectionThread(final Socket socket, final int myConnectionID) {
            this.socket = socket;
            this.myConnectionID = myConnectionID;
            this.setDaemon(true);
        }
        
        public void run() {
            Object inetAddress = null;
            try {
                inetAddress = this.socket.getInetAddress();
                println("Connected to " + inetAddress);
                final PrintWriter printWriter = new PrintWriter(this.socket.getOutputStream());
                final Scanner scanner = new Scanner(this.socket.getInputStream());
                printWriter.println("DISTRIBUTED ULTIMATE MANDELBROT " + MandelbrotNetworkTaskServer.workers.length);
                printWriter.flush();
                try {
                    if (!scanner.nextLine().equals("DISTRIBUTED ULTIMATE MANDELBROT")) {
                        throw new IllegalArgumentException();
                    }
                }
                catch (Exception ex2) {
                    throw new IOException("The other side of the connection is not a Mandelbrot client.");
                }
                final ReaderThread readerThread = new ReaderThread(scanner);
                readerThread.start();
            Label_0335:
                while (MandelbrotNetworkTaskServer.connectionID == this.myConnectionID) {
                    MandelbrotTask mandelbrotTask = null;
                    while (mandelbrotTask == null && MandelbrotNetworkTaskServer.connectionID == this.myConnectionID) {
                        try {
                            mandelbrotTask = MandelbrotNetworkTaskServer.finishedTasks.take();
                            continue;
                        }
                        catch (InterruptedException ex3) {
                            break Label_0335;
                        }
                        break;
                    }
                    ++this.tasksDone;
                    if (mandelbrotTask.getJobNumber() == MandelbrotNetworkTaskServer.jobNum) {
                        final int[] results = mandelbrotTask.getResults();
                        printWriter.print(mandelbrotTask.getJobNumber());
                        printWriter.print(' ');
                        printWriter.print(mandelbrotTask.getRowNumber());
                        printWriter.print(' ');
                        printWriter.print(results.length);
                        printWriter.print(' ');
                        for (int i = 0; i < results.length - 1; ++i) {
                            printWriter.print(results[i]);
                            printWriter.print(' ');
                        }
                        printWriter.println(results[results.length - 1]);
                        printWriter.flush();
                        if (printWriter.checkError()) {
                            throw new IOException("Error while trying to transmit data.");
                        }
                        continue;
                    }
                }
                if (readerThread.error != null) {
                    throw readerThread.error;
                }
                println("Connection from " + inetAddress + " closed normally.");
            }
            catch (Exception ex) {
                err_println("Connection from " + inetAddress + " closed with error :" + ex);
            }
            finally {
                endJob(-1);
                try {
                    this.socket.close();
                }
                catch (Exception ex4) {}
            }
        }
        
        private class ReaderThread extends Thread
        {
            Scanner in;
            volatile Exception error;
            
            public ReaderThread(final Scanner in) {
                this.in = in;
                this.setDaemon(true);
            }
            
            public void run() {
                try {
                    try {
                        while (true) {
                            final String nextLine = this.in.nextLine();
                            TimeoutTask.set();
                            if (nextLine.equals("SHUTDOWN")) {
                                System.exit(0);
                                break;
                            }
                            if (nextLine.equals("SIGNOFF")) {
                                return;
                            }
                            if (!nextLine.startsWith("NEWJOB")) {
                                break;
                            }
                            final int int1 = Integer.parseInt(nextLine.substring("NEWJOB".length()).trim());
                            println("Starting job " + int1 + "; " + ConnectionThread.this.tasksDone + " tasks completed.");
                            endJob(int1);
                        }
                    }
                    catch (Exception ex) {
                        return;
                        MandelbrotTask access$900;
                        try {
                            final String nextLine;
                            access$900 = decode(nextLine);
                        }
                        catch (Exception ex2) {
                            throw new IOException("Illegal Mandelbot task data received.");
                        }
                        MandelbrotNetworkTaskServer.tasks.put(access$900);
                    }
                }
                catch (Exception error) {
                    this.error = error;
                }
                finally {
                    synchronized (MandelbrotNetworkTaskServer.tasks) {
                        if (MandelbrotNetworkTaskServer.connectionID == ConnectionThread.this.myConnectionID) {
                            MandelbrotNetworkTaskServer.connectionID++;
                        }
                        MandelbrotNetworkTaskServer.tasks.notifyAll();
                    }
                    ConnectionThread.this.interrupt();
                }
            }
        }
    }
    
    private static class Worker extends Thread
    {
        public void run() {
            MandelbrotTask mandelbrotTask = null;
            while (true) {
                if (mandelbrotTask == null) {
                    try {
                        mandelbrotTask = MandelbrotNetworkTaskServer.tasks.take();
                    }
                    catch (InterruptedException ex) {}
                }
                else {
                    mandelbrotTask.run();
                    while (mandelbrotTask != null) {
                        try {
                            MandelbrotNetworkTaskServer.finishedTasks.put(mandelbrotTask);
                            mandelbrotTask = null;
                        }
                        catch (InterruptedException ex2) {}
                    }
                }
            }
        }
    }
}
