public class Subnetcalculator {


    public static void main(String[] args) {

        // Erstelle Instanzen der Klassen
        IPAddress ip = new IPAddress();
        Subnetmask subnetmask = new Subnetmask();
        Calculate calc = new Calculate(ip, subnetmask);

        // Rufe die Methoden auf, um Daten einzugeben
        ip.welcomeMessage();
        ip.inputIP();
        subnetmask.inputSubnetmask();

        // Rufe die Berechnungsmethoden auf
        calc.calcNetID();
        calc.calcBCAddress();
        calc.calcNumberHosts();
    }
}
