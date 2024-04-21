package com.ocado.basket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BasketSplitter {
    private Map<String, List<String>> config;
    private static final String CONFIG_NOT_FOUND_ERROR = "Config file not found: ";

    public BasketSplitter(String absolutPathToConfigFile) {
        byte[] jsonData;
        try {
            jsonData = Files.readAllBytes(Paths.get(absolutPathToConfigFile));
        } catch (IOException e) {
            throw new IllegalArgumentException(CONFIG_NOT_FOUND_ERROR + absolutPathToConfigFile, e);
        }
        mapConfigData(jsonData);
    }

    BasketSplitter() {
    }

    void mapConfigData(byte[] jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            config = objectMapper.readValue(jsonData, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid Config file format", e);
        }
    }

    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> result = new HashMap<>();

        // Iteracja po wszystkich produktach w koszyku
        for (String item : items) {
            boolean addedToGroup = false;
            // Iteracja po wszystkich istniejących grupach dostaw
            for (Map.Entry<String, List<String>> entry : result.entrySet()) {
                String deliveryMethod = entry.getKey();
                List<String> productsInGroup = entry.getValue();
                // Sprawdzenie czy produkt można dodać do istniejącej grupy dostaw
                if (productsInGroup.stream().allMatch(product -> canDeliverWithMethod(product, item))) {
                    productsInGroup.add(item);
                    addedToGroup = true;
                    break;
                }
            }
            // Jeśli produkt nie pasuje do żadnej istniejącej grupy, utwórz nową grupę dostaw
            if (!addedToGroup) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(item);
                result.put(item, newGroup);
            }
        }
        return result;
    }

    private boolean canDeliverWithMethod(String product, String deliveryMethod) {
        // Wyszukaj możliwe sposoby dostawy dla produktu
        List<String> methodsForProduct = config.getOrDefault(product, Collections.emptyList());
        // Sprawdź czy dana metoda dostawy jest dostępna dla produktu
        return methodsForProduct.contains(deliveryMethod);
    }


}
