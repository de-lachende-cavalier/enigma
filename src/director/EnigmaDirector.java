package director;

import builders.Builder;
import components.*;
import org.jetbrains.annotations.NotNull;

/**
 * The director defines the order of the building steps.
 * It is basically a software analogy for an engineer/architect directing the construction of the Enigma by managing
 * builders.
 * It can construct many varieties of Objects(/Enigma machines).
 */
public class EnigmaDirector {

    /**
     * Constructs a classic (kinda) Enigma machine
     * @param builder the Builder interface considered for the final product
     */
    public final void constructEnigmaMachine(@NotNull Builder builder) {
        builder.buildPlugboard(new Plugboard());
        builder.buildReflector(new Reflector());
        builder.buildStator(new Stator());
        builder.buildRotors(new RotorStack(98, true));
    }
}
