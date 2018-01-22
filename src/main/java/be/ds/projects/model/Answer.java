package be.ds.projects.model;

public class Answer {
    Long ID;
    Long dateTime;
    double type;
    double price;
    double amount;

    public Answer(Long ID, Long dateTime, double type, double price, double amount) {
        this.ID = ID;
        this.dateTime = dateTime;
        this.type = type;
        this.price = price;
        this.amount = amount;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "ID=" + ID +
                ", dateTime=" + dateTime +
                ", type=" + type +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
