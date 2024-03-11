package artoria.generator.code;

import artoria.core.Renderer;
import artoria.generator.Generator;

public interface CodeBuilder extends Generator {

    String getName();

    Renderer getEngine();

    String build(BuildContext context);

}
