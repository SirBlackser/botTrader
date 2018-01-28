package be.ds.projects.botTrader.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import be.ds.projects.botTrader.model.TickerRepository;

/**
 * @author Steven de Cleene
 */
public class ReadTickerJob implements Job {

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {

//        SchedulerContext schedulerContext = null;
//        try {
//            schedulerContext = context.getScheduler().getContext();
//        } catch (SchedulerException e) {
//            // Ignore for now
//        }
//        try {
//            ((ConfigurableApplicationContext) schedulerContext.get("springCTX"))
//                    .getBean("tickerRepository", TickerRepository.class)
//                    .save(BitStampInterfacer.ticker(CurrencyPair.BTC2EUR));
//        } catch (Exception e) {
//            // ignore this one for now too
//        }
//
//        System.out.println("Persisted ticker values! [" + LocalDateTime.now().toString() + "]");

    }

}
