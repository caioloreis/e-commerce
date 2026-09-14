package devcaio.ecommerce.dto;

import devcaio.ecommerce.entity.OrderEntity;
import devcaio.ecommerce.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(Long id,
                               BigDecimal total,
                               LocalDateTime orderDate,
                               UUID userId,
                               List<OrderItemResponseDto> itens) {


    public static OrderResponseDto fromEntity(OrderEntity entity) {
            return new OrderResponseDto(
                    entity.getId(),
                    entity.getTotal(),
                    entity.getOrderDate(),
                    entity.getUser().getUserId(),
                    getItens(entity.getItens())
            );
    }

    private static List<OrderItemResponseDto> getItens(List<OrderItemEntity> itens) {
        return  itens.stream()
                .map(OrderItemResponseDto:: fromEntity)
                .toList();
    }
}
