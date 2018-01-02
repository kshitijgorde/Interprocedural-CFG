// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.bandwidthtest;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import java.net.URL;
import java.util.Date;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.EventQueue;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.ArrayList;
import javax.swing.JApplet;

public class BandwidthTester extends JApplet
{
    String strCallBackContext;
    boolean isSecure;
    ArrayList record;
    Timer timer;
    long totalsend;
    long interByteSent;
    String method;
    int block;
    int totalToSend;
    DecimalFormat format;
    SimpleDateFormat tformat;
    HistoryList histList;
    long starttime;
    long endtime;
    boolean isComplete;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JLabel timeStart;
    private JTextField txtBitRate;
    private JLabel txtMessage;
    private JProgressBar txtProgress;
    private JTextField txtUploadResult;
    
    public BandwidthTester() {
        this.strCallBackContext = null;
        this.isSecure = false;
        this.record = new ArrayList();
        this.timer = null;
        this.totalsend = 0L;
        this.interByteSent = 0L;
        this.method = "POST";
        this.block = 1024;
        this.totalToSend = 5242880;
        this.format = new DecimalFormat("###,###,##0.00");
        this.tformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        this.histList = new HistoryList();
        this.starttime = 0L;
        this.endtime = 0L;
        this.isComplete = false;
    }
    
    public void run() {
    }
    
    public void init() {
        try {
            final String protocol = super.getCodeBase().getProtocol();
            if (protocol.toLowerCase().equalsIgnoreCase("https")) {
                this.isSecure = true;
            }
            final String sampling = this.getParameter("SAMPLING_SIZE");
            if (sampling != null && Integer.parseInt(sampling) > 0) {
                this.totalToSend = Integer.parseInt(sampling);
            }
            final int intPort = this.getCodeBase().getDefaultPort();
            final String strHost = this.getCodeBase().getHost();
            final String strContext = this.getCodeBase().getPath().substring(0, this.getCodeBase().getPath().indexOf("/", 1));
            this.strCallBackContext = protocol + "://" + strHost + ":" + intPort + strContext;
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    BandwidthTester.this.initComponents();
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.txtProgress = new JProgressBar();
        this.jPanel1 = new JPanel();
        this.jLabel3 = new JLabel();
        this.jButton1 = new JButton();
        this.txtUploadResult = new JTextField();
        this.jLabel2 = new JLabel();
        this.txtMessage = new JLabel();
        this.timeStart = new JLabel();
        this.txtBitRate = new JTextField();
        this.jLabel4 = new JLabel();
        this.getContentPane().setLayout(new GridBagLayout());
        this.jLabel1.setBackground(new Color(255, 255, 255));
        this.jLabel1.setFont(new Font("Arial", 1, 11));
        this.jLabel1.setText("Lightweight Bandwidth Tester v1.1");
        this.jLabel1.setOpaque(true);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jLabel1, gridBagConstraints);
        this.txtProgress.setBackground(new Color(255, 255, 255));
        this.txtProgress.setFont(new Font("Arial", 0, 11));
        this.txtProgress.setStringPainted(true);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(1, 3, 0, 2);
        this.getContentPane().add(this.txtProgress, gridBagConstraints);
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jPanel1.setLayout(new GridBagLayout());
        this.jLabel3.setFont(new Font("Arial", 0, 11));
        this.jLabel3.setText("Average speed");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(7, 5, 5, 1);
        this.jPanel1.add(this.jLabel3, gridBagConstraints);
        this.jButton1.setFont(new Font("Arial", 1, 11));
        this.jButton1.setText("Test now");
        this.jButton1.setMargin(new Insets(2, 10, 2, 10));
        this.jButton1.setPreferredSize(new Dimension(80, 23));
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BandwidthTester.this.jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(4, 0, 3, 2);
        this.jPanel1.add(this.jButton1, gridBagConstraints);
        this.txtUploadResult.setEditable(false);
        this.txtUploadResult.setFont(new Font("Arial", 0, 11));
        this.txtUploadResult.setHorizontalAlignment(0);
        this.txtUploadResult.setText("0");
        this.txtUploadResult.setMinimumSize(new Dimension(100, 20));
        this.txtUploadResult.setPreferredSize(new Dimension(100, 20));
        this.txtUploadResult.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BandwidthTester.this.txtUploadResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(7, 15, 4, 3);
        this.jPanel1.add(this.txtUploadResult, gridBagConstraints);
        this.jLabel2.setFont(new Font("Arial", 0, 11));
        this.jLabel2.setText("kbps");
        this.jLabel2.setToolTipText("kilobits per second");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(3, 3, 3, 2);
        this.jPanel1.add(this.jLabel2, gridBagConstraints);
        this.txtMessage.setFont(new Font("Arial", 1, 14));
        this.txtMessage.setForeground(new Color(0, 51, 255));
        this.txtMessage.setHorizontalAlignment(0);
        this.txtMessage.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = 1;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(3, 2, 4, 3);
        this.jPanel1.add(this.txtMessage, gridBagConstraints);
        this.timeStart.setFont(new Font("Arial", 0, 11));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        this.jPanel1.add(this.timeStart, gridBagConstraints);
        this.txtBitRate.setEditable(false);
        this.txtBitRate.setFont(new Font("Arial", 0, 11));
        this.txtBitRate.setHorizontalAlignment(0);
        this.txtBitRate.setText("0");
        this.txtBitRate.setMinimumSize(new Dimension(50, 20));
        this.txtBitRate.setPreferredSize(new Dimension(100, 20));
        this.txtBitRate.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                BandwidthTester.this.txtBitRateActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 3, 4, 3);
        this.jPanel1.add(this.txtBitRate, gridBagConstraints);
        this.jLabel4.setFont(new Font("Arial", 0, 11));
        this.jLabel4.setText("KB/s");
        this.jLabel4.setToolTipText("Kilobytes Per Second");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(4, 0, 0, 4);
        this.jPanel1.add(this.jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        this.getContentPane().add(this.jPanel1, gridBagConstraints);
    }
    
    private void reset() {
        this.jButton1.setEnabled(false);
        this.txtMessage.setText("");
        this.record.clear();
        this.txtProgress.setValue(0);
        this.totalsend = 0L;
        this.interByteSent = 0L;
        this.starttime = 0L;
        this.endtime = 0L;
        this.isComplete = false;
        this.txtUploadResult.setText("0");
        this.txtProgress.setIndeterminate(false);
        this.timeStart.setText("");
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        if (this.OKToTest()) {
            try {
                this.reset();
                (this.timer = new Timer()).scheduleAtFixedRate(new TTask(), 0L, 1000L);
                this.timeStart.setText("Start : " + this.tformat.format(new Date()));
                if (this.isSecure) {
                    this.sendSecureBinaryData(new URL(this.strCallBackContext + "/bwTester"), this.totalToSend);
                }
                else {
                    this.sendPlainBinaryData(new URL(this.strCallBackContext + "/bwTester"), this.totalToSend);
                }
            }
            catch (Exception exp) {
                exp.printStackTrace();
                JOptionPane.showMessageDialog(null, exp.getMessage(), "Exception", 0);
                this.jButton1.setEnabled(true);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Connection Quota Limit Reach. Please try again later.", "INFO", 1);
        }
    }
    
    private void txtUploadResultActionPerformed(final ActionEvent evt) {
    }
    
    private void txtBitRateActionPerformed(final ActionEvent evt) {
    }
    
    public boolean OKToTest() {
        boolean ok = false;
        final ComponentHandler handler = new ComponentHandler(this);
        try {
            final String a = handler.httpRequestAction(this.isSecure, this.strCallBackContext + "/bwTester?PERM=Y", null, null);
            if (a != null && a.equalsIgnoreCase("Y")) {
                ok = true;
            }
            this.debug("[OKToTest] " + ok);
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
        return ok;
    }
    
    private void sendSecureBinaryData(final URL objUrl, final int bytesize) throws Exception {
        final Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    final HttpsURLConnection objConn = (HttpsURLConnection)objUrl.openConnection();
                    objConn.setDoInput(true);
                    objConn.setDoOutput(true);
                    objConn.setUseCaches(false);
                    objConn.setDefaultUseCaches(false);
                    objConn.setRequestMethod(BandwidthTester.this.method);
                    objConn.setRequestProperty("Connection", "Keep-Alive");
                    objConn.setRequestProperty("Content-Type", "application/octet-stream");
                    objConn.setRequestProperty("Content-Length", "" + bytesize);
                    objConn.setFixedLengthStreamingMode(bytesize);
                    final OutputStream objDOS = objConn.getOutputStream();
                    BandwidthTester.this.starttime = System.currentTimeMillis();
                    final byte[] data = new byte[BandwidthTester.this.block];
                    for (int i = 0; i < BandwidthTester.this.block; ++i) {
                        data[i] = 90;
                    }
                    while (BandwidthTester.this.totalsend < bytesize) {
                        objDOS.write(data);
                        final BandwidthTester this$0 = BandwidthTester.this;
                        this$0.totalsend += data.length;
                        final BandwidthTester this$2 = BandwidthTester.this;
                        this$2.interByteSent += data.length;
                        objDOS.flush();
                    }
                    BandwidthTester.this.debug("Start sending data over");
                    objDOS.close();
                    final String response = BandwidthTester.this.retrieveOutputStream(objConn);
                    BandwidthTester.this.endtime = System.currentTimeMillis();
                    BandwidthTester.this.debug("Response : " + response);
                    BandwidthTester.this.showResult();
                }
                catch (Exception exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog(null, exp.getMessage(), "Exception", 0);
                }
            }
        });
        t.start();
    }
    
    private void sendPlainBinaryData(final URL objUrl, final int bytesize) throws Exception {
        final Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    final HttpURLConnection objConn = (HttpURLConnection)objUrl.openConnection();
                    objConn.setDoInput(true);
                    objConn.setDoOutput(true);
                    objConn.setUseCaches(false);
                    objConn.setDefaultUseCaches(false);
                    objConn.setRequestMethod(BandwidthTester.this.method);
                    objConn.setRequestProperty("Connection", "Keep-Alive");
                    objConn.setRequestProperty("Content-Type", "application/octet-stream");
                    objConn.setRequestProperty("Content-Length", "" + bytesize);
                    objConn.setFixedLengthStreamingMode(bytesize);
                    final OutputStream objDOS = objConn.getOutputStream();
                    BandwidthTester.this.starttime = System.currentTimeMillis();
                    final byte[] data = new byte[BandwidthTester.this.block];
                    for (int i = 0; i < BandwidthTester.this.block; ++i) {
                        data[i] = 90;
                    }
                    while (BandwidthTester.this.totalsend < bytesize) {
                        objDOS.write(data);
                        final BandwidthTester this$0 = BandwidthTester.this;
                        this$0.totalsend += data.length;
                        final BandwidthTester this$2 = BandwidthTester.this;
                        this$2.interByteSent += data.length;
                        objDOS.flush();
                    }
                    BandwidthTester.this.debug("Start sending data over");
                    objDOS.close();
                    final String response = BandwidthTester.this.retrieveOutputStream(objConn);
                    BandwidthTester.this.debug("Response : " + response);
                    BandwidthTester.this.endtime = System.currentTimeMillis();
                    BandwidthTester.this.showResult();
                }
                catch (Exception exp) {
                    JOptionPane.showMessageDialog(null, exp.getMessage(), "Exception", 0);
                }
            }
        });
        t.start();
    }
    
    private void showResult() {
        double average = this.getAverageSpeed();
        final double averageinkbps = average * 8.0 / 1000.0;
        average /= 1024.0;
        this.debug("Total time taken : " + (this.endtime - this.starttime) / 1000.0);
        this.txtUploadResult.setText(this.format.format(average));
        this.debug("Average speed (KB/s) : " + this.format.format(average));
        if (average < 18.0) {
            this.txtMessage.setText("Your connection is too slow. Recommend you to use faster network link.");
        }
        else if (average > 18.0 && average < 38.0) {
            this.txtMessage.setText("Your connection is just fine for light weight upload.");
        }
        else if (average > 38.0 && average < 88.0) {
            this.txtMessage.setText("Your connection speed is ideal!");
        }
        else {
            this.txtMessage.setText("Your connection speed is superb!");
        }
        final double minute = 5120.0 / average / 60.0;
        final double minute2 = 10240.0 / average / 60.0;
        final String average_bandwidth = "Speed result : " + this.format.format(average) + " KB/s or " + this.format.format(averageinkbps) + " kbps\n\n";
        String summary = average_bandwidth + "1 MB = " + this.format.format(1024.0 / average) + " seconds or \n";
        summary = summary + "5 MB = " + ((minute >= 1.0) ? (this.format.format(minute) + " minutes or \n") : (this.format.format(5120.0 / average) + " seconds or \n"));
        summary = summary + "10 MB = " + ((minute2 >= 1.0) ? (this.format.format(minute2) + " minutes") : (this.format.format(10240.0 / average) + " seconds"));
        JOptionPane.showMessageDialog(this, summary, "Info", 1);
        this.txtUploadResult.setToolTipText(summary);
        this.isComplete = true;
        this.txtProgress.setIndeterminate(false);
    }
    
    private double getAverageSpeed() {
        long sum = 0L;
        this.debug("[getAverageSpeed] record size : " + this.record.size());
        for (int i = 0; i < this.record.size(); ++i) {
            final Long bw = this.record.get(i);
            sum += bw;
            this.debug("[getAverageSpeed] size : " + bw);
        }
        this.debug("[getAverageSpeed] average speed :" + sum / this.record.size());
        return sum / this.record.size();
    }
    
    public void recordBandwidth(final long totalbytesendsec) {
        this.record.add(new Long(totalbytesendsec));
        this.txtUploadResult.setText(this.format.format(totalbytesendsec / 1024.0));
        this.txtBitRate.setText(this.format.format(totalbytesendsec * 8L / 1000.0));
    }
    
    private void debug(final String msg) {
        System.out.println("[INFO] " + msg);
    }
    
    private String retrieveOutputStream(final URLConnection objConn) throws Exception {
        final BufferedInputStream ri = new BufferedInputStream(objConn.getInputStream());
        final BufferedReader rd = new BufferedReader(new InputStreamReader(ri));
        final StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.substring(0);
    }
    
    public class TTask extends TimerTask
    {
        public void run() {
            final int percent = (int)(BandwidthTester.this.totalsend * 1.0 / BandwidthTester.this.totalToSend * 100.0);
            BandwidthTester.this.txtProgress.setValue(percent);
            BandwidthTester.this.recordBandwidth(BandwidthTester.this.interByteSent);
            BandwidthTester.this.interByteSent = 0L;
            if (percent >= 100 && BandwidthTester.this.isComplete) {
                BandwidthTester.this.jButton1.setEnabled(true);
                BandwidthTester.this.timer.cancel();
            }
        }
    }
}
