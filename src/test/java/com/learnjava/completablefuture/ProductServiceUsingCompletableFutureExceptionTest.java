package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @InjectMocks
    ProductServiceUsingCompletableFuture pscf;
    @Mock
    private ProductInfoService pisMock;
    @Mock
    private ReviewService rsMock;
    @Mock
    private InventoryService isMock;

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        // given
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
        when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException(("Exception occurred!")));
        when(isMock.retrieveInventory(any())).thenCallRealMethod();

        // when
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() >= 2);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertNotNull(product.getReview());
        assertEquals(0, product.getReview().getNoOfReviews());
    }


    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError() {
        // given
        String productId = "ABC123";
        when(pisMock.retrieveProductInfo(any()))
                .thenThrow(new RuntimeException(("Exception occurred!")));
        when(rsMock.retrieveReviews(any()))
                .thenCallRealMethod();

        // when

        // then
        Assertions.assertThrows(RuntimeException.class, () -> pscf.retrieveProductDetailsWithInventory_approach2(productId));
    }
}
