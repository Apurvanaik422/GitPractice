package org.example;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String [] arr ={"aka","ama","bus","car","train"};

        List<String> arrList =Arrays.asList(arr);

        arrList.stream().filter(modes ->modes.contains("train")).findFirst().orElse(null);
    }
}
