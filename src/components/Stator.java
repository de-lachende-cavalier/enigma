package components;

import java.util.HashMap;
import java.util.Map;

/**
 * The stator is so called because it is a static rotor.
 */
public class Stator implements WireConnector {

    private HashMap<Character, Character> internalStatorWiring = new HashMap<>();
    private final int[] tempRandom = new int[26];

    private void initArray() {
        for (int i = 0; i < tempRandom.length; i++) {
            tempRandom[i] = i + 65;
        }
    }

    @Override
    public void mapWires() {
        initArray();
        RandomnessHandler.shuffleFisherYates(tempRandom);

        for (int i = 0; i < tempRandom.length; i++) {
            internalStatorWiring.put((char) ((int) 'A' + i), (char) tempRandom[i]);
        }
    }

    @Override
    public char connectInternalWires(char input) {
        return internalStatorWiring.get(Character.toUpperCase(input));
    }

    @Override
    public char reverseConnectInternalWires(char input) {
        HashMap<Character, Character> reversedWiring = new HashMap<>();
        for (Map.Entry<Character, Character> entry : internalStatorWiring.entrySet()) {
            reversedWiring.put(entry.getValue(), entry.getKey());
        }

        return reversedWiring.get(Character.toUpperCase(input));
    }

    public HashMap<Character, Character> getInternalStatorWiring() {
        return internalStatorWiring;
    }

    public void setInternalStatorWiring(HashMap<Character, Character> internalStatorWiring) {
        this.internalStatorWiring = internalStatorWiring;
    }
}
