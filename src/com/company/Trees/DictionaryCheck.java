package com.company.Trees;

/**
 * Created by hmaduri on 7/2/16.
 */
public class DictionaryCheck {

    public static void main(String[] args) {
        Dictionary dict = new Dictionary();

        dict.insert("hello,bolo");
        dict.insert("chalo,mexico");
        dict.insert("shiva,supremehero");
        dict.insert("supremehero,shiva");

        System.out.println(dict.get("shiva"));
        System.out.println(dict.get("chalore"));
    }
}
