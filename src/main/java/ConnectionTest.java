import org.neo4j.driver.v1.*;
import org.apache.commons.lang.time.StopWatch;

import java.io.IOException;


/**
 * Created by l on 30-03-2017.
 */
public class ConnectionTest {
    public static void main(String[] args) throws IOException {

        double avg;
        double median;
        long[] times = new long[20];
        int[] rndNodes = {4647,28999,23,500,3556,99873,5,97687,45678,333,299,1111,84376,300090,444444,34,77,9
        ,7778,1000};
        Calculater Cal = new Calculater();
        Driver driver = GraphDatabase.driver(
                "bolt://localhost:7687",
                AuthTokens.basic( "neo4j", "class" ) );
        Session session = driver.session();
        StopWatch timer = new StopWatch();



        System.out.println("The dept of one:");
    for (int i = 0; i < 20; i++) {
        timer.reset();
        timer.start();
         // all persons that a person endorses, i.e., endorsements of depth one.
        StatementResult result = session.run(
            "MATCH (p:Person)-[r:ENDORSES]->(e:Person)" +
                    "WHERE p.id = "+ rndNodes[i] +
                    " RETURN e.name as name");
        timer.stop();
        times[i] =  timer.getTime();

        System.out.println(+timer.getTime()+" ms");
        }

        avg = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("The Median time: "+ median +" ms");

        System.in.read();


        System.out.println("The dept of two:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            // all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.
            StatementResult result2 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        avg = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("The Median time: "+ median +" ms");

        System.in.read();


        System.out.println("The dept of three:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result3 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        avg = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("The Median time: "+ median +" ms");

        System.in.read();


        System.out.println("The dept of four:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result4 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();
       /* while ( result.hasNext() ) {
            Record record = result.next();
            System.out.println( record.get("name").asString() );
        }*/
            System.out.println(+timer.getTime()+" ms");
        }

        avg = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("The Median time: "+ median +" ms");

        System.in.read();



        /*System.out.println("The dept of five:");
        for (int i = 0; i < 20; i++) {
            timer.reset();
            timer.start();
            StatementResult result5 = session.run(
                    "MATCH (p:Person)-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(e:Person)" +
                            "WHERE p.id = "+ rndNodes[i] +
                            " RETURN e.name as name");
            timer.stop();
            times[i] =  timer.getTime();

            System.out.println(+timer.getTime()+" ms");
        }

        avg = Cal.CalAvg(times);
        System.out.println("The Avg time: "+ avg +" ms");
        median = Cal.CalMedian(times);
        System.out.println("The Median time: "+ median +" ms");*/







        session.close();
        driver.close();
    }
}
