// 
// Decompiled by Procyon v0.5.30
// 

class Commu003
{
    InfoW \u010d;
    SetTogether \u010e;
    Mpput001 sendData;
    Mpget001 \u010f;
    Mpexe002 \u0110;
    final int \u0111 = 100;
    final int \u0112 = 100;
    final int \u0113 = 100;
    int \u0114;
    String \u0115;
    boolean \u0116;
    boolean inRoom;
    boolean block;
    boolean \u0117;
    String \u0118;
    String \u0119;
    
    Commu003(final SetTogether \u010f, final String \u0119, final InfoW \u010d) {
        this.\u0115 = "[no error occured]";
        this.\u0117 = true;
        this.\u010d = \u010d;
        this.\u0116 = false;
        this.inRoom = false;
        this.block = false;
        this.\u010e = \u010f;
        this.\u0118 = \u0119;
    }
    
    public synchronized void block() {
        this.block = true;
    }
    
    public synchronized void unblock() {
        this.block = false;
    }
    
    public synchronized boolean blocked() {
        return this.block;
    }
    
    public synchronized boolean inRoom() {
        return this.inRoom;
    }
    
    public String getWaitingRoomName() {
        return this.\u0119;
    }
    
    public synchronized boolean createRoom(final String s, final String s2, final String s3, final String s4) {
        if (this.\u0117) {
            this.sendData = new Mpput001(this.\u0118);
            this.\u010f = new Mpget001(this.\u0118, this.\u010d);
            this.\u0110 = new Mpexe002(this.\u0118, this, this.sendData, this.\u010f, this.\u010d);
            this.sendData.reset();
            this.\u010f.start();
            this.\u0110.start();
            this.\u0117 = false;
        }
        if (this.\u0110.createRoom(s, s2, s3, s4)) {
            this.\u010d();
            return this.inRoom = true;
        }
        this.\u0115 = this.\u0110.getErrorMessage();
        return false;
    }
    
    public synchronized boolean enterRoom(final int n, final int n2, final String s, final String s2) {
        if (this.\u0117) {
            this.sendData = new Mpput001(this.\u0118);
            this.\u010f = new Mpget001(this.\u0118, this.\u010d);
            this.\u0110 = new Mpexe002(this.\u0118, this, this.sendData, this.\u010f, this.\u010d);
            this.sendData.reset();
            this.\u010f.start();
            this.\u0110.start();
            this.\u0117 = false;
        }
        if (this.\u0110.enterRoom(n, n2, s, s2)) {
            this.\u010d();
            return this.inRoom = true;
        }
        this.\u0115 = this.\u0110.getErrorMessage();
        return false;
    }
    
    public synchronized boolean enterWaitingRoom(final String s, final String s2) {
        if (this.\u0117) {
            this.sendData = new Mpput001(this.\u0118);
            this.\u010f = new Mpget001(this.\u0118, this.\u010d);
            this.\u0110 = new Mpexe002(this.\u0118, this, this.sendData, this.\u010f, this.\u010d);
            this.sendData.reset();
            this.\u010f.start();
            this.\u0110.start();
            this.\u0117 = false;
        }
        if (this.\u0110.enterWaitingRoom(s, s2)) {
            this.\u010d();
            this.\u0119 = this.\u0110.getRoomName();
            return this.inRoom = true;
        }
        this.\u0115 = this.\u0110.getErrorMessage();
        return false;
    }
    
    public void puzzlerInWaitingRoom(final String s) {
        if (this.\u010e.\u0292B) {
            this.\u010e.puzzlerInWaitingRoom(s);
        }
    }
    
    public int getRoomID() {
        return Integer.parseInt(this.\u0110.getRoomID());
    }
    
    public int getPuzzleType() {
        return this.\u0110.getPuzzleType();
    }
    
    public SetSolutionList getCurrentSolutions() {
        return this.\u0110.getCurrentSolutions();
    }
    
    public void setSolutions(final SetSolutionList solutions) {
        this.\u010e.setSolutions(solutions);
    }
    
    public synchronized boolean enterWaitingRoom2(final int n, final int n2, final String s) {
        if (this.\u0117) {
            this.sendData = new Mpput001(this.\u0118);
            this.\u010f = new Mpget001(this.\u0118, this.\u010d);
            this.\u0110 = new Mpexe002(this.\u0118, this, this.sendData, this.\u010f, this.\u010d);
            this.sendData.reset();
            this.\u010f.start();
            this.\u0110.start();
            this.\u0117 = false;
        }
        if (this.\u0110.enterWaitingRoom2(n, n2, s)) {
            this.\u010d();
            this.\u0119 = this.\u0110.getRoomName();
            return this.inRoom = true;
        }
        this.\u0115 = this.\u0110.getErrorMessage();
        return false;
    }
    
    public synchronized void leaveRoom() {
        this.\u0110.leaveRoom();
        this.\u010e();
        this.inRoom = false;
    }
    
    public synchronized void loadPuzzle(final int n, final int n2) {
        this.\u0110.loadPuzzle(n, n2);
    }
    
    public synchronized void sendChatMessage(final String s) {
        if (this.inRoom) {
            this.sendToInfoWindow("Commu003", "[SendChatMessage][chatX" + s + "]\n");
            this.sendData.addPacket("chatX" + s);
        }
    }
    
    public void sendToInfoWindow(final String s, final String s2) {
        if (this.\u010d != null) {
            this.sendToInfoWindow(s, s2);
        }
    }
    
    public synchronized void sendPuzzlePiece(final String s, final int n, final int n2) {
        if (this.inRoom) {
            final PuzzleMove puzzleMove = new PuzzleMove(this.\u0110.getUniqueNo() % 100, s, n, n2);
            this.sendToInfoWindow("Commu003", "[SendPuzzlePiece][" + puzzleMove.getMoveString() + "]\n");
            this.sendData.addPacket("move" + puzzleMove.getMoveString());
        }
    }
    
    public synchronized void sendSetFound(final int n, final int n2, final int n3, final String s) {
        if (this.inRoom) {
            this.sendData.addPacket("setmove[" + n + "][" + n2 + "][" + n3 + "][" + s + "]");
        }
    }
    
    public synchronized void reset() {
        this.\u010e();
        this.\u010d();
    }
    
    public synchronized int reconnect() {
        ++this.\u0114;
        return this.\u0110.reconnect();
    }
    
    public synchronized int reconnectAttempts() {
        return this.\u0114;
    }
    
    public synchronized String getErrorMessage() {
        return this.\u0115;
    }
    
    public synchronized String processGetRoomUsersList() {
        return this.\u0110.processGetRoomUsersList();
    }
    
    public synchronized void processSetMove(final String s) {
        this.\u010e.verwerkSetMove(s);
    }
    
    public synchronized void processPuzzleMove(final int n, final int n2, final String s, final int n3) {
        this.\u010e.verwerkPuzzelstukje(n, n2, s, n3);
    }
    
    public synchronized void processChatMessage(final String s) {
        this.\u010e.verwerkChatTekst(s);
    }
    
    public synchronized void updateUserList(final String s) {
        this.\u010e.updateRoomUserList(s);
    }
    
    public synchronized void updatePuzzle(final String s) {
        this.\u010e.updatePuzzle(s);
    }
    
    public synchronized void reportConnectionLost(final String s) {
        this.\u010e.reportConnectionLost(s);
    }
    
    public synchronized void reportOutOfRoom() {
        this.\u010e.reportOutOfRoom();
    }
    
    public synchronized String getPuzzleContent() {
        return this.\u010e.getPuzzleContent();
    }
    
    public synchronized int getHashCodeCurrentSituation() {
        return this.\u010e.getHashCodeCurrentSituation();
    }
    
    private synchronized void \u010d() {
        this.\u0114 = 0;
        this.\u0115 = "[no error occured]";
        this.sendData.reset();
        this.\u010f.startThread(this.\u0110.getRoomID());
        this.\u0110.startThread();
    }
    
    private synchronized void \u010e() {
        this.sendData.reset();
        this.\u010f.stopThread();
        this.\u0110.stopThread();
    }
    
    public synchronized void killAll() {
        if (!this.\u0117) {
            if (this.inRoom()) {
                this.leaveRoom();
            }
            else {
                this.\u010e();
            }
            this.\u010f.stop();
            this.\u0110.stop();
        }
    }
}
