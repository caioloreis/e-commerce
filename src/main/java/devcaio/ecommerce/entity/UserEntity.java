package devcaio.ecommerce.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @JoinColumn(name = "billing_adress_id")
    private BillingAdressEntity billingAdress;



    public UserEntity() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BillingAdressEntity getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(BillingAdressEntity billingAdress) {
        this.billingAdress = billingAdress;
    }
}
