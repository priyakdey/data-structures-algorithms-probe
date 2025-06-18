package com.priyakdey.probe.dsa.encode;

/**
 * @author Priyak Dey
 */
public class Base64Encoder {
    private static final char[] BASE64_TABLE = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public static String encode(byte[] input) {
        int cursor = 0;
        int length = input.length;

        int outputLength = ((length + 2) / 3) * 4;
        char[] buffer = new char[outputLength];
        int k = 0;

        while (cursor + 2 < length) {
            int b0 = input[cursor] & 0xFF;
            int b1 = input[cursor + 1] & 0xFF;
            int b2 = input[cursor + 2] & 0xFF;

            int first  = b0 >> 2;
            int second = ((b0 & 0x3) << 4) | ((b1 & 0xF0) >> 4);
            int third = ((b1 & 0xF) << 2) | ((b2 & 0xC0) >> 6);
            int fourth = b2 & 0x3F;

            buffer[k++] = BASE64_TABLE[first];
            buffer[k++] = BASE64_TABLE[second];
            buffer[k++] = BASE64_TABLE[third];
            buffer[k++] = BASE64_TABLE[fourth];

            cursor += 3;
        }

        if (length - cursor == 2) {
            int b0 = input[cursor] & 0xFF;
            int b1 = input[cursor + 1] & 0xFF;

            int first  = b0 >> 2;
            int second = ((b0 & 0x3) << 4) | (b1 >> 4);
            int third = (b1 & 0xF) << 2;

            buffer[k++] = BASE64_TABLE[first];
            buffer[k++] = BASE64_TABLE[second];
            buffer[k++] = BASE64_TABLE[third];
            buffer[k++] = '=';
        } else if (length - cursor == 1) {
            int b0 = input[cursor] & 0xFF;

            int first  = b0 >> 2;
            int second = (b0 & 0x3) << 4;

            buffer[k++] = BASE64_TABLE[first];
            buffer[k++] = BASE64_TABLE[second];
            buffer[k++] = '=';
            buffer[k++] = '=';
        }

        return new String(buffer);
    }

}
