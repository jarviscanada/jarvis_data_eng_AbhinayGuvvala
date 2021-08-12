package ca.jrvs.practice.dataStructure.list;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Employee implements Comparable<Employee> {

  private int id;
  private String name;
  private int age;
  private long salary;

  Locale canada = new Locale("en", "CA");
  Currency dollars = Currency.getInstance(canada);
  NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(canada);

  public Employee() {
  }

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  @Override
  public String toString(){
    return getId()+" "+getName()+" "+getAge()+" "+ dollarFormat.format(getSalary());
  }

  @Override
  public int compareTo(Employee o) {
    return Integer.compare(getAge(), o.getAge());
  }
}
