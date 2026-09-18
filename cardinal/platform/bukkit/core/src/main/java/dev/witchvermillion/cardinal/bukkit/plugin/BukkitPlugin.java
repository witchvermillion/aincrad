package dev.witchvermillion.cardinal.bukkit.plugin;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import jakarta.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER})
public @interface BukkitPlugin {}
