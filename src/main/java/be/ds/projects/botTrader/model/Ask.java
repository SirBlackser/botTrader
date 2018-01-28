package be.ds.projects.botTrader.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "asks")
public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // Currency price for this ask
    @Column(scale = 2)
    private Double price;

    // Currency amount for this ask
    @Column(scale = 8)
    private Double amount;

    public Ask() {}

    @JsonCreator
    public Ask(final List<Double> doubleList) {
        this.price = doubleList.get(0);
        this.amount = doubleList.get(1);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}