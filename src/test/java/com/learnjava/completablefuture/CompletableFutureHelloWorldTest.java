package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        // given

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        // then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

    @Test
    void helloWorld_multiple_async_calls() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_multiple_async_calls();

        // then
        assertEquals("HELLO WORLD!", hellowWorld);
    }

    @Test
    void helloWorld_3_async_calls() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_3_async_calls();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hellowWorld);
    }

    @Test
    void helloWorld_3_async_calls_log() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_3_async_calls_log();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hellowWorld);
    }

    @Test
    void helloWorld_3_async_calls_log_async() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_3_async_calls_log_async();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hellowWorld);
    }

    @Test
    void helloWorld_3_async_calls_threadpool() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_3_async_calls_custom_threadpool();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hellowWorld);
    }

    @Test
    void helloWorld_3_async_calls_threadpool_async() {
        // given

        // when
        String hellowWorld = cfhw.helloWorld_3_async_calls_custom_threadpool_async();

        // then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hellowWorld);
    }

    @Test
    void helloWorld_thenCompose() {
        // given
        startTimer();

        // when
        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        // then
        completableFuture
                .thenAccept(s -> {
                    assertEquals("HELLO WORLD!", s);
                })
                .join();

        timeTaken();
    }

    @Test
    void anyOf() {
        // given

        // when
        String hellowWorld = cfhw.anyOf();

        // then
        assertEquals("hello world", hellowWorld);
    }
}
