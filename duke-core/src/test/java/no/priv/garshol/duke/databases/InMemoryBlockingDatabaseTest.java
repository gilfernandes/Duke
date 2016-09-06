
package no.priv.garshol.duke.databases;

import no.priv.garshol.duke.Configuration;
import no.priv.garshol.duke.Database;
import no.priv.garshol.duke.Record;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryBlockingDatabaseTest extends DatabaseTest {

  public Database createDatabase(Configuration config) {
    InMemoryBlockingDatabase db = new InMemoryBlockingDatabase();
    db.setConfiguration(config);

    Collection<KeyFunction> functions = new ArrayList<KeyFunction>();
    functions.add(new TestKeyFunction());
    db.setKeyFunctions(functions);
    return db;
  }

  private static class TestKeyFunction implements KeyFunction {
    public String makeKey(Record record) {
      return record.getValue("NAME");
    }
  }
}