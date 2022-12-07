package ru.sfedu.lab;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lab1 {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String NE_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final char[] ALPHABET_CHARS = ALPHABET.toCharArray();
    private static final char[] NE_ALPHABET_CHARS = NE_ALPHABET.toCharArray();

    private static Map<Character, Character> cryptMap = IntStream.range(0, ALPHABET.length())
            .boxed()
            .collect(Collectors.toMap(x -> ALPHABET_CHARS[x], x -> NE_ALPHABET_CHARS[x]));
    private static Map<Character, Character> decryptMap = IntStream.range(0, ALPHABET.length())
            .boxed()
            .collect(Collectors.toMap(x -> NE_ALPHABET_CHARS[x], x -> ALPHABET_CHARS[x]));

    private static String customCrypt(String alphabet ,int shift, String message) {
        StringBuilder cryptMessage = new StringBuilder();
        for (var letter : message.toCharArray()) {
            int index = alphabet.indexOf(letter);
            cryptMessage.append(index != -1 ?
                    alphabet.charAt((index + shift) % alphabet.length()) :
                    letter
            );
        }
        return cryptMessage.toString();
    }

    public static String ceaserCrypt(int shift, String message) {
        return customCrypt(ALPHABET ,shift, message);
    }

    public static String ceaserDecrypt(int shift, String message) {
        return customCrypt(ALPHABET, ALPHABET.length() - (shift % ALPHABET.length()), message);
    }

    public static String customCrypt(int shift, String message) {
        return customCrypt(NE_ALPHABET ,shift, message);
    }

    public static String customDecrypt(int shift, String message) {
        return customCrypt(NE_ALPHABET, NE_ALPHABET.length() - (shift % NE_ALPHABET.length()), message);
    }

    private static String helper(String message, Map<Character, Character> cryptMap){
        char[] chars = message.toCharArray();
        return IntStream.range(0, chars.length).mapToObj(i -> chars[i])
                .map(x -> cryptMap.getOrDefault(x, x))
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));
    }

    public static String otherCrypt(String message) {
        return helper(message, cryptMap);
    }

    public static String otherDecrypt(String message) {
        return helper(message, decryptMap);
    }

    public static void main(String[] args) {
        System.out.println(ceaserCrypt(500, "hello world"));
        System.out.println(ceaserDecrypt(500, "nkrru cuxrj"));
        System.out.println(otherCrypt("hello world"));
        System.out.println(otherDecrypt("ждккн хнркг"));
    }
}
