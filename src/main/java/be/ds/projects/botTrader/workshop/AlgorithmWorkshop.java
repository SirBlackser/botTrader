package be.ds.projects.botTrader.workshop;

import be.ds.projects.botTrader.model.Ask;
import be.ds.projects.botTrader.model.Ticker;

import java.util.LinkedList;

/**
 * @author Steven de Cleene
 */
public class AlgorithmWorkshop {

    private long moveingAverage;
    private double moveingAverageNumber;
    private double percentageProfit;
    private double sellDropOff;
    private LinkedList values;
    private long sum;
    private LinkedList<Ask> buys;
    private double prevMoveingAverage;

    public AlgorithmWorkshop(double moveingAverageNumber, double percentageProfit, double sellDropOff) {
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
        /*
            @TODO: Implement algorithm
            Scheduler werkt nu, in main kunt ge scheduler instellen (als dat niet duidelijk is hoe laat je maar weten)
            en die gaat dan data opslaan in de tabellen die automatisch gegenereerd worden.

            SQL: gewoon een locale mysql installeren en setting in application.properties goed zetten (als ge zelfde
            als mij instelt hoeven we die file niet te gitignoren). In die mysql maakt ge gewoon een schema "trader".
            Als ge dan de applicatie runned gaat die automatisch de tabellen maken via de annotaties in de code. In
            application.properties kunt ge spring.jpa.hibernate.ddl-auto ook aanpassen. als ge die op create zet gaat
            die elke keer de databases opnieuw opbouwen (als ge fresh start wilt), met update zit ge veilig.

            dus wat ge gaat krijgen om te testen is een DataCollection, check de objecten om daar verder wijs uit te
            worden zou ik zeggen :')

            Ik heb in DateTimeUtil methods steken om te converteren tussen LocalDateTime en UnixTime en Date. Voel u
            vrij om dat uit te breiden indien nodig.

            Enige dat wel mankeert is een query om een datacollection te krijgen van de database en die chronologisch
            gesorteerd te hebben. Ge moet eens wat rondproberen met die JpaRepository, staan wel wat guides van op
            internet.
            Mogelijk probleem met retrieven gaat lazy loading ook zijn, als ge hier geen oplossing voor vind kunt ge
            bepaalde annotaties veranderen van "FetchType.LAZY" naar FetchType.EAGER.

            Maak miss een tweede Main class aan om deze klasse te starten, dan kan Main die er nu is mooi voorbeeld
            blijven voor data collection

            enjoy ;)
         */

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
