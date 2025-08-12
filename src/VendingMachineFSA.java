import java.util.*;

public class VendingMachineFSA {
    protected static final Set<String> states = new HashSet<>(Arrays.asList(
        "q0","q1","q2","q3","q4","q5","q6","q7","q8","q9","q10"
    ));
    
    protected static final Set<Character> inputSymbols = new HashSet<>(Arrays.asList(
        'a','b','c','d','e','f','g','h','i','j'
    ));
    
    protected static final String START_STATE = "q0";
    protected static final Set<String> FINAL_STATES = new HashSet<>(Collections.singletonList("q10"));
    
    protected static final Map<String, Map<Character, String>> transitionFunction = new HashMap<>();
    protected static final Map<String, String> stateDescriptions = new HashMap<>();
    
    static {
        stateDescriptions.put("q0", "Start State - Choose a Drink");
        stateDescriptions.put("q1", "Olatte Apple Selected");
        stateDescriptions.put("q2", "Olatte Peach Selected");
        stateDescriptions.put("q3", "Olatte Pear Selected");
        stateDescriptions.put("q4", "Olatte Strawberry Selected");
        stateDescriptions.put("q5", "Cash Payment");
        stateDescriptions.put("q6", "Non-Cash Payment");
        stateDescriptions.put("q7", "Insert Banknotes");
        stateDescriptions.put("q8", "Insert Coins");
        stateDescriptions.put("q9", "E-money Processing");
        stateDescriptions.put("q10", "Transaction Completed");
        
        // Transitions
        addTransition("q0", 'a', "q1");
        addTransition("q0", 'b', "q2");
        addTransition("q0", 'c', "q3");
        addTransition("q0", 'd', "q4");

        addTransition("q1", 'e', "q5");
        addTransition("q2", 'e', "q5");
        addTransition("q3", 'e', "q5");
        addTransition("q4", 'e', "q5");

        addTransition("q1", 'f', "q6");
        addTransition("q2", 'f', "q6");
        addTransition("q3", 'f', "q6");
        addTransition("q4", 'f', "q6");

        addTransition("q5", 'g', "q7");
        addTransition("q7", 'g', "q7");

        addTransition("q5", 'h', "q8");
        addTransition("q8", 'h', "q8");

        addTransition("q6", 'i', "q9");

        addTransition("q7", 'j', "q10");
        addTransition("q8", 'j', "q10");
        addTransition("q9", 'j', "q10");
    }
    
    protected static void addTransition(String fromState, char input, String toState) {
        transitionFunction
            .computeIfAbsent(fromState, k -> new HashMap<>())
            .put(input, toState);
    }
    
    protected String currentState;
    protected StringBuilder inputSequence;
    protected boolean isValidSequence;
    
    public VendingMachineFSA() {
        resetMachine();
    }
    
    public void processInput(char symbol) {
        if (currentState.equals("q10")) return;
        
        inputSequence.append(symbol);
        
        Map<Character, String> transitions = transitionFunction.get(currentState);
        if (transitions != null && transitions.containsKey(symbol)) {
            currentState = transitions.get(symbol);
        } else {
            isValidSequence = false;
        }
        
        if (FINAL_STATES.contains(currentState) && symbol == 'j') {
            isValidSequence = checkFullSequenceValidity();
        }
    }
    
    private boolean checkFullSequenceValidity() {
        String simulatedState = START_STATE;
        
        for (char symbol : inputSequence.toString().toCharArray()) {
            Map<Character, String> transitions = transitionFunction.get(simulatedState);
            if (transitions == null || !transitions.containsKey(symbol)) {
                return false;
            }
            simulatedState = transitions.get(symbol);
        }
        
        return FINAL_STATES.contains(simulatedState);
    }
    
    public void resetMachine() {
        currentState = START_STATE;
        inputSequence = new StringBuilder();
        isValidSequence = true;
    }
    
    public String getCurrentState() {
        return currentState;
    }
    
    public String getStateDescription() {
        return stateDescriptions.get(currentState);
    }
    
    public String getInputSequence() {
        return inputSequence.toString();
    }
    
    public boolean isFinalState() {
        return FINAL_STATES.contains(currentState);
    }
    
    public boolean isValidSequence() {
        return isValidSequence;
    }
    
    public boolean shouldShowResult() {
        return inputSequence.length() > 0 && inputSequence.charAt(inputSequence.length()-1) == 'j';
    }
}