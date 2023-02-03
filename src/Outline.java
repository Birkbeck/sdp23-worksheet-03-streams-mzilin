import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Outline {

  public static List<String> getList() {
    return List.of("hi", "bat", "ear", "hello", "iguana",
            "beaver", "winterland", "elephant", "eye", "qi");
  }

  // Loop through the words and print each one on a separate line,
  // with two spaces in front of each word.
  public static void question1() {
    List<String> words = getList();
    System.out.println("1: ");
    // YOUR CODE
    words.forEach(val -> System.out.println("  " + val));
  }

  // Repeat this problem but without two spaces in front of each word.
  // This should be trivial if you use the same approach as the previous
  // question; the point here is to make use of a method reference.
  public static void question2() {
    List<String> words = getList();
    System.out.println("2: ");
    // YOUR CODE
    words.forEach(System.out::println);
  }

  // For each of the following lambda expressions (see Question 5 in Worksheet 2),
  // produce the list that contains the elements of the original list
  // that satisfy the predicate defined by the lambda expression
  // (use the filter stream operation):
  //  - s -> s.length() < 4 (strings with no more than 3 characters),
  //  -  s -> s.contains("b") (strings containing "b"),
  // s -> (s.length() % 2) == 0 (strings of even length).

  public static void question3() {
    List<String> words = getList();
    System.out.println("3:");
    // YOUR CODE
    List<String> shortWords = words.stream()
            .filter(s -> s.length() < 4).toList();
    System.out.println("shortWords: " + shortWords);

    List<String> wordsWithB = words.stream()
            .filter(s -> s.contains("b")).toList();
    System.out.println("wordsWithB: " + wordsWithB);

    List<String> evenWords = words.stream()
            .filter(s -> (s.length() % 2) == 0).toList();
    System.out.println("evenWords: " + evenWords);
  }


  // For each of the following lambda expressions (see Question 7 in Worksheet 2),
  // produce the list that contains the results of applying the function
  // defined by the lambda expression to each element of the original list
  // (use the map stream operation):
  // - s -> s + "!",
  //  s -> s.replace("i", "eye"),
  //  s -> s.toUpperCase().

  public static void question4() {
    List<String> words = getList();
    System.out.println("4:");
    // YOUR CODE
    List<String> excitingWords = words.stream()
            .map(s -> s + "!").toList();
    System.out.println("excitingWords: " + excitingWords);

    List<String> eyeWords = words.stream()
            .map(s -> s.replace("i", "eye")).toList();
    System.out.println("eyeWords: " + eyeWords);

    List<String> uppercaseWords = words.stream()
            .map(String::toUpperCase).toList();
    System.out.println("uppercaseWords: " + uppercaseWords);
  }


  // (*) Turn the strings in the list into uppercase, keep only the
  // ones that are shorter than four characters, and, of what is remaining,
  // keep only the ones that contain "e", and print the first result.
  // Repeat the process, except checking for a "q" instead of an "e".

  public static void question5() {
    List<String> words = getList();
    System.out.println("5a:");
    // YOUR CODE
    Optional<String> result = words.stream()
            .map(String::toUpperCase)
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains("E"))
            .findFirst();
    System.out.println(result);

    System.out.println("5b:");
    // YOUR CODE
    result = words.stream()
            .map(String::toUpperCase)
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains("Q"))
            .findFirst();
    result.ifPresent(System.out::println);
  }


  // (** ) The above example uses lazy evaluation, but it is not easy to see
  // that it is doing so. Create a variation of the above example that shows
  // that it is doing lazy evaluation. The simplest way is to track which
  // entries are turned into upper case.

  public static void question6() {
    List<String> words = getList();
    System.out.println("6:");
    // YOUR CODE
    Optional<String> result = words.stream()
            .map(String::toUpperCase)
            .peek(s -> System.out.println("pre-filter1: " + s))
            .filter(s -> s.length() < 4)
            .peek(s -> System.out.println("pre-filter2: " + s))
            .filter(s -> s.contains("E"))
            .findFirst();
    System.out.println(result);
  }

  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Hint: use a map operation that turns the words into upper case,
  // followed by a reduce operation that concatenates them.

  public static void question7() {
    List<String> words = getList();
    System.out.println("7:");
    // YOUR CODE
    String concat = words.stream()
            .map(String::toUpperCase)
            .reduce("", (a, b) -> a + b);
    System.out.println(concat);
  }


  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Use a single reduce operation, without using map.

  public static void question8() {
    List<String> words = getList();
    System.out.println("8:");
    // YOUR CODE
    String concat = words.stream()
            .reduce("", (a, b) -> a.toUpperCase() + b.toUpperCase());
    System.out.println(concat);
  }

  // (*) Produce a String that is all the words concatenated together, but
  // with commas in between. For example, the result should be "hi,hello,...".
  // Note that there is no comma at the beginning, before "hi", and also no comma
  // at the end, after the last word.

  public static void question9() {
    List<String> words = getList();
    System.out.println("9:");
    // YOUR CODE
    String concat = words.stream()
            .collect(Collectors.joining(","));
//    String concat = String.join(",", words);
    System.out.println(concat);
  }

  // ------------------------

  // Use streams to filter the first two meat dishes.

  public static void question10() {
    System.out.println("10:");
    List<Dish> menu = Dish.getMenu().stream()
            .filter(d -> d.type() == Dish.Type.MEAT)
            .limit(2)
            .toList();
    System.out.println(menu);
  }


  // Count the number of dishes in a stream using the map and reduce methods.

  public static void question11() {
    System.out.println("11:");
    int numberOfDishes = Dish.getMenu().stream()
            .map(d -> 1)
            .reduce(0, Integer::sum);
    System.out.println("numberOfDishes: " + numberOfDishes);
  }


  public static Integer[] getIntegerArray() {
    return new Integer[] { 1, 7, 3, 4, 8, 2 };
  }


  // Given a list of numbers, print out the list of the squares of each number.
  // For example, given `[1, 2, 3, 4, 5]` you should print `[1, 4, 9, 16, 25]`.

  public static void question12() {
    System.out.println("12:");
    List<Integer> squares = Stream.of(getIntegerArray())
            .map(x -> x * x)
            .toList();
    System.out.println("squares: " + squares);
  }


  // Given two lists of numbers, print out all pairs of numbers. For example,
  // given a list `[1, 2, 3]` and a list `[3, 4]` you should print:
  //    `[[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]`.
  //  For simplicity, you can represent each *pair* as a list with two elements.

  public static void question13() {
    System.out.println("13:");
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(3, 4);
    List<List<Integer>> pairs = list1.stream()
            .flatMap(x -> list2.stream()
                    .map(y -> List.of(x, y)))
            .toList();
    System.out.println("pairs: " + pairs);
  }


  // Extend the previous example to return only pairs whose
  // sum is divisible by `3`. For example, `[2, 4]` and `[3, 3]` are valid.

  public static void question14() {
    System.out.println("14:");
    List<Integer> list1 = List.of(1, 2, 3);
    List<Integer> list2 = List.of(3, 4);
    List<List<Integer>> pairs = list1.stream()
            .flatMap(x -> list2.stream()
                    .filter(y -> (x + y) % 3 == 0)
                    .map(y -> List.of(x, y)))
            .toList();
    System.out.println("pairs: " + pairs);
  }


  // (*) Provide three ways to use streams to compute the sum of a list of
  // numbers.

  public static void question15() {
    System.out.println("15:");
  }


  // (*) Write a static method that produces a `List` of a specified length of
  //    random numbers. For example,
  //
  //```.java
  //    List<Double> nums = randomNumberList(someSize);
  //```
  // Result is something like `[0.7096867136897776, 0.09894202723079482, ...]`

  public static void question16() {
    System.out.println("16:");

  }


  // (*) Write a static method that produces a `List` of numbers that go in order
  //    by a step size. For example,
  //
  //```.java
  //    List<Integer> nums = orderedNumberList(50, 5, someSize);
  //```
  //  Result is `[50, 55, 60, ...]`.

  public static void question17() {
    System.out.println("17:");
  }


  // (*) Rewrite one of the solutions from Question 15 so that it can be executed
  // in parallel; verify that you get the same answer as for the sequential code.

  public static void question18() {
    System.out.println("18:");
  }


  // (**) Now, use streams to compute the product of some doubles. Show that the *serial*
  // and *parallel* versions **do not** always result in the same answer.
  //
  //    **Note:**
  //
  //    This is a bit tricky, because it seems at first that multiplication is associative,
  //as required by the parallel `reduce`.
  //    Also, it will be impossible to have differing results if you have a single-core computer!

  public static void question19() {
    System.out.println("19:");
  }

  // ------------------------

  public static void main(String... args) { // varargs alternative to String[]
    question1();
    question2();
    question3();
    question4();
    question5();
    question6();
    question7();
    question8();
    question9();

    question10();
    question11();
    question12();
    question13();
    question14();
    question15();
    question16();
    question17();
    question18();
    question19();

  }
}