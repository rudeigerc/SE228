package bookstore.kafka;

import bookstore.model.Book;
import bookstore.model.Order;
import bookstore.model.OrderItem;
import bookstore.service.BookService;
import bookstore.service.OrderItemService;
import bookstore.service.OrderService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class OrderConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "bookstore");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("order"));

        Gson gson = new Gson();
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        OrderService orderService = (OrderService) context.getBean("orderService");
        OrderItemService orderItemService = (OrderItemService) context.getBean("orderItemService");

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> data = gson.fromJson(record.value(), Map.class);
                    Order order = gson.fromJson((String) data.get("order"), Order.class);
                    @SuppressWarnings("unchecked")
                    List<OrderItem> orderItems = gson.fromJson((String) data.get("orderItems"), new TypeToken<List<OrderItem>>(){}.getType());
                    @SuppressWarnings("unchecked")
                    List<Book> books = gson.fromJson((String) data.get("books"), new TypeToken<List<Book>>(){}.getType());

                    Integer orderId = orderService.addOrder(order);
                    for (OrderItem orderItem : orderItems) {
                        orderItem.setOrderId(orderId);
                        orderItemService.addOrderItem(orderItem);
                    }
                    for (Book book : books) {
                        bookService.updateBook(book);
                    }
                }
            }
        } catch (Exception e)  {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
