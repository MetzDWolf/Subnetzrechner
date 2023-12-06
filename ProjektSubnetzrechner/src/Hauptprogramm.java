public class Hauptprogramm {


    public static void main(String[] args) {

        // Erstelle Instanzen der Klassen
        IP ip = new IP();
        Subnetzmaske subnetzmaske = new Subnetzmaske();
        Berechnung berechnung = new Berechnung(ip, subnetzmaske);

        // Rufe die Methoden auf, um Daten einzugeben
        ip.willkommensNachricht();
        ip.gibIpAdresseEin();
        subnetzmaske.gibSubnetzmaskeEin();

        // Rufe die Berechnungsmethoden auf
        berechnung.berechneNetzId();
        berechnung.berechneBroadcastAdresse();
        berechnung.berechneAnzahlHosts();
    }
}
