// 
// Decompiled by Procyon v0.5.30
// 

package inscripcion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.awt.Color;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.EventQueue;
import javax.swing.text.Document;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JApplet;

public class Formulario extends JApplet
{
    final String masculino = "~AARON~ABEL~ABELARDO~ABIGAIL~ABRAHAM~ABSALON~ADALBERTO~ADAN~ABELARDO~ADOLFO~ADONIS~ADRIAN~ADRIANO~AGAMENON~AGAPITO~AGAR~AGUSTIN~AITOR~ALAN~ALBANO~ALBERTO~ALBINO~ALDO~ALEX~ALEJANDRO~ALEJO~ALFONSO~ALFREDO~ALVARO~AMADEO~AMADOR~AMBROSIO~AMERICO~AMILCAR~ANASTASIO~ANDRES~ANGEL~ANIBAL~ANICETO~ANSELMO~ANTOLIN~ANTON~ANTONELLO~ANTONIO~ANTONIO~APOLO~APOLONIO~AQUILES~AQUILINO~ARCADIO~ARCANGEL~ARCHIBALDO~ARIANO~ARIEL~ARISTOBULO~ARISTOTELES~ARMANDO~ARNALDO~ARQUIMEDES~ARSENIO~ARTEMIO~ARTURO~ASTOR~ATAHUALPA~ATANASIO~ATILA~AUGUSTO~AURELIANO~AURELIO~AXEL~BACO~BALDOMERO~BALDUINO~BALTASAR~BARTOLOME~BARTOLO~BARUCH~BAUTISTA~BELISARIO~BELTRAN~BENEDICTO~BENIGNO~BENITO~BENJAMIN~BERNABE~BERNARDINO~BERNARDO~BIENVENIDO~BLAS~BONIFACIO~BORIS~BORJA~BRAULIO~BRUNO~CAETANO~CAIN~CALIGULA~CALIPSO~CALISTO~CALIXTO~CAMILO~CANDIDO~CARLO~CARLOMAGNO~CARLOS~CARMELO~CASIANO~CASIMIRO~CASIO~CASTO~CASTOR~CATRIEL~CAYETANO~CECILIANO~CECILIO~CEFERINO~CELESTINO~CELIO~CELSO~CESAR~CHANTAL~CIBELES~CICERON~CID~CIPRIANO~CIRIACO~CIRILO~CIRO~CLAUDIO~CLEMENTE~CLODOVEO~COLUMBANO~CONRADO~CONFUCIO~CONSTANCIO~CONSTANTINO~CORNELIO~COSME~CRISPO~CRISPULO~CRISTIAN~CRISTO~CRISTOBAL~CUCUFATE~DAGOMAR~DALMACIO~DAMASO~DAMIAN~DAMOCLES~DAN~DANI~DANIEL~DANILO~DANTE~DARIO~DAVID~DEDALO~DELFIN~DEMETER~DEMETRIO~DESIDERIO~DIEGO~DIMAS~DIMITRI~DINO~DIODORO~DIOGENES~DIONISIO~DOMENICO~DOMICIO~DOMINGO~DONALDO~DONATO~DIRIAN~DOROTEO~DUSTANO~EDGAR~EDGARDO~EDIPO~EDISON~EDMUNDO~EDUARDO~EDWIN~EDY~EFRAIN~EFREN~EGIDIO~EGISTO~ELADIO~ELEUTERIO~ELIAS~ELIO~ELISANDRO~ELISEO~ELOY~ELVIO~ELVIS~EMMANUEL~EMERSON~EMILIANO~EMILIO~EMMANUEL~ENEAS~ENRIQUE~ENZO~EPIFANIO~ERASMO~ERIC~ERICO~ERMEGOL~ERNESTO~EROS~ESPARTACO~ESTANISLAO~ESTEBAN~ESTEFANO~ESTIBALIZ~ESTRABON~EUGENIO~EULOGIO~EUSEBIO~EUSTAQUIO~EVARISTO~EVELIO~EZEQUIEL~FABIAN~FABIO~FABRICIO~FACUNDO~FAUSTINO~FAUSTO~FEBE~FEDERICO~FEDRO~FELICIANO~FELIPE~FELIX~FERGUS~FERDINANDO~FERMIN~FERNAN~FERNANDO~FERRER~FERRUCIO~FIDEL~FIDELIO~FILEMON~FILIBERTO~FILOMENO~FIADOR~FLAMINIO~FLAVIO~FLORENCIO~FLORENTINO~FLORIAN~FLORIANO~FORTUNATO~FRANCISCO~FRANCO~FROILAN~FRUTOS~FRUCTUOSO~FULCO~FULGENCIO~FULVIO~GABINO~GABRIEL~GAD~GAIA~GAIL~GALEN~GALILEO~GALO~GAMAL~GAMALIEL~GANIMEDES~GARCIA~GASPAR~GASTON~GAUDENCIO~GEDEON~GELASIO~GEMINIANO~GENTIL~GENARO~GEORGE~GERARDO~GERMAIN~GERMAN~GERMANICO~GERSON~GERVASIO~GETULIO~GIANCARLO~GIANFRANCO~GILBERTO~GIORDANO~GIORGIO~GLAUCO~GODOFREDO~GOLIARD~GONZALO~GORDON~GOTARDO~GRACIANO~GRAHAM~GREGORIO~GUALTERIO~GUIDO~GUILLERMO~GUNTER~GUSTAVO~HAAKON~HAMLET~HAROLD~HARTMAN~HASAN~HEBER~HECTOR~HELIO~HELIODORO~HELIOGABALO~HERACLIDES~HERACLIO~HERCULANO~HERCULES~HERIBERTO~HERMAN~HERMENEGILDO~HERMES~HERMINIO~HERMINIO~HERMIONE~HERMOGENES~HERNAN~HERNANI~HERNANDO~HERODIAS~HIGINIO~HILARIO~HILARION~HILDEMARO~HIPACIO~HIPOCRATES~HIPOLITO~HOMERO~HONESTO~HONORATO~HONORIO~HORACIO~HORTENSIO~HOSPICIO~HUBERTO~HUGO~HOGOLINO~HUMBERTO~IAGO~IBERICO~IBERO~ICARO~IGNACIO~IGOR~ILDEFONSO~ILUMINADO~IMANOL~IMPERIO~INCA~INDALECIO~INDIANA~INDIBEIL~INDRO~INGMAR~INOCENCIO~I\u00d1AKI~I\u00d1IGO~IRENEO~IRINEO~ISAAC~ISACIO~ISAIAS~ISIDORO~ISIDRO~ISMAEL~ITALO~IVAN~IVO~JACINTO~JACOB~JACOBO~JAIME~JAIRO~JALIL~JANO~JASON~JAVIER~JENARO~JEREMIAS~JERJES~JERONIMO~JERUSALEN~JESUS~JOAB~JOAQUIN~JOB~JOEL~JON~JONAS~JONATAN~JONATHAN~JORDAN~JORDI~JORGE~JOSAFAT~JOSE~JOSUE~JOVINIANO~JOVINO~JUAN~JUAN~BAUTISTA~JUAN~DE~DIOS~JUDAS~JULIAN~JULIO~JUPITER~JUSTINIANO~JUSTINO~JUSTO~JUVENCIO~JUVENIL~KAISER~KALED~KALIL~KEITH~KENNETH~KENT~KENZO~KEVIN~KILIAN~KIM~KIN~KIRA~KIRIAN~KIRIK~KLAUS~KNUT~KOLMAN~KONG~KRISHNA~KUMIGO~LABAN~LADISLAO~LAMBERTO~LAMCELOT~LANDELINO~LANDOLFO~LANFRANCO~LANZAROTE~LAUREANO~LAURENCIO~LAURENTINO~LAURO~LAUTARO~LAZARO~LEANDRO~LEARCO~LELIO~LENIN~LEO~LEOCADIO~LEODEGARIO~LEON~LEONARDO~LEONCIO~LEONEL~LEOPOLDO~LEOVIGILDO~LESLIE~LESMES~LETO~LEUCIPO~LEVI~LIBERATO~LIBORIO~LICARIO~LICAS~LICEO~LICINIO~LICOMEDES~LIONEL~LINO~LISANDRO~LIVIO~LOBO~LONGINOS~LORENZO~LORETO~LOT~LOTARIO~LUCA~LUCANO~LUCAS~LUCIANO~LUCIO~LUCRECIO~LUDOVICO~LUIS~LUPERCIO~LUTERO~MACARIO~MACEDONIO~MAHOMA~MAGIN~MAGNO~MAINARD~MALAQUIAS~MALCO~MALCOLM~MALIBRAN~MALEN~MAMERTO~MANASES~MANFREDO~MANOLO~MANON~MANRIQUE~MANUEL~MARCELINO~MARCELO~MARCIAL~MARCIANO~MARCO~MARCOS~MARCOVAL~MARDOQUEO~MARIANO~MARINO~MARIO~MARLON~MARSIAS~MARTIN~MARTINIANO~MARTINO~MARVIN~MATEO~MATIAS~MATUSALEN~MAURICIO~MAURO~MAX~MAXIMILIANO~MAXIMO~MECENAS~MELCHOR~MELITO~MELITON~MELQUIADES~MELQUISEDEC~MELVIN~MENANDRO~MENEALO~MENTOR~MERCURIO~MERLIN~METODIO~MICHELLE~MIDAS~MIGUEL~MIJAEL~MILLAN~MILTON~MINOS~MIRKO~MOCTEZUMA~MODESTO~MOHAMED~MOISES~MORENO~MORFEO~MORO~MUSTAFA~NABUCONODOSOR~NADIR~NAHUEL~NAPOLEON~NARCISO~NASYA~NATALIO~NAZARENO~NAZARET~NAZARIO~NEFTALI~NELSON~NEMESIO~NEON~NEPTUNO~NEREO~NERON~NESTOR~NICANOR~NICASIO~NICETO~NICODEMO~NICOLAS~NICOMEDES~NILO~NIVARDO~NOE~NOLAN~NORBERTO~NORMAN~NUNCIO~MU\u00d1O~OBERON~OBERTO~OCTAVIANO~OCTAVIO~ODETTE~ODILON~ODIN~ODISEO~ODORICO~OLAF~OLEGARIO~OLIMPIO~OLIVERIO~OLVIDO~OMAR~ONESIMO~ONOFRE~ORANGEL~ORDO\u00d1O~ORENCIO~ORESTES~ORFEO~ORIGENES~ORIOL~ORION~ORLANDO~OROZCO~OSCAR~OSIAN~OSMAR~OSVALDO~OTELO~OTILIO~OTON~OTONIEL~OVIDIO~OWEN~PABLO~PACIANO~PACIENTE~PACIFICO~PACO~PACOMIO~PALAMEDES~PALMIRO~PANCRACIO~PANFILO~PANTALEON~PAOLO~PARMENIO~PARSIFAL~PASCAL~PASCASIO~PASCUAL~PASTOR~PATERNO~PATRICIO~PATROCINIO~PATROCLO~PAULINO~PAUL~PAULINO~PAULO~PAYO~PEDRO~PELAGIO~PELAYO~PELEO~PEREGRINO~PERICLES~PERPETUO~PERSEO~PETRARCA~PETRONILA~PETRONIO~PIGMALION~PINO~PIO~PLACIDO~PLATON~PLINIO~PLUTARCO~PLUTON~POLICARPO~POLIFEMO~POLINICE~POMPEYO~POMPILIO~PONCIANO~PONCIO~PORFIRIO~POSEIDON~PRIAMO~PRIMITIVO~PRIMO~PRISCO~PROCOPIO~PROMETEO~PROSPERO~PRUDENCIO~PUBLIO~QUICO~QUILDO~QUIJOTE~QUINTILIANO~QUINTIN~QUINTO~QUIQUE~QUIRICO~QUIRINO~RAFA~RAFAEL~RAIMUNDO~RAINERO~RAMADES~RAMIRO~RAMON~RAMOS~RAMSES~RAUL~REAGAN~REFUGIO~REGINALDO~REGINO~REGULO~REINALDO~REMIGIO~REMO~RENAN~RENATO~RENZO~RESTITUTO~REYES~REYNALDO~RICARDO~RIGOBERTO~ROBERTO~ROBINE~ROBINSON~ROBUSTIANO~ROCCO~RODOLFO~RODRIGO~ROGELIO~ROLANDO~ROLDAN~ROMAN~ROMANO~ROMEO~ROMUALDO~ROMULO~ROQUE~ROSENDO~RUBEN~RUFINO~RUFO~RUPERTO~RUSTICO~RUTILIO~RYAN~SABAS~SABINO~SABRA~SACRAMENTO~SALOMON~SALUSTIANO~SALVADOR~SALVIANO~SALVINO~SALVIO~SALUSTIANO~SAMUEL~SANCHO~SANDOR~SANDRO~SANSON~SANTIAGO~SANTO~SANTOS~SATURIO~SATURNINO~SATURNO~SAUL~SAVERIO~SEAN~SEBASTIAN~SEGISMUNDO~SEGUNDO~SEPTIMO~SERAFIN~SERAPIO~SERGIO~SERVANDO~SEVERINO~SEVERO~SIGFRIDO~SILVANO~SILVERIO~SILVESTRE~SILVIO~SIMEON~SIMON~SILESIO~SINFORIANO~SINFOROSO~SION~SIRO~SISIFO~SIXTO~SOCRATES~SOFOCLES~SOLANO~SULPICIO~TABARE~TACIANO~TADEO~TAMAR~TANCREDO~TAMGUY~TARSICIO~TAURINO~TELEMACO~TELMO~TEO~TEOBALDO~TEODOMIRO~TEODORICO~TEODORO~TEOFILO~TEOFANES~TERENCIO~TERPSICORE~TESEO~TIBURCIO~TIMOTEO~TIRSO~TITO~TOBIAS~TOBY~TOMAS~TORCUATO~TORIBIO~TRIFON~TRISTAN~TROILO~TYLER~TIRON~VALENTIN~VALENTINO~VALERIANO~VALERIO~VALERO~VASCO~VELASCO~VENANCIO~VENCESLAO~VENTURA~VICENTE~VICTOR~VICTORIANO~VICTORIO~VIDAL~VINICIO~VENTILA~VIOLANTE~VIRGILIO~VITO~VLADEMIR~VLADIMIRO~WALDO~WALTER~WAREEN~WENCESLAO~WERNER~WERTHER~WASHINGTON~WILLIAMS~WILSON~WOLFANGO~WOODY~XAVIER~YAEL~YAGO~YAMIL~ZACARIAS~ZENON~";
    final String femenino = "~ABRIL~ACACIA~ADA~ADABELA~ADALIA~ADELA~ADELAIDA~ADELINA~ADELINDA~ADORACION~ADRIANA~AFRICA~AFRODITA~AGATA~AGOSTINA~AGRIPINA~AGUSTINA~AIDA~AINOA~AITANA~ALBA~ALBANA~ALBINA~ALBERTA~ALBERTINA~ALDA~ALDANA~ALEGRA~ALEGRIA~ALEJANDRA~ALEJANDRINA~ALEXIA~ALEXA~ALFONSA~ALFONSINA~ALI~ALICIA~ALIDA~ALINA~ALONDRA~ALMA~ALMENDRA~ALMUDENA~ALTAGRACIA~ALTEA~AMADA~AMALIA~AMANCAY~AMANDA~AMAPOLA~AMAYA~AMBAR~AMELIA~AMERICA~AMPARO~ANA~ANABEL~ANABELLA~ANACLARA~ANAHI~ANAIS~ANASTASIA~ANDREA~ANDREINA~ANGELA~ANGELES~ANGELICA~ANGELINA~ANGIE~ANIA~ANTEA~ANTONELA~ANTOLINA~ANTONIETA~ANTONIA~ANUNCIACION~ANUNCIATA~APIA~APOLONIA~AQUILINA~ARABELA~ARACELI~ARANTXAARANZAZU~ARCADIA~ARGENTINA~ARIADNA~ARIANA~ARMONIA~ARTEMISAOARTEMIS~AROA~ASCENSION~ASTRID~ASUNCION~ASUNTA~ATALA~ATALANTA~ATENEA~AUGUSTA~AURELIA~AURORA~AUXILIADORA~AYELEN~AZALEA~AZUCENA~BARBARA~BEATA~BEATRIZ~BEGONIA~BEGO\u00d1A~BELEN~BELINDA~BELLA~BENEDICTO~BENIGNA~BENITA~BERENICE~BERNARDA~BERTA~BETHSABE~BETIANA~BETINA~BIANCA~BIBIANA~BLANCA~BONA~BRIGIDA~BRIGITTE~BRENDA~BRUNILDA~BUENAVENTURA~CALA~CAMELIA~CAMILA~CANDELA~CANDELARIA~CANDIDA~CANELA~CARIDAD~CARINA~CARLA~CARLOTA~CARMELA~CARMEN~CARMINA~CAROL~CAROLA~CAROLINA~CASANDRA~CASILDA~CASTALIA~CATALINA~CATERINA~CATHY~CAYETANA~CECILIA~CEFERINA~CELESTE~CELESTINA~CELIA~CELINA~CINTA~CINTHIA~CIRCE~CLARA~CLARIBEL~CLARISA~CLAUDIA~CLELIA~CLEMENTINA~CLEOPATRA~CLIO~CLOE~CLORIS~CLOTILDE~COLETA~CONCEPCION~CONCHA~CONSOLACION~CONSTANCIA~CONSTANZA~CONSUELO~COPELIA~CORA~CORAL~CORDELIA~CORINA~CORNELIO~COSIMA~COVADONGA~CREUSA~CRISPIN~CRISTAL~CRISTINA~CRISTIANA~DAFNE~DAISY~DALIA~DALILA~DAMARIS~DANA~DANAE~DANIELA~DARIA~DEBORA~DEDICACION~DEJANIRA~DELA~DELFINA~DELIA~DELMA~DEMETER~DESDEMONA~DENIS~DESIRE~DEVORAH~DIADEMA~DIANA~DIGNA~DINA~DIONISIA~DOLORES~DOMINGA~DOMINIQUE~DOMITILA~DONA~DONATELLA~DONATILA~ADNATA~DORA~DORIS~DOROTEA~DULCE~DULCINEA~DUNIA~ECO~EDA~EDDA~EDITA~EDIT~EDNA~EDUINA~EDURNE~EDUVIGIS~EGERIA~EGLE~HIELEN~EIRA~ELBA~ELCIRA~ELDA~ELECTRA~ELENA~ELEONORA~ELISA~ELIZABET~ELISENDA~ELOISA~ELSA~ELVIRA~EMA~EMILIA~EMILIANA~EMILY~EMMA~ENA~ENCARNACION~ENGRACIA~ENRIQUETA~EPIFANIA~ERICA~ERIKA~ERMINIA~ERNESTINA~ESMERALDA~ESPERANZA~ESTEFANIA~ESTELA~ESTER~ESTHER~ESTRELLA~ETEL~ETELVINA~ETHEL~EUFEMIA~EUGENIA~EULALIA~EUNICE~EURIDICE~EUROPA~EUTERPE~EVA~EVANGELINA~EVEL~EVELINA~EXALTACION~FABIOLA~FANNY~FATIMA~FAUSTINA~FE~FEBE~FEBRONIA~FEDERICA~FEDORA~FEDRA~FELICIA~FELICIDAD~FELICITAS~FELIPA~FELISA~FERMINA~FERNANDA~FILIS~FILOMENA~FINA~FIONA~FIORELA~FLAMINIA~FLAVIA~FLOR~FLORA~FLORENCIAFLORENTINA~FLORIDA~FORTUNATA~FRANCESCA~FRANCISCA~FREYA~FRIDA~FRINE~FRUCTUOSA~FUENCISCLA~FUENSANTA~GABRIELA~GALA~GALATEA~GALENA~GARDENIA~GAVIRA~GEA~GEMA~GEMMA~GENCIANA~GENEROSA~GENOVEVA~GEORGIA~GEORGINA~GERALDINA~GERALDINE~GERDA~GERMANA~GERTRUDIS~GIANIRA~GIANNA~GIANNINA~GILDA~GIMENA~GINA~GINEBRA~GINETTE~GIOCONDA~GISELDA~GIULIANA~GIUNIA~GLADIS~GLENDA~GLICERA~GLORIA~GODIVA~GODOLEVA~GRACE~GRACIAS~GRACIELA~GRACIOSA~GRECIA~GREGORIA~GRETA~GRETEL~GRISEL~GRISELDA~GUADALUPE~GUDELIA~GUDULA~GUENDOLINA~GUIA~GUILLERMINA~HADA~HARMONIA~HAYDE~HAYDEE~HEBE~HECUBA~HEIDI~HELDA~HELEN~HELENA~HELEIA~HELGA~HELIANA~HELIENA~HERA~HERMINIA~HERMIONE~HILARIA~HILDA~HILDEGARDA~HILDEGUNDA~HIPODAMIA~HIPOLITAHOMBELINA~HONORATA~HONORINA~HORTENSIA~HOSANA~IANINAIARA~ICIAR~IDA~IDALIA~IDOTA~IFIGENIA~ILUMINADA~IMELDA~IMPERIA~INDIANA~INDIRA~INDRA~INES~INGRID~INMA~INMACULADA~IOANA~IOLE~IRACEMA~IRENE~IRINA~IRIS~IRMA~IRMINA~ISABEL~ISABELA~ISAURA~ISIDORO~ISIS~ISOLDA~ISOLINA~IVA~IVANA~IVON~IVONNE~IZASKUM~JABEL~JACINTA~JACQUELINE~JACOBA~JADE~JAEL~JAMILA~JANA~JAQUELINA~JAZMIN~JEANNETTE~JENNIFER~JENNY~JESSICA~JESUSA~JEZABEL~JIMENA~JOAN~JOANA~JOHANNA~JORDANA~JORGELINA~JOSEFA~JOSEFINA~JOVITA~JUANA~JUDITH~JUDIT~JULIA~JULIANA~JULIETA~JUNQUERA~JUSTA~JUSTINA~JUTTA~KALI~KAREN~KARENA~KARENINA~KARIN~KARINA~KATHERINA~KATIA~KATIXA~KAY~KEILA~KEITH~KENDRA~KINISBURGA~KIONA~KIRA~KIRIAN~LAIA~LAILA~LALA~LAODAMIA~LARA~LARISA~LATONA~LAURA~LAUREANA~LAVINIA~LEA~LEAL~LEANDRA~LEDA~LEIF~LEILA~LELIA~LENA~LEOCADIA~LEOCRICIA~LEONARDA~LEONCIA~LEONELA~LEONIDA~LEONIDAS~LEONILA~LEONILDA~LEONOR~LEONORA~LESBIA~LESLIE~LETICIA~LEUCOFRINA~LIA~LIANA~LIBERA~LIBERATA~LIBERTAD~LIBIA~LICIA~LIDIA~LIUVINA~LIGIA~LILA~LILI~LILIA~LILIAN~LILIANA~LILIT~LINA~LINDA~LIOBA~LIONELA~LIS~LISA~LIU~LIUBA~LIVIA~LIZA~LOLA~LORA~LORELEY~LORENA~LORENZA~LORETA~LORNA~LOURDES~LUANA~LUCIA~LUCIANA~LUCILA~LUCINA~LUCRECIA~LUCY~LUDMILA~LUDOVICA~LUISA~LUISINA~LUJAN~LUMINOSA~LUNA~LUPE~LUTECIA~LUTGARDA~LUZ~LIDIA~LYLA~MABEL~MACARENA~MACHA~MACRINA~MADELAINE~MADONNA~MADRONA~MAFALDA~MAGALI~MAGDA~MAGDALENA~MAGIA~MAGNOLIA~MAIA~MAICA~MAIRA~MAITANE~MAITE~MALENA~MALKA~MALVA~MALVINA~MAMES~MANDISA~MANUELA~MAR~MARA~MARCELA~MARCELINA~MARCIA~MARCIANA~MARFISA~MARGARITA~MARGAUX~MARGOT~MARIA~MARIAN~MARIANA~MARIANELA~MARIBEL~MARIE~MARIEL~MARILINA~MARILU~MARILYN~MARINA~MARION~MARISA~MARISOL~MARITA~MARLENE~MARTA~MARTINA~MATILDA~MATILDE~MAURA~MAXIMA~MAYA~MAYRA~MAYTE~MEDEA~MELANIA~MELBA~MELIBEA~MELINDA~MELISA~MELISENDA~MELITINA~MELODY~MERCEDES~MEREDITH~MERITXELL~MERYL~MESALINA~MIA~MICAELA~MICHELLE~MILA~MILAGROS~MILAGROSA~MILDRED~MILENA~MIMI~MINERVA~MIRANDA~MIREYA~MIRIAM~MIRINDA~MIRRA~MIRTA~MIRYAN~MISERICORDIA~MOIRA~MONA~MONICA~MONTSERRAT~MORGANA~MURIEL~MYRIAN~NADIA~NANCY~NAOMI~NARA~NARCISA~NARILLA~NASYA~NATACHA~NATALI~NATALIA~NATALIE~NATAN~NATASHA~NATIVIDAD~NAUSICA~NAZARENA~NEERA~NEFELE~NELIDA~NELLY~NEREO~NERINA~NICOLAZA~NICOLASA~NICOLE~NIDIA~NIEVES~NILDA~NINA~NINFA~NINI~NIOBE~NISSA~NOELIA~NOEMI~NORA~NORBERTA~NOREIA~NORMA~NOVELLA~NUNILA~NURIA~NURIEL~NYDIA~OBDULIA~OCTAVIA~ODA~ODELIA~ODETTE~ODILA~ODILIA~OFELIA~OITA~OLAYA~OLGA~OLIMPIA~OLINDA~OLIVA~OLIVIA~OLIMPIA~ONA~ONDINA~ONFALIA~ONIA~OPHELIA~ORACION~ORALIA~ORELLANA~ORFILIA~ORIA~ORIANA~ORQUIDEA~ORNELLA~OROSIA~OSEAS~OSIRIS~OSITA~OTILIA~OZANA~PALMA~PALMIRA~PALOMA~PAMELA~PAMPA~PANDORA~PAOLA~PASCUALA~PASTORA~PATRICIA~PATTY~PAULA~PAULINA~PAZ~PELAGIA~PELEAS~PENELOPE~PEREGRINA~PERLA~PERPETUA~PERSEFONE~PETRA~PETRONA~PETRONILA~PETUNIA~PIA~PIEDAD~PIERA~PILAR~POLIXENA~POMONA~PRAXEDES~PRECIOSA~PRESENTACION~PRIMAVERA~PRIMITIVA~PRIMULA~PRISCA~PRISCILA~PRUDENCIA~PULQUERIA~PURA~PURIFICACIONPUSINA~QUERINA~QUERUBINA~QUETA~QUIRINA~QUITERIA~RAFAELA~RAMONA~RAQUEL~REA~REBECA~REGINA~REINA~REMEDIOS~RENATA~RENE~RESTITUTA~RESURRECCION~RITA~ROBERTA~ROCIO~ROMANA~ROMILDA~ROMINA~ROQUELINA~ROSA~ROSALBA~ROSALIA~ROSALINDA~ROSAMUNDA~ROSANA~ROSARIO~ROSAURA~ROSELINA~ROSETA~ROSINA~ROSMARY~ROXANA~ROXANNE~ROXY~RUDY~RUFINA~RUT~RUTH~SABEL~SABINA~SABRINA~SALLY~SALOME~SALUD~SALVADORA~SALVIA~SALVINA~SAMANTA~SANDRA~SANDY~SANTINA~SARA~SARAI~SEGUNDA~SELENA~SELMA~SERAFINA~SERENA~SEVERA~SEVERINA~SHAKIRA~SHARON~SHEILA~SHIRLEY~SIBILA~SIDNEY~SILVANA~SILVIA~SIMONA~SOCORRO~SOFIA~SOLANA~SOLANGES~SOLEDAD~SONIA~SORAYA~SONSOLES~STEFANIA~STELLA~SUSANA~TABITA~TAIF~TAKARA~TALIA~TAMAR~TAMARA~TANIA~TARA~TANYA~TARSILA~TATIANA~TECLA~TELGA~TELMA~TEODORA~TEODOSIA~TEOFANIA~TERESA~TERESITA~TESSA~TETIS~TALIA~THAIS~TIARA~TIFFANY~TINA~TITANIA~TOMASA~TRINIDAD~TULA~TURA~ULIANA~ULLA~URSULA~VALENTINA~VALERIA~VALLIVANA~VALVANERA~VANESA~VEDIA~VELANIA~VELIA~VENTURA~VENUS~VERA~VERONICA~VICENTA~VICTORIA~VILMA~VINTILA~VIOLA~VIOLANTE~VIOLETA~VIRGINIA~VIRIDIANA~VISITACION~VIVIANA~WANDA~WENDY~XENIA~XIMENA~YANET~YANINA~YASMINA~YASMIN~YERMA~YOCASTA~YOCO~YOLANDA~ZAIRA~ZARA~ZENOBIA~ZITA~ZOE~ZORAIDA~ZULEICA~";
    final String LetrasDNI = "TRWAGMYFPDXBNJZSQVHLCKET";
    private JButton jBcerrar;
    private JButton jBsiguiente;
    private JComboBox jCBa\u00f1o;
    private JComboBox jCBlocal;
    private JComboBox jCBsexo;
    private JCheckBox jChBxAcepto;
    private JFrame jFreglamento;
    private JLabel jLabel4;
    private JLabel jLapellido1;
    private JLabel jLapellido2;
    private JLabel jLasterisco1;
    private JLabel jLasterisco2;
    private JLabel jLa\u00f1o;
    private JLabel jLclase;
    private JLabel jLclub;
    private JLabel jLcorreo;
    private JLabel jLdni;
    private JLabel jLedad;
    private JLabel jLemergencias;
    private JLabel jLlocal;
    private JLabel jLmensaje;
    private JLabel jLmovil;
    private JLabel jLnombre;
    private JLabel jLreglamento;
    private JLabel jLsexo;
    private JLabel jLtelefono;
    private JTextField jTFapellido1;
    private JTextField jTFapellido2;
    private JTextField jTFclase;
    private JTextField jTFclub;
    private JTextField jTFcorreo;
    private JTextField jTFdni;
    private JTextField jTFemergencias;
    private JTextField jTFletra;
    private JTextField jTFmovil;
    private JTextField jTFnombre;
    private JTextField jTFtelefono;
    
    public void init() {
        final Calendar c1 = Calendar.getInstance();
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    Formulario.this.initComponents();
                    Formulario.this.jTFnombre.setDocument(new JTextFieldLimit(20, false));
                    Formulario.this.jTFapellido1.setDocument(new JTextFieldLimit(20, false));
                    Formulario.this.jTFapellido2.setDocument(new JTextFieldLimit(20, false));
                    Formulario.this.jTFdni.setDocument(new JTextFieldLimit(8, true));
                    Formulario.this.jTFclub.setDocument(new JTextFieldLimit(20, false));
                    Formulario.this.jTFclase.setDocument(new JTextFieldLimit(4, false));
                    Formulario.this.jTFcorreo.setDocument(new JTextFieldLimit(100, false, false));
                    Formulario.this.jTFtelefono.setDocument(new JTextFieldLimit(9, true));
                    Formulario.this.jTFmovil.setDocument(new JTextFieldLimit(9, true));
                    Formulario.this.jTFemergencias.setDocument(new JTextFieldLimit(9, true));
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void initComponents() {
        this.jFreglamento = new JFrame();
        this.jLreglamento = new JLabel();
        this.jBcerrar = new JButton();
        this.jLnombre = new JLabel();
        this.jLasterisco1 = new JLabel();
        this.jTFnombre = new JTextField();
        this.jLapellido1 = new JLabel();
        this.jLasterisco2 = new JLabel();
        this.jTFapellido1 = new JTextField();
        this.jLapellido2 = new JLabel();
        this.jTFapellido2 = new JTextField();
        this.jLdni = new JLabel();
        this.jTFdni = new JTextField();
        this.jTFletra = new JTextField();
        this.jLa\u00f1o = new JLabel();
        this.jCBa\u00f1o = new JComboBox();
        this.jLedad = new JLabel();
        this.jLsexo = new JLabel();
        this.jCBsexo = new JComboBox();
        this.jLlocal = new JLabel();
        this.jCBlocal = new JComboBox();
        this.jLclub = new JLabel();
        this.jTFclub = new JTextField();
        this.jLclase = new JLabel();
        this.jTFclase = new JTextField();
        this.jLcorreo = new JLabel();
        this.jTFcorreo = new JTextField();
        this.jLtelefono = new JLabel();
        this.jTFtelefono = new JTextField();
        this.jLmovil = new JLabel();
        this.jTFmovil = new JTextField();
        this.jLemergencias = new JLabel();
        this.jTFemergencias = new JTextField();
        this.jLabel4 = new JLabel();
        this.jChBxAcepto = new JCheckBox();
        this.jLmensaje = new JLabel();
        this.jBsiguiente = new JButton();
        this.jFreglamento.setTitle("Reglamento");
        this.jFreglamento.setBounds(new Rectangle(0, 0, 400, 400));
        this.jFreglamento.setCursor(new Cursor(0));
        this.jFreglamento.setResizable(false);
        this.jLreglamento.setHorizontalAlignment(0);
        this.jLreglamento.setText("<html>\n<body>\nREGLAMENTO<br>\n<br>\n<div style=\"text-align: justify;\">1. El hecho de\nparticipar lleva consigo la aceptaci\u00f3n del presente reglamento. En caso\nde duda, prevalecer\u00e1 el criterio de la organizaci\u00f3n.<br>\n</div>\n<div style=\"text-align: justify;\">2. Podr\u00e1n&nbsp;\ninscribirse&nbsp; clubes&nbsp; deportivos,&nbsp; y&nbsp; aquel&nbsp;\nque&nbsp; desee&nbsp; participar&nbsp; de&nbsp; forma individual.<br>\n</div>\n<div style=\"text-align: justify;\">3. La organizaci\u00f3n\ndispone de sistema de clasificaci\u00f3n con lector \u00f3ptico&nbsp; y\nresultados en p\u00e1gina web www.brenes.es <br>\n</div>\n<div style=\"text-align: justify;\">4. Los dorsales se\nrecoger\u00e1n en Paseo Jos\u00e9 Fern\u00e1ndez Vega (Salida-Meta), pr\u00f3ximo a\nEstaci\u00f3n de RENFE, una hora antes de la prueba. <br>\n</div>\n<div style=\"text-align: justify;\">5. Podr\u00e1n ser\ndescalificados los atletas por: conductas antideportivas, no pasar por\nlos controles existentes en la prueba, no llevar el dorsal visible\n(parte delantera). Su p\u00e9rdida se considera descalificaci\u00f3n as\u00ed como no\nrespetar toda&nbsp; las indicaciones realizadas por la organizaci\u00f3n.<br>\n</div>\n<div style=\"text-align: justify;\">6. La organizaci\u00f3n\nno se responsabiliza de las posibles lesiones, da\u00f1os, perjuicios\nmorales o materiales que pudieran sufrir los atletas durante la prueba.<br>\n</div>\n<div style=\"text-align: justify;\">7. los atletas\nclasificados en los tres primeros puestos deber\u00e1n presentar D.N.I.,\ncarnet de conducir o pasaporte en caso de solicitarlo la organizaci\u00f3n.<br>\n</div>\n<div style=\"text-align: justify;\">8. En la Carrera\nAbsoluta (9.500 m.) habr\u00e1 un puesto de avituallamiento.<br>\n</div>\n<div style=\"text-align: justify;\">9. Los&nbsp;\n\u00fanicos&nbsp; veh\u00edculos&nbsp; autorizados&nbsp; a&nbsp; seguir&nbsp;\nla&nbsp; prueba&nbsp; ser\u00e1n&nbsp; asignados&nbsp; por&nbsp; la\norganizaci\u00f3n.<br>\n</div>\n<div style=\"text-align: justify;\">10. Los premios no son\nacumulables.<br>\n</div>\n<div style=\"text-align: justify;\">11. Habr\u00e1 servicio de megafon\u00eda\ny locutor que ir\u00e1n facilitando la informaci\u00f3n antes y durante las\ndistintas pruebas.<br>\n</div>\n<div style=\"text-align: justify;\">12. Las posibles reclamaciones\ndeber\u00e1n ser dirigidas al comit\u00e9 organizador despu\u00e9s de ser publicada la\nclasificaci\u00f3n final en el tabl\u00f3n de anuncios. La decisi\u00f3n del comit\u00e9\norganizador ser\u00e1 inapelable.<br>\n</div>\n<div style=\"text-align: justify;\">13. La organizaci\u00f3n se reserva\nel derecho de modificar todo lo relativo a horarios, itinerario,\navituallamiento, etc. cuando por alg\u00fan motivo fuese necesario.<br>\n</div>\n</body>\n</html>");
        this.jLreglamento.setVerticalAlignment(1);
        this.jLreglamento.setHorizontalTextPosition(0);
        this.jLreglamento.setVerticalTextPosition(1);
        this.jBcerrar.setText("Cerrar reglamento");
        this.jBcerrar.setHorizontalTextPosition(0);
        this.jBcerrar.setVerticalAlignment(3);
        this.jBcerrar.setVerticalTextPosition(3);
        this.jBcerrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                Formulario.this.jBcerrarMouseClicked(evt);
            }
        });
        this.jBcerrar.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Formulario.this.jBcerrarActionPerformed(evt);
            }
        });
        final GroupLayout jFreglamentoLayout = new GroupLayout(this.jFreglamento.getContentPane());
        this.jFreglamento.getContentPane().setLayout(jFreglamentoLayout);
        jFreglamentoLayout.setHorizontalGroup(jFreglamentoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jFreglamentoLayout.createSequentialGroup().addContainerGap().addGroup(jFreglamentoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jFreglamentoLayout.createSequentialGroup().addComponent(this.jLreglamento, -1, 273, 32767).addContainerGap()).addGroup(jFreglamentoLayout.createSequentialGroup().addComponent(this.jBcerrar).addGap(73, 73, 73)))));
        jFreglamentoLayout.setVerticalGroup(jFreglamentoLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jFreglamentoLayout.createSequentialGroup().addContainerGap().addComponent(this.jLreglamento).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jBcerrar).addContainerGap()));
        this.jLnombre.setText("Nombre");
        this.jLasterisco1.setForeground(Color.red);
        this.jLasterisco1.setText("(*)");
        this.jTFnombre.setColumns(20);
        this.jTFnombre.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFnombreFocusLost(evt);
            }
        });
        this.jTFnombre.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent evt) {
                Formulario.this.jTFnombreKeyReleased(evt);
            }
        });
        this.jLapellido1.setText("Primer apellido");
        this.jLasterisco2.setForeground(Color.red);
        this.jLasterisco2.setText("(*)");
        this.jTFapellido1.setColumns(20);
        this.jTFapellido1.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFapellido1FocusLost(evt);
            }
        });
        this.jTFapellido1.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent evt) {
                Formulario.this.jTFapellido1KeyReleased(evt);
            }
        });
        this.jLapellido2.setText("Segundo apellido");
        this.jTFapellido2.setColumns(20);
        this.jTFapellido2.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFapellido2FocusLost(evt);
            }
        });
        this.jLdni.setText("DNI (sin letra)");
        this.jTFdni.setColumns(8);
        this.jTFdni.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent evt) {
                Formulario.this.jTFdniKeyReleased(evt);
            }
        });
        this.jTFletra.setColumns(1);
        this.jTFletra.setFont(new Font("Tahoma", 1, 11));
        this.jTFletra.setEnabled(false);
        this.jLa\u00f1o.setText("A\u00f1o de nacimiento");
        this.jCBa\u00f1o.setMaximumRowCount(12);
        this.jCBa\u00f1o.setModel(new DefaultComboBoxModel<String>(new String[] { "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900" }));
        this.jCBa\u00f1o.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                Formulario.this.jCBa\u00f1oItemStateChanged(evt);
            }
        });
        this.jLedad.setText("(aprox. 0 a\u00f1os)");
        this.jLsexo.setText("Sexo");
        this.jCBsexo.setModel(new DefaultComboBoxModel<String>(new String[] { "Femenino", "Masculino" }));
        this.jCBsexo.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                Formulario.this.jCBsexoItemStateChanged(evt);
            }
        });
        this.jLlocal.setText("Corredor local");
        this.jCBlocal.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
        this.jLclub.setText("Club / Centro escolar");
        this.jTFclub.setColumns(20);
        this.jTFclub.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFclubFocusLost(evt);
            }
        });
        this.jLclase.setText("Clase (si pertenece a un centro escolar / p. ej. 1A, 4C, I3B, 2D...)");
        this.jTFclase.setColumns(4);
        this.jTFclase.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFclaseFocusLost(evt);
            }
        });
        this.jLcorreo.setText("Correo electr\u00f3nico");
        this.jTFcorreo.setColumns(30);
        this.jTFcorreo.addFocusListener(new FocusAdapter() {
            public void focusLost(final FocusEvent evt) {
                Formulario.this.jTFcorreoFocusLost(evt);
            }
        });
        this.jLtelefono.setText("Tel\u00e9fono");
        this.jTFtelefono.setColumns(9);
        this.jLmovil.setText("Tel\u00e9fono m\u00f3vil");
        this.jTFmovil.setColumns(9);
        this.jLemergencias.setText("Tel\u00e9fono para emergencias");
        this.jTFemergencias.setColumns(9);
        this.jLabel4.setFont(new Font("Tahoma", 0, 12));
        this.jLabel4.setForeground(Color.blue);
        this.jLabel4.setText("<HTML><P ALIGN=\"JUSTIFY\"><U>El hecho de inscribirse implica la aceptaci\u00f3n del reglamento de la carrera. Por favor, lea detenidamente el reglamento antes de aceptar de forma definitiva.</U></P></HTML>");
        this.jLabel4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                Formulario.this.jLabel4MouseClicked(evt);
            }
        });
        this.jChBxAcepto.setText("Acepto el reglamento");
        this.jChBxAcepto.setContentAreaFilled(false);
        this.jChBxAcepto.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent evt) {
                Formulario.this.jChBxAceptoItemStateChanged(evt);
            }
        });
        this.jChBxAcepto.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Formulario.this.jChBxAceptoActionPerformed(evt);
            }
        });
        this.jLmensaje.setForeground(Color.red);
        this.jLmensaje.setText(" ");
        this.jBsiguiente.setText("Siguiente");
        this.jBsiguiente.setEnabled(false);
        this.jBsiguiente.setMaximumSize(new Dimension(127, 23));
        this.jBsiguiente.setMinimumSize(new Dimension(127, 23));
        this.jBsiguiente.setPreferredSize(new Dimension(127, 23));
        this.jBsiguiente.setRequestFocusEnabled(false);
        this.jBsiguiente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                Formulario.this.jBsiguienteMouseClicked(evt);
            }
        });
        this.jBsiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Formulario.this.jBsiguienteActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4, -1, 318, 32767).addComponent(this.jLa\u00f1o).addComponent(this.jCBsexo, -2, -1, -2).addComponent(this.jLsexo).addComponent(this.jCBlocal, -2, -1, -2).addComponent(this.jLlocal).addComponent(this.jLclub).addComponent(this.jTFclub, -2, -1, -2).addGroup(layout.createSequentialGroup().addComponent(this.jCBa\u00f1o, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLedad)).addComponent(this.jTFclase, -2, -1, -2).addComponent(this.jLcorreo).addComponent(this.jLtelefono).addComponent(this.jLemergencias).addComponent(this.jTFemergencias, -2, -1, -2).addComponent(this.jTFtelefono, -2, -1, -2).addComponent(this.jLmovil).addComponent(this.jTFmovil, -2, -1, -2).addComponent(this.jLclase, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(this.jTFcorreo, -1, 318, 32767).addComponent(this.jChBxAcepto).addGroup(layout.createSequentialGroup().addComponent(this.jTFdni, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jTFletra, -2, -1, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLnombre).addGap(2, 2, 2).addComponent(this.jLasterisco1)).addComponent(this.jBsiguiente, -2, -1, -2).addComponent(this.jLmensaje, -1, 318, 32767).addComponent(this.jTFapellido2, -2, -1, -2).addComponent(this.jLdni).addComponent(this.jLapellido2).addComponent(this.jTFnombre, -2, -1, -2).addGroup(layout.createSequentialGroup().addComponent(this.jLapellido1).addGap(2, 2, 2).addComponent(this.jLasterisco2)).addComponent(this.jTFapellido1, -2, -1, -2)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLnombre).addComponent(this.jLasterisco1)).addGap(1, 1, 1).addComponent(this.jTFnombre, -2, -1, -2).addGap(2, 2, 2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLapellido1).addComponent(this.jLasterisco2)).addGap(1, 1, 1).addComponent(this.jTFapellido1, -2, 20, -2).addGap(2, 2, 2).addComponent(this.jLapellido2).addGap(1, 1, 1).addComponent(this.jTFapellido2, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLdni).addGap(1, 1, 1).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTFdni, -2, -1, -2).addComponent(this.jTFletra, -2, -1, -2)).addGap(1, 1, 1).addComponent(this.jLa\u00f1o).addGap(2, 2, 2).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jCBa\u00f1o, -2, -1, -2).addComponent(this.jLedad)).addGap(1, 1, 1).addComponent(this.jLsexo).addGap(1, 1, 1).addComponent(this.jCBsexo, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLlocal).addGap(1, 1, 1).addComponent(this.jCBlocal, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLclub).addGap(1, 1, 1).addComponent(this.jTFclub, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLclase).addGap(1, 1, 1).addComponent(this.jTFclase, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLcorreo).addGap(1, 1, 1).addComponent(this.jTFcorreo, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLtelefono).addGap(1, 1, 1).addComponent(this.jTFtelefono, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLmovil).addGap(1, 1, 1).addComponent(this.jTFmovil, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLemergencias).addGap(1, 1, 1).addComponent(this.jTFemergencias, -2, -1, -2).addGap(2, 2, 2).addComponent(this.jLabel4, -2, 40, -2).addGap(2, 2, 2).addComponent(this.jChBxAcepto).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLmensaje).addGap(1, 1, 1).addComponent(this.jBsiguiente, -2, -1, -2).addContainerGap(-1, 32767)));
        this.jLabel4.getAccessibleContext().setAccessibleName("El hecho de inscribirse implica la aceptaci\u00f3n del reglamento de la \ncarrera. Por favor, lea detenidamente el reglamento antes de \naceptar de forma definitiva.");
    }
    
    private void jCBa\u00f1oItemStateChanged(final ItemEvent evt) {
        final int edad = Integer.parseInt(this.jCBa\u00f1o.getItemAt(0).toString()) - Integer.valueOf(this.jCBa\u00f1o.getSelectedItem().toString());
        this.jLedad.setText("(aprox. " + String.valueOf(edad) + " a\u00f1os)");
        this.jLmensaje.setText("");
        if (edad >= 10 & edad <= 11) {
            this.jLmensaje.setText("Alev\u00edn M y F");
        }
        else if (edad >= 12 & edad <= 13) {
            this.jLmensaje.setText("Infantil M y F");
        }
        else if (edad >= 14 & edad <= 15) {
            this.jLmensaje.setText("Cadete M y F");
        }
        else if (this.jCBsexo.getSelectedItem().toString().equals("Masculino")) {
            if (edad <= 7) {
                this.jLmensaje.setText("Pitufo M");
            }
            else if (edad >= 8 & edad <= 9) {
                this.jLmensaje.setText("Benjam\u00edn M");
            }
            else if (edad >= 16 & edad <= 39) {
                this.jLmensaje.setText("Absoluta M");
            }
            else if (edad >= 40 & edad <= 50) {
                this.jLmensaje.setText("Veteranos A");
            }
            else if (edad >= 51) {
                this.jLmensaje.setText("Veteranos B");
            }
        }
        else if (this.jCBsexo.getSelectedItem().toString().equals("Femenino")) {
            if (edad <= 7) {
                this.jLmensaje.setText("Pitufo F");
            }
            else if (edad >= 8 & edad <= 9) {
                this.jLmensaje.setText("Benjam\u00edn F");
            }
            else if (edad >= 16 & edad <= 34) {
                this.jLmensaje.setText("Absoluta F");
            }
            else if (edad >= 35 & edad <= 45) {
                this.jLmensaje.setText("Veteranas A");
            }
            else if (edad >= 46) {
                this.jLmensaje.setText("Veteranas B");
            }
        }
    }
    
    private void jBsiguienteMouseClicked(final MouseEvent evt) {
        if (this.jBsiguiente.isEnabled()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (Exception ex) {
                this.jLmensaje.setText("EN ESTOS MOMENTOS NO SE PUEDE TRAMITAR SU INSCRIPCION");
            }
            try {
                final Connection cnx = DriverManager.getConnection("jdbc:mysql://brenes.es/carrera?user=carrera&password=954796469");
                final Statement stm = cnx.createStatement();
                stm.executeUpdate("INSERT INTO inscripciones (descarte, nombre, apellido1, apellido2, dni, a\u00f1o, sexo, local, club, clase, correo, telefono, movil, emergencias) VALUES('N', '" + this.jTFnombre.getText() + "', '" + this.jTFapellido1.getText() + "', '" + this.jTFapellido2.getText() + "', '" + this.jTFdni.getText() + "', " + this.jCBa\u00f1o.getSelectedItem().toString() + ", '" + this.jCBsexo.getSelectedItem().toString().substring(0, 1) + "', '" + this.jCBlocal.getSelectedItem().toString().substring(0, 1) + "', '" + this.jTFclub.getText() + "', '" + this.jTFclase.getText() + "', '" + this.jTFcorreo.getText() + "', '" + this.jTFtelefono.getText() + "', '" + this.jTFmovil.getText() + "', '" + this.jTFemergencias.getText() + "')");
                stm.close();
                cnx.close();
                try {
                    final URL yahoo = new URL("http://www.brenes.es/gracias.html");
                    this.getAppletContext().showDocument(yahoo, "_self");
                }
                catch (MalformedURLException ex3) {}
            }
            catch (SQLException ex2) {
                this.jLmensaje.setText("EN ESTOS MOMENTOS NO SE PUEDE TRAMITAR SU INSCRIPCION");
            }
        }
    }
    
    private void jTFnombreFocusLost(final FocusEvent evt) {
        this.jTFnombre.setText(this.jTFnombre.getText().trim());
        String nombre;
        if (this.jTFnombre.getText().contains(" ")) {
            nombre = this.jTFnombre.getText().substring(0, this.jTFnombre.getText().indexOf(" "));
        }
        else {
            nombre = this.jTFnombre.getText();
        }
        nombre = "~" + nombre + "~";
        if ("~AARON~ABEL~ABELARDO~ABIGAIL~ABRAHAM~ABSALON~ADALBERTO~ADAN~ABELARDO~ADOLFO~ADONIS~ADRIAN~ADRIANO~AGAMENON~AGAPITO~AGAR~AGUSTIN~AITOR~ALAN~ALBANO~ALBERTO~ALBINO~ALDO~ALEX~ALEJANDRO~ALEJO~ALFONSO~ALFREDO~ALVARO~AMADEO~AMADOR~AMBROSIO~AMERICO~AMILCAR~ANASTASIO~ANDRES~ANGEL~ANIBAL~ANICETO~ANSELMO~ANTOLIN~ANTON~ANTONELLO~ANTONIO~ANTONIO~APOLO~APOLONIO~AQUILES~AQUILINO~ARCADIO~ARCANGEL~ARCHIBALDO~ARIANO~ARIEL~ARISTOBULO~ARISTOTELES~ARMANDO~ARNALDO~ARQUIMEDES~ARSENIO~ARTEMIO~ARTURO~ASTOR~ATAHUALPA~ATANASIO~ATILA~AUGUSTO~AURELIANO~AURELIO~AXEL~BACO~BALDOMERO~BALDUINO~BALTASAR~BARTOLOME~BARTOLO~BARUCH~BAUTISTA~BELISARIO~BELTRAN~BENEDICTO~BENIGNO~BENITO~BENJAMIN~BERNABE~BERNARDINO~BERNARDO~BIENVENIDO~BLAS~BONIFACIO~BORIS~BORJA~BRAULIO~BRUNO~CAETANO~CAIN~CALIGULA~CALIPSO~CALISTO~CALIXTO~CAMILO~CANDIDO~CARLO~CARLOMAGNO~CARLOS~CARMELO~CASIANO~CASIMIRO~CASIO~CASTO~CASTOR~CATRIEL~CAYETANO~CECILIANO~CECILIO~CEFERINO~CELESTINO~CELIO~CELSO~CESAR~CHANTAL~CIBELES~CICERON~CID~CIPRIANO~CIRIACO~CIRILO~CIRO~CLAUDIO~CLEMENTE~CLODOVEO~COLUMBANO~CONRADO~CONFUCIO~CONSTANCIO~CONSTANTINO~CORNELIO~COSME~CRISPO~CRISPULO~CRISTIAN~CRISTO~CRISTOBAL~CUCUFATE~DAGOMAR~DALMACIO~DAMASO~DAMIAN~DAMOCLES~DAN~DANI~DANIEL~DANILO~DANTE~DARIO~DAVID~DEDALO~DELFIN~DEMETER~DEMETRIO~DESIDERIO~DIEGO~DIMAS~DIMITRI~DINO~DIODORO~DIOGENES~DIONISIO~DOMENICO~DOMICIO~DOMINGO~DONALDO~DONATO~DIRIAN~DOROTEO~DUSTANO~EDGAR~EDGARDO~EDIPO~EDISON~EDMUNDO~EDUARDO~EDWIN~EDY~EFRAIN~EFREN~EGIDIO~EGISTO~ELADIO~ELEUTERIO~ELIAS~ELIO~ELISANDRO~ELISEO~ELOY~ELVIO~ELVIS~EMMANUEL~EMERSON~EMILIANO~EMILIO~EMMANUEL~ENEAS~ENRIQUE~ENZO~EPIFANIO~ERASMO~ERIC~ERICO~ERMEGOL~ERNESTO~EROS~ESPARTACO~ESTANISLAO~ESTEBAN~ESTEFANO~ESTIBALIZ~ESTRABON~EUGENIO~EULOGIO~EUSEBIO~EUSTAQUIO~EVARISTO~EVELIO~EZEQUIEL~FABIAN~FABIO~FABRICIO~FACUNDO~FAUSTINO~FAUSTO~FEBE~FEDERICO~FEDRO~FELICIANO~FELIPE~FELIX~FERGUS~FERDINANDO~FERMIN~FERNAN~FERNANDO~FERRER~FERRUCIO~FIDEL~FIDELIO~FILEMON~FILIBERTO~FILOMENO~FIADOR~FLAMINIO~FLAVIO~FLORENCIO~FLORENTINO~FLORIAN~FLORIANO~FORTUNATO~FRANCISCO~FRANCO~FROILAN~FRUTOS~FRUCTUOSO~FULCO~FULGENCIO~FULVIO~GABINO~GABRIEL~GAD~GAIA~GAIL~GALEN~GALILEO~GALO~GAMAL~GAMALIEL~GANIMEDES~GARCIA~GASPAR~GASTON~GAUDENCIO~GEDEON~GELASIO~GEMINIANO~GENTIL~GENARO~GEORGE~GERARDO~GERMAIN~GERMAN~GERMANICO~GERSON~GERVASIO~GETULIO~GIANCARLO~GIANFRANCO~GILBERTO~GIORDANO~GIORGIO~GLAUCO~GODOFREDO~GOLIARD~GONZALO~GORDON~GOTARDO~GRACIANO~GRAHAM~GREGORIO~GUALTERIO~GUIDO~GUILLERMO~GUNTER~GUSTAVO~HAAKON~HAMLET~HAROLD~HARTMAN~HASAN~HEBER~HECTOR~HELIO~HELIODORO~HELIOGABALO~HERACLIDES~HERACLIO~HERCULANO~HERCULES~HERIBERTO~HERMAN~HERMENEGILDO~HERMES~HERMINIO~HERMINIO~HERMIONE~HERMOGENES~HERNAN~HERNANI~HERNANDO~HERODIAS~HIGINIO~HILARIO~HILARION~HILDEMARO~HIPACIO~HIPOCRATES~HIPOLITO~HOMERO~HONESTO~HONORATO~HONORIO~HORACIO~HORTENSIO~HOSPICIO~HUBERTO~HUGO~HOGOLINO~HUMBERTO~IAGO~IBERICO~IBERO~ICARO~IGNACIO~IGOR~ILDEFONSO~ILUMINADO~IMANOL~IMPERIO~INCA~INDALECIO~INDIANA~INDIBEIL~INDRO~INGMAR~INOCENCIO~I\u00d1AKI~I\u00d1IGO~IRENEO~IRINEO~ISAAC~ISACIO~ISAIAS~ISIDORO~ISIDRO~ISMAEL~ITALO~IVAN~IVO~JACINTO~JACOB~JACOBO~JAIME~JAIRO~JALIL~JANO~JASON~JAVIER~JENARO~JEREMIAS~JERJES~JERONIMO~JERUSALEN~JESUS~JOAB~JOAQUIN~JOB~JOEL~JON~JONAS~JONATAN~JONATHAN~JORDAN~JORDI~JORGE~JOSAFAT~JOSE~JOSUE~JOVINIANO~JOVINO~JUAN~JUAN~BAUTISTA~JUAN~DE~DIOS~JUDAS~JULIAN~JULIO~JUPITER~JUSTINIANO~JUSTINO~JUSTO~JUVENCIO~JUVENIL~KAISER~KALED~KALIL~KEITH~KENNETH~KENT~KENZO~KEVIN~KILIAN~KIM~KIN~KIRA~KIRIAN~KIRIK~KLAUS~KNUT~KOLMAN~KONG~KRISHNA~KUMIGO~LABAN~LADISLAO~LAMBERTO~LAMCELOT~LANDELINO~LANDOLFO~LANFRANCO~LANZAROTE~LAUREANO~LAURENCIO~LAURENTINO~LAURO~LAUTARO~LAZARO~LEANDRO~LEARCO~LELIO~LENIN~LEO~LEOCADIO~LEODEGARIO~LEON~LEONARDO~LEONCIO~LEONEL~LEOPOLDO~LEOVIGILDO~LESLIE~LESMES~LETO~LEUCIPO~LEVI~LIBERATO~LIBORIO~LICARIO~LICAS~LICEO~LICINIO~LICOMEDES~LIONEL~LINO~LISANDRO~LIVIO~LOBO~LONGINOS~LORENZO~LORETO~LOT~LOTARIO~LUCA~LUCANO~LUCAS~LUCIANO~LUCIO~LUCRECIO~LUDOVICO~LUIS~LUPERCIO~LUTERO~MACARIO~MACEDONIO~MAHOMA~MAGIN~MAGNO~MAINARD~MALAQUIAS~MALCO~MALCOLM~MALIBRAN~MALEN~MAMERTO~MANASES~MANFREDO~MANOLO~MANON~MANRIQUE~MANUEL~MARCELINO~MARCELO~MARCIAL~MARCIANO~MARCO~MARCOS~MARCOVAL~MARDOQUEO~MARIANO~MARINO~MARIO~MARLON~MARSIAS~MARTIN~MARTINIANO~MARTINO~MARVIN~MATEO~MATIAS~MATUSALEN~MAURICIO~MAURO~MAX~MAXIMILIANO~MAXIMO~MECENAS~MELCHOR~MELITO~MELITON~MELQUIADES~MELQUISEDEC~MELVIN~MENANDRO~MENEALO~MENTOR~MERCURIO~MERLIN~METODIO~MICHELLE~MIDAS~MIGUEL~MIJAEL~MILLAN~MILTON~MINOS~MIRKO~MOCTEZUMA~MODESTO~MOHAMED~MOISES~MORENO~MORFEO~MORO~MUSTAFA~NABUCONODOSOR~NADIR~NAHUEL~NAPOLEON~NARCISO~NASYA~NATALIO~NAZARENO~NAZARET~NAZARIO~NEFTALI~NELSON~NEMESIO~NEON~NEPTUNO~NEREO~NERON~NESTOR~NICANOR~NICASIO~NICETO~NICODEMO~NICOLAS~NICOMEDES~NILO~NIVARDO~NOE~NOLAN~NORBERTO~NORMAN~NUNCIO~MU\u00d1O~OBERON~OBERTO~OCTAVIANO~OCTAVIO~ODETTE~ODILON~ODIN~ODISEO~ODORICO~OLAF~OLEGARIO~OLIMPIO~OLIVERIO~OLVIDO~OMAR~ONESIMO~ONOFRE~ORANGEL~ORDO\u00d1O~ORENCIO~ORESTES~ORFEO~ORIGENES~ORIOL~ORION~ORLANDO~OROZCO~OSCAR~OSIAN~OSMAR~OSVALDO~OTELO~OTILIO~OTON~OTONIEL~OVIDIO~OWEN~PABLO~PACIANO~PACIENTE~PACIFICO~PACO~PACOMIO~PALAMEDES~PALMIRO~PANCRACIO~PANFILO~PANTALEON~PAOLO~PARMENIO~PARSIFAL~PASCAL~PASCASIO~PASCUAL~PASTOR~PATERNO~PATRICIO~PATROCINIO~PATROCLO~PAULINO~PAUL~PAULINO~PAULO~PAYO~PEDRO~PELAGIO~PELAYO~PELEO~PEREGRINO~PERICLES~PERPETUO~PERSEO~PETRARCA~PETRONILA~PETRONIO~PIGMALION~PINO~PIO~PLACIDO~PLATON~PLINIO~PLUTARCO~PLUTON~POLICARPO~POLIFEMO~POLINICE~POMPEYO~POMPILIO~PONCIANO~PONCIO~PORFIRIO~POSEIDON~PRIAMO~PRIMITIVO~PRIMO~PRISCO~PROCOPIO~PROMETEO~PROSPERO~PRUDENCIO~PUBLIO~QUICO~QUILDO~QUIJOTE~QUINTILIANO~QUINTIN~QUINTO~QUIQUE~QUIRICO~QUIRINO~RAFA~RAFAEL~RAIMUNDO~RAINERO~RAMADES~RAMIRO~RAMON~RAMOS~RAMSES~RAUL~REAGAN~REFUGIO~REGINALDO~REGINO~REGULO~REINALDO~REMIGIO~REMO~RENAN~RENATO~RENZO~RESTITUTO~REYES~REYNALDO~RICARDO~RIGOBERTO~ROBERTO~ROBINE~ROBINSON~ROBUSTIANO~ROCCO~RODOLFO~RODRIGO~ROGELIO~ROLANDO~ROLDAN~ROMAN~ROMANO~ROMEO~ROMUALDO~ROMULO~ROQUE~ROSENDO~RUBEN~RUFINO~RUFO~RUPERTO~RUSTICO~RUTILIO~RYAN~SABAS~SABINO~SABRA~SACRAMENTO~SALOMON~SALUSTIANO~SALVADOR~SALVIANO~SALVINO~SALVIO~SALUSTIANO~SAMUEL~SANCHO~SANDOR~SANDRO~SANSON~SANTIAGO~SANTO~SANTOS~SATURIO~SATURNINO~SATURNO~SAUL~SAVERIO~SEAN~SEBASTIAN~SEGISMUNDO~SEGUNDO~SEPTIMO~SERAFIN~SERAPIO~SERGIO~SERVANDO~SEVERINO~SEVERO~SIGFRIDO~SILVANO~SILVERIO~SILVESTRE~SILVIO~SIMEON~SIMON~SILESIO~SINFORIANO~SINFOROSO~SION~SIRO~SISIFO~SIXTO~SOCRATES~SOFOCLES~SOLANO~SULPICIO~TABARE~TACIANO~TADEO~TAMAR~TANCREDO~TAMGUY~TARSICIO~TAURINO~TELEMACO~TELMO~TEO~TEOBALDO~TEODOMIRO~TEODORICO~TEODORO~TEOFILO~TEOFANES~TERENCIO~TERPSICORE~TESEO~TIBURCIO~TIMOTEO~TIRSO~TITO~TOBIAS~TOBY~TOMAS~TORCUATO~TORIBIO~TRIFON~TRISTAN~TROILO~TYLER~TIRON~VALENTIN~VALENTINO~VALERIANO~VALERIO~VALERO~VASCO~VELASCO~VENANCIO~VENCESLAO~VENTURA~VICENTE~VICTOR~VICTORIANO~VICTORIO~VIDAL~VINICIO~VENTILA~VIOLANTE~VIRGILIO~VITO~VLADEMIR~VLADIMIRO~WALDO~WALTER~WAREEN~WENCESLAO~WERNER~WERTHER~WASHINGTON~WILLIAMS~WILSON~WOLFANGO~WOODY~XAVIER~YAEL~YAGO~YAMIL~ZACARIAS~ZENON~".contains(nombre)) {
            this.jCBsexo.setSelectedItem("Masculino");
        }
        else if ("~ABRIL~ACACIA~ADA~ADABELA~ADALIA~ADELA~ADELAIDA~ADELINA~ADELINDA~ADORACION~ADRIANA~AFRICA~AFRODITA~AGATA~AGOSTINA~AGRIPINA~AGUSTINA~AIDA~AINOA~AITANA~ALBA~ALBANA~ALBINA~ALBERTA~ALBERTINA~ALDA~ALDANA~ALEGRA~ALEGRIA~ALEJANDRA~ALEJANDRINA~ALEXIA~ALEXA~ALFONSA~ALFONSINA~ALI~ALICIA~ALIDA~ALINA~ALONDRA~ALMA~ALMENDRA~ALMUDENA~ALTAGRACIA~ALTEA~AMADA~AMALIA~AMANCAY~AMANDA~AMAPOLA~AMAYA~AMBAR~AMELIA~AMERICA~AMPARO~ANA~ANABEL~ANABELLA~ANACLARA~ANAHI~ANAIS~ANASTASIA~ANDREA~ANDREINA~ANGELA~ANGELES~ANGELICA~ANGELINA~ANGIE~ANIA~ANTEA~ANTONELA~ANTOLINA~ANTONIETA~ANTONIA~ANUNCIACION~ANUNCIATA~APIA~APOLONIA~AQUILINA~ARABELA~ARACELI~ARANTXAARANZAZU~ARCADIA~ARGENTINA~ARIADNA~ARIANA~ARMONIA~ARTEMISAOARTEMIS~AROA~ASCENSION~ASTRID~ASUNCION~ASUNTA~ATALA~ATALANTA~ATENEA~AUGUSTA~AURELIA~AURORA~AUXILIADORA~AYELEN~AZALEA~AZUCENA~BARBARA~BEATA~BEATRIZ~BEGONIA~BEGO\u00d1A~BELEN~BELINDA~BELLA~BENEDICTO~BENIGNA~BENITA~BERENICE~BERNARDA~BERTA~BETHSABE~BETIANA~BETINA~BIANCA~BIBIANA~BLANCA~BONA~BRIGIDA~BRIGITTE~BRENDA~BRUNILDA~BUENAVENTURA~CALA~CAMELIA~CAMILA~CANDELA~CANDELARIA~CANDIDA~CANELA~CARIDAD~CARINA~CARLA~CARLOTA~CARMELA~CARMEN~CARMINA~CAROL~CAROLA~CAROLINA~CASANDRA~CASILDA~CASTALIA~CATALINA~CATERINA~CATHY~CAYETANA~CECILIA~CEFERINA~CELESTE~CELESTINA~CELIA~CELINA~CINTA~CINTHIA~CIRCE~CLARA~CLARIBEL~CLARISA~CLAUDIA~CLELIA~CLEMENTINA~CLEOPATRA~CLIO~CLOE~CLORIS~CLOTILDE~COLETA~CONCEPCION~CONCHA~CONSOLACION~CONSTANCIA~CONSTANZA~CONSUELO~COPELIA~CORA~CORAL~CORDELIA~CORINA~CORNELIO~COSIMA~COVADONGA~CREUSA~CRISPIN~CRISTAL~CRISTINA~CRISTIANA~DAFNE~DAISY~DALIA~DALILA~DAMARIS~DANA~DANAE~DANIELA~DARIA~DEBORA~DEDICACION~DEJANIRA~DELA~DELFINA~DELIA~DELMA~DEMETER~DESDEMONA~DENIS~DESIRE~DEVORAH~DIADEMA~DIANA~DIGNA~DINA~DIONISIA~DOLORES~DOMINGA~DOMINIQUE~DOMITILA~DONA~DONATELLA~DONATILA~ADNATA~DORA~DORIS~DOROTEA~DULCE~DULCINEA~DUNIA~ECO~EDA~EDDA~EDITA~EDIT~EDNA~EDUINA~EDURNE~EDUVIGIS~EGERIA~EGLE~HIELEN~EIRA~ELBA~ELCIRA~ELDA~ELECTRA~ELENA~ELEONORA~ELISA~ELIZABET~ELISENDA~ELOISA~ELSA~ELVIRA~EMA~EMILIA~EMILIANA~EMILY~EMMA~ENA~ENCARNACION~ENGRACIA~ENRIQUETA~EPIFANIA~ERICA~ERIKA~ERMINIA~ERNESTINA~ESMERALDA~ESPERANZA~ESTEFANIA~ESTELA~ESTER~ESTHER~ESTRELLA~ETEL~ETELVINA~ETHEL~EUFEMIA~EUGENIA~EULALIA~EUNICE~EURIDICE~EUROPA~EUTERPE~EVA~EVANGELINA~EVEL~EVELINA~EXALTACION~FABIOLA~FANNY~FATIMA~FAUSTINA~FE~FEBE~FEBRONIA~FEDERICA~FEDORA~FEDRA~FELICIA~FELICIDAD~FELICITAS~FELIPA~FELISA~FERMINA~FERNANDA~FILIS~FILOMENA~FINA~FIONA~FIORELA~FLAMINIA~FLAVIA~FLOR~FLORA~FLORENCIAFLORENTINA~FLORIDA~FORTUNATA~FRANCESCA~FRANCISCA~FREYA~FRIDA~FRINE~FRUCTUOSA~FUENCISCLA~FUENSANTA~GABRIELA~GALA~GALATEA~GALENA~GARDENIA~GAVIRA~GEA~GEMA~GEMMA~GENCIANA~GENEROSA~GENOVEVA~GEORGIA~GEORGINA~GERALDINA~GERALDINE~GERDA~GERMANA~GERTRUDIS~GIANIRA~GIANNA~GIANNINA~GILDA~GIMENA~GINA~GINEBRA~GINETTE~GIOCONDA~GISELDA~GIULIANA~GIUNIA~GLADIS~GLENDA~GLICERA~GLORIA~GODIVA~GODOLEVA~GRACE~GRACIAS~GRACIELA~GRACIOSA~GRECIA~GREGORIA~GRETA~GRETEL~GRISEL~GRISELDA~GUADALUPE~GUDELIA~GUDULA~GUENDOLINA~GUIA~GUILLERMINA~HADA~HARMONIA~HAYDE~HAYDEE~HEBE~HECUBA~HEIDI~HELDA~HELEN~HELENA~HELEIA~HELGA~HELIANA~HELIENA~HERA~HERMINIA~HERMIONE~HILARIA~HILDA~HILDEGARDA~HILDEGUNDA~HIPODAMIA~HIPOLITAHOMBELINA~HONORATA~HONORINA~HORTENSIA~HOSANA~IANINAIARA~ICIAR~IDA~IDALIA~IDOTA~IFIGENIA~ILUMINADA~IMELDA~IMPERIA~INDIANA~INDIRA~INDRA~INES~INGRID~INMA~INMACULADA~IOANA~IOLE~IRACEMA~IRENE~IRINA~IRIS~IRMA~IRMINA~ISABEL~ISABELA~ISAURA~ISIDORO~ISIS~ISOLDA~ISOLINA~IVA~IVANA~IVON~IVONNE~IZASKUM~JABEL~JACINTA~JACQUELINE~JACOBA~JADE~JAEL~JAMILA~JANA~JAQUELINA~JAZMIN~JEANNETTE~JENNIFER~JENNY~JESSICA~JESUSA~JEZABEL~JIMENA~JOAN~JOANA~JOHANNA~JORDANA~JORGELINA~JOSEFA~JOSEFINA~JOVITA~JUANA~JUDITH~JUDIT~JULIA~JULIANA~JULIETA~JUNQUERA~JUSTA~JUSTINA~JUTTA~KALI~KAREN~KARENA~KARENINA~KARIN~KARINA~KATHERINA~KATIA~KATIXA~KAY~KEILA~KEITH~KENDRA~KINISBURGA~KIONA~KIRA~KIRIAN~LAIA~LAILA~LALA~LAODAMIA~LARA~LARISA~LATONA~LAURA~LAUREANA~LAVINIA~LEA~LEAL~LEANDRA~LEDA~LEIF~LEILA~LELIA~LENA~LEOCADIA~LEOCRICIA~LEONARDA~LEONCIA~LEONELA~LEONIDA~LEONIDAS~LEONILA~LEONILDA~LEONOR~LEONORA~LESBIA~LESLIE~LETICIA~LEUCOFRINA~LIA~LIANA~LIBERA~LIBERATA~LIBERTAD~LIBIA~LICIA~LIDIA~LIUVINA~LIGIA~LILA~LILI~LILIA~LILIAN~LILIANA~LILIT~LINA~LINDA~LIOBA~LIONELA~LIS~LISA~LIU~LIUBA~LIVIA~LIZA~LOLA~LORA~LORELEY~LORENA~LORENZA~LORETA~LORNA~LOURDES~LUANA~LUCIA~LUCIANA~LUCILA~LUCINA~LUCRECIA~LUCY~LUDMILA~LUDOVICA~LUISA~LUISINA~LUJAN~LUMINOSA~LUNA~LUPE~LUTECIA~LUTGARDA~LUZ~LIDIA~LYLA~MABEL~MACARENA~MACHA~MACRINA~MADELAINE~MADONNA~MADRONA~MAFALDA~MAGALI~MAGDA~MAGDALENA~MAGIA~MAGNOLIA~MAIA~MAICA~MAIRA~MAITANE~MAITE~MALENA~MALKA~MALVA~MALVINA~MAMES~MANDISA~MANUELA~MAR~MARA~MARCELA~MARCELINA~MARCIA~MARCIANA~MARFISA~MARGARITA~MARGAUX~MARGOT~MARIA~MARIAN~MARIANA~MARIANELA~MARIBEL~MARIE~MARIEL~MARILINA~MARILU~MARILYN~MARINA~MARION~MARISA~MARISOL~MARITA~MARLENE~MARTA~MARTINA~MATILDA~MATILDE~MAURA~MAXIMA~MAYA~MAYRA~MAYTE~MEDEA~MELANIA~MELBA~MELIBEA~MELINDA~MELISA~MELISENDA~MELITINA~MELODY~MERCEDES~MEREDITH~MERITXELL~MERYL~MESALINA~MIA~MICAELA~MICHELLE~MILA~MILAGROS~MILAGROSA~MILDRED~MILENA~MIMI~MINERVA~MIRANDA~MIREYA~MIRIAM~MIRINDA~MIRRA~MIRTA~MIRYAN~MISERICORDIA~MOIRA~MONA~MONICA~MONTSERRAT~MORGANA~MURIEL~MYRIAN~NADIA~NANCY~NAOMI~NARA~NARCISA~NARILLA~NASYA~NATACHA~NATALI~NATALIA~NATALIE~NATAN~NATASHA~NATIVIDAD~NAUSICA~NAZARENA~NEERA~NEFELE~NELIDA~NELLY~NEREO~NERINA~NICOLAZA~NICOLASA~NICOLE~NIDIA~NIEVES~NILDA~NINA~NINFA~NINI~NIOBE~NISSA~NOELIA~NOEMI~NORA~NORBERTA~NOREIA~NORMA~NOVELLA~NUNILA~NURIA~NURIEL~NYDIA~OBDULIA~OCTAVIA~ODA~ODELIA~ODETTE~ODILA~ODILIA~OFELIA~OITA~OLAYA~OLGA~OLIMPIA~OLINDA~OLIVA~OLIVIA~OLIMPIA~ONA~ONDINA~ONFALIA~ONIA~OPHELIA~ORACION~ORALIA~ORELLANA~ORFILIA~ORIA~ORIANA~ORQUIDEA~ORNELLA~OROSIA~OSEAS~OSIRIS~OSITA~OTILIA~OZANA~PALMA~PALMIRA~PALOMA~PAMELA~PAMPA~PANDORA~PAOLA~PASCUALA~PASTORA~PATRICIA~PATTY~PAULA~PAULINA~PAZ~PELAGIA~PELEAS~PENELOPE~PEREGRINA~PERLA~PERPETUA~PERSEFONE~PETRA~PETRONA~PETRONILA~PETUNIA~PIA~PIEDAD~PIERA~PILAR~POLIXENA~POMONA~PRAXEDES~PRECIOSA~PRESENTACION~PRIMAVERA~PRIMITIVA~PRIMULA~PRISCA~PRISCILA~PRUDENCIA~PULQUERIA~PURA~PURIFICACIONPUSINA~QUERINA~QUERUBINA~QUETA~QUIRINA~QUITERIA~RAFAELA~RAMONA~RAQUEL~REA~REBECA~REGINA~REINA~REMEDIOS~RENATA~RENE~RESTITUTA~RESURRECCION~RITA~ROBERTA~ROCIO~ROMANA~ROMILDA~ROMINA~ROQUELINA~ROSA~ROSALBA~ROSALIA~ROSALINDA~ROSAMUNDA~ROSANA~ROSARIO~ROSAURA~ROSELINA~ROSETA~ROSINA~ROSMARY~ROXANA~ROXANNE~ROXY~RUDY~RUFINA~RUT~RUTH~SABEL~SABINA~SABRINA~SALLY~SALOME~SALUD~SALVADORA~SALVIA~SALVINA~SAMANTA~SANDRA~SANDY~SANTINA~SARA~SARAI~SEGUNDA~SELENA~SELMA~SERAFINA~SERENA~SEVERA~SEVERINA~SHAKIRA~SHARON~SHEILA~SHIRLEY~SIBILA~SIDNEY~SILVANA~SILVIA~SIMONA~SOCORRO~SOFIA~SOLANA~SOLANGES~SOLEDAD~SONIA~SORAYA~SONSOLES~STEFANIA~STELLA~SUSANA~TABITA~TAIF~TAKARA~TALIA~TAMAR~TAMARA~TANIA~TARA~TANYA~TARSILA~TATIANA~TECLA~TELGA~TELMA~TEODORA~TEODOSIA~TEOFANIA~TERESA~TERESITA~TESSA~TETIS~TALIA~THAIS~TIARA~TIFFANY~TINA~TITANIA~TOMASA~TRINIDAD~TULA~TURA~ULIANA~ULLA~URSULA~VALENTINA~VALERIA~VALLIVANA~VALVANERA~VANESA~VEDIA~VELANIA~VELIA~VENTURA~VENUS~VERA~VERONICA~VICENTA~VICTORIA~VILMA~VINTILA~VIOLA~VIOLANTE~VIOLETA~VIRGINIA~VIRIDIANA~VISITACION~VIVIANA~WANDA~WENDY~XENIA~XIMENA~YANET~YANINA~YASMINA~YASMIN~YERMA~YOCASTA~YOCO~YOLANDA~ZAIRA~ZARA~ZENOBIA~ZITA~ZOE~ZORAIDA~ZULEICA~".contains(this.jTFnombre.getText())) {
            this.jCBsexo.setSelectedItem("Femenino");
        }
    }
    
    private void jTFnombreKeyReleased(final KeyEvent evt) {
        if (this.jTFnombre.getText().trim().equals("")) {
            this.jLasterisco1.setText("(*)");
        }
        else {
            this.jLasterisco1.setText("");
        }
    }
    
    private void jTFapellido1KeyReleased(final KeyEvent evt) {
        if (this.jTFapellido1.getText().trim().equals("")) {
            this.jLasterisco2.setText("(*)");
        }
        else {
            this.jLasterisco2.setText("");
        }
    }
    
    private void jTFapellido1FocusLost(final FocusEvent evt) {
        this.jTFapellido1.setText(this.jTFapellido1.getText().trim());
    }
    
    private void jTFapellido2FocusLost(final FocusEvent evt) {
        this.jTFapellido2.setText(this.jTFapellido2.getText().trim());
    }
    
    private void jTFclubFocusLost(final FocusEvent evt) {
        this.jTFclub.setText(this.jTFclub.getText().trim());
    }
    
    private void jTFclaseFocusLost(final FocusEvent evt) {
        this.jTFclase.setText(this.jTFclase.getText().trim());
    }
    
    private void jTFcorreoFocusLost(final FocusEvent evt) {
        this.jTFcorreo.setText(this.jTFcorreo.getText().trim());
    }
    
    private void jTFdniKeyReleased(final KeyEvent evt) {
        if (this.jTFdni.getText().length() == 8 && Integer.valueOf(this.jTFdni.getText()) > 10000000) {
            this.jTFletra.setText(String.valueOf("TRWAGMYFPDXBNJZSQVHLCKET".charAt(Integer.valueOf(this.jTFdni.getText()) % 23)));
        }
        else {
            this.jTFletra.setText("");
        }
    }
    
    private void jChBxAceptoItemStateChanged(final ItemEvent evt) {
        if (this.jLasterisco1.getText().equals("") & this.jLasterisco2.getText().equals("")) {
            this.jBsiguiente.setEnabled(this.jChBxAcepto.isSelected());
            this.jLmensaje.setText("");
        }
        else {
            this.jLmensaje.setText("DEBE RELLENAR EL NOMBRE Y EL PRIMER APELLIDO");
            this.jChBxAcepto.setSelected(false);
        }
    }
    
    private void jLabel4MouseClicked(final MouseEvent evt) {
        this.jFreglamento.setBounds(this.getLocationOnScreen().x, this.getLocationOnScreen().y, this.getWidth(), 625);
        this.jFreglamento.setVisible(true);
    }
    
    private void jChBxAceptoActionPerformed(final ActionEvent evt) {
    }
    
    private void jBcerrarMouseClicked(final MouseEvent evt) {
        this.jFreglamento.setVisible(false);
    }
    
    private void jBcerrarActionPerformed(final ActionEvent evt) {
    }
    
    private void jBsiguienteActionPerformed(final ActionEvent evt) {
    }
    
    private void jCBsexoItemStateChanged(final ItemEvent evt) {
        this.jCBa\u00f1oItemStateChanged(null);
    }
}
