import java.util.Scanner;

public class Subnetzmaske {
    private String subnetzmaskeOkt1;
    private String subnetzmaskeOkt2;
    private String subnetzmaskeOkt3;
    private String subnetzmaskeOkt4;

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

    public String getSubnetzmaskeOkt1() {
        return subnetzmaskeOkt1;
    }

    public String getSubnetzmaskeOkt2() {
        return subnetzmaskeOkt2;
    }

    public String getSubnetzmaskeOkt3() {
        return subnetzmaskeOkt3;
    }

    public String getSubnetzmaskeOkt4() {
        return subnetzmaskeOkt4;
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
}
