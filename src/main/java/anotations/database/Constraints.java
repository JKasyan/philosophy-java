package anotations.database;

import java.lang.annotation.*;

/**
 * Created by evgen on 10.01.16.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {

    public boolean primaryKey() default false;
    public boolean allowNull() default true;
    public boolean unique() default false;
}
