package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ArrayListAPIs {

  public static void main(String[] args) {

    List<String> animals = new ArrayList();
    animals.add("dog");
    animals.add("cat");
    animals.add(2,"tiger");

    int s = animals.size();

    String firstElement = animals.get(0);

    boolean hasCat = animals.contains("cat");

    int catIndex = animals.indexOf("cat");

    boolean isCatRemoved = animals.remove("cat");
    String removedElement = animals.remove(1);

    animals.sort(String::compareToIgnoreCase);

    System.out.println(Arrays.toString(animals.toArray()));
  }

}
