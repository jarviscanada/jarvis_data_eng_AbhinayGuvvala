package ca.jrvs.practice.dataStructure.list;

import java.util.Collections;
import java.util.LinkedList;

public class LinkedListAPIs {

  public static void main(String[] args) {

    LinkedList<String> route = new LinkedList();

    route.add("HYD");
    route.add(1,"BOM");

    route.add("DEL");
    route.add("FRA");
    route.addLast("YYZ");
    route.addFirst("VGA");

    boolean isDELRemoved = route.remove("DEL");
    String removedStop = route.removeFirst();

    Collections.sort(route);
    System.out.println(route);

  }

}
