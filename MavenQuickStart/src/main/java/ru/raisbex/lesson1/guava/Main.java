package ru.raisbex.lesson1.guava;

import com.google.common.collect.Lists;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // работа со списками
        List<String> sourceList = Lists.newArrayList("apple", "banana", "cherry", "date", "elderberry");
        List<List<String>> partitionedList = Lists.partition(sourceList, 2);

        System.out.println("Original list: " + sourceList);
        System.out.println("Partitioned list: " + partitionedList);


        // работа с кэшом
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();

        cache.put("key1", "value1");
        cache.put("key2", "value2");

        System.out.println("Value for key1: " + cache.getIfPresent("key1"));
        System.out.println("Value for key2: " + cache.getIfPresent("key2"));
    }
}
