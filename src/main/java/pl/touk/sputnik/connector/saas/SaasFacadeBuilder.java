package pl.touk.sputnik.connector.saas;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jetbrains.annotations.NotNull;
import pl.touk.sputnik.configuration.Configuration;
import pl.touk.sputnik.connector.ConnectorDetails;
import pl.touk.sputnik.connector.github.GithubPatchsetBuilder;
import pl.touk.sputnik.connector.http.HttpConnector;
import pl.touk.sputnik.connector.http.HttpHelper;

public class SaasFacadeBuilder {

    private HttpHelper httpHelper = new HttpHelper();

    @NotNull
    public SaasFacade build(Configuration configuration) {
        ConnectorDetails connectorDetails = new ConnectorDetails(configuration);

        HttpHost httpHost = httpHelper.buildHttpHost(connectorDetails);
        HttpClientContext httpClientContext = httpHelper.buildClientContext(httpHost, new BasicScheme());
        CloseableHttpClient closeableHttpClient = httpHelper.buildClient(httpHost, connectorDetails);

        return new SaasFacade(new SaasConnector(
                new HttpConnector(closeableHttpClient, httpClientContext, connectorDetails.getPath()),
                GithubPatchsetBuilder.build(configuration)), new Gson());
    }

}
