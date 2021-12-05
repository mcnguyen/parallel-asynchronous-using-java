package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {
    private HelloWorldService hws;

    public CompletableFutureHelloWorldException(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloWorld_3_async_calls_handle() {
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String hw = hello
                .handle((res, e) -> {
                    if (e != null) {
                        log("Exception is: " + e.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(world, (h, w) -> h + w) // " world!"
                .handle((res, e) -> {
                    if (e != null) {
                        log("Exception after world is: " + e.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                // " Hi CompletableFuture!"
                .thenApply(String::toUpperCase) // " HI COMPLETABLEFUTURE!"
                .join();

        timeTaken();

        return hw;
    }

    public String helloWorld_3_async_calls_exceptionally() {
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String hw = hello
                // only called when there is exception
                .exceptionally(e -> {
                    log("Exception is: " + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w) // " world!"
                .exceptionally(e -> {
                    log("Exception after world is: " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                // " Hi CompletableFuture!"
                .thenApply(String::toUpperCase) // " HI COMPLETABLEFUTURE!"
                .join();

        timeTaken();

        return hw;
    }

    public String helloWorld_3_async_calls_whenComplete() {
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
        CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String hw = hello
                .whenComplete((res, e) -> {
                    log("res is: " + res);
                    if (e != null) {
                        log("Exception is: " + e.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w) // " world!"
                .whenComplete((res, e) -> {
                    log("res is: " + res);
                    if (e != null) {
                        log("Exception after world is: " + e.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception after world is: " + e.getMessage());
                    return "";
                })
                .thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
                // " Hi CompletableFuture!"
                .thenApply(String::toUpperCase) // " HI COMPLETABLEFUTURE!"
                .join();

        timeTaken();

        return hw;
    }
}
