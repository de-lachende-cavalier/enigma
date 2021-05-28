package components;

/**
 * Every component of the Enigma implements this interface, seeing as every part has some wiring to take care of.
 */
public interface WireConnector {
    /**
     * Allows to connect the wires, thus setting it up for encryption/decryption.
     */
    void mapWires();

    /**
     * Mimics the current flow in the enigma, by "connecting" the wires as mapped by mapWires().
     *
     * @param input the input character
     * @return the character resulting from the connection
     */
    char connectInternalWires(char input);

    /**
     * Allows for reverse flow when decrypting.
     *
     * @param input the input character
     * @return the character resulting from the reverse flow
     */
    char reverseConnectInternalWires(char input);
}
