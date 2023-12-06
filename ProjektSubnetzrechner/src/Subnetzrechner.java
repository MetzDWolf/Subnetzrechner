public class Subnetzrechner {
    public static void main(String[] args) {
        IPAdresse ipAdresse = new IPAdresse();

        ipAdresse.willkommensNachricht();
        ipAdresse.gibIpAdresseEin();
        ipAdresse.gibSubnetzmaskeEin();
        ipAdresse.berechneNetzId();
        ipAdresse.berechneBroadcastAdresse();
        ipAdresse.berechneAnzahlHosts();
    }
}
