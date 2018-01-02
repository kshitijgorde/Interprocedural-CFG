// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class as
{
    aw a;
    
    public as(final aw a) {
        this.a = a;
    }
    
    public String if(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "TranslateInnerDemo", ex);
        }
        return s;
    }
    
    public String a(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Nombre de Usuario";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Send Chat")) {
                return "Enviar Chat";
            }
            if (s.equals("Destination")) {
                return "Destino";
            }
            if (s.equals("Provider")) {
                return "Proveedor";
            }
            if (s.equals("Password")) {
                return "Clave";
            }
            if (s.equals("Save Settings")) {
                return "Salvar Configuraci\u00f3n";
            }
            if (s.equals("Connect")) {
                return "Conectar";
            }
            if (s.equals("Hangup")) {
                return "Colgar";
            }
            if (s.equals("Reject")) {
                return "Rechazar";
            }
            if (s.equals("Accept")) {
                return "Aceptar";
            }
            if (s.equals("Call")) {
                return "Llamar";
            }
            if (s.equals("Chat")) {
                return "Chat";
            }
            if (s.equals("Mute")) {
                return "Mudo";
            }
            if (s.equals("Transf")) {
                return "Transf";
            }
            if (s.equals("Redial")) {
                return "Remarcar";
            }
            if (s.equals("Send to")) {
                return "Enviar a";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Not Initialized")) {
                return "No Inicializado";
            }
            if (s.equals("Register Failed")) {
                return "Registro Fall\u00f3";
            }
            if (s.equals("Registered")) {
                return "Registrado";
            }
            if (s.equals("Call Finished")) {
                return "Llamada Terminada";
            }
            if (s.equals("Call Initiated")) {
                return "Llamada Iniciada";
            }
            if (s.equals("Initializing")) {
                return "Inicializando";
            }
            if (s.equals("Initializing...")) {
                return "Inicializando...";
            }
            if (s.equals("Registering")) {
                return "Registrando";
            }
            if (s.equals("Calling")) {
                return "Llamando";
            }
            if (s.equals("In Call")) {
                return "Ocupado";
            }
            if (s.equals("Incoming call")) {
                return "Entrando llamada";
            }
            if (s.equals("Incoming call...")) {
                return "Entrando llamada...";
            }
            if (s.equals("Incoming call from")) {
                return "Entrando llamada de";
            }
            if (s.equals("Unknown")) {
                return "Desconocido";
            }
            if (s.equals("Init")) {
                return "Iniciar";
            }
            if (s.equals("Ready")) {
                return "Listo";
            }
            if (s.equals("Ready.")) {
                return "Listo.";
            }
            if (s.equals("SignIn")) {
                return "Ingresar";
            }
            if (s.equals("Subscribe")) {
                return "Suscribir";
            }
            if (s.equals("Setup")) {
                return "Configurar";
            }
            if (s.equals("CallProgress")) {
                return "Llamada en Progrso";
            }
            if (s.equals("Routed")) {
                return "Enrutado";
            }
            if (s.equals("Ringing")) {
                return "Timbrando";
            }
            if (s.equals("CallInitiated")) {
                return "LlamadaIniciada";
            }
            if (s.equals("CallStarted")) {
                return "Llamada Arrancada";
            }
            if (s.equals("Midcall")) {
                return "Llamada media";
            }
            if (s.equals("CallFinishing")) {
                return "LLamada Terminando";
            }
            if (s.equals("CallFinished")) {
                return "Llamada Terminada";
            }
            if (s.equals("Error")) {
                return "Error";
            }
            if (s.equals("Warning")) {
                return "Advertencia";
            }
            if (s.equals("Trying")) {
                return "Probando";
            }
            if (s.equals("Ringing")) {
                return "Timbrando";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "Llamada est\u00e1 siendo transferida";
            }
            if (s.equals("Queued")) {
                return "Encloada";
            }
            if (s.equals("Session Progress")) {
                return "Sesi\u00f3n en Progreso";
            }
            if (s.equals("OK")) {
                return "ACEPTAR";
            }
            if (s.equals("Accepted")) {
                return "Aceptado";
            }
            if (s.equals("Multiple Choices")) {
                return "M\u00faltiples Selecciones";
            }
            if (s.equals("Moved Permanently")) {
                return "Movida Permanentemente";
            }
            if (s.equals("Moved Temporarily")) {
                return "Movida Temporalmente";
            }
            if (s.equals("Use Proxy")) {
                return "Use Proxy";
            }
            if (s.equals("Alternative Service")) {
                return "Servicio Alternativo";
            }
            if (s.equals("Bad Request")) {
                return "Mala Consulta";
            }
            if (s.equals("Unauthorized")) {
                return "No Autorizado";
            }
            if (s.equals("Payment Required")) {
                return "Pago requerido";
            }
            if (s.equals("Forbidden")) {
                return "Prohibido";
            }
            if (s.equals("Not Found")) {
                return "No encontrado";
            }
            if (s.equals("Method Not Allowed")) {
                return "M\u00e9todo No Permitido";
            }
            if (s.equals("Not Acceptable")) {
                return "No Aceptable";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Proxy Requiere Autenticaci\u00f3n";
            }
            if (s.equals("Request Timeout")) {
                return "Solicitud de tiempo de espera";
            }
            if (s.equals("Conflict")) {
                return "Conflicto";
            }
            if (s.equals("Gone")) {
                return "Ido";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Fall\u00f3 Petici\u00f3n Condicional";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Petici\u00f3n Entidad Demasido Larga";
            }
            if (s.equals("Request-URI Too Long")) {
                return "Petici\u00f3n-URI Demasiado Larga";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Tipo de Medio No Soportado";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Esquema URI No Soportado";
            }
            if (s.equals("Bad Extension")) {
                return "Mala Extensi\u00f3n";
            }
            if (s.equals("Extension Required")) {
                return "Extensi\u00f3n requerida";
            }
            if (s.equals("Interval Too Brief")) {
                return "Interv\u00e1lo Demasido Breve";
            }
            if (s.equals("Unresolvable destination")) {
                return "Destino sin Soluci\u00f3n";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Temporalmente No Disponible";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "Transacci\u00f3n de Llamada No Existe";
            }
            if (s.equals("Loop Detected")) {
                return "Detectado bucle";
            }
            if (s.equals("Too Many Hops")) {
                return "Demasiados Saltos";
            }
            if (s.equals("Address Incomplete")) {
                return "Direcci\u00f3n Incompleta";
            }
            if (s.equals("Ambiguous")) {
                return "Ambiguo";
            }
            if (s.equals("Busy Here")) {
                return "Ocupado aqu\u00ed";
            }
            if (s.equals("Request Terminated")) {
                return "Petici\u00f3n terminada";
            }
            if (s.equals("Not Acceptable Here")) {
                return "NO aceptable aqu\u00ed";
            }
            if (s.equals("Bad Event")) {
                return "Evento malo";
            }
            if (s.equals("Request Pending")) {
                return "Petici\u00f3n pendiente";
            }
            if (s.equals("Undecipherable")) {
                return "Indescifrable";
            }
            if (s.equals("Server Internal Error")) {
                return "Error Interno del Servidor";
            }
            if (s.equals("Not Implemented")) {
                return "No IMplementado";
            }
            if (s.equals("Bad Gateway")) {
                return "Pasarela Mala";
            }
            if (s.equals("Service Unavailable")) {
                return "Servicio No Dispononle";
            }
            if (s.equals("Server Time-out")) {
                return "timeout del servidor";
            }
            if (s.equals("Version Not Supported")) {
                return "Versi\u00f3n No Soportada";
            }
            if (s.equals("Message Too Large")) {
                return "Mensaje Demasiado Largo";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Respuesta No Puede Ser Enviada de Forma Segura";
            }
            if (s.equals("Requires congestion management")) {
                return "Requiere Administraci\u00f3n de Congesti\u00f3n";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Podr\u00eda Inducir a Fragmentaci\u00f3n";
            }
            if (s.equals("Busy Everywhere")) {
                return "Ocupado de Cualquier Manera";
            }
            if (s.equals("Decline")) {
                return "Disminuci\u00f3n";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "No Existe de Cualquier Manera";
            }
            if (s.equals("Not Acceptable")) {
                return "No Aceptable";
            }
            if (s.equals("Cancel")) {
                return "Cancelar";
            }
            if (s.equals("Your authentication username")) {
                return "Su Autenticaci\u00f3n Nombre de Usuario";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Microf\u00f3no";
            }
            if (s.equals("Change microphone volume")) {
                return "Cambiar vol\u00famen del Micr\u00f3fono";
            }
            if (s.equals("Spk")) {
                return "Par";
            }
            if (s.equals("Speaker")) {
                return "Parlante";
            }
            if (s.equals("Change speaker volume")) {
                return "Cammbiar Vol\u00famen del Parlante";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Iniciar llamada de voz o colgar";
            }
            if (s.equals("Send instant message")) {
                return "Enviar mensaje instant\u00e1neo";
            }
            if (s.equals("Logs")) {
                return "Logs";
            }
            if (s.equals("Disconnect current call")) {
                return "Desconectar llamada Actual";
            }
            if (s.equals("Test")) {
                return "Examinar";
            }
            if (s.equals("Audio")) {
                return "Audio";
            }
            if (s.equals("Select audio devices")) {
                return "Seleccionar Dispositivo de Audio";
            }
            if (s.equals("Register to this SIP server")) {
                return "Registrar a este servidor SIP";
            }
            if (s.equals("Phone")) {
                return "Tel\u00e9fono";
            }
            if (s.equals("Line")) {
                return "L\u00ednea";
            }
            if (s.equals("Phone line")) {
                return "L\u00ednea Telef\u00f3nica";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Deshabilitar/Habilitar Audio";
            }
            if (s.equals("Hold")) {
                return "Suspender";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Suspender/recargar llamada actual";
            }
            if (s.equals("Trans")) {
                return "Transferir";
            }
            if (s.equals("Call transfer")) {
                return "Transferir llamada";
            }
            if (s.equals("Recall last number")) {
                return "remarcar \u00faltimo n\u00famero";
            }
            if (s.equals("Send text message")) {
                return "Enviar mensaje de Texto";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Salvar mi configuraci\u00f3n en este computador local(incluyendo mi clave!!!)";
            }
            if (s.equals("Register")) {
                return "Registrar";
            }
            if (s.equals("Deletable")) {
                return "Borrable";
            }
            if (s.equals("Authenticated successfully")) {
                return "Autenticado Exitosamente";
            }
            if (s.equals("Incoming")) {
                return "Entrando";
            }
            if (s.equals("Setup")) {
                return "Configurar";
            }
            if (s.equals("My PhoneNumber")) {
                return "Mi N\u00famero Telef\u00f3nico";
            }
            if (s.equals("Save")) {
                return "Guardar";
            }
            if (s.equals("Back")) {
                return "Atr\u00e1s";
            }
            if (s.equals("Call number")) {
                return "N\u00famero a Llamar";
            }
            if (s.equals("Phone number  to call")) {
                return "N\u00famero telef\u00f3nico a llamar";
            }
            if (s.equals("Exit")) {
                return "Salir";
            }
            if (s.equals("Call")) {
                return "Llamar";
            }
            if (s.equals("Callback")) {
                return "Devolver Llamada";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "N\u00famero A";
            }
            if (s.equals("B Number")) {
                return "N\u00famero B";
            }
            if (s.equals("Initiate Call")) {
                return "Iniciar Llamada";
            }
            if (s.equals("Cancel")) {
                return "Cancelar";
            }
            if (s.equals("SMS sender")) {
                return "Enviar SMS";
            }
            if (s.equals("Phone number")) {
                return "N\u00famero Telef\u00f3nico";
            }
            if (s.equals("SMS message")) {
                return "Mensaje SMS";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Back")) {
                return "Atr\u00e1s";
            }
            if (s.equals("Config saved")) {
                return "Configuraci\u00f3n salvada";
            }
            if (s.equals("cannot save config")) {
                return "No puedo salvar la configiraci\u00f3n";
            }
            if (s.equals("Callback")) {
                return "Devolver Llamada";
            }
            if (s.equals("invalid username")) {
                return "Nombre de usuario Inv\u00e1lido";
            }
            if (s.equals("invalid password")) {
                return "Clave Inv\u00e1lida";
            }
            if (s.equals("invalid phonenumber")) {
                return "N\u00famero telef\u00f3nico Inv\u00e1lido";
            }
            if (s.equals("invalid number")) {
                return "N\u00famero inv\u00e1lido";
            }
            if (s.equals("Callback initiated")) {
                return "iniciar devoluci\u00f3n llamada";
            }
            if (s.equals("Failed")) {
                return "Fall\u00f3";
            }
            if (s.equals("Succeed")) {
                return "\u00c9xito";
            }
            if (s.equals("Check credit")) {
                return "Revisar cr\u00e9dito";
            }
            if (s.equals("ERROR")) {
                return "Error";
            }
            if (s.equals("OK")) {
                return "ACEPTAR";
            }
            if (s.equals("Call")) {
                return "Llamar";
            }
            if (s.equals("Too many requests")) {
                return "Demasiadas peticiones";
            }
            if (s.equals("Too many wrong requests")) {
                return "Demasiadas peticiones err\u00f3neas";
            }
            if (s.equals("no answer")) {
                return "No responde";
            }
            if (s.equals("My balance")) {
                return "Mi balance";
            }
            if (s.equals("Ready")) {
                return "Listo";
            }
            if (s.equals("Call")) {
                return "Llamada";
            }
            if (s.equals("Calling")) {
                return "Llamando";
            }
            if (s.equals("Calling...")) {
                return "Llamando...";
            }
            if (s.equals("InProgress")) {
                return "En Progreso";
            }
            if (s.equals("InProgress...")) {
                return "En Progreso...";
            }
            if (s.equals("Call Finished")) {
                return "Llamada Conclu\u00edda";
            }
            if (s.equals("Speaking")) {
                return "Llamada Activa";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "TranslateInnerDemo", ex);
        }
        return s;
    }
    
    public String case(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Felhaszn\u00e1l\u00f3";
            }
            if (s.equals("Send")) {
                return "K\u00fcld";
            }
            if (s.equals("Send Chat")) {
                return "\u00dczenetet k\u00fcld";
            }
            if (s.equals("Destination")) {
                return "Hivott";
            }
            if (s.equals("Provider")) {
                return "Szolg\u00e1ltat\u00f3";
            }
            if (s.equals("Password")) {
                return "Jelsz\u00f3";
            }
            if (s.equals("Save Settings")) {
                return "Be\u00e1ll\u00edt\u00e1sok ment\u00e9se";
            }
            if (s.equals("Connect")) {
                return "Kapcsol\u00f3d\u00e1s";
            }
            if (s.equals("Hangup")) {
                return "Bont\u00e1s";
            }
            if (s.equals("Reject")) {
                return "Visszautas\u00edt";
            }
            if (s.equals("Accept")) {
                return "Elfogad";
            }
            if (s.equals("Call")) {
                return "H\u00edv\u00e1s";
            }
            if (s.equals("Chat")) {
                return "Chat";
            }
            if (s.equals("Mute")) {
                return "N\u00e9mit";
            }
            if (s.equals("Transf")) {
                return "\u00c1tir\u00e1ny\u00edt";
            }
            if (s.equals("Redial")) {
                return "Visszah\u00edv";
            }
            if (s.equals("Send to")) {
                return "K\u00fcld\u00e9s";
            }
            if (s.equals("Send")) {
                return "K\u00fcld";
            }
            if (s.equals("Calling")) {
                return "T\u00e1rcs\u00e1z\u00e1s";
            }
            if (s.equals("Calling...")) {
                return "T\u00e1rcs\u00e1z\u00e1s...";
            }
            if (s.equals("InProgress...")) {
                return "T\u00e1rcs\u00e1z\u00e1s...";
            }
            if (s.equals("Call")) {
                return "T\u00e1rcs\u00e1z";
            }
            if (s.equals("Call rejected")) {
                return "Hiv\u00e1s el\u00fatasitva";
            }
            if (s.equals("service unavailable")) {
                return "szolg\u00e1ltat\u00e1s nemel\u00e9rhet\u0151";
            }
            if (s.equals("Call rejected: service unavailable")) {
                return "Hiv\u00e1s el\u00fatasitva: szolg\u00e1ltat\u00e1s nemel\u00e9rhet\u0151";
            }
            if (s.equals("Not Initialized")) {
                return "Nem inicializ\u00e1lt";
            }
            if (s.equals("Register Failed")) {
                return "Sikertelen bejelentkez\u00e9s ";
            }
            if (s.equals("Registered")) {
                return "Bejelentkezett";
            }
            if (s.equals("Call Finished")) {
                return "H\u00edv\u00e1s v\u00e9ge";
            }
            if (s.equals("Call Initiated")) {
                return "H\u00edv\u00e1s folyamatban";
            }
            if (s.equals("Initializing")) {
                return "Inicializ\u00e1liz\u00e1l\u00e1s";
            }
            if (s.equals("Initializing...")) {
                return "Inicializ\u00e1liz\u00e1l\u00e1s...";
            }
            if (s.equals("Registering")) {
                return "Bejelentkez\u00e9s";
            }
            if (s.equals("Calling")) {
                return "H\u00edv\u00e1s";
            }
            if (s.equals("In Call")) {
                return "H\u00edv\u00e1sban";
            }
            if (s.equals("Incoming call")) {
                return "Bej\u00f6v\u0151 h\u00edv\u00e1s";
            }
            if (s.equals("Incoming call...")) {
                return "Bej\u00f6v\u0151 h\u00edv\u00e1s...";
            }
            if (s.equals("Incoming call from")) {
                return "Bej\u00f6v\u0151 h\u00edv\u00e1s";
            }
            if (s.equals("Unknown")) {
                return "Ismeretlen";
            }
            if (s.equals("Init")) {
                return "Inicializ\u00e1l\u00e1s";
            }
            if (s.equals("Ready")) {
                return "K\u00e9szenl\u00e9tben";
            }
            if (s.equals("Ready.")) {
                return "K\u00e9szenl\u00e9tben.";
            }
            if (s.equals("Outband")) {
                return "Outband";
            }
            if (s.equals("SignIn")) {
                return "Bejelentkez\u00e9s";
            }
            if (s.equals("Subscribe")) {
                return "Feliratkoz\u00e1s";
            }
            if (s.equals("Setup")) {
                return "Be\u00e1ll\u00edt\u00e1s";
            }
            if (s.equals("CallProgress")) {
                return "H\u00edv\u00e1s folyamatban";
            }
            if (s.equals("Routed")) {
                return "Routolt";
            }
            if (s.equals("Ringing")) {
                return "Cs\u00f6rg\u00e9s";
            }
            if (s.equals("CallInitiated")) {
                return "H\u00edv\u00e1s inicializ\u00e1lva";
            }
            if (s.equals("CallStarted")) {
                return "H\u00edv\u00e1s megkezdve";
            }
            if (s.equals("Midcall")) {
                return "H\u00edv\u00e1s k\u00f6zben";
            }
            if (s.equals("CallFinishing")) {
                return "H\u00edv\u00e1s befejez\u0151dik";
            }
            if (s.equals("CallFinished")) {
                return "H\u00edv\u00e1s befejezve";
            }
            if (s.equals("Error")) {
                return "Hiba";
            }
            if (s.equals("Warning")) {
                return "Figyelmeztet\u00e9s";
            }
            if (s.equals("Trying")) {
                return "Pr\u00f3b\u00e1lkoz\u00e1s";
            }
            if (s.equals("Ringing")) {
                return "Cs\u00f6nget\u00e9s";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "H\u00edv\u00e1s tov\u00e1bb\u00edt\u00f3dik";
            }
            if (s.equals("Queued")) {
                return "Sorba t\u00e9ve";
            }
            if (s.equals("Session Progress")) {
                return "Folyamatban";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Accepted")) {
                return "Elfogadva";
            }
            if (s.equals("Multiple Choices")) {
                return "T\u00f6bb v\u00e1laszt\u00e1s";
            }
            if (s.equals("Moved Permanently")) {
                return "V\u00e9gleg elk\u00f6lt\u00f6z\u00f6tt";
            }
            if (s.equals("Moved Temporarily")) {
                return "Ideiglenesen elk\u00f6lt\u00f6z\u00f6tt";
            }
            if (s.equals("Use Proxy")) {
                return "Haszn\u00e1lj proxy-t";
            }
            if (s.equals("Alternative Service")) {
                return "Alternat\u00edv szolg\u00e1ltat\u00e1s";
            }
            if (s.equals("Bad Request")) {
                return "Rossz k\u00e9r\u00e9s";
            }
            if (s.equals("Unauthorized")) {
                return "Jogosulatlan";
            }
            if (s.equals("Payment Required")) {
                return "Fizet\u00e9s sz\u00fcks\u00e9ges";
            }
            if (s.equals("Forbidden")) {
                return "Tiltva";
            }
            if (s.equals("Not Found")) {
                return "Nem tal\u00e1lt";
            }
            if (s.equals("Method Not Allowed")) {
                return "M\u00f3dszer nem megengedett";
            }
            if (s.equals("Not Acceptable")) {
                return "Elfogadhatatlan";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Proxy hiteles\u00edt\u00e9s sz\u00fcks\u00e9ges";
            }
            if (s.equals("Request Timeout")) {
                return "K\u00e9r\u00e9s ideje lej\u00e1rt";
            }
            if (s.equals("Conflict")) {
                return "Konfliktus";
            }
            if (s.equals("Gone")) {
                return "M\u00e1r nem tal\u00e1lhat\u00f3";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Felt\u00e9teles k\u00e9r\u00e9s meghius\u00falt";
            }
            if (s.equals("Request Entity Too Large")) {
                return "T\u00fal hossz\u00fa k\u00e9r\u00e9s";
            }
            if (s.equals("Request-URI Too Long")) {
                return "K\u00e9r\u00e9s URI-ja t\u00fal hossz\u00fa";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Nem t\u00e1mogatott m\u00e9dia t\u00edpus";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Nem t\u00e1mogatott URI minta";
            }
            if (s.equals("Bad Extension")) {
                return "Rossz kiterjeszt\u00e9s";
            }
            if (s.equals("Extension Required")) {
                return "Kiterjeszt\u00e9s sz\u00fcks\u00e9ges";
            }
            if (s.equals("Interval Too Brief")) {
                return "Intervallum t\u00fal r\u00f6vid";
            }
            if (s.equals("Unresolvable destination")) {
                return "Nem megfelel\u0151 c\u00e9l";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Ideiglenesen nem el\u00e9rhet\u0151";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "H\u00edv\u00e1s nem l\u00e9tezik";
            }
            if (s.equals("Loop Detected")) {
                return "Hurok \u00e9szlelve";
            }
            if (s.equals("Too Many Hops")) {
                return "T\u00fal sok ugr\u00e1s";
            }
            if (s.equals("Address Incomplete")) {
                return "C\u00edm nem teljes";
            }
            if (s.equals("Ambiguous")) {
                return "K\u00e9t\u00e9rtelm\u0171";
            }
            if (s.equals("Busy Here")) {
                return "Foglalt";
            }
            if (s.equals("Request Terminated")) {
                return "K\u00e9r\u00e9s lej\u00e1rt";
            }
            if (s.equals("Not Acceptable Here")) {
                return "Nem elfogadhat\u00f3";
            }
            if (s.equals("Bad Event")) {
                return "Rossz esem\u00e9ny";
            }
            if (s.equals("Request Pending")) {
                return "K\u00e9r\u00e9s f\u00fcgg\u0151ben van";
            }
            if (s.equals("Undecipherable")) {
                return "Megfejthetetlen";
            }
            if (s.equals("Server Internal Error")) {
                return "Szerver bels\u0151 hiba";
            }
            if (s.equals("Not Implemented")) {
                return "Nem teljes\u00edtve";
            }
            if (s.equals("Bad Gateway")) {
                return "Rossz gateway";
            }
            if (s.equals("Service Unavailable")) {
                return "Szolg\u00e1ltat\u00e1s el\u00e9rhetetlen";
            }
            if (s.equals("Server Time-out")) {
                return "Szerver id\u0151 lej\u00e1rt";
            }
            if (s.equals("Version Not Supported")) {
                return "Verzi\u00f3 nincs t\u00e1mogatva";
            }
            if (s.equals("Message Too Large")) {
                return "\u00dczenet t\u00fal nagy";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Nem lehet biztons\u00e1gosan v\u00e1laszolni";
            }
            if (s.equals("Requires congestion management")) {
                return "Torl\u00f3d\u00e1s kezel\u00e9se sz\u00fcks\u00e9ges";
            }
            if (s.equals("Would induce fragmentation")) {
                return "T\u00f6red\u00e9kez\u0151d\u00e9st okozna";
            }
            if (s.equals("Busy Everywhere")) {
                return "Mindenhol foglalt";
            }
            if (s.equals("Decline")) {
                return "Visszautas\u00edt";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "Sehol sem l\u00e9tezik";
            }
            if (s.equals("Not Acceptable")) {
                return "Nem elfogadhat\u00f3";
            }
            if (s.equals("Cancel")) {
                return "Visszavon";
            }
            if (s.equals("Your authentication username")) {
                return "Az \u00d6n hiteles\u00edt\u0151 felhaszn\u00e1l\u00f3neve";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Mikrofon";
            }
            if (s.equals("Change microphone volume")) {
                return "Mikrofon hangerej\u00e9t m\u00f3dos\u00edtja";
            }
            if (s.equals("Spk")) {
                return "Spk";
            }
            if (s.equals("Speaker")) {
                return "Hangsz\u00f3r\u00f3";
            }
            if (s.equals("Change speaker volume")) {
                return "Hangsz\u00f3r\u00f3 hangerej\u00e9t m\u00f3dos\u00edtja";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Kezdem\u00e9nyezzen h\u00edv\u00e1st vagy tegye le";
            }
            if (s.equals("Send instant message")) {
                return "K\u00fcldj \u00fczenetet";
            }
            if (s.equals("Logs")) {
                return "Logok";
            }
            if (s.equals("Disconnect current call")) {
                return "H\u00edv\u00e1st megszak\u00edt\u00e1sa";
            }
            if (s.equals("Test")) {
                return "Teszt";
            }
            if (s.equals("Audio")) {
                return "Audio";
            }
            if (s.equals("Select audio devices")) {
                return "V\u00e1lasszon audi\u00f3 eszk\u00f6zt";
            }
            if (s.equals("Register to this SIP server")) {
                return "Jelentkezzen be ehhez a SIP szerverhez";
            }
            if (s.equals("Phone")) {
                return "Telefon";
            }
            if (s.equals("Line")) {
                return "Vonal";
            }
            if (s.equals("Phone line")) {
                return "Telefon vonal";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Audi\u00f3t kiiktat/enged\u00e9lyez";
            }
            if (s.equals("Hold")) {
                return "Tart";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Hiv\u00e1start\u00e1s/\u00fajrah\u00edv";
            }
            if (s.equals("Trans")) {
                return "\u00c1tir\u00e1ny\u00edt";
            }
            if (s.equals("Call transfer")) {
                return "H\u00edv\u00e1s \u00e1tir\u00e1ny\u00edt\u00e1s";
            }
            if (s.equals("Recall last number")) {
                return "H\u00edvd \u00fajra az utols\u00f3 sz\u00e1mot";
            }
            if (s.equals("Send text message")) {
                return "Sz\u00f6veges \u00fczenet k\u00fcld\u00e9se";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Mentse le be\u00e1ll\u00edt\u00e1saimat ezen a sz\u00e1m\u00edt\u00f3g\u00e9pen (jelsz\u00f3t bele\u00e9rtve)";
            }
            if (s.equals("Register")) {
                return "Bejelentkez\u00e9s";
            }
            if (s.equals("Deletable")) {
                return "T\u00f6rlend\u0151";
            }
            if (s.equals("Authenticated successfully")) {
                return "Sikeres bejelentkez\u00e9s";
            }
            if (s.equals("Incoming")) {
                return "Bej\u00f6v\u0151";
            }
            if (s.equals("Setup")) {
                return "Be\u00e1ll\u00edt\u00e1sok";
            }
            if (s.equals("My PhoneNumber")) {
                return "Telefonsz\u00e1mom";
            }
            if (s.equals("Save")) {
                return "Ment\u00e9s";
            }
            if (s.equals("Back")) {
                return "Vissza";
            }
            if (s.equals("Call number")) {
                return "Sz\u00e1m hiv\u00e1sa";
            }
            if (s.equals("Phone number  to call")) {
                return "H\u00edvand\u00f3 telefonsz\u00e1m";
            }
            if (s.equals("Exit")) {
                return "Kil\u00e9p\u00e9s";
            }
            if (s.equals("Call")) {
                return "H\u00edv\u00e1s";
            }
            if (s.equals("Callback")) {
                return "Visszah\u00edv\u00e1s";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "A sz\u00e1m";
            }
            if (s.equals("B Number")) {
                return "B sz\u00e1m";
            }
            if (s.equals("Initiate Call")) {
                return "H\u00edv\u00e1st kezd";
            }
            if (s.equals("Cancel")) {
                return "Visszavon";
            }
            if (s.equals("SMS sender")) {
                return "SMS k\u00fcld\u0151";
            }
            if (s.equals("Phone number")) {
                return "Telefonsz\u00e1m";
            }
            if (s.equals("SMS message")) {
                return "SMS \u00fczenet";
            }
            if (s.equals("Send")) {
                return "K\u00fcld";
            }
            if (s.equals("Back")) {
                return "Vissza";
            }
            if (s.equals("Config saved")) {
                return "Konfig lementve";
            }
            if (s.equals("cannot save config")) {
                return "sikeretelen ment\u00e9s";
            }
            if (s.equals("Callback")) {
                return "Visszah\u00edv\u00e1s";
            }
            if (s.equals("invalid username")) {
                return "\u00e9rv\u00e9nytelen felhaszn\u00e1l\u00f3n\u00e9v";
            }
            if (s.equals("invalid password")) {
                return "\u00e9rv\u00e9nytelen jelsz\u00f3";
            }
            if (s.equals("invalid phonenumber")) {
                return "\u00e9rv\u00e9nytelen telefonsz\u00e1m";
            }
            if (s.equals("invalid number")) {
                return "\u00e9rv\u00e9nytelen sz\u00e1m";
            }
            if (s.equals("Callback initiated")) {
                return "Visszah\u00edv\u00e1s inicializ\u00e1lva";
            }
            if (s.equals("Failed")) {
                return "Sikertelen";
            }
            if (s.equals("Succeed")) {
                return "Sikeres";
            }
            if (s.equals("Check credit")) {
                return "Ellen\u0151rizze a kreditet";
            }
            if (s.equals("ERROR")) {
                return "HIBA";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Call")) {
                return "H\u00edv\u00e1s";
            }
            if (s.equals("Too many requests")) {
                return "T\u00fal sok k\u00e9r\u00e9s";
            }
            if (s.equals("Too many wrong requests")) {
                return "T\u00fal sok rossz k\u00e9r\u00e9s";
            }
            if (s.equals("no answer")) {
                return "nincs v\u00e1lasz";
            }
            if (s.equals("My balance")) {
                return "M\u00e9rlegem";
            }
            if (s.equals("Outbound")) {
                return "K\u00fcls\u0151";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "TranslateInnerHun", ex);
        }
        return s;
    }
    
    public String for(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "\u0418\u043c\u044f";
            }
            if (s.equals("Send")) {
                return "\u041f\u043e\u0441\u043b\u0430\u0442\u044c";
            }
            if (s.equals("Send Chat")) {
                return "\u041f\u043e\u0441\u043b\u0430\u0442\u044c";
            }
            if (s.equals("Destination")) {
                return "\u041a\u043e\u043c\u0443";
            }
            if (s.equals("Provider")) {
                return "\u041f\u0440\u043e\u0432\u0430\u0439\u0434\u0435\u0440";
            }
            if (s.equals("Username")) {
                return "\u0418\u043c\u044f \u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044f";
            }
            if (s.equals("Password")) {
                return "\u041f\u0430\u0440\u043e\u043b\u044c";
            }
            if (s.equals("Destination")) {
                return "\u041d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Save Settings")) {
                return "\u0417\u0430\u043f\u043e\u043c\u043d\u0438\u0442\u044c";
            }
            if (s.equals("Connect")) {
                return "\u0412\u043e\u0439\u0442\u0438 \u0432 \u0441\u0435\u0442\u044c";
            }
            if (s.equals("Hangup")) {
                return "\u041f\u043e\u0432\u0435\u0441\u0438\u0442\u044c \u0442\u0440\u0443\u0431\u043a\u0443";
            }
            if (s.equals("Reject")) {
                return "\u041e\u0442\u043a\u0430\u0437\u0430\u0442\u044c\u0441\u044f";
            }
            if (s.equals("Accept")) {
                return "\u041f\u0440\u0438\u043d\u044f\u0442\u044c";
            }
            if (s.equals("Call")) {
                return "\u041f\u043e\u0437\u0432\u043e\u043d\u0438\u0442\u044c";
            }
            if (s.equals("Chat")) {
                return "\u041e\u0442\u043f\u0440\u0430\u0432\u0438\u0442\u044c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Mute")) {
                return "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043c\u0438\u043a\u0440\u043e\u0444\u043e\u043d";
            }
            if (s.equals("Transf")) {
                return "\u0422\u0440\u0430\u043d\u0441\u0444\u0435\u0440";
            }
            if (s.equals("Redial")) {
                return "\u041f\u043e\u0432\u0442\u043e\u0440\u043d\u044b\u0439 \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Send to")) {
                return "\u041e\u0442\u043f\u0440\u0430\u0432\u0438\u0442\u044c...";
            }
            if (s.equals("Send")) {
                return "\u041e\u0442\u043f\u0440\u0430\u0432\u0438\u0442\u044c";
            }
            if (s.equals("Not Initialized")) {
                return "\u041d\u0435 \u0438\u043d\u0438\u0446\u0438\u0430\u043b\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u043d\u043e";
            }
            if (s.equals("Register Failed")) {
                return "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043d\u0435 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0430";
            }
            if (s.equals("Registered")) {
                return "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d\u0430";
            }
            if (s.equals("Call Finished")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u0437\u0430\u0432\u0435\u0440\u0448\u043e\u043d";
            }
            if (s.equals("Call Initiated")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u0437\u0430\u043f\u0443\u0449\u0435\u043d";
            }
            if (s.equals("Initializing")) {
                return "\u0418\u043d\u0438\u0446\u0438\u0430\u043b\u0438\u0437\u0430\u0446\u0438\u044f";
            }
            if (s.equals("Initializing...")) {
                return "\u0418\u043d\u0438\u0446\u0438\u0430\u043b\u0438\u0437\u0430\u0446\u0438\u044f";
            }
            if (s.equals("Registering")) {
                return "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f";
            }
            if (s.equals("Calling")) {
                return "\u0417\u0432\u043e\u043d\u0438\u043c";
            }
            if (s.equals("Calling...")) {
                return "\u0417\u0432\u043e\u043d\u0438\u043c";
            }
            if (s.equals("InProgress...")) {
                return "\u0417\u0432\u043e\u043d\u0438\u043c";
            }
            if (s.equals("In Call")) {
                return "\u0412\u043e \u0432\u0440\u0435\u043c\u044f \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("Incoming call")) {
                return "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0439 \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Incoming call...")) {
                return "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0439 \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Incoming call from")) {
                return "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0439 \u0437\u0432\u043e\u043d\u043e\u043a \u043e\u0442";
            }
            if (s.equals("Unknown")) {
                return "\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u0435\u043d";
            }
            if (s.equals("Init")) {
                return "\u0418\u043d\u0438\u0442";
            }
            if (s.equals("Ready")) {
                return "\u0413\u043e\u0442\u043e\u0432";
            }
            if (s.equals("Ready.")) {
                return "\u0413\u043e\u0442\u043e\u0432";
            }
            if (s.equals("Outband")) {
                return "Outband";
            }
            if (s.equals("SignIn")) {
                return "\u0412\u043e\u0439\u0442\u0438";
            }
            if (s.equals("Subscribe")) {
                return "\u041f\u043e\u0434\u043f\u0438\u0441\u0430\u0442\u044c\u0441\u044f";
            }
            if (s.equals("Setup")) {
                return "\u041d\u0430\u0441\u0442\u0440\u043e\u0438\u0442\u044c";
            }
            if (s.equals("CallProgress")) {
                return "\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("Routed")) {
                return "\u041f\u0435\u0440\u0435\u0430\u0434\u0440\u0435\u0441\u043e\u0432\u0430\u043d\u043e";
            }
            if (s.equals("Ringing")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("CallInitiated")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u043d\u0430\u0447\u0438\u043d\u0430\u0435\u0442\u0441\u044f";
            }
            if (s.equals("CallStarted")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u043d\u0430\u0447\u0430\u0442";
            }
            if (s.equals("Midcall")) {
                return "Midcall";
            }
            if (s.equals("CallFinishing")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u0437\u0430\u043a\u0430\u043d\u0447\u0438\u0432\u0430\u0435\u0442\u0441\u044f";
            }
            if (s.equals("CallFinished")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d";
            }
            if (s.equals("Error")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("ERROR")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("Warning")) {
                return "\u041f\u0440\u0435\u0434\u0443\u043f\u0440\u0435\u0436\u0434\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Trying")) {
                return "\u041f\u043e\u043f\u044b\u0442\u043a\u0430";
            }
            if (s.equals("Ringing")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a \u043f\u0435\u0440\u0435\u043d\u0430\u043f\u0440\u0430\u0432\u043b\u044f\u0435\u0442\u0441\u044f";
            }
            if (s.equals("Queued")) {
                return "\u0412 \u043e\u0447\u0435\u0440\u0435\u0434\u0438";
            }
            if (s.equals("Session Progress")) {
                return "\u0421\u043e\u0435\u0434\u0438\u043d\u0435\u043d\u0438\u0435";
            }
            if (s.equals("OK")) {
                return "\u041e\u041a";
            }
            if (s.equals("Accepted")) {
                return "\u041f\u0440\u0438\u043d\u044f\u0442\u043e";
            }
            if (s.equals("Multiple Choices")) {
                return "\u041c\u043d\u043e\u0436\u0435\u0441\u0442\u0432\u0435\u043d\u043d\u044b\u0439 \u0432\u044b\u0431\u043e\u0440";
            }
            if (s.equals("Moved Permanently")) {
                return "\u041f\u043e\u0441\u0442\u043e\u044f\u043d\u043d\u043e \u043f\u0435\u0440\u0435\u043d\u0435\u0441\u0435\u043d\u043e";
            }
            if (s.equals("Moved Temporarily")) {
                return "\u0412\u0440\u0435\u043c\u0435\u043d\u043d\u043e \u043f\u0435\u0440\u0435\u043d\u0435\u0441\u0435\u043d\u043e";
            }
            if (s.equals("Use Proxy")) {
                return "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438";
            }
            if (s.equals("Alternative Service")) {
                return "\u0410\u043b\u044c\u0442\u0435\u0440\u043d\u0430\u0442\u0438\u0432\u043d\u044b\u0439 \u0441\u0435\u0440\u0432\u0438\u0441";
            }
            if (s.equals("Bad Request")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u0437\u0430\u043f\u0440\u043e\u0441";
            }
            if (s.equals("Unauthorized")) {
                return "\u041d\u0435\u0430\u0432\u0442\u043e\u0440\u0438\u0437\u043e\u0432\u0430\u043d\u043e";
            }
            if (s.equals("Payment Required")) {
                return "\u0422\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044f \u043e\u043f\u043b\u0430\u0442\u0430";
            }
            if (s.equals("Forbidden")) {
                return "\u0417\u0430\u043f\u0440\u0435\u0449\u0435\u043d\u043e";
            }
            if (s.equals("Not Found")) {
                return "\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u043e";
            }
            if (s.equals("Method Not Allowed")) {
                return "\u0417\u0430\u043f\u0440\u0435\u0449\u0435\u043d\u043e";
            }
            if (s.equals("Not Acceptable")) {
                return "\u041d\u0435 \u043f\u0440\u0438\u043c\u0435\u043d\u0438\u043c\u043e";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "\u041f\u0440\u043e\u043a\u0441\u0438 \u0437\u0430\u043f\u0440\u0430\u0448\u0438\u0432\u0430\u0435\u0442 \u043b\u043e\u0433\u0438\u043d/\u043f\u0430\u0440\u043e\u043b\u044c";
            }
            if (s.equals("Request Timeout")) {
                return "\u0422\u0430\u0439\u043c\u0430\u0443\u0442";
            }
            if (s.equals("Conflict")) {
                return "\u041a\u043e\u043d\u0444\u043b\u0438\u043a\u0442";
            }
            if (s.equals("Gone")) {
                return "\u0423\u0448\u0451\u043b";
            }
            if (s.equals("Conditional Request Failed")) {
                return "\u0423\u0441\u043b\u043e\u0432\u043d\u044b\u0439 \u0437\u0430\u043f\u0440\u043e\u0441 \u043d\u0435 \u043f\u0440\u043e\u0448\u0435\u043b";
            }
            if (s.equals("Request Entity Too Large")) {
                return "\u0417\u0430\u043f\u0440\u043e\u0448\u0435\u0441 \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u043b\u0438\u043d\u043d\u044b\u0439";
            }
            if (s.equals("Request-URI Too Long")) {
                return "URL \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u043b\u0438\u043d\u043d\u044b\u0439";
            }
            if (s.equals("Unsupported Media Type")) {
                return "\u041d\u0435\u043f\u043e\u0434\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0435\u043c\u044b\u0439 \u0442\u0438\u043f";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "\u041d\u0435\u043f\u043e\u0434\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0435\u043c\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442";
            }
            if (s.equals("Bad Extension")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u043e\u0435 \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Extension Required")) {
                return "\u0422\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044f \u0440\u0430\u0441\u0448\u0438\u0440\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Interval Too Brief")) {
                return "\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u043a\u043e\u0440\u043e\u0442\u043a\u0438\u0439 \u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b";
            }
            if (s.equals("Unresolvable destination")) {
                return "\u041d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043d\u0435 \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u043e";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "\u0412\u0440\u0435\u043c\u0435\u043d\u043d\u043e \u043d\u0435\u0434\u043e\u0441\u0442\u0443\u043f\u043d\u043e";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "\u0422\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u044f \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442";
            }
            if (s.equals("Loop Detected")) {
                return "\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0430 \u043f\u0435\u0442\u043b\u044f";
            }
            if (s.equals("Too Many Hops")) {
                return "\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u043b\u0438\u043d\u043d\u044b\u0439 \u043c\u0430\u0440\u0448\u0440\u0443\u0442";
            }
            if (s.equals("Address Incomplete")) {
                return "\u0412\u0432\u043e\u0434 \u0430\u0434\u0440\u0435\u0441\u0430 \u043d\u0435 \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d";
            }
            if (s.equals("Ambiguous")) {
                return "\u041d\u0435\u043e\u0434\u043d\u043e\u0437\u043d\u0430\u0447\u043d\u043e";
            }
            if (s.equals("Busy Here")) {
                return "\u0417\u0430\u043d\u044f\u0442\u043e";
            }
            if (s.equals("Request Terminated")) {
                return "\u0417\u0430\u043f\u0440\u043e\u0441 \u043e\u0442\u043a\u043b\u043e\u043d\u0435\u043d";
            }
            if (s.equals("Not Acceptable Here")) {
                return "\u041d\u0435 \u043f\u0440\u0438\u043c\u0435\u043d\u0438\u043c\u043e";
            }
            if (s.equals("Bad Event")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("Request Pending")) {
                return "\u0417\u0430\u043f\u0440\u043e\u0441 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0435\u0442\u0441\u044f";
            }
            if (s.equals("Undecipherable")) {
                return "\u041d\u0435 \u043f\u043e\u0434\u0434\u0430\u0435\u0442\u0441\u044f \u0440\u0430\u0441\u0448\u0438\u0444\u0440\u043e\u0432\u043a\u0435";
            }
            if (s.equals("Server Internal Error")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0430";
            }
            if (s.equals("Not Implemented")) {
                return "\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043e";
            }
            if (s.equals("Bad Gateway")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u0448\u043b\u044e\u0437";
            }
            if (s.equals("Service Unavailable")) {
                return "\u0421\u0435\u0440\u0432\u0438\u0441 \u043d\u0435\u0434\u043e\u0441\u0442\u0443\u043f\u0435\u043d";
            }
            if (s.equals("Server Time-out")) {
                return "\u0422\u0430\u0439\u043c\u0430\u0443\u0442 \u0441\u0435\u0440\u0432\u0435\u0440\u0430";
            }
            if (s.equals("Version Not Supported")) {
                return "\u0412\u0435\u0440\u0441\u0438\u044f \u043d\u0435 \u043f\u043e\u0434\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0435\u0442\u0441\u044f";
            }
            if (s.equals("Message Too Large")) {
                return "\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u043b\u0438\u043d\u043d\u043e\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "\u041e\u0442\u0432\u0435\u0442 \u043d\u0435 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u043f\u043e\u0441\u043b\u0430\u043d";
            }
            if (s.equals("Requires congestion management")) {
                return "\u0422\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044f \u0432\u043c\u0435\u0448\u0430\u0442\u0435\u043b\u044c\u0441\u0442\u0432\u043e";
            }
            if (s.equals("Would induce fragmentation")) {
                return "\u0423\u0432\u0435\u043b\u0438\u0447\u0438\u0442 \u0444\u0440\u0430\u0433\u043c\u0435\u043d\u0442\u0430\u0446\u0438\u044e";
            }
            if (s.equals("Busy Everywhere")) {
                return "\u0412\u0435\u0437\u0434\u0435 \u0437\u0430\u043d\u044f\u0442\u043e";
            }
            if (s.equals("Decline")) {
                return "\u041e\u0442\u043a\u043b\u043e\u043d\u0438\u0442\u044c";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "\u0411\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442";
            }
            if (s.equals("Not Acceptable")) {
                return "\u041d\u0435 \u043f\u0440\u0438\u043c\u0435\u043d\u0438\u043c\u043e";
            }
            if (s.equals("OK")) {
                return "\u041e\u041a";
            }
            if (s.equals("Cancel")) {
                return "\u041e\u0442\u043c\u0435\u043d\u0430";
            }
            if (s.equals("Your authentication username")) {
                return "\u0412\u0430\u0448 \u043b\u043e\u0433\u0438\u043d";
            }
            if (s.equals("Mic")) {
                return "\u041c\u0438\u043a\u0440";
            }
            if (s.equals("Microphone")) {
                return "\u041c\u0438\u043a\u0440\u043e\u0444\u043e\u043d";
            }
            if (s.equals("Change microphone volume")) {
                return "\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u0433\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c \u043c\u0438\u043a\u0440\u043e\u0444\u043e\u043d\u0430";
            }
            if (s.equals("Spk")) {
                return "\u041a\u043e\u043b";
            }
            if (s.equals("Speaker")) {
                return "\u041a\u043e\u043b\u043e\u043d\u043a\u0438";
            }
            if (s.equals("Change speaker volume")) {
                return "\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u0433\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c \u043a\u043e\u043b\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "\u041d\u0430\u0447\u0430\u0442\u044c \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u043e\u0439 \u0437\u0432\u043e\u043d\u043e\u043a \u0438\u043b\u0438 \u043f\u043e\u0432\u0435\u0441\u0438\u0442\u044c \u0442\u0440\u0443\u0431\u043a\u0443";
            }
            if (s.equals("Send instant message")) {
                return "\u041f\u043e\u0441\u043b\u0430\u0442\u044c \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Logs")) {
                return "\u041b\u043e\u0433\u0438";
            }
            if (s.equals("Disconnect current call")) {
                return "\u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u0442\u0435\u043a\u0443\u0449\u0438\u0439 \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Test")) {
                return "\u0422\u0435\u0441\u0442";
            }
            if (s.equals("Audio")) {
                return "\u0410\u0443\u0434\u0438\u043e";
            }
            if (s.equals("Select audio devices")) {
                return "\u0412\u044b\u0431\u0440\u0430\u0442\u044c \u0430\u0443\u0434\u0438\u043e \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u043e";
            }
            if (s.equals("Register to this SIP server")) {
                return "\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f \u043d\u0430 \u044d\u0442\u043e\u043c \u0441\u0435\u0440\u0432\u0435\u0440\u0435 SIP";
            }
            if (s.equals("Phone")) {
                return "\u0422\u0435\u043b\u0435\u0444\u043e\u043d";
            }
            if (s.equals("Line")) {
                return "\u041b\u0438\u043d\u0438\u044f";
            }
            if (s.equals("Phone line")) {
                return "\u0422\u0435\u043b\u0435\u0444\u043e\u043d\u043d\u0430\u044f \u043b\u0438\u043d\u0438\u044f";
            }
            if (s.equals("Disable/Enable audio")) {
                return "\u0412\u044b\u043a\u043b/\u0412\u043a\u043b \u0430\u0443\u0434\u0438\u043e";
            }
            if (s.equals("Hold")) {
                return "\u0425\u043e\u043b\u0434";
            }
            if (s.equals("Hold/Reload current call")) {
                return "\u041f\u043e\u0441\u0442\u0430\u0432\u0438\u0442\u044c/\u0421\u043d\u0430\u044f\u0442\u044c \u0441 \u0445\u043e\u043b\u0434\u0430 \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Trans")) {
                return "\u041f\u0435\u0440\u0435\u0432\u043e\u0434";
            }
            if (s.equals("Call transfer")) {
                return "\u041f\u0435\u0440\u0435\u0432\u043e\u0434 \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("Recall last number")) {
                return "\u041f\u0435\u0440\u0435\u0437\u0432\u043e\u043d\u0438\u0442\u044c \u043d\u0430 \u044d\u0442\u043e\u0442 \u0436\u0435 \u043d\u043e\u043c\u0435\u0440";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "\u041d\u0430\u0447\u0430\u0442\u044c \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u043e\u0439 \u0437\u0432\u043e\u043d\u043e\u043a \u0438\u043b\u0438 \u043f\u043e\u0432\u0435\u0441\u0438\u0442\u044c \u0442\u0440\u0443\u0431\u043a\u0443";
            }
            if (s.equals("Send text message")) {
                return "\u041f\u043e\u0441\u043b\u0430\u0442\u044c \u0442\u0435\u043a\u0441\u0442\u043e\u0432\u043e\u0435 \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043c\u043e\u0438 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u043d\u0430 \u044d\u0442\u043e\u043c \u043a\u043e\u043c\u043f\u044c\u044e\u0442\u0435\u0440\u0435 (\u0432\u043a\u043b\u044e\u0447\u0430\u044f \u043f\u0430\u0440\u043e\u043b\u044c!)";
            }
            if (s.equals("Register")) {
                return "\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c";
            }
            if (s.equals("Deletable")) {
                return "\u0423\u0434\u0430\u043b\u0438\u0442\u044c";
            }
            if (s.equals("Authenticated successfully")) {
                return "\u0423\u0441\u043f\u0435\u0448\u043d\u043e \u0430\u0443\u0442\u0435\u043d\u0442\u0438\u0444\u0438\u0446\u0438\u0440\u043e\u0432\u0430\u043d";
            }
            if (s.equals("Incoming")) {
                return "\u0412\u0445\u043e\u0434\u044f\u0449\u0438\u0439";
            }
            if (s.equals("Setup")) {
                return "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438";
            }
            if (s.equals("UserName")) {
                return "\u041b\u043e\u0433\u0438\u043d";
            }
            if (s.equals("Password")) {
                return "\u041f\u0430\u0440\u043e\u043b\u044c";
            }
            if (s.equals("My PhoneNumber")) {
                return "\u041c\u043e\u0439 \u0442\u0435\u043b\u0435\u0444\u043e\u043d\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440";
            }
            if (s.equals("Save")) {
                return "\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c";
            }
            if (s.equals("Back")) {
                return "\u041d\u0430\u0437\u0430\u0434";
            }
            if (s.equals("Call number")) {
                return "\u041d\u043e\u043c\u0435\u0440 \u0442\u0435\u043b\u0435\u0444\u043e\u043d\u0430";
            }
            if (s.equals("Phone number  to call")) {
                return "\u0422\u0435\u043b\u0435\u0444\u043e\u043d\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440 \u0434\u043b\u044f \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("Exit")) {
                return "\u0412\u044b\u0445\u043e\u0434";
            }
            if (s.equals("Call")) {
                return "\u0417\u0432\u043e\u043d\u0438\u0442";
            }
            if (s.equals("Callback")) {
                return "\u041e\u0431\u0440\u0430\u0442\u043d\u044b\u0439 \u0432\u044b\u0437\u043e\u0432";
            }
            if (s.equals("P2P")) {
                return "\u0417\u0430\u043a\u0430\u0437 \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("SMS")) {
                return "\u0421\u041c\u0421";
            }
            if (s.equals("P2P")) {
                return "\u0417\u0430\u043a\u0430\u0437 \u0437\u0432\u043e\u043d\u043a\u0430";
            }
            if (s.equals("A Number")) {
                return "\u041d\u043e\u043c\u0435\u0440 \u0410";
            }
            if (s.equals("B Number")) {
                return "\u041d\u043e\u043c\u0435\u0440 \u0411";
            }
            if (s.equals("Initiate Call")) {
                return "\u0438\u043d\u0438\u0446\u0438\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0437\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Cancel")) {
                return "\u0417\u0430\u043a\u0440\u044b\u0442\u044c";
            }
            if (s.equals("SMS sender")) {
                return "\u041e\u0442\u043f\u0440\u0430\u0432\u043a\u0430 \u0421\u041c\u0421";
            }
            if (s.equals("Phone number")) {
                return "\u0422\u0435\u043b\u0435\u0444\u043e\u043d\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440";
            }
            if (s.equals("SMS message")) {
                return "\u0422\u0435\u043a\u0441\u0442 \u0421\u041c\u0421";
            }
            if (s.equals("Send")) {
                return "\u041e\u0442\u043f\u0440\u0430\u0432\u0438\u0442\u044c";
            }
            if (s.equals("Back")) {
                return "\u041d\u0430\u0437\u0430\u0434";
            }
            if (s.equals("Config saved")) {
                return "\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u044b";
            }
            if (s.equals("cannot save config")) {
                return "\u041d\u0435 \u043c\u043e\u0433\u0443 \u0441\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438";
            }
            if (s.equals("Callback")) {
                return "\u041e\u0431\u0440\u0430\u0442\u043d\u044b\u0439 \u0432\u044b\u0437\u043e\u0432";
            }
            if (s.equals("invalid username")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u043b\u043e\u0433\u0438\u043d";
            }
            if (s.equals("invalid password")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u043f\u0430\u0440\u043e\u043b\u044c";
            }
            if (s.equals("invalid phonenumber")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440";
            }
            if (s.equals("invalid number")) {
                return "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440";
            }
            if (s.equals("Callback initiated")) {
                return "\u041d\u0430\u0447\u0438\u043d\u0430\u0435\u043c \u0437\u0432\u043e\u043d\u0438\u0442\u044c";
            }
            if (s.equals("Failed")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("Succeed")) {
                return "\u0421\u0434\u0435\u043b\u0430\u043d\u043e";
            }
            if (s.equals("Check credit")) {
                return "\u041f\u0440\u043e\u0432\u0435\u0440\u0438\u0442\u044c \u0431\u0430\u043b\u0430\u043d\u0441";
            }
            if (s.equals("ERROR")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("OK")) {
                return "\u041e\u043a";
            }
            if (s.equals("Failed")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("Call")) {
                return "\u0417\u0432\u043e\u043d\u043e\u043a";
            }
            if (s.equals("Too many requests")) {
                return "\u0421\u043b\u0438\u0448\u043a\u043e\u043c \u043c\u043d\u043e\u0433\u043e \u0437\u0430\u043f\u0440\u043e\u0441\u043e\u0432";
            }
            if (s.equals("Too many wrong requests")) {
                return "\u041e\u0448\u0438\u0431\u043a\u0430";
            }
            if (s.equals("no answer")) {
                return "\u041e\u0442\u0432\u0435\u0442\u0430 \u043d\u0435\u0442";
            }
            if (s.equals("My balance")) {
                return "\u0411\u0430\u043b\u0430\u043d\u0441";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "commonTranslateInner", ex);
        }
        return s;
    }
    
    public String int(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Username";
            }
            if (s.equals("Send")) {
                return "Invia";
            }
            if (s.equals("Send Chat")) {
                return "Invia Chat";
            }
            if (s.equals("Destination")) {
                return "Destinazione";
            }
            if (s.equals("Provider")) {
                return "Provider";
            }
            if (s.equals("Password")) {
                return "Password";
            }
            if (s.equals("Save Settings")) {
                return "Salva Impostazioni";
            }
            if (s.equals("Connect")) {
                return "Connetti";
            }
            if (s.equals("Hangup")) {
                return "Chiudi";
            }
            if (s.equals("Reject")) {
                return "Rifiuta";
            }
            if (s.equals("Accept")) {
                return "Accetta";
            }
            if (s.equals("Call")) {
                return "Chiama";
            }
            if (s.equals("Chat")) {
                return "Chat";
            }
            if (s.equals("Mute")) {
                return "Muto";
            }
            if (s.equals("Transf")) {
                return "Trasferimento";
            }
            if (s.equals("Redial")) {
                return "Richiama";
            }
            if (s.equals("Send to")) {
                return "Invia a";
            }
            if (s.equals("Send")) {
                return "Invia";
            }
            if (s.equals("Not Initialized")) {
                return "Non Inizializzato";
            }
            if (s.equals("Register Failed")) {
                return "Registrazione Fallita";
            }
            if (s.equals("Registered")) {
                return "Registrato";
            }
            if (s.equals("Call Finished")) {
                return "Chiamata Terminata";
            }
            if (s.equals("Call Initiated")) {
                return "Chiamata Iniziata";
            }
            if (s.equals("Initializing")) {
                return "Inizializzazione";
            }
            if (s.equals("Initializing...")) {
                return "Inizializzazione...";
            }
            if (s.equals("Registering")) {
                return "Registrazione";
            }
            if (s.equals("Calling")) {
                return "Chiamata in corso";
            }
            if (s.equals("Calling...")) {
                return "Chiamata in corso...";
            }
            if (s.equals("InProgress...")) {
                return "Chiamata in corso...";
            }
            if (s.equals("In Call")) {
                return "In Chiamata";
            }
            if (s.equals("Incoming call")) {
                return "Chiamata in ingresso";
            }
            if (s.equals("Incoming call...")) {
                return "Chiamata in ingresso...";
            }
            if (s.equals("Incoming call from")) {
                return "Chiamata in ingresso da";
            }
            if (s.equals("Unknown")) {
                return "Sconosciuto";
            }
            if (s.equals("Init")) {
                return "Inizio";
            }
            if (s.equals("Ready")) {
                return "Pronto";
            }
            if (s.equals("Ready.")) {
                return "Pronto.";
            }
            if (s.equals("Outband")) {
                return "Fuori banda";
            }
            if (s.equals("SignIn")) {
                return "Registrati";
            }
            if (s.equals("Subscribe")) {
                return "Iscriviti";
            }
            if (s.equals("Setup")) {
                return "Setup";
            }
            if (s.equals("CallProgress")) {
                return "Chiamata in Corso";
            }
            if (s.equals("Routed")) {
                return "Inoltrata";
            }
            if (s.equals("Ringing")) {
                return "Squillando";
            }
            if (s.equals("CallInitiated")) {
                return "Chiamata Inizializzata";
            }
            if (s.equals("CallStarted")) {
                return "Chiamata Iniziata";
            }
            if (s.equals("Midcall")) {
                return "Met\u00e0 chiamata";
            }
            if (s.equals("CallFinishing")) {
                return "Chiamata in Chiusura";
            }
            if (s.equals("CallFinished")) {
                return "Chiamata Terminata";
            }
            if (s.equals("Error")) {
                return "Errore";
            }
            if (s.equals("Warning")) {
                return "Avviso";
            }
            if (s.equals("Trying")) {
                return "Provando";
            }
            if (s.equals("Ringing")) {
                return "Squillando";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "La chiamata \u00e8 stata inoltrata";
            }
            if (s.equals("Queued")) {
                return "Accodata";
            }
            if (s.equals("Session Progress")) {
                return "Progresso Sessione";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Accepted")) {
                return "Accettato";
            }
            if (s.equals("Multiple Choices")) {
                return "Scelte Multiple";
            }
            if (s.equals("Moved Permanently")) {
                return "Mosso Permanentemente";
            }
            if (s.equals("Moved Temporarily")) {
                return "Mosso Temporaneamente";
            }
            if (s.equals("Use Proxy")) {
                return "Utilizza Proxy";
            }
            if (s.equals("Alternative Service")) {
                return "Servizio Alternativo";
            }
            if (s.equals("Bad Request")) {
                return "Richiesta Errata";
            }
            if (s.equals("Unauthorized")) {
                return "Non Autorizzato";
            }
            if (s.equals("Payment Required")) {
                return "Pagamento Richiesto";
            }
            if (s.equals("Forbidden")) {
                return "Negato";
            }
            if (s.equals("Not Found")) {
                return "Non Trovato";
            }
            if (s.equals("Method Not Allowed")) {
                return "Metodo non Consentito";
            }
            if (s.equals("Not Acceptable")) {
                return "Non Accettabile";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Autenticazione Proxy Richiesta";
            }
            if (s.equals("Request Timeout")) {
                return "Timeout Richiesta";
            }
            if (s.equals("Conflict")) {
                return "Conflitto";
            }
            if (s.equals("Gone")) {
                return "Andato";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Richiesta Condizionale Fallita";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Entit\u00e0 della richiesta troppo grande";
            }
            if (s.equals("Request-URI Too Long")) {
                return "URI Richiesta troppo lunga";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Media Type non supportato";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Schema URI non supportato";
            }
            if (s.equals("Bad Extension")) {
                return "Estensione Errata";
            }
            if (s.equals("Extension Required")) {
                return "Estensione Richiesta";
            }
            if (s.equals("Interval Too Brief")) {
                return "Intervallo Troppo Breve";
            }
            if (s.equals("Unresolvable destination")) {
                return "Destinazione non trovata";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Temporaneamente non Disponibile";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "Transazione Chiamata non esistente";
            }
            if (s.equals("Loop Detected")) {
                return "Rilevato Loop";
            }
            if (s.equals("Too Many Hops")) {
                return "Troppi rimbalzi";
            }
            if (s.equals("Address Incomplete")) {
                return "Indirizzo Incompleto";
            }
            if (s.equals("Ambiguous")) {
                return "Ambiguo";
            }
            if (s.equals("Busy Here")) {
                return "Occupato qua";
            }
            if (s.equals("Request Terminated")) {
                return "Richiesta Terminata";
            }
            if (s.equals("Not Acceptable Here")) {
                return "Non Accettabile qua";
            }
            if (s.equals("Bad Event")) {
                return "Evento Errato";
            }
            if (s.equals("Request Pending")) {
                return "Richiesta Pendente";
            }
            if (s.equals("Undecipherable")) {
                return "Indecifrabile";
            }
            if (s.equals("Server Internal Error")) {
                return "Errore Interno del Server";
            }
            if (s.equals("Not Implemented")) {
                return "Non Implementato";
            }
            if (s.equals("Bad Gateway")) {
                return "Gateway Errato";
            }
            if (s.equals("Service Unavailable")) {
                return "Servizio non Disponibile";
            }
            if (s.equals("Server Time-out")) {
                return "Server Time-out";
            }
            if (s.equals("Version Not Supported")) {
                return "Versione Non Supportata";
            }
            if (s.equals("Message Too Large")) {
                return "Messaggio Troppo Largo";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "La risposta non pu\u00f2 essere trasmessa in modo sicuro";
            }
            if (s.equals("Requires congestion management")) {
                return "Richiede la gestione della congestione";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Potrebbe indurre frammentazione";
            }
            if (s.equals("Busy Everywhere")) {
                return "Occupato Dovunque";
            }
            if (s.equals("Decline")) {
                return "Rifiuta";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "Non Esiste da Nessuna Parte";
            }
            if (s.equals("Not Acceptable")) {
                return "Non Accettabile";
            }
            if (s.equals("Cancel")) {
                return "Cancella";
            }
            if (s.equals("Your authentication username")) {
                return "Il tuo nome utente di autenticazione";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Microfono";
            }
            if (s.equals("Change microphone volume")) {
                return "Cambia volume microfono";
            }
            if (s.equals("Spk")) {
                return "Altop";
            }
            if (s.equals("Speaker")) {
                return "Altoparlante";
            }
            if (s.equals("Change speaker volume")) {
                return "Cambia volume altoparlante";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Inizia la chiamata o riaggancia";
            }
            if (s.equals("Send instant message")) {
                return "Invia messaggio istantaneo";
            }
            if (s.equals("Logs")) {
                return "Logs";
            }
            if (s.equals("Disconnect current call")) {
                return "Disconnetti la chiamata corrente";
            }
            if (s.equals("Test")) {
                return "Test";
            }
            if (s.equals("Audio")) {
                return "Audio";
            }
            if (s.equals("Select audio devices")) {
                return "Seleziona dispositivo audio";
            }
            if (s.equals("Register to this SIP server")) {
                return "Registrati a questo server SIP";
            }
            if (s.equals("Phone")) {
                return "Telefono";
            }
            if (s.equals("Line")) {
                return "Linea";
            }
            if (s.equals("Phone line")) {
                return "Linea telefonica";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Disabilita/Abilita audio";
            }
            if (s.equals("Hold")) {
                return "Attesa";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Attesa/Riprendi chiamata corrente";
            }
            if (s.equals("Trans")) {
                return "Trasf";
            }
            if (s.equals("Call transfer")) {
                return "Trasferimento Chiamata";
            }
            if (s.equals("Recall last number")) {
                return "Richiama l'ultimo numero";
            }
            if (s.equals("Send text message")) {
                return "Invia messaggio di testo";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Salva le mie impostazioni in questo computer (inclusa la password!)";
            }
            if (s.equals("Register")) {
                return "Registra";
            }
            if (s.equals("Deletable")) {
                return "Cancellabile";
            }
            if (s.equals("Authenticated successfully")) {
                return "Autenticato con successo";
            }
            if (s.equals("Incoming")) {
                return "Ingresso";
            }
            if (s.equals("Setup")) {
                return "Setup";
            }
            if (s.equals("My PhoneNumber")) {
                return "Mio Numero di Telefono";
            }
            if (s.equals("Save")) {
                return "Salva";
            }
            if (s.equals("Back")) {
                return "Indietro";
            }
            if (s.equals("Call number")) {
                return "Chiama il numero";
            }
            if (s.equals("Phone number  to call")) {
                return "Numero di telefono da chiamare";
            }
            if (s.equals("Exit")) {
                return "Uscita";
            }
            if (s.equals("Call")) {
                return "Chiama";
            }
            if (s.equals("Callback")) {
                return "Richiama";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "Numero A";
            }
            if (s.equals("B Number")) {
                return "Numero B";
            }
            if (s.equals("Initiate Call")) {
                return "Avviare la chiamata";
            }
            if (s.equals("Cancel")) {
                return "Cancella";
            }
            if (s.equals("SMS sender")) {
                return "Mittente SMS";
            }
            if (s.equals("Phone number")) {
                return "Numero Telefono";
            }
            if (s.equals("SMS message")) {
                return "Messaggio SMS";
            }
            if (s.equals("Send")) {
                return "Invia";
            }
            if (s.equals("Back")) {
                return "Indietro";
            }
            if (s.equals("Config saved")) {
                return "Configurazione salvata";
            }
            if (s.equals("cannot save config")) {
                return "Non \u00e8 possibile salvare la configurazione";
            }
            if (s.equals("Callback")) {
                return "Richiamare";
            }
            if (s.equals("invalid username")) {
                return "username non valido";
            }
            if (s.equals("invalid password")) {
                return "password non valida";
            }
            if (s.equals("invalid phonenumber")) {
                return "numero di telefono non valido";
            }
            if (s.equals("invalid number")) {
                return "numero non valido";
            }
            if (s.equals("Callback initiated")) {
                return "Richiamata iniziata";
            }
            if (s.equals("Failed")) {
                return "Fallito";
            }
            if (s.equals("Succeed")) {
                return "Successo";
            }
            if (s.equals("Check credit")) {
                return "Controlla credito";
            }
            if (s.equals("ERROR")) {
                return "ERRORE";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Call")) {
                return "Chiama";
            }
            if (s.equals("Too many requests")) {
                return "Troppe richieste";
            }
            if (s.equals("Too many wrong requests")) {
                return "Troppe richieste errate";
            }
            if (s.equals("no answer")) {
                return "nessuna risposta";
            }
            if (s.equals("My balance")) {
                return "Il mio equilibrio";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "commonTranslateInner", ex);
        }
        return s;
    }
    
    public String new(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Nume utilizator";
            }
            if (s.equals("Send")) {
                return "Trimite";
            }
            if (s.equals("Send Chat")) {
                return "Trimite mesaj";
            }
            if (s.equals("Destination")) {
                return "Destinatie";
            }
            if (s.equals("Provider")) {
                return "Furnizor";
            }
            if (s.equals("Password")) {
                return "Parola";
            }
            if (s.equals("Save Settings")) {
                return "Salveaza Setarile";
            }
            if (s.equals("Connect")) {
                return "Conectare";
            }
            if (s.equals("Hangup")) {
                return "\u00cenchide";
            }
            if (s.equals("Reject")) {
                return "Respinge";
            }
            if (s.equals("Accept")) {
                return "Accepta";
            }
            if (s.equals("Call")) {
                return "Apel";
            }
            if (s.equals("Chat")) {
                return "Mesaj";
            }
            if (s.equals("Mute")) {
                return "Mut";
            }
            if (s.equals("Transf")) {
                return "Transfer";
            }
            if (s.equals("Redial")) {
                return "Reapelare";
            }
            if (s.equals("Send to")) {
                return "Trimite";
            }
            if (s.equals("Send")) {
                return "Trimite";
            }
            if (s.equals("Not Initialized")) {
                return "Neinitializat";
            }
            if (s.equals("Register Failed")) {
                return "\u00cenregistrare esuata";
            }
            if (s.equals("Registered")) {
                return "\u00cenregistrat";
            }
            if (s.equals("Call Finished")) {
                return "Apel terminat";
            }
            if (s.equals("Call Initiated")) {
                return "Apel initiat";
            }
            if (s.equals("Initializing")) {
                return "Initializare";
            }
            if (s.equals("Initializing...")) {
                return "Initializare...";
            }
            if (s.equals("Registering")) {
                return "\u00cenregistrare";
            }
            if (s.equals("Calling")) {
                return "Apelare";
            }
            if (s.equals("Calling...")) {
                return "Apelare...";
            }
            if (s.equals("InProgress...")) {
                return "Apelare...";
            }
            if (s.equals("In Call")) {
                return "\u00cen apel";
            }
            if (s.equals("Incoming call")) {
                return "Soseste apel";
            }
            if (s.equals("Incoming call...")) {
                return "Soseste apel...";
            }
            if (s.equals("Incoming call from")) {
                return "Soseste apel de la";
            }
            if (s.equals("Unknown")) {
                return "Necunoscut";
            }
            if (s.equals("Init")) {
                return "Init";
            }
            if (s.equals("Ready")) {
                return "Gata";
            }
            if (s.equals("Ready.")) {
                return "Gata.";
            }
            if (s.equals("Outband")) {
                return "Outband";
            }
            if (s.equals("SignIn")) {
                return "\u00cenregistrati-va";
            }
            if (s.equals("Subscribe")) {
                return "Aboneaza-te";
            }
            if (s.equals("Setup")) {
                return "Setari";
            }
            if (s.equals("CallProgress")) {
                return "ProgresulApelului";
            }
            if (s.equals("Routed")) {
                return "Directionat";
            }
            if (s.equals("Ringing")) {
                return "Suna";
            }
            if (s.equals("CallInitiated")) {
                return "Apel initiat";
            }
            if (s.equals("CallStarted")) {
                return "Apel pornit";
            }
            if (s.equals("Midcall")) {
                return "\u00cen apel";
            }
            if (s.equals("CallFinishing")) {
                return "ApelulSeTermina";
            }
            if (s.equals("CallFinished")) {
                return "ApelTerminat";
            }
            if (s.equals("Error")) {
                return "Eroare";
            }
            if (s.equals("Warning")) {
                return "Avertisment";
            }
            if (s.equals("Trying")) {
                return "\u00cencerc";
            }
            if (s.equals("Ringing")) {
                return "Suna";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "Apelul este transmis";
            }
            if (s.equals("Queued")) {
                return "Asezat la r\u00e2nd";
            }
            if (s.equals("Session Progress")) {
                return "Progres Sesiune";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Accepted")) {
                return "Acceptat";
            }
            if (s.equals("Multiple Choices")) {
                return "Alegeri multiple";
            }
            if (s.equals("Moved Permanently")) {
                return "S-a mutat permanent";
            }
            if (s.equals("Moved Temporarily")) {
                return "S-a mutat temporar";
            }
            if (s.equals("Use Proxy")) {
                return "Foloseste proxy";
            }
            if (s.equals("Alternative Service")) {
                return "Serviciu Alternativ";
            }
            if (s.equals("Bad Request")) {
                return "Cerere eronata";
            }
            if (s.equals("Unauthorized")) {
                return "Neautorizat";
            }
            if (s.equals("Payment Required")) {
                return "Plata necesara";
            }
            if (s.equals("Forbidden")) {
                return "Interzis";
            }
            if (s.equals("Not Found")) {
                return "Nu s-a gasit";
            }
            if (s.equals("Method Not Allowed")) {
                return "Metoda nepermisa";
            }
            if (s.equals("Not Acceptable")) {
                return "Inacceptabil";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Solicitat Autenticare Proxy";
            }
            if (s.equals("Request Timeout")) {
                return "Timeout Cerere";
            }
            if (s.equals("Conflict")) {
                return "Conflict";
            }
            if (s.equals("Gone")) {
                return "A disparut";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Cerere conditionala esuata";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Entitatea de Cerere e prea mare";
            }
            if (s.equals("Request-URI Too Long")) {
                return "URI Cerere prea lung";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Tip media nesustinut";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Schema URI nesustinut";
            }
            if (s.equals("Bad Extension")) {
                return "Extensie rea";
            }
            if (s.equals("Extension Required")) {
                return "Se solicita extensie";
            }
            if (s.equals("Interval Too Brief")) {
                return "Interval Prea Scurt";
            }
            if (s.equals("Unresolvable destination")) {
                return "Destinatie imposibil de gasit";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Temporar Indisponibil";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "Tranzactia Apel Nu Exista";
            }
            if (s.equals("Loop Detected")) {
                return "Bulca Detectata";
            }
            if (s.equals("Too Many Hops")) {
                return "Prea Multe Salturi";
            }
            if (s.equals("Address Incomplete")) {
                return "Adresa incompleta";
            }
            if (s.equals("Ambiguous")) {
                return "Ambiguu";
            }
            if (s.equals("Busy Here")) {
                return "Ocupat Aici";
            }
            if (s.equals("Request Terminated")) {
                return "Cerere Terminata";
            }
            if (s.equals("Not Acceptable Here")) {
                return "Aici Inacceptabil";
            }
            if (s.equals("Bad Event")) {
                return "Eveniment De Eroare";
            }
            if (s.equals("Request Pending")) {
                return "Cerere \u00cen Asteptare";
            }
            if (s.equals("Undecipherable")) {
                return "Indescifrabil";
            }
            if (s.equals("Server Internal Error")) {
                return "Eroare Interna Server";
            }
            if (s.equals("Not Implemented")) {
                return "Neimplementat";
            }
            if (s.equals("Bad Gateway")) {
                return "Gateway Incorect";
            }
            if (s.equals("Service Unavailable")) {
                return "Serviciu Indisponibil";
            }
            if (s.equals("Server Time-out")) {
                return "Time-out Server";
            }
            if (s.equals("Version Not Supported")) {
                return "Versiune Nesprijinit";
            }
            if (s.equals("Message Too Large")) {
                return "Mesaj Prea Mare";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Raspuns Nu Poate Fi Trimis \u00cen Siguranta";
            }
            if (s.equals("Requires congestion management")) {
                return "Cere managementul congestiei";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Ar cauza fragmentare";
            }
            if (s.equals("Busy Everywhere")) {
                return "Toate ocupate";
            }
            if (s.equals("Decline")) {
                return "Declin";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "Nu Exista Niciunde";
            }
            if (s.equals("Not Acceptable")) {
                return "Inacceptabil";
            }
            if (s.equals("Cancel")) {
                return "Anulare";
            }
            if (s.equals("Your authentication username")) {
                return "Numele utilizator al dvs.";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Microfon";
            }
            if (s.equals("Change microphone volume")) {
                return "Schimba volumul microfonului";
            }
            if (s.equals("Spk")) {
                return "Dif";
            }
            if (s.equals("Speaker")) {
                return "Difuzor";
            }
            if (s.equals("Change speaker volume")) {
                return "Schimba volumul difuzorului";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Initiati apel voce sau \u00eenchidere";
            }
            if (s.equals("Send instant message")) {
                return "Trimite mesaj";
            }
            if (s.equals("Logs")) {
                return "Jurnale";
            }
            if (s.equals("Disconnect current call")) {
                return "\u00cenchide apelul curent";
            }
            if (s.equals("Test")) {
                return "Test";
            }
            if (s.equals("Audio")) {
                return "Audio";
            }
            if (s.equals("Select audio devices")) {
                return "Selecteaza obiectele audio";
            }
            if (s.equals("Register to this SIP server")) {
                return "\u00cenregistrare la acest server SIP";
            }
            if (s.equals("Phone")) {
                return "Telefon";
            }
            if (s.equals("Line")) {
                return "Linie";
            }
            if (s.equals("Phone line")) {
                return "Linie telefon";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Interzice/Permite audio";
            }
            if (s.equals("Hold")) {
                return "Tine";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Tine/Re\u00eencarca apel curent";
            }
            if (s.equals("Trans")) {
                return "Trans";
            }
            if (s.equals("Call transfer")) {
                return "Transfer Apel";
            }
            if (s.equals("Recall last number")) {
                return "Suna din nou ultimul numar";
            }
            if (s.equals("Send text message")) {
                return "Trimite mesaj text";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Salveaza setarile mele local la acest calculator (inclusiv parola)";
            }
            if (s.equals("Register")) {
                return "\u00cenregistrare";
            }
            if (s.equals("Deletable")) {
                return "Anulabil";
            }
            if (s.equals("Authenticated successfully")) {
                return "\u00cenregistrare reusita";
            }
            if (s.equals("Incoming")) {
                return "Sosire";
            }
            if (s.equals("Setup")) {
                return "Setare";
            }
            if (s.equals("My PhoneNumber")) {
                return "Numarul Meu De Telefon";
            }
            if (s.equals("Save")) {
                return "Salveaza";
            }
            if (s.equals("Back")) {
                return "\u00cenapoi";
            }
            if (s.equals("Call number")) {
                return "Apeleaza numarul";
            }
            if (s.equals("Phone number to call")) {
                return "Numar telefon de apelat";
            }
            if (s.equals("Exit")) {
                return "Iesire";
            }
            if (s.equals("Call")) {
                return "Apel";
            }
            if (s.equals("Callback")) {
                return "Callback";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "Numarul A";
            }
            if (s.equals("B Number")) {
                return "Numarul B";
            }
            if (s.equals("Initiate Call")) {
                return "Initiare apel";
            }
            if (s.equals("Cancel")) {
                return "Anulare";
            }
            if (s.equals("SMS sender")) {
                return "Expeditor SMS";
            }
            if (s.equals("Phone number")) {
                return "Numar telefon";
            }
            if (s.equals("SMS message")) {
                return "Mesaj SMS";
            }
            if (s.equals("Send")) {
                return "Trimite";
            }
            if (s.equals("Back")) {
                return "\u00cenapoi";
            }
            if (s.equals("Config saved")) {
                return "Configuratia salvata";
            }
            if (s.equals("cannot save config")) {
                return "nu pot sa salvez configuratia";
            }
            if (s.equals("Callback")) {
                return "Callback";
            }
            if (s.equals("invalid username")) {
                return "nume utilizator invalid";
            }
            if (s.equals("invalid password")) {
                return "parola invalida";
            }
            if (s.equals("invalid phonenumber")) {
                return "numar telefon invalid";
            }
            if (s.equals("invalid number")) {
                return "numar invalid";
            }
            if (s.equals("Callback initiated")) {
                return "Apel initiat";
            }
            if (s.equals("Failed")) {
                return "Esuat";
            }
            if (s.equals("Succeed")) {
                return "Urmeaza";
            }
            if (s.equals("Check credit")) {
                return "Verifica crediul";
            }
            if (s.equals("ERROR")) {
                return "EROARE";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Call")) {
                return "Apel";
            }
            if (s.equals("Too many requests")) {
                return "Prea multe cereri";
            }
            if (s.equals("Too many wrong requests")) {
                return "Prea multe cereri eronate";
            }
            if (s.equals("no answer")) {
                return "nu e raspuns";
            }
            if (s.equals("My balance")) {
                return "Bilantul meu";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "commonTranslateInner", ex);
        }
        return s;
    }
    
    public String byte(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Benutzername";
            }
            if (s.equals("Send")) {
                return "Senden";
            }
            if (s.equals("Send Chat")) {
                return "Message Senden";
            }
            if (s.equals("Destination")) {
                return "Destination";
            }
            if (s.equals("Provider")) {
                return "Dienstanbieter";
            }
            if (s.equals("Password")) {
                return "Passwort";
            }
            if (s.equals("Save Settings")) {
                return "Einstellungen Speichern";
            }
            if (s.equals("Connect")) {
                return "Verbinden";
            }
            if (s.equals("Hangup")) {
                return "Ablegen";
            }
            if (s.equals("Reject")) {
                return "Abweisen";
            }
            if (s.equals("Accept")) {
                return "Annehmen";
            }
            if (s.equals("Call")) {
                return "Anruf";
            }
            if (s.equals("Chat")) {
                return "Message";
            }
            if (s.equals("Mute")) {
                return "Stumm";
            }
            if (s.equals("Transf")) {
                return "Transf";
            }
            if (s.equals("Redial")) {
                return "Neu anrufen";
            }
            if (s.equals("Send to")) {
                return "Senden zu";
            }
            if (s.equals("Send")) {
                return "Senden";
            }
            if (s.equals("Not Initialized")) {
                return "Nicht Inizialisiert";
            }
            if (s.equals("Register Failed")) {
                return "Gescheiterte Registrierung";
            }
            if (s.equals("Registered")) {
                return "Registriert";
            }
            if (s.equals("Call Finished")) {
                return "Anruf beendet";
            }
            if (s.equals("Call Initiated")) {
                return "Anruf inizialisiert";
            }
            if (s.equals("Initializing")) {
                return "Inizialisierung";
            }
            if (s.equals("Initializing...")) {
                return "Inizialisierung...";
            }
            if (s.equals("Registering")) {
                return "Registrierung";
            }
            if (s.equals("Calling")) {
                return "Anruf";
            }
            if (s.equals("Calling...")) {
                return "Anruf...";
            }
            if (s.equals("InProgress...")) {
                return "Anruf...";
            }
            if (s.equals("In Call")) {
                return "Im Anruf";
            }
            if (s.equals("Incoming call")) {
                return "Einlaufende Anruf";
            }
            if (s.equals("Incoming call...")) {
                return "Einlaufende Anruf...";
            }
            if (s.equals("Incoming call from")) {
                return "Einlaufende Anruf von";
            }
            if (s.equals("Unknown")) {
                return "Unbekannte";
            }
            if (s.equals("Init")) {
                return "Init";
            }
            if (s.equals("Ready")) {
                return "Fertig";
            }
            if (s.equals("Ready.")) {
                return "Fertig.";
            }
            if (s.equals("Outband")) {
                return "Ausserband";
            }
            if (s.equals("SignIn")) {
                return "Eintragen";
            }
            if (s.equals("Subscribe")) {
                return "Abonnieren";
            }
            if (s.equals("Setup")) {
                return "Einstellung";
            }
            if (s.equals("CallProgress")) {
                return "Anruf Ablauf";
            }
            if (s.equals("Routed")) {
                return "Geleitet";
            }
            if (s.equals("Ringing")) {
                return "Es klingelt";
            }
            if (s.equals("CallInitiated")) {
                return "Anruf inizialisiert";
            }
            if (s.equals("CallStarted")) {
                return "Anruf gestartet";
            }
            if (s.equals("Midcall")) {
                return "Im Anruf";
            }
            if (s.equals("CallFinishing")) {
                return "Anruf wird beendet";
            }
            if (s.equals("CallFinished")) {
                return "Anruf beendet";
            }
            if (s.equals("Error")) {
                return "Fehler";
            }
            if (s.equals("Warning")) {
                return "Achtung";
            }
            if (s.equals("Trying")) {
                return "Fersuch";
            }
            if (s.equals("Ringing")) {
                return "Klingeln";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "Der Anruf wird weitergeleitet";
            }
            if (s.equals("Queued")) {
                return "Im Reihe gelegt";
            }
            if (s.equals("Session Progress")) {
                return "Session im Ablauf";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Accepted")) {
                return "Akzeptiert";
            }
            if (s.equals("Multiple Choices")) {
                return "Mehrere Ausw\u00e4hle";
            }
            if (s.equals("Moved Permanently")) {
                return "Permanent eingezogen";
            }
            if (s.equals("Moved Temporarily")) {
                return "Einweilen eingezogen";
            }
            if (s.equals("Use Proxy")) {
                return "Proxy verwenden";
            }
            if (s.equals("Alternative Service")) {
                return "Alternative Betrieb";
            }
            if (s.equals("Bad Request")) {
                return "Fehlanfrage";
            }
            if (s.equals("Unauthorized")) {
                return "Nicht autorisiert";
            }
            if (s.equals("Payment Required")) {
                return "Bezahlung n\u00f6tig";
            }
            if (s.equals("Forbidden")) {
                return "Verboten";
            }
            if (s.equals("Not Found")) {
                return "Nicht gefunden";
            }
            if (s.equals("Method Not Allowed")) {
                return "Methode nicht erlaubt";
            }
            if (s.equals("Not Acceptable")) {
                return "Unakkzeptable";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Proxy Authentifikation n\u00f6tig";
            }
            if (s.equals("Request Timeout")) {
                return "Time-out der Anfrage";
            }
            if (s.equals("Conflict")) {
                return "Konflikt";
            }
            if (s.equals("Gone")) {
                return "Ist weg";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Konditionaler Abfrage gescheitert";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Abfrage ist zu gro\u00df";
            }
            if (s.equals("Request-URI Too Long")) {
                return "URI-Abfrage zu lang";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Nicht unterst\u00fctzter Mediatyp";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Nicht unterst\u00fctzter URI Modell";
            }
            if (s.equals("Bad Extension")) {
                return "Fehlerhaftes Ausweitung";
            }
            if (s.equals("Extension Required")) {
                return "Ausweitung n\u00f6tig";
            }
            if (s.equals("Interval Too Brief")) {
                return "Bereich ist zu kurz";
            }
            if (s.equals("Unresolvable destination")) {
                return "Unauffindiger Destination";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Einweilen nicht verf\u00fcgbar";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "Anruftransaction existiert nicht";
            }
            if (s.equals("Loop Detected")) {
                return "Endloser Schleife bemerkt";
            }
            if (s.equals("Too Many Hops")) {
                return "Zu viele Spr\u00fcnge";
            }
            if (s.equals("Address Incomplete")) {
                return "Addresse nicht komplett";
            }
            if (s.equals("Ambiguous")) {
                return "Vieldeutig";
            }
            if (s.equals("Busy Here")) {
                return "Besetzt hier";
            }
            if (s.equals("Request Terminated")) {
                return "Anfrage beendet";
            }
            if (s.equals("Not Acceptable Here")) {
                return "Nicht akkzeptabel hier";
            }
            if (s.equals("Bad Event")) {
                return "Schlechter Ereignis";
            }
            if (s.equals("Request Pending")) {
                return "Anfrage h\u00e4ngig";
            }
            if (s.equals("Undecipherable")) {
                return "Nicht entzifferbar";
            }
            if (s.equals("Server Internal Error")) {
                return "Interner Serverfehler";
            }
            if (s.equals("Not Implemented")) {
                return "Nicht eingebaut";
            }
            if (s.equals("Bad Gateway")) {
                return "Schlecter schnittstelle";
            }
            if (s.equals("Service Unavailable")) {
                return "Dienst nicht erreichbar";
            }
            if (s.equals("Server Time-out")) {
                return "Server Time-out";
            }
            if (s.equals("Version Not Supported")) {
                return "Version nicht unterst\u00fctzt";
            }
            if (s.equals("Message Too Large")) {
                return "Nachricht zu gro\u00df";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Unm\u00f6glich Antwort sicher zu senden";
            }
            if (s.equals("Requires congestion management")) {
                return "Ben\u00f6tigt Blokierungmanagement";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Es w\u00fcrde Fragmentation verursachen";
            }
            if (s.equals("Busy Everywhere")) {
                return "Alle besetzt";
            }
            if (s.equals("Decline")) {
                return "Nachlassen";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "Es existiert niergentwo";
            }
            if (s.equals("Not Acceptable")) {
                return "Unakkzeptabel";
            }
            if (s.equals("Cancel")) {
                return "Abbrechen";
            }
            if (s.equals("Your authentication username")) {
                return "Ihre authentication Benutzername";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Mikrophon";
            }
            if (s.equals("Change microphone volume")) {
                return "Mikrophonvolumen ver\u00e4ndern";
            }
            if (s.equals("Spk")) {
                return "Spk";
            }
            if (s.equals("Speaker")) {
                return "Lautsprecher";
            }
            if (s.equals("Change speaker volume")) {
                return "Lautsprechervolumen ver\u00e4ndern";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Anruf beginnen oder abbrechen";
            }
            if (s.equals("Send instant message")) {
                return "Nachricht senden";
            }
            if (s.equals("Logs")) {
                return "Protokolldateien";
            }
            if (s.equals("Disconnect current call")) {
                return "Aktueller Anruf abbrechen";
            }
            if (s.equals("Test")) {
                return "Test";
            }
            if (s.equals("Audio")) {
                return "Audio";
            }
            if (s.equals("Select audio devices")) {
                return "W\u00e4hlen sie die audio Instrumente";
            }
            if (s.equals("Register to this SIP server")) {
                return "Registrieren sich zu diesem SIP Server";
            }
            if (s.equals("Phone")) {
                return "Telefon";
            }
            if (s.equals("Line")) {
                return "Linie";
            }
            if (s.equals("Phone line")) {
                return "Telefonlinie";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Audio deaktivieren/aktivieren";
            }
            if (s.equals("Hold")) {
                return "Halten";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Aktueller Anruf halten/neu anrufen";
            }
            if (s.equals("Trans")) {
                return "Trans";
            }
            if (s.equals("Call transfer")) {
                return "Anruf \u00dcbergabe";
            }
            if (s.equals("Recall last number")) {
                return "Letzten Nummer neu anrufen";
            }
            if (s.equals("Send text message")) {
                return "Text Nachricht senden";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Speichere meine Einstellungen lokal, auf dieser Computer";
            }
            if (s.equals("Register")) {
                return "Registrieren";
            }
            if (s.equals("Deletable")) {
                return "L\u00f6schbar";
            }
            if (s.equals("Authenticated successfully")) {
                return "Erfolgreich authentifiziert";
            }
            if (s.equals("Incoming")) {
                return "Ankommend";
            }
            if (s.equals("Setup")) {
                return "Einstellung";
            }
            if (s.equals("My PhoneNumber")) {
                return "Mein Telefonnummer";
            }
            if (s.equals("Save")) {
                return "Speichern";
            }
            if (s.equals("Back")) {
                return "Zur\u00fcck";
            }
            if (s.equals("Call number")) {
                return "Angerufenes Nummer";
            }
            if (s.equals("Phone number to call")) {
                return "Anrufender Telefonnummer";
            }
            if (s.equals("Exit")) {
                return "Ausgang";
            }
            if (s.equals("Call")) {
                return "Anruf";
            }
            if (s.equals("Callback")) {
                return "Callback";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "Nummer A";
            }
            if (s.equals("B Number")) {
                return "Nummer B";
            }
            if (s.equals("Initiate Call")) {
                return "Anruf inizialisieren";
            }
            if (s.equals("Cancel")) {
                return "Abbrechen";
            }
            if (s.equals("SMS sender")) {
                return "SMS sender";
            }
            if (s.equals("Phone number")) {
                return "Telefonnummer";
            }
            if (s.equals("SMS message")) {
                return "SMS Nachrich";
            }
            if (s.equals("Send")) {
                return "Senden";
            }
            if (s.equals("Back")) {
                return "Zur\u00fcck";
            }
            if (s.equals("Config saved")) {
                return "Einstellungen gespeichert";
            }
            if (s.equals("cannot save config")) {
                return "Unf\u00e4hig Enstellungen zu speichern";
            }
            if (s.equals("Callback")) {
                return "Callback";
            }
            if (s.equals("invalid username")) {
                return "ung\u00fcltiger Benutzername";
            }
            if (s.equals("invalid password")) {
                return "ung\u00fcltiger Passwort";
            }
            if (s.equals("invalid phonenumber")) {
                return "ung\u00fcltigher Telefonnummer";
            }
            if (s.equals("invalid number")) {
                return "ung\u00fcltiger Nummer";
            }
            if (s.equals("Callback initiated")) {
                return "Callback initialisiert";
            }
            if (s.equals("Failed")) {
                return "Gescheitert";
            }
            if (s.equals("Succeed")) {
                return "Gelungen";
            }
            if (s.equals("Check credit")) {
                return "Kredit pr\u00fcfen";
            }
            if (s.equals("ERROR")) {
                return "Fehler";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Call")) {
                return "Anruf";
            }
            if (s.equals("Too many requests")) {
                return "Zu viele Anfragen";
            }
            if (s.equals("Too many wrong requests")) {
                return "Zu viele schlechte Anfragen";
            }
            if (s.equals("no answer")) {
                return "Keine Antwort";
            }
            if (s.equals("My balance")) {
                return "Mein Bilanz";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "commonTranslateInner", ex);
        }
        return s;
    }
    
    public String try(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Usu\u00e1rio";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Send Chat")) {
                return "Enviar Bate-papo";
            }
            if (s.equals("Destination")) {
                return "Destino";
            }
            if (s.equals("Provider")) {
                return "Provedor";
            }
            if (s.equals("Password")) {
                return "Senha";
            }
            if (s.equals("Save Settings")) {
                return "Salvar Configura\u00e7\u00f5es";
            }
            if (s.equals("Connect")) {
                return "Conectar";
            }
            if (s.equals("Hangup")) {
                return "Desligar";
            }
            if (s.equals("Reject")) {
                return "Rejeitar";
            }
            if (s.equals("Accept")) {
                return "Aceitar";
            }
            if (s.equals("Call")) {
                return "Chamar";
            }
            if (s.equals("Chat")) {
                return "Bate-papo";
            }
            if (s.equals("Mute")) {
                return "Mudo";
            }
            if (s.equals("Transf")) {
                return "Transf";
            }
            if (s.equals("Redial")) {
                return "Rediscagem";
            }
            if (s.equals("Send to")) {
                return "Enviar para";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Not Initialized")) {
                return "N\u00e3o Inicializado";
            }
            if (s.equals("Register Failed")) {
                return "Registro Falhou";
            }
            if (s.equals("Registered")) {
                return "Registrado";
            }
            if (s.equals("Call Finished")) {
                return "Chamada Terminou";
            }
            if (s.equals("Call Initiated")) {
                return "Chamada Iniciada";
            }
            if (s.equals("Initializing")) {
                return "Inicializando";
            }
            if (s.equals("Initializing...")) {
                return "Inicializando...";
            }
            if (s.equals("Registering")) {
                return "Registrando";
            }
            if (s.equals("Calling")) {
                return "Chamando";
            }
            if (s.equals("In Call")) {
                return "Em Chamada";
            }
            if (s.equals("Incoming call")) {
                return "Recebendo Chamada";
            }
            if (s.equals("Incoming call...")) {
                return "Recebendo Chamada...";
            }
            if (s.equals("Incoming call from")) {
                return "Recebendo Chamada de";
            }
            if (s.equals("Unknown")) {
                return "Desconhecido";
            }
            if (s.equals("Init")) {
                return "Inic";
            }
            if (s.equals("Ready")) {
                return "Pronto";
            }
            if (s.equals("Ready.")) {
                return "Pronto.";
            }
            if (s.equals("Outband")) {
                return "Fora de Banda";
            }
            if (s.equals("SignIn")) {
                return "Entrar";
            }
            if (s.equals("Subscribe")) {
                return "Inscrever";
            }
            if (s.equals("Setup")) {
                return "Instala\u00e7\u00e3o";
            }
            if (s.equals("CallProgress")) {
                return "Chamada em Progresso";
            }
            if (s.equals("Routed")) {
                return "Encaminhada";
            }
            if (s.equals("Ringing")) {
                return "Tocando";
            }
            if (s.equals("CallInitiated")) {
                return "Chamada Inicializada";
            }
            if (s.equals("CallStarted")) {
                return "Chamada Iniciada";
            }
            if (s.equals("Midcall")) {
                return "Midcall";
            }
            if (s.equals("CallFinishing")) {
                return "Chamada Finalizando";
            }
            if (s.equals("CallFinished")) {
                return "Chamada Finalizada";
            }
            if (s.equals("Error")) {
                return "Erro";
            }
            if (s.equals("Warning")) {
                return "Aten\u00e7\u00e3o";
            }
            if (s.equals("Trying")) {
                return "Tentando";
            }
            if (s.equals("Ringing")) {
                return "Tocando";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "Chamada sendo Encaminhada";
            }
            if (s.equals("Queued")) {
                return "Chamada em Espera";
            }
            if (s.equals("Session Progress")) {
                return "Sess\u00e3o em Progresso";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Accepted")) {
                return "Aceito";
            }
            if (s.equals("Multiple Choices")) {
                return "M\u00faltipla Escolha";
            }
            if (s.equals("Moved Permanently")) {
                return "Movido Permanentemente";
            }
            if (s.equals("Moved Temporarily")) {
                return "Movido Temporariamente";
            }
            if (s.equals("Use Proxy")) {
                return "Use Proxy";
            }
            if (s.equals("Alternative Service")) {
                return "Servi\u00e7o Alternativo";
            }
            if (s.equals("Bad Request")) {
                return "Requisi\u00e7\u00e3o Errada";
            }
            if (s.equals("Unauthorized")) {
                return "N\u00e3o Autorizado";
            }
            if (s.equals("Payment Required")) {
                return "Necessita Pagamento";
            }
            if (s.equals("Forbidden")) {
                return "Proibida";
            }
            if (s.equals("Not Found")) {
                return "N\u00e3o Encontrado";
            }
            if (s.equals("Method Not Allowed")) {
                return "M\u00e9todo N\u00e3o Permitido";
            }
            if (s.equals("Not Acceptable")) {
                return "N\u00e3o Aceit\u00e1vel";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Requer Autentica\u00e7\u00e3o Proxy";
            }
            if (s.equals("Request Timeout")) {
                return "Tempo Esgotado da Requisi\u00e7\u00e3o";
            }
            if (s.equals("Conflict")) {
                return "Conflito";
            }
            if (s.equals("Gone")) {
                return "Saiu";
            }
            if (s.equals("Conditional Request Failed")) {
                return "Requisi\u00e7\u00e3o Condicional Falhou";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Requisi\u00e7\u00e3o de Dados Muito Grande";
            }
            if (s.equals("Request-URI Too Long")) {
                return "Requisi\u00e7\u00e3o de URI Muito Grande";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Tipo de M\u00eddia n\u00e3o Suportada";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Esquema URI n\u00e3o Suportado";
            }
            if (s.equals("Bad Extension")) {
                return "Extens\u00e3o Ruim";
            }
            if (s.equals("Extension Required")) {
                return "Requer Extens\u00e3o";
            }
            if (s.equals("Interval Too Brief")) {
                return "Intervalo muito Curto";
            }
            if (s.equals("Unresolvable destination")) {
                return "Destino n\u00e3o Resolv\u00edvel";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Temporariamente Indispon\u00edvel";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "Chamada Transa\u00e7\u00e3o n\u00e3o Existe";
            }
            if (s.equals("Loop Detected")) {
                return "Loop Detectado";
            }
            if (s.equals("Too Many Hops")) {
                return "Muitos Saltos";
            }
            if (s.equals("Address Incomplete")) {
                return "Endere\u00e7o Incompleto";
            }
            if (s.equals("Ambiguous")) {
                return "Amb\u00edguo";
            }
            if (s.equals("Busy Here")) {
                return "Ocupado Aqui";
            }
            if (s.equals("Request Terminated")) {
                return "Requisi\u00e7\u00e3o Terminada";
            }
            if (s.equals("Not Acceptable Here")) {
                return "N\u00e3o Aceito Aqui";
            }
            if (s.equals("Bad Event")) {
                return "Evento Ruim";
            }
            if (s.equals("Request Pending")) {
                return "Requisi\u00e7\u00e3o Pendente";
            }
            if (s.equals("Undecipherable")) {
                return "Indecifr\u00e1vel";
            }
            if (s.equals("Server Internal Error")) {
                return "Erro Interno do Servidor";
            }
            if (s.equals("Not Implemented")) {
                return "N\u00e3o Implementado";
            }
            if (s.equals("Bad Gateway")) {
                return "Gateway Ruim";
            }
            if (s.equals("Service Unavailable")) {
                return "Servi\u00e7o Indispon\u00edvel";
            }
            if (s.equals("Server Time-out")) {
                return "Tempo Esgotado do Servidor";
            }
            if (s.equals("Version Not Supported")) {
                return "Vers\u00e3o N\u00e3o Suportada";
            }
            if (s.equals("Message Too Large")) {
                return "Mensagem Muito Grande";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Resposta n\u00e3o pode ser Enviada com Seguran\u00e7a";
            }
            if (s.equals("Requires congestion management")) {
                return "Requer Gest\u00e3o dos Congestionamentos";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Induziria Fragmenta\u00e7\u00e3o";
            }
            if (s.equals("Busy Everywhere")) {
                return "Ocupado em Todo Lugar";
            }
            if (s.equals("Decline")) {
                return "Rejeitar";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "N\u00e3o existe em Lugar Nenhum";
            }
            if (s.equals("Not Acceptable")) {
                return "N\u00e3o Permitido";
            }
            if (s.equals("Cancel")) {
                return "Cancelar";
            }
            if (s.equals("Your authentication username")) {
                return "Seu Usu\u00e1rio de Autentica\u00e7\u00e3o";
            }
            if (s.equals("Mic")) {
                return "Mic";
            }
            if (s.equals("Microphone")) {
                return "Microfone";
            }
            if (s.equals("Change microphone volume")) {
                return "Alterar o Volume do Microfone";
            }
            if (s.equals("Spk")) {
                return "Som";
            }
            if (s.equals("Speaker")) {
                return "Alto-falante";
            }
            if (s.equals("Change speaker volume")) {
                return "Alterar o Volume do Alto-falante";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "Iniciar Chamada de Voz ou Desligar";
            }
            if (s.equals("Send instant message")) {
                return "Enviar Mensagem Instant\u00e2nea";
            }
            if (s.equals("Logs")) {
                return "Hist\u00f3ricos";
            }
            if (s.equals("Disconnect current call")) {
                return "Desligar a Chamada Atual";
            }
            if (s.equals("Test")) {
                return "Teste";
            }
            if (s.equals("Audio")) {
                return "\u00c1udio";
            }
            if (s.equals("Select audio devices")) {
                return "Selecione os Dispositivos de \u00c1udio";
            }
            if (s.equals("Register to this SIP server")) {
                return "Registre no Servidor SIP";
            }
            if (s.equals("Phone")) {
                return "Telefone";
            }
            if (s.equals("Line")) {
                return "Linha";
            }
            if (s.equals("Phone line")) {
                return "Linha de Telefone";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Desativar/Ativar \u00c1udio";
            }
            if (s.equals("Hold")) {
                return "Segurar";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Segurar/Recarregar Chamada Atual";
            }
            if (s.equals("Trans")) {
                return "Trans";
            }
            if (s.equals("Call transfer")) {
                return "Transfer\u00eancia de Chamadas";
            }
            if (s.equals("Recall last number")) {
                return "Chamar o \u00daltimo N\u00famero";
            }
            if (s.equals("Send text message")) {
                return "Enviar Mensagem de Texto";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Salvar minhas configura\u00e7\u00f5es neste Computador Local (inclusive a senha!)";
            }
            if (s.equals("Register")) {
                return "Registrar";
            }
            if (s.equals("Deletable")) {
                return "Apag\u00e1vel";
            }
            if (s.equals("Authenticated successfully")) {
                return "Autenticado com Sucesso";
            }
            if (s.equals("Incoming")) {
                return "Entrada";
            }
            if (s.equals("Setup")) {
                return "Instala\u00e7\u00e3o";
            }
            if (s.equals("My PhoneNumber")) {
                return "Meu N\u00famero de Telefone";
            }
            if (s.equals("Save")) {
                return "Salvar";
            }
            if (s.equals("Back")) {
                return "Voltar";
            }
            if (s.equals("Call number")) {
                return "N\u00famero de Chamada";
            }
            if (s.equals("Phone number  to call")) {
                return "N\u00famero de Telefone para Chamar";
            }
            if (s.equals("Exit")) {
                return "Sair";
            }
            if (s.equals("Call")) {
                return "Chamar";
            }
            if (s.equals("Callback")) {
                return "Chamada de Volta";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "N\u00famero A";
            }
            if (s.equals("B Number")) {
                return "N\u00famero B";
            }
            if (s.equals("Initiate Call")) {
                return "Iniciar Chamada";
            }
            if (s.equals("Cancel")) {
                return "Cancelar";
            }
            if (s.equals("SMS sender")) {
                return "Remetente do SMS";
            }
            if (s.equals("Phone number")) {
                return "N\u00famero do Telefone";
            }
            if (s.equals("SMS message")) {
                return "Mensagem SMS";
            }
            if (s.equals("Send")) {
                return "Enviar";
            }
            if (s.equals("Back")) {
                return "Voltar";
            }
            if (s.equals("Config saved")) {
                return "Configura\u00e7\u00e3o Salva";
            }
            if (s.equals("cannot save config")) {
                return "N\u00e3o pode Salvar Configura\u00e7\u00e3o";
            }
            if (s.equals("Callback")) {
                return "Chamada de Volta";
            }
            if (s.equals("invalid username")) {
                return "Usu\u00e1rio Inv\u00e1lido";
            }
            if (s.equals("invalid password")) {
                return "Senha Inv\u00e1lida";
            }
            if (s.equals("invalid phonenumber")) {
                return "N\u00famero do Telefone Inv\u00e1lido";
            }
            if (s.equals("invalid number")) {
                return "N\u00famero Inv\u00e1lido";
            }
            if (s.equals("Callback initiated")) {
                return "Chamada de Volta Iniciada";
            }
            if (s.equals("Failed")) {
                return "Falha";
            }
            if (s.equals("Succeed")) {
                return "Sucesso";
            }
            if (s.equals("Check credit")) {
                return "Verifica\u00e7\u00e3o de Cr\u00e9dito";
            }
            if (s.equals("ERROR")) {
                return "ERRO";
            }
            if (s.equals("OK")) {
                return "OK";
            }
            if (s.equals("Call")) {
                return "Chamar";
            }
            if (s.equals("Too many requests")) {
                return "Muitas Requisi\u00e7\u00f5es";
            }
            if (s.equals("Too many wrong requests")) {
                return "Muitos Requisi\u00e7\u00f5es Erradas";
            }
            if (s.equals("no answer")) {
                return "Sem Resposta";
            }
            if (s.equals("My balance")) {
                return "Meu Balanceamento";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "TranslateInnerTr", ex);
        }
        return s;
    }
    
    public String do(final String s) {
        if (this.a.ad < 1) {
            return s;
        }
        try {
            if (s == null) {
                return null;
            }
            if (s.length() < 1) {
                return "";
            }
            if (s.equals("Username")) {
                return "Kullan\u0131c\u0131 Ad\u0131";
            }
            if (s.equals("Send")) {
                return "G\u00f6nder";
            }
            if (s.equals("Send Chat")) {
                return "Mesaj G\u00f6nder";
            }
            if (s.equals("Destination")) {
                return "Hedef";
            }
            if (s.equals("Provider")) {
                return "Servis Sa\u011flay\u0131c\u0131";
            }
            if (s.equals("Password")) {
                return "\u015eifre";
            }
            if (s.equals("Save Settings")) {
                return "Ayarlar\u0131 Kaydet";
            }
            if (s.equals("Connect")) {
                return "Ba\u011flan";
            }
            if (s.equals("Hangup")) {
                return "Kapat";
            }
            if (s.equals("Reject")) {
                return "Reddet";
            }
            if (s.equals("Accept")) {
                return "Kabul et";
            }
            if (s.equals("Call")) {
                return "\u00c7a\u011fr\u0131";
            }
            if (s.equals("Chat")) {
                return "Mesajla\u015fma";
            }
            if (s.equals("Mute")) {
                return "Sessiz";
            }
            if (s.equals("Transf")) {
                return "Aktar";
            }
            if (s.equals("Redial")) {
                return "Yeniden ara";
            }
            if (s.equals("Send to")) {
                return "G\u00f6nder";
            }
            if (s.equals("Send")) {
                return "G\u00f6nder";
            }
            if (s.equals("Not Initialized")) {
                return "Haz\u0131r de\u011fil";
            }
            if (s.equals("Register Failed")) {
                return "Kay\u0131t ba\u015far\u0131s\u0131z";
            }
            if (s.equals("Registered")) {
                return "Kay\u0131t Ba\u015far\u0131l\u0131";
            }
            if (s.equals("Call Finished")) {
                return "\u00c7a\u011fr\u0131 sonland\u0131";
            }
            if (s.equals("Call Initiated")) {
                return "\u00c7a\u011fr\u0131 Ba\u015flat\u0131ld\u0131";
            }
            if (s.equals("Initializing")) {
                return "Haz\u0131rlan\u0131yor";
            }
            if (s.equals("Initializing...")) {
                return "Haz\u0131rlan\u0131yor...";
            }
            if (s.equals("Registering")) {
                return "Kay\u0131t olunuyor";
            }
            if (s.equals("Calling")) {
                return "Aran\u0131yor";
            }
            if (s.equals("Calling...")) {
                return "Aran\u0131yor";
            }
            if (s.equals("InProgress...")) {
                return "Aran\u0131yor...";
            }
            if (s.equals("In Call")) {
                return "G\u00f6r\u00fc\u015fmede";
            }
            if (s.equals("Incoming call")) {
                return "Gelen \u00e7a\u011fr\u0131";
            }
            if (s.equals("Incoming call...")) {
                return "Gelen \u00e7a\u011fr\u0131...";
            }
            if (s.equals("Incoming call from")) {
                return "Gelen \u00e7a\u011fr\u0131";
            }
            if (s.equals("Unknown")) {
                return "Bilinmiyor";
            }
            if (s.equals("Init")) {
                return "Haz\u0131rla";
            }
            if (s.equals("Ready")) {
                return "Haz\u0131r";
            }
            if (s.equals("Ready.")) {
                return "Haz\u0131r.";
            }
            if (s.equals("Outband")) {
                return "Outband";
            }
            if (s.equals("SignIn")) {
                return "Kay\u0131t ol";
            }
            if (s.equals("Subscribe")) {
                return "\u00dcye ol";
            }
            if (s.equals("Setup")) {
                return "Ayarla";
            }
            if (s.equals("CallProgress")) {
                return "\u00c7a\u011fr\u0131 durumu";
            }
            if (s.equals("Routed")) {
                return "Y\u00f6nlendi";
            }
            if (s.equals("Ringing")) {
                return "\u00c7al\u0131yor";
            }
            if (s.equals("CallInitiated")) {
                return "\u00c7a\u011fr\u0131 ba\u015flat\u0131ld\u0131";
            }
            if (s.equals("CallStarted")) {
                return "\u00c7a\u011fr\u0131 ba\u015flad\u0131";
            }
            if (s.equals("Midcall")) {
                return "Midcall";
            }
            if (s.equals("CallFinishing")) {
                return "\u00c7a\u011fr\u0131 sonlan\u0131yor";
            }
            if (s.equals("CallFinished")) {
                return "\u00c7a\u011fr\u0131 sonland\u0131";
            }
            if (s.equals("Error")) {
                return "Hata";
            }
            if (s.equals("Warning")) {
                return "Uyar\u0131";
            }
            if (s.equals("Trying")) {
                return "Deniyor";
            }
            if (s.equals("Ringing")) {
                return "\u00c7al\u0131yor";
            }
            if (s.equals("Call Is Being Forwarded")) {
                return "\u00c7a\u011fr\u0131 y\u00f6nlendirildi";
            }
            if (s.equals("Queued")) {
                return "S\u0131rada";
            }
            if (s.equals("Session Progress")) {
                return "\u0130\u015fleniyor";
            }
            if (s.equals("OK")) {
                return "Tamam";
            }
            if (s.equals("Accepted")) {
                return "Kabul edildi";
            }
            if (s.equals("Multiple Choices")) {
                return "Birden fazla se\u00e7enek";
            }
            if (s.equals("Moved Permanently")) {
                return "Kal\u0131c\u0131 olarak ta\u015f\u0131nd\u0131";
            }
            if (s.equals("Moved Temporarily")) {
                return "Ge\u00e7ici olarak ta\u015f\u0131nd\u0131";
            }
            if (s.equals("Use Proxy")) {
                return "Proxy kullan";
            }
            if (s.equals("Alternative Service")) {
                return "Alternatif servis";
            }
            if (s.equals("Bad Request")) {
                return "Ge\u00e7ersiz istek";
            }
            if (s.equals("Unauthorized")) {
                return "Yetkilendirilmemi\u015f";
            }
            if (s.equals("Payment Required")) {
                return "\u00d6deme gerekli";
            }
            if (s.equals("Forbidden")) {
                return "Yasakl\u0131";
            }
            if (s.equals("Not Found")) {
                return "Bulunamad\u0131";
            }
            if (s.equals("Method Not Allowed")) {
                return "\u0130zin verilemiyor";
            }
            if (s.equals("Not Acceptable")) {
                return "Kabul edilmiyor";
            }
            if (s.equals("Proxy Authentication Required")) {
                return "Kimlik do\u011frulama gerekli";
            }
            if (s.equals("Request Timeout")) {
                return "\u0130stek zaman a\u015f\u0131m\u0131";
            }
            if (s.equals("Conflict")) {
                return "\u00c7ak\u0131\u015fma";
            }
            if (s.equals("Gone")) {
                return "Gitti";
            }
            if (s.equals("Conditional Request Failed")) {
                return "\u0130stek ba\u015far\u0131s\u0131z";
            }
            if (s.equals("Request Entity Too Large")) {
                return "Request Entity Too Large";
            }
            if (s.equals("Request-URI Too Long")) {
                return "Request-URI Too Long";
            }
            if (s.equals("Unsupported Media Type")) {
                return "Desteklenmeyen ortam t\u00fcr\u00fc";
            }
            if (s.equals("Unsupported URI Scheme")) {
                return "Desteklenmeyen URI";
            }
            if (s.equals("Bad Extension")) {
                return "Hatal\u0131 dahili";
            }
            if (s.equals("Extension Required")) {
                return "Dahili gerekli";
            }
            if (s.equals("Interval Too Brief")) {
                return "Interval Too Brief";
            }
            if (s.equals("Unresolvable destination")) {
                return "\u00c7\u00f6z\u00fcmlenemeyen hedef";
            }
            if (s.equals("Temporarily Unavailable")) {
                return "Ge\u00e7ici olarak servis d\u0131\u015f\u0131";
            }
            if (s.equals("CallTransaction Does Not Exist")) {
                return "\u00c7a\u011fr\u0131 mevcut de\u011fil";
            }
            if (s.equals("Loop Detected")) {
                return "Loop Detected";
            }
            if (s.equals("Too Many Hops")) {
                return "Too Many Hops";
            }
            if (s.equals("Address Incomplete")) {
                return "Eksik Numara";
            }
            if (s.equals("Ambiguous")) {
                return "Ambiguous";
            }
            if (s.equals("Busy Here")) {
                return "Me\u015fguk";
            }
            if (s.equals("Request Terminated")) {
                return "\u0130stek iptal edildi";
            }
            if (s.equals("Not Acceptable Here")) {
                return "Kabul edilemez";
            }
            if (s.equals("Bad Event")) {
                return "Bad Event";
            }
            if (s.equals("Request Pending")) {
                return "\u0130stek beklemede";
            }
            if (s.equals("Undecipherable")) {
                return "Undecipherable";
            }
            if (s.equals("Server Internal Error")) {
                return "Dahili sunucu hatas\u0131";
            }
            if (s.equals("Not Implemented")) {
                return "Not Implemented";
            }
            if (s.equals("Bad Gateway")) {
                return "Bad Gateway";
            }
            if (s.equals("Service Unavailable")) {
                return "Servis d\u0131\u015f\u0131";
            }
            if (s.equals("Server Time-out")) {
                return "Sunucu zaman a\u015f\u0131m\u0131";
            }
            if (s.equals("Version Not Supported")) {
                return "Desteklenmeyen versiyon";
            }
            if (s.equals("Message Too Large")) {
                return "Mesaj boyutu fazla b\u00fcy\u00fck";
            }
            if (s.equals("Response Cannot Be Sent Safely")) {
                return "Yan\u0131t g\u00fcvenli bir \u015fekilde g\u00f6nderilemez";
            }
            if (s.equals("Requires congestion management")) {
                return "Requires congestion management";
            }
            if (s.equals("Would induce fragmentation")) {
                return "Would induce fragmentation";
            }
            if (s.equals("Busy Everywhere")) {
                return "Busy Everywhere";
            }
            if (s.equals("Decline")) {
                return "Reddedildi";
            }
            if (s.equals("Does Not Exist Anywhere")) {
                return "Does Not Exist Anywhere";
            }
            if (s.equals("Not Acceptable")) {
                return "Kabul edilemez";
            }
            if (s.equals("Cancel")) {
                return "\u0130ptal";
            }
            if (s.equals("Your authentication username")) {
                return "Kimlik do\u011frulama kullan\u0131c\u0131 ad\u0131";
            }
            if (s.equals("Mic")) {
                return "Mik";
            }
            if (s.equals("Microphone")) {
                return "Mikrofon";
            }
            if (s.equals("Change microphone volume")) {
                return "Mikrofon ses d\u00fczeyini de\u011fi\u015ftir";
            }
            if (s.equals("Spk")) {
                return "Hpr";
            }
            if (s.equals("Speaker")) {
                return "Hoparl\u00f6r";
            }
            if (s.equals("Change speaker volume")) {
                return "Hoparl\u00f6r ses seviyesini de\u011fi\u015ftir";
            }
            if (s.equals("Initiate voice call or hangup")) {
                return "\u00c7a\u011fr\u0131 ba\u015flat veya sonland\u0131r";
            }
            if (s.equals("Send instant message")) {
                return "Mesaj g\u00f6nder";
            }
            if (s.equals("Logs")) {
                return "Kay\u0131tlar";
            }
            if (s.equals("Disconnect current call")) {
                return "Mevcut \u00e7a\u011fr\u0131y\u0131 sonland\u0131r";
            }
            if (s.equals("Test")) {
                return "Test";
            }
            if (s.equals("Audio")) {
                return "Ses";
            }
            if (s.equals("Select audio devices")) {
                return "Ses cihazlar\u0131n\u0131 se\u00e7in";
            }
            if (s.equals("Register to this SIP server")) {
                return "Bu SIP sunucusuna kay\u0131t ol";
            }
            if (s.equals("Phone")) {
                return "Telefon";
            }
            if (s.equals("Line")) {
                return "Hat";
            }
            if (s.equals("Phone line")) {
                return "Telefon hatt\u0131";
            }
            if (s.equals("Disable/Enable audio")) {
                return "Ses A\u00e7/Kapat";
            }
            if (s.equals("Hold")) {
                return "Beklet";
            }
            if (s.equals("Hold/Reload current call")) {
                return "Mevcut \u00e7a\u011fr\u0131y\u0131 Beklet/geri al";
            }
            if (s.equals("Trans")) {
                return "Aktar";
            }
            if (s.equals("Call transfer")) {
                return "\u00c7a\u011fr\u0131 aktar";
            }
            if (s.equals("Recall last number")) {
                return "Son numaray\u0131 geri ara";
            }
            if (s.equals("Send text message")) {
                return "Yaz\u0131l\u0131 mesaj g\u00f6nder";
            }
            if (s.equals("Save my settings on this computer locally (including password!)")) {
                return "Ayarlar\u0131m\u0131 bu bilgisayar \u00fczerinde kaydet (\u015fifreler dahil!)";
            }
            if (s.equals("Register")) {
                return "Kay\u0131t ol";
            }
            if (s.equals("Deletable")) {
                return "Silinebilir";
            }
            if (s.equals("Authenticated successfully")) {
                return "Kimlik do\u011fruland\u0131";
            }
            if (s.equals("Incoming")) {
                return "Gelen";
            }
            if (s.equals("Setup")) {
                return "Ayarla";
            }
            if (s.equals("My PhoneNumber")) {
                return "Telefon numaram";
            }
            if (s.equals("Save")) {
                return "Kaydet";
            }
            if (s.equals("Back")) {
                return "Geri";
            }
            if (s.equals("Call number")) {
                return "Nuamray\u0131 ara";
            }
            if (s.equals("Phone number  to call")) {
                return "Arancak numara";
            }
            if (s.equals("Exit")) {
                return "\u00c7\u0131k\u0131\u015f";
            }
            if (s.equals("Call")) {
                return "Ara";
            }
            if (s.equals("Callback")) {
                return "Geri ara";
            }
            if (s.equals("P2P")) {
                return "P2P";
            }
            if (s.equals("SMS")) {
                return "SMS";
            }
            if (s.equals("A Number")) {
                return "A numaras\u0131";
            }
            if (s.equals("B Number")) {
                return "B numaras\u0131";
            }
            if (s.equals("Initiate Call")) {
                return "\u00c7a\u011fr\u0131 ba\u015flat";
            }
            if (s.equals("Cancel")) {
                return "\u0130ptal";
            }
            if (s.equals("SMS sender")) {
                return "SMS g\u00f6nderen";
            }
            if (s.equals("Phone number")) {
                return "Telefon numaras\u0131";
            }
            if (s.equals("SMS message")) {
                return "SMS mesaj\u0131";
            }
            if (s.equals("Send")) {
                return "G\u00f6nder";
            }
            if (s.equals("Back")) {
                return "Geri";
            }
            if (s.equals("Config saved")) {
                return "Ayarlar kaydedildi";
            }
            if (s.equals("cannot save config")) {
                return "Ayarlar kaydedilemiyor";
            }
            if (s.equals("Callback")) {
                return "Geri ara";
            }
            if (s.equals("invalid username")) {
                return "Hatal\u0131 kullan\u0131c\u0131 ad\u0131";
            }
            if (s.equals("invalid password")) {
                return "Hatal\u0131 \u015fifre";
            }
            if (s.equals("invalid phonenumber")) {
                return "Hatal\u0131 telefon numaras\u0131";
            }
            if (s.equals("invalid number")) {
                return "Hatal\u0131 numara";
            }
            if (s.equals("Callback initiated")) {
                return "Geri arama ba\u015flat\u0131ld\u0131";
            }
            if (s.equals("Failed")) {
                return "Ba\u015far\u0131s\u0131z";
            }
            if (s.equals("Succeed")) {
                return "Ba\u015far\u0131l\u0131";
            }
            if (s.equals("Check credit")) {
                return "Krediyi kontrol et";
            }
            if (s.equals("ERROR")) {
                return "Hata";
            }
            if (s.equals("OK")) {
                return "Tamam";
            }
            if (s.equals("Call")) {
                return "\u00c7a\u011fr\u0131";
            }
            if (s.equals("Too many requests")) {
                return "\u00c7ok fazla istek";
            }
            if (s.equals("Too many wrong requests")) {
                return "\u00c7ok fazla hatal\u0131 istek";
            }
            if (s.equals("no answer")) {
                return "Yan\u0131t yok";
            }
            if (s.equals("My balance")) {
                return "Hesab\u0131m";
            }
        }
        catch (Exception ex) {
            this.a.a(3, "commonTranslateInner", ex);
        }
        return s;
    }
}
