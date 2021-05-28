package builders;

import components.*;

/**
 * This Builder interface defines all possible ways to construct an object (in our case an EnigmaMachine)
 */
public interface Builder {

    void buildPlugboard(Plugboard plugBoard);
    void buildStator(Stator stator);
    void buildRotors(RotorStack rotors);
    void buildReflector(Reflector reflector);


}
