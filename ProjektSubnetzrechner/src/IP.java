import java.util.Scanner;

public class IP {
    private String oktette1;
    private String oktette2;
    private String oktette3;
    private String oktette4;

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

    public String getOktette1() {
        return oktette1;
    }

    public String getOktette2() {
        return oktette2;
    }

    public String getOktette3() {
        return oktette3;
    }

    public String getOktette4() {
        return oktette4;
    }

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
