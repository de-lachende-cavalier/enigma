package components;

import java.util.HashMap;

/**
 * The reflector constitues the second to last encryption procedure. It substantially works as an ATBASH cipher.
 */
public class Reflector implements WireConnector {

    private HashMap<Character, Character> reflectorWiring = new HashMap<>();

    @Override
    public void mapWires() {
        for (int i = 0; i < 26; i++) {
            reflectorWiring.put((char) ((int) 'A' + i), (char) ((int) 'Z' - i));
        }
    }

    @Override
    public char connectInternalWires(char input) {
        return reflectorWiring.get(Character.toUpperCase(input));
    }

    @Override
    public char reverseConnectInternalWires(char input) {
        return 0;
    }

    public HashMap<Character, Character> getReflectorWiring() {
        return reflectorWiring;
    }

    public void setReflectorWiring(HashMap<Character, Character> reflectorWiring) {
        this.reflectorWiring = reflectorWiring;
    }
}


