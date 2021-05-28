package builders;

import components.*;
import product.EnigmaMachine;

/**
 * This corresponds to the concrete builder. It implements all the steps defined in the general Builder interface.
 */
public class EnigmaBuilder implements Builder {

    private Plugboard plugBoard;
    private Stator stator;
    private RotorStack rotors;
    private Reflector reflector;

    @Override
    public void buildPlugboard(Plugboard plugBoard) {
        this.plugBoard = plugBoard;
    }

    @Override
    public void buildStator(Stator stator) {
        this.stator = stator;
    }

    @Override
    public void buildRotors(RotorStack rotors) {
        this.rotors = rotors;
    }

    @Override
    public void buildReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    /**
     * Puts all the built parts together.
     * @return the Enigma obtained from combining all the built parts
     */
    public EnigmaMachine getBuiltEnigma() {
        return new EnigmaMachine(plugBoard, stator, rotors, reflector);
    }


}
