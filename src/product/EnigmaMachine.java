package product;

import components.*;
import org.jetbrains.annotations.NotNull;

/**
 * This is the product class of the whole builder design pattern.
 */
public class EnigmaMachine {

    private Plugboard plugBoard = new Plugboard();
    private Stator stator = new Stator();
    private RotorStack rotors = new RotorStack();
    private Reflector reflector = new Reflector();
    private int uniqRandRotCounter = RandomnessHandler.getCSPRN(26);

    public EnigmaMachine(@NotNull Plugboard plugBoard, @NotNull Stator stator,
                         RotorStack rotors, @NotNull Reflector reflector) {
        this.plugBoard = plugBoard;
        this.stator = stator;
        this.rotors = rotors;
        this.reflector = reflector;

        plugBoard.mapWires();
        stator.mapWires();
        reflector.mapWires();
        // the mapping of the rotor stack is done directly in the RotorStack class
    }

    public EnigmaMachine() {

    }

    public Plugboard getPlugBoard() {
        return plugBoard;
    }

    public void setPlugBoard(Plugboard plugBoard) {
        this.plugBoard = plugBoard;
    }

    public Stator getStator() {
        return stator;
    }

    public void setStator(Stator stator) {
        this.stator = stator;
    }

    public RotorStack getRotors() {
        return rotors;
    }

    public void setRotors(RotorStack rotors) {
        this.rotors = rotors;
    }

    public Reflector getReflector() {
        return reflector;
    }

    public void setReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    public int getUniqRandRotCounter() {
        return uniqRandRotCounter;
    }

    public void setUniqRandRotCounter(int uniqRandRotCounter) {
        this.uniqRandRotCounter = uniqRandRotCounter;
    }
}
