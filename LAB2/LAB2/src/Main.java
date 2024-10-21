import java.util.Scanner;  // Import the Scanner class

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EncryptionService service = new EncryptionService();

        System.out.println("Select encryption method: 1) AES  2) DES  3) Caesar Cipher");
        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1:
                    service.setEncryptionStrategy(new AESEncryption());
                    break;
                case 2:
                    service.setEncryptionStrategy(new DESEncryption());
                    break;
                case 3:
                    System.out.println("Enter shift value for Caesar Cipher:");
                    int shift = scanner.nextInt();
                    service.setEncryptionStrategy(new CaesarCipherEncryption(shift));
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            System.out.println("Enter input file path:");
            String inputFile = scanner.next();
            System.out.println("Enter output file path for encryption:");
            String outputFile = scanner.next();

            service.encryptFile(inputFile, outputFile);
            System.out.println("File encrypted successfully.");

            System.out.println("Enter output file path for decryption:");
            String decryptedFile = scanner.next();
            service.decryptFile(outputFile, decryptedFile);
            System.out.println("File decrypted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}