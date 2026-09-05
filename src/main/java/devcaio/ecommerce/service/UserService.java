package devcaio.ecommerce.service;

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

    public UserEntity createUser(CreateUserDto dto){
        return null;

    }
}
