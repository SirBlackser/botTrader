package be.ds.projects.botTrader.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "tickers")
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(scale = 2)
    private Double last;

    @NotNull
    @Column(scale = 2)
    private Double high;

    @NotNull
    @Column(scale = 2)
    private Double low;

    @NotNull
    @Column(scale = 2)
    private Double vwap;

    @NotNull
    @Column(scale = 8)
    private Double volume;

    @NotNull
    @Column(scale = 2)
    private Double bid;

    @NotNull
    @Column(scale = 2)
    private Double ask;

    @NotNull
    private Long timestamp;

    @NotNull
    @Column(scale = 2)
    private Double open;

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

}
