import java.util.Scanner;

public class Subnetmask {
    private String subnetmaskOctet1;
    private String subnetmaskOctet2;
    private String subnetmaskOctet3;
    private String subnetmaskOctet4;

    // Methode zur Eingabe und Überprüfung der Subnetzmaske
    public void inputSubnetmask() {
        Scanner input = new Scanner(System.in);
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Bitte geben Sie hier die Subnetzmaske ein (Bsp.: 255.255.255.0): ");
            String subnetmask = input.nextLine();
            System.out.println("--------------------------------------------------------------------------------");

            // Überprüfe, ob die eingegebene Subnetzmaske gültig ist und das richtige Format hat
            if (isValidSubnetmask(subnetmask) && checkInput(subnetmask)) {
                String[] subnetmaskOctects = subnetmask.split("\\.");
                if (subnetmaskOctects.length == 4) {
                    // Trenne die Subnetzmaske in Oktette auf
                    subnetmaskOctet1 = subnetmaskOctects[0];
                    subnetmaskOctet2 = subnetmaskOctects[1];
                    subnetmaskOctet3 = subnetmaskOctects[2];
                    subnetmaskOctet4 = subnetmaskOctects[3];

                    // Überprüfe, ob die Subnetzmaske die Standard-Subnetzmaske ist
                    if (isDefaultSubnetmask()) {
                        System.out.println("Die Subnetzmaske \"0.0.0.0\" ist zum Rechnen ungültig.");
                        System.out.println("Bitte geben Sie eine gültige Subnetzmaske ein.");
                        System.out.println("--------------------------------------------------------------------------------");
                    } else {
                        isValidInput = true; // Beende die Schleife, wenn die Eingabe gültig ist
                    }
                }
            } else {
                System.out.println("Fehler. Keine gültige Subnetzmaske!");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    }

    // Getter-Methoden für die Subnetzmaske-Oktette
    public String getSubnetmaskOctet1() {
        return subnetmaskOctet1;
    }

    public String getSubnetmaskOctet2() {
        return subnetmaskOctet2;
    }

    public String getSubnetmaskOctet3() {
        return subnetmaskOctet3;
    }

    public String getSubnetmaskOctet4() {
        return subnetmaskOctet4;
    }

    // Methode zur Überprüfung, ob die Eingabe nur Zahlen und Punkte enthält
    private boolean checkInput(String input) {
        return input.matches("[0-9.]+");
    }

    // Methode, die überprüft, ob die eingegebene Subnetzmaske einer gültigen Subnetzmaske entspricht
    private boolean isValidSubnetmask(String subnetmask) {
        String[] validMask = {
                "255.255.255.255", "255.255.255.254", "255.255.255.252",
                // ... (Liste der gültigen Subnetzmasken)
                "128.0.0.0", "0.0.0.0"};

        for (String correctMask : validMask) {
            if (subnetmask.equals(correctMask)) {
                return true;
            }
        }

        return false;
    }

    // Methode, die überprüft, ob der Benutzer die Subnetzmaske 0.0.0.0 eingibt (deckt kompletten IP-Adressraum ab)
    private boolean isDefaultSubnetmask() {
        return subnetmaskOctet1.equals("0") &&
                subnetmaskOctet2.equals("0") &&
                subnetmaskOctet3.equals("0") &&
                subnetmaskOctet4.equals("0");
    }
}
