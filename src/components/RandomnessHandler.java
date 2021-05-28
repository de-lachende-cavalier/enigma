package components;

import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;

/**
 * This class is responsible for handling anything that's concerned directly with the CSPRNG.
 */
public class RandomnessHandler {

    private static final SecureRandom CSPRN = new SecureRandom();

    /**
     * This method only exists to isolate the CSPRNG as much as possible so that I never have to import SecureRandom
     * anywhere but in this class
     *
     * @param upBound the upperBound for nextInt
     * @return the random value with specified upBound
     */
    @NotNull
    public static Integer getCSPRN(int upBound) {
        return CSPRN.nextInt(upBound);
    }

    /**
     * In mapping the wires, the only real work has to be done on the values of the HashMap, which are shuffled using this method.
     *
     * Fun fact: the Fisher-Yates shuffle is mentioned in The Art of Computer Programming as Algorithm P, it is also
     * referred to as the Knuth Shuffle [source: Wikipedia].
     *
     * @param array the array that's to be shuffled
     */
    protected static void shuffleFisherYates(@NotNull int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = CSPRN.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
