public class ticker {
    //Last BTC price (average I guess)
    int last;

    //Highest price in the last 24 hours
    int high;

    //Lowest price in the last 24 hours
    int low;

    //24h volume weighted average pcire
    int vwap;

    //volume of the last 24 hours
    int volume;

    //highest buy order (atm or last 24h?)
    int bid;

    //lowest buy order (last 24h or atm?)
    int ask;

    //unix timestamp
    Long timestamp;

    //first price of the day
    int open;

    private ticker(int last, int high, int low, int vwap, int volume, int bid, int ask, Long timestamp, int open) {
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

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getVwap() {
        return vwap;
    }

    public void setVwap(int vwap) {
        this.vwap = vwap;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getAsk() {
        return ask;
    }

    public void setAsk(int ask) {
        this.ask = ask;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
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
