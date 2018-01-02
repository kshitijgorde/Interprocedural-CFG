import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.awt.Component;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmegaQueueEvaluation extends Thread implements ActionListener
{
    private OmegaClient omega;
    private String[] str;
    private Component component;
    private int output_mode;
    private boolean aturat;
    private boolean freeContent;
    private boolean async;
    private ByteArrayOutputStream output;
    private int timeout;
    private OmegaTimer timer;
    private String defaultError;
    
    public OmegaQueueEvaluation() {
        this.aturat = false;
        this.timeout = 10000;
    }
    
    public final void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
    
    public final void freeContent(final OmegaClient omega, final ByteArrayOutputStream output, final Component component, final boolean async) {
        this.omega = omega;
        this.defaultError = "Error";
        this.component = component;
        this.output = output;
        this.freeContent = true;
        this.async = async;
        if (async) {
            this.start();
        }
        else {
            this.run();
        }
    }
    
    public final void evaluate(final OmegaClient omega, final String[] array, final int output_mode, final Component component) {
        this.omega = omega;
        this.defaultError = "Error";
        this.component = component;
        if (this.omega.testOmegaStatus(3)) {
            throw new OException("Kernel is already calculating.");
        }
        this.omega.setOmegaStatus(3, true);
        this.str = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.str[i] = array[i];
        }
        this.output_mode = output_mode;
        this.async = true;
        this.freeContent = false;
        this.start();
    }
    
    public final void run() {
        boolean b = false;
        try {
            try {
                this.omega.startupIfNeeded();
            }
            catch (IOException ex3) {}
            if (this.timeout != 0) {
                (this.timer = new OmegaTimer(this.timeout, this, this, null)).start();
            }
            if (this.async) {
                this.setName("QueueEvaluation");
            }
            if (this.freeContent) {
                ByteArrayInputStream i;
                try {
                    final DataOutputStream iniciaConnexio = this.omega.iniciaConnexio();
                    iniciaConnexio.write(this.output.toByteArray());
                    iniciaConnexio.flush();
                    i = OmegaClient.I(this.omega.getInputStream());
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    i = null;
                    if (t instanceof OException && t.getMessage().equals("Cannot connect to the kernel.")) {
                        this.defaultError = "Cannot connect to the kernel.";
                    }
                }
                final OmegaAnswerEvent omegaAnswerEvent = new OmegaAnswerEvent(this.component, this.omega);
                omegaAnswerEvent.input = i;
                this.finish(omegaAnswerEvent);
            }
            else {
                String[] str2;
                try {
                    final String[] str = this.str;
                    str2 = this.omega.evaluate(this.str, this.output_mode);
                }
                catch (Throwable t2) {
                    if (t2 instanceof OException && t2.getMessage().equals("Cannot connect to the kernel.") && this.defaultError.equals("Error")) {
                        this.defaultError = "Cannot connect to the kernel.";
                    }
                    str2 = this.omega.arreglaError(this.str, t2.getMessage());
                }
                try {
                    this.omega.setOmegaStatus(3, false);
                    b = true;
                }
                catch (OException ex) {
                    System.out.println(ex.getMessage());
                }
                final OmegaAnswerEvent omegaAnswerEvent2 = new OmegaAnswerEvent(this.component, this.omega);
                if (this.aturat) {
                    omegaAnswerEvent2.str = this.omega.arreglaError(this.str, this.defaultError);
                }
                else {
                    omegaAnswerEvent2.str = str2;
                }
                this.finish(omegaAnswerEvent2);
            }
        }
        finally {
            try {
                if (!b) {
                    this.omega.setOmegaStatus(3, false);
                }
            }
            catch (OException ex2) {
                System.out.println(ex2.getMessage());
            }
        }
    }
    
    public final void interrumpt() {
        if (this == null || !this.omega.testOmegaStatus(3)) {
            return;
        }
        this.aturat = true;
        this.interrupt();
        this.omega.interrumpt();
    }
    
    private void finish(final OmegaAnswerEvent omegaAnswerEvent) {
        if (this.timeout != 0) {
            this.timer.stopTimer();
        }
        this.component.dispatchEvent(omegaAnswerEvent);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (this.aturat) {
                return;
            }
            this.defaultError = "Lack of time.";
            this.interrumpt();
        }
        catch (OException ex) {}
    }
    
    public final void setError(final String defaultError) {
        this.defaultError = defaultError;
    }
    
    public final String getError() {
        return this.defaultError;
    }
}
