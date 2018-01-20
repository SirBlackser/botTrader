package be.ds.projects.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

@SuppressWarnings("unused")
public class Ask {

    private Double price;

    private Double amount;

    @JsonCreator
    public Ask(final List<Double> doubleList) {
        this.price = doubleList.get(0);
        this.amount = doubleList.get(1);
    }

    public Double getPrice() {
        return price;
    }

    public Double getAmount() {
        return amount;
    }

}
