import api.PersonRequest;
import utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {

  private static String NAME_URL = "http://localhost:8080/StatisticServices/api/person/add";


  public static class MyFirstThread extends Thread {

    private PersonRequest person;
    MyFirstThread( PersonRequest person ) {
      this.person = person;
    }

    @Override
    public void run() {
      System.out.println("I am: " + Thread.currentThread().getName() + " :: person = " + person );
      try {
        MainClient.setPersonData( person );
      } catch ( IOException e ) {
        throw new RuntimeException( e );
      }
    }
  }

  public static void main( String[] args ) throws IOException {
    System.out.println( "Yxxx" );
    //setPersonData( DataTest.getPerson1() );

    MyFirstThread thread1 = new MyFirstThread( DataTest.getPerson1() );
    MyFirstThread thread2 = new MyFirstThread( DataTest.getPerson2() );
    MyFirstThread thread3 = new MyFirstThread( DataTest.getPerson3() );
    MyFirstThread thread4 = new MyFirstThread( DataTest.getPerson4() );

    ExecutorService service = Executors.newFixedThreadPool(2);
    for (int i = 1; i < 5; i++) {
      if ( i == 1 ) {
        service.submit( thread1 );
      } else if ( i == 2 ) {
        service.submit( thread3 );
      }
    }

    for (int i = 1; i < 5; i++) {
      if ( i == 3 ) {
        service.submit( thread2 );
      } else if ( i == 4 ) {
        service.submit( thread4 );
      }
    }

    service.shutdown();

  }

  public static String setPersonData( PersonRequest person ) throws IOException{
    HttpURLConnection connection = (HttpURLConnection) new URL( NAME_URL ).openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestProperty("Accept", "application/json");

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString( person );
    System.out.println( jsonString );

    connection.setDoOutput(true);
    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    wr.write( jsonString );
    wr.flush();

    int responseCode = connection.getResponseCode();
    if(responseCode == 200){
      System.out.println("POST was successful.");
    }
    else if(responseCode == 401){
      System.out.println("Wrong password.");
    }

    return "";
  }
}
