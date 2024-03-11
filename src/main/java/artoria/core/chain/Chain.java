package artoria.core.chain;

public interface Chain {

    /**
     *
     * The arguments mean (most of the scenes):
     *      0 strategy or operation or null,
     *      1 input object,
     *      2 return value type
     * @param arguments
     * @return
     */
    Object execute(Object[] arguments);

}
