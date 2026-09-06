package devcaio.ecommerce.service;

import devcaio.ecommerce.dto.CreateUserDto;
import devcaio.ecommerce.entity.BillingAddressEntity;
import devcaio.ecommerce.entity.UserEntity;
import devcaio.ecommerce.repository.BillingAddressRepository;
import devcaio.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository  userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto) {

        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());

        var savedBillingAddress = billingAddressRepository.save(billingAddress);

        var user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(savedBillingAddress);

        return userRepository.save(user);
    }


}
