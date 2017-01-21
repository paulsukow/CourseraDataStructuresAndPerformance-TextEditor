package document;

import org.junit.Test;

import static org.junit.Assert.*;

public class BasicDocumentTest {

    private static BasicDocument basicDocument;

    @Test
    public void getNumWords() throws Exception {
        basicDocument = new BasicDocument("");
        assertEquals(0, basicDocument.getNumWords());

        basicDocument = new BasicDocument("apple");
        assertEquals(1, basicDocument.getNumWords());

        basicDocument = new BasicDocument("a");
        assertEquals(1, basicDocument.getNumWords());

        basicDocument = new BasicDocument("A");
        assertEquals(1, basicDocument.getNumWords());

        basicDocument = new BasicDocument("Apple");
        assertEquals(1, basicDocument.getNumWords());

        basicDocument = new BasicDocument("A banana");
        assertEquals(2, basicDocument.getNumWords());

        basicDocument = new BasicDocument("A banana is good to eat.");
        assertEquals(6, basicDocument.getNumWords());
    }

    @Test
    public void getNumSentences() throws Exception {
        basicDocument = new BasicDocument("");
        assertEquals(0, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Two bananas.");
        assertEquals(1, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Two bananas. Yes.");
        assertEquals(2, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Two bananas? No!");
        assertEquals(2, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Two bananas... No!");
        assertEquals(2, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Two bananas... No! Why?");
        assertEquals(3, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("\"This is a test.  How many???  " +
                "Senteeeeeeeeeences are here... there should be 5!  Right?");
        assertEquals(5, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5.");
        assertEquals(4, basicDocument.getNumSentences());

        basicDocument = new BasicDocument("Here is a series of test sentences. Your program should "
                + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
                + "the correct amount of syllables (example, for example), ");
        assertEquals(3, basicDocument.getNumSentences());
    }

    @Test
    public void getNumSyllables() throws Exception {
        basicDocument = new BasicDocument("");
        assertEquals(0, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("a");
        assertEquals(1, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("Happy");
        assertEquals(2, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("the");
        assertEquals(1, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("are");
        assertEquals(1, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("greed");
        assertEquals(1, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("is");
        assertEquals(1, basicDocument.getNumSyllables());

        basicDocument = new BasicDocument("And");
        assertEquals(1, basicDocument.getNumSyllables());
    }
}