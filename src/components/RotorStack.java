package components;

import java.util.ArrayList;

/**
 * This class simply manages all the rotors.
 * The numbers of rotors in the stack is defined in the EnigmaDirector class.
 */
public class RotorStack {

    private final ArrayList<Rotor> rotors = new ArrayList<>();

    public RotorStack() {
    }

    /**
     * This constructor initialized the rotors, on top of building the rotor stack.
     *
     * @param numRotors the number of rotors in the stack
     */
    public RotorStack(int numRotors, boolean toMap) {
        for (int i = 0; i < numRotors; i++) {
            rotors.add(new Rotor(RandomnessHandler.getCSPRN(26)));
            // map both internal and external wires
            if (toMap)
                rotors.get(i).mapWires();
        }
    }

    /**
     * Gets the number of rotors.
     *
     * @return the number of rotors
     */
    public int getRotorNum() {
        return rotors.size();
    }

    /**
     * Allows to single out one rotor and act on it
     *
     * @param N the rotor considered (N ranges from 0 to whatever)
     * @return the Rotor object that's part of the stack
     */
    public Rotor getRotorN(int N) {
        return rotors.get(N);
    }

}
