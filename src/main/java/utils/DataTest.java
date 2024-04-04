package utils;

import api.PersonRequest;

public class DataTest {

  public static PersonRequest getPerson1() {
    PersonRequest request = new PersonRequest();
    request.setSex( 1 );
    request.setDtOfBirth( "01.01.1998" );
    request.setRegion( 86 );
    request.setIncome( 346.345  );
    return request;
  }

  public static PersonRequest getPerson2() {
    PersonRequest request = new PersonRequest();
    request.setSex( 1 );
    request.setDtOfBirth( "06.03.2024" );
    request.setRegion( 86 );
    return request;
  }

  public static PersonRequest getPerson3() {
    PersonRequest request = new PersonRequest();
    request.setSex( 0 );
    request.setDtOfBirth( "01.01.1988" );
    request.setRegion( 56 );
    request.setIncome( 60.0  );
    return request;
  }

  public static PersonRequest getPerson4() {
    PersonRequest request = new PersonRequest();
    request.setSex( 0 );
    request.setDtOfBirth( "01.01.1982" );
    request.setRegion( 56 );
    return request;
  }
}
