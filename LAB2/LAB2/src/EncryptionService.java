import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptionService {
    private EncryptionStrategy strategy;

    public void setEncryptionStrategy(EncryptionStrategy strategy) {
        this.strategy = strategy;
    }

    public void encryptFile(String inputFile, String outputFile) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(inputFile)));

        String encryptedContent = strategy.encrypt(content);

        Files.write(Paths.get(outputFile), encryptedContent.getBytes());

        System.out.println("File encrypted successfully: " + outputFile);
    }

    public void decryptFile(String inputFile, String outputFile) throws IOException {

        String encryptedContent = new String(Files.readAllBytes(Paths.get(inputFile)));

        String decryptedContent = strategy.decrypt(encryptedContent);

        Files.write(Paths.get(outputFile), decryptedContent.getBytes());

        System.out.println("File decrypted successfully: " + outputFile);
    }
}
