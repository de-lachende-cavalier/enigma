package components;

import java.util.HashMap;
import java.util.Map;

/**
 * Rotors are the main contributor to Enigma's encryption strength.
 * Internally, their wiring is obtained the same way as for a Stator, and the only thing they have that a Stator doesn't
 * is the capacity to rotate, thus we can't just use the default constructor for a Rotor, because we need to specify the
 * initial position it's in.
 */
public class Rotor implements WireConnector {

    private HashMap<Character, Character> internalRotorWiring = new HashMap<>();
    private Integer rotationCounter;
    private final int[] tempRandom = new int[26];

    public Rotor(Integer rotationCounter) {
        this.rotationCounter = rotationCounter;
    }

    /**
     * Rotates the rotor forward, meaning it puts the value K(n) in K(n + 1)
     */
    public void rotate() {
        char[] toSub = new char[26];
        for (int i = 0; i < internalRotorWiring.size(); i++) {
            toSub[i] = internalRotorWiring.get((char) ((int) 'A' + i));
        }
        // shift every key value pair but the first
        for (int i = 0; i < internalRotorWiring.size() - 1; i++) {
            internalRotorWiring.put((char) ((int) 'A' + i + 1), toSub[i]);
        }
        // fix first key
        internalRotorWiring.put('A', toSub[25]);

        rotationCounter = (rotationCounter + 1) % 26;
    }

    /**
     * Rotates the rotor backward, meaning it puts the value of K(n) in K(n - 1)
     */
    public void reverseRotate() {
        char toSub;
        // to save the value at the first key
        char toSubEnd;
        toSubEnd = internalRotorWiring.get('A');
        // shift every key value pair but the last
        for (int i = 0; i < internalRotorWiring.size() - 1; i++) {
            toSub = internalRotorWiring.get((char) ((int) 'A' + (i + 1)));
            internalRotorWiring.put((char) ((int) 'A' + i), toSub);
        }
        // fix last key
        internalRotorWiring.put('Z', toSubEnd);

        rotationCounter = (rotationCounter - 1) % 26;
    }

    private void initArray() {
        // init array with values 65 - 90 (ASCII of 'A' - 'Z')
        for (int i = 0; i < tempRandom.length; i++) {
            tempRandom[i] = i + 65;
        }
    }

    @Override
    public void mapWires() {
        initArray();
        RandomnessHandler.shuffleFisherYates(tempRandom);
        for (int i = 0; i < tempRandom.length; i++) {
            internalRotorWiring.put((char) ((int) 'A' + i), (char) tempRandom[i]);
        }
    }

    @Override
    public char connectInternalWires(char input) {
        return internalRotorWiring.get(Character.toUpperCase(input));
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public char reverseConnectInternalWires(char input) {
        HashMap<Character, Character> reversedWiring = new HashMap<>();
        for (Map.Entry<Character, Character> entry: internalRotorWiring.entrySet()) {
           reversedWiring.put(entry.getValue(), entry.getKey());
        }

        return reversedWiring.get(Character.toUpperCase(input));
    }

    public Integer getRotationCounter() {
        return rotationCounter;
    }

    public void setRotationCounter(Integer rotationCounter) {
        this.rotationCounter = rotationCounter;
    }

    public HashMap<Character, Character> getInternalRotorWiring() {
        return internalRotorWiring;
    }

    public void setInternalRotorWiring(HashMap<Character, Character> internalRotorWiring) {
        this.internalRotorWiring = internalRotorWiring;
    }
}
