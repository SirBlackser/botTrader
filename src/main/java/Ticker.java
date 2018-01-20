public class Ticker {
    //Last BTC price (average I guess)
    Double last;

    //Highest price in the last 24 hours
    Double high;

    //Lowest price in the last 24 hours
    Double low;

    //24h volume weighted average pcire
    Double vwap;

    //volume of the last 24 hours
    Double volume;

    //highest buy order (atm or last 24h?)
    Double bid;

    //lowest buy order (last 24h or atm?)
    Double ask;

    //unix timestamp
    Double timestamp;

    //first price of the day
    Double open;

    public Ticker() {
    }

    private Ticker(Double last, Double high, Double low, Double vwap, Double volume, Double bid, Double ask, Double timestamp, Double open) {
        this.last = last;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
        this.volume = volume;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
        this.open = open;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVwap() {
        return vwap;
    }

    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }
    @Override
    public String toString() {
        return "ticker{" +
                "last=" + last +
                ", high=" + high +
                ", low=" + low +
                ", vwap=" + vwap +
                ", volume=" + volume +
                ", bid=" + bid +
                ", ask=" + ask +
                ", timestamp=" + timestamp +
                ", open=" + open +
                '}';
    }

}
