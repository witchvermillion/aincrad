import io.avaje.inject.InjectModule;

@InjectModule(name = "profile-runtime")
module dev.witchvermillion.cardinal.profile.runtime {
  requires static org.jspecify;
  requires dev.witchvermillion.cardinal.profile.api;
  requires io.avaje.inject;
  requires jakarta.inject;
  requires reactor.core;
}
