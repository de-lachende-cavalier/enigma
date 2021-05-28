import components.RotorStack;
import product.EnigmaMachine;

import java.io.*;
import java.util.HashMap;

/**
 * This class manages existing Enigma configurations or creates new ones to use for later decryption.
 * To always assure forward secrecy, the encryption settings are overwritten every time one encrypts using the
 * machine, thus denying the possibility of encrypting with the same configuration multiple times.
 */
public class EnigmaConfigManager {

    private final String path = "/Users/d0larhyde/Desktop/Java/Enigma/";
    private boolean extracted = false;
    private boolean configured = false;

    /**
     * Used when encrypting, to save an Enigma configuration.
     *
     * @param configuredEnigma the Enigma configured during the encryption procedure
     */
    public void extractConfig(EnigmaMachine configuredEnigma) {
        if (!extracted) {
            String toWrite = path + "EnigmaConfig.ser";

            try {
                FileOutputStream fileToWrite = new FileOutputStream(toWrite);
                ObjectOutputStream componentMapping = new ObjectOutputStream(fileToWrite);

                // write the number of rotors to the file so that we can initialize the right amount
                componentMapping.writeInt(configuredEnigma.getRotors().getRotorNum());
                componentMapping.writeInt(configuredEnigma.getUniqRandRotCounter());

                componentMapping.writeObject(configuredEnigma.getPlugBoard().getWireSettings());
                componentMapping.writeObject(configuredEnigma.getStator().getInternalStatorWiring());
                componentMapping.writeObject(configuredEnigma.getReflector().getReflectorWiring());
                for (int i = 0; i < configuredEnigma.getRotors().getRotorNum(); i++) {
                    componentMapping.writeObject(configuredEnigma.getRotors().getRotorN(i).getInternalRotorWiring());
                    componentMapping.writeInt(configuredEnigma.getRotors().getRotorN(i).getRotationCounter());
                }

                componentMapping.close();
                fileToWrite.close();
                // it only needs to run once
                extracted = true;
            } catch (Exception e) {
                System.out.println("WRITING ERROR!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Used when decrypting, to get an existing configuration of the Enigma.
     *
     * @return the configured Enigma machine, to use for decryption
     */
    public EnigmaMachine configureEnigma() {
        if (configured) {
            return null;
        }
        else {
            EnigmaMachine toConfigure = new EnigmaMachine();
            String toRead = path + "EnigmaConfig.ser";
            String readData;

            try {
                FileInputStream fileToRead = new FileInputStream(toRead);
                ObjectInputStream componentMapping = new ObjectInputStream(fileToRead);

                // initialize rotor amount based on what previously written
                toConfigure.setRotors(new RotorStack(componentMapping.readInt(), false));
                toConfigure.setUniqRandRotCounter(componentMapping.readInt());

                // The problem is that a cast is a runtime check - but due to type erasure, at runtime there's actually no
                // difference between a HashMap<String,String> and HashMap<Integer, Integer>
                toConfigure.getPlugBoard()
                        .setWireSettings((HashMap<Character, Integer>) componentMapping.readObject());
                toConfigure.getStator()
                        .setInternalStatorWiring((HashMap<Character, Character>) componentMapping.readObject());
                toConfigure.getReflector()
                        .setReflectorWiring((HashMap<Character, Character>) componentMapping.readObject());
                for (int i = 0; i < toConfigure.getRotors().getRotorNum(); i++) {
                    toConfigure.getRotors().getRotorN(i)
                            .setInternalRotorWiring((HashMap<Character, Character>) componentMapping.readObject());
                    toConfigure.getRotors().getRotorN(i)
                            .setRotationCounter(componentMapping.readInt());
                }

                componentMapping.close();
                fileToRead.close();
                configured = true;
            } catch (Exception e) {
                System.out.println("READING ERROR!");
                e.printStackTrace();
            }
            return toConfigure;
        }
    }
}
