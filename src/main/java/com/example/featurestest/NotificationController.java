package com.example.featurestest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("/notification")
@Slf4j
public class NotificationController {

    private final BlockingQueue<DeferredResult<String>> waitlist = new ArrayBlockingQueue<>(50);

    @GetMapping("/product")
    DeferredResult<String> newProduct() {
        var response = new DeferredResult<String>();
        var added = waitlist.offer(response);
        if (!added) {
            response.setErrorResult("Waitlist is full");
        }
        return response;
    }

    @Async
    @EventListener
    public void onProductCreated(Product product) {
        log.info("Notify new created product {}, waiting list: {}", product, waitlist.size());
        int currentSize = waitlist.size();
        for (int i = 0; i < currentSize; i++) {
            Objects.requireNonNull(waitlist.poll()).setResult(product.id());
        }
    }
}
