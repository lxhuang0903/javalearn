package com.yc.jmh.demo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * FileName: StringBuilderBenchmark
 *
 * @author: yuchao
 * @date: 2018/10/23
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBuilderBenchmark {
    @Benchmark
    public void testStringAdd() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        print(a);
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    private void print(String a) {
    }

    public static void main(String[] args) throws RunnerException {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/jmh-learn/target/benchmark.log";
        Options options = new OptionsBuilder()
                .include(StringBuilderBenchmark.class.getSimpleName())
                .output(path)
                .build();
        new Runner(options).run();
    }
}
