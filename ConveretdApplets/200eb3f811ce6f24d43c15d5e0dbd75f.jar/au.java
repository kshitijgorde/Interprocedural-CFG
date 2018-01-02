import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class au
{
    public static en a;
    public static Hashtable b;
    
    public static final en a() {
        return au.a;
    }
    
    static {
        (au.a = new en("PushProperties")).a("PUSH_SERVER_DELAY", new eo("PUSH_SERVER_DELAY", "500", et.j, "Integer", 2));
        au.a.a("HTTP_POLL_INTERVAL", new eo("HTTP_POLL_INTERVAL", "1000", et.j, "Integer", 2));
        au.a.a("PUSH_ESTABLISH_TIMEOUT", new eo("PUSH_ESTABLISH_TIMEOUT", "2000", et.j, "Integer", 2));
        au.a.a("PUSH_SLEEP_TIME", new eo("PUSH_SLEEP_TIME", "2000", et.j, "Integer", 2));
        au.a.a("PUSH_IDLE_TIMEOUT", new eo("PUSH_IDLE_TIMEOUT", "10000", et.j, "Integer", 2));
        au.a.a("PUSH_UPDATE_MODE", new eo("PUSH_UPDATE_MODE", "0", et.j, "Integer", 2));
        au.a.a("PUSH_MODE", new eo("PUSH_MODE", "auto", et.o, "String", 2));
        au.a.a("LAUNCHER_MODES", new ep("LAUNCHER_MODES", "config", et.o, "StringArray", 2));
        au.a.a("LAUNCHER_PAUSES", new ep("LAUNCHER_PAUSES", "1000", et.j, "IntegerArray", 2));
        au.a.a("MAX_PUSH_SESSIONS", new eo("MAX_PUSH_SESSIONS", "-1", et.j, "Integer", 2));
        au.a.a("PUSH_CLIENT_NAME", new eo("PUSH_CLIENT_NAME", "", et.o, "String", 2));
        au.a.a("URI_SESSION", new eo("URI_SESSION", "/skye/session.csv?", et.o, "String", 1));
        au.a.a("URI_UPDATE", new eo("URI_UPDATE", "/skye/update.csv?", et.o, "String", 1));
        au.a.a("URI_UNSUBSCRIBE", new eo("URI_UNSUBSCRIBE", "/skye/unsubscribe.csv?", et.o, "String", 1));
        au.a.a("URI_STATUS", new eo("URI_STATUS", "/skye/status.csv?", et.o, "String", 1));
        au.a.a("URI_BYE", new eo("URI_BYE", "/skye/bye.csv?", et.o, "String", 1));
        au.a.a("URI_POST_SUBSCRIBE", new eo("URI_POST_SUBSCRIBE", "/skye/post_subscribe.csv?", et.o, "String", 1));
        au.a.a("URI_PREFIX_SUBSCRIBE", new eo("URI_PREFIX_SUBSCRIBE", "/skye/subscribe", et.o, "String", 1));
        au.a.a("URI_SESSION_STATUS", new eo("URI_SESSION_STATUS", "/skye/session_status.csv?", et.o, "String", 1));
        au.a.a("SESSION_STATUS", new eo("SESSION_STATUS", "SESSION_STATUS", et.o, "String", 1));
        au.a.a("SESSION_STATUS_MSG", new eo("SESSION_STATUS_MSG", "SESSION_STATUS_MSG", et.o, "String", 1));
        au.a.a("PUSH_CONFIG_PARAMS", new eo("PUSH_CONFIG_PARAMS", "&PUSH=1", et.o, "String", 1));
        au.a.a("SESSION_INFO_MSG", new eo("SESSION_INFO_MSG", "", et.o, "String", 1));
        au.a.a("PUSH_CASCADE", new eo("PUSH_CASCADE", "0", et.j, "Integer", 1));
        au.a.a("PUSH_CLIENT_STATUS_TIMEOUT", new eo("PUSH_CLIENT_STATUS_TIMEOUT", "0", et.j, "Integer", 2));
        au.a.a("PUSH_CLIENT_STATUS_FACTOR", new eo("PUSH_CLIENT_STATUS_FACTOR", "0", et.j, "Integer", 2));
        au.a.a("SESSION_FLIP_LIMIT", new eo("SESSION_FLIP_LIMIT", "0", et.j, "Integer", 1));
        au.a.a("SUBSCRIBE_SESSION_STATUS", new eo("SUBSCRIBE_SESSION_STATUS", "false", et.p, "Boolean", 2));
        au.a.a("OBSERVE_SESSION_STATUS", new eo("OBSERVE_SESSION_STATUS", "false", et.p, "Boolean", 2));
        au.a.a("PUSH_UNSUBSCRIBE_DELAY", new eo("PUSH_UNSUBSCRIBE_DELAY", "0", et.j, "Integer", 2));
        au.a.a("MAX_SLEEP_TIME", new eo("MAX_SLEEP_TIME", "60000", et.j, "Integer", 2));
        au.a.a("RESUBSCRIBE_MAX_CHUNK_SIZE", new eo("RESUBSCRIBE_MAX_CHUNK_SIZE", "500", et.j, "Integer", 2));
        au.a.a("RESUBSCRIBE_SLEEP", new eo("RESUBSCRIBE_SLEEP", "100", et.j, "Integer", 2));
        (au.b = new Hashtable()).put("PUSH_SERVER_DELAY", "Time in ms for push server delay");
        au.b.put("HTTP_POLL_INTERVAL", "Time in ms for poll of updates if no direct TCP connection could be established to the push-server");
        au.b.put("PUSH_ESTABLISH_TIMEOUT", "Timeout in ms for initial push-session requests");
        au.b.put("PUSH_SLEEP_TIME", "Time in ms PushSession sleeps before reconnecting");
        au.b.put("PUSH_IDLE_TIMEOUT", "Interval in ms for exchange of 'am-alive heartbeats'");
        au.b.put("PUSH_UPDATE_MODE", "Specifies which kind of updates we want to receive (0, 1 or 2)");
        au.b.put("PUSH_MODE", "Specifies which push mode(s) is/are available");
        au.b.put("LAUNCHER_MODES", "Komma-separated list of push-modes for the SessionLauncher");
        au.b.put("LAUNCHER_PAUSES", "Komma-separated list of millisecond-pauses for the SessionLauncher");
        au.b.put("MAX_PUSH_SESSIONS", "Specifies how many persistent push sessions can be established.");
        au.b.put("PUSH_CLIENT_NAME", "Defines the name of the client");
        au.b.put("URI_SESSION", "The URI to request the session-object");
        au.b.put("URI_UPDATE", "The URI any RequestHandler should use to receive object-updates");
        au.b.put("URI_UNSUBSCRIBE", "The URI to unsubscribe one or more objects");
        au.b.put("URI_STATUS", "The URI to request the session-status");
        au.b.put("URI_BYE", "The URI to close a push-session");
        au.b.put("URI_POST_SUBSCRIBE", "The URI to close a push-session");
        au.b.put("URI_PREFIX_SUBSCRIBE", "The URI-prefix to subscribe one or more objects");
        au.b.put("URI_SESSION_STATUS", "The URI to subscribe to the session status");
        au.b.put("SESSION_STATUS", "The status attribute name of the session_status object");
        au.b.put("SESSION_STATUS_MSG", "The status-message attribute name of the session_status object");
        au.b.put("PUSH_CONFIG_PARAMS", "Defines a URI appendix to the mdg_config URI");
        au.b.put("SESSION_INFO_MSG", "If set, the parameter SESSION_INFO_MSG is added to every session-request.");
        au.b.put("PUSH_CASCADE", "Indicates if the push server can update multiple objects with one update pkt.");
        au.b.put("PUSH_CLIENT_STATUS_TIMEOUT", "Interval in ms the client must send a status-request to keep the client-server communication running.");
        au.b.put("PUSH_CLIENT_STATUS_FACTOR", "Before sending the PUSH_CLIENT_STATUS_TIMEOUT to the server, the timeout is multiplied by PUSH_CLIENT_STATUS_FACTOR");
        au.b.put("SESSION_FLIP_LIMIT", "If nrConnectTries is greater than SESSION_FLIP_LIMIT, the pushSessionMode changes between push and poll for any further connection request");
        au.b.put("SUBSCRIBE_SESSION_STATUS", "Indicate whether the PushSession subscribes to a status object which provides server-side information about a session's status");
        au.b.put("OBSERVE_SESSION_STATUS", "Observe the session_status object and close the session if needed");
        au.b.put("PUSH_UNSUBSCRIBE_DELAY", "The delay which is used to wait for unsubscriptions");
        au.b.put("MAX_SLEEP_TIME", "The maximum sleep time in milliseconds for a PushSession between reconnect attempts");
        au.b.put("RESUBSCRIBE_MAX_CHUNK_SIZE", "The maximum size of a subscription array when resubscribing after a connection failure");
        au.b.put("RESUBSCRIBE_SLEEP", "The sleep time between resubscribe chunks");
    }
}
