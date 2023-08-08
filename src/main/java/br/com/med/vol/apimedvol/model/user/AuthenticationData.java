package br.com.med.vol.apimedvol.model.user;

public record AuthenticationData(
        String login,
        String password
) {
}
