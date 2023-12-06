public class Berechnung {
    private final IP ip;
    private final Subnetzmaske subnetzmaske;

    public Berechnung(IP ip, Subnetzmaske subnetzmaske) {
        this.ip = ip;
        this.subnetzmaske = subnetzmaske;
    }

    public void berechneNetzId() {
        int netzIdOktett1 = Integer.parseInt(ip.getOktette1()) & Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt1());
        int netzIdOktett2 = Integer.parseInt(ip.getOktette2()) & Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt2());
        int netzIdOktett3 = Integer.parseInt(ip.getOktette3()) & Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt3());
        int netzIdOktett4 = Integer.parseInt(ip.getOktette4()) & Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt4());
        System.out.println("Netz-ID: " + netzIdOktett1 + "." + netzIdOktett2 + "." + netzIdOktett3 + "." + netzIdOktett4);
    }

    public void berechneBroadcastAdresse() {
        int subnetzmaskeInvertedOkt1 = 255 - Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt1());
        int subnetzmaskeInvertedOkt2 = 255 - Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt2());
        int subnetzmaskeInvertedOkt3 = 255 - Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt3());
        int subnetzmaskeInvertedOkt4 = 255 - Integer.parseInt(subnetzmaske.getSubnetzmaskeOkt4());

        int broadcastOktett1 = Integer.parseInt(ip.getOktette1()) | subnetzmaskeInvertedOkt1;
        int broadcastOktett2 = Integer.parseInt(ip.getOktette2()) | subnetzmaskeInvertedOkt2;
        int broadcastOktett3 = Integer.parseInt(ip.getOktette3()) | subnetzmaskeInvertedOkt3;
        int broadcastOktett4 = Integer.parseInt(ip.getOktette4()) | subnetzmaskeInvertedOkt4;

        System.out.println("Broadcast-Adresse: " + broadcastOktett1 + "." + broadcastOktett2 + "." + broadcastOktett3 + "." + broadcastOktett4);
    }

    public void berechneAnzahlHosts() {
        long subnetzmaskeInvertedOkt1 = 255L - Long.parseLong(subnetzmaske.getSubnetzmaskeOkt1());
        long subnetzmaskeInvertedOkt2 = 255L - Long.parseLong(subnetzmaske.getSubnetzmaskeOkt2());
        long subnetzmaskeInvertedOkt3 = 255L - Long.parseLong(subnetzmaske.getSubnetzmaskeOkt3());
        long subnetzmaskeInvertedOkt4 = 255L - Long.parseLong(subnetzmaske.getSubnetzmaskeOkt4());

        // Addiere Netz-ID und Broadcast vor der Verschiebung der Oktetten
        long anzahlHosts = ((subnetzmaskeInvertedOkt1 << 24) + (subnetzmaskeInvertedOkt2 << 16) +
                (subnetzmaskeInvertedOkt3 << 8) + subnetzmaskeInvertedOkt4) + 1;

        System.out.println("Anzahl Hosts: " + anzahlHosts);

        // Berechne und zeige die Anzahl der tatsächlichen Hosts an
        long tatsaechlicheHosts = anzahlHosts - 2; // Subtrahiere Netzadresse und Broadcastadresse
        System.out.println("Anzahl vergebbare Hosts: " + tatsaechlicheHosts);
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

    // Weitere Methoden für Berechnungen
}
