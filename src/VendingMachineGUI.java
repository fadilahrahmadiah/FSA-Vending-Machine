import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineGUI extends JFrame {
    private final VendingMachineFSA fsa;
    private Map<Character, JButton> buttons;
    
    private JLabel stateLabel;
    private JLabel sequenceLabel;
    private JLabel resultLabel;
    
    public VendingMachineGUI(VendingMachineFSA fsa) {
        this.fsa = fsa;
        setTitle("Vending Machine FSA Simulator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        initializeComponents();
    }
    
    private void initializeComponents() {
        stateLabel = new JLabel("Current State: " + fsa.getCurrentState() + " - " + fsa.getStateDescription());
        stateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        sequenceLabel = new JLabel("Input Sequence: ");
        sequenceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        resultLabel = new JLabel("Please start the transaction");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel drinksPanel = createPanel("Drinks");
        JPanel paymentTypePanel = createPanel("Payment Type");
        JPanel paymentActionPanel = createPanel("Payment Process");
        JPanel finishPanel = createPanel("Finish");
        
        buttons = new HashMap<>();
        buttons.put('a', createImageButton("Olatte_Apple.png", 'a'));
        buttons.put('b', createImageButton("Olatte_Peach.png", 'b'));
        buttons.put('c', createImageButton("Olatte_Pear.png", 'c'));
        buttons.put('d', createImageButton("Olatte_Strawberry.png", 'd'));
        // buttons.put('a', createButton("Olatte Apple (a)", 'a'));
        // buttons.put('b', createButton("Olatte Peach (b)", 'b'));
        // buttons.put('c', createButton("Olatte Pear (c)", 'c'));
        // buttons.put('d', createButton("Olatte Strawberry (d)", 'd'));
        buttons.put('e', createButton("Cash (e)", 'e'));
        buttons.put('f', createButton("Non-Cash (f)", 'f'));
        buttons.put('g', createButton("BankNotes (g)", 'g'));
        buttons.put('h', createButton("Coins (h)", 'h'));
        buttons.put('i', createButton("E-money (i)", 'i'));
        buttons.put('j', createButton("Finish (j)", 'j'));
        
        drinksPanel.add(buttons.get('a'));
        drinksPanel.add(buttons.get('b'));
        drinksPanel.add(buttons.get('c'));
        drinksPanel.add(buttons.get('d'));
        
        paymentTypePanel.add(buttons.get('e'));
        paymentTypePanel.add(buttons.get('f'));
        
        paymentActionPanel.add(buttons.get('g'));
        paymentActionPanel.add(buttons.get('h'));
        paymentActionPanel.add(buttons.get('i'));
        
        finishPanel.add(buttons.get('j'));
        
        JPanel mainButtonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainButtonPanel.add(drinksPanel);
        mainButtonPanel.add(paymentTypePanel);
        mainButtonPanel.add(paymentActionPanel);
        mainButtonPanel.add(finishPanel);
        
        add(stateLabel, BorderLayout.NORTH);
        add(new JScrollPane(mainButtonPanel), BorderLayout.CENTER);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(sequenceLabel, BorderLayout.NORTH);
        southPanel.add(resultLabel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        JButton resetButton = new JButton("Reset Machine");
        resetButton.addActionListener(e -> resetMachine());
        add(resetButton, BorderLayout.EAST);
    }

    private JButton createImageButton(String imagePath, char symbol) {
    java.net.URL imgURL = getClass().getResource("/Images/" + imagePath);
    if (imgURL == null) {
        System.err.println("Could not find image: " + imagePath);
        return new JButton("Missing");
    }
    
    ImageIcon icon = new ImageIcon(imgURL);
    Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    icon = new ImageIcon(scaledImage);
    
    JButton button = new JButton(icon);
    button.addActionListener(e -> handleButtonPress(symbol));
    return button;
}


    private JPanel createPanel(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }
    
    private JButton createButton(String text, char symbol) {
        JButton button = new JButton(text);
        button.addActionListener(e -> handleButtonPress(symbol));
        return button;
    }
    
    private void handleButtonPress(char symbol) {
        fsa.processInput(symbol);
        
        sequenceLabel.setText("Input Sequence: " + fsa.getInputSequence());
        stateLabel.setText("Current State: " + fsa.getCurrentState() + " - " + fsa.getStateDescription());
        
        if (fsa.shouldShowResult()) {
            if (fsa.isValidSequence()) {
                resultLabel.setText("FINAL RESULT: VALID (matches FSA)");
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setText("FINAL RESULT: INVALID (does not match FSA)");
                resultLabel.setForeground(Color.RED);
            }
        } else if (fsa.isFinalState()) {
            resultLabel.setText("Transaction completed, press Reset to start again");
        } else {
            resultLabel.setText("Transaction in progress...");
            resultLabel.setForeground(Color.BLACK);
        }
    }
    
    private void resetMachine() {
        fsa.resetMachine();
        stateLabel.setText("Current State: " + fsa.getCurrentState() + " - " + fsa.getStateDescription());
        sequenceLabel.setText("Input Sequence: ");
        resultLabel.setText("Please start the transaction");
        resultLabel.setForeground(Color.BLACK);
    }
}