package ru.sfedu.lab;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lab2 {

    public static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) {
        String message = "шцагщг иахшцкихпг м агюбцгэ ы бюпгшцх гцбэщбу фщъайъцхмх\n" +
                "ы мбщжъ 1940-е йй. иах гмцхыщбэ клгшцхх клъщче, хээхйахабыгытхе хё\n" +
                "йъаэгщхх. ш шгэбйб щглгпг акмбыбьшцыб гайъщцхщч шцаъэхпбшо ьбюхцошд\n" +
                "ы дьъащбу бюпгшцх эгмшхэгпощбу щъёгыхшхэбшцх, иах мбцбабу ыбёэбрщб\n" +
                "бьщбыаъэъщщбъ агёыхцхъ гцбэщбу фщъайъцхмх х щгклщб-цъещхлъшмбйб\n" +
                "ибцъщжхгпг ы бюпгшцх дьъащбйб цбипхыщбйб жхмпг (дцж), ьбпйбъ ыаъэд\n" +
                "бшцгыпдд бцмачцчэ ыбиабш ъйб хшибпоёбыгщхд ы ыбъщщче жъпде.ы 1953 й.\n" +
                "ы гайъщцхщъ щглгпхшо агюбцч иб иабэчтпъщщбу ьбючлъ кагщг. ёг 1958–1972\n" +
                "йй. ючпх ыыъьъщч ы фмшипкгцгжхн лъцчаъ хшшпъьбыгцъпошмхе аъгмцбаг,\n" +
                "бьхщ хё мбцбаче ючп хэибацхабыгщ хё штг, бшцгпощчъ шмбщшцакхабыгщч х\n" +
                "ибшцабъщч шбюшцыъщщчэх шхпгэх. ы 1969 й. ы фшъушъ, ы 40 мэ бц\n" +
                "шцбпхжч гайъщцхщч юкфщбш-гуаъшг, ючпг икяъщг пгюбагцбащгд\n" +
                "кшцгщбымг иб агьхбехэхлъшмбу иъаъагюбцмъ бюпклъщщбйб дьъащбйб\n" +
                "цбипхыг (бдц), щб ы 1973 й. ибшпъ ычьъпъщхд бм. 1 мй ипкцбщхд бщг ючпг\n" +
                "ьъэбщцхабыгщг. ы 1974 й. ы пхэъ, ы 100 мэ м шъыъак бц юкфщбш-гуаъшг,\n" +
                "ючпг икяъщг иъаыгд гцбэщгд фпъмцабшцгщжхд (гфш) «гцклг-1»,\n" +
                "ибшцабъщщгд иах ибэбях щъэъжмбу мбэигщхх «шхэъщш». гфш ючпг шбёьгщг\n" +
                "щг бшщбыъ цдръпбыбьщ йб аъгмцбаг щг иахабьщбэ кагщъ фпъмцахлъшмбу ��\n" +
                "эбящбшцон 357 эыц.";
        String characters = "оаниетрсвлмкпгыбяудйчэцхзьщшжюфъё";
        System.out.println(hardDecrypt(message, characters));
    }

    public static void decrypt(String message){
        for (var i = 1; i <= 33; i ++){
            System.out.println(i + ")" + Lab1.customDecrypt(i, message));
        }
    }

    public static String hardDecrypt(String message, String replacedCharacter){
        Map<Character, Character> characterMap =
                characterMap(getSortedCharacters(message), replacedCharacter.toCharArray());
        return message.chars()
                .map(ch -> characterMap.getOrDefault((char) ch, (char) ch))
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    private static Map<Character, Character> characterMap(List<Character> sortedCharacter, char[] replacedCharacter){
        return Stream.iterate(0, n -> n + 1)
                .limit(sortedCharacter.size())
                .collect(Collectors.toMap(sortedCharacter::get, x -> replacedCharacter[x]));
    }

    private static List<Character> getSortedCharacters(String message){
        Map<Character, Integer> map = getAlphabetMap();
        message.chars().forEach(ch -> map.computeIfPresent((char) ch, (key, value) -> value + 1));
        return map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static Map<Character, Integer> getAlphabetMap(){
        return ALPHABET.chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toMap(x -> x, x-> 0));
    }
}