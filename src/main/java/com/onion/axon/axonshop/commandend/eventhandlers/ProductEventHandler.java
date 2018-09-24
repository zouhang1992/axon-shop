//package com.onion.axon.axonshop.commandend.eventhandlers;
//
//import com.onion.axon.axonshop.commandend.events.ProductCreatedEvent;
//import com.onion.axon.axonshop.commandend.events.ProductReservedEvent;
//import com.onion.axon.axonshop.commandend.events.ReserveCancelledEvent;
//import com.onion.axon.axonshop.persistence.entity.ProductEntity;
//import com.onion.axon.axonshop.persistence.repository.ProductRepository;
//import org.axonframework.eventhandling.EventHandler;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import static org.slf4j.LoggerFactory.getLogger;
//
//@Component
//public class ProductEventHandler {
//
//    private static final Logger LOGGER = getLogger(ProductEventHandler.class);
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @EventHandler
//    public void on(ProductCreatedEvent event){
//        productRepository.save(ProductEntity.builder()
//                        .productId(event.getProductId())
//                        .price(event.getPrice())
//                        .name(event.getName())
//                        .nums(event.getNums())
//                        .build()
//        );
//    }
//
//
//    @EventHandler
//    public void on(ProductReservedEvent event){
//        ProductEntity productEntity = productRepository.getOne(event.getProductId());
//        if(productEntity==null){
//            LOGGER.error("商品{}不存在", productEntity.getName());
//            return;
//        }
//        productEntity.setNums(event.getNums());
//        productRepository.save(productEntity);
//    }
//
//    @EventHandler
//    public void on(ReserveCancelledEvent event){
//        ProductEntity productEntity = productRepository.getOne(event.getProductId());
//        if(productEntity==null){
//            LOGGER.error("商品{}不存在", productEntity.getName());
//            return;
//        }
//        productEntity.setNums(event.getNums());
//        productRepository.save(productEntity);
//    }
//
//
//
//
//
//
//}
