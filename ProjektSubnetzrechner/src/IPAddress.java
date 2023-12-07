import java.util.Scanner;
public class IPAddress {
    private String octec1;
    private String octec2;
    private String octec3;
    private String octec4;
    public void inputIP() {
        Scanner input = new Scanner(System.in);
        boolean isValidInput = false;

        // Hier wird die Richtigkeit der Eingabe so lange geprüft, bis diese korrekt ist.
        while (!isValidInput) {
            System.out.print("Bitte geben Sie hier die IPv4-Adresse ein (Bsp.: 192.168.0.100): ");
            String ipAddress = input.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            // Hier erfolgt die Trennung der Eingabe, sodass eine gültige IP-Adresse entsteht. (Format xxx.xxx.xxx.xxx)
            if (checkInput(ipAddress)) {
                String[] octects = ipAddress.split("\\.");

                /* Wenn die Gesamtlänge aller Oktetten gleich 4 sind und die Oktetten einen Wert von 0 bis 255 haben,
                dann trifft der Fall zu.
                 */
                if (octects.length == 4 &&
                        isValidOctects(octects[0]) &&
                        isValidOctects(octects[1]) &&
                        isValidOctects(octects[2]) &&
                        isValidOctects(octects[3])) {
                    octec1 = octects[0];
                    octec2 = octects[1];
                    octec3 = octects[2];
                    octec4 = octects[3];

                    isValidInput = true;
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
    public String getOctec1() {
        return octec1;
    }

    public String getOctec2() {
        return octec2;
    }

    public String getOctec3() {
        return octec3;
    }

    public String getOctec4() {
        return octec4;
    }
    private boolean checkInput(String eingabe) {
        return eingabe.matches("[0-9.]+");
    }
    // Methode, die überprüft, ob die Eingabe der Oktette zulässig ist (0 - 255)
    private boolean isValidOctects(String octet) {
        try {
            int value = Integer.parseInt(octet);
            return value >= 0 && value <= 255;
        } catch (NumberFormatException e) {
            return false; // Wenn die Umwandlung in eine Zahl fehlschlägt, ist es kein gültiges Oktett
        }
    }
    public void welcomeMessage() {
        System.out.println("""
                +------------------------------------------------------------------------------+
                |                             SUBNETZRECHNER                                   |
                +------------------------------------------------------------------------------+""");
    }
}
