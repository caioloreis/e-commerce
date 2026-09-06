package devcaio.ecommerce.dto;

public record CreateUserDto(String fullName,
                            String address,
                            String number,
                            String complement
                            ) {
}
