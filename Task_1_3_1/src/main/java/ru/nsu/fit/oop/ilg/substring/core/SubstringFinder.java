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
     * @param reader    поток для чтения текста
     * @param substring искомая подстрока
     * @return список индексов начала каждого вхождения
     * @throws IOException при ошибке чтения
     */
    public List<Long> find(Reader reader, String substring) throws IOException {
        if (substring == null || substring.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> results = new ArrayList<>();
        int overlap = substring.length() - 1;
        long globalOffset = 0;
        String carry = "";

        while (true) {
            List<Character> chunkList = readChunkToList(reader, BUFFER_SIZE);

            if (chunkList.isEmpty() && carry.isEmpty()) {
                break;
            }

            StringBuilder sb = new StringBuilder(chunkList.size());
            sb.append(carry);
            for (Character c : chunkList) {
                sb.append(c);
            }
            String chunk = sb.toString();

            int index = 0;
            while ((index = chunk.indexOf(substring, index)) != -1) {
                long globalIndex = globalOffset - carry.length() + index;
                results.add(globalIndex);
                index++;
            }

            if (chunk.length() >= overlap) {
                carry = chunk.substring(chunk.length() - overlap);
            } else {
                carry = chunk;
            }

            globalOffset += chunkList.size();

            if (chunkList.size() < BUFFER_SIZE) {
                break;
            }
        }

        return results;
    }

    private List<Character> readChunkToList(Reader reader, int size) throws IOException {
        List<Character> list = new ArrayList<>(size);
        int charsRead = 0;
        int c;
        while (charsRead < size && (c = reader.read()) != -1) {
            list.add((char) c);
            charsRead++;
        }
        return list;
    }
}