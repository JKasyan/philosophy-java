package generics.selfbounded;

/**
 * Created by evgen on 09.01.16.
 */
public class GenericClass<T> {}

class CuriouslyRecurringGeneric extends GenericClass<CuriouslyRecurringGeneric>{}
