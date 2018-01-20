package be.ds.projects.model;

@SuppressWarnings("unused")
public class Transaction {

    private Long date;

    private Long tid;

    private Double price;

    private Double amount;

    // 0 = buy
    // 1 = sell
    private Integer type;

    public Long getDate() {
        return date;
    }

    public Long getTid() {
        return tid;
    }

    public Double getPrice() {
        return price;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getType() {
        return type;
    }

}
