public class main {


    public static void main(String[] args) {

        // Erstelle Instanzen der Klassen
        ipaddress ip = new ipaddress();
        subnetmask subnetmask = new subnetmask();
        calculate calc = new calculate(ip, subnetmask);

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
