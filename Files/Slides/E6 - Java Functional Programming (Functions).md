# Functions

### Why Java is changing?
A typical problem in 1991: model a company employees, departments, salaries, etc.

```
public class Employee {
    String name;
    String lastname;
    double salary;
    Department department;
    ...
}
```
The rise of **big-data**, and **affordable multicore architectures** made functional programming concepts popular.

A typical problem in 2023: analyse a 100GB dataset for finding lazy employees :)

```
List<Employee> l = employees.stream()
.filter(e -> e.getVacations() > 1)
.collect(Collectors.toList());
```

Languages need to evolve to track changing hardware or programmers expectations. Otherwise, they die (COBOL, LISP, …)

### Functional vs imperative programming
Because of changing needs, a number of languages (Java, Python, Scala) are introducing ways for supporting functional programming. Haskell is a recent purely functional language.

The functional programming paradigm describes a program by applying and composing functions.
  * Passing functions to functions (**behaviour parametrization**)
  * Functional programs can be very terse and elegant, packing a lot of behaviour into very few lines of code. Functional programmers make the case that in a multicore world, you need to avoid mutable state in order to scale out your programs.

The imperative programming paradigm allows you to describe a program in terms of a sequence of statements that mutate state.
  * Passing objects to functions (**value parametrization**)
  * Object-oriented programmers retort that in actual business environments object-oriented programming scales out well in terms of developers and, as an industry, we know how to do it.

![](images/behaviour-parametrization.png)

### Lambda expressions
By **lambda expression** (or just "a lambda"), we mean a **function** that isn't bound to its name (an anonymous function) but can be assigned to a variable.

The most general form of a lambda expression looks like this:

`(parameters) -> { body };`.

The brackets `{ }` are required only for multi-line lambda expressions:

`(parameters) -> expression`

The part before `->` is the list of parameters (like in methods), and the part after that is the body that can return a value.

Even if a lambda doesn't have a value to return, it has a body that does some useful actions (e.g. prints or saves something).

### Lambda expressions (examples)
A boolean expression: `(List<String> list) -> list.isEmpty()`

Creating objects: `() -> new Apple(45)`

Consuming from an object: `(Apple a) -> { System.out.println(a.getWeight()); }`

Select/extract a field from an object: `(String s) -> s.length()`

Multiply two ints: `(int a, int b) -> a * b`

Compare two objects: `(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())`

# A case study: filtering and printing students
We need to select all students with a given average, and also print a string representation of the selected students.

```
public static List<Student> filterStudentsByGrade(List<Student> students, double average) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (s.getAverage() == average) {
            System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
            result.add(s);
        }
    }
    return result;
}
```

After a while, requirements change, and you need to select all the students with an average comprised within a given range. 
You can add an alternative method. 

```
public static List<Student> filterStudentsByGradeRange(List<Student> students, double low, double high) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (s.getAverage() >= low && s.getAverage() <= high) {
            System.out.println(String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()));
            result.add(s);
        }
    }
    return result;
}
```

However, this approach breaks the **DRY (Don’t Repeat Yourself)** principle. The two methods vary only in one line!

### Strategy Pattern

We can define a set of interfaces and implement the [Strategy Pattern](https://refactoring.guru/design-patterns/strategy). Different classes can implement different strategies.

Behaviour parameterization is great because it separates the logic of iterating the collection from the behaviour to be applied on each element of that collection.


```
public interface StudentPredicate {
    boolean test(Student s);
}

public interface StudentFunction {
    String apply(Student s);
}

public interface StudentConsumer {
    void accept(String s);
}
```

Our function becomes much more flexible than our first attempt, and at the same time it’s easy to read and to use!

```
public static List<Student> filterStudents(List<Student> students, StudentPredicate sp, StudentFunction sf, StudentConsumer sc) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (sp.test(s)) {
            String str = sf.apply(s);
            sc.accept(str);
            result.add(s);
        }
    }
    return result;
}
```
This code is much more flexible than our first attempt, and at the same time it’s easy to read and to use!

However, when you want to pass new behaviour to your filterStudent method, you’re forced to one of the following two options:

* (a) define classes implementing the interfaces and then instantiate the needed objects (verbose)!
* (b) define anonymous implementations of the interfaces (better but still verbose!)

Verbosity, in general, is bad because verbose code takes a long time to write and maintain.

Example of (a):
```
class StudentGoodPredicate implements StudentPredicate {
	public boolean test(Student p) {
		return s.getAverage() >= 24;
	}
}

class StudentGoodRangePredicate implements StudentPredicate {
	public boolean test(Student p) {
		return s.getAverage() >= 24 && s.average <= 28;
	}
}
```

Example of (b):
```
result = filterStudents(students, new StudentPredicate() {
    @Override
    public boolean test(Student s) {
        return s.getAverage() >= 26 && s.getAverage() <= 30;
    }
}, new StudentFunction() {
    @Override
    public String apply(Student s) {
        return String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage());
    }
}, new StudentConsumer() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});
```

### Functional interfaces
Functional interfaces or **interfaces defining only one method** are ideal candidates for making use of lambda expressions.

Instead of using anonymous classes (still verbose!), **lambda expressions can be used for providing the implementation of their single method**!

```
public interface StudentPredicate {
    boolean test(Student s);
}
```

```
// using anonymous classes
StudentPredicate p = new StudentPredicate() {
    @Override
    public boolean test(Student s) {
        return s.getAverage() >= 26 && s.getAverage() <= 30;
    }
}

// using lambda expressions:
StudentPredicate p = s -> s.getAverage() >= 26 && s.getAverage() <= 30
```

### Strategy Pattern + Lambda expressions

```
public static List<Student> filterStudents(List<Student> students, StudentPredicate sp, StudentFunction sf, StudentConsumer sc) {
    List<Student> result = new ArrayList<>();
    for (Student s : students) {
        if (sp.test(s)) {
            String str = sf.apply(s);
            sc.accept(str);
            result.add(s);
        }
    }
    return result;
}
```

```
result = filterStudents(students, 
    s -> s.getAverage() >= 26 && s.getAverage() <= 30, 
    s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()), 
    s -> System.out.println(s));
```

### Strategy Pattern + Lambda expressions + Generics
```
public interface Predicate<T> {
    boolean test(T s);
}

public interface Function<T, R> {
    R apply(T s);
}

public interface Consumer<T> {
    void accept(T s);
}
```

``` 
public static <T, R> List<T> filter(List<T> l, Predicate<T> sp, Function<T, R> sf, Consumer<R> sc) {
    List<T> result = new ArrayList<>();
    for (T s : l) {
        if (sp.test(s)) {
            R x = sf.apply(s);
            sc.accept(x);
            result.add(s);
        }
    }
    return result;
}
```

```
result = filterStudents(students, 
    s -> s.getAverage() >= 26 && s.getAverage() <= 30, 
    s -> String.format("%s_%s_%f", s.getLastname(), s.getName(), s.getAverage()), 
    s -> System.out.println(s));
```

### java.util.function package
The most simple and general case of a lambda is a functional interface with a method that receives one value and returns another. This function of a single argument is represented by the Function interface, which is parameterized by the types of its argument and a return value:

```
public interface Function<T, R> {
    R apply(T t);
}
```

```
// if it has only one argument "()" are optional
Function<Integer, Integer> adder1 = x -> x + 1;

// with type inference
Function<Integer, Integer> mult2 = (Integer x) -> x * 2;

// with multiple statements
Function<Integer, Integer> adder5 = (x) -> {
    x += 2;
    x += 3;
    return x;
};

// with two different types
Function<String, String> quote = s -> "'" + s + "'";
```

Let's consider a single-line lambda expression that just checks whether the first number is divisible by the second one.

```
BiFunction<Integer, Integer, Boolean> isDivisible = (x, y) -> x % y == 0;
```

The expression has the type `BiFunction<Integer, Integer, Boolean>` which means, that it takes two `Integer` values and returns a `Boolean` value.

```
@FunctionalInterface
public interface BiFunction<T,U,R> {
    R apply(T t, U u)
}
```


### Primitive Function Specializations

Since a primitive type can’t be a generic type argument, there are versions of the Function interface for the most used primitive types double, int, long, and their combinations in argument and return types:

* IntFunction, LongFunction, DoubleFunction: arguments are of specified type, return type is parameterized

```
@FunctionalInterface
public interface IntFunction<R> {
    R apply(int value);
}
```

* ToIntFunction, ToLongFunction, ToDoubleFunction: return type is of specified type, arguments are parameterized

```
@FunctionalInterface
public interface ToIntFunction<T> {
    int apply(T value);
}
```

* DoubleToIntFunction, DoubleToLongFunction, IntToDoubleFunction, IntToLongFunction, LongToIntFunction, LongToDoubleFunction: having both argument and return type defined as primitive types, as specified by their names

```
@FunctionalInterface
public interface DoubleToIntFunction {
    int apply(double value);
}
```

### Two-Arity Function Specializations
To define lambdas with two arguments, we have to use additional interfaces that contain “Bi” keyword in their names: BiFunction, ToDoubleBiFunction, ToIntBiFunction, and ToLongBiFunction.

BiFunction has both arguments and a return type generified, while ToDoubleBiFunction and others allow us to return a primitive value.

```
@FunctionalInterface
public interface BiFunction<T,U,R> {
    R apply(T t, U u);
}
```

```
@FunctionalInterface
public interface ToDoubleBiFunction<T,U> {
    double apply(T t, U u);
}
```

One of the typical examples of using this interface in the standard API is in the Map.replaceAll method, which allows replacing all **values** in a `Map<K,V>` with a BiFunction:

```
default void replaceAll(BiFunction<? super K,? super V,? extends V> function)
```

```
Map<String, Integer> salaries = new HashMap<>();
salaries.put("John", 40000);
salaries.put("Freddy", 30000);
salaries.put("Samuel", 50000);

salaries.replaceAll((name, oldValue) ->
name.equals("Freddy") ? oldValue : oldValue + 10000);
```

### Suppliers
The Supplier functional interface is yet another Function specialization that does not take any arguments. 

```
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

We typically use it for lazy generation of values. For instance, let's define a function that squares a double value. It will not receive a value itself, but a Supplier of this value:

```
Supplier<LocalDateTime> supplier = () -> LocalDateTime.now();
LocalDateTime time = supplier.get();
```

### Consumers
As opposed to the Supplier, the Consumer accepts a generified argument and returns nothing. It is a function that is representing side effects.

```
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

For instance, let’s greet everybody in a list of names by printing the greeting in the console. The lambda passed to the List.forEach method implements the Consumer functional interface:

```
List<String> names = Arrays.asList("John", "Freddy", "Samuel");
names.forEach(name -> System.out.println("Hello, " + name));
```

One of its use cases is iterating through the entries of a map via the Biconsumer interface specialization:

```
@FunctionalInterface
public interface BiConsumer<T,U> {
    void accept(T t, U u);
}
```

```
Map<String, Integer> ages = new HashMap<>();
ages.put("John", 25);
ages.put("Freddy", 24);
ages.put("Samuel", 30);

ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
```

### Predicates
In mathematical logic, a predicate is a function that receives a value and returns a boolean value.

The Predicate functional interface is a specialization of a Function that receives a generified value and returns a boolean. A typical use case of the Predicate lambda is to filter a collection of values:

```
List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");
names.removeIf(s -> s.startsWith("B"));
```

```
List<E> {
    ...
    boolean removeIf(Predicate<? super E> filter);
    ...
}
```
In the code above, we remove from a list the names that start with the letter “B”. The Predicate implementation encapsulates the filtering logic.

As in all of the previous examples, there are IntPredicate, DoublePredicate and LongPredicate versions of this function that receive primitive values.

### Operators
Operator interfaces are special cases of a function that receive and return the same value type. 

The UnaryOperator interface receives a single argument.

One of its use cases in the Collections API is to replace all values in a list with some computed values of the same type:

```
List<E> {
    ...
    default void replaceAll(UnaryOperator<E> operator);
    ...
}
```

```
List<String> names = Arrays.asList("bob", "josh", "megan");
names.replaceAll(name -> name.toUpperCase());
```

The List.replaceAll function returns void as it replaces the values in place. To fit the purpose, the lambda used to transform the values of a list has to return the same result type as it receives. This is why the UnaryOperator is useful here.

### Passing lambda expressions to methods

One of the most popular cases is to pass a lambda expression to a method and then call it there.

Look at the method below. It takes an object of the standard generic `Function` type.

```
static void printResultOfLambda(Function<String, Integer> function, String message) {
    System.out.println(function.apply(message));
}
```

This function can take a `String` argument and return an `Integer` result. To test the method, let's create an object and pass it into the method:

```
// it returns the length of a string
Function<String, Integer> f = s -> s.length();
printResultOfLambda(f, "Happy new year 3000!"); // prints 20
```

You can also pass a lambda expression to the method directly without an intermediate reference:

```
// passing without a reference
printResultOfLambda(s -> s.length(), "Happy new year 3000!"); // prints 20
```

We can present our function as an object and pass it to a method as its argument, if the method takes an object of that type. Then, inside the method, the given function will be invoked.

We do not pass data to the `printResultOfLambda`, but rather some piece of code as data. So, we can parameterize the same method with a different behavior at runtime. This is what typical uses of lambda expressions look like. Many standard methods can accept lambda expressions.

### Adding generics
```
static <T,U> void printResultOfLambda(Function<T, U> function, T message) {
    System.out.println(function.apply(message));
}
```

```
Function<String, Integer> f1 = s -> s.length();
printResultOfLambda(f1, "Happy new year 3000!"); // it prints 20

Function<Integer, String> f2 = n -> String.valueOf(n).repeat(n);
printResultOfLambda(f2, 4); // it prints 4444
```

### Make code clearer with method references
By method reference, we mean a function that refers to a particular method via its name and can be invoked any time we need it. The base syntax of a method reference looks like this:

```
objectOrClass :: methodName
```

where `objectOrClass` can be a **class name** or a **particular instance** of a class.

Here is an example, we create a reference to the standard static method `max` of the `Integer` class.

```
// lambda expression
BiFunction<Integer, Integer, Integer> max = (x, y) -> Integer.max(x, y);
```

```
// method reference
BiFunction<Integer, Integer, Integer> max = Integer::max;
```

Here, `Integer::max` is a method reference to a static method.

This code works because the definition of the method `int max(int a, int b)` fits the type `BiFunction<Integer, Integer, Integer>`: they both mean taking two integer arguments and returning an integer value.

Now we have the `max` object that can be used as a function by invoking the `apply` method. Let's invoke it!

```
System.out.println(max.apply(50, 70)); // 70
```

It is recommended to use method references rather than lambda expressions if you just need to invoke a standard method without other operations. Your code will be shorter, more readable, and easier to test.

### Kinds of method references

It's possible to write method references to both static and instance (non-static) methods.

In general, there are four kinds of method references:

-   reference to a static method;
-   reference to an instance method of an existing object;
-   reference to an instance method of an object of a particular type;
-   reference to a constructor.

### Reference to a static method

The general form is the following:

```
ClassName :: staticMethodName
```

Let's take a look at the reference to the static method `sqrt`of the class `Math`:

```
Function<Double, Double> sqrt = Math::sqrt;
```

Now we can invoke the `sqrt` method for double values:

```
sqrt.apply(100.0d); // the result is 10.0d
```

The `sqrt` method can be also written using the following lambda expression:

```
Function<Double, Double> sqrt = x -> Math.sqrt(x);
```

### Reference to an instance method of an object

The general form looks like this:

```
objectName :: instanceMethodName
```

Let's check out the example of a reference to the `indexOf` method of a particular string.

```
String whatsGoingOnText = "What's going on here?";

Function<String, Integer> indexWithinWhatsGoingOnText = whatsGoingOnText::indexOf;
```

Here is the result of applying it to different arguments:

```
System.out.println(indexWithinWhatsGoingOnText.apply("going")); // 7
System.out.println(indexWithinWhatsGoingOnText.apply("Hi"));    // -1
```

As you can see, actually we always work with the `whatsGoingOnText` object captured from the context.

The following example of a lambda expression is a full equivalent of the reference above and can make your understanding of the situation better:

```
Function<String, Integer> indexWithinWhatsGoingOnText = string -> whatsGoingOnText.indexOf(string);
```

### Reference to an instance method of an object of a particular type

Here is a general form of a reference:

```
ClassName :: instanceMethodName
```

In that case, you need to pass an instance of the class as the function argument.

Let's focus on the following reference to an instance of the method `doubleValue` of the class `Long`:

```
Function<Long, Double> converter = Long::doubleValue;
```

Now we can invoke the `converter` for long values:

```
converter.apply(100L); // the result is 100.0d
converter.apply(200L); // the result is 200.0d
```

Also, we can write the same converter using the following lambda expression:

```
Function<Long, Double> converter = val -> val.doubleValue();
```

### Reference to a constructor

This reference has the following declaration:

```
ClassName :: new
```

For example, let's consider our custom class `Person` with a single field `name`.

```
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}
```

Here is a reference to the constructor of this class:

```
Function<String, Person> personGenerator = Person::new;
```

This function produces new `Person` objects based on their names.

```
Person johnFoster = personGenerator.apply("John Foster"); // we have a John Foster object
```

Here is the corresponding lambda expression that does the same.

```
Function<String, Person> personGenerator = name -> new Person(name);
```

### Summarizing: implementing functional interfaces

There are several ways to implement a functional interface. As you know from the previous OOP theory, it is impossible to directly create an instance of an interface. Let's consider three ways to do it.

**1) Anonymous classes.**

A functional interface can be implemented by using an anonymous class or regular inheritance.

To implement a functional interface let's create an anonymous class and override the `apply` method. The overridden method calculates the square of a given value:

```
Function<Long, Long> square = new Function<Long, Long>() {
    @Override
    public Long apply(Long value) {
        return value * value;
    }
};

long val = square.apply(10L); // the result is 100L
```

In this example, we model a math function that squares a given value. This code works perfectly but it is a bit unclear since it contains a lot of extra characters to perform a single line of useful code.

We won't give you an example of a regular class because it has the same (and even more) disadvantages.

**2) Lambda expressions.**

A functional interface can also be implemented and instantiated by using a lambda expression.

```
Func<Long, Long> square = value -> value * value;

long val = square.apply(10L); // the result is 100L
```

The type of the functional interface (left) and the type of the lambda (right) are the same from a semantic perspective. Parameters and the result of a lambda expression correspond to the parameters and the result of **a single abstract method of the functional interface**.

The code that creates a lambda expression may look as if the object of an interface type is created. As you know, it is impossible. Actually, the Java compiler automatically creates a special intermediate class that implements the functional interface and then creates an object of this class rather than an object of an interface type. The name of such a class may look like `Functions$$Lambda$14/0x0000000100066840` or something similar.

**3) Method references.**

Another way to implement a functional interface is by using method references. In this case, the number and type of arguments and the return type of a method should match the number and types of arguments and the return type of the single abstract method of a functional interface.

Suppose, there is a method `square` that takes and returns a `long` value:

```
class Functions {

    public static long square(long value) {
        return value * value;
    }
}
```

The argument and the return type of this method fits the `Func<Long, Long>` functional interface. This means we can create a method reference and assign it to an object of the `Function<Long, Long>` type:

```
Function<Long, Long> square = Functions::square;
```

Keep in mind, that the compiler creates an intermediate hidden class that implements the `Function<Long, Long>` interface in a similar way to the case of lambda expressions.

### null: the billion-dollar mistake
Like many programming languages, Java uses `null` to represent the absence of a value. Sometimes this approach leads to exceptions like **NPEs** since non-null checks make code less readable. 

The British computer scientist [Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare), the inventor of the `null` concept, even describes introducing `null` as a **"billion-dollar mistake"** since it has led to innumerable errors, vulnerabilities, and system crashes. 

To avoid the issues associated with `null`, Java provides the `Optional` class that is a safer alternative for standard `null` references.

### Optional values

The `Optional<T>` class represents the presence or absence of a value of the specified type `T`. An object of this class can be either **empty** or **non-empty**.

Let's look at an example. In the following code, we create two Optional objects called `absent` and `present`. The first object represents an empty value (such as `null`), and the second one keeps a real string value.

```
Optional<String> absent = Optional.empty();
Optional<String> present = Optional.of("Hello");
```

The `isPresent` method checks whether an object is empty or not:

```
System.out.println(absent.isPresent()); // false
System.out.println(present.isPresent()); // true
```

### Optionals and nullable objects

In a situation when you don't know whether a variable is `null` or not, you should pass it to the `ofNullable` method instead of the `of` method. It creates an empty Optional if the passed value is `null`.

The word `nullable` means that a variable is potentially `null`.

In the following example, the `getRandomMessage` method may return `null` or some string message. Depending on what is returned, the result will be different.

```
String message = getRandomMessage(); // it may be null

Optional<String> optMessage = Optional.ofNullable(message);

System.out.println(optMessage.isPresent()); // true or false
```

If the `message` is not `null` (e.g. `"Hello"`) the code will print `true`. Otherwise, it will print `false` because the Optional object is empty.

In a sense, `Optional` is like a box that contains either some value or nothing. It wraps a value or `null` keeping the possibility to check it by using special methods.

### Getting the value from an Optional

The most obvious thing to do with an Optional is to get its value. For now, we're going to discuss three methods with this purpose:

-   `get` returns the value if it's present, otherwise throws an exception;
-   `orElse(T other)` returns the value if it's present, otherwise returns `other`;
-   `orElseGet(Supplier<? extends T> other)` returns the value if it's present, otherwise invokes `other` and returns its result.

Let's see how they work. First, we use the `get` method to obtain the present value:

```
Optional<String> optName = Optional.of("John");
String name = optName.get(); // "John"
```

This code works well and returns the name `"John"` from the Optional. But if an Optional object is empty, the program throws `NoSuchElementException` exception.

```
Optional<String> optName = Optional.ofNullable(null);
String name = optName.get(); // throws NoSuchElementException
```

This is not exactly what we would expect from the class designed to reduce the number of exceptions.

Since Java 10, the preferred alternative to the `get` method is the `orElseThrow` method whose behavior is the same, but the name describes it better.

The `orElse` method is used to extract the value wrapped inside an Optional object or return some default value when the Optional is empty. The default value is passed to the method as its argument:

```
Optional<String> optName = Optional.ofNullable(null);
String name = optName.orElse("unknown");

System.out.println(name); // unknown
```

Unlike the previous example, this one doesn't throw an exception but returns a default value instead.

`orElseGet` method is quite similar, but it takes a **supplier function** to produce a result instead of taking some value to return:

```
String name = Optional
        .ofNullable(nullableName)
        .orElseGet(SomeClass::getDefaultResult);
```

In this example, we use the `getDefaultResult` method for producing a default result.

### Conditional actions

There are also convenient methods that take functions as arguments and perform some actions on values wrapped inside Optional:

-   `ifPresent` performs the given action with the value, otherwise does nothing;
-   `ifPresentOrElse` performs the given action with the value, otherwise performs the given empty-based action.

The `ifPresent` method allows us to run some code on the value if the Optional is not empty. The method takes a **consumer function** that can process the value.

The following example prints the length of a company's name by using the `ifPresent`.

```
Optional<String> companyName = Optional.of("Google");
companyName.ifPresent((name) -> System.out.println(name.length())); // 6
```

However, the following code does not print anything because the Optional object is empty.

```
Optional<String> noName = Optional.empty();
noName.ifPresent((name) -> System.out.println(name.length()));
```

It does not throw an exception due to performing the internal `null` check.

The "classic" equivalent of these two code snippets looks like the following:

```
String companyName = ...;
if (companyName != null) {
    System.out.println(companyName.length());
}
```

This code is more error-prone because it is possible to forget to perform the `null` check explicitly and then get the **NPE**.

The method `ifPresentOrElse` is a safer alternative to the whole `if-else` statement. It executes one of two functions depending on whether the value is present in the `Optional`.

```
Optional<String> optName = Optional.ofNullable(/* some value goes here */);

optName.ifPresentOrElse(
        (name) -> System.out.println(name.length()),
        () -> System.out.println(0)
);
```

If `optName` contains some value (like `"Google"`), the lambda expression is called and it prints the length of the name. If `optName` is empty, the second function prints `0` as the default value. Sometimes, developers call the second lambda expression a **fallback,** which is an alternative plan when something goes wrong (no value).