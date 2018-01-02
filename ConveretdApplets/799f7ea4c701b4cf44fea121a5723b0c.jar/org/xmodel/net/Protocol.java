// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import org.xmodel.external.NonSyncingListener;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.ExternalReference;
import org.xmodel.DepthFirstIterator;
import org.xmodel.ModelAlgorithms;
import org.xmodel.Xlate;
import org.xmodel.external.CachingException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import org.xmodel.xaction.debug.Debugger;
import org.xmodel.xaction.debug.IDebugger;
import org.xmodel.xaction.XAction;
import org.xmodel.compress.TabularCompressor;
import org.xmodel.compress.ICompressor;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.xmodel.util.Identifier;
import java.nio.BufferUnderflowException;
import org.xmodel.xaction.IXAction;
import java.util.Iterator;
import org.xmodel.xaction.XActionException;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.PathSyntaxException;
import org.xmodel.xpath.XPath;
import org.xmodel.external.IExternalReference;
import java.io.IOException;
import org.xmodel.ModelObject;
import java.util.ArrayList;
import org.xmodel.ImmediateDispatcher;
import java.nio.ByteOrder;
import java.util.WeakHashMap;
import java.util.Collections;
import java.util.HashMap;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.IDispatcher;
import java.util.Random;
import java.nio.ByteBuffer;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import java.util.concurrent.BlockingQueue;
import java.util.List;
import java.util.Map;
import org.xmodel.log.Log;

public class Protocol implements ILink.IListener
{
    public static final short version = 1;
    private static Log E;
    private Map<ILink, List<SessionInfo>> L;
    private Map<String, BlockingQueue<byte[]>> A;
    private Map<IModelObject, _J> M;
    private IContext B;
    private ByteBuffer F;
    private int I;
    private Random D;
    private IDispatcher N;
    private List<String> H;
    private ILink J;
    private boolean G;
    private static /* synthetic */ int[] K;
    private static /* synthetic */ int[] C;
    
    static {
        Protocol.E = Log.getLog("org.xmodel.net");
    }
    
    public Protocol(final int i) {
        this.B = new StatefulContext();
        this.L = Collections.synchronizedMap(new HashMap<ILink, List<SessionInfo>>());
        this.A = Collections.synchronizedMap(new HashMap<String, BlockingQueue<byte[]>>());
        this.M = Collections.synchronizedMap(new WeakHashMap<IModelObject, _J>());
        this.I = i;
        this.D = new Random();
        (this.F = ByteBuffer.allocate(4096)).order(ByteOrder.BIG_ENDIAN);
        this.N = new ImmediateDispatcher();
        this.H = new ArrayList<String>();
    }
    
    public void setServerContext(final IContext b) {
        if (this.G) {
            this.B.getScope().clear("debug");
            b.set("debug", new ModelObject("debug"));
        }
        this.B = b;
        this.N = b.getModel().getDispatcher();
    }
    
    public void setEnableDebugging(final boolean g) {
        this.G = g;
        if (this.G && this.B != null) {
            this.B.set("debug", new ModelObject("debug"));
        }
    }
    
    public void setDispatcher(final IDispatcher n) {
        this.N = n;
    }
    
    public void addPackage(final String s) {
        this.H.add(s);
    }
    
    public Session openSession(final ILink link) throws IOException {
        final int sendSessionOpenRequest = this.sendSessionOpenRequest(link, (short)1);
        final SessionInfo sessionInfo = new SessionInfo();
        List<SessionInfo> list = this.L.get(link);
        if (list == null) {
            list = new ArrayList<SessionInfo>(1);
            this.L.put(link, list);
        }
        for (int i = list.size(); i <= sendSessionOpenRequest; ++i) {
            list.add(null);
        }
        list.set(sendSessionOpenRequest, sessionInfo);
        return new Session(this, link, sendSessionOpenRequest);
    }
    
    public void closeSession(final ILink link, final int n) throws IOException {
        this.sendSessionCloseRequest(link, n);
    }
    
    public void attach(final ILink link, final int n, final String xpath, final IExternalReference element) throws IOException {
        if (link == null || !link.isOpen()) {
            throw new IOException("Link not open.");
        }
        final SessionInfo session = this.getSession(link, n);
        if (session.xpath != null) {
            throw new IOException("Protocol only supports one concurrent attach operation per session.");
        }
        session.xpath = xpath;
        session.element = element;
        session.isAttachClient = true;
        session.dispatcher = element.getModel().getDispatcher();
        this.sendAttachRequest(link, n, xpath);
        (session.listener = new Listener(link, n, xpath, element)).install(element);
    }
    
    public void detach(final ILink link, final int n) throws IOException {
        this.sendDetachRequest(link, n);
        final SessionInfo session = this.getSession(link, n);
        if (session != null) {
            session.xpath = null;
            session.element = null;
            session.dispatcher = null;
        }
    }
    
    public Object query(final ILink link, final int n, final IContext context, final String s, final int n2) throws IOException {
        if (link != null && link.isOpen()) {
            return this.sendQueryRequest(link, n, context, s, n2);
        }
        throw new IOException("Link not open.");
    }
    
    public Object[] execute(final ILink link, final int n, final StatefulContext statefulContext, final String[] array, final IModelObject modelObject, final int n2) throws IOException {
        if (link != null && link.isOpen()) {
            return this.sendExecuteRequest(link, n, statefulContext, array, modelObject, n2);
        }
        throw new IOException("Link not open.");
    }
    
    protected void doAttach(final ILink link, final int n, final String xpath) throws IOException {
        try {
            final IModelObject queryFirst = XPath.compileExpression(xpath).queryFirst(this.B);
            IModelObject encode = null;
            if (queryFirst != null) {
                final SessionInfo session = this.getSession(link, n);
                session.xpath = xpath;
                (session.element = queryFirst).getChildren();
                encode = this.encode(link, n, queryFirst, true);
                (session.listener = new Listener(link, n, xpath, queryFirst)).install(queryFirst);
            }
            this.sendAttachResponse(link, n, encode);
        }
        catch (Exception ex) {
            this.sendError(link, n, ex.getMessage());
        }
    }
    
    protected void doDetach(final ILink link, final int n) {
        final SessionInfo session = this.getSession(link, n);
        if (session != null) {
            session.index.clear();
            if (session.listener != null) {
                session.listener.uninstall();
                session.listener = null;
            }
            session.xpath = null;
            session.element = null;
        }
    }
    
    protected void doQuery(final ILink link, final int n, final IModelObject modelObject) throws IOException {
        try {
            final IExpression request = QueryProtocol.readRequest(modelObject, this.B);
            Object o = null;
            switch (C()[request.getType(this.B).ordinal()]) {
                case 1: {
                    o = request.evaluateNodes(this.B);
                    break;
                }
                case 2: {
                    o = request.evaluateString(this.B);
                    break;
                }
                case 3: {
                    o = request.evaluateNumber(this.B);
                    break;
                }
                case 4: {
                    o = request.evaluateBoolean(this.B);
                    break;
                }
            }
            this.sendQueryResponse(link, n, o);
        }
        catch (PathSyntaxException ex) {
            try {
                this.sendError(link, n, ex.getMessage());
            }
            catch (IOException ex2) {}
        }
    }
    
    protected void doExecute(final ILink link, final int n, final IContext context, final IModelObject modelObject) {
        final XActionDocument xActionDocument = new XActionDocument(modelObject);
        final Iterator<String> iterator = this.H.iterator();
        while (iterator.hasNext()) {
            xActionDocument.addPackage(iterator.next());
        }
        Object[] run = null;
        try {
            final IXAction action = xActionDocument.getAction(modelObject);
            if (action == null) {
                throw new XActionException(String.format("Unable to resolve IXAction class: %s.", modelObject.getType()));
            }
            run = action.run(context);
        }
        catch (Throwable t) {
            Protocol.E.exception(t);
            try {
                this.sendError(link, n, String.format("%s: %s", t.getClass().getName(), t.getMessage()));
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        try {
            this.sendExecuteResponse(link, n, context, run);
        }
        catch (IOException ex2) {
            Protocol.E.exception(ex2);
        }
    }
    
    protected void doSessionClose(final ILink link, final int n) {
        this.A(link, n);
    }
    
    protected void doClose(final ILink link, final int n) {
        IExternalReference externalReference = null;
        final SessionInfo session = this.getSession(link, n);
        if (session != null && session.isAttachClient) {
            externalReference = (IExternalReference)session.element;
        }
        this.doDetach(link, n);
        this.A(link, n);
        if (externalReference != null) {
            externalReference.setDirty(true);
        }
    }
    
    @Override
    public void onClose(final ILink link) {
        final List<SessionInfo> sessions = this.getSessions(link);
        for (int i = 0; i < sessions.size(); ++i) {
            final SessionInfo sessionInfo = sessions.get(i);
            if (sessionInfo != null) {
                this.dispatch(sessionInfo, new _L(link, i));
            }
        }
    }
    
    @Override
    public void onReceive(final ILink link, final ByteBuffer byteBuffer) {
        byteBuffer.mark();
        while (this.A(link, byteBuffer)) {
            byteBuffer.mark();
        }
        byteBuffer.reset();
    }
    
    private final boolean A(final ILink link, final ByteBuffer byteBuffer) {
        try {
            final byte value = byteBuffer.get();
            final Type type = Type.values()[value & 0x3F];
            final int messageSession = readMessageSession(value, byteBuffer);
            final int messageLength = readMessageLength(value, byteBuffer);
            if (messageLength > byteBuffer.remaining()) {
                return false;
            }
            switch (B()[type.ordinal()]) {
                case 1: {
                    this.A(link, byteBuffer, messageLength);
                    return true;
                }
                case 2: {
                    this.O(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 3: {
                    this.R(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 4: {
                    this.B(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 5: {
                    this.U(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 6: {
                    this.J(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 7: {
                    this.V(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 8: {
                    this.C(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 9: {
                    this.A(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 10: {
                    this.N(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 11: {
                    this.H(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 12: {
                    this.Q(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 13: {
                    this.W(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 14: {
                    this.T(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 15: {
                    this.L(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 16: {
                    this.D(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 17: {
                    this.G(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 18: {
                    this.X(link, messageSession, byteBuffer, messageLength);
                    return true;
                }
                case 19: {
                    if (this.G) {
                        this.S(link, messageSession, byteBuffer, messageLength);
                    }
                    return true;
                }
                case 20: {
                    if (this.G) {
                        this.I(link, messageSession, byteBuffer, messageLength);
                    }
                    return true;
                }
                case 21: {
                    if (this.G) {
                        this.M(link, messageSession, byteBuffer, messageLength);
                    }
                    return true;
                }
                case 22: {
                    if (this.G) {
                        this.E(link, messageSession, byteBuffer, messageLength);
                    }
                    return true;
                }
                case 23: {
                    if (this.G) {
                        this.F(link, messageSession, byteBuffer, messageLength);
                    }
                    return true;
                }
            }
        }
        catch (BufferUnderflowException ex) {}
        return false;
    }
    
    public final int sendSessionOpenRequest(final ILink link, final short n) throws IOException {
        final String generate = Identifier.generate(this.D, 15);
        Protocol.E.debugf("sendSessionOpenRequest: %d, %s", n, generate);
        initialize(this.F);
        this.F.putShort(n);
        this.writeString(generate);
        finalize(this.F, Type.sessionOpenRequest, 0, 2);
        final byte[] a = this.A(link, generate, this.F, this.I);
        return (a[0] << 24) + (a[1] << 16) + (a[2] << 8) + a[3];
    }
    
    private final void A(final ILink link, final ByteBuffer byteBuffer, final int n) {
        final short short1 = byteBuffer.getShort();
        final String string = readString(byteBuffer);
        Protocol.E.debugf("handleSessionOpenRequest: %d, %s", short1, string);
        if (short1 != 1) {
            link.close();
            return;
        }
        try {
            this.sendSessionOpenResponse(link, this.A(link), string);
        }
        catch (IOException ex) {
            Protocol.E.exception(ex);
        }
    }
    
    public final void sendSessionOpenResponse(final ILink link, final int n, final String s) throws IOException {
        Protocol.E.debugf("sendSessionOpenResponse: %d, %s", n, s);
        initialize(this.F);
        this.writeString(s);
        this.F.putInt(n);
        finalize(this.F, Type.sessionOpenResponse, 0, 4);
        link.send(this.F);
    }
    
    private final void O(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.B(link, readString(byteBuffer), byteBuffer, n2);
    }
    
    public final void sendSessionCloseRequest(final ILink link, final int n) throws IOException {
        Protocol.E.debugf("sendSessionCloseRequest(%d)", n);
        initialize(this.F);
        finalize(this.F, Type.sessionCloseRequest, n, 0);
    }
    
    private final void R(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.dispatch(this.getSession(link, n), new _F(link, n));
    }
    
    public final void sendError(final ILink link, final int n, final String s) throws IOException {
        Protocol.E.debugf("sendError: %s", s);
        initialize(this.F);
        final byte[] bytes = s.getBytes();
        this.F.put(bytes, 0, bytes.length);
        finalize(this.F, Type.error, n, bytes.length);
        link.send(this.F);
    }
    
    private final void B(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        Protocol.E.debugf("handleError: %s", new String(array));
        this.handleError(link, new String(array));
    }
    
    protected void handleError(final ILink link, final String s) {
        Protocol.E.error(s);
    }
    
    public final void sendAttachRequest(final ILink link, final int n, final String s) throws IOException {
        Protocol.E.debugf("sendAttachRequest(%d): %s", n, s);
        initialize(this.F);
        final byte[] bytes = s.getBytes();
        this.F.put(bytes, 0, bytes.length);
        finalize(this.F, Type.attachRequest, n, bytes.length);
        final IModelObject decompress = this.getSession(link, n).compressor.decompress(new ByteArrayInputStream(this.P(link, n, this.F, this.I)));
        Protocol.E.debugf("handleAttachResponse(%d): %s\n", n, decompress.getType());
        this.handleAttachResponse(link, n, decompress);
    }
    
    private final void U(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        Protocol.E.debugf("handleAttachRequest(%d): %s", n, new String(array));
        this.handleAttachRequest(link, n, new String(array));
    }
    
    protected void handleAttachRequest(final ILink link, final int n, final String s) {
        this.dispatch(this.getSession(link, n), new _K(link, n, s));
    }
    
    public final void sendAttachResponse(final ILink link, final int n, final IModelObject modelObject) throws IOException {
        Protocol.E.debugf("sendAttachResponse(%d): %s", n, modelObject.getType());
        initialize(this.F);
        final byte[] compress = this.getSession(link, n).compressor.compress(modelObject);
        final ByteBuffer wrap = ByteBuffer.wrap(compress);
        finalize(this.F, Type.attachResponse, n, compress.length);
        link.send(this.F);
        link.send(wrap);
    }
    
    private final void J(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.K(link, n, byteBuffer, n2);
    }
    
    protected void handleAttachResponse(final ILink link, final int n, final IModelObject modelObject) {
        final IExternalReference externalReference = (IExternalReference)this.getSession(link, n).element;
        if (externalReference != null) {
            externalReference.getCachingPolicy().update(externalReference, this.decode(link, n, modelObject));
        }
    }
    
    public final void sendDetachRequest(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.detachRequest, n, 0);
        link.send(this.F);
    }
    
    private final void V(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        Protocol.E.debugf("handleDetachRequest(%d)", n);
        this.handleDetachRequest(link, n);
    }
    
    protected void handleDetachRequest(final ILink link, final int n) {
        this.dispatch(this.getSession(link, n), new _D(link, n));
    }
    
    public final void sendSyncRequest(final IExternalReference externalReference) throws IOException {
        final _J j = this.M.get(externalReference);
        Protocol.E.debugf("sendSyncRequest(%d): %s", j.D, externalReference.getType());
        initialize(this.F);
        final byte[] bytes = j.B.getBytes();
        this.F.put(bytes, 0, bytes.length);
        finalize(this.F, Type.syncRequest, j.D, bytes.length);
        this.P(j.C, j.D, this.F, this.I);
        this.handleSyncResponse(j.C, j.D);
    }
    
    private final void C(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        this.handleSyncRequest(link, n, new String(array));
    }
    
    protected void handleSyncRequest(final ILink link, final int n, final String s) {
        this.dispatch(this.getSession(link, n), new _C(link, n, s));
    }
    
    public final void sendSyncResponse(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.syncResponse, n, 0);
        link.send(this.F);
    }
    
    private final void A(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.K(link, n, byteBuffer, n2);
    }
    
    protected void handleSyncResponse(final ILink link, final int n) {
    }
    
    public final void sendAddChild(final ILink link, final int n, final String s, final IModelObject modelObject, final int n2) throws IOException {
        Protocol.E.debugf("sendAddChild(%d): %s, %s", n, s, modelObject.getType());
        initialize(this.F);
        int n3 = this.writeString(s) + this.writeElement(this.getSession(link, n).compressor, modelObject);
        this.F.putInt(n2);
        n3 += 4;
        finalize(this.F, Type.addChild, n, n3);
        link.send(this.F);
    }
    
    private final void N(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleAddChild(link, n, readString(byteBuffer), readBytes(byteBuffer, false), byteBuffer.getInt());
    }
    
    protected void handleAddChild(final ILink link, final int n, final String s, final byte[] array, final int n2) {
        this.dispatch(this.getSession(link, n), new _B(link, n, s, array, n2));
    }
    
    private void A(final ILink j, final int n, final String s, final byte[] array, final int n2) {
        try {
            this.J = j;
            final IModelObject element = this.getSession(j, n).element;
            if (element == null) {
                return;
            }
            final IExpression expression = XPath.createExpression(s);
            if (expression != null) {
                final IModelObject queryFirst = expression.queryFirst(element);
                final IModelObject decompress = this.getSession(j, n).compressor.decompress(array, 0);
                Protocol.E.debugf("processAddChild(%d): %s, %s", n, s, decompress.getType());
                if (queryFirst != null) {
                    queryFirst.addChild(this.decode(j, n, decompress), n2);
                }
            }
        }
        finally {
            this.J = null;
        }
        this.J = null;
    }
    
    public final void sendRemoveChild(final ILink link, final int n, final String s, final int n2) throws IOException {
        Protocol.E.debugf("sendRemoveChild(%d): %s, %d", n, s, n2);
        initialize(this.F);
        int writeString = this.writeString(s);
        this.F.putInt(n2);
        writeString += 4;
        finalize(this.F, Type.removeChild, n, writeString);
        link.send(this.F);
    }
    
    private final void H(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleRemoveChild(link, n, readString(byteBuffer), byteBuffer.getInt());
    }
    
    protected void handleRemoveChild(final ILink link, final int n, final String s, final int n2) {
        this.dispatch(this.getSession(link, n), new _A(link, n, s, n2));
    }
    
    private void A(final ILink j, final int n, final String s, final int n2) {
        try {
            this.J = j;
            final IModelObject element = this.getSession(j, n).element;
            if (element == null) {
                return;
            }
            final IExpression expression = XPath.createExpression(s);
            if (expression != null) {
                final IModelObject queryFirst = expression.queryFirst(element);
                if (queryFirst != null) {
                    final IModelObject removeChild = queryFirst.removeChild(n2);
                    Protocol.E.debugf("processRemoveChild: %s, %s", s, (removeChild != null) ? removeChild.getType() : "null");
                    if (removeChild instanceof IExternalReference) {
                        this.M.remove(removeChild);
                    }
                }
            }
        }
        finally {
            this.J = null;
        }
        this.J = null;
    }
    
    public final void sendChangeAttribute(final ILink link, final int n, final String s, final String s2, final Object o) throws IOException {
        final byte[] a = this.A(o);
        initialize(this.F);
        finalize(this.F, Type.changeAttribute, n, this.writeString(s) + this.writeString(s2) + this.writeBytes(a, 0, a.length, true));
        link.send(this.F);
    }
    
    private final void Q(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleChangeAttribute(link, n, readString(byteBuffer), readString(byteBuffer), this.A(readBytes(byteBuffer, true)));
    }
    
    protected void handleChangeAttribute(final ILink link, final int n, final String s, final String s2, final Object o) {
        this.dispatch(this.getSession(link, n), new _G(link, n, s, s2, o));
    }
    
    private void A(final ILink j, final int n, final String s, final String s2, Object o) {
        try {
            this.J = j;
            final IModelObject element = this.getSession(j, n).element;
            if (element == null) {
                return;
            }
            if (o == null) {
                o = "";
            }
            final IExpression expression = XPath.createExpression(s);
            if (expression != null) {
                final IModelObject queryFirst = expression.queryFirst(element);
                if (queryFirst != null) {
                    queryFirst.setAttribute(s2, o);
                }
            }
        }
        finally {
            this.J = null;
        }
        this.J = null;
    }
    
    public final void sendClearAttribute(final ILink link, final int n, final String s, final String s2) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.clearAttribute, n, this.writeString(s) + this.writeString(s2));
        link.send(this.F);
    }
    
    private final void W(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleClearAttribute(link, n, readString(byteBuffer), readString(byteBuffer));
    }
    
    protected void handleClearAttribute(final ILink link, final int n, final String s, final String s2) {
        this.dispatch(this.getSession(link, n), new _H(link, n, s, s2));
    }
    
    private void A(final ILink j, final int n, final String s, final String s2) {
        try {
            this.J = j;
            final IModelObject element = this.getSession(j, n).element;
            if (element == null) {
                return;
            }
            final IExpression expression = XPath.createExpression(s);
            if (expression != null) {
                final IModelObject queryFirst = expression.queryFirst(element);
                if (queryFirst != null) {
                    queryFirst.removeAttribute(s2);
                }
            }
        }
        finally {
            this.J = null;
        }
        this.J = null;
    }
    
    public final void sendChangeDirty(final ILink link, final int n, final String s, final boolean b) throws IOException {
        initialize(this.F);
        int writeString = this.writeString(s);
        this.F.put((byte)(b ? 1 : 0));
        ++writeString;
        finalize(this.F, Type.changeDirty, n, writeString);
        link.send(this.F);
    }
    
    private final void T(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleChangeDirty(link, n, readString(byteBuffer), byteBuffer.get() != 0);
    }
    
    protected void handleChangeDirty(final ILink link, final int n, final String s, final boolean b) {
        this.dispatch(this.getSession(link, n), new _M(link, n, s, b));
    }
    
    private void A(final ILink j, final int n, final String s, final boolean dirty) {
        try {
            this.J = j;
            final IModelObject element = this.getSession(j, n).element;
            if (element == null) {
                return;
            }
            final IExpression expression = XPath.createExpression(s);
            if (expression != null) {
                final IModelObject queryFirst = expression.queryFirst(element);
                if (queryFirst != null) {
                    ((IExternalReference)queryFirst).setDirty(dirty);
                }
            }
        }
        finally {
            this.J = null;
        }
        this.J = null;
    }
    
    public final Object sendQueryRequest(final ILink link, final int n, final IContext context, final String s, final int n2) throws IOException {
        initialize(this.F);
        final IModelObject buildRequest = QueryProtocol.buildRequest(context, s);
        final TabularCompressor compressor = this.getSession(link, n).compressor;
        final byte[] compress = compressor.compress(buildRequest);
        this.F.put(compress);
        finalize(this.F, Type.queryRequest, n, compress.length);
        final ModelObject modelObject = (ModelObject)compressor.decompress(this.P(link, n, this.F, n2), 0);
        Protocol.E.debugf("handleQueryResponse(%d):%s\n", n, modelObject.toXml());
        return QueryProtocol.readResponse(modelObject);
    }
    
    private final void L(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        this.handleQueryRequest(link, n, this.getSession(link, n).compressor.decompress(array, 0));
    }
    
    protected void handleQueryRequest(final ILink link, final int n, final IModelObject modelObject) {
        this.dispatch(this.getSession(link, n), new _E(link, n, modelObject));
    }
    
    public final void sendQueryResponse(final ILink link, final int n, final Object o) throws IOException {
        initialize(this.F);
        final byte[] compress = this.getSession(link, n).compressor.compress(QueryProtocol.buildResponse(o));
        this.F.put(compress);
        finalize(this.F, Type.queryResponse, n, compress.length);
        link.send(this.F);
    }
    
    private final void D(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.K(link, n, byteBuffer, n2);
    }
    
    public final Object[] sendExecuteRequest(final ILink link, final int n, final StatefulContext statefulContext, final String[] array, final IModelObject modelObject, final int n2) throws IOException {
        initialize(this.F);
        final ModelObject modelObject2 = (ModelObject)ExecutionProtocol.buildRequest(statefulContext, array, modelObject);
        Protocol.E.debugf("sendExecuteRequest(%d):\n%s", n, modelObject2.toXml());
        final TabularCompressor compressor = this.getSession(link, n).compressor;
        final byte[] compress = compressor.compress(modelObject2);
        this.F.put(compress);
        finalize(this.F, Type.executeRequest, n, compress.length);
        if (n2 > 0) {
            final ModelObject modelObject3 = (ModelObject)compressor.decompress(this.P(link, n, this.F, n2), 0);
            Protocol.E.debugf("handleExecuteResponse(%d):\n%s", n, modelObject3.toXml());
            return ExecutionProtocol.readResponse(modelObject3, statefulContext);
        }
        link.send(this.F);
        return null;
    }
    
    private final void G(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        final ModelObject modelObject = (ModelObject)this.getSession(link, n).compressor.decompress(array, 0);
        Protocol.E.debugf("handleExecuteRequest(%d):\n%s", n, modelObject.toXml());
        final StatefulContext statefulContext = new StatefulContext(this.B);
        this.handleExecuteRequest(link, n, statefulContext, ExecutionProtocol.readRequest(modelObject, statefulContext));
    }
    
    protected void handleExecuteRequest(final ILink link, final int n, final IContext context, final IModelObject modelObject) {
        this.dispatch(this.getSession(link, n), new _I(link, n, context, modelObject));
    }
    
    public final void sendExecuteResponse(final ILink link, final int n, final IContext context, final Object[] array) throws IOException {
        initialize(this.F);
        final ModelObject modelObject = (ModelObject)ExecutionProtocol.buildResponse(context, array);
        Protocol.E.debugf("sendExecuteResponse(%d):\n%s", n, modelObject.toXml());
        final byte[] compress = this.getSession(link, n).compressor.compress(modelObject);
        this.F.put(compress);
        finalize(this.F, Type.executeResponse, n, compress.length);
        link.send(this.F);
    }
    
    private final void X(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.K(link, n, byteBuffer, n2);
    }
    
    public final void sendDebugGo(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.debugGo, n, 0);
        link.send(this.F);
    }
    
    private final void S(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleDebugGo(link, n);
    }
    
    protected void handleDebugGo(final ILink link, final int n) {
        this.dispatch(this.getSession(link, n), new Runnable() {
            @Override
            public void run() {
                XAction.getDebugger().stepOut();
                XAction.setDebugger(null);
            }
        });
    }
    
    public final void sendDebugStop(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.debugStop, n, 0);
        link.send(this.F);
    }
    
    private final void I(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleDebugStop(link, n);
    }
    
    protected void handleDebugStop(final ILink link, final int n) {
        this.dispatch(this.getSession(link, n), new Runnable() {
            @Override
            public void run() {
                XAction.setDebugger(new Debugger(Protocol.this, Protocol.this.B));
            }
        });
    }
    
    public final void sendDebugStepIn(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.debugStepIn, n, 0);
        link.send(this.F);
    }
    
    private final void M(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleDebugStepIn(link, n);
    }
    
    protected void handleDebugStepIn(final ILink link, final int n) {
        this.dispatch(this.getSession(link, n), new Runnable() {
            @Override
            public void run() {
                XAction.getDebugger().stepIn();
            }
        });
    }
    
    public final void sendDebugStepOver(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.debugStepOver, n, 0);
        link.send(this.F);
    }
    
    private final void E(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleDebugStepOver(link, n);
    }
    
    protected void handleDebugStepOver(final ILink link, final int n) {
        this.N.execute(new Runnable() {
            @Override
            public void run() {
                XAction.getDebugger().stepOver();
            }
        });
    }
    
    public final void sendDebugStepOut(final ILink link, final int n) throws IOException {
        initialize(this.F);
        finalize(this.F, Type.debugStepOut, n, 0);
        link.send(this.F);
    }
    
    private final void F(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        this.handleDebugStepOut(link, n);
    }
    
    protected void handleDebugStepOut(final ILink link, final int n) {
        this.N.execute(new Runnable() {
            @Override
            public void run() {
                XAction.getDebugger().stepOut();
            }
        });
    }
    
    private byte[] P(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) throws IOException {
        try {
            link.send(byteBuffer);
            final byte[] array = this.getSession(link, n).responseQueue.poll(n2, TimeUnit.MILLISECONDS);
            if (array != null) {
                return array;
            }
        }
        catch (InterruptedException ex) {
            return null;
        }
        throw new IOException("Network request timeout.");
    }
    
    private byte[] A(final ILink link, final String s, final ByteBuffer byteBuffer, final int n) throws IOException {
        try {
            this.A.put(s, new SynchronousQueue<byte[]>());
            link.send(byteBuffer);
            final byte[] array = this.A.get(s).poll(n, TimeUnit.MILLISECONDS);
            this.A.remove(s);
            if (array != null) {
                return array;
            }
        }
        catch (InterruptedException ex) {
            return null;
        }
        throw new IOException("Network request timeout.");
    }
    
    private void K(final ILink link, final int n, final ByteBuffer byteBuffer, final int n2) {
        final byte[] array = new byte[n2];
        byteBuffer.get(array);
        try {
            final SessionInfo session = this.getSession(link, n);
            if (session != null) {
                session.responseQueue.put(array);
            }
        }
        catch (InterruptedException ex) {}
    }
    
    private void B(final ILink link, final String s, final ByteBuffer byteBuffer, final int n) {
        final byte[] array = new byte[n];
        byteBuffer.get(array);
        try {
            final BlockingQueue<byte[]> blockingQueue = this.A.get(s);
            if (blockingQueue != null) {
                blockingQueue.put(array);
            }
        }
        catch (InterruptedException ex) {}
    }
    
    private byte[] A(final Object o) {
        if (o == null) {
            return new byte[0];
        }
        if (o instanceof Serializable) {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(o);
                objectOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            catch (Exception ex) {
                Protocol.E.exception(ex);
            }
        }
        return new byte[0];
    }
    
    private Object A(final byte[] array) {
        if (array.length == 0) {
            return null;
        }
        try {
            return new ObjectInputStream(new ByteArrayInputStream(array)).readObject();
        }
        catch (Exception ex) {
            throw new CachingException("Unable to deserialize object.", ex);
        }
    }
    
    public static void initialize(final ByteBuffer byteBuffer) {
        byteBuffer.clear();
        byteBuffer.position(9);
    }
    
    public static int readMessageSession(final int n, final ByteBuffer byteBuffer) {
        if ((n & 0x80) == 0x0) {
            return byteBuffer.get();
        }
        return byteBuffer.getInt();
    }
    
    public static int readMessageLength(final int n, final ByteBuffer byteBuffer) {
        if ((n & 0x40) == 0x0) {
            return byteBuffer.get();
        }
        return byteBuffer.getInt();
    }
    
    public static void finalize(final ByteBuffer byteBuffer, final Type type, final int n, final int n2) {
        byteBuffer.limit(byteBuffer.position());
        int n3 = 0;
        int n4 = 9;
        if (n2 < 128) {
            --n4;
            byteBuffer.put(n4, (byte)n2);
        }
        else {
            n3 |= 0x40;
            n4 -= 4;
            byteBuffer.putInt(n4, n2);
        }
        if (n >= 0 && n < 128) {
            --n4;
            byteBuffer.put(n4, (byte)n);
        }
        else {
            n3 |= 0x80;
            n4 -= 4;
            byteBuffer.putInt(n4, n);
        }
        --n4;
        byteBuffer.put(n4, (byte)(type.ordinal() | n3));
        byteBuffer.position(n4);
    }
    
    private static int A(final ByteBuffer byteBuffer, final boolean b) {
        byteBuffer.mark();
        if (b) {
            final byte value = byteBuffer.get();
            if (value >= 0) {
                return value;
            }
        }
        else {
            final short short1 = byteBuffer.getShort();
            if (short1 >= 0) {
                return short1;
            }
        }
        byteBuffer.reset();
        return -byteBuffer.getInt();
    }
    
    static int A(final ByteBuffer byteBuffer, final int n, final boolean b) {
        if (b) {
            if (n < 128) {
                byteBuffer.put((byte)n);
                return 1;
            }
        }
        else if (n < 32768) {
            byteBuffer.putShort((short)n);
            return 2;
        }
        byteBuffer.putInt(-n);
        return 4;
    }
    
    public static byte[] readBytes(final ByteBuffer byteBuffer, final boolean b) {
        final int a = A(byteBuffer, b);
        final byte[] array = new byte[a];
        byteBuffer.get(array, 0, a);
        return array;
    }
    
    public int writeBytes(final byte[] array, final int n, final int n2, final boolean b) {
        final int n3 = this.F.position() + n2;
        if (n3 >= this.F.limit()) {
            this.F.flip();
            final ByteBuffer allocate = ByteBuffer.allocate((int)(n3 * 1.5));
            allocate.put(this.F);
            this.F = allocate;
        }
        final int a = A(this.F, n2, b);
        this.F.put(array, n, n2);
        return a + n2;
    }
    
    public static String readString(final ByteBuffer byteBuffer) {
        return new String(readBytes(byteBuffer, true));
    }
    
    public int writeString(final String s) {
        final byte[] bytes = s.getBytes();
        return this.writeBytes(bytes, 0, bytes.length, true);
    }
    
    public IModelObject readElement(final ICompressor compressor, final ByteBuffer byteBuffer) {
        return compressor.decompress(readBytes(byteBuffer, false), 0);
    }
    
    public int writeElement(final ICompressor compressor, final IModelObject modelObject) {
        final byte[] compress = compressor.compress(modelObject);
        return this.writeBytes(compress, 0, compress.length, false);
    }
    
    protected IModelObject encode1(final ILink link, final int n, final IModelObject modelObject, final boolean b) {
        ModelObject modelObject2;
        if (modelObject instanceof IExternalReference) {
            final IExternalReference externalReference = (IExternalReference)modelObject;
            modelObject2 = new ModelObject(externalReference.getType());
            if (!b) {
                modelObject2.setAttribute("net:key", this.A(link, n, externalReference));
            }
            String[] staticAttributes;
            for (int length = (staticAttributes = externalReference.getStaticAttributes()).length, i = 0; i < length; ++i) {
                final String value = staticAttributes[i];
                final ModelObject modelObject3 = new ModelObject("net:static");
                modelObject3.setValue(value);
                modelObject2.addChild(modelObject3);
            }
            if (modelObject.isDirty()) {
                Xlate.set(modelObject2, "net:dirty", true);
                String[] staticAttributes2;
                for (int length2 = (staticAttributes2 = externalReference.getStaticAttributes()).length, j = 0; j < length2; ++j) {
                    final String s = staticAttributes2[j];
                    final Object attribute = modelObject.getAttribute(s);
                    if (attribute != null) {
                        modelObject2.setAttribute(s, attribute);
                    }
                }
            }
        }
        else {
            modelObject2 = new ModelObject(modelObject.getType());
        }
        if (!(modelObject instanceof IExternalReference) || !modelObject.isDirty()) {
            ModelAlgorithms.copyAttributes(modelObject, modelObject2);
            final Iterator<IModelObject> iterator = modelObject.getChildren().iterator();
            while (iterator.hasNext()) {
                modelObject2.addChild(this.encode(link, n, iterator.next(), false));
            }
        }
        return modelObject2;
    }
    
    protected IModelObject encode(final ILink link, final int n, final IModelObject modelObject, final boolean b) {
        modelObject.getModel().setSyncLock(true);
        final HashMap<IModelObject, ModelObject> hashMap = (HashMap<IModelObject, ModelObject>)new HashMap<Object, ModelObject>();
        try {
            final DepthFirstIterator depthFirstIterator = new DepthFirstIterator(modelObject);
            while (depthFirstIterator.hasNext()) {
                final IModelObject next = depthFirstIterator.next();
                final ModelObject modelObject2 = hashMap.get(next);
                final ModelObject modelObject3 = hashMap.get(next.getParent());
                final ModelObject modelObject4 = new ModelObject(next.getType());
                if (next instanceof IExternalReference) {
                    final IExternalReference externalReference = (IExternalReference)modelObject;
                    if (!b || modelObject4 != modelObject) {
                        Xlate.set((IModelObject)modelObject4, "net:key", this.A(link, n, externalReference));
                    }
                    String[] staticAttributes;
                    for (int length = (staticAttributes = externalReference.getStaticAttributes()).length, i = 0; i < length; ++i) {
                        final String value = staticAttributes[i];
                        final ModelObject modelObject5 = new ModelObject("net:static");
                        modelObject5.setValue(value);
                        modelObject4.addChild(modelObject5);
                    }
                    if (next.isDirty()) {
                        Xlate.set(modelObject4, "net:dirty", true);
                        String[] staticAttributes2;
                        for (int length2 = (staticAttributes2 = externalReference.getStaticAttributes()).length, j = 0; j < length2; ++j) {
                            final String s = staticAttributes2[j];
                            final Object attribute = modelObject.getAttribute(s);
                            if (attribute != null) {
                                modelObject4.setAttribute(s, attribute);
                            }
                        }
                    }
                }
                else {
                    ModelAlgorithms.copyAttributes(next, modelObject4);
                }
                hashMap.put(next, modelObject4);
                if (modelObject3 != null) {
                    modelObject3.addChild(modelObject4);
                }
            }
        }
        finally {
            modelObject.getModel().setSyncLock(false);
        }
        modelObject.getModel().setSyncLock(false);
        return hashMap.get(modelObject);
    }
    
    protected IModelObject decode(final ILink c, final int d, final IModelObject modelObject) {
        modelObject.getModel().setSyncLock(true);
        final HashMap<IModelObject, IModelObject> hashMap = (HashMap<IModelObject, IModelObject>)new HashMap<IModelObject, ModelObject>();
        try {
            final DepthFirstIterator depthFirstIterator = new DepthFirstIterator(modelObject);
            while (depthFirstIterator.hasNext()) {
                final IModelObject next = depthFirstIterator.next();
                if (next.isType("net:static")) {
                    continue;
                }
                final IModelObject modelObject2 = hashMap.get(next);
                final IModelObject modelObject3 = hashMap.get(next.getParent());
                final String value = Xlate.get(next, "net:key", (String)null);
                ModelObject modelObject4;
                if (value != null) {
                    final ExternalReference externalReference = new ExternalReference(next.getType());
                    ModelAlgorithms.copyAttributes(next, externalReference);
                    externalReference.removeAttribute("net:key");
                    externalReference.removeChildren("net:static");
                    final A cachingPolicy = new A(this);
                    cachingPolicy.A(this.A(next));
                    externalReference.setCachingPolicy(cachingPolicy);
                    final boolean value2 = Xlate.get(externalReference, "net:dirty", false);
                    externalReference.removeAttribute("net:dirty");
                    externalReference.setDirty(value2);
                    final _J j = new _J((_J)null);
                    j.B = value;
                    j.C = c;
                    j.D = d;
                    this.M.put(externalReference, j);
                    modelObject4 = externalReference;
                }
                else {
                    modelObject4 = new ModelObject(next.getType());
                    ModelAlgorithms.copyAttributes(next, modelObject4);
                }
                hashMap.put(next, modelObject4);
                if (modelObject3 == null) {
                    continue;
                }
                modelObject3.addChild(modelObject4);
            }
        }
        finally {
            modelObject.getModel().setSyncLock(false);
        }
        modelObject.getModel().setSyncLock(false);
        return hashMap.get(modelObject);
    }
    
    private String A(final ILink link, final int n, final IModelObject modelObject) {
        final String generate = Identifier.generate(this.D, 13);
        System.out.printf("index: %d: %s -> %s\n", n, modelObject.getType(), generate);
        this.getSession(link, n).index.put(generate, modelObject);
        return generate;
    }
    
    private List<String> A(final IModelObject modelObject) {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<IModelObject> iterator = modelObject.getChildren("net:static").iterator();
        while (iterator.hasNext()) {
            list.add(Xlate.get((IModelObject)iterator.next(), ""));
        }
        return list;
    }
    
    private final String A(final IModelObject modelObject, final IModelObject modelObject2) {
        return ModelAlgorithms.createRelativePath(modelObject, modelObject2).toString();
    }
    
    protected void dispatch(final SessionInfo sessionInfo, final Runnable runnable) {
        if (sessionInfo.dispatcher != null) {
            sessionInfo.dispatcher.execute(runnable);
        }
        else if (this.N != null) {
            this.N.execute(runnable);
        }
    }
    
    private final int A(final ILink link) {
        synchronized (this.L) {
            List<SessionInfo> list = this.L.get(link);
            if (list == null) {
                list = new ArrayList<SessionInfo>();
                this.L.put(link, list);
            }
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) == null) {
                    list.set(i, new SessionInfo());
                    // monitorexit(this.L)
                    return i;
                }
            }
            list.add(new SessionInfo());
            // monitorexit(this.L)
            return list.size() - 1;
        }
    }
    
    private final void A(final ILink link, final int n) {
        synchronized (this.L) {
            final List<SessionInfo> list = this.L.get(link);
            if (list != null) {
                list.set(n, null);
            }
        }
        // monitorexit(this.L)
    }
    
    protected final SessionInfo getSession(final ILink link, final int n) {
        synchronized (this.L) {
            final List<SessionInfo> list = this.L.get(link);
            if (list != null) {
                // monitorexit(this.L)
                return list.get(n);
            }
            // monitorexit(this.L)
            return null;
        }
    }
    
    protected final List<SessionInfo> getSessions(final ILink link) {
        synchronized (this.L) {
            final List<SessionInfo> list = this.L.get(link);
            if (list == null) {
                // monitorexit(this.L)
                return Collections.emptyList();
            }
            final ArrayList<SessionInfo> list2 = new ArrayList<SessionInfo>();
            for (final SessionInfo sessionInfo : list) {
                if (sessionInfo != null) {
                    list2.add(sessionInfo);
                }
            }
            // monitorexit(this.L)
            return list2;
        }
    }
    
    static /* synthetic */ int[] C() {
        final int[] k = Protocol.K;
        if (k != null) {
            return k;
        }
        final int[] i = new int[IExpression.ResultType.values().length];
        try {
            i[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            i[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            i[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            i[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            i[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return Protocol.K = i;
    }
    
    static /* synthetic */ int[] B() {
        final int[] c = Protocol.C;
        if (c != null) {
            return c;
        }
        final int[] c2 = new int[Type.values().length];
        try {
            c2[Type.addChild.ordinal()] = 10;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            c2[Type.attachRequest.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            c2[Type.attachResponse.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            c2[Type.changeAttribute.ordinal()] = 12;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            c2[Type.changeDirty.ordinal()] = 14;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        try {
            c2[Type.clearAttribute.ordinal()] = 13;
        }
        catch (NoSuchFieldError noSuchFieldError6) {}
        try {
            c2[Type.debugGo.ordinal()] = 19;
        }
        catch (NoSuchFieldError noSuchFieldError7) {}
        try {
            c2[Type.debugStepIn.ordinal()] = 21;
        }
        catch (NoSuchFieldError noSuchFieldError8) {}
        try {
            c2[Type.debugStepOut.ordinal()] = 23;
        }
        catch (NoSuchFieldError noSuchFieldError9) {}
        try {
            c2[Type.debugStepOver.ordinal()] = 22;
        }
        catch (NoSuchFieldError noSuchFieldError10) {}
        try {
            c2[Type.debugStop.ordinal()] = 20;
        }
        catch (NoSuchFieldError noSuchFieldError11) {}
        try {
            c2[Type.detachRequest.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError12) {}
        try {
            c2[Type.error.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError13) {}
        try {
            c2[Type.executeRequest.ordinal()] = 17;
        }
        catch (NoSuchFieldError noSuchFieldError14) {}
        try {
            c2[Type.executeResponse.ordinal()] = 18;
        }
        catch (NoSuchFieldError noSuchFieldError15) {}
        try {
            c2[Type.queryRequest.ordinal()] = 15;
        }
        catch (NoSuchFieldError noSuchFieldError16) {}
        try {
            c2[Type.queryResponse.ordinal()] = 16;
        }
        catch (NoSuchFieldError noSuchFieldError17) {}
        try {
            c2[Type.removeChild.ordinal()] = 11;
        }
        catch (NoSuchFieldError noSuchFieldError18) {}
        try {
            c2[Type.sessionCloseRequest.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError19) {}
        try {
            c2[Type.sessionOpenRequest.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError20) {}
        try {
            c2[Type.sessionOpenResponse.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError21) {}
        try {
            c2[Type.syncRequest.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError22) {}
        try {
            c2[Type.syncResponse.ordinal()] = 9;
        }
        catch (NoSuchFieldError noSuchFieldError23) {}
        return Protocol.C = c2;
    }
    
    private final class _B implements Runnable
    {
        private ILink D;
        private int E;
        private String B;
        private byte[] F;
        private int C;
        
        public _B(final ILink d, final int e, final String b, final byte[] f, final int c) {
            this.D = d;
            this.E = e;
            this.B = b;
            this.F = f;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.A(this.D, this.E, this.B, this.F, this.C);
        }
    }
    
    private final class _K implements Runnable
    {
        private ILink C;
        private int D;
        private String B;
        
        public _K(final ILink c, final int d, final String b) {
            this.C = c;
            this.D = d;
            this.B = b;
        }
        
        @Override
        public void run() {
            try {
                Protocol.this.doAttach(this.C, this.D, this.B);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
    }
    
    private final class _G implements Runnable
    {
        private ILink D;
        private int F;
        private String B;
        private String C;
        private Object E;
        
        public _G(final ILink d, final int f, final String b, final String c, final Object e) {
            this.D = d;
            this.F = f;
            this.B = b;
            this.C = c;
            this.E = e;
        }
        
        @Override
        public void run() {
            Protocol.this.A(this.D, this.F, this.B, this.C, this.E);
        }
    }
    
    private final class _M implements Runnable
    {
        private ILink D;
        private int E;
        private String B;
        private boolean C;
        
        public _M(final ILink d, final int e, final String b, final boolean c) {
            this.D = d;
            this.E = e;
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.A(this.D, this.E, this.B, this.C);
        }
    }
    
    private final class _H implements Runnable
    {
        private ILink D;
        private int E;
        private String B;
        private String C;
        
        public _H(final ILink d, final int e, final String b, final String c) {
            this.D = d;
            this.E = e;
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.A(this.D, this.E, this.B, this.C);
        }
    }
    
    private final class _L implements Runnable
    {
        private ILink B;
        private int C;
        
        public _L(final ILink b, final int c) {
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.doClose(this.B, this.C);
        }
    }
    
    private final class _D implements Runnable
    {
        private ILink B;
        private int C;
        
        public _D(final ILink b, final int c) {
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.doDetach(this.B, this.C);
        }
    }
    
    private final class _I implements Runnable
    {
        private ILink D;
        private int E;
        private IContext C;
        private IModelObject B;
        
        public _I(final ILink d, final int e, final IContext c, final IModelObject b) {
            this.D = d;
            this.E = e;
            this.C = c;
            this.B = b;
        }
        
        @Override
        public void run() {
            Protocol.this.doExecute(this.D, this.E, this.C, this.B);
        }
    }
    
    private class _J
    {
        String B;
        ILink C;
        int D;
    }
    
    protected class Listener extends NonSyncingListener
    {
        private ILink \u00e8;
        private int \u00e9;
        private String \u00e7;
        private IModelObject \u00e6;
        
        public Listener(final ILink \u00e8, final int \u00e9, final String \u00e7, final IModelObject \u00e6) {
            this.\u00e8 = \u00e8;
            this.\u00e9 = \u00e9;
            this.\u00e7 = \u00e7;
            this.\u00e6 = \u00e6;
        }
        
        public void uninstall() {
            this.uninstall(this.\u00e6);
        }
        
        @Override
        public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
            super.notifyAddChild(modelObject, modelObject2, n);
            if (Protocol.this.J == this.\u00e8) {
                return;
            }
            try {
                Protocol.this.sendAddChild(this.\u00e8, this.\u00e9, Protocol.this.A(this.\u00e6, modelObject), Protocol.this.encode(this.\u00e8, this.\u00e9, modelObject2, false), n);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        
        @Override
        public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
            super.notifyRemoveChild(modelObject, modelObject2, n);
            if (Protocol.this.J == this.\u00e8) {
                return;
            }
            try {
                Protocol.this.sendRemoveChild(this.\u00e8, this.\u00e9, Protocol.this.A(this.\u00e6, modelObject), n);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        
        @Override
        public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
            if (Protocol.this.J == this.\u00e8) {
                return;
            }
            final boolean equals = s.equals("id");
            if (equals) {
                modelObject.getModel().revert();
            }
            final String a = Protocol.this.A(this.\u00e6, modelObject);
            if (equals) {
                modelObject.getModel().restore();
            }
            try {
                Protocol.this.sendChangeAttribute(this.\u00e8, this.\u00e9, a, s, o);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        
        @Override
        public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
            if (Protocol.this.J == this.\u00e8) {
                return;
            }
            final boolean equals = s.equals("id");
            if (equals) {
                modelObject.getModel().revert();
            }
            final String a = Protocol.this.A(this.\u00e6, modelObject);
            if (equals) {
                modelObject.getModel().restore();
            }
            try {
                Protocol.this.sendClearAttribute(this.\u00e8, this.\u00e9, a, s);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        
        @Override
        public void notifyDirty(final IModelObject modelObject, final boolean b) {
            if (Protocol.this.J == this.\u00e8 || Protocol.this.M.get(modelObject) != null) {
                return;
            }
            try {
                Protocol.this.sendChangeDirty(this.\u00e8, this.\u00e9, Protocol.this.A(this.\u00e6, modelObject), b);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof Listener) {
                final Listener listener = (Listener)o;
                return listener.\u00e8 == this.\u00e8 && listener.\u00e7.equals(this.\u00e7);
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return this.\u00e8.hashCode() + this.\u00e7.hashCode();
        }
    }
    
    private final class _E implements Runnable
    {
        private ILink B;
        private int D;
        private IModelObject C;
        
        public _E(final ILink b, final int d, final IModelObject c) {
            this.B = b;
            this.D = d;
            this.C = c;
        }
        
        @Override
        public void run() {
            try {
                Protocol.this.doQuery(this.B, this.D, this.C);
            }
            catch (IOException ex) {
                Protocol.E.exception(ex);
            }
        }
    }
    
    private final class _A implements Runnable
    {
        private ILink D;
        private int E;
        private String B;
        private int C;
        
        public _A(final ILink d, final int e, final String b, final int c) {
            this.D = d;
            this.E = e;
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.A(this.D, this.E, this.B, this.C);
        }
    }
    
    private final class _F implements Runnable
    {
        private ILink B;
        private int C;
        
        public _F(final ILink b, final int c) {
            this.B = b;
            this.C = c;
        }
        
        @Override
        public void run() {
            Protocol.this.doSessionClose(this.B, this.C);
        }
    }
    
    protected class SessionInfo
    {
        public BlockingQueue<byte[]> responseQueue;
        public IDispatcher dispatcher;
        public String xpath;
        public IModelObject element;
        public boolean isAttachClient;
        public Map<String, IModelObject> index;
        public TabularCompressor compressor;
        public Listener listener;
        
        public SessionInfo() {
            this.responseQueue = new SynchronousQueue<byte[]>();
            this.index = new HashMap<String, IModelObject>();
            this.compressor = new TabularCompressor(TabularCompressor.PostCompression.zip);
        }
    }
    
    private final class _C implements Runnable
    {
        private ILink C;
        private int D;
        private String B;
        
        public _C(final ILink c, final int d, final String b) {
            this.C = c;
            this.D = d;
            this.B = b;
        }
        
        @Override
        public void run() {
            final IModelObject modelObject = Protocol.this.getSession(this.C, this.D).index.get(this.B);
            if (modelObject != null) {
                try {
                    modelObject.getChildren();
                    Protocol.this.sendSyncResponse(this.C, this.D);
                }
                catch (IOException ex) {
                    Protocol.E.exception(ex);
                }
            }
        }
    }
    
    public enum Type
    {
        sessionOpenRequest("sessionOpenRequest", 0), 
        sessionOpenResponse("sessionOpenResponse", 1), 
        sessionCloseRequest("sessionCloseRequest", 2), 
        error("error", 3), 
        attachRequest("attachRequest", 4), 
        attachResponse("attachResponse", 5), 
        detachRequest("detachRequest", 6), 
        syncRequest("syncRequest", 7), 
        syncResponse("syncResponse", 8), 
        addChild("addChild", 9), 
        removeChild("removeChild", 10), 
        changeAttribute("changeAttribute", 11), 
        clearAttribute("clearAttribute", 12), 
        changeDirty("changeDirty", 13), 
        queryRequest("queryRequest", 14), 
        queryResponse("queryResponse", 15), 
        executeRequest("executeRequest", 16), 
        executeResponse("executeResponse", 17), 
        debugGo("debugGo", 18), 
        debugStop("debugStop", 19), 
        debugStepIn("debugStepIn", 20), 
        debugStepOver("debugStepOver", 21), 
        debugStepOut("debugStepOut", 22);
        
        static {
            A = new Type[] { Type.sessionOpenRequest, Type.sessionOpenResponse, Type.sessionCloseRequest, Type.error, Type.attachRequest, Type.attachResponse, Type.detachRequest, Type.syncRequest, Type.syncResponse, Type.addChild, Type.removeChild, Type.changeAttribute, Type.clearAttribute, Type.changeDirty, Type.queryRequest, Type.queryResponse, Type.executeRequest, Type.executeResponse, Type.debugGo, Type.debugStop, Type.debugStepIn, Type.debugStepOver, Type.debugStepOut };
        }
        
        private Type(final String s, final int n) {
        }
    }
}
