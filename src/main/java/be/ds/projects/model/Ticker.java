package be.ds.projects.model;

@SuppressWarnings("unused")
public class Ticker {

    // Last BTC price
    private Double last;

    // Last X hours price high
    private Double high;

    // Last X hours price low
    private Double low;

    // Last X hours volume weighted average price
    private Double vwap;

    // Last X hours volume
    private Double volume;

    // Highest buy order
    private Double bid;

    // Lowest sell order
    private Double ask;

    // Unix timestamp date and time
    private Long timestamp;

    // First price of the day
    private Double open;

    public Double getLast() {
        return last;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getVwap() {
        return vwap;
    }

    public Double getVolume() {
        return volume;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getOpen() {
        return open;
    }

}
