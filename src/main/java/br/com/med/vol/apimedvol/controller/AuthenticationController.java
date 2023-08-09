package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.infra.security.TokenJWTData;
import br.com.med.vol.apimedvol.infra.security.TokenService;
import br.com.med.vol.apimedvol.model.user.AuthenticationData;
import br.com.med.vol.apimedvol.model.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(
            @RequestBody
            @Valid
            AuthenticationData authenticationData)
    {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User)authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
