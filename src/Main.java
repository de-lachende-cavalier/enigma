import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {

        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        Scanner usrIO = new Scanner(System.in);
        Character toEncDec;
        String finalOut = "";

        System.out.println("Welcome to the POPEI (Personal One-time Pad Enigma Implementation).\nWhat do you wish to do?\n" +
                "Type 'e' for encryption and 'd' for decryption.");
        toEncDec = usrIO.next().charAt(0);

        if (toEncDec.equals('e')) {
            char temp;

            System.out.println("The encryption will be done one character at a time (as in the traditional Enigma).");
            System.out.println("Enter 0 to quit.");
            while (toEncDec != '0') {
                System.out.print(">> ");
                toEncDec = usrIO.next().charAt(0);
                if (toEncDec != '0') {
                    temp = encryptDecrypt.encrypt(toEncDec);
                    System.out.println("     " + temp);
                    finalOut += temp;
                }
            }
            System.out.println("The encrypted message is: " + finalOut);

        } else if (toEncDec.equals('d')) {
            char temp;

            System.out.println("The decryption will be done one character at a time (as in the traditional Enigma).");
            System.out.println("Enter 0 to quit.");
            while (toEncDec != '0') {
                System.out.print(">> ");
                toEncDec = usrIO.next().charAt(0);
                if (toEncDec != '0') {
                    temp = encryptDecrypt.decrypt(toEncDec);
                    finalOut += temp;
                    System.out.println("     " + temp);
                }
            }
            System.out.println("The decrypted message is: " + finalOut);
        } else {
            System.out.println("No such option.");
        }
    }
}
