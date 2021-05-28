import builders.EnigmaBuilder;
import director.EnigmaDirector;
import product.EnigmaMachine;

/**
 * This class permits the user to interface with the machine, it fundamentally makes sure that the electric signal
 * travels correctly through the Enigma and handles its output and input.
 */
public class EncryptDecrypt {

    private final EnigmaConfigManager enigmaConfig = new EnigmaConfigManager();
    private final EnigmaDirector director = new EnigmaDirector();
    private final EnigmaBuilder builder = new EnigmaBuilder();
    private EnigmaMachine curEnigma;
    private boolean called = false;
    private int rotations = 0;


    private void handleRotation() {
        int rotorNum = curEnigma.getRotors().getRotorNum();
        // handle rotor movement
        curEnigma.getRotors().getRotorN(0).rotate();
        rotations++;
        if (rotations % curEnigma.getUniqRandRotCounter() == 0) {
            // the rightmost rotor has moved full circle
            for (int i = 0; i < rotorNum; i++) {
                if (i > 0) {
                    if (i % 2 == 0) {
                        // reverse rotate
                        curEnigma.getRotors().getRotorN(rotorNum - i).reverseRotate();
                    } else {
                        // normally rotate
                        curEnigma.getRotors().getRotorN(rotorNum - i).rotate();
                    }
                    rotations++;
                }
            }
        }
    }

    /**
     * Method used to encrypt char by char.
     *
     * @param toEncrypt the character to encrypt
     * @return the encrypted character
     */
    public char encrypt(char toEncrypt) {
        if (!called) {
            director.constructEnigmaMachine(builder);
            curEnigma = builder.getBuiltEnigma();
            // we only want to initialize the Enigma once
            called = true;
        }
        enigmaConfig.extractConfig(curEnigma);

        int rotorNum = curEnigma.getRotors().getRotorNum();

        handleRotation();

        // keyboard --> reflector
        toEncrypt = curEnigma.getPlugBoard().connectInternalWires(toEncrypt);
        toEncrypt = curEnigma.getStator().connectInternalWires(toEncrypt);
        for (int i = 0; i < rotorNum; i++) {
            toEncrypt = curEnigma.getRotors().getRotorN(i).connectInternalWires(toEncrypt);
        }

        toEncrypt = curEnigma.getReflector().connectInternalWires(toEncrypt);

        // reflector --> lamp panel
        for (int i = rotorNum - 1; i >= 0; i--) {
            toEncrypt = curEnigma.getRotors().getRotorN(i).reverseConnectInternalWires(toEncrypt);
        }
        toEncrypt = curEnigma.getStator().reverseConnectInternalWires(toEncrypt);

        return toEncrypt;
    }

    /**
     * Method used to decrypt char by char.
     *
     * @param toDecrypt the character to decrypt
     * @return the decrypted character
     */
    public char decrypt(char toDecrypt) {
        if (!called) {
            curEnigma = enigmaConfig.configureEnigma();
            called = true;
        }
        handleRotation();

        toDecrypt = curEnigma.getStator().connectInternalWires(toDecrypt);
        for (int i = 0; i < curEnigma.getRotors().getRotorNum(); i++) {
            toDecrypt = curEnigma.getRotors().getRotorN(i).connectInternalWires(toDecrypt);
        }

        toDecrypt = curEnigma.getReflector().connectInternalWires(toDecrypt);

        // reflector --> lamp panel
        for (int i = curEnigma.getRotors().getRotorNum() - 1; i >= 0; i--) {
            toDecrypt = curEnigma.getRotors().getRotorN(i).reverseConnectInternalWires(toDecrypt);
        }
        toDecrypt = curEnigma.getStator().reverseConnectInternalWires(toDecrypt);
        toDecrypt = curEnigma.getPlugBoard().reverseConnectInternalWires(toDecrypt);

        return toDecrypt;
    }
}
