package ru.nsu.fit.oop.ilg.substring.core;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для поиска подстроки в большом текстовом файле.
 */
public class SubstringFinder {
    private static final int BUFFER_SIZE = 1024 * 1024; // 1MB

    /**
     * Ищет все вхождения подстроки в тексте.
     *
     * @param reader   поток для чтения текста (например, файла)
     * @param substring искомая подстрока
     * @return список индексов (в символах) начала каждого вхождения
     * @throws IOException при ошибке чтения
     */
    public List<Long> find(Reader reader, String substring) throws IOException {
        if (substring == null || substring.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> results = new ArrayList<>();
        char[] buffer = new char[BUFFER_SIZE];
        int overlap = substring.length() - 1;

        long globalOffset = 0;
        int charsRead;
        String carry = "";

        while ((charsRead = reader.read(buffer)) != -1) {
            String chunk = carry + new String(buffer, 0, charsRead);
            int index = 0;

            while ((index = chunk.indexOf(substring, index)) != -1) {
                long globalIndex = globalOffset - carry.length() + index;
                results.add(globalIndex);
                index += substring.length();
            }

            if (chunk.length() >= overlap) {
                carry = chunk.substring(chunk.length() - overlap);
            } else {
                carry = chunk;
            }

            globalOffset += charsRead;
        }

        return results;
    }
}