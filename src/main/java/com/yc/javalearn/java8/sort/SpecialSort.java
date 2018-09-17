package com.yc.javalearn.java8.sort;

import java.util.*;

/**
 * FileName: SpecialSort
 *
 * @author: yuchao
 * @date: 2018/9/5
 */
public class SpecialSort {

    public static void main(String[] args) {
        Book abc = new Book(1L, 2L, "abc");
        Book abc1 = new Book(2L, 4L, "abc");
        Book abc2 = new Book(3L, 3L, "abc");
        Book abc3 = new Book(4L, 3L, "abc");
        Book abc4 = new Book(5L, 3L, "abc");
        Book abc5 = new Book(6L, 4L, "abc");
        List<Book> books = Arrays.asList(abc, abc1, abc2, abc3, abc4, abc5);
        Collections.sort(books, Comparator.comparing(Book::getCategoryId));
        Map<Long, Integer> indexMap = new HashMap<>();
        List<List<Book>> list = new ArrayList<>();
        for (Book book : books) {
            Long categoryId = book.getCategoryId();
            Integer index = indexMap.get(categoryId) == null ? 0 : indexMap.get(categoryId);

            List l;
            if (list.size() == index) {
                l = new ArrayList<>();
                list.add(l);
            } else {
                l = list.get(index);
            }
            l.add(book);
            indexMap.put(categoryId, ++index);
        }
        list.stream().flatMap(List::stream).map(Book::getId).forEach(System.out::println);

    }


    static class Book {
        Long id;
        Long categoryId;
        String name;

        public Book(Long id, Long categoryId, String name) {
            this.id = id;
            this.categoryId = categoryId;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
