package org.example;

import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.*;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.IDToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapTypeConfigPropertyMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

    private static final String MAP_CONFIG_ID = "mapping-example";
    private static final List<ProviderConfigProperty> PROVIDER_CONFIG = new ArrayList<>();

    static {
        PROVIDER_CONFIG.add(new ProviderConfigProperty(MAP_CONFIG_ID, "Mapping Example", "Mapping table to show issue with configuration UI", ProviderConfigProperty.MAP_TYPE, null));
        OIDCAttributeMapperHelper.addAttributeConfig(PROVIDER_CONFIG, MapTypeConfigPropertyMapper.class);
    }

    @Override
    public String getDisplayCategory() {
        return TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return "MapType Config Mapper Example";
    }

    @Override
    public String getHelpText() {
        return "A sample Keycloak OIDC mapper provider for showing the MAP_TYPE ProviderConfigProperty issue in configuration page UI.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return Collections.unmodifiableList(PROVIDER_CONFIG);
    }

    @Override
    public String getId() {
        return "oidc.map.type.config.example";
    }

    @Override
    protected void setClaim(IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession, KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx) {
        // Just add current value of current mapping setup to a claim for debugging
        String mapValue = mappingModel.getConfig().get(MAP_CONFIG_ID);
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, mapValue);
    }
}
