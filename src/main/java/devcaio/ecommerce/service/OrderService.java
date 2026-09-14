package devcaio.ecommerce.service;

import devcaio.ecommerce.dto.CreateOrderDto;
import devcaio.ecommerce.dto.OrderItemDto;
import devcaio.ecommerce.dto.OrderSummaryDto;
import devcaio.ecommerce.entity.*;
import devcaio.ecommerce.exception.CreateOrderException;
import devcaio.ecommerce.repository.OrderRepository;
import devcaio.ecommerce.repository.ProductRepository;
import devcaio.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderEntity createOrder(CreateOrderDto dto) {

        var order = new OrderEntity();

        var user = validateUser(dto);

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        var orderItems = validateOrderItems(order, dto);

        var total = calculateOrderTotal(orderItems);

        order.setItens(orderItems);
        order.setTotal(total);

        return orderRepository.save(order);
    }

    private UserEntity validateUser(CreateOrderDto dto) {

        return userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new CreateOrderException("User not found"));
    }

    private List<OrderItemEntity> validateOrderItems(
            OrderEntity order,
            CreateOrderDto dto) {

        if (dto.items().isEmpty()) {
            throw new CreateOrderException("Order items is empty");
        }

        return dto.items()
                .stream()
                .map(orderItemDto ->
                        getOrderItem(order, orderItemDto))
                .toList();
    }

    private OrderItemEntity getOrderItem(
            OrderEntity order,
            OrderItemDto orderItemDto) {

        var orderItemEntity = new OrderItemEntity();

        var id = new OrderItemId();

        var product = getProduct(orderItemDto.productId());

        id.setOrder(order);
        id.setProduct(product);

        orderItemEntity.setId(id);
        orderItemEntity.setQuantity(orderItemDto.quantity());
        orderItemEntity.setSalePrice(product.getPrice());

        return orderItemEntity;
    }

    private ProductEntity getProduct(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new CreateOrderException("Product not found"));
    }

    private BigDecimal calculateOrderTotal(
            List<OrderItemEntity> items) {

        return items.stream()
                .map(item ->
                        item.getSalePrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                )
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Page<OrderSummaryDto> findAll(Integer page, Integer pageSize) {
        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(entity ->{
                    return new OrderSummaryDto(
                            entity.getId(),
                            entity.getOrderDate(),
                            entity.getUser().getUserId(),
                            entity.getTotal()

                    );
                });
    }

    public Optional<OrderEntity> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}