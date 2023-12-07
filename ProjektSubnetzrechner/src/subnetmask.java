import java.util.Scanner;

public class subnetmask {
    private String subnetmaskOctec1;
    private String subnetmaskOctec2;
    private String subnetmaskOctec3;
    private String subnetmaskOctec4;

    public void inputSubnetmask() {
        Scanner input = new Scanner(System.in);
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Bitte geben Sie hier die Subnetzmaske ein (Bsp.: 255.255.255.0): ");
            String subnetmask = input.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            if (isValidSubnetmask(subnetmask) && checkInput(subnetmask)) {
                String[] subnetmaskOctects = subnetmask.split("\\.");
                if (subnetmaskOctects.length == 4) {
                    subnetmaskOctec1 = subnetmaskOctects[0];
                    subnetmaskOctec2 = subnetmaskOctects[1];
                    subnetmaskOctec3 = subnetmaskOctects[2];
                    subnetmaskOctec4 = subnetmaskOctects[3];

                    if (isDefaultSubnetmask()) {
                        System.out.println("FEHLER! Die Subnetzmaske \"0.0.0.0\" ist ungültig. Es wird der gesamte IP-Adressraum umfasst.");
                        System.out.println("Bitte geben Sie eine gültige Subnetzmaske ein.");
                        System.out.println("--------------------------------------------------------------------------------");
                    } else {
                        isValidInput = true;
                    }
                }
            } else {
                System.out.println("FEHLER! Die Subnetzmaske darf keine Buchstaben, Leerzeichen oder" + "\n" + "Sonderzeichen (>>außer Punkt<<) enthalten und muss eine gültige Subnetzmaske sein.");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    public String getSubnetmaskOctec1() {
        return subnetmaskOctec1;
    }

    public String getSubnetzmaskeOkt2() {
        return subnetmaskOctec2;
    }

    public String getSubnetmaskOctec3() {
        return subnetmaskOctec3;
    }

    public String getSubnetmaskOctec4() {
        return subnetmaskOctec4;
    }
    private boolean checkInput(String input) {
        return input.matches("[0-9.]+");
    }
    // Methode, die überprüft, ob die eingegebene Subnetzmaske einer gültigen Subnetzmaske entspricht.
    private boolean isValidSubnetmask(String subnetmask) {
        String[] validMask = {
                "255.255.255.255", "255.255.255.254", "255.255.255.252",
                "255.255.255.248", "255.255.255.240", "255.255.255.224",
                "255.255.255.192", "255.255.255.128", "255.255.255.0",
                "255.255.254.0", "255.255.252.0", "255.255.248.0",
                "255.255.240.0", "255.255.224.0", "255.255.192.0",
                "255.255.128.0", "255.255.0.0", "255.254.0.0", "255.252.0.0",
                "255.248.0.0", "255.240.0.0", "255.224.0.0", "255.192.0.0",
                "255.128.0.0", "255.0.0.0", "254.0.0.0", "252.0.0.0",
                "248.0.0.0", "240.0.0.0", "224.0.0.0", "192.0.0.0",
                "128.0.0.0", "0.0.0.0"};

        for (String correctMask : validMask) {
            if (subnetmask.equals(correctMask)) {
                return true;
            }
        }

        return false;
    }
    // Methode, die überprüft, ob der Benutzer die Subnetzmaske 0.0.0.0 eingibt (deckt kompletten Ip-Adressraum ab)
    private boolean isDefaultSubnetmask() {
        return subnetmaskOctec1.equals("0") &&
                subnetmaskOctec2.equals("0") &&
                subnetmaskOctec3.equals("0") &&
                subnetmaskOctec4.equals("0");
    }
    // Methode, die überprüft, ob die Eingabe der Oktette zulässig ist (0 - 255)
    private boolean validOctec(String octec) {
        try {
            int wert = Integer.parseInt(octec);
            return wert >= 0 && wert <= 255;
        } catch (NumberFormatException e) {
            return false; // Wenn die Umwandlung in eine Zahl fehlschlägt, ist es kein gültiges Oktett
        }
    }
}
