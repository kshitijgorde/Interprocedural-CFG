// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.overlays;

import com.masystem.beergame.network.StatusMessages;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.ConnectionManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.network.protocol.Request;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Command;

public abstract class ConnectingOverlay extends MessageOverlay
{
    private ResponseHandler responseHandler;
    private Runnable finishRunnable;
    protected boolean finished;
    protected boolean autoFinish;
    
    public ConnectingOverlay(final String s, final String s2, final boolean autoFinish) {
        super(s, s2, "CANCEL", false);
        this.responseHandler = new ResponseHandler();
        this.autoFinish = autoFinish;
    }
    
    public final void throwResponse(final Command command, final Status status, final Object... array) {
        this.responseHandler.onResponse(new Response(Request.ID_NONE, command, status, array));
    }
    
    @Override
    public final void onSetup() {
        super.onSetup();
        this.getRightButton().addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                if (ConnectingOverlay.this.getRightButton().getText().equals("CANCEL")) {
                    synchronized (ConnectingOverlay.this) {
                        if (!ConnectingOverlay.this.finished) {
                            ConnectingOverlay.this.finished = true;
                            ConnectionManager.getConnection().disconnect(null);
                            ConnectingOverlay.this.throwResponse(Command.NONE, Status.ERROR_CONNECTION_ABORTED, new Object[0]);
                        }
                    }
                }
                ConnectingOverlay.this.close();
            }
        });
    }
    
    @Override
    protected final void onFinish() {
        synchronized (this) {
            this.finishRunnable.run();
            this.finishRunnable = null;
        }
    }
    
    protected abstract void onResponse(final Response p0);
    
    protected abstract void onFinish(final Response p0);
    
    public final ResponseHandler getResponseHandler() {
        return this.responseHandler;
    }
    
    protected final void finish(final Response response) {
        synchronized (this) {
            this.finished = true;
            final Status status;
            if ((status = response.getStatus()) == Status.OK) {
                this.finishRunnable = new Runnable() {
                    @Override
                    public final void run() {
                        ConnectingOverlay.this.onFinish(response);
                    }
                };
            }
            else {
                this.finishRunnable = new Runnable() {
                    @Override
                    public final void run() {
                        ConnectingOverlay.this.prepareOverlayUpdate();
                        ConnectingOverlay.this.setMessage("Cannot connect", String.format(StatusMessages.get(status) + ".", response.getArguments()));
                        ConnectingOverlay.this.setRightButtonLabel("BACK");
                        ConnectingOverlay.this.onFinish(response);
                        ConnectingOverlay.this.updateOverlayContents();
                    }
                };
            }
            this.setTaskFinished(true);
        }
    }
    
    public final class ResponseHandler implements ResponseListener
    {
        @Override
        public final void onResponse(final Response response) {
            synchronized (ConnectingOverlay.this) {
                if (!ConnectingOverlay.this.finished) {
                    ConnectingOverlay.this.onResponse(response);
                    if (response.getStatus() == Status.OK) {
                        if (ConnectingOverlay.this.autoFinish && !ConnectionManager.getConnection().hasMoreTasks()) {
                            ConnectingOverlay.this.finish(response);
                        }
                    }
                    else if (ConnectingOverlay.this.autoFinish) {
                        ConnectingOverlay.this.finish(response);
                    }
                }
            }
        }
    }
}
