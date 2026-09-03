package devcaio.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "tb_billing_adress")
public class BillingAdressEntity {


    @Id
    @Column (name = "billing_adress_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billingAdressId;

    @Column (name = "adress")
    private String address;

    @Column (name = "number")
    private String number;

    @Column (name = "complement")
    private String complement;

    @OneToOne(mappedBy = "billingAdress")

    private UserEntity user;


    public BillingAdressEntity() {
    }

    public long getBillingAdressId() {
        return billingAdressId;
    }

    public void setBillingAdressId(long billingAdressId) {
        this.billingAdressId = billingAdressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
