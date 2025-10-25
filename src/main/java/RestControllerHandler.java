import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import java.util.Optional;

public class RestControllerHandler extends AzureSpringBootRequestHandler<HttpRequestMessage<Optional<String>>, HttpResponseMessage> {
    @FunctionName("labsync-api")
    public HttpResponseMessage execute(
            @HttpTrigger(
                    name = "req",

                    methods = {
                            HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT,
                            HttpMethod.DELETE, HttpMethod.OPTIONS
                    },

                    authLevel = AuthorizationLevel.ANONYMOUS,

                    route = "{*path}"
            ) HttpRequestMessage<Optional<String>> request,

            ExecutionContext context) {

        // Log opcional para depuração
        context.getLogger().info("Requisição recebida para rota: " + request.getUri().getPath());

        // handleRequest é o método da classe pai (AzureSpringBootRequestHandler)
        return super.handleRequest(request, context);
    }
}
