package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class CheckName {
    //ищем слово в котором все символы одинаковы
    public int counter(String nickName) {
        int count = 0;
        for (int j = 0; j < nickName.length(); j++) {
            if (nickName.charAt(j) == nickName.charAt((nickName.length() - 1))) {
                count++;
            } else {
                count = 0;
                break;
            }

        }
        return count;
    }

    //ищем полиндромы
    public int counterPolindrom(String nickName) {
        int countPoli = 0;
        for (int j = 0; j < nickName.length(); j++) {
            if (nickName.charAt(j) == nickName.charAt((nickName.length() - 1) - j)) {
                countPoli++;
                if (((j == ((nickName.length() - 1) - j) && (nickName.length() - 1) % 2 == 0) || (j == ((nickName.length()) - j) && (nickName.length() - 1) % 2 != 0))
                        && nickName.charAt(j) == nickName.charAt((nickName.length() - 1))) {
                    countPoli--;
                }

            } else {
                countPoli = 0;
                break;
            }
        }
        return countPoli;
    }

    //ищем слово где буквы в порядке возрастания
    public int counterIncreas(String nickName) {
        int increas = 0;
        for (int j = 0; j < nickName.length(); j++) {
            if (j < nickName.length() - 1) {
                if (nickName.charAt(j) <= nickName.charAt(j + 1) && nickName.charAt(j) != nickName.charAt((nickName.length() - 1))) {
                    increas++;
                }
                else {
                    increas = 0;
                    break;
                }
            }
        }
        return increas;
    }
}
