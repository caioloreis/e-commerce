package devcaio.ecommerce.dto;

public record PaginationResponse(Integer page,
                                     Integer pageSize,
                                     Long totalElements,
                                     Integer totalPages) {
}
