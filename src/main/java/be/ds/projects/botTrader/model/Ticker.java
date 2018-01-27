package be.ds.projects.botTrader.model;

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

    // Last price
    private Double last;

    // Last 24 hours price high
    private Double high;

    // Last 24 hours price low
    private Double low;

    // Last 24 hours volume weighted average price
    private Double vwap;

    // Last 24 hours volume
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
