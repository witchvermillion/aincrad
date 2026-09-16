import io.avaje.inject.InjectModule;

@InjectModule(name = "redisson")
module dev.witchvermillion.cardinal.redisson {
  requires static org.jspecify;
  requires io.avaje.inject;
  requires redisson;
}
