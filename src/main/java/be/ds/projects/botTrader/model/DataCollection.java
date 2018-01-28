package be.ds.projects.botTrader.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Steven de Cleene
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "datacollections")
public class DataCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dataCollection")
    private List<DataPoint> dataPoints;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyPair currencyPair;

    @NotNull
    private Long startTimeStamp;

    @NotNull
    private Long stopTimeStamp;

    @NotNull
    private Integer readInterval;

    private Boolean gracefulFinish = true;

    public DataCollection() {
        dataPoints = new ArrayList<>();
    }

    public DataCollection(final CurrencyPair currencyPair, final Long startTimeStamp, final Long stopTimeStamp,
                          final Integer readInterval) {
        this.dataPoints = new ArrayList<>();
        this.currencyPair = currencyPair;
        this.startTimeStamp = startTimeStamp;
        this.stopTimeStamp = stopTimeStamp;
        this.readInterval = readInterval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(CurrencyPair currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Long getStopTimeStamp() {
        return stopTimeStamp;
    }

    public void setStopTimeStamp(Long stopTimeStamp) {
        this.stopTimeStamp = stopTimeStamp;
    }

    public Integer getReadInterval() {
        return readInterval;
    }

    public void setReadInterval(Integer readInterval) {
        this.readInterval = readInterval;
    }

    public Boolean getGracefulFinish() {
        return gracefulFinish;
    }

    public void setGracefulFinish(Boolean gracefulFinish) {
        this.gracefulFinish = gracefulFinish;
    }

}
