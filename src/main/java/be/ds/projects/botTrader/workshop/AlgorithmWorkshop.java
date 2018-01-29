package be.ds.projects.botTrader.workshop;

/**
 * @author Steven de Cleene
 */
public class AlgorithmWorkshop {

    public void doAwesomeShit() {
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
    }

}
