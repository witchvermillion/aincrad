import io.avaje.inject.InjectModule;

@InjectModule(name = "mongo")
module dev.witchvermillion.cardinal.mongo {
  requires static org.jspecify;
  requires org.mongodb.driver.reactivestreams;
  requires org.mongodb.driver.core;
  requires org.mongodb.bson;
  requires io.avaje.inject;
}
