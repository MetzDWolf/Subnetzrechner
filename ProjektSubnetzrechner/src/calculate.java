public class calculate {
    private final ipaddress ip;
    private final subnetmask subnetmask;

    public calculate(ipaddress ip, subnetmask subnetMask) {
        this.ip = ip;
        this.subnetmask = subnetMask;
    }

    public void calcNetID() {
        int netzIdOktett1 = Integer.parseInt(ip.getOctec1()) & Integer.parseInt(subnetmask.getSubnetmaskOctec1());
        int netzIdOktett2 = Integer.parseInt(ip.getOctec2()) & Integer.parseInt(subnetmask.getSubnetzmaskeOkt2());
        int netzIdOktett3 = Integer.parseInt(ip.getOctec3()) & Integer.parseInt(subnetmask.getSubnetmaskOctec3());
        int netzIdOktett4 = Integer.parseInt(ip.getOctec4()) & Integer.parseInt(subnetmask.getSubnetmaskOctec4());
        System.out.println("Netz-ID: " + netzIdOktett1 + "." + netzIdOktett2 + "." + netzIdOktett3 + "." + netzIdOktett4);
    }

    public void calcBCAddress() {
        int subnetmaskInvertedOct1 = 255 - Integer.parseInt(subnetmask.getSubnetmaskOctec1());
        int subnetmaskInvertedOct2 = 255 - Integer.parseInt(subnetmask.getSubnetzmaskeOkt2());
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
        long subnetzmaskeInvertedOkt2 = 255L - Long.parseLong(subnetmask.getSubnetzmaskeOkt2());
        long subnetzmaskeInvertedOkt3 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctec3());
        long subnetzmaskeInvertedOkt4 = 255L - Long.parseLong(subnetmask.getSubnetmaskOctec4());

        // Addiere Netz-ID und Broadcast vor der Verschiebung der Oktetten
        long numberHosts = ((subnetzmaskeInvertedOkt1 << 24) + (subnetzmaskeInvertedOkt2 << 16) +
                (subnetzmaskeInvertedOkt3 << 8) + subnetzmaskeInvertedOkt4) + 1;

        System.out.println("Anzahl Hosts: " + numberHosts);

        // Berechne und zeige die Anzahl der tatsächlichen Hosts an
        long actualHosts = numberHosts - 2; // Subtrahiere Netzadresse und Broadcastadresse
        System.out.println("Anzahl vergebbare Hosts: " + actualHosts);
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Methode, die überprüft, ob die Eingabe dem vorgegebenen Muster entspricht (0 - 9 und Punkt)
    private boolean checkInput(String input) {
        return input.matches("[0-9.]+");
    }
    // Methode, die überprüft, ob die eingegebene Subnetzmaske einer gültigen Subnetzmaske entspricht.
    private boolean isValidSubnetmask(String subnetmask) {
        String[] validMask = {"255.255.255.255", "255.255.255.254", "255.255.255.252",
                "255.255.255.248", "255.255.255.240", "255.255.255.224",
                "255.255.255.192", "255.255.255.128", "255.255.255.0",
                "255.255.254.0", "255.255.252.0", "255.255.248.0",
                "255.255.240.0", "255.255.224.0", "255.255.192.0",
                "255.255.128.0", "255.255.0.0", "255.254.0.0",
                "255.252.0.0", "255.248.0.0", "255.240.0.0", "255.224.0.0",
                "255.192.0.0", "255.128.0.0", "255.0.0.0", "254.0.0.0", "252.0.0.0",
                "248.0.0.0", "240.0.0.0", "224.0.0.0", "192.0.0.0", "128.0.0.0", "0.0.0.0"};

        for (String correctMask : validMask) {
            if (subnetmask.equals(correctMask)) {
                return true;
            }
        }
        return false;
    }
}
