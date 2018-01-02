// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.HashMap;

public final class a
{
    private static HashMap a;
    private static int b;
    private static int c;
    private static int d;
    private static boolean e;
    
    public static void a(final String s) {
        COM.NextBus.AdminMap.a.d = 0;
        COM.NextBus.AdminMap.a.e = false;
        if (s == null) {
            return;
        }
        if (s.equals("fr")) {
            COM.NextBus.AdminMap.a.d = COM.NextBus.AdminMap.a.c;
            COM.NextBus.AdminMap.a.e = true;
        }
    }
    
    private static void a(final String s, final String s2, final String s3) {
        COM.NextBus.AdminMap.a.a.put(s, new String[] { s2, s3 });
    }
    
    public static String b(final String s) {
        if (s == null) {
            return null;
        }
        final String[] array;
        if ((array = COM.NextBus.AdminMap.a.a.get(s)) == null) {
            return s;
        }
        final String s2;
        if ((s2 = array[COM.NextBus.AdminMap.a.d]) == null) {
            return s;
        }
        return s2;
    }
    
    public static boolean a() {
        return COM.NextBus.AdminMap.a.e;
    }
    
    static {
        COM.NextBus.AdminMap.a.a = new HashMap();
        a("Stop", null, "Arr\u00eat");
        a("Predictions", null, "Pr\u00e9visions");
        a("No current prediction", null, "Aucune pr\u00e9vision actuelle");
        a("Last Passing", null, "Dernier passage ");
        a("unavailable", null, "Non disponible");
        a("Vehicle", null, "V\u00e9hicule");
        a("Driver ID", null, "ID du conducteur");
        a("Route", null, "Parcours");
        a("Direction", null, "Direction");
        a("Job", null, "T\u00e2che");
        a("Trip", null, "Voyage");
        a("Next stop", null, "Prochain arr\u00eat");
        a("Not predicted", null, "Pas pr\u00e9dis");
        a("unknown", null, "inconnu");
        a("early", null, "\u00e9 l'avance");
        a("late", null, "en retard");
        a("breakpointing", null, "interruption");
        a("not found", null, "non trouv\u00e9");
        a("Unknown problem when trying to find vehicle.", null, "Le probl\u00e9me inconnu lors de la tentative de localisation d'un v\u00e9hicule.");
        a("It might be that it hasn't reported in a while.", null, "Une explication possible est que le v\u00e9hicule n'a pas fourni de rapport depuis un certain temps.");
        a("Time at stop", null, "Heure \u00e9 l'arr\u00eat");
        a("Time until stop", null, "Temps jusqu'\u00e0 l'arr\u00eat");
        a("Speed", null, "Vitesse");
        a("Headway", null, "Intervalle");
        a("Shared", null, "Partag\u00e9");
        a("Scheduled", null, "Sur l'horaire");
        a("Adherence", null, "Horaire respect\u00e9");
        a("Time since GPS report", null, "Le temps depuis le rapport GPS");
        a("Time since event received", null, "Le temps depuis la r\u00e9ception de l'\u00e9v\u00e9nement");
        a("Virtual vehicle assigned", null, "Le V\u00e9hicule virtuel assign\u00e9");
        a("Silent Alarm", null, "Alarme muette");
        a("Oil Pressure", null, "Pression d'huile");
        a("Coolant Temperature", null, "Temp\u00e9rature d'agent de Refroidissement");
        a("Battery Voltage", null, "Tension de la Batterie");
        a("Map Toolbar for", null, "Barre d'outils de la carte pour");
        a("Show Toolbar", null, "Barre d'outils d'affichage");
        a("Zoom Out", null, "Zoom arri\u00e9re");
        a("Zoom In", null, "Zoom avant");
        a("Select Config", null, "S\u00e9lectionner Config");
        a("Save Config", null, "Sauvegarder Config");
        a("Routes", null, "Parcours");
        a("Vehicles", null, "V\u00e9hicules");
        a("Properties", null, "Propri\u00e9t\u00e9s");
        a("Events", null, "Ev\u00e9nements");
        a("Help", null, "Aide");
        a("default", null, "par d\u00e9faut");
        a("Begin Date", null, "Date de d\u00e9but");
        a("Hour/Minute", null, "Heure/minute");
        a("Rate", null, "Taux");
        a("Status", null, "Statut");
        a("Stopped", null, "Arr\u00eat\u00e9");
        a("Server Initializing", null, "Initialisation du serveur");
        a("Server Busy", null, "Serveur occup\u00e9");
        a("Playing at", null, "Lecture \u00e9");
        a("Paused at", null, "Arr\u00eat\u00e9 \u00e9");
        a("Date", null, "Date");
        a("is in future!", null, "est dans le futur!");
        a("Jan", null, "Jan");
        a("Feb", null, "F\u00e9v");
        a("Mar", null, "Mar");
        a("Apr", null, "Avr");
        a("May", null, "Mai");
        a("Jun", null, "Jun");
        a("Jul", null, "Jui");
        a("Aug", null, "Aug");
        a("Sep", null, "Sep");
        a("Oct", null, "Oct");
        a("Nov", null, "Nov");
        a("Dec", null, "D\u00e9c");
        a("Save Configuration", null, "Sauvegarder la configuration");
        a("Existing Configurations", null, "Configurations existantes");
        a("Configuration Name", null, "Nom de la configuration");
        a("Save Current Config", null, "Sauvegarder la config. actuelle");
        a("Delete", null, "Supprimer");
        a("Routes for", null, "Parcours pour");
        a("Select All", null, "S\u00e9lectionner tout");
        a("De-Select All", null, "Dess\u00e9lectionner tout");
        a("Zoom to Routes", null, "Zoom sur les parcours");
        a("Close", null, "Fermer");
        a("Vehicle Inspector", null, "Inspecteur de v\u00e9hicule");
        a("Select a Vehicle", null, "S\u00e9lectionner un v\u00e9hicule");
        a("Vehicle ID", null, "ID du v\u00e9hicule");
        a("no match", null, "aucun assortiment");
        a("Perform Action on Selected Vehicle", null, "Performer l'action sur le v\u00e9hicule s\u00e9lectionn\u00e9");
        a("Find Vehicle", null, "Trouver un v\u00e9hicule");
        a("Zoom to Vehicle", null, "Zoom sur le v\u00e9hicule");
        a("Zoom to All Vehicles", null, "Zoom sur TOUS les v\u00e9hicules");
        a("Show Trails", null, "Afficher les trajets");
        a("Clear Trails", null, "Effacer les trajets");
        a("Change Route/Run of Selected Vehicle", null, "Modifir le parcours/trajet du v\u00e9hicule s\u00e9lectionn\u00e9");
        a("New Route", null, "Nouveau parcours");
        a("no route", null, "aucun parcours");
        a("New Job", null, "Nouvelle t\u00e2che");
        a("no job", null, "aucune t\u00e2che");
        a("Specify New Job", null, "Sp\u00e9cifier la nouvelle t\u00e2che");
        a("Notice", null, "Remarque");
        a("Properties", null, "Propri\u00e9t\u00e9s");
        a("Vehicles to Display", null, "V\u00e9hicules \u00e9 afficher");
        a("Show No-Job (grey) Vehicles", null, "Afficher les v\u00e9hicules sans t\u00e2che (gris)");
        a("Show Off-Job (white) Vehicles", null, "Afficher les v\u00e9hicules qui n'ont plus de t\u00e2che (blancs)");
        a("Show Unassigned Vehicles in Yards", null, "Afficher les v\u00e9hicules non assign\u00e9s dans les cours");
        a("Show Stale Vehicles", null, "Afficher les v\u00e9hicules \u00e9chauff\u00e9s");
        a("Vehicle Labels", null, "\u00c9tiquettes de v\u00e9hicule Labels");
        a("Label Vehicles with", null, "\u00c9tiqueter les v\u00e9hicules avec");
        a("No Label", null, "Aucune \u00e9tiquette");
        a("Route&Id Only", null, "Parcours et ID seul.");
        a("Add Adherence", null, "Ajouter le respect d'horaire");
        a("Add Headway", null, "Ajouter une intervalle");
        a("Show Vehicles Colored by", null, "Afficher les v\u00e9hicules color\u00e9 en");
        a("Map Display", null, "Affichage de la carte");
        a("Show Street Map", null, "Afficher la carte routi\u00e9re Map");
        a("Show Hidden Stops", null, "Afficher les arr\u00eats cach\u00e9s");
        a("Show Time in Clock Time Instead of Countdown", null, "Affichage horaire des pr\u00e9visions au lieu d'un compte \u00e9 rebours");
        a("Snap Vehicles to Route", null, "Attacher les v\u00e9hicules au parcours");
        a("Show Latitude/Longitude for Mouse", null, "Afficher la latitude/longitude pour la souris");
        a("Vehicle Details in Rollover Window", null, "D\u00e9tails du v\u00e9hicule dans une fen\u00e9tre d\u00e9roulante");
        a("Show Latitude/Longitude", null, "Afficher la latitude/longitude");
        a("Show Time the GPS Report Received", null, "Afficher heure \u00e9 laquelle le rapport GPS a \u00e9t\u00e9 re\u00e9u");
        a("Requesting list of", null, "Demander la liste des");
        a("from server.", null, "\u00e0 partir du serveur");
        a("Requesting information for all agencies.", null, "Demander de l'information pour tous les organismes.");
        a("Requesting information for agency", null, "Demander de l'information pour l'agence");
        a("Requesting information for all", null, "Demander de l'information pour tous");
        a("Loading default configuration.", null, "Chargement de la configuration par d\u00e9faut.");
        a("Loading user configuration.", null, "Chargement de configuration utilisateur.");
        a("Stopping connection retries.", null, "Tentatives de connexion d'arr\u00eat.");
        a("Applet is out of date.", null, "Applet est obsol\u00e8te.");
        a("Will retry after", null, "Nouvelle tentative apr\u00e8s");
        a("Version", null, "Version");
        a("Copyright", null, "Droits d'auteur");
        a("Send comments and questions to", null, "Acheminer les commentaires et les questions \u00e9");
        a("Replay Map", null, "R\u00e9p\u00e9tition Carte");
        a("Agency Map", null, "Carte d'agence");
        a("Map", null, "Carte");
        a("commonHelpText", "STOP INFORMATION\n\nVehicle routes and vehicles are displayed over a street map image. Stops are drawn along the routes as small square boxes.\n\nMove the mouse over a stop.  A pop-up window will appear showing information about the stop, including the predictions for when vehicles will arrive at that stop and the headway based on the predicted arrival of the next vehicle.  The color of each stop's entry corresponds to the color of the route.\n\nVEHICLE INFORMATION\n\nVehicles are drawn with pointed ends to indicate the direction of travel.  If the vehicle is on-route, the drawn direction is determined from the route.  If the vehicle is on-route but stopped, the arrowhead in the center of the vehicle becomes a bar.  If the vehicle is off-route and moving, the drawn direction is determined from the gps report.  If the vehicle is off-route and stopped, it will be represented as a circle.\n\nNear each vehicle is an associated label, connected to it by a black line. Move the mouse over one of the vehicle labels. Just as with stops, a pop-up window will appear showing information about the vehicle.  One of those values is headway which is based on the time gap between the arrivals at the last stop of this vehicle and the previous.  Additional variations of headway are:\n * Shared headway - Time spread between vehicles that share the same path and destination but are assigned to different routes\n * Scheduled headway - Time spread between vehicles based on the agency's schedule\n\nAdherence is the vehicles preformance compared to the schedule. Positive values are early, negative values are late.  The color of each vehicle's pop-up entry corresponds to the color of the route.  If the vehicle is off route but assigned, its color is white If the vehicle is unassigned, its color is gray.\n\nZOOMING AND PANNING\n\nIn the upper-left-hand corner of the map are two buttons labeled \"Zoom Out\" and \"Zoom In\".  Click on the \"Zoom In\" button. The routes and vehicles will immediately redraw. Depending on the speed of your connection, after a short period of time, a new higher resolution street map will appear.\n\nClick and drag starting anywhere on the map to get live scrolling.\n\nROUTE SELECTION\n\nClick on the button labeled \"Routes...\".  The route names and colors are shown in a dialog, each with a checkbox.  Check or uncheck the boxes to change the routes shown. If the new set of routes covers a different geographical area, to better see that area, click the button labeled \"Zoom to Selected Routes\".  This recenters the map and changes the zoom level as necessary to fit the selected routes.\n\n", "INFORMATION SUR LES ARR\u00caTS\n\nLes trajets de v\u00e9hicules et les v\u00e9hicules sont affich\u00e9s sur une image du plan des rues. Les arr\u00eats sont dessin\u00e9s le long des trajets sous forme de petites bo\u00e9tes carr\u00e9es.\n\nD\u00e9placez la souris au-dessus d'un arr\u00eat.  Une fen\u00e9tre contextuelle s'affiche contenant l'information sur l'arr\u00eat, y compris les pr\u00e9visions concernant l'arriv\u00e9e des v\u00e9hicules \u00e9 cet arr\u00eat et l'intervalle bas\u00e9e sur la pr\u00e9diction d'arriv\u00e9e du prochain v\u00e9hicule.  La couleur de l'entr\u00e9e de chaque arr\u00eat correspond \u00e9 la couleur du trajet.\n\nINFORMATION DE VEHICULE\n\nLes v\u00e9hicules sont dessin\u00e9s avec des extr\u00e9mit\u00e9s en pointe pour indiquer la direction du d\u00e9placement.  Si le v\u00e9hicule est sur le trajet, la direction dessin\u00e9e est d\u00e9termin\u00e9e par le trajet.  Si le v\u00e9hicule est sur le trajet mais arr\u00eat\u00e9, la pointe de fl\u00e9che dans le centre du v\u00e9hicule devient une barre.  Si le v\u00e9hicule est hors trajet et en d\u00e9placement, la direction\tdessin\u00e9e est d\u00e9termin\u00e9e par le rapport GPS.  Si le v\u00e9hicule est hors trajet et arr\u00eat\u00e9, il sera repr\u00e9sent\u00e9 comme un cercle.\n\nPr\u00e9s de chaque v\u00e9hicule se trouve une \u00e9tiquette associ\u00e9e, li\u00e9e \u00e9 lui par une ligne noire. D\u00e9placez la souris au-dessus d'une des \u00e9tiquettes de v\u00e9hicules. \u00e9 l'instar des arr\u00eats, une fen\u00e9tre contextuelle s'affichera contenant l'information au sujet du v\u00e9hicule.  Une de ces valeurs est l'intervalle, laquelle est bas\u00e9e sur l'\u00e9cart de temps entre les arriv\u00e9es de ce v\u00e9hicule\tau dernier arr\u00eat et \u00e9 l'arr\u00eat pr\u00e9c\u00e9dent.  Des variations additionnelles d'intervalles sont:\n * Intervalle partag\u00e9e - L'\u00e9cart de temps entre les v\u00e9hicules qui partagent le m\u00e9me trac\u00e9 et la m\u00e9me destination mais qui sont assign\u00e9s \u00e9 diff\u00e9rents trajets\n * Intervalle pr\u00e9vue \u00e9 l'horaire - L'\u00e9cart de temps entre les v\u00e9hicules bas\u00e9 sur l'horaire de l'organisme\n\nRespect de l'horaire est la performance du v\u00e9hicule compar\u00e9e \u00e9 l'horaire. Les valeurs positives sont en avance, les valeurs n\u00e9gatives sont en retard.  La couleur de chaque entr\u00e9e de fen\u00e9tre contextuelle de v\u00e9hicule correspond \u00e9 la couleur du trajet.  Si le v\u00e9hicule est hors trajet mais assign\u00e9, sa couleur est blanche Si le v\u00e9hicule est non assign\u00e9, sa couleur est grise.\n\nZOOMS ET PANORAMIQUES\n\nDans le coin sup\u00e9rieur gauche de la carte se trouvent deux\tboutons \u00e9tiquet\u00e9s \"Zoom arri\u00e9re\" et \"Zoom avant\".  Cliquez sur le bouton \"Zoom avant\". Les trajets et les v\u00e9hicules se redessinent imm\u00e9diatement. Selon la vitesse de votre connexion, apr\u00e9s une courte p\u00e9riode de temps, un plan des rues \u00e9 une r\u00e9solution plus \u00e9lev\u00e9e s'affichera.\n\nCliquez-glissez en commen\u00e9ant n'importe o\u00e9 sur la carte pour obtenir un d\u00e9filement imm\u00e9diat.\n\nSELECTION DU TRAJET\n\nCliquez sur le bouton \u00e9tiquet\u00e9 \"Trajets...\".  Les noms et les couleurs de trajets sont montr\u00e9s dans une bo\u00e9te de dialogue, chacun d'eux avec une case \u00e9 cocher.  Cochez ou d\u00e9cochez les cases pour modifier les trajets \u00e9 afficher. Si le nouvel ensemble de trajets couvre une aire g\u00e9ographique diff\u00e9rente, pour mieux visualiser la zone, cliquez sur le bouton \"Zoom sur les trajets s\u00e9lectionn\u00e9s\".  Ceci recentre la carte et choisit le niveau de zoom n\u00e9cessaire pour englober les trajets s\u00e9lectionn\u00e9s .\n\n");
        a("agencyMapHelpText", "VEHICLE INSPECTOR\n\nClick on a vehicle label. Alternatively, click on the button labeled \"Vehicles...\". Various capabilities associated with vehicles will appear in a vehicle inspector window.\n\nClick on the \"Find Vehicle\" or \"Zoom to Vehicle\" buttons. The map will re-center over the selected vehicle and animate a bulls-eye to help locate it.\n\nClick on the \"Show Trails\" button.  A series of marks of diminishing size will appear showing where the vehicle has been.  The \"Clear Trails\" button clears the marks.\n\nSometimes vehicles will be off the map because they are far from any of the selected routes.  To see such vehicles, click on the \"Zoom to All Vehicles\" button.  This recenters the map and changes the zoom level as necessary to show all vehicles.\n\nTo specify job: click on the label of an off-job vehicle.  Select the route with the route pop-up.  The job pop-up will have the choices appropriate for this route.  Select the correct job and then click on the \"Specify Job\" button.\n\nPROPERTIES\n\nVarious properties of the map can be changed. To do so, click on the button labeled \"Properties...\". Checking or unchecking any of the boxes results in the corresponding property immediately being changed in the map.\n\n", "VEHICLE INSPECTOR\n\nCliquez sur une \u00e9tiquette de v\u00e9hicule. Autre option, cliquez sur le bouton \u00e9tiquet\u00e9 \"V\u00e9hicules...\". Les diff\u00e9rentes capacit\u00e9s associ\u00e9es aux v\u00e9hicules apparaissent dans la fen\u00e9tre Vehicle Inspector .\n\nCliquez sur les boutons \"Trouver v\u00e9hicule\" ou \"Zoom sur v\u00e9hicule\". La carte se recentrera sur le v\u00e9hicule s\u00e9lectionn\u00e9 et animera une cible pour aider \u00e9 le rep\u00e9rer.\n\nCliquez sur le bouton \"Afficher les pistes\".  Une s\u00e9rie de marques de taille d\u00e9croissante s'affichera pour montrer le trajet effectu\u00e9 par le v\u00e9hicule.  Le bouton \"Supprimer les pistes\" efface les marques.\n\nParfois les v\u00e9hicules seront en dehors de la carte parce qu'ils sont \u00e9loign\u00e9s de tous les trajets s\u00e9lectionn\u00e9s.  Pour visualiser ces v\u00e9hicules, cliquez sur le bouton \"Zoom sur tous les v\u00e9hicules\".  Ceci recentre la carte et choisit le niveau de zoom n\u00e9cessaire pour visualiser tous les v\u00e9hicules.\n\nPour sp\u00e9cifier un quart de travail: cliquez sur l'\u00e9tiquette d'un v\u00e9hicule hors quart de travail.  Choisissez le trajet au moyen de la fen\u00e9tre contextuelle des trajets.  La fen\u00e9tre contextuelle du quart de travail comportera les choix appropri\u00e9s pour ce trajet.  S\u00e9lectionnez le quart de travail appropri\u00e9, cliquez ensuite sur le bouton \"Sp\u00e9cifier quart\" .\n\nPROPRIETES\n\nLes diff\u00e9rentes propri\u00e9t\u00e9s de la carte peuvent \u00e9tre modifi\u00e9es. Pour le faire, Cliquez sur le bouton \u00e9tiquet\u00e9 \"Propri\u00e9t\u00e9s...\". Cocher ou d\u00e9cocher n'importe quelle bo\u00e9te entra\u00e9ne la modification imm\u00e9diate de la propri\u00e9t\u00e9 correspondante sur la carte.\n\n");
        a("replayMapHelpText", "VEHICLE INSPECTOR\n\nClick on a vehicle label. Alternatively, click on the button labeled \"Vehicles...\".  Various capabilities associated with vehicles will appear in a vehicle inspector window.\n\nClick on the \"Find Vehicle\" or \"Zoom to Vehicle\" buttons.  The map will re-center over the selected vehicle and animate a bulls-eye to help locate it.\n\nClick on the \"Show Trails\" button.  A series of marks of diminishing size will appear showing where the vehicle has been.  The \"Clear Trails\" button clears the marks.\n\nSometimes vehicles will be off the map because they are far from any of the selected routes.  To see such vehicles, click on the \"Zoom to All Vehicles\" button.  This recenters the map and changes the zoom level as necessary to show all vehicles.\n\nPROPERTIES\n\nVarious properties of the map can be changed. To do so, click on the button labeled \"Properties...\".  Checking or unchecking any of the boxes results in the corresponding property immediately being changed in the map.\n\nREPLAY FEATURES\n\nUsers familiar with the Real-Time Console will find the controls described above equally familiar as part of the Replay Map.  Additional controls for replay are the date and time selectors, the replay rate selector, and the stop and play/pause buttons.\n\nThe date and time selectors specify the beginning point of the replay. These selectors are only active while the replay is stopped. If the replay is playing, to change the beginning point, first click the stop button.  The replay rate selector is always active. It can change the replay rate from 1x to 120x.\n\nThe play/pause button has the following effects: if stopped, playing begins at the beginning point specified by the date and time selectors.  If playing, the replay pauses.  If paused, the replay resumes at the time at which it was paused.\n\nThe stop button has the following effect: if playing, playing stops, and the date and time selectors are enabled. If stopped, the stop button has no effect.\n\nA replay status is shown near the replay controls. The status shows the replay date and time.  The replay applet gets historical information from a replay server. When this server is initializing or busy, this is shown in the replay status. Under normal operation, this server should always become available after a short period of time, and the status will be updated.\n\nSome features of the Real-Time Console are not present in the Replay Map: stop predictions are unavailable.  The specify-job feature is unavailable.\n\n", "VEHICLE INSPECTOR\n\nCliquez sur une \u00e9tiquette de v\u00e9hicule. Autre option, cliquez sur le bouton \u00e9tiquet\u00e9 \"V\u00e9hicules...\".  Les diff\u00e9rentes capacit\u00e9s associ\u00e9es aux v\u00e9hicules apparaissent dans la fen\u00e9tre Vehicle Inspector .\n\nCliquez sur les boutons \"Trouver v\u00e9hicule\" ou \"Zoom sur v\u00e9hicule\". La carte se recentrera sur le v\u00e9hicule s\u00e9lectionn\u00e9 et animera une cible pour aider \u00e9 le rep\u00e9rer.\n\nCliquez sur le bouton \"Afficher les pistes\".  Une s\u00e9rie  de marques de taille d\u00e9croissante s'affichera pour montrer le trajet effectu\u00e9 par le v\u00e9hicule.  Le bouton \"Supprimer les pistes\" efface les marques.\n\nParfois les v\u00e9hicules seront en dehors de la carte parce qu'ils sont \u00e9loign\u00e9s de tous les trajets s\u00e9lectionn\u00e9s.  Pour visualiser ces v\u00e9hicules, cliquez sur le bouton \"Zoom sur tous les v\u00e9hicules\".  Ceci recentre la carte et choisit le niveau de zoom n\u00e9cessaire pour visualiser tous les v\u00e9hicules.\n\nPROPRIETES\n\nLes diff\u00e9rentes propri\u00e9t\u00e9s de la carte peuvent \u00e9tre modifi\u00e9es. Pour le faire, Cliquez sur le bouton \u00e9tiquet\u00e9 \"Propri\u00e9t\u00e9s...\".  Cocher ou d\u00e9cocher n'importe quelle bo\u00e9te entra\u00e9ne la modification imm\u00e9diate de la propri\u00e9t\u00e9 correspondante sur la carte.\n\nCARACTERISTIQUES DE REJEU\n\nLes usagers familiers avec la console en temps r\u00e9el trouveront les commandes d\u00e9crites ci-dessus tout aussi famili\u00e9res en partie int\u00e9grante \u00e9 la carte de lecture. Les commandes additionnelles pour la lecture sont les s\u00e9lecteurs de date et heure, le s\u00e9lecteur de vitesse de lecture et les boutons Jouer/Pause.\n\nLes s\u00e9lecteurs de date et heure sp\u00e9cifient le point de d\u00e9part de la relecture. Ces s\u00e9lecteurs ne sont actifs que lorsque la lecture est arr\u00eat\u00e9e. Si la lecture est en cours, pour modifier le point de d\u00e9part, cliquez d'abord sur le bouton Arr\u00eat.  Le s\u00e9lecteur de vitesse de lecture est toujours actif. On peut modifier la vitesse de lecture de 1x \u00e9 120x.\n\nLe bouton Jouer/Pause a les effets ci-apr\u00e9s: si arr\u00eat\u00e9, le jeu d\u00e9bute au point de d\u00e9part sp\u00e9cifi\u00e9 part les s\u00e9lecteurs de date et heure.  Si la lecture est en cours, elle se met en pause.  Si elle est en pause, la lecture se poursuit \u00e9 partir du point o\u00e9 elle avait \u00e9t\u00e9 arr\u00eat\u00e9e.\n\nLe bouton Arr\u00eat a l'effet ci-apr\u00e9s: si la lecture est en cours, elle s'arr\u00eate, et les s\u00e9lecteurs de date et heure deviennent actifs. Si elle est arr\u00eat\u00e9e, le bouton Arr\u00eat n'a aucun effet.\n\nUn indicateur de rejeu s'affiche pr\u00e9s des commandes de rejeu. L'indicateur affiche la date et l'heure de la lecture.  L'appliquette de rejeu re\u00e9oit l'information historis\u00e9e d'un serveur de lecture. Lorsque ce serveur est en train de s'initialiser ou est occup\u00e9, cette information s'affiche sur l'indicateur. Par conditions de fonctionnement normal, ce serveur devrait toujours devenir disponible apr\u00e9s une courte p\u00e9riode de temps, et l'indicateur se mettra \u00e9 jour.\n\nCertaines fonctionnalit\u00e9s de la console en temps r\u00e9el n'apparaissent pas dans la carte de lecture : les pr\u00e9dictions d'arr\u00eats ne sont pas disponibles.  La fonction Sp\u00e9cifier quart de travail est indisponible.\n\n");
        COM.NextBus.AdminMap.a.b = 0;
        COM.NextBus.AdminMap.a.c = 1;
    }
}
