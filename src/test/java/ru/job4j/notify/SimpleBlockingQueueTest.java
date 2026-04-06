package ru.job4j.notify;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    void whenProducerOfferThenConsumerPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    queue.offer(1);
                    queue.offer(2);
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        result.add(queue.poll());
                        result.add(queue.poll());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result).containsExactly(1, 2);
    }

    @Test
    void whenQueueIsFullThenProducerWait() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    queue.offer(1);
                    queue.offer(2);
                    queue.offer(3);
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        result.add(queue.poll());
                        result.add(queue.poll());
                        result.add(queue.poll());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result).containsExactly(1, 2, 3);
    }

}