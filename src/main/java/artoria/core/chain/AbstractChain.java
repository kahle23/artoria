package artoria.core.chain;

import artoria.util.Assert;

import java.lang.reflect.Type;

public abstract class AbstractChain implements Chain {

    protected ChainContext buildContext(Object[] arguments) {

        return new ChainContextImpl(this, arguments);
    }

    protected Iterable<ChainNode> getNodes() {

        throw new UnsupportedOperationException();
    }

    protected Object getResult(ChainContext context) {

        return context.getResult();
    }

    @Override
    public Object execute(Object[] arguments) {
        ChainContext context = buildContext(arguments);
        Iterable<ChainNode> nodes = getNodes();
        Assert.notNull(nodes, "Chain nodes must not null. ");
        for (ChainNode node : nodes) {
            if (!node.execute(context)) { break; }
        }
        return getResult(context);
    }

    public Object execute(Object input, String operation, Type type) {

        return execute(new Object[]{operation, input, type});
    }

    public Object execute(Object input) {

        return execute(new Object[]{null, input});
    }

    public static class ChainContextImpl implements ChainContext {
        private final Object[] arguments;
        private final Chain chain;
        private Object result;

        public ChainContextImpl(Chain chain, Object[] arguments) {
            Assert.notNull(arguments, "Parameter \"arguments\" must not null. ");
            Assert.notNull(chain, "Parameter \"chain\" must not null. ");
            this.arguments = arguments;
            this.chain = chain;
        }

        @Override
        public Chain getChain() {

            return chain;
        }

        @Override
        public Object[] getArguments() {

            return arguments;
        }

        @Override
        public Object getResult() {

            return result;
        }

        @Override
        public void setResult(Object result) {

            this.result = result;
        }

    }

}
