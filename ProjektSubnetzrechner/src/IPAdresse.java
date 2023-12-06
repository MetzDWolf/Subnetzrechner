import java.util.Scanner;
public class IPAdresse {
    // Deklarierung aller notwendigen Variablen, um eine IP-Adresse und Subnetzmaske zu initialisieren.
    private String oktette1;
    private String oktette2;
    private String oktette3;
    private String oktette4;
    private String subnetzmaskeOkt1;
    private String subnetzmaskeOkt2;
    private String subnetzmaskeOkt3;
    private String subnetzmaskeOkt4;

    // Eine Methode, die zur Eingabe der IP-Adresse aufruft.
    public void gibIpAdresseEin() {
        Scanner eingabe = new Scanner(System.in);
        boolean gueltigeEingabe = false;

        // Hier wird die Richtigkeit der Eingabe so lange geprüft, bis diese korrekt ist.
        while (!gueltigeEingabe) {
            System.out.print("Bitte geben Sie hier die IPv4-Adresse ein (Bsp.: 192.168.0.100): ");
            String ipAdresse = eingabe.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            // Hier erfolgt die Trennung der Eingabe, sodass eine gültige IP-Adresse entsteht. (Format xxx.xxx.xxx.xxx)
            if (pruefeEingabe(ipAdresse)) {
                String[] oktetten = ipAdresse.split("\\.");

                /* Wenn die Gesamtlänge aller Oktetten gleich 4 sind und die Oktetten einen Wert von 0 bis 255 haben,
                dann trifft der Fall zu.
                 */
                if (oktetten.length == 4 &&
                        istGueltigesOktett(oktetten[0]) &&
                        istGueltigesOktett(oktetten[1]) &&
                        istGueltigesOktett(oktetten[2]) &&
                        istGueltigesOktett(oktetten[3])) {
                    oktette1 = oktetten[0];
                    oktette2 = oktetten[1];
                    oktette3 = oktetten[2];
                    oktette4 = oktetten[3];

                    gueltigeEingabe = true;
                } else {
                    // Trifft der Fall nicht zu, wird eine Fehlermeldung ausgegeben.
                    System.out.println("FEHLER! Die IP-Adresse muss aus genau 4 Oktetten bestehen und" + "\n" +
                            "jedes Oktett kann nur Werte zwischen 0 und 255 haben!");
                    System.out.println("--------------------------------------------------------------------------------");
                }
            } else {
                System.out.println("FEHLER! Die IP-Adresse darf keine Buchstaben, Leerzeichen und Sonderzeichen (>>außer Punkt<<) enthalten.");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    // Eine Methode, die zur Eingabe der Subnetzmaske aufruft.
    public void gibSubnetzmaskeEin() {
        Scanner eingabe = new Scanner(System.in);
        boolean gueltigeEingabe = false;

        while (!gueltigeEingabe) {
            System.out.print("Bitte geben Sie hier die Subnetzmaske ein (Bsp.: 255.255.255.0): ");
            String subnetzmaske = eingabe.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            if (istGueltigeSubnetzmaske(subnetzmaske) && pruefeEingabe(subnetzmaske)) {
                String[] subnetzmaskeOktetten = subnetzmaske.split("\\.");
                if (subnetzmaskeOktetten.length == 4) {
                    subnetzmaskeOkt1 = subnetzmaskeOktetten[0];
                    subnetzmaskeOkt2 = subnetzmaskeOktetten[1];
                    subnetzmaskeOkt3 = subnetzmaskeOktetten[2];
                    subnetzmaskeOkt4 = subnetzmaskeOktetten[3];

                    if (istDefaultSubnetzmaske()) {
                        System.out.println("FEHLER! Die Subnetzmaske \"0.0.0.0\" ist ungültig. Es wird der gesamte IP-Adressraum umfasst.");
                        System.out.println("Bitte geben Sie eine gültige Subnetzmaske ein.");
                        System.out.println("--------------------------------------------------------------------------------");
                    } else {
                        gueltigeEingabe = true;
                    }
                }
            } else {
                System.out.println("FEHLER! Die Subnetzmaske darf keine Buchstaben, Leerzeichen oder" + "\n" + "Sonderzeichen (>>außer Punkt<<) enthalten und muss eine gültige Subnetzmaske sein.");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }
    /* Methode, die für die Berechnung der Netzadresse zuständig ist. Die Subnetzmaske wird wie eine Art Schablone
    auf die IP-Adresse gelegt, um Netz- und Hostadresse zu ermitteln.
     */
    public void berechneNetzId() {
        int netzIdOktett1 = Integer.parseInt(oktette1) & Integer.parseInt(subnetzmaskeOkt1);
        int netzIdOktett2 = Integer.parseInt(oktette2) & Integer.parseInt(subnetzmaskeOkt2);
        int netzIdOktett3 = Integer.parseInt(oktette3) & Integer.parseInt(subnetzmaskeOkt3);
        int netzIdOktett4 = Integer.parseInt(oktette4) & Integer.parseInt(subnetzmaskeOkt4);
        System.out.println("Netz-ID: " + netzIdOktett1 + "." + netzIdOktett2 + "." + netzIdOktett3 + "." + netzIdOktett4);
    }
    public void berechneBroadcastAdresse() {
        int subnetzmaskeInvertedOkt1 = 255 - Integer.parseInt(subnetzmaskeOkt1);
        int subnetzmaskeInvertedOkt2 = 255 - Integer.parseInt(subnetzmaskeOkt2);
        int subnetzmaskeInvertedOkt3 = 255 - Integer.parseInt(subnetzmaskeOkt3);
        int subnetzmaskeInvertedOkt4 = 255 - Integer.parseInt(subnetzmaskeOkt4);
        int broadcastOktett1 = Integer.parseInt(oktette1) | subnetzmaskeInvertedOkt1;
        int broadcastOktett2 = Integer.parseInt(oktette2) | subnetzmaskeInvertedOkt2;
        int broadcastOktett3 = Integer.parseInt(oktette3) | subnetzmaskeInvertedOkt3;
        int broadcastOktett4 = Integer.parseInt(oktette4) | subnetzmaskeInvertedOkt4;
        System.out.println("Broadcast-Adresse: " + broadcastOktett1 + "." + broadcastOktett2 + "." + broadcastOktett3 + "." + broadcastOktett4);
    }
    public void berechneAnzahlHosts() {
        long subnetzmaskeInvertedOkt1 = 255L - Integer.parseInt(subnetzmaskeOkt1);
        long subnetzmaskeInvertedOkt2 = 255L - Integer.parseInt(subnetzmaskeOkt2);
        long subnetzmaskeInvertedOkt3 = 255L - Integer.parseInt(subnetzmaskeOkt3);
        long subnetzmaskeInvertedOkt4 = 255L - Integer.parseInt(subnetzmaskeOkt4);

        // Addiere Netz-ID und Broadcast vor der Verschiebung der Oktetten
        long anzahlHosts = ((subnetzmaskeInvertedOkt1 << 24) + (subnetzmaskeInvertedOkt2 << 16) +
                (subnetzmaskeInvertedOkt3 << 8) + subnetzmaskeInvertedOkt4) + 1;

        System.out.println("Anzahl Hosts: " + anzahlHosts);

        // Berechne und zeige die Anzahl der tatsächlichen Hosts an
        long tatsaechlicheHosts = anzahlHosts - 2; // Subtrahiere Netzadresse und Broadcastadresse
        System.out.println("Anzahl tatsächlicher Hosts: " + tatsaechlicheHosts);
        System.out.println("--------------------------------------------------------------------------------");
    }
    // Methode, die überprüft, ob die Eingabe dem vorgegebenen Muster entspricht (0 - 9 und Punkt)
    private boolean pruefeEingabe(String eingabe) {
        return eingabe.matches("[0-9.]+");
    }
    // Methode, die überprüft, ob die eingegebene Subnetzmaske einer gültigen Subnetzmaske entspricht.
    private boolean istGueltigeSubnetzmaske(String subnetzmaske) {
        String[] gueltigeMaske = {"255.255.255.255", "255.255.255.254", "255.255.255.252", "255.255.255.248", "255.255.255.240", "255.255.255.224", "255.255.255.192", "255.255.255.128", "255.255.255.0", "255.255.254.0", "255.255.252.0", "255.255.248.0", "255.255.240.0", "255.255.224.0", "255.255.192.0", "255.255.128.0", "255.255.0.0", "255.254.0.0", "255.252.0.0", "255.248.0.0", "255.240.0.0", "255.224.0.0", "255.192.0.0", "255.128.0.0", "255.0.0.0", "254.0.0.0", "252.0.0.0", "248.0.0.0", "240.0.0.0", "224.0.0.0", "192.0.0.0", "128.0.0.0", "0.0.0.0"};

        for (String richtigeMaske : gueltigeMaske) {
            if (subnetzmaske.equals(richtigeMaske)) {
                return true;
            }
        }

        return false;
    }
    // Methode, die überprüft, ob der Benutzer die Subnetzmaske 0.0.0.0 eingibt (deckt kompletten Ip-Adressraum ab)
    private boolean istDefaultSubnetzmaske() {
        return subnetzmaskeOkt1.equals("0") &&
                subnetzmaskeOkt2.equals("0") &&
                subnetzmaskeOkt3.equals("0") &&
                subnetzmaskeOkt4.equals("0");
    }
    // Methode, die überprüft, ob die Eingabe der Oktette zulässig ist (0 - 255)
    private boolean istGueltigesOktett(String oktett) {
        try {
            int wert = Integer.parseInt(oktett);
            return wert >= 0 && wert <= 255;
        } catch (NumberFormatException e) {
            return false; // Wenn die Umwandlung in eine Zahl fehlschlägt, ist es kein gültiges Oktett
        }
    }
    public void willkommensNachricht() {
        System.out.println("+------------------------------------------------------------------------------+" + "\n" +
                "|                             SUBNETZRECHNER                                   |" + "\n" +
                "+------------------------------------------------------------------------------+");
    }
}
