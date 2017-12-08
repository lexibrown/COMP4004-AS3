package strategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig;

import com.lexi.comp4004.common.game.util.Config;
import com.lexi.comp4004.common.game.util.Config.Key;

public class AuthorizationConfigurator extends ClientEndpointConfig.Configurator {
	@Override
	public void beforeRequest(Map<String, List<String>> headers) {
		headers.put(Key.TOKEN, Arrays.asList(Config.token));
	}
}
