package org.example.utils;

import java.util.Arrays;

public class MathUtils {
    public static int min(int[] array) {
        return Arrays.stream(array).min().orElseThrow();
    }

    public static int max(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }
    public static
}
