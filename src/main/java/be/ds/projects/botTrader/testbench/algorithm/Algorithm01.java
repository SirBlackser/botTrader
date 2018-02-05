package be.ds.projects.botTrader.testbench.algorithm;

import be.ds.projects.botTrader.model.Ask;
import be.ds.projects.botTrader.model.Ticker;

import java.util.LinkedList;

/**
 * @author Dries Blontrock
 */
public class Algorithm01 {

    private long moveingAverage;
    private double moveingAverageNumber;
    private double percentageProfit;
    private double sellDropOff;
    private LinkedList values;
    private long sum;
    private LinkedList<Ask> buys;
    private double prevMoveingAverage;

    public Algorithm01(double moveingAverageNumber, double percentageProfit, double sellDropOff) {
        this.moveingAverageNumber = moveingAverageNumber;
        this.percentageProfit = 1.05 + (percentageProfit/100);
        this.sellDropOff = sellDropOff;
        this.moveingAverage = 0;
        this.prevMoveingAverage = this.moveingAverage;
        values = new LinkedList();
        buys = new LinkedList<>();
        sum = 0;
    }

    public void doAwesomeShitV1(Ticker ticker) {
        this.prevMoveingAverage = this.moveingAverage;
        if (values.size() == moveingAverageNumber && moveingAverageNumber > 0)
        {
            sum -= ((Double) values.getFirst()).doubleValue();
            values.removeFirst();
        }
        sum += ticker.getLast();
        values.addLast(new Double(ticker.getLast()));
        moveingAverage = sum / values.size();

        if(buys.size() > 0 && buys.getLast().getPrice()*percentageProfit < ticker.getLast() && prevMoveingAverage > moveingAverage) {
            // TODO sell
        } else if(moveingAverage > prevMoveingAverage) {
            // TODO buy
        } else if((buys.getLast().getPrice()*(1-(sellDropOff/100))) < moveingAverage) {
            // TODO sell
        }
    }

}
