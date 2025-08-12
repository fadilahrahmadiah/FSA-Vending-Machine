import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendingMachineFSA fsa = new VendingMachineFSA();
            VendingMachineGUI gui = new VendingMachineGUI(fsa);
            gui.setVisible(true);
        });
    }
}