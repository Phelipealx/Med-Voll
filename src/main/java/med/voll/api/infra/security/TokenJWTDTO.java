package med.voll.api.infra.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenJWTDTO(@JsonProperty("tokenJWT") String token) {
}
