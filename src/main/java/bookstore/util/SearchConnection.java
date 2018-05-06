package bookstore.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SearchConnection {

    private static TransportClient client;

    public static TransportClient getClient() {

        if (client == null) {
            Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch_rudeigerc").build();
            try {
                client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

}
