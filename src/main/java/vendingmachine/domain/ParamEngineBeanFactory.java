package vendingmachine.domain;

import org.smartparam.engine.config.ParamEngineConfig;
import org.smartparam.engine.config.ParamEngineConfigBuilder;
import org.smartparam.engine.config.ParamEngineFactory;
import org.smartparam.engine.core.ParamEngine;
import org.smartparam.repository.fs.ClasspathParamRepository;

/**
 *
 * @author Adam Dubiel
 */
public class ParamEngineBeanFactory {

    public ParamEngine create() {
        ClasspathParamRepository repository = new ClasspathParamRepository("/param", ".*\\.param$");
        ParamEngineConfig config = ParamEngineConfigBuilder.paramEngineConfig()
                .withPackagesToScan("vendingmachine.domain")
                .withParameterRepositories(repository).build();

        return ParamEngineFactory.paramEngine(config);
    }

}
