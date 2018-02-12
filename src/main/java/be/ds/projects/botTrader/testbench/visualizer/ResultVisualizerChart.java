package be.ds.projects.botTrader.testbench.visualizer;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Steven de Cleene
 */
public class ResultVisualizerChart extends JPanel {

    private XYDataset currencyValues;

    private XYDataset buyTimestamps;

    private XYDataset sellTimestamps;

    public ResultVisualizerChart(final DataCollection dataCollection, final List<Long> buyTimestamps, final List<Long> sellTimestamps) {
        super(new BorderLayout());
        initializeDataSets(dataCollection, buyTimestamps, sellTimestamps);
        add(createChart());
    }

    private void initializeDataSets(final DataCollection dataCollection, final List<Long> buyTimestamps, final List<Long> sellTimestamps) {

        final XYSeries currencyValuesSeries = new XYSeries("Currency Values");
        final XYSeries buyTimestampsSeries = new XYSeries("Buys");
        final XYSeries sellTimestampsSeries = new XYSeries("Sells");

        int buyTimestampIndex = 0;
        int sellTimestampIndex = 0;

        for (final DataPoint dataPoint : dataCollection.getDataPoints()) {

            final Double tickerValue = dataPoint.getTicker().getLast();
            final Long tickerTimestamp = dataPoint.getTicker().getTimestamp();

            currencyValuesSeries.add(tickerTimestamp, tickerValue);

            if (tickerTimestamp.equals(buyTimestamps.get(buyTimestampIndex)) && buyTimestampIndex < buyTimestamps.size()) {
                buyTimestampsSeries.add(tickerTimestamp, tickerValue);
                buyTimestampIndex++;
            }

            if (tickerTimestamp.equals(sellTimestamps.get(sellTimestampIndex)) && sellTimestampIndex < sellTimestamps.size()) {
                sellTimestampsSeries.add(tickerTimestamp, tickerValue);
                sellTimestampIndex++;
            }

        }

        this.currencyValues = new XYSeriesCollection(currencyValuesSeries);
        this.buyTimestamps = new XYSeriesCollection(buyTimestampsSeries);
        this.sellTimestamps = new XYSeriesCollection(sellTimestampsSeries);

    }

    private ChartPanel createChart() {

        final NumberAxis xAxis = new NumberAxis("Timestamp");
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis("Currency Value");
        yAxis.setAutoRangeIncludesZero(false);

        final XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        final XYLineAndShapeRenderer shapeRenderer = new XYLineAndShapeRenderer(false, true);

        lineRenderer.setSeriesPaint(0, Color.BLACK);
        shapeRenderer.setSeriesPaint(0, Color.GREEN);
        shapeRenderer.setSeriesPaint(1, Color.RED);

        final XYPlot plot = new XYPlot();

        plot.setDataset(0, currencyValues);
        plot.setRenderer(0, lineRenderer);

        plot.setDataset(1, buyTimestamps);
        plot.setRenderer(1, shapeRenderer);

        plot.setDataset(2, sellTimestamps);
        plot.setRenderer(2, shapeRenderer);

        JFreeChart chart = new JFreeChart("Algorithm Result Visualizer", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        return new ChartPanel(chart);

    }

}
