package dev.witchvermillion.seed.participant.component;

import io.github.elebras1.flecs.annotation.Component;
import org.jspecify.annotations.NonNull;

@Component
public record Username(@NonNull String username) {}
