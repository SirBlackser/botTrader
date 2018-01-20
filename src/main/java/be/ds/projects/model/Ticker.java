package be.ds.projects.model;

@SuppressWarnings("unused")
public class Ticker {

    private Double last;

    private Double high;

    private Double low;

    private Double vwap;

    private Double volume;

    private Double bid;

    private Double ask;

    private Long timestamp;

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
