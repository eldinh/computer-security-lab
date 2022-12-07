package ru.sfedu.lab;

public class Lab3 {
    public static void main(String[] args) {
        System.out.println( hash("acb".toCharArray()) );
        System.out.println( hardHash("HellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjaapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworld".toCharArray()) );
        System.out.println( hardHash("HellhhiwahphfawhfjapwfjipawfaworldHellhhiiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworldHellhhiwahphfawhfjaapwfjipawfaworldHellhhiwahphfawhfjapwfjipawfaworld".toCharArray()) );
    }
// обработка батчами
    public static long hash(char[] text) {
        long hash = 1;
        for (int i = 0; i < text.length; i ++) {
            hash *= text[i];
            hash /= (i % 10) + 1;
            hash /= (text[i] % (i + 1)) % 7 + 1;
        }
        return hash;
    }

    public static String hardHash(char[] text) {
        long hash1 =  get16hashNumber(hash(text), 32);
        long hash2 =  get16hashNumber(hash(text), 33);
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < 16; i ++) {
            hash.append(getChar((int) (hash1 % 10))).append(numberToChar((int) (hash2 % 10)));
            hash1 /= 10;
            hash2 /= 10;
        }
        return hash.toString();
    }

    private static long get16hashNumber(long number, int salt){
        long hash = number + salt;
        int length;
        while ( (length = numberLength(hash)) < 16) {
            hash = salt * hash + number;
            hash /= (number %  (length + 3)) + 1;
        }
        return hash;
    }

    private static char getChar(int x) { // a - z, A - z
        return (char) (x + 65);
    }

    private static char numberToChar(int number) {
        return (char) (48 + number);
    }

    private static int numberLength(long number) {
        return (int)(Math.log10(number)+1);
    }
}
