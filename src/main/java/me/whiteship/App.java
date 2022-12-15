package me.whiteship;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static me.whiteship.Book2.A;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        final Class<Book> bookClass = Book.class;
        final Book book = new Book();
        //필드 가져오기
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        System.out.println("=================================================");
        //메소드 가져오기
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

        System.out.println("=================================================");
        //생성자 가져오기
        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);

        System.out.println("=================================================");
        //상속된 클래스 가져오기
        System.out.println(MyBook.class.getSuperclass());

        System.out.println("=================================================");
        //인터페이스 가져오기
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        //어노테이션 인터페이스 가져오기
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);

        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    final MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.value());
                    System.out.println(myAnnotation.number());
                }
            });
        });

        System.out.println("=================================================");
        //리플렉션. 생성자로 가져오기
        final Class<?> bookClass2 = Class.forName("me.whiteship.Book2");
        final Constructor<?> constructor = bookClass2.getConstructor(String.class);
        final Book2 book2 = (Book2) constructor.newInstance("myBook");
        System.out.println("book2 = " + book2);

        System.out.println("=================================================");
        //리플렉션. 필드로 가져오기1
        final Field a = Book2.class.getDeclaredField("A");
        System.out.println(a.get(null));
        a.set(null, "AAAAAAA");
        System.out.println(a.get(null));

        //리플렉션. 필드로 가져오기2
        final Field b = Book2.class.getDeclaredField("B");
        b.setAccessible(true); // private 으로 되어있는 필드여서 해제 시킴
        System.out.println(b.get(book2));
        b.set(book2, "BBBBBBBB");
        System.out.println(b.get(book2));

        final Method c = Book2.class.getDeclaredMethod("sum", int.class, int.class);
        final int invoke = (int) c.invoke(book2, 1, 2);
        System.out.println("invoke = " + invoke);
    }
}