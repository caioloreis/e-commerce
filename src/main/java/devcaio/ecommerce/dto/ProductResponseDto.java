package devcaio.ecommerce.dto;

import devcaio.ecommerce.entity.ProductEntity;
import devcaio.ecommerce.entity.TagEntity;

import java.util.List;

public record ProductResponseDto(Long productId,
                                 String productName,
                                 List<TagResponseDto> tags) {

    public static ProductResponseDto fromEntity(ProductEntity product) {
        return new ProductResponseDto(
                product.getProdutcId(),
                product.getName(),
                getTags(product.getTags())
        );
    }

    private static List<TagResponseDto> getTags(List<TagEntity> tags) {

        return tags.stream()
                .map(TagResponseDto::fromEntity)
                .toList();
    }
}