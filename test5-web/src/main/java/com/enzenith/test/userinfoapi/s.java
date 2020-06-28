/**
 * @author: LinWeiDa
 * @date: 2020-06-08 9:22
 */
package com.enzenith.test.userinfoapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {阐述类的作用}
 * @author: LinWeiDa
 * @date: 2020-06-08 9:22
 */
public class s {
    public static void main(String[] args) {
        List<String> allList = new ArrayList<String>();
        allList.add("3748E31EC7844BD59D031CC954DE4722");
        allList.add("B29A90C13D4C40B1BC4BAEC227DDE15C");
        allList.add("3EBF4870F3DE4BA684DB36D1A3DCF4C1");
        allList.add("4C696996CCAF4EDE8BADF22C1BC74A0D");
        allList.add("69B976F5FAE7410FAF2831A78D2CBBEC");
        List<String> alreadyList = new ArrayList<String>();
        alreadyList.add("B29A90C13D4C40B1BC4BAEC227DDE15C");
        alreadyList.add("69B976F5FAE7410FAF2831A78D2CBBEC");
        alreadyList.add("3748E31EC7844BD59D031CC954DE4722");
        List<String> noList = allList.stream().filter(num ->!alreadyList.contains(num))
                .collect(Collectors.toList());
        System.out.println("noList:"+noList);

    }
}
