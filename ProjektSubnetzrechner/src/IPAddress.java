import java.util.Scanner;

public class IPAddress {
    private String octet1;
    private String octet2;
    private String octet3;
    private String octet4;

    // Methode zur Eingabe und Überprüfung der IPv4-Adresse
    public void inputIP() {
        Scanner input = new Scanner(System.in);
        boolean isValidInput = false;

        // Die Richtigkeit der Eingabe wird so lange überprüft, bis sie korrekt ist.
        while (!isValidInput) {
            System.out.print("Bitte geben Sie hier die IPv4-Adresse ein (Bsp.: 192.168.0.100): ");
            String ipAddress = input.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            // Trennung der Eingabe, um eine gültige IP-Adresse zu erstellen (Format xxx.xxx.xxx.xxx)
            if (checkInput(ipAddress)) {
                String[] octets = ipAddress.split("\\.");

                /* Wenn die Gesamtlänge aller Oktetten gleich 4 ist und die Oktetten einen Wert von 0 bis 255 haben,
                dann trifft der Fall zu.
                 */
                if (octets.length == 4 &&
                        isValidOctets(octets[0]) &&
                        isValidOctets(octets[1]) &&
                        isValidOctets(octets[2]) &&
                        isValidOctets(octets[3])) {
                    octet1 = octets[0];
                    octet2 = octets[1];
                    octet3 = octets[2];
                    octet4 = octets[3];

                    isValidInput = true;
                } else {
                    // Trifft der Fall nicht zu, wird eine Fehlermeldung ausgegeben.
                    System.out.println("Keine gültige IP-Adresse!");
                    System.out.println("--------------------------------------------------------------------------------");
                }
            } else {
                System.out.println("Keine gültige IP-Adresse!");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    // Getter-Methoden für die Oktette der IP-Adresse
    public String getOctet1() {
        return octet1;
    }

    public String getOctet2() {
        return octet2;
    }

    public String getOctet3() {
        return octet3;
    }

    public String getOctet4() {
        return octet4;
    }

    // Methode zur Überprüfung, ob die Eingabe nur Zahlen und Punkte enthält
    private boolean checkInput(String input) {
        return input.matches("[0-9.]+");
    }

    // Methode, die überprüft, ob die Eingabe der Oktette zulässig ist (0 - 255)
    private boolean isValidOctets(String octet) {
        try {
            int value = Integer.parseInt(octet);
            return value >= 0 && value <= 255;
        } catch (NumberFormatException e) {
            return false; // Wenn die Umwandlung in eine Zahl fehlschlägt, ist es kein gültiges Oktett
        }
    }

    // Methode zur Ausgabe einer Begrüßungsnachricht
    public void welcomeMessage() {
        System.out.println("""
                +------------------------------------------------------------------------------+
                |                             SUBNETZRECHNER                                   |
                +------------------------------------------------------------------------------+""");
    }
}
