package be.ds.projects.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TradingPairInfo {

    private String name;

    @JsonProperty("url_symbol")
    private String urlSymbol;

    @JsonProperty("base_decimals")
    private Integer baseDecimals;

    @JsonProperty("counter_decimals")
    private Integer counterDecimals;

    @JsonProperty("minimum_order")
    private String minimumOrder;

    private String trading;

    private String description;

    public String getName() {
        return name;
    }

    public String getUrlSymbol() {
        return urlSymbol;
    }

    public Integer getBaseDecimals() {
        return baseDecimals;
    }

    public Integer getCounterDecimals() {
        return counterDecimals;
    }

    public String getMinimumOrder() {
        return minimumOrder;
    }

    public String getTrading() {
        return trading;
    }

    public String getDescription() {
        return description;
    }

}
