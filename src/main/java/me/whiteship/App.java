package me.whiteship;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
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
    }
}
