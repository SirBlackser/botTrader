package be.ds.projects.botTrader.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "datapoints")
public class DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tickerId")
    private Ticker ticker;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderBookId")
    private OrderBook orderBook;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dataCollectionId", nullable = false)
    private DataCollection dataCollection;

    public DataPoint() {}

    public DataPoint(final Ticker ticker, final OrderBook orderBook) {
        this.ticker = ticker;
        this.orderBook = orderBook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public OrderBook getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    public DataCollection getDataCollection() {
        return dataCollection;
    }

    public void setDataCollection(DataCollection dataCollection) {
        this.dataCollection = dataCollection;
    }

}
