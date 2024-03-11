package artoria.core.chain;

import artoria.core.Context;

public interface ChainContext extends Context {

    Chain getChain();

    Object[] getArguments();

    Object getResult();

    void setResult(Object result);

}
