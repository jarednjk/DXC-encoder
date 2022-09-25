class Main {
    private static final String REFERENCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public static String encode(String plainText, char offsetChar) {
        String encodeChar = Character.toString(offsetChar);
        // check if reference contains the offset char
        // if reference does not contain offset char, return original plain text
        if (!REFERENCE.contains(encodeChar)) {
            return plainText;
            // else if reference contains the offset char
        } else {
            // convert plain text input from user from string into an array of chars
            char[] chars = plainText.toCharArray();
            // find the index of offset char
            int shiftKey = REFERENCE.indexOf(encodeChar);

            // iterate through the array of plaintext characters
            for (int i = 0; i < chars.length; i++) {

                // find index of each char of plaintext
                int index = REFERENCE.indexOf(chars[i]);

                // if chars of plain text can be found in REFERENCE
                if (index != -1) {
                    // (index of each char of plaintext) minus (index of offset character)
                    index -= shiftKey;
                    if (index >= 0) {
                        chars[i] = REFERENCE.charAt(index);
                    } else {
                        chars[i] = REFERENCE.charAt(index + 44);
                    }
                }

            }

            // convert new array of encoded chars into a string and concatenate to the
            // offset char
            String encodedText = encodeChar + new String(chars);
            return encodedText;
        }
    }

    public static String decode(String encodedText) {
        // Assign first character of encoded text to be decode char
        String decodeChar = Character.toString(encodedText.charAt(0));
        // check if first char of encoded text is inside the reference
        // if no, return encode text
        if (!REFERENCE.contains(decodeChar)) {
            return encodedText;
        } else {
            // convert encode text from string into an array of chars
            char[] chars = encodedText.toCharArray();
            // find the index of decode char
            int shiftKey = REFERENCE.indexOf(decodeChar);
            // iterate through the array of encode chars

            for (int i = 1; i < chars.length; i++) {

                // find index of each char of encoded text
                int index = REFERENCE.indexOf(chars[i]);

                // if chars of encoded text can be found in REFERENCE
                if (index != -1) {
                    // (index of each char of encoded text) PLUS (index of decode char)
                    index += shiftKey;
                    if (index < 44) {
                        chars[i] = REFERENCE.charAt(index);
                    } else {
                        chars[i] = REFERENCE.charAt(index - 44);
                    }
                }

            }
            // convert new array of decoded chars into a string and remove the first char
            // (decode char)
            String decodedText = new String(chars).substring(1);
            return decodedText;
        }
    }

    public static void main(String[] args) {
        String encodedExample1 = encode("HELLO WORLD", 'B');
        System.out.println(encodedExample1);

        String decodedExample1 = decode("BGDKKN VNQKC");
        System.out.println(decodedExample1);

        String encodedExample2 = encode("HELLO WORLD", 'F');
        System.out.println(encodedExample2);

        String decodedExample2 = decode("FC/GGJ RJMG.");
        System.out.println(decodedExample2);
    }

}