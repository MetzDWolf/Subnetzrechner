public class Calculate {
    private final IPAddress ip;
    private final Subnetmask subnetmask;

    public Calculate(IPAddress ip, Subnetmask subnetMask) {
        this.ip = ip;
        this.subnetmask = subnetMask;
    }

    public void calcNetID() {
        int netzIdOktett1 = Integer.parseInt(ip.getOctec1()) & Integer.parseInt(subnetmask.getSubnetmaskOctec1());
        int netzIdOktett2 = Integer.parseInt(ip.getOctec2()) & Integer.parseInt(subnetmask.getSubnetzmaskeOctet2());
        int netzIdOktett3 = Integer.parseInt(ip.getOctec3()) & Integer.parseInt(subnetmask.getSubnetmaskOctet3());
        int netzIdOktett4 = Integer.parseInt(ip.getOctec4()) & Integer.parseInt(subnetmask.getSubnetmaskOctec4());
        System.out.println("Netz-ID: " + netzIdOktett1 + "." + netzIdOktett2 + "." + netzIdOktett3 + "." + netzIdOktett4);
    }

    public void calcBCAddress() {
        int subnetmaskInvertedOct1 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctec1());
        int subnetmaskInvertedOct2 = 255 - Integer.parseInt(subnetmask.getSubnetzmaskeOctet2());
        int subnetmaskInvertedOct3 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctec3());
        int subnetmaskInvertedOct4 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctec4());

        int bcOctec1 = Integer.parseInt(ip.getOctec1()) | subnetmaskInvertedOct1;
        int bcOctec2 = Integer.parseInt(ip.getOctec2()) | subnetmaskInvertedOct2;
        int bcOctec3 = Integer.parseInt(ip.getOctec3()) | subnetmaskInvertedOct3;
        int bcOctec4 = Integer.parseInt(ip.getOctec4()) | subnetmaskInvertedOct4;

        System.out.println("Broadcast-Adresse: " + bcOctec1 + "." + bcOctec2 + "." + bcOctec3 + "." + bcOctec4);
    }

    public void calcNumberHosts() {
        long subnetzmaskeInvertedOkt1 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctec1());
        long numberHosts = getNumberHosts(subnetzmaskeInvertedOkt1);

        System.out.println("Anzahl Hosts: " + numberHosts);

        // Berechne und zeige die Anzahl der tatsächlichen Hosts an
        long actualHosts = numberHosts - 2; // Subtrahiere Netzadresse und Broadcastadresse
        System.out.println("Anzahl vergebbare Hosts: " + actualHosts);
        System.out.println("--------------------------------------------------------------------------------");
    }

    private long getNumberHosts(long subnetzmaskeInvertedOkt1) {
        long subnetzmaskeInvertedOkt2 = 255L - Long.parseLong(subnetmask.getSubnetzmaskeOkt2());
        long subnetzmaskeInvertedOkt3 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctec3());
        long subnetzmaskeInvertedOkt4 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctec4());

        // Addiere Netz-ID und Broadcast vor der Verschiebung der Oktetten
        return ((subnetzmaskeInvertedOkt1 << 24) + (subnetzmaskeInvertedOkt2 << 16) +
                (subnetzmaskeInvertedOkt3 << 8) + subnetzmaskeInvertedOkt4) + 1;
    }
    // Methode, die überprüft, ob die eingegebene Subnetzmaske einer gültigen Subnetzmaske entspricht.
}
