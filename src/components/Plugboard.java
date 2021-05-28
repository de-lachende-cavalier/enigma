package components;

import java.util.HashMap;
import java.util.Map;

/**
 * The plugboard starts the encryption.
 * I use the Mk. II design, which maps the numbers 1 - 26 to the letters of the alphabet.
 */
public class Plugboard implements WireConnector {

    private HashMap<Character, Integer> wireSettings = new HashMap<>();
    private final int[] tempRandom = new int[26];

    private void initArray() {
        for (int i = 0; i < tempRandom.length; i++) {
            tempRandom[i] = i;
        }
    }

    @Override
    public void mapWires() {
        initArray();
        RandomnessHandler.shuffleFisherYates(tempRandom);

        for (int i = 0; i < tempRandom.length; i++) {
            wireSettings.put((char) ((int) 'A' + i), tempRandom[i]);
        }
    }

    @Override
    public char connectInternalWires(char input) {
        Integer out = wireSettings.get(Character.toUpperCase(input));
        return (char) (out + (int) 'A');
    }

    @Override
    public char reverseConnectInternalWires(char input) {
        HashMap<Integer, Character> reversedWiring = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : wireSettings.entrySet()) {
            reversedWiring.put(entry.getValue(), entry.getKey());
        }

        return reversedWiring.get(Math.abs((int) 'A' - (int) input));
    }

    public HashMap<Character, Integer> getWireSettings() {
        return wireSettings;
    }

    public void setWireSettings(HashMap<Character, Integer> wireSettings) {
        this.wireSettings = wireSettings;
    }
}
