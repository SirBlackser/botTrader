package be.ds.projects.botTrader.testbench.visualizer;

import be.ds.projects.botTrader.model.DataCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

/**
 * @author Steven de Cleene
 */
public class ResultVisualizer {

    private final DataCollection dataCollection;

    public ResultVisualizer(DataCollection dataCollection) {
        this.dataCollection = dataCollection;
    }

    public void showAlgorithmResults(final List<Long> buyTimestamps, final List<Long> sellTimestamps) {
        new ResultVisualizerChart(dataCollection, buyTimestamps, sellTimestamps);
//        final ResultVisualizerFrame resultVisualizerFrame = new ResultVisualizerFrame(dataCollection, buyTimestamps, sellTimestamps);
//        resultVisualizerFrame.pack();
//        RefineryUtilities.centerFrameOnScreen(resultVisualizerFrame);
//        resultVisualizerFrame.setVisible(true);
    }

}
