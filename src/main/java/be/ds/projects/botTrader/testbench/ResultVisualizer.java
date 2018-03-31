package be.ds.projects.botTrader.testbench;

import be.ds.projects.botTrader.model.DataCollection;
import be.ds.projects.botTrader.model.DataPoint;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class that creates a chart showing algorithm information.
 *
 * @author Steven de Cleene
 */
public class ResultVisualizer {

    public void createAlgorithmResultChart(final Class clazz, final DataCollection dataCollection, final List<Long> buyTimestamps, final List<Long> sellTimestamps) {

        /*
            Read in datasets
         */

        final XYSeries currencyValuesSeries = new XYSeries("Currency Values");
        final XYSeries buyTimestampsSeries = new XYSeries("Buys");
        final XYSeries sellTimestampsSeries = new XYSeries("Sells");

        int buyTimestampIndex = 0;
        int sellTimestampIndex = 0;

        for (final DataPoint dataPoint : dataCollection.getDataPoints()) {

            final Double tickerValue = dataPoint.getTicker().getLast();
            final Long tickerTimestamp = dataPoint.getTicker().getTimestamp();

            currencyValuesSeries.add(tickerTimestamp, tickerValue);

            if (buyTimestampIndex < buyTimestamps.size() && tickerTimestamp.equals(buyTimestamps.get(buyTimestampIndex))) {
                buyTimestampsSeries.add(tickerTimestamp, tickerValue);
                buyTimestampIndex++;
            }

            if (sellTimestampIndex < sellTimestamps.size() && tickerTimestamp.equals(sellTimestamps.get(sellTimestampIndex))) {
                sellTimestampsSeries.add(tickerTimestamp, tickerValue);
                sellTimestampIndex++;
            }

        }

        final XYDataset currencyValues = new XYSeriesCollection(currencyValuesSeries);
        final XYDataset buys = new XYSeriesCollection(buyTimestampsSeries);
        final XYDataset sells = new XYSeriesCollection(sellTimestampsSeries);

        /*
            Define Chart properties
         */

        final NumberAxis xAxis = new NumberAxis("Timestamp");
        xAxis.setAutoRangeIncludesZero(false);

        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(false);

        final XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        lineRenderer.setSeriesPaint(0, Color.BLACK);

        final XYShapeRenderer shapeRendererBuys = new XYShapeRenderer();
        shapeRendererBuys.setSeriesPaint(0, Color.BLUE);

        final XYShapeRenderer shapeRendererSells = new XYShapeRenderer();
        shapeRendererSells.setSeriesPaint(0, Color.RED);

        /*
            Define XY plot and fill it with data
         */

        final XYPlot plot = new XYPlot();

        plot.setDataset(0, currencyValues);
        plot.setRenderer(0, lineRenderer);
        plot.setDomainAxis(0, xAxis);
        plot.setRangeAxis(0, yAxis);

        if(buyTimestamps.size() > 0) {
            plot.setDataset(1, buys);
            plot.setRenderer(1, shapeRendererBuys);
        }

        if(sellTimestamps.size() > 0) {
            plot.setDataset(2, sells);
            plot.setRenderer(2, shapeRendererSells);
        }

        /*
            Construct chart
         */

        final JFreeChart chart = new JFreeChart(clazz.getSimpleName() + " Result Visualizer", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

        /*
            Get path to put chart and save chart
         */

        try {
            final File chartFilePath = new File("./charts/" + clazz.getSimpleName() + "_" + (System.currentTimeMillis() / 1000L) + ".png");
            if (!chartFilePath.getParentFile().getAbsoluteFile().exists()) {
                chartFilePath.getParentFile().mkdir();
            }
            ChartUtilities.saveChartAsPNG(chartFilePath, chart, 1280, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
