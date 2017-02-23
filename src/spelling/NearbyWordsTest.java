package spelling;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NearbyWordsTest {

    NearbyWords nearbyWords;
    DictionaryBST smallDict;
    List<String> currentList;
    List<String> expectedList;

    @Before
    public void setup() {
        smallDict = new DictionaryBST();
        smallDict.addWord("hello");
        smallDict.addWord("hi");
        smallDict.addWord("help");
        smallDict.addWord("a");
        smallDict.addWord("no");
        smallDict.addWord("on");
        smallDict.addWord("subsequent");
        smallDict.addWord("he");
        smallDict.addWord("hen");
        smallDict.addWord("the");
        smallDict.addWord("hey");
        smallDict.addWord("hoe");

        nearbyWords = new NearbyWords(smallDict);
    }

    @Test
    public void insertions() throws Exception {
        currentList = new ArrayList<>();
        nearbyWords.insertions(null, currentList, true);
//        System.out.println(currentList);
        assertEquals("null input with wordsOnly set to true",
                0, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions(null, currentList, false);
//        System.out.println(currentList);
        assertEquals("null input with wordsOnly set to false",
                0, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("", currentList, true);
//        System.out.println(currentList);
        assertEquals("empty input with wordsOnly set to true",
                1, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("", currentList, false);
//        System.out.println(currentList);
        assertEquals("empty input with wordsOnly set to false",
                26, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("o", currentList, true);
//        System.out.println(currentList);
        assertEquals("Single letter input with wordsOnly set to true",
                2, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("o", currentList, false);
//        System.out.println(currentList);
        assertEquals("Single letter input with wordsOnly set to false",
                51, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("he", currentList, true);
//        System.out.println(currentList);
        assertEquals("Two letter input with wordsOnly set to true",
                4, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.insertions("he", currentList, false);
//        System.out.println(currentList);
        assertEquals("Two letter input with wordsOnly set to false",
                76, currentList.size());
    }

    @Test
    public void deletions() throws Exception {
        currentList = new ArrayList<>();
        nearbyWords.deletions(null, currentList, true);
//        System.out.println(currentList);
        assertEquals("null with wordsOnly set to true",
                0, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.deletions(null, currentList, false);
//        System.out.println(currentList);
        assertEquals("null with wordsOnly set to false",
                0, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.deletions("a", currentList, false);
//        System.out.println(currentList);
        assertEquals("single letter input with wordsOnly set to false",
                0, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.deletions("ta", currentList, true);
//        System.out.println(currentList);
        assertEquals("Two letter input with wordsOnly set to true",
                1, currentList.size());

        currentList = new ArrayList<>();
        nearbyWords.deletions("ta", currentList, false);
//        System.out.println(currentList);
        assertEquals("Two letter input with wordsOnly set to false",
                2, currentList.size());
    }
}