import io.avaje.inject.InjectModule;

@InjectModule(name = "profile-runtime")
module dev.witchvermillion.cardinal.profile.runtime {
  requires static org.jspecify;
  requires dev.witchvermillion.cardinal.profile.api;
  requires org.mongodb.driver.reactivestreams;
  requires org.mongodb.driver.core;
  requires org.mongodb.bson;
  requires io.avaje.inject;
  requires jakarta.inject;
  requires reactor.core;
  requires redisson;
}
