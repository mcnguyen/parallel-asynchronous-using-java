package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorld_3_async_calls_handle() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred!"));
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals(" WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred!"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception occurred, too!"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_handle_3() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_handle();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_exceptionally();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_exceptionally_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred!"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception occurred, too!"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_exceptionally();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_whenComplete() {
        //given
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_whenComplete_2() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred!"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception occurred, too!"));

        //when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }

    @Test
    void helloWorld_3_async_calls_whenComplete_3() {
        //given
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception occurred!"));
        when(helloWorldService.world()).thenCallRealMethod();

        //when
        String result = hwcfe.helloWorld_3_async_calls_whenComplete();

        //then
        assertEquals(" HI COMPLETABLEFUTURE!", result);
    }
}
