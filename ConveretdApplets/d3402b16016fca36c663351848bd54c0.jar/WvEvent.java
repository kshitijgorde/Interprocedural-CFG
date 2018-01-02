// 
// Decompiled by Procyon v0.5.30
// 

public class WvEvent
{
    public static final int NoConnect = 10000;
    public static final int KickOff = 10001;
    public static final int Connect = 10002;
    public static final int Disconnect = 10011;
    public static final int CantConnect = 10012;
    public static final int VideoTimeout = 10013;
    public static final int VideoCaptureServerConnectionEstablished = 11;
    public static final int VideoCaptureServerConnectionClosed = 12;
    public static final int VideoCaptureServerConnectionFailed = 13;
    public static final int CameraControlServerConnectionEstablished = 14;
    public static final int CameraControlServerConnectionClosed = 15;
    public static final int CameraControlServerConnectionFailed = 16;
    public static final int CameraConnectError = 201;
    public static final int CameraNotAvailable = 204;
    public static final int CameraIsNotAvailable = 302;
    public static final int CameraOperated = 41;
    public static final int CameraOperatedByAnotherClient = 42;
    public static final int InternalPtzDispatch = 10020;
    public static final int CameraSelected = 43;
    public static final int CameraChanged = 44;
    public static final int PresetCameraOperationFinished = 45;
    public static final int CameraWillChanged = 46;
    public static final int ImageSizeChanged = 61;
    public static final int CameraPowerOn = 21;
    public static final int CameraPowerOff = 22;
    public static final int EnabledCameraControl = 31;
    public static final int DisabledCameraControl = 32;
    public static final int NoCameraControlRight = 301;
    public static final int CameraIsNotControllable = 303;
    public static final int WaitingCameraControl = 33;
    public static final int FailedToGetCameraControl = 34;
    public static final int InternalError = 101;
    public static final int FailToGetFile = 102;
    public static final int ProtocolVersionMismatch = 104;
    public static final int ClientVersionMismatch = 105;
    public static final int PossibleClientVersionMismatch = 106;
    public static final int NowConnecting = 200;
    public static final int LiveCameraMode = 206;
    public static final int ClientConnectionClosed = 23;
    public static final int CaptureConnectError = 202;
    public static final int UnknownServerID = 203;
    public static final int StillCameraMode = 205;
    public static final int TooManyCameraServer = 207;
    public static final int NoAvailableCamera = 208;
    public static final int InvalidCameraServer = 209;
    public static final int CameraControlNotAllowed = 211;
    public static final int IPRestrictedCameraServer = 212;
    public static final int UnknownOperator = 401;
    public static final int UnknownParameter = 402;
    public static final int InvalidParameterValue = 403;
    public static final int OperationTimeout = 404;
    public static final int ConnectionTimeLimitIsOver = 500;
    public static final int UnknownConnectionID = 501;
    public static final int FailToCreateClientEntry = 502;
    public static final int TooManyClients = 503;
    public static final int InvalidAddress = 504;
    public static final int ConnectionAlreadyUse = 505;
    public static final int UnknownConnectionName = 506;
    public static final int ClientSessionIsNotAllowed = 507;
    public static final int HttpServerConnectError = 601;
    public static final int FailToGetReplyFromHttp = 602;
    public int id;
    public Object arg;
    public int int1;
    public int int2;
    
    public WvEvent(final int n) {
        this(n, null);
    }
    
    public WvEvent(final int id, final int n) {
        this.id = id;
        this.arg = new Integer(n);
    }
    
    public WvEvent(final int id, final Object arg) {
        this.id = id;
        this.arg = arg;
    }
    
    public WvEvent(final int id, final Object arg, final int int1, final int int2) {
        this.id = id;
        this.arg = arg;
        this.int1 = int1;
        this.int2 = int2;
    }
}
