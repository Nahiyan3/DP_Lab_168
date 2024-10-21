public class CaesarCipherEncryption implements EncryptionStrategy {
    private int shift;

    public CaesarCipherEncryption(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String data) {
        return shiftText(data, shift);
    }

    @Override
    public String decrypt(String data) {
        return shiftText(data, -shift);
    }

    private String shiftText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }
}
