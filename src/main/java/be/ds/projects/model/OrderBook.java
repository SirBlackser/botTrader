package be.ds.projects.model;

import java.util.List;

public class OrderBook {

    private Long timestamp;

    private List<Bid> bids;

    private List<Ask> asks;

    public Long getTimestamp() {
        return timestamp;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public List<Ask> getAsks() {
        return asks;
    }

}
