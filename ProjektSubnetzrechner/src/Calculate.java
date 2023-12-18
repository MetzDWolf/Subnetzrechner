public class Calculate {
    private final IPAddress ip;
    private final Subnetmask subnetmask;

    // Konstruktor, der Instanzen von IPAddress und Subnetmask erhält
    public Calculate(IPAddress ip, Subnetmask subnetMask) {
        this.ip = ip;
        this.subnetmask = subnetMask;
    }

    // Methode zur Berechnung der Netzwerk-ID
    public void calcNetID() {
        // Holen der Oktetten der IP-Adresse und der Subnetzmaske
        int netIDOctet1 = Integer.parseInt(ip.getOctet1()) & Integer.parseInt(subnetmask.getSubnetmaskOctet1());
        int netIDOctet2 = Integer.parseInt(ip.getOctet2()) & Integer.parseInt(subnetmask.getSubnetmaskOctet2());
        int netIDOctet3 = Integer.parseInt(ip.getOctet3()) & Integer.parseInt(subnetmask.getSubnetmaskOctet3());
        int netIDOctet4 = Integer.parseInt(ip.getOctet4()) & Integer.parseInt(subnetmask.getSubnetmaskOctet4());

        // Ausgabe der Netzwerk-ID
        System.out.println("Netz-ID: " + netIDOctet1 + "." + netIDOctet2 + "." + netIDOctet3 + "." + netIDOctet4);
    }

    // Methode zur Berechnung der Broadcast-Adresse
    public void calcBCAddress() {
        // Berechnung der invertierten Subnetzmaske
        int subnetmaskInvertedOct1 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctet1());
        int subnetmaskInvertedOct2 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctet2());
        int subnetmaskInvertedOct3 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctet3());
        int subnetmaskInvertedOct4 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctet4());

        // Berechnung der Broadcast-Adresse
        int bcOctet1 = Integer.parseInt(ip.getOctet1()) | subnetmaskInvertedOct1;
        int bcOctet2 = Integer.parseInt(ip.getOctet2()) | subnetmaskInvertedOct2;
        int bcOctet3 = Integer.parseInt(ip.getOctet3()) | subnetmaskInvertedOct3;
        int bcOctet4 = Integer.parseInt(ip.getOctet4()) | subnetmaskInvertedOct4;

        // Ausgabe der Broadcast-Adresse
        System.out.println("Broadcast-Adresse: " + bcOctet1 + "." + bcOctet2 + "." + bcOctet3 + "." + bcOctet4);
    }

    // Methode zur Berechnung der Anzahl der Hosts im Netzwerk
    public void calcNumberHosts() {
        // Berechnung der invertierten Subnetzmaske für das erste Oktett
        long subnetmaskInvertedOct1 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctet1());

        // Aufruf der Hilfsmethode zur Berechnung der Anzahl der Hosts
        long numberHosts = getNumberHosts(subnetmaskInvertedOct1);

        // Ausgabe der Gesamtanzahl der Hosts
        System.out.println("Anzahl Hosts: " + numberHosts);

        // Berechnung und Ausgabe der tatsächlich verfügbaren Hosts (abzüglich Netzadresse und Broadcastadresse)
        long actualHosts = numberHosts - 2;
        System.out.println("Anzahl vergebbare Hosts: " + actualHosts);
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Hilfsmethode zur Berechnung der Anzahl der Hosts
    private long getNumberHosts(long subnetmaskInvertedOct1) {
        // Berechnung der invertierten Subnetzmaske für die weiteren Oktetten
        long subnetmaskInvertedOct2 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctet2());
        long subnetmaskInvertedOct3 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctet3());
        long subnetmaskInvertedOct4 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctet4());

        // Addieren der invertierten Oktetten vor der Verschiebung
        return ((subnetmaskInvertedOct1 << 24) + (subnetmaskInvertedOct2 << 16) +
                (subnetmaskInvertedOct3 << 8) + subnetmaskInvertedOct4) + 1;
    }
}
